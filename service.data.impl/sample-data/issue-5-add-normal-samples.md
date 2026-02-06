## 🎯 Objective
Add normal sample extraction for remaining 22 views (107 total views - 85 extracted = 22 missing), ensuring 100% view coverage with representative temporal and categorical sampling.

## 📋 Background
The extract-sample-data.sql script successfully extracted sample data for 85 views but **22 views are missing CSV files**. These missing views are likely:
1. Views that timed out during extraction (complex queries)
2. Empty views due to materialized view dependencies (fixed by Issue #1)
3. Views with temporal stratification that failed (date range issues)
4. Newly added analytical views not included in extraction logic

**Goal:** Achieve 100% view coverage (107 CSV files) with high-quality samples using appropriate sampling strategies (temporal stratification, percentile-based, categorical, random).

## 📊 Current State (Measured Metrics)
- **Total Views**: 107 (79 views + 28 materialized views)
- **Extracted View CSV Files**: 85 files
- **Missing View CSV Files**: 22 files (21% gap)
- **Target**: 107 view CSV files (100% coverage)

**Missing views (estimated based on problematic_views.csv):**
- Election analysis views: `view_election_cycle_comparative_analysis`, `view_riksdagen_party_electoral_trends`
- Coalition views: `view_riksdagen_party_coalition_evolution`, `view_riksdagen_party_longitudinal_performance`
- Career path views: `view_riksdagen_politician_career_path_10level`
- Temporal trend views: `view_election_cycle_temporal_trends`, `view_riksdagen_pre_election_quarterly_activity`
- Performance views: `view_party_effectiveness_trends`, `view_committee_productivity_matrix`

## ✅ Acceptance Criteria
- [ ] Identify all 22 missing views without CSV files
- [ ] Determine appropriate sampling strategy for each view:
  - Temporal views → Temporal stratified sampling
  - Analytical views → Percentile-based sampling
  - Entity views → Random sampling
  - Complex views → Materialized view conversion (if timeout issues)
- [ ] Extract samples for all 22 missing views
- [ ] Achieve 100% view coverage (107 CSV files)
- [ ] Add timeout handling for complex views (increase to 300s for Level 5+ dependency views)
- [ ] Update `extraction_statistics.csv` with 100% view extraction success rate
- [ ] Update `sample_data_manifest.csv` with all 107 view files

## 🛠️ Implementation Guidance

**Files to Modify:**
- `service.data.impl/src/main/resources/extract-sample-data.sql` - Phase 5: Extract View Samples
- `service.data.impl/sample-data/README.md` - Update with complete view coverage

**Approach:**

1. **Identify Missing Views:**
   ```bash
   # Compare database views vs CSV files
   cd service.data.impl/sample-data
   
   # List all views in database
   psql -h localhost -U eris -d cia_dev -t -c \
     "SELECT table_name FROM information_schema.views WHERE table_schema = 'public' AND table_name LIKE 'view_%' ORDER BY table_name" \
     > db_views.txt
   
   # List all view CSV files
   ls -1 view_*.csv | sed 's/_sample\.csv$//' > extracted_views.txt
   
   # Find missing views
   comm -23 <(sort db_views.txt) <(sort extracted_views.txt) > missing_views.txt
   ```

2. **Categorize Missing Views by Sampling Strategy:**
   ```sql
   -- Temporal views (use temporal stratified sampling)
   SELECT table_name, 
          COUNT(*) FILTER (WHERE column_name LIKE '%date%' OR column_name LIKE '%year%') as temporal_columns
   FROM information_schema.columns
   WHERE table_schema = 'public' 
     AND table_name IN ('view_election_cycle_comparative_analysis', 'view_riksdagen_party_electoral_trends', ...)
   GROUP BY table_name
   HAVING COUNT(*) FILTER (WHERE column_name LIKE '%date%' OR column_name LIKE '%year%') > 0;
   
   -- Analytical views with numerical metrics (use percentile-based sampling)
   SELECT table_name,
          COUNT(*) FILTER (WHERE data_type IN ('integer', 'numeric', 'real')) as numerical_columns
   FROM information_schema.columns
   WHERE table_schema = 'public'
     AND table_name IN (SELECT missing_view FROM missing_views.txt)
   GROUP BY table_name
   HAVING COUNT(*) FILTER (WHERE data_type IN ('integer', 'numeric', 'real')) > 5;
   ```

3. **Extract Missing Views with Appropriate Strategy:**
   ```sql
   DO $$
   DECLARE
     v_view_name TEXT;
     v_sampling_strategy TEXT;
     v_sample_size INT;
     v_query TEXT;
   BEGIN
     -- Load missing views from file
     FOR v_view_name IN 
       SELECT view_name FROM missing_views_table
     LOOP
       -- Determine sampling strategy
       IF v_view_name LIKE '%temporal%' OR v_view_name LIKE '%election%' OR v_view_name LIKE '%quarterly%' THEN
         v_sampling_strategy := 'temporal';
         v_sample_size := 500;  -- Larger sample for temporal analysis
         v_query := cia_classify_temporal_view(v_view_name);  -- Use temporal stratification
       ELSIF v_view_name LIKE '%performance%' OR v_view_name LIKE '%risk%' OR v_view_name LIKE '%productivity%' THEN
         v_sampling_strategy := 'percentile';
         v_sample_size := 500;
         v_query := cia_percentile_sample(v_view_name, 'metric_column', 'ORDER BY metric_column');
       ELSE
         v_sampling_strategy := 'random';
         v_sample_size := 200;
         v_query := format('SELECT * FROM %I ORDER BY random() LIMIT %s', v_view_name, v_sample_size);
       END IF;
       
       -- Execute extraction
       RAISE NOTICE 'Extracting % using % strategy (% rows)', v_view_name, v_sampling_strategy, v_sample_size;
       EXECUTE format('COPY (%s) TO STDOUT CSV HEADER', v_query) 
         TO format('%s_sample.csv', v_view_name);
     END LOOP;
   END $$;
   ```

4. **Handle Complex View Timeouts:**
   ```sql
   -- For views with dependencies (Level 5-6), increase timeout
   SET statement_timeout = '300s';  -- 5 minutes for complex views
   
   -- Consider materialized view conversion for persistent timeout issues
   CREATE MATERIALIZED VIEW IF NOT EXISTS mv_election_cycle_comparative_analysis AS
   SELECT * FROM view_election_cycle_comparative_analysis;
   
   -- Then extract from materialized view
   COPY (SELECT * FROM mv_election_cycle_comparative_analysis ORDER BY random() LIMIT 500) 
     TO 'view_election_cycle_comparative_analysis_sample.csv' CSV HEADER;
   ```

5. **Validation:**
   ```bash
   # Verify 100% coverage
   ls -1 view_*.csv | wc -l
   # Expected: 107 files
   
   # Check sample sizes
   for f in view_*.csv; do
     echo "$f: $(wc -l < $f) rows"
   done | sort -t: -k2 -n
   
   # Verify no empty files
   find . -name "view_*.csv" -size 0
   # Expected: 0 files
   ```

## 📚 Related Documentation
- `service.data.impl/sample-data/problematic_views.csv` - Views with errors (candidates for missing)
- `service.data.impl/sample-data/empty_views.csv` - Views with no data (may need special handling)
- `service.data.impl/src/main/resources/extract-sample-data-functions.sql` - Sampling helper functions

## 🔗 Dependencies
- Depends on: Issue #1 (materialized views must be populated to fix 52 problematic views)
- Depends on: Issue #2 (table extraction must complete without LIMIT errors)
- Depends on: Issue #3 (distinct values help validate categorical coverage)
- Depends on: Issue #4 (percentile stats help validate numerical coverage)

## 🤖 Recommended Agent

**Agent**: @stack-specialist  
**Rationale**: This is the final integration issue that requires comprehensive understanding of all sampling strategies (temporal, percentile, random, categorical), CIA view architecture (dependency levels, complexity categories), and PostgreSQL optimization techniques (timeout handling, materialized view conversion). The stack-specialist has the full context from Issues #1-4 to complete 100% view coverage.

**Implementation Steps for Stack Specialist:**
1. Generate list of 22 missing views (compare database vs CSV files)
2. Categorize missing views by sampling strategy (temporal, percentile, random)
3. Implement extraction for each missing view with appropriate strategy
4. Handle complex view timeouts (increase to 300s, consider materialized view conversion)
5. Test extraction for all 22 missing views
6. Verify 100% coverage (107 view CSV files)
7. Update extraction_statistics.csv and sample_data_manifest.csv
8. Update README.md with complete view coverage documentation

**Success Criteria:**
- All 107 views have CSV files in `service.data.impl/sample-data/`
- No "materialized view not populated" errors (fixed by Issue #1)
- No "LIMIT must not be negative" errors (fixed by Issue #2)
- Complete distinct value coverage (Issue #3)
- Complete percentile statistics (Issue #4)
- 100% sample data extraction success rate
