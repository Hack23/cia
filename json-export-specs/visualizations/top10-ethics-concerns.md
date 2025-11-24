# Top 10 Ethics Concerns

**Last Updated**: 2024-11-24

## Overview

Politicians with highest ethics concern scores based on investigations, violations, conflicts of interest, and behavioral issues requiring accountability oversight.

## Ethics Concern Rankings

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Top 10 Ethics Concerns (Severity Score 0-100)"
    x-axis ["Pol A", "Pol B", "Pol C", "Pol D", "Pol E", "Pol F", "Pol G", "Pol H", "Pol I", "Pol J"]
    y-axis "Severity Score" 0 --> 100
    bar [92, 85, 78, 74, 71, 68, 64, 61, 58, 55]
```

## Violation Type Distribution

```mermaid
%%{init: {'theme':'base'}}%%
pie title "Ethics Violations by Category"
    "Conflict of Interest" : 28
    "Financial Disclosure" : 22
    "Misuse of Position" : 18
    "Harassment/Misconduct" : 15
    "Code of Conduct" : 12
    "Other" : 5
```

## Investigation Status

```mermaid
graph TB
    STATUS[Investigation Status] --> ACTIVE[Active Investigations]
    STATUS --> CLOSED[Closed Cases]
    STATUS --> PENDING[Pending Review]
    
    ACTIVE --> A1[5 politicians<br/>Ongoing inquiries<br/>Awaiting findings]
    ACTIVE --> A2[⚠️ High priority<br/>Media attention<br/>Potential sanctions]
    
    CLOSED --> C1[3 politicians<br/>Violations confirmed<br/>Sanctions applied]
    C1 --> C2[❌ Proven misconduct<br/>Reputational damage<br/>Electoral impact]
    
    PENDING --> P1[2 politicians<br/>Initial complaints<br/>Under review]
    P1 --> P2[�� Monitoring<br/>Evidence gathering<br/>Outcome uncertain]
    
    style STATUS fill:#3498db,stroke:#2980b9,color:#fff
    style ACTIVE fill:#f39c12,stroke:#f39c12,color:#fff
    style CLOSED fill:#e74c3c,stroke:#c0392b,color:#fff
    style PENDING fill:#95a5a6,stroke:#7f8c8d,color:#fff
```

## Severity Classification

```mermaid
graph LR
    A[Severity Levels] --> B[Critical 80-100]
    A --> C[High 60-80]
    A --> D[Moderate 40-60]
    A --> E[Minor <40]
    
    B --> B1[⛔ Resignation warranted<br/>Criminal referral<br/>Party expulsion risk]
    
    C --> C1[⚠️ Formal sanctions<br/>Committee removal<br/>Public censure]
    
    D --> D1[📊 Warning issued<br/>Corrective action<br/>Monitoring required]
    
    E --> E1[✅ Minor infraction<br/>Counseling<br/>No formal action]
    
    style A fill:#3498db,stroke:#2980b9,color:#fff
    style B fill:#e74c3c,stroke:#c0392b,color:#fff
    style C fill:#f39c12,stroke:#f39c12,color:#fff
    style D fill:#95a5a6,stroke:#7f8c8d,color:#fff
    style E fill:#27ae60,stroke:#2ecc71,color:#fff
```

## Conflict of Interest Network

```mermaid
graph TD
    COI[Conflicts of Interest] --> BUS[Business Interests]
    COI --> FAM[Family Connections]
    COI --> FIN[Financial Holdings]
    
    BUS --> B1[Board memberships<br/>Consulting roles<br/>Private sector ties]
    BUS --> B2[Policy influence<br/>Vote impact<br/>Disclosure violations]
    
    FAM --> F1[Spouse employment<br/>Relative contracts<br/>Nepotism concerns]
    FAM --> F2[Indirect benefits<br/>Appearance of impropriety<br/>Public trust issues]
    
    FIN --> FI1[Stock holdings<br/>Real estate<br/>Undisclosed assets]
    FI1 --> FI2[Voting conflicts<br/>Enrichment<br/>Transparency failures]
    
    style COI fill:#3498db,stroke:#2980b9,color:#fff
    style BUS fill:#e74c3c,stroke:#c0392b,color:#fff
    style FAM fill:#f39c12,stroke:#f39c12,color:#fff
    style FIN fill:#e74c3c,stroke:#c0392b,color:#fff
```

## Timeline of Incidents

```mermaid
gantt
    title Ethics Violations Timeline (2024)
    dateFormat YYYY-MM-DD
    section Critical Cases
    Pol A Financial scandal: 2024-02-15, 2024-11-24
    Pol B Misconduct: 2024-06-01, 2024-11-24
    section High Severity
    Pol C Conflict of interest: 2024-04-10, 2024-09-30
    Pol D Disclosure violation: 2024-07-15, 2024-11-24
    section Moderate
    Pol E Code breach: 2024-05-20, 2024-08-30
```

## Party Response Matrix

```mermaid
%%{init: {'theme':'base'}}%%
quadrantChart
    title Party Action vs Severity
    x-axis Weak Response --> Strong Response
    y-axis Low Severity --> High Severity
    quadrant-1 Appropriate Action
    quadrant-2 Inadequate Response
    quadrant-3 Appropriate Action
    quadrant-4 Overreaction
    
    Politician A: [0.7, 0.9]
    Politician B: [0.4, 0.85]
    Politician C: [0.6, 0.75]
    Politician D: [0.5, 0.7]
    Politician E: [0.65, 0.6]
```

## Impact on Public Trust

```mermaid
%%{init: {'theme':'base'}}%%
xychart-beta
    title "Public Trust in Politicians (% trust)"
    x-axis ["No violations", "Minor", "Moderate", "High", "Critical"]
    y-axis "Trust %" 0 --> 100
    bar [68, 52, 38, 22, 12]
```

## Sanctions Applied

```mermaid
graph LR
    SANCTIONS[Disciplinary Actions] --> FORMAL[Formal Sanctions]
    SANCTIONS --> INFORMAL[Informal Measures]
    SANCTIONS --> NONE[No Action]
    
    FORMAL --> F1[Committee removal: 3<br/>Public censure: 2<br/>Suspension: 1]
    
    INFORMAL --> I1[Private warning: 5<br/>Party pressure: 4<br/>Media statements: 3]
    
    NONE --> N1[Under investigation: 2<br/>Insufficient evidence: 1<br/>Statute limitations: 0]
    
    style SANCTIONS fill:#3498db,stroke:#2980b9,color:#fff
    style FORMAL fill:#e74c3c,stroke:#c0392b,color:#fff
    style INFORMAL fill:#f39c12,stroke:#f39c12,color:#fff
    style NONE fill:#95a5a6,stroke:#7f8c8d,color:#fff
```

## Recidivism Analysis

```mermaid
graph TB
    REPEAT[Repeat Offenders] --> FIRST[First Violation]
    REPEAT --> SECOND[Second Violation]
    REPEAT --> MULTIPLE[Multiple Violations]
    
    FIRST --> F1[7 politicians<br/>Learning opportunity<br/>Corrective path]
    
    SECOND --> S1[2 politicians<br/>Pattern emerging<br/>Enhanced scrutiny]
    
    MULTIPLE --> M1[1 politician<br/>Systemic problem<br/>Removal recommended]
    
    style REPEAT fill:#3498db,stroke:#2980b9,color:#fff
    style FIRST fill:#f39c12,stroke:#f39c12,color:#fff
    style SECOND fill:#e74c3c,stroke:#c0392b,color:#fff
    style MULTIPLE fill:#e74c3c,stroke:#c0392b,color:#fff
```

## Electoral Consequences

```mermaid
graph TD
    ELECTORAL[Electoral Impact] --> LOSS[Seat Loss Risk]
    ELECTORAL --> PRIMARY[Primary Challenge]
    ELECTORAL --> PARTY[Party Abandonment]
    
    LOSS --> L1[High severity cases<br/>Voter rejection<br/>Unwinnable]
    
    PRIMARY --> P1[Moderate cases<br/>Within-party challenge<br/>New candidate]
    
    PARTY --> PA1[Critical cases<br/>Endorsement withdrawn<br/>Independent run only]
    
    L1 --> OUTCOME[2026 Impact]
    P1 --> OUTCOME
    PA1 --> OUTCOME
    
    OUTCOME --> O1[3-5 seats at risk<br/>Party reputation damage<br/>Reform pressure]
    
    style ELECTORAL fill:#3498db,stroke:#2980b9,color:#fff
    style LOSS fill:#e74c3c,stroke:#c0392b,color:#fff
    style PRIMARY fill:#f39c12,stroke:#f39c12,color:#fff
    style PARTY fill:#e74c3c,stroke:#c0392b,color:#fff
```

## Whistleblower Sources

```mermaid
%%{init: {'theme':'base'}}%%
pie title "How Violations Were Discovered"
    "Media Investigation" : 35
    "Internal Complaint" : 28
    "Public Report" : 20
    "Anonymous Tip" : 12
    "Audit Finding" : 5
```

## Features

- **Severity Rankings**: Top 10 politicians by ethics concern score
- **Violation Types**: Comprehensive categorization of misconduct
- **Investigation Tracking**: Status of active, closed, and pending cases
- **Conflict Analysis**: Network mapping of interests and relationships
- **Sanctions Record**: Documentation of disciplinary actions
- **Recidivism Monitoring**: Tracking of repeat offenders
- **Electoral Impact**: Assessment of political consequences
- **Public Trust**: Impact on voter confidence metrics

## Usage Scenarios

1. **Accountability Journalism**: Ethics violation reporting and investigation
2. **Voter Information**: Informed voting decisions on candidate integrity
3. **Party Oversight**: Internal discipline and candidate screening
4. **Electoral Strategy**: Opposition research and campaign messaging
5. **Public Trust Monitoring**: Democratic health assessment
6. **Reform Advocacy**: Evidence for ethics rule strengthening

## Data Sources

- **Source**: `view_riksdagen_politician`, ethics committee records, media reports
- **Violation Database**: 45 documented cases (2022-2024)
- **Update Frequency**: Weekly (active cases), As-needed (new violations)
- **Verification**: Multiple source corroboration required
- **Privacy**: Public information only, no unsubstantiated allegations

## Ethics Framework

**Swedish Parliamentary Code of Conduct:**
- Conflict of interest disclosure
- Financial transparency requirements
- Professional behavior standards
- Public resource usage rules
- Lobbying interaction limits

**Enforcement Mechanisms:**
- Parliamentary ethics committee
- Party disciplinary procedures
- Media and public scrutiny
- Electoral accountability
- Legal prosecution (severe cases)

---

**Last Review**: 2024-11-24  
**Visualization Version**: 1.0.0  
**Compliance**: WCAG 2.1 AA
