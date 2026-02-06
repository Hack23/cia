## 🎯 Objective
Fix negative LIMIT errors in table extraction (Phase 4) that prevent sample data extraction from empty tables.

## 📋 Background
The extract-sample-data.sql script generates **44 "LIMIT must not be negative" errors** during table extraction (Phase 4). These errors occur when the script calculates a LIMIT value based on table row count, but for empty tables the calculation produces negative numbers.

**Root cause:** Dynamic SQL generation uses `GREATEST(LIMIT - row_count, 0)` which can produce negative values when `row_count > LIMIT`, or when calculation logic has integer underflow issues.

## 📊 Current State (Measured Metrics)
- **Total Tables**: 94 tables in database
- **Successful Table Extractions**: 93 tables with CSV files
- **Empty Tables (Header Only)**: 1 table
- **LIMIT Errors**: 44 occurrences (46% error rate for dynamic queries)
- **Target**: 0 LIMIT errors, 100% table extraction success

**Error pattern from `extract-sample-data.log`:**
```
psql:/tmp/cia_table_extract_commands.sql:12: ERROR:  2201W: LIMIT must not be negative
psql:/tmp/cia_table_extract_commands.sql:22: ERROR:  2201W: LIMIT must not be negative
psql:/tmp/cia_table_extract_commands.sql:32: ERROR:  2201W: LIMIT must not be negative
```

**Affected queries:**
- Random sampling queries with calculated LIMIT values
- Stratified sampling with per-stratum LIMIT calculations
- Percentile-based sampling with dynamic sample sizes

## ✅ Acceptance Criteria
- [ ] Fix all 44 "LIMIT must not be negative" errors
- [ ] Handle empty tables gracefully (export header-only CSV)
- [ ] Ensure LIMIT calculation never produces negative values
- [ ] Add validation: `LIMIT = GREATEST(calculated_limit, 1)` for all dynamic queries
- [ ] Test extraction on empty tables without errors
- [ ] Verify all 94 tables have CSV files (including empty tables with headers)
- [ ] Update `extract-sample-data.sql` Phase 4 with corrected LIMIT logic

## 🛠️ Implementation Guidance

**Files to Modify:**
- `service.data.impl/src/main/resources/extract-sample-data.sql` - Phase 4: Extract Table Samples (lines 1200-1800)

**Approach:**

1. **Identify Dynamic LIMIT Calculations:**
   Search for patterns like:
   ```sql
   -- Problematic patterns
   LIMIT (200 - row_count)  -- Can be negative if row_count > 200
   LIMIT row_count / strata_count  -- Can be negative if division underflows
   LIMIT percentile_position - 1  -- Can be negative if percentile_position = 0
   ```

2. **Apply Safe LIMIT Wrapper:**
   ```sql
   -- Safe pattern using GREATEST
   LIMIT GREATEST(
     CASE 
       WHEN v_row_count <= 200 THEN v_row_count
       ELSE 200
     END,
     1  -- Minimum 1 row for non-empty tables
   )
   
   -- For empty tables (v_row_count = 0), use LIMIT 0 to get header only
   LIMIT CASE 
     WHEN v_row_count = 0 THEN 0
     ELSE GREATEST(calculated_limit, 1)
   END
   ```

3. **Handle Empty Tables:**
   ```sql
   IF v_row_count = 0 THEN
     -- Export header-only CSV
     v_query := format(
       'COPY (SELECT * FROM %I.%I LIMIT 0) TO STDOUT CSV HEADER',
       schema_name, table_name
     );
     RAISE NOTICE 'Table % is empty - exporting header only', table_name;
   ELSE
     -- Normal sampling logic
     v_query := format(
       'COPY (SELECT * FROM %I.%I ORDER BY random() LIMIT %s) TO STDOUT CSV HEADER',
       schema_name, table_name, GREATEST(LEAST(v_row_count, 200), 1)
     );
   END IF;
   ```

4. **Fix Stratified Sampling:**
   ```sql
   -- Calculate samples per stratum
   v_samples_per_stratum := GREATEST(
     FLOOR(v_total_samples::NUMERIC / v_num_strata::NUMERIC),
     1  -- Minimum 1 sample per stratum
   );
   
   -- Generate sampling query
   v_query := format(
     'SELECT * FROM %I.%I WHERE %I = $1 ORDER BY random() LIMIT %s',
     schema_name, table_name, strata_column,
     GREATEST(v_samples_per_stratum, 1)  -- Never negative
   );
   ```

5. **Add Defensive Checks:**
   ```sql
   -- Before executing any dynamic query
   IF v_limit_value < 0 THEN
     RAISE WARNING 'Calculated negative LIMIT (%) for table %, using 0', 
                   v_limit_value, table_name;
     v_limit_value := 0;
   END IF;
   ```

## 📚 Related Documentation
- `service.data.impl/sample-data/extract-sample-data.log` - Lines with "LIMIT must not be negative" errors
- `service.data.impl/src/main/resources/extract-sample-data-functions.sql` - Helper functions with LIMIT calculations
- PostgreSQL LIMIT documentation: https://www.postgresql.org/docs/16/queries-limit.html

## 🔗 Dependencies
- Depends on: Issue #1 (materialized view refresh must complete first)
- PostgreSQL 16+ LIMIT clause behavior
- PL/pgSQL dynamic SQL generation

## 🤖 Recommended Agent

**Agent**: @stack-specialist  
**Rationale**: This issue requires PostgreSQL expertise (dynamic SQL, PL/pgSQL, LIMIT clause behavior) and careful debugging of existing extraction logic. The stack-specialist understands PostgreSQL edge cases and the CIA database schema.

**Implementation Steps for Stack Specialist:**
1. Locate all dynamic LIMIT calculations in extract-sample-data.sql
2. Identify which calculations can produce negative values
3. Add GREATEST() wrapper to ensure LIMIT ≥ 0
4. Special handling for empty tables (LIMIT 0 for header-only)
5. Test on database with mix of empty and populated tables
6. Verify no "LIMIT must not be negative" errors in log
7. Confirm all 94 tables have CSV files
