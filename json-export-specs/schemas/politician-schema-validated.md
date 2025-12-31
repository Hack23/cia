# Politician Schema (Data-Validated)

**Status:** ✅ Validated against actual sample data  
**Last Updated:** 2025-12-31  
**Fields:** 12 (only fields present in sample data)

## Overview

This schema has been automatically updated to reflect **only fields that exist in actual sample data**. 
Fields that were originally specified but not found in any data source have been removed.

**Validation Results:**
- Original fields defined: 46
- Fields validated in data: 12
- Fields removed (not in data): 34

## Data Model

```mermaid
classDiagram
    class PoliticianExport {
        +String activityLevel
        +Integer bornYear
        +String firstName
        +String gender
        +String id
        +String lastName
        +PartyLink party
        +String party
        +String status
        +Integer totalDays
        +Integer totalDocuments
        +Integer totalVotes
    }
```

## Field Descriptions

- **activityLevel** (`String`): Field found in sample data
- **bornYear** (`Integer`): Field found in sample data
- **firstName** (`String`): Field found in sample data
- **gender** (`String`): Field found in sample data
- **id** (`String`): Field found in sample data
- **lastName** (`String`): Field found in sample data
- **party** (`PartyLink`): Field found in sample data
- **party** (`String`): Field found in sample data
- **status** (`String`): Field found in sample data
- **totalDays** (`Integer`): Field found in sample data
- **totalDocuments** (`Integer`): Field found in sample data
- **totalVotes** (`Integer`): Field found in sample data


## Data Sources

This schema is validated against the following data sources:

- `view_riksdagen_politician_document_sample.csv`
- `view_riksdagen_politician_document_daily_summary_sample.csv`
- `view_riksdagen_politician_document_summary_sample.csv`
- `view_riksdagen_politician_ballot_summary_sample.csv`
- `view_riksdagen_politician_experience_summary_sample.csv`
- `view_riksdagen_politician_decision_pattern_sample.csv`
- `view_riksdagen_politician_sample.csv`


## Migration Notes

**⚠️ Breaking Changes from Original Schema:**

This schema has been significantly reduced to match actual data availability. Fields removed include:

- `absences` - Not found in any data source
- `activeDays` - Not found in any data source
- `activity` - Not found in any data source
- `amendments` - Not found in any data source
- `attendanceRate` - Not found in any data source
- `attributes` - Not found in any data source
- `breakdown` - Not found in any data source
- `byType` - Not found in any data source
- `category` - Not found in any data source
- `committees` - Not found in any data source
- `descriptions` - Not found in any data source
- `detailed` - Not found in any data source
- `district` - Not found in any data source
- `documents` - Not found in any data source
- `fullName` - Not found in any data source
- `imageUrl` - Not found in any data source
- `influenceScore` - Not found in any data source
- `intelligence` - Not found in any data source
- `labels` - Not found in any data source
- `long` - Not found in any data source
- `ministry` - Not found in any data source
- `motions` - Not found in any data source
- `partyLoyalty` - Not found in any data source
- `performance` - Not found in any data source
- `period` - Not found in any data source
- `questions` - Not found in any data source
- `rankingPosition` - Not found in any data source
- `rebellions` - Not found in any data source
- `relationships` - Not found in any data source
- `riskLevel` - Not found in any data source
- `riskScore` - Not found in any data source
- `short` - Not found in any data source
- `trendDirection` - Not found in any data source
- `voting` - Not found in any data source


**Recommendation:** Review the original schema documentation for intended functionality and determine:
1. Which removed fields should be implemented in the database
2. Which removed fields were speculative and can be permanently removed
3. Whether additional computed fields should be added to the export logic

See `SCHEMA_VALIDATION_REPORT.md` for detailed analysis.
