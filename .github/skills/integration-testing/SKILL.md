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
@SpringBootTest
class DatabaseIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
        .withDatabaseName("cia_test")
        .withUsername("test")
        .withPassword("test");
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
    
    @Test
    void shouldConnectToDatabase() {
        assertThat(postgres.isRunning()).isTrue();
    }
}
```

## Security Integration Tests

```java
@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldAllowAdminAccess() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
            .andExpect(status().isOk());
    }
    
    @Test
    void shouldDenyUnauthenticatedAccess() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
            .andExpect(status().isUnauthorized());
    }
}
```

## References

- Spring Boot Testing: https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing
- TestContainers: https://www.testcontainers.org/
