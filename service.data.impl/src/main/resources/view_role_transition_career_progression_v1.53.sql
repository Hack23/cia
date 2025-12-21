DROP VIEW IF EXISTS view_role_transition_career_progression CASCADE;
CREATE VIEW view_role_transition_career_progression AS

WITH role_power_scoring AS (
    SELECT 
        a.hjid,
        a.intressent_id AS person_id,
        a.role_code,
        a.from_date,
        a.to_date,
        a.org_code,
        a.assignment_type,
        a.status,
        a.detail,
        
        -- Power score calculation (based on v1.44 logic)
        CASE
            /* Level 1: Top Leadership (Prime Minister, Party Leader) */
            WHEN a.assignment_type = 'Departement' AND a.role_code = 'Statsminister' THEN 10.0
            WHEN a.assignment_type = 'partiuppdrag' AND a.role_code = 'Partiledare' THEN 9.5
            WHEN a.assignment_type = 'Departement' AND a.role_code = 'Vice statsminister' THEN 9.0

            /* Level 2: Senior Government (Ministers, Speaker) */
            WHEN a.assignment_type = 'Departement' AND (a.role_code ~~* '%minister%' OR a.role_code = 'Statsråd') THEN 8.5
            WHEN a.assignment_type = 'kammaruppdrag' AND a.role_code = 'Talman' THEN 8.0
            WHEN a.assignment_type = 'talmansuppdrag' AND a.role_code IN ('Förste vice talman', 'Andre vice talman', 'Tredje vice talman') THEN 7.5

            /* Level 3: Committee Chairs and Party Leadership */
            WHEN a.assignment_type IN ('uppdrag', 'Riksdagsorgan') AND a.role_code = 'Ordförande' THEN 7.0
            WHEN a.assignment_type = 'partiuppdrag' AND a.role_code IN ('Gruppledare', 'Partisekreterare') THEN 6.5

            /* Level 4: Committee Vice Chairs and Deputies */
            WHEN a.assignment_type IN ('uppdrag', 'Riksdagsorgan') AND a.role_code = 'Vice ordförande' THEN 6.0
            WHEN a.assignment_type = 'partiuppdrag' AND a.role_code IN ('Förste vice gruppledare', 'Andre vice gruppledare') THEN 5.5

            /* Level 5: Regular Members (MPs, MEPs) */
            WHEN a.assignment_type = 'kammaruppdrag' AND a.role_code = 'Riksdagsledamot' THEN 5.0
            WHEN a.assignment_type = 'Europaparlamentet' AND a.role_code = 'Ledamot' THEN 4.5
            WHEN a.assignment_type IN ('uppdrag', 'Riksdagsorgan') AND a.role_code = 'Ledamot' THEN 4.0

            /* Level 6: Substitute Roles */
            WHEN a.role_code IN ('Suppleant', 'Ersättare', 'Extra suppleant', 'Personlig suppleant') THEN 3.0

            /* Level 7: Regional and Specialized Roles */
            WHEN a.assignment_type = 'Riksdagsorgan' AND a.org_code IN ('RJ', 'NR', 'RFK', 'RRS') THEN 2.5
            WHEN a.assignment_type = 'uppdrag' AND a.role_code = 'Ledamot' AND a.org_code IN ('MJU', 'BoU', 'TU') THEN 2.0

            /* Level 8: Other Roles */
            ELSE 1.0
        END AS aggregate_power_score,
        
        -- Institutional power center classification
        CASE 
            WHEN a.assignment_type = 'Departement' THEN 'EXECUTIVE'
            WHEN a.assignment_type = 'kammaruppdrag' THEN 'LEGISLATIVE'
            WHEN a.assignment_type = 'partiuppdrag' THEN 'PARTY'
            WHEN a.assignment_type = 'Europaparlamentet' THEN 'INTERNATIONAL'
            WHEN a.assignment_type IN ('uppdrag', 'Riksdagsorgan') THEN 'COMMITTEE'
            ELSE 'OTHER'
        END AS institutional_power_center
        
    FROM assignment_data a
),

politician_timeline AS (
    SELECT 
        rps.*,
        
        -- Calculate sequence within person's career
        ROW_NUMBER() OVER (PARTITION BY rps.person_id ORDER BY rps.from_date, rps.hjid) AS career_sequence,
        
        -- Get previous and next roles
        LAG(rps.role_code) OVER (PARTITION BY rps.person_id ORDER BY rps.from_date, rps.hjid) AS previous_role,
        LEAD(rps.role_code) OVER (PARTITION BY rps.person_id ORDER BY rps.from_date, rps.hjid) AS next_role,
        
        LAG(rps.aggregate_power_score) OVER (PARTITION BY rps.person_id ORDER BY rps.from_date, rps.hjid) AS previous_power_score,
        LEAD(rps.aggregate_power_score) OVER (PARTITION BY rps.person_id ORDER BY rps.from_date, rps.hjid) AS next_power_score,
        
        -- Time calculations
        LAG(rps.to_date) OVER (PARTITION BY rps.person_id ORDER BY rps.from_date, rps.hjid) AS previous_role_end,
        LEAD(rps.from_date) OVER (PARTITION BY rps.person_id ORDER BY rps.from_date, rps.hjid) AS next_role_start
        
    FROM role_power_scoring rps
),

role_transitions AS (
    SELECT 
        pt.*,
        p.first_name,
        p.last_name,
        p.party,
        p.gender,
        p.born_year,
        
        -- Calculate age at transition
        EXTRACT(YEAR FROM pt.from_date::date) - p.born_year AS age_at_role_start,
        
        -- Transition type classification
        CASE 
            WHEN pt.previous_role IS NULL THEN 'CAREER_START'
            WHEN pt.next_role IS NULL AND pt.to_date IS NOT NULL THEN 'CAREER_END'
            WHEN pt.aggregate_power_score > pt.previous_power_score + 0.5 THEN 'PROMOTION'
            WHEN pt.aggregate_power_score < pt.previous_power_score - 0.5 THEN 'DEMOTION'
            WHEN ABS(pt.aggregate_power_score - pt.previous_power_score) <= 0.5 THEN 'LATERAL_MOVE'
            ELSE 'TRANSITION'
        END AS transition_type,
        
        -- Power change magnitude
        COALESCE(pt.aggregate_power_score - pt.previous_power_score, 0) AS power_change,
        
        -- Time between roles (gap analysis)
        CASE 
            WHEN pt.previous_role_end IS NOT NULL THEN
                DATE_PART('day', pt.from_date::date - pt.previous_role_end::date)
            ELSE NULL
        END AS days_since_previous_role,
        
        -- Role duration
        CASE 
            WHEN pt.to_date IS NULL THEN 
                DATE_PART('day', CURRENT_DATE - pt.from_date::date)
            ELSE 
                DATE_PART('day', pt.to_date::date - pt.from_date::date)
        END AS role_duration_days
        
    FROM politician_timeline pt
    LEFT JOIN person_data p ON p.id = pt.person_id
),

career_metrics AS (
    SELECT 
        person_id,
        first_name || ' ' || last_name AS full_name,
        party,
        gender,
        
        -- Career statistics
        COUNT(*) AS total_roles,
        COUNT(DISTINCT institutional_power_center) AS power_centers_served,
        MIN(from_date) AS career_start,
        MAX(COALESCE(to_date::date, CURRENT_DATE)) AS career_end,
        
        -- Experience accumulation
        SUM(role_duration_days) / 365.25 AS total_political_years,
        AVG(aggregate_power_score) AS avg_power_score,
        MAX(aggregate_power_score) AS peak_power_score,
        
        -- Transition patterns
        SUM(CASE WHEN transition_type = 'PROMOTION' THEN 1 ELSE 0 END) AS promotions,
        SUM(CASE WHEN transition_type = 'DEMOTION' THEN 1 ELSE 0 END) AS demotions,
        SUM(CASE WHEN transition_type = 'LATERAL_MOVE' THEN 1 ELSE 0 END) AS lateral_moves,
        
        -- Career velocity (promotions per year)
        ROUND(
            SUM(CASE WHEN transition_type = 'PROMOTION' THEN 1 ELSE 0 END)::decimal / 
            NULLIF((MAX(COALESCE(to_date::date, CURRENT_DATE)) - MIN(from_date))::numeric / 365.25, 0),
            2
        ) AS promotions_per_year
        
    FROM role_transitions
    GROUP BY person_id, first_name, last_name, party, gender
),

peak_role_ages AS (
    SELECT 
        rt.person_id,
        MIN(rt.age_at_role_start) AS age_at_peak_role
    FROM role_transitions rt
    INNER JOIN career_metrics cm ON cm.person_id = rt.person_id
    WHERE rt.aggregate_power_score = cm.peak_power_score
    GROUP BY rt.person_id
),

career_path_patterns AS (
    SELECT 
        rt1.person_id,
        rt1.previous_role AS from_role,
        rt1.role_code AS to_role,
        COUNT(*) AS transition_count,
        AVG(rt1.days_since_previous_role) AS avg_gap_days,
        AVG(rt1.age_at_role_start) AS avg_age_at_transition,
        STRING_AGG(DISTINCT rt1.party, ', ') AS parties
    FROM role_transitions rt1
    WHERE rt1.previous_role IS NOT NULL
    GROUP BY rt1.person_id, rt1.previous_role, rt1.role_code
),

common_progressions AS (
    SELECT 
        from_role,
        to_role,
        COUNT(DISTINCT person_id) AS politicians_following_path,
        AVG(avg_gap_days) AS typical_gap_days,
        AVG(avg_age_at_transition) AS typical_age
    FROM career_path_patterns
    GROUP BY from_role, to_role
    HAVING COUNT(DISTINCT person_id) >= 5
),

politician_classification AS (
    SELECT 
        cm.*,
        pra.age_at_peak_role,
        
        -- Career type classification
        CASE 
            WHEN cm.peak_power_score >= 9 THEN 'TOP_TIER_LEADER'
            WHEN cm.peak_power_score >= 7 AND cm.promotions >= 3 THEN 'SENIOR_LEADER'
            WHEN cm.peak_power_score >= 5 AND cm.total_roles >= 5 THEN 'EXPERIENCED_LEGISLATOR'
            WHEN cm.total_political_years >= 10 THEN 'VETERAN_MEMBER'
            ELSE 'JUNIOR_MEMBER'
        END AS career_classification,
        
        -- Career speed classification
        CASE 
            WHEN cm.promotions_per_year >= 0.5 THEN 'FAST_TRACK'
            WHEN cm.promotions_per_year >= 0.25 THEN 'ACCELERATED'
            WHEN cm.promotions_per_year >= 0.1 THEN 'STEADY'
            ELSE 'GRADUAL'
        END AS career_speed,
        
        -- Career trajectory
        CASE 
            WHEN cm.promotions > cm.demotions * 2 THEN 'ASCENDING'
            WHEN cm.demotions > cm.promotions THEN 'DESCENDING'
            WHEN cm.lateral_moves > cm.promotions + cm.demotions THEN 'LATERAL'
            ELSE 'MIXED'
        END AS career_trajectory
        
    FROM career_metrics cm
    LEFT JOIN peak_role_ages pra ON pra.person_id = cm.person_id
)

-- Main result set
SELECT 
    rt.person_id,
    rt.full_name,
    rt.party,
    rt.gender,
    rt.role_code AS current_role,
    rt.previous_role,
    rt.next_role,
    rt.from_date,
    rt.to_date,
    rt.career_sequence,
    rt.age_at_role_start,
    rt.transition_type,
    rt.power_change,
    rt.aggregate_power_score,
    rt.institutional_power_center,
    rt.role_duration_days / 365.25 AS role_duration_years,
    rt.days_since_previous_role,
    
    -- Career metrics
    pc.total_roles,
    pc.total_political_years,
    pc.peak_power_score,
    pc.promotions,
    pc.demotions,
    pc.lateral_moves,
    pc.promotions_per_year,
    pc.age_at_peak_role,
    pc.career_classification,
    pc.career_speed,
    pc.career_trajectory,
    pc.power_centers_served,
    
    -- Common progression context
    cp.politicians_following_path AS others_on_same_path,
    cp.typical_age AS typical_age_for_transition,
    cp.typical_gap_days AS typical_gap_for_transition

FROM role_transitions rt
LEFT JOIN politician_classification pc ON pc.person_id = rt.person_id
LEFT JOIN common_progressions cp 
    ON cp.from_role = rt.previous_role 
    AND cp.to_role = rt.role_code
ORDER BY rt.person_id, rt.career_sequence;
