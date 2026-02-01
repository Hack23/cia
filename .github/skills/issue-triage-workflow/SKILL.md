---
name: issue-triage-workflow
description: Triage GitHub issues: labeling, prioritization, assignment, and resolution tracking
license: Apache-2.0
---

# Issue Triage Workflow Skill

## Purpose
Efficiently triage and manage GitHub issues for CIA project.

## When to Use
- ✅ New issues submitted
- ✅ Bug reports received
- ✅ Feature requests proposed
- ✅ Security vulnerabilities reported

## Triage Process

### 1. Initial Review (Within 24 Hours)
- ✅ Read issue description
- ✅ Verify it's not duplicate
- ✅ Check if sufficient information provided

### 2. Classification

#### Bug Reports
```markdown
Labels: bug, needs-triage

Checklist:
- [ ] Steps to reproduce provided
- [ ] Expected vs actual behavior clear
- [ ] Environment details included
- [ ] Screenshots/logs attached

Severity:
- critical: System down, data loss
- high: Major feature broken
- medium: Feature partially broken
- low: Minor issue, workaround exists
```

#### Feature Requests
```markdown
Labels: enhancement, needs-triage

Checklist:
- [ ] Use case described
- [ ] Acceptance criteria defined
- [ ] Similar features considered
- [ ] Implementation complexity estimated

Priority:
- high: Aligns with roadmap, high value
- medium: Useful but not urgent
- low: Nice to have
```

#### Security Issues
```markdown
Labels: security, URGENT

Process:
1. Immediately notify security team
2. Move to private security advisory
3. Do NOT discuss publicly
4. Follow SECURITY.md disclosure process
```

### 3. Labeling

**Type Labels:**
- `bug` - Something isn't working
- `enhancement` - New feature or request
- `documentation` - Documentation improvements
- `question` - Further information requested
- `security` - Security vulnerability

**Priority Labels:**
- `priority: critical` - Immediate action required
- `priority: high` - Should be addressed soon
- `priority: medium` - Important but not urgent
- `priority: low` - Nice to have

**Status Labels:**
- `status: needs-triage` - Awaiting initial review
- `status: accepted` - Confirmed, ready for work
- `status: in-progress` - Work started
- `status: blocked` - Waiting on external factor
- `status: wontfix` - Will not be addressed

**Area Labels:**
- `area: security` - Security-related
- `area: api` - API changes
- `area: ui` - User interface
- `area: database` - Database changes
- `area: ci/cd` - Build/deployment

### 4. Assignment
- Assign to appropriate team member
- If unclear, assign to tech lead
- Self-assign if actively working on it

### 5. Milestone
- Assign to current sprint/release if prioritized
- Backlog for future consideration

## Issue Templates

### Bug Report Template
```markdown
**Describe the bug**
A clear description of the bug.

**To Reproduce**
Steps to reproduce:
1. Go to '...'
2. Click on '...'
3. See error

**Expected behavior**
What you expected to happen.

**Screenshots**
If applicable, add screenshots.

**Environment**
- OS: [e.g., Ubuntu 22.04]
- Browser: [e.g., Chrome 120]
- Version: [e.g., 2.5.0]

**Additional context**
Any other context about the problem.
```

### Feature Request Template
```markdown
**Is your feature request related to a problem?**
Describe the problem.

**Describe the solution you'd like**
Clear description of what you want.

**Describe alternatives you've considered**
Other solutions you've thought about.

**Additional context**
Any other context or screenshots.
```

## Automation

### GitHub Actions
```yaml
name: Issue Triage

on:
  issues:
    types: [opened]

jobs:
  triage:
    runs-on: ubuntu-latest
    steps:
      - name: Add needs-triage label
        uses: actions/github-script@v7
        with:
          script: |
            github.rest.issues.addLabels({
              owner: context.repo.owner,
              repo: context.repo.repo,
              issue_number: context.issue.number,
              labels: ['status: needs-triage']
            })
```

## Closing Issues

### Resolution Messages
```markdown
Fixed in #123 (PR number)
Released in v2.5.0
Documentation updated in CHANGELOG.md
```

### Wontfix Messages
```markdown
Thank you for the suggestion. After discussion with the team, 
we've decided not to implement this because [reason]. 

We're closing this issue but appreciate your input.
```

## Metrics to Track
- Time to first response
- Time to resolution
- Issue backlog size
- Bug vs feature ratio
- Critical bug frequency

## References
- .github/ISSUE_TEMPLATE/
- GitHub Issues Guide: https://guides.github.com/features/issues/