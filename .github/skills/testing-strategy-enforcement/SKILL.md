---
name: testing-strategy-enforcement
description: Test strategy governance, coverage enforcement (80% line, 70% branch), test pyramid adherence, and CI gate enforcement
license: Apache-2.0
---

# Testing Strategy Enforcement Skill

## Purpose

This skill enforces the CIA platform's testing strategy, ensuring consistent coverage thresholds, proper test pyramid adherence, and CI/CD gate enforcement. It provides actionable guidance for maintaining test quality across all modules.

## When to Use This Skill

Apply this skill when:
- ✅ Writing tests for new features
- ✅ Reviewing PRs for test adequacy
- ✅ Configuring CI/CD test gates
- ✅ Investigating test failures or flaky tests
- ✅ Measuring and improving test coverage
- ✅ Deciding test scope for a change
- ✅ Refactoring existing test suites

Do NOT use for:
- ❌ Security-specific testing (use secure-code-review)
- ❌ Performance testing design (use performance analysis)
- ❌ UI/UX validation (use playwright-ui-testing)

## Coverage Requirements

### Mandatory Thresholds

| Metric | Minimum | Target | Blocking? |
|---|---|---|---|
| Line Coverage | 80% | 85%+ | Yes — CI gate |
| Branch Coverage | 70% | 75%+ | Yes — CI gate |
| New Code Coverage | 80% | 90%+ | Yes — SonarCloud |
| Mutation Score | N/A | 60%+ | Advisory |

### JaCoCo Configuration

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <executions>
        <execution>
            <id>check</id>
            <goals><goal>check</goal></goals>
            <configuration>
                <rules>
                    <rule>
                        <element>BUNDLE</element>
                        <limits>
                            <limit>
                                <counter>LINE</counter>
                                <value>COVEREDRATIO</value>
                                <minimum>0.80</minimum>
                            </limit>
                            <limit>
                                <counter>BRANCH</counter>
                                <value>COVEREDRATIO</value>
                                <minimum>0.70</minimum>
                            </limit>
                        </limits>
                    </rule>
                </rules>
            </configuration>
        </execution>
    </executions>
</plugin>
```

## Test Pyramid

```
        ╱╲
       ╱  ╲        E2E Tests (Playwright/Selenium)
      ╱ 5% ╲       - Critical user workflows only
     ╱──────╲      - Login, navigation, key features
    ╱        ╲
   ╱  15-20%  ╲    Integration Tests (Spring Test + TestContainers)
  ╱            ╲   - Service layer, repository layer
 ╱──────────────╲  - External API integration
╱                ╲
╱    75-80%       ╲ Unit Tests (JUnit 5 + Mockito)
╱──────────────────╲- Business logic, utilities, models
```

### Unit Tests (75-80% of total tests)

**Framework:** JUnit 5 + Mockito + AssertJ

**What to Test:**
- Business logic in service classes
- Data transformation and mapping
- Validation logic
- Utility methods
- Exception handling paths
- Edge cases and boundary conditions

**Patterns:**
```java
@ExtendWith(MockitoExtension.class)
class PoliticianServiceTest {

    @Mock
    private PoliticianRepository repository;

    @InjectMocks
    private PoliticianServiceImpl service;

    @Test
    void shouldReturnPoliticianWhenFound() {
        // Given
        var politician = createTestPolitician();
        when(repository.findById("id-1")).thenReturn(Optional.of(politician));

        // When
        var result = service.findById("id-1");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Test Name");
        verify(repository).findById("id-1");
    }

    @Test
    void shouldThrowWhenPoliticianNotFound() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getById("invalid"))
            .isInstanceOf(EntityNotFoundException.class);
    }
}
```

### Integration Tests (15-20% of total tests)

**Framework:** Spring Test + TestContainers

**What to Test:**
- Database repository operations
- Spring Security filter chains
- Service layer with real dependencies
- External API client integration
- Transaction boundaries

**Patterns:**
```java
@SpringBootTest
@ActiveProfiles("test")
class PoliticianRepositoryIntegrationTest {

    @Autowired
    private PoliticianRepository repository;

    @Test
    void shouldPersistAndRetrievePolitician() {
        var politician = createTestPolitician();
        repository.save(politician);

        var found = repository.findById(politician.getId());
        assertThat(found).isPresent();
    }
}
```

### E2E Tests (5% of total tests)

**Framework:** Playwright

**What to Test:**
- Critical user workflows (login, navigation)
- Key data visualization pages
- Admin operations
- Cross-browser compatibility

## CI Gate Enforcement

### Required CI Gates

| Gate | Tool | Threshold | Stage |
|---|---|---|---|
| Compilation | Maven | Zero errors | Build |
| Unit Tests | JUnit 5 | 100% pass | Test |
| Coverage | JaCoCo | 80% line, 70% branch | Test |
| Code Quality | SonarCloud | Quality gate pass | Analysis |
| Security Scan | CodeQL | Zero critical/high | Security |
| Dependency Check | OWASP DC | CVSS < 7 | Security |
| Integration Tests | Spring Test | 100% pass | Integration |

### Gate Failure Response

```
CI Gate Failed
    │
    ├─→ Test Failure?
    │   ├─→ Is it a flaky test? → Fix flaky test, don't skip
    │   ├─→ Is it a real regression? → Fix the code
    │   └─→ Is the test outdated? → Update the test
    │
    ├─→ Coverage Below Threshold?
    │   ├─→ New code not tested? → Add tests for new code
    │   ├─→ Existing coverage dropped? → Add missing tests
    │   └─→ Threshold too aggressive? → Document exception
    │
    ├─→ Quality Gate Failed?
    │   ├─→ Critical issue? → Fix immediately
    │   ├─→ False positive? → Document suppression
    │   └─→ Technical debt? → Create backlog item
    │
    └─→ Security Scan Failed?
        ├─→ Real vulnerability? → Fix before merge
        └─→ False positive? → Add suppression with justification
```

## Test Quality Guidelines

### Test Naming Convention
```
methodUnderTest_condition_expectedResult
```
Example: `findById_whenPoliticianExists_returnsPolitician`

### Test Anti-Patterns to Avoid

| Anti-Pattern | Problem | Solution |
|---|---|---|
| No assertions | Test passes vacuously | Add meaningful assertions |
| Testing implementation | Brittle, breaks on refactor | Test behavior, not implementation |
| Ignoring exceptions | Hides failures | Use assertThrows or assertThatThrownBy |
| Shared mutable state | Tests affect each other | Isolate test state |
| Excessive mocking | Tests don't verify real behavior | Mock boundaries only |
| Flaky tests | Erode CI trust | Fix root cause, never @Ignore |
| Test per line | Slow, redundant | Test per behavior |

### Coverage Exceptions

Coverage exceptions are permitted only when:
1. Code is auto-generated (JPA metamodel, JAXB)
2. Code is boilerplate (getters/setters on simple DTOs)
3. Infrastructure code with no business logic

**Exception Process:**
```xml
<!-- JaCoCo exclusion in pom.xml -->
<configuration>
    <excludes>
        <exclude>**/model/*_.class</exclude>    <!-- JPA metamodel -->
        <exclude>**/generated/**</exclude>       <!-- Generated code -->
    </excludes>
</configuration>
```

## Measuring Test Effectiveness

### Reports and Dashboards
```bash
# Generate coverage report
mvn clean test jacoco:report

# View report
open target/site/jacoco/index.html

# Generate aggregate report for multi-module
mvn clean verify -pl . -am
```

### Coverage Trend Monitoring
- SonarCloud tracks coverage trends over time
- Coverage must not decrease on any PR
- Target steady improvement toward 85%+ line coverage

## ISMS Alignment

| Testing Area | ISO 27001 Control | NIST CSF |
|---|---|---|
| Unit Testing | A.8.29 | PR.IP-12 |
| Integration Testing | A.8.29 | PR.IP-12 |
| Security Testing | A.8.33 | DE.CM-8 |
| Test Documentation | A.8.25 | PR.IP-12 |
| CI/CD Gates | A.8.31 | PR.IP-12 |

## References

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [CIA UnitTestPlan.md](../../UnitTestPlan.md)
