# SQL Scripts Reference

Comprehensive reference for all SQL scripts in the CIA project.

## Overview

The CIA project includes 31 SQL scripts organized into 7 categories for database management, validation, analysis, and data extraction.

## Categories

### 1. Data Extraction (4 scripts)

#### extract-sample-data.sql
**Purpose**: Main data extraction script for generating representative sample datasets  
**Status**: ✅ Active (with known view tracking issue being fixed)  
**Usage**: `./extract-sample-data.sh [output_dir] [database]`  
**Features**:
- Extracts tables and views with smart sampling
- Temporal stratification for time-series data
- Percentile-based distribution analysis
- Generates validation reports
- Timeout protection (120s per query)

#### extract-sample-data-functions.sql  
**Purpose**: Helper functions for sample data extraction  
**Status**: ✅ Active  
**Key Functions**:
- `cia_tmp_rowcount()` - Fast row counting with error handling
- `cia_percentile_sample()` - Percentile-based sampling
- `cia_classify_temporal_view()` - Temporal granularity classification
- `cia_find_temporal_column()` - Automatic temporal column detection

#### extract-percentile-summaries.sql
**Purpose**: Generate percentile distribution summaries (P1-P99) for analytical views  
**Status**: ✅ Active  
**Output**: 24 CSV files with percentile distributions  
**Use Case**: Risk rule calibration, data distribution analysis

#### extract-framework-validation-data.sql
**Purpose**: Extract validation data for framework testing  
**Status**: ✅ Active  
**Output**: Framework validation datasets

### 2. Schema Management (5 scripts)

#### full_schema.sql
**Purpose**: Complete database schema definition  
**Status**: ✅ Active (loads in CI)  
**Size**: 94 tables, 81 regular views, 28 materialized views  
**Usage**: Loaded during database initialization

#### schema-validation.sql
**Purpose**: Basic schema validation  
**Status**: ✅ Active  
**Features**: Validates table existence and basic structure

#### schema-validation-v2.sql
**Purpose**: Enhanced schema validation with detailed coverage analysis  
**Status**: ✅ Active (improved version)  
**Features**:
- Comprehensive object counting
- Missing object detection
- Detailed validation reporting
- Progress tracking

#### schema-health-check.sql
**Purpose**: Database health monitoring  
**Status**: ✅ Active  
**Checks**:
- Table row counts
- Index health
- View validity
- Database statistics

#### schema-coverage-analysis.sql
**Purpose**: Schema coverage comparison (expected vs. actual)  
**Status**: ✅ Active  
**Output**: Detailed coverage analysis report  
**Use Case**: Identify schema drift, missing objects

### 3. View Operations (2 scripts)

#### refresh-all-views.sql
**Purpose**: Refresh all materialized views in dependency order  
**Status**: ✅ Active  
**Features**:
- Automatic dependency resolution
- Topologically sorted refresh order
- Timeout protection (120s per view)
- Error handling (continues on failure)
- Progress logging by dependency level

#### analyze-view-dependencies.sql
**Purpose**: Analyze view dependency graph  
**Status**: ✅ Active  
**Documentation**: ⭐ Excellent  
**Output**: View dependency analysis, complexity levels

### 4. Validation Scripts (5 scripts)

#### validate-views-with-timeout.sql
**Purpose**: Comprehensive view validation with timeout protection  
**Status**: ✅ Active  
**Documentation**: ⭐ Excellent  
**Features**:
- Timeout protection (30s per view)
- Dependency level calculation
- Performance metrics
- Row count validation
- Complexity categorization
**Output**: validation_report.csv

#### validate-temporal-stratification.sql
**Purpose**: Validate temporal data stratification  
**Status**: ✅ Active  
**Use Case**: Ensure temporal sampling is working correctly

#### validate-ministry-fixes.sql
**Purpose**: Validate ministry and government view fixes (v1.32)  
**Status**: ✅ Active  
**Documentation**: ⭐ Excellent  
**GitHub Issue**: #7884  
**Use Case**: Verify bug fixes for specific views

#### validate-party-longitudinal-views.sql
**Purpose**: Validate party longitudinal analysis views  
**Status**: ✅ Active  
**Use Case**: Ensure party historical data is correct

#### validate-risk-threshold-rebalancing.sql
**Purpose**: Validate risk score threshold distribution  
**Status**: ✅ Active  
**Use Case**: Verify risk scoring calibration

### 5. Analysis Scripts (3 scripts)

#### analyze-coalition-view.sql
**Purpose**: Detailed coalition alignment matrix performance analysis  
**Status**: ✅ Active  
**Features**:
- CTE-by-CTE performance breakdown
- Query optimization identification
- Timeout protection (60s per query)

#### diagnose-ministry-views.sql
**Purpose**: Diagnose ministry view performance issues  
**Status**: ✅ Active  
**Use Case**: Troubleshoot ministry-related view problems

#### calculate-risk-gini.sql
**Purpose**: Calculate risk scores and Gini coefficients  
**Status**: ✅ Active  
**Use Case**: Risk analysis, inequality metrics

### 6. Maintenance Scripts (5 scripts)

⚠️ **WARNING**: These scripts are DESTRUCTIVE and should only be run with explicit confirmation.

#### clean-documentstatus.sql
**Purpose**: Clean up document status data  
**Status**: ⚠️ Destructive  
**Safety**: Review before execution

#### remove-committee-proposal.sql
**Purpose**: Remove committee proposal data  
**Status**: ⚠️ Destructive  
**Safety**: Review before execution

#### remove-documents.sql
**Purpose**: Remove document data  
**Status**: ⚠️ Destructive  
**Safety**: Review before execution

#### remove-documentstatus.sql
**Purpose**: Remove document status records  
**Status**: ⚠️ Destructive  
**Safety**: Review before execution

#### remove-persondata.sql
**Purpose**: Remove person data (GDPR compliance)  
**Status**: ⚠️ Destructive  
**Safety**: Review before execution  
**Use Case**: Data anonymization, GDPR right to erasure

### 7. View Definitions (7 scripts)

#### view_riksdagen_politician_v1.46.sql
**Purpose**: Core politician view definition  
**Status**: ✅ Active  
**Version**: 1.46

#### view_riksdagen_politician_experience_summary_v1.44.sql
**Purpose**: Politician experience summary metrics  
**Status**: ✅ Active  
**Version**: 1.44

#### view_riksdagen_politician_career_trajectory_v1.56.sql
**Purpose**: Politician career progression analysis  
**Status**: ✅ Active  
**Version**: 1.56

#### view_riksdagen_politician_role_evolution_v1.56.sql
**Purpose**: Politician role changes over time  
**Status**: ✅ Active  
**Version**: 1.56

#### view_riksdagen_politician_longevity_analysis_v1.56.sql
**Purpose**: Politician tenure and longevity metrics  
**Status**: ✅ Active  
**Version**: 1.56

#### view_decision_temporal_trends_v1.45.sql
**Purpose**: Decision-making temporal trends  
**Status**: ✅ Active  
**Version**: 1.45

#### view_ministry_decision_impact_v1.45.sql
**Purpose**: Ministry decision impact analysis  
**Status**: ✅ Active  
**Version**: 1.45

## Quick Reference

### By Use Case

**Database Setup**:
1. full_schema.sql - Load complete schema

**Data Extraction**:
1. extract-sample-data.sh - Main extraction
2. extract-percentile-summaries.sql - Percentile analysis

**Validation**:
1. schema-validation-v2.sql - Schema validation
2. validate-views-with-timeout.sql - View validation
3. schema-health-check.sql - Health monitoring

**Maintenance**:
1. refresh-all-views.sql - Refresh materialized views
2. analyze-view-dependencies.sql - Dependency analysis

**Troubleshooting**:
1. diagnose-ministry-views.sql - Ministry view issues
2. analyze-coalition-view.sql - Coalition view performance
3. validate-ministry-fixes.sql - Verify fixes

**Data Cleanup** (⚠️ Destructive):
1. remove-persondata.sql - GDPR erasure
2. clean-documentstatus.sql - Document cleanup

### By Priority

**P0 - Critical**:
- full_schema.sql
- extract-sample-data.sql
- refresh-all-views.sql

**P1 - High**:
- schema-validation-v2.sql
- validate-views-with-timeout.sql
- extract-sample-data-functions.sql

**P2 - Medium**:
- All validation scripts
- All analysis scripts
- schema-health-check.sql

**P3 - Low**:
- Maintenance scripts (use as needed)
- View definition scripts (loaded via schema)

## Testing

Use the SQL testing framework to validate all scripts:

```bash
cd service.data.impl/src/main/resources
./test-sql-scripts.sh
```

This will:
- Check syntax for all scripts
- Verify documentation completeness
- Test execution (where safe)
- Generate CSV report with results

## Documentation Standards

All SQL scripts should include:

1. **Header comment** with script name and purpose
2. **Usage instructions** with example commands
3. **Prerequisites** (required functions, tables, etc.)
4. **Output description** (files generated, reports, etc.)
5. **Version history** (for view definitions)
6. **Safety warnings** (for destructive scripts)

**Example**:
```sql
-- script-name.sql
-- Brief description of purpose
--
-- Purpose: Detailed explanation
-- Usage: psql -d cia_dev -f script-name.sql
-- Prerequisites: full_schema.sql must be loaded
-- Output: results.csv
-- Safety: Non-destructive / Destructive (mark clearly)
```

## Version Control

- View definition scripts are versioned (e.g., v1.56)
- Schema changes tracked in db-changelog-*.xml files
- Script modifications should update header comments
- Breaking changes require version bump

## Performance Considerations

- Most scripts include timeout protection
- Complex views may take 30-120 seconds
- Materialized view refresh can take several minutes
- Use `EXPLAIN ANALYZE` for query optimization

## Security Considerations

- All scripts use parameterized queries
- No hardcoded credentials
- GDPR compliance for person data removal
- Audit logging for destructive operations

## Maintenance

- Review scripts quarterly for optimization
- Update documentation when functionality changes
- Deprecate unused scripts (don't delete immediately)
- Track script dependencies

## Related Documentation

- [README-EXTRACT-SAMPLE-DATA.md](README-EXTRACT-SAMPLE-DATA.md) - Detailed extraction guide
- [SCHEMA-MAINTENANCE.md](../README-SCHEMA-MAINTENANCE.md) - Schema change procedures
- [full_schema.sql](full_schema.sql) - Complete schema definition

## Support

For issues or questions:
1. Check script header comments for usage instructions
2. Review related documentation
3. Check GitHub issues for known problems
4. Create new issue with [SQL] prefix

---

**Last Updated**: 2026-02-05  
**Maintained By**: CIA Development Team  
**Total Scripts**: 31
