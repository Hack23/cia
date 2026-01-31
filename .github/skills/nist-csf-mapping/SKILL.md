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
