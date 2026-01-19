# Follow-Up Action Plan for Drools Risk Rule Enhancement

**Status:** Ready for Implementation  
**Priority:** CRITICAL gaps must be addressed for issue completion  
**Created:** 2026-01-19

---

## 🎯 Critical Actions Required (Issue Completion)

### Action 1: Integrate Threshold Changes into Drools Rule Files
**Priority:** 🔴 CRITICAL  
**Effort:** 1-2 days  
**Assignee:** Development Team

**Task:**
Modify actual Drools rule files (.drl) with calibrated thresholds from threshold_recommendations.json

**Files to Modify:**
```
service.impl/src/main/resources/com/hack23/cia/service/impl/rules/politician/
├── PoliticianLowVotingParticipation.drl
├── PoliticianLowDocumentActivity.drl  
├── PoliticianHighRebelRate.drl
└── ... (other politician rules)
```

**Example Change:**
```java
// File: PoliticianLowVotingParticipation.drl
// BEFORE:
rule "High absence rate - MODERATE severity"
when
    $summary : RiksdagenVoteDataBallotPoliticianSummary(
        absenceRate > 12 && absenceRate <= 25  // Current threshold
    )
then
    insert(new PoliticianRiskViolation($summary.getPersonId(), "HIGH_ABSENCE", "MODERATE", 50));
end

// AFTER (based on distribution analysis P90 = 18%):
rule "High absence rate - MODERATE severity"
when
    $summary : RiksdagenVoteDataBallotPoliticianSummary(
        absenceRate > 15 && absenceRate <= 25  // Calibrated threshold
    )
then
    insert(new PoliticianRiskViolation($summary.getPersonId(), "HIGH_ABSENCE", "MODERATE", 50));
end
```

**Validation:**
- [ ] Rules compile successfully
- [ ] Tests pass
- [ ] Document changes in DROOLS_RISK_RULES.md

---

### Action 2: Execute Actual Accuracy Validation
**Priority:** 🔴 HIGH  
**Effort:** 2-3 days  
**Assignee:** Test Specialist / Intelligence Operative

**Task:**
Run Drools rules engine on enhanced test datasets and measure actual accuracy

**Steps:**
1. Set up Drools execution environment
2. Load enhanced test datasets from framework-validation/
3. Execute rules for each framework
4. Compare predictions to `expected_detection` columns
5. Calculate accuracy = correct_predictions / total_cases
6. Update validation-results.csv with measured results

**Expected Results:**
- Resignation prediction: ≥90% (target met/not met)
- Coalition stress: ≥85% (target met/not met)
- Ministry decline: ≥87% (target met/not met)

**Deliverables:**
- [ ] validation-results.csv with actual measurements
- [ ] Accuracy report comparing expected vs actual
- [ ] Analysis of any discrepancies

---

### Action 3: Complete Risk-Rule-Tests Suite
**Priority:** 🔴 HIGH  
**Effort:** 3-5 days  
**Assignee:** Test Specialist

**Task:**
Generate test cases for all 50 Drools rules (currently only 1 populated)

**Files to Create:**
```
service.data.impl/sample-data/risk-rule-tests/
├── politician-rules/ (24 files - only 1 exists)
│   ├── P01-absence-anomaly-test-cases.csv ✅ EXISTS
│   ├── P02-vote-rebellion-test-cases.csv ❌ MISSING
│   ├── P03-document-inactivity-test-cases.csv ❌ MISSING
│   └── ... (21 more files needed)
├── party-rules/ (10 files - all missing)
├── committee-rules/ (4 files - all missing)
├── ministry-rules/ (4 files - all missing)
└── decision-rules/ (5 files - all missing)
```

**Test Case Template:**
Each file should contain 10-20 test cases covering:
- Boundary conditions (threshold edges)
- True positives (should trigger)
- True negatives (should not trigger)
- False positives (potential issues)
- Edge cases (unusual scenarios)

**Deliverables:**
- [ ] 49 new test case CSV files
- [ ] Total 500-1000 test cases across all rules
- [ ] Update validation-expected-outcomes.csv

---

## 📊 High-Value Enhancements (Significant Improvement)

### Action 4: Add Edge Cases for All 6 Frameworks
**Priority:** ⚠️ MEDIUM  
**Effort:** 2-3 days  
**Assignee:** Intelligence Operative / Test Specialist

**Task:**
Add edge cases to remaining frameworks (currently only Temporal and Predictive enhanced)

**Frameworks Needing Edge Cases:**
1. **Pattern Recognition** (framework-validation/pattern/)
   - Target: 15 new edge cases
   - Focus: Extreme behavioral clusters, crisis patterns
   
2. **Network Analysis** (framework-validation/network/)
   - Target: 15 new edge cases
   - Focus: Extreme brokers, complete isolation cases
   
3. **Decision Intelligence** (framework-validation/decision/)
   - Target: 15 new edge cases
   - Focus: Coalition misalignment extremes, decision volume anomalies
   
4. **Comparative Analysis** (framework-validation/comparative/)
   - Target: 10 additional edge cases
   - Focus: Performance extremes, peer comparison outliers

**Deliverables:**
- [ ] 55 additional edge cases across 4 frameworks
- [ ] Total edge cases: 70 (15 current + 55 new)
- [ ] Update enhancement-details.md with new cases

---

### Action 5: Validate Historical Edge Cases
**Priority:** ⚠️ MEDIUM  
**Effort:** 3-4 days  
**Assignee:** Intelligence Operative

**Task:**
Replace synthetic edge cases with actual historical data from Riksdagen API

**Historical Events to Validate:**
1. **2014 Government Crisis** (referenced in temporal test cases)
   - Extract actual absence rates during Stefan Löfven government formation
   - Validate 65% absence spike claim
   - Source: Riksdagen API historical data

2. **2018 Election Deadlock** (referenced in predictive test cases)
   - Extract actual coalition alignment data
   - Validate 15% alignment claim
   - Source: Election Authority + Riksdagen voting records

3. **Ministry Catastrophic Decline** (P99 edge case)
   - Identify actual ministry with 50%+ approval drop
   - Extract real data to replace synthetic case
   - Source: Historical ministry performance data

**Deliverables:**
- [ ] Historical data extraction scripts
- [ ] Validation report comparing synthetic vs actual
- [ ] Updated test cases with real historical data

---

### Action 6: Cross-Framework Correlation Analysis
**Priority:** ⚠️ MEDIUM  
**Effort:** 2-3 days  
**Assignee:** Intelligence Operative

**Task:**
Analyze combined signals across frameworks for improved detection

**Example: Multi-Dimensional Resignation Risk**
```
HIGH RISK = 
  Temporal (declining engagement 10%→25% over 4 months) AND
  Predictive (resignation risk score >70) AND
  Pattern (behavioral clustering = ANOMALOUS)
  
Expected Improvement: 87% → 93% accuracy
```

**Deliverables:**
- [ ] Cross-framework correlation test cases
- [ ] Combined signal detection methodology
- [ ] Measured accuracy improvement from correlation

---

## 🚀 Advanced Features (Nice-to-Have)

### Action 7: Automated Validation Pipeline
**Priority:** 🟡 LOW  
**Effort:** 2-3 days  
**Assignee:** DevOps Engineer

**Task:**
Create GitHub Actions workflow for continuous rule validation

**Deliverables:**
- [ ] `.github/workflows/drools-rule-validation.yml`
- [ ] Automated accuracy regression testing
- [ ] CI/CD integration for rule changes
- [ ] Validation report artifacts

---

### Action 8: Machine Learning Threshold Optimization
**Priority:** 🟡 LOW  
**Effort:** 5-7 days  
**Assignee:** Data Scientist / Intelligence Operative

**Task:**
Implement ML-based adaptive threshold tuning

**Deliverables:**
- [ ] `ml_threshold_tuner.py` script
- [ ] Training on historical resignation/crisis data
- [ ] Comparison: ML-optimized vs manual thresholds
- [ ] Integration with automated pipeline

---

## 📋 Implementation Checklist

### Week 1: Critical Actions
- [ ] Day 1-2: Integrate threshold changes into .drl files (Action 1)
- [ ] Day 3-4: Execute actual accuracy validation (Action 2)
- [ ] Day 5: Begin risk-rule-tests suite generation (Action 3)

### Week 2: Complete Critical + Start High-Value
- [ ] Day 1-3: Complete risk-rule-tests suite (Action 3)
- [ ] Day 4-5: Add edge cases for remaining frameworks (Action 4)

### Week 3: High-Value Enhancements
- [ ] Day 1-3: Historical data validation (Action 5)
- [ ] Day 4-5: Cross-framework correlation analysis (Action 6)

### Week 4+: Advanced Features (Optional)
- [ ] Automated validation pipeline (Action 7)
- [ ] Machine learning integration (Action 8)

---

## 🎯 Success Criteria

### Must Have (Issue Completion)
- ✅ Drools rule files modified with calibrated thresholds
- ✅ Actual accuracy measured (not just expected)
- ✅ All 50 rules have test cases
- ✅ Targets met: 90%+ resignation, 85%+ coalition, 87%+ ministry

### Should Have (High Value)
- ✅ All 6 frameworks have edge cases
- ✅ Historical validation complete
- ✅ Cross-framework correlation implemented

### Nice to Have (Advanced)
- ⭕ Automated validation pipeline
- ⭕ ML threshold optimization
- ⭕ Performance benchmarking

---

## 📊 Resource Requirements

### Personnel
- **Development Team**: 1-2 days (Drools rule modifications)
- **Test Specialist**: 5-8 days (Test case generation, validation execution)
- **Intelligence Operative**: 5-7 days (Edge cases, historical validation, correlation)
- **DevOps Engineer**: 2-3 days (Automation - optional)
- **Data Scientist**: 5-7 days (ML integration - optional)

### Infrastructure
- Drools rules engine testing environment
- PostgreSQL database with historical data (2002-2026)
- Riksdagen API access for historical extraction
- CI/CD pipeline integration (GitHub Actions)

### Timeline
- **Minimum (Critical only)**: 2 weeks
- **Recommended (Critical + High-Value)**: 3 weeks
- **Complete (Including Advanced)**: 4-5 weeks

---

## 🔗 Related Issues

**Create Follow-Up Issues:**
1. **Issue: Integrate Calibrated Thresholds into Drools Rules** (Action 1)
2. **Issue: Execute and Measure Actual Rule Accuracy** (Action 2)
3. **Issue: Complete Risk-Rule-Tests Suite for All 50 Rules** (Action 3)
4. **Issue: Add Edge Cases to All 6 Frameworks** (Action 4)
5. **Issue: Validate Historical Edge Cases with Real Data** (Action 5)
6. **Issue: Implement Cross-Framework Correlation Analysis** (Action 6)

---

## 📞 Support

For questions or clarifications:
- **Documentation**: COMPREHENSIVE_IMPROVEMENT_ANALYSIS.md
- **Technical Details**: enhancement-details.md, threshold_recommendations.json
- **Test Data**: risk-rule-tests/ directory
- **Database Validation**: DATABASE_VIEW_VALIDATION_REPORT.md
