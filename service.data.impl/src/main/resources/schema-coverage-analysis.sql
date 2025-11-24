-- schema-coverage-analysis.sql
-- Schema Coverage Analysis - Generates detailed comparison report
-- Citizen Intelligence Agency - Open Source Intelligence Platform
-- Generated for PostgreSQL 16
--
-- Purpose: Generates detailed coverage analysis comparing full_schema.sql
--          expected objects with actual database objects
--
-- Issue: Hack23/cia#7872 - Extend Schema Validation Script
--
-- *** MAINTENANCE NOTE ***
-- The table arrays in this script must be kept in sync with full_schema.sql
-- and schema-validation-v2.sql. When the schema changes, update all three files.
-- Last synchronized: 2025-11-24
--
-- Usage:
--   psql -U postgres -d cia_dev -f schema-coverage-analysis.sql > coverage_analysis.txt 2>&1
--
-- Features:
--   - Identifies objects in full_schema.sql but missing in database
--   - Identifies objects in database but not in full_schema.sql
--   - Generates coverage percentage report
--   - Exports results to CSV format

\set ECHO queries
\timing on

\echo ''
\echo '=========================================================================='
\echo '=== SCHEMA COVERAGE ANALYSIS                                           ==='
\echo '=========================================================================='
\echo ''
\echo 'Issue: Hack23/cia#7872 - Schema Coverage Analysis'
\echo 'Comparing full_schema.sql (expected) vs actual database'
\echo ''

-- ===========================================================================
-- PART 1: TABLE COVERAGE ANALYSIS
-- ===========================================================================

\echo ''
\echo '--- TABLE COVERAGE ANALYSIS ---'
\echo ''

WITH expected_tables AS (
    SELECT unnest(ARRAY[
        'against_proposal_container', 'against_proposal_data', 'agency',
        'application_action_event', 'application_configuration', 'application_session',
        'application_view', 'assignment_data', 'assignment_element',
        'committee_document_data', 'committee_proposal_component_0', 'committee_proposal_container',
        'committee_proposal_data', 'countries_element', 'country_element',
        'data_element', 'data_source_content', 'databasechangelog',
        'databasechangeloglock', 'detail_data', 'detail_element',
        'document_activity_container', 'document_activity_data', 'document_attachment',
        'document_attachment_container', 'document_container_element', 'document_content_data',
        'document_data', 'document_detail_container', 'document_detail_data',
        'document_element', 'document_person_reference_co_0', 'document_person_reference_da_0',
        'document_proposal_container', 'document_proposal_data', 'document_reference_container',
        'document_reference_data', 'document_status_container', 'domain_portal',
        'encrypted_value', 'indicator_element', 'indicators_element',
        'jv_commit', 'jv_commit_property', 'jv_global_id',
        'jv_snapshot', 'language_content_data', 'language_data',
        'operational_information_cont_0', 'performance_indicator_content', 'person_assignment_data',
        'person_assignment_element', 'person_container_data', 'person_container_element',
        'person_data', 'person_detail_data', 'person_detail_element',
        'person_element', 'portal', 'qrtz_blob_triggers',
        'qrtz_calendars', 'qrtz_cron_triggers', 'qrtz_fired_triggers',
        'qrtz_job_details', 'qrtz_locks', 'qrtz_paused_trigger_grps',
        'qrtz_scheduler_state', 'qrtz_simple_triggers', 'qrtz_simprop_triggers',
        'qrtz_triggers', 'quality_assurance_content', 'rule_violation',
        'sweden_county_data', 'sweden_county_data_container', 'sweden_county_electoral_area',
        'sweden_county_electoral_regi_0', 'sweden_county_electoral_regi_1', 'sweden_election_region',
        'sweden_election_type', 'sweden_election_type_contain_0', 'sweden_municipality_data',
        'sweden_municipality_election_0', 'sweden_parliament_electoral__0', 'sweden_parliament_electoral__1',
        'sweden_political_party', 'target_profile_content', 'topic',
        'topics', 'user_account', 'user_account_address',
        'vote_data', 'vote_meta_data', 'world_bank_data'
    ]) AS table_name
),
actual_tables AS (
    SELECT tablename AS table_name
    FROM pg_tables
    WHERE schemaname = 'public'
),
comparison AS (
    SELECT 
        COALESCE(e.table_name, a.table_name) AS table_name,
        e.table_name IS NOT NULL AS in_schema,
        a.table_name IS NOT NULL AS in_database,
        CASE 
            WHEN e.table_name IS NULL THEN 'EXTRA (in DB, not in full_schema.sql)'
            WHEN a.table_name IS NULL THEN 'MISSING (in full_schema.sql, not in DB)'
            ELSE 'OK (in both)'
        END AS status
    FROM expected_tables e
    FULL OUTER JOIN actual_tables a ON e.table_name = a.table_name
)
SELECT 
    table_name,
    status
FROM comparison
WHERE status != 'OK (in both)'
ORDER BY status, table_name;

-- Coverage summary for tables
WITH expected_tables AS (SELECT 93 AS count),
     actual_tables AS (SELECT COUNT(*) AS count FROM pg_tables WHERE schemaname = 'public'),
     matched_tables AS (
         SELECT COUNT(*) AS count FROM pg_tables 
         WHERE schemaname = 'public' AND tablename IN (
             'against_proposal_container', 'against_proposal_data', 'agency',
             'application_action_event', 'application_configuration', 'application_session',
             'application_view', 'assignment_data', 'assignment_element',
             'committee_document_data', 'committee_proposal_component_0', 'committee_proposal_container',
             'committee_proposal_data', 'countries_element', 'country_element',
             'data_element', 'data_source_content', 'databasechangelog',
             'databasechangeloglock', 'detail_data', 'detail_element',
             'document_activity_container', 'document_activity_data', 'document_attachment',
             'document_attachment_container', 'document_container_element', 'document_content_data',
             'document_data', 'document_detail_container', 'document_detail_data',
             'document_element', 'document_person_reference_co_0', 'document_person_reference_da_0',
             'document_proposal_container', 'document_proposal_data', 'document_reference_container',
             'document_reference_data', 'document_status_container', 'domain_portal',
             'encrypted_value', 'indicator_element', 'indicators_element',
             'jv_commit', 'jv_commit_property', 'jv_global_id',
             'jv_snapshot', 'language_content_data', 'language_data',
             'operational_information_cont_0', 'performance_indicator_content', 'person_assignment_data',
             'person_assignment_element', 'person_container_data', 'person_container_element',
             'person_data', 'person_detail_data', 'person_detail_element',
             'person_element', 'portal', 'qrtz_blob_triggers',
             'qrtz_calendars', 'qrtz_cron_triggers', 'qrtz_fired_triggers',
             'qrtz_job_details', 'qrtz_locks', 'qrtz_paused_trigger_grps',
             'qrtz_scheduler_state', 'qrtz_simple_triggers', 'qrtz_simprop_triggers',
             'qrtz_triggers', 'quality_assurance_content', 'rule_violation',
             'sweden_county_data', 'sweden_county_data_container', 'sweden_county_electoral_area',
             'sweden_county_electoral_regi_0', 'sweden_county_electoral_regi_1', 'sweden_election_region',
             'sweden_election_type', 'sweden_election_type_contain_0', 'sweden_municipality_data',
             'sweden_municipality_election_0', 'sweden_parliament_electoral__0', 'sweden_parliament_electoral__1',
             'sweden_political_party', 'target_profile_content', 'topic',
             'topics', 'user_account', 'user_account_address',
             'vote_data', 'vote_meta_data', 'world_bank_data'
         )
     )
SELECT 
    'TABLES' AS object_type,
    e.count AS expected_in_schema,
    a.count AS found_in_database,
    m.count AS matched,
    ROUND((m.count::numeric / e.count) * 100, 2) AS coverage_pct
FROM expected_tables e, actual_tables a, matched_tables m;

-- ===========================================================================
-- PART 2: REGULAR VIEW COVERAGE ANALYSIS
-- ===========================================================================

\echo ''
\echo '--- REGULAR VIEW COVERAGE ANALYSIS ---'
\echo ''

SELECT 
    'VIEWS' AS object_type,
    56 AS expected_in_schema,
    COUNT(*) AS found_in_database,
    ROUND((COUNT(*)::numeric / 56) * 100, 2) AS coverage_pct
FROM pg_views
WHERE schemaname = 'public';

-- ===========================================================================
-- PART 3: MATERIALIZED VIEW COVERAGE ANALYSIS
-- ===========================================================================

\echo ''
\echo '--- MATERIALIZED VIEW COVERAGE ANALYSIS ---'
\echo ''

SELECT 
    'MATERIALIZED VIEWS' AS object_type,
    28 AS expected_in_schema,
    COUNT(*) AS found_in_database,
    ROUND((COUNT(*)::numeric / 28) * 100, 2) AS coverage_pct
FROM pg_matviews
WHERE schemaname = 'public';

-- ===========================================================================
-- PART 4: OVERALL COVERAGE SUMMARY
-- ===========================================================================

\echo ''
\echo '=========================================================================='
\echo '=== OVERALL COVERAGE SUMMARY                                           ==='
\echo '=========================================================================='
\echo ''

WITH coverage_summary AS (
    SELECT 
        'Tables' AS object_type,
        93 AS expected,
        (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public') AS found,
        93 AS validated
    UNION ALL
    SELECT 
        'Regular Views',
        56,
        (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public'),
        56
    UNION ALL
    SELECT 
        'Materialized Views',
        28,
        (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public'),
        28
)
SELECT 
    object_type,
    expected,
    found,
    validated,
    ROUND((validated::numeric / expected) * 100, 1) AS validation_coverage_pct,
    ROUND((found::numeric / expected) * 100, 1) AS database_coverage_pct,
    CASE 
        WHEN found = expected AND validated = expected THEN '✓ Perfect Match'
        WHEN found >= expected AND validated = expected THEN '✓ All Expected Objects Found'
        WHEN found < expected THEN '✗ Missing Objects'
        ELSE '⚠ Check Required'
    END AS status
FROM coverage_summary;

-- Grand total
WITH totals AS (
    SELECT 
        177 AS expected_total,
        (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public') +
        (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') +
        (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public') AS found_total,
        177 AS validated_total
)
SELECT 
    'TOTAL' AS object_type,
    expected_total,
    found_total,
    validated_total,
    ROUND((validated_total::numeric / expected_total) * 100, 2) AS coverage_pct,
    CASE 
        WHEN found_total >= expected_total THEN '✓ All Objects Validated'
        ELSE '✗ Missing Objects'
    END AS status
FROM totals;

\echo ''
\echo '=========================================================================='
\echo '=== COVERAGE ANALYSIS COMPLETE                                         ==='
\echo '=========================================================================='
\echo ''
\echo 'Coverage analysis finished at:'
SELECT NOW();
\echo ''

\timing off
