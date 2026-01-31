---
name: unit-testing-patterns
description: Implement JUnit 5, Mockito, and AssertJ patterns with 80%+ code coverage for CIA platform unit tests
license: Apache-2.0
---

# Unit Testing Patterns Skill

## Purpose

Write effective, maintainable unit tests using JUnit 5, Mockito, and AssertJ that achieve 80%+ code coverage.

## When to Use

- ✅ Testing business logic in services
- ✅ Testing data validation
- ✅ Testing utility functions
- ✅ Test-driven development (TDD)

## JUnit 5 Patterns

### Test Structure (AAA Pattern)

```java
@ExtendWith(MockitoExtension.class)
class PoliticianServiceTest {
    
    @Mock
    private PoliticianRepository repository;
    
    @Mock
    private AuditLogger auditLogger;
    
    @InjectMocks
    private PoliticianService service;
    
    @Test
    @DisplayName("Should find politician by ID when exists")
    void shouldFindPoliticianById() {
        // Arrange
        String politicianId = "196401011234";
        Politician expected = new Politician(politicianId, "John", "Doe");
        when(repository.findById(politicianId)).thenReturn(Optional.of(expected));
        
        // Act
        Politician actual = service.findById(politicianId);
        
        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(politicianId);
        assertThat(actual.getFirstName()).isEqualTo("John");
    }
    
    @Test
    @DisplayName("Should throw exception when politician not found")
    void shouldThrowExceptionWhenNotFound() {
        // Arrange
        String politicianId = "999999999999";
        when(repository.findById(politicianId)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThatThrownBy(() -> service.findById(politicianId))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessageContaining("Politician not found");
    }
}
```

### Parameterized Tests

```java
@ParameterizedTest
@CsvSource({
    "196401011234, true",
    "999999999999, true",
    "12345, false",
    "abcdefghijkl, false",
    "'', false"
})
@DisplayName("Should validate Swedish personal ID format")
void shouldValidatePersonalIdFormat(String personalId, boolean expected) {
    assertThat(validator.isValidPersonalId(personalId)).isEqualTo(expected);
}

@ParameterizedTest
@ValueSource(strings = {"password", "12345678", "qwerty", "admin"})
@DisplayName("Should reject common passwords")
void shouldRejectCommonPasswords(String password) {
    assertThat(passwordValidator.isWeak(password)).isTrue();
}
```

### Test Lifecycle

```java
class DatabaseTest {
    
    @BeforeAll
    static void setupClass() {
        // Run once before all tests
        initializeTestDatabase();
    }
    
    @BeforeEach
    void setup() {
        // Run before each test
        clearDatabase();
    }
    
    @AfterEach
    void teardown() {
        // Run after each test
        rollbackTransaction();
    }
    
    @AfterAll
    static void teardownClass() {
        // Run once after all tests
        closeConnections();
    }
}
```

## Mockito Best Practices

### Stubbing

```java
@Test
void shouldReturnCachedData() {
    // Stub method to return specific value
    when(cache.get("key")).thenReturn("value");
    
    // Stub with argument matchers
    when(repository.findByName(anyString())).thenReturn(new ArrayList<>());
    
    // Stub with custom answer
    when(service.process(any())).thenAnswer(invocation -> {
        String arg = invocation.getArgument(0);
        return arg.toUpperCase();
    });
    
    // Stub to throw exception
    when(repository.findById("invalid"))
        .thenThrow(new IllegalArgumentException("Invalid ID"));
}
```

### Verification

```java
@Test
void shouldLogAuditEvent() {
    // Arrange
    Politician politician = new Politician("123", "John", "Doe");
    
    // Act
    service.create(politician);
    
    // Verify method was called once
    verify(auditLogger).log(eq("CREATE_POLITICIAN"), any(Politician.class));
    
    // Verify exact number of calls
    verify(repository, times(1)).save(politician);
    
    // Verify never called
    verify(emailService, never()).sendNotification(any());
    
    // Verify no more interactions
    verifyNoMoreInteractions(auditLogger);
}
```

### Argument Captors

```java
@Test
void shouldSaveWithCorrectTimestamp() {
    ArgumentCaptor<Politician> captor = ArgumentCaptor.forClass(Politician.class);
    
    service.create(new Politician("123", "John", "Doe"));
    
    verify(repository).save(captor.capture());
    
    Politician saved = captor.getValue();
    assertThat(saved.getCreatedAt()).isCloseTo(
        LocalDateTime.now(),
        within(1, ChronoUnit.SECONDS)
    );
}
```

## AssertJ Fluent Assertions

```java
@Test
void shouldHaveCorrectProperties() {
    Politician politician = service.findById("123");
    
    // Fluent assertions
    assertThat(politician)
        .isNotNull()
        .hasFieldOrPropertyWithValue("firstName", "John")
        .hasFieldOrProperty("lastName")
        .extracting("party")
        .isEqualTo("S");
    
    // Collection assertions
    List<Politician> politicians = service.findAll();
    assertThat(politicians)
        .isNotEmpty()
        .hasSize(5)
        .extracting("firstName")
        .contains("John", "Jane")
        .doesNotContain("Invalid");
    
    // Exception assertions
    assertThatThrownBy(() -> service.delete("invalid"))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Politician not found")
        .hasNoCause();
}
```

## Code Coverage

### Target: 80% Line Coverage, 70% Branch Coverage

```xml
<!-- pom.xml -->
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.11</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
        <execution>
            <id>check</id>
            <goals>
                <goal>check</goal>
            </goals>
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

## Test Organization

```
src/test/java/
└── com/hack23/cia/
    ├── service/
    │   ├── impl/
    │   │   ├── PoliticianServiceTest.java
    │   │   └── VotingAnalysisServiceTest.java
    │   └── api/
    │       └── PoliticianServiceIT.java (Integration tests)
    ├── util/
    │   ├── DateUtilsTest.java
    │   └── ValidationUtilsTest.java
    └── testfoundation/
        ├── TestDataFactory.java
        └── AbstractServiceTest.java
```

## References

- JUnit 5: https://junit.org/junit5/
- Mockito: https://site.mockito.org/
- AssertJ: https://assertj.github.io/doc/
- UnitTestPlan.md
