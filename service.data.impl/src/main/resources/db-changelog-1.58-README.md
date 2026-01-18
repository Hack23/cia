# Database Changelog v1.58 - 10-Level Career Path Classification

## Overview

Changelog v1.58 introduces a comprehensive 10-level hierarchical role classification system for tracking politician career paths in the Swedish Riksdag. This enhancement builds upon issue #8211 (Politician Career Trajectory Tracking) and existing view infrastructure to provide deep insights into career progression, typical paths, and downward spirals.

## Architecture

### Building on Existing Views

This changelog follows the pattern established in v1.53 (party longitudinal analysis) by **building on existing views** rather than querying base tables directly:

- **Primary Source**: `view_riksdagen_politician_role_evolution` (from v1.56)
  - Already contains role classification logic (role_tier)
  - Already has role_weight scoring (1000 for PM down to 50 for other)
  - Pre-aggregated role summaries for performance
  
- **Benefits**:
  - No duplication of role classification logic
  - Better query performance (uses pre-aggregated data)
  - Leverages existing, validated role categorization
  - Consistent with project architecture patterns (see db-changelog-1.53.xml)

## Created Views

### `view_riksdagen_politician_career_path_10level`

A comprehensive view that analyzes politician career trajectories using a 10-level hierarchical classification system (derived from existing role_weight ranges) with advanced statistical functions and 40+ KPI metrics.

## 10-Level Career Classification System

| Level | Classification | Role Examples | Career Score |
|-------|----------------|---------------|--------------|
| L10 | Prime Minister | Statsminister | 1000 |
| L09 | Cabinet Ministers | Vice statsminister, Statsråd, other ministers | 900 |
| L08 | Speaker of Parliament | Talman | 800 |
| L07 | Party Leaders & Deputy Speakers | Partiledare, Vice talman | 700 |
| L06 | Party Secretaries & Committee Chairs | Partisekreterare, Ordförande | 600 |
| L05 | Committee Vice Chairs | Vice ordförande | 500 |
| L04 | Active MPs | Riksdagsledamot | 400 |
| L03 | Committee Members | Ledamot (in committees) | 300 |
| L02 | Substitute MPs | Suppleant, Ersättare | 50 |
| L01 | Other/Entry Roles | Other assignments | 10 |

**Note on Scoring**: The scoring system uses non-linear weights for lower levels (L01-L02) to prevent accumulation effects. Multiple substitute roles (L02 at 50 points each) will not artificially inflate career scores above substantive positions. For example, 6 substitute roles (300 total) remain lower than 1 active MP role (400 points), accurately reflecting career significance.

## Key Features

### Career Pattern Classification (8 Types)

1. **FAST_TRACK**: Consistent rapid promotions, no demotions, career span ≤10 years
2. **RISING_STAR**: At or near peak, strong recent progression, more promotions than demotions
3. **STEADY_PROGRESS**: Regular advancement over time, career span >10 years
4. **PEAK_PERFORMER**: Sustained at high level (≥L7) for 3+ years
5. **STAGNANT**: No progression, stuck at same level for 5+ years
6. **DECLINING**: More demotions than promotions
7. **DOWNWARD_SPIRAL**: Consecutive recent demotions (2+)
8. **EARLY_CAREER**: Just starting out (≤3 years)

### Downward Spiral Indicators

- **Downward Spiral Flag**: TRUE when 2+ demotions with recent demotion
- **Exit Risk Score**: 0-100 scale assessing likelihood of leaving politics
  - 90: Consecutive demotions ongoing
  - 75: Dropped from L4+ to L2
  - 60: More demotions than promotions
  - 50: Former role now at L2
  - 40: Recent demotion
  - 30: Long career (20+ years) stuck at low level (L3 or below)
  - 10: Default

### Advanced Metrics (40+ KPIs)

#### Progression Metrics
- `advancement_velocity`: Levels gained per year between roles
- `promotions_count`: Total number of promotions
- `demotions_count`: Total number of demotions
- `level_change`: Change in level from previous role
- `score_change`: Change in career score from previous role

#### Peak Analysis
- `peak_career_level`: Highest level achieved
- `peak_career_score`: Highest score achieved
- `is_peak_role`: TRUE if current role equals peak
- `years_at_peak`: Total years spent at peak level

#### Time Analysis
- `career_span_years`: Total years in politics (first to last assignment)
- `years_in_role`: Duration in current role
- `years_since_prev_role`: Gap between previous and current role
- `total_years_at_level`: Cumulative time at current level
- `avg_years_per_promotion`: Average pacing of career advancement

#### Comparative Rankings
- `overall_career_rank`: Rank by peak score and career span
- `career_percentile`: Percentile rank by peak score (0.0-1.0)
- `career_decile`: Decile grouping by peak score (1-10)

#### Health & Stability
- `career_health_score`: Composite score (0-100) considering:
  - Current level (40%)
  - Peak status (20%)
  - Net progression (20%)
  - Recent direction (10%)
  - Stability (10%)
- `career_score_volatility`: Standard deviation of career scores
- `avg_career_score`: Mean career score across all roles

## Sample Queries

### 1. Find All Prime Ministers and Their Career Paths

```sql
SELECT 
    first_name,
    last_name,
    party,
    from_date,
    to_date,
    career_span_years,
    peak_career_level,
    career_pattern,
    career_health_score
FROM view_riksdagen_politician_career_path_10level
WHERE career_level = 10
ORDER BY from_date DESC;
```

### 2. Track Party Leaders Over Time

```sql
SELECT 
    first_name,
    last_name,
    party,
    role_code,
    from_date,
    to_date,
    is_current_role,
    years_in_role,
    career_pattern,
    exit_risk_score
FROM view_riksdagen_politician_career_path_10level
WHERE career_level = 7
  AND role_code IN ('Partiledare', 'Tillförordnad partiledare')
ORDER BY party, from_date DESC;
```

### 3. Identify Fast-Track Careers (Rapid Advancement)

```sql
SELECT 
    first_name,
    last_name,
    party,
    career_start_year,
    career_span_years,
    peak_career_level,
    peak_career_score,
    promotions_count,
    demotions_count,
    avg_years_per_promotion,
    career_health_score
FROM view_riksdagen_politician_career_path_10level
WHERE career_pattern = 'FAST_TRACK'
  AND is_current_role = TRUE
ORDER BY career_health_score DESC;
```

### 4. Detect Downward Spirals (High Exit Risk)

```sql
SELECT 
    first_name,
    last_name,
    party,
    role_code,
    career_level,
    progression_direction,
    demotions_count,
    exit_risk_score,
    career_pattern,
    is_current_role
FROM view_riksdagen_politician_career_path_10level
WHERE downward_spiral_flag = TRUE
  OR exit_risk_score >= 60
ORDER BY exit_risk_score DESC, last_name;
```

### 5. Analyze Speaker Succession Patterns

```sql
SELECT 
    first_name,
    last_name,
    party,
    from_date,
    to_date,
    years_in_role,
    prev_career_level,
    next_career_level,
    progression_direction,
    career_health_score
FROM view_riksdagen_politician_career_path_10level
WHERE career_level = 8
  OR (career_level = 7 AND role_code LIKE '%vice talman%')
ORDER BY from_date DESC;
```

### 6. Compare Career Trajectories by Pattern

```sql
SELECT 
    career_pattern,
    COUNT(DISTINCT person_id) as total_politicians,
    ROUND(AVG(career_span_years), 1) as avg_career_span,
    ROUND(AVG(peak_career_score), 0) as avg_peak_score,
    ROUND(AVG(promotions_count), 1) as avg_promotions,
    ROUND(AVG(career_health_score), 1) as avg_health_score
FROM view_riksdagen_politician_career_path_10level
WHERE is_current_role = TRUE
GROUP BY career_pattern
ORDER BY avg_peak_score DESC;
```

### 7. Track Current MPs by Career Stage

```sql
SELECT 
    career_stage,
    COUNT(DISTINCT person_id) as total_mps,
    ROUND(AVG(career_health_score), 1) as avg_health,
    ROUND(AVG(exit_risk_score), 1) as avg_exit_risk,
    COUNT(CASE WHEN is_typical_career_path THEN 1 END) as typical_paths,
    COUNT(CASE WHEN is_atypical_career_path THEN 1 END) as atypical_paths
FROM view_riksdagen_politician_career_path_10level
WHERE is_current_role = TRUE
  AND career_level >= 4  -- Active MPs and above
GROUP BY career_stage
ORDER BY 
    CASE career_stage
        WHEN 'EARLY_CAREER' THEN 1
        WHEN 'MID_CAREER' THEN 2
        WHEN 'SENIOR_CAREER' THEN 3
        WHEN 'VETERAN_CAREER' THEN 4
    END;
```

### 8. Identify Peak Performers and Their Longevity

```sql
SELECT 
    first_name,
    last_name,
    party,
    career_level_name,
    years_at_peak,
    total_years_at_level,
    career_span_years,
    career_health_score,
    is_current_role
FROM view_riksdagen_politician_career_path_10level
WHERE career_pattern = 'PEAK_PERFORMER'
  AND peak_career_level >= 7
ORDER BY years_at_peak DESC, career_health_score DESC
LIMIT 20;
```

### 9. Career Advancement Velocity Analysis

```sql
SELECT 
    person_id,
    first_name,
    last_name,
    party,
    career_step,
    role_code,
    career_level,
    advancement_velocity,
    years_since_prev_role,
    progression_direction
FROM view_riksdagen_politician_career_path_10level
WHERE advancement_velocity IS NOT NULL
  AND advancement_velocity > 0
ORDER BY advancement_velocity DESC, person_id, career_step
LIMIT 50;
```

### 10. Stagnant Careers Needing Intervention

```sql
SELECT 
    first_name,
    last_name,
    party,
    role_code,
    career_level,
    total_years_at_level,
    career_span_years,
    promotions_count,
    career_health_score,
    exit_risk_score
FROM view_riksdagen_politician_career_path_10level
WHERE career_pattern = 'STAGNANT'
  AND is_current_role = TRUE
  AND total_years_at_level >= 5
ORDER BY total_years_at_level DESC, exit_risk_score DESC;
```

## Intelligence Applications

### 1. Leadership Succession Planning
- Identify potential candidates for top positions based on career trajectory
- Track current party leaders and their succession readiness
- Forecast speaker transitions using career pattern analysis

### 2. Talent Retention Analysis
- Monitor politicians with high exit risk scores
- Detect downward spirals early for intervention
- Identify stagnant careers needing development opportunities

### 3. Career Forecasting
- Predict future role transitions using advancement velocity
- Model typical career paths for different starting positions
- Estimate time to peak for rising stars

### 4. Party Health Assessment
- Compare career patterns across parties
- Identify parties with strong talent pipelines (many fast-track careers)
- Detect parties with retention issues (high exit risk scores)

### 5. Historical Analysis
- Study career patterns of former Prime Ministers
- Analyze successful vs. unsuccessful party leadership transitions
- Track evolution of career expectations over time (2002-present)

## Technical Notes

### Window Functions Used
- **RANK**: Rank politicians within career steps
- **PERCENT_RANK**: Percentile-based comparative positioning
- **NTILE**: Decile grouping for stratification
- **LAG**: Access previous role metrics
- **LEAD**: Access next role metrics for forecasting
- **STDDEV_POP**: Career volatility measurement
- **COUNT**: Aggregate promotion/demotion counts
- **SUM**: Cumulative time-in-level calculations
- **AVG**: Rolling averages and career-wide means

### Performance Considerations
- View is not materialized - computed on query execution
- Complex window functions may take 1-3 seconds for full dataset
- Consider adding WHERE clauses to filter by:
  - `is_current_role = TRUE` for current positions only
  - `career_start_year >= 2010` for recent entrants
  - Specific `career_level` or `party` for targeted analysis

### Data Quality Notes
- Covers assignments from 2002-01-01 onwards (modern era)
- Uses COALESCE to handle NULL values in status/assignment_type/org_code
- Role classification based on `role_code` field from `assignment_data`
- Career boundaries calculated from actual assignment dates

## Integration with Existing Views

This view complements the existing career analysis views:

- **v1.56 `view_riksdagen_politician_career_trajectory`**: Election cycle-based performance
- **v1.56 `view_riksdagen_politician_role_evolution`**: Role tier classification
- **v1.56 `view_riksdagen_politician_longevity_analysis`**: Career duration patterns
- **v1.57 `view_riksdagen_party_transition_history`**: Party switching analysis

Use v1.58 when you need:
- Granular 10-level classification (vs. broader tiers)
- Comprehensive career pattern detection (8 types)
- Downward spiral and exit risk assessment
- Time-in-level and peak sustainability analysis
- Career health scoring and comparative rankings

## Validation

After applying this changelog, validate with:

```sql
-- Check view exists
SELECT COUNT(*) 
FROM information_schema.views 
WHERE table_name = 'view_riksdagen_politician_career_path_10level';

-- Verify data coverage
SELECT 
    COUNT(DISTINCT person_id) as total_people,
    MIN(career_start_year) as earliest_year,
    MAX(career_end_year) as latest_year,
    COUNT(*) as total_career_records
FROM view_riksdagen_politician_career_path_10level;

-- Test career pattern distribution
SELECT 
    career_pattern, 
    COUNT(*) as count
FROM view_riksdagen_politician_career_path_10level
WHERE is_current_role = TRUE
GROUP BY career_pattern
ORDER BY count DESC;
```

## References

- **Issue**: [#8211 - Politician Career Trajectory Tracking Across Election Cycles](https://github.com/Hack23/cia/issues/8211)
- **Framework**: DATA_ANALYSIS_INTOP_OSINT.md - Predictive Intelligence Framework 4
- **Base Views**: db-changelog-1.56.xml (career trajectory views)
- **Related**: db-changelog-1.53.xml (party longitudinal analysis)
