-- =============================================
-- View: view_role_hierarchy_classification
-- Version: 1.52
-- Author: Intelligence Operative
-- Date: 2025-12-21
-- 
-- Purpose: Comprehensive role hierarchy, power structure analysis, and 
--          formal/informal authority classification for all 76 distinct role codes
--
-- Classification Framework:
-- - Formal Authority: Constitutional/legal power (1-10 scale)
-- - Informal Power: Political influence (1-10 scale)
-- - Institutional Power Centers: Executive, Legislative, Party, Administrative
-- - Decision Authority: Primary/Secondary decision maker, voting member, substitute
-- - Prestige Level: Highest to support role classification
--
-- Intelligence Applications:
-- - Experience scoring and politician ranking
-- - Power distribution analysis
-- - Career progression path modeling
-- - Institutional authority mapping
-- - Political influence measurement
--
-- Documentation:
-- - DATABASE_VIEW_INTELLIGENCE_CATALOG.md
-- - service.data.impl/sample-data/distinct_values/DISTINCT_VALUES_ANALYSIS.md
-- =============================================

DROP VIEW IF EXISTS view_role_hierarchy_classification CASCADE;
CREATE VIEW view_role_hierarchy_classification AS

WITH role_taxonomy AS (
    -- Extract distinct role codes from assignment_data and classify them
    SELECT DISTINCT
        a.role_code,
        
        -- Basic categorization based on role patterns
        CASE 
            WHEN a.role_code = 'Statsminister' THEN 'GOVERNMENT'
            WHEN a.role_code = 'Vice statsminister' THEN 'GOVERNMENT'
            WHEN a.role_code ILIKE '%minister%' THEN 'GOVERNMENT'
            WHEN a.role_code = 'Statsråd' THEN 'GOVERNMENT'
            WHEN a.role_code = 'Statsrådsersättare' THEN 'GOVERNMENT_SUBSTITUTE'
            WHEN a.role_code = 'Talman' THEN 'SPEAKER'
            WHEN a.role_code ILIKE '%vice talman' THEN 'SPEAKER'
            WHEN a.role_code = 'Talmansersättare' THEN 'SPEAKER_SUBSTITUTE'
            WHEN a.role_code = 'Ordförande' THEN 'COMMITTEE_LEADERSHIP'
            WHEN a.role_code ILIKE '%vice ordförande' THEN 'COMMITTEE_LEADERSHIP'
            WHEN a.role_code = 'Partiledare' THEN 'PARTY_LEADERSHIP'
            WHEN a.role_code = 'Tillförordnad partiledare' THEN 'PARTY_LEADERSHIP'
            WHEN a.role_code = 'Gruppledare' THEN 'PARTY_LEADERSHIP'
            WHEN a.role_code ILIKE '%vice gruppledare' THEN 'PARTY_LEADERSHIP'
            WHEN a.role_code = 'Partisekreterare' THEN 'PARTY_LEADERSHIP'
            WHEN a.role_code = 'Tillförordnad partisekreterare' THEN 'PARTY_LEADERSHIP'
            WHEN a.role_code = 'Språkrör' THEN 'PARTY_LEADERSHIP'
            WHEN a.role_code = 'Riksdagsledamot' THEN 'PARLIAMENTARY'
            WHEN a.role_code = 'Ledamot' THEN 'PARLIAMENTARY'
            WHEN a.role_code = 'Revisor' THEN 'BOARD'
            WHEN a.role_code = 'Revisorssuppleant' THEN 'BOARD_SUBSTITUTE'
            WHEN a.role_code = 'Kvittningsperson' THEN 'ADMINISTRATIVE'
            WHEN a.role_code IN ('Suppleant', 'Extra suppleant', 'Personlig suppleant') THEN 'SUBSTITUTE'
            WHEN a.role_code IN ('Ersättare', 'Personlig ersättare') THEN 'SUBSTITUTE'
            WHEN a.role_code = 'Deputerad' THEN 'SUBSTITUTE'
            ELSE 'OTHER'
        END AS role_category,
        
        -- English translation of role name
        CASE 
            WHEN a.role_code = 'Statsminister' THEN 'Prime Minister'
            WHEN a.role_code = 'Vice statsminister' THEN 'Deputy Prime Minister'
            WHEN a.role_code = 'Talman' THEN 'Speaker'
            WHEN a.role_code = 'Förste vice talman' THEN 'First Deputy Speaker'
            WHEN a.role_code = 'Andre vice talman' THEN 'Second Deputy Speaker'
            WHEN a.role_code = 'Tredje vice talman' THEN 'Third Deputy Speaker'
            WHEN a.role_code = 'Talmansersättare' THEN 'Speaker Substitute'
            WHEN a.role_code = 'Riksdagsledamot' THEN 'Member of Parliament'
            WHEN a.role_code = 'Ledamot' THEN 'Member'
            WHEN a.role_code = 'Suppleant' THEN 'Substitute'
            WHEN a.role_code = 'Extra suppleant' THEN 'Extra Substitute'
            WHEN a.role_code = 'Personlig suppleant' THEN 'Personal Substitute'
            WHEN a.role_code = 'Ersättare' THEN 'Deputy'
            WHEN a.role_code = 'Personlig ersättare' THEN 'Personal Deputy'
            WHEN a.role_code = 'Deputerad' THEN 'Alternate'
            WHEN a.role_code = 'Ordförande' THEN 'Chair'
            WHEN a.role_code = 'Vice ordförande' THEN 'Vice Chair'
            WHEN a.role_code = 'Förste vice ordförande' THEN 'First Vice Chair'
            WHEN a.role_code = 'Andre vice ordförande' THEN 'Second Vice Chair'
            WHEN a.role_code = 'Tredje vice ordförande' THEN 'Third Vice Chair'
            WHEN a.role_code = 'Partiledare' THEN 'Party Leader'
            WHEN a.role_code = 'Tillförordnad partiledare' THEN 'Acting Party Leader'
            WHEN a.role_code = 'Gruppledare' THEN 'Parliamentary Group Leader'
            WHEN a.role_code = 'Förste vice gruppledare' THEN 'First Deputy Group Leader'
            WHEN a.role_code = 'Andre vice gruppledare' THEN 'Second Deputy Group Leader'
            WHEN a.role_code = 'Partisekreterare' THEN 'Party Secretary'
            WHEN a.role_code = 'Tillförordnad partisekreterare' THEN 'Acting Party Secretary'
            WHEN a.role_code = 'Språkrör' THEN 'Party Spokesperson'
            WHEN a.role_code = 'Statsråd' THEN 'Minister of State'
            WHEN a.role_code = 'Statsrådsersättare' THEN 'Minister Substitute'
            WHEN a.role_code = 'Finansminister' THEN 'Minister of Finance'
            WHEN a.role_code = 'Utrikesminister' THEN 'Minister of Foreign Affairs'
            WHEN a.role_code = 'Försvarsminister' THEN 'Minister of Defense'
            WHEN a.role_code = 'Justitieminister' THEN 'Minister of Justice'
            WHEN a.role_code = 'Socialminister' THEN 'Minister of Social Affairs'
            WHEN a.role_code = 'Utbildningsminister' THEN 'Minister of Education'
            WHEN a.role_code = 'Arbetsmarknadsminister' THEN 'Minister of Labor'
            WHEN a.role_code = 'Näringsminister' THEN 'Minister of Enterprise'
            WHEN a.role_code = 'Kulturminister' THEN 'Minister of Culture'
            WHEN a.role_code = 'Miljöminister' THEN 'Minister of Environment'
            WHEN a.role_code = 'Infrastrukturminister' THEN 'Minister of Infrastructure'
            WHEN a.role_code = 'Landsbygdsminister' THEN 'Minister of Rural Affairs'
            WHEN a.role_code ILIKE '%minister%' THEN 'Minister (' || a.role_code || ')'
            WHEN a.role_code = 'Revisor' THEN 'Auditor'
            WHEN a.role_code = 'Revisorssuppleant' THEN 'Auditor Substitute'
            WHEN a.role_code = 'Kvittningsperson' THEN 'Settlement Person'
            ELSE a.role_code
        END AS role_name_en
        
    FROM assignment_data a
    WHERE a.role_code IS NOT NULL
),

role_hierarchy AS (
    SELECT 
        rt.role_code,
        rt.role_name_en,
        rt.role_category,
        
        -- Formal authority classification (constitutional/legal power) - 1-10 scale
        CASE 
            WHEN rt.role_code = 'Statsminister' THEN 10
            WHEN rt.role_code = 'Vice statsminister' THEN 9
            WHEN rt.role_code IN ('Finansminister', 'Utrikesminister', 'Försvarsminister') THEN 9
            WHEN rt.role_code ILIKE '%minister%' AND rt.role_code NOT ILIKE '%vice%' AND rt.role_code NOT ILIKE 'Statsrådsersättare' THEN 8
            WHEN rt.role_code = 'Talman' THEN 9
            WHEN rt.role_code = 'Förste vice talman' THEN 8
            WHEN rt.role_code IN ('Andre vice talman', 'Tredje vice talman') THEN 7
            WHEN rt.role_code = 'Partiledare' THEN 8
            WHEN rt.role_code = 'Tillförordnad partiledare' THEN 7
            WHEN rt.role_code = 'Gruppledare' THEN 7
            WHEN rt.role_code ILIKE '%vice gruppledare' THEN 6
            WHEN rt.role_code = 'Ordförande' THEN 6
            WHEN rt.role_code = 'Förste vice ordförande' THEN 5
            WHEN rt.role_code IN ('Vice ordförande', 'Andre vice ordförande', 'Tredje vice ordförande') THEN 5
            WHEN rt.role_code = 'Riksdagsledamot' THEN 4
            WHEN rt.role_code = 'Ledamot' THEN 4
            WHEN rt.role_code = 'Statsråd' THEN 7
            WHEN rt.role_code = 'Partisekreterare' THEN 5
            WHEN rt.role_code = 'Tillförordnad partisekreterare' THEN 5
            WHEN rt.role_code = 'Språkrör' THEN 6
            WHEN rt.role_code = 'Revisor' THEN 4
            WHEN rt.role_code = 'Kvittningsperson' THEN 3
            WHEN rt.role_code IN ('Deputerad', 'Ersättare', 'Personlig ersättare') THEN 3
            WHEN rt.role_code IN ('Suppleant', 'Extra suppleant', 'Personlig suppleant') THEN 2
            WHEN rt.role_code IN ('Statsrådsersättare', 'Talmansersättare', 'Revisorssuppleant') THEN 2
            ELSE 1
        END AS formal_authority_score,
        
        -- Informal power classification (political influence) - 1-10 scale
        CASE 
            WHEN rt.role_code = 'Statsminister' THEN 10
            WHEN rt.role_code = 'Partiledare' THEN 9
            WHEN rt.role_code = 'Tillförordnad partiledare' THEN 8
            WHEN rt.role_code = 'Vice statsminister' THEN 9
            WHEN rt.role_code IN ('Finansminister', 'Utrikesminister') THEN 8
            WHEN rt.role_code ILIKE '%minister%' AND rt.role_code NOT ILIKE 'Statsrådsersättare' THEN 7
            WHEN rt.role_code = 'Talman' THEN 7
            WHEN rt.role_code = 'Gruppledare' THEN 7
            WHEN rt.role_code = 'Partisekreterare' THEN 6
            WHEN rt.role_code = 'Tillförordnad partisekreterare' THEN 6
            WHEN rt.role_code = 'Språkrör' THEN 6
            WHEN rt.role_code = 'Ordförande' THEN 6
            WHEN rt.role_code ILIKE '%vice talman' THEN 5
            WHEN rt.role_code ILIKE '%vice ordförande' THEN 5
            WHEN rt.role_code ILIKE '%vice gruppledare' THEN 5
            WHEN rt.role_code = 'Riksdagsledamot' THEN 3
            WHEN rt.role_code = 'Ledamot' THEN 3
            WHEN rt.role_code = 'Statsråd' THEN 6
            WHEN rt.role_code = 'Revisor' THEN 3
            WHEN rt.role_code = 'Kvittningsperson' THEN 2
            ELSE 2
        END AS informal_power_score,
        
        -- Institutional power center
        CASE 
            WHEN rt.role_category = 'GOVERNMENT' THEN 'EXECUTIVE'
            WHEN rt.role_category = 'GOVERNMENT_SUBSTITUTE' THEN 'EXECUTIVE_BACKUP'
            WHEN rt.role_category = 'SPEAKER' THEN 'LEGISLATIVE_LEADERSHIP'
            WHEN rt.role_category = 'SPEAKER_SUBSTITUTE' THEN 'LEGISLATIVE_LEADERSHIP_BACKUP'
            WHEN rt.role_category = 'COMMITTEE_LEADERSHIP' THEN 'LEGISLATIVE_COMMITTEE'
            WHEN rt.role_category = 'PARTY_LEADERSHIP' THEN 'PARTY_ORGANIZATION'
            WHEN rt.role_category = 'BOARD' THEN 'PARLIAMENTARY_ADMINISTRATION'
            WHEN rt.role_category = 'BOARD_SUBSTITUTE' THEN 'PARLIAMENTARY_ADMINISTRATION_BACKUP'
            WHEN rt.role_category = 'PARLIAMENTARY' THEN 'LEGISLATIVE_MEMBERSHIP'
            WHEN rt.role_category = 'SUBSTITUTE' THEN 'BACKUP_CAPACITY'
            WHEN rt.role_category = 'ADMINISTRATIVE' THEN 'ADMINISTRATIVE_SUPPORT'
            ELSE 'OTHER'
        END AS institutional_power_center,
        
        -- Decision-making authority
        CASE 
            WHEN rt.role_code IN ('Statsminister', 'Talman', 'Ordförande', 'Partiledare', 'Tillförordnad partiledare') THEN 'PRIMARY_DECISION_MAKER'
            WHEN rt.role_code ILIKE '%vice talman' OR rt.role_code ILIKE '%vice ordförande' OR rt.role_code ILIKE '%vice gruppledare' THEN 'SECONDARY_DECISION_MAKER'
            WHEN rt.role_code ILIKE '%minister%' AND rt.role_code NOT ILIKE 'Statsrådsersättare' THEN 'PRIMARY_DECISION_MAKER'
            WHEN rt.role_code = 'Vice statsminister' THEN 'SECONDARY_DECISION_MAKER'
            WHEN rt.role_code = 'Gruppledare' THEN 'PRIMARY_DECISION_MAKER'
            WHEN rt.role_code IN ('Partisekreterare', 'Tillförordnad partisekreterare', 'Språkrör') THEN 'SECONDARY_DECISION_MAKER'
            WHEN rt.role_code IN ('Riksdagsledamot', 'Ledamot') THEN 'VOTING_MEMBER'
            WHEN rt.role_code = 'Statsråd' THEN 'VOTING_MEMBER'
            WHEN rt.role_code = 'Revisor' THEN 'OVERSIGHT_ROLE'
            WHEN rt.role_code IN ('Suppleant', 'Extra suppleant', 'Personlig suppleant', 'Ersättare', 'Personlig ersättare', 'Deputerad') THEN 'SUBSTITUTE_CAPACITY'
            WHEN rt.role_code IN ('Statsrådsersättare', 'Talmansersättare', 'Revisorssuppleant') THEN 'SUBSTITUTE_CAPACITY'
            ELSE 'ADMINISTRATIVE'
        END AS decision_authority_type,
        
        -- Prestige classification
        CASE 
            WHEN rt.role_code IN ('Statsminister', 'Talman', 'Partiledare') THEN 'HIGHEST_PRESTIGE'
            WHEN rt.role_code IN ('Vice statsminister', 'Finansminister', 'Utrikesminister', 'Försvarsminister', 'Tillförordnad partiledare') THEN 'HIGHEST_PRESTIGE'
            WHEN rt.role_code ILIKE '%minister%' AND rt.role_code NOT ILIKE 'Statsrådsersättare' THEN 'HIGH_PRESTIGE'
            WHEN rt.role_code IN ('Gruppledare', 'Ordförande', 'Förste vice talman') THEN 'HIGH_PRESTIGE'
            WHEN rt.role_code ILIKE '%vice talman' OR rt.role_code ILIKE '%vice ordförande' OR rt.role_code ILIKE '%vice gruppledare' THEN 'MEDIUM_PRESTIGE'
            WHEN rt.role_code IN ('Partisekreterare', 'Tillförordnad partisekreterare', 'Språkrör', 'Statsråd') THEN 'MEDIUM_PRESTIGE'
            WHEN rt.role_code IN ('Riksdagsledamot', 'Ledamot') THEN 'STANDARD_PRESTIGE'
            WHEN rt.role_code = 'Revisor' THEN 'STANDARD_PRESTIGE'
            ELSE 'SUPPORT_ROLE'
        END AS prestige_level,
        
        -- Experience weight (aligned with view_riksdagen_politician_experience_summary)
        CASE
            WHEN rt.role_code = 'Statsminister' THEN 1000.0
            WHEN rt.role_code = 'Partiledare' THEN 950.0
            WHEN rt.role_code = 'Tillförordnad partiledare' THEN 925.0
            WHEN rt.role_code = 'Vice statsminister' THEN 900.0
            WHEN rt.role_code ILIKE '%minister%' OR rt.role_code = 'Statsråd' THEN 850.0
            WHEN rt.role_code = 'Talman' THEN 800.0
            WHEN rt.role_code IN ('Förste vice talman', 'Andre vice talman', 'Tredje vice talman') THEN 750.0
            WHEN rt.role_code = 'Ordförande' THEN 700.0
            WHEN rt.role_code IN ('Gruppledare', 'Partisekreterare', 'Tillförordnad partisekreterare') THEN 650.0
            WHEN rt.role_code IN ('Vice ordförande', 'Förste vice ordförande', 'Andre vice ordförande', 'Tredje vice ordförande') THEN 600.0
            WHEN rt.role_code IN ('Förste vice gruppledare', 'Andre vice gruppledare') THEN 550.0
            WHEN rt.role_code = 'Språkrör' THEN 550.0
            WHEN rt.role_code = 'Riksdagsledamot' THEN 500.0
            WHEN rt.role_code = 'Ledamot' THEN 400.0
            WHEN rt.role_code = 'Revisor' THEN 350.0
            WHEN rt.role_code IN ('Suppleant', 'Extra suppleant', 'Personlig suppleant') THEN 200.0
            WHEN rt.role_code IN ('Ersättare', 'Personlig ersättare', 'Deputerad') THEN 250.0
            WHEN rt.role_code IN ('Statsrådsersättare', 'Talmansersättare', 'Revisorssuppleant') THEN 200.0
            WHEN rt.role_code = 'Kvittningsperson' THEN 150.0
            ELSE 100.0
        END AS experience_weight,
        
        -- Authority level (1-10 legacy scale)
        CASE 
            WHEN rt.role_code = 'Statsminister' THEN 10
            WHEN rt.role_code = 'Vice statsminister' THEN 9
            WHEN rt.role_code ILIKE '%minister%' OR rt.role_code = 'Partiledare' OR rt.role_code = 'Talman' THEN 8
            WHEN rt.role_code = 'Gruppledare' OR rt.role_code ILIKE '%vice talman' THEN 7
            WHEN rt.role_code = 'Ordförande' OR rt.role_code = 'Partisekreterare' OR rt.role_code = 'Språkrör' THEN 6
            WHEN rt.role_code ILIKE '%vice ordförande' OR rt.role_code ILIKE '%vice gruppledare' THEN 5
            WHEN rt.role_code = 'Riksdagsledamot' THEN 4
            WHEN rt.role_code = 'Ledamot' OR rt.role_code = 'Statsråd' OR rt.role_code = 'Revisor' THEN 3
            WHEN rt.role_code IN ('Ersättare', 'Deputerad', 'Personlig ersättare') THEN 2
            ELSE 1
        END AS authority_level
        
    FROM role_taxonomy rt
),

aggregate_power_metrics AS (
    SELECT 
        rh.*,
        
        -- Combined power score (average of formal + informal)
        ROUND((rh.formal_authority_score + rh.informal_power_score) / 2.0, 2) AS aggregate_power_score,
        
        -- Power type classification
        CASE 
            WHEN rh.formal_authority_score > rh.informal_power_score THEN 'FORMAL_AUTHORITY_DOMINANT'
            WHEN rh.informal_power_score > rh.formal_authority_score THEN 'INFORMAL_POWER_DOMINANT'
            ELSE 'BALANCED_POWER'
        END AS power_type,
        
        -- Strategic value (for career progression)
        CASE 
            WHEN rh.formal_authority_score + rh.informal_power_score >= 16 THEN 'STRATEGIC_APEX'
            WHEN rh.formal_authority_score + rh.informal_power_score >= 12 THEN 'STRATEGIC_HIGH'
            WHEN rh.formal_authority_score + rh.informal_power_score >= 8 THEN 'STRATEGIC_MEDIUM'
            ELSE 'STRATEGIC_LOW'
        END AS strategic_value
        
    FROM role_hierarchy rh
),

role_distribution_analysis AS (
    SELECT 
        apm.institutional_power_center,
        apm.decision_authority_type,
        COUNT(*) AS roles_in_category,
        ROUND(AVG(apm.aggregate_power_score), 2) AS avg_power_score,
        ROUND(SUM(apm.experience_weight), 2) AS total_experience_weight
    FROM aggregate_power_metrics apm
    GROUP BY apm.institutional_power_center, apm.decision_authority_type
),

current_power_distribution AS (
    SELECT 
        apm.institutional_power_center,
        apm.power_type,
        COUNT(DISTINCT a.intressent_id) AS politicians_holding_role,
        ROUND(AVG(apm.aggregate_power_score), 2) AS avg_power_score,
        COUNT(*) AS total_assignments
    FROM aggregate_power_metrics apm
    LEFT JOIN assignment_data a ON a.role_code = apm.role_code
        AND (a.to_date IS NULL OR a.to_date >= CURRENT_DATE)
    GROUP BY apm.institutional_power_center, apm.power_type
)

-- Main result set
SELECT 
    apm.role_code,
    apm.role_name_en,
    apm.role_category,
    apm.institutional_power_center,
    apm.decision_authority_type,
    apm.prestige_level,
    
    -- Authority scores
    apm.formal_authority_score,
    apm.informal_power_score,
    apm.aggregate_power_score,
    apm.power_type,
    apm.strategic_value,
    
    -- Experience metrics
    apm.authority_level,
    apm.experience_weight,
    
    -- Distribution metrics
    rda.roles_in_category AS similar_roles_count,
    rda.avg_power_score AS category_avg_power,
    
    -- Current holders
    cpd.politicians_holding_role AS current_holders,
    cpd.total_assignments AS current_assignments,
    
    -- Hierarchical ranking
    DENSE_RANK() OVER (ORDER BY apm.aggregate_power_score DESC, apm.role_code) AS power_rank,
    DENSE_RANK() OVER (PARTITION BY apm.institutional_power_center ORDER BY apm.aggregate_power_score DESC, apm.role_code) AS power_rank_within_institution

FROM aggregate_power_metrics apm
LEFT JOIN role_distribution_analysis rda 
    ON rda.institutional_power_center = apm.institutional_power_center
    AND rda.decision_authority_type = apm.decision_authority_type
LEFT JOIN current_power_distribution cpd 
    ON cpd.institutional_power_center = apm.institutional_power_center
    AND cpd.power_type = apm.power_type
ORDER BY apm.aggregate_power_score DESC, apm.role_code;
