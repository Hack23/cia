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
