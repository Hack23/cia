## Comprehensive Financial and Security Plan

---

### Cash Flow Visualization

Below is a breakdown of daily, monthly, and annual cash flows for better financial planning:

| **Time Frame**  | **Daily (USD)** | **Monthly (USD)** | **Annual (USD)** |
|------------------|-----------------|-------------------|------------------|
| **Total Cash Flow** | **$24.70**      | **$750.80**       | **$9,009.60**    |

---

### High-Level Perspective

This financial plan provides a structured and cost-efficient deployment for your application infrastructure in the **AWS eu-west-1 (Ireland)** region. It integrates key components of scalability, security, and resilience to support critical workloads while maintaining budgetary control.

#### Core Highlights
1. **Scalability & Resilience:** Single EC2 instance optimized for performance and costs, with an optional High Availability (HA) solution for redundancy.
2. **Cybersecurity Measures:** AWS WAF, CloudWatch Logs, and secure S3 storage for logs safeguard the application from cyber threats while supporting compliance.
3. **Cost Optimization:** Leverages AWS Graviton-based instances for better price-performance ratios.
4. **Resilience Tracking:** AWS Resilience Hub ensures operational readiness and business continuity.

---

### Cost Breakdown: Core Components

| **Component**          | **Daily (USD)** | **Monthly (USD)** | **Annual (USD)** |
|-------------------------|-----------------|-------------------|------------------|
| **Amazon EC2 (m7g.2xlarge)** | $8.37           | $251.12           | $3,013.44         |
| **Amazon RDS (db.m7g.large)** | $3.24           | $97.26            | $1,167.12         |
| **Elastic Load Balancer (ALB)** | $0.89           | $26.58            | $318.96           |
| **NAT Gateway**         | $1.33           | $39.84            | $478.08           |
| **Data Transfer (100 GB)** | $0.30           | $9.00             | $108.00           |
| **AWS WAF**             | $0.52           | $15.60            | $187.20           |
| **CloudWatch Logs & Alarms** | $0.59           | $17.60            | $211.20           |
| **Amazon S3 for Logs**  | $0.01           | $0.24             | $2.88             |
| **AWS Resilience Hub**  | $0.50           | $15.00            | $180.00           |
| **Security Services (Combined)** | $5.95           | $178.56           | $2,142.72         |
| **Total**               | **$24.70**      | **$750.80**       | **$9,009.60**    |

#### Links to Core Components:
- [Amazon EC2 Pricing](https://aws.amazon.com/ec2/pricing/) | [Amazon EC2 Documentation](https://docs.aws.amazon.com/ec2/)
- [Amazon RDS Pricing](https://aws.amazon.com/rds/pricing/) | [Amazon RDS Documentation](https://docs.aws.amazon.com/rds/)
- [Elastic Load Balancer Pricing](https://aws.amazon.com/elasticloadbalancing/pricing/) | [Elastic Load Balancer Documentation](https://docs.aws.amazon.com/elasticloadbalancing/)
- [NAT Gateway Pricing](https://aws.amazon.com/vpc/pricing/) | [NAT Gateway Documentation](https://docs.aws.amazon.com/vpc/)
- [Data Transfer Pricing](https://aws.amazon.com/ec2/pricing/on-demand/) | [Data Transfer Documentation](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-network-bandwidth.html)

---

### Security Assumptions and Costs for Basic Services

Below is a breakdown of daily, monthly, and annual costs for AWS security services based on typical usage patterns. These estimates include foundational security services that enhance your AWS account's overall security posture.

| **Service**                 | **Daily (USD)** | **Monthly (USD)** | **Annual (USD)** |
|-----------------------------|-----------------|-------------------|------------------|
| **Security Hub**            | $1.69           | $50.78            | $609.36          |
| **Amazon Detective**        | $1.01           | $30.26            | $363.12          |
| **Amazon Inspector**        | $0.87           | $26.04            | $312.48          |
| **Key Management Service**  | $0.77           | $22.99            | $275.88          |
| **AWS Config**              | $0.65           | $19.59            | $235.08          |
| **Amazon GuardDuty**        | $0.96           | $28.90            | $346.80          |
| **Total Security Costs**    | **$5.95**       | **$178.56**       | **$2,142.72**    |

#### Links to Security Services:
- [AWS Security Hub Pricing](https://aws.amazon.com/security-hub/pricing/) | [AWS Security Hub Documentation](https://docs.aws.amazon.com/securityhub/)
- [Amazon Detective Pricing](https://aws.amazon.com/detective/pricing/) | [Amazon Detective Documentation](https://docs.aws.amazon.com/detective/)
- [Amazon Inspector Pricing](https://aws.amazon.com/inspector/pricing/) | [Amazon Inspector Documentation](https://docs.aws.amazon.com/inspector/)
- [AWS Key Management Service Pricing](https://aws.amazon.com/kms/pricing/) | [AWS KMS Documentation](https://docs.aws.amazon.com/kms/)
- [AWS Config Pricing](https://aws.amazon.com/config/pricing/) | [AWS Config Documentation](https://docs.aws.amazon.com/config/)
- [Amazon GuardDuty Pricing](https://aws.amazon.com/guardduty/pricing/) | [Amazon GuardDuty Documentation](https://docs.aws.amazon.com/guardduty/)

---

### Cybersecurity Enhancements via AWS Security Services

#### Core Features
1. **Threat Detection with GuardDuty:**
   - Analyzes AWS logs (e.g., CloudTrail, VPC Flow Logs) for suspicious activity.
   - Automatically integrates with Security Hub for unified threat visibility.
   - **Benefit:** Real-time alerts on potential threats.

2. **Vulnerability Scanning with Inspector:**
   - Automatically assesses EC2 instances and container workloads for vulnerabilities.
   - Provides actionable findings for improving security posture.
   - **Benefit:** Continuous compliance with security best practices.

3. **Security Event Investigations with Detective:**
   - Simplifies root cause analysis for suspicious activities flagged by GuardDuty.
   - **Benefit:** Faster resolution of security incidents with visual context.

4. **Configuration Management with AWS Config:**
   - Tracks changes to resource configurations and ensures compliance with defined rules.
   - **Benefit:** Proactive compliance auditing and security enforcement.

5. **Centralized Visibility with Security Hub:**
   - Aggregates findings from GuardDuty, Inspector, and Config into a single dashboard.
   - Provides AWS Foundational Security Best Practices checks.
   - **Benefit:** Unified security insights and automation.

6. **Data Protection with Key Management Service (KMS):**
   - Provides encryption for data at rest and in transit.
   - Integrates with S3, EBS, RDS, and other AWS services.
   - **Benefit:** Secure encryption key storage and management.

---

### Optional High Availability Solution

| **Time Frame**  | **Daily (USD)** | **Monthly (USD)** | **Annual (USD)** |
|------------------|-----------------|-------------------|------------------|
| **Total (HA Solution)** | **$24.31**      | **$729.36**       | **$8,752.32**    |

---

### Conclusion

This financial plan balances scalability, cost-efficiency, and cybersecurity. The inclusion of EC2 and RDS costs complements the robust AWS security services. Optional HA ensures resilience for critical workloads requiring robust uptime guarantees while leveraging AWS security services for proactive threat detection and compliance monitoring.


## Security Controls

The Citizen Intelligence Agency (CIA) project implements the following **AWS Foundational Security Best Practices (FSBP)** controls. These controls leverage AWS services to protect financial data, detect threats, and ensure compliance.

---

## 1. Foundational Security Services

### AWS Config
- **Control:** [Config.1: AWS Config should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-config-1)  
- **Description:** AWS Config provides continuous monitoring of resource configurations and compliance checks. It is foundational for AWS Security Hub and other security services.  
- **Implementation Steps:** Enable AWS Config in all regions and configure compliance rules.  
- **Learn More:** [What is AWS Config?](https://docs.aws.amazon.com/config/latest/developerguide/)

### AWS Security Hub
- **Control:** [SecurityHub.1: Security Hub should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-securityhub-1)  
- **Description:** AWS Security Hub aggregates security findings and evaluates compliance with the AWS FSBP standard.  
- **Implementation Steps:** Enable Security Hub and integrate it with GuardDuty, Inspector, and AWS Config.  
- **Learn More:** [AWS Security Hub Overview](https://docs.aws.amazon.com/securityhub/latest/userguide/)

---

## 2. Threat Detection and Monitoring

### Amazon GuardDuty
- **Controls:**  
  - [GuardDuty.1: GuardDuty should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-guardduty-1)  
  - [GuardDuty.5: GuardDuty EKS Audit Log Monitoring should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-guardduty-5)  
  - [GuardDuty.6: GuardDuty Lambda Protection should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-guardduty-6)  
  - [GuardDuty.7: GuardDuty EKS Runtime Monitoring should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-guardduty-7)  
  - [GuardDuty.8: GuardDuty Malware Protection for EC2 should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-guardduty-8)  
  - [GuardDuty.9: GuardDuty RDS Protection should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-guardduty-9)  
  - [GuardDuty.10: GuardDuty S3 Protection should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-guardduty-10)  
- **Description:** GuardDuty provides intelligent threat detection across AWS resources, including EKS, Lambda, EC2, RDS, and S3.  
- **Implementation Steps:** Enable GuardDuty in all regions, activate relevant protections, and regularly review and address findings.  
- **Learn More:** [What is Amazon GuardDuty?](https://docs.aws.amazon.com/guardduty/latest/ug/)

---

## 3. Vulnerability Management

### Amazon Inspector
- **Controls:**  
  - [Inspector.1: Amazon Inspector should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-inspector-1)  
  - [Inspector.2: Amazon Inspector ECR scanning should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-inspector-2)  
  - [Inspector.3: Amazon Inspector Lambda code scanning should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-inspector-3)  
  - [Inspector.4: Amazon Inspector Lambda standard scanning should be enabled](https://docs.aws.amazon.com/securityhub/latest/userguide/securityhub-standards-fsbp-controls.html#fsbp-inspector-4)  
- **Description:** Inspector scans workloads, container images (ECR), and Lambda functions for vulnerabilities.  
- **Implementation Steps:** Enable Amazon Inspector, configure ECR scanning, and activate Lambda code and standard scans.  
- **Learn More:** [What is Amazon Inspector?](https://docs.aws.amazon.com/inspector/latest/user/)

---

## Conclusion

These **FSBP-aligned controls** ensure the CIA project maintains robust security for financial operations, proactively addresses vulnerabilities, and aligns with industry best practices.
```
