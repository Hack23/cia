---
name: github-agentic-workflows
description: Master GitHub Agentic Workflows (gh-aw) - AI-powered repository automation with safe outputs, sandboxed execution, and multi-engine support
license: Apache-2.0
---

# GitHub Agentic Workflows Skill

## Purpose

Guide creation, deployment, and governance of **GitHub Agentic Workflows** (`gh-aw`) — markdown-defined AI automations that run inside GitHub Actions with defense-in-depth security. Developed by GitHub Next and Microsoft Research, they augment deterministic CI/CD with [Continuous AI](https://githubnext.com/projects/continuous-ai) capabilities.

## When to Use

- ✅ AI-driven issue triage, PR review, documentation maintenance, code quality improvement
- ✅ Multi-agent orchestration with specialized workers
- ✅ Scheduled/event-driven repository analysis and reporting
- ✅ Continuous improvement workflows (code simplification, security scanning, metrics)
- ❌ Simple deterministic shell-script automation (use traditional GitHub Actions)
- ❌ Operations requiring 100% predictable execution paths

## Architecture & Security Layers

GitHub Agentic Workflows enforce **five security layers**:

| Layer | Mechanism | Effect |
|-------|-----------|--------|
| **Read-only tokens** | Agent receives read-only GitHub token | Cannot push, create PRs, or delete files |
| **Zero secrets** | Write tokens never enter agent process | Compromised agent has nothing to steal |
| **Container + Firewall** | Agent Workflow Firewall (AWF) with Squid proxy allowlist | Blocks exfiltration to unapproved domains |
| **Safe outputs** | Structured artifact → gated write job | Agent proposes; separate job with scoped permissions executes |
| **Threat detection** | AI-powered scan before output is applied | Blocks prompt injection, leaked credentials, malicious code |

```
Event → [Sandboxed Agent (read-only, firewalled)] → Proposed Output (artifact)
  → Threat Detection (AI scan) → ✓ Safe → Write Job (scoped token) → GitHub API
                                → ✗ Suspicious → Blocked
```

## Workflow Structure

Every workflow is a **markdown file** with YAML frontmatter + natural language body:

```markdown
---
on:
  schedule: daily          # or: issue, pull_request, discussion, workflow_dispatch, etc.
timeout-minutes: 10
permissions:
  contents: read
  issues: read
tools:
  github:
    toolsets: [issues, pull_requests, repos]
safe-outputs:
  create-issue:
    title-prefix: "[report] "
    labels: [automated, daily]
    max: 1
    close-older-issues: true
---

# Daily Repository Status Report

Analyze recent activity and create a summary issue.

## Instructions
- Review open issues, merged PRs, and recent commits
- Highlight blockers and achievements
- Provide actionable next steps for maintainers
```

The `gh aw compile` CLI generates a `.lock.yml` GitHub Actions workflow from this markdown.

## AI Engines

| Engine | Key | Notes |
|--------|-----|-------|
| **GitHub Copilot** | `copilot` (default) | Best general-purpose agent |
| **Claude (Anthropic)** | `claude` | Strong for analysis and NLP tasks |
| **OpenAI Codex** | `codex` | Code-focused tasks |
| **Google Gemini** | `gemini` | Multi-modal capabilities |

Set engine in frontmatter: `engine: claude`

## Triggers (`on:`)

| Trigger | Syntax | Use Case |
|---------|--------|----------|
| **Schedule** | `schedule: daily`, `schedule: "0 9 * * 1-5"` | Periodic reports, scans |
| **Issue events** | `issue: types: [opened, reopened]` | Triage, auto-labeling |
| **PR events** | `pull_request: types: [opened, synchronize]` | Code review, checks |
| **Discussion** | `discussion: types: [created]` | Task mining, Q&A |
| **Dispatch** | `workflow_dispatch:` | Manual trigger |
| **Comment** | `issue_comment: types: [created]` | Slash commands (`/plan`, `/review`) |

## Tools Configuration

### GitHub Tools (MCP-based)
```yaml
tools:
  github:
    toolsets: [issues, pull_requests, repos, code_search, actions, security]
    min-integrity: approved   # or: none (for public repos processing external contributors)
```

Available toolsets: `issues`, `pull_requests`, `repos`, `code_search`, `actions`, `security`, `labels`, `discussions`, `projects`, `users`, `notifications`, `stars`, `gists`

### Custom MCP Servers
```yaml
tools:
  mcp:
    brave-search:
      command: npx -y @anthropic/mcp-brave-search
      env:
        BRAVE_API_KEY: ${{ secrets.BRAVE_API_KEY }}
```

### Integrity Filtering

Controls which users' content the agent can see. Critical for public repositories:

| Level | Who Is Visible | Use When |
|-------|---------------|----------|
| `approved` (default) | Owners, members, collaborators | Most workflows |
| `none` | All contributors | Issue triage in public repos |

## Safe Outputs

Safe outputs are the **only way** agents write to GitHub. Each type has hard constraints:

### Issue Operations
```yaml
safe-outputs:
  create-issue:
    title-prefix: "[auto] "    # Required prefix (prevents impersonation)
    labels: [automated]         # Allowed labels only
    max: 3                      # Hard limit per run
    close-older-issues: true    # Auto-close previous issues with same prefix
  add-labels:
    allowed: [bug, feature, enhancement, documentation, question]
  add-comment: {}               # Allow commenting on issues
```

### PR Operations
```yaml
safe-outputs:
  create-pull-request:
    title-prefix: "[auto] "
    max: 1
    branch-prefix: "auto/"
    labels: [automated]
    draft: true                 # Create as draft PR
  add-review-comment: {}
```

### Discussion Operations
```yaml
safe-outputs:
  create-discussion:
    category: "Reports"
    title-prefix: "[daily] "
    max: 1
    close-older-discussions: true
```

## Permissions (Least Privilege)

```yaml
permissions:
  contents: read          # Repository content (always read-only for agent)
  issues: read            # Issue data access
  pull-requests: read     # PR data access
  discussions: read       # Discussion access
  actions: read           # Workflow run data
  security-events: read   # Code scanning alerts
```

Write permissions are **never granted to the agent** — only to the safe output write job.

## Network Permissions

Control outbound access from the agent container:

```yaml
network:
  allowed-domains:
    - "api.github.com"
    - "*.githubusercontent.com"
    - "pypi.org"           # For Python package installs
```

All other domains are blocked by the AWF firewall.

## Compilation & Deployment

```bash
# Install CLI
gh extension install github/gh-aw

# Add workflow from gallery
gh aw add-wizard https://github.com/github/gh-aw/blob/v0.45.5/.github/workflows/issue-triage-agent.md

# Compile markdown to lock file
gh aw compile

# Compile specific workflow
gh aw compile .github/workflows/my-workflow.md

# List workflows
gh aw list

# Validate without compiling
gh aw validate .github/workflows/my-workflow.md
```

The `.lock.yml` file is the actual GitHub Actions workflow. Never edit it directly — always edit the `.md` source and recompile.

## Workflow Gallery (Key Patterns)

### Issue & PR Management
- **Issue Triage Agent** — Auto-label and comment on new issues
- **PR Triage Agent** — Categorize and assign PRs
- **Plan Command** (`/plan`) — Decompose issues into sub-tasks (67% merge rate, 514+ merged PRs)
- **Auto-Assign Issue** — Route issues to appropriate team members

### Continuous Improvement
- **Code Simplifier** — Daily complexity reduction
- **Repository Quality Improver** — Holistic code quality analysis
- **Code Refiner** — Systematic refactoring suggestions
- **Dead Code Removal Agent** — Find and remove unused code

### Documentation & Reporting
- **Daily Issues Report** — Team status summaries
- **Documentation Healer** — Fix stale docs and broken links
- **Architecture Diagram Generator** — Auto-generate C4/Mermaid diagrams
- **Discussion Task Miner** — Extract actionable tasks from discussions (57% merge rate)

### Security & Quality
- **Code Scanning Fixer** — Auto-fix CodeQL/SAST findings
- **Security Review Agent** (`/security`) — On-demand security analysis
- **CI Failure Doctor** — Diagnose and suggest fixes for CI failures
- **Malicious Code Scan** — Daily security sweeps
- **Breaking Change Checker** — Detect API breaking changes

### Metrics & Analytics
- **Copilot Session Insights** — ML analysis of agent behavior
- **Prompt Clustering Analysis** — Categorize agent prompts using ML
- **Workflow Health Manager** — Monitor agent success rates and costs

### Multi-Repository
- **Organization Health Report** — Cross-repo analysis
- **Feature Sync** — Coordinate changes across repositories

## Real-World Example: CIA Platform Issue Triage

```markdown
---
on:
  issue:
    types: [opened, reopened]
timeout-minutes: 5
permissions:
  contents: read
  issues: read
tools:
  github:
    toolsets: [issues, repos, code_search]
    min-integrity: none
safe-outputs:
  add-labels:
    allowed: [bug, enhancement, documentation, security, performance, ui, data-integration]
  add-comment: {}
---

# CIA Platform Issue Triage

Analyze new issues for the Citizen Intelligence Agency platform.

## Context
This is a Java/Spring/Vaadin political intelligence platform monitoring Swedish parliament data.

## Instructions
1. Read the issue title and body carefully
2. Search the codebase for related files and patterns
3. Apply the most appropriate label from the allowed set
4. Comment with:
   - Brief analysis of the issue
   - Relevant code areas (module, package, class)
   - Suggested approach for resolution
   - Related issues if any exist

## Label Criteria
- `bug` — Incorrect behavior, crashes, data errors
- `enhancement` — New features or improvements
- `security` — Security vulnerabilities or hardening
- `performance` — Speed, memory, query optimization
- `ui` — Vaadin UI/UX improvements
- `data-integration` — External API or data pipeline issues
- `documentation` — Docs updates needed
```

## Real-World Example: Daily Security Scan

```markdown
---
on:
  schedule: "0 8 * * 1-5"
timeout-minutes: 15
permissions:
  contents: read
  security-events: read
  issues: read
tools:
  github:
    toolsets: [repos, security, issues, code_search]
safe-outputs:
  create-issue:
    title-prefix: "[security-scan] "
    labels: [security, automated]
    max: 1
    close-older-issues: true
---

# Daily Security Scan Report

Analyze repository security posture and create a summary.

## Instructions
1. Check code scanning alerts (CodeQL, Dependabot)
2. Review dependency vulnerabilities
3. Assess OWASP Top 10 exposure
4. Create a prioritized issue with findings and remediation steps
5. Reference ISO 27001, NIST CSF controls where applicable
```

## Orchestration Patterns

### Single Agent (Simple)
One workflow, one task. Best for focused automation.

### Handoff Pattern
Agent A completes work, creates artifact → Agent B picks up and continues.

### Reflection Pattern
Agent produces output → same or different agent reviews it → iterates until quality threshold met.

### Plan-Execute Pattern (Most Powerful)
1. **Plan**: `/plan` command decomposes issue into sub-tasks
2. **Execute**: Copilot Coding Agent works on each sub-task
3. **Review**: PR review workflow validates changes
4. **Merge**: Human approves and merges

## Labels and Organization

```yaml
safe-outputs:
  add-labels:
    allowed: [agentic-workflow, automated, needs-review]
```

Use consistent label prefixes to track agentic work across the repository.

## CLI Quick Reference

| Command | Description |
|---------|-------------|
| `gh aw compile` | Compile all .md workflows to .lock.yml |
| `gh aw compile <file>` | Compile specific workflow |
| `gh aw list` | List all workflows and their status |
| `gh aw validate` | Validate workflow syntax |
| `gh aw add-wizard <url>` | Add workflow from gallery |
| `gh aw run <workflow>` | Trigger workflow manually |
| `gh aw logs <run-id>` | View workflow run logs |

## Testing Workflows

1. **Validate syntax**: `gh aw validate .github/workflows/my-workflow.md`
2. **Compile**: `gh aw compile .github/workflows/my-workflow.md`
3. **Dry run**: Trigger with `workflow_dispatch` on a test branch
4. **Monitor**: Check Actions tab for run status and logs
5. **Review outputs**: Verify safe output constraints were respected
6. **Iterate**: Refine instructions based on agent behavior

## Integration with Hack23 ISMS

### Applicable Controls

| Control | Implementation |
|---------|---------------|
| **ISO 27001 A.8.8** | Change management via PR review of workflow changes |
| **ISO 27001 A.8.15** | Logging via GitHub Actions audit logs |
| **ISO 27001 A.9.4.1** | Access restriction via permissions and safe outputs |
| **NIST CSF PR.AC-4** | Least privilege via read-only agent permissions |
| **CIS Control 2.7** | Privileged access management via safe output jobs |
| **NIST CSF DE.CM** | Continuous monitoring via scheduled security workflows |

### Documentation Requirements
- Update `WORKFLOWS.md` when adding/modifying agentic workflows
- Update `SECURITY_ARCHITECTURE.md` if workflows access security data
- Update `THREAT_MODEL.md` for new attack surfaces from agentic automation
- Track workflow costs and success rates in operational reviews

## OWASP Agentic Security (Top 10 2026)

| Risk | Mitigation in gh-aw |
|------|---------------------|
| **AG01 - Prompt Injection** | Read-only tokens + threat detection scan |
| **AG02 - Tool Misuse** | Scoped toolsets, no write permissions in agent |
| **AG03 - Insecure Output** | Safe outputs with hard limits and prefix constraints |
| **AG04 - Data Exfiltration** | AWF firewall with domain allowlist |
| **AG05 - Excessive Permissions** | Least-privilege permissions model |
| **AG06 - Memory Poisoning** | Stateless per-run execution, no persistent memory |
| **AG07 - Supply Chain** | Pinned action versions, locked dependencies |
| **AG08 - Credential Theft** | Zero secrets in agent process |
| **AG09 - Denial of Service** | Timeout limits, max output constraints |
| **AG10 - Insufficient Monitoring** | Actions audit logs, workflow health monitoring |

## References

- **Official docs**: https://github.github.com/gh-aw/
- **Abridged docs**: https://github.github.com/gh-aw/llms-small.txt
- **Full docs**: https://github.github.com/gh-aw/llms-full.txt
- **Blog series**: https://github.github.com/gh-aw/_llms-txt/agentic-workflows.txt
- **Quick Start**: https://github.github.com/gh-aw/setup/quick-start/
- **Creating Workflows**: https://github.github.com/gh-aw/setup/creating-workflows/
- **Safe Outputs Reference**: https://github.github.com/gh-aw/reference/safe-outputs/
- **Security Architecture**: https://github.github.com/gh-aw/introduction/architecture/
- **Engines Reference**: https://github.github.com/gh-aw/reference/engines/
- **GitHub Blog**: https://github.blog/ai-and-ml/automate-repository-tasks-with-github-agentic-workflows/
- **OWASP Agentic Top 10**: https://genai.owasp.org/resource/owasp-top-10-for-agentic-applications-for-2026/
- **Hack23 Secure Development Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md
