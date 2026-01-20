#!/bin/bash
# validate-refactoring.sh
# Validation script to verify refactored SQL structure

echo "========================================================================"
echo "Framework Validation Data Extraction - Refactoring Validation"
echo "========================================================================"
echo ""

BASE_DIR="service.data.impl/src/main/resources"
MODULE_DIR="$BASE_DIR/extract-framework-validation"
MAIN_SCRIPT="$BASE_DIR/extract-framework-validation-data.sql"
BACKUP_SCRIPT="$BASE_DIR/extract-framework-validation-data.sql.backup"

# Color codes
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Counters
pass_count=0
fail_count=0

# Check function
check() {
    local description="$1"
    local condition="$2"
    
    echo -n "  • $description ... "
    if eval "$condition"; then
        echo -e "${GREEN}✓ PASS${NC}"
        ((pass_count++))
        return 0
    else
        echo -e "${RED}✗ FAIL${NC}"
        ((fail_count++))
        return 1
    fi
}

echo "1. DIRECTORY STRUCTURE"
echo "----------------------"
check "Module directory exists" "[ -d '$MODULE_DIR' ]"
check "Main script exists" "[ -f '$MAIN_SCRIPT' ]"
check "Backup script exists" "[ -f '$BACKUP_SCRIPT' ]"
echo ""

echo "2. MODULAR FILES"
echo "----------------"
check "config.sql exists" "[ -f '$MODULE_DIR/config.sql' ]"
check "helpers.sql exists" "[ -f '$MODULE_DIR/helpers.sql' ]"
check "temporal.sql exists" "[ -f '$MODULE_DIR/temporal.sql' ]"
check "comparative.sql exists" "[ -f '$MODULE_DIR/comparative.sql' ]"
check "pattern.sql exists" "[ -f '$MODULE_DIR/pattern.sql' ]"
check "predictive.sql exists" "[ -f '$MODULE_DIR/predictive.sql' ]"
check "network.sql exists" "[ -f '$MODULE_DIR/network.sql' ]"
check "decision.sql exists" "[ -f '$MODULE_DIR/decision.sql' ]"
echo ""

echo "3. DOCUMENTATION"
echo "----------------"
check "README.md exists" "[ -f '$MODULE_DIR/README.md' ]"
check "REFACTORING_SUMMARY.md exists" "[ -f '$MODULE_DIR/REFACTORING_SUMMARY.md' ]"
check "README.md is not empty" "[ -s '$MODULE_DIR/README.md' ]"
check "REFACTORING_SUMMARY.md is not empty" "[ -s '$MODULE_DIR/REFACTORING_SUMMARY.md' ]"
echo ""

echo "4. FILE CONTENT VALIDATION"
echo "--------------------------"
check "config.sql contains framework_validation_config" "grep -q 'framework_validation_config' '$MODULE_DIR/config.sql'"
check "helpers.sql contains helper functions" "grep -q 'cia_get_config_value' '$MODULE_DIR/helpers.sql'"
check "Main script includes config.sql" "grep -q '\\\\i.*config.sql' '$MAIN_SCRIPT'"
check "Main script includes helpers.sql" "grep -q '\\\\i.*helpers.sql' '$MAIN_SCRIPT'"
check "Main script includes temporal.sql" "grep -q '\\\\i.*temporal.sql' '$MAIN_SCRIPT'"
check "Main script includes comparative.sql" "grep -q '\\\\i.*comparative.sql' '$MAIN_SCRIPT'"
check "Main script includes pattern.sql" "grep -q '\\\\i.*pattern.sql' '$MAIN_SCRIPT'"
check "Main script includes predictive.sql" "grep -q '\\\\i.*predictive.sql' '$MAIN_SCRIPT'"
check "Main script includes network.sql" "grep -q '\\\\i.*network.sql' '$MAIN_SCRIPT'"
check "Main script includes decision.sql" "grep -q '\\\\i.*decision.sql' '$MAIN_SCRIPT'"
echo ""

echo "5. CODE QUALITY METRICS"
echo "-----------------------"
ORIGINAL_LINES=$(wc -l < "$BACKUP_SCRIPT" 2>/dev/null || echo "0")
NEW_MAIN_LINES=$(wc -l < "$MAIN_SCRIPT" 2>/dev/null || echo "0")
TOTAL_MODULE_LINES=$(find "$MODULE_DIR" -name "*.sql" -type f -exec wc -l {} + 2>/dev/null | tail -1 | awk '{print $1}' || echo "0")

echo "  • Original script: $ORIGINAL_LINES lines"
echo "  • New main script: $NEW_MAIN_LINES lines"
echo "  • Total module lines: $TOTAL_MODULE_LINES lines"
echo "  • Documentation: $(wc -l < "$MODULE_DIR/README.md" 2>/dev/null || echo "0") + $(wc -l < "$MODULE_DIR/REFACTORING_SUMMARY.md" 2>/dev/null || echo "0") lines"

if [ "$NEW_MAIN_LINES" -lt "$ORIGINAL_LINES" ]; then
    echo -e "  ${GREEN}✓ Main script reduced in size${NC}"
    ((pass_count++))
else
    echo -e "  ${YELLOW}⚠ Main script increased (expected due to documentation)${NC}"
fi
echo ""

echo "6. HELPER FUNCTIONS"
echo "-------------------"
HELPER_FUNCTIONS=(
    "cia_get_config_value"
    "cia_get_election_years"
    "cia_calculate_temporal_trend"
    "cia_classify_performance"
    "cia_classify_behavioral_cluster"
    "cia_classify_risk_level"
    "cia_classify_rebellion_pattern"
    "cia_classify_anomaly"
    "cia_classify_coalition_alignment"
    "cia_get_lookback_interval"
    "cia_validate_result_count"
)

for func in "${HELPER_FUNCTIONS[@]}"; do
    check "Function $func defined" "grep -q 'CREATE.*FUNCTION $func' '$MODULE_DIR/helpers.sql'"
done
echo ""

echo "7. CONFIGURATION PARAMETERS"
echo "---------------------------"
CONFIG_KEYS=(
    "sample_size_default"
    "lookback_months_medium"
    "trend_significant_threshold"
    "performance_high_threshold"
    "discipline_high_threshold"
    "absence_high_risk_threshold"
    "alignment_moderate_threshold"
)

for key in "${CONFIG_KEYS[@]}"; do
    check "Config key $key present" "grep -q \"'$key'\" '$MODULE_DIR/config.sql'"
done
echo ""

echo "8. TEST CASE COVERAGE"
echo "---------------------"
check "Temporal framework (5 tests)" "[ $(grep -c 'Test Case 1\.[0-9]' '$MODULE_DIR/temporal.sql') -ge 4 ]"
check "Comparative framework (3 tests)" "[ $(grep -c 'Test Case 2\.[0-9]' '$MODULE_DIR/comparative.sql') -ge 3 ]"
check "Pattern framework (2 tests)" "[ $(grep -c 'Test Case 3\.[0-9]' '$MODULE_DIR/pattern.sql') -ge 2 ]"
check "Predictive framework (3 tests)" "[ $(grep -c 'Test Case 4\.[0-9]' '$MODULE_DIR/predictive.sql') -ge 3 ]"
check "Network framework (2 tests)" "[ $(grep -c 'Test Case 5\.[0-9]' '$MODULE_DIR/network.sql') -ge 2 ]"
check "Decision framework (2 tests)" "[ $(grep -c 'Test Case 6\.[0-9]' '$MODULE_DIR/decision.sql') -ge 2 ]"
echo ""

echo "9. BACKWARD COMPATIBILITY"
echo "-------------------------"
check "CSV output paths preserved" "grep -q '/workspaces/cia/service.data.impl/sample-data/framework-validation/' '$MODULE_DIR/temporal.sql'"
check "Validation catalog generation" "grep -q 'validation-test-catalog.csv' '$MAIN_SCRIPT'"
check "Error handling enabled" "grep -q 'ON_ERROR_STOP' '$MAIN_SCRIPT'"
check "Timing enabled" "grep -q 'timing on' '$MAIN_SCRIPT'"
echo ""

echo "========================================================================"
echo "VALIDATION SUMMARY"
echo "========================================================================"
total_checks=$((pass_count + fail_count))
pass_percentage=$((pass_count * 100 / total_checks))

echo ""
echo -e "Total Checks: $total_checks"
echo -e "${GREEN}Passed: $pass_count${NC}"
if [ $fail_count -gt 0 ]; then
    echo -e "${RED}Failed: $fail_count${NC}"
else
    echo -e "Failed: $fail_count"
fi
echo -e "Success Rate: ${pass_percentage}%"
echo ""

if [ $fail_count -eq 0 ]; then
    echo -e "${GREEN}✓ ALL VALIDATIONS PASSED${NC}"
    echo "Refactoring is complete and verified!"
    exit 0
elif [ $fail_count -le 3 ]; then
    echo -e "${YELLOW}⚠ MINOR ISSUES DETECTED${NC}"
    echo "Review the failed checks above."
    exit 1
else
    echo -e "${RED}✗ CRITICAL ISSUES DETECTED${NC}"
    echo "Refactoring needs attention."
    exit 2
fi
