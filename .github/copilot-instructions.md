# Copilot Instructions for Citizen Intelligence Agency

## Context Files — Read First

**ALWAYS read at the start of EVERY task:**
1. **[README.md](../README.md)** — Project overview, features, docs
2. **[copilot-setup-steps.yml](workflows/copilot-setup-steps.yml)** — Build environment (Java 26, Maven 3.9.14, PostgreSQL 18)
3. **[copilot-mcp-config.json](copilot-mcp-config.json)** — MCP servers (GitHub, filesystem, memory, playwright)
4. **[skills/](skills/)** — 79 skills for security, testing, architecture, compliance
5. **[agents/](agents/)** — 6 specialized agents (task, stack, UI, intelligence, business, marketing)

## Project Overview

**Citizen Intelligence Agency** — volunteer-driven OSINT platform analyzing Swedish political activities. Monitors politicians and institutions with financial metrics, risk analytics, trend analysis, and transparency insights. Strictly independent and non-partisan.

| Component | Version/Detail |
|-----------|---------------|
| **Backend** | Java 26 (source 21), Spring Framework 5.x, Hibernate/JPA |
| **UI** | Vaadin |
| **Database** | PostgreSQL 18 (pgaudit, pgcrypto, pg_stat_statements) |
| **Build** | Maven 3.9.14, 49+ modules |
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

### 5. Test Everything
Unit tests for all new functionality. Maintain or improve coverage. JUnit 5, Mockito. Follow existing test patterns.

### 6. Use Skills and Agents
Check relevant skills before implementing. Delegate to specialized agents when appropriate:
- **Security**: secure-code-review, security-by-design, input-validation, compliance-frameworks
- **Architecture**: spring-framework-patterns, jpa-hibernate-optimization, vaadin-component-design
- **Testing**: unit-testing-patterns, testing-strategy-enforcement, playwright-ui-testing
- **CI/CD**: github-actions-workflows, github-agentic-workflows, ci-cd-security

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
