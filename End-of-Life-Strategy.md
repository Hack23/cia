# CIA Project End-of-Life (EOL) Strategy

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

See the [AWS Infrastructure documentation](AWS_INFRASTRUCTURE.md) for deployment considerations.

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

### Vaadin 8 UI Layer

- **Current UI Strategy**: Continue using **Vaadin 8** to avoid the costs and major structural changes of migrating to Vaadin 10+.
- **Licensing Note**: Vaadin 8 reached EOL for open-source use, so commercial support is available but optional.

For UI component details, see the [UI Component Framework documentation](UI_COMPONENTS.md).

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

## Related Documentation

- [README](README.md) - Project overview and quick links
- [Architecture Documentation](ARCHITECTURE.md) - Current system architecture
- [Financial Security Plan](FinancialSecurityPlan.md) - Security implementation details
- [Future Architecture Vision](FUTURE_MINDMAP.md) - Long-term roadmap
- [CIA Features](https://hack23.com/cia-features.html) - Feature showcase with screenshots
- [Project Documentation](https://hack23.github.io/cia/) - Comprehensive developer resources
- [Threat Model](THREAT_MODEL.md) - Lifecycle risk and residual threat alignment

