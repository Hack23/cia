# DATABASE_VIEW_INTELLIGENCE_CATALOG.md Validation Report

## Validation Metadata

**Date:** 2025-11-25  
**Validator:** Automated Script (validate-view-documentation.sh)  
**Source Schema:** service.data.impl/src/main/resources/full_schema.sql  
**Documentation File:** DATABASE_VIEW_INTELLIGENCE_CATALOG.md  
**Validation Method:** Automated comparison script  
**Repository:** Hack23/cia

---

## Executive Summary

This validation report confirms the current status of view documentation coverage.

### Current Status

| Metric | Value | Status |
|--------|-------|--------|
| **Total views in database** | 84 | ✓ Confirmed |
| **Total views documented** | 59 | ⚠️ Good |
| **Documentation coverage** | 70.24% | ⚠️ In Progress |
| **Views missing from documentation** | 26 | ❌ Action Required |
| **Views documented but not in DB** | 1 | ⚠️ Review Needed |

### Severity Assessment

🟡 **ACCEPTABLE**: Documentation provides **70.24% coverage** for 84 database views.


### Progress Since Previous Validation (2025-11-21)

| Metric | Previous (2025-11-21) | Current (2025-11-25) | Improvement |
|--------|----------------------|---------------------|-------------|
| **Total views in database** | 82 | 84 | +2 |
| **Total views documented** | 9 | 59 | +50 views |
| **Documentation coverage** | 10.98% | 70.24% | +59.26% |
| **Views missing from documentation** | 73 | 26 | -47 views |


---

## Missing Views

The following 26 views are in the schema but missing from documentation:

### Application Action Event Views

- `view_application_action_event_page_annual_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_daily_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_element_annual_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_element_daily_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_element_hourly_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_element_weekly_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_hourly_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_modes_annual_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_modes_daily_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_modes_hourly_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_modes_weekly_summary` ⭐⭐ LOW - User behavior analytics
- `view_application_action_event_page_weekly_summary` ⭐⭐ LOW - User behavior analytics

### Vote Data Summary Views

- `view_riksdagen_vote_data_ballot_party_summary` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_party_summary_annual` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_party_summary_daily` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_party_summary_monthly` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_party_summary_weekly` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_politician_summary` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_politician_summary_annual` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_politician_summary_monthly` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_politician_summary_weekly` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_summary` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_summary_annual` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_summary_daily` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_summary_monthly` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis
- `view_riksdagen_vote_data_ballot_summary_weekly` ⭐⭐⭐⭐⭐ VERY HIGH - Voting behavior analysis


---

## Views Documented But Not In Schema

⚠️ The following 1 views are documented but do not exist in the current schema:

- `view_decision_outcome_kpi_dashboard`

**Action Required:** Review these views and remove from documentation if they have been deprecated.


---

## Next Steps

### Immediate Actions

1. **Document Missing Views**: Add documentation for 26 missing views
2. **Prioritize High-Value Views**: Focus on vote data summary views (high intelligence value)
3. **Complete Low-Priority Views**: Document application event tracking views
4. **Re-run Validation**: Execute this script again to verify completion

### Documentation Standards

Each view should include:
- ✓ Purpose statement
- ✓ Key columns table
- ✓ At least 2 example queries
- ✓ Intelligence value rating
- ✓ Performance characteristics
- ✓ Dependencies


---

## Validation Methodology

### Automated Process

This validation is performed by `validate-view-documentation.sh`:

1. **Extract Schema Views**: Parse full_schema.sql for CREATE VIEW statements
2. **Extract Documented Views**: Parse DATABASE_VIEW_INTELLIGENCE_CATALOG.md for view headers
3. **Compare Sets**: Identify missing and extra views using set operations
4. **Calculate Coverage**: Compute percentage of documented views
5. **Generate Report**: Create this markdown report with findings

### Commands Executed

```bash
# Extract views from schema
grep -E "^CREATE (OR REPLACE )?(MATERIALIZED )?VIEW" full_schema.sql | \
  sed 's/.*VIEW //' | sed 's/ AS.*//' | sed 's/public\.//' | sort | uniq

# Extract documented views
grep -E "^### view_" DATABASE_VIEW_INTELLIGENCE_CATALOG.md | \
  sed 's/### //' | awk '{print $1}' | sort | uniq

# Compare and calculate coverage
comm -23 schema_views.txt documented_views.txt > missing_views.txt
```

### Validation Schedule

- **Automated**: Monthly via GitHub Actions (1st of each month at 02:00 UTC)
- **Manual**: Can be triggered via workflow_dispatch
- **On Changes**: Runs automatically when schema or documentation changes

---

## Changelog

| Date | Coverage | Missing Views | Status |
|------|----------|---------------|--------|
| 2025-11-21 | 10.98% | 73 | Initial validation |
| 2025-11-25 | 70.24% | 26 | ⚠️ In Progress |

---

**Report Status**: GENERATED  
**Generated By**: validate-view-documentation.sh  
**Next Validation**: 2025-12-01 02:00 UTC
