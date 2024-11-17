## Comprehensive Financial and Security Plan

---

### Cash Flow Visualization

Below is a breakdown of daily, monthly, and annual cash flows for better financial planning:

| **Time Frame**  | **Daily (USD)** | **Monthly (USD)** | **Annual (USD)** |
|------------------|-----------------|-------------------|------------------|
| **Total Cash Flow** | **$15.94**      | **$478.24**       | **$5,738.88**    |

---

### High-Level Perspective

This financial plan provides a structured and cost-efficient deployment for your application infrastructure in the **AWS eu-west-1 (Ireland)** region. It integrates key components of scalability, security, and resilience to support critical workloads while maintaining budgetary control.

#### Core Highlights
1. **Scalability & Resilience:** Single EC2 instance optimized for performance and costs, with an optional High Availability (HA) solution for redundancy.
2. **Cybersecurity Measures:** AWS WAF, CloudWatch Logs, and secure S3 storage for logs safeguard the application from cyber threats while supporting compliance.
3. **Cost Optimization:** Leverages AWS Graviton-based instances for better price-performance ratios.
4. **Resilience Tracking:** AWS Resilience Hub ensures operational readiness and business continuity.

---

### Core Security Capabilities with NAT

#### Enhanced Security Measures for Key Components

1. **AWS WAF (Web Application Firewall):**
   - Protects web applications from common threats like SQL injection, cross-site scripting (XSS), and DDoS attacks.
   - Ensures that only valid and authorized traffic reaches your application.
   - **Monthly Cost:** $15.60  
   - [AWS WAF Pricing](https://aws.amazon.com/waf/pricing/)  
   - [AWS WAF Documentation](https://docs.aws.amazon.com/waf/latest/developerguide/)

2. **CloudWatch Logs & Alarms:**
   - Enables real-time monitoring of application and system logs for suspicious activities.
   - Triggers alarms for predefined thresholds, supporting proactive threat detection.
   - **Monthly Cost:** $17.60  
   - [CloudWatch Pricing](https://aws.amazon.com/cloudwatch/pricing/)  
   - [CloudWatch Documentation](https://docs.aws.amazon.com/cloudwatch/)

3. **Amazon S3 for Log Storage:**
   - Securely stores application logs, audit trails, and compliance data.
   - Offers server-side encryption to protect sensitive log information.
   - **Monthly Cost:** $0.24  
   - [Amazon S3 Pricing](https://aws.amazon.com/s3/pricing/)  
   - [Amazon S3 Documentation](https://docs.aws.amazon.com/s3/)

4. **NAT Gateway (Network Address Translation):**
   - Provides secure internet access for resources in private subnets, such as backend databases and application servers.
   - Ensures that outbound traffic is routed through a controlled and monitored gateway.
   - Shields internal resources by hiding their IP addresses.
   - **Monthly Cost:** $39.84  
   - [NAT Gateway Pricing](https://aws.amazon.com/vpc/pricing/)  
   - [NAT Gateway Documentation](https://docs.aws.amazon.com/vpc/latest/userguide/vpc-nat-gateway.html)

5. **AWS Resilience Hub:**
   - Tracks and assesses resilience against operational and cyber disruptions.
   - Identifies gaps in recovery planning and provides actionable recommendations.
   - **Monthly Cost:** $15.00  
   - [AWS Resilience Hub Pricing](https://aws.amazon.com/resilience-hub/pricing/)  
   - [AWS Resilience Hub Documentation](https://docs.aws.amazon.com/resilience-hub/)

---

### Security Summary
- The **NAT Gateway** is a key component in securing private subnets by controlling outbound internet access while preventing direct exposure to external threats.
- Combined with AWS WAF, CloudWatch Logs, and secure S3 storage, the infrastructure achieves a layered security model.
- **Total Monthly Security-Related Cost:** $88.28 (includes WAF, CloudWatch, S3 Logs, NAT, and Resilience Hub).

This approach addresses both network-level and application-level security, ensuring a robust defense posture.

---

### Detailed Technical and Financial Breakdown

#### 1. Amazon EC2 Instances
- **Purpose:** Hosts the web application.
- **Instance Type:** `m7g.2xlarge` (8 vCPUs, 32 GiB RAM)
- **Daily Cost:** $8.37  
- **Monthly Cost:** $251.12  
- **Annual Cost:** $3,013.44  
- [EC2 Pricing](https://aws.amazon.com/ec2/pricing/)  
- [EC2 Documentation](https://docs.aws.amazon.com/ec2/)

#### 2. Amazon RDS (Relational Database Service)
- **Purpose:** Supports the backend PostgreSQL database.
- **Instance Type:** `db.m7g.large` (2 vCPUs, 8 GiB RAM, 200 GB SSD)
- **Daily Cost:** $3.24  
- **Monthly Cost:** $97.26  
- **Annual Cost:** $1,167.12  
- [RDS Pricing](https://aws.amazon.com/rds/pricing/)  
- [RDS Documentation](https://docs.aws.amazon.com/rds/)

#### 3. Elastic Load Balancer (ALB)
- **Purpose:** Distributes incoming application traffic across multiple instances.
- **Daily Cost:** $0.89  
- **Monthly Cost:** $26.58  
- **Annual Cost:** $318.96  
- [Elastic Load Balancer Pricing](https://aws.amazon.com/elasticloadbalancing/pricing/)  
- [Elastic Load Balancer Documentation](https://docs.aws.amazon.com/elasticloadbalancing/)

#### 4. NAT Gateway
- **Purpose:** Enables outbound internet traffic for private subnets.
- **Daily Cost:** $1.33  
- **Monthly Cost:** $39.84  
- **Annual Cost:** $478.08  

#### 5. Data Transfer (Outbound)
- **Purpose:** Handles 100 GB of outbound traffic monthly.
- **Daily Cost:** $0.30  
- **Monthly Cost:** $9.00  
- **Annual Cost:** $108.00  

#### 6. AWS WAF
- **Purpose:** Protects against SQL injection, XSS, and other cyber threats.
- **Daily Cost:** $0.52  
- **Monthly Cost:** $15.60  
- **Annual Cost:** $187.20  

#### 7. CloudWatch Logs & Alarms
- **Purpose:** Monitors and logs data in real-time.
- **Daily Cost:** $0.59  
- **Monthly Cost:** $17.60  
- **Annual Cost:** $211.20  

#### 8. S3 for Logs
- **Purpose:** Stores compliance and audit logs securely.
- **Daily Cost:** $0.01  
- **Monthly Cost:** $0.24  
- **Annual Cost:** $2.88  

#### 9. AWS Resilience Hub
- **Purpose:** Tracks resilience across workloads.
- **Daily Cost:** $0.50  
- **Monthly Cost:** $15.00  
- **Annual Cost:** $180.00  

---

### Optional High Availability Solution

| **Time Frame**  | **Daily (USD)** | **Monthly (USD)** | **Annual (USD)** |
|------------------|-----------------|-------------------|------------------|
| **Total (HA Solution)** | **$24.31**      | **$729.36**       | **$8,752.32**    |

---

### Conclusion

This financial plan balances scalability, cost-efficiency, and cybersecurity. Optional HA ensures resilience for critical workloads requiring robust uptime guarantees.
