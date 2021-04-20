#!/bin/bash

TARGET="../../../target/cloudformation-doc"
mkdir -p $TARGET

cat ../resources/cia-dist-cloudformation.json | /usr/local/bin/cfn-graph   | dot -Tpng -o$TARGET/cia-dist-cloudformation.png
#/usr/local/bin/cfn-graph aws-org-account/AWSCloudFormationStackSetAdministrationRole.template | dot -Tpng -o$TARGET/aws-org-account-AWSCloudFormationStackSetAdministrationRole.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/CloudTrailAllAccounts.template | dot -Tpng -o$TARGET/aws-org-account-stackset-CloudTrailAllAccounts.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/vpc_flow_logs_enabled.template | dot -Tpng -o$TARGET/aws-org-account-stackset-vpc_flow_logs_enabled.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/kms/kms.template | dot -Tpng -o$TARGET/aws-org-account-stackset-kms-kms.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/guardduty-enabled-centralized.template | dot -Tpng -o$TARGET/aws-org-account-stackset-guardduty-enabled-centralized.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/ssm-config-rule-all-managed-and-patch-compliant.template | dot -Tpng -o$TARGET/aws-org-account-stackset-ssm-config-rule-all-managed-and-patch-compliant.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/AmazonMacieHandshakeRole.template | dot -Tpng -o$TARGET/aws-org-account-stackset-AmazonMacieHandshakeRole.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/ManageSecurityHubRole.template | dot -Tpng -o$TARGET/aws-org-account-stackset-ManageSecurityHubRole.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/default-config-rules.template | dot -Tpng -o$TARGET/aws-org-account-stackset-default-config-rules.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/allow-central-config.template | dot -Tpng -o$TARGET/aws-org-account-stackset-allow-central-config.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/cloudtrailfixedbucket.template | dot -Tpng -o$TARGET/aws-org-account-stackset-cloudtrailfixedbucket.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/detect-config-cloudtrail-changes-alarm.template | dot -Tpng -o$TARGET/aws-org-account-stackset-detect-config-cloudtrail-changes-alarm.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/ec2-managedinstance-patch-compliance-status-check.template | dot -Tpng -o$TARGET/aws-org-account-stackset-ec2-managedinstance-patch-compliance-status-check.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/ConfigRuleCloudTrailEnabled.template | dot -Tpng -o$TARGET/aws-org-account-stackset-ConfigRuleCloudTrailEnabled.png
#/usr/local/bin/cfn-graph aws-org-account/aws-cross-account-manager-master.template | dot -Tpng -o$TARGET/aws-org-account-aws-cross-account-manager-master.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/Golden-AMI-Cross-Account-Role.template | dot -Tpng -o$TARGET/aws-org-account-stackset-Golden-AMI-Cross-Account-Role.png
#/usr/local/bin/cfn-graph aws-org-account/stackset/Golden-AMI-Compliance-CFT.template | dot -Tpng -o$TARGET/aws-org-account-stackset-Golden-AMI-Compliance-CFT.png
#/usr/local/bin/cfn-graph aws-account/op-center/Gold-AMi-Stack-CFT-CI.template | dot -Tpng -o$TARGET/aws-account-op-center-Gold-AMi-Stack-CFT-CI.png
#/usr/local/bin/cfn-graph aws-account/op-center/simpleEC2-SSMParamInput.template | dot -Tpng -o$TARGET/aws-account-op-center-simpleEC2-SSMParamInput.png
exit 0