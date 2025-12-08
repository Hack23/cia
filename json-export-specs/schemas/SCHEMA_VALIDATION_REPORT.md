# JSON Schema Validation Report

**Generated:** 2025-12-08T11:08:21.189931Z
**Schemas Validated:** 5
**Sample Files Analyzed:** 132
**Total Mismatches:** 126

---

## Executive Summary

This report validates the 5 JSON export schemas against 142 real CSV sample data files from the CIA database to ensure schema correctness and identify gaps between schema definitions and actual data structure.

### Validation Scope

| Schema | Fields Defined | Views Matched | Missing Views | Field Mismatches | Status |
|--------|---------------|---------------|---------------|------------------|--------|
| Politician | 46 | 7 | 3 | 35 | ⚠️ REVIEW |
| Party | 43 | 11 | 2 | 40 | ⚠️ REVIEW |
| Committee | 26 | 10 | 2 | 24 | ⚠️ REVIEW |
| Ministry | 29 | 6 | 1 | 27 | ⚠️ REVIEW |
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

- `riksdagen_politician_vote_summary`
- `riksdagen_politician_ranking`
- `riksdagen_politician_summary`

#### Field Mismatches

**Field:** `committees`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `committees`, `committees`, `committees`, `COMMITTEES`

**Field:** `rebellions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `rebellions`, `rebellions`, `rebellions`, `REBELLIONS`

**Field:** `descriptions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `descriptions`, `descriptions`, `descriptions`, `DESCRIPTIONS`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `amendments`, `amendments`, `amendments`, `AMENDMENTS`

**Field:** `8`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `8`, `8`, `8`, `8`

**Field:** `period`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `period`, `period`, `period`, `PERIOD`

**Field:** `riskLevel`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `riskLevel`, `risk_level`, `risklevel`, `RISKLEVEL`

**Field:** `trendDirection`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `trendDirection`, `trend_direction`, `trenddirection`, `TRENDDIRECTION`

**Field:** `absences`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `absences`, `absences`, `absences`, `ABSENCES`

**Field:** `questions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `questions`, `questions`, `questions`, `QUESTIONS`

*... and 25 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: current_ministry_assignments, doc_activity_profile, sub_type, no_percentage, referred_back_decisions

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

- `riksdagen_party_ballot_support_summary`
- `riksdagen_party_vote_summary`

#### Field Mismatches

**Field:** `stability`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `stability`, `stability`, `stability`, `STABILITY`

**Field:** `foundedYear`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `foundedYear`, `founded_year`, `foundedyear`, `FOUNDEDYEAR`

**Field:** `descriptions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `descriptions`, `descriptions`, `descriptions`, `DESCRIPTIONS`

**Field:** `ministries`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ministries`, `ministries`, `ministries`, `MINISTRIES`

**Field:** `votePercentage`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `votePercentage`, `vote_percentage`, `votepercentage`, `VOTEPERCENTAGE`

**Field:** `8`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `8`, `8`, `8`, `8`

**Field:** `committeeChairs`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `committeeChairs`, `committee_chairs`, `committeechairs`, `COMMITTEECHAIRS`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `nameEn`, `name_en`, `nameen`, `NAMEEN`

**Field:** `spectrum`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `spectrum`, `spectrum`, `spectrum`, `SPECTRUM`

**Field:** `strengthScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `strengthScore`, `strength_score`, `strengthscore`, `STRENGTHSCORE`

*... and 30 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: total_motions, party_focused_members, total_propositions, email, current_ministry_assignments

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

**Field:** `deputyMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `deputyMembers`, `deputy_members`, `deputymembers`, `DEPUTYMEMBERS`

**Field:** `type`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `type`, `type`, `type`, `TYPE`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `established`, `established`, `established`, `ESTABLISHED`

**Field:** `membership`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `membership`, `membership`, `membership`, `MEMBERSHIP`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `amendments`, `amendments`, `amendments`, `AMENDMENTS`

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `code`, `code`, `CODE`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `nameEn`, `name_en`, `nameen`, `NAMEEN`

**Field:** `decisions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `decisions`, `decisions`, `decisions`, `DECISIONS`

**Field:** `meetings`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `meetings`, `meetings`, `meetings`, `MEETINGS`

**Field:** `hearings`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `hearings`, `hearings`, `hearings`, `HEARINGS`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: embedded_id_org, party_approved, party, sub_type, against_proposal_number

---

### Ministry Schema

**Fields Defined:** 29  
**Database Views Referenced:** 2  
**Sample Files Matched:** 6

#### Matched Data Files

- `view_riksdagen_goverment_role_member_sample.csv`
- `view_ministry_effectiveness_trends_sample.csv`
- `view_riksdagen_goverment_proposals_sample.csv`
- `view_ministry_decision_impact_sample.csv`
- `view_riksdagen_goverment_sample.csv`
- `view_riksdagen_goverment_roles_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_government_role_member_summary`

#### Field Mismatches

**Field:** `executionRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `executionRate`, `execution_rate`, `executionrate`, `EXECUTIONRATE`

**Field:** `spent`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `spent`, `spent`, `spent`, `SPENT`

**Field:** `headquarters`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `headquarters`, `headquarters`, `headquarters`, `HEADQUARTERS`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `established`, `established`, `established`, `ESTABLISHED`

**Field:** `portfolio`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `portfolio`, `portfolio`, `portfolio`, `PORTFOLIO`

**Field:** `publicSatisfaction`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `publicSatisfaction`, `public_satisfaction`, `publicsatisfaction`, `PUBLICSATISFACTION`

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `code`, `code`, `CODE`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `nameEn`, `name_en`, `nameen`, `NAMEEN`

**Field:** `decisions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `decisions`, `decisions`, `decisions`, `DECISIONS`

**Field:** `civilServants`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `civilServants`, `civil_servants`, `civilservants`, `CIVILSERVANTS`

*... and 17 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: legislative_change, total_propositions, ministry_code, total_government_bills, productivity_level

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

- Consider adding 20 data columns to schema: avg_approval_rate, avg_rebel_rate, daily_approval_rate, party, decision_day

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

**Report Generated:** 2025-12-08 11:08:21 UTC
