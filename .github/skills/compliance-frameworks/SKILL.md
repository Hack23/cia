---
name: compliance-frameworks
description: Multi-framework compliance (ISO 27001, NIST CSF, CIS Controls, GDPR, NIS2, EU CRA, SOC 2), control mapping
license: Apache-2.0
---

# Compliance Frameworks Skill

## Purpose

This skill provides unified compliance mapping across ISO 27001:2022, NIST CSF 2.0, CIS Controls v8, GDPR, NIS2, EU CRA, and SOC 2 for the CIA platform. It enables developers to implement controls that satisfy multiple frameworks simultaneously, reducing compliance overhead.

## When to Use This Skill

Apply this skill when:
- ✅ Implementing a new security control or feature
- ✅ Documenting compliance evidence for audits
- ✅ Mapping requirements across multiple frameworks
- ✅ Assessing regulatory impact of platform changes
- ✅ Preparing for ISO 27001 certification audits
- ✅ Evaluating NIS2 or EU CRA applicability
- ✅ Creating compliance reports for stakeholders

Do NOT use for:
- ❌ Detailed implementation of specific controls (use dedicated skills)
- ❌ Runtime security monitoring
- ❌ Code-level vulnerability fixing

## Framework Overview

```
Compliance Framework Hierarchy for CIA Platform
│
├─ MANDATORY COMPLIANCE
│  ├─ GDPR (data protection, Swedish political data)
│  ├─ NIS2 (network and information security, if applicable)
│  └─ EU CRA (cyber resilience for open-source software)
│
├─ VOLUNTARY STANDARDS (Hack23 ISMS)
│  ├─ ISO 27001:2022 (information security management)
│  ├─ NIST CSF 2.0 (cybersecurity framework)
│  └─ CIS Controls v8 (critical security controls)
│
└─ INDUSTRY BEST PRACTICES
   ├─ SOC 2 Type II (service organization controls)
   ├─ OWASP Top 10 (web application security)
   └─ OpenSSF Scorecard (open-source security posture)
```

## Cross-Framework Control Mapping

### Access Control

| Requirement | ISO 27001 | NIST CSF | CIS Controls | GDPR | NIS2 |
|------------|-----------|----------|--------------|------|------|
| Authentication | A.8.5 | PR.AA-01 | CIS 6.3 | Art. 32 | Art. 21(2)(d) |
| Authorization | A.5.15 | PR.AA-03 | CIS 6.8 | Art. 25 | Art. 21(2)(d) |
| Least privilege | A.8.2 | PR.AA-05 | CIS 6.1 | Art. 25 | Art. 21(2)(i) |
| MFA | A.8.5 | PR.AA-02 | CIS 6.5 | Art. 32 | Art. 21(2)(j) |
| Access review | A.5.18 | PR.AA-06 | CIS 6.2 | Art. 32 | Art. 21(2)(d) |

### Data Protection

| Requirement | ISO 27001 | NIST CSF | CIS Controls | GDPR | EU CRA |
|------------|-----------|----------|--------------|------|--------|
| Encryption at rest | A.8.24 | PR.DS-01 | CIS 3.11 | Art. 32(1)(a) | Art. 10(1) |
| Encryption in transit | A.8.24 | PR.DS-02 | CIS 3.10 | Art. 32(1)(a) | Art. 10(1) |
| Data classification | A.5.12 | ID.AM-08 | CIS 3.7 | Art. 9 | — |
| Data retention | A.5.33 | PR.DS-10 | CIS 3.1 | Art. 5(1)(e) | — |
| Backup | A.8.13 | PR.DS-11 | CIS 11.2 | Art. 32(1)(c) | Art. 10(1) |

### Vulnerability Management

| Requirement | ISO 27001 | NIST CSF | CIS Controls | EU CRA | SOC 2 |
|------------|-----------|----------|--------------|--------|-------|
| Vulnerability scanning | A.8.8 | DE.CM-08 | CIS 7.5 | Art. 10(6) | CC7.1 |
| Patch management | A.8.8 | PR.PS-02 | CIS 7.4 | Art. 10(6) | CC7.1 |
| Dependency check | A.8.28 | PR.PS-02 | CIS 16.4 | Art. 10(6) | CC7.1 |
| Pen testing | A.8.8 | DE.CM-08 | CIS 18.3 | Art. 10(4) | CC7.1 |
| SBOM | A.8.28 | PR.PS-01 | CIS 16.4 | Art. 10(5) | — |

### Incident Response

| Requirement | ISO 27001 | NIST CSF | CIS Controls | NIS2 | SOC 2 |
|------------|-----------|----------|--------------|------|-------|
| Incident plan | A.5.24 | RS.MA-01 | CIS 17.1 | Art. 23 | CC7.3 |
| Incident detection | A.8.16 | DE.AE-02 | CIS 17.3 | Art. 23(1) | CC7.2 |
| Reporting | A.5.25 | RS.CO-02 | CIS 17.2 | Art. 23(4) | CC7.4 |
| Lessons learned | A.5.27 | RS.IM-02 | CIS 17.8 | Art. 23 | CC7.5 |
| Evidence preservation | A.5.28 | RS.AN-06 | CIS 17.4 | Art. 23 | CC7.3 |

## CIA Platform Compliance Decision Tree

```
New Feature Compliance Assessment
│
├─→ Does it process personal data?
│   ├─ YES → GDPR (Art. 6 legal basis, Art. 25 privacy by design)
│   └─ NO → Continue
│
├─→ Does it affect network/information security?
│   ├─ YES → NIS2 (Art. 21 risk management measures)
│   └─ NO → Continue
│
├─→ Is it a software product/component?
│   ├─ YES → EU CRA (Art. 10 vulnerability handling)
│   └─ NO → Continue
│
├─→ Does it change security controls?
│   ├─ YES → ISO 27001 (Annex A controls)
│   │        NIST CSF (relevant function)
│   │        CIS Controls (implementation group)
│   └─ NO → Continue
│
└─→ Apply general secure development practices
    └─ OWASP Top 10, secure coding standards
```

## NIS2 Directive Compliance

### Applicability Assessment

```
NIS2 applies to CIA platform if:
- Essential entity: Public administration ICT services
- Important entity: Digital infrastructure providers
- Open-source steward: Maintained open-source project (Art. 15a)

Hack23/CIA classification: Open-Source Steward
Obligations: Due diligence, vulnerability handling, coordination
```

### Key Requirements

| NIS2 Article | Requirement | CIA Implementation |
|-------------|------------|-------------------|
| Art. 21(2)(a) | Risk analysis and IS policies | Hack23 ISMS policies |
| Art. 21(2)(b) | Incident handling | Incident response plan |
| Art. 21(2)(d) | Supply chain security | OWASP dependency check |
| Art. 21(2)(e) | Secure development | SDLC security gates |
| Art. 21(2)(h) | Security awareness | Developer training |
| Art. 21(2)(j) | MFA and encryption | Spring Security, AES-256 |

## EU Cyber Resilience Act (CRA)

### Open-Source Software Obligations

```
EU CRA Open-Source Steward Requirements:
├─ Vulnerability disclosure policy (SECURITY.md)
├─ Coordinated vulnerability handling
├─ Security update distribution
├─ Software Bill of Materials (SBOM)
├─ CE marking considerations
└─ Documentation of security properties
```

### Implementation Evidence

| CRA Requirement | Evidence |
|----------------|---------|
| Vulnerability handling | SECURITY.md, GitHub Security Advisories |
| Security updates | Dependabot, automated dependency updates |
| SBOM generation | Maven CycloneDX plugin |
| Secure by default | Spring Security configuration |
| Documentation | SECURITY_ARCHITECTURE.md, THREAT_MODEL.md |

## Compliance Evidence Collection

### Per-Sprint Evidence

```
Sprint Compliance Artifacts:
□ Code review records (GitHub PR reviews)
□ Security scan results (CodeQL, OWASP)
□ Test coverage reports (JaCoCo)
□ Dependency audit (Dependabot alerts)
□ Access control changes (audit log)
□ Configuration changes (git history)
```

### Annual Evidence

```
Annual Compliance Review:
□ ISMS policy review and update
□ Risk assessment update
□ Penetration testing results
□ Business continuity test
□ Access rights review
□ Security awareness training records
□ Supplier security assessments
□ Incident response drill results
```

## ISMS Alignment

| Policy | Frameworks Covered | Location |
|--------|-------------------|----------|
| Information Security Policy | ISO 27001, NIST CSF | Hack23 ISMS |
| Classification Policy | ISO 27001, GDPR | Hack23 ISMS |
| Access Control Policy | ISO 27001, CIS, NIS2 | Hack23 ISMS |
| Secure Development Policy | ISO 27001, EU CRA | Hack23 ISMS |
| Incident Response Policy | ISO 27001, NIS2 | Hack23 ISMS |
| Cryptography Policy | ISO 27001, GDPR | Hack23 ISMS |

## References

- [Hack23 ISMS Public](https://github.com/Hack23/ISMS-PUBLIC)
- [ISO 27001:2022](https://www.iso.org/standard/27001)
- [NIST CSF 2.0](https://www.nist.gov/cyberframework)
- [CIS Controls v8](https://www.cisecurity.org/controls/v8)
- [NIS2 Directive](https://eur-lex.europa.eu/eli/dir/2022/2555)
- [EU Cyber Resilience Act](https://eur-lex.europa.eu/eli/reg/2024/2847)
- [GDPR](https://gdpr-info.eu/)
