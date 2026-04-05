# JSON Schema to Database Field Mapping

**Generated:** 2026-04-05  
**Purpose:** Document explicit mappings between JSON schema fields and database columns with implementation status  
**Status:** Comprehensive mapping with validated implementation status  
**Validation Run:** 121 total mismatches across 4 schemas (Intelligence: 0 mismatches)

---

## Overview

This document provides explicit mappings between JSON schema fields and their corresponding database columns, including the **implementation status** of each field based on validation against live database sample data.

### Status Legend

| Icon | Status | Meaning |
|------|--------|---------|
| ✅ | **IMPLEMENTED** | Field exists in database sample data and is available for export |
| 🔀 | **COMPUTED** | Field can be derived/computed from existing database columns |
| 🔄 | **PLANNED** | Field is defined in schema but not yet available in data |
| ❌ | **DEPRECATED** | Structural JSON grouping field — should be removed from field-level validation |

### Validation Summary

| Schema | Total Fields | Implemented | Computed | Planned | Deprecated | Mismatches |
|--------|-------------|-------------|----------|---------|------------|------------|
| **Politician** | 45 | 14 | 12 | 5 | 14 | 31 |
| **Party** | 42 | 4 | 13 | 7 | 14 | 38 |
| **Committee** | 26 | 2 | 10 | 6 | 8 | 24 |
| **Ministry** | 29 | 1 | 8 | 10 | 9 | 28 |
| **Intelligence** | All | All | — | — | — | **0** |
| **Total** | **142** | **21** | **43** | **28** | **45** | **121** |

---

## Politician Schema Field Mappings

**Validation result:** 14 fields implemented, 31 mismatches  
**Source views:** `view_riksdagen_politician`, `view_riksdagen_politician_ballot_summary`, `view_riksdagen_politician_document_summary`  
**Available DB columns:** 251 total

### Implemented Fields (14 — Found in Data)

| JSON Field | Database Column | Data Type | Source View | Status |
|------------|-----------------|-----------|-------------|--------|
| `id` | `person_id` | string | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `firstName` | `first_name` | string | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `lastName` | `last_name` | string | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `bornYear` | `born_year` | integer | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `gender` | `gender` | string | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `party` | `party` | string | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `status` | `active` | boolean | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `totalDays` | `total_days_served` | integer | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `totalDocuments` | `total_documents` | integer | `view_riksdagen_politician_document_summary` | ✅ **IMPLEMENTED** |
| `totalVotes` | `total_votes_cast` | integer | `view_riksdagen_politician_ballot_summary` | ✅ **IMPLEMENTED** |
| `activityLevel` | `doc_activity_level` | string | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `activityProfile` | `doc_activity_profile` | string | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `collaborationPercentage` | `collaboration_percentage` | float | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |
| `partyName` | `party` (text form) | string | `view_riksdagen_politician` | ✅ **IMPLEMENTED** |

### Computed Fields (12 — Derivable from Existing DB Columns)

| JSON Field | Source Column(s) | Computation Logic | Effort | Status |
|------------|-----------------|-------------------|--------|--------|
| `fullName` | `first_name`, `last_name` | Concatenate with space separator | Low | 🔀 **COMPUTED** |
| `partyLoyalty` | `loyalty_rate` | Direct mapping from loyalty_rate column | Low | 🔀 **COMPUTED** |
| `rebellions` | `rebel_rate` | Map rebel_rate; optionally count votes against party line | Low | 🔀 **COMPUTED** |
| `influenceScore` | `influence_classification`, `leadership_profile` | Weighted score from classification + leadership data | Medium | 🔀 **COMPUTED** |
| `rankingPosition` | Multiple metrics | Comparative rank via `ROW_NUMBER()` across all politicians | Medium | 🔀 **COMPUTED** |
| `trendDirection` | `career_phase`, `career_score` | Derive from career trajectory and score trend | Medium | 🔀 **COMPUTED** |
| `absences` | `attendance_rate`, `total_days_served` | `total_days_served * (1 - attendance_rate)` | Low | 🔀 **COMPUTED** |
| `activeDays` | `total_days_served`, `attendance_rate` | `total_days_served * attendance_rate` | Low | 🔀 **COMPUTED** |
| `amendments` | `total_documents` (filtered) | Count documents where type = 'mot' (motions/amendments) | Medium | 🔀 **COMPUTED** |
| `questions` | `total_documents` (filtered) | Count documents where type = 'fr' or 'ip' (questions) | Medium | 🔀 **COMPUTED** |
| `motions` | `total_documents` (filtered) | Count documents where type = 'mot' | Medium | 🔀 **COMPUTED** |
| `performance` | `risk_score`, `attendance_rate`, `win_rate` | Composite score from multiple performance metrics | High | 🔀 **COMPUTED** |

### Planned Fields (5 — Require New Data Sources)

| JSON Field | Data Type | What's Needed | Effort | Status |
|------------|-----------|---------------|--------|--------|
| `district` | string | Electoral district data from Swedish Election Authority API | Medium | 🔄 **PLANNED** |
| `imageUrl` | string | Riksdagen image URL construction from person_id | Low | 🔄 **PLANNED** |
| `chairPositions` | array | Extract from committee role data (partially available) | Medium | 🔄 **PLANNED** |
| `colleagues` | array | Compute from committee/party co-membership graph | High | 🔄 **PLANNED** |
| `keyVotes` | array | Curated list of significant parliamentary votes | High | 🔄 **PLANNED** |

### Deprecated Fields (14 — Structural JSON Groupings)

These fields are structural nesting keys in the JSON schema, not data fields. They should be excluded from field-level validation.

| JSON Field | Reason | Status |
|------------|--------|--------|
| `attributes` | Container object for profile attributes | ❌ **DEPRECATED** |
| `labels` | Container object for classification labels | ❌ **DEPRECATED** |
| `descriptions` | Container for short/detailed/long text | ❌ **DEPRECATED** |
| `short` | Sub-field of descriptions | ❌ **DEPRECATED** |
| `detailed` | Sub-field of descriptions | ❌ **DEPRECATED** |
| `long` | Sub-field of descriptions | ❌ **DEPRECATED** |
| `relationships` | Container for entity relationships | ❌ **DEPRECATED** |
| `activity` | Container section for activity data | ❌ **DEPRECATED** |
| `voting` | Container section for voting data | ❌ **DEPRECATED** |
| `breakdown` | Sub-section of voting data | ❌ **DEPRECATED** |
| `documents` | Container section for document data | ❌ **DEPRECATED** |
| `byType` | Sub-section of documents | ❌ **DEPRECATED** |
| `period` | Sub-section of activity | ❌ **DEPRECATED** |
| `category` | Label category field | ❌ **DEPRECATED** |
| `intelligence` | Container section for intelligence data | ❌ **DEPRECATED** |
| `ministry` | Container section for ministry relationships | ❌ **DEPRECATED** |
| `committees` | Container section for committee data | ❌ **DEPRECATED** |

### Unmapped Database Columns (High-Value — Not Yet in Schema)

| Database Column | Data Type | Recommended JSON Field | Priority |
|-----------------|-----------|----------------------|----------|
| `risk_score` | float | `riskScore` | 🔴 High |
| `risk_level` | string | `riskLevel` | 🔴 High |
| `attendance_rate` | float | `attendanceRate` | 🔴 High |
| `win_rate` | float | `winRate` | 🟡 Medium |
| `career_phase` | string | `careerPhase` | 🟡 Medium |
| `career_score` | float | `careerScore` | 🟡 Medium |
| `experience_level` | string | `experienceLevel` | 🟡 Medium |
| `total_assignments` | integer | `totalAssignments` | 🟢 Low |
| `current_assignments` | integer | `currentAssignments` | 🟢 Low |
| `current_ministry_assignments` | integer | `currentMinistryAssignments` | 🟢 Low |

---

## Party Schema Field Mappings

**Validation result:** 4 fields implemented, 38 mismatches  
**Source views:** `view_riksdagen_party_summary`, `view_riksdagen_party_member`, `view_riksdagen_party_ballot_support_annual_summary`  
**Available DB columns:** 317 total

### Implemented Fields (4 — Found in Data)

| JSON Field | Database Column | Data Type | Source View | Status |
|------------|-----------------|-----------|-------------|--------|
| `id` | `party` | string | `view_riksdagen_party_summary` | ✅ **IMPLEMENTED** |
| `shortCode` | `short_code` | string | `view_riksdagen_party_summary` | ✅ **IMPLEMENTED** |
| `status` | `status` | string | `view_riksdagen_party_summary` | ✅ **IMPLEMENTED** |
| `party` | `party` | string | `view_riksdagen_party_summary` | ✅ **IMPLEMENTED** |

### Computed Fields (13 — Derivable from Existing DB Columns)

| JSON Field | Source Column(s) | Computation Logic | Effort | Status |
|------------|-----------------|-------------------|--------|--------|
| `totalMembers` | `total_active` | Direct mapping from total_active | Low | 🔀 **COMPUTED** |
| `seats` | `currently_active_members` | Direct mapping from currently_active_members | Low | 🔀 **COMPUTED** |
| `activityRate` | `participation_rate` | Direct mapping from participation_rate | Low | 🔀 **COMPUTED** |
| `riskScore` | Risk indicators | Aggregate from member risk_scores | Medium | 🔀 **COMPUTED** |
| `strengthScore` | Multiple metrics | Weighted composite of seats, votes, stability | Medium | 🔀 **COMPUTED** |
| `cohesionScore` | `avg_collaboration_pct` | Direct mapping from avg_collaboration_pct | Low | 🔀 **COMPUTED** |
| `disciplineRate` | Voting data | Compute from member loyalty_rate averages | Medium | 🔀 **COMPUTED** |
| `votePercentage` | `win_rate` | Direct mapping from win_rate | Low | 🔀 **COMPUTED** |
| `legislativeSuccess` | `win_rate`, `total_documents` | Composite of win_rate and document success | Medium | 🔀 **COMPUTED** |
| `stability` | `stability_classification`, `volatility` | Map stability_classification + volatility score | Low | 🔀 **COMPUTED** |
| `currentSupport` | `electoral_trend`, `momentum` | Derive from electoral_trend and momentum | Medium | 🔀 **COMPUTED** |
| `committeeChairs` | Committee role data | Count chairs from committee membership data | Medium | 🔀 **COMPUTED** |
| `productivity` | `total_documents`, `performance_tier` | Documents per member weighted by performance_tier | Medium | 🔀 **COMPUTED** |

### Planned Fields (7 — Require New Data Sources)

| JSON Field | Data Type | What's Needed | Effort | Status |
|------------|-----------|---------------|--------|--------|
| `fullName` | string | Full party name (e.g., "Socialdemokraterna") — static reference data | Low | 🔄 **PLANNED** |
| `nameEn` | string | English party name translation — static reference data | Low | 🔄 **PLANNED** |
| `foundedYear` | integer | Party founding year — static reference data | Low | 🔄 **PLANNED** |
| `ideology` | string | Political ideology classification — static reference data | Low | 🔄 **PLANNED** |
| `spectrum` | string | Left-right spectrum position — static reference data | Low | 🔄 **PLANNED** |
| `color` | string | Official party color hex code — static reference data | Low | 🔄 **PLANNED** |
| `websiteUrl` | string | Party website URL — static reference data | Low | 🔄 **PLANNED** |
| `logoUrl` | string | Party logo URL — static reference data | Low | 🔄 **PLANNED** |

### Deprecated Fields (14 — Structural JSON Groupings)

| JSON Field | Reason | Status |
|------------|--------|--------|
| `attributes` | Container object for party attributes | ❌ **DEPRECATED** |
| `labels` | Container object for classification labels | ❌ **DEPRECATED** |
| `descriptions` | Container for descriptive text | ❌ **DEPRECATED** |
| `relationships` | Container for entity relationships | ❌ **DEPRECATED** |
| `category` | Label category field | ❌ **DEPRECATED** |
| `trend` | Container section for trend data | ❌ **DEPRECATED** |
| `voting` | Container section for voting data | ❌ **DEPRECATED** |
| `alignment` | Sub-section of voting alignment | ❌ **DEPRECATED** |
| `members` | Container section for member data | ❌ **DEPRECATED** |
| `electoral` | Container section for electoral data | ❌ **DEPRECATED** |
| `parliamentary` | Container section for parliamentary data | ❌ **DEPRECATED** |
| `coalition` | Container section for coalition data | ❌ **DEPRECATED** |
| `ministries` | Container section for ministry data | ❌ **DEPRECATED** |
| `intelligence` | Container section for intelligence data | ❌ **DEPRECATED** |
| `policy` | Container section for policy data | ❌ **DEPRECATED** |
| `predictions` | Container section for predictive analytics | ❌ **DEPRECATED** |

### Unmapped Database Columns (High-Value — Not Yet in Schema)

| Database Column | Data Type | Recommended JSON Field | Priority |
|-----------------|-----------|----------------------|----------|
| `trend_direction` | string | `trendDirection` | 🔴 High |
| `volatility` | float | `volatilityScore` | 🔴 High |
| `ballots_participated` | integer | `ballotsParticipated` | 🟡 Medium |
| `total_days_served` | integer | `totalDays` | 🟡 Medium |
| `total_documents` | integer | `totalDocuments` | 🟡 Medium |
| `total_committee_motions` | integer | `totalCommitteeMotions` | 🟢 Low |

---

## Committee Schema Field Mappings

**Validation result:** 2 fields implemented, 24 mismatches  
**Source views:** `view_riksdagen_committee`, `view_riksdagen_committee_decisions`  
**Available DB columns:** 155 total

### Implemented Fields (2 — Found in Data)

| JSON Field | Database Column | Data Type | Source View | Status |
|------------|-----------------|-----------|-------------|--------|
| `id` | `embedded_id_org_code` | string | `view_riksdagen_committee` | ✅ **IMPLEMENTED** |
| `activityLevel` | `activity_level` | string | `view_riksdagen_committee` | ✅ **IMPLEMENTED** |

### Computed Fields (10 — Derivable from Existing DB Columns)

| JSON Field | Source Column(s) | Computation Logic | Effort | Status |
|------------|-----------------|-------------------|--------|--------|
| `name` | `committee_name` | Direct mapping from committee_name | Low | 🔀 **COMPUTED** |
| `code` | `committee_code` / `embedded_id_org_code` | Direct mapping from committee_code | Low | 🔀 **COMPUTED** |
| `totalMembers` | `current_member_size` | Direct mapping from current_member_size | Low | 🔀 **COMPUTED** |
| `regularMembers` | `current_regular_members` | Direct mapping from current_regular_members | Low | 🔀 **COMPUTED** |
| `deputyMembers` | `current_substitute_positions` | Direct mapping from current_substitute_positions | Low | 🔀 **COMPUTED** |
| `performanceScore` | `productivity_score` | Direct mapping from productivity_score | Low | 🔀 **COMPUTED** |
| `reports` | `reports_count` | Direct mapping from reports_count | Low | 🔀 **COMPUTED** |
| `productivity` | `productivity_score`, `total_documents` | Weighted productivity from score + output | Low | 🔀 **COMPUTED** |
| `attendanceRate` | Member attendance data | Aggregate member attendance rates per committee | Medium | 🔀 **COMPUTED** |
| `amendments` | `total_documents` (filtered) | Count amendment-type documents for committee | Medium | 🔀 **COMPUTED** |

### Planned Fields (6 — Require New Data Sources)

| JSON Field | Data Type | What's Needed | Effort | Status |
|------------|-----------|---------------|--------|--------|
| `type` | string | Committee type classification (standing/special) — static reference | Low | 🔄 **PLANNED** |
| `policyDomain` | string | Policy area mapping — static reference data | Low | 🔄 **PLANNED** |
| `nameEn` | string | English committee name — static translation | Low | 🔄 **PLANNED** |
| `established` | date | Committee founding date — historical reference | Low | 🔄 **PLANNED** |
| `meetings` | integer | Meeting count data — requires new data source | Medium | 🔄 **PLANNED** |
| `hearings` | integer | Public hearing count — requires new data source | Medium | 🔄 **PLANNED** |
| `influence` | float | Cross-committee influence scoring — requires algorithm | High | 🔄 **PLANNED** |

### Deprecated Fields (8 — Structural JSON Groupings)

| JSON Field | Reason | Status |
|------------|--------|--------|
| `attributes` | Container object for committee attributes | ❌ **DEPRECATED** |
| `labels` | Container object for classification labels | ❌ **DEPRECATED** |
| `relationships` | Container for entity relationships | ❌ **DEPRECATED** |
| `membership` | Container section for membership data | ❌ **DEPRECATED** |
| `decisions` | Container section for decision data | ❌ **DEPRECATED** |
| `intelligence` | Container section for intelligence data | ❌ **DEPRECATED** |
| `policy` | Container section for policy data | ❌ **DEPRECATED** |
| `meetings` | Container section for meeting data (when structural) | ❌ **DEPRECATED** |

### Unmapped Database Columns (High-Value — Not Yet in Schema)

| Database Column | Data Type | Recommended JSON Field | Priority |
|-----------------|-----------|----------------------|----------|
| `total_decisions_all_time` | integer | `totalDecisions` | 🔴 High |
| `approved` | integer | `approvedDecisions` | 🔴 High |
| `percentage_yes` | float | `approvalRate` | 🔴 High |
| `chairs_count` | integer | `chairsCount` | 🟡 Medium |
| `total_days_served` | integer | `totalDays` | 🟡 Medium |
| `total_documents` | integer | `totalDocuments` | 🟡 Medium |

---

## Ministry Schema Field Mappings

**Validation result:** 1 field implemented, 28 mismatches  
**Source views:** `view_riksdagen_government`, `view_riksdagen_government_role_member`  
**Available DB columns:** 96 total

### Implemented Fields (1 — Found in Data)

| JSON Field | Database Column | Data Type | Source View | Status |
|------------|-----------------|-----------|-------------|--------|
| `name` | `name` | string | `view_riksdagen_government` | ✅ **IMPLEMENTED** |

### Computed Fields (8 — Derivable from Existing DB Columns)

| JSON Field | Source Column(s) | Computation Logic | Effort | Status |
|------------|-----------------|-------------------|--------|--------|
| `id` | `name_id` | Direct mapping from name_id | Low | 🔀 **COMPUTED** |
| `code` | `name_id` | Derive ministry code from name_id | Low | 🔀 **COMPUTED** |
| `effectiveness` | `effectiveness_assessment` | Direct mapping from effectiveness_assessment | Low | 🔀 **COMPUTED** |
| `performanceScore` | `productivity_level`, `approval_rate` | Composite from productivity + approval metrics | Medium | 🔀 **COMPUTED** |
| `efficiency` | `documents_per_member`, `approval_rate` | Ratio of output quality per resource | Medium | 🔀 **COMPUTED** |
| `executionRate` | `approval_rate`, `rejection_rate` | `approval_rate / (approval_rate + rejection_rate)` | Low | 🔀 **COMPUTED** |
| `ministers` | `current_member_size` | Direct mapping from current_member_size | Low | 🔀 **COMPUTED** |
| `decisionsImplemented` | `total_government_bills`, `total_propositions` | Sum of enacted government bills and propositions | Medium | 🔀 **COMPUTED** |

### Planned Fields (10 — Require New Data Sources)

| JSON Field | Data Type | What's Needed | Effort | Status |
|------------|-----------|---------------|--------|--------|
| `nameEn` | string | English ministry name — static translation | Low | 🔄 **PLANNED** |
| `portfolio` | string | Ministry portfolio description — static reference | Low | 🔄 **PLANNED** |
| `established` | date | Ministry founding date — historical reference | Low | 🔄 **PLANNED** |
| `headquarters` | string | Physical address — static reference data | Low | 🔄 **PLANNED** |
| `stateSecretaries` | array | State secretary data — requires role member filtering | Medium | 🔄 **PLANNED** |
| `civilServants` | integer | Civil servant count — external data from government HR | High | 🔄 **PLANNED** |
| `publicSatisfaction` | float | Public satisfaction survey data — external source | High | 🔄 **PLANNED** |
| `allocation` | float | Budget allocation — ESV (Swedish National Financial Management Authority) | Medium | 🔄 **PLANNED** |
| `spent` | float | Actual spending — ESV data integration | Medium | 🔄 **PLANNED** |
| `trend` | string | Performance trend direction — requires time-series computation | Medium | 🔄 **PLANNED** |

### Deprecated Fields (9 — Structural JSON Groupings)

| JSON Field | Reason | Status |
|------------|--------|--------|
| `attributes` | Container object for ministry attributes | ❌ **DEPRECATED** |
| `labels` | Container object for classification labels | ❌ **DEPRECATED** |
| `relationships` | Container for entity relationships | ❌ **DEPRECATED** |
| `personnel` | Container section for personnel data | ❌ **DEPRECATED** |
| `decisions` | Container section for decision data | ❌ **DEPRECATED** |
| `intelligence` | Container section for intelligence data | ❌ **DEPRECATED** |
| `policy` | Container section for policy data | ❌ **DEPRECATED** |
| `predictions` | Container section for predictive analytics | ❌ **DEPRECATED** |
| `performance` | Container section for performance data | ❌ **DEPRECATED** |
| `budget` | Container section for budget data | ❌ **DEPRECATED** |

### Unmapped Database Columns (High-Value — Not Yet in Schema)

| Database Column | Data Type | Recommended JSON Field | Priority |
|-----------------|-----------|----------------------|----------|
| `activity_level` | string | `activityLevel` | 🔴 High |
| `risk_level` | string | `riskLevel` | 🔴 High |
| `total_documents` | integer | `totalDocuments` | 🔴 High |
| `total_days_served` | integer | `totalDays` | 🟡 Medium |
| `legislative_documents` | integer | `legislativeDocuments` | 🟡 Medium |
| `total_government_bills` | integer | `totalGovernmentBills` | 🟡 Medium |

---

## Intelligence Schema Field Mappings

**Validation result: ✅ 0 mismatches — Fully aligned with implementation**

The Intelligence schema is designed for aggregated analytics and risk assessments. All fields are validated and available. Fields are primarily computed from multiple sources.

### Risk Assessment Fields

| JSON Field | Computation Logic | Source | Status |
|------------|-------------------|--------|--------|
| `riskScore` | Drools rule engine output | 50 behavioral rules | ✅ **IMPLEMENTED** |
| `riskCategory` | Risk score categorization | Computed from riskScore | ✅ **IMPLEMENTED** |
| `riskIndicators` | Active risk flags | Rule violations | ✅ **IMPLEMENTED** |
| `riskTrend` | Risk score over time | Historical comparison | ✅ **IMPLEMENTED** |

### Temporal Trend Fields

| JSON Field | Data Source | Views Used | Status |
|------------|-------------|------------|--------|
| `dailyMetrics` | Daily aggregation | `*_daily_summary` views | ✅ **IMPLEMENTED** |
| `weeklyMetrics` | Weekly aggregation | `*_weekly_summary` views | ✅ **IMPLEMENTED** |
| `monthlyMetrics` | Monthly aggregation | `*_monthly_summary` views | ✅ **IMPLEMENTED** |
| `annualMetrics` | Annual aggregation | `*_annual_summary` views | ✅ **IMPLEMENTED** |

### Database Columns Available

| Database Column | Data Type | Usage | Status |
|-----------------|-----------|-------|--------|
| `avg_approval_rate` | float | Temporal trends | ✅ **IMPLEMENTED** |
| `avg_rebel_rate` | float | Risk assessment | ✅ **IMPLEMENTED** |
| `volatility` | float | Stability analysis | ✅ **IMPLEMENTED** |
| `trend_direction` | string | Momentum indicators | ✅ **IMPLEMENTED** |
| `violation_rate_percentage` | float | Risk scoring | ✅ **IMPLEMENTED** |

---

## Implementation Priority: Top 25 High-Value Fields

The following fields are prioritized based on: data availability (Low effort = already in DB), user value (risk, performance, attendance metrics), and derivability from existing columns.

### Tier 1 — Low Effort, High Value (Direct DB Mapping)

Fields that exist in database views and only require column-to-JSON mapping.

| # | Schema | JSON Field | DB Column | Source View | Effort | User Value |
|---|--------|-----------|-----------|-------------|--------|------------|
| 1 | Politician | `partyLoyalty` | `loyalty_rate` | `view_riksdagen_politician` | **Low** | 🔴 High |
| 2 | Politician | `attendanceRate` ¹ | `attendance_rate` | `view_riksdagen_politician` | **Low** | 🔴 High |
| 3 | Politician | `riskScore` ¹ | `risk_score` | `view_riksdagen_politician` | **Low** | 🔴 High |
| 4 | Politician | `riskLevel` ¹ | `risk_level` | `view_riksdagen_politician` | **Low** | 🔴 High |
| 5 | Party | `totalMembers` | `total_active` | `view_riksdagen_party_summary` | **Low** | 🔴 High |
| 6 | Party | `seats` | `currently_active_members` | `view_riksdagen_party_summary` | **Low** | 🔴 High |
| 7 | Party | `stability` | `stability_classification` | `view_riksdagen_party_summary` | **Low** | 🔴 High |
| 8 | Committee | `name` | `committee_name` | `view_riksdagen_committee` | **Low** | 🔴 High |
| 9 | Committee | `totalMembers` | `current_member_size` | `view_riksdagen_committee` | **Low** | 🔴 High |
| 10 | Committee | `performanceScore` | `productivity_score` | `view_riksdagen_committee` | **Low** | 🔴 High |
| 11 | Ministry | `id` | `name_id` | `view_riksdagen_government` | **Low** | 🔴 High |
| 12 | Ministry | `effectiveness` | `effectiveness_assessment` | `view_riksdagen_government` | **Low** | 🔴 High |

> ¹ These fields are in the DB but not yet mapped to the JSON schema — add to schema first.

### Tier 2 — Low Effort, Medium Value (Simple Derivation)

Fields requiring minimal computation from existing columns.

| # | Schema | JSON Field | Computation | Source View | Effort | User Value |
|---|--------|-----------|-------------|-------------|--------|------------|
| 13 | Politician | `fullName` | `first_name \|\| ' ' \|\| last_name` | `view_riksdagen_politician` | **Low** | 🟡 Medium |
| 14 | Politician | `absences` | `total_days * (1 - attendance_rate)` | `view_riksdagen_politician` | **Low** | 🟡 Medium |
| 15 | Politician | `activeDays` | `total_days * attendance_rate` | `view_riksdagen_politician` | **Low** | 🟡 Medium |
| 16 | Party | `cohesionScore` | `avg_collaboration_pct` | `view_riksdagen_party_summary` | **Low** | 🟡 Medium |
| 17 | Party | `votePercentage` | `win_rate` | `view_riksdagen_party_summary` | **Low** | 🟡 Medium |
| 18 | Committee | `regularMembers` | `current_regular_members` | `view_riksdagen_committee` | **Low** | 🟡 Medium |
| 19 | Committee | `deputyMembers` | `current_substitute_positions` | `view_riksdagen_committee` | **Low** | 🟡 Medium |
| 20 | Committee | `reports` | `reports_count` | `view_riksdagen_committee` | **Low** | 🟡 Medium |
| 21 | Ministry | `executionRate` | `approval_rate / (approval_rate + rejection_rate)` | `view_riksdagen_government` | **Low** | 🟡 Medium |

### Tier 3 — Medium Effort, High Value (Aggregation Required)

Fields requiring multi-column computation or cross-entity aggregation.

| # | Schema | JSON Field | Computation | Source View(s) | Effort | User Value |
|---|--------|-----------|-------------|----------------|--------|------------|
| 22 | Politician | `influenceScore` | Weighted from `influence_classification`, `leadership_profile`, `experience_level` | `view_riksdagen_politician` | **Medium** | 🔴 High |
| 23 | Politician | `rankingPosition` | `ROW_NUMBER() OVER (ORDER BY composite_score DESC)` | `view_riksdagen_politician` | **Medium** | 🔴 High |
| 24 | Party | `riskScore` | Aggregate member risk scores + party-level indicators | Multiple views | **Medium** | 🔴 High |
| 25 | Party | `disciplineRate` | `AVG(loyalty_rate)` across party members | `view_riksdagen_politician` | **Medium** | 🔴 High |

### Implementation Roadmap

```
Sprint 1 (Tier 1): Map 12 direct DB columns → JSON fields
  → Estimated: 2-3 days development + testing
  → Impact: +12 implemented fields, covers core metrics

Sprint 2 (Tier 2): Add 9 simple computed fields
  → Estimated: 2-3 days development + testing
  → Impact: +9 fields, fills common data gaps

Sprint 3 (Tier 3): Build 4 aggregation pipelines
  → Estimated: 3-5 days development + testing
  → Impact: +4 high-value intelligence fields
```

---

## Field Naming Conventions

### JSON Schema Conventions
- **camelCase** for field names: `firstName`, `totalDays`, `riskScore`
- **Arrays** for collections: `committees[]`, `documents[]`
- **Nested objects** for grouping: `labels.descriptions.short`

### Database Conventions
- **snake_case** for column names: `first_name`, `total_days`, `risk_score`
- **Prefix patterns**: `total_*`, `current_*`, `active_*`
- **Suffix patterns**: `*_date`, `*_count`, `*_percentage`

### Conversion Rules
1. **Direct conversion:** `firstName` → `first_name`
2. **Aggregation prefix:** `total` remains as `total_`
3. **Boolean flags:** Schema `active` → DB `active` (boolean)
4. **Computed fields:** Schema field has no direct DB equivalent

---

## Mismatch Resolution Strategy

### Reducing the 121 Mismatches

| Action | Fields Resolved | Remaining |
|--------|----------------|-----------|
| **Start** | — | 121 mismatches |
| Mark structural groupings as DEPRECATED | 45 fields | 76 mismatches |
| Implement Tier 1 direct mappings | 12 fields | 64 mismatches |
| Implement Tier 2 simple derivations | 9 fields | 55 mismatches |
| Implement Tier 3 aggregations | 4 fields | 51 mismatches |
| Add remaining COMPUTED fields | 18 fields | 33 mismatches |
| Add PLANNED static reference data | 15 fields | 18 mismatches |
| Remaining (external data sources) | — | **18 true gaps** |

### Per-Schema Mismatch Breakdown After Resolution

| Schema | Current | After Deprecation | After All Computed | Final Gaps |
|--------|---------|-------------------|--------------------|------------|
| Politician | 31 | 14 | 2 | 2 (district, imageUrl via external) |
| Party | 38 | 20 | 7 | 7 (static reference data) |
| Committee | 24 | 16 | 6 | 4 (meetings, hearings, type, policyDomain) |
| Ministry | 28 | 18 | 10 | 5 (budget data, civil servants, etc.) |
| **Total** | **121** | **68** | **25** | **18** |

---

## Update Guidelines

When updating schemas or database structure:

1. **Schema Changes:**
   - Update this mapping document with implementation status
   - Run validation: `python3 validate_schemas.py`
   - Verify JSON examples match mappings
   - Update API documentation

2. **Database Changes:**
   - Document new columns in this file
   - Assess impact on JSON schemas
   - Update views if necessary
   - Re-run schema validation
   - Update implementation status (PLANNED → COMPUTED → IMPLEMENTED)

3. **Adding Computed Fields:**
   - Document computation logic
   - Specify source data columns
   - Note any dependencies
   - Provide example calculation
   - Mark as 🔀 **COMPUTED** until validated

4. **Deprecating Fields:**
   - Mark as ❌ **DEPRECATED** in this document
   - Add to validation exclusion list
   - Maintain backward compatibility for 1 release cycle
   - Document migration path

5. **Promoting Field Status:**
   - 🔄 PLANNED → 🔀 COMPUTED (when source columns identified)
   - 🔀 COMPUTED → ✅ IMPLEMENTED (when validated in export data)
   - ✅ IMPLEMENTED → ❌ DEPRECATED (when field is retired)

---

## Validation Integration

This mapping document should be used in conjunction with:
- **Validation Script:** `validate_schemas.py`
- **Validation Report:** `schemas/SCHEMA_VALIDATION_REPORT.md`
- **CI/CD Workflow:** `.github/workflows/validate-json-schemas.yml`

Run validation after any mapping changes:
```bash
cd json-export-specs
python3 validate_schemas.py
```

### Validation Exclusions

The following field categories should be excluded from mismatch counts:
- All ❌ **DEPRECATED** structural grouping fields (45 fields)
- Fields marked as 🔀 **COMPUTED** that have documented computation logic

**Adjusted mismatch target:** 121 total → **18 true gaps** (after deprecation + computation)

---

## References

- [JSON Export Specs README](README.md)
- [Validation Tool Documentation](VALIDATION_README.md)
- [Database View Catalog](../DATABASE_VIEW_INTELLIGENCE_CATALOG.md)
- [Schema Validation Report](schemas/SCHEMA_VALIDATION_REPORT.md)
- [Implementation Guide](IMPLEMENTATION_GUIDE.md)
- [Curated Aggregates Spec](CURATED_AGGREGATES_SPEC.md)

---

**Last Updated:** 2026-04-05  
**Maintained By:** CIA Development Team  
**Status:** Living document — update as schemas evolve and fields are implemented
