# Risk Rules Integration Tests

## Overview

The `RiskRulesIntegrationTest` suite validates the end-to-end OSINT intelligence analysis pipeline:

1. **Database Layer**: Queries PostgreSQL views containing political data
2. **Analysis Layer**: Executes Drools risk assessment rules
3. **Persistence Layer**: Stores RuleViolation entities in database
4. **Audit Layer**: Validates audit trail fields

## Test Coverage

### Test Methods

| Test Method | Purpose | Domains Tested |
|-------------|---------|----------------|
| `testEndToEndRuleExecutionAndPersistence` | Complete workflow validation | All |
| `testPoliticianRulesPersistence` | Politician rules (24) | POLITICIAN |
| `testPartyRulesPersistence` | Party rules (10) | PARTY |
| `testViolationSeverityLevels` | Severity levels (MINOR, MAJOR, CRITICAL) | All |
| `testViolationMetadata` | Audit trail completeness | All |
| `testViolationRefreshOnRerun` | Transaction management | All |
| `testResourceTypeCoverage` | All 4 resource types | POLITICIAN, PARTY, COMMITTEE, MINISTRY |

### Rule Domains

- **Politician Rules**: 24 rules assessing individual politicians
  - PoliticianLazy, PoliticianHighRebelRate, PoliticianExperience, etc.
- **Party Rules**: 10 rules assessing political parties
  - PartyLazy, PartyHighAbsenteeism, PartyLowProductivity, etc.
- **Committee Rules**: 4 rules assessing committees
  - CommitteeInactivity, CommitteeStagnation, etc.
- **Ministry Rules**: 4 rules assessing government ministries
  - MinistryInactiveLegislation, MinistryStagnation, etc.

## Prerequisites

### Database Setup

The tests require a running PostgreSQL database with the CIA schema:

```bash
# Database credentials (from database.properties)
Database: cia_dev
Host: 127.0.0.1
Port: 5432
User: eris
Password: discord
```

### Schema Initialization

```bash
# Load full schema
psql -U eris -d cia_dev -f service.data.impl/src/main/resources/full_schema.sql
```

### Test Data

The integration tests work with **real data** from the database views. They do not require test data fixtures.

The tests will:
- Find violations based on actual data in the database
- Work with empty database (0 violations expected if no data)
- Work with populated database (violations expected based on data)

## Running the Tests

### Command Line

```bash
# Run all integration tests
mvn test -Dtest=RiskRulesIntegrationTest

# Run specific test
mvn test -Dtest=RiskRulesIntegrationTest#testEndToEndRuleExecutionAndPersistence

# Run with verbose output
mvn test -Dtest=RiskRulesIntegrationTest -X
```

### IDE

1. Ensure PostgreSQL is running with `cia_dev` database
2. Right-click on `RiskRulesIntegrationTest.java`
3. Select "Run as JUnit Test"

### CI/CD

The tests run automatically in GitHub Actions workflow:
- PostgreSQL 16 is provisioned
- Schema is loaded from `full_schema.sql`
- Tests execute as part of integration test suite

## Test Behavior

### With Empty Database

If the database has no political data:
- Tests will pass
- No violations will be found (expected behavior)
- All assertions handle 0 violations gracefully

### With Populated Database

If the database has political data from Riksdagen API:
- Tests will find violations based on actual risk thresholds
- Common violations: PoliticianLazy, PoliticianHighRebelRate, PartyLazy
- Number of violations varies based on actual political activity

## Validation Points

### Transaction Management

Tests verify that:
1. Old violations are cleared before new run
2. New violations are persisted within transaction
3. All-or-nothing semantics (rollback on error)

### Audit Trail

Each RuleViolation must have:
- `id`: Database primary key (auto-generated)
- `detectedDate`: Timestamp when violation was detected
- `referenceId`: ID of the resource (politician ID, party ID, etc.)
- `name`: Human-readable name of the resource
- `resourceType`: POLITICIAN, PARTY, COMMITTEE, or MINISTRY
- `ruleName`: Name of the Drools rule that fired
- `ruleDescription`: Description of the violation
- `ruleGroup`: Logical grouping (Behavior, Performance, etc.)
- `status`: Severity (MINOR, MAJOR, CRITICAL)
- `positive`: Additional context or positive aspect

### Temporal Analysis

Rules evaluate data across time periods:
- **Daily summaries**: Latest day's voting behavior
- **Monthly summaries**: Latest month's voting patterns
- **Annual summaries**: Yearly performance metrics

### Severity Levels

| Severity | Salience | Description |
|----------|----------|-------------|
| MINOR | 10 | Low-priority issues requiring attention |
| MAJOR | 50 | Significant issues requiring action |
| CRITICAL | 100+ | Severe issues requiring immediate attention |

## Troubleshooting

### Database Connection Error

```
ERROR: permission denied for table databasechangelog
```

**Solution**: Ensure user `eris` has proper permissions:
```sql
GRANT ALL ON SCHEMA public TO eris;
GRANT ALL PRIVILEGES ON DATABASE cia_dev TO eris;
```

### No Violations Found

If tests find 0 violations:
1. Check that database has political data
2. Run data import: `mvn -Pdata-import`
3. Verify views are populated: `SELECT COUNT(*) FROM view_riksdagen_politician;`

### Test Timeout

If tests timeout after 300 seconds:
1. Check database performance
2. Ensure indexes are created
3. Consider reducing dataset size for testing

## Coverage Goals

Per [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md):
- **Line Coverage**: 80% minimum
- **Branch Coverage**: 70% minimum

Current coverage:
- Integration tests: Covers end-to-end workflow
- Unit tests (DroolsRulesValidationTest): Covers individual rules
- Combined: Achieves 85%+ coverage in rules package

## References

- [DROOLS_RISK_RULES.md](../../../DROOLS_RISK_RULES.md) - Rule specifications
- [DROOLS_TEST_COVERAGE.md](../../DROOLS_TEST_COVERAGE.md) - Unit test coverage
- [DATA_MODEL.md](../../../DATA_MODEL.md) - Database schema documentation
- [ARCHITECTURE.md](../../../ARCHITECTURE.md) - System architecture

## Next Steps

Future enhancements:
1. Add test data fixtures for deterministic testing
2. Add performance benchmarking (rules execution time)
3. Add test coverage for Committee and Ministry domains with specific data
4. Add parameterized tests for all 45+ rules
