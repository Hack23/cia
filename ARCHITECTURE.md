<p align="center">
  <img src="https://hack23.com/icon-192.png" alt="Hack23 Logo" width="192" height="192">
</p>

<h1 align="center">🏛️ Citizen Intelligence Agency — Architecture</h1>

<p align="center">
  <strong>📐 C4 Model Architectural Documentation for Democratic Transparency</strong><br>
  <em>🎯 Comprehensive System Design from Context to Component Level</em>
</p>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/Owner-CEO-0A66C2?style=for-the-badge" alt="Owner"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Version-1.1-555?style=for-the-badge" alt="Version"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Effective-2026--04--20-success?style=for-the-badge" alt="Effective Date"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Review-Annual-orange?style=for-the-badge" alt="Review Cycle"/></a>
</p>

**📋 Document Owner:** CEO | **📄 Version:** 1.1 | **📅 Last Updated:** 2026-04-20 (UTC)  
**🔄 Review Cycle:** Annual | **⏰ Next Review:** 2027-04-20
**🏷️ Classification:** Public (Open Civic Transparency Platform)

---

## 🎯 Purpose

This document serves as the primary entry point for the Citizen Intelligence Agency's architectural documentation. It provides a comprehensive view of the system's design using the C4 model approach, starting from a high-level system context and drilling down to component interactions.

## 📚 Architecture Documentation Map

<div class="documentation-map">

| Document                                            | Focus           | Description                               | Documentation Link                                                              |
| --------------------------------------------------- | --------------- | ----------------------------------------- | ------------------------------------------------------------------------------- |
| **[Architecture](ARCHITECTURE.md)**                 | 🏛️ Architecture | C4 model showing current system structure | [View Source](https://github.com/Hack23/cia/blob/master/ARCHITECTURE.md)         |
| **[Future Architecture](FUTURE_ARCHITECTURE.md)**   | 🏛️ Architecture | C4 model showing future system structure | [View Source](https://github.com/Hack23/cia/blob/master/FUTURE_ARCHITECTURE.md)         |
| **[Mindmaps](MINDMAP.md)**                          | 🧠 Concept      | Current system component relationships    | [View Source](https://github.com/Hack23/cia/blob/master/MINDMAP.md)             |
| **[Future Mindmaps](FUTURE_MINDMAP.md)**            | 🧠 Concept      | Future capability evolution               | [View Source](https://github.com/Hack23/cia/blob/master/FUTURE_MINDMAP.md)      |
| **[SWOT Analysis](SWOT.md)**                        | 💼 Business     | Current strategic assessment              | [View Source](https://github.com/Hack23/cia/blob/master/SWOT.md)                |
| **[Future SWOT Analysis](FUTURE_SWOT.md)**          | 💼 Business     | Future strategic opportunities            | [View Source](https://github.com/Hack23/cia/blob/master/FUTURE_SWOT.md)         |
| **[Data Model](DATA_MODEL.md)**                     | 📊 Data         | Current data structures and relationships | [View Source](https://github.com/Hack23/cia/blob/master/DATA_MODEL.md)          |
| **[Future Data Model](FUTURE_DATA_MODEL.md)**       | 📊 Data         | Enhanced political data architecture      | [View Source](https://github.com/Hack23/cia/blob/master/FUTURE_DATA_MODEL.md)   |
| **[Flowcharts](FLOWCHART.md)**                      | 🔄 Process      | Current data processing workflows         | [View Source](https://github.com/Hack23/cia/blob/master/FLOWCHART.md)           |
| **[Future Flowcharts](FUTURE_FLOWCHART.md)**        | 🔄 Process      | Enhanced AI-driven workflows              | [View Source](https://github.com/Hack23/cia/blob/master/FUTURE_FLOWCHART.md)    |
| **[State Diagrams](STATEDIAGRAM.md)**               | 🔄 Behavior     | Current system state transitions          | [View Source](https://github.com/Hack23/cia/blob/master/STATEDIAGRAM.md)        |
| **[Future State Diagrams](FUTURE_STATEDIAGRAM.md)** | 🔄 Behavior     | Enhanced adaptive state transitions       | [View Source](https://github.com/Hack23/cia/blob/master/FUTURE_STATEDIAGRAM.md) |
| **[CI/CD Workflows](WORKFLOWS.md)**                 | 🔧 DevOps       | Current automation processes              | [View Source](https://github.com/Hack23/cia/blob/master/WORKFLOWS.md)           |
| **[Future Workflows](FUTURE_WORKFLOWS.md)**         | 🔧 DevOps       | Enhanced CI/CD with ML                    | [View Source](https://github.com/Hack23/cia/blob/master/FUTURE_WORKFLOWS.md)    |
| **[End-of-Life Strategy](End-of-Life-Strategy.md)** | 📅 Lifecycle    | Maintenance and EOL planning              | [View Source](https://github.com/Hack23/cia/blob/master/End-of-Life-Strategy.md) |
| **[Financial Security Plan](FinancialSecurityPlan.md)** | 💰 Security | Cost and security implementation          | [View Source](https://github.com/Hack23/cia/blob/master/FinancialSecurityPlan.md) |
| **[ISMS Compliance Mapping](ISMS_COMPLIANCE_MAPPING.md)** | 🔐 ISMS | Comprehensive ISMS-PUBLIC policy mapping | [View Source](https://github.com/Hack23/cia/blob/master/ISMS_COMPLIANCE_MAPPING.md) |
| **[Security Architecture](SECURITY_ARCHITECTURE.md)** | 🛡️ Security | Complete security overview | [View Source](https://github.com/Hack23/cia/blob/master/SECURITY_ARCHITECTURE.md) |
| **[CIA Features](https://hack23.com/cia-features.html)** | 🚀 Features | Platform features overview                | [View on hack23.com](https://hack23.com/cia-features.html)                     |
| **[Threat Model](THREAT_MODEL.md)**                 | 🛡️ Security     | Platform threat analysis (STRIDE/MITRE)   | [View Source](https://github.com/Hack23/cia/blob/master/THREAT_MODEL.md)        |
| **[Future Threat Model](FUTURE_THREAT_MODEL.md)**   | 🛡️ Security     | Future threat landscape (AI/PQC/2026-2037)| [View Source](https://github.com/Hack23/cia/blob/master/FUTURE_THREAT_MODEL.md) |
| **[CRA Assessment](CRA-ASSESSMENT.md)**             | 🛡️ Compliance   | EU Cyber Resilience Act conformity        | [View Source](https://github.com/Hack23/cia/blob/master/CRA-ASSESSMENT.md)      |
| **[Business Continuity Plan](BCPPlan.md)**           | 📋 Resilience   | RTO/RPO targets and recovery procedures   | [View Source](https://github.com/Hack23/cia/blob/master/BCPPlan.md)             |
| **[Business Product Document](BUSINESS_PRODUCT_DOCUMENT.md)** | 💼 Business | Data analytics and risk intelligence products | [View Source](https://github.com/Hack23/cia/blob/master/BUSINESS_PRODUCT_DOCUMENT.md) |
| **[Database View Catalog](DATABASE_VIEW_INTELLIGENCE_CATALOG.md)** | 📊 Data | Catalog of 110 database views for analytics | [View Source](https://github.com/Hack23/cia/blob/master/DATABASE_VIEW_INTELLIGENCE_CATALOG.md) |

</div>

### 🌍 Hack23 Civic Tech Ecosystem (cross-repo architecture context)

CIA is the canonical data backbone for several sibling Hack23 projects. Architectural decisions in this repository must consider downstream consumers and shared ISMS controls:

| Project | Role | Architectural relationship |
|---------|------|----------------------------|
| 🗳️ [Riksdagsmonitor](https://github.com/Hack23/riksdagsmonitor) ([live](https://riksdagsmonitor.com)) | Sweden — public news & dashboards in 14 languages | **Downstream consumer** — pulls CIA JSON exports nightly |
| 🏛️ [EU Parliament Monitor](https://github.com/Hack23/euparliamentmonitor) ([live](https://euparliamentmonitor.com)) | European Parliament — political intelligence newsroom | Sibling architecture pattern (TypeScript + agentic newsroom) |
| 🤖 [European Parliament MCP Server](https://github.com/Hack23/European-Parliament-MCP-Server) | EP Open Data MCP (60+ tools) for AI agents | Reference for MCP integration patterns |
| 🛡️ [Hack23 ISMS-PUBLIC](https://github.com/Hack23/ISMS-PUBLIC) | Apex Information Security Management System | All architecture decisions are governed by these policies |

## 🌐 C4 System Context Diagram

This diagram illustrates how different stakeholders interact with the Citizen Intelligence Agency system and the external data sources required for political intelligence analysis. For a conceptual overview of these relationships, see the [Mindmap](MINDMAP.md).

```mermaid
C4Context
  title System Context diagram for Citizen Intelligence Agency

  Person("anonymousUser, #quot;Anonymous User#quot;, #quot;Accesses public political data and metrics#quot;")
  Person("registeredUser, #quot;Registered User#quot;, #quot;Creates account and accesses personalized features#quot;")
  Person("adminUser, #quot;System Administrator#quot;, #quot;Manages system configuration and user accounts#quot;")
  
  System("cia, #quot;Citizen Intelligence Agency#quot;, #quot;Provides analysis and visualization of Swedish political activities#quot;")
  
  System_Ext("riksdagenAPI, #quot;Swedish Parliament API#quot;, #quot;Source of parliamentary data including members, votes, and documents#quot;")
  System_Ext("electionAuthority, #quot;Swedish Election Authority#quot;, #quot;Provides election data and political party information#quot;")
  System_Ext("worldBankAPI, #quot;World Bank Open Data#quot;, #quot;Source of global economic indicators#quot;")
  System_Ext("governmentBodies, #quot;Swedish Government Bodies#quot;, #quot;Financial and headcount data for government agencies#quot;")
  
  Rel("anonymousUser, cia, #quot;Views political data and metrics#quot;")
  Rel("registeredUser, cia, #quot;Creates account, customizes views, and receives updates#quot;")
  Rel("adminUser, cia, #quot;Configures system and manages user accounts#quot;")
  
  Rel("cia, riksdagenAPI, #quot;Retrieves parliamentary data from#quot;")
  Rel("cia, electionAuthority, #quot;Retrieves election data from#quot;")
  Rel("cia, worldBankAPI, #quot;Retrieves economic indicators from#quot;")
  Rel("cia, governmentBodies, #quot;Retrieves government body data from#quot;")
  
  UpdateLayoutConfig("$c4ShapeInRow=#quot;3#quot;, $c4BoundaryInRow=#quot;1#quot;")
  
  UpdateElementStyle("anonymousUser, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#bbdefb#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("registeredUser, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#bbdefb#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("adminUser, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#bbdefb#quot;, $borderColor=#quot;#86b5d9#quot;")
  
  UpdateElementStyle("cia, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("riksdagenAPI, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#d1c4e9#quot;, $borderColor=#quot;#9575cd#quot;")
  UpdateElementStyle("electionAuthority, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#d1c4e9#quot;, $borderColor=#quot;#9575cd#quot;")
  UpdateElementStyle("worldBankAPI, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#d1c4e9#quot;, $borderColor=#quot;#9575cd#quot;")
  UpdateElementStyle("governmentBodies, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#d1c4e9#quot;, $borderColor=#quot;#9575cd#quot;")
```

## 🏛️ C4 Container Diagram

This diagram reveals the modular construction of the application with distinct containers for the web application, service layer, data access, and external data integration. See [README.md - AWS Services Stack](README.md#aws-services-stack) for cloud deployment details and [Financial Security Plan](FinancialSecurityPlan.md) for security implementation.

```mermaid
C4Container
  title Container diagram for Citizen Intelligence Agency

  Person("anonymousUser, #quot;Anonymous User#quot;, #quot;Accesses public political data and metrics#quot;")
  Person("registeredUser, #quot;Registered User#quot;, #quot;Creates account and accesses personalized features#quot;")
  Person("adminUser, #quot;System Administrator#quot;, #quot;Manages system configuration and user accounts#quot;")
  
  System_Boundary("cia, #quot;Citizen Intelligence Agency#quot;") {
    Container("webApplication, #quot;Web Application#quot;, #quot;Java 26/21, Spring 5.3.39, Vaadin 8.14.4, Jetty 12.1.8 EE8#quot;, #quot;Provides interactive UI for political data visualization and analysis#quot;")
    
    Container("serviceLayer, #quot;Service Layer#quot;, #quot;Java 26/21, Spring 5.3.39#quot;, #quot;Implements business logic and manages application services#quot;")
    
    Container("dataAccess, #quot;Data Access Layer#quot;, #quot;Java 26/21, Hibernate 5.6.15, JPA 2.x#quot;, #quot;Handles database interactions and entity management#quot;")
    
    Container("dataIntegration, #quot;Data Integration Services#quot;, #quot;Java 26/21, Spring Integration#quot;, #quot;Fetches and processes data from external sources#quot;")
    
    ContainerDb(database, "Database", "PostgreSQL 18 (pgaudit, pgcrypto, pg_stat_statements, pgvector)", "Stores political data, user information, and system configuration")
    
    Container("securityModule, #quot;Security Module#quot;, #quot;Spring Security#quot;, #quot;Manages authentication, authorization, and security controls#quot;")
    
    Container("monitoringModule, #quot;Monitoring Module#quot;, #quot;JavaMelody#quot;, #quot;Monitors application performance and generates alerts#quot;")
  }
  
  System_Ext("riksdagenAPI, #quot;Swedish Parliament API#quot;, #quot;Source of parliamentary data#quot;")
  System_Ext("electionAuthority, #quot;Swedish Election Authority#quot;, #quot;Source of election data#quot;")
  
  Rel("anonymousUser, webApplication, #quot;Uses#quot;, #quot;HTTPS#quot;")
  Rel("registeredUser, webApplication, #quot;Uses#quot;, #quot;HTTPS#quot;")
  Rel("adminUser, webApplication, #quot;Manages#quot;, #quot;HTTPS#quot;")
  
  Rel("webApplication, serviceLayer, #quot;Uses#quot;")
  Rel("webApplication, securityModule, #quot;Uses for authentication/authorization#quot;")
  
  Rel("serviceLayer, dataAccess, #quot;Uses#quot;")
  Rel("serviceLayer, dataIntegration, #quot;Uses#quot;")
  
  Rel("dataAccess, database, #quot;Reads from and writes to#quot;")
  Rel("dataIntegration, riksdagenAPI, #quot;Fetches data from#quot;, #quot;HTTPS#quot;")
  Rel("dataIntegration, electionAuthority, #quot;Fetches data from#quot;, #quot;HTTPS#quot;")
  
  Rel("monitoringModule, webApplication, #quot;Monitors#quot;")
  Rel("monitoringModule, serviceLayer, #quot;Monitors#quot;")
  Rel("monitoringModule, dataAccess, #quot;Monitors#quot;")
  Rel("monitoringModule, dataIntegration, #quot;Monitors#quot;")
  
  UpdateLayoutConfig("$c4ShapeInRow=#quot;3#quot;, $c4BoundaryInRow=#quot;1#quot;")
  
  UpdateElementStyle("anonymousUser, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#bbdefb#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("registeredUser, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#bbdefb#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("adminUser, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#bbdefb#quot;, $borderColor=#quot;#86b5d9#quot;")
  
  UpdateElementStyle("webApplication, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("serviceLayer, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("dataAccess, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("dataIntegration, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("database, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#d1c4e9#quot;, $borderColor=#quot;#9575cd#quot;")
  UpdateElementStyle("securityModule, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#ffecb3#quot;, $borderColor=#quot;#ffd54f#quot;")
  UpdateElementStyle("monitoringModule, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#ffecb3#quot;, $borderColor=#quot;#ffd54f#quot;")
```

## 🧩 C4 Component Diagram for Web Application

This diagram demonstrates the internal structure of the Web Application container, showing how Vaadin UI components interact with service components and the view factory. See the [CIA Features](https://hack23.com/cia-features.html) for screenshots of the actual interface.

```mermaid
C4Component
  title Component diagram for CIA Web Application

  Container_Boundary("webApplication, #quot;Web Application#quot;") {
    Component("uiApplication, #quot;UI Application#quot;, #quot;Java, Vaadin#quot;, #quot;Main application entry point and UI configuration#quot;")
    
    Component("viewFactory, #quot;View Factory#quot;, #quot;Java, Spring#quot;, #quot;Creates and manages view instances based on user navigation#quot;")
    
    Component("dashboardView, #quot;Dashboard View#quot;, #quot;Vaadin Components#quot;, #quot;Displays overview of political activity metrics#quot;")
    Component("parliamentView, #quot;Parliament View#quot;, #quot;Vaadin Components#quot;, #quot;Shows parliament composition and activity#quot;")
    Component("politicianView, #quot;Politician View#quot;, #quot;Vaadin Components#quot;, #quot;Displays politician profiles and performance metrics#quot;")
    Component("partyView, #quot;Political Party View#quot;, #quot;Vaadin Components#quot;, #quot;Shows party information and voting patterns#quot;")
    Component("ballotView, #quot;Ballot View#quot;, #quot;Vaadin Components#quot;, #quot;Displays voting records and results#quot;")
    Component("committeeView, #quot;Committee View#quot;, #quot;Vaadin Components#quot;, #quot;Shows parliamentary committees and their activities#quot;")
    Component("governmentBodyView, #quot;Government Body View#quot;, #quot;Vaadin Components#quot;, #quot;Displays government agency data#quot;")
    
    Component("chartFactory, #quot;Chart Factory#quot;, #quot;Java, Chart.js#quot;, #quot;Creates data visualizations and charts#quot;")
    Component("menuFactory, #quot;Menu Factory#quot;, #quot;Java, Vaadin#quot;, #quot;Creates navigation menus based on user role#quot;")
    
    Component("userHomeView, #quot;User Home View#quot;, #quot;Vaadin Components#quot;, #quot;User profile and personalized dashboard#quot;")
    Component("adminView, #quot;Admin View#quot;, #quot;Vaadin Components#quot;, #quot;System administration interface#quot;")
    
    Component("pageEventHelper, #quot;Page Action Event Helper#quot;, #quot;Java#quot;, #quot;Tracks user page visits and interactions#quot;")
  }
  
  Container("serviceLayer, #quot;Service Layer#quot;, #quot;Java, Spring#quot;")
  Container("securityModule, #quot;Security Module#quot;, #quot;Spring Security#quot;")
  
  Rel("uiApplication, viewFactory, #quot;Uses#quot;")
  Rel("uiApplication, securityModule, #quot;Authenticates users via#quot;")
  
  Rel("viewFactory, dashboardView, #quot;Creates#quot;")
  Rel("viewFactory, parliamentView, #quot;Creates#quot;")
  Rel("viewFactory, politicianView, #quot;Creates#quot;")
  Rel("viewFactory, partyView, #quot;Creates#quot;")
  Rel("viewFactory, ballotView, #quot;Creates#quot;")
  Rel("viewFactory, committeeView, #quot;Creates#quot;")
  Rel("viewFactory, governmentBodyView, #quot;Creates#quot;")
  Rel("viewFactory, userHomeView, #quot;Creates#quot;")
  Rel("viewFactory, adminView, #quot;Creates#quot;")
  
  Rel("dashboardView, chartFactory, #quot;Uses#quot;")
  Rel("parliamentView, chartFactory, #quot;Uses#quot;")
  Rel("politicianView, chartFactory, #quot;Uses#quot;")
  Rel("partyView, chartFactory, #quot;Uses#quot;")
  
  Rel("dashboardView, serviceLayer, #quot;Retrieves data from#quot;")
  Rel("parliamentView, serviceLayer, #quot;Retrieves data from#quot;")
  Rel("politicianView, serviceLayer, #quot;Retrieves data from#quot;")
  Rel("partyView, serviceLayer, #quot;Retrieves data from#quot;")
  Rel("ballotView, serviceLayer, #quot;Retrieves data from#quot;")
  Rel("committeeView, serviceLayer, #quot;Retrieves data from#quot;")
  Rel("governmentBodyView, serviceLayer, #quot;Retrieves data from#quot;")
  
  Rel("dashboardView, pageEventHelper, #quot;Records page visits via#quot;")
  Rel("parliamentView, pageEventHelper, #quot;Records page visits via#quot;")
  Rel("politicianView, pageEventHelper, #quot;Records page visits via#quot;")
  Rel("partyView, pageEventHelper, #quot;Records page visits via#quot;")
  
  Rel("pageEventHelper, serviceLayer, #quot;Sends events to#quot;")
  
  Rel("uiApplication, menuFactory, #quot;Uses#quot;")
  Rel("menuFactory, securityModule, #quot;Checks permissions via#quot;")
  
  UpdateLayoutConfig("$c4ShapeInRow=#quot;3#quot;, $c4BoundaryInRow=#quot;1#quot;")
  
  UpdateElementStyle("uiApplication, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("viewFactory, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("dashboardView, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("parliamentView, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("politicianView, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("partyView, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("ballotView, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("committeeView, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("governmentBodyView, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("chartFactory, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#c8e6c9#quot;, $borderColor=#quot;#66bb6a#quot;")
  UpdateElementStyle("menuFactory, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#c8e6c9#quot;, $borderColor=#quot;#66bb6a#quot;")
  UpdateElementStyle("userHomeView, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("adminView, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("pageEventHelper, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#c8e6c9#quot;, $borderColor=#quot;#66bb6a#quot;")
```

## 🧠 C4 Component Diagram for Service Layer

This diagram reveals the internal structure of the Service Layer, showing the various services that manage different aspects of the application's business logic. For implementation details, see the [API Documentation](https://hack23.github.io/cia/apidocs/index.html).

```mermaid
C4Component
  title Component diagram for CIA Service Layer

  Container_Boundary("serviceLayer, #quot;Service Layer#quot;") {
    Component("applicationManager, #quot;Application Manager#quot;, #quot;Java, Spring#quot;, #quot;Coordinates service operations and maintains application state#quot;")
    
    Component("userService, #quot;User Service#quot;, #quot;Java, Spring#quot;, #quot;Manages user accounts and profiles#quot;")
    Component("securityService, #quot;Security Service#quot;, #quot;Java, Spring#quot;, #quot;Handles security-related operations#quot;")
    
    Component("parliamentDataService, #quot;Parliament Data Service#quot;, #quot;Java, Spring#quot;, #quot;Manages parliament-related data#quot;")
    Component("politicianDataService, #quot;Politician Data Service#quot;, #quot;Java, Spring#quot;, #quot;Manages politician-related data#quot;")
    Component("partyDataService, #quot;Party Data Service#quot;, #quot;Java, Spring#quot;, #quot;Manages political party data#quot;")
    Component("ballotDataService, #quot;Ballot Data Service#quot;, #quot;Java, Spring#quot;, #quot;Manages voting record data#quot;")
    Component("committeeDataService, #quot;Committee Data Service#quot;, #quot;Java, Spring#quot;, #quot;Manages committee data#quot;")
    Component("governmentBodyDataService, #quot;Government Body Service#quot;, #quot;Java, Spring#quot;, #quot;Manages government agency data#quot;")
    
    Component("dataImportService, #quot;Data Import Service#quot;, #quot;Java, Spring#quot;, #quot;Coordinates data import from external sources#quot;")
    Component("chartDataManager, #quot;Chart Data Manager#quot;, #quot;Java, Spring#quot;, #quot;Prepares data for visualization#quot;")
    
    Component("applicationEventService, #quot;Application Event Service#quot;, #quot;Java, Spring#quot;, #quot;Handles application events and page visits#quot;")
  }
  
  Container("dataAccess, #quot;Data Access Layer#quot;, #quot;Java, Hibernate, JPA#quot;")
  Container("dataIntegration, #quot;Data Integration Services#quot;, #quot;Java, Spring Integration#quot;")
  
  Rel("applicationManager, userService, #quot;Coordinates#quot;")
  Rel("applicationManager, securityService, #quot;Coordinates#quot;")
  Rel("applicationManager, parliamentDataService, #quot;Coordinates#quot;")
  Rel("applicationManager, politicianDataService, #quot;Coordinates#quot;")
  Rel("applicationManager, partyDataService, #quot;Coordinates#quot;")
  Rel("applicationManager, ballotDataService, #quot;Coordinates#quot;")
  Rel("applicationManager, committeeDataService, #quot;Coordinates#quot;")
  Rel("applicationManager, governmentBodyDataService, #quot;Coordinates#quot;")
  Rel("applicationManager, dataImportService, #quot;Coordinates#quot;")
  Rel("applicationManager, chartDataManager, #quot;Coordinates#quot;")
  Rel("applicationManager, applicationEventService, #quot;Coordinates#quot;")
  
  Rel("userService, dataAccess, #quot;Uses#quot;")
  Rel("parliamentDataService, dataAccess, #quot;Uses#quot;")
  Rel("politicianDataService, dataAccess, #quot;Uses#quot;")
  Rel("partyDataService, dataAccess, #quot;Uses#quot;")
  Rel("ballotDataService, dataAccess, #quot;Uses#quot;")
  Rel("committeeDataService, dataAccess, #quot;Uses#quot;")
  Rel("governmentBodyDataService, dataAccess, #quot;Uses#quot;")
  
  Rel("dataImportService, dataIntegration, #quot;Uses#quot;")
  Rel("chartDataManager, dataAccess, #quot;Uses#quot;")
  Rel("applicationEventService, dataAccess, #quot;Uses#quot;")
  
  UpdateLayoutConfig("$c4ShapeInRow=#quot;3#quot;, $c4BoundaryInRow=#quot;1#quot;")
  
  UpdateElementStyle("applicationManager, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("userService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("securityService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("parliamentDataService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("politicianDataService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("partyDataService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("ballotDataService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("committeeDataService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("governmentBodyDataService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("dataImportService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("chartDataManager, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("applicationEventService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  
  UpdateElementStyle("dataAccess, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#d1c4e9#quot;, $borderColor=#quot;#9575cd#quot;")
  UpdateElementStyle("dataIntegration, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#d1c4e9#quot;, $borderColor=#quot;#9575cd#quot;")
```

## 🔄 C4 Dynamic Diagram

This diagram maps the sequence of interactions when a user accesses a politician's profile, showing how the request flows through the system components to retrieve, process, and present political data. For the entity model details, see the [Entity Documentation](https://hack23.github.io/cia/service.data.impl/hbm2doc/entities/index.html).

```mermaid
C4Dynamic
  title Dynamic diagram for Politician Profile Access

  Person("user, #quot;Registered User#quot;, #quot;Wants to view politician data#quot;")
  
  Container("webApplication, #quot;Web Application#quot;, #quot;Java, Spring MVC, Vaadin#quot;")
  Component("politicianView, #quot;Politician View#quot;, #quot;Vaadin Components#quot;")
  Component("chartFactory, #quot;Chart Factory#quot;, #quot;Java, Chart.js#quot;")
  Component("pageEventHelper, #quot;Page Action Event Helper#quot;, #quot;Java#quot;")
  
  Container("serviceLayer, #quot;Service Layer#quot;, #quot;Java, Spring#quot;")
  Component("politicianDataService, #quot;Politician Data Service#quot;, #quot;Java, Spring#quot;")
  Component("chartDataManager, #quot;Chart Data Manager#quot;, #quot;Java, Spring#quot;")
  Component("applicationEventService, #quot;Application Event Service#quot;, #quot;Java, Spring#quot;")
  
  Container("dataAccess, #quot;Data Access Layer#quot;, #quot;Java, Hibernate, JPA#quot;")
  ContainerDb("database, #quot;Database#quot;, #quot;PostgreSQL#quot;")
  
  Rel("user, webApplication, #quot;1. Navigates to politician profile#quot;")
  Rel("webApplication, politicianView, #quot;2. Creates view#quot;")
  Rel("politicianView, politicianDataService, #quot;3. Requests politician data#quot;")
  Rel("politicianDataService, dataAccess, #quot;4. Queries for politician records#quot;")
  Rel("dataAccess, database, #quot;5. Executes SQL queries#quot;")
  Rel("database, dataAccess, #quot;6. Returns politician records#quot;")
  Rel("dataAccess, politicianDataService, #quot;7. Returns politician entity objects#quot;")
  Rel("politicianView, chartDataManager, #quot;8. Requests chart data#quot;")
  Rel("chartDataManager, dataAccess, #quot;9. Queries for voting history and metrics#quot;")
  Rel("dataAccess, database, #quot;10. Executes analytics queries#quot;")
  Rel("database, dataAccess, #quot;11. Returns statistics and metrics#quot;")
  Rel("dataAccess, chartDataManager, #quot;12. Returns processed chart data#quot;")
  Rel("chartDataManager, politicianView, #quot;13. Returns visualization data#quot;")
  Rel("politicianView, chartFactory, #quot;14. Creates data visualizations#quot;")
  Rel("chartFactory, politicianView, #quot;15. Returns chart components#quot;")
  Rel("politicianView, webApplication, #quot;16. Updates UI with politician profile#quot;")
  Rel("politicianView, pageEventHelper, #quot;17. Records page visit#quot;")
  Rel("pageEventHelper, applicationEventService, #quot;18. Sends page visit event#quot;")
  Rel("applicationEventService, dataAccess, #quot;19. Stores page visit data#quot;")
  Rel("webApplication, user, #quot;20. Displays politician profile with analytics#quot;")
  
  UpdateElementStyle("user, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#bbdefb#quot;, $borderColor=#quot;#86b5d9#quot;")
  
  UpdateElementStyle("webApplication, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("politicianView, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("chartFactory, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#c8e6c9#quot;, $borderColor=#quot;#66bb6a#quot;")
  UpdateElementStyle("pageEventHelper, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#c8e6c9#quot;, $borderColor=#quot;#66bb6a#quot;")
  
  UpdateElementStyle("serviceLayer, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("politicianDataService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("chartDataManager, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  UpdateElementStyle("applicationEventService, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#a0c8e0#quot;, $borderColor=#quot;#86b5d9#quot;")
  
  UpdateElementStyle("dataAccess, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#d1c4e9#quot;, $borderColor=#quot;#9575cd#quot;")
  UpdateElementStyle("database, $fontColor=#quot;#333333#quot;, $bgColor=#quot;#d1c4e9#quot;, $borderColor=#quot;#9575cd#quot;")
```

## 📚 Architecture Layers

### 1. Presentation Layer

- **Vaadin Framework**: Server-side UI components with client-side rendering
- **View Components**: Modular views for different political entities and data (politicians, parties, committees, etc.)
- **Page Mode Content Factories**: Component factories for creating different page views and modes
- **Menu Factories**: Navigation components customized by user role
- **Chart Components**: Data visualization components
- **Card Information Components**: Reusable information display modules

### 2. Business Logic Layer

- **Service Layer**: Core business logic implementation
- **Application Manager**: Central coordination of application services
- **Entity-specific Services**: Specialized services for different political entities
- **Chart Data Managers**: Services that prepare data for visualization
- **Page Action Event Helper**: Tracks user interactions with the system

### 3. Data Access Layer

- **JPA/Hibernate**: Object-relational mapping
- **Data Containers**: Entity-specific data access objects
- **Entity Models**: Java domain model classes
- **View Entities**: Read-optimized entity views

### 4. Integration Layer

- **Data Import Services**: Services for importing data from external sources
- **API Clients**: Client implementations for external data APIs

### 5. Security Layer

- **Spring Security**: Framework for authentication and authorization
- **Role-based Access Control**: User-based access restrictions (ANONYMOUS, USER, ADMIN)
- **@Secured Annotations**: Method-level security controls
- **Session Management**: Secure session handling

### 6. Monitoring Layer

- **Monitoring Module**: Application monitoring and statistics
- **Logging Framework**: Structured logging

## 🔒 Security Architecture

For detailed information on AWS security implementations and costs, see the [Financial Security Plan](FinancialSecurityPlan.md).

### 1. Authentication and Authorization

- **Spring Security**: Provides comprehensive security framework
- **Role-based Access Control**: Three main roles (ANONYMOUS, USER, ADMIN)
- **@Secured Annotations**: Method-level security constraints
- **User Session Management**: Secure session handling with CreateApplicationSessionRequest
- **Password Security**: Secure credential storage

### 2. Data Security

- **Input Validation**: Prevents SQL injection and XSS
- **HTTPS Enforcement**: Secure HTTP headers including Strict-Transport-Security
- **Session Protection**: Session validation and tracking

### 3. Application Security

- **Error Handling**: Centralized exception handling
- **Secure Logging**: No sensitive data in logs
- **Content Security**: Prevents cross-site scripting (XSS)

## 💾 Data Model Overview

The CIA data model is comprehensive, capturing various aspects of the Swedish political system. For detailed entity documentation, see the [Entity Model](https://hack23.github.io/cia/service.data.impl/hbm2doc/entities/index.html).

### Core Entities:

1. **Politicians**: Swedish parliamentary members
2. **Political Parties**: Political organizations
3. **Committees**: Parliamentary committees
4. **Ballots**: Voting records and decisions
5. **Documents**: Legislative documents and proposals
6. **Ministries**: Government ministries
7. **Government Bodies**: Government agencies with financial data
8. **Users**: System users and their profiles
9. **Application Events**: User interactions and page visits
10. **Application Configuration**: System configuration

### Key Relationships:

1. Politicians belong to Political Parties
2. Politicians serve on Committees
3. Politicians participate in Votes on Ballots
4. Documents are processed by Committees
5. Ballots are associated with Documents
6. Ministries oversee Government Bodies
7. Users have Security Roles

## 🚀 Technology Stack

For details on the technology lifecycle and maintenance strategy, see the [End-of-Life Strategy](End-of-Life-Strategy.md).

### Core Framework:
- Spring Framework 5.3.39 (hack23java25 fork) — MVC, Security, Data, Integration
- Vaadin 8.14.4 — UI Framework
- Hibernate 5.6.15.Final / JPA 2.x — Data Access & ORM
- PostgreSQL 18 — Database (with pgaudit, pgcrypto, pg_stat_statements, pgvector)

### UI Components:
- Vaadin UI Components
- Custom Card-based Components
- Chart Visualization Libraries

### Security:
- Spring Security
- Role-based Access Control
- Secure HTTP Headers

### Monitoring:
- JavaMelody
- SLF4J/Logback

## 🎨 Architecture Color Legend

The color schemes used throughout the C4 diagrams follow these conventions:

| Element Type        | Color                  | Description                                         |
| ------------------- | ---------------------- | --------------------------------------------------- |
| Person              | #bbdefb (Light Blue)   | External users or roles interacting with the system |
| System              | #a0c8e0 (Medium Blue)  | The main system being described                     |
| Container           | #a0c8e0 (Medium Blue)  | Main application containers within the system       |
| Component           | #a0c8e0 (Medium Blue)  | Individual components within containers             |
| Database            | #d1c4e9 (Light Purple) | Data storage components                             |
| External System     | #d1c4e9 (Light Purple) | External systems or services                        |
| Process Component   | #c8e6c9 (Light Green)  | Processing and calculation components               |
| Security Component  | #ffecb3 (Light Yellow) | Security-related components                         |

## 🏭 Key Design Patterns

1. **MVC Pattern**: Model-View-Controller pattern for web UI
2. **Factory Pattern**: For creating views, menu items, and UI components
3. **Page Mode Pattern**: For managing different view modes within the same base view
4. **Command Pattern**: For menu navigation actions
5. **Repository Pattern**: For data access abstraction
6. **Service Pattern**: For business logic encapsulation
7. **Dependency Injection**: Spring-based inversion of control

## 📈 Future Architecture Improvements

For a detailed vision of the future architecture, see the [Future Mindmaps](FUTURE_MINDMAP.md) document which outlines AI-enhanced analytics capabilities and expanded political data coverage.

1. **Microservices Migration**: Potential refactoring into domain-specific microservices
2. **Real-time Updates**: Adding WebSocket support for live data updates
3. **Enhanced Analytics**: Machine learning for political trend analysis
4. **Mobile-optimized Views**: Improved responsive design for mobile access
5. **API Gateway**: Enhanced API management and documentation
6. **Containerization**: Docker-based deployment model

The CIA architecture demonstrates a well-structured, modular design that effectively separates concerns while providing a comprehensive platform for political data analysis and visualization. The system leverages modern Java/Spring technologies, secure design principles, and established UI frameworks to deliver a robust application for transparency in political processes.

## Related Documentation

- [Mindmaps](MINDMAP.md) - Conceptual overview of system components and relationships
- [Future Vision](FUTURE_MINDMAP.md) - Roadmap for AI-enhanced capabilities
- [End-of-Life Strategy](End-of-Life-Strategy.md) - Technology maintenance plans
- [Financial Security Plan](FinancialSecurityPlan.md) - AWS security implementations
- [README](README.md) - Project overview and quick links
- [CIA Features](https://hack23.com/cia-features.html) - Detailed features with screenshots
- [Project Documentation](https://hack23.github.io/cia/) - Comprehensive developer resources

---

**📋 Document Control:**  
**✅ Approved by:** James Pether Sörling, CEO - Hack23 AB  
**📤 Distribution:** Public  
**🏷️ Classification:** [![Confidentiality: Public](https://img.shields.io/badge/C-Public-lightgrey?style=flat-square&logo=shield&logoColor=black)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#confidentiality-levels) [![Integrity: High](https://img.shields.io/badge/I-High-orange?style=flat-square&logo=check-circle&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#integrity-levels) [![Availability: Moderate](https://img.shields.io/badge/A-Moderate-yellow?style=flat-square&logo=server&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#availability-levels)  
**📅 Effective Date:** 2026-04-20  
**⏰ Next Review:** 2027-04-20  
**🎯 Framework Compliance:** [![ISO 27001](https://img.shields.io/badge/ISO_27001-2022_Aligned-blue?style=flat-square&logo=iso&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) [![NIST CSF 2.0](https://img.shields.io/badge/NIST_CSF-2.0_Aligned-green?style=flat-square&logo=nist&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) [![CIS Controls](https://img.shields.io/badge/CIS_Controls-v8.1_Aligned-orange?style=flat-square&logo=cisecurity&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md)
