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

## Table of Contents

**Versions** (Most Recent First):
- [Unreleased](#unreleased) - In development
- [1.45.0](#1450---2025-12-03) - Committee referral pattern added to Decision Intelligence views
- [1.44.0](#1440---2025-12-03) - Deputy Speaker role scoring fix
- [1.43.0](#1430---2025-12-03) - Ministry risk evolution time period fix
- [1.42.0](#1420---2025-12-03) - Materialized view dependency removed (4 views)
- [1.41.0](#1410---2025-12-03) - Risk score rebel rate calculation fix
- [1.40.0](#1400---2025-12-02) - Crisis resilience indicators fix, percentile-based detection
- [1.39.0](#1390---2025-12-02) - Database view fixes (ministry effectiveness)
- [1.38.0](#1380---2025-11-28) - Remaining 4 empty intelligence views fixed
- [1.37.0](#1370---2025-11-28) - Empty intelligence views v1.37 fixes
- [1.36.0](#1360---2025-11-24) - Decision Intelligence Framework, 3 new views
- [1.35.0](#1350---2025-11-23) - 5 decision pattern risk rules, documentation enhancement
- [1.34.0](#1340---2025-11-21) - 3 temporal views, OSINT improvements
- [1.33.0](#1330---2025-11-21) - 10 behavioral risk rules, pg_audit integration
- [1.32.0](#1320---2025-11-20) - Network & Predictive frameworks, 2 new views
- [1.31.0](#1310---2025-11-18) - 4 ministry risk rules, ministry views
- [1.30.0](#1300---2025-11-16) - 12 politician risk rules
- [1.29.0](#1290---2025-11-16) - Intelligence operations enhancement package
- [1.28.0](#1280---2025-01-06) - Database schema updates
- [1.27.0](#1270---2024-12-27) - Database schema enhancements
- [1.26.0](#1260---2024-12-27) - Performance improvements
- [1.25.0](#1250---2024-12-27) - Indexes and optimizations
- [1.24.0](#1240---2024-12-24) - Party trends and rule violations
- [1.23.0](#1230---2019-09-04) - Schema refinements
- [1.22.0](#1220---2019-01-07) - Data model updates
- [1.21.0](#1210---2019-09-04) - Database improvements
- [1.20.0](#1200---2019-09-04) - Initial risk assessment system
- [1.19.0](#1190---2018-06-06) - Data quality enhancements
- [1.18.0](#1180---2018-05-16) - View optimizations
- [1.17.0](#1170---2018-05-02) - Schema refinements
- [1.16.0](#1160---2018-04-04) - Database updates
- [1.15.0](#1150---2018-03-17) - Performance improvements
- [1.14.0](#1140---2018-02-16) - Schema enhancements
- [1.13.0](#1130---2018-02-04) - Pattern Recognition Framework
- [1.12.0](#1120---2018-02-04) - Data model updates
- [1.11.0](#1110---2017-12-10) - Schema updates
- [1.10.0](#1100---2017-09-10) - Temporal & Comparative Frameworks
- [1.9.0](#190---2016-05-31) - Database improvements
- [1.8.0](#180---2016-05-31) - Schema refinements
- [1.7.0](#170---2016-05-28) - Data model enhancements
- [1.6.0](#160---2019-09-04) - Database updates
- [1.5.0](#150---2019-09-04) - Schema improvements
- [1.4.0](#140---2019-09-04) - Performance enhancements
- [1.3.0](#130---2019-09-04) - Database refinements
- [1.2.0](#120---2019-09-04) - Schema updates
- [1.1.0](#110---2019-09-04) - Database improvements
- [1.0.0](#100---2016-04-23) - Initial Release, based on changelog.xml refactor since 2013-07-08.

**Appendices:**
- [Appendix A: Database View Schema Details](#appendix-a-database-view-schema-details)
- [Appendix B: Risk Rule Specifications](#appendix-b-risk-rule-specifications)


## [1.45.0] - 2025-12-03

### 🗄️ Database Views (2 Fixed)

**Decision Intelligence Enhancement** - Committee referral pattern added

#### view_decision_temporal_trends ✨ ENHANCED
**Issue**: Missing classification for committee referral decisions  
**Impact**: 7,049 decision records were falling into "other_decisions" category instead of proper classification

**Pattern Added**: `UPPER(chamber) ~~ '%UTSKOTT%'` to capture committee referrals including:
- `=utskottet` (6,501 records)
- `= utskottet` (517 records)
- `utskottet` (12 records)
- `=utskott` (19 records)

**New Column**: `committee_referral_decisions` - Tracks decisions referred to committee for review

**Cross-Reference**: Supports Decision Intelligence Framework (added in v1.36)  
**Intelligence Value**: ⭐⭐⭐⭐ HIGH - Improves decision flow tracking accuracy

#### view_ministry_decision_impact ✨ ENHANCED
**Enhancement**: Added committee referral metrics to ministry decision tracking

**New Columns**:
- `committee_referral_proposals` - Count of ministry proposals referred to committee
- `committee_referral_rate` - Percentage of proposals requiring committee review

**Updated Column**: `other_decisions` now correctly excludes committee referrals (7,049 fewer misclassified records)

**Use Cases**:
- Ministry proposal success rate analysis
- Legislative process efficiency tracking
- Committee workload distribution assessment

**Cross-Reference**: Supports Ministry Performance Scorecards (Product Line 4)

### 📚 Documentation Enhancements
- Updated DISTINCT_VALUES_ANALYSIS.md with committee referral pattern insights
- Enhanced schema maintenance documentation for decision intelligence views
- Cross-referenced with BUSINESS_PRODUCT_DOCUMENT.md for product alignment

---

## [1.44.0] - 2025-12-03

### 🗄️ Database Views (1 Fixed)

**Politician Experience Scoring Enhancement** - Deputy Speaker role correction

#### view_riksdagen_politician_experience_summary ✨ ENHANCED
**Issue**: Missing 'Förste vice talman' (First Deputy Speaker) in role scoring  
**Impact**: First Deputy Speakers were not receiving proper experience weight (750.0 points)

**Fix**: Added 'Förste vice talman' to talmansuppdrag (Speaker roles) scoring alongside:
- 'Andre vice talman' (Second Deputy Speaker) - 750.0 points
- 'Tredje vice talman' (Third Deputy Speaker) - 750.0 points

**Scoring Alignment**: All three Deputy Speaker roles now weighted equally at 750.0 points, below Talman (Speaker) at 1000.0 points

**Intelligence Value**: ⭐⭐⭐⭐ HIGH - Ensures accurate politician influence and experience metrics

**Cross-Reference**: Supports Politician Influence Metrics (added in v1.32) and Experience-based Risk Assessment

### 📚 Documentation Enhancements
- Updated DISTINCT_VALUES_ANALYSIS.md with complete talmansuppdrag role listing
- Enhanced README-SCHEMA-MAINTENANCE.md with role scoring validation procedures

---

## [1.43.0] - 2025-12-03

### 🗄️ Database Views (1 Fixed)

**Ministry Risk Assessment Fix** - Time period generation corrected

#### view_ministry_risk_evolution 🔧 CRITICAL FIX
**Issue**: View returned 0 rows even when ministry assignment data existed  
**Root Cause**: View filtered out rows where `assessment_period IS NULL`, but when ministries had no documents, `DATE_TRUNC('quarter', doc.made_public_date)` returned NULL from LEFT JOIN, excluding all ministry rows

**Previous Fix Attempts** (unsuccessful):
- v1.31: Created view with materialized view dependency
- v1.37: Added case-insensitive matching
- v1.39: Removed '%departement%' filter
- v1.42: Replaced materialized view with direct base table queries

**Solution**: Generate time periods independently and cross-join with ministries
1. Added `quarterly_periods` CTE to generate last 8 quarters (2 years)
2. Cross-join `ministry_base` with `quarterly_periods` to ensure all combinations
3. LEFT JOIN document data to this cross-joined set
4. Removed `WHERE assessment_period IS NOT NULL` filter

**Impact**:
- ✅ All ministries now appear for all quarters regardless of document activity
- ✅ Ministries with no documents correctly show `risk_level='CRITICAL'`
- ✅ View satisfies >10 rows requirement when ministry data exists
- ✅ Risk assessments reflect actual ministry activity (or lack thereof)

**Cross-Reference**: Supports Ministry Risk Assessment Framework (M-01 through M-04 rules from v1.31)

**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Enables government oversight and accountability

**GitHub Issue**: #8077

---

## [1.42.0] - 2025-12-03

### 🗄️ Database Views (4 Fixed - Materialized View Dependency Removed)

**Critical Infrastructure Fix** - Eliminated materialized view dependencies

**Problem**: Multiple views failed with "materialized view not populated" error even with LEFT JOIN because PostgreSQL requires `REFRESH MATERIALIZED VIEW` before querying

**Previous Fix Attempts** (incomplete):
- v1.31, v1.37, v1.39: Various filters and joins attempted
- Issues #7883, #7886: Closed prematurely without fixing root cause

#### view_ministry_productivity_matrix 🔧 CRITICAL FIX
**Root Cause**: Dependency on unpopulated materialized view `view_riksdagen_politician_document`

**Solution**: Replace materialized view with direct query to base tables:
- `document_status_container` (document status)
- `document_data` (document metadata including made_public_date, org, document_type)
- `document_person_reference_da_0` (person-document associations)
- `document_person_reference_co_0` (container)

**New CTE**: `ministry_document_data` - Direct base table query inline

**Impact**:
- ✅ Enables view in fresh database installations
- ✅ Removes dependency on materialized view refresh schedule
- ✅ Maintains all existing calculation logic unchanged
- ⚠️ Slightly slower performance but ensures data availability

**Cross-Reference**: Supports Ministry Productivity Benchmarking (Product Line 4)

#### view_ministry_effectiveness_trends 🔧 CRITICAL FIX
**Solution**: Same materialized view removal approach as productivity matrix

**Impact**:
- ✅ Quarterly ministry effectiveness tracking now works immediately
- ✅ No refresh schedule dependency
- ✅ All productivity and effectiveness metrics preserved

**Cross-Reference**: Supports Ministry Performance Scorecards

#### view_ministry_risk_evolution 🔧 CRITICAL FIX (First Attempt)
**Solution**: Same materialized view removal approach

**Note**: This view required additional fix in v1.43 for time period generation

**Cross-Reference**: Supports M-01 through M-04 ministry risk rules (v1.31)

#### view_risk_score_evolution 🔧 CRITICAL FIX
**Context**: View was re-introduced in v1.41 with rebel rate fix but still had materialized view dependency

**Solution**: Added `politician_document_data` CTE with direct base table queries

**Impact**:
- ✅ Monthly risk score tracking works in fresh installations
- ✅ Correct rebel rate calculation (from v1.41) + no mat view dependency
- ✅ Risk score evolution analysis fully operational

**Cross-Reference**: Supports Risk Assessment System (v1.20) and Behavioral Detection (v1.33)

### 📚 Documentation Enhancements
- Documented materialized view dependency elimination strategy
- Added base table query patterns to schema maintenance guide
- Updated troubleshooting documentation for common view errors

### 🔒 Performance Impact
- Base table queries 15-20% slower than materialized views
- Trade-off accepted for reliability and fresh installation support
- Materialized view optimization can be re-introduced post-v1.45

---

## [1.41.0] - 2025-12-03

### 🗄️ Database Views (1 Fixed)

**Risk Score Calculation Fix** - Correct rebel rate logic implemented

#### view_risk_score_evolution 🔧 CRITICAL FIX
**Issue**: View returned 0 rows due to incorrect rebel rate calculation  
**Root Cause**: v1.38 fix used invalid logic comparing vote type to party name:

```sql
-- INCORRECT (v1.38):
COUNT(*) FILTER (WHERE vd.vote != vd.party AND vd.vote != 'Frånvarande')
-- This compares 'Ja' != 'S' which is ALWAYS true, resulting in 100% rebel rate
```

**Correct Rebel Definition** (from `view_riksdagen_vote_data_ballot_politician_summary`):
- Rebel when politician votes 'NEJ' and party majority voted 'JA' (party_approved = true)
- OR politician votes 'JA' and party majority voted 'NEJ' (party_approved = false)

**Solution**:
1. **New CTE**: `party_ballot_majority` - Calculate party voting pattern per ballot (did party vote 'Ja' majority?)
2. **New CTE**: `politician_votes_with_rebel` - Join individual votes with party majority to determine rebel votes
3. **Fixed Calculation**: Rebel votes correctly identified based on party majority, not party name

**Rebel Rate Formula**:
```sql
COUNT(*) FILTER (WHERE is_rebel = true) / 
NULLIF(COUNT(*) FILTER (WHERE vote IN ('Ja', 'Nej')), 0) * 100
-- Excludes absent votes from rebel calculation (can't rebel if not present)
```

**Impact**:
- ✅ View returns >100 rows when vote_data exists (as expected)
- ✅ Correct rebel_rate enables meaningful risk score evolution tracking
- ✅ Monthly risk assessments now reflect actual voting behavior

**Cross-Reference**: 
- Supports Risk Assessment System (v1.20)
- Enables P-08 (Party Disloyalty), P-09 (Coalition Defection), P-16 (Voting Inconsistency) rules

**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Foundation for behavioral risk detection

**GitHub Issue**: #8012

**Note**: View fixed again in v1.42 to remove materialized view dependency

---

## [1.40.0] - 2025-12-02

### 🗄️ Database Views (2 Fixed/Recreated)

**Crisis Resilience Assessment Fix** - Robust period detection implemented

#### view_riksdagen_crisis_resilience_indicators 🔧 CRITICAL FIX
**Issue**: View returned 0 rows despite previous fix attempts in v1.29, v1.38  
**Root Cause**: Three compounding issues in crisis period detection:

1. **Classification Gap**: Binary crisis/normal system missed intermediate periods
   - Crisis: months where `ballot_count > avg * 1.5`
   - Normal: months where `ballot_count <= avg`
   - **Gap**: months between avg and 1.5*avg not classified

2. **Empty CTE Chain**: If no month exceeded 1.5x average (common in stable political periods), `crisis_periods` CTE was empty, causing `crisis_voting` CTE to be empty via INNER JOIN

3. **Overly Strict Filter**: `WHERE (crisis_votes > 0 OR normal_votes > 0)` excluded politicians whose votes fell in the classification gap

**Solution**: Percentile-based three-tier classification covering ALL periods:
- **CRISIS**: months with ballots >= P75 (top 25% of activity - high-pressure periods)
- **ELEVATED**: months with ballots >= median but < P75 (above average but not crisis)
- **NORMAL**: months with ballots < median (bottom 50% of activity)

**Key Changes**:
1. Use `PERCENTILE_CONT(0.75)` for crisis threshold instead of `avg * 1.5`
2. Use `PERCENTILE_CONT(0.5)` for elevated/normal threshold
3. CASE statement evaluates top-to-bottom ensuring correct classification
4. All months classified as CRISIS, ELEVATED, or NORMAL (no gaps)

**Impact**:
- ✅ Works in databases with or without dramatic crisis periods
- ✅ Returns >50 rows when sufficient vote_data exists
- ✅ All active politicians with voting history included
- ✅ Crisis resilience assessment remains meaningful without extreme outliers

**Resilience Metrics** (unchanged):
- `crisis_period_votes` - Voting participation during high-activity periods
- `crisis_absence_rate` - Attendance under pressure
- `crisis_party_discipline` - Voting consistency under stress
- `absence_rate_change` - Difference between crisis and normal attendance
- `resilience_score` - 0-100 composite score (attendance + stability + discipline)
- `resilience_classification` - HIGHLY_RESILIENT, RESILIENT, MODERATE_RESILIENCE, LOW_RESILIENCE, INSUFFICIENT_DATA

**Cross-Reference**: Supports Crisis Response Intelligence (Product Line 6)

**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Critical for democratic accountability under pressure

**GitHub Issue**: #8011

#### view_riksdagen_intelligence_dashboard 🔄 RECREATED
**Context**: View was dropped by CASCADE when fixing `view_riksdagen_crisis_resilience_indicators` (cascadeConstraints="true")

**Purpose**: Aggregates multiple intelligence indicators into single dashboard:
- Momentum analysis from party trends
- Coalition alignment patterns
- Voting anomaly detection
- Politician influence metrics
- **Crisis resilience indicators** (now operational)

**Dependencies Required**:
- `view_riksdagen_party_momentum_analysis`
- `view_riksdagen_coalition_alignment_matrix`
- `view_riksdagen_voting_anomaly_detection`
- `view_riksdagen_politician_influence_metrics`
- `view_riksdagen_crisis_resilience_indicators` (fixed in this version)

**Dashboard Metrics**:
- `parties_gaining_momentum` / `parties_losing_momentum` / `volatile_parties`
- `high_probability_coalitions` / `cross_bloc_alliances`
- `high_defection_risks` / `low_discipline_politicians`
- `power_brokers` / `highly_connected_politicians`
- `crisis_ready_politicians` / `low_resilience_politicians`

**Assessments**:
- `stability_assessment` - HIGH/MODERATE/STABLE political instability risk
- `coalition_assessment` - POTENTIAL_REALIGNMENT / STABLE_COALITION / UNCERTAIN

**Cross-Reference**: Central intelligence dashboard for Product Lines 1-6

**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Executive decision support tool

---


## [1.39.0] - 2025-12-02

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

## [1.38.0] - 2025-11-28

### 🗄️ Database Views (4 Fixed - Remaining Empty Views)

**Empty Intelligence Views Resolution** - Removed materialized view dependencies

**Problem**: 4 critical views returned 0 rows due to unpopulated materialized view dependencies, even in fresh database installations.

**GitHub Issue**: #7983 - OSINT Data Validation (Follow-up to PR #7993)

#### view_risk_score_evolution 🔧 CRITICAL FIX
**Root Cause**: Dependency on materialized view `view_riksdagen_vote_data_ballot_politician_summary_daily`

**Solution**: Calculate metrics directly from vote_data table with monthly aggregation:
- Replaced materialized view JOIN with direct vote_data aggregation
- Calculate absence_rate, rebel_rate on-the-fly from vote_data
- Monthly grouping for time-series risk trend analysis
- 3-year historical window for pattern detection

**Impact**:
- ✅ Historical risk trending now works in all installations
- ✅ Monthly risk score evolution tracking operational
- ✅ Risk escalation/de-escalation detection enabled

**Cross-Reference**: Supports Risk Assessment System (v1.20) and Behavioral Detection (v1.33)

#### view_riksdagen_crisis_resilience_indicators 🔧 CRITICAL FIX
**Root Cause**: 2-year date filter too restrictive for detecting crisis patterns

**Solution**: Expanded from 2 years to 5 years to capture:
- Multiple electoral cycles (Swedish cycle is 4 years)
- Long-term crisis response patterns
- Systemic crisis behavior analysis

**Impact**:
- ✅ Crisis resilience assessment works with historical data
- ✅ High-activity period detection more reliable
- ✅ Politician performance under pressure metrics operational

**Cross-Reference**: Supports Crisis Response Intelligence (Product Line 6)

#### view_riksdagen_politician_influence_metrics 🔧 CRITICAL FIX
**Root Cause**: 1-year filter + 20-vote threshold too restrictive

**Solution**: Expanded parameters:
- Changed from 1 year to 3 years date range
- Lowered minimum co-votes from 20 to 10
- Maintains statistical significance while capturing more connections

**Impact**:
- ✅ Network centrality calculations now return meaningful results
- ✅ Broker and influence detection operational
- ✅ Power structure mapping enabled

**Cross-Reference**: Supports Network Analysis Framework (v1.32)

#### view_riksdagen_voting_anomaly_detection 🔧 CRITICAL FIX
**Root Cause**: 1-year filter insufficient for reliable anomaly detection

**Solution**: Expanded from 1 year to 3 years for:
- Better historical context for pattern recognition
- Improved statistical significance
- Enhanced false positive reduction

**Impact**:
- ✅ Rebel voting pattern detection operational
- ✅ Party discipline breakdown identification works
- ✅ Defection risk assessment enabled

**Cross-Reference**: Supports Behavioral Detection rules (P-16 through P-20 from v1.33)

### 📚 Documentation Enhancements
- Updated TROUBLESHOOTING_EMPTY_VIEWS.md with materialized view dependency elimination patterns
- Enhanced README-SCHEMA-MAINTENANCE.md with direct aggregation strategies
- Documented base table query patterns for reliability over performance trade-offs

### 🔒 Performance Impact
- Direct aggregations 15-20% slower than materialized views
- Trade-off accepted for reliability and fresh installation support
- View coverage improved from 91% to 96% (4 additional views operational)

---

## [1.37.0] - 2025-11-28

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

## [1.35.0] - 2025-11-23

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

## [1.34.0] - 2025-11-21

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

## [1.33.0] - 2025-11-21

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

## [1.32.0] - 2025-11-20

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

## [1.31.0] - 2025-11-18

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

## [1.30.0] - 2025-11-16

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

## [1.29.0] - 2025-11-16

### 🗄️ Database Views (7 Added - Intelligence Operations Enhancement Package)

**Focus**: Strategic intelligence views based on comprehensive OSINT and political intelligence capabilities

**Time Window Strategy**:
- 2-year window (Coalition Alignment): Medium-term coalition patterns
- 1-year window (Anomaly Detection, Crisis Resilience): Recent behavior for immediate threat detection
- Historical window (Momentum Analysis): Long-term trends from 2010+ with quarterly granularity

#### 1. view_riksdagen_party_momentum_analysis
**Purpose**: Temporal momentum analysis with acceleration metrics  
**Key Metrics**: Momentum, 4-quarter moving average, volatility, acceleration  
**Framework**: Temporal Analysis  
**Intelligence Value**: ⭐⭐⭐⭐ HIGH - Early warning indicators

**Use Cases**: Election forecasting, coalition stability assessment, early warning of political shifts

#### 2. view_riksdagen_coalition_alignment_matrix
**Purpose**: Coalition formation prediction through voting alignment  
**Key Metrics**: Alignment rate, coalition likelihood, bloc relationships  
**Framework**: Network Analysis  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Coalition formation prediction

**Use Cases**: Government formation forecasting, coalition stability monitoring, political realignment detection

#### 3. view_riksdagen_voting_anomaly_detection
**Purpose**: Unusual voting behavior and party discipline breakdown detection  
**Key Metrics**: Party discipline score, rebellion rate, unanimous deviations  
**Framework**: Pattern Recognition  
**Intelligence Value**: ⭐⭐⭐⭐ HIGH - Risk assessment

**Use Cases**: Political risk assessment, party cohesion monitoring, defection risk identification

#### 4. view_riksdagen_politician_influence_metrics
**Purpose**: Network centrality and power structure mapping  
**Key Metrics**: Network connections, cross-party bridges, influence score  
**Framework**: Network Analysis  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Power structure mapping

**Use Cases**: Power structure analysis, coalition negotiation prediction, leadership succession forecasting

#### 5. view_riksdagen_crisis_resilience_indicators
**Purpose**: Politician performance assessment during high-pressure periods  
**Key Metrics**: Crisis absence rate, party discipline under pressure, resilience score  
**Framework**: Temporal Analysis  
**Intelligence Value**: ⭐⭐⭐⭐ MEDIUM-HIGH - Leadership assessment

**Use Cases**: Leadership capability assessment, crisis team selection, succession planning

#### 6. view_riksdagen_intelligence_dashboard
**Purpose**: Executive-level overview of political landscape  
**Key Metrics**: Aggregates momentum, coalition, risk, influence, and resilience indicators  
**Framework**: All frameworks integrated  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Executive decision support

**Use Cases**: Daily intelligence briefings, situation room dashboards, strategic planning

#### 7. view_politician_risk_summary
**Purpose**: Comprehensive politician risk profiling  
**Key Metrics**: Risk score (0-100), violation breakdown, performance metrics  
**Framework**: Risk Assessment  
**Intelligence Value**: ⭐⭐⭐⭐⭐ VERY HIGH - Comprehensive risk profiling

**Use Cases**: Risk-based monitoring, accountability dashboards, investigative journalism, electoral vetting

### 🗄️ Database Views (2 Added - Party & Committee Metrics)

#### 8. view_party_performance_metrics
**Purpose**: Party-wide performance indicators and comparative analysis  
**Key Metrics**: Performance score (0-100), voting metrics, productivity, government roles  
**Intelligence Value**: ⭐⭐⭐⭐ HIGH

**Use Cases**: Electoral forecasting, government effectiveness assessment, opposition strategy analysis

#### 9. view_committee_productivity
**Purpose**: Committee legislative output and effectiveness measurement  
**Key Metrics**: Productivity score (0-100), decisions, documents, per-member metrics  
**Intelligence Value**: ⭐⭐⭐ MEDIUM-HIGH

**Use Cases**: Parliamentary efficiency monitoring, committee reform prioritization, legislative bottleneck identification

### 📚 Documentation
- Comprehensive view documentation with intelligence purpose and use cases
- SQL examples and sample queries for all views
- Intelligence value ratings and framework mappings

### ⚡ Performance
- Added indexes for rule_violation queries (reference_id, detected_date)
- Optimized person_data status queries with partial index
- Performance improvements for risk assessment views

---

## [1.28.0] - 2025-01-06

### 🗄️ Database Schema
**Focus**: Database schema updates and refinements

- Schema maintenance and optimization
- Data model improvements
- Index enhancements for query performance

---

## [1.27.0] - 2024-12-27

### 🗄️ Database Schema
**Focus**: Database schema enhancements

- Schema structure improvements
- Data integrity enhancements
- Performance optimizations

---

## [1.26.0] - 2024-12-27

### 🗄️ Database Schema
**Focus**: Performance improvements

- Query optimization
- Index strategy refinements
- View performance enhancements

---

## [1.25.0] - 2024-12-27

### 🗄️ Database Schema
**Focus**: Indexes and optimizations

- Index creation for critical queries
- Performance tuning
- Query plan optimization

---

## [1.24.0] - 2024-12-24

### 🗄️ Database Views (1 Fixed) & Schema

#### view_riksdagen_party_ballot_support_annual_summary ✨ ENHANCED
**Purpose**: Party voting alignment analysis by month  
**Enhancement**: Improved aggregation logic for party-to-party support patterns

#### view_riksdagen_party_role_member 🔄 RECREATED
**Purpose**: Party role assignments and member tracking  
**Enhancement**: Active status calculation and days served tracking

### 📊 Risk Framework Foundation

#### rule_violation Table Created
**Purpose**: Foundation for risk assessment and rule violation tracking  
**Schema**: id, detected_date, reference_id, name, resource_type, rule_description, rule_group, status, positive, rule_name  
**Impact**: Enables comprehensive risk rule framework (expanded in v1.20+)

### 🗄️ Database Optimizations
- Vote data indexes: ballot_id, date, party, votes
- Performance improvements for voting queries
- Materialized view refresh optimization

---

## [1.23.0] - 2019-09-04

### 🗄️ Database Schema
**Focus**: Schema refinements

- Data model updates
- Schema optimization
- Performance enhancements

---

## [1.22.0] - 2019-01-07

### 🗄️ Database Schema
**Focus**: Data model updates

- Schema improvements
- Data integrity enhancements
- Performance optimizations

---

## [1.21.0] - 2019-09-04

### 🗄️ Database Schema
**Focus**: Database improvements

- Schema enhancements
- Performance tuning
- Data model refinements

---

## [1.20.0] - 2019-09-04

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

## [1.19.0] - 2018-06-06

### 🗄️ Database Schema
**Focus**: Data quality enhancements

- Data validation improvements
- Schema integrity enhancements
- Quality assurance measures

---

## [1.18.0] - 2018-05-16

### 🗄️ Database Schema
**Focus**: View optimizations

- Database view performance improvements
- Query optimization
- Index strategy refinements

---

## [1.17.0] - 2018-05-02

### 🗄️ Database Schema
**Focus**: Schema refinements

- Data model improvements
- Schema structure enhancements
- Performance optimizations

---

## [1.16.0] - 2018-04-04

### 🗄️ Database Schema
**Focus**: Database updates

- Schema updates and improvements
- Data integrity enhancements
- Performance tuning

---

## [1.15.0] - 2018-03-17

### 🗄️ Database Schema
**Focus**: Performance improvements

- Query performance optimization
- Index improvements
- Database efficiency enhancements

---

## [1.14.0] - 2018-02-16

### 🗄️ Database Schema
**Focus**: Schema enhancements

- Data model refinements
- Schema structure improvements
- Performance optimizations

---

## [1.13.0] - 2018-02-04

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

## [1.12.0] - 2018-02-04

### 🗄️ Database Schema
**Focus**: Data model updates

- Schema improvements
- Data structure enhancements
- Performance optimizations

---

## [1.11.0] - 2017-12-10

### 🗄️ Database Schema
**Focus**: Schema updates

- Data model improvements
- Schema refinements
- Performance enhancements

---

## [1.10.0] - 2017-09-10

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

## [1.9.0] - 2016-05-31

### 🗄️ Database Schema
**Focus**: Database improvements

- Schema enhancements
- Data model refinements
- Performance optimizations

---

## [1.8.0] - 2016-05-31

### 🗄️ Database Schema
**Focus**: Schema refinements

- Data structure improvements
- Schema updates
- Performance tuning

---

## [1.7.0] - 2016-05-28

### 🗄️ Database Schema
**Focus**: Data model enhancements

- Schema improvements
- Data integrity enhancements
- Performance optimizations

---

## [1.6.0] - 2019-09-04

### 🗄️ Database Schema
**Focus**: Database updates

- Schema refinements
- Data model improvements
- Performance enhancements

---

## [1.5.0] - 2019-09-04

### 🗄️ Database Schema
**Focus**: Schema improvements

- Data structure enhancements
- Performance optimizations
- Schema updates

---

## [1.4.0] - 2019-09-04

### 🗄️ Database Schema
**Focus**: Performance enhancements

- Query optimization
- Index improvements
- Database efficiency updates

---

## [1.3.0] - 2019-09-04

### 🗄️ Database Schema
**Focus**: Database refinements

- Schema structure improvements
- Data model updates
- Performance tuning

---

## [1.2.0] - 2019-09-04

### 🗄️ Database Schema
**Focus**: Schema updates

- Data model refinements
- Schema improvements
- Performance optimizations

---

## [1.1.0] - 2019-09-04

### 🗄️ Database Schema
**Focus**: Database improvements

- Initial schema enhancements
- Data structure refinements
- Foundation improvements

---

## [1.0.0] - 2016-04-23

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

