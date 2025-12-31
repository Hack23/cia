# Party Schema (Data-Validated)

**Status:** ✅ Validated against actual sample data  
**Last Updated:** 2025-12-31  
**Fields:** 3 (only fields present in sample data)

## Overview

This schema has been automatically updated to reflect **only fields that exist in actual sample data**. 
Fields that were originally specified but not found in any data source have been removed.

**Validation Results:**
- Original fields defined: 43
- Fields validated in data: 3
- Fields removed (not in data): 40

## Data Model

```mermaid
classDiagram
    class PartyExport {
        +String id
        +String shortCode
        +String status
    }
```

## Field Descriptions

- **id** (`String`): Field found in sample data
- **shortCode** (`String`): Field found in sample data
- **status** (`String`): Field found in sample data


## Data Sources

This schema is validated against the following data sources:

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
- `view_riksdagen_party_document_summary_sample.csv`


## Migration Notes

**⚠️ Breaking Changes from Original Schema:**

This schema has been significantly reduced to match actual data availability. Fields removed include:

- `activityRate` - Not found in any data source
- `alignment` - Not found in any data source
- `attributes` - Not found in any data source
- `category` - Not found in any data source
- `coalition` - Not found in any data source
- `cohesionScore` - Not found in any data source
- `color` - Not found in any data source
- `committeeChairs` - Not found in any data source
- `currentSupport` - Not found in any data source
- `descriptions` - Not found in any data source
- `disciplineRate` - Not found in any data source
- `documents` - Not found in any data source
- `electoral` - Not found in any data source
- `foundedYear` - Not found in any data source
- `fullName` - Not found in any data source
- `ideology` - Not found in any data source
- `intelligence` - Not found in any data source
- `labels` - Not found in any data source
- `legislativeSuccess` - Not found in any data source
- `logoUrl` - Not found in any data source
- `members` - Not found in any data source
- `ministries` - Not found in any data source
- `nameEn` - Not found in any data source
- `parliamentary` - Not found in any data source
- `policy` - Not found in any data source
- `predictions` - Not found in any data source
- `productivity` - Not found in any data source
- `relationships` - Not found in any data source
- `riskScore` - Not found in any data source
- `seats` - Not found in any data source
- `spectrum` - Not found in any data source
- `stability` - Not found in any data source
- `strengthScore` - Not found in any data source
- `totalMembers` - Not found in any data source
- `totalVotes` - Not found in any data source
- `trend` - Not found in any data source
- `votePercentage` - Not found in any data source
- `voting` - Not found in any data source
- `websiteUrl` - Not found in any data source


**Recommendation:** Review the original schema documentation for intended functionality and determine:
1. Which removed fields should be implemented in the database
2. Which removed fields were speculative and can be permanently removed
3. Whether additional computed fields should be added to the export logic

See `SCHEMA_VALIDATION_REPORT.md` for detailed analysis.
