# JSON Schema Validation Report

**Generated:** 2026-04-06T13:10:16.816382+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 202
**Total Mismatches:** 120

---

## Executive Summary

This report validates the 5 JSON export schemas against 202 relevant CSV sample data files from the CIA database to ensure schema correctness and identify gaps between schema definitions and actual data structure.

### Field Implementation Status Summary

| Category | Count | Description |
|----------|-------|-------------|
| ✅ Implemented | 24 | Fields found in database sample data |
| ❌ Structural | 56 | JSON grouping objects (not direct DB columns) |
| 🔀 Computed | 42 | Derivable from existing database columns |
| 🔄 Planned | 22 | Fields not yet available in data |

### Validation Scope

| Schema | Fields Defined | Views Matched | Missing Views | Field Mismatches | Implemented | Status |
|--------|---------------|---------------|---------------|------------------|-------------|--------|
| Politician | 46 | 12 | 3 | 32 | 14 | ⚠️ REVIEW |
| Party | 43 | 17 | 2 | 39 | 4 | ⚠️ REVIEW |
| Committee | 26 | 12 | 2 | 22 | 4 | ⚠️ REVIEW |
| Ministry | 29 | 8 | 1 | 27 | 2 | ⚠️ REVIEW |
| Intelligence | 0 | 5 | 2 | 0 | 0 | ✅ PASS |

---

## Detailed Findings

### Politician Schema

**Fields Defined:** 46  
**Database Views Referenced:** 8  
**Sample Files Matched:** 12

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 14 | `activityLevel`, `attendanceRate`, `bornYear`, `firstName`, `gender`, `id`, `lastName`, `party`... |
| ❌ Structural | 19 | `Activity:activity`, `Attributes:attributes`, `Committees:committees`, `DateRange:period`, `Descriptions:descriptions`, `DocumentBreakdown:byType`, `Documents:documents`, `Intelligence:intelligence`... |
| 🔀 Computed | 11 | `absences`, `activeDays`, `amendments`, `fullName`, `influenceScore`, `motions`, `partyLoyalty`, `questions`... |
| 🔄 Planned | 2 | `district`, `imageUrl` |

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

- `riksdagen_politician_vote_summary`
- `riksdagen_politician_summary`
- `riksdagen_politician_ranking`

#### Field Mismatches

**Field:** `Activity:activity`
- **Issue:** Non-scalar type (Activity) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Attributes:attributes`
- **Issue:** Non-scalar type (Attributes) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Committees:committees`
- **Issue:** Non-scalar type (Committees) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `DateRange:period`
- **Issue:** Non-scalar type (DateRange) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Descriptions:descriptions`
- **Issue:** Non-scalar type (Descriptions) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `DocumentBreakdown:byType`
- **Issue:** Non-scalar type (DocumentBreakdown) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Documents:documents`
- **Issue:** Non-scalar type (Documents) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Intelligence:intelligence`
- **Issue:** Non-scalar type (Intelligence) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Labels:labels`
- **Issue:** Non-scalar type (Labels) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `MinistryLink:ministry`
- **Issue:** Non-scalar type (MinistryLink) — JSON grouping object, not a DB column
- **Suggested columns:** 

*... and 22 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: absence_rate, absenteeism_violations, active, active_committee, active_eu

---

### Party Schema

**Fields Defined:** 43  
**Database Views Referenced:** 8  
**Sample Files Matched:** 17

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 4 | `id`, `shortCode`, `status`, `totalVotes` |
| ❌ Structural | 18 | `Attributes:attributes`, `Coalition:coalition`, `Descriptions:descriptions`, `Documents:documents`, `Electoral:electoral`, `Intelligence:intelligence`, `Labels:labels`, `Members:members`... |
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

- `riksdagen_party_ballot_support_summary`
- `riksdagen_party_vote_summary`

#### Field Mismatches

**Field:** `Attributes:attributes`
- **Issue:** Non-scalar type (Attributes) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Coalition:coalition`
- **Issue:** Non-scalar type (Coalition) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Descriptions:descriptions`
- **Issue:** Non-scalar type (Descriptions) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Documents:documents`
- **Issue:** Non-scalar type (Documents) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Electoral:electoral`
- **Issue:** Non-scalar type (Electoral) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Intelligence:intelligence`
- **Issue:** Non-scalar type (Intelligence) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Labels:labels`
- **Issue:** Non-scalar type (Labels) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Members:members`
- **Issue:** Non-scalar type (Members) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Parliamentary:parliamentary`
- **Issue:** Non-scalar type (Parliamentary) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Policy:policy`
- **Issue:** Non-scalar type (Policy) — JSON grouping object, not a DB column
- **Suggested columns:** 

*... and 29 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: acceleration, active, active_committee, active_eu, active_government

---

### Committee Schema

**Fields Defined:** 26  
**Database Views Referenced:** 4  
**Sample Files Matched:** 12

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 4 | `activityLevel`, `id`, `regularMembers`, `totalMembers` |
| ❌ Structural | 8 | `Attributes:attributes`, `Decisions:decisions`, `Intelligence:intelligence`, `Labels:labels`, `Membership:membership`, `Policy:policy`, `Productivity:productivity`, `Relationships:relationships` |
| 🔀 Computed | 9 | `amendments`, `attendanceRate`, `code`, `deputyMembers`, `established`, `hearings`, `name`, `performanceScore`... |
| 🔄 Planned | 5 | `influence`, `meetings`, `nameEn`, `policyDomain`, `type` |

#### Matched Data Files

- `view_riksdagen_committee_decision_type_org_summary_sample.csv`
- `view_riksdagen_committee_sample.csv`
- `view_riksdagen_committee_decisions_sample.csv`
- `view_committee_productivity_matrix_sample.csv`
- `view_riksdagen_committee_ballot_decision_politician_summary_sample.csv`
- `view_riksdagen_committee_parliament_member_proposal_sample.csv`
- `view_riksdagen_committee_ballot_decision_party_summary_sample.csv`
- `view_riksdagen_committee_role_member_sample.csv`
- `view_riksdagen_committee_roles_sample.csv`
- `view_riksdagen_committee_decision_type_summary_sample.csv`
- ... and 2 more

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_committee_summary`
- `riksdagen_committee_decision_summary`

#### Field Mismatches

**Field:** `Attributes:attributes`
- **Issue:** Non-scalar type (Attributes) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Decisions:decisions`
- **Issue:** Non-scalar type (Decisions) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Intelligence:intelligence`
- **Issue:** Non-scalar type (Intelligence) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Labels:labels`
- **Issue:** Non-scalar type (Labels) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Membership:membership`
- **Issue:** Non-scalar type (Membership) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Policy:policy`
- **Issue:** Non-scalar type (Policy) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Productivity:productivity`
- **Issue:** Non-scalar type (Productivity) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Relationships:relationships`
- **Issue:** Non-scalar type (Relationships) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `amendments`, `AMENDMENTS`

**Field:** `attendanceRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attendanceRate`, `attendance_rate`, `attendancerate`, `ATTENDANCERATE`

*... and 12 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: absent_votes, abstain_votes, active, active_members, activity_span_days

---

### Ministry Schema

**Fields Defined:** 29  
**Database Views Referenced:** 4  
**Sample Files Matched:** 8

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 2 | `id`, `name` |
| ❌ Structural | 11 | `Attributes:attributes`, `Budget:budget`, `Decisions:decisions`, `Intelligence:intelligence`, `Labels:labels`, `Performance:performance`, `Personnel:personnel`, `Policy:policy`... |
| 🔀 Computed | 10 | `civilServants`, `code`, `decisionsImplemented`, `effectiveness`, `efficiency`, `established`, `executionRate`, `ministers`... |
| 🔄 Planned | 6 | `allocation`, `headquarters`, `nameEn`, `portfolio`, `publicSatisfaction`, `spent` |

#### Matched Data Files

- `view_riksdagen_goverment_sample.csv`
- `view_ministry_risk_evolution_sample.csv`
- `view_ministry_productivity_matrix_sample.csv`
- `view_ministry_effectiveness_trends_sample.csv`
- `view_riksdagen_goverment_role_member_sample.csv`
- `view_riksdagen_goverment_proposals_sample.csv`
- `view_riksdagen_goverment_roles_sample.csv`
- `view_ministry_decision_impact_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_government_role_member_summary`

#### Field Mismatches

**Field:** `Attributes:attributes`
- **Issue:** Non-scalar type (Attributes) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Budget:budget`
- **Issue:** Non-scalar type (Budget) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Decisions:decisions`
- **Issue:** Non-scalar type (Decisions) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Intelligence:intelligence`
- **Issue:** Non-scalar type (Intelligence) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Labels:labels`
- **Issue:** Non-scalar type (Labels) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Performance:performance`
- **Issue:** Non-scalar type (Performance) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Personnel:personnel`
- **Issue:** Non-scalar type (Personnel) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Policy:policy`
- **Issue:** Non-scalar type (Policy) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Predictions:predictions`
- **Issue:** Non-scalar type (Predictions) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Relationships:relationships`
- **Issue:** Non-scalar type (Relationships) — JSON grouping object, not a DB column
- **Suggested columns:** 

*... and 17 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: active, active_members, activity_level, approval_rate, approved_proposals

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

**Report Generated:** 2026-04-06 13:10:16 UTC
