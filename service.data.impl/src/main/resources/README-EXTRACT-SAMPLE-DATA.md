# Extract Sample Data Scripts

Simple guide for extracting sample data from the CIA database with percentile-based sampling, automated validation, and robust timeout handling.

## Quick Start

### Basic Usage

```bash
# Run the main extraction script
cd service.data.impl/src/main/resources
./extract-sample-data.sh
```

The script will:
1. Connect to PostgreSQL database (localhost by default)
2. Extract sample data from all tables and views (that contain data)
3. Generate percentile distribution summaries (P1-P99)
4. Validate coverage (temporal, categorical, percentile)
5. Create CSV files with sample data

### Configuration Options

Set environment variables to customize extraction:

```bash
# Database connection
export PSQL_HOST="localhost"      # Database host (default: localhost)
export PSQL_PORT="5432"           # Database port (default: 5432)
export PSQL_USER="postgres"       # Database user (default: postgres)
export PGPASSWORD="your_password" # Database password (required for non-local connections)

# Timeout configuration
export EXTRACTION_TIMEOUT="3600"  # Total extraction timeout in seconds (default: 1800 = 30 minutes)

# Run extraction
./extract-sample-data.sh /output/directory cia_dev
```

### Database Setup

For standard local PostgreSQL:

```bash
# No configuration needed - defaults work
./extract-sample-data.sh
```

For remote or custom PostgreSQL setup:

```bash
export PSQL_HOST="db.example.com"
export PSQL_PORT="5432"
export PSQL_USER="cia_user"
export PGPASSWORD="your_secure_password"
./extract-sample-data.sh /tmp/samples cia_prod
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

### Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `PSQL_HOST` | `localhost` | PostgreSQL server hostname or IP |
| `PSQL_PORT` | `5432` | PostgreSQL server port |
| `PSQL_USER` | `postgres` | PostgreSQL username |
| `PGPASSWORD` | (none) | PostgreSQL password (required for authentication) |
| `EXTRACTION_TIMEOUT` | `1800` | Maximum extraction time in seconds (30 minutes) |

### Sample Sizes

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

### Timeout Behavior

The script implements **two-level timeout protection**:

1. **Shell-level timeout** (via `timeout` command): Total extraction time limit
   - Default: 1800 seconds (30 minutes)
   - Configurable: `EXTRACTION_TIMEOUT=3600 ./extract-sample-data.sh`
   - Sends TERM signal, then KILL after 30s grace period
   - Exit codes: 124 (timeout), 137 (killed)

2. **SQL-level timeout** (via PostgreSQL settings): Per-query limits
   - `statement_timeout`: 120s per query (prevents individual query hangs)
   - `lock_timeout`: 30s wait for locks (prevents deadlocks)
   - `idle_in_transaction_session_timeout`: 180s (kills idle transactions)

**Graceful Interruption:**
- Press Ctrl+C to stop extraction cleanly
- Partial results are preserved
- Check `extract-sample-data.log` for details

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

### Database Connection Issues

**Error: "connection to server on socket failed: FATAL: Peer authentication failed"**
- **Cause**: PostgreSQL peer authentication requires matching OS user
- **Solution 1**: Use TCP/IP connection with password
  ```bash
  export PSQL_HOST=localhost
  export PGPASSWORD=your_password
  ./extract-sample-data.sh
  ```
- **Solution 2**: Run as postgres user
  ```bash
  sudo -u postgres ./extract-sample-data.sh
  ```

**Error: "connection refused"**
- **Cause**: PostgreSQL not running or wrong host/port
- **Check**: `sudo systemctl status postgresql`
- **Check**: `netstat -tlnp | grep 5432`
- **Solution**: Start PostgreSQL or verify correct host/port

### Timeout Issues

**Extraction times out after 30 minutes**
- **Cause**: Database has very large tables/views or slow queries
- **Solution 1**: Increase timeout
  ```bash
  EXTRACTION_TIMEOUT=7200 ./extract-sample-data.sh  # 2 hours
  ```
- **Solution 2**: Optimize database
  ```sql
  VACUUM ANALYZE;  -- Update statistics
  REINDEX DATABASE cia_dev;  -- Rebuild indexes
  ```
- **Solution 3**: Review slow queries in log
  ```bash
  grep "statement timeout" extract-sample-data.log
  ```

**Script hangs indefinitely (no timeout)**
- **Cause**: `timeout` command not available on system
- **Solution**: Install coreutils
  ```bash
  # Debian/Ubuntu
  sudo apt-get install coreutils
  
  # macOS
  brew install coreutils
  ```

### Data Issues

**Missing data in samples**
- Normal if database is empty (test environment)
- Functions work correctly, will populate with real data
- Script only extracts tables/views that contain data
- Check extraction_statistics.csv for details:
  - "Tables with data: 8" means only 8 tables have rows
  - "Empty tables: 86" means 86 tables are empty (skipped)

**Validation warnings**
- Expected with empty database
- Warnings indicate what data is missing
- Review `validation_coverage_report.csv` for details
- Will disappear when database is populated

**All tables/views showing as empty**
- **Cause**: Wrong database selected
- **Check**: `psql -h localhost -U eris -d cia_dev -c "SELECT COUNT(*) FROM pg_tables WHERE schemaname='public';"`
- **Solution**: Verify database name and schema are loaded

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
