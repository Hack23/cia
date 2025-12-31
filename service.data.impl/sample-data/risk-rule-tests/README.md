# Risk Rule Test Data Suite

## Overview

This directory contains comprehensive test datasets for validating all 50 behavioral detection risk rules in the CIA platform. The test suite enables systematic validation of rule accuracy, threshold tuning, and false positive/negative analysis.

## Purpose

From **RISK_RULES_INTOP_OSINT.md**, the CIA platform implements:
- **24 Politician Rules**: Individual behavioral analysis
- **10 Party Rules**: Organizational effectiveness monitoring
- **4 Committee Rules**: Legislative body performance
- **4 Ministry Rules**: Government executive assessment
- **5 Decision Pattern Rules**: Legislative effectiveness and coalition stability
- **3 Other Rules**: Application and user-level rules

**Validation Goals**:
- Verify accuracy claims: 87% pre-resignation, 78% coalition stress, 82% ministry decline
- Validate false positive rate ≤10%, false negative rate ≤15%
- Enable threshold tuning for salience levels (MINOR 10-49, MAJOR 50-99, CRITICAL 100+)

## Directory Structure

```
risk-rule-tests/
├── README.md                                    # This file
├── test-scenario-matrix.csv                     # Complete test scenario catalog
├── validation-expected-outcomes.csv             # Expected results for all test cases
├── validation-results.csv                       # Actual vs. expected comparison
├── politician-rules/                            # 24 politician risk rules
│   ├── P01-absence-anomaly-test-cases.csv
│   ├── P02-vote-rebellion-test-cases.csv
│   ├── P03-document-inactivity-test-cases.csv
│   ├── P09-pre-resignation-indicators-test-cases.csv
│   └── ... (20 more files)
├── party-rules/                                 # 10 party risk rules
│   ├── PA01-coalition-instability-test-cases.csv
│   ├── PA02-party-lazy-test-cases.csv
│   └── ... (8 more files)
├── committee-rules/                             # 4 committee risk rules
│   ├── C01-committee-gridlock-test-cases.csv
│   ├── C02-leadership-vacancy-test-cases.csv
│   └── ... (2 more files)
├── ministry-rules/                              # 4 ministry risk rules
│   ├── M01-ministry-decline-test-cases.csv
│   ├── M02-inactive-legislation-test-cases.csv
│   └── ... (2 more files)
├── decision-rules/                              # 5 decision pattern rules
│   ├── D01-party-low-approval-test-cases.csv
│   ├── D02-politician-ineffectiveness-test-cases.csv
│   ├── D03-ministry-declining-success-test-cases.csv
│   ├── D04-decision-volume-anomaly-test-cases.csv
│   └── D05-coalition-misalignment-test-cases.csv
└── validation-queries/                          # SQL validation queries
    ├── validate-all-rules.sql
    ├── validate-politician-rules.sql
    ├── validate-party-rules.sql
    ├── validate-committee-rules.sql
    ├── validate-ministry-rules.sql
    └── validate-decision-rules.sql
```

## Test Case Structure

Each test case CSV file contains the following columns:

| Column | Description | Example |
|--------|-------------|---------|
| `test_id` | Unique test case identifier | `P01-TC001` |
| `rule_id` | Risk rule being tested | `P01` |
| `scenario_type` | Test scenario category | `TRUE_POSITIVE`, `TRUE_NEGATIVE`, `BOUNDARY_CONDITION`, `FALSE_POSITIVE`, `FALSE_NEGATIVE` |
| `description` | Human-readable test description | "80% absence rate triggers MAJOR severity" |
| `expected_salience` | Expected salience score | `85` |
| `expected_severity` | Expected severity level | `MAJOR`, `MINOR`, `CRITICAL`, `NONE` |
| `threshold_tested` | Specific threshold being validated | `MAJOR_THRESHOLD_50` |
| `test_data_reference` | Reference to synthetic data | `TEST_P01_HIGH_ABSENCE` |
| `validation_query` | SQL query to execute | `SELECT ... FROM view_riksdagen_vote_data_ballot_politician_summary_monthly WHERE ...` |
| `notes` | Additional context | "Simulates pre-resignation attendance drop" |

## Test Scenario Categories

### 1. Boundary Conditions
Tests behavior at exact threshold boundaries:
- **Below threshold**: Salience = 9 (should NOT trigger MINOR)
- **At threshold**: Salience = 10 (should trigger MINOR)
- **Upper bound**: Salience = 49 (should remain MINOR, not escalate to MAJOR)
- **Next threshold**: Salience = 50 (should trigger MAJOR)

### 2. True Positives
Known cases that should trigger the rule correctly:
- High absence rates (80%+) triggering absence anomaly rules
- Consistent rebel voting (20%+) triggering party discipline rules
- Zero legislative output triggering productivity rules

### 3. True Negatives
Normal behavior that should NOT trigger rules:
- 10% absence rate (within acceptable range)
- 90% party loyalty (high discipline)
- Regular document production (5+ documents/year)

### 4. False Positives (Potential)
Borderline cases requiring tuning:
- Summer recess absences (structural, not behavioral)
- Election period high activity (expected anomaly)
- Government change coalition shifts (expected disruption)

### 5. False Negatives (Potential)
Missed detections requiring rule improvement:
- Strategic abstention masking ineffectiveness
- Committee specialization reducing overall activity
- Coordinated opposition behavior appearing normal

## Threshold Definitions

Per **RISK_RULES_INTOP_OSINT.md**:

| Severity | Salience Range | Color Code | Intelligence Level |
|----------|----------------|------------|-------------------|
| MINOR | 10-49 | 🟡 | Early warning, trend monitoring |
| MAJOR | 50-99 | 🟠 | Significant concern, tactical intelligence |
| CRITICAL | 100+ | 🔴 | Immediate action required, strategic intelligence |

## Usage

### Running Validation Suite

```bash
# Execute all validation queries
psql -d cia_dev -U eris -f validation-queries/validate-all-rules.sql

# Run specific rule category
psql -d cia_dev -U eris -f validation-queries/validate-politician-rules.sql

# Compare actual vs. expected results
python3 scripts/compare-validation-results.py
```

### Interpreting Results

**Success Criteria**:
- Rule triggers on true positive cases (100% accuracy expected)
- Rule does NOT trigger on true negative cases (100% accuracy expected)
- Boundary conditions behave as documented (100% accuracy expected)
- False positive rate ≤10%
- False negative rate ≤15%

**Accuracy Calculation**:
```
Accuracy = (True Positives + True Negatives) / Total Test Cases
Precision = True Positives / (True Positives + False Positives)
Recall = True Positives / (True Positives + False Negatives)
F1 Score = 2 * (Precision * Recall) / (Precision + Recall)
```

## Related Documentation

- [RISK_RULES_INTOP_OSINT.md](../../../RISK_RULES_INTOP_OSINT.md) - Complete risk rule catalog
- [DATA_ANALYSIS_INTOP_OSINT.md](../../../DATA_ANALYSIS_INTOP_OSINT.md) - Analytical frameworks
- [DATABASE_VIEW_INTELLIGENCE_CATALOG.md](../../../DATABASE_VIEW_INTELLIGENCE_CATALOG.md) - Database view reference
- [INTELLIGENCE_DATA_FLOW.md](../../../INTELLIGENCE_DATA_FLOW.md) - Data pipeline documentation

## Support

For questions or issues with the test suite:
1. Review test case structure in this README
2. Check validation query syntax in `validation-queries/`
3. Open GitHub issue with `test-suite` label
4. Reference specific rule ID and test case ID in issue description
