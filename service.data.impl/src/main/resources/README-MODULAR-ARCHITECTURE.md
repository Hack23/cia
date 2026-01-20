# CIA Sample Data Extraction - Modular Architecture

## Overview
The sample data extraction scripts have been refactored into a modular architecture for better maintainability, reusability, and testability.

## File Structure

```
service.data.impl/src/main/resources/
├── extract-sample-data.sql              # Main extraction script (2,000 lines, -17%)
├── extract-sample-data.sh               # Main shell wrapper (290 lines, -26%)
├── extract-sample-data-functions.sql    # Reusable SQL functions (260 lines)
├── extract-percentile-summaries.sql     # Optional percentile module (180 lines)
└── validation-functions.sh              # Reusable validation library (280 lines)
```

## Modules

### 1. extract-sample-data-functions.sql
**Purpose**: Reusable PostgreSQL helper functions for statistical sampling and analysis

**Functions**:
- `cia_tmp_rowcount(schema, table)` - Get row count for table/view
- `cia_classify_temporal_view(viewname)` - Classify temporal granularity
- `cia_percentile_sample(table, column, order_by)` - Sample at P1-P99 percentiles
- `cia_generate_distribution_summary(table)` - Generate distribution statistics

**Usage**:
```sql
-- In extract-sample-data.sql or other scripts
\i extract-sample-data-functions.sql

-- Use functions directly
SELECT * FROM cia_percentile_sample('view_politician_risk_summary', 'risk_score');
SELECT * FROM cia_generate_distribution_summary('view_party_performance_metrics');
```

**Benefits**:
- Functions can be reused in other SQL scripts
- Easier to test individual functions
- Clear separation of utility code from main extraction logic

### 2. extract-percentile-summaries.sql
**Purpose**: Optional module for generating percentile distribution summaries across 24 analytical views

**Output**: 24 CSV files named `percentile_*.csv` containing:
- column_name, data_type, distinct_count
- min_value, max_value
- p1, p10, p25, median, p75, p90, p99

**Usage**:
```sql
-- Standalone usage
\i extract-sample-data-functions.sql  -- Load functions first
\i extract-percentile-summaries.sql   -- Generate percentile summaries

-- Or integrated in main script
\i extract-sample-data.sql  -- Includes call to percentile summaries
```

**Benefits**:
- Can be run independently for percentile analysis only
- Optional - skip if percentiles not needed
- Reduces main script complexity by ~150 lines

### 3. validation-functions.sh
**Purpose**: Reusable shell validation functions for data quality assurance

**Functions**:
- `validate_temporal_coverage()` - Check year range (2002-2026)
- `validate_party_coverage()` - Check 8 Swedish parties present
- `validate_percentile_coverage()` - Check percentile files complete
- `generate_coverage_report()` - Create validation_coverage_report.csv
- `run_all_validations()` - Execute all validations in sequence

**Usage**:
```bash
#!/bin/bash
# In extract-sample-data.sh or other scripts
source validation-functions.sh

# Run individual validations
validate_temporal_coverage
validate_party_coverage
validate_percentile_coverage
generate_coverage_report

# Or run all at once
run_all_validations
```

**Benefits**:
- Validation logic can be reused in CI/CD pipelines
- Easier to test validation functions independently
- Reduces main shell script by ~100 lines

## Integration with Main Scripts

### extract-sample-data.sql
The main SQL script now:
1. Sources helper functions from `extract-sample-data-functions.sql`
2. Executes main extraction logic (phases 1-6)
3. Optionally includes `extract-percentile-summaries.sql` for Phase 6.5
4. Cleans up functions at the end

**Before**: 2,425 lines (monolithic)
**After**: ~2,000 lines main + 260 functions + 180 percentiles = 2,440 total
**Improvement**: Better organized, reusable components

### extract-sample-data.sh
The main shell script now:
1. Sources validation functions from `validation-functions.sh`
2. Executes PostgreSQL extraction
3. Calls validation functions for coverage checks
4. Generates summary reports

**Before**: 392 lines (monolithic)
**After**: ~290 lines main + 280 validation = 570 total
**Improvement**: Validation logic is reusable, testable independently

## Migration Guide

### For Existing Users
No changes required! The main scripts work exactly as before:
```bash
# Same command as always
./extract-sample-data.sh /output/directory database_name
```

The modularization is transparent to end users.

### For Developers
New capabilities enabled by modularization:

**1. Use functions in custom scripts**:
```sql
\i extract-sample-data-functions.sql
-- Now use any function independently
SELECT * FROM cia_generate_distribution_summary('my_custom_view');
```

**2. Run validations separately**:
```bash
source validation-functions.sh
cd /path/to/sample/data
validate_temporal_coverage
validate_party_coverage
```

**3. Skip optional modules**:
```sql
-- Skip percentile summaries for faster extraction
\i extract-sample-data-functions.sql
-- Run phases 1-6 without Phase 6.5
```

## Testing

### Unit Testing SQL Functions
```sql
-- Test percentile sampling
\i extract-sample-data-functions.sql
SELECT * FROM cia_percentile_sample('pg_class', 'reltuples');

-- Test distribution summary
SELECT * FROM cia_generate_distribution_summary('pg_stat_user_tables');
```

### Unit Testing Validation Functions
```bash
source validation-functions.sh
export VALIDATION_FAILED=0

# Mock data for testing
echo "2010,2020" > extraction_statistics.csv
validate_temporal_coverage
echo "Validation result: $VALIDATION_FAILED"
```

### Integration Testing
```bash
# Run full extraction with validation
./extract-sample-data.sh /tmp/test_output test_db

# Check outputs
ls -lh /tmp/test_output/*.csv
cat /tmp/test_output/validation_coverage_report.csv
```

## Configuration Management

### Future Enhancement: sample-data-config.sh
Planned configuration file for centralizing settings:
```bash
# Sample size thresholds
export DEFAULT_SAMPLE_SIZE=200
export TREND_SAMPLE_SIZE=500

# Expected data ranges
export MIN_YEAR=2002
export MAX_YEAR=2026
export MIN_YEAR_RANGE=10

# Expected parties
export EXPECTED_PARTIES="S M SD C V KD L MP"

# Percentile targets
export PERCENTILE_TARGETS="0.01 0.10 0.25 0.50 0.75 0.90 0.99"
```

## Performance Impact

**Modularization overhead**: Negligible
- Function loading: <1 second
- Validation execution: +5-10 seconds
- Overall execution time: Same (5-10 minutes)

**Benefits**:
- Development: Easier to modify individual components
- Testing: Can test functions in isolation
- Maintenance: Clear separation of concerns
- Reusability: Functions used in multiple contexts

## Version History

### v1.0.0 (January 2026)
- Initial modular architecture
- Extracted 4 SQL functions to separate file
- Extracted Phase 6.5 to optional module
- Extracted 5 validation functions to separate file
- Maintained 100% backward compatibility

## Future Enhancements

1. **Configuration file** (Medium Priority)
   - Centralize all configuration settings
   - Allow environment-based overrides

2. **Test suite** (Medium Priority)
   - Unit tests for SQL functions
   - Unit tests for validation functions
   - Integration test for full extraction

3. **CI/CD integration** (Low Priority)
   - GitHub Actions workflow for validation
   - Automated testing on PRs

4. **Additional modules** (Low Priority)
   - `extract-distinct-values.sql` - Separate distinct value extraction
   - `analyze-sample-quality.sh` - Post-extraction quality analysis

## Support

For issues or questions about the modular architecture:
1. Check function documentation in individual files
2. Review usage examples in this README
3. Refer to STATISTICAL_SAMPLING_ENHANCEMENTS.md for methodology

## License

Same license as main CIA project (Apache 2.0)
