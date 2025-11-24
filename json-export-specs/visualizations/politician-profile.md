# Politician Profile Visualization

**Last Updated**: 2024-11-24

## Overview

Complete visualization guide for politician profile data including network graphs, activity timelines, performance dashboards, and intelligence metrics.

## Data Structure

```mermaid
graph TB
    A[Politician JSON] --> B[Basic Info]
    A --> C[Activity Data]
    A --> D[Relationships]
    A --> E[Intelligence]
    A --> F[Time Series]
    
    B --> B1[Name, Photo]
    B --> B2[Party, Role]
    B --> B3[Contact Info]
    
    C --> C1[Attendance]
    C --> C2[Documents]
    C --> C3[Votes]
    
    D --> D1[Committees]
    D --> D2[Ministry]
    D --> D3[Colleagues]
    
    E --> E1[Risk Score]
    E --> E2[Influence Metrics]
    E --> E3[Network Centrality]
    
    F --> F1[Behavioral Trends]
    F --> F2[Risk Evolution]
    F --> F3[Influence Changes]
    
    style A fill:#e1f5ff
    style B fill:#c3e7ff
    style C fill:#c3e7ff
    style D fill:#c3e7ff
    style E fill:#c3e7ff
    style F fill:#c3e7ff
```

## Data Flow

```mermaid
graph LR
    A[CDN Endpoint] -->|fetch| B[Browser Cache]
    B -->|parse| C[JSON Parser]
    C -->|validate| D[Data Layer]
    D -->|transform| E[View Model]
    E -->|render| F[Visualization]
    F -->|display| G[User Interface]
    
    style A fill:#d4edda
    style B fill:#d4edda
    style C fill:#d4edda
    style D fill:#d4edda
    style E fill:#d4edda
    style F fill:#d4edda
    style G fill:#d4edda
```

## Component Architecture

```mermaid
graph TB
    A[PoliticianProfile] --> B[ProfileHeader]
    A --> C[ActivitySection]
    A --> D[NetworkSection]
    A --> E[IntelligenceSection]
    A --> F[TimeSeriesSection]
    
    B --> B1[PhotoCard]
    B --> B2[BasicInfo]
    B --> B3[PartyBadge]
    B --> B4[StatusIndicators]
    
    C --> C1[AttendanceChart]
    C --> C2[DocumentList]
    C --> C3[VotingRecord]
    
    D --> D1[NetworkGraph]
    D --> D2[RelationshipMap]
    D --> D3[CollaborationMatrix]
    
    E --> E1[RiskGauge]
    E --> E2[InfluenceScore]
    E --> E3[CentralityMetrics]
    
    F --> F1[TrendChart]
    F --> F2[EvolutionTimeline]
    F --> F3[PredictionView]
    
    style A fill:#fff3cd
    style B fill:#ffe69c
    style C fill:#ffe69c
    style D fill:#ffe69c
    style E fill:#ffe69c
    style F fill:#ffe69c
```

## User Interaction Flow

```mermaid
stateDiagram-v2
    [*] --> Loading
    Loading --> ProfileView
    ProfileView --> NetworkView: Click network
    ProfileView --> ActivityView: Click activity
    ProfileView --> IntelligenceView: Click intelligence
    
    NetworkView --> ColleagueProfile: Click colleague
    ActivityView --> DocumentDetail: Click document
    IntelligenceView --> TrendDetail: Click trend
    
    ColleagueProfile --> ProfileView: Back
    DocumentDetail --> ActivityView: Back
    TrendDetail --> IntelligenceView: Back
    
    ProfileView --> [*]: Close
    
    style Loading fill:#f8d7da
    style ProfileView fill:#f8d7da
    style NetworkView fill:#f8d7da
    style ActivityView fill:#f8d7da
    style IntelligenceView fill:#f8d7da
```

## Visualization Types

### 1. Profile Header Card

```mermaid
graph LR
    A[Photo] --- B[Name & Party]
    B --- C[Role & Status]
    C --- D[Contact Info]
    
    style A fill:#0173B2
    style B fill:#029E73
    style C fill:#DE8F05
    style D fill:#CC78BC
```

### 2. Network Graph Structure

```mermaid
graph TD
    P[Politician] --> C1[Committee 1]
    P --> C2[Committee 2]
    P --> M[Ministry]
    
    P -.collaboration.- Col1[Colleague 1]
    P -.collaboration.- Col2[Colleague 2]
    
    C1 --> Col1
    C2 --> Col2
    
    style P fill:#E8112d,color:#fff
    style C1 fill:#52BDEC
    style C2 fill:#52BDEC
    style M fill:#009933
    style Col1 fill:#DA291C
    style Col2 fill:#006AB3
```

### 3. Activity Timeline

```mermaid
gantt
    title Parliamentary Activity Timeline
    dateFormat YYYY-MM-DD
    section Documents
    Motions Submitted    :2024-01-15, 2024-03-30
    Reports Filed        :2024-04-01, 2024-06-15
    section Committee
    Committee Member     :2024-01-01, 2024-12-31
    Chair Position       :2024-07-01, 2024-12-31
    section Votes
    Active Voting        :2024-01-01, 2024-11-24
```

### 4. Performance Dashboard

```mermaid
graph LR
    A[Performance Metrics] --> B[Attendance: 87%]
    A --> C[Documents: 142]
    A --> D[Votes: 456]
    A --> E[Influence: 0.78]
    
    B --> B1{Above Average}
    C --> C1{High Activity}
    D --> D1{Consistent}
    E --> E1{Growing}
    
    style A fill:#0173B2
    style B fill:#029E73
    style C fill:#029E73
    style D fill:#029E73
    style E fill:#DE8F05
```

### 5. Intelligence Metrics

```mermaid
graph TB
    I[Intelligence Profile] --> R[Risk Assessment]
    I --> N[Network Position]
    I --> T[Trend Analysis]
    
    R --> R1[Score: 0.34 Low]
    R --> R2[Factors: 3]
    R --> R3[Trend: Stable]
    
    N --> N1[Centrality: 0.67]
    N --> N2[Connections: 87]
    N --> N3[Influence: 0.78]
    
    T --> T1[Momentum: +12%]
    T --> T2[Activity: Up]
    T --> T3[Visibility: High]
    
    style I fill:#E8112d,color:#fff
    style R fill:#DE8F05
    style N fill:#029E73
    style T fill:#52BDEC
```

## Swedish Party Colors

```mermaid
graph LR
    S[S Social Democrats] --> M[M Moderates]
    M --> SD[SD Sweden Democrats]
    SD --> C[C Center Party]
    C --> V[V Left Party]
    V --> KD[KD Christian Democrats]
    KD --> L[L Liberals]
    L --> MP[MP Green Party]
    
    style S fill:#E8112d,color:#fff
    style M fill:#52BDEC
    style SD fill:#DDDD00
    style C fill:#009933,color:#fff
    style V fill:#DA291C,color:#fff
    style KD fill:#000077,color:#fff
    style L fill:#006AB3,color:#fff
    style MP fill:#83CF39
```

## Responsive Layout

```mermaid
graph TB
    A[Desktop 1440px] --> B[Large 1024px]
    B --> C[Tablet 768px]
    C --> D[Mobile 320px]
    
    A --> A1[4 Columns]
    B --> B1[3 Columns]
    C --> C1[2 Columns]
    D --> D1[1 Column]
    
    A1 --> A2[Full Network Graph]
    B1 --> B2[Compact Network]
    C1 --> C2[List View]
    D1 --> D2[Cards Stack]
    
    style A fill:#d4edda
    style B fill:#d4edda
    style C fill:#fff3cd
    style D fill:#f8d7da
```

## Accessibility Flow

```mermaid
graph LR
    A[Keyboard Navigation] --> B[Tab Order]
    B --> C[Focus Indicators]
    C --> D[Screen Reader]
    D --> E[ARIA Labels]
    E --> F[Alt Text]
    F --> G[Color Contrast]
    
    style A fill:#d4edda
    style B fill:#d4edda
    style C fill:#d4edda
    style D fill:#d4edda
    style E fill:#d4edda
    style F fill:#d4edda
    style G fill:#d4edda
```

## Data Loading States

```mermaid
stateDiagram-v2
    [*] --> Initial
    Initial --> Loading: Fetch request
    Loading --> Success: Data received
    Loading --> Error: Network error
    
    Success --> Rendering: Parse data
    Rendering --> Interactive: Complete
    
    Error --> Retry: User action
    Retry --> Loading
    
    Interactive --> Refreshing: Update request
    Refreshing --> Success: New data
    
    Interactive --> [*]: User exits
```

## Features

- **Profile Card**: Photo, name, party affiliation, role, contact information
- **Network Graph**: Relationships with committees, colleagues, ministries
- **Activity Timeline**: Documents, votes, committee participation over time
- **Performance Metrics**: Attendance, productivity, engagement scores
- **Intelligence Dashboard**: Risk assessment, influence metrics, network centrality
- **Trend Analysis**: Behavioral patterns, risk evolution, momentum indicators

## Usage Scenarios

1. **Journalist Research**: Quick profile overview with key intelligence metrics
2. **Voter Information**: Comprehensive activity record and voting history
3. **Network Analysis**: Understanding political relationships and influence
4. **Accountability Tracking**: Attendance, document production, engagement
5. **Predictive Analytics**: Risk assessment, trend forecasting

## Integration Points

```mermaid
graph TB
    A[Politician Profile] --> B[Party Dashboard]
    A --> C[Committee View]
    A --> D[Ministry Overview]
    A --> E[Intelligence Reports]
    
    B --> B1[Party comparison]
    C --> C1[Committee membership]
    D --> D1[Government role]
    E --> E1[Risk assessment]
    
    style A fill:#E8112d,color:#fff
    style B fill:#52BDEC
    style C fill:#029E73
    style D fill:#009933,color:#fff
    style E fill:#DE8F05
```

## Performance Considerations

- Lazy load network graph for 50+ connections
- Virtualize document lists for 100+ items
- Cache profile data for 1 hour
- Progressive image loading for photos
- Debounce search/filter interactions (300ms)

---

**JSON Source**: `/v1.0.0/politicians/profiles/{id}.json`
**Update Frequency**: Daily (02:00 UTC)
**Cache Duration**: 1 hour
**Bundle Size**: ~5KB (profile card only), ~50KB (full profile with network)
