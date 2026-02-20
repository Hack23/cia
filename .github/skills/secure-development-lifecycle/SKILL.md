---
name: secure-development-lifecycle
description: Secure SDLC phases, security requirements, secure coding practices, and security testing integration for the CIA platform
license: Apache-2.0
---

# Secure Development Lifecycle Skill

## Purpose

This skill provides guidance for integrating security into every phase of the software development lifecycle (SDLC) for the Citizen Intelligence Agency platform. It ensures that security is not an afterthought but a fundamental design principle from requirements through deployment.

## When to Use This Skill

Apply this skill when:
- ✅ Starting a new feature or module
- ✅ Defining requirements for new functionality
- ✅ Designing architecture for new components
- ✅ Writing code that handles sensitive political data
- ✅ Planning test strategies for new features
- ✅ Preparing releases and deployments
- ✅ Conducting post-deployment security reviews

Do NOT use for:
- ❌ Operational security monitoring (use incident-response skill)
- ❌ Infrastructure-level security (use security-architecture-validation)
- ❌ Pure code review without lifecycle context (use secure-code-review)

## SDLC Phases

### Phase 1: Security Requirements

Define security requirements alongside functional requirements:

| Requirement Category | CIA Platform Example | ISMS Control |
|---|---|---|
| Authentication | Spring Security login, 2FA enforcement | ISO 27001 A.8.5 |
| Authorization | Role-based access to admin views | ISO 27001 A.8.3 |
| Data Protection | Encryption of user credentials | ISO 27001 A.8.24 |
| Input Validation | Sanitize all Riksdag API data inputs | ISO 27001 A.8.28 |
| Audit Logging | Log all admin actions and data access | ISO 27001 A.8.15 |
| Privacy | GDPR compliance for user data | GDPR Art. 25 |

**Security Requirements Checklist:**
```
□ Threat model updated for new feature
□ Data classification level assigned
□ Authentication requirements defined
□ Authorization matrix updated
□ Input validation rules specified
□ Audit logging requirements defined
□ Privacy impact assessment completed
□ Compliance requirements mapped
```

### Phase 2: Secure Design

Apply secure design principles:

1. **Defense in Depth** — Multiple security layers (Spring Security filters, service-layer validation, JPA parameterized queries)
2. **Least Privilege** — Minimal permissions for each role
3. **Fail Secure** — Deny by default on errors
4. **Separation of Duties** — Distinct service/model/web layers
5. **Economy of Mechanism** — Simple, auditable security controls

**Design Review Checklist:**
```
□ Architecture follows existing layered pattern
□ No direct database access from web layer
□ All external API calls go through service layer
□ Error handling does not leak sensitive information
□ Session management uses Spring Security defaults
□ CSRF protection enabled for state-changing operations
```

### Phase 3: Secure Coding

Follow secure coding practices specific to the CIA stack:

**Java/Spring Security Practices:**
```java
// DO: Use parameterized queries via JPA
@Query("SELECT p FROM Politician p WHERE p.name = :name")
List<Politician> findByName(@Param("name") String name);

// DON'T: Concatenate user input into queries
// String query = "SELECT * FROM politician WHERE name = '" + name + "'";

// DO: Validate input at controller level
@Valid @RequestBody PoliticianRequest request

// DO: Use constructor injection
@Service
public class PoliticianService {
    private final PoliticianRepository repository;

    public PoliticianService(PoliticianRepository repository) {
        this.repository = repository;
    }
}
```

**Key Coding Rules:**
- Never log sensitive data (passwords, tokens, PII)
- Use `@Transactional(readOnly = true)` for read-only operations
- Validate all inputs from external APIs (Riksdag, World Bank)
- Encode output in Vaadin components to prevent XSS
- Use Spring Security's CSRF protection for all forms

### Phase 4: Security Testing

Integrate security testing at multiple levels:

| Test Level | Tool/Approach | When | Coverage Target |
|---|---|---|---|
| Unit Tests | JUnit 5 + Mockito | Every commit | 80% line, 70% branch |
| SAST | CodeQL, SonarCloud | PR checks | Zero critical/high |
| Dependency Scan | OWASP Dependency Check | PR checks | No critical CVEs |
| Integration Tests | Spring Test + TestContainers | PR checks | Service layer coverage |
| DAST | ZAP Scan | Release pipeline | OWASP Top 10 |
| Penetration Testing | Manual + automated | Quarterly | Full application |

**Security Test Checklist:**
```
□ Unit tests cover authentication logic
□ Unit tests cover authorization boundaries
□ Input validation tests include malicious payloads
□ Integration tests verify security filter chains
□ CodeQL scan passes with zero new alerts
□ OWASP Dependency Check passes
□ ZAP scan shows no new vulnerabilities
```

### Phase 5: Secure Deployment

Deployment security controls:

```yaml
# CI/CD Security Gates (GitHub Actions)
- name: Security Gate
  steps:
    - mvn test                          # All tests pass
    - mvn dependency-check:check        # No critical CVEs
    - codeql-analysis                   # No security alerts
    - sonarcloud-scan                   # Quality gate pass
    - zap-scan                          # No high findings
```

**Deployment Checklist:**
```
□ All CI/CD security gates pass
□ No secrets in source code or configuration
□ Database migrations reviewed for security impact
□ CloudFormation templates follow least privilege
□ Monitoring and alerting configured
□ Rollback plan documented
```

### Phase 6: Post-Deployment

Ongoing security activities:

- Monitor application logs for security events
- Review Dependabot alerts weekly
- Conduct quarterly security reviews
- Update threat model after incidents
- Maintain ISMS documentation currency

## Decision Framework

```
New Feature Request
    │
    ├─→ Define security requirements
    │   └─→ Update threat model if needed
    │
    ├─→ Design with security principles
    │   └─→ Review against OWASP Top 10
    │
    ├─→ Implement with secure coding practices
    │   └─→ Follow CIA coding standards
    │
    ├─→ Test security at all levels
    │   └─→ Pass all CI/CD security gates
    │
    ├─→ Deploy with security controls
    │   └─→ Verify monitoring in place
    │
    └─→ Monitor and maintain
        └─→ Update ISMS documentation
```

## ISMS Alignment

| SDLC Phase | ISO 27001 Controls | NIST CSF Functions |
|---|---|---|
| Requirements | A.5.8, A.8.25 | ID.RA, PR.IP |
| Design | A.8.25, A.8.26 | PR.IP, PR.DS |
| Coding | A.8.28, A.8.25 | PR.IP, PR.DS |
| Testing | A.8.29, A.8.33 | DE.CM, PR.IP |
| Deployment | A.8.31, A.8.32 | PR.IP, PR.MA |
| Operations | A.8.15, A.8.16 | DE.AE, RS.AN |

## References

- [OWASP Secure SDLC](https://owasp.org/www-project-integration-standards/)
- [NIST SP 800-218 SSDF](https://csrc.nist.gov/publications/detail/sp/800-218/final)
- [Hack23 Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)
- [ISO 27001:2022 Annex A](https://www.iso.org/standard/27001)
