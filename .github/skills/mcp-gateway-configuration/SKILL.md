---
name: mcp-gateway-configuration
description: MCP gateway setup for multi-server integration, security configuration, tool routing, and access control
license: Apache-2.0
---

# MCP Gateway Configuration Skill

## Purpose

This skill provides guidance for configuring MCP (Model Context Protocol) gateways for the CIA platform. It covers multi-server integration, tool routing, security configuration, and access control to enable secure and efficient AI-assisted development workflows.

## When to Use This Skill

Apply this skill when:
- ✅ Setting up or modifying `.github/copilot-mcp-config.json`
- ✅ Adding new MCP servers to the gateway
- ✅ Configuring tool routing between MCP servers
- ✅ Setting up access control for MCP tools
- ✅ Troubleshooting MCP connectivity issues
- ✅ Reviewing MCP configuration for security
- ✅ Integrating new data sources via MCP

Do NOT use for:
- ❌ MCP security hardening (use mcp-gateway-security)
- ❌ General API gateway configuration (different pattern)
- ❌ Application-level API design (use service layer patterns)

## MCP Architecture Overview

```
┌─────────────────────────────────────────────┐
│              GitHub Copilot                  │
│          (AI Assistant Client)               │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│           MCP Gateway Layer                  │
│  ┌─────────────────────────────────────┐    │
│  │   copilot-mcp-config.json           │    │
│  │   - Server definitions              │    │
│  │   - Tool routing rules              │    │
│  │   - Access control policies         │    │
│  └─────────────────────────────────────┘    │
└──────┬──────────┬──────────┬────────────────┘
       │          │          │
       ▼          ▼          ▼
┌──────────┐ ┌──────────┐ ┌──────────┐
│ GitHub   │ │ Filesystem│ │ Playwright│
│ MCP      │ │ MCP      │ │ MCP      │
│ Server   │ │ Server   │ │ Server   │
└──────────┘ └──────────┘ └──────────┘
```

## Configuration Structure

### Base Configuration (`copilot-mcp-config.json`)

```json
{
  "mcpServers": {
    "server-name": {
      "type": "stdio",
      "command": "command-to-run",
      "args": ["arg1", "arg2"],
      "env": {
        "ENV_VAR": "value"
      }
    }
  }
}
```

### Server Type Patterns

**stdio Servers (Local Process):**
```json
{
  "filesystem": {
    "type": "stdio",
    "command": "npx",
    "args": ["-y", "@modelcontextprotocol/server-filesystem", "/path/to/allowed"],
    "env": {}
  }
}
```

**SSE Servers (Remote HTTP):**
```json
{
  "remote-server": {
    "type": "sse",
    "url": "https://mcp-server.example.com/sse",
    "headers": {
      "Authorization": "Bearer ${MCP_TOKEN}"
    }
  }
}
```

## CIA Platform MCP Servers

### Required Servers

| Server | Purpose | Tools Provided |
|---|---|---|
| **github** | Repository operations | Issues, PRs, code search, Actions |
| **filesystem** | Local file operations | Read, write, search files |
| **playwright** | Browser automation | UI testing, screenshots |

### Configuration Best Practices

**1. Minimize Filesystem Access:**
```json
{
  "filesystem": {
    "type": "stdio",
    "command": "npx",
    "args": [
      "-y", "@modelcontextprotocol/server-filesystem",
      "/home/runner/work/cia/cia"
    ]
  }
}
```
Only expose the project root — never expose `/`, `/home`, or parent directories.

**2. Use Environment Variables for Secrets:**
```json
{
  "github": {
    "type": "stdio",
    "command": "github-mcp-server",
    "env": {
      "GITHUB_TOKEN": "${GITHUB_TOKEN}"
    }
  }
}
```
Never hardcode tokens in configuration files.

**3. Specify Exact Package Versions:**
```json
{
  "args": ["-y", "@modelcontextprotocol/server-filesystem@1.2.3"]
}
```
Pin versions to prevent supply chain attacks.

## Tool Routing

### Routing Principles

1. **Least Privilege** — Each server should only expose tools needed for its purpose
2. **Separation of Concerns** — Different servers for different capabilities
3. **Fail-Safe Defaults** — Tools should default to read-only when possible
4. **Audit Trail** — All tool invocations should be logged

### Tool Categories

| Category | Server | Example Tools |
|---|---|---|
| Code Management | github | create_pull_request, push_files |
| Code Analysis | github | search_code, get_file_contents |
| File Operations | filesystem | read_file, write_file, search |
| UI Testing | playwright | navigate, click, screenshot |
| Issue Management | github | create_issue, list_issues |
| CI/CD | github | list_workflows, get_job_logs |

## Access Control Configuration

### Server-Level Access Control

```json
{
  "mcpServers": {
    "filesystem": {
      "type": "stdio",
      "command": "npx",
      "args": [
        "-y", "@modelcontextprotocol/server-filesystem",
        "/home/runner/work/cia/cia"
      ],
      "env": {
        "ALLOWED_OPERATIONS": "read,write,search"
      }
    }
  }
}
```

### Directory Restrictions

Allowed directories should follow the principle of least privilege:

```
✅ /home/runner/work/cia/cia          — Project root
✅ /home/runner/work/cia/cia/src      — Source code
✅ /home/runner/work/cia/cia/.github  — CI/CD configuration

❌ /home/runner                        — Too broad
❌ /etc                                — System configuration
❌ /tmp                                — Temporary files (security risk)
```

## Troubleshooting

### Common Issues

| Issue | Symptom | Resolution |
|---|---|---|
| Server not starting | "Failed to connect" error | Check command path and args |
| Permission denied | Tool call fails | Verify filesystem paths and permissions |
| Token expired | Authentication errors | Refresh environment variables |
| Version mismatch | Unexpected tool behavior | Pin and update package versions |
| Timeout | Tool call hangs | Check network connectivity for SSE servers |

### Diagnostic Commands

```bash
# Verify MCP config syntax
cat .github/copilot-mcp-config.json | python3 -m json.tool

# Check if MCP server binary is available
which github-mcp-server

# Test filesystem server
npx -y @modelcontextprotocol/server-filesystem --help

# Check environment variables
env | grep -i mcp
env | grep -i github_token
```

## Configuration Validation Checklist

```
□ JSON syntax is valid
□ All server commands exist and are executable
□ Environment variables are properly referenced (not hardcoded)
□ Filesystem paths follow least privilege
□ Package versions are pinned
□ No secrets in configuration file
□ Configuration is committed to repository
□ Server definitions match documented architecture
□ Access control rules are documented
```

## ISMS Alignment

| Configuration Area | ISO 27001 | NIST CSF | CIS Controls |
|---|---|---|---|
| Access Control | A.8.3 | PR.AC-4 | CIS 6.1 |
| Secret Management | A.8.24 | PR.DS-1 | CIS 3.11 |
| Configuration Mgmt | A.8.9 | PR.IP-1 | CIS 4.1 |
| Audit Logging | A.8.15 | DE.AE-3 | CIS 8.2 |
| Change Control | A.8.32 | PR.IP-3 | CIS 4.2 |

## References

- [Model Context Protocol Specification](https://modelcontextprotocol.io/)
- [GitHub Copilot MCP Configuration](https://docs.github.com/en/copilot/customizing-copilot/extending-the-capabilities-of-github-copilot-in-your-organization)
- [CIA `.github/copilot-mcp-config.json`](../copilot-mcp-config.json)
