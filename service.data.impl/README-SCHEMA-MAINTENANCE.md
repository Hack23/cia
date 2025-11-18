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
  uses: actions/upload-artifact@v4
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

## Related Files

- `service.data.impl/src/main/resources/full_schema.sql` - The complete schema file
- `service.data.impl/src/main/resources/schema-validation.sql` - Schema validation and statistics script
- `service.data.impl/src/main/resources/refresh-all-views.sql` - Materialized view refresh script
- `service.data.impl/src/main/resources/db-changelog*.xml` - Liquibase migration files
- `.github/workflows/copilot-setup-steps.yml` - CI/CD database setup
- `DATABASE_VIEW_INTELLIGENCE_CATALOG.md` - View documentation catalog
- `README.md` - Main project documentation with PostgreSQL setup instructions

## Additional Resources

- [PostgreSQL pg_dump Documentation](https://www.postgresql.org/docs/16/app-pgdump.html)
- [PostgreSQL Information Schema](https://www.postgresql.org/docs/16/information-schema.html)
- [PostgreSQL System Catalogs](https://www.postgresql.org/docs/16/catalogs.html)
- [Liquibase Documentation](https://docs.liquibase.com/)
- [CIA Architecture Documentation](../ARCHITECTURE.md)
- [CIA Data Model Documentation](../DATA_MODEL.md)
