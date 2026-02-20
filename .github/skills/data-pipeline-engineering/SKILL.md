---
name: data-pipeline-engineering
description: Data pipeline design, ETL processes, Spring Integration patterns, batch processing for political data
license: Apache-2.0
---

# Data Pipeline Engineering Skill

## Purpose

Design and implement robust data pipelines for the CIA platform that extract, transform, and load Swedish political data from multiple sources into the internal data model. Covers Spring Integration, batch processing, and monitoring patterns.

## When to Use

- ✅ Building data import pipelines for Riksdagen data
- ✅ Designing ETL workflows for political data aggregation
- ✅ Implementing batch processing for large datasets
- ✅ Creating data refresh and synchronization jobs
- ✅ Monitoring pipeline health and data quality

Do NOT use for:
- ❌ Real-time API request handling (use api-integration skill)
- ❌ UI data binding (use vaadin-component-design skill)

## Pipeline Architecture

```
┌────────────┐    ┌────────────┐    ┌────────────┐    ┌────────────┐
│  Extract   │───▶│ Transform  │───▶│   Load     │───▶│  Monitor   │
│            │    │            │    │            │    │            │
│ API Fetch  │    │ Validate   │    │ JPA Upsert │    │ Metrics    │
│ XML/JSON   │    │ Map Fields │    │ Batch Save │    │ Alerts     │
│ Pagination │    │ Enrich     │    │ Index      │    │ Dashboards │
└────────────┘    └────────────┘    └────────────┘    └────────────┘
```

## Spring Integration Patterns

### Message-Driven Pipeline

```java
@Configuration
public class RiksdagPipelineConfig {

    @Bean
    public IntegrationFlow riksdagImportFlow() {
        return IntegrationFlow
            .from(pollingSource(), e -> e.poller(
                Pollers.cron("0 0 2 * * *")     // Daily at 2 AM
                    .maxMessagesPerPoll(1)
                    .errorHandler(pipelineErrorHandler())))
            .channel("riksdagRawChannel")
            .transform(xmlToJsonTransformer())
            .split(personListSplitter())
            .channel(c -> c.executor(taskExecutor()))
            .filter(dataQualityFilter())
            .transform(entityMapper())
            .aggregate(batchAggregator())
            .handle(jpaOutboundAdapter())
            .get();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("riksdag-pipeline-");
        return executor;
    }
}
```

### Error Channel Configuration

```java
@Bean
public IntegrationFlow errorFlow() {
    return IntegrationFlow
        .from("errorChannel")
        .handle(message -> {
            MessagingException exception = (MessagingException) message.getPayload();
            LOG.error("Pipeline error: {}", exception.getMessage(), exception);
            metricsService.incrementErrorCount("riksdag-pipeline");

            // Route to dead letter queue for manual review
            Message<?> failedMessage = exception.getFailedMessage();
            deadLetterRepository.save(new DeadLetterEntry(
                failedMessage.getPayload().toString(),
                exception.getMessage(),
                Instant.now()
            ));
        })
        .get();
}
```

## Batch Processing

### Spring Batch Job Configuration

```java
@Configuration
public class VoteImportBatchConfig {

    @Bean
    public Job voteImportJob(JobRepository jobRepository, Step importStep) {
        return new JobBuilder("voteImportJob", jobRepository)
            .incrementer(new RunIdIncrementer())
            .start(importStep)
            .build();
    }

    @Bean
    public Step importStep(JobRepository jobRepository,
                           PlatformTransactionManager txManager) {
        return new StepBuilder("importVotes", jobRepository)
            .<RiksdagVote, VoteData>chunk(100, txManager)
            .reader(voteReader())
            .processor(voteProcessor())
            .writer(voteWriter())
            .faultTolerant()
            .retryLimit(3)
            .retry(TransientDataAccessException.class)
            .skipLimit(10)
            .skip(DataValidationException.class)
            .listener(stepListener())
            .build();
    }
}
```

### Chunk Size Guidelines

| Data Type | Records/Batch | Chunk Size | Reason |
|-----------|---------------|------------|--------|
| Person data | ~350 | 50 | Small dataset, frequent updates |
| Vote records | ~100K/session | 500 | Large dataset, bulk insert |
| Documents | ~50K | 100 | Variable size, careful processing |
| Committee data | ~100 | 25 | Small, relational integrity |

## Data Transformation Patterns

### Field Mapping

```java
@Component
public class RiksdagEntityMapper {

    public PersonData mapPerson(RiksdagPerson source) {
        PersonData target = new PersonData();
        target.setId(source.getIntressentId());
        target.setFirstName(sanitize(source.getFornamn()));
        target.setLastName(sanitize(source.getEfternamn()));
        target.setParty(normalizeParty(source.getParti()));
        target.setBornYear(parseYear(source.getFoddAr()));
        target.setGender(normalizeGender(source.getKon()));
        target.setStatus(source.getStatus());
        target.setImportTimestamp(Instant.now());
        return target;
    }

    private String sanitize(String input) {
        if (input == null) return null;
        String trimmed = input.trim().replaceAll("[\\p{Cntrl}]", "");  // Remove control characters
        return trimmed.substring(0, Math.min(trimmed.length(), 255));
    }

    private String normalizeParty(String party) {
        return Optional.ofNullable(party)
            .map(String::trim)
            .map(String::toUpperCase)
            .orElse("-");
    }
}
```

## Scheduling and Orchestration

```java
@Component
public class PipelineScheduler {

    @Scheduled(cron = "0 0 2 * * *")   // Daily full refresh
    public void dailyFullImport() {
        LOG.info("Starting daily full import");
        importService.importAll();
    }

    @Scheduled(cron = "0 */15 8-18 * * MON-FRI")  // Every 15 min during sessions
    public void incrementalVoteImport() {
        if (riksdagSessionActive()) {
            LOG.info("Starting incremental vote import");
            importService.importRecentVotes();
        }
    }
}
```

## Monitoring and Observability

### Pipeline Metrics

```java
@Component
public class PipelineMetrics {

    private final MeterRegistry meterRegistry;

    public void recordImport(String pipeline, int recordCount, long durationMs) {
        meterRegistry.counter("pipeline.records.imported",
            "pipeline", pipeline).increment(recordCount);
        meterRegistry.timer("pipeline.duration",
            "pipeline", pipeline).record(durationMs, TimeUnit.MILLISECONDS);
    }

    public void recordError(String pipeline, String errorType) {
        meterRegistry.counter("pipeline.errors",
            "pipeline", pipeline,
            "error_type", errorType).increment();
    }
}
```

## Security Considerations

- **Sanitize all imported data** — apply input validation before persistence
- **Use transactions** — ensure atomicity for batch operations
- **Limit concurrency** — prevent resource exhaustion with bounded thread pools
- **Secure credentials** — externalize API keys, use Spring Vault or env vars
- **Audit data imports** — log source, count, and timestamp for traceability

## ISMS Alignment

| Control | Requirement |
|---------|-------------|
| ISO 27001 A.8.10 | Information deletion / data retention |
| ISO 27001 A.5.33 | Protection of records |
| NIST CSF PR.DS-1 | Data-at-rest protection |
| CIS Control 3 | Data protection |
