#!/bin/bash
set -e

# Start PostgreSQL service
service postgresql start

# Create the database and user
su - postgres -c "psql -c 'CREATE USER eris WITH password '\''discord'\'';'"
su - postgres -c "psql -c 'CREATE DATABASE cia_dev;'"
su - postgres -c "psql -c 'GRANT ALL PRIVILEGES ON DATABASE cia_dev to eris;'"

# Generate SSL certificates
openssl rand -base64 48 > passphrase.txt
openssl genrsa -des3 -passout file:passphrase.txt -out server.pass.key 2048
openssl rsa -passin file:passphrase.txt -in server.pass.key -out server.key
rm server.pass.key
openssl req -new -key server.key -out server.csr -subj "/C=UK/ST=Postgresql/L=Docker/O=Hack23/OU=demo/CN=127.0.0.1"
openssl x509 -req -days 3650 -in server.csr -signkey server.key -out server.crt
rm passphrase.txt
rm server.csr

# Configure PostgreSQL SSL and other settings
cp server.crt /var/lib/postgresql/15/main/server.crt
cp server.key /var/lib/postgresql/15/main/server.key
rm server.key
chmod 700 /var/lib/postgresql/15/main/server.key
chmod 700 /var/lib/postgresql/15/main/server.crt
chown -R postgres:postgres /var/lib/postgresql/15/main/

# Create .postgresql directory for the vscode user
mkdir -p /home/vscode/.postgresql
cp server.crt /home/vscode/.postgresql/root.crt
chmod 700 /home/vscode/.postgresql/root.crt
chown -R vscode:vscode /home/vscode/.postgresql
rm server.crt

# Update PostgreSQL configuration
echo "ssl_cert_file = '/var/lib/postgresql/15/main/server.crt'" >> /etc/postgresql/15/main/postgresql.conf
echo "ssl_key_file = '/var/lib/postgresql/15/main/server.key'" >> /etc/postgresql/15/main/postgresql.conf
echo "max_prepared_transactions = 100" >> /etc/postgresql/15/main/postgresql.conf
echo "shared_preload_libraries = 'pg_stat_statements, pgaudit, pgcrypto'" >> /etc/postgresql/15/main/postgresql.conf
echo "pgaudit.log = ddl" >> /etc/postgresql/15/main/postgresql.conf
echo "pg_stat_statements.track = all" >> /etc/postgresql/15/main/postgresql.conf
echo "pg_stat_statements.max = 10000" >> /etc/postgresql/15/main/postgresql.conf
echo "listen_addresses = '*'" >> /etc/postgresql/15/main/postgresql.conf

# Update pg_hba.conf
echo "host all all 0.0.0.0/0 md5" >> /etc/postgresql/15/main/pg_hba.conf
echo "host all all ::1/128 md5" >> /etc/postgresql/15/main/pg_hba.conf

# Restart PostgreSQL to apply changes
service postgresql restart
