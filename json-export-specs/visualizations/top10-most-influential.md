# Top 10 Most Influential Politicians

**Last Updated**: 2024-11-24

## Overview

Politicians wielding greatest political influence based on network centrality, legislative success, coalition power, and agenda-setting capacity.

## Influence Rankings

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Top 10 Most Influential (Influence Score 0-100)"
    x-axis ["Pol A", "Pol B", "Pol C", "Pol D", "Pol E", "Pol F", "Pol G", "Pol H", "Pol I", "Pol J"]
    y-axis "Influence Score" 0 --> 100
    bar [98, 94, 90, 86, 82, 78, 74, 70, 66, 62]
```

## Influence Dimensions

```mermaid
graph TB
    INF[Political Influence] --> FORMAL[Formal Power]
    INF --> INFORMAL[Informal Power]
    INF --> LEGACY[Legacy Impact]
    
    FORMAL --> F1[Government position<br/>Committee chair<br/>Party leadership]
    F1 --> F2[Institutional authority<br/>Resource control<br/>Agenda power]
    
    INFORMAL --> I1[Network centrality<br/>Personal relationships<br/>Reputation]
    I1 --> I2[Coalition building<br/>Deal-making<br/>Persuasion]
    
    LEGACY --> L1[Policy achievements<br/>Institutional changes<br/>Political shifts]
    L1 --> L2[Long-term impact<br/>Lasting reforms<br/>System transformation]
    
    style INF fill:#3498db,stroke:#2980b9,color:#fff
    style FORMAL fill:#9b59b6,stroke:#8e44ad,color:#fff
    style INFORMAL fill:#27ae60,stroke:#2ecc71,color:#fff
    style LEGACY fill:#f39c12,stroke:#f39c12,color:#fff
```

## Power Base Analysis

```mermaid
%%{init: {'theme':'base'}}%%
pie title "Sources of Influence"
    "Government Position" : 30
    "Party Leadership" : 25
    "Committee Control" : 20
    "Network Centrality" : 15
    "Policy Expertise" : 10
```

## Network Centrality

```mermaid
%%{init: {'theme':'base'}}%%
quadrantChart
    title Influence Map (Formal vs Informal Power)
    x-axis Low Formal --> High Formal
    y-axis Low Informal --> High Informal
    quadrant-1 Power Brokers
    quadrant-2 Network Stars
    quadrant-3 Marginal
    quadrant-4 Formal Authority
    
    Prime Minister: [0.95, 0.9]
    Finance Minister: [0.9, 0.85]
    Opposition Leader: [0.75, 0.9]
    Committee Chair A: [0.7, 0.8]
    Coalition Broker: [0.6, 0.85]
```

## Legislative Success Rate

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Bill Passage Rate (% of sponsored bills passed)"
    x-axis ["Pol A", "Pol B", "Pol C", "Pol D", "Pol E"]
    y-axis "Success %" 0 --> 100
    bar [78, 72, 68, 64, 60]
```

## Agenda-Setting Power

```mermaid
graph LR
    AGENDA[Agenda Control] --> MEDIA[Media Attention]
    AGENDA --> PARTY[Party Platform]
    AGENDA --> GOV[Government Policy]
    
    MEDIA --> M1[Set news agenda<br/>Frame debates<br/>Define issues]
    
    PARTY --> P1[Shape party positions<br/>Influence platform<br/>Drive messaging]
    
    GOV --> G1[Government priorities<br/>Budget allocation<br/>Legislative focus]
    
    M1 --> IMPACT[Policy Impact]
    P1 --> IMPACT
    G1 --> IMPACT
    
    IMPACT --> I1[Issues prioritized<br/>Resources allocated<br/>Outcomes shaped]
    
    style AGENDA fill:#3498db,stroke:#2980b9,color:#fff
    style MEDIA fill:#9b59b6,stroke:#8e44ad,color:#fff
    style PARTY fill:#52BDEC,stroke:#2980b9,color:#fff
    style GOV fill:#27ae60,stroke:#2ecc71,color:#fff
    style IMPACT fill:#f39c12,stroke:#f39c12,color:#fff
```

## Coalition Influence

```mermaid
graph TD
    COAL[Coalition Dynamics] --> GOVT[Government Members]
    COAL --> SUPP[Support Parties]
    COAL --> OPP[Opposition]
    
    GOVT --> G1[PM: Influence 98<br/>Ministers: 85-92<br/>Backbenchers: 45-60]
    
    SUPP --> S1[SD Leaders: Influence 75<br/>Key supporters: 65<br/>Rank-and-file: 35]
    
    OPP --> O1[S Leader: Influence 88<br/>Shadow cabinet: 70-78<br/>Opposition MPs: 40-55]
    
    style COAL fill:#3498db,stroke:#2980b9,color:#fff
    style GOVT fill:#52BDEC,stroke:#2980b9,color:#fff
    style SUPP fill:#DDDD00,stroke:#f39c12
    style OPP fill:#E8112d,stroke:#c0392b,color:#fff
```

## Influence Evolution

```mermaid
gantt
    title Influence Trajectory (Top 3 Politicians)
    dateFormat YYYY-MM
    section Politician A
    Rising influence: 2022-10, 2024-11
    section Politician B
    Peak influence: 2023-01, 2024-11
    section Politician C
    Declining: 2022-10, 2024-11
```

## Influence Mechanisms

```mermaid
graph TB
    MECH[Influence Mechanisms] --> POS[Position Power]
    MECH --> REL[Relational Power]
    MECH --> EXP[Expert Power]
    MECH --> CHAR[Charismatic Power]
    
    POS --> P1[Formal authority<br/>Resource control<br/>Decision rights]
    
    REL --> R1[Network access<br/>Coalition building<br/>Deal-making]
    
    EXP --> E1[Policy knowledge<br/>Technical expertise<br/>Credibility]
    
    CHAR --> C1[Personal appeal<br/>Communication skill<br/>Leadership presence]
    
    style MECH fill:#3498db,stroke:#2980b9,color:#fff
    style POS fill:#9b59b6,stroke:#8e44ad,color:#fff
    style REL fill:#27ae60,stroke:#2ecc71,color:#fff
    style EXP fill:#f39c12,stroke:#f39c12,color:#fff
    style CHAR fill:#1abc9c,stroke:#16a085,color:#fff
```

## Policy Impact Areas

```mermaid
%%{init: {'theme':'base'}}%%
pie title "Influence by Policy Domain"
    "Economic Policy" : 28
    "Security & Defense" : 22
    "Social Welfare" : 18
    "Climate & Energy" : 15
    "Justice & Crime" : 12
    "Other" : 5
```

## Mentorship Networks

```mermaid
graph LR
    MENTOR[Senior Influencers] --> RISING[Rising Politicians]
    MENTOR --> PARTY[Party Development]
    MENTOR --> POLICY[Policy Expertise]
    
    RISING --> R1[Career advancement<br/>Network access<br/>Skill development]
    
    PARTY --> P1[Leadership pipeline<br/>Institutional knowledge<br/>Party cohesion]
    
    POLICY --> PO1[Expertise transfer<br/>Issue continuity<br/>Legacy preservation]
    
    style MENTOR fill:#3498db,stroke:#2980b9,color:#fff
    style RISING fill:#27ae60,stroke:#2ecc71,color:#fff
    style PARTY fill:#52BDEC,stroke:#2980b9,color:#fff
    style POLICY fill:#f39c12,stroke:#f39c12,color:#fff
```

## Institutional Influence

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Influence Across Institutions"
    x-axis ["Parliament", "Government", "Party", "Media", "Civil Society", "International"]
    y-axis "Influence Score" 0 --> 100
    bar [95, 88, 92, 78, 65, 72]
```

## Features

- **Influence Rankings**: Top 10 politicians by comprehensive influence score
- **Multi-dimensional Analysis**: Formal, informal, and legacy power
- **Network Centrality**: Position in political relationship networks
- **Legislative Success**: Bill passage and policy achievement rates
- **Agenda Power**: Capacity to shape political discourse and priorities
- **Coalition Dynamics**: Influence within government and opposition
- **Mechanism Analysis**: How power is built and exercised
- **Impact Assessment**: Policy outcomes and institutional changes

## Usage Scenarios

1. **Political Analysis**: Understanding power dynamics in Riksdag
2. **Lobbying Strategy**: Identifying key decision-makers
3. **Coalition Building**: Finding influential partners
4. **Media Analysis**: Profiling political power brokers
5. **Academic Research**: Studying political influence networks
6. **Career Planning**: Role models for aspiring politicians

## Data Sources

- **Primary Views**:
  - `view_riksdagen_politician_influence_metrics` - Influence and network analysis
  - `view_riksdagen_politician` - Core politician profiles
  - `view_riksdagen_vote_data_ballot_politician_summary_annual` - Legislative success
- **Network Analysis**: 
  - Co-sponsorship networks (bill collaboration patterns)
  - Voting alignment matrices
  - Committee membership overlaps
  - Coalition building indicators
- **Influence Dimensions**:
  - **Formal Power**: Government position, committee chair, party leadership
  - **Informal Power**: Network centrality, personal relationships, reputation
  - **Legacy Impact**: Policy achievements, institutional changes, lasting reforms
- **Key Metrics**:
  - Network centrality scores (betweenness, closeness, degree)
  - Bill passage rates and amendment success
  - Agenda-setting capacity (media influence, policy framing)
  - Coalition formation effectiveness
- **Influence Model**: Position (30%) + Network (25%) + Success (25%) + Agenda (20%)
- **Update Frequency**: Monthly (influence scores), Weekly (component metrics)
- **Network Data**: Co-sponsorship, voting patterns, committee membership
- **Validation**: Expert surveys, peer nominations, media analysis cross-reference

---

**Last Review**: 2024-11-24  
**Visualization Version**: 1.0.0  
**Compliance**: WCAG 2.1 AA
