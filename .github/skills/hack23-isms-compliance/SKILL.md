---
name: hack23-isms-compliance
description: Hack23 ISMS organization-wide compliance requirements, policy enforcement, audit preparation
license: Apache-2.0
---

# Hack23 ISMS Compliance Skill

## Purpose

Ensure all Hack23 organization projects comply with the Information Security Management System (ISMS) requirements. Covers ISO 27001:2022, NIST CSF 2.0, CIS Controls v8, NIS2, and GDPR compliance across the development lifecycle. Provides actionable guidance for audit preparation and policy enforcement.

## When to Use

- ✅ Starting new projects or repositories under Hack23
- ✅ Preparing for internal or external security audits
- ✅ Reviewing compliance status of existing projects
- ✅ Implementing security controls for new features
- ✅ Creating or updating security policies and procedures
- ✅ Responding to security incidents

Do NOT use for:
- ❌ Specific code-level security patterns (use secure-code-review skill)
- ❌ Threat modeling exercises (use threat-modeling skill)
- ❌ Infrastructure security (use aws-cloudwatch-monitoring skill)

## ISMS Framework Overview

```
┌─────────────────────────────────────────────────────────┐
│                  Hack23 ISMS Framework                   │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │ ISO 27001    │  │ NIST CSF 2.0 │  │ CIS Controls │  │
│  │ :2022        │  │              │  │ v8           │  │
│  │ 93 Controls  │  │ 6 Functions  │  │ 18 Controls  │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
│                                                         │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │ NIS2         │  │ GDPR         │  │ EU CRA       │  │
│  │ Directive    │  │              │  │              │  │
│  │              │  │ Data Privacy │  │ Cyber        │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
│                                                         │
│  Reference: github.com/Hack23/ISMS-PUBLIC               │
└─────────────────────────────────────────────────────────┘
```

## Compliance Checklist Per Repository

### Mandatory Documents

| Document | Status | Description |
|----------|--------|-------------|
| `SECURITY.md` | Required | Security policy and vulnerability reporting |
| `SECURITY_ARCHITECTURE.md` | Required | Security architecture documentation |
| `THREAT_MODEL.md` | Required | Threat model using STRIDE framework |
| `LICENSE.txt` | Required | Apache 2.0 license file |
| `CODEOWNERS` | Required | Code ownership and review requirements |
| `CODE_OF_CONDUCT.md` | Required | Community standards |

### Required GitHub Settings

- [ ] Branch protection on `main` — require PR reviews
- [ ] Secret scanning enabled with push protection
- [ ] Dependabot alerts enabled for all ecosystems
- [ ] Code scanning (CodeQL) enabled
- [ ] OSSF Scorecard action configured
- [ ] Signed commits required (recommended)

### Required CI/CD Controls

- [ ] OWASP Dependency Check in pipeline
- [ ] SAST scanning (CodeQL or SonarCloud)
- [ ] License compliance check
- [ ] SBOM generation (CycloneDX)
- [ ] Container scanning (if applicable)

## ISO 27001:2022 Control Mapping

### Key Controls for Development

| Control ID | Control Name | Implementation |
|------------|-------------|----------------|
| A.5.1 | Policies for information security | `SECURITY.md`, ISMS policies |
| A.8.4 | Access to source code | GitHub branch protection, CODEOWNERS |
| A.8.9 | Configuration management | Infrastructure as Code, version control |
| A.8.25 | Secure development lifecycle | CI/CD pipeline with security gates |
| A.8.26 | Application security requirements | Input validation, OWASP Top 10 |
| A.8.28 | Secure coding | Code review, SAST scanning |
| A.8.31 | Separation of environments | Dev/staging/prod separation |
| A.8.33 | Test information | No production data in test environments |

## NIST CSF 2.0 Mapping

| Function | Category | CIA Platform Implementation |
|----------|----------|----------------------------|
| **Identify** | Asset Management | Repository inventory, SBOM |
| **Protect** | Access Control | Spring Security, RBAC |
| **Protect** | Data Security | Encryption, input validation |
| **Detect** | Continuous Monitoring | CodeQL, Dependabot, OSSF |
| **Respond** | Incident Response | `SECURITY.md` reporting process |
| **Recover** | Recovery Planning | Backup procedures, DR plans |

## GDPR Compliance for Political Data

### Data Classification

| Category | Examples | Handling |
|----------|----------|----------|
| Public political data | Votes, speeches, motions | Open access, no restrictions |
| Politician profiles | Name, party, committee | Public figure exception applies |
| User accounts | Email, preferences | Minimize, encrypt, consent required |
| Analytics data | Usage patterns | Anonymize, aggregate |

### GDPR Requirements Checklist

- [ ] Data Processing Register maintained
- [ ] Privacy Impact Assessment for new features
- [ ] Data retention periods defined and enforced
- [ ] Right to erasure implemented for user data
- [ ] Data breach notification procedure documented
- [ ] Third-party data processor agreements in place

## Audit Preparation Guide

### Pre-Audit Checklist

1. **Documentation Review**
   - All mandatory documents up to date
   - Architecture diagrams current
   - Risk register reviewed within 90 days

2. **Technical Controls Verification**
   - Run full security scan suite
   - Verify all CI/CD security gates active
   - Check dependency vulnerability status
   - Review access control configuration

3. **Evidence Collection**
   ```bash
   # Generate compliance evidence
   mvn dependency-check:check           # Vulnerability scan
   mvn org.cyclonedx:cyclonedx-maven-plugin:makeBom  # SBOM
   mvn site                             # Project reports
   ```

4. **Metrics Preparation**
   - Mean time to remediate vulnerabilities
   - Code coverage percentages
   - Open security issue count and age
   - Dependency update compliance rate

## Policy Enforcement

### Automated Enforcement

```yaml
# Branch protection rules (enforce via GitHub API)
protection:
  required_reviews: 1
  dismiss_stale_reviews: true
  require_code_owner_reviews: true
  required_status_checks:
    - "build"
    - "codeql"
    - "dependency-check"
  enforce_admins: true
```

### Manual Review Requirements

| Change Type | Reviewer | Approval |
|-------------|----------|----------|
| Security policy | Security lead | Required |
| Architecture change | Tech lead | Required |
| New dependency | Any reviewer | Required + security scan |
| CI/CD pipeline | DevOps + Security | Both required |
| Data model change | Tech lead | Required |

## Incident Response Alignment

When a security incident occurs:

1. **Detect** — CodeQL alerts, Dependabot alerts, manual report via `SECURITY.md`
2. **Contain** — Disable affected component, revoke compromised credentials
3. **Eradicate** — Fix vulnerability, update dependencies
4. **Recover** — Deploy fix, verify resolution
5. **Lessons Learned** — Update threat model, create ADR if architectural change needed

## ISMS Reference

The authoritative ISMS documentation is maintained at:
- **Repository**: `github.com/Hack23/ISMS-PUBLIC`
- **Secure Development Policy**: `Secure_Development_Policy.md`
- **Key Management Policy**: `Key_Management_Policy.md`
- **Access Control Policy**: `Access_Control_Policy.md`

All Hack23 projects must align with these organization-wide policies.
