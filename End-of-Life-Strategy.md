<p align="center">
  <img src="https://hack23.com/icon-192.png" alt="Hack23 Logo" width="192" height="192">
</p>

<h1 align="center">📅 End-of-Life Strategy — Citizen Intelligence Agency</h1>

<p align="center">
  <strong>🔄 Lifecycle Management and Maintenance Planning</strong><br>
  <em>🎯 Ensuring Stability, Compatibility, and Security Until EOL</em>
</p>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/Owner-CEO-0A66C2?style=for-the-badge" alt="Owner"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Version-1.0-555?style=for-the-badge" alt="Version"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Effective-2025--09--18-success?style=for-the-badge" alt="Effective Date"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Review-Annual-orange?style=for-the-badge" alt="Review Cycle"/></a>
</p>

**📋 Document Owner:** CEO | **📄 Version:** 1.0 | **📅 Last Updated:** 2025-09-18 (UTC)  
**🔄 Review Cycle:** Annual | **⏰ Next Review:** 2026-09-18

---

## Overview

The **CIA Project** will maintain its existing stack, utilizing `javax.*` dependencies and Vaadin 8, without transitioning to Jakarta namespaces. The project will reach EOL when compatibility with the latest JVM requires a Jakarta migration. Below is a structured plan to ensure stability, compatibility, and security until that point.

This strategy should be considered alongside the [Financial Security Plan](FinancialSecurityPlan.md) and [Architecture Documentation](ARCHITECTURE.md) to understand the full technical context.

---

## EOL Objective

**Primary Goal**: Maintain the CIA project on its current stack without migrating to Jakarta namespaces, ending support only when essential updates require this shift.

For the current feature set that will be maintained under this strategy, see the [CIA Features page](https://hack23.com/cia-features.html).

## Jetty 10 to Jetty 12 Transition Plan

- **Current Web Server**: The project currently uses **Jetty 10**.
- **EOL for Jetty 10**: Scheduled for **2026** ([endoflife.date](https://endoflife.date/eclipse-jetty)).
- **Potential Move to Jetty 12**: Jetty 12 supports both `javax.servlet` and Jakarta namespaces and has an EOL of **2028**. Migrating to Jetty 12 would allow the CIA project to remain compatible with future JVMs while avoiding an architectural transition to Jakarta.

See [README.md - Deployment Options](README.md#deployment-options) for deployment considerations.

---

## Ongoing Maintenance Strategy

### JVM Compatibility

- **JVM Monitoring**: Regularly evaluate compatibility with new JVM versions.
- **EOL Trigger**: The project will officially end when updates require Jakarta namespaces for continued compatibility.

### Dependency Updates

- **Automated Minor and Security Updates**: Dependabot and similar tools will manage minor updates and security patches across core libraries, including:
  - [Spring Security](https://spring.io/projects/spring-security)
  - [Logback](http://logback.qos.ch/)
  - [Bouncy Castle](https://www.bouncycastle.org/)

For security implementation details, see the [Financial Security Plan](FinancialSecurityPlan.md).

### 🔐 ISMS Policy Governance

The ongoing maintenance strategy aligns with Hack23 AB's [ISMS-PUBLIC framework](https://github.com/Hack23/ISMS-PUBLIC) to ensure systematic security management throughout the platform lifecycle.

#### Maintenance Activities by ISMS Policy

| 🛡️ ISMS Policy | 🔧 Maintenance Activity | 📋 Implementation |
|---------------|------------------------|------------------|
| [**Change Management**](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Change_Management.md) | Jetty 10 → Jetty 12 migration planning<br>Jakarta namespace evaluation | Risk-assessed transition with testing<br>Documented migration path |
| [**Vulnerability Management**](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md) | Automated security patching<br>Dependency updates via Dependabot | Weekly vulnerability scans<br>30-day patch SLA for critical issues |
| [**Asset Register**](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Asset_Register.md) | EOL tracking for dependencies<br>Technology stack monitoring | Documented component lifecycle<br>Replacement planning for EOL tech |
| [**Business Continuity Plan**](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Business_Continuity_Plan.md) | Platform availability during transitions<br>Rollback procedures | Multi-AZ deployment maintenance<br>Tested recovery procedures |

**Security Assurance:**
- ✅ All dependency updates security-vetted through [WORKFLOWS.md](WORKFLOWS.md) automated scanning
- ✅ Version compatibility tested before production deployment
- ✅ Security patches prioritized per [Vulnerability Management policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md)
- ✅ EOL components tracked in [Asset Register](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Asset_Register.md)

**Related Documentation:**
- 🔐 [ISMS Compliance Mapping](ISMS_COMPLIANCE_MAPPING.md) - Lifecycle security controls
- 🛡️ [Security Architecture](SECURITY_ARCHITECTURE.md) - Current security implementation
- 🔧 [Workflows](WORKFLOWS.md) - Automated security checks

### Vaadin 8 UI Layer

- **Current UI Strategy**: Continue using **Vaadin 8** to avoid the costs and major structural changes of migrating to Vaadin 10+.
- **Licensing Note**: Vaadin 8 reached EOL for open-source use, so commercial support is available but optional.

For UI component details, see [README.md - Technology Stack](README.md#project-technology-stack).

---

## Final EOL Condition

The CIA project will be designated as EOL and archived in a read-only state when it can no longer function on the latest JVM without adopting Jakarta namespaces.

For the future vision of the platform that may supersede this version, see the [Future Architecture Mindmap](FUTURE_MINDMAP.md).

---

## Project Technology Stack

For a conceptual overview of how these components interact, see the [System Mindmap](MINDMAP.md).

| **Category**                | **Technologies**                                                                                   | **EOL**                     |
|-----------------------------|----------------------------------------------------------------------------------------------------|-----------------------------|
| **Core Framework**          | [Spring Framework 5.x](https://spring.io/projects/spring-framework)                                | **August 31, 2024**             |
| **Security**                | [Spring Security](https://spring.io/projects/spring-security), [Bouncy Castle](https://www.bouncycastle.org/) | Aligns with Spring 5.x |
| **Data Access**             | [Hibernate](https://hibernate.org/), JPA, [PostgreSQL](https://www.postgresql.org/), JDBC          | Hibernate 5.x: Ended; PostgreSQL 16: **Nov 2028** |
| **Transaction Management**  | [Narayana](https://narayana.io/)                                                                   | Active                      |
| **Data Auditing**           | [Javers](https://javers.org/)                                                                      | Active                      |
| **Business Rules Engine**   | [Drools](https://www.drools.org/)                                                                  | Active                      |
| **Messaging**               | [ActiveMQ Artemis](https://activemq.apache.org/components/artemis/), [Spring JMS](https://spring.io/projects/spring-jms) | Active                      |
| **Web/UI Layer**            | [Vaadin 8](https://vaadin.com/docs/latest/guide/vaadin-8/overview), Vaadin Sass Compiler           | Reached EOL; commercial support available |
| **Web Server**              | [Jetty 10.x](https://www.eclipse.org/jetty/) (Potential future move to Jetty 12)                   | Jetty 10 EOL: **2026**; Jetty 12 EOL: **2028** |
| **Monitoring**              | [JavaMelody](https://github.com/javamelody/javamelody), [AWS SDK for CloudWatch](https://aws.amazon.com/cloudwatch/) | Active                      |
| **Testing**                 | [JUnit](https://junit.org/junit5/), [Mockito](https://site.mockito.org/), [Spring Test](https://spring.io/projects/spring-framework), [Selenium WebDriver](https://www.selenium.dev/documentation/) | JUnit 4: Legacy; JUnit 5 & Mockito Active |
| **Utilities**               | [Apache Commons](https://commons.apache.org/), [Google Guava](https://github.com/google/guava), [SLF4J](http://www.slf4j.org/), [Logback](http://logback.qos.ch/), [Jackson](https://github.com/FasterXML/jackson) | Active                      |
| **Build & Dependency Management** | [Maven](https://maven.apache.org/)                                                          | Active                      |

---

## Notes

- **Security Focus**: Prioritize security updates for dependencies in **Spring Security**, **Logback**, and **Bouncy Castle**.
- **Documentation**: See each dependency's documentation for details and licensing options, as summarized on [endoflife.date](https://endoflife.date/).

---

## 📚 Related Documents

### 🏗️ Architecture & Planning
- [🏛️ Architecture](./ARCHITECTURE.md) - Current system architecture
- [🚀 Future Architecture](./FUTURE_ARCHITECTURE.md) - Long-term architectural vision
- [🧠 Future Mindmap](./FUTURE_MINDMAP.md) - Capability expansion plans
- [📋 README](./README.md) - Project overview and quick links

### 🛡️ Security & Compliance
- [🛡️ Security Architecture](./SECURITY_ARCHITECTURE.md) - Current security implementation
- [🎯 Threat Model](./THREAT_MODEL.md) - Lifecycle risk and residual threat alignment
- [💰 Financial Security Plan](./FinancialSecurityPlan.md) - Security implementation details
- [📋 CRA Assessment](./CRA-ASSESSMENT.md) - EU Cyber Resilience Act compliance
- [🗺️ ISMS Compliance Mapping](./ISMS_COMPLIANCE_MAPPING.md) - Complete ISMS policy mapping

### 🔄 Operations & Workflows
- [🔄 CI/CD Workflows](./WORKFLOWS.md) - Security-hardened CI/CD pipelines
- [🔮 Future Workflows](./FUTURE_WORKFLOWS.md) - Enhanced CI/CD roadmap
- [🚀 CIA Features](https://hack23.com/cia-features.html) - Feature showcase with screenshots
- [📊 Project Documentation](https://hack23.github.io/cia/) - Comprehensive developer resources

### 🔐 ISMS Policies
- [🔐 Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) - Overall security governance
- [🔍 Vulnerability Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md) - Security testing and remediation
- [📝 Change Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Change_Management.md) - Risk-controlled change processes
- [🏷️ Classification Framework](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) - Business impact and risk assessment

---

**📋 Document Control:**  
**✅ Approved by:** James Pether Sörling, CEO  
**📤 Distribution:** Public  
**🏷️ Classification:** [![Confidentiality: Public](https://img.shields.io/badge/C-Public-lightgrey?style=flat-square&logo=shield&logoColor=black)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#confidentiality-levels) [![Integrity: Moderate](https://img.shields.io/badge/I-Moderate-yellow?style=flat-square&logo=check-circle&logoColor=black)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#integrity-levels) [![Availability: Standard](https://img.shields.io/badge/A-Standard-lightgreen?style=flat-square&logo=server&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#availability-levels)  
**📅 Effective Date:** 2025-09-18  
**⏰ Next Review:** 2026-09-18  
**🎯 Framework Compliance:** [![ISO 27001](https://img.shields.io/badge/ISO_27001-2022_Aligned-blue?style=flat-square&logo=iso&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) [![NIST CSF 2.0](https://img.shields.io/badge/NIST_CSF-2.0_Aligned-green?style=flat-square&logo=nist&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) [![CIS Controls](https://img.shields.io/badge/CIS_Controls-v8.1_Aligned-orange?style=flat-square&logo=cisecurity&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) [![AWS Well-Architected](https://img.shields.io/badge/AWS-Well_Architected-orange?style=flat-square&logo=amazon-aws&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md)
