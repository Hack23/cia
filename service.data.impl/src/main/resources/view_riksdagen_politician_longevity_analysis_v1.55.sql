DROP VIEW IF EXISTS view_riksdagen_politician_longevity_analysis CASCADE;

CREATE VIEW view_riksdagen_politician_longevity_analysis AS
WITH career_timeline AS (
    -- Aggregate all political activity for each person
    SELECT 
        p.id AS person_id,
        p.first_name,
        p.last_name,
        p.party,
        p.status,
        p.born_year,
        -- Career start: earliest of assignment start or first vote
        LEAST(
            MIN(ad.from_date),
            MIN(vd.vote_date)
        ) AS career_start_date,
        -- Career end: latest of assignment end, last vote, or current date if still active
        GREATEST(
            MAX(COALESCE(ad.to_date, CURRENT_DATE)),
            MAX(vd.vote_date),
            CASE WHEN p.status LIKE '%Tjänstgörande%' THEN CURRENT_DATE ELSE MAX(vd.vote_date) END
        ) AS career_end_date,
        -- Count distinct election cycles participated in
        COUNT(DISTINCT CASE 
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2002 AND 2005 THEN 2002
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2006 AND 2009 THEN 2006
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2010 AND 2013 THEN 2010
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2014 AND 2017 THEN 2014
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2018 AND 2021 THEN 2018
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2022 AND 2025 THEN 2022
            ELSE 2026
        END) AS election_cycles_active,
        -- Total votes cast
        COUNT(DISTINCT vd.embedded_id_ballot_id) AS total_votes_cast,
        -- Total assignments held
        COUNT(DISTINCT ad.hjid) AS total_assignments,
        -- Check if currently active
        BOOL_OR(p.status LIKE '%Tjänstgörande%') AS is_currently_active,
        -- First and last activity years
        EXTRACT(YEAR FROM MIN(COALESCE(ad.from_date, vd.vote_date)))::INTEGER AS first_activity_year,
        EXTRACT(YEAR FROM MAX(COALESCE(ad.to_date, vd.vote_date, CURRENT_DATE)))::INTEGER AS last_activity_year
    FROM person_data p
    LEFT JOIN assignment_data ad ON p.id = ad.intressent_id
    LEFT JOIN vote_data vd ON p.id = vd.embedded_id_intressent_id
    WHERE p.id IS NOT NULL
        AND (ad.from_date IS NOT NULL OR vd.vote_date IS NOT NULL)
    GROUP BY p.id, p.first_name, p.last_name, p.party, p.status, p.born_year
),
activity_patterns AS (
    SELECT 
        ct.*,
        -- Calculate career duration
        career_end_date - career_start_date AS total_career_days,
        ROUND((career_end_date - career_start_date) / 365.25, 1) AS total_career_years,
        -- Calculate age at career start (if born_year available)
        CASE 
            WHEN born_year IS NOT NULL 
            THEN EXTRACT(YEAR FROM career_start_date)::INTEGER - born_year
            ELSE NULL
        END AS age_at_career_start,
        -- Calculate current age or age at career end
        CASE 
            WHEN born_year IS NOT NULL 
            THEN EXTRACT(YEAR FROM career_end_date)::INTEGER - born_year
            ELSE NULL
        END AS age_at_career_end,
        -- Activity intensity: votes per year
        ROUND(total_votes_cast::NUMERIC / 
              NULLIF((career_end_date - career_start_date) / 365.25, 0), 1) 
            AS avg_votes_per_year,
        -- Assignment intensity: assignments per year
        ROUND(total_assignments::NUMERIC / 
              NULLIF((career_end_date - career_start_date) / 365.25, 0), 2) 
            AS avg_assignments_per_year,
        -- Continuity score: ratio of cycles active to total possible cycles
        CASE 
            WHEN first_activity_year <= 2002 THEN 
                ROUND(election_cycles_active::NUMERIC / 7.0 * 100, 1)
            WHEN first_activity_year <= 2006 THEN 
                ROUND(election_cycles_active::NUMERIC / 6.0 * 100, 1)
            WHEN first_activity_year <= 2010 THEN 
                ROUND(election_cycles_active::NUMERIC / 5.0 * 100, 1)
            WHEN first_activity_year <= 2014 THEN 
                ROUND(election_cycles_active::NUMERIC / 4.0 * 100, 1)
            WHEN first_activity_year <= 2018 THEN 
                ROUND(election_cycles_active::NUMERIC / 3.0 * 100, 1)
            WHEN first_activity_year <= 2022 THEN 
                ROUND(election_cycles_active::NUMERIC / 2.0 * 100, 1)
            ELSE 100.0
        END AS career_continuity_score
    FROM career_timeline ct
)
SELECT 
    person_id,
    first_name,
    last_name,
    party,
    status,
    born_year,
    career_start_date,
    career_end_date,
    first_activity_year,
    last_activity_year,
    total_career_days,
    total_career_years,
    age_at_career_start,
    age_at_career_end,
    election_cycles_active,
    total_votes_cast,
    total_assignments,
    is_currently_active,
    avg_votes_per_year,
    avg_assignments_per_year,
    career_continuity_score,
    -- Classify career longevity
    CASE 
        WHEN total_career_years >= 20 THEN 'VETERAN_20_PLUS'
        WHEN total_career_years >= 15 THEN 'LONG_SERVICE_15_20'
        WHEN total_career_years >= 10 THEN 'ESTABLISHED_10_15'
        WHEN total_career_years >= 5 THEN 'MID_CAREER_5_10'
        WHEN total_career_years >= 2 THEN 'JUNIOR_2_5'
        ELSE 'NEWCOMER_UNDER_2'
    END AS longevity_category,
    -- Classify activity level
    CASE 
        WHEN avg_votes_per_year >= 300 THEN 'VERY_ACTIVE'
        WHEN avg_votes_per_year >= 200 THEN 'ACTIVE'
        WHEN avg_votes_per_year >= 100 THEN 'MODERATE'
        WHEN avg_votes_per_year >= 50 THEN 'LOW_ACTIVITY'
        ELSE 'MINIMAL'
    END AS activity_level,
    -- Classify career continuity
    CASE 
        WHEN career_continuity_score >= 90 THEN 'CONTINUOUS'
        WHEN career_continuity_score >= 70 THEN 'MOSTLY_CONTINUOUS'
        WHEN career_continuity_score >= 50 THEN 'INTERMITTENT'
        WHEN career_continuity_score >= 30 THEN 'SPORADIC'
        ELSE 'IRREGULAR'
    END AS continuity_pattern,
    -- Career life stage
    CASE 
        WHEN age_at_career_end IS NULL THEN 'UNKNOWN_AGE'
        WHEN is_currently_active AND age_at_career_end >= 65 THEN 'SENIOR_ACTIVE'
        WHEN is_currently_active AND age_at_career_end >= 50 THEN 'MATURE_ACTIVE'
        WHEN is_currently_active AND age_at_career_end >= 35 THEN 'MID_CAREER_ACTIVE'
        WHEN is_currently_active THEN 'EARLY_CAREER_ACTIVE'
        WHEN age_at_career_end >= 65 THEN 'RETIRED_SENIOR'
        WHEN age_at_career_end >= 50 THEN 'RETIRED_MATURE'
        ELSE 'EXITED_EARLY'
    END AS career_life_stage,
    -- Retention risk indicator (for active politicians)
    CASE 
        WHEN is_currently_active THEN
            CASE 
                WHEN total_career_years >= 15 AND age_at_career_end >= 60 
                    THEN 'HIGH_RETIREMENT_RISK'
                WHEN total_career_years >= 10 AND avg_votes_per_year < 150 
                    THEN 'MODERATE_ATTRITION_RISK'
                WHEN total_career_years < 3 AND avg_votes_per_year < 200 
                    THEN 'EARLY_EXIT_RISK'
                WHEN career_continuity_score < 60 
                    THEN 'ENGAGEMENT_RISK'
                ELSE 'LOW_RISK'
            END
        ELSE 'NOT_ACTIVE'
    END AS retention_risk
FROM activity_patterns
ORDER BY total_career_years DESC, person_id;

COMMENT ON VIEW view_riksdagen_politician_longevity_analysis IS 
'Career longevity and activity pattern analysis measuring politician career duration and engagement levels.
Calculates total career years, election cycles active, activity intensity (votes/assignments per year),
and career continuity scores. Classifies politicians by longevity (veteran/established/junior),
activity level (very active/active/moderate/low), and identifies retention risks for active politicians.
Used for Predictive Intelligence Framework (Framework 4) to forecast career longevity and resignation risks.';
