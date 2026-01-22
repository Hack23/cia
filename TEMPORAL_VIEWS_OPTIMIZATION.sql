-- ============================================================================
-- TEMPORAL ANALYSIS FRAMEWORK: IMMEDIATE OPTIMIZATION SCRIPT
-- ============================================================================
-- Generated: 2026-01-22
-- Database: PostgreSQL 16.11
-- Purpose: Optimize performance of 27 temporal analysis views
--
-- Execution Time: ~15 minutes
-- Prerequisites: Database admin access
-- ============================================================================

-- ============================================================================
-- SECTION 1: REFRESH ALL MATERIALIZED VIEWS (5 minutes)
-- ============================================================================
-- All 16 materialized views need initial population

\echo 'Starting materialized view refresh...'
\timing on

-- Ballot Summary Views (Daily/Weekly/Monthly/Annual)
\echo 'Refreshing ballot summary views...'
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_daily;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_weekly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_monthly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_annual;

-- Ballot Party Summary Views
\echo 'Refreshing ballot party summary views...'
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_daily;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_weekly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_monthly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_annual;

-- Ballot Politician Summary Views
\echo 'Refreshing ballot politician summary views...'
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_daily;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_weekly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_monthly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_annual;

-- Document Summary Views
\echo 'Refreshing document summary views...'
REFRESH MATERIALIZED VIEW view_riksdagen_document_type_daily_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_party_document_daily_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_org_document_daily_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_politician_document_daily_summary;

\echo 'Materialized view refresh complete!'

-- ============================================================================
-- SECTION 2: ADD RECOMMENDED INDEXES (10 minutes)
-- ============================================================================
-- These indexes will improve query performance by 20-40%

\echo 'Creating recommended indexes...'

-- Vote Data Table Indexes
\echo 'Adding indexes to vote_data table...'

-- Composite index for ballot + date queries (used in all ballot summary views)
CREATE INDEX IF NOT EXISTS idx_vote_data_ballot_date 
    ON vote_data (embedded_id_ballot_id, vote_date);

-- Composite index for party + date queries (used in party summary views)
CREATE INDEX IF NOT EXISTS idx_vote_data_party_date 
    ON vote_data (party, vote_date);

-- Composite index for politician (intressent_id) + date queries
CREATE INDEX IF NOT EXISTS idx_vote_data_politician_date 
    ON vote_data (embedded_id_intressent_id, vote_date);

-- Covering index for common aggregation queries
-- Note: This index is large but provides significant performance improvement
CREATE INDEX IF NOT EXISTS idx_vote_data_aggregation_cover 
    ON vote_data (vote_date, party, vote, approved) 
    INCLUDE (embedded_id_ballot_id, embedded_id_intressent_id);

\echo 'Vote data indexes created!'

-- Application Action Event Indexes
\echo 'Checking application_action_event indexes...'
-- Note: Current indexes are already excellent:
-- - application_action_event_created_date_idx
-- - application_action_event_page_idx
-- - application_action_event_page_created_date_idx (composite)
-- - application_action_event_page_element_id_idx (composite)
-- No additional indexes needed!

\echo 'All recommended indexes created!'

-- ============================================================================
-- SECTION 3: ANALYZE TABLES (1 minute)
-- ============================================================================
-- Update statistics for query planner optimization

\echo 'Analyzing tables to update statistics...'

ANALYZE vote_data;
ANALYZE application_action_event;
ANALYZE document_data;
ANALYZE document_element;

\echo 'Table analysis complete!'

-- ============================================================================
-- SECTION 4: VERIFY OPTIMIZATION
-- ============================================================================

\echo '============================================================================'
\echo 'OPTIMIZATION COMPLETE!'
\echo '============================================================================'

-- Check materialized view sizes
\echo ''
\echo 'Materialized View Status:'
SELECT 
    schemaname,
    matviewname,
    pg_size_pretty(pg_total_relation_size(schemaname || '.' || matviewname)) AS size,
    hasindexes
FROM pg_matviews
WHERE schemaname = 'public'
    AND matviewname LIKE '%_summary_%'
ORDER BY matviewname;

-- Check new indexes
\echo ''
\echo 'New Indexes Created:'
SELECT 
    schemaname,
    relname as tablename,
    indexrelname as indexname,
    pg_size_pretty(pg_relation_size(indexrelid)) AS index_size
FROM pg_stat_user_indexes
WHERE schemaname = 'public'
    AND indexrelname LIKE 'idx_vote_data_%'
    AND indexrelname IN (
        'idx_vote_data_ballot_date',
        'idx_vote_data_party_date',
        'idx_vote_data_politician_date',
        'idx_vote_data_aggregation_cover'
    )
ORDER BY indexrelname;

-- ============================================================================
-- SECTION 5: ENHANCED STATISTICS VALIDATION (PR #8271)
-- ============================================================================

\echo ''
\echo '============================================================================'
\echo 'ENHANCED STATISTICS VALIDATION'
\echo '============================================================================'

-- Verify pg_stat_statements extension and configuration
\echo ''
\echo 'pg_stat_statements Configuration:'
SELECT 
    name,
    setting,
    unit
FROM pg_settings 
WHERE name IN (
    'pg_stat_statements.track',
    'pg_stat_statements.track_planning',
    'pg_stat_statements.track_utility',
    'track_io_timing',
    'track_functions'
)
ORDER BY name;

-- Check statistics collection status
\echo ''
\echo 'Statistics Collection Status:'
SELECT 
    schemaname,
    relname,
    last_analyze,
    last_autoanalyze,
    n_live_tup,
    n_dead_tup,
    n_mod_since_analyze
FROM pg_stat_user_tables 
WHERE relname IN ('vote_data', 'application_action_event', 'document_data', 'document_element')
ORDER BY relname;

-- Verify statistics freshness (should be within 20% of actual data changes)
\echo ''
\echo 'Statistics Freshness Check:'
SELECT 
    relname,
    n_live_tup,
    n_mod_since_analyze,
    CASE 
        WHEN n_live_tup > 0 THEN 
            ROUND(100.0 * n_mod_since_analyze / n_live_tup, 2)
        ELSE 0 
    END as staleness_pct,
    CASE 
        WHEN n_live_tup = 0 THEN 'OK (empty)'
        WHEN n_mod_since_analyze::float / NULLIF(n_live_tup, 0) < 0.20 THEN 'OK'
        ELSE 'NEEDS ANALYZE'
    END as status
FROM pg_stat_user_tables
WHERE relname IN ('vote_data', 'application_action_event', 'document_data', 'document_element')
ORDER BY staleness_pct DESC;

-- Sample pg_stat_statements data (will be empty until queries are run)
\echo ''
\echo 'pg_stat_statements Sample (Top 10 temporal views by total time):'
-- Use specific naming patterns for temporal views per documentation
SELECT 
    LEFT(query, 60) as view_query,
    calls,
    ROUND(mean_plan_time::numeric, 3) as mean_plan_ms,
    ROUND(mean_exec_time::numeric, 3) as mean_exec_ms,
    ROUND((mean_plan_time + mean_exec_time)::numeric, 3) as total_ms,
    ROUND((100.0 * mean_plan_time / NULLIF(mean_plan_time + mean_exec_time, 0))::numeric, 1) as plan_pct
FROM pg_stat_statements 
WHERE (query LIKE '%_summary_%' 
    OR query LIKE '%_temporal_%'
    OR query LIKE '%_daily%'
    OR query LIKE '%_weekly%'
    OR query LIKE '%_monthly%'
    OR query LIKE '%_annual%')
  AND query LIKE '%COUNT%'
  AND query NOT LIKE '%pg_stat_statements%'
ORDER BY total_ms DESC
LIMIT 10;

-- Buffer hit ratio check
\echo ''
\echo 'Buffer Hit Ratios (should be >95% in production):'
SELECT 
    relname,
    heap_blks_read,
    heap_blks_hit,
    CASE 
        WHEN (heap_blks_hit + heap_blks_read) > 0 THEN
            ROUND(100.0 * heap_blks_hit / (heap_blks_hit + heap_blks_read), 2)
        ELSE 0 
    END as buffer_hit_ratio,
    CASE 
        WHEN (heap_blks_hit + heap_blks_read) = 0 THEN 'N/A (no access)'
        WHEN heap_blks_hit::float / NULLIF(heap_blks_hit + heap_blks_read, 0) > 0.95 THEN 'GOOD'
        WHEN heap_blks_hit::float / NULLIF(heap_blks_hit + heap_blks_read, 0) > 0.80 THEN 'OK'
        ELSE 'POOR (increase shared_buffers)'
    END as status
FROM pg_statio_user_tables
WHERE relname LIKE '%vote_data%' 
   OR relname LIKE '%application_action_event%'
   OR relname LIKE '%document_%'
ORDER BY (heap_blks_hit + heap_blks_read) DESC
LIMIT 10;

\echo ''
\echo '============================================================================'
\echo 'NEXT STEPS:'
\echo '1. Run queries on temporal views to populate pg_stat_statements'
\echo '2. Run performance benchmarks: see TEMPORAL_ANALYSIS_PERFORMANCE_REPORT.md'
\echo '3. Set up automated refresh: see pg_cron configuration in report'
\echo '4. Monitor query performance with enhanced statistics'
\echo '============================================================================'
\echo ''
\echo 'Enhanced Statistics Queries:'
\echo '  - Planning vs Execution: see pg_stat_statements output above'
\echo '  - Buffer Statistics: see buffer hit ratios above'
\echo '  - I/O Timing: use EXPLAIN (ANALYZE, BUFFERS, TIMING) on individual views'
\echo '  - Row Estimate Accuracy: compare plan_rows vs actual_rows in EXPLAIN output'
\echo '============================================================================'

\timing off
