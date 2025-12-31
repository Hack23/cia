# 🏢 Committee JSON Schema v1.0 - CSV Reality-Based

## Overview

**Version:** 1.0.0 (Reality-Based)  
**Schema ID**: `committee-profile-v1`  
**Last Updated**: 2025-12-31  
**Data Source**: `view_riksdagen_committee_sample.csv` (19 columns, actual database fields)

This schema documents the **actual available fields** from the Swedish Parliament committee database. All fields listed here are present in the sample CSV data and can be exported via JSON API.

---

## JSON Structure

```json
{
  "committeeId": "string",
  "basicInfo": { ... },
  "membershipStats": { ... },
  "documentActivity": { ... },
  "activityMetrics": { ... }
}
```

---

## Field Specifications

### 1. Basic Information (`basicInfo`)

| Field | Type | CSV Column | Description | Example |
|-------|------|------------|-------------|---------|
| `embeddedIdDetail` | string | `embedded_id_detail` | Detailed committee ID | "AU" |
| `embeddedIdOrgCode` | string | `embedded_id_org_code` | Organization code | "AU" |
| `active` | boolean | `active` | Currently active | true |

**JSON Example:**
```json
{
  "basicInfo": {
    "embeddedIdDetail": "AU",
    "embeddedIdOrgCode": "AU",
    "active": true
  }
}
```

---

### 2. Assignment History (`assignmentHistory`)

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalAssignments` | integer | `total_assignments` | Total member assignments |
| `firstAssignmentDate` | date | `first_assignment_date` | Committee inception date |
| `lastAssignmentDate` | date | `last_assignment_date` | Most recent assignment |
| `totalDaysServed` | integer | `total_days_served` | Total days committee active |

**JSON Example:**
```json
{
  "assignmentHistory": {
    "totalAssignments": 248,
    "firstAssignmentDate": "1867-01-01",
    "lastAssignmentDate": "2026-09-21",
    "totalDaysServed": 58319
  }
}
```

---

### 3. Membership Statistics (`membershipStats`)

**Current Status:**

| Field | Type | CSV Column | Description |
|-------|------|-------------|-------------|
| `currentMemberSize` | integer | `current_member_size` | Current total members |
| `currentRegularMembers` | integer | `current_regular_members` | Current regular members |
| `currentLeadershipPositions` | integer | `current_leadership_positions` | Current leadership |
| `currentSubstitutePositions` | integer | `current_substitute_positions` | Current substitutes |

**Historical Totals:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalLeadershipPositions` | integer | `total_leadership_positions` | Total leadership roles |
| `totalSubstitutePositions` | integer | `total_substitute_positions` | Total substitute roles |

**JSON Example:**
```json
{
  "membershipStats": {
    "currentMemberSize": 17,
    "currentRegularMembers": 17,
    "currentLeadershipPositions": 3,
    "currentSubstitutePositions": 0,
    "totalLeadershipPositions": 156,
    "totalSubstitutePositions": 92
  }
}
```

---

### 4. Document Activity (`documentActivity`)

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalDocuments` | integer | `total_documents` | Total documents |
| `documentsLastYear` | integer | `documents_last_year` | Documents past year |
| `avgDocumentsPerMember` | decimal | `avg_documents_per_member` | Average per member |
| `totalCommitteeMotions` | integer | `total_committee_motions` | Committee motions |
| `totalFollowUpMotions` | integer | `total_follow_up_motions` | Follow-up motions |

**JSON Example:**
```json
{
  "documentActivity": {
    "totalDocuments": 1245,
    "documentsLastYear": 87,
    "avgDocumentsPerMember": 73.2,
    "totalCommitteeMotions": 456,
    "totalFollowUpMotions": 123
  }
}
```

---

### 5. Activity Metrics (`activityMetrics`)

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `activityLevel` | string | `activity_level` | Activity classification | "high", "medium", "low" |

**JSON Example:**
```json
{
  "activityMetrics": {
    "activityLevel": "high"
  }
}
```

---

## Complete JSON Example

```json
{
  "committeeId": "AU",
  "basicInfo": {
    "embeddedIdDetail": "AU",
    "embeddedIdOrgCode": "AU",
    "active": true
  },
  "assignmentHistory": {
    "totalAssignments": 248,
    "firstAssignmentDate": "1867-01-01",
    "lastAssignmentDate": "2026-09-21",
    "totalDaysServed": 58319
  },
  "membershipStats": {
    "currentMemberSize": 17,
    "currentRegularMembers": 17,
    "currentLeadershipPositions": 3,
    "currentSubstitutePositions": 0
  },
  "documentActivity": {
    "totalDocuments": 1245,
    "documentsLastYear": 87,
    "avgDocumentsPerMember": 73.2,
    "totalCommitteeMotions": 456,
    "totalFollowUpMotions": 123
  },
  "activityMetrics": {
    "activityLevel": "high"
  }
}
```

---

## Data Quality Notes

### Available Data
✅ All 19 fields documented above are **present in sample CSV**  
✅ Data validated through automated CI checks  
✅ 100% field completeness verification  

### Not Yet Available
❌ Proposal success rates (requires vote result aggregation)  
❌ Productivity rankings (requires cross-committee comparison)  
❌ Decision flow analysis (requires process modeling)  

---

## Validation

**CSV Source**: `service.data.impl/sample-data/view_riksdagen_committee_sample.csv`  
**Field Count**: 19 columns  
**Validation Script**: `json-export-specs/validate-field-completeness.sh`  
**CI Check**: Automated via GitHub Actions  
**Status**: ✅ All 7 core fields validated

---

## Version History

- **v1.0.0** (2025-12-31): Initial reality-based schema matching actual CSV structure
- **v2.0.0** (planned): Add proposal analytics, productivity metrics, decision flows

---

**See Also:**
- [committee-schema.md](./committee-schema.md) - Aspirational v2.0 with computed fields
- [politician-schema-v1.0.md](./politician-schema-v1.0.md) - Related politician schema
