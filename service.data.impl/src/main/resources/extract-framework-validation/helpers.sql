-- helpers.sql
-- Reusable Helper Functions for Framework Validation
-- Purpose: DRY principle - eliminate duplicate logic across test cases

\echo '>>> Loading helper functions...'

-- ============================================================================
-- FUNCTION: cia_get_config_value
-- Purpose: Retrieve configuration value by key
-- Parameters: 
--   p_key - Configuration key name
-- Returns: VARCHAR configuration value
-- Usage: SELECT cia_get_config_value('sample_size_default')::INTEGER;
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_get_config_value(p_key VARCHAR) 
RETURNS VARCHAR AS $$
DECLARE
    v_value VARCHAR;
BEGIN
    SELECT config_value INTO v_value
    FROM framework_validation_config
    WHERE config_key = p_key;
    
    IF v_value IS NULL THEN
        RAISE NOTICE 'Configuration key not found: %. Using empty string.', p_key;
        RETURN '';
    END IF;
    
    RETURN v_value;
END;
$$ LANGUAGE plpgsql STABLE;

-- ============================================================================
-- FUNCTION: cia_get_election_years
-- Purpose: Returns array of election years for temporal analysis
-- Returns: INTEGER[] array of election years
-- Usage: SELECT * FROM UNNEST(cia_get_election_years()) AS election_year;
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_get_election_years()
RETURNS INTEGER[] AS $$
BEGIN
    RETURN ARRAY[2002, 2006, 2010, 2014, 2018, 2022, 2026];
END;
$$ LANGUAGE plpgsql IMMUTABLE;

-- ============================================================================
-- FUNCTION: cia_calculate_temporal_trend
-- Purpose: Classify temporal trend based on baseline and current values
-- Parameters:
--   p_baseline - Baseline value (earlier period)
--   p_current - Current value (later period)
--   p_threshold - Threshold for significant change (default 10)
-- Returns: VARCHAR trend classification
-- Classifications:
--   SIGNIFICANT_IMPROVEMENT: decrease >= p_threshold (for negative metrics like absence)
--   MODERATE_IMPROVEMENT: decrease >= p_threshold/2
--   SIGNIFICANT_DECLINE: increase >= p_threshold
--   MODERATE_DECLINE: increase >= p_threshold/2
--   STABLE: change < p_threshold/2
-- Usage: SELECT cia_calculate_temporal_trend(30.5, 15.2, 10.0);
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_calculate_temporal_trend(
    p_baseline NUMERIC,
    p_current NUMERIC,
    p_threshold NUMERIC DEFAULT 10.0
)
RETURNS VARCHAR AS $$
DECLARE
    v_change NUMERIC;
BEGIN
    -- Handle NULL values
    IF p_baseline IS NULL OR p_current IS NULL THEN
        RETURN 'INSUFFICIENT_DATA';
    END IF;
    
    v_change := p_current - p_baseline;
    
    -- For negative metrics (absence, decline), lower is better
    IF v_change < -p_threshold THEN
        RETURN 'SIGNIFICANT_IMPROVEMENT';
    ELSIF v_change < -(p_threshold / 2) THEN
        RETURN 'MODERATE_IMPROVEMENT';
    ELSIF v_change > p_threshold THEN
        RETURN 'SIGNIFICANT_DECLINE';
    ELSIF v_change > (p_threshold / 2) THEN
        RETURN 'MODERATE_DECLINE';
    ELSE
        RETURN 'STABLE';
    END IF;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

-- ============================================================================
-- FUNCTION: cia_classify_performance
-- Purpose: Classify performance into tiers based on value and thresholds
-- Parameters:
--   p_value - Performance metric value
--   p_high_threshold - Threshold for high performance (default 75)
--   p_moderate_threshold - Threshold for moderate performance (default 50)
-- Returns: VARCHAR performance tier
-- Classifications: HIGH_PERFORMANCE, MEDIUM_PERFORMANCE, LOW_PERFORMANCE
-- Usage: SELECT cia_classify_performance(85.5, 75.0, 50.0);
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_classify_performance(
    p_value NUMERIC,
    p_high_threshold NUMERIC DEFAULT 75.0,
    p_moderate_threshold NUMERIC DEFAULT 50.0
)
RETURNS VARCHAR AS $$
BEGIN
    IF p_value IS NULL THEN
        RETURN 'INSUFFICIENT_DATA';
    END IF;
    
    IF p_value >= p_high_threshold THEN
        RETURN 'HIGH_PERFORMANCE';
    ELSIF p_value >= p_moderate_threshold THEN
        RETURN 'MEDIUM_PERFORMANCE';
    ELSE
        RETURN 'LOW_PERFORMANCE';
    END IF;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

-- ============================================================================
-- FUNCTION: cia_classify_behavioral_cluster
-- Purpose: Classify politician behavioral patterns into clusters
-- Parameters:
--   p_absence_rate - Average absence rate (%)
--   p_win_rate - Average win rate (%)
--   p_rebel_rate - Average rebellion rate (%)
-- Returns: VARCHAR cluster classification
-- Classifications: NORMAL_BEHAVIOR, ANOMALOUS_BEHAVIOR, CONCERNING_BEHAVIOR
-- Usage: SELECT cia_classify_behavioral_cluster(8.5, 65.0, 7.0);
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_classify_behavioral_cluster(
    p_absence_rate NUMERIC,
    p_win_rate NUMERIC,
    p_rebel_rate NUMERIC
)
RETURNS VARCHAR AS $$
DECLARE
    v_absence_concerning NUMERIC := cia_get_config_value('absence_concerning_threshold')::NUMERIC;
    v_absence_anomalous_min NUMERIC := cia_get_config_value('absence_anomalous_min')::NUMERIC;
    v_win_low NUMERIC := cia_get_config_value('win_rate_low_threshold')::NUMERIC;
    v_rebel_high NUMERIC := cia_get_config_value('rebel_high_independence')::NUMERIC;
    v_rebel_moderate NUMERIC := cia_get_config_value('rebel_moderate_independence')::NUMERIC;
BEGIN
    -- Concerning: high absence OR low win OR high rebellion
    IF p_absence_rate > v_absence_concerning 
       OR p_win_rate < v_win_low 
       OR p_rebel_rate > v_rebel_high THEN
        RETURN 'CONCERNING_BEHAVIOR';
    END IF;
    
    -- Anomalous: moderate absence OR moderate rebellion
    IF (p_absence_rate BETWEEN v_absence_anomalous_min AND v_absence_concerning)
       OR (p_rebel_rate BETWEEN v_rebel_moderate AND v_rebel_high) THEN
        RETURN 'ANOMALOUS_BEHAVIOR';
    END IF;
    
    -- Normal: good metrics across the board
    RETURN 'NORMAL_BEHAVIOR';
END;
$$ LANGUAGE plpgsql STABLE;

-- ============================================================================
-- FUNCTION: cia_classify_risk_level
-- Purpose: Classify resignation/performance risk based on absence and trend
-- Parameters:
--   p_recent_absence - Recent absence rate (%)
--   p_absence_trend - Change in absence rate
-- Returns: VARCHAR risk classification
-- Classifications: HIGH_RESIGNATION_RISK, MODERATE_RESIGNATION_RISK, LOW_RISK
-- Usage: SELECT cia_classify_risk_level(55.0, 12.5);
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_classify_risk_level(
    p_recent_absence NUMERIC,
    p_absence_trend NUMERIC
)
RETURNS VARCHAR AS $$
DECLARE
    v_high_risk NUMERIC := cia_get_config_value('absence_high_risk_threshold')::NUMERIC;
    v_moderate_risk NUMERIC := cia_get_config_value('absence_moderate_risk_threshold')::NUMERIC;
    v_trend_threshold NUMERIC := cia_get_config_value('trend_moderate_threshold')::NUMERIC;
BEGIN
    IF p_recent_absence > v_high_risk THEN
        RETURN 'HIGH_RESIGNATION_RISK';
    ELSIF p_recent_absence > v_moderate_risk 
          AND p_absence_trend > v_trend_threshold THEN
        RETURN 'MODERATE_RESIGNATION_RISK';
    ELSE
        RETURN 'LOW_RISK';
    END IF;
END;
$$ LANGUAGE plpgsql STABLE;

-- ============================================================================
-- FUNCTION: cia_classify_rebellion_pattern
-- Purpose: Classify rebellion/independence pattern
-- Parameters:
--   p_rebel_rate - Average rebellion rate (%)
-- Returns: VARCHAR pattern classification
-- Classifications: HIGH_INDEPENDENCE, MODERATE_INDEPENDENCE, 
--                 LOW_INDEPENDENCE, PARTY_LINE
-- Usage: SELECT cia_classify_rebellion_pattern(17.5);
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_classify_rebellion_pattern(p_rebel_rate NUMERIC)
RETURNS VARCHAR AS $$
DECLARE
    v_high NUMERIC := cia_get_config_value('rebel_high_independence')::NUMERIC;
    v_moderate NUMERIC := cia_get_config_value('rebel_moderate_independence')::NUMERIC;
    v_low NUMERIC := cia_get_config_value('rebel_low_independence')::NUMERIC;
BEGIN
    IF p_rebel_rate > v_high THEN
        RETURN 'HIGH_INDEPENDENCE';
    ELSIF p_rebel_rate BETWEEN v_moderate AND v_high THEN
        RETURN 'MODERATE_INDEPENDENCE';
    ELSIF p_rebel_rate BETWEEN v_low AND v_moderate THEN
        RETURN 'LOW_INDEPENDENCE';
    ELSE
        RETURN 'PARTY_LINE';
    END IF;
END;
$$ LANGUAGE plpgsql STABLE;

-- ============================================================================
-- FUNCTION: cia_classify_anomaly
-- Purpose: Classify anomaly based on z-score
-- Parameters:
--   p_zscore - Z-score value
-- Returns: VARCHAR anomaly classification
-- Classifications: EXTREME_ANOMALY, SIGNIFICANT_ANOMALY, 
--                 MODERATE_ANOMALY, NORMAL
-- Usage: SELECT cia_classify_anomaly(2.8);
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_classify_anomaly(p_zscore NUMERIC)
RETURNS VARCHAR AS $$
DECLARE
    v_extreme NUMERIC := cia_get_config_value('zscore_extreme_threshold')::NUMERIC;
    v_significant NUMERIC := cia_get_config_value('zscore_significant_threshold')::NUMERIC;
    v_moderate NUMERIC := cia_get_config_value('zscore_moderate_threshold')::NUMERIC;
BEGIN
    IF ABS(p_zscore) > v_extreme THEN
        RETURN 'EXTREME_ANOMALY';
    ELSIF ABS(p_zscore) > v_significant THEN
        RETURN 'SIGNIFICANT_ANOMALY';
    ELSIF ABS(p_zscore) > v_moderate THEN
        RETURN 'MODERATE_ANOMALY';
    ELSE
        RETURN 'NORMAL';
    END IF;
END;
$$ LANGUAGE plpgsql STABLE;

-- ============================================================================
-- FUNCTION: cia_classify_coalition_alignment
-- Purpose: Classify coalition alignment based on alignment rate
-- Parameters:
--   p_alignment_rate - Alignment rate between parties (%)
-- Returns: VARCHAR alignment classification
-- Classifications: HIGH_COALITION_STRESS, MODERATE_COALITION_STRESS, NORMAL
-- Usage: SELECT cia_classify_coalition_alignment(55.0);
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_classify_coalition_alignment(p_alignment_rate NUMERIC)
RETURNS VARCHAR AS $$
DECLARE
    v_stress NUMERIC := cia_get_config_value('alignment_stress_threshold')::NUMERIC;
    v_moderate NUMERIC := cia_get_config_value('alignment_moderate_threshold')::NUMERIC;
BEGIN
    IF p_alignment_rate < v_stress THEN
        RETURN 'HIGH_COALITION_STRESS';
    ELSIF p_alignment_rate < v_moderate THEN
        RETURN 'MODERATE_COALITION_STRESS';
    ELSE
        RETURN 'NORMAL';
    END IF;
END;
$$ LANGUAGE plpgsql STABLE;

-- ============================================================================
-- FUNCTION: cia_get_lookback_interval
-- Purpose: Get lookback interval for temporal queries
-- Parameters:
--   p_period - Period type (short/medium/long/xllong)
-- Returns: INTERVAL for date calculations
-- Usage: SELECT CURRENT_DATE - cia_get_lookback_interval('medium');
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_get_lookback_interval(p_period VARCHAR)
RETURNS INTERVAL AS $$
DECLARE
    v_months INTEGER;
BEGIN
    CASE p_period
        WHEN 'short' THEN
            v_months := cia_get_config_value('lookback_months_short')::INTEGER;
        WHEN 'medium' THEN
            v_months := cia_get_config_value('lookback_months_medium')::INTEGER;
        WHEN 'long' THEN
            v_months := cia_get_config_value('lookback_months_long')::INTEGER;
        WHEN 'xllong' THEN
            v_months := cia_get_config_value('lookback_months_xllong')::INTEGER;
        ELSE
            v_months := 12; -- Default to 12 months
    END CASE;
    
    RETURN (v_months || ' months')::INTERVAL;
END;
$$ LANGUAGE plpgsql STABLE;

-- ============================================================================
-- FUNCTION: cia_validate_result_count
-- Purpose: Validate that result set is not empty before export
-- Parameters:
--   p_count - Number of rows in result set
--   p_test_name - Test case name for logging
-- Returns: BOOLEAN (TRUE if valid, raises notice if invalid)
-- Usage: PERFORM cia_validate_result_count(50, 'Test 1.1');
-- ============================================================================
CREATE OR REPLACE FUNCTION cia_validate_result_count(
    p_count INTEGER,
    p_test_name VARCHAR
)
RETURNS BOOLEAN AS $$
BEGIN
    IF p_count = 0 THEN
        RAISE NOTICE 'WARNING: % returned 0 rows. CSV export may be empty.', p_test_name;
        RETURN FALSE;
    ELSIF p_count < 10 THEN
        RAISE NOTICE 'NOTICE: % returned only % rows (expected more).', p_test_name, p_count;
        RETURN TRUE;
    ELSE
        RAISE NOTICE 'SUCCESS: % generated % validation cases.', p_test_name, p_count;
        RETURN TRUE;
    END IF;
END;
$$ LANGUAGE plpgsql;

\echo '>>> Helper functions loaded successfully'
\echo '>>> Available functions:'
\echo '>>>   - cia_get_config_value(key) - Retrieve configuration'
\echo '>>>   - cia_get_election_years() - Get election year array'
\echo '>>>   - cia_calculate_temporal_trend(baseline, current, threshold) - Trend classification'
\echo '>>>   - cia_classify_performance(value, high, moderate) - Performance tier'
\echo '>>>   - cia_classify_behavioral_cluster(absence, win, rebel) - Behavior pattern'
\echo '>>>   - cia_classify_risk_level(absence, trend) - Risk classification'
\echo '>>>   - cia_classify_rebellion_pattern(rebel_rate) - Rebellion pattern'
\echo '>>>   - cia_classify_anomaly(zscore) - Anomaly detection'
\echo '>>>   - cia_classify_coalition_alignment(rate) - Coalition stress'
\echo '>>>   - cia_get_lookback_interval(period) - Date interval'
\echo '>>>   - cia_validate_result_count(count, name) - Result validation'
\echo ''
