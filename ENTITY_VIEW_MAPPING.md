# JPA Entity to Database View Mapping for Risk Rules Framework

## 📋 Overview

This document maps JPA entity classes to their corresponding database views and the risk rules they support. This mapping is essential for the complete 45-rule intelligence framework defined in RISK_RULES_INTOP_OSINT.md.

**Status**: Partially Implemented  
**Date**: 2025-11-17  
**Related Issue**: #[Current Issue Number]

---

## 📊 Implementation Status Summary

| Category | Total Views | Implemented | Pending | Coverage |
|----------|-------------|-------------|---------|----------|
| V1.30 OSINT Performance Tracking | 4 | 1 | 3 | 25% |
| V1.29 Intelligence Operations | 6 | 0 | 6 | 0% |
| **TOTAL NEW VIEWS** | **10** | **1** | **9** | **10%** |

---

## ✅ Implemented Entities (v1.30)

### 1. ViewPoliticianBehavioralTrends
- **Package**: `com.hack23.cia.model.internal.application.data.politician.impl`
- **Database View**: `view_politician_behavioral_trends`
- **Liquibase Version**: v1.30
- **Primary Key**: Composite (person_id + period_start)
- **Columns**: 26 fields
- **Risk Rules Supported**: P-01 through P-24 (All politician behavioral rules)
- **Intelligence Purpose**: Time-series analysis of individual politician behavioral metrics (absence, effectiveness, rebellion) with automated classification
- **Status**: ✅ COMPLETE
- **Key Metrics**:
  - Behavioral metrics: absence_rate, win_rate, rebel_rate
  - Trend indicators: absence_trend, win_rate_trend, rebel_rate_trend
  - Moving averages: 3-month MA for smoothed trend analysis
  - Classifications: attendance_status, effectiveness_status, discipline_status, behavioral_assessment

---

## 🔶 Pending Entity Implementations

### v1.30 OSINT Performance Tracking Views (3 remaining)

#### 2. ViewPartyEffectivenessTrends
- **Package**: `com.hack23.cia.model.internal.application.data.party.impl`
- **Database View**: `view_party_effectiveness_trends`
- **Liquibase Version**: v1.30
- **Primary Key**: Composite (party + period_start)
- **Estimated Columns**: 30+
- **Risk Rules Supported**: Pa-01 through Pa-10 (All party-level rules)
- **Intelligence Purpose**: Monitor party-level performance metrics over time, track win rates, productivity, and collaboration patterns
- **Key Metrics**:
  - Performance: avg_win_rate, avg_absence_rate, avg_rebel_rate
  - Productivity: documents_produced, motions_count, votes_per_member
  - Trend indicators: win_rate_trend, absence_trend, member_change
  - Classifications: performance_level, productivity_level
- **Status**: 🔶 PENDING - Entity class needs to be created

#### 3. ViewRiskScoreEvolution
- **Package**: `com.hack23.cia.model.internal.application.data.rules.impl`
- **Database View**: `view_risk_score_evolution`
- **Liquibase Version**: v1.30
- **Primary Key**: Composite (person_id + assessment_period)
- **Estimated Columns**: 22
- **Risk Rules Supported**: All 45 rules (cross-cutting risk assessment)
- **Intelligence Purpose**: Track temporal evolution of risk scores with severity classification and trend analysis
- **Key Metrics**:
  - Risk metrics: risk_score, prev_risk_score, risk_score_change
  - Contributing factors: absence_rate, win_rate, rebel_rate, violation_count
  - Classifications: risk_severity, risk_trend, severity_transition
  - Assessment: risk_assessment (CRITICAL, HIGH, MODERATE, LOW, MINIMAL)
- **Status**: 🔶 PENDING - Entity class needs to be created

#### 4. ViewCommitteeProductivityMatrix
- **Package**: `com.hack23.cia.model.internal.application.data.committee.impl`
- **Database View**: `view_committee_productivity_matrix`
- **Liquibase Version**: v1.30
- **Primary Key**: Composite (committee_code + period_start)
- **Estimated Columns**: 28
- **Risk Rules Supported**: C-01 through C-04 (Committee performance rules)
- **Intelligence Purpose**: Quarterly committee productivity metrics with comparative analysis and benchmarking
- **Key Metrics**:
  - Productivity: total_documents, committee_reports, documents_per_member
  - Trend indicators: document_change, document_change_pct, ma_4quarter_documents
  - Benchmarking: vs_average, vs_average_pct, period_median_documents
  - Classifications: productivity_level, productivity_trend, productivity_assessment
- **Status**: 🔶 PENDING - Entity class needs to be created

---

### v1.29 Intelligence Operations Enhancement Views (6 remaining)

#### 5. ViewRiksdagenPartyMomentumAnalysis
- **Package**: `com.hack23.cia.model.internal.application.data.party.impl`
- **Database View**: `view_riksdagen_party_momentum_analysis`
- **Liquibase Version**: v1.29
- **Primary Key**: Composite (party + year + quarter)
- **Estimated Columns**: 14
- **Risk Rules Supported**: Pa-01, Pa-02, Pa-07 (Party momentum and stability)
- **Intelligence Purpose**: Detect momentum shifts in party support with acceleration/deceleration patterns
- **Key Metrics**:
  - Momentum: momentum, acceleration, prev_quarter_rate
  - Smoothing: moving_avg_4q, volatility
  - Classifications: trend_direction, stability_classification, intelligence_assessment
- **Status**: 🔶 PENDING - Entity class needs to be created

#### 6. ViewRiksdagenCoalitionAlignmentMatrix
- **Package**: `com.hack23.cia.model.internal.application.data.party.impl`
- **Database View**: `view_riksdagen_coalition_alignment_matrix`
- **Liquibase Version**: v1.29
- **Primary Key**: Composite (party_1 + party_2)
- **Estimated Columns**: 12
- **Risk Rules Supported**: Pa-03, Pa-08 (Coalition formation and stability)
- **Intelligence Purpose**: Measure voting alignment between party pairs for coalition formation prediction
- **Key Metrics**:
  - Alignment: shared_votes, aligned_votes, opposed_votes, alignment_rate
  - Analysis: coalition_likelihood, bloc_relationship
  - Intelligence: intelligence_comment, years_observed
- **Status**: 🔶 PENDING - Entity class needs to be created

#### 7. ViewRiksdagenVotingAnomalyDetection
- **Package**: `com.hack23.cia.model.internal.application.data.politician.impl`
- **Database View**: `view_riksdagen_voting_anomaly_detection`
- **Liquibase Version**: v1.29
- **Primary Key**: Composite (person_id + party)
- **Estimated Columns**: 13
- **Risk Rules Supported**: P-03, P-05, P-13 (Rebellion and discipline)
- **Intelligence Purpose**: Detect voting anomalies and party discipline deviations
- **Key Metrics**:
  - Voting patterns: total_votes, aligned_votes, opposed_votes
  - Discipline: party_discipline_score, rebellion_rate, unanimous_deviations
  - Classifications: discipline_classification, defection_risk_assessment
- **Status**: 🔶 PENDING - Entity class needs to be created

#### 8. ViewRiksdagenPoliticianInfluenceMetrics
- **Package**: `com.hack23.cia.model.internal.application.data.politician.impl`
- **Database View**: `view_riksdagen_politician_influence_metrics`
- **Liquibase Version**: v1.29
- **Primary Key**: person_id
- **Estimated Columns**: 10
- **Risk Rules Supported**: P-20, P-21 (Network influence and broker roles)
- **Intelligence Purpose**: Basic network centrality and influence scoring
- **Key Metrics**:
  - Network: network_connections, cross_party_bridges
  - Centrality: normalized_centrality, connectivity_level
  - Classification: broker_classification, influence_score, influence_assessment
- **Status**: 🔶 PENDING - Entity class needs to be created

#### 9. ViewRiksdagenCrisisResilienceIndicators
- **Package**: `com.hack23.cia.model.internal.application.data.politician.impl`
- **Database View**: `view_riksdagen_crisis_resilience_indicators`
- **Liquibase Version**: v1.29
- **Primary Key**: person_id
- **Estimated Columns**: 13
- **Risk Rules Supported**: P-08, P-22 (Performance under pressure)
- **Intelligence Purpose**: Assess politician performance during crisis periods vs normal periods
- **Key Metrics**:
  - Crisis performance: crisis_period_votes, crisis_absence_rate, crisis_party_discipline
  - Normal baseline: normal_period_votes, normal_absence_rate
  - Analysis: absence_rate_change, resilience_score
  - Classification: resilience_classification, pressure_performance_assessment
- **Status**: 🔶 PENDING - Entity class needs to be created

#### 10. ViewRiksdagenIntelligenceDashboard
- **Package**: `com.hack23.cia.model.internal.application.data.impl`
- **Database View**: `view_riksdagen_intelligence_dashboard`
- **Liquibase Version**: v1.29
- **Primary Key**: None (single-row summary view, timestamp-based)
- **Estimated Columns**: 3
- **Risk Rules Supported**: All rules (summary dashboard)
- **Intelligence Purpose**: Executive summary dashboard with overall stability and coalition assessments
- **Key Metrics**:
  - stability_assessment: Overall political stability indicator
  - coalition_assessment: Coalition formation likelihood and status
  - intelligence_report_timestamp: Report generation time
- **Status**: 🔶 PENDING - Entity class needs to be created

---

## 📝 Implementation Guidelines

### Entity Class Template

All view entities should follow this pattern:

```java
package com.hack23.cia.model.internal.application.data.[package].impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * JPA entity for [view_name] database view.
 * 
 * Intelligence Purpose: [purpose description]
 * Created by: Liquibase [version]
 * Risk Rules Supported: [rule numbers]
 */
@Entity(name = "[ClassName]")
@Table(name = "[view_name]")
public class [ClassName] implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // ID fields marked with @Id
    // Other fields with @Column
    // Dates with @Temporal
    // BigDecimal for numeric fields with precision
    
    // Default constructor
    // Getters and setters
    // equals(), hashCode(), toString() using Commons Lang builders
}
```

### Key Annotations

- `@Entity(name = "ClassName")`: JPA entity marker
- `@Table(name = "view_name")`: Database view name (lowercase with underscores)
- `@Id`: Primary key fields (may be composite)
- `@Column(name = "column_name")`: Column mapping
- `@Temporal(TemporalType.DATE|TIMESTAMP)`: For Date fields
- For BigDecimal: `@Column(name = "col", precision = X, scale = Y)`

### Composite Keys

For views with composite keys (multiple @Id fields), the combination of all @Id fields forms the primary key. No separate @IdClass is needed if using simple types.

---

## 🔗 Integration with Risk Rules Framework

### Rule-to-View Mapping

| Rule Category | Primary Views | Secondary Views |
|---------------|---------------|-----------------|
| **Politician Rules (P-01 to P-24)** | ViewPoliticianBehavioralTrends, ViewRiksdagenVotingAnomalyDetection | ViewRiksdagenPoliticianInfluenceMetrics, ViewRiksdagenCrisisResilienceIndicators |
| **Party Rules (Pa-01 to Pa-10)** | ViewPartyEffectivenessTrends, ViewRiksdagenPartyMomentumAnalysis | ViewRiksdagenCoalitionAlignmentMatrix |
| **Committee Rules (C-01 to C-04)** | ViewCommitteeProductivityMatrix | - |
| **Ministry Rules (M-01 to M-04)** | (v1.31 - not yet implemented) | - |
| **Cross-Cutting** | ViewRiskScoreEvolution, ViewRiksdagenIntelligenceDashboard | - |

---

## 📊 Technical Specifications

### Data Types Mapping

| SQL Type | Java Type | Notes |
|----------|-----------|-------|
| VARCHAR | String | Use @Column(length = X) |
| INTEGER | Integer or Long | Use Long for counts |
| NUMERIC(p,s) | BigDecimal | Use @Column(precision = p, scale = s) |
| DATE | Date | Use @Temporal(TemporalType.DATE) |
| TIMESTAMP | Date | Use @Temporal(TemporalType.TIMESTAMP) |
| BOOLEAN | Boolean | - |

### Naming Conventions

- **Entity Class**: PascalCase, e.g., `ViewPoliticianBehavioralTrends`
- **Table Name**: snake_case, e.g., `view_politician_behavioral_trends`
- **Column Name**: snake_case, e.g., `avg_absence_rate`
- **Field Name**: camelCase, e.g., `avgAbsenceRate`

---

## 🚀 Next Steps

### Priority 1: Complete v1.30 Views (Required for Core Risk Assessment)
1. **ViewRiskScoreEvolution** - Critical for all 45 rules
2. **ViewCommitteeProductivityMatrix** - Committee rules (C-01 to C-04)
3. **ViewPartyEffectivenessTrends** - Party rules (Pa-01 to Pa-10)

### Priority 2: Complete v1.29 Intelligence Views (Enhanced Analytics)
4. **ViewRiksdagenVotingAnomalyDetection** - Critical for P-03, P-05, P-13
5. **ViewRiksdagenPartyMomentumAnalysis** - Party momentum analysis
6. **ViewRiksdagenCoalitionAlignmentMatrix** - Coalition formation
7. **ViewRiksdagenPoliticianInfluenceMetrics** - Network analysis
8. **ViewRiksdagenCrisisResilienceIndicators** - Crisis performance
9. **ViewRiksdagenIntelligenceDashboard** - Executive summary

### Priority 3: Verification and Testing
- Update persistence.xml with all new entity registrations
- Build and test entity mappings
- Verify query performance
- Test integration with rules engine
- Document usage examples

---

## 📚 Related Documentation

- [RISK_RULES_INTOP_OSINT.md](RISK_RULES_INTOP_OSINT.md) - Complete 45-rule framework
- [DATABASE_VIEW_INTELLIGENCE_CATALOG.md](DATABASE_VIEW_INTELLIGENCE_CATALOG.md) - View catalog and usage patterns
- [LIQUIBASE_CHANGELOG_INTELLIGENCE_ANALYSIS.md](LIQUIBASE_CHANGELOG_INTELLIGENCE_ANALYSIS.md) - Changelog evolution
- [DATA_MODEL.md](DATA_MODEL.md) - Database schema and relationships
- [service.data.impl/README-SCHEMA-MAINTENANCE.md](service.data.impl/README-SCHEMA-MAINTENANCE.md) - Schema maintenance guidelines

---

## 📝 Notes

- All views are READ-ONLY (database views, not tables)
- No @Immutable annotation needed (causes module dependency issues)
- Views are materialized or standard based on Liquibase definitions
- Composite keys use multiple @Id annotations on entity fields
- Use Apache Commons Lang builders for equals/hashCode/toString

**Last Updated**: 2025-11-17  
**Maintainer**: CIA Intelligence Operations Team
