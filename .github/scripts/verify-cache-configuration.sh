#!/bin/bash
# Verification script for CI/CD cache configuration
# This script validates that all workflow files have proper caching configured

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
WORKFLOW_DIR="${SCRIPT_DIR}/../workflows"
REQUIRED_CACHE_VERSION="actions/cache@9255dc7a253b0ccc959486e2bca901246202afeb"

echo "=== Cache Configuration Verification ==="
echo ""

# Color codes
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Check if workflow directory exists
if [ ! -d "$WORKFLOW_DIR" ]; then
    echo -e "${RED}✗ Workflow directory not found: $WORKFLOW_DIR${NC}"
    exit 1
fi

# Workflows that should have APT cache
WORKFLOWS_WITH_APT=(
    "release.yml"
    "codeql-analysis.yml"
    "copilot-setup-steps.yml"
)

# Workflows that should have Maven cache
WORKFLOWS_WITH_MAVEN=(
    "release.yml"
    "codeql-analysis.yml"
    "copilot-setup-steps.yml"
)

echo "Checking APT cache configuration..."
echo ""

apt_cache_found=0
apt_cache_missing=0

for workflow in "${WORKFLOWS_WITH_APT[@]}"; do
    workflow_path="${WORKFLOW_DIR}/${workflow}"
    
    if [ ! -f "$workflow_path" ]; then
        echo -e "${RED}✗ Workflow not found: $workflow${NC}"
        apt_cache_missing=$((apt_cache_missing + 1))
        continue
    fi
    
    # Check if APT cache is configured
    if grep -q "Cache APT packages" "$workflow_path"; then
        echo -e "${GREEN}✓ $workflow - APT cache configured${NC}"
        
        # Verify cache version
        if grep -q "$REQUIRED_CACHE_VERSION" "$workflow_path"; then
            echo -e "  ${GREEN}✓ Using correct cache version${NC}"
        else
            echo -e "  ${YELLOW}⚠ Warning: Not using required cache version${NC}"
        fi
        
        # Verify cache path
        if grep -q "/var/cache/apt/archives" "$workflow_path"; then
            echo -e "  ${GREEN}✓ Correct cache path configured${NC}"
        else
            echo -e "  ${RED}✗ Missing or incorrect cache path${NC}"
        fi
        
        # Verify cache key
        if grep -q "runner.os.*apt.*hashFiles" "$workflow_path"; then
            echo -e "  ${GREEN}✓ Dynamic cache key configured${NC}"
        else
            echo -e "  ${YELLOW}⚠ Warning: Static cache key or missing hashFiles${NC}"
        fi
        
        # Verify restore keys
        if grep -q "restore-keys:" "$workflow_path"; then
            echo -e "  ${GREEN}✓ Restore keys configured${NC}"
        else
            echo -e "  ${YELLOW}⚠ Warning: No restore keys configured${NC}"
        fi
        
        apt_cache_found=$((apt_cache_found + 1))
    else
        echo -e "${RED}✗ $workflow - APT cache NOT configured${NC}"
        apt_cache_missing=$((apt_cache_missing + 1))
    fi
    echo ""
done

echo ""
echo "Checking Maven cache configuration..."
echo ""

maven_cache_found=0
maven_cache_missing=0

for workflow in "${WORKFLOWS_WITH_MAVEN[@]}"; do
    workflow_path="${WORKFLOW_DIR}/${workflow}"
    
    if [ ! -f "$workflow_path" ]; then
        echo -e "${RED}✗ Workflow not found: $workflow${NC}"
        maven_cache_missing=$((maven_cache_missing + 1))
        continue
    fi
    
    # Check if Maven cache is configured (either via setup-java or explicit cache)
    has_setup_java_cache=false
    has_explicit_cache=false
    
    if grep -q "cache: 'maven'" "$workflow_path" || grep -q 'cache: "maven"' "$workflow_path"; then
        has_setup_java_cache=true
    fi
    
    if grep -q "Cache Maven and Sonar artifacts" "$workflow_path" || grep -q "Cache Maven" "$workflow_path"; then
        has_explicit_cache=true
    fi
    
    if [ "$has_setup_java_cache" = true ] || [ "$has_explicit_cache" = true ]; then
        echo -e "${GREEN}✓ $workflow - Maven cache configured${NC}"
        
        if [ "$has_setup_java_cache" = true ]; then
            echo -e "  ${GREEN}✓ setup-java Maven cache enabled${NC}"
        fi
        
        if [ "$has_explicit_cache" = true ]; then
            echo -e "  ${GREEN}✓ Explicit Maven cache configured${NC}"
            
            # Verify ~/.m2/repository is cached
            if grep -q "~/.m2/repository" "$workflow_path"; then
                echo -e "  ${GREEN}✓ Maven repository cache configured${NC}"
            fi
            
            # Verify Sonar cache is included
            if grep -q "~/.sonar/cache" "$workflow_path"; then
                echo -e "  ${GREEN}✓ Sonar cache configured${NC}"
            fi
        fi
        
        maven_cache_found=$((maven_cache_found + 1))
    else
        echo -e "${YELLOW}⚠ $workflow - Maven cache not explicitly configured${NC}"
        echo -e "  Note: May rely on setup-java built-in cache"
        maven_cache_missing=$((maven_cache_missing + 1))
    fi
    echo ""
done

echo ""
echo "=== Verification Summary ==="
echo ""
echo "APT Cache:"
echo -e "  Configured: ${GREEN}$apt_cache_found${NC} workflows"
echo -e "  Missing: ${RED}$apt_cache_missing${NC} workflows"
echo ""
echo "Maven Cache:"
echo -e "  Configured: ${GREEN}$maven_cache_found${NC} workflows"
echo -e "  Not explicit: ${YELLOW}$maven_cache_missing${NC} workflows"
echo ""

# Exit with error if any required caches are missing
if [ $apt_cache_missing -gt 0 ]; then
    echo -e "${RED}✗ Some workflows are missing required APT cache configuration${NC}"
    exit 1
fi

echo -e "${GREEN}✓ All required cache configurations are present${NC}"
echo ""
echo "Cache optimization is properly configured!"
exit 0
