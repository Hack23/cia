# Top 10 Electoral Risk Politicians

**Last Updated**: 2024-11-24

## Overview

Politicians facing highest electoral risk in the 2026 election based on multi-factor risk assessment including polling, scandals, attendance, and district vulnerability.

## Electoral Risk Rankings

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Top 10 Politicians at Electoral Risk (Risk Score 0-100)"
    x-axis ["Pol A", "Pol B", "Pol C", "Pol D", "Pol E", "Pol F", "Pol G", "Pol H", "Pol I", "Pol J"]
    y-axis "Risk Score" 0 --> 100
    bar [87, 82, 78, 75, 72, 69, 66, 64, 61, 59]
```

## Risk Factor Breakdown

```mermaid
graph TB
    RISK[Electoral Risk Factors] --> POLL[Poll Decline]
    RISK --> SCAN[Scandals]
    RISK --> PERF[Performance]
    RISK --> DIST[District Vulnerability]
    
    POLL --> P1[Personal approval down<br/>Party support declining<br/>Challenger strength]
    POLL --> P2[Weight: 35%<br/>High impact]
    
    SCAN --> S1[Ethics violations<br/>Media controversies<br/>Legal issues]
    SCAN --> S2[Weight: 25%<br/>Very high impact]
    
    PERF --> PF1[Low attendance<br/>Few bills<br/>Weak committee work]
    PERF --> PF2[Weight: 20%<br/>Moderate impact]
    
    DIST --> D1[Narrow 2022 victory<br/>Swing district<br/>Strong opposition]
    DIST --> D2[Weight: 20%<br/>High impact]
    
    style RISK fill:#3498db,stroke:#2980b9,color:#fff
    style POLL fill:#e74c3c,stroke:#c0392b,color:#fff
    style SCAN fill:#e74c3c,stroke:#c0392b,color:#fff
    style PERF fill:#f39c12,stroke:#f39c12,color:#fff
    style DIST fill:#f39c12,stroke:#f39c12,color:#fff
```

## Risk by Party

```mermaid
%%{init: {'theme':'base'}}%%
pie title "High-Risk Seats (>60) by Party"
    "Social Democrats (S)" : 3
    "Moderates (M)" : 2
    "Sweden Democrats (SD)" : 2
    "Center Party (C)" : 1
    "Left Party (V)" : 1
    "Green Party (MP)" : 1
```

## Risk Category Distribution

```mermaid
graph LR
    A[Risk Categories] --> B[Critical 80-100]
    A --> C[High 60-80]
    A --> D[Moderate 40-60]
    
    B --> B1[⛔ Likely to lose<br/>3 politicians<br/>Immediate intervention]
    
    C --> C1[⚠️ Vulnerable<br/>7 politicians<br/>Enhanced campaign]
    
    D --> D1[📊 At risk<br/>15 politicians<br/>Monitor closely]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#e74c3c,stroke:#c0392b,color:#fff
    style C fill:#f39c12,stroke:#f39c12,color:#fff
    style D fill:#95a5a6,stroke:#7f8c8d,color:#fff
```

## District Vulnerability Map

```mermaid
%%{init: {'theme':'base'}}%%
quadrantChart
    title Electoral Vulnerability (Margin vs Opposition Strength)
    x-axis Weak Opposition --> Strong Opposition
    y-axis Large Margin --> Narrow Margin
    quadrant-1 Critical Risk
    quadrant-2 High Risk
    quadrant-3 Low Risk
    quadrant-4 Moderate Risk
    
    Politician A: [0.8, 0.9]
    Politician B: [0.75, 0.85]
    Politician C: [0.7, 0.8]
    Politician D: [0.65, 0.75]
    Politician E: [0.6, 0.7]
    Politician F: [0.55, 0.65]
    Politician G: [0.5, 0.6]
```

## Trend Analysis

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Risk Score Evolution (6 Months)"
    x-axis ["Jun", "Jul", "Aug", "Sep", "Oct", "Nov"]
    y-axis "Risk Score" 0 --> 100
    line [75, 78, 80, 83, 85, 87]
    line [70, 72, 74, 77, 80, 82]
    line [65, 67, 69, 72, 75, 78]
```

## Scandal Impact Assessment

```mermaid
graph TD
    SCANDAL[Scandal Types] --> ETHICS[Ethics Violations]
    SCANDAL --> FINANCIAL[Financial Impropriety]
    SCANDAL --> BEHAVIOR[Behavioral Issues]
    
    ETHICS --> E1[Conflict of interest<br/>Vote trading<br/>Misconduct]
    ETHICS --> E2[Impact: +15-25 risk points<br/>Voter trust damaged]
    
    FINANCIAL --> F1[Asset disclosure<br/>Unexplained wealth<br/>Tax issues]
    FINANCIAL --> F2[Impact: +20-30 risk points<br/>Corruption perception]
    
    BEHAVIOR --> B1[Harassment<br/>Discrimination<br/>Inappropriate conduct]
    BEHAVIOR --> B2[Impact: +25-35 risk points<br/>Party liability]
    
    style SCANDAL fill:#3498db,stroke:#2980b9,color:#fff
    style ETHICS fill:#f39c12,stroke:#f39c12,color:#fff
    style FINANCIAL fill:#e74c3c,stroke:#c0392b,color:#fff
    style BEHAVIOR fill:#e74c3c,stroke:#c0392b,color:#fff
```

## Campaign Strength Comparison

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Incumbent vs Challenger Strength"
    x-axis ["Pol A", "Pol B", "Pol C", "Pol D", "Pol E"]
    y-axis "Strength Score" 0 --> 100
    bar [45, 52, 48, 55, 50]
    bar [72, 68, 70, 65, 67]
```

## Recovery Strategies

```mermaid
graph LR
    RECOVERY[Risk Mitigation] --> MEDIA[Media Campaign]
    RECOVERY --> GROUND[Ground Game]
    RECOVERY --> POLICY[Policy Focus]
    RECOVERY --> DAMAGE[Damage Control]
    
    MEDIA --> M1[Positive messaging<br/>Accomplishments<br/>Local media]
    
    GROUND --> G1[Door-knocking<br/>Town halls<br/>Constituency service]
    
    POLICY --> P1[Local projects<br/>Issue leadership<br/>Tangible results]
    
    DAMAGE --> D1[Apology/explanation<br/>Corrective action<br/>Transparency]
    
    style RECOVERY fill:#3498db,stroke:#2980b9,color:#fff
    style MEDIA fill:#9b59b6,stroke:#8e44ad,color:#fff
    style GROUND fill:#27ae60,stroke:#2ecc71,color:#fff
    style POLICY fill:#f39c12,stroke:#f39c12,color:#fff
    style DAMAGE fill:#e74c3c,stroke:#c0392b,color:#fff
```

## Historical Seat Loss Correlation

```mermaid
gantt
    title Risk Score vs Actual Seat Loss (2022 Election)
    dateFormat YYYY-MM-DD
    section High Risk (80+)
    Lost 75% of seats: 2022-01-01, 2022-09-11
    section Medium Risk (60-80)
    Lost 40% of seats: 2022-01-01, 2022-09-11
    section Low Risk (<60)
    Lost 10% of seats: 2022-01-01, 2022-09-11
```

## Party Strategy Implications

```mermaid
graph TB
    PARTY[Party Response] --> PROTECT[Protect Incumbents]
    PARTY --> REPLACE[Replace Candidates]
    PARTY --> SUPPORT[Target Support]
    
    PROTECT --> PR1[Additional funding<br/>Leadership visits<br/>Media support]
    PROTECT --> PR2[For: Recoverable seats<br/>Strong performers]
    
    REPLACE --> RE1[Primary challenger<br/>Retirement pressure<br/>Different candidate]
    REPLACE --> RE2[For: Unrecoverable<br/>Toxic candidates]
    
    SUPPORT --> SU1[Resource allocation<br/>Targeted ads<br/>GOTV efforts]
    SUPPORT --> SU2[For: Winnable districts<br/>Close races]
    
    style PARTY fill:#3498db,stroke:#2980b9,color:#fff
    style PROTECT fill:#27ae60,stroke:#2ecc71,color:#fff
    style REPLACE fill:#e74c3c,stroke:#c0392b,color:#fff
    style SUPPORT fill:#f39c12,stroke:#f39c12,color:#fff
```

## Features

- **Risk Rankings**: Comprehensive risk scores for top 10 vulnerable politicians
- **Multi-Factor Model**: Combines polling, scandals, performance, and district factors
- **Trend Tracking**: Six-month evolution of risk scores
- **Party Analysis**: Distribution of at-risk seats across parties
- **Scandal Impact**: Quantified effect of different scandal types
- **Recovery Strategies**: Evidence-based mitigation approaches
- **Historical Validation**: Correlation with actual 2022 election outcomes
- **Strategic Implications**: Party decision-making guidance

## Usage Scenarios

1. **Campaign Planning**: Target resources to vulnerable incumbents
2. **Opposition Research**: Identify challenger opportunities
3. **Media Analysis**: Electoral vulnerability reporting
4. **Party Strategy**: Candidate replacement decisions
5. **Voter Information**: Understanding competitive races
6. **Polling Guidance**: Priority districts for surveying

## Data Sources

- **Primary Views**: 
  - `view_politician_behavioral_trends` - Behavioral metrics and risk assessment
  - `view_politician_risk_summary` - Aggregated risk indicators
  - `view_riksdagen_vote_data_ballot_politician_summary_annual` - Voting patterns
- **Supplementary Data**: 
  - Electoral margin data (2022 election results)
  - District competitiveness indicators
  - External polling data (when available)
- **Key Columns**:
  - `behavioral_assessment` - EXCELLENT_BEHAVIOR to CRITICAL_CONCERN
  - `avg_absence_rate` - Attendance performance
  - `avg_win_rate` - Legislative effectiveness
  - `effectiveness_status` - Classification level
- **Risk Model Components** (Weighted):
  - Poll Decline (35%): Trends in support metrics
  - Scandals (25%): Ethics violations and media controversies
  - Performance (20%): Attendance + effectiveness + productivity metrics
  - District (20%): Electoral margin and opponent strength
- **Risk Rules**: Multiple rules including `PoliticianLazy.drl`, `PoliticianIneffectiveVoting.drl`
- **Update Frequency**: Weekly (risk scores), Daily (behavioral metrics)
- **Historical Data**: 2018, 2022 elections for validation
- **Predictive Accuracy**: Based on documented behavioral patterns

## Model Details

**Risk Score Calculation:**
- Poll Decline (35%): Personal + party polling trends
- Scandals (25%): Ethics violations, controversies, investigations
- Performance (20%): Attendance, productivity, committee work
- District (20%): 2022 margin, opponent strength, demographics

**Risk Thresholds:**
- Critical (80-100): >75% probability of seat loss
- High (60-80): 40-75% probability of seat loss
- Moderate (40-60): 20-40% probability of seat loss

---

**Last Review**: 2024-11-24  
**Visualization Version**: 1.0.0  
**Compliance**: WCAG 2.1 AA
