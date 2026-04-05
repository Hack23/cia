# JSON Schema Validation Report

**Generated:** 2026-04-05T21:45:27.809258+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 202
**Total Mismatches:** 121

---

## Executive Summary

This report validates the 5 JSON export schemas against 138 relevant CSV sample data files 
(filtered from 210+ total files, excluding stats and distinct value tables) 
from the CIA database to ensure schema correctness and identify gaps between 
schema definitions and actual data structure.

### Field Implementation Status Summary

| Category | Count | Description |
|----------|-------|-------------|
| ✅ Implemented | 21 | Fields found in database sample data |
| ❌ Structural | 50 | JSON grouping objects (not direct DB columns) |
| 🔄 Planned | 71 | Fields not yet available in data |

### Validation Scope

| Schema | Fields Defined | Views Matched | Missing Views | Field Mismatches | Implemented | Status |
|--------|---------------|---------------|---------------|------------------|-------------|--------|
| Politician | 45 | 12 | 3 | 31 | 14 | ⚠️ REVIEW |
| Party | 42 | 17 | 2 | 38 | 4 | ⚠️ REVIEW |
| Committee | 26 | 10 | 2 | 24 | 2 | ⚠️ REVIEW |
| Ministry | 29 | 2 | 1 | 28 | 1 | ⚠️ REVIEW |
| Intelligence | 0 | 5 | 2 | 0 | 0 | ✅ PASS |

---

## Detailed Findings

### Politician Schema

**Fields Defined:** 45  
**Database Views Referenced:** 8  
**Sample Files Matched:** 12

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 14 | `gender`, `totalDocuments`, `firstName`, `totalDays`, `status`, `riskScore`, `totalVotes`, `attendanceRate`... |
| ❌ Structural | 17 | `labels`, `committees`, `voting`, `detailed`, `documents`, `descriptions`, `byType`, `activity`... |
| 🔄 Planned | 14 | `rankingPosition`, `imageUrl`, `motions`, `trendDirection`, `rebellions`, `influenceScore`, `amendments`, `absences`... |

#### Matched Data Files

- `view_riksdagen_politician_document_summary_sample.csv`
- `view_riksdagen_politician_experience_summary_sample.csv`
- `view_riksdagen_politician_sample.csv`
- `view_riksdagen_politician_career_path_10level_sample.csv`
- `view_riksdagen_politician_career_trajectory_sample.csv`
- `view_riksdagen_politician_ballot_summary_sample.csv`
- `view_riksdagen_politician_document_sample.csv`
- `view_riksdagen_politician_longevity_analysis_sample.csv`
- `view_riksdagen_politician_role_evolution_sample.csv`
- `view_riksdagen_politician_influence_metrics_sample.csv`
- ... and 2 more

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_politician_summary`
- `riksdagen_politician_ranking`
- `riksdagen_politician_vote_summary`

#### Field Mismatches

**Field:** `rankingPosition`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `rankingPosition`, `ranking_position`, `rankingposition`, `RANKINGPOSITION`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `labels`, `LABELS`

**Field:** `committees`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `committees`, `COMMITTEES`

**Field:** `voting`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `voting`, `VOTING`

**Field:** `detailed`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DETAILED`, `detailed`

**Field:** `documents`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `documents`, `DOCUMENTS`

**Field:** `imageUrl`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `IMAGEURL`, `imageUrl`, `image_url`, `imageurl`

**Field:** `descriptions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `descriptions`, `DESCRIPTIONS`

**Field:** `byType`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `by_type`, `byType`, `BYTYPE`, `bytype`

**Field:** `motions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MOTIONS`, `motions`

*... and 21 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: role_end, active_parliament, title, broker_score, network_connections

---

### Party Schema

**Fields Defined:** 42  
**Database Views Referenced:** 8  
**Sample Files Matched:** 17

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 4 | `shortCode`, `totalVotes`, `status`, `id` |
| ❌ Structural | 15 | `productivity`, `labels`, `policy`, `electoral`, `documents`, `members`, `descriptions`, `parliamentary`... |
| 🔄 Planned | 23 | `seats`, `legislativeSuccess`, `spectrum`, `foundedYear`, `cohesionScore`, `color`, `stability`, `votePercentage`... |

#### Matched Data Files

- `view_riksdagen_party_momentum_analysis_sample.csv`
- `view_riksdagen_party_decision_flow_sample.csv`
- `view_riksdagen_party_sample.csv`
- `view_riksdagen_party_defector_analysis_sample.csv`
- `view_riksdagen_party_coalition_evolution_sample.csv`
- `view_riksdagen_party_signatures_document_summary_sample.csv`
- `view_riksdagen_party_coalation_against_annual_summary_sample.csv`
- `view_riksdagen_party_member_sample.csv`
- `view_riksdagen_party_document_daily_summary_sample.csv`
- `view_riksdagen_party_longitudinal_performance_sample.csv`
- ... and 7 more

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_party_vote_summary`
- `riksdagen_party_ballot_support_summary`

#### Field Mismatches

**Field:** `seats`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `seats`, `SEATS`

**Field:** `legislativeSuccess`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `legislativeSuccess`, `legislative_success`, `legislativesuccess`, `LEGISLATIVESUCCESS`

**Field:** `productivity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `productivity`, `PRODUCTIVITY`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `labels`, `LABELS`

**Field:** `spectrum`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `spectrum`, `SPECTRUM`

**Field:** `foundedYear`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `FOUNDEDYEAR`, `founded_year`, `foundedyear`, `foundedYear`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

**Field:** `cohesionScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `cohesionScore`, `cohesion_score`, `COHESIONSCORE`, `cohesionscore`

**Field:** `electoral`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ELECTORAL`, `electoral`

**Field:** `color`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `color`, `COLOR`

*... and 28 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: active_parliament, volatility_classification, rank_by_discipline, hangar_guid, election_cycle_id

---

### Committee Schema

**Fields Defined:** 26  
**Database Views Referenced:** 3  
**Sample Files Matched:** 10

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 2 | `activityLevel`, `id` |
| ❌ Structural | 8 | `productivity`, `labels`, `policy`, `attributes`, `relationships`, `membership`, `decisions`, `intelligence` |
| 🔄 Planned | 16 | `deputyMembers`, `policyDomain`, `attendanceRate`, `established`, `name`, `type`, `influence`, `meetings`... |

#### Matched Data Files

- `view_riksdagen_committee_decision_type_org_summary_sample.csv`
- `view_riksdagen_committee_sample.csv`
- `view_riksdagen_committee_decisions_sample.csv`
- `view_riksdagen_committee_ballot_decision_politician_summary_sample.csv`
- `view_riksdagen_committee_parliament_member_proposal_sample.csv`
- `view_riksdagen_committee_ballot_decision_party_summary_sample.csv`
- `view_riksdagen_committee_role_member_sample.csv`
- `view_riksdagen_committee_roles_sample.csv`
- `view_riksdagen_committee_decision_type_summary_sample.csv`
- `view_riksdagen_committee_ballot_decision_summary_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_committee_summary`
- `riksdagen_committee_decision_summary`

#### Field Mismatches

**Field:** `productivity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `productivity`, `PRODUCTIVITY`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `labels`, `LABELS`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

**Field:** `deputyMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DEPUTYMEMBERS`, `deputymembers`, `deputy_members`, `deputyMembers`

**Field:** `policyDomain`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `policyDomain`, `POLICYDOMAIN`, `policydomain`, `policy_domain`

**Field:** `attendanceRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attendancerate`, `attendance_rate`, `attendanceRate`, `ATTENDANCERATE`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `established`, `ESTABLISHED`

**Field:** `name`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `NAME`, `name`

**Field:** `type`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `type`, `TYPE`

**Field:** `influence`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `influence`, `INFLUENCE`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: title, current_regular_members, number_value, absent_votes, born_year

---

### Ministry Schema

**Fields Defined:** 29  
**Database Views Referenced:** 3  
**Sample Files Matched:** 2

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 1 | `name` |
| ❌ Structural | 10 | `labels`, `policy`, `budget`, `performance`, `attributes`, `relationships`, `predictions`, `personnel`... |
| 🔄 Planned | 18 | `effectiveness`, `executionRate`, `spent`, `publicSatisfaction`, `trend`, `established`, `efficiency`, `headquarters`... |

#### Matched Data Files

- `view_ministry_effectiveness_trends_sample.csv`
- `view_ministry_decision_impact_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_government_role_member_summary`

#### Field Mismatches

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `labels`, `LABELS`

**Field:** `effectiveness`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `EFFECTIVENESS`, `effectiveness`

**Field:** `executionRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `executionrate`, `executionRate`, `execution_rate`, `EXECUTIONRATE`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

**Field:** `spent`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `spent`, `SPENT`

**Field:** `budget`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `budget`, `BUDGET`

**Field:** `publicSatisfaction`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `publicsatisfaction`, `PUBLICSATISFACTION`, `public_satisfaction`, `publicSatisfaction`

**Field:** `trend`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `TREND`, `trend`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `established`, `ESTABLISHED`

**Field:** `efficiency`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `efficiency`, `EFFICIENCY`

*... and 18 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: propositions, approval_rate, latest_proposal_date, member_change, total_proposals

---

### Intelligence Schema

**Fields Defined:** 0  
**Database Views Referenced:** 10  
**Sample Files Matched:** 5

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 0 |  |
| ❌ Structural | 0 |  |
| 🔄 Planned | 0 |  |

#### Matched Data Files

- `view_committee_productivity_matrix_sample.csv`
- `view_politician_risk_summary_sample.csv`
- `view_committee_productivity_sample.csv`
- `view_party_performance_metrics_sample.csv`
- `view_decision_temporal_trends_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_election_year_vs_midterm`
- `riksdagen_election_year_anomalies`

#### Recommendations

- Consider adding 20 data columns to schema: committee_reports, reports_count, productivity_assessment, decisions_last_month, rejected_decisions

---

## Next Steps

1. **Review Field Mismatches**: Update schemas to match actual database column names
2. **Add Missing Fields**: Consider adding important data columns to schemas
3. **Document Mappings**: Create explicit field mapping documentation (JSON ↔ Database)
4. **Update Examples**: Ensure JSON examples use actual field names from data
5. **Continuous Validation**: Integrate this validation into CI/CD pipeline

## Validation Methodology

This validation compares:
- Field definitions in JSON schema markdown files
- Column names and data types from 138 relevant CSV sample files
- Database view references in schema documentation

**Validation includes:**
- Field name matching (with camelCase/snake_case conversion)
- Data type inference from sample data
- Coverage analysis (schema fields vs. actual columns)
- Missing view detection

**Report Generated:** 2026-04-05 21:45:27 UTC
