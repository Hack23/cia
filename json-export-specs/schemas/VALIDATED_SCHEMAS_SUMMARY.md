# Validated Schemas Summary

**Generated:** 2025-12-31  
**Purpose:** Data-validated schemas containing only fields present in actual sample data

## Overview

The original JSON export schemas were projections created before all data was available. This validation process has identified which fields actually exist in the sample data and created updated schemas that reflect reality.

## Validation Results

| Schema | Original Fields | Validated Fields | Removed Fields | Retention Rate |
|--------|----------------|------------------|----------------|----------------|
| **Politician** | 46 | 12 | 34 | 26% |
| **Party** | 43 | 3 | 40 | 7% |
| **Committee** | 26 | 2 | 24 | 8% |
| **Ministry** | 29 | 1 | 28 | 3% |
| **Intelligence** | 0 | 0 | 0 | N/A |
| **TOTAL** | 144 | 18 | 126 | 13% |

## Impact Analysis

### High Impact (75%+ fields removed)
- **Ministry**: 97% removed (28/29 fields) - Only `name` field exists in data
- **Committee**: 92% removed (24/26 fields) - Only `id`, `org_code` exist in data
- **Party**: 93% removed (40/43 fields) - Only `id`, `shortCode`, `status` exist

### Medium Impact (50-75% fields removed)
- **Politician**: 74% removed (34/46 fields) - Core fields like `id`, `firstName`, `lastName`, `party`, `status`, `totalVotes` remain

### Key Findings

1. **Most projected fields were never implemented**
   - Risk scores, influence metrics, intelligence fields
   - Performance indicators, trend analysis fields
   - Relationship and coalition tracking

2. **Only basic identifying fields exist**
   - IDs, names, status codes
   - Simple counters (total votes, total documents, total days)
   - Party affiliations

3. **Complex analytical fields missing**
   - No voting behavior analysis fields
   - No attendance or participation metrics
   - No committee effectiveness tracking
   - No ministry performance data

## Validated Schema Files

The following data-validated schema files have been created:

- `politician-schema-validated.md` - 12 fields validated
- `party-schema-validated.md` - 3 fields validated  
- `committee-schema-validated.md` - 2 fields validated
- `ministry-schema-validated.md` - 1 field validated

Each file includes:
- Updated mermaid diagram with only validated fields
- List of removed fields
- Data source references
- Migration recommendations

## Recommendations

### Immediate Actions
1. **Review original requirements**: Determine which removed fields are still needed
2. **Implement missing fields**: Add critical fields to database if required
3. **Remove obsolete projections**: Clean up schema files to remove permanently unneeded fields
4. **Update documentation**: Reflect actual vs. planned capabilities

### Long-term Strategy
1. **Phased implementation**: Prioritize high-value analytical fields
2. **Data pipeline updates**: Ensure new fields are populated from source systems
3. **Export logic**: Add computed field calculations where appropriate
4. **Continuous validation**: Run validation regularly to catch drift

## Next Steps

1. **Decision required**: Choose between:
   - **Option A**: Use validated schemas as-is (reflects current reality)
   - **Option B**: Implement missing fields in database (match original vision)
   - **Option C**: Hybrid approach (keep core fields, remove speculative ones)

2. **Implementation path**:
   - If Option A: Replace original schemas with validated versions
   - If Option B: Create implementation backlog from removed fields
   - If Option C: Review each removed field for relevance

3. **Testing**: Validate export functionality with actual data structure

## References

- **Validation Report**: `SCHEMA_VALIDATION_REPORT.md`
- **Field Mappings**: `FIELD_MAPPING.md`
- **Validation Results**: `validation-results.json`
- **Original Schemas**: `*-schema.md` files

---

**Note**: These validated schemas represent the **current state** of available data. They should be treated as the baseline for any future enhancements to the export API.
