---
name: aws-security-architecture
description: AWS security best practices, VPC security, IAM, KMS, CloudTrail, GuardDuty for CIA platform deployment
license: Apache-2.0
---

# AWS Security Architecture Skill

## Purpose

This skill provides AWS security architecture guidance for the CIA platform deployment, covering VPC network security, IAM least-privilege policies, KMS encryption, CloudTrail auditing, and GuardDuty threat detection. It aligns with Hack23 ISMS and AWS Well-Architected Security Pillar.

## When to Use This Skill

Apply this skill when:
- ✅ Designing or modifying AWS infrastructure (CloudFormation)
- ✅ Configuring IAM roles, policies, or permissions
- ✅ Setting up encryption with KMS for data at rest
- ✅ Configuring VPC networking, security groups, or NACLs
- ✅ Enabling audit logging with CloudTrail
- ✅ Setting up threat detection with GuardDuty
- ✅ Reviewing cia-dist-cloudformation templates

Do NOT use for:
- ❌ Application-level security (use secure-code-review skill)
- ❌ CI/CD pipeline security (use github-actions-workflows skill)
- ❌ Data classification decisions (use data-protection skill)

## AWS Security Architecture Overview

```
CIA Platform AWS Architecture
│
├─ VPC (10.0.0.0/16)
│  ├─ Public Subnet (10.0.1.0/24)
│  │  ├─ ALB (Application Load Balancer)
│  │  └─ NAT Gateway
│  │
│  ├─ Private Subnet - App (10.0.2.0/24)
│  │  └─ EC2 / ECS (CIA Application)
│  │
│  └─ Private Subnet - Data (10.0.3.0/24)
│     └─ RDS PostgreSQL (encrypted)
│
├─ Security Services
│  ├─ AWS WAF (on ALB)
│  ├─ AWS Shield (DDoS protection)
│  ├─ GuardDuty (threat detection)
│  ├─ CloudTrail (audit logging)
│  └─ AWS Config (compliance monitoring)
│
└─ Key Management
   └─ KMS (Customer Managed Keys)
      ├─ RDS encryption key
      ├─ S3 encryption key
      └─ Secrets Manager key
```

## VPC Security

### Security Group Rules

```yaml
# Application Security Group - Least Privilege
ApplicationSecurityGroup:
  Type: AWS::EC2::SecurityGroup
  Properties:
    GroupDescription: CIA Application Server
    VpcId: !Ref VPC
    SecurityGroupIngress:
      # Only allow traffic from ALB
      - IpProtocol: tcp
        FromPort: 8080
        ToPort: 8080
        SourceSecurityGroupId: !Ref ALBSecurityGroup
    SecurityGroupEgress:
      # PostgreSQL to database only
      - IpProtocol: tcp
        FromPort: 5432
        ToPort: 5432
        DestinationSecurityGroupId: !Ref DatabaseSecurityGroup
      # HTTPS for external API calls
      - IpProtocol: tcp
        FromPort: 443
        ToPort: 443
        CidrIp: 0.0.0.0/0

# Database Security Group
DatabaseSecurityGroup:
  Type: AWS::EC2::SecurityGroup
  Properties:
    GroupDescription: CIA PostgreSQL Database
    VpcId: !Ref VPC
    SecurityGroupIngress:
      # Only from application servers
      - IpProtocol: tcp
        FromPort: 5432
        ToPort: 5432
        SourceSecurityGroupId: !Ref ApplicationSecurityGroup
    SecurityGroupEgress: []  # No outbound access needed
```

### Network ACLs

```
NACL Rules (defense in depth):
├─ Allow inbound HTTPS (443) from internet to public subnet
├─ Allow inbound 8080 from public to private app subnet
├─ Allow inbound 5432 from app subnet to data subnet
├─ Deny all other inbound traffic
└─ Allow ephemeral ports for return traffic
```

## IAM Least Privilege

### Application IAM Role

```yaml
# EC2/ECS Task Role - Minimum permissions
CIAApplicationRole:
  Type: AWS::IAM::Role
  Properties:
    RoleName: cia-application-role
    AssumeRolePolicyDocument:
      Version: '2012-10-17'
      Statement:
        - Effect: Allow
          Principal:
            Service: ecs-tasks.amazonaws.com
          Action: sts:AssumeRole
    Policies:
      - PolicyName: cia-app-policy
        PolicyDocument:
          Version: '2012-10-17'
          Statement:
            # Read secrets from Secrets Manager
            - Effect: Allow
              Action:
                - secretsmanager:GetSecretValue
              Resource:
                - !Sub 'arn:aws:secretsmanager:${AWS::Region}:${AWS::AccountId}:secret:cia/*'
            # Write CloudWatch logs
            - Effect: Allow
              Action:
                - logs:CreateLogStream
                - logs:PutLogEvents
              Resource:
                - !Sub 'arn:aws:logs:${AWS::Region}:${AWS::AccountId}:log-group:/cia/*'
            # KMS decrypt for database credentials
            - Effect: Allow
              Action:
                - kms:Decrypt
              Resource:
                - !GetAtt CIAEncryptionKey.Arn
```

### IAM Anti-Patterns

```yaml
# ❌ INSECURE: Overly permissive policy
- Effect: Allow
  Action: '*'
  Resource: '*'

# ❌ INSECURE: Wildcard on sensitive services
- Effect: Allow
  Action: 's3:*'
  Resource: '*'

# ✅ SECURE: Specific actions on specific resources
- Effect: Allow
  Action:
    - s3:GetObject
    - s3:PutObject
  Resource:
    - !Sub 'arn:aws:s3:::cia-data-bucket/*'
```

## KMS Encryption

### Key Configuration

```yaml
CIAEncryptionKey:
  Type: AWS::KMS::Key
  Properties:
    Description: CIA Platform encryption key
    Enabled: true
    EnableKeyRotation: true  # Annual automatic rotation
    KeyPolicy:
      Version: '2012-10-17'
      Statement:
        - Sid: AllowKeyAdministration
          Effect: Allow
          Principal:
            AWS: !Sub 'arn:aws:iam::${AWS::AccountId}:role/admin'
          Action:
            - kms:Create*
            - kms:Describe*
            - kms:Enable*
            - kms:List*
            - kms:Put*
            - kms:Update*
            - kms:Revoke*
            - kms:Disable*
            - kms:Get*
            - kms:Delete*
            - kms:ScheduleKeyDeletion
          Resource: '*'
        - Sid: AllowApplicationUse
          Effect: Allow
          Principal:
            AWS: !GetAtt CIAApplicationRole.Arn
          Action:
            - kms:Decrypt
            - kms:GenerateDataKey
          Resource: '*'
```

### Encryption Scope

| Resource | Encryption | Key Type |
|----------|-----------|----------|
| RDS PostgreSQL | At rest + in transit | KMS CMK |
| S3 buckets | SSE-KMS | KMS CMK |
| EBS volumes | At rest | KMS CMK |
| Secrets Manager | At rest | KMS CMK |
| CloudWatch Logs | At rest | AWS managed |
| ALB (TLS) | In transit | ACM certificate |

## CloudTrail Audit Logging

```yaml
CIACloudTrail:
  Type: AWS::CloudTrail::Trail
  Properties:
    TrailName: cia-audit-trail
    IsLogging: true
    IsMultiRegionTrail: true
    EnableLogFileValidation: true  # Tamper detection
    IncludeGlobalServiceEvents: true
    S3BucketName: !Ref AuditLogBucket
    CloudWatchLogsLogGroupArn: !GetAtt AuditLogGroup.Arn
    EventSelectors:
      - ReadWriteType: All
        IncludeManagementEvents: true
        DataResources:
          - Type: AWS::S3::Object
            Values: ['arn:aws:s3:::cia-data-bucket/']
```

## GuardDuty Threat Detection

### Enabled Findings

```
GuardDuty Detection Categories:
├─ Reconnaissance: Port scanning, API enumeration
├─ Instance Compromise: Cryptocurrency mining, C&C communication
├─ Account Compromise: Unusual API calls, disabled logging
├─ S3 Compromise: Public bucket access, unusual data transfer
└─ RDS Protection: Unusual login attempts, suspicious queries
```

### Alert Response

| Severity | Response Time | Action |
|----------|-------------|--------|
| Critical | < 1 hour | Immediate investigation, isolate resource |
| High | < 4 hours | Investigate, assess impact |
| Medium | < 24 hours | Review, plan remediation |
| Low | < 1 week | Log, trend analysis |

## Security Checklist for CloudFormation

```
CloudFormation Security Review:
□ No hardcoded secrets or credentials
□ IAM roles follow least privilege
□ Security groups restrict inbound/outbound
□ RDS encryption enabled (KMS CMK)
□ S3 buckets private, encrypted, versioned
□ CloudTrail enabled with log validation
□ VPC flow logs enabled
□ ALB uses TLS 1.2+ only
□ Auto-scaling configured for availability
□ Backup retention configured (RDS, S3)
□ Tags applied for cost and security tracking
```

## ISMS Alignment

| Control | Requirement | AWS Implementation |
|---------|------------|-------------------|
| ISO 27001 A.8.1 | User endpoint devices | Security groups, NACLs |
| ISO 27001 A.8.9 | Configuration management | AWS Config rules |
| ISO 27001 A.8.15 | Logging | CloudTrail, CloudWatch |
| ISO 27001 A.8.20 | Network security | VPC, WAF, Shield |
| ISO 27001 A.8.24 | Cryptography | KMS, ACM, TLS |
| NIST CSF DE.CM | Continuous monitoring | GuardDuty, Config |
| CIS Control 3 | Data protection | KMS encryption |
| CIS Control 8 | Audit log management | CloudTrail |

## References

- [AWS Well-Architected Security Pillar](https://docs.aws.amazon.com/wellarchitected/latest/security-pillar/)
- [AWS CloudFormation Security Best Practices](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/security-best-practices.html)
- [CIS AWS Benchmark](https://www.cisecurity.org/benchmark/amazon_web_services)
- [Hack23 ISMS Infrastructure Security](https://github.com/Hack23/ISMS-PUBLIC)
