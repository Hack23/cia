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
--
-- ===========================================================================
-- MODULAR ARCHITECTURE INTEGRATION (Optional)
-- ===========================================================================
--
-- This script can optionally leverage modular components for better code
-- organization and reusability. The modular files are 100% optional - this
-- script will work with or without them.
--
-- MODULAR FILES AVAILABLE:
--
-- 1. extract-sample-data-functions.sql (260 lines)
--    - cia_tmp_rowcount(schema, table): Get row counts
--    - cia_classify_temporal_view(view_name): Classify temporal granularity
--    - cia_percentile_sample(table, column, order_by): Sample at P1-P99
--    - cia_generate_distribution_summary(view_name): Generate distribution stats
--    Usage: \i extract-sample-data-functions.sql
--
-- 2. extract-percentile-summaries.sql (180 lines)
--    - Optional Phase 6.5 module
--    - Generates 24 percentile distribution CSVs
--    - Can be run standalone: psql -f extract-percentile-summaries.sql
--
-- TO USE MODULAR FUNCTIONS:
--   Uncomment the line below to load helper functions from external file:
--   -- \i extract-sample-data-functions.sql
--
-- ===========================================================================
-- STATISTICAL SAMPLING METHODOLOGY
-- ===========================================================================
--
-- This script implements advanced statistical sampling strategies to ensure
-- representative data extraction across temporal, categorical, and numerical
-- dimensions of the CIA database.
--
-- SAMPLING STRATEGIES:
--
-- 1. TEMPORAL STRATIFIED SAMPLING
--    Purpose: Ensure representative coverage across time periods
--    When to use: Views with date/timestamp columns and temporal patterns
--    Implementation:
--      - Daily views: 2 samples per day (last 30 days) = ~60 samples
--      - Weekly views: 2 samples per week (last 6 months) = ~52 samples
--      - Monthly views: 2 samples per month (last 3 years) = ~72 samples
--      - Annual views: 2 samples per year (full history) = ~40-50 samples
--      - Trend views: 1 sample per time bucket (all periods)
--    Expected coverage: 10+ years (2002-2026), all major political periods
--
-- 2. PERCENTILE-BASED SAMPLING
--    Purpose: Capture distribution shape for numerical metrics
--    When to use: Views with risk scores, performance metrics, counts
--    Implementation: Sample at P1, P10, P25, P50, P75, P90, P99
--    Expected coverage: Full range from min to max, including outliers
--    Functions: cia_percentile_sample(), cia_generate_distribution_summary()
--
-- 3. CATEGORICAL STRATIFIED SAMPLING
--    Purpose: Ensure all categories represented
--    When to use: Views with party, committee, status, type columns
--    Implementation: Extract distinct values, sample proportionally
--    Expected coverage: All 8 major parties (S, M, SD, C, V, KD, L, MP)
--
-- 4. RANDOM SAMPLING
--    Purpose: Unbiased sample when no stratification needed
--    When to use: Non-temporal, non-numerical entity tables
--    Implementation: TABLESAMPLE BERNOULLI or ORDER BY RANDOM()
--
-- SAMPLE SIZE CONFIGURATION:
--    - Default: 200 rows (general tables/views)
--    - Political entities: 500 rows (party/committee/person)
--    - Documents/Voting: 300 rows (high volume sources)
--    - Analytical views: 500 rows (trend/proximity/career analysis)
--    - Complete extraction: < 3000 rows (small datasets)
--
-- VALIDATION METRICS:
--    - Temporal coverage: Minimum 10 years, ideally 2002-2026
--    - Categorical coverage: All 8 major parties present
--    - Percentile coverage: P1, P10, P25, P50, P75, P90, P99 in distributions
--    - Warnings generated for missing coverage gaps
--
-- ===========================================================================

\set ON_ERROR_STOP off
\timing on
\set VERBOSITY verbose

-- Set statement timeout for individual extraction queries
-- This prevents individual queries from hanging indefinitely
-- The shell script provides overall timeout protection
SET statement_timeout = '120s';  -- Increased from 30s to 120s for complex views
SET lock_timeout = '30s';        -- Wait max 30s for locks
SET idle_in_transaction_session_timeout = '180s';  -- Kill idle transactions after 3 minutes

\echo '=================================================='
\echo 'CIA Sample Data Extraction'
\echo 'Started:' `date`
\echo 'Statement timeout: 120s per query'
\echo 'Lock timeout: 30s'
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

-- Extended sample sizes for analytical trend views (increased for better temporal coverage)
\set TREND_SAMPLE_SIZE 500

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

-- ===========================================================================
-- SECTION 0.0.5: CREATE EXTRACTION TRACKING TABLE
-- ===========================================================================
-- Track success/failure of each extraction for summary report at end
-- ===========================================================================

DROP TABLE IF EXISTS cia_extraction_tracking;
CREATE TEMP TABLE cia_extraction_tracking (
    object_type TEXT,
    object_name TEXT,
    status TEXT,  -- 'success', 'timeout', 'error', 'empty', 'skipped'
    error_message TEXT,
    row_count BIGINT,
    extraction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ON COMMIT PRESERVE ROWS;

\echo 'Created extraction tracking table: cia_extraction_tracking'
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
EXCEPTION 
    WHEN query_canceled THEN
        -- Re-raise query_canceled (timeout) so caller can handle it
        RAISE;
    WHEN OTHERS THEN
        -- Re-raise other errors so caller can capture full error details (SQLERRM)
        -- This allows the caller's EXCEPTION handler to properly log and track the error
        RAISE;
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
-- SECTION 0.1B: PERCENTILE SAMPLING FUNCTIONS
-- ===========================================================================
-- Functions for advanced statistical sampling with percentile-based distribution
-- analysis for numerical columns (risk scores, counts, metrics)
-- ===========================================================================

-- Function: cia_percentile_sample
-- Purpose: Sample rows at key percentiles (P1, P10, P25, P50, P75, P90, P99)
--          for numerical columns to capture distribution shape
-- Parameters:
--   p_table_name: Table or view name to sample from
--   p_column_name: Numerical column to use for percentile calculation
--   p_order_by: Optional ORDER BY clause for tie-breaking (default: primary key)
-- Returns: Sampled rows representing key percentiles
DROP FUNCTION IF EXISTS cia_percentile_sample(text, text, text);
CREATE OR REPLACE FUNCTION cia_percentile_sample(
    p_table_name text,
    p_column_name text,
    p_order_by text DEFAULT NULL
)
RETURNS TABLE (
    percentile_label text,
    percentile_value numeric,
    row_data jsonb
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_sql text;
    v_order_by text;
BEGIN
    -- Default to column name if no order_by specified
    v_order_by := COALESCE(p_order_by, p_column_name);
    
    -- Build dynamic SQL to extract percentile samples using PERCENT_RANK()
    -- Design decision: PERCENT_RANK() directly calculates a continuous percentile
    -- position for each row, allowing precise matching of specific percentiles
    -- (P1, P10, P25, etc.) without relying on fixed NTILE(100) buckets
    v_sql := format('
        WITH ranked_data AS (
            SELECT 
                %I AS column_value,
                row_to_json(t.*)::jsonb AS row_data,
                PERCENT_RANK() OVER (ORDER BY %I) AS pct_rank
            FROM %I AS t
            WHERE %I IS NOT NULL
        ),
        target_percentiles AS (
            SELECT unnest(ARRAY[0.01, 0.10, 0.25, 0.50, 0.75, 0.90, 0.99]) AS target_pct,
                   unnest(ARRAY[''P1'', ''P10'', ''P25'', ''P50'', ''P75'', ''P90'', ''P99'']) AS percentile_label
        ),
        closest_rows AS (
            SELECT DISTINCT ON (tp.percentile_label)
                tp.percentile_label,
                rd.column_value AS percentile_value,
                rd.row_data
            FROM target_percentiles tp
            CROSS JOIN LATERAL (
                SELECT column_value, row_data, pct_rank
                FROM ranked_data
                WHERE pct_rank >= tp.target_pct
                ORDER BY pct_rank, %I
                LIMIT 1
            ) rd
        )
        SELECT percentile_label, percentile_value, row_data
        FROM closest_rows
        ORDER BY percentile_value
    ', p_column_name, p_column_name, p_table_name, p_column_name, v_order_by);
    
    RETURN QUERY EXECUTE v_sql;
EXCEPTION WHEN OTHERS THEN
    RAISE NOTICE 'ERROR in cia_percentile_sample for %.%: %', p_table_name, p_column_name, SQLERRM;
    RETURN;
END;
$$;

\echo 'Created helper function: cia_percentile_sample'

-- Function: cia_generate_distribution_summary
-- Purpose: Generate comprehensive distribution summary for all numerical columns
--          in a table/view, including min, max, percentiles, and distinct count
-- Parameters:
--   p_table_name: Table or view name to analyze
-- Returns: CSV-formatted distribution summary with column statistics
DROP FUNCTION IF EXISTS cia_generate_distribution_summary(text);
CREATE OR REPLACE FUNCTION cia_generate_distribution_summary(p_table_name text)
RETURNS TABLE (
    column_name text,
    data_type text,
    distinct_count bigint,
    min_value numeric,
    max_value numeric,
    p1 numeric,
    p10 numeric,
    p25 numeric,
    median numeric,
    p75 numeric,
    p90 numeric,
    p99 numeric
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_column_record RECORD;
    v_sql text;
BEGIN
    -- Iterate through all numerical columns in the table/view
    FOR v_column_record IN
        SELECT c.column_name, c.data_type
        FROM information_schema.columns c
        WHERE c.table_schema = 'public'
          AND c.table_name = p_table_name
          AND c.data_type IN ('integer', 'bigint', 'numeric', 'real', 'double precision', 
                              'smallint', 'decimal', 'money')
        ORDER BY c.ordinal_position
    LOOP
        BEGIN
            -- Build dynamic SQL to calculate distribution statistics
            v_sql := format('
                SELECT 
                    %L::text AS column_name,
                    %L::text AS data_type,
                    COUNT(DISTINCT %I) AS distinct_count,
                    MIN(%I)::numeric AS min_value,
                    MAX(%I)::numeric AS max_value,
                    PERCENTILE_CONT(0.01) WITHIN GROUP (ORDER BY %I)::numeric AS p1,
                    PERCENTILE_CONT(0.10) WITHIN GROUP (ORDER BY %I)::numeric AS p10,
                    PERCENTILE_CONT(0.25) WITHIN GROUP (ORDER BY %I)::numeric AS p25,
                    PERCENTILE_CONT(0.50) WITHIN GROUP (ORDER BY %I)::numeric AS median,
                    PERCENTILE_CONT(0.75) WITHIN GROUP (ORDER BY %I)::numeric AS p75,
                    PERCENTILE_CONT(0.90) WITHIN GROUP (ORDER BY %I)::numeric AS p90,
                    PERCENTILE_CONT(0.99) WITHIN GROUP (ORDER BY %I)::numeric AS p99
                FROM %I
                WHERE %I IS NOT NULL
            ',
                v_column_record.column_name,
                v_column_record.data_type,
                v_column_record.column_name,
                v_column_record.column_name,
                v_column_record.column_name,
                v_column_record.column_name,
                v_column_record.column_name,
                v_column_record.column_name,
                v_column_record.column_name,
                v_column_record.column_name,
                v_column_record.column_name,
                v_column_record.column_name,
                p_table_name,
                v_column_record.column_name
            );
            
            RETURN QUERY EXECUTE v_sql;
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'ERROR analyzing column %.%: %', p_table_name, v_column_record.column_name, SQLERRM;
            CONTINUE;
        END;
    END LOOP;
END;
$$;

\echo 'Created helper function: cia_generate_distribution_summary'
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
    RAISE NOTICE 'Getting row counts from statistics (fast)...';
    RAISE NOTICE '';
    
    -- Count empty vs non-empty tables using pg_class statistics (fast)
    FOR table_rec IN 
        SELECT t.tablename,
               COALESCE(c.reltuples, 0)::BIGINT AS row_count
        FROM pg_tables t
        JOIN pg_class c ON c.relname = t.tablename
        JOIN pg_namespace n ON n.oid = c.relnamespace AND n.nspname = t.schemaname
        WHERE t.schemaname = 'public'
        ORDER BY t.tablename
    LOOP
        IF table_rec.row_count > 0 THEN
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
-- Use pg_class.reltuples for fast row count estimates
WITH table_counts AS (
    SELECT t.schemaname,
           t.tablename,
           COALESCE(c.reltuples, 0)::BIGINT AS row_count
    FROM pg_tables t
    JOIN pg_class c ON c.relname = t.tablename
    JOIN pg_namespace n ON n.oid = c.relnamespace AND n.nspname = t.schemaname
    WHERE t.schemaname = 'public'
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
    -- CHANGED: Include ALL tables, even empty ones (for header-only CSV files)
    -- WHERE row_count > 0  -- REMOVED: No longer skip empty tables
)
SELECT format(
    '\echo ''[TABLE-%s] Extracting: %I.%I (%s rows of %s total)''' || E'\n' ||
    '\copy (SELECT * FROM %I.%I ORDER BY random() LIMIT %s) TO ''%s_sample.csv'' CSV HEADER' || E'\n' ||
    'INSERT INTO cia_extraction_tracking (object_type, object_name, status, row_count) VALUES (''table'', ''' || '%s' || ''', CASE WHEN %s = 0 THEN ''empty'' ELSE ''success'' END, %s);' || E'\n' ||
    CASE 
        WHEN row_count = 0 THEN '\echo ''  ℹ️  Empty table - header-only CSV generated: %s_sample.csv''' || E'\n'
        ELSE '\echo ''  ✓ Completed: %s_sample.csv''' || E'\n'
    END,
    extraction_type,
    schemaname,
    tablename,
    sample_rows,
    row_count,
    schemaname,
    tablename,
    sample_rows,
    file_prefix,
    tablename,
    row_count,
    row_count,
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

-- Create persistent temp table to cache view row counts (reused in Phase 2)
DROP TABLE IF EXISTS cia_view_row_counts;
CREATE TEMP TABLE cia_view_row_counts (
    schemaname TEXT,
    viewname TEXT,
    view_type TEXT,
    row_count BIGINT
);

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
        
        -- Use fast pg_class statistics for materialized views, slow COUNT for regular views
        -- Wrap in exception handler to gracefully handle timeouts
        BEGIN
            IF view_record.object_type = 'MATERIALIZED VIEW' THEN
                -- Fast: use cached statistics from pg_class
                SELECT COALESCE(c.reltuples, 0)::BIGINT INTO row_count
                FROM pg_class c
                JOIN pg_namespace n ON n.oid = c.relnamespace
                WHERE n.nspname = view_record.schemaname
                  AND c.relname = view_record.object_name;
            ELSE
                -- Regular views need actual COUNT (no cached stats)
                row_count := cia_tmp_rowcount(view_record.schemaname, view_record.object_name);
            END IF;
            
            -- Cache result in temp table for Phase 2 reuse
            INSERT INTO cia_view_row_counts(schemaname, viewname, view_type, row_count)
            VALUES (view_record.schemaname, view_record.object_name, view_record.object_type, row_count);
            
            -- Show result immediately after
            IF row_count > 0 THEN
                RAISE NOTICE '  ✓ Contains % rows', row_count;
                extract_count := extract_count + 1;
            ELSE
                RAISE NOTICE '  ⚠️  EMPTY (0 rows)';
            END IF;
            
        EXCEPTION 
            WHEN query_canceled THEN
                -- Handle statement timeout (SQLSTATE 57014)
                RAISE WARNING '  ⏱️  TIMEOUT after % - skipping view and continuing with next',
                    current_setting('statement_timeout');
                
                -- Still cache with -1 to indicate timeout (so Phase 2 can skip it)
                INSERT INTO cia_view_row_counts(schemaname, viewname, view_type, row_count)
                VALUES (view_record.schemaname, view_record.object_name, view_record.object_type, -1);
                
                -- Track timeout in extraction tracking (use lowercase 'view' for consistency)
                INSERT INTO cia_extraction_tracking(object_type, object_name, status, error_message, row_count)
                VALUES ('view', view_record.object_name, 'timeout', 'Statement timeout during row count', -1);
                
            WHEN OTHERS THEN
                -- Handle any other errors
                RAISE WARNING '  ❌ ERROR: % - skipping view and continuing with next', SQLERRM;
                
                -- Cache with -2 to indicate error (so Phase 2 can skip it)
                INSERT INTO cia_view_row_counts(schemaname, viewname, view_type, row_count)
                VALUES (view_record.schemaname, view_record.object_name, view_record.object_type, -2);
                
                -- Track error in extraction tracking (use lowercase 'view' for consistency)
                INSERT INTO cia_extraction_tracking(object_type, object_name, status, error_message, row_count)
                VALUES ('view', view_record.object_name, 'error', SQLERRM, -2);
        END;
        
        RAISE NOTICE '';
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE '✓ Phase 1 complete: % of % views have data', extract_count, total_views;
    RAISE NOTICE '';
END $$;

\echo ''
\echo 'Phase 2: Generating extraction commands (using cached row counts)...'
\echo ''

\! rm -f :VIEW_CMD_FILE
\pset format unaligned
\pset tuples_only on

\o :VIEW_CMD_FILE
-- Use cached row counts from Phase 1 temp table instead of recounting
WITH view_counts AS (
    SELECT schemaname,
           viewname,
           row_count
    FROM cia_view_row_counts
    WHERE viewname != 'view_riksdagen_coalition_alignment_matrix'
      AND viewname != 'view_riksdagen_intelligence_dashboard'
      -- Skip views that timed out or had errors during Phase 1
      -- row_count: -1 = timeout, -2 = error, 0+ = success
      AND row_count >= 0
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
    -- CHANGED: Include ALL views, even empty ones (for header-only CSV files)
    -- WHERE vc.row_count > 0  -- REMOVED: No longer skip empty views
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
        -- 2. Extended samples for analytical trend views (election proximity, seasonal activity, career trajectory)
        -- 3. Extended samples for party/committee/person views
        -- 4. Extended samples for document/voting/worldbank views
        -- 5. Default sample size for others
        CASE 
            -- Complete extraction for small views
            WHEN vtc.row_count <= :COMPLETE_EXTRACTION_THRESHOLD::int THEN vtc.row_count
            -- Extended samples for analytical trend views (500 rows for better temporal coverage)
            WHEN vtc.viewname IN ('view_riksdagen_election_proximity_trends',
                                   'view_riksdagen_seasonal_activity_patterns',
                                   'view_riksdagen_politician_career_trajectory') THEN
                LEAST(:TREND_SAMPLE_SIZE::int, vtc.row_count)
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
            WHEN vtc.viewname IN ('view_riksdagen_election_proximity_trends',
                                   'view_riksdagen_seasonal_activity_patterns',
                                   'view_riksdagen_politician_career_trajectory') THEN 'TREND-EXTENDED'
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
                'INSERT INTO cia_extraction_tracking (object_type, object_name, status, row_count) VALUES (''view'', ''' || '%s' || ''', CASE WHEN %s = 0 THEN ''empty'' ELSE ''success'' END, %s);' || E'\n' ||
                CASE 
                    WHEN row_count = 0 THEN '\echo ''  ℹ️  Empty view - header-only CSV generated: %s_sample.csv''' || E'\n'
                    ELSE '\echo ''  ✓ Completed: %s_sample.csv (temporal stratification: daily)''' || E'\n'
                END,
                viewname,
                samples_per_bucket,
                temporal_column, schemaname, viewname, temporal_column,
                samples_per_bucket, sample_rows,
                file_prefix,
                viewname, row_count, row_count,
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
                'INSERT INTO cia_extraction_tracking (object_type, object_name, status, row_count) VALUES (''view'', ''' || '%s' || ''', CASE WHEN %s = 0 THEN ''empty'' ELSE ''success'' END, %s);' || E'\n' ||
                CASE 
                    WHEN row_count = 0 THEN '\echo ''  ℹ️  Empty view - header-only CSV generated: %s_sample.csv''' || E'\n'
                    ELSE '\echo ''  ✓ Completed: %s_sample.csv (temporal stratification: weekly)''' || E'\n'
                END,
                viewname,
                samples_per_bucket,
                temporal_column, schemaname, viewname, temporal_column,
                samples_per_bucket, sample_rows,
                file_prefix,
                viewname, row_count, row_count,
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
                'INSERT INTO cia_extraction_tracking (object_type, object_name, status, row_count) VALUES (''view'', ''' || '%s' || ''', CASE WHEN %s = 0 THEN ''empty'' ELSE ''success'' END, %s);' || E'\n' ||
                CASE 
                    WHEN row_count = 0 THEN '\echo ''  ℹ️  Empty view - header-only CSV generated: %s_sample.csv''' || E'\n'
                    ELSE '\echo ''  ✓ Completed: %s_sample.csv (temporal stratification: monthly)''' || E'\n'
                END,
                viewname,
                samples_per_bucket,
                temporal_column, schemaname, viewname, temporal_column,
                samples_per_bucket, sample_rows,
                file_prefix,
                viewname, row_count, row_count,
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
                'INSERT INTO cia_extraction_tracking (object_type, object_name, status, row_count) VALUES (''view'', ''' || '%s' || ''', CASE WHEN %s = 0 THEN ''empty'' ELSE ''success'' END, %s);' || E'\n' ||
                CASE 
                    WHEN row_count = 0 THEN '\echo ''  ℹ️  Empty view - header-only CSV generated: %s_sample.csv''' || E'\n'
                    ELSE '\echo ''  ✓ Completed: %s_sample.csv (temporal stratification: annual)''' || E'\n'
                END,
                viewname,
                samples_per_bucket,
                temporal_column, schemaname, viewname,
                samples_per_bucket, sample_rows,
                file_prefix,
                viewname, row_count, row_count,
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
                'INSERT INTO cia_extraction_tracking (object_type, object_name, status, row_count) VALUES (''view'', ''' || '%s' || ''', CASE WHEN %s = 0 THEN ''empty'' ELSE ''success'' END, %s);' || E'\n' ||
                CASE 
                    WHEN row_count = 0 THEN '\echo ''  ℹ️  Empty view - header-only CSV generated: %s_sample.csv''' || E'\n'
                    ELSE '\echo ''  ✓ Completed: %s_sample.csv (temporal stratification: trend)''' || E'\n'
                END,
                viewname,
                temporal_column, temporal_column, schemaname, viewname,
                sample_rows,
                file_prefix,
                viewname, row_count, row_count,
                file_prefix, file_prefix
            )
        
        -- =====================================================================
        -- NON-TEMPORAL VIEWS: Use random sampling with smart sample sizes
        -- =====================================================================
        ELSE
            format(
                '\echo ''[VIEW-%s] Extracting: %s (%s rows of %s total)''' || E'\n' ||
                '\copy (SELECT * FROM %I.%I ORDER BY random() LIMIT %s) TO ''%s_sample.csv'' WITH CSV HEADER' || E'\n' ||
                'INSERT INTO cia_extraction_tracking (object_type, object_name, status, row_count) VALUES (''view'', ''' || '%s' || ''', CASE WHEN %s = 0 THEN ''empty'' ELSE ''success'' END, %s);' || E'\n' ||
                CASE 
                    WHEN row_count = 0 THEN '\echo ''  ℹ️  Empty view - header-only CSV generated: %s_sample.csv''' || E'\n'
                    ELSE '\echo ''  ✓ Completed: %s_sample.csv''' || E'\n'
                END,
                extraction_type,
                viewname,
                sample_rows,
                row_count,
                schemaname,
                viewname,
                sample_rows,
                file_prefix,
                viewname, row_count, row_count,
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

-- 6.9: View Size Distribution (Row Counts) - Using cached reltuples for materialized views
\echo 'Generating view size distribution...'
\echo '  (Using pg_class statistics for materialized views - much faster)'

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
    -- For materialized views, use pg_class.reltuples (fast, uses cached stats)
    SELECT c.relname AS view_name, c.reltuples::bigint AS row_count
    FROM pg_class c
    JOIN pg_namespace n ON n.oid = c.relnamespace
    WHERE n.nspname = 'public' AND c.relkind = 'm'
    UNION ALL
    -- For regular views, use reltuples from a LIMIT 0 query estimate or just 0
    -- Regular views don't have stats, so we just report them without counting
    SELECT viewname AS view_name, 0::bigint AS row_count
    FROM pg_views WHERE schemaname = 'public'
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

-- Note: extraction_manifest table may not exist in all environments
-- Using a safer query that creates the table if data exists from earlier phases
\copy (SELECT 'tables' AS extraction_type, COUNT(*) AS file_count, 0::bigint AS total_rows FROM pg_tables WHERE schemaname = 'public' UNION ALL SELECT 'views', COUNT(*), 0 FROM pg_views WHERE schemaname = 'public' UNION ALL SELECT 'materialized_views', COUNT(*), 0 FROM pg_matviews WHERE schemaname = 'public') TO 'summary_extraction_types.csv' WITH CSV HEADER
\echo '✓ Generated: summary_extraction_types.csv'

-- ===========================================================================
-- 6.16-6.25: ANNUAL ENTITY DISTRIBUTIONS (for temporal view development)
-- ===========================================================================
\echo ''
\echo 'Generating annual entity distributions for temporal analysis...'

-- 6.16: Party Members by Year (when they were active/assigned)
\echo 'Generating annual party member distribution...'
\copy (SELECT EXTRACT(YEAR FROM a.from_date)::int AS year, p.party, COUNT(DISTINCT p.id) AS active_members FROM person_data p JOIN assignment_data a ON a.intressent_id = p.id WHERE a.from_date IS NOT NULL AND a.from_date >= '1990-01-01' AND p.party IS NOT NULL GROUP BY EXTRACT(YEAR FROM a.from_date)::int, p.party ORDER BY year, party) TO 'distribution_annual_party_members.csv' WITH CSV HEADER
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
\copy (SELECT first_year, party, COUNT(*) AS politicians_started FROM (SELECT p.id, p.party, EXTRACT(YEAR FROM MIN(a.from_date))::int AS first_year FROM person_data p JOIN assignment_data a ON a.intressent_id = p.id WHERE a.from_date IS NOT NULL AND p.party IS NOT NULL GROUP BY p.id, p.party) career_starts WHERE first_year >= 1990 GROUP BY first_year, party ORDER BY first_year, politicians_started DESC) TO 'distribution_politician_career_starts.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_politician_career_starts.csv'

-- 6.24: Document Status Changes by Year
\echo 'Generating annual document status distribution...'
\copy (SELECT EXTRACT(YEAR FROM made_public_date)::int AS year, status, COUNT(*) AS doc_count FROM document_data WHERE made_public_date IS NOT NULL AND made_public_date >= '1990-01-01' AND status IS NOT NULL GROUP BY EXTRACT(YEAR FROM made_public_date)::int, status ORDER BY year, doc_count DESC) TO 'distribution_annual_document_status.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_annual_document_status.csv'

-- 6.25: Empty Views Report (views with no data that need attention)
-- Using cached stats from Phase 1 analysis to avoid redundant counting
\echo 'Generating empty views report...'
\echo '  (Using pg_class statistics for materialized views)'

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
    -- For materialized views, use pg_class.reltuples (fast)
    SELECT c.relname AS view_name, 'materialized' AS view_type, c.reltuples::bigint AS row_count
    FROM pg_class c
    JOIN pg_namespace n ON n.oid = c.relnamespace
    WHERE n.nspname = 'public' AND c.relkind = 'm'
    -- Note: Regular views require actual count - skip them in empty check
    -- They were already counted in Phase 1
) v
WHERE row_count = 0
ORDER BY view_category, view_name;

\copy (SELECT * FROM tmp_empty_views) TO 'report_empty_views.csv' WITH CSV HEADER
DROP TABLE tmp_empty_views;
\echo '✓ Generated: report_empty_views.csv'

-- ===========================================================================
-- 6.26-6.50: ANALYTICAL VIEW DISTRIBUTIONS (Risk, Performance, Assessment)
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== ANALYTICAL VIEW DISTRIBUTIONS              ==='
\echo '=================================================='
\echo ''
\echo 'Generating distribution statistics for analytical/assessment views...'
\echo 'These support debugging and improving analytical view population'
\echo ''

-- ---------------------------------------------------------------------------
-- 6.26: Politician Risk Level Distribution
-- ---------------------------------------------------------------------------
\echo '6.26: Politician Risk Level Distribution...'
\copy (SELECT COALESCE(risk_level, 'UNKNOWN') AS risk_level, COUNT(*) AS politician_count, ROUND(COUNT(*) * 100.0 / NULLIF(SUM(COUNT(*)) OVER (), 0), 2) AS percentage FROM view_politician_risk_summary GROUP BY risk_level ORDER BY CASE risk_level WHEN 'CRITICAL' THEN 1 WHEN 'HIGH' THEN 2 WHEN 'MEDIUM' THEN 3 WHEN 'LOW' THEN 4 WHEN 'MINIMAL' THEN 5 ELSE 6 END) TO 'distribution_politician_risk_levels.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_politician_risk_levels.csv'

-- ---------------------------------------------------------------------------
-- 6.27: Politician Risk by Party Distribution
-- ---------------------------------------------------------------------------
\echo '6.27: Politician Risk by Party...'
\copy (SELECT party, risk_level, COUNT(*) AS politician_count, ROUND(AVG(risk_score), 2) AS avg_risk_score FROM view_politician_risk_summary WHERE party IS NOT NULL GROUP BY party, risk_level ORDER BY party, CASE risk_level WHEN 'CRITICAL' THEN 1 WHEN 'HIGH' THEN 2 WHEN 'MEDIUM' THEN 3 WHEN 'LOW' THEN 4 WHEN 'MINIMAL' THEN 5 ELSE 6 END) TO 'distribution_risk_by_party.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_risk_by_party.csv'

-- ---------------------------------------------------------------------------
-- 6.28: Risk Score Distribution Buckets
-- ---------------------------------------------------------------------------
\echo '6.28: Risk Score Distribution Buckets...'
\copy (SELECT CASE WHEN risk_score >= 70 THEN '70-100 (Critical)' WHEN risk_score >= 50 THEN '50-69 (High)' WHEN risk_score >= 30 THEN '30-49 (Medium)' WHEN risk_score >= 10 THEN '10-29 (Low)' ELSE '0-9 (Minimal)' END AS score_bucket, COUNT(*) AS politician_count, ROUND(AVG(risk_score), 2) AS avg_score, MIN(risk_score) AS min_score, MAX(risk_score) AS max_score FROM view_politician_risk_summary GROUP BY CASE WHEN risk_score >= 70 THEN '70-100 (Critical)' WHEN risk_score >= 50 THEN '50-69 (High)' WHEN risk_score >= 30 THEN '30-49 (Medium)' WHEN risk_score >= 10 THEN '10-29 (Low)' ELSE '0-9 (Minimal)' END ORDER BY MIN(risk_score) DESC) TO 'distribution_risk_score_buckets.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_risk_score_buckets.csv'

-- ---------------------------------------------------------------------------
-- 6.29: Voting Anomaly Classification Distribution
-- ---------------------------------------------------------------------------
\echo '6.29: Voting Anomaly Classification...'
\copy (SELECT COALESCE(anomaly_classification, 'UNKNOWN') AS anomaly_classification, COUNT(*) AS politician_count, ROUND(COUNT(*) * 100.0 / NULLIF(SUM(COUNT(*)) OVER (), 0), 2) AS percentage, ROUND(AVG(total_rebellions), 2) AS avg_rebellions FROM view_riksdagen_voting_anomaly_detection GROUP BY anomaly_classification ORDER BY CASE anomaly_classification WHEN 'FREQUENT_STRONG_REBEL' THEN 1 WHEN 'CONSISTENT_REBEL' THEN 2 WHEN 'MODERATE_REBEL' THEN 3 WHEN 'OCCASIONAL_REBEL' THEN 4 WHEN 'PARTY_ALIGNED' THEN 5 ELSE 6 END) TO 'distribution_voting_anomaly_classification.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_voting_anomaly_classification.csv'

-- ---------------------------------------------------------------------------
-- 6.30: Voting Anomaly by Party Distribution
-- ---------------------------------------------------------------------------
\echo '6.30: Voting Anomaly by Party...'
\copy (SELECT party, anomaly_classification, COUNT(*) AS politician_count, ROUND(AVG(total_rebellions), 2) AS avg_rebellions FROM view_riksdagen_voting_anomaly_detection WHERE party IS NOT NULL GROUP BY party, anomaly_classification ORDER BY party, CASE anomaly_classification WHEN 'FREQUENT_STRONG_REBEL' THEN 1 WHEN 'CONSISTENT_REBEL' THEN 2 WHEN 'MODERATE_REBEL' THEN 3 WHEN 'OCCASIONAL_REBEL' THEN 4 WHEN 'PARTY_ALIGNED' THEN 5 ELSE 6 END) TO 'distribution_anomaly_by_party.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_anomaly_by_party.csv'

-- ---------------------------------------------------------------------------
-- 6.31: Ministry Risk Level Evolution Distribution
-- ---------------------------------------------------------------------------
\echo '6.31: Ministry Risk Level Evolution...'
\copy (SELECT COALESCE(risk_level, 'UNKNOWN') AS risk_level, COUNT(*) AS period_count, ROUND(COUNT(*) * 100.0 / NULLIF(SUM(COUNT(*)) OVER (), 0), 2) AS percentage, ROUND(AVG(documents_produced), 2) AS avg_documents FROM view_ministry_risk_evolution GROUP BY risk_level ORDER BY CASE risk_level WHEN 'CRITICAL' THEN 1 WHEN 'HIGH' THEN 2 WHEN 'MEDIUM' THEN 3 WHEN 'ELEVATED' THEN 4 WHEN 'LOW' THEN 5 ELSE 6 END) TO 'distribution_ministry_risk_levels.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_ministry_risk_levels.csv'

-- ---------------------------------------------------------------------------
-- 6.32: Ministry Risk by Quarter
-- ---------------------------------------------------------------------------
\echo '6.32: Ministry Risk by Quarter...'
\copy (SELECT year, quarter, risk_level, COUNT(*) AS ministry_count, ROUND(AVG(documents_produced), 2) AS avg_documents FROM view_ministry_risk_evolution WHERE year IS NOT NULL GROUP BY year, quarter, risk_level ORDER BY year DESC, quarter DESC, CASE risk_level WHEN 'CRITICAL' THEN 1 WHEN 'HIGH' THEN 2 WHEN 'MEDIUM' THEN 3 WHEN 'ELEVATED' THEN 4 WHEN 'LOW' THEN 5 ELSE 6 END) TO 'distribution_ministry_risk_quarterly.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_ministry_risk_quarterly.csv'

-- ---------------------------------------------------------------------------
-- 6.33: Party Performance Metrics Distribution
-- ---------------------------------------------------------------------------
\echo '6.33: Party Performance Metrics...'
\copy (SELECT party, party_name, active_members, inactive_members, documents_last_year, motions_last_year, propositions_last_year, ROUND(docs_per_member, 2) AS docs_per_member, performance_level FROM view_party_performance_metrics ORDER BY active_members DESC) TO 'distribution_party_performance.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_party_performance.csv'

-- ---------------------------------------------------------------------------
-- 6.34: Committee Productivity Distribution
-- ---------------------------------------------------------------------------
\echo '6.34: Committee Productivity...'
\copy (SELECT committee_name, total_documents, propositions_count, reports_count, total_members, ROUND(documents_per_member, 2) AS docs_per_member, productivity_level FROM view_committee_productivity ORDER BY total_documents DESC) TO 'distribution_committee_productivity.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_committee_productivity.csv'

-- ---------------------------------------------------------------------------
-- 6.35: Coalition Alignment Matrix Distribution
-- (SKIPPED - view_riksdagen_coalition_alignment_matrix is very slow to query)
-- ---------------------------------------------------------------------------
\echo '6.35: Coalition Alignment... (SKIPPED - slow view)'
-- \copy (SELECT party1, party2, shared_votes, aligned_votes, opposed_votes, ROUND(alignment_rate, 2) AS alignment_rate, coalition_likelihood, bloc_relationship FROM view_riksdagen_coalition_alignment_matrix WHERE shared_votes > 0 ORDER BY alignment_rate DESC LIMIT 100) TO 'distribution_coalition_alignment.csv' WITH CSV HEADER
\echo '✓ Skipped: distribution_coalition_alignment.csv'

-- ---------------------------------------------------------------------------
-- 6.36: Politician Experience Level Distribution
-- ---------------------------------------------------------------------------
\echo '6.36: Politician Experience Levels...'
\copy (SELECT COALESCE(experience_level, 'UNKNOWN') AS experience_level, COUNT(*) AS politician_count, ROUND(COUNT(*) * 100.0 / NULLIF(SUM(COUNT(*)) OVER (), 0), 2) AS percentage FROM view_riksdagen_politician_experience_summary GROUP BY experience_level ORDER BY experience_level) TO 'distribution_experience_levels.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_experience_levels.csv'

-- ---------------------------------------------------------------------------
-- 6.37: Experience Level by Party
-- (Note: experience_summary doesn't have party, using person_data join)
-- ---------------------------------------------------------------------------
\echo '6.37: Experience Level by Party...'
\copy (SELECT p.party, e.experience_level, COUNT(*) AS politician_count FROM view_riksdagen_politician_experience_summary e JOIN person_data p ON e.person_id = p.id WHERE p.party IS NOT NULL GROUP BY p.party, e.experience_level ORDER BY p.party, e.experience_level) TO 'distribution_experience_by_party.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_experience_by_party.csv'

-- ---------------------------------------------------------------------------
-- 6.38: Politician Influence Metrics Distribution
-- ---------------------------------------------------------------------------
\echo '6.38: Politician Influence Metrics...'
\copy (SELECT COALESCE(influence_classification, 'UNKNOWN') AS influence_classification, COUNT(*) AS politician_count, ROUND(COUNT(*) * 100.0 / NULLIF(SUM(COUNT(*)) OVER (), 0), 2) AS percentage FROM view_riksdagen_politician_influence_metrics GROUP BY influence_classification ORDER BY influence_classification) TO 'distribution_influence_buckets.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_influence_buckets.csv'

-- ---------------------------------------------------------------------------
-- 6.39: Party Effectiveness Trends
-- ---------------------------------------------------------------------------
\echo '6.39: Party Effectiveness Trends...'
\copy (SELECT party, year, quarter, documents_produced, motions_count, active_members, ROUND(avg_win_rate, 2) AS avg_win_rate, effectiveness_assessment FROM view_party_effectiveness_trends WHERE year >= EXTRACT(YEAR FROM CURRENT_DATE) - 5 ORDER BY party, year, quarter) TO 'distribution_party_effectiveness_trends.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_party_effectiveness_trends.csv'

-- ---------------------------------------------------------------------------
-- 6.40: Ministry Effectiveness Trends
-- ---------------------------------------------------------------------------
\echo '6.40: Ministry Effectiveness Trends...'
\copy (SELECT name AS ministry_name, year, quarter, documents_produced, government_bills, active_members, effectiveness_assessment FROM view_ministry_effectiveness_trends WHERE year >= EXTRACT(YEAR FROM CURRENT_DATE) - 5 ORDER BY name, year, quarter) TO 'distribution_ministry_effectiveness.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_ministry_effectiveness.csv'

-- ---------------------------------------------------------------------------
-- 6.41: Decision Temporal Trends
-- ---------------------------------------------------------------------------
\echo '6.41: Decision Temporal Trends...'
\copy (SELECT decision_year AS year, decision_month AS month, daily_decisions AS decision_count, approved_decisions, rejected_decisions, ROUND(daily_approval_rate, 2) AS approval_rate FROM view_decision_temporal_trends WHERE decision_year >= EXTRACT(YEAR FROM CURRENT_DATE) - 5 ORDER BY decision_year, decision_month) TO 'distribution_decision_trends.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_decision_trends.csv'

-- ---------------------------------------------------------------------------
-- 6.42: Ministry Decision Impact Distribution
-- ---------------------------------------------------------------------------
\echo '6.42: Ministry Decision Impact...'
\copy (SELECT ministry_code, committee, decision_type, total_proposals, approved_proposals, rejected_proposals, ROUND(approval_rate, 2) AS approval_rate FROM view_ministry_decision_impact ORDER BY total_proposals DESC LIMIT 200) TO 'distribution_ministry_decision_impact.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_ministry_decision_impact.csv'

-- ---------------------------------------------------------------------------
-- 6.43: Crisis Resilience Indicators
-- ---------------------------------------------------------------------------
\echo '6.43: Crisis Resilience Indicators...'
\copy (SELECT party, COALESCE(resilience_classification, 'UNKNOWN') AS resilience_classification, COUNT(*) AS politician_count, ROUND(AVG(resilience_score), 2) AS avg_resilience_score FROM view_riksdagen_crisis_resilience_indicators WHERE party IS NOT NULL GROUP BY party, resilience_classification ORDER BY party, resilience_classification) TO 'distribution_crisis_resilience.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_crisis_resilience.csv'

-- ---------------------------------------------------------------------------
-- 6.44: Party Momentum Analysis
-- ---------------------------------------------------------------------------
\echo '6.44: Party Momentum Analysis...'
\copy (SELECT party, year, quarter, period, ROUND(participation_rate, 2) AS participation_rate, ROUND(momentum, 2) AS momentum, trend_direction, stability_classification FROM view_riksdagen_party_momentum_analysis ORDER BY year DESC, quarter DESC, party) TO 'distribution_party_momentum.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_party_momentum.csv'

-- ---------------------------------------------------------------------------
-- 6.45: Politician Behavioral Trends
-- ---------------------------------------------------------------------------
\echo '6.45: Politician Behavioral Trends...'
\copy (SELECT party, COALESCE(behavioral_assessment, 'UNKNOWN') AS behavioral_assessment, COUNT(*) AS politician_count, ROUND(AVG(avg_absence_rate), 2) AS avg_absence_rate FROM view_politician_behavioral_trends WHERE party IS NOT NULL GROUP BY party, behavioral_assessment ORDER BY party, behavioral_assessment) TO 'distribution_behavioral_patterns_by_party.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_behavioral_patterns_by_party.csv'

-- ---------------------------------------------------------------------------
-- 6.46: Risk Score Evolution Over Time
-- ---------------------------------------------------------------------------
\echo '6.46: Risk Score Evolution Over Time...'
\copy (SELECT assessment_period, COALESCE(risk_severity, 'UNKNOWN') AS risk_severity, COUNT(*) AS politician_count, ROUND(AVG(risk_score), 2) AS avg_risk_score FROM view_risk_score_evolution GROUP BY assessment_period, risk_severity ORDER BY assessment_period DESC, CASE risk_severity WHEN 'CRITICAL' THEN 1 WHEN 'HIGH' THEN 2 WHEN 'MEDIUM' THEN 3 WHEN 'LOW' THEN 4 WHEN 'MINIMAL' THEN 5 ELSE 6 END) TO 'distribution_risk_evolution_temporal.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_risk_evolution_temporal.csv'

-- ---------------------------------------------------------------------------
-- 6.47: Committee Productivity Matrix
-- ---------------------------------------------------------------------------
\echo '6.47: Committee Productivity Matrix...'
\copy (SELECT committee_code, committee_name, year, quarter, total_documents, active_members, productivity_level, productivity_assessment FROM view_committee_productivity_matrix WHERE year >= EXTRACT(YEAR FROM CURRENT_DATE) - 3 ORDER BY year DESC, quarter DESC, total_documents DESC) TO 'distribution_committee_productivity_matrix.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_committee_productivity_matrix.csv'

-- ---------------------------------------------------------------------------
-- 6.48: Ministry Productivity Matrix
-- ---------------------------------------------------------------------------
\echo '6.48: Ministry Productivity Matrix...'
\copy (SELECT name AS ministry_name, year, documents_produced, propositions, government_bills, unique_contributors, performance_assessment FROM view_ministry_productivity_matrix WHERE year >= EXTRACT(YEAR FROM CURRENT_DATE) - 3 ORDER BY year DESC, documents_produced DESC) TO 'distribution_ministry_productivity_matrix.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_ministry_productivity_matrix.csv'

-- ---------------------------------------------------------------------------
-- 6.49: Politician Decision Pattern Distribution
-- ---------------------------------------------------------------------------
\echo '6.49: Politician Decision Patterns...'
\copy (SELECT party, committee, decision_year, COUNT(*) AS decision_count, SUM(total_decisions) AS total_decisions, ROUND(AVG(approval_rate), 2) AS avg_approval_rate FROM view_riksdagen_politician_decision_pattern WHERE party IS NOT NULL AND decision_year >= EXTRACT(YEAR FROM CURRENT_DATE) - 3 GROUP BY party, committee, decision_year ORDER BY party, decision_year DESC, committee) TO 'distribution_decision_patterns_by_party.csv' WITH CSV HEADER
\echo '✓ Generated: distribution_decision_patterns_by_party.csv'

-- ---------------------------------------------------------------------------
-- 6.50: Analytical Views Summary Statistics
-- ---------------------------------------------------------------------------
\echo '6.50: Analytical Views Summary Statistics...'

CREATE TEMP TABLE tmp_analytical_view_stats AS
SELECT 
    'view_politician_risk_summary' AS view_name, 'risk_assessment' AS category, COUNT(*) AS row_count,
    COUNT(DISTINCT party) AS distinct_parties, COUNT(DISTINCT risk_level) AS distinct_levels
FROM view_politician_risk_summary
UNION ALL
SELECT 'view_riksdagen_voting_anomaly_detection', 'anomaly_detection', COUNT(*), 
    COUNT(DISTINCT party), COUNT(DISTINCT anomaly_classification)
FROM view_riksdagen_voting_anomaly_detection
UNION ALL  
SELECT 'view_ministry_risk_evolution', 'risk_assessment', COUNT(*),
    COUNT(DISTINCT name), COUNT(DISTINCT risk_level)
FROM view_ministry_risk_evolution
UNION ALL
SELECT 'view_party_performance_metrics', 'performance_analysis', COUNT(*),
    COUNT(DISTINCT party), COUNT(DISTINCT performance_level)
FROM view_party_performance_metrics
UNION ALL
SELECT 'view_committee_productivity', 'productivity_analysis', COUNT(*),
    COUNT(DISTINCT committee_name), COUNT(DISTINCT productivity_level)
FROM view_committee_productivity
UNION ALL
SELECT 'view_riksdagen_politician_experience_summary', 'experience_analysis', COUNT(*),
    0, COUNT(DISTINCT experience_level)
FROM view_riksdagen_politician_experience_summary;

\copy (SELECT * FROM tmp_analytical_view_stats ORDER BY category, view_name) TO 'summary_analytical_views.csv' WITH CSV HEADER
DROP TABLE tmp_analytical_view_stats;
\echo '✓ Generated: summary_analytical_views.csv'

\echo ''
\echo '=== ANALYTICAL VIEW DISTRIBUTIONS COMPLETE ===' 
\echo ''

\echo ''
\echo '=================================================='
\echo '=== PHASE 6 COMPLETE: Distribution Stats Done ==='
\echo '=================================================='
\echo ''

-- ===========================================================================
-- PHASE 6.5: PERCENTILE DISTRIBUTION SUMMARIES
-- ===========================================================================
-- Generate comprehensive percentile-based distribution summaries for all
-- analytical views with numerical columns (risk scores, metrics, counts).
-- This provides P1, P10, P25, P50, P75, P90, P99 for understanding data shape.
-- ===========================================================================

\echo ''
\echo '=================================================='
\echo '=== PHASE 6.5: Percentile Distribution Summaries ==='
\echo '=================================================='
\echo ''
\echo 'Generating percentile-based distribution summaries for analytical views...'
\echo 'This captures P1, P10, P25, P50 (median), P75, P90, P99 for numerical columns'
\echo ''

-- ---------------------------------------------------------------------------
-- 6.5.1: Risk Assessment Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.1: Risk Assessment Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_politician_risk_summary')) TO 'percentile_politician_risk_summary.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_risk_summary.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_ministry_risk_evolution')) TO 'percentile_ministry_risk_evolution.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_ministry_risk_evolution.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_risk_score_evolution')) TO 'percentile_risk_score_evolution.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_risk_score_evolution.csv'

-- ---------------------------------------------------------------------------
-- 6.5.2: Performance & Productivity Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.2: Performance & Productivity Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_party_performance_metrics')) TO 'percentile_party_performance_metrics.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_party_performance_metrics.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_committee_productivity')) TO 'percentile_committee_productivity.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_committee_productivity.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_committee_productivity_matrix')) TO 'percentile_committee_productivity_matrix.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_committee_productivity_matrix.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_ministry_productivity_matrix')) TO 'percentile_ministry_productivity_matrix.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_ministry_productivity_matrix.csv'

-- ---------------------------------------------------------------------------
-- 6.5.3: Anomaly Detection Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.3: Anomaly Detection Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_voting_anomaly_detection')) TO 'percentile_voting_anomaly_detection.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_voting_anomaly_detection.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_seasonal_anomaly_detection')) TO 'percentile_seasonal_anomaly_detection.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_seasonal_anomaly_detection.csv'

-- ---------------------------------------------------------------------------
-- 6.5.4: Experience & Influence Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.4: Experience & Influence Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_experience_summary')) TO 'percentile_politician_experience_summary.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_experience_summary.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_influence_metrics')) TO 'percentile_politician_influence_metrics.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_influence_metrics.csv'

-- ---------------------------------------------------------------------------
-- 6.5.5: Behavioral & Decision Pattern Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.5: Behavioral & Decision Pattern Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_politician_behavioral_trends')) TO 'percentile_politician_behavioral_trends.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_behavioral_trends.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_decision_pattern')) TO 'percentile_politician_decision_pattern.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_decision_pattern.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_ministry_decision_impact')) TO 'percentile_ministry_decision_impact.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_ministry_decision_impact.csv'

-- ---------------------------------------------------------------------------
-- 6.5.6: Coalition & Momentum Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.6: Coalition & Momentum Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_party_momentum_analysis')) TO 'percentile_party_momentum_analysis.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_party_momentum_analysis.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_crisis_resilience_indicators')) TO 'percentile_crisis_resilience_indicators.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_crisis_resilience_indicators.csv'

-- ---------------------------------------------------------------------------
-- 6.5.7: Temporal Trend Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.7: Temporal Trend Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_election_proximity_trends')) TO 'percentile_election_proximity_trends.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_election_proximity_trends.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_seasonal_activity_patterns')) TO 'percentile_seasonal_activity_patterns.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_seasonal_activity_patterns.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_party_effectiveness_trends')) TO 'percentile_party_effectiveness_trends.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_party_effectiveness_trends.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_ministry_effectiveness_trend')) TO 'percentile_ministry_effectiveness_trend.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_ministry_effectiveness_trend.csv'

-- ---------------------------------------------------------------------------
-- 6.5.8: Career & Longevity Views - Percentile Distributions
-- ---------------------------------------------------------------------------
\echo '6.5.8: Career & Longevity Views - Percentile Distributions...'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_career_trajectory')) TO 'percentile_politician_career_trajectory.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_career_trajectory.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_longevity_analysis')) TO 'percentile_politician_longevity_analysis.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_longevity_analysis.csv'

\copy (SELECT * FROM cia_generate_distribution_summary('view_riksdagen_politician_role_evolution')) TO 'percentile_politician_role_evolution.csv' WITH CSV HEADER
\echo '✓ Generated: percentile_politician_role_evolution.csv'

\echo ''
\echo '=== PERCENTILE DISTRIBUTION SUMMARIES COMPLETE ===' 
\echo ''
\echo 'Generated percentile distributions for 24 analytical views'
\echo 'Each CSV contains: column_name, data_type, distinct_count, min, max, P1, P10, P25, median, P75, P90, P99'
\echo ''

\echo ''
\echo '=================================================='
\echo '=== PHASE 6.5 COMPLETE: Percentile Stats Done ==='
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

DROP FUNCTION IF EXISTS cia_percentile_sample(text, text, text);
\echo 'Dropped helper function: cia_percentile_sample'

DROP FUNCTION IF EXISTS cia_generate_distribution_summary(text);
\echo 'Dropped helper function: cia_generate_distribution_summary'

DROP TABLE IF EXISTS cia_view_row_counts;
\echo 'Dropped temp table: cia_view_row_counts'

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
\echo 'Analytical/Assessment View Distributions:'
\echo '  Risk Analysis:'
\echo '  - distribution_politician_risk_levels.csv     : Risk level breakdown'
\echo '  - distribution_risk_by_party.csv              : Risk levels by party'
\echo '  - distribution_risk_score_buckets.csv         : Risk score bucket analysis'
\echo '  - distribution_ministry_risk_levels.csv       : Ministry risk distribution'
\echo '  - distribution_ministry_risk_quarterly.csv    : Ministry risk over time'
\echo '  - distribution_risk_evolution_temporal.csv    : Risk score changes over time'
\echo ''
\echo '  Anomaly Detection:'
\echo '  - distribution_voting_anomaly_classification.csv : Voting anomaly types'
\echo '  - distribution_anomaly_by_party.csv             : Anomalies by party'
\echo ''
\echo '  Performance/Productivity Analysis:'
\echo '  - distribution_party_performance.csv            : Party performance metrics'
\echo '  - distribution_committee_productivity.csv       : Committee productivity'
\echo '  - distribution_committee_productivity_matrix.csv: Committee productivity over time'
\echo '  - distribution_ministry_productivity_matrix.csv : Ministry productivity over time'
\echo '  - distribution_party_effectiveness_trends.csv   : Party effectiveness over time'
\echo '  - distribution_ministry_effectiveness.csv       : Ministry effectiveness trends'
\echo ''
\echo '  Experience/Influence Analysis:'
\echo '  - distribution_experience_levels.csv           : Experience level breakdown'
\echo '  - distribution_experience_by_party.csv         : Experience by party'
\echo '  - distribution_influence_buckets.csv           : Influence score distribution'
\echo ''
\echo '  Behavioral/Decision Pattern Analysis:'
\echo '  - distribution_behavioral_patterns_by_party.csv: Behavioral patterns by party'
\echo '  - distribution_decision_patterns_by_party.csv  : Decision patterns by party'
\echo '  - distribution_decision_trends.csv             : Decision trends over time'
\echo '  - distribution_ministry_decision_impact.csv    : Ministry decision impact'
\echo ''
\echo '  Coalition/Momentum Analysis:'
\echo '  - distribution_coalition_alignment.csv         : Party coalition alignments'
\echo '  - distribution_party_momentum.csv              : Party momentum scores'
\echo '  - distribution_crisis_resilience.csv           : Crisis resilience indicators'
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
\echo '  - summary_analytical_views.csv       : Analytical view statistics'
\echo ''
\echo 'Percentile Distribution Summaries (NEW - Phase 6.5):'
\echo '  - percentile_*.csv                   : P1, P10, P25, P50, P75, P90, P99 for all numerical columns'
\echo '  Risk Assessment Views:'
\echo '    - percentile_politician_risk_summary.csv'
\echo '    - percentile_ministry_risk_evolution.csv'
\echo '    - percentile_risk_score_evolution.csv'
\echo '  Performance & Productivity Views:'
\echo '    - percentile_party_performance_metrics.csv'
\echo '    - percentile_committee_productivity*.csv'
\echo '    - percentile_ministry_productivity_matrix.csv'
\echo '  Anomaly Detection Views:'
\echo '    - percentile_voting_anomaly_detection.csv'
\echo '    - percentile_seasonal_anomaly_detection.csv'
\echo '  Experience & Influence Views:'
\echo '    - percentile_politician_experience_summary.csv'
\echo '    - percentile_politician_influence_metrics.csv'
\echo '  Behavioral & Decision Pattern Views:'
\echo '    - percentile_politician_behavioral_trends.csv'
\echo '    - percentile_politician_decision_pattern.csv'
\echo '    - percentile_ministry_decision_impact.csv'
\echo '  Coalition & Momentum Views:'
\echo '    - percentile_party_momentum_analysis.csv'
\echo '    - percentile_crisis_resilience_indicators.csv'
\echo '  Temporal Trend Views:'
\echo '    - percentile_election_proximity_trends.csv'
\echo '    - percentile_seasonal_activity_patterns.csv'
\echo '    - percentile_party_effectiveness_trends.csv'
\echo '    - percentile_ministry_effectiveness_trend.csv'
\echo '  Career & Longevity Views:'
\echo '    - percentile_politician_career_trajectory.csv'
\echo '    - percentile_politician_longevity_analysis.csv'
\echo '    - percentile_politician_role_evolution.csv'
\echo ''
\echo 'Sample Size Configuration:'
\echo '  - Default: 200 rows (most tables/views)'
\echo '  - Political entities (party/committee/person): 500 rows'
\echo '  - Document/Voting/Worldbank data: 300 rows'
\echo '  - Analytical trend views: 500 rows (election proximity, seasonal activity, career trajectory)'
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

-- Reset timeouts to defaults
RESET statement_timeout;
RESET lock_timeout;
RESET idle_in_transaction_session_timeout;

\echo ''
\echo '=================================================='
\echo '=== EXTRACTION SUMMARY REPORT                  ==='
\echo '=================================================='
\echo ''

-- Generate extraction summary report
DO $$
DECLARE
    total_tables INTEGER;
    total_views INTEGER;
    success_tables INTEGER;
    success_views INTEGER;
    empty_tables INTEGER;
    empty_views INTEGER;
    timeout_tables INTEGER;
    timeout_views INTEGER;
    error_tables INTEGER;
    error_views INTEGER;
BEGIN
    -- Count totals
    SELECT COUNT(*) INTO total_tables FROM cia_extraction_tracking WHERE object_type = 'table';
    SELECT COUNT(*) INTO total_views FROM cia_extraction_tracking WHERE object_type = 'view';
    
    -- Count by status for tables
    SELECT COUNT(*) INTO success_tables FROM cia_extraction_tracking WHERE object_type = 'table' AND status = 'success';
    SELECT COUNT(*) INTO empty_tables FROM cia_extraction_tracking WHERE object_type = 'table' AND status = 'empty';
    SELECT COUNT(*) INTO timeout_tables FROM cia_extraction_tracking WHERE object_type = 'table' AND status = 'timeout';
    SELECT COUNT(*) INTO error_tables FROM cia_extraction_tracking WHERE object_type = 'table' AND status = 'error';
    
    -- Count by status for views
    SELECT COUNT(*) INTO success_views FROM cia_extraction_tracking WHERE object_type = 'view' AND status = 'success';
    SELECT COUNT(*) INTO empty_views FROM cia_extraction_tracking WHERE object_type = 'view' AND status = 'empty';
    SELECT COUNT(*) INTO timeout_views FROM cia_extraction_tracking WHERE object_type = 'view' AND status = 'timeout';
    SELECT COUNT(*) INTO error_views FROM cia_extraction_tracking WHERE object_type = 'view' AND status = 'error';
    
    -- Display summary
    RAISE NOTICE '';
    RAISE NOTICE 'Extraction Summary:';
    RAISE NOTICE '==================';
    RAISE NOTICE '';
    RAISE NOTICE 'TABLES:';
    RAISE NOTICE '  Total processed: %', total_tables;
    RAISE NOTICE '  ✅ Success (with data): %', success_tables;
    RAISE NOTICE '  ℹ️  Empty (header only): %', empty_tables;
    IF timeout_tables > 0 THEN
        RAISE NOTICE '  ⏱️  Timed out: %', timeout_tables;
    END IF;
    IF error_tables > 0 THEN
        RAISE NOTICE '  ❌ Errors: %', error_tables;
    END IF;
    RAISE NOTICE '';
    RAISE NOTICE 'VIEWS:';
    RAISE NOTICE '  Total processed: %', total_views;
    RAISE NOTICE '  ✅ Success (with data): %', success_views;
    RAISE NOTICE '  ℹ️  Empty (header only): %', empty_views;
    IF timeout_views > 0 THEN
        RAISE NOTICE '  ⏱️  Timed out: %', timeout_views;
    END IF;
    IF error_views > 0 THEN
        RAISE NOTICE '  ❌ Errors: %', error_views;
    END IF;
    RAISE NOTICE '';
    
    -- Show details of timeouts/errors if any
    IF timeout_tables > 0 OR timeout_views > 0 OR error_tables > 0 OR error_views > 0 THEN
        RAISE NOTICE '================================================';
        RAISE NOTICE 'DETAILS OF TIMEOUTS AND ERRORS:';
        RAISE NOTICE '================================================';
        RAISE NOTICE '';
    END IF;
END $$;

-- Export detailed tracking report to CSV
\echo 'Exporting extraction summary to: extraction_summary_report.csv'
\copy (SELECT object_type, object_name, status, row_count, error_message, extraction_time FROM cia_extraction_tracking ORDER BY object_type, status, object_name) TO 'extraction_summary_report.csv' CSV HEADER

-- Show failed extractions if any
DO $$
DECLARE
    failed_rec RECORD;
    has_failures BOOLEAN := FALSE;
BEGIN
    FOR failed_rec IN 
        SELECT object_type, object_name, status, error_message
        FROM cia_extraction_tracking
        WHERE status IN ('timeout', 'error')
        ORDER BY object_type, status, object_name
    LOOP
        IF NOT has_failures THEN
            RAISE NOTICE '';
            RAISE NOTICE 'Failed Extractions:';
            RAISE NOTICE '-------------------';
            has_failures := TRUE;
        END IF;
        
        IF failed_rec.status = 'timeout' THEN
            RAISE NOTICE '⏱️  TIMEOUT: % - %', failed_rec.object_type, failed_rec.object_name;
        ELSE
            RAISE NOTICE '❌ ERROR: % - %', failed_rec.object_type, failed_rec.object_name;
            IF failed_rec.error_message IS NOT NULL THEN
                RAISE NOTICE '   Message: %', failed_rec.error_message;
            END IF;
        END IF;
    END LOOP;
    
    IF has_failures THEN
        RAISE NOTICE '';
        RAISE NOTICE 'See extraction_summary_report.csv for complete details';
        RAISE NOTICE '';
    END IF;
END $$;

\echo ''
\echo '=================================================='
\echo 'Sample data extraction complete.'
\echo 'Timeout protection: 120s per query, 30s lock wait, 180s idle transaction'
\echo 'Shell-level timeout (if configured): See EXTRACTION_TIMEOUT'
\echo ''
\echo 'Summary report: extraction_summary_report.csv'
\echo '=================================================='
