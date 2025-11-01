# GitHub Copilot Instructions for Citizen Intelligence Agency

## Project Overview

The Citizen Intelligence Agency (CIA) is an independent, volunteer-driven OSINT platform that monitors Swedish political activity. It's a comprehensive Java-based web application built with Spring Framework and Vaadin that provides analysis of political activities, financial metrics, and transparency insights.

## Tech Stack

### Core Technologies
- **Language**: Java 25
- **Build Tool**: Maven 3.9.9+
- **Web Framework**: Vaadin (Full-stack framework)
- **Backend Framework**: Spring Framework (Spring MVC, Spring Security, Spring Data, Spring Integration)
- **ORM**: Hibernate/JPA
- **Database**: PostgreSQL (production), H2 (testing)
- **Caching**: Ehcache
- **Integration**: Apache Camel
- **Server**: JBoss

### Testing & Quality
- **Testing Frameworks**: JUnit, Mockito
- **Security Scanning**: CodeQL, Checkov, Snyk
- **CI/CD**: GitHub Actions
- **Code Quality**: SonarCloud

## Project Structure

This is a multi-module Maven project organized as follows:

```
cia/
├── parent-pom/                          # Parent POM with shared configuration
├── testfoundation/                      # Shared test utilities and base classes
├── model.*/                             # Data model modules (JPA entities)
│   ├── model.common.api/               # Common model API
│   ├── model.common.impl/              # Common model implementation
│   ├── model.external.riksdagen*/      # Swedish Parliament data models
│   ├── model.external.val*/            # Swedish Election Authority data models
│   ├── model.external.worldbank*/      # World Bank data models
│   └── model.internal.application*/    # Internal application data models
├── service.*/                           # Service layer modules
│   ├── service.api/                    # Service API
│   ├── service.data.*/                 # Data access services
│   ├── service.component.agent.*/      # Agent services
│   └── service.external.*/             # External data integration services
├── citizen-intelligence-agency/         # Main web application
├── web-widgets/                         # UI components
├── cia-dist-*/                         # Distribution packages
└── .github/                            # GitHub configuration
```

## Coding Standards

### Java Code Style

1. **Copyright Header**: All Java files must include the Apache License 2.0 header:
```java
/*
 * Copyright 2010 -2025 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
```

2. **Package Structure**: Follow the existing package naming convention:
   - `com.hack23.cia.model.*` - Data models
   - `com.hack23.cia.service.*` - Business logic and services
   - `com.hack23.cia.web.*` - Web/UI layer

3. **Class Documentation**: Use JavaDoc for all public classes and methods:
```java
/**
 * The Class ExampleClass.
 */
public final class ExampleClass {
    
    /** The Constant EXAMPLE_CONSTANT. */
    private static final String EXAMPLE_CONSTANT = "example";
    
    /**
     * Example method.
     *
     * @param param the param
     * @return the result
     */
    public String exampleMethod(final String param) {
        // Implementation
    }
}
```

4. **Code Formatting**:
   - Use tabs for indentation (consistent with existing code)
   - Place opening braces on the same line
   - Use `final` modifier for method parameters and local variables where appropriate
   - Prefer immutability

5. **Naming Conventions**:
   - Constants: `UPPER_SNAKE_CASE`
   - Classes: `PascalCase`
   - Methods/Variables: `camelCase`
   - Test classes: End with `Test` (e.g., `ModelSanityTest`)

### Testing Standards

1. **Test Location**: Tests should be in `src/test/java` mirroring the structure of `src/main/java`

2. **Test Base Class**: Extend `AbstractUnitTest` from `testfoundation` module:
```java
public final class ExampleTest extends AbstractUnitTest {
    @Test
    public void testExample() {
        // Test implementation
    }
}
```

3. **Test Naming**: Use descriptive method names that explain what is being tested

4. **Coverage**: Write tests for new functionality; maintain existing test coverage

5. **Model Testing**: Each model package should have a `ModelSanityTest` that validates all classes in the package

## Security Guidelines

### Critical Security Requirements

1. **Input Validation**: Always validate and sanitize user inputs to prevent injection attacks

2. **Authentication & Authorization**: 
   - Use Spring Security for authentication
   - Implement proper authorization checks
   - Never bypass security controls

3. **Sensitive Data**:
   - Never commit secrets, passwords, or API keys
   - Use environment variables or secure configuration management
   - Follow the project's security policy in `SECURITY.md`

4. **Dependencies**:
   - Keep dependencies up to date
   - Review dependency advisories (Snyk, Dependabot)
   - Use only vetted libraries

5. **SQL Injection Prevention**:
   - Always use parameterized queries
   - Use JPA/Hibernate properly to avoid SQL injection

6. **XSS Prevention**:
   - Properly escape outputs in Vaadin components
   - Validate and sanitize HTML content

7. **Security Scanning**:
   - All code is scanned with CodeQL
   - CloudFormation templates are scanned with Checkov
   - Address security findings before merging

## Build & Development

### Building the Project

```bash
# Full build with all modules
mvn clean install -Prelease-site,all-modules

# Skip tests during build (for faster builds)
mvn clean install -Dmaven.test.skip=true

# Run tests
mvn test

# Build specific module
cd <module-name>
mvn clean install
```

### Prerequisites

- **JDK**: Java 25 (Temurin distribution recommended)
- **Maven**: 3.9.9 or later
- **Database**: PostgreSQL (for development)
- **Build Tools** (for Debian packaging): graphviz, build-essential, fakeroot, devscripts, debhelper, dh-make

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ExampleTest

# Run tests for a specific module
cd <module-name>
mvn test
```

## Data Integration

The application integrates with several external data sources:

1. **Swedish Parliament API** (`riksdagen`) - Parliamentary data, votes, documents
2. **Swedish Election Authority** (`val`) - Election results, party information
3. **World Bank Open Data** (`worldbank`) - Economic indicators
4. **Swedish Financial Management Authority** (`esv`) - Government finances

When working with data integration:
- Use existing service interfaces in `service.external.*` modules
- Follow the established patterns for data fetching and caching
- Handle external API failures gracefully

## UI Development with Vaadin

1. **Component Structure**: UI components are in `web-widgets` and `citizen-intelligence-agency/src/main/java/com/hack23/cia/web`

2. **Vaadin Best Practices**:
   - Use Vaadin's component model correctly
   - Implement proper event handling
   - Ensure thread safety in UI updates

3. **Admin Operations**: Admin UI is in `com.hack23.cia.web.impl.ui.application.views.admin`

## Version Control

### Commit Messages

Write clear, descriptive commit messages following these guidelines:
- Use present tense ("Add feature" not "Added feature")
- First line should be a concise summary (50 chars or less)
- Add detailed description if needed after a blank line
- Reference issue numbers when applicable

### Branch Strategy

- `master` - Main development branch
- Feature branches should be created from `master`
- Follow the pull request process outlined in `CONTRIBUTING.md`

## Documentation

1. **Architecture**: See `ARCHITECTURE.md` for C4 model diagrams
2. **Data Model**: See `DATA_MODEL.md` for entity relationships
3. **Workflows**: See `WORKFLOWS.md` for CI/CD processes
4. **Security**: See `SECURITY.md` for security policy
5. **Threat Model**: See `THREAT_MODEL.md` for security analysis

When adding new features:
- Update relevant documentation
- Add JavaDoc for public APIs
- Update README.md if user-facing functionality changes

## Common Patterns

### Service Layer Pattern

```java
@Service
public class ExampleServiceImpl implements ExampleService {
    
    @Autowired
    private ExampleRepository repository;
    
    @Override
    @Transactional(readOnly = true)
    public Example findById(final Long id) {
        return repository.findById(id).orElse(null);
    }
}
```

### Repository Pattern

```java
@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
    List<Example> findByProperty(String property);
}
```

### Test Pattern

```java
public final class ExampleServiceTest extends AbstractUnitTest {
    
    @Mock
    private ExampleRepository repository;
    
    @InjectMocks
    private ExampleServiceImpl service;
    
    @Test
    public void testFindById() {
        // Arrange
        final Long id = 1L;
        final Example expected = new Example();
        when(repository.findById(id)).thenReturn(Optional.of(expected));
        
        // Act
        final Example actual = service.findById(id);
        
        // Assert
        assertEquals(expected, actual);
    }
}
```

## CI/CD Pipeline

### GitHub Actions Workflows

1. **CodeQL Analysis** (`.github/workflows/codeql-analysis.yml`):
   - Runs on push to master and PRs
   - Performs security scanning with CodeQL
   - Runs Checkov for CloudFormation templates
   - Builds the project with Maven

2. **Release** (`.github/workflows/release.yml`):
   - Automated release process
   - Version management

3. **Dependency Review** (`.github/workflows/dependency-review.yml`):
   - Checks for vulnerable dependencies

### Build Profile

The project uses Maven profiles:
- `release-site` - Generate site documentation
- `all-modules` - Build all modules

## Best Practices for AI-Assisted Development

1. **Understand Context**: Review existing code patterns before generating new code
2. **Follow Conventions**: Match the project's established coding style
3. **Security First**: Always consider security implications
4. **Test Coverage**: Generate tests alongside implementation code
5. **Documentation**: Include JavaDoc and update relevant documentation
6. **Small Commits**: Make focused, incremental changes
7. **Review Dependencies**: Verify any new dependencies align with project standards
8. **Performance**: Consider caching and database query optimization
9. **Error Handling**: Implement proper exception handling and logging
10. **Backwards Compatibility**: Maintain compatibility with existing APIs

## Troubleshooting

### Common Build Issues

1. **Maven Dependencies**: Run `mvn clean install` in parent directory first
2. **Database Issues**: Ensure PostgreSQL is running for integration tests
3. **Memory Issues**: Increase Maven heap size: `export MAVEN_OPTS="-Xmx2048m"`
4. **Test Failures**: Check if tests depend on external services being available

## Resources

- **GitHub Repository**: https://github.com/Hack23/cia
- **Project Website**: https://hack23.com
- **Documentation**: See markdown files in repository root
- **Issues**: https://github.com/Hack23/cia/issues
- **Pull Requests**: https://github.com/Hack23/cia/pulls

## License

All code contributions must be compatible with the Apache License 2.0. Ensure copyright headers are included in new files.

---

*Last Updated: 2025*
*For questions or clarifications, refer to CONTRIBUTING.md or open an issue.*
