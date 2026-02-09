# View Improvement Recommendations
## Action Plan Based on Sample Data Analysis

**Priority Classification**: CRITICAL → HIGH → MEDIUM → LOW  
**Implementation Timeline**: Immediate (7 days) → Short-term (30 days) → Medium-term (90 days)  
**Generated**: 2026-02-09  
**Based on**: POLITICAL_INTELLIGENCE_ANALYSIS_REPORT.md

---

## 🚨 CRITICAL PRIORITY (Immediate Action Required)

### 1. Fix 2025-2026 Voting Data Pipeline Failure

**Problem**: All parties show 0.00% participation rate in 2025-2026 quarters

**Evidence**:
```csv
# From distribution_party_momentum.csv
party,year,quarter,period,participation_rate,momentum,trend_direction
S,2026,1,2026-Q1,0.00,0.00,STABLE,VERY_STABLE
M,2026,1,2026-Q1,0.00,0.00,STABLE,VERY_STABLE
... (all parties identical)
```

**Affected Views**:
- `view_riksdagen_party_momentum_analysis`
- `view_riksdagen_crisis_resilience_indicators`
- `view_riksdagen_voting_anomaly_detection`
- `view_election_proximity_trends`

**Root Cause Analysis**:
1. **Date Filter Issue**: Views may use restrictive 5-year filter excluding recent data
2. **Data Pipeline**: vote_data table may be missing 2025-2026 records
3. **API Change**: Riksdag API format may have changed

**Action Items**:
```sql
-- Diagnostic Query 1: Check vote_data for recent records
SELECT 
    EXTRACT(YEAR FROM vote_date) as year,
    EXTRACT(QUARTER FROM vote_date) as quarter,
    COUNT(*) as vote_count,
    COUNT(DISTINCT party) as parties,
    COUNT(DISTINCT ballot_id) as ballots,
    COUNT(DISTINCT person_id) as politicians
FROM vote_data
WHERE vote_date >= '2024-01-01'
GROUP BY EXTRACT(YEAR FROM vote_date), EXTRACT(QUARTER FROM vote_date)
ORDER BY year, quarter;

-- Expected: Should show data for 2024, 2025, 2026
-- If empty: Data pipeline failure
-- If populated: Date filter issue in views
```

**Fix Strategy**:
1. If data exists: Apply 25-year date filter fix (db-changelog-1.74.xml pattern)
2. If data missing: Investigate ETL pipeline for 2025-2026 period
3. Verify Riksdag API connectivity and data ingestion logs

**Impact**: **SEVERE** - Affects real-time political intelligence, momentum analysis, crisis detection

**Estimated Effort**: 4-8 hours (investigation + fix)

---

### 2. Resolve Motion/Proposition Zero Count Issue

**Problem**: All parties show 0 motions and 0 propositions in performance metrics

**Evidence**:
```csv
# From distribution_party_performance.csv
party,documents_last_year,motions_last_year,propositions_last_year
M,1299,0,0
S,850,0,0
... (all parties show 0 motions/propositions)
```

**Affected Views**:
- `view_riksdagen_party_performance_metrics`

**Root Cause Analysis**:
1. **Document Type Taxonomy**: document_type values may not match expected 'mot'/'prop'
2. **Date Filter**: 5-year filter may exclude historical motions
3. **JOIN Logic**: View may not correctly link documents to parties

**Action Items**:
```sql
-- Diagnostic Query 1: Check document_type taxonomy
SELECT 
    document_type,
    COUNT(*) as count,
    MIN(made_public_date) as earliest,
    MAX(made_public_date) as latest
FROM document_data
WHERE made_public_date >= '2020-01-01'
GROUP BY document_type
ORDER BY count DESC
LIMIT 20;

-- Diagnostic Query 2: Check for motion/proposition patterns
SELECT 
    document_type,
    EXTRACT(YEAR FROM made_public_date) as year,
    COUNT(*) as count
FROM document_data
WHERE (
    document_type LIKE '%mot%' OR 
    document_type LIKE '%prop%' OR
    document_type IN ('motion', 'proposition', 'Motion', 'Proposition')
)
  AND made_public_date >= '2002-01-01'
GROUP BY document_type, EXTRACT(YEAR FROM made_public_date)
ORDER BY year DESC, count DESC;
```

**Fix Strategy**:
1. Identify correct document_type values for motions/propositions
2. Update view WHERE clauses to match actual taxonomy
3. Extend date filter if needed (5y → 25y)
4. Verify person-to-document linkage logic

**Impact**: **HIGH** - Party performance analysis incomplete without motion/proposition tracking

**Estimated Effort**: 2-4 hours

---

## 🔴 HIGH PRIORITY (Within 7 Days)

### 3. Extend Date Filters from 5 Years to 25 Years

**Problem**: Restrictive 5-year filters exclude 19 years of historical data (2002-2020)

**Affected Views** (from previous analysis):
1. `view_riksdagen_committee_role_member` (line 9251)
2. `view_riksdagen_crisis_resilience_indicators` (lines 9293, 9334)
3. `view_riksdagen_goverment_role_member` (line 10160)
4. `view_riksdagen_party_role_member` (line 11770)

**Already Fixed**:
- ✅ `view_riksdagen_coalition_alignment_matrix` (db-changelog-1.74.xml)
- ✅ `view_decision_temporal_trends` (db-changelog-1.74.xml)

**Impact Analysis**:
- **Current**: Shows 2021-2026 only (5 years = 20% of data)
- **After Fix**: Shows 2002-2026 (24 years = 100% of data)
- **Example**: committee_role_member has 19,072 rows with 5y filter → ~95,000 expected with 25y

**Action Items**:
```sql
-- Template for each view fix
CREATE OR REPLACE VIEW view_riksdagen_VIEWNAME AS
-- ... existing view logic ...
WHERE made_public_date >= (CURRENT_DATE - '25 years'::interval)  -- Changed from 5 years
-- ... rest of view ...
```

**Implementation**:
1. Create additional changesets in db-changelog-1.74.xml
2. Follow existing pattern from changesets 001-002
3. Include rollback scripts
4. Test each view after update

**Impact**: **HIGH** - Unlocks 24 years of historical political intelligence

**Estimated Effort**: 4-6 hours (4 views × 1-1.5 hours each)

---

### 4. Fix Coalition Alignment Matrix Data Scarcity

**Problem**: Minimal coalition alignment data (109 bytes file)

**Affected Views**:
- `view_riksdagen_coalition_alignment_matrix` (partially fixed)
- `view_election_cycle_network_analysis` (depends on coalition matrix)

**Root Cause**: Date filter already extended to 25 years in db-changelog-1.74.xml, but view may need:
1. **Minimum Threshold**: Reduce shared_votes threshold from 10 to 5
2. **Time Periods**: Add quarterly/annual aggregations for better temporal resolution
3. **Party Combinations**: Ensure all 8 major parties captured

**Action Items**:
```sql
-- Diagnostic Query: Check coalition alignment data availability
SELECT 
    party1,
    party2,
    shared_votes,
    alignment_rate,
    coalition_likelihood,
    first_year,
    last_year
FROM view_riksdagen_coalition_alignment_matrix
ORDER BY shared_votes DESC, alignment_rate DESC
LIMIT 50;

-- Expected: Should show multiple party pairs with 2002-2026 coverage
-- If sparse: Reduce thresholds or adjust filtering logic
```

**Fix Strategy**:
1. Review alignment_matrix view definition
2. Reduce minimum shared_votes threshold (10 → 5)
3. Consider adding quarterly temporal dimension
4. Verify all 28 party combinations represented (8 parties choose 2)

**Impact**: **HIGH** - Coalition analysis is core political intelligence function

**Estimated Effort**: 3-4 hours

---

### 5. Enhance Voting Anomaly Detection

**Problem**: Minimal anomaly data (61 bytes file)

**Affected Views**:
- `view_riksdagen_voting_anomaly_detection`
- `distribution_voting_anomaly_classification`
- `distribution_anomaly_by_party`

**Root Cause**: Date filter already fixed to 20 years in db-changelog-1.73.xml, but may need:
1. **Anomaly Thresholds**: Current thresholds may be too strict
2. **Baseline Period**: 20 years may still be insufficient for rare events
3. **Algorithm Tuning**: Anomaly detection parameters need adjustment

**Action Items**:
```sql
-- Diagnostic Query: Check voting pattern distribution
SELECT 
    party,
    EXTRACT(YEAR FROM vote_date) as year,
    COUNT(*) as total_votes,
    COUNT(*) FILTER (WHERE vote = 'Ja') as yes_votes,
    COUNT(*) FILTER (WHERE vote = 'Nej') as no_votes,
    COUNT(*) FILTER (WHERE vote = 'Avstå') as abstain_votes,
    COUNT(*) FILTER (WHERE vote = 'Frånvarande') as absent_votes,
    ROUND(100.0 * COUNT(*) FILTER (WHERE vote != 'Ja' AND vote != 'Nej') / COUNT(*), 2) as anomaly_rate
FROM vote_data
WHERE vote_date >= '2002-01-01'
GROUP BY party, EXTRACT(YEAR FROM vote_date)
HAVING COUNT(*) >= 100
ORDER BY year DESC, anomaly_rate DESC;
```

**Fix Strategy**:
1. Review anomaly detection algorithm in view definition
2. Adjust statistical thresholds (e.g., 3σ → 2σ for rare event detection)
3. Add temporal context (pre-election vs post-election behavior)
4. Consider party-specific baselines (different parties have different voting patterns)

**Impact**: **HIGH** - Anomaly detection is critical for identifying unusual political behavior

**Estimated Effort**: 4-6 hours (algorithm review + tuning)

---

## 🟡 MEDIUM PRIORITY (Within 30 Days)

### 6. Validate and Refresh All 30 Materialized Views

**Problem**: Previous analysis indicated 30 materialized views may be unpopulated

**Affected Infrastructure**:
- All `mv_*` and materialized `view_riksdagen_*` views
- Performance optimization infrastructure

**Action Items**:
```sql
-- Diagnostic Query: Check materialized view status
SELECT 
    schemaname,
    matviewname,
    ispopulated,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||matviewname)) as size,
    CASE 
        WHEN ispopulated AND pg_total_relation_size(schemaname||'.'||matviewname) > 0 
        THEN 'HEALTHY'
        WHEN ispopulated AND pg_total_relation_size(schemaname||'.'||matviewname) = 0 
        THEN 'EMPTY'
        ELSE 'UNPOPULATED'
    END as status
FROM pg_matviews 
WHERE schemaname = 'public'
ORDER BY 
    CASE 
        WHEN ispopulated THEN 1 
        ELSE 0 
    END,
    matviewname;
```

**Fix Strategy**:
1. Run REFRESH MATERIALIZED VIEW for each unpopulated MV
2. Investigate empty MVs for root cause (date filters, dependencies)
3. Set up automated refresh schedule (daily at 2 AM)
4. Add monitoring for MV freshness

**Impact**: **MEDIUM** - Performance optimization, but views still function without MVs

**Estimated Effort**: 3-4 hours (validation + refresh + monitoring setup)

---

### 7. Create New Analytical Views

#### 7.1 Coalition Stability Tracker (24-Year Historical)

**Purpose**: Track coalition voting cohesion from 2002-2026

**Implementation**:
```sql
CREATE VIEW view_riksdagen_coalition_stability_24y AS
WITH coalition_votes AS (
    SELECT 
        v1.party as party1,
        v2.party as party2,
        v1.ballot_id,
        v1.vote_date,
        v1.vote as party1_vote,
        v2.vote as party2_vote,
        CASE WHEN v1.vote = v2.vote THEN 1 ELSE 0 END as aligned
    FROM vote_data v1
    JOIN vote_data v2 ON v1.ballot_id = v2.ballot_id AND v1.party < v2.party
    WHERE v1.vote_date >= (CURRENT_DATE - '25 years'::interval)
      AND v1.vote IS NOT NULL 
      AND v2.vote IS NOT NULL
)
SELECT 
    party1,
    party2,
    EXTRACT(YEAR FROM vote_date) as year,
    COUNT(*) as total_votes,
    SUM(aligned) as aligned_votes,
    ROUND(100.0 * SUM(aligned) / COUNT(*), 2) as alignment_rate,
    CASE 
        WHEN ROUND(100.0 * SUM(aligned) / COUNT(*), 2) >= 80 THEN 'STRONG_COALITION'
        WHEN ROUND(100.0 * SUM(aligned) / COUNT(*), 2) >= 60 THEN 'MODERATE_COALITION'
        ELSE 'WEAK_ALIGNMENT'
    END as coalition_strength,
    -- Add momentum indicator
    ROUND(
        100.0 * SUM(aligned) / COUNT(*) - 
        LAG(100.0 * SUM(aligned) / COUNT(*)) OVER (PARTITION BY party1, party2 ORDER BY EXTRACT(YEAR FROM vote_date)),
        2
    ) as annual_momentum
FROM coalition_votes
GROUP BY party1, party2, EXTRACT(YEAR FROM vote_date)
HAVING COUNT(*) >= 20
ORDER BY year DESC, alignment_rate DESC;
```

**Value**: Enables historical coalition pattern analysis, predictive coalition modeling

**Estimated Effort**: 6-8 hours (development + testing + documentation)

#### 7.2 Gender Representation Trends

**Purpose**: Track gender parity evolution across parties (2002-2026)

**Implementation**:
```sql
CREATE VIEW view_riksdagen_gender_trends_24y AS
WITH annual_gender AS (
    SELECT 
        p.party,
        EXTRACT(YEAR FROM a.from_date) as year,
        p.gender,
        COUNT(*) as member_count
    FROM person_data p
    JOIN assignment_data a ON p.id = a.intressent_id
    WHERE a.assignment_type = 'Riksdagsledamot'
      AND a.from_date >= (CURRENT_DATE - '25 years'::interval)
      AND p.gender IN ('MAN', 'KVINNA')
    GROUP BY p.party, EXTRACT(YEAR FROM a.from_date), p.gender
)
SELECT 
    party,
    year,
    SUM(CASE WHEN gender = 'KVINNA' THEN member_count ELSE 0 END) as female_count,
    SUM(CASE WHEN gender = 'MAN' THEN member_count ELSE 0 END) as male_count,
    SUM(member_count) as total_members,
    ROUND(100.0 * SUM(CASE WHEN gender = 'KVINNA' THEN member_count ELSE 0 END) / SUM(member_count), 2) as female_percentage,
    CASE 
        WHEN ROUND(100.0 * SUM(CASE WHEN gender = 'KVINNA' THEN member_count ELSE 0 END) / SUM(member_count), 2) >= 50 
        THEN 'PARITY_ACHIEVED'
        WHEN ROUND(100.0 * SUM(CASE WHEN gender = 'KVINNA' THEN member_count ELSE 0 END) / SUM(member_count), 2) >= 40 
        THEN 'NEAR_PARITY'
        WHEN ROUND(100.0 * SUM(CASE WHEN gender = 'KVINNA' THEN member_count ELSE 0 END) / SUM(member_count), 2) >= 30 
        THEN 'MODERATE_IMBALANCE'
        ELSE 'SIGNIFICANT_IMBALANCE'
    END as parity_status,
    -- Trend indicator
    ROUND(
        (100.0 * SUM(CASE WHEN gender = 'KVINNA' THEN member_count ELSE 0 END) / SUM(member_count)) - 
        LAG(100.0 * SUM(CASE WHEN gender = 'KVINNA' THEN member_count ELSE 0 END) / SUM(member_count)) 
            OVER (PARTITION BY party ORDER BY year),
        2
    ) as annual_change_pct
FROM annual_gender
GROUP BY party, year
ORDER BY year DESC, female_percentage DESC;
```

**Value**: Gender equality monitoring, recruitment pattern analysis, leadership diversity

**Estimated Effort**: 5-6 hours

#### 7.3 Crisis Response Effectiveness

**Purpose**: Analyze government response during crisis periods (2008, 2015, 2020-2021, 2022)

**Implementation**: Create view identifying crisis periods and measuring:
- Time-to-legislation (crisis event → first responsive bill)
- Legislative volume (crisis vs normal periods)
- Cross-party cooperation rates
- Emergency measure reversals

**Estimated Effort**: 8-10 hours (complex temporal logic + crisis period definition)

---

### 8. New Materialized Views for Performance

**Proposed MVs**:

1. **mv_annual_party_performance** (Refresh: Daily 2AM)
2. **mv_committee_productivity_annual** (Refresh: Daily 2AM)
3. **mv_politician_risk_monthly** (Refresh: Monthly on 1st)
4. **mv_gender_representation_annual** (Refresh: Daily 2AM)
5. **mv_coalition_alignment_quarterly** (Refresh: Daily 2AM)

**Total Estimated Effort**: 10-15 hours (2-3 hours per MV)

---

## 🟢 LOW PRIORITY (Within 90 Days)

### 9. External Data Integration

**Opportunities**:
1. **Public Opinion Polling** (Novus, Demoskop, YouGov)
2. **Media Sentiment Analysis** (DN, SvD, Aftonbladet)
3. **EU Voting Records** (Swedish MEPs)
4. **Economic Indicators** (SCB expanded coverage)

**Estimated Effort**: 40-60 hours (varies by data source)

---

### 10. Advanced Analytics & Machine Learning

**Capabilities**:
1. Coalition formation probability models
2. Election outcome forecasting
3. Legislative success prediction
4. Voting behavior anomaly detection (ML-based)
5. Politician career trajectory prediction

**Estimated Effort**: 80-120 hours (varies by model complexity)

---

## Implementation Priority Matrix

| Priority | Task | Impact | Effort | ROI |
|----------|------|--------|--------|-----|
| 🚨 CRITICAL | Fix 2025-2026 voting data | SEVERE | 4-8h | ⭐⭐⭐⭐⭐ |
| 🚨 CRITICAL | Fix motion/proposition zero count | HIGH | 2-4h | ⭐⭐⭐⭐⭐ |
| 🔴 HIGH | Extend 4 remaining date filters | HIGH | 4-6h | ⭐⭐⭐⭐⭐ |
| 🔴 HIGH | Fix coalition alignment scarcity | HIGH | 3-4h | ⭐⭐⭐⭐☆ |
| 🔴 HIGH | Enhance anomaly detection | HIGH | 4-6h | ⭐⭐⭐⭐☆ |
| 🟡 MEDIUM | Validate/refresh 30 MVs | MEDIUM | 3-4h | ⭐⭐⭐☆☆ |
| 🟡 MEDIUM | Coalition stability tracker | MEDIUM | 6-8h | ⭐⭐⭐⭐☆ |
| 🟡 MEDIUM | Gender trends view | MEDIUM | 5-6h | ⭐⭐⭐☆☆ |
| 🟡 MEDIUM | Crisis response view | MEDIUM | 8-10h | ⭐⭐⭐⭐☆ |
| 🟡 MEDIUM | 5 new materialized views | MEDIUM | 10-15h | ⭐⭐⭐☆☆ |
| 🟢 LOW | External data integration | LOW | 40-60h | ⭐⭐⭐☆☆ |
| 🟢 LOW | ML/Advanced analytics | LOW | 80-120h | ⭐⭐⭐⭐☆ |

---

## Total Effort Estimates

**Immediate (7 days)**:
- Critical: 6-12 hours
- High: 15-20 hours
- **Total**: 21-32 hours (~1 week)

**Short-term (30 days)**:
- Medium: 32-43 hours
- **Total**: 32-43 hours (~1 week)

**Medium-term (90 days)**:
- Low (partial): 40-60 hours
- **Total**: 40-60 hours (~1.5 weeks)

**Grand Total**: 93-135 hours (~2.5-3.5 weeks of development)

---

## Success Metrics

**Data Quality Improvements**:
- ✅ 2025-2026 voting participation restored to normal levels (>0%)
- ✅ Motion/proposition counts reflect actual legislative activity
- ✅ Coalition alignment matrix shows 28 party pairs with 2002-2026 coverage
- ✅ Voting anomaly detection identifies 10+ anomalies per year
- ✅ All 30 materialized views populated and refreshed

**Intelligence Capability Enhancements**:
- ✅ 24-year historical analysis enabled (2002-2026)
- ✅ Coalition stability tracker operational
- ✅ Gender representation trends tracked annually
- ✅ Crisis response effectiveness measurable
- ✅ 5 new performance-optimized materialized views

**Platform Performance**:
- ✅ Query response times <500ms for most analytical views
- ✅ Materialized views reduce query load by 50-70%
- ✅ Data freshness indicators show <24h staleness
- ✅ Zero critical data quality alerts

---

## Next Steps

1. **Review & Approve**: Stakeholder review of recommendations
2. **Prioritize**: Confirm priority classification and resource allocation
3. **Sprint Planning**: Organize work into 2-week sprints
4. **Execute**: Implement fixes in priority order
5. **Validate**: Test each fix with production data
6. **Monitor**: Track success metrics post-deployment
7. **Iterate**: Continuous improvement based on user feedback

**Contact**: intelligence@citizensintelligenceagency.se  
**Document Version**: 1.0  
**Last Updated**: 2026-02-09
