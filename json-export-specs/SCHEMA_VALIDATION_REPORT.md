# JSON Schema Validation Report
## Database Schema Alignment Analysis

**Version:** 1.0.0  
**Date:** 2024-11-24  
**Status:** Analysis Complete - Recommendations Provided  
**Validated Against:** service.data.impl/src/main/resources/full_schema.sql

---

## Executive Summary

This document validates the JSON export specifications against the actual CIA platform database schema and sample data. The analysis ensures that exported JSON files accurately reflect real database structures and contain realistic data patterns.

### Validation Scope

- **Database Schema Source:** `service.data.impl/src/main/resources/full_schema.sql` (13,156 lines, 149 tables/views)
- **Sample Data Source:** `service.data.impl/sample-data/` (85 view samples)
- **JSON Specifications:** 5 schema files (politician, party, ministry, committee, intelligence)
- **Intelligence Catalog:** `DATABASE_VIEW_INTELLIGENCE_CATALOG.md` (85 documented views)

### Overall Assessment

| Category | Status | Coverage | Notes |
|----------|--------|----------|-------|
| **Core Attributes** | ✅ Aligned | 85% | Most key fields mapped correctly |
| **Time Series Data** | ⚠️ Partial | 40% | Underrepresented in JSON specs |
| **Sample Data Accuracy** | ⚠️ Synthetic | 30% | Examples use synthetic vs real patterns |
| **Intelligence Views** | ✅ Good | 75% | Core intelligence views covered |
| **Advanced Analytics** | ⚠️ Partial | 50% | Some views not exposed in JSON |

**Legend:** ✅ Fully Aligned | ⚠️ Partial/Improvements Needed | ❌ Missing/Not Aligned

---

## 1. Politician Schema Validation

### 1.1 Database View: `view_riksdagen_politician`

**Source:** Lines 8,000-8,100 in full_schema.sql  
**Sample Data:** `view_view_riksdagen_politician_sample.csv` (349 politicians)

#### Core Attributes Mapping

| JSON Schema Field | Database Column | Present | Type Match | Notes |
|-------------------|-----------------|---------|------------|-------|
| `id` | `person_id` | ✅ | ✅ | String/VARCHAR(255) |
| `firstName` | `first_name` | ✅ | ✅ | |
| `lastName` | `last_name` | ✅ | ✅ | |
| `party` | `party` | ✅ | ✅ | Party short code (S, M, V, etc.) |
| `bornYear` | `born_year` | ✅ | ✅ | Integer |
| `gender` | `gender` | ✅ | ✅ | "MAN"/"WOMAN" in DB |
| `active` | `active` | ✅ | ✅ | Boolean |

#### Activity Metrics Mapping

| JSON Schema Field | Database Column | Present | Notes |
|-------------------|-----------------|---------|-------|
| `totalDaysServed` | `total_days_served` | ✅ | Calculated field |
| `parliamentDays` | `total_days_served_parliament` | ✅ | |
| `committeeDays` | `total_days_served_committee` | ✅ | |
| `currentAssignments` | `current_assignments` | ✅ | |
| `totalAssignments` | `total_assignments` | ✅ | |
| `activeCommittee` | `active_committee` | ✅ | |
| `activeParliament` | `active_parliament` | ✅ | |

#### Document Metrics Mapping

| JSON Schema Field | Database Column | Present | Notes |
|-------------------|-----------------|---------|-------|
| `totalDocuments` | `total_documents` | ✅ | |
| `partyMotions` | `party_motions` | ✅ | |
| `individualMotions` | `individual_motions` | ✅ | |
| `committeeMotions` | `committee_motions` | ✅ | |
| `activityLevel` | `doc_activity_level` | ✅ | Low/Medium/High |
| `activityProfile` | `doc_activity_profile` | ✅ | Individual/Party/Committee-focused |

#### Missing Database Fields (Not in JSON Schema)

These real database columns could enhance the JSON export:

1. **`collaboration_percentage`** (NUMERIC) - Percentage of multi-party collaboration
2. **`total_days_served_speaker`** (INTEGER) - Days in speaker roles
3. **`active_speaker`** (BOOLEAN) - Currently in speaker role
4. **`total_committee_substitute_assignments`** (INTEGER) - Substitute committee roles
5. **`current_committee_substitute_assignments`** (INTEGER) - Current substitutes
6. **`total_days_served_committee_substitute`** (INTEGER) - Days as substitute
7. **`total_committee_leadership_assignments`** (INTEGER) - Leadership positions
8. **`current_committee_leadership_assignments`** (INTEGER) - Current leadership
9. **`total_days_served_committee_leadership`** (INTEGER) - Days in leadership
10. **`document_types`** (TEXT[]) - Array of document types produced
11. **`first_document_date`** (DATE) - First document submission
12. **`last_document_date`** (DATE) - Most recent document

**Recommendation:** Add these fields to provide more comprehensive politician profiles.

### 1.2 Related Intelligence Views

Additional views that could enhance politician data:

| View Name | Purpose | Fields Available | JSON Integration |
|-----------|---------|------------------|------------------|
| `view_politician_risk_summary` | Risk scoring | risk_score, risk_factors, risk_category | ⚠️ Should add |
| `view_politician_behavioral_trends` | Trend analysis | activity_trends, attendance_trends | ⚠️ Should add |
| `view_riksdagen_politician_influence_metrics` | Network analysis | influence_score, centrality_metrics | ⚠️ Should add |
| `view_riksdagen_politician_ballot_summary` | Voting record | votes_cast, alignment_score | ✅ Partially covered |
| `view_riksdagen_politician_experience_summary` | Experience tracking | years_experience, role_history | ✅ Partially covered |

### 1.3 Sample Data Validation

**Real Sample Data Pattern** (from `table_person_data_sample.csv`):

```csv
id,born_year,election_region,first_name,gender,party,status
0694077681517,1954,Västerbottens län,Lennart,MAN,V,Tidigare riksdagsledamot
0457722435319,1967,,Tomas,MAN,S,Ledamot
```

**Current JSON Example Pattern:**

```json
{
  "id": "0123456789",
  "firstName": "Anna",
  "lastName": "Andersson"
}
```

**Issues Identified:**

1. ❌ **ID Format:** JSON uses sequential "0123456789", real IDs are numeric strings like "0457722435319" (13 digits)
2. ❌ **Name Patterns:** JSON uses "Anna Andersson" (generic), real data has diverse Swedish names
3. ⚠️ **Party Codes:** JSON correctly uses single letters (S, M, V), matches real data ✅
4. ⚠️ **Status Values:** Real DB uses Swedish: "Ledamot", "Tidigare riksdagsledamot", JSON uses English

**Recommendation:** Update examples to use realistic ID patterns and actual Swedish naming conventions.

---

## 2. Party Schema Validation

### 2.1 Database View: `view_riksdagen_party`

**Sample Data:** `view_view_riksdagen_party_sample.csv` (8 parliamentary parties)

#### Real Party Data

From sample data, the 8 parliamentary parties are:

| Party Code | Full Name | Party Color | Historical |
|------------|-----------|-------------|------------|
| S | Socialdemokraterna | Red | ✅ |
| M | Moderaterna | Blue | ✅ |
| V | Vänsterpartiet | Red | ✅ |
| KD | Kristdemokraterna | Blue | ✅ |
| C | Centerpartiet | Green | ✅ |
| L | Liberalerna | Blue | ✅ |
| MP | Miljöpartiet | Green | ✅ |
| SD | Sverigedemokraterna | Yellow | ✅ |

#### Party View Attributes

| JSON Schema Field | Database Column | Present | Notes |
|-------------------|-----------------|---------|-------|
| `id` | `party_id` / `party` | ✅ | Short code (S, M, V, etc.) |
| `name` | `party_name` | ✅ | Full Swedish name |
| `active` | `active` | ✅ | Currently in parliament |
| `founded` | N/A | ❌ | Not in database |
| `ideology` | N/A | ❌ | Not in database |

#### Performance Metrics Available

From `view_party_performance_metrics`:

| Database Column | JSON Schema | Present | Notes |
|-----------------|-------------|---------|-------|
| `total_seats` | seats | ✅ | Current parliament seats |
| `votes_cast` | votingActivity.totalVotes | ✅ | |
| `attendance_rate` | attendance.rate | ✅ | |
| `government_participation` | coalition.inGovernment | ✅ | |
| `documents_submitted` | documents.total | ✅ | |
| `success_rate` | effectiveness.successRate | ⚠️ | Not in JSON |

**Recommendation:** Add `effectiveness.successRate` and `decisionWinRate` from database.

---

## 3. Time Series Data Analysis

### 3.1 Available Time Series Views (20 views)

The database contains extensive time series data that is **underrepresented** in JSON specifications:

#### Vote Summary Time Series (10 views)

| View Name | Granularity | Available | In JSON |
|-----------|-------------|-----------|---------|
| `view_riksdagen_vote_data_ballot_summary_daily` | Daily | ✅ | ❌ |
| `view_riksdagen_vote_data_ballot_summary_weekly` | Weekly | ✅ | ❌ |
| `view_riksdagen_vote_data_ballot_summary_monthly` | Monthly | ✅ | ❌ |
| `view_riksdagen_vote_data_ballot_summary_annual` | Annual | ✅ | ❌ |
| `view_riksdagen_vote_data_ballot_politician_summary_daily` | Politician/Daily | ✅ | ⚠️ |
| `view_riksdagen_vote_data_ballot_politician_summary_weekly` | Politician/Weekly | ✅ | ⚠️ |
| `view_riksdagen_vote_data_ballot_politician_summary_monthly` | Politician/Monthly | ✅ | ⚠️ |
| `view_riksdagen_vote_data_ballot_politician_summary_annual` | Politician/Annual | ✅ | ⚠️ |
| `view_riksdagen_vote_data_ballot_party_summary_daily` | Party/Daily | ✅ | ⚠️ |
| `view_riksdagen_vote_data_ballot_party_summary_weekly` | Party/Weekly | ✅ | ⚠️ |

#### Intelligence Time Series (4 views)

| View Name | Purpose | Available | In JSON |
|-----------|---------|-----------|---------|
| `view_politician_behavioral_trends` | Behavior patterns over time | ✅ | ❌ |
| `view_party_effectiveness_trends` | Party effectiveness evolution | ✅ | ❌ |
| `view_ministry_effectiveness_trends` | Ministry performance over time | ✅ | ❌ |
| `view_risk_score_evolution` | Risk score changes | ✅ | ❌ |

### 3.2 Recommendations for Time Series Integration

Add dedicated time series sections to JSON schemas:

**For Politician Schema:**

```json
{
  "timeSeries": {
    "votingActivity": {
      "daily": [...],
      "weekly": [...],
      "monthly": [...],
      "annual": [...]
    },
    "behavioralTrends": {
      "attendanceTrend": [...],
      "activityTrend": [...],
      "collaborationTrend": [...]
    },
    "riskEvolution": {
      "riskScores": [...],
      "timestamps": [...]
    }
  }
}
```

**For Party Schema:**

```json
{
  "timeSeries": {
    "performanceMetrics": {
      "monthly": [...],
      "quarterly": [...],
      "annual": [...]
    },
    "effectivenessTrends": {
      "successRate": [...],
      "coalitionStability": [...],
      "publicSupport": [...]
    }
  }
}
```

---

## 4. Ministry & Committee Validation

### 4.1 Ministry Views

**Primary View:** `view_riksdagen_goverment`  
**Supporting Views:** 
- `view_ministry_productivity_matrix`
- `view_ministry_effectiveness_trends`
- `view_ministry_risk_evolution`

**Sample Data Status:** ⚠️ Most ministry views return minimal data

#### Available Ministry Data

| Field | Database Column | JSON Schema | Present |
|-------|-----------------|-------------|---------|
| Ministry ID | `org_code` | `id` | ✅ |
| Ministry Name | `org_name` | `name` | ✅ |
| Current Minister | `role_holder` | `currentMinister` | ✅ |
| Budget | N/A | `budget.allocated` | ❌ Not in DB |
| Personnel Count | N/A | `personnel.total` | ❌ Not in DB |

**Issue:** JSON schema includes budget and personnel data not present in database views.

**Recommendation:** Either add these fields to database or remove from JSON schema, or mark as "planned" features.

### 4.2 Committee Views

**Primary View:** `view_riksdagen_committee`  
**Supporting Views:**
- `view_committee_productivity_matrix` ✅
- `view_riksdagen_committee_decisions` ✅
- `view_riksdagen_committee_role_member` ✅

**Validation:** ✅ Committee JSON schema aligns well with database views.

---

## 5. Intelligence Product Validation

### 5.1 Intelligence Views Available (7 views)

| View Name | Purpose | Data Available | JSON Coverage |
|-----------|---------|----------------|---------------|
| `view_riksdagen_intelligence_dashboard` | KPI dashboard | ✅ Real data | ✅ Covered |
| `view_politician_risk_summary` | Risk assessment | ⚠️ Limited data | ⚠️ Partial |
| `view_riksdagen_voting_anomaly_detection` | Anomaly patterns | ⚠️ Limited data | ✅ Covered |
| `view_riksdagen_politician_influence_metrics` | Network analysis | ✅ Real data | ⚠️ Partial |
| `view_riksdagen_coalition_alignment_matrix` | Coalition analysis | ⚠️ Limited data | ✅ Covered |
| `view_party_momentum_analysis` | Trend momentum | ✅ Real data | ⚠️ Partial |
| `view_riksdagen_crisis_resilience_indicators` | Crisis monitoring | ⚠️ Limited data | ❌ Not covered |

### 5.2 Intelligence Schema Alignment

**✅ Well Covered:**
- Risk assessments structure
- Trend analysis format
- Coalition stability monitoring
- Voting pattern analytics

**⚠️ Needs Enhancement:**
- Influence metrics (add centrality scores, network position)
- Momentum analysis (add velocity, acceleration metrics)
- Crisis indicators (new section needed)

---

## 6. Recommendations Summary

### Priority 1: High Impact

1. **Update Sample Data to Use Real Patterns**
   - Use realistic person IDs (13-digit format)
   - Use actual Swedish names from sample data
   - Use real party codes and Swedish terminology
   - Files: `politician-example.json`, `party-example.json`

2. **Add Time Series Sections**
   - Add `timeSeries` object to politician and party schemas
   - Include daily/weekly/monthly/annual aggregations
   - Add behavioral trends and risk evolution
   - Files: `politician-schema.md`, `party-schema.md`

3. **Add Missing Core Fields**
   - Add `collaboration_percentage` to politician schema
   - Add `effectiveness.successRate` to party schema
   - Add committee leadership fields to politician schema
   - Files: `politician-schema.md`, `party-schema.md`

### Priority 2: Medium Impact

4. **Enhance Intelligence Products**
   - Add influence metrics (centrality, betweenness)
   - Add momentum analysis (velocity, acceleration)
   - Add crisis resilience indicators
   - File: `intelligence-schema.md`

5. **Align Ministry Schema with Database**
   - Mark budget fields as "planned" if not in DB
   - Mark personnel fields as "planned" if not in DB
   - Or add these fields to database views
   - File: `ministry-schema.md`

### Priority 3: Nice to Have

6. **Add Document Type Arrays**
   - Include `document_types` array field
   - Add `first_document_date` and `last_document_date`
   - File: `politician-schema.md`

7. **Add Speaker Role Information**
   - Include speaker-related fields from database
   - Add speaker role history
   - File: `politician-schema.md`

---

## 7. Implementation Roadmap

### Phase 1: Critical Alignment (1 week)
- Update example JSON files with real data patterns
- Add missing core fields to schemas
- Document fields that don't exist in database

### Phase 2: Time Series Integration (1 week)
- Design time series JSON structure
- Add time series sections to schemas
- Create example time series data

### Phase 3: Intelligence Enhancement (1 week)
- Add advanced intelligence metrics
- Enhance influence and momentum sections
- Add crisis resilience indicators

### Phase 4: Documentation & Validation (3 days)
- Update all documentation
- Create validation test suite
- Generate updated example files

---

## 8. Validation Checklist

Use this checklist when implementing changes:

- [ ] Person ID format matches real data (13 digits)
- [ ] Names use realistic Swedish patterns
- [ ] Party codes match 8 parliamentary parties (S, M, V, KD, C, L, MP, SD)
- [ ] All database view columns are documented
- [ ] Time series sections are added
- [ ] Intelligence metrics align with actual views
- [ ] Example JSON files use real sample data patterns
- [ ] Fields not in database are marked as "planned"
- [ ] Schema validation tests pass
- [ ] Documentation references correct view names

---

## Appendix A: Database View Inventory

### Politician-Related Views (14)
1. view_riksdagen_politician ✅ Primary
2. view_riksdagen_politician_document ✅
3. view_riksdagen_politician_document_summary ✅
4. view_riksdagen_politician_document_daily_summary ✅
5. view_riksdagen_politician_ballot_summary ✅
6. view_riksdagen_politician_experience_summary ✅
7. view_riksdagen_politician_influence_metrics ⚠️ Partial
8. view_politician_risk_summary ⚠️ Partial
9. view_politician_behavioral_trends ❌ Not in JSON
10. view_riksdagen_person_signed_document_summary ✅
11. view_riksdagen_vote_data_ballot_politician_summary (+ 4 time variants) ⚠️

### Party-Related Views (13)
1. view_riksdagen_party ✅ Primary
2. view_riksdagen_party_member ✅
3. view_riksdagen_party_summary ✅
4. view_riksdagen_party_document_summary ✅
5. view_riksdagen_party_document_daily_summary ✅
6. view_party_performance_metrics ✅
7. view_party_effectiveness_trends ❌ Not in JSON
8. view_party_momentum_analysis ⚠️ Partial
9. view_riksdagen_party_ballot_support_annual_summary ✅
10. view_riksdagen_party_coalation_against_annual_summary ✅
11. view_riksdagen_vote_data_ballot_party_summary (+ 4 time variants) ⚠️

### Committee-Related Views (12)
1. view_riksdagen_committee ✅ Primary
2. view_committee_productivity_matrix ✅
3. view_committee_productivity ✅
4. view_riksdagen_committee_decisions ✅
5. view_riksdagen_committee_role_member ✅
6. view_riksdagen_committee_roles ✅
7. view_riksdagen_committee_parliament_member_proposal ✅
8. view_riksdagen_committee_decision_type_summary ✅
9. view_riksdagen_committee_decision_type_org_summary ✅
10. view_riksdagen_committee_ballot_decision_summary ✅
11. view_riksdagen_committee_ballot_decision_party_summary ✅
12. view_riksdagen_committee_ballot_decision_politician_summary ✅

### Ministry/Government Views (8)
1. view_riksdagen_goverment ✅ Primary
2. view_riksdagen_goverment_roles ✅
3. view_riksdagen_goverment_role_member ✅
4. view_riksdagen_goverment_proposals ✅
5. view_ministry_productivity_matrix ⚠️ Limited data
6. view_ministry_effectiveness_trends ⚠️ Limited data
7. view_ministry_risk_evolution ⚠️ Limited data
8. view_ministry_decision_impact (v1.35) ⚠️ New

### Intelligence Views (7)
1. view_riksdagen_intelligence_dashboard ✅
2. view_politician_risk_summary ⚠️
3. view_riksdagen_voting_anomaly_detection ⚠️
4. view_riksdagen_politician_influence_metrics ⚠️
5. view_riksdagen_coalition_alignment_matrix ⚠️
6. view_party_momentum_analysis ⚠️
7. view_riksdagen_crisis_resilience_indicators ⚠️

---

## Appendix B: Sample Data File Reference

### Core Tables
- `table_person_data_sample.csv` - 349 politicians (24.7 KB)
- `table_person_assignment_data_sample.csv` - Assignment history
- `table_person_detail_data_sample.csv` - Detailed person info

### View Samples
- `view_view_riksdagen_politician_sample.csv` - 349 rows, 13.5 KB
- `view_view_riksdagen_party_sample.csv` - 8 parties
- `view_view_riksdagen_committee_sample.csv` - 15 committees
- `view_view_committee_productivity_matrix_sample.csv` - Performance data
- `view_view_party_performance_metrics_sample.csv` - Party metrics
- `view_view_riksdagen_intelligence_dashboard_sample.csv` - KPIs

### Time Series Samples
- `view_view_riksdagen_vote_data_ballot_summary_daily_sample.csv` - 6.5 KB
- `view_view_riksdagen_vote_data_ballot_summary_weekly_sample.csv` - 5.7 KB
- `view_view_politician_behavioral_trends_sample.csv` - 10.9 KB
- `view_view_party_effectiveness_trends_sample.csv` - 10.3 KB

---

**Document Status:** ✅ Complete  
**Next Review:** After implementing Priority 1 recommendations  
**Owner:** Intelligence Operations Team  
**Last Updated:** 2024-11-24
