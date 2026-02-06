# GitHub Issue Creation Status

## Objective
Create 5 GitHub issues for sample data extraction improvements using GitHub MCP server.

## Status: Specifications Complete ✅

All 5 issue specifications have been prepared and are ready for creation. The specifications include:
- Detailed objectives and background
- Measured current state metrics
- Clear acceptance criteria
- Implementation guidance with code examples
- Agent recommendations (@stack-specialist)
- Dependencies and related documentation

## Issue Files Ready

| # | File | Title | Priority | Effort |
|---|------|-------|----------|--------|
| 1 | `issue-1-fix-materialized-view-refresh.md` | Fix materialized view refresh sequence | High | Medium (4-8h) |
| 2 | `issue-2-fix-negative-limit-errors.md` | Fix negative LIMIT errors | High | Small (1-2h) |
| 3 | `issue-3-complete-distinct-values.md` | Complete distinct value extraction | Medium | Medium (4-8h) |
| 4 | `issue-4-add-percentile-statistics.md` | Add percentile statistics | Medium | Medium (4-8h) |
| 5 | `issue-5-add-normal-samples.md` | Add normal sample extraction | Medium | Medium (4-8h) |

## How to Create Issues

### Option 1: Automated Script (Recommended)
```bash
cd service.data.impl/sample-data
gh auth login  # One-time authentication
./create-github-issues.sh
```

### Option 2: Manual Creation
For each issue file (issue-1 through issue-5):
1. Go to https://github.com/Hack23/cia/issues/new
2. Copy the content from the corresponding `issue-*.md` file
3. Apply labels:
   - All: `type:refactor`, `domain:database`
   - Issues #1-2: `priority:high`
   - Issues #3-5: `priority:medium`
   - Issue #2: `size:small`
   - Issues #1,3-5: `size:medium`
   - Issues #3-4: `domain:testing`
4. Create the issue

## Expected Outcomes

After creation, all 5 issues will:
- Have accurate metrics from actual analysis
- Include detailed implementation guidance
- Reference @stack-specialist for implementation
- Link to relevant documentation
- Follow proper dependency chain (Issue #1 → #2 → #3-4 → #5)

## GitHub MCP Server Note

The GitHub MCP server configuration is present in `.github/copilot-mcp-config.json` with HTTP endpoint at `https://api.githubcopilot.com/mcp/insiders`. The PAT (Personal Access Token) is configured via `COPILOT_MCP_GITHUB_PERSONAL_ACCESS_TOKEN` secret.

While the MCP server provides organization-wide access to Hack23 repositories, the direct issue creation via the MCP server API requires authentication that may not be available in the current execution context. The issue specifications are complete and ready for creation using either the automated script or manual process.
