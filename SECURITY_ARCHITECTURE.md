# 🛡️ Citizen Intelligence Agency Security Architecture

This document outlines the comprehensive security architecture of the Citizen Intelligence Agency platform, detailing the authentication mechanisms, network security, data protection, AWS infrastructure, and compliance measures that safeguard the system and its data.

## 📚 Security Documentation Map

| Document | Focus | Description |
|----------|-------|-------------|
| [Security Architecture](SECURITY_ARCHITECTURE.md) | 🔐 Security | Complete security overview |
| [Future Security Architecture](FUTURE_SECURITY_ARCHITECTURE.md) | 🔐 Security | Future security overview |
| [Financial Security Plan](FinancialSecurityPlan.md) | 💰 Cost/Security | AWS security implementation costs |
| [Architecture](ARCHITECTURE.md) | 🏛️ Structure | Overall system architecture |
| [End-of-Life Strategy](End-of-Life-Strategy.md) | 📅 Lifecycle | Security patching and updates |

## 🔑 Authentication and Authorization Architecture

This diagram illustrates the multi-layered authentication and authorization process, including the MFA (Multi-Factor Authentication) flow, login blocking mechanisms, and role-based access control.

```mermaid
flowchart TD
    classDef user fill:#bbdefb,stroke:#333,stroke-width:1px
    classDef service fill:#a0c8e0,stroke:#333,stroke-width:1px
    classDef security fill:#ffecb3,stroke:#333,stroke-width:1px,color:black
    classDef error fill:#ffcdd2,stroke:#333,stroke-width:1px
    classDef success fill:#c8e6c9,stroke:#333,stroke-width:1px
    
    subgraph "Authentication Layer"
        A[User] -->|1. Provides credentials| B(LoginService)
        B -->|2. Validate credentials| C{Valid?}
        C -->|No| D[LoginBlockedAccess]
        D -->|Check block status| E{Blocked?}
        E -->|Yes| F[Return blocked error]
        E -->|No| G[Return authentication error]
        C -->|Yes| H[Check MFA]
        H -->|Validate OTP| I{Valid OTP?}
        I -->|No| J[Return MFA error]
        I -->|Yes| K[Authentication successful]
        
        K -->|Create session| L[SecurityContextHolder]
        L -->|Store authentication| M[User session established]
    end

    subgraph "Authorization Layer"
        M -->|Access secured resource| N[Spring Security Filter]
        N -->|Check authorities| O{Authorized?}
        O -->|No| P[Access denied]
        O -->|Yes| Q[Access granted]
        
        Q -->|@Secured annotation| R[Method-level security]
        R -->|Role check| S{Has role?}
        S -->|No| T[Throw SecurityException]
        S -->|Yes| U[Execute method]
    end
    
    class A user
    class B,L service
    class D,E,F,G,J,P,T error
    class K,M,Q,U success
    class N,O,R,S,H,I,C security
```

### 🔐 Authentication Components

1. **Login Service**: Processes user authentication requests and validates credentials
   - Implements password validation with BCryptPasswordEncoder
   - Supports MFA with Google Authenticator
   - Enforces account lockout after failed attempts

2. **Login Blocking**: Prevents brute force attacks through multiple protection mechanisms
   - IP-based blocking after excessive failures
   - Session-based tracking 
   - User account-based blocking
   - Configurable thresholds via application configuration

3. **Role-Based Security**: Three primary security roles
   - `ROLE_ANONYMOUS`: Unauthenticated users with limited access
   - `ROLE_USER`: Standard authenticated users
   - `ROLE_ADMIN`: Administrative users with extended privileges

4. **Method-Level Security**: `@Secured` annotations protecting service methods
   - Example: `@Secured({ "ROLE_USER", "ROLE_ADMIN" })` for user operations
   - Example: `@Secured({ "ROLE_ADMIN" })` for administrative functions
   - Example: `@Secured({ "ROLE_ANONYMOUS" })` for public endpoints

5. **Logout Handling**: Secure session termination
   - Invalidates authentication token
   - Creates audit log entry
   - Returns to anonymous context

## 📜 Data Integrity & Auditing Architecture

This diagram illustrates our comprehensive data integrity and auditing system, which ensures complete traceability, ownership attribution, and immutable history for all data changes.

```mermaid
flowchart TD
    classDef user fill:#bbdefb,stroke:#333,stroke-width:1px
    classDef service fill:#a0c8e0,stroke:#333,stroke-width:1px
    classDef database fill:#d1c4e9,stroke:#333,stroke-width:1px
    classDef audit fill:#ffecb3,stroke:#333,stroke-width:1px,color:black
    classDef event fill:#ffcdd2,stroke:#333,stroke-width:1px
    classDef data fill:#c8e6c9,stroke:#333,stroke-width:1px
    
    subgraph "Data Modification Flow"
        A[User] -->|Authenticated Action| B[Service Layer]
        B -->|Request| C[Data Access Layer]
        C -->|Persist| D[(Database)]
    end
    
    subgraph "Javers Auditing"
        C --> E[Javers AOP Aspect]
        E -->|Intercept| F[Author Extraction]
        F -->|Current User| G[Security Context]
        
        E -->|Capture| H[Change Metadata]
        H --> I[Changed Entity]
        H --> J[Changed Properties]
        H --> K[Old Values]
        H --> L[New Values]
        H --> M[Author]
        H --> N[Timestamp]
        
        H -->|Store| O[Javers Repository]
        O -->|Persist| P[Audit Database]
    end
    
    subgraph "Security Event Tracking"
        Q[Authentication Events] --> R[Auth Success/Failure]
        S[Authorization Events] --> T[Access Allowed/Denied]
        
        R & T --> U[ApplicationActionEvent]
        U --> V[User ID]
        U --> W[Session ID]
        U --> X[IP Information]
        U --> Y[Event Type]
        U --> Z[Timestamp]
        
        U -->|Store| AA[Event Repository]
        AA -->|Persist| AB[Event Database]
    end
    
    class A user
    class B,C service
    class D,P,AB database
    class E,F,O,AA,G audit
    class H,I,J,K,L,M,N,U,V,W,X,Y,Z data
    class Q,R,S,T event
```

### 📊 Data Versioning with Javers

Our system uses Javers to provide comprehensive audit trails and data versioning capabilities:

1. **Change Tracking**: Every modification to domain objects is automatically captured
   - Entity creation, updates, and deletions
   - Property-level change detection
   - Before/after value comparisons
   - Complete audit trail of who changed what and when

2. **Author Attribution**: Every change is attributed to a specific user
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

3. **JPA Integration**: Seamlessly works with our Hibernate/JPA persistence layer
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

4. **AOP-Based Auditing**: Transparent interception of data operations
   ```java
   @Bean
   public JaversAuditableAspect javersAuditableAspect(final Javers javers, 
           final AuthorProvider authorProvider,
           final CommitPropertiesProvider commitPropertiesProvider) {
       return new JaversAuditableAspect(javers, authorProvider, commitPropertiesProvider);
   }
   ```

5. **Audit Analysis**: Author-based activity reporting and summaries
   ```java
   public class ViewAuditAuthorSummary implements ModelObject {
       protected long id;
       protected String author;
       protected long changes;
       protected Date firstDate;
       protected Date lastDate;
       // Methods omitted for brevity
   }
   ```

### 📋 User Activity Tracking

The system maintains comprehensive records of all user activities through session and event tracking:

1. **Application Sessions**: Tracks complete user sessions
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
       // Methods omitted for brevity
   }
   ```

2. **Application Action Events**: Records specific user actions
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
       // Methods omitted for brevity
   }
   ```

3. **Event Categories**:
   - Authentication events (login success/failure)
   - Authorization events (access allowed/denied)
   - Data manipulation events (create/update/delete)
   - System events (startup/shutdown)

### 🔍 Security Event Monitoring

The platform implements comprehensive monitoring of security-related events:

1. **Authentication Failure Tracking**:
   - Records all failed login attempts with context
   ```java
   private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_IP = 
       "Max failed login attempts recent hour per ip";
   private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_SESSION = 
       "Max failed login attempts recent hour per session";
   private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_USER = 
       "Max failed login attempts recent hour per user";
   ```

2. **Authorization Failure Monitoring**:
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

3. **Comprehensive Event Auditing**:
   - Every security event recorded with:
     - User identity (if available)
     - Session identifier
     - IP address and user agent
     - Event timestamp
     - Operation type
     - Success/failure indication
     - Detailed error messages
     - Required vs. actual permissions (for authorization failures)

### 🛡️ Data Integrity Controls

Our data integrity controls ensure the reliability and trustworthiness of stored information:

1. **Entity Versioning**: All entities maintain version information
   ```java
   @Version
   @Column(name = "MODEL_OBJECT_VERSION")
   public int getModelObjectVersion() {
       return modelObjectVersion;
   }
   ```

2. **Optimistic Locking**: Prevents concurrent modification conflicts
   - Hibernate `@Version` annotation for optimistic locking
   - Automatic detection of concurrent modifications
   - Appropriate error handling for version conflicts

3. **Immutable Audit Trail**: Guaranteed integrity of audit records
   - Audit records cannot be modified once created
   - Complete historical record maintained permanently
   - JaversSQL repository with proper permissions

4. **Database Constraints**: Structural integrity enforcement
   - Referential integrity through foreign key constraints
   - Check constraints for data validation
   - Unique constraints for entity identity

5. **Data Validation**: Application-level input validation
   - Bean Validation (JSR-380) constraints
   - Custom validators for complex business rules
   - Pre-persistence validation in service layer

## 🌐 Network Security Architecture

This diagram shows how network security is implemented across multiple layers, from AWS WAF through VPC security to the application layer.

```mermaid
graph TD
    classDef internet fill:#bbdefb,stroke:#333,stroke-width:1px
    classDef firewall fill:#ffecb3,stroke:#333,stroke-width:1px,color:black
    classDef network fill:#a0c8e0,stroke:#333,stroke-width:1px
    classDef database fill:#d1c4e9,stroke:#333,stroke-width:1px
    
    subgraph "Internet Layer"
        A[Internet] -->|HTTPS only| B[AWS WAF]
        B -->|Rule filtering| C[Application Load Balancer]
    end
    
    subgraph "Public Network Layer"
        C -->|Port 443 only| D[Public Network ACL]
        D -->|Filtered traffic| E[Public Subnets]
    end
    
    subgraph "Application Layer"
        E -->|Port 8443| F[Private App Network ACL]
        F -->|Filtered traffic| G[WebServer Security Group]
        G -->|Allow 8443 only| H[EC2 Application Servers]
    end
    
    subgraph "Data Layer"
        H -->|Port 5432| I[Private Network ACL]
        I -->|Filtered traffic| J[Database Security Group]
        J -->|Allow 5432 only| K[RDS PostgreSQL]
    end
    
    class A internet
    class B,D,F,G,I,J firewall
    class C,E,H network
    class K database
```

### 🔒 Network Security Components

1. **AWS WAF (Web Application Firewall)**: Protects against common web exploits
   - AWS Managed Rules for known attack patterns
   - IP reputation filtering
   - Rate limiting and bot control
   - Protection against SQL injection and XSS

2. **Network Isolation**: VPC design with clear separation of concerns
   - Public subnets for load balancers only
   - Private application subnets for EC2 instances
   - Private database subnets for data storage
   - Controlled traffic flow between layers

3. **Network ACLs**: Stateless packet filtering at subnet level
   - Inbound/outbound rules limiting traffic by port and source/destination
   - Explicit deny rules for RDP (port 3389)
   - Public subnet limited to HTTPS (443)
   - Private app subnets limited to application traffic
   - Private database subnets limited to PostgreSQL (5432)

4. **Security Groups**: Stateful instance-level firewall
   - Load balancer security group: Allow 443 inbound, 8443 outbound
   - Web server security group: Allow 8443 inbound from load balancer only
   - Database security group: Allow 5432 inbound from application servers only

5. **TLS Encryption**: Secure communication throughout
   - Certificate Manager for public certificates
   - HTTPS enforcement with HTTP-to-HTTPS redirection
   - Security headers including HSTS, CSP, and X-Frame-Options

## 💾 Data Protection Architecture

This diagram illustrates how data is protected throughout its lifecycle, including encryption, access controls, and secure storage mechanisms.

```mermaid
flowchart TD
    classDef user fill:#bbdefb,stroke:#333,stroke-width:1px
    classDef network fill:#a0c8e0,stroke:#333,stroke-width:1px
    classDef database fill:#d1c4e9,stroke:#333,stroke-width:1px
    classDef security fill:#ffecb3,stroke:#333,stroke-width:1px,color:black
    classDef storage fill:#c8e6c9,stroke:#333,stroke-width:1px
    
    subgraph "Data in Transit"
        A[User Browser] <-->|TLS 1.3| B[Load Balancer]
        B <-->|TLS 1.2+| C[Application Server]
        C <-->|TLS 1.2+| D[RDS Database]
    end
    
    subgraph "Data at Rest"
        D -->|Store| E[Encrypted DB Volumes]
        E -->|KMS Keys| F[AWS KMS]
        
        C -->|Store| G[Encrypted EBS Volumes]
        G -->|KMS Keys| F
        
        C -->|Log| H[CloudWatch Logs]
        H -->|Encryption| F
        
        C -->|Backup| I[S3 Buckets]
        I -->|Server-side encryption| F
    end
    
    subgraph "Secret Management"
        J[AWS Secrets Manager] -->|Provide| K[Database Credentials]
        K -->|Accessed by| C
        K -->|Used for| D
        
        J -->|Automatic rotation| L[Password Rotation Lambda]
        L -->|Updates| K
    end
    
    class A user
    class B,C,L network
    class D database
    class E,F,G,J,K security
    class H,I storage
```

### 🔐 Data Protection Components

1. **Encryption in Transit**: Secure data transmission
   - TLS 1.3 for client-to-load-balancer communication
   - TLS 1.2+ for internal service communications
   - SSL policy: ELBSecurityPolicy-TLS13-1-2-2021-06
   - Certificate management through AWS Certificate Manager

2. **Encryption at Rest**: Protection of stored data
   - EBS encryption for EC2 instance volumes
   - RDS encryption with AWS KMS
   - S3 bucket encryption for logs and artifacts
   - Encrypted PostgreSQL connections

3. **Secret Management**: Secure credential handling
   - AWS Secrets Manager for database credentials
   - Automatic credential rotation
   - Limited IAM access to secrets
   - No hardcoded secrets in application code

4. **Data Access Control**: Least privilege principles
   - Role-based access in application
   - IAM policies for AWS resource access
   - Limited database access through security groups
   - Application-level data access authorization

5. **Password Security**: Strong password policies
   - BCrypt password hashing with unique salts
   - Password complexity requirements:
     - Minimum 8 characters, maximum 64 characters
     - At least one uppercase letter
     - At least one lowercase letter
     - At least one number
     - At least one special character
     - No whitespace characters

## ☁️ AWS Security Infrastructure

This diagram shows the multi-layered AWS security infrastructure protecting the application.

```mermaid
graph TD
    classDef internet fill:#bbdefb,stroke:#333,stroke-width:1px
    classDef security fill:#ffecb3,stroke:#333,stroke-width:1px,color:black
    classDef network fill:#a0c8e0,stroke:#333,stroke-width:1px
    classDef monitoring fill:#c8e6c9,stroke:#333,stroke-width:1px
    
    subgraph "Edge Security"
        A[Internet] -->|HTTPS| B[AWS Shield]
        B -->|DDoS Protection| C[AWS WAF]
        C -->|Web Security Rules| D[Route 53]
        D -->|DNS| E[CloudFront]
    end
    
    subgraph "Network Security"
        E -->|HTTPS| F[VPC]
        F -->|Traffic Control| G[Network ACLs]
        G -->|Stateless filtering| H[Security Groups]
        H -->|Stateful filtering| I[EC2 Instances]
    end
    
    subgraph "Identity & Access Management"
        J[IAM Roles] -->|Grant permissions| I
        J -->|Service permissions| K[AWS Services]
        L[IAM Policies] -->|Define permissions| J
    end
    
    subgraph "Monitoring & Detection"
        I -->|Logs| M[CloudWatch Logs]
        I -->|Metrics| N[CloudWatch]
        F -->|Flow Logs| O[VPC Flow Logs]
        
        M -->|Analyze| P[GuardDuty]
        O -->|Analyze| P
        
        P -->|Detect threats| Q[Security Hub]
        R[Config] -->|Compliance| Q
        S[Inspector] -->|Vulnerabilities| Q
    end
    
    class A internet
    class B,C,G,H,J,L,P,Q,R,S security
    class D,E,F,I,K network
    class M,N,O monitoring
```

### 🛡️ AWS Security Components

1. **Edge Protection**: Defense against internet-based attacks
   - AWS Shield for DDoS protection
   - AWS WAF with managed rule sets:
     - AWSManagedRulesAmazonIpReputationList
     - AWSManagedRulesAnonymousIpList
     - AWSManagedRulesCommonRuleSet
     - AWSManagedRulesKnownBadInputsRuleSet
     - AWSManagedRulesLinuxRuleSet
     - AWSManagedRulesUnixRuleSet
   - Route 53 for secure DNS management
   - Certificate Manager for TLS certificates

2. **Network Security**: Layered defense within AWS
   - VPC with public/private subnet isolation
   - Network ACLs for subnet-level filtering
   - Security Groups for instance-level access control
   - VPC Endpoints for secure AWS service access
   - VPC Flow Logs for traffic monitoring

3. **Identity and Access Management**:
   - IAM roles with least privilege principle
   - Role-based access for EC2 instances
   - Service roles for AWS service integration
   - Secure parameter and secret access

4. **Threat Detection and Monitoring**:
   - GuardDuty for continuous threat detection
   - AWS Config for compliance monitoring
   - Inspector for vulnerability assessment
   - Security Hub for security posture management
   - CloudWatch for monitoring and alerting

5. **Hardened Infrastructure**:
   - Encrypted EBS volumes
   - IMDSv2 required for all EC2 instances
   - Security patch management
   - Automatic software updates

## 📊 Monitoring and Compliance Architecture

This diagram illustrates how security events are monitored, detected, and responded to across the system.

```mermaid
flowchart TD
    classDef logs fill:#c8e6c9,stroke:#333,stroke-width:1px
    classDef service fill:#a0c8e0,stroke:#333,stroke-width:1px
    classDef security fill:#ffecb3,stroke:#333,stroke-width:1px,color:black
    classDef data fill:#d1c4e9,stroke:#333,stroke-width:1px
    classDef user fill:#bbdefb,stroke:#333,stroke-width:1px
    
    subgraph "Event Sources"
        A1[Application Logs] -->|Stream| B[CloudWatch Logs]
        A2[VPC Flow Logs] -->|Stream| B
        A3[ALB Access Logs] -->|Store| C[S3 Buckets]
        A4[AWS API Calls] -->|Record| D[CloudTrail]
    end
    
    subgraph "Detection & Analysis"
        B -->|Process| E[CloudWatch Alarms]
        C -->|Analyze| F[GuardDuty]
        D -->|Analyze| F
        
        E -->|Trigger| G[SNS Notifications]
        F -->|Generate| H[Security Findings]
    end
    
    subgraph "Aggregation & Visualization"
        H -->|Collect| I[Security Hub]
        E -->|Display| J[CloudWatch Dashboards]
        
        I -->|Compliance status| K[NIST CSF Compliance]
        I -->|Security score| L[Security Posture]
    end
    
    subgraph "Response & Remediation"
        G -->|Alert| M[Admin Notification]
        I -->|Trigger| N[Auto Remediation]
        
        M -->|Manual action| O[Incident Response]
        N -->|Automatic fix| P[Security Controls]
    end
    
    class A1,A2,A3,A4,H logs
    class B,C,D,G,J,N service
    class E,F,I,O,P security
    class K,L data
    class M user
```

### 📈 Monitoring Components

1. **Logging and Monitoring**: Comprehensive visibility
   - CloudWatch Logs for application logs
   - VPC Flow Logs for network traffic
   - CloudTrail for API activity
   - ALB access logs for request tracking
   - Custom application event logging

2. **Security Event Detection**:
   - CloudWatch Alarms for threshold-based alerts
   - GuardDuty for threat detection
   - AWS Config for compliance checking
   - Security Hub for finding aggregation

3. **Compliance Framework Integration**:
   - NIST Cybersecurity Framework controls
   - ISO 27001 alignment
   - Automated compliance checking
   - Security posture dashboards

4. **Application Security Monitoring**:
   - Login attempt monitoring
   - User activity tracking
   - Session management
   - API request logging
   - Error tracking and analysis

5. **Alerting and Response**:
   - SNS notifications for security events
   - Automated remediation for common issues
   - Incident response procedures
   - Security control feedback loop

## 🔐 Application Security Controls

### Spring Security Configuration

The application uses Spring Security for authentication, authorization, and web security controls:

```xml
<http use-expressions="false">
    <csrf disabled="true" />
    <form-login />
    <anonymous />
    <logout />
    <headers>
        <frame-options policy="SAMEORIGIN" />
        <hsts include-subdomains="true" max-age-seconds="31536000" />
        <referrer-policy policy="no-referrer"/>
        <header name="Feature-Policy" value="geolocation 'none'; camera 'none'; microphone 'none'"/>
        <content-security-policy policy-directives="default-src 'unsafe-inline' 'self'; connect-src 'unsafe-inline' 'unsafe-eval' 'self' wss:; script-src 'unsafe-inline' 'unsafe-eval' 'self' https://w[...]
        <content-type-options/>
    </headers>
</http>
```

Key security headers implemented:
- **HSTS** (HTTP Strict Transport Security): Ensures browser only connects via HTTPS
- **Content Security Policy**: Restricts resource loading to specific trusted sources
- **X-Content-Type-Options**: Prevents MIME type sniffing
- **Referrer Policy**: Controls HTTP referrer information
- **Feature Policy**: Restricts browser feature usage

### 🛡️ Authentication Protections

The system implements multiple layers of authentication protection:

1. **Login Attempt Limiting**: 
   ```java
   private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_IP = "Max failed login attempts recent hour per ip";
   private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_SESSION = "Max failed login attempts recent hour per session";
   private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_USER = "Max failed login attempts recent hour per user";
   ```

2. **Password Validation**:
   ```java
   private final PasswordValidator passwordValidator = new PasswordValidator(new LengthRule(8, 64),
        new CharacterRule(EnglishCharacterData.UpperCase, 1), new CharacterRule(EnglishCharacterData.LowerCase, 1),
        new CharacterRule(EnglishCharacterData.Digit, 1), new CharacterRule(EnglishCharacterData.Special, 1),
        new WhitespaceRule());
   ```

3. **Multi-Factor Authentication**:
   ```java
   private static boolean verifyOtp(final LoginRequest serviceRequest, final String authKey) {
       boolean authorizedOtp = true;
       if (authKey != null) {
           final GoogleAuthenticator gAuth = new GoogleAuthenticator();
           if (!StringUtils.isBlank(serviceRequest.getOtpCode())
                   && StringUtils.isNumeric(serviceRequest.getOtpCode())) {
               authorizedOtp = gAuth.authorize(authKey,
                       Integer.parseInt(serviceRequest.getOtpCode()));
           } else {
               authorizedOtp = false;
           }
       }
       return authorizedOtp;
   }
   ```

4. **Secure Password Storage**:
   ```java
   private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   userAccount.setUserpassword(passwordEncoder.encode(userAccount.getUserId() + ".uuid" + serviceRequest.getUserpassword()));
   ```

### ⚙️ AWS Security Implementation

AWS infrastructure security as defined in CloudFormation:

1. **VPC Security Architecture**:
   - Public subnets for load balancers
   - Private subnets for application and database
   - Network ACLs and security groups for traffic control

2. **Security Groups**:
   ```json
   "WebServerSecurityGroup": {
       "Type": "AWS::EC2::SecurityGroup",
       "Properties": {
           "GroupDescription": "Allow access from load balancer and bastion as well as outbound HTTP and HTTPS traffic",
           "SecurityGroupIngress": [
               {
                   "Description": "Loadbalancer inbound access",
                   "IpProtocol": "tcp",
                   "FromPort": "8443",
                   "ToPort": "8443",
                   "SourceSecurityGroupId": {"Ref": "PublicLoadBalancerSecurityGroup"}
               }
           ]
       }
   }
   ```

3. **WAF Implementation**:
   ```json
   "BasicSecurityACL": {
       "Type": "AWS::WAFv2::WebACL",
       "Properties": {
           "Name": "BasicSecurityACL",
           "Scope": "REGIONAL",
           "Rules": [
               {
                   "Name": "RuleWithAWSManagedRulesAmazonIpReputationList",
                   "Priority": 0,
                   "Statement": {
                       "ManagedRuleGroupStatement": {
                           "VendorName": "AWS",
                           "Name": "AWSManagedRulesAmazonIpReputationList"
                       }
                   }
               }
           ]
       }
   }
   ```

4. **Database Encryption**:
   ```json
   "Database": {
       "Type": "AWS::RDS::DBInstance",
       "Properties": {
           "StorageEncrypted": "true",
           "KmsKeyId": {"Ref": "DBEncryptionKmsAlias"},
           "EnableCloudwatchLogsExports": ["postgresql", "upgrade"]
       }
   }
   ```

## 📜 Security Compliance Architecture

The CIA platform is designed to comply with major security frameworks:

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

### ISO 27001 Control Alignment

| Control ID | Description | Implementation |
|------------|-------------|----------------|
| A.5 | Information security policies | Documentation, compliance checks |
| A.9 | Access control | IAM, Spring Security |
| A.10 | Cryptography | KMS, TLS, password hashing |
| A.12 | Operations security | Patching, monitoring |
| A.13 | Communications security | Network security, encryption |
| A.14 | System acquisition and development | Secure SDLC |
| A.18 | Compliance | Security Hub, Config Rules |

## 🛡️ Defense-in-Depth Strategy

The security architecture follows the defense-in-depth principle with multiple layers of protection:

```mermaid
graph TD
    classDef layer fill:#ffecb3,stroke:#333,stroke-width:1px,color:black
    classDef user fill:#bbdefb,stroke:#333,stroke-width:1px
    
    A[User Request] -->|Layer 1| B[DNS Security]
    B -->|Layer 2| C[WAF Protection]
    C -->|Layer 3| D[Network Security]
    D -->|Layer 4| E[Host Security]
    E -->|Layer 5| F[Application Security]
    F -->|Layer 6| G[Data Security]
    
    class A user
    class B,C,D,E,F,G layer
```

### 🔒 Security Layer Details

1. **DNS Security Layer**
   - Route 53 with DNSSEC support
   - DNS query logging
   - Monitoring for DNS poisoning attempts

2. **WAF Protection Layer**
   - Rate limiting
   - IP reputation filtering
   - SQL injection and XSS protection
   - Bot control

3. **Network Security Layer**
   - VPC isolation
   - Network ACLs
   - Security Groups
   - Flow logging and monitoring

4. **Host Security Layer**
   - Hardened AMIs
   - Instance encryption
   - Systems Manager patching
   - IMDSv2 enforcement
   - Host-based monitoring

5. **Application Security Layer**
   - Spring Security framework
   - Authentication and authorization controls
   - Input validation
   - Session management
   - Security headers

6. **Data Security Layer**
   - Encryption at rest
   - Encryption in transit
   - Access controls
   - Secrets management
   - Data loss prevention

## 📈 Security Threat Modeling

### STRIDE Threat Analysis

| Threat Type | Controls |
|-------------|----------|
| **Spoofing** | Authentication, MFA, secure session management |
| **Tampering** | Encryption, integrity checks, WAF rules |
| **Repudiation** | Comprehensive logging, audit trails, CloudTrail |
| **Information Disclosure** | Encryption, access control, data classification |
| **Denial of Service** | WAF rate limiting, auto scaling, DDoS protection |
| **Elevation of Privilege** | Least privilege, role separation, input validation |

### Critical Data Flow Protection

```mermaid
graph TD
    classDef user fill:#bbdefb,stroke:#333,stroke-width:1px
    classDef network fill:#a0c8e0,stroke:#333,stroke-width:1px
    classDef data fill:#d1c4e9,stroke:#333,stroke-width:1px
    classDef security fill:#ffecb3,stroke:#333,stroke-width:1px,color:black
    classDef process fill:#c8e6c9,stroke:#333,stroke-width:1px
    
    A[User Credentials] -->|Encrypted TLS| B[Load Balancer]
    B -->|Encrypted TLS| C[Application Server]
    C -->|Bcrypt| D[Password Hash]
    D -->|Store| E[Database]
    
    F[Database Credentials] -->|Encrypted| G[Secrets Manager]
    G -->|Decrypt| C
    C -->|Encrypted TLS| E
    
    class A user
    class B,C network
    class D,E data
    class F process
    class G security
```

## 🔄 Security Operations and Maintenance

### Security Update Lifecycle

```mermaid
graph LR
    classDef event fill:#bbdefb,stroke:#333,stroke-width:1px
    classDef service fill:#a0c8e0,stroke:#333,stroke-width:1px
    classDef data fill:#d1c4e9,stroke:#333,stroke-width:1px
    classDef security fill:#ffecb3,stroke:#333,stroke-width:1px,color:black
    classDef process fill:#c8e6c9,stroke:#333,stroke-width:1px
    
    A[Security Patch Released] -->|Detection| B[AWS Inspector]
    B -->|Identification| C[Patch Needs]
    C -->|Scheduling| D[Maintenance Window]
    D -->|Deployment| E[SSM Patch Manager]
    E -->|Verification| F[Compliance Check]
    F -->|Documentation| G[Patch History]
    
    class A event
    class B,D,E service
    class C,G data
    class F process
```

### Automated Security Processes

1. **Automated Security Assessments**:
   - Daily vulnerability scans
   - Weekly compliance checks
   - Monthly penetration tests

2. **Continuous Monitoring**:
   - Real-time threat detection
   - Behavior analysis
   - Anomaly detection

3. **Automatic Remediation**:
   - Self-healing infrastructure
   - Automatic patching
   - Configuration correction

## 🌟 Security Architecture Best Practices

### Implemented Security Principles

1. **Zero Trust Architecture**
   - "Never trust, always verify" approach
   - Network segmentation
   - Least privilege access
   - Continuous validation

2. **Secure by Design**
   - Security integrated from project inception
   - Regular threat modeling
   - Security requirements as first-class constraints
   - Defensive programming practices

3. **Security Automation**
   - Automated security testing
   - Compliance as code
   - Infrastructure as code security checks
   - Continuous security monitoring

4. **Shift-Left Security**
   - Security integrated into CI/CD pipeline
   - Early vulnerability detection
   - Developer security training
   - Security gates in deployment process

## 📝 Conclusion

The Citizen Intelligence Agency employs a comprehensive, defense-in-depth security architecture that spans from application-level controls to infrastructure security. By implementing multiple layers of protection, we ensure the confidentiality, integrity, and availability of sensitive political data.

Key security highlights include:

- 🔐 Multi-factor authentication with sophisticated brute force protection
- 🛡️ AWS WAF and Shield protection against web-based attacks
- 🔒 Comprehensive encryption for data at rest and in transit
- 👁️ Continuous monitoring with GuardDuty, Inspector, and Security Hub
- 📜 Complete data integrity with Javers versioning and author attribution
- 🔍 Comprehensive user activity and security event tracking
- 📋 NIST CSF and ISO 27001 compliance alignment

For detailed implementation costs and specific AWS security services, refer to the [Financial Security Plan](FinancialSecurityPlan.md).
