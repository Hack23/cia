-- decision.sql
-- Framework 6: Decision Intelligence Validation Test Cases
-- Purpose: Extract validation datasets for decision effectiveness and alignment

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 6: DECISION INTELLIGENCE VALIDATION               ==='
\echo '==================================================================='
\echo ''

-- ============================================================================
-- TEST 6.1: Decision Effectiveness Patterns
-- Expected Outcome: Classify HIGH_EFFECTIVENESS, LOW_EFFECTIVENESS patterns
-- Sample Size: 80 cases
-- ============================================================================
\echo '>>> Test Case 6.1: Decision Effectiveness - Approval Rate Patterns'
\echo '>>> Expected Outcome: Classify HIGH/MODERATE/LOW effectiveness patterns'

COPY (
    WITH decision_patterns AS (
        SELECT 
            party,
            committee,
            decision_year,
            decision_month,
            SUM(total_proposals) AS total_proposals,
            SUM(approved_proposals) AS approved_proposals,
            SUM(rejected_proposals) AS rejected_proposals,
            ROUND(AVG(approval_rate), 2) AS avg_approval_rate,
            CASE 
                WHEN AVG(approval_rate) >= cia_get_config_value('performance_high_threshold')::NUMERIC 
                     AND SUM(total_proposals) >= cia_get_config_value('min_votes_per_month')::INTEGER THEN 'HIGH_EFFECTIVENESS'
                WHEN AVG(approval_rate) BETWEEN cia_get_config_value('performance_moderate_threshold')::NUMERIC 
                                            AND cia_get_config_value('performance_high_threshold')::NUMERIC THEN 'MODERATE_EFFECTIVENESS'
                WHEN AVG(approval_rate) < cia_get_config_value('performance_moderate_threshold')::NUMERIC 
                     AND SUM(total_proposals) >= cia_get_config_value('min_proposals_for_analysis')::INTEGER THEN 'LOW_EFFECTIVENESS'
                ELSE 'INSUFFICIENT_DATA'
            END AS expected_pattern
        FROM view_riksdagen_party_decision_flow
        WHERE decision_year >= EXTRACT(YEAR FROM CURRENT_DATE) - 2
        GROUP BY party, committee, decision_year, decision_month
    )
    SELECT 
        party,
        committee,
        decision_year,
        decision_month,
        total_proposals,
        approved_proposals,
        rejected_proposals,
        avg_approval_rate,
        expected_pattern AS expected_classification,
        'decision_effectiveness_pattern' AS test_case,
        CASE 
            WHEN expected_pattern IN ('HIGH_EFFECTIVENESS', 'LOW_EFFECTIVENESS') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM decision_patterns
    WHERE expected_pattern IN ('HIGH_EFFECTIVENESS', 'MODERATE_EFFECTIVENESS', 'LOW_EFFECTIVENESS')
    ORDER BY 
        CASE expected_pattern
            WHEN 'HIGH_EFFECTIVENESS' THEN 1
            WHEN 'MODERATE_EFFECTIVENESS' THEN 2
            WHEN 'LOW_EFFECTIVENESS' THEN 3
        END,
        avg_approval_rate DESC
    LIMIT cia_get_config_value('sample_size_xlarge')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/decision/test_6_1_effectiveness_patterns.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected decision effectiveness classification'
\echo ''

-- ============================================================================
-- TEST 6.2: Coalition Misalignment Detection
-- Expected Outcome: Detect MISALIGNED vs. ALIGNED coalition decision patterns
-- Sample Size: 60 cases
-- ============================================================================
\echo '>>> Test Case 6.2: Coalition Misalignment - Conflicting Decision Patterns'
\echo '>>> Expected Outcome: Detect MISALIGNED vs. ALIGNED coalition patterns'

COPY (
    WITH coalition_decisions_base AS (
        SELECT 
            pdf1.party AS party_1,
            pdf2.party AS party_2,
            pdf1.committee,
            pdf1.decision_year,
            EXTRACT(QUARTER FROM pdf1.decision_month) AS decision_quarter,
            AVG(pdf1.approval_rate) AS party1_approval,
            AVG(pdf2.approval_rate) AS party2_approval,
            ABS(AVG(pdf1.approval_rate) - AVG(pdf2.approval_rate)) AS approval_gap
        FROM view_riksdagen_party_decision_flow pdf1
        INNER JOIN view_riksdagen_party_decision_flow pdf2 
            ON pdf2.committee = pdf1.committee 
            AND pdf2.decision_year = pdf1.decision_year 
            AND EXTRACT(QUARTER FROM pdf2.decision_month) = EXTRACT(QUARTER FROM pdf1.decision_month)
            AND pdf2.party > pdf1.party
        WHERE pdf1.total_proposals >= 3
          AND pdf2.total_proposals >= 3
          AND pdf1.decision_year >= EXTRACT(YEAR FROM CURRENT_DATE) - 2
        GROUP BY pdf1.party, pdf2.party, pdf1.committee, pdf1.decision_year, EXTRACT(QUARTER FROM pdf1.decision_month)
    ),
    coalition_decisions AS (
        SELECT 
            party_1,
            party_2,
            committee,
            decision_year,
            decision_quarter,
            party1_approval,
            party2_approval,
            approval_gap,
            CASE 
                WHEN approval_gap > cia_get_config_value('alignment_gap_misalignment')::NUMERIC THEN 'COALITION_MISALIGNMENT'
                WHEN approval_gap < cia_get_config_value('alignment_gap_aligned')::NUMERIC THEN 'COALITION_ALIGNMENT'
                ELSE 'MODERATE_DIVERGENCE'
            END AS expected_alignment
        FROM coalition_decisions_base
    )
    SELECT 
        party_1,
        party_2,
        committee,
        decision_year,
        decision_quarter,
        ROUND(party1_approval, 2) AS party1_approval_rate,
        ROUND(party2_approval, 2) AS party2_approval_rate,
        ROUND(approval_gap, 2) AS approval_gap,
        expected_alignment AS expected_detection,
        'decision_coalition_misalignment' AS test_case,
        CASE 
            WHEN expected_alignment IN ('COALITION_MISALIGNMENT', 'COALITION_ALIGNMENT') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM coalition_decisions
    WHERE expected_alignment IN ('COALITION_MISALIGNMENT', 'COALITION_ALIGNMENT')
    ORDER BY approval_gap DESC
    LIMIT cia_get_config_value('sample_size_medium')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/decision/test_6_2_coalition_misalignment.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected coalition alignment detection'
\echo ''
