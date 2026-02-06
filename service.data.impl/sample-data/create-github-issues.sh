#!/bin/bash
# Script to create 5 GitHub issues for sample data extraction improvements
# Requires: gh CLI installed and authenticated (gh auth login)

set -e

REPO="Hack23/cia"

echo "Creating 5 GitHub issues for sample data extraction..."
echo "Repository: $REPO"
echo ""

# Issue 1: Fix materialized view refresh sequence
echo "[1/5] Creating Issue 1: Fix materialized view refresh sequence..."
ISSUE1_URL=$(gh issue create \
  --repo "$REPO" \
  --title "Fix materialized view refresh sequence for complete sample data extraction (Phase 0)" \
  --body "$(cat issue-1-fix-materialized-view-refresh.md)" \
  --label "type:refactor,priority:high,size:medium,domain:database")
echo "✓ Created: $ISSUE1_URL"
echo ""

# Issue 2: Fix negative LIMIT errors
echo "[2/5] Creating Issue 2: Fix negative LIMIT errors..."
ISSUE2_URL=$(gh issue create \
  --repo "$REPO" \
  --title "Fix negative LIMIT errors in table extraction (Phase 4)" \
  --body "$(cat issue-2-fix-negative-limit-errors.md)" \
  --label "type:refactor,priority:high,size:small,domain:database")
echo "✓ Created: $ISSUE2_URL"
echo ""

# Issue 3: Complete distinct value extraction
echo "[3/5] Creating Issue 3: Complete distinct value extraction..."
ISSUE3_URL=$(gh issue create \
  --repo "$REPO" \
  --title "Complete distinct value extraction for all 107 views" \
  --body "$(cat issue-3-complete-distinct-values.md)" \
  --label "type:refactor,priority:medium,size:medium,domain:database,domain:testing")
echo "✓ Created: $ISSUE3_URL"
echo ""

# Issue 4: Add percentile statistics
echo "[4/5] Creating Issue 4: Add percentile statistics..."
ISSUE4_URL=$(gh issue create \
  --repo "$REPO" \
  --title "Add percentile statistics extraction for all numerical columns" \
  --body "$(cat issue-4-add-percentile-statistics.md)" \
  --label "type:refactor,priority:medium,size:medium,domain:database,domain:testing")
echo "✓ Created: $ISSUE4_URL"
echo ""

# Issue 5: Add normal sample extraction
echo "[5/5] Creating Issue 5: Add normal sample extraction for remaining views..."
ISSUE5_URL=$(gh issue create \
  --repo "$REPO" \
  --title "Add normal sample extraction for remaining 22 views (100% coverage)" \
  --body "$(cat issue-5-add-normal-samples.md)" \
  --label "type:refactor,priority:medium,size:medium,domain:database")
echo "✓ Created: $ISSUE5_URL"
echo ""

echo "=========================================="
echo "All 5 issues created successfully!"
echo "=========================================="
echo "Issue 1: $ISSUE1_URL"
echo "Issue 2: $ISSUE2_URL"
echo "Issue 3: $ISSUE3_URL"
echo "Issue 4: $ISSUE4_URL"
echo "Issue 5: $ISSUE5_URL"
echo ""
echo "Implementation sequence:"
echo "  1. Issue #1 (Critical) - Unblocks all other issues"
echo "  2. Issue #2 (High) - Can be done in parallel with #1"
echo "  3. Issues #3-4 (Medium) - Depend on #1, can be done in parallel"
echo "  4. Issue #5 (Medium) - Depends on #1-4, final integration"
