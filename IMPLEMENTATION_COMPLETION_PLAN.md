# JPA Entity Implementation Completion Plan

## Current Status
**Implemented:** 3 of 10 entities (30%)
**Coverage:** 93.8% (76/81 views mapped)
**Target:** 100% (81/81 views mapped)

## Completed Entities

### Phase 1-2 (Committed)
1. ✅ ViewPartyPerformanceMetrics (450+ lines, 26 columns) - Comparative Analysis
2. ✅ ViewPoliticianRiskSummary (380+ lines, 20 columns) - Pattern Recognition
3. ✅ ViewMinistryEffectivenessTrends (485+ lines, 27 columns) - Predictive Intelligence

## Remaining Implementation (7 entities)

### HIGH Priority Ministry Views (2 entities)

#### 4. ViewMinistryProductivityMatrix
- **View:** view_ministry_productivity_matrix (line 6529)
- **Columns:** 16 (org_code+year composite PK, annual benchmarking)
- **Purpose:** Ministry productivity benchmarking with quartile rankings
- **Primary Key:** Composite (org_code + year)
- **Estimated Lines:** ~420

**Key Columns:**
```
org_code, short_code, name, year, documents_produced, propositions, 
government_bills, unique_contributors, earliest_document, latest_document,
median_documents, avg_documents, p25_documents, p75_documents, 
pct_vs_average, productivity_quartile, performance_assessment
```

#### 5. ViewMinistryRiskEvolution
- **View:** view_ministry_risk_evolution (line 6617)
- **Columns:** ~18 (org_code+assessment_period composite PK)
- **Purpose:** Evolution of ministry risk indicators over time
- **Primary Key:** Composite (org_code + assessment_period)
- **Estimated Lines:** ~450

### HIGH Priority Committee View (1 entity)

#### 6. ViewCommitteeProductivity
- **View:** view_committee_productivity (line 6037)
- **Columns:** 23 (committee_code PK, comprehensive productivity metrics)
- **Purpose:** Committee productivity and efficiency indicators
- **Primary Key:** committee_code
- **Estimated Lines:** ~500

**Key Columns:**
```
committee_code, committee_name, total_members, chairs_count, vice_chairs_count,
regular_members, substitutes, staffing_status, total_decisions_all_time,
decisions_last_year, decisions_last_month, latest_decision_date,
days_since_last_decision, total_documents, documents_last_year, motions_count,
propositions_count, reports_count, decisions_per_member, documents_per_member,
ballots_decided_last_year, avg_approval_rate, productivity_score,
productivity_level, performance_concerns, productivity_assessment
```

### MEDIUM Priority Document Views (4 entities)

#### 7. ViewRiksdagenCommitteeParliamentMemberProposal
- **View:** view_riksdagen_committee_parliament_member_proposal (line 7775)
- **Purpose:** Parliamentary member proposals to committees
- **Estimated Lines:** ~200
- **Complexity:** Lower - straightforward mapping

#### 8. ViewRiksdagenMemberProposals
- **View:** view_riksdagen_member_proposals (line 8211)
- **Purpose:** Member legislative proposals tracking
- **Estimated Lines:** ~250
- **Complexity:** Lower - aggregate view

#### 9. ViewRiksdagenPoliticianDocumentDailySummary
- **View:** view_riksdagen_politician_document_daily_summary (line 9251)
- **Type:** **MATERIALIZED VIEW**
- **Purpose:** Daily politician document productivity
- **Estimated Lines:** ~300
- **Note:** Materialized view - may need special handling

#### 10. ViewRiksdagenPoliticianDocumentSummary
- **View:** view_riksdagen_politician_document_summary (line 8257)
- **Type:** **MATERIALIZED VIEW**
- **Purpose:** Aggregated politician document statistics
- **Estimated Lines:** ~350
- **Note:** Materialized view - may need special handling

## Implementation Approach

### Batch 1: HIGH Priority (Entities 4-6)
**Estimated Effort:** 6-8 hours
**Impact:** Completes Predictive Intelligence Framework (86% → 100%)

1. Extract column definitions from full_schema.sql
2. Create JPA entities following ViewMinistryEffectivenessTrends pattern
3. Handle composite primary keys properly
4. Register in persistence.xml
5. Build and validate

### Batch 2: MEDIUM Priority (Entities 7-10)
**Estimated Effort:** 4-6 hours
**Impact:** Achieves 100% coverage (81/81 views)

1. Extract column definitions for each view
2. Create entities with simpler structures
3. Handle materialized views (same as regular views for JPA)
4. Register in persistence.xml
5. Build and validate

## Technical Notes

### Composite Primary Keys
Views with composite keys need:
- Multiple fields marked with `@Id`
- Proper equals/hashCode implementation (handled by reflection builders)

### Materialized Views
JPA treats materialized views identically to regular views:
- Use `@Table(name = "view_name")`
- Read-only entities (no INSERT/UPDATE/DELETE)
- Same annotation patterns as regular view entities

### Build Validation
After each batch:
```bash
mvn clean compile -pl model.internal.application.user.impl -am -DskipTests
mvn clean compile -pl service.data.impl -am -DskipTests
```

## Success Criteria

- [ ] All 10 entities implemented
- [ ] All entities registered in persistence.xml
- [ ] Build successful with no compilation errors
- [ ] All 6 OSINT frameworks at 100% coverage
- [ ] Overall coverage: 100% (81/81 views mapped)
- [ ] Documentation updated

## Framework Impact Projection

**After Complete Implementation:**
- Temporal Analysis: 97% → 98% (1 daily summary added)
- Comparative Analysis: 100% ✅ (COMPLETE)
- Pattern Recognition: 100% ✅ (COMPLETE)
- **Predictive Intelligence: 86% → 100%** ✅ (2 ministry views complete framework)
- Network Analysis: 100% ✅ (COMPLETE)
- Decision Intelligence: 100% ✅ (COMPLETE)

**Result:** All 6 OSINT analysis frameworks at 100% coverage
