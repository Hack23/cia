-- view_eu_parliament_representatives v1.50
-- EU Parliament Representatives View with Career Path Analysis
-- Tracks Swedish MEPs with EU parliamentary activity and cross-referencing with Swedish Riksdag service
-- Analysis: 85 Europaparlamentet assignment records in database (DISTINCT_VALUES_ANALYSIS.md)

DROP VIEW IF EXISTS view_eu_parliament_representatives CASCADE;

CREATE VIEW view_eu_parliament_representatives AS
WITH eu_assignments AS (
    SELECT 
        a.intressent_id AS person_id,
        a.intressent_id,
        a.role_code,
        a.org_code,
        a.detail AS role_description,
        a.from_date,
        a.to_date,
        
        -- Tenure calculation
        CASE 
            WHEN a.to_date IS NULL THEN 
                DATE_PART('day', CURRENT_DATE - a.from_date::date)
            ELSE 
                DATE_PART('day', a.to_date::date - a.from_date::date)
        END AS tenure_days,
        
        -- Current status
        CASE 
            WHEN a.to_date IS NULL OR a.to_date::date >= CURRENT_DATE THEN true
            ELSE false
        END AS is_current_mep,
        
        EXTRACT(YEAR FROM a.from_date::date) AS mep_start_year,
        EXTRACT(YEAR FROM COALESCE(a.to_date::date, CURRENT_DATE)) AS mep_end_year
        
    FROM assignment_data a
    WHERE a.assignment_type = 'Europaparlamentet'
),

person_eu_meps AS (
    SELECT 
        eu.*,
        p.first_name,
        p.last_name,
        p.gender,
        p.party AS swedish_party_affiliation,
        p.born_year,
        EXTRACT(YEAR FROM eu.from_date::date) - p.born_year AS age_when_elected_mep
        
    FROM eu_assignments eu
    LEFT JOIN person_data p ON p.id = eu.person_id
),

riksdag_service_history AS (
    SELECT 
        a.intressent_id AS person_id,
        COUNT(*) AS riksdag_assignments,
        MIN(a.from_date) AS first_riksdag_service,
        MAX(COALESCE(a.to_date::date, CURRENT_DATE)) AS last_riksdag_service,
        SUM(
            CASE 
                WHEN a.to_date IS NULL THEN 
                    DATE_PART('day', CURRENT_DATE - a.from_date::date)
                ELSE 
                    DATE_PART('day', a.to_date::date - a.from_date::date)
            END
        ) AS total_riksdag_days,
        
        -- Check if currently in Riksdag
        MAX(CASE WHEN a.to_date IS NULL OR a.to_date::date >= CURRENT_DATE THEN 1 ELSE 0 END) AS currently_in_riksdag
        
    FROM assignment_data a
    WHERE a.role_code = 'Riksdagsledamot'
    GROUP BY a.intressent_id
),

career_path_analysis AS (
    SELECT 
        pem.person_id,
        pem.first_name || ' ' || pem.last_name AS full_name,
        
        -- Career path classification
        CASE 
            WHEN rsh.first_riksdag_service IS NULL THEN 'EU_ONLY'
            WHEN rsh.first_riksdag_service < pem.from_date 
                 AND rsh.last_riksdag_service < pem.from_date THEN 'RIKSDAG_TO_EU'
            WHEN rsh.first_riksdag_service < pem.from_date 
                 AND rsh.last_riksdag_service > COALESCE(pem.to_date::date, CURRENT_DATE) THEN 'RIKSDAG_EU_RIKSDAG'
            WHEN rsh.first_riksdag_service > COALESCE(pem.to_date::date, CURRENT_DATE) THEN 'EU_TO_RIKSDAG'
            WHEN rsh.currently_in_riksdag = 1 AND pem.is_current_mep THEN 'CONCURRENT_SERVICE'
            ELSE 'COMPLEX_PATH'
        END AS career_progression_type,
        
        rsh.total_riksdag_days / 365.25 AS years_in_riksdag,
        rsh.riksdag_assignments,
        rsh.currently_in_riksdag = 1 AS serving_in_both
        
    FROM person_eu_meps pem
    LEFT JOIN riksdag_service_history rsh ON rsh.person_id = pem.person_id
),

party_eu_representation AS (
    SELECT 
        mep_start_year,
        swedish_party_affiliation,
        COUNT(*) AS meps_count,
        AVG(tenure_days) AS avg_tenure_days,
        ROUND(AVG(tenure_days) / 365.25, 2) AS avg_tenure_years,
        COUNT(CASE WHEN is_current_mep THEN 1 END) AS current_meps
    FROM person_eu_meps
    WHERE swedish_party_affiliation IS NOT NULL
    GROUP BY mep_start_year, swedish_party_affiliation
),

temporal_mep_composition AS (
    SELECT 
        mep_start_year AS year,
        COUNT(DISTINCT person_id) AS total_swedish_meps,
        COUNT(DISTINCT swedish_party_affiliation) AS parties_represented,
        STRING_AGG(DISTINCT swedish_party_affiliation, ', ' ORDER BY swedish_party_affiliation) AS party_list,
        
        -- Gender balance
        COUNT(CASE WHEN gender = 'man' THEN 1 END) AS male_meps,
        COUNT(CASE WHEN gender = 'kvinna' THEN 1 END) AS female_meps,
        ROUND(
            COUNT(CASE WHEN gender = 'kvinna' THEN 1 END)::decimal / 
            NULLIF(COUNT(*), 0)::decimal * 100, 
            1
        ) AS female_percentage
    FROM person_eu_meps
    GROUP BY mep_start_year
)

-- Main result set
SELECT 
    pem.person_id,
    pem.intressent_id,
    pem.first_name || ' ' || pem.last_name AS full_name,
    pem.first_name,
    pem.last_name,
    pem.role_code,
    pem.role_description,
    pem.from_date AS mep_start_date,
    pem.to_date AS mep_end_date,
    pem.tenure_days AS mep_tenure_days,
    ROUND(pem.tenure_days / 365.25, 2) AS mep_tenure_years,
    pem.is_current_mep,
    pem.swedish_party_affiliation,
    pem.gender,
    pem.age_when_elected_mep,
    pem.mep_start_year,
    pem.mep_end_year,
    
    -- Career progression
    cpa.career_progression_type,
    cpa.years_in_riksdag,
    cpa.riksdag_assignments,
    cpa.serving_in_both AS concurrent_riksdag_eu_service,
    
    -- Party representation metrics
    per.avg_tenure_years AS party_avg_mep_tenure,
    per.current_meps AS party_current_meps,
    
    -- Temporal composition
    tmc.total_swedish_meps AS year_total_meps,
    tmc.parties_represented AS year_parties_count,
    tmc.female_percentage AS year_female_percentage,
    
    -- Tenure classification
    CASE 
        WHEN pem.tenure_days < 1825 THEN 'SINGLE_TERM'
        WHEN pem.tenure_days < 3650 THEN 'TWO_TERMS'
        ELSE 'VETERAN'
    END AS mep_tenure_classification

FROM person_eu_meps pem
LEFT JOIN career_path_analysis cpa ON cpa.person_id = pem.person_id
LEFT JOIN party_eu_representation per 
    ON per.mep_start_year = pem.mep_start_year 
    AND per.swedish_party_affiliation = pem.swedish_party_affiliation
LEFT JOIN temporal_mep_composition tmc ON tmc.year = pem.mep_start_year
ORDER BY pem.is_current_mep DESC, pem.from_date DESC;
