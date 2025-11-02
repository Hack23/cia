# GitHub Copilot Setup with MCP Servers

This directory contains configuration files that make GitHub Copilot smarter and more effective when working with the Citizen Intelligence Agency project.

## Overview

The configuration includes:

1. **`copilot-setup-steps.yml`** - Pre-installation steps for the development environment
2. **`copilot-mcp-config.json`** - Model Context Protocol (MCP) servers configuration
3. **`copilot-instructions.md`** - Project-specific instructions for Copilot agents

## What are MCP Servers?

Model Context Protocol (MCP) servers provide additional context and capabilities to GitHub Copilot, enabling it to:

- Access repository information through the GitHub API
- Navigate and search the project's file system
- Query database schemas (when available)
- Understand Git history and changes
- Access external APIs and services

## MCP Servers Configured

### 1. GitHub MCP Server

**Purpose**: Provides access to GitHub repository operations, issues, pull requests, and workflows.

**Capabilities**:
- Search code across the repository
- Read and analyze issues and pull requests
- Access workflow runs and job logs
- Navigate repository structure
- Read file contents and commit history

**Usage**: Automatically enabled when running in GitHub Actions or with a GitHub token.

### 2. File System MCP Server

**Purpose**: Provides access to project files and directory structure.

**Capabilities**:
- Navigate project directory structure
- Read and analyze source code files
- Search for patterns in code
- Access configuration files

**Allowed Extensions**: `.java`, `.xml`, `.properties`, `.yml`, `.yaml`, `.md`, `.sql`, `.json`

### 3. PostgreSQL MCP Server (Optional)

**Purpose**: Provides context about database schema and queries.

**Capabilities**:
- Query database schema
- Validate SQL queries
- Understand table relationships

**Note**: Only available when PostgreSQL is configured and accessible.

### 4. Git MCP Server

**Purpose**: Provides Git version control operations.

**Capabilities**:
- View commit history
- Compare changes (git diff)
- Check repository status
- List and switch branches

## Environment Setup

The `copilot-setup-steps.yml` file configures the development environment with:

### Required Tools

1. **Java 25 JDK** (Temurin distribution)
   - Required for building and running the application
   - Configured with Maven caching for faster builds

2. **Maven 3.9.9+**
   - Build automation tool for the multi-module project
   - Dependency management

3. **Build Tools**
   - `graphviz` - For documentation generation (architecture diagrams)
   - `build-essential` - Core build tools
   - `fakeroot`, `devscripts`, `debhelper`, `dh-make` - For Debian package building

4. **PostgreSQL Client**
   - For database operations and testing

5. **Swedish Timezone Configuration**
   - Project-specific requirement for date/time operations

### Build Commands Reference

```bash
# Clean and install all modules
mvn clean install

# Build without tests (faster)
mvn clean install -DskipTests

# Run tests only
mvn test

# Generate site documentation
mvn site

# Generate code coverage report
mvn clean test jacoco:report

# Run dependency security check
mvn dependency-check:check

# Build with release profile
mvn clean install -Prelease-site,all-modules
```

## Using in GitHub Actions Workflows

To use the setup configuration in a workflow:

```yaml
name: My Workflow
on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-24.04
    steps:
      # Include the setup steps
      - uses: actions/checkout@v5
      
      - name: Set up JDK 25
        uses: actions/setup-java@v5
        with:
          distribution: 'temurin'
          java-version: '25'
          cache: 'maven'
      
      - name: Set up Maven
        uses: stCarolas/setup-maven@v5
        with:
          maven-version: 3.9.9
      
      - name: Install build tools
        run: |
          sudo apt-get update
          sudo apt-get install -y graphviz build-essential postgresql-client
      
      # Your build steps
      - name: Build with Maven
        run: mvn clean install
```

## Technology Stack Context

The MCP configuration provides Copilot with context about:

### Primary Technologies
- **Language**: Java 25
- **Build Tool**: Maven 3.9.9+
- **Architecture**: Multi-module Maven project with layered architecture

### Frameworks
- Spring Framework 5.x (MVC, Security, Data, Integration)
- Vaadin 8 (Server-side UI)
- Hibernate/JPA (ORM)
- Drools (Business Rules Engine)

### Databases
- PostgreSQL 16

### Messaging
- ActiveMQ Artemis (JMS)

### Testing
- JUnit 5
- Mockito
- Selenium WebDriver

### Security
- Spring Security
- OWASP Dependency Check
- CodeQL
- Bouncy Castle (Cryptography)

### Monitoring
- JavaMelody
- SLF4J/Logback

## Project Structure

```
cia/
├── citizen-intelligence-agency/   # Main web application
├── service.*/                     # Service layer modules
├── model.*/                       # Data model modules
├── testfoundation/                # Testing utilities
├── cia-dist-deb/                  # Debian package distribution
├── cia-dist-cloudformation/       # AWS CloudFormation templates
└── .github/
    ├── copilot-setup-steps.yml    # Environment setup
    ├── copilot-mcp-config.json    # MCP servers configuration
    ├── copilot-instructions.md    # Copilot instructions
    └── workflows/                  # GitHub Actions workflows
```

## Coding Standards

The configuration enforces these standards:

### Java Code Style
- Use Java 25 features where appropriate
- Follow existing code formatting
- Use meaningful variable and method names
- Add JavaDoc for public APIs
- Keep methods focused and concise

### Spring Framework Conventions
- Use constructor injection for dependencies
- Proper annotation usage (@Service, @Repository, @Controller)
- Transaction management with @Transactional

### JPA/Hibernate Guidelines
- Entity classes in `model.*` packages
- Proper JPA annotations
- Avoid N+1 query problems

### Security Rules
- Never commit secrets or credentials
- Validate all user inputs
- Use parameterized queries
- Follow OWASP best practices

### Testing Requirements
- Write unit tests for all new functionality
- Maintain test coverage above existing levels
- Place tests in `src/test/java`
- Use JUnit 5 and Mockito

## External API Integrations

The project integrates with several external APIs:

1. **Swedish Parliament (Riksdagen) API** - Parliamentary data
2. **Swedish Election Authority** - Election and party data
3. **World Bank Open Data** - Economic indicators
4. **Swedish Financial Management Authority** - Government finances

When working with these APIs:
- Handle errors gracefully
- Implement retry logic for transient failures
- Cache data appropriately to reduce API calls
- Document any new integrations

## Reference Workflows

Use these workflows as setup guides:

### 1. release.yml
**Purpose**: Main CI/CD pipeline with complete build setup

**Key Steps**:
- Java 25 setup with Temurin distribution
- Maven 3.9.9 installation
- Build tools installation
- Swedish timezone configuration
- Multi-module Maven build with release profile
- SBOM and attestation generation

### 2. codeql-analysis.yml
**Purpose**: Security scanning with CodeQL

**Key Steps**:
- CodeQL initialization for Java
- Security vulnerability scanning
- Automated security alerts

### 3. dependency-review.yml
**Purpose**: Dependency security checks

**Key Steps**:
- Dependency vulnerability scanning
- License compliance checking
- Pull request dependency analysis

### 4. zap-scan.yml
**Purpose**: OWASP ZAP security testing

**Key Steps**:
- Dynamic application security testing
- Web application vulnerability scanning

## Quality Assurance

The project maintains high quality standards:

### Code Quality Tools
- **SonarCloud** - Code quality and security analysis
- **OWASP Dependency Check** - Dependency vulnerability scanning
- **CodeQL** - Security vulnerability detection
- **JaCoCo** - Code coverage reporting

### Quality Gates
- All tests must pass
- Code coverage must be maintained
- No critical security issues
- No vulnerable dependencies
- No critical SonarCloud issues

## Best Practices

When working with Copilot on this project:

1. **Understand Context** - Review related code and architecture before making changes
2. **Minimal Changes** - Make the smallest change necessary to achieve the goal
3. **Test Thoroughly** - Always run tests and verify functionality
4. **Security First** - Consider security implications of all changes
5. **Follow Patterns** - Use existing code patterns and conventions
6. **Document Changes** - Update documentation for significant changes
7. **Ask Questions** - If requirements are unclear, ask for clarification

## Troubleshooting

### Maven Build Issues

```bash
# Clear local repository cache
rm -rf ~/.m2/repository

# Build with dependency resolution
mvn clean install -U

# Skip tests if needed for diagnosis
mvn clean install -DskipTests
```

### Java Version Issues

```bash
# Check Java version
java -version

# Set JAVA_HOME if needed
export JAVA_HOME=/usr/lib/jvm/temurin-25-jdk-amd64
```

### PostgreSQL Connection Issues

```bash
# Check PostgreSQL client
psql --version

# Test connection
psql -h localhost -U postgres -d cia
```

## Additional Resources

- [GitHub Copilot Documentation](https://docs.github.com/en/copilot)
- [Model Context Protocol Specification](https://modelcontextprotocol.io/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Vaadin Documentation](https://vaadin.com/docs)

## Contributing

When modifying these configuration files:

1. Test changes in a branch first
2. Verify environment setup works in GitHub Actions
3. Update this README with any new capabilities
4. Document any new MCP servers added
5. Maintain backward compatibility where possible

## Support

For questions or issues with the Copilot setup:

1. Check the [project documentation](../../README.md)
2. Review [GitHub workflow files](.github/workflows/)
3. Consult [CONTRIBUTING.md](../../CONTRIBUTING.md)
4. Open an issue in the repository

---

**Note**: This configuration makes Copilot more effective by providing it with complete context about the project's technology stack, coding standards, and development environment. The MCP servers enable Copilot to access and understand the codebase more deeply, resulting in better suggestions and more accurate code generation.
