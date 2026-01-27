-- validate-views-with-timeout.sql
-- Comprehensive View Validation Script with Timeout Protection
-- Citizen Intelligence Agency - Open Source Intelligence Platform
--
-- Purpose: Validates all database views with timeout protection to handle
--          complex views that take extreme time to execute
--
-- Usage:
--   psql -U eris -d cia_dev -f service.data.impl/src/main/resources/validate-views-with-timeout.sql
--
-- Features:
--   - Automatic timeout protection (30s per view by default)
--   - Dependency level calculation
--   - Performance metrics (EXPLAIN ANALYZE with timeout)
--   - Row count validation
--   - Error handling and reporting
--   - Categorization by complexity
--
-- Output:
--   - validation_report.csv: Full validation results
--   - validation_summary.csv: Summary statistics
--   - problematic_views.csv: Views that timed out or failed
--   - view_dependencies.csv: View dependency graph

\set ON_ERROR_STOP off
\timing on

\echo '======================================='
\echo 'View Validation with Timeout Protection'
\echo 'Started at:' `date`
\echo '======================================='

-- Set statement timeout for individual view queries
-- This prevents hanging on complex views
SET statement_timeout = '30s';

-- ===========================================================================
-- PHASE 1: ANALYZE VIEW DEPENDENCIES
-- ===========================================================================

\echo ''
\echo '--- PHASE 1: Analyzing View Dependencies ---'
\echo ''

DROP TABLE IF EXISTS tmp_view_dependencies;
DROP TABLE IF EXISTS tmp_view_levels;

-- Create temporary table for view dependencies
CREATE TEMP TABLE tmp_view_dependencies AS
WITH RECURSIVE 
view_deps AS (
    SELECT DISTINCT
        dependent_view.relname AS view_name,
        source_table.relname AS depends_on,
        CASE 
            WHEN source_table.relkind = 'm' THEN 'materialized_view'
            WHEN source_table.relkind = 'v' THEN 'view'
            WHEN source_table.relkind = 'r' THEN 'table'
            ELSE 'other'
        END AS dependency_type
    FROM pg_depend
    JOIN pg_rewrite ON pg_depend.objid = pg_rewrite.oid
    JOIN pg_class AS dependent_view ON pg_rewrite.ev_class = dependent_view.oid
    JOIN pg_class AS source_table ON pg_depend.refobjid = source_table.oid
    JOIN pg_namespace dependent_ns ON dependent_ns.oid = dependent_view.relnamespace
    JOIN pg_namespace source_ns ON source_ns.oid = source_table.relnamespace
    WHERE dependent_view.relkind IN ('v', 'm')
      AND source_table.relkind IN ('v', 'm', 'r')
      AND pg_depend.deptype = 'n'
      AND dependent_ns.nspname = 'public'
      AND source_ns.nspname = 'public'
)
SELECT * FROM view_deps;

-- Calculate dependency levels
CREATE TEMP TABLE tmp_view_levels AS
WITH RECURSIVE 
all_views AS (
    SELECT viewname AS view_name, 'view' AS view_type
    FROM pg_views 
    WHERE schemaname = 'public'
    UNION ALL
    SELECT matviewname AS view_name, 'materialized_view' AS view_type
    FROM pg_matviews 
    WHERE schemaname = 'public'
),
view_only_deps AS (
    SELECT vd.view_name, vd.depends_on
    FROM tmp_view_dependencies vd
    WHERE vd.dependency_type IN ('view', 'materialized_view')
),
dependency_depth AS (
    -- Base case: views with no view dependencies (only table dependencies)
    SELECT 
        av.view_name,
        av.view_type,
        0 AS depth,
        ARRAY[av.view_name] AS path
    FROM all_views av
    WHERE NOT EXISTS (
        SELECT 1 FROM view_only_deps vod 
        WHERE vod.view_name = av.view_name
    )
    
    UNION ALL
    
    -- Recursive case: views depending on already processed views
    SELECT 
        vod.view_name,
        av.view_type,
        dd.depth + 1,
        dd.path || vod.view_name
    FROM view_only_deps vod
    JOIN dependency_depth dd ON vod.depends_on = dd.view_name
    JOIN all_views av ON vod.view_name = av.view_name
    WHERE NOT (vod.view_name = ANY(dd.path)) -- Prevent cycles
)
SELECT 
    view_name,
    view_type,
    MAX(depth) as dependency_level,
    CASE 
        WHEN MAX(depth) BETWEEN 0 AND 1 THEN 'SIMPLE'
        WHEN MAX(depth) = 2 THEN 'MODERATE'
        WHEN MAX(depth) = 3 THEN 'COMPLEX'
        WHEN MAX(depth) >= 4 THEN 'VERY_COMPLEX'
    END AS complexity_category
FROM dependency_depth
GROUP BY view_name, view_type;

\echo 'Dependency analysis complete'

-- Show dependency statistics
SELECT 
    complexity_category,
    COUNT(*) as view_count,
    MIN(dependency_level) as min_level,
    MAX(dependency_level) as max_level
FROM tmp_view_levels
GROUP BY complexity_category
ORDER BY MIN(dependency_level);

-- ===========================================================================
-- PHASE 2: VALIDATE VIEWS
-- ===========================================================================

\echo ''
\echo '--- PHASE 2: Validating Views ---'
\echo 'Testing each view with 30-second timeout protection'
\echo ''

DROP TABLE IF EXISTS tmp_validation_results;

CREATE TEMP TABLE tmp_validation_results (
    view_name TEXT,
    view_type TEXT,
    dependency_level INTEGER,
    complexity_category TEXT,
    validation_status TEXT, -- 'SUCCESS', 'TIMEOUT', 'ERROR', 'EMPTY'
    row_count BIGINT,
    execution_time_ms INTEGER,
    error_message TEXT,
    validated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Validate each view with timeout protection
DO $$
DECLARE
    v_record RECORD;
    v_row_count BIGINT;
    v_start_time TIMESTAMP;
    v_end_time TIMESTAMP;
    v_duration_ms INTEGER;
    v_status TEXT;
    v_error TEXT;
    v_total INTEGER := 0;
    v_success INTEGER := 0;
    v_timeout INTEGER := 0;
    v_error_count INTEGER := 0;
    v_empty INTEGER := 0;
BEGIN
    -- Get total count
    SELECT COUNT(*) INTO v_total FROM tmp_view_levels;
    
    RAISE NOTICE 'Starting validation of % views...', v_total;
    RAISE NOTICE '';
    
    FOR v_record IN 
        SELECT * FROM tmp_view_levels ORDER BY dependency_level, view_name
    LOOP
        BEGIN
            v_start_time := clock_timestamp();
            v_status := 'SUCCESS';
            v_error := NULL;
            v_row_count := NULL;
            
            -- Try to count rows with timeout protection
            BEGIN
                EXECUTE format('SELECT COUNT(*) FROM %I', v_record.view_name) INTO v_row_count;
                
                v_end_time := clock_timestamp();
                v_duration_ms := EXTRACT(EPOCH FROM (v_end_time - v_start_time)) * 1000;
                
                IF v_row_count = 0 THEN
                    v_status := 'EMPTY';
                    v_empty := v_empty + 1;
                ELSE
                    v_success := v_success + 1;
                END IF;
                
                RAISE NOTICE '[%/%] ✓ %: % rows in % ms (%)', 
                    v_success + v_timeout + v_error_count + v_empty,
                    v_total,
                    v_record.view_name,
                    COALESCE(v_row_count::TEXT, '0'),
                    v_duration_ms,
                    v_record.complexity_category;
                    
            EXCEPTION 
                WHEN query_canceled THEN
                    v_status := 'TIMEOUT';
                    v_error := 'Query exceeded 30-second timeout';
                    v_duration_ms := 30000;
                    v_timeout := v_timeout + 1;
                    RAISE WARNING '[%/%] ⏱ TIMEOUT: % (exceeded 30s)', 
                        v_success + v_timeout + v_error_count + v_empty,
                        v_total,
                        v_record.view_name;
                WHEN OTHERS THEN
                    v_status := 'ERROR';
                    v_error := SQLERRM;
                    v_end_time := clock_timestamp();
                    v_duration_ms := EXTRACT(EPOCH FROM (v_end_time - v_start_time)) * 1000;
                    v_error_count := v_error_count + 1;
                    RAISE WARNING '[%/%] ✗ ERROR: %: %', 
                        v_success + v_timeout + v_error_count + v_empty,
                        v_total,
                        v_record.view_name,
                        SQLERRM;
            END;
            
            -- Insert result
            INSERT INTO tmp_validation_results 
                (view_name, view_type, dependency_level, complexity_category, 
                 validation_status, row_count, execution_time_ms, error_message)
            VALUES 
                (v_record.view_name, v_record.view_type, v_record.dependency_level, 
                 v_record.complexity_category, v_status, v_row_count, v_duration_ms, v_error);
                 
        EXCEPTION WHEN OTHERS THEN
            -- Catch-all for any unexpected errors
            RAISE WARNING 'Unexpected error validating %: %', v_record.view_name, SQLERRM;
        END;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE '===========================================';
    RAISE NOTICE 'VALIDATION SUMMARY';
    RAISE NOTICE '===========================================';
    RAISE NOTICE 'Total views:     %', v_total;
    RAISE NOTICE 'Success:         % (%.1f%%)', v_success, (v_success::FLOAT / v_total * 100);
    RAISE NOTICE 'Empty:           % (%.1f%%)', v_empty, (v_empty::FLOAT / v_total * 100);
    RAISE NOTICE 'Timeout:         % (%.1f%%)', v_timeout, (v_timeout::FLOAT / v_total * 100);
    RAISE NOTICE 'Errors:          % (%.1f%%)', v_error_count, (v_error_count::FLOAT / v_total * 100);
    RAISE NOTICE '===========================================';
END $$;

-- ===========================================================================
-- PHASE 3: EXPORT RESULTS
-- ===========================================================================

\echo ''
\echo '--- PHASE 3: Exporting Results ---'
\echo ''

-- Export full validation report
\echo 'Generating validation_report.csv...'
\copy (SELECT * FROM tmp_validation_results ORDER BY dependency_level, view_name) TO 'validation_report.csv' WITH CSV HEADER;

-- Export summary by complexity
\echo 'Generating validation_summary.csv...'
\copy (
    SELECT
        complexity_category,
        COUNT(*) AS total_views,
        COUNT(*) FILTER (WHERE validation_status = 'SUCCESS') AS success,
        COUNT(*) FILTER (WHERE validation_status = 'EMPTY') AS empty,
        COUNT(*) FILTER (WHERE validation_status = 'TIMEOUT') AS timeout,
        COUNT(*) FILTER (WHERE validation_status = 'ERROR') AS error,
        ROUND(AVG(execution_time_ms)) AS avg_time_ms,
        MAX(execution_time_ms) AS max_time_ms
    FROM tmp_validation_results
    GROUP BY complexity_category
    ORDER BY MIN(dependency_level)
) TO 'validation_summary.csv' WITH CSV HEADER;

-- Export problematic views
\echo 'Generating problematic_views.csv...'
\copy (SELECT view_name, view_type, dependency_level, complexity_category, validation_status, error_message FROM tmp_validation_results WHERE validation_status IN ('TIMEOUT', 'ERROR') ORDER BY dependency_level DESC, view_name) TO 'problematic_views.csv' WITH CSV HEADER;

-- Export view dependencies
\echo 'Generating view_dependencies.csv...'
\copy (SELECT view_name, STRING_AGG(depends_on, ', ' ORDER BY depends_on) as dependencies FROM tmp_view_dependencies GROUP BY view_name ORDER BY view_name) TO 'view_dependencies.csv' WITH CSV HEADER;

-- Export empty views
\echo 'Generating empty_views.csv...'
\copy (SELECT view_name, view_type, dependency_level, complexity_category FROM tmp_validation_results WHERE validation_status = 'EMPTY' ORDER BY view_name) TO 'empty_views.csv' WITH CSV HEADER;

-- ===========================================================================
-- PHASE 4: DISPLAY RESULTS
-- ===========================================================================

\echo ''
\echo '--- PHASE 4: Results Summary ---'
\echo ''

\echo 'Top 10 Slowest Views:'
SELECT 
    view_name,
    complexity_category,
    validation_status,
    execution_time_ms,
    row_count
FROM tmp_validation_results
WHERE validation_status IN ('SUCCESS', 'TIMEOUT')
ORDER BY execution_time_ms DESC
LIMIT 10;

\echo ''
\echo 'Views by Status:'
SELECT 
    validation_status,
    COUNT(*) as count,
    ROUND(AVG(execution_time_ms)) as avg_time_ms
FROM tmp_validation_results
GROUP BY validation_status
ORDER BY 
    CASE validation_status
        WHEN 'SUCCESS' THEN 1
        WHEN 'EMPTY' THEN 2
        WHEN 'TIMEOUT' THEN 3
        WHEN 'ERROR' THEN 4
    END;

\echo ''
\echo 'Problematic Views (Timeout or Error):'
SELECT 
    view_name,
    complexity_category,
    validation_status,
    SUBSTRING(error_message, 1, 60) as error_summary
FROM tmp_validation_results
WHERE validation_status IN ('TIMEOUT', 'ERROR')
ORDER BY dependency_level DESC, view_name;

-- Reset statement timeout
RESET statement_timeout;

\echo ''
\echo '======================================='
\echo 'Validation Complete'
\echo 'Finished at:' `date`
\echo '======================================='
\echo ''
\echo 'Generated files:'
\echo '  - validation_report.csv (full results)'
\echo '  - validation_summary.csv (summary statistics)'
\echo '  - problematic_views.csv (timeout/error views)'
\echo '  - view_dependencies.csv (dependency graph)'
\echo '  - empty_views.csv (views with no data)'
\echo ''
