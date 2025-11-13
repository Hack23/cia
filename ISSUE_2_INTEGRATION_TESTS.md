# Issue #2: ✅ Comprehensive Integration Test Suite for Data Import Pipelines

**Priority:** 🟠 High  
**Effort:** M (Medium - 2-3 weeks)  
**Labels:** `testing`, `priority:high`, `size:medium`, `quality`, `data-integrity`

## 🎯 Objective

Develop a comprehensive integration test suite for critical data import pipelines to ensure data integrity, reliability, and correctness of political data ingestion from external Swedish government APIs (Riksdagen, Election Authority, World Bank, ESV).

## 📋 Background

The CIA platform's value proposition relies on accurate, timely political data. The data import pipelines are mission-critical components that fetch, transform, and persist data from multiple external sources. Currently, test coverage is inconsistent across modules, with only 219 test files covering 1,153 source files (~19% file coverage ratio).

**Why This Matters:**
- **Data Integrity is Critical**: Incorrect political data undermines platform credibility
- **Complex Transformations**: Data goes through multiple transformation stages
- **External API Dependencies**: Changes in external APIs can break imports silently
- **Regulatory Compliance**: ISMS requires validated data processing
- **User Trust**: Analytical accuracy depends on correct data import

**Current State:**
- ✅ Unit tests exist for some data models
- ✅ Basic integration test framework (testfoundation module)
- ❌ No comprehensive end-to-end import pipeline tests
- ❌ No automated tests for external API integration
- ❌ No data validation tests for transformed entities
- ❌ No regression tests for data quality metrics

**Current Metrics:**
- **Total Maven Modules:** 49
- **Java Source Files:** 1,153
- **Java Test Files:** 219 (~19% coverage ratio)
- **External Data Sources:** 4 major APIs (Riksdagen, Val.se, World Bank, ESV)
- **Data Import Services:** 9+ service modules

## ✅ Acceptance Criteria

- [ ] Create integration tests for all 4 external data source integrations
- [ ] Implement end-to-end pipeline tests for each data import workflow
- [ ] Add data validation tests to verify transformed entity correctness
- [ ] Create mock/stub services for external APIs to enable offline testing
- [ ] Implement regression tests for historical data import scenarios
- [ ] Add performance tests for large data import batches
- [ ] Create data quality validation tests (completeness, accuracy, consistency)
- [ ] Implement error handling and retry logic tests
- [ ] Add monitoring/alerting for data import failures
- [ ] Achieve 70%+ test coverage for data import service modules
- [ ] Document testing strategy and test data management approach

## 🛠️ Implementation Guidance

### Files to Create/Modify

1. **New Test Modules:**
   - `service.external.riksdagen/src/test/java/` - Riksdagen API integration tests
   - `service.external.val/src/test/java/` - Election Authority API integration tests
   - `service.external.worldbank/src/test/java/` - World Bank API integration tests
   - `service.external.esv/src/test/java/` - ESV API integration tests
   - `service.data.impl/src/test/java/` - Data persistence integration tests

2. **Test Infrastructure:**
   - `testfoundation/src/main/java/com/hack23/cia/testfoundation/` - Test utilities
     - `MockApiServer.java` - WireMock-based API mock server
     - `TestDataFactory.java` - Test data builders
     - `DataValidationAssertions.java` - Custom assertions for political data
     - `IntegrationTestBase.java` - Base class for integration tests

3. **Test Data Resources:**
   - `*/src/test/resources/api-responses/` - Mock API response fixtures
   - `*/src/test/resources/test-data/` - Sample political data for testing
   - `*/src/test/resources/validation-rules/` - Data quality validation rules

4. **Test Configuration:**
   - `*/src/test/resources/application-test.properties` - Test-specific Spring config
   - `*/src/test/resources/logback-test.xml` - Test logging configuration

5. **Documentation:**
   - `TESTING_STRATEGY.md` - Comprehensive testing approach documentation
   - `docs/testing/` - Test data management, mock server usage guides

### Approach

**Phase 1: Test Infrastructure Setup (Week 1)**

1. **Set up WireMock for API Mocking**
   ```xml
   <dependency>
       <groupId>org.wiremock</groupId>
       <artifactId>wiremock-standalone</artifactId>
       <version>3.10.0</version>
       <scope>test</scope>
   </dependency>
   ```

2. **Create Base Integration Test Class**
   - Spring Boot Test configuration
   - Embedded PostgreSQL database (or Testcontainers)
   - WireMock server setup/teardown
   - Transaction rollback for test isolation

3. **Capture Real API Responses**
   - Record actual API responses from external sources
   - Sanitize any sensitive data
   - Store as test fixtures in JSON/XML format

**Phase 2: Riksdagen API Integration Tests (Week 2)**

Focus on the most critical data source (Swedish Parliament):

1. **Test Coverage Areas:**
   - Member/Politician data import
   - Voting records (voteringlista) import
   - Document import (dokumentlista)
   - Committee assignments
   - Speech records

2. **Test Scenarios:**
   - Happy path: Successful data import
   - Empty response handling
   - Malformed XML/JSON responses
   - API rate limiting responses
   - Network timeout scenarios
   - Incremental updates (delta imports)
   - Data deduplication

3. **Validation Tests:**
   - Verify entity mapping correctness
   - Check referential integrity
   - Validate business rules (e.g., voting percentages sum to 100%)
   - Confirm audit trail creation

**Phase 3: Other External Sources & Data Quality (Week 3)**

1. **Val.se (Election Authority) Tests**
   - Election results import
   - Party registration data
   - Electoral district data
   - Historical election data

2. **World Bank & ESV Tests**
   - Economic indicators import
   - Government financial data
   - Time series data validation

3. **Data Quality Tests**
   - Completeness: Required fields populated
   - Accuracy: Cross-reference checks
   - Consistency: Relationships maintained
   - Timeliness: Import frequency validation

**Phase 4: Performance & Monitoring (Week 4)**

1. **Performance Tests**
   - Large batch import tests (e.g., 10,000 voting records)
   - Concurrent import handling
   - Database connection pool under load
   - Memory usage profiling

2. **Monitoring & Alerts**
   - Add metrics for import success/failure rates
   - Track import duration trends
   - Alert on data quality degradation
   - Dashboard for import health

3. **Documentation & Knowledge Transfer**
   - Document test patterns and practices
   - Create test data management guide
   - Team training on running integration tests

### Example Test Implementation

**Riksdagen Politician Import Integration Test:**

```java
package com.hack23.cia.service.external.riksdagen;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenPersonService;
import com.hack23.cia.testfoundation.IntegrationTestBase;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RiksdagenPersonServiceIntegrationTest extends IntegrationTestBase {

    @Autowired
    private RiksdagenPersonService personService;

    private WireMockServer wireMockServer;

    @BeforeAll
    void setupWireMock() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        configureFor("localhost", 8089);
    }

    @AfterAll
    void teardownWireMock() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Should successfully import politician data from Riksdagen API")
    void testImportPoliticianData() {
        // Arrange: Setup mock API response
        stubFor(get(urlPathEqualTo("/personlista/"))
                .withQueryParam("utformat", equalTo("xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBodyFile("riksdagen/personlista-sample.xml")));

        // Act: Execute import
        List<PersonData> persons = personService.getPersonList();

        // Assert: Verify data correctness
        assertThat(persons).isNotEmpty();
        assertThat(persons).hasSize(5); // Sample file has 5 politicians
        
        PersonData firstPerson = persons.get(0);
        assertThat(firstPerson.getFirstName()).isNotBlank();
        assertThat(firstPerson.getLastName()).isNotBlank();
        assertThat(firstPerson.getIntegritetskyddad()).isFalse();
        assertThat(firstPerson.getSourceId()).isNotBlank();
        
        // Verify API was called exactly once
        verify(1, getRequestedFor(urlPathEqualTo("/personlista/")));
    }

    @Test
    @DisplayName("Should handle API timeout gracefully")
    void testHandleApiTimeout() {
        // Arrange: Simulate timeout
        stubFor(get(urlPathEqualTo("/personlista/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withFixedDelay(6000))); // 6 second delay

        // Act & Assert: Should throw or retry appropriately
        assertThatThrownBy(() -> personService.getPersonList())
                .isInstanceOf(ServiceException.class)
                .hasMessageContaining("timeout");
    }

    @Test
    @DisplayName("Should handle malformed XML response")
    void testHandleMalformedXml() {
        // Arrange: Return invalid XML
        stubFor(get(urlPathEqualTo("/personlista/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBody("<invalid>malformed<xml>")));

        // Act & Assert
        assertThatThrownBy(() -> personService.getPersonList())
                .isInstanceOf(DataParsingException.class);
    }

    @Test
    @DisplayName("Should persist imported politicians to database")
    void testPersistImportedData() {
        // Arrange
        stubFor(get(urlPathEqualTo("/personlista/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBodyFile("riksdagen/personlista-sample.xml")));

        // Act: Import and persist
        personService.importAndPersistPersonList();

        // Assert: Verify database contains data
        List<PersonData> persisted = personRepository.findAll();
        assertThat(persisted).hasSize(5);
        
        // Verify audit trail
        assertThat(auditRepository.findByEntityType("PersonData")).isNotEmpty();
    }

    @Test
    @DisplayName("Should handle incremental updates correctly")
    void testIncrementalUpdate() {
        // Arrange: Initial import
        stubFor(get(urlPathEqualTo("/personlista/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("riksdagen/personlista-initial.xml")));
        personService.importAndPersistPersonList();

        // Act: Update with new data
        stubFor(get(urlPathEqualTo("/personlista/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("riksdagen/personlista-updated.xml")));
        personService.importAndPersistPersonList();

        // Assert: Verify updates applied, no duplicates
        List<PersonData> all = personRepository.findAll();
        assertThat(all).hasSize(5); // No duplicates
        
        PersonData updated = personRepository.findBySourceId("123");
        assertThat(updated.getParty()).isEqualTo("M"); // Party changed
    }
}
```

**Data Quality Validation Test:**

```java
@Test
@DisplayName("Should validate data quality metrics after import")
void testDataQualityValidation() {
    // Arrange & Act
    personService.importAndPersistPersonList();
    
    // Assert: Data quality checks
    DataQualityReport report = dataQualityService.generateReport();
    
    // Completeness: All required fields populated
    assertThat(report.getCompletenessScore()).isGreaterThan(0.95);
    
    // Accuracy: Cross-reference with known data
    assertThat(report.getAccuracyScore()).isGreaterThan(0.90);
    
    // Consistency: Referential integrity maintained
    assertThat(report.getConsistencyScore()).isEqualTo(1.0);
    
    // Timeliness: Data imported within 24 hours
    assertThat(report.getDataFreshness()).isLessThanOrEqualTo(Duration.ofHours(24));
}
```

### Test Infrastructure Components

**Base Integration Test Class:**

```java
package com.hack23.cia.testfoundation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public abstract class IntegrationTestBase {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setupBaseTest() {
        // Clean database before each test
        cleanDatabase();
    }

    @AfterEach
    void teardownBaseTest() {
        // Transaction rollback handles cleanup
    }

    protected void cleanDatabase() {
        // Truncate tables in correct order (respecting FK constraints)
        jdbcTemplate.execute("TRUNCATE TABLE application_event CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE person_data CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE voting_data CASCADE");
        // Add other tables as needed
    }

    protected void loadTestData(String sqlScript) {
        // Utility to load SQL test data
        jdbcTemplate.execute(readResource("/test-data/" + sqlScript));
    }
}
```

## 🔗 Related Resources

- [Spring Boot Testing Documentation](https://docs.spring.io/spring-boot/reference/testing/index.html)
- [WireMock Documentation](https://wiremock.org/docs/)
- [AssertJ Documentation](https://assertj.github.io/doc/)
- [Testcontainers Documentation](https://testcontainers.com/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Riksdagen Open Data API](http://data.riksdagen.se/)
- [Swedish Election Authority Data](http://www.val.se/)
- [World Bank Open Data API](https://datahelpdesk.worldbank.org/knowledgebase/topics/125589)

## 📊 Success Metrics

### Key Performance Indicators (KPIs)

1. **Test Coverage**:
   - **Target**: 70%+ line coverage for service.external.* modules
   - **Current**: ~19% file coverage ratio (219 tests / 1,153 source files)
   - **Measurement**: JaCoCo reports per module

2. **Test Reliability**:
   - **Target**: <1% flaky test rate
   - **Measurement**: Test stability over 100 runs

3. **Data Quality**:
   - **Completeness**: 95%+ of required fields populated
   - **Accuracy**: 90%+ cross-validation success rate
   - **Consistency**: 100% referential integrity maintained

4. **Import Success Rate**:
   - **Target**: 99%+ successful imports
   - **Measurement**: Import success/failure ratio in production

5. **Performance**:
   - **Import Duration**: <10 minutes for full dataset
   - **Resource Usage**: <2GB memory for import process

### Test Metrics Dashboard

Track in CI/CD:
- Total test count trend
- Test execution time trend
- Test coverage by module
- Flaky test rate
- Data quality score trend

## 💰 Business Impact

### Risk Mitigation
- **Data Integrity**: Prevents incorrect political data from undermining platform credibility
- **Regulatory Compliance**: Meets ISMS data validation requirements
- **User Trust**: Ensures analytical accuracy and reliability
- **Operational Stability**: Catches regressions before production

### Value Metrics
- **Quality Improvement**: 70%+ test coverage vs. current 19% file coverage
- **Bug Detection**: Catch data import issues in CI vs. production
- **Confidence**: Rapid feature development with safety net
- **Documentation**: Tests serve as living documentation of data import behavior

## 🔐 Security Considerations

1. **Test Data Privacy**: Ensure test data doesn't contain real PII
2. **API Credentials**: Use test-specific API keys, never production credentials
3. **Data Sanitization**: Sanitize captured API responses before committing
4. **Access Control**: Limit access to sensitive test data
5. **Isolation**: Tests must not affect production systems

## 📝 Documentation Updates Required

1. **New: TESTING_STRATEGY.md**: Comprehensive testing approach
2. **New: docs/testing/API_MOCKING.md**: WireMock usage guide
3. **New: docs/testing/TEST_DATA_MANAGEMENT.md**: Test data creation and maintenance
4. **CONTRIBUTING.md**: Add testing guidelines for contributors
5. **README.md**: Add testing section with quick start guide

## 🎯 Definition of Done

- [ ] Integration tests created for all 4 external API integrations
- [ ] 70%+ test coverage achieved for service.external.* modules
- [ ] All tests pass reliably in CI/CD pipeline
- [ ] Test data fixtures created and documented
- [ ] WireMock infrastructure set up and documented
- [ ] Data quality validation tests implemented
- [ ] Performance tests for large imports added
- [ ] Error handling tests cover timeout, malformed data, rate limiting
- [ ] Documentation updated (TESTING_STRATEGY.md, CONTRIBUTING.md)
- [ ] Team trained on writing integration tests
- [ ] Test metrics dashboard created in CI/CD
- [ ] All tests run in <10 minutes total

## 🏷️ Issue Metadata

**Priority:** 🟠 High  
**Effort:** M (Medium - 2-3 weeks, ~40-60 hours)  
**Type:** Enhancement  
**Component:** Testing, Quality Assurance, Data Import  
**Assignee:** TBD (Recommend: QA Lead or Senior Developer)  
**Milestone:** Q1 2025  
**Related Issues:**
- Supports data integrity and ISMS compliance
- Enables confident refactoring of import pipelines
- Prerequisite for implementing new data sources

**Dependencies:**
- None (can start immediately)

**Blocking:**
- None (parallel with other work)

---

**Created:** 2025-11-13  
**Status:** Proposed (Ready for GitHub Issue Creation)  
**Issue Number:** TBD (Awaiting GitHub API authentication)
