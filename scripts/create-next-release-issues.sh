#!/bin/bash
#
# Script to create the top 5 priority issues for next CIA release
# 
# Usage:
#   export GITHUB_TOKEN=your_github_token
#   ./scripts/create-next-release-issues.sh
#
# Or use GitHub CLI with authentication:
#   gh auth login
#   ./scripts/create-next-release-issues.sh
#

set -e

REPO="Hack23/cia"
ISSUE_DIR="/tmp"

# Check if gh CLI is available
if ! command -v gh &> /dev/null; then
    echo "Error: GitHub CLI (gh) is not installed"
    echo "Install from: https://cli.github.com/"
    exit 1
fi

# Check authentication
if ! gh auth status &> /dev/null; then
    echo "Error: Not authenticated with GitHub CLI"
    echo "Run: gh auth login"
    exit 1
fi

echo "================================================"
echo "Creating Top 5 Priority Issues for Next Release"
echo "Repository: $REPO"
echo "================================================"
echo ""

# Issue 1: Jetty 12 Upgrade
echo "[1/5] Creating: Upgrade to Jetty 12 for Extended Support Until 2028"
if [ -f "$ISSUE_DIR/issue1.md" ]; then
    ISSUE1_URL=$(gh issue create \
        --repo "$REPO" \
        --title "Upgrade to Jetty 12 for Extended Support Until 2028" \
        --body-file "$ISSUE_DIR/issue1.md" \
        --label "enhancement,infrastructure,dependencies")
    echo "✓ Created: $ISSUE1_URL"
else
    echo "✗ File not found: $ISSUE_DIR/issue1.md"
fi
echo ""
sleep 2

# Issue 2: Drools Test Coverage
echo "[2/5] Creating: Enhance Test Coverage for Drools Risk Assessment Rules"
if [ -f "$ISSUE_DIR/issue2.md" ]; then
    ISSUE2_URL=$(gh issue create \
        --repo "$REPO" \
        --title "Enhance Test Coverage for Drools Risk Assessment Rules" \
        --body-file "$ISSUE_DIR/issue2.md" \
        --label "testing,enhancement,political-analysis,analytics")
    echo "✓ Created: $ISSUE2_URL"
else
    echo "✗ File not found: $ISSUE_DIR/issue2.md"
fi
echo ""
sleep 2

# Issue 3: PostgreSQL Documentation
echo "[3/5] Creating: Update and Validate PostgreSQL 16 Configuration Documentation"
if [ -f "$ISSUE_DIR/issue3.md" ]; then
    ISSUE3_URL=$(gh issue create \
        --repo "$REPO" \
        --title "Update and Validate PostgreSQL 16 Configuration Documentation" \
        --body-file "$ISSUE_DIR/issue3.md" \
        --label "documentation,database,enhancement")
    echo "✓ Created: $ISSUE3_URL"
else
    echo "✗ File not found: $ISSUE_DIR/issue3.md"
fi
echo ""
sleep 2

# Issue 4: Security Audit
echo "[4/5] Creating: Pre-Release Security Dependency Audit and Updates"
if [ -f "$ISSUE_DIR/issue4.md" ]; then
    ISSUE4_URL=$(gh issue create \
        --repo "$REPO" \
        --title "Pre-Release Security Dependency Audit and Updates" \
        --body-file "$ISSUE_DIR/issue4.md" \
        --label "security,dependencies,enhancement")
    echo "✓ Created: $ISSUE4_URL"
else
    echo "✗ File not found: $ISSUE_DIR/issue4.md"
fi
echo ""
sleep 2

# Issue 5: Performance Optimization
echo "[5/5] Creating: Database Query Performance Optimization"
if [ -f "$ISSUE_DIR/issue5.md" ]; then
    ISSUE5_URL=$(gh issue create \
        --repo "$REPO" \
        --title "Database Query Performance Optimization" \
        --body-file "$ISSUE_DIR/issue5.md" \
        --label "performance,database,enhancement")
    echo "✓ Created: $ISSUE5_URL"
else
    echo "✗ File not found: $ISSUE_DIR/issue5.md"
fi
echo ""

echo "================================================"
echo "Summary: All 5 Issues Created Successfully!"
echo "================================================"
echo ""
echo "Issue URLs:"
echo "1. $ISSUE1_URL"
echo "2. $ISSUE2_URL"
echo "3. $ISSUE3_URL"
echo "4. $ISSUE4_URL"
echo "5. $ISSUE5_URL"
echo ""
echo "Next steps:"
echo "  1. Review issues on GitHub"
echo "  2. Assign to appropriate team members"
echo "  3. Link to 'Next Release' milestone"
echo "  4. Prioritize in project board"
echo ""
