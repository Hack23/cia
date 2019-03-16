fix reduce mem ; oom_reaper out of memory: Kill process (java) score or sacrifice child

Will use latest hvm-ssd ubuntu image:Current: ubuntu/images/hvm-ssd/ubuntu-xenial-16.04-amd64-server-20180126 : ami-1b791862

Prepare use 18.04 ubuntu/images/hvm-ssd/ubuntu-bionic-18.04-amd64-server-20190212.1

http://www.ec2instances.info/

* Add WAF to application loadbalancer. https://docs.aws.amazon.com/solutions/latest/aws-waf-security-automations/template.html
* Basic SSM config.
* move to yaml, simplify expressions enable subnet prefix.
* improve postgres rds config.
* inspector trigger and result actions  (wip)

Log retention (GDPR)

aws logs put-retention-policy --log-group-name ec2awslogs --retention-in-days 7 --region eu-west-1
aws logs put-retention-policy --log-group-name VPCFlowLogsGroup-ciatest --retention-in-days 7 --region eu-west-1


https://www.versent.com.au/insights/patch-your-s-it-with-amazon-systems-manager	  