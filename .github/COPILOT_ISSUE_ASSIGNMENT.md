# 🤖 GitHub Copilot Issue Assignment Guide

This document describes how to create GitHub issues and assign them to GitHub Copilot's coding agent for automated implementation.

## 📋 Overview

GitHub Copilot can now be assigned to issues just like human developers. When assigned, Copilot will automatically analyze the issue and create a pull request with the implementation.

## 🔧 Prerequisites

### Required Permissions

1. **Personal Access Token (PAT)** with the following scopes:
   - `repo` - Full control of private repositories
   - `write:org` - Read and write organization membership
   - `issues:write` - Create and modify issues
   - `pull-requests:write` - Create and modify pull requests

2. **Repository Settings**:
   - Copilot coding agent must be enabled for the repository
   - PAT must be stored as a secret (e.g., `COPILOT_MCP_GITHUB_PERSONAL_ACCESS_TOKEN`)

### Environment Configuration

The PAT should be configured in the GitHub repository:

1. **Repository Settings** → **Environments** → **Configure copilot**
2. Add environment secret: `COPILOT_MCP_GITHUB_PERSONAL_ACCESS_TOKEN`

## 🛠️ Methods for Assigning Issues to Copilot

### Method 1: REST API (Recommended)

You can assign issues to Copilot using the standard GitHub REST API:

```bash
# Using curl
curl -X POST \
  -H "Accept: application/vnd.github+json" \
  -H "Authorization: Bearer $GITHUB_TOKEN" \
  -H "X-GitHub-Api-Version: 2022-11-28" \
  https://api.github.com/repos/OWNER/REPO/issues/ISSUE_NUMBER/assignees \
  -d '{"assignees": ["copilot-swe-agent[bot]"]}'
```

```bash
# Using GitHub CLI
gh api \
  --method POST \
  -H "Accept: application/vnd.github+json" \
  -H "X-GitHub-Api-Version: 2022-11-28" \
  /repos/OWNER/REPO/issues/ISSUE_NUMBER/assignees \
  -f "assignees[]=copilot-swe-agent[bot]"
```

### Method 2: GraphQL API

For more complex operations, use the GraphQL API with the required feature flag:

```bash
# Get the Copilot bot ID first
BOT_ID=$(gh api graphql \
  -H "GraphQL-Features: issues_copilot_assignment_api_support" \
  -f query='{ 
    repository(owner:"OWNER",name:"REPO") { 
      suggestedActors(capabilities:[CAN_BE_ASSIGNED],first:10) { 
        nodes { login id } 
      } 
    } 
  }' \
  --jq '.data.repository.suggestedActors.nodes[] | select(.login | contains("copilot")) | .id')

# Assign to issue
gh api graphql \
  -H "GraphQL-Features: issues_copilot_assignment_api_support" \
  -f query="mutation { 
    addAssigneesToAssignable(input: {
      assignableId: \"ISSUE_NODE_ID\", 
      assigneeIds: [\"$BOT_ID\"]
    }) { clientMutationId } 
  }"
```

### Method 3: GitHub MCP Tools (For Copilot Agents)

When using GitHub MCP tools within Copilot, you can use the `github-update_issue` function:

```javascript
// Using MCP tool
github-update_issue({
  owner: "Hack23",
  repo: "cia",
  issue_number: 8033,
  assignees: ["copilot-swe-agent[bot]"]
})
```

### Method 4: GitHub UI (Manual)

1. Navigate to the issue in GitHub
2. Click "Assignees" in the right sidebar
3. Search for "Copilot" or "copilot-swe-agent"
4. Select the Copilot coding agent

## 📝 Creating Effective Issues for Copilot

### Issue Structure Best Practices

For Copilot to effectively implement issues, structure them with:

```markdown
## 🎯 Objective
[One-sentence description of what needs to be accomplished]

## 📋 Background
[Context: Why this is needed, current state, impact]

## 📊 Current State (Measured Metrics)
[Actual measurements: file paths, settings, error counts, etc.]

## ✅ Acceptance Criteria
- [ ] Criterion 1 with measurable outcome
- [ ] Criterion 2 with specific deliverable
- [ ] Tests added/updated (specify coverage target)
- [ ] Documentation updated (if applicable)

## 🛠️ Implementation Guidance
**Files to Modify:**
- `path/to/file.java` - [specific changes needed]
- `path/to/config.yml` - [configuration modifications]

**Approach:**
1. Step 1: [detailed action]
2. Step 2: [detailed action]
3. Edge cases: [considerations]
4. Testing strategy: [how to validate]

**Example Code:**
```java
// Implementation code example
```

## 🤖 Recommended Agent
**Agent**: @agent-name
**Rationale**: [Why this agent is the best fit]

## 📚 Related Documentation
- [Link to relevant docs]
- [Link to related issues]
```

### Labels for Categorization

Apply appropriate labels:
- **Type**: `type: feature`, `type: bug`, `type: docs`, `type: refactor`
- **Priority**: `priority: critical`, `priority: high`, `priority: medium`, `priority: low`
- **Size**: `size: small`, `size: medium`, `size: large`
- **Domain**: `domain: infrastructure`, `domain: backend`, `domain: frontend`, `domain: security`

## 🔄 Workflow Example

### Step 1: Create the Issue

```bash
# Create issue using GitHub CLI
gh issue create \
  --title "Optimize PostgreSQL Configuration" \
  --body "$(cat issue-template.md)" \
  --label "type: feature,priority: medium,domain: infrastructure"
```

Or use the GitHub MCP tool:

```javascript
github-create_issue({
  owner: "Hack23",
  repo: "cia",
  title: "Optimize PostgreSQL Configuration",
  body: "Issue body with implementation guidance...",
  labels: ["type: feature", "priority: medium", "domain: infrastructure"]
})
```

### Step 2: Assign to Copilot

```bash
# Assign using REST API
gh api \
  --method POST \
  -H "Accept: application/vnd.github+json" \
  /repos/Hack23/cia/issues/ISSUE_NUMBER/assignees \
  -f "assignees[]=copilot-swe-agent[bot]"
```

Or use the GitHub MCP tool:

```javascript
github-update_issue({
  owner: "Hack23",
  repo: "cia",
  issue_number: ISSUE_NUMBER,
  assignees: ["copilot-swe-agent[bot]"]
})
```

### Step 3: Monitor Progress

Once assigned, Copilot will:
1. Analyze the issue requirements
2. Create a branch
3. Implement the changes
4. Create a pull request
5. Request review

## 📊 Real-World Example: PR #8032 - PostgreSQL Optimization Issues

This section documents exactly what was done in PR #8032 to create 4 issues and assign them to Copilot.

### Context and Environment

**PR**: [#8032](https://github.com/Hack23/cia/pull/8032) - Create 4 GitHub issues for optimized PostgreSQL setup  
**Date**: December 3, 2025  
**Custom Agent Used**: `hack23-cia-taskagent`

#### PAT Access Configuration

The PAT was configured via the MCP (Model Context Protocol) server system:

1. **Secret Storage**: `COPILOT_MCP_GITHUB_PERSONAL_ACCESS_TOKEN` stored in:
   - Repository Settings → Environments → "copilot" environment
   - Environment secrets section

2. **Secret Injection**: The PAT is injected into MCP servers via:
   ```
   COPILOT_AGENT_INJECTED_SECRET_NAMES=COPILOT_MCP_GITHUB_PERSONAL_ACCESS_TOKEN
   ```

3. **MCP Configuration** (from `.github/copilot-mcp-config.json`):
   ```json
   {
     "mcpServers": {
       "github": {
         "command": "npx",
         "args": ["-y", "@modelcontextprotocol/server-github"],
         "env": {
           "GITHUB_TOKEN": "${{ secrets.COPILOT_MCP_GITHUB_PERSONAL_ACCESS_TOKEN }}",
           "GITHUB_PERSONAL_ACCESS_TOKEN": "${{ secrets.COPILOT_MCP_GITHUB_PERSONAL_ACCESS_TOKEN }}"
         }
       }
     }
   }
   ```

### Step 1: Issue Creation (Using Task Agent)

The 4 issues were created by the `hack23-cia-taskagent` custom agent, which analyzed:
- `service.data.impl/README-SCHEMA-MAINTENANCE.md` for PostgreSQL best practices
- Current PostgreSQL configurations in target files
- Missing performance optimizations

#### Issues Created

| Issue | Target File | Node ID | Purpose |
|-------|-------------|---------|---------|
| [#8033](https://github.com/Hack23/cia/issues/8033) | `.devcontainer/init-postgresql.sh` | `I_kwDOAmMR6M7cEDQY` | Codespaces PostgreSQL tuning |
| [#8034](https://github.com/Hack23/cia/issues/8034) | `.github/workflows/copilot-setup-steps.yml` | `I_kwDOAmMR6M7cEDiV` | Copilot workflow PostgreSQL tuning |
| [#8035](https://github.com/Hack23/cia/issues/8035) | `.github/workflows/release.yml` | `I_kwDOAmMR6M7cED26` | Release workflow PostgreSQL tuning |
| [#8036](https://github.com/Hack23/cia/issues/8036) | `README.md` | `I_kwDOAmMR6M7cEERw` | Documentation for PostgreSQL setup |

### Step 2: Issue Assignment to Copilot

#### Initial Attempts (Failed)

**Attempt 1**: REST API with `copilot-swe-agent`
```javascript
github-update_issue({
  owner: "Hack23",
  repo: "cia", 
  issue_number: 8033,
  assignees: ["copilot-swe-agent"]
})
```
**Result**: `Validation Error: Validation Failed - "copilot-swe-agent" is invalid`

**Attempt 2**: REST API with `copilot`
```javascript
github-update_issue({
  owner: "Hack23",
  repo: "cia",
  issue_number: 8033, 
  assignees: ["copilot"]
})
```
**Result**: `Validation Error: Validation Failed - "copilot" is invalid`

#### Successful Method: REST API with `copilot-swe-agent[bot]`

The correct assignee username is `copilot-swe-agent[bot]` (with `[bot]` suffix):

```javascript
// MCP Tool Call - github-update_issue
github-update_issue({
  owner: "Hack23",
  repo: "cia",
  issue_number: 8033,
  assignees: ["copilot-swe-agent[bot]"]
})
```

#### Actual MCP Tool Calls Made

All 4 issues were assigned in parallel using the `github-update_issue` MCP tool:

```javascript
// Issue #8033
github-update_issue({
  owner: "Hack23",
  repo: "cia",
  issue_number: 8033,
  assignees: ["copilot-swe-agent[bot]"]
})

// Issue #8034
github-update_issue({
  owner: "Hack23",
  repo: "cia",
  issue_number: 8034,
  assignees: ["copilot-swe-agent[bot]"]
})

// Issue #8035
github-update_issue({
  owner: "Hack23",
  repo: "cia",
  issue_number: 8035,
  assignees: ["copilot-swe-agent[bot]"]
})

// Issue #8036
github-update_issue({
  owner: "Hack23",
  repo: "cia",
  issue_number: 8036,
  assignees: ["copilot-swe-agent[bot]"]
})
```

#### API Response (Truncated Example for #8033)

```json
{
  "url": "https://api.github.com/repos/Hack23/cia/issues/8033",
  "html_url": "https://github.com/Hack23/cia/issues/8033",
  "id": 3692049432,
  "node_id": "I_kwDOAmMR6M7cEDQY",
  "number": 8033,
  "title": "Optimize PostgreSQL Configuration for GitHub Codespaces Development Environment",
  "state": "open",
  "assignee": {
    "login": "Copilot",
    "id": 198982749,
    "node_id": "BOT_kgDOC9w8XQ",
    "avatar_url": "https://avatars.githubusercontent.com/in/1143301?v=4",
    "html_url": "https://github.com/apps/copilot-swe-agent",
    "type": "Bot"
  },
  "assignees": [
    {
      "login": "Copilot",
      "id": 198982749,
      "type": "Bot"
    }
  ],
  "updated_at": "2025-12-03T23:18:57Z"
}
```

### Step 3: Verification

After assignment, each issue shows:
- **Assignee**: `Copilot` (type: `Bot`, id: `198982749`)
- **Assignee URL**: `https://github.com/apps/copilot-swe-agent`
- **Status**: Open, ready for Copilot to pick up

### Key Findings

1. **Correct Assignee Username**: Must use `copilot-swe-agent[bot]` (with `[bot]` suffix)
2. **REST API Works**: The GitHub REST API now supports Copilot assignment directly
3. **MCP Tool**: The `github-update_issue` MCP tool uses REST API under the hood
4. **PAT Injection**: PAT is automatically injected via `COPILOT_AGENT_INJECTED_SECRET_NAMES`
5. **Bot Metadata**: Copilot appears as `type: "Bot"` with id `198982749`

### Timeline

| Time (UTC) | Action |
|------------|--------|
| 21:55:12 | Issue #8033 created |
| 21:55:43 | Issue #8034 created |
| 21:56:12 | Issue #8035 created |
| 21:56:49 | Issue #8036 created |
| 23:18:57 | All 4 issues assigned to Copilot |

### Labels Applied

All issues received these labels:
- `type: feature`
- `priority: medium`
- `domain: infrastructure`
- `size: small`

Exception: #8036 (README) also has `type: docs` instead of `type: feature`

## 🔐 Security Considerations

1. **PAT Security**: Store PAT as encrypted environment secrets, never in code
2. **Scope Limitation**: Use minimum required permissions for PAT
3. **Audit Trail**: All assignments are logged in GitHub's audit log
4. **Review Process**: Copilot PRs should still go through code review

## 🐛 Troubleshooting

### Common Issues

**Error: "Invalid assignee"**
- Ensure you're using the correct username: `copilot-swe-agent[bot]`
- Verify Copilot is enabled for the repository

**Error: "Resource not accessible"**
- Check PAT has `issues:write` scope
- Verify PAT is not expired

**Error: "Validation Failed"**
- The assignee username might be incorrect
- Try using the GraphQL API with feature flag

### Getting Issue Node IDs

For GraphQL operations, you need the issue's node ID:

```bash
# Get issue node ID
gh api repos/OWNER/REPO/issues/ISSUE_NUMBER --jq '.node_id'
```

## 📚 References

- [GitHub REST API - Add Assignees](https://docs.github.com/en/rest/issues/assignees#add-assignees-to-an-issue)
- [GitHub GraphQL API - Mutations](https://docs.github.com/en/graphql/reference/mutations)
- [GitHub Copilot Documentation](https://docs.github.com/en/copilot)
- [Repository Custom Agents](.github/agents/README.md)

## 🤝 Contributing

To improve this guide:
1. Open an issue describing the improvement
2. Submit a PR with your changes
3. Reference the issue in your PR

---

**Last Updated**: December 2025  
**Maintainer**: @pethers
