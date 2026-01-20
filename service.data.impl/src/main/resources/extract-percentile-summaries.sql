-- ===========================================================================
-- CIA Sample Data Extraction - Percentile Distribution Summaries (Phase 6.5)
-- ===========================================================================
-- Optional module for generating percentile-based distribution summaries
-- across analytical views with numerical columns.
--
-- This module can be included or excluded based on analysis requirements.
-- It generates P1, P10, P25, P50 (median), P75, P90, P99 for understanding
-- data distribution shape critical for Drools risk rule calibration.
--
-- Prerequisites:
--   - Helper functions must be loaded (extract-sample-data-functions.sql)
--   - cia_generate_distribution_summary() function must exist
--
-- Usage:
--   \i extract-percentile-summaries.sql
--
-- Output:
--   - 24 CSV files named percentile_*.csv
--   - Each contains: column_name, data_type, distinct_count, min, max, P1-P99
--
-- Version: 1.0.0
-- Last Updated: January 2026
-- ===========================================================================

\echo ''
\echo '=================================================='
\echo '=== PHASE 6.5: Percentile Distribution Summaries ==='
\echo '=================================================='
\echo ''
\echo 'Generating percentile-based distribution summaries for analytical views...'
\echo 'This captures P1, P10, P25, P50 (median), P75, P90, P99 for numerical columns'
\echo ''

-- ---------------------------------------------------------------------------
-- 6.5.1: Risk Assessment Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.1: Risk Assessment Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_politician_risk_summary')) TO 'percentile_politician_risk_summary.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_risk_summary.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_ministry_risk_evolution')) TO 'percentile_ministry_risk_evolution.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_ministry_risk_evolution.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_risk_score_evolution')) TO 'percentile_risk_score_evolution.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_risk_score_evolution.csv'

-- ---------------------------------------------------------------------------
-- 6.5.2: Performance & Productivity Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.2: Performance & Productivity Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_party_performance_metrics')) TO 'percentile_party_performance_metrics.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_party_performance_metrics.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_committee_productivity')) TO 'percentile_committee_productivity.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_committee_productivity.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_committee_productivity_matrix')) TO 'percentile_committee_productivity_matrix.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_committee_productivity_matrix.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_ministry_productivity_matrix')) TO 'percentile_ministry_productivity_matrix.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_ministry_productivity_matrix.csv'

-- ---------------------------------------------------------------------------
-- 6.5.3: Anomaly Detection Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.3: Anomaly Detection Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_voting_anomaly_detection')) TO 'percentile_voting_anomaly_detection.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_voting_anomaly_detection.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_seasonal_anomaly_detection')) TO 'percentile_seasonal_anomaly_detection.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_seasonal_anomaly_detection.csv'

-- ---------------------------------------------------------------------------
-- 6.5.4: Experience & Influence Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.4: Experience & Influence Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_experience_summary')) TO 'percentile_politician_experience_summary.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_experience_summary.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_influence_metrics')) TO 'percentile_politician_influence_metrics.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_influence_metrics.csv'

-- ---------------------------------------------------------------------------
-- 6.5.5: Behavioral & Decision Pattern Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.5: Behavioral & Decision Pattern Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_politician_behavioral_trends')) TO 'percentile_politician_behavioral_trends.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_behavioral_trends.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_decision_pattern')) TO 'percentile_politician_decision_pattern.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_decision_pattern.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_ministry_decision_impact')) TO 'percentile_ministry_decision_impact.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_ministry_decision_impact.csv'

-- ---------------------------------------------------------------------------
-- 6.5.6: Coalition & Momentum Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.6: Coalition & Momentum Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_party_momentum_analysis')) TO 'percentile_party_momentum_analysis.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_party_momentum_analysis.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_crisis_resilience_indicators')) TO 'percentile_crisis_resilience_indicators.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_crisis_resilience_indicators.csv'

-- ---------------------------------------------------------------------------
-- 6.5.7: Temporal Trend Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.7: Temporal Trend Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_election_proximity_trends')) TO 'percentile_election_proximity_trends.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_election_proximity_trends.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_seasonal_activity_patterns')) TO 'percentile_seasonal_activity_patterns.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_seasonal_activity_patterns.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_party_effectiveness_trends')) TO 'percentile_party_effectiveness_trends.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_party_effectiveness_trends.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_ministry_effectiveness_trend')) TO 'percentile_ministry_effectiveness_trend.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_ministry_effectiveness_trend.csv'

-- ---------------------------------------------------------------------------
-- 6.5.8: Career & Longevity Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.8: Career & Longevity Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_career_trajectory')) TO 'percentile_politician_career_trajectory.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_career_trajectory.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_longevity_analysis')) TO 'percentile_politician_longevity_analysis.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_longevity_analysis.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_role_evolution')) TO 'percentile_politician_role_evolution.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_role_evolution.csv'

\echo ''
\echo '=== PERCENTILE DISTRIBUTION SUMMARIES COMPLETE ===' 
\echo ''
\echo 'Generated percentile distributions for 24 analytical views'
\echo 'Each CSV contains: column_name, data_type, distinct_count, min, max, P1, P10, P25, median, P75, P90, P99'
\echo ''

\echo ''
\echo '=================================================='
\echo '=== PHASE 6.5 COMPLETE: Percentile Stats Done ==='
\echo '=================================================='
\echo ''
