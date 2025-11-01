# GitHub Copilot Instructions for Citizen Intelligence Agency

## Repository Overview

The Citizen Intelligence Agency (CIA) is an independent, volunteer-driven OSINT platform that monitors Swedish political activity. It provides comprehensive analysis of political activities in Sweden through financial performance metrics, risk assessment analytics, political trend analysis, politician ranking systems, and transparency insights.

**Key Information:**
- **Project Type**: Data Analytics / Political Intelligence Platform
- **Primary Language**: Java
- **Build Tool**: Maven
- **Framework**: Spring Framework with Vaadin UI
- **Database**: PostgreSQL
- **Runtime**: JDK 21, 22, 23, 24, 25 (LTS: 21, 25)

## Technology Stack

### Core Technologies
- **Framework**: Spring Framework, Spring Security, Spring JMS
- **ORM/Database**: Hibernate, JPA, PostgreSQL, JDBC
- **Transaction Management**: Narayana (integrated with Spring JpaTransactionManager)
- **Business Rules**: Drools
- **Messaging**: ActiveMQ Artemis
- **UI**: Vaadin (server-driven architecture)
- **Security**: Spring Security, Bouncy Castle
- **Testing**: JUnit, Mockito, Spring Test, Selenium WebDriver
- **Build**: Maven

### AWS Infrastructure
When working with AWS-related code, note the following services are used:
- VPC, EC2, RDS (PostgreSQL), S3, CloudWatch
- Application Load Balancer (ALB), AWS WAF
- Secrets Manager, KMS
- Route 53, Certificate Manager (ACM)
- Systems Manager (SSM)

## Project Structure

The project is organized as a multi-module Maven project:

```
cia/
├── parent-pom/                          # Parent POM with dependency management
├── model.*/                             # Data models (external and internal)
│   ├── model.external.riksdagen.*/     # Swedish Parliament data models
│   ├── model.external.worldbank.*/     # World Bank data models
│   ├── model.external.val.*/           # Swedish Election Authority models
│   └── model.internal.application*/    # Internal application models
├── service.*/                           # Service layer modules
│   ├── service.api/                    # Service API interfaces
│   ├── service.impl/                   # Service implementations
│   ├── service.data.*/                 # Data access services
│   └── service.external.*/             # External data source integrations
├── citizen-intelligence-agency/         # Main web application
├── cia-dist-deb/                       # Debian package distribution
├── cia-dist-cloudformation/            # AWS CloudFormation templates
└── testfoundation/                     # Test utilities and base classes
```

## Build and Test Instructions

### Building the Project

**Standard build:**
```bash
mvn clean install
```

**Build with all modules:**
```bash
mvn clean install -Pall-modules
```

**Build without tests (for faster iteration):**
```bash
mvn clean install -DskipTests
```

**Build with site documentation:**
```bash
mvn clean install -Prelease-site,all-modules
```

### Running Tests

**Run all tests:**
```bash
mvn test
```

**Run specific test:**
```bash
mvn test -Dtest=ClassName#methodName
```

**Skip integration tests:**
```bash
mvn test -Dtest='!**ITest*'
```

**Important Test Patterns:**
- Unit tests: `*Test.java`
- Integration tests: `*ITest.java`
- Documentation tests: `*DocumentationTest.java`

### Prerequisites for Local Development

1. **JDK**: JDK 21 or later (JDK 25 recommended)
2. **Maven**: 3.9.9 or later
3. **PostgreSQL**: Version 16 with SSL, prepared transactions enabled
4. **Build tools**: graphviz (for documentation generation)

### Database Configuration

The project requires PostgreSQL 16 with specific configuration:
- `max_prepared_transactions = 100`
- SSL enabled
- Extensions: `pg_stat_statements`, `pgaudit`, `pgcrypto`

Default development credentials (change in production):
- Database: `cia_dev`
- Username: `eris`
- Password: `discord`

## Code Style and Conventions

### General Principles
1. **Minimal changes**: Make the smallest possible changes to achieve the goal
2. **Test coverage**: Add or update tests for any new functionality
3. **Documentation**: Update documentation if it relates directly to your changes
4. **Security**: Always validate changes don't introduce security vulnerabilities

### Java Coding Standards
- Follow standard Java naming conventions
- Use meaningful variable and method names
- Keep methods focused and concise
- Add JavaDoc for public APIs
- Use proper exception handling

### Testing Standards
- Write unit tests using JUnit
- Use Mockito for mocking dependencies
- Integration tests should end with `ITest.java`
- Ensure tests are idempotent and can run in any order
- Use Spring Test framework for integration tests

### Common Patterns in the Codebase

1. **Service Layer**: Services are Spring-managed beans with `@Service` annotation
2. **Data Access**: Use JPA repositories with Hibernate
3. **Transaction Management**: Transactions are managed via `@Transactional` annotation
4. **External Data Integration**: Dedicated service modules for each external data source
5. **UI Components**: Vaadin components in the web module

## Common Tasks

### Adding a New Service
1. Define interface in `service.api` module
2. Implement in corresponding `service.impl` module
3. Add Spring configuration/annotations
4. Write unit tests in the same module
5. Update integration tests if needed

### Adding a New Data Model
1. Create model class in appropriate `model.*` module
2. Add JPA annotations for persistence
3. Create corresponding repository interface
4. Add unit tests for model validation
5. Update schema if database changes are needed

### Modifying External Data Integration
1. Locate the appropriate `service.external.*` module
2. Update the data model in corresponding `model.external.*` module
3. Modify service implementation
4. Update or add tests
5. Verify integration with external API

### Working with the Web UI
1. UI code is in `citizen-intelligence-agency` module
2. Vaadin components are server-side
3. Follow existing UI patterns and layouts
4. Test UI changes manually (automated with Selenium where applicable)

## Security Considerations

**Critical Security Guidelines:**
1. **Never commit secrets** to source code
2. **Use Secrets Manager** for sensitive credentials in AWS deployments
3. **Validate all inputs** especially from external sources
4. **Follow principle of least privilege** for access controls
5. **Keep dependencies updated** to avoid known vulnerabilities
6. **Use HTTPS/SSL** for all external communications

**Security Tools Used:**
- CodeQL for static analysis
- Dependency-Check for vulnerability scanning
- SonarCloud for code quality and security
- OWASP ZAP for security testing

## CI/CD and GitHub Actions

### Workflows
- **release.yml**: Full build, test, and release workflow
- **codeql-analysis.yml**: Security scanning
- **dependency-review.yml**: Dependency vulnerability checking
- **scorecards.yml**: OpenSSF Scorecard evaluation
- **zap-scan.yml**: OWASP ZAP security scanning

### When Making Changes
- All PRs trigger automated checks
- CodeQL analysis must pass
- Dependency review must pass
- Tests must pass before merge
- Maintain code quality standards (SonarCloud)

## Documentation

### Key Documentation Files
- `ARCHITECTURE.md`: C4 model architecture diagrams
- `MINDMAP.md`: System component relationships
- `DATA_MODEL.md`: Data structures and relationships
- `SECURITY_ARCHITECTURE.md`: Security implementation details
- `CONTRIBUTING.md`: Contribution guidelines
- `README.md`: Main project overview

### When to Update Documentation
- Architecture changes require updates to ARCHITECTURE.md
- New features should be reflected in README.md
- Security changes must update SECURITY_ARCHITECTURE.md
- API changes need JavaDoc updates

## Troubleshooting Common Issues

### Build Failures
1. **Dependency resolution errors**: Check Maven settings and repository access
2. **Test failures**: Run tests individually to isolate issues
3. **Memory issues**: Increase Maven heap size with `MAVEN_OPTS=-Xmx2g`

### Database Issues
1. **Connection errors**: Verify PostgreSQL is running and credentials are correct
2. **Prepared transaction errors**: Check `max_prepared_transactions` setting
3. **SSL errors**: Ensure SSL certificates are properly configured

### Integration Test Failures
1. **External API timeouts**: May need to skip integration tests with `-Dtest='!**ITest*'`
2. **Database state**: Ensure clean database state between test runs
3. **Network issues**: Check connectivity to external data sources

## Good First Issues

If you're new to contributing:
1. Documentation improvements
2. Test coverage improvements
3. Fixing code quality issues flagged by SonarCloud
4. Updating dependencies
5. UI/UX enhancements in Vaadin components

## Best Practices for Copilot Usage

When working on this repository:
1. **Start small**: Focus on one module at a time
2. **Run tests frequently**: Validate changes iteratively
3. **Check existing patterns**: Look for similar implementations in the codebase
4. **Review documentation**: Ensure your changes align with architecture
5. **Security first**: Always consider security implications
6. **Minimal changes**: Make surgical, focused changes rather than broad refactoring

## Resources

- **Project Website**: https://www.hack23.com
- **Entity Model Docs**: https://hack23.github.io/cia/service.data.impl/hbm2doc/entities/index.html
- **API Documentation**: https://hack23.github.io/cia/apidocs/index.html
- **ISMS Public Repository**: https://github.com/Hack23/ISMS-PUBLIC
- **Swedish Parliament API**: http://data.riksdagen.se/
- **Swedish Election Authority**: http://www.val.se/
- **World Bank Open Data**: http://data.worldbank.org/

## Questions or Issues?

- Check existing issues on GitHub
- Review CONTRIBUTING.md for contribution guidelines
- Consult CODE_OF_CONDUCT.md for community standards
- Open a new issue if you need help
