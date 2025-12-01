#!/bin/bash

set -e

DB_NAME="${DB_NAME:-cia_dev}"
DB_USER="${DB_USER:-eris}"
DB_HOST="${DB_HOST:-localhost}"
DB_PORT="${DB_PORT:-5432}"

OUTPUT_FILE="coalition-view-analysis-$(date +%Y%m%d-%H%M%S).txt"

echo "=================================================="
echo "Running Coalition Alignment Matrix Analysis"
echo "=================================================="
echo "Database: $DB_NAME"
echo "Output: $OUTPUT_FILE"
echo ""

# Run the analysis
psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d "$DB_NAME" \
    -f analyze-coalition-view.sql \
    > "$OUTPUT_FILE" 2>&1

echo ""
echo "Analysis complete! Results saved to: $OUTPUT_FILE"
echo ""
echo "Key metrics to look for:"
echo "  - Shared blocks hit vs read (cache effectiveness)"
echo "  - Temp blocks read/written (sort/hash spills)"
echo "  - Execution time breakdown by CTE"
echo "  - Nested Loop vs Hash Join costs"
echo ""
