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

## Related Files

- `service.data.impl/src/main/resources/full_schema.sql` - The complete schema file
- `service.data.impl/src/main/resources/db-changelog*.xml` - Liquibase migration files
- `.github/workflows/copilot-setup-steps.yml` - CI/CD database setup
- `README.md` - Main project documentation with PostgreSQL setup instructions

## Additional Resources

- [PostgreSQL pg_dump Documentation](https://www.postgresql.org/docs/16/app-pgdump.html)
- [Liquibase Documentation](https://docs.liquibase.com/)
- [CIA Architecture Documentation](../../ARCHITECTURE.md)
