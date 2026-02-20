---
name: ci-cd-security
description: CI/CD pipeline security, supply chain security, SLSA compliance, artifact signing, dependency scanning
license: Apache-2.0
---

# CI/CD Security Skill

## Purpose

Secure the CIA platform's CI/CD pipelines against supply chain attacks, ensure artifact integrity, and maintain compliance with SLSA (Supply-chain Levels for Software Artifacts) requirements. Covers GitHub Actions hardening, dependency scanning, and build provenance.

## When to Use

- ✅ Creating or modifying GitHub Actions workflows
- ✅ Adding new dependencies to the project
- ✅ Configuring build artifact signing or attestation
- ✅ Reviewing pipeline security posture
- ✅ Implementing dependency update policies

Do NOT use for:
- ❌ Application-level security (use secure-code-review skill)
- ❌ Infrastructure security (use threat-modeling skill)

## GitHub Actions Security Hardening

### Workflow Permissions

```yaml
# Always use minimum required permissions
permissions:
  contents: read
  actions: read
  security-events: write  # Only if needed for CodeQL

# Pin actions to full SHA, not tags
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@b4ffde65f46336ab88eb53be808477a3936bae11 # v4.1.1
      - uses: actions/setup-java@99b8673ff64fbf99d8d325f52d9a5bdedb8483e9 # v4.2.1
        with:
          distribution: 'temurin'
          java-version: '25'
```

### Key Security Rules

1. **Pin all action versions to commit SHAs** — tags can be moved
2. **Use `permissions` at job level** — minimize token scope
3. **Never echo secrets** — use `::add-mask::` for dynamic values
4. **Audit third-party actions** — review source before adopting
5. **Use `CODEOWNERS`** for workflow files — require review for changes

### Secret Management

```yaml
# Good: Use GitHub Secrets
env:
  SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}

# Bad: Never hardcode or log secrets
# run: echo ${{ secrets.TOKEN }}  # NEVER DO THIS
```

## Supply Chain Security

### SLSA Compliance Levels

| Level | Requirement | CIA Status |
|-------|-------------|------------|
| SLSA 1 | Build process documented | ✅ GitHub Actions |
| SLSA 2 | Hosted build, signed provenance | ✅ GitHub-hosted runners |
| SLSA 3 | Hardened build, non-falsifiable provenance | 🔄 In progress |

### Dependency Scanning Stack

```yaml
# Multi-layer dependency scanning
- name: OWASP Dependency Check
  run: mvn dependency-check:check -DfailBuildOnCVSS=7

- name: GitHub Dependency Review
  uses: actions/dependency-review-action@v4
  with:
    fail-on-severity: high

- name: Dependabot Auto-merge (patch only)
  # Configured in .github/dependabot.yml
```

### SBOM Generation

```yaml
- name: Generate SBOM
  run: |
    mvn org.cyclonedx:cyclonedx-maven-plugin:makeBom
    # Output: target/bom.json (CycloneDX format)

- name: Attest SBOM
  uses: actions/attest-sbom@v1
  with:
    subject-path: 'target/*.jar'
    sbom-path: 'target/bom.json'
```

## Build Integrity

### Reproducible Builds

```xml
<!-- Maven settings for reproducible builds -->
<properties>
    <project.build.outputTimestamp>2024-01-01T00:00:00Z</project.build.outputTimestamp>
</properties>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <addDefaultImplementationEntries>true</addDefaultImplementationEntries>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

### Artifact Attestation

```yaml
- name: Generate artifact attestation
  uses: actions/attest-build-provenance@v1
  with:
    subject-path: 'target/citizen-intelligence-agency-*.war'
```

## Dependency Management Rules

### Adding New Dependencies

1. **Check license compatibility** — must be Apache 2.0 compatible
2. **Run security scan** — `mvn dependency-check:check`
3. **Check GitHub Advisory Database** — use `gh-advisory-database` tool
4. **Verify publisher** — check Maven Central publisher identity
5. **Pin version** — use exact versions, not ranges
6. **Document justification** — explain why the dependency is needed in PR

### Dependency Update Policy

| Update Type | Auto-merge | Review Required |
|-------------|------------|-----------------|
| Patch (x.x.PATCH) | ✅ If tests pass | No |
| Minor (x.MINOR.x) | ❌ | Yes |
| Major (MAJOR.x.x) | ❌ | Yes + security review |

## Runner Security

```yaml
jobs:
  build:
    runs-on: ubuntu-latest  # Use GitHub-hosted runners
    # Never use self-hosted runners for public repos without hardening
    steps:
      - name: Harden Runner
        uses: step-security/harden-runner@v2
        with:
          egress-policy: audit  # Monitor outbound connections
```

## Monitoring and Alerts

- **Enable Dependabot alerts** for all ecosystems
- **Enable secret scanning** with push protection
- **Enable code scanning** with CodeQL
- **Review Security tab** weekly for new alerts
- **Monitor OSSF Scorecard** results for security posture

## ISMS Alignment

| Control | Requirement |
|---------|-------------|
| ISO 27001 A.8.25 | Secure development lifecycle |
| ISO 27001 A.8.31 | Separation of dev/test/prod |
| NIST CSF PR.IP-1 | Configuration management |
| CIS Control 16 | Application software security |
| SLSA L2 | Signed provenance, hosted build |
