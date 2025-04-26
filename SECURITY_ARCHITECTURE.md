# 🛡️ Citizen Intelligence Agency Security Architecture

This document outlines the comprehensive security architecture of the Citizen Intelligence Agency platform, detailing how we protect our systems and data through multiple security layers.

## 📑 Table of Contents

- [🔐 Security Documentation Map](#-security-documentation-map)
- [🔑 Authentication Architecture](#-authentication-architecture)
- [📜 Data Integrity & Auditing](#-data-integrity--auditing)
- [📊 Session & Action Tracking](#-session--action-tracking)
- [🔍 Security Event Monitoring](#-security-event-monitoring)
- [🌐 Network Security](#-network-security)
- [🔌 VPC Endpoints Security](#-vpc-endpoints-security)
- [🏗️ High Availability Design](#-high-availability-design)
- [💾 Data Protection](#-data-protection)
- [☁️ AWS Security Infrastructure](#-aws-security-infrastructure)
- [📈 Monitoring & Compliance](#-monitoring--compliance)
- [🤖 Automated Security Operations](#-automated-security-operations)
- [🔒 Application Security](#-application-security)
- [📋 Compliance Framework](#-compliance-framework)
- [🛡️ Defense-in-Depth Strategy](#-defense-in-depth-strategy)
- [🔄 Security Operations](#-security-operations)
- [📝 Conclusion](#-conclusion)

## 🔐 Security Documentation Map

| Document | Focus | Description |
|----------|-------|-------------|
| [Security Architecture](SECURITY_ARCHITECTURE.md) | 🛡️ Security | Complete security overview |
| [Future Security Architecture](FUTURE_SECURITY_ARCHITECTURE.md) | 🚀 Vision | Future security roadmap |
| [Financial Security Plan](FinancialSecurityPlan.md) | 💰 Cost | AWS security implementation costs |
| [Architecture](ARCHITECTURE.md) | 🏛️ Structure | Overall system architecture |
| [End-of-Life Strategy](End-of-Life-Strategy.md) | 📅 Lifecycle | Security patching and updates |

## 🔑 Authentication Architecture

Our multi-layered authentication and authorization process includes MFA, login blocking, and role-based access control.

```mermaid
flowchart TD
    subgraph "Authentication Flow"
        direction TB
        A[👤 User] -->|"1️⃣ Login Request"| B[🔐 Authentication Service]
        B -->|"2️⃣ Validate"| C{✓ Valid?}
        
        C -->|"❌ No"| D[🚫 Login Blocking]
        D -->|"Check Status"| E{🔍 Blocked?}
        E -->|"✓ Yes"| F[⛔ Access Denied]
        E -->|"❌ No"| G[⚠️ Auth Error]
        
        C -->|"✓ Yes"| H[🔢 MFA Verification]
        H -->|"Validate Code"| I{✓ Valid?}
        I -->|"❌ No"| J[⚠️ MFA Error]
        I -->|"✓ Yes"| K[✅ Authentication Success]
        
        K -->|"Create"| L[🔑 Security Context]
        L -->|"Establish"| M[👤 User Session]
        M -->|"Log"| N[📊 Session Tracking]
    end

    subgraph "Authorization Flow"
        direction TB
        M -->|"1️⃣ Request Resource"| O[🛡️ Security Filter]
        O -->|"2️⃣ Check Permission"| P{✓ Authorized?}
        P -->|"❌ No"| Q[⛔ Access Denied]
        P -->|"✓ Yes"| R[✅ Access Granted]
        
        R -->|"Method Access"| S[🔒 Security Annotation]
        S -->|"Role Check"| T{✓ Has Role?}
        T -->|"❌ No"| U[⚠️ Security Exception]
        T -->|"✓ Yes"| V[✅ Execute Method]
        
        Q & U -->|"Log"| W[📝 Auth Event]
    end
    
    style A fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style B,L,O fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style C,E,I,P,T fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style D,G,J,Q,U fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style F fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style H,S fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style K,M,R,V fill:#00E676,stroke:#00C853,stroke-width:2px,color:black,font-weight:bold
    style N,W fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
```

### Key Components

- **🔐 Multi-Factor Authentication**: Google Authenticator OTP integration
- **🚫 Brute Force Protection**: IP, session, and user-based blocking with configurable thresholds
- **👥 Role-Based Access**: Three security tiers (Anonymous, User, Admin)
- **🔒 Method-Level Security**: `@Secured` annotations for fine-grained control
- **📤 Secure Logout**: Complete session invalidation with audit logging

### Login Blocking Protection

The system implements sophisticated login blocking mechanisms:

```java
private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_IP = 
    "Max failed login attempts recent hour per ip";
private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_SESSION = 
    "Max failed login attempts recent hour per session";
private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_USER = 
    "Max failed login attempts recent hour per user";
```

These thresholds can be configured via the application's administrative interface to adjust security posture based on threat conditions.

## 📜 Data Integrity & Auditing

Our auditing system provides comprehensive traceability and data integrity protection through Javers versioning.

```mermaid
flowchart TD
    subgraph "Javers Data Auditing"
        direction TB
        A[👤 User] -->|"Action"| B[⚙️ Service Layer]
        B -->|"Persist"| C[(💾 Database)]
        
        B -.->|"Intercept"| D[📝 Javers AOP]
        D -->|"Extract"| E[👤 Author Context]
        D -->|"Capture"| F[📊 Change Metadata]
        
        F -->|"Record"| G[📋 Entity Changes]
        F -->|"Record"| H[📋 Property Changes]
        F -->|"Record"| I[📋 Value Diff]
        
        G & H & I -->|"Store"| J[(📜 Audit Database)]
        
        K[🔍 Audit Queries] -->|"Access"| J
        K -->|"Return"| L[👁️ Change History]
        K -->|"Return"| M[📊 Author Activity]
        K -->|"Return"| N[⏱️ Timeline View]
    end
    
    style A fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style B fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style C,J fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style D,E,K fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style F,G,H,I fill:#00E676,stroke:#00C853,stroke-width:2px,color:black,font-weight:bold
    style L,M,N fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Javers Audit Components

- **📝 AOP Interception**: Transparent capture of all data changes
- **👤 Author Tracking**: Every change attributed to the authenticated user
- **📊 Complete Change History**: Entity, property, and value-level auditing
- **📋 Property-Level Tracking**: Detailed before/after snapshots for all changes
- **⏱️ Temporal Data Access**: Historical view of data at any point in time

### Javers Implementation

Our system uses Javers to provide comprehensive audit trails and data versioning capabilities:

```java
@Bean
public Javers getJavers(final PlatformTransactionManager txManager) {
    final JaversSqlRepository sqlRepository = SqlRepositoryBuilder.sqlRepository()
            .withConnectionProvider(new ConnectionProvider() {
                @Override
                public Connection getConnection() {
                    final SharedSessionContractImplementor session = 
                        entityManager.unwrap(SharedSessionContractImplementor.class);
                    return session.connection();
                }
            }).withDialect(DialectName.POSTGRES).build();

    return TransactionalJpaJaversBuilder.javers().withTxManager(txManager)
            .withObjectAccessHook(new HibernateUnproxyObjectAccessHook())
            .registerJaversRepository(sqlRepository)
            .withMappingStyle(MappingStyle.BEAN).build();
}
```

The author attribution system ensures every change is linked to the user who made it:

```java
@Bean
public AuthorProvider authorProvider() {
    return () -> {
        final SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null) {
            return context.getAuthentication().getPrincipal().toString();
        } else {
            return "system";
        }
    };
}
```

## 📊 Session & Action Tracking

Our comprehensive user activity tracking system records all user sessions and actions for security monitoring and audit purposes.

```mermaid
flowchart TD
    subgraph "User Activity Tracking"
        direction TB
        A[👤 User] -->|"Login"| B[🔑 Authentication]
        B -->|"Create"| C[📝 ApplicationSession]
        
        A -->|"Interact"| D[🖱️ Page/Component]
        D -->|"Generate"| E[📊 ApplicationActionEvent]
        E -->|"Associated with"| C
        
        C -->|"Contains"| F[📋 Session Metadata]
        F -->|"Records"| G[👤 User Identifier]
        F -->|"Records"| H[🌐 IP Information]
        F -->|"Records"| I[🌍 Browser/OS]
        F -->|"Records"| J[⏰ Time Data]
        
        E -->|"Contains"| K[📋 Action Metadata]
        K -->|"Records"| L[🔍 Operation Type]
        K -->|"Records"| M[📄 Page/Element]
        K -->|"Records"| N[⚙️ Action Details]
        K -->|"Records"| O[⏱️ Timestamp]
        
        C & E -->|"Store"| P[(💾 Tracking Database)]
        P -->|"Security Analysis"| Q[🔍 Security Alerts]
        P -->|"Pattern Analysis"| R[📊 Usage Analytics]
    end
    
    style A fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style B fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style C,E fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style D fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style F,K fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style G,H,I,J,L,M,N,O fill:#00E676,stroke:#00C853,stroke-width:2px,color:black,font-weight:bold
    style P fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style Q,R fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### ApplicationSession Tracking

Every user session is tracked with comprehensive metadata:

```java
public class ApplicationSession implements ModelObject {
    protected String sessionId;
    protected String userId;
    protected String ipInformation;
    protected String userAgentInformation;
    protected String operatingSystem;
    protected String screenSize;
    protected String timeZone;
    protected ApplicationSessionType sessionType;
    protected List<ApplicationActionEvent> events;
    protected Date createdDate;
    protected Date destroyedDate;
}
```

Key session tracking features:
- **🔑 Unique Session Identification**: Each session receives a unique ID
- **👤 User Attribution**: All sessions linked to authenticated or anonymous users
- **📡 Network Context**: IP address and location information
- **💻 Device Information**: Browser, OS, and screen characteristics
- **⏰ Temporal Tracking**: Complete session lifecycle timestamps
- **🔄 Session Type Classification**: Different session types for various contexts

### ApplicationActionEvent Tracking

Every user interaction with the system is recorded as an ApplicationActionEvent:

```java
public class ApplicationActionEvent implements ModelObject {
    protected ApplicationOperationType applicationOperation;
    protected ApplicationEventGroup eventGroup;
    protected String sessionId;
    protected String userId;
    protected String page;
    protected String pageMode;
    protected String elementId;
    protected String actionName;
    protected String errorMessage;
    protected String applicationMessage;
    protected Date createdDate;
}
```

Key action tracking features:
- **🔄 Operation Categorization**: Events classified by operation type
- **📊 Event Grouping**: Logical grouping of related events
- **🔗 Session Association**: Every event linked to its parent session
- **📱 UI Context**: Page, component, and element identification
- **🔍 Action Details**: Complete description of user action
- **⚠️ Error Tracking**: Any errors associated with the action
- **⏱️ Precise Timing**: Exact timestamp of each action

## 🔍 Security Event Monitoring

Our security event monitoring system captures, analyzes, and responds to security-related events throughout the application.

```mermaid
flowchart TD
    subgraph "Security Event Monitoring"
        direction TB
        A[🔓 Authentication<br>Events] --> B[🔑 Login Success]
        A --> C[⚠️ Login Failure]
        
        D[🛡️ Authorization<br>Events] --> E[✅ Access Granted]
        D --> F[⛔ Access Denied]
        
        G[⚙️ System<br>Events] --> H[🚀 Startup]
        G --> I[🛑 Shutdown]
        G --> J[⚠️ Error]
        
        B & C & E & F & H & I & J -->|"Generate"| K[📝 ApplicationActionEvent]
        
        K -->|"Contains"| L[📋 Event Metadata]
        L -->|"Records"| M[🔍 Event Type]
        L -->|"Records"| N[👤 User ID]
        L -->|"Records"| O[🔗 Session ID]
        L -->|"Records"| P[🌐 IP Information]
        L -->|"Records"| Q[⏱️ Timestamp]
        
        K -->|"Analyzed by"| R[🚨 Security Rules]
        R -->|"May Trigger"| S[⚡ Security Alert]
        S -->|"If Critical"| T[👥 Admin Notification]
        
        K -->|"Store"| U[(💾 Event Database)]
        U -->|"Security Analysis"| V[📊 Security Dashboard]
        U -->|"Compliance"| W[📋 Audit Reports]
    end
    
    style A,D,G fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style B,E,H fill:#00E676,stroke:#00C853,stroke-width:2px,color:black,font-weight:bold
    style C,F,I,J fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style K fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style L,R fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style M,N,O,P,Q fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style S,T fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style U fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style V,W fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Security Event Types

Our system monitors several categories of security events:

1. **🔓 Authentication Events**
   - Login success/failure
   - Password changes
   - MFA enrollments/verifications
   - Account lockouts

2. **🛡️ Authorization Events**
   - Access granted/denied to resources
   - Permission changes
   - Role assignments
   - Privilege escalations

3. **🔐 Data Security Events**
   - Sensitive data access
   - Unusual data operations
   - Large data retrievals
   - Encryption operations

4. **⚙️ System Events**
   - Application startup/shutdown
   - Configuration changes
   - System errors
   - Integration failures

### Event Monitoring Implementation

The system uses event listeners to capture security events:

```java
public class AuthorizationFailureEventListener 
        implements ApplicationListener<AuthorizationFailureEvent> {
    private static final String ACCESS_DENIED = "Access Denied";
    private static final String ERROR_MESSAGE_FORMAT = 
        "SECURITY:Url:{0} , Method{1} ,{2}{3}{4}{5} source:{6}";
    
    @Override
    public void onApplicationEvent(final AuthorizationFailureEvent authorizationFailureEvent) {
        // Event handling logic
        serviceRequest.setEventGroup(ApplicationEventGroup.APPLICATION);
        serviceRequest.setApplicationOperation(ApplicationOperationType.AUTHORIZATION);
        serviceRequest.setUserId(UserContextUtil.getUserIdFromSecurityContext());
        serviceRequest.setErrorMessage(MessageFormat.format(ERROR_MESSAGE_FORMAT, 
            requestUrl, methodInfo, AUTHORITIES, authorities, 
            REQUIRED_AUTHORITIES, configAttributes, 
            authorizationFailureEvent.getSource()));
        serviceRequest.setApplicationMessage(ACCESS_DENIED);
        
        applicationManager.service(serviceRequest);
    }
}
```

### Authentication Failure Tracking

The system implements thresholds for detecting authentication attacks:

```java
private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_IP = 
    "Max failed login attempts recent hour per ip";
private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_SESSION = 
    "Max failed login attempts recent hour per session";
private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_USER = 
    "Max failed login attempts recent hour per user";
```

## 🌐 Network Security

Our defense-in-depth network architecture implements multiple security layers.

```mermaid
graph TD
    subgraph "Multi-Layer Network Security"
        A[🌐 Internet] -->|"HTTPS Only"| B[🛡️ AWS WAF]
        B -->|"Filtered Traffic"| C[⚖️ Load Balancer]
        
        subgraph "Security Zones"
            C -->|"Public Zone"| D[🌐 Public Subnets]
            D -->|"NAT Gateway"| E[🔒 Private App Subnets]
            E -->|"DB Traffic"| F[🔐 Private DB Subnets]
        end
        
        G[☁️ VPC Endpoints] -.->|"Private AWS Access"| E
    end
    
    D -->|"Host"| H[🔍 Bastion]
    E -->|"Host"| I[🖥️ EC2 Instances]
    F -->|"Host"| J[(💾 RDS Database)]
    
    style A fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style B fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style C fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style D fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style E fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style F fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style G fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style H fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style I fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style J fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Key Components

- **🛡️ AWS WAF**: Guards against OWASP Top 10 vulnerabilities
- **🌐 Network Segmentation**: Three isolated security zones with controlled traffic flow
- **🚪 NAT Gateways**: Secure outbound connectivity for private resources
- **🔥 Security Groups & NACLs**: Layered stateful and stateless filtering
- **📊 VPC Flow Logs**: Comprehensive traffic monitoring and anomaly detection
- **🔒 TLS Everywhere**: End-to-end encryption for all network traffic

## 🔌 VPC Endpoints Security

VPC Endpoints provide secure, private access to AWS services without internet exposure.

```mermaid
flowchart LR
    subgraph "Private AWS Access"
        A[🔒 Private Subnets] --> B[🔌 VPC Endpoints]
        
        B --> C[S3]
        B --> D[Secrets<br>Manager]
        B --> E[Systems<br>Manager]
        B --> F[CloudWatch]
        B --> G[KMS]
        
        H[🚪 Interface<br>Endpoints] -.-> B
        I[🔄 Gateway<br>Endpoints] -.-> B
    end
    
    style A fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style B fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style C,D,E,F,G fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style H,I fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Security Benefits

- **🔒 Private Connectivity**: Services accessed through AWS private network
- **🌐 No Internet Exposure**: Traffic never traverses the public internet
- **⚡ Performance**: Lower latency for AWS service requests
- **📄 Fine-Grained Control**: Endpoint policies restrict actions and resources
- **🔍 Audit Trail**: Complete logging of all endpoint activity

## 🏗️ High Availability Design

Our multi-AZ architecture ensures both security and resilience against infrastructure failures.

```mermaid
graph TD
    A[⚖️ Load Balancer] --> B[🌐 Public Subnets]
    
    subgraph "Availability Zone A"
        B --> |"Zone A"| C[🚪 NAT<br>Gateway A]
        C --> D[🔒 Private<br>App A]
        D --> E[🔐 Private<br>DB A]
        E --> F[(💾 Primary<br>DB)]
    end
    
    subgraph "Availability Zone B"
        B --> |"Zone B"| G[🚪 NAT<br>Gateway B]
        G --> H[🔒 Private<br>App B]
        H --> I[🔐 Private<br>DB B]
        I --> J[(💾 Standby<br>DB)]
    end
    
    subgraph "Availability Zone C"
        B --> |"Zone C"| K[🚪 NAT<br>Gateway C]
        K --> L[🔒 Private<br>App C]
        L --> M[🔐 Private<br>DB C]
        M --> N[(💾 Read<br>Replica)]
    end
    
    style A fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style B fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style C,G,K fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style D,H,L fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style E,I,M fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style F,J,N fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Resilience Features

- **⚖️ Multi-AZ Load Balancing**: Traffic distribution across three availability zones
- **🚪 Redundant NAT Gateways**: One per AZ for fault-tolerant outbound connectivity
- **📊 Security Consistency**: Identical security controls across all zones
- **💾 Database Redundancy**: Multi-AZ deployment with automatic failover
- **⚡ Automatic Recovery**: Self-healing infrastructure with health checks

## 💾 Data Protection

Our comprehensive data protection strategy secures data throughout its lifecycle.

```mermaid
flowchart TD
    subgraph "Data Protection Strategy"
        A[👤 User] <-->|"🔒 TLS 1.3"| B[⚖️ Load Balancer]
        B <-->|"🔒 TLS 1.2+"| C[🖥️ Application]
        C <-->|"🔒 TLS 1.2+"| D[(💾 Database)]
        
        E[🗝️ Secrets<br>Manager] -->|"Secure Credentials"| C
        F[🔑 KMS] -->|"Encryption Keys"| G[🔐 Encrypted<br>Storage]
        
        G --> D
        G --> H[📦 S3 Buckets]
        G --> I[💿 EBS Volumes]
        
        J[🔄 Automatic<br>Rotation] -->|"Update"| E
    end
    
    style A fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style B fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style C fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style D fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style E,F fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style G fill:#00E676,stroke:#00C853,stroke-width:2px,color:black,font-weight:bold
    style H,I fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style J fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Protection Mechanisms

- **🔒 End-to-End Encryption**: TLS for all communications
- **🔐 Data-at-Rest Encryption**: KMS encryption for databases, EBS volumes, and S3
- **🗝️ Secrets Management**: Secure credential storage with automated rotation
- **📦 S3 Security**: Server-side encryption, versioning, and access controls
- **🔑 Key Management**: Automatic key rotation and strict access controls

## ☁️ AWS Security Infrastructure

Our AWS security infrastructure provides comprehensive protection at all levels.

```mermaid
graph TD
    subgraph "Defense Layers"
        A[🌐 Internet] --> B[🛡️ Edge Security]
        B --> C[🔒 Network Security]
        C --> D[👤 Identity Security]
        D --> E[👁️ Monitoring & Detection]
    end
    
    B --> F[🔰 Shield]
    B --> G[🧱 WAF]
    
    C --> H[🕸️ VPC]
    C --> I[🚧 Security Groups]
    C --> J[🔍 Network ACLs]
    
    D --> K[👥 IAM]
    D --> L[🎭 Roles]
    D --> M[📜 Policies]
    
    E --> N[🕵️ GuardDuty]
    E --> O[📊 CloudTrail]
    E --> P[📈 Security Hub]
    
    style A fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style B,C,D,E fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style F,G fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style H,I,J fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style K,L,M fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style N,O,P fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Core AWS Security Services

- **🔰 AWS Shield**: DDoS protection at network and transport layers
- **🧱 AWS WAF**: Web application firewall with managed rule sets
- **🕸️ VPC Design**: Isolated network segments with controlled traffic flow
- **👥 IAM Framework**: Least-privilege access model with role-based permissions
- **🕵️ GuardDuty**: Continuous threat detection with machine learning
- **📈 Security Hub**: Unified security and compliance management

## 📊 Monitoring & Compliance

Our comprehensive monitoring system provides real-time visibility and rapid response capabilities.

```mermaid
flowchart TD
    subgraph "Security Monitoring Ecosystem"
        A[📊 Log Sources] --> B[📈 CloudWatch]
        B --> C[🔍 Analysis]
        C --> D[⚡ Response]
        
        A --> E[⚙️ Application]
        A --> F[🔌 Network]
        A --> G[🔑 API Activity]
        
        C --> H[🕵️ GuardDuty]
        C --> I[📋 Security Hub]
        C --> J[🔎 Inspector]
        
        D --> K[🚨 Alerts]
        D --> L[🤖 Auto-Remediation]
        D --> M[👥 Incident Response]
    end
    
    style A,B,C,D fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style E,F,G fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style H,I,J fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style K,L,M fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Monitoring Components

- **📊 Centralized Logging**: CloudWatch for application, VPC Flow, and CloudTrail logs
- **🕵️ Threat Intelligence**: GuardDuty for anomaly detection and threat identification
- **🔎 Vulnerability Management**: Inspector for automated assessment
- **📋 Compliance Dashboard**: Security Hub for standards adherence
- **🚨 Alert Pipeline**: SNS-based notifications with severity classifications
- **🤖 Automated Response**: EventBridge rules for common security events

## 🤖 Automated Security Operations

Our automated security maintenance system ensures continuous protection.

```mermaid
flowchart TD
    subgraph "Automated Security Maintenance"
        A[⏱️ Maintenance<br>Window] --> B[🔄 Automated<br>Operations]
        
        B --> C[🔍 Security<br>Scanning]
        B --> D[🛠️ Patch<br>Management]
        B --> E[🔄 Agent<br>Updates]
        B --> F[📊 Inventory<br>Collection]
        
        C --> G[📝 Findings]
        D --> H[📊 Compliance<br>Status]
        
        G & H --> I[📦 S3 Artifact<br>Storage]
    end
    
    style A fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style B fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style C,D,E,F fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style G,H fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style I fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### AWS Systems Manager Maintenance Window

The CloudFormation template defines a comprehensive maintenance window for automated security operations:

```json
"MaintenanceWindow": {
    "Type": "AWS::SSM::MaintenanceWindow",
    "Properties": {
        "Description": "Daily Maintenance Window",
        "AllowUnassociatedTargets": false,
        "Cutoff": 0,
        "Schedule": "rate(1 hour)",
        "Duration": 1,
        "Name": "hourly-patching"
    }
}
```

This configuration ensures regular, automated security maintenance:
- Hourly execution schedule
- Defined maintenance window
- Controlled target selection via tags
- Automated output logging

### Automation Components

- **⏱️ Scheduled Maintenance**: Regular security operations on defined schedules
- **🛠️ Patch Management**: Automated security patch deployment and validation
- **🔄 Agent Updates**: SSM agent and security tool updates
- **📊 Inventory Tracking**: Software and configuration monitoring
- **🔍 Compliance Verification**: Automated checks against security baselines

## 🔒 Application Security

Our application implements robust security controls at the code level.

```mermaid
flowchart LR
    subgraph "Application Security Controls"
        A[🛡️ Spring<br>Security] --> B[🔐 Authentication]
        A --> C[🔑 Authorization]
        A --> D[🔒 Headers]
        A --> E[🛑 Input<br>Validation]
        
        B --> F[👤 MFA]
        B --> G[🚫 Brute Force<br>Protection]
        
        C --> H[🎭 Role-Based<br>Access]
        C --> I[📝 Method<br>Security]
        
        D --> J[🔐 Content<br>Security]
        D --> K[📌 HSTS]
    end
    
    style A fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style B,C,D,E fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style F,G fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style H,I fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style J,K fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Method-Level Security Implementation

The application implements `@Secured` annotations for fine-grained access control:

```java
@Secured({ "ROLE_USER", "ROLE_ADMIN" })
public DataContainer<UserAccount> getUserAccountByEmail(ServiceRequest serviceRequest) {
    // Implementation details...
}

@Secured({ "ROLE_ADMIN" })
public ServiceResponse updateApplicationConfiguration(ServiceRequest serviceRequest) {
    // Implementation details...
}
```

Each secured method enforces specific role requirements:
- `ROLE_ANONYMOUS`: Unauthenticated access (limited functionality)
- `ROLE_USER`: Standard authenticated user access
- `ROLE_ADMIN`: Administrative privileges for sensitive operations

### Application Security Features

- **🛡️ Spring Security Framework**: Enterprise-grade security integration
- **🔐 Authentication**: Multi-factor and password-based with BCrypt hashing
- **🔑 Role-Based Access Control**: Fine-grained authorization with method annotations
- **🔒 Security Headers**: CSP, HSTS, X-Content-Type-Options, and Referrer-Policy
- **🛑 Input Validation**: Both client and server-side validation

## 📋 Compliance Framework

Our security architecture aligns with key compliance frameworks.

```mermaid
graph TD
    subgraph "Compliance Integration"
        A[🏛️ Compliance<br>Framework] --> B[🔍 NIST CSF]
        A --> C[🔐 ISO 27001]
        
        B --> D[👁️ Identify]
        B --> E[🛡️ Protect]
        B --> F[🔎 Detect]
        B --> G[⚡ Respond]
        B --> H[🔄 Recover]
        
        C --> I[👥 Access<br>Control]
        C --> J[🔒 Cryptography]
        C --> K[⚙️ Operations]
        C --> L[📡 Communications]
    end
    
    style A fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style B,C fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style D,E,F,G,H fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style I,J,K,L fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    classDef default font-weight:bold
```

### NIST Cybersecurity Framework Mapping

| Function | Category | Implementation |
|----------|----------|----------------|
| **Identify (ID)** | Asset Management (ID.AM) | AWS Config for resource inventory |
| | Risk Assessment (ID.RA) | Security Hub risk scoring |
| **Protect (PR)** | Identity Management (PR.AC) | IAM, Spring Security, MFA |
| | Data Security (PR.DS) | Encryption at rest and in transit |
| | Protective Technology (PR.PT) | WAF, Security Groups, Network ACLs |
| **Detect (DE)** | Anomalies and Events (DE.AE) | CloudWatch, GuardDuty |
| | Security Monitoring (DE.CM) | VPC Flow Logs, CloudTrail |
| **Respond (RS)** | Response Planning (RS.RP) | SNS alerting, Security Hub |
| | Analysis (RS.AN) | Detective, Security Hub |
| **Recover (RC)** | Recovery Planning (RC.RP) | AWS Backup, Multi-AZ deployment |
| | Improvements (RC.IM) | Automated remediations |

### Framework Alignment

- **🔍 NIST Cybersecurity Framework**: Complete implementation of all core functions
- **🔐 ISO 27001 Controls**: Alignment with key control domains
- **📊 Security Hub Standards**: Automated compliance verification
- **📝 Documentation**: Comprehensive policy and procedure documentation

## 🛡️ Defense-in-Depth Strategy

Our security architecture implements multiple protective layers to create a comprehensive defense.

```mermaid
graph LR
    A[👤 User<br>Request] --> B[🌐 DNS]
    B --> C[🧱 WAF]
    C --> D[🔒 Network]
    D --> E[💻 Host]
    E --> F[⚙️ Application]
    F --> G[💾 Data]
    
    style A fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style B fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style C fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style D fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style E fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style F fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style G fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Security Layers

Each layer provides distinct security controls:

- **🌐 DNS Security**: Route 53 with DNSSEC and query logging
- **🧱 WAF Protection**: Rule-based filtering for OWASP Top 10 vulnerabilities
- **🔒 Network Security**: Segmentation, ACLs, and encryption
- **💻 Host Security**: Hardened AMIs, encryption, and patch management
- **⚙️ Application Security**: Authentication, authorization, and input validation
- **💾 Data Security**: Encryption, access controls, and integrity verification

## 🔄 Security Operations

Our continuous security operations ensure the environment remains protected.

```mermaid
flowchart TD
    subgraph "Security Operations Cycle"
        A[🔍 Assess] --> B[🛡️ Protect]
        B --> C[👁️ Monitor]
        C --> D[⚡ Respond]
        D --> A
        
        E[🤖 Automation] -.-> A & B & C & D
    end
    
    A --> F[🔎 Vulnerability<br>Scans]
    A --> G[📋 Compliance<br>Checks]
    
    B --> H[🛠️ Patching]
    B --> I[🔐 Hardening]
    
    C --> J[🔍 Threat<br>Detection]
    C --> K[📊 Log<br>Analysis]
    
    D --> L[🚨 Incident<br>Response]
    D --> M[🔄 Recovery]
    
    style A,B,C,D fill:#00C853,stroke:#007E33,stroke-width:2px,color:white,font-weight:bold
    style E fill:#673AB7,stroke:#311B92,stroke-width:2px,color:white,font-weight:bold
    style F,G fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    style H,I fill:#FFD600,stroke:#FF8F00,stroke-width:2px,color:black,font-weight:bold
    style J,K fill:#FF3D00,stroke:#BF360C,stroke-width:2px,color:white,font-weight:bold
    style L,M fill:#2979FF,stroke:#0D47A1,stroke-width:2px,color:white,font-weight:bold
    classDef default font-weight:bold
```

### Operations Components

- **🔍 Security Assessment**: Regular vulnerability scans and penetration tests
- **🛡️ Protection Controls**: Continuous hardening and configuration management
- **👁️ Monitoring**: Real-time detection of security events
- **⚡ Incident Response**: Structured process for security incidents
- **🤖 Automation**: Programmatic security operations across the lifecycle

## 📝 Conclusion

The Citizen Intelligence Agency employs a comprehensive, defense-in-depth security architecture that spans from application-level controls to infrastructure security. By implementing multiple layers of protection with high availability designs, we ensure the confidentiality, integrity, and availability of sensitive political data.

### Key Security Highlights

- 🔐 Multi-factor authentication with sophisticated brute force protection
- 🛡️ AWS WAF and Shield protection against web-based attacks
- 🔒 Comprehensive encryption for data at rest and in transit
- 🌐 Redundant multi-AZ architecture with NAT Gateways for resilience
- 🔌 VPC Endpoints for secure AWS service access
- 👁️ Continuous monitoring with GuardDuty, Inspector, and Security Hub
- 📜 Complete data integrity with Javers versioning and author attribution
- 📊 Comprehensive session tracking and user action auditing with ApplicationSession and ApplicationActionEvent
- 🔍 Detailed security event monitoring with multiple event types
- 🤖 Automated security operations with Systems Manager

For detailed implementation costs and specific AWS security services, refer to the [Financial Security Plan](FinancialSecurityPlan.md).
