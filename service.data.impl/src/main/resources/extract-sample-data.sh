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
echo "  SQL script: $SQL_SCRIPT"
echo "  Sample size: 50 rows per table/view"
echo "  Verbosity: Enhanced debug logging"
echo "  Exclusions: view_riksdagen_coalition_alignment_matrix (complex query)"
echo "  Distinct values: Only columns used in view WHERE/JOIN/GROUP BY clauses"
echo ""

# Create output directory if it doesn't exist
mkdir -p "$OUTPUT_DIR"

# Change to output directory
cd "$OUTPUT_DIR"

echo "=================================================="
echo "=== STARTING EXTRACTION                        ==="
echo "=================================================="
echo ""
echo "Note: Progress will be shown before each operation"
echo "      Long-running operations will display timestamps"
echo ""

# Run the SQL script with verbose output
psql -U "$PSQL_USER" -d "$DATABASE" -f "$SQL_SCRIPT" 2>&1 | tee extract-sample-data.log

echo ""
echo "=================================================="
echo "=== EXTRACTION COMPLETED                       ==="
echo "=================================================="
echo ""
echo "Full log saved to: extract-sample-data.log"
echo ""

echo "=================================================="
echo "=== VALIDATION                                 ==="
echo "=================================================="
echo ""

# Count actual files
ACTUAL_TABLES=$(ls -1 table_*_sample.csv 2>/dev/null | wc -l)
ACTUAL_VIEWS=$(ls -1 view_*_sample.csv 2>/dev/null | wc -l)
ACTUAL_DISTINCT=$(ls -1 distinct_*.csv 2>/dev/null | wc -l)
ACTUAL_METADATA=$(ls -1 sample_data_manifest.csv view_column_mapping.csv extraction_statistics.csv 2>/dev/null | wc -l)
TOTAL_CSV=$(ls -1 *.csv 2>/dev/null | wc -l)

echo "Generated CSV Files:"
echo "  Table samples: $ACTUAL_TABLES"
echo "  View samples: $ACTUAL_VIEWS"
echo "  Distinct value sets: $ACTUAL_DISTINCT"
echo "  Metadata files: $ACTUAL_METADATA"
echo "  Total CSV files: $TOTAL_CSV"
echo ""

# Validation checks
VALIDATION_FAILED=0

# Check for empty CSV files
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
        if [ -s "$file" ]; then
            HEADER_COUNT=$(head -1 "$file" 2>/dev/null | tr ',' '\n' | wc -l)
            if [ "$HEADER_COUNT" -eq 0 ]; then
                echo "⚠️  WARNING: No header in: $file"
                INVALID_HEADERS=$((INVALID_HEADERS + 1))
                VALIDATION_FAILED=1
            fi
        fi
    fi
done

if [ "$INVALID_HEADERS" -eq 0 ]; then
    echo "✅ All CSV files have valid headers"
else
    echo "⚠️  WARNING: $INVALID_HEADERS CSV files have invalid headers"
fi

echo ""

# File size check
echo "Checking file sizes..."
SMALL_FILES=$(find . -maxdepth 1 -name "*_sample.csv" -size -100c 2>/dev/null)
if [ -n "$SMALL_FILES" ]; then
    echo "ℹ️  INFO: Found small CSV files (< 100 bytes):"
    echo "$SMALL_FILES" | while IFS= read -r file; do
        if [ -f "$file" ]; then
            ls -lh "$file" 2>/dev/null | awk '{print "    " $9 " - " $5}'
        fi
    done
    echo "    (These may be from nearly-empty tables/views)"
else
    echo "✅ All sample CSV files have reasonable sizes"
fi

echo ""

# Display extraction statistics if available
if [ -f "extraction_statistics.csv" ]; then
    echo "Coverage Statistics:"
    echo ""
    if command -v column &> /dev/null; then
        column -t -s',' < extraction_statistics.csv
    else
        cat extraction_statistics.csv
    fi
    echo ""
fi

# Check for empty views in the log
echo "Checking for empty view warnings in log..."
EMPTY_VIEW_COUNT=$(grep -c "EMPTY VIEW" extract-sample-data.log 2>/dev/null || echo "0")
if [ "$EMPTY_VIEW_COUNT" -gt 0 ]; then
    echo "⚠️  WARNING: Found $EMPTY_VIEW_COUNT empty views!"
    echo "    See extract-sample-data.log for details"
    echo ""
    echo "    Empty views detected:"
    grep "EMPTY VIEW" extract-sample-data.log | sed 's/^/    /'
    VALIDATION_FAILED=1
else
    echo "✅ No empty views detected"
fi

echo ""

# Summary
echo "=================================================="
if [ "$VALIDATION_FAILED" -eq 0 ]; then
    echo "✅ Validation Passed - All Checks Successful"
else
    echo "⚠️  Validation Completed with Warnings"
    echo "    Review warnings above and check extract-sample-data.log"
fi
echo "=================================================="
echo ""

echo "=================================================="
echo "=== ADVANCED VALIDATION (Coverage Metrics)    ==="
echo "=================================================="
echo ""

# Initialize validation state
VALIDATION_FAILED=0

# Temporal Coverage Validation
echo "📅 Temporal Coverage Validation:"
echo "   Checking for data from years 2002-2026..."

# Check if extraction_statistics.csv exists and has temporal data
if [ -f "extraction_statistics.csv" ]; then
    # Extract plausible years (1900-2100) from temporal stratification results
    EARLIEST_YEAR=$(grep -oE "19[0-9]{2}|20[0-9]{2}|2100" extraction_statistics.csv 2>/dev/null | sort -n | head -1)
    LATEST_YEAR=$(grep -oE "19[0-9]{2}|20[0-9]{2}|2100" extraction_statistics.csv 2>/dev/null | sort -n | tail -1)
    
    if [ -n "$EARLIEST_YEAR" ] && [ -n "$LATEST_YEAR" ]; then
        YEAR_RANGE=$((LATEST_YEAR - EARLIEST_YEAR))
        echo "   ✓ Data range: $EARLIEST_YEAR - $LATEST_YEAR ($YEAR_RANGE years)"
        
        if [ "$YEAR_RANGE" -lt 10 ]; then
            echo "   ⚠️  WARNING: Temporal coverage < 10 years (found $YEAR_RANGE years)"
            echo "      Expected: At least 10 years for meaningful trend analysis"
            VALIDATION_FAILED=1
        else
            echo "   ✓ Temporal coverage adequate (>= 10 years)"
        fi
    else
        echo "   ℹ️  INFO: Could not extract year range from extraction_statistics.csv"
    fi
else
    echo "   ⚠️  WARNING: extraction_statistics.csv not found, cannot validate temporal coverage"
    VALIDATION_FAILED=1
fi

echo ""

# Categorical Coverage Validation (Swedish Political Parties)
echo "🏛️  Categorical Coverage Validation (Political Parties):"
echo "   Checking for all 8 major Swedish parties: S, M, SD, C, V, KD, L, MP..."

# Expected parties
EXPECTED_PARTIES="S M SD C V KD L MP"
MISSING_PARTIES=""

# Ensure that at least one expected distribution file exists before validating
HAS_PARTY_DISTRIBUTION_FILES=0
for pattern in "distribution_party_*.csv" "distribution_*_by_party.csv" "distribution_*.csv"; do
    if compgen -G "$pattern" > /dev/null 2>&1; then
        HAS_PARTY_DISTRIBUTION_FILES=1
        break
    fi
done

if [ "$HAS_PARTY_DISTRIBUTION_FILES" -eq 0 ]; then
    echo "   ⚠️  WARNING: No party distribution CSV files found."
    echo "      Expected one or more of: distribution_party_*.csv, distribution_*_by_party.csv, distribution_*.csv"
    echo "      Cannot validate categorical coverage for political parties."
    VALIDATION_FAILED=1
else
    for party in $EXPECTED_PARTIES; do
        # Check if party appears in any distribution file
        if grep -q "^$party," distribution_party_*.csv 2>/dev/null || \
           grep -q ",$party," distribution_*_by_party.csv 2>/dev/null || \
           grep -q "\"$party\"" distribution_*.csv 2>/dev/null; then
            echo "   ✓ Found party: $party"
        else
            echo "   ⚠️  Missing party: $party"
            MISSING_PARTIES="$MISSING_PARTIES $party"
            VALIDATION_FAILED=1
        fi
    done

    if [ -z "$MISSING_PARTIES" ]; then
        echo "   ✓ All 8 major parties present in sample data"
    else
        echo "   ⚠️  WARNING: Missing parties:$MISSING_PARTIES"
        echo "      This may indicate incomplete political representation in sample"
    fi
fi

echo ""

# Percentile Coverage Validation
echo "📊 Percentile Coverage Validation:"
echo "   Checking for percentile distribution summaries (P1, P10, P25, P50, P75, P90, P99)..."

PERCENTILE_FILES=$(ls -1 percentile_*.csv 2>/dev/null | wc -l)

if [ "$PERCENTILE_FILES" -gt 0 ]; then
    echo "   ✓ Found $PERCENTILE_FILES percentile distribution summary files"
    
    # Validate percentile columns in first file
    SAMPLE_FILE=$(ls -1 percentile_*.csv 2>/dev/null | head -1)
    if [ -f "$SAMPLE_FILE" ]; then
        HEADER=$(head -1 "$SAMPLE_FILE")
        
        # Check for required percentile columns
        REQUIRED_COLS="p1 p10 p25 median p75 p90 p99"
        MISSING_COLS=""
        
        for col in $REQUIRED_COLS; do
            if ! echo "$HEADER" | grep -q "$col"; then
                MISSING_COLS="$MISSING_COLS $col"
            fi
        done
        
        if [ -z "$MISSING_COLS" ]; then
            echo "   ✓ Percentile files contain all required columns (P1-P99)"
        else
            echo "   ⚠️  WARNING: Missing percentile columns:$MISSING_COLS"
            echo "      File: $SAMPLE_FILE"
            VALIDATION_FAILED=1
        fi
    fi
else
    echo "   ⚠️  WARNING: No percentile distribution files found"
    echo "      Expected: percentile_*.csv files with P1, P10, P25, P50, P75, P90, P99"
    VALIDATION_FAILED=1
fi

echo ""

# Generate Coverage Report
echo "📋 Generating validation coverage report..."

COVERAGE_REPORT="validation_coverage_report.csv"

# Prepare properly escaped status and details for CSV
if [ -n "$EARLIEST_YEAR" ] && [ -n "$LATEST_YEAR" ]; then
    TEMPORAL_STATUS="PASS"
    TEMPORAL_DETAILS="$EARLIEST_YEAR-$LATEST_YEAR ($YEAR_RANGE years)"
else
    TEMPORAL_STATUS="FAIL"
    TEMPORAL_DETAILS="Unable to determine year range"
fi

if [ -z "$MISSING_PARTIES" ]; then
    PARTY_STATUS="PASS"
    PARTY_DETAILS="All 8 parties present"
else
    PARTY_STATUS="WARNING"
    PARTY_DETAILS="Missing parties -$MISSING_PARTIES"
fi

if [ "$PERCENTILE_FILES" -gt 0 ]; then
    PERCENTILE_STATUS="PASS"
    PERCENTILE_DETAILS="$PERCENTILE_FILES files generated"
else
    PERCENTILE_STATUS="FAIL"
    PERCENTILE_DETAILS="No percentile files found"
fi

# Escape double quotes in CSV values
TEMPORAL_STATUS_ESCAPED=${TEMPORAL_STATUS//\"/\"\"}
TEMPORAL_DETAILS_ESCAPED=${TEMPORAL_DETAILS//\"/\"\"}
PARTY_STATUS_ESCAPED=${PARTY_STATUS//\"/\"\"}
PARTY_DETAILS_ESCAPED=${PARTY_DETAILS//\"/\"\"}
PERCENTILE_STATUS_ESCAPED=${PERCENTILE_STATUS//\"/\"\"}
PERCENTILE_DETAILS_ESCAPED=${PERCENTILE_DETAILS//\"/\"\"}

cat > "$COVERAGE_REPORT" << EOF
validation_type,status,details
temporal_coverage,"$TEMPORAL_STATUS_ESCAPED","$TEMPORAL_DETAILS_ESCAPED"
party_coverage,"$PARTY_STATUS_ESCAPED","$PARTY_DETAILS_ESCAPED"
percentile_coverage,"$PERCENTILE_STATUS_ESCAPED","$PERCENTILE_DETAILS_ESCAPED"
EOF

echo "   ✓ Generated: $COVERAGE_REPORT"
echo ""

echo "=================================================="
echo "=== FILE LISTING                               ==="
echo "=================================================="
echo ""
echo "Sample of generated files:"
if [ "$TOTAL_CSV" -gt 0 ]; then
    ls -lh *.csv 2>/dev/null | head -30
    echo ""
    if [ "$TOTAL_CSV" -gt 30 ]; then
        echo "... and $((TOTAL_CSV - 30)) more files"
        echo ""
    fi
else
    echo "  No CSV files found"
fi
echo ""

echo "=================================================="
echo "=== SUMMARY                                    ==="
echo "=================================================="
echo ""
echo "Output directory: $OUTPUT_DIR"
echo "Total CSV files: $TOTAL_CSV"
echo "  - Table samples: $ACTUAL_TABLES"
echo "  - View samples: $ACTUAL_VIEWS"
echo "  - Distinct value sets: $ACTUAL_DISTINCT"
echo "  - Percentile distributions: $PERCENTILE_FILES"
echo "  - Metadata files: $ACTUAL_METADATA"
echo ""
echo "Coverage Validation:"
if [ -f "$COVERAGE_REPORT" ]; then
    echo "  - Validation report: $COVERAGE_REPORT"
    if [ "$VALIDATION_FAILED" -eq 0 ]; then
        echo "  - Status: ✅ All validation checks passed"
    else
        echo "  - Status: ⚠️  Validation completed with warnings"
    fi
else
    echo "  - Validation report: Not generated"
fi
echo ""
echo "Full extraction log: extract-sample-data.log"
echo ""

exit $VALIDATION_FAILED
