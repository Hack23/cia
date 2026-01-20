# Extract Sample Data Scripts

Simple guide for extracting sample data from the CIA database with percentile-based sampling and automated validation.

## Quick Start

### Basic Usage

```bash
# Run the main extraction script
cd service.data.impl/src/main/resources
./extract-sample-data.sh
```

The script will:
1. Connect to PostgreSQL database
2. Extract sample data from all tables and views
3. Generate percentile distribution summaries (P1-P99)
4. Validate coverage (temporal, categorical, percentile)
5. Create CSV files with sample data

### Database Setup

Configure database connection in `sample-data-config.sh` or set environment variables:

```bash
export DB_NAME="cia_dev"
export DB_HOST="localhost"
export DB_PORT="5432"
export DB_USER="cia_user"
```

## Available Scripts

### Main Scripts

- **extract-sample-data.sql** - PostgreSQL script with sampling logic
- **extract-sample-data.sh** - Shell wrapper with validation

### Modular Components (Optional)

- **sample-data-config.sh** - Configuration settings (sample sizes, parties, years)
- **extract-sample-data-functions.sql** - 4 reusable SQL functions
- **extract-percentile-summaries.sql** - Standalone percentile generation
- **validation-functions.sh** - 5 reusable validation functions
- **test-extract-functions.sh** - Test script for validation

## Configuration

Edit `sample-data-config.sh` to customize:

```bash
# Sample sizes
DEFAULT_SAMPLE_SIZE=200        # Default rows per view
TREND_SAMPLE_SIZE=500         # Rows for trend analysis views

# Temporal coverage
MIN_YEAR=2002
MAX_YEAR=2026
MIN_YEAR_COVERAGE=10          # Minimum years required

# Political parties
EXPECTED_PARTIES="S M SD C V KD L MP"  # 8 Swedish parties

# Percentile targets
PERCENTILE_TARGETS="p1 p10 p25 p50 p75 p90 p99"
```

## SQL Functions

Load reusable functions in PostgreSQL:

```sql
-- Load helper functions
\i extract-sample-data-functions.sql

-- Use functions
SELECT * FROM cia_tmp_rowcount('view_name');
SELECT * FROM cia_classify_temporal_view('view_name', 'date_column');
SELECT * FROM cia_percentile_sample('view_name', 'numeric_column', 500);
SELECT * FROM cia_generate_distribution_summary('view_name');
```

## Output Files

After execution, you'll have:

- **{table}_sample.csv** - Sample data for each table/view (200-500 rows)
- **percentile_{view}.csv** - Distribution summaries for 24 analytical views
- **validation_coverage_report.csv** - Coverage validation results
- **extraction_statistics.csv** - Extraction statistics and metadata

## Validation

The script automatically validates:

✓ **Temporal Coverage** - All years 2002-2026 represented  
✓ **Party Coverage** - All 8 Swedish parties present  
✓ **Percentile Coverage** - P1-P99 columns in distribution files

Check `validation_coverage_report.csv` for results.

## Testing

Test all components against the database:

```bash
# Test SQL functions
psql -d cia_dev -f extract-sample-data-functions.sql
./test-extract-functions.sh

# Test configuration
source sample-data-config.sh
echo "Sample size: $DEFAULT_SAMPLE_SIZE"

# Test validation functions
source validation-functions.sh
validate_temporal_coverage
validate_party_coverage
validate_percentile_coverage
```

## Troubleshooting

**Database connection fails:**
- Check PostgreSQL is running
- Verify database credentials in config
- Ensure database exists and schema is loaded

**Missing data in samples:**
- Normal if database is empty (test environment)
- Functions work correctly, will populate with real data

**Validation warnings:**
- Expected with empty database
- Warnings indicate what data is missing
- Review `validation_coverage_report.csv` for details

## Architecture

The scripts use a modular architecture:

```
extract-sample-data.sh (main)
├── sample-data-config.sh (optional config)
├── validation-functions.sh (optional validation)
└── extract-sample-data.sql
    ├── extract-sample-data-functions.sql (optional functions)
    └── extract-percentile-summaries.sql (optional Phase 6.5)
```

Main scripts work standalone. Modular components are optional enhancements that are automatically sourced if available.

## Sample Sizes

- **Default views**: 200 rows (uniform random sampling)
- **Trend analysis views**: 500 rows (election proximity, seasonal activity, career trajectory)
- **Percentile distributions**: P1, P10, P25, P50, P75, P90, P99 for 24 analytical views

## Support

For issues or questions:
- Check validation report for specific errors
- Review test results: `./test-extract-functions.sh`
- Examine database logs for connection issues
- Verify schema is fully loaded: 94 tables, 79 views expected

---

**Version**: 1.0  
**Database**: PostgreSQL 13+  
**Last Updated**: January 2026
