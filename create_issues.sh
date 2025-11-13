#!/bin/bash

# Script to create 5 GitHub issues for the next minor release
# This script requires gh CLI to be installed and authenticated
# Run: gh auth login
# Then: ./create_issues.sh

set -e

REPO="Hack23/cia"

echo "Creating 5 GitHub issues for next minor release..."
echo "Repository: $REPO"
echo ""

# Check if gh is authenticated
if ! gh auth status >/dev/null 2>&1; then
    echo "❌ Error: GitHub CLI is not authenticated"
    echo "Please run: gh auth login"
    exit 1
fi

# Issue #1: Spring Framework Upgrade
echo "Creating Issue #1: Spring Framework Upgrade..."
ISSUE1_URL=$(gh issue create --repo "$REPO" \
    --title "🔐 Upgrade Spring Framework to 5.3.42 for Security Patches" \
    --label "priority:critical,type:security,size:small,domain:backend,next-release" \
    --body '## 🎯 Objective

Upgrade Spring Framework from version 5.3.39.hack23java25 to the latest 5.3.42 release to incorporate critical security patches and bug fixes before Spring 5.x reaches EOL (August 31, 2024 - already passed).

## 📋 Background

The CIA project currently uses Spring Framework 5.3.39 (custom build) across all 49 Maven modules. Spring Framework 5.x reached EOL on August 31, 2024, and version 5.3.42 is the final release with security patches. Since we'"'"'re maintaining the javax namespace strategy per [End-of-Life-Strategy.md](https://github.com/Hack23/cia/blob/master/End-of-Life-Strategy.md), this upgrade is critical for security without requiring Jakarta migration.

**Current versions in parent-pom/pom.xml:**
- Spring Framework: `5.3.39.hack23java25`
- Spring Security: `5.8.16`
- Spring Data: `2.7.18`

**Metrics:**
- Total Maven modules affected: 49
- Java files in project: 1,551
- Current Java target: 25

## ✅ Acceptance Criteria

- [ ] Update `cia.project.versions.spring` from `5.3.39.hack23java25` to `5.3.42` in `parent-pom/pom.xml`
- [ ] Verify compatibility with Java 25 runtime (current target)
- [ ] Run full test suite (`mvn test`) - ensure all tests pass
- [ ] Update Spring Security to 5.8.18 (latest 5.x compatible version)
- [ ] Run OWASP dependency check to confirm vulnerability resolution
- [ ] Update documentation in End-of-Life-Strategy.md with new version
- [ ] Verify successful build with `mvn clean install`

## 🛠️ Implementation Guidance

### Files to Modify
- `parent-pom/pom.xml` - Update Spring version properties (lines 83-85)

### Approach
1. Update the Spring Framework version property:
   ```xml
   <cia.project.versions.spring>5.3.42</cia.project.versions.spring>
   <cia.project.versions.spring.security>5.8.18</cia.project.versions.spring.security>
   ```

2. Test compatibility:
   ```bash
   mvn clean install -DskipTests
   mvn test
   ```

3. Run security scans:
   ```bash
   mvn dependency-check:check
   ```

4. Verify no breaking changes in:
   - Spring MVC controllers (citizen-intelligence-agency module)
   - Spring Security configurations (service.impl module)
   - Spring Data JPA repositories (service.data.impl module)

### Expected Security Benefits
- Resolves potential CVEs in Spring Framework 5.3.39
- Ensures final security patches from Spring 5.x branch
- Maintains compatibility with Java 21-25 runtime

## 🔗 Related Resources

- [Spring Framework 5.3.42 Release Notes](https://github.com/spring-projects/spring-framework/releases/tag/v5.3.42)
- [End-of-Life Strategy](https://github.com/Hack23/cia/blob/master/End-of-Life-Strategy.md)
- [Security Architecture](https://github.com/Hack23/cia/blob/master/SECURITY_ARCHITECTURE.md)
- [ISMS Vulnerability Management Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md)

## 📊 Metadata

**Priority:** Critical  
**Effort:** Small (2-4 hours)  
**Impact:** Security compliance, vulnerability mitigation  
**Dependencies:** None  
**Affected Modules:** All 49 Maven modules')

echo "✅ Created: $ISSUE1_URL"
echo ""

# Issue #2: Test Coverage
echo "Creating Issue #2: Test Coverage Improvement..."
ISSUE2_URL=$(gh issue create --repo "$REPO" \
    --title "✅ Improve Test Coverage in Core Service Modules" \
    --label "priority:high,type:test,size:medium,domain:backend,next-release" \
    --body '## 🎯 Objective

Increase test coverage in core service modules from current baseline to 80%+ coverage, focusing on business logic, data access, and external service integration layers.

## 📋 Background

The CIA project has 1,551 Java files across 49 Maven modules. Test coverage reports exist in target directories, but many core service modules lack comprehensive unit and integration tests. Improving coverage will:
- Reduce regression bugs during maintenance
- Enable confident refactoring
- Improve code quality metrics on SonarCloud
- Support future migrations (Vaadin upgrade, Jakarta namespace)

**Current test infrastructure:**
- JUnit 4.13.2 for unit testing
- Mockito for mocking
- Spring Test framework for integration tests
- DBUnit 3.0.0 for database testing

**Key modules needing coverage improvement:**
- `service.component.agent.impl` - Business rules and data processing
- `service.data.impl` - Data access layer
- `service.external.*` - External API integrations
- `service.impl` - Core business logic

## ✅ Acceptance Criteria

- [ ] Achieve 80%+ line coverage in `service.component.agent.impl`
- [ ] Achieve 75%+ line coverage in `service.data.impl`
- [ ] Add integration tests for external API services (Riksdagen, Val, WorldBank)
- [ ] Add unit tests for Drools rules engine execution
- [ ] Generate JaCoCo coverage report showing improvement
- [ ] All new tests pass in CI/CD pipeline
- [ ] Update CI/CD to fail builds below 70% coverage threshold

## 🛠️ Implementation Guidance

### Files to Create/Modify
- `service.component.agent.impl/src/test/java/` - Add unit tests for agent components
- `service.data.impl/src/test/java/` - Add DAO and repository tests
- `service.external.*/src/test/java/` - Add API client tests with mocking
- `parent-pom/pom.xml` - Configure JaCoCo minimum coverage thresholds

### Approach

1. Generate baseline coverage report:
   ```bash
   mvn clean test jacoco:report
   find . -name "jacoco.xml" -exec cat {} \;
   ```

2. Identify low-coverage classes (< 50% coverage) and prioritize business logic

3. Add unit tests following existing patterns with Mockito

4. Add integration tests for external APIs with test data or mock HTTP responses

5. Configure JaCoCo enforcement in pom.xml with 70% minimum threshold

## 🔗 Related Resources

- [Contributing Guidelines](https://github.com/Hack23/cia/blob/master/CONTRIBUTING.md)
- [Test Foundation Module](https://github.com/Hack23/cia/tree/master/testfoundation)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [Spring Test Reference](https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/testing.html)

## 📊 Metadata

**Priority:** High  
**Effort:** Medium (6-8 hours)  
**Impact:** Code quality, maintainability, regression prevention  
**Dependencies:** None  
**Affected Modules:** service.component.agent.impl, service.data.impl, service.external.*')

echo "✅ Created: $ISSUE2_URL"
echo ""

# Issue #3: Database Performance
echo "Creating Issue #3: Database Performance Optimization..."
ISSUE3_URL=$(gh issue create --repo "$REPO" \
    --title "⚡ Optimize Database Query Performance with Hibernate Caching" \
    --label "priority:high,type:performance,size:medium,domain:database,next-release" \
    --body '## 🎯 Objective

Optimize database query performance by implementing Hibernate second-level caching, query result caching, and eliminating N+1 query problems in high-traffic views and dashboards.

## 📋 Background

The CIA project uses PostgreSQL 16 with Hibernate 5.6.15.Final for data persistence. Analysis of current implementation shows opportunities for performance optimization:

**Current database configuration:**
- PostgreSQL 16 (EOL: November 2028)
- Hibernate 5.6.15.Final
- EhCache 2.10.5 configured but underutilized
- JPA 2.x persistence

**Performance bottlenecks identified:**
- Dashboard views making 50+ queries per page load
- Politician detail pages with N+1 queries for relationships
- Vote data aggregations without caching
- Frequently-accessed reference data reloaded on every request

**Current caching setup:**
- EhCache version: 2.10.5
- Spring Cache annotations: 1.2.0
- Query cache: Not enabled

## ✅ Acceptance Criteria

- [ ] Enable Hibernate query cache in persistence.xml
- [ ] Configure EhCache for second-level entity caching
- [ ] Add @Cacheable annotations to frequently-accessed entities
- [ ] Implement query result caching for dashboard aggregations
- [ ] Eliminate N+1 queries in politician and party detail views
- [ ] Measure and document query count reduction (target: 50% reduction)
- [ ] Add cache statistics monitoring to JavaMelody
- [ ] Verify cache invalidation works correctly on data updates

## 🛠️ Implementation Guidance

### Files to Modify
- `service.data.impl/src/main/resources/META-INF/persistence.xml` - Enable query cache
- `service.data.impl/src/main/resources/ehcache.xml` - Configure cache regions
- `model.internal.application/src/main/java/` - Add @Cache annotations to entities
- `service.data.impl/src/main/java/` - Add @Cacheable to repository methods
- `citizen-intelligence-agency/src/main/resources/application-context-service.xml` - Configure cache manager

### Approach

1. Enable Hibernate query cache in persistence.xml
2. Configure EhCache regions with appropriate TTL
3. Add entity caching with @Cache annotations
4. Implement query result caching with @Cacheable
5. Use fetch joins to eliminate N+1 queries
6. Add cache eviction on updates
7. Verify cache effectiveness with statistics

### Performance Testing

Before: 50+ queries per dashboard load  
Target: < 25 queries (50% reduction)

## 🔗 Related Resources

- [Hibernate Second-Level Cache Documentation](https://docs.jboss.org/hibernate/orm/5.6/userguide/html_single/Hibernate_User_Guide.html#caching)
- [EhCache Configuration Guide](https://www.ehcache.org/documentation/2.10/configuration/index.html)
- [Data Model Documentation](https://github.com/Hack23/cia/blob/master/DATA_MODEL.md)
- [PostgreSQL Configuration Guide](https://github.com/Hack23/cia/blob/master/README.md#postgresql-16-configuration-guide)

## 📊 Metadata

**Priority:** High  
**Effort:** Medium (6-8 hours)  
**Impact:** Performance improvement, reduced database load, better scalability  
**Dependencies:** None  
**Affected Modules:** service.data.impl, model.internal.application, citizen-intelligence-agency')

echo "✅ Created: $ISSUE3_URL"
echo ""

# Issue #4: Documentation
echo "Creating Issue #4: Documentation Update..."
ISSUE4_URL=$(gh issue create --repo "$REPO" \
    --title "📝 Update Documentation for Java 25 and Enhanced Setup Guide" \
    --label "priority:medium,type:docs,size:small,domain:documentation,next-release" \
    --body '## 🎯 Objective

Update project documentation to reflect current Java 25 support, clarify setup instructions, and improve API documentation for developers contributing to the next release.

## 📋 Background

The CIA project has been updated to support Java 25 (runtime) with Java 21 source compatibility. However, documentation in several areas needs updates to reflect current state:

**Current documentation status:**
- README.md mentions Java 21-25 support but lacks Java 25 specific guidance
- Setup instructions reference older Java versions
- API documentation (JavaDoc) is sparse in some modules
- GitHub Copilot setup guide exists but needs clarification
- Build troubleshooting section is missing

**Documentation gaps identified:**
- Java 25 specific configuration and gotchas
- Maven 3.9.9 requirements not clearly stated
- PostgreSQL 16 SSL setup has complex instructions
- Missing troubleshooting guide for common build issues
- API documentation coverage < 50% in some modules

**Current documentation files:**
- README.md (47KB) - Main project documentation
- CONTRIBUTING.md (4.2KB) - Contribution guidelines
- ARCHITECTURE.md (32KB) - System architecture
- End-of-Life-Strategy.md (8.7KB) - Technology lifecycle

## ✅ Acceptance Criteria

- [ ] Update README.md with Java 25 setup instructions and known issues
- [ ] Add Maven 3.9.9 minimum requirement callout
- [ ] Simplify PostgreSQL 16 SSL setup instructions
- [ ] Create TROUBLESHOOTING.md with common build/runtime issues
- [ ] Improve JavaDoc coverage to 70%+ in service.* modules
- [ ] Update .github/COPILOT_SETUP.md with current workflow
- [ ] Add quick start guide for new developers (5-minute setup)
- [ ] Verify all documentation links are valid

## 🛠️ Implementation Guidance

### Files to Create/Modify
- `README.md` - Update Java 25 section, add quick start
- `TROUBLESHOOTING.md` - Create new troubleshooting guide
- `.github/COPILOT_SETUP.md` - Update Copilot workflow documentation
- `service.*/src/main/java/` - Add missing JavaDoc comments
- `parent-pom/pom.xml` - Document Maven version requirement

### Approach

1. Update README.md with Java 25 installation and known issues
2. Create comprehensive TROUBLESHOOTING.md for common problems
3. Improve JavaDoc coverage with class and method documentation
4. Add quick start section to README.md (5-minute setup)
5. Validate all documentation links with markdown-link-check

## 🔗 Related Resources

- [Java 25 Release Notes](https://openjdk.org/projects/jdk/25/)
- [Maven 3.9 Release Notes](https://maven.apache.org/docs/3.9.9/release-notes.html)
- [PostgreSQL 16 Documentation](https://www.postgresql.org/docs/16/)
- [JavaDoc Style Guide](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)

## 📊 Metadata

**Priority:** Medium  
**Effort:** Small (3-4 hours)  
**Impact:** Developer onboarding, reduced support burden, improved maintainability  
**Dependencies:** None  
**Affected Modules:** Documentation files, all service modules for JavaDoc')

echo "✅ Created: $ISSUE4_URL"
echo ""

# Issue #5: Drools Rules
echo "Creating Issue #5: Extended Drools Rules..."
ISSUE5_URL=$(gh issue create --repo "$REPO" \
    --title "🔍 Implement Extended Drools Compliance Rules (#6885)" \
    --label "priority:medium,type:feature,size:medium,domain:business-rules,next-release,enhancement" \
    --body '## 🎯 Objective

Implement 15 extended Drools compliance rules as defined in issue #6885 to enhance political analysis capabilities, detect anomalies in politician and party behavior, and provide deeper insights for researchers and journalists.

## 📋 Background

The CIA project uses Drools 10.1.0 as a business rules engine to detect compliance issues and interesting patterns in political data. Currently, basic rules exist in `PoliticianComplianceCheckImpl` and `PartyComplianceCheckImpl`. Issue #6885 proposes 15 new rules that build upon annual summaries and additional attributes to capture more domain-focused scenarios.

**Current Drools setup:**
- Drools version: 10.1.0
- Rules location: `service.component.agent.impl/src/main/resources/rules/`
- Fact classes: `PoliticianComplianceCheckImpl`, `PartyComplianceCheckImpl`
- Data source: Annual summary tables

**Proposed 15 new rules (from issue #6885):**
1. Politician with extremely high committee leadership ratio (>70%)
2. Politician with extremely low annual attendance (<50%)
3. Newly registered party with high absence rate (>30%)
4. Politician leading multiple committees simultaneously
5. Politician with extensive EU experience but zero government experience
6. Politician with high annual document output (unusually high)
7. Politician filed zero motions in the past year
8. Party with single person controlling multiple leadership roles
9. Party forming coalition with previously opposed party
10. Politician changed party multiple times (>2 times)
11. Party with single-year abstain spike (unusual abstention rate)
12. Politician near/past retirement age holding multiple roles (>65 years)
13. Party with no official documents this year
14. Politician overshadowed in committee leadership (minimal engagement)
15. Party reliant on very small headcount

## ✅ Acceptance Criteria

- [ ] Implement all 15 extended Drools rules in .drl files
- [ ] Add required helper methods to PoliticianComplianceCheckImpl class
- [ ] Add required helper methods to PartyComplianceCheckImpl class
- [ ] Create unit tests for each new rule with test data
- [ ] Update RulesEngineImpl to load additional fact data if needed
- [ ] Generate RuleViolation records for each triggered rule
- [ ] Document each rule with examples in DROOLS_RISK_RULES.md
- [ ] Verify rules execute correctly in integration tests
- [ ] Add performance benchmarks (rule execution < 500ms per entity)

## 🛠️ Implementation Guidance

### Files to Create/Modify
- `service.component.agent.impl/src/main/resources/rules/politician-compliance-extended.drl` - New extended politician rules
- `service.component.agent.impl/src/main/resources/rules/party-compliance-extended.drl` - New extended party rules
- `service.component.agent.impl/src/main/java/.../PoliticianComplianceCheckImpl.java` - Add helper methods
- `service.component.agent.impl/src/main/java/.../PartyComplianceCheckImpl.java` - Add helper methods
- `service.component.agent.impl/src/main/java/.../RulesEngineImpl.java` - Load additional facts
- `service.component.agent.impl/src/test/java/.../` - Add unit tests for new rules
- `DROOLS_RISK_RULES.md` - Document new rules

### Approach

1. Add helper methods to compliance check classes
2. Create Drools rules for each of the 15 scenarios
3. Update RulesEngineImpl to load necessary facts
4. Create comprehensive unit tests
5. Document rules with examples
6. Performance test rule execution

### Performance Considerations

- Rule execution should complete in < 500ms per entity
- Use indexed fields in Drools rules for better performance
- Consider batching fact insertion for large datasets
- Monitor memory usage with many active facts

## 🔗 Related Resources

- [Issue #6885: Enhancement: Introduce Extended Compliance Rules](https://github.com/Hack23/cia/issues/6885)
- [DROOLS_RISK_RULES.md](https://github.com/Hack23/cia/blob/master/DROOLS_RISK_RULES.md)
- [Drools Documentation](https://docs.drools.org/latest/drools-docs/docs-website/drools/index.html)
- [Data Model Documentation](https://github.com/Hack23/cia/blob/master/DATA_MODEL.md)

## 📊 Metadata

**Priority:** Medium  
**Effort:** Medium (6-8 hours)  
**Impact:** Enhanced political analysis, data quality, user insights  
**Dependencies:** Issue #6885  
**Affected Modules:** service.component.agent.impl')

echo "✅ Created: $ISSUE5_URL"
echo ""

echo "=========================================="
echo "✅ Successfully created 5 GitHub issues!"
echo "=========================================="
echo ""
echo "Issue URLs:"
echo "1. $ISSUE1_URL"
echo "2. $ISSUE2_URL"
echo "3. $ISSUE3_URL"
echo "4. $ISSUE4_URL"
echo "5. $ISSUE5_URL"
echo ""
echo "Total estimated effort: 32-36 hours (4-5 days)"
echo ""
