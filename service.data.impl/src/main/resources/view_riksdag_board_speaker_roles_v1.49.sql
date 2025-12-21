DROP VIEW IF EXISTS view_riksdag_board_speaker_roles CASCADE;
CREATE VIEW view_riksdag_board_speaker_roles AS

-- CTE 1: Speaker and board positions from assignment_data
WITH speaker_positions AS (
    SELECT 
        a.intressent_id AS person_id,
        a.assignment_type,
        a.role_code,
        a.org_code,
        a.detail,
        a.from_date,
        a.to_date,
        
        -- Tenure calculation
        CASE 
            WHEN a.to_date IS NULL THEN 
                DATE_PART('day', CURRENT_DATE - a.from_date::date)
            ELSE 
                DATE_PART('day', a.to_date::date - a.from_date::date)
        END AS tenure_days,
        
        -- Speaker hierarchy (lower is more senior)
        CASE 
            WHEN a.role_code = 'Talman' THEN 1
            WHEN a.role_code = 'Förste vice talman' THEN 2
            WHEN a.role_code = 'Andre vice talman' THEN 3
            WHEN a.role_code = 'Tredje vice talman' THEN 4
            ELSE 99
        END AS speaker_rank,
        
        -- Authority level (higher is more authority)
        CASE 
            WHEN a.role_code = 'Talman' THEN 10
            WHEN a.role_code = 'Förste vice talman' THEN 9
            WHEN a.role_code = 'Andre vice talman' THEN 8
            WHEN a.role_code = 'Tredje vice talman' THEN 7
            ELSE 5
        END AS authority_level,
        
        -- Position type classification
        CASE 
            WHEN a.assignment_type = 'talmansuppdrag' THEN 'SPEAKER'
            WHEN a.assignment_type = 'Riksdagsorgan' 
                 AND (a.detail ILIKE '%riksdagsstyrelsen%' OR a.org_code = 'RS') THEN 'BOARD'
            ELSE 'OTHER'
        END AS position_type,
        
        -- Current status
        CASE 
            WHEN a.to_date IS NULL OR a.to_date::date >= CURRENT_DATE THEN true
            ELSE false
        END AS is_current,
        
        EXTRACT(YEAR FROM a.from_date::date) AS appointment_year
        
    FROM assignment_data a
    WHERE 
        (a.assignment_type = 'talmansuppdrag' 
         OR (a.assignment_type = 'Riksdagsorgan' 
             AND (a.detail ILIKE '%riksdagsstyrelsen%' OR a.org_code = 'RS')))
),

-- CTE 2: Join with person data
person_speaker_board AS (
    SELECT 
        sp.*,
        p.first_name,
        p.last_name,
        p.gender,
        p.party AS party_affiliation,
        p.born_year,
        EXTRACT(YEAR FROM sp.from_date::date) - p.born_year AS age_at_appointment
        
    FROM speaker_positions sp
    LEFT JOIN person_data p ON p.id = sp.person_id
),

-- CTE 3: Speaker succession analysis
speaker_succession AS (
    SELECT 
        psb1.role_code,
        psb1.person_id AS outgoing_speaker,
        psb1.first_name || ' ' || psb1.last_name AS outgoing_name,
        psb1.party_affiliation AS outgoing_party,
        psb1.to_date AS succession_date,
        psb2.person_id AS incoming_speaker,
        psb2.first_name || ' ' || psb2.last_name AS incoming_name,
        psb2.party_affiliation AS incoming_party,
        psb2.from_date AS new_speaker_start,
        
        -- Party balance check
        CASE 
            WHEN psb1.party_affiliation = psb2.party_affiliation THEN false
            ELSE true
        END AS party_changed,
        
        -- Transition timing
        CASE 
            WHEN psb1.to_date::date = psb2.from_date::date THEN 'IMMEDIATE'
            WHEN DATE_PART('day', psb2.from_date::date - psb1.to_date::date) <= 7 THEN 'RAPID'
            ELSE 'DELAYED'
        END AS transition_type
        
    FROM person_speaker_board psb1
    INNER JOIN person_speaker_board psb2 
        ON psb1.role_code = psb2.role_code
        AND psb1.position_type = 'SPEAKER'
        AND psb2.position_type = 'SPEAKER'
        AND psb1.to_date IS NOT NULL
        AND psb2.from_date::date > psb1.to_date::date
    WHERE NOT EXISTS (
        SELECT 1 FROM person_speaker_board psb3
        WHERE psb3.role_code = psb1.role_code
            AND psb3.position_type = 'SPEAKER'
            AND psb3.from_date::date > psb1.to_date::date
            AND psb3.from_date::date < psb2.from_date::date
    )
),

-- CTE 4: Party balance analysis by year
party_balance_analysis AS (
    SELECT 
        appointment_year,
        position_type,
        COUNT(DISTINCT party_affiliation) AS parties_represented,
        STRING_AGG(DISTINCT party_affiliation, ', ' ORDER BY party_affiliation) AS party_list,
        COUNT(*) AS total_positions,
        
        -- Gender balance
        COUNT(CASE WHEN gender = 'man' THEN 1 END) AS male_positions,
        COUNT(CASE WHEN gender = 'kvinna' THEN 1 END) AS female_positions
        
    FROM person_speaker_board
    WHERE is_current = true
    GROUP BY appointment_year, position_type
),

-- CTE 5: Governance stability metrics
governance_stability_metrics AS (
    SELECT 
        position_type,
        role_code,
        COUNT(*) AS total_terms,
        COUNT(DISTINCT person_id) AS unique_holders,
        AVG(tenure_days) AS avg_tenure_days,
        ROUND(AVG(tenure_days) / 365.25, 2) AS avg_tenure_years,
        MAX(tenure_days) AS max_tenure_days,
        MIN(tenure_days) AS min_tenure_days,
        
        -- Stability classification
        CASE 
            WHEN AVG(tenure_days) > 1460 THEN 'VERY_STABLE'
            WHEN AVG(tenure_days) > 730 THEN 'STABLE'
            ELSE 'MODERATE'
        END AS stability_rating
        
    FROM person_speaker_board
    GROUP BY position_type, role_code
),

-- CTE 6: Concurrent board and speaker roles
concurrent_board_roles AS (
    SELECT 
        psb1.person_id,
        psb1.first_name || ' ' || psb1.last_name AS full_name,
        COUNT(DISTINCT psb1.role_code) AS concurrent_governance_roles,
        STRING_AGG(DISTINCT psb1.position_type, ' + ' ORDER BY psb1.position_type) AS role_types,
        MIN(psb1.from_date) AS combined_start,
        MAX(COALESCE(psb1.to_date::date, CURRENT_DATE)) AS combined_end
    FROM person_speaker_board psb1
    WHERE EXISTS (
        SELECT 1 FROM person_speaker_board psb2
        WHERE psb2.person_id = psb1.person_id
            AND psb2.role_code != psb1.role_code
            AND (
                (psb1.from_date::date BETWEEN psb2.from_date::date AND COALESCE(psb2.to_date::date, CURRENT_DATE))
                OR (psb2.from_date::date BETWEEN psb1.from_date::date AND COALESCE(psb1.to_date::date, CURRENT_DATE))
            )
    )
    GROUP BY psb1.person_id, psb1.first_name, psb1.last_name
)

-- Main result set
SELECT 
    psb.person_id,
    psb.first_name || ' ' || psb.last_name AS full_name,
    psb.first_name,
    psb.last_name,
    psb.role_code,
    psb.position_type,
    psb.speaker_rank,
    psb.authority_level,
    psb.from_date,
    psb.to_date,
    psb.tenure_days,
    ROUND(psb.tenure_days / 365.25, 2) AS tenure_years,
    psb.is_current,
    psb.party_affiliation,
    psb.gender,
    psb.age_at_appointment,
    psb.appointment_year,
    psb.org_code,
    psb.detail,
    
    -- Governance metrics
    gsm.avg_tenure_years AS position_avg_tenure,
    gsm.stability_rating AS position_stability,
    gsm.unique_holders AS position_total_holders,
    
    -- Party balance
    pba.parties_represented,
    pba.party_list AS current_party_balance,
    
    -- Concurrent roles
    COALESCE(cbr.concurrent_governance_roles, 0) AS concurrent_roles,
    cbr.role_types,
    
    -- Tenure classification
    CASE 
        WHEN psb.tenure_days < 1460 THEN 'TERM'
        WHEN psb.tenure_days < 2920 THEN 'EXTENDED'
        ELSE 'VETERAN'
    END AS tenure_classification

FROM person_speaker_board psb
LEFT JOIN governance_stability_metrics gsm 
    ON gsm.position_type = psb.position_type 
    AND gsm.role_code = psb.role_code
LEFT JOIN party_balance_analysis pba 
    ON pba.appointment_year = psb.appointment_year 
    AND pba.position_type = psb.position_type
LEFT JOIN concurrent_board_roles cbr 
    ON cbr.person_id = psb.person_id
ORDER BY psb.is_current DESC, psb.speaker_rank, psb.from_date DESC;
