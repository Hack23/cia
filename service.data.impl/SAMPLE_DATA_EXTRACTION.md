# Sample Data Extraction Enhancement

## Overview

The sample data extraction script provides comprehensive coverage of all database tables and views with increased sample size for better testing and debugging capabilities.

## Verified Coverage Statistics (v2.1)

**Last Validated**: 2025-11-24  
**Database Schema**: cia_dev  
**PostgreSQL Version**: 16.x+

### Extraction Coverage

| Object Type | Total in Schema | Extracted | Coverage % | Status |
|-------------|----------------|-----------|------------|--------|
| **Base Tables** | 93 | 80 | 86.0% | ✅ Comprehensive |
| **Regular Views** | 56 | 56 | 100% | ✅ Complete |
| **Materialized Views** | 28 | 28 | 100% | ✅ Complete |
| **Distinct Value Sets** | N/A | 8 | N/A | ✅ Custom extractions |
| **Metadata Files** | N/A | 3 | N/A | ✅ Manifest + mapping + statistics |
| **TOTAL** | 177 | 164 | 92.66% | ✅ Near-complete |

### Intentionally Skipped Objects

**Tables Not Extracted (13):**
- `qrtz_*` (11 tables) - Quartz scheduler internal tables
- `databasechangelog` (1 table) - Liquibase tracking table
- `databasechangeloglock` (1 table) - Liquibase locking table

**Explanation**: These are internal framework tables not relevant for application data analysis.

### Expected Output Files

**Total CSV Files**: ~175
- **Table CSVs**: 80 files (table_*_sample.csv)
- **View CSVs**: 84 files (view_*_sample.csv) - includes regular and materialized views
- **Distinct Value CSVs**: 8 files (distinct_*_values.csv)
- **Metadata CSVs**: 3 files (sample_data_manifest.csv, view_column_mapping.csv, extraction_statistics.csv)

## What's New

### Previous Behavior (v1.0)
- Extracted only **10 rows** per table/view
- Only extracted from **9 specific tables** (hardcoded)
- Only extracted from **4 specific views** (hardcoded)
- Total: ~13 CSV files generated

### Enhanced Behavior (v2.1)
- Extracts **50 rows** per table/view
- Extracts from **ALL 80 tables** dynamically (excluding 13 internal framework tables)
- Extracts from **ALL 84 views** dynamically (both regular and materialized)
- Extracts **distinct value sets** for important columns used in views
- Generates **extraction statistics** with coverage metrics
- **Automated validation** of extraction completeness
- Total: **~175 CSV files** generated (80 tables + 84 views + 8 distinct sets + 3 metadata)

## Key Features

### 1. Dynamic Discovery
- Automatically discovers all tables and views in the database
- No hardcoded lists to maintain
- Adapts to schema changes automatically

### 2. Diverse Sampling
- Uses `ORDER BY random()` for variety in sampled data
- Ensures diverse representation of data patterns
- Better for testing edge cases

### 3. Comprehensive Coverage
- **Tables**: All user tables (excludes 11 Quartz scheduler and 2 Liquibase tables)
- **Views**: All 56 regular views
- **Materialized Views**: All 28 materialized views
- **Distinct Value Sets**: Important columns with counts for view analysis
- **Statistics**: Extraction coverage metrics and validation results
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
- Refreshes each view and validates row counts
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
extraction_statistics.csv             (Coverage statistics and metrics)
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

→ [1/28] Refreshing: public.view_riksdagen_committee_ballot_decision_party_summary
  ✓ Refreshed successfully - 34558 rows

→ [2/28] Refreshing: public.view_riksdagen_committee_ballot_decision_politician_summary
  ✓ Refreshed successfully - 156789 rows

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

### 4. Documentation Examples
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
**Solution**: This is expected behavior as of v2.2+. The extraction script now automatically refreshes all materialized views in Phase 0 before extraction. If views remain empty after refresh:
- Check the source tables have data
- Review view definitions for date filters or other constraints
- Consult TROUBLESHOOTING_EMPTY_VIEWS.md for view-specific debugging
- Some views may legitimately be empty based on data filters (e.g., date ranges)

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

- **v2.2** (2025-12-29): Added automatic materialized view refresh in Phase 0
  - Dynamically discovers and refreshes all materialized views before extraction
  - Validates row counts after refresh
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
