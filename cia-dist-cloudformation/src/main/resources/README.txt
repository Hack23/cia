Will use latest hvm-ssd ubuntu image:Current: ubuntu/images/hvm-ssd/ubuntu-xenial-16.04-amd64-server-20161115

16.10
ubuntu/images/hvm-ssd/ubuntu-yakkety-16.10-amd64-server-20161020.1 : ami-3e713f4d


http://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/PostgreSQL.Procedural.Importing.html

https://www.cloudconformity.com/conformity-rules/RDS/rds-encryption-enabled.html

sed -i -e 's_\">/cia<_\">/<_' /opt/cia/webapps/cia.xml 