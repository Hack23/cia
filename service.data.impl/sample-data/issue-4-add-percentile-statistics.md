## 🎯 Objective
Add percentile statistics extraction (P1, P10, P25, P50, P75, P90, P99) for all numerical columns in analytical views to enable statistical validation and distribution analysis.

## 📋 Background
The extract-sample-data.sql script has a Phase 6.5 for percentile summary extraction, but **percentile statistics are missing for many analytical views**. Percentile data is critical for:
1. **Distribution validation**: Verify risk scores follow expected distribution (not all zeros/ones)
2. **Outlier detection**: Identify extreme values (P1, P99) for data quality checks
3. **Statistical testing**: Compare distributions across time periods or parties
4. **Performance benchmarking**: Track metric distributions over time

**Current coverage gap:** Only ~15 percentile CSV files exist, but 107 views × ~5 numerical columns each = **~500+ percentile extractions needed**.

## 📊 Current State (Measured Metrics)
- **Total Views**: 107 (79 views + 28 materialized views)
- **Percentile CSV Files**: 24 files in `service.data.impl/sample-data/percentile_*.csv`
- **Expected Numerical Columns**: ~300-400 columns across all views
- **Coverage**: ~6% of numerical columns have percentile stats
- **Target**: 90% coverage (exclude columns with < 100 rows)

**Existing percentile files:**
```
percentile_committee_productivity.csv
percentile_ministry_decision_impact.csv
percentile_party_performance_metrics.csv
percentile_politician_risk_summary.csv
percentile_risk_score_evolution.csv
... (19 more files)
```

**Missing percentile files (examples):**
```
percentile_view_riksdagen_coalition_alignment_matrix.csv  (alignment_rate, vote_count)
percentile_view_riksdagen_crisis_resilience_indicators.csv  (resilience_score, crisis_response_time)
percentile_view_riksdagen_voting_anomaly_detection.csv  (anomaly_score, deviation_magnitude)
percentile_view_party_momentum_analysis.csv  (momentum_score, trend_coefficient)
```

## ✅ Acceptance Criteria
- [ ] Identify all numerical columns in 107 views (INTEGER, NUMERIC, REAL, DOUBLE PRECISION)
- [ ] Extract P1, P10, P25, P50 (median), P75, P90, P99 for each numerical column
- [ ] Generate at least 80 additional percentile CSV files (total ~100 files)
- [ ] Include row count, min, max, mean, stddev alongside percentiles
- [ ] Skip columns with < 100 rows (insufficient for percentile analysis)
- [ ] Add percentile extraction for priority analytical views:
  - Risk assessment views (8 views)
  - Performance/productivity views (12 views)
  - Anomaly detection views (5 views)
  - Coalition/momentum views (6 views)
- [ ] Update `extraction_statistics.csv` with percentile coverage metrics

## 🛠️ Implementation Guidance

**Files to Modify:**
- `service.data.impl/src/main/resources/extract-sample-data.sql` - Expand Phase 6.5: Percentile Distribution Summaries
- `service.data.impl/src/main/resources/extract-percentile-summaries.sql` - Modular percentile extraction (optional)

**Approach:**

1. **Detect Numerical Columns:**
   ```sql
   SELECT 
     c.table_schema,
     c.table_name,
     c.column_name,
     c.data_type,
     c.numeric_precision,
     c.numeric_scale
   FROM information_schema.columns c
   WHERE c.table_schema = 'public'
     AND c.table_name LIKE 'view_%'
     AND c.data_type IN ('smallint', 'integer', 'bigint', 'numeric', 'real', 'double precision')
     AND c.column_name NOT IN ('id', 'hjid', 'row_number')  -- Exclude technical columns
   ORDER BY c.table_name, c.column_name;
   ```

2. **Generate Percentile Query:**
   ```sql
   CREATE OR REPLACE FUNCTION extract_percentiles(
     p_view_name TEXT,
     p_column_name TEXT
   ) RETURNS TABLE (
     view_name TEXT,
     column_name TEXT,
     row_count BIGINT,
     min_value NUMERIC,
     p1 NUMERIC,
     p10 NUMERIC,
     p25 NUMERIC,
     p50 NUMERIC,  -- Median
     p75 NUMERIC,
     p90 NUMERIC,
     p99 NUMERIC,
     max_value NUMERIC,
     mean NUMERIC,
     stddev NUMERIC
   ) AS $$
   BEGIN
     RETURN QUERY EXECUTE format(
       'SELECT 
         %L::TEXT as view_name,
         %L::TEXT as column_name,
         COUNT(*)::BIGINT as row_count,
         MIN(%I)::NUMERIC as min_value,
         PERCENTILE_CONT(0.01) WITHIN GROUP (ORDER BY %I)::NUMERIC as p1,
         PERCENTILE_CONT(0.10) WITHIN GROUP (ORDER BY %I)::NUMERIC as p10,
         PERCENTILE_CONT(0.25) WITHIN GROUP (ORDER BY %I)::NUMERIC as p25,
         PERCENTILE_CONT(0.50) WITHIN GROUP (ORDER BY %I)::NUMERIC as p50,
         PERCENTILE_CONT(0.75) WITHIN GROUP (ORDER BY %I)::NUMERIC as p75,
         PERCENTILE_CONT(0.90) WITHIN GROUP (ORDER BY %I)::NUMERIC as p90,
         PERCENTILE_CONT(0.99) WITHIN GROUP (ORDER BY %I)::NUMERIC as p99,
         MAX(%I)::NUMERIC as max_value,
         AVG(%I)::NUMERIC as mean,
         STDDEV(%I)::NUMERIC as stddev
       FROM %I
       WHERE %I IS NOT NULL',
       p_view_name, p_column_name,
       p_column_name, p_column_name, p_column_name, p_column_name, p_column_name,
       p_column_name, p_column_name, p_column_name, p_column_name, p_column_name,
       p_column_name, p_view_name, p_column_name
     );
   END;
   $$ LANGUAGE plpgsql;
   ```

3. **Batch Percentile Extraction:**
   ```sql
   DO $$
   DECLARE
     v_view_name TEXT;
     v_column_name TEXT;
     v_row_count BIGINT;
     v_output_file TEXT;
   BEGIN
     FOR v_view_name, v_column_name IN
       SELECT DISTINCT c.table_name, c.column_name
       FROM information_schema.columns c
       WHERE c.table_schema = 'public'
         AND c.table_name LIKE 'view_%'
         AND c.data_type IN ('integer', 'bigint', 'numeric', 'real', 'double precision')
         AND c.column_name NOT IN ('id', 'hjid', 'row_number')
     LOOP
       -- Check if view has sufficient data
       EXECUTE format('SELECT COUNT(*) FROM %I WHERE %I IS NOT NULL', 
                      v_view_name, v_column_name) INTO v_row_count;
       
       IF v_row_count >= 100 THEN
         v_output_file := format('percentile_%s_%s.csv', v_view_name, v_column_name);
         
         EXECUTE format(
           'COPY (SELECT * FROM extract_percentiles(%L, %L)) TO STDOUT CSV HEADER',
           v_view_name, v_column_name
         ) TO v_output_file;
         
         RAISE NOTICE 'Extracted percentiles for %.% (% rows) to %', 
                      v_view_name, v_column_name, v_row_count, v_output_file;
       ELSE
         RAISE NOTICE 'Skipped %.% - insufficient data (% rows)', 
                      v_view_name, v_column_name, v_row_count;
       END IF;
     END LOOP;
   END $$;
   ```

4. **Priority Views (Extract First):**
   - Risk assessment: `view_politician_risk_summary`, `view_ministry_risk_evolution`, `view_risk_score_evolution`
   - Performance: `view_party_performance_metrics`, `view_committee_productivity`, `view_ministry_effectiveness_trends`
   - Anomaly detection: `view_riksdagen_voting_anomaly_detection`, `view_riksdagen_seasonal_anomaly_detection`
   - Coalition: `view_riksdagen_coalition_alignment_matrix`, `view_party_momentum_analysis`

5. **Validation:**
   ```sql
   -- Verify percentile files generated
   \! ls -1 percentile_*.csv | wc -l
   -- Expected: >= 100 files
   
   -- Check distribution sanity (P1 < P10 < P25 < P50 < P75 < P90 < P99)
   SELECT * FROM percentile_politician_risk_summary WHERE p1 > p50 OR p50 > p99;
   -- Expected: 0 rows (no violations)
   ```

## 📚 Related Documentation
- `service.data.impl/src/main/resources/extract-percentile-summaries.sql` - Modular percentile extraction
- `service.data.impl/sample-data/README.md` - Percentile file structure
- PostgreSQL PERCENTILE_CONT: https://www.postgresql.org/docs/16/functions-aggregate.html

## 🔗 Dependencies
- Depends on: Issue #1 (materialized views must be populated)
- Depends on: Issue #2 (table extraction must complete without errors)

## 🤖 Recommended Agent

**Agent**: @stack-specialist  
**Rationale**: This issue requires advanced PostgreSQL window functions (PERCENTILE_CONT), statistical analysis, and understanding of CIA analytical views (risk, performance, anomaly, coalition). The stack-specialist has expertise in PostgreSQL analytics and the CIA data model.

**Implementation Steps for Stack Specialist:**
1. Query `information_schema.columns` to find all numerical columns in views
2. Create `extract_percentiles()` function for reusable percentile calculation
3. Generate percentile extraction queries for priority analytical views
4. Add Phase 6.5 expansion to extract-sample-data.sql
5. Test on priority views (risk, performance, anomaly)
6. Verify percentile ordering (P1 < P10 < ... < P99)
7. Generate at least 80 additional percentile CSV files
