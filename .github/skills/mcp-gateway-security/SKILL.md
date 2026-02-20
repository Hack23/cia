---
name: mcp-gateway-security
description: MCP gateway security patterns, token management, request validation, and audit logging for MCP communications
license: Apache-2.0
---

# MCP Gateway Security Skill

## Purpose

This skill provides security patterns and best practices for securing MCP (Model Context Protocol) gateway communications in the CIA platform. It covers token management, request validation, audit logging, and threat mitigation for AI-assisted development workflows.

## When to Use This Skill

Apply this skill when:
- ✅ Hardening MCP gateway configurations
- ✅ Managing tokens and credentials for MCP servers
- ✅ Implementing request validation for MCP tool calls
- ✅ Setting up audit logging for MCP communications
- ✅ Conducting security reviews of MCP configurations
- ✅ Responding to security incidents involving MCP
- ✅ Assessing risks of new MCP server integrations

Do NOT use for:
- ❌ Initial MCP setup (use mcp-gateway-configuration)
- ❌ Application-level security (use secure-code-review)
- ❌ Infrastructure security (use security-architecture-validation)

## Threat Model for MCP

### Attack Surface

```
┌──────────────────────────────────────────────┐
│                 Threat Vectors                │
├──────────────────────────────────────────────┤
│                                              │
│  ┌──────────┐    ┌──────────┐    ┌────────┐ │
│  │ Token     │    │ Prompt   │    │ Supply │ │
│  │ Theft     │    │ Injection│    │ Chain  │ │
│  └────┬─────┘    └────┬─────┘    └───┬────┘ │
│       │               │              │       │
│       ▼               ▼              ▼       │
│  ┌─────────────────────────────────────────┐ │
│  │         MCP Gateway                      │ │
│  └────┬────────────┬────────────┬──────────┘ │
│       │            │            │            │
│  ┌────▼────┐  ┌────▼────┐  ┌───▼─────┐     │
│  │ Unauth  │  │ Data    │  │ Lateral │     │
│  │ Access  │  │ Exfil   │  │ Movement│     │
│  └─────────┘  └─────────┘  └─────────┘     │
└──────────────────────────────────────────────┘
```

### Threat Matrix

| Threat | Likelihood | Impact | Mitigation |
|---|---|---|---|
| Token theft from config | Medium | Critical | Environment variables, secret managers |
| Prompt injection via tools | Medium | High | Input validation, output sanitization |
| Supply chain attack on MCP packages | Low | Critical | Version pinning, integrity checks |
| Unauthorized file access | Medium | High | Directory restrictions, least privilege |
| Data exfiltration via MCP tools | Low | High | Output monitoring, allowed destinations |
| Privilege escalation | Low | Critical | Role-based access, capability limits |

## Token Management

### Token Security Requirements

| Requirement | Implementation | Priority |
|---|---|---|
| No hardcoded tokens | Environment variables only | Critical |
| Token rotation | Regular rotation schedule | High |
| Least privilege scopes | Minimal required permissions | Critical |
| Token encryption at rest | OS keychain or secret manager | High |
| Token audit trail | Log token usage, not values | Medium |

### Secure Token Configuration

```json
{
  "mcpServers": {
    "github": {
      "type": "stdio",
      "command": "github-mcp-server",
      "env": {
        "GITHUB_TOKEN": "${GITHUB_TOKEN}"
      }
    }
  }
}
```

**Token Handling Rules:**
```
✅ DO: Use environment variable references (${VAR_NAME})
✅ DO: Use GitHub Actions secrets for CI/CD tokens
✅ DO: Rotate tokens at least quarterly
✅ DO: Use fine-grained PATs with minimal scopes
✅ DO: Revoke tokens immediately when compromised

❌ DON'T: Hardcode tokens in configuration files
❌ DON'T: Commit tokens to version control
❌ DON'T: Share tokens between environments
❌ DON'T: Use classic PATs with broad scopes
❌ DON'T: Log token values in any log output
```

### GitHub Token Scopes (Principle of Least Privilege)

| MCP Operation | Required Scope | Justification |
|---|---|---|
| Read code | `contents:read` | Code search and file reading |
| Create PRs | `pull_requests:write` | PR creation and updates |
| Manage issues | `issues:write` | Issue creation and updates |
| Read workflows | `actions:read` | CI/CD status checking |
| Security alerts | `security_events:read` | CodeQL and Dependabot |

## Request Validation

### Input Validation for MCP Tools

**File Operations:**
```
Validation Rules:
1. Path must be within allowed directories
2. Path must not contain traversal sequences (../)
3. File extension must be in allowed list
4. File size must not exceed limits
5. Content must not contain known malicious patterns
```

**Code Operations:**
```
Validation Rules:
1. Branch names must match allowed pattern
2. Commit messages must not contain secrets
3. File content must pass security scanning
4. PR descriptions must not leak sensitive data
```

### Output Sanitization

```
Before Returning MCP Tool Output:
1. Strip any credential-like patterns
2. Remove internal IP addresses/hostnames
3. Truncate excessively large outputs
4. Validate JSON/structured output format
5. Log sanitization actions for audit
```

### Dangerous Tool Patterns

| Tool | Risk | Mitigation |
|---|---|---|
| `filesystem.write_file` | Overwrite critical files | Restrict to project directories |
| `filesystem.delete` | Data loss | Require confirmation, backup |
| `github.push_files` | Inject malicious code | Code review before merge |
| `playwright.evaluate` | Execute arbitrary JS | Sandbox, restrict domains |
| `bash.execute` | System command execution | Allowlist commands, sandbox |

## Audit Logging

### What to Log

| Event | Log Level | Data to Capture |
|---|---|---|
| MCP server start/stop | INFO | Server name, timestamp |
| Tool invocation | INFO | Tool name, parameters (sanitized) |
| Authentication success | INFO | Server name, token type (not value) |
| Authentication failure | WARN | Server name, failure reason |
| Access denied | WARN | Tool, resource, reason |
| Configuration change | INFO | What changed, who changed it |
| Error/exception | ERROR | Error details, stack trace |

### What NOT to Log

```
❌ Token values or API keys
❌ File contents containing secrets
❌ User passwords or credentials
❌ Full request/response bodies with PII
❌ Internal network topology details
```

### Audit Log Format

```json
{
  "timestamp": "2024-01-15T10:30:00Z",
  "event": "mcp.tool.invocation",
  "server": "github",
  "tool": "create_pull_request",
  "parameters": {
    "owner": "Hack23",
    "repo": "cia",
    "title": "[REDACTED]"
  },
  "result": "success",
  "duration_ms": 1250,
  "user": "copilot-agent"
}
```

## Security Configuration Checklist

### Pre-Deployment

```
□ All tokens use environment variables (never hardcoded)
□ Token scopes follow least privilege
□ Filesystem access restricted to project directory only
□ MCP package versions pinned to specific releases
□ Configuration file committed (without secrets)
□ No sensitive data in MCP server arguments
□ SSE server URLs use HTTPS only
□ Certificate validation enabled for remote servers
```

### Periodic Review (Monthly)

```
□ Review token scopes — remove unnecessary permissions
□ Rotate tokens per schedule
□ Check for new MCP package versions and CVEs
□ Review audit logs for anomalies
□ Verify directory restrictions still appropriate
□ Test authentication failure handling
□ Review and update threat model
□ Check for deprecated MCP server versions
```

### Incident Response

```
MCP Security Incident
    │
    ├─→ Token Compromise
    │   ├─→ Revoke token immediately
    │   ├─→ Rotate all related tokens
    │   ├─→ Review audit logs for unauthorized access
    │   └─→ Update token storage mechanism
    │
    ├─→ Unauthorized File Access
    │   ├─→ Review filesystem server configuration
    │   ├─→ Check for directory traversal attempts
    │   ├─→ Restrict filesystem paths
    │   └─→ Review accessed files for data exposure
    │
    ├─→ Supply Chain Attack
    │   ├─→ Pin to known-good version
    │   ├─→ Verify package integrity
    │   ├─→ Check for malicious tool behavior
    │   └─→ Report to MCP package maintainers
    │
    └─→ Prompt Injection
        ├─→ Review tool invocation logs
        ├─→ Identify injected content
        ├─→ Assess data exposure
        └─→ Update input validation rules
```

## OWASP Agentic Security Alignment

| OWASP Agentic Risk | MCP Mitigation |
|---|---|
| Excessive Agency | Restrict tool capabilities, require confirmation |
| Tool Misuse | Input validation, output monitoring |
| Privilege Escalation | Least privilege tokens, capability limits |
| Data Leakage | Output sanitization, logging controls |
| Insecure Output | Validate all MCP tool responses |
| Supply Chain | Pin versions, verify integrity |

## ISMS Alignment

| Security Area | ISO 27001 | NIST CSF | CIS Controls |
|---|---|---|---|
| Token Management | A.8.24, A.5.17 | PR.DS-1 | CIS 3.11 |
| Access Control | A.8.3, A.8.5 | PR.AC-4 | CIS 6.1 |
| Audit Logging | A.8.15 | DE.AE-3 | CIS 8.2 |
| Input Validation | A.8.28 | PR.IP-12 | CIS 16.1 |
| Configuration Mgmt | A.8.9 | PR.IP-1 | CIS 4.1 |
| Incident Response | A.5.24-A.5.27 | RS.MA-1 | CIS 17.1 |

## References

- [Model Context Protocol Security](https://modelcontextprotocol.io/docs/concepts/security)
- [OWASP Agentic AI Security](https://owasp.org/www-project-agentic-ai-threats/)
- [GitHub Fine-grained PATs](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens)
- [Hack23 ISMS Security Policy](https://github.com/Hack23/ISMS-PUBLIC)
