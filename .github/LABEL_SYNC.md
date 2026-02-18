# Label Synchronization Documentation

## Overview

The CIA project uses labels for PR categorization, release notes generation, and automated workflow triggers. To maintain consistency, labels must be synchronized across multiple configuration files.

## Label Configuration Files

### 1. `.github/labeler.yml`
**Purpose:** Automatically applies labels to PRs based on file paths changed.

**Usage:**
- Applied by the `.github/workflows/labeler.yml` workflow
- Runs on every PR
- Uses glob patterns to match file paths

### 2. `.github/release-drafter.yml`
**Purpose:** Generates release notes by categorizing changes using labels.

**Usage:**
- Applied by the `.github/workflows/release.yml` workflow during releases
- Groups PRs into categories based on labels
- Includes autolabeler rules for commit message patterns

## Current Label Set

As of the last sync verification, **19 labels** are defined and synchronized:

### Feature Categories
- `feature` - New features
- `enhancement` - Improvements to existing features

### Political Analysis
- `political-analysis` - Political analysis components
- `party-data` - Party-related data
- `committee` - Committee-related changes
- `government` - Government/ministry data

### Analytics & Data
- `analytics` - Analytics features
- `visualization` - Data visualization
- `database` - Database schema or queries

### UI/UX
- `ui` - User interface changes

### Infrastructure
- `infrastructure` - Infrastructure and deployment
- `performance` - Performance improvements
- `build` - Build system changes

### Code Quality
- `refactor` - Code refactoring
- `testing` - Testing improvements

### Security
- `security` - Security-related changes

### Documentation
- `documentation` - Documentation updates

### Dependencies
- `dependencies` - Dependency updates

### Bug Fixes
- `bug` - Bug fixes

## Synchronization Requirements

### Rule 1: Label Definitions Must Match
All labels defined in `labeler.yml` must have corresponding categories in `release-drafter.yml`.

### Rule 2: Autolabeler Patterns
The `autolabeler` section in `release-drafter.yml` provides additional label assignment based on PR title patterns (conventional commits).

### Rule 3: Version Resolver Labels
Special labels used for semantic versioning:
- `major`, `breaking` → Major version bump
- `feature`, `enhancement`, `political-analysis` → Minor version bump
- `bug`, `security`, `dependencies` → Patch version bump

## Verification Script

To verify label synchronization, use:

```bash
# Extract labels from release-drafter.yml
grep -A 100 "categories:" .github/release-drafter.yml | \
  grep "      - '" | sed "s/.*'\(.*\)'/\1/" | sort | uniq

# Extract labels from labeler.yml
grep "^[a-z-]*:" .github/labeler.yml | sed 's/://' | sort | uniq
```

Both commands should produce the same list (excluding special labels like `major`, `breaking`, `skip-changelog`).

## Adding New Labels

When adding a new label, follow these steps:

1. **Define in `labeler.yml`:**
   ```yaml
   new-label:
   - any:
     - changed-files:
       - any-glob-to-any-file:
         - 'path/to/**'
   ```

2. **Add to `release-drafter.yml` categories:**
   ```yaml
   - title: '📝 New Category'
     labels:
       - 'new-label'
   ```

3. **Optional: Add autolabeler rule:**
   ```yaml
   - label: 'new-label'
     title:
       - '/^new(\([^)]+\))?:/i'
   ```

4. **Verify synchronization** using the verification script above

5. **Document** the new label in this file

## Maintenance

- **Owner:** DevOps team
- **Review Frequency:** Every release
- **Last Verified:** 2026-02-18
- **Verification Status:** ✅ All 19 labels synchronized

## Related Workflows

- `.github/workflows/labeler.yml` - Applies labels to PRs
- `.github/workflows/release.yml` - Uses labels for release notes
- `.github/workflows/release-drafter.yml` (if exists) - Draft release configuration

## References

- [GitHub Labeler Action](https://github.com/actions/labeler)
- [Release Drafter](https://github.com/release-drafter/release-drafter)
- [Conventional Commits](https://www.conventionalcommits.org/)
