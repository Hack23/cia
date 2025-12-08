# JSON Schema to Database Field Mapping

**Generated:** 2025-12-08  
**Purpose:** Document explicit mappings between JSON schema fields and database columns  
**Status:** Initial mapping based on validation results

---

## Overview

This document provides explicit mappings between JSON schema fields and their corresponding database columns. It distinguishes between:
- **Direct mappings:** JSON field maps to a single database column
- **Computed fields:** JSON field is calculated from multiple columns or aggregations
- **Composite fields:** JSON field combines multiple database columns
- **Missing mappings:** Schema fields without clear database source

---

## Politician Schema Field Mappings

### Direct Mappings (JSON → Database)

| JSON Field | Database Column | Data Type | Source View |
|------------|-----------------|-----------|-------------|
| `id` | `person_id` | string | `view_riksdagen_politician` |
| `firstName` | `first_name` | string | `view_riksdagen_politician` |
| `lastName` | `last_name` | string | `view_riksdagen_politician` |
| `fullName` | `first_name` + `last_name` | string | Computed |
| `bornYear` | `born_year` | integer | `view_riksdagen_politician` |
| `gender` | `gender` | string | `view_riksdagen_politician` |
| `party` | `party` | string | `view_riksdagen_politician` |
| `status` | `active` | boolean | `view_riksdagen_politician` |
| `district` | - | string | Missing |
| `imageUrl` | - | string | Missing |
| `totalDays` | `total_days_served` | integer | `view_riksdagen_politician` |
| `totalVotes` | - | integer | `view_riksdagen_politician_ballot_summary` |
| `totalDocuments` | `total_documents` | integer | `view_riksdagen_politician_document_summary` |
| `partyLoyalty` | - | float | Computed from ballot data |

### Computed Fields (Aggregations/Calculations)

| JSON Field | Computation Logic | Source Tables/Views |
|------------|-------------------|---------------------|
| `riskScore` | Risk rule engine output | Multiple views + Drools rules |
| `riskLevel` | Categorization of riskScore | Computed from riskScore |
| `influenceScore` | Activity + votes + documents weighted | Multiple metrics |
| `rankingPosition` | Comparative ranking among politicians | Aggregation across politicians |
| `trendDirection` | Time-series analysis | Historical data comparison |
| `attendanceRate` | (Active days / Total days) * 100 | `total_days_served`, activity dates |
| `activityLevel` | Categorization of activity metrics | Multiple activity columns |

### Missing Fields (Not Found in Database)

| JSON Field | Status | Recommendation |
|------------|--------|----------------|
| `rebellions` | Missing | Add column tracking votes against party line |
| `amendments` | Missing | Count from document type filtering |
| `questions` | Missing | Count from document type filtering |
| `chairPositions` | Partial | Extract from committee role data |
| `colleagues` | Missing | Calculate from committee/party co-membership |

### Database Columns Not in Schema

| Database Column | Data Type | Recommendation |
|-----------------|-----------|----------------|
| `doc_activity_profile` | string | Consider adding as metadata |
| `collaboration_percentage` | float | Useful metric for JSON export |
| `career_phase` | string | Add to profile section |
| `current_ministry_assignments` | integer | Add to activity section |
| `total_committee_substitute_assignments` | integer | Add to committee section |

---

## Party Schema Field Mappings

### Direct Mappings (JSON → Database)

| JSON Field | Database Column | Data Type | Source View |
|------------|-----------------|-----------|-------------|
| `id` | `party` | string | `view_riksdagen_party` |
| `name` | `party` | string | `view_riksdagen_party` |
| `shortCode` | `party_short_code` | string | `view_riksdagen_party_member` |
| `website` | - | string | Missing |
| `email` | `email` | string | `view_riksdagen_party_member` |
| `memberCount` | `total_active` | integer | `view_riksdagen_party_summary` |
| `foundedYear` | - | integer | Missing |
| `currentSeats` | `current_assignments` | integer | `view_riksdagen_party_member` |

### Computed Fields

| JSON Field | Computation Logic | Source Tables/Views |
|------------|-------------------|---------------------|
| `votingCohesion` | Party member vote agreement rate | `view_riksdagen_party_ballot_support_annual_summary` |
| `coalitionAlignment` | Voting alignment with coalition partners | Cross-party voting analysis |
| `electoralTrend` | Historical electoral performance analysis | Election data + time series |
| `riskScore` | Party-level risk assessment | Multiple risk indicators |
| `stabilityScore` | Volatility analysis of party metrics | `stability_classification` |

### Database Columns Not in Schema

| Database Column | Data Type | Recommendation |
|-----------------|-----------|----------------|
| `volatility` | float | Add to analytics section |
| `stability_classification` | string | Add to intelligence section |
| `trend_direction` | string | Add to performance metrics |
| `avg_collaboration_pct` | float | Add to coalition analysis |
| `ballots_participated` | integer | Add to voting section |

---

## Committee Schema Field Mappings

### Direct Mappings (JSON → Database)

| JSON Field | Database Column | Data Type | Source View |
|------------|-----------------|-----------|-------------|
| `id` | `org_code` | string | `view_riksdagen_committee` |
| `name` | `details` | string | `view_riksdagen_committee` |
| `code` | `org_code` | string | `view_riksdagen_committee` |
| `established` | `from_date` | date | `view_riksdagen_committee` |
| `memberCount` | `current_member_size` | integer | `view_riksdagen_committee` |
| `chairPerson` | - | string | `view_riksdagen_committee_role_member` |

### Computed Fields

| JSON Field | Computation Logic | Source Tables/Views |
|------------|-------------------|---------------------|
| `productivityScore` | Decisions + proposals weighted | `view_riksdagen_committee_decisions` |
| `decisionRate` | Decisions per meeting | Committee decision data |
| `consensusLevel` | Unanimity rate in decisions | Vote breakdown analysis |

### Database Columns Not in Schema

| Database Column | Data Type | Recommendation |
|-----------------|-----------|----------------|
| `party_approved` | integer | Add to decision analysis |
| `percentage_yes` | float | Add to voting metrics |
| `approved` | integer | Add to decision tracking |
| `current_member_size` | integer | Already mapped above |

---

## Ministry Schema Field Mappings

### Direct Mappings (JSON → Database)

| JSON Field | Database Column | Data Type | Source View |
|------------|-----------------|-----------|-------------|
| `id` | `ministry_code` | string | `view_riksdagen_goverment` |
| `name` | `details` | string | `view_riksdagen_goverment` |
| `minister` | - | string | `view_riksdagen_goverment_role_member` |
| `portfolio` | - | string | Missing |

### Computed Fields

| JSON Field | Computation Logic | Source Tables/Views |
|------------|-------------------|---------------------|
| `effectivenessScore` | Multiple performance indicators | `view_ministry_effectiveness_trends` |
| `decisionImpact` | Impact assessment metrics | `view_ministry_decision_impact` |
| `budgetExecution` | Budget vs. actual spending | Financial data |

### Database Columns Not in Schema

| Database Column | Data Type | Recommendation |
|-----------------|-----------|----------------|
| `legislative_change` | float | Add to performance section |
| `productivity_level` | string | Add to analytics |
| `approval_rate` | float | Add to effectiveness metrics |
| `rejection_rate` | float | Add to effectiveness metrics |
| `documents_per_member` | float | Add to activity metrics |

---

## Intelligence Schema Field Mappings

The Intelligence schema is designed for aggregated analytics and risk assessments. Fields are primarily computed from multiple sources.

### Risk Assessment Fields

| JSON Field | Computation Logic | Source |
|------------|-------------------|--------|
| `riskScore` | Drools rule engine output | 50 behavioral rules |
| `riskCategory` | Risk score categorization | Computed |
| `riskIndicators` | Active risk flags | Rule violations |
| `riskTrend` | Risk score over time | Historical comparison |

### Temporal Trend Fields

| JSON Field | Data Source | Views Used |
|------------|-------------|------------|
| `dailyMetrics` | Daily aggregation | `*_daily_summary` views |
| `weeklyMetrics` | Weekly aggregation | `*_weekly_summary` views |
| `monthlyMetrics` | Monthly aggregation | `*_monthly_summary` views |
| `annualMetrics` | Annual aggregation | `*_annual_summary` views |

### Database Columns Available

| Database Column | Data Type | Usage |
|-----------------|-----------|-------|
| `avg_approval_rate` | float | Temporal trends |
| `avg_rebel_rate` | float | Risk assessment |
| `volatility` | float | Stability analysis |
| `trend_direction` | string | Momentum indicators |
| `violation_rate_percentage` | float | Risk scoring |

---

## Field Naming Conventions

### JSON Schema Conventions
- **camelCase** for field names: `firstName`, `totalDays`, `riskScore`
- **Arrays** for collections: `committees[]`, `documents[]`
- **Nested objects** for grouping: `labels.descriptions.short`

### Database Conventions
- **snake_case** for column names: `first_name`, `total_days`, `risk_score`
- **Prefix patterns**: `total_*`, `current_*`, `active_*`
- **Suffix patterns**: `*_date`, `*_count`, `*_percentage`

### Conversion Rules
1. **Direct conversion:** `firstName` → `first_name`
2. **Aggregation prefix:** `total` remains as `total_`
3. **Boolean flags:** Schema `active` → DB `active` (boolean)
4. **Computed fields:** Schema field has no direct DB equivalent

---

## Update Guidelines

When updating schemas or database structure:

1. **Schema Changes:**
   - Update this mapping document
   - Run validation: `python3 validate_schemas.py`
   - Verify JSON examples match mappings
   - Update API documentation

2. **Database Changes:**
   - Document new columns in this file
   - Assess impact on JSON schemas
   - Update views if necessary
   - Re-run schema validation

3. **Adding Computed Fields:**
   - Document computation logic
   - Specify source data
   - Note any dependencies
   - Provide example calculation

4. **Deprecating Fields:**
   - Mark as deprecated in schema
   - Maintain backward compatibility
   - Document migration path
   - Set removal timeline

---

## Validation Integration

This mapping document should be used in conjunction with:
- **Validation Script:** `validate_schemas.py`
- **Validation Report:** `schemas/SCHEMA_VALIDATION_REPORT.md`
- **CI/CD Workflow:** `.github/workflows/validate-json-schemas.yml`

Run validation after any mapping changes:
```bash
cd json-export-specs
python3 validate_schemas.py
```

---

## References

- [JSON Export Specs README](README.md)
- [Validation Tool Documentation](VALIDATION_README.md)
- [Database View Catalog](../DATABASE_VIEW_INTELLIGENCE_CATALOG.md)
- [Schema Validation Report](schemas/SCHEMA_VALIDATION_REPORT.md)

---

**Last Updated:** 2025-12-08  
**Maintained By:** CIA Development Team  
**Status:** Living document - update as schemas evolve
