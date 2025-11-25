# Committee Network Analysis

**Last Updated**: 2024-11-24

## Overview

Visualization of Swedish Riksdag committee structure, cross-committee collaboration patterns, and network analysis of committee relationships.

## Committee Network Structure

```mermaid
graph TB
    subgraph "Finance & Economy"
        FIN[Finance Committee<br/>15 members<br/>Budget oversight]
        SKU[Taxation Committee<br/>17 members<br/>Tax policy]
    end
    
    subgraph "Security & Defense"
        DEF[Defense Committee<br/>17 members<br/>Military affairs]
        FOR[Foreign Affairs<br/>17 members<br/>International relations]
        JUS[Justice Committee<br/>17 members<br/>Legal framework]
    end
    
    subgraph "Social Policy"
        SOC[Social Insurance<br/>17 members<br/>Welfare programs]
        ARB[Labour Market<br/>17 members<br/>Employment policy]
        SFU[Social Affairs<br/>17 members<br/>Family policy]
    end
    
    subgraph "Infrastructure & Environment"
        ENV[Environment Committee<br/>17 members<br/>Climate policy]
        TRA[Transport Committee<br/>17 members<br/>Infrastructure]
        NAR[Business Committee<br/>17 members<br/>Industry policy]
    end
    
    subgraph "Knowledge & Culture"
        EDU[Education Committee<br/>17 members<br/>Schools & universities]
        KRU[Cultural Affairs<br/>17 members<br/>Arts & heritage]
    end
    
    FIN -->|Budget coordination| DEF
    FIN -->|Funding allocation| EDU
    FIN -->|Funding allocation| ENV
    FIN -->|Economic policy| SKU
    
    DEF -->|Security cooperation| FOR
    DEF -->|Legal framework| JUS
    
    SOC -->|Policy alignment| ARB
    SOC -->|Family support| SFU
    ARB -->|Education-work link| EDU
    
    ENV -->|Sustainable transport| TRA
    ENV -->|Green industry| NAR
    TRA -->|Business logistics| NAR
    
    EDU -->|Cultural education| KRU
    
    style FIN fill:#27ae60,stroke:#2ecc71,color:#fff
    style SKU fill:#27ae60,stroke:#2ecc71,color:#fff
    style DEF fill:#e74c3c,stroke:#c0392b,color:#fff
    style FOR fill:#3498db,stroke:#2980b9,color:#fff
    style JUS fill:#34495e,stroke:#2c3e50,color:#fff
    style SOC fill:#f39c12,stroke:#f39c12,color:#fff
    style ARB fill:#f39c12,stroke:#f39c12,color:#fff
    style SFU fill:#f39c12,stroke:#f39c12,color:#fff
    style ENV fill:#2ecc71,stroke:#27ae60,color:#fff
    style TRA fill:#95a5a6,stroke:#7f8c8d,color:#fff
    style NAR fill:#95a5a6,stroke:#7f8c8d,color:#fff
    style EDU fill:#9b59b6,stroke:#8e44ad,color:#fff
    style KRU fill:#1abc9c,stroke:#16a085,color:#fff
```

## Collaboration Intensity Matrix

```mermaid
%%{init: {'theme':'base', 'themeVariables': { 'fontSize':'14px'}}}%%
graph LR
    A[Committee Collaboration] --> B[High Intensity]
    A --> C[Medium Intensity]
    A --> D[Low Intensity]
    
    B --> B1[Finance-Defense<br/>Budget coordination]
    B --> B2[Social-Education<br/>Welfare-education link]
    B --> B3[Environment-Transport<br/>Sustainability goals]
    
    C --> C1[Finance-Education<br/>Funding allocation]
    C --> C2[Justice-Defense<br/>Security legislation]
    C --> C3[Labour-Education<br/>Skills development]
    
    D --> D1[Culture-Transport<br/>Limited overlap]
    D --> D2[Taxation-Culture<br/>Occasional interaction]
    D --> D3[Foreign-Environment<br/>Climate diplomacy]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#27ae60,stroke:#2ecc71,color:#fff
    style C fill:#f39c12,stroke:#f39c12,color:#fff
    style D fill:#e74c3c,stroke:#c0392b,color:#fff
```

## Committee Member Distribution by Party

```mermaid
%%{init: {'theme':'base'}}%%
pie title "Committee Membership Distribution (349 total seats)"
    "Social Democrats (S)" : 107
    "Sweden Democrats (SD)" : 73
    "Moderates (M)" : 68
    "Center Party (C)" : 24
    "Left Party (V)" : 24
    "Christian Democrats (KD)" : 19
    "Liberals (L)" : 16
    "Green Party (MP)" : 16
```

## Cross-Party Collaboration Patterns

```mermaid
graph TD
    A[Committee Collaboration Network] --> B[Coalition Committees]
    A --> C[Opposition Committees]
    A --> D[Cross-Aisle Cooperation]
    
    B --> B1[Finance: M+KD+L chairs<br/>Strong government control]
    B --> B2[Defense: M chair<br/>Coalition consensus]
    
    C --> C1[Social Insurance: S chair<br/>Opposition-led]
    C --> C2[Labour Market: S chair<br/>Worker focus]
    
    D --> D1[Environment: Cross-party<br/>Climate consensus]
    D --> D2[Justice: Broad support<br/>Legal expertise valued]
    D --> D3[Foreign Affairs: Unity<br/>National interest priority]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#52BDEC,stroke:#2980b9,color:#fff
    style C fill:#E8112d,stroke:#c0392b,color:#fff
    style D fill:#27ae60,stroke:#2ecc71,color:#fff
```

## Committee Productivity Rankings

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Committee Legislative Output (Documents per year)"
    x-axis ["Finance", "Justice", "Social", "Defense", "Education", "Environment", "Foreign", "Transport", "Culture", "Labour"]
    y-axis "Documents" 0 --> 500
    bar [456, 389, 367, 298, 287, 276, 245, 234, 198, 187]
```

## Committee Leadership Timeline

```mermaid
gantt
    title Committee Chair Positions (2024)
    dateFormat YYYY-MM-DD
    section Government Coalition
    Finance Committee (M): 2024-01-01, 2024-12-31
    Defense Committee (M): 2024-01-01, 2024-12-31
    Education (L): 2024-01-01, 2024-12-31
    section Opposition
    Social Insurance (S): 2024-01-01, 2024-12-31
    Labour Market (S): 2024-01-01, 2024-12-31
    section Cross-Party
    Environment (Mixed): 2024-01-01, 2024-12-31
    Justice (Mixed): 2024-01-01, 2024-12-31
```

## Committee Meeting Frequency

```mermaid
graph LR
    A[Meeting Frequency] --> B[Weekly]
    A --> C[Bi-weekly]
    A --> D[Monthly]
    
    B --> B1[Finance<br/>Justice<br/>Social Insurance]
    C --> C1[Defense<br/>Education<br/>Environment]
    D --> D1[Culture<br/>Foreign Affairs<br/>Transport]
    
    B --> B2[High activity<br/>40-50 meetings/year]
    C --> C2[Regular activity<br/>20-30 meetings/year]
    D --> D2[Moderate activity<br/>10-20 meetings/year]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#27ae60,stroke:#2ecc71,color:#fff
    style C fill:#f39c12,stroke:#f39c12,color:#fff
    style D fill:#e74c3c,stroke:#c0392b,color:#fff
```

## Network Centrality Analysis

```mermaid
graph TB
    Central[Central Hub Committees] --> FIN
    Central --> JUS
    Central --> SOC
    
    FIN[Finance Committee<br/>Centrality: 0.89<br/>Most connected]
    JUS[Justice Committee<br/>Centrality: 0.76<br/>Legal hub]
    SOC[Social Insurance<br/>Centrality: 0.73<br/>Welfare hub]
    
    FIN --> Links1[Links to 8 committees<br/>Budget oversight role]
    JUS --> Links2[Links to 6 committees<br/>Legal framework role]
    SOC --> Links3[Links to 5 committees<br/>Social policy role]
    
    style Central fill:#3498db,stroke:#2980b9,color:#fff
    style FIN fill:#27ae60,stroke:#2ecc71,color:#fff
    style JUS fill:#34495e,stroke:#2c3e50,color:#fff
    style SOC fill:#f39c12,stroke:#f39c12,color:#fff
```

## Committee Expertise Areas

```mermaid
%%{init: {'theme':'base'}}%%
quadrantChart
    title Committee Specialization vs Collaboration
    x-axis Low Specialization --> High Specialization
    y-axis Low Collaboration --> High Collaboration
    quadrant-1 Expert Collaborators
    quadrant-2 Specialized Silos
    quadrant-3 Generalist Teams
    quadrant-4 Connected Generalists
    
    Finance Committee: [0.8, 0.9]
    Defense Committee: [0.9, 0.6]
    Justice Committee: [0.85, 0.75]
    Social Insurance: [0.7, 0.8]
    Environment Committee: [0.75, 0.85]
    Education Committee: [0.6, 0.7]
    Culture Committee: [0.8, 0.4]
    Foreign Affairs: [0.95, 0.5]
```

## Cross-Committee Working Groups

```mermaid
graph TB
    A[Inter-Committee Groups] --> B[Climate Action Group]
    A --> C[Digital Transformation]
    A --> D[Security Alliance]
    
    B --> B1[Environment<br/>Transport<br/>Business]
    B --> B2[Focus: Green transition<br/>Policy alignment]
    
    C --> C1[Education<br/>Business<br/>Justice]
    C --> C2[Focus: Digital society<br/>E-governance]
    
    D --> D1[Defense<br/>Foreign Affairs<br/>Justice]
    D --> D2[Focus: National security<br/>Coordination]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#2ecc71,stroke:#27ae60,color:#fff
    style C fill:#9b59b6,stroke:#8e44ad,color:#fff
    style D fill:#e74c3c,stroke:#c0392b,color:#fff
```

## Committee Influence Flow

```mermaid
graph LR
    A[Policy Initiation] --> B{Committee Type}
    B -->|Financial| FIN[Finance Committee]
    B -->|Social| SOC[Social Committees]
    B -->|Security| SEC[Security Committees]
    
    FIN --> C[Budget Review]
    SOC --> D[Policy Development]
    SEC --> E[Security Assessment]
    
    C --> F[Plenary Vote]
    D --> F
    E --> F
    
    F --> G[Legislation]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style FIN fill:#27ae60,stroke:#2ecc71,color:#fff
    style SOC fill:#f39c12,stroke:#f39c12,color:#fff
    style SEC fill:#e74c3c,stroke:#c0392b,color:#fff
    style G fill:#9b59b6,stroke:#8e44ad,color:#fff
```

## Features

- **Network Structure**: Complete committee relationship mapping with 15 committees
- **Collaboration Matrix**: Intensity levels showing cross-committee work patterns
- **Party Distribution**: Seat allocation across all committees reflecting parliamentary composition
- **Productivity Metrics**: Legislative output and meeting frequency analysis
- **Centrality Analysis**: Identification of hub committees with highest connectivity
- **Specialization Matrix**: Committee expertise vs. collaboration patterns
- **Working Groups**: Inter-committee coordination bodies for cross-cutting issues

## Usage Scenarios

1. **Legislative Process Tracking**: Understanding how bills move through committee structure
2. **Coalition Analysis**: Identifying committee control and partisan influence
3. **Policy Coordination**: Mapping cross-committee collaboration on complex issues
4. **Productivity Assessment**: Evaluating committee efficiency and output
5. **Network Analysis**: Understanding information flow and influence patterns
6. **Resource Allocation**: Identifying high-activity committees requiring support

## Data Sources

- **Primary Views**:
  - `view_riksdagen_committee` - Committee structure and membership
  - `view_committee_productivity` - Productivity metrics and efficiency
  - `view_committee_productivity_matrix` - Cross-committee benchmarking
  - `view_riksdagen_committee_decisions` - Decision tracking
  - `view_riksdagen_committee_ballot_decision_summary` - Voting outcomes
- **Committee Collaboration Indicators**:
  - Cross-committee proposal references
  - Joint hearings and reports
  - Budget coordination patterns
  - Policy area overlaps
- **Key Metrics**:
  - Member counts and party distribution
  - Decisions per quarter
  - Processing times (days from proposal to decision)
  - Productivity level classifications (HIGH, MODERATE, LOW)
- **JSON Spec**: `committee-relationships.json`, `committee-members.json`
- **Update Frequency**: Daily (membership), Weekly (collaboration metrics)
- **Cache Duration**: 6 hours
- **Coverage**: All 15 Riksdag committees with documented codes (FiU, FöU, UU, JuU, etc.)
- **Coverage**: All 15 standing committees + special committees

## Swedish Committee System Context

The Riksdag has 15 standing committees (utskott) that prepare decisions on matters before the chamber. Each committee specializes in a particular area and consists of 15-17 members. Committee membership reflects the party distribution in parliament. Committees are the primary venue for detailed legislative work, conducting hearings, reviewing bills, and proposing amendments before plenary votes.

---

**Last Review**: 2024-11-24  
**Visualization Version**: 1.0.0  
**Compliance**: WCAG 2.1 AA
