# view_riksdagen_party_summary SQL Fix Summary

## Problem
The view `view_riksdagen_party_summary` in `db-changelog-1.61.xml` was referencing `dd.party` column in the `document_data` table, but this column doesn't exist in the database schema.

## Root Cause
The `document_data` table does NOT have a `party` column. Party information is stored in a separate table (`document_person_reference_da_0`) and requires joining through intermediate tables:

1. `document_data` → `document_status_container` (via `document_document_status_con_0`)
2. `document_status_container` → `document_person_reference_co_0` (via `hjid`)
3. `document_person_reference_co_0` → `document_person_reference_da_0` (via `document_person_reference_li_1`)
4. `document_person_reference_da_0` contains `party_short_code`

## Solution
Rewrote the SQL query to:

1. **Create a CTE (Common Table Expression)** named `party_documents` that properly joins the tables:
   ```sql
   WITH party_documents AS (
       SELECT 
           dpr.party_short_code AS party,
           dpr.person_reference_id,
           dd.id AS doc_id,
           dd.document_type,
           dd.label,
           dd.made_public_date,
           dd.org
       FROM document_data dd
       INNER JOIN document_status_container dsc 
           ON dd.id = dsc.document_document_status_con_0
       INNER JOIN document_person_reference_co_0 dprc 
           ON dsc.hjid = dprc.hjid
       INNER JOIN document_person_reference_da_0 dpr 
           ON dprc.hjid = dpr.document_person_reference_li_1
       WHERE dpr.party_short_code IS NOT NULL
   )
   ```

2. **Main SELECT** now uses:
   - Direct aggregation from `party_documents` CTE instead of correlated subqueries
   - LEFT JOIN with the CTE to allow parties without documents
   - COALESCE to handle NULL values properly
   - COUNT(DISTINCT) to avoid duplicate counting

## Key Changes

### Before (Broken):
```sql
(SELECT COUNT(*) FROM document_data dd WHERE dd.party = vp.party)::bigint AS total_documents
```

### After (Fixed):
```sql
COALESCE(COUNT(DISTINCT pd.doc_id), 0)::bigint AS total_documents
```

## Benefits of the New Approach

1. **Correct Data Model**: Uses the actual database schema join paths
2. **Better Performance**: Single LEFT JOIN instead of multiple correlated subqueries
3. **Maintainability**: CTE makes the query structure clearer
4. **NULL Safety**: COALESCE ensures 0 instead of NULL for missing data
5. **Accuracy**: COUNT(DISTINCT) prevents duplicate counting from the joins

## Columns That Changed

All document-related metrics now properly use the `party_documents` CTE:
- `total_documents`
- `avg_documents_per_member`
- `total_party_motions`
- `total_individual_motions`
- `total_committee_motions`
- `very_high_activity_members`
- `high_activity_members`
- `medium_activity_members`
- `low_activity_members`
- `currently_active_members`
- `total_documents_last_year`
- `avg_documents_last_year`
- `first_party_document`
- `last_party_document`

## Placeholder Values

The following columns have been set to placeholder values (0) because they require additional logic to implement:
- `total_collaborative_motions` - needs multi-person document detection
- `total_follow_up_motions` - needs related_id join logic
- `avg_collaboration_percentage` - needs multi-person document percentage
- `highly_collaborative_members` - needs per-member collaboration analysis
- `party_focused_members` - needs document pattern analysis
- `committee_focused_members` - needs document pattern analysis
- `individual_focused_members` - needs document pattern analysis

These can be implemented in a future changeset once the base view is working.

## Testing Recommendation

1. Apply the changelog to a test database
2. Verify the view creates successfully
3. Query the view to ensure data is returned
4. Compare results with existing party data for validation
5. Check for NULL values in key columns

## Reference

This fix was based on the join pattern used in `view_riksdagen_politician_document` (defined in `db-changelog-1.24.xml`), which successfully implements the same document-to-party join path.
