---
name: hack23-information-security-policy
description: Hack23 Information Security Policy integration for SDLC — developer-facing mapping of ISP requirements to daily engineering activities, tooling, and evidence
license: Apache-2.0
---

# Hack23 Information Security Policy Integration Skill

## Purpose

Translate the [Hack23 Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) — the **apex** document of the Hack23 ISMS — into concrete, enforceable engineering behaviour for the CIA platform. This skill connects the high-level policy (CIA triad objectives, risk appetite, roles, legal duties) to the code, workflows, reviews, and evidence that actually implement it.

Where `hack23-isms-compliance` describes the **framework** and `information-security-strategy` describes the **strategy**, this skill tells an engineer or AI agent *"given the ISP and its supporting policies, what must I do in this commit, PR, or design?"*

## When to Use

Apply this skill when:
- ✅ Starting or reviewing any code / infrastructure / documentation change that touches data, authentication, authorization, crypto, logging, or third-party integrations
- ✅ Creating or updating a GitHub issue or PR that is security-relevant
- ✅ Onboarding a new contributor, AI agent, or repository to Hack23 standards
- ✅ Responding to a CodeQL / SonarCloud / OWASP / Dependabot finding
- ✅ Classifying data, designing an API, or integrating an external source
- ✅ Preparing release notes, SBOM, SECURITY.md, or audit evidence
- ✅ Assessing whether an AI coding agent action is policy-compliant

Do NOT use for:
- ❌ Tactical incident response (use `incident-response` skill)
- ❌ Deep cryptographic implementation (use `crypto-best-practices` / `cryptography-policy`)
- ❌ Framework-level compliance mapping only (use `hack23-isms-compliance`)

## Information Security Policy — Core Tenets (Engineering View)

The ISP is binding on all contributors, including AI agents. Its five engineering-relevant tenets are:

| # | ISP Tenet | What it means in code/PRs | Primary Evidence |
|---|-----------|---------------------------|------------------|
| 1 | **Confidentiality, Integrity, Availability (CIA triad)** | Every change must preserve or strengthen C/I/A for affected assets; no change may silently lower a classification control. | THREAT_MODEL.md, SECURITY_ARCHITECTURE.md, classification labels |
| 2 | **Risk-based, defence-in-depth** | Controls at code, platform, CI, infra, and monitoring layers; no single point of failure. | SECURITY_ARCHITECTURE.md, Risk Register, ZAP/CodeQL scans |
| 3 | **Least privilege & segregation of duties** | Minimum IAM, minimum token scopes, CODEOWNERS review, `permissions:` block on every workflow. | `.github/workflows/*`, GitHub branch protection, IAM policies |
| 4 | **Transparency & accountability** | Signed commits, public security docs, SBOM, OpenSSF/CII badges, auditable logs. | OpenSSF Scorecard, CII badge, SECURITY.md, SBOM |
| 5 | **Legal & regulatory alignment** | GDPR, NIS2, EU CRA, ISO 27001:2022, NIST CSF 2.0, CIS v8. | `CRA-ASSESSMENT.md`, `ISMS_COMPLIANCE_MAPPING.md`, DPIA |

## Supporting Policies and When Each Applies

The ISP is implemented through these supporting policies. An AI agent or developer MUST consult the ones that apply to the task at hand.

| Policy | Applies When | Key Engineering Checks |
|--------|--------------|------------------------|
| [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) | Any code or build change | SAST+DAST+SCA pass, banned patterns, signed commits |
| [Open Source Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Open_Source_Policy.md) | Public repos, OSS dep changes, release | OpenSSF ≥7.0, CII Passing, SLSA 3, FOSSA pass, SBOM attached |
| [Secrets Management Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secrets_Management_Policy.md) | Any credential, token, key, env var | No secrets in code, GitHub Secrets/SSM, rotation, scanning enabled |
| [Cryptography Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Cryptography_Policy.md) | TLS, hashing, encryption, signing | TLS 1.3, AES-256-GCM, bcrypt/argon2, RSA-4096/EC P-384, KMS-managed keys |
| [Access Control Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Access_Control_Policy.md) | AuthN/AuthZ, Spring Security, IAM | Least privilege, MFA, role separation, deny-by-default |
| [Data Classification / CLASSIFICATION](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) | New entity, API, log, export | Correct C/I/A label, handling controls per level |
| [Data Protection Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Data_Protection_Policy.md) | Personal data, retention, export | GDPR lawful basis, retention, DSAR support |
| [Privacy Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Privacy_Policy.md) | Any UI, log, analytics touching users | Minimal data, consent, cookies, opt-out paths |
| [Vulnerability Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md) | Any finding from scanners | SLA: Crit 24h/7d, High 7d, Med 30d, Low 90d |
| [Change Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Change_Management.md) | Releases, schema migrations, infra | CAB review, rollback plan, migration changeset |
| [Backup & Recovery](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Backup_Recovery_Policy.md) | DB or state change, DR tests | RPO/RTO respected, restore tested |
| [Incident Response Plan](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Incident_Response_Plan.md) | Suspected breach, outage, data loss | Ticket, classify, contain, notify ≤72h GDPR |
| [Threat Modeling](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Threat_Modeling.md) | New feature, API, trust boundary | STRIDE, attack tree, mitigation mapping |
| [Third Party Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Third_Party_Management.md) | New dependency, API, vendor, SaaS | License OK, CVE clean, data-sharing justified |
| [Segregation of Duties](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Segregation_of_Duties_Policy.md) | Workflow, deployment, review config | Author ≠ approver, CODEOWNERS enforced |
| [AI Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/AI_Policy.md) | Any AI / Copilot / agent action | EU AI Act risk-tier, OWASP LLM, human-in-the-loop |
| [Information Security Strategy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Strategy.md) | Roadmap, architecture decisions | Aligned with CEO-approved risk appetite |

## SDLC × ISP Responsibility Matrix

Each phase has mandatory ISP-derived checks. AI agents must confirm all items for their phase before proceeding.

```
┌────────────────┬──────────────────────────────────────────────────────────┐
│ SDLC Phase     │ ISP-Derived Gate                                         │
├────────────────┼──────────────────────────────────────────────────────────┤
│ Plan           │ Classification set; threat model updated; DPIA if needed │
│ Design         │ Least privilege; trust boundaries; crypto choices per    │
│                │ Cryptography Policy; third-party review                  │
│ Implement      │ Banned patterns avoided; input validation; output        │
│                │ encoding; parameterized queries; no secrets              │
│ Review         │ CODEOWNERS; SoD (author ≠ approver); PR template met    │
│ Build          │ Pinned actions; reproducible; signed; SBOM generated     │
│ Test           │ ≥80% line / ≥70% branch; CodeQL + SCA pass; DAST         │
│                │ baseline; negative test cases                            │
│ Release        │ Signed artifacts; SLSA provenance; release notes;        │
│                │ security advisories closed                               │
│ Operate        │ Logging (no PII); alerting; rotation; DR test            │
│ Respond        │ Incident plan triggers; 72h GDPR notification path       │
│ Retire         │ Secure data deletion; key destruction; dep removal       │
└────────────────┴──────────────────────────────────────────────────────────┘
```

## Non-Negotiable Rules (from ISP §Acceptable Use / §Secure Engineering)

🔴 **Never**:
- Commit secrets, tokens, keys, or personal data — use GitHub Secrets / AWS SSM
- Disable a security control (CSRF, CSP, TLS, CodeQL) without a risk-accepted PR + CEO sign-off
- Introduce a dependency without vulnerability + license check
- Bypass CODEOWNERS, branch protection, or the signed-commit requirement
- Log request bodies, tokens, passwords, or classified data
- Ship a release with open Critical or High CVEs
- Use an unpinned GitHub Action in a workflow that has write permissions
- Process personal data without a lawful basis documented in the DPIA

🟢 **Always**:
- Classify new data (`Public / Internal / Confidential / Restricted`)
- Default to deny in authorization checks
- Parameterize all database queries
- Encode output at the rendering boundary (Vaadin `ContentMode.TEXT`)
- Use `@Transactional` with explicit boundaries; `readOnly = true` where applicable
- Add unit tests for the negative / unauthorized path
- Sign commits (GPG or SSH) and releases (Sigstore/cosign)
- Record security-relevant decisions in SECURITY_ARCHITECTURE.md / THREAT_MODEL.md

## AI Agent & Copilot Guardrails (ISP + AI Policy)

These rules bind every AI agent (task-agent, stack-specialist, etc.) operating in this repo:

1. **Read before act** — load README, copilot-instructions, and the ISP before making changes
2. **Classification awareness** — never escalate Public → Confidential exposure inadvertently
3. **Minimal diff** — surgical, auditable changes; no unrelated refactors
4. **Evidence first** — every security claim cites scanner output, ISMS control, or test
5. **Human-in-the-loop** — Critical/High security changes require human approval before merge
6. **Transparent reasoning** — PR descriptions link to ISP / supporting policy when relevant
7. **No secret handling** — agents never print, commit, or send secrets; only reference names

## PR Policy Compliance Checklist

Paste into PR description for any security-relevant change:

```
### ISP & Supporting Policy Compliance
- [ ] Change scope and classification documented
- [ ] Threat model impact assessed (STRIDE) or confirmed N/A
- [ ] Secure Development Policy checks pass (CodeQL, SonarCloud, OWASP, SpotBugs)
- [ ] Secrets Management Policy respected (no secrets, rotation considered)
- [ ] Cryptography Policy respected (approved algorithms, key storage)
- [ ] Access Control Policy respected (least privilege, deny-by-default)
- [ ] Data Protection / Privacy Policy respected (GDPR basis, retention)
- [ ] Third Party Management respected (license, CVE, attribution)
- [ ] Open Source Policy respected (SBOM, signed release, badges)
- [ ] Logging reviewed (no PII, no secrets, useful for IR)
- [ ] Negative-path tests added
- [ ] SECURITY_ARCHITECTURE.md / THREAT_MODEL.md updated if needed
```

## Evidence & Audit Trail

Every security claim is backed by machine-verifiable evidence:

| Claim | Evidence |
|-------|----------|
| "Least privilege in CI" | `permissions:` block in workflow YAML |
| "No critical CVEs" | OWASP Dependency Check report, Dependabot status |
| "Signed releases" | Sigstore attestation, GitHub Release signatures |
| "Reproducible build" | SLSA provenance attestation |
| "Coverage ≥80%" | JaCoCo report in SonarCloud |
| "No known security hotspots" | SonarCloud Security Hotspot review |
| "License compliant" | FOSSA status badge, LICENSES/ directory |
| "SBOM complete" | CycloneDX `target/bom.json` attached to release |

## ISMS Framework Mapping

| ISP Tenet | ISO 27001:2022 | NIST CSF 2.0 | CIS v8 |
|-----------|----------------|--------------|--------|
| CIA Triad | A.5.1, A.8.2 | GV.RM, PR.DS | CIS 3 |
| Defence-in-depth | A.8.16, A.8.26 | PR.IP, DE.CM | CIS 4, 13 |
| Least privilege | A.5.15, A.8.2, A.8.3 | PR.AC-4 | CIS 6 |
| Transparency | A.5.31, A.5.37 | GV.OC, ID.GV | CIS 17 |
| Legal alignment | A.5.31–A.5.36 | GV.OC-03 | CIS 17.1 |

## References

- [Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) — apex policy
- [All Hack23 ISMS Policies](https://github.com/Hack23/ISMS-PUBLIC)
- [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)
- [Open Source Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Open_Source_Policy.md)
- [Information Security Strategy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Strategy.md)
- [AI Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/AI_Policy.md)
- Related skills: `hack23-isms-compliance`, `information-security-strategy`, `secure-development-policy`, `open-source-policy`, `compliance-frameworks`
