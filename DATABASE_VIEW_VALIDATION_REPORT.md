# DATABASE_VIEW_INTELLIGENCE_CATALOG.md Validation Report

## Validation Metadata

**Date:** 2025-11-21  
**Validator:** Intelligence Operative (Copilot Agent)  
**Source Schema:** service.data.impl/src/main/resources/full_schema.sql  
**Documentation File:** DATABASE_VIEW_INTELLIGENCE_CATALOG.md  
**Validation Method:** Automated comparison of view names in schema vs. documentation  
**Schema Validation Report:** service.data.impl/sample-data/schema_validation_20251121_142510.txt  
**Health Check Report:** service.data.impl/sample-data/health_check_20251121_143220.txt

---

## Executive Summary

This validation report identifies significant discrepancies between the documented database views in DATABASE_VIEW_INTELLIGENCE_CATALOG.md and the actual database schema defined in full_schema.sql.

### Critical Findings

| Metric | Value | Status |
|--------|-------|--------|
| **Total views in database** | 82 | ✓ Confirmed (54 regular + 28 materialized) |
| **Total views documented** | 9 | ❌ **Critical Gap** |
| **Documentation coverage** | 10.98% | ❌ **Severely Inadequate** |
| **Views missing from documentation** | 73 | ❌ **Major Issue** |
| **Views documented but not in DB** | 0 | ✓ Good |
| **Empty views requiring investigation** | 9 | ⚠️ **Action Required** |
| **Materialized views never vacuumed** | 10 | ⚠️ **Performance Impact** |
| **Materialized views never refreshed** | 4 | ⚠️ **Data Freshness Issue** |
| **Missing indexes on foreign keys** | 68 | ⚠️ **Performance Impact** |
| **Database health score** | 78.37/100 | ⚠️ **Good - Monitor warnings** |
| **Matching views requiring validation** | 9 | ⚠️ Needs column/example validation |

### Severity Assessment

🔴 **CRITICAL**: The documentation claims "80+ database views" in the executive summary but only documents 9 views (11.25% coverage). This represents a **major documentation debt** that severely limits the utility of the catalog for intelligence operations and developer onboarding.

---

## Detailed Findings

### 1. Views Missing from Documentation (71 views)

These views exist in the database but are completely undocumented in DATABASE_VIEW_INTELLIGENCE_CATALOG.md:

#### Application & Audit Views (14 views)
1. `view_application_action_event_page_annual_summary`
2. `view_application_action_event_page_daily_summary`
3. `view_application_action_event_page_element_annual_summary`
4. `view_application_action_event_page_element_daily_summary`
5. `view_application_action_event_page_element_hourly_summary`
6. `view_application_action_event_page_element_weekly_summary`
7. `view_application_action_event_page_hourly_summary`
8. `view_application_action_event_page_modes_annual_summary`
9. `view_application_action_event_page_modes_daily_summary`
10. `view_application_action_event_page_modes_hourly_summary`
11. `view_application_action_event_page_modes_weekly_summary`
12. `view_application_action_event_page_weekly_summary`
13. `view_audit_author_summary`
14. `view_audit_data_summary`

**Intelligence Value**: ⭐⭐ LOW - Administrative/audit tracking, user behavior analytics

#### Committee Views (11 views)
15. `view_committee_productivity`
16. `view_committee_productivity_matrix`
17. `view_riksdagen_committee`
18. `view_riksdagen_committee_ballot_decision_party_summary`
19. `view_riksdagen_committee_ballot_decision_politician_summary`
20. `view_riksdagen_committee_ballot_decision_summary`
21. `view_riksdagen_committee_decision_type_org_summary`
22. `view_riksdagen_committee_decision_type_summary`
23. `view_riksdagen_committee_decisions`
24. `view_riksdagen_committee_parliament_member_proposal`
25. `view_riksdagen_committee_role_member`

**Intelligence Value**: ⭐⭐⭐⭐ HIGH - Committee effectiveness analysis, productivity metrics

#### Committee Roles (1 view)
26. `view_riksdagen_committee_roles`

**Intelligence Value**: ⭐⭐⭐ MEDIUM - Committee membership tracking

#### Ministry/Government Views (4 views)
27. `view_ministry_effectiveness_trends`
28. `view_ministry_productivity_matrix`
29. `view_ministry_risk_evolution`
30. `view_riksdagen_goverment` (note: spelling in schema)

**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Government performance assessment

#### Government-Related Views (3 views)
31. `view_riksdagen_goverment_proposals`
32. `view_riksdagen_goverment_role_member`
33. `view_riksdagen_goverment_roles`

**Intelligence Value**: ⭐⭐⭐⭐ HIGH - Government composition and policy initiatives

#### Document Views (3 views)
34. `view_document_data_committee_report_url`
35. `view_riksdagen_org_document_daily_summary`
36. `view_riksdagen_document_type_daily_summary`

**Intelligence Value**: ⭐⭐⭐ MEDIUM - Document tracking and productivity

#### Intelligence Dashboard & Risk Views (3 views)
37. `view_riksdagen_intelligence_dashboard`
38. `view_riksdagen_crisis_resilience_indicators`
39. `view_riksdagen_voting_anomaly_detection`

**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Critical intelligence products

#### Politician Views (3 views)
40. `view_politician_risk_summary`
41. `view_riksdagen_politician_ballot_summary`
42. `view_riksdagen_politician_influence_metrics`

**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Politician risk assessment and influence mapping

#### Politician Document Views (2 views)
43. `view_riksdagen_politician_document_daily_summary`
44. `view_riksdagen_politician_document_summary`

**Intelligence Value**: ⭐⭐⭐⭐ HIGH - Legislative productivity analysis

#### Party Views (9 views)
45. `view_party_performance_metrics`
46. `view_riksdagen_party_ballot_support_annual_summary`
47. `view_riksdagen_party_coalation_against_annual_summary` (note: typo "coalation")
48. `view_riksdagen_party_document_daily_summary`
49. `view_riksdagen_party_document_summary`
50. `view_riksdagen_party_member`
51. `view_riksdagen_party_momentum_analysis`
52. `view_riksdagen_party_role_member`
53. `view_riksdagen_party_signatures_document_summary`

**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Party performance, momentum, coalition analysis

#### Party Summary (1 view)
54. `view_riksdagen_party_summary`

**Intelligence Value**: ⭐⭐⭐⭐ HIGH - Party overview metrics

#### Member Proposals (1 view)
55. `view_riksdagen_member_proposals`

**Intelligence Value**: ⭐⭐⭐ MEDIUM - Legislative initiative tracking

#### Person/Signature Views (1 view)
56. `view_riksdagen_person_signed_document_summary`

**Intelligence Value**: ⭐⭐⭐ MEDIUM - Document signature analysis

#### Vote Data Views - Party Level (5 views)
57. `view_riksdagen_vote_data_ballot_party_summary`
58. `view_riksdagen_vote_data_ballot_party_summary_annual`
59. `view_riksdagen_vote_data_ballot_party_summary_daily`
60. `view_riksdagen_vote_data_ballot_party_summary_monthly`
61. `view_riksdagen_vote_data_ballot_party_summary_weekly`

**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Party voting behavior analysis

#### Vote Data Views - Politician Level (4 views)
62. `view_riksdagen_vote_data_ballot_politician_summary`
63. `view_riksdagen_vote_data_ballot_politician_summary_annual`
64. `view_riksdagen_vote_data_ballot_politician_summary_monthly`
65. `view_riksdagen_vote_data_ballot_politician_summary_weekly`

**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Individual voting record analysis

#### Vote Data Views - Ballot Level (5 views)
66. `view_riksdagen_vote_data_ballot_summary`
67. `view_riksdagen_vote_data_ballot_summary_annual`
68. `view_riksdagen_vote_data_ballot_summary_daily`
69. `view_riksdagen_vote_data_ballot_summary_monthly`
70. `view_riksdagen_vote_data_ballot_summary_weekly`

**Intelligence Value**: ⭐⭐⭐⭐ HIGH - Ballot outcome analysis

#### WorldBank Data (1 view)
71. `view_worldbank_indicator_data_country_summary`

**Intelligence Value**: ⭐⭐⭐ MEDIUM - Economic context for political analysis

---

### 2. Views Documented but Not in Database

✓ **No issues found** - All 9 documented views exist in the database schema.

---

### 3. Views Present in Both (Requiring Validation)

These 9 views are documented and exist in the database. Their documentation should be validated for accuracy:

1. ✓ `view_party_effectiveness_trends`
2. ✓ `view_politician_behavioral_trends`
3. ✓ `view_riksdagen_coalition_alignment_matrix`
4. ✓ `view_riksdagen_party`
5. ✓ `view_riksdagen_politician`
6. ✓ `view_riksdagen_politician_document`
7. ✓ `view_riksdagen_politician_experience_summary`
8. ✓ `view_riksdagen_vote_data_ballot_politician_summary_daily`
9. ✓ `view_risk_score_evolution`

**Validation Status**: ⚠️ Column lists, SQL examples, and row counts need validation against actual database

---

## Impact Assessment

### Operational Impact

| Area | Impact Level | Description |
|------|-------------|-------------|
| **Intelligence Operations** | 🔴 CRITICAL | Analysts lack documentation for 89% of available views |
| **Developer Onboarding** | 🔴 CRITICAL | New developers cannot discover/understand most views |
| **Query Optimization** | 🟡 MEDIUM | Missing performance characteristics for 71 views |
| **Risk Analysis** | 🔴 CRITICAL | Key risk/intelligence views undocumented |
| **Coalition Analysis** | 🟡 MEDIUM | Party/voting views largely undocumented |
| **Government Monitoring** | 🔴 CRITICAL | Ministry/government views completely undocumented |

### Intelligence Capability Gaps

The following high-value intelligence capabilities are hidden due to missing documentation:

1. **Government Performance Assessment**: Ministry effectiveness, productivity, risk views undocumented
2. **Advanced Risk Detection**: Crisis resilience, voting anomaly, politician risk views undocumented
3. **Coalition Analysis**: Party momentum, coalition support views undocumented
4. **Intelligence Dashboard**: Unified dashboard view undocumented
5. **Network Analysis**: Politician influence metrics undocumented
6. **Temporal Analysis**: 20+ daily/weekly/monthly/annual summary views undocumented

---

## Recommendations

### Priority 1: Critical Intelligence Views (Immediate - 2-3 hours)

Document these 13 high-value intelligence views immediately:

1. `view_riksdagen_intelligence_dashboard` ⭐⭐⭐⭐⭐
2. `view_riksdagen_crisis_resilience_indicators` ⭐⭐⭐⭐⭐
3. `view_riksdagen_voting_anomaly_detection` ⭐⭐⭐⭐⭐
4. `view_politician_risk_summary` ⭐⭐⭐⭐⭐
5. `view_riksdagen_politician_influence_metrics` ⭐⭐⭐⭐⭐
6. `view_ministry_effectiveness_trends` ⭐⭐⭐⭐⭐
7. `view_ministry_productivity_matrix` ⭐⭐⭐⭐⭐
8. `view_ministry_risk_evolution` ⭐⭐⭐⭐⭐
9. `view_party_performance_metrics` ⭐⭐⭐⭐⭐
10. `view_riksdagen_party_momentum_analysis` ⭐⭐⭐⭐⭐
11. `view_riksdagen_coalition_alignment_matrix` (validate existing)
12. `view_party_effectiveness_trends` (validate existing)
13. `view_politician_behavioral_trends` (validate existing)

### Priority 2: Vote & Party Analysis Views (Next - 3-4 hours)

Document all vote data summary views (20 views) and remaining party views (8 views):
- All ballot party summaries (daily/weekly/monthly/annual)
- All ballot politician summaries (daily/weekly/monthly/annual)
- All ballot summaries (daily/weekly/monthly/annual)
- Party document, support, and coalition views

### Priority 3: Committee & Government Views (After Priority 2 - 2-3 hours)

Document committee and government views (15 views):
- Committee productivity, decisions, roles
- Government proposals, roles, membership

### Priority 4: Supporting Views (Low Priority - 1-2 hours)

Document remaining views (23 views):
- Application action event tracking (12 views)
- Audit views (2 views)
- Document views (3 views)
- Miscellaneous (6 views)

---

## Validation Methodology

### Tools Used
- `grep` for extracting view names from schema and documentation
- `comm` for set comparison operations
- Schema file: `service.data.impl/src/main/resources/full_schema.sql`
- Documentation file: `DATABASE_VIEW_INTELLIGENCE_CATALOG.md`

### Commands Executed
```bash
# Extract views from schema
grep -E "CREATE (MATERIALIZED )?VIEW" full_schema.sql | \
  sed 's/.*VIEW public\.//' | sed 's/ AS.*//' | sort > actual_views.txt

# Extract views from documentation
grep -E "^### view_" DATABASE_VIEW_INTELLIGENCE_CATALOG.md | \
  sed 's/^### //' | cut -d' ' -f1 | sort > documented_views.txt

# Compare lists
comm -23 actual_views.txt documented_views.txt > missing_in_docs.txt
comm -13 actual_views.txt documented_views.txt > missing_in_db.txt
comm -12 actual_views.txt documented_views.txt > views_to_validate.txt
```

### Known Limitations

1. **Column Validation**: This report does not validate column names/types for the 9 documented views against the actual schema
2. **SQL Example Testing**: SQL examples in documentation have not been tested against the database
3. **Row Count Accuracy**: Row count estimates have not been verified
4. **Performance Metrics**: Performance characteristics have not been validated
5. **Materialized View Identification**: Report does not distinguish between standard views and materialized views in the missing list

---

## Next Steps

### Immediate Actions Required

1. **Document Critical Intelligence Views**: Add documentation for the 13 Priority 1 views
2. **Update Executive Summary**: Correct the "80+ views" claim with accurate coverage statistics
3. **Add Validation Metadata**: Include "Last Validated" date on each view entry
4. **Create View Index**: Add alphabetical index at the beginning for quick reference

### Validation Script Enhancement

Create an automated validation script that:
- Extracts column definitions from actual views
- Tests SQL examples against database
- Validates row counts
- Identifies materialized vs. standard views
- Generates differential reports on schema changes

### Documentation Standards

Establish minimum documentation requirements for each view:
- ✓ Purpose statement
- ✓ Key columns table
- ✓ At least 2 example queries
- ✓ Performance characteristics
- ✓ Intelligence value rating
- ✓ Dependencies
- ✓ Risk rules supported
- ✓ Intelligence frameworks applicable
- ✓ Last validated date

---

## Changelog of This Validation

| Date | Change | Validator |
|------|--------|-----------|
| 2025-11-20 | Initial validation report created | Intelligence Operative (Copilot) |
| 2025-11-20 | Identified 71 undocumented views | Intelligence Operative (Copilot) |
| 2025-11-20 | Categorized missing views by type | Intelligence Operative (Copilot) |
| 2025-11-20 | Assigned preliminary intelligence value ratings | Intelligence Operative (Copilot) |

---

## Appendix A: Materialized Views from refresh-all-views.sql

The following 28 views are listed as materialized views requiring refresh:

1. view_worldbank_indicator_data_country_summary ✓
2. view_riksdagen_politician_document ✓
3. view_riksdagen_org_document_daily_summary
4. view_riksdagen_document_type_daily_summary
5. view_riksdagen_committee_decisions
6. view_riksdagen_vote_data_ballot_summary
7. view_riksdagen_vote_data_ballot_summary_daily
8. view_riksdagen_committee_ballot_decision_summary
9. view_riksdagen_vote_data_ballot_party_summary
10. view_riksdagen_vote_data_ballot_party_summary_daily
11. view_riksdagen_vote_data_ballot_party_summary_monthly
12. view_riksdagen_vote_data_ballot_party_summary_weekly
13. view_riksdagen_vote_data_ballot_party_summary_annual
14. view_riksdagen_vote_data_ballot_summary_annual
15. view_riksdagen_vote_data_ballot_summary_monthly
16. view_riksdagen_vote_data_ballot_summary_weekly
17. view_riksdagen_vote_data_ballot_politician_summary
18. view_riksdagen_vote_data_ballot_politician_summary_daily ✓
19. view_riksdagen_vote_data_ballot_politician_summary_annual
20. view_riksdagen_vote_data_ballot_politician_summary_monthly
21. view_riksdagen_vote_data_ballot_politician_summary_weekly
22. view_riksdagen_committee_ballot_decision_party_summary
23. view_riksdagen_committee_ballot_decision_politician_summary
24. view_riksdagen_committee_decision_type_org_summary
25. view_riksdagen_committee_decision_type_summary
26. view_riksdagen_party_document_daily_summary
27. view_riksdagen_politician_document_daily_summary
28. view_riksdagen_politician_document_summary

**Status**: 3/28 documented (10.7%), 25/28 undocumented (89.3%)

✓ = Documented in DATABASE_VIEW_INTELLIGENCE_CATALOG.md

---

## Appendix B: View Categories Summary

| Category | Total Views | Documented | Missing | Coverage |
|----------|-------------|------------|---------|----------|
| **Intelligence/Risk** | 6 | 3 | 3 | 50% |
| **Politician** | 8 | 4 | 4 | 50% |
| **Party** | 12 | 2 | 10 | 17% |
| **Vote Data** | 20 | 1 | 19 | 5% |
| **Committee** | 12 | 0 | 12 | 0% |
| **Government/Ministry** | 7 | 0 | 7 | 0% |
| **Document** | 3 | 1 | 2 | 33% |
| **Application/Audit** | 14 | 0 | 14 | 0% |
| **WorldBank** | 1 | 0 | 1 | 0% |
| **TOTAL** | **80** | **9** | **71** | **11.25%** |

---

**Report Status**: COMPLETE  
**Action Required**: IMMEDIATE - Begin Priority 1 documentation
