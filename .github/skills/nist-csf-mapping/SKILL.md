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

## Hack23 ISMS Policy References

**NIST CSF Framework Implementation:**
- [Information Security Strategy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Strategy.md) - Strategic CSF alignment
- [Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) - Governance framework (GV)
- [Risk Assessment Methodology](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Risk_Assessment_Methodology.md) - Identify function (ID)
- [Access Control Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Access_Control_Policy.md) - Protect function (PR.AC)
- [Cryptography Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Cryptography_Policy.md) - Data security (PR.DS)
- [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) - Platform security (PR.IP)
- [Vulnerability Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md) - Detect function (DE.CM)
- [Incident Response Plan](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Incident_Response_Plan.md) - Respond function (RS)
- [Business Continuity Plan](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Business_Continuity_Plan.md) - Recover function (RC)
- [Backup Recovery Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Backup_Recovery_Policy.md) - Recovery planning (RC.RP)
- [Change Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Change_Management.md) - Configuration management (PR.IP)

**All Hack23 ISMS Policies**: https://github.com/Hack23/ISMS-PUBLIC

## CIA Platform Architecture References

- **Security Architecture**: [CIA SECURITY_ARCHITECTURE.md](https://github.com/Hack23/cia/blob/master/SECURITY_ARCHITECTURE.md) - CSF control implementation
- **Threat Model**: [CIA THREAT_MODEL.md](https://github.com/Hack23/cia/blob/master/THREAT_MODEL.md) - Risk identification (ID.RA)

## References

- **NIST CSF 2.0**: https://www.nist.gov/cyberframework
- **NIST CSF Implementation Guide**: https://doi.org/10.6028/NIST.CSWP.29
- **ISO 27001:2022**: https://www.iso.org/standard/27001
- **CIS Controls v8.1**: https://www.cisecurity.org/controls
