# Sample Data Directory

This directory contains sample CSV data extracted from the CIA database for:
- Debugging empty views
- Creating test fixtures
- Understanding data relationships
- Documentation with real examples

## Extracting Sample Data

### Using the SQL Script

```bash
# Create output directory
mkdir -p service.data.impl/src/main/resources/sample_data
cd service.data.impl/src/main/resources/sample_data

# Run extraction
psql -U postgres -d cia_dev -f ../extract-sample-data.sql
```

### Using the Shell Wrapper

```bash
# Extract to this directory
./service.data.impl/src/main/resources/extract-sample-data.sh service.data.impl/src/main/resources/sample_data
```

## Files Generated

- `table_*_sample.csv` - Sample data from tables (50 rows each)
- `view_*_sample.csv` - Sample data from views (50 rows each)
- `sample_data_manifest.csv` - Metadata about all extracted files
- `view_column_mapping.csv` - Shows which columns are used in which views

The extraction script now automatically extracts from ALL tables and views in the database, providing diverse sampling for comprehensive coverage.

## Version Control

**IMPORTANT:** Review files before committing to ensure no sensitive data is included.

### Recommended Approach

```bash
# Extract sample data
./extract-sample-data.sh service.data.impl/src/main/resources/sample_data

# Review files for sensitive information
cat service.data.impl/src/main/resources/sample_data/*.csv

# Anonymize if needed
# (Replace sensitive values with placeholders)

# Add to version control
git add service.data.impl/src/main/resources/sample_data/*.csv
git commit -m "docs: Update sample data for development"
```

### Gitignore Option

If data should not be committed:

```bash
# Add to .gitignore
echo "service.data.impl/src/main/resources/sample_data/*.csv" >> .gitignore
```

## Usage

### For View Debugging

When debugging empty views:

1. Check `view_column_mapping.csv` to see which columns the view needs
2. Check corresponding `table_*_sample.csv` files to verify data exists
3. Compare with TROUBLESHOOTING_EMPTY_VIEWS.md

### For Test Data

Load sample data into test database:

```bash
cd service.data.impl/src/main/resources/sample_data

for file in table_*_sample.csv; do
    table_name=$(echo $file | sed 's/table_//' | sed 's/_sample.csv//')
    echo "Loading $table_name..."
    psql -U postgres -d cia_test -c "\copy $table_name FROM '$file' WITH CSV HEADER;"
done

# Refresh materialized views
psql -U postgres -d cia_test -f ../refresh-all-views.sql
```

## Documentation

See `README-SCHEMA-MAINTENANCE.md` for complete documentation on:
- Sample data extraction
- Customization options
- Automation approaches
- Best practices
