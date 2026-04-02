---
name: github-actions-workflows
description: Create secure CI/CD workflows with GitHub Actions for Java 26/Maven/PostgreSQL builds, security scans, and deployments
license: Apache-2.0
---

# GitHub Actions Workflows Skill

## Purpose

Create and maintain secure, efficient CI/CD pipelines using GitHub Actions for the CIA platform. Covers build, test, security scanning, deployment, and agentic workflow integration.

## When to Use
- ✅ Setting up or modifying CI/CD pipelines
- ✅ Automating security scans (CodeQL, OWASP, SonarCloud)
- ✅ Implementing deployment pipelines
- ✅ Scheduling periodic tasks
- ✅ Integrating with GitHub Agentic Workflows (`gh-aw`)

## Current CIA Build Environment

| Component | Version | Notes |
|-----------|---------|-------|
| **Java JDK** | 26-ea (Temurin) | Source level 21 |
| **Maven** | 3.9.14 | Multi-module reactor build |
| **PostgreSQL** | 18 | Extensions: pgaudit, pgcrypto, pg_stat_statements |
| **Node.js** | 24 | MCP servers, Playwright |
| **Runner** | ubuntu-latest | GitHub Actions hosted |

## CI/CD Pipeline Template

```yaml
name: CI/CD Pipeline

on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]

permissions:
  contents: read
  security-events: write
  actions: read

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@11bd71901bbe5b1630ceea73d27597364c9af683 # v4.2.2

      - name: Set up JDK 26
        uses: actions/setup-java@c5195efecf7bdfc987ee8bae7a71cb8b11521c00 # v4.7.1
        with:
          java-version: '26-ea'
          distribution: 'temurin'
          cache: 'maven'

      - name: Build with Maven
        run: mvn clean install -DskipTests

      - name: Run Tests
        run: mvn test -Dtest='!*ITest*,!Xml*TypeAdapterTest'

      - name: Upload Coverage
        uses: codecov/codecov-action@18283e04ce6e62d37312384ff67231eb8fd56d24 # v5.4.3
        with:
          files: '**/target/site/jacoco/jacoco.xml'

  security:
    runs-on: ubuntu-latest
    needs: build
    steps:
      - uses: actions/checkout@11bd71901bbe5b1630ceea73d27597364c9af683 # v4.2.2

      - name: Initialize CodeQL
        uses: github/codeql-action/init@ff0a06e83cb2de871e5a09832bc6a81e7276941f # v3.28.18
        with:
          languages: java

      - name: CodeQL Analysis
        uses: github/codeql-action/analyze@ff0a06e83cb2de871e5a09832bc6a81e7276941f # v3.28.18

      - name: OWASP Dependency Check
        run: mvn org.owasp:dependency-check-maven:check

  deploy:
    runs-on: ubuntu-latest
    needs: [build, security]
    if: github.ref == 'refs/heads/main'
    steps:
      - name: Deploy to AWS
        uses: aws-actions/configure-aws-credentials@ececac1a45f3b08a01d2dd070d28d111c5fe6722 # v4.1.0
        with:
          role-to-assume: ${{ secrets.AWS_ROLE_ARN }}
          aws-region: eu-north-1
```

## Security Best Practices

### Action Pinning
Always pin actions to full SHA commit hashes, never tags:
```yaml
# ✅ Correct - pinned to SHA
- uses: actions/checkout@11bd71901bbe5b1630ceea73d27597364c9af683 # v4.2.2

# ❌ Wrong - mutable tag
- uses: actions/checkout@v4
```

### Permissions
Always declare minimal permissions at workflow and job level:
```yaml
permissions:
  contents: read      # Default for most jobs
  security-events: write  # Only for security scan uploads
  issues: write       # Only for issue management jobs
```

### Secrets Management
```yaml
# ✅ Use GitHub secrets
env:
  SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}

# ✅ Use OIDC for cloud access (no long-lived keys)
- uses: aws-actions/configure-aws-credentials@v4
  with:
    role-to-assume: ${{ secrets.AWS_ROLE_ARN }}

# ❌ Never hardcode credentials
```

### Supply Chain Security
```yaml
# SLSA 3 build provenance
- uses: slsa-framework/slsa-github-generator/.github/workflows/builder_maven.yml@v2
  with:
    rekor-log-public: true
```

## CIA-Specific Workflows

### Existing Workflows
| Workflow | Purpose | Trigger |
|----------|---------|---------|
| `verify-and-release.yml` | Main CI/CD: build, test, release | Push/PR to main |
| `codeql-analysis.yml` | Security vulnerability scanning | Push/PR + scheduled |
| `dependency-review.yml` | Dependency security checks | PR only |
| `scorecards.yml` | OpenSSF Scorecard assessment | Scheduled |
| `release.yml` | Build artifacts + SLSA attestations | Tag push |
| `copilot-setup-steps.yml` | Copilot agent build environment | Copilot sessions |

### PostgreSQL Setup Pattern
```yaml
services:
  postgres:
    image: postgres:18
    env:
      POSTGRES_USER: eris
      POSTGRES_PASSWORD: ${{ secrets.DB_PASSWORD }}
      POSTGRES_DB: cia_dev
    options: >-
      --health-cmd pg_isready
      --health-interval 10s
      --health-timeout 5s
      --health-retries 5
    ports:
      - 5432:5432
```

### Maven Build Optimization
```yaml
# Multi-level caching for resilience
- name: Cache Maven repository
  uses: actions/cache@v4
  with:
    path: ~/.m2/repository
    key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
    restore-keys: |
      ${{ runner.os }}-maven-

# Parallel builds for multi-module projects
- name: Build
  run: mvn -T 1C clean install -DskipTests
```

## Integration with Agentic Workflows

GitHub Agentic Workflows (`gh-aw`) complement traditional Actions:

| Aspect | Traditional Actions | Agentic Workflows |
|--------|-------------------|-------------------|
| **Logic** | Deterministic YAML | Natural language AI |
| **Decisions** | Pre-programmed conditionals | Context-aware reasoning |
| **Format** | `.yml` files | `.md` files → compiled to `.lock.yml` |
| **Security** | Manual permission management | Built-in 5-layer security |
| **Best for** | Build, test, deploy | Triage, review, docs, analysis |

Use both together: traditional Actions for deterministic build/test/deploy, agentic workflows for intelligent automation.

## ISMS Control Mapping

| Control | Implementation |
|---------|---------------|
| **ISO 27001 A.8.8** | Change management via PR-gated workflow changes |
| **ISO 27001 A.8.15** | Audit logging via Actions run logs |
| **NIST CSF PR.IP-1** | Baseline configuration via pinned action versions |
| **CIS Control 16** | Application security via CodeQL + OWASP in pipeline |

## References
- GitHub Actions: https://docs.github.com/en/actions
- Action Security Hardening: https://docs.github.com/en/actions/security-for-github-actions
- SLSA Framework: https://slsa.dev/
- OpenSSF Scorecard: https://securityscorecards.dev/
- CIA Workflows: `.github/workflows/`
