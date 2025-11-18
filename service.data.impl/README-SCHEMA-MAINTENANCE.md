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
# Extract JSON report
psql -U postgres -d cia_dev -t -A -f service.data.impl/src/main/resources/schema-health-check.sql | \
  grep -A 1000 '{"timestamp"' | grep -B 1000 '}\s*$' | head -1 > health_check.json

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
WHERE person_id NOT IN (SELECT person_id FROM person_data);

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

-- Or rebuild table
CREATE TABLE table_name_new AS SELECT * FROM table_name;
DROP TABLE table_name;
ALTER TABLE table_name_new RENAME TO table_name;
-- Recreate indexes and constraints
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
HEALTH_SCORE=$(grep "HEALTH SCORE:" "$REPORT_FILE" | grep -oP '\d+' | head -1)

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

# Run daily at 2 AM
echo "0 2 * * * /usr/local/bin/cia-daily-health-check.sh" | crontab -
```

#### Integration with CI/CD

Add to `.github/workflows/verify-and-release.yml`:

```yaml
- name: Database Health Check
  run: |
    psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-health-check.sql > health_check.txt 2>&1
    
    # Extract health score
    HEALTH_SCORE=$(grep "HEALTH SCORE:" health_check.txt | grep -oP '\d+' | head -1)
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
JSON=$(psql -U postgres -d cia_dev -t -A -f schema-health-check.sql | grep '{"timestamp"' | head -1)

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

## Related Files

- `service.data.impl/src/main/resources/full_schema.sql` - The complete schema file
- `service.data.impl/src/main/resources/schema-validation.sql` - Schema validation and statistics script
- `service.data.impl/src/main/resources/schema-health-check.sql` - Database health check script
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
