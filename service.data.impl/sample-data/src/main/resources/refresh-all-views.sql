-- refresh-all-views.sql
-- Materialized View Refresh Script with Validation and Error Handling
-- 
-- Usage:
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/refresh-all-views.sql
--
-- Description:
--   Refreshes all materialized views in correct dependency order with:
--   - Validation that all views exist before attempting refresh
--   - Error handling to continue on individual failures
--   - Timing information for each view refresh
--   - Progress logging and summary report

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
    v_views TEXT[] := ARRAY[
        'view_worldbank_indicator_data_country_summary',
        'view_riksdagen_politician_document',
        'view_riksdagen_org_document_daily_summary',
        'view_riksdagen_document_type_daily_summary',
        'view_riksdagen_committee_decisions',
        'view_riksdagen_vote_data_ballot_summary',
        'view_riksdagen_vote_data_ballot_summary_daily',
        'view_riksdagen_committee_ballot_decision_summary',
        'view_riksdagen_vote_data_ballot_party_summary',
        'view_riksdagen_vote_data_ballot_party_summary_daily',
        'view_riksdagen_vote_data_ballot_party_summary_monthly',
        'view_riksdagen_vote_data_ballot_party_summary_weekly',
        'view_riksdagen_vote_data_ballot_party_summary_annual',
        'view_riksdagen_vote_data_ballot_summary_annual',
        'view_riksdagen_vote_data_ballot_summary_monthly',
        'view_riksdagen_vote_data_ballot_summary_weekly',
        'view_riksdagen_vote_data_ballot_politician_summary',
        'view_riksdagen_vote_data_ballot_politician_summary_daily',
        'view_riksdagen_vote_data_ballot_politician_summary_annual',
        'view_riksdagen_vote_data_ballot_politician_summary_monthly',
        'view_riksdagen_vote_data_ballot_politician_summary_weekly',
        'view_riksdagen_committee_ballot_decision_party_summary',
        'view_riksdagen_committee_ballot_decision_politician_summary',
        'view_riksdagen_committee_decision_type_org_summary',
        'view_riksdagen_committee_decision_type_summary',
        'view_riksdagen_party_document_daily_summary',
        'view_riksdagen_politician_document_daily_summary',
        'view_riksdagen_politician_document_summary'
    ];
    v_view TEXT;
    v_exists BOOLEAN;
    v_missing INT := 0;
BEGIN
    FOREACH v_view IN ARRAY v_views
    LOOP
        SELECT EXISTS (
            SELECT 1 FROM pg_matviews 
            WHERE schemaname = 'public' AND matviewname = v_view
        ) INTO v_exists;
        
        IF NOT v_exists THEN
            RAISE WARNING 'Materialized view does not exist: %', v_view;
            v_missing := v_missing + 1;
        ELSE
            RAISE NOTICE '✓ View exists: %', v_view;
        END IF;
    END LOOP;
    
    IF v_missing > 0 THEN
        RAISE EXCEPTION 'Validation failed: % materialized views missing', v_missing;
    ELSE
        RAISE NOTICE 'Validation passed: All % views exist', array_length(v_views, 1);
    END IF;
END $$;

-- ===========================================================================
-- PHASE 2: REFRESH - Refresh materialized views with error handling
-- ===========================================================================

\echo ''
\echo '--- REFRESH PHASE ---'
\echo 'Refreshing materialized views in dependency order...'
\echo ''

DO $$
DECLARE
    v_views TEXT[] := ARRAY[
        -- TIER 1: Base materialized views (no dependencies on other materialized views)
        'view_worldbank_indicator_data_country_summary',
        'view_riksdagen_politician_document',
        'view_riksdagen_org_document_daily_summary',
        'view_riksdagen_document_type_daily_summary',
        'view_riksdagen_committee_decisions',
        'view_riksdagen_vote_data_ballot_summary',
        'view_riksdagen_committee_ballot_decision_summary',
        'view_riksdagen_vote_data_ballot_party_summary',
        'view_riksdagen_vote_data_ballot_politician_summary',
        -- TIER 2: Daily/summary views that depend on base materialized views
        'view_riksdagen_vote_data_ballot_summary_daily',
        'view_riksdagen_vote_data_ballot_summary_weekly',
        'view_riksdagen_vote_data_ballot_summary_monthly',
        'view_riksdagen_vote_data_ballot_summary_annual',
        'view_riksdagen_vote_data_ballot_party_summary_daily',
        'view_riksdagen_vote_data_ballot_party_summary_weekly',
        'view_riksdagen_vote_data_ballot_party_summary_monthly',
        'view_riksdagen_vote_data_ballot_party_summary_annual',
        'view_riksdagen_vote_data_ballot_politician_summary_daily',
        'view_riksdagen_vote_data_ballot_politician_summary_weekly',
        'view_riksdagen_vote_data_ballot_politician_summary_monthly',
        'view_riksdagen_vote_data_ballot_politician_summary_annual',
        'view_riksdagen_committee_ballot_decision_party_summary',
        'view_riksdagen_committee_ballot_decision_politician_summary',
        'view_riksdagen_committee_decision_type_org_summary',
        'view_riksdagen_committee_decision_type_summary',
        'view_riksdagen_party_document_daily_summary',
        'view_riksdagen_politician_document_daily_summary',
        'view_riksdagen_politician_document_summary'
    ];
    v_view TEXT;
    v_success INT := 0;
    v_failed INT := 0;
    v_start TIMESTAMP;
    v_duration INTERVAL;
BEGIN
    FOREACH v_view IN ARRAY v_views
    LOOP
        BEGIN
            v_start := clock_timestamp();
            RAISE NOTICE 'Refreshing: %...', v_view;
            
            EXECUTE format('REFRESH MATERIALIZED VIEW %I', v_view);
            
            v_duration := clock_timestamp() - v_start;
            RAISE NOTICE '✓ Refreshed % in %', v_view, v_duration;
            v_success := v_success + 1;
        EXCEPTION WHEN OTHERS THEN
            RAISE WARNING '✗ Failed to refresh %: %', v_view, SQLERRM;
            v_failed := v_failed + 1;
        END;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE '===========================================';
    RAISE NOTICE '--- REFRESH SUMMARY ---';
    RAISE NOTICE '===========================================';
    RAISE NOTICE 'Total views: %', array_length(v_views, 1);
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



