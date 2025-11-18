# Agent Issue Assignments - Next Minor Release

> **Created:** 2025-11-16  
> **Purpose:** Document agent suitability and assignments for issues #7842-#7846

## 📊 Executive Summary

All 5 created issues for the next minor release are **100% agent-ready** with clear assignments matching the repository's custom agent expertise profiles.

## 🤖 Available Agents

- 🛠️ **stack-specialist** - Java, Spring Framework, Vaadin, Hibernate/JPA, PostgreSQL, Maven, Testing
- 🎨 **ui-enhancement-specialist** - Vaadin UI, Data Visualization, Responsive Design, Accessibility

## 📋 Issue Assignment Matrix

| Issue # | Title | Primary Agent(s) | Complexity | Hours | Match Quality |
|---------|-------|-----------------|------------|-------|---------------|
| [#7842](https://github.com/Hack23/cia/issues/7842) | 🎨 Upgrade Vaadin 8→24 | stack-specialist (60%)<br/>ui-enhancement-specialist (40%) | High | 12-16h | ✅ Perfect |
| [#7843](https://github.com/Hack23/cia/issues/7843) | 🔌 REST API Implementation | stack-specialist (100%) | Medium | 8-10h | ✅ Perfect |
| [#7844](https://github.com/Hack23/cia/issues/7844) | ⚡ Database Performance | stack-specialist (100%) | Medium | 8-10h | ✅ Perfect |
| [#7845](https://github.com/Hack23/cia/issues/7845) | 🔐 Spring Security 6.x | stack-specialist (100%) | Medium | 8-10h | ✅ Perfect |
| [#7846](https://github.com/Hack23/cia/issues/7846) | 📱 Mobile-First Design | ui-enhancement-specialist (100%) | Medium | 8-10h | ✅ Perfect |

## 🎯 Detailed Agent Assignments

### Issue #7842: 🎨 Upgrade Vaadin from 8.14.3 to 24+

**Recommended Agents:**
- 🛠️ **stack-specialist** (Phases 1, 3, 5)
- 🎨 **ui-enhancement-specialist** (Phases 2, 4)

**Agent Coordination:**
This issue requires collaboration between two agents:
1. stack-specialist handles framework upgrades and backend changes
2. ui-enhancement-specialist handles UI component migration and UX improvements
3. Both agents coordinate on Phase 2 (Component Migration)

**Subtasks for stack-specialist:**
- [ ] Phase 1: Update dependencies in parent-pom/pom.xml (2-3h)
- [ ] Phase 3: Migrate Navigator to Vaadin Flow Router (2-3h)
- [ ] Phase 5: Update Vaadin TestBench tests (2-3h)
- [ ] Configure Vaadin Maven Plugin
- [ ] Verify Spring integration compatibility

**Subtasks for ui-enhancement-specialist:**
- [ ] Phase 2: Migrate all UI components (Grid, Form, Layout) (4-6h)
- [ ] Phase 4: Apply Lumo theme and responsive styling (1-2h)
- [ ] Implement mobile-first responsive breakpoints
- [ ] Ensure WCAG 2.1 AA accessibility compliance
- [ ] Optimize data visualizations for Vaadin 24

**Agent Tools Required:**
- `view`, `edit`, `create`, `bash`, `search_code`

---

### Issue #7843: 🔌 Implement REST API for Political Risk Intelligence Data Export

**Recommended Agent:**
- 🛠️ **stack-specialist** (All phases)

**Why stack-specialist:**
- Spring Boot REST controller development
- Spring Security OAuth 2.0 configuration
- OpenAPI 3.0 documentation setup
- Rate limiting and API security patterns
- Integration testing with MockMvc

**Subtasks for stack-specialist:**
- [ ] Phase 1: Create REST API module structure (1-2h)
- [ ] Phase 2: Implement politician and party endpoints (2-3h)
- [ ] Phase 3: Implement risk violations endpoint (2-3h)
- [ ] Phase 4: Add OAuth 2.0 authentication and rate limiting (1-2h)
- [ ] Phase 5: Configure OpenAPI 3.0 documentation (1h)
- [ ] Phase 6: Create integration tests (1-2h)

**Agent Tools Required:**
- `view`, `edit`, `create`, `bash`, `search_code`

**Key Technical Areas:**
- Spring Boot Starter Web
- Spring Security OAuth2 Resource Server
- OpenAPI 3.0 (Springdoc)
- Bucket4j for rate limiting
- HATEOAS for hypermedia links

---

### Issue #7844: ⚡ Optimize Database Queries and Caching for Politician Dashboard Performance

**Recommended Agent:**
- 🛠️ **stack-specialist** (All phases)

**Why stack-specialist:**
- Hibernate/JPA query optimization
- PostgreSQL indexing and performance tuning
- Redis caching with Spring
- HikariCP connection pooling
- Database migration with Liquibase

**Subtasks for stack-specialist:**
- [ ] Phase 1: Enable Hibernate statistics and identify N+1 queries (1h)
- [ ] Phase 2: Add database indexes via Liquibase (2-3h)
- [ ] Phase 3: Fix N+1 queries with JOIN FETCH (2-3h)
- [ ] Phase 4: Implement Redis caching layer (2-3h)
- [ ] Phase 5: Configure HikariCP connection pool (1h)
- [ ] Phase 6: Add pagination to endpoints (1h)
- [ ] Phase 7: Create performance tests (1h)

**Agent Tools Required:**
- `view`, `edit`, `create`, `bash`, `search_code`

**Key Technical Areas:**
- Liquibase database migrations
- JPA @Query with @EntityGraph
- Spring Cache with Redis
- HikariCP configuration
- Performance testing with JUnit

---

### Issue #7845: 🔐 Upgrade Spring Security to 6.x with WebAuthn and Rate Limiting

**Recommended Agent:**
- 🛠️ **stack-specialist** (All phases)

**Why stack-specialist:**
- Spring Security 6.x migration patterns
- OAuth 2.1 authorization server setup
- WebAuthn integration (webauthn4j library)
- Security testing with Spring Security Test
- CSRF and session management

**Subtasks for stack-specialist:**
- [ ] Phase 1: Upgrade Spring Security dependencies (1-2h)
- [ ] Phase 2: Migrate SecurityConfig to Spring Security 6 DSL (2-3h)
- [ ] Phase 3: Implement WebAuthn passwordless auth (3-4h)
- [ ] Phase 4: Add rate limiting for login endpoints (1-2h)
- [ ] Phase 5: Create comprehensive security tests (2h)

**Agent Tools Required:**
- `view`, `edit`, `create`, `bash`, `search_code`

**Key Technical Areas:**
- Spring Security 6.x SecurityFilterChain
- WebAuthn4J library integration
- Bucket4j rate limiting
- CookieCsrfTokenRepository
- Spring Security Test with MockMvc

---

### Issue #7846: 📱 Implement Responsive Mobile-First Design for Political Dashboards

**Recommended Agent:**
- 🎨 **ui-enhancement-specialist** (All phases)

**Why ui-enhancement-specialist:**
- Responsive CSS and mobile-first design
- Vaadin responsive layouts
- Touch interaction optimization
- WCAG 2.1 AA accessibility compliance
- Mobile performance optimization

**Subtasks for ui-enhancement-specialist:**
- [ ] Phase 1: Create responsive CSS framework with breakpoints (2-3h)
- [ ] Phase 2: Design mobile-first view layouts (3-4h)
- [ ] Phase 3: Implement touch-optimized components (2h)
- [ ] Phase 4: Create mobile navigation (hamburger menu) (1-2h)
- [ ] Phase 5: Optimize images and assets for mobile (1-2h)
- [ ] Phase 6: Set up Lighthouse CI mobile testing (1h)

**Agent Tools Required:**
- `view`, `edit`, `create`, `bash`, `playwright-browser_navigate`, `playwright-browser_take_screenshot`

**Key Technical Areas:**
- CSS media queries and responsive breakpoints
- Vaadin ResponsiveLayout component
- Touch event handling (touchstart, touchend)
- WCAG 2.1 touch target sizes (48px minimum)
- Google Lighthouse mobile UX scoring
- Lazy loading and responsive images

---

## 📊 Agent Utilization Summary

**Total Agent Coverage:** 100% - All issues can be handled by existing agents

**Agent Distribution:**
- 🛠️ **stack-specialist**: 4 issues (80% workload)
  - #7842 (shared, 60%)
  - #7843 (100%)
  - #7844 (100%)
  - #7845 (100%)

- 🎨 **ui-enhancement-specialist**: 2 issues (40% workload)
  - #7842 (shared, 40%)
  - #7846 (100%)

**Complexity Distribution:**
- High: 1 issue (20%)
- Medium: 4 issues (80%)

**Total Estimated Hours:** 44-56 hours across all issues

## ✅ Agent Readiness Checklist

For each issue, the following elements ensure agent readiness:

- [x] Clear primary agent assignment
- [x] Detailed phase-by-phase subtask breakdown
- [x] Required tools specification
- [x] Key technical areas identified
- [x] Measurable acceptance criteria
- [x] Code examples and implementation guidance
- [x] Agent coordination notes (for multi-agent issues)

## 🚀 Recommended Execution Order

1. **Issue #7845** (Security) - Critical security updates, no dependencies
2. **Issue #7844** (Performance) - Improves platform for other work
3. **Issue #7843** (REST API) - Can proceed in parallel with #7846
4. **Issue #7846** (Mobile UX) - Can proceed in parallel with #7843
5. **Issue #7842** (Vaadin Upgrade) - Major refactoring, should be last

## 📝 Notes for Agent Execution

### For stack-specialist:
- All backend issues (#7843, #7844, #7845) are independent and can run in parallel
- Issue #7842 requires coordination with ui-enhancement-specialist
- Maven and Spring expertise is heavily utilized across all assigned issues

### For ui-enhancement-specialist:
- Issue #7846 can start immediately without dependencies
- Issue #7842 requires coordination with stack-specialist (dependency upgrades first)
- Playwright tools will be used for mobile testing and screenshots

## 🔗 Related Documentation

- [Custom Agents README](.github/agents/README.md)
- [Stack Specialist Profile](.github/agents/stack-specialist.md)
- [UI Enhancement Specialist Profile](.github/agents/ui-enhancement-specialist.md)
- [Issues #7842-#7846](https://github.com/Hack23/cia/issues?q=is%3Aissue+is%3Aopen+7842..7846)

---

**Last Updated:** 2025-11-16  
**Status:** All issues agent-ready for next minor release execution
