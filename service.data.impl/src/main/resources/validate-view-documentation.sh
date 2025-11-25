#!/bin/bash
# validate-view-documentation.sh
# Automated validation of view documentation coverage
# Part of the Citizen Intelligence Agency (CIA) platform quality assurance system
# NOTE: This script requires Linux/GNU utilities (date -d) and is designed for CI environments

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/../../../.." && pwd)"

echo "==================================="
echo "View Documentation Validation"
echo "Started: $(date)"
echo "==================================="
echo ""

# File paths
SCHEMA_FILE="$REPO_ROOT/service.data.impl/src/main/resources/full_schema.sql"
DOC_FILE="$REPO_ROOT/DATABASE_VIEW_INTELLIGENCE_CATALOG.md"
OUTPUT_FILE="$REPO_ROOT/DATABASE_VIEW_VALIDATION_REPORT.md"

# Check if required files exist
if [ ! -f "$SCHEMA_FILE" ]; then
    echo "❌ ERROR: Schema file not found: $SCHEMA_FILE"
    exit 1
fi

if [ ! -f "$DOC_FILE" ]; then
    echo "❌ ERROR: Documentation file not found: $DOC_FILE"
    exit 1
fi

echo "📁 Files:"
echo "   Schema: $SCHEMA_FILE"
echo "   Documentation: $DOC_FILE"
echo "   Output: $OUTPUT_FILE"
echo ""

# Create temporary directory for working files
TEMP_DIR=$(mktemp -d)
trap "rm -rf $TEMP_DIR" EXIT

# Extract views from full_schema.sql
echo "🔍 Extracting views from schema..."
grep -E "^CREATE (OR REPLACE )?(MATERIALIZED )?VIEW" "$SCHEMA_FILE" | \
  sed 's/.*VIEW //' | \
  sed 's/ AS.*//' | \
  sed 's/public\.//' | \
  sort | uniq > "$TEMP_DIR/schema_views.txt"

# Extract documented views from catalog
echo "📚 Extracting documented views..."
grep -E "^### view_" "$DOC_FILE" | \
  sed 's/### //' | \
  awk '{print $1}' | \
  sort | uniq > "$TEMP_DIR/documented_views.txt"

# Calculate statistics
TOTAL_VIEWS=$(wc -l < "$TEMP_DIR/schema_views.txt" | tr -d ' ')
DOCUMENTED_VIEWS=$(wc -l < "$TEMP_DIR/documented_views.txt" | tr -d ' ')

# Check for division by zero
if [ "$TOTAL_VIEWS" -eq 0 ]; then
    echo "❌ ERROR: No views found in schema"
    exit 1
fi

COVERAGE=$(awk "BEGIN {printf \"%.2f\", ($DOCUMENTED_VIEWS / $TOTAL_VIEWS) * 100}")

echo ""
echo "📊 Statistics:"
echo "   Total views in schema: $TOTAL_VIEWS"
echo "   Documented views: $DOCUMENTED_VIEWS"
echo "   Coverage: $COVERAGE%"
echo ""

# Find missing views
comm -23 "$TEMP_DIR/schema_views.txt" "$TEMP_DIR/documented_views.txt" > "$TEMP_DIR/missing_views.txt"
MISSING_COUNT=$(wc -l < "$TEMP_DIR/missing_views.txt" | tr -d ' ')

# Find extra views (documented but not in schema)
comm -13 "$TEMP_DIR/schema_views.txt" "$TEMP_DIR/documented_views.txt" > "$TEMP_DIR/extra_views.txt"
EXTRA_COUNT=$(wc -l < "$TEMP_DIR/extra_views.txt" | tr -d ' ')

# Determine status using awk for portability (instead of bc)
if [ "$MISSING_COUNT" -eq 0 ] && [ "$EXTRA_COUNT" -eq 0 ]; then
    STATUS="✅ Complete"
    SEVERITY="🟢 **OPTIMAL**"
elif [ "$COVERAGE" == "100.00" ] && [ "$EXTRA_COUNT" -gt 0 ]; then
    STATUS="⚠️ Extra views documented"
    SEVERITY="🟡 **ATTENTION**"
elif awk "BEGIN {exit !($COVERAGE >= 90)}"; then
    STATUS="✅ Excellent"
    SEVERITY="🟢 **GOOD**"
elif awk "BEGIN {exit !($COVERAGE >= 70)}"; then
    STATUS="⚠️ Good"
    SEVERITY="🟡 **ACCEPTABLE**"
else
    STATUS="❌ Critical Gap"
    SEVERITY="🔴 **CRITICAL**"
fi

echo "Status: $STATUS"
echo ""

# Generate report
cat > "$OUTPUT_FILE" <<EOF
# DATABASE_VIEW_INTELLIGENCE_CATALOG.md Validation Report

## Validation Metadata

**Date:** $(date +%Y-%m-%d)  
**Validator:** Automated Script (validate-view-documentation.sh)  
**Source Schema:** service.data.impl/src/main/resources/full_schema.sql  
**Documentation File:** DATABASE_VIEW_INTELLIGENCE_CATALOG.md  
**Validation Method:** Automated comparison script  
**Repository:** Hack23/cia

---

## Executive Summary

This validation report confirms the current status of view documentation coverage.

### Current Status

| Metric | Value | Status |
|--------|-------|--------|
| **Total views in database** | $TOTAL_VIEWS | ✓ Confirmed |
| **Total views documented** | $DOCUMENTED_VIEWS | $STATUS |
| **Documentation coverage** | $COVERAGE% | $([ "$COVERAGE" == "100.00" ] && echo "✅ Complete" || echo "⚠️ In Progress") |
| **Views missing from documentation** | $MISSING_COUNT | $([ "$MISSING_COUNT" -eq 0 ] && echo "✅ None" || echo "❌ Action Required") |
| **Views documented but not in DB** | $EXTRA_COUNT | $([ "$EXTRA_COUNT" -eq 0 ] && echo "✅ None" || echo "⚠️ Review Needed") |

### Severity Assessment

$SEVERITY: Documentation provides **$COVERAGE% coverage** for $TOTAL_VIEWS database views.

EOF

# Create backup of existing report before generating new one
if [ -f "$OUTPUT_FILE" ]; then
    cp "$OUTPUT_FILE" "$OUTPUT_FILE.bak"
fi

# Helper function to extract numeric value from markdown table and validate
extract_numeric() {
    local line="$1"
    local value
    value=$(echo "$line" | awk -F'|' '{gsub(/^[ \t]+|[ \t]+$/, "", $(NF-1)); print $(NF-1)}')
    # Remove percent sign if present
    value="${value%\%}"
    # Remove any status text (e.g., "✓ Confirmed")
    value=$(echo "$value" | awk '{print $1}')
    # Validate numeric (integer or float)
    if [[ "$value" =~ ^[0-9]+([.][0-9]+)?$ ]]; then
        echo "$value"
    else
        echo ""
    fi
}

# Add comparison to previous validation if significant progress was made
# Extract previous metrics from backup report if it exists
if [ -f "$OUTPUT_FILE.bak" ]; then
    PREV_COVERAGE_LINE=$(grep "Documentation coverage.*|" "$OUTPUT_FILE.bak" | head -1)
    PREV_TOTAL_LINE=$(grep "Total views in database.*|" "$OUTPUT_FILE.bak" | head -1)
    PREV_DOCUMENTED_LINE=$(grep "Total views documented.*|" "$OUTPUT_FILE.bak" | head -1)
    PREV_MISSING_LINE=$(grep "Views missing from documentation.*|" "$OUTPUT_FILE.bak" | head -1)
    
    PREV_COVERAGE=$(extract_numeric "$PREV_COVERAGE_LINE")
    PREV_TOTAL=$(extract_numeric "$PREV_TOTAL_LINE")
    PREV_DOCUMENTED=$(extract_numeric "$PREV_DOCUMENTED_LINE")
    PREV_MISSING=$(extract_numeric "$PREV_MISSING_LINE")
    
    # Fallback to baseline if extraction failed
    [ -z "$PREV_COVERAGE" ] && PREV_COVERAGE="10.98"
    [ -z "$PREV_TOTAL" ] && PREV_TOTAL="82"
    [ -z "$PREV_DOCUMENTED" ] && PREV_DOCUMENTED="9"
    [ -z "$PREV_MISSING" ] && PREV_MISSING="73"
else
    # Fallback to known baseline from 2025-11-21 validation
    PREV_COVERAGE="10.98"
    PREV_TOTAL="82"
    PREV_DOCUMENTED="9"
    PREV_MISSING="73"
fi

# Show progress if there's been improvement (using awk for portability)
if awk "BEGIN {exit !($COVERAGE > $PREV_COVERAGE)}"; then
    cat >> "$OUTPUT_FILE" <<EOF

### Progress Since Previous Validation (2025-11-21)

| Metric | Previous (2025-11-21) | Current ($(date +%Y-%m-%d)) | Improvement |
|--------|----------------------|---------------------|-------------|
| **Total views in database** | ${PREV_TOTAL:-0} | $TOTAL_VIEWS | $([ ${TOTAL_VIEWS:-0} -gt ${PREV_TOTAL:-0} ] && echo "+$((${TOTAL_VIEWS:-0} - ${PREV_TOTAL:-0}))" || echo "✓") |
| **Total views documented** | ${PREV_DOCUMENTED:-0} | $DOCUMENTED_VIEWS | +$((${DOCUMENTED_VIEWS:-0} - ${PREV_DOCUMENTED:-0})) views |
| **Documentation coverage** | ${PREV_COVERAGE:-0}% | $COVERAGE% | +$(awk "BEGIN {printf \"%.2f\", ${COVERAGE:-0} - ${PREV_COVERAGE:-0}}")% |
| **Views missing from documentation** | ${PREV_MISSING:-0} | $MISSING_COUNT | -$((${PREV_MISSING:-0} - ${MISSING_COUNT:-0})) views |

EOF
fi

# Add missing views section
if [ "$MISSING_COUNT" -gt 0 ]; then
    cat >> "$OUTPUT_FILE" <<EOF

---

## Missing Views

The following $MISSING_COUNT views are in the schema but missing from documentation:

EOF
    
    # Categorize missing views
    APP_VIEWS=$(grep "view_application_action_event" "$TEMP_DIR/missing_views.txt" || true)
    VOTE_VIEWS=$(grep "view_riksdagen_vote_data_ballot" "$TEMP_DIR/missing_views.txt" || true)
    OTHER_VIEWS=$(grep -v "view_application_action_event\|view_riksdagen_vote_data_ballot" "$TEMP_DIR/missing_views.txt" || true)
    
    if [ ! -z "$APP_VIEWS" ]; then
        echo "### Application Action Event Views" >> "$OUTPUT_FILE"
        echo "" >> "$OUTPUT_FILE"
        echo "$APP_VIEWS" | while read view; do
            echo "- \`$view\` ⭐⭐ LOW - User behavior analytics" >> "$OUTPUT_FILE"
        done
        echo "" >> "$OUTPUT_FILE"
    fi
    
    if [ ! -z "$VOTE_VIEWS" ]; then
        echo "### Vote Data Summary Views" >> "$OUTPUT_FILE"
        echo "" >> "$OUTPUT_FILE"
        echo "$VOTE_VIEWS" | while read view; do
            echo "- \`$view\` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis" >> "$OUTPUT_FILE"
        done
        echo "" >> "$OUTPUT_FILE"
    fi
    
    if [ ! -z "$OTHER_VIEWS" ]; then
        echo "### Other Views" >> "$OUTPUT_FILE"
        echo "" >> "$OUTPUT_FILE"
        echo "$OTHER_VIEWS" | while read view; do
            echo "- \`$view\`" >> "$OUTPUT_FILE"
        done
        echo "" >> "$OUTPUT_FILE"
    fi
else
    cat >> "$OUTPUT_FILE" <<EOF

---

## Missing Views

✅ **All views are documented!** The DATABASE_VIEW_INTELLIGENCE_CATALOG.md now provides comprehensive coverage for all $TOTAL_VIEWS database views.

EOF
fi

# Add extra views section if any
if [ "$EXTRA_COUNT" -gt 0 ]; then
    cat >> "$OUTPUT_FILE" <<EOF

---

## Views Documented But Not In Schema

⚠️ The following $EXTRA_COUNT views are documented but do not exist in the current schema:

EOF
    cat "$TEMP_DIR/extra_views.txt" | while read view; do
        echo "- \`$view\`" >> "$OUTPUT_FILE"
    done
    echo "" >> "$OUTPUT_FILE"
    echo "**Action Required:** Review these views and remove from documentation if they have been deprecated." >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
fi

# Add next steps section
cat >> "$OUTPUT_FILE" <<EOF

---

## Next Steps

EOF

if [ "$MISSING_COUNT" -gt 0 ]; then
    cat >> "$OUTPUT_FILE" <<EOF
### Immediate Actions

1. **Document Missing Views**: Add documentation for $MISSING_COUNT missing views
2. **Prioritize High-Value Views**: Focus on vote data summary views (high intelligence value)
3. **Complete Low-Priority Views**: Document application event tracking views
4. **Re-run Validation**: Execute this script again to verify completion

### Documentation Standards

Each view should include:
- ✓ Purpose statement
- ✓ Key columns table
- ✓ At least 2 example queries
- ✓ Intelligence value rating
- ✓ Performance characteristics
- ✓ Dependencies

EOF
else
    cat >> "$OUTPUT_FILE" <<EOF
✅ **No action required.** Documentation is complete and up-to-date.

### Maintenance Tasks

1. **Monthly Validation**: This script runs automatically via GitHub Actions
2. **Schema Changes**: Update documentation when new views are added
3. **Quality Review**: Periodically review examples and performance metrics
4. **Cross-References**: Maintain links to related intelligence frameworks

EOF
fi

# Add validation methodology
cat >> "$OUTPUT_FILE" <<EOF

---

## Validation Methodology

### Automated Process

This validation is performed by \`validate-view-documentation.sh\`:

1. **Extract Schema Views**: Parse full_schema.sql for CREATE VIEW statements
2. **Extract Documented Views**: Parse DATABASE_VIEW_INTELLIGENCE_CATALOG.md for view headers
3. **Compare Sets**: Identify missing and extra views using set operations
4. **Calculate Coverage**: Compute percentage of documented views
5. **Generate Report**: Create this markdown report with findings

### Commands Executed

\`\`\`bash
# Extract views from schema
grep -E "^CREATE (OR REPLACE )?(MATERIALIZED )?VIEW" full_schema.sql | \\
  sed 's/.*VIEW //' | sed 's/ AS.*//' | sed 's/public\\.//' | sort | uniq

# Extract documented views
grep -E "^### view_" DATABASE_VIEW_INTELLIGENCE_CATALOG.md | \\
  sed 's/### //' | awk '{print \$1}' | sort | uniq

# Compare and calculate coverage
comm -23 schema_views.txt documented_views.txt > missing_views.txt
\`\`\`

### Validation Schedule

- **Automated**: Monthly via GitHub Actions (1st of each month at 02:00 UTC)
- **Manual**: Can be triggered via workflow_dispatch
- **On Changes**: Runs automatically when schema or documentation changes

---

## Changelog

| Date | Coverage | Missing Views | Status |
|------|----------|---------------|--------|
| 2025-11-21 | 10.98% | 73 | Initial validation |
| $(date +%Y-%m-%d) | $COVERAGE% | $MISSING_COUNT | $([ "$MISSING_COUNT" -eq 0 ] && echo "✅ Complete" || echo "⚠️ In Progress") |

---

**Report Status**: GENERATED  
**Generated By**: validate-view-documentation.sh  
**Next Validation**: $(date -d "+1 month" +%Y-%m-01 2>/dev/null || echo "Next month") 02:00 UTC
EOF

echo "✅ Report generated: $OUTPUT_FILE"
echo ""
echo "📊 Summary:"
echo "   Coverage: $COVERAGE%"
echo "   Missing: $MISSING_COUNT views"
echo "   Extra: $EXTRA_COUNT views"
echo ""

# Cleanup is handled by trap EXIT that removes $TEMP_DIR

# Exit with appropriate code
if [ "$MISSING_COUNT" -eq 0 ] && [ "$EXTRA_COUNT" -eq 0 ]; then
    echo "✅ VALIDATION PASSED: 100% documentation coverage achieved"
    exit 0
elif [ "$MISSING_COUNT" -gt 0 ]; then
    echo "❌ VALIDATION FAILED: Documentation coverage is $COVERAGE% (target: 100%)"
    echo "   Missing views: $MISSING_COUNT"
    exit 1
else
    echo "⚠️ VALIDATION WARNING: Extra views documented ($EXTRA_COUNT)"
    exit 0
fi
