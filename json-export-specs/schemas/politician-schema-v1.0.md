# 👤 Politician JSON Schema v1.0 - CSV Reality-Based

## Overview

**Version:** 1.0.0 (Reality-Based)  
**Schema ID**: `politician-profile-v1`  
**Last Updated**: 2025-12-31  
**Data Source**: `view_riksdagen_politician_sample.csv` (65 columns, actual database fields)

This schema documents the **actual available fields** from the Swedish Parliament (Riksdagen) politician database. All fields listed here are present in the sample CSV data and can be exported via JSON API.

---

## Business Context

Powers political intelligence products with **real, validated data**:
- **Political Intelligence API** - €630K revenue potential
- **Advanced Analytics Suite** - €855K revenue potential  
- **Risk Intelligence Feed** - €1.77M revenue potential

**Market Segments**: Political Consulting (€15M TAM), Academic Research (€5M TAM), Media (€8M TAM)

---

## JSON Structure

```json
{
  "politicianId": "string",
  "personalInfo": { ... },
  "politicalActivity": { ... },
  "documentActivity": { ... },
  "assignments": { ... }
}
```

---

## Field Specifications

### 1. Personal Information (`personalInfo`)

**Available Fields** (direct from database):

| Field | Type | CSV Column | Description | Example |
|-------|------|------------|-------------|---------|
| `personId` | string | `person_id` | Unique person identifier | "0761174174409" |
| `firstName` | string | `first_name` | Given name | "Seija" |
| `lastName` | string | `last_name` | Family name | "Viitamäki-Carlsson" |
| `party` | string | `party` | Party affiliation code | "V", "S", "M" |
| `bornYear` | integer | `born_year` | Birth year | 1948 |
| `gender` | string | `gender` | Gender | "KVINNA", "MAN" |

**JSON Example:**
```json
{
  "personalInfo": {
    "personId": "0761174174409",
    "firstName": "Seija",
    "lastName": "Viitamäki-Carlsson",
    "party": "V",
    "bornYear": 1948,
    "gender": "KVINNA"
  }
}
```

---

### 2. Political Activity (`politicalActivity`)

**Service Duration Fields:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `firstAssignmentDate` | date | `first_assignment_date` | First day in parliament |
| `lastAssignmentDate` | date | `last_assignment_date` | Last day (or current date if active) |
| `totalDaysServed` | integer | `total_days_served` | Total days across all assignments |
| `totalDaysServedParliament` | integer | `total_days_served_parliament` | Days as MP |
| `totalDaysServedCommittee` | integer | `total_days_served_committee` | Days in committees |
| `totalDaysServedGovernment` | integer | `total_days_served_government` | Days in government |
| `totalDaysServedEu` | integer | `total_days_served_eu` | Days in EU parliament |
| `totalDaysServedSpeaker` | integer | `total_days_served_speaker` | Days as speaker |
| `totalDaysServedParty` | integer | `total_days_served_party` | Days in party leadership |

**Status Fields:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `active` | boolean | `active` | Currently active |
| `activeSpeaker` | boolean | `active_speaker` | Currently speaker |
| `activeParty` | boolean | `active_party` | Currently in party leadership |
| `activeEu` | boolean | `active_eu` | Currently in EU parliament |
| `activeGovernment` | boolean | `active_government` | Currently in government |
| `activeCommittee` | boolean | `active_committee` | Currently in committee |
| `activeParliament` | boolean | `active_parliament` | Currently MP |

**JSON Example:**
```json
{
  "politicalActivity": {
    "firstAssignmentDate": "1990-11-12",
    "lastAssignmentDate": "1991-12-10",
    "totalDaysServed": 116,
    "totalDaysServedParliament": 58,
    "totalDaysServedCommittee": 58,
    "active": false,
    "activeParliament": false
  }
}
```

---

### 3. Assignments (`assignments`)

**Assignment Counts:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalAssignments` | integer | `total_assignments` | Total all assignments |
| `currentAssignments` | integer | `current_assignments` | Active assignments |
| `totalCommitteeAssignments` | integer | `total_committee_assignments` | Committee memberships |
| `currentCommitteeAssignments` | integer | `current_committee_assignments` | Active committees |
| `totalPartyAssignments` | integer | `total_party_assignments` | Party leadership roles |
| `currentPartyAssignments` | integer | `current_party_assignments` | Current party roles |
| `totalMinistryAssignments` | integer | `total_ministry_assignments` | Government positions |
| `currentMinistryAssignments` | integer | `current_ministry_assignments` | Current gov positions |
| `totalSpeakerAssignments` | integer | `total_speaker_assignments` | Speaker assignments |
| `currentSpeakerAssignments` | integer | `current_speaker_assignments` | Current speaker role |

**Committee Leadership:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalCommitteeLeadershipAssignments` | integer | `total_committee_leadership_assignments` | Leadership positions |
| `currentCommitteeLeadershipAssignments` | integer | `current_committee_leadership_assignments` | Current leadership |
| `totalDaysServedCommitteeLeadership` | integer | `total_days_served_committee_leadership` | Days in leadership |
| `totalCommitteeChairAssignments` | integer | `total_committee_chair_assignments` | Chair positions |
| `currentCommitteeChairAssignments` | integer | `current_committee_chair_assignments` | Current chair roles |
| `totalCommitteeViceChairAllAssignments` | integer | `total_committee_vice_chair_all_assignments` | Vice chair positions |
| `currentCommitteeViceChairAllAssignments` | integer | `current_committee_vice_chair_all_assignments` | Current vice chair |

**Substitute Positions:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalCommitteeSubstituteAssignments` | integer | `total_committee_substitute_assignments` | Substitute roles |
| `currentCommitteeSubstituteAssignments` | integer | `current_committee_substitute_assignments` | Current substitutes |
| `totalDaysServedCommitteeSubstitute` | integer | `total_days_served_committee_substitute` | Days as substitute |
| `totalSuppleantAssignments` | integer | `total_suppleant_assignments` | Suppleant roles |
| `currentSuppleantAssignments` | integer | `current_suppleant_assignments` | Current suppleant |

**Party Leadership:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalPartyLeaderAssignments` | integer | `total_party_leader_assignments` | Party leader roles |
| `currentPartyLeaderAssignments` | integer | `current_party_leader_assignments` | Current party leader |
| `totalPartySecretaryAssignments` | integer | `total_party_secretary_assignments` | Party secretary roles |
| `currentPartySecretaryAssignments` | integer | `current_party_secretary_assignments` | Current secretary |
| `totalStatsministerAssignments` | integer | `total_statsminister_assignments` | Prime minister roles |
| `currentStatsministerAssignments` | integer | `current_statsminister_assignments` | Current PM |

**JSON Example:**
```json
{
  "assignments": {
    "totalAssignments": 4,
    "currentAssignments": 0,
    "totalCommitteeAssignments": 3,
    "currentCommitteeAssignments": 0,
    "totalCommitteeChairAssignments": 1,
    "currentCommitteeChairAssignments": 0
  }
}
```

---

### 4. Document Activity (`documentActivity`)

**Document Statistics:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `totalDocuments` | integer | `total_documents` | Total documents authored |
| `partyMotions` | integer | `party_motions` | Party-affiliated motions |
| `individualMotions` | integer | `individual_motions` | Individual motions |
| `committeeMotions` | integer | `committee_motions` | Committee motions |
| `multiPartyMotions` | integer | `multi_party_motions` | Cross-party motions |
| `followUpMotions` | integer | `follow_up_motions` | Follow-up motions |
| `documentsLastYear` | integer | `documents_last_year` | Documents in past year |
| `documentYearsActive` | integer | `document_years_active` | Years with documents |
| `averageDocsPerYear` | decimal | `average_docs_per_year` | Average annual output |

**Activity Metrics:**

| Field | Type | CSV Column | Description |
|-------|------|------------|-------------|
| `docActivityLevel` | string | `doc_activity_level` | Activity classification |
| `docActivityProfile` | string | `doc_activity_profile` | Profile category |
| `collaborationPercentage` | decimal | `collaboration_percentage` | % collaborative docs |
| `documentTypes` | integer | `document_types` | Types of documents |
| `firstDocumentDate` | date | `first_document_date` | First document date |
| `lastDocumentDate` | date | `last_document_date` | Most recent document |

**JSON Example:**
```json
{
  "documentActivity": {
    "totalDocuments": 156,
    "partyMotions": 120,
    "individualMotions": 25,
    "documentsLastYear": 18,
    "averageDocsPerYear": 12.5,
    "collaborationPercentage": 35.2,
    "docActivityLevel": "high"
  }
}
```

---

## Complete JSON Example

```json
{
  "politicianId": "0761174174409",
  "personalInfo": {
    "personId": "0761174174409",
    "firstName": "Seija",
    "lastName": "Viitamäki-Carlsson",
    "party": "V",
    "bornYear": 1948,
    "gender": "KVINNA"
  },
  "politicalActivity": {
    "firstAssignmentDate": "1990-11-12",
    "lastAssignmentDate": "1991-12-10",
    "totalDaysServed": 116,
    "totalDaysServedParliament": 58,
    "totalDaysServedCommittee": 58,
    "totalDaysServedGovernment": 0,
    "active": false,
    "activeParliament": false,
    "activeCommittee": false
  },
  "assignments": {
    "totalAssignments": 4,
    "currentAssignments": 0,
    "totalCommitteeAssignments": 3,
    "currentCommitteeAssignments": 0,
    "totalPartyAssignments": 0,
    "totalMinistryAssignments": 0
  },
  "documentActivity": {
    "totalDocuments": 45,
    "partyMotions": 38,
    "individualMotions": 5,
    "documentsLastYear": 0,
    "averageDocsPerYear": 3.8,
    "collaborationPercentage": 22.5,
    "docActivityLevel": "medium"
  }
}
```

---

## Data Quality Notes

### Available Data
✅ All 65 fields documented above are **present in sample CSV**  
✅ Data validated through automated CI checks  
✅ 100% field completeness verification  

### Not Yet Available
❌ Voting records (requires separate table join)  
❌ Risk scores (requires computation layer)  
❌ Predictive analytics (future enhancement)  
❌ Nested intelligence objects (complex aggregation needed)  

### Next Steps
See [politician-schema.md](./politician-schema.md) for **aspirational v2.0 schema** with computed fields, voting records, and intelligence analytics.

---

## Validation

**CSV Source**: `service.data.impl/sample-data/view_riksdagen_politician_sample.csv`  
**Field Count**: 65 columns  
**Validation Script**: `json-export-specs/validate-field-completeness.sh`  
**CI Check**: Automated via GitHub Actions  
**Status**: ✅ All 21 core fields validated

---

## Version History

- **v1.0.0** (2025-12-31): Initial reality-based schema matching actual CSV structure
- **v2.0.0** (planned): Add computed fields, voting records, intelligence analytics

---

**See Also:**
- [politician-schema.md](./politician-schema.md) - Aspirational v2.0 with computed fields
- [BUSINESS_PRODUCT_DOCUMENT.md](../../BUSINESS_PRODUCT_DOCUMENT.md) - Product strategy
- [validate-field-completeness.sh](../validate-field-completeness.sh) - Validation script
