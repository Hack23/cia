---
name: product-quality-analysis
description: Product quality metrics, SonarCloud analysis, technical debt management, code quality gates
license: Apache-2.0
---

# Product Quality Analysis Skill

## Purpose

This skill provides guidance for measuring, monitoring, and improving product quality in the CIA platform using SonarCloud analysis, code quality gates, technical debt management, and quality metrics. It ensures the political intelligence platform maintains high reliability and maintainability.

## When to Use This Skill

Apply this skill when:
- ✅ Reviewing SonarCloud analysis results
- ✅ Assessing technical debt before or after changes
- ✅ Configuring quality gates for CI/CD pipelines
- ✅ Prioritizing refactoring efforts
- ✅ Measuring code quality trends over time
- ✅ Evaluating pull request quality impact
- ✅ Planning quality improvement sprints

Do NOT use for:
- ❌ Security vulnerability analysis (use secure-code-review skill)
- ❌ Performance benchmarking (use performance-optimization skill)
- ❌ UI/UX quality assessment (use ui-ux-design-system skill)

## Quality Gates

### CIA Platform Quality Gate Configuration

```
Quality Gate: CIA Platform Standard
│
├─ NEW CODE (changes since last version)
│  ├─ Coverage ≥ 80% on new code
│  ├─ Duplicated lines ≤ 3%
│  ├─ Maintainability rating = A
│  ├─ Reliability rating = A
│  ├─ Security rating = A
│  ├─ Security hotspots reviewed = 100%
│  └─ No new blocker or critical issues
│
└─ OVERALL CODE
   ├─ Coverage ≥ 70% (target: 80%)
   ├─ Duplicated lines ≤ 5%
   ├─ Technical debt ratio ≤ 5%
   ├─ Maintainability rating ≥ B
   ├─ Reliability rating ≥ B
   └─ Security rating ≥ B
```

### Quality Gate Decision Flow

```
Pull Request Quality Check
│
├─→ SonarCloud analysis passes?
│   ├─ YES → Continue
│   └─ NO → Block merge, fix issues
│
├─→ Code coverage meets threshold?
│   ├─ YES → Continue
│   └─ NO → Add missing tests
│
├─→ No new critical/blocker issues?
│   ├─ YES → Continue
│   └─ NO → Fix before merge
│
├─→ Security hotspots reviewed?
│   ├─ YES → Continue
│   └─ NO → Review and classify
│
└─→ Approve PR for merge
```

## SonarCloud Metrics

### Key Metrics for CIA Platform

| Metric | Description | Target | Action if Below |
|--------|------------|--------|----------------|
| Bugs | Reliability issues | 0 new | Fix before merge |
| Vulnerabilities | Security flaws | 0 new | Fix immediately |
| Code smells | Maintainability issues | A rating | Refactor in sprint |
| Coverage | Test coverage % | ≥ 80% new | Add unit tests |
| Duplications | Copy-paste code % | ≤ 3% new | Extract shared code |
| Complexity | Cyclomatic complexity | < 10/method | Decompose methods |
| Cognitive complexity | Readability measure | < 15/method | Simplify logic |
| Technical debt | Effort to fix issues | < 5% ratio | Plan debt sprints |

### Interpreting SonarCloud Results

```
SonarCloud Rating Scale:
  A = 0 issues (excellent)
  B = at least 1 minor issue
  C = at least 1 major issue
  D = at least 1 critical issue
  E = at least 1 blocker issue

CIA Platform Minimum: B for overall, A for new code
```

## Technical Debt Management

### Debt Classification

```
Technical Debt Categories
│
├─ DESIGN DEBT
│  ├─ Circular dependencies between modules
│  ├─ Missing abstraction layers
│  └─ Tight coupling between services
│
├─ CODE DEBT
│  ├─ Duplicated code across modules
│  ├─ Complex methods (high cyclomatic complexity)
│  ├─ Missing or outdated documentation
│  └─ Inconsistent naming conventions
│
├─ TEST DEBT
│  ├─ Missing unit tests for critical paths
│  ├─ Flaky integration tests
│  ├─ No E2E tests for user flows
│  └─ Low branch coverage in complex logic
│
└─ DEPENDENCY DEBT
   ├─ Outdated library versions
   ├─ Unused dependencies in POMs
   ├─ Known vulnerability in dependencies
   └─ License compatibility issues
```

### Debt Prioritization Matrix

| Impact | Effort: Low | Effort: Medium | Effort: High |
|--------|------------|---------------|-------------|
| **High** | Fix immediately | Plan for next sprint | Schedule dedicated sprint |
| **Medium** | Fix during feature work | Add to backlog | Evaluate ROI |
| **Low** | Boy scout rule | Track in backlog | Defer |

### Debt Reduction Patterns

```java
// ✅ Boy Scout Rule: Leave code cleaner than you found it
// When modifying a method, also:
// - Add missing tests
// - Fix adjacent code smells
// - Update outdated JavaDoc
// - Remove unused imports

// ✅ Extract Method for complex logic
// BEFORE: Method with cyclomatic complexity > 10
public String analyzeVotingPattern(List<Vote> votes) {
    // 50+ lines of complex logic
}

// AFTER: Decomposed into focused methods
public String analyzeVotingPattern(List<Vote> votes) {
    VoteStatistics stats = calculateStatistics(votes);
    String trend = identifyTrend(stats);
    return formatAnalysis(stats, trend);
}
```

## Code Quality Patterns

### Module Quality Standards

```
CIA Platform Module Quality Targets:
│
├─ model.* modules
│  ├─ Coverage: ≥ 70% (generated code excluded)
│  ├─ Complexity: Low (POJOs, entities)
│  └─ Focus: Correct JPA annotations, equals/hashCode
│
├─ service.* modules
│  ├─ Coverage: ≥ 85% (business logic)
│  ├─ Complexity: Medium (application logic)
│  └─ Focus: Transaction boundaries, error handling
│
├─ web-widgets module
│  ├─ Coverage: ≥ 75% (UI logic)
│  ├─ Complexity: Medium (view logic)
│  └─ Focus: Accessibility, responsive design
│
└─ citizen-intelligence-agency module
   ├─ Coverage: ≥ 80% (integration)
   ├─ Complexity: Low (configuration, wiring)
   └─ Focus: Security configuration, startup
```

### Maven Quality Plugins

```xml
<!-- JaCoCo coverage enforcement -->
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <configuration>
        <rules>
            <rule>
                <element>BUNDLE</element>
                <limits>
                    <limit>
                        <counter>LINE</counter>
                        <value>COVEREDRATIO</value>
                        <minimum>0.80</minimum>
                    </limit>
                    <limit>
                        <counter>BRANCH</counter>
                        <value>COVEREDRATIO</value>
                        <minimum>0.70</minimum>
                    </limit>
                </limits>
            </rule>
        </rules>
    </configuration>
</plugin>
```

## Quality Review Checklist

### Per Pull Request

```
Code Quality Review:
□ SonarCloud quality gate passes
□ No new bugs or vulnerabilities
□ Code coverage ≥ 80% on new code
□ No duplicated blocks > 10 lines
□ Cyclomatic complexity < 10 per method
□ Cognitive complexity < 15 per method
□ JavaDoc on public APIs
□ Consistent naming conventions
□ No TODO/FIXME without linked issue
```

### Per Release

```
Release Quality Assessment:
□ Overall coverage trending upward
□ Technical debt ratio ≤ 5%
□ Zero blocker or critical issues
□ All security hotspots reviewed
□ Dependency updates applied
□ Performance regression tests pass
□ E2E test suite passes
```

## References

- [SonarCloud Documentation](https://docs.sonarcloud.io/)
- [JaCoCo Maven Plugin](https://www.jacoco.org/jacoco/trunk/doc/maven.html)
- [Clean Code by Robert C. Martin](https://www.oreilly.com/library/view/clean-code-a/9780136083238/)
- [Hack23 ISMS Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)
