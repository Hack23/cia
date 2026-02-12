# CIA Sample Data Extract

This directory contains sample data extracted from the CIA database for:
- Testing and development
- View validation
- Data quality analysis
- Temporal pattern understanding

## Extraction Scripts

### Main Extraction (All Data)
Run from database server:
```bash
cd /workspaces/cia/service.data.impl/sample-data
psql -h localhost -U eris -d cia_dev -f ../src/main/resources/extract-sample-data.sql 2>&1 | tee extract-sample-data.log
```

### Party Data Extraction
Extract complete party master data, members, leaders, and voting patterns:
```bash
psql -h localhost -U eris -d cia_dev -f ../src/main/resources/extract-party-data.sql
```

Generates 7 files:
- `party_master_data.csv` - All registered political parties
- `party_members_current.csv` - Current active members
- `party_members_historical.csv` - Complete membership history
- `party_leaders_current.csv` - Current leadership positions
- `party_leaders_historical.csv` - Historical leadership transitions
- `party_voting_summary.csv` - Party voting statistics
- `party_composition_timeline.csv` - Annual membership changes

### Minister Data Extraction
Extract complete government minister data, ministries, and assignments:
```bash
psql -h localhost -U eris -d cia_dev -f ../src/main/resources/extract-minister-data.sql
```

Generates 7 files:
- `minister_current.csv` - Current government ministers
- `minister_historical.csv` - Complete minister history
- `ministry_assignments_current.csv` - Current ministry assignments
- `ministry_assignments_historical.csv` - Complete assignment history
- `government_composition.csv` - Current government structure
- `government_transitions.csv` - Government changes over time
- `minister_performance.csv` - Minister performance metrics

### Riksdagsmonitor Integration

The main extraction script generates **33 distribution CSV files** with aggregate statistics for the [Riksdagsmonitor](https://github.com/Hack23/riksdagsmonitor) dashboard. Most files contain complete aggregated data; some use deterministic top-N limits (e.g., coalition alignment top 200 by rate) for performance while maintaining reproducibility. Files are downloaded by `download-csv.sh`:

**Party (9):** party_performance, party_effectiveness_trends, party_momentum, coalition_alignment, annual_party_members, gender_by_party, experience_by_party, behavioral_patterns_by_party, decision_patterns_by_party

**Voting (7):** annual_party_votes, annual_ballots, decision_trends, document_types, annual_document_types, document_status, annual_document_status

**Committee (5):** committee_activity, committee_productivity, committee_productivity_matrix, annual_committee_assignments, annual_committee_documents

**Ministry (4):** ministry_effectiveness, ministry_productivity_matrix, ministry_decision_impact, annual_ministry_assignments

**Risk (3):** ministry_risk_levels, ministry_risk_quarterly, crisis_resilience

**Other (5):** anomaly_by_party, election_regions, experience_levels, assignment_roles, influence_buckets

## Sample Size Configuration

| Category | Sample Size | Description |
|----------|-------------|-------------|
| Default | 200 rows | Most tables/views |
| Political entities | 500 rows | Parties, committees, politicians |
| Document/Voting/Worldbank | 300 rows | Extended sample for domain data |
| Complete extraction | ≤3000 rows | Small tables/views extracted fully |

## Temporal Stratification

Views with temporal columns are sampled to ensure coverage across time periods:

| Granularity | Strategy | Time Range |
|-------------|----------|------------|
| Daily | 2 samples/day | Last 30 days |
| Weekly | 2 samples/week | Last 6 months |
| Monthly | 2 samples/month | Last 3 years |
| Annual | 2 samples/year | Full history |
| Trend | 1 sample/period | All time periods |

## File Categories

### Table Samples (`table_*.csv`)
Random samples from database tables.

### View Samples (`view_*.csv`)
Samples from views with temporal stratification applied.

### Distinct Values (`distinct_*.csv`)
All unique values for categorical/predicate columns - useful for filter testing.

### Distributions (`distribution_*.csv`)
Statistical distributions for understanding data patterns:

| File | Description |
|------|-------------|
| `distribution_party_members.csv` | Party membership counts |
| `distribution_annual_party_members.csv` | **Active party members by year** |
| `distribution_annual_document_types.csv` | **Document types by year** |
| `distribution_annual_committee_documents.csv` | **Committee activity by year** |
| `distribution_annual_party_votes.csv` | **Party voting patterns by year** |
| `distribution_annual_ballots.csv` | **Ballot volume by year** |
| `distribution_politician_career_starts.csv` | **When politicians started** |

### Trends (`trend_*.csv`)
Time-series data for trend analysis.

### Reports (`report_*.csv`)
Quality reports and analysis results.

### Summaries (`summary_*.csv`)
Aggregated statistics and metrics.

## Known Data Quality Issues

### Empty Views Due to Status Mismatch

Several views return 0 rows because they filter on English status values (`'active'`, `'Active'`, `'ACTIVE'`) but the actual data contains Swedish values:

**Actual person_data.status values:**
- `Tjänstgörande riksdagsledamot` (Active member of parliament)
- `Tidigare riksdagsledamot` (Former member of parliament)
- `Inga uppdrag` (No assignments)
- `Tillgänglig ersättare` (Available substitute)
- etc.

**Affected views:**
- `view_politician_risk_summary`
- `view_risk_score_evolution`
- `view_riksdagen_crisis_resilience_indicators`
- `view_riksdagen_politician_influence_metrics`
- `view_riksdagen_voting_anomaly_detection`

**Fix:** Update view definitions to use correct Swedish status values:
```sql
WHERE p.status IN ('Tjänstgörande riksdagsledamot', 'Tjänstgörande ersättare', 'Tillgänglig ersättare')
```

See `report_empty_views.csv` for the full list of empty views.

## Using Annual Distributions for View Development

The annual distribution files help understand when data entities existed:

1. **Check entity existence:** Before building a view for a specific year, verify the entity had data
2. **Identify gaps:** Find years with missing or sparse data
3. **Temporal join safety:** Use LEFT JOINs when combining entities that didn't always coexist

Example: When building politician activity views, use `distribution_annual_party_members.csv` to verify which parties had active members in each year.

## Generated Files

See `extraction_statistics.csv` for complete extraction metrics and `extraction_manifest.csv` for the full file inventory.
