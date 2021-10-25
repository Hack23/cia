#!/bin/bash -xe
exec > >(tee /var/log/user-data.log) 2>&1
export DEBIAN_FRONTEND=noninteractive

#
# Update apt
#

apt-get update

apt-get -y dist-upgrade

apt-get -y install gnupg2

#apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys B97B0AFCAA1A47F044F244A07FCC7D46ACCC4CF8

#echo "deb http://apt.postgresql.org/pub/repos/apt/ cosmic-pgdg main" > /etc/apt/sources.list.d/pgdg.list

#apt-get update


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

apt-get -y install postgresql-13 postgresql-contrib-13
service postgresql stop

echo "host all all 0.0.0.0/0 md5" >> /etc/postgresql/13/main/pg_hba.conf

openssl rand -base64 48 > passphrase.txt
openssl genrsa -des3 -passout file:passphrase.txt -out server.pass.key 2048
openssl rsa -passin file:passphrase.txt -in server.pass.key -out server.key
rm server.pass.key
openssl req -new -key server.key -out server.csr -subj "/C=UK/ST=Postgresqll/L=Docker/O=Hack23/OU=demo/CN=127.0.0.1"
openssl x509 -req -days 3650 -in server.csr -signkey server.key -out server.crt
rm passphrase.txt
rm server.csr

cp server.crt /var/lib/postgresql/13/main/server.crt
cp server.key /var/lib/postgresql/13/main/server.key
rm server.key
chmod 700 /var/lib/postgresql/13/main/server.key
chmod 700 /var/lib/postgresql/13/main/server.crt
chown -R postgres:postgres /var/lib/postgresql/13/main/

echo "ssl_cert_file = '/var/lib/postgresql/13/main/server.crt'" >> /etc/postgresql/13/main/postgresql.conf
echo "ssl_key_file = '/var/lib/postgresql/13/main/server.key'" >> /etc/postgresql/13/main/postgresql.conf
echo "max_prepared_transactions = 100" >> /etc/postgresql/13/main/postgresql.conf

echo "shared_preload_libraries = 'pg_stat_statements, pgcrypto'" >> /etc/postgresql/13/main/postgresql.conf
echo "pgaudit.log = ddl" >> /etc/postgresql/13/main/postgresql.conf
echo "pg_stat_statements.track = all" >> /etc/postgresql/13/main/postgresql.conf
echo "pg_stat_statements.max = 10000" >> /etc/postgresql/13/main/postgresql.conf
echo "listen_addresses = '*'" >> /etc/postgresql/13/main/postgresql.conf
echo "port = 6432" >> /etc/postgresql/13/main/postgresql.conf

service postgresql start

su - postgres -c "psql -c 'CREATE USER eris WITH password '\''discord'\'';'"
su - postgres -c "psql -c 'CREATE DATABASE cia_dev;'"
su - postgres -c "psql -c 'GRANT ALL PRIVILEGES ON DATABASE cia_dev to eris;'"

#
# INSTALL ORACLE JDK
#
apt-get -y install software-properties-common openjdk-17-jdk-headless ca-certificates-java wget

ln -s /usr/lib/jvm/java-17-openjdk-amd64 /usr/lib/jvm/jdk-17

dpkg -i /root/cia-dist-deb.deb

mkdir /opt/cia/.postgresql
cp server.crt /opt/cia/.postgresql/root.crt
chmod 700 /opt/cia/.postgresql/root.crt
chown -R cia:cia /opt/cia/.postgresql/root.crt
rm server.crt

echo "database.hostname=127.0.0.1" >> /opt/cia/cia-base/webapps/cia/WEB-INF/database.properties
echo "database.port=6432" >> /opt/cia/cia-base/webapps//cia/WEB-INF/database.properties

apt-get -y autoclean
apt-get -y autoremove
rm /root/cia-dist-deb.deb

echo completed
