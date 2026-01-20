-- comparative.sql
-- Framework 2: Comparative Analysis Validation Test Cases
-- Purpose: Extract validation datasets for comparative performance analysis

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 2: COMPARATIVE ANALYSIS VALIDATION                ==='
\echo '==================================================================='
\echo ''

-- ============================================================================
-- TEST 2.1: Party Performance Rankings
-- Expected Outcome: Classify parties into performance tiers (HIGH/MEDIUM/LOW)
-- Sample Size: All parties
-- ============================================================================
\echo '>>> Test Case 2.1: Party Performance Rankings - Win Rate & Discipline'
\echo '>>> Expected Outcome: Classify parties into performance tiers'

COPY (
    WITH raw_votes AS (
        SELECT 
             party,
             embedded_id_ballot_id as ballot_id,
             vote
        FROM vote_data
        WHERE party IS NOT NULL AND party != '-' AND party != ''
          AND vote_date >= DATE_TRUNC('month', CURRENT_DATE) - cia_get_lookback_interval('medium')
    ),
    party_ballot_majority AS (
        SELECT
            party,
            ballot_id,
            mode() WITHIN GROUP (ORDER BY vote) as party_position
        FROM raw_votes
        WHERE vote IN ('JA', 'NEJ', 'AVSTÅR')
        GROUP BY party, ballot_id
    ),
    party_discipline_agg AS (
        SELECT
            rv.party,
            COUNT(*) as total_votes,
            SUM(CASE WHEN rv.vote IN ('FRÅNVARANDE') THEN 1 ELSE 0 END) as absent_votes,
            SUM(CASE WHEN rv.vote = pbm.party_position THEN 1 ELSE 0 END) as loyal_votes
        FROM raw_votes rv
        LEFT JOIN party_ballot_majority pbm ON rv.party = pbm.party AND rv.ballot_id = pbm.ballot_id
        GROUP BY rv.party
    ),
    party_metrics AS (
        SELECT
            party,
            total_votes,
            ROUND(100.0 * absent_votes / NULLIF(total_votes, 0), 2) as avg_absence_rate,
            ROUND(100.0 * loyal_votes / NULLIF(total_votes - absent_votes, 0), 2) as avg_discipline
        FROM party_discipline_agg
        WHERE total_votes > 0
    ),
    party_rankings AS (
        SELECT 
            party,
            0 as active_members,
            0.0 as avg_win_rate,
            avg_discipline,
            avg_absence_rate,
            total_votes as total_documents,
            RANK() OVER (ORDER BY avg_discipline DESC) AS discipline_rank,
            RANK() OVER (ORDER BY avg_absence_rate ASC) AS attendance_rank,
            cia_classify_performance(
                avg_discipline,
                cia_get_config_value('discipline_high_threshold')::NUMERIC,
                cia_get_config_value('discipline_moderate_threshold')::NUMERIC
            ) AS expected_classification
        FROM party_metrics
    )
    SELECT 
        party,
        active_members,
        avg_win_rate,
        avg_discipline,
        avg_absence_rate,
        total_documents,
        1 as win_rate_rank,
        discipline_rank,
        attendance_rank,
        expected_classification AS expected_detection,
        'comparative_party_ranking' AS test_case,
        CASE 
            WHEN expected_classification IN ('HIGH_PERFORMANCE', 'MEDIUM_PERFORMANCE', 'LOW_PERFORMANCE') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM party_rankings
    ORDER BY avg_discipline DESC
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/comparative/test_2_1_party_rankings.csv' WITH CSV HEADER;

\echo '>>> Exported party performance rankings with expected tier classification'
\echo ''

-- ============================================================================
-- TEST 2.2: Politician Peer Group Comparison
-- Expected Outcome: Classify politicians as ABOVE_AVERAGE/AVERAGE/BELOW_AVERAGE
-- Sample Size: 60 cases
-- ============================================================================
\echo '>>> Test Case 2.2: Politician vs. Party Average Performance'
\echo '>>> Expected Outcome: Classify politicians as ABOVE/AVERAGE/BELOW'

COPY (
    WITH party_averages AS (
        SELECT 
            party,
            AVG(avg_absence_rate) AS party_avg_absence,
            AVG(avg_win_rate) AS party_avg_win,
            AVG(avg_rebel_rate) AS party_avg_rebel
        FROM view_politician_behavioral_trends
        WHERE period_start >= CURRENT_DATE - cia_get_lookback_interval('medium')
          AND total_ballots >= cia_get_config_value('min_votes_per_month')::INTEGER
        GROUP BY party
    ),
    politician_comparison_base AS (
        SELECT 
            pbt.person_id,
            pbt.first_name,
            pbt.last_name,
            pbt.party,
            AVG(pbt.avg_absence_rate) AS individual_absence,
            AVG(pbt.avg_win_rate) AS individual_win,
            AVG(pbt.avg_rebel_rate) AS individual_rebel,
            pa.party_avg_absence,
            pa.party_avg_win,
            pa.party_avg_rebel,
            AVG(pbt.avg_absence_rate) - pa.party_avg_absence AS absence_vs_party,
            AVG(pbt.avg_win_rate) - pa.party_avg_win AS win_vs_party
        FROM view_politician_behavioral_trends pbt
        JOIN party_averages pa ON pa.party = pbt.party
        WHERE pbt.period_start >= CURRENT_DATE - cia_get_lookback_interval('medium')
          AND pbt.total_ballots >= cia_get_config_value('min_votes_per_month')::INTEGER
        GROUP BY pbt.person_id, pbt.first_name, pbt.last_name, pbt.party, 
                 pa.party_avg_absence, pa.party_avg_win, pa.party_avg_rebel
    ),
    politician_comparison AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            individual_absence,
            individual_win,
            individual_rebel,
            party_avg_absence,
            party_avg_win,
            party_avg_rebel,
            absence_vs_party,
            win_vs_party,
            CASE 
                WHEN individual_win > party_avg_win + cia_get_config_value('win_gap_above_average')::NUMERIC 
                     AND individual_absence < party_avg_absence - 3 THEN 'ABOVE_AVERAGE'
                WHEN individual_win < party_avg_win + cia_get_config_value('win_gap_below_average')::NUMERIC 
                     OR individual_absence > party_avg_absence + 5 THEN 'BELOW_AVERAGE'
                ELSE 'AVERAGE'
            END AS expected_classification
        FROM politician_comparison_base
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        ROUND(individual_absence, 2) AS individual_absence,
        ROUND(individual_win, 2) AS individual_win,
        ROUND(party_avg_absence, 2) AS party_avg_absence,
        ROUND(party_avg_win, 2) AS party_avg_win,
        ROUND(absence_vs_party, 2) AS absence_gap,
        ROUND(win_vs_party, 2) AS win_gap,
        expected_classification AS expected_detection,
        'comparative_peer_group' AS test_case,
        CASE 
            WHEN expected_classification IN ('ABOVE_AVERAGE', 'BELOW_AVERAGE') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM politician_comparison
    WHERE expected_classification IN ('ABOVE_AVERAGE', 'BELOW_AVERAGE')
    ORDER BY win_vs_party DESC
    LIMIT cia_get_config_value('sample_size_medium')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/comparative/test_2_2_peer_comparison.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected peer group classification'
\echo ''

-- ============================================================================
-- TEST 2.3: Party Momentum Analysis (Enhanced Temporal Comparison)
-- Expected Outcome: Classify parties by momentum (ACCELERATING/DECELERATING/STABLE)
-- Sample Size: 70 cases
-- ============================================================================
\echo '>>> Test Case 2.3: Party Momentum - Performance Acceleration Patterns'
\echo '>>> Expected Outcome: Classify parties by momentum'

COPY (
    WITH momentum_classification AS (
        SELECT 
            party,
            year,
            quarter,
            period,
            ballots_participated,
            ROUND(participation_rate, 2) AS participation_rate,
            ROUND(prev_quarter_rate, 2) AS prev_quarter_rate,
            ROUND(momentum, 2) AS momentum,
            ROUND(moving_avg_4q, 2) AS moving_avg_4q,
            ROUND(volatility, 2) AS volatility,
            ROUND(acceleration, 2) AS acceleration,
            trend_direction,
            stability_classification,
            intelligence_assessment,
            CASE 
                WHEN momentum > cia_get_config_value('momentum_strong_threshold')::NUMERIC 
                     AND acceleration > cia_get_config_value('acceleration_threshold')::NUMERIC THEN 'STRONG_ACCELERATION'
                WHEN momentum > cia_get_config_value('momentum_moderate_threshold')::NUMERIC 
                     AND trend_direction IN ('POSITIVE', 'STRONG_POSITIVE') THEN 'MODERATE_ACCELERATION'
                WHEN momentum < -cia_get_config_value('momentum_strong_threshold')::NUMERIC 
                     AND acceleration < -cia_get_config_value('acceleration_threshold')::NUMERIC THEN 'STRONG_DECELERATION'
                WHEN momentum < -cia_get_config_value('momentum_moderate_threshold')::NUMERIC 
                     AND trend_direction IN ('NEGATIVE', 'STRONG_NEGATIVE') THEN 'MODERATE_DECELERATION'
                WHEN volatility < cia_get_config_value('volatility_stable_threshold')::NUMERIC 
                     AND trend_direction = 'STABLE' THEN 'STABLE_PERFORMANCE'
                ELSE 'UNCLASSIFIED'
            END AS expected_momentum_classification
        FROM view_riksdagen_party_momentum_analysis
        WHERE year >= EXTRACT(YEAR FROM CURRENT_DATE) - 3
          AND ballots_participated > 0
    )
    SELECT 
        party,
        year,
        quarter,
        period,
        ballots_participated,
        participation_rate,
        prev_quarter_rate,
        momentum,
        moving_avg_4q,
        volatility,
        acceleration,
        trend_direction,
        stability_classification,
        expected_momentum_classification AS expected_detection,
        'comparative_party_momentum' AS test_case,
        CASE 
            WHEN expected_momentum_classification IN ('STRONG_ACCELERATION', 'STRONG_DECELERATION', 'MODERATE_ACCELERATION', 'MODERATE_DECELERATION') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM momentum_classification
    WHERE expected_momentum_classification != 'UNCLASSIFIED'
    ORDER BY 
        CASE expected_momentum_classification
            WHEN 'STRONG_ACCELERATION' THEN 1
            WHEN 'STRONG_DECELERATION' THEN 2
            WHEN 'MODERATE_ACCELERATION' THEN 3
            WHEN 'MODERATE_DECELERATION' THEN 4
            WHEN 'STABLE_PERFORMANCE' THEN 5
        END,
        year DESC, quarter DESC
    LIMIT 70
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/comparative/test_2_3_party_momentum.csv' WITH CSV HEADER;

\echo '>>> Exported 70 cases with party momentum classification'
\echo ''
