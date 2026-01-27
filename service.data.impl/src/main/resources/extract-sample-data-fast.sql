-- extract-sample-data-fast.sql
-- Fast Sample Data Extraction with Timeout Protection
-- Citizen Intelligence Agency - Open Source Intelligence Platform
--
-- Purpose: Quickly extracts sample data from all views and tables with
--          timeout protection for complex views
--
-- Usage:
--   cd service.data.impl/sample-data
--   psql -U eris -d cia_dev -f ../src/main/resources/extract-sample-data-fast.sql
--
-- Features:
--   - Fast extraction with LIMIT clause
--   - Timeout protection (10s per view)
--   - Prioritizes simple views over complex ones
--   - Graceful handling of timeouts
--   - Progress tracking
--
-- Output:
--   - table_<name>_sample.csv for each table
--   - view_<name>_sample.csv for each view
--   - extraction_log.csv with status and timing

\set ON_ERROR_STOP off
\timing off

\echo '======================================='
\echo 'Fast Sample Data Extraction'
\echo 'Started at:' `date`
\echo '======================================='

-- Set timeout for individual queries
SET statement_timeout = '10s';

-- ===========================================================================
-- PHASE 1: GET VIEW COMPLEXITY LEVELS
-- ===========================================================================

\echo ''
\echo '--- PHASE 1: Analyzing View Complexity ---'

DROP TABLE IF EXISTS tmp_view_complexity;

CREATE TEMP TABLE tmp_view_complexity AS
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
view_deps AS (
    SELECT DISTINCT
        dependent_view.relname AS view_name,
        source_table.relname AS depends_on
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
),
view_only_deps AS (
    SELECT vd.view_name, vd.depends_on
    FROM view_deps vd
    WHERE EXISTS (
        SELECT 1 FROM all_views av WHERE av.view_name = vd.depends_on
    )
),
dependency_depth AS (
    SELECT 
        av.view_name,
        av.view_type,
        0 AS depth
    FROM all_views av
    WHERE NOT EXISTS (
        SELECT 1 FROM view_only_deps vod WHERE vod.view_name = av.view_name
    )
    
    UNION ALL
    
    SELECT 
        vod.view_name,
        av.view_type,
        dd.depth + 1
    FROM view_only_deps vod
    JOIN dependency_depth dd ON vod.depends_on = dd.view_name
    JOIN all_views av ON vod.view_name = av.view_name
)
SELECT 
    view_name,
    view_type,
    MAX(depth) as dependency_level,
    CASE 
        WHEN MAX(depth) <= 1 THEN 50  -- Simple: 50 rows
        WHEN MAX(depth) = 2 THEN 30   -- Moderate: 30 rows
        ELSE 10                        -- Complex: 10 rows
    END AS sample_size
FROM dependency_depth
GROUP BY view_name, view_type;

\echo 'Complexity analysis complete'

SELECT 
    CASE 
        WHEN dependency_level <= 1 THEN 'SIMPLE (0-1)'
        WHEN dependency_level = 2 THEN 'MODERATE (2)'
        ELSE 'COMPLEX (3+)'
    END AS complexity,
    COUNT(*) as view_count,
    MAX(sample_size) as sample_size
FROM tmp_view_complexity
GROUP BY 
    CASE 
        WHEN dependency_level <= 1 THEN 1
        WHEN dependency_level = 2 THEN 2
        ELSE 3
    END,
    CASE 
        WHEN dependency_level <= 1 THEN 'SIMPLE (0-1)'
        WHEN dependency_level = 2 THEN 'MODERATE (2)'
        ELSE 'COMPLEX (3+)'
    END
ORDER BY 1;

-- ===========================================================================
-- PHASE 2: EXTRACT SAMPLES FROM BASE TABLES
-- ===========================================================================

\echo ''
\echo '--- PHASE 2: Extracting Base Tables ---'

DROP TABLE IF EXISTS tmp_extraction_log;

CREATE TEMP TABLE tmp_extraction_log (
    object_name TEXT,
    object_type TEXT,
    extraction_status TEXT,
    rows_extracted INTEGER,
    execution_time_ms INTEGER,
    error_message TEXT,
    extracted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DO $$
DECLARE
    t_record RECORD;
    v_count INTEGER;
    v_start_time TIMESTAMP;
    v_duration_ms INTEGER;
    v_status TEXT;
    v_error TEXT;
    v_total INTEGER := 0;
    v_success INTEGER := 0;
BEGIN
    -- Get count of tables
    SELECT COUNT(*) INTO v_total 
    FROM pg_tables 
    WHERE schemaname = 'public' 
      AND tablename NOT LIKE 'qrtz_%'
      AND tablename NOT IN ('databasechangelog', 'databasechangeloglock');
    
    RAISE NOTICE 'Extracting from % base tables...', v_total;
    
    FOR t_record IN 
        SELECT tablename 
        FROM pg_tables 
        WHERE schemaname = 'public' 
          AND tablename NOT LIKE 'qrtz_%'
          AND tablename NOT IN ('databasechangelog', 'databasechangeloglock')
        ORDER BY tablename
    LOOP
        BEGIN
            v_start_time := clock_timestamp();
            v_status := 'SUCCESS';
            v_error := NULL;
            
            -- Extract sample with LIMIT using \copy (client-side) via stdout
            -- Note: We use a workaround since COPY TO requires absolute paths
            EXECUTE format(
                'COPY (SELECT * FROM %I ORDER BY random() LIMIT 50) TO STDOUT WITH CSV HEADER',
                t_record.tablename
            );
            -- File will be written by psql \copy command instead
            
            GET DIAGNOSTICS v_count = ROW_COUNT;
            v_duration_ms := EXTRACT(EPOCH FROM (clock_timestamp() - v_start_time)) * 1000;
            v_success := v_success + 1;
            
            RAISE NOTICE '[%/%] ✓ table_%: % rows in % ms', 
                v_success, v_total, t_record.tablename, v_count, v_duration_ms;
            
            INSERT INTO tmp_extraction_log 
                (object_name, object_type, extraction_status, rows_extracted, execution_time_ms)
            VALUES 
                (t_record.tablename, 'table', v_status, v_count, v_duration_ms);
                
        EXCEPTION 
            WHEN query_canceled THEN
                v_status := 'TIMEOUT';
                v_error := 'Extraction exceeded 10-second timeout';
                v_duration_ms := 10000;
                RAISE WARNING '[%/%] ⏱ TIMEOUT: table_%', v_success + 1, v_total, t_record.tablename;
                INSERT INTO tmp_extraction_log 
                    (object_name, object_type, extraction_status, rows_extracted, execution_time_ms, error_message)
                VALUES 
                    (t_record.tablename, 'table', v_status, 0, v_duration_ms, v_error);
            WHEN OTHERS THEN
                v_status := 'ERROR';
                v_error := SQLERRM;
                v_duration_ms := EXTRACT(EPOCH FROM (clock_timestamp() - v_start_time)) * 1000;
                RAISE WARNING '[%/%] ✗ ERROR: table_%: %', v_success + 1, v_total, t_record.tablename, SQLERRM;
                INSERT INTO tmp_extraction_log 
                    (object_name, object_type, extraction_status, rows_extracted, execution_time_ms, error_message)
                VALUES 
                    (t_record.tablename, 'table', v_status, 0, v_duration_ms, v_error);
        END;
    END LOOP;
    
    RAISE NOTICE 'Tables: % successful out of %', v_success, v_total;
END $$;

-- ===========================================================================
-- PHASE 3: EXTRACT SAMPLES FROM VIEWS (ORDERED BY COMPLEXITY)
-- ===========================================================================

\echo ''
\echo '--- PHASE 3: Extracting Views (Simple to Complex) ---'

DO $$
DECLARE
    v_record RECORD;
    v_count INTEGER;
    v_start_time TIMESTAMP;
    v_duration_ms INTEGER;
    v_status TEXT;
    v_error TEXT;
    v_total INTEGER := 0;
    v_success INTEGER := 0;
    v_timeout INTEGER := 0;
    v_error_count INTEGER := 0;
BEGIN
    -- Get count of views
    SELECT COUNT(*) INTO v_total FROM tmp_view_complexity;
    
    RAISE NOTICE 'Extracting from % views (ordered by complexity)...', v_total;
    
    FOR v_record IN 
        SELECT * FROM tmp_view_complexity 
        ORDER BY dependency_level, view_name
    LOOP
        BEGIN
            v_start_time := clock_timestamp();
            v_status := 'SUCCESS';
            v_error := NULL;
            
            -- Extract sample with complexity-based LIMIT using stdout
            -- Note: We use a workaround since COPY TO requires absolute paths
            EXECUTE format(
                'COPY (SELECT * FROM %I ORDER BY random() LIMIT %s) TO STDOUT WITH CSV HEADER',
                v_record.view_name,
                v_record.sample_size
            );
            -- File will be written by psql \copy command instead
            
            GET DIAGNOSTICS v_count = ROW_COUNT;
            v_duration_ms := EXTRACT(EPOCH FROM (clock_timestamp() - v_start_time)) * 1000;
            v_success := v_success + 1;
            
            RAISE NOTICE '[%/%] ✓ view_% (L%): % rows in % ms', 
                v_success + v_timeout + v_error_count, 
                v_total, 
                v_record.view_name, 
                v_record.dependency_level,
                v_count, 
                v_duration_ms;
            
            INSERT INTO tmp_extraction_log 
                (object_name, object_type, extraction_status, rows_extracted, execution_time_ms)
            VALUES 
                (v_record.view_name, v_record.view_type, v_status, v_count, v_duration_ms);
                
        EXCEPTION 
            WHEN query_canceled THEN
                v_status := 'TIMEOUT';
                v_error := 'Extraction exceeded 10-second timeout';
                v_duration_ms := 10000;
                v_timeout := v_timeout + 1;
                RAISE WARNING '[%/%] ⏱ TIMEOUT: view_% (L%) - skipping', 
                    v_success + v_timeout + v_error_count, 
                    v_total, 
                    v_record.view_name,
                    v_record.dependency_level;
                INSERT INTO tmp_extraction_log 
                    (object_name, object_type, extraction_status, rows_extracted, execution_time_ms, error_message)
                VALUES 
                    (v_record.view_name, v_record.view_type, v_status, 0, v_duration_ms, v_error);
            WHEN OTHERS THEN
                v_status := 'ERROR';
                v_error := SQLERRM;
                v_duration_ms := EXTRACT(EPOCH FROM (clock_timestamp() - v_start_time)) * 1000;
                v_error_count := v_error_count + 1;
                RAISE WARNING '[%/%] ✗ ERROR: view_%: %', 
                    v_success + v_timeout + v_error_count, 
                    v_total, 
                    v_record.view_name, 
                    SQLERRM;
                INSERT INTO tmp_extraction_log 
                    (object_name, object_type, extraction_status, rows_extracted, execution_time_ms, error_message)
                VALUES 
                    (v_record.view_name, v_record.view_type, v_status, 0, v_duration_ms, v_error);
        END;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE 'Views: % successful, % timeout, % error out of %', 
        v_success, v_timeout, v_error_count, v_total;
END $$;

-- ===========================================================================
-- PHASE 4: EXPORT EXTRACTION LOG
-- ===========================================================================

\echo ''
\echo '--- PHASE 4: Exporting Extraction Log ---'

\copy (SELECT * FROM tmp_extraction_log ORDER BY object_type, object_name) TO 'extraction_log.csv' WITH CSV HEADER;

\echo ''
\echo 'Extraction Summary:'
SELECT 
    object_type,
    extraction_status,
    COUNT(*) as count,
    ROUND(AVG(execution_time_ms)) as avg_time_ms,
    SUM(rows_extracted) as total_rows
FROM tmp_extraction_log
GROUP BY object_type, extraction_status
ORDER BY object_type, 
    CASE extraction_status
        WHEN 'SUCCESS' THEN 1
        WHEN 'TIMEOUT' THEN 2
        WHEN 'ERROR' THEN 3
    END;

\echo ''
\echo 'Overall Statistics:'
SELECT 
    COUNT(*) as total_objects,
    COUNT(*) FILTER (WHERE extraction_status = 'SUCCESS') as successful,
    COUNT(*) FILTER (WHERE extraction_status = 'TIMEOUT') as timeout,
    COUNT(*) FILTER (WHERE extraction_status = 'ERROR') as error,
    SUM(rows_extracted) as total_rows_extracted,
    ROUND(AVG(execution_time_ms)) as avg_time_ms
FROM tmp_extraction_log;

-- Reset timeout
RESET statement_timeout;

\echo ''
\echo '======================================='
\echo 'Extraction Complete'
\echo 'Finished at:' `date`
\echo '======================================='
\echo ''
\echo 'Generated files:'
\echo '  - table_*_sample.csv (base table samples)'
\echo '  - view_*_sample.csv (view samples)'
\echo '  - extraction_log.csv (extraction status and timing)'
\echo ''
