#!/bin/bash
# ===========================================================================
# CIA Sample Data Extraction - Validation Functions
# ===========================================================================
# Reusable validation functions for sample data quality assurance
# 
# This library provides modular validation functions that can be sourced
# by extract-sample-data.sh or used independently for data quality checks.
#
# Functions included:
#   - validate_temporal_coverage()  - Check year range coverage
#   - validate_party_coverage()     - Check political party representation
#   - validate_percentile_coverage() - Check percentile file completeness
#   - generate_coverage_report()    - Generate validation summary CSV
#
# Usage:
#   source validation-functions.sh
#   validate_temporal_coverage
#   validate_party_coverage
#   validate_percentile_coverage
#
# Environment Variables Required:
#   VALIDATION_FAILED - Set to 0 before calling, incremented on failures
#
# Version: 1.0.0
# Last Updated: January 2026
# ===========================================================================

# ===========================================================================
# Function: validate_temporal_coverage
# ===========================================================================
# Purpose: Validate that sample data covers adequate temporal range
# Parameters: None (reads from extraction_statistics.csv)
# Returns: Sets VALIDATION_FAILED=1 if coverage < 10 years
# Exports: EARLIEST_YEAR, LATEST_YEAR, YEAR_RANGE
# ===========================================================================
validate_temporal_coverage() {
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
}

# ===========================================================================
# Function: validate_party_coverage
# ===========================================================================
# Purpose: Validate that sample data includes all major Swedish political parties
# Parameters: None (reads from distribution_*.csv files)
# Returns: Sets VALIDATION_FAILED=1 if any parties missing
# Exports: MISSING_PARTIES
# ===========================================================================
validate_party_coverage() {
    echo "🏛️  Categorical Coverage Validation (Political Parties):"
    echo "   Checking for all 8 major Swedish parties: S, M, SD, C, V, KD, L, MP..."
    
    # Expected parties
    local EXPECTED_PARTIES="S M SD C V KD L MP"
    MISSING_PARTIES=""
    
    # Ensure that at least one expected distribution file exists before validating
    local HAS_PARTY_DISTRIBUTION_FILES=0
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
}

# ===========================================================================
# Function: validate_percentile_coverage
# ===========================================================================
# Purpose: Validate that percentile distribution files exist and are complete
# Parameters: None (reads from percentile_*.csv files)
# Returns: Sets VALIDATION_FAILED=1 if files missing or incomplete
# Exports: PERCENTILE_FILES
# ===========================================================================
validate_percentile_coverage() {
    echo "📊 Percentile Coverage Validation:"
    echo "   Checking for percentile distribution summaries (P1, P10, P25, P50, P75, P90, P99)..."
    
    PERCENTILE_FILES=$(ls -1 percentile_*.csv 2>/dev/null | wc -l)
    
    if [ "$PERCENTILE_FILES" -gt 0 ]; then
        echo "   ✓ Found $PERCENTILE_FILES percentile distribution summary files"
        
        # Validate percentile columns in first file
        local SAMPLE_FILE=$(ls -1 percentile_*.csv 2>/dev/null | head -1)
        if [ -f "$SAMPLE_FILE" ]; then
            local HEADER=$(head -1 "$SAMPLE_FILE")
            
            # Check for required percentile columns
            local REQUIRED_COLS="p1 p10 p25 median p75 p90 p99"
            local MISSING_COLS=""
            
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
}

# ===========================================================================
# Function: generate_coverage_report
# ===========================================================================
# Purpose: Generate CSV validation coverage report
# Parameters: None (uses exported variables from validation functions)
# Returns: Creates validation_coverage_report.csv
# Requires: EARLIEST_YEAR, LATEST_YEAR, YEAR_RANGE, MISSING_PARTIES, PERCENTILE_FILES
# ===========================================================================
generate_coverage_report() {
    local COVERAGE_REPORT="validation_coverage_report.csv"
    
    echo "📋 Generating Coverage Report:"
    echo "   Creating: $COVERAGE_REPORT"
    
    # Prepare properly escaped status and details for CSV
    local TEMPORAL_STATUS TEMPORAL_DETAILS
    if [ -n "$EARLIEST_YEAR" ] && [ -n "$LATEST_YEAR" ]; then
        TEMPORAL_STATUS="PASS"
        TEMPORAL_DETAILS="$EARLIEST_YEAR-$LATEST_YEAR ($YEAR_RANGE years)"
    else
        TEMPORAL_STATUS="FAIL"
        TEMPORAL_DETAILS="Unable to determine year range"
    fi
    
    local PARTY_STATUS PARTY_DETAILS
    if [ -z "$MISSING_PARTIES" ]; then
        PARTY_STATUS="PASS"
        PARTY_DETAILS="All 8 parties present"
    else
        PARTY_STATUS="WARNING"
        PARTY_DETAILS="Missing parties -$MISSING_PARTIES"
    fi
    
    local PERCENTILE_STATUS PERCENTILE_DETAILS
    if [ "$PERCENTILE_FILES" -gt 0 ]; then
        PERCENTILE_STATUS="PASS"
        PERCENTILE_DETAILS="$PERCENTILE_FILES files generated"
    else
        PERCENTILE_STATUS="FAIL"
        PERCENTILE_DETAILS="No percentile files found"
    fi
    
    # Escape double quotes in CSV values
    local TEMPORAL_STATUS_ESCAPED=${TEMPORAL_STATUS//\"/\"\"}
    local TEMPORAL_DETAILS_ESCAPED=${TEMPORAL_DETAILS//\"/\"\"}
    local PARTY_STATUS_ESCAPED=${PARTY_STATUS//\"/\"\"}
    local PARTY_DETAILS_ESCAPED=${PARTY_DETAILS//\"/\"\"}
    local PERCENTILE_STATUS_ESCAPED=${PERCENTILE_STATUS//\"/\"\"}
    local PERCENTILE_DETAILS_ESCAPED=${PERCENTILE_DETAILS//\"/\"\"}
    
    cat > "$COVERAGE_REPORT" << EOF
validation_type,status,details
temporal_coverage,"$TEMPORAL_STATUS_ESCAPED","$TEMPORAL_DETAILS_ESCAPED"
party_coverage,"$PARTY_STATUS_ESCAPED","$PARTY_DETAILS_ESCAPED"
percentile_coverage,"$PERCENTILE_STATUS_ESCAPED","$PERCENTILE_DETAILS_ESCAPED"
EOF
    
    echo "   ✓ Generated: $COVERAGE_REPORT"
    echo ""
}

# ===========================================================================
# Function: run_all_validations
# ===========================================================================
# Purpose: Run all validation functions in sequence
# Parameters: None
# Returns: Exit code 0 if all pass, 1 if any fail
# ===========================================================================
run_all_validations() {
    echo "=================================================="
    echo "=== ADVANCED VALIDATION (Coverage Metrics)    ==="
    echo "=================================================="
    echo ""
    
    # Run all validation functions
    validate_temporal_coverage
    validate_party_coverage
    validate_percentile_coverage
    
    # Generate coverage report
    generate_coverage_report
    
    # Summary
    echo "=================================================="
    if [ "$VALIDATION_FAILED" -eq 0 ]; then
        echo "✅ Validation Passed - All Checks Successful"
    else
        echo "⚠️  Validation Completed with Warnings"
        echo "    Review warnings above and check validation_coverage_report.csv"
    fi
    echo "=================================================="
    echo ""
    
    return $VALIDATION_FAILED
}

# ===========================================================================
# Export functions for external use
# ===========================================================================
export -f validate_temporal_coverage
export -f validate_party_coverage
export -f validate_percentile_coverage
export -f generate_coverage_report
export -f run_all_validations

echo "✓ Loaded CIA Sample Data Validation Functions (v1.0.0)"
