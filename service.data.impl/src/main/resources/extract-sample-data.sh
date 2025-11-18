#!/bin/bash
# extract-sample-data.sh
# Shell wrapper for sample data extraction
# 
# Usage:
#   ./extract-sample-data.sh [output_directory] [database_name]
#
# Examples:
#   ./extract-sample-data.sh                    # Extract to current directory from cia_dev
#   ./extract-sample-data.sh /tmp/samples       # Extract to /tmp/samples from cia_dev
#   ./extract-sample-data.sh /tmp/samples cia_prod  # Extract from cia_prod

set -e

# Configuration
OUTPUT_DIR="${1:-.}"
DATABASE="${2:-cia_dev}"
PSQL_USER="${PSQL_USER:-postgres}"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SQL_SCRIPT="$SCRIPT_DIR/extract-sample-data.sql"

echo "=================================================="
echo "CIA Sample Data Extraction Wrapper"
echo "=================================================="
echo ""
echo "Configuration:"
echo "  Database: $DATABASE"
echo "  Output directory: $OUTPUT_DIR"
echo "  PostgreSQL user: $PSQL_USER"
echo ""

# Create output directory if it doesn't exist
mkdir -p "$OUTPUT_DIR"

# Change to output directory
cd "$OUTPUT_DIR"

echo "Extracting sample data..."
echo ""

# Run the SQL script
psql -U "$PSQL_USER" -d "$DATABASE" -f "$SQL_SCRIPT"

echo ""
echo "=================================================="
echo "Extraction Complete!"
echo "=================================================="
echo ""
echo "Files generated in: $OUTPUT_DIR"
echo ""
ls -lh table_*_sample.csv view_*_sample.csv *.csv 2>/dev/null | head -20
echo ""
echo "Total CSV files: $(ls -1 *.csv 2>/dev/null | wc -l)"
echo ""
