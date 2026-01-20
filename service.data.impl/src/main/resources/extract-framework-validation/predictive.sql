-- predictive.sql
-- Framework 4: Predictive Intelligence Validation Test Cases
-- Purpose: Extract validation datasets for risk prediction and forecasting

\echo ''
\echo '==================================================================='
\echo '=== FRAMEWORK 4: PREDICTIVE INTELLIGENCE VALIDATION             ==='
\echo '==================================================================='
\echo ''

-- ============================================================================
-- TEST 4.1: Resignation Risk Prediction Signals
-- Expected Outcome: Identify HIGH_RISK, MODERATE_RISK patterns using raw vote_data
-- Sample Size: 40 cases
-- ============================================================================
\echo '>>> Test Case 4.1: Resignation Risk - Declining Engagement Signals'
\echo '>>> Expected Outcome: Identify HIGH_RISK, MODERATE_RISK patterns'

COPY (
    WITH monthly_votes AS (
        SELECT 
            embedded_id_intressent_id AS person_id,
            first_name,
            last_name,
            party,
            DATE_TRUNC('month', vote_date) AS month,
            COUNT(*) AS total_votes,
            COUNT(*) FILTER (WHERE vote = 'FRÅNVARANDE') AS absent_votes
        FROM vote_data
        WHERE vote_date >= DATE_TRUNC('month', CURRENT_DATE) - cia_get_lookback_interval('medium')
        GROUP BY 1, 2, 3, 4, 5
        HAVING COUNT(*) > 0
    ),
    monthly_rates AS (
        SELECT 
            *,
            (absent_votes::FLOAT / total_votes) * 100 AS absence_rate,
            ROW_NUMBER() OVER (PARTITION BY person_id ORDER BY month DESC) AS month_rank
        FROM monthly_votes
    ),
    trend_calc AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            AVG(absence_rate) AS avg_absence,
            COUNT(*) AS months_tracked,
            -- Calculate trend as difference between recent 3m and prior 3m
            AVG(CASE WHEN month_rank <= 3 THEN absence_rate END) AS recent_avg,
            AVG(CASE WHEN month_rank BETWEEN 4 AND 6 THEN absence_rate END) AS prior_avg
        FROM monthly_rates
        GROUP BY 1, 2, 3, 4
    ),
    final_risk AS (
        SELECT 
            *,
            COALESCE(recent_avg - prior_avg, 0) AS absence_trend,
            cia_classify_risk_level(recent_avg, COALESCE(recent_avg - prior_avg, 0)) AS risk_level
        FROM trend_calc
        WHERE months_tracked >= 3
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        ROUND(avg_absence::numeric, 2) AS avg_absence_rate,
        ROUND(absence_trend::numeric, 2) AS absence_trend,
        0.0 AS effectiveness_trend,
        ROUND(recent_avg::numeric, 2) AS smoothed_3month_absence,
        months_tracked,
        risk_level AS expected_detection,
        'predictive_resignation_risk' AS test_case,
        'PASS' AS validation_label
    FROM final_risk
    WHERE risk_level IN ('HIGH_RESIGNATION_RISK', 'MODERATE_RESIGNATION_RISK')
    ORDER BY avg_absence DESC
    LIMIT 40
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/predictive/test_4_1_resignation_prediction.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected resignation risk prediction'
\echo ''

-- ============================================================================
-- TEST 4.1b: Politician Risk Summary (Enhanced Multi-Violation Analysis)
-- Expected Outcome: Identify high-risk politicians with multiple violation types
-- Sample Size: 45 cases
-- ============================================================================
\echo '>>> Test Case 4.1b: Politician Risk Profile - Multi-Violation Patterns'
\echo '>>> Expected Outcome: Identify high-risk politicians with multiple violations'

COPY (
    WITH risk_profiles AS (
        SELECT 
            person_id,
            first_name,
            last_name,
            party,
            status,
            total_violations,
            absenteeism_violations,
            effectiveness_violations,
            discipline_violations,
            productivity_violations,
            collaboration_violations,
            annual_absence_rate,
            annual_rebel_rate,
            annual_vote_count,
            documents_last_year,
            risk_score,
            risk_level,
            risk_assessment,
            CASE 
                WHEN total_violations >= cia_get_config_value('risk_violations_multi')::INTEGER 
                     AND (absenteeism_violations > 0 AND effectiveness_violations > 0) THEN 'MULTI_DIMENSION_RISK'
                WHEN total_violations >= cia_get_config_value('risk_violations_extreme')::INTEGER THEN 'EXTREME_VIOLATION_RISK'
                WHEN risk_score >= cia_get_config_value('risk_score_critical')::NUMERIC 
                     AND total_violations >= cia_get_config_value('risk_violations_moderate')::INTEGER THEN 'HIGH_RISK_PROFILE'
                WHEN annual_absence_rate > cia_get_config_value('rebel_high_independence')::NUMERIC 
                     AND annual_rebel_rate > cia_get_config_value('rebel_moderate_independence')::NUMERIC THEN 'DUAL_BEHAVIORAL_RISK'
                ELSE 'MODERATE_RISK'
            END AS expected_risk_classification,
            CASE WHEN absenteeism_violations > 0 THEN 1 ELSE 0 END +
            CASE WHEN effectiveness_violations > 0 THEN 1 ELSE 0 END +
            CASE WHEN discipline_violations > 0 THEN 1 ELSE 0 END +
            CASE WHEN productivity_violations > 0 THEN 1 ELSE 0 END +
            CASE WHEN collaboration_violations > 0 THEN 1 ELSE 0 END AS violation_dimension_count
        FROM view_politician_risk_summary
        WHERE total_violations > 0
          AND status = 'Tjänstgörande riksdagsledamot'
    )
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        total_violations,
        violation_dimension_count,
        absenteeism_violations,
        effectiveness_violations,
        discipline_violations,
        ROUND(annual_absence_rate, 2) AS annual_absence_rate,
        ROUND(annual_rebel_rate, 2) AS annual_rebel_rate,
        annual_vote_count,
        documents_last_year,
        ROUND(risk_score, 2) AS risk_score,
        risk_level,
        expected_risk_classification AS expected_detection,
        'predictive_risk_profile' AS test_case,
        CASE 
            WHEN expected_risk_classification IN ('MULTI_DIMENSION_RISK', 'EXTREME_VIOLATION_RISK', 'HIGH_RISK_PROFILE', 'DUAL_BEHAVIORAL_RISK') THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM risk_profiles
    WHERE expected_risk_classification IN ('MULTI_DIMENSION_RISK', 'EXTREME_VIOLATION_RISK', 'HIGH_RISK_PROFILE', 'DUAL_BEHAVIORAL_RISK')
    ORDER BY 
        CASE expected_risk_classification
            WHEN 'EXTREME_VIOLATION_RISK' THEN 1
            WHEN 'MULTI_DIMENSION_RISK' THEN 2
            WHEN 'HIGH_RISK_PROFILE' THEN 3
            WHEN 'DUAL_BEHAVIORAL_RISK' THEN 4
        END,
        risk_score DESC,
        total_violations DESC
    LIMIT 45
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/predictive/test_4_1b_politician_risk_profiles.csv' WITH CSV HEADER;

\echo '>>> Exported 45 cases with multi-dimensional risk profiles'
\echo ''

-- ============================================================================
-- TEST 4.2: Coalition Stress Prediction
-- Expected Outcome: Detect coalition stress with ≥78% accuracy
-- Sample Size: 50 cases
-- ============================================================================
\echo '>>> Test Case 4.2: Coalition Stress - Alignment Degradation Signals'
\echo '>>> Expected Outcome: Detect coalition stress'

COPY (
    WITH party_ballot_counts AS (
        SELECT
            party,
            embedded_id_ballot_id as ballot_id,
            vote,
            COUNT(*) as cnt
        FROM vote_data
        WHERE vote IN ('JA', 'NEJ')
          AND vote_date >= DATE_TRUNC('month', CURRENT_DATE) - cia_get_lookback_interval('medium')
        GROUP BY party, embedded_id_ballot_id, vote
    ),
    party_ballot_majority AS (
        SELECT
            party,
            ballot_id,
            vote
        FROM (
            SELECT
                party,
                ballot_id,
                vote,
                ROW_NUMBER() OVER(PARTITION BY party, ballot_id ORDER BY cnt DESC) as rn
            FROM party_ballot_counts
        ) sub
        WHERE rn = 1
    ),
    pair_stats AS (
        SELECT
            t1.party as party_1,
            t2.party as party_2,
            COUNT(*) as shared_votes,
            SUM(CASE WHEN t1.vote = t2.vote THEN 1 ELSE 0 END) as aligned_votes
        FROM party_ballot_majority t1
        JOIN party_ballot_majority t2 ON t1.ballot_id = t2.ballot_id AND t1.party < t2.party
        GROUP BY t1.party, t2.party
    )
    SELECT
        party_1,
        party_2,
        ROUND((aligned_votes::numeric / NULLIF(shared_votes,0)) * 100, 2) as alignment_rate,
        shared_votes,
        CASE
            WHEN (aligned_votes::numeric / NULLIF(shared_votes,0)) > 0.9 THEN 'High'
            WHEN (aligned_votes::numeric / NULLIF(shared_votes,0)) > 0.5 THEN 'Medium'
            ELSE 'Low'
        END as coalition_likelihood,
        'N/A' as bloc_relationship,
        cia_classify_coalition_alignment(
            (aligned_votes::numeric / NULLIF(shared_votes,0)) * 100
        ) as expected_detection,
        'Majority Vote Alignment' as test_case,
        'VALIDATED' as validation_label
    FROM pair_stats
    -- Ensure we get some variety
    ORDER BY alignment_rate ASC
    LIMIT cia_get_config_value('sample_size_default')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/predictive/test_4_2_coalition_stress.csv' WITH CSV HEADER;

\echo '>>> Exported cases with expected coalition stress prediction'
\echo ''

-- ============================================================================
-- TEST 4.3: Pre-Election Activity Spike Detection (Advanced View v1.61)
-- Expected Outcome: Identify unusual activity spikes before elections
-- Sample Size: 50 cases
-- ============================================================================
\echo '>>> Test Case 4.3: Pre-Election Activity - Quarterly Activity Pattern Analysis'
\echo '>>> Expected Outcome: Detect significant activity increases before elections'

COPY (
    SELECT 
        person_id,
        first_name,
        last_name,
        party,
        election_year,
        quarter,
        quarters_to_election,
        ROUND(quarterly_decisions::numeric, 2) AS quarterly_decisions,
        ROUND(quarterly_absence_rate::numeric, 2) AS quarterly_absence_rate,
        ROUND(baseline_decisions::numeric, 2) AS baseline_decisions,
        ROUND(baseline_absence::numeric, 2) AS baseline_absence,
        ROUND(decision_change_pct::numeric, 2) AS decision_change_pct,
        ROUND(absence_change_pct::numeric, 2) AS absence_change_pct,
        CASE 
            WHEN decision_change_pct > 20 AND quarters_to_election <= 2 THEN 'PRE_ELECTION_SPIKE'
            WHEN absence_change_pct < -10 AND quarters_to_election <= 2 THEN 'ATTENDANCE_BOOST'
            WHEN decision_change_pct < -20 AND quarters_to_election <= 2 THEN 'PRE_ELECTION_DECLINE'
            ELSE 'NORMAL_PATTERN'
        END AS expected_pattern,
        'pre_election_activity_analysis' AS test_case,
        CASE 
            WHEN ABS(decision_change_pct) >= 15 OR ABS(absence_change_pct) >= 10 THEN 'PASS'
            ELSE 'BASELINE'
        END AS validation_label
    FROM view_riksdagen_pre_election_quarterly_activity
    WHERE election_year >= 2010  -- Focus on recent elections
      AND quarters_to_election <= 4  -- Last year before election
      AND (ABS(decision_change_pct) > 0 OR ABS(absence_change_pct) > 0)
    ORDER BY ABS(decision_change_pct) DESC
    LIMIT cia_get_config_value('sample_size_default')::INTEGER
) TO '/workspaces/cia/service.data.impl/sample-data/framework-validation/predictive/test_4_3_pre_election_activity.csv' WITH CSV HEADER;

\echo '>>> Exported pre-election activity patterns'
\echo ''
