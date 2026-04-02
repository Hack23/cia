# CIA GitHub Copilot Skills Library

This directory contains 79 comprehensive agent skills for the Citizen Intelligence Agency platform, following GitHub Copilot's December 2025 Agent Skills feature best practices.

## Skills Overview

### 🔒 Security-by-Design (8 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [secure-code-review](secure-code-review/) | OWASP Top 10, SAST/DAST patterns | Code reviews, security audits |
| [threat-modeling](threat-modeling/) | STRIDE, attack trees, security architecture | Feature design, risk assessment |
| [secrets-management](secrets-management/) | Never commit secrets, vault usage | Credential management, key rotation |
| [input-validation](input-validation/) | Sanitization, XSS, SQL injection prevention | User input handling, API security |
| [crypto-best-practices](crypto-best-practices/) | Encryption, hashing, key management | Data protection, authentication |
| [vulnerability-management](vulnerability-management/) | Systematic vulnerability lifecycle management with SLAs: Critical 7d, High 30d | Dependabot response, OWASP findings, security patching |
| [incident-response](incident-response/) | Security incident detection, containment, recovery per NIST SP 800-61r2 | Security breaches, GDPR notifications, incident handling |
| [cryptography-policy](cryptography-policy/) | TLS 1.3, AES-256-GCM, bcrypt, RSA-4096, key management per NIST FIPS 140-2 | Encryption implementation, TLS configuration, key rotation |

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

### ⚙️ CI/CD & DevOps (4 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [github-actions-workflows](github-actions-workflows/) | CI/CD with Java 26, Maven, PostgreSQL 18, SHA-pinned actions, SLSA 3 | Pipeline creation, security scans, deployments |
| [github-agentic-workflows](github-agentic-workflows/) | GitHub Agentic Workflows (gh-aw) — markdown-defined AI automation with 5-layer security, safe outputs, multi-engine (Copilot/Claude/Codex/Gemini) | Issue triage, code quality, docs maintenance, security scanning, metrics |
| [maven-build-management](maven-build-management/) | Multi-module builds | Dependency management, build configuration |
| [postgresql-operations](postgresql-operations/) | Database management | Schema migrations, performance tuning |

### 🤝 Open Source & Community (3 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [contribution-guidelines](contribution-guidelines/) | PR process, code review | Contributing, reviewing PRs |
| [documentation-standards](documentation-standards/) | Markdown, diagrams, clarity | Documentation writing, API docs |
| [issue-triage-workflow](issue-triage-workflow/) | Labeling, assignment, prioritization | Issue management, bug tracking |

### 🔍 Intelligence & OSINT (10 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [political-science-analysis](political-science-analysis/) | Comparative politics, political behavior, public policy analysis | Democratic accountability, institutional performance, voting behavior |
| [osint-methodologies](osint-methodologies/) | OSINT collection, source evaluation, data integration, verification | Political data gathering, source credibility assessment |
| [intelligence-analysis-techniques](intelligence-analysis-techniques/) | ACH, SWOT, Devil's Advocacy, Red Team, structured analytics | Competing hypotheses, strategic assessment, bias mitigation |
| [swedish-political-system](swedish-political-system/) | Riksdag structure, party system, electoral system, government formation | Swedish political context, coalition analysis, parliamentary procedures |
| [data-science-for-intelligence](data-science-for-intelligence/) | Statistical analysis, ML, NLP, time series, network analysis | Political data analysis, forecasting, pattern recognition |
| [electoral-analysis](electoral-analysis/) | Election forecasting, campaign analysis, coalition prediction | Electoral outcomes, voter behavior, seat projection |
| [behavioral-analysis](behavioral-analysis/) | Political psychology, cognitive biases, leadership analysis | Voting psychology, group dynamics, decision-making patterns |
| [strategic-communication-analysis](strategic-communication-analysis/) | Narrative analysis, media analysis, discourse analysis | Media bias detection, framing analysis, influence assessment |
| [legislative-monitoring](legislative-monitoring/) | Voting pattern analysis, committee effectiveness, bill tracking | Parliamentary oversight, legislative productivity, cross-party collaboration |
| [risk-assessment-frameworks](risk-assessment-frameworks/) | Political risk, institutional risk, corruption indicators, early warning | Democratic backsliding, corruption detection, coalition stability |

### 📊 Product Management & Agile (1 skill)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [product-management-patterns](product-management-patterns/) | User stories, backlog management, sprint planning, stakeholder communication | Product planning, agile development, requirements gathering |

### ☁️ Cloud Operations (1 skill)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [aws-cloudwatch-monitoring](aws-cloudwatch-monitoring/) | CloudWatch metrics, alarms, dashboards, log insights, application monitoring | Performance monitoring, alerting, troubleshooting |

### 🎨 UI/UX Excellence (2 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [accessibility-wcag-patterns](accessibility-wcag-patterns/) | WCAG 2.1 AA compliance, ARIA attributes, keyboard navigation, screen readers | Accessible UI design, inclusive design |
| [data-visualization-principles](data-visualization-principles/) | Chart selection, color theory, dashboard design, data storytelling | Political data visualization, dashboards |

### 🎯 Testing & Quality Assurance (1 skill)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [playwright-ui-testing](playwright-ui-testing/) | Playwright automation, visual regression, accessibility testing, E2E workflows | UI testing, browser automation, quality assurance |

### 💼 Business Strategy (1 skill)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [business-model-canvas](business-model-canvas/) | Value proposition, customer segments, revenue streams, business model design | Strategic planning, revenue modeling, sustainability |

### 📢 Marketing & Growth (1 skill)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [seo-best-practices](seo-best-practices/) | On-page SEO, technical SEO, keyword research, content optimization | Search visibility, organic growth, content strategy |

### 🤖 AI & MCP Skills (4 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [ai-governance](ai-governance/) | EU AI Act compliance, OWASP LLM security, responsible AI practices | GitHub Copilot agents, AI risk assessment, LLM security |
| [mcp-server-development](mcp-server-development/) | MCP server patterns, tool development, GitHub MCP integration | MCP tool creation, server configuration, AI tool integration |
| [mcp-gateway-configuration](mcp-gateway-configuration/) | Multi-server MCP setup, tool routing, access control | MCP gateway setup, multi-agent orchestration |
| [mcp-gateway-security](mcp-gateway-security/) | MCP security patterns, token management, request validation | MCP communications security, audit logging |

### 🛡️ Advanced Security (6 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [security-by-design](security-by-design/) | STRIDE methodology, defense in depth, security in SDLC | Feature design, architecture review, threat prevention |
| [aws-security-architecture](aws-security-architecture/) | VPC security, IAM, KMS, CloudTrail, GuardDuty | AWS deployment, cloud security, infrastructure hardening |
| [secure-development-lifecycle](secure-development-lifecycle/) | Secure SDLC phases, security requirements, testing integration | Development lifecycle, security gates, release management |
| [secure-development-policy](secure-development-policy/) | Hack23 secure development policy, SAST/DAST, dependency scanning | Policy enforcement, security scanning, code signing |
| [security-architecture-validation](security-architecture-validation/) | Security architecture review, control validation, pen testing | Architecture audits, compliance verification, security reviews |
| [ci-cd-security](ci-cd-security/) | Pipeline security, supply chain, SLSA compliance, artifact signing | CI/CD hardening, supply chain security, build integrity |

### 📊 Compliance & Governance (5 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [compliance-frameworks](compliance-frameworks/) | Multi-framework compliance (ISO 27001, NIST CSF, CIS, GDPR, NIS2, EU CRA) | Compliance mapping, audit preparation, control verification |
| [hack23-isms-compliance](hack23-isms-compliance/) | Hack23 org-wide ISMS compliance requirements, policy enforcement | Organization compliance, audit preparation, policy alignment |
| [compliance-framework-alignment](compliance-framework-alignment/) | Cross-framework control mapping and alignment | Control harmonization, multi-standard compliance |
| [classification-framework-enforcement](classification-framework-enforcement/) | Data classification enforcement, sensitivity labeling | Data handling, information classification, access controls |
| [testing-strategy-enforcement](testing-strategy-enforcement/) | Test coverage enforcement (80% line, 70% branch), CI gates | Quality gates, coverage enforcement, test governance |

### 📐 Architecture & Data Engineering (5 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [hack23-future-architecture-standards](hack23-future-architecture-standards/) | Hack23 C4 model requirements, architecture documentation standards | Architecture docs, ADRs, future state planning |
| [data-pipeline-engineering](data-pipeline-engineering/) | ETL processes, Spring Integration, batch processing | Data processing, political data pipelines, data loading |
| [cia-data-integration](cia-data-integration/) | CIA data model, riksdag data processing, entity mapping | Data integration, political entity mapping, data quality |
| [advanced-data-visualization](advanced-data-visualization/) | Advanced charts, political data visualization, time series | Dashboard design, political trends, data storytelling |
| [api-integration](api-integration/) | External API patterns, retry logic, circuit breakers, caching | Government API integration, data fetching, resilience |

### 🔧 Quality & Performance (4 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [performance-optimization](performance-optimization/) | JVM tuning, database query optimization, Vaadin performance | Application performance, response time, resource efficiency |
| [product-quality-analysis](product-quality-analysis/) | SonarCloud analysis, technical debt, quality gates | Quality metrics, tech debt management, code quality |
| [data-protection](data-protection/) | Data classification (CIA triad), GDPR privacy, encryption | Data handling, privacy by design, encryption standards |
| [ui-ux-design-system](ui-ux-design-system/) | Vaadin design system, component library, accessibility | UI consistency, design patterns, accessible components |

### 🌐 Cross-Platform Integration (2 skills)

| Skill | Description | Use Cases |
|-------|-------------|-----------|
| [european-parliament-api](european-parliament-api/) | EU Parliament open data API, MEP data, voting records | Cross-parliament analysis, EU political monitoring |
| [typescript-strict-patterns](typescript-strict-patterns/) | TypeScript strict mode, type safety, type guards | Frontend tooling, CI/CD scripts, type-safe integrations |

## Agent Cross-Reference

The skills library is designed to support 6 specialized agents in the CIA platform. Each agent references relevant skills for their domain:

### 📋 Task Agent (23 skills)
**Primary Role**: Product quality, GitHub issue management, ISMS compliance  
**Key Skills**: All security/ISMS skills (10), testing/quality (4), DevOps (3), community (3), architecture docs (3)  
**See**: [.github/agents/task-agent.md](../agents/task-agent.md)

### 🛠️ Stack Specialist (22 skills)
**Primary Role**: Java/Spring/PostgreSQL technical expertise  
**Key Skills**: All security/ISMS skills (10), architecture/framework (4), testing (3), DevOps (3), documentation (2)  
**See**: [.github/agents/stack-specialist.md](../agents/stack-specialist.md)

### 🎨 UI Enhancement Specialist (17 skills)
**Primary Role**: Vaadin UI/UX, accessibility, data visualization  
**Key Skills**: All security/ISMS skills (10), UI frameworks (3), testing (3), documentation (1)  
**See**: [.github/agents/ui-enhancement-specialist.md](../agents/ui-enhancement-specialist.md)

### 🔍 Intelligence Operative (21 skills)
**Primary Role**: Political analysis, OSINT, intelligence methodologies  
**Key Skills**: All security/ISMS skills (10), all intelligence/OSINT skills (10), documentation (1)  
**See**: [.github/agents/intelligence-operative.md](../agents/intelligence-operative.md)

### 💰 Business Development Specialist (16 skills)
**Primary Role**: Strategic planning, partnerships, revenue models  
**Key Skills**: All security/ISMS skills (10), market context (3), documentation (3)  
**See**: [.github/agents/business-development-specialist.md](../agents/business-development-specialist.md)

### 📢 Marketing Specialist (15 skills)
**Primary Role**: Digital marketing, content strategy, community building  
**Key Skills**: All security/ISMS skills (10), audience understanding (3), technical marketing (2)  
**See**: [.github/agents/marketing-specialist.md](../agents/marketing-specialist.md)

**For detailed agent-skill mappings, see [.github/agents/README.md](../agents/README.md#agent-skills-matrix)**

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

**Last Updated**: February 2026  
**Skill Count**: 79  
**Compliance Standards**: ISO 27001:2022, NIST CSF 2.0, CIS Controls v8, GDPR, NIS2, EU CRA
