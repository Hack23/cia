---
name: secure-code-review
description: Conduct comprehensive security code reviews using OWASP Top 10, SAST/DAST patterns, and Hack23 ISMS secure development policy
license: Apache-2.0
---

# Secure Code Review Skill

## Purpose

This skill provides strategic guidance for conducting thorough security code reviews that identify vulnerabilities before they reach production. It implements defense-in-depth principles aligned with OWASP Top 10, SANS Top 25, and Hack23 ISMS Secure Development Policy.

## When to Use This Skill

Apply this skill when:
- ✅ Reviewing pull requests before merge
- ✅ Conducting periodic security audits of existing code
- ✅ Implementing new features that handle sensitive data
- ✅ Integrating third-party libraries or APIs
- ✅ Refactoring authentication/authorization logic
- ✅ Before major releases or production deployments
- ✅ After security incidents or vulnerability disclosures

Do NOT use for:
- ❌ General code style reviews (use code-quality-checks skill)
- ❌ Performance optimization (different concern)
- ❌ Business logic validation (functional testing)

## Decision Tree

```
START: Code Review Required
    │
    ├─→ Does code handle user input?
    │   ├─→ YES → Apply Input Validation Checklist
    │   └─→ NO → Continue
    │
    ├─→ Does code access database?
    │   ├─→ YES → Apply SQL Injection Prevention
    │   └─→ NO → Continue
    │
    ├─→ Does code handle authentication/authorization?
    │   ├─→ YES → Apply Authentication Security Checklist
    │   └─→ NO → Continue
    │
    ├─→ Does code process sensitive data?
    │   ├─→ YES → Apply Data Protection Checklist
    │   └─→ NO → Continue
    │
    ├─→ Does code interact with external systems?
    │   ├─→ YES → Apply API Security Checklist
    │   └─→ NO → Continue
    │
    └─→ Run SAST tools (SonarCloud, CodeQL)
        └─→ Document findings and mitigation
```

## OWASP Top 10 Security Review Checklist

### A01:2021 – Broken Access Control

**What to Check:**
- ✅ Verify authorization checks exist before resource access
- ✅ Ensure users cannot access resources outside their permissions
- ✅ Check for insecure direct object references (IDOR)
- ✅ Validate that horizontal and vertical privilege escalation is prevented
- ✅ Confirm Spring Security annotations (@PreAuthorize, @Secured) are correctly applied

**Code Patterns:**

✅ **SECURE - Proper Authorization Check:**
```java
@Service
public class DocumentService {
    @Autowired
    private SecurityContext securityContext;
    
    @PreAuthorize("hasRole('USER')")
    public Document getDocument(Long documentId) {
        Document doc = documentRepository.findById(documentId)
            .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
        
        // Verify ownership before returning
        if (!doc.getOwnerId().equals(getCurrentUserId())) {
            throw new AccessDeniedException("Cannot access document owned by another user");
        }
        
        return doc;
    }
}
```

❌ **INSECURE - Missing Authorization:**
```java
// BAD: No authorization check, anyone can access any document
@GetMapping("/documents/{id}")
public Document getDocument(@PathVariable Long id) {
    return documentRepository.findById(id).orElse(null);
}
```

### A02:2021 – Cryptographic Failures

**What to Check:**
- ✅ Verify sensitive data is encrypted at rest and in transit
- ✅ Ensure strong encryption algorithms (AES-256, RSA-2048+)
- ✅ Check that passwords are hashed with bcrypt/argon2 (never plain text)
- ✅ Validate TLS 1.2+ is enforced for all connections
- ✅ Confirm encryption keys are managed securely (never hardcoded)

**Code Patterns:**

✅ **SECURE - Proper Password Hashing:**
```java
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder; // BCryptPasswordEncoder
    
    public void createUser(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);
        userRepository.save(user);
    }
    
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
```

❌ **INSECURE - Plain Text Password:**
```java
// BAD: Storing passwords in plain text
public void createUser(String username, String password) {
    User user = new User(username, password); // NEVER DO THIS
    userRepository.save(user);
}
```

### A03:2021 – Injection

**What to Check:**
- ✅ All SQL queries use parameterized statements or JPA criteria queries
- ✅ User input is validated before use in queries
- ✅ Command injection is prevented (no Runtime.exec with user input)
- ✅ LDAP, XML, and OS command injection vectors are addressed
- ✅ ORM queries are not constructed with string concatenation

**Code Patterns:**

✅ **SECURE - Parameterized Query:**
```java
@Repository
public interface PoliticianRepository extends JpaRepository<Politician, Long> {
    // JPA query with named parameter - safe from SQL injection
    @Query("SELECT p FROM Politician p WHERE p.firstName = :firstName AND p.lastName = :lastName")
    List<Politician> findByName(@Param("firstName") String firstName, 
                                @Param("lastName") String lastName);
}

// Using JPA Criteria API - also safe
public List<Politician> searchPoliticians(String searchTerm) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Politician> query = cb.createQuery(Politician.class);
    Root<Politician> politician = query.from(Politician.class);
    
    query.where(cb.like(politician.get("firstName"), "%" + searchTerm + "%"));
    return entityManager.createQuery(query).getResultList();
}
```

❌ **INSECURE - SQL Injection Vulnerable:**
```java
// BAD: String concatenation in query
@Query(value = "SELECT * FROM politician WHERE first_name = '" + firstName + "'", 
       nativeQuery = true)
List<Politician> findByName(String firstName); // SQL INJECTION RISK!

// BAD: Direct JDBC with concatenation
public List<Politician> search(String name) {
    String sql = "SELECT * FROM politician WHERE name = '" + name + "'";
    return jdbcTemplate.query(sql, new PoliticianMapper()); // VULNERABLE!
}
```

### A04:2021 – Insecure Design

**What to Check:**
- ✅ Security requirements defined before implementation
- ✅ Threat modeling completed for sensitive features
- ✅ Security controls are built into the architecture
- ✅ Rate limiting implemented for sensitive operations
- ✅ Business logic flaws identified and mitigated

**Secure Design Principles:**
```java
// Example: Rate limiting for login attempts
@Service
public class LoginService {
    private static final int MAX_ATTEMPTS = 5;
    private static final Duration LOCKOUT_DURATION = Duration.ofMinutes(15);
    
    private final LoadingCache<String, Integer> loginAttempts = CacheBuilder.newBuilder()
        .expireAfterWrite(LOCKOUT_DURATION)
        .build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    
    public void login(String username, String password) {
        // Check if account is locked
        int attempts = loginAttempts.get(username);
        if (attempts >= MAX_ATTEMPTS) {
            throw new AccountLockedException(
                "Account locked due to too many failed attempts. Try again in 15 minutes.");
        }
        
        // Attempt authentication
        boolean authenticated = authenticate(username, password);
        
        if (!authenticated) {
            loginAttempts.put(username, attempts + 1);
            throw new BadCredentialsException("Invalid credentials");
        }
        
        // Success - reset counter
        loginAttempts.invalidate(username);
    }
}
```

### A05:2021 – Security Misconfiguration

**What to Check:**
- ✅ Default credentials are changed
- ✅ Error messages don't leak sensitive information
- ✅ Debug mode disabled in production
- ✅ Unnecessary features/services are disabled
- ✅ Security headers configured (CSP, HSTS, X-Frame-Options)
- ✅ Dependency versions are up to date

**Configuration Checklist:**

✅ **SECURE - Production Configuration:**
```yaml
# application-production.yml
spring:
  profiles:
    active: production
  
  # Disable debug endpoints
  boot:
    admin:
      client:
        enabled: false
  
  # Secure session management
  session:
    timeout: 30m
    cookie:
      secure: true
      http-only: true
      same-site: strict
  
  # Hide implementation details
  mvc:
    throw-exception-if-no-handler-found: true
  resources:
    add-mappings: false

# Security headers
server:
  error:
    include-message: never
    include-stacktrace: never
    include-exception: false
```

❌ **INSECURE - Development Settings in Production:**
```yaml
# BAD: Debug enabled in production
spring:
  profiles:
    active: development
  
  # Exposes sensitive endpoints
  boot:
    admin:
      client:
        enabled: true
  
  # Leak stack traces
server:
  error:
    include-stacktrace: always
    include-exception: true
```

### A06:2021 – Vulnerable and Outdated Components

**What to Check:**
- ✅ All dependencies scanned with OWASP Dependency-Check
- ✅ No dependencies with known high/critical CVEs
- ✅ Dependencies are from trusted sources
- ✅ Unused dependencies are removed
- ✅ Security patches applied promptly

**Maven Security Check:**
```bash
# Run OWASP Dependency Check before adding new dependencies
mvn org.owasp:dependency-check-maven:check

# Review the report
ls -la target/dependency-check-report.html

# Fail build on high severity vulnerabilities
mvn verify -Dowasp.failOnCVSS=7
```

**pom.xml Best Practices:**
```xml
<!-- Use dependencyManagement to control versions -->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>3.2.1</version> <!-- Keep updated -->
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<!-- Enable OWASP Dependency Check -->
<plugin>
    <groupId>org.owasp</groupId>
    <artifactId>dependency-check-maven</artifactId>
    <version>9.0.7</version>
    <configuration>
        <failBuildOnCVSS>7</failBuildOnCVSS>
        <suppressionFiles>
            <suppressionFile>dependency-check-suppressions.xml</suppressionFile>
        </suppressionFiles>
    </configuration>
</plugin>
```

### A07:2021 – Identification and Authentication Failures

**What to Check:**
- ✅ Multi-factor authentication implemented for sensitive operations
- ✅ Session management is secure (secure cookies, timeout)
- ✅ Password requirements enforce strong passwords
- ✅ Credential stuffing protection (rate limiting, CAPTCHA)
- ✅ Session fixation attacks prevented

**Code Patterns:**

✅ **SECURE - Spring Security Configuration:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .failureHandler(authenticationFailureHandler())
                .successHandler(authenticationSuccessHandler())
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
                .expiredUrl("/login?expired")
            )
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt with strength 12
        return new BCryptPasswordEncoder(12);
    }
}
```

### A08:2021 – Software and Data Integrity Failures

**What to Check:**
- ✅ Deserialization of untrusted data is prevented
- ✅ Code signing and integrity verification in CI/CD
- ✅ Dependency checksums verified
- ✅ No auto-updates without integrity verification
- ✅ Git commits are signed

**Secure Deserialization:**
```java
// Use allowlist-based deserialization
@Bean
public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    
    // Enable default typing only for specific base types
    mapper.activateDefaultTyping(
        mapper.getPolymorphicTypeValidator(),
        ObjectMapper.DefaultTyping.NON_FINAL,
        JsonTypeInfo.As.PROPERTY
    );
    
    // Disable dangerous features
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    
    return mapper;
}
```

### A09:2021 – Security Logging and Monitoring Failures

**What to Check:**
- ✅ Authentication/authorization failures are logged
- ✅ High-value transactions are audited
- ✅ Logs don't contain sensitive data (passwords, tokens)
- ✅ Log injection attacks prevented
- ✅ Alerting configured for suspicious activities

**Secure Logging Pattern:**
```java
@Component
public class SecurityAuditLogger {
    private static final Logger auditLog = LoggerFactory.getLogger("SECURITY_AUDIT");
    
    public void logAuthenticationSuccess(String username, String ipAddress) {
        auditLog.info("Authentication successful - User: {}, IP: {}", 
            sanitizeForLog(username), 
            sanitizeForLog(ipAddress));
    }
    
    public void logAuthenticationFailure(String username, String ipAddress, String reason) {
        auditLog.warn("Authentication failed - User: {}, IP: {}, Reason: {}", 
            sanitizeForLog(username), 
            sanitizeForLog(ipAddress),
            reason);
    }
    
    public void logAccessDenied(String username, String resource) {
        auditLog.warn("Access denied - User: {}, Resource: {}", 
            sanitizeForLog(username), 
            sanitizeForLog(resource));
    }
    
    // Prevent log injection
    private String sanitizeForLog(String input) {
        if (input == null) return "null";
        return input.replaceAll("[\n\r\t]", "_");
    }
}
```

### A10:2021 – Server-Side Request Forgery (SSRF)

**What to Check:**
- ✅ URLs from user input are validated against allowlist
- ✅ Network segmentation prevents access to internal resources
- ✅ DNS rebinding attacks prevented
- ✅ Disable unused URL schemas (file://, gopher://)

**SSRF Prevention:**
```java
@Service
public class ExternalDataService {
    private static final Set<String> ALLOWED_HOSTS = Set.of(
        "api.riksdagen.se",
        "api.worldbank.org",
        "data.val.se"
    );
    
    public String fetchExternalData(String url) throws IOException {
        URL parsedUrl;
        try {
            parsedUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL", e);
        }
        
        // Validate protocol
        if (!parsedUrl.getProtocol().equals("https")) {
            throw new IllegalArgumentException("Only HTTPS URLs allowed");
        }
        
        // Validate host against allowlist
        if (!ALLOWED_HOSTS.contains(parsedUrl.getHost())) {
            throw new IllegalArgumentException("Host not in allowlist: " + parsedUrl.getHost());
        }
        
        // Fetch data with timeout
        HttpURLConnection conn = (HttpURLConnection) parsedUrl.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        
        return IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8);
    }
}
```

## SAST/DAST Integration

### CodeQL Configuration

Ensure `.github/workflows/codeql.yml` includes:
```yaml
name: "CodeQL Security Analysis"
on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]
  schedule:
    - cron: '0 0 * * 0' # Weekly scan

jobs:
  analyze:
    name: Analyze
    runs-on: ubuntu-latest
    permissions:
      actions: read
      contents: read
      security-events: write
    
    steps:
    - name: Checkout repository
      uses: actions/checkout@v4
    
    - name: Initialize CodeQL
      uses: github/codeql-action/init@v3
      with:
        languages: java
        queries: security-and-quality
    
    - name: Build
      run: mvn clean compile -DskipTests
    
    - name: Perform CodeQL Analysis
      uses: github/codeql-action/analyze@v3
```

### SonarCloud Quality Gates

Required quality gate thresholds:
- ✅ Security Rating: A (0 vulnerabilities)
- ✅ Security Hotspots: All reviewed
- ✅ Coverage: > 80%
- ✅ Duplications: < 3%
- ✅ Maintainability Rating: A

## ISMS Compliance Mapping

### ISO 27001:2022 Controls
- **A.8.1 - User Endpoint Devices**: Secure code prevents endpoint exploitation
- **A.8.2 - Privileged Access Rights**: Authorization checks enforce least privilege
- **A.8.3 - Information Access Restriction**: Access control mechanisms properly implemented
- **A.8.8 - Management of Technical Vulnerabilities**: Vulnerability scanning and remediation
- **A.8.28 - Secure Coding**: Adherence to secure coding standards

### NIST Cybersecurity Framework
- **PR.DS-6**: Integrity checking mechanisms in place
- **PR.IP-1**: Baseline security configurations
- **DE.CM-4**: Malicious code detected
- **RS.AN-5**: Processes for vulnerability response

### CIS Controls v8
- **Control 16.11**: Remediate detected vulnerabilities
- **Control 4.1**: Establish and maintain secure configuration
- **Control 16.1**: Application software security
- **Control 16.14**: Establish process for accepting application risk

## Hack23 ISMS Policy References

Review these policies before code review:
- **Secure Development Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/secure-development-policy.md
- **Vulnerability Management Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/vulnerability-management-policy.md
- **Access Control Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/access-control-policy.md

## Review Workflow

1. **Pre-Review Setup**
   ```bash
   # Checkout PR branch
   gh pr checkout <PR-NUMBER>
   
   # Run security scans
   mvn clean verify
   mvn org.owasp:dependency-check-maven:check
   
   # Review CodeQL alerts
   gh api /repos/Hack23/cia/code-scanning/alerts --jq '.[] | select(.state=="open")'
   ```

2. **Manual Code Review**
   - Apply OWASP Top 10 checklist to changed files
   - Review authentication/authorization changes
   - Verify input validation on all user inputs
   - Check for hardcoded secrets or credentials
   - Validate error handling doesn't leak sensitive info

3. **Document Findings**
   ```markdown
   ## Security Review Findings
   
   ### Critical Issues
   - [ ] SQL injection in `PoliticianController.search()` - line 45
   
   ### High Priority
   - [ ] Missing authorization check in `DocumentService.getDocument()`
   
   ### Medium Priority
   - [ ] Weak password validation in `UserRegistrationForm`
   
   ### Low Priority / Informational
   - [ ] Consider adding rate limiting to login endpoint
   ```

4. **Request Changes or Approve**
   - If critical/high issues found: Request changes with detailed explanation
   - If only medium/low: Approve with recommendations
   - Always provide actionable feedback with code examples

## Security Review Exit Criteria

Before approving a PR, verify:
- ✅ No new CodeQL alerts introduced
- ✅ SonarCloud quality gate passed
- ✅ OWASP Dependency Check shows no new high/critical CVEs
- ✅ All authentication/authorization logic reviewed
- ✅ Input validation present on all user inputs
- ✅ No secrets or credentials in code
- ✅ Security test cases added for sensitive features
- ✅ Documentation updated if security architecture changed

## References

### Official Documentation
- OWASP Top 10: https://owasp.org/www-project-top-ten/
- OWASP ASVS: https://owasp.org/www-project-application-security-verification-standard/
- CWE Top 25: https://cwe.mitre.org/top25/
- Spring Security Reference: https://docs.spring.io/spring-security/reference/

### CIA Project Documentation
- SECURITY_ARCHITECTURE.md - Security controls overview
- THREAT_MODEL.md - Identified threats and mitigations
- SECURITY.md - Vulnerability reporting process
- .github/workflows/codeql.yml - SAST configuration

### Tools
- CodeQL: https://codeql.github.com/
- SonarCloud: https://sonarcloud.io/
- OWASP Dependency Check: https://owasp.org/www-project-dependency-check/
- SpotBugs with FindSecBugs: https://find-sec-bugs.github.io/

## Success Metrics

Track these KPIs to measure secure code review effectiveness:
- Zero security vulnerabilities in production
- Mean time to remediate (MTTR) for vulnerabilities < 30 days
- 100% of PRs pass security review before merge
- Decrease in vulnerability count over time
- Security training completion rate for all contributors
