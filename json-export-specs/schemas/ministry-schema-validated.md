# Ministry Schema (Data-Validated)

**Status:** ✅ Validated against actual sample data  
**Last Updated:** 2026-04-05  
**Fields:** 2 implemented (only fields present in sample data)

## Overview

This schema reflects **only fields that exist in actual sample data**. 
The 27 mismatched fields have been categorized by implementation status.

**Validation Results:**
- Original fields defined: 29
- Fields validated in data: 2
- Fields not in data: 27 (11 structural, 10 computed, 6 planned)

## Data Model

```mermaid
classDiagram
    class MinistryExport {
        +String id
        +String name
    }
```

## Field Descriptions

- **id** (`String`): Ministry identifier, found in `view_riksdagen_goverment_proposals_sample.csv`
- **name** (`String`): Field found in sample data


## Data Sources

This schema is validated against the following data sources:

- `view_riksdagen_goverment_sample.csv`
- `view_riksdagen_goverment_role_member_sample.csv`
- `view_riksdagen_goverment_proposals_sample.csv`
- `view_riksdagen_goverment_roles_sample.csv`
- `view_ministry_effectiveness_trends_sample.csv`
- `view_ministry_decision_impact_sample.csv`
- `view_ministry_productivity_matrix_sample.csv`
- `view_ministry_risk_evolution_sample.csv`


## Migration Notes

**Field Classification (27 fields not in data):**

### ❌ Structural Fields (JSON grouping objects)
- `attributes`, `labels`, `relationships`, `intelligence`, `personnel`
- `performance`, `decisions`, `policy`, `budget`, `predictions`, `trend`

### 🔀 Computable Fields (derivable from existing DB columns)
- `code` — from `org_code` or `ministry_code`
- `ministers` — count from `view_riksdagen_goverment_role_member` where role = minister
- `stateSecretaries` — count where role = state secretary
- `civilServants` — from `current_member_size`
- `performanceScore` — from `productivity_level`
- `effectiveness` — from `effectiveness_assessment`
- `decisionsImplemented` — from `approved_proposals`
- `efficiency` — from `approval_rate`
- `executionRate` — from `approval_rate`
- `established` — from `first_assignment_date`

### 🔄 Planned Fields (require new data sources)
- `nameEn` — English translation not in database
- `portfolio` — ministry portfolio classification
- `headquarters` — physical location
- `allocation` — budget allocation data not in DB
- `spent` — spending data not in DB
- `publicSatisfaction` — external survey data

**Recommendation:** See `FIELD_MAPPING.md` for implementation priority and `SCHEMA_VALIDATION_REPORT.md` for remediation plan.
