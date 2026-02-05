# Maven Caching Strategy for GitHub Actions

## Overview

This document describes the optimized Maven caching strategy implemented across all GitHub Actions workflows in the Citizen Intelligence Agency (CIA) project. The strategy is designed to minimize Maven dependency download times, improve workflow resilience, and ensure consistent builds.

## Cache Architecture

### Cache Structure

We use GitHub Actions `actions/cache@v5` to cache Maven artifacts across workflow runs:

```yaml
- name: Cache Maven dependencies
  uses: actions/cache@cdf6c1fa76f9f475f3d7449005a359c84ca0f306 # v5.0.3
  with:
    path: |
      ~/.m2/repository
      ~/.m2/wrapper
      ~/.sonar/cache
    key: ${{ runner.os }}-maven-3.9.9-${{ hashFiles('**/pom.xml', '.mvn/**') }}
    restore-keys: |
      ${{ runner.os }}-maven-3.9.9-${{ hashFiles('**/pom.xml') }}
      ${{ runner.os }}-maven-3.9.9-
      ${{ runner.os }}-maven-
```

### Cached Directories

1. **`~/.m2/repository`** - Maven local repository containing downloaded artifacts
2. **`~/.m2/wrapper`** - Maven wrapper distribution (if used)
3. **`~/.sonar/cache`** - SonarCloud/SonarQube analysis cache

## Cache Key Strategy

### Primary Cache Key

```
${{ runner.os }}-maven-3.9.9-${{ hashFiles('**/pom.xml', '.mvn/**') }}
```

**Components:**
- `${{ runner.os }}` - Platform-specific (Linux, macOS, Windows)
- `maven-3.9.9` - Maven version for isolation
- `${{ hashFiles('**/pom.xml', '.mvn/**') }}` - Hash of all POM files and Maven config

**Benefits:**
- Exact match when no POM files change = instant cache restore
- Maven version in key prevents conflicts between Maven versions
- Platform-specific to avoid cross-platform compatibility issues

### Restore Keys (Fallback Hierarchy)

Cache restoration follows a hierarchical fallback strategy:

1. **Level 1: Exact POM match**
   ```
   ${{ runner.os }}-maven-3.9.9-${{ hashFiles('**/pom.xml') }}
   ```
   Restores cache when most POM files match (excludes .mvn changes)

2. **Level 2: Maven version match**
   ```
   ${{ runner.os }}-maven-3.9.9-
   ```
   Restores any cache from same Maven version (allows POM differences)

3. **Level 3: Any Maven cache**
   ```
   ${{ runner.os }}-maven-
   ```
   Restores any Maven cache as last resort

**Benefits:**
- **Resilience:** Always finds some cache, even if POMs changed
- **Speed:** Partial cache hit is faster than no cache
- **Flexibility:** Gracefully handles dependency updates

## Maven Configuration for Resilience

### Settings.xml Configuration

Each workflow configures Maven with optimized settings for resilience:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
          https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <mirrors>
    <!-- Primary: Maven Central via HTTPS -->
    <mirror>
      <id>central-secure</id>
      <url>https://repo1.maven.org/maven2</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
  </mirrors>
  <profiles>
    <profile>
      <id>github-retry</id>
      <properties>
        <!-- Increase retry count and timeout for better resilience -->
        <maven.wagon.http.retryHandler.count>3</maven.wagon.http.retryHandler.count>
        <maven.wagon.httpconnectionManager.ttlSeconds>120</maven.wagon.httpconnectionManager.ttlSeconds>
        <maven.wagon.http.pool>true</maven.wagon.http.pool>
      </properties>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>github-retry</activeProfile>
  </activeProfiles>
</settings>
```

### Resilience Features

1. **Retry Mechanism**
   - `maven.wagon.http.retryHandler.count=3` - Retry failed downloads 3 times
   - Handles transient network failures automatically

2. **Connection Pooling**
   - `maven.wagon.http.pool=true` - Reuse HTTP connections
   - Reduces overhead of establishing new connections

3. **Connection Timeout**
   - `maven.wagon.httpconnectionManager.ttlSeconds=120` - Keep connections alive
   - Balances connection reuse with resource cleanup

4. **Secure Mirror**
   - Uses HTTPS for Maven Central (`https://repo1.maven.org/maven2`)
   - Ensures secure artifact downloads

## Implementation Across Workflows

## Maven Configuration Strategy

### Command-Line Arguments Instead of settings.xml

Maven configuration is now passed via command-line arguments instead of creating a settings.xml file. This approach:
- **Keeps configuration visible** in workflow files
- **Eliminates external file dependencies** 
- **Simplifies troubleshooting** and maintenance
- **Reduces workflow complexity**

### Retry and Connection Pooling Configuration

All Maven commands include these arguments for resilience:

```bash
-Dmaven.wagon.http.retryHandler.count=3 \
-Dmaven.wagon.httpconnectionManager.ttlSeconds=120 \
-Dmaven.wagon.http.pool=true
```

**Benefits:**
- **3 automatic retries** on failed downloads
- **120-second connection TTL** for connection reuse
- **Connection pooling enabled** for efficiency

### Maven Repository Configuration

All Maven repositories are centralized in `parent-pom/pom.xml`:

1. **vaadin.addons** - https://maven.vaadin.com/vaadin-addons/
   - Vaadin add-on components
   
2. **mulesoft** - https://repository.mulesoft.org/nexus/content/repositories/public/
   - MuleSoft artifacts (under review for removal)
   
3. **hack23.ciamodified** - https://raw.githubusercontent.com/Hack23/ciamavenrepo/main/
   - Custom/modified CIA project artifacts

**Note:** The redundant sonatype repository has been removed from citizen-intelligence-agency/pom.xml as it duplicates Maven Central functionality.

### Workflows with Maven Caching

All workflows that use Maven have the optimized caching strategy:

1. **copilot-setup-steps.yml** - Copilot agent environment setup
2. **release.yml** - Release builds and artifact generation
3. **codeql-analysis.yml** - Security scanning with CodeQL
4. **javadoc-generation.yml** - Javadoc documentation generation
5. **site-generation.yml** - Complete Maven site generation

### Key Implementation Details

1. **Removed Duplicate Caching**
   - Disabled `setup-java` built-in cache (`cache: ""`)
   - Use single optimized `actions/cache` configuration
   - Prevents cache key conflicts

2. **Consistent Configuration**
   - All workflows use identical cache configuration
   - Same cache keys across workflows = maximum reuse
   - Shared cache between workflows on same platform

3. **Maven Settings Injection**
   - Settings configured before build in each workflow
   - Ensures retry and resilience features are active
   - No persistent changes to repository

## Cache Behavior

### Cache Hit Scenarios

| Scenario | Cache Key Match | Result | Download Time |
|----------|----------------|--------|---------------|
| No changes | Exact key match | Full cache restored | ~10 seconds |
| Minor POM change | Restore key Level 1 | Most dependencies cached | ~30 seconds |
| Major POM change | Restore key Level 2 | Base dependencies cached | ~60 seconds |
| New Maven version | Restore key Level 3 | Minimal cache benefit | ~120 seconds |
| First run | No match | Full download | ~180 seconds |

### Cache Size Considerations

- **Typical cache size:** 500 MB - 2 GB
- **GitHub cache limit:** 10 GB per repository
- **Cache retention:** 7 days unused, max 90 days
- **Cache eviction:** Least recently used (LRU) when limit reached

### Best Practices

1. **Cache Invalidation**
   - Cache automatically invalidates when POMs change
   - No manual cache clearing needed
   - Ensures builds always use correct dependencies

2. **Parallel Workflow Runs**
   - Cache is safely shared between concurrent runs
   - No risk of corruption or conflicts
   - Maven handles concurrent access to local repository

3. **Monitoring Cache Effectiveness**
   - Check workflow logs for "Cache hit" or "Cache miss"
   - Monitor total workflow duration trends
   - Expect 50-80% reduction in build time with warm cache

## Troubleshooting

### Cache Not Restoring

**Symptoms:** Every build downloads all dependencies

**Solutions:**
1. Check if POM files changed (forces new cache key)
2. Verify cache size hasn't exceeded 10 GB limit
3. Ensure cache retention hasn't expired (7 days unused)
4. Check GitHub Actions cache storage page

### Slow Builds Despite Cache

**Symptoms:** Cache restores but build still slow

**Solutions:**
1. Check if cache is partial (restore key match, not exact key)
2. Verify network connectivity to Maven repositories
3. Check for large artifacts being downloaded (not in cache)
4. Review Maven output for download activity

### Build Failures After Cache Restore

**Symptoms:** Build fails with dependency resolution errors

**Solutions:**
1. Clear cache by changing cache key (add `-v2` suffix)
2. Verify POM file changes are syntactically correct
3. Check if dependencies were removed from remote repositories
4. Try manual cache invalidation via GitHub Actions UI

## Performance Metrics

### Expected Improvements

| Metric | Without Cache | With Cache (Warm) | Improvement |
|--------|--------------|-------------------|-------------|
| Dependency download | 120-180s | 10-30s | 75-85% |
| Total workflow time | 15-20 min | 8-12 min | 40-50% |
| Network bandwidth | 1-2 GB | 50-200 MB | 90% |
| Cache hit rate | 0% | 70-85% | - |

### Monitoring

Monitor these metrics to track caching effectiveness:

1. **Cache Hit Rate**
   - View in workflow logs: "Cache restored from key: ..."
   - Target: 70-85% across all runs

2. **Build Duration**
   - Track median build time per workflow
   - Compare with historical data

3. **Network Usage**
   - Monitor GitHub Actions network egress
   - Should see significant reduction

## Maintenance

### Regular Tasks

1. **Monthly Review**
   - Check cache effectiveness in workflow analytics
   - Review cache size trends
   - Verify no cache bloat

2. **Version Updates**
   - Update cache key when Maven version changes
   - Test cache restoration after updates
   - Document any breaking changes

3. **Configuration Updates**
   - Keep retry settings optimized for GitHub Actions
   - Monitor Maven repository availability
   - Update mirrors if needed

## Security Considerations

1. **HTTPS Enforcement**
   - All Maven repositories use HTTPS
   - Prevents man-in-the-middle attacks
   - Ensures artifact integrity

2. **Cache Isolation**
   - Separate caches per platform (OS)
   - Separate caches per Maven version
   - Prevents cross-contamination

3. **Dependency Verification**
   - Maven verifies checksums automatically
   - Cached artifacts are validated
   - No security compromise from caching

## References

- [GitHub Actions Cache Documentation](https://docs.github.com/en/actions/using-workflows/caching-dependencies-to-speed-up-workflows)
- [Maven Settings Reference](https://maven.apache.org/settings.html)
- [Maven Wagon HTTP Configuration](https://maven.apache.org/wagon/wagon-providers/wagon-http/)
- [GitHub Actions Cache Limits](https://docs.github.com/en/actions/using-workflows/caching-dependencies-to-speed-up-workflows#usage-limits-and-eviction-policy)

## Changelog

### 2026-02-05 - Configuration Refactoring
- Replaced settings.xml creation with Maven CLI arguments
- Removed settings.xml step from all 5 workflows
- Pass retry configuration as command-line arguments:
  * `-Dmaven.wagon.http.retryHandler.count=3`
  * `-Dmaven.wagon.httpconnectionManager.ttlSeconds=120`
  * `-Dmaven.wagon.http.pool=true`
- Consolidated Maven repositories in parent-pom/pom.xml
- Removed redundant sonatype repository from citizen-intelligence-agency/pom.xml
- Added TODO for mulesoft repository review

### 2026-02-05 - Initial Implementation
- Implemented optimized Maven caching strategy
- Added hierarchical restore keys for better cache hits
- Configured Maven retry mechanism for resilience
- Removed duplicate cache configurations
- Added comprehensive documentation
- Applied to copilot-setup-steps.yml, release.yml, codeql-analysis.yml, javadoc-generation.yml, site-generation.yml

---

**Maintained by:** Hack23 DevOps Team  
**Last Updated:** 2026-02-05  
**Review Schedule:** Monthly
