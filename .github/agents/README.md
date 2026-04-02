# 🤖 Custom Agent Profiles

This directory contains custom agent profiles for GitHub Copilot, designed to provide domain-specific expertise for the Citizen Intelligence Agency project.

## 📋 Overview

| Agent | Expertise | Primary Use |
|-------|-----------|-------------|
| [task-agent](task-agent.md) | Product quality, GitHub issues, ISMS compliance | Issue creation, quality analysis, Copilot task delegation |
| [stack-specialist](stack-specialist.md) | Java 21, Spring, Vaadin, Hibernate, PostgreSQL, Maven | Code implementation, architecture decisions, debugging |
| [ui-enhancement-specialist](ui-enhancement-specialist.md) | Vaadin, data visualization, accessibility (WCAG 2.1 AA) | UI improvements, chart design, responsive layouts |
| [intelligence-operative](intelligence-operative.md) | Political science, OSINT, Swedish politics, risk analysis | Analytical frameworks, data source integration, intelligence products |
| [business-development-specialist](business-development-specialist.md) | Strategic planning, partnerships, revenue models | Business strategy, market analysis, sustainability planning |
| [marketing-specialist](marketing-specialist.md) | Digital marketing, content strategy, community building | Brand positioning, SEO, developer relations, growth |

## 🚀 Usage

Agents are automatically available in GitHub Copilot conversations. Reference them by name or invoke them using the custom-agent tool:

```
@task-agent Analyze SonarCloud results and create prioritized improvement issues
@stack-specialist How should I implement a new JPA entity for committee membership?
@ui-enhancement-specialist Review this Vaadin view for WCAG 2.1 AA compliance
@intelligence-operative Design a risk scoring framework for politician financial disclosures
```

## 🏗️ Agent Architecture

All agents share these common principles:

1. **Context-aware**: Read README.md, copilot-setup-steps.yml, and copilot-mcp-config.json before starting work
2. **Skills-integrated**: Reference appropriate skills from `.github/skills/` (79 available)
3. **ISMS-compliant**: Align with ISO 27001, NIST CSF, CIS Controls, GDPR
4. **Evidence-based**: Base recommendations on data, metrics, and verifiable facts
5. **Security-first**: Follow Hack23 Secure Development Policy

## 📚 Related Resources

- [Skills Library](../skills/) — 79 strategic skills for security, testing, architecture, and compliance
- [Copilot Instructions](../copilot-instructions.md) — Global coding standards and rules
- [Copilot MCP Config](../copilot-mcp-config.json) — MCP server configuration
- [README](../../README.md) — Project overview and documentation
- [Hack23 Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)
