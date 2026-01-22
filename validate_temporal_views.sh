#!/bin/bash
# ============================================================================
# TEMPORAL VIEWS PERFORMANCE VALIDATION SCRIPT
# ============================================================================
# Purpose: Validate performance of all 27 temporal analysis views
# Usage: ./validate_temporal_views.sh
# ============================================================================

DB_NAME="cia_test"
DB_USER="postgres"
REPORT_FILE="temporal_views_validation_$(date +%Y%m%d_%H%M%S).txt"

echo "============================================================================"
echo "TEMPORAL ANALYSIS VIEWS PERFORMANCE VALIDATION"
echo "============================================================================"
echo "Database: $DB_NAME"
echo "Report: $REPORT_FILE"
echo "Started: $(date)"
echo "============================================================================"
echo ""

# Performance targets (in milliseconds)
DAILY_TARGET=250
WEEKLY_TARGET=400
MONTHLY_TARGET=800
ANNUAL_TARGET=1500

# Initialize report
cat > $REPORT_FILE << EOF
TEMPORAL ANALYSIS VIEWS PERFORMANCE VALIDATION
Generated: $(date)
Database: $DB_NAME

============================================================================
PERFORMANCE TARGETS
============================================================================
Daily Views: < ${DAILY_TARGET}ms
Weekly Views: < ${WEEKLY_TARGET}ms
Monthly Views: < ${MONTHLY_TARGET}ms
Annual Views: < ${ANNUAL_TARGET}ms

============================================================================
TEST RESULTS
============================================================================

EOF

# Function to test a view
test_view() {
    local view_name=$1
    local granularity=$2
    local target=$3
    
    echo "Testing: $view_name ($granularity)"
    
    # Run query and measure time
    local result=$(sudo -u $DB_USER psql -d $DB_NAME -t -A -c "\timing on" -c "SELECT COUNT(*) FROM $view_name;" 2>&1)
    
    # Extract count and time
    local count=$(echo "$result" | grep -E "^[0-9]+$" | head -1)
    local time_str=$(echo "$result" | grep "Time:" | grep -oP '\d+\.\d+')
    
    # Determine pass/fail
    local status="PASS"
    if (( $(echo "$time_str > $target" | bc -l) )); then
        status="FAIL"
    fi
    
    # Log result
    printf "%-60s | %10s | %8s ms | %6s ms | %s\n" \
        "$view_name" "$count rows" "$time_str" "$target" "$status" | tee -a $REPORT_FILE
}

# Test all views by granularity
echo "Daily Views (Target: < ${DAILY_TARGET}ms)"
echo "------------------------------------------------------------------------"
test_view "view_application_action_event_page_daily_summary" "daily" $DAILY_TARGET
test_view "view_application_action_event_page_element_daily_summary" "daily" $DAILY_TARGET
test_view "view_application_action_event_page_modes_daily_summary" "daily" $DAILY_TARGET
test_view "view_riksdagen_document_type_daily_summary" "daily" $DAILY_TARGET
test_view "view_riksdagen_org_document_daily_summary" "daily" $DAILY_TARGET
test_view "view_riksdagen_party_document_daily_summary" "daily" $DAILY_TARGET
test_view "view_riksdagen_politician_document_daily_summary" "daily" $DAILY_TARGET
test_view "view_riksdagen_vote_data_ballot_party_summary_daily" "daily" $DAILY_TARGET
test_view "view_riksdagen_vote_data_ballot_politician_summary_daily" "daily" $DAILY_TARGET
test_view "view_riksdagen_vote_data_ballot_summary_daily" "daily" $DAILY_TARGET
echo ""

echo "Weekly Views (Target: < ${WEEKLY_TARGET}ms)"
echo "------------------------------------------------------------------------"
test_view "view_application_action_event_page_element_weekly_summary" "weekly" $WEEKLY_TARGET
test_view "view_application_action_event_page_modes_weekly_summary" "weekly" $WEEKLY_TARGET
test_view "view_application_action_event_page_weekly_summary" "weekly" $WEEKLY_TARGET
test_view "view_riksdagen_vote_data_ballot_party_summary_weekly" "weekly" $WEEKLY_TARGET
test_view "view_riksdagen_vote_data_ballot_politician_summary_weekly" "weekly" $WEEKLY_TARGET
test_view "view_riksdagen_vote_data_ballot_summary_weekly" "weekly" $WEEKLY_TARGET
echo ""

echo "Monthly Views (Target: < ${MONTHLY_TARGET}ms)"
echo "------------------------------------------------------------------------"
test_view "view_riksdagen_vote_data_ballot_party_summary_monthly" "monthly" $MONTHLY_TARGET
test_view "view_riksdagen_vote_data_ballot_politician_summary_monthly" "monthly" $MONTHLY_TARGET
test_view "view_riksdagen_vote_data_ballot_summary_monthly" "monthly" $MONTHLY_TARGET
echo ""

echo "Annual Views (Target: < ${ANNUAL_TARGET}ms)"
echo "------------------------------------------------------------------------"
test_view "view_application_action_event_page_annual_summary" "annual" $ANNUAL_TARGET
test_view "view_application_action_event_page_element_annual_summary" "annual" $ANNUAL_TARGET
test_view "view_application_action_event_page_modes_annual_summary" "annual" $ANNUAL_TARGET
test_view "view_riksdagen_party_ballot_support_annual_summary" "annual" $ANNUAL_TARGET
test_view "view_riksdagen_party_coalation_against_annual_summary" "annual" $ANNUAL_TARGET
test_view "view_riksdagen_vote_data_ballot_party_summary_annual" "annual" $ANNUAL_TARGET
test_view "view_riksdagen_vote_data_ballot_politician_summary_annual" "annual" $ANNUAL_TARGET
test_view "view_riksdagen_vote_data_ballot_summary_annual" "annual" $ANNUAL_TARGET
echo ""

echo "============================================================================"
echo "VALIDATION COMPLETE"
echo "============================================================================"
echo "Full report saved to: $REPORT_FILE"
echo ""

# Summary statistics
echo "Summary Statistics:"
echo "------------------------------------------------------------------------"
sudo -u $DB_USER psql -d $DB_NAME << EOF
SELECT 
    COUNT(*) AS total_views,
    SUM(CASE WHEN pg_total_relation_size(viewname::regclass) > 0 THEN 1 ELSE 0 END) AS views_with_data
FROM pg_views 
WHERE schemaname = 'public' 
    AND viewname LIKE '%_summary_%'
UNION ALL
SELECT 
    COUNT(*) AS total_matviews,
    COUNT(*) AS matviews_populated
FROM pg_matviews 
WHERE schemaname = 'public' 
    AND matviewname LIKE '%_summary_%';
EOF

echo ""
echo "Completed: $(date)"
