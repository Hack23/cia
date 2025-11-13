# Top 5 Priority Issues for Next Minor Release

This document contains 5 high-priority issues identified for the next minor release of the Citizen Intelligence Agency project. Each issue includes complete details, acceptance criteria, implementation guidance, and accurate metrics measured from the actual codebase.

## Summary

| Issue | Title | Priority | Effort | Impact |
|-------|-------|----------|--------|--------|
| #1 | Upgrade Spring Framework to 5.3.42 for Security Patches | Critical | Small (2-4h) | Security compliance, vulnerability mitigation |
| #2 | Improve Test Coverage in Core Service Modules | High | Medium (6-8h) | Code quality, maintainability, regression prevention |
| #3 | Optimize Database Query Performance with Hibernate Caching | High | Medium (6-8h) | Performance improvement, reduced DB load |
| #4 | Update Documentation for Java 25 and Enhanced Setup Guide | Medium | Small (3-4h) | Developer onboarding, reduced support burden |
| #5 | Implement Extended Drools Compliance Rules (#6885) | Medium | Medium (6-8h) | Enhanced political analysis, data quality |

---

## Issue #1: 🔐 Upgrade Spring Framework to 5.3.42 for Security Patches

### 🎯 Objective

Upgrade Spring Framework from version 5.3.39.hack23java25 to the latest 5.3.42 release to incorporate critical security patches and bug fixes before Spring 5.x reaches EOL (August 31, 2024 - already passed).

### 📋 Background

The CIA project currently uses Spring Framework 5.3.39 (custom build) across all 49 Maven modules. Spring Framework 5.x reached EOL on August 31, 2024, and version 5.3.42 is the final release with security patches. Since we're maintaining the javax namespace strategy per [End-of-Life-Strategy.md](https://github.com/Hack23/cia/blob/master/End-of-Life-Strategy.md), this upgrade is critical for security without requiring Jakarta migration.

**Current versions in parent-pom/pom.xml:**
- Spring Framework: `5.3.39.hack23java25`
- Spring Security: `5.8.16`
- Spring Data: `2.7.18`

**Metrics:**
- Total Maven modules affected: 49
- Java files in project: 1,551
- Current Java target: 25

### ✅ Acceptance Criteria

- [ ] Update `cia.project.versions.spring` from `5.3.39.hack23java25` to `5.3.42` in `parent-pom/pom.xml`
- [ ] Verify compatibility with Java 25 runtime (current target)
- [ ] Run full test suite (`mvn test`) - ensure all tests pass
- [ ] Update Spring Security to 5.8.18 (latest 5.x compatible version)
- [ ] Run OWASP dependency check to confirm vulnerability resolution
- [ ] Update documentation in End-of-Life-Strategy.md with new version
- [ ] Verify successful build with `mvn clean install`

### 🛠️ Implementation Guidance

#### Files to Modify
- `parent-pom/pom.xml` - Update Spring version properties (lines 83-85)

#### Approach
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

#### Expected Security Benefits
- Resolves potential CVEs in Spring Framework 5.3.39
- Ensures final security patches from Spring 5.x branch
- Maintains compatibility with Java 21-25 runtime

### 🔗 Related Resources

- [Spring Framework 5.3.42 Release Notes](https://github.com/spring-projects/spring-framework/releases/tag/v5.3.42)
- [End-of-Life Strategy](https://github.com/Hack23/cia/blob/master/End-of-Life-Strategy.md)
- [Security Architecture](https://github.com/Hack23/cia/blob/master/SECURITY_ARCHITECTURE.md)
- [ISMS Vulnerability Management Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md)

### 📊 Metadata

**Priority:** Critical  
**Effort:** Small (2-4 hours)  
**Impact:** Security compliance, vulnerability mitigation  
**Dependencies:** None  
**Affected Modules:** All 49 Maven modules  
**Labels:** `priority:critical`, `type:security`, `size:small`, `domain:backend`, `next-release`

---

## Issue #2: ✅ Improve Test Coverage in Core Service Modules

### 🎯 Objective

Increase test coverage in core service modules from current baseline to 80%+ coverage, focusing on business logic, data access, and external service integration layers.

### 📋 Background

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

### ✅ Acceptance Criteria

- [ ] Achieve 80%+ line coverage in `service.component.agent.impl`
- [ ] Achieve 75%+ line coverage in `service.data.impl`
- [ ] Add integration tests for external API services (Riksdagen, Val, WorldBank)
- [ ] Add unit tests for Drools rules engine execution
- [ ] Generate JaCoCo coverage report showing improvement
- [ ] All new tests pass in CI/CD pipeline
- [ ] Update CI/CD to fail builds below 70% coverage threshold

### 🛠️ Implementation Guidance

#### Files to Create/Modify
- `service.component.agent.impl/src/test/java/` - Add unit tests for agent components
- `service.data.impl/src/test/java/` - Add DAO and repository tests
- `service.external.*/src/test/java/` - Add API client tests with mocking
- `parent-pom/pom.xml` - Configure JaCoCo minimum coverage thresholds

#### Approach

1. **Generate baseline coverage report:**
   ```bash
   mvn clean test jacoco:report
   find . -name "jacoco.xml" -exec cat {} \;
   ```

2. **Identify low-coverage classes:**
   - Look for classes with <50% coverage
   - Prioritize business logic over boilerplate

3. **Add unit tests following existing patterns:**
   ```java
   @RunWith(MockitoJUnitRunner.class)
   public class MyServiceTest {
       @Mock
       private MyRepository mockRepository;
       
       @InjectMocks
       private MyServiceImpl service;
       
       @Test
       public void testBusinessLogic() {
           // Arrange
           when(mockRepository.findById(1L)).thenReturn(testEntity);
           
           // Act
           Result result = service.processData(1L);
           
           // Assert
           assertNotNull(result);
           verify(mockRepository).findById(1L);
       }
   }
   ```

4. **Add integration tests for external APIs:**
   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration
   public class RiksdagenApiIntegrationTest {
       @Autowired
       private RiksdagenService riksdagenService;
       
       @Test
       public void testFetchPoliticianData() {
           // Use test data or mock HTTP responses
           List<Politician> politicians = riksdagenService.fetchAll();
           assertNotNull(politicians);
       }
   }
   ```

5. **Configure JaCoCo enforcement:**
   ```xml
   <plugin>
       <groupId>org.jacoco</groupId>
       <artifactId>jacoco-maven-plugin</artifactId>
       <executions>
           <execution>
               <id>check</id>
               <goals>
                   <goal>check</goal>
               </goals>
               <configuration>
                   <rules>
                       <rule>
                           <element>BUNDLE</element>
                           <limits>
                               <limit>
                                   <counter>LINE</counter>
                                   <value>COVEREDRATIO</value>
                                   <minimum>0.70</minimum>
                               </limit>
                           </limits>
                       </rule>
                   </rules>
               </configuration>
           </execution>
       </executions>
   </plugin>
   ```

### 🔗 Related Resources

- [Contributing Guidelines](https://github.com/Hack23/cia/blob/master/CONTRIBUTING.md)
- [Test Foundation Module](https://github.com/Hack23/cia/tree/master/testfoundation)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [Spring Test Reference](https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/testing.html)

### 📊 Metadata

**Priority:** High  
**Effort:** Medium (6-8 hours)  
**Impact:** Code quality, maintainability, regression prevention  
**Dependencies:** None  
**Affected Modules:** service.component.agent.impl, service.data.impl, service.external.*  
**Labels:** `priority:high`, `type:test`, `size:medium`, `domain:backend`, `next-release`

---

## Issue #3: ⚡ Optimize Database Query Performance with Hibernate Caching

### 🎯 Objective

Optimize database query performance by implementing Hibernate second-level caching, query result caching, and eliminating N+1 query problems in high-traffic views and dashboards.

### 📋 Background

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

### ✅ Acceptance Criteria

- [ ] Enable Hibernate query cache in persistence.xml
- [ ] Configure EhCache for second-level entity caching
- [ ] Add @Cacheable annotations to frequently-accessed entities
- [ ] Implement query result caching for dashboard aggregations
- [ ] Eliminate N+1 queries in politician and party detail views
- [ ] Measure and document query count reduction (target: 50% reduction)
- [ ] Add cache statistics monitoring to JavaMelody
- [ ] Verify cache invalidation works correctly on data updates

### 🛠️ Implementation Guidance

#### Files to Modify
- `service.data.impl/src/main/resources/META-INF/persistence.xml` - Enable query cache
- `service.data.impl/src/main/resources/ehcache.xml` - Configure cache regions
- `model.internal.application/src/main/java/` - Add @Cache annotations to entities
- `service.data.impl/src/main/java/` - Add @Cacheable to repository methods
- `citizen-intelligence-agency/src/main/resources/application-context-service.xml` - Configure cache manager

#### Approach

1. **Enable Hibernate query cache in persistence.xml:**
   ```xml
   <property name="hibernate.cache.use_query_cache" value="true"/>
   <property name="hibernate.cache.use_second_level_cache" value="true"/>
   <property name="hibernate.cache.region.factory_class" 
             value="org.hibernate.cache.ehcache.EhCacheRegionFactory"/>
   ```

2. **Configure EhCache regions in ehcache.xml:**
   ```xml
   <cache name="politicianCache"
          maxEntriesLocalHeap="1000"
          eternal="false"
          timeToIdleSeconds="3600"
          timeToLiveSeconds="7200"
          memoryStoreEvictionPolicy="LRU">
   </cache>
   
   <cache name="voteDataCache"
          maxEntriesLocalHeap="5000"
          eternal="false"
          timeToIdleSeconds="1800"
          timeToLiveSeconds="3600">
   </cache>
   
   <cache name="org.hibernate.cache.internal.StandardQueryCache"
          maxEntriesLocalHeap="500"
          eternal="false"
          timeToLiveSeconds="1800">
   </cache>
   ```

3. **Add entity caching annotations:**
   ```java
   @Entity
   @Table(name = "politician")
   @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "politicianCache")
   public class Politician implements Serializable {
       // Entity fields
   }
   ```

4. **Add query result caching:**
   ```java
   @Cacheable("voteDataCache")
   public List<VoteData> findVotesByPoliticianId(Long politicianId) {
       return entityManager.createQuery(
           "SELECT v FROM VoteData v WHERE v.politicianId = :id", VoteData.class)
           .setParameter("id", politicianId)
           .setHint("org.hibernate.cacheable", true)
           .getResultList();
   }
   ```

5. **Eliminate N+1 queries with fetch joins:**
   ```java
   @Query("SELECT p FROM Politician p " +
          "LEFT JOIN FETCH p.partyMemberships " +
          "LEFT JOIN FETCH p.committeeAssignments " +
          "WHERE p.id = :id")
   Politician findByIdWithAssociations(@Param("id") Long id);
   ```

6. **Add cache eviction on updates:**
   ```java
   @CacheEvict(value = "politicianCache", key = "#politician.id")
   public void updatePolitician(Politician politician) {
       politicianRepository.save(politician);
   }
   ```

7. **Verify cache effectiveness:**
   ```bash
   # Check Hibernate statistics in logs
   hibernate.generate_statistics=true
   
   # Monitor cache hit ratio in JavaMelody
   # Target: >80% cache hit ratio for frequently-accessed data
   ```

#### Performance Testing

Before optimization:
```bash
# Measure baseline query count
mvn test -Dtest=DashboardPerformanceTest
# Expected: 50+ queries per dashboard load
```

After optimization:
```bash
# Verify query reduction
mvn test -Dtest=DashboardPerformanceTest
# Target: <25 queries per dashboard load (50% reduction)
```

### 🔗 Related Resources

- [Hibernate Second-Level Cache Documentation](https://docs.jboss.org/hibernate/orm/5.6/userguide/html_single/Hibernate_User_Guide.html#caching)
- [EhCache Configuration Guide](https://www.ehcache.org/documentation/2.10/configuration/index.html)
- [Data Model Documentation](https://github.com/Hack23/cia/blob/master/DATA_MODEL.md)
- [PostgreSQL Configuration Guide](https://github.com/Hack23/cia/blob/master/README.md#postgresql-16-configuration-guide)

### 📊 Metadata

**Priority:** High  
**Effort:** Medium (6-8 hours)  
**Impact:** Performance improvement, reduced database load, better scalability  
**Dependencies:** None  
**Affected Modules:** service.data.impl, model.internal.application, citizen-intelligence-agency  
**Labels:** `priority:high`, `type:performance`, `size:medium`, `domain:database`, `next-release`

---

## Issue #4: 📝 Update Documentation for Java 25 and Enhanced Setup Guide

### 🎯 Objective

Update project documentation to reflect current Java 25 support, clarify setup instructions, and improve API documentation for developers contributing to the next release.

### 📋 Background

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

### ✅ Acceptance Criteria

- [ ] Update README.md with Java 25 setup instructions and known issues
- [ ] Add Maven 3.9.9 minimum requirement callout
- [ ] Simplify PostgreSQL 16 SSL setup instructions
- [ ] Create TROUBLESHOOTING.md with common build/runtime issues
- [ ] Improve JavaDoc coverage to 70%+ in service.* modules
- [ ] Update .github/COPILOT_SETUP.md with current workflow
- [ ] Add quick start guide for new developers (5-minute setup)
- [ ] Verify all documentation links are valid

### 🛠️ Implementation Guidance

#### Files to Create/Modify
- `README.md` - Update Java 25 section, add quick start
- `TROUBLESHOOTING.md` - Create new troubleshooting guide
- `.github/COPILOT_SETUP.md` - Update Copilot workflow documentation
- `service.*/src/main/java/` - Add missing JavaDoc comments
- `parent-pom/pom.xml` - Document Maven version requirement

#### Approach

1. **Update README.md for Java 25:**
   ```markdown
   ## 🚀 Runtime Environment
   
   ### Java 25 Setup
   
   The CIA project runs on Java 25 with source level 21 compatibility.
   
   #### Install Java 25 (Eclipse Temurin)
   ```bash
   # Ubuntu/Debian
   wget https://github.com/adoptium/temurin25-binaries/releases/download/...
   sudo dpkg -i temurin-25-jdk_amd64.deb
   
   # Verify installation
   java -version  # Should show "openjdk version 25"
   ```
   
   #### Known Issues with Java 25
   - **Warning about restricted methods**: Safe to ignore when using Jansi in Maven
   - **Native access warnings**: Add `--enable-native-access=ALL-UNNAMED` if needed
   - **Module system**: Project uses classpath mode, no module-info.java required
   ```

2. **Create TROUBLESHOOTING.md:**
   ```markdown
   # CIA Project Troubleshooting Guide
   
   ## Build Issues
   
   ### "package javax.servlet does not exist"
   **Cause:** Maven dependencies not downloaded
   **Solution:**
   ```bash
   mvn clean install -U
   ```
   
   ### Build takes longer than 10 minutes
   **Cause:** Maven is downloading dependencies
   **Solution:** First build takes time. Use `-Dmaven.test.skip=true` to skip tests.
   
   ### "Failed to connect to database"
   **Cause:** PostgreSQL not running or wrong credentials
   **Solution:** Check PostgreSQL status and verify database.properties
   ```bash
   sudo systemctl status postgresql
   sudo -u postgres psql -c "\l"  # List databases
   ```
   
   ## Runtime Issues
   
   ### Application won't start on port 28443
   **Cause:** Port already in use
   **Solution:**
   ```bash
   sudo lsof -i :28443  # Find process using port
   sudo kill -9 <PID>   # Kill process if safe
   ```
   
   ## Test Failures
   
   ### Integration tests fail with "Connection refused"
   **Cause:** PostgreSQL not configured with SSL
   **Solution:** Follow PostgreSQL SSL setup in README.md
   ```

3. **Improve JavaDoc coverage:**
   ```java
   /**
    * Service for managing political data from the Swedish Parliament (Riksdagen).
    * 
    * <p>This service integrates with the Riksdagen API to fetch:
    * <ul>
    *   <li>Politician profiles and career data</li>
    *   <li>Vote records and attendance</li>
    *   <li>Documents and motions</li>
    *   <li>Committee assignments</li>
    * </ul>
    * 
    * @see <a href="http://data.riksdagen.se/">Riksdagen Open Data</a>
    * @since 2025-SNAPSHOT
    */
   public interface RiksdagenService {
       
       /**
        * Fetches all active politicians from the Riksdagen API.
        * 
        * @return list of politicians currently serving in parliament
        * @throws ApiException if the Riksdagen API is unavailable
        */
       List<Politician> fetchActivePoliticians() throws ApiException;
   }
   ```

4. **Add quick start section to README.md:**
   ```markdown
   ## ⚡ Quick Start (5 Minutes)
   
   Get the CIA project running locally in 5 minutes:
   
   ```bash
   # 1. Clone repository
   git clone https://github.com/Hack23/cia.git
   cd cia
   
   # 2. Build project (skip tests for speed)
   mvn clean install -DskipTests
   
   # 3. Setup PostgreSQL database
   sudo -u postgres psql -c "CREATE USER eris WITH PASSWORD 'discord';"
   sudo -u postgres psql -c "CREATE DATABASE cia_dev;"
   sudo -u postgres psql -c "GRANT ALL PRIVILEGES ON DATABASE cia_dev to eris;"
   
   # 4. Install and run
   sudo dpkg -i cia-dist-deb/target/cia-dist-deb-2025-SNAPSHOT.all.deb
   
   # 5. Access application
   # Open browser to https://localhost:28443/cia/
   ```
   
   **First time?** See [TROUBLESHOOTING.md](TROUBLESHOOTING.md) if you encounter issues.
   ```

5. **Validate all documentation links:**
   ```bash
   # Use markdown link checker
   npm install -g markdown-link-check
   find . -name "*.md" -exec markdown-link-check {} \;
   ```

### 🔗 Related Resources

- [Java 25 Release Notes](https://openjdk.org/projects/jdk/25/)
- [Maven 3.9 Release Notes](https://maven.apache.org/docs/3.9.9/release-notes.html)
- [PostgreSQL 16 Documentation](https://www.postgresql.org/docs/16/)
- [JavaDoc Style Guide](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)

### 📊 Metadata

**Priority:** Medium  
**Effort:** Small (3-4 hours)  
**Impact:** Developer onboarding, reduced support burden, improved maintainability  
**Dependencies:** None  
**Affected Modules:** Documentation files, all service modules for JavaDoc  
**Labels:** `priority:medium`, `type:docs`, `size:small`, `domain:documentation`, `next-release`

---

## Issue #5: 🔍 Implement Extended Drools Compliance Rules (Issue #6885)

### 🎯 Objective

Implement 15 extended Drools compliance rules as defined in issue #6885 to enhance political analysis capabilities, detect anomalies in politician and party behavior, and provide deeper insights for researchers and journalists.

### 📋 Background

The CIA project uses Drools 10.1.0 as a business rules engine to detect compliance issues and interesting patterns in political data. Currently, basic rules exist in `PoliticianComplianceCheckImpl` and `PartyComplianceCheckImpl`. Issue #6885 proposes 15 new rules that build upon annual summaries and additional attributes to capture more domain-focused scenarios.

**Current Drools setup:**
- Drools version: 10.1.0
- Rules location: `service.component.agent.impl/src/main/resources/rules/`
- Fact classes: `PoliticianComplianceCheckImpl`, `PartyComplianceCheckImpl`
- Data source: Annual summary tables (`ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual`, `ViewRiksdagenVoteDataBallotPartySummaryAnnual`)

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

### ✅ Acceptance Criteria

- [ ] Implement all 15 extended Drools rules in `.drl` files
- [ ] Add required helper methods to `PoliticianComplianceCheckImpl` class
- [ ] Add required helper methods to `PartyComplianceCheckImpl` class
- [ ] Create unit tests for each new rule with test data
- [ ] Update `RulesEngineImpl` to load additional fact data if needed
- [ ] Generate `RuleViolation` records for each triggered rule
- [ ] Document each rule with examples in DROOLS_RISK_RULES.md
- [ ] Verify rules execute correctly in integration tests
- [ ] Add performance benchmarks (rule execution < 500ms per entity)

### 🛠️ Implementation Guidance

#### Files to Create/Modify
- `service.component.agent.impl/src/main/resources/rules/politician-compliance-extended.drl` - New extended politician rules
- `service.component.agent.impl/src/main/resources/rules/party-compliance-extended.drl` - New extended party rules
- `service.component.agent.impl/src/main/java/.../PoliticianComplianceCheckImpl.java` - Add helper methods
- `service.component.agent.impl/src/main/java/.../PartyComplianceCheckImpl.java` - Add helper methods
- `service.component.agent.impl/src/main/java/.../RulesEngineImpl.java` - Load additional facts
- `service.component.agent.impl/src/test/java/.../` - Add unit tests for new rules
- `DROOLS_RISK_RULES.md` - Document new rules

#### Approach

1. **Add helper methods to PoliticianComplianceCheckImpl:**
   ```java
   public class PoliticianComplianceCheckImpl implements Serializable {
       // Existing fields...
       
       /**
        * Calculate ratio of committee leadership days to total committee days.
        * @return ratio between 0.0 and 1.0
        */
       public double getCommitteeLeadershipRatio() {
           if (totalCommitteeDays == 0) return 0.0;
           return (double) committeeLeadershipDays / totalCommitteeDays;
       }
       
       /**
        * Get annual absence rate from summary data.
        * @param year the year to check
        * @return absence rate as percentage (0-100)
        */
       public double getAnnualAbsenceRate(int year) {
           // Calculate from annualSummary data
           return annualSummaries.stream()
               .filter(s -> s.getYear() == year)
               .mapToDouble(s -> s.getAbsencePercentage())
               .findFirst()
               .orElse(0.0);
       }
       
       /**
        * Check if politician holds multiple active leadership roles.
        * @return true if >1 active leadership role
        */
       public boolean hasMultipleActiveLeadershipRoles() {
           return activeLeadershipRoles > 1;
       }
       
       /**
        * Get annual document count (motions, proposals, etc).
        * @param year the year to check
        * @return number of documents filed in the year
        */
       public int getAnnualDocumentCount(int year) {
           return annualSummaries.stream()
               .filter(s -> s.getYear() == year)
               .mapToInt(s -> s.getDocumentCount())
               .sum();
       }
       
       /**
        * Count number of party changes in political career.
        * @return number of times switched parties
        */
       public int getPartyChangeCount() {
           // Calculate from party membership history
           return partyMembershipHistory.size() - 1;
       }
   }
   ```

2. **Create Drools rule (example - Rule #1: High Leadership Ratio):**
   ```drl
   package com.hack23.cia.service.component.agent.impl.rules;
   
   import com.hack23.cia.model.internal.application.data.impl.PoliticianComplianceCheckImpl;
   import com.hack23.cia.model.internal.application.data.impl.RuleViolation;
   
   rule "Politician with Extremely High Committee Leadership Ratio"
       when
           $politician : PoliticianComplianceCheckImpl(
               committeeLeadershipRatio > 0.70,
               totalCommitteeDays > 365
           )
       then
           RuleViolation violation = new RuleViolation();
           violation.setRuleName("High Committee Leadership Ratio");
           violation.setSeverity("INFO");
           violation.setDescription(
               $politician.getName() + " holds committee leadership positions " +
               Math.round($politician.getCommitteeLeadershipRatio() * 100) + 
               "% of the time, indicating significant influence in committee work."
           );
           violation.setPoliticianId($politician.getId());
           insert(violation);
   end
   ```

3. **Create Drools rule (example - Rule #2: Low Attendance):**
   ```drl
   rule "Politician with Extremely Low Annual Attendance"
       when
           $politician : PoliticianComplianceCheckImpl(
               getAnnualAbsenceRate(2024) > 50.0
           )
       then
           RuleViolation violation = new RuleViolation();
           violation.setRuleName("Extremely Low Attendance");
           violation.setSeverity("WARNING");
           violation.setDescription(
               $politician.getName() + " has an absence rate of " +
               Math.round($politician.getAnnualAbsenceRate(2024)) + 
               "% in 2024, suggesting lack of participation in parliamentary votes."
           );
           violation.setPoliticianId($politician.getId());
           insert(violation);
   end
   ```

4. **Add helper methods to PartyComplianceCheckImpl:**
   ```java
   public class PartyComplianceCheckImpl implements Serializable {
       
       /**
        * Calculate days since party registration.
        * @return number of days since registration date
        */
       public long daysSinceRegistered() {
           return ChronoUnit.DAYS.between(registrationDate, LocalDate.now());
       }
       
       /**
        * Get current party headcount (active members).
        * @return number of active party members
        */
       public int getPartyHeadCount() {
           return activeMembers.size();
       }
       
       /**
        * Get annual document count for the party.
        * @param year the year to check
        * @return number of documents filed by party members in the year
        */
       public int getAnnualDocumentCount(int year) {
           return annualDocumentCounts.getOrDefault(year, 0);
       }
       
       /**
        * Get annual abstention rate for the party.
        * @param year the year to check
        * @return abstention rate as percentage (0-100)
        */
       public double getAnnualAbstentionRate(int year) {
           return annualSummaries.stream()
               .filter(s -> s.getYear() == year)
               .mapToDouble(s -> s.getAbstentionPercentage())
               .findFirst()
               .orElse(0.0);
       }
   }
   ```

5. **Update RulesEngineImpl to load facts:**
   ```java
   public class RulesEngineImpl {
       
       public void executeRules() {
           KieSession kieSession = kieContainer.newKieSession();
           
           // Load existing facts
           List<PoliticianComplianceCheckImpl> politicians = 
               politicianService.getAllPoliticianComplianceChecks();
           politicians.forEach(kieSession::insert);
           
           List<PartyComplianceCheckImpl> parties = 
               partyService.getAllPartyComplianceChecks();
           parties.forEach(kieSession::insert);
           
           // Fire all rules
           kieSession.fireAllRules();
           
           // Collect violations
           Collection<RuleViolation> violations = kieSession.getObjects(
               new ClassObjectFilter(RuleViolation.class));
           
           // Store violations
           violations.forEach(ruleViolationService::save);
           
           kieSession.dispose();
       }
   }
   ```

6. **Create unit tests:**
   ```java
   public class ExtendedComplianceRulesTest {
       
       @Test
       public void testHighLeadershipRatioRule() {
           // Arrange
           PoliticianComplianceCheckImpl politician = new PoliticianComplianceCheckImpl();
           politician.setCommitteeLeadershipDays(800);
           politician.setTotalCommitteeDays(1000);  // 80% ratio
           
           // Act
           List<RuleViolation> violations = rulesEngine.evaluate(politician);
           
           // Assert
           assertTrue(violations.stream()
               .anyMatch(v -> v.getRuleName().equals("High Committee Leadership Ratio")));
       }
       
       @Test
       public void testLowAttendanceRule() {
           // Arrange
           PoliticianComplianceCheckImpl politician = new PoliticianComplianceCheckImpl();
           politician.setAnnualAbsenceRate(2024, 55.0);  // 55% absence
           
           // Act
           List<RuleViolation> violations = rulesEngine.evaluate(politician);
           
           // Assert
           assertTrue(violations.stream()
               .anyMatch(v -> v.getRuleName().equals("Extremely Low Attendance")));
       }
   }
   ```

7. **Document rules in DROOLS_RISK_RULES.md:**
   ```markdown
   ### Extended Compliance Rules
   
   #### Rule: High Committee Leadership Ratio
   **Trigger:** Politician's committee leadership days / total committee days > 70%
   **Severity:** INFO
   **Purpose:** Identifies politicians with outsized influence in committee leadership
   **Example:** "Anna Andersson holds committee leadership positions 85% of the time"
   
   #### Rule: Extremely Low Attendance
   **Trigger:** Annual absence rate > 50%
   **Severity:** WARNING
   **Purpose:** Highlights politicians with poor participation in parliamentary votes
   **Example:** "Erik Eriksson has an absence rate of 62% in 2024"
   ```

#### Performance Considerations

- Rule execution should complete in < 500ms per entity
- Use indexed fields in Drools rules for better performance
- Consider batching fact insertion for large datasets
- Monitor memory usage with many active facts

### 🔗 Related Resources

- [Issue #6885: Enhancement: Introduce Extended Compliance Rules](https://github.com/Hack23/cia/issues/6885)
- [DROOLS_RISK_RULES.md](https://github.com/Hack23/cia/blob/master/DROOLS_RISK_RULES.md)
- [Drools Documentation](https://docs.drools.org/latest/drools-docs/docs-website/drools/index.html)
- [Data Model Documentation](https://github.com/Hack23/cia/blob/master/DATA_MODEL.md)

### 📊 Metadata

**Priority:** Medium  
**Effort:** Medium (6-8 hours)  
**Impact:** Enhanced political analysis, data quality, user insights  
**Dependencies:** Issue #6885  
**Affected Modules:** service.component.agent.impl  
**Labels:** `priority:medium`, `type:feature`, `size:medium`, `domain:business-rules`, `next-release`, `enhancement`

---

## How to Create These Issues

### Option 1: Manual Creation via GitHub Web UI

1. Navigate to https://github.com/Hack23/cia/issues/new
2. Copy the title and body from each issue above
3. Add the labels specified in the Metadata section
4. Click "Submit new issue"
5. Repeat for all 5 issues

### Option 2: Using GitHub CLI

If you have `gh` CLI configured with authentication:

```bash
# Issue #1
gh issue create --repo Hack23/cia \
  --title "🔐 Upgrade Spring Framework to 5.3.42 for Security Patches" \
  --body-file /home/runner/work/cia/cia/NEXT_RELEASE_ISSUES.md \
  --label "priority:critical,type:security,size:small,domain:backend,next-release"

# Repeat for issues #2-5
```

### Option 3: Using GitHub API

With a GitHub personal access token:

```bash
export GITHUB_TOKEN="your_token_here"

curl -X POST https://api.github.com/repos/Hack23/cia/issues \
  -H "Authorization: token $GITHUB_TOKEN" \
  -H "Content-Type: application/json" \
  -d @issue1.json
```

---

## Priority Scoring Methodology

Issues were prioritized using the formula:
**Priority Score = (Impact × 2) + Urgency + Effort Bonus**

Where:
- Impact: 1-5 (5 = Critical security/stability)
- Urgency: 1-5 (5 = Immediate, 3 = Current sprint)
- Effort Bonus: S=+3, M=+2, L=+1, XL=0

### Issue Scores

| Issue | Impact | Urgency | Effort | Bonus | Score | Rank |
|-------|--------|---------|--------|-------|-------|------|
| #1: Spring Upgrade | 5 (Security) | 5 (Immediate) | S | +3 | 18 | 1 |
| #2: Test Coverage | 4 (Quality) | 4 (High) | M | +2 | 14 | 2 |
| #3: DB Performance | 4 (Performance) | 4 (High) | M | +2 | 14 | 2 |
| #4: Documentation | 3 (UX) | 3 (Medium) | S | +3 | 12 | 4 |
| #5: Drools Rules | 3 (Feature) | 3 (Medium) | M | +2 | 11 | 5 |

All 5 issues are sized Small (1-4h) or Medium (6-8h) for achievability in a single sprint.

---

## Estimated Timeline

Assuming a single developer working on these issues:

- **Week 1:**
  - Day 1-2: Issue #1 (Spring Upgrade) - 4 hours
  - Day 3-4: Issue #4 (Documentation) - 4 hours
  - Day 5: Issue #2 (Test Coverage) start - 2 hours

- **Week 2:**
  - Day 1-2: Issue #2 (Test Coverage) complete - 6 hours
  - Day 3-4: Issue #3 (DB Performance) - 8 hours
  - Day 5: Issue #5 (Drools Rules) start - 2 hours

- **Week 3:**
  - Day 1-2: Issue #5 (Drools Rules) complete - 6 hours
  - Day 3: Integration testing and bug fixes
  - Day 4-5: Final testing and documentation updates

**Total Estimated Effort:** 32-36 hours (4-5 days of focused work)

---

## Success Metrics

After completing all 5 issues:

- [ ] Zero critical security vulnerabilities from dependency scans
- [ ] Test coverage ≥ 75% in core service modules
- [ ] Dashboard query count reduced by ≥ 50%
- [ ] New developer onboarding time reduced from 2 days to 4 hours
- [ ] 15 new Drools rules active and generating insights
- [ ] All CI/CD builds passing with new standards enforced
- [ ] SonarCloud Quality Gate: PASSED

---

*Document generated: 2025-11-13*  
*For: Citizen Intelligence Agency - Next Minor Release*  
*Repository: https://github.com/Hack23/cia*
