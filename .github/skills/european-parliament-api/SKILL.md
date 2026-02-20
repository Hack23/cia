---
name: european-parliament-api
description: EU Parliament open data API integration, MEP data, voting records, committee data for cross-parliament analysis
license: Apache-2.0
---

# European Parliament API Skill

## Purpose

This skill provides integration guidance for the European Parliament's open data APIs, enabling the CIA platform to perform cross-parliament analysis between the Swedish Riksdag and the EU Parliament. It covers MEP data retrieval, plenary voting records, committee information, and legislative document tracking.

## When to Use This Skill

Apply this skill when:
- ✅ Integrating European Parliament data into the CIA platform
- ✅ Building cross-parliament comparison features (Riksdag vs EU)
- ✅ Retrieving MEP profiles and voting records
- ✅ Tracking EU legislative procedures relevant to Sweden
- ✅ Analyzing Swedish MEP participation in EU committees
- ✅ Building data import pipelines for EU Parliament data

Do NOT use for:
- ❌ Swedish Riksdag API integration (use existing riksdagen modules)
- ❌ World Bank or election authority APIs (use existing modules)
- ❌ Generic REST API design patterns

## European Parliament Data Sources

```
EU Parliament Open Data Ecosystem
│
├─ European Parliament Open Data Portal
│  ├─ URL: https://data.europarl.europa.eu/
│  ├─ Format: RDF/SPARQL, JSON-LD, CSV
│  └─ License: CC BY 4.0
│
├─ Plenary Data (Votes, Debates)
│  ├─ Roll-call votes by session
│  ├─ Debate transcripts
│  └─ Attendance records
│
├─ MEP Data
│  ├─ Current and former MEPs
│  ├─ Political group membership
│  ├─ Committee assignments
│  └─ Contact information
│
├─ Legislative Data
│  ├─ Legislative procedures (COD, CNS, APP)
│  ├─ Committee reports and opinions
│  ├─ Amendments and resolutions
│  └─ Interinstitutional negotiations
│
└─ Parliamentary Questions
   ├─ Written questions
   ├─ Oral questions
   └─ Priority questions
```

## API Integration Patterns

### REST API Client

```java
@Service
public class EuropeanParliamentApiClient {

    private static final String BASE_URL = "https://data.europarl.europa.eu/api/v2";
    private static final Duration TIMEOUT = Duration.ofSeconds(30);
    private static final int MAX_RETRIES = 3;

    private final RestTemplate restTemplate;

    public EuropeanParliamentApiClient(RestTemplateBuilder builder) {
        this.restTemplate = builder
            .setConnectTimeout(TIMEOUT)
            .setReadTimeout(TIMEOUT)
            .defaultHeader("Accept", "application/json")
            .build();
    }

    /**
     * Retrieve current MEPs with retry logic.
     */
    public List<MepData> getCurrentMeps() {
        String url = BASE_URL + "/meps?filter=current&format=json";
        return executeWithRetry(() -> {
            ResponseEntity<MepListResponse> response =
                restTemplate.getForEntity(url, MepListResponse.class);
            return response.getBody().getMeps();
        });
    }

    /**
     * Retrieve voting records for a specific plenary session.
     */
    public List<VoteRecord> getPlenaryVotes(String sessionId) {
        String url = BASE_URL + "/plenary-sessions/" + sessionId + "/votes";
        return executeWithRetry(() -> {
            ResponseEntity<VoteListResponse> response =
                restTemplate.getForEntity(url, VoteListResponse.class);
            return response.getBody().getVotes();
        });
    }

    private <T> T executeWithRetry(Supplier<T> operation) {
        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            try {
                return operation.get();
            } catch (RestClientException e) {
                attempts++;
                if (attempts >= MAX_RETRIES) throw e;
                try {
                    Thread.sleep((long) Math.pow(2, attempts) * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ie);
                }
            }
        }
        throw new RuntimeException("Max retries exceeded");
    }
}
```

### Data Model for EU Parliament

```java
@Entity
@Table(name = "eu_mep_data")
public class MepData {

    @Id
    @Column(name = "mep_id")
    private String mepId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "country")
    private String country;  // "SE" for Swedish MEPs

    @Column(name = "political_group")
    private String politicalGroup;

    @Column(name = "national_party")
    private String nationalParty;

    @Column(name = "active")
    private boolean active;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
}

@Entity
@Table(name = "eu_vote_record")
public class EuVoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "vote_date")
    private LocalDate voteDate;

    @Column(name = "subject")
    private String subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mep_id")
    private MepData mep;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote_position")
    private VotePosition position;  // FOR, AGAINST, ABSTAIN, ABSENT
}
```

## Swedish MEP Analysis

### Cross-Parliament Comparison

```
Swedish MEP Analysis Dimensions
│
├─ Voting Alignment
│  ├─ Swedish MEP votes vs national party line
│  ├─ Swedish MEP votes vs EU political group
│  └─ Deviation analysis (party loyalty metric)
│
├─ Committee Participation
│  ├─ Swedish MEP committee assignments
│  ├─ Report authorship frequency
│  ├─ Amendment submission rate
│  └─ Attendance in committee meetings
│
├─ Legislative Influence
│  ├─ Rapporteur appointments
│  ├─ Shadow rapporteur roles
│  ├─ Negotiation mandates
│  └─ Successful amendments ratio
│
└─ Cross-Parliament Correlation
   ├─ EU legislation impact on Swedish law
   ├─ Swedish Riksdag debates on EU matters
   ├─ Transposition compliance tracking
   └─ Swedish positions in Council vs MEP votes
```

### Query Pattern for Swedish MEPs

```java
@Repository
public interface MepRepository extends JpaRepository<MepData, String> {

    // Find all Swedish MEPs (current)
    @Query("SELECT m FROM MepData m WHERE m.country = 'SE' AND m.active = true")
    List<MepData> findActiveSwedishMeps();

    // Find Swedish MEPs by national party
    @Query("SELECT m FROM MepData m WHERE m.country = 'SE' " +
           "AND m.nationalParty = :party AND m.active = true")
    List<MepData> findSwedishMepsByParty(@Param("party") String party);

    // Voting record for a specific MEP
    @Query("SELECT v FROM EuVoteRecord v WHERE v.mep.mepId = :mepId " +
           "AND v.voteDate BETWEEN :startDate AND :endDate " +
           "ORDER BY v.voteDate DESC")
    List<EuVoteRecord> findVotesByMepAndPeriod(
        @Param("mepId") String mepId,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate);
}
```

## Data Import Pipeline

### Scheduled Import Service

```java
@Service
public class EuParliamentImportService {

    private static final Logger LOG =
        LoggerFactory.getLogger(EuParliamentImportService.class);

    @Autowired
    private EuropeanParliamentApiClient apiClient;

    @Autowired
    private MepRepository mepRepository;

    /**
     * Import MEP data daily at 02:00 UTC.
     * EU Parliament data updates are typically published overnight.
     */
    @Scheduled(cron = "0 0 2 * * *")
    @Transactional
    public void importMepData() {
        LOG.info("Starting EU Parliament MEP data import");
        try {
            List<MepData> meps = apiClient.getCurrentMeps();
            int imported = 0;
            for (MepData mep : meps) {
                mepRepository.save(mep);
                imported++;
            }
            LOG.info("Imported {} MEP records", imported);
        } catch (Exception e) {
            LOG.error("MEP import failed", e);
            // Alert via CloudWatch alarm
        }
    }
}
```

### Error Handling and Resilience

```java
// Circuit breaker for EU Parliament API
@Service
public class ResilientEuParliamentClient {

    private final AtomicInteger failureCount = new AtomicInteger(0);
    private static final int FAILURE_THRESHOLD = 5;
    private volatile Instant circuitOpenedAt;

    public <T> Optional<T> callWithCircuitBreaker(Supplier<T> apiCall) {
        if (isCircuitOpen()) {
            LOG.warn("Circuit breaker OPEN for EU Parliament API");
            return Optional.empty();
        }
        try {
            T result = apiCall.get();
            failureCount.set(0); // Reset on success
            return Optional.of(result);
        } catch (Exception e) {
            if (failureCount.incrementAndGet() >= FAILURE_THRESHOLD) {
                circuitOpenedAt = Instant.now();
                LOG.error("Circuit breaker OPENED after {} failures", FAILURE_THRESHOLD);
            }
            return Optional.empty();
        }
    }

    private boolean isCircuitOpen() {
        if (circuitOpenedAt == null) return false;
        // Auto-reset after 5 minutes
        return Duration.between(circuitOpenedAt, Instant.now()).toMinutes() < 5;
    }
}
```

## Data Caching Strategy

| Data Type | Cache Duration | Reason |
|----------|---------------|--------|
| MEP profiles | 24 hours | Changes rarely, daily import |
| Plenary votes | 1 week | Immutable after session ends |
| Committee data | 12 hours | Updates with meeting schedules |
| Legislative docs | 6 hours | Active procedures change frequently |
| Session calendar | 1 month | Published well in advance |

## GDPR Considerations

```
EU Parliament Data and GDPR:
├─ MEP public data: Legitimate interest (public figure, Art. 6(1)(f))
├─ Voting records: Public document (Art. 6(1)(e))
├─ Contact details: Only official/public information
├─ Personal data: Never collect private MEP data
└─ Data retention: Archive per parliamentary term
```

## Integration with Existing CIA Modules

```
Module Integration Points
│
├─ model.external.riksdagen → Cross-reference Swedish party data
├─ service.external.riksdagen → Compare Riksdag votes with EU votes
├─ service.data.api → Shared data access patterns
├─ web-widgets → EU Parliament views and dashboards
└─ citizen-intelligence-agency → Configuration and routing
```

## References

- [European Parliament Open Data Portal](https://data.europarl.europa.eu/)
- [EP Open Data API Documentation](https://data.europarl.europa.eu/en/developer-corner/opendata-api)
- [VoteWatch Europe](https://www.votewatch.eu/) (analysis reference)
- [Swedish MEPs](https://www.europarl.europa.eu/meps/en/search/advanced?countryCode=SE)
- [EU Legislative Process](https://www.europarl.europa.eu/ordinary-legislative-procedure/en/ordinary-legislative-procedure.html)
