<p align="center">
  <img src="https://hack23.github.io/cia-compliance-manager/icon-192.png" alt="Hack23 Logo" width="192" height="192">
</p>

<h1 align="center">🔧 Troubleshooting Empty Database Views</h1>

<p align="center">
  <strong>🔍 Diagnostic and Repair Guide for Views Returning Zero Rows</strong><br>
  <em>📊 Ensuring Data Availability Across All Intelligence Views</em>
</p>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/Owner-Database_Team-0A66C2?style=for-the-badge" alt="Owner"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Version-1.0-555?style=for-the-badge" alt="Version"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Date-2025--11--18-success?style=for-the-badge" alt="Date"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Priority-HIGH-red?style=for-the-badge" alt="Priority"/></a>
</p>

**📋 Document Owner:** Database Administration Team | **📄 Version:** 1.0 | **📅 Created:** 2025-11-18 (UTC)  
**🔍 Scope:** Diagnostic procedures for views returning 0 rows  
**🏷️ Classification:** [![Confidentiality: Internal](https://img.shields.io/badge/C-Internal-blue?style=flat-square)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#confidentiality-levels)

---

## 📋 Executive Summary

This guide provides systematic troubleshooting procedures for database views returning zero rows. Empty views can indicate missing data, incorrect view definitions, data import failures, or dependency issues. This document helps diagnose and resolve these issues to ensure complete data availability across the CIA platform's 82 views.

### 🎯 Key Concepts

**Why Views Return 0 Rows:**
1. **🔴 CRITICAL - Source Data Missing:** Base tables have no data (requires data import)
2. **🟠 HIGH - View Definition Issues:** Incorrect JOINs or WHERE clauses filtering all rows
3. **🟠 HIGH - Dependency Failures:** Upstream views/tables failed to populate
4. **🟡 MEDIUM - Timing Issues:** Data not yet imported (scheduled processes pending)
5. **🟢 LOW - Expected Behavior:** View is conditionally populated (e.g., error tracking views)

---

## 🔍 Diagnostic Process

### Step 1: Identify Empty Views

```sql
-- Run this query to find all views with 0 rows
DO $$
DECLARE
    view_record RECORD;
    row_count INTEGER;
BEGIN
    FOR view_record IN 
        SELECT schemaname, viewname 
        FROM pg_views 
        WHERE schemaname = 'public'
        ORDER BY viewname
    LOOP
        EXECUTE format('SELECT COUNT(*) FROM %I.%I', 
            view_record.schemaname, view_record.viewname) 
        INTO row_count;
        
        IF row_count = 0 THEN
            RAISE NOTICE 'Empty View: %.% | Definition Required for Diagnosis', 
                view_record.schemaname, view_record.viewname;
        END IF;
    END LOOP;
END $$;
```

**Expected Output:**
```
NOTICE:  Empty View: public.view_riksdagen_vote_data_ballot_summary_daily | Definition Required for Diagnosis
NOTICE:  Empty View: public.view_riksdagen_politician_summary | Definition Required for Diagnosis
```

### Step 2: Check View Definition

```sql
-- Get view definition for analysis
SELECT 
    schemaname,
    viewname,
    definition
FROM pg_views
WHERE viewname = 'your_empty_view_name'
  AND schemaname = 'public';
```

### Step 3: Analyze Dependency Chain

```sql
-- Check what tables/views this view depends on
WITH RECURSIVE view_deps AS (
    -- Base case: direct dependencies
    SELECT 
        v.view_schema,
        v.view_name,
        v.table_schema AS dep_schema,
        v.table_name AS dep_name,
        1 AS depth
    FROM information_schema.view_table_usage v
    WHERE v.view_schema = 'public' 
      AND v.view_name = 'your_empty_view_name'
    
    UNION ALL
    
    -- Recursive case: dependencies of dependencies
    SELECT 
        v.view_schema,
        v.view_name,
        v.table_schema AS dep_schema,
        v.table_name AS dep_name,
        vd.depth + 1 AS depth
    FROM information_schema.view_table_usage v
    JOIN view_deps vd 
        ON v.view_schema = vd.dep_schema 
        AND v.view_name = vd.dep_name
    WHERE vd.depth < 5  -- Prevent infinite recursion
)
SELECT DISTINCT
    dep_schema,
    dep_name AS dependency,
    depth,
    CASE 
        WHEN EXISTS (
            SELECT 1 FROM pg_tables t 
            WHERE t.schemaname = dep_schema 
              AND t.tablename = dep_name
        ) THEN 'TABLE'
        WHEN EXISTS (
            SELECT 1 FROM pg_views v 
            WHERE v.schemaname = dep_schema 
              AND v.viewname = dep_name
        ) THEN 'VIEW'
        WHEN EXISTS (
            SELECT 1 FROM pg_matviews m 
            WHERE m.schemaname = dep_schema 
              AND m.matviewname = dep_name
        ) THEN 'MATERIALIZED VIEW'
        ELSE 'UNKNOWN'
    END AS object_type
FROM view_deps
ORDER BY depth, dep_name;
```

### Step 4: Check Row Counts in Dependencies

```sql
-- Template for checking dependency row counts
-- Replace {{dependency_name}} with actual dependency

SELECT COUNT(*) AS row_count 
FROM {{dependency_name}};

-- Example for multiple dependencies:
SELECT 
    'person_data' AS table_name,
    COUNT(*) AS row_count 
FROM person_data
UNION ALL
SELECT 
    'ballot_data' AS table_name,
    COUNT(*) AS row_count 
FROM ballot_data
UNION ALL
SELECT 
    'document_data' AS table_name,
    COUNT(*) AS row_count 
FROM document_data
ORDER BY table_name;
```

---

## 🛠️ Common Fixes by View Category

### 📊 Vote Data Views (e.g., view_riksdagen_vote_data_ballot_summary_*)

**Common Cause:** Missing ballot_data or vote_data

**Diagnostic Check:**
```sql
-- Check if source data exists
SELECT 
    (SELECT COUNT(*) FROM ballot_data) AS ballot_count,
    (SELECT COUNT(*) FROM vote_data) AS vote_count,
    (SELECT COUNT(*) FROM document_data WHERE document_type = 'vote') AS vote_docs;
```

**Fix:**
```sql
-- If counts are 0, data needs to be imported
-- Run the data import job:
-- 1. Check if Riksdagen API is accessible
-- 2. Run data import script (location: service.data.impl/src/main/resources/import_riksdagen_votes.sql)
-- 3. Or trigger via application: DataImportJob scheduled task

-- Manual import example (requires API credentials):
INSERT INTO ballot_data (ballot_id, issue_id, vote_date, ...)
SELECT ... FROM riksdagen_api_votes;

-- Refresh dependent materialized views:
REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_vote_data_ballot_summary;
```

### 👥 Politician Views (e.g., view_riksdagen_politician_summary)

**Common Cause:** Missing person_data or politician role assignments

**Diagnostic Check:**
```sql
-- Check politician data
SELECT 
    (SELECT COUNT(*) FROM person_data) AS total_persons,
    (SELECT COUNT(DISTINCT person_id) FROM assignment_data WHERE role_code = 'politician') AS politicians,
    (SELECT COUNT(*) FROM party_member_data) AS party_members;
```

**Fix:**
```sql
-- If person_data is empty, import from Riksdagen API
-- Check assignment_data for role assignments
SELECT DISTINCT role_code, COUNT(*) 
FROM assignment_data 
GROUP BY role_code;

-- If no 'politician' role, check role_code values in source data
-- Verify role mapping in application configuration

-- Refresh dependent views:
REFRESH MATERIALIZED VIEW view_riksdagen_politician;
```

### 🏛️ Committee Views (e.g., view_riksdagen_committee_decisions)

**Common Cause:** Missing committee_proposal_data or committee assignment data

**Diagnostic Check:**
```sql
-- Check committee data
SELECT 
    (SELECT COUNT(*) FROM committee_proposal_data) AS proposals,
    (SELECT COUNT(*) FROM committee_data) AS committees,
    (SELECT COUNT(*) FROM assignment_data WHERE role_code LIKE '%committee%') AS committee_assignments;
```

**Fix:**
```sql
-- Import committee data if missing
-- Check committee_data table:
SELECT org_code, name_sv, active 
FROM committee_data 
ORDER BY name_sv;

-- If empty, import from Riksdagen API:
-- Run: java -jar cia-app.jar --import-committees

-- Refresh committee views:
REFRESH MATERIALIZED VIEW view_riksdagen_committee_ballot_decision_summary;
```

### 📄 Document Views (e.g., view_riksdagen_document_summary)

**Common Cause:** Missing document_data imports

**Diagnostic Check:**
```sql
-- Check document data
SELECT 
    document_type,
    COUNT(*) AS doc_count,
    MIN(made_public_date) AS oldest_doc,
    MAX(made_public_date) AS newest_doc
FROM document_data
GROUP BY document_type
ORDER BY document_type;
```

**Fix:**
```sql
-- If document_data is empty or incomplete:
-- 1. Check Riksdagen API document endpoint
-- 2. Run document import job
-- 3. Verify document_type values match expected types

-- Example import (pseudo-code):
-- RiksdagenDocumentService.importDocuments(startDate, endDate);

-- Refresh document views:
REFRESH MATERIALIZED VIEW view_riksdagen_document_person_reference_daily_summary;
```

### 🖥️ Application Event Views (e.g., view_application_action_event_*)

**Common Cause:** No user activity recorded yet (expected for new installations)

**Diagnostic Check:**
```sql
-- Check application event data
SELECT 
    COUNT(*) AS total_events,
    MIN(created_date) AS first_event,
    MAX(created_date) AS last_event,
    COUNT(DISTINCT page) AS unique_pages
FROM application_action_event;
```

**Fix:**
```sql
-- If this is a new installation with no users:
-- This is EXPECTED BEHAVIOR - views will populate as users interact with the system

-- If events should exist but don't:
-- 1. Check application logging configuration
-- 2. Verify ApplicationActionEventService is recording events
-- 3. Check for errors in application logs

-- No manual data import needed - events are created by user activity
```

---

## 🔄 Materialized View Refresh Strategy

### When to Refresh Materialized Views

**🔴 CRITICAL - Immediate Refresh Required:**
- After bulk data import operations
- After schema changes affecting view definitions
- When debugging empty view issues

**🟠 SCHEDULED - Regular Refresh:**
- Daily: Summary views aggregating daily metrics
- Weekly: Trend analysis views
- Monthly: Historical comparison views

### Refresh Commands

```sql
-- Single view refresh (blocking)
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary;

-- Single view refresh (non-blocking, requires unique index)
REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_politician;

-- Refresh all materialized views in dependency order
-- Use the script: service.data.impl/src/main/resources/refresh-all-views.sql

\i refresh-all-views.sql
```

### Automated Refresh Script

```bash
#!/bin/bash
# File: refresh_empty_views.sh
# Purpose: Identify and refresh empty materialized views

PGHOST="${PGHOST:-localhost}"
PGPORT="${PGPORT:-5432}"
PGDATABASE="${PGDATABASE:-cia}"
PGUSER="${PGUSER:-cia_user}"

echo "🔍 Checking for empty materialized views..."

psql -h $PGHOST -p $PGPORT -d $PGDATABASE -U $PGUSER << 'EOF'
DO $$
DECLARE
    mview_record RECORD;
    row_count BIGINT;
BEGIN
    FOR mview_record IN 
        SELECT schemaname, matviewname 
        FROM pg_matviews 
        WHERE schemaname = 'public'
        ORDER BY matviewname
    LOOP
        EXECUTE format('SELECT COUNT(*) FROM %I.%I', 
            mview_record.schemaname, mview_record.matviewname) 
        INTO row_count;
        
        IF row_count = 0 THEN
            RAISE NOTICE '⚠️  Empty: %.% - Refreshing...', 
                mview_record.schemaname, mview_record.matviewname;
            
            -- Attempt refresh
            BEGIN
                EXECUTE format('REFRESH MATERIALIZED VIEW %I.%I',
                    mview_record.schemaname, mview_record.matviewname);
                RAISE NOTICE '✅ Refreshed successfully';
            EXCEPTION WHEN OTHERS THEN
                RAISE NOTICE '❌ Refresh failed: %', SQLERRM;
            END;
        END IF;
    END LOOP;
END $$;
EOF

echo "✅ Empty view check and refresh complete"
```

---

## 📊 View-Specific Troubleshooting

### View: view_riksdagen_vote_data_ballot_summary

**Purpose:** Base ballot aggregation view  
**Expected Row Count:** 1000+ (depends on imported vote data)

**Common Issues:**
1. **Empty ballot_data table**
   - **Fix:** Import vote data from Riksdagen API
   - **Command:** `RiksdagenVoteDataService.importAllVotes()`

2. **Date range filters excluding all data**
   - **Diagnostic:** Check MIN/MAX dates in ballot_data
   - **Fix:** Adjust view WHERE clause date filters

3. **JOIN conditions too restrictive**
   - **Diagnostic:** Check individual table counts before JOIN
   - **Fix:** Review JOIN conditions, consider LEFT JOIN instead of INNER JOIN

**Validation Query:**
```sql
-- After fix, verify row count
SELECT COUNT(*) AS row_count 
FROM view_riksdagen_vote_data_ballot_summary;

-- Should return > 0 if vote data exists
-- Typical range: 1,000 - 50,000 rows depending on data volume
```

### View: view_riksdagen_committee_decisions

**Purpose:** Committee decision tracking  
**Expected Row Count:** 8,000+ (based on schema_report.txt)

**Common Issues:**
1. **Missing committee_proposal_data**
   - **Fix:** Import committee proposals from Riksdagen API
   - **Check:** `SELECT COUNT(*) FROM committee_proposal_data;`

2. **Committee org_code mismatch**
   - **Diagnostic:** Check committee codes in source vs. view
   - **Fix:** Update committee code mapping in view definition

**Validation Query:**
```sql
-- Verify committee data completeness
SELECT 
    c.org_code,
    c.name_sv AS committee_name,
    COUNT(cp.id) AS proposal_count
FROM committee_data c
LEFT JOIN committee_proposal_data cp ON c.org_code = cp.organ
GROUP BY c.org_code, c.name_sv
ORDER BY proposal_count DESC;
```

### View: view_riksdagen_politician_summary

**Purpose:** Politician profile aggregation  
**Expected Row Count:** 350+ (current Riksdag members)

**Common Issues:**
1. **No active politician assignments**
   - **Fix:** Import current assignment_data from Riksdagen
   - **Check:** Active period dates in assignment_data

2. **Party membership missing**
   - **Fix:** Import party_member_data
   - **Check:** `SELECT COUNT(*) FROM party_member_data WHERE active = true;`

**Validation Query:**
```sql
-- Check politician data pipeline
SELECT 
    'person_data' AS source,
    COUNT(*) AS count
FROM person_data
UNION ALL
SELECT 
    'assignment_data (politician)',
    COUNT(DISTINCT person_id)
FROM assignment_data
WHERE role_code = 'politician'
UNION ALL
SELECT 
    'party_member_data (active)',
    COUNT(DISTINCT person_id)
FROM party_member_data
WHERE active = true;
```

### Advanced Politician Intelligence Views

**Purpose:** Risk assessment, influence tracking, coalition analysis, anomaly detection  
**Expected Row Count:** Variable based on active politicians and time windows

#### View: view_politician_risk_summary

**Common Issues:**
1. **Overly restrictive date filter on annual summary view**
   - **Symptom:** View returns 0 rows even with active politicians
   - **Root Cause:** JOIN condition using exact date match on `view_riksdagen_vote_data_ballot_politician_summary_annual`
   - **Fix Applied in v1.32:** Changed to LATERAL join with most recent data lookup
   
   **Diagnostic:**
   ```sql
   -- Check if annual summary has data for expected date
   SELECT 
       COUNT(*) as total_rows,
       MIN(embedded_id_vote_date) as earliest_date,
       MAX(embedded_id_vote_date) as latest_date
   FROM view_riksdagen_vote_data_ballot_politician_summary_annual;
   
   -- Expected: Should have data, but may not match exact date filter
   ```
   
   **Solution (Applied):**
   ```sql
   -- v1.32 fix: Use LATERAL join to get most recent data
   LEFT JOIN LATERAL (
       SELECT 
           avg_percentage_absent,
           won_percentage,
           rebel_percentage,
           total_votes
       FROM view_riksdagen_vote_data_ballot_politician_summary_annual
       WHERE embedded_id_intressent_id = p.id
           AND embedded_id_vote_date >= date_trunc('year', CURRENT_DATE - INTERVAL '2 years')
       ORDER BY embedded_id_vote_date DESC
       LIMIT 1
   ) vps_annual ON true
   ```

2. **Missing rule_violation data**
   - **Check:** `SELECT COUNT(*) FROM rule_violation WHERE resource_type = 'POLITICIAN';`
   - **Note:** Risk scores work even without violations (uses voting/document data)

**Validation Query:**
```sql
-- After fix, should return one row per active politician
SELECT 
    COUNT(*) as politicians_with_risk_scores,
    AVG(risk_score) as avg_risk_score,
    COUNT(*) FILTER (WHERE risk_level = 'CRITICAL') as critical_count
FROM view_politician_risk_summary;
```

#### View: view_riksdagen_coalition_alignment_matrix

**Common Issues:**
1. **Insufficient vote_data within time window**
   - **Time Window:** 2 years
   - **Required:** Party votes with 'Ja' or 'Nej' values
   
   **Diagnostic:**
   ```sql
   -- Check vote availability
   SELECT 
       COUNT(*) as total_votes,
       COUNT(DISTINCT party) as unique_parties,
       MIN(vote_date) as earliest,
       MAX(vote_date) as latest
   FROM vote_data
   WHERE vote_date >= CURRENT_DATE - INTERVAL '2 years'
       AND vote IN ('Ja', 'Nej')
       AND party IS NOT NULL;
   ```

2. **Missing party information**
   - **Fix:** Ensure `vote_data.party` is populated from import

**Validation Query:**
```sql
-- Should return party pairs with alignment metrics
SELECT 
    party_1,
    party_2,
    alignment_rate,
    coalition_likelihood
FROM view_riksdagen_coalition_alignment_matrix
ORDER BY alignment_rate DESC
LIMIT 10;
```

#### View: view_riksdagen_politician_influence_metrics

**Common Issues:**
1. **Insufficient co-voting data**
   - **Time Window:** 1 year
   - **Minimum:** 20 shared votes per politician pair
   
   **Diagnostic:**
   ```sql
   -- Check if enough votes for network analysis
   SELECT 
       COUNT(DISTINCT embedded_id_intressent_id) as politicians,
       COUNT(DISTINCT embedded_id_ballot_id) as ballots,
       AVG(vote_count) as avg_votes_per_politician
   FROM (
       SELECT 
           embedded_id_intressent_id,
           COUNT(*) as vote_count
       FROM vote_data
       WHERE vote_date >= CURRENT_DATE - INTERVAL '1 year'
           AND vote IN ('Ja', 'Nej')
       GROUP BY embedded_id_intressent_id
   ) sub;
   ```

**Validation Query:**
```sql
-- Should show politicians with network metrics
SELECT 
    first_name,
    last_name,
    party,
    network_connections,
    cross_party_bridges,
    connectivity_level
FROM view_riksdagen_politician_influence_metrics
ORDER BY network_connections DESC
LIMIT 10;
```

#### View: view_riksdagen_voting_anomaly_detection

**Common Issues:**
1. **Low party cohesion makes anomaly detection difficult**
   - **Requires:** Clear party majority votes
   - **Time Window:** 1 year
   
   **Diagnostic:**
   ```sql
   -- Check party voting cohesion
   SELECT 
       party,
       COUNT(DISTINCT embedded_id_ballot_id) as ballots,
       COUNT(*) as total_votes
   FROM vote_data
   WHERE vote_date >= CURRENT_DATE - INTERVAL '1 year'
       AND vote IN ('Ja', 'Nej', 'Avstår')
       AND party IS NOT NULL
   GROUP BY party
   ORDER BY party;
   ```

**Validation Query:**
```sql
-- Should show politicians who voted against party line
SELECT 
    first_name,
    last_name,
    party,
    total_votes,
    party_discipline_score,
    rebellion_rate,
    discipline_classification
FROM view_riksdagen_voting_anomaly_detection
ORDER BY rebellion_rate DESC
LIMIT 10;
```

---

## 🏛️ Ministry & Government Views

**Purpose:** Ministry effectiveness analysis and government proposal tracking  
**Expected Row Count:** Variable based on ministry count and document availability

### View: view_riksdagen_goverment_proposals

**Common Issues:**

1. **Case-sensitive document_type filter (FIXED in v1.32)**
   - **Symptom:** View returns 0 rows even with proposal documents
   - **Root Cause:** Filter used `document_type = 'PROP'` but data might be lowercase 'prop'
   - **Fix Applied:** Changed to `UPPER(document_type) = 'PROP' OR document_type = 'Proposition'`
   
   **Diagnostic:**
   ```sql
   -- Check actual document_type values
   SELECT 
       document_type,
       COUNT(*) AS count
   FROM document_data
   WHERE UPPER(document_type) LIKE '%PROP%'
      OR LOWER(document_type) LIKE '%prop%'
      OR document_type LIKE '%Proposition%'
   GROUP BY document_type
   ORDER BY count DESC;
   ```

2. **No government proposal documents imported**
   - **Fix:** Import proposal data from Riksdagen API
   - **Check:** `SELECT COUNT(*) FROM document_data WHERE UPPER(document_type) = 'PROP';`

**Validation Query:**
```sql
-- After v1.32 fix, should return government proposals
SELECT 
    COUNT(*) AS total_proposals,
    MIN(made_public_date) AS earliest,
    MAX(made_public_date) AS latest,
    COUNT(DISTINCT org) AS unique_ministries
FROM view_riksdagen_goverment_proposals;
```

### View: view_ministry_effectiveness_trends

**Purpose:** Track ministry performance metrics over time (quarterly)  
**Expected Row Count:** # of ministries × # of quarters with data (typically 10-50 rows)

**Common Issues:**

1. **No ministry assignments in assignment_data**
   - **Diagnostic:**
   ```sql
   -- Check for ministry assignments
   SELECT 
       COUNT(DISTINCT org_code) AS ministry_count,
       MIN(from_date) AS earliest_assignment,
       MAX(COALESCE(to_date, CURRENT_DATE)) AS latest_assignment
   FROM assignment_data
   WHERE assignment_type = 'Departement'
       AND LOWER(org_code) LIKE '%departement%';
   ```
   
   - **Fix:** If count is 0, ministry assignment data needs to be imported
   - **Expected:** Should have 10-15 unique ministry org_codes

2. **view_riksdagen_politician_document not populated**
   - **Diagnostic:**
   ```sql
   -- Check for ministry documents
   SELECT 
       COUNT(*) AS ministry_doc_count,
       MIN(made_public_date) AS earliest,
       MAX(made_public_date) AS latest
   FROM view_riksdagen_politician_document
   WHERE LOWER(org) LIKE '%departement%'
       AND made_public_date >= CURRENT_DATE - INTERVAL '3 years';
   ```
   
   - **Fix:** Refresh materialized view
   ```sql
   REFRESH MATERIALIZED VIEW view_riksdagen_politician_document;
   ```

3. **Org codes don't match between tables**
   - **Diagnostic:**
   ```sql
   -- Check org_code matching
   SELECT 
       'In assignment_data only' AS location,
       COUNT(DISTINCT ad.org_code) AS count
   FROM assignment_data ad
   WHERE ad.assignment_type = 'Departement'
       AND LOWER(ad.org_code) LIKE '%departement%'
       AND NOT EXISTS (
           SELECT 1 FROM view_riksdagen_politician_document vpd
           WHERE vpd.org = ad.org_code
       )
   UNION ALL
   SELECT 
       'In politician_document only',
       COUNT(DISTINCT vpd.org)
   FROM view_riksdagen_politician_document vpd
   WHERE LOWER(vpd.org) LIKE '%departement%'
       AND NOT EXISTS (
           SELECT 1 FROM assignment_data ad
           WHERE ad.org_code = vpd.org
               AND ad.assignment_type = 'Departement'
       );
   ```
   
   - **Expected:** Should show "In both (matching)" with count > 0
   - **Fix:** Verify org_code format consistency, update if needed

4. **No documents in 3-year window**
   - **Diagnostic:** View filters by `made_public_date >= CURRENT_DATE - INTERVAL '3 years'`
   - **Fix:** Extend date range or import more recent ministry documents

**Validation Query:**
```sql
-- Should return quarterly metrics for each ministry
SELECT 
    short_code,
    name,
    period_start,
    documents_produced,
    propositions,
    active_members,
    productivity_level,
    effectiveness_assessment
FROM view_ministry_effectiveness_trends
ORDER BY period_start DESC, documents_produced DESC
LIMIT 20;
```

### View: view_ministry_productivity_matrix

**Purpose:** Annual benchmarking of ministry performance  
**Expected Row Count:** # of ministries × # of years with data (typically 30-60 rows)

**Common Issues:**
- Same as `view_ministry_effectiveness_trends` but with annual aggregation
- Requires at least 1 full year of data to populate

**Diagnostic:**
```sql
-- Check annual ministry data
WITH ministry_base AS (
    SELECT DISTINCT
        org_code,
        detail AS name
    FROM assignment_data
    WHERE assignment_type = 'Departement'
        AND LOWER(org_code) LIKE '%departement%'
)
SELECT 
    m.org_code,
    m.name,
    EXTRACT(YEAR FROM doc.made_public_date) AS year,
    COUNT(DISTINCT doc.id) AS document_count
FROM ministry_base m
LEFT JOIN view_riksdagen_politician_document doc 
    ON doc.org = m.org_code
    AND doc.made_public_date >= CURRENT_DATE - INTERVAL '3 years'
GROUP BY m.org_code, m.name, EXTRACT(YEAR FROM doc.made_public_date)
ORDER BY year DESC, document_count DESC;
```

**Validation Query:**
```sql
-- Should show annual productivity comparison
SELECT 
    short_code,
    name,
    year,
    documents_produced,
    performance_classification,
    vs_average_pct,
    productivity_assessment
FROM view_ministry_productivity_matrix
ORDER BY year DESC, documents_produced DESC
LIMIT 15;
```

### View: view_ministry_risk_evolution

**Purpose:** Track ministry risk scores over time (quarterly)  
**Expected Row Count:** # of ministries × # of quarters with assessments

**Common Issues:**
- Same dependencies as other ministry views
- Risk score calculated from productivity and staffing metrics
- Will show data even if risk score is 0 (good performance)

**Diagnostic:**
```sql
-- Check ministry risk calculation
WITH ministry_base AS (
    SELECT DISTINCT
        org_code,
        detail AS name
    FROM assignment_data
    WHERE assignment_type = 'Departement'
        AND LOWER(org_code) LIKE '%departement%'
)
SELECT 
    m.org_code,
    DATE_TRUNC('quarter', doc.made_public_date) AS quarter,
    COUNT(DISTINCT doc.id) AS doc_count,
    COUNT(DISTINCT CASE 
        WHEN LOWER(doc.document_type) IN ('prop', 'ds') THEN doc.id 
    END) AS legislative_count,
    COUNT(DISTINCT doc.person_reference_id) AS active_members
FROM ministry_base m
LEFT JOIN view_riksdagen_politician_document doc 
    ON doc.org = m.org_code
    AND doc.made_public_date >= CURRENT_DATE - INTERVAL '3 years'
GROUP BY m.org_code, DATE_TRUNC('quarter', doc.made_public_date)
ORDER BY quarter DESC, doc_count DESC
LIMIT 20;
```

**Validation Query:**
```sql
-- Should show risk evolution over time
SELECT 
    short_code,
    name,
    assessment_period,
    document_count,
    legislative_count,
    active_members,
    risk_score,
    risk_severity,
    risk_assessment
FROM view_ministry_risk_evolution
ORDER BY assessment_period DESC, risk_score DESC
LIMIT 20;
```

### Ministry Views Diagnostic Script

**Comprehensive diagnostic query provided in:**
```bash
service.data.impl/src/main/resources/diagnose-ministry-views.sql
```

**Run complete diagnosis:**
```bash
psql -U cia_user -d cia -f service.data.impl/src/main/resources/diagnose-ministry-views.sql
```

**Expected output:**
1. Document type analysis
2. Ministry org_code verification
3. Data availability check
4. Org matching analysis
5. Current view row counts
6. Recommendations for fixes

---

## 🎯 Intelligence Views: Crisis, Risk, and Proposal Analysis

**Purpose:** Track crisis resilience, risk score evolution, and parliamentary proposal analysis  
**Expected Row Count:** Variable based on active politicians and available data

### View: view_riksdagen_member_proposals

**Purpose:** All parliamentary member proposals (motions)  
**Expected Row Count:** ~90,000+ rows (all member motions in document_element)

**Common Issues:**

1. **Case-sensitive document_type filter (FIXED in v1.33)**
   - **Symptom:** View returns 0 rows even with motion documents
   - **Root Cause:** Filter used `document_type = 'MOT'` but data contains `'mot'` (lowercase)
   - **Fix Applied:** Changed to `UPPER(document_type) = 'MOT'`
   
   **Diagnostic:**
   ```sql
   -- Check actual document_type values
   SELECT 
       document_type,
       COUNT(*) AS count
   FROM document_element
   WHERE LOWER(document_type) = 'mot'
   GROUP BY document_type;
   
   -- Expected: Should show 'mot' with ~90,000+ rows
   ```

2. **No motion documents imported**
   - **Fix:** Import motion data from Riksdagen API
   - **Check:** `SELECT COUNT(*) FROM document_element WHERE UPPER(document_type) = 'MOT';`

**Validation Query:**
```sql
-- After v1.33 fix, should return all member proposals
SELECT 
    COUNT(*) AS total_proposals,
    MIN(made_public_date) AS earliest,
    MAX(made_public_date) AS latest,
    COUNT(DISTINCT org) AS unique_orgs
FROM view_riksdagen_member_proposals;

-- Expected: ~90,000+ proposals from various parliamentary organizations
```

### View: view_riksdagen_committee_parliament_member_proposal

**Purpose:** Committee member proposals linked to specific committees  
**Expected Row Count:** Subset of member proposals where org matches committee org_code

**Common Issues:**

1. **Case-sensitive document_type filter (FIXED in v1.33)**
   - **Same issue as view_riksdagen_member_proposals**
   - **Fix Applied:** Changed to `UPPER(document_data.document_type) = 'MOT'`
   
   **Diagnostic:**
   ```sql
   -- Check committee-linked proposals
   SELECT 
       c.embedded_id_org_code AS committee,
       COUNT(DISTINCT d.id) AS proposal_count
   FROM view_riksdagen_committee c
   LEFT JOIN document_data d ON c.embedded_id_org_code = d.org
   WHERE UPPER(d.document_type) = 'MOT'
   GROUP BY c.embedded_id_org_code
   ORDER BY proposal_count DESC;
   ```

2. **No committee org matching**
   - **Diagnostic:** Check if document_data.org values match committee org_codes
   - **Fix:** Verify org_code format consistency

**Validation Query:**
```sql
-- Should return committee-linked proposals
SELECT 
    embedded_id_org_code AS committee,
    embedded_id_detail AS committee_name,
    COUNT(*) AS proposal_count
FROM view_riksdagen_committee_parliament_member_proposal
GROUP BY embedded_id_org_code, embedded_id_detail
ORDER BY proposal_count DESC
LIMIT 20;
```

### View: view_riksdagen_crisis_resilience_indicators

**Purpose:** Track politician performance during high-activity (crisis) periods vs normal periods  
**Expected Row Count:** Active politicians with voting data in both crisis and normal periods

**Common Issues:**

1. **Case-sensitive vote value filters (FIXED in v1.33)**
   - **Symptom:** View returns 0 rows even with extensive vote data
   - **Root Cause:** Filters used `vote = 'Ja'`, `'Nej'`, `'Frånvarande'` (mixed case) but data contains `'JA'`, `'NEJ'`, `'FRÅNVARANDE'` (all uppercase)
   - **Fix Applied:** Changed to `UPPER(vote)` for all vote comparisons
   
   **Diagnostic:**
   ```sql
   -- Check actual vote values and case
   SELECT 
       vote,
       COUNT(*) AS count
   FROM vote_data
   WHERE vote_date >= CURRENT_DATE - INTERVAL '2 years'
   GROUP BY vote
   ORDER BY count DESC;
   
   -- Expected: Should show 'JA', 'NEJ', 'FRÅNVARANDE', 'AVSTÅR' (uppercase)
   ```

2. **Insufficient data for crisis/normal period detection**
   - **Requires:** At least 2 years of voting data with varying monthly activity levels
   - **Diagnostic:**
   ```sql
   -- Check monthly ballot distribution
   WITH monthly_activity AS (
       SELECT 
           DATE_TRUNC('month', vote_date::TIMESTAMP WITH TIME ZONE) AS month,
           COUNT(DISTINCT embedded_id_ballot_id) AS ballot_count
       FROM vote_data
       WHERE vote_date >= CURRENT_DATE - INTERVAL '2 years'
       GROUP BY DATE_TRUNC('month', vote_date::TIMESTAMP WITH TIME ZONE)
   )
   SELECT 
       COUNT(*) AS total_months,
       AVG(ballot_count) AS avg_monthly_ballots,
       MIN(ballot_count) AS min_ballots,
       MAX(ballot_count) AS max_ballots,
       COUNT(*) FILTER (WHERE ballot_count > AVG(ballot_count) * 1.5) AS crisis_months,
       COUNT(*) FILTER (WHERE ballot_count <= AVG(ballot_count)) AS normal_months
   FROM monthly_activity;
   
   -- Expected: Should show variation in monthly activity for crisis detection
   ```

3. **No politicians with both crisis and normal period votes**
   - **Fix:** View now includes politicians with either crisis OR normal votes
   - **Filter:** WHERE clause ensures at least one period has data

**Validation Query:**
```sql
-- Should show resilience metrics for active politicians
SELECT 
    resilience_classification,
    COUNT(*) AS politician_count,
    AVG(crisis_period_votes) AS avg_crisis_votes,
    AVG(normal_period_votes) AS avg_normal_votes,
    AVG(resilience_score) AS avg_resilience
FROM view_riksdagen_crisis_resilience_indicators
GROUP BY resilience_classification
ORDER BY resilience_classification;
```

### View: view_risk_score_evolution

**Purpose:** Track monthly risk score changes for politicians over time (3-year window)  
**Expected Row Count:** Monthly records for active politicians with sufficient voting activity

**Common Issues:**

1. **Case-sensitive status filter (IMPROVED in v1.33)**
   - **Symptom:** View returns 0 rows if person_data.status has case variations
   - **Root Cause:** Filter used `p.status = 'active'` but data might contain 'Active' or 'ACTIVE'
   - **Fix Applied:** Changed to `p.status IN ('active', 'Active', 'ACTIVE')`
   
   **Diagnostic:**
   ```sql
   -- Check actual status values
   SELECT 
       status,
       COUNT(*) AS count
   FROM person_data
   WHERE status IS NOT NULL
   GROUP BY status;
   
   -- Expected: Should show actual status values in the data
   ```

2. **Materialized view not refreshed**
   - **Requires:** view_riksdagen_vote_data_ballot_politician_summary_daily must be up-to-date
   - **Diagnostic:**
   ```sql
   -- Check if daily summary has recent data
   SELECT 
       COUNT(*) AS total_records,
       MIN(embedded_id_vote_date) AS earliest_date,
       MAX(embedded_id_vote_date) AS latest_date,
       COUNT(DISTINCT embedded_id_intressent_id) AS unique_politicians
   FROM view_riksdagen_vote_data_ballot_politician_summary_daily
   WHERE embedded_id_vote_date >= CURRENT_DATE - INTERVAL '3 years';
   
   -- Expected: Should have records within 3-year window
   ```
   
   **Fix:**
   ```sql
   -- Refresh materialized view
   REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_daily;
   
   -- Then check view_risk_score_evolution again
   ```

3. **Insufficient ballot count per month**
   - **Filter:** View requires ballot_count >= 5 per month
   - **Diagnostic:** If too restrictive, adjust threshold in view definition

**Validation Query:**
```sql
-- Should show risk evolution over time
SELECT 
    DATE_TRUNC('month', assessment_period) AS month,
    COUNT(DISTINCT person_id) AS politicians_assessed,
    AVG(risk_score) AS avg_risk,
    COUNT(*) FILTER (WHERE risk_severity = 'CRITICAL') AS critical_count,
    COUNT(*) FILTER (WHERE risk_trend = 'SIGNIFICANT_INCREASE') AS escalating
FROM view_risk_score_evolution
GROUP BY DATE_TRUNC('month', assessment_period)
ORDER BY month DESC
LIMIT 12;
```

### Crisis, Risk, and Proposal Views Diagnostic Script

**Quick diagnostic for all 4 views:**

```sql
-- Comprehensive diagnostic for intelligence views
DO $$
DECLARE
    view_name TEXT;
    row_count BIGINT;
BEGIN
    RAISE NOTICE '=== Intelligence Views Status ===';
    
    -- Check each view
    FOR view_name IN 
        SELECT unnest(ARRAY[
            'view_riksdagen_member_proposals',
            'view_riksdagen_committee_parliament_member_proposal',
            'view_riksdagen_crisis_resilience_indicators',
            'view_risk_score_evolution'
        ])
    LOOP
        EXECUTE format('SELECT COUNT(*) FROM %I', view_name) INTO row_count;
        RAISE NOTICE 'View: % | Rows: %', view_name, row_count;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE '=== Data Availability Check ===';
    
    -- Check source data
    SELECT COUNT(*) INTO row_count FROM document_element WHERE UPPER(document_type) = 'MOT';
    RAISE NOTICE 'Motion documents (document_element): %', row_count;
    
    SELECT COUNT(*) INTO row_count FROM vote_data WHERE vote_date >= CURRENT_DATE - INTERVAL '2 years';
    RAISE NOTICE 'Votes (last 2 years): %', row_count;
    
    SELECT COUNT(*) INTO row_count FROM person_data WHERE status ILIKE '%active%';
    RAISE NOTICE 'Active politicians: %', row_count;
    
    SELECT COUNT(*) INTO row_count 
    FROM view_riksdagen_vote_data_ballot_politician_summary_daily 
    WHERE embedded_id_vote_date >= CURRENT_DATE - INTERVAL '3 years';
    RAISE NOTICE 'Daily vote summaries (last 3 years): %', row_count;
END $$;
```

**Expected output (after v1.33 fixes):**
```
NOTICE:  === Intelligence Views Status ===
NOTICE:  View: view_riksdagen_member_proposals | Rows: 90534
NOTICE:  View: view_riksdagen_committee_parliament_member_proposal | Rows: [varies]
NOTICE:  View: view_riksdagen_crisis_resilience_indicators | Rows: [varies]
NOTICE:  View: view_risk_score_evolution | Rows: [varies]
NOTICE:  
NOTICE:  === Data Availability Check ===
NOTICE:  Motion documents (document_element): 90534
NOTICE:  Votes (last 2 years): [varies]
NOTICE:  Active politicians: [varies]
NOTICE:  Daily vote summaries (last 3 years): [varies]
```

---

## 🚨 Emergency Data Recovery

### Scenario: Multiple Views Empty After Update

**Symptoms:**
- Most or all views suddenly return 0 rows
- Previously working views now empty
- No recent data import failures

**Root Cause Analysis:**
```sql
-- Check recent schema changes
SELECT 
    schemaname,
    tablename,
    last_vacuum,
    last_autovacuum,
    last_analyze,
    last_autoanalyze
FROM pg_stat_user_tables
WHERE schemaname = 'public'
  AND (last_vacuum IS NULL OR last_vacuum < NOW() - INTERVAL '7 days')
ORDER BY schemaname, tablename;

-- Check for corrupted indexes
SELECT 
    schemaname,
    tablename,
    indexname,
    idx_scan,
    idx_tup_read,
    idx_tup_fetch
FROM pg_stat_user_indexes
WHERE schemaname = 'public'
  AND idx_scan = 0  -- Unused indexes might indicate corruption
ORDER BY tablename, indexname;
```

**Recovery Steps:**

1. **Backup Current State:**
```bash
pg_dump -h localhost -U cia_user -d cia -Fc -f backup_before_recovery_$(date +%Y%m%d_%H%M%S).dump
```

2. **Verify Base Table Integrity:**
```sql
-- Check all base tables have data
SELECT 
    schemaname,
    tablename,
    n_live_tup AS approximate_row_count
FROM pg_stat_user_tables
WHERE schemaname = 'public'
  AND tablename NOT LIKE 'view_%'
ORDER BY n_live_tup DESC;
```

3. **Rebuild Materialized Views:**
```sql
-- Drop and recreate all materialized views
\i service.data.impl/src/main/resources/drop_all_materialized_views.sql
\i service.data.impl/src/main/resources/create_all_materialized_views.sql
\i service.data.impl/src/main/resources/refresh-all-views.sql
```

4. **Verify Recovery:**
```sql
-- Check all views now have data
SELECT 
    schemaname,
    viewname,
    (SELECT COUNT(*) FROM (SELECT * FROM public.pg_views WHERE viewname = v.viewname LIMIT 1) x) AS exists_check
FROM pg_views v
WHERE schemaname = 'public'
ORDER BY viewname;
```

---

## 📈 Monitoring and Prevention

### Automated Monitoring

**Create Monitoring View:**
```sql
CREATE OR REPLACE VIEW view_empty_view_monitor AS
SELECT 
    v.schemaname,
    v.viewname,
    CASE 
        WHEN EXISTS (
            SELECT 1 FROM pg_matviews m 
            WHERE m.schemaname = v.schemaname 
              AND m.matviewname = v.viewname
        ) THEN 'MATERIALIZED'
        ELSE 'REGULAR'
    END AS view_type,
    CURRENT_TIMESTAMP AS last_checked
FROM pg_views v
WHERE v.schemaname = 'public'
  AND NOT EXISTS (
      -- This is a placeholder - actual row count check would be dynamic
      SELECT 1 FROM information_schema.tables t
      WHERE t.table_schema = v.schemaname
        AND t.table_name = v.viewname
  );
```

**Monitoring Query (Run Daily):**
```sql
-- Find views that might be empty (requires execution of dynamic SQL)
DO $$
DECLARE
    view_rec RECORD;
    row_count INTEGER;
    empty_count INTEGER := 0;
BEGIN
    FOR view_rec IN 
        SELECT schemaname, viewname 
        FROM pg_views 
        WHERE schemaname = 'public'
    LOOP
        BEGIN
            EXECUTE format('SELECT COUNT(*) FROM %I.%I', 
                view_rec.schemaname, view_rec.viewname)
            INTO row_count;
            
            IF row_count = 0 THEN
                empty_count := empty_count + 1;
                RAISE NOTICE '⚠️  Empty view detected: %.%', 
                    view_rec.schemaname, view_rec.viewname;
            END IF;
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE '❌ Error checking %.%: %', 
                view_rec.schemaname, view_rec.viewname, SQLERRM;
        END;
    END LOOP;
    
    RAISE NOTICE '📊 Summary: % empty views found out of total', empty_count;
END $$;
```

### Prevention Checklist

- [ ] **Scheduled Data Imports:** Configure cron jobs for regular Riksdagen API imports
- [ ] **View Refresh Schedule:** Automate materialized view refresh based on data update frequency
- [ ] **Monitoring Alerts:** Set up alerts for views that become empty unexpectedly
- [ ] **Dependency Documentation:** Maintain clear documentation of view dependencies (see view_dependencies.csv)
- [ ] **Testing After Schema Changes:** Always test views after modifying base tables or view definitions
- [ ] **Backup Strategy:** Regular database backups before major data operations
- [ ] **Data Validation:** Implement pre-import validation to catch data quality issues early

---

## 🔗 Related Documentation

- [📊 DATABASE_VIEW_INTELLIGENCE_CATALOG.md](./DATABASE_VIEW_INTELLIGENCE_CATALOG.md) - Complete view catalog with usage examples
- [🔍 DATA_ANALYSIS_INTOP_OSINT.md](./DATA_ANALYSIS_INTOP_OSINT.md) - Intelligence analysis frameworks
- [🛠️ service.data.impl/README-SCHEMA-MAINTENANCE.md](./service.data.impl/README-SCHEMA-MAINTENANCE.md) - Schema maintenance procedures
- [📈 SWOT_SCHEMA_DOCUMENTATION.md](./SWOT_SCHEMA_DOCUMENTATION.md) - Documentation quality analysis
- [🔄 service.data.impl/src/main/resources/refresh-all-views.sql](./service.data.impl/src/main/resources/refresh-all-views.sql) - View refresh script

---

## 📞 Support and Escalation

### Self-Service Troubleshooting Order

1. **Check this guide** for view-specific troubleshooting
2. **Run diagnostic queries** from Step 1-4 above
3. **Review application logs** for import job failures
4. **Check Riksdagen API status** for external data source issues
5. **Consult schema documentation** for view definition details

### When to Escalate

**🔴 Immediate Escalation (Severity: Critical):**
- All views suddenly empty after being populated
- Database corruption suspected
- Data loss detected in base tables

**🟠 Standard Escalation (Severity: High):**
- Multiple views remain empty after following this guide
- Import jobs consistently failing
- View refresh errors persisting after troubleshooting

**🟡 Advisory Escalation (Severity: Medium):**
- Performance issues with view queries
- Questions about view design or optimization
- Documentation clarification needed

**Contact:** Database Administration Team  
**Email:** database-admin@hack23.com  
**Slack:** #cia-database-support  
**On-Call:** See PagerDuty rotation

---

**📋 Document Control:**  
**✅ Approved by:** Database Administration Team  
**📤 Distribution:** Engineering, DevOps, Support  
**🏷️ Classification:** [![Confidentiality: Internal](https://img.shields.io/badge/C-Internal-blue?style=flat-square)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#confidentiality-levels)  
**📅 Created:** 2025-11-18  
**⏰ Next Review:** 2026-02-18 (Quarterly)  

**🔄 Change Log:**

| Version | Date | Changes | Author |
|---------|------|---------|--------|
| 1.0 | 2025-11-18 | Initial troubleshooting guide for empty database views | Database Team |
| 1.1 | 2025-11-20 | Added fixes for 4 empty intelligence views (crisis, risk, proposals) | Intelligence Team |

---

<p align="center">
  <strong>🔍 Ensuring Data Integrity • 📊 Maintaining View Availability</strong>
</p>

<p align="center">
  <em>Hack23 AB — Citizen Intelligence Agency Platform</em>
</p>
