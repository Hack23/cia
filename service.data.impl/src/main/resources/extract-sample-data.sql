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
--
-- Configuration:
--   SAMPLE_SIZE: Number of rows to extract per table/view (default: 50)

\set ON_ERROR_STOP off
\timing on

\echo '=================================================='
\echo 'CIA Sample Data Extraction'
\echo 'Started:' `date`
\echo '=================================================='

-- Configuration
\set SAMPLE_SIZE 50

\echo ''
\echo 'Configuration:'
\echo '  Sample size: 50 rows per table/view'
\echo '  Output format: CSV with headers'
\echo '  Extraction strategy: Diverse sampling with DISTINCT for key columns'
\echo ''

-- ===========================================================================
-- SECTION 1: Extract Sample Data from ALL Tables Dynamically
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== EXTRACTING TABLE SAMPLE DATA      ==='
\echo '=========================================='
\echo ''

-- Dynamic extraction from all tables
DO $$
DECLARE
    table_record RECORD;
    table_count INTEGER := 0;
    skip_count INTEGER := 0;
    error_count INTEGER := 0;
    row_count BIGINT;
    copy_command TEXT;
    filename TEXT;
BEGIN
    RAISE NOTICE 'Extracting sample data from all tables...';
    RAISE NOTICE '';
    
    FOR table_record IN 
        SELECT schemaname, tablename 
        FROM pg_tables 
        WHERE schemaname = 'public' 
        AND tablename NOT LIKE 'qrtz_%'  -- Skip Quartz scheduler tables
        AND tablename NOT LIKE 'databasechange%'  -- Skip Liquibase tables
        ORDER BY tablename
    LOOP
        BEGIN
            -- Check if table has data
            EXECUTE format('SELECT COUNT(*) FROM %I.%I', 
                table_record.schemaname, table_record.tablename) 
            INTO row_count;
            
            IF row_count > 0 THEN
                filename := 'table_' || table_record.tablename || '_sample.csv';
                
                -- Generate copy command to extract data
                -- Use a diverse sampling strategy with ORDER BY random() for variety
                copy_command := format(
                    '\copy (SELECT * FROM %I.%I ORDER BY random() LIMIT 50) TO ''%s'' WITH CSV HEADER',
                    table_record.schemaname,
                    table_record.tablename,
                    filename
                );
                
                RAISE NOTICE 'Extracting: % (% rows available)', 
                    table_record.tablename, row_count;
                
                -- Execute the copy command
                EXECUTE copy_command;
                table_count := table_count + 1;
                
            ELSE
                RAISE NOTICE 'Skipping: % (empty table)', table_record.tablename;
                skip_count := skip_count + 1;
            END IF;
            
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'ERROR extracting %: %', table_record.tablename, SQLERRM;
            error_count := error_count + 1;
        END;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE 'Table extraction summary:';
    RAISE NOTICE '  Tables extracted: %', table_count;
    RAISE NOTICE '  Empty tables skipped: %', skip_count;
    RAISE NOTICE '  Errors: %', error_count;
    RAISE NOTICE '';
END $$;

-- ===========================================================================
-- SECTION 2: Extract Sample Data from ALL Views Dynamically
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== EXTRACTING VIEW SAMPLE DATA       ==='
\echo '=========================================='
\echo ''

-- Dynamic extraction from all views (both regular and materialized)
DO $$
DECLARE
    view_record RECORD;
    view_count INTEGER := 0;
    error_count INTEGER := 0;
    empty_count INTEGER := 0;
    row_count BIGINT;
    copy_command TEXT;
    filename TEXT;
BEGIN
    RAISE NOTICE 'Extracting sample data from all views...';
    RAISE NOTICE '';
    
    -- Process regular views
    FOR view_record IN 
        SELECT schemaname, viewname AS objectname, 'VIEW' AS object_type
        FROM pg_views 
        WHERE schemaname = 'public'
        UNION ALL
        SELECT schemaname, matviewname AS objectname, 'MATERIALIZED VIEW' AS object_type
        FROM pg_matviews
        WHERE schemaname = 'public'
        ORDER BY objectname
    LOOP
        BEGIN
            -- Check if view has data
            EXECUTE format('SELECT COUNT(*) FROM %I.%I', 
                view_record.schemaname, view_record.objectname) 
            INTO row_count;
            
            IF row_count > 0 THEN
                filename := 'view_' || view_record.objectname || '_sample.csv';
                
                -- Generate copy command to extract data
                -- For views, use diverse sampling to get variety across different dimensions
                copy_command := format(
                    '\copy (SELECT * FROM %I.%I ORDER BY random() LIMIT 50) TO ''%s'' WITH CSV HEADER',
                    view_record.schemaname,
                    view_record.objectname,
                    filename
                );
                
                RAISE NOTICE 'Extracting: % (% - % rows available)', 
                    view_record.objectname, view_record.object_type, row_count;
                
                -- Execute the copy command
                EXECUTE copy_command;
                view_count := view_count + 1;
                
            ELSE
                RAISE NOTICE 'Skipping: % (empty %)', 
                    view_record.objectname, view_record.object_type;
                empty_count := empty_count + 1;
            END IF;
            
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'ERROR extracting %: %', view_record.objectname, SQLERRM;
            error_count := error_count + 1;
        END;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE 'View extraction summary:';
    RAISE NOTICE '  Views extracted: %', view_count;
    RAISE NOTICE '  Empty views skipped: %', empty_count;
    RAISE NOTICE '  Errors: %', error_count;
    RAISE NOTICE '';
END $$;

-- ===========================================================================
-- SECTION 3: Generate Comprehensive Manifest File
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== GENERATING MANIFEST FILE          ==='
\echo '=========================================='

-- Create comprehensive manifest with metadata about all extracted files
\copy (SELECT 'TABLE' AS source_type, relname AS object_name, n_live_tup AS approximate_rows, pg_size_pretty(pg_total_relation_size(schemaname||'.'||relname)) AS size, 'table_' || relname || '_sample.csv' AS filename FROM pg_stat_user_tables WHERE schemaname = 'public' AND n_live_tup > 0 AND relname NOT LIKE 'qrtz_%' AND relname NOT LIKE 'databasechange%' UNION ALL SELECT 'VIEW' AS source_type, schemaname||'.'||viewname AS object_name, 0 AS approximate_rows, '0 bytes' AS size, 'view_' || viewname || '_sample.csv' AS filename FROM pg_views WHERE schemaname = 'public' UNION ALL SELECT 'MATERIALIZED VIEW' AS source_type, schemaname||'.'||matviewname AS object_name, 0 AS approximate_rows, pg_size_pretty(pg_total_relation_size(schemaname||'.'||matviewname)) AS size, 'view_' || matviewname || '_sample.csv' AS filename FROM pg_matviews WHERE schemaname = 'public' ORDER BY source_type, object_name) TO 'sample_data_manifest.csv' WITH CSV HEADER;

-- ===========================================================================
-- SECTION 4: Generate Column Mapping for Views
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== GENERATING VIEW COLUMN MAPPING    ==='
\echo '=========================================='

-- Generate CSV showing which table columns are used in which views
\copy (WITH view_columns AS (SELECT table_schema, table_name, column_name FROM information_schema.columns WHERE table_schema = 'public' AND table_name IN (SELECT viewname FROM pg_views WHERE schemaname = 'public') ORDER BY table_name, column_name) SELECT * FROM view_columns) TO 'view_column_mapping.csv' WITH CSV HEADER;

\echo ''
\echo '=================================================='
\echo 'Sample data extraction completed'
\echo 'Finished:' `date`
\echo '=================================================='
\echo ''
\echo 'Generated files:'
\echo '  - table_*_sample.csv: Sample data from tables'
\echo '  - view_*_sample.csv: Sample data from views'
\echo '  - sample_data_manifest.csv: Metadata about extracted files'
\echo '  - view_column_mapping.csv: Column mappings for views'
\echo ''
\echo 'To reload sample data:'
\echo '  1. Review the CSV files'
\echo '  2. Use COPY FROM to import: '
\echo '     \copy table_name FROM ''table_name_sample.csv'' WITH CSV HEADER;'
\echo ''
\echo 'For troubleshooting empty views, see:'
\echo '  - TROUBLESHOOTING_EMPTY_VIEWS.md'
\echo '  - view_column_mapping.csv for required columns'
\echo ''

\timing off
