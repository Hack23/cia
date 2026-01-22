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
    tablename,
    indexname,
    pg_size_pretty(pg_relation_size(indexrelid)) AS index_size
FROM pg_stat_user_indexes
WHERE schemaname = 'public'
    AND indexname LIKE 'idx_vote_data_%'
    AND indexname IN (
        'idx_vote_data_ballot_date',
        'idx_vote_data_party_date',
        'idx_vote_data_politician_date',
        'idx_vote_data_aggregation_cover'
    )
ORDER BY indexname;

\echo ''
\echo '============================================================================'
\echo 'NEXT STEPS:'
\echo '1. Run performance benchmarks: see TEMPORAL_ANALYSIS_PERFORMANCE_REPORT.md'
\echo '2. Set up automated refresh: see pg_cron configuration in report'
\echo '3. Monitor query performance: use pg_stat_statements'
\echo '============================================================================'

\timing off
