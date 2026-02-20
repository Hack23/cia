---
name: mcp-server-development
description: Model Context Protocol server development, MCP tool patterns, MCP configuration best practices, GitHub MCP integration
license: Apache-2.0
---

# MCP Server Development Skill

## Purpose

Guide the development and configuration of Model Context Protocol (MCP) servers for the CIA platform, enabling AI-powered tooling integration with GitHub Copilot and other MCP-compatible clients.

## When to Use

- ✅ Creating new MCP server tools for political data access
- ✅ Configuring `.github/copilot-mcp-config.json` for new integrations
- ✅ Designing tool schemas for structured data retrieval
- ✅ Implementing MCP transports (stdio, SSE, HTTP)
- ✅ Integrating GitHub MCP tools with CI/CD workflows

Do NOT use for:
- ❌ Standard REST API development (use api-integration skill)
- ❌ UI component development (use vaadin-component-design skill)

## MCP Architecture Overview

```
┌─────────────────┐     ┌──────────────┐     ┌─────────────────┐
│  MCP Client     │────▶│  MCP Server  │────▶│  Data Sources   │
│  (Copilot/IDE)  │◀────│  (Tools)     │◀────│  (Riksdag API)  │
└─────────────────┘     └──────────────┘     └─────────────────┘
        │                       │
        │ JSON-RPC 2.0         │ Tool Definitions
        │ over stdio/SSE       │ Input/Output Schemas
```

## MCP Configuration Best Practices

### GitHub Copilot MCP Config

```json
{
  "mcpServers": {
    "cia-political-data": {
      "type": "stdio",
      "command": "node",
      "args": ["dist/mcp-server.js"],
      "env": {
        "CIA_DATA_DIR": "${workspaceFolder}/data"
      }
    }
  }
}
```

### Key Configuration Rules

1. **Never embed secrets** in `copilot-mcp-config.json` — use environment references
2. **Scope tools narrowly** — each tool should do one thing well
3. **Validate all inputs** against JSON Schema before processing
4. **Return structured data** — prefer typed objects over free-form strings
5. **Include error details** in tool responses for debugging

## MCP Tool Design Patterns

### Tool Definition Schema

```typescript
interface McpToolDefinition {
  name: string;           // e.g., "get_politician_votes"
  description: string;    // Clear, concise purpose
  inputSchema: {
    type: "object";
    properties: Record<string, JsonSchema>;
    required: string[];
  };
}
```

### CIA-Specific Tool Examples

```typescript
// Politician lookup tool
const getPoliticianTool = {
  name: "get_politician_profile",
  description: "Retrieve profile and voting record for a Swedish Parliament member",
  inputSchema: {
    type: "object",
    properties: {
      personId: { type: "string", description: "Riksdagen person ID" },
      includeVotes: { type: "boolean", default: false }
    },
    required: ["personId"]
  }
};

// Document search tool
const searchDocumentsTool = {
  name: "search_riksdag_documents",
  description: "Search Swedish Parliament documents by keyword, type, and date range",
  inputSchema: {
    type: "object",
    properties: {
      query: { type: "string" },
      docType: { type: "string", enum: ["motion", "proposition", "betankande"] },
      fromDate: { type: "string", format: "date" },
      toDate: { type: "string", format: "date" }
    },
    required: ["query"]
  }
};
```

### Error Handling Pattern

```typescript
async function handleToolCall(name: string, args: unknown): Promise<McpResult> {
  try {
    const validated = validateInput(name, args);
    const result = await executeQuery(validated);
    return { content: [{ type: "text", text: JSON.stringify(result) }] };
  } catch (error) {
    return {
      content: [{ type: "text", text: `Error: ${error.message}` }],
      isError: true
    };
  }
}
```

## GitHub MCP Integration

### Available GitHub MCP Tools

The CIA project uses GitHub MCP server for:
- **Repository management** — branches, commits, file operations
- **Issue tracking** — create, update, search issues
- **Pull request workflows** — reviews, comments, merges
- **Actions integration** — trigger workflows, check status
- **Security scanning** — code scanning alerts, Dependabot

### Best Practices for GitHub MCP

1. **Use specific queries** — avoid broad searches that return too many results
2. **Paginate results** — always handle pagination for large result sets
3. **Cache responses** — reduce API calls for frequently accessed data
4. **Handle rate limits** — implement exponential backoff

## Security Considerations

- **Input validation** — sanitize all tool inputs before processing
- **Authentication** — use token-based auth, never hardcode credentials
- **Authorization** — scope tool access to minimum required permissions
- **Logging** — log tool invocations for audit trails, never log sensitive data
- **Transport security** — use encrypted transports for remote MCP servers

## ISMS Alignment

| Control | Requirement |
|---------|-------------|
| ISO 27001 A.8.9 | Configuration management for MCP servers |
| NIST CSF PR.DS-2 | Data-in-transit protection for MCP transport |
| CIS Control 16 | Application software security for MCP tools |
