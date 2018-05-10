#!/bin/bash -xe
exec > >(tee /var/log/user-data.log) 2>&1 
export DEBIAN_FRONTEND=noninteractive

#
# Update apt
#
apt-get update

#
# Time and locale settings
#
apt-get -y install locales tzdata
rm /etc/localtime
ln -s /usr/share/zoneinfo/Europe/Stockholm /etc/localtime 
dpkg-reconfigure -f noninteractive tzdata 
echo 'LANG=\"en_US.UTF-8\"'> /etc/default/locale
locale-gen en_US.UTF-8 en_GB.UTF-8 sv_SE.UTF-8 
dpkg-reconfigure --frontend=noninteractive locales 

#
#
# 
apt-get -y install postgresql-10 postgresql-10-pgaudit postgresql-contrib
service postgresql stop

echo "host all all 0.0.0.0/0 md5" >> /etc/postgresql/10/main/pg_hba.conf

openssl rand -base64 48 > passphrase.txt
openssl genrsa -des3 -passout file:passphrase.txt -out server.pass.key 2048
openssl rsa -passin file:passphrase.txt -in server.pass.key -out server.key
rm server.pass.key
openssl req -new -key server.key -out server.csr -subj "/C=UK/ST=Postgresqll/L=Docker/O=Hack23/OU=demo/CN=cia"
openssl x509 -req -days 3650 -in server.csr -signkey server.key -out server.crt
rm passphrase.txt
rm server.csr

cp server.crt /var/lib/postgresql/10/main/server.crt
cp server.key /var/lib/postgresql/10/main/server.key
rm server.key
chmod 700 /var/lib/postgresql/10/main/server.*
chown postgres:postgres /var/lib/postgresql/10/main/server.*

echo "ssl_cert_file = '/var/lib/postgresql/10/main/server.crt'" >> /etc/postgresql/10/main/postgresql.conf
echo "ssl_key_file = '/var/lib/postgresql/10/main/server.key'" >> /etc/postgresql/10/main/postgresql.conf
echo "max_prepared_transactions = 100" >> /etc/postgresql/10/main/postgresql.conf 

echo "shared_preload_libraries = 'pg_stat_statements, pgaudit, pgcrypto'" >> /etc/postgresql/10/main/postgresql.conf 
echo "pgaudit.log = ddl" >> /etc/postgresql/10/main/postgresql.conf 
echo "pg_stat_statements.track = all" >> /etc/postgresql/10/main/postgresql.conf 
echo "pg_stat_statements.max = 10000" >> /etc/postgresql/10/main/postgresql.conf
echo "listen_addresses = '*'" >> /etc/postgresql/10/main/postgresql.conf

service postgresql start

su - postgres -c "psql -c 'CREATE USER eris WITH password '\''discord'\'';'"
su - postgres -c "psql -c 'CREATE DATABASE cia_dev;'"
su - postgres -c "psql -c 'GRANT ALL PRIVILEGES ON DATABASE cia_dev to eris;'"

#
# INSTALL ORACLE JDK
#
apt-get -y install software-properties-common openjdk-8-jdk-headless ca-certificates-java
add-apt-repository ppa:linuxuprising/java
apt-get update
echo oracle-java10-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
apt-get -y install oracle-java10-installer
dpkg -i /root/cia-dist-deb.deb

mkdir /opt/cia/.postgresql
cp server.crt /opt/cia/.postgresql/root.crt
chmod 700 /opt/cia/.postgresql/root.crt
chown -R cia:cia /opt/cia/.postgresql/root.crt
rm server.crt

echo "database.hostname=cia" >> /opt/cia/webapps/cia/WEB-INF/database.properties

apt-get -y autoclean
rm /root/cia-dist-deb.deb

echo completed