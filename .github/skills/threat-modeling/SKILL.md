---
name: threat-modeling
description: Conduct systematic threat modeling using STRIDE framework, attack trees, and security architecture analysis for CIA platform
license: Apache-2.0
---

# Threat Modeling Skill

## Purpose

This skill provides structured methodology for identifying, analyzing, and mitigating security threats in the CIA platform using industry-standard frameworks including STRIDE, PASTA, and attack tree analysis. It ensures proactive security design aligned with Hack23 ISMS requirements.

## When to Use This Skill

Apply this skill when:
- ✅ Designing new features that handle sensitive political data
- ✅ Integrating external APIs (Riksdagen, World Bank, Election Authority)
- ✅ Implementing authentication or authorization mechanisms
- ✅ Modifying security architecture or trust boundaries
- ✅ Before major releases to identify residual risks
- ✅ After security incidents to prevent recurrence
- ✅ Conducting annual security architecture reviews

Do NOT use for:
- ❌ Routine bug fixes without security implications
- ❌ UI/UX changes that don't affect security
- ❌ Performance optimizations (unless affecting security)

## STRIDE Threat Model Framework

### S - Spoofing Identity

**Threats to Consider:**
- Attackers impersonating legitimate users
- Session hijacking or fixation
- Credential theft or replay attacks
- API key theft and misuse

**CIA Platform Attack Scenarios:**
```
Threat: Attacker impersonates politician to modify profile data
├─ Entry Point: User authentication endpoint (/login)
├─ Attack Vector: Credential stuffing with breached password databases
├─ Impact: Reputational damage, data integrity loss
└─ Mitigation:
    ├─ Implement rate limiting (5 attempts per 15 minutes)
    ├─ Enable 2FA for sensitive accounts
    ├─ Monitor for suspicious login patterns
    └─ Enforce strong password policy (12+ chars, complexity)
```

**Mitigation Checklist:**
- ✅ Multi-factor authentication for privileged accounts
- ✅ Certificate-based authentication for API clients
- ✅ Strong password policies (bcrypt with cost factor 12)
- ✅ Session tokens with HMAC integrity protection
- ✅ JWT signature verification for API tokens

**Code Pattern:**
```java
@Service
public class AuthenticationService {
    
    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static final Duration LOCKOUT_PERIOD = Duration.ofMinutes(15);
    
    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    public AuthenticationToken authenticate(LoginRequest request) {
        String username = request.getUsername();
        
        // Check if account is locked due to failed attempts
        if (loginAttemptService.isLocked(username)) {
            throw new AccountLockedException(
                "Account temporarily locked. Try again in " + 
                loginAttemptService.getTimeUntilUnlock(username) + " minutes"
            );
        }
        
        try {
            // Authenticate user
            UserDetails user = userDetailsService.loadUserByUsername(username);
            
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                loginAttemptService.recordFailedAttempt(username);
                throw new BadCredentialsException("Invalid credentials");
            }
            
            // Success - reset attempt counter
            loginAttemptService.resetAttempts(username);
            
            // Generate secure token
            return tokenService.generateToken(user);
            
        } catch (UsernameNotFoundException e) {
            // Don't reveal if username exists
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
```

### T - Tampering with Data

**Threats to Consider:**
- Modification of politician voting records
- Alteration of financial data or reports
- Database injection attacks
- Parameter tampering in HTTP requests

**CIA Platform Attack Scenarios:**
```
Threat: Attacker modifies politician absence rate to damage reputation
├─ Entry Point: REST API endpoint /api/politicians/{id}/data
├─ Attack Vector: SQL injection via unvalidated input parameters
├─ Impact: Data integrity compromise, false political analysis
└─ Mitigation:
    ├─ Use JPA parameterized queries exclusively
    ├─ Implement data integrity checks (checksums, timestamps)
    ├─ Enable database audit logging
    └─ Apply digital signatures to critical data
```

**Mitigation Checklist:**
- ✅ All database queries use parameterized statements
- ✅ Input validation on all user-provided data
- ✅ Data integrity checks (checksums, digital signatures)
- ✅ Database audit logging enabled
- ✅ CSRF protection on all state-changing operations
- ✅ Immutable data structures for sensitive information

**Code Pattern:**
```java
@Entity
@Table(name = "politician_voting_record")
@Audited // Hibernate Envers for audit trail
public class VotingRecord {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String politicianId;
    
    @Column(nullable = false)
    private String voteType;
    
    @Column(nullable = false)
    private LocalDateTime voteDate;
    
    // Integrity protection - computed hash of critical fields
    @Column(name = "data_hash")
    private String dataHash;
    
    @PrePersist
    @PreUpdate
    private void calculateHash() {
        this.dataHash = DigestUtils.sha256Hex(
            politicianId + voteType + voteDate.toString()
        );
    }
    
    public boolean verifyIntegrity() {
        String expectedHash = DigestUtils.sha256Hex(
            politicianId + voteType + voteDate.toString()
        );
        return expectedHash.equals(this.dataHash);
    }
}

@Repository
public interface VotingRecordRepository extends JpaRepository<VotingRecord, Long> {
    // Safe: JPA query with named parameters
    @Query("SELECT v FROM VotingRecord v WHERE v.politicianId = :politicianId " +
           "AND v.voteDate BETWEEN :startDate AND :endDate")
    List<VotingRecord> findByPoliticianAndDateRange(
        @Param("politicianId") String politicianId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
```

### R - Repudiation

**Threats to Consider:**
- Users denying actions they performed
- Lack of audit trails for sensitive operations
- Missing transaction logs
- Insufficient evidence for security investigations

**CIA Platform Attack Scenarios:**
```
Threat: Administrator denies modifying user permissions
├─ Entry Point: Admin panel /admin/users/{id}/permissions
├─ Attack Vector: No audit logging of administrative actions
├─ Impact: Accountability loss, inability to investigate incidents
└─ Mitigation:
    ├─ Implement comprehensive audit logging
    ├─ Log all authentication and authorization events
    ├─ Include timestamps, user identity, IP address, action details
    └─ Store logs in tamper-proof storage (write-once)
```

**Mitigation Checklist:**
- ✅ Comprehensive audit logging for all sensitive operations
- ✅ Logs include: timestamp, user, IP, action, outcome
- ✅ Logs stored in tamper-resistant storage
- ✅ Log retention policy enforced (minimum 1 year)
- ✅ Regular log review and analysis
- ✅ Digital signatures on critical transactions

**Code Pattern:**
```java
@Component
@Aspect
public class AuditLoggingAspect {
    
    private static final Logger auditLog = LoggerFactory.getLogger("AUDIT");
    
    @Autowired
    private AuditLogRepository auditLogRepository;
    
    @Around("@annotation(Audited)")
    public Object logAuditEvent(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
        // Capture context
        String username = SecurityContextHolder.getContext()
            .getAuthentication().getName();
        String ipAddress = RequestContextHolder.currentRequestAttributes()
            .getSessionId();
        
        AuditLogEntry entry = new AuditLogEntry();
        entry.setTimestamp(Instant.now());
        entry.setUsername(username);
        entry.setIpAddress(ipAddress);
        entry.setAction(className + "." + methodName);
        entry.setParameters(Arrays.toString(joinPoint.getArgs()));
        
        try {
            // Execute the method
            Object result = joinPoint.proceed();
            
            entry.setOutcome("SUCCESS");
            entry.setResult(result != null ? result.toString() : "void");
            
            return result;
            
        } catch (Exception e) {
            entry.setOutcome("FAILURE");
            entry.setErrorMessage(e.getMessage());
            throw e;
            
        } finally {
            // Always log, even on failure
            auditLogRepository.save(entry);
            auditLog.info("Audit: {}", entry.toJson());
        }
    }
}

@Entity
@Table(name = "audit_log")
public class AuditLogEntry {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false, updatable = false)
    private Instant timestamp;
    
    @Column(nullable = false, updatable = false)
    private String username;
    
    @Column(updatable = false)
    private String ipAddress;
    
    @Column(nullable = false, updatable = false)
    private String action;
    
    @Column(updatable = false, columnDefinition = "TEXT")
    private String parameters;
    
    @Column(nullable = false, updatable = false)
    private String outcome;
    
    // Immutable - prevent tampering
    @PreUpdate
    private void preventUpdate() {
        throw new IllegalStateException("Audit log entries cannot be modified");
    }
}
```

### I - Information Disclosure

**Threats to Consider:**
- Exposure of personal identifiable information (PII)
- Leakage of API keys or credentials
- Verbose error messages revealing system details
- Insufficient access controls on sensitive data

**CIA Platform Attack Scenarios:**
```
Threat: Attacker accesses politician personal contact information
├─ Entry Point: Public API endpoint /api/politicians/{id}
├─ Attack Vector: Insufficient access control, over-fetching data
├─ Impact: GDPR violation, privacy breach
└─ Mitigation:
    ├─ Implement field-level access control
    ├─ Return only public data in API responses
    ├─ Redact or mask sensitive fields (phone, email)
    └─ Log all access to PII for audit purposes
```

**Mitigation Checklist:**
- ✅ Sensitive data encrypted at rest (database encryption)
- ✅ TLS 1.2+ for all data in transit
- ✅ Error messages sanitized (no stack traces in production)
- ✅ Access control on all data endpoints
- ✅ Data classification and handling procedures
- ✅ PII minimization and purpose limitation

**Code Pattern:**
```java
@RestController
@RequestMapping("/api/politicians")
public class PoliticianController {
    
    @GetMapping("/{id}")
    public ResponseEntity<PoliticianDTO> getPolitician(
            @PathVariable String id,
            @AuthenticationPrincipal UserDetails currentUser) {
        
        Politician politician = politicianService.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Politician not found"));
        
        // Apply field-level filtering based on user role
        PoliticianDTO dto = mapToDTO(politician, currentUser);
        
        // Log PII access for audit
        auditLogger.logPIIAccess(currentUser.getUsername(), "Politician", id);
        
        return ResponseEntity.ok(dto);
    }
    
    private PoliticianDTO mapToDTO(Politician politician, UserDetails user) {
        PoliticianDTO dto = new PoliticianDTO();
        
        // Always include public information
        dto.setFirstName(politician.getFirstName());
        dto.setLastName(politician.getLastName());
        dto.setParty(politician.getParty());
        dto.setDistrict(politician.getDistrict());
        
        // Only include sensitive data for authorized users
        if (hasRole(user, "ADMIN") || hasRole(user, "RESEARCHER")) {
            // Redact instead of exposing full data
            dto.setEmail(redactEmail(politician.getEmail()));
            dto.setPhone(redactPhone(politician.getPhone()));
        }
        
        return dto;
    }
    
    private String redactEmail(String email) {
        if (email == null) return null;
        int atIndex = email.indexOf('@');
        if (atIndex > 2) {
            return email.substring(0, 2) + "***" + email.substring(atIndex);
        }
        return "***" + email.substring(atIndex);
    }
}

@Configuration
public class SecurityHeadersConfig {
    
    @Bean
    public SecurityFilterChain securityHeaders(HttpSecurity http) throws Exception {
        http.headers(headers -> headers
            .contentSecurityPolicy("default-src 'self'; script-src 'self'; style-src 'self'")
            .xssProtection()
            .frameOptions().deny()
            .httpStrictTransportSecurity()
                .maxAgeInSeconds(31536000)
                .includeSubDomains(true)
        );
        return http.build();
    }
}
```

### D - Denial of Service

**Threats to Consider:**
- Resource exhaustion attacks (CPU, memory, disk)
- Application-level DoS (expensive queries)
- Distributed denial of service (DDoS)
- API abuse and rate limit bypass

**CIA Platform Attack Scenarios:**
```
Threat: Attacker overwhelms system with expensive political data queries
├─ Entry Point: Public search API /api/search?query=*
├─ Attack Vector: Recursive queries, unbounded result sets
├─ Impact: Service unavailability, degraded performance for legitimate users
└─ Mitigation:
    ├─ Implement rate limiting (100 requests/minute per IP)
    ├─ Query result pagination (max 100 results per page)
    ├─ Timeout for long-running queries (30 seconds)
    └─ Resource quotas per user tier
```

**Mitigation Checklist:**
- ✅ Rate limiting on all public APIs
- ✅ Request size limits enforced
- ✅ Query timeouts configured
- ✅ Pagination on all list endpoints
- ✅ Resource monitoring and alerting
- ✅ Circuit breakers for external dependencies

**Code Pattern:**
```java
@Configuration
public class RateLimitingConfig {
    
    @Bean
    public RateLimiter apiRateLimiter() {
        return RateLimiter.create(100.0); // 100 requests per second
    }
}

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {
    
    @Autowired
    private RateLimiter rateLimiter;
    
    private final LoadingCache<String, AtomicInteger> requestCounts = CacheBuilder.newBuilder()
        .expireAfterWrite(1, TimeUnit.MINUTES)
        .build(new CacheLoader<String, AtomicInteger>() {
            public AtomicInteger load(String key) {
                return new AtomicInteger(0);
            }
        });
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                            Object handler) throws Exception {
        
        String clientIp = getClientIP(request);
        
        // Apply rate limit
        if (!rateLimiter.tryAcquire(1, TimeUnit.SECONDS)) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded. Try again later.");
            return false;
        }
        
        // Check per-IP limit
        int requestCount = requestCounts.get(clientIp).incrementAndGet();
        if (requestCount > 1000) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("IP-based rate limit exceeded.");
            return false;
        }
        
        return true;
    }
}

@Repository
public interface PoliticianSearchRepository extends JpaRepository<Politician, Long> {
    
    // Pageable prevents unbounded result sets
    @Query("SELECT p FROM Politician p WHERE " +
           "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    @QueryHints(@QueryHint(name = "org.hibernate.timeout", value = "30")) // 30 second timeout
    Page<Politician> search(@Param("searchTerm") String searchTerm, Pageable pageable);
}

@RestController
public class SearchController {
    
    private static final int MAX_PAGE_SIZE = 100;
    
    @GetMapping("/api/search")
    public Page<PoliticianDTO> search(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        // Enforce maximum page size
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }
        
        Pageable pageable = PageRequest.of(page, size);
        return searchService.search(query, pageable);
    }
}
```

### E - Elevation of Privilege

**Threats to Consider:**
- Horizontal privilege escalation (access other users' data)
- Vertical privilege escalation (gain admin rights)
- Bypassing authorization checks
- Exploiting misconfigured access controls

**CIA Platform Attack Scenarios:**
```
Threat: Regular user gains administrative access to modify site configuration
├─ Entry Point: Admin API endpoint /api/admin/settings
├─ Attack Vector: Missing @PreAuthorize annotation on controller method
├─ Impact: Unauthorized configuration changes, system compromise
└─ Mitigation:
    ├─ Enforce role-based access control on all endpoints
    ├─ Use Spring Security annotations (@PreAuthorize, @Secured)
    ├─ Implement defense in depth (controller + service layer checks)
    └─ Regular access control audits
```

**Mitigation Checklist:**
- ✅ Role-based access control (RBAC) implemented
- ✅ Principle of least privilege enforced
- ✅ Authorization checks at multiple layers
- ✅ Regular access control reviews
- ✅ Privilege escalation attempts logged and alerted
- ✅ No default or backdoor admin accounts

**Code Pattern:**
```java
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')") // Controller-level authorization
public class AdminController {
    
    @PostMapping("/settings")
    @PreAuthorize("hasAuthority('MODIFY_SETTINGS')") // Method-level check
    public ResponseEntity<SystemSettings> updateSettings(
            @RequestBody @Valid SystemSettingsRequest request,
            @AuthenticationPrincipal UserDetails currentUser) {
        
        // Log privileged action
        auditLogger.logPrivilegedAction(
            currentUser.getUsername(),
            "UPDATE_SYSTEM_SETTINGS",
            request.toString()
        );
        
        // Service layer also enforces authorization
        SystemSettings updated = adminService.updateSettings(request);
        
        return ResponseEntity.ok(updated);
    }
}

@Service
public class AdminService {
    
    @PreAuthorize("hasRole('ADMIN')")
    public SystemSettings updateSettings(SystemSettingsRequest request) {
        // Verify authorization again at service layer (defense in depth)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Admin role required");
        }
        
        // Perform update
        SystemSettings settings = settingsRepository.findCurrent();
        settings.updateFrom(request);
        return settingsRepository.save(settings);
    }
}

// Global method security enabled
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler handler = 
            new DefaultMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }
}
```

## Attack Tree Analysis

### Example: Compromise Politician Data Integrity

```
ROOT: Compromise Politician Voting Record Data
├─ AND: Gain Write Access
│  ├─ OR: Exploit Authentication
│  │  ├─ Brute force credentials [Mitigated: Rate limiting]
│  │  ├─ Credential stuffing [Mitigated: 2FA]
│  │  └─ Session hijacking [Mitigated: Secure cookies]
│  └─ OR: Exploit Authorization
│     ├─ Privilege escalation [Mitigated: RBAC]
│     └─ IDOR vulnerability [Mitigated: Ownership checks]
└─ AND: Modify Data
   ├─ OR: Direct Database Access
   │  ├─ SQL injection [Mitigated: Parameterized queries]
   │  └─ Database credential theft [Mitigated: Secrets management]
   └─ OR: API Manipulation
      ├─ Parameter tampering [Mitigated: Input validation]
      └─ CSRF attack [Mitigated: CSRF tokens]
```

### Attack Tree Legend:
- **AND**: All child nodes must succeed
- **OR**: Any child node success achieves goal
- **[Mitigated]**: Control in place, residual risk accepted

## Security Architecture Review

### Trust Boundaries

```
Internet Users (Untrusted)
    │
    ├─→ Application Firewall / Rate Limiter
    │       │
    │       ├─→ Spring Boot Application (DMZ)
    │       │       │
    │       │       ├─→ Authentication Filter
    │       │       ├─→ Authorization Filter
    │       │       └─→ CSRF Protection
    │       │
    │       └─→ Internal Network (Trusted)
    │               │
    │               ├─→ PostgreSQL Database (Encrypted)
    │               └─→ Redis Session Store (Encrypted)
    │
    └─→ External APIs (Semi-Trusted)
            ├─→ Riksdagen API (HTTPS only)
            ├─→ World Bank API (HTTPS only)
            └─→ Election Authority API (HTTPS only)
```

### Data Flow Diagram (DFD) Security Analysis

**Level 0 DFD - Context Diagram:**
```
[Public Users] → (CIA Web App) → [Database]
[Admin Users] → (CIA Web App) → [External APIs]
```

**Security Analysis:**
- Public Users: Untrusted, require authentication for sensitive operations
- Admin Users: Trusted but verify, require strong authentication (2FA)
- External APIs: Semi-trusted, validate all responses
- Database: Trusted, encrypted at rest and in transit

## Threat Model Documentation Template

```markdown
# Threat Model: [Feature Name]

## Overview
- **Component**: [Component being analyzed]
- **Last Updated**: [Date]
- **Reviewer**: [Name]
- **Risk Rating**: [Critical/High/Medium/Low]

## Assets
1. Politician personal information (GDPR protected)
2. Voting records (integrity critical)
3. User credentials (confidentiality critical)
4. API keys for external services

## Trust Boundaries
- Internet → Application Server
- Application Server → Database
- Application Server → External APIs

## STRIDE Analysis

### Spoofing
- **Threat**: [Description]
- **Likelihood**: [High/Medium/Low]
- **Impact**: [High/Medium/Low]
- **Risk**: [Critical/High/Medium/Low]
- **Mitigation**: [Controls in place]
- **Residual Risk**: [Accepted/Needs treatment]

[Repeat for T, R, I, D, E]

## Attack Trees
[Attach attack tree diagrams]

## Security Requirements
1. [Requirement 1]
2. [Requirement 2]

## Security Test Cases
1. Test authentication bypass attempts
2. Test authorization escalation
3. Test input validation

## ISMS Compliance
- ISO 27001:2022 A.5.15 (Access Control)
- NIST CSF PR.AC-4 (Access Permissions)
- CIS Control 6.1 (Access Control Management)

## Sign-off
- **Security Team**: [Approved/Rejected]
- **Development Team**: [Approved/Rejected]
- **Date**: [Date]
```

## ISMS Compliance Mapping

### ISO 27001:2022 Controls
- **A.5.15 - Access Control**: STRIDE analysis ensures proper access controls
- **A.8.8 - Management of Technical Vulnerabilities**: Threat modeling identifies vulnerabilities proactively
- **A.14.2.1 - Secure Development Policy**: Threat modeling is mandatory step in SDLC
- **A.16.1 - Management of Information Security Incidents**: Attack trees inform incident response

### NIST Cybersecurity Framework
- **ID.RA-1**: Vulnerabilities identified through threat modeling
- **ID.RA-3**: Threats identified internally and externally
- **PR.IP-1**: Baseline security configurations from threat analysis
- **DE.CM-4**: System monitored for malicious activity based on threat model

### CIS Controls v8
- **Control 18.3**: Establish and maintain architecture documentation
- **Control 18.4**: Establish secure application design
- **Control 18.5**: Document application design flaws

## Hack23 ISMS Policy References

- **Risk Management Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/risk-management-policy.md
- **Secure Development Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/secure-development-policy.md
- **Architecture Security Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/architecture-security-policy.md

## References

### Threat Modeling Resources
- Microsoft STRIDE: https://learn.microsoft.com/en-us/azure/security/develop/threat-modeling-tool
- OWASP Threat Modeling: https://owasp.org/www-community/Threat_Modeling
- PASTA Methodology: https://versprite.com/pasta-threat-modeling/
- MITRE ATT&CK Framework: https://attack.mitre.org/

### CIA Project Documentation
- THREAT_MODEL.md - Current threat model
- SECURITY_ARCHITECTURE.md - Security architecture
- ARCHITECTURE.md - System architecture

### Tools
- Microsoft Threat Modeling Tool
- OWASP Threat Dragon: https://owasp.org/www-project-threat-dragon/
- Draw.io for DFDs: https://app.diagrams.net/
