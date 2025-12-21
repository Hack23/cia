DROP VIEW IF EXISTS view_comprehensive_role_timeline CASCADE;
CREATE VIEW view_comprehensive_role_timeline AS

WITH role_timeline AS (
    SELECT 
        a.intressent_id AS person_id,
        a.intressent_id,
        a.role_code,
        a.org_code,
        a.detail,
        a.assignment_type,
        a.from_date,
        a.to_date,
        
        -- Normalize dates for calculations
        -- Note: Using CURRENT_DATE for active roles (NULL to_date) allows
        -- real-time duration tracking. For historical analysis, consider
        -- filtering by specific date ranges in queries.
        a.from_date::date AS start_date,
        COALESCE(a.to_date::date, CURRENT_DATE) AS end_date,
        
        -- Calculate duration
        CASE 
            WHEN a.to_date IS NULL THEN 
                DATE_PART('day', CURRENT_DATE - a.from_date::date)
            ELSE 
                DATE_PART('day', a.to_date::date - a.from_date::date)
        END AS duration_days,
        
        -- Simplified power score based on role type
        CASE
            WHEN a.assignment_type = 'Departement' AND a.role_code = 'Statsminister' THEN 1000.0
            WHEN a.assignment_type = 'partiuppdrag' AND a.role_code = 'Partiledare' THEN 950.0
            WHEN a.assignment_type = 'Departement' AND (a.role_code ~~* '%minister%' OR a.role_code = 'Statsråd') THEN 850.0
            WHEN a.assignment_type = 'kammaruppdrag' AND a.role_code = 'Talman' THEN 800.0
            WHEN a.assignment_type IN ('uppdrag', 'Riksdagsorgan') AND a.role_code = 'Ordförande' THEN 700.0
            WHEN a.assignment_type = 'partiuppdrag' AND a.role_code IN ('Gruppledare', 'Partisekreterare') THEN 650.0
            WHEN a.assignment_type IN ('uppdrag', 'Riksdagsorgan') AND a.role_code = 'Vice ordförande' THEN 600.0
            WHEN a.assignment_type = 'kammaruppdrag' AND a.role_code = 'Riksdagsledamot' THEN 500.0
            WHEN a.assignment_type IN ('uppdrag', 'Riksdagsorgan') AND a.role_code = 'Ledamot' THEN 400.0
            WHEN a.role_code IN ('Ersättare', 'Statsrådsersättare') THEN 150.0
            WHEN a.role_code IN ('Suppleant', 'Extra suppleant') THEN 100.0
            ELSE 10.0
        END AS aggregate_power_score,
        
        -- Institutional power center classification
        CASE
            WHEN a.assignment_type = 'Departement' THEN 'GOVERNMENT'
            WHEN a.assignment_type = 'kammaruppdrag' THEN 'PARLIAMENT'
            WHEN a.assignment_type = 'partiuppdrag' THEN 'PARTY'
            WHEN a.assignment_type IN ('uppdrag', 'Riksdagsorgan') THEN 'COMMITTEE'
            WHEN a.assignment_type = 'Europaparlamentet' THEN 'EU_PARLIAMENT'
            ELSE 'OTHER'
        END AS institutional_power_center,
        
        -- Role name in English (simplified)
        a.role_code AS role_name_en,
        
        -- Role category
        CASE
            WHEN a.role_code IN ('Statsminister', 'Vice statsminister') THEN 'EXECUTIVE_LEADERSHIP'
            WHEN a.role_code ~~* '%minister%' OR a.role_code = 'Statsråd' THEN 'MINISTER'
            WHEN a.role_code IN ('Talman', 'Förste vice talman', 'Andre vice talman', 'Tredje vice talman') THEN 'SPEAKER'
            WHEN a.role_code IN ('Partiledare', 'Gruppledare', 'Partisekreterare') THEN 'PARTY_LEADERSHIP'
            WHEN a.role_code IN ('Ordförande', 'Vice ordförande') THEN 'COMMITTEE_LEADERSHIP'
            WHEN a.role_code = 'Riksdagsledamot' THEN 'MEMBER_OF_PARLIAMENT'
            WHEN a.role_code = 'Ledamot' THEN 'COMMITTEE_MEMBER'
            WHEN a.role_code IN ('Suppleant', 'Extra suppleant', 'Ersättare') THEN 'SUBSTITUTE'
            ELSE 'OTHER'
        END AS role_category,
        
        -- Current status
        CASE 
            WHEN a.to_date IS NULL OR a.to_date::date >= CURRENT_DATE THEN true
            ELSE false
        END AS is_active
        
    FROM assignment_data a
    WHERE a.intressent_id IS NOT NULL
),

concurrent_roles_analysis AS (
    SELECT 
        rt1.person_id,
        rt1.role_code AS role1,
        rt2.role_code AS role2,
        rt1.start_date AS role1_start,
        rt1.end_date AS role1_end,
        rt2.start_date AS role2_start,
        rt2.end_date AS role2_end,
        
        -- Calculate overlap period
        GREATEST(rt1.start_date, rt2.start_date) AS overlap_start,
        LEAST(rt1.end_date, rt2.end_date) AS overlap_end,
        
        -- Overlap duration (only positive overlaps)
        GREATEST(0, DATE_PART('day', 
            LEAST(rt1.end_date, rt2.end_date) - 
            GREATEST(rt1.start_date, rt2.start_date)
        )) AS overlap_days,
        
        -- Conflict detection (same assignment type in same org)
        CASE 
            WHEN rt1.assignment_type = rt2.assignment_type 
                 AND rt1.org_code = rt2.org_code THEN true
            ELSE false
        END AS potential_conflict,
        
        -- Power accumulation
        rt1.aggregate_power_score + rt2.aggregate_power_score AS combined_power_score
        
    FROM role_timeline rt1
    INNER JOIN role_timeline rt2 
        ON rt1.person_id = rt2.person_id
        -- Use unique identifier to avoid duplicates
        AND (rt1.from_date::text || rt1.role_code || COALESCE(rt1.org_code, '')) < 
            (rt2.from_date::text || rt2.role_code || COALESCE(rt2.org_code, ''))
        -- Proper overlap detection: roles overlap if one starts before the other ends
        AND rt1.start_date <= rt2.end_date
        AND rt2.start_date <= rt1.end_date
),

role_gaps AS (
    SELECT 
        rt1.person_id,
        rt1.role_code AS previous_role,
        rt1.end_date AS previous_role_end,
        rt2.role_code AS next_role,
        rt2.start_date AS next_role_start,
        
        -- Gap duration
        DATE_PART('day', rt2.start_date - rt1.end_date) AS gap_days,
        
        -- Gap classification
        CASE 
            WHEN DATE_PART('day', rt2.start_date - rt1.end_date) <= 0 THEN 'OVERLAP'
            WHEN DATE_PART('day', rt2.start_date - rt1.end_date) <= 30 THEN 'SHORT_GAP'
            WHEN DATE_PART('day', rt2.start_date - rt1.end_date) <= 365 THEN 'MEDIUM_GAP'
            ELSE 'LONG_GAP'
        END AS gap_classification
        
    FROM role_timeline rt1
    LEFT JOIN LATERAL (
        SELECT role_code, start_date
        FROM role_timeline rt2_inner
        WHERE rt2_inner.person_id = rt1.person_id
          AND rt2_inner.start_date > rt1.end_date
        ORDER BY rt2_inner.start_date
        LIMIT 1
    ) rt2 ON true
    WHERE rt2.start_date IS NOT NULL
),

person_timeline_metrics AS (
    SELECT 
        rt.person_id,
        p.first_name || ' ' || p.last_name AS full_name,
        p.party,
        p.gender,
        
        -- Timeline statistics
        MIN(rt.start_date) AS career_first_role,
        MAX(rt.end_date) AS career_last_role,
        DATE_PART('day', MAX(rt.end_date) - MIN(rt.start_date)) / 365.25 AS career_span_years,
        
        -- Role statistics
        COUNT(*) AS total_roles,
        SUM(rt.duration_days) / 365.25 AS total_active_years,
        COUNT(DISTINCT rt.assignment_type) AS assignment_types_served,
        COUNT(DISTINCT rt.institutional_power_center) AS power_centers_served,
        
        -- Concurrent role analysis
        -- Note: These subqueries reference pre-computed CTEs (concurrent_roles_analysis, role_gaps)
        -- which are materialized once. Performance impact is minimal compared to table scans.
        (SELECT COUNT(*) 
         FROM concurrent_roles_analysis cra 
         WHERE cra.person_id = rt.person_id) AS total_concurrent_periods,
        
        (SELECT AVG(overlap_days) 
         FROM concurrent_roles_analysis cra 
         WHERE cra.person_id = rt.person_id) AS avg_concurrent_overlap_days,
        
        (SELECT MAX(combined_power_score) 
         FROM concurrent_roles_analysis cra 
         WHERE cra.person_id = rt.person_id) AS peak_concurrent_power,
        
        -- Gap analysis
        (SELECT COUNT(*) 
         FROM role_gaps rg 
         WHERE rg.person_id = rt.person_id 
           AND rg.gap_classification IN ('MEDIUM_GAP', 'LONG_GAP')) AS significant_gaps,
        
        (SELECT AVG(gap_days) 
         FROM role_gaps rg 
         WHERE rg.person_id = rt.person_id 
           AND rg.gap_days > 0) AS avg_gap_days,
        
        -- Activity metrics (handle edge cases with short careers)
        CASE 
            WHEN DATE_PART('day', MAX(rt.end_date) - MIN(rt.start_date)) < 1 THEN 1.0
            ELSE SUM(rt.duration_days) / DATE_PART('day', MAX(rt.end_date) - MIN(rt.start_date))
        END AS activity_ratio,
        
        -- Current status
        MAX(CASE WHEN rt.is_active THEN 1 ELSE 0 END) AS currently_active
        
    FROM role_timeline rt
    LEFT JOIN person_data p ON p.id = rt.person_id
    GROUP BY rt.person_id, p.first_name, p.last_name, p.party, p.gender
),

workload_intensity AS (
    SELECT 
        rt.person_id,
        DATE_TRUNC('year', rt.start_date) AS year,
        COUNT(*) AS roles_in_year,
        SUM(rt.aggregate_power_score) AS total_power_score_year,
        COUNT(DISTINCT rt.institutional_power_center) AS power_centers_year,
        
        -- Concurrent role indicator for year
        (SELECT COUNT(DISTINCT cra.role2)
         FROM concurrent_roles_analysis cra
         WHERE cra.person_id = rt.person_id
           AND DATE_TRUNC('year', cra.overlap_start) = DATE_TRUNC('year', rt.start_date)) AS concurrent_roles_count
        
    FROM role_timeline rt
    GROUP BY rt.person_id, DATE_TRUNC('year', rt.start_date)
),

timeline_classification AS (
    SELECT 
        ptm.*,
        
        -- Career continuity classification
        CASE 
            WHEN ptm.activity_ratio >= 0.9 THEN 'CONTINUOUS_CAREER'
            WHEN ptm.activity_ratio >= 0.7 THEN 'MOSTLY_ACTIVE'
            WHEN ptm.activity_ratio >= 0.5 THEN 'INTERMITTENT'
            ELSE 'SPORADIC'
        END AS career_continuity,
        
        -- Workload classification
        CASE 
            WHEN ptm.total_concurrent_periods >= 10 THEN 'HEAVY_MULTITASKER'
            WHEN ptm.total_concurrent_periods >= 5 THEN 'MODERATE_MULTITASKER'
            WHEN ptm.total_concurrent_periods >= 1 THEN 'OCCASIONAL_MULTITASKER'
            ELSE 'SEQUENTIAL_ROLES'
        END AS workload_pattern,
        
        -- Career stability
        CASE 
            WHEN ptm.significant_gaps = 0 THEN 'STABLE'
            WHEN ptm.significant_gaps <= 2 THEN 'MODERATE_STABILITY'
            ELSE 'UNSTABLE'
        END AS career_stability
        
    FROM person_timeline_metrics ptm
)

-- Main result set
SELECT 
    rt.person_id,
    rt.intressent_id,
    tc.full_name,
    tc.party,
    tc.gender,
    rt.role_code,
    rt.role_name_en,
    rt.role_category,
    rt.institutional_power_center,
    rt.org_code,
    rt.detail AS role_description,
    rt.assignment_type,
    rt.start_date,
    rt.end_date,
    rt.duration_days / 365.25 AS duration_years,
    rt.is_active,
    rt.aggregate_power_score,
    
    -- Timeline metrics
    tc.career_first_role,
    tc.career_last_role,
    tc.career_span_years,
    tc.total_roles,
    tc.total_active_years,
    tc.activity_ratio,
    tc.career_continuity,
    
    -- Concurrent role analysis
    tc.total_concurrent_periods,
    tc.avg_concurrent_overlap_days / 365.25 AS avg_concurrent_overlap_years,
    tc.peak_concurrent_power,
    tc.workload_pattern,
    
    -- Gap analysis
    tc.significant_gaps,
    tc.avg_gap_days / 365.25 AS avg_gap_years,
    tc.career_stability,
    
    -- Diversity metrics
    tc.assignment_types_served,
    tc.power_centers_served,
    tc.currently_active,
    
    -- Workload intensity for role period
    wi.roles_in_year AS roles_during_same_year,
    wi.concurrent_roles_count AS concurrent_during_role,
    wi.total_power_score_year AS year_total_power

FROM role_timeline rt
LEFT JOIN timeline_classification tc ON tc.person_id = rt.person_id
LEFT JOIN workload_intensity wi 
    ON wi.person_id = rt.person_id 
    AND wi.year = DATE_TRUNC('year', rt.start_date)
ORDER BY rt.person_id, rt.start_date;
