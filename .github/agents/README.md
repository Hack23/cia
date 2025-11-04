# Custom Agent Profiles

This directory contains custom agent profiles for GitHub Copilot, designed to provide domain-specific expertise for the Citizen Intelligence Agency project.

## Overview

Each agent profile is a Markdown file with YAML frontmatter that defines specialized expertise. When working on tasks related to a specific domain, GitHub Copilot can leverage these profiles to provide more informed and contextual assistance.

## Available Agents

### 1. [Stack Specialist](stack-specialist.md)
**Expertise**: Java 25, Spring Framework, Vaadin, Hibernate/JPA, PostgreSQL, Maven, Testing

Focus on technical architecture, framework usage, and implementation details for the Java-based stack.

**Use for**: Architecture decisions, Spring configuration, JPA/Hibernate optimization, Vaadin UI development, database design, testing strategies, build system configuration, technical troubleshooting

### 2. [UI Enhancement Specialist](ui-enhancement-specialist.md)
**Expertise**: Vaadin Framework, Data Visualization, UI/UX Design, Responsive Design, Accessibility

Focus on creating exceptional user experiences for political data visualization and transparency.

**Use for**: UI/UX design improvements, Vaadin component development, data visualization (charts, dashboards), responsive design, accessibility (WCAG compliance), performance optimization for UI

### 3. [Business Development Specialist](business-development-specialist.md)
**Expertise**: Strategic Planning, Partnership Development, Revenue Models, Market Expansion

Focus on growing the platform's reach, sustainability, and impact.

**Use for**: Strategic planning, partnership development, revenue model design, market expansion strategies, competitive analysis, customer segmentation, go-to-market strategy

### 4. [Intelligence Operative](intelligence-operative.md)
**Expertise**: Political Science, OSINT, Intelligence Analysis, Behavioral Analysis, Swedish Politics

Focus on political intelligence, data analysis, and maintaining analytical rigor while respecting ethical boundaries.

**Use for**: Political data analysis, intelligence analysis methodologies, pattern recognition, predictive analytics, OSINT techniques, Swedish political system expertise, risk assessment

### 5. [Marketing Specialist](marketing-specialist.md)
**Expertise**: Digital Marketing, Content Strategy, Community Building, Brand Positioning

Focus on building awareness, engagement, and adoption while maintaining political neutrality.

**Use for**: Marketing strategy, content creation and SEO, social media strategy, public relations, community building, brand development, growth hacking, analytics

## Agent Profile Format

Each agent profile follows the GitHub Copilot custom agent format:

```markdown
---
name: agent-name
description: Brief description of the agent's expertise and focus
tools: ["tool1", "tool2", "tool3"]
---

Agent prompt content goes here...
```

### YAML Frontmatter Properties

- **name**: Unique identifier for the agent (lowercase with hyphens, e.g., `stack-specialist`)
- **description**: Brief explanation of what the agent does and its specific capabilities (maximum 200 characters)
- **tools**: Array of tool aliases the agent can use. Available tools:
  - `"view"` - Read file contents
  - `"edit"` - Modify file contents  
  - `"create"` - Create new files
  - `"bash"` - Execute shell commands
  - `"search_code"` - Search codebase
  - `"web"` - Web search (if enabled)
  - `"custom-agent"` - Invoke other custom agents
  - `"todo"` - Task management (if enabled)

### Agent Prompt

Below the YAML frontmatter, define the agent's behavior, expertise, and instructions. This should include:
- Core expertise areas
- Responsibilities
- Best practices
- Key principles
- Resources
- Example patterns or code where relevant

## Using These Profiles

### For Developers
When working on specific aspects of the project:
1. Reference the appropriate specialist profile for your task
2. Follow the best practices and patterns outlined in the profile
3. Consider the domain-specific considerations mentioned
4. Apply the recommended techniques and tools

### For GitHub Copilot
These profiles serve as context for AI-assisted development:
1. They provide domain-specific knowledge and context
2. They establish best practices and standards
3. They define the expertise boundaries for each specialty
4. They offer examples and patterns to follow

### For Contributors
1. Read the relevant specialist profile before contributing to that area
2. Understand the project's approach and standards
3. Follow the guidelines and best practices
4. Ask questions when the profile doesn't cover your specific case

## Maintaining These Profiles

### When to Update
- Technology stack changes (new frameworks, versions)
- Architectural decisions (new patterns, approaches)
- Business model evolution (new markets, strategies)
- Team learning (lessons learned, best practices refined)
- External changes (new regulations, market shifts)

### How to Update
1. Keep profiles aligned with project documentation (ARCHITECTURE.md, SWOT.md, etc.)
2. Reflect current best practices and standards
3. Add new examples and use cases as they emerge
4. Remove outdated information
5. Ensure consistency across profiles

### Who Can Update
- Project maintainers
- Domain experts (with review)
- Contributors (via pull requests with justification)

## Integration with Project Documentation

These agent profiles complement and reference the main project documentation:

- [Architecture](../../ARCHITECTURE.md) - System design and structure
- [SWOT Analysis](../../SWOT.md) - Strategic context
- [Tech Stack](../../techstack.md) - Technology inventory
- [Security Architecture](../../SECURITY_ARCHITECTURE.md) - Security design
- [Data Model](../../DATA_MODEL.md) - Entity relationships
- [End-of-Life Strategy](../../End-of-Life-Strategy.md) - Technology lifecycle
- [Financial Security Plan](../../FinancialSecurityPlan.md) - AWS and security costs

## Philosophy

These profiles embody the project's core values:

### Technical Excellence
- Follow best practices and industry standards
- Write maintainable, tested, secure code
- Consider performance and scalability
- Embrace modern development practices

### Democratic Mission
- Prioritize transparency and accessibility
- Maintain strict political neutrality
- Empower informed citizenship
- Serve the public interest

### Open Source Values
- Share knowledge and expertise
- Welcome contributors from all backgrounds
- Document decisions and rationale
- Build for the community

### Ethical Standards
- Respect privacy and data protection
- Use data responsibly and ethically
- Consider societal impact
- Maintain trust through transparency

## Contributing

To improve these profiles:

1. **Identify the need**: What's missing or outdated?
2. **Research**: Gather information, best practices, examples
3. **Draft changes**: Write clear, actionable content
4. **Submit PR**: Include rationale for changes
5. **Discuss**: Engage with reviewers on feedback
6. **Iterate**: Refine based on discussion

## Questions?

If you have questions about:
- **Using these profiles**: Open a discussion in GitHub Discussions
- **Updating profiles**: Comment on relevant issues or PRs
- **Creating new profiles**: Propose in an issue with justification

## Examples from GitHub Documentation

### Testing Specialist Example
```markdown
---
name: test-specialist
description: Focuses on test coverage, quality, and testing best practices without modifying production code
tools: ["view", "edit", "create", "bash", "search_code"]
---

You are a testing specialist focused on improving code quality through comprehensive testing. Your responsibilities:

- Analyze existing tests and identify coverage gaps
- Write unit tests, integration tests, and end-to-end tests following best practices
- Review test quality and suggest improvements for maintainability
- Ensure tests are isolated, deterministic, and well-documented
- Focus only on test files and avoid modifying production code unless specifically requested

Always include clear test descriptions and use appropriate testing patterns for the language and framework.
```

### Implementation Planner Example
```markdown
---
name: implementation-planner
description: Creates detailed implementation plans and technical specifications in markdown format
tools: ["view", "search_code", "edit", "create"]
---

You are a technical planning specialist focused on creating comprehensive implementation plans. Your responsibilities:

- Analyze requirements and break them down into actionable tasks
- Create detailed technical specifications and architecture documentation
- Generate implementation plans with clear steps, dependencies, and timelines
- Document API designs, data models, and system interactions
- Create markdown files with structured plans that development teams can follow

Always structure your plans with clear headings, task breakdowns, and acceptance criteria.
```

---

**Remember**: These profiles are living documents that evolve with the project. They represent our collective expertise and commitment to building an excellent political transparency platform.
