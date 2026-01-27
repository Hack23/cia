# View Validation and Sample Data Extraction

This directory contains tools for validating SQL views and extracting sample data with timeout protection for complex views.

## Overview

The Citizen Intelligence Agency database has 109 views (81 regular + 28 materialized) with varying complexity levels. Some advanced "election_cycle" views (Level 3-4 dependencies) can take significant time to execute. These tools provide:

1. **View Validation** - Test all views with timeout protection
2. **Materialized View Refresh** - Refresh all materialized views in dependency order
3. **Fast Sample Data Extraction** - Extract sample data with complexity-aware limits

## Tools

### 1. View Validation (`validate-views-with-timeout.sql`)

Validates all database views with 30-second timeout protection per view.

**Features:**
- Automatic timeout protection (30s per view)
- Dependency level calculation (0-4)
- Performance metrics (execution time, row counts)
- Error handling and reporting
- Categorization by complexity (SIMPLE/MODERATE/COMPLEX/VERY_COMPLEX)

**Usage:**
```bash
cd service.data.impl/sample-data
psql -U eris -d cia_dev -f ../src/main/resources/validate-views-with-timeout.sql
```

**Output Files:**
- `validation_report.csv` - Full validation results for all views
- `validation_summary.csv` - Summary statistics by complexity
- `problematic_views.csv` - Views that timed out or failed
- `view_dependencies.csv` - View dependency graph
- `empty_views.csv` - Views with no data

**Example Results:**
```
Complexity Category | Total Views | Success | Empty | Timeout | Error
--------------------|-------------|---------|-------|---------|------
SIMPLE (0-1)        | 50          | 4       | 40    | 0       | 6
MODERATE (2)        | 23          | 2       | 11    | 0       | 10
COMPLEX (3)         | 12          | 0       | 0     | 0       | 12
VERY_COMPLEX (4+)   | 24          | 4       | 0     | 0       | 20
```

### 2. Materialized View Refresh (`refresh-all-views.sql`)

Refreshes all 28 materialized views in correct dependency order.

**Features:**
- Dynamic discovery from pg_matviews
- Topologically sorted refresh order (Level 0 → Level 1 → ... → Level 4)
- Error handling to continue on individual failures
- Timing information for each view refresh
- Progress logging and summary report

**Usage:**
```bash
cd service.data.impl/sample-data
psql -U eris -d cia_dev -f ../src/main/resources/refresh-all-views.sql
```

**Example Output:**
```
=== Refreshing Level 0 (dependency depth: 0) ===
[1/28] [L0] Refreshing: public.view_riksdagen_committee_decisions...
      ✓ Refreshed in 00:00:00.003456

--- REFRESH SUMMARY ---
Total views: 28
Successful: 28
Failed: 0
Dependency levels processed: 5 (0 to 4)
```

**Important:** Run this script before validation or sample extraction to ensure all materialized views are populated!

### 3. Fast Sample Data Extraction (`extract-sample-data-fast.py`)

Python script for quickly extracting sample data with timeout protection.

**Features:**
- Fast extraction with LIMIT clause
- Timeout protection (10s per view)
- Complexity-aware sample sizes:
  - SIMPLE (Level 0-1): 50 rows
  - MODERATE (Level 2): 30 rows
  - COMPLEX (Level 3+): 10 rows
- Prioritizes simple views over complex ones
- Graceful handling of timeouts
- Progress tracking

**Usage:**
```bash
cd service.data.impl/sample-data
export PGPASSWORD=your_password
python3 ../src/main/resources/extract-sample-data-fast.py
```

**Environment Variables:**
- `PGHOST` - PostgreSQL host (default: localhost)
- `PGPORT` - PostgreSQL port (default: 5432)
- `PGDATABASE` - Database name (default: cia_dev)
- `PGUSER` - Database user (default: eris)
- `PGPASSWORD` - Database password

**Output Files:**
- `table_<name>_sample.csv` - Sample data from each table (50 rows)
- `view_<name>_sample.csv` - Sample data from each view (10-50 rows)
- `extraction_fast_log.csv` - Extraction status and timing

**Example Output:**
```
Overall Statistics:
  Total objects:      190
  Successful:         190
  Timeout:            0
  Errors:             0
  Total rows:         2,282
  Success rate:       100%
```

## Workflow

### Initial Setup (First Time)

1. **Refresh materialized views:**
   ```bash
   psql -U eris -d cia_dev -f refresh-all-views.sql
   ```

2. **Validate all views:**
   ```bash
   psql -U eris -d cia_dev -f validate-views-with-timeout.sql
   ```

3. **Extract sample data:**
   ```bash
   export PGPASSWORD=discord
   python3 extract-sample-data-fast.py
   ```

### Regular Maintenance

**When to refresh materialized views:**
- After schema changes
- After data updates
- Before validation or extraction
- If views show as unpopulated

**When to re-validate:**
- After schema changes
- After view definition updates
- To check for performance regressions
- To identify problematic views

**When to re-extract samples:**
- After data updates
- For testing and development
- To validate view content
- To generate fresh test data

## Understanding the Results

### View Dependency Levels

Views are organized into dependency levels based on how many layers of views they depend on:

- **Level 0**: Views that only depend on base tables
- **Level 1**: Views that depend on Level 0 views
- **Level 2**: Views that depend on Level 1 views
- **Level 3**: Views that depend on Level 2 views
- **Level 4**: Views that depend on Level 3 views

Example dependency chain:
```
Table: vote_data (base table)
  ↓
Level 0: view_riksdagen_vote_data_ballot_summary
  ↓
Level 1: view_riksdagen_vote_data_ballot_party_summary
  ↓
Level 2: view_riksdagen_vote_data_ballot_party_summary_daily
  ↓
Level 3: view_riksdagen_vote_data_ballot_party_summary_annual
  ↓
Level 4: view_riksdagen_party_electoral_trends
```

### Complexity Categories

- **SIMPLE** (Levels 0-1): Direct table queries or single-level views
- **MODERATE** (Level 2): Two levels of view dependencies
- **COMPLEX** (Level 3): Three levels of view dependencies
- **VERY_COMPLEX** (Level 4+): Four or more levels of view dependencies

### Empty Views

99 out of 109 views return 0 rows. This is expected because:
1. Database may not have historical data loaded
2. Views filter on specific conditions that aren't met
3. Test database vs. production database

The validation and extraction tools handle empty views gracefully.

## Troubleshooting

### Views Fail with "materialized view has not been populated"

**Solution:** Run the refresh script first:
```bash
psql -U eris -d cia_dev -f refresh-all-views.sql
```

### Extraction Fails with "relative path not allowed"

**Solution:** Use the Python script instead of the SQL script:
```bash
python3 extract-sample-data-fast.py
```

### Views Time Out

The scripts are designed to handle timeouts gracefully:
- Validation: Continues with other views, logs timeout
- Extraction: Skips timed-out view, continues with others

Check `problematic_views.csv` or `extraction_fast_log.csv` for details.

### Permission Denied

Ensure you have:
- Read access to all tables and views
- Write access to the current directory (for CSV files)
- Superuser privileges (for some system catalogs)

## Performance Considerations

### Validation Performance

- Most views: < 10ms
- Complex views: 10-50ms
- Very complex views: 50-100ms
- Slowest view: `view_election_cycle_temporal_trends` at ~10ms

### Extraction Performance

- Total time: ~30-60 seconds for all 190 objects
- Average per object: ~200-300ms
- Complexity-aware limiting prevents long-running queries
- Parallel extraction not needed due to fast completion

### Database Load

These tools are designed for minimal database impact:
- Read-only operations
- Limited row sampling (10-50 rows per view)
- Timeout protection prevents runaway queries
- No transactions or locks

## Files Generated

After running all tools, you'll have:

```
service.data.impl/sample-data/
├── validation_report.csv              # All view validation results
├── validation_summary.csv             # Validation statistics
├── problematic_views.csv              # Timeout/error views
├── view_dependencies.csv              # Dependency graph
├── empty_views.csv                    # Views with no data
├── extraction_fast_log.csv            # Extraction log
├── table_*_sample.csv                 # Table samples (81 files)
└── view_*_sample.csv                  # View samples (109 files)
```

Total: ~200 CSV files + 6 metadata files

## Integration with Existing Scripts

These new scripts complement the existing `extract-sample-data.sql` script:

| Script | Purpose | Sample Size | Timeout | Speed |
|--------|---------|-------------|---------|-------|
| `extract-sample-data.sql` | Comprehensive extraction with temporal stratification | 50-500 rows | None | Slow |
| `extract-sample-data-fast.py` | Quick extraction for testing | 10-50 rows | 10s | Fast |
| `validate-views-with-timeout.sql` | View validation and health check | N/A | 30s | Fast |

Use `extract-sample-data-fast.py` for:
- Quick testing and development
- CI/CD pipelines
- View validation
- Debugging empty views

Use `extract-sample-data.sql` for:
- Production-like test data
- Comprehensive temporal coverage
- Data quality analysis
- Statistical distributions

## See Also

- [SAMPLE_DATA_EXTRACTION.md](../SAMPLE_DATA_EXTRACTION.md) - Comprehensive extraction documentation
- [README-SCHEMA-MAINTENANCE.md](../README-SCHEMA-MAINTENANCE.md) - Schema maintenance guide
- [DATABASE_VIEW_INTELLIGENCE_CATALOG.md](../../DATABASE_VIEW_INTELLIGENCE_CATALOG.md) - View catalog
