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

## 📊 Real-World Example

### Creating PostgreSQL Optimization Issues

Here's how we created 4 issues for PostgreSQL optimization and assigned them to Copilot:

#### Issues Created

| Issue | Target File | Purpose |
|-------|-------------|---------|
| #8033 | `.devcontainer/init-postgresql.sh` | Codespaces PostgreSQL tuning |
| #8034 | `.github/workflows/copilot-setup-steps.yml` | Copilot workflow PostgreSQL tuning |
| #8035 | `.github/workflows/release.yml` | Release workflow PostgreSQL tuning |
| #8036 | `README.md` | Documentation for PostgreSQL setup |

#### Assignment Command

```javascript
// Assign all 4 issues to Copilot
for (const issueNumber of [8033, 8034, 8035, 8036]) {
  github-update_issue({
    owner: "Hack23",
    repo: "cia",
    issue_number: issueNumber,
    assignees: ["copilot-swe-agent[bot]"]
  })
}
```

#### Verification

Each issue now shows:
- **Assignee**: Copilot (type: Bot)
- **Status**: Open, ready for implementation

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
