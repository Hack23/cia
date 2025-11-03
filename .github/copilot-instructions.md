# Copilot Instructions for Citizen Intelligence Agency

## Project Overview

The Citizen Intelligence Agency (CIA) is a volunteer-driven, open-source intelligence (OSINT) project providing comprehensive analysis of Swedish political activities. The platform monitors political figures and institutions, delivering financial performance metrics, risk assessment analytics, political trend analysis, and transparency insights.

**Technology Stack:**
- Java 25 (src 21) with Maven build system
- Spring Framework 5.x (MVC, Security, Data)
- Vaadin for UI
- Hibernate/JPA for data access
- PostgreSQL database
- Spring Integration for data processing

## Build and Development

### Prerequisites
- Java 25 JDK
- Maven 3.9.9 or later
- PostgreSQL (for full integration testing)

### Build Commands
```bash
# Clean and install all modules
mvn clean install

# Build without tests (faster)
mvn clean install -DskipTests

# Run tests only
mvn test

# Generate site documentation
mvn site
```

### Project Structure
This is a multi-module Maven project with the following key modules:
- `parent-pom/` - Parent POM with common configurations
- `citizen-intelligence-agency/` - Main web application
- `service.*/` - Service layer modules
- `model.*/` - Data model modules (internal and external)
- `testfoundation/` - Testing utilities
- `cia-dist-deb/` - Debian package distribution
- `cia-dist-cloudformation/` - AWS CloudFormation templates

## Code Quality and Testing

### Testing Requirements
- Write unit tests for all new functionality
- Maintain test coverage above existing levels
- Place tests in `src/test/java` following the same package structure as source code
- Use JUnit for unit tests
- Follow existing test patterns in the codebase

### Code Quality Tools
- **SonarCloud**: Used for code quality analysis
- **OWASP Dependency Check**: Scans for vulnerable dependencies
- **CodeQL**: Security vulnerability scanning
- **JaCoCo**: Code coverage reporting

### Running Quality Checks
```bash
# Run with coverage
mvn clean test jacoco:report

# Generate dependency check report
mvn dependency-check:check
```

## Coding Standards

### Java Code Style
- Use Java 21 (src 21, runtime 25) features where appropriate
- Follow existing code formatting in the project
- Use meaningful variable and method names
- Add JavaDoc for public APIs
- Avoid adding comments unless necessary for complex logic
- Keep methods focused and concise

### Spring Framework Conventions
- Use dependency injection via constructor injection where possible
- Annotate services with `@Service`, repositories with `@Repository`, controllers with `@Controller`
- Follow Spring best practices for transaction management
- Use `@Transactional` appropriately for database operations

### JPA/Hibernate Guidelines
- Entity classes should be in `model.*` packages
- Use proper JPA annotations (`@Entity`, `@Table`, `@Column`, etc.)
- Define relationships clearly with appropriate fetch types
- Avoid N+1 query problems

## Security Guidelines

### Critical Security Rules
1. **Never commit secrets, API keys, or credentials**
   - Use environment variables or external configuration
   - Check `.gitignore` to ensure sensitive files are excluded

2. **Input Validation**
   - Validate all user inputs
   - Use parameterized queries to prevent SQL injection
   - Sanitize data before rendering in UI (XSS prevention)

3. **Authentication and Authorization**
   - Use Spring Security for access control
   - Follow principle of least privilege
   - Never bypass security checks

4. **Dependency Management**
   - Keep dependencies up to date
   - Review security advisories for dependencies
   - Use OWASP Dependency Check before adding new dependencies

5. **Data Protection**
   - Handle personal data according to GDPR
   - Use encryption for sensitive data
   - Follow the project's [Security Policy](../SECURITY.md)

## Pull Request Guidelines

### Before Submitting a PR
1. Ensure all tests pass: `mvn test`
2. Verify the build succeeds: `mvn clean install`
3. Check for code quality issues
4. Update relevant documentation if needed
5. Follow the PR template at `.github/pull_request_template.md`

### PR Description Requirements
- Clearly describe what the PR changes and why
- Reference related issues using `#issue-number`
- Include any breaking changes
- List any new dependencies added
- Note any documentation updates needed

### Commit Messages
- Use clear, descriptive commit messages
- Follow format: `<type>: <description>`
- Types: feat, fix, docs, style, refactor, test, chore
- Example: `feat: add politician risk score calculation`

## Documentation

### When to Update Documentation
- New features require documentation updates in README.md
- Architecture changes need updates in ARCHITECTURE.md
- API changes should be reflected in JavaDoc
- Security-related changes may require SECURITY.md updates

### Documentation Standards
- Use clear, concise language
- Include code examples where helpful
- Keep technical documentation up to date with code changes
- Use Markdown for all documentation files

## External Data Sources

The project integrates with several external APIs:
- **Swedish Parliament (Riksdagen) API**: Parliamentary data
- **Swedish Election Authority**: Election and party data
- **World Bank Open Data**: Economic indicators
- **Swedish Financial Management Authority**: Government finances

When working with external data integrations:
- Handle API errors gracefully
- Implement retry logic for transient failures
- Cache data appropriately to reduce API calls
- Document any new data source integrations

## Common Tasks

### Adding a New Feature
1. Review existing architecture in [ARCHITECTURE.md](../ARCHITECTURE.md)
2. Create an issue describing the feature
3. Implement feature with appropriate tests
4. Update documentation
5. Submit PR with clear description

### Fixing a Bug
1. Write a failing test that reproduces the bug
2. Fix the bug
3. Ensure the test now passes
4. Verify no regression in existing tests
5. Submit PR referencing the bug issue

### Adding a Dependency
1. Check if it's really needed
2. Verify license compatibility (Apache 2.0)
3. Run security scan: `mvn dependency-check:check`
4. Add to appropriate POM file
5. Document why it's needed in PR

## Continuous Integration

### GitHub Actions Workflows
- **Verify and Release**: Main CI/CD pipeline
- **CodeQL Analysis**: Security scanning
- **Dependency Review**: Checks for vulnerable dependencies
- **ZAP Scan**: Security testing
- **Scorecards**: Security posture assessment

### Build Requirements
All PRs must:
- Pass all automated tests
- Pass CodeQL security scan
- Pass dependency security checks
- Meet code coverage requirements
- Have no critical SonarCloud issues

## Resources

### Project Documentation
- [README.md](../README.md) - Project overview and setup
- [CONTRIBUTING.md](../CONTRIBUTING.md) - Contribution guidelines
- [ARCHITECTURE.md](../ARCHITECTURE.md) - System architecture
- [SECURITY.md](../SECURITY.md) - Security policy and reporting
- [CODE_OF_CONDUCT.md](../CODE_OF_CONDUCT.md) - Community standards

### Development References
- [Data Model](../DATA_MODEL.md) - Database schema
- [Flowcharts](../FLOWCHART.md) - Data processing workflows
- [Mindmap](../MINDMAP.md) - Component relationships
- [SWOT Analysis](../SWOT.md) - Strategic assessment
- [Threat Model](../THREAT_MODEL.md) - Security analysis

## Notes for AI Coding Assistants

When making changes:
1. **Understand the context**: Review related code and architecture before making changes
2. **Minimal changes**: Make the smallest change necessary to achieve the goal
3. **Test thoroughly**: Always run tests and verify functionality
4. **Security first**: Consider security implications of all changes
5. **Follow patterns**: Use existing code patterns and conventions in the project
6. **Document when needed**: Update documentation for significant changes
7. **Ask questions**: If requirements are unclear, ask for clarification rather than making assumptions

This is a mature, security-conscious project with strong testing and quality standards. Prioritize code quality, security, and maintainability over rapid development.
