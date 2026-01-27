#!/bin/bash
# extract-sample-data-fast.sh
# Fast Sample Data Extraction with Timeout Protection
# Citizen Intelligence Agency - Open Source Intelligence Platform
#
# Purpose: Quickly extracts sample data from all views and tables with
#          timeout protection for complex views
#
# Usage:
#   cd service.data.impl/sample-data
#   bash ../src/main/resources/extract-sample-data-fast.sh
#
# Environment Variables:
#   PGHOST - PostgreSQL host (default: localhost)
#   PGPORT - PostgreSQL port (default: 5432)
#   PGDATABASE - Database name (default: cia_dev)
#   PGUSER - Database user (default: eris)
#   PGPASSWORD - Database password

set -e

# Configuration
export PGHOST="${PGHOST:-localhost}"
export PGPORT="${PGPORT:-5432}"
export PGDATABASE="${PGDATABASE:-cia_dev}"
export PGUSER="${PGUSER:-eris}"
TIMEOUT_SECONDS=10

echo "======================================="
echo "Fast Sample Data Extraction"
echo "Started at: $(date)"
echo "======================================="
echo ""
echo "Configuration:"
echo "  Host: $PGHOST:$PGPORT"
echo "  Database: $PGDATABASE"
echo "  User: $PGUSER"
echo "  Timeout: ${TIMEOUT_SECONDS}s per view"
echo ""

# Create log file
LOG_FILE="extraction_fast_log.csv"
echo "object_name,object_type,status,rows_extracted,duration_ms,error_message" > "$LOG_FILE"

# Function to extract data with timeout
extract_with_timeout() {
    local object_name="$1"
    local object_type="$2"
    local sample_size="$3"
    local output_file="$4"
    
    local start_time=$(date +%s%3N)
    local status="SUCCESS"
    local rows=0
    local error_msg=""
    
    # Run query with timeout
    if timeout "${TIMEOUT_SECONDS}s" psql -c "\COPY (SELECT * FROM ${object_name} ORDER BY random() LIMIT ${sample_size}) TO '${output_file}' WITH CSV HEADER" 2>&1 > /dev/null; then
        rows=$(wc -l < "$output_file" 2>/dev/null || echo "0")
        rows=$((rows - 1))  # Subtract header row
        if [ $rows -lt 0 ]; then rows=0; fi
        echo "✓ ${object_type}_${object_name}: $rows rows"
    else
        local exit_code=$?
        if [ $exit_code -eq 124 ]; then
            status="TIMEOUT"
            error_msg="Exceeded ${TIMEOUT_SECONDS}s timeout"
            echo "⏱ TIMEOUT: ${object_type}_${object_name}"
        else
            status="ERROR"
            error_msg="Exit code: $exit_code"
            echo "✗ ERROR: ${object_type}_${object_name}"
        fi
    fi
    
    local end_time=$(date +%s%3N)
    local duration=$((end_time - start_time))
    
    # Log result
    echo "${object_name},${object_type},${status},${rows},${duration},\"${error_msg}\"" >> "$LOG_FILE"
}

# Phase 1: Extract base tables
echo "--- PHASE 1: Extracting Base Tables ---"
echo ""

table_count=0
table_success=0

while IFS= read -r tablename; do
    ((table_count++))
    extract_with_timeout "$tablename" "table" 50 "table_${tablename}_sample.csv"
    status=$(tail -1 "$LOG_FILE" | cut -d, -f3)
    if [ "$status" = "SUCCESS" ]; then
        ((table_success++))
    fi
done < <(psql -t -c "SELECT tablename FROM pg_tables WHERE schemaname = 'public' AND tablename NOT LIKE 'qrtz_%' AND tablename NOT IN ('databasechangelog', 'databasechangeloglock') ORDER BY tablename;")

echo ""
echo "Tables: $table_success successful out of $table_count"
echo ""

# Phase 2: Get view complexity
echo "--- PHASE 2: Analyzing View Complexity ---"
echo ""

# Create temporary file for view complexity
COMPLEXITY_FILE=$(mktemp)

psql -t -c "
WITH RECURSIVE 
all_views AS (
    SELECT viewname AS view_name, 'view' AS view_type
    FROM pg_views 
    WHERE schemaname = 'public'
    UNION ALL
    SELECT matviewname AS view_name, 'materialized_view' AS view_type
    FROM pg_matviews 
    WHERE schemaname = 'public'
),
view_deps AS (
    SELECT DISTINCT
        dependent_view.relname AS view_name,
        source_table.relname AS depends_on
    FROM pg_depend
    JOIN pg_rewrite ON pg_depend.objid = pg_rewrite.oid
    JOIN pg_class AS dependent_view ON pg_rewrite.ev_class = dependent_view.oid
    JOIN pg_class AS source_table ON pg_depend.refobjid = source_table.oid
    JOIN pg_namespace dependent_ns ON dependent_ns.oid = dependent_view.relnamespace
    JOIN pg_namespace source_ns ON source_ns.oid = source_table.relnamespace
    WHERE dependent_view.relkind IN ('v', 'm')
      AND source_table.relkind IN ('v', 'm', 'r')
      AND pg_depend.deptype = 'n'
      AND dependent_ns.nspname = 'public'
      AND source_ns.nspname = 'public'
),
view_only_deps AS (
    SELECT vd.view_name, vd.depends_on
    FROM view_deps vd
    WHERE EXISTS (
        SELECT 1 FROM all_views av WHERE av.view_name = vd.depends_on
    )
),
dependency_depth AS (
    SELECT 
        av.view_name,
        av.view_type,
        0 AS depth
    FROM all_views av
    WHERE NOT EXISTS (
        SELECT 1 FROM view_only_deps vod WHERE vod.view_name = av.view_name
    )
    
    UNION ALL
    
    SELECT 
        vod.view_name,
        av.view_type,
        dd.depth + 1
    FROM view_only_deps vod
    JOIN dependency_depth dd ON vod.depends_on = dd.view_name
    JOIN all_views av ON vod.view_name = av.view_name
)
SELECT 
    view_name,
    view_type,
    MAX(depth) as dependency_level,
    CASE 
        WHEN MAX(depth) <= 1 THEN 50
        WHEN MAX(depth) = 2 THEN 30
        ELSE 10
    END AS sample_size
FROM dependency_depth
GROUP BY view_name, view_type
ORDER BY MAX(depth), view_name;
" > "$COMPLEXITY_FILE"

echo "Complexity analysis complete"
echo ""

# Phase 3: Extract views
echo "--- PHASE 3: Extracting Views (Simple to Complex) ---"
echo ""

view_count=0
view_success=0
view_timeout=0

while IFS='|' read -r view_name view_type dependency_level sample_size; do
    # Trim whitespace
    view_name=$(echo "$view_name" | xargs)
    view_type=$(echo "$view_type" | xargs)
    sample_size=$(echo "$sample_size" | xargs)
    
    if [ -z "$view_name" ]; then
        continue
    fi
    
    ((view_count++))
    extract_with_timeout "$view_name" "$view_type" "$sample_size" "view_${view_name}_sample.csv"
    
    status=$(tail -1 "$LOG_FILE" | cut -d, -f3)
    if [ "$status" = "SUCCESS" ]; then
        ((view_success++))
    elif [ "$status" = "TIMEOUT" ]; then
        ((view_timeout++))
    fi
done < "$COMPLEXITY_FILE"

rm -f "$COMPLEXITY_FILE"

echo ""
echo "Views: $view_success successful, $view_timeout timeout out of $view_count"
echo ""

# Phase 4: Summary
echo "--- PHASE 4: Summary ---"
echo ""

total_objects=$((table_count + view_count))
total_success=$((table_success + view_success))
total_timeout=$view_timeout

echo "Overall Statistics:"
echo "  Total objects:  $total_objects"
echo "  Successful:     $total_success"
echo "  Timeout:        $total_timeout"
echo "  Success rate:   $((total_success * 100 / total_objects))%"
echo ""

echo "======================================="
echo "Extraction Complete"
echo "Finished at: $(date)"
echo "======================================="
echo ""
echo "Generated files:"
echo "  - table_*_sample.csv (base table samples)"
echo "  - view_*_sample.csv (view samples)"
echo "  - $LOG_FILE (extraction status and timing)"
echo ""
