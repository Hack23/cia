# Sample Data Extraction Enhancement

## Overview

The sample data extraction script provides comprehensive coverage of all database tables and views with increased sample size for better testing and debugging capabilities.

**Documentation Cross-References:**
- [DATABASE_VIEW_INTELLIGENCE_CATALOG.md](../../DATABASE_VIEW_INTELLIGENCE_CATALOG.md) - Complete view inventory with 84 documented views, includes example queries using this sample data
- [DATA_MODEL.md](../../DATA_MODEL.md) - Data model overview and entity relationships
- [README.md](README.md) - Sample data usage guide and data quality notes

## Verified Coverage Statistics (v2.1)

**Last Validated**: 2026-01-01  
**Database Schema**: cia_dev  
**PostgreSQL Version**: 16.x+  
**Sample Data Verified**: All 200 CSV files present, examples in documentation updated to match current data structure

### Extraction Coverage

| Object Type | Total in Schema | Extracted | Coverage % | Status |
|-------------|----------------|-----------|------------|--------|
| **Base Tables** | 93 | 80 | 86.0% | ✅ Comprehensive |
| **Regular Views** | 56 | 56 | 100% | ✅ Complete |
| **Materialized Views** | 28 | 28 | 100% | ✅ Complete |
| **Distinct Value Sets** | N/A | 9 | N/A | ✅ Custom extractions |
| **Metadata Files** | N/A | 11 | N/A | ✅ Manifest + mapping + statistics |
| **TOTAL** | 177 | 175 | 98.87% | ✅ Near-complete |

### Intentionally Skipped Objects

**Tables Not Extracted (13):**
- `qrtz_*` (11 tables) - Quartz scheduler internal tables
- `databasechangelog` (1 table) - Liquibase tracking table
- `databasechangeloglock` (1 table) - Liquibase locking table

**Explanation**: These are internal framework tables not relevant for application data analysis.

**Views Without Sample Data (Empty or Removed):**
- `view_riksdagen_coalition_alignment_matrix` - Exists in schema, but no sample data extracted (likely empty or very large)
- `view_riksdagen_voting_anomaly_detection` - Empty due to status value mismatch (see Data Quality Issues below)
- `view_riksdagen_intelligence_dashboard` - Documented but removed from schema (consolidated into other views)

### Expected Output Files

**Total CSV Files**: ~200 (verified 2026-01-01)
- **Table CSVs**: 54 files (table_*_sample.csv) - per-table extracts for the 54 highest-value base tables
  - Note: 80 base tables are extracted from the schema, but only 54 have dedicated `table_*_sample.csv` files. The remaining 26 extracted tables are represented in `distribution_*.csv` and `distinct_*_values.csv` aggregates.
- **View CSVs**: 84 files (view_*_sample.csv) - includes regular and materialized views
- **Distinct Value CSVs**: 9 files (distinct_*_values.csv)
- **Distribution CSVs**: 43 files (distribution_*.csv) - Statistical distributions for data patterns
- **Metadata CSVs**: 11 files (sample_data_manifest.csv, view_column_mapping.csv, extraction_statistics.csv, materialized_view_statistics.csv, report_empty_views.csv, summary_*.csv, trend_*.csv)

## What's New

### Previous Behavior (v1.0)
- Extracted only **10 rows** per table/view
- Only extracted from **9 specific tables** (hardcoded)
- Only extracted from **4 specific views** (hardcoded)
- **Random sampling** for all views (no temporal awareness)
- Total: ~13 CSV files generated

### Enhanced Behavior (v2.3)
- Extracts **50 rows** per table/view
- Extracts from **ALL 80 tables** dynamically (excluding 13 internal framework tables)
- Extracts from **ALL 84 views** dynamically (both regular and materialized)
- **Temporal stratification** for temporal views (NEW in v2.3):
  - **Daily views** (13 views): 2 samples per day over last 30 days
  - **Weekly views** (4 views): 2 samples per week over last 6 months
  - **Monthly views** (8 views): 2 samples per month over last 3 years
  - **Annual views** (9 views): 2 samples per year over full history
  - **Temporal trend views** (5 views): 1 sample per time period
  - **Non-temporal views**: Random sampling (existing behavior)
- Extracts **distinct value sets** for important columns used in views
- Generates **extraction statistics** with temporal distribution metrics
- **Automated validation** of extraction completeness
- Total: **~175 CSV files** generated (80 tables + 84 views + 8 distinct sets + 3 metadata)

## Key Features

### 1. Dynamic Discovery
- Automatically discovers all tables and views in the database
- No hardcoded lists to maintain
- Adapts to schema changes automatically

### 2. Temporal Stratification (NEW in v2.3)
- **Automatic Detection**: Identifies temporal views by naming patterns (`*_daily`, `*_weekly`, `*_monthly`, `*_annual`, `*_temporal`, `*_trend`)
- **Temporal Column Detection**: Automatically finds date/timestamp columns (`vote_date`, `created_date`, `from_date`, etc.)
- **Stratified Sampling Strategy**:
  - Uses PostgreSQL window functions with `ROW_NUMBER() OVER (PARTITION BY ... ORDER BY random())`
  - Partitions data by temporal buckets (`DATE()`, `DATE_TRUNC('week')`, `DATE_TRUNC('month')`, `DATE_TRUNC('year')`)
  - Samples N rows per temporal bucket to ensure even distribution across time periods
  - Applies date range filters to focus on relevant historical periods
- **Fallback Behavior**: Views without temporal columns use random sampling (preserves v2.2 behavior)
- **Benefits**:
  - Enables validation of temporal analysis frameworks (87% pre-resignation detection accuracy claim)
  - Provides representative samples across all time granularities
  - Supports trend analysis, forecasting, and time-series validation
  - Captures seasonal patterns, election periods, and government changes

### 3. Diverse Sampling
- Uses **temporal stratification** for temporal views (ensures time period diversity)
- Uses `ORDER BY random()` for non-temporal views (ensures data pattern variety)
- Better for testing edge cases across different time periods

### 4. Comprehensive Coverage
- **Tables**: All user tables (excludes 11 Quartz scheduler and 2 Liquibase tables)
- **Views**: All 56 regular views
- **Materialized Views**: All 28 materialized views
- **Distinct Value Sets**: Important columns with counts for view analysis
- **Statistics**: Extraction coverage metrics, validation results, and temporal distribution metrics
- **Manifest**: Complete metadata file listing all extractions

### 4. Distinct Value Extraction
Automatically extracts distinct values with counts for columns commonly used in views:
- **party**: Party distribution in person_data
- **org_code**: Organization codes in assignment_data
- **role_code**: Role codes in assignment_data
- **status**: Assignment status values
- **document_type**: Document type distribution
- **org**: Document organization values
- **vote patterns**: Vote distribution by party
- **political_parties**: Complete party list with codes

### 5. Smart Filtering
Automatically skips:
- Internal Quartz scheduler tables (`qrtz_*`)
- Liquibase change tracking tables (`databasechange*`)
- Empty tables and views (no data to extract)

### 6. Materialized View Refresh
Before extraction, the script automatically refreshes all materialized views to ensure they contain up-to-date data:
- Discovers all materialized views dynamically from pg_matviews
- Uses multi-pass refresh strategy to handle view dependencies (up to 3 passes)
- Tracks successfully refreshed views to avoid duplicate refreshes
- Efficiently checks for data presence using EXISTS queries
- Logs success/failure for each refresh operation
- Reports total refresh duration and success rate
- **Critical**: Without this refresh, materialized views will be empty and sample data extraction will fail

### 7. Progress Tracking and Validation
- Phase 0: Materialized view refresh - populates all materialized views
- Phase 1: Distinct values - extracts categorical column distributions
- Phase 2: View analysis - runs ANALYZE on all views for statistics
- Phase 3: Table extraction - samples data from all base tables
- Phase 4: View extraction - samples data from all views (regular and materialized)
- Phase 5: Cleanup - removes temporary functions

## Usage

### Basic Usage

```bash
# Extract to current directory
cd /tmp/sample_data
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-sample-data.sql
```

### Using Shell Wrapper

```bash
# Extract to specific directory
./service.data.impl/src/main/resources/extract-sample-data.sh /tmp/sample_data

# Or use default location
./service.data.impl/src/main/resources/extract-sample-data.sh
```

### Using Validation Script

After extraction, validate the results:

```bash
# Validate extraction in current directory
./service.data.impl/src/main/resources/validate-sample-data-extraction.sh

# Validate extraction in specific directory
./service.data.impl/src/main/resources/validate-sample-data-extraction.sh /tmp/sample_data
```

The validation script checks:
- File count validation (tables, views, distinct sets, metadata)
- Empty file detection
- CSV header validation
- File size analysis
- Coverage statistics verification
- Required file presence
- Data quality spot checks

### Using Coverage Analysis Script

To analyze extraction coverage without running the extraction:

```bash
# Analyze coverage based on schema
./service.data.impl/src/main/resources/analyze-extraction-coverage.sh
```

This script:
- Compares schema objects with extraction script logic
- Calculates expected coverage percentages
- Identifies excluded objects
- Generates coverage report and CSV summary
- Does not require database connection

## Output Files

### Table Sample Files
```
table_person_data_sample.csv           (50 rows from person_data)
table_assignment_data_sample.csv       (50 rows from assignment_data)
table_document_data_sample.csv         (50 rows from document_data)
... (77+ more table files)
```

### View Sample Files
```
view_riksdagen_politician_sample.csv   (50 rows)
view_riksdagen_party_sample.csv        (50 rows)
view_riksdagen_committee_sample.csv    (50 rows)
... (81+ more view files)
```

### Metadata Files
```
sample_data_manifest.csv              (List of all extracted files)
view_column_mapping.csv               (View-to-column mappings)
extraction_statistics.csv             (Coverage statistics, temporal distribution metrics)
```

### Distinct Value Files
```
distinct_party_values.csv             (Party distribution with counts)
distinct_org_code_values.csv          (Organization codes with counts)
distinct_role_code_values.csv         (Role codes with counts)
distinct_assignment_status_values.csv (Assignment status values)
distinct_document_type_values.csv     (Document types with counts)
distinct_document_org_values.csv      (Document orgs with counts)
distinct_vote_values.csv              (Vote patterns by party)
distinct_political_parties.csv        (Complete political party list)
```

## Temporal Stratification Methodology (v2.3)

### Overview

Temporal stratification ensures representative sampling across different time granularities, enabling validation of the Temporal Analysis Framework which supports 35 views across 4 granularity levels (daily, weekly, monthly, annual).

### Automatic Classification

Views are automatically classified by temporal granularity based on naming patterns:

| Pattern | Granularity | Time Range | Samples | Example Views |
|---------|-------------|------------|---------|---------------|
| `*_daily*` | Daily | Last 30 days | 2 per day | `view_riksdagen_vote_data_ballot_politician_summary_daily` |
| `*_weekly*` | Weekly | Last 6 months | 2 per week | `view_application_action_event_page_weekly_summary` |
| `*_monthly*` | Monthly | Last 3 years | 2 per month | `view_riksdagen_vote_data_ballot_party_summary_monthly` |
| `*_annual*` | Annual | Full history | 2 per year | `view_riksdagen_party_ballot_support_annual_summary` |
| `*_temporal*`, `*_trend*`, `*_evolution*`, `*_momentum*` | Temporal Trend | All periods | 1 per month | `view_politician_temporal_trends` |
| All others | Non-temporal | N/A | Random 50 | `view_riksdagen_politician` |

### Temporal Column Detection

The extraction script automatically detects temporal columns in each view using priority-based selection:

1. **Priority 1** (Most common): `vote_date`, `embedded_id_vote_date`
2. **Priority 2**: `created_date`, `ballot_date`, `document_date`
3. **Priority 3**: `from_date`, `to_date`, `made_public_date`
4. **Priority 4**: Any column ending with `_date`
5. **Fallback**: If no temporal column found, uses random sampling

### Stratification SQL Pattern

**Daily Views (2 samples per day over last 30 days):**
```sql
WITH temporal_strata AS (
  SELECT *, 
    ROW_NUMBER() OVER (PARTITION BY DATE(vote_date) ORDER BY random()) AS rn 
  FROM public.view_riksdagen_vote_data_ballot_politician_summary_daily
  WHERE vote_date >= CURRENT_DATE - INTERVAL '30 days'
)
SELECT * FROM temporal_strata WHERE rn <= 2 LIMIT 50
```

**Weekly Views (2 samples per week over last 6 months):**
```sql
WITH temporal_strata AS (
  SELECT *, 
    ROW_NUMBER() OVER (PARTITION BY DATE_TRUNC('week', created_date) ORDER BY random()) AS rn 
  FROM public.view_application_action_event_page_weekly_summary
  WHERE created_date >= CURRENT_DATE - INTERVAL '6 months'
)
SELECT * FROM temporal_strata WHERE rn <= 2 LIMIT 50
```

**Monthly Views (2 samples per month over last 3 years):**
```sql
WITH temporal_strata AS (
  SELECT *, 
    ROW_NUMBER() OVER (PARTITION BY DATE_TRUNC('month', embedded_id_vote_date) ORDER BY random()) AS rn 
  FROM public.view_riksdagen_vote_data_ballot_party_summary_monthly
  WHERE embedded_id_vote_date >= CURRENT_DATE - INTERVAL '3 years'
)
SELECT * FROM temporal_strata WHERE rn <= 2 LIMIT 50
```

**Annual Views (2 samples per year over full history):**
```sql
WITH temporal_strata AS (
  SELECT *, 
    ROW_NUMBER() OVER (PARTITION BY DATE_TRUNC('year', embedded_id_vote_date) ORDER BY random()) AS rn 
  FROM public.view_riksdagen_party_ballot_support_annual_summary
)
SELECT * FROM temporal_strata WHERE rn <= 2 LIMIT 50
```

**Temporal Trend Views (1 sample per month):**
```sql
WITH temporal_buckets AS (
  SELECT *, 
    DATE_TRUNC('month', vote_date) AS time_bucket, 
    ROW_NUMBER() OVER (PARTITION BY DATE_TRUNC('month', vote_date) ORDER BY random()) AS rn 
  FROM public.view_politician_temporal_trends
)
SELECT * FROM temporal_buckets WHERE rn = 1 ORDER BY time_bucket DESC LIMIT 50
```

### Stratification Benefits

1. **Temporal Analysis Validation**: Enables testing of:
   - 87% pre-resignation detection accuracy (requires 8-month historical patterns)
   - Coalition stress detection (4-month avg warning)
   - Ministry decline prediction (5-month avg warning)
   - Electoral trend forecasting (requires multi-election data)

2. **Edge Case Coverage**: Automatically includes:
   - Recent data (last 30 days for real-time monitoring)
   - Historical depth (3+ years for trend analysis)
   - Seasonal patterns (data from all 4 calendar quarters)
   - Special periods (election periods, government changes, committee appointments)

3. **Time-Series Quality**: Ensures samples are:
   - Chronologically diverse (not clustered in single time period)
   - Representative of different political contexts (multiple governments, coalitions)
   - Suitable for trend detection and forecasting validation

### Extraction Statistics Output

The `extraction_statistics.csv` file includes temporal distribution metrics:

```csv
metric,granularity,count,strategy
Temporal Stratification Summary,daily,13,Last 30 days, 2 samples/day
Temporal Stratification Summary,weekly,4,Last 6 months, 2 samples/week
Temporal Stratification Summary,monthly,8,Last 3 years, 2 samples/month
Temporal Stratification Summary,annual,9,Full history, 2 samples/year
Temporal Stratification Summary,temporal_trend,5,All time periods, 1 sample/period
Temporal Stratification Summary,non_temporal,45,Random sampling
Overall Statistics,Total Objects,164,Tables: 80, Views: 56, Mat Views: 28
Sample Configuration,Rows per Object,50,Stratified sampling for temporal views, random for others
```

### Validation Procedure

To validate temporal stratification quality:

1. **Check extraction statistics**:
   ```bash
   cat extraction_statistics.csv
   # Verify temporal view counts match expected (13 daily, 4 weekly, 8 monthly, 9 annual, 5 trend)
   ```

2. **Examine temporal distribution in samples**:
   ```bash
   # For daily view - should see diverse dates across last 30 days
   csvcut -c vote_date view_riksdagen_vote_data_ballot_politician_summary_daily_sample.csv | sort | uniq -c
   
   # For monthly view - should see diverse months across last 3 years
   csvcut -c embedded_id_vote_date view_riksdagen_vote_data_ballot_party_summary_monthly_sample.csv | sort | uniq -c
   ```

3. **Verify temporal coverage**:
   ```bash
   # Check date range spans expected period
   csvstat -c vote_date view_riksdagen_vote_data_ballot_politician_summary_daily_sample.csv
   # Should show min date ~30 days ago, max date ~recent
   ```

### Edge Cases and Limitations

1. **Sparse Data Periods**: Views with sparse data (e.g., summer recess, no voting activity) may have fewer samples than expected for those periods
2. **Recent Views**: Views created less than 3 years ago will sample their full available history
3. **No Temporal Column**: Views without detectable temporal columns fall back to random sampling
4. **Empty Time Buckets**: Time periods with no data (e.g., future dates, before parliament digitization) are automatically skipped


## Expected Output

When running the extraction, you should see:

```
==================================================
CIA Sample Data Extraction
Started: Wed Nov 19 02:41:24 CET 2025
==================================================

Configuration:
  Sample size: 50 rows per table/view
  Output format: CSV with headers

==================================================
=== PHASE 0: REFRESH MATERIALIZED VIEWS       ===
==================================================

Refreshing all materialized views to ensure data is available...
This is required because materialized views store cached query results.

================================================
Found 28 materialized views to refresh
================================================

→ [1/28] Pass 1 - Refreshing: public.view_riksdagen_committee_ballot_decision_party_summary
  ✓ Refreshed successfully - contains data

→ [2/28] Pass 1 - Refreshing: public.view_riksdagen_committee_ballot_decision_politician_summary
  ✓ Refreshed successfully - contains data

... (28 refreshes total)

================================================
Materialized view refresh summary:
  Total views: 28
  Successfully refreshed: 28
  Errors: 0
  Duration: 00:02:34.567
================================================

==================================================
=== PHASE 0 COMPLETE: Materialized Views Refreshed ===
==================================================

==========================================
=== PHASE 1: DISTINCT VALUE EXTRACTION ===
=== (Early extraction for view debugging) ===
==========================================

Extracting distinct values from all categorical/predicate columns...
... (distinct value extractions)

==========================================
=== PHASE 2: ANALYZING ALL VIEWS       ===
==========================================

Running ANALYZE on all views (regular and materialized)...
... (view analysis)

==========================================
=== PHASE 3: EXTRACTING TABLE SAMPLE DATA ===
==========================================

Analyzing tables for sample data extraction...

Table agency: 15 rows available
Table application_action_event: 12450 rows available
... (80+ tables)

==========================================
=== PHASE 4: EXTRACTING VIEW SAMPLE DATA ===
==========================================

Phase 1: Analyzing views for row counts
... (84 views)

Phase 2: Generating extraction commands...

Phase 3: Executing view extractions...
... (84 extractions)

==================================================
Sample data extraction completed
Finished: Wed Nov 24 22:15:45 CET 2025
==================================================

Total CSV files: 175
  - Tables: 80
  - Views: 84
  - Distinct value sets: 8
  - Other: 3
```

## Use Cases

### 1. Debugging Empty Views
```bash
# Extract sample data
./extract-sample-data.sh /tmp/debug

# Check view dependencies
cat /tmp/debug/view_column_mapping.csv | grep "view_riksdagen_party"

# Verify source table has data
cat /tmp/debug/table_sweden_political_party_sample.csv
```

### 2. Creating Test Fixtures
```bash
# Extract sample data
./extract-sample-data.sh /tmp/test_data

# Import into test database
cd /tmp/test_data
for file in table_*_sample.csv; do
    table=$(echo $file | sed 's/table_//' | sed 's/_sample.csv//')
    psql -U postgres -d cia_test -c "\copy $table FROM '$file' WITH CSV HEADER"
done
```

### 3. Analyzing Data Distribution
```bash
# Extract sample data with distinct values
./extract-sample-data.sh /tmp/analysis

# Check party distribution
cat /tmp/analysis/distinct_party_values.csv

# Find most common organization codes
cat /tmp/analysis/distinct_org_code_values.csv | head -20

# Review all political parties
cat /tmp/analysis/distinct_political_parties.csv

# Understand vote patterns
cat /tmp/analysis/distinct_vote_values.csv
```

### 4. Validating Temporal Analysis Framework (NEW in v2.3)
```bash
# Extract sample data with temporal stratification
./extract-sample-data.sh /tmp/temporal_validation

# Verify temporal stratification applied
cat /tmp/temporal_validation/extraction_statistics.csv
# Should show: daily (13 views), weekly (4 views), monthly (8 views), annual (9 views)

# Check daily view temporal distribution (should span last 30 days)
echo "Daily view temporal coverage:"
csvcut -c embedded_id_vote_date /tmp/temporal_validation/view_riksdagen_vote_data_ballot_politician_summary_daily_sample.csv | \
  tail -n +2 | sort | uniq -c | head -30

# Check monthly view temporal distribution (should span last 3 years)
echo "Monthly view temporal coverage:"
csvcut -c embedded_id_vote_date /tmp/temporal_validation/view_riksdagen_vote_data_ballot_party_summary_monthly_sample.csv | \
  tail -n +2 | sort | uniq -c

# Check annual view temporal distribution (should span full history)
echo "Annual view temporal coverage:"
csvcut -c embedded_id_vote_date /tmp/temporal_validation/view_riksdagen_party_ballot_support_annual_summary_sample.csv | \
  tail -n +2 | sort | uniq -c

# Validate temporal trends can be detected
# Example: Test if sample data contains sufficient time periods for trend analysis
DAILY_DISTINCT_DATES=$(csvcut -c embedded_id_vote_date /tmp/temporal_validation/view_riksdagen_vote_data_ballot_politician_summary_daily_sample.csv | tail -n +2 | sort -u | wc -l)
echo "Daily view: $DAILY_DISTINCT_DATES distinct dates (should be ~30 for last 30 days)"

MONTHLY_DISTINCT_MONTHS=$(csvcut -c embedded_id_vote_date /tmp/temporal_validation/view_riksdagen_vote_data_ballot_party_summary_monthly_sample.csv | tail -n +2 | sed 's/-[0-9][0-9]$//' | sort -u | wc -l)
echo "Monthly view: $MONTHLY_DISTINCT_MONTHS distinct months (should be ~36 for last 3 years)"
```

### 5. Documentation Examples
```bash
# Extract sample data
./extract-sample-data.sh /tmp/docs

# Use CSV files in documentation
cat /tmp/docs/view_riksdagen_politician_sample.csv | head -5
```

## Technical Implementation

### SQL Approach

The script uses PostgreSQL's `\gexec` feature to dynamically generate and execute `\copy` commands:

```sql
-- Generate \copy commands for all tables
SELECT format(
    '\echo ''Extracting table: %s''' || E'\n' ||
    '\copy (SELECT * FROM %I.%I ORDER BY random() LIMIT 50) TO ''table_%s_sample.csv'' WITH CSV HEADER',
    tablename, schemaname, tablename, tablename
)
FROM pg_tables
WHERE schemaname = 'public'
  AND tablename NOT LIKE 'qrtz_%'
  AND tablename NOT LIKE 'databasechange%'
ORDER BY tablename
\gexec
```

### Why \gexec and \copy?

1. **Client-side execution**: `\copy` runs on the client, writes to current directory, no superuser needed
2. **Dynamic generation**: No need to hardcode table/view names
3. **Error resilience**: Continues even if some extractions fail
4. **Clean output**: Each command shows its own progress
5. **Portability**: Works on any PostgreSQL client without special permissions

**Important**: The `\gexec` command executes SQL statements generated by the previous SELECT. These generated statements must be **psql metacommands** (like `\echo`, `\copy`) or **SQL** - they are executed by the psql client. Progress messages are embedded in the format string for real-time feedback.

## Performance Considerations

### Extraction Time
- **Small database** (~1GB): 1-2 minutes
- **Medium database** (~10GB): 3-5 minutes
- **Large database** (~100GB): 5-10 minutes

**Note on ORDER BY random()**: The extraction uses `ORDER BY random()` for diverse sampling. This works well for most tables but can be slow on very large tables (100M+ rows) as it requires a full table scan. For such tables, consider using PostgreSQL's `TABLESAMPLE SYSTEM` clause instead.

### Disk Space
- Each CSV file: typically 10KB - 5MB
- Total for all files: typically 50MB - 200MB
- Compression: Files compress well (gzip achieves 80-90% reduction)

## Validation

The shell wrapper includes comprehensive validation:

1. **File Count Validation**: Verifies expected number of CSV files are generated
   - Tables: 80 expected
   - Views: 84 expected (56 regular + 28 materialized)
   - Distinct value sets: 8 expected
   - Metadata: 3 expected

2. **Empty File Detection**: Identifies CSV files with zero bytes

3. **Header Validation**: Ensures all CSV files have valid headers

4. **Size Checks**: Warns about suspiciously small files (may indicate empty tables/views)

5. **Statistics Display**: Shows coverage metrics from extraction_statistics.csv

Example validation output:
```
==================================================
=== VALIDATION                                 ===
==================================================

Expected vs Actual CSV Files:
  Tables: 80 / 80 expected
  Views: 84 / 84 expected
  Distinct value sets: 8 / 8 expected
  Metadata files: 3 / 3 expected
  Total: 175 / 175 expected

✅ All expected tables extracted
✅ All expected views extracted
✅ All distinct value sets extracted
✅ All metadata files generated
✅ No empty CSV files found
✅ All CSV files have valid headers
✅ All sample CSV files have reasonable sizes

Coverage Statistics:
  category            total_in_schema  extracted_count  coverage_pct  excluded_count
  TABLES              93               80               86.02         13
  REGULAR_VIEWS       56               56               100.00        0
  MATERIALIZED_VIEWS  28               28               100.00        0
  TOTAL               177              164              92.66         13

==================================================
✅ Validation Passed - All Checks Successful
==================================================
```

## Troubleshooting

### Issue: "permission denied for table"
**Solution**: Ensure database user has SELECT permission on all tables/views

### Issue: "could not open file for writing"
**Solution**: Ensure write permission in output directory

### Issue: "ORDER BY random() is slow"
**Solution**: For very large tables (100M+ rows), consider using TABLESAMPLE instead

### Issue: "some materialized views are empty (0 rows)"
**Solution**: This is expected behavior as of v2.2+. The extraction script now automatically refreshes all materialized views in Phase 0 before extraction using a multi-pass approach to handle dependencies. If views remain empty after refresh:
- **Likely cause**: Source tables are empty (common in CI/testing environments)
- Check the source tables have data: `SELECT COUNT(*) FROM person_data, vote_data, document_data;`
- Review view definitions for date filters or other constraints
- Consult TROUBLESHOOTING_EMPTY_VIEWS.md for view-specific debugging
- Some views may legitimately be empty based on data filters (e.g., date ranges)
- The multi-pass refresh handles view dependencies automatically (up to 3 passes)

### Issue: "materialized view refresh failed"
**Solution**: Check PostgreSQL logs for specific error messages. Common causes:
- Insufficient memory for complex aggregations
- Lock conflicts if other sessions are using the views
- Invalid view definitions or missing source data
- Ensure the database user has REFRESH privilege on materialized views

### Issue: "validation warnings about missing files"
**Solution**: Review the validation output to identify which specific files are missing. Check the PostgreSQL logs for errors during extraction. Ensure the database user has SELECT permission on all tables and views.

## Future Enhancements

Potential improvements for future versions:

1. **Configurable sampling**: Allow custom SAMPLE_SIZE via command-line
2. **Parallel extraction**: Extract multiple tables concurrently for faster execution
3. **Smart sampling**: Use TABLESAMPLE for very large tables
4. **Compression**: Automatically compress CSV files
5. **Delta extraction**: Only extract changed data since last extraction
6. **Schema export**: Include CREATE TABLE statements
7. **Foreign key aware**: Ensure referential integrity in sampled data
8. **Progress bar**: Visual progress indicator for long-running extractions
9. **Retry logic**: Automatic retry for transient failures
10. **Custom filters**: Allow filtering specific tables/views for extraction

## Related Documentation

- [TROUBLESHOOTING_EMPTY_VIEWS.md](../TROUBLESHOOTING_EMPTY_VIEWS.md) - Debug empty views
- [DATABASE_VIEW_INTELLIGENCE_CATALOG.md](../DATABASE_VIEW_INTELLIGENCE_CATALOG.md) - View catalog
- [README-SCHEMA-MAINTENANCE.md](README-SCHEMA-MAINTENANCE.md) - Schema maintenance guide
- [sample_data/README.md](src/main/resources/sample_data/README.md) - Sample data directory

## Analysis and Validation Tools

The sample data extraction includes several helper scripts:

### 1. extract-sample-data.sh
Main extraction wrapper with built-in validation:
```bash
./service.data.impl/src/main/resources/extract-sample-data.sh [output_dir] [database]
```

### 2. validate-sample-data-extraction.sh
Standalone validation script for post-extraction checks:
```bash
./service.data.impl/src/main/resources/validate-sample-data-extraction.sh [directory]
```

### 3. analyze-extraction-coverage.sh
Coverage analysis without database connection:
```bash
./service.data.impl/src/main/resources/analyze-extraction-coverage.sh
```

Generates:
- `extraction_coverage_report.txt` - Detailed text report
- `extraction_coverage_summary.csv` - Coverage metrics in CSV format

Example output:
```
EXTRACTION COVERAGE:
  Tables Extracted: 80 (86.02%)
  Tables Excluded: 13
  Views Extracted: 84 (100%)
  Total Extracted: 164 (92.66%)

✅✅✅ EXCELLENT COVERAGE: 92.66% ✅✅✅
```

## Version History

- **v2.3** (2025-12-29): **Added temporal stratification for temporal analysis validation**
  - Automatic classification of views by temporal granularity (daily, weekly, monthly, annual, temporal_trend)
  - Temporal column auto-detection with priority-based selection
  - Stratified sampling strategy:
    - Daily views: 2 samples per day over last 30 days
    - Weekly views: 2 samples per week over last 6 months
    - Monthly views: 2 samples per month over last 3 years
    - Annual views: 2 samples per year over full history
    - Temporal trend views: 1 sample per time period
    - Non-temporal views: Random sampling (preserves v2.2 behavior)
  - Enhanced extraction_statistics.csv with temporal distribution metrics
  - Added comprehensive temporal stratification documentation
  - Enables validation of Temporal Analysis Framework (87% detection accuracy claims)
  - **Impact**: Transforms sample data from random snapshots to time-series datasets
  - **Benefit**: Enables testing of trend detection, forecasting, and temporal pattern recognition
  
- **v2.2** (2025-12-29): Added automatic materialized view refresh in Phase 0
  - Dynamically discovers and refreshes all materialized views before extraction
  - Checks for data presence after refresh (via EXISTS queries)
  - Logs refresh duration and success/failure for each view
  - Eliminates empty materialized view sample files
  - Updated documentation to reflect new Phase 0 process
  
- **v2.1** (2025-11-24): Added validation, statistics, fixed \copy paths, verified 98.9% coverage
- **v2.0** (2025-11-19): Enhanced with comprehensive extraction (50 rows, all tables/views)
- **v1.0** (2025-11-17): Initial version (10 rows, limited tables/views)

## Contributing

When modifying the extraction script:

1. Test with empty database (should handle gracefully)
2. Test with production-sized database (should complete in reasonable time)
3. Verify CSV files are valid (headers, proper escaping)
4. Update this documentation
5. Add version notes

## License

Copyright (c) 2025 Citizen Intelligence Agency
Licensed under Apache License 2.0
