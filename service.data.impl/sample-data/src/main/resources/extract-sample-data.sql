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
\set TABLE_CMD_FILE '/tmp/cia_table_extract_commands.sql'
\set VIEW_CMD_FILE '/tmp/cia_view_extract_commands.sql'

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
END;
$$;

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
    '\echo ''Extracting table: %I.%I (%s rows sampled of %s rows total)''' || E'\n' ||
    '\copy (SELECT * FROM %I.%I LIMIT %s) TO ''%s_sample.csv'' CSV HEADER',
    schemaname,
    tablename,
    sample_rows,
    row_count,
    schemaname,
    tablename,
    sample_rows,
    file_prefix
)
FROM table_extract
ORDER BY tablename;
\o
\pset format aligned
\pset tuples_only off

\i :TABLE_CMD_FILE
\! rm -f :TABLE_CMD_FILE

-- ===========================================================================
-- SECTION 2: Extract Sample Data from ALL Views Dynamically
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== EXTRACTING VIEW SAMPLE DATA       ==='
\echo '=========================================='
\echo ''

-- Analyze which views have data (both regular and materialized)
DO $$
DECLARE
    view_record RECORD;
    view_count INTEGER := 0;
    empty_count INTEGER := 0;
    row_count BIGINT;
BEGIN
    RAISE NOTICE 'Analyzing views for sample data extraction...';
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
                RAISE NOTICE 'View %: % rows available (%)', 
                    view_record.objectname, row_count, view_record.object_type;
                view_count := view_count + 1;
            ELSE
                RAISE NOTICE 'Skipping %: empty %', 
                    view_record.objectname, view_record.object_type;
                empty_count := empty_count + 1;
            END IF;
            
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'ERROR checking %: %', view_record.objectname, SQLERRM;
        END;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE 'View analysis summary:';
    RAISE NOTICE '  Views with data: %', view_count;
    RAISE NOTICE '  Empty views: %', empty_count;
    RAISE NOTICE '';
END $$;

-- Now extract sample data from all non-empty views using dynamic \copy commands
\echo 'Extracting sample data from regular views...'
\echo ''

\! rm -f :VIEW_CMD_FILE
\pset format unaligned
\pset tuples_only on
\o :VIEW_CMD_FILE
WITH all_views AS (
    SELECT schemaname, viewname AS object_name, 'VIEW' AS object_type
    FROM pg_views
    WHERE schemaname = 'public'
    UNION ALL
    SELECT schemaname, matviewname AS object_name, 'MATERIALIZED VIEW' AS object_type
    FROM pg_matviews
    WHERE schemaname = 'public'
),
view_counts AS (
    SELECT schemaname,
           object_name,
           object_type,
           cia_tmp_rowcount(schemaname, object_name) AS row_count
    FROM all_views
),
view_extract AS (
    SELECT schemaname,
           object_name,
           object_type,
           row_count,
           LEAST(:SAMPLE_SIZE::int, row_count) AS sample_rows,
           CASE WHEN object_name LIKE 'view_%' THEN object_name ELSE 'view_' || object_name END AS file_prefix
    FROM view_counts
    WHERE row_count > 0
)
SELECT format(
    '\echo ''Extracting %s: %s''' || E'\n' ||
    '\copy (SELECT * FROM %I.%I ORDER BY random() LIMIT %s) TO ''%s_sample.csv'' WITH CSV HEADER',
    lower(object_type),
    object_name,
    schemaname,
    object_name,
    sample_rows,
    file_prefix
)
FROM view_extract
ORDER BY object_name;
\o
\pset format aligned
\pset tuples_only off

\i :VIEW_CMD_FILE
\! rm -f :VIEW_CMD_FILE

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

-- ===========================================================================
-- SECTION 5: Extract Distinct Values for Important Columns
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== EXTRACTING DISTINCT VALUE SETS    ==='
\echo '=========================================='

-- Extract distinct values with counts for columns commonly used in views
-- This helps understand data distribution and aids in view debugging

\echo 'Extracting distinct party values...'
\copy (SELECT party, COUNT(*) as count FROM person_data WHERE party IS NOT NULL GROUP BY party ORDER BY count DESC, party) TO 'distinct_party_values.csv' WITH CSV HEADER;

\echo 'Extracting distinct org_code values...'
\copy (SELECT org_code, COUNT(*) as count FROM assignment_data WHERE org_code IS NOT NULL GROUP BY org_code ORDER BY count DESC, org_code) TO 'distinct_org_code_values.csv' WITH CSV HEADER;

\echo 'Extracting distinct role_code values...'
\copy (SELECT role_code, COUNT(*) as count FROM assignment_data WHERE role_code IS NOT NULL GROUP BY role_code ORDER BY count DESC, role_code) TO 'distinct_role_code_values.csv' WITH CSV HEADER;

\echo 'Extracting distinct assignment status values...'
\copy (SELECT status, COUNT(*) as count FROM assignment_data WHERE status IS NOT NULL GROUP BY status ORDER BY count DESC, status) TO 'distinct_assignment_status_values.csv' WITH CSV HEADER;

\echo 'Extracting distinct document types...'
\copy (SELECT document_type, COUNT(*) as count FROM document_data WHERE document_type IS NOT NULL GROUP BY document_type ORDER BY count DESC, document_type) TO 'distinct_document_type_values.csv' WITH CSV HEADER;

\echo 'Extracting distinct document org values...'
\copy (SELECT org, COUNT(*) as count FROM document_data WHERE org IS NOT NULL GROUP BY org ORDER BY count DESC, org) TO 'distinct_document_org_values.csv' WITH CSV HEADER;

\echo 'Extracting distinct vote values...'
\copy (SELECT vote, party, COUNT(*) as count FROM vote_data WHERE vote IS NOT NULL GROUP BY vote, party ORDER BY count DESC, vote, party LIMIT 100) TO 'distinct_vote_values.csv' WITH CSV HEADER;

\echo 'Extracting political party list...'
\copy (SELECT party_id, party_name, short_code FROM sweden_political_party ORDER BY party_name) TO 'distinct_political_parties.csv' WITH CSV HEADER;

\echo ''
\echo 'Distinct value extraction summary:'
\echo '  - distinct_party_values.csv: Party distribution'
\echo '  - distinct_org_code_values.csv: Organization codes'
\echo '  - distinct_role_code_values.csv: Role codes'
\echo '  - distinct_assignment_status_values.csv: Assignment statuses'
\echo '  - distinct_document_type_values.csv: Document types'
\echo '  - distinct_document_org_values.csv: Document organizations'
\echo '  - distinct_vote_values.csv: Vote patterns by party'
\echo '  - distinct_political_parties.csv: Complete party list'
\echo ''

-- ===========================================================================
-- SECTION 6: Generate Extraction Statistics
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== EXTRACTION STATISTICS              ==='
\echo '=========================================='
\echo ''

-- Generate comprehensive extraction statistics
-- Note: This query is intentionally on one line to work with \copy command
-- It cannot be split into multiple lines or use temporary tables/views because
-- \copy requires a complete SELECT statement in a single command
-- The query generates a summary table with coverage metrics for:
--   - TABLES: counts and filters out qrtz_* and databasechange* tables
--   - REGULAR_VIEWS: counts all regular views
--   - MATERIALIZED_VIEWS: counts all materialized views
--   - TOTAL: aggregates all object types with overall coverage percentage
-- Column order: category, total_in_schema, extracted_count, excluded_count, coverage_pct
\copy (SELECT 'TABLES' as category, COUNT(*) as total_in_schema, (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%') as extracted_count, (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND (tablename LIKE 'qrtz_%' OR tablename LIKE 'databasechange%')) as excluded_count, ROUND((SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%')::numeric / NULLIF(COUNT(*), 0) * 100, 2) as coverage_pct FROM pg_tables WHERE schemaname = 'public' UNION ALL SELECT 'REGULAR_VIEWS', COUNT(*), COUNT(*), 0, 100.00 FROM pg_views WHERE schemaname = 'public' UNION ALL SELECT 'MATERIALIZED_VIEWS', COUNT(*), COUNT(*), 0, 100.00 FROM pg_matviews WHERE schemaname = 'public' UNION ALL SELECT 'TOTAL', (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public'), (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%') + (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public'), (SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND (tablename LIKE 'qrtz_%' OR tablename LIKE 'databasechange%')), ROUND(((SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public' AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%') + (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public'))::numeric / NULLIF((SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public'), 0) * 100, 2)) TO 'extraction_statistics.csv' WITH CSV HEADER;

\echo 'Extraction statistics saved to: extraction_statistics.csv'
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
BEGIN
    SELECT COUNT(*) INTO total_tables FROM pg_tables WHERE schemaname = 'public';
    SELECT COUNT(*) INTO extracted_tables FROM pg_tables WHERE schemaname = 'public' 
        AND tablename NOT LIKE 'qrtz_%' AND tablename NOT LIKE 'databasechange%';
    SELECT COUNT(*) INTO excluded_tables FROM pg_tables WHERE schemaname = 'public' 
        AND (tablename LIKE 'qrtz_%' OR tablename LIKE 'databasechange%');
    SELECT COUNT(*) INTO regular_views FROM pg_views WHERE schemaname = 'public';
    SELECT COUNT(*) INTO mat_views FROM pg_matviews WHERE schemaname = 'public';
    
    total_objects := total_tables + regular_views + mat_views;
    total_extracted := extracted_tables + regular_views + mat_views;
    coverage_pct := ROUND((total_extracted::numeric / NULLIF(total_objects, 0) * 100), 2);
    
    RAISE NOTICE 'Coverage Summary:';
    RAISE NOTICE '  Total Tables: % (% extracted, % excluded)', total_tables, extracted_tables, excluded_tables;
    RAISE NOTICE '  Regular Views: % (all extracted)', regular_views;
    RAISE NOTICE '  Materialized Views: % (all extracted)', mat_views;
    RAISE NOTICE '  Total Objects: % (% extracted = %)', total_objects, total_extracted, format('%s%%', coverage_pct);
    RAISE NOTICE '';
    RAISE NOTICE 'Expected CSV Files:';
    RAISE NOTICE '  Table CSVs: %', extracted_tables;
    RAISE NOTICE '  View CSVs: %', regular_views + mat_views;
    RAISE NOTICE '  Distinct value CSVs: 8';
    RAISE NOTICE '  Metadata CSVs: 3 (manifest + column mapping + statistics)';
    RAISE NOTICE '  Total Expected: %', extracted_tables + regular_views + mat_views + 11;
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
\echo '  - sample_data_manifest.csv: Metadata about extracted files'
\echo '  - view_column_mapping.csv: Column mappings for views'
\echo '  - extraction_statistics.csv: Coverage statistics'
\echo '  - distinct_*_values.csv: Distinct values with counts for important columns'
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
