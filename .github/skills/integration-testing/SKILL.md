---
name: integration-testing
description: Implement Spring Framework integration tests with TestContainers, Spring Test, and database fixtures
license: Apache-2.0
---

# Integration Testing Skill

## Purpose

Test component interactions, database operations, and external API integrations using Spring testing framework with TestContainers.

## When to Use

- ✅ Testing repository layer with real database
- ✅ Testing REST API endpoints (if applicable)
- ✅ Testing Spring Security configuration
- ✅ Testing external API integrations

## Spring Integration Test Patterns

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PoliticianIntegrationTest {
    
    @Autowired
    private PoliticianRepository repository;
    
    @Test
    void shouldCreatePoliticianViaApi() {
        // Arrange
        PoliticianRequest request = new PoliticianRequest("John", "Doe", "S");
        
        // Act
        ResponseEntity<Politician> response = restTemplate.postForEntity(
            "/api/politicians",
            request,
            Politician.class
        );
        
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        
        // Verify in database
        Politician saved = repository.findById(response.getBody().getId()).orElseThrow();
        assertThat(saved.getFirstName()).isEqualTo("John");
    }
}
```

## TestContainers for PostgreSQL

```java
@Testcontainers
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
public class DatabaseIntegrationTest {
    
    @ClassRule
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
        .withDatabaseName("cia_test")
        .withUsername("test")
        .withPassword("test");
    
    @BeforeClass
    public static void setupTestDatabase() {
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }
    
    @Test
    public void shouldConnectToDatabase() {
        assertThat(postgres.isRunning()).isTrue();
    }
}
```

## Security Integration Tests

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContext.class, SecurityConfig.class})
@WebAppConfiguration
public class SecurityIntegrationTest {
    
    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldAllowAdminAccess() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
            .andExpect(status().isOk());
    }
    
    @Test
    public void shouldDenyUnauthenticatedAccess() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
            .andExpect(status().isUnauthorized());
    }
}
```

## References

- Spring Framework Testing: https://docs.spring.io/spring-framework/reference/testing.html
- TestContainers: https://www.testcontainers.org/
