-- view_decision_temporal_trends v1.45
-- Fix: Add committee_referral_decisions pattern to capture =utskottet, utskottet, =utskott values
-- Analysis: 7,049 records were falling into other_decisions instead of proper classification
-- Pattern: UPPER(chamber) ~~ '%UTSKOTT%' catches =utskottet (6501), = utskottet (517), utskottet (12), =utskott (19)

DROP VIEW IF EXISTS view_decision_temporal_trends CASCADE;

CREATE VIEW view_decision_temporal_trends AS
WITH daily_decisions AS (
    SELECT 
        dd.made_public_date AS decision_day,
        COUNT(*) AS daily_decisions,
        ROUND(100.0 * COUNT(*) FILTER (
            WHERE UPPER(dpd.chamber) ~~ '%BIFALL%' 
               OR UPPER(dpd.chamber) ~~ '%GODKÄNT%' 
               OR UPPER(dpd.chamber) ~~ '%BIFALLA%'
        )::NUMERIC / NULLIF(COUNT(*), 0)::NUMERIC, 2) AS daily_approval_rate,
        COUNT(*) FILTER (
            WHERE UPPER(dpd.chamber) ~~ '%BIFALL%' 
               OR UPPER(dpd.chamber) ~~ '%GODKÄNT%' 
               OR UPPER(dpd.chamber) ~~ '%BIFALLA%'
        ) AS approved_decisions,
        COUNT(*) FILTER (
            WHERE UPPER(dpd.chamber) ~~ '%AVSLAG%' 
               OR UPPER(dpd.chamber) ~~ '%AVSLÅ%'
        ) AS rejected_decisions,
        COUNT(*) FILTER (
            WHERE UPPER(dpd.chamber) ~~ '%ÅTERFÖRVISNING%' 
               OR UPPER(dpd.chamber) ~~ '%ÅTERFÖRVISA%'
        ) AS referred_back_decisions,
        -- NEW: Committee referral decisions (captures =utskottet, = utskottet, utskottet, =utskott)
        COUNT(*) FILTER (
            WHERE UPPER(dpd.chamber) ~~ '%UTSKOTT%'
              AND UPPER(dpd.chamber) !~~ '%ÅTERFÖRVISNING%'
        ) AS committee_referral_decisions
    FROM document_proposal_data dpd
    JOIN document_proposal_container dpc ON dpc.proposal_document_proposal_c_0 = dpd.hjid
    JOIN document_status_container dsc ON dsc.document_proposal_document_s_0 = dpc.hjid
    JOIN document_data dd ON dd.id = dsc.document_document_status_con_0
    WHERE dd.made_public_date IS NOT NULL
      AND dd.made_public_date >= CURRENT_DATE - INTERVAL '5 years'
      AND dpd.chamber IS NOT NULL
      AND LENGTH(dpd.chamber) >= 6
      AND LENGTH(dpd.chamber) <= 29
    GROUP BY dd.made_public_date
)
SELECT 
    decision_day,
    daily_decisions,
    daily_approval_rate,
    approved_decisions,
    rejected_decisions,
    referred_back_decisions,
    committee_referral_decisions,
    ROUND(AVG(daily_decisions) OVER (ORDER BY decision_day ROWS BETWEEN 6 PRECEDING AND CURRENT ROW), 2) AS ma_7day_decisions,
    ROUND(AVG(daily_decisions) OVER (ORDER BY decision_day ROWS BETWEEN 29 PRECEDING AND CURRENT ROW), 2) AS ma_30day_decisions,
    ROUND(AVG(daily_decisions) OVER (ORDER BY decision_day ROWS BETWEEN 89 PRECEDING AND CURRENT ROW), 2) AS ma_90day_decisions,
    ROUND(AVG(daily_approval_rate) OVER (ORDER BY decision_day ROWS BETWEEN 29 PRECEDING AND CURRENT ROW), 2) AS ma_30day_approval_rate,
    LAG(daily_decisions, 365) OVER (ORDER BY decision_day) AS decisions_last_year,
    (daily_decisions - LAG(daily_decisions, 365) OVER (ORDER BY decision_day)) AS yoy_decisions_change,
    ROUND(100.0 * (daily_decisions - LAG(daily_decisions, 365) OVER (ORDER BY decision_day))::NUMERIC 
        / NULLIF(LAG(daily_decisions, 365) OVER (ORDER BY decision_day), 0)::NUMERIC, 2) AS yoy_decisions_change_pct,
    EXTRACT(YEAR FROM decision_day) AS decision_year,
    EXTRACT(MONTH FROM decision_day) AS decision_month,
    EXTRACT(WEEK FROM decision_day) AS decision_week,
    EXTRACT(DOW FROM decision_day) AS decision_day_of_week,
    CASE
        WHEN EXTRACT(MONTH FROM decision_day) IN (7, 8) THEN 'Summer Recess'
        WHEN EXTRACT(MONTH FROM decision_day) IN (12, 1) THEN 'Winter Recess'
        WHEN EXTRACT(MONTH FROM decision_day) IN (2, 3) THEN 'Spring Session'
        WHEN EXTRACT(MONTH FROM decision_day) IN (9, 10, 11) THEN 'Autumn Session'
        WHEN EXTRACT(MONTH FROM decision_day) IN (4, 5, 6) THEN 'Late Spring Session'
        ELSE 'Active Session'
    END AS parliamentary_period,
    'Q' || EXTRACT(QUARTER FROM decision_day)::TEXT || ' ' || EXTRACT(YEAR FROM decision_day)::TEXT AS decision_quarter
FROM daily_decisions
ORDER BY decision_day DESC;
