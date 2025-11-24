# Political Intelligence Dashboard

**Last Updated**: 2024-11-24

## Overview

High-level political intelligence dashboard providing comprehensive overview of Swedish Riksdag with key performance indicators, risk metrics, and intelligence products.

## System Health Overview

```mermaid
graph LR
    A[CIA Platform Status] --> B[Data Collection]
    A --> C[Processing Pipeline]
    A --> D[Analytics Engine]
    A --> E[Risk Detection]
    
    B --> B1[✅ Operational<br/>99.2% uptime]
    C --> C1[✅ Healthy<br/>Hourly refresh]
    D --> D1[⚠️ Warning<br/>High load]
    E --> E1[✅ Active<br/>45 rules running]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#27ae60,stroke:#2ecc71,color:#fff
    style C fill:#27ae60,stroke:#2ecc71,color:#fff
    style D fill:#f39c12,stroke:#f39c12,color:#fff
    style E fill:#27ae60,stroke:#2ecc71,color:#fff
```

## Key Performance Indicators

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Riksdag Activity Metrics (Last 30 Days)"
    x-axis ["Week 1", "Week 2", "Week 3", "Week 4"]
    y-axis "Activity Score" 0 --> 100
    line [72, 75, 78, 82]
    line [65, 68, 71, 75]
    line [58, 62, 65, 68]
```

## Political Risk Heat Map

```mermaid
%%{init: {'theme':'base'}}%%
quadrantChart
    title Political Risk Matrix (Impact vs Likelihood)
    x-axis Low Likelihood --> High Likelihood
    y-axis Low Impact --> High Impact
    quadrant-1 Critical Risks
    quadrant-2 Strategic Concerns
    quadrant-3 Monitor
    quadrant-4 Active Management
    
    Coalition Instability: [0.55, 0.75]
    Corruption Scandal: [0.3, 0.9]
    Policy Deadlock: [0.65, 0.6]
    Minister Resignation: [0.4, 0.7]
    Electoral Shift: [0.7, 0.8]
    Public Unrest: [0.35, 0.65]
    International Crisis: [0.5, 0.85]
    Economic Downturn: [0.6, 0.75]
```

## Data Source Health

```mermaid
%%{init: {'theme':'base'}}%%
pie title "Data Collection Status (85 Sources)"
    "Operational" : 75
    "Degraded" : 7
    "Offline" : 3
```

## Intelligence Products Status

```mermaid
graph TB
    INTEL[Intelligence Products] --> RISK[Risk Assessments]
    INTEL --> NETWORK[Network Analysis]
    INTEL --> TREND[Trend Reports]
    INTEL --> PRED[Predictive Analytics]
    
    RISK --> R1[349 Politicians<br/>8 Parties<br/>15 Committees]
    RISK --> R2[Updated: Hourly<br/>45 risk rules<br/>3 severity levels]
    
    NETWORK --> N1[Network graphs<br/>Influence metrics<br/>Centrality scores]
    NETWORK --> N2[Updated: Daily<br/>12,547 relationships<br/>87 key influencers]
    
    TREND --> T1[Monthly reports<br/>Behavioral patterns<br/>Historical analysis]
    TREND --> T2[Updated: Weekly<br/>156 trend indicators<br/>24-month lookback]
    
    PRED --> P1[Election forecasts<br/>Coalition models<br/>Risk predictions]
    PRED --> P2[Updated: Monthly<br/>3 forecast models<br/>85% accuracy]
    
    style INTEL fill:#3498db,stroke:#2980b9,color:#fff
    style RISK fill:#e74c3c,stroke:#c0392b,color:#fff
    style NETWORK fill:#9b59b6,stroke:#8e44ad,color:#fff
    style TREND fill:#f39c12,stroke:#f39c12,color:#fff
    style PRED fill:#1abc9c,stroke:#16a085,color:#fff
```

## Real-Time Activity Monitor

```mermaid
gantt
    title Parliamentary Activity Timeline (Current Week)
    dateFormat YYYY-MM-DD
    section Plenary
    Chamber Debates: 2024-11-18, 2024-11-22
    Voting Sessions: 2024-11-20, 2024-11-21
    section Committees
    Finance Meetings: 2024-11-18, 2024-11-22
    Defense Hearings: 2024-11-19, 2024-11-20
    section Intelligence
    Risk Assessment: 2024-11-18, 2024-11-24
    Trend Analysis: 2024-11-21, 2024-11-24
```

## Risk Rule Distribution

```mermaid
graph LR
    A[45 Risk Rules] --> B[Behavioral 18]
    A --> C[Financial 12]
    A --> D[Ethical 10]
    A --> E[Performance 5]
    
    B --> B1[Absence tracking<br/>Voting patterns<br/>Activity monitoring]
    C --> C1[Conflict of interest<br/>Asset disclosure<br/>Financial ties]
    D --> D1[Ethics violations<br/>Code of conduct<br/>Investigations]
    E --> E1[Productivity<br/>Responsiveness<br/>Effectiveness]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#f39c12,stroke:#f39c12,color:#fff
    style C fill:#27ae60,stroke:#2ecc71,color:#fff
    style D fill:#e74c3c,stroke:#c0392b,color:#fff
    style E fill:#9b59b6,stroke:#8e44ad,color:#fff
```

## Data Pipeline Flow

```mermaid
graph TB
    A[External APIs] --> B[Data Collection]
    B --> C[Validation Layer]
    C --> D[Database Storage]
    D --> E[Analytics Engine]
    E --> F[Intelligence Products]
    F --> G[Public API]
    
    B --> B1[Riksdagen API<br/>Election Authority<br/>World Bank]
    C --> C1[Schema validation<br/>Quality checks<br/>Deduplication]
    D --> D1[PostgreSQL<br/>85 views<br/>349 politicians]
    E --> E1[Risk scoring<br/>Network analysis<br/>Trend detection]
    F --> F1[Dashboards<br/>Reports<br/>Alerts]
    G --> G1[JSON exports<br/>Visualizations<br/>API endpoints]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#27ae60,stroke:#2ecc71,color:#fff
    style C fill:#f39c12,stroke:#f39c12,color:#fff
    style D fill:#9b59b6,stroke:#8e44ad,color:#fff
    style E fill:#e74c3c,stroke:#c0392b,color:#fff
    style F fill:#1abc9c,stroke:#16a085,color:#fff
    style G fill:#34495e,stroke:#2c3e50,color:#fff
```

## Coverage Metrics

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Platform Coverage"
    x-axis ["Politicians", "Parties", "Committees", "Ministries", "Documents", "Votes"]
    y-axis "Count" 0 --> 500
    bar [349, 8, 15, 12, 48756, 125893]
```

## Intelligence Confidence Levels

```mermaid
graph TB
    CONF[Confidence Assessment] --> HIGH[High Confidence]
    CONF --> MED[Medium Confidence]
    CONF --> LOW[Low Confidence]
    
    HIGH --> H1[Multiple sources<br/>Verified data<br/>Recent updates]
    HIGH --> H2[Examples:<br/>Voting records<br/>Attendance data<br/>Committee assignments]
    
    MED --> M1[Single source<br/>Partially verified<br/>Periodic updates]
    MED --> M2[Examples:<br/>Network analysis<br/>Influence metrics<br/>Trend predictions]
    
    LOW --> L1[Limited sources<br/>Inferred data<br/>Irregular updates]
    LOW --> L2[Examples:<br/>Long-term forecasts<br/>Behavioral predictions<br/>Coalition scenarios]
    
    style CONF fill:#3498db,stroke:#2980b9,color:#fff
    style HIGH fill:#27ae60,stroke:#2ecc71,color:#fff
    style MED fill:#f39c12,stroke:#f39c12,color:#fff
    style LOW fill:#e74c3c,stroke:#c0392b,color:#fff
```

## Top Risk Categories

```mermaid
%%{init: {'theme':'base'}}%%
pie title "Risk Alerts by Category (Last 30 Days)"
    "Attendance Issues" : 28
    "Ethics Concerns" : 15
    "Financial Conflicts" : 12
    "Performance Decline" : 18
    "Controversial Votes" : 22
    "Media Scandals" : 5
```

## User Engagement Trends

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Platform Usage (Daily Active Users)"
    x-axis ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]
    y-axis "Users" 0 --> 5000
    line [3200, 3500, 3800, 4200, 4500, 2100, 1800]
```

## Alert Priority Distribution

```mermaid
graph LR
    A[Alert System] --> B[Critical: 5]
    A --> C[High: 23]
    A --> D[Medium: 47]
    A --> E[Low: 108]
    
    B --> B1[Immediate attention<br/>Major impact<br/>Email + SMS]
    C --> C1[Same-day review<br/>Significant impact<br/>Email notification]
    D --> D1[Weekly review<br/>Moderate impact<br/>Dashboard only]
    E --> E1[Monthly review<br/>Minor impact<br/>Log only]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#e74c3c,stroke:#c0392b,color:#fff
    style C fill:#f39c12,stroke:#f39c12,color:#fff
    style D fill:#f39c12,stroke:#f39c12,color:#fff
    style E fill:#27ae60,stroke:#2ecc71,color:#fff
```

## Database Performance

```mermaid
graph TB
    DB[Database Metrics] --> SIZE[Storage]
    DB --> PERF[Performance]
    DB --> HEALTH[Health]
    
    SIZE --> S1[Total: 12.5 GB<br/>Views: 85<br/>Tables: 156]
    PERF --> P1[Query time: 45ms avg<br/>Throughput: 1,200/sec<br/>Cache hit: 92%]
    HEALTH --> H1[Connections: 78/200<br/>Replication: Current<br/>Backup: Daily]
    
    S1 --> SR1{✅ Normal}
    P1 --> PR1{✅ Good}
    H1 --> HR1{✅ Healthy}
    
    style DB fill:#3498db,stroke:#2980b9,color:#fff
    style SIZE fill:#9b59b6,stroke:#8e44ad,color:#fff
    style PERF fill:#27ae60,stroke:#2ecc71,color:#fff
    style HEALTH fill:#1abc9c,stroke:#16a085,color:#fff
```

## Intelligence Production Timeline

```mermaid
gantt
    title Intelligence Product Updates
    dateFormat YYYY-MM-DD
    section Daily
    Risk Assessments: 2024-11-24, 1d
    Network Updates: 2024-11-24, 1d
    section Weekly
    Trend Reports: 2024-11-18, 7d
    Top 10 Rankings: 2024-11-18, 7d
    section Monthly
    Forecasts: 2024-11-01, 30d
    Analysis Reports: 2024-11-01, 30d
```

## API Performance

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "API Response Times (milliseconds)"
    x-axis ["Politicians", "Parties", "Committees", "Government", "Intelligence", "Top10"]
    y-axis "Response Time (ms)" 0 --> 200
    bar [45, 32, 38, 41, 89, 67]
```

## Features

- **System Health**: Real-time monitoring of data collection and processing
- **KPI Dashboard**: Key metrics tracking parliamentary activity
- **Risk Heat Map**: Visual representation of political risks by impact and likelihood
- **Intelligence Products**: Status of all analytical outputs
- **Data Pipeline**: Complete flow from collection to public API
- **Alert System**: Priority-based notification system
- **Performance Metrics**: Database, API, and system performance tracking
- **Production Timeline**: Update schedules for all intelligence products

## Usage Scenarios

1. **Platform Monitoring**: Real-time health check of CIA infrastructure
2. **Risk Overview**: High-level assessment of current political risks
3. **Data Quality**: Verification of source availability and freshness
4. **Performance Tracking**: System optimization and capacity planning
5. **Intelligence Briefing**: Quick overview for stakeholders
6. **Operational Dashboard**: Daily operational management

## Data Sources

- **Primary Views** (All 85 documented views):
  - `view_riksdagen_intelligence_dashboard` - Unified intelligence dashboard with KPIs
  - `view_riksdagen_voting_anomaly_detection` - Voting anomalies and defection risk
  - `view_riksdagen_coalition_alignment_matrix` - Coalition probability matrix
  - `view_politician_behavioral_trends` - Behavioral pattern analysis
  - `view_politician_risk_summary` - Aggregated risk indicators
  - `view_risk_score_evolution` - Risk score temporal evolution
  - `view_riksdagen_crisis_resilience_indicators` - Crisis performance metrics
- **System Metrics**:
  - Application logs and performance data
  - Database health indicators (connections, query times, cache hit rates)
  - Data collection status from external APIs (Riksdagen, Election Authority, World Bank)
- **Risk Rules Framework**:
  - 45-50 Drools-based behavioral detection rules
  - Categories: Politician (24), Party (10), Committee (4), Ministry (4), Decision Pattern (5)
  - Severity levels: MINOR (10-49), MAJOR (50-99), CRITICAL (100+)
- **Intelligence Products Status**:
  - Risk assessments (349 politicians, 8 parties, 15 committees)
  - Network analysis (12,547 relationships, 87 key influencers)
  - Trend reports (156 indicators, 24-month lookback)
  - Predictive analytics (3 forecast models, documented accuracy)
- **JSON Spec**: `dashboard-metrics.json`, `system-health.json`
- **Update Frequency**: Real-time (system metrics), Hourly (intelligence products)
- **Cache Duration**: 5 minutes (system metrics), 1 hour (intelligence products)
- **Coverage**: Complete CIA platform infrastructure and all intelligence products

## Technical Architecture

The CIA platform operates on a sophisticated intelligence infrastructure:
- **Data Layer**: PostgreSQL with 85 analytical views processing 349 politicians, 8 parties, 15 committees
- **Analytics Engine**: 45 Drools-based risk rules with behavioral, financial, ethical, and performance categories
- **Update Cycle**: Hourly data refresh from Riksdagen API, daily network analysis, weekly trend reports
- **API Layer**: RESTful JSON endpoints with sub-100ms response times
- **Intelligence Products**: Risk assessments, network graphs, trend reports, predictive models

---

**Last Review**: 2024-11-24  
**Visualization Version**: 1.0.0  
**Compliance**: WCAG 2.1 AA
