---
name: cis-controls
description: Implement CIS Controls v8 critical security controls for effective cyber defense in CIA platform
license: Apache-2.0
---

# CIS Controls v8 Implementation Skill

## Purpose

Implement prioritized CIS Controls for cyber defense, focusing on high-impact security controls.

## When to Use

- ✅ Security hardening activities
- ✅ Compliance assessments
- ✅ Security baseline establishment
- ✅ Vendor security reviews

## Critical CIS Controls

### Control 1: Inventory and Control of Enterprise Assets

```bash
# Maintain asset inventory
aws ec2 describe-instances --query 'Reservations[*].Instances[*].[InstanceId,Tags[?Key==`Name`].Value|[0],State.Name]' --output table

# Tag all resources
aws ec2 create-tags --resources i-1234567890abcdef0 --tags Key=Application,Value=CIA Key=Environment,Value=Production
```

### Control 2: Inventory and Control of Software Assets

```xml
<!-- Track all dependencies in pom.xml -->
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>${spring.version}</version> <!-- Version via parent property -->
    </dependency>
</dependencies>
```

### Control 3: Data Protection

```java
@Service
public class DataProtectionService {
    @Autowired
    private BytesEncryptor encryptor;
    
    public void protectSensitiveData(SensitiveData data) {
        // Encrypt at rest
        data.setEncryptedContent(encryptor.encrypt(data.getPlainContent()));
        
        // Classify data
        data.setClassification(DataClassification.CONFIDENTIAL);
        
        // Set retention period
        data.setRetentionUntil(LocalDate.now().plusYears(7));
        
        dataRepository.save(data);
    }
}
```

### Control 4: Secure Configuration

```yaml
# application-production.yml - Secure defaults
spring:
  security:
    user:
      name: ${ADMIN_USERNAME}
      password: ${ADMIN_PASSWORD}
  
server:
  port: 8443
  ssl:
    enabled: true
  error:
    include-stacktrace: never
```

### Control 5: Account Management

```java
@Service
public class AccountManagementService {
    
    @Scheduled(cron = "0 0 2 * * *") // Daily at 2 AM
    public void reviewAccounts() {
        // Disable inactive accounts
        List<User> inactiveUsers = userRepository.findInactiveSince(
            LocalDateTime.now().minusDays(90)
        );
        
        inactiveUsers.forEach(user -> {
            user.setEnabled(false);
            auditLog.log("Account disabled due to inactivity: " + user.getUsername());
        });
        
        userRepository.saveAll(inactiveUsers);
    }
}
```

### Control 6: Access Control Management

```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser(String userId) {
    // Enforce least privilege
    auditLogger.logPrivilegedAction("DELETE_USER", userId);
    userRepository.deleteById(userId);
}
```

### Control 8: Audit Log Management

```java
@Aspect
@Component
public class AuditLoggingAspect {
    @Around("@annotation(Audited)")
    public Object auditMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String action = joinPoint.getSignature().getName();
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        
        auditLog.info("Action: {}, User: {}, Timestamp: {}", 
            action, user, Instant.now());
        
        return joinPoint.proceed();
    }
}
```

### Control 16: Application Software Security

```bash
# Security scanning in CI/CD
mvn org.owasp:dependency-check-maven:check
mvn sonar:sonar -Dsonar.qualitygate.wait=true
```

## Implementation Priority

1. **IG1 (Implementation Group 1)** - Essential for all organizations
   - Controls 1-6: Basic cyber hygiene

2. **IG2** - Additional controls for medium-sized organizations
   - Controls 7-16: Enhanced security

3. **IG3** - Comprehensive controls for large organizations
   - Controls 17-18: Advanced/specialized

## Hack23 ISMS Policy References

**CIS Controls Implementation:**
- [Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) - Governance (14.1-14.9)
- [Asset Register](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Asset_Register.md) - Control 1 (Asset Management)
- [Data Classification Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Data_Classification_Policy.md) - Control 3 (Data Protection)
- [Access Control Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Access_Control_Policy.md) - Control 5-6 (Access Control)
- [Vulnerability Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md) - Control 7 (Vulnerability Management)
- [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) - Control 16 (Application Security)
- [Incident Response Plan](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Incident_Response_Plan.md) - Control 17 (Incident Response)
- [Backup Recovery Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Backup_Recovery_Policy.md) - Control 11 (Data Recovery)
- [Change Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Change_Management.md) - Control 8 (Audit Logging)
- [Risk Assessment Methodology](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Risk_Assessment_Methodology.md) - Control 4 (Secure Configuration)

**All Hack23 ISMS Policies**: https://github.com/Hack23/ISMS-PUBLIC

## CIA Platform Architecture References

- **Security Architecture**: [CIA SECURITY_ARCHITECTURE.md](https://github.com/Hack23/cia/blob/master/SECURITY_ARCHITECTURE.md) - CIS Controls implementation
- **Architecture**: [CIA ARCHITECTURE.md](https://github.com/Hack23/cia/blob/master/ARCHITECTURE.md) - System design

## References

- **CIS Controls v8.1**: https://www.cisecurity.org/controls/v8
- **CIS Implementation Guide**: https://www.cisecurity.org/controls/cis-controls-implementation-guide
- **ISO 27001:2022**: https://www.iso.org/standard/27001
- **NIST CSF 2.0**: https://www.nist.gov/cyberframework
