-- Verification Queries for Political Role Taxonomy (db-changelog-1.46)
-- Purpose: Verify role_code_taxonomy table structure and data integrity
-- Date: 2025-12-21

\echo '===================================================='
\echo 'Political Role Taxonomy Verification'
\echo '===================================================='
\echo ''

-- 1. Verify table exists and has correct structure
\echo '1. Verifying table structure...'
\d role_code_taxonomy

\echo ''
\echo '2. Count all roles by category...'
SELECT 
    role_category,
    COUNT(*) as role_count,
    MIN(authority_level) as min_authority,
    MAX(authority_level) as max_authority,
    MIN(experience_weight) as min_weight,
    MAX(experience_weight) as max_weight
FROM role_code_taxonomy
GROUP BY role_category
ORDER BY MAX(authority_level) DESC, MAX(experience_weight) DESC;

\echo ''
\echo '3. Total role count (should be 76)...'
SELECT COUNT(*) as total_roles FROM role_code_taxonomy;

\echo ''
\echo '4. Verify no NULL values in required fields...'
SELECT 
    COUNT(*) FILTER (WHERE role_code IS NULL) as null_role_code,
    COUNT(*) FILTER (WHERE role_name_sv IS NULL) as null_name_sv,
    COUNT(*) FILTER (WHERE role_name_en IS NULL) as null_name_en,
    COUNT(*) FILTER (WHERE role_category IS NULL) as null_category,
    COUNT(*) FILTER (WHERE authority_level IS NULL) as null_authority,
    COUNT(*) FILTER (WHERE experience_weight IS NULL) as null_weight
FROM role_code_taxonomy;

\echo ''
\echo '5. Top 10 roles by authority and experience weight...'
SELECT 
    role_code,
    role_name_en,
    role_category,
    authority_level,
    experience_weight
FROM role_code_taxonomy
ORDER BY authority_level DESC, experience_weight DESC
LIMIT 10;

\echo ''
\echo '6. Verify all assignment_data roles are documented...'
SELECT 
    'Missing Roles' as status,
    COUNT(DISTINCT a.role_code) as count
FROM assignment_data a
LEFT JOIN role_code_taxonomy r ON r.role_code = a.role_code
WHERE r.role_code IS NULL
UNION ALL
SELECT 
    'Documented Roles' as status,
    COUNT(DISTINCT a.role_code) as count
FROM assignment_data a
INNER JOIN role_code_taxonomy r ON r.role_code = a.role_code;

\echo ''
\echo '7. List any missing roles from assignment_data...'
SELECT DISTINCT a.role_code, COUNT(*) as occurrence_count
FROM assignment_data a
LEFT JOIN role_code_taxonomy r ON r.role_code = a.role_code
WHERE r.role_code IS NULL
GROUP BY a.role_code
ORDER BY COUNT(*) DESC;

\echo ''
\echo '8. Experience weight distribution...'
SELECT 
    CASE 
        WHEN experience_weight >= 1500 THEN 'Highest (1500+)'
        WHEN experience_weight >= 1000 THEN 'Very High (1000-1499)'
        WHEN experience_weight >= 500 THEN 'High (500-999)'
        WHEN experience_weight >= 100 THEN 'Medium (100-499)'
        ELSE 'Low (<100)'
    END as weight_category,
    COUNT(*) as role_count,
    MIN(experience_weight) as min_weight,
    MAX(experience_weight) as max_weight
FROM role_code_taxonomy
GROUP BY 
    CASE 
        WHEN experience_weight >= 1500 THEN 'Highest (1500+)'
        WHEN experience_weight >= 1000 THEN 'Very High (1000-1499)'
        WHEN experience_weight >= 500 THEN 'High (500-999)'
        WHEN experience_weight >= 100 THEN 'Medium (100-499)'
        ELSE 'Low (<100)'
    END
ORDER BY MIN(experience_weight) DESC;

\echo ''
\echo '9. Sample roles from each category...'
SELECT 
    role_category,
    role_code,
    role_name_en,
    authority_level,
    experience_weight
FROM (
    SELECT 
        *,
        ROW_NUMBER() OVER (PARTITION BY role_category ORDER BY authority_level DESC, experience_weight DESC) as rn
    FROM role_code_taxonomy
) ranked
WHERE rn <= 2
ORDER BY role_category, authority_level DESC;

\echo ''
\echo '10. Verify alignment with view_riksdagen_politician_experience_summary patterns...'
\echo 'Checking speaker roles (should be 750.00 for vice talman, 1000.00 for talman)...'
SELECT role_code, role_name_en, experience_weight
FROM role_code_taxonomy
WHERE role_category = 'SPEAKER'
ORDER BY authority_level DESC;

\echo ''
\echo '11. Check for duplicate role codes (should be 0)...'
SELECT role_code, COUNT(*) as duplicate_count
FROM role_code_taxonomy
GROUP BY role_code
HAVING COUNT(*) > 1;

\echo ''
\echo '===================================================='
\echo 'Verification Complete'
\echo '===================================================='
