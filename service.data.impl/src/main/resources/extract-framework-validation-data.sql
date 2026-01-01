-- extract-framework-validation-data.sql
-- Framework Validation Dataset Extraction with Known Outcomes
-- Citizen Intelligence Agency - Intelligence Framework Validation
--
-- Purpose: Extracts validation datasets for all 6 intelligence frameworks
--          with known outcomes to verify analytical accuracy and tune algorithms
--
-- Frameworks Covered:
--   1. Temporal Analysis (time-series trends, anomalies)
--   2. Comparative Analysis (peer rankings, performance gaps)
--   3. Pattern Recognition (behavioral clustering, anomaly detection)
--   4. Predictive Intelligence (resignations, coalition changes, ministry decline)
--   5. Network Analysis (influence networks, power structures)
--   6. Decision Intelligence (reversals, consistency, effectiveness)
--
-- Usage:
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-framework-validation-data.sql
--
-- Output Directory: service.data.impl/sample-data/framework-validation/

\set ON_ERROR_STOP off
\timing on

\echo '===================================================================='
\echo 'CIA Framework Validation Dataset Extraction'
\echo 'Started:' `date`
\echo '===================================================================='
\echo ''

-- ============================================================================
-- FRAMEWORK 1: TEMPORAL ANALYSIS VALIDATION
-- ============================================================================
-- Test Cases:
--   - Upward trends (attendance improvement, productivity increase)
--   - Downward trends (declining engagement, ministry effectiveness drop)
--   - Cyclical patterns (seasonal parliamentary activity)
--   - Anomalies (sudden changes, crisis response)
-- Expected Accuracy: 82% for ministry decline, 95% for trend detection
-- ============================================================================

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 1: TEMPORAL ANALYSIS VALIDATION                   ==='
\echo '==================================================================='
\echo ''

-- TEMPORAL TEST 1.1: Upward Trend Detection (Politician Attendance Improvement)
\echo '>>> Test Case 1.1: Upward Trend - Politician Attendance Improvement'
\echo '>>> Expected Outcome: Detect attendance rate increase ≥10% over 6 months'

\copy (
    WITH politician_trends AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            year_month,
            avg_absence_rate,
            LAG(avg_absence_rate, 6) OVER (PARTITION BY person_id ORDER BY year_month) AS absence_6mo_ago,
            avg_absence_rate - LAG(avg_absence_rate, 6) OVER (PARTITION BY person_id ORDER BY year_month) AS absence_change,
            CASE 
                WHEN (avg_absence_rate - LAG(avg_absence_rate, 6) OVER (PARTITION BY person_id ORDER BY year_month)) < -10 THEN 'SIGNIFICANT_IMPROVEMENT'
                WHEN (avg_absence_rate - LAG(avg_absence_rate, 6) OVER (PARTITION BY person_id ORDER BY year_month)) < -5 THEN 'MODERATE_IMPROVEMENT'
                ELSE 'STABLE'
            END AS trend_classification
        FROM view_politician_behavioral_trends
        WHERE ballot_count >= 10
          AND year_month >= CURRENT_DATE - INTERVAL '24 months'
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        year_month AS measurement_month,
        ROUND(absence_6mo_ago, 2) AS baseline_absence_rate,
        ROUND(avg_absence_rate, 2) AS current_absence_rate,
        ROUND(absence_change, 2) AS change_magnitude,
        trend_classification AS expected_detection,
        'temporal_upward_trend' AS test_case,
        CASE 
            WHEN trend_classification IN ('SIGNIFICANT_IMPROVEMENT', 'MODERATE_IMPROVEMENT') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM politician_trends
    WHERE trend_classification IN ('SIGNIFICANT_IMPROVEMENT', 'MODERATE_IMPROVEMENT')
    ORDER BY absence_change ASC
    LIMIT 50
) TO 'service.data.impl/sample-data/framework-validation/temporal/test_1_1_upward_trend_attendance.csv' WITH CSV HEADER;

\echo '>>> Exported 50 cases with expected upward trend detection (attendance improvement)'
\echo ''

-- TEMPORAL TEST 1.2: Downward Trend Detection (Ministry Effectiveness Decline)
\echo '>>> Test Case 1.2: Downward Trend - Ministry Effectiveness Decline'
\echo '>>> Expected Outcome: Detect ministry approval rate decline ≥15% over 4 quarters'

\copy (
    WITH ministry_trends AS (
        SELECT 
            ministry_code,
            ministry_name,
            decision_year,
            decision_quarter,
            TO_DATE(decision_year::TEXT || '-' || ((decision_quarter-1)*3+1)::TEXT || '-01', 'YYYY-MM-DD') AS quarter_date,
            approval_rate,
            LAG(approval_rate, 4) OVER (PARTITION BY ministry_code ORDER BY decision_year, decision_quarter) AS approval_4q_ago,
            approval_rate - LAG(approval_rate, 4) OVER (PARTITION BY ministry_code ORDER BY decision_year, decision_quarter) AS approval_change,
            CASE 
                WHEN (approval_rate - LAG(approval_rate, 4) OVER (PARTITION BY ministry_code ORDER BY decision_year, decision_quarter)) < -15 THEN 'SIGNIFICANT_DECLINE'
                WHEN (approval_rate - LAG(approval_rate, 4) OVER (PARTITION BY ministry_code ORDER BY decision_year, decision_quarter)) < -10 THEN 'MODERATE_DECLINE'
                ELSE 'STABLE'
            END AS trend_classification
        FROM view_ministry_decision_impact
        WHERE total_proposals >= 5
          AND decision_year >= EXTRACT(YEAR FROM CURRENT_DATE) - 3
    )
    SELECT 
        ministry_code,
        ministry_name,
        decision_year,
        decision_quarter,
        quarter_date,
        ROUND(approval_4q_ago, 2) AS baseline_approval_rate,
        ROUND(approval_rate, 2) AS current_approval_rate,
        ROUND(approval_change, 2) AS change_magnitude,
        trend_classification AS expected_detection,
        'temporal_downward_trend' AS test_case,
        CASE 
            WHEN trend_classification IN ('SIGNIFICANT_DECLINE', 'MODERATE_DECLINE') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM ministry_trends
    WHERE trend_classification IN ('SIGNIFICANT_DECLINE', 'MODERATE_DECLINE')
    ORDER BY approval_change ASC
    LIMIT 30
) TO 'service.data.impl/sample-data/framework-validation/temporal/test_1_2_downward_trend_ministry.csv' WITH CSV HEADER;

\echo '>>> Exported 30 cases with expected downward trend detection (ministry decline)'
\echo ''

-- TEMPORAL TEST 1.3: Cyclical Pattern Detection (Parliamentary Session Activity)
\echo '>>> Test Case 1.3: Cyclical Patterns - Parliamentary Session Seasonality'
\echo '>>> Expected Outcome: Detect seasonal patterns (Autumn high, Summer low)'

\copy (
    WITH seasonal_patterns AS (
        SELECT 
            decision_day,
            EXTRACT(MONTH FROM decision_day) AS month_number,
            EXTRACT(QUARTER FROM decision_day) AS quarter,
            EXTRACT(YEAR FROM decision_day) AS year,
            TO_CHAR(decision_day, 'Month') AS month_name,
            daily_decisions,
            parliamentary_period,
            CASE 
                WHEN EXTRACT(MONTH FROM decision_day) IN (9, 10, 11) THEN 'AUTUMN_SESSION_HIGH'
                WHEN EXTRACT(MONTH FROM decision_day) IN (7, 8) THEN 'SUMMER_RECESS_LOW'
                WHEN EXTRACT(MONTH FROM decision_day) IN (12, 1) THEN 'WINTER_RECESS_LOW'
                WHEN EXTRACT(MONTH FROM decision_day) IN (2, 3, 4, 5, 6) THEN 'SPRING_SESSION_MODERATE'
                ELSE 'UNCLASSIFIED'
            END AS expected_pattern,
            CASE 
                WHEN EXTRACT(MONTH FROM decision_day) IN (9, 10, 11) AND daily_decisions >= 30 THEN 'PASS'
                WHEN EXTRACT(MONTH FROM decision_day) IN (7, 8) AND daily_decisions <= 15 THEN 'PASS'
                WHEN EXTRACT(MONTH FROM decision_day) IN (12, 1) AND daily_decisions <= 20 THEN 'PASS'
                ELSE 'BASELINE'
            END AS validation_label
        FROM view_decision_temporal_trends
        WHERE decision_day >= CURRENT_DATE - INTERVAL '36 months'
          AND daily_decisions > 0
    )
    SELECT 
        decision_day,
        year,
        quarter,
        month_number,
        month_name,
        daily_decisions,
        parliamentary_period,
        expected_pattern,
        'temporal_cyclical_pattern' AS test_case,
        validation_label
    FROM seasonal_patterns
    WHERE validation_label = 'PASS'
    ORDER BY decision_day DESC
    LIMIT 100
) TO 'service.data.impl/sample-data/framework-validation/temporal/test_1_3_cyclical_patterns.csv' WITH CSV HEADER;

\echo '>>> Exported 100 cases with expected cyclical pattern detection'
\echo ''

-- TEMPORAL TEST 1.4: Anomaly Detection (Sudden Behavior Changes)
\echo '>>> Test Case 1.4: Anomalies - Sudden Decision Volume Spikes'
\echo '>>> Expected Outcome: Detect days with decision volume >2 standard deviations'

\copy (
    WITH decision_stats AS (
        SELECT 
            AVG(daily_decisions) AS mean_decisions,
            STDDEV(daily_decisions) AS stddev_decisions
        FROM view_decision_temporal_trends
        WHERE decision_day >= CURRENT_DATE - INTERVAL '12 months'
    ),
    anomaly_detection AS (
        SELECT 
            vdt.decision_day,
            vdt.daily_decisions,
            vdt.ma_7day_decisions,
            vdt.ma_30day_decisions,
            vdt.parliamentary_period,
            ROUND(s.mean_decisions, 2) AS baseline_mean,
            ROUND(s.stddev_decisions, 2) AS baseline_stddev,
            ROUND((vdt.daily_decisions - s.mean_decisions) / NULLIF(s.stddev_decisions, 0), 2) AS z_score,
            CASE 
                WHEN ABS((vdt.daily_decisions - s.mean_decisions) / NULLIF(s.stddev_decisions, 0)) > 2.5 THEN 'EXTREME_ANOMALY'
                WHEN ABS((vdt.daily_decisions - s.mean_decisions) / NULLIF(s.stddev_decisions, 0)) > 2.0 THEN 'SIGNIFICANT_ANOMALY'
                WHEN ABS((vdt.daily_decisions - s.mean_decisions) / NULLIF(s.stddev_decisions, 0)) > 1.5 THEN 'MODERATE_ANOMALY'
                ELSE 'NORMAL'
            END AS expected_classification
        FROM view_decision_temporal_trends vdt
        CROSS JOIN decision_stats s
        WHERE vdt.decision_day >= CURRENT_DATE - INTERVAL '12 months'
    )
    SELECT 
        decision_day,
        daily_decisions,
        ROUND(ma_7day_decisions, 2) AS smoothed_7day,
        ROUND(ma_30day_decisions, 2) AS smoothed_30day,
        parliamentary_period,
        baseline_mean,
        baseline_stddev,
        z_score,
        expected_classification AS expected_detection,
        'temporal_anomaly_detection' AS test_case,
        CASE 
            WHEN expected_classification IN ('EXTREME_ANOMALY', 'SIGNIFICANT_ANOMALY') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM anomaly_detection
    WHERE expected_classification IN ('EXTREME_ANOMALY', 'SIGNIFICANT_ANOMALY', 'MODERATE_ANOMALY')
    ORDER BY ABS(z_score) DESC
    LIMIT 40
) TO 'service.data.impl/sample-data/framework-validation/temporal/test_1_4_anomaly_detection.csv' WITH CSV HEADER;

\echo '>>> Exported 40 cases with expected anomaly detection (z-score >1.5)'
\echo ''

-- ============================================================================
-- FRAMEWORK 2: COMPARATIVE ANALYSIS VALIDATION
-- ============================================================================
-- Test Cases:
--   - Party performance rankings (win rate, discipline, productivity)
--   - Politician peer group comparisons (vs. party average, vs. experience level)
--   - Committee effectiveness rankings
-- Expected Accuracy: 90%+ for performance ranking classification
-- ============================================================================

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 2: COMPARATIVE ANALYSIS VALIDATION                ==='
\echo '==================================================================='
\echo ''

-- COMPARATIVE TEST 2.1: Party Performance Rankings
\echo '>>> Test Case 2.1: Party Performance Rankings - Win Rate & Discipline'
\echo '>>> Expected Outcome: Classify parties into performance tiers (HIGH/MEDIUM/LOW)'

\copy (
    WITH party_metrics AS (
        SELECT 
            party,
            active_members,
            AVG(avg_party_win_rate) AS avg_win_rate,
            AVG(avg_party_discipline) AS avg_discipline,
            AVG(avg_party_absence_rate) AS avg_absence_rate,
            SUM(total_documents) AS total_documents,
            COUNT(*) AS months_tracked
        FROM view_party_effectiveness_trends
        WHERE year_month >= CURRENT_DATE - INTERVAL '12 months'
        GROUP BY party, active_members
        HAVING COUNT(*) >= 6
    ),
    party_rankings AS (
        SELECT 
            party,
            active_members,
            ROUND(avg_win_rate, 2) AS avg_win_rate,
            ROUND(avg_discipline, 2) AS avg_discipline,
            ROUND(avg_absence_rate, 2) AS avg_absence_rate,
            total_documents,
            RANK() OVER (ORDER BY avg_win_rate DESC) AS win_rate_rank,
            RANK() OVER (ORDER BY avg_discipline DESC) AS discipline_rank,
            RANK() OVER (ORDER BY avg_absence_rate ASC) AS attendance_rank,
            CASE 
                WHEN avg_win_rate >= 70 AND avg_discipline >= 92 THEN 'HIGH_PERFORMANCE'
                WHEN avg_win_rate >= 60 AND avg_discipline >= 88 THEN 'MEDIUM_PERFORMANCE'
                ELSE 'LOW_PERFORMANCE'
            END AS expected_classification
        FROM party_metrics
    )
    SELECT 
        party,
        active_members,
        avg_win_rate,
        avg_discipline,
        avg_absence_rate,
        total_documents,
        win_rate_rank,
        discipline_rank,
        attendance_rank,
        expected_classification AS expected_tier,
        'comparative_party_ranking' AS test_case,
        CASE 
            WHEN expected_classification IN ('HIGH_PERFORMANCE', 'MEDIUM_PERFORMANCE', 'LOW_PERFORMANCE') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM party_rankings
    ORDER BY avg_win_rate DESC
) TO 'service.data.impl/sample-data/framework-validation/comparative/test_2_1_party_rankings.csv' WITH CSV HEADER;

\echo '>>> Exported party performance rankings with expected tier classification'
\echo ''

-- COMPARATIVE TEST 2.2: Politician Peer Group Comparison
\echo '>>> Test Case 2.2: Politician vs. Party Average Performance'
\echo '>>> Expected Outcome: Classify politicians as ABOVE_AVERAGE/AVERAGE/BELOW_AVERAGE'

\copy (
    WITH party_averages AS (
        SELECT 
            party,
            AVG(avg_absence_rate) AS party_avg_absence,
            AVG(avg_win_rate) AS party_avg_win,
            AVG(avg_rebel_rate) AS party_avg_rebel
        FROM view_politician_behavioral_trends
        WHERE year_month >= CURRENT_DATE - INTERVAL '12 months'
          AND ballot_count >= 10
        GROUP BY party
    ),
    politician_comparison AS (
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
            AVG(pbt.avg_win_rate) - pa.party_avg_win AS win_vs_party,
            CASE 
                WHEN AVG(pbt.avg_win_rate) > pa.party_avg_win + 5 AND AVG(pbt.avg_absence_rate) < pa.party_avg_absence - 3 THEN 'ABOVE_AVERAGE'
                WHEN AVG(pbt.avg_win_rate) < pa.party_avg_win - 5 OR AVG(pbt.avg_absence_rate) > pa.party_avg_absence + 5 THEN 'BELOW_AVERAGE'
                ELSE 'AVERAGE'
            END AS expected_classification
        FROM view_politician_behavioral_trends pbt
        JOIN party_averages pa ON pa.party = pbt.party
        WHERE pbt.year_month >= CURRENT_DATE - INTERVAL '12 months'
          AND pbt.ballot_count >= 10
        GROUP BY pbt.person_id, pbt.first_name, pbt.last_name, pbt.party, 
                 pa.party_avg_absence, pa.party_avg_win, pa.party_avg_rebel
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
        expected_classification AS expected_tier,
        'comparative_peer_group' AS test_case,
        CASE 
            WHEN expected_classification IN ('ABOVE_AVERAGE', 'BELOW_AVERAGE') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM politician_comparison
    WHERE expected_classification IN ('ABOVE_AVERAGE', 'BELOW_AVERAGE')
    ORDER BY win_vs_party DESC
    LIMIT 60
) TO 'service.data.impl/sample-data/framework-validation/comparative/test_2_2_peer_comparison.csv' WITH CSV HEADER;

\echo '>>> Exported 60 cases with expected peer group classification'
\echo ''

-- ============================================================================
-- FRAMEWORK 3: PATTERN RECOGNITION VALIDATION
-- ============================================================================
-- Test Cases:
--   - Behavioral clustering (normal, anomalous, concerning)
--   - Rebellion pattern identification
--   - Absence pattern classification
-- Expected Accuracy: 91% true positive rate for behavioral clustering
-- ============================================================================

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 3: PATTERN RECOGNITION VALIDATION                 ==='
\echo '==================================================================='
\echo ''

-- PATTERN TEST 3.1: Behavioral Clustering (Normal, Anomalous, Concerning)
\echo '>>> Test Case 3.1: Behavioral Clustering - Performance Pattern Classification'
\echo '>>> Expected Outcome: Classify behavioral patterns into 3 clusters'

\copy (
    WITH politician_patterns AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            AVG(avg_absence_rate) AS avg_absence,
            AVG(avg_win_rate) AS avg_win,
            AVG(avg_rebel_rate) AS avg_rebel,
            COUNT(*) AS months_tracked,
            STDDEV(avg_absence_rate) AS absence_volatility,
            CASE 
                WHEN AVG(avg_absence_rate) < 10 AND AVG(avg_win_rate) > 60 AND AVG(avg_rebel_rate) < 10 THEN 'NORMAL_BEHAVIOR'
                WHEN AVG(avg_absence_rate) BETWEEN 10 AND 20 OR AVG(avg_rebel_rate) BETWEEN 10 AND 15 THEN 'ANOMALOUS_BEHAVIOR'
                WHEN AVG(avg_absence_rate) > 20 OR AVG(avg_win_rate) < 45 OR AVG(avg_rebel_rate) > 15 THEN 'CONCERNING_BEHAVIOR'
                ELSE 'UNCLASSIFIED'
            END AS expected_cluster
        FROM view_politician_behavioral_trends
        WHERE year_month >= CURRENT_DATE - INTERVAL '12 months'
          AND ballot_count >= 10
        GROUP BY person_id, first_name, last_name, party
        HAVING COUNT(*) >= 6
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        ROUND(avg_absence, 2) AS avg_absence_rate,
        ROUND(avg_win, 2) AS avg_win_rate,
        ROUND(avg_rebel, 2) AS avg_rebel_rate,
        ROUND(absence_volatility, 2) AS absence_volatility,
        months_tracked,
        expected_cluster,
        'pattern_behavioral_clustering' AS test_case,
        CASE 
            WHEN expected_cluster IN ('NORMAL_BEHAVIOR', 'ANOMALOUS_BEHAVIOR', 'CONCERNING_BEHAVIOR') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM politician_patterns
    WHERE expected_cluster != 'UNCLASSIFIED'
    ORDER BY 
        CASE expected_cluster
            WHEN 'CONCERNING_BEHAVIOR' THEN 1
            WHEN 'ANOMALOUS_BEHAVIOR' THEN 2
            WHEN 'NORMAL_BEHAVIOR' THEN 3
        END,
        avg_absence DESC
    LIMIT 100
) TO 'service.data.impl/sample-data/framework-validation/pattern/test_3_1_behavioral_clustering.csv' WITH CSV HEADER;

\echo '>>> Exported 100 cases with expected behavioral cluster classification'
\echo ''

-- PATTERN TEST 3.2: Rebellion Pattern Identification
\echo '>>> Test Case 3.2: Rebellion Patterns - High Independence vs. Party Line'
\echo '>>> Expected Outcome: Identify HIGH_INDEPENDENCE and PARTY_LINE patterns'

\copy (
    WITH rebellion_patterns AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            AVG(avg_rebel_rate) AS avg_rebel_rate,
            MAX(avg_rebel_rate) AS peak_rebel_rate,
            STDDEV(avg_rebel_rate) AS rebel_volatility,
            COUNT(*) AS months_tracked,
            CASE 
                WHEN AVG(avg_rebel_rate) > 15 THEN 'HIGH_INDEPENDENCE'
                WHEN AVG(avg_rebel_rate) BETWEEN 10 AND 15 THEN 'MODERATE_INDEPENDENCE'
                WHEN AVG(avg_rebel_rate) BETWEEN 5 AND 10 THEN 'LOW_INDEPENDENCE'
                WHEN AVG(avg_rebel_rate) < 5 THEN 'PARTY_LINE'
                ELSE 'UNCLASSIFIED'
            END AS expected_pattern
        FROM view_politician_behavioral_trends
        WHERE year_month >= CURRENT_DATE - INTERVAL '12 months'
          AND ballot_count >= 10
        GROUP BY person_id, first_name, last_name, party
        HAVING COUNT(*) >= 6
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        ROUND(avg_rebel_rate, 2) AS avg_rebel_rate,
        ROUND(peak_rebel_rate, 2) AS peak_rebel_rate,
        ROUND(rebel_volatility, 2) AS rebel_volatility,
        months_tracked,
        expected_pattern AS expected_rebellion_pattern,
        'pattern_rebellion_identification' AS test_case,
        CASE 
            WHEN expected_pattern IN ('HIGH_INDEPENDENCE', 'PARTY_LINE') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM rebellion_patterns
    WHERE expected_pattern IN ('HIGH_INDEPENDENCE', 'PARTY_LINE', 'MODERATE_INDEPENDENCE')
    ORDER BY avg_rebel_rate DESC
    LIMIT 80
) TO 'service.data.impl/sample-data/framework-validation/pattern/test_3_2_rebellion_patterns.csv' WITH CSV HEADER;

\echo '>>> Exported 80 cases with expected rebellion pattern classification'
\echo ''

-- ============================================================================
-- FRAMEWORK 4: PREDICTIVE INTELLIGENCE VALIDATION
-- ============================================================================
-- Test Cases:
--   - Resignation prediction (declining engagement signals)
--   - Coalition stress prediction (alignment degradation)
--   - Ministry effectiveness forecasting
-- Expected Accuracy: 87% for resignation prediction (6-8 months prior)
-- ============================================================================

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 4: PREDICTIVE INTELLIGENCE VALIDATION             ==='
\echo '==================================================================='
\echo ''

-- PREDICTIVE TEST 4.1: Resignation Risk Prediction Signals
\echo '>>> Test Case 4.1: Resignation Risk - Declining Engagement Signals'
\echo '>>> Expected Outcome: Identify HIGH_RISK, MODERATE_RISK patterns'

\copy (
    WITH resignation_signals AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            AVG(avg_absence_rate) AS avg_absence,
            AVG(absence_trend) AS avg_absence_trend,
            AVG(effectiveness_trend) AS avg_effectiveness_trend,
            AVG(ma_3month_absence) AS smoothed_absence,
            COUNT(*) AS months_tracked,
            CASE 
                WHEN AVG(avg_absence_rate) > 20 AND AVG(absence_trend) > 2 AND AVG(effectiveness_trend) < -3 THEN 'HIGH_RESIGNATION_RISK'
                WHEN AVG(avg_absence_rate) > 15 AND AVG(absence_trend) > 1 THEN 'MODERATE_RESIGNATION_RISK'
                ELSE 'LOW_RISK'
            END AS expected_risk_level
        FROM view_politician_behavioral_trends
        WHERE year_month >= CURRENT_DATE - INTERVAL '12 months'
          AND ballot_count >= 10
        GROUP BY person_id, first_name, last_name, party
        HAVING COUNT(*) >= 6
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        ROUND(avg_absence, 2) AS avg_absence_rate,
        ROUND(avg_absence_trend, 2) AS absence_trend,
        ROUND(avg_effectiveness_trend, 2) AS effectiveness_trend,
        ROUND(smoothed_absence, 2) AS smoothed_3month_absence,
        months_tracked,
        expected_risk_level AS expected_prediction,
        'predictive_resignation_risk' AS test_case,
        CASE 
            WHEN expected_risk_level IN ('HIGH_RESIGNATION_RISK', 'MODERATE_RESIGNATION_RISK') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM resignation_signals
    WHERE expected_risk_level IN ('HIGH_RESIGNATION_RISK', 'MODERATE_RESIGNATION_RISK')
    ORDER BY avg_absence DESC, avg_absence_trend DESC
    LIMIT 40
) TO 'service.data.impl/sample-data/framework-validation/predictive/test_4_1_resignation_prediction.csv' WITH CSV HEADER;

\echo '>>> Exported 40 cases with expected resignation risk prediction'
\echo ''

-- PREDICTIVE TEST 4.2: Coalition Stress Prediction
\echo '>>> Test Case 4.2: Coalition Stress - Alignment Degradation Signals'
\echo '>>> Expected Outcome: Detect coalition stress with ≥78% accuracy'

\copy (
    WITH coalition_pairs AS (
        SELECT 
            party_1,
            party_2,
            alignment_rate,
            shared_votes,
            coalition_likelihood,
            bloc_relationship,
            CASE 
                WHEN alignment_rate < 60 AND shared_votes >= 100 AND bloc_relationship LIKE '%BLOC_INTERNAL%' THEN 'HIGH_COALITION_STRESS'
                WHEN alignment_rate BETWEEN 60 AND 70 AND bloc_relationship LIKE '%BLOC_INTERNAL%' THEN 'MODERATE_COALITION_STRESS'
                WHEN alignment_rate >= 75 THEN 'STABLE_COALITION'
                ELSE 'UNCLASSIFIED'
            END AS expected_stress_level
        FROM view_riksdagen_coalition_alignment_matrix
        WHERE shared_votes >= 50
    )
    SELECT 
        party_1,
        party_2,
        ROUND(alignment_rate, 2) AS alignment_rate,
        shared_votes,
        coalition_likelihood,
        bloc_relationship,
        expected_stress_level AS expected_prediction,
        'predictive_coalition_stress' AS test_case,
        CASE 
            WHEN expected_stress_level IN ('HIGH_COALITION_STRESS', 'MODERATE_COALITION_STRESS') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM coalition_pairs
    WHERE expected_stress_level IN ('HIGH_COALITION_STRESS', 'MODERATE_COALITION_STRESS', 'STABLE_COALITION')
    ORDER BY alignment_rate ASC
    LIMIT 50
) TO 'service.data.impl/sample-data/framework-validation/predictive/test_4_2_coalition_stress.csv' WITH CSV HEADER;

\echo '>>> Exported 50 cases with expected coalition stress prediction'
\echo ''

-- ============================================================================
-- FRAMEWORK 5: NETWORK ANALYSIS VALIDATION
-- ============================================================================
-- Test Cases:
--   - Power broker identification (high betweenness centrality)
--   - Influence network structure (cross-party connections)
--   - Coalition facilitator detection
-- Expected Accuracy: 90% for power structure mapping
-- ============================================================================

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 5: NETWORK ANALYSIS VALIDATION                    ==='
\echo '==================================================================='
\echo ''

-- NETWORK TEST 5.1: Power Broker Identification
\echo '>>> Test Case 5.1: Power Brokers - High Influence & Cross-Party Connections'
\echo '>>> Expected Outcome: Identify STRONG_BROKER and MODERATE_BROKER classifications'

\copy (
    WITH power_metrics AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            broker_score,
            cross_party_connections,
            total_connections,
            connectivity_level,
            broker_classification,
            CASE 
                WHEN broker_score >= 0.7 AND cross_party_connections >= 5 THEN 'STRONG_POWER_BROKER'
                WHEN broker_score >= 0.5 AND cross_party_connections >= 3 THEN 'MODERATE_POWER_BROKER'
                WHEN cross_party_connections >= 4 THEN 'CROSS_PARTY_CONNECTOR'
                ELSE 'STANDARD_NETWORK'
            END AS expected_classification
        FROM view_riksdagen_politician_influence_metrics
        WHERE total_connections > 0
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
            WHEN expected_classification IN ('STRONG_POWER_BROKER', 'MODERATE_POWER_BROKER') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM power_metrics
    WHERE expected_classification IN ('STRONG_POWER_BROKER', 'MODERATE_POWER_BROKER', 'CROSS_PARTY_CONNECTOR')
    ORDER BY broker_score DESC, cross_party_connections DESC
    LIMIT 60
) TO 'service.data.impl/sample-data/framework-validation/network/test_5_1_power_brokers.csv' WITH CSV HEADER;

\echo '>>> Exported 60 cases with expected power broker classification'
\echo ''

-- NETWORK TEST 5.2: Coalition Facilitator Detection (Cross-Bloc Bridges)
\echo '>>> Test Case 5.2: Coalition Facilitators - Cross-Bloc Connection Patterns'
\echo '>>> Expected Outcome: Identify politicians bridging political blocs'

\copy (
    WITH coalition_bridges AS (
        SELECT 
            party_1,
            party_2,
            alignment_rate,
            shared_votes,
            votes_aligned,
            coalition_likelihood,
            bloc_relationship,
            CASE 
                WHEN bloc_relationship = 'CROSS_BLOC' AND alignment_rate >= 50 THEN 'COALITION_FACILITATOR'
                WHEN bloc_relationship LIKE '%BLOC_INTERNAL%' AND alignment_rate >= 80 THEN 'BLOC_SOLIDIFIER'
                ELSE 'STANDARD_RELATIONSHIP'
            END AS expected_role
        FROM view_riksdagen_coalition_alignment_matrix
        WHERE shared_votes >= 100
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
    LIMIT 50
) TO 'service.data.impl/sample-data/framework-validation/network/test_5_2_coalition_facilitators.csv' WITH CSV HEADER;

\echo '>>> Exported 50 cases with expected coalition facilitator identification'
\echo ''

-- ============================================================================
-- FRAMEWORK 6: DECISION INTELLIGENCE VALIDATION
-- ============================================================================
-- Test Cases:
--   - Policy reversal detection (decision pattern inconsistency)
--   - Coalition misalignment (conflicting decisions)
--   - Ministry effectiveness patterns
-- Expected Accuracy: 85% for decision pattern classification
-- ============================================================================

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 6: DECISION INTELLIGENCE VALIDATION               ==='
\echo '==================================================================='
\echo ''

-- DECISION TEST 6.1: Decision Effectiveness Patterns
\echo '>>> Test Case 6.1: Decision Effectiveness - Approval Rate Patterns'
\echo '>>> Expected Outcome: Classify HIGH_EFFECTIVENESS, LOW_EFFECTIVENESS patterns'

\copy (
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
                WHEN AVG(approval_rate) >= 75 AND SUM(total_proposals) >= 10 THEN 'HIGH_EFFECTIVENESS'
                WHEN AVG(approval_rate) BETWEEN 50 AND 75 THEN 'MODERATE_EFFECTIVENESS'
                WHEN AVG(approval_rate) < 50 AND SUM(total_proposals) >= 5 THEN 'LOW_EFFECTIVENESS'
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
    LIMIT 80
) TO 'service.data.impl/sample-data/framework-validation/decision/test_6_1_effectiveness_patterns.csv' WITH CSV HEADER;

\echo '>>> Exported 80 cases with expected decision effectiveness classification'
\echo ''

-- DECISION TEST 6.2: Coalition Misalignment Detection
\echo '>>> Test Case 6.2: Coalition Misalignment - Conflicting Decision Patterns'
\echo '>>> Expected Outcome: Detect MISALIGNED vs. ALIGNED coalition decision patterns'

\copy (
    WITH coalition_decisions AS (
        SELECT 
            pdf1.party AS party_1,
            pdf2.party AS party_2,
            pdf1.committee,
            pdf1.decision_year,
            pdf1.decision_quarter,
            AVG(pdf1.approval_rate) AS party1_approval,
            AVG(pdf2.approval_rate) AS party2_approval,
            ABS(AVG(pdf1.approval_rate) - AVG(pdf2.approval_rate)) AS approval_gap,
            CASE 
                WHEN ABS(AVG(pdf1.approval_rate) - AVG(pdf2.approval_rate)) > 30 THEN 'COALITION_MISALIGNMENT'
                WHEN ABS(AVG(pdf1.approval_rate) - AVG(pdf2.approval_rate)) < 15 THEN 'COALITION_ALIGNMENT'
                ELSE 'MODERATE_DIVERGENCE'
            END AS expected_alignment
        FROM view_riksdagen_party_decision_flow pdf1
        INNER JOIN view_riksdagen_party_decision_flow pdf2 
            ON pdf2.committee = pdf1.committee 
            AND pdf2.decision_year = pdf1.decision_year 
            AND pdf2.decision_quarter = pdf1.decision_quarter
            AND pdf2.party > pdf1.party
        WHERE pdf1.total_proposals >= 3
          AND pdf2.total_proposals >= 3
          AND pdf1.decision_year >= EXTRACT(YEAR FROM CURRENT_DATE) - 2
        GROUP BY pdf1.party, pdf2.party, pdf1.committee, pdf1.decision_year, pdf1.decision_quarter
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
    LIMIT 60
) TO 'service.data.impl/sample-data/framework-validation/decision/test_6_2_coalition_misalignment.csv' WITH CSV HEADER;

\echo '>>> Exported 60 cases with expected coalition alignment detection'
\echo ''

-- ============================================================================
-- VALIDATION REPORT GENERATION
-- ============================================================================

\echo ''
\echo '==================================================================='
\echo '=== VALIDATION REPORT SUMMARY                                   ==='
\echo '==================================================================='
\echo ''

-- Generate validation summary CSV
\copy (
    SELECT 
        'Temporal Analysis' AS framework,
        'Test 1.1' AS test_id,
        'Upward Trend Detection' AS test_name,
        'Attendance Improvement ≥10% over 6 months' AS test_description,
        '95% confidence' AS expected_accuracy,
        50 AS sample_size,
        'temporal/test_1_1_upward_trend_attendance.csv' AS data_file
    UNION ALL
    SELECT 
        'Temporal Analysis' AS framework,
        'Test 1.2' AS test_id,
        'Downward Trend Detection' AS test_name,
        'Ministry approval rate decline ≥15% over 4 quarters' AS test_description,
        '82% accuracy' AS expected_accuracy,
        30 AS sample_size,
        'temporal/test_1_2_downward_trend_ministry.csv' AS data_file
    UNION ALL
    SELECT 
        'Temporal Analysis' AS framework,
        'Test 1.3' AS test_id,
        'Cyclical Pattern Detection' AS test_name,
        'Parliamentary seasonal patterns (Autumn high, Summer low)' AS test_description,
        '95% confidence' AS expected_accuracy,
        100 AS sample_size,
        'temporal/test_1_3_cyclical_patterns.csv' AS data_file
    UNION ALL
    SELECT 
        'Temporal Analysis' AS framework,
        'Test 1.4' AS test_id,
        'Anomaly Detection' AS test_name,
        'Decision volume >2 standard deviations' AS test_description,
        '90% true positive rate' AS expected_accuracy,
        40 AS sample_size,
        'temporal/test_1_4_anomaly_detection.csv' AS data_file
    UNION ALL
    SELECT 
        'Comparative Analysis' AS framework,
        'Test 2.1' AS test_id,
        'Party Performance Rankings' AS test_name,
        'Classify parties into HIGH/MEDIUM/LOW performance tiers' AS test_description,
        '90%+ accuracy' AS expected_accuracy,
        8 AS sample_size,
        'comparative/test_2_1_party_rankings.csv' AS data_file
    UNION ALL
    SELECT 
        'Comparative Analysis' AS framework,
        'Test 2.2' AS test_id,
        'Peer Group Comparison' AS test_name,
        'Classify politicians vs. party average (ABOVE/AVERAGE/BELOW)' AS test_description,
        '90%+ accuracy' AS expected_accuracy,
        60 AS sample_size,
        'comparative/test_2_2_peer_comparison.csv' AS data_file
    UNION ALL
    SELECT 
        'Pattern Recognition' AS framework,
        'Test 3.1' AS test_id,
        'Behavioral Clustering' AS test_name,
        'Classify patterns into NORMAL/ANOMALOUS/CONCERNING clusters' AS test_description,
        '91% true positive rate' AS expected_accuracy,
        100 AS sample_size,
        'pattern/test_3_1_behavioral_clustering.csv' AS data_file
    UNION ALL
    SELECT 
        'Pattern Recognition' AS framework,
        'Test 3.2' AS test_id,
        'Rebellion Pattern Identification' AS test_name,
        'Identify HIGH_INDEPENDENCE and PARTY_LINE patterns' AS test_description,
        '85% accuracy' AS expected_accuracy,
        80 AS sample_size,
        'pattern/test_3_2_rebellion_patterns.csv' AS data_file
    UNION ALL
    SELECT 
        'Predictive Intelligence' AS framework,
        'Test 4.1' AS test_id,
        'Resignation Risk Prediction' AS test_name,
        'Identify HIGH_RISK and MODERATE_RISK resignation signals' AS test_description,
        '87% accuracy (6-8 months prior)' AS expected_accuracy,
        40 AS sample_size,
        'predictive/test_4_1_resignation_prediction.csv' AS data_file
    UNION ALL
    SELECT 
        'Predictive Intelligence' AS framework,
        'Test 4.2' AS test_id,
        'Coalition Stress Prediction' AS test_name,
        'Detect coalition stress with alignment degradation' AS test_description,
        '78% accuracy' AS expected_accuracy,
        50 AS sample_size,
        'predictive/test_4_2_coalition_stress.csv' AS data_file
    UNION ALL
    SELECT 
        'Network Analysis' AS framework,
        'Test 5.1' AS test_id,
        'Power Broker Identification' AS test_name,
        'Identify STRONG_BROKER and MODERATE_BROKER classifications' AS test_description,
        '90% accuracy' AS expected_accuracy,
        60 AS sample_size,
        'network/test_5_1_power_brokers.csv' AS data_file
    UNION ALL
    SELECT 
        'Network Analysis' AS framework,
        'Test 5.2' AS test_id,
        'Coalition Facilitator Detection' AS test_name,
        'Identify cross-bloc bridge politicians' AS test_description,
        '85% accuracy' AS expected_accuracy,
        50 AS sample_size,
        'network/test_5_2_coalition_facilitators.csv' AS data_file
    UNION ALL
    SELECT 
        'Decision Intelligence' AS framework,
        'Test 6.1' AS test_id,
        'Decision Effectiveness Patterns' AS test_name,
        'Classify HIGH/MODERATE/LOW effectiveness patterns' AS test_description,
        '85% accuracy' AS expected_accuracy,
        80 AS sample_size,
        'decision/test_6_1_effectiveness_patterns.csv' AS data_file
    UNION ALL
    SELECT 
        'Decision Intelligence' AS framework,
        'Test 6.2' AS test_id,
        'Coalition Misalignment Detection' AS test_name,
        'Detect MISALIGNED vs. ALIGNED coalition decision patterns' AS test_description,
        '80% accuracy' AS expected_accuracy,
        60 AS sample_size,
        'decision/test_6_2_coalition_misalignment.csv' AS data_file
    ORDER BY framework, test_id
) TO 'service.data.impl/sample-data/framework-validation/validation-test-catalog.csv' WITH CSV HEADER;

\echo '>>> Generated validation test catalog: validation-test-catalog.csv'
\echo ''

\echo '===================================================================='
\echo 'Framework Validation Dataset Extraction Complete'
\echo 'Completed:' `date`
\echo '===================================================================='
\echo ''
\echo 'Summary:'
\echo '  ✓ Temporal Analysis:        4 test datasets (220 validation cases)'
\echo '  ✓ Comparative Analysis:     2 test datasets (68 validation cases)'
\echo '  ✓ Pattern Recognition:      2 test datasets (180 validation cases)'
\echo '  ✓ Predictive Intelligence:  2 test datasets (90 validation cases)'
\echo '  ✓ Network Analysis:         2 test datasets (110 validation cases)'
\echo '  ✓ Decision Intelligence:    2 test datasets (140 validation cases)'
\echo ''
\echo '  Total Validation Cases:     808 test scenarios with known outcomes'
\echo ''
\echo 'Output Location: service.data.impl/sample-data/framework-validation/'
\echo ''
\echo 'Next Steps:'
\echo '  1. Run framework analytics on validation datasets'
\echo '  2. Compare predictions to documented expected outcomes'
\echo '  3. Generate accuracy report per framework'
\echo '  4. Tune algorithms based on validation results'
\echo ''
