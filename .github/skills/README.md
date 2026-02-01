# CIA GitHub Copilot Skills Library

This directory contains 24 comprehensive agent skills for the Citizen Intelligence Agency platform, following GitHub Copilot's December 2025 Agent Skills feature best practices.

## Skills Overview

### 🔒 Security-by-Design (5 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [secure-code-review](secure-code-review/) | OWASP Top 10, SAST/DAST patterns | Code reviews, security audits |
| [threat-modeling](threat-modeling/) | STRIDE, attack trees, security architecture | Feature design, risk assessment |
| [secrets-management](secrets-management/) | Never commit secrets, vault usage | Credential management, key rotation |
| [input-validation](input-validation/) | Sanitization, XSS, SQL injection prevention | User input handling, API security |
| [crypto-best-practices](crypto-best-practices/) | Encryption, hashing, key management | Data protection, authentication |

### 📋 ISMS Compliance (5 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [iso-27001-controls](iso-27001-controls/) | Control implementation verification | Compliance audits, ISMS reviews |
| [nist-csf-mapping](nist-csf-mapping/) | Framework alignment | Risk management, security assessments |
| [cis-controls](cis-controls/) | Benchmark compliance | Security hardening, baseline establishment |
| [gdpr-compliance](gdpr-compliance/) | Data protection requirements | Privacy implementation, consent management |
| [security-documentation](security-documentation/) | Required docs (SECURITY_ARCHITECTURE.md, etc.) | Documentation maintenance, audits |

### 🧪 Testing & Quality (4 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [unit-testing-patterns](unit-testing-patterns/) | JUnit 5, Mockito, 80% coverage | Test implementation, TDD |
| [integration-testing](integration-testing/) | Spring test context, database testing | Component integration, API testing |
| [e2e-testing](e2e-testing/) | Selenium, Playwright patterns | UI testing, workflow validation |
| [code-quality-checks](code-quality-checks/) | SonarCloud, CodeQL integration | Quality gates, static analysis |

### 🏗️ Architecture & Design (4 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [spring-framework-patterns](spring-framework-patterns/) | DI, transaction management, AOP | Service design, Spring configuration |
| [jpa-hibernate-optimization](jpa-hibernate-optimization/) | Entity design, query optimization | Database performance, N+1 prevention |
| [vaadin-component-design](vaadin-component-design/) | UI patterns, lifecycle management | UI development, component design |
| [c4-architecture-documentation](c4-architecture-documentation/) | Required architecture docs | Architecture documentation, diagrams |

### ⚙️ CI/CD & DevOps (3 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [github-actions-workflows](github-actions-workflows/) | CI/CD patterns | Pipeline creation, automation |
| [maven-build-management](maven-build-management/) | Multi-module builds | Dependency management, build configuration |
| [postgresql-operations](postgresql-operations/) | Database management | Schema migrations, performance tuning |

### 🤝 Open Source & Community (3 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [contribution-guidelines](contribution-guidelines/) | PR process, code review | Contributing, reviewing PRs |
| [documentation-standards](documentation-standards/) | Markdown, diagrams, clarity | Documentation writing, API docs |
| [issue-triage-workflow](issue-triage-workflow/) | Labeling, assignment, prioritization | Issue management, bug tracking |

## Skill Structure

Each skill follows this standardized structure:

```markdown
---
name: skill-name
description: Clear, actionable description (max 200 chars)
license: Apache-2.0
---

# Skill Name

## Purpose
Why this skill exists and what problems it solves.

## When to Use
Specific scenarios with ✅ (do use) and ❌ (don't use) examples.

## Patterns & Examples
Code examples, decision trees, checklists with good/bad patterns.

## ISMS Compliance Mapping
ISO 27001, NIST CSF, CIS Controls references.

## Hack23 ISMS Policy References
Links to relevant policies in https://github.com/Hack23/ISMS-PUBLIC

## References
Official documentation, CIA project files, external resources.
```

## Usage in GitHub Copilot

GitHub Copilot automatically discovers skills in `.github/skills/` directories. Skills become available:

1. **In Chat**: Ask questions that match skill descriptions
   - "How do I implement OWASP Top 10 protections?"
   - "What's the proper way to handle secrets?"
   - "Show me JPA optimization patterns"

2. **During Development**: Copilot suggests patterns from skills
   - Writing authentication code → suggests secure-code-review patterns
   - Creating entities → suggests jpa-hibernate-optimization patterns
   - Writing tests → suggests unit-testing-patterns

3. **In Code Reviews**: Reference skills in review comments
   - "Please review secure-code-review skill for SQL injection prevention"
   - "See input-validation skill for proper sanitization"

## Design Principles

### Strategic, Not Tactical
Skills provide high-level guidance and decision frameworks, not step-by-step instructions.

### Rule-Based
Clear, enforceable rules with ✅/❌ examples rather than open-ended suggestions.

### Security-by-Design
All skills incorporate security considerations aligned with Hack23 ISMS policies.

### ISMS-Aligned
Every skill maps to ISO 27001:2022, NIST CSF, and CIS Controls where applicable.

### Composable
Skills can reference and build upon each other for complex scenarios.

## Compliance Mapping

All skills align with CIA platform's ISMS framework:

- **ISO 27001:2022**: Information security management controls
- **NIST Cybersecurity Framework**: Identify, Protect, Detect, Respond, Recover
- **CIS Controls v8**: Critical security controls
- **GDPR**: Data protection and privacy requirements

See [ISMS_COMPLIANCE_MAPPING.md](../../ISMS_COMPLIANCE_MAPPING.md) for detailed mappings.

## Contributing to Skills

### Adding New Skills

1. Create directory: `.github/skills/skill-name/`
2. Create `SKILL.md` with YAML frontmatter
3. Follow naming convention: lowercase-hyphen-separated
4. Include all required sections
5. Map to ISMS controls
6. Link to Hack23 policies
7. Provide code examples
8. Test with GitHub Copilot

### Updating Existing Skills

1. Keep skill descriptions under 200 characters
2. Maintain ✅/❌ pattern examples
3. Update ISMS mappings when standards change
4. Verify links to policies and documentation
5. Ensure code examples compile/run
6. Update references when project structure changes

### Quality Standards

- **Clarity**: Clear, concise language
- **Actionability**: Specific, enforceable guidance
- **Security**: Defense-in-depth principles
- **Compliance**: ISMS framework alignment
- **Maintainability**: Easy to update, version control friendly

## Related Documentation

- [SECURITY.md](../../SECURITY.md) - Security policy and reporting
- [SECURITY_ARCHITECTURE.md](../../SECURITY_ARCHITECTURE.md) - Security architecture
- [THREAT_MODEL.md](../../THREAT_MODEL.md) - Threat analysis
- [ARCHITECTURE.md](../../ARCHITECTURE.md) - System architecture
- [CONTRIBUTING.md](../../CONTRIBUTING.md) - Contribution guidelines
- [Hack23 ISMS Policies](https://github.com/Hack23/ISMS-PUBLIC) - Information security policies

## Skill Effectiveness Metrics

Track these metrics to measure skill effectiveness:

- **Adoption Rate**: How often skills are referenced in PRs/reviews
- **Security Improvements**: Reduction in vulnerabilities after skill implementation
- **Code Quality**: Improvement in SonarCloud/CodeQL scores
- **Compliance**: Faster ISMS audit completion
- **Developer Productivity**: Reduced time to implement security controls

## License

All skills are licensed under Apache License 2.0, consistent with the CIA project.

## Contact

- **Security Issues**: security@hack23.com
- **ISMS Questions**: isms@hack23.com
- **General Questions**: Create issue in https://github.com/Hack23/cia

---

**Last Updated**: January 2026  
**Skill Count**: 24  
**Compliance Standards**: ISO 27001:2022, NIST CSF 2.0, CIS Controls v8, GDPR
