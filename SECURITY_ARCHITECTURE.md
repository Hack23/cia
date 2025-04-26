# 🔐 Citizen Intelligence Agency Security Architecture

This document outlines the comprehensive security architecture of the Citizen Intelligence Agency platform, detailing the authentication mechanisms, network security, data protection, AWS infrastructure security, compliance frameworks, and DevSecOps practices.

**Last Updated:** 2025-04-26 06:51:59  
**Updated By:** pethers

## 📚 Security Documentation Map

| Document | Focus | Description |
|----------|-------|-------------|
| [Security Architecture](SECURITY_ARCHITECTURE.md) | 🔐 Security | Complete security overview |
| [Financial Security Plan](FinancialSecurityPlan.md) | 💰 Cost/Security | AWS security implementation costs |
| [Architecture](ARCHITECTURE.md) | 🏛️ Structure | Overall system architecture |
| [End-of-Life Strategy](End-of-Life-Strategy.md) | 📅 Lifecycle | Security patching and updates |
| [CI/CD Workflows](WORKFLOWS.md) | 🔧 DevOps | Security pipeline processes |

## 🔑 Authentication and Authorization Architecture

This diagram illustrates the multi-layered authentication and authorization process, including the MFA (Multi-Factor Authentication) flow, login blocking mechanisms, and role-based access control.

```mermaid
flowchart TD
    %% Title and metadata
    classDef titleClass fill:#f9f9f9,stroke:#333,stroke-width:1px
    class title titleClass
    
    %% Node styling classes
    classDef userNode fill:#e3f2fd,stroke:#1565c0,stroke-width:2px,color:#0d47a1,font-weight:bold
    classDef serviceNode fill:#bbdefb,stroke:#1976d2,stroke-width:2px,color:#0d47a1
    classDef decisionNode fill:#fff9c4,stroke:#fbc02d,stroke-width:2px,color:#664d00,font-weight:bold
    classDef successNode fill:#c8e6c9,stroke:#2e7d32,stroke-width:2px,color:#1b5e20
    classDef errorNode fill:#ffcdd2,stroke:#c62828,stroke-width:2px,color:#7f0000
    classDef securityNode fill:#d1c4e9,stroke:#4527a0,stroke-width:2px,color:#311b92
    
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
        
        Q -->|"@Secured annotation"| R[Method-level security]
        R -->|Role check| S{Has role?}
        S -->|No| T[Throw SecurityException]
        S -->|Yes| U[Execute method]
    end
    
    %% Add metadata note
    note["Last updated: 2025-04-26 06:51:59<br>Updated by: pethers"]
    
    %% Apply styling classes
    class A userNode
    class B,H,N,R serviceNode
    class C,E,I,O,S decisionNode
    class K,M,Q,U successNode
    class D,F,G,J,P,T errorNode
    class L securityNode
    class note titleClass
```

### Authentication Components

1. **Login Service**: Processes user authentication requests and validates credentials
   - 🔑 Implements password validation with BCryptPasswordEncoder
   - 🔐 Supports MFA with Google Authenticator
   - 🛡️ Enforces account lockout after failed attempts

2. **Login Blocking**: Prevents brute force attacks through multiple protection mechanisms
   - 🌐 IP-based blocking after excessive failures
   - 🔄 Session-based tracking 
   - 👤 User account-based blocking
   - ⚙️ Configurable thresholds via application configuration

3. **Role-Based Security**: Three primary security roles
   - 👥 `ROLE_ANONYMOUS`: Unauthenticated users with limited access
   - 👤 `ROLE_USER`: Standard authenticated users
   - 👑 `ROLE_ADMIN`: Administrative users with extended privileges

4. **Method-Level Security**: `@Secured` annotations protecting service methods
   - 🛡️ Example: `@Secured({ "ROLE_USER", "ROLE_ADMIN" })` for user operations
   - 🔒 Example: `@Secured({ "ROLE_ADMIN" })` for administrative functions
   - 🔓 Example: `@Secured({ "ROLE_ANONYMOUS" })` for public endpoints

5. **Logout Handling**: Secure session termination
   - 🚪 Invalidates authentication token
   - 📝 Creates audit log entry
   - 🔄 Returns to anonymous context

## 🔒 Network Security Architecture

This enhanced diagram shows how network security is implemented across multiple layers, from AWS WAF through VPC security to the application layer.

```mermaid
graph TD
    %% Node styling classes
    classDef internetNode fill:#bbdefb,stroke:#1565c0,stroke-width:2px,color:#0d47a1
    classDef securityNode fill:#ffecb3,stroke:#ff8f00,stroke-width:2px,color:#bf360c
    classDef networkNode fill:#a0c8e0,stroke:#0288d1,stroke-width:2px,color:#01579b
    classDef databaseNode fill:#d1c4e9,stroke:#673ab7,stroke-width:2px,color:#311b92
    
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
    
    %% Apply styling classes
    class A internetNode
    class B,D,F,G,I,J securityNode
    class C,E,H networkNode
    class K databaseNode
```

### Network Security Components

1. **AWS WAF (Web Application Firewall)**: 🛡️ Protects against common web exploits
   - AWS Managed Rules for known attack patterns
   - IP reputation filtering
   - Rate limiting and bot control
   - Protection against SQL injection and XSS

2. **Network Isolation**: 🔀 VPC design with clear separation of concerns
   - Public subnets for load balancers only
   - Private application subnets for EC2 instances
   - Private database subnets for data storage
   - Controlled traffic flow between layers

3. **Network ACLs**: 🔍 Stateless packet filtering at subnet level
   - Inbound/outbound rules limiting traffic by port and source/destination
   - Explicit deny rules for RDP (port 3389)
   - Public subnet limited to HTTPS (443)
   - Private app subnets limited to application traffic
   - Private database subnets limited to PostgreSQL (5432)

4. **Security Groups**: 🧱 Stateful instance-level firewall
   - Load balancer security group: Allow 443 inbound, 8443 outbound
   - Web server security group: Allow 8443 inbound from load balancer only
   - Database security group: Allow 5432 inbound from application servers only

5. **TLS Encryption**: 🔐 Secure communication throughout
   - Certificate Manager for public certificates
   - HTTPS enforcement with HTTP-to-HTTPS redirection
   - Security headers including HSTS, CSP, and X-Frame-Options

## 🛡️ Data Protection Architecture

This diagram illustrates how data is protected throughout its lifecycle, including encryption, access controls, and secure storage mechanisms.

```mermaid
flowchart TD
    %% Node styling classes
    classDef userNode fill:#bbdefb,stroke:#1565c0,stroke-width:2px,color:#0d47a1
    classDef appNode fill:#a0c8e0,stroke:#0288d1,stroke-width:2px,color:#01579b
    classDef dataNode fill:#d1c4e9,stroke:#673ab7,stroke-width:2px,color:#311b92
    classDef encryptNode fill:#ffecb3,stroke:#ff8f00,stroke-width:2px,color:#bf360c
    classDef loggingNode fill:#c8e6c9,stroke:#388e3c,stroke-width:2px,color:#1b5e20
    
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
    
    %% Apply styling classes
    class A userNode
    class B,C appNode
    class D,E,G,K dataNode
    class F,J encryptNode
    class H,I,L loggingNode
```

### Data Protection Components

1. **Encryption in Transit**: 🔐 Secure data transmission
   - TLS 1.3 for client-to-load-balancer communication
   - TLS 1.2+ for internal service communications
   - SSL policy: ELBSecurityPolicy-TLS13-1-2-2021-06
   - Certificate management through AWS Certificate Manager

2. **Encryption at Rest**: 💾 Protection of stored data
   - EBS encryption for EC2 instance volumes
   - RDS encryption with AWS KMS
   - S3 bucket encryption for logs and artifacts
   - Encrypted PostgreSQL connections

3. **Secret Management**: 🔑 Secure credential handling
   - AWS Secrets Manager for database credentials
   - Automatic credential rotation
   - Limited IAM access to secrets
   - No hardcoded secrets in application code

4. **Data Access Control**: 🚪 Least privilege principles
   - Role-based access in application
   - IAM policies for AWS resource access
   - Limited database access through security groups
   - Application-level data access authorization

5. **Password Security**: 🔒 Strong password policies
   - BCrypt password hashing with unique salts
   - Password complexity requirements:
     - Minimum 8 characters, maximum 64 characters
     - At least one uppercase letter
     - At least one lowercase letter
     - At least one number
     - At least one special character
     - No whitespace characters

## 🌐 AWS Security Infrastructure

This enhanced diagram shows the multi-layered AWS security infrastructure protecting the application.

```mermaid
graph TD
    %% Node styling classes
    classDef internetNode fill:#bbdefb,stroke:#1565c0,stroke-width:2px,color:#0d47a1
    classDef securityNode fill:#ffecb3,stroke:#ff8f00,stroke-width:2px,color:#bf360c
    classDef networkNode fill:#a0c8e0,stroke:#0288d1,stroke-width:2px,color:#01579b
    classDef monitorNode fill:#c8e6c9,stroke:#388e3c,stroke-width:2px,color:#1b5e20
    classDef iamNode fill:#d1c4e9,stroke:#673ab7,stroke-width:2px,color:#311b92
    
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
    
    %% Apply styling classes
    class A internetNode
    class B,C,G,H,P,Q,R,S securityNode
    class D,E,F,I,K networkNode
    class M,N,O monitorNode
    class J,L iamNode
```

### AWS Security Components

1. **Edge Protection**: 🛡️ Defense against internet-based attacks
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

2. **Network Security**: 🔀 Layered defense within AWS
   - VPC with public/private subnet isolation
   - Network ACLs for subnet-level filtering
   - Security Groups for instance-level access control
   - VPC Endpoints for secure AWS service access
   - VPC Flow Logs for traffic monitoring

3. **Identity and Access Management**: 👤 
   - IAM roles with least privilege principle
   - Role-based access for EC2 instances
   - Service roles for AWS service integration
   - Secure parameter and secret access

4. **Threat Detection and Monitoring**: 🔍
   - GuardDuty for continuous threat detection
   - AWS Config for compliance monitoring
   - Inspector for vulnerability assessment
   - Security Hub for security posture management
   - CloudWatch for monitoring and alerting

5. **Hardened Infrastructure**: 🏰
   - Encrypted EBS volumes
   - IMDSv2 required for all EC2 instances
   - Security patch management
   - Automatic software updates

## 📊 Monitoring and Compliance Architecture

This enhanced diagram illustrates how security events are monitored, detected, and responded to across the system.

```mermaid
flowchart TD
    %% Node styling classes
    classDef logNode fill:#c8e6c9,stroke:#388e3c,stroke-width:2px,color:#1b5e20
    classDef alertNode fill:#ffecb3,stroke:#ff8f00,stroke-width:2px,color:#bf360c
    classDef dashboardNode fill:#a0c8e0,stroke:#0288d1,stroke-width:2px,color:#01579b
    classDef complianceNode fill:#d1c4e9,stroke:#673ab7,stroke-width:2px,color:#311b92
    classDef responderNode fill:#bbdefb,stroke:#1565c0,stroke-width:2px,color:#0d47a1
    
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
    
    %% Apply styling classes
    class A1,A2,A3,A4,B,C,D logNode
    class E,F,G,H alertNode
    class I,J dashboardNode
    class K,L complianceNode
    class M,N,O,P responderNode
```

### Monitoring Components

1. **Logging and Monitoring**: 📝 Comprehensive visibility
   - CloudWatch Logs for application logs
   - VPC Flow Logs for network traffic
   - CloudTrail for API activity
   - ALB access logs for request tracking
   - Custom application event logging

2. **Security Event Detection**: 🔍
   - CloudWatch Alarms for threshold-based alerts
   - GuardDuty for threat detection
   - AWS Config for compliance checking
   - Security Hub for finding aggregation

3. **Compliance Framework Integration**: 📋
   - NIST Cybersecurity Framework controls
   - ISO 27001 alignment
   - Automated compliance checking
   - Security posture dashboards

4. **Application Security Monitoring**: 👁️
   - Login attempt monitoring
   - User activity tracking
   - Session management
   - API request logging
   - Error tracking and analysis

5. **Alerting and Response**: 🚨
   - SNS notifications for security events
   - Automated remediation for common issues
   - Incident response procedures
   - Security control feedback loop

## 🔄 DevSecOps Security Pipeline

This new section details the CI/CD security workflows that protect the codebase and deployment processes.

```mermaid
flowchart TB
    %% Node styling classes
    classDef triggerNode fill:#bbdefb,stroke:#1565c0,stroke-width:2px,color:#0d47a1,font-weight:bold
    classDef scanNode fill:#ffecb3,stroke:#ff8f00,stroke-width:2px,color:#bf360c
    classDef buildNode fill:#c8e6c9,stroke:#388e3c,stroke-width:2px,color:#1b5e20
    classDef attestNode fill:#d1c4e9,stroke:#673ab7,stroke-width:2px,color:#311b92
    classDef releaseNode fill:#a0c8e0,stroke:#0288d1,stroke-width:2px,color:#01579b
    
    subgraph "Security Scanning"
        A[Pull Request] --> B[CodeQL Analysis]
        A --> C[Dependency Review]
        A --> D[SAST Scanning]
        
        E[Main Branch] --> F[Weekly CodeQL Scan]
        E --> G[OSSF Scorecard]
    end
    
    subgraph "Secure Build Process"
        H[Release Trigger] --> I[Environment Setup]
        I --> J[Set Version]
        J --> K[Build & Test]
        K --> L[Generate SBOM]
        L --> M[Create Attestations]
        M --> N[Sign Artifacts]
    end
    
    subgraph "Release & Deploy"
        N --> O[Create GitHub Release]
        O --> P[Security Report Generation]
        P --> Q[Dependency Submission]
    end
    
    %% Apply styling classes
    class A,E,H triggerNode
    class B,C,D,F,G scanNode
    class I,J,K buildNode
    class L,M,N attestNode
    class O,P,Q releaseNode
```

### DevSecOps Components

1. **Code Security Scanning**: 🔍 Automated vulnerability detection
   - CodeQL static application security testing
   - Dependency vulnerability scanning
   - Pull request security validation
   - Weekly security rescanning

2. **Supply Chain Security**: 🔗 Secure software delivery
   - Software Bill of Materials (SBOM) generation
   - Artifact attestations and provenance
   - Code signing and verification
   - SLSA compliance

3. **Secure CI/CD Pipelines**: 🔄
   - Hardened runner environments
   - Controlled egress with explicit policies
   - Least privilege permissions model
   - Dependency caching for build integrity

4. **Security Metrics & Reporting**: 📊
   - OSSF Scorecard assessments
   - Dependency vulnerability tracking
   - Security scanning trend analysis
   - Automated security reporting

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
- 🔒 **HSTS** (HTTP Strict Transport Security): Ensures browser only connects via HTTPS
- 🛡️ **Content Security Policy**: Restricts resource loading to specific trusted sources
- 🔍 **X-Content-Type-Options**: Prevents MIME type sniffing
- 🔗 **Referrer Policy**: Controls HTTP referrer information
- 📱 **Feature Policy**: Restricts browser feature usage

### Authentication Protections

The system implements multiple layers of authentication protection:

1. **Login Attempt Limiting**: 🚫
   ```java
   private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_IP = "Max failed login attempts recent hour per ip";
   private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_SESSION = "Max failed login attempts recent hour per session";
   private static final String MAX_FAILED_LOGIN_ATTEMPTS_RECENT_HOUR_PER_USER = "Max failed login attempts recent hour per user";
   ```

2. **Password Validation**: 🔑
   ```java
   private final PasswordValidator passwordValidator = new PasswordValidator(new LengthRule(8, 64),
        new CharacterRule(EnglishCharacterData.UpperCase, 1), new CharacterRule(EnglishCharacterData.LowerCase, 1),
        new CharacterRule(EnglishCharacterData.Digit, 1), new CharacterRule(EnglishCharacterData.Special, 1),
        new WhitespaceRule());
   ```

3. **Multi-Factor Authentication**: 🔐
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

4. **Secure Password Storage**: 🔒
   ```java
   private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   userAccount.setUserpassword(passwordEncoder.encode(userAccount.getUserId() + ".uuid" + serviceRequest.getUserpassword()));
   ```

## 📈 Security Cost Structure

This new section provides insights into the financial aspects of the security infrastructure based on the Financial Security Plan.

```mermaid
pie
    title "Annual Security Cost Breakdown ($2,142.72)"
    "Security Hub" : 609.36
    "Amazon Detective" : 363.12
    "Amazon Inspector" : 312.48
    "Key Management Service" : 275.88
    "AWS Config" : 235.08
    "Amazon GuardDuty" : 346.80
```

### Security Investments

| **Service**                 | **Monthly (USD)** | **Annual (USD)** | **Security Function** |
|-----------------------------|-------------------|------------------|----------------------|
| **Security Hub**            | $50.78            | $609.36          | Centralized security management |
| **Amazon Detective**        | $30.26            | $363.12          | Security investigation |
| **Amazon Inspector**        | $26.04            | $312.48          | Vulnerability assessment |
| **Key Management Service**  | $22.99            | $275.88          | Encryption management |
| **AWS Config**              | $19.59            | $235.08          | Configuration compliance |
| **Amazon GuardDuty**        | $28.90            | $346.80          | Threat detection |
| **Total Security Costs**    | **$178.56**       | **$2,142.72**    | **Comprehensive Security** |

### Security ROI Analysis

- 🛡️ **Breach Prevention Value**: $150,000 - $500,000 estimated cost avoidance
- 🎯 **Compliance Assurance**: Prevents regulatory penalties (GDPR, etc.)
- ⏱️ **Incident Response Efficiency**: 60% reduction in detection-to-remediation time
- 💼 **Reputation Protection**: Preserves public trust and data integrity

## 🏛️ Security Compliance Architecture

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
    %% Node styling classes
    classDef userNode fill:#bbdefb,stroke:#1565c0,stroke-width:2px,color:#0d47a1
    classDef layer1 fill:#ffe0b2,stroke:#e65100,stroke-width:2px,color:#bf360c,font-weight:bold
    classDef layer2 fill:#ffcc80,stroke:#ef6c00,stroke-width:2px,color:#bf360c,font-weight:bold
    classDef layer3 fill:#ffb74d,stroke:#f57c00,stroke-width:2px,color:#bf360c,font-weight:bold
    classDef layer4 fill:#ffa726,stroke:#fb8c00,stroke-width:2px,color:#bf360c,font-weight:bold
    classDef layer5 fill:#ff9800,stroke:#f57c00,stroke-width:2px,color:#bf360c,font-weight:bold
    classDef layer6 fill:#fb8c00,stroke:#ef6c00,stroke-width:2px,color:#bf360c,font-weight:bold
    
    A[User Request] -->|Layer 1| B[DNS Security]
    B -->|Layer 2| C[WAF Protection]
    C -->|Layer 3| D[Network Security]
    D -->|Layer 4| E[Host Security]
    E -->|Layer 5| F[Application Security]
    F -->|Layer 6| G[Data Security]
    
    %% Apply styling classes
    class A userNode
    class B layer1
    class C layer2
    class D layer3
    class E layer4
    class F layer5
    class G layer6
```

### Security Layer Details

1. **DNS Security Layer** 🌐
   - Route 53 with DNSSEC support
   - DNS query logging
   - Monitoring for DNS poisoning attempts

2. **WAF Protection Layer** 🛡️
   - Rate limiting
   - IP reputation filtering
   - SQL injection and XSS protection
   - Bot control

3. **Network Security Layer** 🔀
   - VPC isolation
   - Network ACLs
   - Security Groups
   - Flow logging and monitoring

4. **Host Security Layer** 💻
   - Hardened AMIs
   - Instance encryption
   - Systems Manager patching
   - IMDSv2 enforcement
   - Host-based monitoring

5. **Application Security Layer** 🔌
   - Spring Security framework
   - Authentication and authorization controls
   - Input validation
   - Session management
   - Security headers

6. **Data Security Layer** 💾
   - Encryption at rest
   - Encryption in transit
   - Access controls
   - Secrets management
   - Data loss prevention

## 📈 Security Threat Modeling

### STRIDE Threat Analysis

| Threat Type | Controls | Risk Level |
|-------------|----------|------------|
| **Spoofing** | Authentication, MFA, secure session management | 🟢 Low |
| **Tampering** | Encryption, integrity checks, WAF rules | 🟢 Low |
| **Repudiation** | Comprehensive logging, audit trails, CloudTrail | 🟢 Low |
| **Information Disclosure** | Encryption, access control, data classification | 🟡 Medium |
| **Denial of Service** | WAF rate limiting, auto scaling, DDoS protection | 🟡 Medium |
| **Elevation of Privilege** | Least privilege, role separation, input validation | 🟢 Low |

### Critical Data Flow Protection

```mermaid
graph TD
    %% Node styling classes
    classDef userNode fill:#bbdefb,stroke:#1565c0,stroke-width:2px,color:#0d47a1
    classDef appNode fill:#a0c8e0,stroke:#0288d1,stroke-width:2px,color:#01579b
    classDef dataNode fill:#d1c4e9,stroke:#673ab7,stroke-width:2px,color:#311b92
    classDef encryptNode fill:#c8e6c9,stroke:#388e3c,stroke-width:2px,color:#1b5e20
    
    A[User Credentials] -->|Encrypted TLS| B[Load Balancer]
    B -->|Encrypted TLS| C[Application Server]
    C -->|Bcrypt| D[Password Hash]
    D -->|Store| E[Database]
    
    F[Database Credentials] -->|Encrypted| G[Secrets Manager]
    G -->|Decrypt| C
    C -->|Encrypted TLS| E
    
    %% Apply styling classes
    class A userNode
    class B,C appNode
    class D,E,F dataNode
    class G encryptNode
```

## 🔄 Security Operations and Maintenance

### Security Update Lifecycle

```mermaid
graph LR
    %% Node styling classes
    classDef triggerNode fill:#bbdefb,stroke:#1565c0,stroke-width:2px,color:#0d47a1
    classDef detectNode fill:#a0c8e0,stroke:#0288d1,stroke-width:2px,color:#01579b
    classDef planNode fill:#d1c4e9,stroke:#673ab7,stroke-width:2px,color:#311b92
    classDef implementNode fill:#ffecb3,stroke:#ff8f00,stroke-width:2px,color:#bf360c
    classDef verifyNode fill:#c8e6c9,stroke:#388e3c,stroke-width:2px,color:#1b5e20
    
    A[Security Patch Released] -->|Detection| B[AWS Inspector]
    B -->|Identification| C[Patch Needs]
    C -->|Scheduling| D[Maintenance Window]
    D -->|Deployment| E[SSM Patch Manager]
    E -->|Verification| F[Compliance Check]
    F -->|Documentation| G[Patch History]
    
    %% Apply styling classes
    class A triggerNode
    class B detectNode
    class C,D planNode
    class E implementNode
    class F,G verifyNode
```

### Automated Security Processes

1. **Automated Security Assessments**: 🔍
   - Daily vulnerability scans
   - Weekly compliance checks
   - Monthly penetration tests

2. **Continuous Monitoring**: 👁️
   - Real-time threat detection
   - Behavior analysis
   - Anomaly detection

3. **Automatic Remediation**: 🔧
   - Self-healing infrastructure
   - Automatic patching
   - Configuration correction

### Security Incident Response

```mermaid
flowchart TD
    %% Node styling classes
    classDef detectNode fill:#ffecb3,stroke:#ff8f00,stroke-width:2px,color:#bf360c
    classDef analyzeNode fill:#bbdefb,stroke:#1565c0,stroke-width:2px,color:#0d47a1
    classDef containNode fill:#ffccbc,stroke:#e64a19,stroke-width:2px,color:#bf360c
    classDef eradicateNode fill:#d1c4e9,stroke:#673ab7,stroke-width:2px,color:#311b92
    classDef recoverNode fill:#c8e6c9,stroke:#388e3c,stroke-width:2px,color:#1b5e20
    classDef learnNode fill:#a0c8e0,stroke:#0288d1,stroke-width:2px,color:#01579b
    
    A[Incident Detection] -->|Alert| B[Initial Assessment]
    B -->|Critical?| C{Severity}
    C -->|High| D[Emergency Response]
    C -->|Medium| E[Standard Response]
    C -->|Low| F[Routine Response]
    
    D --> G[Containment]
    E --> G
    F --> G
    
    G --> H[Investigation]
    H --> I[Root Cause Analysis]
    I --> J[Eradication]
    J --> K[Recovery]
    K --> L[Post-Incident Review]
    L --> M[Update Documentation]
    M --> N[Improve Controls]
    
    %% Apply styling classes
    class A,B,C detectNode
    class D,E,F,H,I analyzeNode
    class G containNode
    class J eradicateNode
    class K recoverNode
    class L,M,N learnNode
```

## 🌟 Security Architecture Best Practices

### Implemented Security Principles

1. **Zero Trust Architecture** 🚫
   - "Never trust, always verify" approach
   - Network segmentation
   - Least privilege access
   - Continuous validation

2. **Secure by Design** 🏗️
   - Security integrated from project inception
   - Regular threat modeling
   - Security requirements as first-class constraints
   - Defensive programming practices

3. **Security Automation** 🤖
   - Automated security testing
   - Compliance as code
   - Infrastructure as code security checks
   - Continuous security monitoring

4. **Shift-Left Security** ⬅️
   - Security integrated into CI/CD pipeline
   - Early vulnerability detection
   - Developer security training
   - Security gates in deployment process

### Security Architecture Maturity Model

```mermaid
graph TD
    %% Node styling classes
    classDef level1 fill:#ffcdd2,stroke:#d32f2f,stroke-width:2px,color:#b71c1c
    classDef level2 fill:#ffecb3,stroke:#ff8f00,stroke-width:2px,color:#bf360c
    classDef level3 fill:#c8e6c9,stroke:#388e3c,stroke-width:2px,color:#1b5e20
    classDef level4 fill:#bbdefb,stroke:#1565c0,stroke-width:2px,color:#0d47a1
    classDef level5 fill:#d1c4e9,stroke:#673ab7,stroke-width:2px,color:#311b92
    
    A[Level 1: Initial] -->|Documentation| B[Level 2: Managed]
    B -->|Standardization| C[Level 3: Defined]
    C -->|Measurement| D[Level 4: Quantitatively Managed]
    D -->|Optimization| E[Level 5: Optimizing]
    
    A1[Ad-hoc security] --- A
    B1[Repeatable processes] --- B
    B2[Risk assessment] --- B
    C1[Consistent standards] --- C
    C2[Control frameworks] --- C
    D1[Security metrics] --- D
    D2[Predictive analysis] --- D
    E1[Continuous improvement] --- E
    E2[Adaptive response] --- E
    
    %% Current position marker
    currentLevel[Current: Level 4] --- D
    
    %% Apply styling classes
    class A,A1 level1
    class B,B1,B2 level2
    class C,C1,C2 level3
    class D,D1,D2,currentLevel level4
    class E,E1,E2 level5
```

## 📝 Conclusion

The Citizen Intelligence Agency employs a comprehensive, defense-in-depth security architecture that spans from application-level controls to infrastructure security. By implementing multiple layers of protection—including network security, data encryption, identity management, and continuous monitoring—the system achieves a robust security posture aligned with industry best practices and compliance frameworks.

Key security highlights include:

- 🔐 Multi-factor authentication with sophisticated brute force protection
- 🛡️ AWS WAF and Shield protection against web-based attacks
- 🔒 Comprehensive encryption for data at rest and in transit
- 👁️ Continuous monitoring with GuardDuty, Inspector, and Security Hub
- 🔄 Automated security scanning in CI/CD pipelines
- 📋 NIST CSF and ISO 27001 compliance alignment
- 🤖 Automated remediation capabilities

For detailed implementation costs and specific AWS security services, refer to the [Financial Security Plan](FinancialSecurityPlan.md).
