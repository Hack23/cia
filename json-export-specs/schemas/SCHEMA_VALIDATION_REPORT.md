# JSON Schema Validation Report

**Generated:** 2026-04-06T16:15:10.370110+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 202
**Total Mismatches:** 144

---

## Executive Summary

This report validates the 5 JSON export schemas against 202 relevant CSV sample data files from the CIA database to ensure schema correctness and identify gaps between schema definitions and actual data structure.

### Field Implementation Status Summary

| Category | Count | Description |
|----------|-------|-------------|
| ✅ Implemented | 24 | Fields found in database sample data |
| ❌ Structural | 67 | JSON grouping objects (not direct DB columns) |
| 🔀 Computed | 42 | Derivable from existing database columns |
| 🔄 Planned | 35 | Fields not yet available in data |

### Validation Scope

| Schema | Fields Defined | Views Matched | Missing Views | Field Mismatches | Implemented | Status |
|--------|---------------|---------------|---------------|------------------|-------------|--------|
| Politician | 55 | 12 | 3 | 41 | 14 | ⚠️ REVIEW |
| Party | 51 | 17 | 2 | 47 | 4 | ⚠️ REVIEW |
| Committee | 29 | 12 | 2 | 25 | 4 | ⚠️ REVIEW |
| Ministry | 33 | 8 | 1 | 31 | 2 | ⚠️ REVIEW |
| Intelligence | 0 | 5 | 2 | 0 | 0 | ✅ PASS |

---

## Detailed Findings

### Politician Schema

**Fields Defined:** 55  
**Database Views Referenced:** 8  
**Sample Files Matched:** 12

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 14 | `activityLevel`, `attendanceRate`, `bornYear`, `firstName`, `gender`, `id`, `lastName`, `party`... |
| ❌ Structural | 24 | `Activity:activity`, `Attributes:attributes`, `CommitteeLink[]:committees`, `CommitteeMembership[]:current`, `CommitteeMembership[]:historical`, `Committees:committees`, `DateRange:period`, `Descriptions:descriptions`... |
| 🔀 Computed | 11 | `absences`, `activeDays`, `amendments`, `fullName`, `influenceScore`, `motions`, `partyLoyalty`, `questions`... |
| 🔄 Planned | 6 | `behavioralFlags`, `chairPositions`, `colleagues`, `district`, `imageUrl`, `keyVotes` |

#### Matched Data Files

- `view_riksdagen_politician_ballot_summary_sample.csv`
- `view_riksdagen_politician_career_path_10level_sample.csv`
- `view_riksdagen_politician_career_trajectory_sample.csv`
- `view_riksdagen_politician_decision_pattern_sample.csv`
- `view_riksdagen_politician_document_daily_summary_sample.csv`
- `view_riksdagen_politician_document_sample.csv`
- `view_riksdagen_politician_document_summary_sample.csv`
- `view_riksdagen_politician_experience_summary_sample.csv`
- `view_riksdagen_politician_influence_metrics_sample.csv`
- `view_riksdagen_politician_longevity_analysis_sample.csv`
- ... and 2 more

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_politician_ranking`
- `riksdagen_politician_summary`
- `riksdagen_politician_vote_summary`

#### Field Mismatches

**Field:** `Activity:activity`
- **Issue:** Non-scalar type (Activity) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `Attributes:attributes`
- **Issue:** Non-scalar type (Attributes) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `CommitteeLink[]:committees`
- **Issue:** Non-scalar type (CommitteeLink[]) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `CommitteeMembership[]:current`
- **Issue:** Non-scalar type (CommitteeMembership[]) — JSON grouping object, not a DB column
- **Suggested columns:** 

**Field:** `CommitteeMembership[]:historical`
- **Issue:** Non-scalar type (CommitteeMembership[]) — JSON grouping object, not a DB column
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

*... and 31 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: absence_rate, absenteeism_violations, active, active_committee, active_eu

---

### Party Schema

**Fields Defined:** 51  
**Database Views Referenced:** 8  
**Sample Files Matched:** 17

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 4 | `id`, `shortCode`, `status`, `totalVotes` |
| ❌ Structural | 22 | `Attributes:attributes`, `Coalition:coalition`, `Descriptions:descriptions`, `Documents:documents`, `ElectionHistory[]:history`, `Electoral:electoral`, `Intelligence:intelligence`, `Labels:labels`... |
| 🔀 Computed | 12 | `activityRate`, `cohesionScore`, `committeeChairs`, `currentSupport`, `disciplineRate`, `fullName`, `legislativeSuccess`, `seats`... |
| 🔄 Planned | 13 | `agreements`, `color`, `foundedYear`, `ideology`, `keyVotes`, `logoUrl`, `ministries`, `nameEn`... |

#### Matched Data Files

- `view_riksdagen_party_ballot_support_annual_summary_sample.csv`
- `view_riksdagen_party_coalation_against_annual_summary_sample.csv`
- `view_riksdagen_party_coalition_evolution_sample.csv`
- `view_riksdagen_party_decision_flow_sample.csv`
- `view_riksdagen_party_defector_analysis_sample.csv`
- `view_riksdagen_party_document_daily_summary_sample.csv`
- `view_riksdagen_party_document_summary_sample.csv`
- `view_riksdagen_party_electoral_trends_sample.csv`
- `view_riksdagen_party_longitudinal_performance_sample.csv`
- `view_riksdagen_party_member_sample.csv`
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

**Field:** `ElectionHistory[]:history`
- **Issue:** Non-scalar type (ElectionHistory[]) — JSON grouping object, not a DB column
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

*... and 37 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: acceleration, active, active_committee, active_eu, active_government

---

### Committee Schema

**Fields Defined:** 29  
**Database Views Referenced:** 4  
**Sample Files Matched:** 12

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 4 | `activityLevel`, `id`, `regularMembers`, `totalMembers` |
| ❌ Structural | 9 | `Attributes:attributes`, `Decisions:decisions`, `Intelligence:intelligence`, `Labels:labels`, `Membership:membership`, `PartyDistribution[]:parties`, `Policy:policy`, `Productivity:productivity`... |
| 🔀 Computed | 9 | `amendments`, `attendanceRate`, `code`, `deputyMembers`, `established`, `hearings`, `name`, `performanceScore`... |
| 🔄 Planned | 7 | `influence`, `leadership`, `meetings`, `nameEn`, `policyDomain`, `trends`, `type` |

#### Matched Data Files

- `view_committee_productivity_matrix_sample.csv`
- `view_committee_productivity_sample.csv`
- `view_riksdagen_committee_ballot_decision_party_summary_sample.csv`
- `view_riksdagen_committee_ballot_decision_politician_summary_sample.csv`
- `view_riksdagen_committee_ballot_decision_summary_sample.csv`
- `view_riksdagen_committee_decision_type_org_summary_sample.csv`
- `view_riksdagen_committee_decision_type_summary_sample.csv`
- `view_riksdagen_committee_decisions_sample.csv`
- `view_riksdagen_committee_parliament_member_proposal_sample.csv`
- `view_riksdagen_committee_role_member_sample.csv`
- ... and 2 more

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_committee_decision_summary`
- `riksdagen_committee_summary`

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

**Field:** `PartyDistribution[]:parties`
- **Issue:** Non-scalar type (PartyDistribution[]) — JSON grouping object, not a DB column
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

*... and 15 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: absent_votes, abstain_votes, active, active_members, activity_span_days

---

### Ministry Schema

**Fields Defined:** 33  
**Database Views Referenced:** 4  
**Sample Files Matched:** 8

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 2 | `id`, `name` |
| ❌ Structural | 12 | `Attributes:attributes`, `Budget:budget`, `BudgetHistory[]:history`, `Decisions:decisions`, `Intelligence:intelligence`, `Labels:labels`, `Performance:performance`, `Personnel:personnel`... |
| 🔀 Computed | 10 | `civilServants`, `code`, `decisionsImplemented`, `effectiveness`, `efficiency`, `established`, `executionRate`, `ministers`... |
| 🔄 Planned | 9 | `allocation`, `headquarters`, `leadership`, `nameEn`, `portfolio`, `programs`, `publicSatisfaction`, `riskFactors`... |

#### Matched Data Files

- `view_ministry_decision_impact_sample.csv`
- `view_ministry_effectiveness_trends_sample.csv`
- `view_ministry_productivity_matrix_sample.csv`
- `view_ministry_risk_evolution_sample.csv`
- `view_riksdagen_goverment_proposals_sample.csv`
- `view_riksdagen_goverment_role_member_sample.csv`
- `view_riksdagen_goverment_roles_sample.csv`
- `view_riksdagen_goverment_sample.csv`

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

**Field:** `BudgetHistory[]:history`
- **Issue:** Non-scalar type (BudgetHistory[]) — JSON grouping object, not a DB column
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

*... and 21 more mismatches*

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
- `view_committee_productivity_sample.csv`
- `view_decision_temporal_trends_sample.csv`
- `view_party_performance_metrics_sample.csv`
- `view_politician_risk_summary_sample.csv`

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

**Report Generated:** 2026-04-06 16:15:10 UTC
