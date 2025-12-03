-- view_ministry_decision_impact v1.45
-- Fix: Add committee_referral_proposals pattern to capture =utskottet, utskottet, =utskott values
-- Analysis: 7,049 records were falling into other_decisions instead of proper classification
-- Pattern: UPPER(chamber) ~~ '%UTSKOTT%' catches =utskottet (6501), = utskottet (517), utskottet (12), =utskott (19)

DROP VIEW IF EXISTS view_ministry_decision_impact CASCADE;

CREATE VIEW view_ministry_decision_impact AS
SELECT 
    dd.org AS ministry_code,
    dpd.committee,
    dpd.decision_type,
    DATE_TRUNC('quarter', dd.made_public_date) AS decision_quarter,
    EXTRACT(YEAR FROM dd.made_public_date) AS decision_year,
    EXTRACT(QUARTER FROM dd.made_public_date) AS quarter_num,
    COUNT(*) AS total_proposals,
    COUNT(*) FILTER (
        WHERE UPPER(dpd.chamber) ~~ '%BIFALL%' 
           OR UPPER(dpd.chamber) ~~ '%GODKÄNT%' 
           OR UPPER(dpd.chamber) ~~ '%BIFALLA%'
    ) AS approved_proposals,
    COUNT(*) FILTER (
        WHERE UPPER(dpd.chamber) ~~ '%AVSLAG%' 
           OR UPPER(dpd.chamber) ~~ '%AVSLÅ%'
    ) AS rejected_proposals,
    COUNT(*) FILTER (
        WHERE UPPER(dpd.chamber) ~~ '%ÅTERFÖRVISNING%' 
           OR UPPER(dpd.chamber) ~~ '%ÅTERFÖRVISA%'
    ) AS referred_back_proposals,
    -- NEW: Committee referral proposals (captures =utskottet, = utskottet, utskottet, =utskott)
    COUNT(*) FILTER (
        WHERE UPPER(dpd.chamber) ~~ '%UTSKOTT%'
          AND UPPER(dpd.chamber) !~~ '%ÅTERFÖRVISNING%'
    ) AS committee_referral_proposals,
    -- UPDATED: Exclude committee referrals from other_decisions
    COUNT(*) FILTER (
        WHERE UPPER(dpd.chamber) !~~ '%BIFALL%'
          AND UPPER(dpd.chamber) !~~ '%AVSLAG%'
          AND UPPER(dpd.chamber) !~~ '%GODKÄNT%'
          AND UPPER(dpd.chamber) !~~ '%BIFALLA%'
          AND UPPER(dpd.chamber) !~~ '%AVSLÅ%'
          AND UPPER(dpd.chamber) !~~ '%ÅTERFÖRVISNING%'
          AND UPPER(dpd.chamber) !~~ '%ÅTERFÖRVISA%'
          AND UPPER(dpd.chamber) !~~ '%UTSKOTT%'
    ) AS other_decisions,
    ROUND(100.0 * COUNT(*) FILTER (
        WHERE UPPER(dpd.chamber) ~~ '%BIFALL%' 
           OR UPPER(dpd.chamber) ~~ '%GODKÄNT%' 
           OR UPPER(dpd.chamber) ~~ '%BIFALLA%'
    )::NUMERIC / NULLIF(COUNT(*), 0)::NUMERIC, 2) AS approval_rate,
    ROUND(100.0 * COUNT(*) FILTER (
        WHERE UPPER(dpd.chamber) ~~ '%AVSLAG%' 
           OR UPPER(dpd.chamber) ~~ '%AVSLÅ%'
    )::NUMERIC / NULLIF(COUNT(*), 0)::NUMERIC, 2) AS rejection_rate,
    -- NEW: Committee referral rate
    ROUND(100.0 * COUNT(*) FILTER (
        WHERE UPPER(dpd.chamber) ~~ '%UTSKOTT%'
          AND UPPER(dpd.chamber) !~~ '%ÅTERFÖRVISNING%'
    )::NUMERIC / NULLIF(COUNT(*), 0)::NUMERIC, 2) AS committee_referral_rate,
    MIN(dd.made_public_date) AS earliest_proposal_date,
    MAX(dd.made_public_date) AS latest_proposal_date
FROM document_data dd
JOIN document_status_container dsc ON dsc.document_document_status_con_0 = dd.id
JOIN document_proposal_container dpc ON dpc.hjid = dsc.document_proposal_document_s_0
JOIN document_proposal_data dpd ON dpd.hjid = dpc.proposal_document_proposal_c_0
WHERE dd.document_type = 'prop'
  AND dd.org IS NOT NULL
  AND dpd.committee IS NOT NULL
  AND dpd.chamber IS NOT NULL
  AND dd.made_public_date IS NOT NULL
  AND LENGTH(dpd.chamber) >= 6
  AND LENGTH(dpd.chamber) <= 29
GROUP BY dd.org, dpd.committee, dpd.decision_type, 
         DATE_TRUNC('quarter', dd.made_public_date),
         EXTRACT(YEAR FROM dd.made_public_date),
         EXTRACT(QUARTER FROM dd.made_public_date)
HAVING COUNT(*) > 0
ORDER BY EXTRACT(YEAR FROM dd.made_public_date) DESC,
         EXTRACT(QUARTER FROM dd.made_public_date) DESC,
         dd.org, dpd.committee;
