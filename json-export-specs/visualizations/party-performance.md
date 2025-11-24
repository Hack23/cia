# Party Performance Visualization

**Last Updated**: 2024-11-24

## Overview

Visualization guide for party performance data including electoral trends, coalition dynamics, voting cohesion, and momentum analysis.

## Data Structure

```mermaid
graph TB
    A[Party JSON] --> B[Identity]
    A --> C[Electoral Data]
    A --> D[Performance]
    A --> E[Coalition]
    A --> F[Trends]
    
    B --> B1[Name, Logo]
    B --> B2[Ideology]
    B --> B3[Leadership]
    
    C --> C1[Seats]
    C --> C2[Vote Share]
    C --> C3[Polls]
    
    D --> D1[Cohesion]
    D --> D2[Activity]
    D --> D3[Influence]
    
    E --> E1[Partners]
    E --> E2[Alignment]
    E --> E3[Stability]
    
    F --> F1[Momentum]
    F --> F2[Predictions]
    F --> F3[Historical]
    
    style A fill:#e1f5ff
    style B fill:#c3e7ff
    style C fill:#c3e7ff
    style D fill:#c3e7ff
    style E fill:#c3e7ff
    style F fill:#c3e7ff
```

## Electoral Trend Flow

```mermaid
graph LR
    A[Historical Results] -->|analyze| B[Current Polls]
    B -->|predict| C[Seat Projections]
    C -->|calculate| D[Coalition Scenarios]
    D -->|visualize| E[Dashboard]
    
    style A fill:#d4edda
    style B fill:#d4edda
    style C fill:#d4edda
    style D fill:#d4edda
    style E fill:#d4edda
```

## Party Dashboard Components

```mermaid
graph TB
    A[PartyDashboard] --> B[HeaderSection]
    A --> C[ElectoralSection]
    A --> D[PerformanceSection]
    A --> E[CoalitionSection]
    
    B --> B1[PartyLogo]
    B --> B2[CurrentSeats]
    B --> B3[LeaderInfo]
    
    C --> C1[TrendChart]
    C --> C2[PollTracker]
    C --> C3[SeatProjection]
    
    D --> D1[CohesionMeter]
    D --> D2[ActivityScore]
    D --> D3[InfluenceGauge]
    
    E --> E1[PartnerMap]
    E --> E2[AlignmentMatrix]
    E --> E3[StabilityScore]
    
    style A fill:#fff3cd
    style B fill:#ffe69c
    style C fill:#ffe69c
    style D fill:#ffe69c
    style E fill:#ffe69c
```

## Electoral Trend Timeline

```mermaid
gantt
    title Electoral Performance 2022-2024
    dateFormat YYYY-MM-DD
    section Polls
    Upward trend      :2024-01-01, 2024-04-30
    Stabilization     :2024-05-01, 2024-08-31
    Current period    :2024-09-01, 2024-11-24
    section Seats
    Current: 107      :milestone, 2022-09-11, 0d
    Projected: 112    :milestone, 2026-09-13, 0d
```

## Coalition Network

```mermaid
graph TD
    Gov[Government Coalition] --> M[M Moderates]
    Gov --> KD[KD Christian Democrats]
    Gov --> L[L Liberals]
    
    Support[Support Party] --> SD[SD Sweden Democrats]
    
    Opp[Opposition] --> S[S Social Democrats]
    Opp --> V[V Left Party]
    Opp --> C[C Center Party]
    Opp --> MP[MP Green Party]
    
    M -.strong ties.- KD
    KD -.moderate ties.- L
    M -.support agreement.- SD
    
    S -.occasional.- C
    S -.close.- MP
    
    style M fill:#52BDEC
    style KD fill:#000077,color:#fff
    style L fill:#006AB3,color:#fff
    style SD fill:#DDDD00
    style S fill:#E8112d,color:#fff
    style V fill:#DA291C,color:#fff
    style C fill:#009933,color:#fff
    style MP fill:#83CF39
```

## Performance Comparison

```mermaid
graph LR
    A[8 Parties] --> B[Cohesion Score]
    A --> C[Activity Level]
    A --> D[Influence Score]
    
    B --> B1[M: 0.91]
    B --> B2[S: 0.88]
    B --> B3[SD: 0.87]
    
    C --> C1[V: High]
    C --> C2[MP: Medium]
    C --> C3[C: Medium]
    
    D --> D1[M: 0.89]
    D --> D2[S: 0.85]
    D --> D3[SD: 0.76]
    
    style A fill:#e1f5ff
    style B fill:#029E73
    style C fill:#DE8F05
    style D fill:#52BDEC
```

## Voting Cohesion Analysis

```mermaid
graph TB
    Party[Party Votes] --> Unity[Unity Votes]
    Party --> Split[Split Votes]
    Party --> Rebel[Rebel Votes]
    
    Unity --> U1[92% cohesion]
    Split --> S1[6% divided]
    Rebel --> R1[2% against party]
    
    U1 --> Result1[Strong discipline]
    S1 --> Result2[Some dissent]
    R1 --> Result3[Manageable rebels]
    
    style Party fill:#52BDEC
    style Unity fill:#029E73
    style Split fill:#DE8F05
    style Rebel fill:#f8d7da
```

## Momentum Tracker

```mermaid
graph LR
    A[Momentum Analysis] --> B[Velocity: +2.3%]
    A --> C[Acceleration: +0.4%]
    A --> D[Direction: Upward]
    
    B --> B1{Above average}
    C --> C1{Increasing}
    D --> D1{Favorable}
    
    style A fill:#52BDEC
    style B fill:#029E73
    style C fill:#029E73
    style D fill:#029E73
```

## Poll Data Structure

```mermaid
graph TB
    Polls[Poll Data] --> Sifo[Sifo]
    Polls --> Novus[Novus]
    Polls --> Ipsos[Ipsos]
    
    Sifo --> S1[Date: 2024-11-15]
    Sifo --> S2[Support: 23.4%]
    Sifo --> S3[Sample: 1500]
    
    Novus --> N1[Date: 2024-11-10]
    Novus --> N2[Support: 22.8%]
    Novus --> N3[Sample: 2000]
    
    Ipsos --> I1[Date: 2024-11-05]
    Ipsos --> I2[Support: 23.1%]
    Ipsos --> I3[Sample: 1800]
    
    style Polls fill:#52BDEC
    style Sifo fill:#029E73
    style Novus fill:#029E73
    style Ipsos fill:#029E73
```

## Seat Projection Model

```mermaid
graph LR
    A[Current Polls] -->|weight: 60%| D[Projection Model]
    B[Historical Trends] -->|weight: 25%| D
    C[Regional Factors] -->|weight: 15%| D
    
    D --> E[Projected Seats: 112]
    D --> F[Confidence: ±4 seats]
    D --> G[Probability: 85%]
    
    style A fill:#52BDEC
    style B fill:#029E73
    style C fill:#DE8F05
    style D fill:#e1f5ff
    style E fill:#029E73
    style F fill:#fff3cd
    style G fill:#029E73
```

## Coalition Stability Matrix

```mermaid
graph TB
    M[M Moderates] -->|0.89| KD[KD Christian Democrats]
    M -->|0.76| L[L Liberals]
    KD -->|0.81| L
    
    M -->|0.67 support| SD[SD Sweden Democrats]
    KD -->|0.72 support| SD
    L -->|0.54 support| SD
    
    S[S Social Democrats] -->|0.45| C[C Center Party]
    S -->|0.78| MP[MP Green Party]
    S -->|0.82| V[V Left Party]
    
    style M fill:#52BDEC
    style KD fill:#000077,color:#fff
    style L fill:#006AB3,color:#fff
    style SD fill:#DDDD00
    style S fill:#E8112d,color:#fff
    style C fill:#009933,color:#fff
    style MP fill:#83CF39
    style V fill:#DA291C,color:#fff
```

## Party Comparison Dashboard

```mermaid
graph LR
    Compare[Party Comparison] --> Seats[Seats]
    Compare --> Support[Support %]
    Compare --> Activity[Activity]
    Compare --> Cohesion[Cohesion]
    
    Seats --> S1[M: 68]
    Seats --> S2[S: 107]
    Seats --> S3[SD: 73]
    
    Support --> P1[M: 19.5%]
    Support --> P2[S: 30.3%]
    Support --> P3[SD: 20.6%]
    
    style Compare fill:#e1f5ff
    style Seats fill:#52BDEC
    style Support fill:#029E73
    style Activity fill:#DE8F05
    style Cohesion fill:#CC78BC
```

## User Interaction States

```mermaid
stateDiagram-v2
    [*] --> Overview
    Overview --> PartyDetail: Click party
    Overview --> CompareMode: Compare button
    
    PartyDetail --> TrendView: View trends
    PartyDetail --> CoalitionView: View coalition
    
    CompareMode --> SelectParties: Choose 2-4 parties
    SelectParties --> CompareView: Show comparison
    
    TrendView --> PartyDetail: Back
    CoalitionView --> PartyDetail: Back
    CompareView --> Overview: Reset
    
    PartyDetail --> [*]: Close
    
    style Overview fill:#d4edda
    style PartyDetail fill:#52BDEC,color:#fff
    style CompareMode fill:#fff3cd
```

## Regional Support Map

```mermaid
graph TB
    Sweden[Sweden 349 seats] --> North[Northern: 32]
    Sweden --> Central[Central: 156]
    Sweden --> South[Southern: 161]
    
    North --> N1[Strong: C, S]
    Central --> C1[Strong: M, S]
    South --> S1[Strong: SD, M]
    
    style Sweden fill:#006AA7,color:#fff
    style North fill:#FECC00
    style Central fill:#FECC00
    style South fill:#FECC00
```

## Performance Over Time

```mermaid
gantt
    title Party Performance Metrics 2024
    dateFormat YYYY-MM
    section Electoral Support
    22-24% range    :2024-01, 2024-06
    Upward trend    :2024-07, 2024-11
    section Activity
    High activity   :2024-01, 2024-11
    section Cohesion
    Stable 88-92%   :2024-01, 2024-11
```

## Features

- **Electoral Trends**: Poll tracking, seat projections, historical comparison
- **Coalition Dynamics**: Partner relationships, alignment scores, stability metrics
- **Performance Metrics**: Voting cohesion, activity levels, influence scores
- **Momentum Analysis**: Velocity, acceleration, trajectory predictions
- **Regional Support**: Geographic breakdown, constituency strength
- **Comparative Analysis**: Multi-party comparison across metrics

## Usage Scenarios

1. **Election Analysis**: Track polling trends and seat projections
2. **Coalition Monitoring**: Assess government stability and partner relationships
3. **Party Strategy**: Compare performance across multiple metrics
4. **Media Reporting**: Quick access to latest polls and trends
5. **Voter Information**: Understand party positions and performance

## Swedish Parliamentary Context

```mermaid
graph TB
    Riks[Riksdag 349 seats] --> Majority[Majority: 175 seats]
    Riks --> Current[Current Government]
    
    Current --> Gov[Coalition: 107 seats]
    Current --> Supp[Support: 73 seats]
    Current --> Total[Total: 180 seats]
    
    Gov --> M[M: 68]
    Gov --> KD[KD: 19]
    Gov --> L[L: 16]
    
    Supp --> SD[SD: 73]
    
    Opp[Opposition: 169] --> S[S: 107]
    Opp --> Others[V, C, MP: 62]
    
    style Riks fill:#006AA7,color:#fff
    style Gov fill:#52BDEC
    style Supp fill:#DDDD00
    style Opp fill:#E8112d,color:#fff
```

---

**JSON Source**: `/v1.0.0/parties/{party-code}.json`
**Update Frequency**: Daily (polls), Weekly (trends)
**Cache Duration**: 1 hour
**Bundle Size**: ~3KB (single party), ~15KB (all parties comparison)
