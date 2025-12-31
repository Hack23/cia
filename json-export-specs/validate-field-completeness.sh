#!/bin/bash
###############################################################################
# CIA JSON Export Schema - Field Completeness Validator
#
# Validates that sample CSV files contain all required fields for JSON export
# schemas. Prevents API failures by ensuring data completeness.
#
# Usage:
#   bash validate-field-completeness.sh
#   bash validate-field-completeness.sh --verbose
###############################################################################

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

VERBOSE=false
if [[ "${1:-}" == "--verbose" ]]; then
    VERBOSE=true
fi

# Get script directory
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
CSV_DIR="$SCRIPT_DIR/../service.data.impl/sample-data"
OUTPUT_FILE="$SCRIPT_DIR/FIELD_COMPLETENESS_REPORT.md"

# Schema-to-CSV mappings with required fields
declare -A SCHEMAS
declare -A PRIMARY_CSVS
declare -A REQUIRED_FIELDS

# Politician schema
SCHEMAS[politician]="politician-schema.md"
PRIMARY_CSVS[politician]="view_riksdagen_politician_sample.csv"
REQUIRED_FIELDS[politician]="person_id,first_name,last_name,party,born_year,gender,total_days_served,active,first_assignment_date,last_assignment_date,total_assignments,current_assignments,total_committee_assignments,current_committee_assignments,total_documents,documents_last_year,average_docs_per_year,total_days_served_parliament,total_days_served_committee,total_days_served_government,total_days_served_party"

# Party schema
SCHEMAS[party]="party-schema.md"
PRIMARY_CSVS[party]="view_riksdagen_party_sample.csv"
REQUIRED_FIELDS[party]="party_id,party_name,head_count,total_documents,documents_last_year,registered_date"

# Committee schema
SCHEMAS[committee]="committee-schema.md"
PRIMARY_CSVS[committee]="view_riksdagen_committee_sample.csv"
REQUIRED_FIELDS[committee]="embedded_id_org_code,current_member_size,active,total_documents,total_assignments,first_assignment_date,last_assignment_date"

# Ministry schema
SCHEMAS[ministry]="ministry-schema.md"
PRIMARY_CSVS[ministry]="view_riksdagen_goverment_sample.csv"
REQUIRED_FIELDS[ministry]="name_id,active,current_member_size,total_documents,total_propositions,total_government_bills,first_assignment_date,last_assignment_date"

# Intelligence schema
SCHEMAS[intelligence]="intelligence-schema.md"
PRIMARY_CSVS[intelligence]="table_rule_violation_sample.csv"
REQUIRED_FIELDS[intelligence]="rule_name,reference_id,resource_type,status,name,rule_description,rule_group,detected_date,positive"

# Function to get CSV columns
get_csv_columns() {
    local csv_file="$1"
    if [[ ! -f "$csv_file" ]]; then
        echo ""
        return
    fi
    head -n 1 "$csv_file" | tr ',' '\n' | sort
}

# Function to count rows in CSV
count_csv_rows() {
    local csv_file="$1"
    if [[ ! -f "$csv_file" ]]; then
        echo "0"
        return
    fi
    # Subtract 1 for header
    local count=$(($(wc -l < "$csv_file") - 1))
    echo "$count"
}

# Function to validate schema
validate_schema() {
    local schema_key="$1"
    local schema_name="${SCHEMAS[$schema_key]}"
    local primary_csv="${PRIMARY_CSVS[$schema_key]}"
    local required_fields="${REQUIRED_FIELDS[$schema_key]}"
    
    local csv_path="$CSV_DIR/$primary_csv"
    local missing_fields=()
    local field_count=0
    local missing_count=0
    local completeness=0
    local row_count=0
    local col_count=0
    
    # Count required fields
    IFS=',' read -ra fields <<< "$required_fields"
    field_count=${#fields[@]}
    
    if [[ -f "$csv_path" ]]; then
        # Get CSV columns
        csv_columns=$(get_csv_columns "$csv_path")
        row_count=$(count_csv_rows "$csv_path")
        col_count=$(echo "$csv_columns" | wc -l)
        
        # Check each required field
        for field in "${fields[@]}"; do
            if ! echo "$csv_columns" | grep -q "^${field}$"; then
                missing_fields+=("$field")
                ((missing_count++))
            fi
        done
        
        # Calculate completeness
        if [[ $field_count -gt 0 ]]; then
            completeness=$(( (field_count - missing_count) * 100 / field_count ))
        fi
    else
        missing_count=$field_count
        missing_fields=("${fields[@]}")
    fi
    
    # Store results in arrays
    SCHEMA_NAMES+=("$schema_name")
    CSV_FILES+=("$primary_csv")
    ROW_COUNTS+=("$row_count")
    COL_COUNTS+=("$col_count")
    FIELD_COUNTS+=("$field_count")
    MISSING_COUNTS+=("$missing_count")
    COMPLETENESS+=("$completeness")
    
    # Store missing fields as string
    local missing_str=$(IFS=','; echo "${missing_fields[*]}")
    MISSING_FIELDS_LIST+=("$missing_str")
    
    # Store CSV existence
    if [[ -f "$csv_path" ]]; then
        CSV_EXISTS+=("true")
    else
        CSV_EXISTS+=("false")
    fi
}

# Initialize result arrays
SCHEMA_NAMES=()
CSV_FILES=()
ROW_COUNTS=()
COL_COUNTS=()
FIELD_COUNTS=()
MISSING_COUNTS=()
COMPLETENESS=()
MISSING_FIELDS_LIST=()
CSV_EXISTS=()

# Validate all schemas
echo "================================================================================"
echo " CIA JSON Export Schema - Field Completeness Validation Report"
echo "================================================================================"
echo ""
echo "This validation checks that sample CSV files contain the required fields"
echo "to support JSON export schemas defined in BUSINESS_PRODUCT_DOCUMENT.md"
echo ""

for schema_key in politician party committee ministry intelligence; do
    validate_schema "$schema_key"
done

# Print results
for i in "${!SCHEMA_NAMES[@]}"; do
    if [[ ${MISSING_COUNTS[$i]} -eq 0 ]]; then
        echo -e "${GREEN}✅${NC} Schema: ${SCHEMA_NAMES[$i]}"
    else
        echo -e "${YELLOW}⚠️${NC} Schema: ${SCHEMA_NAMES[$i]}"
    fi
    
    echo "   Primary CSV: ${CSV_FILES[$i]}"
    
    if [[ ${CSV_EXISTS[$i]} == "true" ]]; then
        echo "   Status: Found (${ROW_COUNTS[$i]} rows, ${COL_COUNTS[$i]} columns)"
        echo "   Required Fields: ${FIELD_COUNTS[$i]}"
        echo "   Missing Fields: ${MISSING_COUNTS[$i]}"
        echo "   Completeness: ${COMPLETENESS[$i]}%"
    else
        echo "   Status: ❌ CSV file not found!"
    fi
    
    if [[ $VERBOSE == true ]] && [[ -n "${MISSING_FIELDS_LIST[$i]:-}" ]]; then
        echo ""
        echo "   Missing required fields:"
        IFS=',' read -ra missing <<< "${MISSING_FIELDS_LIST[$i]}"
        for field in "${missing[@]}"; do
            echo "      - $field"
        done
    fi
    echo ""
done

# Calculate summary
total_schemas=${#SCHEMA_NAMES[@]}
complete_schemas=0
total_completeness=0

for i in "${!MISSING_COUNTS[@]}"; do
    if [[ ${MISSING_COUNTS[$i]} -eq 0 ]]; then
        ((complete_schemas++))
    fi
    total_completeness=$((total_completeness + ${COMPLETENESS[$i]}))
done

if [[ $total_schemas -gt 0 ]]; then
    avg_completeness=$((total_completeness / total_schemas))
else
    avg_completeness=0
fi

echo "================================================================================"
echo " Summary"
echo "================================================================================"
echo ""
echo "   Total Schemas Validated: $total_schemas"
echo "   Complete Schemas: $complete_schemas"
echo "   Incomplete Schemas: $((total_schemas - complete_schemas))"
echo "   Average Completeness: ${avg_completeness}%"
echo ""

if [[ $complete_schemas -eq $total_schemas ]]; then
    echo -e "   ${GREEN}✅ All schemas have complete field coverage!${NC}"
    echo ""
else
    echo -e "   ${YELLOW}⚠️  Some schemas have missing fields or CSVs.${NC}"
    echo ""
fi

# Generate markdown report
cat > "$OUTPUT_FILE" << 'REPORT_HEADER'
# Field Completeness Validation Report

REPORT_HEADER

# Add dynamic date
echo "**Generated:** $(date +%Y-%m-%d)" >> "$OUTPUT_FILE"
echo "" >> "$OUTPUT_FILE"

# Add rest of header
cat >> "$OUTPUT_FILE" << 'REPORT_OVERVIEW'
**Purpose:** Validate sample CSV data completeness for JSON export schemas

## Overview

This report validates that sample CSV files contain the required fields to support the 5 JSON export schemas.

### Summary Table

| Schema | Primary CSV | Rows | Required Fields | Missing | Completeness |
|--------|-------------|------|----------------|---------|-------------|
REPORT_OVERVIEW

# Add table rows
for i in "${!SCHEMA_NAMES[@]}"; do
    if [[ ${MISSING_COUNTS[$i]} -eq 0 ]]; then
        status="✅"
    else
        status="⚠️"
    fi
    echo "| $status ${SCHEMA_NAMES[$i]} | \`${CSV_FILES[$i]}\` | ${ROW_COUNTS[$i]} | ${FIELD_COUNTS[$i]} | ${MISSING_COUNTS[$i]} | ${COMPLETENESS[$i]}% |" >> "$OUTPUT_FILE"
done

# Add detailed results
cat >> "$OUTPUT_FILE" << 'REPORT_DETAIL'

## Detailed Results

REPORT_DETAIL

for i in "${!SCHEMA_NAMES[@]}"; do
    cat >> "$OUTPUT_FILE" << DETAIL_START
### ${SCHEMA_NAMES[$i]}

**Primary CSV:** \`${CSV_FILES[$i]}\`

DETAIL_START
    
    if [[ ${CSV_EXISTS[$i]} == "true" ]]; then
        cat >> "$OUTPUT_FILE" << DETAIL_CSV
- **Status:** ✅ Found
- **Rows:** ${ROW_COUNTS[$i]}
- **Columns:** ${COL_COUNTS[$i]}
- **Completeness:** ${COMPLETENESS[$i]}%

DETAIL_CSV
    else
        cat >> "$OUTPUT_FILE" << DETAIL_NOCSV
- **Status:** ❌ NOT FOUND

DETAIL_NOCSV
    fi
    
    if [[ -n "${MISSING_FIELDS_LIST[$i]:-}" ]]; then
        echo "**Missing Required Fields (${MISSING_COUNTS[$i]}):**" >> "$OUTPUT_FILE"
        echo "" >> "$OUTPUT_FILE"
        IFS=',' read -ra missing <<< "${MISSING_FIELDS_LIST[$i]}"
        for field in "${missing[@]}"; do
            echo "- \`$field\`" >> "$OUTPUT_FILE"
        done
        echo "" >> "$OUTPUT_FILE"
    else
        echo "✅ All required fields present in primary CSV!" >> "$OUTPUT_FILE"
        echo "" >> "$OUTPUT_FILE"
    fi
done

echo "📄 Detailed report saved to: $OUTPUT_FILE"
echo ""

# Exit with error if any schema is incomplete
if [[ $complete_schemas -ne $total_schemas ]]; then
    exit 1
fi

exit 0
