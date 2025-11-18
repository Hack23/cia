-- schema-validation.sql
-- Database Schema Validation and Statistics Report
-- Citizen Intelligence Agency - Open Source Intelligence Platform
-- Generated for PostgreSQL 16
--
-- Purpose: Validates database schema by counting objects, extracting samples,
--          and generating statistics reports for documentation validation
--
-- Usage:
--   psql -U postgres -d cia_dev -f schema-validation.sql > schema_report.txt 2>&1
--
-- Output formats available:
--   1. Text report (stdout)
--   2. JSON report (see JSON output section)
--   3. CSV inventory (see CSV output section)

\set ECHO queries
\timing on

-- ===========================================================================
-- PART 1: DATABASE OBJECT COUNTS
-- ===========================================================================

\echo ''
\echo '=========================================='
\echo '=== DATABASE OBJECT COUNTS             ==='
\echo '=========================================='
\echo ''

-- Count base tables
SELECT 'Base Tables' AS object_type, COUNT(*) AS count
FROM information_schema.tables
WHERE table_schema = 'public' AND table_type = 'BASE TABLE';

-- Count regular views
SELECT 'Regular Views' AS object_type, COUNT(*) AS count
FROM information_schema.views
WHERE table_schema = 'public';

-- Count materialized views
SELECT 'Materialized Views' AS object_type, COUNT(*) AS count
FROM pg_matviews
WHERE schemaname = 'public';

-- Count indexes
SELECT 'Indexes' AS object_type, COUNT(*) AS count
FROM pg_indexes
WHERE schemaname = 'public';

-- Count sequences
SELECT 'Sequences' AS object_type, COUNT(*) AS count
FROM information_schema.sequences
WHERE sequence_schema = 'public';

-- Count functions
SELECT 'Functions' AS object_type, COUNT(*) AS count
FROM pg_proc p
JOIN pg_namespace n ON p.pronamespace = n.oid
WHERE n.nspname = 'public';

-- Summary
\echo ''
\echo 'Summary of database objects:'
SELECT 
    (SELECT COUNT(*) FROM information_schema.tables 
     WHERE table_schema = 'public' AND table_type = 'BASE TABLE') AS base_tables,
    (SELECT COUNT(*) FROM information_schema.views 
     WHERE table_schema = 'public') AS regular_views,
    (SELECT COUNT(*) FROM pg_matviews 
     WHERE schemaname = 'public') AS materialized_views,
    (SELECT COUNT(*) FROM pg_indexes 
     WHERE schemaname = 'public') AS indexes,
    (SELECT COUNT(*) FROM information_schema.sequences 
     WHERE sequence_schema = 'public') AS sequences,
    (SELECT COUNT(*) FROM pg_proc p JOIN pg_namespace n ON p.pronamespace = n.oid 
     WHERE n.nspname = 'public') AS functions;

-- ===========================================================================
-- PART 2: TABLE INVENTORY WITH ROW COUNTS AND METADATA
-- ===========================================================================

\echo ''
\echo '=========================================='
\echo '=== TABLE INVENTORY                    ==='
\echo '=========================================='
\echo ''

DO $$
DECLARE
    r RECORD;
    row_count BIGINT;
    col_count INTEGER;
    table_size TEXT;
BEGIN
    RAISE NOTICE 'TABLE INVENTORY WITH ROW COUNTS, COLUMNS, AND SIZE';
    RAISE NOTICE '-----------------------------------------------------';
    RAISE NOTICE 'Format: Schema.Table | Rows | Columns | Size';
    RAISE NOTICE '';
    
    FOR r IN 
        SELECT schemaname, tablename 
        FROM pg_tables 
        WHERE schemaname = 'public'
        ORDER BY tablename
    LOOP
        BEGIN
            -- Get row count
            EXECUTE format('SELECT COUNT(*) FROM %I.%I', r.schemaname, r.tablename) INTO row_count;
            
            -- Get column count
            SELECT COUNT(*) INTO col_count
            FROM information_schema.columns
            WHERE table_schema = r.schemaname AND table_name = r.tablename;
            
            -- Get table size
            EXECUTE format('SELECT pg_size_pretty(pg_total_relation_size(%L))', 
                          r.schemaname || '.' || r.tablename) INTO table_size;
            
            RAISE NOTICE 'Table: %.% | Rows: % | Columns: % | Size: %', 
                         r.schemaname, r.tablename, row_count, col_count, table_size;
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'Table: %.% | ERROR: %', r.schemaname, r.tablename, SQLERRM;
        END;
    END LOOP;
END $$;

-- ===========================================================================
-- PART 3: VIEW INVENTORY WITH ROW COUNTS AND METADATA
-- ===========================================================================

\echo ''
\echo '=========================================='
\echo '=== VIEW INVENTORY                     ==='
\echo '=========================================='
\echo ''

DO $$
DECLARE
    r RECORD;
    row_count BIGINT;
    col_count INTEGER;
BEGIN
    RAISE NOTICE 'REGULAR VIEWS INVENTORY';
    RAISE NOTICE '-----------------------';
    RAISE NOTICE 'Format: Schema.View | Rows | Columns';
    RAISE NOTICE '';
    
    -- Regular views
    FOR r IN 
        SELECT schemaname, viewname AS name
        FROM pg_views 
        WHERE schemaname = 'public'
        ORDER BY viewname
    LOOP
        BEGIN
            -- Get row count
            EXECUTE format('SELECT COUNT(*) FROM %I.%I', r.schemaname, r.name) INTO row_count;
            
            -- Get column count
            SELECT COUNT(*) INTO col_count
            FROM information_schema.columns
            WHERE table_schema = r.schemaname AND table_name = r.name;
            
            RAISE NOTICE 'View: %.% | Rows: % | Columns: %', 
                         r.schemaname, r.name, row_count, col_count;
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'View: %.% | ERROR: %', r.schemaname, r.name, SQLERRM;
        END;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE 'MATERIALIZED VIEWS INVENTORY';
    RAISE NOTICE '----------------------------';
    RAISE NOTICE 'Format: Schema.View | Rows | Columns | Size';
    RAISE NOTICE '';
    
    -- Materialized views
    FOR r IN 
        SELECT schemaname, matviewname AS name
        FROM pg_matviews 
        WHERE schemaname = 'public'
        ORDER BY matviewname
    LOOP
        DECLARE
            view_size TEXT;
        BEGIN
            -- Get row count
            EXECUTE format('SELECT COUNT(*) FROM %I.%I', r.schemaname, r.name) INTO row_count;
            
            -- Get column count
            SELECT COUNT(*) INTO col_count
            FROM information_schema.columns
            WHERE table_schema = r.schemaname AND table_name = r.name;
            
            -- Get materialized view size
            EXECUTE format('SELECT pg_size_pretty(pg_total_relation_size(%L))', 
                          r.schemaname || '.' || r.name) INTO view_size;
            
            RAISE NOTICE 'Materialized View: %.% | Rows: % | Columns: % | Size: %', 
                         r.schemaname, r.name, row_count, col_count, view_size;
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'Materialized View: %.% | ERROR: %', r.schemaname, r.name, SQLERRM;
        END;
    END LOOP;
END $$;

-- ===========================================================================
-- PART 4: SAMPLE DATA FROM KEY VIEWS
-- ===========================================================================

\echo ''
\echo '=========================================='
\echo '=== SAMPLE DATA FROM KEY VIEWS         ==='
\echo '=========================================='
\echo ''

-- Sample from view_riksdagen_politician (if exists)
\echo '--- Sample from view_riksdagen_politician (5 rows) ---'
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.views WHERE table_name = 'view_riksdagen_politician') THEN
        RAISE NOTICE 'Executing: SELECT * FROM view_riksdagen_politician LIMIT 5';
    ELSE
        RAISE NOTICE 'View view_riksdagen_politician does not exist';
    END IF;
END $$;

SELECT * FROM view_riksdagen_politician LIMIT 5;

\echo ''
\echo '--- Sample from view_riksdagen_party (5 rows) ---'
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.views WHERE table_name = 'view_riksdagen_party') THEN
        RAISE NOTICE 'Executing: SELECT * FROM view_riksdagen_party LIMIT 5';
    ELSE
        RAISE NOTICE 'View view_riksdagen_party does not exist';
    END IF;
END $$;

SELECT * FROM view_riksdagen_party LIMIT 5;

\echo ''
\echo '--- Sample from view_riksdagen_vote_data_ballot_politician_summary_daily (recent 5 rows) ---'
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM pg_matviews WHERE matviewname = 'view_riksdagen_vote_data_ballot_politician_summary_daily') THEN
        RAISE NOTICE 'Executing: SELECT * FROM view_riksdagen_vote_data_ballot_politician_summary_daily ORDER BY vote_date DESC LIMIT 5';
    ELSE
        RAISE NOTICE 'Materialized view view_riksdagen_vote_data_ballot_politician_summary_daily does not exist';
    END IF;
END $$;

-- Only select if table has vote_date column
DO $$
DECLARE
    has_column BOOLEAN;
BEGIN
    SELECT EXISTS (
        SELECT 1 FROM information_schema.columns 
        WHERE table_name = 'view_riksdagen_vote_data_ballot_politician_summary_daily' 
        AND column_name = 'vote_date'
    ) INTO has_column;
    
    IF has_column THEN
        EXECUTE 'SELECT * FROM view_riksdagen_vote_data_ballot_politician_summary_daily ORDER BY vote_date DESC LIMIT 5';
    ELSE
        EXECUTE 'SELECT * FROM view_riksdagen_vote_data_ballot_politician_summary_daily LIMIT 5';
    END IF;
EXCEPTION WHEN OTHERS THEN
    RAISE NOTICE 'Could not query view: %', SQLERRM;
END $$;

-- ===========================================================================
-- PART 5: SCHEMA STATISTICS
-- ===========================================================================

\echo ''
\echo '=========================================='
\echo '=== SCHEMA STATISTICS                  ==='
\echo '=========================================='
\echo ''

-- Largest tables by row count
\echo '--- Top 20 Largest Tables by Row Count ---'
SELECT 
    schemaname,
    tablename,
    n_live_tup AS row_count,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) AS total_size,
    pg_size_pretty(pg_relation_size(schemaname||'.'||tablename)) AS table_size,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename) - pg_relation_size(schemaname||'.'||tablename)) AS indexes_size
FROM pg_stat_user_tables
WHERE schemaname = 'public'
ORDER BY n_live_tup DESC
LIMIT 20;

\echo ''
\echo '--- Top 20 Largest Tables by Total Size ---'
SELECT 
    schemaname,
    tablename,
    n_live_tup AS row_count,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) AS total_size
FROM pg_stat_user_tables
WHERE schemaname = 'public'
ORDER BY pg_total_relation_size(schemaname||'.'||tablename) DESC
LIMIT 20;

\echo ''
\echo '--- Tables with No Data ---'
SELECT 
    t.tablename,
    COALESCE(s.n_live_tup, 0) AS row_count
FROM pg_tables t
LEFT JOIN pg_stat_user_tables s ON t.tablename = s.relname AND t.schemaname = s.schemaname
WHERE t.schemaname = 'public' 
  AND (s.n_live_tup = 0 OR s.n_live_tup IS NULL)
ORDER BY t.tablename;

\echo ''
\echo '--- Top 20 Views with Most Dependencies ---'
SELECT 
    dependent_view.relname AS view_name,
    dependent_view.relkind AS view_type,
    COUNT(DISTINCT source_table.relname) AS dependency_count
FROM pg_depend
JOIN pg_rewrite ON pg_depend.objid = pg_rewrite.oid
JOIN pg_class AS dependent_view ON pg_rewrite.ev_class = dependent_view.oid
JOIN pg_class AS source_table ON pg_depend.refobjid = source_table.oid
WHERE dependent_view.relkind IN ('v', 'm')
  AND dependent_view.relnamespace = (SELECT oid FROM pg_namespace WHERE nspname = 'public')
GROUP BY dependent_view.relname, dependent_view.relkind
ORDER BY dependency_count DESC
LIMIT 20;

\echo ''
\echo '--- Database Size Summary ---'
SELECT 
    pg_size_pretty(pg_database_size(current_database())) AS database_size,
    (SELECT COUNT(*) FROM pg_stat_user_tables WHERE schemaname = 'public') AS table_count,
    (SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public') AS view_count,
    (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public') AS materialized_view_count,
    (SELECT SUM(n_live_tup) FROM pg_stat_user_tables WHERE schemaname = 'public') AS total_rows;

-- ===========================================================================
-- PART 6: COLUMN STATISTICS
-- ===========================================================================

\echo ''
\echo '=========================================='
\echo '=== COLUMN STATISTICS                  ==='
\echo '=========================================='
\echo ''

\echo '--- Tables with Most Columns ---'
SELECT 
    table_name,
    COUNT(*) AS column_count
FROM information_schema.columns
WHERE table_schema = 'public'
GROUP BY table_name
ORDER BY column_count DESC
LIMIT 20;

\echo ''
\echo '--- Most Common Column Names ---'
SELECT 
    column_name,
    COUNT(*) AS usage_count
FROM information_schema.columns
WHERE table_schema = 'public'
GROUP BY column_name
ORDER BY usage_count DESC
LIMIT 20;

\echo ''
\echo '--- Most Common Data Types ---'
SELECT 
    data_type,
    COUNT(*) AS usage_count
FROM information_schema.columns
WHERE table_schema = 'public'
GROUP BY data_type
ORDER BY usage_count DESC;

-- ===========================================================================
-- PART 7: INDEX STATISTICS
-- ===========================================================================

\echo ''
\echo '=========================================='
\echo '=== INDEX STATISTICS                   ==='
\echo '=========================================='
\echo ''

\echo '--- Index Usage Statistics (Top 20 by Size) ---'
SELECT 
    schemaname,
    tablename,
    indexname,
    pg_size_pretty(pg_relation_size(indexrelid)) AS index_size,
    idx_scan AS number_of_scans,
    idx_tup_read AS tuples_read,
    idx_tup_fetch AS tuples_fetched
FROM pg_stat_user_indexes
WHERE schemaname = 'public'
ORDER BY pg_relation_size(indexrelid) DESC
LIMIT 20;

\echo ''
\echo '--- Unused Indexes (No Scans) ---'
SELECT 
    schemaname,
    tablename,
    indexname,
    pg_size_pretty(pg_relation_size(indexrelid)) AS index_size
FROM pg_stat_user_indexes
WHERE schemaname = 'public'
  AND idx_scan = 0
ORDER BY pg_relation_size(indexrelid) DESC;

-- ===========================================================================
-- PART 8: JSON OUTPUT (Optional)
-- ===========================================================================

\echo ''
\echo '=========================================='
\echo '=== JSON OUTPUT AVAILABLE              ==='
\echo '=========================================='
\echo ''
\echo 'To generate JSON report, run:'
\echo 'psql -U postgres -d cia_dev -t -A -F"," -c "SELECT json_build_object(...)" > schema_report.json'
\echo ''

-- Commented out: Uncomment and modify path as needed
/*
COPY (
  SELECT json_build_object(
    'generated_at', NOW(),
    'database', current_database(),
    'summary', json_build_object(
      'base_tables', (SELECT COUNT(*) FROM information_schema.tables 
                      WHERE table_schema = 'public' AND table_type = 'BASE TABLE'),
      'regular_views', (SELECT COUNT(*) FROM information_schema.views 
                       WHERE table_schema = 'public'),
      'materialized_views', (SELECT COUNT(*) FROM pg_matviews 
                            WHERE schemaname = 'public'),
      'indexes', (SELECT COUNT(*) FROM pg_indexes WHERE schemaname = 'public'),
      'total_rows', (SELECT SUM(n_live_tup) FROM pg_stat_user_tables WHERE schemaname = 'public')
    ),
    'tables', (
      SELECT json_agg(json_build_object(
        'name', tablename,
        'rows', n_live_tup,
        'size', pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename))
      ))
      FROM pg_stat_user_tables 
      WHERE schemaname = 'public'
      ORDER BY tablename
    ),
    'views', (
      SELECT json_agg(json_build_object(
        'name', viewname,
        'type', 'regular'
      ))
      FROM pg_views 
      WHERE schemaname = 'public'
      ORDER BY viewname
    ),
    'materialized_views', (
      SELECT json_agg(json_build_object(
        'name', matviewname,
        'type', 'materialized'
      ))
      FROM pg_matviews 
      WHERE schemaname = 'public'
      ORDER BY matviewname
    )
  )::text
) TO '/tmp/schema_report.json';
*/

-- ===========================================================================
-- PART 9: CSV OUTPUT (Optional)
-- ===========================================================================

\echo ''
\echo '=========================================='
\echo '=== CSV OUTPUT AVAILABLE               ==='
\echo '=========================================='
\echo ''
\echo 'To generate CSV inventory, run:'
\echo 'psql -U postgres -d cia_dev -c "COPY (...) TO STDOUT WITH CSV HEADER" > schema_inventory.csv'
\echo ''

-- Commented out: Uncomment and modify path as needed
/*
COPY (
  SELECT 
    'TABLE' AS object_type, 
    tablename AS object_name, 
    n_live_tup AS row_count,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) AS total_size,
    (SELECT COUNT(*) FROM information_schema.columns 
     WHERE table_schema = schemaname AND table_name = tablename) AS column_count
  FROM pg_stat_user_tables
  WHERE schemaname = 'public'
  
  UNION ALL
  
  SELECT 
    'VIEW' AS object_type, 
    viewname AS object_name, 
    NULL AS row_count,
    NULL AS total_size,
    (SELECT COUNT(*) FROM information_schema.columns 
     WHERE table_schema = schemaname AND table_name = viewname) AS column_count
  FROM pg_views
  WHERE schemaname = 'public'
  
  UNION ALL
  
  SELECT 
    'MATERIALIZED_VIEW' AS object_type, 
    matviewname AS object_name, 
    NULL AS row_count,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||matviewname)) AS total_size,
    (SELECT COUNT(*) FROM information_schema.columns 
     WHERE table_schema = schemaname AND table_name = matviewname) AS column_count
  FROM pg_matviews
  WHERE schemaname = 'public'
  
  ORDER BY object_type, object_name
) TO '/tmp/schema_inventory.csv' WITH (FORMAT CSV, HEADER);
*/

-- ===========================================================================
-- VALIDATION COMPLETE
-- ===========================================================================

\echo ''
\echo '=========================================='
\echo '=== VALIDATION COMPLETE                ==='
\echo '=========================================='
\echo ''
\echo 'Schema validation report generated successfully.'
\echo 'Review the output above for schema health and statistics.'
\echo ''

\timing off
