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
\echo '  Extraction strategy: Diverse sampling with DISTINCT for key columns'
\echo '  Distinct value extraction: All tables/views with categorical columns'
\echo '  View analysis: ANALYZE will be run on all views before extraction'
\echo ''

-- ===========================================================================
-- SECTION 1: Analyze All Views for Statistics
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== ANALYZING ALL VIEWS                       ==='
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
\echo '=== EXTRACTING TABLE SAMPLE DATA              ==='
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
-- SECTION 3: Extract Sample Data from ALL Views Dynamically
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== EXTRACTING VIEW SAMPLE DATA               ==='
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
    -- Get total view count
    SELECT COUNT(*) INTO total_views
    FROM (
        SELECT viewname FROM pg_views WHERE schemaname = 'public'
        UNION ALL
        SELECT matviewname FROM pg_matviews WHERE schemaname = 'public'
    ) v;
    
    RAISE NOTICE '';
    RAISE NOTICE 'Phase 1: Analyzing % views for row counts', total_views;
    RAISE NOTICE 'This may take several minutes for complex views...';
    RAISE NOTICE '';
    
    -- Process each view with progress BEFORE the slow operation
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
    UNION ALL
    SELECT schemaname,
           matviewname AS viewname,
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
-- SECTION 4: Extract Distinct Values for ALL Tables Dynamically
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== EXTRACTING DISTINCT VALUE SETS            ==='
\echo '=================================================='
\echo ''
\echo 'Analyzing tables for categorical columns...'
\echo ''

\! rm -f :DISTINCT_CMD_FILE
\pset format unaligned
\pset tuples_only on
\o :DISTINCT_CMD_FILE

WITH table_columns AS (
    SELECT 
        t.schemaname,
        t.tablename,
        c.column_name,
        c.data_type,
        c.character_maximum_length
    FROM pg_tables t
    JOIN information_schema.columns c 
        ON c.table_schema = t.schemaname 
        AND c.table_name = t.tablename
    WHERE t.schemaname = 'public'
        AND t.tablename NOT LIKE 'qrtz_%'
        AND t.tablename NOT LIKE 'databasechange%'
        AND c.data_type IN ('character varying', 'text', 'character', 'USER-DEFINED')
        AND (c.character_maximum_length IS NULL OR c.character_maximum_length < 500)
),
tables_with_data AS (
    SELECT DISTINCT
        schemaname,
        tablename
    FROM table_columns tc
    WHERE cia_tmp_rowcount(tc.schemaname, tc.tablename) > 0
),
distinct_extracts AS (
    SELECT 
        tc.schemaname,
        tc.tablename,
        tc.column_name,
        'distinct_' || tc.tablename || '_' || tc.column_name || '_values.csv' AS filename
    FROM table_columns tc
    JOIN tables_with_data twd 
        ON twd.schemaname = tc.schemaname 
        AND twd.tablename = tc.tablename
)
SELECT format(
    '\echo ''[DISTINCT] %I.%I.%I -> %s''' || E'\n' ||
    '\copy (SELECT %I as value, COUNT(*) as count FROM %I.%I WHERE %I IS NOT NULL GROUP BY %I ORDER BY count DESC, %I LIMIT 1000) TO ''%s'' WITH CSV HEADER' || E'\n' ||
    '\echo ''  ✓ Completed: %s''' || E'\n',
    schemaname,
    tablename,
    column_name,
    filename,
    column_name,
    schemaname,
    tablename,
    column_name,
    column_name,
    column_name,
    filename,
    filename
)
FROM distinct_extracts
ORDER BY tablename, column_name;

\o
\pset format aligned
\pset tuples_only off

\echo 'Executing distinct value extractions...'
\echo '(This extracts unique values from text/varchar columns)'
\echo ''
\i :DISTINCT_CMD_FILE
\! rm -f :DISTINCT_CMD_FILE

\echo ''
\echo 'Distinct value extraction completed'
\echo ''

-- ===========================================================================
-- SECTION 5: Generate Comprehensive Manifest File
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== GENERATING MANIFEST FILE                  ==='
\echo '=================================================='
\echo ''

\echo 'Generating sample_data_manifest.csv...'
\copy (SELECT 'TABLE' AS source_type, relname AS object_name, n_live_tup AS approximate_rows, pg_size_pretty(pg_total_relation_size(schemaname||'.'||relname)) AS size, 'table_' || relname || '_sample.csv' AS filename FROM pg_stat_user_tables WHERE schemaname = 'public' AND n_live_tup > 0 AND relname NOT LIKE 'qrtz_%' AND relname NOT LIKE 'databasechange%' UNION ALL SELECT 'VIEW' AS source_type, schemaname||'.'||viewname AS object_name, 0 AS approximate_rows, '0 bytes' AS size, 'view_' || viewname || '_sample.csv' AS filename FROM pg_views WHERE schemaname = 'public' UNION ALL SELECT 'MATERIALIZED VIEW' AS source_type, schemaname||'.'||matviewname AS object_name, 0 AS approximate_rows, pg_size_pretty(pg_total_relation_size(schemaname||'.'||matviewname)) AS size, 'view_' || matviewname || '_sample.csv' AS filename FROM pg_matviews WHERE schemaname = 'public' ORDER BY source_type, object_name) TO 'sample_data_manifest.csv' WITH CSV HEADER;
\echo '  ✓ Completed: sample_data_manifest.csv'

-- ===========================================================================
-- SECTION 6: Generate Column Mapping for Views
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== GENERATING VIEW COLUMN MAPPING            ==='
\echo '=================================================='
\echo ''

\echo 'Generating view_column_mapping.csv...'
\copy (WITH view_columns AS (SELECT table_schema, table_name, column_name FROM information_schema.columns WHERE table_schema = 'public' AND table_name IN (SELECT viewname FROM pg_views WHERE schemaname = 'public') ORDER BY table_name, column_name) SELECT * FROM view_columns) TO 'view_column_mapping.csv' WITH CSV HEADER;
\echo '  ✓ Completed: view_column_mapping.csv'

-- ===========================================================================
-- SECTION 7: Generate Extraction Statistics
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== EXTRACTION STATISTICS                     ==='
\echo '=================================================='
\echo ''

\echo 'Generating extraction_statistics.csv...'
\copy (SELECT 'TABLES' as category, COUNT(*) as total_in_schema, (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%') as extracted_count, (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND (tablename LIKE 'qrtz_%' OR tablename LIKE 'databasechange%')) as excluded_count, ROUND((SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%')::numeric / NULLIF(COUNT(*), 0) * 100, 2) as coverage_pct FROM pg_tables WHERE schemaname = 'public' UNION ALL SELECT 'REGULAR_VIEWS', COUNT(*), COUNT(*), 0, 100.00 FROM pg_views WHERE schemaname = 'public' UNION ALL SELECT 'MATERIALIZED_VIEWS', COUNT(*), COUNT(*), 0, 100.00 FROM pg_matviews WHERE schemaname = 'public' UNION ALL SELECT 'TOTAL', (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public'), (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%') + (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public'), (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND (tablename LIKE 'qrtz_%' OR tablename LIKE 'databasechange%')), ROUND(((SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%') + (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public'))::numeric / NULLIF((SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public'), 0) * 100, 2)) TO 'extraction_statistics.csv' WITH CSV HEADER;
\echo '  ✓ Completed: extraction_statistics.csv'
\echo ''

-- Display statistics summary
DO $$
DECLARE
    total_tables INTEGER;
    extracted_tables INTEGER;
    excluded_tables INTEGER;
    regular_views INTEGER;
    mat_views INTEGER;
    total_objects INTEGER;
    total_extracted INTEGER;
    coverage_pct NUMERIC;
    distinct_files INTEGER;
BEGIN
    SELECT COUNT(*) INTO total_tables FROM pg_tables WHERE schemaname = 'public';
    SELECT COUNT(*) INTO extracted_tables FROM pg_tables WHERE schemaname = 'public' 
        AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%';
    SELECT COUNT(*) INTO excluded_tables FROM pg_tables WHERE schemaname = 'public' 
        AND (tablename LIKE 'qrtz_%' OR tablename LIKE 'databasechange%');
    SELECT COUNT(*) INTO regular_views FROM pg_views WHERE schemaname = 'public';
    SELECT COUNT(*) INTO mat_views FROM pg_matviews WHERE schemaname = 'public';
    
    -- Estimate distinct value files (one per text/varchar column in non-empty tables)
    SELECT COUNT(*) INTO distinct_files
    FROM information_schema.columns c
    JOIN pg_tables t ON t.schemaname = c.table_schema AND t.tablename = c.table_name
    WHERE c.table_schema = 'public'
        AND t.tablename NOT LIKE 'qrtz_%'
        AND t.tablename NOT LIKE 'databasechange%'
        AND c.data_type IN ('character varying', 'text', 'character', 'USER-DEFINED')
        AND (c.character_maximum_length IS NULL OR c.character_maximum_length < 500);
    
    total_objects := total_tables + regular_views + mat_views;
    total_extracted := extracted_tables + regular_views + mat_views;
    coverage_pct := ROUND((total_extracted::numeric / NULLIF(total_objects, 0) * 100), 2);
    
    RAISE NOTICE '';
    RAISE NOTICE '================================================';
    RAISE NOTICE 'FINAL COVERAGE SUMMARY';
    RAISE NOTICE '================================================';
    RAISE NOTICE '';
    RAISE NOTICE 'Objects:';
    RAISE NOTICE '  Total Tables: % (% extracted, % excluded)', total_tables, extracted_tables, excluded_tables;
    RAISE NOTICE '  Regular Views: % (all extracted)', regular_views;
    RAISE NOTICE '  Materialized Views: % (all extracted)', mat_views;
    RAISE NOTICE '  Total Objects: % (% extracted = %%)', total_objects, total_extracted, coverage_pct;
    RAISE NOTICE '';
    RAISE NOTICE 'Expected CSV Files:';
    RAISE NOTICE '  Table CSVs: %', extracted_tables;
    RAISE NOTICE '  View CSVs: %', regular_views + mat_views;
    RAISE NOTICE '  Distinct value CSVs: % (estimated)', distinct_files;
    RAISE NOTICE '  Metadata CSVs: 3 (manifest + column mapping + statistics)';
    RAISE NOTICE '  Total Expected: %', extracted_tables + regular_views + mat_views + distinct_files + 3;
    RAISE NOTICE '';
    RAISE NOTICE '================================================';
END $$;

\echo ''
\echo '=================================================='
\echo 'Sample data extraction completed'
\echo 'Finished:' `date`
\echo '=================================================='
\echo ''
\echo 'Generated files:'
\echo '  - table_*_sample.csv: Sample data from tables'
\echo '  - view_*_sample.csv: Sample data from views'
\echo '  - distinct_*_values.csv: Distinct values for all text/varchar columns'
\echo '  - sample_data_manifest.csv: Metadata about extracted files'
\echo '  - view_column_mapping.csv: Column mappings for views'
\echo '  - extraction_statistics.csv: Coverage statistics'
\echo ''
\echo 'To reload sample data:'
\echo '  1. Review the CSV files'
\echo '  2. Use \copy FROM to import: '
\echo '     \copy table_name FROM ''table_name_sample.csv'' WITH CSV HEADER;'
\echo ''
\echo 'For troubleshooting empty views, see:'
\echo '  - TROUBLESHOOTING_EMPTY_VIEWS.md'
\echo '  - view_column_mapping.csv for required columns'
\echo ''

DROP FUNCTION IF EXISTS cia_tmp_rowcount(text, text);

\timing off
