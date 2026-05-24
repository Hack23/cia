---
name: hack23-future-architecture-standards
description: Hack23 organization architecture documentation standards, C4 model requirements, future state planning
license: Apache-2.0
---

# Hack23 Future Architecture Standards Skill

## Purpose

Define and enforce architecture documentation standards across the Hack23 organization, using the C4 model for system decomposition, future state planning with ADRs (Architecture Decision Records), and consistent documentation patterns across all repositories.

## When to Use

- ✅ Documenting system architecture for new or existing projects
- ✅ Creating Architecture Decision Records (ADRs)
- ✅ Planning future state architecture migrations
- ✅ Reviewing architecture documentation for completeness
- ✅ Generating C4 model diagrams for the CIA platform

Do NOT use for:
- ❌ Code-level documentation (use JavaDoc)
- ❌ Security-specific architecture (use security-documentation skill)
- ❌ Operational runbooks (use aws-cloudwatch-monitoring skill)

## C4 Model Requirements

### Level 1: System Context

Every Hack23 project **must** include a system context diagram showing:
- The system under description
- All external actors (users, systems)
- Data flows with protocols and formats

```mermaid
C4Context
    title CIA Platform - System Context

    Person("citizen, #quot;Citizen#quot;, #quot;Swedish citizen seeking political transparency#quot;")
    Person("analyst, #quot;Political Analyst#quot;, #quot;Researches political patterns#quot;")

    System("cia, #quot;CIA Platform#quot;, #quot;Political intelligence and analysis#quot;")

    System_Ext("riksdag, #quot;Riksdagen API#quot;, #quot;Swedish Parliament open data#quot;")
    System_Ext("val, #quot;Val.se#quot;, #quot;Swedish Election Authority#quot;")
    System_Ext("worldbank, #quot;World Bank#quot;, #quot;Economic indicators#quot;")
    System_Ext("esv, #quot;ESV#quot;, #quot;Swedish financial data#quot;")

    Rel("citizen, cia, #quot;Views political data#quot;, #quot;HTTPS#quot;")
    Rel("analyst, cia, #quot;Analyzes political trends#quot;, #quot;HTTPS#quot;")
    Rel("cia, riksdag, #quot;Fetches parliament data#quot;, #quot;HTTPS/JSON#quot;")
    Rel("cia, val, #quot;Fetches election data#quot;, #quot;HTTPS/JSON#quot;")
    Rel("cia, worldbank, #quot;Fetches economic data#quot;, #quot;HTTPS/JSON#quot;")
    Rel("cia, esv, #quot;Fetches financial data#quot;, #quot;HTTPS/JSON#quot;")
```

### Level 2: Container Diagram

Show major deployable units:

```mermaid
C4Container
    title CIA Platform - Container Diagram

    Container("webapp, #quot;Web Application#quot;, #quot;Vaadin/Spring#quot;, #quot;Political data dashboards#quot;")
    ContainerDb("db, #quot;PostgreSQL#quot;, #quot;Database#quot;, #quot;Political data store#quot;")
    Container("agent, #quot;Data Agent#quot;, #quot;Spring Integration#quot;, #quot;Data import pipelines#quot;")

    Rel("webapp, db, #quot;Reads/Writes#quot;, #quot;JDBC/JPA#quot;")
    Rel("agent, db, #quot;Writes imported data#quot;, #quot;JDBC/JPA#quot;")
```

### Level 3: Component Diagram

Required for complex modules showing internal components.

### Level 4: Code Diagram

Optional — only for critical/complex algorithms.

## Architecture Decision Records (ADRs)

### ADR Template

```markdown
# ADR-{number}: {Title}

## Status
{Proposed | Accepted | Deprecated | Superseded by ADR-xxx}

## Context
{What is the issue? Why do we need to make a decision?}

## Decision
{What is the change that we're proposing and/or doing?}

## Consequences
### Positive
- {Benefit 1}
- {Benefit 2}

### Negative
- {Trade-off 1}
- {Trade-off 2}

### Risks
- {Risk 1 and mitigation}
```

### ADR Naming Convention

- File: `docs/adr/ADR-NNNN-short-title.md`
- Number: Sequential, zero-padded to 4 digits
- Title: Kebab-case, descriptive

## Future State Planning

### Documentation Requirements

Each Hack23 project must maintain:

| Document | Purpose | Update Frequency |
|----------|---------|------------------|
| `ARCHITECTURE.md` | Current state architecture | Every major change |
| `FUTURE_ARCHITECTURE.md` | Target state architecture | Quarterly review |
| `SECURITY_ARCHITECTURE.md` | Security architecture | Every security change |
| `DATA_MODEL.md` | Entity relationship diagrams | Every schema change |
| `FLOWCHART.md` | Data processing workflows | When workflows change |
| `MINDMAP.md` | Component relationships | Annually |

### Migration Planning Template

```markdown
## Current State → Future State

### Phase 1: Foundation (Q1)
- [ ] Milestone 1: Description
- [ ] Milestone 2: Description

### Phase 2: Migration (Q2)
- [ ] Milestone 3: Description

### Phase 3: Optimization (Q3)
- [ ] Milestone 4: Description

### Rollback Plan
- Step 1: {rollback action}
- Step 2: {verification}
```

## Diagram Standards

### Tool Chain

- **Mermaid** — primary diagramming tool (renders in GitHub Markdown)
- **PlantUML** — alternative for complex UML diagrams
- **C4-PlantUML** — C4 model diagrams with PlantUML

### Diagram Checklist

- [ ] Title present and descriptive
- [ ] All elements labeled
- [ ] Relationships have descriptions and protocols
- [ ] Legend included for non-standard notation
- [ ] Consistent color scheme across diagrams
- [ ] Rendered preview verified in GitHub

## Cross-Repository Consistency

### Hack23 Organization Standards

All repositories under `github.com/Hack23/` must:

1. **Use consistent naming** — `ARCHITECTURE.md`, not `architecture.md`
2. **Include C4 Level 1** at minimum in `ARCHITECTURE.md`
3. **Maintain FUTURE_*.md** documents for forward planning
4. **Link related repos** — cross-reference dependent systems
5. **Version diagrams** — include date or version in diagram titles

## ISMS Alignment

| Control | Requirement |
|---------|-------------|
| ISO 27001 A.5.1 | Policies for information security (documented) |
| ISO 27001 A.8.25 | Secure development lifecycle documentation |
| NIST CSF ID.AM-2 | Software platform inventory |
| CIS Control 2 | Inventory and control of software assets |
