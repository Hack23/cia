#!/bin/bash

# This script creates the remaining skills for the CIA GitHub Copilot Skills Library

# Track created skills
created_count=0

# ISMS Compliance Skills (remaining)
echo "Creating ISMS Compliance skills..."

# nist-csf-mapping
cat > nist-csf-mapping/SKILL.md << 'EOF1'
---
name: nist-csf-mapping
description: Map CIA platform security controls to NIST Cybersecurity Framework functions: Identify, Protect, Detect, Respond, Recover
license: Apache-2.0
---

# NIST Cybersecurity Framework Mapping Skill

## Purpose

Map security implementations to NIST CSF 2.0 framework functions and categories, ensuring comprehensive cybersecurity coverage.

## When to Use

- ✅ Security architecture reviews
- ✅ Compliance assessments  
- ✅ Risk management activities
- ✅ Security control implementation

## NIST CSF Core Functions

### IDENTIFY (ID)

**ID.AM - Asset Management**
```yaml
# Document all assets
assets:
  - Application: CIA Web Platform
    Classification: Critical
    Owner: Development Team
    Data: Political intelligence, user data
```

**ID.RA - Risk Assessment**
- Conduct threat modeling (STRIDE)
- Annual risk assessments
- Vulnerability scanning

### PROTECT (PR)

**PR.AC - Identity Management and Access Control**
```java
@Configuration
@EnableWebSecurity
public class AccessControlConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/**").authenticated()
            .anyRequest().permitAll()
        );
        return http.build();
    }
}
```

**PR.DS - Data Security**
- Encryption at rest (AES-256)
- Encryption in transit (TLS 1.3)
- Data classification enforcement

### DETECT (DE)

**DE.CM - Security Continuous Monitoring**
```java
@Service
public class SecurityMonitoringService {
    @Scheduled(fixedRate = 60000) // Every minute
    public void monitorSecurityEvents() {
        List<SecurityEvent> events = securityEventRepository.findRecent();
        for (SecurityEvent event : events) {
            if (event.isSuspicious()) {
                alertService.raiseAlert(event);
            }
        }
    }
}
```

### RESPOND (RS)

**RS.AN - Analysis**
- Incident classification
- Root cause analysis
- Impact assessment

**RS.MI - Mitigation**
- Incident containment
- System isolation
- Evidence preservation

### RECOVER (RC)

**RC.RP - Recovery Planning**
- Backup and restore procedures
- Disaster recovery plan
- Business continuity plan

## Implementation Checklist

- ✅ Identify all assets
- ✅ Implement access controls
- ✅ Enable logging and monitoring
- ✅ Define incident response procedures
- ✅ Test recovery procedures

## References

- NIST CSF 2.0: https://www.nist.gov/cyberframework
- ISMS_COMPLIANCE_MAPPING.md
EOF1

# cis-controls
cat > cis-controls/SKILL.md << 'EOF2'
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
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>3.2.1</version> <!-- Version pinning -->
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

## References

- CIS Controls v8: https://www.cisecurity.org/controls/v8
- ISMS_COMPLIANCE_MAPPING.md
EOF2

echo "Created $((++created_count)) skills..."
