---
name: contribution-guidelines
description: Follow CIA project contribution process: PR workflow, code review standards, commit conventions
license: Apache-2.0
---

# Contribution Guidelines Skill

## Purpose
Follow proper contribution process for CIA project including PR workflow and code review standards.

## When to Use
- ✅ Submitting pull requests
- ✅ Conducting code reviews
- ✅ Contributing to open source
- ✅ Reporting bugs or requesting features

## Pull Request Process

### 1. Fork and Clone
```bash
git clone https://github.com/YOUR-USERNAME/cia.git
cd cia
git remote add upstream https://github.com/Hack23/cia.git
```

### 2. Create Feature Branch
```bash
git checkout -b feature/politician-analytics
```

### 3. Make Changes
```bash
# Follow coding standards
# Write tests
# Update documentation
```

### 4. Commit with Conventional Commits
```bash
git commit -m "feat: add politician voting analytics dashboard"
git commit -m "fix: resolve SQL injection in search endpoint"
git commit -m "docs: update SECURITY_ARCHITECTURE.md"
```

### 5. Push and Create PR
```bash
git push origin feature/politician-analytics
# Create PR via GitHub UI
```

## Code Review Checklist

### For Contributors
- ✅ All tests pass locally
- ✅ Code follows project conventions
- ✅ Documentation updated
- ✅ Security considerations addressed
- ✅ No secrets committed

### For Reviewers
- ✅ Code is maintainable and readable
- ✅ Test coverage adequate
- ✅ Security vulnerabilities checked
- ✅ Performance implications considered
- ✅ Documentation complete

## Commit Message Format
```
type(scope): subject

body

footer
```

Types: feat, fix, docs, style, refactor, test, chore

## References
- CONTRIBUTING.md
- CODE_OF_CONDUCT.md
- PULL_REQUEST_TEMPLATE.md