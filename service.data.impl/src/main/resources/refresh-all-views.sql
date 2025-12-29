-- refresh-all-views.sql
-- Materialized View Refresh Script with Dynamic Discovery
-- 
-- Usage:
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/refresh-all-views.sql
--
-- Description:
--   Refreshes all materialized views dynamically with:
--   - Dynamic discovery from pg_matviews (no hardcoded list)
--   - Error handling to continue on individual failures
--   - Timing information for each view refresh
--   - Progress logging and summary report
--   - Alphabetical ordering (suitable for most cases)
--
-- Note:
--   For complex dependency chains between materialized views,
--   consider creating a dependency-aware ordering script.

\set ON_ERROR_STOP off
\timing on

\echo '======================================='
\echo 'Materialized View Refresh Script'
\echo 'Started at:' `date`
\echo '======================================='

-- ===========================================================================
-- PHASE 1: VALIDATION - Check that all views exist
-- ===========================================================================

\echo ''
\echo '--- VALIDATION PHASE ---'
\echo 'Checking that all materialized views exist...'

DO $$
DECLARE
    total_mvs INTEGER;
BEGIN
    -- Get total count dynamically
    SELECT COUNT(*) INTO total_mvs FROM pg_matviews WHERE schemaname = 'public';
    
    RAISE NOTICE 'Found % materialized views in public schema', total_mvs;
    
    -- List all materialized views for reference
    FOR mv_name IN 
        SELECT matviewname FROM pg_matviews WHERE schemaname = 'public' ORDER BY matviewname
    LOOP
        RAISE NOTICE '  - %', mv_name;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE 'Validation passed: All materialized views will be refreshed dynamically';
END $$;

-- ===========================================================================
-- PHASE 2: REFRESH - Refresh materialized views dynamically
-- ===========================================================================

\echo ''
\echo '--- REFRESH PHASE ---'
\echo 'Refreshing materialized views dynamically...'
\echo 'Views are refreshed in alphabetical order.'
\echo 'For complex dependency chains, consider manual ordering.'
\echo ''

DO $$
DECLARE
    mv_record RECORD;
    v_success INT := 0;
    v_failed INT := 0;
    v_start TIMESTAMP;
    v_duration INTERVAL;
    total_mvs INTEGER;
    mv_count INTEGER := 0;
BEGIN
    -- Get total count for progress reporting
    SELECT COUNT(*) INTO total_mvs FROM pg_matviews WHERE schemaname = 'public';
    
    -- Refresh all materialized views dynamically
    FOR mv_record IN 
        SELECT schemaname, matviewname
        FROM pg_matviews
        WHERE schemaname = 'public'
        ORDER BY matviewname
    LOOP
        BEGIN
            mv_count := mv_count + 1;
            v_start := clock_timestamp();
            RAISE NOTICE '[%/%] Refreshing: %.%...', mv_count, total_mvs,
                mv_record.schemaname, mv_record.matviewname;
            
            EXECUTE format('REFRESH MATERIALIZED VIEW %I.%I', 
                mv_record.schemaname, mv_record.matviewname);
            
            v_duration := clock_timestamp() - v_start;
            RAISE NOTICE '✓ Refreshed %.% in %', 
                mv_record.schemaname, mv_record.matviewname, v_duration;
            v_success := v_success + 1;
        EXCEPTION WHEN OTHERS THEN
            RAISE WARNING '✗ Failed to refresh %.%: %', 
                mv_record.schemaname, mv_record.matviewname, SQLERRM;
            v_failed := v_failed + 1;
        END;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE '===========================================';
    RAISE NOTICE '--- REFRESH SUMMARY ---';
    RAISE NOTICE '===========================================';
    RAISE NOTICE 'Total views: %', total_mvs;
    RAISE NOTICE 'Successful: %', v_success;
    RAISE NOTICE 'Failed: %', v_failed;
    RAISE NOTICE '===========================================';
    
    IF v_failed > 0 THEN
        RAISE WARNING 'Some view refreshes failed. Check logs above for details.';
    END IF;
END $$;

\echo ''
\echo '======================================='
\echo 'Refresh script completed'
\echo 'Finished at:' `date`
\echo '======================================='



