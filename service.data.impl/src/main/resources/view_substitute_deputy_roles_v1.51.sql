DROP VIEW IF EXISTS view_substitute_deputy_roles CASCADE;
CREATE VIEW view_substitute_deputy_roles AS

WITH substitute_roles AS (
    SELECT 
        a.intressent_id AS person_id,
        a.hjid AS assignment_id,
        a.role_code,
        a.org_code,
        a.detail AS role_description,
        a.assignment_type,
        a.from_date,
        a.to_date,
        
        -- Tenure calculation
        CASE 
            WHEN a.to_date IS NULL THEN 
                DATE_PART('day', CURRENT_DATE - a.from_date::date)
            ELSE 
                DATE_PART('day', a.to_date::date - a.from_date::date)
        END AS tenure_days,
        
        -- Substitute type classification
        CASE 
            WHEN a.role_code = 'Suppleant' THEN 'COMMITTEE_SUBSTITUTE'
            WHEN a.role_code = 'Extra suppleant' THEN 'COMMITTEE_SUBSTITUTE_EXTRA'
            WHEN a.role_code = 'Personlig suppleant' THEN 'PERSONAL_SUBSTITUTE'
            WHEN a.role_code = 'Ersättare' THEN 'GENERAL_SUBSTITUTE'
            WHEN a.role_code = 'Deputerad' THEN 'DEPUTY'
            WHEN a.role_code = 'Statsrådsersättare' THEN 'MINISTER_DEPUTY'
            ELSE 'OTHER_SUBSTITUTE'
        END AS substitute_type,
        
        -- English role name
        CASE 
            WHEN a.role_code = 'Suppleant' THEN 'Deputy Member'
            WHEN a.role_code = 'Extra suppleant' THEN 'Extra Deputy Member'
            WHEN a.role_code = 'Personlig suppleant' THEN 'Personal Deputy Member'
            WHEN a.role_code = 'Ersättare' THEN 'Substitute'
            WHEN a.role_code = 'Deputerad' THEN 'Deputy'
            WHEN a.role_code = 'Statsrådsersättare' THEN 'Deputy Minister'
            ELSE 'Other Substitute'
        END AS role_name_en,
        
        -- Authority level (higher = more authority)
        CASE 
            WHEN a.role_code = 'Statsrådsersättare' THEN 6
            WHEN a.role_code = 'Deputerad' THEN 5
            WHEN a.role_code = 'Ersättare' THEN 4
            WHEN a.role_code = 'Suppleant' THEN 3
            WHEN a.role_code = 'Extra suppleant' THEN 2
            WHEN a.role_code = 'Personlig suppleant' THEN 2
            ELSE 1
        END AS authority_level,
        
        -- Experience weight (for scoring)
        CASE 
            WHEN a.role_code IN ('Ersättare', 'Statsrådsersättare') THEN 150.0
            WHEN a.role_code IN ('Suppleant', 'Extra suppleant', 'Personlig suppleant') THEN 100.0
            WHEN a.role_code = 'Deputerad' THEN 120.0
            ELSE 50.0
        END AS experience_weight,
        
        -- Current status
        CASE 
            WHEN a.to_date IS NULL OR a.to_date::date >= CURRENT_DATE THEN true
            ELSE false
        END AS is_current,
        
        EXTRACT(YEAR FROM a.from_date::date) AS appointment_year
        
    FROM assignment_data a
    WHERE a.role_code IN (
        'Suppleant', 
        'Extra suppleant', 
        'Personlig suppleant',
        'Ersättare', 
        'Deputerad', 
        'Statsrådsersättare'
    )
),

person_substitutes AS (
    SELECT 
        sr.*,
        p.first_name,
        p.last_name,
        p.gender,
        p.party AS party_affiliation,
        p.born_year,
        EXTRACT(YEAR FROM sr.from_date::date) - p.born_year AS age_at_appointment
        
    FROM substitute_roles sr
    LEFT JOIN person_data p ON p.id = sr.person_id
),

primary_role_mapping AS (
    -- Map substitutes to their primary roles
    SELECT 
        ps.person_id,
        ps.assignment_id,
        ps.org_code,
        ps.assignment_type,
        ps.substitute_type,
        
        -- Find corresponding primary roles
        a_primary.role_code AS primary_role_code,
        a_primary.intressent_id AS primary_role_holder,
        COUNT(*) AS substitutes_for_primary
        
    FROM person_substitutes ps
    LEFT JOIN assignment_data a_primary 
        ON a_primary.org_code = ps.org_code
        AND a_primary.assignment_type = ps.assignment_type
        AND a_primary.role_code NOT IN (
            'Suppleant', 
            'Extra suppleant', 
            'Personlig suppleant',
            'Ersättare', 
            'Deputerad', 
            'Statsrådsersättare'
        )
        AND (
            (ps.from_date::date BETWEEN a_primary.from_date::date 
                AND COALESCE(a_primary.to_date::date, CURRENT_DATE))
            OR (a_primary.from_date::date BETWEEN ps.from_date::date 
                AND COALESCE(ps.to_date::date, CURRENT_DATE))
        )
    GROUP BY ps.person_id, ps.assignment_id, ps.org_code, ps.assignment_type, ps.substitute_type, 
             a_primary.role_code, a_primary.intressent_id
),

career_progression AS (
    -- Track substitutes who later became primary role holders
    SELECT 
        ps.person_id,
        ps.first_name || ' ' || ps.last_name AS full_name,
        
        COUNT(DISTINCT ps.substitute_type) AS substitute_types_held,
        SUM(ps.tenure_days) AS total_substitute_days,
        
        -- Check if later held primary role
        CASE 
            WHEN EXISTS (
                SELECT 1 FROM assignment_data a
                WHERE a.intressent_id = ps.person_id
                    AND a.role_code NOT IN (
                        'Suppleant', 
                        'Extra suppleant', 
                        'Personlig suppleant',
                        'Ersättare', 
                        'Deputerad', 
                        'Statsrådsersättare'
                    )
                    AND a.from_date::date > ps.from_date::date
            ) THEN true
            ELSE false
        END AS progressed_to_primary,
        
        MIN(ps.from_date) AS first_substitute_role,
        MAX(COALESCE(ps.to_date::date, CURRENT_DATE)) AS last_substitute_role
        
    FROM person_substitutes ps
    GROUP BY ps.person_id, ps.first_name, ps.last_name
),

coverage_analysis AS (
    SELECT 
        org_code,
        role_description,
        substitute_type,
        assignment_type,
        COUNT(*) AS substitute_count,
        AVG(tenure_days) AS avg_substitute_tenure,
        
        -- Calculate coverage ratio
        COUNT(*)::decimal / 
            NULLIF(
                (SELECT COUNT(DISTINCT intressent_id) 
                 FROM assignment_data a2 
                 WHERE a2.org_code = ps.org_code
                     AND a2.assignment_type = ps.assignment_type
                     AND a2.role_code NOT IN (
                        'Suppleant', 
                        'Extra suppleant', 
                        'Personlig suppleant',
                        'Ersättare', 
                        'Deputerad', 
                        'Statsrådsersättare'
                    )),
                0
            ) AS substitute_coverage_ratio,
            
        COUNT(CASE WHEN is_current THEN 1 END) AS current_substitutes
        
    FROM person_substitutes ps
    GROUP BY org_code, role_description, substitute_type, assignment_type
),

substitute_portfolio AS (
    SELECT 
        person_id,
        first_name || ' ' || last_name AS full_name,
        COUNT(*) AS total_substitute_roles,
        COUNT(DISTINCT org_code) AS organizations_substituted,
        COUNT(DISTINCT assignment_type) AS assignment_types,
        STRING_AGG(DISTINCT substitute_type, ' + ' ORDER BY substitute_type) AS substitute_types,
        SUM(tenure_days) / 365.25 AS total_substitute_years
    FROM person_substitutes
    GROUP BY person_id, first_name, last_name
)

-- Main result set
SELECT 
    ps.person_id,
    ps.assignment_id,
    ps.first_name || ' ' || ps.last_name AS full_name,
    ps.role_code,
    ps.role_name_en,
    ps.substitute_type,
    ps.org_code,
    ps.role_description,
    ps.assignment_type,
    ps.from_date,
    ps.to_date,
    ps.tenure_days,
    ROUND(ps.tenure_days / 365.25, 2) AS tenure_years,
    ps.authority_level,
    ps.experience_weight,
    ps.is_current,
    ps.party_affiliation,
    ps.gender,
    ps.age_at_appointment,
    ps.appointment_year,
    
    -- Primary role mapping
    prm.primary_role_code,
    prm.substitutes_for_primary,
    
    -- Career progression
    cp.progressed_to_primary,
    cp.substitute_types_held,
    ROUND(cp.total_substitute_days / 365.25, 2) AS total_substitute_years,
    
    -- Coverage metrics
    ROUND(ca.substitute_coverage_ratio, 3) AS substitute_coverage_ratio,
    ROUND(ca.avg_substitute_tenure / 365.25, 2) AS org_avg_substitute_years,
    ca.current_substitutes AS org_current_substitutes,
    
    -- Portfolio metrics
    sp.total_substitute_roles,
    sp.organizations_substituted,
    sp.substitute_types,
    
    -- Utilization classification
    CASE 
        WHEN ps.tenure_days < 365 THEN 'SHORT_TERM'
        WHEN ps.tenure_days < 1460 THEN 'REGULAR'
        ELSE 'LONG_TERM'
    END AS substitute_tenure_class

FROM person_substitutes ps
LEFT JOIN primary_role_mapping prm 
    ON prm.person_id = ps.person_id 
    AND prm.assignment_id = ps.assignment_id
LEFT JOIN career_progression cp 
    ON cp.person_id = ps.person_id
LEFT JOIN coverage_analysis ca 
    ON ca.org_code = ps.org_code 
    AND ca.assignment_type = ps.assignment_type
    AND ca.substitute_type = ps.substitute_type
LEFT JOIN substitute_portfolio sp 
    ON sp.person_id = ps.person_id
ORDER BY ps.is_current DESC, ps.authority_level DESC, ps.from_date DESC;
