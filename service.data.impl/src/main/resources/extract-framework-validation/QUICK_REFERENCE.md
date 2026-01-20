# Quick Reference Guide - Framework Validation

## ⚡ Quick Start

```bash
# Run all tests
psql -U postgres -d cia_dev \
  -f service.data.impl/src/main/resources/extract-framework-validation-data.sql

# Validate refactoring
./service.data.impl/src/main/resources/validate-refactoring.sh
```

## 📊 Framework Overview

| Framework | Tests | Output Files | Primary Views |
|-----------|-------|--------------|---------------|
| **1. Temporal** | 10 | `temporal/test_1_*.csv` | election_proximity, election_anomalies, seasonal_patterns |
| **2. Comparative** | 7 | `comparative/test_2_*.csv` | party_longitudinal, coalition_evolution, electoral_trends |
| **3. Pattern** | 3 | `pattern/test_3_*.csv` | behavioral_trends, career_path |
| **4. Predictive** | 4 | `predictive/test_4_*.csv` | pre_election_activity, risk_summary |
| **5. Network** | 2 | `network/test_5_*.csv` | influence_metrics, coalition_alignment |
| **6. Decision** | 2 | `decision/test_6_*.csv` | decision_temporal, decision_impact |

**Total: 28 test cases** (increased from 18, +56%)

## 🔧 Configuration Quick Reference

| Parameter | Value | Description |
|-----------|-------|-------------|
| `sample_size_default` | 50 | Default test case count |
| `sample_size_medium` | 60 | Medium sample size |
| `lookback_months_medium` | 12 | 1-year analysis window |
| `lookback_months_long` | 24 | 2-year analysis window |
| `trend_significant_threshold` | 10% | Significant trend change |
| `performance_high_threshold` | 75% | High performance cutoff |
| `discipline_high_threshold` | 95% | High discipline cutoff |
| `absence_high_risk_threshold` | 20% | High absence risk |

**Full config**: `extract-framework-validation/config.sql` (45+ parameters)

## 🛠️ Helper Functions

| Function | Purpose | Example |
|----------|---------|---------|
| `cia_get_config_value(key)` | Get config parameter | `cia_get_config_value('sample_size_default')::INTEGER` |
| `cia_calculate_temporal_trend()` | Classify trends | `cia_calculate_temporal_trend(baseline, current, 10.0)` |
| `cia_classify_performance()` | Tier classification | `cia_classify_performance(value, 75, 50)` |
| `cia_classify_risk_level()` | Risk assessment | `cia_classify_risk_level(absence, trend)` |
| `cia_get_lookback_interval()` | Date intervals | `cia_get_lookback_interval('medium')` |

**Full list**: `extract-framework-validation/helpers.sql` (11 functions)

## 📁 File Structure

```
extract-framework-validation/
├── config.sql          # 129 lines - Configuration
├── helpers.sql         # 352 lines - Helper functions
├── temporal.sql        # 430+ lines - 7 test cases
├── comparative.sql     # 305+ lines - 4 test cases
├── pattern.sql         # 142 lines - 2 test cases
├── predictive.sql      # 280+ lines - 4 test cases
├── network.sql         # 119 lines - 2 test cases
├── decision.sql        # 142 lines - 2 test cases
├── README.md           # Comprehensive guide
├── ARCHITECTURE.md     # Mermaid diagrams
└── REFACTORING_SUMMARY.md # Metrics
```

## 🎯 Test Cases (21 Total)

### Temporal Analysis (10 tests)
1. **1.1** - Upward Trend: Attendance improvement (50 cases)
2. **1.2** - Downward Trend: Ministry effectiveness decline (30 cases)
3. **1.2b** - Ministry Risk Evolution: Multi-quarter deterioration (35 cases)
4. **1.3** - Cyclical Patterns: Parliamentary seasonality (100 cases)
5. **1.4** - Anomalies: Sudden decision spikes (40 cases)
6. **1.5** - 🆕 Election Proximity: Behavioral changes near elections (50 cases)
7. **1.6** - 🆕 Election Year Patterns: Year type differences (60 cases)
8. **1.7** - 🎯 Election Year Anomalies: Statistical outliers (50 cases) **PHASE2**
9. **1.8** - 🎯 Election vs Midterm: Direct statistical comparison (60 cases) **PHASE2**
10. **1.9** - 🎯 Seasonal Patterns: Parliamentary session cycles (100 cases) **PHASE2**

### Comparative Analysis (7 tests)
1. **2.1** - Party Rankings: Win rate & discipline (all parties)
2. **2.2** - Peer Comparison: Individual vs party average (60 cases)
3. **2.3** - Party Momentum: 6-month vs 12-month trends (70 cases)
4. **2.4** - 🆕 Party Longitudinal: Multi-year performance (all parties)
5. **2.5** - 🎯 Coalition Evolution: Coalition stability analysis (50 cases) **PHASE2**
6. **2.6** - 🎯 Electoral Trends: Multi-election performance (12 cases) **PHASE2**
7. **2.7** - 🎯 Party Summary: Comprehensive metrics (12 cases) **PHASE2**

### Pattern Recognition (3 tests)
1. **3.1** - Behavioral Clustering: Normal/anomalous/concerning (100 cases)
2. **3.2** - Rebellion Patterns: Party defection rates (80 cases)
3. **3.3** - 🎯 Career Path: 10-level classification (80 cases) **PHASE2**

### Predictive Intelligence (4 tests)
1. **4.1** - Resignation Risk: Declining engagement (40 cases)
2. **4.1b** - Politician Risk: Multi-violation analysis (45 cases)
3. **4.2** - Coalition Stress: Low alignment pairs (50 cases)
4. **4.3** - 🆕 Pre-Election Activity: Quarterly activity spikes (50 cases)

### Network Analysis (2 tests)
1. **5.1** - Power Brokers: High influence metrics (30 cases)
2. **5.2** - Coalition Facilitators: Cross-bloc bridges (25 cases)

### Decision Intelligence (2 tests)
1. **6.1** - Effectiveness Patterns: Decision quality trends (40 cases)
2. **6.2** - Coalition Misalignment: Cross-bloc disagreements (40 cases)

## 🆕 Advanced Views Coverage (v1.58-v1.61) - **100% COMPLETE**

| View | Framework | Test | Status |
|------|-----------|------|--------|
| `view_riksdagen_election_proximity_trends` | Temporal | 1.5 | ✅ Covered |
| `view_riksdagen_election_year_behavioral_patterns` | Temporal | 1.6 | ✅ Covered |
| `view_riksdagen_election_year_anomalies` | Temporal | 1.7 | ✅ **PHASE2** |
| `view_riksdagen_election_year_vs_midterm` | Temporal | 1.8 | ✅ **PHASE2** |
| `view_riksdagen_seasonal_activity_patterns` | Temporal | 1.9 | ✅ **PHASE2** |
| `view_riksdagen_party_longitudinal_performance` | Comparative | 2.4 | ✅ Covered |
| `view_riksdagen_party_coalition_evolution` | Comparative | 2.5 | ✅ **PHASE2** |
| `view_riksdagen_party_electoral_trends` | Comparative | 2.6 | ✅ **PHASE2** |
| `view_riksdagen_party_summary` | Comparative | 2.7 | ✅ **PHASE2** |
| `view_riksdagen_politician_career_path_10level` | Pattern | 3.3 | ✅ **PHASE2** |
| `view_riksdagen_pre_election_quarterly_activity` | Predictive | 4.3 | ✅ Covered |

**Coverage**: 11 of 11 views (100%) ✅

## 📝 Updating Configuration

```sql
-- Edit config.sql
UPDATE framework_validation_config 
SET config_value = '100' 
WHERE config_key = 'sample_size_default';

-- Or edit the file directly
vi service.data.impl/src/main/resources/extract-framework-validation/config.sql
```

## 🔍 Adding New Test Cases

1. Choose appropriate framework file (temporal.sql, comparative.sql, etc.)
2. Copy existing test case structure
3. Update view name, filters, and output path
4. Use helper functions for consistency
5. Add entry to main orchestrator script
6. Run validation script to verify

## 📊 Code Quality Metrics

| Metric | Before | After | Improvement |
|--------|--------|-------|-------------|
| Total Lines | 1,466 | 295 (main) | -80% |
| Code Duplication | 30% | 3% | -90% |
| Magic Numbers | 60+ | 0 | -100% |
| Test Cases | 18 | 21 | +17% |
| Helper Functions | 0 | 11 | +∞ |
| Documentation | Minimal | 1,000+ lines | Comprehensive |

## 🚀 Performance Tips

1. **Parallel Execution**: Not supported - run sequentially
2. **Index Usage**: Views use pre-computed aggregations
3. **Sample Size**: Adjust via config for faster testing
4. **Lookback Period**: Shorter periods = faster queries

## ⚠️ Troubleshooting

| Issue | Solution |
|-------|----------|
| Empty CSV files | Check view exists: `\d view_name` |
| Config not found | Verify config.sql loaded first |
| Function not found | Verify helpers.sql loaded after config |
| Slow queries | Reduce lookback period or sample size |
| Path errors | Verify `/workspaces/cia/` path exists |

## 📚 Documentation Links

- [Full README](README.md) - Comprehensive usage guide
- [Architecture](ARCHITECTURE.md) - Mermaid diagrams & flow charts
- [Refactoring Summary](REFACTORING_SUMMARY.md) - Detailed metrics
- [Validation Script](../validate-refactoring.sh) - Automated checks

## 🔄 Version History

- **v1.0** (2024-01-20): Initial modular refactoring
  - Split monolithic 1,466-line script into 8 modules
  - Created 11 helper functions
  - Added 45+ configuration parameters
  - Implemented 18 test cases

- **v1.1** (2024-01-20): Advanced views coverage (v1.58-v1.61)
  - Added Test 1.5: Election Proximity Trends
  - Added Test 1.6: Election Year Patterns
  - Added Test 2.4: Party Longitudinal Performance
  - Added Test 4.3: Pre-Election Activity
  - Created ARCHITECTURE.md with mermaid diagrams
  - Created QUICK_REFERENCE.md

## 💡 Best Practices

1. **Always load config first** - Other modules depend on it
2. **Use helper functions** - Ensures consistency
3. **Validate after changes** - Run validate-refactoring.sh
4. **Document new tests** - Add to README and this guide
5. **Keep samples focused** - Use appropriate sample sizes
6. **Test incrementally** - Run individual framework files during development

## 📞 Support

For questions or issues:
1. Check [README.md](README.md) for detailed documentation
2. Review [ARCHITECTURE.md](ARCHITECTURE.md) for system design
3. Run [validate-refactoring.sh](../validate-refactoring.sh) for verification
4. Consult [REFACTORING_SUMMARY.md](REFACTORING_SUMMARY.md) for metrics
