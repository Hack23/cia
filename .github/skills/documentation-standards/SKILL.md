---
name: documentation-standards
description: Write clear technical documentation: Markdown best practices, diagrams, API docs per DOCUMENTATION_NAMING_CONVENTION.md
license: Apache-2.0
---

# Documentation Standards Skill

## Purpose
Maintain clear, consistent technical documentation for CIA platform.

## When to Use
- ✅ Writing README files
- ✅ Creating API documentation
- ✅ Documenting architecture
- ✅ Writing user guides

## Markdown Best Practices

### Structure
```markdown
# Title (H1 - only one per document)

## Overview (H2)
Brief description of what this document covers.

## Section 1 (H2)
Content for section 1.

### Subsection 1.1 (H3)
Detailed content.

## References
- [Link 1](url)
- [Link 2](url)
```

### Code Blocks
```markdown
\`\`\`java
public class Example {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
\`\`\`
```

### Tables
```markdown
| Column 1 | Column 2 | Column 3 |
|----------|----------|----------|
| Data 1   | Data 2   | Data 3   |
```

## Documentation Types

### README.md
- Purpose and overview
- Quick start guide
- Installation instructions
- Basic usage examples
- Links to detailed docs

### ARCHITECTURE.md
- System overview
- Component descriptions
- Data flow diagrams
- Technology stack

### API Documentation
```java
/**
 * Retrieves a politician by their personal ID.
 *
 * @param personalId the Swedish personal ID (12 digits)
 * @return the politician if found
 * @throws ResourceNotFoundException if politician not found
 * @throws IllegalArgumentException if personalId format invalid
 */
@GetMapping("/{personalId}")
public Politician getPolitician(@PathVariable String personalId) {
    return service.findById(personalId);
}
```

## Naming Conventions

Files:
- `UPPERCASE_WITH_UNDERSCORES.md` for major docs
- `lowercase-with-hyphens.md` for guides
- `README.md` for module documentation

## Diagrams

Use Mermaid for diagrams:
```markdown
\`\`\`mermaid
graph LR
    A[User] --> B[Web App]
    B --> C[Database]
    B --> D[External API]
\`\`\`
```

## References
- DOCUMENTATION_NAMING_CONVENTION.md
- README.md
- Markdown Guide: https://www.markdownguide.org/