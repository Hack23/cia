#!/bin/bash

/usr/local/bin/cfn-flip -j aws-org-account/AWSCloudFormationStackSetAdministrationRole.yml aws-org-account/AWSCloudFormationStackSetAdministrationRole.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/CloudTrailAllAccounts.yml aws-org-account/stackset/CloudTrailAllAccounts.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/vpc_flow_logs_enabled.yml aws-org-account/stackset/vpc_flow_logs_enabled.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/guardduty-enabled-centralized.yml aws-org-account/stackset/guardduty-enabled-centralized.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/ssm-config-rule-all-managed-and-patch-compliant.yml aws-org-account/stackset/ssm-config-rule-all-managed-and-patch-compliant.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/AmazonMacieHandshakeRole.yml aws-org-account/stackset/AmazonMacieHandshakeRole.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/ManageSecurityHubRole.yml aws-org-account/stackset/ManageSecurityHubRole.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/default-config-rules.yml aws-org-account/stackset/default-config-rules.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/allow-central-config.yml aws-org-account/stackset/allow-central-config.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/cloudtrailfixedbucket.yml aws-org-account/stackset/cloudtrailfixedbucket.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/detect-config-cloudtrail-changes-alarm.yml aws-org-account/stackset/detect-config-cloudtrail-changes-alarm.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/ec2-managedinstance-patch-compliance-status-check.yml aws-org-account/stackset/ec2-managedinstance-patch-compliance-status-check.template
/usr/local/bin/cfn-flip -j aws-org-account/stackset/ConfigRuleCloudTrailEnabled.yml aws-org-account/stackset/ConfigRuleCloudTrailEnabled.template
/usr/local/bin/cfn-flip -j aws-org-account/aws-cross-account-manager-master.yml aws-org-account/aws-cross-account-manager-master.template