config-rule (11) : from
cloudtrail_cloudwatch_integrated A config rule that ensures CloudTrail is enabled in all regions, ensure CloudTrail trails are integrated with CloudWatch Logs .
cloudtrail_encryption_validation A config rule that ensures CloudTrail log file validation is enabled and that CloudTrail logs are encrypted at rest using KMS CMKs.
vpc_flow_logs_enabled A Config rule that checks that VPC Flow Logs is enabled at specific VPC.
kms_cmk_rotation A Config rule that ensures rotation for customer created CMKs is enabled.
guardduty-enabled-centralized A Config rule that checks whether Amazon GuardDuty is enabled in your AWS account and region. If you provide an AWS account for centralization, the rule evaluates the Amazon GuardDuty results in the centralized account. The rule is compliant when Amazon GuardDuty is enabled.
rds-storage-encrypted A Config rule that checks whether storage encryption is enabled for your RDS DB instances.
rds_vpc_public_subnet A Config rule that checks that no RDS Instances are in Public Subnet.
rds-snapshots-public-prohibited A Config rule that checks if Amazon Relational Database Service (Amazon RDS) snapshots are public. The rule is non-compliant if any existing and new Amazon RDS snapshots are public.
encrypted-volumes A Config rule that checks whether the EBS volumes that are in an attached state are encrypted. If you specify the ID of a KMS key for encryption using the kmsId parameter, the rule checks if the EBS volumes in an attached state are encrypted with that KMS key.
ec2-managedinstance-association-compliance-status-check A Config rule that checks whether the compliance status of the Amazon EC2 Systems Manager association compliance is COMPLIANT or NON_COMPLIANT after the association execution on the instance. The rule is compliant if the field status is COMPLIANT.
ec2-managedinstance-patch-compliance-status-check A Config rule that checks whether the compliance status of the Amazon EC2 Systems Manager patch compliance is COMPLIANT or NON_COMPLIANT after the patch installation on the instance. The rule is compliant if the field status is COMPLIANT.