#!/bin/bash
# extract-sample-data.sh
# Shell wrapper for sample data extraction
# 
# Usage:
#   ./extract-sample-data.sh [output_directory] [database_name]
#
# Examples:
#   ./extract-sample-data.sh                    # Extract to current directory from cia_dev
#   ./extract-sample-data.sh /tmp/samples       # Extract to /tmp/samples from cia_dev
#   ./extract-sample-data.sh /tmp/samples cia_prod  # Extract from cia_prod

set -e

# Configuration
OUTPUT_DIR="${1:-.}"
DATABASE="${2:-cia_dev}"
PSQL_USER="${PSQL_USER:-postgres}"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SQL_SCRIPT="$SCRIPT_DIR/extract-sample-data.sql"

echo "=================================================="
echo "CIA Sample Data Extraction Wrapper"
echo "=================================================="
echo ""
echo "Configuration:"
echo "  Database: $DATABASE"
echo "  Output directory: $OUTPUT_DIR"
echo "  PostgreSQL user: $PSQL_USER"
echo "  Sample size: 50 rows per table/view"
echo ""

# Create output directory if it doesn't exist
mkdir -p "$OUTPUT_DIR"

# Change to output directory
cd "$OUTPUT_DIR"

echo "Extracting sample data..."
echo ""

# Run the SQL script
psql -U "$PSQL_USER" -d "$DATABASE" -f "$SQL_SCRIPT"

echo ""
echo "=================================================="
echo "Extraction Complete!"
echo "=================================================="
echo ""
echo "=================================================="
echo "=== VALIDATION                                 ==="
echo "=================================================="
echo ""

# Expected counts based on schema analysis
EXPECTED_TABLES=80
EXPECTED_VIEWS=84
EXPECTED_DISTINCT=8
EXPECTED_METADATA=3

# Actual counts
ACTUAL_TABLES=$(ls -1 table_*_sample.csv 2>/dev/null | wc -l)
ACTUAL_VIEWS=$(ls -1 view_*_sample.csv 2>/dev/null | wc -l)
ACTUAL_DISTINCT=$(ls -1 distinct_*.csv 2>/dev/null | wc -l)
ACTUAL_METADATA=$(ls -1 sample_data_manifest.csv view_column_mapping.csv extraction_statistics.csv 2>/dev/null | wc -l)
TOTAL_CSV=$(ls -1 *.csv 2>/dev/null | wc -l)

echo "Expected vs Actual CSV Files:"
echo "  Tables: $ACTUAL_TABLES / $EXPECTED_TABLES expected"
echo "  Views: $ACTUAL_VIEWS / $EXPECTED_VIEWS expected"
echo "  Distinct value sets: $ACTUAL_DISTINCT / $EXPECTED_DISTINCT expected"
echo "  Metadata files: $ACTUAL_METADATA / $EXPECTED_METADATA expected"
echo "  Total: $TOTAL_CSV / $((EXPECTED_TABLES + EXPECTED_VIEWS + EXPECTED_DISTINCT + EXPECTED_METADATA)) expected"
echo ""

# Validation checks
VALIDATION_FAILED=0

if [ "$ACTUAL_TABLES" -lt "$EXPECTED_TABLES" ]; then
    echo "⚠️  WARNING: Missing table extractions!"
    echo "    Missing: $((EXPECTED_TABLES - ACTUAL_TABLES)) table CSV files"
    VALIDATION_FAILED=1
else
    echo "✅ All expected tables extracted"
fi

if [ "$ACTUAL_VIEWS" -lt "$EXPECTED_VIEWS" ]; then
    echo "⚠️  WARNING: Missing view extractions!"
    echo "    Missing: $((EXPECTED_VIEWS - ACTUAL_VIEWS)) view CSV files"
    VALIDATION_FAILED=1
else
    echo "✅ All expected views extracted"
fi

if [ "$ACTUAL_DISTINCT" -lt "$EXPECTED_DISTINCT" ]; then
    echo "⚠️  WARNING: Missing distinct value extractions!"
    echo "    Missing: $((EXPECTED_DISTINCT - ACTUAL_DISTINCT)) distinct value CSV files"
    VALIDATION_FAILED=1
else
    echo "✅ All distinct value sets extracted"
fi

if [ "$ACTUAL_METADATA" -lt "$EXPECTED_METADATA" ]; then
    echo "⚠️  WARNING: Missing metadata files!"
    echo "    Missing: $((EXPECTED_METADATA - ACTUAL_METADATA)) metadata CSV files"
    VALIDATION_FAILED=1
else
    echo "✅ All metadata files generated"
fi

echo ""

# Check for empty CSV files (should have at least headers)
echo "Checking for empty CSV files..."
EMPTY_FILES=$(find . -maxdepth 1 -name "*.csv" -size 0 2>/dev/null)
if [ -n "$EMPTY_FILES" ]; then
    echo "⚠️  WARNING: Found empty CSV files:"
    echo "$EMPTY_FILES" | sed 's/^/    /'
    VALIDATION_FAILED=1
else
    echo "✅ No empty CSV files found"
fi

echo ""

# Check CSV format (has headers)
echo "Validating CSV headers..."
INVALID_HEADERS=0
for file in *.csv; do
    if [ -f "$file" ]; then
        HEADER_COUNT=$(head -1 "$file" 2>/dev/null | tr ',' '\n' | wc -l)
        if [ "$HEADER_COUNT" -eq 0 ]; then
            echo "⚠️  WARNING: No header in: $file"
            INVALID_HEADERS=$((INVALID_HEADERS + 1))
            VALIDATION_FAILED=1
        fi
    fi
done

if [ "$INVALID_HEADERS" -eq 0 ]; then
    echo "✅ All CSV files have valid headers"
else
    echo "⚠️  WARNING: $INVALID_HEADERS CSV files have invalid headers"
fi

echo ""

# File size check (warn about unusually small files)
echo "Checking file sizes..."
SMALL_FILES=$(find . -maxdepth 1 -name "*_sample.csv" -size -100c 2>/dev/null)
if [ -n "$SMALL_FILES" ]; then
    echo "⚠️  INFO: Found suspiciously small CSV files (< 100 bytes):"
    echo "$SMALL_FILES" | while IFS= read -r file; do
        if [ -f "$file" ]; then
            ls -lh "$file" 2>/dev/null | awk '{print "    " $9 " - " $5}'
        fi
    done
    echo "    (These may be from empty tables/views)"
else
    echo "✅ All sample CSV files have reasonable sizes"
fi

echo ""

# Display extraction statistics if available
if [ -f "extraction_statistics.csv" ]; then
    echo "Coverage Statistics (from extraction_statistics.csv):"
    if command -v column &> /dev/null; then
        column -t -s',' < extraction_statistics.csv | sed 's/^/  /'
    else
        sed 's/^/  /' < extraction_statistics.csv
    fi
    echo ""
fi

echo ""
echo "=================================================="
if [ "$VALIDATION_FAILED" -eq 0 ]; then
    echo "✅ Validation Passed - All Checks Successful"
else
    echo "⚠️  Validation Completed with Warnings"
    echo "    Review warnings above for details"
fi
echo "=================================================="
echo ""
echo ""
echo "Files generated in: $OUTPUT_DIR"
echo ""
if compgen -G "table_*_sample.csv" > /dev/null || compgen -G "view_*_sample.csv" > /dev/null || compgen -G "*.csv" > /dev/null; then
    echo "Sample of generated files:"
    ls -lh table_*_sample.csv view_*_sample.csv *.csv 2>/dev/null | head -30
else
    echo "  No CSV files found"
fi
echo ""
echo "Total CSV files: $(ls -1 *.csv 2>/dev/null | wc -l)"
echo "  - Tables: $(ls -1 table_*_sample.csv 2>/dev/null | wc -l)"
echo "  - Views: $(ls -1 view_*_sample.csv 2>/dev/null | wc -l)"
echo "  - Distinct value sets: $(ls -1 distinct_*.csv 2>/dev/null | wc -l)"
echo "  - Other: $(ls -1 *.csv 2>/dev/null | grep -v -E '(table_|view_|distinct_)' | wc -l)"
echo ""
