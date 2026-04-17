# Copilot Instructions for Citizen Intelligence Agency

## Context Files — Read First

**ALWAYS read at the start of EVERY task:**
1. **[README.md](../README.md)** — Project overview, features, docs
2. **[copilot-setup-steps.yml](workflows/copilot-setup-steps.yml)** — Build environment (Java 26, Maven 3.9.15, PostgreSQL 18)
3. **[copilot-mcp-config.json](copilot-mcp-config.json)** — MCP servers (GitHub, filesystem, memory, sequential-thinking, playwright)
4. **[skills/](skills/)** — 80 skills for security, testing, architecture, compliance (see [skills/README.md](skills/README.md))
5. **[agents/](agents/)** — 6 specialized agents (see [agents/README.md](agents/README.md))
6. **[Hack23 Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md)** — apex ISMS policy (via `hack23-information-security-policy` skill)

## Project Overview

**Citizen Intelligence Agency** — volunteer-driven OSINT platform analyzing Swedish political activities. Monitors politicians and institutions with financial metrics, risk analytics, trend analysis, and transparency insights. Strictly independent and non-partisan.

| Component | Version/Detail |
|-----------|---------------|
| **Backend** | Java 26 (source 21), Spring Framework 5.x, Hibernate/JPA |
| **UI** | Vaadin |
| **Database** | PostgreSQL 18 (pgaudit, pgcrypto, pg_stat_statements) |
| **Build** | Maven 3.9.15, 49+ modules |
| **External APIs** | Riksdagen, Swedish Election Authority, World Bank, ESV |
| **Security** | OpenSSF 7.2/10, SLSA 3, CII Best Practices, zero critical CVEs 5+ years |

## Build Commands

```bash
mvn clean install              # Full build with tests
mvn clean install -DskipTests  # Build without tests
mvn test -Dtest='!**ITest*,!**/XmlDateTypeAdapterTest,!**/XmlTimeTypeAdapterTest,!**/XmlDateTimeTypeAdapterTest'  # Tests only (excludes integration and Xml*TypeAdapterTest)
mvn clean test jacoco:report   # Tests with coverage
mvn dependency-check:check     # OWASP dependency scan
mvn site                       # Generate site documentation
```

**Database changes**: Follow `service.data.impl/README-SCHEMA-MAINTENANCE.md`. Never manually edit `full_schema.sql` — always regenerate via `pg_dump`.

## Coding Standards

- **Java**: Source level 21, runtime 26. Constructor injection preferred. `@Service`, `@Repository`, `@Controller` annotations. `@Transactional` for DB operations.
- **JPA**: Entities in `model.*` packages. Proper annotations. Appropriate fetch types. Avoid N+1 queries.
- **Style**: Meaningful names. JavaDoc for public APIs. Minimal comments. Follow existing patterns.
- **Security**: Never commit secrets. Parameterized queries. Input validation. Output encoding. Spring Security for access control. GDPR compliance.

## Quality Requirements

| Metric | Threshold |
|--------|-----------|
| Line coverage | ≥ 80% |
| Branch coverage | ≥ 70% |
| Cyclomatic complexity | < 10 |
| Code duplication | < 3% |
| Critical SonarCloud issues | 0 |
| Critical/High CVEs | 0 |

## Mandatory Rules

### 1. Minimal, Surgical Changes
Change only what's needed. Don't refactor unrelated code. Review impact before committing.

### 2. Validate Before Committing
Compile (`mvn clean compile`), test relevant areas, check `git diff`, verify no secrets.

### 3. Security is Non-Negotiable
Pass CodeQL + OWASP checks. Validate all inputs. Parameterized queries. Encode outputs. Update SECURITY_ARCHITECTURE.md and THREAT_MODEL.md when relevant.

### 4. ISMS Compliance
Align with ISO 27001:2022, NIST CSF 2.0, CIS Controls v8, GDPR. Update security documentation for security-related changes.

The **[Hack23 Information Security Policy (ISP)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) is the apex** policy — every change must defer to it. It is implemented through the supporting policies below; consult the ones relevant to your task. See the [`hack23-information-security-policy`](skills/hack23-information-security-policy/SKILL.md) skill for a developer-facing mapping.

| Concern | Policy | Primary Skill |
|---------|--------|---------------|
| Apex (everything) | [Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) | `hack23-information-security-policy` |
| SDLC / code / build | [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) | `secure-development-policy` |
| Open-source posture | [Open Source Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Open_Source_Policy.md) | `open-source-policy` |
| Secrets / tokens | [Secrets Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secrets_Management_Policy.md) | `secrets-management` |
| TLS / crypto / hashing | [Cryptography Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Cryptography_Policy.md) | `cryptography-policy` / `crypto-best-practices` |
| AuthN / AuthZ | [Access Control](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Access_Control_Policy.md) | `access-control-policy` |
| Data labelling | [CLASSIFICATION](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) | `classification-framework-enforcement` |
| Personal data | [Data Protection](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Data_Protection_Policy.md), [Privacy Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Privacy_Policy.md) | `data-protection`, `gdpr-compliance` |
| CVE triage | [Vulnerability Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md) | `vulnerability-management` |
| Releases / migrations | [Change Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Change_Management.md) | `change-management` |
| Backup / DR | [Backup Recovery](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Backup_Recovery_Policy.md) | `backup-recovery-policy` |
| Incidents | [Incident Response Plan](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Incident_Response_Plan.md) | `incident-response` |
| Threat modelling | [Threat Modeling](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Threat_Modeling.md) | `threat-modeling` |
| Third-party / deps | [Third Party Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Third_Party_Management.md) | `open-source-policy` |
| AI / Copilot agents | [AI Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/AI_Policy.md) | `ai-governance` |
| Strategy | [Information Security Strategy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Strategy.md) | `information-security-strategy` |

### 5. Test Everything
Unit tests for all new functionality. Maintain or improve coverage. JUnit 5, Mockito. Follow existing test patterns.

### 6. Use Skills and Agents
Check relevant skills before implementing. Delegate to specialized agents when appropriate:
- **ISMS / policy**: hack23-information-security-policy (apex), hack23-isms-compliance, compliance-frameworks
- **Security**: secure-code-review, security-by-design, input-validation, threat-modeling, vulnerability-management
- **Architecture**: spring-framework-patterns, jpa-hibernate-optimization, vaadin-component-design
- **Testing**: unit-testing-patterns, testing-strategy-enforcement, playwright-ui-testing
- **CI/CD**: github-actions-workflows, github-agentic-workflows, ci-cd-security
- **AI governance**: ai-governance (AI Policy + OWASP LLM + EU AI Act)

### 7. Clear Commit Messages
Format: `<type>: <description>` — Types: feat, fix, docs, style, refactor, test, chore

## Architecture Documentation (Hack23 Standard)

Every repository maintains C4 model documentation:

**Current**: ARCHITECTURE.md, DATA_MODEL.md, FLOWCHART.md, STATEDIAGRAM.md, MINDMAP.md, SWOT.md
**Future**: FUTURE_ARCHITECTURE.md, FUTURE_DATA_MODEL.md, FUTURE_FLOWCHART.md, FUTURE_STATEDIAGRAM.md, FUTURE_MINDMAP.md, FUTURE_SWOT.md
**Security**: SECURITY_ARCHITECTURE.md, FUTURE_SECURITY_ARCHITECTURE.md, THREAT_MODEL.md

## Decision Framework

When uncertain:
1. Check relevant skill in `.github/skills/`
2. Review similar code patterns in the repository
3. Consult ARCHITECTURE.md, SECURITY_ARCHITECTURE.md
4. Apply security-by-design (deny by default, validate input, encrypt data)
5. Follow ISMS requirements
6. Only then ask for clarification
