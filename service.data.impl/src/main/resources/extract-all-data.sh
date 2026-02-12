#!/bin/bash
# extract-all-data.sh
# Comprehensive Data Extraction for CIA Platform
# Citizen Intelligence Agency - Open Source Intelligence Platform
#
# Purpose: Orchestrates complete data extraction including:
#   - Sample data for all tables/views (200-500 rows)
#   - Complete distribution files for riksdagsmonitor (33 files)
#   - Complete party data (7 files)
#   - Complete minister data (7 files)
#
# Usage:
#   ./extract-all-data.sh [output_directory] [database]
#
# Example:
#   ./extract-all-data.sh /tmp/cia-data cia_dev
#
# Environment Variables:
#   PSQL_HOST     - PostgreSQL host (default: localhost)
#   PSQL_PORT     - PostgreSQL port (default: 5432)
#   PSQL_USER     - PostgreSQL user (default: postgres)
#   PGPASSWORD    - PostgreSQL password (required if not using peer auth)
#
# ===========================================================================

set -euo pipefail

# Configuration
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
OUTPUT_DIR="${1:-${PWD}}"
DATABASE="${2:-cia_dev}"
PSQL_HOST="${PSQL_HOST:-localhost}"
PSQL_PORT="${PSQL_PORT:-5432}"
PSQL_USER="${PSQL_USER:-postgres}"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Functions
print_header() {
    echo ""
    echo -e "${BLUE}========================================${NC}"
    echo -e "${BLUE}$1${NC}"
    echo -e "${BLUE}========================================${NC}"
    echo ""
}

print_success() {
    echo -e "${GREEN}✓${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}⚠${NC} $1"
}

print_error() {
    echo -e "${RED}✗${NC} $1"
}

print_info() {
    echo -e "${BLUE}ℹ${NC} $1"
}

check_prerequisites() {
    print_header "Checking Prerequisites"
    
    # Check psql
    if ! command -v psql &> /dev/null; then
        print_error "psql not found. Please install PostgreSQL client."
        exit 1
    fi
    print_success "psql found: $(psql --version)"
    
    # Check database connection
    if ! psql -h "$PSQL_HOST" -p "$PSQL_PORT" -U "$PSQL_USER" -d "$DATABASE" -c "SELECT 1" &> /dev/null; then
        print_error "Cannot connect to database: $DATABASE on $PSQL_HOST:$PSQL_PORT"
        print_info "Make sure PostgreSQL is running and PGPASSWORD is set if needed"
        exit 1
    fi
    print_success "Database connection successful"
    
    # Create output directory
    mkdir -p "$OUTPUT_DIR"
    print_success "Output directory: $OUTPUT_DIR"
}

extract_sample_data() {
    print_header "Phase 1: Extracting Sample Data + Distribution Files"
    print_info "Running extract-sample-data.sql..."
    print_info "This generates:"
    print_info "  - Sample data for all tables/views (200-500 rows)"
    print_info "  - 33 complete distribution files for riksdagsmonitor"
    print_info "  - Percentile summaries (P1-P99)"
    echo ""
    
    cd "$OUTPUT_DIR"
    psql -h "$PSQL_HOST" -p "$PSQL_PORT" -U "$PSQL_USER" -d "$DATABASE" \
        -f "$SCRIPT_DIR/extract-sample-data.sql" 2>&1 | tee extract-sample-data.log
    local psql_status=${PIPESTATUS[0]}

    if [[ $psql_status -ne 0 ]]; then
        print_error "Sample data extraction failed (psql exit code: $psql_status). See extract-sample-data.log for details."
        return 1
    fi

    if grep -q "^ERROR:" extract-sample-data.log; then
        print_error "Sample data extraction encountered SQL errors. See extract-sample-data.log for details."
        return 1
    fi

    print_success "Sample data extraction completed"
    
    # Count generated files using safe nullglob pattern
    shopt -s nullglob
    local table_files=(table_*.csv)
    local view_files=(view_*.csv)
    local dist_files=(distribution_*.csv)
    local percentile_files=(percentile_*.csv)
    local table_count=${#table_files[@]}
    local view_count=${#view_files[@]}
    local dist_count=${#dist_files[@]}
    local percentile_count=${#percentile_files[@]}
    shopt -u nullglob
    
    print_info "Generated:"
    print_info "  - $table_count table samples"
    print_info "  - $view_count view samples"
    print_info "  - $dist_count distribution files"
    print_info "  - $percentile_count percentile summaries"
}

extract_party_data() {
    print_header "Phase 2: Extracting Complete Party Data"
    print_info "Running extract-party-data.sql..."
    print_info "This generates 7 files:"
    print_info "  - party_master_data.csv"
    print_info "  - party_members_current.csv"
    print_info "  - party_members_historical.csv"
    print_info "  - party_leaders_current.csv"
    print_info "  - party_leaders_historical.csv"
    print_info "  - party_voting_summary.csv"
    print_info "  - party_composition_timeline.csv"
    echo ""
    
    cd "$OUTPUT_DIR"
    psql -h "$PSQL_HOST" -p "$PSQL_PORT" -U "$PSQL_USER" -d "$DATABASE" \
        -f "$SCRIPT_DIR/extract-party-data.sql" 2>&1 | tee extract-party-data.log
    local psql_status=${PIPESTATUS[0]}
    
    # Append to consolidated log
    cat extract-party-data.log >> extract-sample-data.log
    
    if [[ $psql_status -ne 0 ]]; then
        print_error "Party data extraction failed (psql exit code: $psql_status). See extract-party-data.log for details."
        return 1
    fi
    
    # Check for SQL errors in this phase's log only
    if grep -q "^ERROR:" extract-party-data.log; then
        print_error "SQL errors detected in party data extraction (see extract-party-data.log)"
        return 1
    fi
    
    print_success "Party data extraction completed"
    
    # Verify files (safely handle globs)
    shopt -s nullglob
    local party_files=(party_*.csv)
    shopt -u nullglob
    
    if [ ${#party_files[@]} -eq 7 ]; then
        print_success "All 7 party data files generated"
    else
        print_warning "Expected 7 party files, got ${#party_files[@]}"
    fi
}

extract_minister_data() {
    print_header "Phase 3: Extracting Complete Minister Data"
    print_info "Running extract-minister-data.sql..."
    print_info "This generates 7 files:"
    print_info "  - minister_current.csv"
    print_info "  - minister_historical.csv"
    print_info "  - ministry_assignments_current.csv"
    print_info "  - ministry_assignments_historical.csv"
    print_info "  - government_composition.csv"
    print_info "  - government_transitions.csv"
    print_info "  - minister_performance.csv"
    echo ""
    
    cd "$OUTPUT_DIR"
    psql -h "$PSQL_HOST" -p "$PSQL_PORT" -U "$PSQL_USER" -d "$DATABASE" \
        -f "$SCRIPT_DIR/extract-minister-data.sql" 2>&1 | tee extract-minister-data.log
    local psql_status=${PIPESTATUS[0]}
    
    # Append to consolidated log
    cat extract-minister-data.log >> extract-sample-data.log
    
    if [[ $psql_status -ne 0 ]]; then
        print_error "Minister data extraction failed (psql exit code: $psql_status). See extract-minister-data.log for details."
        return 1
    fi
    
    # Check for SQL errors in this phase's log only
    if grep -q "^ERROR:" extract-minister-data.log; then
        print_error "SQL errors detected in minister data extraction (see extract-minister-data.log)"
        return 1
    fi
    
    print_success "Minister data extraction completed"
    
    # Verify files using safe nullglob pattern
    shopt -s nullglob
    local minister_files_array=(minister*.csv government*.csv ministry*.csv)
    local minister_files=${#minister_files_array[@]}
    shopt -u nullglob
    
    if [ "$minister_files" -eq 7 ]; then
        print_success "All 7 minister data files generated"
        else
            print_warning "Expected 7 minister files, got $minister_files"
        fi
    else
        print_error "Minister data extraction failed"
        return 1
    fi
}

generate_summary() {
    print_header "Extraction Summary"
    
    cd "$OUTPUT_DIR"
    
    # Count all CSV files by type (safely handle empty globs)
    shopt -s nullglob
    local all_csvs=(*.csv)
    local table_samples=(table_*.csv)
    local view_samples=(view_*.csv)
    local distributions=(distribution_*.csv)
    local percentiles=(percentile_*.csv)
    local trends=(trend_*.csv)
    local party_files=(party_*.csv)
    local minister_files=(minister*.csv government*.csv ministry*.csv)
    shopt -u nullglob
    
    print_info "Total CSV files generated: ${#all_csvs[@]}"
    echo ""
    print_info "Breakdown:"
    print_info "  📊 Table samples:        ${#table_samples[@]}"
    print_info "  📈 View samples:         ${#view_samples[@]}"
    print_info "  📉 Distribution files:   ${#distributions[@]} (riksdagsmonitor)"
    print_info "  📐 Percentile summaries: ${#percentiles[@]}"
    print_info "  📅 Trend files:          ${#trends[@]}"
    print_info "  🏛️  Party data:           ${#party_files[@]}"
    print_info "  👔 Minister data:        ${#minister_files[@]}"
    echo ""
    
    # Check for riksdagsmonitor required files
    print_info "Riksdagsmonitor integration:"
    local required_files=(
        "distribution_party_performance.csv"
        "distribution_coalition_alignment.csv"
        "distribution_annual_party_members.csv"
        "distribution_annual_party_votes.csv"
        "distribution_committee_productivity.csv"
        "distribution_ministry_effectiveness.csv"
    )
    
    local missing_count=0
    for file in "${required_files[@]}"; do
        if [ ! -f "$file" ]; then
            print_warning "Missing: $file"
            ((missing_count++))
        fi
    done
    
    if [ $missing_count -eq 0 ]; then
        print_success "All sample riksdagsmonitor files present"
    else
        print_warning "$missing_count riksdagsmonitor files missing (check extraction log)"
    fi
    
    echo ""
    print_info "Output directory: $OUTPUT_DIR"
    print_info "Extraction log: $OUTPUT_DIR/extract-sample-data.log"
    print_info "Summary report: $OUTPUT_DIR/extraction_summary_report.csv"
}

# Main execution
main() {
    print_header "CIA Platform - Complete Data Extraction"
    print_info "Database: $DATABASE @ $PSQL_HOST:$PSQL_PORT"
    print_info "User: $PSQL_USER"
    print_info "Output: $OUTPUT_DIR"
    
    check_prerequisites
    
    # Track start time
    local start_time=$(date +%s)
    
    # Track failures
    local exit_code=0
    
    # Run extractions
    if ! extract_sample_data; then
        print_warning "Sample data extraction had issues"
        exit_code=1
    fi
    
    if ! extract_party_data; then
        print_warning "Party data extraction had issues"
        exit_code=1
    fi
    
    if ! extract_minister_data; then
        print_warning "Minister data extraction had issues"
        exit_code=1
    fi
    
    # Generate summary
    generate_summary
    
    # Calculate duration
    local end_time=$(date +%s)
    local duration=$((end_time - start_time))
    local minutes=$((duration / 60))
    local seconds=$((duration % 60))
    
    print_header "Extraction Complete"
    print_success "Total time: ${minutes}m ${seconds}s"
    print_info "All data extracted to: $OUTPUT_DIR"
    echo ""
    
    # Return exit code (0 if all succeeded, 1 if any failed)
    return $exit_code
}

# Run main function and propagate exit code
main "$@"
exit $?
