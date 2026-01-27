# Copilot Instructions for Citizen Intelligence Agency

**Last Updated:** 2026-01-27 | **Version:** 2025-SNAPSHOT | **ISMS Alignment:** [Hack23 ISMS v3.2 (2026-01-25)](https://github.com/Hack23/ISMS-PUBLIC)

## Project Overview

The Citizen Intelligence Agency (CIA) is a volunteer-driven, open-source intelligence (OSINT) project providing comprehensive analysis of Swedish political activities. The platform monitors political figures and institutions, delivering financial performance metrics, risk assessment analytics, political trend analysis, and transparency insights.

**Technology Stack:**
- Java 25 (src 21, runtime 25) with Maven 3.9.9
- Spring Framework 5.x (MVC, Security, Data, Integration)
- Vaadin 8 for server-side UI
- Hibernate/JPA for ORM
- PostgreSQL 16 database with SSL/TLS
- Spring Integration for data processing
- Drools for business rules
- JavaMelody for monitoring

## Build and Development

### Prerequisites
- Java 25 JDK
- Maven 3.9.9 or later
- Ant (for application-specific tasks)
- PostgreSQL (for full integration testing, review ../service.data.impl/README-SCHEMA-MAINTENANCE.md for task related to any database changes)

### Build Commands

**Maven** (multi-module project):
```bash
# Clean and install all modules
mvn clean install

# Build without tests (faster)
mvn clean install -DskipTests

# Full build with all profiles (CI/CD)
mvn clean install -Prelease-site,all-modules -DskipTests

# Run tests only
mvn test

# Run tests with coverage
mvn clean test jacoco:report

# Security dependency check
mvn dependency-check:check

# Generate site documentation
mvn site
```

**Ant** (application-specific tasks from citizen-intelligence-agency/build.xml):
```bash
# Clean install without tests (fast)
ant clean-install-notest

# Run unit tests
ant unit-test

# Start the application
ant start

# Check for dependency updates
ant check-updates

# Generate site documentation
ant site-cia
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
Per [Secure Development Policy v2.1](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md):
- **Minimum 80% line coverage** across all modules
- **Minimum 70% branch coverage** across all modules
- Write unit tests for all new functionality
- Place tests in `src/test/java` following the same package structure as source code
- Use JUnit for unit tests
- Follow existing test patterns in the codebase

### Code Quality Tools
- **SonarCloud**: Code quality analysis and technical debt tracking
- **OWASP Dependency Check**: Vulnerable dependency scanning
- **CodeQL**: Security vulnerability detection and SAST
- **JaCoCo**: Code coverage reporting and analysis (target: 80% line, 70% branch per [Secure Development Policy v2.1](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md))
- **JavaMelody**: Production monitoring and performance metrics
- **OpenSSF Scorecard**: Supply chain security assessment

### Running Quality Checks
```bash
# Run with coverage report
mvn clean test jacoco:report

# Generate dependency security scan
mvn dependency-check:check

# Full build with all checks (CI/CD)
mvn clean install -Prelease-site,all-modules
```

### ISMS Compliance (2026)

This project aligns with **Hack23 ISMS v3.2 (2026-01-25)** standards:
- **ISO 27001:2022** - All Annex A controls implemented
- **NIST CSF 2.0** - Complete framework alignment  
- **CIS Controls v8.1** - Critical security controls
- **GDPR** - Swedish data protection compliance
- **NIS2 Directive** - EU cybersecurity requirements
- **EU Cyber Resilience Act** - Product security conformity

**Key ISMS Resources:**
- [Secure Development Policy v2.1](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) - Security-integrated SDLC, 80% line coverage, 70% branch coverage requirements
- [Information Security Policy v2.0](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) - Overall security governance framework
- [Cryptography Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Cryptography_Policy.md) - TLS 1.3, AES-256 encryption standards
- [Access Control Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Access_Control_Policy.md) - Zero-trust identity and authorization
- [Data Classification Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Data_Classification_Policy.md) - Information handling requirements
- [Privacy Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Privacy_Policy.md) - GDPR-compliant privacy framework
- [Incident Response Plan](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Incident_Response_Plan.md) - Security event handling
- [Open Source Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Open_Source_Policy.md) - Open source business model governance
- [Compliance Checklist](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Compliance_Checklist.md) - Multi-framework compliance tracking
- [Risk Register](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Risk_Register.md) - Risk identification and treatment
- [Vulnerability Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md) - Systematic security testing

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
   - Follow [Cryptography Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Cryptography_Policy.md)

2. **Input Validation**
   - Validate all user inputs per OWASP guidelines
   - Use parameterized queries to prevent SQL injection
   - Sanitize data before rendering in UI (XSS prevention)
   - Follow [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)

3. **Authentication and Authorization**
   - Use Spring Security for access control
   - Follow principle of least privilege
   - Never bypass security checks
   - Implement proper session management

4. **Dependency Management**
   - Keep dependencies up to date
   - Review security advisories for dependencies
   - **ALWAYS** run `mvn dependency-check:check` before adding dependencies
   - Monitor OpenSSF Scorecard: [![CIA](https://api.securityscorecards.dev/projects/github.com/Hack23/cia/badge)](https://scorecard.dev/viewer/?uri=github.com/Hack23/cia)

5. **Data Protection**
   - Handle personal data according to GDPR
   - Use encryption for sensitive data (TLS 1.3, AES-256)
   - Follow the project's [Security Policy](../SECURITY.md)
   - Align with [Data Protection Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Data_Protection_Policy.md)

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
All PRs must meet [Secure Development Policy v2.1](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) requirements:
- Pass all automated tests
- Meet minimum 80% line coverage, 70% branch coverage
- Pass CodeQL security scan
- Pass dependency security checks (OWASP Dependency Check)
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

### Quality Standards Summary

**Before Starting Work**:
1. Run `mvn clean install` to verify current build status
2. Review existing test coverage: `mvn clean test jacoco:report`
3. Check for security issues: `mvn dependency-check:check`
4. Review SonarCloud dashboard for code quality metrics

**During Development**:
1. Write tests for all new functionality (JUnit 5)
2. Maintain or improve code coverage (JaCoCo)
3. Follow existing code patterns and Spring conventions
4. Apply security best practices (input validation, parameterized queries)
5. Document public APIs with JavaDoc

**Before Submitting**:
1. Run full test suite: `mvn clean test`
2. Verify coverage: `mvn jacoco:report` (check `target/site/jacoco/`)
3. Security scan: `mvn dependency-check:check`
4. Full build: `mvn clean install -Prelease-site,all-modules`
5. Review SonarCloud results

**Key Metrics to Maintain** (per [Secure Development Policy v2.1](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)):
- Test Coverage: Minimum 80% line coverage, 70% branch coverage
- Security: Zero critical/high vulnerabilities
- Code Quality: SonarCloud Quality Gate passing
- Build: All CI/CD checks passing

### When Making Changes:
1. **Understand the context**: Review related code and architecture before making changes
2. **Minimal changes**: Make the smallest change necessary to achieve the goal
3. **Test thoroughly**: Always run tests and verify functionality
4. **Security first**: Consider security implications of all changes
5. **Follow patterns**: Use existing code patterns and conventions in the project
6. **Document when needed**: Update documentation for significant changes
7. **Ask questions**: If requirements are unclear, ask for clarification rather than making assumptions

This is a mature, security-conscious project with strong testing and quality standards. Prioritize code quality, security, and maintainability over rapid development.
