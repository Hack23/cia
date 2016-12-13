Will use latest hvm-ssd ubuntu image:Current: ubuntu/images/hvm-ssd/ubuntu-xenial-16.04-amd64-server-20161205

16.10
ubuntu/images/hvm-ssd/ubuntu-yakkety-16.10-amd64-server-20161020.1 : ami-3e713f4d


http://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/PostgreSQL.Procedural.Importing.html

https://www.cloudconformity.com/conformity-rules/RDS/rds-encryption-enabled.html

 "AvailabilityZone" : { "Fn::Select" : [ "0", { "Fn::GetAZs" : { "Ref" : "AWS::Region" } } ] }
 
add java memory size map per instance type.

improve postgres rds config.

why no materialized views in quicksight. 


http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/quickref-cloudwatchlogs.html




    AWS::ApplicationAutoScaling::ScalableTarget

    AWS::ApplicationAutoScaling::ScalingPolicy
    
        AWS::SSM::Association

    AWS::SSM::Document
    
        AWS::WAF::ByteMatchSet

    AWS::WAF::IPSet

    AWS::WAF::Rule

    AWS::WAF::SizeConstraintSet

    AWS::WAF::SqlInjectionMatchSet

    AWS::WAF::WebACL

    AWS::WAF::XssMatchSet            
            

AWS::Logs::LogGroup

http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/systems-manager.html

http://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/QuickStartEC2Instance.html

http://docs.aws.amazon.com/inspector/latest/userguide/inspector_introduction.html



   "Mappings":{
      "SubnetConfig":{
         "VPC":{
            "CIDR":{
               "Fn::Join":[
                  ".",
                  [
                     {
                        "Ref":"SubNetPrefix"
                     },
                     "0.0/16"
                  ]
               ]
            }
         },
         "Public":{
            "CIDR":{
               "Fn::Join":[
                  ".",
                  [
                     {
                        "Ref":"SubNetPrefix"
                     },
                     "10.0/24"
                  ]
               ]
            }
         },
         "Public2":{
            "CIDR":{
               "Fn::Join":[
                  ".",
                  [
                     {
                        "Ref":"SubNetPrefix"
                     },
                     "11.0/24"
                  ]
               ]
            }
         },
         "Public3":{
            "CIDR":{
               "Fn::Join":[
                  ".",
                  [
                     {
                        "Ref":"SubNetPrefix"
                     },
                     "12.0/24"
                  ]
               ]
            }
         },
         "Private":{
            "CIDR":{
               "Fn::Join":[
                  ".",
                  [
                     {
                        "Ref":"SubNetPrefix"
                     },
                     "20.0/24"
                  ]
               ]
            }
         },
         "Private2":{
            "CIDR":{
               "Fn::Join":[
                  ".",
                  [
                     {
                        "Ref":"SubNetPrefix"
                     },
                     "21.0/24"
                  ]
               ]
            }
         },
         "Private3":{
            "CIDR":{
               "Fn::Join":[
                  ".",
                  [
                     {
                        "Ref":"SubNetPrefix"
                     },
                     "22.0/24"
                  ]
               ]
            }
         }
      },
