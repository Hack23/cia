#!/bin/bash

TARGET="../../../target/cloudformation-nag-reports"
mkdir -p $TARGET

/usr/local/bin/cfn_nag_scan --input-path ../resources/cia-dist-cloudformation.json > $TARGET/cia-dist-cloudformation.json-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/AWSCloudFormationStackSetAdministrationRole.template > $TARGET/aws-org-account-AWSCloudFormationStackSetAdministrationRole-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/CloudTrailAllAccounts.template > $TARGET/aws-org-account-stackset-CloudTrailAllAccounts-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/vpc_flow_logs_enabled.template > $TARGET/aws-org-account-stackset-vpc_flow_logs_enabled-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/kms/kms.template > $TARGET/aws-org-account-stackset-kms-kms-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/guardduty-enabled-centralized.template > $TARGET/aws-org-account-stackset-guardduty-enabled-centralized-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/ssm-config-rule-all-managed-and-patch-compliant.template > $TARGET/aws-org-account-stackset-ssm-config-rule-all-managed-and-patch-compliant-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/AmazonMacieHandshakeRole.template > $TARGET/aws-org-account-stackset-AmazonMacieHandshakeRole-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/ManageSecurityHubRole.template > $TARGET/aws-org-account-stackset-ManageSecurityHubRole-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/default-config-rules.template > $TARGET/aws-org-account-stackset-default-config-rules-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/allow-central-config.template > $TARGET/aws-org-account-stackset-allow-central-config-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/cloudtrailfixedbucket.template > $TARGET/aws-org-account-stackset-cloudtrailfixedbucket-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/detect-config-cloudtrail-changes-alarm.template > $TARGET/aws-org-account-stackset-detect-config-cloudtrail-changes-alarm-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/ec2-managedinstance-patch-compliance-status-check.template > $TARGET/aws-org-account-stackset-ec2-managedinstance-patch-compliance-status-check-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/ConfigRuleCloudTrailEnabled.template > $TARGET/aws-org-account-stackset-ConfigRuleCloudTrailEnabled-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/aws-cross-account-manager-master.template > $TARGET/aws-org-account-aws-cross-account-manager-master-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/Golden-AMI-Cross-Account-Role.template > $TARGET/aws-org-account-stackset-Golden-AMI-Cross-Account-Role-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-org-account/stackset/Golden-AMI-Compliance-CFT.template > $TARGET/aws-org-account-stackset-Golden-AMI-Compliance-CFT-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-account/op-center/Gold-AMi-Stack-CFT-CI.template > $TARGET/aws-account-op-center-Gold-AMi-Stack-CFT-CI-nag-scan-report.txt
#/usr/local/bin/cfn_nag_scan --input-path aws-account/op-center/simpleEC2-SSMParamInput.template > $TARGET/aws-account-op-center-simpleEC2-SSMParamInput-nag-scan-report.txt
