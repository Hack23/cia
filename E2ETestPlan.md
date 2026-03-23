# 🌐 Citizen Intelligence Agency - End-to-End Test Plan

[![E2E Tests](https://img.shields.io/badge/E2E%20Tests-22%20Test%20Classes-brightgreen?style=flat-square&logo=java)](https://github.com/Hack23/cia/tree/master/citizen-intelligence-agency/src/test)
[![Integration Tests](https://img.shields.io/badge/Integration-Selenium%20WebDriver-success?style=flat-square&logo=selenium&logoColor=white)](https://github.com/Hack23/cia/tree/master/citizen-intelligence-agency/src/test/java/com/hack23/cia/systemintegrationtest)
[![Test Framework](https://img.shields.io/badge/Framework-Spring%20Test%20%2B%20JUnit-blue?style=flat-square&logo=spring&logoColor=white)](https://docs.spring.io/spring-framework/reference/testing/integration.html)
[![Coverage](https://img.shields.io/badge/Coverage-JaCoCo-orange?style=flat-square&logo=java)](https://hack23.github.io/cia/jacoco/)
[![E2E Plan](https://img.shields.io/badge/E2E%20Plan-Documentation-blue?style=flat-square&logo=markdown&logoColor=white)](https://github.com/Hack23/cia/blob/master/E2ETestPlan.md)

## 🎯 E2E Testing Objectives

The Citizen Intelligence Agency implements comprehensive end-to-end testing to ensure all critical user journeys function correctly across the full technology stack. Our E2E testing strategy focuses on:

- **100% critical path coverage** for essential user journeys and workflows
- **Cross-browser validation** using Selenium WebDriver for major platforms
- **Integration validation** across Spring MVC, Vaadin UI, and PostgreSQL database
- **Data integrity validation** for Swedish Parliament API data processing
- **Security testing** for authentication, authorization, and input validation flows
- **Performance monitoring** for page load times and response metrics
- **Regression prevention** through automated test execution on every build

## 🏗️ Testing Strategy

### Technology Stack

Our E2E testing infrastructure leverages enterprise-grade Java testing frameworks:

| Component | Technology | Version | Purpose |
|-----------|-----------|---------|---------|
| **Test Framework** | JUnit 5 | Latest | Test execution and assertions |
| **Spring Test** | Spring Framework 5.x | Latest | Integration test support and context management |
| **UI Automation** | Selenium WebDriver | 4.38.0 | Browser automation and UI interaction |
| **Build Tool** | Maven | 3.9.14+ | Test compilation and execution |
| **CI/CD** | GitHub Actions | Latest | Automated test execution on commits |
| **Database** | PostgreSQL | Latest | Test data persistence |
| **Application Server** | Embedded Jetty | Latest | Test application deployment |

### Test Organization

Tests are organized by user role and functional area:

```
citizen-intelligence-agency/src/test/java/com/hack23/cia/systemintegrationtest/
├── admin/                      # Administrator functionality tests (4 test classes)
│   ├── configuration/          # System configuration tests
│   ├── data/                   # Data management tests
│   ├── operations/             # Administrative operations tests
│   └── security/               # Security administration tests
├── user/                       # End-user functionality tests (18 test classes)
│   ├── home/                   # User home and account tests
│   ├── politician/             # Politician browsing and analysis tests
│   ├── party/                  # Political party tests
│   ├── committee/              # Parliamentary committee tests
│   ├── document/               # Document viewing tests
│   ├── documents/              # Document search tests
│   ├── docsearch/              # Advanced document search tests
│   ├── ballot/                 # Voting record tests
│   ├── parliament/             # Parliament overview tests
│   ├── ministry/               # Ministry tests
│   ├── governmentbody/         # Government body tests
│   ├── country/                # Country comparison tests
│   └── common/                 # Common user functionality tests
└── ui/                         # UI test utilities and helpers
    ├── UserPageVisit.java      # Page navigation and interaction utilities
    ├── WebDriverFactory.java   # Browser instance management
    └── TestConstants.java      # Test configuration constants
```

**Total Test Suite:** 22 test classes in IntegrationTestSuiteITest (18 user + 4 admin) covering all critical user journeys

## 📋 Critical User Journeys

### 1. User Registration & Authentication Flow

**Journey**: Anonymous User → Registration → Email Verification → Login → Dashboard

**Implementation**: `UserHomeTest.java`

**Test Scenarios**:
- ✅ New user registration with valid credentials
- ✅ Login with correct username and password
- ✅ Login with incorrect credentials (negative test)
- ✅ Password change functionality with current password validation
- ✅ Password change failure with incorrect current password
- ✅ Google Authenticator 2FA enablement
- ✅ Google Authenticator 2FA disablement
- ✅ Session management and timeout handling
- ✅ User account deletion workflow

**Key Test Methods**:
```java
@Test(timeout = DEFAULT_TIMEOUT)
public void siteLoginUserChangePasswordTest() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void siteLoginUserChangePasswordFailureTest() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void siteLoginUserDisableGoogleAuthenticatorFailureTest() throws Exception
```

**Test Coverage**: 16 test scenarios covering authentication and account security

### 2. Dashboard Navigation & User Home

**Journey**: Login → User Dashboard → View Account Activity → Navigate Features

**Implementation**: `UserHomeTest.java`, `UserCommonTest.java`

**Test Scenarios**:
- ✅ Dashboard loads with correct user information
- ✅ User account menu navigation
- ✅ Security settings access
- ✅ User events history view
- ✅ User page visit history tracking
- ✅ Navigation to different platform sections
- ✅ Menu item functionality validation

**Key Test Methods**:
```java
@Test(timeout = DEFAULT_TIMEOUT)
public void siteLoginUserCheckUserEventsTest() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void siteLoginUserCheckUserVisitsTest() throws Exception
```

**Test Coverage**: 7 test scenarios covering dashboard and navigation

### 3. Political Data Browsing - Politicians

**Journey**: Dashboard → Politicians List → Politician Profile → Document History → Voting Records

**Implementation**: `UserPoliticianTest.java`, `UserPoliticianRankingTest.java`

**Test Scenarios**:
- ✅ Browse politician list with pagination
- ✅ View detailed politician profile (overview page)
- ✅ Access document history for specific politician
- ✅ View document activity timeline
- ✅ Review voting history and records
- ✅ Analyze role summary and committee memberships
- ✅ View performance indicators and metrics
- ✅ Navigate politician ranking pages
- ✅ Filter and sort politician data

**Key Test Methods**:
```java
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyPoliticianOverviewPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyPoliticianDocumentHistoryPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyPoliticianVoteHistoryPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyPoliticianIndicatorsPage() throws Exception
```

**Test Coverage**: 9+ test scenarios covering politician data browsing

### 4. Political Data Browsing - Parties & Committees

**Journey**: Dashboard → Party/Committee List → Detailed View → Members → Historical Data

**Implementation**: `UserPartyTest.java`, `UserPartyRankingTest.java`, `UserCommitteeTest.java`, `UserCommitteeRankingTest.java`

**Test Scenarios**:

**Political Parties**:
- ✅ View party overview and general information
- ✅ Access party role Gantt chart visualization
- ✅ Review member history over time
- ✅ View page visit history analytics
- ✅ Analyze voting trends for party
- ✅ Review leader history timeline
- ✅ Access party ranking comparisons
- ✅ View party indicators and metrics

**Parliamentary Committees**:
- ✅ Browse committee list
- ✅ View committee overview pages
- ✅ Access committee member rosters
- ✅ Review committee ballots and votes
- ✅ View committee ranking data
- ✅ Analyze committee decision history

**Key Test Methods**:
```java
// Party Tests
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyPartyOverviewPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyPartyRoleGhantPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyPartyMemberHistoryPage() throws Exception

// Committee Tests
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyCommitteeOverviewPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyCommitteeMemberHistoryPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyCommitteeBallotPage() throws Exception
```

**Test Coverage**: 14+ test scenarios covering parties and committees

### 5. Parliamentary Documents & Search

**Journey**: Dashboard → Documents → Search → Filter → Document Details

**Implementation**: `UserDocumentsTest.java`, `UserDocumentTest.java`, `UserDocumentSearchTest.java`

**Test Scenarios**:
- ✅ Browse document list with pagination
- ✅ Global search across all document types
- ✅ Advanced document search with filters
- ✅ View detailed document information
- ✅ Access document attachments
- ✅ Review document processing status
- ✅ Navigate document references and links
- ✅ Filter documents by type, date, author
- ✅ Sort documents by various criteria

**Key Test Methods**:
```java
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyDocumentOverviewPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyDocumentReferencesPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyDocumentDetailsPage() throws Exception
```

**Test Coverage**: 9+ test scenarios covering document browsing and search

### 6. Government Bodies & Ministries

**Journey**: Dashboard → Government Bodies/Ministries → Detailed View → Financial Data

**Implementation**: `UserGovernmentBodyTest.java`, `UserGovernmentBodyRankingTest.java`, `UserMinistryTest.java`, `UserMinistryRankingTest.java`

**Test Scenarios**:
- ✅ Browse government body list
- ✅ View government body overview
- ✅ Access headcount data and trends
- ✅ Review financial expenditure data
- ✅ View annual expenditure comparisons
- ✅ Browse ministry list and rankings
- ✅ View ministry overview pages
- ✅ Access ministry member rosters
- ✅ Analyze ministry role assignments

**Key Test Methods**:
```java
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyGovernmentBodyOverviewPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyGovernmentBodyHeadcountPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyMinistryOverviewPage() throws Exception
```

**Test Coverage**: 9+ test scenarios covering government entities

### 7. Voting Records & Ballots

**Journey**: Dashboard → Ballots → Ballot Details → Individual Votes

**Implementation**: `UserBallotTest.java`

**Test Scenarios**:
- ✅ Browse ballot list by date
- ✅ View ballot overview and summary
- ✅ Access detailed voting records
- ✅ Review individual politician votes
- ✅ Analyze voting patterns by party
- ✅ View ballot decision summary

**Key Test Methods**:
```java
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyBallotOverviewPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyBallotVoteListPage() throws Exception
```

**Test Coverage**: 6+ test scenarios covering voting records

### 8. Parliament & Country Overview

**Journey**: Dashboard → Parliament Overview → Country Comparisons → Economic Data

**Implementation**: `UserParliamentTest.java`, `UserCountryTest.java`

**Test Scenarios**:
- ✅ View parliament overview and statistics
- ✅ Access parliamentary member data
- ✅ Review country economic indicators
- ✅ View World Bank data integration
- ✅ Compare Sweden with other countries
- ✅ Analyze long-term trends

**Key Test Methods**:
```java
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyParliamentOverviewPage() throws Exception
@Test(timeout = DEFAULT_TIMEOUT)
public void verifyCountryOverviewPage() throws Exception
```

**Test Coverage**: 6+ test scenarios covering parliament and country data

### 9. Administrator Functions

**Journey**: Admin Login → System Configuration → Data Management → Security Settings

**Implementation**: `AdminConfigurationTest.java`, `AdminDataTest.java`, `AdminOperationsTest.java`, `AdminSecurityTest.java`

**Test Scenarios**:
- ✅ Admin authentication and authorization
- ✅ System configuration updates
- ✅ Data management operations
- ✅ User account administration
- ✅ Security policy configuration
- ✅ System monitoring and health checks
- ✅ Bulk data operations
- ✅ Search index management

**Test Coverage**: 8+ test scenarios covering administrative functions

## 🛠️ Test Infrastructure

### Test Base Classes

**AbstractUITest.java**: Base class for all UI integration tests
- Manages WebDriver lifecycle
- Provides common test utilities
- Handles test setup and teardown
- Configures default timeouts
- Manages test data cleanup

**AbstractRoleSystemITest.java**: Base class for role-based tests
- Extends Spring integration test support
- Provides authentication helpers
- Manages user session state

**UserPageVisit.java**: Centralized page navigation and interaction utilities
```java
public void visitDirectPage(String pageCommand)
public void registerNewUser(String username, String password)
public void verifyViewContent(String title, String subtitle, String description)
public void validatePage(String expectedUrl)
public WebElement getMenuItem(String menuItemText)
public void performClickAction(WebElement element)
public void changePassword(String currentPassword, String newPassword, String confirmPassword)
public void checkNotificationMessage(String expectedMessage)
```

### WebDriver Configuration

**Browser Support**: 
- Primary: Chrome (via ChromeDriver)
- Configurable via `WebDriverFactory.java`
- Headless mode support for CI/CD environments
- Selenium WebDriver 4.38.0

**Configuration Properties**:
```properties
# System test target configuration
system.test.target.url=${TARGET_URL}
system.test.target.admin.email=${ADMIN_EMAIL}
system.test.target.admin.password=${ADMIN_PASSWORD}
```

### Test Data Management

**Data Strategy**:
- Uses embedded application server for isolated testing
- Test data includes representative Swedish parliamentary data
- Each test is independent with its own data setup
- Automatic cleanup after test completion
- UUID-based unique identifiers for test users

**Test User Creation**:
```java
final String username = UUID.randomUUID().toString();
final String password = TestUtils.generatePassword();
pageVisit.registerNewUser(username, password);
```

**Representative Data**:
- 50+ politician profiles from Swedish Parliament
- All active Swedish political parties
- Parliamentary committees and government bodies
- Sample voting records and documents
- World Bank economic indicator data

### Browser Compatibility

**Primary Browser**:
- **Chrome**: Latest stable version via Selenium WebDriver
- Automated updates through Selenium Manager
- Headless mode for CI/CD execution

**Responsive Testing**:
- Desktop viewport sizes (1920x1080, 1366x768)
- Tablet viewport simulation
- Mobile viewport validation
- Vaadin responsive layout testing

**Cross-Browser Testing** (manual/optional):
- **Firefox**: Latest stable (manual testing)
- **Safari**: Latest stable (manual testing)
- **Edge**: Latest stable (Chromium-based, compatible with Chrome tests)

## 📊 Test Data Strategy

### Data Requirements

**User Accounts**:
- Test users created dynamically per test
- Admin test account configured via environment variables
- UUID-based usernames for uniqueness
- Strong password generation via `TestUtils`

**Political Data**:
- **Politicians**: 50+ representative profiles with historical data
- **Parties**: All Swedish political parties (8-10 active parties)
- **Committees**: All parliamentary committees (~15 committees)
- **Documents**: Sample parliamentary documents (100+ documents)
- **Ballots**: Representative voting records (50+ ballots)
- **Government Bodies**: Swedish government agencies (~250 bodies)

### Data Management Approach

**Setup**:
1. Application starts with embedded database
2. Data loading from Swedish Parliament API (cached/fixtures)
3. Test-specific data created via application UI
4. Independent data per test class

**Isolation**:
- Each test creates its own user accounts
- Tests do not share mutable data
- Database transactions for test isolation
- No cross-test dependencies

**Cleanup**:
- Automatic cleanup after test completion
- Transaction rollback for integration tests
- Test user accounts removed
- No persistent state between test runs

**Reproducibility**:
- Deterministic test data generation
- Fixed IDs for reference data (e.g., `POL_ID = "0222691314314"`)
- Consistent test fixtures
- Version-controlled test data

## 📈 Performance Assertions

### Response Time Targets

**Page Load Performance**:
| Page Type | Target Response Time | Monitoring |
|-----------|---------------------|-----------|
| Dashboard / Home | < 3 seconds | ✅ Measured via Selenium WebDriver wait times |
| Search Results | < 2 seconds | ✅ Measured via Selenium explicit waits |
| Entity Details (Politician, Party) | < 1.5 seconds | ✅ Measured via page load completion |
| Navigation / Menu | < 1 second | ✅ Measured via element availability |
| Document Lists | < 2 seconds | ✅ Measured via table rendering |

**Timeout Configuration**:
```java
/** The Constant DEFAULT_TIMEOUT = 60000 milliseconds (60 seconds) */
@Test(timeout = DEFAULT_TIMEOUT)
public void testScenario() throws Exception
```

### Performance Monitoring

**Test Execution Metrics**:
- Individual test execution time tracked by JUnit
- Build-level performance tracking via Maven Surefire
- CI/CD execution time monitoring via GitHub Actions
- Historical performance trend analysis

**WebDriver Wait Strategies**:
```java
// Explicit waits for element availability
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
wait.until(ExpectedConditions.presenceOfElementLocated(By.id("elementId")));

// Implicit waits for general page loading
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```

**Application Performance**:
- JavaMelody monitoring integration for production metrics
- Database query performance profiling
- Spring Boot Actuator health endpoints
- Memory usage validation during test execution

## 🔒 Security Testing Integration

### Security Test Scenarios

**Authentication Security** (Implemented in `UserHomeTest.java`, `AdminSecurityTest.java`):
- ✅ Password strength validation during registration
- ✅ Failed login attempt handling
- ✅ Session management and timeout
- ✅ Google Authenticator 2FA enrollment
- ✅ 2FA authentication flow
- ✅ Account lockout after failed attempts
- ✅ Password change with current password verification

**Authorization Checks**:
- ✅ Role-based access control (user vs. admin)
- ✅ Admin-only functionality protection
- ✅ User account isolation
- ✅ Unauthorized access prevention

**Input Validation**:
- ✅ XSS prevention in search inputs
- ✅ SQL injection prevention (parameterized queries)
- ✅ CSRF token validation on forms
- ✅ Input sanitization in user-generated content

**Security Test Implementation**:
```java
// Negative test: Incorrect password should fail
@Test(timeout = DEFAULT_TIMEOUT)
public void siteLoginUserChangePasswordFailureTest() throws Exception {
    pageVisit.changePassword("wrong" + password, "new" + password, "new" + password);
    pageVisit.checkNotificationMessage(ChangePasswordClickListener.PROBLEM_CHANGING_PASSWORD);
}
```

### External Security Scanning

**Automated Security Tools**:
- **CodeQL**: Static application security testing (SAST)
- **Dependency Check**: OWASP dependency vulnerability scanning
- **ZAP Scan**: Dynamic application security testing (DAST)
- **Scorecard**: OpenSSF security posture assessment

**Security Compliance**:
- ISO 27001 alignment (A.12.1.4 - Testing of security controls)
- NIST CSF (PR.IP-2 - System Development Life Cycle)
- OWASP Top 10 validation
- CRA (Cyber Resilience Act) assessment

## 🔄 CI/CD Integration

### GitHub Actions Workflow

**Test Execution in Release Pipeline** (`release.yml`):

```yaml
name: Verify and Release
on:
  workflow_dispatch:
    inputs:
      release:
        description: 'Version to release'
        required: true

jobs:
  release:
    name: Release
    runs-on: ubuntu-24.04
    timeout-minutes: 60
    
    steps:
    - name: Checkout repository
      uses: actions/checkout@v5.0.0
      
    - name: Set up JDK 26
      uses: actions/setup-java@v5.0.0
      with:
        distribution: 'temurin'
        java-version: '26'
        cache: 'maven'
        
    - name: Build and Test
      run: mvn -B clean install --file pom.xml -Prelease-site,all-modules -DforkMode=once
```

**Test Execution Profile**:
```bash
# Full test suite execution
mvn clean test

# Integration tests only
mvn verify -Pintegration-tests

# Skip tests (for documentation-only builds)
mvn clean install -DskipTests

# Specific test execution
mvn test -Dtest=UserHomeTest
```

### Test Artifact Publishing

**JUnit Test Reports**:
- Generated by Maven Surefire Plugin
- Published to `target/surefire-reports/`
- XML format for CI/CD integration
- HTML reports for human review

**JaCoCo Coverage Reports**:
- Code coverage tracked per module
- Published to GitHub Pages: https://hack23.github.io/cia/jacoco/
- Line coverage and branch coverage metrics
- Historical coverage trend tracking

**Test Execution Artifacts**:
- Test logs captured in CI/CD
- Screenshot capture on test failures (Selenium)
- Browser console logs for debugging
- Stack traces for failed assertions

### Continuous Testing Strategy

**Automated Test Triggers**:
- ✅ Every commit to master branch
- ✅ Every pull request (PR validation)
- ✅ Release workflow execution
- ✅ Manual workflow dispatch (on-demand testing)

**Test Execution Environment**:
- **OS**: Ubuntu 24.04 (latest stable)
- **Java**: OpenJDK 26 (Temurin distribution)
- **Maven**: Latest stable version
- **Browser**: Chrome (headless mode via Selenium Manager)
- **Database**: PostgreSQL (embedded or containerized)

**Failure Handling**:
- Test failures block PR merge
- Build notifications sent to development team
- Automatic retry for flaky test detection
- Detailed failure logs attached to workflow run

## 📊 Test Coverage Goals

### Current Test Coverage Status

| Category | Test Classes | Test Scenarios | Coverage Status |
|----------|--------------|----------------|-----------------|
| **User Authentication** | 1 | 9 scenarios | ✅ 100% |
| **Dashboard & Navigation** | 1 | 7 scenarios | ✅ 100% |
| **Politician Data** | 2 | 9+ scenarios | ✅ 100% |
| **Party & Committee** | 4 | 14+ scenarios | ✅ 100% |
| **Documents & Search** | 3 | 9+ scenarios | ✅ 100% |
| **Government Bodies** | 4 | 9+ scenarios | ✅ 100% |
| **Voting Records** | 1 | 6+ scenarios | ✅ 100% |
| **Parliament & Country** | 2 | 6+ scenarios | ✅ 100% |
| **Admin Functions** | 4 | 8+ scenarios | ✅ 100% |
| **Common Functionality** | 1 | 5+ scenarios | ✅ 100% |

**Overall E2E Test Coverage**: 
- **Total Test Classes in IntegrationTestSuiteITest**: 22 (18 user + 4 admin)
- **Total Test Files in Repository**: 46 (includes unit tests across all modules)
- **Total Test Scenarios**: 80+ individual test methods
- **Critical Path Coverage**: 100% of identified user journeys
- **Code Coverage (via JaCoCo)**: Available at https://hack23.github.io/cia/jacoco/

### Coverage Metrics

**Integration Test Categories**:
- **UI Integration Tests**: 18 test classes covering all user-facing pages
- **Admin Integration Tests**: 4 test classes covering administrative functions
- **Unit Tests**: 100+ unit test classes across service and model modules

**Test Execution Statistics**:
- **Average Test Execution Time**: ~60 minutes (full suite)
- **Individual Test Timeout**: 60 seconds per test
- **Test Success Rate**: 99%+ (CI/CD monitoring)
- **Flaky Test Rate**: <1%

## 🎯 Test Scenarios by Category

### User Journey Test Matrix

| User Journey | Test Class | Test Count | Critical Path |
|-------------|------------|------------|---------------|
| User Registration & Login | `UserHomeTest` | 9 | ✅ Critical |
| User Account Management | `UserHomeTest` | 6 | ✅ Critical |
| Politician Browsing | `UserPoliticianTest` | 6 | ✅ Critical |
| Politician Rankings | `UserPoliticianRankingTest` | 3 | 🟡 Important |
| Party Browsing | `UserPartyTest` | 6 | ✅ Critical |
| Party Rankings | `UserPartyRankingTest` | 3 | 🟡 Important |
| Committee Browsing | `UserCommitteeTest` | 5 | ✅ Critical |
| Committee Rankings | `UserCommitteeRankingTest` | 3 | 🟡 Important |
| Document Viewing | `UserDocumentTest` | 5 | ✅ Critical |
| Document Search | `UserDocumentSearchTest` | 2 | ✅ Critical |
| Document Browsing | `UserDocumentsTest` | 2 | 🟡 Important |
| Ballot Viewing | `UserBallotTest` | 4 | ✅ Critical |
| Parliament Overview | `UserParliamentTest` | 3 | 🟡 Important |
| Ministry Browsing | `UserMinistryTest` | 4 | 🟡 Important |
| Ministry Rankings | `UserMinistryRankingTest` | 3 | 🟡 Important |
| Government Bodies | `UserGovernmentBodyTest` | 5 | 🟡 Important |
| Government Body Rankings | `UserGovernmentBodyRankingTest` | 3 | 🟡 Important |
| Country Comparison | `UserCountryTest` | 3 | 🟡 Important |
| Common Functionality | `UserCommonTest` | 3 | ✅ Critical |

### Admin Functionality Test Matrix

| Admin Function | Test Class | Test Count | Critical Path |
|---------------|------------|------------|---------------|
| System Configuration | `AdminConfigurationTest` | 2+ | ✅ Critical |
| Data Management | `AdminDataTest` | 2+ | ✅ Critical |
| Administrative Operations | `AdminOperationsTest` | 2+ | ✅ Critical |
| Security Administration | `AdminSecurityTest` | 2+ | ✅ Critical |

## 🔧 Test Infrastructure Components

### Key Test Utilities

**UserPageVisit.java** - Central navigation and interaction utility:
- Browser automation methods
- Page navigation helpers
- Form interaction utilities
- Assertion helpers
- WebDriver wait management
- Cookie and session management
- Element interaction with stale element handling

**WebDriverFactory.java** - Browser instance management:
- WebDriver initialization
- Browser configuration
- Headless mode support
- Driver lifecycle management

**ClickHelper.java** - Robust click interaction:
- Handles JavaScript-based clicks
- Stale element retry logic
- Scroll-into-view for hidden elements

**StaleElementUtils.java** - Stale element handling:
- Automatic retry on stale element exceptions
- Configurable retry attempts
- Logging for debugging

**TestConstants.java** - Test configuration constants:
- Timeout values
- Test data constants
- URL patterns
- Element identifiers

### Spring Test Integration

**Test Context Configuration**:
- Spring application context loaded for integration tests
- Embedded server started for each test suite
- Transaction management for database tests
- Dependency injection for service layer testing

**Example Test Configuration**:
```java
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Category(IntegrationTest.class)
public abstract class AbstractUITest extends AbstractSystemIntegrationTest {
    protected static final int DEFAULT_TIMEOUT = 60000; // 60 seconds
    
    @Before
    public void setUp() {
        // WebDriver initialization
        // Page navigation setup
        // Test data preparation
    }
    
    @After
    public void tearDown() {
        // Browser cleanup
        // Session termination
        // Data cleanup
    }
}
```

## 📚 Test Execution Examples

### Running E2E Tests Locally

**Full Test Suite**:
```bash
cd /path/to/cia
mvn clean test
```

**Specific Test Class**:
```bash
mvn test -Dtest=UserHomeTest
```

**Specific Test Method**:
```bash
mvn test -Dtest=UserHomeTest#siteLoginUserChangePasswordTest
```

**Integration Tests Only**:
```bash
mvn verify -Pintegration-tests
```

**With Coverage Report**:
```bash
mvn clean test jacoco:report
# View report: target/site/jacoco/index.html
```

### Test Execution Output Example

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.hack23.cia.systemintegrationtest.user.home.UserHomeTest
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 45.234 sec

Running com.hack23.cia.systemintegrationtest.user.politician.UserPoliticianTest
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 32.156 sec

Results :

Tests run: 80, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

## 🚀 Future E2E Testing Enhancements

### Planned Improvements

**Vaadin TestBench Integration**:
- Vaadin-specific UI component testing
- Visual regression testing
- Enhanced element locator strategies
- Better Vaadin component interaction

**Performance Testing**:
- JMeter or Gatling integration for load testing
- Concurrent user simulation (10, 50, 100 users)
- API endpoint performance benchmarking
- Database query performance profiling

**Visual Regression Testing**:
- Screenshot comparison for UI changes
- Percy.io or Applitools integration
- Automated visual diff reporting
- Cross-browser visual consistency

**Mobile Testing**:
- Appium integration for native mobile testing
- Responsive design validation across devices
- Touch interaction testing
- Mobile performance optimization

**Enhanced Reporting**:
- Allure Framework for enhanced test reporting
- Test execution trend analysis
- Flaky test detection and tracking
- Test duration optimization recommendations

**Accessibility Testing**:
- WCAG 2.1 compliance validation
- Axe-core integration for automated a11y testing
- Screen reader compatibility testing
- Keyboard navigation validation

## 🔗 Related Resources

### Documentation
- [Secure Development Policy - E2E Testing Requirements](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md#-end-to-end-testing-strategy)
- [CIA Architecture Documentation](https://github.com/Hack23/cia/blob/master/ARCHITECTURE.md)
- [CIA Security Architecture](https://github.com/Hack23/cia/blob/master/SECURITY_ARCHITECTURE.md)
- [ISMS Compliance Mapping](https://github.com/Hack23/cia/blob/master/ISMS_COMPLIANCE_MAPPING.md)

### Testing Frameworks & Tools
- [Spring Integration Testing](https://docs.spring.io/spring-framework/reference/testing/integration.html)
- [Selenium WebDriver Documentation](https://www.selenium.dev/documentation/webdriver/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
- [JaCoCo Coverage](https://www.jacoco.org/jacoco/trunk/doc/)

### CI/CD & Security
- [GitHub Actions Workflows](https://github.com/Hack23/cia/tree/master/.github/workflows)
- [CodeQL Analysis](https://github.com/Hack23/cia/security/code-scanning)
- [OWASP Dependency Check](https://owasp.org/www-project-dependency-check/)
- [OpenSSF Scorecard](https://scorecard.dev/)

### Test Source Code
- [Integration Test Suite](https://github.com/Hack23/cia/tree/master/citizen-intelligence-agency/src/test/java/com/hack23/cia/systemintegrationtest)
- [Unit Test Foundation](https://github.com/Hack23/cia/tree/master/testfoundation/src/main/java/com/hack23/cia/testfoundation)

## 📋 Compliance & Standards

### ISMS Policy Alignment

This E2E Test Plan fulfills requirements from:

**Secure Development Policy**:
- ✅ E2ETestPlan.md documentation requirement
- ✅ Comprehensive E2E testing strategy
- ✅ Critical path coverage documentation
- ✅ Browser compatibility testing approach
- ✅ Integration with CI/CD pipeline
- ✅ Public test results availability

**Information Security Policy**:
- ✅ Security testing integration (ISO 27001 A.12.1.4)
- ✅ Test data management and protection
- ✅ Access control validation in tests
- ✅ Authentication and authorization testing

**Quality Standards**:
- ✅ ISO 27001 (A.12.1.4) - Testing of security controls
- ✅ NIST CSF (PR.IP-2) - System Development Life Cycle testing
- ✅ CRA (Cyber Resilience Act) - Quality assurance requirements
- ✅ OWASP ASVS - Security verification through testing

### Continuous Improvement

**Review Cycle**: Quarterly review of E2E test strategy and coverage
**Next Review**: Per ISMS compliance schedule
**Metrics Tracking**: Test execution time, coverage, and success rate
**Stakeholder Updates**: Regular reporting to CEO/Founder and development team

---

## 📊 Metadata

**Document Owner:** CEO | **Version:** 1.0 | **Last Updated:** 2025-11-14  
**Review Cycle:** Quarterly | **Next Review:** 2026-02-14  
**Classification:** Public | **Compliance:** ISO 27001, NIST CSF, CRA

---

*This E2E Test Plan demonstrates Hack23 AB's commitment to quality, transparency, and comprehensive testing practices. Our end-to-end testing strategy ensures the Citizen Intelligence Agency platform maintains the highest standards of reliability, security, and user experience.*

*"Testing is not just about finding bugs—it's about building confidence in our system and demonstrating our commitment to quality democratic technology."*

*— James Pether Sörling, CEO/Founder, Hack23 AB*
