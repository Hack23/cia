# JSON Schema Validation Report

**Generated:** 2026-04-06T12:11:30.447114+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 202
**Total Mismatches:** 121

---

## Executive Summary

This report validates the 5 JSON export schemas against 202 relevant CSV sample data files from the CIA database to ensure schema correctness and identify gaps between schema definitions and actual data structure.

### Field Implementation Status Summary

| Category | Count | Description |
|----------|-------|-------------|
| ✅ Implemented | 21 | Fields found in database sample data |
| ❌ Structural | 53 | JSON grouping objects (not direct DB columns) |
| 🔀 Computed | 45 | Derivable from existing database columns |
| 🔄 Planned | 23 | Fields not yet available in data |

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
| ✅ Implemented | 14 | `activityLevel`, `attendanceRate`, `bornYear`, `firstName`, `gender`, `id`, `lastName`, `party`... |
| ❌ Structural | 17 | `activity`, `attributes`, `breakdown`, `byType`, `category`, `committees`, `descriptions`, `detailed`... |
| 🔀 Computed | 11 | `absences`, `activeDays`, `amendments`, `fullName`, `influenceScore`, `motions`, `partyLoyalty`, `questions`... |
| 🔄 Planned | 3 | `district`, `imageUrl`, `ministry` |

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

- `riksdagen_politician_ranking`
- `riksdagen_politician_summary`
- `riksdagen_politician_vote_summary`

#### Field Mismatches

**Field:** `absences`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `absences`, `ABSENCES`

**Field:** `activeDays`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `activeDays`, `active_days`, `activedays`, `ACTIVEDAYS`

**Field:** `activity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `activity`, `ACTIVITY`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `amendments`, `AMENDMENTS`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `breakdown`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `breakdown`, `BREAKDOWN`

**Field:** `byType`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `byType`, `by_type`, `bytype`, `BYTYPE`

**Field:** `category`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `category`, `CATEGORY`

**Field:** `committees`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `committees`, `COMMITTEES`

**Field:** `descriptions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `descriptions`, `DESCRIPTIONS`

*... and 21 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: absence_rate, absenteeism_violations, active, active_committee, active_eu

---

### Party Schema

**Fields Defined:** 42  
**Database Views Referenced:** 8  
**Sample Files Matched:** 17

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 4 | `id`, `shortCode`, `status`, `totalVotes` |
| ❌ Structural | 17 | `alignment`, `attributes`, `category`, `coalition`, `descriptions`, `documents`, `electoral`, `intelligence`... |
| 🔀 Computed | 12 | `activityRate`, `cohesionScore`, `committeeChairs`, `currentSupport`, `disciplineRate`, `fullName`, `legislativeSuccess`, `seats`... |
| 🔄 Planned | 9 | `color`, `foundedYear`, `ideology`, `logoUrl`, `ministries`, `nameEn`, `riskScore`, `spectrum`... |

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

**Field:** `activityRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `activityRate`, `activity_rate`, `activityrate`, `ACTIVITYRATE`

**Field:** `alignment`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `alignment`, `ALIGNMENT`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `category`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `category`, `CATEGORY`

**Field:** `coalition`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `coalition`, `COALITION`

**Field:** `cohesionScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `cohesionScore`, `cohesion_score`, `cohesionscore`, `COHESIONSCORE`

**Field:** `color`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `color`, `COLOR`

**Field:** `committeeChairs`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `committeeChairs`, `committee_chairs`, `committeechairs`, `COMMITTEECHAIRS`

**Field:** `currentSupport`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `currentSupport`, `current_support`, `currentsupport`, `CURRENTSUPPORT`

**Field:** `descriptions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `descriptions`, `DESCRIPTIONS`

*... and 28 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: acceleration, active, active_committee, active_eu, active_government

---

### Committee Schema

**Fields Defined:** 26  
**Database Views Referenced:** 3  
**Sample Files Matched:** 10

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 2 | `activityLevel`, `id` |
| ❌ Structural | 8 | `attributes`, `decisions`, `intelligence`, `labels`, `membership`, `policy`, `productivity`, `relationships` |
| 🔀 Computed | 11 | `amendments`, `attendanceRate`, `code`, `deputyMembers`, `established`, `hearings`, `name`, `performanceScore`... |
| 🔄 Planned | 5 | `influence`, `meetings`, `nameEn`, `policyDomain`, `type` |

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

- `riksdagen_committee_decision_summary`
- `riksdagen_committee_summary`

#### Field Mismatches

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `amendments`, `AMENDMENTS`

**Field:** `attendanceRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attendanceRate`, `attendance_rate`, `attendancerate`, `ATTENDANCERATE`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `CODE`

**Field:** `decisions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `decisions`, `DECISIONS`

**Field:** `deputyMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `deputyMembers`, `deputy_members`, `deputymembers`, `DEPUTYMEMBERS`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `established`, `ESTABLISHED`

**Field:** `hearings`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `hearings`, `HEARINGS`

**Field:** `influence`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `influence`, `INFLUENCE`

**Field:** `intelligence`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `intelligence`, `INTELLIGENCE`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: absent_votes, abstain_votes, active, against_proposal_number, against_proposal_parties

---

### Ministry Schema

**Fields Defined:** 29  
**Database Views Referenced:** 3  
**Sample Files Matched:** 2

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 1 | `name` |
| ❌ Structural | 11 | `attributes`, `budget`, `decisions`, `intelligence`, `labels`, `performance`, `personnel`, `policy`... |
| 🔀 Computed | 11 | `civilServants`, `code`, `decisionsImplemented`, `effectiveness`, `efficiency`, `established`, `executionRate`, `id`... |
| 🔄 Planned | 6 | `allocation`, `headquarters`, `nameEn`, `portfolio`, `publicSatisfaction`, `spent` |

#### Matched Data Files

- `view_ministry_effectiveness_trends_sample.csv`
- `view_ministry_decision_impact_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_government_role_member_summary`

#### Field Mismatches

**Field:** `allocation`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `allocation`, `ALLOCATION`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `budget`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `budget`, `BUDGET`

**Field:** `civilServants`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `civilServants`, `civil_servants`, `civilservants`, `CIVILSERVANTS`

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `CODE`

**Field:** `decisions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `decisions`, `DECISIONS`

**Field:** `decisionsImplemented`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `decisionsImplemented`, `decisions_implemented`, `decisionsimplemented`, `DECISIONSIMPLEMENTED`

**Field:** `effectiveness`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `effectiveness`, `EFFECTIVENESS`

**Field:** `efficiency`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `efficiency`, `EFFICIENCY`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `established`, `ESTABLISHED`

*... and 18 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: active_members, approval_rate, approved_proposals, committee, committee_referral_proposals

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
| 🔀 Computed | 0 |  |
| 🔄 Planned | 0 |  |

#### Matched Data Files

- `view_committee_productivity_matrix_sample.csv`
- `view_politician_risk_summary_sample.csv`
- `view_committee_productivity_sample.csv`
- `view_party_performance_metrics_sample.csv`
- `view_decision_temporal_trends_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_election_year_anomalies`
- `riksdagen_election_year_vs_midterm`

#### Recommendations

- Consider adding 20 data columns to schema: absenteeism_violations, active_members, activity_span_days, annual_absence_rate, annual_rebel_rate

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
- Column names and data types from 202 relevant CSV sample files
- Database view references in schema documentation

**Validation includes:**
- Field name matching (with camelCase/snake_case conversion)
- Data type inference from sample data
- Coverage analysis (schema fields vs. actual columns)
- Missing view detection

**Report Generated:** 2026-04-06 12:11:30 UTC
