# 🧪 Citizen Intelligence Agency - Unit Test Plan

[![Unit Test Coverage](https://img.shields.io/badge/Unit%20Test%20Coverage-JaCoCo%20Results-brightgreen?style=flat-square&logo=java)](https://hack23.github.io/cia/jacoco/)
[![Unit Tests](https://img.shields.io/badge/Unit%20Tests-Live%20Results-success?style=flat-square&logo=junit5&logoColor=white)](https://hack23.github.io/cia/surefire.html)
[![Code Quality](https://sonarcloud.io/api/project_badges/measure?project=Hack23_cia&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Hack23_cia)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Hack23_cia&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=Hack23_cia)

> **Compliance Statement**: This document fulfills the testing requirements of the [Hack23 Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) as mandated by ISO 27001 (A.12.1.4), NIST CSF (PR.IP-2), and CIS Controls (16.8).

---

## 📋 Table of Contents

- [Testing Objectives](#-testing-objectives)
- [Testing Strategy](#-testing-strategy)
- [Test Infrastructure](#-test-infrastructure)
- [Coverage Metrics](#-coverage-metrics)
- [Module Testing Approach](#-module-testing-approach)
- [Test Execution](#-test-execution)
- [Quality Gates](#-quality-gates)
- [Continuous Improvement](#-continuous-improvement)
- [Compliance & Standards](#-compliance--standards)

---

## 🎯 Testing Objectives

The Citizen Intelligence Agency maintains comprehensive unit testing to ensure:

- ✅ **80% minimum line coverage** across all production modules
- ✅ **70% minimum branch coverage** for critical business logic
- ✅ **90%+ coverage** for security and authentication components
- ✅ **100% coverage** for utility and validation classes
- ✅ **Zero critical security vulnerabilities** in tested code paths
- ✅ **Public transparency** through live coverage reporting

### Business Goals

- **Quality Assurance**: Prevent regressions through automated testing
- **Security Validation**: Test security controls and authentication flows
- **Maintainability**: Enable safe refactoring with comprehensive test suite
- **Documentation**: Tests serve as executable specifications
- **Compliance**: Meet ISO 27001, NIST CSF, and CIS Controls requirements

---

## 🏗️ Testing Strategy

### Test Naming Convention

The project uses strict naming conventions to distinguish unit tests from integration tests:

#### Unit Tests (`*Test.java`)
- **Suffix**: `*Test.java`
- **Characteristics**:
  - Pure unit tests with mocked dependencies
  - No database access or external API calls
  - Fast execution (< 1 second per test)
  - No Spring context or infrastructure required
- **Examples**: `RiksdagenDateUtilTest`, `ApiDtoSanityTest`, `RemoveDataManagerTest`
- **Maven execution**: Included in standard `mvn test` runs

#### Integration Tests (`*ITest.java`)
- **Suffix**: `*ITest.java`
- **Characteristics**:
  - Tests requiring database access
  - Tests calling external APIs
  - Tests using Spring application context
  - Slower execution, require infrastructure
- **Examples**: `WorldbankTopicApiImplITest`, `DataDAOITest`, `UserHomeITest`
- **Maven execution**: Excluded from unit test runs using `-Dtest='!**ITest*,!**DocumentationTest*'`

#### Build Integration

The `citizen-intelligence-agency/build.xml` and workflows use this exclusion pattern:
```bash
mvn test -Dtest='!**ITest*,!**DocumentationTest*'
```

This ensures unit tests run quickly in CI/CD without external dependencies, while integration tests can be run separately when infrastructure is available.

### Test Pyramid Implementation

Our testing strategy follows the industry-standard test pyramid approach:

```
                    /\
                   /  \
                  / E2E \         10% - End-to-End Tests
                 /  Tests \       (Critical user journeys)
                /----------\
               /            \
              / Integration  \    20% - Integration Tests
             /     Tests      \   (Component interactions)
            /------------------\
           /                    \
          /     Unit Tests       \ 70% - Unit Tests
         /________________________\ (Isolated component testing)
```

### Coverage Targets by Module Type

| Module Category | Line Coverage | Branch Coverage | Priority | Rationale |
|----------------|---------------|-----------------|----------|-----------|
| **Security & Authentication** | 90%+ | 80%+ | Critical | Security-critical code requires maximum validation |
| **Service Layer** | 80%+ | 70%+ | High | Business logic implementation |
| **Data Access (DAO)** | 80%+ | 70%+ | High | Database operations and query validation |
| **Business Logic** | 80%+ | 70%+ | High | Core application functionality |
| **External Integration** | 75%+ | 65%+ | Medium | Third-party API integration |
| **Data Models** | 80%+ | 70%+ | Medium | Entity validation and integrity |
| **UI Components** | 60%+ | 50%+ | Low | Vaadin UI components (E2E tested separately) |
| **Configuration** | 70%+ | 60%+ | Medium | Spring configuration and wiring |

### Test Categories

#### 1. **Unit Tests** (70% of test suite)
- **Purpose**: Test individual classes and methods in isolation
- **Scope**: Single class or component
- **Speed**: < 1 second per test
- **Dependencies**: Mocked using Mockito
- **Execution**: Every commit, every PR

#### 2. **Integration Tests** (20% of test suite)
- **Purpose**: Test component interactions and data flow
- **Scope**: Multiple components, database operations
- **Speed**: < 10 seconds per test
- **Dependencies**: Spring Test Context, H2 in-memory database
- **Execution**: Every commit, every PR

#### 3. **Functional Tests** (10% of test suite)
- **Purpose**: Test complete features and user scenarios
- **Scope**: Full application stack
- **Speed**: < 30 seconds per test
- **Dependencies**: Embedded containers, test database
- **Execution**: Pre-merge, nightly builds

---

## 🛠️ Test Infrastructure

### Testing Frameworks & Tools

| Component | Technology | Version | Purpose |
|-----------|-----------|---------|---------|
| **Test Framework** | JUnit | 4.13.2 | Core testing framework |
| **Mocking** | Mockito | 5.20.0 | Mock object creation |
| **Spring Testing** | Spring Test | 5.3.39 | Spring context testing |
| **Coverage Tool** | JaCoCo | 0.8.14 | Code coverage analysis |
| **Database Testing** | H2 Database | 2.4.240 | In-memory test database |
| **DBUnit** | DBUnit | 3.0.0 | Database test data management |
| **CI/CD Platform** | GitHub Actions | - | Automated test execution |
| **Quality Analysis** | SonarCloud | - | Static analysis & coverage reporting |

### JaCoCo Configuration

The project uses JaCoCo Maven Plugin for comprehensive coverage reporting:

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.14</version>
    <executions>
        <execution>
            <id>pre-unit-test</id>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>post-unit-test</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

**Report Locations:**
- 📊 **JaCoCo HTML Reports**: `target/site/jacoco/index.html` (per module)
- 📊 **SonarCloud Dashboard**: https://sonarcloud.io/summary/new_code?id=Hack23_cia
- 📊 **GitHub Pages**: https://hack23.github.io/cia/jacoco/

---

## 📊 Coverage Metrics

### Current Test Statistics

| Metric | Count | Details |
|--------|-------|---------|
| **Total Maven Modules** | 48 | Multi-module Maven project |
| **Java Source Files** | 1,153 | Production code files |
| **Test Files** | 207 | Test classes (*Test.java) |
| **Test-to-Code Ratio** | 17.9% | 207 tests / 1,153 source files |
| **Lines of Code** | 100,000+ | As reported by SonarCloud |

### Coverage Goals & Thresholds

#### Minimum Thresholds (Enforced in CI/CD)

```
✓ Line Coverage:   80% minimum (global)
✓ Branch Coverage: 70% minimum (global)
✓ Security Modules: 90% minimum (line coverage)
✓ Utility Classes:  100% recommended
```

#### Coverage Enforcement

Coverage thresholds are **enforced** through:
1. **Maven Build**: JaCoCo plugin fails build if thresholds not met
2. **SonarCloud Quality Gate**: Blocks PRs below coverage targets
3. **GitHub Actions**: CI workflow validates coverage on every commit

---

## 🗂️ Module Testing Approach

The CIA project consists of **48 Maven modules** organized into logical categories:

### 1. **Model Modules** (23 modules)

#### External Data Models
API integration models for external Swedish government services:

- **Riksdagen (Parliament) API Models** (9 modules)
  - `model.external.riksdagen` - Core parliament data
  - `model.external.riksdagen.documentcontent.impl` - Document content
  - `model.external.riksdagen.dokumentlista.impl` - Document lists
  - `model.external.riksdagen.dokumentstatus.impl` - Document status
  - `model.external.riksdagen.person.impl` - Person data
  - `model.external.riksdagen.personlista.impl` - Person lists
  - `model.external.riksdagen.utskottsforslag.impl` - Committee proposals
  - `model.external.riksdagen.votering.impl` - Voting records
  - `model.external.riksdagen.voteringlista.impl` - Voting lists

- **Val (Election Authority) Models** (5 modules)
  - `model.external.val` - Core election data
  - `model.external.val.kommunvalkrets.impl` - Municipal districts
  - `model.external.val.landstingvalkrets.impl` - County districts
  - `model.external.val.partier.impl` - Political parties
  - `model.external.val.riksdagsvalkrets.impl` - Parliamentary districts

- **World Bank Models** (5 modules)
  - `model.external.worldbank` - Core World Bank data
  - `model.external.worldbank.countries.impl` - Country data
  - `model.external.worldbank.data.impl` - Economic indicators
  - `model.external.worldbank.indicators.impl` - Indicator metadata
  - `model.external.worldbank.topic.impl` - Topic classifications

#### Internal Data Models
- `model.common.api` - Common model interfaces
- `model.common.impl` - Common model implementations
- `model.internal.application` - Application domain models
- `model.internal.application.user.impl` - User and security models

**Testing Strategy for Models:**
- ✅ Entity validation tests
- ✅ JAXB marshaling/unmarshaling roundtrip tests
- ✅ JPA entity mapping verification
- ✅ Equals/hashCode contract validation
- ✅ Serialization compatibility tests

**Example Test Pattern:**
```java
@Test
public void testRoundtripSerialization() {
    DocumentData original = createTestData();
    String xml = marshal(original);
    DocumentData deserialized = unmarshal(xml);
    assertEquals(original, deserialized);
}
```

### 2. **Service Modules** (11 modules)

#### Core Service Layer
- `service.api` - Service interfaces and contracts
- `service.impl` - Core service implementations

#### Component Services
- `service.component.agent.api` - Agent component interfaces
- `service.component.agent.impl` - Agent component implementations

#### Data Access Services
- `service.data.api` - DAO interfaces
- `service.data.impl` - DAO implementations (Hibernate/JPA)

#### External Integration Services
- `service.external.common` - Common integration utilities
- `service.external.esv` - Swedish Financial Management Authority integration
- `service.external.riksdagen` - Parliament API integration
- `service.external.val` - Election Authority integration
- `service.external.worldbank` - World Bank API integration

**Testing Strategy for Services:**
- ✅ Business logic validation with unit tests
- ✅ Transaction management verification
- ✅ Exception handling scenarios
- ✅ External API mocking (Mockito)
- ✅ Database operations with H2 in-memory
- ✅ Spring context integration tests

**Example Test Pattern:**
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ServiceIntegrationTest {
    
    @Autowired
    private UserService userService;
    
    @Mock
    private ExternalApiClient apiClient;
    
    @Test
    public void testServiceOperation() {
        when(apiClient.fetchData()).thenReturn(mockData);
        Result result = userService.processData();
        verify(apiClient, times(1)).fetchData();
        assertNotNull(result);
    }
}
```

### 3. **Web Application Modules (2 modules)**

- `citizen-intelligence-agency` - Main Vaadin web application
- `web-widgets` - Reusable UI components

**Testing Strategy for Web:**
- ✅ Spring MVC controller tests
- ✅ Vaadin UI component tests
- ✅ Security filter chain validation
- ✅ Session management tests
- ✅ Integration with service layer
- 📝 E2E tests (separate test plan)

### 4. **Infrastructure Modules** (8 modules)

- `parent-pom` - Parent POM configuration
- `parent-model-pom` - Model modules parent
- `parent-model-external-pom` - External models parent
- `parent-model-internal-pom` - Internal models parent
- `parent-model-jpa2-pom` - JPA models parent
- `parent-service-pom` - Service modules parent
- `parent-web-pom` - Web modules parent
- `testfoundation` - Common test utilities

**Testing Strategy for Infrastructure:**
- ✅ Test utility validation
- ✅ Configuration verification
- ✅ Build configuration tests
- ⚙️ Minimal direct testing (build-time validation)

### 5. **Distribution & Deployment** (4 modules)

- `cia-dist-deb` - Debian package distribution
- `cia-dist-cloudformation` - AWS CloudFormation templates
- `jms-broker` - JMS message broker configuration
- `encrypt.properties` - Property encryption utilities

**Testing Strategy for Distribution:**
- ✅ Package assembly verification
- ✅ Configuration validation
- ✅ Integration smoke tests
- 🚀 Deployment validation (separate E2E tests)

---

## 🚀 Test Execution

### Local Development

#### Run All Tests
```bash
mvn clean test
```

#### Run Tests with Coverage Report
```bash
mvn clean test jacoco:report
```

#### Run Tests for Specific Module
```bash
cd model.external.riksdagen.person.impl
mvn test
```

#### View Coverage Report
```bash
# After running tests with jacoco:report
open target/site/jacoco/index.html
```

### Continuous Integration (GitHub Actions)

Tests are automatically executed on:
- ✅ **Every Commit**: Fast unit tests on all branches
- ✅ **Pull Requests**: Full test suite + coverage analysis
- ✅ **Merge to Main**: Integration tests + deployment validation
- ✅ **Nightly Builds**: Extended test suite + performance tests

**GitHub Actions Workflow:**
```yaml
name: CI Build
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 25
        uses: actions/setup-java@v4
        with:
          java-version: '25'
      - name: Run Tests
        run: mvn clean test jacoco:report
      - name: SonarCloud Analysis
        run: mvn sonar:sonar
```

### Test Reports

| Report Type | Location | Access |
|-------------|----------|--------|
| **JaCoCo HTML** | `target/site/jacoco/` | Local, GitHub Pages |
| **Surefire Report** | `target/surefire-reports/` | Local, GitHub Pages |
| **SonarCloud** | https://sonarcloud.io | Public dashboard |
| **GitHub Actions** | Actions tab | PR status checks |

---

## 🔒 Quality Gates

### Pre-Merge Validation

All pull requests must pass the following quality gates:

#### 1. **Test Execution**
- ✅ All tests must pass (0 failures)
- ✅ No compilation errors
- ✅ No test timeouts

#### 2. **Coverage Thresholds**
- ✅ Minimum 80% line coverage (global)
- ✅ Minimum 70% branch coverage (global)
- ✅ No decrease in overall coverage (delta ≥ 0%)

#### 3. **Code Quality (SonarCloud)**
- ✅ Quality Gate: "Passed"
- ✅ Zero critical vulnerabilities
- ✅ Zero blocker issues
- ✅ Maintainability rating: A or B
- ✅ Security rating: A

#### 4. **Security Scanning**
- ✅ CodeQL analysis: No new alerts
- ✅ Dependency Check: No critical CVEs
- ✅ OWASP ZAP: No high-severity findings

### Build Failure Policy

Builds fail if:
- ❌ Any test fails
- ❌ Coverage drops below 80%/70%
- ❌ New critical security vulnerabilities introduced
- ❌ SonarCloud quality gate fails

---

## 🔄 Continuous Improvement

### Coverage Monitoring

- **Weekly Reviews**: Coverage trend analysis in team meetings
- **Monthly Reports**: Test effectiveness metrics and flaky test analysis
- **Quarterly Goals**: Coverage improvement targets per module

### Test Maintenance

- **Flaky Test Management**: Tests with >1% failure rate are investigated immediately
- **Performance Monitoring**: Tests taking >1s (unit) or >10s (integration) are optimized
- **Obsolete Test Cleanup**: Redundant tests removed during refactoring
- **Test Naming Standards**: Descriptive names following `should_expectedBehavior_when_stateUnderTest` pattern

### Testing Best Practices

#### ✅ Do's
- Write tests before fixing bugs (TDD approach)
- Keep tests independent and isolated
- Use meaningful test names describing behavior
- Mock external dependencies
- Test both success and failure scenarios
- Include edge cases and boundary conditions
- Maintain test data builders/factories
- Review test coverage in code reviews

#### ❌ Don'ts
- Don't test framework code or third-party libraries
- Don't write tests dependent on execution order
- Don't use production data in tests
- Don't skip tests instead of fixing them
- Don't write overly complex tests
- Don't test implementation details
- Don't commit commented-out tests

---

## 📜 Compliance & Standards

### Regulatory Requirements

This testing approach satisfies:

#### ISO 27001:2022
- **A.12.1.4 - Separation of development, testing and operational environments**
  - ✅ Separate test environments with H2 database
  - ✅ Test data isolation from production
  - ✅ Automated test execution in CI/CD

#### NIST Cybersecurity Framework 2.0
- **PR.IP-2 - Secure Development Lifecycle**
  - ✅ Unit testing integrated into development process
  - ✅ Automated security testing (CodeQL, OWASP)
  - ✅ Coverage reporting and quality gates

#### CIS Controls v8.1
- **16.8 - Maintain up-to-date test scripts and processes**
  - ✅ Comprehensive test documentation
  - ✅ Version-controlled test code
  - ✅ Regular test review and updates

### Industry Standards

- **OWASP ASVS (Application Security Verification Standard)**: Security testing integrated
- **IEEE 829 (Software Test Documentation)**: Structured test planning approach
- **ISO/IEC 25010 (Software Quality Requirements)**: Coverage of quality characteristics

---

## 📚 Additional Resources

### Internal Documentation
- [Architecture Overview](ARCHITECTURE.md) - System architecture and design
- [Security Architecture](SECURITY_ARCHITECTURE.md) - Security controls and design
- [Contributing Guidelines](CONTRIBUTING.md) - Development workflow and standards
- [Data Model](DATA_MODEL.md) - Database schema and entities

### External Standards
- [Hack23 ISMS Public Repository](https://github.com/Hack23/ISMS-PUBLIC)
- [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)
- [Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md)

### Testing Resources
- [JUnit 4 Documentation](https://junit.org/junit4/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Spring Testing Guide](https://docs.spring.io/spring-framework/reference/testing.html)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)

---

## 📞 Contact & Support

**Test Infrastructure Questions:**
- **GitHub Issues**: https://github.com/Hack23/cia/issues
- **Security Issues**: security@hack23.com (see [SECURITY.md](SECURITY.md))

**Project Leadership:**
- **Maintainer**: James Sörling
- **Organization**: Hack23 AB
- **Website**: https://www.hack23.com

---

## 📊 Appendix: Test Metrics Dashboard

### Key Performance Indicators (KPIs)

| Metric | Target | Current | Status |
|--------|--------|---------|--------|
| **Line Coverage** | ≥80% | See [SonarCloud](https://sonarcloud.io/summary/new_code?id=Hack23_cia) | 🔄 Monitored |
| **Branch Coverage** | ≥70% | See [SonarCloud](https://sonarcloud.io/summary/new_code?id=Hack23_cia) | 🔄 Monitored |
| **Test Execution Time** | <5 min | 🔄 Optimizing | ⚠️ In Progress |
| **Flaky Test Rate** | <1% | 🔄 Tracking | ✅ Target |
| **Test-to-Code Ratio** | ≥15% | 17.9% | ✅ Target |

### Historical Trends

Coverage trends and historical data available at:
- **SonarCloud Dashboard**: https://sonarcloud.io/summary/new_code?id=Hack23_cia
- **GitHub Actions**: https://github.com/Hack23/cia/actions
- **GitHub Pages Reports**: https://hack23.github.io/cia/

---

**Document Version**: 1.0  
**Last Updated**: 2025-11-14  
**Next Review**: 2026-02-14 (Quarterly)  
**Owner**: Hack23 AB Development Team  
**Compliance Status**: ✅ **ISMS-Compliant**

---

<div align="center">

[![Unit Test Coverage](https://img.shields.io/badge/Unit%20Test%20Coverage-JaCoCo%20Results-brightgreen?style=flat-square&logo=java)](https://hack23.github.io/cia/jacoco/)
[![Unit Tests](https://img.shields.io/badge/Unit%20Tests-Live%20Results-success?style=flat-square&logo=junit5&logoColor=white)](https://hack23.github.io/cia/surefire.html)
[![Code Quality](https://sonarcloud.io/api/project_badges/measure?project=Hack23_cia&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Hack23_cia)

**Transparency Through Testing** | **Quality Through Validation** | **Security Through Verification**

</div>
