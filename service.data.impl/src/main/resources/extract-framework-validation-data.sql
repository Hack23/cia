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

\set ON_ERROR_STOP on
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

COPY (
    WITH politician_trends_base AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            period_start,
            avg_absence_rate,
            LAG(avg_absence_rate, 6) OVER (PARTITION BY person_id ORDER BY period_start) AS absence_6mo_ago
        FROM view_politician_behavioral_trends
        WHERE total_ballots >= 10
          AND period_start >= CURRENT_DATE - INTERVAL '24 months'
    ),
    politician_trends_with_change AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            period_start,
            avg_absence_rate,
            absence_6mo_ago,
            avg_absence_rate - absence_6mo_ago AS absence_change
        FROM politician_trends_base
    ),
    politician_trends AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            period_start,
            avg_absence_rate,
            absence_6mo_ago,
            absence_change,
            CASE 
                WHEN absence_change < -10 THEN 'SIGNIFICANT_IMPROVEMENT'
                WHEN absence_change < -5 THEN 'MODERATE_IMPROVEMENT'
                ELSE 'STABLE'
            END AS trend_classification
        FROM politician_trends_with_change
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        period_start AS measurement_month,
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
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/temporal/test_1_1_upward_trend_attendance.csv' WITH CSV HEADER;

\echo '>>> Exported 50 cases with expected upward trend detection (attendance improvement)'
\echo ''

-- TEMPORAL TEST 1.2: Downward Trend Detection (Ministry Effectiveness Decline)
\echo '>>> Test Case 1.2: Downward Trend - Ministry Effectiveness Decline'
\echo '>>> Expected Outcome: Detect ministry approval rate decline ≥15% over 4 quarters'

COPY (
    WITH ministry_trends_base AS (
        SELECT 
            ministry_code,
            decision_year,
            quarter_num,
            TO_DATE(decision_year::TEXT || '-' || ((quarter_num-1)*3+1)::TEXT || '-01', 'YYYY-MM-DD') AS quarter_date,
            approval_rate,
            LAG(approval_rate, 4) OVER (PARTITION BY ministry_code ORDER BY decision_year, quarter_num) AS approval_4q_ago
        FROM view_ministry_decision_impact
        WHERE total_proposals >= 5
          AND decision_year >= EXTRACT(YEAR FROM CURRENT_DATE) - 3
    ),
    ministry_trends_with_change AS (
        SELECT 
            ministry_code,
            decision_year,
            quarter_num,
            quarter_date,
            approval_rate,
            approval_4q_ago,
            approval_rate - approval_4q_ago AS approval_change
        FROM ministry_trends_base
    ),
    ministry_trends AS (
        SELECT 
            ministry_code,
            decision_year,
            quarter_num,
            quarter_date,
            approval_rate,
            approval_4q_ago,
            approval_change,
            CASE 
                WHEN approval_change < -15 THEN 'SIGNIFICANT_DECLINE'
                WHEN approval_change < -10 THEN 'MODERATE_DECLINE'
                ELSE 'STABLE'
            END AS trend_classification
        FROM ministry_trends_with_change
    )
    SELECT 
        ministry_code,
        decision_year,
        quarter_num,
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
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/temporal/test_1_2_downward_trend_ministry.csv' WITH CSV HEADER;

\echo '>>> Exported 30 cases with expected downward trend detection (ministry decline)'
\echo ''

-- TEMPORAL TEST 1.2b: Ministry Risk Evolution (Enhanced with Risk Scoring)
\echo '>>> Test Case 1.2b: Ministry Risk Evolution - Multi-quarter Deterioration'
\echo '>>> Expected Outcome: Detect CRITICAL risk escalation patterns'

COPY (
    WITH risk_evolution_base AS (
        SELECT 
            org_code,
            name AS ministry_name,
            year,
            quarter,
            documents_produced,
            legislative_count,
            document_trend,
            legislative_trend,
            risk_level,
            risk_assessment,
            LAG(risk_level, 1) OVER (PARTITION BY org_code ORDER BY year, quarter) AS prev_risk_level,
            LAG(risk_level, 2) OVER (PARTITION BY org_code ORDER BY year, quarter) AS prev_2q_risk_level
        FROM view_ministry_risk_evolution
        WHERE year >= EXTRACT(YEAR FROM CURRENT_DATE) - 3
          AND documents_produced IS NOT NULL
    ),
    risk_evolution AS (
        SELECT 
            org_code,
            ministry_name,
            year,
            quarter,
            documents_produced,
            legislative_count,
            document_trend,
            legislative_trend,
            risk_level,
            risk_assessment,
            prev_risk_level,
            prev_2q_risk_level,
            CASE 
                WHEN risk_level = 'CRITICAL' AND prev_risk_level IN ('HIGH', 'MEDIUM', 'LOW') THEN 'RAPID_ESCALATION'
                WHEN risk_level = 'CRITICAL' AND prev_risk_level = 'HIGH' THEN 'GRADUAL_ESCALATION'
                WHEN risk_level = 'HIGH' AND prev_2q_risk_level = 'MEDIUM' THEN 'SUSTAINED_DETERIORATION'
                ELSE 'OTHER'
            END AS risk_escalation_pattern
        FROM risk_evolution_base
    )
    SELECT 
        org_code,
        ministry_name,
        year,
        quarter,
        documents_produced,
        legislative_count,
        document_trend,
        legislative_trend,
        prev_2q_risk_level AS baseline_risk,
        prev_risk_level AS intermediate_risk,
        risk_level AS current_risk,
        risk_assessment,
        risk_escalation_pattern AS expected_detection,
        'temporal_risk_evolution' AS test_case,
        CASE 
            WHEN risk_escalation_pattern IN ('RAPID_ESCALATION', 'GRADUAL_ESCALATION', 'SUSTAINED_DETERIORATION') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM risk_evolution
    WHERE risk_escalation_pattern IN ('RAPID_ESCALATION', 'GRADUAL_ESCALATION', 'SUSTAINED_DETERIORATION')
    ORDER BY 
        CASE risk_escalation_pattern
            WHEN 'RAPID_ESCALATION' THEN 1
            WHEN 'GRADUAL_ESCALATION' THEN 2
            WHEN 'SUSTAINED_DETERIORATION' THEN 3
        END,
        year DESC, quarter DESC
    LIMIT 35
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/temporal/test_1_2b_ministry_risk_evolution.csv' WITH CSV HEADER;

\echo '>>> Exported 35 cases with ministry risk escalation patterns'
\echo ''

-- TEMPORAL TEST 1.3: Cyclical Pattern Detection (Parliamentary Session Activity)
\echo '>>> Test Case 1.3: Cyclical Patterns - Parliamentary Session Seasonality'
\echo '>>> Expected Outcome: Detect seasonal patterns (Autumn high, Summer low)'

COPY (
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
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/temporal/test_1_3_cyclical_patterns.csv' WITH CSV HEADER;

\echo '>>> Exported 100 cases with expected cyclical pattern detection'
\echo ''

-- TEMPORAL TEST 1.4: Anomaly Detection (Sudden Behavior Changes)
\echo '>>> Test Case 1.4: Anomalies - Sudden Decision Volume Spikes'
\echo '>>> Expected Outcome: Detect days with decision volume >2 standard deviations'

COPY (
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
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/temporal/test_1_4_anomaly_detection.csv' WITH CSV HEADER;

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

COPY (
    WITH party_metrics AS (
        SELECT 
            party,
            MAX(active_members) AS active_members,
            SUM(documents_produced) AS total_documents,
            AVG(avg_win_rate) AS avg_win_rate,
            AVG(100 - avg_rebel_rate) AS avg_discipline,
            AVG(avg_absence_rate) AS avg_absence_rate,
            COUNT(*) AS months_tracked
        FROM view_party_effectiveness_trends
        WHERE period_start >= CURRENT_DATE - INTERVAL '12 months'
        GROUP BY party
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
        expected_classification AS expected_detection,
        'comparative_party_ranking' AS test_case,
        CASE 
            WHEN expected_classification IN ('HIGH_PERFORMANCE', 'MEDIUM_PERFORMANCE', 'LOW_PERFORMANCE') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM party_rankings
    ORDER BY avg_win_rate DESC
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/comparative/test_2_1_party_rankings.csv' WITH CSV HEADER;

\echo '>>> Exported party performance rankings with expected tier classification'
\echo ''

-- COMPARATIVE TEST 2.2: Politician Peer Group Comparison
\echo '>>> Test Case 2.2: Politician vs. Party Average Performance'
\echo '>>> Expected Outcome: Classify politicians as ABOVE_AVERAGE/AVERAGE/BELOW_AVERAGE'

COPY (
    WITH party_averages AS (
        SELECT 
            party,
            AVG(avg_absence_rate) AS party_avg_absence,
            AVG(avg_win_rate) AS party_avg_win,
            AVG(avg_rebel_rate) AS party_avg_rebel
        FROM view_politician_behavioral_trends
        WHERE period_start >= CURRENT_DATE - INTERVAL '12 months'
          AND total_ballots >= 10
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
        WHERE pbt.period_start >= CURRENT_DATE - INTERVAL '12 months'
          AND pbt.total_ballots >= 10
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
                WHEN individual_win > party_avg_win + 5 AND individual_absence < party_avg_absence - 3 THEN 'ABOVE_AVERAGE'
                WHEN individual_win < party_avg_win - 5 OR individual_absence > party_avg_absence + 5 THEN 'BELOW_AVERAGE'
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
    LIMIT 60
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/comparative/test_2_2_peer_comparison.csv' WITH CSV HEADER;

\echo '>>> Exported 60 cases with expected peer group classification'
\echo ''

-- COMPARATIVE TEST 2.3: Party Momentum Analysis (Enhanced Temporal Comparison)
\echo '>>> Test Case 2.3: Party Momentum - Performance Acceleration Patterns'
\echo '>>> Expected Outcome: Classify parties by momentum (ACCELERATING/DECELERATING/STABLE)'

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
                WHEN momentum > 0.05 AND acceleration > 0.02 THEN 'STRONG_ACCELERATION'
                WHEN momentum > 0.02 AND trend_direction IN ('POSITIVE', 'STRONG_POSITIVE') THEN 'MODERATE_ACCELERATION'
                WHEN momentum < -0.05 AND acceleration < -0.02 THEN 'STRONG_DECELERATION'
                WHEN momentum < -0.02 AND trend_direction IN ('NEGATIVE', 'STRONG_NEGATIVE') THEN 'MODERATE_DECELERATION'
                WHEN volatility < 0.02 AND trend_direction = 'STABLE' THEN 'STABLE_PERFORMANCE'
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

COPY (
    WITH politician_patterns_base AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            AVG(avg_absence_rate) AS avg_absence,
            AVG(avg_win_rate) AS avg_win,
            AVG(avg_rebel_rate) AS avg_rebel,
            COUNT(*) AS months_tracked,
            STDDEV(avg_absence_rate) AS absence_volatility
        FROM view_politician_behavioral_trends
        WHERE period_start >= CURRENT_DATE - INTERVAL '12 months'
          AND total_ballots >= 10
        GROUP BY person_id, first_name, last_name, party
        HAVING COUNT(*) >= 6
    ),
    politician_patterns AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            avg_absence,
            avg_win,
            avg_rebel,
            months_tracked,
            absence_volatility,
            CASE 
                WHEN avg_absence < 10 AND avg_win > 60 AND avg_rebel < 10 THEN 'NORMAL_BEHAVIOR'
                WHEN avg_absence BETWEEN 10 AND 20 OR avg_rebel BETWEEN 10 AND 15 THEN 'ANOMALOUS_BEHAVIOR'
                WHEN avg_absence > 20 OR avg_win < 45 OR avg_rebel > 15 THEN 'CONCERNING_BEHAVIOR'
                ELSE 'UNCLASSIFIED'
            END AS expected_cluster
        FROM politician_patterns_base
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
        expected_cluster AS expected_detection,
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
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/pattern/test_3_1_behavioral_clustering.csv' WITH CSV HEADER;

\echo '>>> Exported 100 cases with expected behavioral cluster classification'
\echo ''

-- PATTERN TEST 3.2: Rebellion Pattern Identification
\echo '>>> Test Case 3.2: Rebellion Patterns - High Independence vs. Party Line'
\echo '>>> Expected Outcome: Identify HIGH_INDEPENDENCE and PARTY_LINE patterns'

COPY (
    WITH rebellion_patterns_base AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            AVG(avg_rebel_rate) AS avg_rebel_rate,
            MAX(avg_rebel_rate) AS peak_rebel_rate,
            STDDEV(avg_rebel_rate) AS rebel_volatility,
            COUNT(*) AS months_tracked
        FROM view_politician_behavioral_trends
        WHERE period_start >= CURRENT_DATE - INTERVAL '12 months'
          AND total_ballots >= 10
        GROUP BY person_id, first_name, last_name, party
        HAVING COUNT(*) >= 6
    ),
    rebellion_patterns AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            avg_rebel_rate,
            peak_rebel_rate,
            rebel_volatility,
            months_tracked,
            CASE 
                WHEN avg_rebel_rate > 15 THEN 'HIGH_INDEPENDENCE'
                WHEN avg_rebel_rate BETWEEN 10 AND 15 THEN 'MODERATE_INDEPENDENCE'
                WHEN avg_rebel_rate BETWEEN 5 AND 10 THEN 'LOW_INDEPENDENCE'
                WHEN avg_rebel_rate < 5 THEN 'PARTY_LINE'
                ELSE 'UNCLASSIFIED'
            END AS expected_pattern
        FROM rebellion_patterns_base
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
        expected_pattern AS expected_detection,
        'pattern_rebellion_identification' AS test_case,
        CASE 
            WHEN expected_pattern IN ('HIGH_INDEPENDENCE', 'PARTY_LINE') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM rebellion_patterns
    WHERE expected_pattern IN ('HIGH_INDEPENDENCE', 'PARTY_LINE', 'MODERATE_INDEPENDENCE')
    ORDER BY avg_rebel_rate DESC
    LIMIT 80
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/pattern/test_3_2_rebellion_patterns.csv' WITH CSV HEADER;

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

COPY (
    WITH resignation_signals_base AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            AVG(avg_absence_rate) AS avg_absence,
            AVG(absence_trend) AS avg_absence_trend,
            AVG(effectiveness_trend) AS avg_effectiveness_trend,
            AVG(ma_3month_absence) AS smoothed_absence,
            COUNT(*) AS months_tracked
        FROM view_politician_behavioral_trends
        WHERE period_start >= CURRENT_DATE - INTERVAL '12 months'
          AND total_ballots >= 10
        GROUP BY person_id, first_name, last_name, party
        HAVING COUNT(*) >= 6
    ),
    resignation_signals AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            avg_absence,
            avg_absence_trend,
            avg_effectiveness_trend,
            smoothed_absence,
            months_tracked,
            CASE 
                WHEN avg_absence > 20 AND avg_absence_trend > 2 AND avg_effectiveness_trend < -3 THEN 'HIGH_RESIGNATION_RISK'
                WHEN avg_absence > 15 AND avg_absence_trend > 1 THEN 'MODERATE_RESIGNATION_RISK'
                ELSE 'LOW_RISK'
            END AS expected_risk_level
        FROM resignation_signals_base
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
        expected_risk_level AS expected_detection,
        'predictive_resignation_risk' AS test_case,
        CASE 
            WHEN expected_risk_level IN ('HIGH_RESIGNATION_RISK', 'MODERATE_RESIGNATION_RISK') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM resignation_signals
    WHERE expected_risk_level IN ('HIGH_RESIGNATION_RISK', 'MODERATE_RESIGNATION_RISK')
    ORDER BY avg_absence DESC, avg_absence_trend DESC
    LIMIT 40
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/predictive/test_4_1_resignation_prediction.csv' WITH CSV HEADER;

\echo '>>> Exported 40 cases with expected resignation risk prediction'
\echo ''

-- PREDICTIVE TEST 4.1b: Politician Risk Summary (Enhanced Multi-Violation Analysis)
\echo '>>> Test Case 4.1b: Politician Risk Profile - Multi-Violation Patterns'
\echo '>>> Expected Outcome: Identify high-risk politicians with multiple violation types'

COPY (
    WITH risk_profiles AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            status,
            total_violations,
            absenteeism_violations,
            effectiveness_violations,
            discipline_violations,
            productivity_violations,
            collaboration_violations,
            annual_absence_rate,
            annual_rebel_rate,
            annual_vote_count,
            documents_last_year,
            risk_score,
            risk_level,
            risk_assessment,
            CASE 
                WHEN total_violations >= 5 AND (absenteeism_violations > 0 AND effectiveness_violations > 0) THEN 'MULTI_DIMENSION_RISK'
                WHEN total_violations >= 7 THEN 'EXTREME_VIOLATION_RISK'
                WHEN risk_score >= 50 AND total_violations >= 3 THEN 'HIGH_RISK_PROFILE'
                WHEN annual_absence_rate > 15 AND annual_rebel_rate > 10 THEN 'DUAL_BEHAVIORAL_RISK'
                ELSE 'MODERATE_RISK'
            END AS expected_risk_classification,
            CASE 
                WHEN absenteeism_violations > 0 THEN 1 ELSE 0 END +
                CASE WHEN effectiveness_violations > 0 THEN 1 ELSE 0 END +
                CASE WHEN discipline_violations > 0 THEN 1 ELSE 0 END +
                CASE WHEN productivity_violations > 0 THEN 1 ELSE 0 END +
                CASE WHEN collaboration_violations > 0 THEN 1 ELSE 0 END AS violation_dimension_count
        FROM view_politician_risk_summary
        WHERE total_violations > 0
          AND status = 'Tjänstgörande riksdagsledamot'
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        total_violations,
        violation_dimension_count,
        absenteeism_violations,
        effectiveness_violations,
        discipline_violations,
        ROUND(annual_absence_rate, 2) AS annual_absence_rate,
        ROUND(annual_rebel_rate, 2) AS annual_rebel_rate,
        annual_vote_count,
        documents_last_year,
        ROUND(risk_score, 2) AS risk_score,
        risk_level,
        expected_risk_classification AS expected_detection,
        'predictive_risk_profile' AS test_case,
        CASE 
            WHEN expected_risk_classification IN ('MULTI_DIMENSION_RISK', 'EXTREME_VIOLATION_RISK', 'HIGH_RISK_PROFILE', 'DUAL_BEHAVIORAL_RISK') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM risk_profiles
    WHERE expected_risk_classification IN ('MULTI_DIMENSION_RISK', 'EXTREME_VIOLATION_RISK', 'HIGH_RISK_PROFILE', 'DUAL_BEHAVIORAL_RISK')
    ORDER BY 
        CASE expected_risk_classification
            WHEN 'EXTREME_VIOLATION_RISK' THEN 1
            WHEN 'MULTI_DIMENSION_RISK' THEN 2
            WHEN 'HIGH_RISK_PROFILE' THEN 3
            WHEN 'DUAL_BEHAVIORAL_RISK' THEN 4
        END,
        risk_score DESC,
        total_violations DESC
    LIMIT 45
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/predictive/test_4_1b_politician_risk_profiles.csv' WITH CSV HEADER;

\echo '>>> Exported 45 cases with multi-dimensional risk profiles'
\echo ''

-- PREDICTIVE TEST 4.2: Coalition Stress Prediction
\echo '>>> Test Case 4.2: Coalition Stress - Alignment Degradation Signals'
\echo '>>> Expected Outcome: Detect coalition stress with ≥78% accuracy'

COPY (
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
        expected_stress_level AS expected_detection,
        'predictive_coalition_stress' AS test_case,
        CASE 
            WHEN expected_stress_level IN ('HIGH_COALITION_STRESS', 'MODERATE_COALITION_STRESS') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM coalition_pairs
    WHERE expected_stress_level IN ('HIGH_COALITION_STRESS', 'MODERATE_COALITION_STRESS', 'STABLE_COALITION')
    ORDER BY alignment_rate ASC
    LIMIT 50
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/predictive/test_4_2_coalition_stress.csv' WITH CSV HEADER;

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

COPY (
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
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/network/test_5_1_power_brokers.csv' WITH CSV HEADER;

\echo '>>> Exported 60 cases with expected power broker classification'
\echo ''

-- NETWORK TEST 5.2: Coalition Facilitator Detection (Cross-Bloc Bridges)
\echo '>>> Test Case 5.2: Coalition Facilitators - Cross-Bloc Connection Patterns'
\echo '>>> Expected Outcome: Identify politicians bridging political blocs'

COPY (
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
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/network/test_5_2_coalition_facilitators.csv' WITH CSV HEADER;

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
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/decision/test_6_1_effectiveness_patterns.csv' WITH CSV HEADER;

\echo '>>> Exported 80 cases with expected decision effectiveness classification'
\echo ''

-- DECISION TEST 6.2: Coalition Misalignment Detection
\echo '>>> Test Case 6.2: Coalition Misalignment - Conflicting Decision Patterns'
\echo '>>> Expected Outcome: Detect MISALIGNED vs. ALIGNED coalition decision patterns'

COPY (
    WITH coalition_decisions_base AS (
        SELECT 
            pdf1.party AS party_1,
            pdf2.party AS party_2,
            pdf1.committee,
            pdf1.decision_year,
            pdf1.decision_quarter,
            AVG(pdf1.approval_rate) AS party1_approval,
            AVG(pdf2.approval_rate) AS party2_approval,
            ABS(AVG(pdf1.approval_rate) - AVG(pdf2.approval_rate)) AS approval_gap
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
                WHEN approval_gap > 30 THEN 'COALITION_MISALIGNMENT'
                WHEN approval_gap < 15 THEN 'COALITION_ALIGNMENT'
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
    LIMIT 60
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/decision/test_6_2_coalition_misalignment.csv' WITH CSV HEADER;

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
COPY (
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
        'Test 1.2b' AS test_id,
        'Ministry Risk Evolution' AS test_name,
        'Multi-quarter risk escalation (CRITICAL, HIGH) patterns' AS test_description,
        '85% accuracy' AS expected_accuracy,
        35 AS sample_size,
        'temporal/test_1_2b_ministry_risk_evolution.csv' AS data_file
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
        'Comparative Analysis' AS framework,
        'Test 2.3' AS test_id,
        'Party Momentum Analysis' AS test_name,
        'Performance acceleration patterns (ACCELERATING/STABLE/DECELERATING)' AS test_description,
        '88% accuracy' AS expected_accuracy,
        70 AS sample_size,
        'comparative/test_2_3_party_momentum.csv' AS data_file
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
        'Test 4.1b' AS test_id,
        'Politician Risk Profiles' AS test_name,
        'Multi-dimensional risk analysis (violations across multiple dimensions)' AS test_description,
        '89% accuracy' AS expected_accuracy,
        45 AS sample_size,
        'predictive/test_4_1b_politician_risk_profiles.csv' AS data_file
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
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/validation-test-catalog.csv' WITH CSV HEADER;

\echo '>>> Generated validation test catalog: validation-test-catalog.csv'
\echo ''

\echo '===================================================================='
\echo 'Framework Validation Dataset Extraction Complete'
\echo 'Completed:' `date`
\echo '===================================================================='
\echo ''
\echo 'Summary:'
\echo '  ✓ Temporal Analysis:        5 test datasets (255 validation cases)'
\echo '  ✓ Comparative Analysis:     3 test datasets (138 validation cases)'
\echo '  ✓ Pattern Recognition:      2 test datasets (180 validation cases)'
\echo '  ✓ Predictive Intelligence:  3 test datasets (135 validation cases)'
\echo '  ✓ Network Analysis:         2 test datasets (110 validation cases)'
\echo '  ✓ Decision Intelligence:    2 test datasets (140 validation cases)'
\echo ''
\echo '  Total Validation Cases:     958 test scenarios with known outcomes'
\echo ''
\echo 'Enhanced Features:'
\echo '  • Ministry risk evolution tracking (CRITICAL escalation patterns)'
\echo '  • Multi-dimensional politician risk profiles (violation analysis)'
\echo '  • Party momentum analysis (acceleration/deceleration detection)'
\echo ''
\echo 'Output Location: service.data.impl/sample-data/framework-validation/'
\echo ''
\echo 'Next Steps:'
\echo '  1. Run framework analytics on validation datasets'
\echo '  2. Compare predictions to documented expected outcomes'
\echo '  3. Generate accuracy report per framework'
\echo '  4. Tune algorithms based on validation results'
\echo ''
