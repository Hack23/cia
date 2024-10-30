# Citizen Intelligence Agency

The Citizen Intelligence Agency is a volunteer-driven, open-source intelligence (OSINT) project focusing on political activity in Sweden. By monitoring key political figures and institutions, the platform provides valuable insights into financial performance, risk metrics, and political trends. The dashboard features a ranking system, enabling users to objectively compare politicians based on performance. The initiative is independent and non-partisan, seeking to encourage informed decision-making, enhance transparency in governance, and cultivate an engaged and well-informed citizenry.

## About Hack23

- Website: [www.hack23.com](https://www.hack23.com/)
- LinkedIn: [in/jamessorling](https://www.linkedin.com/in/jamessorling)

## Data Sources

The project relies on open data from various sources, including:

- [Swedish Parliament Open Data](http://data.riksdagen.se/): Offers a wide range of data related to the Swedish Parliament, including members, committees, and documents.
- [Swedish Election Authority](http://www.val.se/): Provides information on election processes, results, and political parties in Sweden.
- [World Bank Open Data](http://data.worldbank.org/): Contains global development data, including economic indicators and demographic information.
- [Swedish National Financial Management Authority (ESV) Public Sector Information (PSI) Data](https://www.esv.se/): Offers data on government finances, economic trends, and public sector operations in Sweden.

## Badges

[![GitHub Release](https://img.shields.io/github/v/release/Hack23/cia)](https://github.com/Hack23/cia/releases)
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/770/badge)](https://bestpractices.coreinfrastructure.org/projects/770)
[![OpenSSF Scorecard](https://api.securityscorecards.dev/projects/github.com/Hack23/cia/badge)](https://scorecard.dev/viewer/?uri=github.com/Hack23/cia)
[![SLSA 3](https://slsa.dev/images/gh-badge-level3.svg)](https://slsa.dev/spec/v1.0/levels)
[![Verify & Release ](https://github.com/Hack23/cia/actions/workflows/release.yml/badge.svg)](https://github.com/Hack23/cia/actions/workflows/release.yml)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Hack23_cia&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=Hack23_cia)
[![Verify PR](https://github.com/Hack23/cia/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/Hack23/cia/actions/workflows/codeql-analysis.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Hack23_cia&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Hack23_cia)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Hack23_cia&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=Hack23_cia)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Hack23_cia&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=Hack23_cia)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Hack23_cia&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=Hack23_cia)
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/Hack23/cia.svg)](http://isitmaintained.com/project/Hack23/cia "Average time to resolve an issue")
[![Percentage of issues still open](http://isitmaintained.com/badge/open/Hack23/cia.svg)](http://isitmaintained.com/project/Hack23/cia "Percentage of issues still open")
[![license](https://img.shields.io/github/license/Hack23/cia.svg)](https://raw.githubusercontent.com/Hack23/cia/master/citizen-intelligence-agency/LICENSE.txt)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FHack23%2Fcia.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2FHack23%2Fcia?ref=badge_shield)
[![CLA assistant](https://cla-assistant.io/readme/badge/Hack23/cia)](https://cla-assistant.io/Hack23/cia)


## Runtime(JDK 21+)

[![JDK-21](https://img.shields.io/badge/jdk-21-green.svg)]
[![JDK-22](https://img.shields.io/badge/jdk-22-orange.svg)]
[![JDK-23](https://img.shields.io/badge/jdk-23-green.svg)]


## Resources

- [Project Documentation](http://hack23.github.io/cia/) 
- [Project Architecture](http://hack23.github.io/cia/architecture.html) - Delve into the architecture of the Citizen Intelligence Agency. This overview provides a look at the enterprise context, system context, system containers, web application components, deployment strategy, and AWS account structure of the project.
- [Entity Model](https://hack23.github.io/cia/service.data.impl/hbm2doc/entities/index.html) - Explore our Entity Model which provides a detailed look at the entities in our system and their relationships. This page is particularly useful for understanding the data structure of our project.
- [Api docs](https://hack23.github.io/cia/apidocs/index.html) - Access the API documentation for the Citizen Intelligence Agency project. This documentation provides a detailed view of the various packages within the system, helping developers understand and work with the project's API.


## Reporting Security Issues

Please follow the instructions in our [SECURITY.md](https://github.com/Hack23/cia/blob/master/SECURITY.md) file for reporting security issues.


## Project Technology Stack Overview

This document provides a high-level overview of the key technologies used within the **Citizen Intelligence Agency (CIA)** project. Each technology plays a vital role in supporting CIA’s goals for data analysis, security, and scalability within the political intelligence domain.

| **Category**              | **Technologies**                                                                                                                                                   |
|---------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Core Framework**        | [Spring Framework](https://spring.io/projects/spring-framework)                                                                                                   |
| **Security**              | [Spring Security](https://spring.io/projects/spring-security), [Bouncy Castle](https://www.bouncycastle.org/)                                                     |
| **Data Access**           | [Hibernate](https://hibernate.org/orm/), [JPA](https://jakarta.ee/specifications/persistence/), [PostgreSQL](https://www.postgresql.org/), [JDBC](https://docs.oracle.com/javase/tutorial/jdbc/overview/index.html) |
| **Transaction Management**| [Narayana](https://narayana.io/) (Integrated with Spring `JpaTransactionManager`)                                           |
| **Data Auditing**         | [Javers](https://javers.org/)                                                                                                                                     |
| **Business Rules Engine** | [Drools](https://www.drools.org/)                                                                                                                                 |
| **Messaging**             | [ActiveMQ Artemis](https://activemq.apache.org/components/artemis/), [Spring JMS](https://spring.io/projects/spring-framework)                                    |
| **Web/UI Layer**          | [Vaadin](https://vaadin.com/), [Vaadin Sass Compiler](http://vaadin.com/), [Vaadin Themes](https://vaadin.com/)                                                  |
| **Monitoring**            | [JavaMelody](https://github.com/javamelody/javamelody), [AWS SDK for CloudWatch](https://aws.amazon.com/cloudwatch/)                                              |
| **Testing**               | [JUnit](https://junit.org/), [Mockito](https://site.mockito.org/), [Spring Test](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html), [Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/) |
| **Utilities**             | [Apache Commons](https://commons.apache.org/), [Google Guava](https://guava.dev/), [SLF4J](http://www.slf4j.org/), [Logback](https://logback.qos.ch/), [Jackson](https://github.com/FasterXML/jackson) |
| **Build & Dependency Management** | [Maven](https://maven.apache.org/)                                                                                                                           |

## Stack Summary

This stack comprises:

- **Core Framework**: The project uses **Spring Framework** to provide a foundation for dependency injection, component management, and service configuration across modules.
- **Security**: **Spring Security** manages authentication and authorization, complemented by **Bouncy Castle** for cryptographic operations.
- **Data Access**: A combination of **Hibernate**, **JPA**, and **PostgreSQL** supports robust ORM-based data persistence, with **JDBC** facilitating additional database connectivity needs.
- **Transaction Management**: The project uses **Narayana** as the transaction manager implementation, integrated with **Spring’s JpaTransactionManager** for distributed transaction support and ensuring transactional integrity.
- **Data Auditing**: **Javers** provides auditing and historical versioning, allowing for tracking and comparing changes to data over time.
- **Business Rules Engine** : **Drools** is integrated into the CIA project to enable a robust business rules engine.
- **Messaging**: **ActiveMQ Artemis** and **Spring JMS** enable asynchronous communication between application components, supporting distributed and event-driven designs.
- **Web/UI Layer**: **Vaadin** powers the UI with a server-driven architecture, providing components like **Vaadin Themes** and **Sass Compiler** for a rich, interactive frontend experience directly in Java.
- **Monitoring**: **JavaMelody** and **AWS SDK for CloudWatch** provide real-time application monitoring and logging capabilities, supporting both local and cloud environments.
- **Testing**: **JUnit**, **Mockito**, **Spring Test** and **Selenium WebDriver** are used extensively for unit, integration, system, browser and mock testing to ensure application reliability and robustness.
- **Utilities**: **Apache Commons**, **Google Guava**, **SLF4J**, and **Logback** offer utility functions and structured logging, enhancing application maintainability and monitoring.
- **Build & Dependency Management**: **Maven** handles project builds, dependency management, and plugin configurations, enabling smooth project management and modular builds.

## AWS Services Stack Overview

This document provides a comprehensive summary of the AWS services utilized in the **Citizen Intelligence Agency (CIA)** project infrastructure, as defined by its CloudFormation template. These services work together to ensure a secure, resilient, and scalable deployment environment.

```markdown
# AWS Services Stack Overview

This document provides a comprehensive summary of the AWS services utilized in the **Citizen Intelligence Agency (CIA)** project infrastructure, as defined by its CloudFormation template. These services work together to ensure a secure, resilient, and scalable deployment environment.

| **Category**                  | **AWS Services**                                                                                                                                                                                                                       | **NIST CSF Function, Category & Subcategory**                                                                                                                                                                                                                                     | **ISO 27001:2022 Control & Link**                                                                                                                                                                                                                                                                                            |
|-------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Networking and Security**   | - [Amazon VPC](https://aws.amazon.com/vpc/): Configures a custom network environment with public/private subnets, route tables, NAT Gateway, Network ACLs (NACLs) for traffic control, and VPC Flow Logs.<br> - [VPC Endpoints](https://docs.aws.amazon.com/vpc/latest/privatelink/): Enables private access to AWS services (e.g., S3, EC2, SSM, CloudWatch Logs).<br> - [AWS WAF](https://aws.amazon.com/waf/): Protects against web attacks at the ALB layer.<br> - [AWS IAM](https://aws.amazon.com/iam/): Manages role-based access control.<br> - [AWS KMS](https://aws.amazon.com/kms/): Manages encryption for data at rest. | **[Identify (ID)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/id/)**:<br> - [Asset Management (ID.AM-2)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/id/am/2/)<br> **[Protect (PR)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/)**:<br> - [Access Control (PR.AC-1, PR.AC-3, PR.AC-5)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/ac/)<br> - [Data Security (PR.DS-1, PR.DS-2)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/ds/)<br> - [Protective Technology (PR.PT-3)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/pt/3/)<br> **[Detect (DE)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/de/)**:<br> - [Security Continuous Monitoring (DE.CM-3)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/de/cm/3/) | - **A.8.1**: Asset management<br> - **A.9.4.1**: Access control policy<br> - **A.13.1.1**: Network controls<br> - **A.13.1.3**: Segregation in networks<br> - **A.18.1.5**: Regulation and compliance (see [ISO 27001](https://www.iso.org/standard/82875.html)) |
| **Domain and SSL Management** | - [Amazon Route 53](https://aws.amazon.com/route53/): Manages domain registration and DNS routing.<br> - [AWS Certificate Manager (ACM)](https://aws.amazon.com/certificate-manager/): Issues and manages SSL/TLS certificates.                    | **[Protect (PR)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/)**:<br> - [Data Security (PR.DS-5)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/ds/5/)<br> **[Detect (DE)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/de/)**:<br> - [Anomalies and Events (DE.AE-3)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/de/ae/3/) | - **A.10.1.1**: Cryptographic controls for data protection<br> - **A.12.4.3**: Security of network services |
| **Compute**                   | - [Amazon EC2](https://aws.amazon.com/ec2/): Provides scalable compute instances.                                                                                                                                                      | **[Protect (PR)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/)**:<br> - [Protective Technology (PR.PT-1)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/pt/1/)<br> **[Respond (RS)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rs/)**:<br> - [Analysis (RS.AN-1)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rs/an/1/), [Mitigation (RS.MI-2)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rs/mi/2/) | - **A.12.1.3**: Capacity management for IT infrastructure and services |
| **Load Balancing**            | - [Application Load Balancer (ALB)](https://aws.amazon.com/elasticloadbalancing/application-load-balancer/): Distributes HTTP/HTTPS traffic across EC2 instances.                                                                      | **[Protect (PR)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/)**:<br> - [Protective Technology (PR.PT-3)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/pt/3/)<br> **[Respond (RS)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rs/)**:<br> - [Communications (RS.CO-2)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rs/co/2/) | - **A.13.1.1**: Network controls<br> - **A.13.2.1**: Information transfer policies |
| **Data Storage**              | - [Amazon S3](https://aws.amazon.com/s3/): Stores application artifacts and logs with encryption, access control, and lifecycle policies.<br> - [Amazon RDS](https://aws.amazon.com/rds/): PostgreSQL database with multi-AZ deployment. | **[Protect (PR)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/)**:<br> - [Data Security (PR.DS-1, PR.DS-5)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/ds/)<br> - [Information Protection Processes and Procedures (PR.IP-3, PR.IP-4)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/ip/)<br> - [Maintenance (PR.MA-1)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/ma/1/)<br> **[Recover (RC)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rc/)**:<br> - [Recovery Planning (RC.RP-1)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rc/rp/1/), [Communications (RC.CO-2)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rc/co/2/) | - **A.8.2.3**: Information backup<br> - **A.10.1.1**: Use of cryptographic controls |
| **Secrets Management**        | - [AWS Secrets Manager](https://aws.amazon.com/secrets-manager/): Securely stores and rotates sensitive credentials with Lambda rotation support.                                                | **[Protect (PR)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/)**:<br> - [Access Control (PR.AC-1, PR.AC-4)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/ac/)<br> - [Data Security (PR.DS-6)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/ds/6/)<br> - [Identity Management and Access Control (PR.AC-7)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/ac/7/) | - **A.9.2.2**: User access provisioning<br> - **A.10.1.1**: Management of encryption keys and secret information |
| **Monitoring and Alarms**     | - [Amazon CloudWatch](https://aws.amazon.com/cloudwatch/): Provides real-time metrics, logs, and alarms to monitor performance and health.                                                                | **[Detect (DE)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/de/)**:<br> - [Security Continuous Monitoring (DE.CM-3)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/de/cm/3/) | - **A.12.4.1**: Monitoring activities |
| **Resilience and Disaster Recovery** | - [AWS Resilience Hub](https://aws.amazon.com/resilience-hub/): Assesses and improves the architecture’s resilience, recommending strategies for fault tolerance and disaster recovery. | **[Recover (RC)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rc/)**:<br> - [Recovery Planning (RC.RP-1)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rc/rp/1/)<br> - [Improvements (RC.IM-1)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/rc/im/1/) | - **A.17.1.2**: Implementing continuity controls<br> - **A.17.2.1**: Availability of information processing facilities |
| **Automation and Maintenance** | - [AWS Systems Manager (SSM)](https://aws.amazon.com/systems-manager/): Automates inventory, patching, and maintenance tasks, with **SSM Maintenance Windows** and **SSM Patch Baselines** for streamlined operations. | **[Protect (PR)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/)**:<br> - [Maintenance (PR.MA-1, PR.MA-2)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/ma/)<br> - [Protective Technology (PR.PT-1)](https://csf.tools/reference/nist-cybersecurity-framework/v2-0/pr/pt/1) | - **A.12.6.1**: Control of technical vulnerabilities<br> - **A.12.7.1**: Information systems audit considerations |


## AWS Stack Summary

- **Networking and Security**: [Amazon VPC](https://aws.amazon.com/vpc/) creates an isolated network environment with NAT Gateway, NACLs, and VPC Flow Logs. [VPC Endpoints](https://docs.aws.amazon.com/vpc/latest/privatelink/) provide private access to AWS services (e.g., S3, EC2, SSM), [AWS WAF](https://aws.amazon.com/waf/) protects against web attacks, [AWS IAM](https://aws.amazon.com/iam/) secures access control, and [AWS KMS](https://aws.amazon.com/kms/) encrypts data at rest.

- **Domain and SSL Management**: [Amazon Route 53](https://aws.amazon.com/route53/) handles DNS and domain registration, while [AWS Certificate Manager (ACM)](https://aws.amazon.com/certificate-manager/) provides SSL/TLS certificates for HTTPS security.

- **Compute Layer**: [Amazon EC2](https://aws.amazon.com/ec2/) instances host the application, providing flexible and scalable compute resources.

- **Load Balancing**: The [Application Load Balancer (ALB)](https://aws.amazon.com/elasticloadbalancing/application-load-balancer/) distributes HTTP/HTTPS traffic across EC2 instances, optimizing for high availability and resilience.

- **Data Storage**: [Amazon RDS](https://aws.amazon.com/rds/) offers a resilient PostgreSQL setup with multi-AZ deployment and custom parameter groups. [Amazon S3](https://aws.amazon.com/s3/) securely stores artifacts and logs, with lifecycle policies and KMS-managed encryption keys for compliance.

- **Secrets Management**: [AWS Secrets Manager](https://aws.amazon.com/secrets-manager/) securely stores and rotates credentials, such as database passwords, with automated Lambda support for rotation.

- **Monitoring and Alarms**: [Amazon CloudWatch](https://aws.amazon.com/cloudwatch/) monitors infrastructure health through metrics, logs, and alarms, enabling proactive management.

- **Resilience and Disaster Recovery**: [AWS Resilience Hub](https://aws.amazon.com/resilience-hub/) assesses and recommends enhancements to improve the system's resilience, providing disaster recovery and fault-tolerant strategies.

- **Automation and Maintenance**: [AWS Systems Manager (SSM)](https://aws.amazon.com/systems-manager/) automates inventory, patching, and other maintenance tasks, increasing operational efficiency.

## Deploying to AWS using CloudFormation

The Citizen Intelligence Agency (CIA) project can be deployed on AWS using the provided CloudFormation stack file. This file is located in the `cia-dist-cloudformation/src/main/resources/cia-dist-cloudformation.json` folder.

You can find the CloudFormation stack file [here](cia-dist-cloudformation/src/main/resources/cia-dist-cloudformation.json).

### Launching the CloudFormation Stack

To launch the CloudFormation stack, follow these steps:

1. Log in to your AWS Management Console.

2. Navigate to the CloudFormation service.

3. Click on **Create stack**.

4. In the **Select Template** section, choose **Upload a template file** and upload the `cia-dist-cloudformation.json` file.

5. Click **Next**.

6. Fill out the **Stack name** and any required parameters. The parameters are described in the CloudFormation stack file. Adjust them according to your requirements.

7. Click **Next** to configure stack options. You can add tags or configure advanced options as needed.

8. Click **Next** to review your stack settings. Make sure everything is set up as desired.

9. In the **Capabilities** section, check the boxes for the following options:
   - I acknowledge that AWS CloudFormation might create IAM resources.
   - I acknowledge that AWS CloudFormation might create IAM resources with custom names.

10. Click **Create stack** to start the stack creation process. AWS CloudFormation will create the required resources and deploy the CIA project.

11. Once the stack creation process is complete, you can access the application by navigating to the output URL provided in the CloudFormation stack Outputs tab.

## Diagrams

- ![Cloudformation stack Diagram](cia-dist-cloudformation/src/main/resources/cia-dist-cloudformation.png)
- ![Package overview diagram](https://hack23.github.io/cia/apidocs/package-dependencies.svg)


# Installing Debian/Ubuntu package

This guide will walk you through installing the CIA project on Debian and Ubuntu 24.4+ systems.

## Prerequisites

1. Install OpenJDK and PostgreSQL:

```bash
$ sudo apt-get install openjdk-21-jdk postgresql-16
```

2. Install PostgreSQL on Ubuntu:

```bash
$ sudo apt-get install postgresql-16 postgresql-contrib postgresql-16-pgaudit
```

## Database Setup

3. Create an empty database:

Below instructions set the default username/password and database name used for development. We recommend using custom credentials and updating the configuration at `/opt/cia/webapps/cia/WEB-INF/database.properties` to define your own username/password and database name.

```bash
$ sudo su - postgres
$ psql
postgres=# CREATE USER eris WITH password 'discord';
postgres=# CREATE DATABASE cia_dev;
postgres=# GRANT ALL PRIVILEGES ON DATABASE cia_dev to eris;
```

## PostgreSQL Configuration

4. Enable prepared transactions and required extensions:

Edit `/etc/postgresql/16/main/postgresql.conf` and set:

```ini
max_prepared_transactions = 100
```

```ini
shared_preload_libraries = 'pg_stat_statements, pgaudit, pgcrypto'
pgaudit.log = ddl
pg_stat_statements.track = all
pg_stat_statements.max = 10000
```

5. Modify PostgreSQL settings:

Edit `/etc/postgresql/16/main/pg_hba.conf` and add the following line:

```ini
host all all ::1/128 md5
```

6. Restart PostgreSQL:

```bash
$ service postgresql restart
```

## Install CIA Debian Package

7. Download the CIA Debian package:

```bash
$ wget https://github.com/Hack23/cia/releases/download/2024.10.15/cia-dist-deb-2024.10.15.all.deb
```

8. Install the Debian package:

```bash
$ sudo dpkg -i cia-dist-deb-2024.10.15.all.deb
```

## Access the Server

9. Access the server at [https://localhost:28443/cia/](https://localhost:28443/cia/).

## Citizen Intelligence Agency - Sweden Political Activity Dashboard

Welcome to the Citizen Intelligence Agency - Sweden Political Activity Dashboard repository. This project provides a comprehensive and neutral dashboard focusing on political activity in Sweden. By monitoring key political figures and institutions, we offer valuable insights into financial performance, risk metrics, and political trends. 

## [Dashboard](https://github.com/Hack23/cia/blob/master/dashboard.md)

Our [dashboard](https://github.com/Hack23/cia/blob/master/dashboard.md) offers a detailed overview of political figures, comprehensive data on various ministries, performance metrics, and visual data representation. Check out our [dashboard.md](https://github.com/Hack23/cia/blob/master/dashboard.md) for more details.

## Medborgarunderrättelsebyrån - Sverige Politisk aktivitetsdashboard

Medborgarunderrättelsebyrån tillhandahåller en neutral och omfattande dashboard som fokuserar på politisk aktivitet i Sverige. Genom att övervaka nyckelpolitiska figurer och institutioner erbjuder plattformen värdefulla insikter i finansiell prestanda, riskmetriker och politiska trender. Detta projekt drivs av avancerade AI-teknologier som [OpenAI](https://www.openai.com/) och datavisualiseringsverktyg som [Daigram](https://daigr.am/).

Vår [dashboard](https://github.com/Hack23/cia/blob/master/dashboard_sv.md) erbjuder en detaljerad översikt över politiska figurer, omfattande data om olika departement, prestandametriker och visuell datarepresentation. Kolla in vår [dashboard_sv.md](https://github.com/Hack23/cia/blob/master/dashboard_sv.md) för mer information.


## AI and Data Visualization

This project is powered by advanced AI technologies like OpenAI for data processing and analysis. We use AI to process and analyze a large amount of data from various open sources such as the Swedish Parliament, Swedish Election Authority, World Bank, and the Swedish National Financial Management Authority. The analyzed data is then represented visually through data visualization tools like Daigram.
