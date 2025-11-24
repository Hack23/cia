#!/bin/bash
#
# CIA JSON Export - CDN Deployment Script
# Deploys JSON export files to AWS S3 with CloudFront invalidation
#
# Usage: ./deploy-cdn.sh [environment]
# Environments: dev, staging, prod
#

set -e  # Exit on error
set -u  # Exit on undefined variable

# Configuration
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ENV="${1:-dev}"
VERSION="1.0.0"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)

# Color output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Print functions
print_info() {
    echo -e "${BLUE}ℹ${NC} $1"
}

print_success() {
    echo -e "${GREEN}✓${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}⚠${NC} $1"
}

print_error() {
    echo -e "${RED}✗${NC} $1"
}

print_header() {
    echo ""
    echo -e "${BLUE}═══════════════════════════════════════════════════${NC}"
    echo -e "${BLUE}  $1${NC}"
    echo -e "${BLUE}═══════════════════════════════════════════════════${NC}"
    echo ""
}

# Load environment configuration
load_config() {
    case "$ENV" in
        dev)
            S3_BUCKET="cia-json-export-dev"
            CLOUDFRONT_ID="${CLOUDFRONT_ID_DEV:-}"
            AWS_REGION="eu-north-1"
            if [ -z "$CLOUDFRONT_ID" ]; then
                print_error "CLOUDFRONT_ID_DEV environment variable is not set."
                print_info "Set it with: export CLOUDFRONT_ID_DEV=E1234567890ABC"
                exit 1
            fi
            ;;
        staging)
            S3_BUCKET="cia-json-export-staging"
            CLOUDFRONT_ID="${CLOUDFRONT_ID_STAGING:-}"
            AWS_REGION="eu-north-1"
            if [ -z "$CLOUDFRONT_ID" ]; then
                print_error "CLOUDFRONT_ID_STAGING environment variable is not set."
                print_info "Set it with: export CLOUDFRONT_ID_STAGING=E0987654321XYZ"
                exit 1
            fi
            ;;
        prod)
            S3_BUCKET="cia-json-export"
            CLOUDFRONT_ID="${CLOUDFRONT_ID_PROD:-}"
            AWS_REGION="eu-north-1"
            if [ -z "$CLOUDFRONT_ID" ]; then
                print_error "CLOUDFRONT_ID_PROD environment variable is not set."
                print_info "Set it with: export CLOUDFRONT_ID_PROD=E1122334455DEF"
                exit 1
            fi
            ;;
        *)
            print_error "Unknown environment: $ENV"
            print_info "Valid environments: dev, staging, prod"
            exit 1
            ;;
    esac
    
    print_info "Environment: $ENV"
    print_info "S3 Bucket: $S3_BUCKET"
    print_info "Region: $AWS_REGION"
    print_info "Version: $VERSION"
}

# Check prerequisites
check_prerequisites() {
    print_header "Checking Prerequisites"
    
    # Check AWS CLI
    if ! command -v aws &> /dev/null; then
        print_error "AWS CLI not found. Please install: https://aws.amazon.com/cli/"
        exit 1
    fi
    print_success "AWS CLI found: $(aws --version)"
    
    # Check AWS credentials
    if ! aws sts get-caller-identity &> /dev/null; then
        print_error "AWS credentials not configured. Run: aws configure"
        exit 1
    fi
    print_success "AWS credentials configured"
    
    # Check jq for JSON processing
    if ! command -v jq &> /dev/null; then
        print_warning "jq not found. Install for JSON validation: apt-get install jq"
    else
        print_success "jq found: $(jq --version)"
    fi
}

# Create directory structure
create_directory_structure() {
    print_header "Creating Directory Structure"
    
    local export_dir="$SCRIPT_DIR/export"
    local version_dir="$export_dir/v$VERSION"
    
    # Create directories
    mkdir -p "$version_dir/politicians/profiles"
    mkdir -p "$version_dir/politicians/by-party"
    mkdir -p "$version_dir/politicians/by-committee"
    mkdir -p "$version_dir/parties/profiles"
    mkdir -p "$version_dir/parties/analytics"
    mkdir -p "$version_dir/ministries/profiles"
    mkdir -p "$version_dir/ministries/performance"
    mkdir -p "$version_dir/committees/profiles"
    mkdir -p "$version_dir/committees/productivity"
    mkdir -p "$version_dir/intelligence"
    
    print_success "Directory structure created: $version_dir"
    echo "$version_dir"
}

# Generate JSON files
generate_json_files() {
    print_header "Generating JSON Files"
    
    local export_dir="$1"
    
    print_info "Generating politicians index..."
    # Placeholder - would call Java export service
    # java -jar cia-json-export.jar --entity politicians --output "$export_dir/politicians/index.json"
    
    print_info "Generating parties index..."
    # java -jar cia-json-export.jar --entity parties --output "$export_dir/parties/index.json"
    
    print_info "Generating intelligence products..."
    # java -jar cia-json-export.jar --entity intelligence --output "$export_dir/intelligence/"
    
    # For now, copy examples
    if [ -d "$SCRIPT_DIR/examples" ]; then
        cp -r "$SCRIPT_DIR/examples/"*.json "$export_dir/" || true
        print_success "Copied example files"
    fi
    
    print_success "JSON generation complete"
}

# Validate JSON files
validate_json_files() {
    print_header "Validating JSON Files"
    
    local export_dir="$1"
    local valid=0
    local invalid=0
    
    if ! command -v jq &> /dev/null; then
        print_warning "Skipping validation (jq not installed)"
        return
    fi
    
    while IFS= read -r -d '' file; do
        if jq empty "$file" 2>/dev/null; then
            ((valid++))
        else
            ((invalid++))
            print_error "Invalid JSON: $file"
        fi
    done < <(find "$export_dir" -name "*.json" -type f -print0)
    
    print_success "Valid JSON files: $valid"
    if [ $invalid -gt 0 ]; then
        print_error "Invalid JSON files: $invalid"
        exit 1
    fi
}

# Upload to S3
upload_to_s3() {
    print_header "Uploading to S3"
    
    local export_dir="$1"
    
    print_info "Syncing files to s3://$S3_BUCKET/"
    
    # Upload with appropriate caching headers
    # Note: Using --acl public-read for CDN serving. Modern best practice
    # is to use bucket policies instead of ACLs. Ensure your S3 bucket
    # policy is properly configured for CloudFront access.
    aws s3 sync "$export_dir" "s3://$S3_BUCKET/v$VERSION/" \
        --region "$AWS_REGION" \
        --delete \
        --cache-control "public, max-age=86400" \
        --content-type "application/json" \
        --metadata "version=$VERSION,timestamp=$TIMESTAMP" \
        --acl public-read
    
    print_success "Upload complete"
    
    # Update 'latest' symlink
    print_info "Updating 'latest' pointer..."
    echo "{\"version\": \"$VERSION\", \"updated\": \"$(date -Iseconds)\"}" > /tmp/version.json
    aws s3 cp /tmp/version.json "s3://$S3_BUCKET/latest/version.json" \
        --region "$AWS_REGION" \
        --cache-control "no-cache" \
        --content-type "application/json" \
        --acl public-read
    
    print_success "'latest' pointer updated"
}

# Invalidate CloudFront cache
invalidate_cloudfront() {
    print_header "Invalidating CloudFront Cache"
    
    print_info "Creating invalidation for distribution: $CLOUDFRONT_ID"
    
    local invalidation_id
    invalidation_id=$(aws cloudfront create-invalidation \
        --distribution-id "$CLOUDFRONT_ID" \
        --paths "/*" \
        --query 'Invalidation.Id' \
        --output text)
    
    print_success "Invalidation created: $invalidation_id"
    print_info "Cache invalidation may take 5-10 minutes to complete"
    
    # Optional: Wait for invalidation to complete
    if [ "${WAIT_FOR_INVALIDATION:-false}" == "true" ]; then
        print_info "Waiting for invalidation to complete..."
        aws cloudfront wait invalidation-completed \
            --distribution-id "$CLOUDFRONT_ID" \
            --id "$invalidation_id"
        print_success "Invalidation complete"
    fi
}

# Generate metadata
generate_metadata() {
    print_header "Generating Metadata"
    
    local export_dir="$1"
    
    cat > "$export_dir/metadata.json" <<EOF
{
  "version": "$VERSION",
  "generated": "$(date -Iseconds)",
  "environment": "$ENV",
  "timestamp": "$TIMESTAMP",
  "s3Bucket": "$S3_BUCKET",
  "cloudFrontUrl": "https://$(aws cloudfront get-distribution --id "$CLOUDFRONT_ID" --query 'Distribution.DomainName' --output text 2>/dev/null || echo 'cdn.cia.se')",
  "files": {
    "politicians": "$(find "$export_dir/politicians" -name "*.json" -type f | wc -l)",
    "parties": "$(find "$export_dir/parties" -name "*.json" -type f | wc -l)",
    "ministries": "$(find "$export_dir/ministries" -name "*.json" -type f | wc -l)",
    "committees": "$(find "$export_dir/committees" -name "*.json" -type f | wc -l)",
    "intelligence": "$(find "$export_dir/intelligence" -name "*.json" -type f | wc -l)"
  }
}
EOF
    
    print_success "Metadata file created"
}

# Health check
health_check() {
    print_header "Health Check"
    
    local cdn_url="https://$(aws cloudfront get-distribution --id "$CLOUDFRONT_ID" --query 'Distribution.DomainName' --output text 2>/dev/null || echo 'cdn.cia.se')"
    
    print_info "Testing CDN endpoint: $cdn_url/v$VERSION/metadata.json"
    
    # Wait a moment for propagation
    sleep 5
    
    if curl -sSf "$cdn_url/v$VERSION/metadata.json" > /dev/null 2>&1; then
        print_success "CDN endpoint is accessible"
    else
        print_warning "CDN endpoint not yet accessible (cache may still be updating)"
    fi
}

# Main deployment flow
main() {
    print_header "CIA JSON Export - CDN Deployment"
    print_info "Starting deployment to $ENV environment"
    
    load_config
    check_prerequisites
    
    local export_dir
    export_dir=$(create_directory_structure)
    
    generate_json_files "$export_dir"
    validate_json_files "$export_dir"
    generate_metadata "$export_dir"
    upload_to_s3 "$export_dir"
    invalidate_cloudfront
    health_check
    
    print_header "Deployment Complete"
    print_success "Version $VERSION deployed to $ENV"
    print_info "CDN URL: https://$(aws cloudfront get-distribution --id "$CLOUDFRONT_ID" --query 'Distribution.DomainName' --output text 2>/dev/null || echo 'cdn.cia.se')/v$VERSION/"
}

# Run main function
main "$@"
