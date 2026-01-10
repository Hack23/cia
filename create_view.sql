CREATE VIEW view_risk_score_evolution AS
WITH politician_votes_with_rebel AS (
    SELECT
        vd.embedded_id_intressent_id,
        vd.embedded_id_ballot_id,
        vd.vote,
        vd.vote_date,
        vd.party,
        CASE
            WHEN vd.vote != vd.party AND vd.vote IN ('Ja', 'Nej') THEN true
            ELSE false
        END AS is_rebel
    FROM vote_data vd
    WHERE vd.vote_date >= CURRENT_DATE - INTERVAL '3 years'
),
politician_document_data AS (
    SELECT
        dpr.person_reference_id,
        dd.made_public_date,
        dd.id
    FROM document_status_container dsc
    LEFT JOIN document_data dd 
        ON dsc.document_document_status_con_0 = dd.id
    LEFT JOIN document_person_reference_co_0 dprc 
        ON dsc.hjid = dprc.hjid
    LEFT JOIN document_person_reference_da_0 dpr 
        ON dpr.document_person_reference_li_1 = dprc.hjid
    WHERE dd.made_public_date >= CURRENT_DATE - INTERVAL '3 years'
        AND dpr.person_reference_id IS NOT NULL
),
monthly_risk_base AS (
    SELECT
        p.id AS person_id,
        p.first_name,
        p.last_name,
        p.party,
        DATE_TRUNC('month', pvr.vote_date) AS assessment_period,
        
        ROUND(COUNT(*) FILTER (WHERE pvr.vote = 'Frånvarande')::NUMERIC / 
              NULLIF(COUNT(*), 0)::NUMERIC * 100, 2) AS absence_rate,
        ROUND(COUNT(*) FILTER (WHERE pvr.is_rebel = true)::NUMERIC / 
              NULLIF(COUNT(*) FILTER (WHERE pvr.vote IN ('Ja', 'Nej')), 0)::NUMERIC * 100, 2) AS rebel_rate,
        COUNT(*) AS ballot_count,
        COUNT(DISTINCT vpd.id) AS document_count
        
    FROM person_data p
    LEFT JOIN politician_votes_with_rebel pvr 
        ON pvr.embedded_id_intressent_id = p.id
    LEFT JOIN politician_document_data vpd 
        ON vpd.person_reference_id = p.id
        AND vpd.made_public_date >= CURRENT_DATE - INTERVAL '3 years'
        AND DATE_TRUNC('month', vpd.made_public_date) = DATE_TRUNC('month', pvr.vote_date)
    
    WHERE p.status IN ('Tjänstgörande riksdagsledamot', 'Tjänstgörande ersättare', 'Tillgänglig ersättare')
    
    GROUP BY p.id, p.first_name, p.last_name, p.party, DATE_TRUNC('month', pvr.vote_date)
),
monthly_violations AS (
    SELECT
        reference_id AS person_id,
        DATE_TRUNC('month', detected_date) AS assessment_period,
        COUNT(*) AS violation_count,
        COUNT(DISTINCT rule_group) AS violation_categories,
        STRING_AGG(DISTINCT rule_group, ', ' ORDER BY rule_group) AS violation_types
    FROM rule_violation
    WHERE resource_type = 'POLITICIAN'
        AND status IN ('MINOR', 'MAJOR', 'CRITICAL')
        AND detected_date >= CURRENT_DATE - INTERVAL '3 years'
    GROUP BY reference_id, DATE_TRUNC('month', detected_date)
),
risk_calculations AS (
    SELECT
        mrb.person_id,
        mrb.first_name,
        mrb.last_name,
        mrb.party,
        mrb.assessment_period,
        mrb.absence_rate,
        mrb.rebel_rate,
        mrb.ballot_count,
        mrb.document_count,
        
        COALESCE(mv.violation_count, 0) AS violation_count,
        COALESCE(mv.violation_categories, 0) AS violation_categories,
        COALESCE(mv.violation_types, '') AS violation_types,
        
        ROUND(
            LEAST(COALESCE(mv.violation_count, 0) * 2, 40) +
            (COALESCE(mrb.absence_rate, 0) * 30 / 100.0) +
            (COALESCE(mrb.rebel_rate, 0) * 20 / 100.0) +
            (CASE WHEN mrb.document_count < 5 THEN 10 ELSE 0 END),
        2) AS calculated_risk_score,
        
        LAG(ROUND(
            LEAST(COALESCE(mv.violation_count, 0) * 2, 40) +
            (COALESCE(mrb.absence_rate, 0) * 30 / 100.0) +
            (COALESCE(mrb.rebel_rate, 0) * 20 / 100.0) +
            (CASE WHEN mrb.document_count < 5 THEN 10 ELSE 0 END),
        2), 1) OVER (PARTITION BY mrb.person_id ORDER BY mrb.assessment_period) AS prev_risk_score
        
    FROM monthly_risk_base mrb
    LEFT JOIN monthly_violations mv 
        ON mrb.person_id = mv.person_id 
        AND mrb.assessment_period = mv.assessment_period
    
    WHERE mrb.ballot_count >= 5
        AND mrb.assessment_period IS NOT NULL
)
SELECT
    person_id,
    first_name,
    last_name,
    party,
    assessment_period,
    (assessment_period + INTERVAL '1 month' - INTERVAL '1 day')::DATE AS assessment_period_end,
    absence_rate,
    rebel_rate,
    ballot_count,
    document_count,
    violation_count,
    violation_categories,
    violation_types,
    calculated_risk_score AS risk_score,
    prev_risk_score,
    ROUND(calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score), 2) AS risk_score_change,
    
    CASE
        WHEN (calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) >= 10 THEN 'SIGNIFICANT_INCREASE'
        WHEN (calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) >= 5 THEN 'MODERATE_INCREASE'
        WHEN (calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) > 0 THEN 'SLIGHT_INCREASE'
        WHEN (calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) <= -10 THEN 'SIGNIFICANT_DECREASE'
        WHEN (calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) <= -5 THEN 'MODERATE_DECREASE'
        WHEN (calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) < 0 THEN 'SLIGHT_DECREASE'
        ELSE 'STABLE'
    END AS risk_trend,
    
    CASE
        WHEN calculated_risk_score >= 65 THEN 'CRITICAL'
        WHEN calculated_risk_score >= 45 THEN 'HIGH'
        WHEN calculated_risk_score >= 25 THEN 'MODERATE'
        WHEN calculated_risk_score >= 15 THEN 'LOW'
        ELSE 'MINIMAL'
    END AS risk_severity,
    
    CASE
        WHEN COALESCE(prev_risk_score, 0) > 0 THEN
            CASE
                WHEN prev_risk_score < 25 AND calculated_risk_score >= 25 THEN 'ESCALATION_TO_MODERATE'
                WHEN prev_risk_score < 45 AND calculated_risk_score >= 45 THEN 'ESCALATION_TO_HIGH'
                WHEN prev_risk_score < 65 AND calculated_risk_score >= 65 THEN 'ESCALATION_TO_CRITICAL'
                WHEN prev_risk_score >= 65 AND calculated_risk_score < 65 THEN 'DEESCALATION_FROM_CRITICAL'
                WHEN prev_risk_score >= 45 AND calculated_risk_score < 45 THEN 'DEESCALATION_FROM_HIGH'
                WHEN prev_risk_score >= 25 AND calculated_risk_score < 25 THEN 'DEESCALATION_FROM_MODERATE'
                ELSE 'NO_SEVERITY_TRANSITION'
            END
        ELSE 'INITIAL_ASSESSMENT'
    END AS severity_transition,
    
    CASE
        WHEN calculated_risk_score >= 65 THEN 'CRITICAL: Immediate attention required'
        WHEN calculated_risk_score >= 45 AND (calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) >= 5 
            THEN 'HIGH RISK: Escalating trend detected'
        WHEN calculated_risk_score >= 45 THEN 'HIGH RISK: Monitor closely'
        WHEN calculated_risk_score >= 25 AND (calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) >= 10 
            THEN 'MODERATE RISK: Rapid escalation warning'
        WHEN calculated_risk_score >= 25 THEN 'MODERATE RISK: Standard monitoring'
        WHEN prev_risk_score >= 45 AND calculated_risk_score < 25 THEN 'IMPROVING: Effective risk mitigation'
        ELSE 'LOW RISK: Normal operations'
    END AS risk_assessment

FROM risk_calculations
ORDER BY person_id, assessment_period DESC;
