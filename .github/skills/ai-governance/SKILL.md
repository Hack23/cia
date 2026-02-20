---
name: ai-governance
description: AI governance, EU AI Act compliance, OWASP LLM security, responsible AI practices for GitHub Copilot agents
license: Apache-2.0
---

# AI Governance Skill

## Purpose

This skill provides governance guidelines for AI usage in the CIA platform, including GitHub Copilot agent security, EU AI Act compliance, and responsible AI practices. It ensures AI-assisted development follows Hack23 ISMS policies and regulatory requirements.

## When to Use This Skill

Apply this skill when:
- ✅ Configuring or updating GitHub Copilot agent workflows
- ✅ Integrating AI/ML models for political data analysis
- ✅ Reviewing AI-generated code before merge
- ✅ Assessing AI risk classification under EU AI Act
- ✅ Implementing prompt engineering for data analysis
- ✅ Auditing AI agent outputs for bias or accuracy

Do NOT use for:
- ❌ Standard code reviews without AI involvement
- ❌ Manual data analysis without AI components
- ❌ Infrastructure changes unrelated to AI services

## EU AI Act Classification

### Risk Assessment for CIA Platform

```
CIA Platform AI Usage Assessment
│
├─→ Political Data Analysis (NLP, trend detection)
│   ├─ Risk Level: LIMITED RISK (Article 52)
│   ├─ Requirement: Transparency obligations
│   └─ Action: Disclose AI-generated analysis to users
│
├─→ GitHub Copilot Code Generation
│   ├─ Risk Level: MINIMAL RISK
│   ├─ Requirement: Voluntary codes of conduct
│   └─ Action: Code review before merge, security scanning
│
├─→ Political Risk Scoring
│   ├─ Risk Level: HIGH RISK (Annex III, Category 8)
│   ├─ Requirement: Conformity assessment, human oversight
│   └─ Action: Human review of all risk scores, audit trail
│
└─→ Voter Behavior Prediction
    ├─ Risk Level: HIGH RISK
    ├─ Requirement: Transparency, fairness, accountability
    └─ Action: Bias testing, explainability, regular audits
```

### Compliance Checklist

- ✅ Document AI system purpose and intended use
- ✅ Classify AI risk level per EU AI Act categories
- ✅ Implement human oversight for high-risk AI outputs
- ✅ Maintain audit trail of AI-generated decisions
- ✅ Conduct bias and fairness assessments
- ✅ Provide transparency notices for AI-generated content
- ✅ Implement data governance for training datasets

## OWASP LLM Top 10 for CIA Platform

### LLM01: Prompt Injection

**Risk:** Malicious input manipulating Copilot agent behavior.

**Mitigation:**
```yaml
# .github/copilot-instructions.md safeguards
- Validate all agent outputs before committing
- Never allow agents to modify security configurations
- Restrict agent file access to source code only
- Review agent-generated code with CodeQL scanning
```

### LLM02: Insecure Output Handling

**Risk:** AI-generated code containing vulnerabilities.

**Mitigation:**
- Run CodeQL on all AI-generated code changes
- Apply OWASP secure code review checklist
- Validate AI outputs against coding standards
- Never trust AI-generated SQL or security logic without review

### LLM06: Sensitive Information Disclosure

**Risk:** AI agents leaking secrets or sensitive political data.

**Mitigation:**
```java
// Never pass sensitive data to AI prompts
// ✅ SECURE: Generic analysis request
String prompt = "Analyze voting patterns for committee " + committeeId;

// ❌ INSECURE: Including PII in prompts
String prompt = "Analyze voting for " + politicianName + " SSN: " + ssn;
```

### LLM09: Overreliance

**Risk:** Blindly trusting AI-generated political analysis.

**Mitigation:**
- All AI analysis must include confidence scores
- Human analyst review required for published insights
- Cross-validate AI outputs with official data sources
- Label AI-generated content clearly in the UI

## GitHub Copilot Agent Security

### Agent Configuration Best Practices

```yaml
# Secure agent workflow permissions
permissions:
  contents: read      # Read-only by default
  pull-requests: write # Only for PR creation
  issues: write       # Only for issue management
  actions: read       # Read workflow status

# Never grant:
# - admin permissions
# - security_events write
# - secrets access
```

### Agent Output Validation

```
Agent Output Validation Pipeline
│
├─ Step 1: Syntax validation (compile check)
├─ Step 2: Security scan (CodeQL, OWASP)
├─ Step 3: Test execution (unit + integration)
├─ Step 4: Code review (human or Copilot review)
└─ Step 5: Merge approval (maintainer sign-off)
```

## Responsible AI Practices

### Bias Prevention in Political Analysis

- Test analysis algorithms across all 8 Swedish parties equally
- Validate data representation for minority viewpoints
- Audit sentiment analysis for political neutrality
- Document model limitations and known biases

### Transparency Requirements

- Label all AI-generated content in the CIA platform UI
- Provide methodology documentation for AI analysis
- Enable users to access raw data behind AI insights
- Maintain changelog of AI model updates

## ISMS Alignment

| Control | Requirement | Implementation |
|---------|------------|----------------|
| ISO 27001 A.5.1 | Information security policies | AI governance policy |
| ISO 27001 A.8.1 | Asset management | AI model inventory |
| NIST CSF GV.OC | Organizational context | AI risk assessment |
| CIS Control 16 | Application security | AI code review gates |
| GDPR Art. 22 | Automated decision-making | Human oversight for scoring |

## References

- [EU AI Act](https://artificialintelligenceact.eu/)
- [OWASP LLM Top 10](https://owasp.org/www-project-top-10-for-large-language-model-applications/)
- [Hack23 ISMS Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)
- [GitHub Copilot Trust Center](https://resources.github.com/copilot-trust-center/)
