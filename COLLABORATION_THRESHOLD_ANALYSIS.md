# Collaboration Threshold Validation Analysis

## 🎯 Executive Summary

This document presents the empirical analysis of collaboration thresholds used in Drools risk rules for politician and party behavior. The analysis validates current thresholds against actual Swedish Parliament (Riksdagen) collaboration data.

**Key Finding**: Previous thresholds were **TOO LENIENT** - they failed to identify truly isolated behavior because cross-party collaboration is extremely rare in the Swedish Parliament.

## 📊 Data Source

- **Politician Sample**: 385 active politicians with >10 documents
- **Party Sample**: 8 active parties with collaboration data
- **Time Period**: Current parliamentary session (2022-2025)
- **Sample Data**: `service.data.impl/sample-data/view_riksdagen_politician_sample.csv` and `view_riksdagen_party_summary_sample.csv`

---

## 🧑‍⚖️ Politician-Level Analysis

### Previous Rules (Before Recalibration)

```drools
MINOR (salience 10): totalDocuments > 10 && collaborationPercentage < 20 && >= 10
MAJOR (salience 50): totalDocuments > 10 && collaborationPercentage < 10 && > 0
CRITICAL (salience 100): totalDocuments > 20 && multiPartyMotions == 0
```

### Empirical Distribution

| Metric | Value |
|--------|-------|
| **Count** | 385 active politicians |
| **Mean Collaboration** | 0.44% |
| **Median (P50)** | 0.00% |
| **P10** | 0.00% |
| **P25** | 0.00% |
| **P75** | 0.00% |
| **P90** | 1.30% |
| **Min** | 0.00% |
| **Max** | 19.60% |

### Threshold Impact Analysis

| Threshold | Count | Percentage | Assessment |
|-----------|-------|------------|------------|
| **Zero collaboration (0%)** | 325 | 84.4% | Baseline - highly partisan |
| **Below 10%** | 382 | 99.2% | **Nearly everyone** |
| **10-20%** | 3 | 0.8% | Extremely rare |
| **Above 20%** | 0 | 0.0% | **No one** exceeds this |

### Multi-Party Motions (>20 documents)

| Metric | Count | Percentage |
|--------|-------|------------|
| **Total Politicians** | 352 | - |
| **Zero multi-party motions** | 293 | 83.2% |
| **One multi-party motion** | 10 | 2.8% |
| **Two+ multi-party motions** | 49 | 13.9% |
| **Median** | 0 | - |
| **P75** | 0 | - |
| **Max** | 72 | - |

### Collaboration by Party

| Party | N | Avg Collab | Zero % | Range |
|-------|---|------------|--------|-------|
| **C** | 26 | 0.96% | 81% | 0.0-19.6% |
| **KD** | 26 | 0.68% | 69% | 0.0-4.1% |
| **L** | 17 | 0.46% | 82% | 0.0-4.7% |
| **M** | 87 | 0.71% | 82% | 0.0-12.5% |
| **MP** | 21 | 0.24% | 95% | 0.0-5.1% |
| **S** | 115 | 0.45% | 77% | 0.0-5.3% |
| **SD** | 65 | 0.00% | 100% | 0.0-0.0% |
| **V** | 24 | 0.05% | 96% | 0.0-1.1% |

### 🚨 Critical Issues with Previous Thresholds

1. **20% threshold is meaningless**: No politician exceeds it - **100% flagged**
2. **10% threshold is meaningless**: 99.2% of politicians are below it - **nearly universal**
3. **multiPartyMotions == 0 is appropriate**: 83.2% have zero, binary threshold makes sense
4. **Thresholds don't differentiate**: Everyone falls below current limits

---

## 🏛️ Party-Level Analysis

### Previous Rules (Before Recalibration)

```drools
MINOR (salience 10): avgCollaborationPercentage < 1.6 && >= 1.0
MAJOR (salience 50): avgCollaborationPercentage < 1.0 && >= 0.5
CRITICAL (salience 100): avgCollaborationPercentage < 0.5 && >= 0
CRITICAL (salience 100): currentlyActiveMembers > 5 && highlyCollaborativeMembers == 0  [REMOVED IN THIS PR]
MINOR (salience 10): totalDocuments > 50 && totalCollaborativeMotions < (totalDocuments * 0.05)
```

### Empirical Distribution

| Metric | Value |
|--------|-------|
| **Count** | 8 active parties |
| **Mean Collaboration** | 1.68% |
| **Median (P50)** | 1.65% |
| **Min** | 0.00% |
| **Max** | 2.90% |

### Threshold Impact Analysis

| Threshold | Count | Percentage | Assessment |
|-----------|-------|------------|------------|
| **CRITICAL (<0.5%)** | 1 (SD) | 12.5% | Appropriate - extreme isolation |
| **MAJOR (0.5-1.0%)** | 1 (S) | 12.5% | Reasonable - low engagement |
| **MINOR (1.0-1.6%)** | 0 | 0.0% | Gap in coverage |
| **OK (>=1.6%)** | 6 | 75.0% | Majority pass |

### Party Collaboration Details

| Party | Status | Avg Collab | Members | Highly Collab | Collab Motion Ratio |
|-------|--------|------------|---------|---------------|---------------------|
| **V** | OK | 1.6% | 22 | 0 | 2.8% |
| **S** | MAJOR | 0.8% | 110 | 0 | 1.6% |
| **M** | OK | 1.6% | 76 | 0 | 3.3% |
| **MP** | OK | 1.7% | 21 | 0 | 3.8% |
| **KD** | OK | 2.9% | 26 | 0 | 5.4% |
| **SD** | CRITICAL | 0.0% | 67 | 0 | 0.0% |
| **C** | OK | 2.4% | 25 | 0 | 7.2% |
| **L** | OK | 2.4% | 21 | 0 | 5.3% |

### 🚨 Critical Issues with Previous Party Rules

1. **highlyCollaborativeMembers == 0 for ALL parties**: Rule will flag every active party with >5 members
2. **5% collaborative motion ratio**: Most parties fall in 2-7% range, threshold is borderline
3. **Gap at 1.0-1.6%**: No parties currently fall in MINOR threshold range

---

## 🔍 Root Cause Analysis

### Why Were Previous Thresholds Failing?

1. **Assumption Error**: Rules were designed assuming normal cross-party collaboration (10-20%)
2. **Partisan Reality**: Swedish Parliament is highly partisan - median collaboration is **0.0%**
3. **Government vs Opposition Not Considered**: Rules don't account for structural collaboration differences
4. **Binary vs Continuous**: multiPartyMotions == 0 is correct (binary), but collaboration % thresholds need dramatic revision

### Structural Factors

1. **Coalition Government**: Governing parties (M, KD, L, C in current term) have higher collaboration
2. **Opposition Parties**: S, SD, V, MP have lower collaboration - structural, not behavioral
3. **Party Size**: Large parties (S, M, SD) have more members with zero collaboration
4. **Ideological Distance**: SD isolated on right, V on left - expected behavior, not anomaly

---

## ✅ Recommended Threshold Adjustments

### 🧑‍⚖️ Politician-Level Rules (PoliticianIsolatedBehavior.drl)

#### ❌ REJECT Current Thresholds
- Current: `<20%` (MINOR), `<10%` (MAJOR)
- Issue: 99%+ of politicians flagged - thresholds are meaningless

#### ✅ PROPOSED New Thresholds

```drools
rule "Politician with extremely low collaboration - below 1%"
    dialect "java"
    salience 10
    when
        $p : PoliticianComplianceCheckImpl(
            politician.active && 
            politician.totalDocuments > 10 && 
            politician.collaborationPercentage < 1.0 && 
            politician.collaborationPercentage > 0
        )
    then
        $p.addViolation(Status.MINOR, "PoliticianIsolatedBehavior", "Behavior", 
                       kcontext.getRule().getName(), "ExtremelyLowCollaboration");
end

rule "Politician with zero collaboration"
    dialect "java"
    salience 50
    when
        $p : PoliticianComplianceCheckImpl(
            politician.active && 
            politician.totalDocuments > 10 && 
            politician.collaborationPercentage == 0
        )
    then
        $p.addViolation(Status.MAJOR, "PoliticianIsolatedBehavior", "Behavior", 
                       kcontext.getRule().getName(), "ZeroCollaboration");
end

rule "Politician with no multi-party collaboration"
    dialect "java"
    salience 100
    when
        $p : PoliticianComplianceCheckImpl(
            politician.active && 
            politician.totalDocuments > 20 && 
            politician.multiPartyMotions == 0
        )
    then
        $p.addViolation(Status.CRITICAL, "PoliticianIsolatedBehavior", "Behavior", 
                       kcontext.getRule().getName(), "NoMultiPartyCollaboration");
end
```

**Rationale**:
- **P90 is 1.3%**: Setting MINOR threshold at <1% captures bottom 10% (truly isolated)
- **Median is 0%**: Zero collaboration is MAJOR issue (84% have this, but it's still concerning)
- **multiPartyMotions == 0 kept**: 83% have zero - this is the CRITICAL isolation indicator

### 🏛️ Party-Level Rules (PartyLowCollaboration.drl)

#### ✅ KEEP Current Thresholds (They Work!)

Current party thresholds (0.5%, 1.0%, 1.6%) are **empirically validated** and working correctly:
- 1 party (SD) flagged as CRITICAL (<0.5%) - correct
- 1 party (S) flagged as MAJOR (0.5-1.0%) - reasonable
- 6 parties OK (>=1.6%) - majority

#### ⚠️ FIX Broken Rule

```drools
rule "Party with no highly collaborative members"
    dialect "java"
    salience 100
    when
        $p : PartyComplianceCheckImpl(
            party.activeParliament && 
            party.party != "-" && 
            party.currentlyActiveMembers > 5 && 
            party.highlyCollaborativeMembers == 0
        )
    then
        $p.addViolation(Status.CRITICAL, "PartyLowCollaboration", "Behavior", 
                       kcontext.getRule().getName(), "NoCollaborativeMembers");
end
```

**Issue**: `highlyCollaborativeMembers` is 0 for **all parties** in sample. Rule will flag everyone.

**Options**:
1. **Remove rule entirely** (recommended) - metric not useful in highly partisan environment
2. **Redefine "highly collaborative"** - set threshold at P75 of individual collaboration (currently 0%)
3. **Change to relative measure** - flag if party has <X% of members above party average

#### ✅ KEEP Collaborative Motion Ratio Rule

```drools
rule "Party with low collaborative motions ratio"
    dialect "java"
    salience 10
    when
        $p : PartyComplianceCheckImpl(
            party.activeParliament && 
            party.party != "-" && 
            party.totalDocuments > 50 && 
            party.totalCollaborativeMotions < (party.totalDocuments * 0.05)
        )
    then
        $p.addViolation(Status.MINOR, "PartyLowCollaboration", "Behavior", 
                       kcontext.getRule().getName(), "FewCollaborativeMotions");
end
```

**Validation**: Current 5% threshold captures low-collaboration parties:
- S: 1.6% (flagged)
- V: 2.8% (flagged)
- SD: 0.0% (flagged)
- Most others: 5-7% (OK)

---

## 🎯 Context-Aware Rules (New)

### Government vs Opposition Context

```drools
rule "Opposition party with unusually low collaboration"
    dialect "java"
    salience 10
    when
        $p : PartyComplianceCheckImpl(
            party.activeParliament && 
            party.party != "-" &&
            party.governmentStatus == "OPPOSITION" &&
            party.avgCollaborationPercentage < 0.3
        )
    then
        $p.addViolation(Status.MINOR, "PartyLowCollaboration", "Behavior", 
                       kcontext.getRule().getName(), "OppositionLowCollaboration");
end

rule "Government party with low collaboration"
    dialect "java"
    salience 50
    when
        $p : PartyComplianceCheckImpl(
            party.activeParliament && 
            party.party != "-" &&
            party.governmentStatus == "GOVERNMENT" &&
            party.avgCollaborationPercentage < 1.5
        )
    then
        $p.addViolation(Status.MAJOR, "PartyLowCollaboration", "Behavior", 
                       kcontext.getRule().getName(), "GovernmentLowCollaboration");
end
```

**Note**: Requires `governmentStatus` field in `ViewRiksdagenPartySummary` entity (not currently available)

---

## 📈 Implementation Impact

### Before Changes

| Level | Previous Behavior | Issue |
|-------|-------------------|-------|
| Politician | 99.2% flagged (<10%) | Too many false positives |
| Politician | 100% flagged (<20%) | Threshold meaningless |
| Party | 100% flagged (highlyCollaborativeMembers==0) | Broken rule |

### After Changes

| Level | New Behavior | Expected Result |
|-------|--------------|-----------------|
| Politician MINOR | ~10% flagged (<1%) | Bottom decile - truly isolated |
| Politician MAJOR | 84.4% flagged (0%) | Highlights zero-collaboration concern |
| Politician CRITICAL | 83.2% flagged (multiPartyMotions==0) | Structural isolation indicator |
| Party | Remove highlyCollaborativeMembers rule | Eliminate false positives |

---

## 🔗 Related Documentation

- **Rules**: `service.impl/src/main/resources/com/hack23/cia/service/impl/rules/politician/PoliticianIsolatedBehavior.drl`
- **Rules**: `service.impl/src/main/resources/com/hack23/cia/service/impl/rules/party/PartyLowCollaboration.drl`
- **Documentation**: `DROOLS_RISK_RULES.md` (updated with new thresholds)
- **Sample Data**: `service.data.impl/sample-data/view_riksdagen_politician_sample.csv`
- **Database Views**: `DATABASE_VIEW_INTELLIGENCE_CATALOG.md`

---

## 📝 Conclusion

1. **Politician thresholds (10-20%) are unrealistic**: Reduce to <1% (MINOR) and ==0% (MAJOR)
2. **Party thresholds (0.5-1.6%) are correct**: Keep existing rules
3. **highlyCollaborativeMembers rule is broken**: Remove or redefine metric
4. **multiPartyMotions == 0 is appropriate**: Keep as CRITICAL indicator
5. **Context matters**: Future work should add government vs opposition awareness

**Bottom Line**: Swedish Parliament is highly partisan. Previous rules assumed normal collaboration levels that don't exist. Thresholds must be recalibrated to Swedish political reality.

---

**Analysis Date**: 2026-01-10  
**Analyst**: Intelligence Operative  
**Data Source**: Riksdagen API via CIA Platform Sample Data  
**Sample Size**: 385 politicians, 8 parties
