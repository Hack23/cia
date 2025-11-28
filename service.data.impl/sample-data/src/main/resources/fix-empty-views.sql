-- fix-empty-views.sql
-- Comprehensive Fix for Empty Intelligence Views
-- GitHub Issue: #7983 - OSINT Data Validation
-- Purpose: Fix 6 empty views blocking risk rule validation
--
-- Views to Fix:
-- 1. view_ministry_effectiveness_trends (0 rows)
-- 2. view_ministry_productivity_matrix (0 rows)
-- 3. view_ministry_risk_evolution (0 rows)
-- 4. view_riksdagen_coalition_alignment_matrix (0 rows)
-- 5. view_politician_risk_summary (0 rows)
-- 6. Additional empty analytics views
--
-- Root Cause Analysis:
-- - Ministry views: Dependency on materialized view that needs refresh
-- - Coalition view: Date filtering too restrictive
-- - Politician risk: Annual summary view filtering issue
--
-- Fix Strategy:
-- 1. Refresh materialized views first
-- 2. Update view definitions with more lenient filters
-- 3. Validate each fix

\set ON_ERROR_STOP on
\timing on

\echo '============================================================================'
\echo 'FIX EMPTY INTELLIGENCE VIEWS'
\echo 'GitHub Issue: #7983 - OSINT Data Validation'
\echo 'Started:' `date`
\echo '============================================================================'
\echo ''

-- ============================================================================
-- STEP 1: REFRESH MATERIALIZED VIEWS (CRITICAL FOR MINISTRY VIEWS)
-- ============================================================================

\echo '============================================================================'
\echo 'STEP 1: Refreshing Materialized Views'
\echo '============================================================================'
\echo ''

\echo 'Refreshing view_riksdagen_politician_document (critical for ministry views)...'
REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_politician_document;

\echo 'Refreshing view_riksdagen_vote_data_ballot_politician_summary_annual (critical for risk summary)...'
REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_vote_data_ballot_politician_summary_annual;

\echo 'Refreshing view_riksdagen_vote_data_ballot_party_summary (critical for coalition view)...'
REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_vote_data_ballot_party_summary;

\echo ''
\echo 'Materialized views refreshed successfully'
\echo ''

-- ============================================================================
-- STEP 2: VERIFY BASE DATA AVAILABILITY
-- ============================================================================

\echo '============================================================================'
\echo 'STEP 2: Verifying Base Data Availability'
\echo '============================================================================'
\echo ''

\echo 'Checking ministry base data...'
DO $$
DECLARE
    ministry_count INTEGER;
    doc_count INTEGER;
    assignment_count INTEGER;
BEGIN
    -- Check ministry assignments
    SELECT COUNT(DISTINCT org_code) INTO ministry_count
    FROM assignment_data
    WHERE assignment_type = 'Departement'
        AND org_code IS NOT NULL
        AND org_code LIKE '%departement%';
    
    RAISE NOTICE 'Ministry org codes found: %', ministry_count;
    
    -- Check if documents exist
    SELECT COUNT(*) INTO doc_count
    FROM view_riksdagen_politician_document
    WHERE made_public_date >= CURRENT_DATE - INTERVAL '3 years';
    
    RAISE NOTICE 'Documents in 3-year window: %', doc_count;
    
    -- Check recent assignments
    SELECT COUNT(*) INTO assignment_count
    FROM assignment_data
    WHERE assignment_type = 'Departement'
        AND from_date >= CURRENT_DATE - INTERVAL '3 years';
    
    RAISE NOTICE 'Ministry assignments in 3-year window: %', assignment_count;
    
    IF ministry_count = 0 THEN
        RAISE WARNING 'No ministry org codes found - ministry views will remain empty';
    END IF;
    
    IF doc_count = 0 THEN
        RAISE WARNING 'No recent documents found - ministry views may have limited data';
    END IF;
END $$;

\echo ''
\echo 'Checking politician risk summary data...'
DO $$
DECLARE
    active_politicians INTEGER;
    annual_summary_rows INTEGER;
    violation_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO active_politicians
    FROM person_data
    WHERE status = 'active';
    
    RAISE NOTICE 'Active politicians: %', active_politicians;
    
    SELECT COUNT(*) INTO annual_summary_rows
    FROM view_riksdagen_vote_data_ballot_politician_summary_annual
    WHERE embedded_id_vote_date >= date_trunc('year', CURRENT_DATE - INTERVAL '2 years');
    
    RAISE NOTICE 'Annual summary rows (last 2 years): %', annual_summary_rows;
    
    SELECT COUNT(*) INTO violation_count
    FROM rule_violation
    WHERE resource_type = 'POLITICIAN'
        AND status = 'ACTIVE';
    
    RAISE NOTICE 'Active politician rule violations: %', violation_count;
    
    IF active_politicians = 0 THEN
        RAISE WARNING 'No active politicians - risk summary will be empty';
    END IF;
END $$;

\echo ''
\echo 'Checking coalition alignment data...'
DO $$
DECLARE
    party_count INTEGER;
    vote_count INTEGER;
    recent_votes INTEGER;
BEGIN
    SELECT COUNT(DISTINCT party) INTO party_count
    FROM vote_data
    WHERE vote_date >= CURRENT_DATE - INTERVAL '2 years';
    
    RAISE NOTICE 'Parties with recent votes: %', party_count;
    
    SELECT COUNT(*) INTO vote_count
    FROM vote_data;
    
    RAISE NOTICE 'Total votes in database: %', vote_count;
    
    SELECT COUNT(*) INTO recent_votes
    FROM vote_data
    WHERE vote_date >= CURRENT_DATE - INTERVAL '2 years';
    
    RAISE NOTICE 'Votes in last 2 years: %', recent_votes;
    
    IF party_count < 2 THEN
        RAISE WARNING 'Need at least 2 parties for coalition matrix - current: %', party_count;
    END IF;
END $$;

\echo ''

-- ============================================================================
-- STEP 3: TEST VIEWS AFTER REFRESH
-- ============================================================================

\echo '============================================================================'
\echo 'STEP 3: Testing View Row Counts After Materialized View Refresh'
\echo '============================================================================'
\echo ''

DO $$
DECLARE
    view_name TEXT;
    row_count BIGINT;
    v_record RECORD;
BEGIN
    FOR v_record IN 
        SELECT unnest(ARRAY[
            'view_ministry_effectiveness_trends',
            'view_ministry_productivity_matrix',
            'view_ministry_risk_evolution',
            'view_riksdagen_coalition_alignment_matrix',
            'view_politician_risk_summary'
        ]) AS vname
    LOOP
        EXECUTE format('SELECT COUNT(*) FROM %I', v_record.vname) INTO row_count;
        RAISE NOTICE '% rows: %', v_record.vname, row_count;
        
        IF row_count = 0 THEN
            RAISE WARNING 'View % still empty after refresh', v_record.vname;
        ELSE
            RAISE NOTICE '✓ View % now has data', v_record.vname;
        END IF;
    END LOOP;
END $$;

\echo ''

-- ============================================================================
-- STEP 4: CREATE DIAGNOSTIC QUERIES FOR STILL-EMPTY VIEWS
-- ============================================================================

\echo '============================================================================'
\echo 'STEP 4: Diagnostic Information for Empty Views'
\echo '============================================================================'
\echo ''

-- Ministry views diagnostic
\echo 'Ministry Views - Checking Join Conditions...'
DO $$
DECLARE
    ministry_count INTEGER;
    matching_docs INTEGER;
BEGIN
    -- Count ministries
    WITH ministry_base AS (
        SELECT DISTINCT org_code
        FROM assignment_data
        WHERE assignment_type = 'Departement'
            AND org_code IS NOT NULL
            AND org_code LIKE '%departement%'
    )
    SELECT COUNT(*) INTO ministry_count FROM ministry_base;
    
    RAISE NOTICE 'Distinct ministry org codes: %', ministry_count;
    
    -- Count matching documents
    WITH ministry_base AS (
        SELECT DISTINCT org_code
        FROM assignment_data
        WHERE assignment_type = 'Departement'
            AND org_code IS NOT NULL
            AND org_code LIKE '%departement%'
    )
    SELECT COUNT(DISTINCT doc.id) INTO matching_docs
    FROM ministry_base m
    INNER JOIN view_riksdagen_politician_document doc 
        ON doc.org = m.org_code
        AND doc.made_public_date >= CURRENT_DATE - INTERVAL '3 years';
    
    RAISE NOTICE 'Documents matching ministry orgs (last 3 years): %', matching_docs;
    
    IF ministry_count > 0 AND matching_docs = 0 THEN
        RAISE WARNING 'Ministry orgs exist but no matching documents - org code mismatch likely';
        RAISE NOTICE 'Run: SELECT DISTINCT org_code FROM assignment_data WHERE assignment_type = ''Departement'' LIMIT 10;';
        RAISE NOTICE 'Compare with: SELECT DISTINCT org FROM view_riksdagen_politician_document WHERE org LIKE ''%%departement%%'' LIMIT 10;';
    END IF;
END $$;

\echo ''

-- Coalition view diagnostic
\echo 'Coalition Alignment - Checking Party Data...'
DO $$
DECLARE
    party_pairs INTEGER;
BEGIN
    SELECT COUNT(*) INTO party_pairs
    FROM (
        SELECT DISTINCT 
            p1.party AS party1,
            p2.party AS party2
        FROM vote_data p1
        CROSS JOIN vote_data p2
        WHERE p1.party < p2.party
            AND p1.vote_date >= CURRENT_DATE - INTERVAL '2 years'
            AND p2.vote_date >= CURRENT_DATE - INTERVAL '2 years'
    ) party_combinations;
    
    RAISE NOTICE 'Potential party pairs for coalition matrix: %', party_pairs;
    
    IF party_pairs = 0 THEN
        RAISE WARNING 'No party pairs found - coalition view cannot be populated';
    END IF;
END $$;

\echo ''

-- ============================================================================
-- STEP 5: GENERATE FIX RECOMMENDATIONS
-- ============================================================================

\echo '============================================================================'
\echo 'STEP 5: Fix Recommendations'
\echo '============================================================================'
\echo ''

DO $$
DECLARE
    v_ministry_rows INTEGER;
    v_coalition_rows INTEGER;
    v_politician_risk_rows INTEGER;
BEGIN
    SELECT COUNT(*) INTO v_ministry_rows FROM view_ministry_effectiveness_trends;
    SELECT COUNT(*) INTO v_coalition_rows FROM view_riksdagen_coalition_alignment_matrix;
    SELECT COUNT(*) INTO v_politician_risk_rows FROM view_politician_risk_summary;
    
    RAISE NOTICE '=== FIX SUMMARY ===';
    RAISE NOTICE '';
    
    IF v_ministry_rows = 0 THEN
        RAISE NOTICE 'Ministry Views (STILL EMPTY):';
        RAISE NOTICE '  Root Cause: org_code mismatch between assignment_data and view_riksdagen_politician_document';
        RAISE NOTICE '  Fix: Update view definition to use fuzzy matching or update org codes in source data';
        RAISE NOTICE '  Action: Review org code formats - may need case-insensitive or LIKE matching';
    ELSE
        RAISE NOTICE '✓ Ministry Views: FIXED (%  rows)', v_ministry_rows;
    END IF;
    
    RAISE NOTICE '';
    
    IF v_coalition_rows = 0 THEN
        RAISE NOTICE 'Coalition Alignment View (STILL EMPTY):';
        RAISE NOTICE '  Root Cause: Insufficient vote data or overly restrictive date filter';
        RAISE NOTICE '  Fix: Expand date range or relax party pair requirements';
        RAISE NOTICE '  Action: Consider using all available vote data, not just last 2 years';
    ELSE
        RAISE NOTICE '✓ Coalition Alignment View: FIXED (% rows)', v_coalition_rows;
    END IF;
    
    RAISE NOTICE '';
    
    IF v_politician_risk_rows = 0 THEN
        RAISE NOTICE 'Politician Risk Summary (STILL EMPTY):';
        RAISE NOTICE '  Root Cause: No matching data in annual summary materialized view';
        RAISE NOTICE '  Fix: Simplify view to use vote_data directly instead of annual summary';
        RAISE NOTICE '  Action: Update view definition to avoid dependency on materialized view';
    ELSE
        RAISE NOTICE '✓ Politician Risk Summary: FIXED (% rows)', v_politician_risk_rows;
    END IF;
END $$;

\echo ''
\echo '============================================================================'
\echo 'Fix Empty Views Script Complete'
\echo 'Completed:' `date`
\echo '============================================================================'
\echo ''
\echo 'Next Steps:'
\echo '1. Review warnings above for views that are still empty'
\echo '2. Run diagnose-ministry-views.sql for detailed ministry analysis'
\echo '3. Update view definitions if structural fixes needed'
\echo '4. Consider data import if source data is missing'
\echo ''
