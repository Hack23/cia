---
name: performance-optimization
description: Application performance tuning, JVM optimization, database query tuning, Vaadin UI performance
license: Apache-2.0
---

# Performance Optimization Skill

## Purpose

This skill provides performance optimization guidance for the CIA platform across JVM tuning, PostgreSQL query optimization, Vaadin UI rendering, and Spring Framework efficiency. It ensures the political intelligence platform delivers responsive analysis to users.

## When to Use This Skill

Apply this skill when:
- ✅ Diagnosing slow page loads or API response times
- ✅ Optimizing database queries for large political datasets
- ✅ Tuning JVM parameters for production deployment
- ✅ Improving Vaadin UI component rendering performance
- ✅ Reducing memory consumption in data processing pipelines
- ✅ Optimizing materialized view refresh schedules
- ✅ Profiling Spring application startup time

Do NOT use for:
- ❌ Security hardening (use security-by-design skill)
- ❌ Feature development without performance concerns
- ❌ UI styling changes that don't affect rendering

## Performance Targets

| Metric | Target | Measurement |
|--------|--------|-------------|
| Page load time | < 3 seconds | Lighthouse, browser DevTools |
| API response time | < 500ms (p95) | CloudWatch metrics |
| Database query time | < 100ms (p95) | PostgreSQL pg_stat_statements |
| JVM heap usage | < 80% of max | JMX / CloudWatch |
| Vaadin push latency | < 200ms | Client-side measurement |
| Startup time | < 30 seconds | Application logs |

## JVM Optimization

### Recommended JVM Settings

```bash
# Production JVM flags for CIA platform (Java 21+)
JAVA_OPTS="-server \
  -Xms2g -Xmx4g \
  -XX:+UseG1GC \
  -XX:MaxGCPauseMillis=200 \
  -XX:+UseStringDeduplication \
  -XX:+OptimizeStringConcat \
  -XX:MetaspaceSize=256m \
  -XX:MaxMetaspaceSize=512m \
  -XX:+HeapDumpOnOutOfMemoryError \
  -XX:HeapDumpPath=/var/log/cia/heapdump.hprof \
  -Djava.security.egd=file:/dev/./urandom"
```

### Memory Optimization Patterns

```java
// ✅ EFFICIENT: Stream processing for large datasets
@Service
public class VotingDataProcessor {

    @Transactional(readOnly = true)
    public void processVotingRecords(String sessionId) {
        try (Stream<VoteData> votes = voteRepository.streamBySession(sessionId)) {
            votes.map(this::analyzeVote)
                 .filter(Objects::nonNull)
                 .forEach(resultRepository::save);
        }
        // Stream auto-closes, no memory accumulation
    }
}

// ❌ INEFFICIENT: Loading all records into memory
public void processVotingRecords(String sessionId) {
    List<VoteData> allVotes = voteRepository.findBySession(sessionId);
    // May load millions of records into heap
}
```

## PostgreSQL Query Optimization

### Index Strategy for Political Data

```sql
-- High-impact indexes for common CIA queries
-- Politician lookup by party and status
CREATE INDEX idx_person_party_status
  ON person_data (party, status) WHERE status = 'ACTIVE';

-- Voting record time-series queries
CREATE INDEX idx_vote_date_committee
  ON vote_data (vote_date DESC, committee_id);

-- Document search optimization
CREATE INDEX idx_document_search
  ON document_content USING gin(to_tsvector('swedish', content));

-- Materialized view refresh tracking
CREATE INDEX idx_mv_refresh_status
  ON materialized_view_log (view_name, last_refresh DESC);
```

### Query Anti-Patterns

```java
// ❌ N+1 Query Problem
@Entity
public class Committee {
    @OneToMany(fetch = FetchType.LAZY)
    private List<CommitteeMember> members;
}
// Accessing committee.getMembers() in a loop = N+1 queries

// ✅ FIX: Use JOIN FETCH or EntityGraph
@Query("SELECT c FROM Committee c JOIN FETCH c.members WHERE c.id = :id")
Committee findWithMembers(@Param("id") Long id);

// ✅ FIX: Use @EntityGraph
@EntityGraph(attributePaths = {"members", "members.person"})
Committee findById(Long id);
```

### Materialized View Optimization

```sql
-- Concurrent refresh to avoid locks during read
REFRESH MATERIALIZED VIEW CONCURRENTLY view_riksdagen_politician_summary;

-- Schedule refresh based on data freshness requirements
-- High priority: refresh every 15 minutes during business hours
-- Low priority: refresh daily during off-peak
```

## Vaadin UI Performance

### Lazy Loading for Large Datasets

```java
// ✅ EFFICIENT: Lazy loading with DataProvider
Grid<PoliticianSummary> grid = new Grid<>(PoliticianSummary.class);
grid.setDataProvider(
    DataProvider.fromCallbacks(
        query -> politicianService.fetch(query.getOffset(), query.getLimit()),
        query -> politicianService.count()
    )
);

// ❌ INEFFICIENT: Loading all items upfront
grid.setItems(politicianService.findAll()); // Loads everything
```

### Component Optimization

```java
// ✅ Use virtual scrolling for long lists
grid.setPageSize(50);
grid.setMultiSort(true);

// ✅ Minimize push updates
@Push(transport = Transport.WEBSOCKET_XHR)
public class MainView extends AppLayout {
    // Only push critical real-time updates
}

// ✅ Defer non-critical UI updates
UI.getCurrent().access(() -> {
    notificationComponent.update(newData);
});
```

### Bundle Size Reduction

- Use `@CssImport` instead of inline styles for reuse
- Enable production mode (`vaadin.productionMode=true`)
- Minimize custom JavaScript in `@ClientCallable` methods
- Use Vaadin's built-in components over custom widgets

## Spring Framework Performance

### Caching Strategy

```java
@Service
public class PoliticianService {

    // Cache frequently accessed, rarely changing data
    @Cacheable(value = "politicians", key = "#personId",
               unless = "#result == null")
    public PoliticianSummary getPolitician(String personId) {
        return repository.findSummaryById(personId);
    }

    // Evict cache when data is updated
    @CacheEvict(value = "politicians", key = "#personId")
    public void updatePolitician(String personId, PoliticianUpdate update) {
        repository.update(personId, update);
    }
}
```

### Transaction Optimization

```java
// ✅ Read-only transactions for queries (no dirty checking)
@Transactional(readOnly = true)
public List<VotingSummary> getVotingSummaries(String sessionId) {
    return votingRepository.findSummariesBySession(sessionId);
}

// ✅ Batch operations for bulk inserts
@Transactional
public void importVotingData(List<VoteData> votes) {
    int batchSize = 50;
    for (int i = 0; i < votes.size(); i++) {
        entityManager.persist(votes.get(i));
        if (i % batchSize == 0) {
            entityManager.flush();
            entityManager.clear(); // Release memory
        }
    }
}
```

## Performance Testing

### JMH Benchmarks for Critical Paths

```java
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class PoliticianLookupBenchmark {

    @Benchmark
    public void benchmarkPoliticianSearch() {
        politicianService.searchByName("test");
    }
}
```

### Monitoring Checklist

- ✅ Enable `pg_stat_statements` for query analysis
- ✅ Configure JMX export for JVM metrics
- ✅ Set up CloudWatch dashboards for response times
- ✅ Monitor Vaadin session count and memory usage
- ✅ Track materialized view refresh durations
- ✅ Alert on p95 latency threshold breaches

## References

- [PostgreSQL Performance Tips](https://www.postgresql.org/docs/current/performance-tips.html)
- [Vaadin Performance Best Practices](https://vaadin.com/docs/latest/advanced/performance)
- [Spring Boot Performance](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [JVM Tuning Guide (Oracle)](https://docs.oracle.com/en/java/javase/21/gctuning/)
