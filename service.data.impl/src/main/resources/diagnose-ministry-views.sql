-- Diagnostic queries for empty ministry and government views
-- GitHub Issue: #7884
-- Purpose: Identify root causes for empty views

-- ============================================================================
-- 1. Check document_type values in document_data
-- ============================================================================
SELECT '1. Document Types in document_data' AS query_name;
SELECT 
    document_type,
    COUNT(*) AS count,
    MIN(made_public_date) AS earliest,
    MAX(made_public_date) AS latest
FROM document_data
WHERE document_type IS NOT NULL
GROUP BY document_type
ORDER BY count DESC;

-- ============================================================================
-- 2. Check for government proposals with different case variations
-- ============================================================================
SELECT '2. Government Proposals - Case Variations' AS query_name;
SELECT 
    'UPPER(PROP)' AS filter_type,
    COUNT(*) AS row_count
FROM document_data
WHERE UPPER(document_type) = 'PROP'
UNION ALL
SELECT 
    'lowercase(prop)',
    COUNT(*)
FROM document_data
WHERE LOWER(document_type) = 'prop'
UNION ALL
SELECT 
    'exact(PROP)',
    COUNT(*)
FROM document_data
WHERE document_type = 'PROP'
UNION ALL
SELECT 
    'exact(prop)',
    COUNT(*)
FROM document_data
WHERE document_type = 'prop'
UNION ALL
SELECT 
    'exact(Proposition)',
    COUNT(*)
FROM document_data
WHERE document_type = 'Proposition';

-- ============================================================================
-- 3. Check assignment_data for ministry org_codes
-- ============================================================================
SELECT '3. Ministry Org Codes in assignment_data' AS query_name;
SELECT 
    CASE 
        WHEN LOWER(org_code) LIKE '%departement%' THEN 'Has departement (any case)'
        WHEN LOWER(org_code) LIKE '%department%' THEN 'Has department (eng)'
        ELSE 'Other'
    END AS org_type,
    COUNT(DISTINCT org_code) AS unique_org_codes,
    COUNT(*) AS total_assignments,
    MIN(from_date) AS earliest_assignment,
    MAX(COALESCE(to_date, CURRENT_DATE)) AS latest_assignment
FROM assignment_data
WHERE assignment_type = 'Departement'
GROUP BY org_type
ORDER BY total_assignments DESC;

-- Show sample ministry org codes
SELECT '3b. Sample Ministry Org Codes' AS query_name;
SELECT DISTINCT 
    org_code,
    detail AS name,
    COUNT(*) AS assignment_count
FROM assignment_data
WHERE assignment_type = 'Departement'
    AND org_code IS NOT NULL
    AND LOWER(org_code) LIKE '%departement%'
GROUP BY org_code, detail
ORDER BY assignment_count DESC
LIMIT 20;

-- ============================================================================
-- 4. Check view_riksdagen_politician_document for ministry documents
-- ============================================================================
SELECT '4. Ministry Documents in view_riksdagen_politician_document' AS query_name;
SELECT 
    CASE 
        WHEN LOWER(org) LIKE '%departement%' THEN 'Has departement'
        WHEN LOWER(org) LIKE '%department%' THEN 'Has department (eng)'
        WHEN org IS NULL THEN 'NULL org'
        ELSE 'Other org'
    END AS org_category,
    COUNT(*) AS document_count,
    COUNT(DISTINCT org) AS unique_orgs,
    MIN(made_public_date) AS earliest_date,
    MAX(made_public_date) AS latest_date
FROM view_riksdagen_politician_document
GROUP BY org_category
ORDER BY document_count DESC;

-- Show sample ministry orgs in politician document view
SELECT '4b. Sample Orgs in view_riksdagen_politician_document' AS query_name;
SELECT 
    org,
    document_type,
    COUNT(*) AS doc_count,
    MIN(made_public_date) AS earliest,
    MAX(made_public_date) AS latest
FROM view_riksdagen_politician_document
WHERE LOWER(org) LIKE '%departement%'
    OR LOWER(org) LIKE '%regeringskansliet%'
GROUP BY org, document_type
ORDER BY doc_count DESC
LIMIT 20;

-- ============================================================================
-- 5. Test ministry effectiveness trends query with data
-- ============================================================================
SELECT '5. Test Ministry Base CTE' AS query_name;
WITH ministry_base AS (
    SELECT DISTINCT
        org_code,
        SUBSTRING(org_code FROM 1 FOR position('departement' IN org_code) + 10) AS short_code,
        detail AS name
    FROM assignment_data
    WHERE assignment_type = 'Departement'
        AND org_code IS NOT NULL
        AND org_code LIKE '%departement%'
)
SELECT 
    COUNT(*) AS ministry_count,
    STRING_AGG(DISTINCT org_code, ', ' ORDER BY org_code) AS sample_codes
FROM ministry_base;

-- ============================================================================
-- 6. Test ministry documents with current view join logic
-- ============================================================================
SELECT '6. Test Ministry Document Join' AS query_name;
WITH ministry_base AS (
    SELECT DISTINCT
        org_code,
        detail AS name
    FROM assignment_data
    WHERE assignment_type = 'Departement'
        AND org_code IS NOT NULL
        AND LOWER(org_code) LIKE '%departement%'
)
SELECT 
    m.org_code,
    m.name,
    COUNT(DISTINCT doc.id) AS document_count,
    MIN(doc.made_public_date) AS earliest_doc,
    MAX(doc.made_public_date) AS latest_doc
FROM ministry_base m
LEFT JOIN view_riksdagen_politician_document doc 
    ON doc.org = m.org_code
    AND doc.made_public_date >= CURRENT_DATE - INTERVAL '3 years'
GROUP BY m.org_code, m.name
ORDER BY document_count DESC;

-- ============================================================================
-- 7. Check if org codes match between tables
-- ============================================================================
SELECT '7. Org Code Matching Analysis' AS query_name;
SELECT 
    'In assignment_data only' AS location,
    COUNT(DISTINCT ad.org_code) AS count
FROM assignment_data ad
WHERE ad.assignment_type = 'Departement'
    AND LOWER(ad.org_code) LIKE '%departement%'
    AND NOT EXISTS (
        SELECT 1 FROM view_riksdagen_politician_document vpd
        WHERE vpd.org = ad.org_code
    )
UNION ALL
SELECT 
    'In politician_document only',
    COUNT(DISTINCT vpd.org)
FROM view_riksdagen_politician_document vpd
WHERE LOWER(vpd.org) LIKE '%departement%'
    AND NOT EXISTS (
        SELECT 1 FROM assignment_data ad
        WHERE ad.org_code = vpd.org
            AND ad.assignment_type = 'Departement'
    )
UNION ALL
SELECT 
    'In both (matching)',
    COUNT(DISTINCT ad.org_code)
FROM assignment_data ad
INNER JOIN view_riksdagen_politician_document vpd
    ON vpd.org = ad.org_code
WHERE ad.assignment_type = 'Departement'
    AND LOWER(ad.org_code) LIKE '%departement%';

-- ============================================================================
-- 8. Summary and Recommendations
-- ============================================================================
SELECT '8. Summary - Current View Row Counts' AS query_name;
SELECT 
    'view_ministry_effectiveness_trends' AS view_name,
    (SELECT COUNT(*) FROM view_ministry_effectiveness_trends) AS row_count
UNION ALL
SELECT 
    'view_ministry_productivity_matrix',
    (SELECT COUNT(*) FROM view_ministry_productivity_matrix)
UNION ALL
SELECT 
    'view_ministry_risk_evolution',
    (SELECT COUNT(*) FROM view_ministry_risk_evolution)
UNION ALL
SELECT 
    'view_riksdagen_goverment_proposals',
    (SELECT COUNT(*) FROM view_riksdagen_goverment_proposals);

-- ============================================================================
-- End of diagnostic queries
-- ============================================================================
SELECT 'Diagnostic queries completed' AS status;
