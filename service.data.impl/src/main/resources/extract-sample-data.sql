-- extract-sample-data.sql
-- Sample Data Extraction for View Debugging and Testing
-- Citizen Intelligence Agency - Open Source Intelligence Platform
--
-- Purpose: Extracts sample data from all tables and views to CSV files
--          focusing on columns used in views for debugging empty views
--
-- Usage:
--   # Extract all sample data to CSV files
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-sample-data.sql
--
--   # Or extract to specific directory
--   cd /output/directory
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-sample-data.sql
--
-- Output:
--   - Creates CSV files for each table: table_<tablename>_sample.csv
--   - Creates CSV files for each view: view_<viewname>_sample.csv
--   - Creates manifest file: sample_data_manifest.csv
--   - Creates distinct value files: distinct_<table>_<column>_values.csv

\set ON_ERROR_STOP off
\timing on
\set VERBOSITY verbose

\echo '=================================================='
\echo 'CIA Sample Data Extraction'
\echo 'Started:' `date`
\echo '=================================================='

-- ============================================================================
-- CONFIGURATION - Sample Size Settings
-- ============================================================================
-- Default sample size for most tables/views (increased from 50 for better coverage)
\set SAMPLE_SIZE 200

-- Threshold for complete extraction (tables/views under this size extracted fully)
\set COMPLETE_EXTRACTION_THRESHOLD 3000

-- Extended sample sizes for important entity types
\set PARTY_SAMPLE_SIZE 500
\set COMMITTEE_SAMPLE_SIZE 500  
\set PERSON_SAMPLE_SIZE 500

-- Extended sample sizes for document and voting analysis  
\set DOCUMENT_SAMPLE_SIZE 300
\set VOTING_SAMPLE_SIZE 300
\set WORLDBANK_SAMPLE_SIZE 300

\set TABLE_CMD_FILE '/tmp/cia_table_extract_commands.sql'
\set VIEW_CMD_FILE '/tmp/cia_view_extract_commands.sql'
\set DISTINCT_CMD_FILE '/tmp/cia_distinct_extract_commands.sql'

\echo ''
\echo '=================================================='
\echo '=== INITIALIZATION                            ==='
\echo '=================================================='

\echo ''
\echo 'Configuration:'
\echo '  Default sample size: 200 rows'
\echo '  Party/Committee/Person sample size: 500 rows'
\echo '  Document/Voting/Worldbank sample size: 300 rows'
\echo '  Complete extraction threshold: 3000 rows (smaller datasets extracted fully)'
\echo '  Output format: CSV with headers'
\echo ''

DROP FUNCTION IF EXISTS cia_tmp_rowcount(text, text);
CREATE OR REPLACE FUNCTION cia_tmp_rowcount(schema_name text, rel_name text)
RETURNS bigint
LANGUAGE plpgsql
AS $$
DECLARE
    result bigint;
BEGIN
    EXECUTE format('SELECT COUNT(*) FROM %I.%I', schema_name, rel_name) INTO result;
    RETURN COALESCE(result, 0);
EXCEPTION WHEN OTHERS THEN
    RAISE NOTICE 'ERROR counting rows in %.%: %', schema_name, rel_name, SQLERRM;
    RETURN 0;
END;
$$;

\echo 'Created helper function: cia_tmp_rowcount'
\echo ''

-- ===========================================================================
-- SECTION 0.1: CREATE HELPER FUNCTIONS
-- ===========================================================================
-- Create reusable function for temporal view classification
-- This eliminates code duplication and ensures consistency
-- ===========================================================================

DROP FUNCTION IF EXISTS cia_classify_temporal_view(text);
CREATE OR REPLACE FUNCTION cia_classify_temporal_view(p_viewname text)
RETURNS TABLE (temporal_granularity text, samples_per_bucket integer)
LANGUAGE sql
IMMUTABLE
AS $$
    SELECT
        CASE 
            -- Daily granularity: 2 samples per day over last 30 days (60 samples max)
            WHEN p_viewname LIKE '%_daily%' THEN 'daily'
            -- Weekly granularity: 2 samples per week over last 6 months (52 samples max)
            WHEN p_viewname LIKE '%_weekly%' THEN 'weekly'
            -- Monthly granularity: 2 samples per month over last 3 years (72 samples max)
            WHEN p_viewname LIKE '%_monthly%' THEN 'monthly'
            -- Annual granularity: 2 samples per year over full history
            WHEN p_viewname LIKE '%_annual%' THEN 'annual'
            -- Temporal trend views: mixed strategy (all available time periods)
            WHEN p_viewname LIKE '%_temporal%' OR p_viewname LIKE '%_trend%' 
                 OR p_viewname LIKE '%_evolution%' OR p_viewname LIKE '%_momentum%' THEN 'temporal_trend'
            -- Non-temporal views: use random sampling
            ELSE 'non_temporal'
        END AS temporal_granularity,
        CASE 
            WHEN p_viewname LIKE '%_daily%' THEN 2
            WHEN p_viewname LIKE '%_weekly%' THEN 2
            WHEN p_viewname LIKE '%_monthly%' THEN 2
            WHEN p_viewname LIKE '%_annual%' THEN 2
            WHEN p_viewname LIKE '%_temporal%' OR p_viewname LIKE '%_trend%' 
                 OR p_viewname LIKE '%_evolution%' OR p_viewname LIKE '%_momentum%' THEN 1
            ELSE 0
        END AS samples_per_bucket;
$$;

\echo 'Created helper function: cia_classify_temporal_view'
\echo ''

-- ===========================================================================
-- SECTION 0.2: REFRESH ALL MATERIALIZED VIEWS
-- ===========================================================================
-- This section ensures materialized views are populated before extraction
-- Handles dependency chains by refreshing in multiple passes
-- ===========================================================================

\echo ''
\echo '=================================================='
\echo '=== PHASE 0: REFRESH MATERIALIZED VIEWS       ==='
\echo '=================================================='
\echo ''
\echo 'Refreshing all materialized views to ensure data is available...'
\echo 'This is required because materialized views store cached query results.'
\echo 'Uses multi-pass approach to handle view dependencies.'
\echo ''

DO $$
DECLARE
    mv_record RECORD;
    success_count INTEGER := 0;
    error_count INTEGER := 0;
    start_time TIMESTAMP;
    end_time TIMESTAMP;
    duration INTERVAL;
    total_mvs INTEGER;
    has_data BOOLEAN;
    max_passes INTEGER := 3;
    views_refreshed_this_pass INTEGER;
    view_index INTEGER := 0;
    pass_number INTEGER;
BEGIN
    start_time := clock_timestamp();
    
    -- Get total count for progress reporting
    SELECT COUNT(*) INTO total_mvs FROM pg_matviews WHERE schemaname = 'public';
    
    RAISE NOTICE '================================================';
    RAISE NOTICE 'Found % materialized views to refresh', total_mvs;
    RAISE NOTICE 'Using multi-pass approach to handle dependencies';
    RAISE NOTICE '================================================';
    RAISE NOTICE '';
    
    -- Create temp table to track successfully refreshed views
    CREATE TEMP TABLE IF NOT EXISTS tmp_mv_refresh_state (
        schemaname  text NOT NULL,
        matviewname text NOT NULL,
        PRIMARY KEY (schemaname, matviewname)
    ) ON COMMIT PRESERVE ROWS;
    
    -- Multi-pass refresh to handle dependencies
    -- Pass 1: Refresh base views (no dependencies on other materialized views)
    -- Pass 2-3: Refresh dependent views (may fail in early passes if dependencies not ready)
    FOR pass_number IN 1..max_passes LOOP
        views_refreshed_this_pass := 0;
        view_index := 0;
        
        RAISE NOTICE '--- Pass % of % ---', pass_number, max_passes;
        RAISE NOTICE '';
        
        -- Try to refresh all materialized views
        FOR mv_record IN 
            SELECT schemaname, matviewname
            FROM pg_matviews
            WHERE schemaname = 'public'
            ORDER BY matviewname
        LOOP
            BEGIN
                view_index := view_index + 1;
                
                -- Skip views that have already been successfully refreshed in a previous pass
                IF EXISTS (
                    SELECT 1
                    FROM tmp_mv_refresh_state s
                    WHERE s.schemaname  = mv_record.schemaname
                      AND s.matviewname = mv_record.matviewname
                ) THEN
                    RAISE NOTICE '  [%/%] SKIP %.% - already successfully refreshed in a previous pass',
                        view_index, total_mvs,
                        mv_record.schemaname, mv_record.matviewname;
                    CONTINUE;
                END IF;
                
                RAISE NOTICE '→ [%/%] Pass % - Refreshing: %.%', 
                    view_index, total_mvs, pass_number,
                    mv_record.schemaname, mv_record.matviewname;
                
                -- Refresh the materialized view
                EXECUTE format('REFRESH MATERIALIZED VIEW %I.%I', 
                    mv_record.schemaname, mv_record.matviewname);
                
                -- Record that this view has now been successfully refreshed (even if it contains 0 rows)
                INSERT INTO tmp_mv_refresh_state (schemaname, matviewname)
                VALUES (mv_record.schemaname, mv_record.matviewname)
                ON CONFLICT (schemaname, matviewname) DO NOTHING;
                
                -- Check if view has any data using efficient EXISTS query
                EXECUTE format('SELECT EXISTS(SELECT 1 FROM %I.%I LIMIT 1)', 
                    mv_record.schemaname, mv_record.matviewname) INTO has_data;
                
                success_count := success_count + 1;
                views_refreshed_this_pass := views_refreshed_this_pass + 1;
                
                IF has_data THEN
                    RAISE NOTICE '  ✓ Refreshed successfully - contains data';
                ELSE
                    RAISE NOTICE '  ✓ Refreshed successfully - 0 rows (may be expected based on data filters)';
                END IF;
                RAISE NOTICE '';
                
            EXCEPTION WHEN OTHERS THEN
                IF pass_number < max_passes THEN
                    RAISE NOTICE '  ⏭  Deferred (will retry in next pass): %', SQLERRM;
                ELSE
                    error_count := error_count + 1;
                    RAISE WARNING '  ✗ Failed to refresh after % passes: %', max_passes, SQLERRM;
                END IF;
                RAISE NOTICE '';
            END;
        END LOOP;
        
        RAISE NOTICE 'Pass % complete: % views refreshed', pass_number, views_refreshed_this_pass;
        RAISE NOTICE '';
        
        -- Early exit logic:
        -- - If no views were refreshed AND all views have been accounted for (success/error), we're done
        -- - If no views were refreshed BUT there are still unprocessed views, continue to next pass
        --   (they were deferred and might succeed after dependencies are met)
        -- - If views were refreshed, continue to next pass to retry deferred views
        IF views_refreshed_this_pass = 0
           AND (total_mvs - success_count - error_count) = 0 THEN
            RAISE NOTICE 'No views refreshed in this pass and no remaining views to retry - stopping early';
            EXIT;
        END IF;
    END LOOP;
    
    end_time := clock_timestamp();
    duration := end_time - start_time;
    
    RAISE NOTICE '';
    RAISE NOTICE '================================================';
    RAISE NOTICE 'Materialized view refresh summary:';
    RAISE NOTICE '  Total views: %', total_mvs;
    RAISE NOTICE '  Successfully refreshed: %', success_count;
    RAISE NOTICE '  Errors: %', error_count;
    RAISE NOTICE '  Duration: %', duration;
    RAISE NOTICE '================================================';
    RAISE NOTICE '';
    
    IF error_count > 0 THEN
        RAISE WARNING 'Some materialized views failed to refresh. Sample data for these views may be incomplete.';
    END IF;
END $$;

\echo '=================================================='
\echo '=== PHASE 0 COMPLETE: Materialized Views Refreshed ==='
\echo '=================================================='
\echo ''

-- ===========================================================================
-- SECTION 1: EARLY DISTINCT VALUE EXTRACTION (Before View Analysis)
-- ===========================================================================
-- This section extracts ALL distinct values from columns likely used in 
-- view predicates (WHERE, JOIN, GROUP BY, HAVING conditions)
-- ===========================================================================

\echo ''
\echo '=================================================='
\echo '=== PHASE 1: DISTINCT VALUE EXTRACTION        ==='
\echo '=== (Early extraction for view debugging)     ==='
\echo '=================================================='
\echo ''

-- Create output directory marker
\! mkdir -p distinct_values

\echo 'Extracting distinct values from all categorical/predicate columns...'
\echo 'This runs BEFORE view row counting to capture all possible filter values.'
\echo ''

-- Generate distinct value extraction commands
\! rm -f :DISTINCT_CMD_FILE
\pset format unaligned
\pset tuples_only on
\o :DISTINCT_CMD_FILE

-- ============================================================================
-- KNOWN CRITICAL COLUMNS: Hardcoded list of columns used in views
-- These are extracted from analyzing view definitions in the schema
-- ============================================================================

SELECT E'-- Critical predicate columns from view analysis\n' ||
       E'\\echo ''Extracting critical predicate columns...''\n';

-- assignment_data columns (used in committee/government/ministry views)
SELECT format(
    '\echo ''  [CRITICAL] assignment_data.%s''' || E'\n' ||
    '\copy (SELECT %s AS value, COUNT(*) AS count FROM assignment_data WHERE %s IS NOT NULL GROUP BY %s ORDER BY count DESC, %s LIMIT 2000) TO ''distinct_values/assignment_data_%s.csv'' WITH CSV HEADER' || E'\n',
    col, col, col, col, col, col
)
FROM unnest(ARRAY['org_code', 'role_code', 'status', 'detail', 'assignment_type', 'intressent_id']) AS col
WHERE EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'assignment_data' AND column_name = col);

-- person_data columns
SELECT format(
    '\echo ''  [CRITICAL] person_data.%s''' || E'\n' ||
    '\copy (SELECT %s AS value, COUNT(*) AS count FROM person_data WHERE %s IS NOT NULL GROUP BY %s ORDER BY count DESC, %s LIMIT 2000) TO ''distinct_values/person_data_%s.csv'' WITH CSV HEADER' || E'\n',
    col, col, col, col, col, col
)
FROM unnest(ARRAY['party', 'status', 'gender', 'election_region']) AS col
WHERE EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'person_data' AND column_name = col);

-- vote_data columns  
SELECT format(
    '\echo ''  [CRITICAL] vote_data.%s''' || E'\n' ||
    '\copy (SELECT %s AS value, COUNT(*) AS count FROM vote_data WHERE %s IS NOT NULL GROUP BY %s ORDER BY count DESC, %s LIMIT 2000) TO ''distinct_values/vote_data_%s.csv'' WITH CSV HEADER' || E'\n',
    col, col, col, col, col, col
)
FROM unnest(ARRAY['vote', 'ballot_type', 'party', 'gender']) AS col
WHERE EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'vote_data' AND column_name = col);

-- document_data columns
SELECT format(
    '\echo ''  [CRITICAL] document_data.%s''' || E'\n' ||
    '\copy (SELECT %s AS value, COUNT(*) AS count FROM document_data WHERE %s IS NOT NULL GROUP BY %s ORDER BY count DESC, %s LIMIT 2000) TO ''distinct_values/document_data_%s.csv'' WITH CSV HEADER' || E'\n',
    col, col, col, col, col, col
)
FROM unnest(ARRAY['document_type', 'status', 'org', 'rm', 'sub_type']) AS col
WHERE EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'document_data' AND column_name = col);

-- committee_document_data columns
SELECT format(
    '\echo ''  [CRITICAL] committee_document_data.%s''' || E'\n' ||
    '\copy (SELECT %s AS value, COUNT(*) AS count FROM committee_document_data WHERE %s IS NOT NULL GROUP BY %s ORDER BY count DESC, %s LIMIT 2000) TO ''distinct_values/committee_document_data_%s.csv'' WITH CSV HEADER' || E'\n',
    col, col, col, col, col, col
)
FROM unnest(ARRAY['org', 'status', 'rm', 'sub_type']) AS col
WHERE EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'committee_document_data' AND column_name = col);

-- committee_proposal_data columns
SELECT format(
    '\echo ''  [CRITICAL] committee_proposal_data.%s''' || E'\n' ||
    '\copy (SELECT %s AS value, COUNT(*) AS count FROM committee_proposal_data WHERE %s IS NOT NULL GROUP BY %s ORDER BY count DESC, %s LIMIT 2000) TO ''distinct_values/committee_proposal_data_%s.csv'' WITH CSV HEADER' || E'\n',
    col, col, col, col, col, col
)
FROM unnest(ARRAY['decision_type', 'winner', 'committee_report', 'rm']) AS col
WHERE EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'committee_proposal_data' AND column_name = col);

-- document_status_container columns
SELECT format(
    '\echo ''  [CRITICAL] document_status_container.%s''' || E'\n' ||
    '\copy (SELECT %s AS value, COUNT(*) AS count FROM document_status_container WHERE %s IS NOT NULL GROUP BY %s ORDER BY count DESC, %s LIMIT 2000) TO ''distinct_values/document_status_container_%s.csv'' WITH CSV HEADER' || E'\n',
    col, col, col, col, col, col
)
FROM unnest(ARRAY['document_category']) AS col
WHERE EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'document_status_container' AND column_name = col);

-- document_element columns
SELECT format(
    '\echo ''  [CRITICAL] document_element.%s''' || E'\n' ||
    '\copy (SELECT %s AS value, COUNT(*) AS count FROM document_element WHERE %s IS NOT NULL GROUP BY %s ORDER BY count DESC, %s LIMIT 2000) TO ''distinct_values/document_element_%s.csv'' WITH CSV HEADER' || E'\n',
    col, col, col, col, col, col
)
FROM unnest(ARRAY['document_type', 'status', 'org', 'rm', 'sub_type', 'doc_type', 'database_source']) AS col
WHERE EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'document_element' AND column_name = col);

-- detail_data columns
SELECT format(
    '\echo ''  [CRITICAL] detail_data.%s''' || E'\n' ||
    '\copy (SELECT %s AS value, COUNT(*) AS count FROM detail_data WHERE %s IS NOT NULL GROUP BY %s ORDER BY count DESC, %s LIMIT 2000) TO ''distinct_values/detail_data_%s.csv'' WITH CSV HEADER' || E'\n',
    col, col, col, col, col, col
)
FROM unnest(ARRAY['code', 'detail_type']) AS col
WHERE EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'detail_data' AND column_name = col);

-- ============================================================================
-- DYNAMIC EXTRACTION: All varchar/text columns with low cardinality
-- ============================================================================

SELECT E'\n-- Dynamic extraction of all categorical columns\n' ||
       E'\\echo ''''\n' ||
       E'\\echo ''Extracting additional categorical columns dynamically...''\n';

-- Extract all text/varchar columns that likely contain categorical data
WITH categorical_columns AS (
    SELECT 
        c.table_name,
        c.column_name,
        c.data_type,
        c.character_maximum_length
    FROM information_schema.columns c
    JOIN information_schema.tables t 
        ON t.table_schema = c.table_schema 
        AND t.table_name = c.table_name
    WHERE c.table_schema = 'public'
      AND t.table_type = 'BASE TABLE'
      AND c.data_type IN ('character varying', 'text', 'character')
      -- Exclude large text fields and known non-categorical columns
      AND (c.character_maximum_length IS NULL OR c.character_maximum_length <= 255)
      -- ========================================================================
      -- EXCLUDE CONTENT/TEXT FIELDS (long text, descriptions, etc.)
      -- ========================================================================
      AND c.column_name NOT IN ('content', 'title', 'sub_title', 'description', 'text', 
                                 'summary', 'note', 'header', 'proposal', 'wording',
                                 'wording_2', 'wording_3', 'wording_4', 'source_note',
                                 'reference_name', 'detail_name', 'activity_name',
                                 'config_description', 'component_description',
                                 'error_message', 'application_message', 'storage')
      -- ========================================================================
      -- EXCLUDE URL FIELDS
      -- ========================================================================
      AND c.column_name NOT IN ('file_url', 'file_name', 'image_url_192', 'image_url_80', 
                                 'image_url_max', 'person_url_xml', 'document_url_html',
                                 'document_url_text', 'document_status_url_xml',
                                 'document_status_url_www', 'committee_proposal_url_xml',
                                 'committee_report_url_xml', 'ballot_url_xml', 'website')
      -- ========================================================================
      -- EXCLUDE NAME FIELDS (person names, full names, etc.)
      -- ========================================================================
      AND c.column_name NOT IN ('first_name', 'last_name', 'full_name', 'name',
                                 'party_name', 'country_name', 'indicator_name',
                                 'county_name', 'municipal_name', 'region_name',
                                 'electoral_area_name', 'election_region_name',
                                 'capital_city', 'place', 'city', 'address',
                                 'co_address', 'source_organization', 'adminregion_value', 
                                 'region_value', 'property_value')
      -- ========================================================================
      -- EXCLUDE ID FIELDS (unique identifiers, foreign keys, etc.)
      -- ========================================================================
      AND c.column_name NOT LIKE '%_id'
      AND c.column_name NOT LIKE '%_id_%'
      AND c.column_name NOT LIKE 'id_%'
      AND c.column_name != 'id'
      AND c.column_name NOT LIKE 'hjid%'
      AND c.column_name NOT LIKE '%intressent_id%'
      AND c.column_name NOT LIKE '%person_reference_id%'
      AND c.column_name NOT LIKE '%document_id%'
      AND c.column_name NOT LIKE '%ballot_id%'
      AND c.column_name NOT LIKE '%hangar%'
      AND c.column_name NOT LIKE '%guid%'
      -- ========================================================================
      -- EXCLUDE SESSION/USER TRACKING FIELDS
      -- ========================================================================
      AND c.column_name NOT IN ('ip_information', 'user_agent_information', 'session_id',
                                 'user_id', 'user_password', 'email', 'username')
      -- ========================================================================
      -- EXCLUDE DATE/TIME STRING FIELDS
      -- ========================================================================
      AND c.column_name NOT LIKE '%_date'
      AND c.column_name NOT LIKE '%date_%'
      AND c.column_name NOT IN ('created', 'datum', 'created_date', 'made_public_date',
                                 'system_date')
      -- ========================================================================
      -- EXCLUDE TECHNICAL/INFRASTRUCTURE TABLES (not domain data)
      -- ========================================================================
      -- Javers audit tables (jv_*)
      AND c.table_name NOT LIKE 'jv_%'
      -- Quartz scheduler tables (qrtz_*)
      AND c.table_name NOT LIKE 'qrtz_%'
      -- Liquibase migration tables
      AND c.table_name NOT IN ('databasechangelog', 'databasechangeloglock')
      -- Hibernate sequence table
      AND c.table_name != 'hibernate_sequence'
      -- Encrypted value storage (sensitive)
      AND c.table_name != 'encrypted_value'
      -- Application session/event tables (user tracking, not political data)
      AND c.table_name NOT IN ('application_session', 'application_action_event', 
                                'application_configuration', 'application_view')
      -- User account tables (sensitive)
      AND c.table_name NOT IN ('user_account', 'user_account_address')
      -- Portal configuration tables
      AND c.table_name NOT IN ('portal', 'domain_portal', 'agency')
      -- Rule violation tracking
      AND c.table_name != 'rule_violation'
      -- Data source metadata
      AND c.table_name != 'data_source_content'
      -- Language/translation tables (not political data)
      AND c.table_name NOT IN ('language_data', 'language_content_data')
      -- ========================================================================
      -- EXCLUDE OTHER HIGH-CARDINALITY OR NON-CATEGORICAL COLUMNS
      -- ========================================================================
      AND c.column_name NOT IN ('label', 'temp_label', 'kall_id', 'related_id',
                                 'next_page', 'debug', 'warning', 'document_version',
                                 'order_number', 'bank_number', 'phone_number', 
                                 'fax_number', 'post_code', 'latitude', 'longitude',
                                 'rest', 'value_', 'data_value')
      -- ========================================================================
      -- Skip columns already extracted above (critical columns)
      -- ========================================================================
      AND NOT (c.table_name = 'assignment_data' AND c.column_name IN ('org_code', 'role_code', 'status', 'detail', 'assignment_type', 'intressent_id'))
      AND NOT (c.table_name = 'person_data' AND c.column_name IN ('party', 'status', 'gender', 'election_region'))
      AND NOT (c.table_name = 'vote_data' AND c.column_name IN ('vote', 'ballot_type', 'party', 'gender'))
      AND NOT (c.table_name = 'document_data' AND c.column_name IN ('document_type', 'status', 'org', 'rm', 'sub_type'))
      AND NOT (c.table_name = 'committee_document_data' AND c.column_name IN ('org', 'status', 'rm', 'sub_type'))
      AND NOT (c.table_name = 'committee_proposal_data' AND c.column_name IN ('decision_type', 'winner', 'committee_report', 'rm'))
      AND NOT (c.table_name = 'document_status_container' AND c.column_name IN ('document_category'))
      AND NOT (c.table_name = 'document_element' AND c.column_name IN ('document_type', 'status', 'org', 'rm', 'sub_type', 'doc_type', 'database_source'))
      AND NOT (c.table_name = 'detail_data' AND c.column_name IN ('code', 'detail_type'))
    ORDER BY c.table_name, c.column_name
)
SELECT format(
    '\echo ''  [AUTO] %s.%s''' || E'\n' ||
    '\copy (SELECT %s AS value, COUNT(*) AS count FROM %s WHERE %s IS NOT NULL GROUP BY %s ORDER BY count DESC, %s LIMIT 1000) TO ''distinct_values/%s_%s.csv'' WITH CSV HEADER' || E'\n',
    table_name, column_name,
    column_name, table_name, column_name, column_name, column_name,
    table_name, column_name
)
FROM categorical_columns;

-- ============================================================================
-- CROSS-TABLE VALUE ANALYSIS: Values that appear in JOINs
-- ============================================================================

SELECT E'\n-- Cross-table join key analysis\n' ||
       E'\\echo ''''\n' ||
       E'\\echo ''Extracting cross-table join key distributions...''\n';

-- Extract ID distributions for common join patterns
SELECT E'\\echo ''  [JOIN] person_data IDs in assignment_data''' || E'\n' ||
       E'\\copy (SELECT intressent_id AS value, COUNT(*) AS count FROM assignment_data WHERE intressent_id IS NOT NULL GROUP BY intressent_id ORDER BY count DESC LIMIT 500) TO ''distinct_values/join_assignment_to_person.csv'' WITH CSV HEADER' || E'\n'
WHERE EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'assignment_data');

SELECT E'\\echo ''  [JOIN] document IDs in document_status_container''' || E'\n' ||
       E'\\copy (SELECT document_document_status_con_0 AS value, COUNT(*) AS count FROM document_status_container WHERE document_document_status_con_0 IS NOT NULL GROUP BY document_document_status_con_0 ORDER BY count DESC LIMIT 500) TO ''distinct_values/join_docstatus_to_document.csv'' WITH CSV HEADER' || E'\n'
WHERE EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'document_status_container' AND column_name = 'document_document_status_con_0');

-- ============================================================================
-- SUMMARY STATISTICS
-- ============================================================================

SELECT E'\n\\echo ''''\n' ||
       E'\\echo ''==================================================''\n' ||
       E'\\echo ''Distinct value extraction complete.''\n' ||
       E'\\echo ''Files saved to: distinct_values/''\n' ||
       E'\\echo ''==================================================''\n';

\o
\pset format aligned
\pset tuples_only off

-- Execute the distinct value extractions
\echo ''
\echo 'Executing distinct value extractions...'
\i :DISTINCT_CMD_FILE
\! rm -f :DISTINCT_CMD_FILE

-- Create a summary of extracted distinct values
\echo ''
\echo 'Creating distinct value summary...'

\copy (SELECT 'Summary of Distinct Value Extractions' AS info, COUNT(*) AS file_count FROM (SELECT 1) x) TO 'distinct_values/_extraction_summary.csv' WITH CSV HEADER

\! ls -la distinct_values/ 2>/dev/null | head -50 || echo "distinct_values directory listing"

\echo ''
\echo '=================================================='
\echo '=== PHASE 1 COMPLETE: Distinct Values Extracted ==='
\echo '=================================================='
\echo ''

-- ===========================================================================
-- SECTION 2: Analyze Materialized Views for Statistics
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== PHASE 2: ANALYZING MATERIALIZED VIEWS     ==='
\echo '=================================================='
\echo ''
\echo 'Running ANALYZE on materialized views only...'
\echo '(Regular views cannot be ANALYZEd - they are virtual tables)'
\echo ''

DO $$
DECLARE
    view_record RECORD;
    view_count INTEGER := 0;
    success_count INTEGER := 0;
    error_count INTEGER := 0;
    start_time TIMESTAMP;
    end_time TIMESTAMP;
    duration INTERVAL;
    total_mat_views INTEGER;
    total_reg_views INTEGER;
BEGIN
    start_time := clock_timestamp();
    
    -- Get counts for reporting
    SELECT COUNT(*) INTO total_mat_views FROM pg_matviews WHERE schemaname = 'public';
    SELECT COUNT(*) INTO total_reg_views FROM pg_views WHERE schemaname = 'public';
    
    RAISE NOTICE '================================================';
    RAISE NOTICE 'ANALYZE Phase: Only analyzing MATERIALIZED views';
    RAISE NOTICE '  - Materialized views to analyze: %', total_mat_views;
    RAISE NOTICE '  - Regular views (skipped - cannot be analyzed): %', total_reg_views;
    RAISE NOTICE '================================================';
    RAISE NOTICE '';
    RAISE NOTICE 'Note: Regular views cannot be ANALYZEd in PostgreSQL.';
    RAISE NOTICE '      They are virtual tables computed from their underlying query.';
    RAISE NOTICE '';
    
    -- Only analyze materialized views (regular views cannot be analyzed)
    RAISE NOTICE '--- Analyzing Materialized Views ---';
    RAISE NOTICE '';
    
    FOR view_record IN 
        SELECT schemaname, matviewname AS viewname
        FROM pg_matviews
        WHERE schemaname = 'public'
        ORDER BY matviewname
    LOOP
        BEGIN
            view_count := view_count + 1;
            RAISE NOTICE 'ANALYZE [%/%]: %.%', view_count, total_mat_views,
                view_record.schemaname, view_record.viewname;
            
            EXECUTE format('ANALYZE %I.%I', view_record.schemaname, view_record.viewname);
            success_count := success_count + 1;
            RAISE NOTICE '  ✓ Analyzed successfully';
            RAISE NOTICE '';
            
        EXCEPTION WHEN OTHERS THEN
            error_count := error_count + 1;
            RAISE NOTICE '  ✗ ERROR: %', SQLERRM;
            RAISE NOTICE '';
        END;
    END LOOP;
    
    end_time := clock_timestamp();
    duration := end_time - start_time;
    
    RAISE NOTICE '';
    RAISE NOTICE '================================================';
    RAISE NOTICE 'View analysis summary:';
    RAISE NOTICE '  Materialized views analyzed: %', view_count;
    RAISE NOTICE '  Successfully analyzed: %', success_count;
    RAISE NOTICE '  Errors: %', error_count;
    RAISE NOTICE '  Duration: %', duration;
    RAISE NOTICE '  Regular views skipped: % (cannot be analyzed)', total_reg_views;
    RAISE NOTICE '================================================';
    RAISE NOTICE '';
END $$;

-- Display statistics results from pg_stats
\echo ''
\echo '=================================================='
\echo '=== ANALYZE STATISTICS RESULTS                ==='
\echo '=================================================='
\echo ''
\echo 'Displaying sample statistics for materialized views...'
\echo ''

-- Show row count estimates for all materialized views
-- Note: Materialized views are stored as tables in pg_class, not in pg_stat_user_tables
\copy (SELECT schemaname AS schema, matviewname AS view_name, (SELECT n_live_tup FROM pg_stat_all_tables WHERE schemaname = m.schemaname AND relname = m.matviewname) AS estimated_rows, (SELECT n_dead_tup FROM pg_stat_all_tables WHERE schemaname = m.schemaname AND relname = m.matviewname) AS dead_rows, (SELECT last_analyze FROM pg_stat_all_tables WHERE schemaname = m.schemaname AND relname = m.matviewname) AS last_analyzed, (SELECT last_autoanalyze FROM pg_stat_all_tables WHERE schemaname = m.schemaname AND relname = m.matviewname) AS last_auto_analyzed FROM pg_matviews m WHERE schemaname = 'public' ORDER BY estimated_rows DESC NULLS LAST) TO 'materialized_view_statistics.csv' WITH CSV HEADER;

\echo '  ✓ Statistics exported to: materialized_view_statistics.csv'
\echo ''

-- Display top 10 materialized views by row count
\echo 'Top 10 materialized views by estimated row count:'
\echo ''

SELECT 
    m.schemaname || '.' || m.matviewname AS view_name,
    s.n_live_tup AS estimated_rows,
    pg_size_pretty(pg_total_relation_size(m.schemaname || '.' || m.matviewname)) AS total_size,
    s.last_analyze AS last_analyzed
FROM pg_matviews m
LEFT JOIN pg_stat_all_tables s ON s.schemaname = m.schemaname AND s.relname = m.matviewname
WHERE m.schemaname = 'public'
ORDER BY s.n_live_tup DESC NULLS LAST
LIMIT 10;

\echo ''
\echo '=================================================='

-- ===========================================================================
-- SECTION 1.5: Generate Individual Query Plan Files
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== GENERATING INDIVIDUAL VIEW QUERY PLANS    ==='
\echo '=================================================='
\echo ''

\! mkdir -p view_plans

DO $$
DECLARE
    view_record RECORD;
    view_count INTEGER := 0;
    file_name TEXT;
    explain_query TEXT;
BEGIN
    RAISE NOTICE 'Generating individual query plan files...';
    RAISE NOTICE '';
    
    FOR view_record IN 
        SELECT schemaname, viewname AS object_name, 'VIEW' AS object_type
        FROM pg_views
        WHERE schemaname = 'public'
        UNION ALL
        SELECT schemaname, matviewname AS object_name, 'MATERIALIZED VIEW' AS object_type
        FROM pg_matviews
        WHERE schemaname = 'public'
        ORDER BY object_name
    LOOP
        BEGIN
            view_count := view_count + 1;
            file_name := '/workspaces/cia/service.data.impl/sample-data/view_plans/explain_' || view_record.object_name || '.txt';
            
            RAISE NOTICE '[%] %.%', view_count, view_record.schemaname, view_record.object_name;
            
            -- Execute EXPLAIN and write to file using psql \o command
            explain_query := 'EXPLAIN (ANALYZE, BUFFERS, VERBOSE, FORMAT TEXT) SELECT * FROM ' || 
                            quote_ident(view_record.schemaname) || '.' || 
                            quote_ident(view_record.object_name) || ' LIMIT 1';
            
            -- This approach won't work in DO block, skip EXPLAIN generation
            RAISE NOTICE '  ℹ️  Skipping EXPLAIN (requires psql \\o command)';
            
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE '  ✗ ERROR: %', SQLERRM;
        END;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE '  Note: EXPLAIN plans require manual execution with psql \\o command';
    RAISE NOTICE '';
END $$;

-- ===========================================================================
-- SECTION 2: Extract Sample Data from ALL Tables Dynamically
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== PHASE 3: EXTRACTING TABLE SAMPLE DATA     ==='
\echo '=================================================='
\echo ''

DO $$
DECLARE
    table_count INTEGER;
    empty_count INTEGER := 0;
    non_empty_count INTEGER := 0;
    table_rec RECORD;
    row_count BIGINT;
BEGIN
    SELECT COUNT(*) INTO table_count 
    FROM pg_tables 
    WHERE schemaname = 'public';
    
    RAISE NOTICE 'Found % tables in public schema', table_count;
    RAISE NOTICE '';
    RAISE NOTICE 'Counting rows in each table (this may take a moment)...';
    RAISE NOTICE '';
    
    -- Count empty vs non-empty tables with progress
    FOR table_rec IN 
        SELECT tablename
        FROM pg_tables 
        WHERE schemaname = 'public'
        ORDER BY tablename
    LOOP
        row_count := cia_tmp_rowcount('public', table_rec.tablename);
        IF row_count > 0 THEN
            non_empty_count := non_empty_count + 1;
        ELSE
            empty_count := empty_count + 1;
        END IF;
    END LOOP;
    
    RAISE NOTICE '✓ Row count analysis complete';
    RAISE NOTICE '  Tables with data: %', non_empty_count;
    RAISE NOTICE '  Empty tables: %', empty_count;
    RAISE NOTICE '';
END $$;

\! rm -f :TABLE_CMD_FILE
\pset format unaligned
\pset tuples_only on
\o :TABLE_CMD_FILE
WITH table_counts AS (
    SELECT schemaname,
           tablename,
           cia_tmp_rowcount(schemaname, tablename) AS row_count
    FROM pg_tables
    WHERE schemaname = 'public'
),
table_extract AS (
    SELECT schemaname,
           tablename,
           row_count,
           -- Smart sample size calculation:
           -- 1. Complete extraction for small tables (< threshold)
           -- 2. Extended samples for party/committee/person data
           -- 3. Extended samples for document/voting/worldbank data
           -- 4. Default sample size for others
           CASE 
               -- Complete extraction for small tables
               WHEN row_count <= :COMPLETE_EXTRACTION_THRESHOLD::int THEN row_count
               -- Extended samples for party-related tables
               WHEN tablename ILIKE '%party%' OR tablename ILIKE '%parti%' THEN 
                   LEAST(:PARTY_SAMPLE_SIZE::int, row_count)
               -- Extended samples for committee-related tables
               WHEN tablename ILIKE '%committee%' OR tablename ILIKE '%utskott%' THEN 
                   LEAST(:COMMITTEE_SAMPLE_SIZE::int, row_count)
               -- Extended samples for person/politician data
               WHEN tablename ILIKE '%person%' OR tablename ILIKE '%politician%' 
                   OR tablename ILIKE '%member%' THEN 
                   LEAST(:PERSON_SAMPLE_SIZE::int, row_count)
               -- Extended samples for document-related tables
               WHEN tablename ILIKE '%document%' OR tablename ILIKE '%proposal%' THEN 
                   LEAST(:DOCUMENT_SAMPLE_SIZE::int, row_count)
               -- Extended samples for voting-related tables
               WHEN tablename ILIKE '%vote%' OR tablename ILIKE '%ballot%' THEN 
                   LEAST(:VOTING_SAMPLE_SIZE::int, row_count)
               -- Extended samples for worldbank data
               WHEN tablename ILIKE '%worldbank%' OR tablename ILIKE '%indicator%' 
                   OR tablename ILIKE '%country%' THEN 
                   LEAST(:WORLDBANK_SAMPLE_SIZE::int, row_count)
               -- Default sample size
               ELSE LEAST(:SAMPLE_SIZE::int, row_count)
           END AS sample_rows,
           -- Extraction type for logging
           CASE 
               WHEN row_count <= :COMPLETE_EXTRACTION_THRESHOLD::int THEN 'COMPLETE'
               WHEN tablename ILIKE '%party%' OR tablename ILIKE '%parti%' THEN 'PARTY-EXTENDED'
               WHEN tablename ILIKE '%committee%' OR tablename ILIKE '%utskott%' THEN 'COMMITTEE-EXTENDED'
               WHEN tablename ILIKE '%person%' OR tablename ILIKE '%politician%' 
                   OR tablename ILIKE '%member%' THEN 'PERSON-EXTENDED'
               WHEN tablename ILIKE '%document%' OR tablename ILIKE '%proposal%' THEN 'DOCUMENT-EXTENDED'
               WHEN tablename ILIKE '%vote%' OR tablename ILIKE '%ballot%' THEN 'VOTING-EXTENDED'
               WHEN tablename ILIKE '%worldbank%' OR tablename ILIKE '%indicator%' 
                   OR tablename ILIKE '%country%' THEN 'WORLDBANK-EXTENDED'
               ELSE 'SAMPLE'
           END AS extraction_type,
           CASE WHEN tablename LIKE 'table_%' THEN tablename ELSE 'table_' || tablename END AS file_prefix
    FROM table_counts
    WHERE row_count > 0
)
SELECT format(
    '\echo ''[TABLE-%s] Extracting: %I.%I (%s rows of %s total)''' || E'\n' ||
    '\copy (SELECT * FROM %I.%I ORDER BY random() LIMIT %s) TO ''%s_sample.csv'' CSV HEADER' || E'\n' ||
    '\echo ''  ✓ Completed: %s_sample.csv''' || E'\n',
    extraction_type,
    schemaname,
    tablename,
    sample_rows,
    row_count,
    schemaname,
    tablename,
    sample_rows,
    file_prefix,
    file_prefix
)
FROM table_extract
ORDER BY tablename;
\o
\pset format aligned
\pset tuples_only off

\echo 'Executing table extractions...'
\echo ''

-- Execute with verbose feedback showing what we're about to do
\i :TABLE_CMD_FILE
\! rm -f :TABLE_CMD_FILE

\echo ''
\echo 'Table extraction completed'
\echo ''

-- ===========================================================================
-- SECTION 4: Extract Sample Data from ALL Views Dynamically  
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== PHASE 4: EXTRACTING VIEW SAMPLE DATA      ==='
\echo '=================================================='
\echo ''

DO $$
DECLARE
    view_record RECORD;
    view_count INTEGER := 0;
    total_views INTEGER;
    row_count BIGINT;
    extract_count INTEGER := 0;
BEGIN
    -- Get total view count (excluding coalition alignment matrix)
    SELECT COUNT(*) INTO total_views
    FROM (
        SELECT viewname FROM pg_views 
        WHERE schemaname = 'public' 
          AND viewname != 'view_riksdagen_intelligence_dashboard'
        UNION ALL
        SELECT matviewname FROM pg_matviews 
        WHERE schemaname = 'public'
    ) v;
    
    RAISE NOTICE '';
    RAISE NOTICE 'Phase 1: Analyzing % views for row counts', total_views;
    RAISE NOTICE 'Excluding: view_riksdagen_coalition_alignment_matrix (complex query)';
    RAISE NOTICE 'This may take several minutes for complex views...';
    RAISE NOTICE '';
    
    -- Process each view with progress BEFORE the slow operation
    FOR view_record IN 
        SELECT schemaname, viewname AS object_name, 'VIEW' AS object_type
        FROM pg_views
        WHERE schemaname = 'public'
          AND viewname != 'view_riksdagen_coalition_alignment_matrix'
          AND viewname != 'view_riksdagen_intelligence_dashboard'
        UNION ALL
        SELECT schemaname, matviewname AS object_name, 'MATERIALIZED VIEW' AS object_type
        FROM pg_matviews
        WHERE schemaname = 'public'
        ORDER BY object_name
    LOOP
        view_count := view_count + 1;
        
        -- Always show what we're about to analyze
        RAISE NOTICE '→ [%/%] Analyzing: %.% (%)', 
            view_count, total_views, 
            view_record.schemaname, view_record.object_name,
            view_record.object_type;
        
        -- Now do the slow count operation
        row_count := cia_tmp_rowcount(view_record.schemaname, view_record.object_name);
        
        -- Show result immediately after
        IF row_count > 0 THEN
            RAISE NOTICE '  ✓ Contains % rows', row_count;
            extract_count := extract_count + 1;
        ELSE
            RAISE NOTICE '  ⚠️  EMPTY (0 rows)';
        END IF;
        RAISE NOTICE '';
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE '✓ Phase 1 complete: % of % views have data', extract_count, total_views;
    RAISE NOTICE '';
END $$;

\echo ''
\echo 'Phase 2: Generating extraction commands...'
\echo ''

\! rm -f :VIEW_CMD_FILE
\pset format unaligned
\pset tuples_only on

\o :VIEW_CMD_FILE
WITH view_counts AS (
    SELECT schemaname,
           viewname,
           cia_tmp_rowcount(schemaname, viewname) AS row_count
    FROM pg_views
    WHERE schemaname = 'public'
      AND viewname != 'view_riksdagen_coalition_alignment_matrix'
      AND viewname != 'view_riksdagen_intelligence_dashboard'
    UNION ALL
    SELECT schemaname,
           matviewname,
           cia_tmp_rowcount(schemaname, matviewname) AS row_count
    FROM pg_matviews
    WHERE schemaname = 'public'
),
-- ============================================================================
-- TEMPORAL VIEW CLASSIFICATION
-- ============================================================================
-- Identify views by temporal granularity based on naming patterns
-- This enables stratified sampling across time periods
-- Uses reusable cia_classify_temporal_view() function
-- ============================================================================
view_temporal_classification AS (
    SELECT 
        vc.schemaname,
        vc.viewname,
        vc.row_count,
        c.temporal_granularity,
        c.samples_per_bucket
    FROM view_counts vc
    CROSS JOIN LATERAL cia_classify_temporal_view(vc.viewname) AS c
    WHERE vc.row_count > 0
),
-- ============================================================================
-- TEMPORAL COLUMN DETECTION
-- ============================================================================
-- Automatically detect temporal columns in each view
-- ============================================================================
view_temporal_columns AS (
    SELECT 
        c.table_schema AS schemaname,
        c.table_name AS viewname,
        c.column_name AS temporal_column,
        c.data_type,
        -- Prioritize common temporal column names
        CASE 
            WHEN c.column_name IN ('vote_date', 'embedded_id_vote_date') THEN 1
            WHEN c.column_name IN ('created_date', 'ballot_date', 'document_date') THEN 2
            WHEN c.column_name IN ('from_date', 'to_date', 'made_public_date') THEN 3
            WHEN c.column_name LIKE '%_date' THEN 4
            ELSE 5
        END AS priority
    FROM information_schema.columns c
    WHERE c.table_schema = 'public'
      AND c.data_type IN ('date', 'timestamp without time zone', 'timestamp with time zone')
      AND c.column_name NOT IN ('created', 'model_object_version') -- Exclude technical columns
),
view_primary_temporal_column AS (
    SELECT DISTINCT ON (schemaname, viewname)
        schemaname,
        viewname,
        temporal_column
    FROM view_temporal_columns
    ORDER BY schemaname, viewname, priority
),
-- ============================================================================
-- GENERATE STRATIFIED SAMPLING QUERIES
-- ============================================================================
view_extract AS (
    SELECT 
        vtc.schemaname,
        vtc.viewname,
        vtc.row_count,
        vtc.temporal_granularity,
        vtc.samples_per_bucket,
        vptc.temporal_column,
        -- Smart sample size calculation:
        -- 1. Complete extraction for small views (< threshold)
        -- 2. Extended samples for party/committee/person views
        -- 3. Extended samples for document/voting/worldbank views
        -- 4. Default sample size for others
        CASE 
            -- Complete extraction for small views
            WHEN vtc.row_count <= :COMPLETE_EXTRACTION_THRESHOLD::int THEN vtc.row_count
            -- Extended samples for party-related views
            WHEN vtc.viewname ILIKE '%party%' OR vtc.viewname ILIKE '%parti%' THEN 
                LEAST(:PARTY_SAMPLE_SIZE::int, vtc.row_count)
            -- Extended samples for committee-related views
            WHEN vtc.viewname ILIKE '%committee%' OR vtc.viewname ILIKE '%utskott%' THEN 
                LEAST(:COMMITTEE_SAMPLE_SIZE::int, vtc.row_count)
            -- Extended samples for person/politician views
            WHEN vtc.viewname ILIKE '%person%' OR vtc.viewname ILIKE '%politician%' 
                OR vtc.viewname ILIKE '%member%' THEN 
                LEAST(:PERSON_SAMPLE_SIZE::int, vtc.row_count)
            -- Extended samples for document-related views
            WHEN vtc.viewname ILIKE '%document%' OR vtc.viewname ILIKE '%proposal%' THEN 
                LEAST(:DOCUMENT_SAMPLE_SIZE::int, vtc.row_count)
            -- Extended samples for voting-related views
            WHEN vtc.viewname ILIKE '%vote%' OR vtc.viewname ILIKE '%ballot%' THEN 
                LEAST(:VOTING_SAMPLE_SIZE::int, vtc.row_count)
            -- Extended samples for worldbank data
            WHEN vtc.viewname ILIKE '%worldbank%' OR vtc.viewname ILIKE '%indicator%' 
                OR vtc.viewname ILIKE '%country%' THEN 
                LEAST(:WORLDBANK_SAMPLE_SIZE::int, vtc.row_count)
            -- Default sample size
            ELSE LEAST(:SAMPLE_SIZE::int, vtc.row_count)
        END AS sample_rows,
        -- Extraction type for logging
        CASE 
            WHEN vtc.row_count <= :COMPLETE_EXTRACTION_THRESHOLD::int THEN 'COMPLETE'
            WHEN vtc.viewname ILIKE '%party%' OR vtc.viewname ILIKE '%parti%' THEN 'PARTY-EXTENDED'
            WHEN vtc.viewname ILIKE '%committee%' OR vtc.viewname ILIKE '%utskott%' THEN 'COMMITTEE-EXTENDED'
            WHEN vtc.viewname ILIKE '%person%' OR vtc.viewname ILIKE '%politician%' 
                OR vtc.viewname ILIKE '%member%' THEN 'PERSON-EXTENDED'
            WHEN vtc.viewname ILIKE '%document%' OR vtc.viewname ILIKE '%proposal%' THEN 'DOCUMENT-EXTENDED'
            WHEN vtc.viewname ILIKE '%vote%' OR vtc.viewname ILIKE '%ballot%' THEN 'VOTING-EXTENDED'
            WHEN vtc.viewname ILIKE '%worldbank%' OR vtc.viewname ILIKE '%indicator%' 
                OR vtc.viewname ILIKE '%country%' THEN 'WORLDBANK-EXTENDED'
            ELSE 'SAMPLE'
        END AS extraction_type,
        CASE WHEN vtc.viewname LIKE 'view_%' THEN vtc.viewname ELSE 'view_' || vtc.viewname END AS file_prefix
    FROM view_temporal_classification vtc
    LEFT JOIN view_primary_temporal_column vptc 
        ON vtc.schemaname = vptc.schemaname 
        AND vtc.viewname = vptc.viewname
)
-- ============================================================================
-- GENERATE EXTRACTION COMMANDS WITH TEMPORAL STRATIFICATION
-- ============================================================================
SELECT 
    CASE 
        -- =====================================================================
        -- DAILY VIEWS: Stratified sampling by day
        -- =====================================================================
        WHEN temporal_granularity = 'daily' AND temporal_column IS NOT NULL THEN
            format(
                '\echo ''[VIEW-DAILY] Extracting: %s (stratified: %s samples/day, last 30 days)''' || E'\n' ||
                '\copy (' ||
                'WITH temporal_strata AS (' ||
                '  SELECT *, ' ||
                '    ROW_NUMBER() OVER (PARTITION BY DATE(%I) ORDER BY random()) AS rn ' ||
                '  FROM %I.%I ' ||
                '  WHERE %I >= CURRENT_DATE - INTERVAL ''30 days'' ' ||
                ') ' ||
                'SELECT * FROM temporal_strata WHERE rn <= %s LIMIT %s' ||
                ') TO ''%s_sample.csv'' WITH CSV HEADER' || E'\n' ||
                '\echo ''  ✓ Completed: %s_sample.csv (temporal stratification: daily)''' || E'\n',
                viewname,
                samples_per_bucket,
                temporal_column, schemaname, viewname, temporal_column,
                samples_per_bucket, sample_rows,
                file_prefix, file_prefix
            )
        
        -- =====================================================================
        -- WEEKLY VIEWS: Stratified sampling by week
        -- =====================================================================
        WHEN temporal_granularity = 'weekly' AND temporal_column IS NOT NULL THEN
            format(
                '\echo ''[VIEW-WEEKLY] Extracting: %s (stratified: %s samples/week, last 6 months)''' || E'\n' ||
                '\copy (' ||
                'WITH temporal_strata AS (' ||
                '  SELECT *, ' ||
                '    ROW_NUMBER() OVER (PARTITION BY DATE_TRUNC(''week'', %I) ORDER BY random()) AS rn ' ||
                '  FROM %I.%I ' ||
                '  WHERE %I >= CURRENT_DATE - INTERVAL ''6 months'' ' ||
                ') ' ||
                'SELECT * FROM temporal_strata WHERE rn <= %s LIMIT %s' ||
                ') TO ''%s_sample.csv'' WITH CSV HEADER' || E'\n' ||
                '\echo ''  ✓ Completed: %s_sample.csv (temporal stratification: weekly)''' || E'\n',
                viewname,
                samples_per_bucket,
                temporal_column, schemaname, viewname, temporal_column,
                samples_per_bucket, sample_rows,
                file_prefix, file_prefix
            )
        
        -- =====================================================================
        -- MONTHLY VIEWS: Stratified sampling by month
        -- =====================================================================
        WHEN temporal_granularity = 'monthly' AND temporal_column IS NOT NULL THEN
            format(
                '\echo ''[VIEW-MONTHLY] Extracting: %s (stratified: %s samples/month, last 3 years)''' || E'\n' ||
                '\copy (' ||
                'WITH temporal_strata AS (' ||
                '  SELECT *, ' ||
                '    ROW_NUMBER() OVER (PARTITION BY DATE_TRUNC(''month'', %I) ORDER BY random()) AS rn ' ||
                '  FROM %I.%I ' ||
                '  WHERE %I >= CURRENT_DATE - INTERVAL ''3 years'' ' ||
                ') ' ||
                'SELECT * FROM temporal_strata WHERE rn <= %s LIMIT %s' ||
                ') TO ''%s_sample.csv'' WITH CSV HEADER' || E'\n' ||
                '\echo ''  ✓ Completed: %s_sample.csv (temporal stratification: monthly)''' || E'\n',
                viewname,
                samples_per_bucket,
                temporal_column, schemaname, viewname, temporal_column,
                samples_per_bucket, sample_rows,
                file_prefix, file_prefix
            )
        
        -- =====================================================================
        -- ANNUAL VIEWS: Stratified sampling by year
        -- =====================================================================
        WHEN temporal_granularity = 'annual' AND temporal_column IS NOT NULL THEN
            format(
                '\echo ''[VIEW-ANNUAL] Extracting: %s (stratified: %s samples/year, full history)''' || E'\n' ||
                '\copy (' ||
                'WITH temporal_strata AS (' ||
                '  SELECT *, ' ||
                '    ROW_NUMBER() OVER (PARTITION BY DATE_TRUNC(''year'', %I) ORDER BY random()) AS rn ' ||
                '  FROM %I.%I ' ||
                ') ' ||
                'SELECT * FROM temporal_strata WHERE rn <= %s LIMIT %s' ||
                ') TO ''%s_sample.csv'' WITH CSV HEADER' || E'\n' ||
                '\echo ''  ✓ Completed: %s_sample.csv (temporal stratification: annual)''' || E'\n',
                viewname,
                samples_per_bucket,
                temporal_column, schemaname, viewname,
                samples_per_bucket, sample_rows,
                file_prefix, file_prefix
            )
        
        -- =====================================================================
        -- TEMPORAL TREND VIEWS: Sample across all time periods
        -- =====================================================================
        WHEN temporal_granularity = 'temporal_trend' AND temporal_column IS NOT NULL THEN
            format(
                '\echo ''[VIEW-TREND] Extracting: %s (stratified: 1 sample per time period)''' || E'\n' ||
                '\copy (' ||
                'WITH temporal_buckets AS (' ||
                '  SELECT *, ' ||
                '    DATE_TRUNC(''month'', %I) AS time_bucket, ' ||
                '    ROW_NUMBER() OVER (PARTITION BY DATE_TRUNC(''month'', %I) ORDER BY random()) AS rn ' ||
                '  FROM %I.%I ' ||
                ') ' ||
                'SELECT * FROM temporal_buckets WHERE rn = 1 ORDER BY time_bucket DESC LIMIT %s' ||
                ') TO ''%s_sample.csv'' WITH CSV HEADER' || E'\n' ||
                '\echo ''  ✓ Completed: %s_sample.csv (temporal stratification: trend)''' || E'\n',
                viewname,
                temporal_column, temporal_column, schemaname, viewname,
                sample_rows,
                file_prefix, file_prefix
            )
        
        -- =====================================================================
        -- NON-TEMPORAL VIEWS: Use random sampling with smart sample sizes
        -- =====================================================================
        ELSE
            format(
                '\echo ''[VIEW-%s] Extracting: %s (%s rows of %s total)''' || E'\n' ||
                '\copy (SELECT * FROM %I.%I ORDER BY random() LIMIT %s) TO ''%s_sample.csv'' WITH CSV HEADER' || E'\n' ||
                '\echo ''  ✓ Completed: %s_sample.csv''' || E'\n',
                extraction_type,
                viewname,
                sample_rows,
                row_count,
                schemaname,
                viewname,
                sample_rows,
                file_prefix,
                file_prefix
            )
    END AS extraction_command
FROM view_extract
ORDER BY 
    CASE temporal_granularity
        WHEN 'daily' THEN 1
        WHEN 'weekly' THEN 2
        WHEN 'monthly' THEN 3
        WHEN 'annual' THEN 4
        WHEN 'temporal_trend' THEN 5
        ELSE 6
    END,
    viewname;
\o
\pset format aligned
\pset tuples_only off

\echo ''
\echo 'Phase 3: Executing view extractions...'
\echo ''
\i /tmp/cia_view_extract_commands.sql
\! rm -f /tmp/cia_view_extract_commands.sql

\echo ''
\echo '=================================================='
\echo 'View extraction completed'
\echo '=================================================='
\echo ''

-- ===========================================================================
-- SECTION 5: Generate Extraction Statistics with Temporal Distribution
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== PHASE 5: GENERATING EXTRACTION STATISTICS ==='
\echo '=================================================='
\echo ''

-- Generate comprehensive statistics including temporal stratification info
-- Note: \copy doesn't support CTEs, so we create a temp table first
CREATE TEMP TABLE tmp_extraction_stats AS
WITH view_temporal_classification AS (
    SELECT 
        CASE 
            WHEN matviewname IS NOT NULL THEN matviewname
            ELSE viewname
        END AS view_name,
        c.temporal_granularity
    FROM (
        SELECT viewname, NULL::text AS matviewname FROM pg_views WHERE schemaname = 'public'
        UNION ALL
        SELECT NULL::text AS viewname, matviewname FROM pg_matviews WHERE schemaname = 'public'
    ) v
    CROSS JOIN LATERAL cia_classify_temporal_view(COALESCE(matviewname, viewname)) AS c
),
temporal_summary AS (
    SELECT 
        temporal_granularity,
        COUNT(*) AS view_count,
        CASE temporal_granularity
            WHEN 'daily' THEN 'Last 30 days, 2 samples/day'
            WHEN 'weekly' THEN 'Last 6 months, 2 samples/week'
            WHEN 'monthly' THEN 'Last 3 years, 2 samples/month'
            WHEN 'annual' THEN 'Full history, 2 samples/year'
            WHEN 'temporal_trend' THEN 'All time periods, 1 sample/period'
            ELSE 'Random sampling (no temporal stratification)'
        END AS sampling_strategy
    FROM view_temporal_classification
    GROUP BY temporal_granularity
),
overall_stats AS (
    SELECT 
        'OVERALL' AS category,
        (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%') AS total_tables,
        (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') AS total_views,
        (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public') AS total_materialized_views,
        (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public') AS total_views_all,
        :SAMPLE_SIZE::int AS default_sample_size,
        :PARTY_SAMPLE_SIZE::int AS party_sample_size,
        :COMMITTEE_SAMPLE_SIZE::int AS committee_sample_size,
        :PERSON_SAMPLE_SIZE::int AS person_sample_size,
        :DOCUMENT_SAMPLE_SIZE::int AS document_sample_size,
        :VOTING_SAMPLE_SIZE::int AS voting_sample_size,
        :WORLDBANK_SAMPLE_SIZE::int AS worldbank_sample_size,
        :COMPLETE_EXTRACTION_THRESHOLD::int AS complete_threshold
)
SELECT 
    'Temporal Stratification Summary' AS metric,
    temporal_granularity AS granularity,
    view_count::bigint AS count,
    sampling_strategy AS strategy
FROM temporal_summary
UNION ALL
SELECT 
    'Overall Statistics' AS metric,
    'Total Objects' AS granularity,
    (total_tables + total_views_all)::bigint AS count,
    'Tables: ' || total_tables || ', Views: ' || total_views || ', Mat Views: ' || total_materialized_views AS strategy
FROM overall_stats
UNION ALL
SELECT 
    'Sample Configuration' AS metric,
    'Default Sample Size' AS granularity,
    default_sample_size::bigint AS count,
    'Standard rows per table/view (unless entity-specific or complete extraction)' AS strategy
FROM overall_stats
UNION ALL
SELECT 
    'Sample Configuration' AS metric,
    'Party/Committee/Person Sample' AS granularity,
    party_sample_size::bigint AS count,
    'Extended sample size for political entity tables/views (parties, committees, politicians)' AS strategy
FROM overall_stats
UNION ALL
SELECT 
    'Sample Configuration' AS metric,
    'Document Sample Size' AS granularity,
    document_sample_size::bigint AS count,
    'Extended sample for document, motion, proposition, and bill tables/views' AS strategy
FROM overall_stats
UNION ALL
SELECT 
    'Sample Configuration' AS metric,
    'Voting/Ballot Sample Size' AS granularity,
    voting_sample_size::bigint AS count,
    'Extended sample for vote, ballot, and decision summary tables/views' AS strategy
FROM overall_stats
UNION ALL
SELECT 
    'Sample Configuration' AS metric,
    'Worldbank Sample Size' AS granularity,
    worldbank_sample_size::bigint AS count,
    'Extended sample for worldbank indicators, countries, and economic data' AS strategy
FROM overall_stats
UNION ALL
SELECT 
    'Sample Configuration' AS metric,
    'Complete Extraction Threshold' AS granularity,
    complete_threshold::bigint AS count,
    'Tables/views under this row count are extracted completely' AS strategy
FROM overall_stats
ORDER BY metric, granularity;

\copy (SELECT * FROM tmp_extraction_stats) TO 'extraction_statistics.csv' WITH CSV HEADER

DROP TABLE IF EXISTS tmp_extraction_stats;

\echo '✓ Generated: extraction_statistics.csv'
\echo ''
\echo 'Temporal Distribution Summary:'
\echo ''

-- Display summary on console
SELECT 
    temporal_granularity AS "Granularity",
    COUNT(*) AS "View Count",
    CASE temporal_granularity
        WHEN 'daily' THEN 'Last 30 days, 2 samples/day'
        WHEN 'weekly' THEN 'Last 6 months, 2 samples/week'
        WHEN 'monthly' THEN 'Last 3 years, 2 samples/month'
        WHEN 'annual' THEN 'Full history, 2 samples/year'
        WHEN 'temporal_trend' THEN 'All time periods, 1 sample/period'
        ELSE 'Random sampling'
    END AS "Sampling Strategy"
FROM (
    SELECT 
        CASE 
            WHEN matviewname IS NOT NULL THEN matviewname
            ELSE viewname
        END AS view_name,
        c.temporal_granularity
    FROM (
        SELECT viewname, NULL::text AS matviewname FROM pg_views WHERE schemaname = 'public'
        UNION ALL
        SELECT NULL::text AS viewname, matviewname FROM pg_matviews WHERE schemaname = 'public'
    ) v
    CROSS JOIN LATERAL cia_classify_temporal_view(COALESCE(matviewname, viewname)) AS c
) view_classification
GROUP BY temporal_granularity
ORDER BY 
    CASE temporal_granularity
        WHEN 'daily' THEN 1
        WHEN 'weekly' THEN 2
        WHEN 'monthly' THEN 3
        WHEN 'annual' THEN 4
        WHEN 'temporal_trend' THEN 5
        ELSE 6
    END;

\echo ''
\echo '=================================================='
\echo '=== PHASE 5 COMPLETE: Statistics Generated    ==='
\echo '=================================================='
\echo ''

-- ===========================================================================
-- PHASE 6: DISTRIBUTION & TREND STATISTICS
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== PHASE 6: Distribution & Trend Statistics  ==='
\echo '=================================================='
\echo ''

-- 6.1: Party Distribution Analysis
\echo 'Generating party distribution statistics...'

\copy (SELECT party, COUNT(*) AS member_count, ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS percentage FROM person_data WHERE party IS NOT NULL GROUP BY party ORDER BY member_count DESC) TO 'distribution_party_members.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_party_members.csv'

-- 6.2: Document Type Distribution
\echo 'Generating document type distribution...'

\copy (SELECT document_type, COUNT(*) AS doc_count, ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS percentage FROM document_data WHERE document_type IS NOT NULL GROUP BY document_type ORDER BY doc_count DESC LIMIT 50) TO 'distribution_document_types.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_document_types.csv'

-- 6.3: Committee Activity Distribution
\echo 'Generating committee activity distribution...'

\copy (SELECT org, COUNT(*) AS document_count FROM document_data WHERE org IS NOT NULL AND org != '' GROUP BY org ORDER BY document_count DESC LIMIT 50) TO 'distribution_committee_activity.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_committee_activity.csv'

-- 6.4: Annual Document Volume Trends
\echo 'Generating annual document trends...'

\copy (SELECT EXTRACT(YEAR FROM made_public_date)::int AS year, COUNT(*) AS document_count FROM document_data WHERE made_public_date IS NOT NULL AND made_public_date >= '1990-01-01' GROUP BY EXTRACT(YEAR FROM made_public_date)::int ORDER BY year) TO 'trend_annual_documents.csv' WITH CSV HEADER
\echo '✓ Generated: trend_annual_documents.csv'

-- 6.5: Monthly Document Volume (Last 5 Years)
\echo 'Generating monthly document trends...'

\copy (SELECT DATE_TRUNC('month', made_public_date)::date AS month, COUNT(*) AS document_count FROM document_data WHERE made_public_date >= CURRENT_DATE - INTERVAL '5 years' AND made_public_date IS NOT NULL GROUP BY DATE_TRUNC('month', made_public_date)::date ORDER BY month) TO 'trend_monthly_documents.csv' WITH CSV HEADER
\echo '✓ Generated: trend_monthly_documents.csv'

-- 6.6: Person Status Distribution
\echo 'Generating person status distribution...'

\copy (SELECT status, COUNT(*) AS person_count, ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS percentage FROM person_data WHERE status IS NOT NULL GROUP BY status ORDER BY person_count DESC) TO 'distribution_person_status.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_person_status.csv'

-- 6.7: Gender Distribution by Party
\echo 'Generating gender distribution by party...'

\copy (SELECT party, gender, COUNT(*) AS count FROM person_data WHERE party IS NOT NULL AND gender IS NOT NULL GROUP BY party, gender ORDER BY party, gender) TO 'distribution_gender_by_party.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_gender_by_party.csv'

-- 6.8: Table Size Distribution (Row Counts)
\echo 'Generating table size distribution...'

\copy (SELECT table_name, approx_row_count, CASE WHEN approx_row_count < 100 THEN 'tiny (<100)' WHEN approx_row_count < 1000 THEN 'small (100-1K)' WHEN approx_row_count < 10000 THEN 'medium (1K-10K)' WHEN approx_row_count < 100000 THEN 'large (10K-100K)' WHEN approx_row_count < 1000000 THEN 'very_large (100K-1M)' ELSE 'massive (>1M)' END AS size_category FROM (SELECT relname AS table_name, reltuples::bigint AS approx_row_count FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'public' AND c.relkind = 'r' AND relname NOT LIKE 'qrtz_%' AND relname NOT LIKE 'databasechange%') t ORDER BY approx_row_count DESC) TO 'distribution_table_sizes.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_table_sizes.csv'

-- 6.9: View Size Distribution (Row Counts)
\echo 'Generating view size distribution...'

CREATE TEMP TABLE tmp_view_sizes AS
SELECT view_name, row_count,
    CASE 
        WHEN row_count < 100 THEN 'tiny (<100)'
        WHEN row_count < 1000 THEN 'small (100-1K)'
        WHEN row_count < 10000 THEN 'medium (1K-10K)'
        WHEN row_count < 100000 THEN 'large (10K-100K)'
        WHEN row_count < 1000000 THEN 'very_large (100K-1M)'
        ELSE 'massive (>1M)'
    END AS size_category
FROM (
    SELECT viewname AS view_name, cia_tmp_rowcount('public', viewname) AS row_count
    FROM pg_views WHERE schemaname = 'public'
    UNION ALL
    SELECT matviewname AS view_name, cia_tmp_rowcount('public', matviewname) AS row_count
    FROM pg_matviews WHERE schemaname = 'public'
) v
ORDER BY row_count DESC;

\copy (SELECT * FROM tmp_view_sizes) TO 'distribution_view_sizes.csv' WITH CSV HEADER
DROP TABLE tmp_view_sizes;
\echo '✓ Generated: distribution_view_sizes.csv'

-- 6.10: Size Category Summary
\echo 'Generating size category summary...'

\copy (SELECT size_category, COUNT(*) AS table_count FROM (SELECT CASE WHEN reltuples::bigint < 100 THEN '1_tiny (<100)' WHEN reltuples::bigint < 1000 THEN '2_small (100-1K)' WHEN reltuples::bigint < 10000 THEN '3_medium (1K-10K)' WHEN reltuples::bigint < 100000 THEN '4_large (10K-100K)' WHEN reltuples::bigint < 1000000 THEN '5_very_large (100K-1M)' ELSE '6_massive (>1M)' END AS size_category FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'public' AND c.relkind = 'r' AND relname NOT LIKE 'qrtz_%' AND relname NOT LIKE 'databasechange%') t GROUP BY size_category ORDER BY size_category) TO 'summary_table_size_categories.csv' WITH CSV HEADER
\echo '✓ Generated: summary_table_size_categories.csv'

-- 6.11: Entity Coverage Summary (if person_data has election_region)
\echo 'Generating geographic distribution...'

\copy (SELECT election_region, COUNT(*) AS person_count FROM person_data WHERE election_region IS NOT NULL AND election_region != '' GROUP BY election_region ORDER BY person_count DESC) TO 'distribution_election_regions.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_election_regions.csv'

-- 6.12: Document Status Distribution
\echo 'Generating document status distribution...'

\copy (SELECT status, COUNT(*) AS doc_count, ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS percentage FROM document_data WHERE status IS NOT NULL GROUP BY status ORDER BY doc_count DESC) TO 'distribution_document_status.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_document_status.csv'

-- 6.13: Assignment Distribution by Type (if assignment_data exists)
\echo 'Generating assignment type distribution...'

\copy (SELECT role_code, COUNT(*) AS assignment_count FROM assignment_data WHERE role_code IS NOT NULL GROUP BY role_code ORDER BY assignment_count DESC LIMIT 30) TO 'distribution_assignment_roles.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_assignment_roles.csv'

-- 6.14: Temporal Coverage Analysis
\echo 'Generating temporal coverage analysis...'

\copy (SELECT 'documents' AS data_type, MIN(made_public_date) AS earliest, MAX(made_public_date) AS latest, COUNT(*) AS total_records FROM document_data WHERE made_public_date IS NOT NULL UNION ALL SELECT 'assignments' AS data_type, MIN(from_date) AS earliest, MAX(to_date) AS latest, COUNT(*) AS total_records FROM assignment_data WHERE from_date IS NOT NULL) TO 'summary_temporal_coverage.csv' WITH CSV HEADER
\echo '✓ Generated: summary_temporal_coverage.csv'

-- 6.15: Extraction Type Summary from Manifest
\echo 'Generating extraction type summary...'

\copy (SELECT extraction_type, COUNT(*) AS file_count, SUM(row_count) AS total_rows FROM extraction_manifest GROUP BY extraction_type ORDER BY total_rows DESC) TO 'summary_extraction_types.csv' WITH CSV HEADER
\echo '✓ Generated: summary_extraction_types.csv'

-- ===========================================================================
-- 6.16-6.25: ANNUAL ENTITY DISTRIBUTIONS (for temporal view development)
-- ===========================================================================
\echo ''
\echo 'Generating annual entity distributions for temporal analysis...'

-- 6.16: Party Members by Year (when they were active/assigned)
\echo 'Generating annual party member distribution...'
\copy (SELECT EXTRACT(YEAR FROM a.from_date)::int AS year, p.party, COUNT(DISTINCT p.id) AS active_members FROM person_data p JOIN assignment_data a ON a.person_data_intressent_id = p.id WHERE a.from_date IS NOT NULL AND a.from_date >= '1990-01-01' AND p.party IS NOT NULL GROUP BY EXTRACT(YEAR FROM a.from_date)::int, p.party ORDER BY year, party) TO 'distribution_annual_party_members.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_annual_party_members.csv'

-- 6.17: Documents by Year and Type (showing document type availability over time)
\echo 'Generating annual document type distribution...'
\copy (SELECT EXTRACT(YEAR FROM made_public_date)::int AS year, document_type, COUNT(*) AS doc_count FROM document_data WHERE made_public_date IS NOT NULL AND made_public_date >= '1990-01-01' AND document_type IS NOT NULL GROUP BY EXTRACT(YEAR FROM made_public_date)::int, document_type ORDER BY year, doc_count DESC) TO 'distribution_annual_document_types.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_annual_document_types.csv'

-- 6.18: Documents by Year and Committee (showing committee activity over time)
\echo 'Generating annual committee document distribution...'
\copy (SELECT EXTRACT(YEAR FROM made_public_date)::int AS year, org AS committee, COUNT(*) AS doc_count FROM document_data WHERE made_public_date IS NOT NULL AND made_public_date >= '1990-01-01' AND org IS NOT NULL AND org != '' GROUP BY EXTRACT(YEAR FROM made_public_date)::int, org ORDER BY year, doc_count DESC) TO 'distribution_annual_committee_documents.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_annual_committee_documents.csv'

-- 6.19: Votes by Year and Party (showing voting activity patterns)
\echo 'Generating annual party voting distribution...'
\copy (SELECT EXTRACT(YEAR FROM vote_date)::int AS year, party, COUNT(*) AS vote_count, SUM(CASE WHEN vote = 'Ja' THEN 1 ELSE 0 END) AS yes_votes, SUM(CASE WHEN vote = 'Nej' THEN 1 ELSE 0 END) AS no_votes, SUM(CASE WHEN vote = 'Frånvarande' THEN 1 ELSE 0 END) AS absent FROM vote_data WHERE vote_date IS NOT NULL AND vote_date >= '1990-01-01' AND party IS NOT NULL GROUP BY EXTRACT(YEAR FROM vote_date)::int, party ORDER BY year, vote_count DESC) TO 'distribution_annual_party_votes.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_annual_party_votes.csv'

-- 6.20: Committee Assignments by Year (showing committee existence over time)
\echo 'Generating annual committee assignment distribution...'
\copy (SELECT EXTRACT(YEAR FROM from_date)::int AS year, org_code AS committee, COUNT(*) AS assignment_count, COUNT(DISTINCT intressent_id) AS unique_members FROM assignment_data WHERE from_date IS NOT NULL AND from_date >= '1990-01-01' AND assignment_type = 'uppdrag' AND org_code IS NOT NULL GROUP BY EXTRACT(YEAR FROM from_date)::int, org_code ORDER BY year, assignment_count DESC) TO 'distribution_annual_committee_assignments.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_annual_committee_assignments.csv'

-- 6.21: Ballot Activity by Year (showing ballot volume and outcomes over time)
\echo 'Generating annual ballot summary...'
\copy (SELECT EXTRACT(YEAR FROM vote_date)::int AS year, COUNT(DISTINCT CONCAT(embedded_id_ballot_id, embedded_id_concern, embedded_id_issue)) AS unique_ballots, COUNT(*) AS total_votes, ROUND(AVG(CASE WHEN vote = 'Ja' THEN 1 WHEN vote = 'Nej' THEN 0 END)::numeric, 3) AS avg_yes_rate FROM vote_data WHERE vote_date IS NOT NULL AND vote_date >= '1990-01-01' GROUP BY EXTRACT(YEAR FROM vote_date)::int ORDER BY year) TO 'distribution_annual_ballots.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_annual_ballots.csv'

-- 6.22: Ministry/Government Roles by Year
\echo 'Generating annual ministry assignment distribution...'
\copy (SELECT EXTRACT(YEAR FROM from_date)::int AS year, detail AS ministry, COUNT(*) AS assignment_count, COUNT(DISTINCT intressent_id) AS unique_members FROM assignment_data WHERE from_date IS NOT NULL AND from_date >= '1990-01-01' AND assignment_type = 'Departement' AND detail IS NOT NULL GROUP BY EXTRACT(YEAR FROM from_date)::int, detail ORDER BY year, assignment_count DESC) TO 'distribution_annual_ministry_assignments.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_annual_ministry_assignments.csv'

-- 6.23: Politician First Appearance Year (for career analysis)
\echo 'Generating politician career start distribution...'
\copy (SELECT first_year, party, COUNT(*) AS politicians_started FROM (SELECT p.id, p.party, EXTRACT(YEAR FROM MIN(a.from_date))::int AS first_year FROM person_data p JOIN assignment_data a ON a.person_data_intressent_id = p.id WHERE a.from_date IS NOT NULL AND p.party IS NOT NULL GROUP BY p.id, p.party) career_starts WHERE first_year >= 1990 GROUP BY first_year, party ORDER BY first_year, politicians_started DESC) TO 'distribution_politician_career_starts.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_politician_career_starts.csv'

-- 6.24: Document Status Changes by Year
\echo 'Generating annual document status distribution...'
\copy (SELECT EXTRACT(YEAR FROM made_public_date)::int AS year, status, COUNT(*) AS doc_count FROM document_data WHERE made_public_date IS NOT NULL AND made_public_date >= '1990-01-01' AND status IS NOT NULL GROUP BY EXTRACT(YEAR FROM made_public_date)::int, status ORDER BY year, doc_count DESC) TO 'distribution_annual_document_status.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_annual_document_status.csv'

-- 6.25: Empty Views Report (views with no data that need attention)
\echo 'Generating empty views report...'
CREATE TEMP TABLE tmp_empty_views AS
SELECT view_name, view_type, row_count,
    CASE 
        WHEN view_name LIKE '%risk%' THEN 'risk_analysis'
        WHEN view_name LIKE '%trend%' THEN 'trend_analysis'
        WHEN view_name LIKE '%summary%' THEN 'summary'
        WHEN view_name LIKE '%influence%' THEN 'influence_analysis'
        WHEN view_name LIKE '%anomaly%' THEN 'anomaly_detection'
        WHEN view_name LIKE '%crisis%' THEN 'crisis_analysis'
        ELSE 'other'
    END AS view_category
FROM (
    SELECT viewname AS view_name, 'regular' AS view_type, cia_tmp_rowcount('public', viewname) AS row_count
    FROM pg_views WHERE schemaname = 'public'
    UNION ALL
    SELECT matviewname AS view_name, 'materialized' AS view_type, cia_tmp_rowcount('public', matviewname) AS row_count
    FROM pg_matviews WHERE schemaname = 'public'
) v
WHERE row_count = 0
ORDER BY view_category, view_name;

\copy (SELECT * FROM tmp_empty_views) TO 'report_empty_views.csv' WITH CSV HEADER
DROP TABLE tmp_empty_views;
\echo '✓ Generated: report_empty_views.csv'

\echo ''
\echo '=================================================='
\echo '=== PHASE 6 COMPLETE: Distribution Stats Done ==='
\echo '=================================================='
\echo ''

-- ===========================================================================
-- CLEANUP
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== CLEANUP                                    ==='
\echo '=================================================='

DROP FUNCTION IF EXISTS cia_tmp_rowcount(text, text);
\echo 'Dropped helper function: cia_tmp_rowcount'

DROP FUNCTION IF EXISTS cia_classify_temporal_view(text);
\echo 'Dropped helper function: cia_classify_temporal_view'

\echo ''
\echo '=================================================='
\echo 'CIA Sample Data Extraction COMPLETE'
\echo 'Finished:' `date`
\echo '=================================================='
\echo ''
\echo 'Output files:'
\echo '  - distinct_values/*.csv      : All distinct values for predicate columns'
\echo '  - table_*.csv                : Sample data from tables (random sampling)'
\echo '  - view_*.csv                 : Sample data from views (temporal stratification applied)'
\echo '  - extraction_statistics.csv  : Temporal distribution and coverage metrics'
\echo ''
\echo 'Distribution & Trend Analysis:'
\echo '  - distribution_party_members.csv       : Party membership counts'
\echo '  - distribution_document_types.csv      : Document type breakdown'
\echo '  - distribution_committee_activity.csv  : Committee document activity'
\echo '  - distribution_person_status.csv       : Person status breakdown'
\echo '  - distribution_gender_by_party.csv     : Gender by party analysis'
\echo '  - distribution_election_regions.csv    : Geographic distribution'
\echo '  - distribution_document_status.csv     : Document status breakdown'
\echo '  - distribution_assignment_roles.csv    : Assignment role types'
\echo '  - distribution_table_sizes.csv         : Table row count distribution'
\echo '  - distribution_view_sizes.csv          : View row count distribution'
\echo ''
\echo 'Annual Entity Distributions (for temporal view development):'
\echo '  - distribution_annual_party_members.csv       : Active party members by year'
\echo '  - distribution_annual_document_types.csv      : Document types by year'
\echo '  - distribution_annual_committee_documents.csv : Committee document activity by year'
\echo '  - distribution_annual_party_votes.csv         : Party voting patterns by year'
\echo '  - distribution_annual_committee_assignments.csv: Committee assignments by year'
\echo '  - distribution_annual_ballots.csv             : Ballot volume by year'
\echo '  - distribution_annual_ministry_assignments.csv: Ministry assignments by year'
\echo '  - distribution_politician_career_starts.csv   : When politicians started careers'
\echo '  - distribution_annual_document_status.csv     : Document status by year'
\echo ''
\echo 'Reports:'
\echo '  - report_empty_views.csv     : Views with no data (need investigation)'
\echo ''
\echo 'Trend Analysis:'
\echo '  - trend_annual_documents.csv   : Yearly document volume since 1990'
\echo '  - trend_monthly_documents.csv  : Monthly document volume (last 5 years)'
\echo ''
\echo 'Summary Statistics:'
\echo '  - summary_table_size_categories.csv  : Tables by size category'
\echo '  - summary_temporal_coverage.csv      : Data time range coverage'
\echo '  - summary_extraction_types.csv       : Extraction type breakdown'
\echo ''
\echo 'Sample Size Configuration:'
\echo '  - Default: 200 rows (most tables/views)'
\echo '  - Political entities (party/committee/person): 500 rows'
\echo '  - Document/Voting/Worldbank data: 300 rows'
\echo '  - Complete extraction for tables/views under 3000 rows'
\echo ''
\echo 'Temporal Stratification Applied:'
\echo '  - Daily views: 2 samples per day over last 30 days'
\echo '  - Weekly views: 2 samples per week over last 6 months'
\echo '  - Monthly views: 2 samples per month over last 3 years'
\echo '  - Annual views: 2 samples per year over full history'
\echo '  - Temporal trend views: 1 sample per time period'
\echo '  - Non-temporal views: Random sampling (existing behavior)'
\echo ''
