-- view_riksdagen_politician_role_evolution_v1.56.sql
-- Politician Role Evolution View Definition
--
-- Purpose: Tracks how politician roles change and evolve over time
-- Version: 1.56
-- Category: Analytical View - Role Evolution
--
-- Description:
--   Analyzes role transitions, assignments, and evolution patterns
--   for politicians throughout their careers.
--
-- Usage: View is created/updated during schema deployment
--   SELECT * FROM view_riksdagen_politician_role_evolution LIMIT 10;

DROP VIEW IF EXISTS view_riksdagen_politician_role_evolution CASCADE;

CREATE VIEW view_riksdagen_politician_role_evolution AS
WITH role_assignments AS (
    SELECT 
        p.id AS person_id,
        p.first_name,
        p.last_name,
        p.party,
        ad.role_code,
        -- NOTE: The underlying assignment_data columns (status, assignment_type, org_code)
        -- may be NULL when the source data does not provide a value. For this analytics view
        -- we intentionally normalize such NULLs to the literal 'UNKNOWN' in order to:
        --   * avoid NULLs in composite keys and GROUP BY dimensions, and
        --   * group all "missing / not provided" values into a single technical
        --     category for downstream analysis.
        -- Consumers should treat 'UNKNOWN' here as "value not provided in source",
        -- not as a genuine business domain value.
        COALESCE(ad.status, 'UNKNOWN') AS status,
        COALESCE(ad.assignment_type, 'UNKNOWN') AS assignment_type,
        COALESCE(ad.org_code, 'UNKNOWN') AS org_code,
        ad.from_date,
        ad.to_date,
        -- Classify role tier based on role_code
        CASE 
            WHEN ad.role_code IN ('Statsminister', 'Vice statsminister') OR 
                 ad.role_code LIKE '%minister%' OR 
                 ad.role_code = 'Statsråd' 
                THEN 'MINISTER'
            WHEN ad.role_code = 'Talman' OR 
                 ad.role_code LIKE '%vice talman%' 
                THEN 'SPEAKER'
            WHEN ad.role_code IN ('Partiledare', 'Gruppledare', 'Partisekreterare')
                THEN 'PARTY_LEADER'
            WHEN ad.role_code = 'Ordförande' 
                THEN 'COMMITTEE_CHAIR'
            WHEN ad.role_code = 'Vice ordförande'
                THEN 'COMMITTEE_VICE_CHAIR'
            WHEN ad.role_code = 'Ledamot' AND ad.org_code LIKE 'K%' 
                THEN 'COMMITTEE_MEMBER'
            WHEN ad.role_code = 'Riksdagsledamot'
                THEN 'MP'
            WHEN ad.role_code IN ('Suppleant', 'Ersättare', 'Extra suppleant')
                THEN 'SUBSTITUTE'
            ELSE 'OTHER'
        END AS role_tier,
        -- Assign numeric weight for career progression analysis
        CASE 
            WHEN ad.role_code = 'Statsminister' THEN 1000
            WHEN ad.role_code IN ('Vice statsminister', 'Statsråd') THEN 900
            WHEN ad.role_code LIKE '%minister%' THEN 850
            WHEN ad.role_code = 'Talman' THEN 800
            WHEN ad.role_code LIKE '%vice talman%' THEN 750
            WHEN ad.role_code = 'Partiledare' THEN 700
            WHEN ad.role_code = 'Gruppledare' THEN 650
            WHEN ad.role_code = 'Ordförande' THEN 600
            WHEN ad.role_code = 'Vice ordförande' THEN 550
            WHEN ad.role_code = 'Partisekreterare' THEN 500
            WHEN ad.role_code = 'Riksdagsledamot' THEN 400
            WHEN ad.role_code = 'Ledamot' THEN 350
            WHEN ad.role_code IN ('Suppleant', 'Ersättare') THEN 100
            ELSE 50
        END AS role_weight,
        -- Calculate days in role
        CASE 
            WHEN ad.to_date IS NULL THEN CURRENT_DATE - ad.from_date
            ELSE ad.to_date - ad.from_date
        END AS days_in_role
    FROM person_data p
    JOIN assignment_data ad ON p.id = ad.intressent_id
    WHERE ad.from_date IS NOT NULL
),
role_summary AS (
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        role_code,
        status,
        assignment_type,
        org_code,
        role_tier,
        role_weight,
        MIN(from_date) AS role_start,
        MAX(COALESCE(to_date, CURRENT_DATE)) AS role_end,
        EXTRACT(YEAR FROM MIN(from_date))::INTEGER AS role_start_year,
        EXTRACT(YEAR FROM MAX(COALESCE(to_date, CURRENT_DATE)))::INTEGER AS role_end_year,
        COUNT(*) AS role_instances,
        SUM(days_in_role) AS total_days_in_role,
        -- Track if this is current role
        BOOL_OR(to_date IS NULL) AS is_current_role
    FROM role_assignments
    GROUP BY person_id, first_name, last_name, party, role_code, status, 
             assignment_type, org_code, role_tier, role_weight
),
role_progression AS (
    SELECT 
        *,
        ROW_NUMBER() OVER (PARTITION BY person_id ORDER BY role_start) AS role_sequence,
        MAX(role_weight) OVER (PARTITION BY person_id) AS peak_role_weight,
        MIN(role_start_year) OVER (PARTITION BY person_id) AS career_first_year,
        MAX(role_end_year) OVER (PARTITION BY person_id) AS career_last_year,
        LAG(role_weight) OVER (PARTITION BY person_id ORDER BY role_start) AS prev_role_weight,
        LAG(role_start_year) OVER (PARTITION BY person_id ORDER BY role_start) AS prev_role_start_year,
        LEAD(role_weight) OVER (PARTITION BY person_id ORDER BY role_start) AS next_role_weight
    FROM role_summary
)
SELECT 
    person_id,
    first_name,
    last_name,
    party,
    role_code,
    status,
    assignment_type,
    org_code,
    role_tier,
    role_weight,
    role_start,
    role_end,
    role_start_year,
    role_end_year,
    role_instances,
    total_days_in_role,
    is_current_role,
    role_sequence,
    peak_role_weight,
    career_first_year,
    career_last_year,
    role_end_year - role_start_year + 1 AS years_in_role,
    -- Classify progression pattern
    CASE
        WHEN role_weight = peak_role_weight THEN 'PEAK_ROLE'
        WHEN role_weight > COALESCE(prev_role_weight, 0) AND role_weight > COALESCE(next_role_weight, 0) 
            THEN 'CAREER_PEAK'
        WHEN role_weight > COALESCE(prev_role_weight, 0) THEN 'ASCENDING'
        WHEN role_weight < COALESCE(prev_role_weight, role_weight) THEN 'DESCENDING'
        ELSE 'LATERAL'
    END AS progression_pattern,
    -- Classify career trajectory
    CASE
        WHEN role_weight >= 800 THEN 'TOP_LEADERSHIP'
        WHEN role_weight >= 600 THEN 'SENIOR_LEADERSHIP'
        WHEN role_weight >= 400 THEN 'MID_LEVEL'
        WHEN role_weight >= 200 THEN 'JUNIOR'
        ELSE 'ENTRY_LEVEL'
    END AS career_level,
    -- Calculate advancement velocity (role weight increase per year between consecutive roles)
    CASE 
        WHEN prev_role_weight IS NOT NULL AND prev_role_start_year IS NOT NULL
        THEN ROUND((role_weight - prev_role_weight)::NUMERIC / 
                   NULLIF(role_start_year - prev_role_start_year, 0), 2)
        ELSE NULL
    END AS advancement_velocity
FROM role_progression
ORDER BY person_id, role_start;

COMMENT ON VIEW view_riksdagen_politician_role_evolution IS 
'Role evolution analysis tracking politician career progression through different positions.
Classifies roles into tiers (minister/speaker/party leader/committee chair/member/substitute),
assigns role weights for progression analysis, and identifies career patterns (ascending/descending/lateral).
Calculates advancement velocity and career levels. Used for Predictive Intelligence Framework (Framework 4)
to understand career trajectories and predict future role transitions.';
