#!/bin/bash
# refresh_materialized_views.sh
# Automated refresh script for 5 high-impact materialized views
# Schedule via cron based on refresh frequency for each view

set -e

# Configuration
DB_NAME="${DB_NAME:-cia_dev}"
DB_USER="${DB_USER:-postgres}"
LOG_FILE="${LOG_FILE:-/var/log/cia_mv_refresh.log}"

# Function to log messages
log_message() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - $1" | tee -a "$LOG_FILE"
}

# Function to refresh a materialized view
refresh_view() {
    local view_name=$1
    local description=$2
    
    log_message "Refreshing $view_name ($description)..."
    
    if psql -U "$DB_USER" -d "$DB_NAME" -c "REFRESH MATERIALIZED VIEW CONCURRENTLY $view_name;" 2>&1 | tee -a "$LOG_FILE"; then
        log_message "✓ Successfully refreshed $view_name"
        return 0
    else
        log_message "✗ Failed to refresh $view_name"
        return 1
    fi
}

# Parse command line arguments
VIEW_NAME="${1:-all}"

log_message "=== Starting materialized view refresh ==="
log_message "Target: $VIEW_NAME"

case "$VIEW_NAME" in
    "decision_temporal_trends")
        # Daily refresh: Decision temporal trends
        refresh_view "view_decision_temporal_trends" "Daily at 02:00 UTC"
        ;;
    
    "politician_influence_metrics")
        # Weekly refresh: Politician influence metrics
        refresh_view "view_riksdagen_politician_influence_metrics" "Weekly on Sunday at 03:00 UTC"
        ;;
    
    "politician_behavioral_trends")
        # Monthly refresh: Politician behavioral trends
        refresh_view "view_politician_behavioral_trends" "Monthly on 1st at 04:00 UTC"
        ;;
    
    "party_effectiveness_trends")
        # Quarterly refresh: Party effectiveness trends
        refresh_view "view_party_effectiveness_trends" "Quarterly on 1st at 04:00 UTC"
        ;;
    
    "election_cycle_temporal_trends")
        # Post-election refresh: Election cycle temporal trends
        refresh_view "view_election_cycle_temporal_trends" "Post-election (manual trigger)"
        ;;
    
    "all")
        # Refresh all views (useful for testing or manual full refresh)
        log_message "Refreshing all 5 materialized views..."
        
        success_count=0
        fail_count=0
        
        if refresh_view "view_decision_temporal_trends" "Daily"; then
            ((success_count++))
        else
            ((fail_count++))
        fi
        
        if refresh_view "view_riksdagen_politician_influence_metrics" "Weekly"; then
            ((success_count++))
        else
            ((fail_count++))
        fi
        
        if refresh_view "view_politician_behavioral_trends" "Monthly"; then
            ((success_count++))
        else
            ((fail_count++))
        fi
        
        if refresh_view "view_party_effectiveness_trends" "Quarterly"; then
            ((success_count++))
        else
            ((fail_count++))
        fi
        
        if refresh_view "view_election_cycle_temporal_trends" "Post-election"; then
            ((success_count++))
        else
            ((fail_count++))
        fi
        
        log_message "=== Refresh Summary ==="
        log_message "Successful: $success_count"
        log_message "Failed: $fail_count"
        ;;
    
    *)
        log_message "ERROR: Unknown view name: $VIEW_NAME"
        echo "Usage: $0 [view_name|all]"
        echo ""
        echo "Available views:"
        echo "  decision_temporal_trends          - Daily refresh"
        echo "  politician_influence_metrics      - Weekly refresh"
        echo "  politician_behavioral_trends      - Monthly refresh"
        echo "  party_effectiveness_trends        - Quarterly refresh"
        echo "  election_cycle_temporal_trends    - Post-election refresh"
        echo "  all                               - Refresh all views"
        exit 1
        ;;
esac

log_message "=== Materialized view refresh completed ==="
