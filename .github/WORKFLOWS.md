# GitHub Actions Workflows Documentation

This document describes the GitHub Actions workflows used in the Citizen Intelligence Agency project.

## Overview

The project uses multiple workflows for continuous integration, security analysis, documentation generation, and releases.

## Core CI/CD Workflows

### 1. CodeQL Analysis (`codeql-analysis.yml`)

**Purpose**: Security vulnerability scanning and code quality analysis

**Triggers**:
- Push to master branch
- Pull requests to master
- Weekly schedule (Wednesday 4 AM)

**Key Steps**:
- Runs CodeQL security scanning
- Runs Checkov for CloudFormation template analysis
- Executes all unit tests (excludes integration tests)
- PostgreSQL 16 with SSL/TLS configuration
- Dependency submission for GitHub dependency graph

**Test Execution**:
```bash
mvn -B test --file pom.xml -Prelease-site,all-modules \
  -Dtest='!**ITest*,!**/XmlDateTypeAdapterTest,!**/XmlTimeTypeAdapterTest,!**/XmlDateTimeTypeAdapterTest' \
  -DfailIfNoTests=false -Dsurefire.failIfNoSpecifiedTests=false -Dspdx.skip=true
```

### 2. Copilot Setup Steps (`copilot-setup-steps.yml`)

**Purpose**: Development environment setup and validation for GitHub Copilot

**Triggers**:
- Workflow dispatch (manual)
- Push/PR to this workflow file
- Used by GitHub Copilot for repository setup

**Key Features**:
- Complete web test environment (Chrome, Xvfb)
- PostgreSQL 16 with SSL certificates
- Full application startup validation
- All unit test execution
- Comprehensive environment caching

**Test Execution**: Same as CodeQL workflow

**Environment**:
- Java 25 (Temurin)
- Maven 3.9.9
- PostgreSQL 16 with pgaudit, pgvector extensions
- Google Chrome for UI testing
- Xvfb for headless browser testing

### 3. Release (`release.yml`)

**Purpose**: Production release workflow

**Triggers**: Manual workflow dispatch with version input

**Key Steps**:
- Version update automation
- Full build with release-site profile
- SLSA provenance attestation
- SBOM generation and attestation
- Artifact signing
- GitHub release creation
- Asset upload

**Build Command**:
```bash
mvn -B clean install -Prelease-site,all-modules \
  -DforkMode=once '-Dtest=!**ITest*,!**DocumentationTest*' \
  -Dmaven.test.skip=true -DfailIfNoTests=false
```

## Documentation Workflows

### 4. Site Generation (`site-generation.yml`) ⭐ NEW

**Purpose**: Generate complete Maven site with all reports and documentation

**Triggers**:
- Manual workflow dispatch
- Weekly schedule (Sunday 2 AM UTC)

**Generated Content**:
- Complete Maven site
- Javadoc with UML diagrams
- Test reports (Surefire)
- Code coverage reports (JaCoCo)
- Dependency reports
- Project information reports
- Cross-referenced source code (JXR)

**Build Command**:
```bash
cd citizen-intelligence-agency
mvn -B clean install site -Prelease-site \
  -Dmaven.test.failure.ignore=true \
  -DforkMode=once \
  -Dannotation.failOnError=false \
  -Dspdx.skip=true
```

**Memory Requirements**: 6GB heap (MAVEN_OPTS="-server -Xmx6048m -Xms6048m")

**Artifacts**:
- Full site uploaded as GitHub Actions artifact
- 30-day retention
- Location: `citizen-intelligence-agency/target/site/` and `*/target/site/`

**Based on**: `build.xml` target `site-cia` and `release-site` Maven profile

### 5. Javadoc Generation (`javadoc-generation.yml`) ⭐ NEW

**Purpose**: Generate API documentation with UML diagrams

**Triggers**:
- Manual workflow dispatch
- Push to master (when Java files or POMs change)
- Pull requests to master (when Java files or POMs change)

**Features**:
- Aggregate Javadoc across all modules
- UML class diagrams (via UMLDoclet)
- Cross-referenced source code
- Dependency source includes
- UTF-8 encoding
- Automatic PR comments with Javadoc info

**Build Commands**:
```bash
# Fast build without tests
mvn -B clean install -DskipTests -Prelease-site,all-modules

# Generate Javadoc
mvn -B javadoc:aggregate -Prelease-site -Dmaven.javadoc.failOnError=false
```

**Artifacts**:
- Javadoc uploaded as GitHub Actions artifact
- 30-day retention
- Location: `target/site/apidocs/`

**Configuration**: Uses UMLDoclet 2.2.1 for UML diagram generation (configured in parent-pom)

## Security Workflows

### 6. Dependency Review (`dependency-review.yml`)

**Purpose**: Review pull request dependencies for security issues

**Triggers**: Pull requests

**Features**:
- Checks for vulnerable dependencies
- License compliance verification
- Dependency version analysis

### 7. Scorecards (`scorecards.yml`)

**Purpose**: OpenSSF Scorecard security assessment

**Triggers**:
- Push to master
- Weekly schedule

**Checks**:
- Security best practices
- Dependency management
- Code review practices
- CI/CD security

### 8. ZAP Scan (`zap-scan.yml`)

**Purpose**: OWASP ZAP security scanning

**Triggers**: Weekly schedule

**Features**:
- Dynamic application security testing
- Baseline scan mode
- SARIF report generation

## Validation Workflows

### 9. Validate Field Completeness (`validate-field-completeness.yml`)

**Purpose**: Validate data model field completeness

### 10. Validate JSON Schemas (`validate-json-schemas.yml`)

**Purpose**: Validate JSON schema files

### 11. Validate View Documentation (`validate-view-documentation.yml`)

**Purpose**: Validate view documentation completeness

## Utility Workflows

### 12. Generate Intelligence Changelog (`generate-intelligence-changelog.yml`)

**Purpose**: Generate changelog focused on intelligence features

### 13. Labeler (`labeler.yml`)

**Purpose**: Automatic PR labeling based on file changes

## Test Execution Strategy

### Unit Tests
- **Pattern**: `*Test.java` (excludes `*ITest.java`)
- **Execution**: All modules via `all-modules` profile
- **Exclusions**:
  - `**ITest*` - Integration tests (require DB/network)
  - `**/XmlDateTypeAdapterTest` - Timezone-sensitive
  - `**/XmlTimeTypeAdapterTest` - Timezone-sensitive
  - `**/XmlDateTimeTypeAdapterTest` - Timezone-sensitive
- **Count**: ~108 unit tests

### Integration Tests
- **Pattern**: `*ITest.java`
- **Execution**: Only in full build/release workflows
- **Requirements**: PostgreSQL, external connections
- **Count**: ~113 integration tests

### Test Profiles
- **release-site**: Full reporting, Javadoc, test reports
- **all-modules**: Include all modules in build

## Maven Profiles

### release-site Profile

Defined in `parent-pom/pom.xml`, includes:

**Build Plugins**:
- `maven-surefire-report-plugin` - Test reports
- `tattletale-maven` - Dependency analysis

**Reporting Plugins**:
- `maven-javadoc-plugin` (3.12.0) - Javadoc with UMLDoclet
- `maven-project-info-reports-plugin` (3.9.0) - Project info
- `maven-surefire-plugin` - Test execution
- `jacoco-maven-plugin` (0.8.14) - Code coverage
- `maven-surefire-report-plugin` - Test reports
- `maven-jxr-plugin` (3.6.0) - Source cross-reference
- `versions-maven-plugin` (2.21.0) - Dependency/plugin updates

**Javadoc Configuration**:
- Source: Java 21
- UMLDoclet: Class diagrams
- Dependency sources included
- UTF-8 encoding
- Cross-referenced source

## Workflow Best Practices

### Security
- All workflows use pinned action versions (SHA hashes)
- Harden Runner with egress policy
- Least privilege permissions
- Secret scanning enabled

### Performance
- Maven artifact caching
- APT package caching
- Dependency caching
- Reuse build artifacts where possible

### Reliability
- Retry logic for flaky tests
- Continue-on-error for non-critical steps
- Proper timeout configurations
- Comprehensive error messages

### Observability
- GitHub step summaries
- Artifact uploads
- Test reports
- Coverage reports

## Running Workflows Locally

### Unit Tests
```bash
cd /path/to/cia
mvn -B test -Prelease-site,all-modules \
  -Dtest='!**ITest*,!**/XmlDateTypeAdapterTest,!**/XmlTimeTypeAdapterTest,!**/XmlDateTimeTypeAdapterTest'
```

### Site Generation
```bash
cd /path/to/cia/citizen-intelligence-agency
export MAVEN_OPTS="-server -Xmx6048m -Xms6048m"
mvn clean install site -Prelease-site
```

### Javadoc Only
```bash
cd /path/to/cia
mvn clean install -DskipTests -Prelease-site,all-modules
mvn javadoc:aggregate -Prelease-site
```

## Maintenance

### Updating Dependencies
- Action versions are pinned with SHA hashes
- Update via Dependabot PRs
- Test thoroughly before merging

### Adding New Workflows
1. Create workflow in `.github/workflows/`
2. Follow existing patterns
3. Use pinned action versions
4. Document in this file
5. Test with workflow_dispatch first

### Modifying Test Execution
1. Update test patterns in workflows
2. Update this documentation
3. Verify in CI before merging
4. Ensure integration tests remain separate

## Troubleshooting

### Tests Failing in CI but Passing Locally
- Check timezone differences (XmlDate*TypeAdapterTest)
- Verify PostgreSQL configuration
- Check environment variables
- Review caching issues

### Site Generation Out of Memory
- Increase MAVEN_OPTS heap size
- Check tattletale plugin configuration
- Review large dependency trees

### Javadoc Generation Failures
- Check for Javadoc syntax errors
- Verify UMLDoclet compatibility
- Review source encoding issues
- Check for missing package-info.java

## References

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Maven Site Plugin](https://maven.apache.org/plugins/maven-site-plugin/)
- [Maven Javadoc Plugin](https://maven.apache.org/plugins/maven-javadoc-plugin/)
- [UMLDoclet](https://github.com/talsma-ict/umldoclet)
- [JaCoCo](https://www.jacoco.org/jacoco/trunk/doc/)
