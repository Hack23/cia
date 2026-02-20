---
name: cia-data-integration
description: Citizen Intelligence Agency data model integration, riksdag data processing, political entity mapping
license: Apache-2.0
---

# CIA Data Integration Skill

## Purpose

Guide the integration of Swedish political data into the CIA platform's data model, covering entity mapping, data transformation, and consistency rules for Riksdagen, election, and financial data sources.

## When to Use

- ✅ Mapping new Riksdagen data types to JPA entities
- ✅ Processing parliamentary voting records
- ✅ Integrating election result data
- ✅ Adding new political entity relationships
- ✅ Maintaining data quality and consistency rules

Do NOT use for:
- ❌ Generic API integration (use api-integration skill)
- ❌ UI/dashboard development (use vaadin-component-design skill)

## CIA Data Model Overview

```
┌──────────────────────────────────────────────────────┐
│                   External Data Sources               │
├──────────────┬────────────┬──────────┬───────────────┤
│  Riksdagen   │  Val.se    │  World   │     ESV       │
│  Open Data   │  Elections │  Bank    │   Finances    │
└──────┬───────┴─────┬──────┴────┬─────┴───────┬───────┘
       │             │           │             │
       ▼             ▼           ▼             ▼
┌──────────────────────────────────────────────────────┐
│              Service Layer (Spring)                   │
│  service.external.riksdagen                          │
│  service.external.val                                │
│  service.external.worldbank                          │
│  service.external.esv                                │
└──────────────────────┬───────────────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────────────┐
│            Internal Data Model (JPA)                  │
│  model.internal.application.data                     │
│    ├── Politicians     ├── Committees                │
│    ├── Votes           ├── Documents                 │
│    ├── Parties         ├── Elections                 │
│    └── Risk Scores     └── Financial Data            │
└──────────────────────────────────────────────────────┘
```

## Key Module Structure

| Module | Purpose |
|--------|---------|
| `model.external.riksdagen` | API models for Riksdagen data |
| `model.external.riksdagen.person.impl` | Person/politician data mapping |
| `model.external.riksdagen.votering.impl` | Voting record processing |
| `model.external.riksdagen.dokumentstatus.impl` | Document metadata |
| `model.external.val.partier.impl` | Political party data |
| `model.internal.application.data.impl` | Internal aggregated model |

## Entity Mapping Patterns

### Riksdagen Person to Internal Politician

```java
@Entity
@Table(name = "person_data")
public class PersonData implements Serializable {

    @Id
    @Column(name = "id")
    private String id;              // Riksdagen person ID (intressent_id)

    @Column(name = "first_name")
    private String firstName;       // förnamn

    @Column(name = "last_name")
    private String lastName;        // efternamn

    @Column(name = "party")
    private String party;           // parti

    @Column(name = "born_year")
    private int bornYear;           // född_ar

    @Column(name = "gender")
    private String gender;          // kön

    @Column(name = "status")
    private String status;          // status (Tjänstgörande, etc.)
}
```

### Vote Record Processing

```java
@Entity
@Table(name = "vote_data")
public class VoteData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "intressent_id")
    private String intressentId;    // Links to PersonData

    @Column(name = "rm")
    private String riksmote;        // Parliamentary session (e.g., "2023/24")

    @Column(name = "beteckning")
    private String designation;     // Vote designation

    @Column(name = "rost")
    private String vote;            // Ja/Nej/Avstår/Frånvarande

    @Column(name = "votering_id")
    private String voteringId;      // Unique voting session ID
}
```

## Data Quality Rules

### Validation Constraints

```java
public class DataQualityValidator {

    // Valid Swedish Parliament parties
    private static final Set<String> VALID_PARTIES = Set.of(
        "S", "M", "SD", "C", "V", "KD", "L", "MP", "-"
    );

    // Valid vote values from Riksdagen
    private static final Set<String> VALID_VOTES = Set.of(
        "Ja", "Nej", "Avstår", "Frånvarande"
    );

    public void validatePersonData(PersonData person) {
        Objects.requireNonNull(person.getId(), "Person ID is required");
        if (!VALID_PARTIES.contains(person.getParty())) {
            LOG.warn("Unknown party code: {} for person: {}",
                person.getParty(), person.getId());
        }
    }

    public void validateVoteData(VoteData vote) {
        Objects.requireNonNull(vote.getIntressentId(), "Intressent ID is required");
        if (!VALID_VOTES.contains(vote.getVote())) {
            throw new DataValidationException(
                "Invalid vote value: " + vote.getVote());
        }
    }
}
```

### Data Freshness Requirements

| Data Type | Maximum Age | Refresh Frequency |
|-----------|-------------|-------------------|
| Person list | 24 hours | Daily at 02:00 |
| Voting records | 1 hour | During Riksdag sessions |
| Documents | 6 hours | 4 times daily |
| Committee data | 24 hours | Daily |
| Election results | 30 days | Post-election only |

## Riksdagen API Integration

### API Endpoints

```
GET /personlista/?iid={id}&utformat=json     → Person details
GET /voteringlista/?rm={session}&utformat=json → Voting list
GET /dokumentlista/?sok={query}&utformat=json  → Document search
GET /dokumentstatus/{doc_id}.json              → Document status
```

### Data Import Workflow

```java
@Service
public class RiksdagDataImportService {

    @Transactional
    public ImportResult importPersonData() {
        // 1. Fetch from Riksdagen API
        PersonLista personList = riksdagClient.getPersonLista();

        // 2. Validate each record
        List<PersonData> validated = personList.getPersons().stream()
            .filter(this::isValid)
            .map(this::mapToInternal)
            .collect(Collectors.toList());

        // 3. Upsert into database
        int updated = personRepository.batchUpsert(validated);

        // 4. Log import metrics
        return new ImportResult(validated.size(), updated);
    }
}
```

## Security Considerations

- **Sanitize all imported data** — external data is untrusted input
- **Use parameterized queries** — never concatenate API data into SQL
- **Log data lineage** — track source and import timestamp for audit
- **GDPR compliance** — politician data is public, but handle personal data carefully
- **Validate XML/JSON** — protect against XXE and injection attacks

## ISMS Alignment

| Control | Requirement |
|---------|-------------|
| ISO 27001 A.8.11 | Data masking for sensitive fields |
| ISO 27001 A.5.33 | Protection of records |
| NIST CSF PR.DS-1 | Data-at-rest protection |
| CIS Control 3 | Data protection |
