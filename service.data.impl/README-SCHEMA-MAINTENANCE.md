# Database Schema Maintenance Guide

This guide explains how to maintain and update the `full_schema.sql` file for the CIA application.

## Overview

The `full_schema.sql` file contains the complete database schema including:
- All table definitions
- All view definitions
- Database extensions
- Liquibase changelog tracking (databasechangelog and databasechangeloglock tables)

## Prerequisites

- PostgreSQL 16 installed and running
- Database user with appropriate permissions
- CIA application database (`cia_dev`) set up and populated

## Updating full_schema.sql

### Automated Update Script

To update the `full_schema.sql` file with the latest schema and Liquibase changelog data, use the following command:

```bash
cd /path/to/cia/repository

sudo -u postgres bash -c "(pg_dump -U postgres -d cia_dev --schema-only --no-owner --no-privileges; \
  pg_dump -U postgres -d cia_dev --data-only --no-owner --no-privileges \
    --table=public.databasechangelog \
    --table=public.databasechangeloglock)" > service.data.impl/src/main/resources/full_schema.sql
```

### What This Command Does

1. **First pg_dump**: Exports the complete database schema (tables, views, indexes, constraints, etc.) without owner or privilege information
2. **Second pg_dump**: Exports only the data from the Liquibase tracking tables:
   - `public.databasechangelog` - tracks all applied database migrations
   - `public.databasechangeloglock` - tracks migration lock status

### When to Update

Update the `full_schema.sql` file when:

1. **New Liquibase migrations are added** - After adding new db-changelog-*.xml files
2. **Schema changes are made** - After modifying table structures, views, or indexes
3. **Before major releases** - To ensure the schema file reflects the current state
4. **After database refactoring** - When optimizing or restructuring the database

## Testing the Schema

### 1. Test Fresh Database Creation

After updating `full_schema.sql`, verify it works for fresh installations:

```bash
# Drop and recreate test database
sudo -u postgres psql -c "DROP DATABASE IF EXISTS cia_test;"
sudo -u postgres psql -c "CREATE DATABASE cia_test;"
sudo -u postgres psql -c "GRANT ALL PRIVILEGES ON DATABASE cia_test TO eris;"

# Load the schema
sudo -u postgres psql -d cia_test -f service.data.impl/src/main/resources/full_schema.sql

# Verify the schema loaded correctly
sudo -u postgres psql -d cia_test -c "\dt" | wc -l  # Should show numerous tables
sudo -u postgres psql -d cia_test -c "SELECT COUNT(*) FROM databasechangelog;"  # Should show changelog entries
```

### 2. Test Application Startup

Test the application can start with the new schema:

```bash
# From the citizen-intelligence-agency directory
cd citizen-intelligence-agency
ant start
```

The application should:
- Start without errors
- Connect to the database successfully
- Load Drools rules without compilation errors
- Not attempt to run any Liquibase migrations (they should all be marked as executed)

### 3. Verify Liquibase State

Check that Liquibase recognizes all migrations as already applied:

```bash
cd parent-pom
mvn liquibase:status -pl ../service.data.impl
```

Expected output: All changesets should show as "Previously executed"

## Automation in CI/CD

The schema setup is automated in `.github/workflows/copilot-setup-steps.yml`:

```yaml
- name: Load database schema
  run: |
    sudo -u postgres psql -d cia_dev -f service.data.impl/src/main/resources/full_schema.sql
```

This ensures that:
- CI/CD environments have a consistent database state
- Tests run against the correct schema version
- Application startup tests work correctly

## Best Practices

1. **Always test the schema file** after updating it
2. **Commit the updated schema** alongside any Liquibase migration files
3. **Document schema changes** in commit messages
4. **Keep the schema in sync** with the latest migrations
5. **Use version control** to track schema evolution over time

## Troubleshooting

### Schema Load Fails

If loading the schema fails:

```bash
# Check PostgreSQL logs
sudo tail -f /var/log/postgresql/postgresql-16-main.log

# Verify PostgreSQL extensions are installed
sudo -u postgres psql -d cia_dev -c "\dx"
```

Required extensions:
- `pg_stat_statements`
- `pgaudit`
- `pgcrypto`
- `vector` (if available)

### Liquibase Shows Pending Changes

If Liquibase shows pending changes after loading the schema:

1. Ensure you exported the databasechangelog data
2. Check that all migrations are listed in the changelog
3. Verify the schema was loaded from the correct file

### Missing Data in databasechangelog

If the changelog is empty:

1. Run the application once to execute all migrations
2. Re-export the schema using the command above
3. Verify the databasechangelog table has entries before exporting

## Schema Validation and Statistics

### Overview

The `schema-validation.sql` script provides comprehensive validation of the database schema by:
- Counting all database objects (tables, views, materialized views, indexes, sequences, functions)
- Extracting sample data from key views for validation
- Generating detailed statistics about schema composition and health
- Providing baseline metrics for documentation validation

This script is essential for:
- Validating documentation accuracy (DATABASE_VIEW_INTELLIGENCE_CATALOG.md)
- Detecting schema inconsistencies
- Monitoring database health and performance
- Generating reports for auditing and analysis

### Running Schema Validation

#### Generate Text Report

To generate a comprehensive text report:

```bash
cd /path/to/cia/repository

# Generate full validation report
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-validation.sql > schema_report.txt 2>&1

# View the report
less schema_report.txt
```

The text report includes:
1. **Object Counts**: Summary of all database objects
2. **Table Inventory**: All tables with row counts, column counts, and sizes
3. **View Inventory**: All views (regular and materialized) with metadata
4. **Sample Data**: Sample rows from key views for validation
5. **Schema Statistics**: Largest tables, empty objects, view dependencies
6. **Column Statistics**: Most common columns and data types
7. **Index Statistics**: Index usage and unused indexes

#### Generate JSON Report

For programmatic processing and automation:

```bash
# Generate JSON report with structured data
psql -U postgres -d cia_dev -t -A -c "
SELECT json_build_object(
  'generated_at', NOW(),
  'database', current_database(),
  'summary', json_build_object(
    'base_tables', (SELECT COUNT(*) FROM information_schema.tables 
                    WHERE table_schema = 'public' AND table_type = 'BASE TABLE'),
    'regular_views', (SELECT COUNT(*) FROM information_schema.views 
                     WHERE table_schema = 'public'),
    'materialized_views', (SELECT COUNT(*) FROM pg_matviews 
                          WHERE schemaname = 'public'),
    'total_rows', (SELECT SUM(n_live_tup) FROM pg_stat_user_tables WHERE schemaname = 'public')
  ),
  'tables', (
    SELECT json_agg(json_build_object(
      'name', tablename,
      'rows', n_live_tup,
      'size', pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename))
    ))
    FROM pg_stat_user_tables WHERE schemaname = 'public' ORDER BY tablename
  )
)::text
" > schema_report.json

# Pretty print JSON
cat schema_report.json | python3 -m json.tool
```

#### Generate CSV Inventory

For spreadsheet analysis and gap analysis:

```bash
# Generate CSV inventory with all objects
psql -U postgres -d cia_dev -c "
COPY (
  SELECT 
    'TABLE' AS object_type, 
    tablename AS object_name, 
    n_live_tup AS row_count,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) AS total_size,
    (SELECT COUNT(*) FROM information_schema.columns 
     WHERE table_schema = schemaname AND table_name = tablename) AS column_count
  FROM pg_stat_user_tables
  WHERE schemaname = 'public'
  
  UNION ALL
  
  SELECT 
    'VIEW', viewname, NULL, NULL,
    (SELECT COUNT(*) FROM information_schema.columns 
     WHERE table_schema = schemaname AND table_name = viewname)
  FROM pg_views WHERE schemaname = 'public'
  
  UNION ALL
  
  SELECT 
    'MATERIALIZED_VIEW', matviewname, NULL,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||matviewname)),
    (SELECT COUNT(*) FROM information_schema.columns 
     WHERE table_schema = schemaname AND table_name = matviewname)
  FROM pg_matviews WHERE schemaname = 'public'
  
  ORDER BY object_type, object_name
) TO STDOUT WITH CSV HEADER
" > schema_inventory.csv

# View CSV in terminal
column -s, -t < schema_inventory.csv | less -S
```

### Validation Report Structure

The validation report provides the following sections:

#### 1. Database Object Counts
- Base tables count
- Regular views count
- Materialized views count
- Indexes count
- Sequences count
- Functions count

#### 2. Table Inventory
For each table:
- Schema and table name
- Row count
- Column count
- Total size (including indexes)

#### 3. View Inventory
For each view:
- Schema and view name
- Type (regular or materialized)
- Row count (if queryable)
- Column count
- Size (for materialized views)

#### 4. Sample Data
Sample rows (top 5-10) from key views:
- `view_riksdagen_politician`
- `view_riksdagen_party`
- `view_riksdagen_vote_data_ballot_politician_summary_daily`
- Additional views as needed

#### 5. Schema Statistics
- **Largest Tables**: Top 20 tables by row count and by size
- **Empty Objects**: Tables and views with no data
- **View Dependencies**: Views with most dependencies on other objects
- **Database Size Summary**: Total size and object counts

#### 6. Column Statistics
- Tables with most columns
- Most common column names across schema
- Most common data types

#### 7. Index Statistics
- Index usage statistics (most used, largest indexes)
- Unused indexes (candidates for removal)

### Use Cases

#### 1. Documentation Validation

Validate that DATABASE_VIEW_INTELLIGENCE_CATALOG.md matches actual schema:

```bash
# Generate current schema inventory
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-validation.sql > current_schema.txt

# Compare with documented views
grep "^View:" current_schema.txt | sort > current_views.txt
grep "^Materialized View:" current_schema.txt | sort > current_mviews.txt

# Extract view names from documentation
grep -E "^(###|##) view_" DATABASE_VIEW_INTELLIGENCE_CATALOG.md | sed 's/.*view_/view_/' | sort > documented_views.txt

# Find differences
comm -3 current_views.txt documented_views.txt
```

#### 2. Schema Health Check

Monitor schema health before releases:

```bash
# Run validation and check for issues
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-validation.sql 2>&1 | \
  grep -E "(ERROR|empty|0 rows)" > schema_issues.txt

# Review issues
cat schema_issues.txt
```

#### 3. Performance Analysis

Identify performance optimization opportunities:

```bash
# Generate report focused on performance metrics
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-validation.sql 2>&1 | \
  grep -A 30 "Largest Tables by Total Size" > largest_tables.txt

# Check for unused indexes
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-validation.sql 2>&1 | \
  grep -A 20 "Unused Indexes" > unused_indexes.txt
```

#### 4. Baseline Establishment

Create baseline metrics for tracking schema evolution:

```bash
# Generate baseline report
DATE=$(date +%Y%m%d)
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-validation.sql > baseline_$DATE.txt 2>&1

# Store in version control for comparison
git add baseline_$DATE.txt
git commit -m "Schema baseline snapshot $DATE"
```

### Integration with CI/CD

Add schema validation to CI/CD pipeline:

```yaml
# In .github/workflows/verify-and-release.yml
- name: Validate Database Schema
  run: |
    psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-validation.sql > schema_validation_report.txt 2>&1
    
    # Check for errors in validation
    if grep -q "ERROR" schema_validation_report.txt; then
      echo "Schema validation found errors"
      cat schema_validation_report.txt
      exit 1
    fi
    
    echo "Schema validation passed"

- name: Upload Schema Report
  uses: actions/upload-artifact@v5
  with:
    name: schema-validation-report
    path: schema_validation_report.txt
```

### Best Practices

1. **Regular Validation**: Run schema validation weekly or before major releases
2. **Compare Reports**: Keep historical reports to track schema evolution
3. **Review Empty Objects**: Investigate tables/views with no data
4. **Monitor Sizes**: Track growth trends in largest tables
5. **Index Optimization**: Review unused indexes quarterly for removal
6. **Document Changes**: Update documentation when schema changes are detected
7. **Automate Checks**: Integrate validation into CI/CD for continuous monitoring

### Troubleshooting

#### Validation Script Fails

If the validation script fails to run:

```bash
# Check PostgreSQL version (requires 16+)
psql -U postgres -V

# Verify database exists and is accessible
psql -U postgres -l | grep cia_dev

# Check permissions
psql -U postgres -d cia_dev -c "\du"
```

#### Views Return Errors

If specific views fail during validation:

```bash
# Test individual view
psql -U postgres -d cia_dev -c "SELECT COUNT(*) FROM view_name;"

# Check view definition
psql -U postgres -d cia_dev -c "\d+ view_name"

# Refresh materialized views if needed
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/refresh-all-views.sql
```

#### Performance Issues

If validation takes too long:

```bash
# Run validation without sample data extraction
psql -U postgres -d cia_dev -c "
  SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'public';
  SELECT COUNT(*) FROM pg_views WHERE schemaname = 'public';
  SELECT COUNT(*) FROM pg_matviews WHERE schemaname = 'public';
"

# Focus on specific sections of interest
psql -U postgres -d cia_dev -c "
  SELECT tablename, n_live_tup 
  FROM pg_stat_user_tables 
  WHERE schemaname = 'public' 
  ORDER BY n_live_tup DESC 
  LIMIT 20;
"
```

## Database Health Checks

### Overview

The `schema-health-check.sql` script provides comprehensive database health monitoring by:
- Validating all foreign key constraints for referential integrity
- Checking for orphaned records
- Validating view definitions (detecting broken views)
- Checking materialized view freshness
- Identifying missing indexes on foreign keys
- Checking for empty critical tables
- Validating data quality (NULL values in required fields)
- Analyzing query performance patterns
- Detecting table bloat
- Analyzing view dependency depth
- Generating health score (0-100)
- Providing actionable recommendations

This script complements `schema-validation.sql` (which provides statistics) by adding health checks and recommendations.

### Running Health Check

#### Generate Full Health Check Report

```bash
cd /path/to/cia/repository

# Full health check with text output
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-health-check.sql > health_check_report.txt 2>&1

# View the report
less health_check_report.txt

# Save with timestamp
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-health-check.sql > health_check_$(date +%Y%m%d_%H%M%S).txt 2>&1
```

#### Generate JSON Report for Automation

```bash
# Generate JSON report (extract JSON output from script)
psql -U postgres -d cia_dev -t -A -f service.data.impl/src/main/resources/schema-health-check.sql 2>/dev/null | \
  grep '{"timestamp"' > health_check.json

# Pretty print JSON
cat health_check.json | python3 -m json.tool

# Example JSON structure:
# {
#   "timestamp": "2025-11-18T17:30:00",
#   "database": "cia_dev",
#   "health_score": 87.5,
#   "summary": {
#     "total_checks": 45,
#     "passed": 38,
#     "warnings": 5,
#     "failures": 2,
#     "critical_issues": 1
#   },
#   "categories": [...],
#   "issues": [...]
# }
```

### Health Score Interpretation

The health score ranges from 0 to 100 based on check results:
- **PASS** checks contribute 100 points each
- **WARN** checks contribute 50 points each
- **FAIL** checks contribute 0 points each

| Score | Status | Action Required |
|-------|--------|----------------|
| 90-100 | ✓ EXCELLENT | No action needed, maintain current practices |
| 75-89 | ⚠ GOOD | Monitor warnings, plan improvements |
| 60-74 | ⚠⚠ NEEDS ATTENTION | Address failures and warnings soon |
| < 60 | ✗✗✗ CRITICAL | Immediate action required |

### Health Check Categories

#### 1. Schema Integrity
- **Foreign Key Validation**: Checks for orphaned records in child tables
- **View Integrity**: Validates all views can be queried without errors
- **Materialized View Freshness**: Checks when views were last refreshed

**Example Output:**
```
Category: Schema Integrity
Check: Foreign Key: assignment_data.person_id
Status: PASS
Details: Found 0 orphaned records
```

#### 2. Data Quality
- **Critical Table Checks**: Ensures important tables have data
- **NULL Value Checks**: Validates no NULL values in required columns
- **Duplicate Detection**: Identifies duplicate primary keys in views

**Example Output:**
```
Category: Data Quality
Check: Critical Table: person_data
Status: PASS
Details: Table has 5,432 rows

Check: NULL Check: person_data.person_id
Status: FAIL
Details: Found 12 NULL person_id values
Recommendation: Fix NULL values in person_data.person_id
```

#### 3. Performance Analysis
- **Missing Indexes**: Identifies foreign keys without indexes
- **Table Maintenance**: Checks when tables were last vacuumed
- **Slow Queries**: Reports queries with high execution time (requires pg_stat_statements)
- **Table Bloat**: Detects tables with excessive dead tuples

**Example Output:**
```
Category: Performance
Check: Missing Index: ballot_data.issue_id
Status: WARN
Details: No index found on foreign key column
Recommendation: CREATE INDEX idx_ballot_data_issue_id ON ballot_data(issue_id);

Check: Table Bloat: document_data
Status: WARN
Details: Live tuples: 50000 | Dead tuples: 12000 | Dead ratio: 24.0%
Recommendation: VACUUM FULL document_data; -- WARNING: Locks table
```

#### 4. View Dependencies
- **Dependency Depth**: Checks for overly complex view hierarchies
- **Empty Views**: Identifies views returning zero rows

**Example Output:**
```
Category: View Dependencies
Check: Maximum Dependency Depth
Status: PASS
Details: Maximum dependency depth: 3 | Views with depth > 3: 0

Check: Empty View: view_riksdagen_committee_decisions
Status: WARN
Details: View returns 0 rows
Recommendation: Check if this is expected. See TROUBLESHOOTING_EMPTY_VIEWS.md
```

### Common Issues and Solutions

#### Foreign Key Violations

**Problem:** Orphaned records found in child tables

```sql
-- Find orphaned records
SELECT c.* 
FROM assignment_data c
LEFT JOIN person_data p ON c.person_id = p.person_id
WHERE c.person_id IS NOT NULL AND p.person_id IS NULL;

-- Clean up (use with caution)
DELETE FROM assignment_data
WHERE NOT EXISTS (
    SELECT 1 FROM person_data p
    WHERE p.person_id = assignment_data.person_id
);

-- Or add missing parent records
INSERT INTO person_data (person_id, ...) VALUES (...);
```

#### Stale Materialized Views

**Problem:** Materialized views haven't been refreshed in over 7 days

```bash
# Refresh all materialized views
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/refresh-all-views.sql

# Refresh specific view
psql -U postgres -d cia_dev -c "REFRESH MATERIALIZED VIEW view_name;"
```

#### Missing Indexes on Foreign Keys

**Problem:** Foreign key columns lack indexes, causing slow joins

```sql
-- Create index as recommended
CREATE INDEX idx_ballot_data_issue_id ON ballot_data(issue_id);

-- Verify index created
\d ballot_data
```

#### Empty Critical Tables

**Problem:** Critical tables have no data

See [TROUBLESHOOTING_EMPTY_VIEWS.md](../../TROUBLESHOOTING_EMPTY_VIEWS.md) for detailed diagnostic steps:

1. Check if data import has been run
2. Verify external API connectivity (Riksdagen API)
3. Check application logs for import errors
4. Run data import jobs manually

#### Table Bloat

**Problem:** Tables have high dead tuple ratio

```sql
-- Light cleanup (doesn't lock table)
VACUUM ANALYZE table_name;

-- Heavy cleanup (locks table - use during maintenance window)
VACUUM FULL table_name;
```

> **WARNING:** Manual table rebuilding is complex and error-prone. It can easily lead to data loss, missing indexes, or broken constraints if not done with extreme care. Use `VACUUM FULL` unless you have a specific requirement that cannot be met otherwise.
>
> If you must rebuild a table manually, use a transaction and ensure all indexes, constraints, triggers, and permissions are recreated. Example:

```sql
BEGIN;
-- Create new table with structure, including indexes and constraints
CREATE TABLE table_name_new (LIKE table_name INCLUDING ALL);
-- Copy data
INSERT INTO table_name_new SELECT * FROM table_name;
-- Verify row count matches
-- DROP and RENAME within same transaction
DROP TABLE table_name;
ALTER TABLE table_name_new RENAME TO table_name;
COMMIT;
-- Recreate any dependent objects (triggers, permissions, etc.) as needed
```

### Automation

#### Daily Health Check Script

Create a cron job to run daily health checks:

```bash
#!/bin/bash
# File: /usr/local/bin/cia-daily-health-check.sh

REPORT_DIR="/var/log/cia/health-checks"
mkdir -p "$REPORT_DIR"

DATE=$(date +%Y%m%d_%H%M%S)
REPORT_FILE="$REPORT_DIR/health_check_$DATE.txt"
JSON_FILE="$REPORT_DIR/health_check_$DATE.json"

echo "Running CIA database health check at $DATE"

# Run health check
psql -U postgres -d cia_dev -f /path/to/service.data.impl/src/main/resources/schema-health-check.sql > "$REPORT_FILE" 2>&1

# Extract health score
HEALTH_SCORE=$(grep "HEALTH SCORE:" "$REPORT_FILE" | sed -n 's/.*HEALTH SCORE: \([0-9][0-9]*\).*/\1/p' | head -1)

echo "Health check completed. Score: $HEALTH_SCORE/100"

# Alert if critical
if [ "$HEALTH_SCORE" -lt 60 ]; then
    echo "CRITICAL: Database health score below 60!"
    # Send alert (email, Slack, PagerDuty, etc.)
    # mail -s "CIA Database Health CRITICAL" admin@example.com < "$REPORT_FILE"
    # curl -X POST -H 'Content-type: application/json' \
    #   --data "{\"text\":\"CIA Database Health CRITICAL: $HEALTH_SCORE/100\"}" \
    #   $SLACK_WEBHOOK_URL
fi

# Clean up old reports (keep last 30 days)
find "$REPORT_DIR" -name "health_check_*.txt" -mtime +30 -delete

echo "Health check report saved to $REPORT_FILE"
```

Make executable and add to crontab:

```bash
chmod +x /usr/local/bin/cia-daily-health-check.sh

# Run daily at 2 AM (append to existing crontab safely)
(crontab -l 2>/dev/null; echo "0 2 * * * /usr/local/bin/cia-daily-health-check.sh") | crontab -
```

#### Integration with CI/CD

Add to `.github/workflows/verify-and-release.yml`:

```yaml
- name: Database Health Check
  run: |
    psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-health-check.sql > health_check.txt 2>&1
    
    # Extract health score
    HEALTH_SCORE=$(grep "HEALTH SCORE:" health_check.txt | sed -n 's/.*HEALTH SCORE: \([0-9][0-9]*\).*/\1/p' | head -1)
    echo "Database Health Score: $HEALTH_SCORE/100"
    
    # Fail if score below threshold
    if [ "$HEALTH_SCORE" -lt 75 ]; then
      echo "Health check failed: Score below 75"
      cat health_check.txt
      exit 1
    fi
    
    echo "Health check passed"

- name: Upload Health Check Report
  if: always()
  uses: actions/upload-artifact@v5
  with:
    name: database-health-check
    path: health_check.txt
```

#### Monitoring with Prometheus/Grafana

Export metrics for monitoring systems:

```bash
#!/bin/bash
# File: export-health-metrics.sh

# Run health check and extract JSON
JSON=$(psql -U postgres -d cia_dev -t -A -f service.data.impl/src/main/resources/schema-health-check.sql | grep '{"timestamp"' | head -1)

# Extract metrics
HEALTH_SCORE=$(echo "$JSON" | jq -r '.health_score')
TOTAL_CHECKS=$(echo "$JSON" | jq -r '.summary.total_checks')
PASSED=$(echo "$JSON" | jq -r '.summary.passed')
WARNINGS=$(echo "$JSON" | jq -r '.summary.warnings')
FAILURES=$(echo "$JSON" | jq -r '.summary.failures')
CRITICAL=$(echo "$JSON" | jq -r '.summary.critical_issues')

# Write to Prometheus text file
cat <<EOF > /var/lib/prometheus/node_exporter/textfile_collector/cia_health.prom
# HELP cia_health_score Database health score (0-100)
# TYPE cia_health_score gauge
cia_health_score $HEALTH_SCORE

# HELP cia_health_checks_total Total number of health checks
# TYPE cia_health_checks_total gauge
cia_health_checks_total{status="passed"} $PASSED
cia_health_checks_total{status="warnings"} $WARNINGS
cia_health_checks_total{status="failures"} $FAILURES
cia_health_checks_total{status="critical"} $CRITICAL
EOF
```

### Best Practices

1. **Run health checks regularly**: Weekly or before major deployments
2. **Address issues promptly**: Don't let warnings accumulate
3. **Monitor trends**: Track health score over time
4. **Automate alerts**: Set up notifications for score drops
5. **Document fixes**: Keep track of recurring issues and solutions
6. **Combine with validation**: Use both health-check and validation scripts
7. **Test after fixes**: Re-run health check after addressing issues

### Troubleshooting

#### Health Check Script Fails

If the script fails to run:

```bash
# Check PostgreSQL is running
sudo systemctl status postgresql

# Verify database exists
psql -U postgres -l | grep cia_dev

# Check permissions
psql -U postgres -d cia_dev -c "\du"

# Test basic connectivity
psql -U postgres -d cia_dev -c "SELECT version();"
```

#### False Positives

Some warnings may be expected based on your environment:

- **Empty application event views**: Normal for new installations
- **Unstale materialized views**: May be intentional if data hasn't changed
- **Missing pg_stat_statements**: Optional extension for performance monitoring

Update the script to skip checks that don't apply to your environment.

#### Performance Impact

The health check script queries system catalogs and may perform COUNT(*) operations. To minimize impact:

- Run during low-traffic periods
- Use connection pooling
- Consider running on a read replica
- Adjust check frequency based on database size

## Sample Data Extraction

### Overview

The `extract-sample-data.sql` script extracts sample data from tables and views for:
- Debugging empty views
- Creating test data fixtures
- Understanding data relationships
- Documenting actual data values
- Reproducing issues in development environments

**Key Features:**
- Extracts 10 rows from each table (configurable)
- Focuses on columns used in views
- Exports to CSV format with headers
- Generates metadata manifest
- Creates column mapping for views
- Easy to version control and share

### Running Sample Data Extraction

#### Quick Start

```bash
cd /path/to/cia/repository

# Extract to current directory
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-sample-data.sql

# Or use the shell wrapper
./service.data.impl/src/main/resources/extract-sample-data.sh

# Extract to specific directory
./service.data.impl/src/main/resources/extract-sample-data.sh /tmp/sample_data

# Extract from different database
./service.data.impl/src/main/resources/extract-sample-data.sh /tmp/sample_data cia_prod
```

#### Generated Files

The script creates multiple CSV files:

**Table Samples:**
- `table_person_data_sample.csv` - Person information
- `table_assignment_data_sample.csv` - Political assignments
- `table_document_data_sample.csv` - Documents
- `table_ballot_data_sample.csv` - Voting ballots
- `table_vote_data_sample.csv` - Individual votes
- `table_sweden_political_party_sample.csv` - Party information
- Additional tables as configured

**View Samples:**
- `view_riksdagen_politician_sample.csv` - Politician aggregated data
- `view_riksdagen_party_sample.csv` - Party aggregated data
- `view_riksdagen_vote_data_ballot_summary_sample.csv` - Vote summaries
- All other views with data

**Metadata Files:**
- `sample_data_manifest.csv` - List of all extracted files with sizes
- `view_column_mapping.csv` - Shows which columns are used in which views

### Use Cases

#### 1. Debugging Empty Views

When a view returns no data, use sample data to understand dependencies:

```bash
# Extract sample data
./extract-sample-data.sh /tmp/debug

# Check which tables the view depends on
cat /tmp/debug/view_column_mapping.csv | grep "problem_view"

# Check if those tables have data
cat /tmp/debug/table_*_sample.csv

# Compare with TROUBLESHOOTING_EMPTY_VIEWS.md
```

#### 2. Creating Test Fixtures

```bash
# Extract sample data from production
./extract-sample-data.sh /tmp/prod_sample cia_prod

# Load into test database
cd /tmp/prod_sample
for file in table_*_sample.csv; do
    table_name=$(echo $file | sed 's/table_//' | sed 's/_sample.csv//')
    psql -U postgres -d cia_test -c "\copy $table_name FROM '$file' WITH CSV HEADER;"
done
```

#### 3. Understanding Data Relationships

```bash
# Extract sample data
./extract-sample-data.sh /tmp/analysis

# Analyze relationships
# Open CSV files in spreadsheet to see actual data values
# Compare person_id in person_data with assignment_data
# Verify foreign key relationships with actual data
```

#### 4. Documentation with Real Examples

```bash
# Extract current sample data
./extract-sample-data.sh docs/sample_data

# Commit to repository for documentation
git add docs/sample_data/*.csv
git commit -m "docs: Update sample data for view documentation"

# Reference in documentation
# "Example person_id: 0000000000 (from table_person_data_sample.csv)"
```

### Customizing Sample Size

Edit `extract-sample-data.sql` to change the sample size:

```sql
-- At the top of the script
\set SAMPLE_SIZE 10  -- Change to desired number of rows

-- Or modify individual LIMIT clauses
\copy (SELECT * FROM person_data LIMIT 20) TO 'table_person_data_sample.csv' WITH CSV HEADER;
```

### Reloading Sample Data

To import sample data back into a database:

```bash
# Load specific table
psql -U postgres -d cia_test -c "\copy person_data FROM 'table_person_data_sample.csv' WITH CSV HEADER;"

# Load all tables (bash script)
for file in table_*_sample.csv; do
    table_name=$(echo $file | sed 's/table_//' | sed 's/_sample.csv//')
    echo "Loading $table_name..."
    psql -U postgres -d cia_test -c "\copy $table_name FROM '$file' WITH CSV HEADER;"
done

# Refresh materialized views after loading
psql -U postgres -d cia_test -f service.data.impl/src/main/resources/refresh-all-views.sql
```

### Version Control Considerations

**Recommended Approach:**

```bash
# Create sample_data directory
mkdir -p service.data.impl/src/main/resources/sample_data

# Extract to this directory
./extract-sample-data.sh service.data.impl/src/main/resources/sample_data

# Add to version control (selective)
git add service.data.impl/src/main/resources/sample_data/*.csv

# Or add to .gitignore if data is sensitive
echo "service.data.impl/src/main/resources/sample_data/*.csv" >> .gitignore
```

**Data Sensitivity:**
- Review CSV files before committing to ensure no sensitive data
- Consider anonymizing data: `person_id`, names, etc.
- Use `.gitignore` for sensitive production data
- Commit anonymized samples for development/testing

### Automation

#### Daily Sample Data Backup

```bash
#!/bin/bash
# daily-sample-extract.sh

BACKUP_DIR="/var/backups/cia/sample_data"
DATE=$(date +%Y%m%d)
OUTPUT_DIR="$BACKUP_DIR/$DATE"

mkdir -p "$OUTPUT_DIR"

# Extract sample data
/path/to/extract-sample-data.sh "$OUTPUT_DIR"

# Compress
cd "$BACKUP_DIR"
tar -czf "sample_data_$DATE.tar.gz" "$DATE"
rm -rf "$DATE"

# Keep last 30 days
find "$BACKUP_DIR" -name "sample_data_*.tar.gz" -mtime +30 -delete

echo "Sample data backup completed: sample_data_$DATE.tar.gz"
```

#### CI/CD Integration

```yaml
# In .github/workflows/verify-and-release.yml
- name: Extract Sample Data for Testing
  run: |
    ./service.data.impl/src/main/resources/extract-sample-data.sh /tmp/test_data
    
    # Verify key files were created
    test -f /tmp/test_data/table_person_data_sample.csv
    test -f /tmp/test_data/view_riksdagen_politician_sample.csv
    
    echo "Sample data extracted successfully"

- name: Upload Sample Data Artifact
  uses: actions/upload-artifact@v5
  with:
    name: sample-data
    path: /tmp/test_data/*.csv
```

### Troubleshooting

#### Empty Output Files

If CSV files are empty or missing:

```bash
# Check if tables have data
psql -U postgres -d cia_dev -c "SELECT COUNT(*) FROM person_data;"

# Check view data
psql -U postgres -d cia_dev -c "SELECT COUNT(*) FROM view_riksdagen_politician;"

# Run with verbose output
psql -U postgres -d cia_dev -a -f extract-sample-data.sql

# See TROUBLESHOOTING_EMPTY_VIEWS.md for view-specific issues
```

#### Permission Errors

```bash
# Ensure output directory is writable
chmod 755 /output/directory

# Ensure PostgreSQL user has read access
psql -U postgres -d cia_dev -c "\du"

# Check table permissions
psql -U postgres -d cia_dev -c "\dp person_data"
```

#### Large CSV Files

If sample files are too large:

```bash
# Reduce LIMIT in SQL script
sed -i 's/LIMIT 10/LIMIT 5/g' extract-sample-data.sql

# Or extract specific tables only
psql -U postgres -d cia_dev -c "\copy (SELECT * FROM person_data LIMIT 5) TO 'person_sample.csv' WITH CSV HEADER;"
```

### Best Practices

1. **Regular Extraction**: Extract sample data weekly for up-to-date examples
2. **Anonymize Production Data**: Remove or hash sensitive information before committing
3. **Document Data Values**: Use actual values in documentation (from samples)
4. **Test with Samples**: Use sample data for unit tests and integration tests
5. **Version Control**: Commit representative samples for development team
6. **Combine with Health Check**: Run extraction after health check passes
7. **Automate Updates**: Schedule regular extraction in CI/CD pipeline

### Integration with Other Tools

**With Health Check:**
```bash
# Run health check first
psql -U postgres -d cia_dev -f schema-health-check.sql > health_check.txt

# If health score is acceptable, extract samples
HEALTH_SCORE=$(grep "HEALTH SCORE:" health_check.txt | sed -n 's/.*HEALTH SCORE: \([0-9][0-9]*\).*/\1/p' | head -1)

if [ "$HEALTH_SCORE" -ge 60 ]; then
    ./extract-sample-data.sh /tmp/samples
    echo "Sample data extracted successfully"
else
    echo "Health check failed. Fix issues before extracting sample data."
fi
```

**With Schema Validation:**
```bash
# Validate schema first
psql -U postgres -d cia_dev -f schema-validation.sql

# Then extract samples
./extract-sample-data.sh /tmp/samples

# Compare counts
echo "Validation shows X tables, extraction created Y CSV files"
```

## Related Files

- `service.data.impl/src/main/resources/full_schema.sql` - The complete schema file
- `service.data.impl/src/main/resources/schema-validation.sql` - Schema validation and statistics script
- `service.data.impl/src/main/resources/schema-health-check.sql` - Database health check script
- `service.data.impl/src/main/resources/extract-sample-data.sql` - Sample data extraction script
- `service.data.impl/src/main/resources/extract-sample-data.sh` - Shell wrapper for data extraction
- `service.data.impl/src/main/resources/refresh-all-views.sql` - Materialized view refresh script
- `service.data.impl/src/main/resources/db-changelog*.xml` - Liquibase migration files
- `.github/workflows/copilot-setup-steps.yml` - CI/CD database setup
- `DATABASE_VIEW_INTELLIGENCE_CATALOG.md` - View documentation catalog
- `TROUBLESHOOTING_EMPTY_VIEWS.md` - Troubleshooting guide for empty views
- `README.md` - Main project documentation with PostgreSQL setup instructions

## Additional Resources

- [PostgreSQL pg_dump Documentation](https://www.postgresql.org/docs/16/app-pgdump.html)
- [PostgreSQL Information Schema](https://www.postgresql.org/docs/16/information-schema.html)
- [PostgreSQL System Catalogs](https://www.postgresql.org/docs/16/catalogs.html)
- [Liquibase Documentation](https://docs.liquibase.com/)
- [CIA Architecture Documentation](../ARCHITECTURE.md)
- [CIA Data Model Documentation](../DATA_MODEL.md)
