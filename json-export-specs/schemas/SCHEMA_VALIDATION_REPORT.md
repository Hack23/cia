# JSON Schema Validation Report

**Generated:** 2025-12-08T11:15:06.392882+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 132
**Total Mismatches:** 127

---

## Executive Summary

This report validates the 5 JSON export schemas against 142 real CSV sample data files from the CIA database to ensure schema correctness and identify gaps between schema definitions and actual data structure.

### Validation Scope

| Schema | Fields Defined | Views Matched | Missing Views | Field Mismatches | Status |
|--------|---------------|---------------|---------------|------------------|--------|
| Politician | 46 | 7 | 3 | 35 | ⚠️ REVIEW |
| Party | 43 | 11 | 2 | 40 | ⚠️ REVIEW |
| Committee | 26 | 10 | 2 | 24 | ⚠️ REVIEW |
| Ministry | 29 | 2 | 1 | 28 | ⚠️ REVIEW |
| Intelligence | 0 | 4 | 0 | 0 | ✅ PASS |

---

## Detailed Findings

### Politician Schema

**Fields Defined:** 46  
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

- `riksdagen_politician_summary`
- `riksdagen_politician_vote_summary`
- `riksdagen_politician_ranking`

#### Field Mismatches

**Field:** `activity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ACTIVITY`, `activity`

**Field:** `8`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `8`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `AMENDMENTS`, `amendments`

**Field:** `partyLoyalty`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `partyloyalty`, `partyLoyalty`, `PARTYLOYALTY`, `party_loyalty`

**Field:** `rankingPosition`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `rankingposition`, `ranking_position`, `RANKINGPOSITION`, `rankingPosition`

**Field:** `category`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `category`, `CATEGORY`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTRIBUTES`, `attributes`

**Field:** `voting`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `VOTING`, `voting`

**Field:** `documents`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `documents`, `DOCUMENTS`

**Field:** `district`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `district`, `DISTRICT`

*... and 25 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: rm, role_description, person_id, approved_decisions, total_days_served_party

---

### Party Schema

**Fields Defined:** 43  
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

**Field:** `8`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `8`

**Field:** `votePercentage`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `vote_percentage`, `votePercentage`, `votepercentage`, `VOTEPERCENTAGE`

**Field:** `category`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `category`, `CATEGORY`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTRIBUTES`, `attributes`

**Field:** `voting`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `VOTING`, `voting`

**Field:** `electoral`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `electoral`, `ELECTORAL`

**Field:** `committeeChairs`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `committeechairs`, `committee_chairs`, `COMMITTEECHAIRS`, `committeeChairs`

**Field:** `ministries`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ministries`, `MINISTRIES`

**Field:** `documents`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `documents`, `DOCUMENTS`

**Field:** `totalMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `totalmembers`, `TOTALMEMBERS`, `totalMembers`, `total_members`

*... and 30 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: person_id, total_follow_up_motions, total_active_parliament, website, embedded_id_party

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

**Field:** `type`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `type`, `TYPE`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `AMENDMENTS`, `amendments`

**Field:** `meetings`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MEETINGS`, `meetings`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTRIBUTES`, `attributes`

**Field:** `deputyMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `deputy_members`, `deputymembers`, `DEPUTYMEMBERS`, `deputyMembers`

**Field:** `attendanceRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attendancerate`, `ATTENDANCERATE`, `attendance_rate`, `attendanceRate`

**Field:** `totalMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `totalmembers`, `TOTALMEMBERS`, `totalMembers`, `total_members`

**Field:** `membership`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MEMBERSHIP`, `membership`

**Field:** `performanceScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performanceScore`, `PERFORMANCESCORE`, `performancescore`, `performance_score`

**Field:** `policyDomain`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `policy_domain`, `POLICYDOMAIN`, `policydomain`, `policyDomain`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: rm, abstain_votes, embedded_id_issue_nummer, person_id, final_number

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

**Field:** `decisionsImplemented`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `decisions_implemented`, `decisionsimplemented`, `decisionsImplemented`, `DECISIONSIMPLEMENTED`

**Field:** `allocation`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ALLOCATION`, `allocation`

**Field:** `headquarters`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `HEADQUARTERS`, `headquarters`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTRIBUTES`, `attributes`

**Field:** `ministers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MINISTERS`, `ministers`

**Field:** `budget`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `budget`, `BUDGET`

**Field:** `publicSatisfaction`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `public_satisfaction`, `PUBLICSATISFACTION`, `publicSatisfaction`, `publicsatisfaction`

**Field:** `civilServants`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `civilServants`, `civilservants`, `civil_servants`, `CIVILSERVANTS`

**Field:** `performanceScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performanceScore`, `PERFORMANCESCORE`, `performancescore`, `performance_score`

*... and 18 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: org_code, ma_4quarter_documents, total_proposals, rejection_rate, quarter_num

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

- Consider adding 20 data columns to schema: latest_member_violation, motions_last_year, documents_per_member, approved_decisions, period_max_documents

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

**Report Generated:** 2025-12-08 11:15:06 UTC
