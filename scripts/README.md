# Next Release Priority Issues - Creation Guide

This directory contains the specifications and tools for creating the top 5 priority issues for the next CIA release.

## 📋 Overview

Based on comprehensive analysis of the repository, architecture documentation, SWOT analysis, and End-of-Life Strategy, we've identified 5 high-priority issues that should be addressed for the next release:

1. **Upgrade to Jetty 12** - Extends platform support until 2028
2. **Enhance Drools Test Coverage** - Quality assurance for risk assessment rules
3. **PostgreSQL 16 Documentation** - Improved deployment experience
4. **Security Dependency Audit** - Pre-release security updates
5. **Database Performance Optimization** - Faster dashboards and analytics

## 📁 Files in This Directory

- **Issue Templates**: `scripts/issue-templates/*.md` - Complete issue descriptions
- **Creation Script**: `scripts/create-next-release-issues.sh` - Automated issue creation
- **Summary Document**: `NEXT_RELEASE_PRIORITIES.md` - Analysis and priorities
- **This Guide**: `scripts/README.md` - Instructions

## 🚀 Quick Start

### Option 1: Manual Creation (Recommended for Review)

1. **Review Issue Specifications**
   ```bash
   cat scripts/issue-templates/issue1-jetty-upgrade.md
   cat scripts/issue-templates/issue2-drools-tests.md
   cat scripts/issue-templates/issue3-postgresql-docs.md
   cat scripts/issue-templates/issue4-security-audit.md
   cat scripts/issue-templates/issue5-performance.md
   ```

2. **Create Issues via GitHub Web Interface**
   - Go to https://github.com/Hack23/cia/issues/new
   - Copy content from `scripts/issue-templates/issueX-*.md`
   - Add appropriate labels (specified in each issue)
   - Submit

### Option 2: Automated Creation with GitHub CLI

1. **Prerequisites**
   ```bash
   # Install GitHub CLI
   # See: https://cli.github.com/
   
   # Authenticate
   gh auth login
   ```

2. **Run Creation Script**
   ```bash
   cd "$(git rev-parse --show-toplevel)"
   ./scripts/create-next-release-issues.sh
   ```

3. **Verify**
   ```bash
   gh issue list --repo Hack23/cia --limit 10
   ```

## 📊 Issue Details

### Issue #1: Upgrade to Jetty 12
- **Priority**: High (Score: 18)
- **Effort**: Medium (1-2 days)
- **Labels**: `enhancement`, `infrastructure`, `dependencies`
- **Impact**: Extends platform support lifecycle by 2 years
- **File**: `scripts/issue-templates/issue1-jetty-upgrade.md`

### Issue #2: Enhance Drools Test Coverage
- **Priority**: High (Score: 17)
- **Effort**: Small (4-8 hours)
- **Labels**: `testing`, `enhancement`, `political-analysis`, `analytics`
- **Impact**: Quality assurance for critical analytics features
- **File**: `scripts/issue-templates/issue2-drools-tests.md`

### Issue #3: PostgreSQL 16 Documentation
- **Priority**: Medium (Score: 13)
- **Effort**: Small (2-4 hours)
- **Labels**: `documentation`, `database`, `enhancement`
- **Impact**: Improved deployment experience
- **File**: `scripts/issue-templates/issue3-postgresql-docs.md`

### Issue #4: Security Dependency Audit
- **Priority**: High (Score: 17)
- **Effort**: Small (2-4 hours)
- **Labels**: `security`, `dependencies`, `enhancement`
- **Impact**: Maintains security posture
- **File**: `scripts/issue-templates/issue4-security-audit.md`

### Issue #5: Database Performance Optimization
- **Priority**: Medium (Score: 13)
- **Effort**: Medium (1-2 days)
- **Labels**: `performance`, `database`, `enhancement`, `optimization`
- **Impact**: Improved user experience
- **File**: `scripts/issue-templates/issue5-performance.md`

## 🎯 Priority Scoring

Issues were prioritized using this formula:
```
Priority Score = (Impact × 2) + Urgency + Effort Bonus
```

Where:
- **Impact** (1-5): Business and technical impact
- **Urgency** (1-5): Time sensitivity  
- **Effort Bonus**: Small=+3, Medium=+2, Large=+1, XL=0

## 📅 Implementation Order

Recommended order (though most can be parallelized):

1. **Jetty 12 Upgrade** - Foundation for extended support
2. **Security Audit** - Critical for release
3. **Drools Test Coverage** - Quality assurance
4. **PostgreSQL Documentation** - Improves onboarding
5. **Database Performance** - User experience enhancement

## 🔗 Analysis Sources

The priorities were derived from analysis of:

- **README.md** - Project overview and deployment
- **ARCHITECTURE.md** - System architecture
- **SWOT.md** - Strategic strengths and weaknesses
- **FUTURE_SWOT.md** - Future opportunities
- **End-of-Life-Strategy.md** - Technology lifecycle
- **DROOLS_RISK_RULES.md** - Rules documentation
- **DATA_MODEL.md** - Database structure
- **SECURITY_ARCHITECTURE.md** - Security implementation
- **Recent Releases** - Latest release 2025.11.7
- **Existing Issues** - Current open issues (2)
- **GitHub Workflows** - CI/CD and security processes

## 📝 Issue Template Structure

Each issue follows this structure:

```markdown
## 🎯 Objective
[Clear one-sentence goal]

## 📋 Background
[Context and why this matters]

## ✅ Acceptance Criteria
- [ ] Specific, testable criteria
- [ ] ...

## 🛠️ Implementation Guidance
### Files to Modify
### Approach
### Technical Considerations

## 🔗 Related Resources
[Links to docs and references]

## 📊 Metadata
Priority, Effort, Labels, Impact
```

## 🔍 Quality Checklist

Before creating issues, verify:

- [x] All issues are small/medium effort (< 2 days)
- [x] Clear acceptance criteria defined
- [x] Implementation guidance provided
- [x] Appropriate labels assigned
- [x] Related documentation linked
- [x] Impact clearly stated
- [x] No duplicate issues exist
- [x] Aligned with project SWOT analysis
- [x] Support project strategic goals

## 🤝 Contributing

After issues are created:

1. **Assign owners** based on expertise
2. **Link to milestone** "Next Release"
3. **Add to project board** if available
4. **Track progress** in issue comments
5. **Link PRs** to issues when working

## 📞 Support

For questions about these priorities:
- Review: `NEXT_RELEASE_PRIORITIES.md`
- Check: Project SWOT analysis
- See: End-of-Life Strategy
- Contact: Project maintainers

---

*Generated: 2025-11-11*  
*CIA Project: 2025-SNAPSHOT*  
*Analysis: Task Agent (Repository Analysis & Issue Creation)*
