# JSON Schema Validation Report

**Generated:** 2026-04-05T21:47:31.340461+00:00
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
| ✅ Implemented | 14 | `lastName`, `attendanceRate`, `activityLevel`, `bornYear`, `totalVotes`, `status`, `totalDays`, `firstName`... |
| ❌ Structural | 17 | `short`, `period`, `voting`, `attributes`, `performance`, `activity`, `committees`, `labels`... |
| 🔄 Planned | 14 | `district`, `trendDirection`, `imageUrl`, `ministry`, `partyLoyalty`, `influenceScore`, `absences`, `motions`... |

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
- `riksdagen_politician_vote_summary`
- `riksdagen_politician_ranking`

#### Field Mismatches

**Field:** `short`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `short`, `SHORT`

**Field:** `period`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PERIOD`, `period`

**Field:** `voting`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `VOTING`, `voting`

**Field:** `district`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DISTRICT`, `district`

**Field:** `trendDirection`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `trenddirection`, `trend_direction`, `TRENDDIRECTION`, `trendDirection`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `imageUrl`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `imageurl`, `imageUrl`, `IMAGEURL`, `image_url`

**Field:** `ministry`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `MINISTRY`, `ministry`

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performance`, `PERFORMANCE`

**Field:** `partyLoyalty`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `partyLoyalty`, `partyloyalty`, `party_loyalty`, `PARTYLOYALTY`

*... and 21 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: discipline_status, career_first_year, loyalty_rate, leadership_potential_score, career_percentile

---

### Party Schema

**Fields Defined:** 42  
**Database Views Referenced:** 8  
**Sample Files Matched:** 17

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 4 | `totalVotes`, `shortCode`, `status`, `id` |
| ❌ Structural | 15 | `predictions`, `voting`, `attributes`, `parliamentary`, `members`, `coalition`, `labels`, `descriptions`... |
| 🔄 Planned | 23 | `votePercentage`, `trend`, `websiteUrl`, `nameEn`, `strengthScore`, `foundedYear`, `stability`, `committeeChairs`... |

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

**Field:** `predictions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PREDICTIONS`, `predictions`

**Field:** `voting`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `VOTING`, `voting`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `votePercentage`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `VOTEPERCENTAGE`, `votepercentage`, `vote_percentage`, `votePercentage`

**Field:** `trend`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `TREND`, `trend`

**Field:** `websiteUrl`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `WEBSITEURL`, `websiteUrl`, `website_url`, `websiteurl`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `name_en`, `nameen`, `nameEn`, `NAMEEN`

**Field:** `strengthScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `STRENGTHSCORE`, `strengthScore`, `strength_score`, `strengthscore`

**Field:** `parliamentary`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `parliamentary`, `PARLIAMENTARY`

**Field:** `members`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `members`, `MEMBERS`

*... and 28 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: embedded_id_other_party, total, active_speaker, rank_by_size, disagre_count

---

### Committee Schema

**Fields Defined:** 26  
**Database Views Referenced:** 3  
**Sample Files Matched:** 10

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 2 | `activityLevel`, `id` |
| ❌ Structural | 8 | `attributes`, `labels`, `productivity`, `intelligence`, `relationships`, `policy`, `decisions`, `membership` |
| 🔄 Planned | 16 | `attendanceRate`, `nameEn`, `hearings`, `deputyMembers`, `code`, `reports`, `performanceScore`, `type`... |

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

**Field:** `attendanceRate`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attendanceRate`, `ATTENDANCERATE`, `attendancerate`, `attendance_rate`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `name_en`, `nameen`, `nameEn`, `NAMEEN`

**Field:** `hearings`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `hearings`, `HEARINGS`

**Field:** `deputyMembers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `deputymembers`, `DEPUTYMEMBERS`, `deputyMembers`, `deputy_members`

**Field:** `code`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `code`, `CODE`

**Field:** `reports`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `reports`, `REPORTS`

**Field:** `labels`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `LABELS`, `labels`

**Field:** `performanceScore`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performance_score`, `performancescore`, `PERFORMANCESCORE`, `performanceScore`

**Field:** `type`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `type`, `TYPE`

*... and 14 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: party_percentage_yes, total, party_no_winner, embedded_id_org, won

---

### Ministry Schema

**Fields Defined:** 29  
**Database Views Referenced:** 3  
**Sample Files Matched:** 2

#### Field Implementation Status

| Category | Count | Fields |
|----------|-------|--------|
| ✅ Implemented | 1 | `name` |
| ❌ Structural | 10 | `predictions`, `attributes`, `performance`, `labels`, `personnel`, `intelligence`, `relationships`, `policy`... |
| 🔄 Planned | 18 | `ministers`, `stateSecretaries`, `effectiveness`, `portfolio`, `trend`, `nameEn`, `decisionsImplemented`, `code`... |

#### Matched Data Files

- `view_ministry_effectiveness_trends_sample.csv`
- `view_ministry_decision_impact_sample.csv`

#### Missing Views

The following database views are referenced in the schema but not found in sample data:

- `riksdagen_government_role_member_summary`

#### Field Mismatches

**Field:** `ministers`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `ministers`, `MINISTERS`

**Field:** `predictions`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `PREDICTIONS`, `predictions`

**Field:** `stateSecretaries`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `statesecretaries`, `STATESECRETARIES`, `state_secretaries`, `stateSecretaries`

**Field:** `attributes`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `attributes`, `ATTRIBUTES`

**Field:** `effectiveness`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `EFFECTIVENESS`, `effectiveness`

**Field:** `performance`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `performance`, `PERFORMANCE`

**Field:** `portfolio`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `portfolio`, `PORTFOLIO`

**Field:** `trend`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `TREND`, `trend`

**Field:** `nameEn`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `name_en`, `nameen`, `nameEn`, `NAMEEN`

**Field:** `decisionsImplemented`
- **Issue:** Field defined in schema but not found in data
- **Suggested columns:** `DECISIONSIMPLEMENTED`, `decisionsImplemented`, `decisionsimplemented`, `decisions_implemented`

*... and 18 more mismatches*

#### Recommendations

- Consider adding 20 data columns to schema: org_code, member_change, committee, effectiveness_assessment, referred_back_proposals

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

- Consider adding 20 data columns to schema: strengths, avg_approval_rate, period_max_documents, decision_month, total_violations

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

**Report Generated:** 2026-04-05 21:47:31 UTC
