# 🔍 Intelligence JSON Schema v1.0 - CSV Reality-Based

## Overview

**Version:** 1.0.0 (Reality-Based)  
**Schema ID**: `intelligence-rule-violation-v1`  
**Last Updated**: 2025-12-31  
**Data Source**: `table_rule_violation_sample.csv` (10 columns, actual database fields)

This schema documents the **actual available fields** from the rule violation intelligence database. All fields listed here are present in the sample CSV data and can be exported via JSON API.

---

## JSON Structure

```json
{
  "violationId": "integer",
  "detection": { ... },
  "target": { ... },
  "rule": { ... },
  "status": { ... }
}
```

---

## Field Specifications

### 1. Violation Identification (`violationId`)

| Field | Type | CSV Column | Description | Example |
|-------|------|------------|-------------|---------|
| `id` | integer | `id` | Unique violation ID | 12345 |

---

### 2. Detection Information (`detection`)

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `detectedDate` | datetime | `detected_date` | When violation was detected |

**JSON Example:**
```json
{
  "detection": {
    "detectedDate": "2024-11-15T14:30:00Z"
  }
}
```

---

### 3. Target Information (`target`)

| Field | Type | CSV Column | Description | Example |
|-------|------|------------|-------------|---------|
| `referenceId` | string | `reference_id` | Entity reference ID | "0761174174409" |
| `name` | string | `name` | Entity name | "Seija Viitamäki-Carlsson" |
| `resourceType` | string | `resource_type` | Type of entity | "Politician", "Party", "Committee" |

**JSON Example:**
```json
{
  "target": {
    "referenceId": "0761174174409",
    "name": "Seija Viitamäki-Carlsson",
    "resourceType": "Politician"
  }
}
```

---

### 4. Rule Information (`rule`)

| Field | Type | CSV Column | Description | Example |
|-------|------|------------|-------------|---------|
| `ruleName` | string | `rule_name` | Rule identifier | "POLITICIAN_LOW_ACTIVITY" |
| `ruleDescription` | string | `rule_description` | Rule explanation | "Politician has less than 5 documents per year" |
| `ruleGroup` | string | `rule_group` | Rule category | "ActivityMonitoring", "ComplianceCheck" |

**JSON Example:**
```json
{
  "rule": {
    "ruleName": "POLITICIAN_LOW_ACTIVITY",
    "ruleDescription": "Politician has less than 5 documents per year",
    "ruleGroup": "ActivityMonitoring"
  }
}
```

---

### 5. Status Information (`status`)

| Field | Type | CSV Column | Description | Values |
|-------|------|------------|-------------|--------|
| `status` | string | `status` | Violation status | "OPEN", "CLOSED", "ACKNOWLEDGED" |
| `positive` | boolean | `positive` | Positive or negative indicator | true (positive), false (violation) |

**JSON Example:**
```json
{
  "status": {
    "status": "OPEN",
    "positive": false
  }
}
```

---

## Complete JSON Example

```json
{
  "violationId": 12345,
  "detection": {
    "detectedDate": "2024-11-15T14:30:00Z"
  },
  "target": {
    "referenceId": "0761174174409",
    "name": "Seija Viitamäki-Carlsson",
    "resourceType": "Politician"
  },
  "rule": {
    "ruleName": "POLITICIAN_LOW_ACTIVITY",
    "ruleDescription": "Politician has less than 5 documents per year",
    "ruleGroup": "ActivityMonitoring"
  },
  "status": {
    "status": "OPEN",
    "positive": false
  }
}
```

---

## Rule Types

### Available Rule Groups
Based on `rule_group` field values in sample data:

- **ActivityMonitoring** - Document activity, attendance tracking
- **ComplianceCheck** - Regulatory compliance violations
- **EthicsReview** - Ethical concerns, conflicts of interest
- **TransparencyAudit** - Disclosure requirements, transparency issues
- **PerformanceMetric** - Performance benchmarks, productivity standards

### Common Rule Names
Examples from actual data:

- `POLITICIAN_LOW_ACTIVITY` - Low document output
- `POLITICIAN_NO_COMMITTEE` - No active committee assignments
- `PARTY_LOW_DIVERSITY` - Low gender/age diversity
- `COMMITTEE_INACTIVE` - No recent activity
- `MINISTRY_DELAYED_RESPONSE` - Slow response to inquiries

---

## Data Quality Notes

### Available Data
✅ All 10 fields documented above are **present in sample CSV**  
✅ Data validated through automated CI checks  
✅ 100% field completeness verification  
✅ 45 active intelligence rules (as of 2024-11)

### Not Yet Available
❌ Risk scores (requires scoring algorithm)  
❌ Trend analysis (requires time-series aggregation)  
❌ Predictive alerts (requires ML model)  
❌ Impact assessment (requires correlation analysis)  

### Data Characteristics
- **Source**: `table_rule_violation` (table, not view)
- **Update Frequency**: Real-time as rules trigger
- **Retention**: Historical violations retained
- **Coverage**: All entity types (politicians, parties, committees, ministries)

---

## Validation

**CSV Source**: `service.data.impl/sample-data/table_rule_violation_sample.csv`  
**Field Count**: 10 columns  
**Validation Script**: `json-export-specs/validate-field-completeness.sh`  
**CI Check**: Automated via GitHub Actions  
**Status**: ✅ All 9 core fields validated

**Note**: Source is a table, not a view (different from other schemas).

---

## Version History

- **v1.0.0** (2025-12-31): Initial reality-based schema matching actual CSV structure
- **v2.0.0** (planned): Add risk scoring, trend analysis, predictive alerts, impact metrics

---

## Integration

### Related Schemas
Intelligence violations reference entities from:
- **Politician** (`resource_type=Politician`, `reference_id=person_id`)
- **Party** (`resource_type=Party`, `reference_id=party_id`)
- **Committee** (`resource_type=Committee`, `reference_id=org_code`)
- **Ministry** (`resource_type=Ministry`, `reference_id=name_id`)

### Product Integration
Powers **Risk Intelligence Feed** (€1.77M revenue potential):
- Real-time compliance monitoring
- 45 active intelligence rules
- Multi-entity violation tracking
- Historical audit trail

---

**See Also:**
- [intelligence-schema.md](./intelligence-schema.md) - Aspirational v2.0 with risk scoring
- [politician-schema-v1.0.md](./politician-schema-v1.0.md) - Related politician schema
- [BUSINESS_PRODUCT_DOCUMENT.md](../../BUSINESS_PRODUCT_DOCUMENT.md) - Product strategy
