# Creating GitHub Issues for Next Minor Release

## ⚠️ Authentication Required

GitHub Copilot cannot create issues directly due to authentication limitations in this environment. The GitHub MCP server and CLI both require authentication tokens that are not accessible.

## ✅ Solution: Use the Provided Script

A shell script `create_issues.sh` has been created that will create all 5 issues automatically.

### Prerequisites

1. **GitHub CLI installed** (already available in this environment)
2. **GitHub authentication** (requires one-time setup)

### Steps to Create the Issues

#### Step 1: Authenticate GitHub CLI

Run the following command and follow the prompts:

```bash
gh auth login
```

Select:
- GitHub.com
- HTTPS
- Yes (to authenticate Git with your GitHub credentials)
- Login with a web browser (recommended) or paste an authentication token

#### Step 2: Run the Script

```bash
cd /home/runner/work/cia/cia
./create_issues.sh
```

This will create all 5 issues in sequence and display their URLs.

### Expected Output

```
Creating 5 GitHub issues for next minor release...
Repository: Hack23/cia

Creating Issue #1: Spring Framework Upgrade...
✅ Created: https://github.com/Hack23/cia/issues/XXXX

Creating Issue #2: Test Coverage Improvement...
✅ Created: https://github.com/Hack23/cia/issues/XXXX

Creating Issue #3: Database Performance Optimization...
✅ Created: https://github.com/Hack23/cia/issues/XXXX

Creating Issue #4: Documentation Update...
✅ Created: https://github.com/Hack23/cia/issues/XXXX

Creating Issue #5: Extended Drools Rules...
✅ Created: https://github.com/Hack23/cia/issues/XXXX

==========================================
✅ Successfully created 5 GitHub issues!
==========================================

Issue URLs:
1. https://github.com/Hack23/cia/issues/XXXX
2. https://github.com/Hack23/cia/issues/XXXX
3. https://github.com/Hack23/cia/issues/XXXX
4. https://github.com/Hack23/cia/issues/XXXX
5. https://github.com/Hack23/cia/issues/XXXX

Total estimated effort: 32-36 hours (4-5 days)
```

## Alternative: Manual Creation

If you prefer to create issues manually or the script doesn't work:

1. Go to https://github.com/Hack23/cia/issues/new
2. Open `NEXT_RELEASE_ISSUES.md` in the repository
3. For each of the 5 issues:
   - Copy the title (e.g., "🔐 Upgrade Spring Framework to 5.3.42 for Security Patches")
   - Copy the entire issue body (everything under that issue's section)
   - Add the labels specified in the Metadata section
   - Click "Submit new issue"

## What's in Each Issue

### Issue #1: 🔐 Spring Framework Upgrade (Critical Priority)
- **Effort:** 2-4 hours
- **Impact:** Security compliance, vulnerability mitigation
- Upgrade from Spring 5.3.39 to 5.3.42 (final EOL release)
- Affects all 49 Maven modules

### Issue #2: ✅ Test Coverage Improvement (High Priority)
- **Effort:** 6-8 hours
- **Impact:** Code quality, maintainability
- Target 80%+ coverage in core service modules
- Add unit and integration tests

### Issue #3: ⚡ Database Performance (High Priority)
- **Effort:** 6-8 hours
- **Impact:** 50% query reduction target
- Enable Hibernate second-level caching
- Eliminate N+1 queries

### Issue #4: 📝 Documentation Update (Medium Priority)
- **Effort:** 3-4 hours
- **Impact:** Developer onboarding
- Java 25 setup guide
- Create TROUBLESHOOTING.md

### Issue #5: 🔍 Extended Drools Rules (Medium Priority)
- **Effort:** 6-8 hours
- **Impact:** Enhanced political analysis
- Implement 15 compliance rules from #6885
- Detect political anomalies

## Why Authentication is Required

GitHub API and CLI require authentication to:
1. **Prevent spam and abuse** - Unauthenticated API access is very limited
2. **Associate issues with users** - Issues need an author
3. **Enforce repository permissions** - Only authorized users can create issues

The GitHub MCP server used by Copilot requires a personal access token (PAT) with `repo` scope to be set in the workflow configuration. This token is not available in the Copilot agent's runtime environment.

## Troubleshooting

### "gh: command not found"
Install GitHub CLI:
```bash
# On Ubuntu/Debian
sudo apt install gh

# On macOS
brew install gh
```

### "Authentication failed"
Make sure you've run `gh auth login` and completed the authentication flow.

### "Permission denied"
Make sure you have write access to the Hack23/cia repository.

### Script fails with error
Check the error message. Common issues:
- Network connectivity
- GitHub API rate limiting
- Invalid authentication token

## Questions?

If you encounter any issues creating these GitHub issues, please:
1. Check the error messages carefully
2. Verify your GitHub authentication is working: `gh auth status`
3. Try creating one issue manually to test your permissions
4. Contact the repository maintainers if problems persist
