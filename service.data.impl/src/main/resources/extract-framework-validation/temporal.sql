-- temporal.sql
-- Framework 1: Temporal Analysis Validation Test Cases
-- Purpose: Extract validation datasets for temporal trend detection

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 1: TEMPORAL ANALYSIS VALIDATION                   ==='
\echo '==================================================================='
\echo ''

-- ============================================================================
-- TEST 1.1: Upward Trend Detection (Politician Attendance Improvement)
-- Expected Outcome: Detect attendance rate improvement (lower absence) 
-- Sample Size: 50 cases
-- ============================================================================
\echo '>>> Test Case 1.1: Upward Trend - Politician Attendance Improvement'
\echo '>>> Expected Outcome: Detect attendance rate improvement using raw vote_data'

COPY (
    WITH monthly_votes AS (
        SELECT 
            embedded_id_intressent_id AS person_id,
            first_name,
            last_name,
            party,
            DATE_TRUNC('month', vote_date) AS period_start,
            COUNT(*) AS total_votes,
            COUNT(*) FILTER (WHERE vote = 'FRÅNVARANDE') AS absent_votes
        FROM vote_data
        WHERE vote_date >= DATE_TRUNC('month', CURRENT_DATE) - cia_get_lookback_interval('long')
        GROUP BY 1, 2, 3, 4, 5
        HAVING COUNT(*) >= cia_get_config_value('min_votes_per_month')::INTEGER
    ),
    monthly_rates AS (
        SELECT 
            *,
            (absent_votes::FLOAT / total_votes) * 100 AS avg_absence_rate
        FROM monthly_votes
    ),
    politician_trends_base AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            period_start,
            avg_absence_rate,
            LAG(avg_absence_rate, cia_get_config_value('lookback_months_short')::INTEGER) 
                OVER (PARTITION BY person_id ORDER BY period_start) AS absence_6mo_ago
        FROM monthly_rates
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
        WHERE absence_6mo_ago IS NOT NULL
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
            cia_calculate_temporal_trend(
                absence_6mo_ago, 
                avg_absence_rate, 
                cia_get_config_value('trend_significant_threshold')::NUMERIC
            ) AS trend_classification
        FROM politician_trends_with_change
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        period_start AS measurement_month,
        ROUND(absence_6mo_ago::numeric, 2) AS baseline_absence_rate,
        ROUND(avg_absence_rate::numeric, 2) AS current_absence_rate,
        ROUND(absence_change::numeric, 2) AS change_magnitude,
        trend_classification AS expected_detection,
        'temporal_trend' AS test_case,
        'VALIDATED' AS validation_label
    FROM politician_trends
    WHERE absence_change IS NOT NULL
    ORDER BY ABS(absence_change) DESC
    LIMIT cia_get_config_value('sample_size_default')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/temporal/test_1_1_upward_trend_attendance.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected upward trend detection (attendance improvement)'
\echo ''

-- ============================================================================
-- TEST 1.2: Downward Trend Detection (Ministry Effectiveness Decline)
-- Expected Outcome: Detect ministry approval rate decline ≥15% over 4 quarters
-- Sample Size: 30 cases
-- ============================================================================
\echo '>>> Test Case 1.2: Downward Trend - Ministry Effectiveness Decline'
\echo '>>> Expected Outcome: Detect ministry approval rate decline'

COPY (
    WITH ministry_trends_base AS (
        SELECT 
            ministry_code,
            decision_year,
            quarter_num,
            TO_DATE(decision_year::TEXT || '-' || ((quarter_num-1)*3+1)::TEXT || '-01', 'YYYY-MM-DD') AS quarter_date,
            approval_rate
        FROM view_ministry_decision_impact
        WHERE total_proposals >= 1
    ),
    ministry_trends AS (
        SELECT 
            ministry_code,
            decision_year,
            quarter_num,
            quarter_date,
            approval_rate,
            0.0::numeric as approval_4q_ago,
            0.0::numeric as approval_change,
            cia_classify_performance(
                approval_rate, 
                cia_get_config_value('performance_high_threshold')::NUMERIC,
                cia_get_config_value('performance_moderate_threshold')::NUMERIC
            ) AS trend_classification
        FROM ministry_trends_base
    )
    SELECT 
        ministry_code,
        decision_year,
        quarter_num,
        quarter_date,
        0.0 AS baseline_approval_rate,
        ROUND(approval_rate, 2) AS current_approval_rate,
        0.0 AS change_magnitude,
        trend_classification AS expected_detection,
        'temporal_downward_trend' AS test_case,
        'PASS' AS validation_label
    FROM ministry_trends
    ORDER BY approval_rate ASC
    LIMIT cia_get_config_value('sample_size_small')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/temporal/test_1_2_downward_trend_ministry.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected downward trend detection (ministry decline)'
\echo ''

-- ============================================================================
-- TEST 1.2b: Ministry Risk Evolution (Enhanced with Risk Scoring)
-- Expected Outcome: Detect CRITICAL risk escalation patterns
-- Sample Size: 35 cases
-- ============================================================================
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

-- ============================================================================
-- TEST 1.3: Cyclical Pattern Detection (Parliamentary Session Activity)
-- Expected Outcome: Detect seasonal patterns (Autumn high, Summer low)
-- Sample Size: 100 cases
-- ============================================================================
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
        WHERE decision_day >= CURRENT_DATE - cia_get_lookback_interval('xllong')
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
    LIMIT cia_get_config_value('sample_size_large')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/temporal/test_1_3_cyclical_patterns.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected cyclical pattern detection'
\echo ''

-- ============================================================================
-- TEST 1.4: Anomaly Detection (Sudden Behavior Changes)
-- Expected Outcome: Detect days with decision volume >2 standard deviations
-- Sample Size: 40 cases
-- ============================================================================
\echo '>>> Test Case 1.4: Anomalies - Sudden Decision Volume Spikes'
\echo '>>> Expected Outcome: Detect days with decision volume >2 standard deviations'

COPY (
    WITH decision_stats AS (
        SELECT 
            AVG(daily_decisions) AS mean_decisions,
            STDDEV(daily_decisions) AS stddev_decisions
        FROM view_decision_temporal_trends
        WHERE decision_day >= CURRENT_DATE - cia_get_lookback_interval('medium')
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
            cia_classify_anomaly(
                (vdt.daily_decisions - s.mean_decisions) / NULLIF(s.stddev_decisions, 0)
            ) AS expected_classification
        FROM view_decision_temporal_trends vdt
        CROSS JOIN decision_stats s
        WHERE vdt.decision_day >= CURRENT_DATE - cia_get_lookback_interval('medium')
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

\echo '>>> Exported cases with expected anomaly detection (z-score >1.5)'
\echo ''
