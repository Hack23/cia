---
name: iso-27001-controls
description: Verify implementation of ISO 27001:2022 information security controls across CIA platform development and operations
license: Apache-2.0
---

# ISO 27001:2022 Controls Implementation Skill

## Purpose

This skill provides guidance for implementing and verifying ISO 27001:2022 Annex A controls within the CIA platform, ensuring systematic information security management aligned with Hack23 ISMS framework.

## When to Use This Skill

Apply this skill when:
- ✅ Implementing new security controls
- ✅ Conducting ISMS audits or reviews
- ✅ Responding to security incidents
- ✅ Preparing for ISO 27001 certification audits
- ✅ Reviewing security architecture changes
- ✅ Updating security documentation

## Key ISO 27001:2022 Controls for Software Development

### A.5 - Organizational Controls

**A.5.10 - Acceptable Use of Information**
- ✅ Document acceptable use policy for CIA platform
- ✅ Define data classification (Public, Internal, Confidential, Restricted)
- ✅ Specify usage restrictions for political data

**A.5.15 - Access Control**
- ✅ Implement RBAC (Role-Based Access Control)
- ✅ Enforce least privilege principle
- ✅ Regular access reviews (quarterly)

**A.5.17 - Authentication Information**
- ✅ Strong password policy (12+ chars, complexity)
- ✅ MFA for privileged accounts
- ✅ Secure password storage (bcrypt, Argon2)

**A.5.23 - Information Security for Cloud Services**
- ✅ AWS security configuration review
- ✅ Cloud provider security assessment
- ✅ Data sovereignty compliance (EU GDPR)

### A.8 - Technical Controls

**A.8.1 - User Endpoint Devices**
- ✅ Developer workstation security standards
- ✅ Encrypted disks (BitLocker, FileVault)
- ✅ Antivirus/EDR software required

**A.8.2 - Privileged Access Rights**
```java
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {
    
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER\n" +
                              "ROLE_USER > ROLE_GUEST");
        return hierarchy;
    }
}

@Service
public class PrivilegedOperationService {
    
    @PreAuthorize("hasRole('ADMIN')")
    @Audited
    public void modifySystemConfiguration(ConfigurationChange change) {
        // Log privileged action
        auditLogger.log("PRIVILEGED_ACTION", "System config modified", change);
        
        // Perform operation
        configurationRepository.save(change);
    }
}
```

**A.8.3 - Information Access Restriction**
```java
@Entity
@Table(name = "document")
public class Document {
    @Id
    private String id;
    
    @Enumerated(EnumType.STRING)
    private DataClassification classification; // PUBLIC, INTERNAL, CONFIDENTIAL, RESTRICTED
    
    private String ownerId;
    
    @ElementCollection
    private Set<String> authorizedUserIds;
}

@Service
public class DocumentAccessService {
    
    public Document getDocument(String documentId, String userId) {
        Document doc = documentRepository.findById(documentId)
            .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
        
        // Enforce access control based on classification
        if (!canAccess(doc, userId)) {
            auditLogger.logAccessDenied(userId, documentId);
            throw new AccessDeniedException("Insufficient permissions");
        }
        
        return doc;
    }
    
    private boolean canAccess(Document doc, String userId) {
        switch (doc.getClassification()) {
            case PUBLIC:
                return true;
            case INTERNAL:
                return userService.isInternalUser(userId);
            case CONFIDENTIAL:
                return doc.getAuthorizedUserIds().contains(userId);
            case RESTRICTED:
                return doc.getOwnerId().equals(userId) || 
                       userService.isAdmin(userId);
            default:
                return false;
        }
    }
}
```

**A.8.8 - Management of Technical Vulnerabilities**
- ✅ Weekly vulnerability scans (OWASP Dependency Check)
- ✅ CodeQL analysis on every PR
- ✅ SonarCloud quality gates enforced
- ✅ Security patches applied within 30 days

**A.8.9 - Configuration Management**
```yaml
# Document all configuration in Infrastructure as Code
# Store in version control (git)
# Example: AWS CloudFormation for infrastructure

AWSTemplateFormatVersion: '2010-09-09'
Description: 'CIA Platform Infrastructure - ISO 27001 Compliant'

Resources:
  # Database with encryption enabled (A.8.24)
  CIADatabase:
    Type: AWS::RDS::DBInstance
    Properties:
      Engine: postgres
      StorageEncrypted: true
      KmsKeyId: !Ref DatabaseEncryptionKey
      BackupRetentionPeriod: 30
      EnableCloudwatchLogsExports:
        - postgresql
      DeletionProtection: true
  
  # Application servers with security group restrictions
  AppSecurityGroup:
    Type: AWS::EC2::SecurityGroup
    Properties:
      GroupDescription: CIA Application Security Group
      SecurityGroupIngress:
        - IpProtocol: tcp
          FromPort: 443
          ToPort: 443
          CidrIp: 0.0.0.0/0  # HTTPS only
      SecurityGroupEgress:
        - IpProtocol: tcp
          FromPort: 443
          ToPort: 443
          DestinationSecurityGroupId: !Ref DatabaseSecurityGroup
```

**A.8.11 - Data Masking**
```java
@Component
public class DataMaskingService {
    
    public String maskPersonalId(String personalId) {
        if (personalId == null || personalId.length() < 12) return "***";
        return personalId.substring(0, 4) + "****" + personalId.substring(8);
    }
    
    public String maskEmail(String email) {
        if (email == null || !email.contains("@")) return "***@***";
        int atIndex = email.indexOf('@');
        String prefix = email.substring(0, Math.min(2, atIndex));
        String suffix = email.substring(atIndex);
        return prefix + "***" + suffix;
    }
    
    public String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() < 8) return "***";
        return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 2);
    }
}

// Use in logging
log.info("User accessed document: userId={}, documentId={}", 
    dataMaskingService.maskPersonalId(userId), documentId);
```

**A.8.23 - Web Filtering**
- ✅ Implement Content Security Policy (CSP)
- ✅ Configure CORS restrictions
- ✅ Enable XSS protection headers

```java
@Configuration
public class SecurityHeadersConfig {
    
    @Bean
    public SecurityFilterChain securityHeaders(HttpSecurity http) throws Exception {
        http.headers(headers -> headers
            .contentSecurityPolicy("default-src 'self'; " +
                                 "script-src 'self'; " +
                                 "style-src 'self'; " +
                                 "img-src 'self' data: https:; " +
                                 "font-src 'self'; " +
                                 "connect-src 'self'; " +
                                 "frame-ancestors 'none';")
            .xssProtection()
            .frameOptions().deny()
            .httpStrictTransportSecurity()
                .maxAgeInSeconds(31536000)
                .includeSubDomains(true)
                .preload(true)
        );
        return http.build();
    }
}
```

**A.8.24 - Use of Cryptography**
- ✅ TLS 1.2+ for all communications
- ✅ AES-256-GCM for data encryption
- ✅ bcrypt/Argon2 for password hashing
- ✅ RSA-4096 or Ed25519 for digital signatures

**A.8.28 - Secure Coding**
- ✅ Follow OWASP Top 10 guidelines
- ✅ Conduct security code reviews
- ✅ Use static analysis tools (SonarCloud, CodeQL)
- ✅ Input validation on all user inputs

### A.14 - System Acquisition, Development, and Maintenance

**A.14.2.1 - Secure Development Policy**

Required elements:
1. Security requirements gathering
2. Threat modeling (STRIDE)
3. Secure coding standards
4. Security testing (SAST, DAST)
5. Security code reviews
6. Vulnerability management

**A.14.2.5 - Secure System Engineering Principles**
- ✅ Defense in depth
- ✅ Least privilege
- ✅ Fail securely
- ✅ Separation of duties
- ✅ Economy of mechanism
- ✅ Complete mediation

**A.14.2.8 - System Security Testing**
```bash
# Automated security testing pipeline
# .github/workflows/security-testing.yml

name: Security Testing

on:
  pull_request:
  push:
    branches: [main]

jobs:
  sast:
    name: Static Application Security Testing
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      
      # CodeQL SAST
      - name: Initialize CodeQL
        uses: github/codeql-action/init@v3
        with:
          languages: java
          queries: security-and-quality
      
      - name: Build
        run: mvn clean compile -DskipTests
      
      - name: Perform CodeQL Analysis
        uses: github/codeql-action/analyze@v3
      
      # SonarCloud
      - name: SonarCloud Scan
        env:
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        run: mvn sonar:sonar -Dsonar.qualitygate.wait=true
      
      # OWASP Dependency Check
      - name: OWASP Dependency Check
        run: mvn org.owasp:dependency-check-maven:check
      
      - name: Upload Dependency Check Report
        uses: actions/upload-artifact@v4
        with:
          name: dependency-check-report
          path: target/dependency-check-report.html
  
  dast:
    name: Dynamic Application Security Testing
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      
      # Start application
      - name: Start Application
        run: |
          docker-compose up -d
          sleep 30
      
      # OWASP ZAP scan
      - name: ZAP Scan
        uses: zaproxy/action-baseline@v0.7.0
        with:
          target: 'http://localhost:8080'
          rules_file_name: '.zap/rules.tsv'
          cmd_options: '-a'
```

**A.14.2.9 - System Acceptance Testing**

Security acceptance criteria:
- ✅ All critical/high vulnerabilities resolved
- ✅ Security test cases passed
- ✅ Penetration testing completed
- ✅ Security documentation updated
- ✅ ISMS compliance verified

### A.16 - Information Security Incident Management

**A.16.1.4 - Assessment and Decision of Information Security Events**
```java
@Service
public class SecurityIncidentService {
    
    public void reportIncident(SecurityIncident incident) {
        // Assess severity
        IncidentSeverity severity = assessSeverity(incident);
        
        // Log to SIEM
        siemLogger.log(severity, incident);
        
        // Notify security team for high/critical incidents
        if (severity.isHighOrCritical()) {
            notificationService.notifySecurityTeam(incident);
        }
        
        // Create incident record
        incidentRepository.save(incident);
        
        // Initiate incident response if needed
        if (severity == IncidentSeverity.CRITICAL) {
            incidentResponseService.initiate(incident);
        }
    }
    
    private IncidentSeverity assessSeverity(SecurityIncident incident) {
        // Classify based on impact and likelihood
        if (incident.involvesDataBreach()) {
            return IncidentSeverity.CRITICAL;
        }
        if (incident.affectsAvailability()) {
            return IncidentSeverity.HIGH;
        }
        if (incident.involvesUnauthorizedAccess()) {
            return IncidentSeverity.MEDIUM;
        }
        return IncidentSeverity.LOW;
    }
}
```

## Control Implementation Checklist

Use this checklist for each ISO 27001 control:

1. **Control Identification**
   - ✅ Control reference (e.g., A.8.24)
   - ✅ Control objective documented
   - ✅ Applicability determined

2. **Implementation**
   - ✅ Technical controls implemented
   - ✅ Procedural controls documented
   - ✅ Responsibilities assigned

3. **Evidence Collection**
   - ✅ Configuration screenshots
   - ✅ Code samples
   - ✅ Policy documents
   - ✅ Audit logs

4. **Testing & Verification**
   - ✅ Control effectiveness tested
   - ✅ Gaps identified and remediated
   - ✅ Penetration testing results

5. **Documentation**
   - ✅ Statement of Applicability (SOA) updated
   - ✅ Risk treatment plan updated
   - ✅ ISMS documentation current

## Compliance Verification Scripts

```bash
#!/bin/bash
# iso27001-compliance-check.sh

echo "=== ISO 27001 Compliance Verification ==="

# A.8.8 - Check for known vulnerabilities
echo "Checking for vulnerabilities (A.8.8)..."
mvn org.owasp:dependency-check-maven:check
if [ $? -ne 0 ]; then
    echo "❌ FAIL: Vulnerabilities detected"
else
    echo "✅ PASS: No vulnerabilities"
fi

# A.8.24 - Verify TLS configuration
echo "Checking TLS configuration (A.8.24)..."
if grep -q "TLSv1.3,TLSv1.2" server.xml; then
    echo "✅ PASS: TLS 1.2+ configured"
else
    echo "❌ FAIL: Weak TLS configuration"
fi

# A.8.28 - Run security scans
echo "Running SAST scans (A.8.28)..."
mvn sonar:sonar -Dsonar.qualitygate.wait=true
if [ $? -eq 0 ]; then
    echo "✅ PASS: Code quality gate passed"
else
    echo "❌ FAIL: Code quality issues"
fi

# A.14.2.8 - Security test coverage
echo "Checking security test coverage..."
mvn test
coverage=$(grep -oP 'Coverage: \K[0-9]+' target/site/jacoco/index.html)
if [ "$coverage" -ge 80 ]; then
    echo "✅ PASS: Test coverage ${coverage}%"
else
    echo "❌ FAIL: Test coverage below 80%"
fi

echo "=== Compliance Check Complete ==="
```

## ISMS Documentation Requirements

Maintain these documents for ISO 27001 compliance:

1. **ISMS Policy** (✅ Required)
   - Information Security Policy
   - Purpose and scope
   - Management commitment

2. **Risk Assessment** (✅ Required)
   - Asset inventory
   - Threat analysis
   - Risk treatment plan

3. **Statement of Applicability (SOA)** (✅ Required)
   - List all Annex A controls
   - Justification for inclusion/exclusion
   - Implementation status

4. **Procedures** (✅ Required)
   - Access control procedure
   - Incident response procedure
   - Change management procedure
   - Backup and recovery procedure

5. **Records** (✅ Required)
   - Audit logs
   - Training records
   - Incident reports
   - Risk assessments
   - Management reviews

## Hack23 ISMS Policy References

- **Information Security Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/information-security-policy.md
- **Risk Management Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/risk-management-policy.md
- **All Policies**: https://github.com/Hack23/ISMS-PUBLIC/tree/main/policies

## References

- ISO/IEC 27001:2022: https://www.iso.org/standard/27001
- ISO/IEC 27002:2022: https://www.iso.org/standard/75652.html
- ISMS Compliance Mapping: ISMS_COMPLIANCE_MAPPING.md
