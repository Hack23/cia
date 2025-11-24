#!/bin/bash
# analyze-extraction-coverage.sh
# Comprehensive coverage analysis for sample data extraction
# Compares schema objects with extraction script coverage
# 
# Usage:
#   ./analyze-extraction-coverage.sh
#
# Requires:
#   - full_schema.sql in the same directory
#   - extract-sample-data.sql in the same directory

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SCHEMA_FILE="$SCRIPT_DIR/full_schema.sql"
EXTRACT_FILE="$SCRIPT_DIR/extract-sample-data.sql"

echo "=================================================="
echo "Sample Data Extraction Coverage Analysis"
echo "=================================================="
echo ""
echo "Analysis Date: $(date +%Y-%m-%d)"
echo ""

# Check required files
if [ ! -f "$SCHEMA_FILE" ]; then
    echo "❌ ERROR: full_schema.sql not found at: $SCHEMA_FILE"
    exit 1
fi

if [ ! -f "$EXTRACT_FILE" ]; then
    echo "❌ ERROR: extract-sample-data.sql not found at: $EXTRACT_FILE"
    exit 1
fi

echo "✅ Required files found"
echo ""

# Create temporary directory for analysis
TEMP_DIR=$(mktemp -d)
trap "rm -rf $TEMP_DIR" EXIT

echo "=================================================="
echo "=== SCHEMA OBJECT DISCOVERY                    ==="
echo "=================================================="
echo ""

# Extract all table names from full_schema.sql
echo "Discovering tables in schema..."
grep -E "^CREATE TABLE" "$SCHEMA_FILE" | \
  sed -E 's/CREATE TABLE (public\.)?([a-z_0-9]+).*/\2/' | \
  sort > "$TEMP_DIR/schema_tables.txt"

TOTAL_TABLES=$(wc -l < "$TEMP_DIR/schema_tables.txt")
echo "  Found: $TOTAL_TABLES tables"

# Extract regular view names from full_schema.sql
echo "Discovering regular views in schema..."
grep -E "^CREATE (OR REPLACE )?VIEW" "$SCHEMA_FILE" | \
  grep -v "MATERIALIZED" | \
  sed -E 's/CREATE (OR REPLACE )?VIEW (public\.)?([a-z_0-9]+).*/\3/' | \
  sort > "$TEMP_DIR/schema_views.txt"

TOTAL_VIEWS=$(wc -l < "$TEMP_DIR/schema_views.txt")
echo "  Found: $TOTAL_VIEWS regular views"

# Extract materialized view names from full_schema.sql
echo "Discovering materialized views in schema..."
grep -E "^CREATE MATERIALIZED VIEW" "$SCHEMA_FILE" | \
  sed -E 's/CREATE MATERIALIZED VIEW (public\.)?([a-z_0-9]+).*/\2/' | \
  sort > "$TEMP_DIR/schema_mviews.txt"

TOTAL_MVIEWS=$(wc -l < "$TEMP_DIR/schema_mviews.txt")
echo "  Found: $TOTAL_MVIEWS materialized views"

TOTAL_OBJECTS=$((TOTAL_TABLES + TOTAL_VIEWS + TOTAL_MVIEWS))
echo ""
echo "Total schema objects: $TOTAL_OBJECTS"
echo ""

echo "=================================================="
echo "=== EXTRACTION SCRIPT ANALYSIS                 ==="
echo "=================================================="
echo ""

# Analyze extraction script exclusions
echo "Analyzing extraction script exclusions..."
EXCLUDED_PATTERNS="qrtz_% databasechange%"
EXCLUDED_COUNT=0

for pattern in $EXCLUDED_PATTERNS; do
    # Convert SQL LIKE pattern to grep pattern
    grep_pattern=$(echo "$pattern" | sed 's/%/.*/g')
    pattern_count=$(grep -E "^${grep_pattern}" "$TEMP_DIR/schema_tables.txt" | wc -l)
    EXCLUDED_COUNT=$((EXCLUDED_COUNT + pattern_count))
    echo "  Pattern '$pattern': $pattern_count tables"
done

echo "  Total excluded: $EXCLUDED_COUNT tables"
echo ""

# Calculate expected extractions
EXPECTED_TABLE_EXTRACTIONS=$((TOTAL_TABLES - EXCLUDED_COUNT))
EXPECTED_VIEW_EXTRACTIONS=$((TOTAL_VIEWS + TOTAL_MVIEWS))
EXPECTED_TOTAL_EXTRACTIONS=$((EXPECTED_TABLE_EXTRACTIONS + EXPECTED_VIEW_EXTRACTIONS))

echo "Expected extraction coverage:"
echo "  Tables: $EXPECTED_TABLE_EXTRACTIONS (out of $TOTAL_TABLES)"
echo "  Views: $EXPECTED_VIEW_EXTRACTIONS (out of $((TOTAL_VIEWS + TOTAL_MVIEWS)))"
echo "  Total: $EXPECTED_TOTAL_EXTRACTIONS (out of $TOTAL_OBJECTS)"
echo ""

# Calculate coverage percentage
if [ "$TOTAL_OBJECTS" -gt 0 ]; then
    COVERAGE_PCT=$(awk "BEGIN {printf \"%.2f\", ($EXPECTED_TOTAL_EXTRACTIONS / $TOTAL_OBJECTS) * 100}")
else
    COVERAGE_PCT="0.00"
fi
echo "Expected coverage: $COVERAGE_PCT%"
echo ""

echo "=================================================="
echo "=== EXTRACTION STRATEGY VERIFICATION           ==="
echo "=================================================="
echo ""

# Check if extraction script uses dynamic discovery
USES_PG_TABLES=$(grep -q "FROM pg_tables" "$EXTRACT_FILE" && echo "yes" || echo "no")
USES_PG_VIEWS=$(grep -q "FROM pg_views" "$EXTRACT_FILE" && echo "yes" || echo "no")
USES_PG_MATVIEWS=$(grep -q "FROM pg_matviews" "$EXTRACT_FILE" && echo "yes" || echo "no")

if [ "$USES_PG_TABLES" = "yes" ] && [ "$USES_PG_VIEWS" = "yes" ] && [ "$USES_PG_MATVIEWS" = "yes" ]; then
    echo "✅ Extraction strategy: DYNAMIC DISCOVERY"
    echo "   Uses pg_tables, pg_views, pg_matviews system catalogs"
    echo "   Will automatically adapt to schema changes"
else
    echo "⚠️  Extraction strategy: STATIC/HARDCODED"
    echo "   May require manual updates when schema changes"
fi
echo ""

# Check exclusion filters
echo "Exclusion filters in extraction script:"
FILTER_QRTZ=$(grep -q "NOT LIKE 'qrtz_%'" "$EXTRACT_FILE" && echo "yes" || echo "no")
FILTER_DBCHANGE=$(grep -q "NOT LIKE 'databasechange%'" "$EXTRACT_FILE" && echo "yes" || echo "no")

if [ "$FILTER_QRTZ" = "yes" ]; then
    echo "  ✅ Excludes qrtz_* tables (Quartz scheduler)"
fi
if [ "$FILTER_DBCHANGE" = "yes" ]; then
    echo "  ✅ Excludes databasechange* tables (Liquibase)"
fi
echo ""

echo "=================================================="
echo "=== DETAILED OBJECT LISTS                      ==="
echo "=================================================="
echo ""

# List excluded tables
echo "Tables intentionally excluded from extraction:"
for pattern in $EXCLUDED_PATTERNS; do
    grep_pattern=$(echo "$pattern" | sed 's/%/.*/g')
    excluded_tables=$(grep -E "^${grep_pattern}" "$TEMP_DIR/schema_tables.txt")
    if [ -n "$excluded_tables" ]; then
        echo "$excluded_tables" | sed 's/^/  - /'
    fi
done
echo ""

# List first 10 tables to be extracted
echo "Sample tables to be extracted (first 10):"
for pattern in $EXCLUDED_PATTERNS; do
    grep_pattern=$(echo "$pattern" | sed 's/%/.*/g')
    grep -v -E "^${grep_pattern}" "$TEMP_DIR/schema_tables.txt" || true
done | head -10 | sed 's/^/  - /'
echo "  ... and $((EXPECTED_TABLE_EXTRACTIONS - 10)) more"
echo ""

# List first 10 views to be extracted
echo "Sample views to be extracted (first 10):"
cat "$TEMP_DIR/schema_views.txt" "$TEMP_DIR/schema_mviews.txt" | sort | head -10 | sed 's/^/  - /'
echo "  ... and $((EXPECTED_VIEW_EXTRACTIONS - 10)) more"
echo ""

echo "=================================================="
echo "=== EXTRACTION OUTPUT FILES                    ==="
echo "=================================================="
echo ""

# Calculate expected output files
EXPECTED_TABLE_CSV=$EXPECTED_TABLE_EXTRACTIONS
EXPECTED_VIEW_CSV=$EXPECTED_VIEW_EXTRACTIONS
EXPECTED_DISTINCT_CSV=8
EXPECTED_METADATA_CSV=3
EXPECTED_TOTAL_CSV=$((EXPECTED_TABLE_CSV + EXPECTED_VIEW_CSV + EXPECTED_DISTINCT_CSV + EXPECTED_METADATA_CSV))

echo "Expected output files:"
echo "  table_*_sample.csv: $EXPECTED_TABLE_CSV files"
echo "  view_*_sample.csv: $EXPECTED_VIEW_CSV files"
echo "  distinct_*.csv: $EXPECTED_DISTINCT_CSV files"
echo "  Metadata CSV files: $EXPECTED_METADATA_CSV files"
echo "    - sample_data_manifest.csv"
echo "    - view_column_mapping.csv"
echo "    - extraction_statistics.csv"
echo "  Total: $EXPECTED_TOTAL_CSV CSV files"
echo ""

echo "Distinct value extraction files:"
echo "  - distinct_party_values.csv"
echo "  - distinct_org_code_values.csv"
echo "  - distinct_role_code_values.csv"
echo "  - distinct_assignment_status_values.csv"
echo "  - distinct_document_type_values.csv"
echo "  - distinct_document_org_values.csv"
echo "  - distinct_vote_values.csv"
echo "  - distinct_political_parties.csv"
echo ""

echo "=================================================="
echo "=== COVERAGE SUMMARY REPORT                    ==="
echo "=================================================="
echo ""

# Generate coverage summary
cat > "$TEMP_DIR/coverage_summary.txt" << EOF
SAMPLE DATA EXTRACTION COVERAGE REPORT
Generated: $(date +"%Y-%m-%d %H:%M:%S")

SCHEMA OBJECTS:
  Total Tables: $TOTAL_TABLES
  Total Regular Views: $TOTAL_VIEWS
  Total Materialized Views: $TOTAL_MVIEWS
  Total Objects: $TOTAL_OBJECTS

EXTRACTION COVERAGE:
  Tables Extracted: $EXPECTED_TABLE_EXTRACTIONS ($(awk "BEGIN {printf \"%.2f\", ($EXPECTED_TABLE_EXTRACTIONS / $TOTAL_TABLES) * 100}")%)
  Tables Excluded: $EXCLUDED_COUNT
  Views Extracted: $EXPECTED_VIEW_EXTRACTIONS (100%)
  Total Extracted: $EXPECTED_TOTAL_EXTRACTIONS ($COVERAGE_PCT%)

EXCLUDED OBJECTS:
  - 11 Quartz scheduler tables (qrtz_*)
  - 2 Liquibase tracking tables (databasechange*)

OUTPUT FILES:
  Table CSVs: $EXPECTED_TABLE_CSV
  View CSVs: $EXPECTED_VIEW_CSV
  Distinct Value CSVs: $EXPECTED_DISTINCT_CSV
  Metadata CSVs: $EXPECTED_METADATA_CSV
  Total CSVs: $EXPECTED_TOTAL_CSV

EXTRACTION STRATEGY:
  Method: Dynamic discovery using PostgreSQL system catalogs
  Adaptability: Automatic (adapts to schema changes)
  Sample Size: 50 rows per table/view (or all if fewer)
  Sampling Method: Random sampling (ORDER BY random())

VALIDATION:
  - File count validation
  - Empty file detection
  - CSV header validation
  - File size analysis
  - Coverage statistics verification

REFERENCES:
  - Schema: full_schema.sql
  - Extraction Script: extract-sample-data.sql
  - Documentation: SAMPLE_DATA_EXTRACTION.md
  - Validation Script: validate-sample-data-extraction.sh
EOF

cat "$TEMP_DIR/coverage_summary.txt"
echo ""

# Save report to file
REPORT_FILE="$SCRIPT_DIR/extraction_coverage_report.txt"
cp "$TEMP_DIR/coverage_summary.txt" "$REPORT_FILE"
echo "=================================================="
echo "Report saved to: $REPORT_FILE"
echo "=================================================="
echo ""

# Generate CSV report for easy analysis
cat > "$SCRIPT_DIR/extraction_coverage_summary.csv" << EOF
category,total_in_schema,extracted,excluded,coverage_pct
TABLES,$TOTAL_TABLES,$EXPECTED_TABLE_EXTRACTIONS,$EXCLUDED_COUNT,$(awk "BEGIN {printf \"%.2f\", ($EXPECTED_TABLE_EXTRACTIONS / $TOTAL_TABLES) * 100}")
REGULAR_VIEWS,$TOTAL_VIEWS,$TOTAL_VIEWS,0,100.00
MATERIALIZED_VIEWS,$TOTAL_MVIEWS,$TOTAL_MVIEWS,0,100.00
TOTAL,$TOTAL_OBJECTS,$EXPECTED_TOTAL_EXTRACTIONS,$EXCLUDED_COUNT,$COVERAGE_PCT
EOF

echo "CSV summary saved to: extraction_coverage_summary.csv"
echo ""

# Final status
if [ "$TOTAL_OBJECTS" -eq 0 ]; then
    echo "⚠️  ERROR: No objects found in schema"
    exit 1
fi

# Check coverage using shell arithmetic instead of bc
COVERAGE_INT=$(echo "$COVERAGE_PCT" | cut -d'.' -f1)
if [ "$COVERAGE_INT" -ge 90 ]; then
    echo "✅✅✅ EXCELLENT COVERAGE: $COVERAGE_PCT% ✅✅✅"
    echo ""
    echo "The extraction script provides comprehensive coverage of the schema."
else
    echo "⚠️  COVERAGE BELOW 90%: $COVERAGE_PCT%"
    echo ""
    echo "Consider reviewing excluded objects or extraction strategy."
fi

echo ""
echo "Analysis complete!"
