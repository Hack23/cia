# Government Cabinet Visualization

**Last Updated**: 2024-11-24

## Overview

Comprehensive visualization of Swedish government cabinet structure, ministerial composition, coalition dynamics, and cabinet performance metrics.

## Cabinet Organizational Structure

```mermaid
graph TD
    PM[Prime Minister<br/>Ulf Kristersson M<br/>Head of Government]
    
    subgraph "Core Ministries"
        FINANCE[Finance Minister<br/>Elisabeth Svantesson M<br/>Economic policy]
        FOREIGN[Foreign Minister<br/>Tobias Billström M<br/>International relations]
        DEFENSE[Defense Minister<br/>Pål Jonson M<br/>Military affairs]
        JUSTICE[Justice Minister<br/>Gunnar Strömmer M<br/>Legal affairs]
    end
    
    subgraph "Social Ministries"
        HEALTH[Health Minister<br/>Acko Ankarberg M<br/>Healthcare policy]
        EDUCATION[Education Minister<br/>Lotta Edholm L<br/>Schools policy]
        SOCIAL[Social Affairs<br/>Jakob Forssmed KD<br/>Social services]
        INTEGRATION[Integration Minister<br/>Maria Malmer Stenergard M<br/>Migration policy]
    end
    
    subgraph "Infrastructure & Economy"
        ENERGY[Energy Minister<br/>Ebba Busch KD<br/>Deputy PM]
        CLIMATE[Climate Minister<br/>Romina Pourmokhtari L<br/>Environment policy]
        TRANSPORT[Infrastructure<br/>Andreas Carlson KD<br/>Transport policy]
        BUSINESS[Business Minister<br/>Johan Forssell M<br/>Industry policy]
    end
    
    subgraph "Culture & Development"
        CULTURE[Culture Minister<br/>Parisa Liljestrand M<br/>Cultural affairs]
        RURAL[Rural Affairs<br/>Peter Kullgren KD<br/>Agriculture]
        HOUSING[Housing Minister<br/>Johan Forssell M<br/>Construction]
    end
    
    PM --> FINANCE
    PM --> FOREIGN
    PM --> DEFENSE
    PM --> JUSTICE
    PM --> HEALTH
    PM --> EDUCATION
    PM --> SOCIAL
    PM --> INTEGRATION
    PM --> ENERGY
    PM --> CLIMATE
    PM --> TRANSPORT
    PM --> BUSINESS
    PM --> CULTURE
    PM --> RURAL
    PM --> HOUSING
    
    style PM fill:#003366,stroke:#003366,color:#fff
    style FINANCE fill:#27ae60,stroke:#27ae60,color:#fff
    style FOREIGN fill:#3498db,stroke:#3498db,color:#fff
    style DEFENSE fill:#e74c3c,stroke:#e74c3c,color:#fff
    style ENERGY fill:#f39c12,stroke:#f39c12,color:#fff
```

## Cabinet Party Distribution

```mermaid
%%{init: {'theme':'base'}}%%
pie title "Cabinet Ministerial Positions by Party"
    "Moderates (M)" : 11
    "Christian Democrats (KD)" : 5
    "Liberals (L)" : 4
```

## Coalition Structure

```mermaid
graph TB
    GOV[Government Coalition<br/>107 seats<br/>Minority government]
    
    GOV --> M[Moderates M<br/>68 seats<br/>Lead party]
    GOV --> KD[Christian Democrats KD<br/>19 seats<br/>Coalition partner]
    GOV --> L[Liberals L<br/>16 seats<br/>Coalition partner]
    
    SUPP[Support Agreement<br/>Tidö Agreement]
    SUPP --> SD[Sweden Democrats SD<br/>73 seats<br/>External support]
    
    M --> M1[Prime Minister<br/>Most ministers<br/>Economic policy lead]
    KD --> KD1[Deputy PM<br/>Energy & rural<br/>Social affairs]
    L --> L1[Education & climate<br/>Liberal reforms<br/>Green transition]
    
    SD --> SD1[Support on budget<br/>No cabinet posts<br/>Influence on policy]
    
    OPP[Opposition<br/>169 seats<br/>Majority in parliament]
    OPP --> S[Social Democrats 107]
    OPP --> V[Left Party 24]
    OPP --> C[Center Party 24]
    OPP --> MP[Green Party 16]
    
    style GOV fill:#52BDEC,stroke:#2980b9,color:#fff
    style M fill:#52BDEC,stroke:#2980b9,color:#fff
    style KD fill:#000077,stroke:#000077,color:#fff
    style L fill:#006AB3,stroke:#006AB3,color:#fff
    style SUPP fill:#DDDD00,stroke:#f39c12
    style SD fill:#DDDD00,stroke:#f39c12
    style OPP fill:#E8112d,stroke:#c0392b,color:#fff
```

## Coalition Stability Index

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Coalition Stability Metrics (0-100 scale)"
    x-axis ["Budget Agreement", "Policy Alignment", "Public Support", "Internal Cohesion", "Media Relations", "Overall"]
    y-axis "Score" 0 --> 100
    bar [85, 72, 58, 75, 64, 71]
```

## Ministerial Performance Scorecard

```mermaid
graph TB
    PERF[Cabinet Performance] --> A[Budget Execution]
    PERF --> B[Legislative Success]
    PERF --> C[Public Approval]
    PERF --> D[Crisis Management]
    
    A --> A1[95% on track<br/>Fiscal discipline maintained]
    B --> B1[68% passage rate<br/>Some opposition delays]
    C --> C1[42% approval<br/>Below historical average]
    D --> D1[Mixed response<br/>Energy crisis handling]
    
    A1 --> R1{Strong}
    B1 --> R2{Moderate}
    C1 --> R3{Weak}
    D1 --> R4{Mixed}
    
    style PERF fill:#3498db,stroke:#2980b9,color:#fff
    style A fill:#27ae60,stroke:#2ecc71,color:#fff
    style B fill:#f39c12,stroke:#f39c12,color:#fff
    style C fill:#e74c3c,stroke:#c0392b,color:#fff
    style D fill:#95a5a6,stroke:#7f8c8d,color:#fff
```

## Policy Priority Matrix

```mermaid
%%{init: {'theme':'base'}}%%
quadrantChart
    title Government Policy Priorities (Impact vs Progress)
    x-axis Low Progress --> High Progress
    y-axis Low Impact --> High Impact
    quadrant-1 Success Stories
    quadrant-2 Aspirational Goals
    quadrant-3 Low Priority
    quadrant-4 Quick Wins
    
    Crime Reduction: [0.6, 0.9]
    Energy Security: [0.7, 0.95]
    School Reform: [0.5, 0.85]
    Healthcare Efficiency: [0.45, 0.8]
    Climate Goals: [0.4, 0.7]
    Integration Policy: [0.55, 0.75]
    Defense Spending: [0.8, 0.85]
    Tax Reform: [0.65, 0.6]
```

## Cabinet Timeline

```mermaid
gantt
    title Government Formation and Key Events
    dateFormat YYYY-MM-DD
    section Formation
    Election Day: milestone, 2022-09-11, 0d
    Coalition Talks: 2022-09-12, 2022-10-13
    Tidö Agreement: milestone, 2022-10-14, 0d
    Government Formed: milestone, 2022-10-18, 0d
    section Major Events
    Budget 2023: milestone, 2023-01-01, 0d
    NATO Accession: milestone, 2024-03-07, 0d
    Budget 2024: milestone, 2024-01-01, 0d
    section Current
    Current Term: 2024-01-01, 2026-09-13
    Next Election: milestone, 2026-09-13, 0d
```

## Ministerial Workload Distribution

```mermaid
graph LR
    A[Cabinet Responsibilities] --> B[Heavy Workload]
    A --> C[Moderate Workload]
    A --> D[Light Workload]
    
    B --> B1[Finance<br/>Justice<br/>Health<br/>Education]
    B --> B2[High media attention<br/>Complex portfolios<br/>Major reforms]
    
    C --> C1[Foreign<br/>Defense<br/>Business<br/>Social]
    C --> C2[Steady flow<br/>Regular tasks<br/>Ongoing issues]
    
    D --> D1[Culture<br/>Rural<br/>Housing]
    D --> D2[Specialized focus<br/>Lower visibility<br/>Stable policy]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#e74c3c,stroke:#c0392b,color:#fff
    style C fill:#f39c12,stroke:#f39c12,color:#fff
    style D fill:#27ae60,stroke:#2ecc71,color:#fff
```

## Coalition Agreement Implementation

```mermaid
graph TB
    TIDO[Tidö Agreement<br/>73-point program] --> POL[Policy Areas]
    
    POL --> CRIME[Crime & Justice<br/>18 points]
    POL --> ENERGY[Energy & Climate<br/>12 points]
    POL --> WELFARE[Welfare & Health<br/>15 points]
    POL --> EDU[Education<br/>10 points]
    POL --> ECO[Economy & Tax<br/>18 points]
    
    CRIME --> C1[Implemented: 12<br/>Progress: 67%]
    ENERGY --> E1[Implemented: 8<br/>Progress: 67%]
    WELFARE --> W1[Implemented: 9<br/>Progress: 60%]
    EDU --> ED1[Implemented: 6<br/>Progress: 60%]
    ECO --> EC1[Implemented: 11<br/>Progress: 61%]
    
    C1 --> STATUS1[On track]
    E1 --> STATUS2[On track]
    W1 --> STATUS3[Behind schedule]
    ED1 --> STATUS4[Behind schedule]
    EC1 --> STATUS5[On track]
    
    style TIDO fill:#3498db,stroke:#2980b9,color:#fff
    style CRIME fill:#27ae60,stroke:#2ecc71,color:#fff
    style ENERGY fill:#27ae60,stroke:#2ecc71,color:#fff
    style WELFARE fill:#f39c12,stroke:#f39c12,color:#fff
    style EDU fill:#f39c12,stroke:#f39c12,color:#fff
    style ECO fill:#27ae60,stroke:#2ecc71,color:#fff
```

## Inter-Ministerial Coordination

```mermaid
graph TD
    A[Government Coordination] --> B[Weekly Cabinet]
    A --> C[Ministry Groups]
    A --> D[PM Office]
    
    B --> B1[Full Cabinet Meeting<br/>All ministers<br/>Policy decisions]
    
    C --> C1[Economic Group<br/>Finance + Business + Energy]
    C --> C2[Social Group<br/>Health + Social + Integration]
    C --> C3[Security Group<br/>Defense + Foreign + Justice]
    
    D --> D1[Central Coordination<br/>Policy alignment<br/>Communication strategy]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#52BDEC,stroke:#2980b9,color:#fff
    style C fill:#f39c12,stroke:#f39c12,color:#fff
    style D fill:#9b59b6,stroke:#8e44ad,color:#fff
```

## Cabinet Approval Trends

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Government Approval Rating (% approve)"
    x-axis ["Nov 2022", "Jan 2023", "Apr 2023", "Jul 2023", "Oct 2023", "Jan 2024", "Apr 2024", "Jul 2024", "Oct 2024"]
    y-axis "Approval %" 0 --> 100
    line [52, 48, 45, 43, 41, 40, 39, 42, 42]
```

## Cabinet-Parliament Relations

```mermaid
graph LR
    CAB[Cabinet<br/>107 seats] --> PARL{Parliament<br/>349 seats}
    
    SUPP[SD Support<br/>73 seats] --> PARL
    
    PARL --> MAJ{Majority?}
    
    MAJ -->|With SD: Yes| PASS[180 seats<br/>Legislation passes]
    MAJ -->|Without SD: No| BLOCK[169 opposition<br/>May block]
    
    PASS --> P1[Budget approved<br/>Key reforms pass]
    BLOCK --> B1[Need negotiations<br/>Seek cross-aisle support]
    
    style CAB fill:#52BDEC,stroke:#2980b9,color:#fff
    style SUPP fill:#DDDD00,stroke:#f39c12
    style PASS fill:#27ae60,stroke:#2ecc71,color:#fff
    style BLOCK fill:#e74c3c,stroke:#c0392b,color:#fff
```

## Risk Assessment

```mermaid
graph TB
    RISK[Cabinet Risk Factors] --> R1[Coalition Tensions]
    RISK --> R2[SD Dependency]
    RISK --> R3[Public Approval]
    RISK --> R4[Policy Delivery]
    
    R1 --> R1A[Level: Moderate<br/>L-KD differences on climate<br/>Managing divergent views]
    
    R2 --> R2A[Level: High<br/>Reliant on SD support<br/>Vulnerable to withdrawal]
    
    R3 --> R3A[Level: Moderate<br/>Below 50% approval<br/>Economic concerns]
    
    R4 --> R4A[Level: Moderate<br/>Mixed implementation<br/>Some delays on reforms]
    
    style RISK fill:#3498db,stroke:#2980b9,color:#fff
    style R1 fill:#f39c12,stroke:#f39c12,color:#fff
    style R2 fill:#e74c3c,stroke:#c0392b,color:#fff
    style R3 fill:#f39c12,stroke:#f39c12,color:#fff
    style R4 fill:#f39c12,stroke:#f39c12,color:#fff
```

## Features

- **Organizational Chart**: Complete cabinet structure with all ministerial positions
- **Coalition Composition**: Party distribution and power-sharing arrangements
- **Stability Metrics**: Multi-dimensional assessment of coalition health
- **Performance Tracking**: Key performance indicators for cabinet effectiveness
- **Policy Progress**: Implementation status of coalition agreement commitments
- **Approval Trends**: Public opinion tracking over government term
- **Risk Analysis**: Identification of vulnerabilities and challenges

## Usage Scenarios

1. **Government Monitoring**: Track cabinet composition and ministerial changes
2. **Coalition Analysis**: Understand power dynamics and stability factors
3. **Policy Tracking**: Monitor implementation of government program
4. **Performance Assessment**: Evaluate effectiveness across policy areas
5. **Risk Forecasting**: Identify potential coalition stress points
6. **Media Reporting**: Quick reference for government structure and status

## Data Sources

- **Source**: `view_riksdagen_goverment`, `view_riksdagen_minister`
- **JSON Spec**: `government-composition.json`, `minister-profiles.json`
- **Update Frequency**: As government changes (major updates), Weekly (metrics)
- **Cache Duration**: 24 hours
- **Coverage**: Current government (formed October 2022)

## Swedish Government Context

Sweden operates under a parliamentary system where the Prime Minister leads a cabinet appointed with parliament's approval. The current minority government (Moderates, Christian Democrats, Liberals) holds 107 of 349 seats and relies on external support from the Sweden Democrats (73 seats) through the Tidö Agreement. This unique arrangement allows the coalition to command a working majority while maintaining SD outside the formal government structure.

---

**Last Review**: 2024-11-24  
**Visualization Version**: 1.0.0  
**Compliance**: WCAG 2.1 AA
