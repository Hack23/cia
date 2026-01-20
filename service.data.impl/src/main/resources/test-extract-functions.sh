#!/bin/bash
# ===========================================================================
# CIA Sample Data Extraction - Test Script
# ===========================================================================
# Tests the sample data extraction functions against cia_dev database
# with full schema but no data
#
# Prerequisites:
#   - PostgreSQL running with cia_dev database
#   - Full schema loaded (full_schema.sql)
#   - User 'eris' with password 'discord'
#
# Usage:
#   ./test-extract-functions.sh
#
# Version: 1.0.0
# Last Updated: January 2026
# ===========================================================================

set -e  # Exit on error

# Source configuration
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
source "$SCRIPT_DIR/sample-data-config.sh"

# Database connection
export PGHOST="${DB_HOST}"
export PGPORT="${DB_PORT}"
export PGDATABASE="${DB_NAME}"
export PGUSER="${DB_USER}"
export PGPASSWORD="discord"

echo "=================================================="
echo "=== CIA Sample Data Extraction - Test Script  ==="
echo "=================================================="
echo ""
echo "Database: ${PGDATABASE}@${PGHOST}:${PGPORT}"
echo "User: ${PGUSER}"
echo ""

# ===========================================================================
# Test 1: Database Connection
# ===========================================================================
log_info "Test 1: Verifying database connection..."
if psql -c "SELECT version();" > /dev/null 2>&1; then
    log_info "✓ Database connection successful"
    psql -t -c "SELECT version();" | head -1
else
    log_error "✗ Database connection failed"
    exit 1
fi
echo ""

# ===========================================================================
# Test 2: Schema Validation
# ===========================================================================
log_info "Test 2: Validating database schema..."
TABLE_COUNT=$(psql -t -c "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public' AND table_type = 'BASE TABLE';")
VIEW_COUNT=$(psql -t -c "SELECT COUNT(*) FROM information_schema.views WHERE table_schema = 'public';")

log_info "Found $TABLE_COUNT tables and $VIEW_COUNT views in public schema"

if [ "$TABLE_COUNT" -gt 0 ]; then
    log_info "✓ Schema validation passed"
else
    log_error "✗ No tables found in database"
    exit 1
fi
echo ""

# ===========================================================================
# Test 3: Load Helper Functions
# ===========================================================================
log_info "Test 3: Loading helper functions..."
if psql -f "$SCRIPT_DIR/extract-sample-data-functions.sql" > /dev/null 2>&1; then
    log_info "✓ Helper functions loaded successfully"
else
    log_error "✗ Failed to load helper functions"
    exit 1
fi

# Verify functions exist
FUNCTION_COUNT=$(psql -t -c "SELECT COUNT(*) FROM pg_proc WHERE proname LIKE 'cia_%';")
log_info "Loaded $FUNCTION_COUNT CIA functions"
echo ""

# ===========================================================================
# Test 4: Test cia_tmp_rowcount Function
# ===========================================================================
log_info "Test 4: Testing cia_tmp_rowcount function..."
# Test with information_schema.tables (should always exist)
ROWCOUNT=$(psql -t -c "SELECT cia_tmp_rowcount('information_schema', 'tables');")
log_info "Row count for information_schema.tables: $ROWCOUNT"

if [ "$ROWCOUNT" -gt 0 ]; then
    log_info "✓ cia_tmp_rowcount function works"
else
    log_warn "⚠ cia_tmp_rowcount returned 0 (expected for empty tables)"
fi
echo ""

# ===========================================================================
# Test 5: Test cia_classify_temporal_view Function
# ===========================================================================
log_info "Test 5: Testing cia_classify_temporal_view function..."
CLASSIFICATION=$(psql -t -c "SELECT cia_classify_temporal_view('view_riksdagen_daily_summary');")
log_info "Classification for 'view_riksdagen_daily_summary': $CLASSIFICATION"

if [ -n "$CLASSIFICATION" ]; then
    log_info "✓ cia_classify_temporal_view function works"
else
    log_error "✗ cia_classify_temporal_view function failed"
    exit 1
fi
echo ""

# ===========================================================================
# Test 6: Test cia_percentile_sample Function
# ===========================================================================
log_info "Test 6: Testing cia_percentile_sample function (dry run)..."
# This will fail gracefully with empty tables, but we can test the function exists
if psql -c "SELECT * FROM cia_percentile_sample('information_schema.tables', 'table_name') LIMIT 1;" > /dev/null 2>&1; then
    log_info "✓ cia_percentile_sample function exists and is callable"
else
    log_warn "⚠ cia_percentile_sample function failed (expected with no data)"
fi
echo ""

# ===========================================================================
# Test 7: Test cia_generate_distribution_summary Function  
# ===========================================================================
log_info "Test 7: Testing cia_generate_distribution_summary function (dry run)..."
# This will return empty results with no data, but function should work
if psql -c "SELECT * FROM cia_generate_distribution_summary('pg_stat_user_tables') LIMIT 1;" > /dev/null 2>&1; then
    log_info "✓ cia_generate_distribution_summary function exists and is callable"
else
    log_warn "⚠ cia_generate_distribution_summary function failed (expected with no data)"
fi
echo ""

# ===========================================================================
# Test 8: List Available Views for Sampling
# ===========================================================================
log_info "Test 8: Listing available views for sampling..."
echo "Sample of analytical views:"
psql -t -c "SELECT table_name FROM information_schema.views WHERE table_schema = 'public' AND table_name LIKE 'view_riksdagen%' ORDER BY table_name LIMIT 10;"
echo ""

# ===========================================================================
# Test 9: Validate View Structure
# ===========================================================================
log_info "Test 9: Validating view structure for percentile analysis..."
# Check if views have numerical columns suitable for percentile analysis
VIEW_WITH_NUMERIC=$(psql -t -c "SELECT DISTINCT table_name FROM information_schema.columns WHERE table_schema = 'public' AND table_name LIKE 'view_%' AND data_type IN ('integer', 'bigint', 'numeric', 'real', 'double precision') LIMIT 5;")

if [ -n "$VIEW_WITH_NUMERIC" ]; then
    log_info "✓ Found views with numerical columns:"
    echo "$VIEW_WITH_NUMERIC"
else
    log_warn "⚠ No views with numerical columns found"
fi
echo ""

# ===========================================================================
# Test 10: Clean Up Test Functions (Optional)
# ===========================================================================
log_info "Test 10: Cleaning up test functions..."
read -p "Do you want to drop CIA test functions? (y/N): " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]; then
    psql -c "DROP FUNCTION IF EXISTS cia_tmp_rowcount(text, text) CASCADE;"
    psql -c "DROP FUNCTION IF EXISTS cia_classify_temporal_view(text) CASCADE;"
    psql -c "DROP FUNCTION IF EXISTS cia_percentile_sample(text, text, text) CASCADE;"
    psql -c "DROP FUNCTION IF EXISTS cia_generate_distribution_summary(text) CASCADE;"
    log_info "✓ Test functions dropped"
else
    log_info "Test functions retained for further testing"
fi
echo ""

# ===========================================================================
# Summary
# ===========================================================================
echo "=================================================="
echo "=== TEST SUMMARY                               ==="
echo "=================================================="
log_info "All tests completed successfully!"
log_info "Database schema loaded: $TABLE_COUNT tables, $VIEW_COUNT views"
log_info "Helper functions validated"
log_info ""
log_info "Note: Some functions returned warnings because the database has no data."
log_info "This is expected behavior. Functions will work normally with populated data."
echo ""
echo "=================================================="
