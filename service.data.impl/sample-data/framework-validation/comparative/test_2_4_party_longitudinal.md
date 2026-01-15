# Party Longitudinal Analysis Views - Sample Data Extraction

## Overview
This document describes the sample data extraction process for the 3 new party longitudinal analysis views created in database changelog v1.53.

## Views Created

### 1. view_riksdagen_party_longitudinal_performance
**Purpose**: Track core metrics per party per election cycle with semester granularity (2002-2026)

**Key Metrics**:
- total_ballots: Number of ballots participated in
- active_members: Count of active party members
- win_rate: Percentage of ballots won
- avg_rebel_rate: Average rebellion rate against party line
- documents_last_year: Documents produced by party members
- participation_rate: Percentage of ballots party participated in
- approval_rate: Percentage of decisions party approved

**Trajectory Classifications**:
- BASELINE: First cycle (no previous data for comparison)
- ASCENDING: Sustained improvement (win_rate increasing >5% across 2+ semesters)
- DESCENDING: Sustained decline (win_rate decreasing >5% across 2+ semesters)
- RECOVERING: Single-semester improvement after decline
- DECLINING: Single-semester decline after improvement
- STABLE: Win rate change within ±5%

**Performance Tiers** (based on PERCENT_RANK):
- ELITE_PERFORMER: percentile >= 0.75
- STRONG_PERFORMER: percentile >= 0.50
- MODERATE_PERFORMER: percentile >= 0.25
- WEAK_PERFORMER: percentile < 0.25

### 2. view_riksdagen_party_coalition_evolution
**Purpose**: Track voting alignment between party pairs across election cycles with semester granularity

**Key Metrics**:
- alignment_rate: Percentage of ballots where both parties voted the same way (Ja/Ja or Nej/Nej)
- joint_ballots: Number of ballots where both parties participated
- aligned_ballots: Number of ballots where parties voted the same
- coalition_strength: Classification of relationship strength

**Coalition Strength Classifications**:
- VERY_STRONG_COALITION: alignment_rate >= 80% (extremely reliable partners)
- STRONG_COALITION: alignment_rate >= 65% (reliable partners)
- MODERATE_COALITION: alignment_rate >= 50% (occasional partners)
- WEAK_COALITION: alignment_rate >= 35% (weak alignment)
- OPPOSITION: alignment_rate < 35% (opposing parties)

**Strategic Shift Detection**:
- COALITION_FORMATION: Alignment increases from <50% to >=65%
- COALITION_BREAKUP: Alignment decreases from >=65% to <50%
- MAJOR_REALIGNMENT: Alignment changes by >=20 percentage points
- SIGNIFICANT_SHIFT: Alignment changes by >=10 percentage points
- MINOR_SHIFT: Other changes

### 3. view_riksdagen_party_electoral_trends
**Purpose**: Track seat count and electoral performance across cycles with semester granularity

**Key Metrics**:
- seat_count_proxy: Number of Riksdag seats held (proxy from active members)
- win_rate: Ballot win rate in parliament
- documents_produced: Legislative activity
- participation_rate: Party voting participation rate
- electoral_trend: Growth/decline classification

**Electoral Trend Classifications**:
- SURGING: seat_count_proxy increase >10
- STRONG_GROWTH: seat_count_proxy increase >5
- GROWTH: seat_count_proxy increase >0
- STABLE: No change in seat_count_proxy
- DECLINE: seat_count_proxy decrease >0
- STRONG_DECLINE: seat_count_proxy decrease >5
- COLLAPSING: seat_count_proxy decrease >10

**Party Size Categories**:
- DOMINANT_PARTY: seat_count_proxy >= 100
- MAJOR_PARTY: seat_count_proxy >= 75
- LARGE_PARTY: seat_count_proxy >= 50
- MEDIUM_PARTY: seat_count_proxy >= 30
- SMALL_PARTY: seat_count_proxy >= 15
- MINOR_PARTY: seat_count_proxy < 15

## Sample Data Extraction Commands

### Extract Party Performance Sample (Top 20 rows)
```bash
psql -h localhost -U eris -d cia_dev -c "COPY (
  SELECT * FROM view_riksdagen_party_longitudinal_performance 
  WHERE party IN ('S', 'M', 'SD', 'C', 'V', 'KD', 'L', 'MP')
  ORDER BY party, election_cycle_id, cycle_year, semester
  LIMIT 20
) TO STDOUT WITH CSV HEADER" > party_longitudinal_performance_sample.csv
```

### Extract Coalition Evolution Sample (M-C, S-V pairs)
```bash
psql -h localhost -U eris -d cia_dev -c "COPY (
  SELECT * FROM view_riksdagen_party_coalition_evolution 
  WHERE (party_1 = 'M' AND party_2 = 'C') 
     OR (party_1 = 'S' AND party_2 = 'V')
  ORDER BY party_1, party_2, election_cycle_id, cycle_year, semester
  LIMIT 20
) TO STDOUT WITH CSV HEADER" > party_coalition_evolution_sample.csv
```

### Extract Electoral Trends Sample
```bash
psql -h localhost -U eris -d cia_dev -c "COPY (
  SELECT * FROM view_riksdagen_party_electoral_trends 
  WHERE party IN ('S', 'M', 'SD')
  ORDER BY party, election_cycle_id, cycle_year, semester
  LIMIT 20
) TO STDOUT WITH CSV HEADER" > party_electoral_trends_sample.csv
```

## Validation Queries

### 1. Verify Election Cycle Coverage
```sql
-- Should return 7 election cycles: 2002-2005, 2006-2009, ..., 2026-2029
SELECT DISTINCT election_cycle_id 
FROM view_riksdagen_party_longitudinal_performance 
ORDER BY election_cycle_id;
```

### 2. Check Major Parties Tracked
```sql
-- Should return 8 major parties: S, M, SD, C, V, KD, L/FP, MP
SELECT party, COUNT(DISTINCT election_cycle_id) AS cycles_tracked
FROM view_riksdagen_party_longitudinal_performance
GROUP BY party
ORDER BY cycles_tracked DESC;
```

### 3. Validate Known Party Transformation: S Decline 2002-2014
```sql
-- Social Democrats should show declining trajectory during 2002-2014
SELECT 
  party, 
  election_cycle_id, 
  win_rate, 
  trajectory_win_rate, 
  performance_tier,
  win_rate_change_absolute
FROM view_riksdagen_party_longitudinal_performance
WHERE party = 'S' 
  AND calendar_year BETWEEN 2002 AND 2014
ORDER BY calendar_year;
```

### 4. Validate Coalition Formation: Alliance Parties 2006-2009
```sql
-- M-C-FP-KD should show STRONG_COALITION during Alliance era
SELECT 
  party_1, 
  party_2, 
  election_cycle_id, 
  alignment_rate, 
  coalition_strength
FROM view_riksdagen_party_coalition_evolution
WHERE election_cycle_id = '2006-2009'
  AND (party_1, party_2) IN (
    ('M', 'C'), ('M', 'L'), ('M', 'KD'),
    ('C', 'KD'), ('C', 'L'), ('KD', 'L')
  )
ORDER BY party_1, party_2;
```

### 5. Validate SD Electoral Growth
```sql
-- Sweden Democrats should show STRONG_GROWTH from 2010 onwards
SELECT 
  party, 
  election_cycle_id, 
  seat_count_proxy, 
  electoral_trend, 
  party_size_category,
  seat_change_absolute
FROM view_riksdagen_party_electoral_trends
WHERE party = 'SD'
ORDER BY election_cycle_id;
```

## Expected Results

### Party Performance (example for S - Social Democrats)
| election_cycle_id | win_rate | trajectory_win_rate | performance_tier   |
|-------------------|----------|---------------------|-------------------|
| 2002-2005         | 52.3     | BASELINE            | STRONG_PERFORMER   |
| 2006-2009         | 48.1     | DECLINING           | MODERATE_PERFORMER |
| 2010-2013         | 45.7     | DESCENDING          | MODERATE_PERFORMER |
| 2014-2017         | 44.2     | DESCENDING          | MODERATE_PERFORMER |
| 2018-2021         | 46.8     | RECOVERING          | MODERATE_PERFORMER |
| 2022-2025         | 49.5     | ASCENDING           | MODERATE_PERFORMER |

### Coalition Evolution (example for M-C alignment)
| election_cycle_id | alignment_rate | coalition_strength | coalition_trend |
|-------------------|----------------|-------------------|-----------------|
| 2002-2005         | 45.2           | OPPOSITION        | BASELINE        |
| 2006-2009         | 78.5           | STRONG_COALITION  | STRENGTHENING   |
| 2010-2013         | 81.3           | STRONG_COALITION  | STABLE          |
| 2014-2017         | 76.2           | STRONG_COALITION  | STABLE          |
| 2018-2021         | 68.4           | MODERATE_COALITION| WEAKENING       |
| 2022-2025         | 62.1           | MODERATE_COALITION| DECLINING       |

### Electoral Trends (example for SD)
| election_cycle_id | seat_count_proxy | electoral_trend | party_size_category |
|-------------------|------------------|-----------------|---------------------|
| 2006-2009         | 0                | BASELINE        | MINOR_PARTY         |
| 2010-2013         | 20               | STRONG_GROWTH   | MEDIUM_PARTY        |
| 2014-2017         | 49               | STRONG_GROWTH   | MEDIUM_PARTY        |
| 2018-2021         | 62               | GROWTH          | LARGE_PARTY         |
| 2022-2025         | 73               | GROWTH          | LARGE_PARTY         |

## Integration with Analysis Frameworks

### Comparative Analysis (Framework 2)
These views directly support Comparative Analysis by enabling:
- Cross-cycle party performance comparison
- Historical coalition pattern analysis
- Long-term electoral trend detection

### Predictive Intelligence (Framework 4)
Trajectory classifications support predictive modeling:
- ASCENDING parties: Likely to gain seats in next election
- DESCENDING parties: At risk of seat loss
- Coalition evolution patterns: Predict future government formations

### Temporal Analysis (Framework 1)
7 election cycles (24 years) enable robust temporal pattern detection:
- Identify cyclical patterns in party performance
- Detect regime shifts in coalition structures
- Measure long-term democratic accountability

## Testing Checklist

- [ ] All 3 views created successfully (check information_schema.views)
- [ ] Each view returns data (non-zero row count)
- [ ] Election cycles covered: 2002-2005 through 2026-2029 (7 cycles)
- [ ] Major parties tracked: S, M, SD, C, V, KD, L/FP, MP
- [ ] Trajectory classifications appear reasonable (not all BASELINE)
- [ ] Coalition strengths validated against known historical coalitions
- [ ] No NULL values in critical metrics (win_rate, alignment_rate, seat_count)
- [ ] Query performance < 2 seconds for typical queries
- [ ] Sample data extracted and validated
- [ ] Cross-reference with existing party views (view_party_performance_metrics)

## Known Limitations

1. **Historical Data Availability**: Views rely on vote_data coverage. If vote data is sparse before 2010, early cycles may have limited metrics.

2. **Party Name Changes**: FP → L (Folkpartiet to Liberalerna) may require manual mapping if party codes changed in source data.

3. **Seat Count Proxy**: Electoral trends view uses active members as a proxy for seat count since actual seat allocation data may not be in vote_data.

4. **Coalition Detection**: Alignment rates are based on voting behavior, not formal coalition agreements. Informal alliances may not show as STRONG_COALITION if parties frequently vote differently on specific issues.

5. **Minimum Vote Threshold**: Coalition evolution requires minimum 10 joint votes per cycle for meaningful analysis. Smaller parties may have sparse data.

## Next Steps

1. **Run Validation Script**: Execute `validate-party-longitudinal-views.sql` to verify all views
2. **Extract Sample Data**: Generate CSV samples for all 3 views
3. **Update Documentation**: Add views to DATABASE_VIEW_INTELLIGENCE_CATALOG.md
4. **Create Test Cases**: Add unit tests validating known party transformations
5. **Performance Tuning**: Add indexes if queries exceed 2-second target
6. **Dashboard Integration**: Incorporate longitudinal views into political scorecards

## References

- GitHub Issue: #8209 - Party Performance Across Election Cycles
- Dependency: #8208 - Historical Election Cycle Trend Views (v1.51)
- Framework: DATA_ANALYSIS_INTOP_OSINT.md - Comparative Analysis (Framework 2)
- Schema: service.data.impl/src/main/resources/db-changelog-1.53.xml
- Validation: service.data.impl/src/main/resources/validate-party-longitudinal-views.sql
