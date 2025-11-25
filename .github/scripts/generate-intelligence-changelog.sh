#!/bin/bash
# generate-intelligence-changelog.sh
# Automated generation of intelligence changelog entries
#
# This script analyzes git changes to detect:
# - New database views in full_schema.sql
# - Modified database views
# - Changes to risk rules documentation
# - Updates to intelligence frameworks
#
# Usage: ./generate-intelligence-changelog.sh [previous_commit] [current_commit]
#   If no commits specified, compares HEAD with HEAD~1

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuration
SCHEMA_FILE="service.data.impl/src/main/resources/full_schema.sql"
RISK_RULES_FILE="RISK_RULES_INTOP_OSINT.md"
DATA_ANALYSIS_FILE="DATA_ANALYSIS_INTOP_OSINT.md"
VIEW_CATALOG_FILE="DATABASE_VIEW_INTELLIGENCE_CATALOG.md"

# Parse arguments
PREV_COMMIT="${1:-HEAD~1}"
CURR_COMMIT="${2:-HEAD}"

echo -e "${BLUE}================================================${NC}"
echo -e "${BLUE}Intelligence Changelog Generator${NC}"
echo -e "${BLUE}================================================${NC}"
echo ""
echo -e "Analyzing changes from ${YELLOW}${PREV_COMMIT}${NC} to ${YELLOW}${CURR_COMMIT}${NC}"
echo ""

# Function to extract view names from SQL
extract_view_names() {
    grep -oP "CREATE\s+(MATERIALIZED\s+)?VIEW\s+\K[a-z_]+" || true
}

# Function to detect new views
detect_new_views() {
    echo -e "${GREEN}=== New Database Views ===${NC}"
    
    if git diff --name-only "$PREV_COMMIT" "$CURR_COMMIT" | grep -q "$SCHEMA_FILE"; then
        NEW_VIEWS=$(git diff "$PREV_COMMIT":"$SCHEMA_FILE" "$CURR_COMMIT":"$SCHEMA_FILE" 2>/dev/null | \
                    grep "^+CREATE.*VIEW" | \
                    extract_view_names)
        
        if [ -n "$NEW_VIEWS" ]; then
            echo "$NEW_VIEWS" | while read -r view; do
                echo -e "  ${GREEN}+${NC} $view"
            done
            
            echo ""
            echo "Suggested changelog entry:"
            echo ""
            echo "### Added Views ($(echo "$NEW_VIEWS" | wc -l))"
            echo ""
            echo "$NEW_VIEWS" | while read -r view; do
                echo "#### $view"
                echo "**Purpose**: [TODO: Add purpose description]"
                echo ""
                echo "**Schema**: [TODO: Add schema description]"
                echo ""
                echo "**Intelligence Product**: [TODO: Link to product]"
                echo "**Analysis Framework**: [TODO: Link to framework]"
                echo "**Intelligence Value**: ⭐⭐⭐⭐ HIGH"
                echo ""
            done
        else
            echo -e "  ${YELLOW}No new views detected${NC}"
        fi
    else
        echo -e "  ${YELLOW}No changes to schema file${NC}"
    fi
    echo ""
}

# Function to detect modified views
detect_modified_views() {
    echo -e "${YELLOW}=== Modified Database Views ===${NC}"
    
    if git diff --name-only "$PREV_COMMIT" "$CURR_COMMIT" | grep -q "$SCHEMA_FILE"; then
        # This is more complex - look for changes in CREATE VIEW blocks
        MODIFIED=$(git diff "$PREV_COMMIT":"$SCHEMA_FILE" "$CURR_COMMIT":"$SCHEMA_FILE" 2>/dev/null | \
                   grep -B2 "^[+-].*SELECT\|^[+-].*FROM\|^[+-].*WHERE" | \
                   grep "CREATE.*VIEW" | \
                   extract_view_names | \
                   sort -u)
        
        if [ -n "$MODIFIED" ]; then
            echo "$MODIFIED" | while read -r view; do
                echo -e "  ${YELLOW}~${NC} $view"
            done
            
            echo ""
            echo "Suggested changelog entry:"
            echo ""
            echo "### Modified Views ($(echo "$MODIFIED" | wc -l))"
            echo ""
            echo "$MODIFIED" | while read -r view; do
                echo "#### $view"
                echo "**Change**: [TODO: Describe what changed]"
                echo "**Reason**: [TODO: Why this change was made]"
                echo "**Impact**: [TODO: Impact on intelligence products]"
                echo ""
            done
        else
            echo -e "  ${YELLOW}No modified views detected${NC}"
        fi
    else
        echo -e "  ${YELLOW}No changes to schema file${NC}"
    fi
    echo ""
}

# Function to detect new risk rules
detect_risk_rule_changes() {
    echo -e "${RED}=== Risk Rule Changes ===${NC}"
    
    if git diff --name-only "$PREV_COMMIT" "$CURR_COMMIT" | grep -q "$RISK_RULES_FILE"; then
        # Look for new rule sections (## followed by rule ID)
        NEW_RULES=$(git diff "$PREV_COMMIT":"$RISK_RULES_FILE" "$CURR_COMMIT":"$RISK_RULES_FILE" 2>/dev/null | \
                    grep "^+##" | \
                    grep -oP "[A-Z]+-\d+:" | \
                    sed 's/:$//')
        
        if [ -n "$NEW_RULES" ]; then
            echo "$NEW_RULES" | while read -r rule; do
                echo -e "  ${GREEN}+${NC} New Risk Rule: $rule"
            done
            
            echo ""
            echo "Suggested changelog entry:"
            echo ""
            echo "### Added Rules ($(echo "$NEW_RULES" | wc -l))"
            echo ""
            echo "$NEW_RULES" | while read -r rule; do
                echo "#### $rule: [TODO: Rule name]"
                echo "**Detection Pattern**: [TODO: Add pattern description]"
                echo ""
                echo "**Rule Logic**: [TODO: Add SQL or logic]"
                echo ""
                echo "**Thresholds**: [TODO: Add threshold specifications]"
                echo ""
                echo "**Intelligence Value**: ⭐⭐⭐⭐ HIGH"
                echo ""
            done
        else
            echo -e "  ${YELLOW}No new risk rules detected${NC}"
        fi
        
        # Check for threshold changes
        THRESHOLD_CHANGES=$(git diff "$PREV_COMMIT":"$RISK_RULES_FILE" "$CURR_COMMIT":"$RISK_RULES_FILE" 2>/dev/null | \
                           grep -i "threshold\|severity" | \
                           grep "^[+-]" | \
                           wc -l)
        
        if [ "$THRESHOLD_CHANGES" -gt 0 ]; then
            echo -e "  ${YELLOW}~${NC} Detected $THRESHOLD_CHANGES threshold/severity changes"
            echo ""
            echo "### Threshold Adjustments"
            echo ""
            echo "[TODO: Review and document threshold changes]"
            echo ""
        fi
    else
        echo -e "  ${YELLOW}No changes to risk rules file${NC}"
    fi
    echo ""
}

# Function to detect framework changes
detect_framework_changes() {
    echo -e "${BLUE}=== Analysis Framework Changes ===${NC}"
    
    if git diff --name-only "$PREV_COMMIT" "$CURR_COMMIT" | grep -q "$DATA_ANALYSIS_FILE"; then
        # Look for new framework sections
        FRAMEWORK_CHANGES=$(git diff "$PREV_COMMIT":"$DATA_ANALYSIS_FILE" "$CURR_COMMIT":"$DATA_ANALYSIS_FILE" 2>/dev/null | \
                           grep "^+##.*Framework" | \
                           sed 's/^+## //')
        
        if [ -n "$FRAMEWORK_CHANGES" ]; then
            echo "$FRAMEWORK_CHANGES" | while read -r framework; do
                echo -e "  ${GREEN}+${NC} $framework"
            done
            
            echo ""
            echo "Suggested changelog entry:"
            echo ""
            echo "### Analysis Frameworks"
            echo ""
            echo "$FRAMEWORK_CHANGES" | while read -r framework; do
                echo "**$framework**:"
                echo "- [TODO: Describe enhancement or new capability]"
                echo ""
            done
        else
            echo -e "  ${YELLOW}No framework changes detected${NC}"
        fi
    else
        echo -e "  ${YELLOW}No changes to data analysis file${NC}"
    fi
    echo ""
}

# Function to detect documentation changes
detect_documentation_changes() {
    echo -e "${BLUE}=== Documentation Changes ===${NC}"
    
    CHANGED_DOCS=$(git diff --name-only "$PREV_COMMIT" "$CURR_COMMIT" | \
                   grep -E "\.md$" | \
                   grep -v "CHANGELOG" | \
                   grep -E "INTELLIGENCE|RISK|DATABASE|DATA_ANALYSIS")
    
    if [ -n "$CHANGED_DOCS" ]; then
        echo "$CHANGED_DOCS" | while read -r doc; do
            echo -e "  ${YELLOW}~${NC} $doc"
        done
        
        echo ""
        echo "Suggested changelog entry:"
        echo ""
        echo "### Documentation"
        echo ""
        echo "$CHANGED_DOCS" | while read -r doc; do
            echo "- Updated $(basename "$doc")"
        done
        echo ""
    else
        echo -e "  ${YELLOW}No intelligence documentation changes${NC}"
    fi
    echo ""
}

# Function to generate complete changelog template
generate_changelog_template() {
    echo -e "${BLUE}=== Complete Changelog Template ===${NC}"
    echo ""
    
    VERSION="[UNRELEASED]"
    DATE=$(date +%Y-%m-%d)
    
    cat <<EOF
## $VERSION - $DATE

### Added

**Database Views**:
$(detect_new_views | grep "^+" | sed 's/^/- /')

**Risk Rules**:
$(detect_risk_rule_changes | grep "New Risk Rule" | sed 's/^  /- /')

**Analysis Frameworks**:
[TODO: Add framework enhancements]

**Intelligence Products**:
[TODO: Add new intelligence products]

### Changed

**Documentation**:
$(detect_documentation_changes | grep "~" | sed 's/^  ~/- Updated: /')

**Framework Enhancements**:
[TODO: Add framework improvements]

### Fixed
[TODO: Add bug fixes]

### Security
[TODO: Add security improvements]

### Performance
[TODO: Add performance improvements]

### Breaking Changes
[TODO: Document any breaking changes]

---

EOF
}

# Main execution
echo -e "${BLUE}Running analysis...${NC}"
echo ""

detect_new_views
detect_modified_views
detect_risk_rule_changes
detect_framework_changes
detect_documentation_changes

echo -e "${GREEN}================================================${NC}"
echo -e "${GREEN}Analysis Complete${NC}"
echo -e "${GREEN}================================================${NC}"
echo ""
echo -e "Next steps:"
echo -e "1. Review the suggested changelog entries above"
echo -e "2. Add appropriate descriptions and context"
echo -e "3. Update CHANGELOG_INTELLIGENCE_ANALYSIS.md with the [Unreleased] section"
echo -e "4. Update CHANGELOG_DATABASE_VIEWS.md with view details"
echo -e "5. Update CHANGELOG_RISK_RULES.md with rule specifications"
echo ""
echo -e "For a complete template, run:"
echo -e "  ${YELLOW}$0 $PREV_COMMIT $CURR_COMMIT > changelog-template.md${NC}"
echo ""
