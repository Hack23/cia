## 🎯 Objective
Complete distinct value extraction for all 107 views, ensuring categorical columns (party, committee, status, type, etc.) have comprehensive distinct value CSV files.

## 📋 Background
The extract-sample-data.sql script extracts distinct values for categorical/predicate columns to CSV files (e.g., `distinct_party_values.csv`, `distinct_committee_values.csv`). However, **not all views have distinct value extraction**, particularly analytical views and newly added views.

**Why distinct values matter:**
1. **Testing**: Filter dropdowns need all possible values
2. **Validation**: Verify data completeness (all 8 parties present?)
3. **Development**: Understand value domains before building queries
4. **Documentation**: Catalog valid values for each categorical dimension

## 📊 Current State (Measured Metrics)
- **Total Views**: 107 (79 views + 28 materialized views)
- **Distinct Value CSV Files**: 9 files in `service.data.impl/sample-data/distinct_*.csv`
- **Expected Categorical Columns**: ~40-50 columns across all views
- **Coverage Gap**: ~75% of categorical columns missing distinct value extraction

**Existing distinct value files:**
```
distinct_assignment_data_assignment_type_values.csv
distinct_assignment_data_detail_values.csv
distinct_assignment_data_org_code_values.csv
distinct_assignment_data_role_code_values.csv
distinct_assignment_data_status_values.csv
distinct_document_content_data_id_values.csv
distinct_document_detail_data_code_values.csv
distinct_document_detail_data_detail_name_values.csv
distinct_document_element_database_source_values.csv
```

**Missing distinct value files (examples):**
- Party-related: `distinct_party_id_values.csv`, `distinct_party_short_code_values.csv`
- Committee-related: `distinct_org_code_values.csv`, `distinct_committee_name_values.csv`
- Person-related: `distinct_person_status_values.csv`, `distinct_first_name_values.csv`
- Voting-related: `distinct_vote_values.csv`, `distinct_ballot_id_values.csv`
- Temporal: `distinct_year_values.csv`, `distinct_quarter_values.csv`

## ✅ Acceptance Criteria
- [ ] Identify all categorical columns across 107 views (party, committee, status, type, category, code, id)
- [ ] Generate distinct value CSV files for all categorical columns
- [ ] Add categorical column detection logic in Phase 2.5 (between table/view extraction)
- [ ] Create at least 30 additional distinct value CSV files
- [ ] Validate that all 8 Swedish political parties appear in `distinct_party_*` files
- [ ] Update `extraction_statistics.csv` with distinct value extraction metrics
- [ ] Document distinct value file naming convention in README.md

## 🛠️ Implementation Guidance

**Files to Modify:**
- `service.data.impl/src/main/resources/extract-sample-data.sql` - Add Phase 2.5: Extract Distinct Values for Views
- `service.data.impl/sample-data/README.md` - Document distinct value file structure

**Approach:**

1. **Detect Categorical Columns:**
   ```sql
   -- Query to find categorical columns (low cardinality)
   SELECT 
     c.table_schema,
     c.table_name,
     c.column_name,
     c.data_type,
     COUNT(DISTINCT t.column_value) as distinct_count
   FROM information_schema.columns c
   CROSS JOIN LATERAL (
     SELECT DISTINCT format('SELECT DISTINCT %I FROM %I.%I', c.column_name, c.table_schema, c.table_name)
   ) t
   WHERE c.table_schema = 'public'
     AND c.table_name LIKE 'view_%'
     AND c.data_type IN ('character varying', 'text', 'character', 'smallint', 'integer')
   GROUP BY c.table_schema, c.table_name, c.column_name, c.data_type
   HAVING COUNT(DISTINCT column_value) BETWEEN 2 AND 500  -- Categorical threshold
   ORDER BY distinct_count;
   ```

2. **Generate Distinct Value Extraction:**
   ```sql
   DO $$
   DECLARE
     v_view_name TEXT;
     v_column_name TEXT;
     v_distinct_count BIGINT;
     v_output_file TEXT;
   BEGIN
     -- Loop through categorical columns
     FOR v_view_name, v_column_name, v_distinct_count IN
       SELECT table_name, column_name, COUNT(DISTINCT column_value)
       FROM information_schema.columns
       WHERE table_schema = 'public' 
         AND table_name LIKE 'view_%'
         AND data_type IN ('character varying', 'text', 'character', 'smallint', 'integer')
       GROUP BY table_name, column_name
       HAVING COUNT(DISTINCT column_value) BETWEEN 2 AND 500
     LOOP
       -- Generate output file name
       v_output_file := format('distinct_%s_%s_values.csv', v_view_name, v_column_name);
       
       -- Extract distinct values
       EXECUTE format(
         'COPY (SELECT DISTINCT %I, COUNT(*) as count FROM %I GROUP BY %I ORDER BY count DESC) TO STDOUT CSV HEADER',
         v_column_name, v_view_name, v_column_name
       ) TO v_output_file;
       
       RAISE NOTICE 'Extracted % distinct values from %.% to %', 
                    v_distinct_count, v_view_name, v_column_name, v_output_file;
     END LOOP;
   END $$;
   ```

3. **Priority Columns (Extract First):**
   - **Party**: `party`, `party_short_code`, `party_id`
   - **Committee**: `org_code`, `org`, `committee_name`
   - **Person**: `status`, `gender`, `born_year`, `first_name`, `last_name`
   - **Voting**: `vote`, `ballot_id`, `vote_outcome`
   - **Document**: `doc_type`, `status`, `rm`, `document_type`
   - **Temporal**: `year`, `month`, `quarter`, `election_year`

4. **Validation:**
   ```sql
   -- Verify all 8 Swedish parties appear
   SELECT DISTINCT party FROM view_riksdagen_party_member ORDER BY party;
   -- Expected: S, M, SD, C, V, KD, L, MP
   
   -- Check distinct value file count
   \! ls -1 distinct_*.csv | wc -l
   -- Expected: >= 40 files
   ```

## 📚 Related Documentation
- `service.data.impl/sample-data/README.md` - Distinct value file structure
- `service.data.impl/sample-data/distinct_values/` - Directory for distinct value files
- Swedish political parties: https://en.wikipedia.org/wiki/List_of_political_parties_in_Sweden

## 🔗 Dependencies
- Depends on: Issue #1 (materialized views must be populated)
- Depends on: Issue #2 (table extraction must complete without errors)

## 🤖 Recommended Agent

**Agent**: @stack-specialist  
**Rationale**: This issue requires PostgreSQL metadata querying (`information_schema`), dynamic SQL generation for distinct value extraction, and understanding of CIA database schema (party, committee, person entities). The stack-specialist has deep knowledge of the CIA data model and PostgreSQL.

**Implementation Steps for Stack Specialist:**
1. Query `information_schema.columns` to find categorical columns
2. Identify columns with 2-500 distinct values (categorical threshold)
3. Generate distinct value extraction queries dynamically
4. Add Phase 2.5 to extract-sample-data.sql
5. Test on priority columns first (party, committee, status)
6. Verify all 8 Swedish parties appear in distinct value files
7. Document distinct value files in README.md
