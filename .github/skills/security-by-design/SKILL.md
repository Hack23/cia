---
name: security-by-design
description: Threat modeling before coding, STRIDE methodology, defense in depth, security controls in SDLC
license: Apache-2.0
---

# Security by Design Skill

## Purpose

This skill integrates security into every phase of the CIA platform's software development lifecycle (SDLC). It ensures threats are identified and mitigated before code is written, following defense-in-depth principles aligned with Hack23 ISMS Secure Development Policy.

## When to Use This Skill

Apply this skill when:
- ✅ Starting a new feature or user story
- ✅ Designing API endpoints or data flows
- ✅ Creating architecture or design documents
- ✅ Writing acceptance criteria for security stories
- ✅ Reviewing pull requests for security implications
- ✅ Planning sprint work involving sensitive data
- ✅ Conducting design reviews before implementation

Do NOT use for:
- ❌ Post-incident forensics (use incident-response skill)
- ❌ Runtime security monitoring (use aws-cloudwatch-monitoring)
- ❌ Code-level vulnerability scanning (use secure-code-review)

## Security in SDLC Phases

```
SDLC Phase          Security Activity
│
├─ REQUIREMENTS
│  ├─ Identify security requirements (abuse cases)
│  ├─ Define data classification for new features
│  └─ Document compliance requirements (GDPR, NIS2)
│
├─ DESIGN
│  ├─ Threat model using STRIDE
│  ├─ Define trust boundaries
│  ├─ Select security controls
│  └─ Review architecture for defense in depth
│
├─ IMPLEMENTATION
│  ├─ Follow secure coding standards
│  ├─ Use approved libraries and frameworks
│  ├─ Implement input validation at boundaries
│  └─ Apply principle of least privilege
│
├─ TESTING
│  ├─ Security unit tests
│  ├─ SAST scanning (CodeQL, SonarCloud)
│  ├─ DAST scanning (ZAP)
│  └─ Dependency vulnerability check (OWASP)
│
├─ DEPLOYMENT
│  ├─ Security configuration review
│  ├─ Infrastructure hardening verification
│  ├─ Secrets management validation
│  └─ Monitoring and alerting setup
│
└─ MAINTENANCE
   ├─ Vulnerability patching cadence
   ├─ Security incident response
   ├─ Periodic threat model updates
   └─ Dependency update reviews
```

## STRIDE Threat Modeling

### Quick-Start Template for CIA Features

```
Feature: [Name]
Data Classification: [PUBLIC/INTERNAL/CONFIDENTIAL/RESTRICTED]
Trust Boundary: [User→App / App→DB / App→ExternalAPI]

┌─────────────┬──────────────────────────────────────────┐
│ STRIDE      │ Assessment                               │
├─────────────┼──────────────────────────────────────────┤
│ Spoofing    │ Can an attacker impersonate a user?       │
│ Tampering   │ Can data be modified in transit/storage?  │
│ Repudiation │ Can actions be denied without proof?      │
│ Info Disc.  │ Can sensitive data leak?                  │
│ DoS         │ Can service be overwhelmed?               │
│ Elev. Priv. │ Can a user gain unauthorized access?      │
└─────────────┴──────────────────────────────────────────┘
```

### CIA Platform Example: Politician Dashboard

```
Feature: Politician Risk Score Dashboard
Data Classification: INTERNAL
Trust Boundaries: Browser→Vaadin→Service→Database

Spoofing:
  Threat: Unauthenticated access to risk scores
  Control: Spring Security authentication required
  Code: @PreAuthorize("hasRole('USER')")

Tampering:
  Threat: Manipulation of risk score algorithm inputs
  Control: Read-only database transactions for analysis
  Code: @Transactional(readOnly = true)

Repudiation:
  Threat: User denies viewing sensitive risk data
  Control: Audit logging of all dashboard access
  Code: AuditService.logAccess(userId, "RISK_DASHBOARD")

Information Disclosure:
  Threat: Risk scores leaked to unauthorized users
  Control: Role-based access, no client-side caching
  Code: Cache-Control: no-store, Pragma: no-cache

Denial of Service:
  Threat: Complex queries overloading database
  Control: Query timeout, connection pool limits
  Code: spring.datasource.hikari.connectionTimeout=30000

Elevation of Privilege:
  Threat: Regular user accessing admin risk controls
  Control: Method-level security annotations
  Code: @Secured("ROLE_ADMIN")
```

## Defense in Depth Layers

```
Layer 1: NETWORK
├─ AWS VPC with private subnets
├─ Security groups (least privilege)
├─ WAF rules for common attacks
└─ DDoS protection (AWS Shield)

Layer 2: APPLICATION
├─ Spring Security filter chain
├─ CSRF protection enabled
├─ Content Security Policy headers
└─ Rate limiting per client

Layer 3: DATA
├─ Input validation at every boundary
├─ Parameterized queries (JPA/Hibernate)
├─ Output encoding (Vaadin auto-escapes)
└─ Encryption at rest (AES-256, KMS)

Layer 4: IDENTITY
├─ Strong authentication (bcrypt, cost 12)
├─ Role-based access control (RBAC)
├─ Session management (secure cookies)
└─ Principle of least privilege

Layer 5: MONITORING
├─ Security event logging (CloudWatch)
├─ Intrusion detection (GuardDuty)
├─ Vulnerability scanning (CodeQL, OWASP)
└─ Incident response procedures
```

## Security Controls Checklist

### For Every New Feature

```
Pre-Implementation:
□ Threat model completed (STRIDE)
□ Data classification assigned
□ Security requirements documented
□ Trust boundaries identified

During Implementation:
□ Input validation at all entry points
□ Output encoding for all user-displayed data
□ Authentication required for protected resources
□ Authorization checks at service layer
□ Parameterized queries for database access
□ Secrets managed via environment variables
□ Error messages don't leak internal details
□ Logging includes security-relevant events

Post-Implementation:
□ CodeQL scan passes with no high/critical findings
□ OWASP dependency check passes
□ Security unit tests written and passing
□ Code review with security focus completed
```

### Secure Defaults

```java
// ✅ Spring Security configuration with secure defaults
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("default-src 'self'"))
                .frameOptions(frame -> frame.deny())
                .httpStrictTransportSecurity(hsts -> hsts
                    .maxAgeInSeconds(31536000)
                    .includeSubDomains(true)))
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1))
            .csrf(csrf -> csrf.csrfTokenRepository(
                CookieCsrfTokenRepository.withHttpOnlyFalse()));
        return http.build();
    }
}
```

## ISMS Alignment

| Control | Requirement | Security-by-Design Activity |
|---------|------------|---------------------------|
| ISO 27001 A.8.25 | Secure development lifecycle | STRIDE per feature |
| ISO 27001 A.8.26 | Application security requirements | Security user stories |
| ISO 27001 A.8.28 | Secure coding | Approved coding patterns |
| NIST CSF PR.DS | Data security | Encryption by default |
| CIS Control 16 | Application software security | SAST/DAST in pipeline |
| NIS2 Art. 21 | Cybersecurity risk management | Threat modeling |

## References

- [Hack23 ISMS Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)
- [OWASP Threat Modeling](https://owasp.org/www-community/Threat_Modeling)
- [Microsoft STRIDE](https://learn.microsoft.com/en-us/azure/security/develop/threat-modeling-tool-threats)
- [NIST SP 800-160 Systems Security Engineering](https://csrc.nist.gov/publications/detail/sp/800-160/vol-1/final)
