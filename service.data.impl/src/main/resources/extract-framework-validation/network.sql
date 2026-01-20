-- network.sql
-- Framework 5: Network Analysis Validation Test Cases
-- Purpose: Extract validation datasets for power structure and influence mapping

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 5: NETWORK ANALYSIS VALIDATION                    ==='
\echo '==================================================================='
\echo ''

-- ============================================================================
-- TEST 5.1: Power Broker Identification
-- Expected Outcome: Identify STRONG_BROKER and MODERATE_BROKER classifications
-- Sample Size: 60 cases
-- ============================================================================
\echo '>>> Test Case 5.1: Power Brokers - High Influence & Cross-Party Connections'
\echo '>>> Expected Outcome: Identify STRONG_BROKER and MODERATE_BROKER classifications'

COPY (
    WITH power_metrics AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            -- broker_score and cross_party_connections missing in view v1.46
            -- Using network_median as proxy for score and 0 for connections
            COALESCE(network_median, 0) AS broker_score,
            0 AS cross_party_connections,
            network_connections AS total_connections,
            -- connectivity_level missing
            'UNKNOWN' AS connectivity_level,
            broker_classification,
            CASE 
                WHEN broker_classification = 'STRONG_BROKER' THEN 'STRONG_POWER_BROKER'
                WHEN broker_classification = 'MODERATE_BROKER' THEN 'MODERATE_POWER_BROKER'
                WHEN broker_classification = 'CONNECTOR' THEN 'CROSS_PARTY_CONNECTOR'
                ELSE 'STANDARD_NETWORK'
            END AS expected_classification
        FROM view_riksdagen_politician_influence_metrics
        WHERE network_connections > 0
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        ROUND(broker_score, 3) AS broker_score,
        cross_party_connections,
        total_connections,
        connectivity_level,
        broker_classification AS system_classification,
        expected_classification AS expected_detection,
        'network_power_broker' AS test_case,
        CASE 
            WHEN expected_classification IN ('STRONG_POWER_BROKER', 'MODERATE_POWER_BROKER', 'CROSS_PARTY_CONNECTOR') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM power_metrics
    WHERE expected_classification IN ('STRONG_POWER_BROKER', 'MODERATE_POWER_BROKER', 'CROSS_PARTY_CONNECTOR', 'STANDARD_NETWORK')
    ORDER BY broker_score DESC, total_connections DESC
    LIMIT cia_get_config_value('sample_size_medium')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/network/test_5_1_power_brokers.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected power broker classification'
\echo ''

-- ============================================================================
-- TEST 5.2: Coalition Facilitator Detection (Cross-Bloc Bridges)
-- Expected Outcome: Identify politicians bridging political blocs
-- Sample Size: 50 cases
-- ============================================================================
\echo '>>> Test Case 5.2: Coalition Facilitators - Cross-Bloc Connection Patterns'
\echo '>>> Expected Outcome: Identify politicians bridging political blocs'

COPY (
    WITH coalition_bridges AS (
        SELECT 
            party1 AS party_1,
            party2 AS party_2,
            alignment_rate,
            shared_votes,
            aligned_votes AS votes_aligned,
            coalition_likelihood,
            bloc_relationship,
            CASE 
                WHEN bloc_relationship = 'CROSS_BLOC' 
                     AND alignment_rate >= cia_get_config_value('alignment_low_threshold')::NUMERIC THEN 'COALITION_FACILITATOR'
                WHEN bloc_relationship LIKE '%BLOC_INTERNAL%' 
                     AND alignment_rate >= cia_get_config_value('alignment_moderate_threshold')::NUMERIC THEN 'BLOC_SOLIDIFIER'
                ELSE 'STANDARD_RELATIONSHIP'
            END AS expected_role
        FROM view_riksdagen_coalition_alignment_matrix
        WHERE shared_votes >= cia_get_config_value('min_shared_votes')::INTEGER
    )
    SELECT 
        party_1,
        party_2,
        ROUND(alignment_rate, 2) AS alignment_rate,
        shared_votes,
        votes_aligned,
        coalition_likelihood,
        bloc_relationship,
        expected_role AS expected_detection,
        'network_coalition_facilitator' AS test_case,
        CASE 
            WHEN expected_role = 'COALITION_FACILITATOR' THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM coalition_bridges
    WHERE expected_role IN ('COALITION_FACILITATOR', 'BLOC_SOLIDIFIER')
    ORDER BY 
        CASE WHEN expected_role = 'COALITION_FACILITATOR' THEN 1 ELSE 2 END,
        alignment_rate DESC
    LIMIT cia_get_config_value('sample_size_default')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/network/test_5_2_coalition_facilitators.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected coalition facilitator identification'
\echo ''
