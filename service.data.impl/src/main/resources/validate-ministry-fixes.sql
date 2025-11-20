-- Validation queries for v1.32 ministry and government view fixes
-- GitHub Issue: #7884
-- Purpose: Verify that fixes work correctly

\echo '============================================================================'
\echo 'VALIDATION: v1.32 Ministry and Government View Fixes'
\echo 'GitHub Issue: #7884'
\echo '============================================================================'
\echo ''

-- ============================================================================
-- 1. Test government proposals view (FIXED in v1.32)
-- ============================================================================
\echo '1. Testing view_riksdagen_goverment_proposals...'
\echo 'Expected: Should return >0 rows if government proposals exist'
\echo ''

SELECT 
    COUNT(*) AS total_proposals,
    MIN(made_public_date) AS earliest_proposal,
    MAX(made_public_date) AS latest_proposal,
    COUNT(DISTINCT org) AS unique_organizations
FROM view_riksdagen_goverment_proposals;

\echo ''
\echo 'Status: If total_proposals > 0, view is WORKING'
\echo 'Status: If total_proposals = 0, check document_data for PROP/prop documents'
\echo ''

-- ============================================================================
-- 2. Test ministry effectiveness trends view
-- ============================================================================
\echo '2. Testing view_ministry_effectiveness_trends...'
\echo 'Expected: Should return >0 rows if ministry data exists'
\echo ''

SELECT 
    COUNT(*) AS total_records,
    COUNT(DISTINCT org_code) AS unique_ministries,
    MIN(period_start) AS earliest_period,
    MAX(period_start) AS latest_period,
    SUM(documents_produced) AS total_documents
FROM view_ministry_effectiveness_trends;

\echo ''
\echo 'Status: If total_records > 0, view is WORKING'
\echo 'Status: If total_records = 0, run diagnose-ministry-views.sql'
\echo ''

-- ============================================================================
-- 3. Test ministry productivity matrix view
-- ============================================================================
\echo '3. Testing view_ministry_productivity_matrix...'
\echo 'Expected: Should return >0 rows if ministry annual data exists'
\echo ''

SELECT 
    COUNT(*) AS total_records,
    COUNT(DISTINCT org_code) AS unique_ministries,
    MIN(year) AS earliest_year,
    MAX(year) AS latest_year,
    SUM(documents_produced) AS total_documents
FROM view_ministry_productivity_matrix;

\echo ''
\echo 'Status: If total_records > 0, view is WORKING'
\echo 'Status: If total_records = 0, run diagnose-ministry-views.sql'
\echo ''

-- ============================================================================
-- 4. Test ministry risk evolution view
-- ============================================================================
\echo '4. Testing view_ministry_risk_evolution...'
\echo 'Expected: Should return >0 rows if ministry quarterly data exists'
\echo ''

SELECT 
    COUNT(*) AS total_records,
    COUNT(DISTINCT org_code) AS unique_ministries,
    MIN(assessment_period) AS earliest_period,
    MAX(assessment_period) AS latest_period,
    AVG(risk_score) AS avg_risk_score,
    COUNT(*) FILTER (WHERE risk_severity IN ('HIGH', 'CRITICAL')) AS high_risk_count
FROM view_ministry_risk_evolution;

\echo ''
\echo 'Status: If total_records > 0, view is WORKING'
\echo 'Status: If total_records = 0, run diagnose-ministry-views.sql'
\echo ''

-- ============================================================================
-- 5. Summary of all 4 views
-- ============================================================================
\echo '============================================================================'
\echo 'SUMMARY: View Status After v1.32 Fixes'
\echo '============================================================================'
\echo ''

SELECT 
    'view_riksdagen_goverment_proposals' AS view_name,
    (SELECT COUNT(*) FROM view_riksdagen_goverment_proposals) AS row_count,
    CASE 
        WHEN (SELECT COUNT(*) FROM view_riksdagen_goverment_proposals) > 0 
        THEN '✅ WORKING' 
        ELSE '❌ EMPTY (check document_data)' 
    END AS status
UNION ALL
SELECT 
    'view_ministry_effectiveness_trends',
    (SELECT COUNT(*) FROM view_ministry_effectiveness_trends),
    CASE 
        WHEN (SELECT COUNT(*) FROM view_ministry_effectiveness_trends) > 0 
        THEN '✅ WORKING' 
        ELSE '⚠️  EMPTY (data dependency - see diagnostics)' 
    END
UNION ALL
SELECT 
    'view_ministry_productivity_matrix',
    (SELECT COUNT(*) FROM view_ministry_productivity_matrix),
    CASE 
        WHEN (SELECT COUNT(*) FROM view_ministry_productivity_matrix) > 0 
        THEN '✅ WORKING' 
        ELSE '⚠️  EMPTY (data dependency - see diagnostics)' 
    END
UNION ALL
SELECT 
    'view_ministry_risk_evolution',
    (SELECT COUNT(*) FROM view_ministry_risk_evolution),
    CASE 
        WHEN (SELECT COUNT(*) FROM view_ministry_risk_evolution) > 0 
        THEN '✅ WORKING' 
        ELSE '⚠️  EMPTY (data dependency - see diagnostics)' 
    END
ORDER BY view_name;

\echo ''
\echo '============================================================================'
\echo 'Next Steps:'
\echo '============================================================================'
\echo ''
\echo 'If any ministry views show EMPTY:'
\echo '1. Run: psql -U cia_user -d cia -f diagnose-ministry-views.sql'
\echo '2. Check diagnostic output for root cause'
\echo '3. Review TROUBLESHOOTING_EMPTY_VIEWS.md ministry section'
\echo '4. Verify data sources:'
\echo '   - assignment_data has ministry assignments'
\echo '   - view_riksdagen_politician_document is refreshed'
\echo '   - Documents exist in 3-year window'
\echo ''
\echo 'If government proposals view shows EMPTY:'
\echo '1. Check: SELECT COUNT(*) FROM document_data WHERE UPPER(document_type) LIKE ''%PROP%'';'
\echo '2. If 0, import government proposal documents from Riksdagen API'
\echo '3. If >0, verify view definition includes case-insensitive filter'
\echo ''
\echo '============================================================================'
\echo 'Validation Complete'
\echo '============================================================================'
