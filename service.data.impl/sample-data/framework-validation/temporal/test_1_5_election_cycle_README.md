# Election Cycle Framework Validation Test Cases

This directory contains test cases for validating the election cycle trend analysis views created in changelog v1.51.

## Test Data Structure

The test data simulates 7 election cycles (2002-2026) with known patterns for validation:

### Election Cycles
- **2002-2005**: Baseline cycle (100% reference point)
- **2006-2009**: Growth cycle (+15% ballots)
- **2010-2013**: Stability cycle (±2% variation)
- **2014-2017**: Coalition change cycle (party shifts)
- **2018-2021**: High participation cycle (+20% ballots)
- **2022-2025**: Declining attendance cycle (-10% attendance)
- **2026+**: Current cycle (projection validation)

## Test Cases

### test_1_5_1_election_cycle_summary.csv
Tests: `view_riksdagen_election_cycle_summary`

**Expected Patterns:**
- 7 election cycles with progressive ballot counts
- Cycle-to-cycle LAG comparisons showing growth/decline
- RANK() ordering cycles by ballot volume
- Cumulative metrics increasing over time

**Validation Queries:**
```sql
SELECT 
    election_year,
    total_ballots,
    ballot_change,
    ballot_change_pct,
    ballot_count_rank
FROM view_riksdagen_election_cycle_summary
ORDER BY election_year;
```

**Expected Results:**
- 2002: Baseline (rank varies based on actual data)
- 2006: +15% growth
- 2010: Stable (±2%)
- 2018: Peak activity (+20%)
- 2022: Lower attendance metrics

### test_1_5_2_politician_election_cycle_performance.csv
Tests: `view_riksdagen_politician_election_cycle_performance`

**Expected Patterns:**
- Politicians tracked across multiple cycles
- Entry cycle identification (cohort analysis)
- PERCENT_RANK() showing relative performance (0.0-1.0)
- NTILE(4) grouping into quartiles
- Experience level progression (NEW_MP → VETERAN_MP)

**Validation Queries:**
```sql
SELECT 
    election_year,
    first_name,
    last_name,
    party,
    attendance_score,
    attendance_percentile,
    attendance_quartile,
    experience_level
FROM view_riksdagen_politician_election_cycle_performance
WHERE person_id = '<test_person_id>'
ORDER BY election_year;
```

**Expected Results:**
- Top performers: attendance_percentile > 0.90
- Bottom performers: attendance_percentile < 0.10
- Quartile distribution: roughly equal across 1-4
- Experience progression: cycles_since_entry increments correctly

### test_1_5_3_party_election_cycle_trends.csv
Tests: `view_riksdagen_party_election_cycle_trends`

**Expected Patterns:**
- Major parties (S, M, SD, C, V, MP, KD, L) across all cycles
- Moving averages smoothing volatility
- STDDEV() detecting party instability
- Party status transitions (NEW_PARTY, ESTABLISHED, EXITED)
- Momentum indicators (GROWTH, DECLINE, STABLE)

**Validation Queries:**
```sql
SELECT 
    election_year,
    party,
    party_size,
    size_moving_avg_3cycles,
    size_volatility_3cycles,
    momentum
FROM view_riksdagen_party_election_cycle_trends
WHERE party IN ('S', 'M', 'SD')
ORDER BY election_year, party;
```

**Expected Results:**
- S (Social Democrats): Declining trend 2006-2018, stabilizing 2018+
- M (Moderates): Growth 2002-2010, decline 2010-2018
- SD (Sweden Democrats): Entry 2010, strong growth 2010-2022

### test_1_5_4_committee_election_cycle_activity.csv
Tests: `view_riksdagen_committee_election_cycle_activity`

**Expected Patterns:**
- Committee productivity rankings
- NTILE(4) tiers (VERY_ACTIVE to LOW_ACTIVITY)
- Trend indicators (INCREASING, DECREASING, STABLE)
- Document production volatility

**Validation Queries:**
```sql
SELECT 
    election_year,
    committee,
    documents_produced,
    productivity_rank,
    productivity_tier,
    trend
FROM view_riksdagen_committee_election_cycle_activity
ORDER BY election_year, productivity_rank;
```

**Expected Results:**
- Finance committee: Consistently top 3
- Constitutional committee: High productivity
- Small committees: Bottom quartile

### test_1_5_5_election_cycle_comparative_analysis.csv
Tests: `view_riksdagen_election_cycle_comparative_analysis`

**Expected Patterns:**
- Baseline (2002) comparisons showing evolution
- Historical averages calculated from previous cycles
- Z-scores detecting anomalies (|z| > 2)
- Cycle classification (ANOMALOUS, ABOVE_AVERAGE, TYPICAL, BELOW_AVERAGE)

**Validation Queries:**
```sql
SELECT 
    election_year,
    total_ballots,
    ballots_vs_baseline_pct,
    pct_of_historical_avg_ballots,
    ballots_z_score,
    cycle_classification
FROM view_riksdagen_election_cycle_comparative_analysis
ORDER BY election_year;
```

**Expected Results:**
- 2002: No historical average (first cycle)
- 2018: Likely ABOVE_AVERAGE (high participation)
- 2022: Possible BELOW_AVERAGE if declining
- Anomalies: |z_score| > 2 should be rare

### test_1_5_6_politician_rankings.csv
Tests: `view_riksdagen_election_cycle_politician_rankings`

**Expected Patterns:**
- Top 5%: HALL_OF_FAME designation
- Bottom 5%: NEEDS_IMPROVEMENT
- Party MVP (rank=1 within party)
- Consistency ratings (VERY_CONSISTENT to INCONSISTENT)
- Longevity tiers (NEWCOMER to VETERAN)

**Validation Queries:**
```sql
SELECT 
    election_year,
    first_name,
    last_name,
    party,
    overall_rank,
    party_rank,
    performance_tier,
    special_recognition
FROM view_riksdagen_election_cycle_politician_rankings
WHERE performance_tier IN ('HALL_OF_FAME', 'NEEDS_IMPROVEMENT')
    OR special_recognition = 'PARTY_MVP'
ORDER BY election_year, overall_rank;
```

**Expected Results:**
- HALL_OF_FAME: Top 5% (approximately 17-18 MPs per cycle)
- PARTY_MVP: One per party per cycle
- Rankings stable across cycles for consistent performers

### test_1_5_7_party_momentum.csv
Tests: `view_riksdagen_election_cycle_party_momentum`

**Expected Patterns:**
- Velocity (1-cycle and 2-cycle change)
- Acceleration (change in velocity)
- Momentum score combining velocity & acceleration
- Trajectory classification (ACCELERATING_GROWTH, etc.)
- Coalition potential scores

**Validation Queries:**
```sql
SELECT 
    election_year,
    party,
    party_size,
    velocity_1cycle,
    acceleration,
    momentum_score,
    trajectory,
    coalition_potential_score
FROM view_riksdagen_election_cycle_party_momentum
ORDER BY election_year, momentum_score DESC;
```

**Expected Results:**
- SD: SURGING momentum 2010-2022
- Traditional parties: DECLINING momentum 2006-2018
- Recent cycles: STABLE or DECELERATING

### test_1_5_8_volatility_analysis.csv
Tests: `view_riksdagen_election_cycle_volatility_analysis`

**Expected Patterns:**
- STDDEV() measuring behavioral consistency
- Coefficient of variation (CV) normalizing volatility
- Stability scores (0-100, higher = more stable)
- Risk levels (HIGH_RISK, MODERATE_RISK, LOW_RISK)
- Performance patterns (CONSISTENTLY_EXCELLENT, ERRATIC_PERFORMER, etc.)

**Validation Queries:**
```sql
SELECT 
    first_name,
    last_name,
    party,
    cycles_active,
    attendance_volatility,
    individual_volatility_classification,
    individual_stability_score,
    performance_pattern
FROM view_riksdagen_election_cycle_volatility_analysis
ORDER BY attendance_volatility ASC
LIMIT 20;
```

**Expected Results:**
- Top 20: VERY_STABLE or STABLE (volatility < 10)
- High performers: CONSISTENTLY_EXCELLENT pattern
- Erratic performers: High volatility + variable attendance

## Test Data Creation

Test data is generated from actual vote_data and document_data tables with:
1. Known historical patterns (coalition changes, party trajectories)
2. Synthetic edge cases (new parties, retiring politicians)
3. Validation anchors (specific MPs with documented performance)

## Running Validation

```bash
# From service.data.impl directory
cd /home/runner/work/cia/cia/service.data.impl

# Run all election cycle validation queries
psql -U eris -d cia_dev -f sample-data/framework-validation/temporal/validate_election_cycle_views.sql

# Check results
cat sample-data/framework-validation/temporal/election_cycle_validation_results.csv
```

## Success Criteria

✅ **All views return data for 7 election cycles (2002-2026)**
✅ **Window functions correctly partition by cycle/party/politician**
✅ **Statistical functions (STDDEV, PERCENTILE, RANK) produce valid ranges**
✅ **LAG/LEAD comparisons show correct period-over-period changes**
✅ **Moving averages smooth short-term volatility**
✅ **Performance tiers and rankings distribute appropriately**
✅ **Classification logic produces expected categories**

## Known Issues / Limitations

- Empty database: Test data will be minimal until production data loaded
- Party name changes: Historical party rebranding may affect continuity
- Partial cycles: 2026+ cycle incomplete, projections may be unstable
- Data quality: Missing vote_date or party values excluded from analysis

## Related Documentation

- [Temporal Analysis Framework](../../../DATA_ANALYSIS_INTOP_OSINT.md#framework-1-temporal-analysis)
- [Database View Catalog](../../../DATABASE_VIEW_INTELLIGENCE_CATALOG.md)
- [Liquibase Changelog 1.51](../../../service.data.impl/src/main/resources/db-changelog-1.51.xml)
