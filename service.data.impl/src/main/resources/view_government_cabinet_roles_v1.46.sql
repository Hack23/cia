-- view_government_cabinet_roles v1.46
-- Comprehensive tracking and temporal analysis of cabinet minister positions, 
-- government formations, and ministerial transitions
-- Tracks: Statsminister (10), Vice statsminister (1), 20+ minister titles,
--         Statsråd (259), Statsrådsersättare (295), Departement assignments (500)

DROP VIEW IF EXISTS view_government_cabinet_roles CASCADE;

CREATE VIEW view_government_cabinet_roles AS
WITH minister_assignments AS (
    SELECT 
        a.intressent_id AS person_id,
        a.role_code,
        a.org_code AS ministry_code,
        a.assignment_type,
        a.detail,
        a.from_date,
        a.to_date,
        
        -- Calculate tenure in days
        CASE 
            WHEN a.to_date IS NULL THEN 
                DATE_PART('day', CURRENT_DATE - a.from_date::date)
            ELSE 
                DATE_PART('day', a.to_date::date - a.from_date::date)
        END AS tenure_days,
        
        -- Classify minister authority level (higher = more authority)
        CASE 
            WHEN a.role_code = 'Statsminister' THEN 10
            WHEN a.role_code = 'Vice statsminister' THEN 9
            WHEN a.role_code ILIKE '%minister%' AND a.assignment_type = 'Departement' THEN 8
            WHEN a.role_code = 'Statsråd' THEN 7
            WHEN a.role_code = 'Statsrådsersättare' THEN 6
            ELSE 5
        END AS authority_level,
        
        -- Determine if currently active
        CASE 
            WHEN a.to_date IS NULL OR a.to_date::date >= CURRENT_DATE THEN true
            ELSE false
        END AS is_current,
        
        -- Extract appointment year for government formation grouping
        EXTRACT(YEAR FROM a.from_date::date) AS appointment_year,
        
        -- Map role codes to English names
        CASE 
            WHEN a.role_code = 'Statsminister' THEN 'Prime Minister'
            WHEN a.role_code = 'Vice statsminister' THEN 'Deputy Prime Minister'
            WHEN a.role_code = 'Finansminister' THEN 'Minister of Finance'
            WHEN a.role_code = 'Utrikesminister' THEN 'Minister of Foreign Affairs'
            WHEN a.role_code = 'Justitieminister' THEN 'Minister of Justice'
            WHEN a.role_code = 'Försvarsminister' THEN 'Minister of Defence'
            WHEN a.role_code = 'Inrikesminister' THEN 'Minister of Interior'
            WHEN a.role_code = 'Socialminister' THEN 'Minister of Social Affairs'
            WHEN a.role_code = 'Utbildningsminister' THEN 'Minister of Education'
            WHEN a.role_code = 'Näringsminister' THEN 'Minister of Enterprise'
            WHEN a.role_code = 'Miljöminister' THEN 'Minister of Environment'
            WHEN a.role_code = 'Arbetsmarknadsminister' THEN 'Minister of Employment'
            WHEN a.role_code = 'Kulturminister' THEN 'Minister of Culture'
            WHEN a.role_code = 'Statsråd' THEN 'Minister without Portfolio'
            WHEN a.role_code = 'Statsrådsersättare' THEN 'Deputy Minister'
            WHEN a.role_code ILIKE '%minister%' THEN REPLACE(a.role_code, 'minister', 'Minister')
            ELSE a.role_code
        END AS role_name_en
        
    FROM assignment_data a
    WHERE 
        (a.role_code ILIKE '%minister%' 
         OR a.role_code IN ('Statsråd', 'Statsrådsersättare'))
        AND a.assignment_type IN ('Departement', 'uppdrag')
        AND a.from_date IS NOT NULL
),

person_ministers AS (
    SELECT 
        m.*,
        p.first_name,
        p.last_name,
        p.gender,
        p.party AS party_affiliation,
        p.born_year,
        
        -- Calculate age at appointment
        CASE 
            WHEN p.born_year IS NOT NULL AND m.from_date IS NOT NULL
            THEN EXTRACT(YEAR FROM m.from_date::date) - p.born_year
            ELSE NULL
        END AS age_at_appointment
        
    FROM minister_assignments m
    LEFT JOIN person_data p ON p.id = m.person_id
),

government_formations AS (
    SELECT 
        appointment_year,
        MIN(from_date) AS formation_start,
        MAX(COALESCE(to_date::date, CURRENT_DATE)) AS formation_end,
        COUNT(DISTINCT person_id) AS cabinet_size,
        COUNT(DISTINCT CASE WHEN is_current THEN person_id END) AS current_ministers,
        ROUND(AVG(tenure_days), 0) AS avg_minister_tenure_days,
        STRING_AGG(DISTINCT party_affiliation, ', ' ORDER BY party_affiliation) AS coalition_parties
    FROM person_ministers
    WHERE appointment_year IS NOT NULL
    GROUP BY appointment_year
),

minister_transitions AS (
    SELECT 
        person_id,
        first_name,
        last_name,
        COUNT(*) AS total_appointments,
        COUNT(DISTINCT role_code) AS distinct_roles,
        COUNT(DISTINCT ministry_code) AS portfolios_held,
        MIN(from_date) AS first_appointment,
        MAX(COALESCE(to_date::date, CURRENT_DATE)) AS last_role_end,
        SUM(tenure_days) AS total_minister_days
    FROM person_ministers
    WHERE person_id IS NOT NULL
    GROUP BY person_id, first_name, last_name
)

-- Main result set
SELECT 
    pm.person_id,
    pm.first_name || ' ' || pm.last_name AS full_name,
    pm.first_name,
    pm.last_name,
    pm.role_code,
    pm.role_name_en,
    pm.ministry_code,
    pm.assignment_type,
    pm.from_date,
    pm.to_date,
    pm.tenure_days,
    ROUND(pm.tenure_days / 365.25, 2) AS tenure_years,
    pm.authority_level,
    pm.is_current,
    pm.party_affiliation,
    pm.gender,
    pm.born_year,
    pm.age_at_appointment,
    pm.appointment_year,
    
    -- Government formation context
    gf.formation_start,
    gf.formation_end,
    gf.cabinet_size,
    gf.avg_minister_tenure_days,
    gf.coalition_parties,
    gf.current_ministers,
    
    -- Career progression metrics
    mt.total_appointments,
    mt.distinct_roles,
    mt.portfolios_held,
    mt.first_appointment,
    mt.last_role_end,
    mt.total_minister_days,
    ROUND(mt.total_minister_days / 365.25, 2) AS total_minister_years,
    
    -- Stability indicators
    CASE 
        WHEN pm.tenure_days < 365 THEN 'SHORT_TERM'
        WHEN pm.tenure_days < 1460 THEN 'FULL_TERM'
        ELSE 'LONG_TERM'
    END AS tenure_classification,
    
    -- Portfolio continuity flag
    CASE 
        WHEN mt.portfolios_held > 1 THEN true
        ELSE false
    END AS portfolio_changed

FROM person_ministers pm
LEFT JOIN government_formations gf ON gf.appointment_year = pm.appointment_year
LEFT JOIN minister_transitions mt ON mt.person_id = pm.person_id
WHERE pm.person_id IS NOT NULL
ORDER BY pm.appointment_year DESC, pm.authority_level DESC, pm.from_date DESC;
