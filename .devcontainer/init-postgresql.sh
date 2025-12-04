#!/bin/bash
set -euo pipefail

# Function for error handling
handle_error() {
    echo "Error occurred in script at line: ${1}"
    exit 1
}
trap 'handle_error ${LINENO}' ERR

# Ensure running as root
if [ "$(id -u)" != "0" ]; then
    echo "This script must be run as root" >&2
    exit 1
fi

# Start PostgreSQL service
service postgresql start || { echo "Failed to start PostgreSQL"; exit 1; }

# Wait for PostgreSQL to be ready
for i in {1..30}; do
    if su - postgres -c "psql -c '\q'" >/dev/null 2>&1; then
        break
    fi
    echo "Waiting for PostgreSQL to start..."
    sleep 1
    if [ $i -eq 30 ]; then
        echo "Timeout waiting for PostgreSQL to start"
        exit 1
    fi
done

# Check if database already exists
export NVM_DIR=""
DB_EXISTS=$(su - postgres -c "psql -lqt | cut -d \| -f 1 | grep -w cia_dev | wc -l")

if [ "$DB_EXISTS" -eq 0 ]; then
    echo "Database does not exist. Creating..."
    # Create the database and user
    su - postgres -c "psql -c 'CREATE USER eris WITH password '\''discord'\'';'" || echo "User may already exist"
    su - postgres -c "psql -c 'CREATE DATABASE cia_dev;'"
    su - postgres -c "psql -c 'GRANT ALL PRIVILEGES ON DATABASE cia_dev to eris;'"
    su - postgres -c "psql -c 'ALTER USER eris WITH SUPERUSER;'"
    su - postgres -c "psql -d cia_dev -c 'GRANT ALL ON SCHEMA public TO eris;'"
    su - postgres -c "psql -d cia_dev -c 'ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO eris;'"
    su - postgres -c "psql -d cia_dev -c 'ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO eris;'"
else
    echo "Database already exists. Skipping creation."
fi

# Generate SSL certificates with stronger security settings
openssl rand -base64 48 > passphrase.txt
umask 077 # Ensure secure file permissions
openssl genrsa -des3 -passout file:passphrase.txt -out server.pass.key 4096  # Increased key size
openssl rsa -passin file:passphrase.txt -in server.pass.key -out server.key
rm server.pass.key
# Create OpenSSL config file
cat > openssl.cnf <<EOL
[req]
default_bits = 2048
prompt = no
default_md = sha256
req_extensions = req_ext
distinguished_name = dn

[dn]
C=UK
ST=Postgresql
L=Docker
O=Hack23
OU=demo
CN=localhost

[req_ext]
subjectAltName = @alt_names

[alt_names]
DNS.1 = localhost
IP.1 = 127.0.0.1
EOL

openssl req -new -key server.key -out server.csr -config openssl.cnf
openssl x509 -req -days 3650 -in server.csr -signkey server.key -out server.crt -extensions req_ext -extfile openssl.cnf
rm passphrase.txt
rm server.csr
rm openssl.cnf

# Secure the certificate files
chmod 600 server.key server.crt
chown postgres:postgres server.key server.crt

# Configure PostgreSQL SSL and other settings
cp server.crt /var/lib/postgresql/16/main/server.crt
cp server.key /var/lib/postgresql/16/main/server.key
rm server.key
chmod 700 /var/lib/postgresql/16/main/server.key
chmod 700 /var/lib/postgresql/16/main/server.crt
chown -R postgres:postgres /var/lib/postgresql/16/main/

# Create .postgresql directory for the vscode user
mkdir -p /home/vscode/.postgresql
cp server.crt /home/vscode/.postgresql/root.crt
chmod 700 /home/vscode/.postgresql/root.crt
chown -R vscode:vscode /home/vscode/.postgresql
rm server.crt

# Update PostgreSQL configuration
echo "ssl = on" >> /etc/postgresql/16/main/postgresql.conf
echo "ssl_cert_file = '/var/lib/postgresql/16/main/server.crt'" >> /etc/postgresql/16/main/postgresql.conf
echo "ssl_key_file = '/var/lib/postgresql/16/main/server.key'" >> /etc/postgresql/16/main/postgresql.conf
echo "max_prepared_transactions = 100" >> /etc/postgresql/16/main/postgresql.conf
echo "shared_preload_libraries = 'pg_stat_statements, pgaudit, pgcrypto'" >> /etc/postgresql/16/main/postgresql.conf
echo "pgaudit.log = ddl" >> /etc/postgresql/16/main/postgresql.conf
echo "pg_stat_statements.track = all" >> /etc/postgresql/16/main/postgresql.conf
echo "pg_stat_statements.max = 10000" >> /etc/postgresql/16/main/postgresql.conf
echo "listen_addresses = '*'" >> /etc/postgresql/16/main/postgresql.conf

# Performance optimization settings per README-SCHEMA-MAINTENANCE.md
# Note: Some settings require PostgreSQL restart (shared_buffers, max_connections),
# while others can be reloaded dynamically (work_mem, effective_cache_size, etc.)

# Memory settings (scaled for Codespaces 2-4GB environment)
# shared_buffers: 256MB is ~10% of 2GB minimum RAM (requires restart)
echo "shared_buffers = '256MB'" >> /etc/postgresql/16/main/postgresql.conf
# effective_cache_size: Hint to planner about OS cache availability (reload OK)
echo "effective_cache_size = '768MB'" >> /etc/postgresql/16/main/postgresql.conf
# work_mem: Per-operation memory for sorts/hashes - 8MB conservative for 100 connections (reload OK)
echo "work_mem = '8MB'" >> /etc/postgresql/16/main/postgresql.conf
# maintenance_work_mem: Memory for VACUUM, CREATE INDEX, etc. (reload OK)
echo "maintenance_work_mem = '128MB'" >> /etc/postgresql/16/main/postgresql.conf

# Checkpoint settings for write performance (reload OK)
echo "checkpoint_completion_target = 0.9" >> /etc/postgresql/16/main/postgresql.conf
echo "wal_buffers = '8MB'" >> /etc/postgresql/16/main/postgresql.conf
echo "max_wal_size = '1GB'" >> /etc/postgresql/16/main/postgresql.conf
echo "min_wal_size = '256MB'" >> /etc/postgresql/16/main/postgresql.conf

# Query planning optimizations for SSD storage (reload OK)
echo "random_page_cost = 1.1" >> /etc/postgresql/16/main/postgresql.conf
echo "effective_io_concurrency = 200" >> /etc/postgresql/16/main/postgresql.conf

# Connection settings (requires restart)
echo "max_connections = 100" >> /etc/postgresql/16/main/postgresql.conf

# Update pg_hba.conf
echo "hostssl all all 0.0.0.0/0 md5" >> /etc/postgresql/16/main/pg_hba.conf
echo "hostssl all all ::1/128 md5" >> /etc/postgresql/16/main/pg_hba.conf

# Verify SSL configuration
if ! su - postgres -c "psql -c 'SHOW ssl'" | grep -q 'on'; then
    echo "SSL configuration verification failed"
    exit 1
fi

# Restart PostgreSQL and verify it's running
service postgresql restart
if ! service postgresql status | grep -q "active (running)"; then
    echo "PostgreSQL failed to restart"
    exit 1
fi

echo "PostgreSQL 16 initialization completed successfully"
