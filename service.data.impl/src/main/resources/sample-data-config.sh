#!/bin/bash
# ===========================================================================
# CIA Sample Data Extraction - Configuration File
# ===========================================================================
# Centralized configuration settings for sample data extraction and validation
#
# This file contains all configurable parameters used by:
#   - extract-sample-data.sql
#   - extract-sample-data.sh
#   - extract-percentile-summaries.sql
#   - validation-functions.sh
#
# Usage:
#   source sample-data-config.sh
#
# Version: 1.0.0
# Last Updated: January 2026
# ===========================================================================

# ===========================================================================
# SAMPLE SIZE CONFIGURATION
# ===========================================================================
# Default sample size for standard views
export DEFAULT_SAMPLE_SIZE=200

# Increased sample size for temporal trend views requiring better coverage
export TREND_SAMPLE_SIZE=500

# Sample sizes for specific view types
export DAILY_SAMPLE_SIZE=200
export WEEKLY_SAMPLE_SIZE=200
export MONTHLY_SAMPLE_SIZE=200
export ANNUAL_SAMPLE_SIZE=200

# ===========================================================================
# TEMPORAL DATA CONFIGURATION
# ===========================================================================
# Expected temporal range for Swedish political data
export MIN_YEAR=2002
export MAX_YEAR=2026

# Minimum year range required for validation
export MIN_YEAR_RANGE=10

# Date range for temporal sampling
export TEMPORAL_START_DATE="${MIN_YEAR}-01-01"
export TEMPORAL_END_DATE="${MAX_YEAR}-12-31"

# ===========================================================================
# CATEGORICAL DATA CONFIGURATION
# ===========================================================================
# Expected Swedish political parties (8 major parties)
# S    - Social Democrats (Socialdemokraterna)
# M    - Moderates (Moderaterna)
# SD   - Sweden Democrats (Sverigedemokraterna)
# C    - Centre Party (Centerpartiet)
# V    - Left Party (Vänsterpartiet)
# KD   - Christian Democrats (Kristdemokraterna)
# L    - Liberals (Liberalerna)
# MP   - Green Party (Miljöpartiet)
export EXPECTED_PARTIES="S M SD C V KD L MP"
export PARTY_COUNT=8

# ===========================================================================
# PERCENTILE CONFIGURATION
# ===========================================================================
# Target percentiles for distribution analysis (P1, P10, P25, P50, P75, P90, P99)
export PERCENTILE_TARGETS="0.01 0.10 0.25 0.50 0.75 0.90 0.99"
export PERCENTILE_LABELS="P1 P10 P25 P50 P75 P90 P99"

# Minimum number of percentile distribution files expected
export MIN_PERCENTILE_FILES=20

# ===========================================================================
# DATABASE CONFIGURATION
# ===========================================================================
# Default database connection settings (can be overridden by environment)
export DB_HOST="${DB_HOST:-localhost}"
export DB_PORT="${DB_PORT:-5432}"
export DB_NAME="${DB_NAME:-cia_dev}"
export DB_USER="${DB_USER:-eris}"
export DB_SCHEMA="${DB_SCHEMA:-public}"

# ===========================================================================
# OUTPUT CONFIGURATION
# ===========================================================================
# Output file naming patterns
export SAMPLE_FILE_PATTERN="*_sample.csv"
export PERCENTILE_FILE_PATTERN="percentile_*.csv"
export DISTRIBUTION_FILE_PATTERN="distribution_*.csv"

# Validation report filename
export VALIDATION_REPORT="validation_coverage_report.csv"

# Statistics file for temporal validation
export STATISTICS_FILE="extraction_statistics.csv"

# ===========================================================================
# VALIDATION CONFIGURATION
# ===========================================================================
# Enable/disable specific validation checks
export VALIDATE_TEMPORAL=true
export VALIDATE_PARTIES=true
export VALIDATE_PERCENTILES=true

# Validation failure behavior
export FAIL_ON_WARNINGS=false

# ===========================================================================
# PERFORMANCE CONFIGURATION
# ===========================================================================
# PostgreSQL query timeout (seconds)
export QUERY_TIMEOUT=300

# Maximum rows to process for distribution summaries
export MAX_DISTRIBUTION_ROWS=1000000

# ===========================================================================
# FEATURE FLAGS
# ===========================================================================
# Enable/disable optional features
export ENABLE_PERCENTILE_SUMMARIES=true
export ENABLE_DISTRIBUTION_ANALYSIS=true
export ENABLE_DETAILED_LOGGING=true

# ===========================================================================
# LOGGING CONFIGURATION
# ===========================================================================
# Log levels: DEBUG, INFO, WARN, ERROR
export LOG_LEVEL="${LOG_LEVEL:-INFO}"

# Enable colored output (requires terminal with color support)
export ENABLE_COLOR_OUTPUT=true

# ===========================================================================
# HELPER FUNCTIONS
# ===========================================================================

# Function: log_info
# Purpose: Log informational messages
log_info() {
    if [ "$ENABLE_COLOR_OUTPUT" = true ]; then
        echo -e "\033[0;32m[INFO]\033[0m $1"
    else
        echo "[INFO] $1"
    fi
}

# Function: log_warn
# Purpose: Log warning messages
log_warn() {
    if [ "$ENABLE_COLOR_OUTPUT" = true ]; then
        echo -e "\033[0;33m[WARN]\033[0m $1"
    else
        echo "[WARN] $1"
    fi
}

# Function: log_error
# Purpose: Log error messages
log_error() {
    if [ "$ENABLE_COLOR_OUTPUT" = true ]; then
        echo -e "\033[0;31m[ERROR]\033[0m $1" >&2
    else
        echo "[ERROR] $1" >&2
    fi
}

# Function: log_debug
# Purpose: Log debug messages (only if LOG_LEVEL=DEBUG)
log_debug() {
    if [ "$LOG_LEVEL" = "DEBUG" ]; then
        if [ "$ENABLE_COLOR_OUTPUT" = true ]; then
            echo -e "\033[0;36m[DEBUG]\033[0m $1"
        else
            echo "[DEBUG] $1"
        fi
    fi
}

# ===========================================================================
# CONFIGURATION VALIDATION
# ===========================================================================
validate_config() {
    local errors=0
    
    # Validate year range
    if [ "$MAX_YEAR" -le "$MIN_YEAR" ]; then
        log_error "MAX_YEAR ($MAX_YEAR) must be greater than MIN_YEAR ($MIN_YEAR)"
        errors=$((errors + 1))
    fi
    
    # Validate sample sizes
    if [ "$DEFAULT_SAMPLE_SIZE" -lt 1 ]; then
        log_error "DEFAULT_SAMPLE_SIZE must be >= 1"
        errors=$((errors + 1))
    fi
    
    if [ "$TREND_SAMPLE_SIZE" -lt 1 ]; then
        log_error "TREND_SAMPLE_SIZE must be >= 1"
        errors=$((errors + 1))
    fi
    
    # Validate party count
    local actual_party_count=$(echo "$EXPECTED_PARTIES" | wc -w)
    if [ "$actual_party_count" -ne "$PARTY_COUNT" ]; then
        log_warn "PARTY_COUNT ($PARTY_COUNT) doesn't match actual parties ($actual_party_count)"
    fi
    
    return $errors
}

# ===========================================================================
# AUTO-VALIDATION ON SOURCE
# ===========================================================================
# Automatically validate configuration when sourced
if [ "${BASH_SOURCE[0]}" = "${0}" ]; then
    # Script is being executed directly
    echo "CIA Sample Data Extraction - Configuration File"
    echo "Version: 1.0.0"
    echo ""
    validate_config
    if [ $? -eq 0 ]; then
        log_info "Configuration validation passed"
    else
        log_error "Configuration validation failed"
        exit 1
    fi
else
    # Script is being sourced
    log_debug "Loaded sample-data-config.sh"
fi

# Export all functions for use in other scripts
export -f log_info
export -f log_warn
export -f log_error
export -f log_debug
export -f validate_config
