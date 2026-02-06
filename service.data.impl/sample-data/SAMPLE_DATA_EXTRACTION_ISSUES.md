# Sample Data Extraction Issue Breakdown

## Executive Summary

Analysis of PR #8348 (sample data extraction results) completed. The extract-sample-data.sql script requires 5 targeted fixes to achieve 100% sample data extraction coverage for all 107 database views.

### Current State
- **Total Views**: 107 (79 views + 28 materialized views)
- **Extracted View CSV Files**: 85 (79% coverage)
- **Problematic Views**: 52 views with "materialized view not populated" errors
- **Empty Views**: 51 views with 0 rows
- **LIMIT Errors**: 44 occurrences of "LIMIT must not be negative"

### Target State
- **100% View Coverage**: 107 view CSV files
- **0 Problematic Views**: All materialized views populated correctly
- **Complete Distinct Values**: 40+ distinct value CSV files
- **Complete Percentile Stats**: 100+ percentile distribution CSV files
- **Robust Error Handling**: No LIMIT errors, graceful empty table handling

## Issues to Create

### Issue 1: Fix Materialized View Refresh Sequence (Phase 0)
**Priority**: Critical  
**Effort**: Medium (4-8h)  
**Agent**: @stack-specialist

**Problem**: 52 views fail with "materialized view not populated" errors because Phase 0 refreshes materialized views in single pass without respecting dependencies.

**Solution**: Implement dependency-aware multi-pass refresh algorithm:
- Level 0: Base materialized views (no dependencies)
- Level 1-6: Dependent views in correct order
- Add validation after each pass
- Increase timeouts for complex views (Level 0: 120s → Level 6: 300s)

**Impact**: Fixes 52 problematic views, enables downstream extraction

---

### Issue 2: Fix Negative LIMIT Errors in Table Extraction (Phase 4)
**Priority**: High  
**Effort**: Small (1-2h)  
**Agent**: @stack-specialist

**Problem**: 44 "LIMIT must not be negative" errors occur during dynamic SQL generation when calculating LIMIT values for empty or small tables.

**Solution**: Add defensive GREATEST() wrapper to all LIMIT calculations:
```sql
LIMIT GREATEST(
  CASE 
    WHEN v_row_count <= 200 THEN v_row_count
    ELSE 200
  END,
  1  -- Minimum 1 row for non-empty tables
)

-- For empty tables, use LIMIT 0 to get header only
LIMIT CASE 
  WHEN v_row_count = 0 THEN 0
  ELSE GREATEST(calculated_limit, 1)
END
```

**Impact**: Eliminates all LIMIT errors, enables graceful handling of empty tables

---

### Issue 3: Complete Distinct Value Extraction for All Views
**Priority**: Medium  
**Effort**: Medium (4-8h)  
**Agent**: @stack-specialist

**Problem**: Only 9 distinct value CSV files exist, but 40-50 categorical columns across 107 views need distinct value extraction.

**Solution**: Add Phase 2.5 to query `information_schema.columns`, detect categorical columns (2-500 distinct values), and generate distinct value CSV files dynamically.

**Priority columns**:
- Party: party, party_short_code, party_id
- Committee: org_code, org, committee_name
- Person: status, gender, born_year
- Voting: vote, ballot_id, vote_outcome
- Temporal: year, month, quarter

**Impact**: Enables filter testing, data validation, value domain documentation

---

### Issue 4: Add Percentile Statistics for All Numeric Columns
**Priority**: Medium  
**Effort**: Medium (4-8h)  
**Agent**: @stack-specialist

**Problem**: Only 24 percentile CSV files exist, but 300-400 numerical columns across 107 views need percentile statistics (P1, P10, P25, P50, P75, P90, P99).

**Solution**: Expand Phase 6.5 to:
- Query `information_schema.columns` for numerical columns
- Create `extract_percentiles()` function using PERCENTILE_CONT
- Generate percentile CSV files for columns with >= 100 rows
- Include min, max, mean, stddev alongside percentiles

**Priority views**:
- Risk assessment (8 views)
- Performance/productivity (12 views)
- Anomaly detection (5 views)
- Coalition/momentum (6 views)

**Impact**: Enables statistical validation, distribution analysis, outlier detection

---

### Issue 5: Add Normal Sample Extraction for Remaining 22 Views
**Priority**: Medium  
**Effort**: Medium (4-8h)  
**Agent**: @stack-specialist

**Problem**: 22 views (107 total - 85 extracted = 22 missing) lack CSV files due to timeouts, dependencies, or missing extraction logic.

**Solution**: 
1. Identify 22 missing views (compare database vs CSV files)
2. Categorize by sampling strategy:
   - Temporal views → Temporal stratified sampling (500 rows)
   - Analytical views → Percentile-based sampling (500 rows)
   - Entity views → Random sampling (200 rows)
3. Handle complex view timeouts (increase to 300s, consider materialized view conversion)
4. Extract all 22 missing views

**Dependencies**: Requires Issues #1-4 to be completed first

**Impact**: Achieves 100% view coverage (107 CSV files)

## Implementation Sequence

1. **Issue #1** (Critical) - Must be done first, unblocks Issues #3-5
2. **Issue #2** (High) - Can be done in parallel with Issue #1
3. **Issue #3** (Medium) - Depends on Issue #1
4. **Issue #4** (Medium) - Depends on Issue #1, can be done in parallel with Issue #3
5. **Issue #5** (Medium) - Depends on Issues #1-4, final integration

## Labels to Apply

- **type:refactor** - Improving existing extraction logic
- **priority:high** - Issues #1-2
- **priority:medium** - Issues #3-5
- **size:small** - Issue #2 (1-2h)
- **size:medium** - Issues #1, #3-5 (4-8h each)
- **domain:database** - All issues
- **domain:testing** - Issues #3-4 (test data generation)

## Success Metrics

**Before (Current State):**
- 85/107 view CSV files (79% coverage)
- 52 problematic views
- 44 LIMIT errors
- 9 distinct value files
- 24 percentile files

**After (Target State):**
- 107/107 view CSV files (100% coverage)
- 0 problematic views
- 0 LIMIT errors
- 40+ distinct value files
- 100+ percentile files

## Files Affected

- `service.data.impl/src/main/resources/extract-sample-data.sql` - Main extraction script
- `service.data.impl/sample-data/` - Output directory for CSV files
- `service.data.impl/sample-data/README.md` - Documentation updates
- `service.data.impl/src/main/resources/extract-sample-data-functions.sql` - Helper functions (optional)

## Testing Strategy

After each issue is completed:
1. Run `psql -h localhost -U eris -d cia_dev -f extract-sample-data.sql`
2. Verify no errors in output
3. Check CSV file count: `ls -1 service.data.impl/sample-data/view_*.csv | wc -l`
4. Validate data quality: Check row counts, distinct values, percentile ordering

## Related Documentation

- PR #8348: https://github.com/Hack23/cia/pull/8348
- `service.data.impl/sample-data/extract-sample-data.log` - Extraction log with errors
- `service.data.impl/sample-data/problematic_views.csv` - 52 views with errors
- `service.data.impl/sample-data/empty_views.csv` - 51 empty views
- `service.data.impl/README-SCHEMA-MAINTENANCE.md` - Database maintenance guide
