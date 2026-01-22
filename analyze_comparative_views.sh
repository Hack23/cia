#!/bin/bash
#
# Comprehensive Performance Analysis of 26 Comparative Analysis Framework Views
# CIA Platform - PostgreSQL 16.11
#

set -e

DB_NAME="cia_dev"
DB_USER="postgres"
OUTPUT_DIR="/home/runner/work/cia/cia/performance_analysis"
REPORT_FILE="/home/runner/work/cia/cia/COMPARATIVE_ANALYSIS_PERFORMANCE_REPORT.md"

# Create output directory
mkdir -p "$OUTPUT_DIR"

echo "==================================================================="
echo "CIA Platform - Comparative Analysis Framework Performance Analysis"
echo "==================================================================="
echo ""
echo "Database: $DB_NAME"
echo "PostgreSQL Version: $(sudo -u $DB_USER psql -d $DB_NAME -t -c 'SELECT version();' | head -1)"
echo "Analysis Start: $(date)"
echo ""

# Define the 26 views to analyze
PARTY_VIEWS=(
    "view_riksdagen_party"
    "view_riksdagen_party_summary"
    "view_riksdagen_party_member"
    "view_riksdagen_party_ballot_support_annual_summary"
    "view_riksdagen_party_coalation_against_annual_summary"
    "view_riksdagen_coalition_alignment_matrix"
    "view_party_performance_metrics"
    "view_party_effectiveness_trends"
)

POLITICIAN_VIEWS=(
    "view_riksdagen_politician"
    "view_riksdagen_politician_ballot_summary"
    "view_riksdagen_politician_experience_summary"
    "view_politician_behavioral_trends"
    "view_politician_risk_summary"
    "view_riksdagen_politician_influence_metrics"
    "view_riksdagen_politician_career_trajectory"
)

COMMITTEE_VIEWS=(
    "view_riksdagen_committee"
    "view_riksdagen_committee_roles"
    "view_committee_productivity"
    "view_committee_productivity_matrix"
    "view_riksdagen_committee_decisions"
    "view_riksdagen_committee_ballot_decision_summary"
)

MINISTRY_VIEWS=(
    "view_riksdagen_goverment_roles"
    "view_ministry_effectiveness_trends"
    "view_ministry_productivity_matrix"
    "view_ministry_decision_impact"
)

ALL_VIEWS=("${PARTY_VIEWS[@]}" "${POLITICIAN_VIEWS[@]}" "${COMMITTEE_VIEWS[@]}" "${MINISTRY_VIEWS[@]}")

echo "Total views to analyze: ${#ALL_VIEWS[@]}"
echo ""

# Function to analyze a single view
analyze_view() {
    local view_name=$1
    local category=$2
    local output_file="$OUTPUT_DIR/${view_name}_analysis.txt"
    
    echo "Analyzing: $view_name ($category)"
    
    # Create analysis file
    {
        echo "========================================"
        echo "VIEW: $view_name"
        echo "CATEGORY: $category"
        echo "ANALYSIS DATE: $(date)"
        echo "========================================"
        echo ""
        
        # Check if view exists
        echo "--- VIEW EXISTENCE CHECK ---"
        sudo -u $DB_USER psql -d $DB_NAME -c "
            SELECT 
                CASE 
                    WHEN EXISTS (SELECT 1 FROM pg_views WHERE viewname = '$view_name')
                    THEN 'EXISTS (regular view)'
                    WHEN EXISTS (SELECT 1 FROM pg_matviews WHERE matviewname = '$view_name')
                    THEN 'EXISTS (materialized view)'
                    ELSE 'DOES NOT EXIST'
                END as status;
        " 2>&1
        echo ""
        
        # Get view definition
        echo "--- VIEW DEFINITION ---"
        sudo -u $DB_USER psql -d $DB_NAME -c "
            SELECT pg_get_viewdef('$view_name'::regclass, true) as definition;
        " 2>&1 || echo "Could not retrieve definition"
        echo ""
        
        # Get column information
        echo "--- COLUMN INFORMATION ---"
        sudo -u $DB_USER psql -d $DB_NAME -c "
            SELECT 
                ordinal_position,
                column_name,
                data_type,
                character_maximum_length
            FROM information_schema.columns
            WHERE table_name = '$view_name'
            ORDER BY ordinal_position;
        " 2>&1
        echo ""
        
        # Get row count estimate
        echo "--- ROW COUNT ---"
        sudo -u $DB_USER psql -d $DB_NAME -c "
            SELECT COUNT(*) as row_count FROM $view_name;
        " 2>&1
        echo ""
        
        # Run EXPLAIN ANALYZE
        echo "--- EXPLAIN ANALYZE (LIMIT 100) ---"
        sudo -u $DB_USER psql -d $DB_NAME -c "
            EXPLAIN (ANALYZE, VERBOSE, BUFFERS, FORMAT TEXT) 
            SELECT * FROM $view_name LIMIT 100;
        " 2>&1
        echo ""
        
        # Check dependencies
        echo "--- VIEW DEPENDENCIES ---"
        sudo -u $DB_USER psql -d $DB_NAME -c "
            WITH RECURSIVE view_deps AS (
                SELECT 
                    v.oid::regclass::text AS view_name,
                    d.refobjid::regclass::text AS depends_on,
                    1 as level
                FROM pg_rewrite r
                JOIN pg_depend d ON d.objid = r.oid
                JOIN pg_class v ON v.oid = r.ev_class
                WHERE v.relname = '$view_name'
                  AND d.deptype = 'n'
                  AND d.refobjid <> v.oid
                
                UNION
                
                SELECT 
                    vd.depends_on,
                    d.refobjid::regclass::text,
                    vd.level + 1
                FROM view_deps vd
                JOIN pg_rewrite r ON r.ev_class = vd.depends_on::regclass
                JOIN pg_depend d ON d.objid = r.oid
                WHERE d.deptype = 'n'
                  AND d.refobjid::regclass::text <> vd.depends_on
                  AND vd.level < 5
            )
            SELECT DISTINCT view_name, depends_on, level
            FROM view_deps
            ORDER BY level, depends_on;
        " 2>&1
        echo ""
        
    } > "$output_file"
    
    echo "  -> Analysis saved to: $output_file"
}

# Analyze all views
echo "==================================================================="
echo "PARTY VIEWS ANALYSIS"
echo "==================================================================="
for view in "${PARTY_VIEWS[@]}"; do
    analyze_view "$view" "Party"
done

echo ""
echo "==================================================================="
echo "POLITICIAN VIEWS ANALYSIS"
echo "==================================================================="
for view in "${POLITICIAN_VIEWS[@]}"; do
    analyze_view "$view" "Politician"
done

echo ""
echo "==================================================================="
echo "COMMITTEE VIEWS ANALYSIS"
echo "==================================================================="
for view in "${COMMITTEE_VIEWS[@]}"; do
    analyze_view "$view" "Committee"
done

echo ""
echo "==================================================================="
echo "MINISTRY VIEWS ANALYSIS"
echo "==================================================================="
for view in "${MINISTRY_VIEWS[@]}"; do
    analyze_view "$view" "Ministry"
done

echo ""
echo "==================================================================="
echo "ANALYZING INDEXES"
echo "==================================================================="

# Get index information
sudo -u $DB_USER psql -d $DB_NAME -c "
    SELECT 
        schemaname,
        tablename,
        indexname,
        indexdef
    FROM pg_indexes
    WHERE schemaname = 'public'
      AND (tablename LIKE '%party%' 
           OR tablename LIKE '%politician%' 
           OR tablename LIKE '%committee%' 
           OR tablename LIKE '%ministry%'
           OR tablename LIKE '%person%'
           OR tablename LIKE '%vote%'
           OR tablename LIKE '%ballot%'
           OR tablename LIKE '%document%')
    ORDER BY tablename, indexname;
" > "$OUTPUT_DIR/current_indexes.txt" 2>&1

echo "Index analysis saved to: $OUTPUT_DIR/current_indexes.txt"

echo ""
echo "==================================================================="
echo "ANALYZING FOREIGN KEYS"
echo "==================================================================="

sudo -u $DB_USER psql -d $DB_NAME -c "
    SELECT
        tc.table_schema, 
        tc.table_name, 
        kcu.column_name,
        ccu.table_name AS foreign_table_name,
        ccu.column_name AS foreign_column_name,
        tc.constraint_name
    FROM information_schema.table_constraints AS tc 
    JOIN information_schema.key_column_usage AS kcu
      ON tc.constraint_name = kcu.constraint_name
      AND tc.table_schema = kcu.table_schema
    JOIN information_schema.constraint_column_usage AS ccu
      ON ccu.constraint_name = tc.constraint_name
      AND ccu.table_schema = tc.table_schema
    WHERE tc.constraint_type = 'FOREIGN KEY' 
      AND tc.table_schema = 'public'
    ORDER BY tc.table_name, kcu.column_name;
" > "$OUTPUT_DIR/foreign_keys.txt" 2>&1

echo "Foreign key analysis saved to: $OUTPUT_DIR/foreign_keys.txt"

echo ""
echo "==================================================================="
echo "CHECKING PG_STAT_STATEMENTS"
echo "==================================================================="

sudo -u $DB_USER psql -d $DB_NAME -c "
    SELECT 
        calls,
        mean_exec_time,
        max_exec_time,
        LEFT(query, 100) as query_snippet
    FROM pg_stat_statements
    WHERE query LIKE '%view_%'
    ORDER BY mean_exec_time DESC
    LIMIT 20;
" > "$OUTPUT_DIR/pg_stat_statements.txt" 2>&1 || echo "pg_stat_statements data not available yet"

echo "pg_stat_statements analysis saved to: $OUTPUT_DIR/pg_stat_statements.txt"

echo ""
echo "==================================================================="
echo "CHECKING TABLE STATISTICS"
echo "==================================================================="

sudo -u $DB_USER psql -d $DB_NAME -c "
    SELECT 
        schemaname,
        relname,
        n_live_tup,
        n_dead_tup,
        n_mod_since_analyze,
        last_vacuum,
        last_autovacuum,
        last_analyze,
        last_autoanalyze,
        seq_scan,
        seq_tup_read,
        idx_scan,
        idx_tup_fetch
    FROM pg_stat_user_tables
    WHERE schemaname = 'public'
    ORDER BY n_live_tup DESC;
" > "$OUTPUT_DIR/table_statistics.txt" 2>&1

echo "Table statistics saved to: $OUTPUT_DIR/table_statistics.txt"

echo ""
echo "==================================================================="
echo "ANALYSIS COMPLETE"
echo "==================================================================="
echo ""
echo "Results directory: $OUTPUT_DIR"
echo "Analysis End: $(date)"
echo ""
echo "Next step: Generate comprehensive performance report"
