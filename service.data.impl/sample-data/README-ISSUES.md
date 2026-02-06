# Sample Data Extraction Issues - Implementation Guide

## Overview

This directory contains 5 detailed GitHub issue specifications for completing sample data extraction across all 107 database views.

## Quick Start

### Option 1: Using the Shell Script (Recommended)
```bash
cd service.data.impl/sample-data
# Ensure gh CLI is authenticated
gh auth login
# Create all 5 issues
./create-github-issues.sh
```

### Option 2: Manual Creation
1. Go to https://github.com/Hack23/cia/issues/new
2. Copy content from each issue-*.md file
3. Apply appropriate labels
4. Create the issue

## Issue Files

| File | Title | Priority | Effort | Agent |
|------|-------|----------|--------|-------|
| `issue-1-fix-materialized-view-refresh.md` | Fix materialized view refresh sequence | High | Medium (4-8h) | @stack-specialist |
| `issue-2-fix-negative-limit-errors.md` | Fix negative LIMIT errors | High | Small (1-2h) | @stack-specialist |
| `issue-3-complete-distinct-values.md` | Complete distinct value extraction | Medium | Medium (4-8h) | @stack-specialist |
| `issue-4-add-percentile-statistics.md` | Add percentile statistics | Medium | Medium (4-8h) | @stack-specialist |
| `issue-5-add-normal-samples.md` | Add normal sample extraction | Medium | Medium (4-8h) | @stack-specialist |

## Implementation Sequence

**Critical Path:**
1. ✅ **Issue #1** (Must be done first) - Fixes 52 problematic views, unblocks all downstream work
2. ⚡ **Issue #2** (Can run in parallel with #1) - Fixes 44 LIMIT errors

**Parallel Work (after Issue #1):**
3. 🔄 **Issue #3** (Depends on #1) - Adds 40+ distinct value files
4. 🔄 **Issue #4** (Depends on #1) - Adds 100+ percentile files

**Final Integration:**
5. ✨ **Issue #5** (Depends on #1-4) - Achieves 100% view coverage (107 CSV files)

## Success Metrics

### Current State (After PR #8348)
- ❌ 85/107 view CSV files (79% coverage)
- ❌ 52 problematic views with errors
- ❌ 44 "LIMIT must not be negative" errors
- ❌ 9 distinct value files
- ❌ 24 percentile files

### Target State (After All 5 Issues)
- ✅ 107/107 view CSV files (100% coverage)
- ✅ 0 problematic views
- ✅ 0 LIMIT errors
- ✅ 40+ distinct value files  
- ✅ 100+ percentile files

## Labels to Apply

```
type:refactor          # All issues
priority:high          # Issues #1-2
priority:medium        # Issues #3-5
size:small             # Issue #2
size:medium            # Issues #1, #3-5
domain:database        # All issues
domain:testing         # Issues #3-4
```

## Testing After Each Issue

```bash
# Run extraction
psql -h localhost -U eris -d cia_dev -f service.data.impl/src/main/resources/extract-sample-data.sql 2>&1 | tee extract-sample-data.log

# Check for errors
grep -i "error" extract-sample-data.log | wc -l  # Should be 0

# Verify view coverage
ls -1 service.data.impl/sample-data/view_*.csv | wc -l  # Should be 107 after Issue #5

# Check distinct value coverage
ls -1 service.data.impl/sample-data/distinct_*.csv | wc -l  # Should be 40+ after Issue #3

# Check percentile coverage
ls -1 service.data.impl/sample-data/percentile_*.csv | wc -l  # Should be 100+ after Issue #4
```

## Related Documentation

- **Analysis Report**: `SAMPLE_DATA_EXTRACTION_ISSUES.md` - Comprehensive problem analysis
- **Extraction Log**: `extract-sample-data.log` - Current state with 52 errors
- **Problematic Views**: `problematic_views.csv` - 52 views with "materialized view not populated" errors
- **Empty Views**: `empty_views.csv` - 51 views with 0 rows
- **PR #8348**: https://github.com/Hack23/cia/pull/8348 - Recently merged sample data

## Files Affected by Implementation

- `service.data.impl/src/main/resources/extract-sample-data.sql` - Main extraction script
- `service.data.impl/sample-data/` - Output directory for all CSV files
- `service.data.impl/sample-data/README.md` - Sample data documentation
- `service.data.impl/src/main/resources/extract-sample-data-functions.sql` - Helper functions (optional)

## Agent Recommendations

All 5 issues are assigned to **@stack-specialist** because they require:
- Deep PostgreSQL knowledge (materialized views, dynamic SQL, PL/pgSQL)
- Understanding of CIA database schema (107 views, 28 materialized views)
- Experience with statistical sampling strategies
- Debugging complex SQL generation logic

## Questions?

For questions about these issues, contact:
- Repository maintainers: @pethers
- Database architecture: See `service.data.impl/README-SCHEMA-MAINTENANCE.md`
- Sample data structure: See `service.data.impl/sample-data/README.md`
