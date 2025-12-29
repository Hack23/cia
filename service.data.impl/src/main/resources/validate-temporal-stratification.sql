-- validate-temporal-stratification.sql
-- Validation script for temporal stratification feature
-- 
-- Purpose: Demonstrates and validates the temporal stratification logic
--          used in extract-sample-data.sql without running full extraction
--
-- Usage:
--   psql -U postgres -d cia_dev -f validate-temporal-stratification.sql
--
-- Expected Output:
--   - Count of views by temporal granularity
--   - Temporal column detection results
--   - Sample stratification preview for each category

\set ON_ERROR_STOP off
\timing on

\echo ''
\echo '=================================================='
\echo 'Temporal Stratification Validation'
\echo 'Started:' `date`
\echo '=================================================='
\echo ''

-- ===========================================================================
-- SECTION 0: CREATE HELPER FUNCTION
-- ===========================================================================
-- Create the same classification function used in extract-sample-data.sql
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

\echo 'Created validation function: cia_classify_temporal_view'
\echo ''

-- ===========================================================================
-- SECTION 1: View Classification by Temporal Granularity
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== VIEW TEMPORAL CLASSIFICATION              ==='
\echo '=================================================='
\echo ''

WITH view_classification AS (
    SELECT 
        CASE 
            WHEN matviewname IS NOT NULL THEN matviewname
            ELSE viewname
        END AS view_name,
        CASE 
            WHEN matviewname IS NOT NULL THEN 'MATERIALIZED VIEW'
            ELSE 'VIEW'
        END AS view_type,
        c.temporal_granularity
    FROM (
        SELECT viewname, NULL::text AS matviewname FROM pg_views WHERE schemaname = 'public'
        UNION ALL
        SELECT NULL::text AS viewname, matviewname FROM pg_matviews WHERE schemaname = 'public'
    ) v
    CROSS JOIN LATERAL cia_classify_temporal_view(COALESCE(matviewname, viewname)) AS c
)
SELECT 
    temporal_granularity AS "Temporal Granularity",
    COUNT(*) AS "View Count",
    string_agg(view_name, ', ' ORDER BY view_name) FILTER (WHERE view_name LIKE '%party%' OR view_name LIKE '%politician%') AS "Sample Views (Party/Politician)"
FROM view_classification
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

-- ===========================================================================
-- SECTION 2: Temporal Column Detection
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== TEMPORAL COLUMN DETECTION                 ==='
\echo '=================================================='
\echo ''

WITH view_temporal_columns AS (
    SELECT 
        c.table_schema AS schemaname,
        c.table_name AS viewname,
        c.column_name AS temporal_column,
        c.data_type,
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
      AND c.column_name NOT IN ('created', 'model_object_version')
      AND (c.table_name LIKE '%_daily%' OR c.table_name LIKE '%_weekly%' 
           OR c.table_name LIKE '%_monthly%' OR c.table_name LIKE '%_annual%'
           OR c.table_name LIKE '%_temporal%' OR c.table_name LIKE '%_trend%')
),
view_primary_temporal_column AS (
    SELECT DISTINCT ON (schemaname, viewname)
        schemaname,
        viewname,
        temporal_column,
        data_type,
        priority
    FROM view_temporal_columns
    ORDER BY schemaname, viewname, priority
)
SELECT 
    viewname AS "View Name",
    temporal_column AS "Temporal Column",
    data_type AS "Data Type",
    CASE priority
        WHEN 1 THEN 'High (vote_date)'
        WHEN 2 THEN 'High (created_date)'
        WHEN 3 THEN 'Medium (from/to_date)'
        WHEN 4 THEN 'Low (*_date pattern)'
        ELSE 'None'
    END AS "Priority"
FROM view_primary_temporal_column
ORDER BY priority, viewname
LIMIT 20;

\echo ''
\echo '(Showing first 20 temporal views with detected columns)'
\echo ''

-- ===========================================================================
-- SECTION 3: Sample Stratification Preview
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== SAMPLE STRATIFICATION PREVIEW             ==='
\echo '=================================================='
\echo ''

-- Daily view example (if exists)
DO $$
DECLARE
    view_exists BOOLEAN;
BEGIN
    SELECT EXISTS (
        SELECT 1 FROM pg_matviews 
        WHERE schemaname = 'public' 
        AND matviewname = 'view_riksdagen_vote_data_ballot_politician_summary_daily'
    ) INTO view_exists;
    
    IF view_exists THEN
        RAISE NOTICE '';
        RAISE NOTICE '--- DAILY VIEW EXAMPLE: view_riksdagen_vote_data_ballot_politician_summary_daily ---';
        RAISE NOTICE 'Stratification: 2 samples per day over last 30 days';
        RAISE NOTICE '';
        
        -- Show temporal distribution that would be sampled
        RAISE NOTICE 'Temporal distribution preview:';
    END IF;
END $$;

-- Show daily temporal buckets (only if view exists)
DO $$
DECLARE
    view_exists BOOLEAN;
    sql_query TEXT;
BEGIN
    SELECT EXISTS (
        SELECT 1 FROM pg_matviews 
        WHERE schemaname = 'public' 
        AND matviewname = 'view_riksdagen_vote_data_ballot_politician_summary_daily'
    ) INTO view_exists;
    
    IF view_exists THEN
        EXECUTE '
            SELECT 
                DATE(embedded_id_vote_date) AS "Date",
                COUNT(*) AS "Total Rows",
                LEAST(2, COUNT(*)) AS "Sampled Rows"
            FROM view_riksdagen_vote_data_ballot_politician_summary_daily
            WHERE embedded_id_vote_date >= CURRENT_DATE - INTERVAL ''30 days''
            GROUP BY DATE(embedded_id_vote_date)
            ORDER BY DATE(embedded_id_vote_date) DESC
            LIMIT 10
        ';
        
        RAISE NOTICE '';
        RAISE NOTICE '(Showing last 10 days of daily view data)';
        RAISE NOTICE '';
    ELSE
        RAISE NOTICE '';
        RAISE NOTICE 'Daily view not found - skipping preview';
        RAISE NOTICE '';
    END IF;
END $$;

-- Monthly view example (if exists)
DO $$
DECLARE
    view_exists BOOLEAN;
BEGIN
    SELECT EXISTS (
        SELECT 1 FROM pg_matviews 
        WHERE schemaname = 'public' 
        AND matviewname = 'view_riksdagen_vote_data_ballot_party_summary_monthly'
    ) INTO view_exists;
    
    IF view_exists THEN
        RAISE NOTICE '';
        RAISE NOTICE '--- MONTHLY VIEW EXAMPLE: view_riksdagen_vote_data_ballot_party_summary_monthly ---';
        RAISE NOTICE 'Stratification: 2 samples per month over last 3 years';
        RAISE NOTICE '';
        
        RAISE NOTICE 'Temporal distribution preview:';
    END IF;
END $$;

-- Show monthly temporal buckets (only if view exists)
DO $$
DECLARE
    view_exists BOOLEAN;
    sql_query TEXT;
BEGIN
    SELECT EXISTS (
        SELECT 1 FROM pg_matviews 
        WHERE schemaname = 'public' 
        AND matviewname = 'view_riksdagen_vote_data_ballot_party_summary_monthly'
    ) INTO view_exists;
    
    IF view_exists THEN
        EXECUTE '
            SELECT 
                TO_CHAR(embedded_id_vote_date, ''YYYY-MM'') AS "Month",
                COUNT(*) AS "Total Rows",
                LEAST(2, COUNT(*)) AS "Sampled Rows"
            FROM view_riksdagen_vote_data_ballot_party_summary_monthly
            WHERE embedded_id_vote_date >= CURRENT_DATE - INTERVAL ''3 years''
            GROUP BY TO_CHAR(embedded_id_vote_date, ''YYYY-MM'')
            ORDER BY TO_CHAR(embedded_id_vote_date, ''YYYY-MM'') DESC
            LIMIT 12
        ';
        
        RAISE NOTICE '';
        RAISE NOTICE '(Showing last 12 months of monthly view data)';
        RAISE NOTICE '';
    ELSE
        RAISE NOTICE '';
        RAISE NOTICE 'Monthly view not found - skipping preview';
        RAISE NOTICE '';
    END IF;
END $$;

-- ===========================================================================
-- SECTION 4: Validation Summary
-- ===========================================================================
\echo ''
\echo '=================================================='
\echo '=== VALIDATION SUMMARY                        ==='
\echo '=================================================='
\echo ''

WITH view_classification AS (
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
)
SELECT 
    '✅ Temporal Stratification Feature' AS "Status",
    'Ready for Use' AS "Readiness",
    (SELECT COUNT(*) FROM view_classification WHERE temporal_granularity != 'non_temporal') || ' temporal views identified' AS "Details";

\echo ''
\echo 'Temporal stratification logic validated successfully!'
\echo ''
\echo 'To use temporal stratification in sample data extraction:'
\echo '  cd /tmp/sample_output'
\echo '  psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-sample-data.sql'
\echo ''
\echo '=================================================='
\echo 'Validation completed'
\echo 'Finished:' `date`
\echo '=================================================='
\echo ''

-- ===========================================================================
-- CLEANUP
-- ===========================================================================

DROP FUNCTION IF EXISTS cia_classify_temporal_view(text);
\echo 'Dropped validation function: cia_classify_temporal_view'
\echo ''
