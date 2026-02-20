---
name: api-integration
description: External API integration patterns, retry logic, circuit breakers, caching, rate limiting for government data APIs
license: Apache-2.0
---

# API Integration Skill

## Purpose

Provide robust patterns for integrating the CIA platform with external government data APIs, including the Swedish Riksdagen, Election Authority, World Bank, and ESV (Swedish Financial Management Authority). Covers resilience, caching, and error handling.

## When to Use

- ✅ Integrating new external data sources
- ✅ Improving reliability of existing API connections
- ✅ Implementing caching for frequently accessed political data
- ✅ Adding rate limiting to respect API provider constraints
- ✅ Debugging API integration failures

Do NOT use for:
- ❌ Internal service-to-service calls (use Spring patterns directly)
- ❌ Database access patterns (use JPA/Hibernate skill)

## CIA External API Landscape

| API | Base URL | Data Type | Rate Limit |
|-----|----------|-----------|------------|
| Riksdagen Open Data | `data.riksdagen.se` | Parliament data, votes, documents | Best effort |
| Swedish Election Authority | `data.val.se` | Election results, parties | Low volume |
| World Bank Open Data | `api.worldbank.org` | Economic indicators | 50 req/sec |
| ESV | `www.esv.se` | Government finances | Best effort |

## Retry Logic Pattern

### Exponential Backoff with Jitter

```java
@Service
public class ResilientApiClient {

    private static final int MAX_RETRIES = 3;
    private static final long BASE_DELAY_MS = 1000;
    private static final Logger LOG = LoggerFactory.getLogger(ResilientApiClient.class);

    public <T> T executeWithRetry(Supplier<T> apiCall, String operationName) {
        int attempt = 0;
        while (true) {
            try {
                return apiCall.get();
            } catch (Exception e) {
                attempt++;
                if (attempt >= MAX_RETRIES || !isRetryable(e)) {
                    LOG.error("API call failed after {} attempts: {}", attempt, operationName, e);
                    throw new ApiIntegrationException(operationName, e);
                }
                long delay = calculateBackoff(attempt);
                LOG.warn("Retry {}/{} for {} after {}ms", attempt, MAX_RETRIES, operationName, delay);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new ApiIntegrationException(operationName, ie);
                }
            }
        }
    }

    private long calculateBackoff(int attempt) {
        long exponentialDelay = BASE_DELAY_MS * (1L << (attempt - 1));
        long jitter = ThreadLocalRandom.current().nextLong(0, exponentialDelay / 2);
        return Math.min(exponentialDelay + jitter, 30_000);
    }

    private boolean isRetryable(Exception e) {
        if (e instanceof HttpClientErrorException httpErr) {
            int status = httpErr.getStatusCode().value();
            return status == 429 || status >= 500;
        }
        return e instanceof ResourceAccessException
            || e instanceof SocketTimeoutException;
    }
}
```

## Circuit Breaker Pattern

```java
@Component
public class CircuitBreaker {

    private enum State { CLOSED, OPEN, HALF_OPEN }

    private State state = State.CLOSED;
    private int failureCount = 0;
    private long lastFailureTime = 0;

    private static final int FAILURE_THRESHOLD = 5;
    private static final long RECOVERY_TIMEOUT_MS = 60_000;

    public synchronized <T> T execute(Supplier<T> action, Supplier<T> fallback) {
        if (state == State.OPEN) {
            if (System.currentTimeMillis() - lastFailureTime > RECOVERY_TIMEOUT_MS) {
                state = State.HALF_OPEN;
            } else {
                return fallback.get();
            }
        }

        try {
            T result = action.get();
            reset();
            return result;
        } catch (Exception e) {
            recordFailure();
            return fallback.get();
        }
    }

    private synchronized void recordFailure() {
        failureCount++;
        lastFailureTime = System.currentTimeMillis();
        if (failureCount >= FAILURE_THRESHOLD) {
            state = State.OPEN;
        }
    }

    private synchronized void reset() {
        failureCount = 0;
        state = State.CLOSED;
    }
}
```

## Caching Strategy

### Spring Cache Configuration

```java
@Configuration
@EnableCaching
public class ApiCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager();
        manager.setCaffeine(Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(Duration.ofHours(1))
            .recordStats());
        return manager;
    }
}

@Service
public class RiksdagDataService {

    @Cacheable(value = "politicians", key = "#personId")
    public PoliticianData getPolitician(String personId) {
        return riksdagClient.fetchPerson(personId);
    }

    @CacheEvict(value = "politicians", allEntries = true)
    @Scheduled(cron = "0 0 2 * * *") // Refresh at 2 AM daily
    public void evictPoliticianCache() {
        LOG.info("Evicting politician cache for daily refresh");
    }
}
```

### Cache TTL Guidelines

| Data Type | TTL | Reason |
|-----------|-----|--------|
| Politician profiles | 24 hours | Changes infrequently |
| Voting records | 1 hour | Updated during sessions |
| Document content | 7 days | Immutable once published |
| Election results | 30 days | Updated only at elections |
| Economic indicators | 24 hours | Daily updates from World Bank |

## Rate Limiting

```java
@Component
public class RateLimiter {

    private final Semaphore semaphore;
    private final ScheduledExecutorService scheduler;

    public RateLimiter(@Value("${api.rate.limit:10}") int maxRequestsPerSecond) {
        this.semaphore = new Semaphore(maxRequestsPerSecond);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.scheduler.scheduleAtFixedRate(
            () -> semaphore.release(maxRequestsPerSecond - semaphore.availablePermits()),
            1, 1, TimeUnit.SECONDS
        );
    }

    public <T> T throttled(Supplier<T> apiCall) throws InterruptedException {
        semaphore.acquire();
        return apiCall.get();
    }
}
```

## Error Handling

```java
public class ApiIntegrationException extends RuntimeException {
    private final String operationName;
    private final int httpStatus;

    public ApiIntegrationException(String operationName, Throwable cause) {
        super("API integration failed: " + operationName, cause);
        this.operationName = operationName;
        this.httpStatus = extractStatus(cause);
    }
}
```

## Security Considerations

- **Never log API keys or tokens** — mask sensitive headers in logs
- **Validate all API responses** — treat external data as untrusted input
- **Use HTTPS exclusively** — reject insecure connections
- **Timeout connections** — set connect (5s) and read (30s) timeouts
- **Sanitize data** — escape or validate all data before database storage

## ISMS Alignment

| Control | Requirement |
|---------|-------------|
| ISO 27001 A.8.24 | Use of cryptography for API transport |
| NIST CSF PR.DS-2 | Data-in-transit protection |
| CIS Control 12 | Network infrastructure management |
