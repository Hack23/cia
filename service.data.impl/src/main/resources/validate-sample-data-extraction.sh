#!/bin/bash
# validate-sample-data-extraction.sh
# Validation script for sample data extraction
# 
# Usage:
#   ./validate-sample-data-extraction.sh [directory]
#
# Examples:
#   ./validate-sample-data-extraction.sh                # Validate current directory
#   ./validate-sample-data-extraction.sh /tmp/samples   # Validate specific directory

set -e

# Configuration
VALIDATE_DIR="${1:-.}"

echo "=================================================="
echo "CIA Sample Data Extraction Validator"
echo "=================================================="
echo ""
echo "Validating directory: $VALIDATE_DIR"
echo ""

cd "$VALIDATE_DIR"

# Expected counts based on schema analysis
EXPECTED_TABLES=80
EXPECTED_VIEWS=84
EXPECTED_DISTINCT=8
EXPECTED_METADATA=3
EXPECTED_TOTAL=$((EXPECTED_TABLES + EXPECTED_VIEWS + EXPECTED_DISTINCT + EXPECTED_METADATA))

echo "=================================================="
echo "=== FILE COUNT VALIDATION                      ==="
echo "=================================================="
echo ""

# Actual counts
ACTUAL_TABLES=$(ls -1 table_*_sample.csv 2>/dev/null | wc -l)
ACTUAL_VIEWS=$(ls -1 view_*_sample.csv 2>/dev/null | wc -l)
ACTUAL_DISTINCT=$(ls -1 distinct_*.csv 2>/dev/null | wc -l)
ACTUAL_METADATA=0
[ -f "sample_data_manifest.csv" ] && ACTUAL_METADATA=$((ACTUAL_METADATA + 1))
[ -f "view_column_mapping.csv" ] && ACTUAL_METADATA=$((ACTUAL_METADATA + 1))
[ -f "extraction_statistics.csv" ] && ACTUAL_METADATA=$((ACTUAL_METADATA + 1))
TOTAL_CSV=$(ls -1 *.csv 2>/dev/null | wc -l)

echo "Expected vs Actual CSV Files:"
echo "  Tables: $ACTUAL_TABLES / $EXPECTED_TABLES expected"
echo "  Views: $ACTUAL_VIEWS / $EXPECTED_VIEWS expected"
echo "  Distinct value sets: $ACTUAL_DISTINCT / $EXPECTED_DISTINCT expected"
echo "  Metadata files: $ACTUAL_METADATA / $EXPECTED_METADATA expected"
echo "  Total: $TOTAL_CSV / $EXPECTED_TOTAL expected"
echo ""

# Track validation status
VALIDATION_FAILED=0
WARNINGS=0

# Validate counts
if [ "$ACTUAL_TABLES" -lt "$EXPECTED_TABLES" ]; then
    echo "❌ FAIL: Missing table extractions!"
    echo "   Missing: $((EXPECTED_TABLES - ACTUAL_TABLES)) table CSV files"
    VALIDATION_FAILED=1
elif [ "$ACTUAL_TABLES" -gt "$EXPECTED_TABLES" ]; then
    echo "⚠️  WARNING: More tables than expected"
    echo "   Extra: $((ACTUAL_TABLES - EXPECTED_TABLES)) table CSV files"
    WARNINGS=$((WARNINGS + 1))
else
    echo "✅ PASS: All expected tables extracted ($ACTUAL_TABLES files)"
fi

if [ "$ACTUAL_VIEWS" -lt "$EXPECTED_VIEWS" ]; then
    echo "❌ FAIL: Missing view extractions!"
    echo "   Missing: $((EXPECTED_VIEWS - ACTUAL_VIEWS)) view CSV files"
    VALIDATION_FAILED=1
elif [ "$ACTUAL_VIEWS" -gt "$EXPECTED_VIEWS" ]; then
    echo "⚠️  WARNING: More views than expected"
    echo "   Extra: $((ACTUAL_VIEWS - EXPECTED_VIEWS)) view CSV files"
    WARNINGS=$((WARNINGS + 1))
else
    echo "✅ PASS: All expected views extracted ($ACTUAL_VIEWS files)"
fi

if [ "$ACTUAL_DISTINCT" -lt "$EXPECTED_DISTINCT" ]; then
    echo "❌ FAIL: Missing distinct value extractions!"
    echo "   Missing: $((EXPECTED_DISTINCT - ACTUAL_DISTINCT)) distinct value CSV files"
    VALIDATION_FAILED=1
else
    echo "✅ PASS: All distinct value sets extracted ($ACTUAL_DISTINCT files)"
fi

if [ "$ACTUAL_METADATA" -lt "$EXPECTED_METADATA" ]; then
    echo "❌ FAIL: Missing metadata files!"
    echo "   Missing: $((EXPECTED_METADATA - ACTUAL_METADATA)) metadata CSV files"
    [ ! -f "sample_data_manifest.csv" ] && echo "   - sample_data_manifest.csv is missing"
    [ ! -f "view_column_mapping.csv" ] && echo "   - view_column_mapping.csv is missing"
    [ ! -f "extraction_statistics.csv" ] && echo "   - extraction_statistics.csv is missing"
    VALIDATION_FAILED=1
else
    echo "✅ PASS: All metadata files generated ($ACTUAL_METADATA files)"
fi

echo ""
echo "=================================================="
echo "=== EMPTY FILE DETECTION                       ==="
echo "=================================================="
echo ""

EMPTY_FILES=$(find . -maxdepth 1 -name "*.csv" -size 0 2>/dev/null)
if [ -n "$EMPTY_FILES" ]; then
    echo "❌ FAIL: Found empty CSV files:"
    echo "$EMPTY_FILES" | sed 's/^/   /'
    VALIDATION_FAILED=1
else
    echo "✅ PASS: No empty CSV files found"
fi

echo ""
echo "=================================================="
echo "=== HEADER VALIDATION                          ==="
echo "=================================================="
echo ""

INVALID_HEADERS=0
CHECKED_FILES=0

for file in *.csv; do
    if [ -f "$file" ]; then
        CHECKED_FILES=$((CHECKED_FILES + 1))
        # Check if file has content
        if [ -s "$file" ]; then
            HEADER_COUNT=$(head -1 "$file" 2>/dev/null | tr ',' '\n' | wc -l)
            if [ "$HEADER_COUNT" -eq 0 ]; then
                echo "❌ FAIL: No header in: $file"
                INVALID_HEADERS=$((INVALID_HEADERS + 1))
                VALIDATION_FAILED=1
            fi
        fi
    fi
done

if [ "$INVALID_HEADERS" -eq 0 ]; then
    echo "✅ PASS: All CSV files have valid headers (checked $CHECKED_FILES files)"
else
    echo "❌ FAIL: $INVALID_HEADERS CSV files have invalid headers"
fi

echo ""
echo "=================================================="
echo "=== FILE SIZE ANALYSIS                         ==="
echo "=================================================="
echo ""

# Check for suspiciously small sample files (< 100 bytes)
SMALL_FILES=$(find . -maxdepth 1 -name "*_sample.csv" -size -100c 2>/dev/null | wc -l)
if [ "$SMALL_FILES" -gt 0 ]; then
    echo "⚠️  WARNING: Found $SMALL_FILES suspiciously small CSV files (< 100 bytes):"
    find . -maxdepth 1 -name "*_sample.csv" -size -100c 2>/dev/null | while read file; do
        SIZE=$(ls -lh "$file" | awk '{print $5}')
        echo "   $file - $SIZE"
    done
    echo "   (These may be from empty tables/views - verify expected)"
    WARNINGS=$((WARNINGS + 1))
else
    echo "✅ PASS: All sample CSV files have reasonable sizes (≥ 100 bytes)"
fi

echo ""

# Report overall file sizes
TOTAL_SIZE=$(du -sh . 2>/dev/null | awk '{print $1}')
echo "Total extraction size: $TOTAL_SIZE"

echo ""
echo "=================================================="
echo "=== COVERAGE STATISTICS                        ==="
echo "=================================================="
echo ""

if [ -f "extraction_statistics.csv" ]; then
    echo "Coverage metrics from extraction_statistics.csv:"
    echo ""
    
    # Display statistics in formatted table
    if command -v column &> /dev/null; then
        cat extraction_statistics.csv | column -t -s',' | sed 's/^/  /'
    else
        cat extraction_statistics.csv | sed 's/^/  /'
    fi
    echo ""
    
    # Extract key metrics
    TOTAL_COVERAGE=$(grep "^TOTAL," extraction_statistics.csv | cut -d',' -f4)
    if [ -n "$TOTAL_COVERAGE" ]; then
        echo "Overall coverage: $TOTAL_COVERAGE%"
        
        # Check if coverage meets threshold
        COVERAGE_INT=$(echo "$TOTAL_COVERAGE" | cut -d'.' -f1)
        if [ "$COVERAGE_INT" -ge 90 ]; then
            echo "✅ PASS: Coverage meets 90% threshold"
        else
            echo "⚠️  WARNING: Coverage below 90% threshold"
            WARNINGS=$((WARNINGS + 1))
        fi
    fi
else
    echo "⚠️  WARNING: extraction_statistics.csv not found"
    echo "   Cannot validate coverage metrics"
    WARNINGS=$((WARNINGS + 1))
fi

echo ""
echo "=================================================="
echo "=== SPECIFIC FILE CHECKS                       ==="
echo "=================================================="
echo ""

# Check for required distinct value files
REQUIRED_DISTINCT=(
    "distinct_party_values.csv"
    "distinct_org_code_values.csv"
    "distinct_role_code_values.csv"
    "distinct_assignment_status_values.csv"
    "distinct_document_type_values.csv"
    "distinct_document_org_values.csv"
    "distinct_vote_values.csv"
    "distinct_political_parties.csv"
)

MISSING_DISTINCT=0
for file in "${REQUIRED_DISTINCT[@]}"; do
    if [ ! -f "$file" ]; then
        echo "❌ FAIL: Missing required file: $file"
        MISSING_DISTINCT=$((MISSING_DISTINCT + 1))
        VALIDATION_FAILED=1
    fi
done

if [ "$MISSING_DISTINCT" -eq 0 ]; then
    echo "✅ PASS: All required distinct value files present"
fi

echo ""
echo "=================================================="
echo "=== DATA QUALITY SPOT CHECKS                   ==="
echo "=================================================="
echo ""

# Spot check: Verify some files have multiple rows
SAMPLE_CHECK_PASSED=0
SAMPLE_CHECK_FAILED=0

check_file_rows() {
    local file=$1
    local min_rows=$2
    
    if [ -f "$file" ]; then
        local row_count=$(wc -l < "$file")
        local data_rows=$((row_count - 1))  # Exclude header
        
        if [ "$data_rows" -ge "$min_rows" ]; then
            SAMPLE_CHECK_PASSED=$((SAMPLE_CHECK_PASSED + 1))
        else
            echo "⚠️  WARNING: $file has only $data_rows data rows (expected ≥ $min_rows)"
            SAMPLE_CHECK_FAILED=$((SAMPLE_CHECK_FAILED + 1))
            WARNINGS=$((WARNINGS + 1))
        fi
    fi
}

# Check a few key files
check_file_rows "distinct_political_parties.csv" 5
check_file_rows "sample_data_manifest.csv" 50

if [ "$SAMPLE_CHECK_FAILED" -eq 0 ]; then
    echo "✅ PASS: Spot checks passed ($SAMPLE_CHECK_PASSED files checked)"
fi

echo ""
echo "=================================================="
echo "=== VALIDATION SUMMARY                         ==="
echo "=================================================="
echo ""

echo "Files validated: $CHECKED_FILES CSV files"
echo "Warnings: $WARNINGS"
echo "Failures: $VALIDATION_FAILED"
echo ""

if [ "$VALIDATION_FAILED" -eq 0 ] && [ "$WARNINGS" -eq 0 ]; then
    echo "✅✅✅ VALIDATION PASSED - EXCELLENT ✅✅✅"
    echo ""
    echo "All checks passed successfully!"
    echo "Sample data extraction is complete and valid."
    exit 0
elif [ "$VALIDATION_FAILED" -eq 0 ]; then
    echo "✅ VALIDATION PASSED WITH WARNINGS"
    echo ""
    echo "All critical checks passed, but there are $WARNINGS warning(s)."
    echo "Review warnings above for details."
    exit 0
else
    echo "❌❌❌ VALIDATION FAILED ❌❌❌"
    echo ""
    echo "Critical issues found. Review failures above."
    echo "Sample data extraction may be incomplete."
    exit 1
fi
