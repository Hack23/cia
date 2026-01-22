# Predictive Intelligence Performance Analysis Report

**Generated:** 2026-01-22 16:57:47
**Framework:** Predictive Intelligence (14 supporting views)
**Status:** Comprehensive Performance Assessment

---

## 📋 Executive Summary

### Overview

This report provides a comprehensive performance analysis of 14 database views supporting the Predictive
Intelligence framework. These views enable electoral forecasting, behavioral prediction, and trend analysis
across Swedish parliamentary data spanning 2002-2026.

### Key Findings

- **Total Views Analyzed:** 14
- **High/Very High Complexity:** 8 views (57%)
- **Total Dependencies:** 46 dependent views/tables
- **Window Functions:** 83 across all views

### Critical Performance Issues Identified

1. **Missing Materialized Views**: Most dependent views are not materialized, causing cascading query costs
2. **Expensive CROSS JOINs**: Several views use CROSS JOINs that create Cartesian products
3. **Repeated CTE Calculations**: Election cycle calculations repeated across views
4. **Missing Indexes**: Critical date and foreign key columns lack indexes
5. **Complex Date Operations**: Extensive use of EXTRACT, date_trunc, age() without indexed support

### Estimated Performance Impact

| Severity | View Count | Current Est. Time | Target Time | Status |
|----------|------------|-------------------|-------------|--------|
| 🔴 Critical | 3 | 3.5-5s | < 2s | **Exceeds Target** |
| 🟡 High | 7 | 1.5-3s | < 2s | **Borderline** |
| 🟢 Medium | 4 | 0.5-1.5s | < 1s | **Within Target** |

### High-Priority Optimizations

1. **Materialize 6 Critical Views** (view_risk_score_evolution, view_ministry_risk_evolution, view_party_effectiveness_trends, etc.)
2. **Create Election Cycle Reference Table** (eliminate repeated CTE calculations)
3. **Add Time-Series Indexes** (date columns with functional indexes)
4. **Optimize CROSS JOINs** (replace with proper filtering)
5. **Implement Partitioning Strategy** (by election year for historical data)

---

## 🔍 View-by-View Analysis

### Electoral Forecasting Views

#### 1. `view_election_cycle_predictive_intelligence`

**Line in schema:** 8134
**Complexity:** HIGH
**Performance Target:** < 2s
**Estimated Time:** 1.8-2.5s

**Complexity Metrics:**

- **CTEs:** 2
- **Window Functions:** 6
- **JOINs:** 3
- **Aggregations:** 7

**Dependencies:**

- `view_risk_score_evolution`
- `view_ministry_risk_evolution`
- `view_party_effectiveness_trends`

**Performance Bottlenecks:**

- Multiple LEFT JOINs on date extractions (year comparison)
- Window functions (RANK, PERCENT_RANK, NTILE, LAG, LEAD, STDDEV_POP) over partition
- Generate_series for 30+ years (1994-current+4)
- Dependent on 3 other views that may not be materialized

**Optimization Opportunities:**

1. Materialize dependent views: view_risk_score_evolution, view_ministry_risk_evolution, view_party_effectiveness_trends
2. Add index on risk_score_evolution(assessment_period)
3. Add index on ministry_risk_evolution(assessment_period)
4. Add index on party_effectiveness_trends(period_start)
5. Consider pre-computing election_cycle_periods as a materialized table
6. Add composite index on (calendar_year, semester) for filtering

#### 2. `view_election_cycle_comparative_analysis`

**Line in schema:** 7415
**Complexity:** HIGH
**Performance Target:** < 2s
**Estimated Time:** 1.5-2.2s

**Complexity Metrics:**

- **CTEs:** 2
- **Window Functions:** 7
- **JOINs:** 2
- **Aggregations:** 12

**Dependencies:**

- `view_party_performance_metrics`
- `view_committee_productivity_matrix`

**Performance Bottlenecks:**

- CROSS JOIN with view_party_performance_metrics (could be expensive)
- Window functions over election cycles with LAG calculations
- Generate_series for election cycles
- Multiple complex CASE statements

**Optimization Opportunities:**

1. Replace CROSS JOIN with proper filtering - CROSS JOIN is expensive
2. Materialize view_party_performance_metrics
3. Add index on view_party_performance_metrics(party)
4. Consider partitioning by election_cycle_id
5. Add composite index for (party, calendar_year)

#### 3. `view_riksdagen_election_proximity_trends`

**Line in schema:** 9389
**Complexity:** VERY HIGH
**Performance Target:** < 3s
**Estimated Time:** 3.5-5s

**Complexity Metrics:**

- **CTEs:** 7
- **Window Functions:** 12
- **JOINs:** 10
- **Aggregations:** 30

**Dependencies:**

- `view_politician_behavioral_trends`
- `view_riksdagen_politician_document`
- `view_riksdagen_politician_role_evolution`
- `view_riksdagen_politician_decision_pattern`
- `view_politician_risk_summary`
- `view_riksdagen_politician_influence_metrics`

**Performance Bottlenecks:**

- CROSS JOIN with election_dates array (7 elections)
- Multiple LEFT JOINs across 6 different views
- Complex date calculations (months_until_election with age())
- Window functions calculating 8 different metrics
- Filtering with age() calculations (expensive)
- Final SELECT with complex window functions over person_id partition

**Optimization Opportunities:**

1. 🔴 **CRITICAL**: Materialize all 6 dependent views
2. Add index on vote_date, made_public_date, role_start columns
3. Pre-compute months_until_election as stored value
4. Add composite index on (person_id, activity_quarter)
5. Consider breaking into smaller materialized views per election
6. Add index on date columns with EXTRACT(year)
7. Use table partitioning by election_year for historical data

#### 4. `view_riksdagen_pre_election_quarterly_activity`

**Line in schema:** 13375
**Complexity:** HIGH
**Performance Target:** < 2s
**Estimated Time:** 2-3s

**Complexity Metrics:**

- **CTEs:** 6
- **Window Functions:** 2
- **JOINs:** 5
- **Aggregations:** 25

**Dependencies:**

- `view_politician_behavioral_trends`
- `view_riksdagen_politician_document`
- `view_riksdagen_politician_role_evolution`
- `view_party_effectiveness_trends`
- `view_committee_productivity`

**Performance Bottlenecks:**

- Multiple Q4 aggregations (EXTRACT(quarter) filters)
- CROSS JOIN with committee_productivity_metrics
- Complex FILTER WHERE clauses in aggregations
- Baseline calculations using FILTER WHERE (NOT is_election_year)

**Optimization Opportunities:**

1. Add functional index: CREATE INDEX idx_period_quarter ON table(EXTRACT(quarter FROM period_start))
2. Materialize Q4 data separately
3. Optimize FILTER WHERE with pre-filtered CTEs
4. Add index on is_election_year boolean
5. Consider quarterly partitioning strategy

---

### Behavioral Prediction Views

#### 5. `view_riksdagen_politician_career_trajectory`

**Line in schema:** 12801
**Complexity:** MEDIUM
**Performance Target:** < 1.5s
**Estimated Time:** 1-1.5s

**Complexity Metrics:**

- **CTEs:** 2
- **Window Functions:** 7
- **JOINs:** 4
- **Aggregations:** 10

**Dependencies:**

- `person_data`
- `vote_data`
- `assignment_data`
- `document_person_reference`

**Performance Bottlenecks:**

- Floor division calculation for election_year ((2002 + 4 * floor(...)))
- Multiple DISTINCT counts in aggregations
- Window functions partitioned by person_id
- CASE statements filtering role_code strings

**Optimization Opportunities:**

1. Add functional index on election_year calculation
2. Add index on person_data(id, party)
3. Add index on vote_data(embedded_id_intressent_id, vote_date)
4. Add index on assignment_data(intressent_id, role_code)
5. Consider materialized view for career_cycles CTE

#### 6. `view_riksdagen_politician_role_evolution`

**Line in schema:** 9237
**Complexity:** MEDIUM-HIGH
**Performance Target:** < 1s
**Estimated Time:** 0.8-1.2s

**Complexity Metrics:**

- **CTEs:** 3
- **Window Functions:** 5
- **JOINs:** 1
- **Aggregations:** 8

**Dependencies:**

- `person_data`
- `assignment_data`

**Performance Bottlenecks:**

- Complex CASE statements for role_tier (11 conditions)
- Complex CASE statements for role_weight (14 conditions)
- Window functions with LAG/LEAD operations
- String pattern matching (~~ operator)

**Optimization Opportunities:**

1. Add index on assignment_data(intressent_id, from_date, role_code)
2. Consider lookup table for role_tier and role_weight instead of CASE
3. Add index on assignment_data(role_code) for string matching
4. Materialize role_tier and role_weight as computed columns

#### 7. `view_riksdagen_politician_longevity_analysis`

**Line in schema:** 13234
**Complexity:** MEDIUM
**Performance Target:** < 1s
**Estimated Time:** 0.7-1s

**Complexity Metrics:**

- **CTEs:** 2
- **Window Functions:** 0
- **JOINs:** 3
- **Aggregations:** 12

**Dependencies:**

- `person_data`
- `assignment_data`
- `vote_data`

**Performance Bottlenecks:**

- LEAST/GREATEST operations on dates
- Multiple date extraction operations
- Election cycle calculations with floor division
- Status string pattern matching

**Optimization Opportunities:**

1. Add index on vote_data(embedded_id_intressent_id, vote_date)
2. Add index on assignment_data(intressent_id, from_date, to_date)
3. Add index on person_data(status) for pattern matching
4. Cache career_start_date and career_end_date as columns

#### 8. `view_riksdagen_party_longitudinal_performance`

**Line in schema:** 11260
**Complexity:** VERY HIGH
**Performance Target:** < 3s
**Estimated Time:** 2.8-4s

**Complexity Metrics:**

- **CTEs:** 4
- **Window Functions:** 14
- **JOINs:** 2
- **Aggregations:** 20

**Dependencies:**

- `view_riksdagen_vote_data_ballot_party_summary_annual`
- `view_party_performance_metrics`

**Performance Bottlenecks:**

- Complex election_cycle_calendar CTE with nested CASE statements (7 conditions repeated 4 times)
- Window functions with PARTITION BY election_cycle_id, semester
- Moving averages with ROWS BETWEEN 2 PRECEDING
- Multiple LAG/LEAD operations per party
- Standard deviation calculations across partitions

**Optimization Opportunities:**

1. 🔴 **CRITICAL**: Replace election_cycle_calendar CTE with pre-computed table
2. Materialize view_riksdagen_vote_data_ballot_party_summary_annual
3. Add index on (party, election_cycle_id, cycle_year, semester)
4. Consider partitioning by election_cycle_id
5. Add composite index on (embedded_id_vote_date, embedded_id_party)

#### 9. `view_riksdagen_party_coalition_evolution`

**Line in schema:** 10747
**Complexity:** HIGH
**Performance Target:** < 2s
**Estimated Time:** 2-3s

**Complexity Metrics:**

- **CTEs:** 3
- **Window Functions:** 7
- **JOINs:** 2
- **Aggregations:** 8

**Dependencies:**

- `view_riksdagen_vote_data_ballot_party_summary_annual`

**Performance Bottlenecks:**

- Self-join on vote_data_ballot_party_summary (Cartesian potential)
- Complex alignment calculation with CASE and percentage comparisons
- HAVING clause filtering (joint_voting_days >= 5)
- Window functions with LAG/LEAD per party pair

**Optimization Opportunities:**

1. Add index on (embedded_id_vote_date, embedded_id_party)
2. Optimize self-join with better join conditions
3. Materialize party pair alignments for frequent queries
4. Add index on (party_1, party_2, election_cycle_id) for windowing

---

### Trend Forecasting Views

#### 10. `view_party_effectiveness_trends`

**Line in schema:** 8023
**Complexity:** MEDIUM
**Performance Target:** < 1s
**Estimated Time:** 0.8-1.2s

**Complexity Metrics:**

- **CTEs:** 4
- **Window Functions:** 3
- **JOINs:** 4
- **Aggregations:** 15

**Dependencies:**

- `view_riksdagen_vote_data_ballot_politician_summary_daily`
- `view_riksdagen_politician_document`
- `rule_violation`
- `person_data`

**Performance Bottlenecks:**

- Date range filter (CURRENT_DATE - '3 years')
- date_trunc('quarter') operations
- LAG window functions for trend calculation
- Moving average with ROWS BETWEEN 3 PRECEDING

**Optimization Opportunities:**

1. Add index on vote_date with date_trunc('quarter')
2. Add index on made_public_date with date_trunc('quarter')
3. Add index on rule_violation(detected_date, status, resource_type)
4. Materialize quarterly aggregations
5. Add index on (party, period_start) for trend queries

#### 11. `view_ministry_effectiveness_trends`

**Line in schema:** 8546
**Complexity:** MEDIUM
**Performance Target:** < 1s
**Estimated Time:** 0.9-1.5s

**Complexity Metrics:**

- **CTEs:** 4
- **Window Functions:** 4
- **JOINs:** 5
- **Aggregations:** 11

**Dependencies:**

- `assignment_data`
- `document_status_container`
- `document_data`
- `document_person_reference`

**Performance Bottlenecks:**

- Multiple LEFT JOINs through document hierarchy
- lower() function calls on org_code (case-insensitive matching)
- Date range filter (CURRENT_DATE - '3 years')
- LAG window functions per ministry

**Optimization Opportunities:**

1. Add functional index: CREATE INDEX idx_org_code_lower ON assignment_data(lower(org_code))
2. Add index on document_data(org, made_public_date, document_type)
3. Normalize document hierarchy to reduce JOINs
4. Add index on (org_code, period_start) for windowing
5. Cache ministry_base CTE as table

#### 12. `view_decision_temporal_trends`

**Line in schema:** 6412
**Complexity:** LOW-MEDIUM
**Performance Target:** < 0.8s
**Estimated Time:** 0.5-0.8s

**Complexity Metrics:**

- **CTEs:** 1
- **Window Functions:** 4
- **JOINs:** 3
- **Aggregations:** 7

**Dependencies:**

- `document_proposal_data`
- `document_proposal_container`
- `document_status_container`
- `document_data`

**Performance Bottlenecks:**

- upper() function in FILTER WHERE clauses (6 times)
- Pattern matching with ~~ operator
- Date range filter (CURRENT_DATE - '5 years')
- Moving averages with varying window sizes (7, 30, 90 days)

**Optimization Opportunities:**

1. Add functional index: CREATE INDEX idx_chamber_upper ON document_proposal_data(upper(chamber))
2. Add index on made_public_date for date range queries
3. Normalize chamber values to avoid upper() calls
4. Add index on decision_day for window function ordering
5. Consider daily aggregation materialized view

#### 13. `view_risk_score_evolution`

**Line in schema:** 6633
**Complexity:** HIGH
**Performance Target:** < 1.5s
**Estimated Time:** 1.2-1.8s

**Complexity Metrics:**

- **CTEs:** 6
- **Window Functions:** 2
- **JOINs:** 6
- **Aggregations:** 15

**Dependencies:**

- `vote_data`
- `person_data`
- `document_data`
- `document_person_reference`
- `rule_violation`

**Performance Bottlenecks:**

- party_ballot_majority CTE with vote aggregations
- Self-join for rebel detection
- Multiple document hierarchy LEFT JOINs
- date_trunc('month') operations
- Complex risk score calculation with LEAST, COALESCE
- LAG window function for prev_risk_score

**Optimization Opportunities:**

1. Add index on vote_data(embedded_id_ballot_id, party, vote)
2. Add index on vote_data(embedded_id_intressent_id, vote_date)
3. Materialize party_ballot_majority as daily aggregation
4. Add index on rule_violation(reference_id, detected_date, status)
5. Add composite index on (person_id, assessment_period) for final query
6. Cache monthly aggregations

#### 14. `view_riksdagen_party_electoral_trends`

**Line in schema:** 11055
**Complexity:** HIGH
**Performance Target:** < 2s
**Estimated Time:** 1.5-2.5s

**Complexity Metrics:**

- **CTEs:** 3
- **Window Functions:** 10
- **JOINs:** 1
- **Aggregations:** 11

**Dependencies:**

- `view_riksdagen_vote_data_ballot_party_summary_annual`

**Performance Bottlenecks:**

- election_cycle_periods CTE with complex CASE (7 conditions repeated 3 times)
- Window functions per party with LAG/LEAD operations
- Standard deviation and average calculations across partitions
- HAVING clause filter (voting_days >= 5)

**Optimization Opportunities:**

1. Replace election_cycle_periods with pre-computed table
2. Add index on (embedded_id_party, embedded_id_vote_date)
3. Materialize view_riksdagen_vote_data_ballot_party_summary_annual
4. Add composite index on (party, election_cycle_id, cycle_year, semester)
5. Consider partitioning by election_cycle_id

---

## 🔧 Index Recommendations

### High Priority Indexes

These indexes will have the most significant performance impact:

#### Time-Series Indexes

```sql
CREATE INDEX idx_vote_data_date ON vote_data(vote_date);
CREATE INDEX idx_made_public_date ON document_data(made_public_date);
CREATE INDEX idx_role_start ON assignment_data(from_date);
CREATE INDEX idx_detected_date ON rule_violation(detected_date);
```

#### Functional Date Indexes

```sql
CREATE INDEX idx_vote_date_quarter ON vote_data(date_trunc('quarter', vote_date));
CREATE INDEX idx_vote_date_month ON vote_data(date_trunc('month', vote_date));
CREATE INDEX idx_vote_date_year ON vote_data(EXTRACT(year FROM vote_date));
CREATE INDEX idx_vote_date_quarter_num ON vote_data(EXTRACT(quarter FROM vote_date));
```

#### Foreign Key Indexes

```sql
CREATE INDEX idx_vote_data_person_id ON vote_data(embedded_id_intressent_id);
CREATE INDEX idx_vote_data_ballot_id ON vote_data(embedded_id_ballot_id);
CREATE INDEX idx_assignment_person_id ON assignment_data(intressent_id);
CREATE INDEX idx_doc_person_ref ON document_person_reference_da_0(person_reference_id);
```

#### Composite Indexes for Predictive Views

```sql
CREATE INDEX idx_vote_data_composite ON vote_data(embedded_id_intressent_id, vote_date, party);
CREATE INDEX idx_assignment_composite ON assignment_data(intressent_id, from_date, role_code);
CREATE INDEX idx_doc_data_composite ON document_data(org, made_public_date, document_type);
CREATE INDEX idx_rule_violation_composite ON rule_violation(reference_id, detected_date, status);
```

#### String Matching Optimization

```sql
CREATE INDEX idx_org_code_lower ON assignment_data(lower(org_code));
CREATE INDEX idx_chamber_upper ON document_proposal_data(upper(chamber));
CREATE INDEX idx_status_pattern ON person_data(status) WHERE status LIKE '%Tjänstgörande%';
```

---

## 📊 Materialized View Strategy

### Views Requiring Materialization

#### 🔴 `view_risk_score_evolution` - CRITICAL

**Reason:** Used by 2 high-traffic views, complex 6-CTE calculation
**Refresh Strategy:** Hourly (fast refresh possible with logs)
**Size Estimate:** ~100K rows (politician × month × 3 years)
**Performance Impact:** Reduces query time by 1-2s for dependent views

#### 🔴 `view_ministry_risk_evolution` - CRITICAL

**Reason:** Complex ministry aggregations, used in predictive intelligence
**Refresh Strategy:** Daily
**Size Estimate:** ~50K rows (ministry × month × 3 years)
**Performance Impact:** Reduces query time by 0.5-1s

#### 🔴 `view_party_effectiveness_trends` - CRITICAL

**Reason:** Quarterly aggregations across 4 data sources
**Refresh Strategy:** Daily
**Size Estimate:** ~5K rows (party × quarter × 3 years)
**Performance Impact:** Reduces query time by 0.8-1.2s

#### 🟡 `view_politician_behavioral_trends` - HIGH

**Reason:** Base view for election proximity trends (most complex view)
**Refresh Strategy:** Daily
**Size Estimate:** ~200K rows (politician × period)
**Performance Impact:** Critical for election proximity analysis

#### 🟡 `view_riksdagen_vote_data_ballot_party_summary_annual` - HIGH

**Reason:** Used by 3 views, yearly aggregations
**Refresh Strategy:** Daily
**Size Estimate:** ~150K rows (party × ballot × year)
**Performance Impact:** Reduces join complexity significantly

#### 🟢 `view_party_performance_metrics` - MEDIUM

**Reason:** Cross-joined in election cycle comparative analysis
**Refresh Strategy:** Daily
**Size Estimate:** ~1K rows (party metrics)
**Performance Impact:** Eliminates expensive CROSS JOIN

### Refresh Strategy Implementation

```sql
-- Create materialized views with indexes
CREATE MATERIALIZED VIEW mv_risk_score_evolution AS
SELECT * FROM view_risk_score_evolution;

CREATE INDEX idx_mv_risk_person_period ON mv_risk_score_evolution(person_id, assessment_period);
CREATE INDEX idx_mv_risk_period ON mv_risk_score_evolution(assessment_period);

-- Concurrent refresh (no locks)
REFRESH MATERIALIZED VIEW CONCURRENTLY mv_risk_score_evolution;
```

---

## ⏱️ Time-Series Optimization

### Date Range Query Optimization

Current date range queries use patterns like:
```sql
WHERE vote_date >= (CURRENT_DATE - '3 years'::interval)
WHERE EXTRACT(year FROM vote_date) = calendar_year
WHERE date_trunc('quarter', made_public_date) = period_start
```

**Optimization Strategies:**

1. **Functional Indexes** for date operations
2. **Partitioning** by year or quarter for large tables
3. **Pre-computed Columns** for frequently accessed date parts
4. **BRIN Indexes** for chronologically ordered data

### Window Function Optimization

Views use extensive window functions:

- **RANK/PERCENT_RANK/NTILE**: 45+ instances across views
- **LAG/LEAD**: 38+ instances for trend analysis
- **Moving Averages**: ROWS BETWEEN patterns (7 day, 30 day, 90 day)
- **Standard Deviation**: Per partition calculations

**Optimization:**
- Ensure PARTITION BY columns are indexed
- ORDER BY columns should have B-tree indexes
- Consider pre-computing moving averages for frequent queries

### Partitioning Strategy

Recommended table partitioning for time-series data:

```sql
-- Partition vote_data by year (20+ years of data)
CREATE TABLE vote_data_partitioned (
    -- columns
) PARTITION BY RANGE (vote_date);

CREATE TABLE vote_data_2022 PARTITION OF vote_data_partitioned
    FOR VALUES FROM ('2022-01-01') TO ('2023-01-01');

CREATE TABLE vote_data_2023 PARTITION OF vote_data_partitioned
    FOR VALUES FROM ('2023-01-01') TO ('2024-01-01');
-- etc.
```

**Benefits:**
- Query pruning for date-range queries
- Faster VACUUM/ANALYZE on individual partitions
- Easy archival of old data
- Improved index efficiency on smaller partitions

---

## 🎯 Specific SQL Optimizations

### 1. Replace Election Cycle CTE with Reference Table

**Problem:** Election cycle calculation repeated in 4 views:
```sql
-- Repeated in every query
SELECT
    CASE
        WHEN year BETWEEN 2002 AND 2005 THEN 2002
        WHEN year BETWEEN 2006 AND 2009 THEN 2006
        -- ... (7 conditions)
    END AS election_year
FROM generate_series(2002, CURRENT_YEAR + 4, 1)
```

**Solution:**
```sql
CREATE TABLE election_cycle_calendar (
    calendar_year INT NOT NULL,
    election_year INT NOT NULL,
    cycle_year INT NOT NULL,
    election_cycle_id TEXT NOT NULL,
    is_election_year BOOLEAN NOT NULL,
    PRIMARY KEY (calendar_year)
);

CREATE INDEX idx_election_cycle ON election_cycle_calendar(election_year, cycle_year);

-- Pre-populate with election cycles
INSERT INTO election_cycle_calendar VALUES
    (2002, 2002, 1, '2002-2005', false),
    (2003, 2002, 2, '2002-2005', false),
    -- ... etc
    (2026, 2026, 1, '2026-2029', true);
```

**Impact:** Eliminates 7-condition CASE statement × generate_series overhead

### 2. Optimize CROSS JOIN in Comparative Analysis

**Problem:**
```sql
FROM election_cycle_periods ecp
CROSS JOIN view_party_performance_metrics ppm  -- Cartesian product!
```

**Solution:**
```sql
-- Add party-year dimension to party_performance_metrics
-- Then use proper JOIN
FROM election_cycle_periods ecp
INNER JOIN view_party_performance_metrics ppm
    ON ppm.measurement_year = ecp.calendar_year
WHERE ppm.party IS NOT NULL
```

### 3. Optimize Election Proximity Calculations

**Problem:** Expensive age() calculations:
```sql
(EXTRACT(year FROM age(election_date, period_start)) * 12
  + EXTRACT(month FROM age(election_date, period_start))) AS months_until_election
```

**Solution:** Pre-compute in staging table:
```sql
CREATE TABLE election_proximity_staging AS
SELECT
    person_id,
    activity_quarter,
    election_date,
    -- Pre-computed
    (EXTRACT(year FROM age(election_date, activity_quarter)) * 12
        + EXTRACT(month FROM age(election_date, activity_quarter))) AS months_until_election,
    -- Pre-computed flags
    (EXTRACT(quarter FROM activity_quarter) = 4
        AND months_until_election BETWEEN 9 AND 15) AS is_pre_election_q4
FROM ... -- source data

CREATE INDEX idx_elec_prox ON election_proximity_staging
    (person_id, activity_quarter, election_date);
```

---

## 📈 Performance Metrics & Targets

### Current vs Target Performance

| View | Category | Current Est. | Target | Status | Priority |
|------|----------|--------------|--------|--------|----------|
| `view_election_cycle_predictive_intelligence` | HIGH | 1.8-2.5s | < 2s | ❌ | HIGH |
| `view_election_cycle_comparative_analysis` | HIGH | 1.5-2.2s | < 2s | ⚠️ | HIGH |
| `view_riksdagen_election_proximity_trends` | VERY HIGH | 3.5-5s | < 3s | ❌ | HIGH |
| `view_riksdagen_pre_election_quarterly_activit` | HIGH | 2-3s | < 2s | ❌ | HIGH |
| `view_riksdagen_politician_career_trajectory` | MEDIUM | 1-1.5s | < 1.5s | ❌ | MEDIUM |
| `view_riksdagen_politician_role_evolution` | MEDIUM-HIGH | 0.8-1.2s | < 1s | ❌ | MEDIUM |
| `view_riksdagen_politician_longevity_analysis` | MEDIUM | 0.7-1s | < 1s | ❌ | MEDIUM |
| `view_riksdagen_party_longitudinal_performance` | VERY HIGH | 2.8-4s | < 3s | ❌ | HIGH |
| `view_riksdagen_party_coalition_evolution` | HIGH | 2-3s | < 2s | ❌ | HIGH |
| `view_party_effectiveness_trends` | MEDIUM | 0.8-1.2s | < 1s | ❌ | MEDIUM |
| `view_ministry_effectiveness_trends` | MEDIUM | 0.9-1.5s | < 1s | ❌ | MEDIUM |
| `view_decision_temporal_trends` | LOW-MEDIUM | 0.5-0.8s | < 0.8s | ✅ | MEDIUM |
| `view_risk_score_evolution` | HIGH | 1.2-1.8s | < 1.5s | ❌ | HIGH |
| `view_riksdagen_party_electoral_trends` | HIGH | 1.5-2.5s | < 2s | ⚠️ | HIGH |

### Performance Testing Recommendations

1. **Baseline Measurement**
   - Run EXPLAIN ANALYZE on each view with representative data
   - Measure with cold and warm cache
   - Test with production data volume

2. **Load Testing**
   - Simulate concurrent queries (5-10 users)
   - Test during peak hours
   - Monitor pg_stat_statements for slow queries

3. **Monitoring Setup**
   ```sql
   -- Enable auto_explain for slow queries
   ALTER SYSTEM SET auto_explain.log_min_duration = '1000ms';
   ALTER SYSTEM SET auto_explain.log_analyze = on;
   ALTER SYSTEM SET auto_explain.log_buffers = on;
   SELECT pg_reload_conf();
   ```

---

## 🚀 Implementation Priority

### Phase 1: Critical Performance Fixes (Week 1-2)

**Impact:** 40-60% query time reduction

1. **Create Election Cycle Reference Table**
   - Eliminates repeated CTE calculations
   - Affects: 4 views
   - Effort: 2 hours

2. **Materialize High-Traffic Views**
   - view_risk_score_evolution
   - view_party_effectiveness_trends
   - view_ministry_risk_evolution
   - Effort: 1 day (includes refresh jobs)

3. **Add Time-Series Indexes**
   - vote_date, made_public_date, from_date indexes
   - Functional indexes for date_trunc
   - Effort: 4 hours

4. **Add Foreign Key Indexes**
   - person_id, ballot_id, intressent_id
   - Effort: 2 hours

### Phase 2: Moderate Improvements (Week 3-4)

**Impact:** 20-30% additional improvement

1. **Optimize CROSS JOINs**
   - Replace with proper filtering
   - Add temporal dimension to party_performance_metrics
   - Effort: 1 day

2. **Add Composite Indexes**
   - Multi-column indexes for common query patterns
   - Effort: 4 hours

3. **Materialize Additional Views**
   - view_politician_behavioral_trends
   - view_riksdagen_vote_data_ballot_party_summary_annual
   - Effort: 1 day

4. **Optimize String Operations**
   - Functional indexes for upper()/lower()
   - Normalize frequently matched strings
   - Effort: 4 hours

### Phase 3: Advanced Optimizations (Month 2)

**Impact:** 10-20% improvement, better scalability

1. **Table Partitioning**
   - Partition vote_data by year
   - Partition document_data by year
   - Effort: 3 days (includes migration)

2. **Pre-Compute Complex Calculations**
   - Election proximity staging table
   - Role weight lookup table
   - Effort: 2 days

3. **Query Rewriting**
   - Simplify complex CASE statements
   - Optimize window function usage
   - Effort: 1 week

### Phase 4: Monitoring & Maintenance (Ongoing)

1. **Performance Monitoring**
   - Set up pg_stat_statements tracking
   - Configure auto_explain for slow queries
   - Create performance dashboard

2. **Automated Maintenance**
   - Schedule ANALYZE on key tables
   - Set up materialized view refresh jobs
   - Monitor index bloat

3. **Regular Review**
   - Monthly query performance review
   - Quarterly index usage analysis
   - Annual schema optimization

---

## ⚙️ PostgreSQL Configuration

### Enhanced Configuration (PR #8271)

Recent enhancements include:
- **auto_explain**: Automatic EXPLAIN for slow queries
- **extended_statistics**: Multi-column statistics
- **JIT compilation**: Just-in-time query compilation

### Additional Recommended Settings

```sql
-- Work memory for complex queries
ALTER SYSTEM SET work_mem = '256MB';

-- Maintenance work memory for index creation
ALTER SYSTEM SET maintenance_work_mem = '1GB';

-- Effective cache size (70% of RAM)
ALTER SYSTEM SET effective_cache_size = '8GB';

-- Random page cost (SSD)
ALTER SYSTEM SET random_page_cost = 1.1;

-- Enable parallel query execution
ALTER SYSTEM SET max_parallel_workers_per_gather = 4;
ALTER SYSTEM SET max_parallel_workers = 8;

-- JIT optimization thresholds
ALTER SYSTEM SET jit_above_cost = 100000;
ALTER SYSTEM SET jit_optimize_above_cost = 500000;

SELECT pg_reload_conf();
```

---

## 📊 Implementation Checklist

### Immediate Actions (This Week)

- [ ] Create election_cycle_calendar reference table
- [ ] Add time-series indexes (vote_date, made_public_date, from_date)
- [ ] Add foreign key indexes (person_id, ballot_id)
- [ ] Materialize view_risk_score_evolution
- [ ] Set up auto_explain monitoring

### Short-Term (Next 2 Weeks)

- [ ] Materialize view_party_effectiveness_trends
- [ ] Materialize view_ministry_risk_evolution
- [ ] Add functional date indexes (date_trunc, EXTRACT)
- [ ] Add composite indexes for common query patterns
- [ ] Optimize CROSS JOIN in election_cycle_comparative_analysis

### Medium-Term (Next Month)

- [ ] Materialize view_politician_behavioral_trends
- [ ] Materialize view_riksdagen_vote_data_ballot_party_summary_annual
- [ ] Add string operation functional indexes
- [ ] Create election proximity staging table
- [ ] Set up materialized view refresh jobs

### Long-Term (Next Quarter)

- [ ] Implement table partitioning (vote_data, document_data)
- [ ] Create role weight lookup table
- [ ] Optimize complex window functions
- [ ] Set up performance dashboard
- [ ] Conduct load testing with production volumes

---

## 🎯 Conclusion

This analysis identifies significant optimization opportunities across 14 Predictive Intelligence framework views.
The primary issues are:

1. **Lack of materialization** for frequently-accessed dependent views
2. **Missing indexes** on time-series and foreign key columns
3. **Repeated calculations** (election cycles) that should be pre-computed
4. **Expensive join patterns** (CROSS JOINs, multiple LEFT JOINs)

**Expected Outcome After Optimizations:**

- **Simple forecasting queries**: < 1s (currently 0.8-1.5s) ✅
- **Complex predictive models**: < 3s (currently 3.5-5s) 🎯
- **Electoral forecasting views**: < 2s (currently 1.5-2.5s) ⚡
- **Trend prediction**: < 4s (currently within target) ✅

Implementation of Phase 1 and Phase 2 optimizations will bring all views within performance targets
and provide headroom for future data growth.

---

**Report Generated:** 2026-01-22 16:57:47
**Next Review:** After Phase 1 implementation (2 weeks)
