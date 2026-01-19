# Database Table Relationship for Party Document Joins

## Table Join Path Diagram

```
document_data (dd)
    |
    | dd.id = dsc.document_document_status_con_0
    |
    v
document_status_container (dsc)
    |
    | dsc.hjid = dprc.hjid
    |
    v
document_person_reference_co_0 (dprc)
    |
    | dprc.hjid = dpr.document_person_reference_li_1
    |
    v
document_person_reference_da_0 (dpr)
    |
    |-- dpr.party_short_code (PARTY CODE!)
    |-- dpr.person_reference_id
    |-- dpr.reference_name
    `-- dpr.role_description
```

## Table Descriptions

### document_data
- **Purpose**: Core document information
- **Key Columns**:
  - `id` (PK) - Document identifier
  - `document_type` - Type of document (mot, bet, prop, etc.)
  - `made_public_date` - Publication date
  - `org` - Organization/Committee code
  - `label` - Document label/title
  - **NO `party` column** ❌

### document_status_container
- **Purpose**: Container linking documents to person references
- **Key Columns**:
  - `hjid` (PK) - Container identifier
  - `document_document_status_con_0` (FK) - Links to document_data.id
  - `document_person_reference_co_1` - Links to person reference collection

### document_person_reference_co_0
- **Purpose**: Collection of person references for a document
- **Key Columns**:
  - `hjid` (PK) - Collection identifier
  - Links to document_status_container via hjid

### document_person_reference_da_0
- **Purpose**: Individual person reference with party information
- **Key Columns**:
  - `hjid` (PK) - Person reference identifier
  - `party_short_code` ✅ - **PARTY CODE HERE!**
  - `person_reference_id` - Person identifier
  - `reference_name` - Person name
  - `role_description` - Role in document
  - `document_person_reference_li_1` (FK) - Links to collection

## SQL Join Pattern

```sql
FROM document_data dd
INNER JOIN document_status_container dsc 
    ON dd.id = dsc.document_document_status_con_0
INNER JOIN document_person_reference_co_0 dprc 
    ON dsc.hjid = dprc.hjid
INNER JOIN document_person_reference_da_0 dpr 
    ON dprc.hjid = dpr.document_person_reference_li_1
WHERE dpr.party_short_code IS NOT NULL
```

## Key Insights

1. **Party is NOT in document_data**: You must join through 3 intermediate tables
2. **Multiple people per document**: A document can have multiple person references
3. **Use DISTINCT**: To avoid duplicate counting due to multiple person references
4. **Filter NULL parties**: Some documents may not have party associations

## Usage Example

To get documents by party:
```sql
SELECT 
    dpr.party_short_code AS party,
    COUNT(DISTINCT dd.id) AS document_count
FROM document_data dd
INNER JOIN document_status_container dsc 
    ON dd.id = dsc.document_document_status_con_0
INNER JOIN document_person_reference_co_0 dprc 
    ON dsc.hjid = dprc.hjid
INNER JOIN document_person_reference_da_0 dpr 
    ON dprc.hjid = dpr.document_person_reference_li_1
WHERE dpr.party_short_code IS NOT NULL
GROUP BY dpr.party_short_code;
```

## Historical Context

This join pattern is used consistently throughout the application:
- `view_riksdagen_politician_document` (db-changelog-1.24.xml)
- Document summary views
- Person-to-document associations

The pattern was established early in the schema design and has been stable since v1.1.
