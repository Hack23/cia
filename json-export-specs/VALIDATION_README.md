# JSON Schema Validation Tool

## Overview

This tool validates the 5 JSON export schemas against 142 real CSV sample data files to ensure correctness and identify mismatches between schema definitions and actual database structure.

## Purpose

The JSON export schemas define the structure for:
- Politician profiles
- Party analytics
- Committee data
- Ministry information
- Intelligence products

These schemas must accurately reflect the actual database structure to ensure correct data export and API functionality.

## Usage

### Local Execution

Run the validation script from the `json-export-specs` directory:

```bash
cd json-export-specs
python3 validate_schemas.py
```

The script will:
1. Load all 5 JSON schema markdown files
2. Analyze 142 CSV sample data files
3. Compare schema field definitions with actual data columns
4. Generate validation reports

### Output Files

**Markdown Report:** `schemas/SCHEMA_VALIDATION_REPORT.md`
- Detailed human-readable validation report
- Field mismatch details
- Recommendations for schema updates

**JSON Results:** `schemas/validation-results.json`
- Machine-readable validation results
- Structured data for programmatic analysis
- Used by CI/CD pipeline

### CI/CD Integration

The validation runs automatically via GitHub Actions:

**Triggers:**
- Push to schema files (`json-export-specs/schemas/*.md`)
- Push to validation script (`json-export-specs/validate_schemas.py`)
- Push to sample data (`service.data.impl/sample-data/*.csv`)
- Pull requests affecting these files
- Daily at 02:00 UTC (scheduled)
- Manual workflow dispatch

**Workflow:** `.github/workflows/validate-json-schemas.yml`

**Artifacts:**
- Validation report (Markdown)
- Validation results (JSON)
- Console output

## Validation Methodology

### Field Matching

The tool compares schema field names with database column names using flexible matching:
- Exact match
- camelCase → snake_case conversion
- Case-insensitive comparison

### Schema to View Mapping

Each schema is mapped to relevant database views:

| Schema | Database Views |
|--------|----------------|
| **Politician** | `view_riksdagen_politician`, `view_riksdagen_politician_summary`, `view_riksdagen_politician_document`, `view_riksdagen_politician_ballot` |
| **Party** | `view_riksdagen_party`, `view_riksdagen_party_summary`, `view_riksdagen_party_document_summary`, `view_riksdagen_party_ballot` |
| **Committee** | `view_riksdagen_committee`, `view_riksdagen_committee_decisions`, `view_riksdagen_committee_roles`, `view_riksdagen_committee_ballot_decision` |
| **Ministry** | `view_riksdagen_goverment`, `view_riksdagen_goverment_roles`, `view_ministry_decision_impact`, `view_ministry_effectiveness_trends` |
| **Intelligence** | `view_politician_risk_summary`, `view_party_performance_metrics`, `view_decision_temporal_trends`, `view_committee_productivity` |

### Data Type Inference

The tool infers column data types from sample data:
- **Boolean**: `true/false`, `t/f`, `0/1`
- **Integer**: Numeric values without decimals
- **Float**: Numeric values with decimals
- **Date**: Columns with date/time keywords
- **String**: All other values

## Validation Results

### Current Status (Latest Run)

**Schemas Validated:** 5
**Sample Files Analyzed:** 132 of 142
**Total Field Mismatches:** 126

| Schema | Fields Defined | Views Matched | Field Mismatches | Status |
|--------|----------------|---------------|------------------|--------|
| Politician | 46 | 7 | 35 | ⚠️ REVIEW |
| Party | 43 | 11 | 40 | ⚠️ REVIEW |
| Committee | 26 | 10 | 24 | ⚠️ REVIEW |
| Ministry | 29 | 6 | 27 | ⚠️ REVIEW |
| Intelligence | 0 | 4 | 0 | ✅ PASS |

### Common Issues Found

1. **Schema fields not found in data**
   - Abstract/aggregated fields defined in schema (e.g., `riskLevel`, `trendDirection`)
   - These may be computed fields not present in raw database views

2. **Data columns not in schema**
   - Database columns that should be included in JSON export
   - Raw data fields that may be useful for API consumers

3. **Missing database views**
   - Views referenced in schema but not available in sample data
   - May indicate outdated documentation or missing data extraction

## Addressing Validation Issues

### For Schema Developers

1. **Review Field Mismatches**
   - Check if field names match database column naming convention
   - Verify computed fields are properly documented
   - Update schema documentation to reflect actual structure

2. **Add Missing Fields**
   - Identify important data columns not covered by schema
   - Add relevant fields to schema definition
   - Document field source (database column or computed)

3. **Document Field Mappings**
   - Create explicit mapping: JSON field → database column
   - Note any transformations or calculations
   - Document data sources for computed fields

4. **Update Examples**
   - Ensure JSON examples use actual field names
   - Verify example data matches real data structure
   - Test examples against sample data

### For Database Developers

1. **Maintain Sample Data**
   - Keep sample data up to date with schema changes
   - Ensure all relevant views are included in extraction
   - Verify sample data represents production structure

2. **Document View Changes**
   - Update schema documentation when views change
   - Communicate column additions/removals to schema team
   - Maintain view-to-schema mapping documentation

## Continuous Improvement

### Integration into Development Workflow

1. **Pre-commit Validation**
   - Run validation before committing schema changes
   - Ensure changes don't introduce new mismatches
   - Update documentation with findings

2. **Pull Request Checks**
   - Automatic validation on PR creation
   - Review validation report in PR comments
   - Address issues before merge

3. **Daily Monitoring**
   - Scheduled validation runs daily
   - Automatic issue creation on failures
   - Track validation trends over time

### Metrics and Goals

**Target Metrics:**
- Zero field mismatches for production schemas
- 100% coverage of database columns in schemas
- All referenced views available in sample data

**Quality Gates:**
- Validation must pass before schema release
- No new mismatches introduced in PRs
- Missing views resolved within 1 sprint

## Related Documentation

- [JSON Export Specs README](../README.md) - Overview of JSON export system
- [Politician Schema](schemas/politician-schema.md) - Politician profile specification
- [Party Schema](schemas/party-schema.md) - Party analytics specification
- [Committee Schema](schemas/committee-schema.md) - Committee data specification
- [Ministry Schema](schemas/ministry-schema.md) - Ministry information specification
- [Intelligence Schema](schemas/intelligence-schema.md) - Intelligence products specification
- [Database View Catalog](../../DATABASE_VIEW_INTELLIGENCE_CATALOG.md) - Complete view documentation

## Support

For issues or questions about schema validation:

1. Review the [validation report](schemas/SCHEMA_VALIDATION_REPORT.md)
2. Check the [validation results JSON](schemas/validation-results.json)
3. Consult the [GitHub Actions workflow](.github/workflows/validate-json-schemas.yml)
4. Create an issue with the `schema-validation` label

## License

Apache License 2.0 - See [LICENSE.txt](../LICENSE.txt)
