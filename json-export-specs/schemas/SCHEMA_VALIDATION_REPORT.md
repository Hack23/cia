# JSON Schema Validation Report

**Generated:** 2026-04-05T21:38:31.428700+00:00
**Schemas Validated:** 5
**Sample Files Analyzed:** 202
**Total Mismatches:** 121

---

## Executive Summary

This report validates the 5 JSON export schemas against 138 relevant CSV sample data files 
(filtered from 210+ total files, excluding stats and distinct value tables) 
from the CIA database to ensure schema correctness and identify gaps between 
schema definitions and actual data structure.

### Field Implementation Status Summary

| Category | Count | Description |
|----------|-------|-------------|
| ✅ Implemented | 21 | Fields found in database sample data |
| ❌ Structural | 50 | JSON grouping objects (not direct DB columns) |
| 🔄 Planned | 71 | Fields not yet available in data |

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
| ✅ Implemented | 14 | `lastName`, `attendanceRate`, `riskScore`, `id`, `gender`, `status`, `totalDocuments`, `riskLevel`... |
| ❌ Structural | 17 | `attributes`, `performance`, `descriptions`, `short`, `intelligence`, `byType`, `labels`, `activity`... |
| 🔄 Planned | 14 | `questions`, `motions`, `absences`, `fullName`, `rebellions`, `amendments`, `ministry`, `rankingPosition`... |

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

**Field:** `questions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `QUESTIONS`, `questions`

**Field:** `motions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `motions`, `MOTIONS`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERFORMANCE`, `performance`

**Field:** `absences`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ABSENCES`, `absences`

**Field:** `descriptions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DESCRIPTIONS`, `descriptions`

**Field:** `fullName`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `fullname`, `FULLNAME`, `full_name`, `fullName`

**Field:** `rebellions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `REBELLIONS`, `rebellions`

**Field:** `short`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `SHORT`, `short`

**Field:** `amendments`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `amendments`, `AMENDMENTS`

*... and 21 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: overall_career_rank, role_start, role_sequence, active, effectiveness_violations

---

### Party Schema

**Fields Defined:** 42  
**Database Views Referenced:** 8  
**Sample Files Matched:** 17

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 4 | `shortCode`, `id`, `status`, `totalVotes` |
| ❌ Structural | 15 | `coalition`, `attributes`, `members`, `descriptions`, `policy`, `intelligence`, `electoral`, `labels`... |
| 🔄 Planned | 23 | `currentSupport`, `ministries`, `spectrum`, `websiteUrl`, `activityRate`, `fullName`, `seats`, `nameEn`... |

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

**Field:** `currentSupport`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `currentsupport`, `currentSupport`, `CURRENTSUPPORT`, `current_support`

**Field:** `ministries`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ministries`, `MINISTRIES`

**Field:** `coalition`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `coalition`, `COALITION`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `spectrum`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `spectrum`, `SPECTRUM`

**Field:** `websiteUrl`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `website_url`, `websiteUrl`, `websiteurl`, `WEBSITEURL`

**Field:** `activityRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `activity_rate`, `ACTIVITYRATE`, `activityrate`, `activityRate`

**Field:** `members`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MEMBERS`, `members`

**Field:** `descriptions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DESCRIPTIONS`, `descriptions`

**Field:** `fullName`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `fullname`, `FULLNAME`, `full_name`, `fullName`

*... and 28 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: rank_by_approval, productivity_tier, composite_performance_index, decision_year, decision_type

---

### Committee Schema

**Fields Defined:** 26  
**Database Views Referenced:** 3  
**Sample Files Matched:** 10

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 2 | `id`, `activityLevel` |
| ❌ Structural | 8 | `attributes`, `policy`, `decisions`, `intelligence`, `labels`, `membership`, `relationships`, `productivity` |
| 🔄 Planned | 16 | `performanceScore`, `name`, `influence`, `established`, `attendanceRate`, `policyDomain`, `nameEn`, `type`... |

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

- `riksdagen_committee_summary`
- `riksdagen_committee_decision_summary`

#### Field Mismatches

**Field:** `performanceScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERFORMANCESCORE`, `performanceScore`, `performancescore`, `performance_score`

**Field:** `name`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `name`, `NAME`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `influence`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `influence`, `INFLUENCE`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ESTABLISHED`, `established`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

**Field:** `attendanceRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ATTENDANCERATE`, `attendancerate`, `attendance_rate`, `attendanceRate`

**Field:** `policyDomain`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICYDOMAIN`, `policydomain`, `policy_domain`, `policyDomain`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `NAMEEN`, `nameEn`, `name_en`, `nameen`

**Field:** `type`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `type`, `TYPE`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: active, party_percentage_abstain, total_statements, total_leadership_positions, decision_type

---

### Ministry Schema

**Fields Defined:** 29  
**Database Views Referenced:** 3  
**Sample Files Matched:** 2

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 1 | `name` |
| ❌ Structural | 10 | `attributes`, `personnel`, `performance`, `policy`, `decisions`, `intelligence`, `labels`, `budget`... |
| 🔄 Planned | 18 | `performanceScore`, `headquarters`, `civilServants`, `efficiency`, `established`, `publicSatisfaction`, `nameEn`, `decisionsImplemented`... |

#### Matched Data Files

- `view_ministry_effectiveness_trends_sample.csv`
- `view_ministry_decision_impact_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_government_role_member_summary`

#### Field Mismatches

**Field:** `performanceScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERFORMANCESCORE`, `performanceScore`, `performancescore`, `performance_score`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `personnel`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `personnel`, `PERSONNEL`

**Field:** `headquarters`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `headquarters`, `HEADQUARTERS`

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERFORMANCE`, `performance`

**Field:** `civilServants`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `civilServants`, `CIVILSERVANTS`, `civilservants`, `civil_servants`

**Field:** `efficiency`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `EFFICIENCY`, `efficiency`

**Field:** `established`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ESTABLISHED`, `established`

**Field:** `publicSatisfaction`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `public_satisfaction`, `publicsatisfaction`, `publicSatisfaction`, `PUBLICSATISFACTION`

**Field:** `policy`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `POLICY`, `policy`

*... and 18 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: approval_rate, rejection_rate, legislative_per_member, active_members, quarter_num

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

- Consider adding 20 data columns to schema: ma_30day_decisions, motions_last_year, performance_concerns, effectiveness_violations, decision_year

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

**Report Generated:** 2026-04-05 21:38:31 UTC
