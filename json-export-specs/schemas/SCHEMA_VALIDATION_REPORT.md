# JSON Schema Validation Report

**Generated:** 2026-01-29T03:06:05.885853+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 138
**Total Mismatches:** 125

---

## Executive Summary

This report validates the 5 JSON export schemas against 138 relevant CSV sample data files 
(filtered from 210+ total files, excluding stats and distinct value tables) 
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
- `riksdagen_politician_career_path_10level`
- `riksdagen_politician_summary`
- `riksdagen_politician_career_trajectory`
- `riksdagen_politician_ranking`

#### Field Mismatches

**Field:** `riskLevel`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `RISKLEVEL`, `risklevel`, `riskLevel`, `risk_level`

**Field:** `descriptions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DESCRIPTIONS`, `descriptions`

**Field:** `fullName`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `full_name`, `FULLNAME`, `fullname`, `fullName`

**Field:** `period`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `period`, `PERIOD`

**Field:** `questions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `questions`, `QUESTIONS`

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performance`, `PERFORMANCE`

**Field:** `activeDays`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `activedays`, `activeDays`, `active_days`, `ACTIVEDAYS`

**Field:** `documents`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DOCUMENTS`, `documents`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `labels`, `LABELS`

**Field:** `category`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `CATEGORY`, `category`

*... and 24 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: party_short_code, decision_month, current_party_leader_assignments, total_leadership_days, temp_label

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

- `riksdagen_party_longitudinal_performance`
- `riksdagen_party_vote_summary`
- `riksdagen_party_electoral_trends`
- `riksdagen_party_coalition_evolution`
- `riksdagen_party_ballot_support_summary`

#### Field Mismatches

**Field:** `spectrum`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `spectrum`, `SPECTRUM`

**Field:** `stability`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `stability`, `STABILITY`

**Field:** `descriptions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DESCRIPTIONS`, `descriptions`

**Field:** `fullName`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `full_name`, `FULLNAME`, `fullname`, `fullName`

**Field:** `currentSupport`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `currentSupport`, `current_support`, `CURRENTSUPPORT`, `currentsupport`

**Field:** `members`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `members`, `MEMBERS`

**Field:** `votePercentage`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `vote_percentage`, `VOTEPERCENTAGE`, `votePercentage`, `votepercentage`

**Field:** `activityRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `activityrate`, `activityRate`, `activity_rate`, `ACTIVITYRATE`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `nameen`, `name_en`, `nameEn`, `NAMEEN`

**Field:** `parliamentary`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PARLIAMENTARY`, `parliamentary`

*... and 29 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: avg_collaboration_pct, decision_month, avg_documents_per_member, distinct_documents, year

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

- `riksdagen_committee_summary`
- `riksdagen_committee_decision_summary`

#### Field Mismatches

**Field:** `reports`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `reports`, `REPORTS`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTRIBUTES`, `attributes`

**Field:** `performanceScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performanceScore`, `performance_score`, `PERFORMANCESCORE`, `performancescore`

**Field:** `membership`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MEMBERSHIP`, `membership`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `nameen`, `name_en`, `nameEn`, `NAMEEN`

**Field:** `type`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `TYPE`, `type`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `labels`, `LABELS`

**Field:** `name`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `NAME`, `name`

**Field:** `decisions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `decisions`, `DECISIONS`

**Field:** `regularMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `regularMembers`, `regularmembers`, `REGULARMEMBERS`, `regular_members`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: end_number, approved, party_percentage_abstain, temp_label, avg_documents_per_member

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

**Field:** `effectiveness`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `effectiveness`, `EFFECTIVENESS`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTRIBUTES`, `attributes`

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performance`, `PERFORMANCE`

**Field:** `performanceScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performanceScore`, `performance_score`, `PERFORMANCESCORE`, `performancescore`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `nameen`, `name_en`, `nameEn`, `NAMEEN`

**Field:** `executionRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `executionRate`, `executionrate`, `execution_rate`, `EXECUTIONRATE`

**Field:** `headquarters`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `HEADQUARTERS`, `headquarters`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `labels`, `LABELS`

**Field:** `id`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `id`, `ID`

**Field:** `decisions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `decisions`, `DECISIONS`

*... and 18 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: org_code, earliest_proposal_date, ministry_code, other_decisions, documents_produced

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

- `election_cycle_comparative_analysis`
- `riksdagen_election_year_behavioral_patterns`
- `riksdagen_election_proximity_trends`
- `riksdagen_election_year_vs_midterm`
- `election_cycle_network_analysis`
- `riksdagen_election_year_anomalies`
- `riksdagen_q4_election_year_comparison`
- `riksdagen_pre_election_quarterly_activity`
- `election_cycle_temporal_trends`
- `election_cycle_predictive_intelligence`

#### Recommendations

- Consider adding 20 data columns to schema: chairs_count, vs_average, decision_month, latest_violation_date, period_start

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

**Report Generated:** 2026-01-29 03:06:05 UTC
