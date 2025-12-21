-- view_party_leadership_roles v1.48
-- Purpose: Track parliamentary group leaders (gruppledare), party leaders (partiledare), 
--          and party secretaries (partisekreterare) with party organization analysis
-- Background: Party leadership roles represent highest internal party positions and 
--             significant influence over legislative agenda
-- Data Source: assignment_data table with assignment_type = 'partiuppdrag'
-- Key Metrics: 23 partiledare, 71 gruppledare, 28 partisekreterare positions tracked

DROP VIEW IF EXISTS view_party_leadership_roles CASCADE;

CREATE VIEW view_party_leadership_roles AS
WITH party_leadership AS (
    SELECT 
        a.intressent_id,
        a.role_code,
        a.org_code AS party_code,
        a.detail AS party_name,
        a.from_date,
        a.to_date,
        
        -- Tenure calculation
        CASE 
            WHEN a.to_date IS NULL THEN 
                DATE_PART('day', CURRENT_DATE - a.from_date::date)
            ELSE 
                DATE_PART('day', a.to_date::date - a.from_date::date)
        END AS tenure_days,
        
        -- Leadership classification
        CASE 
            WHEN a.role_code = 'Partiledare' THEN 'PARTY_LEADER'
            WHEN a.role_code = 'Gruppledare' THEN 'GROUP_LEADER'
            WHEN a.role_code = 'Partisekreterare' THEN 'PARTY_SECRETARY'
            ELSE 'OTHER_PARTY_ROLE'
        END AS leadership_type,
        
        -- Authority ranking
        CASE 
            WHEN a.role_code = 'Partiledare' THEN 10
            WHEN a.role_code = 'Gruppledare' THEN 9
            WHEN a.role_code = 'Partisekreterare' THEN 7
            ELSE 5
        END AS authority_level,
        
        -- Current status
        CASE 
            WHEN a.to_date IS NULL OR a.to_date::date >= CURRENT_DATE THEN true
            ELSE false
        END AS is_current,
        
        EXTRACT(YEAR FROM a.from_date::date) AS appointment_year
        
    FROM assignment_data a
    WHERE a.role_code IN ('Partiledare', 'Gruppledare', 'Partisekreterare')
        AND a.assignment_type = 'partiuppdrag'
),

person_party_leadership AS (
    SELECT 
        pl.*,
        p.first_name,
        p.last_name,
        p.gender,
        p.party AS primary_party,
        p.born_year,
        EXTRACT(YEAR FROM pl.from_date::date) - p.born_year AS age_at_appointment
        
    FROM party_leadership pl
    LEFT JOIN person_data p ON p.id = pl.intressent_id
),

party_organizational_metrics AS (
    SELECT 
        party_code,
        party_name,
        leadership_type,
        COUNT(*) AS total_leadership_terms,
        COUNT(DISTINCT intressent_id) AS unique_leaders,
        AVG(tenure_days) AS avg_tenure_days,
        ROUND(AVG(tenure_days) / 365.25, 2) AS avg_tenure_years,
        MAX(tenure_days) AS max_tenure_days,
        MIN(tenure_days) AS min_tenure_days,
        COUNT(CASE WHEN is_current THEN 1 END) AS current_leaders,
        
        -- Leadership stability index
        CASE 
            WHEN AVG(tenure_days) > 1460 THEN 'STABLE'
            WHEN AVG(tenure_days) > 730 THEN 'MODERATE'
            ELSE 'VOLATILE'
        END AS organizational_stability
        
    FROM person_party_leadership
    GROUP BY party_code, party_name, leadership_type
),

concurrent_leadership AS (
    SELECT 
        pl1.intressent_id AS person_id,
        pl1.first_name || ' ' || pl1.last_name AS full_name,
        pl1.party_code,
        COUNT(*) AS concurrent_roles,
        STRING_AGG(pl1.leadership_type, ' + ' ORDER BY pl1.authority_level DESC) AS role_combination,
        MIN(pl1.from_date) AS combined_start,
        MAX(COALESCE(pl1.to_date::date, CURRENT_DATE)) AS combined_end
    FROM person_party_leadership pl1
    WHERE EXISTS (
        SELECT 1 FROM person_party_leadership pl2
        WHERE pl2.intressent_id = pl1.intressent_id
            AND pl2.party_code = pl1.party_code
            AND pl2.role_code != pl1.role_code
            AND (
                (pl1.from_date::date BETWEEN pl2.from_date::date AND COALESCE(pl2.to_date::date, CURRENT_DATE))
                OR (pl2.from_date::date BETWEEN pl1.from_date::date AND COALESCE(pl1.to_date::date, CURRENT_DATE))
            )
    )
    GROUP BY pl1.intressent_id, pl1.first_name, pl1.last_name, pl1.party_code
),

leadership_succession AS (
    SELECT 
        pl1.party_code,
        pl1.party_name,
        pl1.leadership_type,
        pl1.intressent_id AS outgoing_leader,
        pl1.first_name || ' ' || pl1.last_name AS outgoing_name,
        pl1.to_date AS succession_date,
        pl2.intressent_id AS incoming_leader,
        pl2.first_name || ' ' || pl2.last_name AS incoming_name,
        pl2.from_date AS new_leader_start,
        CASE 
            WHEN pl1.to_date::date = pl2.from_date::date THEN 'IMMEDIATE'
            WHEN DATE_PART('day', pl2.from_date::date - pl1.to_date::date) <= 30 THEN 'RAPID'
            ELSE 'DELAYED'
        END AS transition_type
    FROM person_party_leadership pl1
    INNER JOIN person_party_leadership pl2 
        ON pl1.party_code = pl2.party_code
        AND pl1.leadership_type = pl2.leadership_type
        AND pl1.to_date IS NOT NULL
        AND pl2.from_date::date > pl1.to_date::date
    WHERE NOT EXISTS (
        SELECT 1 FROM person_party_leadership pl3
        WHERE pl3.party_code = pl1.party_code
            AND pl3.leadership_type = pl1.leadership_type
            AND pl3.from_date::date > pl1.to_date::date
            AND pl3.from_date::date < pl2.from_date::date
    )
)

-- Main result set
SELECT 
    ppl.intressent_id AS person_id,
    ppl.first_name || ' ' || ppl.last_name AS full_name,
    ppl.role_code,
    ppl.leadership_type,
    ppl.party_code,
    ppl.party_name,
    ppl.from_date,
    ppl.to_date,
    ppl.tenure_days,
    ROUND(ppl.tenure_days / 365.25, 2) AS tenure_years,
    ppl.authority_level,
    ppl.is_current,
    ppl.primary_party,
    ppl.gender,
    ppl.age_at_appointment,
    ppl.appointment_year,
    
    -- Party organizational metrics
    pom.avg_tenure_years AS party_avg_tenure,
    pom.organizational_stability,
    pom.unique_leaders AS party_total_leaders,
    
    -- Concurrent roles indicator
    COALESCE(cl.concurrent_roles, 0) AS concurrent_leadership_roles,
    cl.role_combination,
    
    -- Career classification
    CASE 
        WHEN ppl.tenure_days < 365 THEN 'TRANSITIONAL'
        WHEN ppl.tenure_days < 1460 THEN 'ESTABLISHED'
        ELSE 'VETERAN'
    END AS leadership_tenure_class

FROM person_party_leadership ppl
LEFT JOIN party_organizational_metrics pom 
    ON pom.party_code = ppl.party_code 
    AND pom.leadership_type = ppl.leadership_type
LEFT JOIN concurrent_leadership cl 
    ON cl.person_id = ppl.intressent_id 
    AND cl.party_code = ppl.party_code
ORDER BY ppl.is_current DESC, ppl.authority_level DESC, ppl.appointment_year DESC;
