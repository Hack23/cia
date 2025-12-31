# Ministry Schema (Data-Validated)

**Status:** ✅ Validated against actual sample data  
**Last Updated:** 2025-12-31  
**Fields:** 1 (only fields present in sample data)

## Overview

This schema has been automatically updated to reflect **only fields that exist in actual sample data**. 
Fields that were originally specified but not found in any data source have been removed.

**Validation Results:**
- Original fields defined: 29
- Fields validated in data: 1
- Fields removed (not in data): 28

## Data Model

```mermaid
classDiagram
    class MinistryExport {
        +String name
    }
```

## Field Descriptions

- **name** (`String`): Field found in sample data


## Data Sources

This schema is validated against the following data sources:

- `view_ministry_effectiveness_trends_sample.csv`
- `view_ministry_decision_impact_sample.csv`


## Migration Notes

**⚠️ Breaking Changes from Original Schema:**

This schema has been significantly reduced to match actual data availability. Fields removed include:

- `allocation` - Not found in any data source
- `attributes` - Not found in any data source
- `budget` - Not found in any data source
- `civilServants` - Not found in any data source
- `code` - Not found in any data source
- `decisions` - Not found in any data source
- `decisionsImplemented` - Not found in any data source
- `effectiveness` - Not found in any data source
- `efficiency` - Not found in any data source
- `established` - Not found in any data source
- `executionRate` - Not found in any data source
- `headquarters` - Not found in any data source
- `id` - Not found in any data source
- `intelligence` - Not found in any data source
- `labels` - Not found in any data source
- `ministers` - Not found in any data source
- `nameEn` - Not found in any data source
- `performance` - Not found in any data source
- `performanceScore` - Not found in any data source
- `personnel` - Not found in any data source
- `policy` - Not found in any data source
- `portfolio` - Not found in any data source
- `predictions` - Not found in any data source
- `publicSatisfaction` - Not found in any data source
- `relationships` - Not found in any data source
- `spent` - Not found in any data source
- `stateSecretaries` - Not found in any data source
- `trend` - Not found in any data source


**Recommendation:** Review the original schema documentation for intended functionality and determine:
1. Which removed fields should be implemented in the database
2. Which removed fields were speculative and can be permanently removed
3. Whether additional computed fields should be added to the export logic

See `SCHEMA_VALIDATION_REPORT.md` for detailed analysis.
