-- Risk Score Threshold Validation Query
-- Validates new threshold distribution against current data
-- Author: Political Analyst & Intelligence Operative
-- Date: 2026-01-09
-- Purpose: Verify v1.48 threshold changes achieve balanced distribution

\pset pager off
\set ON_ERROR_STOP on

-- ============================================================================
-- 1. Current Distribution (OLD THRESHOLDS: 70/50/30)
-- 2. New Distribution (NEW THRESHOLDS: 65/45/25)
-- ============================================================================

CREATE TEMP VIEW tmp_risk_distribution AS
SELECT
    'CURRENT_THRESHOLDS' AS analysis_type,
    'Based on v1.32-v1.47 thresholds' AS threshold_config,
    CASE
        WHEN risk_score >= 70 THEN 'CRITICAL'
        WHEN risk_score >= 50 THEN 'HIGH'
        WHEN risk_score >= 30 THEN 'MEDIUM'
        ELSE 'LOW'
    END AS risk_level,
    COUNT(*) AS politician_count,
    ROUND((COUNT(*) * 100.0 / (SELECT COUNT(*) FROM view_politician_risk_summary)), 2) AS percentage,
    MIN(risk_score) AS min_score,
    MAX(risk_score) AS max_score,
    ROUND(AVG(risk_score)::NUMERIC, 2) AS avg_score,
    CASE
        WHEN risk_score >= 70 THEN 1
        WHEN risk_score >= 50 THEN 2
        WHEN risk_score >= 30 THEN 3
        ELSE 4
    END AS sort_order
FROM view_politician_risk_summary
GROUP BY
    CASE
        WHEN risk_score >= 70 THEN 'CRITICAL'
        WHEN risk_score >= 50 THEN 'HIGH'
        WHEN risk_score >= 30 THEN 'MEDIUM'
        ELSE 'LOW'
    END,
    CASE
        WHEN risk_score >= 70 THEN 1
        WHEN risk_score >= 50 THEN 2
        WHEN risk_score >= 30 THEN 3
        ELSE 4
    END
UNION ALL
SELECT
    'NEW_THRESHOLDS',
    'Based on v1.48 thresholds',
    CASE
        WHEN risk_score >= 65 THEN 'CRITICAL'
        WHEN risk_score >= 45 THEN 'HIGH'
        WHEN risk_score >= 25 THEN 'MEDIUM'
        ELSE 'LOW'
    END AS risk_level,
    COUNT(*) AS politician_count,
    ROUND((COUNT(*) * 100.0 / (SELECT COUNT(*) FROM view_politician_risk_summary)), 2) AS percentage,
    MIN(risk_score) AS min_score,
    MAX(risk_score) AS max_score,
    ROUND(AVG(risk_score)::NUMERIC, 2) AS avg_score,
    CASE
        WHEN risk_score >= 65 THEN 1
        WHEN risk_score >= 45 THEN 2
        WHEN risk_score >= 25 THEN 3
        ELSE 4
    END AS sort_order
FROM view_politician_risk_summary
GROUP BY
    CASE
        WHEN risk_score >= 65 THEN 'CRITICAL'
        WHEN risk_score >= 45 THEN 'HIGH'
        WHEN risk_score >= 25 THEN 'MEDIUM'
        ELSE 'LOW'
    END,
    CASE
        WHEN risk_score >= 65 THEN 1
        WHEN risk_score >= 45 THEN 2
        WHEN risk_score >= 25 THEN 3
        ELSE 4
    END
ORDER BY
    analysis_type,
    sort_order;

\copy (SELECT * FROM tmp_risk_distribution) TO 'service.data.impl/sample-data/validate_risk_distribution.csv' WITH (FORMAT CSV, HEADER);
DROP VIEW tmp_risk_distribution;


-- ============================================================================
-- 3. Distribution Comparison Summary
-- ============================================================================

CREATE TEMP VIEW tmp_risk_comparison AS
WITH current_dist AS (
    SELECT
        CASE
            WHEN risk_score >= 70 THEN 'CRITICAL'
            WHEN risk_score >= 50 THEN 'HIGH'
            WHEN risk_score >= 30 THEN 'MEDIUM'
            ELSE 'LOW'
        END AS risk_level,
        COUNT(*) AS count
    FROM view_politician_risk_summary
    GROUP BY
        CASE
            WHEN risk_score >= 70 THEN 'CRITICAL'
            WHEN risk_score >= 50 THEN 'HIGH'
            WHEN risk_score >= 30 THEN 'MEDIUM'
            ELSE 'LOW'
        END
),
new_dist AS (
    SELECT
        CASE
            WHEN risk_score >= 65 THEN 'CRITICAL'
            WHEN risk_score >= 45 THEN 'HIGH'
            WHEN risk_score >= 25 THEN 'MEDIUM'
            ELSE 'LOW'
        END AS risk_level,
        COUNT(*) AS count
    FROM view_politician_risk_summary
    GROUP BY
        CASE
            WHEN risk_score >= 65 THEN 'CRITICAL'
            WHEN risk_score >= 45 THEN 'HIGH'
            WHEN risk_score >= 25 THEN 'MEDIUM'
            ELSE 'LOW'
        END
),
total AS (
    SELECT COUNT(*) AS total_count FROM view_politician_risk_summary
)
SELECT
    COALESCE(cd.risk_level, nd.risk_level) AS risk_level,
    COALESCE(cd.count, 0) AS old_count,
    ROUND((COALESCE(cd.count, 0) * 100.0 / t.total_count), 2) AS old_percentage,
    COALESCE(nd.count, 0) AS new_count,
    ROUND((COALESCE(nd.count, 0) * 100.0 / t.total_count), 2) AS new_percentage,
    (COALESCE(nd.count, 0) - COALESCE(cd.count, 0)) AS count_change,
    ROUND((COALESCE(nd.count, 0) * 100.0 / t.total_count) -
          (COALESCE(cd.count, 0) * 100.0 / t.total_count), 2) AS percentage_change
FROM current_dist cd
FULL OUTER JOIN new_dist nd ON cd.risk_level = nd.risk_level
CROSS JOIN total t
ORDER BY
    CASE COALESCE(cd.risk_level, nd.risk_level)
        WHEN 'CRITICAL' THEN 1
        WHEN 'HIGH' THEN 2
        WHEN 'MEDIUM' THEN 3
        WHEN 'LOW' THEN 4
    END;

\copy (SELECT * FROM tmp_risk_comparison) TO 'service.data.impl/sample-data/validate_risk_comparison.csv' WITH (FORMAT CSV, HEADER);
DROP VIEW tmp_risk_comparison;


-- ============================================================================
-- 4. Target Distribution Validation
-- ============================================================================

CREATE TEMP VIEW tmp_risk_targets AS
WITH new_dist AS (
    SELECT
        CASE
            WHEN risk_score >= 65 THEN 'CRITICAL'
            WHEN risk_score >= 45 THEN 'HIGH'
            WHEN risk_score >= 25 THEN 'MEDIUM'
            ELSE 'LOW'
        END AS risk_level,
        COUNT(*) AS count
    FROM view_politician_risk_summary
    GROUP BY
        CASE
            WHEN risk_score >= 65 THEN 'CRITICAL'
            WHEN risk_score >= 45 THEN 'HIGH'
            WHEN risk_score >= 25 THEN 'MEDIUM'
            ELSE 'LOW'
        END
),
total AS (
    SELECT COUNT(*) AS total_count FROM view_politician_risk_summary
),
targets AS (
    SELECT 'CRITICAL' AS risk_level, 0.0 AS target_min, 1.0 AS target_max
    UNION ALL SELECT 'HIGH', 15.0, 25.0
    UNION ALL SELECT 'MEDIUM', 50.0, 60.0
    UNION ALL SELECT 'LOW', 25.0, 35.0
)
SELECT
    t.risk_level,
    COALESCE(nd.count, 0) AS actual_count,
    ROUND((COALESCE(nd.count, 0) * 100.0 / total.total_count), 2) AS actual_percentage,
    t.target_range,
    CASE
        WHEN (COALESCE(nd.count, 0) * 100.0 / total.total_count) BETWEEN t.target_min AND t.target_max
        THEN '✓ WITHIN TARGET'
        WHEN (COALESCE(nd.count, 0) * 100.0 / total.total_count) < t.target_min
        THEN '✗ BELOW TARGET'
        ELSE '✗ ABOVE TARGET'
    END AS validation_status
FROM
    (SELECT 'CRITICAL' AS risk_level, 0.0 AS target_min, 1.0 AS target_max, '0-1%' AS target_range
     UNION ALL SELECT 'HIGH', 15.0, 25.0, '15-25%'
     UNION ALL SELECT 'MEDIUM', 50.0, 60.0, '50-60%'
     UNION ALL SELECT 'LOW', 25.0, 35.0, '25-35%') t
LEFT JOIN new_dist nd ON t.risk_level = nd.risk_level
CROSS JOIN total
ORDER BY
    CASE t.risk_level
        WHEN 'CRITICAL' THEN 1
        WHEN 'HIGH' THEN 2
        WHEN 'MEDIUM' THEN 3
        WHEN 'LOW' THEN 4
    END;

\copy (SELECT * FROM tmp_risk_targets) TO 'service.data.impl/sample-data/validate_risk_targets.csv' WITH (FORMAT CSV, HEADER);
DROP VIEW tmp_risk_targets;


-- ============================================================================
-- 5. Score Distribution Analysis
-- ============================================================================

CREATE TEMP VIEW tmp_risk_scores AS
SELECT
    '0-10' AS score_range,
    COUNT(*) AS count,
    ROUND(AVG(risk_score)::NUMERIC, 2) AS avg_score,
    MIN(risk_score) AS min_score,
    MAX(risk_score) AS max_score
FROM view_politician_risk_summary
WHERE risk_score >= 0 AND risk_score < 10
GROUP BY score_range
UNION ALL
SELECT
    '10-20' AS score_range,
    COUNT(*) AS count,
    ROUND(AVG(risk_score)::NUMERIC, 2) AS avg_score,
    MIN(risk_score) AS min_score,
    MAX(risk_score) AS max_score
FROM view_politician_risk_summary
WHERE risk_score >= 10 AND risk_score < 20
UNION ALL
SELECT
    '20-30' AS score_range,
    COUNT(*) AS count,
    ROUND(AVG(risk_score)::NUMERIC, 2) AS avg_score,
    MIN(risk_score) AS min_score,
    MAX(risk_score) AS max_score
FROM view_politician_risk_summary
WHERE risk_score >= 20 AND risk_score < 30
UNION ALL
SELECT
    '30-40' AS score_range,
    COUNT(*) AS count,
    ROUND(AVG(risk_score)::NUMERIC, 2) AS avg_score,
    MIN(risk_score) AS min_score,
    MAX(risk_score) AS max_score
FROM view_politician_risk_summary
WHERE risk_score >= 30 AND risk_score < 40
UNION ALL
SELECT
    '40-50' AS score_range,
    COUNT(*) AS count,
    ROUND(AVG(risk_score)::NUMERIC, 2) AS avg_score,
    MIN(risk_score) AS min_score,
    MAX(risk_score) AS max_score
FROM view_politician_risk_summary
WHERE risk_score >= 40 AND risk_score < 50
UNION ALL
SELECT
    '50-60' AS score_range,
    COUNT(*) AS count,
    ROUND(AVG(risk_score)::NUMERIC, 2) AS avg_score,
    MIN(risk_score) AS min_score,
    MAX(risk_score) AS max_score
FROM view_politician_risk_summary
WHERE risk_score >= 50 AND risk_score < 60
UNION ALL
SELECT
    '60-70' AS score_range,
    COUNT(*) AS count,
    ROUND(AVG(risk_score)::NUMERIC, 2) AS avg_score,
    MIN(risk_score) AS min_score,
    MAX(risk_score) AS max_score
FROM view_politician_risk_summary
WHERE risk_score >= 60 AND risk_score < 70
UNION ALL
SELECT
    '70-100' AS score_range,
    COUNT(*) AS count,
    ROUND(AVG(risk_score)::NUMERIC, 2) AS avg_score,
    MIN(risk_score) AS min_score,
    MAX(risk_score) AS max_score
FROM view_politician_risk_summary
WHERE risk_score >= 70
ORDER BY min_score;

\copy (SELECT * FROM tmp_risk_scores) TO 'service.data.impl/sample-data/validate_risk_scores.csv' WITH (FORMAT CSV, HEADER);
DROP VIEW tmp_risk_scores;


-- ============================================================================
-- 6. Gini Coefficient Calculation (Distribution Evenness)
-- ============================================================================

CREATE TEMP VIEW tmp_risk_gini AS
WITH scores AS (
    SELECT
        risk_score,
        ROW_NUMBER() OVER (ORDER BY risk_score) AS rank,
        COUNT(*) OVER () AS total_count
    FROM view_politician_risk_summary
),
cumulative AS (
    SELECT
        rank,
        risk_score,
        total_count,
        SUM(risk_score) OVER (ORDER BY rank) AS cumulative_score,
        SUM(risk_score) OVER () AS total_score
    FROM scores
)
SELECT
    'Gini Coefficient' AS metric,
    ROUND(
        (1 - (2 * SUM((total_count - rank + 0.5) * risk_score) / (total_count * total_score)))::NUMERIC,
        4
    ) AS value,
    CASE
        WHEN (1 - (2 * SUM((total_count - rank + 0.5) * risk_score) / (total_count * total_score))) < 0.4
        THEN '✓ ACCEPTABLE (< 0.4)'
        ELSE '✗ NEEDS IMPROVEMENT (>= 0.4)'
    END AS assessment
FROM cumulative
GROUP BY total_count, total_score;

\copy (SELECT * FROM tmp_risk_gini) TO 'service.data.impl/sample-data/validate_risk_gini.csv' WITH (FORMAT CSV, HEADER);
DROP VIEW tmp_risk_gini;
