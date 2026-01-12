-- Risk Score Gini Coefficient Calculation
-- Part of validation for v1.48 threshold changes

\pset pager off
\set ON_ERROR_STOP on

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
