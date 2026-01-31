---
name: c4-architecture-documentation
description: Document system architecture using C4 model: context, container, component, code diagrams per ARCHITECTURE.md
license: Apache-2.0
---

# C4 Architecture Documentation Skill

## Purpose
Document CIA platform architecture using C4 model for different abstraction levels.

## When to Use
- ✅ Documenting system architecture
- ✅ Onboarding new developers
- ✅ Architecture decision records
- ✅ Security architecture reviews

## C4 Model Levels

### Level 1: System Context
```
[External Users] --uses--> [CIA Platform] --uses--> [Riksdagen API]
[CIA Platform] --uses--> [World Bank API]
[CIA Platform] --uses--> [PostgreSQL Database]
```

### Level 2: Container Diagram
```
[Web Browser] --HTTPS--> [Spring Boot App] --JDBC--> [PostgreSQL]
[Spring Boot App] --HTTPS--> [External APIs]
[Spring Boot App] --uses--> [Redis Cache]
```

### Level 3: Component Diagram
```java
// Service Layer Components
@Component PoliticianService --uses--> @Repository PoliticianRepository
@Component VotingAnalysisService --uses--> @Repository VotingRecordRepository
@Component RiksdagenClient --calls--> [External Riksdagen API]
```

### Level 4: Code
```java
// Detailed class design
public class PoliticianService {
    private final PoliticianRepository repository;
    private final RiksdagenClient riksdagenClient;
    // Implementation details...
}
```

## PlantUML Diagrams
```plantuml
@startuml
!include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Container.puml

Person(user, "Citizen", "Wants political transparency")
System_Boundary(cia, "CIA Platform") {
    Container(web, "Web Application", "Spring Boot, Vaadin", "Provides UI")
    ContainerDb(db, "Database", "PostgreSQL", "Stores data")
}
System_Ext(riksdagen, "Riksdagen API", "Government data")

Rel(user, web, "Uses", "HTTPS")
Rel(web, db, "Reads/Writes", "JDBC")
Rel(web, riksdagen, "Fetches data", "HTTPS/REST")
@enduml
```

## References
- C4 Model: https://c4model.com/
- ARCHITECTURE.md
- FUTURE_ARCHITECTURE.md