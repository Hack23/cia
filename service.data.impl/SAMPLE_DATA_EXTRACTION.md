# Sample Data Extraction Enhancement

## Overview

The sample data extraction script has been enhanced to provide comprehensive coverage of all database tables and views with increased sample size for better testing and debugging capabilities.

## What's New

### Previous Behavior (v1.0)
- Extracted only **10 rows** per table/view
- Only extracted from **9 specific tables** (hardcoded)
- Only extracted from **4 specific views** (hardcoded)
- Total: ~13 CSV files generated

### Enhanced Behavior (v2.0)
- Extracts **50 rows** per table/view
- Extracts from **ALL 93+ tables** dynamically (excluding internal tables)
- Extracts from **ALL 80+ views** dynamically (both regular and materialized)
- Total: **170+ CSV files** generated with comprehensive coverage

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
- **Tables**: All user tables (excludes Quartz scheduler and Liquibase tables)
- **Views**: All regular views
- **Materialized Views**: All materialized views
- **Manifest**: Complete metadata file listing all extractions

### 4. Smart Filtering
Automatically skips:
- Internal Quartz scheduler tables (`qrtz_*`)
- Liquibase change tracking tables (`databasechange*`)
- Empty tables and views (no data to extract)

### 5. Progress Tracking
- Phase 1: Analysis - reports row counts for each object
- Phase 2: Extraction - shows extraction progress
- Phase 3: Manifest generation - creates metadata
- Phase 4: Column mapping - documents view dependencies

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

## Output Files

### Table Sample Files
```
table_person_data_sample.csv           (50 rows from person_data)
table_assignment_data_sample.csv       (50 rows from assignment_data)
table_document_data_sample.csv         (50 rows from document_data)
... (90+ more table files)
```

### View Sample Files
```
view_riksdagen_politician_sample.csv   (50 rows)
view_riksdagen_party_sample.csv        (50 rows)
view_riksdagen_committee_sample.csv    (50 rows)
... (80+ more view files)
```

### Metadata Files
```
sample_data_manifest.csv              (List of all extracted files)
view_column_mapping.csv               (View-to-column mappings)
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
  Extraction strategy: Diverse sampling with DISTINCT for key columns

==========================================
=== EXTRACTING TABLE SAMPLE DATA      ===
==========================================

Analyzing tables for sample data extraction...

Table agency: 15 rows available
Table application_action_event: 12450 rows available
Table assignment_data: 19991 rows available
... (90+ more tables)

Table analysis summary:
  Tables with data: 85
  Empty tables: 8

Extracting sample data from tables...

Extracting: agency
Extracting: application_action_event
Extracting: assignment_data
... (85+ extractions)

==========================================
=== EXTRACTING VIEW SAMPLE DATA       ===
==========================================

Analyzing views for sample data extraction...

View view_riksdagen_politician: 2071 rows available (VIEW)
View view_riksdagen_party: 11 rows available (VIEW)
View view_riksdagen_vote_data_ballot_summary: 13456 rows available (MATERIALIZED VIEW)
... (80+ more views)

View analysis summary:
  Views with data: 42
  Empty views: 38

Extracting sample data from views...

Extracting: view_riksdagen_politician (VIEW)
Extracting: view_riksdagen_party (VIEW)
... (42+ extractions)

==========================================
=== GENERATING MANIFEST FILE          ===
==========================================

==========================================
=== GENERATING VIEW COLUMN MAPPING    ===
==========================================

==================================================
Sample data extraction completed
Finished: Wed Nov 19 02:42:31 CET 2025
==================================================

Total CSV files: 129
  - Tables: 85
  - Views: 42
  - Other: 2
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

### 3. Documentation Examples
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
    '\copy (SELECT * FROM %I.%I ORDER BY random() LIMIT 50) TO ''table_%s_sample.csv'' WITH CSV HEADER',
    schemaname, tablename, tablename
)
FROM pg_tables
WHERE schemaname = 'public'
ORDER BY tablename
\gexec
```

### Why \gexec?

1. **Client-side execution**: `\copy` runs on the client, can write to current directory
2. **Dynamic generation**: No need to hardcode table/view names
3. **Error resilience**: Continues even if some extractions fail
4. **Clean output**: Each command shows its own progress

## Performance Considerations

### Extraction Time
- **Small database** (~1GB): 1-2 minutes
- **Medium database** (~10GB): 3-5 minutes
- **Large database** (~100GB): 5-10 minutes

### Disk Space
- Each CSV file: typically 10KB - 5MB
- Total for all files: typically 50MB - 200MB
- Compression: Files compress well (gzip achieves 80-90% reduction)

## Troubleshooting

### Issue: "permission denied for table"
**Solution**: Ensure database user has SELECT permission on all tables/views

### Issue: "could not open file for writing"
**Solution**: Ensure write permission in output directory

### Issue: "ORDER BY random() is slow"
**Solution**: For very large tables (100M+ rows), consider using TABLESAMPLE instead

### Issue: "some views are empty"
**Solution**: Check TROUBLESHOOTING_EMPTY_VIEWS.md for view-specific debugging

## Future Enhancements

Potential improvements for future versions:

1. **Configurable sampling**: Allow custom SAMPLE_SIZE via command-line
2. **Parallel extraction**: Extract multiple tables concurrently
3. **Smart sampling**: Use TABLESAMPLE for very large tables
4. **Compression**: Automatically compress CSV files
5. **Delta extraction**: Only extract changed data since last extraction
6. **Schema export**: Include CREATE TABLE statements
7. **Foreign key aware**: Ensure referential integrity in sampled data

## Related Documentation

- [TROUBLESHOOTING_EMPTY_VIEWS.md](../../TROUBLESHOOTING_EMPTY_VIEWS.md) - Debug empty views
- [DATABASE_VIEW_INTELLIGENCE_CATALOG.md](../../DATABASE_VIEW_INTELLIGENCE_CATALOG.md) - View catalog
- [README-SCHEMA-MAINTENANCE.md](README-SCHEMA-MAINTENANCE.md) - Schema maintenance guide
- [sample_data/README.md](src/main/resources/sample_data/README.md) - Sample data directory

## Version History

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
