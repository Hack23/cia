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

# Check if SSL certificates exist
if [ ! -f "/var/lib/postgresql/16/main/server.crt" ] || [ ! -f "/var/lib/postgresql/16/main/server.key" ]; then
    echo "SSL certificates not found. Generating..."
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
else
    echo "SSL certificates already exist. Skipping generation."
    # Ensure vscode user has the certificate
    mkdir -p /home/vscode/.postgresql
    if [ ! -f "/home/vscode/.postgresql/root.crt" ]; then
        cp /var/lib/postgresql/16/main/server.crt /home/vscode/.postgresql/root.crt
        chmod 700 /home/vscode/.postgresql/root.crt
        chown -R vscode:vscode /home/vscode/.postgresql
    fi
fi

# Update PostgreSQL configuration (idempotent)
grep -q "^ssl = on" /etc/postgresql/16/main/postgresql.conf || echo "ssl = on" >> /etc/postgresql/16/main/postgresql.conf
grep -q "^ssl_cert_file" /etc/postgresql/16/main/postgresql.conf || echo "ssl_cert_file = '/var/lib/postgresql/16/main/server.crt'" >> /etc/postgresql/16/main/postgresql.conf
grep -q "^ssl_key_file" /etc/postgresql/16/main/postgresql.conf || echo "ssl_key_file = '/var/lib/postgresql/16/main/server.key'" >> /etc/postgresql/16/main/postgresql.conf
grep -q "^max_prepared_transactions" /etc/postgresql/16/main/postgresql.conf || echo "max_prepared_transactions = 100" >> /etc/postgresql/16/main/postgresql.conf
grep -q "^shared_preload_libraries" /etc/postgresql/16/main/postgresql.conf || echo "shared_preload_libraries = 'pg_stat_statements, pgaudit, pgcrypto'" >> /etc/postgresql/16/main/postgresql.conf
grep -q "^pgaudit.log" /etc/postgresql/16/main/postgresql.conf || echo "pgaudit.log = ddl" >> /etc/postgresql/16/main/postgresql.conf
grep -q "^pg_stat_statements.track" /etc/postgresql/16/main/postgresql.conf || echo "pg_stat_statements.track = all" >> /etc/postgresql/16/main/postgresql.conf
grep -q "^pg_stat_statements.max" /etc/postgresql/16/main/postgresql.conf || echo "pg_stat_statements.max = 10000" >> /etc/postgresql/16/main/postgresql.conf
grep -q "^listen_addresses" /etc/postgresql/16/main/postgresql.conf || echo "listen_addresses = '*'" >> /etc/postgresql/16/main/postgresql.conf

# Update pg_hba.conf (idempotent)
grep -q "hostssl all all 0.0.0.0/0 md5" /etc/postgresql/16/main/pg_hba.conf || echo "hostssl all all 0.0.0.0/0 md5" >> /etc/postgresql/16/main/pg_hba.conf
grep -q "hostssl all all ::1/128 md5" /etc/postgresql/16/main/pg_hba.conf || echo "hostssl all all ::1/128 md5" >> /etc/postgresql/16/main/pg_hba.conf

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
