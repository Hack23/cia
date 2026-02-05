-- Enable detailed query analysis with timeout protection
SET track_io_timing = ON;
-- Note: timing is controlled within EXPLAIN command, not as a session parameter
SET statement_timeout = '60s';  -- 60-second timeout per query

\echo '=========================================================================='
\echo '=== Coalition Alignment Matrix - Detailed Subquery Analysis          ==='
\echo '=========================================================================='
\echo ''
\echo 'This analysis breaks down the coalition view into individual CTEs'
\echo 'to identify performance bottlenecks and optimization opportunities.'
\echo 'Each query has a 60-second timeout for safety.'
\echo ''

-- ===========================================================================
-- SECTION 1: Analyze CTE 1 - party_votes (Base Data Filter)
-- ===========================================================================
\echo ''
\echo '=========================================================================='
\echo '=== CTE 1: party_votes (5-year vote filter)                         ==='
\echo '=========================================================================='
\echo ''

EXPLAIN (ANALYZE true, VERBOSE true, BUFFERS true, TIMING true, SUMMARY true, FORMAT text)
SELECT vote_data.party,
    vote_data.embedded_id_ballot_id AS ballot_id,
    vote_data.vote,
    vote_data.vote_date
FROM public.vote_data
WHERE vote_data.vote_date >= (CURRENT_DATE - '5 years'::interval)
  AND vote_data.party IS NOT NULL
  AND vote_data.vote IS NOT NULL;

\echo ''
\echo '--- CTE 1 Analysis Summary ---'
\echo 'Purpose: Filter vote_data for last 5 years with non-null party/vote'
\echo 'Expected: Should use index on vote_date + party + vote'
\echo 'Watch for: Sequential scans, missing indexes'
\echo ''

-- ===========================================================================
-- SECTION 2: Analyze CTE 2 - party_pairs (Cartesian Product)
-- ===========================================================================
\echo ''
\echo '=========================================================================='
\echo '=== CTE 2: party_pairs (All party combinations)                     ==='
\echo '=========================================================================='
\echo ''

EXPLAIN (ANALYZE true, VERBOSE true, BUFFERS true, TIMING true, SUMMARY true, FORMAT text)
WITH party_votes AS (
    SELECT vote_data.party,
        vote_data.embedded_id_ballot_id AS ballot_id,
        vote_data.vote,
        vote_data.vote_date
    FROM public.vote_data
    WHERE vote_data.vote_date >= (CURRENT_DATE - '5 years'::interval)
      AND vote_data.party IS NOT NULL
      AND vote_data.vote IS NOT NULL
)
SELECT DISTINCT p1.party AS party1, p2.party AS party2
FROM party_votes p1
CROSS JOIN party_votes p2
WHERE p1.party < p2.party;

\echo ''
\echo '--- CTE 2 Analysis Summary ---'
\echo 'Purpose: Generate all unique party pairs (lower-triangle matrix)'
\echo 'Expected: DISTINCT on small result set (8-12 parties = 28-66 pairs)'
\echo 'Watch for: Expensive CROSS JOIN materialization'
\echo ''

-- ===========================================================================
-- SECTION 3: Analyze CTE 3 - alignment_metrics (Heavy Aggregation)
-- ===========================================================================
\echo ''
\echo '=========================================================================='
\echo '=== CTE 3: alignment_metrics (Vote alignment aggregation)            ==='
\echo '=========================================================================='
\echo ''

EXPLAIN (ANALYZE true, VERBOSE true, BUFFERS true, TIMING true, SUMMARY true, FORMAT text)
WITH party_votes AS (
    SELECT vote_data.party,
        vote_data.embedded_id_ballot_id AS ballot_id,
        vote_data.vote,
        vote_data.vote_date
    FROM public.vote_data
    WHERE vote_data.vote_date >= (CURRENT_DATE - '5 years'::interval)
      AND vote_data.party IS NOT NULL
      AND vote_data.vote IS NOT NULL
), party_pairs AS (
    SELECT DISTINCT p1.party AS party1, p2.party AS party2
    FROM party_votes p1
    CROSS JOIN party_votes p2
    WHERE p1.party < p2.party
)
SELECT pp.party1,
    pp.party2,
    count(DISTINCT pv1.ballot_id) AS total_votes,
    count(DISTINCT CASE WHEN pv1.vote = pv2.vote THEN pv1.ballot_id END) AS aligned_votes,
    count(DISTINCT CASE WHEN pv1.vote = 'Ja' AND pv2.vote = 'Ja' THEN pv1.ballot_id END) AS both_yes,
    count(DISTINCT CASE WHEN pv1.vote = 'Nej' AND pv2.vote = 'Nej' THEN pv1.ballot_id END) AS both_no,
    count(DISTINCT CASE WHEN pv1.vote = 'Avstå' OR pv2.vote = 'Avstå' THEN pv1.ballot_id END) AS abstention_count,
    min(pv1.vote_date) AS earliest_vote,
    max(pv1.vote_date) AS latest_vote
FROM party_pairs pp
JOIN party_votes pv1 ON pv1.party = pp.party1
JOIN party_votes pv2 ON pv2.party = pp.party2 AND pv2.ballot_id = pv1.ballot_id
GROUP BY pp.party1, pp.party2;

\echo ''
\echo '--- CTE 3 Analysis Summary ---'
\echo 'Purpose: Calculate alignment metrics for each party pair'
\echo 'Expected: Hash joins on party, nested loop on ballot_id'
\echo 'Watch for: Multiple COUNT(DISTINCT), hash aggregation spills'
\echo ''

-- ===========================================================================
-- SECTION 4: Analyze JOIN Strategy Between CTEs
-- ===========================================================================
\echo ''
\echo '=========================================================================='
\echo '=== JOIN Analysis: party_pairs → party_votes (pv1)                   ==='
\echo '=========================================================================='
\echo ''

EXPLAIN (ANALYZE true, VERBOSE true, BUFFERS true, TIMING true, FORMAT text)
WITH party_votes AS (
    SELECT vote_data.party,
        vote_data.embedded_id_ballot_id AS ballot_id,
        vote_data.vote,
        vote_data.vote_date
    FROM public.vote_data
    WHERE vote_data.vote_date >= (CURRENT_DATE - '5 years'::interval)
      AND vote_data.party IS NOT NULL
      AND vote_data.vote IS NOT NULL
), party_pairs AS (
    SELECT DISTINCT p1.party AS party1, p2.party AS party2
    FROM party_votes p1 CROSS JOIN party_votes p2
    WHERE p1.party < p2.party
)
SELECT pp.party1, pp.party2, COUNT(*) AS pv1_matches
FROM party_pairs pp
JOIN party_votes pv1 ON pv1.party = pp.party1
GROUP BY pp.party1, pp.party2
LIMIT 10;

\echo ''
\echo '--- First JOIN Analysis ---'
\echo 'Join type: party_pairs → party_votes (filtering by party1)'
\echo 'Expected: Hash join or nested loop'
\echo ''

\echo ''
\echo '=========================================================================='
\echo '=== JOIN Analysis: pv1 → party_votes (pv2) on ballot_id             ==='
\echo '=========================================================================='
\echo ''

EXPLAIN (ANALYZE true, VERBOSE true, BUFFERS true, TIMING true, FORMAT text)
WITH party_votes AS (
    SELECT vote_data.party,
        vote_data.embedded_id_ballot_id AS ballot_id,
        vote_data.vote,
        vote_data.vote_date
    FROM public.vote_data
    WHERE vote_data.vote_date >= (CURRENT_DATE - '5 years'::interval)
      AND vote_data.party IS NOT NULL
      AND vote_data.vote IS NOT NULL
), party_pairs AS (
    SELECT DISTINCT p1.party AS party1, p2.party AS party2
    FROM party_votes p1 CROSS JOIN party_votes p2
    WHERE p1.party < p2.party
)
SELECT pp.party1, pp.party2, COUNT(*) AS matching_ballots
FROM party_pairs pp
JOIN party_votes pv1 ON pv1.party = pp.party1
JOIN party_votes pv2 ON pv2.party = pp.party2 AND pv2.ballot_id = pv1.ballot_id
GROUP BY pp.party1, pp.party2
LIMIT 10;

\echo ''
\echo '--- Second JOIN Analysis ---'
\echo 'Join type: pv1 → party_votes (pv2) matching ballot_id'
\echo 'Expected: Hash join on ballot_id with party filter'
\echo 'Watch for: Nested loop if index available'
\echo ''

-- ===========================================================================
-- SECTION 5: Analyze COUNT(DISTINCT) Performance
-- ===========================================================================
\echo ''
\echo '=========================================================================='
\echo '=== Aggregation Analysis: COUNT(DISTINCT ballot_id) Performance     ==='
\echo '=========================================================================='
\echo ''

EXPLAIN (ANALYZE true, VERBOSE true, BUFFERS true, TIMING true, FORMAT text)
WITH party_votes AS (
    SELECT vote_data.party,
        vote_data.embedded_id_ballot_id AS ballot_id,
        vote_data.vote,
        vote_data.vote_date
    FROM public.vote_data
    WHERE vote_data.vote_date >= (CURRENT_DATE - '5 years'::interval)
      AND vote_data.party IS NOT NULL
      AND vote_data.vote IS NOT NULL
)
SELECT 
    COUNT(DISTINCT ballot_id) AS total_ballots,
    COUNT(DISTINCT CASE WHEN party = 'S' THEN ballot_id END) AS s_ballots,
    COUNT(DISTINCT CASE WHEN party = 'M' THEN ballot_id END) AS m_ballots
FROM party_votes;

\echo ''
\echo '--- COUNT(DISTINCT) Analysis ---'
\echo 'Purpose: Measure distinct ballot counting overhead'
\echo 'Watch for: Hash aggregate vs. sort aggregate'
\echo ''

-- ===========================================================================
-- SECTION 6: Full Query Analysis
-- ===========================================================================
\echo ''
\echo '=========================================================================='
\echo '=== Full Query: Complete Coalition Alignment Matrix                 ==='
\echo '=========================================================================='
\echo ''

EXPLAIN (ANALYZE true, VERBOSE true, BUFFERS true, TIMING true, SUMMARY true, FORMAT text)
WITH party_votes AS (
    SELECT vote_data.party,
        vote_data.embedded_id_ballot_id AS ballot_id,
        vote_data.vote,
        vote_data.vote_date
    FROM public.vote_data
    WHERE vote_data.vote_date >= (CURRENT_DATE - '5 years'::interval)
      AND vote_data.party IS NOT NULL
      AND vote_data.vote IS NOT NULL
), party_pairs AS (
    SELECT DISTINCT p1.party AS party1, p2.party AS party2
    FROM party_votes p1 CROSS JOIN party_votes p2
    WHERE p1.party < p2.party
), alignment_metrics AS (
    SELECT pp.party1,
        pp.party2,
        count(DISTINCT pv1.ballot_id) AS total_votes,
        count(DISTINCT CASE WHEN pv1.vote = pv2.vote THEN pv1.ballot_id END) AS aligned_votes,
        count(DISTINCT CASE WHEN pv1.vote = 'Ja' AND pv2.vote = 'Ja' THEN pv1.ballot_id END) AS both_yes,
        count(DISTINCT CASE WHEN pv1.vote = 'Nej' AND pv2.vote = 'Nej' THEN pv1.ballot_id END) AS both_no,
        count(DISTINCT CASE WHEN pv1.vote = 'Avstå' OR pv2.vote = 'Avstå' THEN pv1.ballot_id END) AS abstention_count,
        min(pv1.vote_date) AS earliest_vote,
        max(pv1.vote_date) AS latest_vote
    FROM party_pairs pp
    JOIN party_votes pv1 ON pv1.party = pp.party1
    JOIN party_votes pv2 ON pv2.party = pp.party2 AND pv2.ballot_id = pv1.ballot_id
    GROUP BY pp.party1, pp.party2
)
SELECT party1, party2, total_votes, aligned_votes, both_yes, both_no,
    abstention_count, earliest_vote, latest_vote,
    round((aligned_votes::numeric / NULLIF(total_votes, 0) * 100), 2) AS alignment_percentage,
    round((both_yes::numeric / NULLIF(total_votes, 0) * 100), 2) AS joint_support_rate,
    round((both_no::numeric / NULLIF(total_votes, 0) * 100), 2) AS joint_opposition_rate,
    CASE
        WHEN (aligned_votes::numeric / NULLIF(total_votes, 0)) >= 0.80 THEN 'STRONG_COALITION'
        WHEN (aligned_votes::numeric / NULLIF(total_votes, 0)) >= 0.60 THEN 'MODERATE_COALITION'
        WHEN (aligned_votes::numeric / NULLIF(total_votes, 0)) >= 0.40 THEN 'WEAK_ALIGNMENT'
        ELSE 'OPPOSITION'
    END AS coalition_strength,
    CASE
        WHEN (aligned_votes::numeric / NULLIF(total_votes, 0)) >= 0.80 THEN 'Strong coalition partnership - consistent aligned voting'
        WHEN (aligned_votes::numeric / NULLIF(total_votes, 0)) >= 0.60 THEN 'Moderate coalition alignment - generally cooperative'
        WHEN (aligned_votes::numeric / NULLIF(total_votes, 0)) <= 0.40 THEN 'Opposition positioning - frequently divergent votes'
        ELSE 'Neutral relationship - mixed voting patterns'
    END AS coalition_assessment
FROM alignment_metrics
WHERE total_votes >= 10
ORDER BY alignment_percentage DESC, total_votes DESC;

-- ===========================================================================
-- SECTION 7: Index & Statistics Analysis
-- ===========================================================================
\echo ''
\echo '=========================================================================='
\echo '=== Index & Statistics Analysis                                      ==='
\echo '=========================================================================='
\echo ''

\echo '--- Current Indexes on vote_data ---'
SELECT 
    indexname,
    indexdef
FROM pg_indexes
WHERE schemaname = 'public'
AND tablename = 'vote_data'
ORDER BY indexname;

\echo ''
\echo '--- Index Usage Statistics ---'
SELECT
    schemaname,
    relname AS tablename,
    indexrelname AS indexname,
    idx_scan AS index_scans,
    idx_tup_read AS tuples_read,
    idx_tup_fetch AS tuples_fetched,
    pg_size_pretty(pg_relation_size(indexrelid)) AS index_size
FROM pg_stat_user_indexes
WHERE schemaname = 'public'
AND relname = 'vote_data'
ORDER BY idx_scan DESC;

\echo ''
\echo '--- Table Statistics ---'
SELECT 
    relname AS table_name,
    n_live_tup AS row_count,
    n_dead_tup AS dead_rows,
    pg_size_pretty(pg_total_relation_size(relid)) AS total_size,
    pg_size_pretty(pg_relation_size(relid)) AS table_size,
    pg_size_pretty(pg_total_relation_size(relid) - pg_relation_size(relid)) AS index_size,
    last_vacuum,
    last_autovacuum,
    last_analyze,
    last_autoanalyze
FROM pg_stat_user_tables
WHERE schemaname = 'public'
AND relname = 'vote_data';

\echo ''
\echo '--- Column Statistics (for optimizer) ---'
SELECT 
    schemaname,
    tablename,
    attname AS column_name,
    n_distinct,
    correlation,
    most_common_vals,
    most_common_freqs
FROM pg_stats
WHERE schemaname = 'public'
AND tablename = 'vote_data'
AND attname IN ('party', 'vote', 'vote_date', 'embedded_id_ballot_id')
ORDER BY attname;

-- ===========================================================================
-- SECTION 8: Recommendations
-- ===========================================================================
\echo ''
\echo '=========================================================================='
\echo '=== Optimization Recommendations                                     ==='
\echo '=========================================================================='
\echo ''
\echo 'Based on the analysis above, consider:'
\echo ''
\echo '1. Missing Indexes (if sequential scans detected):'
\echo '   CREATE INDEX idx_vote_data_coalition_filter'
\echo '   ON vote_data (vote_date, party, vote)'
\echo '   WHERE vote_date >= (CURRENT_DATE - INTERVAL ''5 years'')'
\echo '     AND party IS NOT NULL AND vote IS NOT NULL;'
\echo ''
\echo '   CREATE INDEX idx_vote_data_party_ballot'
\echo '   ON vote_data (party, embedded_id_ballot_id, vote);'
\echo ''
\echo '2. Materialized View (if query runs frequently):'
\echo '   CREATE MATERIALIZED VIEW mv_coalition_alignment_matrix AS'
\echo '   SELECT * FROM view_riksdagen_coalition_alignment_matrix;'
\echo ''
\echo '3. Statistics Update (if estimates are wrong):'
\echo '   ALTER TABLE vote_data ALTER COLUMN party SET STATISTICS 1000;'
\echo '   ALTER TABLE vote_data ALTER COLUMN embedded_id_ballot_id SET STATISTICS 1000;'
\echo '   ANALYZE vote_data;'
\echo ''
\echo '4. Query Tuning:'
\echo '   - Consider work_mem increase for hash aggregations'
\echo '   - Evaluate if DISTINCT can be replaced with GROUP BY'
\echo '   - Test if pre-filtering party_pairs improves performance'
\echo ''
\echo '=========================================================================='
\echo '=== Analysis Complete                                                ==='
\echo '=========================================================================='

-- Reset settings
RESET statement_timeout;
RESET track_io_timing;
RESET enable_timing;

\echo ''
\echo 'Analysis complete with timeout protection (60s per query).'
