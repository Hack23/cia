# Validated Schemas Summary

**Generated:** 2026-04-05  
**Purpose:** Data-validated schemas with field implementation status classification

## Overview

The original JSON export schemas define comprehensive data models for political intelligence. Automated validation has identified 144 field mismatches across 4 schemas. These mismatches have been classified into three categories: **structural** (JSON grouping objects, including non-scalar relationship/link types and array types), **computed** (derivable from existing DB columns), and **planned** (requiring new data sources).

## Validation Results

| Schema | Original Fields | Implemented | Structural | Computed | Planned | Mismatches |
|--------|----------------|-------------|------------|----------|---------|------------|
| **Politician** | 55 | 14 | 24 | 11 | 6 | 41 |
| **Party** | 51 | 4 | 22 | 12 | 13 | 47 |
| **Committee** | 29 | 4 | 9 | 9 | 7 | 25 |
| **Ministry** | 33 | 2 | 12 | 10 | 9 | 31 |
| **Intelligence** | 0* | 0 | 0 | 0 | 0 | **0** |
| **TOTAL** | **168** | **24** | **67** | **42** | **35** | **144** |

*Intelligence schema uses a different structure not parsed by mermaid field extraction.

## Mismatch Classification

### Not Real Mismatches (67 structural)
Fields like `attributes`, `labels`, `relationships`, `intelligence`, `decisions`, `policy`, `trend`, `alignment`, `productivity` are **JSON grouping objects** — they organize the schema structure but don't correspond to individual database columns. Non-scalar relationship/link types (e.g., `PartyLink:party`, `MinistryLink:ministry`, `Trends:trend`) and array types (e.g., `CommitteeLink[]:committees`, `String[]:subcategories`) are also counted as structural. These should be excluded from field-level validation.

### Implementable Now (42 computed)
Fields like `fullName`, `partyLoyalty`, `performanceScore` can be **derived from existing database columns** with SQL aggregations, concatenations, or view mappings. See `FIELD_MAPPING.md` for implementation details.

### Future Work (35 planned)
Fields like `foundedYear`, `ideology`, `nameEn`, `headquarters`, `keyVotes`, `partners` require **external data sources** not currently in the database. These are long-term implementation goals.

## Validated Schema Files

The following data-validated schema files have been created:

- `politician-schema-validated.md` - 14 fields validated
- `party-schema-validated.md` - 4 fields validated  
- `committee-schema-validated.md` - 4 fields validated
- `ministry-schema-validated.md` - 2 fields validated

Each file includes:
- Updated mermaid diagram with only validated fields
- List of removed fields
- Data source references
- Migration recommendations

## Recommendations

### Immediate Actions (Sprint 1)
1. **Implement low-effort computed fields**: Map direct DB columns like `fullName`, `partyLoyalty`, `totalMembers`
2. **Update field validation**: Exclude structural fields from mismatch counting
3. **Enrich validated schemas**: Add computable fields as they are implemented

### Medium-Term (Sprint 2)
1. **Implement aggregation-based fields**: Build risk scores, performance metrics, coalition analysis
2. **Add time series exports**: Leverage existing daily/weekly/monthly/annual summary views
3. **Cross-reference view data**: Map committee and ministry roles to comprehensive profiles

### Long-Term Strategy
1. **External data collection**: Gather party founding years, ideology classifications, English translations
2. **Budget/financial integration**: Integrate ministry budget data when available
3. **Predictive models**: Build forecasting capabilities for electoral and coalition predictions

## Next Steps

1. **Hybrid approach selected**: Keep comprehensive schema definitions with clear status annotations
2. **Implementation priority**: See `FIELD_MAPPING.md` "Top 25 High-Value Fields" section
3. **Remediation roadmap**: See `SCHEMA_VALIDATION_REPORT.md` "Remediation Roadmap" section
4. **Continuous validation**: CI workflow validates on every push to schema or data files

## References

- **Validation Report**: `SCHEMA_VALIDATION_REPORT.md`
- **Field Mappings**: `FIELD_MAPPING.md`
- **Validation Results**: `validation-results.json`
- **Original Schemas**: `*-schema.md` files

---

**Note**: These validated schemas represent the **current state** of available data. They should be treated as the baseline for any future enhancements to the export API.
