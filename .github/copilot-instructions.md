# Copilot Instructions for Citizen Intelligence Agency

## Required Context - Read First

**ALWAYS read these files at the start of EVERY task:**

1. **[README.md](../README.md)** - Project overview, features, and documentation links
2. **[.github/workflows/copilot-setup-steps.yml](workflows/copilot-setup-steps.yml)** - Build environment, tools, and workflow permissions
3. **[.github/copilot-mcp-config.json](copilot-mcp-config.json)** - MCP server configuration and available tools
4. **[.github/skills/](skills/)** - 24 strategic skills for security, testing, and architecture
5. **[.github/agents/](agents/)** - 6 specialized agents for different domains

**Never skip reading these files. They contain critical context that prevents mistakes.**

## Project Overview

The Citizen Intelligence Agency (CIA) is a volunteer-driven, open-source intelligence (OSINT) project providing comprehensive analysis of Swedish political activities. The platform monitors political figures and institutions, delivering financial performance metrics, risk assessment analytics, political trend analysis, and transparency insights.

**Technology Stack:**
- Java 25 (src 21) with Maven build system
- Spring Framework 5.x (MVC, Security, Data)
- Vaadin for UI
- Hibernate/JPA for data access
- PostgreSQL database
- Spring Integration for data processing

**Available Resources:**
- **[6 Specialized Agents](agents/)** - Task management, stack expertise, UI/UX, intelligence, business, marketing
- **[24 Skills Library](skills/)** - Security, ISMS compliance, testing, architecture, CI/CD, community

## Build and Development

### Prerequisites
- Java 25 JDK
- Maven 3.9.9 or later
- PostgreSQL (for full integration testing, review ../service.data.impl/README-SCHEMA-MAINTENANCE.md for task related to any database changes)

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

## Mandatory Rules for All Work

### Rule 1: Always Read Context First
**BEFORE starting any task, read:**
- README.md - project overview
- .github/workflows/copilot-setup-steps.yml - environment setup
- .github/copilot-mcp-config.json - available tools
- Relevant skills from .github/skills/ directory
- Relevant agent from .github/agents/ directory

### Rule 2: Make Minimal, Surgical Changes
- Change only what's necessary to fix the issue
- Don't refactor unrelated code
- Don't fix unrelated bugs or broken tests
- Keep modifications focused and small
- Review impact before committing

### Rule 3: Never Create New Markdown Files Unless Explicitly Asked
- **Do NOT create** planning documents, notes, tracking files, or summaries
- **Do NOT create** new .md files in root or subdirectories without explicit request
- Work in memory, not in new files
- Only create .md files when user specifically asks for that exact file by name or path

### Rule 4: Run Comprehensive Checks Before Committing
**Before every commit, ALWAYS:**
1. Validate syntax (JSON, XML, YAML, Java)
2. Check for compilation errors: `mvn clean compile`
3. Run relevant tests: `mvn test -Dtest=YourTest`
4. Verify no security issues with changed files
5. Check file permissions and paths
6. Review git diff to ensure only intended changes
7. Verify no secrets, credentials, or sensitive data

### Rule 5: Ask Fewer Questions, Complete More Work
**Act decisively using these frameworks:**

**Security Decisions:**
- Default: Deny access, require authentication
- Validation: Whitelist approach, validate all inputs
- Encryption: Always for sensitive data
- Logging: Log security events, never sensitive data

**Code Quality Decisions:**
- Coverage: Minimum 80% line, 70% branch
- Complexity: Cyclomatic complexity < 10
- Duplication: < 3% code duplication
- Dependencies: Latest stable, no critical CVEs

**Architecture Decisions:**
- Layering: Respect existing architecture
- Patterns: Follow established project patterns
- Dependencies: Minimize coupling, maximize cohesion
- Performance: Measure before optimizing

**Only escalate truly unique scenarios not covered by these frameworks.**

### Rule 6: Use Skills and Agents
**Before implementing, check relevant skills:**
- [secure-code-review](skills/secure-code-review/) - OWASP Top 10, security patterns
- [unit-testing-patterns](skills/unit-testing-patterns/) - JUnit 5, Mockito, 80% coverage
- [spring-framework-patterns](skills/spring-framework-patterns/) - DI, transactions, AOP
- [jpa-hibernate-optimization](skills/jpa-hibernate-optimization/) - Entity design, N+1 prevention
- [input-validation](skills/input-validation/) - XSS, SQL injection prevention
- See [full skills catalog](skills/README.md)

**Delegate to specialized agents when appropriate:**
- [task-agent](agents/task-agent.md) - GitHub issue management, ISMS compliance
- [stack-specialist](agents/stack-specialist.md) - Java, Spring, PostgreSQL expertise
- [ui-enhancement-specialist](agents/ui-enhancement-specialist.md) - Vaadin, accessibility
- See [full agent catalog](agents/README.md)

### Rule 7: Security is Non-Negotiable
**Every change MUST:**
- Pass OWASP Dependency Check (no critical/high vulnerabilities)
- Pass CodeQL security scanning
- Include input validation for all user inputs
- Use parameterized queries (no SQL injection)
- Encode output properly (no XSS vulnerabilities)
- Never commit secrets, API keys, or credentials
- Update security documentation if needed

**Reference:** [Hack23 Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)

### Rule 8: Follow ISMS Compliance Requirements
**All changes MUST align with:**
- **ISO 27001:2022** - Annex A controls
- **NIST CSF 2.0** - Framework functions
- **CIS Controls v8** - Implementation groups
- **GDPR** - Data protection requirements

**Required documentation updates:**
- Update SECURITY_ARCHITECTURE.md if security changes
- Update THREAT_MODEL.md if new threats identified
- Update ARCHITECTURE.md if design changes
- Maintain 80% line coverage, 70% branch coverage

**See:** [iso-27001-controls skill](skills/iso-27001-controls/) for control verification

### Rule 9: Test Everything
**Testing is mandatory:**
- Write unit tests for all new functionality
- Maintain or improve test coverage
- Run tests before committing: `mvn test`
- Follow patterns in existing tests
- Use JUnit 5, Mockito, appropriate test frameworks

**Coverage Requirements:**
- Minimum 80% line coverage
- Minimum 70% branch coverage
- No critical SonarCloud issues

**Reference:** [unit-testing-patterns skill](skills/unit-testing-patterns/)

### Rule 10: Commit Messages Must Be Clear
**Format:** `<type>: <description>`

**Types:**
- `feat:` New feature
- `fix:` Bug fix
- `docs:` Documentation only
- `style:` Formatting (no code change)
- `refactor:` Code restructuring
- `test:` Adding tests
- `chore:` Build process, dependencies

**Example:** `fix: correct SQL injection vulnerability in politician search`

## Working with External APIs

When integrating with external data sources:
- Handle API errors gracefully with proper exception handling
- Implement retry logic for transient failures (3 retries with exponential backoff)
- Cache data appropriately to reduce API calls
- Document any new data source integrations in README.md
- Use circuit breaker pattern for unreliable services

## Decision Framework Summary

**When uncertain, use this hierarchy:**
1. Check relevant skill in `.github/skills/` directory
2. Review similar code patterns in the repository
3. Consult project documentation (ARCHITECTURE.md, SECURITY_ARCHITECTURE.md)
4. Apply security-by-design principles (deny by default, validate input, encrypt data)
5. Follow ISMS requirements (ISO 27001, NIST CSF, CIS Controls)
6. Only then ask for clarification if truly unique scenario

**This is a mature, security-conscious project. Prioritize code quality, security, and maintainability. Act decisively within established frameworks. Complete work thoroughly before committing.**
