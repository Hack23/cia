# JSON Schema Validation Report

**Generated:** 2026-04-05T21:20:44.256431+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 202
**Total Mismatches:** 121

---

## Executive Summary

This report validates the 5 JSON export schemas against 138 relevant CSV sample data files 
(filtered from 210+ total files, excluding stats and distinct value tables) 
from the CIA database to ensure schema correctness and identify gaps between 
schema definitions and actual data structure.

### Validation Scope

| Schema | Fields Defined | Views Matched | Missing Views | Field Mismatches | Status |
|--------|---------------|---------------|---------------|------------------|--------|
| Politician | 45 | 12 | 3 | 31 | ⚠️ REVIEW |
| Party | 42 | 17 | 2 | 38 | ⚠️ REVIEW |
| Committee | 26 | 10 | 2 | 24 | ⚠️ REVIEW |
| Ministry | 29 | 2 | 1 | 28 | ⚠️ REVIEW |
| Intelligence | 0 | 5 | 2 | 0 | ✅ PASS |

---

## Detailed Findings

### Politician Schema

**Fields Defined:** 45  
**Database Views Referenced:** 8  
**Sample Files Matched:** 12

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

**Field:** `category`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `CATEGORY`, `category`

**Field:** `influenceScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `INFLUENCESCORE`, `influencescore`, `influence_score`, `influenceScore`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `AMENDMENTS`, `amendments`

**Field:** `activity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ACTIVITY`, `activity`

**Field:** `relationships`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `relationships`, `RELATIONSHIPS`

**Field:** `trendDirection`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `trenddirection`, `TRENDDIRECTION`, `trendDirection`, `trend_direction`

**Field:** `descriptions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DESCRIPTIONS`, `descriptions`

**Field:** `fullName`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `fullName`, `full_name`, `fullname`, `FULLNAME`

**Field:** `rebellions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `REBELLIONS`, `rebellions`

**Field:** `voting`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `voting`, `VOTING`

*... and 21 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: current_committee_chair_assignments, person_id, referred_back_decisions, current_suppleant_assignments, rm

---

### Party Schema

**Fields Defined:** 42  
**Database Views Referenced:** 8  
**Sample Files Matched:** 17

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

**Field:** `activityRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `activityRate`, `activity_rate`, `activityrate`, `ACTIVITYRATE`

**Field:** `foundedYear`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `foundedyear`, `FOUNDEDYEAR`, `foundedYear`, `founded_year`

**Field:** `ideology`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ideology`, `IDEOLOGY`

**Field:** `spectrum`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `spectrum`, `SPECTRUM`

**Field:** `color`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `COLOR`, `color`

**Field:** `productivity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `productivity`, `PRODUCTIVITY`

**Field:** `trend`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `trend`, `TREND`

**Field:** `category`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `CATEGORY`, `category`

**Field:** `websiteUrl`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `websiteUrl`, `websiteurl`, `website_url`, `WEBSITEURL`

**Field:** `logoUrl`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `logo_url`, `logourl`, `logoUrl`, `LOGOURL`

*... and 28 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: no_rate, coalition_strength, prev_semester_alignment, is_election_cycle_end, last_party_document

---

### Committee Schema

**Fields Defined:** 26  
**Database Views Referenced:** 3  
**Sample Files Matched:** 10

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

**Field:** `influence`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `influence`, `INFLUENCE`

**Field:** `attendanceRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attendance_rate`, `ATTENDANCERATE`, `attendanceRate`, `attendancerate`

**Field:** `decisions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DECISIONS`, `decisions`

**Field:** `productivity`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `productivity`, `PRODUCTIVITY`

**Field:** `type`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `type`, `TYPE`

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `CODE`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `AMENDMENTS`, `amendments`

**Field:** `relationships`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `relationships`, `RELATIONSHIPS`

**Field:** `membership`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `membership`, `MEMBERSHIP`

**Field:** `meetings`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MEETINGS`, `meetings`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: embedded_id_org_code, current_regular_members, person_id, approved, rm

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

**Field:** `ministers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MINISTERS`, `ministers`

**Field:** `decisions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DECISIONS`, `decisions`

**Field:** `publicSatisfaction`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `publicsatisfaction`, `PUBLICSATISFACTION`, `public_satisfaction`, `publicSatisfaction`

**Field:** `personnel`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `personnel`, `PERSONNEL`

**Field:** `decisionsImplemented`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `decisionsimplemented`, `decisionsImplemented`, `decisions_implemented`, `DECISIONSIMPLEMENTED`

**Field:** `trend`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `trend`, `TREND`

**Field:** `id`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ID`, `id`

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `CODE`

**Field:** `relationships`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `relationships`, `RELATIONSHIPS`

**Field:** `civilServants`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `civil_servants`, `CIVILSERVANTS`, `civilServants`, `civilservants`

*... and 18 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: documents_per_member, government_bills, propositions, effectiveness_assessment, rejected_proposals

---

### Intelligence Schema

**Fields Defined:** 0  
**Database Views Referenced:** 10  
**Sample Files Matched:** 5

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

- Consider adding 20 data columns to schema: person_id, referred_back_decisions, total_decisions_all_time, documents_last_year, chairs_count

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

**Report Generated:** 2026-04-05 21:20:44 UTC
