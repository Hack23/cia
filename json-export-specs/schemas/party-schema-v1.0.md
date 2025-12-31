# 🏛️ Party JSON Schema v1.0 - CSV Reality-Based

## Overview

**Version:** 1.0.0 (Reality-Based)  
**Schema ID**: `party-profile-v1`  
**Last Updated**: 2025-12-31  
**Data Source**: `view_riksdagen_party_sample.csv` (17 columns, actual database fields)

This schema documents the **actual available fields** from the Swedish Parliament party database. All fields listed here are present in the sample CSV data and can be exported via JSON API.

---

## Business Context

Powers political party analytics with **real, validated data**:
- **Political Intelligence API** - Party analysis component
- **Advanced Analytics Suite** - Party scorecards  
- **Risk Intelligence Feed** - Party-level monitoring

**Market Segments**: Political Consulting, Academic Research, Media Analysis

---

## JSON Structure

```json
{
  "partyId": "string",
  "basicInfo": { ... },
  "membershipStats": { ... },
  "documentActivity": { ... },
  "memberProfiles": { ... }
}
```

---

## Field Specifications

### 1. Basic Information (`basicInfo`)

| Field | Type | CSV Column | Description | Example |
|-------|------|------------|-------------|---------|
| `partyNumber` | integer | `party_number` | Party number | 1 |
| `partyName` | string | `party_name` | Official party name | "Vänsterpartiet" |
| `partyId` | string | `party_id` | Party code | "V" |
| `website` | string | `website` | Official website URL | "https://..." |
| `registeredDate` | date | `registered_date` | Registration date | "1917-05-13" |

**JSON Example:**
```json
{
  "basicInfo": {
    "partyNumber": 1,
    "partyName": "Vänsterpartiet",
    "partyId": "V",
    "website": "https://www.vansterpartiet.se",
    "registeredDate": "1917-05-13"
  }
}
```

---

### 2. Membership Statistics (`membershipStats`)

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `headCount` | integer | `head_count` | Total party members in parliament |

**Activity Distribution:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `veryHighActivityMembers` | integer | `very_high_activity_members` | Members with very high document output |
| `highActivityMembers` | integer | `high_activity_members` | Members with high activity |
| `mediumActivityMembers` | integer | `medium_activity_members` | Members with medium activity |
| `lowActivityMembers` | integer | `low_activity_members` | Members with low activity |

**Focus Profiles:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `partyFocusedMembers` | integer | `party_focused_members` | Members focusing on party motions |
| `committeeFocusedMembers` | integer | `committee_focused_members` | Members focusing on committee work |
| `individualFocusedMembers` | integer | `individual_focused_members` | Members with individual focus |

**JSON Example:**
```json
{
  "membershipStats": {
    "headCount": 28,
    "veryHighActivityMembers": 5,
    "highActivityMembers": 12,
    "mediumActivityMembers": 8,
    "lowActivityMembers": 3,
    "partyFocusedMembers": 15,
    "committeeFocusedMembers": 10,
    "individualFocusedMembers": 3
  }
}
```

---

### 3. Document Activity (`documentActivity`)

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalDocuments` | integer | `total_documents` | Total documents by party members |
| `avgDocumentsPerMember` | decimal | `avg_documents_per_member` | Average docs per member |
| `documentsLastYear` | integer | `documents_last_year` | Documents in past year |
| `avgCollaborationPercentage` | decimal | `avg_collaboration_percentage` | Average collaboration rate |

**JSON Example:**
```json
{
  "documentActivity": {
    "totalDocuments": 3456,
    "avgDocumentsPerMember": 123.4,
    "documentsLastYear": 428,
    "avgCollaborationPercentage": 45.2
  }
}
```

---

## Complete JSON Example

```json
{
  "partyId": "V",
  "basicInfo": {
    "partyNumber": 1,
    "partyName": "Vänsterpartiet",
    "partyId": "V",
    "website": "https://www.vansterpartiet.se",
    "registeredDate": "1917-05-13"
  },
  "membershipStats": {
    "headCount": 28,
    "veryHighActivityMembers": 5,
    "highActivityMembers": 12,
    "mediumActivityMembers": 8,
    "lowActivityMembers": 3,
    "partyFocusedMembers": 15,
    "committeeFocusedMembers": 10,
    "individualFocusedMembers": 3
  },
  "documentActivity": {
    "totalDocuments": 3456,
    "avgDocumentsPerMember": 123.4,
    "documentsLastYear": 428,
    "avgCollaborationPercentage": 45.2
  }
}
```

---

## Data Quality Notes

### Available Data
✅ All 17 fields documented above are **present in sample CSV**  
✅ Data validated through automated CI checks  
✅ 100% field completeness verification  

### Not Yet Available
❌ Coalition analysis (requires political science modeling)  
❌ Historical voting patterns (requires time-series aggregation)  
❌ Party ideology scores (requires computation layer)  

### Next Steps
See [party-schema.md](./party-schema.md) for **aspirational v2.0 schema** with coalition analytics and trend analysis.

---

## Validation

**CSV Source**: `service.data.impl/sample-data/view_riksdagen_party_sample.csv`  
**Field Count**: 17 columns  
**Validation Script**: `json-export-specs/validate-field-completeness.sh`  
**CI Check**: Automated via GitHub Actions  
**Status**: ✅ All 6 core fields validated

---

## Version History

- **v1.0.0** (2025-12-31): Initial reality-based schema matching actual CSV structure
- **v2.0.0** (planned): Add coalition analytics, trend analysis, ideology metrics

---

**See Also:**
- [party-schema.md](./party-schema.md) - Aspirational v2.0 with computed fields
- [politician-schema-v1.0.md](./politician-schema-v1.0.md) - Related politician schema
