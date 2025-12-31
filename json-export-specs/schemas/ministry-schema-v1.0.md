# 🏛️ Ministry JSON Schema v1.0 - CSV Reality-Based

## Overview

**Version:** 1.0.0 (Reality-Based)  
**Schema ID**: `ministry-profile-v1`  
**Last Updated**: 2025-12-31  
**Data Source**: `view_riksdagen_goverment_sample.csv` (13 columns, actual database fields)

This schema documents the **actual available fields** from the Swedish Government ministry database. All fields listed here are present in the sample CSV data and can be exported via JSON API.

---

## JSON Structure

```json
{
  "ministryId": "string",
  "basicInfo": { ... },
  "assignmentHistory": { ... },
  "membershipStats": { ... },
  "documentActivity": { ... }
}
```

---

## Field Specifications

### 1. Basic Information (`basicInfo`)

| Field | Type | CSV Column | Description | Example |
|-------|------|------------|-------------|---------|
| `nameId` | string | `name_id` | Ministry identifier | "Finansdepartementet" |
| `active` | boolean | `active` | Currently active | true |

**JSON Example:**
```json
{
  "basicInfo": {
    "nameId": "Finansdepartementet",
    "active": true
  }
}
```

---

### 2. Assignment History (`assignmentHistory`)

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalAssignments` | integer | `total_assignments` | Total minister assignments |
| `firstAssignmentDate` | date | `first_assignment_date` | Ministry inception date |
| `lastAssignmentDate` | date | `last_assignment_date` | Most recent assignment |
| `totalDaysServed` | integer | `total_days_served` | Total days ministry active |

**JSON Example:**
```json
{
  "assignmentHistory": {
    "totalAssignments": 45,
    "firstAssignmentDate": "1876-05-20",
    "lastAssignmentDate": "2026-09-21",
    "totalDaysServed": 54873
  }
}
```

---

### 3. Membership Statistics (`membershipStats`)

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `currentMemberSize` | integer | `current_member_size` | Current minister count |

**JSON Example:**
```json
{
  "membershipStats": {
    "currentMemberSize": 3
  }
}
```

---

### 4. Document Activity (`documentActivity`)

**Document Counts:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalDocuments` | integer | `total_documents` | Total documents |
| `documentsLastYear` | integer | `documents_last_year` | Documents past year |
| `avgDocumentsPerMember` | decimal | `avg_documents_per_member` | Average per minister |

**Proposal Types:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalPropositions` | integer | `total_propositions` | Government propositions |
| `totalGovernmentBills` | integer | `total_government_bills` | Government bills |

**Activity Classification:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `activityLevel` | string | `activity_level` | Activity classification | "high", "medium", "low" |

**JSON Example:**
```json
{
  "documentActivity": {
    "totalDocuments": 892,
    "documentsLastYear": 67,
    "avgDocumentsPerMember": 297.3,
    "totalPropositions": 456,
    "totalGovernmentBills": 234,
    "activityLevel": "high"
  }
}
```

---

## Complete JSON Example

```json
{
  "ministryId": "Finansdepartementet",
  "basicInfo": {
    "nameId": "Finansdepartementet",
    "active": true
  },
  "assignmentHistory": {
    "totalAssignments": 45,
    "firstAssignmentDate": "1876-05-20",
    "lastAssignmentDate": "2026-09-21",
    "totalDaysServed": 54873
  },
  "membershipStats": {
    "currentMemberSize": 3
  },
  "documentActivity": {
    "totalDocuments": 892,
    "documentsLastYear": 67,
    "avgDocumentsPerMember": 297.3,
    "totalPropositions": 456,
    "totalGovernmentBills": 234,
    "activityLevel": "high"
  }
}
```

---

## Data Quality Notes

### Available Data
✅ All 13 fields documented above are **present in sample CSV**  
✅ Data validated through automated CI checks  
✅ 100% field completeness verification  

### Not Yet Available
❌ Minister role details (requires person-ministry join)  
❌ Policy area classification (requires manual tagging)  
❌ Decision flow metrics (requires process modeling)  
❌ Budget impact analysis (requires financial data integration)  

---

## Validation

**CSV Source**: `service.data.impl/sample-data/view_riksdagen_goverment_sample.csv`  
**Field Count**: 13 columns  
**Validation Script**: `json-export-specs/validate-field-completeness.sh`  
**CI Check**: Automated via GitHub Actions  
**Status**: ✅ All 8 core fields validated

**Note**: CSV file name contains typo ("goverment" instead of "government") - preserved for consistency with database naming.

---

## Version History

- **v1.0.0** (2025-12-31): Initial reality-based schema matching actual CSV structure
- **v2.0.0** (planned): Add minister details, policy areas, decision flows, budget impact

---

**See Also:**
- [ministry-schema.md](./ministry-schema.md) - Aspirational v2.0 with computed fields
- [politician-schema-v1.0.md](./politician-schema-v1.0.md) - Related politician schema (ministers)
