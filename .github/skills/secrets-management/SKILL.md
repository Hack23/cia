---
name: secrets-management
description: Never commit secrets, manage credentials securely using environment variables, vaults, and Hack23 ISMS key management policy
license: Apache-2.0
---

# Secrets Management Skill

## Purpose

This skill ensures secure handling of sensitive credentials, API keys, database passwords, and cryptographic keys throughout the CIA platform's development and deployment lifecycle. It enforces zero-tolerance for hardcoded secrets and mandates proper secrets management practices.

## When to Use This Skill

Apply this skill when:
- ✅ Adding new external API integrations (Riksdagen, World Bank)
- ✅ Configuring database connections
- ✅ Implementing authentication mechanisms
- ✅ Setting up CI/CD pipelines
- ✅ Deploying to new environments
- ✅ Rotating credentials after security incidents
- ✅ Reviewing code that handles configuration

Do NOT skip for:
- ❌ Development/testing credentials (still use proper secrets management)
- ❌ "Temporary" hardcoded values (they become permanent)
- ❌ Internal-only APIs (still require proper secrets management)

## Golden Rules of Secrets Management

### Rule #1: Never Commit Secrets to Git

**ABSOLUTELY FORBIDDEN:**
```java
// ❌ NEVER DO THIS - Hardcoded credentials
public class DatabaseConfig {
    private static final String DB_URL = "jdbc:postgresql://prod-db.example.com:5432/cia";
    private static final String DB_USERNAME = "admin";
    private static final String DB_PASSWORD = "SuperSecret123!"; // SECURITY VIOLATION!
}

// ❌ NEVER DO THIS - API keys in code
public class RiksdagenClient {
    private static final String API_KEY = "sk_live_abc123def456"; // EXPOSED!
}
```

**SECURE ALTERNATIVES:**
```java
// ✅ CORRECT - Use environment variables
@Configuration
public class DatabaseConfig {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    
    @Value("${spring.datasource.username}")
    private String dbUsername;
    
    @Value("${spring.datasource.password}")
    private String dbPassword;
    
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        return new HikariDataSource(config);
    }
}

// ✅ CORRECT - API keys from configuration
@Service
public class RiksdagenClient {
    private final String apiKey;
    
    public RiksdagenClient(@Value("${riksdagen.api.key}") String apiKey) {
        this.apiKey = apiKey;
    }
}
```

### Rule #2: Use Environment-Specific Configuration

**Application Properties Structure:**
```
src/main/resources/
├── application.yml              # Defaults, no secrets
├── application-dev.yml          # Development config
├── application-test.yml         # Test config
└── application-production.yml   # Production config (secrets from env vars)
```

**application.yml (Safe to commit):**
```yaml
spring:
  application:
    name: citizen-intelligence-agency
  
  datasource:
    # Values from environment variables
    url: ${DATABASE_URL:jdbc:postgresql://localhost:5432/cia_dev}
    username: ${DATABASE_USERNAME:cia_user}
    password: ${DATABASE_PASSWORD}  # No default for passwords!
    
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false

riksdagen:
  api:
    base-url: https://data.riksdagen.se/api
    key: ${RIKSDAGEN_API_KEY}  # Must be provided via environment

worldbank:
  api:
    base-url: https://api.worldbank.org/v2
    key: ${WORLDBANK_API_KEY:}  # Optional, empty default

security:
  jwt:
    secret: ${JWT_SECRET}  # Must be cryptographically random
    expiration: 86400  # 24 hours in seconds
```

**application-production.yml (Also safe):**
```yaml
spring:
  datasource:
    # Production settings, but actual values from env vars
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
      
logging:
  level:
    root: WARN
    com.hack23.cia: INFO
  file:
    name: /var/log/cia/application.log
```

### Rule #3: Environment Variables in Deployment

**Docker Compose (Development):**
```yaml
version: '3.8'
services:
  cia-app:
    image: hack23/cia:latest
    environment:
      # Never commit .env file with real secrets
      DATABASE_URL: ${DATABASE_URL}
      DATABASE_USERNAME: ${DATABASE_USERNAME}
      DATABASE_PASSWORD: ${DATABASE_PASSWORD}
      RIKSDAGEN_API_KEY: ${RIKSDAGEN_API_KEY}
      JWT_SECRET: ${JWT_SECRET}
    env_file:
      - .env  # Git-ignored file with secrets
```

**.env.example (Safe to commit as template):**
```bash
# CIA Application Secrets
# Copy to .env and fill in actual values
# NEVER commit .env file!

DATABASE_URL=jdbc:postgresql://localhost:5432/cia
DATABASE_USERNAME=cia_user
DATABASE_PASSWORD=CHANGE_ME

RIKSDAGEN_API_KEY=your_api_key_here
WORLDBANK_API_KEY=your_api_key_here

JWT_SECRET=generate_with_openssl_rand_base64_64
```

**.gitignore (MUST include):**
```gitignore
# Secrets and credentials
.env
.env.local
.env.production
*.key
*.pem
*.p12
*.jks
secrets/
credentials/
encrypt.properties

# IDE secrets
.idea/dataSources.xml
.vscode/settings.json
```

### Rule #4: Secrets Rotation Strategy

**Quarterly Rotation Schedule:**
```
Q1: Rotate database passwords
Q2: Rotate API keys
Q3: Rotate JWT secrets
Q4: Rotate encryption keys
```

**Rotation Process:**
```bash
# 1. Generate new secret
NEW_DB_PASSWORD=$(openssl rand -base64 32)

# 2. Update application configuration (zero-downtime)
kubectl set env deployment/cia-app DATABASE_PASSWORD=$NEW_DB_PASSWORD

# 3. Update database
psql -h db.example.com -U admin -c "ALTER USER cia_user PASSWORD '$NEW_DB_PASSWORD';"

# 4. Verify application health
kubectl rollout status deployment/cia-app

# 5. Revoke old secret
# (Keep for 24 hours in case of rollback)

# 6. Document rotation in change log
echo "$(date): Rotated database password" >> /var/log/secrets-rotation.log
```

## Secrets Detection and Prevention

### Pre-Commit Hooks

**Install git-secrets:**
```bash
# Install git-secrets
brew install git-secrets  # macOS
# or
sudo apt-get install git-secrets  # Ubuntu

# Setup in repository
cd /path/to/cia
git secrets --install
git secrets --register-aws
git secrets --add 'password\s*=\s*["\'][^"\']{8,}["\']'
git secrets --add 'apikey\s*=\s*["\'][^"\']{16,}["\']'
git secrets --add 'secret\s*=\s*["\'][^"\']{16,}["\']'
```

**Custom Pre-Commit Hook (.git/hooks/pre-commit):**
```bash
#!/bin/bash

echo "Scanning for secrets..."

# Check for common secret patterns
if git diff --cached | grep -iE '(password|secret|api_?key|token)\s*[:=]\s*["\047][^"\047]{8,}["\047]'; then
    echo "❌ ERROR: Potential secret detected in staged files!"
    echo "Please remove hardcoded secrets and use environment variables."
    exit 1
fi

# Check for specific file types that shouldn't be committed
if git diff --cached --name-only | grep -E '\.(key|pem|p12|jks)$'; then
    echo "❌ ERROR: Attempting to commit key/certificate file!"
    exit 1
fi

# Check for .env files
if git diff --cached --name-only | grep -E '^\.env(\.|$)'; then
    echo "❌ ERROR: Attempting to commit .env file!"
    exit 1
fi

echo "✅ No secrets detected"
exit 0
```

### GitHub Actions Secret Scanning

**.github/workflows/secret-scan.yml:**
```yaml
name: Secret Scanning

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  gitleaks:
    name: Gitleaks Secret Scan
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          fetch-depth: 0
      
      - name: Run Gitleaks
        uses: gitleaks/gitleaks-action@v2
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
          GITLEAKS_LICENSE: ${{ secrets.GITLEAKS_LICENSE }}
      
      - name: Upload SARIF report
        if: failure()
        uses: github/codeql-action/upload-sarif@v3
        with:
          sarif_file: results.sarif

  trufflehog:
    name: TruffleHog Secret Scan
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          fetch-depth: 0
      
      - name: TruffleHog Scan
        uses: trufflesecurity/trufflehog@main
        with:
          path: ./
          base: ${{ github.event.repository.default_branch }}
          head: HEAD
```

## Secure Secrets Storage Solutions

### AWS Secrets Manager (Recommended for Production)

**Java Integration:**
```java
@Configuration
public class SecretsManagerConfig {
    
    @Bean
    public SecretsManagerClient secretsManagerClient() {
        return SecretsManagerClient.builder()
            .region(Region.EU_WEST_1)
            .build();
    }
}

@Service
public class SecretsService {
    
    @Autowired
    private SecretsManagerClient secretsManager;
    
    private final Map<String, String> secretsCache = new ConcurrentHashMap<>();
    
    public String getSecret(String secretName) {
        return secretsCache.computeIfAbsent(secretName, this::fetchSecret);
    }
    
    private String fetchSecret(String secretName) {
        GetSecretValueRequest request = GetSecretValueRequest.builder()
            .secretId(secretName)
            .build();
        
        GetSecretValueResponse response = secretsManager.getSecretValue(request);
        return response.secretString();
    }
    
    // Refresh secrets every hour
    @Scheduled(fixedRate = 3600000)
    public void refreshSecrets() {
        secretsCache.clear();
    }
}

@Configuration
public class DatabaseConfigWithSecretsManager {
    
    @Autowired
    private SecretsService secretsService;
    
    @Bean
    public DataSource dataSource() {
        // Fetch database credentials from Secrets Manager
        String dbSecret = secretsService.getSecret("cia/production/database");
        
        // Parse JSON secret
        JSONObject secretJson = new JSONObject(dbSecret);
        String username = secretJson.getString("username");
        String password = secretJson.getString("password");
        String host = secretJson.getString("host");
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://" + host + ":5432/cia");
        config.setUsername(username);
        config.setPassword(password);
        
        return new HikariDataSource(config);
    }
}
```

**Create Secret in AWS:**
```bash
# Create database credentials secret
aws secretsmanager create-secret \
    --name cia/production/database \
    --description "CIA Production Database Credentials" \
    --secret-string '{
        "username": "cia_prod_user",
        "password": "GeneratedSecurePassword123!",
        "host": "cia-prod-db.xyz.eu-west-1.rds.amazonaws.com",
        "port": "5432",
        "database": "cia_production"
    }'

# Grant application IAM role access
aws secretsmanager put-resource-policy \
    --secret-id cia/production/database \
    --resource-policy '{
        "Version": "2012-10-17",
        "Statement": [{
            "Effect": "Allow",
            "Principal": {"AWS": "arn:aws:iam::123456789:role/cia-app-role"},
            "Action": "secretsmanager:GetSecretValue",
            "Resource": "*"
        }]
    }'
```

### HashiCorp Vault (Alternative)

**Java Integration:**
```java
@Configuration
public class VaultConfig {
    
    @Bean
    public VaultTemplate vaultTemplate() {
        VaultEndpoint endpoint = VaultEndpoint.create("vault.example.com", 8200);
        
        // Use token authentication (token from environment)
        TokenAuthentication authentication = new TokenAuthentication(
            System.getenv("VAULT_TOKEN")
        );
        
        SslConfiguration ssl = SslConfiguration.forTrustStore(
            KeyStore.getInstance("PKCS12"),
            "changeit".toCharArray()
        );
        
        return new VaultTemplate(endpoint, 
            new ClientHttpRequestFactoryFactory().create(
                new ClientOptions(), ssl),
            authentication);
    }
}

@Service
public class VaultSecretsService {
    
    @Autowired
    private VaultTemplate vaultTemplate;
    
    public String getDatabasePassword() {
        VaultResponse response = vaultTemplate
            .read("secret/data/cia/production/database");
        
        return (String) response.getData().get("password");
    }
    
    public Map<String, String> getRiksdagenApiConfig() {
        VaultResponse response = vaultTemplate
            .read("secret/data/cia/apis/riksdagen");
        
        return response.getData();
    }
}
```

### Spring Cloud Config Server (Encrypted Properties)

**Config Server Setup:**
```yaml
# bootstrap.yml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/Hack23/cia-config
          search-paths: '{application}'
        encrypt:
          enabled: true
          
encrypt:
  key: ${CONFIG_SERVER_ENCRYPTION_KEY}
```

**Encrypted Properties:**
```yaml
# cia-production.yml in config repo
spring:
  datasource:
    password: '{cipher}AQICAHhwKp7VkJJJJ...'  # Encrypted with config server key
    
riksdagen:
  api:
    key: '{cipher}AQICAHhwKp7VkJJJJ...'
```

**Encrypt secrets:**
```bash
# Encrypt a secret
curl -X POST http://config-server:8888/encrypt \
    -H "Content-Type: text/plain" \
    --data-binary "MySecretPassword123"

# Output: AQICAHhwKp7VkJJJJ...
```

## Cryptographic Key Management

### JWT Signing Keys

**Key Generation:**
```bash
# Generate RS256 key pair for JWT signing
openssl genrsa -out jwt_private.pem 4096
openssl rsa -in jwt_private.pem -pubout -out jwt_public.pem

# Store private key in secrets manager
aws secretsmanager create-secret \
    --name cia/jwt/private-key \
    --secret-binary fileb://jwt_private.pem

# Public key can be stored in application resources
cp jwt_public.pem src/main/resources/jwt-public.pem

# Securely delete local copies
shred -u jwt_private.pem
```

**JWT Configuration:**
```java
@Configuration
public class JwtConfig {
    
    @Autowired
    private SecretsService secretsService;
    
    @Bean
    public PrivateKey jwtPrivateKey() throws Exception {
        String privateKeyPEM = secretsService.getSecret("cia/jwt/private-key");
        
        privateKeyPEM = privateKeyPEM
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replaceAll("\\s", "");
        
        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
        
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        return keyFactory.generatePrivate(keySpec);
    }
    
    @Bean
    public PublicKey jwtPublicKey() throws Exception {
        // Public key from classpath (safe to commit)
        Resource resource = new ClassPathResource("jwt-public.pem");
        String publicKeyPEM = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
        
        publicKeyPEM = publicKeyPEM
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replaceAll("\\s", "");
        
        byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);
        
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        return keyFactory.generatePublic(keySpec);
    }
}
```

### Database Encryption Keys

**PostgreSQL TDE (Transparent Data Encryption):**
```bash
# Generate encryption key
openssl rand -base64 32 > /secure/location/database-encryption-key

# Configure PostgreSQL
echo "ssl = on" >> postgresql.conf
echo "ssl_cert_file = '/etc/ssl/certs/server.crt'" >> postgresql.conf
echo "ssl_key_file = '/secure/location/server.key'" >> postgresql.conf

# Encrypt specific columns with pgcrypto
psql -d cia -c "CREATE EXTENSION IF NOT EXISTS pgcrypto;"
```

**Application-Level Encryption:**
```java
@Configuration
public class EncryptionConfig {
    
    @Bean
    public BytesEncryptor fieldEncryptor() {
        String encryptionKey = System.getenv("FIELD_ENCRYPTION_KEY");
        String salt = System.getenv("FIELD_ENCRYPTION_SALT");
        
        return Encryptors.standard(encryptionKey, salt);
    }
}

@Entity
public class Politician {
    
    @Id
    private String id;
    
    private String firstName;
    
    private String lastName;
    
    // Encrypted field
    @Column(name = "personal_id_encrypted")
    private byte[] personalIdEncrypted;
    
    @Autowired
    @Transient
    private BytesEncryptor encryptor;
    
    @Transient
    public String getPersonalId() {
        if (personalIdEncrypted == null) return null;
        return new String(encryptor.decrypt(personalIdEncrypted));
    }
    
    public void setPersonalId(String personalId) {
        if (personalId == null) {
            this.personalIdEncrypted = null;
        } else {
            this.personalIdEncrypted = encryptor.encrypt(personalId.getBytes());
        }
    }
}
```

## Secrets Incident Response

### If Secret Compromised:

**Immediate Actions (Within 1 Hour):**
1. ✅ Rotate the compromised secret immediately
2. ✅ Revoke old secret/key from all systems
3. ✅ Review access logs for unauthorized access
4. ✅ Notify security team and stakeholders
5. ✅ Document incident in security log

**Investigation (Within 24 Hours):**
1. ✅ Determine how secret was exposed
2. ✅ Identify all systems that used the secret
3. ✅ Check for signs of unauthorized access
4. ✅ Review code repository history
5. ✅ Update detection mechanisms

**Remediation (Within 1 Week):**
1. ✅ Implement additional controls to prevent recurrence
2. ✅ Update security documentation
3. ✅ Conduct team training on secrets management
4. ✅ Add monitoring/alerting for similar incidents
5. ✅ Complete incident report

**Incident Response Script:**
```bash
#!/bin/bash
# secrets-incident-response.sh

SECRET_TYPE=$1  # e.g., "database-password", "api-key"
INCIDENT_ID=$(date +%Y%m%d-%H%M%S)

echo "=== Secrets Incident Response ==="
echo "Incident ID: $INCIDENT_ID"
echo "Secret Type: $SECRET_TYPE"
echo "Started: $(date)"

# 1. Generate new secret
echo "Generating new secret..."
NEW_SECRET=$(openssl rand -base64 32)

# 2. Update Secrets Manager
echo "Updating Secrets Manager..."
aws secretsmanager update-secret \
    --secret-id "cia/production/$SECRET_TYPE" \
    --secret-string "$NEW_SECRET"

# 3. Rotate in application
echo "Rotating in application..."
kubectl set env deployment/cia-app "${SECRET_TYPE^^}"="$NEW_SECRET"

# 4. Verify health
echo "Verifying application health..."
kubectl wait --for=condition=available --timeout=300s deployment/cia-app

# 5. Log incident
echo "Logging incident..."
echo "[$INCIDENT_ID] Rotated $SECRET_TYPE due to compromise" >> /var/log/security-incidents.log

# 6. Notify team
echo "Notifying security team..."
# (Send notification via email/Slack/PagerDuty)

echo "=== Incident Response Complete ==="
echo "Completed: $(date)"
```

## ISMS Compliance Mapping

### ISO 27001:2022 Controls
- **A.8.4 - Access to Source Code**: Secrets not in source code
- **A.8.11 - Data Masking**: Secrets masked in logs
- **A.8.24 - Use of Cryptography**: Keys managed securely
- **A.5.17 - Authentication Information**: Credentials protected

### NIST Cybersecurity Framework
- **PR.AC-1**: Credentials managed and protected
- **PR.DS-5**: Protections against data leaks
- **PR.MA-2**: Remote maintenance authenticated

### CIS Controls v8
- **Control 3.3**: Protect recovery data
- **Control 3.11**: Encrypt sensitive data at rest
- **Control 4.7**: Manage credentials

## Hack23 ISMS Policy References

- **Key Management Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/key-management-policy.md
- **Access Control Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/access-control-policy.md
- **Incident Response Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/incident-response-policy.md

## References

### Tools
- AWS Secrets Manager: https://aws.amazon.com/secrets-manager/
- HashiCorp Vault: https://www.vaultproject.io/
- git-secrets: https://github.com/awslabs/git-secrets
- Gitleaks: https://github.com/gitleaks/gitleaks
- TruffleHog: https://github.com/trufflesecurity/trufflehog

### Documentation
- OWASP Secrets Management: https://cheatsheetseries.owasp.org/cheatsheets/Secrets_Management_Cheat_Sheet.html
- Spring Cloud Config: https://spring.io/projects/spring-cloud-config
- CIA Project README.md - Setup instructions
