---
name: secure-development-policy
description: Hack23 secure development policy enforcement, SAST/DAST integration, dependency scanning, and code signing practices
license: Apache-2.0
---

# Secure Development Policy Skill

## Purpose

This skill enforces the Hack23 Secure Development Policy across the CIA platform. It provides actionable guidance for SAST/DAST integration, dependency scanning, code signing, and policy compliance verification in daily development workflows.

## When to Use This Skill

Apply this skill when:
- ✅ Reviewing PRs for policy compliance
- ✅ Configuring CI/CD security scanning pipelines
- ✅ Adding or updating dependencies
- ✅ Setting up new development environments
- ✅ Investigating security scan findings
- ✅ Preparing code for release
- ✅ Auditing existing code against policy requirements

Do NOT use for:
- ❌ High-level SDLC planning (use secure-development-lifecycle)
- ❌ Runtime security monitoring (use incident-response)
- ❌ Architecture-level security design (use security-architecture-validation)

## Policy Requirements

### 1. Static Application Security Testing (SAST)

**Required Tools:**
| Tool | Purpose | CI Integration | Blocking? |
|---|---|---|---|
| CodeQL | Semantic code analysis | GitHub Actions | Yes — zero critical/high |
| SonarCloud | Code quality + security | GitHub Actions | Yes — quality gate |
| SpotBugs | Java bug detection | Maven plugin | Advisory |

**CodeQL Configuration:**
```yaml
# .github/workflows/codeql.yml
- uses: github/codeql-action/analyze@v3
  with:
    languages: java
    queries: +security-extended
```

**SonarCloud Quality Gate:**
```
# Required passing conditions
- No new critical issues
- No new high security hotspots
- Coverage ≥ 80% on new code
- Duplication < 3% on new code
```

**Developer Actions:**
```
□ Run CodeQL locally before pushing: codeql database create --language=java
□ Check SonarCloud dashboard after PR creation
□ Address all critical/high findings before merge
□ Document false positives with justification
```

### 2. Dynamic Application Security Testing (DAST)

**Required Scanning:**
| Scan Type | Tool | Frequency | Scope |
|---|---|---|---|
| Baseline | OWASP ZAP | Every release | Full application |
| API Scan | OWASP ZAP | Every release | REST endpoints |
| Authenticated | OWASP ZAP | Quarterly | Admin workflows |

**ZAP Integration:**
```yaml
# GitHub Actions ZAP scan
- name: ZAP Scan
  uses: zaproxy/action-baseline@v0.14.0
  with:
    target: 'https://staging.cia.hack23.com'
    rules_file_name: '.zap/rules.tsv'
```

**Handling DAST Findings:**
1. Critical/High — Block release, fix immediately
2. Medium — Fix within current sprint
3. Low/Informational — Track in backlog, fix within 30 days

### 3. Dependency Scanning

**Required Checks:**
| Tool | Purpose | Trigger | Blocking? |
|---|---|---|---|
| OWASP Dependency Check | Known CVE detection | PR + weekly | Yes — critical/high |
| Dependabot | Automated updates | Continuous | Advisory |
| GitHub Advisory DB | Vulnerability alerts | Continuous | Yes — critical |
| License Check | License compliance | PR | Yes — incompatible |

**Maven Configuration:**
```xml
<plugin>
    <groupId>org.owasp</groupId>
    <artifactId>dependency-check-maven</artifactId>
    <configuration>
        <failBuildOnCVSS>7</failBuildOnCVSS>
        <suppressionFile>dependency-check-suppression.xml</suppressionFile>
    </configuration>
</plugin>
```

**Adding New Dependencies — Mandatory Checklist:**
```
□ Check GitHub Advisory DB for known vulnerabilities
□ Verify license compatibility (Apache 2.0 preferred)
□ Run: mvn dependency-check:check
□ Review transitive dependencies
□ Document justification in PR description
□ Verify no critical/high CVEs
□ Update SBOM if maintained
```

### 4. Code Signing and Integrity

**Commit Signing:**
```bash
# All commits should be signed
git config --global commit.gpgsign true
git config --global user.signingkey <GPG-KEY-ID>
```

**Artifact Integrity:**
- Maven builds produce checksums for all artifacts
- Release artifacts are signed
- Docker images use content trust
- CloudFormation templates are versioned and checksummed

### 5. Secure Coding Standards

**Mandatory Practices:**

| Practice | Enforcement | Tool |
|---|---|---|
| Input validation | All user inputs | Spring Validation + custom |
| Output encoding | All rendered data | Vaadin built-in + manual |
| Parameterized queries | All database access | JPA/Hibernate |
| Error handling | No info leakage | Custom exception handlers |
| Logging | No sensitive data | SLF4J + Logback |
| Authentication | Spring Security | Framework default |
| CSRF protection | All state changes | Spring Security |

**Banned Patterns:**
```java
// BANNED: String concatenation in queries
entityManager.createQuery("SELECT p FROM Person p WHERE p.id = " + id);

// BANNED: Logging sensitive data
log.info("User login: " + username + " password: " + password);

// BANNED: Disabling CSRF
http.csrf().disable(); // Only if explicitly justified and documented

// BANNED: Hardcoded credentials
private static final String API_KEY = "sk-1234567890";
```

## Policy Compliance Verification

### PR Review Checklist
```
□ CodeQL scan passes (zero critical/high alerts)
□ SonarCloud quality gate passes
□ OWASP Dependency Check passes (CVSS < 7)
□ No banned code patterns detected
□ Input validation present for all user inputs
□ Output encoding verified for rendered data
□ No secrets or credentials in code
□ Commit is signed (GPG or SSH)
□ Test coverage meets thresholds (80% line, 70% branch)
□ Security-relevant changes documented
```

### Quarterly Audit Checklist
```
□ All dependencies updated to latest stable
□ No open critical/high Dependabot alerts
□ DAST scan completed and findings addressed
□ Security documentation current
□ ISMS controls verified
□ Threat model reviewed and updated
□ Access controls reviewed
```

## ISMS Alignment

| Policy Area | ISO 27001 | NIST CSF | CIS Controls |
|---|---|---|---|
| SAST | A.8.28, A.8.29 | PR.IP-12 | CIS 16.4 |
| DAST | A.8.29, A.8.33 | DE.CM-8 | CIS 16.6 |
| Dependency Scan | A.8.19, A.8.28 | ID.SC-2 | CIS 16.7 |
| Code Signing | A.8.24, A.8.26 | PR.DS-6 | CIS 2.7 |
| Secure Coding | A.8.25, A.8.28 | PR.IP-12 | CIS 16.1 |

## References

- [Hack23 Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)
- [OWASP Secure Coding Practices](https://owasp.org/www-project-secure-coding-practices-quick-reference-guide/)
- [CIS Software Supply Chain Security Guide](https://www.cisecurity.org/benchmark/software-supply-chain-security)
- [NIST SP 800-218 SSDF](https://csrc.nist.gov/publications/detail/sp/800-218/final)
