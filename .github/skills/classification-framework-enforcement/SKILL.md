---
name: classification-framework-enforcement
description: Data classification enforcement, sensitivity labeling, and handling controls per classification level for the CIA platform
license: Apache-2.0
---

# Classification Framework Enforcement Skill

## Purpose

This skill provides guidance for enforcing data classification across the Citizen Intelligence Agency platform. It defines classification levels, sensitivity labeling requirements, and mandatory handling controls for each level to ensure consistent data protection aligned with ISMS requirements.

## When to Use This Skill

Apply this skill when:
- ✅ Designing new data models or database tables
- ✅ Implementing features that process or display data
- ✅ Reviewing code that handles user data or PII
- ✅ Configuring logging, caching, or data export
- ✅ Assessing data flows between system components
- ✅ Integrating with external data sources
- ✅ Conducting data protection impact assessments

Do NOT use for:
- ❌ Access control implementation (use access-control-policy)
- ❌ Encryption algorithm selection (use crypto-best-practices)
- ❌ Incident response procedures (use incident-response)

## Classification Levels

### CIA Platform Data Classification

| Level | Label | Description | Examples in CIA |
|---|---|---|---|
| **Public** | 🟢 PUBLIC | Freely available information | Published Riksdag votes, public politician profiles, World Bank indicators |
| **Internal** | 🟡 INTERNAL | For authorized users only | Aggregated analytics, risk scores, trend analysis |
| **Confidential** | 🟠 CONFIDENTIAL | Restricted access, business-sensitive | User accounts, email addresses, session data |
| **Restricted** | 🔴 RESTRICTED | Highest protection, regulatory requirements | Passwords, API keys, encryption keys, GDPR-protected PII |

### Classification Decision Tree

```
New Data Element
    │
    ├─→ Is it publicly available from source?
    │   ├─→ YES → Is it aggregated/analyzed by CIA?
    │   │   ├─→ YES → 🟡 INTERNAL
    │   │   └─→ NO  → 🟢 PUBLIC
    │   └─→ NO
    │
    ├─→ Is it user-provided personal data?
    │   ├─→ YES → Is it authentication/credential data?
    │   │   ├─→ YES → 🔴 RESTRICTED
    │   │   └─→ NO  → 🟠 CONFIDENTIAL
    │   └─→ NO
    │
    ├─→ Is it a system secret (key, token, password)?
    │   ├─→ YES → 🔴 RESTRICTED
    │   └─→ NO
    │
    └─→ Is it internal analysis or derived data?
        ├─→ YES → 🟡 INTERNAL
        └─→ NO  → 🟢 PUBLIC (default to least restrictive only if certain)
```

## Handling Controls by Classification Level

### 🟢 PUBLIC Data

| Control | Requirement |
|---|---|
| Storage | Standard database storage |
| Transmission | HTTPS preferred but not mandatory for read-only |
| Logging | Can be logged freely |
| Caching | Can be cached without restrictions |
| Display | No restrictions on UI display |
| Export | Can be exported freely |
| Retention | Follow data source policies |
| Backup | Standard backup procedures |

### 🟡 INTERNAL Data

| Control | Requirement |
|---|---|
| Storage | Standard database with access controls |
| Transmission | HTTPS required |
| Logging | Can be logged, no sensitive aggregation details |
| Caching | Can be cached with TTL limits |
| Display | Requires authenticated session |
| Export | Requires authentication |
| Retention | 1 year default, review annually |
| Backup | Standard backup with access controls |

### 🟠 CONFIDENTIAL Data

| Control | Requirement |
|---|---|
| Storage | Encrypted at rest (AES-256) |
| Transmission | TLS 1.2+ required |
| Logging | **Never log confidential field values** |
| Caching | In-memory only, short TTL, no disk cache |
| Display | Masked by default, reveal on explicit action |
| Export | Restricted, requires authorization |
| Retention | Minimum necessary, max 3 years |
| Backup | Encrypted backup, restricted access |
| Access | Role-based, principle of least privilege |

### 🔴 RESTRICTED Data

| Control | Requirement |
|---|---|
| Storage | Encrypted at rest + application-level encryption |
| Transmission | TLS 1.2+ with certificate validation |
| Logging | **Absolutely never log** — not even existence |
| Caching | **Never cache** |
| Display | **Never display in plaintext** |
| Export | **Prohibited** without explicit authorization |
| Retention | Minimum necessary, auto-expire where possible |
| Backup | Encrypted, separate access controls |
| Access | Strict need-to-know, multi-factor authentication |
| Key Management | HSM or AWS KMS, regular rotation |

## Implementation Patterns

### Database Column Classification

```java
@Entity
@Table(name = "application_user")
public class ApplicationUser {

    @Column(name = "username")
    // Classification: CONFIDENTIAL — user-provided, non-public
    private String username;

    @Column(name = "email")
    // Classification: CONFIDENTIAL — PII under GDPR
    private String email;

    @Column(name = "password_hash")
    // Classification: RESTRICTED — credential data
    private String passwordHash;
}
```

### Logging Guard

```java
// DO: Log classification-safe data only
log.info("Processing politician data for id: {}", politicianId);  // PUBLIC id

// DON'T: Log CONFIDENTIAL or RESTRICTED data
// log.info("User login: email={}, password={}", email, password);

// DO: Use placeholder for CONFIDENTIAL data
log.info("User action completed for user id: {}", userId);
```

### Caching Rules

```java
// PUBLIC/INTERNAL: Standard caching allowed
@Cacheable(value = "politicians", key = "#id")
public Politician findPoliticianById(String id) { ... }

// CONFIDENTIAL: Short TTL, in-memory only
@Cacheable(value = "userProfiles", key = "#userId",
    cacheManager = "shortLivedCacheManager")
public UserProfile findUserProfile(String userId) { ... }

// RESTRICTED: Never cache
// No @Cacheable annotation — always fetch from secure storage
public String getApiKey(String serviceId) { ... }
```

## Data Flow Classification

### CIA Platform Data Flows

```
External APIs (Riksdag, World Bank) ──→ Service Layer ──→ Database
    🟢 PUBLIC data                      Classification    Labeled
                                        enforcement       storage
                                            │
                                            ▼
User Browser ◄──── Vaadin UI ◄──── Service Layer
    Display           Encoding        Access control
    controls          applied         enforced
```

### Cross-Boundary Rules

| From → To | Allowed Classifications | Controls Required |
|---|---|---|
| External API → Service | PUBLIC | Input validation |
| Service → Database | All | Encryption for CONFIDENTIAL+ |
| Database → Service | All | Access control check |
| Service → UI | PUBLIC, INTERNAL, CONFIDENTIAL | Output encoding, masking |
| Service → Logs | PUBLIC, INTERNAL only | Never log CONFIDENTIAL+ |
| Service → Cache | PUBLIC, INTERNAL, Confidential (short TTL) | Never cache RESTRICTED |
| Any → External | PUBLIC only | Data export review |

## Compliance Mapping

| Classification Control | ISO 27001 | NIST CSF | GDPR |
|---|---|---|---|
| Data Classification | A.5.12, A.5.13 | ID.AM-5 | Art. 5(1)(f) |
| Labeling | A.5.13 | PR.DS-3 | Art. 30 |
| Access Control | A.5.15, A.8.3 | PR.AC-4 | Art. 25 |
| Encryption | A.8.24 | PR.DS-1 | Art. 32 |
| Logging Controls | A.8.15 | DE.AE-3 | Art. 30 |
| Retention | A.5.33 | PR.IP-6 | Art. 5(1)(e) |
| Data Transfer | A.5.14 | PR.DS-2 | Art. 44-49 |

## References

- [ISO 27001:2022 Annex A — A.5.12-A.5.14 Information Classification](https://www.iso.org/standard/27001)
- [NIST SP 800-60 Guide for Mapping Information Types](https://csrc.nist.gov/publications/detail/sp/800-60/vol-1-rev-1/final)
- [GDPR Article 5 — Principles Relating to Processing](https://gdpr-info.eu/art-5-gdpr/)
- [Hack23 ISMS Data Classification Policy](https://github.com/Hack23/ISMS-PUBLIC)
