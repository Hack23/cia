-- config.sql
-- Framework Validation Configuration Parameters
-- Extracted from hardcoded values across all test cases

-- Purpose: Centralized configuration for framework validation dataset extraction
-- Benefits: Easy tuning of parameters without modifying query logic
--           Consistent thresholds across all test cases
--           Single source of truth for configuration

\echo '>>> Loading framework validation configuration...'

-- Drop and recreate configuration table
DROP TABLE IF EXISTS framework_validation_config;

CREATE TEMPORARY TABLE framework_validation_config (
    config_key VARCHAR(100) PRIMARY KEY,
    config_value VARCHAR(100) NOT NULL,
    description TEXT
);

-- Sample Size Configuration
INSERT INTO framework_validation_config VALUES
    ('sample_size_default', '50', 'Default sample size for test cases'),
    ('sample_size_small', '30', 'Small sample size for focused tests'),
    ('sample_size_medium', '60', 'Medium sample size for comparative tests'),
    ('sample_size_large', '100', 'Large sample size for pattern detection'),
    ('sample_size_xlarge', '80', 'Extra large sample size for comprehensive tests');

-- Date Range Configuration (Temporal Analysis)
INSERT INTO framework_validation_config VALUES
    ('lookback_months_short', '6', 'Short-term lookback period (6 months)'),
    ('lookback_months_medium', '12', 'Medium-term lookback period (1 year)'),
    ('lookback_months_long', '24', 'Long-term lookback period (2 years)'),
    ('lookback_months_xllong', '36', 'Extra long lookback period (3 years)'),
    ('election_year_start', '2002', 'First election year in analysis range'),
    ('election_year_end', '2026', 'Last election year in analysis range');

-- Threshold Configuration (Trend Detection)
INSERT INTO framework_validation_config VALUES
    ('trend_significant_threshold', '10', 'Significant trend change threshold (%)'),
    ('trend_moderate_threshold', '5', 'Moderate trend change threshold (%)'),
    ('decline_significant_threshold', '15', 'Significant decline threshold (%)'),
    ('decline_moderate_threshold', '10', 'Moderate decline threshold (%)');

-- Performance Classification Thresholds
INSERT INTO framework_validation_config VALUES
    ('performance_high_threshold', '75', 'High performance threshold (%)'),
    ('performance_moderate_threshold', '50', 'Moderate performance threshold (%)'),
    ('discipline_high_threshold', '95', 'High discipline threshold (%)'),
    ('discipline_moderate_threshold', '85', 'Moderate discipline threshold (%)'),
    ('absence_high_risk_threshold', '50', 'High absence risk threshold (%)'),
    ('absence_moderate_risk_threshold', '30', 'Moderate absence risk threshold (%)');

-- Behavioral Pattern Thresholds
INSERT INTO framework_validation_config VALUES
    ('absence_concerning_threshold', '20', 'Concerning absence rate threshold (%)'),
    ('absence_anomalous_min', '10', 'Minimum anomalous absence rate (%)'),
    ('absence_anomalous_max', '20', 'Maximum anomalous absence rate (%)'),
    ('rebel_high_independence', '15', 'High independence (rebellion) threshold (%)'),
    ('rebel_moderate_independence', '10', 'Moderate independence threshold (%)'),
    ('rebel_low_independence', '5', 'Low independence threshold (%)');

-- Win Rate Thresholds
INSERT INTO framework_validation_config VALUES
    ('win_rate_high_threshold', '60', 'High win rate threshold (%)'),
    ('win_rate_low_threshold', '45', 'Low win rate threshold (%)'),
    ('win_gap_above_average', '5', 'Above average win rate gap (%)'),
    ('win_gap_below_average', '-5', 'Below average win rate gap (%)');

-- Minimum Data Quality Thresholds
INSERT INTO framework_validation_config VALUES
    ('min_votes_per_month', '10', 'Minimum votes per month for inclusion'),
    ('min_proposals_for_analysis', '5', 'Minimum proposals for ministry analysis'),
    ('min_months_tracked', '6', 'Minimum months tracked for trends'),
    ('min_shared_votes', '100', 'Minimum shared votes for coalition analysis');

-- Statistical Thresholds (Anomaly Detection)
INSERT INTO framework_validation_config VALUES
    ('zscore_extreme_threshold', '2.5', 'Extreme anomaly z-score threshold'),
    ('zscore_significant_threshold', '2.0', 'Significant anomaly z-score threshold'),
    ('zscore_moderate_threshold', '1.5', 'Moderate anomaly z-score threshold');

-- Momentum and Acceleration Thresholds
INSERT INTO framework_validation_config VALUES
    ('momentum_strong_threshold', '0.05', 'Strong momentum threshold'),
    ('momentum_moderate_threshold', '0.02', 'Moderate momentum threshold'),
    ('acceleration_threshold', '0.02', 'Acceleration/deceleration threshold'),
    ('volatility_stable_threshold', '0.02', 'Stable volatility threshold');

-- Coalition Alignment Thresholds
INSERT INTO framework_validation_config VALUES
    ('alignment_high_threshold', '90', 'High alignment threshold (%)'),
    ('alignment_moderate_threshold', '80', 'Moderate alignment threshold (%)'),
    ('alignment_stress_threshold', '60', 'Coalition stress threshold (%)'),
    ('alignment_low_threshold', '50', 'Low alignment threshold (%)'),
    ('alignment_gap_misalignment', '30', 'Misalignment gap threshold (%)'),
    ('alignment_gap_aligned', '15', 'Aligned gap threshold (%)');

-- Risk Scoring Thresholds
INSERT INTO framework_validation_config VALUES
    ('risk_score_critical', '50', 'Critical risk score threshold'),
    ('risk_violations_extreme', '7', 'Extreme violation count threshold'),
    ('risk_violations_multi', '5', 'Multi-dimensional risk threshold'),
    ('risk_violations_moderate', '3', 'Moderate risk violation threshold');

-- Output Path Configuration
INSERT INTO framework_validation_config VALUES
    ('output_base_path', '/workspaces/cia/service.data.impl/sample-data/framework-validation/', 
     'Base path for CSV output files');

-- Election Years Array (for cyclical pattern detection)
DROP TABLE IF EXISTS election_years;
CREATE TEMPORARY TABLE election_years (
    election_year INTEGER PRIMARY KEY,
    description VARCHAR(100)
);

INSERT INTO election_years VALUES
    (2002, 'Election year 2002'),
    (2006, 'Election year 2006'),
    (2010, 'Election year 2010'),
    (2014, 'Election year 2014'),
    (2018, 'Election year 2018'),
    (2022, 'Election year 2022'),
    (2026, 'Election year 2026 (projected)');

\echo '>>> Configuration loaded successfully'
\echo '>>> Configuration includes: sample sizes, date ranges, thresholds, election years'
\echo ''
