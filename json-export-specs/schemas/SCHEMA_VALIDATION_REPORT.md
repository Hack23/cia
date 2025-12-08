# JSON Schema Validation Report

**Generated:** 2025-12-08T13:48:07.081887+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 132
**Total Mismatches:** 125

---

## Executive Summary

This report validates the 5 JSON export schemas against 142 real CSV sample data files 
from the CIA database to ensure schema correctness and identify gaps between 
schema definitions and actual data structure.

### Validation Scope

| Schema | Fields Defined | Views Matched | Missing Views | Field Mismatches | Status |
|--------|---------------|---------------|---------------|------------------|--------|
| Politician | 45 | 7 | 3 | 34 | ⚠️ REVIEW |
| Party | 42 | 11 | 2 | 39 | ⚠️ REVIEW |
| Committee | 26 | 10 | 2 | 24 | ⚠️ REVIEW |
| Ministry | 29 | 2 | 1 | 28 | ⚠️ REVIEW |
| Intelligence | 0 | 4 | 0 | 0 | ✅ PASS |

---

## Detailed Findings

### Politician Schema

**Fields Defined:** 45  
**Database Views Referenced:** 5  
**Sample Files Matched:** 7

#### Matched Data Files

- `view_riksdagen_politician_document_sample.csv`
- `view_riksdagen_politician_document_daily_summary_sample.csv`
- `view_riksdagen_politician_document_summary_sample.csv`
- `view_riksdagen_politician_ballot_summary_sample.csv`
- `view_riksdagen_politician_experience_summary_sample.csv`
- `view_riksdagen_politician_decision_pattern_sample.csv`
- `view_riksdagen_politician_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_politician_vote_summary`
- `riksdagen_politician_summary`
- `riksdagen_politician_ranking`

#### Field Mismatches

**Field:** `voting`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `voting`, `VOTING`

**Field:** `category`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `category`, `CATEGORY`

**Field:** `ministry`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MINISTRY`, `ministry`

**Field:** `long`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `long`, `LONG`

**Field:** `partyLoyalty`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `partyloyalty`, `PARTYLOYALTY`, `party_loyalty`, `partyLoyalty`

**Field:** `detailed`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `detailed`, `DETAILED`

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERFORMANCE`, `performance`

**Field:** `riskScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `riskscore`, `riskScore`, `RISKSCORE`, `risk_score`

**Field:** `period`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERIOD`, `period`

**Field:** `district`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DISTRICT`, `district`

*... and 24 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: roles_json, temp_label, document_type, doc_id, sub_title

---

### Party Schema

**Fields Defined:** 42  
**Database Views Referenced:** 5  
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

- `riksdagen_party_ballot_support_summary`
- `riksdagen_party_vote_summary`

#### Field Mismatches

**Field:** `voting`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `voting`, `VOTING`

**Field:** `category`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `category`, `CATEGORY`

**Field:** `color`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `color`, `COLOR`

**Field:** `websiteUrl`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `WEBSITEURL`, `website_url`, `websiteurl`, `websiteUrl`

**Field:** `electoral`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ELECTORAL`, `electoral`

**Field:** `members`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `members`, `MEMBERS`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `nameEn`, `name_en`, `NAMEEN`, `nameen`

**Field:** `legislativeSuccess`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `legislative_success`, `LEGISLATIVESUCCESS`, `legislativeSuccess`, `legislativesuccess`

**Field:** `cohesionScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `cohesionScore`, `cohesionscore`, `cohesion_score`, `COHESIONSCORE`

**Field:** `riskScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `riskscore`, `riskScore`, `RISKSCORE`, `risk_score`

*... and 29 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: person_url_xml, head_count, role_type, agree_count, ballots_participated

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

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `CODE`

**Field:** `name`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `NAME`, `name`

**Field:** `meetings`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `meetings`, `MEETINGS`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `nameEn`, `name_en`, `NAMEEN`, `nameen`

**Field:** `reports`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `REPORTS`, `reports`

**Field:** `regularMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `regular_members`, `regularmembers`, `REGULARMEMBERS`, `regularMembers`

**Field:** `attendanceRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTENDANCERATE`, `attendance_rate`, `attendanceRate`, `attendancerate`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ESTABLISHED`, `established`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `amendments`, `AMENDMENTS`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: role_type, current_member_size, temp_label, document_type, no_votes

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

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `CODE`

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERFORMANCE`, `performance`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `nameEn`, `name_en`, `NAMEEN`, `nameen`

**Field:** `decisionsImplemented`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `decisionsImplemented`, `decisionsimplemented`, `decisions_implemented`, `DECISIONSIMPLEMENTED`

**Field:** `executionRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `executionRate`, `execution_rate`, `EXECUTIONRATE`, `executionrate`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ESTABLISHED`, `established`

**Field:** `publicSatisfaction`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `publicSatisfaction`, `PUBLICSATISFACTION`, `public_satisfaction`, `publicsatisfaction`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

**Field:** `personnel`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERSONNEL`, `personnel`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `labels`, `LABELS`

*... and 18 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: member_change, short_code, documents_per_member, period_end, period_start

---

### Intelligence Schema

**Fields Defined:** 0  
**Database Views Referenced:** 0  
**Sample Files Matched:** 4

#### Matched Data Files

- `view_party_performance_metrics_sample.csv`
- `view_committee_productivity_sample.csv`
- `view_committee_productivity_matrix_sample.csv`
- `view_decision_temporal_trends_sample.csv`

#### Recommendations

- Consider adding 20 data columns to schema: violation_rate_percentage, yoy_decisions_change_pct, last_document_date, latest_member_violation, decision_quarter

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

**Report Generated:** 2025-12-08 13:48:07 UTC
