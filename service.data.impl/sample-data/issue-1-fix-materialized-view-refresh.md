## 🎯 Objective
Fix materialized view refresh sequence to ensure all 28 materialized views are populated before sample data extraction begins (Phase 0 of extract-sample-data.sql).

## 📋 Background
The extract-sample-data.sql script attempts to extract data from 107 views (79 regular views + 28 materialized views). However, **52 views fail** with "materialized view has not been populated" errors because materialized views are not refreshed in correct dependency order before extraction.

**Root cause:** Phase 0 refreshes materialized views in a single pass, but complex views with dependencies (e.g., `view_riksdagen_politician_document` dependency chain) require multi-pass refresh with dependency-aware ordering.

## 📊 Current State (Measured Metrics)
- **Total Views**: 107 (79 views + 28 materialized views)
- **Problematic Views**: 52 views with "materialized view not populated" errors
- **Empty Views**: 51 views return 0 rows
- **Successful Extractions**: 85 view CSV files (79% coverage)
- **Target**: 100% view extraction coverage (107 CSV files)

**Error pattern from `problematic_views.csv`:**
```
view_riksdagen_politician_document,materialized_view,0,SIMPLE,ERROR,"materialized view ""view_riksdagen_politician_document"" has not been populated"
view_riksdagen_vote_data_ballot_party_summary,materialized_view,1,SIMPLE,ERROR,"materialized view ""view_riksdagen_vote_data_ballot_party_summary"" has not been populated"
```

## ✅ Acceptance Criteria
- [ ] Implement dependency-aware multi-pass materialized view refresh algorithm
- [ ] Ensure all 28 materialized views are populated in correct order (Level 0 → Level 6)
- [ ] Add validation that confirms all materialized views contain data before extraction
- [ ] Reduce problematic views from 52 to 0
- [ ] Achieve 100% materialized view population rate
- [ ] Update `extract-sample-data.sql` Phase 0 with improved refresh logic
- [ ] Add detailed logging showing refresh order and row counts per materialized view

## 🛠️ Implementation Guidance

**Files to Modify:**
- `service.data.impl/src/main/resources/extract-sample-data.sql` - Phase 0: Refresh Materialized Views section (lines 350-600)

**Approach:**

1. **Analyze View Dependencies:**
   - Query `pg_catalog.pg_depend` to build dependency graph
   - Identify dependency levels (0-6 based on `problematic_views.csv`)
   - Level 0 views have no dependencies on other materialized views
   - Level 6 views depend on Level 5, which depend on Level 4, etc.

2. **Multi-Pass Refresh Algorithm:**
   ```sql
   -- Pass 1: Level 0 (base materialized views)
   REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_committee_decisions;
   REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_politician_document;
   REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_vote_data_ballot_summary;
   -- Verify data: SELECT COUNT(*) FROM each view
   
   -- Pass 2: Level 1 (depends on Level 0)
   REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_vote_data_ballot_party_summary;
   REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_politician_document_summary;
   -- Verify data: SELECT COUNT(*) FROM each view
   
   -- Continue for Level 2-6...
   ```

3. **Add Validation:**
   ```sql
   DO $$
   DECLARE
     v_view_name TEXT;
     v_row_count BIGINT;
   BEGIN
     FOR v_view_name IN 
       SELECT matviewname FROM pg_matviews WHERE schemaname = 'public'
     LOOP
       EXECUTE format('SELECT COUNT(*) FROM %I', v_view_name) INTO v_row_count;
       IF v_row_count = 0 THEN
         RAISE WARNING 'Materialized view % is empty after refresh', v_view_name;
       ELSE
         RAISE NOTICE 'Materialized view % contains % rows', v_view_name, v_row_count;
       END IF;
     END LOOP;
   END $$;
   ```

4. **Edge Cases:**
   - Handle circular dependencies (should not exist, but validate)
   - Handle views that are legitimately empty (e.g., no data in date range)
   - Handle refresh failures with CONTINUE exception handling
   - Set appropriate `statement_timeout` per dependency level (Level 0: 120s, Level 6: 300s)

## 📚 Related Documentation
- `service.data.impl/sample-data/problematic_views.csv` - 52 views with errors
- `service.data.impl/sample-data/extract-sample-data.log` - Phase 0 refresh log
- `service.data.impl/src/main/resources/refresh-all-views.sql` - Existing refresh script
- `service.data.impl/src/main/resources/analyze-view-dependencies.sql` - Dependency analysis

## 🔗 Dependencies
- PostgreSQL 16+ with materialized view support
- `full_schema.sql` contains all view definitions
- Database: `cia_dev` with schema loaded

## 🤖 Recommended Agent

**Agent**: @stack-specialist  
**Rationale**: This issue requires deep PostgreSQL knowledge (materialized views, dependency analysis, PL/pgSQL) and experience with the CIA database schema. The stack-specialist has expertise in PostgreSQL, JPA/Hibernate, and the CIA technology stack.

**Implementation Steps for Stack Specialist:**
1. Analyze `problematic_views.csv` to extract dependency levels (0-6)
2. Query `pg_catalog.pg_depend` to validate dependency relationships
3. Implement multi-pass refresh algorithm in Phase 0
4. Add validation queries after each pass
5. Test with `psql -h localhost -U eris -d cia_dev -f extract-sample-data.sql`
6. Verify all 28 materialized views are populated
7. Commit updated `extract-sample-data.sql` with improved Phase 0
