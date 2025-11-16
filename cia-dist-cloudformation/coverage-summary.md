# 🎖️ MITRE ATT&CK Coverage Summary

**Generated:** 2025-11-16 09:05:15 UTC

## 📊 Overall Coverage

- **Total Techniques:** 703
- **Covered Techniques:** 17
- **Coverage Percentage:** 2.4%

## 🎯 Coverage by Tactic

| Tactic | Covered | Total | Percentage |
|--------|---------|-------|------------|
| Initial Access | 4 | 22 | 18.2% |
| Execution | 1 | 51 | 2.0% |
| Persistence | 2 | 130 | 1.5% |
| Privilege Escalation | 4 | 111 | 3.6% |
| Defense Evasion | 2 | 218 | 0.9% |
| Credential Access | 1 | 67 | 1.5% |
| Discovery | 1 | 49 | 2.0% |
| Lateral Movement | 0 | 25 | 0.0% |
| Collection | 0 | 41 | 0.0% |
| Command and Control | 0 | 47 | 0.0% |
| Exfiltration | 1 | 19 | 5.3% |
| Impact | 5 | 33 | 15.2% |

## 🛡️ Security Control Mappings

### AWS WAF
- **Mitigation:** M1050: Exploit Protection
- **Techniques:** T1190
- **Description:** Web Application Firewall protecting public endpoints

### Multi-Factor Authentication
- **Mitigation:** M1032: Multi-factor Authentication
- **Techniques:** T1078, T1110
- **Description:** MFA for admin and privileged user accounts

### AWS CloudTrail
- **Mitigation:** M1047: Audit
- **Techniques:** T1098
- **Description:** Immutable audit logging of all AWS API calls

### VPC Security Groups
- **Mitigation:** M1030: Network Segmentation
- **Techniques:** T1041
- **Description:** Network-level access controls and segmentation

### Spring Security
- **Mitigation:** M1035: Limit Access to Resource Over Network
- **Techniques:** T1068, T1078
- **Description:** Application-layer authentication and authorization

### AWS GuardDuty
- **Mitigation:** M1047: Audit
- **Techniques:** T1190, T1078
- **Description:** Threat detection and continuous monitoring

### Input Validation
- **Mitigation:** M1021: Restrict Web-Based Content
- **Techniques:** T1190, T1059, T1565
- **Description:** Comprehensive input sanitization and validation

### AWS KMS Encryption
- **Mitigation:** M1041: Encrypt Sensitive Information
- **Techniques:** T1041
- **Description:** Data encryption at rest using AWS KMS

