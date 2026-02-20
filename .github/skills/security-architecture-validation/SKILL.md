---
name: security-architecture-validation
description: Security architecture review, control validation, penetration testing guidance, and compliance verification for the CIA platform
license: Apache-2.0
---

# Security Architecture Validation Skill

## Purpose

This skill provides structured guidance for validating the security architecture of the Citizen Intelligence Agency platform. It covers architecture reviews, control validation, penetration testing coordination, and compliance verification against ISMS requirements.

## When to Use This Skill

Apply this skill when:
- ✅ Reviewing architecture changes for security impact
- ✅ Validating security controls are properly implemented
- ✅ Planning or reviewing penetration test results
- ✅ Preparing for compliance audits (ISO 27001, SOC 2)
- ✅ Assessing new integration points (APIs, data sources)
- ✅ Evaluating infrastructure changes (CloudFormation, AWS)
- ✅ Conducting periodic security architecture reviews

Do NOT use for:
- ❌ Line-by-line code review (use secure-code-review)
- ❌ Day-to-day coding practices (use secure-development-policy)
- ❌ Incident handling (use incident-response)

## Architecture Review Framework

### CIA Platform Security Layers

```
┌─────────────────────────────────────────┐
│           WAF / CloudFront              │  Layer 1: Edge Security
├─────────────────────────────────────────┤
│         Load Balancer (ALB)             │  Layer 2: Network
├─────────────────────────────────────────┤
│    Spring Security Filter Chain         │  Layer 3: Application Auth
├─────────────────────────────────────────┤
│   Vaadin UI + Service Layer             │  Layer 4: Business Logic
├─────────────────────────────────────────┤
│   JPA/Hibernate + PostgreSQL            │  Layer 5: Data Access
├─────────────────────────────────────────┤
│   AWS VPC + Security Groups             │  Layer 6: Infrastructure
└─────────────────────────────────────────┘
```

### Architecture Review Checklist

**Authentication & Authorization:**
```
□ Spring Security configured with secure defaults
□ Session management uses secure cookies (HttpOnly, Secure, SameSite)
□ Password storage uses bcrypt or Argon2
□ Role-based access control enforced at service layer
□ Admin endpoints protected with additional authentication
□ API authentication uses token-based mechanism
□ Session timeout configured appropriately
```

**Network Security:**
```
□ TLS 1.2+ enforced for all connections
□ HSTS header configured
□ Security groups follow least privilege
□ Database not publicly accessible
□ VPC subnets properly segmented
□ Outbound traffic restricted to required destinations
```

**Data Protection:**
```
□ Data at rest encrypted (RDS, EBS, S3)
□ Data in transit encrypted (TLS)
□ Sensitive fields encrypted at application level
□ Database credentials managed via AWS Secrets Manager
□ No PII in application logs
□ Backup encryption enabled
```

## Control Validation Matrix

| Control Domain | Expected Control | Validation Method | Evidence |
|---|---|---|---|
| Access Control | RBAC via Spring Security | Config review + test | SecurityConfig.java |
| Encryption | TLS 1.2+ all connections | SSL scan | CloudFormation template |
| Input Validation | All user inputs validated | Code review + DAST | Controller layer review |
| Audit Logging | Security events logged | Log review | Logback configuration |
| Session Mgmt | Secure session handling | Config review | Spring Security config |
| Error Handling | No info leakage | DAST + code review | Exception handlers |
| CSRF | Token-based protection | Config review + test | Spring Security config |
| SQL Injection | Parameterized queries only | SAST + code review | JPA repositories |
| XSS Prevention | Output encoding | DAST + code review | Vaadin components |
| Dependency Mgmt | No critical CVEs | OWASP DC scan | CI/CD pipeline |

## Penetration Testing Guidance

### Scope Definition

**In Scope:**
- Web application (Vaadin UI + REST APIs)
- Authentication and session management
- Authorization boundaries between roles
- Input validation across all entry points
- External API integrations (Riksdag, World Bank)
- CloudFormation-deployed infrastructure

**Out of Scope:**
- Third-party services (GitHub, AWS managed services)
- Physical security
- Social engineering
- Denial of service attacks

### Test Categories

| Category | Focus Areas | OWASP Reference |
|---|---|---|
| Authentication | Login, session, password reset | A07:2021 |
| Authorization | Role escalation, IDOR | A01:2021 |
| Injection | SQL, XSS, Command injection | A03:2021 |
| Configuration | Headers, TLS, error pages | A05:2021 |
| Data Exposure | PII leakage, API responses | A02:2021 |
| Components | Known vulnerable dependencies | A06:2021 |
| Logging | Audit trail completeness | A09:2021 |

### Penetration Test Report Template

```markdown
## Finding: [Title]
- **Severity:** Critical / High / Medium / Low
- **CVSS Score:** X.X
- **OWASP Category:** AXX:2021
- **Affected Component:** [Component name]
- **Description:** [What was found]
- **Impact:** [Business impact]
- **Reproduction Steps:** [Step-by-step]
- **Remediation:** [How to fix]
- **ISO 27001 Control:** [Relevant control]
- **Status:** Open / In Progress / Resolved
```

## Compliance Verification

### ISO 27001:2022 Architecture Controls

| Control | Requirement | CIA Implementation | Status |
|---|---|---|---|
| A.8.1 | User endpoint devices | N/A (server-side app) | N/A |
| A.8.3 | Information access restriction | Spring Security RBAC | ✅ |
| A.8.5 | Secure authentication | Spring Security + bcrypt | ✅ |
| A.8.9 | Configuration management | CloudFormation IaC | ✅ |
| A.8.15 | Logging | SLF4J + CloudWatch | ✅ |
| A.8.20 | Network security | VPC + Security Groups | ✅ |
| A.8.24 | Use of cryptography | TLS + encrypted storage | ✅ |
| A.8.25 | Secure development lifecycle | CI/CD security gates | ✅ |
| A.8.26 | Application security requirements | Security requirements | ✅ |
| A.8.28 | Secure coding | SAST + code review | ✅ |

### Validation Frequency

| Validation Activity | Frequency | Responsible |
|---|---|---|
| Architecture review | Quarterly + major changes | Security lead |
| Control validation | Monthly automated + quarterly manual | DevSecOps |
| Penetration testing | Annually + major releases | External firm |
| Compliance audit | Annual (ISO 27001 certification) | Auditor |
| Dependency review | Weekly automated | Dependabot + manual |
| Configuration review | Monthly | DevSecOps |

## Decision Framework

```
Architecture Change Proposed
    │
    ├─→ Does it change authentication/authorization?
    │   └─→ YES → Full security architecture review required
    │
    ├─→ Does it add new external integrations?
    │   └─→ YES → Threat model update + data flow review
    │
    ├─→ Does it change data storage or processing?
    │   └─→ YES → Data classification review + encryption check
    │
    ├─→ Does it modify infrastructure (CloudFormation)?
    │   └─→ YES → Infrastructure security review
    │
    └─→ Does it change network boundaries?
        └─→ YES → Network security review + firewall rules
```

## References

- [OWASP Application Security Verification Standard](https://owasp.org/www-project-application-security-verification-standard/)
- [NIST SP 800-53 Security Controls](https://csrc.nist.gov/publications/detail/sp/800-53/rev-5/final)
- [CIA SECURITY_ARCHITECTURE.md](../../SECURITY_ARCHITECTURE.md)
- [CIA THREAT_MODEL.md](../../THREAT_MODEL.md)
