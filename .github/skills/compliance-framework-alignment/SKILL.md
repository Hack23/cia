---
name: compliance-framework-alignment
description: Cross-framework compliance alignment and control mapping between ISO 27001, NIST CSF, CIS Controls, and GDPR
license: Apache-2.0
---

# Compliance Framework Alignment Skill

## Purpose

This skill provides a unified cross-framework compliance alignment for the CIA platform, mapping controls between ISO 27001:2022, NIST CSF 2.0, CIS Controls v8, and GDPR. It enables developers and security teams to understand how a single implementation satisfies multiple compliance requirements simultaneously.

## When to Use This Skill

Apply this skill when:
- ✅ Implementing security controls that must satisfy multiple frameworks
- ✅ Preparing for compliance audits
- ✅ Documenting control implementations
- ✅ Assessing compliance gaps
- ✅ Justifying security investments to stakeholders
- ✅ Reviewing architecture changes for compliance impact
- ✅ Updating ISMS documentation

Do NOT use for:
- ❌ Detailed control implementation (use framework-specific skills)
- ❌ Risk assessment methodology (use risk-assessment-methodology)
- ❌ Incident response procedures (use incident-response)

## Framework Overview

### Frameworks in Scope

| Framework | Version | Focus | Applicability |
|---|---|---|---|
| **ISO 27001** | 2022 | ISMS certification | Mandatory — Hack23 ISMS |
| **NIST CSF** | 2.0 | Cybersecurity risk management | Recommended — best practice |
| **CIS Controls** | v8 | Prioritized cyber defense | Recommended — IG1/IG2 |
| **GDPR** | 2016/679 | Personal data protection | Mandatory — EU data processing |

### Framework Function Mapping

```
ISO 27001 Domains    ←→    NIST CSF Functions    ←→    CIS Controls IG
────────────────           ──────────────────           ──────────────
Organizational (5)   ←→    Govern (GV)            ←→    IG1: Essential
People (6)           ←→    Identify (ID)          ←→    IG2: Foundational
Physical (7)         ←→    Protect (PR)           ←→    IG3: Organizational
Technological (8)    ←→    Detect (DE)
                     ←→    Respond (RS)
                     ←→    Recover (RC)
```

## Cross-Framework Control Mapping

### Access Control

| Requirement | ISO 27001 | NIST CSF | CIS Controls | GDPR |
|---|---|---|---|---|
| Access policy | A.5.15 | PR.AA-1 | CIS 6.1 | Art. 25 |
| User authentication | A.8.5 | PR.AA-3 | CIS 6.3 | Art. 32 |
| Privileged access | A.8.2 | PR.AA-5 | CIS 6.5 | Art. 32 |
| Access review | A.5.18 | PR.AA-6 | CIS 6.2 | Art. 5(1)(f) |
| **CIA Implementation** | Spring Security RBAC, role-based views ||||

### Data Protection

| Requirement | ISO 27001 | NIST CSF | CIS Controls | GDPR |
|---|---|---|---|---|
| Data classification | A.5.12 | ID.AM-5 | CIS 3.1 | Art. 30 |
| Encryption at rest | A.8.24 | PR.DS-1 | CIS 3.11 | Art. 32 |
| Encryption in transit | A.8.24 | PR.DS-2 | CIS 3.10 | Art. 32 |
| Data retention | A.5.33 | PR.IP-6 | CIS 3.4 | Art. 5(1)(e) |
| Data minimization | A.5.31 | — | — | Art. 5(1)(c) |
| **CIA Implementation** | TLS 1.2+, RDS encryption, GDPR-compliant user data ||||

### Secure Development

| Requirement | ISO 27001 | NIST CSF | CIS Controls | GDPR |
|---|---|---|---|---|
| Secure SDLC | A.8.25 | PR.IP-12 | CIS 16.1 | Art. 25 |
| Security testing | A.8.29 | DE.CM-8 | CIS 16.4 | Art. 32 |
| Code review | A.8.28 | PR.IP-12 | CIS 16.11 | Art. 25 |
| Dependency management | A.8.19 | ID.SC-2 | CIS 16.7 | Art. 32 |
| Change management | A.8.32 | PR.IP-3 | CIS 16.3 | Art. 25 |
| **CIA Implementation** | CI/CD gates, CodeQL, OWASP DC, SonarCloud ||||

### Logging & Monitoring

| Requirement | ISO 27001 | NIST CSF | CIS Controls | GDPR |
|---|---|---|---|---|
| Audit logging | A.8.15 | DE.AE-3 | CIS 8.2 | Art. 30 |
| Log protection | A.8.15 | PR.DS-6 | CIS 8.9 | Art. 32 |
| Monitoring | A.8.16 | DE.CM-1 | CIS 8.11 | Art. 32 |
| Alerting | A.8.16 | DE.AE-4 | CIS 8.11 | Art. 33 |
| **CIA Implementation** | SLF4J + Logback, AWS CloudWatch ||||

### Incident Management

| Requirement | ISO 27001 | NIST CSF | CIS Controls | GDPR |
|---|---|---|---|---|
| Incident plan | A.5.24 | RS.MA-1 | CIS 17.1 | Art. 33 |
| Incident detection | A.5.25 | DE.AE-2 | CIS 17.3 | Art. 33 |
| Incident response | A.5.26 | RS.MA-2 | CIS 17.4 | Art. 33 |
| Lessons learned | A.5.27 | RS.IM-1 | CIS 17.8 | Art. 33(3) |
| Breach notification | A.5.26 | RS.CO-2 | CIS 17.2 | Art. 33, 34 |
| **CIA Implementation** | GitHub Security Advisories, SECURITY.md process ||||

## Compliance Gap Analysis Template

### Per-Control Assessment

```markdown
## Control: [Name]

### Framework References
- ISO 27001: [Control ID]
- NIST CSF: [Function.Category-Subcategory]
- CIS Controls: [Control ID]
- GDPR: [Article]

### Current Implementation
- **Status:** Implemented / Partial / Not Implemented
- **Implementation:** [Description]
- **Evidence:** [Where to find proof]

### Gap Assessment
- **Gap:** [What's missing]
- **Risk:** Critical / High / Medium / Low
- **Remediation:** [What needs to be done]
- **Timeline:** [When]
- **Owner:** [Who]
```

## Audit Preparation

### Evidence Collection Matrix

| Control Area | Evidence Type | Location | Format |
|---|---|---|---|
| Access Control | Security config | `SecurityConfig.java` | Code review |
| Encryption | TLS config | CloudFormation templates | Config review |
| SAST/DAST | Scan reports | GitHub Actions artifacts | Automated reports |
| Code Review | PR reviews | GitHub PR history | Audit trail |
| Dependency Scan | OWASP DC reports | CI/CD artifacts | Automated reports |
| Logging | Log config | `logback.xml` | Config review |
| Change Mgmt | Git history | GitHub commits/PRs | Automated trail |
| Testing | Coverage reports | JaCoCo/SonarCloud | Automated reports |
| Incident Mgmt | Security advisories | GitHub Security tab | Documented process |

### Audit Readiness Checklist

```
□ ISMS documentation current and approved
□ Risk assessment completed within last year
□ Security controls implemented and documented
□ Evidence artifacts collected and organized
□ Training records available
□ Incident response plan tested
□ Business continuity plan reviewed
□ Third-party security assessments completed
□ Corrective actions from previous audit closed
□ Management review conducted
```

## GDPR-Specific Requirements

### CIA Platform GDPR Obligations

| GDPR Requirement | Article | CIA Implementation |
|---|---|---|
| Lawful basis | Art. 6 | Legitimate interest (political transparency) |
| Data minimization | Art. 5(1)(c) | Collect only necessary user data |
| Purpose limitation | Art. 5(1)(b) | Political transparency analysis only |
| Storage limitation | Art. 5(1)(e) | Defined retention periods |
| Integrity & confidentiality | Art. 5(1)(f) | Encryption, access controls |
| Privacy by design | Art. 25 | Built into architecture |
| Data protection officer | Art. 37 | Assessed — not required (small org) |
| Records of processing | Art. 30 | Maintained in ISMS docs |
| Breach notification | Art. 33 | 72-hour notification process |
| Data subject rights | Art. 15-22 | Account deletion, data export |

## Decision Framework

```
Implementing a New Security Control
    │
    ├─→ Identify all applicable framework requirements
    │   └─→ Check ISO 27001, NIST CSF, CIS, GDPR mappings above
    │
    ├─→ Design control to satisfy ALL applicable frameworks
    │   └─→ One implementation, multiple compliance benefits
    │
    ├─→ Document control implementation
    │   └─→ Map to specific control IDs in each framework
    │
    ├─→ Collect evidence of implementation
    │   └─→ Automated where possible (CI/CD, logs, configs)
    │
    └─→ Verify control effectiveness
        └─→ Test, review, and audit periodically
```

## References

- [ISO 27001:2022](https://www.iso.org/standard/27001)
- [NIST CSF 2.0](https://www.nist.gov/cyberframework)
- [CIS Controls v8](https://www.cisecurity.org/controls)
- [GDPR Full Text](https://gdpr-info.eu/)
- [Hack23 ISMS Compliance Mapping](../../ISMS_COMPLIANCE_MAPPING.md)
