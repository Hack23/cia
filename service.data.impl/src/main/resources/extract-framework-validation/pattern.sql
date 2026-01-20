-- pattern.sql
-- Framework 3: Pattern Recognition Validation Test Cases
-- Purpose: Extract validation datasets for behavioral pattern clustering

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 3: PATTERN RECOGNITION VALIDATION                 ==='
\echo '==================================================================='
\echo ''

-- ============================================================================
-- TEST 3.1: Behavioral Clustering (Normal, Anomalous, Concerning)
-- Expected Outcome: Classify behavioral patterns into 3 clusters
-- Sample Size: 100 cases
-- ============================================================================
\echo '>>> Test Case 3.1: Behavioral Clustering - Performance Pattern Classification'
\echo '>>> Expected Outcome: Classify behavioral patterns into 3 clusters'

COPY (
    WITH politician_patterns_base AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            AVG(avg_absence_rate) AS avg_absence,
            AVG(avg_win_rate) AS avg_win,
            AVG(avg_rebel_rate) AS avg_rebel,
            COUNT(*) AS months_tracked,
            STDDEV(avg_absence_rate) AS absence_volatility
        FROM view_politician_behavioral_trends
        WHERE period_start >= CURRENT_DATE - cia_get_lookback_interval('medium')
          AND total_ballots >= cia_get_config_value('min_votes_per_month')::INTEGER
        GROUP BY person_id, first_name, last_name, party
        HAVING COUNT(*) >= cia_get_config_value('min_months_tracked')::INTEGER
    ),
    politician_patterns AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            avg_absence,
            avg_win,
            avg_rebel,
            months_tracked,
            absence_volatility,
            cia_classify_behavioral_cluster(avg_absence, avg_win, avg_rebel) AS expected_cluster
        FROM politician_patterns_base
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        ROUND(avg_absence, 2) AS avg_absence_rate,
        ROUND(avg_win, 2) AS avg_win_rate,
        ROUND(avg_rebel, 2) AS avg_rebel_rate,
        ROUND(absence_volatility, 2) AS absence_volatility,
        months_tracked,
        expected_cluster AS expected_detection,
        'pattern_behavioral_clustering' AS test_case,
        CASE 
            WHEN expected_cluster IN ('NORMAL_BEHAVIOR', 'ANOMALOUS_BEHAVIOR', 'CONCERNING_BEHAVIOR') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM politician_patterns
    WHERE expected_cluster != 'UNCLASSIFIED'
    ORDER BY 
        CASE expected_cluster
            WHEN 'CONCERNING_BEHAVIOR' THEN 1
            WHEN 'ANOMALOUS_BEHAVIOR' THEN 2
            WHEN 'NORMAL_BEHAVIOR' THEN 3
        END,
        avg_absence DESC
    LIMIT cia_get_config_value('sample_size_large')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/pattern/test_3_1_behavioral_clustering.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected behavioral cluster classification'
\echo ''

-- ============================================================================
-- TEST 3.2: Rebellion Pattern Identification
-- Expected Outcome: Identify HIGH_INDEPENDENCE and PARTY_LINE patterns
-- Sample Size: 80 cases
-- ============================================================================
\echo '>>> Test Case 3.2: Rebellion Patterns - High Independence vs. Party Line'
\echo '>>> Expected Outcome: Identify HIGH_INDEPENDENCE and PARTY_LINE patterns'

COPY (
    WITH rebellion_patterns_base AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            AVG(avg_rebel_rate) AS avg_rebel_rate,
            MAX(avg_rebel_rate) AS peak_rebel_rate,
            STDDEV(avg_rebel_rate) AS rebel_volatility,
            COUNT(*) AS months_tracked
        FROM view_politician_behavioral_trends
        WHERE period_start >= CURRENT_DATE - cia_get_lookback_interval('medium')
          AND total_ballots >= cia_get_config_value('min_votes_per_month')::INTEGER
        GROUP BY person_id, first_name, last_name, party
        HAVING COUNT(*) >= cia_get_config_value('min_months_tracked')::INTEGER
    ),
    rebellion_patterns AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            avg_rebel_rate,
            peak_rebel_rate,
            rebel_volatility,
            months_tracked,
            cia_classify_rebellion_pattern(avg_rebel_rate) AS expected_pattern
        FROM rebellion_patterns_base
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        ROUND(avg_rebel_rate, 2) AS avg_rebel_rate,
        ROUND(peak_rebel_rate, 2) AS peak_rebel_rate,
        ROUND(rebel_volatility, 2) AS rebel_volatility,
        months_tracked,
        expected_pattern AS expected_detection,
        'pattern_rebellion_identification' AS test_case,
        CASE 
            WHEN expected_pattern IN ('HIGH_INDEPENDENCE', 'PARTY_LINE') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM rebellion_patterns
    WHERE expected_pattern IN ('HIGH_INDEPENDENCE', 'PARTY_LINE', 'MODERATE_INDEPENDENCE')
    ORDER BY avg_rebel_rate DESC
    LIMIT cia_get_config_value('sample_size_xlarge')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/pattern/test_3_2_rebellion_patterns.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected rebellion pattern classification'
\echo ''

-- ============================================================================
-- TEST 3.3: Career Path Classification (Advanced View v1.58)
-- Expected Outcome: 10-level career trajectory classification
-- Sample Size: 80 cases
-- ============================================================================
\echo '>>> Test Case 3.3: Career Path - 10-Level Hierarchical Classification'
\echo '>>> Expected Outcome: Identify career progression patterns (L01-L10)'

COPY (
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        total_roles,
        years_active,
        ROUND(career_score::numeric, 2) AS career_score,
        peak_career_level,
        current_career_level,
        career_trajectory,
        peak_role,
        current_role,
        ROUND(roles_l10_pm::numeric, 0) AS roles_l10_pm,
        ROUND(roles_l09_ministers::numeric, 0) AS roles_l09_ministers,
        ROUND(roles_l08_speaker::numeric, 0) AS roles_l08_speaker,
        ROUND(roles_l07_party_leaders::numeric, 0) AS roles_l07_party_leaders,
        ROUND(roles_l06_committee_chairs::numeric, 0) AS roles_l06_committee_chairs,
        ROUND(roles_l04_active_mp::numeric, 0) AS roles_l04_active_mp,
        CASE 
            WHEN peak_career_level IN ('L10', 'L09', 'L08') THEN 'ELITE_CAREER'
            WHEN peak_career_level IN ('L07', 'L06') THEN 'SENIOR_CAREER'
            WHEN peak_career_level IN ('L05', 'L04') THEN 'STANDARD_CAREER'
            ELSE 'ENTRY_LEVEL_CAREER'
        END AS expected_career_tier,
        CASE 
            WHEN career_trajectory = 'UPWARD' AND peak_career_level >= 'L06' THEN 'SUCCESSFUL_PROGRESSION'
            WHEN career_trajectory = 'DOWNWARD' AND current_career_level <= 'L03' THEN 'CAREER_DECLINE'
            WHEN career_trajectory = 'STABLE' AND current_career_level >= 'L06' THEN 'ESTABLISHED_POSITION'
            ELSE 'TYPICAL_PATTERN'
        END AS expected_career_pattern,
        'career_path_analysis' AS test_case,
        CASE 
            WHEN peak_career_level >= 'L06' OR career_score >= 600 THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM view_riksdagen_politician_career_path_10level
    WHERE years_active >= 4  -- Focus on politicians with substantive careers
      AND total_roles >= 2
    ORDER BY career_score DESC
    LIMIT cia_get_config_value('sample_size_xlarge')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/pattern/test_3_3_career_path.csv' WITH CSV HEADER;

\echo '>>> Exported 10-level career path classifications'
\echo ''
