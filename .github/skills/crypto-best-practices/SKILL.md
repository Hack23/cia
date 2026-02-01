---
name: crypto-best-practices
description: Implement strong encryption, secure hashing, and proper key management following NIST and OWASP cryptography guidelines
license: Apache-2.0
---

# Cryptography Best Practices Skill

## Purpose

This skill provides guidance on implementing cryptography correctly in the CIA platform, covering encryption, hashing, digital signatures, and key management. It ensures compliance with NIST, OWASP, and Hack23 ISMS cryptography policies.

## When to Use This Skill

Apply this skill when:
- ✅ Encrypting sensitive data at rest (database, files)
- ✅ Implementing user authentication (password hashing)
- ✅ Securing data in transit (TLS configuration)
- ✅ Generating secure tokens or session IDs
- ✅ Implementing digital signatures
- ✅ Creating API authentication mechanisms
- ✅ Managing encryption keys

## Golden Rules of Cryptography

### Rule #1: Never Roll Your Own Crypto

❌ **NEVER IMPLEMENT YOUR OWN:**
- Encryption algorithms
- Hashing functions
- Random number generators
- Cryptographic protocols

✅ **ALWAYS USE ESTABLISHED LIBRARIES:**
- Java Cryptography Architecture (JCA)
- Bouncy Castle (when JCA insufficient)
- Spring Security Crypto
- Apache Commons Crypto

### Rule #2: Use Strong, Modern Algorithms

**APPROVED ALGORITHMS (2024):**

**Encryption:**
- ✅ AES-256-GCM (preferred for symmetric encryption)
- ✅ ChaCha20-Poly1305 (alternative to AES)
- ✅ RSA-4096 (for asymmetric encryption)
- ❌ DES, 3DES, RC4 (deprecated, insecure)
- ❌ AES-ECB mode (vulnerable to patterns)

**Hashing:**
- ✅ SHA-256, SHA-384, SHA-512 (for general hashing)
- ✅ bcrypt (cost factor 12+) for passwords
- ✅ Argon2id (preferred for new implementations)
- ❌ MD5, SHA-1 (cryptographically broken)

**Key Derivation:**
- ✅ PBKDF2 with SHA-256 (100,000+ iterations)
- ✅ Argon2id (memory-hard, resistant to GPU attacks)
- ❌ Simple hashing without salt

## Password Hashing

### Implementation with BCrypt

```java
@Configuration
public class PasswordConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt with strength 12 (2^12 = 4096 rounds)
        return new BCryptPasswordEncoder(12);
    }
}

@Service
public class UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
    public void createUser(String username, String plainPassword) {
        // Hash password before storing
        String hashedPassword = passwordEncoder.encode(plainPassword);
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);  // Never store plain text!
        
        userRepository.save(user);
    }
    
    public boolean authenticate(String username, String plainPassword) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        // Verify password
        return passwordEncoder.matches(plainPassword, user.getPassword());
    }
    
    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        // Verify old password
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadCredentialsException("Current password is incorrect");
        }
        
        // Hash and store new password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
```

### Password Strength Requirements

```java
@Component
public class PasswordValidator {
    
    private static final int MIN_LENGTH = 12;
    private static final Pattern UPPERCASE = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE = Pattern.compile("[a-z]");
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
    
    public void validatePassword(String password) throws ValidationException {
        List<String> errors = new ArrayList<>();
        
        if (password == null || password.length() < MIN_LENGTH) {
            errors.add("Password must be at least " + MIN_LENGTH + " characters");
        }
        
        if (!UPPERCASE.matcher(password).find()) {
            errors.add("Password must contain at least one uppercase letter");
        }
        
        if (!LOWERCASE.matcher(password).find()) {
            errors.add("Password must contain at least one lowercase letter");
        }
        
        if (!DIGIT.matcher(password).find()) {
            errors.add("Password must contain at least one digit");
        }
        
        if (!SPECIAL.matcher(password).find()) {
            errors.add("Password must contain at least one special character");
        }
        
        // Check against common passwords
        if (isCommonPassword(password)) {
            errors.add("Password is too common, choose a stronger password");
        }
        
        if (!errors.isEmpty()) {
            throw new ValidationException("Password validation failed: " + 
                String.join(", ", errors));
        }
    }
    
    private boolean isCommonPassword(String password) {
        // Check against list of 10,000 most common passwords
        Set<String> commonPasswords = loadCommonPasswords();
        return commonPasswords.contains(password.toLowerCase());
    }
}
```

## Data Encryption at Rest

### AES-256-GCM Encryption

```java
@Configuration
public class EncryptionConfig {
    
    @Bean
    public TextEncryptor textEncryptor() {
        String encryptionKey = System.getenv("ENCRYPTION_KEY");
        String salt = System.getenv("ENCRYPTION_SALT");
        
        return Encryptors.text(encryptionKey, salt);
    }
    
    @Bean
    public BytesEncryptor bytesEncryptor() {
        String encryptionKey = System.getenv("ENCRYPTION_KEY");
        String salt = System.getenv("ENCRYPTION_SALT");
        
        return Encryptors.stronger(encryptionKey, salt);
    }
}

@Service
public class DataEncryptionService {
    
    @Autowired
    private BytesEncryptor encryptor;
    
    /**
     * Encrypt sensitive data before storing in database
     */
    public byte[] encrypt(String plaintext) {
        if (plaintext == null) return null;
        return encryptor.encrypt(plaintext.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * Decrypt data retrieved from database
     */
    public String decrypt(byte[] ciphertext) {
        if (ciphertext == null) return null;
        byte[] decrypted = encryptor.decrypt(ciphertext);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}

// Entity with encrypted fields
@Entity
@Table(name = "politician")
public class Politician {
    
    @Id
    private String id;
    
    private String firstName;  // Public data - not encrypted
    
    private String lastName;   // Public data - not encrypted
    
    // Sensitive field - encrypted in database
    @Column(name = "personal_id_encrypted")
    private byte[] personalIdEncrypted;
    
    @Column(name = "email_encrypted")
    private byte[] emailEncrypted;
    
    @Transient
    private transient DataEncryptionService encryptionService;
    
    // Getter/Setter for encrypted field
    public String getPersonalId() {
        return encryptionService.decrypt(personalIdEncrypted);
    }
    
    public void setPersonalId(String personalId) {
        this.personalIdEncrypted = encryptionService.encrypt(personalId);
    }
    
    public String getEmail() {
        return encryptionService.decrypt(emailEncrypted);
    }
    
    public void setEmail(String email) {
        this.emailEncrypted = encryptionService.encrypt(email);
    }
}
```

### Custom AES-GCM Implementation (Advanced)

```java
@Service
public class AesGcmEncryption {
    
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128;
    private static final int GCM_IV_LENGTH = 12;
    private static final int AES_KEY_SIZE = 256;
    
    private final SecretKey secretKey;
    
    public AesGcmEncryption() throws Exception {
        // Load key from secure key management system
        this.secretKey = loadKey();
    }
    
    public byte[] encrypt(byte[] plaintext) throws Exception {
        // Generate random IV (nonce)
        byte[] iv = generateIV();
        
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
        
        byte[] ciphertext = cipher.doFinal(plaintext);
        
        // Prepend IV to ciphertext (IV doesn't need to be secret)
        byte[] result = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(ciphertext, 0, result, iv.length, ciphertext.length);
        
        return result;
    }
    
    public byte[] decrypt(byte[] encrypted) throws Exception {
        // Extract IV from beginning
        byte[] iv = new byte[GCM_IV_LENGTH];
        System.arraycopy(encrypted, 0, iv, 0, GCM_IV_LENGTH);
        
        // Extract ciphertext
        byte[] ciphertext = new byte[encrypted.length - GCM_IV_LENGTH];
        System.arraycopy(encrypted, GCM_IV_LENGTH, ciphertext, 0, ciphertext.length);
        
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
        
        return cipher.doFinal(ciphertext);
    }
    
    private byte[] generateIV() {
        byte[] iv = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }
    
    private SecretKey loadKey() throws Exception {
        // Load from AWS Secrets Manager, Vault, or secure keystore
        // NEVER hardcode encryption keys!
        String base64Key = System.getenv("AES_ENCRYPTION_KEY");
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(decodedKey, "AES");
    }
}
```

## TLS/SSL Configuration

### Container/Server TLS Configuration

For production deployments, configure TLS at the container or reverse proxy level:

**Tomcat server.xml (if using embedded Tomcat):**
```xml
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
           maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
           clientAuth="false" sslProtocol="TLS"
           sslEnabledProtocols="TLSv1.3,TLSv1.2"
           ciphers="TLS_AES_256_GCM_SHA384,TLS_AES_128_GCM_SHA256,TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256"
           keystoreFile="${SSL_KEYSTORE_PATH}"
           keystorePass="${SSL_KEYSTORE_PASSWORD}"
           keystoreType="PKCS12"
           keyAlias="cia-server"/>
```

**Or use Spring Security for HTTPS redirect and headers:**

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .requiresChannel()
                .anyRequest().requiresSecure()
                .and()
            .headers()
                .httpStrictTransportSecurity()
                    .includeSubDomains(true)
                    .maxAgeInSeconds(31536000)
                    .and()
                .contentSecurityPolicy("default-src 'self'; script-src 'self'; style-src 'self'");
    }
}
```

### Nginx/Apache Reverse Proxy TLS

For most production deployments, configure TLS at the reverse proxy:

```nginx
# nginx.conf
server {
    listen 443 ssl http2;
    
    ssl_certificate /path/to/cert.pem;
    ssl_certificate_key /path/to/key.pem;
    
    ssl_protocols TLSv1.3 TLSv1.2;
    ssl_ciphers 'TLS_AES_256_GCM_SHA384:TLS_AES_128_GCM_SHA256:TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384';
    ssl_prefer_server_ciphers on;
    
    add_header Strict-Transport-Security "max-age=31536000; includeSubDomains" always;
    
    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header X-Forwarded-Proto https;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

## JWT Token Security

### RS256 (RSA Signature)

```java
@Service
public class JwtTokenService {
    
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final long expirationMs = 86400000; // 24 hours
    
    public JwtTokenService() throws Exception {
        // Load keys from secure storage (not hardcoded!)
        this.privateKey = loadPrivateKey();
        this.publicKey = loadPublicKey();
    }
    
    public String generateToken(UserDetails user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMs);
        
        return Jwts.builder()
            .setSubject(user.getUsername())
            .claim("authorities", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(privateKey, SignatureAlgorithm.RS256)
            .compact();
    }
    
    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Token has expired");
        } catch (JwtException e) {
            throw new InvalidTokenException("Invalid token signature");
        }
    }
    
    private PrivateKey loadPrivateKey() throws Exception {
        // Load from AWS Secrets Manager or similar
        // NEVER commit private keys to git!
        return null;  // Placeholder
    }
    
    private PublicKey loadPublicKey() throws Exception {
        // Public key can be in application resources
        InputStream is = getClass().getResourceAsStream("/jwt-public-key.pem");
        // Parse and return public key
        return null;  // Placeholder
    }
}
```

## Secure Random Number Generation

```java
@Component
public class SecureRandomGenerator {
    
    private final SecureRandom secureRandom;
    
    public SecureRandomGenerator() {
        // Use strong random number generator
        this.secureRandom = new SecureRandom();
    }
    
    /**
     * Generate cryptographically secure random bytes
     */
    public byte[] generateRandomBytes(int length) {
        byte[] bytes = new byte[length];
        secureRandom.nextBytes(bytes);
        return bytes;
    }
    
    /**
     * Generate secure random token for session IDs, CSRF tokens, etc.
     */
    public String generateSecureToken(int byteLength) {
        byte[] randomBytes = generateRandomBytes(byteLength);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
    
    /**
     * Generate random API key
     */
    public String generateApiKey() {
        // 32 bytes = 256 bits of entropy
        return "sk_" + generateSecureToken(32);
    }
    
    /**
     * Generate CSRF token
     */
    public String generateCsrfToken() {
        // 24 bytes = 192 bits of entropy
        return generateSecureToken(24);
    }
}
```

## Key Management

### Key Generation

```bash
# Generate AES-256 key
openssl rand -base64 32 > aes-256-key.txt

# Generate RSA-4096 key pair
openssl genrsa -out private-key.pem 4096
openssl rsa -in private-key.pem -pubout -out public-key.pem

# Generate self-signed certificate for TLS
openssl req -x509 -newkey rsa:4096 -keyout server-key.pem -out server-cert.pem -days 365 -nodes
```

### Key Rotation Strategy

```java
@Service
public class KeyRotationService {
    
    @Autowired
    private SecretsManager secretsManager;
    
    @Scheduled(cron = "0 0 0 1 */3 *")  // Every 3 months
    public void rotateEncryptionKey() {
        log.info("Starting encryption key rotation");
        
        // 1. Generate new key
        byte[] newKey = generateNewKey();
        
        // 2. Store new key with version number
        secretsManager.createSecret("encryption-key-v2", newKey);
        
        // 3. Re-encrypt data with new key
        reEncryptAllData("encryption-key-v1", "encryption-key-v2");
        
        // 4. Mark old key for deletion (keep for 30 days)
        secretsManager.scheduleKeyDeletion("encryption-key-v1", 30);
        
        log.info("Encryption key rotation completed");
    }
    
    private byte[] generateNewKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32];  // 256 bits
        random.nextBytes(key);
        return key;
    }
    
    private void reEncryptAllData(String oldKeyName, String newKeyName) {
        // Re-encrypt all encrypted data in database
        // This is a complex operation that should be done carefully
    }
}
```

## ISMS Compliance Mapping

### ISO 27001:2022 Controls
- **A.8.24 - Use of Cryptography**: Implementation of crypto policy
- **A.8.11 - Data Masking**: Encryption of sensitive data
- **A.5.17 - Authentication Information**: Secure password hashing

### NIST Cybersecurity Framework
- **PR.DS-1**: Data-at-rest protected
- **PR.DS-2**: Data-in-transit protected
- **PR.DS-5**: Protections against data leaks

### CIS Controls v8
- **Control 3.11**: Encrypt sensitive data at rest
- **Control 3.10**: Encrypt sensitive data in transit

## Hack23 ISMS Policy References

- **Cryptography Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/cryptography-policy.md
- **Key Management Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/key-management-policy.md

## References

- OWASP Cryptographic Storage Cheat Sheet: https://cheatsheetseries.owasp.org/cheatsheets/Cryptographic_Storage_Cheat_Sheet.html
- NIST Cryptographic Standards: https://csrc.nist.gov/projects/cryptographic-standards-and-guidelines
- Spring Security Crypto: https://docs.spring.io/spring-security/reference/features/integrations/cryptography.html
