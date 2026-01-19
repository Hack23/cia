# Quick Migration Guide: view_riksdagen_party_summary Fix

## Problem Statement
The view `view_riksdagen_party_summary` failed to create because it referenced `dd.party` which doesn't exist in `document_data` table.

## Solution Applied
Rewrote SQL to properly join through the table relationship chain to reach `party_short_code`.

## Files Modified
- ✅ `/service.data.impl/src/main/resources/db-changelog-1.61.xml` (lines 96-227)

## Key Changes

### Before (Broken) ❌
```sql
SELECT
    party,
    ...
    (SELECT COUNT(*) FROM document_data dd WHERE dd.party = vp.party)::bigint AS total_documents,
    ...
FROM view_riksdagen_politician vp
GROUP BY party
```

**Problem**: `dd.party` column doesn't exist!

### After (Fixed) ✅
```sql
WITH party_documents AS (
    SELECT 
        dpr.party_short_code AS party,
        dpr.person_reference_id,
        dd.id AS doc_id,
        ...
    FROM document_data dd
    INNER JOIN document_status_container dsc ON dd.id = dsc.document_document_status_con_0
    INNER JOIN document_person_reference_co_0 dprc ON dsc.hjid = dprc.hjid
    INNER JOIN document_person_reference_da_0 dpr ON dprc.hjid = dpr.document_person_reference_li_1
    WHERE dpr.party_short_code IS NOT NULL
)
SELECT
    vp.party,
    ...
    COALESCE(COUNT(DISTINCT pd.doc_id), 0)::bigint AS total_documents,
    ...
FROM view_riksdagen_politician vp
LEFT JOIN party_documents pd ON vp.party = pd.party
GROUP BY vp.party
```

**Solution**: Proper 4-table join chain reaching `party_short_code`

## Technical Details

### Join Path
```
document_data 
  → document_status_container 
    → document_person_reference_co_0 
      → document_person_reference_da_0 (contains party_short_code)
```

### Performance Improvements
1. **CTE replaces correlated subqueries**: One join instead of 15+ subqueries
2. **DISTINCT prevents duplicates**: Multiple person references per document
3. **LEFT JOIN allows missing data**: Parties without documents show 0 counts

## Deployment Steps

### 1. Pre-Deployment Validation
```bash
# Validate XML syntax
python3 -c "import xml.etree.ElementTree as ET; ET.parse('service.data.impl/src/main/resources/db-changelog-1.61.xml'); print('✓ XML Valid')"

# Review changeset
grep -A5 "1.61-create-party-summary" service.data.impl/src/main/resources/db-changelog-1.61.xml
```

### 2. Apply to Test Database
```bash
# Using Liquibase
liquibase --changeLogFile=service.data.impl/src/main/resources/db-changelog-1.61.xml update

# Or using Maven
mvn liquibase:update -Dliquibase.changeLogFile=service.data.impl/src/main/resources/db-changelog-1.61.xml
```

### 3. Verify View Creation
```sql
-- Check view exists
SELECT EXISTS (
    SELECT 1 FROM information_schema.views 
    WHERE table_name = 'view_riksdagen_party_summary'
);

-- Check column count (should be 52)
SELECT COUNT(*) 
FROM information_schema.columns 
WHERE table_name = 'view_riksdagen_party_summary';

-- Test query
SELECT party, total_documents, total_active 
FROM view_riksdagen_party_summary 
ORDER BY party 
LIMIT 5;
```

### 4. Data Quality Checks
```sql
-- Check for NULLs in key columns (should be 0)
SELECT 
    COUNT(*) FILTER (WHERE total_documents IS NULL) as null_docs,
    COUNT(*) FILTER (WHERE total_active IS NULL) as null_active,
    COUNT(*) FILTER (WHERE party IS NULL) as null_party
FROM view_riksdagen_party_summary;

-- Verify party counts match
SELECT COUNT(DISTINCT party) as party_count
FROM view_riksdagen_party_summary;

-- Sample data inspection
SELECT 
    party,
    total_documents,
    total_active,
    first_party_document,
    last_party_document
FROM view_riksdagen_party_summary
WHERE total_documents > 0
ORDER BY total_documents DESC
LIMIT 10;
```

## Rollback Plan

If issues occur:

### Option 1: Drop View
```sql
DROP VIEW IF EXISTS view_riksdagen_party_summary CASCADE;
```

### Option 2: Liquibase Rollback
```bash
liquibase rollback-count 1
```

### Option 3: Revert Changeset
Mark changeset as failed and exclude from future runs.

## Known Limitations

These columns have placeholder values (0) and need future implementation:
- `total_collaborative_motions`
- `total_follow_up_motions`
- `party_focused_members`
- `committee_focused_members`
- `individual_focused_members`
- `avg_collaboration_percentage`
- `highly_collaborative_members`

**Impact**: View will return 0 for these columns until enhanced in future changeset.

## Testing Checklist

- [x] XML syntax valid
- [x] SQL structure correct
- [x] 52 columns defined
- [x] Join path validated against existing views
- [x] NULL handling implemented
- [ ] Applied to test database
- [ ] View query executes successfully
- [ ] Data quality validated
- [ ] Performance acceptable (<1s for party summary)
- [ ] JPA entity query works

## References

- **Original Issue**: view_riksdagen_party_summary references non-existent `dd.party` column
- **Solution Pattern**: Based on `view_riksdagen_politician_document` (db-changelog-1.24.xml)
- **JPA Entity**: `ViewRiksdagenPartySummary` (52 columns, String PK)
- **Documentation**: 
  - `view-fix-summary.md` - Detailed fix explanation
  - `database-join-documentation.md` - Table relationship diagram
  - `view-validation-test.md` - Comprehensive validation results

## Support

If you encounter issues:
1. Check PostgreSQL logs for SQL errors
2. Verify all dependency views exist
3. Validate table structures match expected schema
4. Review join performance with EXPLAIN ANALYZE
5. Contact Stack Specialist for assistance

---

**Last Updated**: 2025-01-XX  
**Status**: ✅ Ready for Testing  
**Risk Level**: Low (rollback available, isolated view)
