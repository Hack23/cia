---
name: data-protection
description: Data classification (CIA triad), GDPR privacy by design, encryption standards, data lifecycle management
license: Apache-2.0
---

# Data Protection Skill

## Purpose

This skill provides comprehensive data protection guidance for the CIA platform, covering data classification, GDPR privacy by design, encryption standards, and data lifecycle management. It ensures political intelligence data is handled according to Hack23 ISMS classification and data protection policies.

## When to Use This Skill

Apply this skill when:
- ✅ Handling personal data of politicians or citizens
- ✅ Designing database schemas with sensitive fields
- ✅ Implementing data import/export functionality
- ✅ Configuring data retention or deletion policies
- ✅ Integrating external data sources (Riksdagen, World Bank)
- ✅ Processing election or voting records
- ✅ Implementing caching strategies for sensitive data

Do NOT use for:
- ❌ Public open data with no personal information
- ❌ Static UI component changes
- ❌ Build system or CI/CD pipeline changes

## Data Classification Framework

### CIA Platform Data Categories

```
Classification Levels (Hack23 ISMS)
│
├─ PUBLIC
│  ├─ Parliamentary voting records
│  ├─ Published committee documents
│  ├─ Official election results
│  └─ World Bank economic indicators
│
├─ INTERNAL
│  ├─ Aggregated analysis results
│  ├─ Risk scoring algorithms
│  ├─ Platform usage analytics
│  └─ System configuration data
│
├─ CONFIDENTIAL
│  ├─ User account credentials
│  ├─ Session tokens and API keys
│  ├─ Audit logs with user actions
│  └─ Internal security assessments
│
└─ RESTRICTED
   ├─ Database credentials
   ├─ Encryption keys (KMS)
   ├─ AWS access credentials
   └─ Security incident data
```

### Classification Decision Tree

```
Data Classification Decision
│
├─→ Is it publicly available from official sources?
│   ├─ YES → PUBLIC
│   └─ NO ↓
│
├─→ Does it contain personal identifiers?
│   ├─ YES → CONFIDENTIAL (minimum)
│   └─ NO ↓
│
├─→ Is it a credential, key, or secret?
│   ├─ YES → RESTRICTED
│   └─ NO ↓
│
└─→ Is it internal analysis or configuration?
    ├─ YES → INTERNAL
    └─ NO → Assess case-by-case
```

## GDPR Privacy by Design

### Seven Principles Applied to CIA Platform

1. **Proactive, not reactive** — Embed privacy into political data imports
2. **Privacy as default** — Minimize personal data collection
3. **Privacy embedded in design** — Use pseudonymization where possible
4. **Full functionality** — Privacy without sacrificing analysis quality
5. **End-to-end security** — Encrypt data at rest and in transit
6. **Visibility and transparency** — Document all data processing activities
7. **Respect for user privacy** — Provide data access and deletion mechanisms

### Data Processing Patterns

```java
// ✅ SECURE: Minimize personal data in analysis
@Service
public class PoliticianAnalysisService {

    public PoliticianSummary analyzePolitician(String personId) {
        // Only retrieve fields needed for analysis
        PoliticianData data = repository.findPublicDataById(personId);

        // Never include unnecessary personal details
        return PoliticianSummary.builder()
            .personId(personId)
            .votingRecord(data.getVotingRecord())
            .committeeAssignments(data.getCommittees())
            .partyAffiliation(data.getParty())
            .build();
        // Excluded: personal address, phone, private email
    }
}

// ❌ INSECURE: Over-collection of personal data
public PoliticianData getAllPersonalData(String personId) {
    return repository.findById(personId); // Returns ALL fields
}
```

### Data Subject Rights Implementation

| GDPR Right | CIA Platform Implementation |
|-----------|---------------------------|
| Right to access (Art. 15) | User data export endpoint |
| Right to rectification (Art. 16) | Profile update mechanism |
| Right to erasure (Art. 17) | Account deletion with cascade |
| Right to restrict (Art. 18) | Account deactivation option |
| Right to portability (Art. 20) | JSON/CSV data export |
| Right to object (Art. 21) | Opt-out of analytics processing |

## Encryption Standards

### Data at Rest

```
Encryption Requirements by Classification
│
├─ PUBLIC → No encryption required
├─ INTERNAL → AES-256 recommended
├─ CONFIDENTIAL → AES-256 required (AWS KMS)
└─ RESTRICTED → AES-256 + envelope encryption (KMS CMK)
```

### Data in Transit

- TLS 1.2+ for all HTTP connections
- Certificate pinning for external API calls
- mTLS for internal service communication
- HSTS headers enforced

### Database Encryption

```java
// Column-level encryption for sensitive fields
@Entity
@Table(name = "application_user")
public class ApplicationUser {

    @Column(name = "username")
    private String username; // PUBLIC - no encryption

    @Column(name = "email")
    @Convert(converter = EncryptedStringConverter.class)
    private String email; // CONFIDENTIAL - encrypted

    @Column(name = "password_hash")
    private String passwordHash; // Already hashed (bcrypt)
}
```

## Data Lifecycle Management

### Retention Policies

| Data Type | Retention Period | Action at Expiry |
|----------|-----------------|-----------------|
| Voting records | Indefinite | Archive (public record) |
| User sessions | 30 days | Automatic deletion |
| Audit logs | 2 years | Archive to cold storage |
| User accounts | Until deletion request | Anonymize + delete |
| API cache | 24 hours | Automatic expiry |
| Analytics data | 1 year | Aggregate + anonymize |

### Secure Deletion

```java
// Implement secure deletion for user data
@Service
public class DataDeletionService {

    @Transactional
    public void deleteUserAccount(Long userId) {
        // 1. Remove personal data
        userRepository.anonymizeUser(userId);

        // 2. Delete session data
        sessionRepository.deleteByUserId(userId);

        // 3. Audit the deletion (GDPR compliance)
        auditService.logDeletion(userId, "GDPR erasure request");

        // 4. Remove from caches
        cacheManager.evict("user:" + userId);
    }
}
```

## ISMS Alignment

| Control | Requirement | Implementation |
|---------|------------|----------------|
| ISO 27001 A.5.12 | Classification of information | Data classification labels |
| ISO 27001 A.5.33 | Protection of records | Retention policy enforcement |
| ISO 27001 A.8.10 | Information deletion | Secure deletion procedures |
| ISO 27001 A.8.24 | Use of cryptography | AES-256, TLS 1.2+ |
| NIST CSF PR.DS | Data security | Encryption at rest/transit |
| GDPR Art. 25 | Data protection by design | Privacy impact assessments |

## References

- [Hack23 ISMS Classification Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Classification_Policy.md)
- [Hack23 ISMS Cryptography Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Cryptography_Policy.md)
- [GDPR Full Text](https://gdpr-info.eu/)
- [NIST SP 800-122 PII Guide](https://csrc.nist.gov/publications/detail/sp/800-122/final)
