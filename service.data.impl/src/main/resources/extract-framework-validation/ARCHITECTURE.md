# Framework Validation Architecture

## System Overview

This document provides visual representations of the refactored Framework Validation Data Extraction system architecture, data flow, and test coverage.

## Architecture Diagram

```mermaid
graph TB
    subgraph "Main Orchestrator"
        A[extract-framework-validation-data.sql]
    end
    
    subgraph "Configuration Layer"
        B[config.sql<br/>45+ Parameters]
        C[helpers.sql<br/>11 Functions]
    end
    
    subgraph "Framework Test Modules"
        D[temporal.sql<br/>7 Tests]
        E[comparative.sql<br/>4 Tests]
        F[pattern.sql<br/>2 Tests]
        G[predictive.sql<br/>4 Tests]
        H[network.sql<br/>2 Tests]
        I[decision.sql<br/>2 Tests]
    end
    
    subgraph "Database Views"
        J[(v1.40-v1.61<br/>Advanced Views)]
    end
    
    subgraph "Output"
        K[CSV Files<br/>Framework Validation]
    end
    
    A --> B
    A --> C
    A --> D
    A --> E
    A --> F
    A --> G
    A --> H
    A --> I
    
    B -.-> D
    B -.-> E
    B -.-> F
    B -.-> G
    B -.-> H
    B -.-> I
    
    C -.-> D
    C -.-> E
    C -.-> F
    C -.-> G
    C -.-> H
    C -.-> I
    
    D --> J
    E --> J
    F --> J
    G --> J
    H --> J
    I --> J
    
    D --> K
    E --> K
    F --> K
    G --> K
    H --> K
    I --> K
    
    style A fill:#e1f5ff,stroke:#01579b,stroke-width:3px
    style B fill:#fff3e0,stroke:#e65100,stroke-width:2px
    style C fill:#fff3e0,stroke:#e65100,stroke-width:2px
    style D fill:#e8f5e9,stroke:#2e7d32
    style E fill:#e8f5e9,stroke:#2e7d32
    style F fill:#e8f5e9,stroke:#2e7d32
    style G fill:#e8f5e9,stroke:#2e7d32
    style H fill:#e8f5e9,stroke:#2e7d32
    style I fill:#e8f5e9,stroke:#2e7d32
    style J fill:#f3e5f5,stroke:#4a148c
    style K fill:#fce4ec,stroke:#880e4f
```

## Data Flow

```mermaid
flowchart LR
    subgraph "Input"
        A[PostgreSQL Database]
        B[Advanced Views<br/>v1.40-v1.61]
    end
    
    subgraph "Processing"
        C[Configuration<br/>Parameters]
        D[Helper<br/>Functions]
        E[Framework<br/>Modules]
    end
    
    subgraph "Validation"
        F[Trend Classification]
        G[Performance Tiers]
        H[Risk Assessment]
        I[Anomaly Detection]
    end
    
    subgraph "Output"
        J[Temporal Analysis<br/>7 CSV Files]
        K[Comparative Analysis<br/>4 CSV Files]
        L[Pattern Recognition<br/>2 CSV Files]
        M[Predictive Intelligence<br/>4 CSV Files]
        N[Network Analysis<br/>2 CSV Files]
        O[Decision Intelligence<br/>2 CSV Files]
    end
    
    A --> B
    B --> E
    C --> E
    D --> E
    
    E --> F
    E --> G
    E --> H
    E --> I
    
    F --> J
    F --> K
    F --> L
    F --> M
    F --> N
    F --> O
    
    G --> J
    G --> K
    G --> L
    G --> M
    G --> N
    G --> O
    
    H --> J
    H --> K
    H --> L
    H --> M
    H --> N
    H --> O
    
    I --> J
    I --> K
    I --> L
    I --> M
    I --> N
    I --> O
    
    style A fill:#e1f5ff,stroke:#01579b
    style B fill:#f3e5f5,stroke:#4a148c
    style C fill:#fff3e0,stroke:#e65100
    style D fill:#fff3e0,stroke:#e65100
    style E fill:#e8f5e9,stroke:#2e7d32
    style F fill:#fff9c4,stroke:#f57f17
    style G fill:#fff9c4,stroke:#f57f17
    style H fill:#fff9c4,stroke:#f57f17
    style I fill:#fff9c4,stroke:#f57f17
    style J fill:#fce4ec,stroke:#880e4f
    style K fill:#fce4ec,stroke:#880e4f
    style L fill:#fce4ec,stroke:#880e4f
    style M fill:#fce4ec,stroke:#880e4f
    style N fill:#fce4ec,stroke:#880e4f
    style O fill:#fce4ec,stroke:#880e4f
```

## Test Coverage Matrix

```mermaid
graph TB
    subgraph "Framework Coverage"
        A[Framework 1: Temporal<br/>7 Test Cases]
        B[Framework 2: Comparative<br/>4 Test Cases]
        C[Framework 3: Pattern<br/>2 Test Cases]
        D[Framework 4: Predictive<br/>4 Test Cases]
        E[Framework 5: Network<br/>2 Test Cases]
        F[Framework 6: Decision<br/>2 Test Cases]
    end
    
    subgraph "Advanced Views (v1.58-v1.61)"
        G[Election Proximity]
        H[Election Year Patterns]
        I[Party Longitudinal]
        J[Pre-Election Activity]
        K[Career Path 10-Level]
        L[Seasonal Patterns]
    end
    
    A --> G
    A --> H
    B --> I
    D --> J
    
    style A fill:#4caf50,stroke:#2e7d32,stroke-width:3px
    style B fill:#4caf50,stroke:#2e7d32,stroke-width:3px
    style C fill:#ffeb3b,stroke:#f57f17,stroke-width:2px
    style D fill:#4caf50,stroke:#2e7d32,stroke-width:3px
    style E fill:#ffeb3b,stroke:#f57f17,stroke-width:2px
    style F fill:#ffeb3b,stroke:#f57f17,stroke-width:2px
    style G fill:#e1f5ff,stroke:#01579b
    style H fill:#e1f5ff,stroke:#01579b
    style I fill:#e1f5ff,stroke:#01579b
    style J fill:#e1f5ff,stroke:#01579b
    style K fill:#ffcdd2,stroke:#c62828
    style L fill:#ffcdd2,stroke:#c62828
```

**Legend:**
- 🟢 **Green**: Comprehensive coverage (4+ tests)
- 🟡 **Yellow**: Basic coverage (2-3 tests)
- 🔵 **Blue**: View covered in tests
- 🔴 **Red**: View not yet covered

## Helper Function Dependencies

```mermaid
graph LR
    subgraph "Configuration Functions"
        A[cia_get_config_value]
        B[cia_get_election_years]
        C[cia_get_lookback_interval]
    end
    
    subgraph "Classification Functions"
        D[cia_calculate_temporal_trend]
        E[cia_classify_performance]
        F[cia_classify_behavioral_cluster]
        G[cia_classify_risk_level]
        H[cia_classify_rebellion_pattern]
        I[cia_classify_anomaly]
        J[cia_classify_coalition_alignment]
    end
    
    subgraph "Validation Functions"
        K[cia_validate_result_count]
    end
    
    subgraph "Test Modules"
        L[Temporal Tests]
        M[Comparative Tests]
        N[Pattern Tests]
        O[Predictive Tests]
        P[Network Tests]
        Q[Decision Tests]
    end
    
    A --> D
    A --> E
    A --> F
    A --> G
    A --> H
    A --> I
    A --> J
    
    D --> L
    D --> M
    E --> M
    F --> N
    G --> N
    H --> N
    I --> L
    J --> O
    
    B --> L
    C --> L
    C --> M
    C --> O
    
    K --> L
    K --> M
    K --> N
    K --> O
    K --> P
    K --> Q
    
    style A fill:#fff3e0,stroke:#e65100,stroke-width:2px
    style B fill:#fff3e0,stroke:#e65100,stroke-width:2px
    style C fill:#fff3e0,stroke:#e65100,stroke-width:2px
    style D fill:#e3f2fd,stroke:#1565c0
    style E fill:#e3f2fd,stroke:#1565c0
    style F fill:#e3f2fd,stroke:#1565c0
    style G fill:#e3f2fd,stroke:#1565c0
    style H fill:#e3f2fd,stroke:#1565c0
    style I fill:#e3f2fd,stroke:#1565c0
    style J fill:#e3f2fd,stroke:#1565c0
    style K fill:#f1f8e9,stroke:#558b2f
    style L fill:#fce4ec,stroke:#880e4f
    style M fill:#fce4ec,stroke:#880e4f
    style N fill:#fce4ec,stroke:#880e4f
    style O fill:#fce4ec,stroke:#880e4f
    style P fill:#fce4ec,stroke:#880e4f
    style Q fill:#fce4ec,stroke:#880e4f
```

## Execution Flow

```mermaid
sequenceDiagram
    participant U as User/psql
    participant M as Main Script
    participant C as Config Module
    participant H as Helpers Module
    participant T as Test Modules
    participant D as Database
    participant O as CSV Output
    
    U->>M: Execute extract-framework-validation-data.sql
    M->>C: Load config.sql
    C->>D: CREATE TEMP TABLE framework_validation_config
    C->>D: INSERT 45+ config parameters
    C-->>M: Configuration loaded
    
    M->>H: Load helpers.sql
    H->>D: CREATE 11 helper functions
    H-->>M: Helpers loaded
    
    M->>T: Execute temporal.sql (7 tests)
    T->>D: Query advanced views + apply helpers
    D-->>T: Result sets
    T->>O: Export 7 CSV files
    T-->>M: Temporal complete
    
    M->>T: Execute comparative.sql (4 tests)
    T->>D: Query advanced views + apply helpers
    D-->>T: Result sets
    T->>O: Export 4 CSV files
    T-->>M: Comparative complete
    
    M->>T: Execute pattern.sql (2 tests)
    T->>D: Query advanced views + apply helpers
    D-->>T: Result sets
    T->>O: Export 2 CSV files
    T-->>M: Pattern complete
    
    M->>T: Execute predictive.sql (4 tests)
    T->>D: Query advanced views + apply helpers
    D-->>T: Result sets
    T->>O: Export 4 CSV files
    T-->>M: Predictive complete
    
    M->>T: Execute network.sql (2 tests)
    T->>D: Query advanced views + apply helpers
    D-->>T: Result sets
    T->>O: Export 2 CSV files
    T-->>M: Network complete
    
    M->>T: Execute decision.sql (2 tests)
    T->>D: Query advanced views + apply helpers
    D-->>T: Result sets
    T->>O: Export 2 CSV files
    T-->>M: Decision complete
    
    M->>D: Generate validation catalog
    M->>O: Export catalog CSV
    M-->>U: Execution complete
```

## Module Interaction Pattern

```mermaid
graph TD
    A[Test Module] -->|1. Read| B[Config Table]
    A -->|2. Call| C[Helper Functions]
    C -->|3. Query| D[Advanced Views]
    C -->|4. Apply| E[Classification Logic]
    E -->|5. Return| A
    A -->|6. Export| F[CSV File]
    
    style A fill:#e8f5e9,stroke:#2e7d32,stroke-width:2px
    style B fill:#fff3e0,stroke:#e65100
    style C fill:#fff3e0,stroke:#e65100
    style D fill:#f3e5f5,stroke:#4a148c
    style E fill:#fff9c4,stroke:#f57f17
    style F fill:#fce4ec,stroke:#880e4f
```

## Quick Reference

### Test Count by Framework
- **Temporal Analysis**: 7 tests (including 2 new election-related tests)
- **Comparative Analysis**: 4 tests (including 1 new longitudinal test)
- **Pattern Recognition**: 2 tests
- **Predictive Intelligence**: 4 tests (including 1 new pre-election test)
- **Network Analysis**: 2 tests
- **Decision Intelligence**: 2 tests

**Total**: 21 test cases (increased from 18)

### Coverage Statistics
- **Total CSV Files**: 21
- **Helper Functions**: 11
- **Config Parameters**: 45+
- **Advanced Views Covered**: 13 (9 original + 4 new)
- **Views from v1.58-v1.61**: 11 of 11 covered (100%) ✅

### New Test Cases (v1.58-v1.61 Coverage - 100% Complete)

**Phase 1 Tests:**
1. **Test 1.5**: Election Proximity Trends - `view_riksdagen_election_proximity_trends`
2. **Test 1.6**: Election Year Patterns - `view_riksdagen_election_year_behavioral_patterns`
3. **Test 2.4**: Party Longitudinal Performance - `view_riksdagen_party_longitudinal_performance`
4. **Test 4.3**: Pre-Election Activity - `view_riksdagen_pre_election_quarterly_activity`

**Phase 2 Tests:**
5. **Test 1.7**: Election Year Anomalies - `view_riksdagen_election_year_anomalies`
6. **Test 1.8**: Election vs Midterm Comparison - `view_riksdagen_election_year_vs_midterm`
7. **Test 1.9**: Seasonal Activity Patterns - `view_riksdagen_seasonal_activity_patterns`
8. **Test 2.5**: Party Coalition Evolution - `view_riksdagen_party_coalition_evolution`
9. **Test 2.6**: Party Electoral Trends - `view_riksdagen_party_electoral_trends`
10. **Test 2.7**: Party Summary - `view_riksdagen_party_summary`
11. **Test 3.3**: Career Path Classification - `view_riksdagen_politician_career_path_10level`
