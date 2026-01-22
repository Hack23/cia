-- ===================================================================================
-- COMPREHENSIVE NETWORK ANALYSIS PERFORMANCE REPORT - SQL ANALYSIS
-- ===================================================================================
-- Generated: 2026-01-22
-- Purpose: Analyze 11 Network Analysis Framework views for performance optimization
-- Database: cia_dev (PostgreSQL 16.11 with pg_stat_statements)
-- ===================================================================================

\timing on
\x off

-- ===================================================================================
-- SECTION 1: CHECK pg_stat_statements AVAILABILITY
-- ===================================================================================
\echo ''
\echo '=== CHECKING pg_stat_statements EXTENSION ==='
SELECT 
    extname, 
    extversion,
    extrelocatable
FROM pg_extension 
WHERE extname = 'pg_stat_statements';

-- Reset stats for fresh analysis
SELECT pg_stat_statements_reset();

-- ===================================================================================
-- SECTION 2: VIEW INVENTORY AND DEPENDENCIES
-- ===================================================================================
\echo ''
\echo '=== NETWORK ANALYSIS VIEW INVENTORY ==='
SELECT 
    schemaname,
    viewname,
    viewowner,
    pg_size_pretty(pg_relation_size(schemaname||'.'||viewname)) as view_size
FROM pg_views 
WHERE viewname IN (
    'view_election_cycle_network_analysis',
    'view_riksdagen_committee_roles',
    'view_riksdagen_party_member',
    'view_riksdagen_goverment_role_member',
    'view_riksdagen_coalition_alignment_matrix',
    'view_riksdagen_party_coalation_against_annual_summary',
    'view_riksdagen_party_ballot_support_annual_summary',
    'view_riksdagen_party_coalition_evolution',
    'view_riksdagen_politician_influence_metrics',
    'view_riksdagen_party_decision_flow',
    'view_riksdagen_intelligence_dashboard'
)
ORDER BY viewname;

-- ===================================================================================
-- SECTION 3: INDEX ANALYSIS FOR NETWORK COLUMNS
-- ===================================================================================
\echo ''
\echo '=== INDEXES ON NETWORK-RELATED TABLES ==='
SELECT 
    schemaname,
    tablename,
    indexname,
    indexdef,
    pg_size_pretty(pg_relation_size(schemaname||'.'||indexname)) as index_size
FROM pg_indexes
WHERE tablename IN ('vote_data', 'document_person_reference_da_0', 'assignment_data', 
                    'document_proposal_data', 'document_status_container', 'person_data')
ORDER BY tablename, indexname;

-- ===================================================================================
-- SECTION 4: EXPLAIN ANALYSIS FOR VIEW 1 - view_riksdagen_party_decision_flow
-- ===================================================================================
\echo ''
\echo '=== VIEW 1: view_riksdagen_party_decision_flow ==='
\echo 'Query: SELECT * FROM view_riksdagen_party_decision_flow LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_riksdagen_party_decision_flow LIMIT 100;

-- ===================================================================================
-- SECTION 5: EXPLAIN ANALYSIS FOR VIEW 2 - view_riksdagen_coalition_alignment_matrix
-- ===================================================================================
\echo ''
\echo '=== VIEW 2: view_riksdagen_coalition_alignment_matrix ==='
\echo 'Query: SELECT * FROM view_riksdagen_coalition_alignment_matrix LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_riksdagen_coalition_alignment_matrix LIMIT 100;

-- ===================================================================================
-- SECTION 6: EXPLAIN ANALYSIS FOR VIEW 3 - view_riksdagen_politician_influence_metrics
-- ===================================================================================
\echo ''
\echo '=== VIEW 3: view_riksdagen_politician_influence_metrics ==='
\echo 'Query: SELECT * FROM view_riksdagen_politician_influence_metrics LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_riksdagen_politician_influence_metrics LIMIT 100;

-- ===================================================================================
-- SECTION 7: EXPLAIN ANALYSIS FOR VIEW 4 - view_election_cycle_network_analysis
-- ===================================================================================
\echo ''
\echo '=== VIEW 4: view_election_cycle_network_analysis ==='
\echo 'Query: SELECT * FROM view_election_cycle_network_analysis LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_election_cycle_network_analysis LIMIT 100;

-- ===================================================================================
-- SECTION 8: EXPLAIN ANALYSIS FOR VIEW 5 - view_riksdagen_committee_roles
-- ===================================================================================
\echo ''
\echo '=== VIEW 5: view_riksdagen_committee_roles ==='
\echo 'Query: SELECT * FROM view_riksdagen_committee_roles LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_riksdagen_committee_roles LIMIT 100;

-- ===================================================================================
-- SECTION 9: EXPLAIN ANALYSIS FOR VIEW 6 - view_riksdagen_party_member
-- ===================================================================================
\echo ''
\echo '=== VIEW 6: view_riksdagen_party_member ==='
\echo 'Query: SELECT * FROM view_riksdagen_party_member LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_riksdagen_party_member LIMIT 100;

-- ===================================================================================
-- SECTION 10: EXPLAIN ANALYSIS FOR VIEW 7 - view_riksdagen_goverment_role_member
-- ===================================================================================
\echo ''
\echo '=== VIEW 7: view_riksdagen_goverment_role_member ==='
\echo 'Query: SELECT * FROM view_riksdagen_goverment_role_member LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_riksdagen_goverment_role_member LIMIT 100;

-- ===================================================================================
-- SECTION 11: EXPLAIN ANALYSIS FOR VIEW 8 - view_riksdagen_party_coalation_against_annual_summary
-- ===================================================================================
\echo ''
\echo '=== VIEW 8: view_riksdagen_party_coalation_against_annual_summary ==='
\echo 'Query: SELECT * FROM view_riksdagen_party_coalation_against_annual_summary LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_riksdagen_party_coalation_against_annual_summary LIMIT 100;

-- ===================================================================================
-- SECTION 12: EXPLAIN ANALYSIS FOR VIEW 9 - view_riksdagen_party_ballot_support_annual_summary
-- ===================================================================================
\echo ''
\echo '=== VIEW 9: view_riksdagen_party_ballot_support_annual_summary ==='
\echo 'Query: SELECT * FROM view_riksdagen_party_ballot_support_annual_summary LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_riksdagen_party_ballot_support_annual_summary LIMIT 100;

-- ===================================================================================
-- SECTION 13: EXPLAIN ANALYSIS FOR VIEW 10 - view_riksdagen_party_coalition_evolution
-- ===================================================================================
\echo ''
\echo '=== VIEW 10: view_riksdagen_party_coalition_evolution ==='
\echo 'Query: SELECT * FROM view_riksdagen_party_coalition_evolution LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_riksdagen_party_coalition_evolution LIMIT 100;

-- ===================================================================================
-- SECTION 14: EXPLAIN ANALYSIS FOR VIEW 11 - view_riksdagen_intelligence_dashboard
-- ===================================================================================
\echo ''
\echo '=== VIEW 11: view_riksdagen_intelligence_dashboard ==='
\echo 'Query: SELECT * FROM view_riksdagen_intelligence_dashboard LIMIT 100'
EXPLAIN (ANALYZE, BUFFERS, VERBOSE)
SELECT * FROM view_riksdagen_intelligence_dashboard LIMIT 100;

-- ===================================================================================
-- SECTION 15: ROW COUNT ANALYSIS
-- ===================================================================================
\echo ''
\echo '=== ROW COUNT ANALYSIS FOR ALL VIEWS ==='
SELECT 'view_riksdagen_party_decision_flow' as view_name, COUNT(*) as row_count FROM view_riksdagen_party_decision_flow
UNION ALL
SELECT 'view_riksdagen_coalition_alignment_matrix', COUNT(*) FROM view_riksdagen_coalition_alignment_matrix
UNION ALL
SELECT 'view_riksdagen_politician_influence_metrics', COUNT(*) FROM view_riksdagen_politician_influence_metrics
UNION ALL
SELECT 'view_election_cycle_network_analysis', COUNT(*) FROM view_election_cycle_network_analysis
UNION ALL
SELECT 'view_riksdagen_committee_roles', COUNT(*) FROM view_riksdagen_committee_roles
UNION ALL
SELECT 'view_riksdagen_party_member', COUNT(*) FROM view_riksdagen_party_member
UNION ALL
SELECT 'view_riksdagen_goverment_role_member', COUNT(*) FROM view_riksdagen_goverment_role_member
UNION ALL
SELECT 'view_riksdagen_party_coalation_against_annual_summary', COUNT(*) FROM view_riksdagen_party_coalation_against_annual_summary
UNION ALL
SELECT 'view_riksdagen_party_ballot_support_annual_summary', COUNT(*) FROM view_riksdagen_party_ballot_support_annual_summary
UNION ALL
SELECT 'view_riksdagen_party_coalition_evolution', COUNT(*) FROM view_riksdagen_party_coalition_evolution
UNION ALL
SELECT 'view_riksdagen_intelligence_dashboard', COUNT(*) FROM view_riksdagen_intelligence_dashboard;

-- ===================================================================================
-- SECTION 16: pg_stat_statements SUMMARY
-- ===================================================================================
\echo ''
\echo '=== pg_stat_statements TOP QUERIES ==='
SELECT 
    substring(query, 1, 80) as query_snippet,
    calls,
    round(total_exec_time::numeric, 2) as total_time_ms,
    round(mean_exec_time::numeric, 2) as avg_time_ms,
    round(min_exec_time::numeric, 2) as min_time_ms,
    round(max_exec_time::numeric, 2) as max_time_ms,
    shared_blks_hit,
    shared_blks_read,
    shared_blks_written
FROM pg_stat_statements
WHERE query LIKE '%view_riksdagen%' OR query LIKE '%view_election%'
ORDER BY total_exec_time DESC
LIMIT 20;

-- ===================================================================================
-- SECTION 17: DEPENDENCY ANALYSIS
-- ===================================================================================
\echo ''
\echo '=== VIEW DEPENDENCIES ==='
SELECT 
    v.viewname as dependent_view,
    d.deptype as dependency_type,
    c.relname as depends_on_object,
    c.relkind as object_type
FROM pg_views v
JOIN pg_depend d ON d.objid = (v.schemaname||'.'||v.viewname)::regclass
JOIN pg_class c ON c.oid = d.refobjid
WHERE v.viewname IN (
    'view_election_cycle_network_analysis',
    'view_riksdagen_committee_roles',
    'view_riksdagen_party_member',
    'view_riksdagen_goverment_role_member',
    'view_riksdagen_coalition_alignment_matrix',
    'view_riksdagen_party_coalation_against_annual_summary',
    'view_riksdagen_party_ballot_support_annual_summary',
    'view_riksdagen_party_coalition_evolution',
    'view_riksdagen_politician_influence_metrics',
    'view_riksdagen_party_decision_flow',
    'view_riksdagen_intelligence_dashboard'
)
AND c.relname NOT LIKE 'pg_%'
ORDER BY v.viewname, c.relname;

-- ===================================================================================
-- SECTION 18: TABLE STATISTICS FOR UNDERLYING TABLES
-- ===================================================================================
\echo ''
\echo '=== TABLE STATISTICS ==='
SELECT 
    schemaname,
    tablename,
    n_live_tup as row_count,
    n_dead_tup as dead_rows,
    n_tup_ins as inserts,
    n_tup_upd as updates,
    n_tup_del as deletes,
    last_vacuum,
    last_autovacuum,
    last_analyze,
    last_autoanalyze
FROM pg_stat_user_tables
WHERE tablename IN ('vote_data', 'person_data', 'assignment_data', 
                    'document_proposal_data', 'document_status_container',
                    'document_data', 'document_person_reference_da_0')
ORDER BY tablename;

\echo ''
\echo '=== ANALYSIS COMPLETE ==='
