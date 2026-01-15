-- ============================================================================
-- Validation Script for Party Longitudinal Analysis Views (v1.53)
-- ============================================================================
-- Purpose: Validate that the 3 new party longitudinal views are created
--          correctly and contain expected data for known party transformations
-- Author: Intelligence Operative
-- Date: 2026-01-15
-- GitHub Issue: #8209
-- ============================================================================

\echo '========================================================================='
\echo 'Party Longitudinal Analysis Views Validation (v1.53)'
\echo '========================================================================='
\echo ''

-- ============================================================================
-- 1. VIEW EXISTENCE CHECK
-- ============================================================================
\echo '1. Checking view existence...'
\echo ''

SELECT 
    CASE 
        WHEN EXISTS(SELECT 1 FROM information_schema.views WHERE table_name = 'view_riksdagen_party_longitudinal_performance')
        THEN '✓ view_riksdagen_party_longitudinal_performance exists'
        ELSE '✗ view_riksdagen_party_longitudinal_performance MISSING'
    END AS performance_view_status;

SELECT 
    CASE 
        WHEN EXISTS(SELECT 1 FROM information_schema.views WHERE table_name = 'view_riksdagen_party_coalition_evolution')
        THEN '✓ view_riksdagen_party_coalition_evolution exists'
        ELSE '✗ view_riksdagen_party_coalition_evolution MISSING'
    END AS coalition_view_status;

SELECT 
    CASE 
        WHEN EXISTS(SELECT 1 FROM information_schema.views WHERE table_name = 'view_riksdagen_party_electoral_trends')
        THEN '✓ view_riksdagen_party_electoral_trends exists'
        ELSE '✗ view_riksdagen_party_electoral_trends MISSING'
    END AS electoral_view_status;

\echo ''

-- ============================================================================
-- 2. DATA COVERAGE VALIDATION
-- ============================================================================
\echo '2. Validating data coverage...'
\echo ''

\echo '2.1 Party Longitudinal Performance - Election Cycles Covered:'
SELECT 
    election_cycle_id,
    COUNT(DISTINCT party) AS party_count,
    MIN(calendar_year) AS first_year,
    MAX(calendar_year) AS last_year
FROM view_riksdagen_party_longitudinal_performance
GROUP BY election_cycle_id
ORDER BY election_cycle_id;

\echo ''
\echo '2.2 Party Longitudinal Performance - Parties Tracked:'
SELECT 
    party,
    COUNT(DISTINCT election_cycle_id) AS cycles_tracked,
    MIN(calendar_year) AS first_year,
    MAX(calendar_year) AS last_year
FROM view_riksdagen_party_longitudinal_performance
GROUP BY party
ORDER BY cycles_tracked DESC, party;

\echo ''

-- ============================================================================
-- 3. METRIC VALIDATION
-- ============================================================================
\echo '3. Validating metrics completeness...'
\echo ''

\echo '3.1 Check for NULL values in key metrics:'
SELECT 
    'Party Performance View' AS view_name,
    COUNT(*) AS total_rows,
    COUNT(*) FILTER (WHERE total_ballots IS NULL) AS null_ballots,
    COUNT(*) FILTER (WHERE active_members IS NULL) AS null_members,
    COUNT(*) FILTER (WHERE win_rate IS NULL) AS null_win_rate,
    COUNT(*) FILTER (WHERE avg_rebel_rate IS NULL) AS null_rebel_rate,
    COUNT(*) FILTER (WHERE trajectory_win_rate IS NULL) AS null_trajectory_win_rate,
    COUNT(*) FILTER (WHERE trajectory_participation IS NULL) AS null_trajectory_participation
FROM view_riksdagen_party_longitudinal_performance;

\echo ''
\echo '3.2 Coalition Evolution - Party Pairs Tracked:'
SELECT 
    COUNT(DISTINCT (party_1, party_2)) AS unique_party_pairs,
    COUNT(DISTINCT election_cycle_id) AS cycles_covered,
    COUNT(*) AS total_relationships
FROM view_riksdagen_party_coalition_evolution;

\echo ''
\echo '3.3 Electoral Trends - Seat Count Validation:'
SELECT 
    election_cycle_id,
    SUM(seat_count_proxy) AS total_riksdag_seats,
    COUNT(DISTINCT party) AS parties_represented
FROM view_riksdagen_party_electoral_trends
GROUP BY election_cycle_id
ORDER BY election_cycle_id;

\echo ''

-- ============================================================================
-- 4. KNOWN PARTY TRANSFORMATION VALIDATION
-- ============================================================================
\echo '4. Validating known party transformations...'
\echo ''

\echo '4.1 Social Democrats (S) - Performance Evolution 2002-2026:'
SELECT 
    election_cycle_id,
    semester,
    party,
    win_rate,
    active_members,
    avg_rebel_rate,
    trajectory_win_rate,
    performance_tier,
    win_rate_change_absolute,
    membership_change
FROM view_riksdagen_party_longitudinal_performance
WHERE party = 'S'
ORDER BY election_cycle_id, cycle_year, semester;

\echo ''
\echo '4.2 Sweden Democrats (SD) - Electoral Growth Pattern:'
SELECT 
    election_cycle_id,
    semester,
    party,
    seat_count_proxy,
    win_rate,
    electoral_trend,
    party_size_category,
    seat_change_absolute
FROM view_riksdagen_party_electoral_trends
WHERE party = 'SD'
ORDER BY election_cycle_id, cycle_year, semester;

\echo ''

-- ============================================================================
-- 5. COALITION PATTERN VALIDATION
-- ============================================================================
\echo '5. Validating coalition patterns...'
\echo ''

\echo '5.1 Alliance Parties (M, C, FP/L, KD) - Coalition Strength Over Time:'
SELECT 
    election_cycle_id,
    semester,
    party_1,
    party_2,
    alignment_rate,
    coalition_strength,
    coalition_trend,
    strategic_shift
FROM view_riksdagen_party_coalition_evolution
WHERE party_1 IN ('M', 'C', 'L', 'FP', 'KD') 
  AND party_2 IN ('M', 'C', 'L', 'FP', 'KD')
ORDER BY party_1, party_2, election_cycle_id, cycle_year, semester;

\echo ''
\echo '5.2 Red-Green Coalition (S, V, MP) - Alignment Patterns:'
SELECT 
    election_cycle_id,
    semester,
    party_1,
    party_2,
    alignment_rate,
    coalition_strength,
    coalition_trend,
    strategic_shift
FROM view_riksdagen_party_coalition_evolution
WHERE party_1 IN ('S', 'V', 'MP') 
  AND party_2 IN ('S', 'V', 'MP')
ORDER BY party_1, party_2, election_cycle_id, cycle_year, semester;

\echo ''

-- ============================================================================
-- 6. TRAJECTORY CLASSIFICATION VALIDATION
-- ============================================================================
\echo '6. Validating trajectory classifications...'
\echo ''

\echo '6.1 Trajectory Distribution by Election Cycle:'
SELECT 
    election_cycle_id,
    trajectory_win_rate,
    COUNT(*) AS party_count
FROM view_riksdagen_party_longitudinal_performance
GROUP BY election_cycle_id, trajectory_win_rate
ORDER BY election_cycle_id, trajectory_win_rate;

\echo ''
\echo '6.2 Performance Tier Distribution:'
SELECT 
    election_cycle_id,
    performance_tier,
    COUNT(*) AS party_count
FROM view_riksdagen_party_longitudinal_performance
GROUP BY election_cycle_id, performance_tier
ORDER BY election_cycle_id, 
         CASE performance_tier 
             WHEN 'ELITE_PERFORMER' THEN 1 
             WHEN 'STRONG_PERFORMER' THEN 2 
             WHEN 'MODERATE_PERFORMER' THEN 3 
             WHEN 'WEAK_PERFORMER' THEN 4 
         END;

\echo ''

-- ============================================================================
-- 7. CROSS-CYCLE COMPARISON QUERIES
-- ============================================================================
\echo '7. Cross-cycle comparison examples...'
\echo ''

\echo '7.1 Compare S Performance: 2002-2005 vs 2022-2025:'
SELECT 
    'S Performance Comparison' AS analysis_type,
    p1.election_cycle_id AS early_cycle,
    p1.win_rate AS early_win_rate,
    p1.active_members AS early_members,
    p1.trajectory_win_rate AS early_trajectory,
    p2.election_cycle_id AS recent_cycle,
    p2.win_rate AS recent_win_rate,
    p2.active_members AS recent_members,
    p2.trajectory_win_rate AS recent_trajectory,
    ROUND(p2.win_rate - p1.win_rate, 2) AS win_rate_change_total,
    (p2.active_members - p1.active_members) AS membership_change_total
FROM view_riksdagen_party_longitudinal_performance p1
CROSS JOIN view_riksdagen_party_longitudinal_performance p2
WHERE p1.party = 'S' AND p1.election_cycle_id = '2002-2005'
  AND p2.party = 'S' AND p2.election_cycle_id = '2022-2025'
LIMIT 1;

\echo ''
\echo '7.2 Coalition Stability: M-C Alignment 2006-2009 vs 2022-2025:'
SELECT 
    'M-C Coalition Comparison' AS analysis_type,
    c1.election_cycle_id AS early_cycle,
    c1.alignment_rate AS early_alignment,
    c1.coalition_strength AS early_strength,
    c2.election_cycle_id AS recent_cycle,
    c2.alignment_rate AS recent_alignment,
    c2.coalition_strength AS recent_strength,
    ROUND(c2.alignment_rate - c1.alignment_rate, 2) AS alignment_change
FROM view_riksdagen_party_coalition_evolution c1
CROSS JOIN view_riksdagen_party_coalition_evolution c2
WHERE c1.party_1 = 'M' AND c1.party_2 = 'C' AND c1.election_cycle_id = '2006-2009'
  AND c2.party_1 = 'M' AND c2.party_2 = 'C' AND c2.election_cycle_id = '2022-2025'
LIMIT 1;

\echo ''

-- ============================================================================
-- 8. PERFORMANCE METRICS
-- ============================================================================
\echo '8. Performance validation...'
\echo ''

\echo '8.1 Row counts per view:'
SELECT 
    'view_riksdagen_party_longitudinal_performance' AS view_name,
    COUNT(*) AS row_count
FROM view_riksdagen_party_longitudinal_performance
UNION ALL
SELECT 
    'view_riksdagen_party_coalition_evolution' AS view_name,
    COUNT(*) AS row_count
FROM view_riksdagen_party_coalition_evolution
UNION ALL
SELECT 
    'view_riksdagen_party_electoral_trends' AS view_name,
    COUNT(*) AS row_count
FROM view_riksdagen_party_electoral_trends;

\echo ''
\echo '8.2 Query performance check (EXPLAIN ANALYZE):'
EXPLAIN ANALYZE
SELECT 
    party,
    election_cycle_id,
    semester,
    win_rate,
    trajectory_win_rate
FROM view_riksdagen_party_longitudinal_performance
WHERE party IN ('S', 'M', 'SD')
ORDER BY party, election_cycle_id, cycle_year, semester;

\echo ''
\echo '========================================================================='
\echo 'Validation Complete'
\echo '========================================================================='
\echo ''
\echo 'Next Steps:'
\echo '1. Review trajectory classifications for accuracy'
\echo '2. Validate known party transformations (e.g., S decline 2002-2014)'
\echo '3. Check coalition evolution patterns match historical records'
\echo '4. Performance optimization if queries exceed 2 seconds'
\echo ''
