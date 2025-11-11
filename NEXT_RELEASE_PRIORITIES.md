# Top 5 Priority Issues for Next Release

This document outlines the top 5 priority issues identified for preparing the next CIA release. These issues are based on comprehensive analysis of the codebase, architecture documentation, SWOT analysis, and current project status.

## Analysis Summary

### Repository Status
- **Current Release**: 2025.11.7 (released 2025-11-07)
- **Open Issues**: 2 existing open issues
- **Security Posture**: OpenSSF Scorecard 7.2/10, CII Best Practices, SLSA Level 3
- **Technology Stack**: Java 25 (src 21), Spring 5.x, Vaadin 8, PostgreSQL 16, Jetty 10

### Strategic Priorities
Based on the SWOT analysis and End-of-Life Strategy:
- **Strengths to Leverage**: Comprehensive data, robust visualization, modular architecture
- **Weaknesses to Address**: Legacy tech stack, manual processing, resource constraints
- **Opportunities**: AI-enhanced analytics, performance optimization
- **Threats**: Technology EOL, technical debt growth

---

## Priority Issue #1: Upgrade to Jetty 12 for Extended Support Until 2028

### Objective
Upgrade the web server from Jetty 10 to Jetty 12 to extend platform support lifecycle until 2028 while maintaining javax.servlet compatibility.

### Priority & Effort
- **Priority**: High (Impact × 2 + Urgency + Effort Bonus = 18)
  - Impact: 5 (Extends platform viability by 2 years)
  - Urgency: 4 (Jetty 10 EOL in 2026)
  - Effort Bonus: +2 (Medium effort)
- **Effort**: Medium (1-2 days)

### Background
According to the End-of-Life Strategy, Jetty 10 reaches EOL in 2026. Jetty 12 supports both javax.servlet and Jakarta namespaces (EOL 2028), allowing the platform to remain compatible with future JVMs while avoiding architectural transition to Jakarta.

### Key Files
- `parent-pom/pom.xml` - Update Jetty version properties
- `citizen-intelligence-agency/pom.xml` - Verify Jetty plugin configuration
- `End-of-Life-Strategy.md` - Update EOL dates

### Labels
`enhancement`, `infrastructure`, `dependencies`

### Full Specification
See: `/tmp/issue1.md`

---

## Priority Issue #2: Enhance Test Coverage for Drools Risk Assessment Rules

### Objective
Enhance test coverage for the Drools-based risk assessment rules recently added in PR #7770, focusing on politician, party, ministry, and committee behavior analysis.

### Priority & Effort
- **Priority**: High (Impact × 2 + Urgency + Effort Bonus = 17)
  - Impact: 4 (Quality assurance for critical analytics)
  - Urgency: 4 (New features need test coverage)
  - Effort Bonus: +3 (Small effort)
- **Effort**: Small (4-8 hours)

### Background
The project recently introduced comprehensive risk assessment rules for political analysis (PR #7770). These Drools rules provide critical intelligence about politician behavior, party dynamics, ministry performance, and committee effectiveness. Robust unit tests are needed to ensure reliability and prevent regressions.

### Key Files
- `service.impl/src/test/java/com/hack23/cia/service/impl/rules/` - Create test classes
- `service.impl/src/main/resources/com/hack23/cia/service/impl/rules/` - Rules to test
- `DROOLS_RISK_RULES.md` - Document testing approach

### Labels
`testing`, `enhancement`, `political-analysis`, `analytics`

### Full Specification
See: `/tmp/issue2.md`

---

## Priority Issue #3: Update and Validate PostgreSQL 16 Configuration Documentation

### Objective
Update and validate PostgreSQL 16 configuration documentation to ensure smooth deployment experience for new installations.

### Priority & Effort
- **Priority**: Medium (Impact × 2 + Urgency + Effort Bonus = 13)
  - Impact: 3 (Improves deployment experience)
  - Urgency: 3 (Important but not urgent)
  - Effort Bonus: +3 (Small effort)
- **Effort**: Small (2-4 hours)

### Background
The README.md references PostgreSQL 16 with detailed configuration instructions including SSL setup, prepared transactions, and required extensions. These instructions need validation and potential updates to ensure they're current and complete.

### Key Files
- `README.md` - PostgreSQL 16 Configuration Guide (lines ~323-442)
- Optional: `scripts/verify-postgresql-setup.sh` - Automated verification

### Labels
`documentation`, `database`, `enhancement`

### Full Specification
See: `/tmp/issue3.md`

---

## Priority Issue #4: Pre-Release Security Dependency Audit and Updates

### Objective
Conduct a pre-release security audit of critical dependencies and apply necessary updates to maintain the project's security posture.

### Priority & Effort
- **Priority**: High (Impact × 2 + Urgency + Effort Bonus = 17)
  - Impact: 5 (Maintains security posture)
  - Urgency: 4 (Pre-release security audit)
  - Effort Bonus: +3 (Small effort)
- **Effort**: Small (2-4 hours)

### Background
The CIA project maintains high security standards (OpenSSF Scorecard 7.2/10). As part of release preparation, ensure all security-critical dependencies are up-to-date, particularly Spring Security, Bouncy Castle, and Logback.

### Key Dependencies to Review
- Spring Security 5.8.16
- Bouncy Castle 1.82
- Logback 1.5.21
- Spring Framework 5.3.39.hack23java25

### Key Files
- `parent-pom/pom.xml` - Update dependency versions
- `SECURITY.md` - Update if needed
- `CHANGELOG.md` - Document security updates

### Labels
`security`, `dependencies`, `enhancement`

### Full Specification
See: `/tmp/issue4.md`

---

## Priority Issue #5: Database Query Performance Optimization

### Objective
Optimize database query performance through strategic indexing and elimination of N+1 query patterns to improve dashboard and analytics response times.

### Priority & Effort
- **Priority**: Medium (Impact × 2 + Urgency + Effort Bonus = 13)
  - Impact: 4 (Improves user experience)
  - Urgency: 3 (Important for scalability)
  - Effort Bonus: +2 (Medium effort)
- **Effort**: Medium (1-2 days)

### Background
The CIA platform processes large datasets of political information. As the database grows, query performance becomes critical for providing responsive dashboards and analytics. This task focuses on identifying and resolving performance bottlenecks through database optimization.

### Performance Goals
- Dashboard page load < 2 seconds
- Analytics queries < 5 seconds
- Eliminate N+1 query patterns
- Strategic database indexing

### Key Files
- `model.internal.application.data.impl/*Entity.java` - Review fetch strategies
- `service.data.impl/src/main/java/*/repository/*.java` - Optimize queries
- Create: Database migration scripts for indexes

### Labels
`performance`, `database`, `enhancement`, `optimization`

### Full Specification
See: `/tmp/issue5.md`

---

## Priority Scoring Methodology

Issues were prioritized using the formula:
```
Priority Score = (Impact × 2) + Urgency + Effort Bonus
```

Where:
- **Impact** (1-5): Business and technical impact
- **Urgency** (1-5): Time sensitivity
- **Effort Bonus**: S=+3, M=+2, L=+1, XL=0

## Implementation Order Recommendation

1. **Issue #1 (Jetty 12 Upgrade)** - Foundation for extended support
2. **Issue #4 (Security Audit)** - Critical for release
3. **Issue #2 (Test Coverage)** - Quality assurance
4. **Issue #3 (Documentation)** - Improves onboarding
5. **Issue #5 (Performance)** - User experience enhancement

These can be worked on in parallel by different contributors as they have minimal dependencies on each other.

## Next Steps

To create these issues in GitHub:

1. Copy issue specifications from `/tmp/issue{1-5}.md`
2. Use GitHub web interface or API to create issues
3. Apply appropriate labels as specified
4. Link issues to "Next Release" milestone
5. Assign to appropriate team members based on expertise

## Documentation References

- [SWOT Analysis](SWOT.md) - Strategic strengths/weaknesses
- [End-of-Life Strategy](End-of-Life-Strategy.md) - Technology lifecycle
- [ARCHITECTURE.md](ARCHITECTURE.md) - System architecture
- [DROOLS_RISK_RULES.md](DROOLS_RISK_RULES.md) - Rules documentation
- [DATA_MODEL.md](DATA_MODEL.md) - Database structure
- [SECURITY_ARCHITECTURE.md](SECURITY_ARCHITECTURE.md) - Security implementation

---

*Generated: 2025-11-11*  
*CIA Project Version: 2025-SNAPSHOT*  
*Analysis Agent: Task Agent (Repository Analysis & Issue Creation)*
