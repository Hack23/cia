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
    -- Get total view count (excluding coalition alignment matrix)
    SELECT COUNT(*) INTO total_views
    FROM (
        SELECT viewname FROM pg_views 
        WHERE schemaname = 'public' 
          AND viewname != 'view_riksdagen_coalition_alignment_matrix'
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
-- SECTION 4: Extract Distinct Values for View-Referenced Columns ONLY
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== EXTRACTING DISTINCT VALUE SETS            ==='
\echo '=================================================='
\echo ''
\echo 'Analyzing columns ACTUALLY USED in view queries...'
\echo 'Extracting WHERE/JOIN/GROUP BY predicate columns only...'
\echo ''

\! rm -f :DISTINCT_CMD_FILE
\pset format unaligned
\pset tuples_only on
\o :DISTINCT_CMD_FILE

-- Extract columns that are ACTUALLY used in view predicates (WHERE/JOIN/GROUP BY)
WITH RECURSIVE view_definitions AS (
    -- Get all view definitions with their SQL
    SELECT 
        n.nspname AS schemaname,
        c.relname AS viewname,
        pg_get_viewdef(c.oid, true) AS view_definition
    FROM pg_class c
    JOIN pg_namespace n ON n.oid = c.relnamespace
    WHERE c.relkind IN ('v', 'm')
      AND n.nspname = 'public'
      AND c.relname != 'view_riksdagen_coalition_alignment_matrix'
),
-- Enhanced extraction: Multiple patterns to catch different SQL syntaxes
view_column_patterns AS (
    SELECT DISTINCT
        schemaname,
        viewname,
        regexp_matches(view_definition, '\b([a-z_][a-z0-9_]*)\.\s*([a-z_][a-z0-9_]*)\s*(?:=|!=|<>|<|>|<=|>=|IN\s*\(|IS\s+(?:NOT\s+)?NULL|(?:NOT\s+)?LIKE)', 'gi') AS table_column
    FROM view_definitions
),
-- Parse all matches
parsed_columns AS (
    SELECT DISTINCT
        schemaname,
        viewname,
        lower((table_column)[1]) AS table_name,
        lower((table_column)[2]) AS column_name
    FROM view_column_patterns
    WHERE table_column IS NOT NULL 
      AND array_length(table_column, 1) >= 2
      -- Filter out SQL keywords
      AND lower((table_column)[1]) NOT IN (
          'select', 'from', 'where', 'group', 'order', 'having', 
          'case', 'when', 'then', 'else', 'end', 'and', 'or', 
          'not', 'null', 'as', 'on', 'using', 'join', 'left',
          'right', 'inner', 'outer', 'cross', 'natural', 'with',
          'union', 'intersect', 'except', 'limit', 'offset'
      )
      AND lower((table_column)[2]) NOT IN (
          'select', 'from', 'where', 'group', 'order', 'having',
          'case', 'when', 'then', 'else', 'end', 'and', 'or',
          'not', 'null', 'as', 'on', 'using', 'distinct', 'all',
          'exists', 'between', 'like', 'ilike', 'similar', 'in'
      )
),
-- ALSO add common categorical columns from base tables
common_categorical_columns AS (
    SELECT DISTINCT
        t.table_schema AS schemaname,
        t.table_name,
        c.column_name,
        'COMMON_CATEGORICAL' AS source_type
    FROM information_schema.tables t
    JOIN information_schema.columns c 
        ON c.table_schema = t.table_schema 
        AND c.table_name = t.table_name
    WHERE t.table_schema = 'public'
      AND t.table_type = 'BASE TABLE'
      AND c.data_type IN ('character varying', 'text', 'character', 'USER-DEFINED')
      AND (c.character_maximum_length IS NULL OR c.character_maximum_length < 200)
      AND c.column_name IN (
          'party', 'status', 'gender', 'role_code', 'ballot_type',
          'vote', 'document_type', 'detail', 'org', 'org_code',
          'concern', 'issue', 'decision_type', 'winner', 'committee_report',
          'election_region', 'assignment_type', 'detail_type',
          'rm', 'label', 'sub_type', 'category', 'classification'
      )
),
-- Combine parsed columns with common categorical columns
all_candidate_columns AS (
    SELECT DISTINCT
        schemaname,
        table_name,
        column_name,
        'VIEW_PREDICATE' AS source_type
    FROM parsed_columns
    UNION
    SELECT 
        schemaname,
        table_name,
        column_name,
        source_type
    FROM common_categorical_columns
),
-- Verify columns exist in actual tables
verified_columns AS (
    SELECT DISTINCT
        acc.schemaname,
        acc.table_name AS tablename,
        acc.column_name,
        c.data_type,
        c.character_maximum_length,
        COUNT(DISTINCT CASE WHEN acc.source_type = 'VIEW_PREDICATE' THEN acc.table_name END) AS used_in_view_count,
        MAX(CASE WHEN acc.source_type = 'COMMON_CATEGORICAL' THEN 1 ELSE 0 END) AS is_common_categorical
    FROM all_candidate_columns acc
    JOIN information_schema.columns c 
        ON c.table_schema = acc.schemaname 
        AND c.table_name = acc.table_name
        AND c.column_name = acc.column_name
    WHERE c.data_type IN ('character varying', 'text', 'character', 'USER-DEFINED')
      AND (c.character_maximum_length IS NULL OR c.character_maximum_length < 500)
    GROUP BY acc.schemaname, acc.table_name, acc.column_name, c.data_type, c.character_maximum_length
),
-- Check which tables have data
tables_with_data AS (
    SELECT DISTINCT
        vc.schemaname,
        vc.tablename
    FROM verified_columns vc
    WHERE cia_tmp_rowcount(vc.schemaname, vc.tablename) > 0
),
-- Generate extraction commands with priority
distinct_extracts AS (
    SELECT 
        vc.schemaname,
        vc.tablename,
        vc.column_name,
        vc.used_in_view_count,
        vc.is_common_categorical,
        CASE 
            WHEN vc.used_in_view_count > 0 THEN vc.used_in_view_count * 100
            WHEN vc.is_common_categorical = 1 THEN 50
            ELSE 1
        END AS priority_score,
        'distinct_' || vc.tablename || '_' || vc.column_name || '_values.csv' AS filename
    FROM verified_columns vc
    JOIN tables_with_data twd 
        ON twd.schemaname = vc.schemaname 
        AND twd.tablename = vc.tablename
    
    UNION ALL
    
    -- Add critical assignment_data columns (used in committee/government views)
    SELECT 
        'public' AS schemaname,
        'assignment_data' AS tablename,
        col AS column_name,
        15 AS used_in_view_count,
        1 AS is_common_categorical,
        1500 AS priority_score,
        'distinct_assignment_data_' || col || '_values.csv' AS filename
    FROM unnest(ARRAY['org_code', 'detail', 'role_code', 'assignment_type', 'status']) AS col
    WHERE EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'assignment_data')
    
    ORDER BY priority_score DESC, tablename, column_name
)
SELECT format(
    '\echo ''[DISTINCT] %I.%I.%I (priority=%s, views=%s) -> %s''' || E'\n' ||
    '\copy (SELECT %I as value, COUNT(*) as count FROM %I.%I WHERE %I IS NOT NULL GROUP BY %I ORDER BY count DESC, %I LIMIT 1000) TO ''%s'' WITH CSV HEADER' || E'\n' ||
    '\echo ''  ✓ Completed: %s''' || E'\n',
    schemaname,
    tablename,
    column_name,
    priority_score,
    used_in_view_count,
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
FROM distinct_extracts;

\o
\pset format aligned
\pset tuples_only off

\echo 'Executing enhanced distinct value extractions...'
\echo ''

\i :DISTINCT_CMD_FILE
\! rm -f :DISTINCT_CMD_FILE

\echo ''
\echo 'Distinct value extraction completed'
\echo ''

-- ===========================================================================
-- SECTION 6: Generate Summary Statistics
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== EXTRACTION STATISTICS                     ==='
\echo '=================================================='
\echo ''
\echo 'Generating extraction_statistics.csv...'

\copy (SELECT 'TABLES' as category, COUNT(*) as total_in_schema, COUNT(*) FILTER (WHERE cia_tmp_rowcount(t.table_schema, t.table_name) > 0) as extracted_count, COUNT(*) FILTER (WHERE cia_tmp_rowcount(t.table_schema, t.table_name) = 0) as excluded_count, ROUND(100.0 * COUNT(*) FILTER (WHERE cia_tmp_rowcount(t.table_schema, t.table_name) > 0) / NULLIF(COUNT(*), 0), 2) as coverage_pct FROM information_schema.tables t WHERE t.table_schema = 'public' AND t.table_type = 'BASE TABLE' UNION ALL SELECT 'REGULAR_VIEWS' as category, COUNT(*) as total_in_schema, COUNT(*) as extracted_count, 0 as excluded_count, 100.00 as coverage_pct FROM information_schema.views WHERE table_schema = 'public' AND table_name != 'view_riksdagen_coalition_alignment_matrix' UNION ALL SELECT 'MATERIALIZED_VIEWS' as category, COUNT(*) as total_in_schema, COUNT(*) as extracted_count, 0 as excluded_count, 100.00 as coverage_pct FROM pg_matviews WHERE schemaname = 'public' UNION ALL SELECT 'TOTAL' as category, (SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public' AND table_type = 'BASE TABLE') + (SELECT COUNT(*) FROM information_schema.views WHERE table_schema = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public') as total_in_schema, (SELECT COUNT(*) FROM information_schema.tables t WHERE t.table_schema = 'public' AND t.table_type = 'BASE TABLE' AND cia_tmp_rowcount(t.table_schema, t.table_name) > 0) + (SELECT COUNT(*) FROM information_schema.views WHERE table_schema = 'public' AND table_name != 'view_riksdagen_coalition_alignment_matrix') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public') as extracted_count, (SELECT COUNT(*) FROM information_schema.tables t WHERE t.table_schema = 'public' AND t.table_type = 'BASE TABLE' AND cia_tmp_rowcount(t.table_schema, t.table_name) = 0) + 1 as excluded_count, ROUND(100.0 * ((SELECT COUNT(*) FROM information_schema.tables t WHERE t.table_schema = 'public' AND t.table_type = 'BASE TABLE' AND cia_tmp_rowcount(t.table_schema, t.table_name) > 0) + (SELECT COUNT(*) FROM information_schema.views WHERE table_schema = 'public' AND table_name != 'view_riksdagen_coalition_alignment_matrix') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public')) / NULLIF((SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public' AND table_type = 'BASE TABLE') + (SELECT COUNT(*) FROM information_schema.views WHERE table_schema = 'public') + (SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public'), 0), 2) as coverage_pct) TO 'extraction_statistics.csv' WITH CSV HEADER;

\echo '  ✓ Completed: extraction_statistics.csv'
\echo ''

-- ===========================================================================
-- SECTION 7: Summary and Cleanup
-- ===========================================================================
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
\echo '  2. Use copy FROM to import: '
\echo '     copy table_name FROM ''table_name_sample.csv'' WITH CSV HEADER;'
\echo ''
\echo 'For troubleshooting empty views, see:'
\echo '  - TROUBLESHOOTING_EMPTY_VIEWS.md'
\echo '  - view_column_mapping.csv for required columns'
\echo ''

-- Cleanup helper function
DROP FUNCTION IF EXISTS cia_tmp_rowcount(text, text);
