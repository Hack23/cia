#!/bin/bash
# Create remaining skills in batches

# GDPR Compliance
cat > gdpr-compliance/SKILL.md << 'EOF_GDPR'
---
name: gdpr-compliance
description: Ensure GDPR compliance for personal data processing in CIA platform with privacy-by-design principles
license: Apache-2.0
---

# GDPR Compliance Skill

## Purpose

Ensure CIA platform complies with EU General Data Protection Regulation (GDPR) requirements for processing personal data of politicians and users.

## When to Use

- ✅ Processing personal identifiable information (PII)
- ✅ Implementing user consent mechanisms
- ✅ Handling data subject requests
- ✅ International data transfers
- ✅ Privacy impact assessments

## GDPR Principles

### 1. Lawfulness, Fairness, and Transparency

```java
@Entity
public class DataProcessingRecord {
    private String purpose; // Why we process data
    private String legalBasis; // GDPR Article 6 basis
    private LocalDateTime consentDate;
    private boolean consentGiven;
    
    public enum LegalBasis {
        CONSENT,              // Article 6(1)(a)
        CONTRACT,             // Article 6(1)(b)
        LEGAL_OBLIGATION,     // Article 6(1)(c)
        VITAL_INTERESTS,      // Article 6(1)(d)
        PUBLIC_TASK,          // Article 6(1)(e) - Applicable for political monitoring
        LEGITIMATE_INTERESTS  // Article 6(1)(f)
    }
}
```

### 2. Purpose Limitation

```java
@Service
public class DataProcessingService {
    public void processPersonalData(PersonalData data, String purpose) {
        // Verify purpose matches original consent
        if (!data.getConsentedPurposes().contains(purpose)) {
            throw new GDPRViolationException(
                "Processing not covered by original consent"
            );
        }
        
        // Log processing activity
        auditLogger.logDataProcessing(data, purpose);
        
        // Process data
        performProcessing(data, purpose);
    }
}
```

### 3. Data Minimization

```java
@Entity
public class PoliticianPublicProfile {
    // Only collect necessary fields
    private String firstName;
    private String lastName;
    private String party;
    private String district;
    
    // DON'T collect unnecessary data:
    // ❌ private String homeAddress;
    // ❌ private String personalEmail;
    // ❌ private List<String> familyMembers;
}
```

### 4. Accuracy

```java
@Service
public class DataAccuracyService {
    @Scheduled(cron = "0 0 0 * * *") // Daily
    public void verifyDataAccuracy() {
        List<Politician> politicians = politicianRepository.findAll();
        
        for (Politician p : politicians) {
            // Sync with authoritative source (Riksdagen)
            PoliticianDTO official = riksdagenClient.getPolitician(p.getId());
            
            if (!p.isAccurate(official)) {
                p.updateFrom(official);
                p.setLastVerified(LocalDateTime.now());
                politicianRepository.save(p);
            }
        }
    }
}
```

### 5. Storage Limitation

```java
@Entity
public class UserAccount {
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
    
    @Column(name = "data_retention_until")
    private LocalDateTime retentionUntil;
    
    public void setRetentionPeriod(int years) {
        this.retentionUntil = LocalDateTime.now().plusYears(years);
    }
}

@Service
public class DataRetentionService {
    @Scheduled(cron = "0 0 3 * * *") // Daily at 3 AM
    public void enforceRetentionPolicies() {
        // Delete data past retention period
        List<UserAccount> expired = userRepository.findByRetentionUntilBefore(LocalDateTime.now());
        
        for (UserAccount user : expired) {
            // Anonymize or delete
            anonymizationService.anonymize(user);
            auditLogger.log("Data deleted per retention policy: " + user.getId());
        }
    }
}
```

### 6. Integrity and Confidentiality

```java
@Configuration
public class DataSecurityConfig {
    @Bean
    public BytesEncryptor piiEncryptor() {
        // Encrypt personal data at rest
        String key = System.getenv("PII_ENCRYPTION_KEY");
        String salt = System.getenv("PII_ENCRYPTION_SALT");
        return Encryptors.stronger(key, salt);
    }
}
```

## Data Subject Rights Implementation

### Right to Access (Article 15)

```java
@RestController
@RequestMapping("/api/gdpr")
public class GDPRController {
    
    @GetMapping("/data-export")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DataExport> exportUserData(
            @AuthenticationPrincipal UserDetails user) {
        
        // Generate complete data export
        DataExport export = gdprService.generateDataExport(user.getUsername());
        
        // Log access request
        auditLogger.logDataSubjectRequest("ACCESS", user.getUsername());
        
        return ResponseEntity.ok(export);
    }
}

@Service
public class GDPRService {
    public DataExport generateDataExport(String userId) {
        DataExport export = new DataExport();
        
        // Collect all personal data
        export.setPersonalInfo(userRepository.findById(userId));
        export.setActivityLog(activityRepository.findByUserId(userId));
        export.setPreferences(preferencesRepository.findByUserId(userId));
        export.setProcessingRecords(processingRepository.findByUserId(userId));
        
        return export;
    }
}
```

### Right to Erasure (Article 17)

```java
@DeleteMapping("/data")
@PreAuthorize("isAuthenticated()")
public ResponseEntity<Void> deleteUserData(
        @AuthenticationPrincipal UserDetails user,
        @RequestBody DeletionRequest request) {
    
    // Verify deletion is permissible
    if (!gdprService.canDelete(user.getUsername(), request.getReason())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    
    // Perform deletion
    gdprService.eraseUserData(user.getUsername());
    
    // Log deletion
    auditLogger.logDataSubjectRequest("ERASURE", user.getUsername());
    
    return ResponseEntity.noContent().build();
}
```

### Right to Data Portability (Article 20)

```java
@GetMapping("/data-export/json")
public ResponseEntity<byte[]> exportDataPortable(
        @AuthenticationPrincipal UserDetails user) {
    
    // Export in machine-readable format (JSON)
    DataExport export = gdprService.generateDataExport(user.getUsername());
    String json = objectMapper.writeValueAsString(export);
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setContentDisposition(
        ContentDisposition.attachment()
            .filename("user-data-" + user.getUsername() + ".json")
            .build()
    );
    
    return ResponseEntity.ok()
        .headers(headers)
        .body(json.getBytes(StandardCharsets.UTF_8));
}
```

### Right to Object (Article 21)

```java
@PostMapping("/object-processing")
public ResponseEntity<Void> objectToProcessing(
        @AuthenticationPrincipal UserDetails user,
        @RequestBody ObjectionRequest request) {
    
    // Record objection
    gdprService.recordObjection(user.getUsername(), request.getProcessingType());
    
    // Stop processing if applicable
    if (request.getProcessingType() == ProcessingType.MARKETING) {
        marketingService.unsubscribe(user.getUsername());
    }
    
    return ResponseEntity.accepted().build();
}
```

## Privacy by Design

```java
@Service
public class PrivacyByDesignService {
    
    /**
     * Pseudonymization for analytics
     */
    public String pseudonymize(String userId) {
        return DigestUtils.sha256Hex(userId + SALT);
    }
    
    /**
     * Anonymization for deleted accounts
     */
    public void anonymize(User user) {
        user.setFirstName("DELETED");
        user.setLastName("USER");
        user.setEmail("deleted@" + UUID.randomUUID() + ".invalid");
        user.setPhone(null);
        user.setPersonalId(null);
        user.setAnonymized(true);
        user.setAnonymizedAt(LocalDateTime.now());
    }
}
```

## Consent Management

```java
@Entity
public class ConsentRecord {
    @Id
    private String id;
    
    private String userId;
    
    @Enumerated(EnumType.STRING)
    private ConsentPurpose purpose;
    
    private boolean granted;
    
    private LocalDateTime consentDate;
    
    private LocalDateTime withdrawnDate;
    
    private String consentText; // Exact text shown to user
}

@Service
public class ConsentService {
    public void recordConsent(String userId, ConsentPurpose purpose, boolean granted) {
        ConsentRecord consent = new ConsentRecord();
        consent.setUserId(userId);
        consent.setPurpose(purpose);
        consent.setGranted(granted);
        consent.setConsentDate(LocalDateTime.now());
        consent.setConsentText(getConsentText(purpose));
        
        consentRepository.save(consent);
        
        auditLogger.logConsent(userId, purpose, granted);
    }
    
    public boolean hasConsent(String userId, ConsentPurpose purpose) {
        return consentRepository.findByUserIdAndPurpose(userId, purpose)
            .filter(c -> c.isGranted() && c.getWithdrawnDate() == null)
            .isPresent();
    }
}
```

## Data Protection Impact Assessment (DPIA)

Required when processing involves:
- ✅ Large-scale systematic monitoring
- ✅ Sensitive data processing
- ✅ High risk to rights and freedoms

```markdown
# DPIA Template for CIA Platform

## Processing Description
- **Purpose**: Political activity monitoring and transparency
- **Data Types**: Names, party affiliation, voting records, financial declarations
- **Data Subjects**: Swedish politicians
- **Legal Basis**: Public task (Article 6(1)(e))

## Risk Assessment
| Risk | Likelihood | Impact | Mitigation |
|------|-----------|--------|------------|
| Data breach | Medium | High | Encryption, access controls |
| Unauthorized access | Medium | High | MFA, audit logging |
| Inaccurate data | Low | Medium | Regular verification |

## Compliance Measures
- ✅ Encryption at rest and in transit
- ✅ Access control and authentication
- ✅ Regular security audits
- ✅ Incident response plan
- ✅ Data retention policies
```

## GDPR Compliance Checklist

- ✅ Privacy policy published
- ✅ Cookie consent implemented
- ✅ Data protection officer appointed
- ✅ DPIA completed for high-risk processing
- ✅ Data breach notification procedure
- ✅ International data transfer safeguards
- ✅ Regular compliance audits

## References

- GDPR Full Text: https://gdpr-info.eu/
- ICO GDPR Guidance: https://ico.org.uk/for-organisations/guide-to-data-protection/guide-to-the-general-data-protection-regulation-gdpr/
- Hack23 Data Protection Policy: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/data-protection-policy.md
EOF_GDPR

# Security Documentation
cat > security-documentation/SKILL.md << 'EOF_SECDOC'
---
name: security-documentation
description: Maintain comprehensive security documentation including SECURITY_ARCHITECTURE.md, THREAT_MODEL.md per Hack23 ISMS standards
license: Apache-2.0
---

# Security Documentation Skill

## Purpose

Ensure comprehensive, up-to-date security documentation exists for the CIA platform, enabling security reviews, compliance audits, and knowledge transfer.

## When to Use

- ✅ After security architecture changes
- ✅ When new threats identified
- ✅ During compliance audits
- ✅ For security onboarding
- ✅ Before major releases

## Required Security Documents

### 1. SECURITY.md (Required)

```markdown
# Security Policy

## Supported Versions

| Version | Supported          |
| ------- | ------------------ |
| 2.x.x   | :white_check_mark: |
| 1.x.x   | :x:                |

## Reporting a Vulnerability

**DO NOT** create public GitHub issues for security vulnerabilities.

Email: security@hack23.com
PGP Key: [Link to public key]

Expected response time: 48 hours
```

### 2. SECURITY_ARCHITECTURE.md (Required)

```markdown
# Security Architecture

## Authentication & Authorization

### Authentication Flow
\`\`\`
User → Login Form → Spring Security → BCrypt Verification → JWT Token → Access Granted
\`\`\`

### Authorization Model
- Role-Based Access Control (RBAC)
- Roles: GUEST, USER, ADMIN
- Method-level security with @PreAuthorize

## Data Protection

### Encryption
- At Rest: AES-256-GCM for sensitive fields
- In Transit: TLS 1.3 with strong cipher suites

### Data Classification
| Level | Examples | Protection |
|-------|----------|------------|
| Public | Politician names, party | No encryption |
| Internal | Analysis reports | Access control |
| Confidential | Personal IDs | Encrypted, audit logged |
| Restricted | API keys, passwords | Secrets manager |

## Network Security

### Architecture Diagram
\`\`\`
Internet → CloudFlare WAF → Load Balancer → App Servers (Private Subnet) → Database (Private Subnet)
\`\`\`

### Security Controls
- WAF: OWASP ModSecurity rules
- Rate Limiting: 100 req/minute per IP
- DDoS Protection: CloudFlare
- Network Segmentation: VPC with private subnets
```

### 3. THREAT_MODEL.md (Required)

```markdown
# Threat Model

## Assets
1. User credentials
2. Politician personal data
3. Voting records
4. API keys for external services

## Threat Actors
- **External Attackers**: Unauthorized access, data theft
- **Malicious Insiders**: Privilege abuse
- **Nation States**: APT, espionage

## STRIDE Analysis

### Spoofing
- **Threat**: Attacker impersonates legitimate user
- **Mitigation**: MFA, strong password policy, rate limiting

### Tampering
- **Threat**: Modification of voting records
- **Mitigation**: Data integrity checks, audit logging, database triggers

[... Continue for all STRIDE categories]

## Attack Trees

\`\`\`
Goal: Compromise Politician Data
├─ Exploit Authentication
│  ├─ Brute Force [Mitigated: Rate limiting]
│  └─ Session Hijacking [Mitigated: Secure cookies]
└─ Exploit Authorization
   └─ Privilege Escalation [Mitigated: RBAC]
\`\`\`
```

### 4. DATA_MODEL.md (Existing, ensure security section)

```markdown
# Data Model

## Security Considerations

### Encrypted Fields
- `politician.personal_id_encrypted` - AES-256-GCM
- `user.password_hash` - bcrypt (cost factor 12)

### Audit Logging
All changes to these tables are audited:
- `politician`
- `voting_record`
- `user`
- `system_configuration`
```

### 5. INCIDENT_RESPONSE.md (Required)

```markdown
# Incident Response Plan

## Severity Classification

| Severity | Criteria | Response Time |
|----------|----------|---------------|
| Critical | Data breach, system compromise | 1 hour |
| High | Authentication bypass | 4 hours |
| Medium | DoS attack | 24 hours |
| Low | Minor configuration issue | 1 week |

## Response Phases

1. **Detection & Analysis**
   - Monitor alerts
   - Classify severity
   - Assemble response team

2. **Containment**
   - Isolate affected systems
   - Revoke compromised credentials
   - Enable additional logging

3. **Eradication**
   - Remove malware/backdoors
   - Patch vulnerabilities
   - Reset credentials

4. **Recovery**
   - Restore from backups
   - Verify system integrity
   - Gradual service restoration

5. **Post-Incident**
   - Root cause analysis
   - Update controls
   - Document lessons learned
```

### 6. VULNERABILITY_MANAGEMENT.md (Required)

```markdown
# Vulnerability Management

## Vulnerability Classification

| CVSS Score | Severity | SLA |
|------------|----------|-----|
| 9.0-10.0 | Critical | 7 days |
| 7.0-8.9 | High | 30 days |
| 4.0-6.9 | Medium | 90 days |
| 0.1-3.9 | Low | 180 days |

## Scanning Schedule

- **Daily**: Automated dependency scans (OWASP Dependency Check)
- **Weekly**: CodeQL SAST scans
- **Monthly**: Full penetration testing
- **Quarterly**: External security assessment

## Remediation Process

1. Vulnerability identified
2. Risk assessment
3. Prioritization
4. Patch/fix development
5. Testing
6. Deployment
7. Verification
8. Documentation
```

### 7. ACCESS_CONTROL_MATRIX.md (Required)

```markdown
# Access Control Matrix

| Resource | Guest | User | Admin |
|----------|-------|------|-------|
| View public politician data | ✅ | ✅ | ✅ |
| View confidential data | ❌ | ❌ | ✅ |
| Create user account | ✅ | ❌ | ✅ |
| Modify user account | ❌ | Own | All |
| Delete user account | ❌ | Own | All |
| System configuration | ❌ | ❌ | ✅ |
| View audit logs | ❌ | ❌ | ✅ |
```

## Documentation Standards

### Markdown Format
- Use GitHub-Flavored Markdown
- Include table of contents for long documents
- Use code fences with language identifiers
- Add diagrams using Mermaid or ASCII art

### Diagrams

```mermaid
graph LR
    A[User] -->|HTTPS| B[Load Balancer]
    B --> C[App Server 1]
    B --> D[App Server 2]
    C --> E[PostgreSQL Primary]
    D --> E
    E --> F[PostgreSQL Replica]
```

### Update Frequency

| Document | Update Trigger | Review Frequency |
|----------|---------------|------------------|
| SECURITY.md | CVE published | Quarterly |
| SECURITY_ARCHITECTURE.md | Architecture change | Per release |
| THREAT_MODEL.md | New threat identified | Annually |
| INCIDENT_RESPONSE.md | Post-incident | After incidents |

## Documentation Checklist

Before marking security documentation complete:

- ✅ All required documents exist
- ✅ Documents are up-to-date (< 90 days old)
- ✅ Diagrams reflect current architecture
- ✅ Contact information is current
- ✅ Links work (no 404s)
- ✅ Code examples compile/run
- ✅ Reviewed by security team
- ✅ Approved by CISO/security officer

## ISMS Compliance

- **ISO 27001:2022 A.5.10**: Information security policies documented
- **ISO 27001:2022 A.16.1**: Incident management procedures documented
- **NIST CSF ID.GV-1**: Cybersecurity policy established

## References

- Hack23 Documentation Standards: https://github.com/Hack23/ISMS-PUBLIC/blob/main/standards/documentation-standard.md
- DOCUMENTATION_NAMING_CONVENTION.md
EOF_SECDOC

echo "Created GDPR and Security Documentation skills"
