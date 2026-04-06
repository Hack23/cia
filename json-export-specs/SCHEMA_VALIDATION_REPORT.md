# JSON Schema Validation Report
## Database Schema Alignment Analysis

**Version:** 2.0.0  
**Date:** 2026-04-05  
**Status:** Analysis Complete - Remediation Plan Provided  
**Validated Against:** service.data.impl/src/main/resources/full_schema.sql  
**Total Schema Mismatches:** 144 (across 4 entity schemas)

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
| **Politician Schema** | ⚠️ Partial | 25% (14/55) | 41 mismatches; 251 DB columns available across views |
| **Party Schema** | ❌ Low | 8% (4/51) | 47 mismatches; 317 DB columns available across views |
| **Committee Schema** | ⚠️ Partial | 14% (4/29) | 25 mismatches; 155 DB columns available across views |
| **Ministry Schema** | ❌ Low | 6% (2/33) | 31 mismatches; 96 DB columns available across views |
| **Intelligence Schema** | ✅ Good | N/A | Different structure (mermaid-based); no direct field mapping |
| **Time Series Data** | ⚠️ Partial | 40% | Underrepresented in JSON specs |
| **Sample Data Accuracy** | ⚠️ Synthetic | 30% | Examples use synthetic vs real patterns |

### Field Status Summary

| Classification | Count | Description |
|----------------|-------|-------------|
| ✅ Implemented | 24 | Fields found in CSV sample data and mapped to JSON |
| ❌ Structural | 67 | JSON grouping objects, non-scalar types, and array types (not direct DB columns) |
| 🔀 Computed | 42 | Derivable from existing database columns |
| 🔄 Planned | 35 | Not yet available in data; require implementation |
| **Total** | **168** | 24 implemented + 67 structural + 42 computed + 35 planned |

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

## 8. Mismatch Resolution Strategy

The 144 identified mismatches fall into three distinct categories, each requiring a different remediation approach:

### 8.1 Structural Fields (67 mismatches) — Not Real Mismatches

These are JSON grouping objects, non-scalar relationship/link types, and array types used to organize nested data (e.g., `votingActivity`, `documents`, `riskAssessment`, `PartyLink:party`, `Trends:trend`, `CommitteeLink[]:committees`, `String[]:subcategories`). They are structural containers in the JSON schema and do not correspond to individual database columns. No implementation action is required.

**Examples:**
- `votingActivity` → groups `totalVotes`, `attendanceRate`, `rebellionRate`
- `documents` → groups `totalDocuments`, `motions`, `interpellations`
- `riskAssessment` → groups `riskScore`, `riskCategory`, `riskFactors`
- `financialMetrics` → groups budget and expenditure sub-fields

**Resolution:** Document as structural; no DB mapping needed. These exist purely for JSON schema organization.

### 8.2 Computable Fields (42 mismatches) — Implementation Needed

These fields can be derived from existing database views and columns through direct mapping, aggregation, or simple joins. They represent the highest-value implementation targets.

**Sources:**
- Politician views: 251 columns across 14 views
- Party views: 317 columns across 13 views
- Committee views: 155 columns across 12 views
- Ministry views: 96 columns across 8 views

**Resolution:** Implement in Sprints 1–2 of the Remediation Roadmap (see below).

### 8.3 Planned Fields (35 mismatches) — Future Work

These fields require new data sources, external API integration, or new database views not yet created. They include enrichment data such as ideology classifications, historical founding dates, cross-entity network metrics, and array-valued fields like key votes and coalition partners.

**Examples:**
- `party.founded` — requires historical data not in Riksdagen API
- `party.ideology` — requires editorial/classification data
- `ministry.budget.allocated` — requires ESV (Ekonomistyrningsverket) integration
- `ministry.personnel.total` — requires government personnel data source

**Resolution:** Plan for Sprint 3 and beyond; may require new data collection pipelines.

---

## 9. Top 25 Priority Fields for Implementation

These fields provide the highest business value and can be implemented from existing database columns:

| # | Field Name | Schema | Source View/Column | Effort | Business Value |
|---|-----------|--------|-------------------|--------|---------------|
| 1 | `collaborationPercentage` | Politician | `view_riksdagen_politician.collaboration_percentage` | Low | High — cross-party work metric |
| 2 | `successRate` | Party | `view_party_performance_metrics.success_rate` | Low | High — party effectiveness KPI |
| 3 | `totalSeats` | Party | `view_riksdagen_party.total_seats` | Low | High — core representation metric |
| 4 | `riskScore` | Politician | `view_politician_risk_summary.risk_score` | Low | High — risk assessment priority |
| 5 | `riskCategory` | Politician | `view_politician_risk_summary.risk_category` | Low | High — risk classification |
| 6 | `influenceScore` | Politician | `view_riksdagen_politician_influence_metrics.influence_score` | Low | High — network analysis |
| 7 | `attendanceRate` | Party | `view_party_performance_metrics.attendance_rate` | Low | High — accountability metric |
| 8 | `decisionWinRate` | Party | `view_party_performance_metrics.decision_win_rate` | Low | High — legislative effectiveness |
| 9 | `totalDaysServedSpeaker` | Politician | `view_riksdagen_politician.total_days_served_speaker` | Low | Medium — role completeness |
| 10 | `activeSpeaker` | Politician | `view_riksdagen_politician.active_speaker` | Low | Medium — current role status |
| 11 | `committeLeadershipAssignments` | Politician | `view_riksdagen_politician.total_committee_leadership_assignments` | Low | Medium — leadership tracking |
| 12 | `currentCommitteeLeadership` | Politician | `view_riksdagen_politician.current_committee_leadership_assignments` | Low | Medium — current leadership |
| 13 | `committeeSubstituteAssignments` | Politician | `view_riksdagen_politician.total_committee_substitute_assignments` | Low | Medium — role depth |
| 14 | `firstDocumentDate` | Politician | `view_riksdagen_politician.first_document_date` | Low | Medium — career timeline |
| 15 | `lastDocumentDate` | Politician | `view_riksdagen_politician.last_document_date` | Low | Medium — recency indicator |
| 16 | `votingTrendMonthly` | Politician | `view_riksdagen_vote_data_ballot_politician_summary_monthly` | Medium | High — temporal analysis |
| 17 | `behavioralTrends` | Politician | `view_politician_behavioral_trends` (multiple columns) | Medium | High — pattern detection |
| 18 | `partyEffectivenessTrend` | Party | `view_party_effectiveness_trends` (multiple columns) | Medium | High — trend analysis |
| 19 | `momentumAnalysis` | Party | `view_party_momentum_analysis` (multiple columns) | Medium | High — political momentum |
| 20 | `coalitionAlignment` | Party | `view_riksdagen_coalition_alignment_matrix` (multiple columns) | Medium | High — coalition intelligence |
| 21 | `committeeProductivity` | Committee | `view_committee_productivity_matrix` (multiple columns) | Medium | Medium — productivity metrics |
| 22 | `committeeDecisionTypes` | Committee | `view_riksdagen_committee_decision_type_summary` (aggregation) | Medium | Medium — decision classification |
| 23 | `anomalyDetection` | Intelligence | `view_riksdagen_voting_anomaly_detection` (join) | Medium | High — anomaly monitoring |
| 24 | `crisisResilience` | Intelligence | `view_riksdagen_crisis_resilience_indicators` (join) | High | High — crisis preparedness |
| 25 | `ministryDecisionImpact` | Ministry | `view_ministry_decision_impact` (new view, v1.35) | High | Medium — ministry effectiveness |

---

## 10. Remediation Roadmap

### Sprint 1: Low-Effort Computed Fields (1–2 weeks)

**Goal:** Implement direct DB column mappings — fields 1–15 from the priority list.

| Task | Schema(s) | Fields | Effort |
|------|-----------|--------|--------|
| Map politician core missing fields | Politician | `collaborationPercentage`, `totalDaysServedSpeaker`, `activeSpeaker` | 2 days |
| Map politician leadership/substitute fields | Politician | `committeeLeadershipAssignments`, `currentCommitteeLeadership`, `committeeSubstituteAssignments` | 1 day |
| Map politician document timeline | Politician | `firstDocumentDate`, `lastDocumentDate` | 1 day |
| Map politician risk fields | Politician | `riskScore`, `riskCategory` | 1 day |
| Map politician influence | Politician | `influenceScore` | 1 day |
| Map party effectiveness fields | Party | `successRate`, `totalSeats`, `attendanceRate`, `decisionWinRate` | 2 days |
| Update JSON example files | All | Realistic IDs, Swedish names, real party codes | 1 day |

**Expected impact:** Resolves ~15 mismatches. Politician coverage rises to ~60%, Party to ~20%.

### Sprint 2: Medium-Effort Computed Fields (2–3 weeks)

**Goal:** Implement aggregation/join-based fields — fields 16–23 from the priority list.

| Task | Schema(s) | Fields | Effort |
|------|-----------|--------|--------|
| Add time series sections | Politician, Party | `votingTrendMonthly`, `behavioralTrends` | 3 days |
| Add party trend analytics | Party | `partyEffectivenessTrend`, `momentumAnalysis`, `coalitionAlignment` | 3 days |
| Add committee analytics | Committee | `committeeProductivity`, `committeeDecisionTypes` | 2 days |
| Add anomaly detection section | Intelligence | `anomalyDetection` | 2 days |
| Update schemas and documentation | All | Schema definitions, examples, validation | 2 days |

**Expected impact:** Resolves ~8 additional mismatches. Adds time series coverage.

### Sprint 3: High-Effort Planned Fields (3–4 weeks)

**Goal:** Plan and prototype new data collection for fields 24–25 and remaining planned fields.

| Task | Schema(s) | Dependency | Effort |
|------|-----------|------------|--------|
| Integrate crisis resilience indicators | Intelligence | `view_riksdagen_crisis_resilience_indicators` data population | 1 week |
| Integrate ministry decision impact | Ministry | v1.35 view deployment | 1 week |
| Evaluate external data sources | Party, Ministry | ESV API, government personnel data | 1 week |
| Design new data pipelines | All | Architecture review for new feeds | 1 week |

**Expected impact:** Addresses remaining ~31 planned fields over multiple iterations.

### Remediation Progress Tracker

| Sprint | Planned | Completed | Mismatches Resolved | Remaining |
|--------|---------|-----------|---------------------|-----------|
| Sprint 1 | 15 fields | — | — | 120 |
| Sprint 2 | 8 fields | — | — | — |
| Sprint 3 | 31+ fields | — | — | — |
| **Total** | **54+** | **—** | **—** | **—** |

---

## 11. Validation Checklist

Use this checklist when implementing changes:

- [x] Person ID format matches real data (13 digits)
- [x] Names use realistic Swedish patterns
- [x] Party codes match 8 parliamentary parties (S, M, V, KD, C, L, MP, SD)
- [x] All database view columns are documented
- [ ] Time series sections are added to JSON schemas
- [x] Intelligence metrics align with actual views
- [ ] Example JSON files use real sample data patterns
- [x] Fields not in database are marked as "planned"
- [ ] Schema validation tests pass
- [x] Documentation references correct view names
- [x] Structural fields (56) documented as JSON grouping objects
- [x] Computable fields (42) mapped to source DB columns
- [ ] Planned fields (22) tracked with data source requirements
- [ ] Remediation Sprint 1 complete (15 low-effort fields)
- [ ] Remediation Sprint 2 complete (8 medium-effort fields)
- [ ] Remediation Sprint 3 planned (31+ high-effort fields)

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

**Document Status:** ✅ Complete — Remediation Plan Added  
**Next Review:** After completing Sprint 1 remediation  
**Owner:** Intelligence Operations Team  
**Last Updated:** 2026-04-05
