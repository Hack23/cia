Will use latest hvm-ssd ubuntu image:Current: ubuntu/images/hvm-ssd/ubuntu-xenial-16.04-amd64-server-20180126 : ami-1b791862

Prepare use 18.04 hvm-ssd/ubuntu-bionic

http://www.ec2instances.info/

* Add WAF to application loadbalancer. https://docs.aws.amazon.com/solutions/latest/aws-waf-security-automations/template.html
* Basic SSM config.
* move to yaml, simplify expressions enable subnet prefix.
* improve postgres rds config.
* inspector trigger and result actions  (wip)

Log retention (GDPR)

aws logs put-retention-policy --log-group-name ec2awslogs --retention-in-days 7 --region eu-west-1
aws logs put-retention-policy --log-group-name VPCFlowLogsGroup-ciatest --retention-in-days 7 --region eu-west-1



		"ServerMaintenanceWindow": {		
		  "Type" : "AWS::SSM::MaintenanceWindow",
		  "Properties" : {
		    "Description" : String,
		    "AllowUnassociatedTargets" : Boolean,
		    "Cutoff" : Integer,
		    "Schedule" : String,
		    "Duration" : Integer,
		    "Name" : String
		  }
		},
		
		{
  "Type" : "AWS::SSM::MaintenanceWindowTarget",
  "Properties" : {
    "OwnerInformation" : String,
    "Description" : String,
    "WindowId" : String,
    "ResourceType" : String,
    "Targets" : [ Targets, ... ],
    "Name" : String
  }
}


{
  "Type" : "AWS::SSM::ResourceDataSync",
  "Properties" : {
    "KMSKeyArn" : String,
    "BucketName" : String,
    "BucketRegion" : String,
    "SyncFormat" : String,
    "SyncName" : String,
    "BucketPrefix" : String
  }
}		
		
			{
	  "Type" : "AWS::SSM::PatchBaseline",
	  "Properties" : {
	    "OperatingSystem" : String,
	    "ApprovedPatches" : [ String, ... ],
	    "PatchGroups" : [ String, ... ],
	    "Description" : String,
	    "ApprovedPatchesComplianceLevel" : String,
	    "ApprovalRules" : RuleGroup,
	    "GlobalFilters" : PatchFilterGroup,
	    "Name" : String,
	    "RejectedPatches" : [ String, ... ]
	  }
	  
	  {
  "Type" : "AWS::SSM::Association",
  "Properties" : {
    "AssociationName" : String,
    "DocumentVersion" : String,
    "InstanceId" : String,
    "Name" : String,   
    "OutputLocation" : InstanceAssociationOutputLocation ,
    "Parameters" : { String: [String, ...] },
    "ScheduleExpression" : String,
    "Targets" : [ Targets ]
  }
}


{
  "Type" : "AWS::SSM::Document",
  "Properties" : {
    "Content" : JSON object,
    "DocumentType" : String,
    "Tags" : [ Resource Tag, ... ]
  }
}

AWS-UpdateSSMAgent
AWS-RunPatchBaseline

https://www.versent.com.au/insights/patch-your-s-it-with-amazon-systems-manager	  
	  
}

