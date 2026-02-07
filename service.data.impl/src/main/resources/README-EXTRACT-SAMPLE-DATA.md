# Extract Sample Data Scripts

Simple guide for extracting sample data from the CIA database with percentile-based sampling, automated validation, and robust timeout handling.

## ⚠️ IMPORTANT: Do Not Commit Generated CSV Files

**Generated CSV files from extract-sample-data.sql should NOT be committed to the repository.**

- ❌ Do not commit: `service.data.impl/src/main/resources/*.csv`
- ❌ Do not commit: `service.data.impl/src/main/resources/distinct_values/*.csv`
- ✅ Keep: `service.data.impl/sample-data/**/*.csv` (real production samples)

The extraction scripts generate CSV files for:
- Testing SQL script execution
- Validating schema changes
- Debugging data extraction logic

These are **temporary test artifacts** and should be removed after validation. Only the `sample-data/` directory contains curated sample data from a real production database for reference purposes.

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

**Graceful Timeout Handling (Phase 1):**
- Individual view timeouts do NOT abort the entire script
- When a view times out during row counting:
  - Script logs: "⏱️  TIMEOUT after 120s - skipping view and continuing with next"
  - View is marked with row_count=-1 (timeout status)
  - Timeout is tracked in `cia_extraction_tracking` table
  - Script continues processing all remaining views
  - Phase 2 and Phase 3 automatically skip timed-out views
- Complex analytical views may timeout during query planning even with 0 rows
- This behavior ensures maximum data extraction even with problematic views
- Example: If view 24/109 times out, views 25-109 still get processed

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

**Script hangs with no output after "Starting extraction..."**
- **Cause**: `PGPASSWORD` environment variable not set, `psql` waiting for password input
- **Symptoms**: 
  - Script shows "Starting extraction..." and stops
  - No error messages
  - Process must be manually killed (Ctrl+C)
- **Solution**: Set PGPASSWORD before running
  ```bash
  export PGPASSWORD=your_password
  ./extract-sample-data.sh
  ```
- **Alternative**: Configure password-less authentication
  ```bash
  # Add to ~/.pgpass
  echo "localhost:5432:cia_dev:postgres:your_password" >> ~/.pgpass
  chmod 600 ~/.pgpass
  ```

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

**Phase 1 times out at specific view (e.g., "→ [24/109] Analyzing: view_election_cycle_network_analysis")**
- **Previous behavior (FIXED)**: Script would abort completely when a view timed out, preventing all remaining views from being processed
- **Current behavior**: Script now gracefully handles timeouts by:
  - Catching timeout exceptions (SQLSTATE 57014: query_canceled)
  - Logging timeout with ⏱️  TIMEOUT warning message
  - Recording view as "timeout" status with row_count=-1 in tracking table
  - **Continuing with next view** instead of aborting
  - Skipping timed-out views in Phase 2 and Phase 3 extraction
- **Symptoms** (still occur but no longer block progress):
  - Warning: "⏱️  TIMEOUT after 120s - skipping view and continuing with next"
  - Timed-out view excluded from Phase 2/3 extraction
  - All remaining views continue to be processed normally
- **Why timeouts occur**: Complex views may timeout during query plan optimization even with 0 rows due to:
  - Multiple CTEs, window functions, and complex JOINs
  - Query planner building execution plan before knowing result set size
  - Statistics calculation overhead for analytical views
- **Solution 1** (Recommended): Accept graceful timeout handling - script continues processing
  - No action needed - timed-out views are safely skipped
  - Check `cia_extraction_tracking` table for timeout summary
- **Solution 2**: Increase statement timeout if specific view data is critical
  - Edit `extract-sample-data.sql` line ~134
  - Change `SET statement_timeout = '120s';` to `SET statement_timeout = '300s';`
  - Caution: May increase total extraction time significantly
- **Solution 3**: Optimize the slow view query
  ```sql
  -- Analyze the problematic view's query plan
  EXPLAIN ANALYZE SELECT COUNT(*) FROM view_election_cycle_network_analysis;
  
  -- Check for missing indexes
  -- Review view definition for optimization opportunities
  ```
- **Solution 4**: Skip problematic views from extraction entirely (legacy approach)
  - Add view to exclusion list in script (around line 1224)
  ```sql
  AND viewname != 'view_riksdagen_intelligence_dashboard'
  AND viewname != 'view_election_cycle_network_analysis'  -- Add this line
  ```
  - Note: Solution 1 (graceful timeout) is now preferred over exclusion

**Phase 2 shows "syntax error at or near viewname LINE 301"**
- **Cause**: Phase 1 didn't complete, so Phase 2 has incomplete row count data
- **Symptom**: Error when trying to execute generated extraction commands
- **Root Cause**: Statement timeout in Phase 1 (see above)
- **Solution**: Fix Phase 1 timeout issue first (see solutions above)

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
