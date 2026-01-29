# JSON Schema Validation Report

**Generated:** 2026-01-29T03:04:14.052718+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 138
**Total Mismatches:** 125

---

## Executive Summary

This report validates the 5 JSON export schemas against 142 real CSV sample data files 
from the CIA database to ensure schema correctness and identify gaps between 
schema definitions and actual data structure.

### Validation Scope

| Schema | Fields Defined | Views Matched | Missing Views | Field Mismatches | Status |
|--------|---------------|---------------|---------------|------------------|--------|
| Politician | 45 | 8 | 6 | 34 | ⚠️ REVIEW |
| Party | 42 | 11 | 5 | 39 | ⚠️ REVIEW |
| Committee | 26 | 10 | 2 | 24 | ⚠️ REVIEW |
| Ministry | 29 | 2 | 1 | 28 | ⚠️ REVIEW |
| Intelligence | 0 | 5 | 10 | 0 | ✅ PASS |

---

## Detailed Findings

### Politician Schema

**Fields Defined:** 45  
**Database Views Referenced:** 8  
**Sample Files Matched:** 8

#### Matched Data Files

- `view_riksdagen_politician_document_sample.csv`
- `view_riksdagen_politician_document_daily_summary_sample.csv`
- `view_riksdagen_politician_document_summary_sample.csv`
- `view_riksdagen_politician_ballot_summary_sample.csv`
- `view_riksdagen_politician_experience_summary_sample.csv`
- `view_riksdagen_politician_influence_metrics_sample.csv`
- `view_riksdagen_politician_decision_pattern_sample.csv`
- `view_riksdagen_politician_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_politician_role_evolution`
- `riksdagen_politician_vote_summary`
- `riksdagen_politician_ranking`
- `riksdagen_politician_summary`
- `riksdagen_politician_career_path_10level`
- `riksdagen_politician_career_trajectory`

#### Field Mismatches

**Field:** `ministry`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MINISTRY`, `ministry`

**Field:** `documents`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `documents`, `DOCUMENTS`

**Field:** `activity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ACTIVITY`, `activity`

**Field:** `short`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `short`, `SHORT`

**Field:** `period`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `period`, `PERIOD`

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERFORMANCE`, `performance`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `AMENDMENTS`, `amendments`

**Field:** `committees`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `committees`, `COMMITTEES`

**Field:** `attendanceRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attendance_rate`, `attendancerate`, `attendanceRate`, `ATTENDANCERATE`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `LABELS`, `labels`

*... and 24 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: last_assignment_date, total_decisions, current_suppleant_assignments, total_days_served_committee, influence_assessment

---

### Party Schema

**Fields Defined:** 42  
**Database Views Referenced:** 8  
**Sample Files Matched:** 11

#### Matched Data Files

- `view_riksdagen_party_ballot_support_annual_summary_sample.csv`
- `view_riksdagen_party_sample.csv`
- `view_riksdagen_party_decision_flow_sample.csv`
- `view_riksdagen_party_member_sample.csv`
- `view_riksdagen_party_document_daily_summary_sample.csv`
- `view_riksdagen_party_summary_sample.csv`
- `view_riksdagen_party_role_member_sample.csv`
- `view_riksdagen_party_momentum_analysis_sample.csv`
- `view_riksdagen_party_coalation_against_annual_summary_sample.csv`
- `view_riksdagen_party_signatures_document_summary_sample.csv`
- ... and 1 more

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_party_coalition_evolution`
- `riksdagen_party_vote_summary`
- `riksdagen_party_longitudinal_performance`
- `riksdagen_party_ballot_support_summary`
- `riksdagen_party_electoral_trends`

#### Field Mismatches

**Field:** `disciplineRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `disciplineRate`, `DISCIPLINERATE`, `discipline_rate`, `disciplinerate`

**Field:** `parliamentary`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `parliamentary`, `PARLIAMENTARY`

**Field:** `documents`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `documents`, `DOCUMENTS`

**Field:** `ministries`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ministries`, `MINISTRIES`

**Field:** `committeeChairs`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `committeeChairs`, `COMMITTEECHAIRS`, `committee_chairs`, `committeechairs`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

**Field:** `members`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MEMBERS`, `members`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `LABELS`, `labels`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `productivity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PRODUCTIVITY`, `productivity`

*... and 29 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: from_date, avg_docs_per_year, last_assignment_date, total_active_committee, high_activity_members

---

### Committee Schema

**Fields Defined:** 26  
**Database Views Referenced:** 3  
**Sample Files Matched:** 10

#### Matched Data Files

- `view_riksdagen_committee_roles_sample.csv`
- `view_riksdagen_committee_ballot_decision_politician_summary_sample.csv`
- `view_riksdagen_committee_decisions_sample.csv`
- `view_riksdagen_committee_ballot_decision_summary_sample.csv`
- `view_riksdagen_committee_decision_type_summary_sample.csv`
- `view_riksdagen_committee_parliament_member_proposal_sample.csv`
- `view_riksdagen_committee_ballot_decision_party_summary_sample.csv`
- `view_riksdagen_committee_role_member_sample.csv`
- `view_riksdagen_committee_decision_type_org_summary_sample.csv`
- `view_riksdagen_committee_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_committee_decision_summary`
- `riksdagen_committee_summary`

#### Field Mismatches

**Field:** `influence`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `influence`, `INFLUENCE`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `AMENDMENTS`, `amendments`

**Field:** `name`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `name`, `NAME`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

**Field:** `attendanceRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attendance_rate`, `attendancerate`, `attendanceRate`, `ATTENDANCERATE`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `LABELS`, `labels`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `productivity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PRODUCTIVITY`, `productivity`

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `CODE`

**Field:** `deputyMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DEPUTYMEMBERS`, `deputy_members`, `deputyMembers`, `deputymembers`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: from_date, total_votes, last_assignment_date, final_number, total_initiatives

---

### Ministry Schema

**Fields Defined:** 29  
**Database Views Referenced:** 2  
**Sample Files Matched:** 2

#### Matched Data Files

- `view_ministry_effectiveness_trends_sample.csv`
- `view_ministry_decision_impact_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_government_role_member_summary`

#### Field Mismatches

**Field:** `budget`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `budget`, `BUDGET`

**Field:** `effectiveness`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `EFFECTIVENESS`, `effectiveness`

**Field:** `stateSecretaries`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `STATESECRETARIES`, `state_secretaries`, `statesecretaries`, `stateSecretaries`

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERFORMANCE`, `performance`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `LABELS`, `labels`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `allocation`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ALLOCATION`, `allocation`

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `CODE`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `nameEn`, `name_en`, `nameen`, `NAMEEN`

*... and 18 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: document_change, government_bills, other_decisions, ministry_code, active_members

---

### Intelligence Schema

**Fields Defined:** 0  
**Database Views Referenced:** 10  
**Sample Files Matched:** 5

#### Matched Data Files

- `view_party_performance_metrics_sample.csv`
- `view_committee_productivity_sample.csv`
- `view_politician_risk_summary_sample.csv`
- `view_committee_productivity_matrix_sample.csv`
- `view_decision_temporal_trends_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_election_proximity_trends`
- `riksdagen_pre_election_quarterly_activity`
- `election_cycle_temporal_trends`
- `riksdagen_election_year_vs_midterm`
- `election_cycle_comparative_analysis`
- `election_cycle_predictive_intelligence`
- `riksdagen_election_year_behavioral_patterns`
- `riksdagen_q4_election_year_comparison`
- `riksdagen_election_year_anomalies`
- `election_cycle_network_analysis`

#### Recommendations

- Consider adding 20 data columns to schema: yoy_decisions_change_pct, document_change, yoy_decisions_change, approved_decisions, motions_count

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
- Column names and data types from 142 CSV sample files
- Database view references in schema documentation

**Validation includes:**
- Field name matching (with camelCase/snake_case conversion)
- Data type inference from sample data
- Coverage analysis (schema fields vs. actual columns)
- Missing view detection

**Report Generated:** 2026-01-29 03:04:14 UTC
