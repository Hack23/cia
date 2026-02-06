---
name: product-management-patterns
description: Product backlog management, user stories, acceptance criteria, sprint planning, and stakeholder management for agile development
license: Apache-2.0
---

# Product Management Patterns

## Purpose

This skill provides strategic guidance for product management activities in the CIA platform, including backlog prioritization, user story creation, acceptance criteria definition, and stakeholder communication. It ensures product decisions align with user needs, business goals, and ISMS compliance requirements.

## When to Use

### ✅ Use this skill when:
- Creating or refining product backlog items
- Writing user stories with acceptance criteria
- Prioritizing features and bug fixes
- Planning sprints or releases
- Defining MVP scope and feature sets
- Gathering and analyzing user feedback
- Making build vs. buy decisions
- Creating product roadmaps

### ❌ Don't use this skill for:
- Technical implementation details (use stack-specialist skills)
- Security architecture (use threat-modeling, secure-code-review)
- UI/UX design specifics (use vaadin-component-design)
- Business strategy (use business-model-canvas)

## Patterns & Examples

### User Story Template

**Format**: As a [user type], I want [goal] so that [benefit]

```markdown
## User Story
As a **political researcher**, I want to **filter politicians by risk score** so that I can **quickly identify high-risk entities for investigation**.

## Acceptance Criteria
✅ Risk score filter with range slider (0-100)
✅ Filter applies to politician list in real-time
✅ Filtered results show risk score badge
✅ Filter state persists across page refreshes
✅ Filter respects existing search/filter combinations
✅ Loading state shown during filter application

## Technical Notes
- Use Vaadin Slider component
- Backend: RiskScoreService.filterByRange()
- Cache filtered results for 5 minutes
- Log filter usage for analytics

## ISMS Compliance
- Ensure GDPR compliance for filtered data display
- Audit log all risk score queries
- Apply RBAC to sensitive risk data

## Definition of Done
- [ ] Unit tests with 80%+ coverage
- [ ] Integration tests pass
- [ ] Code review approved
- [ ] Security review passed
- [ ] Documentation updated
- [ ] Accessibility WCAG 2.1 AA validated
```

### Backlog Prioritization Framework

**MoSCoW Method**:

1. **Must Have** - Core functionality, regulatory requirements, security fixes
   - GDPR compliance features
   - Critical security vulnerabilities
   - Data integrity issues
   - System stability fixes

2. **Should Have** - Important but not critical, improves UX
   - Performance optimizations
   - Enhanced search capabilities
   - Advanced filtering options
   - Improved visualizations

3. **Could Have** - Nice to have, low impact if dropped
   - Additional chart types
   - Export formats
   - Cosmetic improvements
   - Convenience features

4. **Won't Have** - Out of scope, future consideration
   - Features requiring major architecture changes
   - Low-value, high-effort items
   - Third-party integrations not aligned with mission

## ISMS Compliance Mapping

### ISO 27001:2022 Annex A Controls

**A.8.8 - Management of technical vulnerabilities**
- Prioritize security vulnerabilities in backlog
- Track CVE fixes in sprint planning
- Define SLA for critical security patches

**A.5.1 - Policies for information security**
- Product decisions align with security policies
- New features undergo security review
- ISMS compliance is acceptance criterion

### NIST Cybersecurity Framework 2.0

**Govern (GV)**
- GV.PO-01: Product outcomes support organizational objectives
- GV.RR-01: Risk tolerance levels inform prioritization

**Identify (ID)**
- ID.RA-01: Risk assessment findings drive feature prioritization

### CIS Controls v8

**Control 4: Secure Configuration**
- New features configured securely by default

**Control 18: Penetration Testing**
- Security testing included in Definition of Done

## Hack23 ISMS Policy References

- [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) - Security Requirements in SDLC
- [Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) - Risk-based decision making

## References

### Internal CIA Documentation
- [ARCHITECTURE.md](/ARCHITECTURE.md) - System architecture
- [SECURITY_ARCHITECTURE.md](/SECURITY_ARCHITECTURE.md) - Security requirements
- [CONTRIBUTING.md](/CONTRIBUTING.md) - Contribution workflow

### Product Management Resources
- User Story Mapping - Jeff Patton
- Agile Estimating and Planning - Mike Cohn

## Remember

- **Security is a feature**: Include ISMS compliance in every user story
- **User value first**: Every backlog item must deliver measurable user value
- **Small batches**: Break large features into smaller, releasable increments
- **Data-driven decisions**: Use metrics to validate product assumptions
