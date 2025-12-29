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

-- Configuration
\set SAMPLE_SIZE 50
\set TABLE_CMD_FILE '/tmp/cia_table_extract_commands.sql'
\set VIEW_CMD_FILE '/tmp/cia_view_extract_commands.sql'
\set DISTINCT_CMD_FILE '/tmp/cia_distinct_extract_commands.sql'

\echo ''
\echo '=================================================='
\echo '=== INITIALIZATION                            ==='
\echo '=================================================='

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
\echo 'Configuration:'
\echo '  Sample size: 50 rows per table/view'
\echo '  Output format: CSV with headers'
\echo ''

-- ===========================================================================
-- SECTION 0: REFRESH ALL MATERIALIZED VIEWS
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
    row_count BIGINT;
    pass_number INTEGER := 1;
    max_passes INTEGER := 3;
    views_refreshed_this_pass INTEGER;
    current_pass_number INTEGER := 1;
BEGIN
    start_time := clock_timestamp();
    
    -- Get total count for progress reporting
    SELECT COUNT(*) INTO total_mvs FROM pg_matviews WHERE schemaname = 'public';
    
    RAISE NOTICE '================================================';
    RAISE NOTICE 'Found % materialized views to refresh', total_mvs;
    RAISE NOTICE 'Using multi-pass approach to handle dependencies';
    RAISE NOTICE '================================================';
    RAISE NOTICE '';
    
    -- Multi-pass refresh to handle dependencies
    -- Pass 1: Refresh base views (no dependencies on other materialized views)
    -- Pass 2-3: Refresh dependent views (may fail in early passes if dependencies not ready)
    FOR pass_number IN 1..max_passes LOOP
        views_refreshed_this_pass := 0;
        current_pass_number := 1;
        
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
                -- Check if already populated (successful in previous pass)
                EXECUTE format('SELECT COUNT(*) FROM %I.%I', 
                    mv_record.schemaname, mv_record.matviewname) INTO row_count;
                
                -- If view has data and this is not pass 1, skip it
                IF row_count > 0 AND pass_number > 1 THEN
                    RAISE NOTICE '  [SKIP %/%] %.% - already populated (% rows)', 
                        current_pass_number, total_mvs,
                        mv_record.schemaname, mv_record.matviewname, row_count;
                    current_pass_number := current_pass_number + 1;
                    CONTINUE;
                END IF;
                
                RAISE NOTICE '→ [Pass % - View %/%] Refreshing: %.%', 
                    pass_number, current_pass_number, total_mvs,
                    mv_record.schemaname, mv_record.matviewname;
                
                -- Refresh the materialized view
                EXECUTE format('REFRESH MATERIALIZED VIEW %I.%I', 
                    mv_record.schemaname, mv_record.matviewname);
                
                -- Check row count after refresh
                EXECUTE format('SELECT COUNT(*) FROM %I.%I', 
                    mv_record.schemaname, mv_record.matviewname) INTO row_count;
                
                success_count := success_count + 1;
                views_refreshed_this_pass := views_refreshed_this_pass + 1;
                
                IF row_count > 0 THEN
                    RAISE NOTICE '  ✓ Refreshed successfully - % rows', row_count;
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
            
            current_pass_number := current_pass_number + 1;
        END LOOP;
        
        RAISE NOTICE 'Pass % complete: % views refreshed', pass_number, views_refreshed_this_pass;
        RAISE NOTICE '';
        
        -- If no views were refreshed in this pass, stop early
        IF views_refreshed_this_pass = 0 THEN
            RAISE NOTICE 'No views refreshed in this pass - stopping early';
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
-- SECTION 2: Analyze All Views for Statistics
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== PHASE 2: ANALYZING ALL VIEWS              ==='
\echo '=================================================='
\echo ''
\echo 'Running ANALYZE on all views (regular and materialized)...'
\echo ''

DO $$
DECLARE
    view_record RECORD;
    view_count INTEGER := 0;
    success_count INTEGER := 0;
    error_count INTEGER := 0;
    mat_view_count INTEGER := 0;
    reg_view_count INTEGER := 0;
    start_time TIMESTAMP;
    end_time TIMESTAMP;
    duration INTERVAL;
    total_views INTEGER;
BEGIN
    start_time := clock_timestamp();
    
    -- Get total count for progress reporting
    SELECT 
        (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public') +
        (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public')
    INTO total_views;
    
    RAISE NOTICE '================================================';
    RAISE NOTICE 'Starting ANALYZE of % total views', total_views;
    RAISE NOTICE '  - Materialized views: %', (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public');
    RAISE NOTICE '  - Regular views: %', (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public');
    RAISE NOTICE '================================================';
    RAISE NOTICE '';
    
    -- Analyze materialized views first
    RAISE NOTICE '--- Analyzing Materialized Views ---';
    RAISE NOTICE '';
    
    FOR view_record IN 
        SELECT schemaname, matviewname AS viewname, 'MATERIALIZED VIEW' AS view_type
        FROM pg_matviews
        WHERE schemaname = 'public'
        ORDER BY matviewname
    LOOP
        BEGIN
            view_count := view_count + 1;
            mat_view_count := mat_view_count + 1;
            RAISE NOTICE 'ANALYZE [%/%]: %.% (%)', view_count, total_views,
                view_record.schemaname, view_record.viewname, view_record.view_type;
            
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
    
    -- Now analyze regular views
    RAISE NOTICE '';
    RAISE NOTICE '--- Analyzing Regular Views ---';
    RAISE NOTICE '';
    
    FOR view_record IN 
        SELECT schemaname, viewname, 'VIEW' AS view_type
        FROM pg_views
        WHERE schemaname = 'public'
        ORDER BY viewname
    LOOP
        BEGIN
            view_count := view_count + 1;
            reg_view_count := reg_view_count + 1;
            RAISE NOTICE 'ANALYZE [%/%]: %.% (%)', view_count, total_views,
                view_record.schemaname, view_record.viewname, view_record.view_type;
            
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
    RAISE NOTICE '  Total views analyzed: %', view_count;
    RAISE NOTICE '    - Materialized views: %', mat_view_count;
    RAISE NOTICE '    - Regular views: %', reg_view_count;
    RAISE NOTICE '  Successfully analyzed: %', success_count;
    RAISE NOTICE '  Errors: %', error_count;
    RAISE NOTICE '  Duration: %', duration;
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
           LEAST(:SAMPLE_SIZE::int, row_count) AS sample_rows,
           CASE WHEN tablename LIKE 'table_%' THEN tablename ELSE 'table_' || tablename END AS file_prefix
    FROM table_counts
    WHERE row_count > 0
)
SELECT format(
    '\echo ''[TABLE] Extracting: %I.%I (%s rows sampled of %s total)''' || E'\n' ||
    '\copy (SELECT * FROM %I.%I LIMIT %s) TO ''%s_sample.csv'' CSV HEADER' || E'\n' ||
    '\echo ''  ✓ Completed: %s_sample.csv''' || E'\n',
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
view_extract AS (
    SELECT schemaname,
           viewname,
           row_count,
           LEAST(:SAMPLE_SIZE::int, row_count) AS sample_rows,
           CASE WHEN viewname LIKE 'view_%' THEN viewname ELSE 'view_' || viewname END AS file_prefix
    FROM view_counts
    WHERE row_count > 0
)
SELECT format(
    '\echo ''[VIEW] Extracting: %s (%s rows sampled of %s total)''' || E'\n' ||
    '\copy (SELECT * FROM %I.%I ORDER BY random() LIMIT %s) TO ''%s_sample.csv'' WITH CSV HEADER' || E'\n' ||
    '\echo ''  ✓ Completed: %s_sample.csv''' || E'\n',
    viewname,
    sample_rows,
    row_count,
    schemaname,
    viewname,
    sample_rows,
    file_prefix,
    file_prefix
)
FROM view_extract
ORDER BY viewname;
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
-- CLEANUP
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== CLEANUP                                    ==='
\echo '=================================================='

DROP FUNCTION IF EXISTS cia_tmp_rowcount(text, text);
\echo 'Dropped helper function: cia_tmp_rowcount'

\echo ''
\echo '=================================================='
\echo 'CIA Sample Data Extraction COMPLETE'
\echo 'Finished:' `date`
\echo '=================================================='
\echo ''
\echo 'Output files:'
\echo '  - distinct_values/*.csv  : All distinct values for predicate columns'
\echo '  - table_*.csv            : Sample data from tables'
\echo '  - view_*.csv             : Sample data from views'
\echo ''
