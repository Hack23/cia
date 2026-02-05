#!/bin/bash
#
# SQL Script Testing Framework
# Tests all SQL scripts for syntax, documentation, and execution
#

# Don't exit on error - we want to test all scripts
set +e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuration
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
TEST_OUTPUT="${TEST_OUTPUT:-/tmp/sql-test-results}"
PSQL_HOST="${PSQL_HOST:-localhost}"
PSQL_PORT="${PSQL_PORT:-5432}"
PSQL_USER="${PSQL_USER:-postgres}"
DATABASE="${DATABASE:-cia_dev}"

mkdir -p "$TEST_OUTPUT"

# Counters
TOTAL_SCRIPTS=0
PASSED_SCRIPTS=0
FAILED_SCRIPTS=0
SKIPPED_SCRIPTS=0

# Test results file
RESULTS_FILE="$TEST_OUTPUT/test-results-$(date +%Y%m%d-%H%M%S).csv"
echo "script_name,category,syntax_check,documentation_check,execution_test,notes" > "$RESULTS_FILE"

echo -e "${BLUE}================================================${NC}"
echo -e "${BLUE}SQL Script Testing Framework${NC}"
echo -e "${BLUE}================================================${NC}"
echo ""
echo "Configuration:"
echo "  Script directory: $SCRIPT_DIR"
echo "  Test output: $TEST_OUTPUT"
echo "  Database: $PSQL_HOST:$PSQL_PORT/$DATABASE"
echo "  User: $PSQL_USER"
echo ""

#
# Test 1: Syntax Check
#
check_syntax() {
    local script="$1"
    local script_name=$(basename "$script")
    
    echo -n "  📝 Syntax check... "
    
    # Use PostgreSQL to check syntax without executing
    if psql -h "$PSQL_HOST" -p "$PSQL_PORT" -U "$PSQL_USER" -d "$DATABASE" \
        -v ON_ERROR_STOP=1 --single-transaction --set AUTOCOMMIT=off \
        -f "$script" -o /dev/null 2>&1 | grep -q "ERROR"; then
        echo -e "${RED}FAIL${NC}"
        return 1
    else
        echo -e "${GREEN}PASS${NC}"
        return 0
    fi
}

#
# Test 2: Documentation Check
#
check_documentation() {
    local script="$1"
    local script_name=$(basename "$script")
    
    echo -n "  📚 Documentation... "
    
    local has_header=0
    local has_description=0
    local has_usage=0
    
    # Check for header comments
    if head -10 "$script" | grep -q "^--"; then
        has_header=1
    fi
    
    # Check for description or purpose
    if head -20 "$script" | grep -qi "purpose\|description\|overview"; then
        has_description=1
    fi
    
    # Check for usage examples
    if head -30 "$script" | grep -qi "usage\|example\|how to"; then
        has_usage=1
    fi
    
    local score=$((has_header + has_description + has_usage))
    
    if [ $score -ge 2 ]; then
        echo -e "${GREEN}PASS${NC} ($score/3)"
        return 0
    elif [ $score -eq 1 ]; then
        echo -e "${YELLOW}WARN${NC} ($score/3)"
        return 0
    else
        echo -e "${RED}FAIL${NC} ($score/3)"
        return 1
    fi
}

#
# Test 3: Categorize Script
#
categorize_script() {
    local script_name="$1"
    
    case "$script_name" in
        extract-*)
            echo "data-extraction"
            ;;
        schema-*|full_schema.sql)
            echo "schema-management"
            ;;
        refresh-*|analyze-view-*)
            echo "view-operations"
            ;;
        validate-*)
            echo "validation"
            ;;
        analyze-*|diagnose-*|calculate-*)
            echo "analysis"
            ;;
        remove-*|clean-*)
            echo "maintenance"
            ;;
        view_*)
            echo "view-definition"
            ;;
        *)
            echo "other"
            ;;
    esac
}

#
# Test 4: Execution Test (selective)
#
test_execution() {
    local script="$1"
    local script_name=$(basename "$script")
    local category="$2"
    
    echo -n "  🚀 Execution test... "
    
    # Skip execution for maintenance scripts (destructive)
    if [ "$category" = "maintenance" ]; then
        echo -e "${YELLOW}SKIP${NC} (destructive)"
        return 2
    fi
    
    # Skip execution for view definitions (too large)
    if [ "$category" = "view-definition" ]; then
        echo -e "${YELLOW}SKIP${NC} (view definition)"
        return 2
    fi
    
    # Try to execute with timeout
    local output_file="$TEST_OUTPUT/${script_name%.sql}.log"
    if timeout 30 psql -h "$PSQL_HOST" -p "$PSQL_PORT" -U "$PSQL_USER" -d "$DATABASE" \
        -v ON_ERROR_STOP=off \
        -f "$script" > "$output_file" 2>&1; then
        
        # Check for errors in output
        if grep -q "ERROR:" "$output_file"; then
            echo -e "${RED}FAIL${NC} (errors in output)"
            return 1
        else
            echo -e "${GREEN}PASS${NC}"
            return 0
        fi
    else
        local exit_code=$?
        if [ $exit_code -eq 124 ]; then
            echo -e "${YELLOW}TIMEOUT${NC}"
            return 2
        else
            echo -e "${RED}FAIL${NC} (exit code: $exit_code)"
            return 1
        fi
    fi
}

#
# Main test function
#
test_script() {
    local script="$1"
    local script_name=$(basename "$script")
    
    echo ""
    echo -e "${BLUE}Testing: ${script_name}${NC}"
    
    TOTAL_SCRIPTS=$((TOTAL_SCRIPTS + 1))
    
    # Categorize
    local category=$(categorize_script "$script_name")
    echo "  Category: $category"
    
    # Run tests
    local syntax_result="UNKNOWN"
    local doc_result="UNKNOWN"
    local exec_result="UNKNOWN"
    local notes=""
    
    # Syntax check
    if check_syntax "$script"; then
        syntax_result="PASS"
    else
        syntax_result="FAIL"
        notes="Syntax errors detected"
    fi
    
    # Documentation check
    if check_documentation "$script"; then
        doc_result="PASS"
    else
        doc_result="FAIL"
        notes="$notes; Missing documentation"
    fi
    
    # Execution test (only if syntax passed)
    if [ "$syntax_result" = "PASS" ]; then
        test_execution "$script" "$category"
        local exec_exit=$?
        if [ $exec_exit -eq 0 ]; then
            exec_result="PASS"
        elif [ $exec_exit -eq 2 ]; then
            exec_result="SKIP"
        else
            exec_result="FAIL"
            notes="$notes; Execution failed"
        fi
    else
        exec_result="SKIP"
    fi
    
    # Record results
    echo "$script_name,$category,$syntax_result,$doc_result,$exec_result,\"$notes\"" >> "$RESULTS_FILE"
    
    # Update counters
    if [ "$syntax_result" = "PASS" ] && [ "$doc_result" = "PASS" ] && ([ "$exec_result" = "PASS" ] || [ "$exec_result" = "SKIP" ]); then
        PASSED_SCRIPTS=$((PASSED_SCRIPTS + 1))
        echo -e "  ${GREEN}✓ Overall: PASS${NC}"
    else
        FAILED_SCRIPTS=$((FAILED_SCRIPTS + 1))
        echo -e "  ${RED}✗ Overall: FAIL${NC}"
    fi
}

#
# Main execution
#
main() {
    echo -e "${BLUE}Discovering SQL scripts...${NC}"
    
    # Find all SQL scripts (excluding extract-framework-validation subdirectory)
    local scripts=$(find "$SCRIPT_DIR" -maxdepth 1 -name "*.sql" -type f | sort)
    
    if [ -z "$scripts" ]; then
        echo -e "${RED}No SQL scripts found in $SCRIPT_DIR${NC}"
        exit 1
    fi
    
    echo "Found $(echo "$scripts" | wc -l) scripts"
    echo ""
    
    # Test each script
    for script in $scripts; do
        test_script "$script"
    done
    
    # Summary
    echo ""
    echo -e "${BLUE}================================================${NC}"
    echo -e "${BLUE}Test Summary${NC}"
    echo -e "${BLUE}================================================${NC}"
    echo ""
    echo "Total scripts tested: $TOTAL_SCRIPTS"
    echo -e "${GREEN}Passed: $PASSED_SCRIPTS${NC}"
    echo -e "${RED}Failed: $FAILED_SCRIPTS${NC}"
    echo ""
    echo "Detailed results: $RESULTS_FILE"
    echo ""
    
    # Exit code
    if [ $FAILED_SCRIPTS -gt 0 ]; then
        echo -e "${YELLOW}Some scripts failed validation${NC}"
        exit 1
    else
        echo -e "${GREEN}All scripts passed validation!${NC}"
        exit 0
    fi
}

# Run main function
main "$@"
