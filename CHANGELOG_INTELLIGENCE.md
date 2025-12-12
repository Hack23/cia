# Intelligence Evolution Changelog

Comprehensive tracking of intelligence capabilities, database views, risk rules, and analytical frameworks across all releases of the Citizen Intelligence Agency platform.

**Consolidated From:**
- CHANGELOG_INTELLIGENCE_ANALYSIS.md - Intelligence frameworks and OSINT enhancements
- CHANGELOG_DATABASE_VIEWS.md - Database view schemas and specifications  
- CHANGELOG_RISK_RULES.md - Risk rule evolution and behavioral analysis

**Format**: [Keep a Changelog](https://keepachangelog.com/)  
**Versioning**: Aligned with CIA platform releases  
**Consolidation Date**: 2025-12-10  
**Maintained By**: Citizen Intelligence Agency Intelligence Operations Team

---

## 📋 Quick Reference

| Document Type | Description | Coverage |
|---------------|-------------|----------|
| **Intelligence Frameworks** | Analysis methodologies (Temporal, Comparative, Pattern Recognition, Predictive, Network, Decision) | 6 frameworks |
| **Database Views** | Data layer supporting intelligence products | 84+ views |
| **Risk Rules** | Behavioral detection and assessment rules | 50+ rules |
| **Related Documentation** | [Data Analysis](DATA_ANALYSIS_INTOP_OSINT.md), [Risk Rules](RISK_RULES_INTOP_OSINT.md), [View Catalog](DATABASE_VIEW_INTELLIGENCE_CATALOG.md) | Full suite |

**Original Changelogs** (Archived): [docs/archive/](docs/archive/)

---

## 📖 Table of Contents

**Versions** (Most Recent First):
- [1.39.0](#1390---2025-12-01) - Database view fixes (ministry effectiveness)
- [1.36.0](#1360---2025-11-24) - Decision Intelligence Framework, 3 new views
- [1.35.0](#1350---2025-11-22) - 5 decision pattern risk rules, documentation enhancement
- [1.34.0](#1340---2025-11-18) - 3 temporal views, OSINT improvements
- [1.33.0](#1330---2025-11-10) - 10 behavioral risk rules, pg_audit integration
- [1.32.0](#1320---2025-11-01) - Network & Predictive frameworks, 2 new views
- [1.31.0](#1310---2025-10-15) - 4 ministry risk rules, ministry views
- [1.30.0](#1300---2025-09-01) - 12 politician risk rules
- [1.20.0](#1200---2024-06-01) - Initial risk assessment system
- [1.13.0](#1130---2023-03-15) - Pattern Recognition Framework
- [1.10.0](#1100---2022-06-01) - Temporal & Comparative Frameworks
- [1.0.0](#100---2014-11-01) - Initial Release
- [Unreleased](#unreleased) - In development

**Appendices:**
- [Appendix A: Database View Schema Details](#appendix-a-database-view-schema-details)
- [Appendix B: Risk Rule Specifications](#appendix-b-risk-rule-specifications)

---

## [1.39.0] - 2025-12-01

### 🗄️ Database Views (3 Fixed)

**Critical Ministry View Fixes** - Resolved long-standing issue preventing ministry intelligence tracking

#### view_ministry_effectiveness_trends ⚠️ CRITICAL FIX
**Issue**: View returned 0 rows despite previous fix attempts in v1.31, v1.32, v1.37  
**Root Cause**: Incorrect filter `WHERE LOWER(org_code) LIKE '%departement%'` - Ministry org_codes are SHORT CODES ('KN', 'N', 'UD'), not full names

**Solution**: Removed LIKE filter, rely on `assignment_type = 'Departement'` only

**Impact**:
- ✅ Unblocks ministry effectiveness tracking (Product Line 4)
- ✅ Enables 4 ministry risk rules (M-01 through M-04)  
- ✅ Related views also fixed: view_ministry_productivity_matrix, view_ministry_risk_evolution

**Cross-Reference**: Supports Ministry Risk Assessment Framework  
*See [Appendix A.1](#a1-ministry-view-fixes) for detailed DDL changes*

### 🔴 Risk Rules
No risk rule changes in this version (blocked by view issues, now resolved)

**Cross-Reference**: With views fixed, M-01 through M-04 rules now operational in v1.31 framework

---

## [1.36.0] - 2025-11-24

### 🎯 Intelligence Frameworks (1 Added)

**Decision Intelligence Framework** - 6th core analysis framework
- Evaluates ministry effectiveness through policy outcomes
- Combines temporal, comparative, and predictive analysis methodologies
- Supports coalition stability assessment through decision pattern analysis
- Enables tracking of policy impact and budget execution effectiveness

**Intelligence Product**: Ministry Performance Scorecards, Party Decision Analysis Reports

### 🗄️ Database Views (3 Added)

#### 1. view_ministry_decision_impact
**Purpose**: Ministry effectiveness tracking through decision outcomes  
**Key Metrics**: Decision count, avg impact score, ministry effectiveness ranking, budget impact  
**Framework**: Decision Intelligence Framework  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH

**Use Cases**:
- Government effectiveness assessment
- Ministry performance comparison  
- Coalition stability monitoring
- Policy impact evaluation

*See [Appendix A.2](#a2-decision-views-v1360) for complete schema*

#### 2. view_riksdagen_party_decision_summary  
**Purpose**: Party-level decision aggregation and effectiveness tracking  
**Key Metrics**: Total decisions, successful decisions, avg impact score, coalition support %  
**Framework**: Comparative Analysis, Temporal Analysis  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH

**Cross-Reference**: Enables D-01 (Party Decision Ineffectiveness) risk rule added in v1.35

#### 3. view_riksdagen_politician_decision_summary
**Purpose**: Individual politician decision-making effectiveness  
**Key Metrics**: Decisions made, success rate, effectiveness score, policy area diversity  
**Framework**: Temporal Analysis, Pattern Recognition  
**Intelligence Value**: ⭐⭐⭐⭐ HIGH

**Cross-Reference**: Enables D-02 (Politician Decision Passivity) risk rule added in v1.35

### 🔴 Risk Rules
No new risk rules in this version (views enable rules added in v1.35)

### 📚 Documentation Enhancements
- Comprehensive JSON spec integration in BUSINESS_PRODUCT_DOCUMENT.md
- Automated view documentation validation
- Mermaid visualizations for 10 intelligence dashboards
- Enhanced cross-referencing between intelligence documents
- DATABASE_VIEW_INTELLIGENCE_CATALOG.md updated: 85 views (57 regular + 28 materialized)
- DATA_ANALYSIS_INTOP_OSINT.md: Enhanced framework descriptions with SQL examples

### 🔒 Security
- Enhanced user permission validation in health check script
- Added SSL enforcement checks for database connections
- Improved audit trail coverage for intelligence operations

---

## [1.35.0] - 2025-11-22

### 🎯 Intelligence Frameworks
**Temporal Analysis Framework** enhancements:
- Enhanced with monthly and annual granularity options
- Added 5-year historical trend analysis
- Improved seasonal adjustment algorithms

**Risk Assessment System** expansion:
- Total rules expanded from 45 to 50
- Added decision-based risk category (5 new rules)
- Enhanced severity calculation methodology

### 🗄️ Database Views (Documentation Enhancement)
**Note**: This release focused on comprehensive documentation of all 85 existing views rather than adding new views

**Documentation Achievements**:
- Documented all 85 views with structured format (purpose, key metrics, sample queries, use cases)
- Added intelligence application descriptions for each view
- Created framework-to-view mappings
- Updated DATABASE_VIEW_INTELLIGENCE_CATALOG.md with complete coverage
- Achieved 100% documentation coverage

### 🔴 Risk Rules (5 Added - Decision Pattern Rules)

**New Category**: Decision-based risk detection complementing behavioral analysis

#### D-01: Party Decision Ineffectiveness
**Detection Pattern**: Low party decision success rate indicating strategic weakness or opposition isolation  
**Thresholds**: CRITICAL < 20%, MAJOR 20-30%, MINOR 30-40%  
**Data Source**: view_riksdagen_party_decision_summary  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH

**Cross-Reference**: Requires view added in v1.36.0

#### D-02: Politician Decision Passivity
**Detection Pattern**: Minimal decision participation indicating disengagement  
**Thresholds**: CRITICAL < 2 decisions/2yrs, MAJOR 2-4, MINOR 5-7  
**Data Source**: view_riksdagen_politician_decision_summary  
**Intelligence Value**: ⭐⭐⭐⭐ HIGH

**Cross-Reference**: Requires view added in v1.36.0

#### D-03: Ministry Budget Overrun
**Detection Pattern**: Consistent budget management failures  
**Thresholds**: CRITICAL 3+ overruns OR >15%, MAJOR 2 overruns + 10-15%, MINOR 2 overruns + 5-10%  
**Data Source**: view_ministry_budget_variance  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH

#### D-04: Coalition Decision Instability
**Detection Pattern**: Frequent coalition vote disagreements indicating government instability  
**Thresholds**: CRITICAL >40% fracture rate, MAJOR 30-40%, MINOR 20-30%  
**Data Source**: view_riksdagen_party_ballot_support_annual_summary  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH

**Cross-Reference**: Uses view added in v1.32.0

#### D-05: Legislative Stagnation
**Detection Pattern**: Declining decision-making pace indicating parliamentary dysfunction  
**Thresholds**: CRITICAL >30% decline YoY, MAJOR 20-30%, MINOR 15-20%  
**Data Source**: decision table (direct query)  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH

*See [Appendix B.1](#b1-decision-pattern-rules-v1350) for complete rule specifications*

### 📊 Intelligence Products (New)
- Party decision effectiveness reports
- Ministry performance scorecards
- Coalition stability dashboards
- Legislative momentum tracking

### ⚡ Performance
- Optimized materialized view refresh schedules
- Improved query performance on decision summary views
- Reduced average dashboard load time by 30%

---

## [1.34.0] - 2025-11-18

### 🗄️ Database Views (3 Added - Temporal Granularity)

#### Temporal Voting Summary Views
- **view_riksdagen_vote_data_ballot_politician_summary_daily** - Daily voting statistics per politician
- **view_riksdagen_vote_data_ballot_politician_summary_monthly** - Monthly voting trends and participation  
- **view_riksdagen_vote_data_ballot_politician_summary_annual** - Annual voting behavior and effectiveness

**Framework**: Temporal Analysis Framework  
**Intelligence Products**: Daily Activity Reports, Monthly Performance Scorecards, Annual Performance Reviews  
**Intelligence Value**: ⭐⭐⭐⭐ HIGH

**Use Cases**: Multi-granularity temporal analysis, trend identification, seasonal pattern detection

### 🎯 OSINT Data Sources (Enhanced)
**Riksdagen API Integration**:
- Improved error handling and retry logic
- Added incremental update support  
- Enhanced data validation

**World Bank Data**:
- Added economic indicator correlation
- Enhanced temporal alignment with political data

### Pattern Recognition Framework (Enhanced)
- Improved anomaly detection algorithms with enhanced statistical thresholds
- Added machine learning-based outlier detection
- Seasonal adjustment capabilities (parliamentary recess, election cycles, holidays)
- Improved false positive reduction

### ⚡ Performance
- Reduced data ingestion time by 40%
- Improved view query performance with new indexes
- Optimized temporal aggregation queries

---

## [1.33.0] - 2025-11-10

### 🔴 Risk Rules (10 Added - Behavioral Detection Enhancement)

**Politician Rules** (5 new):
- **P-16**: Politician Voting Inconsistency - Erratic voting patterns (CRITICAL: variance >35 OR loyalty <60%)
- **P-17**: Politician Question Decline - Decreasing parliamentary engagement (CRITICAL: <1 question/quarter)
- **P-18**: Politician Document Productivity Collapse - Sharp drop in legislative output (CRITICAL: >50% decline)
- **P-19**: Politician Committee Absence Spike - Increased committee non-attendance
- **P-20**: Politician Cross-Party Voting Pattern - Frequent opposition voting

**Party Rules** (2 new):
- **PA-06**: Party Internal Division - Increased vote splitting
- **PA-07**: Party Legislative Stagnation - Declining bill introduction

**Committee Rules** (2 new):
- **C-03**: Committee Decision Backlog - Growing unresolved issues
- **C-04**: Committee Member Turnover - High membership churn

**Ministry Rules** (1 new):
- **M-04**: Ministry Response Delay - Slow parliamentary question responses

**Total Risk Rules**: 45 (from 35)  
*See [Appendix B.2](#b2-behavioral-rules-v1330) for specifications*

### Enhanced Severity Calculation Algorithm
- Introduced weighted scoring based on historical patterns
- Added temporal trend analysis for risk escalation
- Implemented confidence intervals for risk assessments
- Enhanced threshold calibration methodology

### 🤖 Intelligence Agents
**Intelligence Operative Agent** with OSINT expertise:
- Specialized in political analysis and behavioral assessment
- Integrated with all 6 analysis frameworks
- Supports automated intelligence product generation

### 🔒 Security
**pg_audit Extension Implementation**:
- Tracks all data access and modifications
- Enhanced audit trail for intelligence operations
- Supports compliance reporting

**Security Health Monitoring**:
- SSL connection verification
- User permission auditing
- Access control validation

### ⚡ Performance
- Optimized risk rule execution with query improvements
- Reduced rule evaluation time by 50%
- Enhanced caching for frequently accessed risk scores

---

## [1.32.0] - 2025-11-01

### 🎯 Intelligence Frameworks (2 Added)

#### Network Analysis Framework
**Purpose**: Coalition detection and influence mapping  
**Capabilities**:
- Centrality metrics (degree, betweenness, closeness)
- Community detection algorithms
- Influence mapping and power structure visualization

**Intelligence Products**: Politician influence network maps, Coalition stability analysis

#### Predictive Intelligence Framework  
**Purpose**: ML-based forecasting and modeling  
**Capabilities**:
- Time series forecasting for voting behavior
- Coalition formation prediction
- Electoral outcome modeling
- Policy success probability estimation

**Intelligence Products**: Coalition stability forecasting, Predictive electoral risk assessments

### 🗄️ Database Views (2 Added)

#### view_riksdagen_party_ballot_support_annual_summary
**Purpose**: Coalition pattern analysis  
**Key Metrics**: Annual party voting alignment matrix, coalition cohesion metrics  
**Framework**: Network Analysis  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH

**Cross-Reference**: Enables D-04 (Coalition Decision Instability) added in v1.35

#### view_riksdagen_committee_productivity
**Purpose**: Committee effectiveness measurement  
**Key Metrics**: Committee decision throughput, member productivity aggregation  
**Framework**: Comparative Analysis  
**Intelligence Product**: Committee Performance Scorecards

### Data Analysis Infrastructure
- Enhanced statistical analysis capabilities
- Added machine learning model integration framework
- Improved data visualization support
- Enhanced export capabilities for intelligence products

### 🔗 Framework Integration
- Cross-framework data sharing improvements
- Unified analytical query interface
- Enhanced framework interoperability

---

## [1.31.0] - 2025-10-15

### 🔴 Risk Rules (4 Added - Ministry Risk Assessment)

**New Domain**: Ministry & Government oversight (4 rules)

- **M-01**: Ministry Effectiveness Decline - Declining ministry performance metrics
- **M-02**: Ministry Productivity Drop - Significant reduction in ministry output  
- **M-03**: Ministry Risk Escalation - Increasing risk indicators over time
- **M-04**: Ministry Response Delay - Slow parliamentary question responses (added in v1.33)

**Data Sources**: view_ministry_effectiveness_trends, view_ministry_productivity_matrix, view_ministry_risk_evolution  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Critical for government oversight

**Note**: These views had critical bugs fixed in v1.39.0, making rules fully operational

### 🗄️ Database Views (3 Added - Ministry Intelligence)

#### Ministry Tracking Views
- **view_ministry_effectiveness_trends** - Ministry performance over time (FIXED in v1.39)
- **view_ministry_productivity_matrix** - Ministry output comparison (FIXED in v1.39)
- **view_ministry_risk_evolution** - Ministry risk tracking (FIXED in v1.39)

**Framework**: Temporal Analysis, Comparative Analysis  
**Intelligence Product**: Ministry Performance Scorecards

**Cross-Reference**: Enable M-01 through M-03 risk rules (operational after v1.39 fixes)

---

## [1.30.0] - 2025-09-01

### 🔴 Risk Rules (12 Added - Politician Behavioral Analysis)

**Focus**: Comprehensive politician performance and behavior monitoring

**Engagement & Attendance** (4 rules):
- **P-01**: Low Attendance Rate - Chronic absenteeism from parliamentary sessions
- **P-02**: Committee Non-Participation - Minimal committee engagement
- **P-03**: Voting Passivity - Low voting participation rate
- **P-04**: Question Inactivity - Minimal parliamentary question submission

**Productivity & Output** (3 rules):
- **P-05**: Document Productivity Decline - Decreasing legislative document production
- **P-06**: Amendment Inactivity - Low amendment proposal rate
- **P-07**: Motion Stagnation - Minimal motion submission

**Party Alignment** (3 rules):
- **P-08**: Party Disloyalty - Frequent voting against party line
- **P-09**: Coalition Defection - Pattern of voting against coalition
- **P-10**: Opposition Alignment - Persistent voting with opposition

**Performance Patterns** (2 rules):
- **P-11**: Declining Influence - Decreasing impact on legislative outcomes
- **P-12**: Inconsistent Participation - Erratic attendance and engagement patterns

**Total Politician Rules**: 24 (including P-13 to P-20 added in later versions)  
*See [Appendix B.3](#b3-politician-rules-comprehensive) for complete specifications*

### Intelligence Products
- Politician performance scorecards
- Behavioral pattern analysis reports
- Engagement trend dashboards

---

## [1.20.0] - 2024-06-01

### 🎯 Risk Assessment System (Initial Release)

**Framework Introduction**: Drools-based behavioral analysis engine

**Initial Rules** (8 rules):
- **P-13**: Politician Risk Escalation - Multiple concurrent risk indicators
- **P-14**: Politician Activity Anomaly - Sudden changes in activity patterns
- **P-15**: Politician Performance Degradation - Overall performance decline
- **PA-01**: Party Cohesion Breakdown - Internal party voting fragmentation
- **PA-02**: Party Performance Decline - Declining party-wide metrics
- **PA-03**: Party Opposition Pattern - Systematic opposition voting
- **PA-04**: Party Absenteeism - High party-wide absence rates
- **PA-05**: Party Legislative Inactivity - Low bill introduction rates

**Severity System**: Simple 3-tier classification (LOW, MEDIUM, HIGH)  
**Note**: Migrated to numeric system (10-49, 50-99, 100+) in v1.33.0

**Committee Rules** (2 initial):
- **C-01**: Committee Inactivity - Low committee decision volume
- **C-02**: Committee Attendance Issues - High member absence rates

**Application Rules** (3 baseline):
- **APP-01**: Application Performance - System health monitoring
- **U-01**: User Activity Pattern - User engagement tracking  
- **U-02**: User Risk Indicator - User behavior anomalies

**Total Rules**: 13 (initial risk assessment framework)

### Intelligence Products
- Initial risk scorecards
- Automated alert generation
- Behavioral pattern detection

---

## [1.13.0] - 2023-03-15

### 🎯 Pattern Recognition Framework (Initial Release)

**Purpose**: Automated anomaly detection and behavioral pattern analysis

**Capabilities**:
- Statistical anomaly detection
- Behavioral pattern clustering
- Trend deviation identification
- Outlier detection

**Applications**:
- Voting behavior anomalies
- Attendance pattern changes
- Productivity shifts
- Coalition alignment variations

**Intelligence Value**: Foundation for automated risk detection (rules added in v1.20)

---

## [1.10.0] - 2022-06-01

### 🎯 Core Analysis Frameworks (Initial Release)

#### Temporal Analysis Framework
**Purpose**: Time-series analysis and trend identification  
**Capabilities**:
- Historical trend analysis
- Time-based pattern detection
- Seasonal variation analysis
- Longitudinal performance tracking

**Data Granularity**: Daily, monthly, annual (enhanced in later versions)

#### Comparative Analysis Framework
**Purpose**: Cross-entity performance comparison  
**Capabilities**:
- Politician vs politician benchmarking
- Party vs party comparison
- Committee effectiveness ranking
- Performance percentile calculation

**Applications**: Ranking systems, performance scorecards, benchmarking reports

---

## [1.0.0] - 2014-11-01

### 🚀 Initial Release

**Core Platform**:
- Initial database schema with politician, party, vote, and document tracking
- Basic OSINT data ingestion from Riksdagen API
- Foundation views for political data analysis

**Data Sources**:
- Riksdagen (Swedish Parliament) API integration
- Election Authority data feeds
- Basic political entity tracking

**Capabilities**:
- Political actor monitoring
- Vote data collection
- Document tracking
- Basic reporting

**Intelligence Focus**: Data collection and aggregation (analysis frameworks added in later versions)

---

## [Unreleased]

### In Development

**Intelligence Frameworks**:
- Decision Intelligence Framework enhancements (ministry focus)
- Advanced predictive modeling integration

**Database Views**:
- View-to-product mapping improvements
- Intelligence-focused view optimizations

**Risk Rules**:
- Machine learning-enhanced rule calibration
- Real-time risk score calculation

**Documentation**:
- Continuous documentation improvements
- Enhanced cross-referencing between intelligence documents

---

# Appendices

Detailed technical specifications for database views and risk rules referenced throughout this changelog.

---

## Appendix A: Database View Schema Details

Complete DDL specifications and schema details for all database views. This appendix provides detailed schema information to supplement the high-level descriptions in version sections above.

### A.1: Ministry View Fixes (v1.39.0)

#### view_ministry_effectiveness_trends - Complete Fix Details

**Problem History**:
- v1.31.0: Initial implementation with incorrect filter
- v1.32.0: Attempted case-insensitive fix (still failed)
- v1.37.0: Additional debugging attempts (still returned 0 rows)
- v1.39.0: Root cause identified and corrected

**Root Cause Analysis**:
```sql
-- INCORRECT ASSUMPTION:
-- org_code contains 'departement' as part of the value
-- Example: 'KN-departement' or 'departement-KN'

-- ACTUAL DATA:
-- org_code contains SHORT CODES only: 'KN', 'N', 'UD', 'F'
-- Full names appear in separate 'detail' field
```

**Corrected DDL** (simplified excerpt):
```sql
CREATE VIEW view_ministry_effectiveness_trends AS
SELECT 
    org_code AS short_code,  -- Direct use, not substring extraction
    assignment_type,
    COUNT(*) as assignment_count,
    AVG(days_served) as avg_tenure,
    EXTRACT(YEAR FROM from_date) as year
FROM view_riksdagen_politician_document_daily_summary  
WHERE assignment_type = 'Departement'  
  AND org_code IS NOT NULL
  -- Removed: AND LOWER(org_code) LIKE '%departement%'
GROUP BY org_code, assignment_type, EXTRACT(YEAR FROM from_date);
```

**Related Views with Same Fix**:
- view_ministry_productivity_matrix
- view_ministry_risk_evolution

**GitHub Issue**: #8009  
**Liquibase Changelog**: db-changelog-1.39.xml

### A.2: Decision Views (v1.36.0)

#### view_ministry_decision_impact - Complete Schema

```sql
CREATE VIEW view_ministry_decision_impact AS
SELECT 
    m.ministry_id,
    m.ministry_name,
    d.decision_id,
    d.decision_date,
    d.decision_type,
    d.impact_score,
    d.budget_affected,
    d.policy_area,
    COUNT(d.decision_id) OVER (PARTITION BY m.ministry_id) as total_decisions,
    AVG(d.impact_score) OVER (PARTITION BY m.ministry_id) as avg_ministry_impact,
    RANK() OVER (ORDER BY AVG(d.impact_score) OVER (PARTITION BY m.ministry_id) DESC) as ministry_effectiveness_rank
FROM ministry m
LEFT JOIN decision d ON m.ministry_id = d.ministry_id
WHERE d.decision_date >= CURRENT_DATE - INTERVAL '5 years';
```

**Sample Query**:
```sql
-- Top 5 most effective ministries in last year
SELECT 
    ministry_name,
    total_decisions,
    ROUND(avg_ministry_impact, 2) as effectiveness_score,
    ministry_effectiveness_rank
FROM view_ministry_decision_impact
WHERE decision_date >= CURRENT_DATE - INTERVAL '1 year'
ORDER BY ministry_effectiveness_rank
LIMIT 5;
```

#### view_riksdagen_party_decision_summary - Complete Schema

```sql
CREATE VIEW view_riksdagen_party_decision_summary AS
SELECT 
    p.party,
    EXTRACT(YEAR FROM d.decision_date) as decision_year,
    COUNT(DISTINCT d.decision_id) as total_decisions,
    COUNT(DISTINCT CASE WHEN d.outcome = 'PASSED' THEN d.decision_id END) as successful_decisions,
    AVG(d.impact_score) as avg_impact_score,
    AVG(d.coalition_support_percent) as avg_coalition_support,
    STDDEV(d.impact_score) as impact_score_variance
FROM party p
JOIN politician pol ON p.party = pol.party
JOIN decision d ON pol.person_id = d.proposer_id
GROUP BY p.party, EXTRACT(YEAR FROM d.decision_date);
```

**Sample Query**:
```sql
-- Party effectiveness ranking for 2024
SELECT 
    party,
    total_decisions,
    successful_decisions,
    ROUND(100.0 * successful_decisions / NULLIF(total_decisions, 0), 1) as success_rate_pct,
    ROUND(avg_impact_score, 2) as effectiveness_score
FROM view_riksdagen_party_decision_summary
WHERE decision_year = 2024
ORDER BY success_rate_pct DESC, total_decisions DESC;
```

### Additional View Documentation

**Complete Catalog**: For comprehensive documentation of all 84+ views, see [DATABASE_VIEW_INTELLIGENCE_CATALOG.md](DATABASE_VIEW_INTELLIGENCE_CATALOG.md)

**Original Changelog**: For complete historical view schema details, see archived [CHANGELOG_DATABASE_VIEWS.md](docs/archive/CHANGELOG_DATABASE_VIEWS.md)

---

## Appendix B: Risk Rule Specifications

Detailed specifications for all behavioral detection and risk assessment rules. This appendix provides technical details to supplement the high-level rule descriptions in version sections above.

### B.1: Decision Pattern Rules (v1.35.0)

#### D-01: Party Decision Ineffectiveness - Complete Specification

**Rule ID**: D-01  
**Category**: Strategic Performance  
**Domain**: Party  
**Added**: v1.35.0

**Detection Logic**:
```sql
SELECT party, 
       COUNT(*) as total_decisions,
       COUNT(CASE WHEN outcome = 'PASSED' THEN 1 END) as passed_decisions,
       ROUND(100.0 * COUNT(CASE WHEN outcome = 'PASSED' THEN 1 END) / COUNT(*), 1) as success_rate
FROM view_riksdagen_party_decision_summary
WHERE decision_year >= EXTRACT(YEAR FROM CURRENT_DATE) - 2
GROUP BY party
HAVING ROUND(100.0 * COUNT(CASE WHEN outcome = 'PASSED' THEN 1 END) / COUNT(*), 1) < 30
   AND COUNT(*) >= 10;
```

**Threshold Matrix**:
| Success Rate | Severity | Salience Score | Confidence |
|--------------|----------|----------------|------------|
| < 20% | CRITICAL | 100+ | High |
| 20-30% | MAJOR | 70 | Medium |
| 30-40% | MINOR | 40 | Medium |

**Data Source**: view_riksdagen_party_decision_summary (added v1.36.0)  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH  
**Historical False Positive Rate**: 5%

**Intelligence Context**:
- Indicates strategic weakness or opposition isolation
- May reflect coalition marginalization
- Can predict electoral vulnerability
- Supports party effectiveness scorecards

#### D-02: Politician Decision Passivity - Complete Specification

**Rule ID**: D-02  
**Category**: Engagement Pattern  
**Domain**: Politician  
**Added**: v1.35.0

**Detection Logic**:
```sql
SELECT person_id, politician_name, party,
       decisions_made,
       EXTRACT(YEAR FROM CURRENT_DATE) - MIN(EXTRACT(YEAR FROM decision_date)) as years_active
FROM view_riksdagen_politician_decision_summary
WHERE decision_year >= EXTRACT(YEAR FROM CURRENT_DATE) - 2
GROUP BY person_id, politician_name, party
HAVING decisions_made < 5 
   AND EXTRACT(YEAR FROM CURRENT_DATE) - MIN(EXTRACT(YEAR FROM decision_date)) >= 2;
```

**Threshold Matrix**:
| Decisions (2 years) | Severity | Salience Score | Confidence |
|---------------------|----------|----------------|------------|
| < 2 | CRITICAL | 90 | High |
| 2-4 | MAJOR | 60 | Medium |
| 5-7 | MINOR | 30 | Low |

**Data Source**: view_riksdagen_politician_decision_summary (added v1.36.0)  
**Intelligence Value**: ⭐⭐⭐⭐ HIGH  
**Historical False Positive Rate**: 10%

**Intelligence Context**:
- Minimal participation indicates disengagement or strategic positioning
- May reflect backbencher status or committee specialization
- Can indicate career transition or health issues
- Supports politician effectiveness scorecards

### B.2: Behavioral Rules (v1.33.0)

#### P-16: Politician Voting Inconsistency - Complete Specification

**Rule ID**: P-16  
**Category**: Voting Behavior  
**Domain**: Politician  
**Added**: v1.33.0

**Detection Logic**:
```sql
SELECT person_id, politician_name,
       STDDEV(vote_consistency_score) as voting_variance,
       AVG(party_alignment_pct) as party_loyalty
FROM view_politician_voting_consistency
WHERE analysis_period >= CURRENT_DATE - INTERVAL '6 months'
GROUP BY person_id, politician_name
HAVING STDDEV(vote_consistency_score) > 25 
   OR AVG(party_alignment_pct) < 70;
```

**Threshold Matrix**:
| Metric | Severity | Salience Score |
|--------|----------|----------------|
| Variance > 35 OR Loyalty < 60% | CRITICAL | 95 |
| Variance 25-35 OR Loyalty 60-70% | MAJOR | 65 |

**Data Source**: view_politician_voting_consistency  
**Intelligence Value**: ⭐⭐⭐⭐ HIGH  
**Intelligence Context**: Erratic patterns may indicate strategic confusion, external influence, or ideological evolution

### B.3: Politician Rules Comprehensive (v1.30.0)

Complete specifications for P-01 through P-15 rules:

**Engagement & Attendance** (P-01 to P-04):
- Low attendance, committee non-participation, voting passivity, question inactivity
- Thresholds: CRITICAL < 60% participation, MAJOR 60-75%, MINOR 75-85%
- Data Sources: attendance tables, voting records, parliamentary question logs

**Productivity & Output** (P-05 to P-07):
- Document productivity decline, amendment inactivity, motion stagnation
- Thresholds: Based on rolling 12-month averages with >30% decline triggers
- Data Sources: document production logs, amendment records, motion tracking

**Party Alignment** (P-08 to P-10):
- Party disloyalty, coalition defection, opposition alignment
- Thresholds: CRITICAL > 40% deviation from party line
- Data Sources: voting alignment views, coalition cohesion metrics

**Performance Patterns** (P-11 to P-15):
- Declining influence, inconsistent participation, risk escalation, activity anomaly, performance degradation
- Complex multi-metric analysis combining attendance, productivity, and voting data
- Data Sources: Multiple view aggregations with weighted scoring

### Severity Classification System

**Current System** (v1.33.0 - Present):

**3-Tier Numeric Classification**:
- 🟡 **MINOR** (Salience 10-49): Early indicators, trend monitoring, preventive intelligence
- 🟠 **MAJOR** (Salience 50-99): Established patterns, accountability concerns, tactical intelligence
- 🔴 **CRITICAL** (Salience 100+): Severe risks, democratic accountability failure, strategic intelligence

**Legacy System** (v1.20.0 - v1.32.0):
- Simple 3-tier: LOW, MEDIUM, HIGH (no numeric ranges)
- Migration completed in v1.33.0 with backward-compatible severity levels

### Additional Rule Documentation

**Complete Catalog**: For comprehensive rule documentation with intelligence context, see [RISK_RULES_INTOP_OSINT.md](RISK_RULES_INTOP_OSINT.md)

**Original Changelog**: For complete historical rule specifications, see archived [CHANGELOG_RISK_RULES.md](docs/archive/CHANGELOG_RISK_RULES.md)

---

## Document Metadata

**Consolidation Information**:

**Original Source Documents**:
- CHANGELOG_INTELLIGENCE_ANALYSIS.md (713 lines) - Intelligence frameworks, OSINT enhancements, high-level changes
- CHANGELOG_DATABASE_VIEWS.md (823 lines) - Detailed database view schemas, DDL specifications
- CHANGELOG_RISK_RULES.md (925 lines) - Risk rule evolution, detection logic, threshold specifications

**Consolidation Details**:
- **Total Original Content**: 2,461 lines across 3 files (~80KB)
- **Consolidation Date**: 2025-12-10
- **Consolidation Method**: Hierarchical merge by version with categorization (Frameworks → Views → Rules)
- **Information Loss**: None - all unique content preserved
- **Cross-References**: Enhanced with inter-version and inter-category links
- **Appendices**: Detailed schemas and rule specifications moved to appendices

**Archive Location**: Original files preserved at [docs/archive/](docs/archive/)

**Related Documentation**:
- [DATA_ANALYSIS_INTOP_OSINT.md](DATA_ANALYSIS_INTOP_OSINT.md) - Analysis frameworks and methodologies
- [RISK_RULES_INTOP_OSINT.md](RISK_RULES_INTOP_OSINT.md) - Complete risk rule catalog
- [DATABASE_VIEW_INTELLIGENCE_CATALOG.md](DATABASE_VIEW_INTELLIGENCE_CATALOG.md) - Comprehensive view documentation
- [INTELLIGENCE_DATA_FLOW.md](INTELLIGENCE_DATA_FLOW.md) - Data pipeline and framework mappings

**Maintained By**: Citizen Intelligence Agency Intelligence Operations Team  
**Document Type**: Unified Intelligence Changelog  
**Status**: Active - Single Source of Truth for Intelligence Evolution  
**Format**: [Keep a Changelog](https://keepachangelog.com/)

---

*This unified changelog eliminates redundancy while maintaining comprehensive tracking of intelligence capabilities, database views, and risk rules across all CIA platform releases.*

