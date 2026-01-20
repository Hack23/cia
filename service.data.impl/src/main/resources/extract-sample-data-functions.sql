-- ===========================================================================
-- CIA Sample Data Extraction - Helper Functions
-- ===========================================================================
-- Modular helper functions for sample data extraction and statistical analysis
-- 
-- This file contains reusable PostgreSQL functions that can be sourced by
-- extract-sample-data.sql or used independently for data analysis tasks.
--
-- Functions included:
--   1. cia_tmp_rowcount - Get row count for a table/view
--   2. cia_classify_temporal_view - Classify temporal granularity
--   3. cia_percentile_sample - Sample rows at key percentiles
--   4. cia_generate_distribution_summary - Generate distribution statistics
--
-- Usage:
--   \i extract-sample-data-functions.sql
--
-- Version: 1.0.0
-- Last Updated: January 2026
-- ===========================================================================

\echo ''
\echo '=================================================='
\echo '=== Loading CIA Sample Data Helper Functions  ==='
\echo '=================================================='
\echo ''

-- ===========================================================================
-- Function: cia_tmp_rowcount
-- ===========================================================================
-- Purpose: Get row count for a table or view
-- Parameters:
--   p_schema: Schema name (usually 'public')
--   p_table: Table or view name
-- Returns: Row count as integer
-- ===========================================================================
DROP FUNCTION IF EXISTS cia_tmp_rowcount(text, text);
CREATE OR REPLACE FUNCTION cia_tmp_rowcount(p_schema text, p_table text)
RETURNS integer
LANGUAGE plpgsql
AS $$
DECLARE
    v_count integer;
BEGIN
    EXECUTE format('SELECT COUNT(*)::integer FROM %I.%I', p_schema, p_table) INTO v_count;
    RETURN v_count;
EXCEPTION WHEN OTHERS THEN
    RETURN 0;
END;
$$;

\echo '✓ Created function: cia_tmp_rowcount'

-- ===========================================================================
-- Function: cia_classify_temporal_view
-- ===========================================================================
-- Purpose: Classify temporal granularity of a view based on its name
-- Parameters:
--   p_viewname: View name to classify
-- Returns: Temporal classification (DAILY, WEEKLY, MONTHLY, ANNUAL, TREND, NON-TEMPORAL)
-- ===========================================================================
DROP FUNCTION IF EXISTS cia_classify_temporal_view(text);
CREATE OR REPLACE FUNCTION cia_classify_temporal_view(p_viewname text)
RETURNS text
LANGUAGE plpgsql
AS $$
BEGIN
    IF p_viewname ~ '_(daily|day)_' THEN
        RETURN 'DAILY';
    ELSIF p_viewname ~ '_(weekly|week)_' THEN
        RETURN 'WEEKLY';
    ELSIF p_viewname ~ '_(monthly|month)_' THEN
        RETURN 'MONTHLY';
    ELSIF p_viewname ~ '_(annual|yearly|year)_' THEN
        RETURN 'ANNUAL';
    ELSIF p_viewname ~ '_(trend|trajectory|evolution|proximity|seasonal|pattern)' THEN
        RETURN 'TREND';
    ELSE
        RETURN 'NON-TEMPORAL';
    END IF;
END;
$$;

\echo '✓ Created function: cia_classify_temporal_view'

-- ===========================================================================
-- Function: cia_percentile_sample
-- ===========================================================================
-- Purpose: Sample rows at key percentiles (P1, P10, P25, P50, P75, P90, P99)
--          for numerical columns to capture distribution shape
-- Parameters:
--   p_table_name: Table or view name to sample from
--   p_column_name: Numerical column to use for percentile calculation
--   p_order_by: Optional ORDER BY clause for tie-breaking (default: column_name)
-- Returns: Sampled rows representing key percentiles with labels
-- ===========================================================================
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

\echo '✓ Created function: cia_percentile_sample'

-- ===========================================================================
-- Function: cia_generate_distribution_summary
-- ===========================================================================
-- Purpose: Generate comprehensive distribution summary for all numerical columns
--          in a table/view, including min, max, percentiles, and distinct count
-- Parameters:
--   p_table_name: Table or view name to analyze
-- Returns: CSV-formatted distribution summary with column statistics
-- ===========================================================================
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
            
            -- Execute and return results
            RETURN QUERY EXECUTE v_sql;
            
        EXCEPTION WHEN OTHERS THEN
            -- Skip columns that cause errors (e.g., no data, type conversion issues)
            RAISE NOTICE 'Skipping column % in %: %', v_column_record.column_name, p_table_name, SQLERRM;
        END;
    END LOOP;
END;
$$;

\echo '✓ Created function: cia_generate_distribution_summary'

\echo ''
\echo '=== All helper functions loaded successfully ==='
\echo ''
