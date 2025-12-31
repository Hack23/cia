# JSON Schema Validation Report

**Generated:** 2025-12-31T20:34:46.450381+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 137
**Total Mismatches:** 125

---

## Executive Summary

This report validates the 5 JSON export schemas against 142 real CSV sample data files 
from the CIA database to ensure schema correctness and identify gaps between 
schema definitions and actual data structure.

### Validation Scope

| Schema | Fields Defined | Views Matched | Missing Views | Field Mismatches | Status |
|--------|---------------|---------------|---------------|------------------|--------|
| Politician | 45 | 8 | 3 | 34 | ⚠️ REVIEW |
| Party | 42 | 11 | 2 | 39 | ⚠️ REVIEW |
| Committee | 26 | 10 | 2 | 24 | ⚠️ REVIEW |
| Ministry | 29 | 2 | 1 | 28 | ⚠️ REVIEW |
| Intelligence | 0 | 5 | 0 | 0 | ✅ PASS |

---

## Detailed Findings

### Politician Schema

**Fields Defined:** 45  
**Database Views Referenced:** 5  
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

- `riksdagen_politician_summary`
- `riksdagen_politician_vote_summary`
- `riksdagen_politician_ranking`

#### Field Mismatches

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performance`, `PERFORMANCE`

**Field:** `activity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `activity`, `ACTIVITY`

**Field:** `breakdown`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `breakdown`, `BREAKDOWN`

**Field:** `short`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `SHORT`, `short`

**Field:** `trendDirection`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `trenddirection`, `TRENDDIRECTION`, `trend_direction`, `trendDirection`

**Field:** `long`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `long`, `LONG`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTRIBUTES`, `attributes`

**Field:** `period`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `period`, `PERIOD`

**Field:** `byType`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `BYTYPE`, `by_type`, `bytype`, `byType`

**Field:** `district`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `district`, `DISTRICT`

*... and 24 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: total_leadership_days, active_eu, active_party, knowledge_areas_json, voting_days

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

- `riksdagen_party_vote_summary`
- `riksdagen_party_ballot_support_summary`

#### Field Mismatches

**Field:** `websiteUrl`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `websiteUrl`, `website_url`, `websiteurl`, `WEBSITEURL`

**Field:** `foundedYear`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `foundedYear`, `foundedyear`, `FOUNDEDYEAR`, `founded_year`

**Field:** `committeeChairs`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `committee_chairs`, `COMMITTEECHAIRS`, `committeeChairs`, `committeechairs`

**Field:** `parliamentary`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PARLIAMENTARY`, `parliamentary`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTRIBUTES`, `attributes`

**Field:** `spectrum`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `SPECTRUM`, `spectrum`

**Field:** `category`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `category`, `CATEGORY`

**Field:** `legislativeSuccess`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `legislativesuccess`, `legislativeSuccess`, `legislative_success`, `LEGISLATIVESUCCESS`

**Field:** `predictions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PREDICTIONS`, `predictions`

**Field:** `intelligence`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `intelligence`, `INTELLIGENCE`

*... and 29 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: active_eu, active_party, decision_type, total_contributing_members, trend_direction

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

**Field:** `membership`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MEMBERSHIP`, `membership`

**Field:** `policyDomain`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `policy_domain`, `POLICYDOMAIN`, `policydomain`, `policyDomain`

**Field:** `influence`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `INFLUENCE`, `influence`

**Field:** `regularMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `REGULARMEMBERS`, `regularMembers`, `regular_members`, `regularmembers`

**Field:** `name`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `name`, `NAME`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTRIBUTES`, `attributes`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ESTABLISHED`, `established`

**Field:** `intelligence`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `intelligence`, `INTELLIGENCE`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `amendments`, `AMENDMENTS`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: percentage_yes, document_status_url_www, document_url_text, decision_type, sub_title

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

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performance`, `PERFORMANCE`

**Field:** `spent`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `SPENT`, `spent`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTRIBUTES`, `attributes`

**Field:** `headquarters`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `headquarters`, `HEADQUARTERS`

**Field:** `personnel`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `personnel`, `PERSONNEL`

**Field:** `ministers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ministers`, `MINISTERS`

**Field:** `efficiency`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `efficiency`, `EFFICIENCY`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ESTABLISHED`, `established`

**Field:** `predictions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PREDICTIONS`, `predictions`

**Field:** `publicSatisfaction`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `publicsatisfaction`, `public_satisfaction`, `publicSatisfaction`, `PUBLICSATISFACTION`

*... and 18 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: committee_referral_rate, legislative_documents, government_bills, committee, staffing_status

---

### Intelligence Schema

**Fields Defined:** 0  
**Database Views Referenced:** 0  
**Sample Files Matched:** 5

#### Matched Data Files

- `view_party_performance_metrics_sample.csv`
- `view_committee_productivity_sample.csv`
- `view_politician_risk_summary_sample.csv`
- `view_committee_productivity_matrix_sample.csv`
- `view_decision_temporal_trends_sample.csv`

#### Recommendations

- Consider adding 20 data columns to schema: avg_participation_rate, productivity_assessment, total_members, annual_rebel_rate, yoy_decisions_change_pct

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

**Report Generated:** 2025-12-31 20:34:46 UTC
