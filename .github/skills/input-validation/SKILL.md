---
name: input-validation
description: Implement comprehensive input validation to prevent XSS, SQL injection, and command injection attacks with sanitization strategies
license: Apache-2.0
---

# Input Validation Skill

## Purpose

This skill provides systematic approaches to validate, sanitize, and encode all user inputs to prevent injection attacks, XSS, and data corruption. It implements defense-in-depth validation at multiple layers aligned with OWASP best practices and Hack23 ISMS secure coding standards.

## When to Use This Skill

Apply this skill when:
- ✅ Processing any user-provided input (forms, APIs, URL parameters)
- ✅ Handling data from external systems (Riksdagen API, World Bank)
- ✅ Constructing database queries or commands
- ✅ Rendering user-generated content in UI
- ✅ Processing file uploads
- ✅ Implementing search functionality
- ✅ Building dynamic SQL, LDAP, or OS commands

## Validation Principles

### Principle #1: Validate Early, Validate Often

**Multi-Layer Validation:**
```
Client Side (JavaScript)  → Basic UX validation
    ↓
Server Side (Controller)  → Format and type validation
    ↓
Service Layer            → Business logic validation
    ↓
Database Layer           → Constraint enforcement
```

### Principle #2: Allowlist Over Blocklist

❌ **INSECURE - Blocklist Approach:**
```java
// BAD: Trying to block malicious patterns (incomplete, bypassable)
public boolean isValidInput(String input) {
    String[] badPatterns = {"<script>", "javascript:", "onerror=", "' OR '1'='1"};
    for (String pattern : badPatterns) {
        if (input.toLowerCase().contains(pattern)) {
            return false;
        }
    }
    return true;
}
```

✅ **SECURE - Allowlist Approach:**
```java
// GOOD: Define what IS valid
public boolean isValidPoliticianName(String name) {
    // Only allow letters, spaces, hyphens, and apostrophes
    return name != null && 
           name.matches("^[A-Za-zÀ-ÿ\\s'-]{2,50}$") &&
           name.length() >= 2 &&
           name.length() <= 50;
}

public boolean isValidEmail(String email) {
    // Use standard email regex
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    return email != null && email.matches(emailRegex);
}

public boolean isValidYear(Integer year) {
    // Define valid range
    int currentYear = Year.now().getValue();
    return year != null && 
           year >= 1900 && 
           year <= currentYear;
}
```

### Principle #3: Fail Securely

```java
@ControllerAdvice
public class ValidationExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(ValidationExceptionHandler.class);
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {
        
        // Log detailed error for debugging (but don't expose to user)
        log.warn("Validation failed: {}", ex.getBindingResult().getAllErrors());
        
        // Return generic error to user (don't leak validation logic)
        ErrorResponse error = new ErrorResponse(
            "Invalid input",
            "One or more fields contain invalid data"
        );
        
        return ResponseEntity.badRequest().body(error);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex) {
        
        log.warn("Constraint violation: {}", ex.getMessage());
        
        ErrorResponse error = new ErrorResponse(
            "Invalid input",
            "The provided data does not meet requirements"
        );
        
        return ResponseEntity.badRequest().body(error);
    }
}
```

## Input Validation Patterns

### Pattern #1: Bean Validation (JSR-380)

```java
// Domain model with validation constraints
@Entity
@Table(name = "politician")
public class Politician {
    
    @Id
    @NotNull
    @Pattern(regexp = "^[0-9]{12}$", message = "Personal ID must be 12 digits")
    private String personalId;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be 2-50 characters")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s'-]+$", message = "First name contains invalid characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be 2-50 characters")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s'-]+$", message = "Last name contains invalid characters")
    private String lastName;
    
    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
    
    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@riksdagen\\.se$", 
             message = "Only riksdagen.se emails allowed")
    private String officialEmail;
    
    @Pattern(regexp = "^\\+46[0-9]{9}$", message = "Invalid Swedish phone number")
    private String phoneNumber;
    
    @NotNull(message = "Party is required")
    @Enumerated(EnumType.STRING)
    private PoliticalParty party;
}

// DTO for API requests
public class CreatePoliticianRequest {
    
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s'-]+$")
    private String firstName;
    
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s'-]+$")
    private String lastName;
    
    @NotNull
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    @Email
    private String email;
    
    @Pattern(regexp = "^\\+46[0-9]{9}$")
    private String phoneNumber;
    
    @NotNull
    private String partyCode;
    
    // Getters and setters
}

// Controller with validation
@RestController
@RequestMapping("/api/politicians")
@Validated
public class PoliticianController {
    
    @PostMapping
    public ResponseEntity<Politician> createPolitician(
            @Valid @RequestBody CreatePoliticianRequest request) {
        
        // If validation passes, create politician
        Politician politician = politicianService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(politician);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Politician> getPolitician(
            @PathVariable 
            @Pattern(regexp = "^[0-9]{12}$", message = "Invalid politician ID") 
            String id) {
        
        Politician politician = politicianService.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Politician not found"));
        
        return ResponseEntity.ok(politician);
    }
}
```

### Pattern #2: Custom Validators

```java
// Custom annotation for Swedish Personal ID
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SwedishPersonalIdValidator.class)
public @interface ValidSwedishPersonalId {
    String message() default "Invalid Swedish personal ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

// Validator implementation with checksum
public class SwedishPersonalIdValidator implements ConstraintValidator<ValidSwedishPersonalId, String> {
    
    @Override
    public boolean isValid(String personalId, ConstraintValidatorContext context) {
        if (personalId == null || personalId.length() != 12) {
            return false;
        }
        
        // Check format: YYYYMMDDNNNN
        if (!personalId.matches("^[0-9]{12}$")) {
            return false;
        }
        
        // Validate date part
        try {
            int year = Integer.parseInt(personalId.substring(0, 4));
            int month = Integer.parseInt(personalId.substring(4, 6));
            int day = Integer.parseInt(personalId.substring(6, 8));
            
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            return false;
        }
        
        // Validate Luhn checksum
        return validateLuhnChecksum(personalId);
    }
    
    private boolean validateLuhnChecksum(String number) {
        int sum = 0;
        boolean alternate = false;
        
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));
            
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            
            sum += digit;
            alternate = !alternate;
        }
        
        return (sum % 10 == 0);
    }
}

// Usage
@Entity
public class Politician {
    @Id
    @ValidSwedishPersonalId
    private String personalId;
}
```

### Pattern #3: Sanitization for XSS Prevention

```java
@Component
public class InputSanitizer {
    
    private final Policy antiSamyPolicy;
    
    public InputSanitizer() throws PolicyException {
        // Load OWASP AntiSamy policy
        this.antiSamyPolicy = Policy.getInstance(
            getClass().getResourceAsStream("/antisamy-policy.xml")
        );
    }
    
    /**
     * Sanitize HTML input to prevent XSS
     */
    public String sanitizeHtml(String input) {
        if (input == null) return null;
        
        try {
            AntiSamy antiSamy = new AntiSamy();
            CleanResults results = antiSamy.scan(input, antiSamyPolicy);
            
            if (results.getNumberOfErrors() > 0) {
                log.warn("Sanitized HTML input. Errors: {}", 
                    results.getErrorMessages());
            }
            
            return results.getCleanHTML();
        } catch (Exception e) {
            log.error("Error sanitizing HTML", e);
            // Fail securely - return empty string
            return "";
        }
    }
    
    /**
     * Encode for HTML output
     */
    public String encodeForHtml(String input) {
        if (input == null) return null;
        return StringEscapeUtils.escapeHtml4(input);
    }
    
    /**
     * Encode for JavaScript output
     */
    public String encodeForJavaScript(String input) {
        if (input == null) return null;
        return StringEscapeUtils.escapeEcmaScript(input);
    }
    
    /**
     * Encode for URL parameter
     */
    public String encodeForUrl(String input) {
        if (input == null) return null;
        try {
            return URLEncoder.encode(input, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Remove all non-alphanumeric characters
     */
    public String alphanumericOnly(String input) {
        if (input == null) return null;
        return input.replaceAll("[^A-Za-z0-9]", "");
    }
    
    /**
     * Truncate to maximum length
     */
    public String truncate(String input, int maxLength) {
        if (input == null) return null;
        if (input.length() <= maxLength) return input;
        return input.substring(0, maxLength);
    }
}

// Usage in service layer
@Service
public class CommentService {
    
    @Autowired
    private InputSanitizer sanitizer;
    
    public Comment createComment(String authorId, String content) {
        // Sanitize user-generated content
        String sanitizedContent = sanitizer.sanitizeHtml(content);
        
        // Truncate to reasonable length
        sanitizedContent = sanitizer.truncate(sanitizedContent, 5000);
        
        Comment comment = new Comment();
        comment.setAuthorId(authorId);
        comment.setContent(sanitizedContent);
        comment.setCreatedAt(Instant.now());
        
        return commentRepository.save(comment);
    }
}

// Usage in Vaadin UI
@Component
public class CommentView extends VerticalLayout {
    
    @Autowired
    private InputSanitizer sanitizer;
    
    public void displayComment(Comment comment) {
        // Encode for HTML display
        String safeContent = sanitizer.encodeForHtml(comment.getContent());
        
        Html contentHtml = new Html("<div>" + safeContent + "</div>");
        add(contentHtml);
    }
}
```

### Pattern #4: SQL Injection Prevention

✅ **SECURE - Parameterized Queries:**
```java
@Repository
public interface PoliticianRepository extends JpaRepository<Politician, String> {
    
    // JPA Query with named parameters (SAFE)
    @Query("SELECT p FROM Politician p WHERE " +
           "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Politician> search(@Param("searchTerm") String searchTerm);
    
    // Spring Data query methods (SAFE)
    List<Politician> findByPartyAndDistrictOrderByLastNameAsc(String party, String district);
}

// Criteria API for dynamic queries (SAFE)
@Repository
public class PoliticianSearchRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<Politician> advancedSearch(PoliticianSearchCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Politician> query = cb.createQuery(Politician.class);
        Root<Politician> politician = query.from(Politician.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        // Safe parameter binding
        if (criteria.getFirstName() != null) {
            predicates.add(cb.like(
                cb.lower(politician.get("firstName")),
                "%" + criteria.getFirstName().toLowerCase() + "%"
            ));
        }
        
        if (criteria.getParty() != null) {
            predicates.add(cb.equal(politician.get("party"), criteria.getParty()));
        }
        
        if (criteria.getMinBirthYear() != null) {
            predicates.add(cb.greaterThanOrEqualTo(
                politician.get("birthDate"),
                LocalDate.of(criteria.getMinBirthYear(), 1, 1)
            ));
        }
        
        query.where(predicates.toArray(new Predicate[0]));
        query.orderBy(cb.asc(politician.get("lastName")));
        
        return entityManager.createQuery(query).getResultList();
    }
}
```

❌ **INSECURE - String Concatenation:**
```java
// NEVER DO THIS!
public List<Politician> searchUnsafe(String searchTerm) {
    String sql = "SELECT * FROM politician WHERE " +
                 "first_name LIKE '%" + searchTerm + "%'";  // SQL INJECTION!
    
    return jdbcTemplate.query(sql, new PoliticianRowMapper());
}
```

### Pattern #5: File Upload Validation

```java
@Configuration
public class FileUploadConfig {
    
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "pdf");
    private static final Set<String> ALLOWED_MIME_TYPES = Set.of(
        "image/jpeg", "image/png", "application/pdf"
    );
    
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(MAX_FILE_SIZE));
        factory.setMaxRequestSize(DataSize.ofBytes(MAX_FILE_SIZE));
        return factory.createMultipartConfig();
    }
}

@Service
public class FileValidationService {
    
    private static final Logger log = LoggerFactory.getLogger(FileValidationService.class);
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "pdf");
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB
    
    /**
     * Comprehensive file validation
     */
    public void validateFile(MultipartFile file) throws ValidationException {
        if (file == null || file.isEmpty()) {
            throw new ValidationException("File is required");
        }
        
        // 1. Check file size
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new ValidationException("File size exceeds maximum of 10 MB");
        }
        
        // 2. Validate filename
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.contains("..")) {
            throw new ValidationException("Invalid filename");
        }
        
        // 3. Check file extension
        String extension = getFileExtension(originalFilename).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new ValidationException(
                "File type not allowed. Allowed types: " + ALLOWED_EXTENSIONS);
        }
        
        // 4. Verify MIME type from content (not just filename)
        try {
            String contentType = detectContentType(file.getInputStream());
            if (!isAllowedContentType(contentType)) {
                throw new ValidationException("File content type not allowed");
            }
        } catch (IOException e) {
            throw new ValidationException("Error reading file");
        }
        
        // 5. Scan for malware (if applicable)
        scanForMalware(file);
    }
    
    private String getFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        if (lastDot == -1) return "";
        return filename.substring(lastDot + 1);
    }
    
    private String detectContentType(InputStream inputStream) throws IOException {
        return URLConnection.guessContentTypeFromStream(inputStream);
    }
    
    private boolean isAllowedContentType(String contentType) {
        Set<String> allowed = Set.of("image/jpeg", "image/png", "application/pdf");
        return contentType != null && allowed.contains(contentType);
    }
    
    private void scanForMalware(MultipartFile file) {
        // Integrate with malware scanning service if available
        // E.g., ClamAV, VirusTotal API
    }
    
    /**
     * Generate safe filename
     */
    public String generateSafeFilename(String originalFilename) {
        String extension = getFileExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + extension;
    }
}

@RestController
@RequestMapping("/api/uploads")
public class FileUploadController {
    
    @Autowired
    private FileValidationService fileValidationService;
    
    @PostMapping("/politician-photo")
    public ResponseEntity<UploadResponse> uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("politicianId") @Pattern(regexp = "^[0-9]{12}$") String politicianId) {
        
        // Validate file
        fileValidationService.validateFile(file);
        
        // Generate safe filename
        String safeFilename = fileValidationService.generateSafeFilename(
            file.getOriginalFilename()
        );
        
        // Store file securely
        Path uploadPath = Paths.get("/secure/uploads/photos/", safeFilename);
        Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
        
        // Update database
        politicianService.updatePhoto(politicianId, safeFilename);
        
        UploadResponse response = new UploadResponse(safeFilename, file.getSize());
        return ResponseEntity.ok(response);
    }
}
```

## Context-Specific Encoding

### HTML Context
```java
// Vaadin/Thymeleaf - Automatic escaping
<div th:text="${politician.firstName}"></div>  <!-- Auto-escaped -->

// Manual encoding when needed
String safe = StringEscapeUtils.escapeHtml4(userInput);
```

### JavaScript Context
```java
// In Vaadin or JavaScript
String safeJs = StringEscapeUtils.escapeEcmaScript(userInput);
String json = objectMapper.writeValueAsString(data); // Proper JSON encoding
```

### URL Context
```java
// URL parameters
String safeUrl = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8);
String url = "/search?q=" + safeUrl;
```

### SQL Context
```java
// ALWAYS use parameterized queries (covered earlier)
// NEVER build SQL strings with user input
```

## Input Validation Checklist

Before deploying code that processes user input:

**Data Type Validation:**
- ✅ Verify data type (string, int, date, etc.)
- ✅ Check for null/empty values
- ✅ Validate length constraints
- ✅ Ensure proper encoding (UTF-8)

**Format Validation:**
- ✅ Use regex for pattern matching
- ✅ Validate against allowlist of acceptable values
- ✅ Check date/time formats
- ✅ Verify email/phone formats

**Business Logic Validation:**
- ✅ Check value ranges (min/max)
- ✅ Validate relationships between fields
- ✅ Verify uniqueness constraints
- ✅ Ensure data consistency

**Security Validation:**
- ✅ Sanitize HTML content (XSS prevention)
- ✅ Use parameterized queries (SQL injection prevention)
- ✅ Validate file uploads (type, size, content)
- ✅ Check for path traversal attempts
- ✅ Prevent command injection

**Output Encoding:**
- ✅ Encode for HTML context
- ✅ Encode for JavaScript context
- ✅ Encode for URL context
- ✅ Encode for CSS context

## ISMS Compliance Mapping

### ISO 27001:2022 Controls
- **A.8.28 - Secure Coding**: Input validation as secure coding practice
- **A.14.2.1 - Secure Development Policy**: Validation requirements
- **A.8.7 - Protection Against Malware**: File upload validation

### NIST Cybersecurity Framework
- **PR.DS-5**: Protections against data leaks implemented
- **DE.CM-4**: Malicious input detected

### CIS Controls v8
- **Control 16.11**: Establish input validation processes
- **Control 18.5**: Validate application input

## Hack23 ISMS Policy References

- **Secure Development Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/secure-development-policy.md
- **Data Protection Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/data-protection-policy.md

## References

- OWASP Input Validation Cheat Sheet: https://cheatsheetseries.owasp.org/cheatsheets/Input_Validation_Cheat_Sheet.html
- OWASP XSS Prevention: https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html
- Bean Validation (JSR-380): https://beanvalidation.org/
- OWASP AntiSamy: https://owasp.org/www-project-antisamy/
