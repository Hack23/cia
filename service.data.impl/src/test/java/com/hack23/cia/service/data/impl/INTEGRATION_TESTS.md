# Integration Tests Documentation

## Overview

This directory contains comprehensive integration tests for the Citizen Intelligence Agency data extraction and analysis workflows. These tests validate the complete data flow from extraction through analysis and risk assessment.

## Test Suites

### 1. DataExtractionIntegrationTest.java
**Purpose**: Tests data extraction and framework validation workflows

**Test Cases**:
- Extract framework validation data SQL execution
- CSV format validation for framework datasets
- Round-trip data import/export validation
- Framework validation test coverage
- Extraction performance benchmark (<20 minutes)
- Data quality validation

**Coverage Target**: 80% line, 70% branch

### 2. TemporalViewChainIntegrationTest.java
**Purpose**: Tests temporal view dependencies and data flow validation

**Test Cases**:
- Ministry risk evolution view (v1.58)
- Party effectiveness trends view (v1.59)
- Decision temporal trends view (v1.60)
- Politician behavioral trends view (v1.61)
- View dependency chain validation
- Temporal view query performance
- Temporal aggregation accuracy

**Coverage Target**: 80% line, 70% branch

### 3. DroolsRiskRuleIntegrationTest.java
**Purpose**: Tests Drools risk rules with framework validation data

**Test Cases**:
- Resignation prediction accuracy (target: 78%)
- Risk score calculation validation
- Multi-dimensional risk profile analysis (target: 85%)
- Rule execution performance (>100 profiles/sec)
- Low productivity rule validation
- Rebel voting rule validation
- Ineffective voting rule validation

**Coverage Target**: 80% line, 70% branch

### 4. AnalysisWorkflowIntegrationTest.java
**Purpose**: End-to-end integration tests for complete analysis workflow

**Test Cases**:
- Data extraction workflow stage
- Temporal analysis workflow stage
- Risk assessment workflow stage
- Reporting workflow stage
- End-to-end workflow validation
- Workflow performance benchmark (<30 seconds)
- Data integrity validation
- Error handling and recovery
- Concurrent workflow execution
- Workflow rollback and cleanup

**Coverage Target**: 80% line, 70% branch

## Test Infrastructure

### Testcontainers
All integration tests use Testcontainers to provide isolated PostgreSQL databases:
- **Image**: postgres:16-alpine
- **Database**: cia_test
- **Username**: test
- **Password**: test
- **Init Script**: test-schema.sql

### Dependencies
- JUnit 5 (Jupiter)
- Testcontainers 1.20.4
- AssertJ 3.27.3
- OpenCSV 5.10

## Running Tests

### Locally

Run all integration tests:
```bash
mvn test -pl service.data.impl -Dtest="*IntegrationTest"
```

Run specific test suite:
```bash
# Data Extraction tests
mvn test -pl service.data.impl -Dtest=DataExtractionIntegrationTest

# Temporal View tests
mvn test -pl service.data.impl -Dtest=TemporalViewChainIntegrationTest

# Drools Risk Rule tests
mvn test -pl service.data.impl -Dtest=DroolsRiskRuleIntegrationTest

# Analysis Workflow tests
mvn test -pl service.data.impl -Dtest=AnalysisWorkflowIntegrationTest
```

### CI/CD (GitHub Actions)

Integration tests run automatically:
- **Nightly**: Every day at 2 AM UTC
- **Pull Requests**: When changes affect service.data.impl or service.impl
- **Manual**: Can be triggered via workflow_dispatch

See: `.github/workflows/integration-tests.yml`

## Test Data

### Framework Validation Data
Tests use sample data from: `sample-data/framework-validation/`

**Frameworks Covered**:
1. **Temporal Analysis** (5 tests, 255 cases) - temporal/
2. **Comparative Analysis** (3 tests, 138 cases) - comparative/
3. **Pattern Recognition** (2 tests, 180 cases) - pattern/
4. **Predictive Intelligence** (3 tests, 135 cases) - predictive/
5. **Network Analysis** (2 tests, 110 cases) - network/
6. **Decision Intelligence** (2 tests, 140 cases) - decision/

Total: **17 datasets, 958 test cases**

### Test Schema
Database schema initialization: `src/test/resources/test-schema.sql`

**Core Tables**:
- vote_data
- person_data
- assignment_data
- document_data
- rule_violation

**Views**:
- view_ministry_risk_evolution
- view_party_effectiveness_trends
- view_decision_temporal_trends
- view_politician_behavioral_trends

## Performance Benchmarks

### Target Metrics
- **Data Extraction**: < 20 minutes
- **View Queries**: < 1 second
- **Risk Rule Execution**: > 100 profiles/second
- **End-to-End Workflow**: < 30 seconds

### Actual Performance (typical)
- Data Extraction: ~15 minutes
- View Queries: ~200-500ms
- Risk Rule Execution: ~150 profiles/second
- End-to-End Workflow: ~10-15 seconds

## Coverage Reporting

### Generate Coverage Report
```bash
mvn jacoco:report -pl service.data.impl
```

Coverage report location: `service.data.impl/target/site/jacoco/index.html`

### Coverage Thresholds
- **Line Coverage**: 80% minimum
- **Branch Coverage**: 70% minimum

Enforced via JaCoCo Maven plugin configuration.

## Troubleshooting

### Docker/Testcontainers Issues
If testcontainers fail to start:
```bash
# Verify Docker is running
docker info

# Check Docker permissions
docker ps

# Clean up containers
docker container prune
```

### Memory Issues
If tests fail with OOM errors:
```bash
export MAVEN_OPTS="-Xmx4g -XX:MaxMetaspaceSize=1g"
mvn test -pl service.data.impl
```

### Test Data Issues
If framework validation data is missing:
```bash
# Verify sample data exists
ls -la service.data.impl/sample-data/framework-validation/

# Re-extract sample data if needed
psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-framework-validation-data.sql
```

## Best Practices

### Writing Integration Tests
1. **Use Testcontainers**: Always use containers for database tests
2. **Isolation**: Each test should be independent
3. **Cleanup**: Always clean up resources in @AfterAll
4. **Assertions**: Use AssertJ for fluent assertions
5. **Logging**: Log test progress for debugging
6. **Performance**: Set reasonable timeouts

### Test Naming
- Test classes: `*IntegrationTest.java`
- Test methods: `test<Functionality>()`
- Display names: Use @DisplayName for clarity

### Example Test Structure
```java
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("My Integration Tests")
public class MyIntegrationTest {
    
    @Container
    private static final PostgreSQLContainer<?> postgres = ...;
    
    @BeforeAll
    public void setup() { ... }
    
    @AfterAll
    public void teardown() { ... }
    
    @Test
    @DisplayName("Test specific functionality")
    public void testSpecificFunctionality() {
        // Arrange
        // Act
        // Assert
    }
}
```

## Related Documentation

- [DROOLS_RISK_RULES.md](../../DROOLS_RISK_RULES.md) - Risk rule specifications
- [DATABASE_VIEW_INTELLIGENCE_CATALOG.md](../../DATABASE_VIEW_INTELLIGENCE_CATALOG.md) - View documentation
- [sample-data/framework-validation/README.md](../../sample-data/framework-validation/README.md) - Test data documentation

## Contributing

When adding new integration tests:
1. Follow existing test patterns
2. Update this README
3. Add appropriate @Tag annotations
4. Ensure coverage targets are met
5. Update CI/CD workflow if needed

## CI/CD Integration

### GitHub Actions Workflow
The integration tests run in GitHub Actions with:
- PostgreSQL testcontainer (postgres:16-alpine)
- JDK 21 (Temurin distribution)
- Maven cache for faster builds
- Test result reporting
- Coverage reporting
- PR comments with results
- Automatic issue creation on failure

### Artifacts
Test results and coverage reports are uploaded as artifacts:
- **Name**: integration-test-results
- **Contents**: Surefire reports, JaCoCo coverage
- **Retention**: 30 days

## Support

For issues or questions:
- Create an issue on GitHub
- Tag with `integration-tests` label
- Include test output and logs
- Mention @Hack23 for review

---

**Last Updated**: 2025-01-20  
**Maintainer**: CIA Development Team  
**Version**: 1.0
