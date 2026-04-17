# 🤖 Custom Agent Profiles

This directory contains repository-level GitHub Copilot custom agents for the Citizen Intelligence Agency platform. Each agent provides domain-specific expertise and enforces the Hack23 Information Security Policy (ISP) and its supporting ISMS policies.

> **Scope:** These are **repository-level** agents (`.github/agents/*.md`). Per Hack23 policy, repo-level agents do **not** declare `mcp-servers:` blocks — MCP configuration is handled by the environment via `.github/copilot-mcp-config.json`. All agents declare `tools: ["*"]` so the Copilot runtime can grant full tool access.

## 📋 Agent Overview

| Agent | Expertise | Primary Use |
|-------|-----------|-------------|
| [task-agent](task-agent.md) | Product quality, GitHub issues, Copilot orchestration, ISMS | Issue engineering, quality analysis, Copilot task delegation (base_ref, stacked PRs) |
| [stack-specialist](stack-specialist.md) | Java 21/26, Spring, Vaadin, Hibernate/JPA, PostgreSQL 18, Maven 3.9.15 | Implementation, refactoring, debugging, SDLC security gates |
| [ui-enhancement-specialist](ui-enhancement-specialist.md) | Vaadin, data visualization, WCAG 2.1 AA, secure rendering, privacy-by-design | UI/UX improvements, charts, accessibility, XSS-safe components |
| [intelligence-operative](intelligence-operative.md) | Political science, OSINT, intelligence analysis, risk frameworks, Swedish politics | Analytical frameworks, data source integration, classified intelligence products |
| [business-development-specialist](business-development-specialist.md) | Strategic planning, partnerships, revenue models, market expansion | Business strategy, licensing, partner due-diligence |
| [marketing-specialist](marketing-specialist.md) | Digital marketing, content strategy, community building, brand positioning | Neutral, privacy-respecting storytelling; OSS posture marketing |

## 🚀 Usage

Agents are automatically available in GitHub Copilot conversations. Reference them by name:

```
@task-agent Analyze SonarCloud results and create prioritized improvement issues
@task-agent Assign issue #8033 to Copilot on branch feature/isp-integration with custom instructions
@stack-specialist How should I implement a new JPA entity for committee membership?
@ui-enhancement-specialist Review this Vaadin view for WCAG 2.1 AA compliance
@intelligence-operative Design a risk scoring framework for politician financial disclosures
@business-development-specialist Evaluate a partnership request from an academic institution
@marketing-specialist Draft a release announcement for the new risk dashboard
```

## 🏗️ Agent Architecture — Shared Principles

All agents follow these common principles:

1. **Context-aware** — read `README.md`, `copilot-instructions.md`, `copilot-setup-steps.yml`, and `copilot-mcp-config.json` before work
2. **ISP-first** — the [Hack23 Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) is the apex — every change defers to it
3. **Skills-integrated** — reference the `.github/skills/` library (80 skills, see [skills README](../skills/README.md))
4. **Evidence-based** — recommendations grounded in data, metrics, scanner output, ISMS controls
5. **Security-first** — align with Secure Development Policy, Open Source Policy, Secrets Management, Cryptography Policy, Access Control
6. **Classification-aware** — every artefact is labelled per [CLASSIFICATION](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md)
7. **Non-partisan** — outputs analyse all parties / politicians with equal rigor
8. **Human-in-the-loop** — Critical / High security changes require human approval

## 🗺️ ISMS Policy × Agent Matrix

Every agent has explicit responsibility for specific ISMS policies. The [Information Security Policy (ISP)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) is the apex for all.

| Policy | task-agent | stack-specialist | ui-enhancement | intelligence-operative | business-dev | marketing |
|--------|:----------:|:----------------:|:--------------:|:----------------------:|:------------:|:---------:|
| **[Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md)** (Apex) | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) | ✅ | ✅ | ✅ | ○ | ○ | ○ |
| [Open Source Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Open_Source_Policy.md) | ✅ | ✅ | ○ | ○ | ✅ | ✅ |
| [Secrets Management Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secrets_Management_Policy.md) | ✅ | ✅ | ○ | ○ | ○ | ○ |
| [Cryptography Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Cryptography_Policy.md) | ○ | ✅ | ○ | ○ | ○ | ○ |
| [Access Control Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Access_Control_Policy.md) | ✅ | ✅ | ✅ | ○ | ○ | ○ |
| [CLASSIFICATION](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| [Data Protection Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Data_Protection_Policy.md) | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| [Privacy Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Privacy_Policy.md) | ○ | ○ | ✅ | ✅ | ○ | ✅ |
| [Vulnerability Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md) | ✅ | ✅ | ○ | ○ | ○ | ✅ |
| [Change Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Change_Management.md) | ✅ | ✅ | ○ | ○ | ✅ | ○ |
| [Third Party Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Third_Party_Management.md) | ○ | ✅ | ○ | ○ | ✅ | ○ |
| [Incident Response Plan](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Incident_Response_Plan.md) | ✅ | ✅ | ○ | ✅ | ○ | ✅ |
| [Threat Modeling](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Threat_Modeling.md) | ✅ | ✅ | ✅ | ○ | ○ | ○ |
| [AI Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/AI_Policy.md) | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| [Information Security Strategy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Strategy.md) | ○ | ○ | ○ | ✅ | ✅ | ✅ |

**Legend:** ✅ primary owner / must comply · ○ secondary / consult as needed

## 🔗 Top Skills per Agent

For the full 80-skill library, see [.github/skills/README.md](../skills/README.md).

### task-agent
- `hack23-information-security-policy`, `hack23-isms-compliance`, `secure-development-policy`, `open-source-policy`
- `vulnerability-management`, `code-quality-checks`, `testing-strategy-enforcement`, `product-quality-analysis`
- `issue-triage-workflow`, `product-management-patterns`
- `playwright-ui-testing`, `accessibility-wcag-patterns`
- `github-actions-workflows`, `github-agentic-workflows`, `ai-governance`

### stack-specialist
- `hack23-information-security-policy`, `secure-development-policy`, `secrets-management`, `cryptography-policy`
- `spring-framework-patterns`, `jpa-hibernate-optimization`, `postgresql-operations`
- `maven-build-management`, `github-actions-workflows`, `ci-cd-security`
- `unit-testing-patterns`, `integration-testing`, `performance-optimization`, `api-integration`
- `secure-code-review`, `input-validation`, `threat-modeling`, `vulnerability-management`

### ui-enhancement-specialist
- `hack23-information-security-policy`, `secure-development-policy`
- `vaadin-component-design`, `ui-ux-design-system`
- `accessibility-wcag-patterns`, `data-visualization-principles`, `advanced-data-visualization`
- `playwright-ui-testing`, `e2e-testing`, `input-validation`
- `data-protection`, `gdpr-compliance`, `classification-framework-enforcement`, `performance-optimization`

### intelligence-operative
- `hack23-information-security-policy`
- `swedish-political-system`, `political-science-analysis`, `electoral-analysis`, `legislative-monitoring`
- `osint-methodologies`, `intelligence-analysis-techniques`, `behavioral-analysis`
- `risk-assessment-frameworks`, `risk-assessment-methodology`, `strategic-communication-analysis`, `data-science-for-intelligence`
- `data-protection`, `gdpr-compliance`, `classification-framework-enforcement`, `ai-governance`
- `european-parliament-api`, `cia-data-integration`

### business-development-specialist
- `hack23-information-security-policy`, `information-security-strategy`
- `business-model-canvas`, `product-management-patterns`
- `open-source-policy`, `compliance-frameworks`, `hack23-isms-compliance`
- `gdpr-compliance`, `data-protection`, `cia-data-integration`, `european-parliament-api`

### marketing-specialist
- `hack23-information-security-policy`
- `seo-best-practices`, `documentation-standards`, `contribution-guidelines`
- `open-source-policy`, `data-visualization-principles`, `accessibility-wcag-patterns`
- `gdpr-compliance`, `data-protection`, `ai-governance`

## 📐 Agent File Format

Each repository-level agent file uses this structure:

```yaml
---
name: agent-name                                    # kebab-case, unique
description: One-sentence role summary (≤200 chars)
tools: ["*"]                                        # always allow all tools
---
# Role header + mission
**Always read first:** …context files…
## Core Expertise
## ISMS Policy Integration        ← explicit mapping table
## (Domain-specific sections)
## Agent Handoff Matrix           ← who to delegate to
## Boundaries — Must NOT Do       ← red-lines
## Skills I Primarily Use
## Remember                       ← bullet key reminders
```

> Repository agents **do not** include `mcp-servers:`. MCP configuration lives in `.github/copilot-mcp-config.json` and is applied by the Copilot runtime.

## 📚 Related Resources

- [Skills Library](../skills/) — 80 strategic skills for security, testing, architecture, compliance, intelligence
- [Copilot Instructions](../copilot-instructions.md) — Global coding standards and ISMS integration
- [Copilot MCP Config](../copilot-mcp-config.json) — MCP server configuration (environment-level)
- [README](../../README.md) — Project overview and documentation
- [Hack23 Information Security Policy (Apex)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md)
- [Hack23 ISMS-PUBLIC](https://github.com/Hack23/ISMS-PUBLIC) — All Hack23 ISMS policies
