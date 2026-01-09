# Drools Risk Rules Documentation

## Overview

This document describes the comprehensive risk assessment rules implemented in the Citizen Intelligence Agency (CIA) Drools rules engine. These rules analyze politician and party behavior to identify risks to democratic effectiveness, accountability, and transparency.

## Rule Architecture

All rules follow a consistent pattern:
- **Dialect**: Java
- **Salience**: Priority ordering (10=MINOR, 50=MAJOR, 100+=CRITICAL)
- **Status Levels**: MINOR, MAJOR, CRITICAL
- **Categories**: Behavior, Experience, Attribute

## Politician Risk Rules

### 1. PoliticianLowDocumentActivity.drl
**Purpose**: Detects politicians with low legislative productivity based on document production.

**Rules**:
- **Very low document activity last year** (MINOR, salience 10)
  - Condition: `documentsLastYear < 5 && documentsLastYear > 0`
  - Category: Behavior
  - Resource Tag: LowProductivity

- **No documents last year** (MAJOR, salience 50)
  - Condition: `documentsLastYear == 0`
  - Category: Behavior
  - Resource Tag: NoProductivity

- **Very low average document activity** (CRITICAL, salience 100)
  - Condition: `documentYearsActive > 2 && averageDocsPerYear < 3`
  - Category: Behavior
  - Resource Tag: ChronicallyLowProductivity

**Intelligence Value**: Identifies politicians who are not actively contributing to the legislative process through motions, proposals, and other parliamentary documents.

---

### 2. PoliticianIsolatedBehavior.drl
**Purpose**: Identifies politicians who avoid cross-party collaboration.

**Rules**:
- **Low collaboration - below 20%** (MINOR, salience 10)
  - Condition: `totalDocuments > 10 && collaborationPercentage < 20 && >= 10`
  - Category: Behavior
  - Resource Tag: LowCollaboration

- **Very low collaboration - below 10%** (MAJOR, salience 50)
  - Condition: `totalDocuments > 10 && collaborationPercentage < 10 && > 0`
  - Category: Behavior
  - Resource Tag: VeryLowCollaboration

- **No multi-party collaboration** (CRITICAL, salience 100)
  - Condition: `totalDocuments > 20 && multiPartyMotions == 0`
  - Category: Behavior
  - Resource Tag: NoMultiPartyCollaboration

**Intelligence Value**: Tracks partisan behavior and identifies politicians who operate in isolation from other parties, which may indicate ideological rigidity or poor coalition-building skills.

---

### 3. PoliticianIneffectiveVoting.drl
**Purpose**: Tracks politicians whose votes rarely align with winning outcomes.

**Rules**:
- **Rarely wins votes - below 30%** (MINOR, salience 10)
  - Condition: `annualSummary.wonPercentage < 30`
  - Category: Behavior
  - Resource Tag: LowWinRate

- **Rarely wins votes - below 20%** (MAJOR, salience 50)
  - Condition: `annualSummary.wonPercentage < 20`
  - Category: Behavior
  - Resource Tag: VeryLowWinRate

- **Rarely wins votes - below 10%** (CRITICAL, salience 100)
  - Condition: `annualSummary.wonPercentage < 10`
  - Category: Behavior
  - Resource Tag: CriticallyLowWinRate

**Intelligence Value**: Measures political effectiveness by tracking how often a politician's voting position aligns with the majority outcome. Low win rates may indicate membership in a marginalized minority or ineffective coalition participation.

---

### 4. PoliticianHighRebelRate.drl
**Purpose**: Enhanced detection of party rebels with granular thresholds.

**Rules**:
- **High rebel rate - 5-10% annually** (MINOR, salience 10)
  - Condition: `rebelPercentage >= 5 && < 10`
  - Category: Behavior
  - Resource Tag: FrequentRebelVoting

- **Very high rebel rate - 10-20% annually** (MAJOR, salience 50)
  - Condition: `rebelPercentage >= 10 && < 20`
  - Category: Behavior
  - Resource Tag: VeryHighRebelVoting

- **Extreme rebel rate - 20%+ annually** (CRITICAL, salience 100)
  - Condition: `rebelPercentage >= 20`
  - Category: Behavior
  - Resource Tag: ExtremeRebelVoting

**Intelligence Value**: Identifies politicians who frequently vote against their party's position, which may indicate internal party conflicts, ideological independence, or coalition stress.

---

### 5. PoliticianLowVotingParticipation.drl
**Purpose**: Comprehensive voting participation tracking combining multiple risk factors.

**Rules**:
- **High abstention rate - over 10% annually** (MINOR, salience 10)
  - Condition: `politicianPercentageAbstain > 10`
  - Category: Behavior
  - Resource Tag: HighAbstentionRate

- **Combined low participation** (MAJOR, salience 50)
  - Condition: `politicianPercentageAbsent >= 15 && wonPercentage < 30`
  - Category: Behavior
  - Resource Tag: LowParticipationAndEffectiveness

- **Extreme combined risk** (CRITICAL, salience 100)
  - Condition: `politicianPercentageAbsent >= 25 && wonPercentage < 20`
  - Category: Behavior
  - Resource Tag: ExtremeLowParticipationAndEffectiveness

**Intelligence Value**: Identifies compounded risks where politicians are both absent frequently and ineffective when present, indicating serious accountability concerns.

---

### 6. PoliticianLazy.drl (Enhanced)
**Purpose**: Detects excessive absenteeism from parliamentary votes.

**Enhanced Rules**:
- **Absent 100% last day** (MINOR, salience 10)
- **Absent >20% last month** (MAJOR, salience 50)
- **Absent 20-30% last year** (CRITICAL, salience 100)
- **Absent >30% last year** (CRITICAL, salience 150, ExtremeAbsenteeism)

**Enhancement**: Added granular distinction for extreme absenteeism (>30%).

---

## Party Risk Rules

### 7. PartyHighAbsenteeism.drl
**Purpose**: Enhanced party-level absence tracking across multiple timeframes.

**Rules**:
- **Concerning daily absenteeism - 25%+ daily** (MINOR, salience 10)
  - Condition: `dailySummary.partyPercentageAbsent >= 25`
  - Category: Behavior
  - Resource Tag: HighDailyAbsenteeism

- **High monthly absenteeism - 20%+ monthly** (MAJOR, salience 50)
  - Condition: `monthlySummary.partyPercentageAbsent >= 20`
  - Category: Behavior
  - Resource Tag: HighMonthlyAbsenteeism

- **Chronic absenteeism - 20%+ annually** (CRITICAL, salience 100)
  - Condition: `annualSummary.partyPercentageAbsent >= 20`
  - Category: Behavior
  - Resource Tag: ChronicAbsenteeism

**Intelligence Value**: Tracks party-wide attendance patterns that may indicate organizational problems, coalition stress, or strategic abstention campaigns.

---

### 8. PartyLowCollaboration.drl
**Purpose**: Tracks parties that avoid cross-party collaboration.

**Rules**:
- **Low average collaboration - below 15%** (MINOR, salience 10)
  - Condition: `avgCollaborationPercentage < 15 && >= 10`
  - Category: Behavior
  - Resource Tag: LowCrossPartyEngagement

- **Very low average collaboration - below 10%** (MAJOR, salience 50)
  - Condition: `avgCollaborationPercentage < 10 && > 0`
  - Category: Behavior
  - Resource Tag: VeryLowCrossPartyEngagement

- **No highly collaborative members** (CRITICAL, salience 100)
  - Condition: `currentlyActiveMembers > 5 && highlyCollaborativeMembers == 0`
  - Category: Behavior
  - Resource Tag: NoCollaborativeMembers

- **Low collaborative motions ratio** (MINOR, salience 10)
  - Condition: `totalDocuments > 50 && totalCollaborativeMotions < (totalDocuments * 0.05)`
  - Category: Behavior
  - Resource Tag: FewCollaborativeMotions

**Intelligence Value**: Identifies parties that operate in isolation, which may indicate ideological extremism, coalition inflexibility, or opposition strategy.

---

### 9. PartyLowEffectiveness.drl
**Purpose**: Party-level effectiveness tracking combining win rates and productivity.

**Rules**:
- **Low annual win rate - below 40%** (MINOR, salience 10)
  - Condition: `partyWonPercentage < 40 && >= 30`
  - Category: Behavior
  - Resource Tag: LowWinRate

- **Very low annual win rate - below 30%** (MAJOR, salience 50)
  - Condition: `partyWonPercentage < 30 && >= 20`
  - Category: Behavior
  - Resource Tag: VeryLowWinRate

- **Critically low annual win rate - below 20%** (CRITICAL, salience 100)
  - Condition: `partyWonPercentage < 20`
  - Category: Behavior
  - Resource Tag: CriticallyLowWinRate

- **Low document productivity** (MINOR, salience 10)
  - Condition: `currentlyActiveMembers > 0 && avgDocumentsLastYear < 5 && > 0`
  - Category: Behavior
  - Resource Tag: LowDocumentProductivity

**Intelligence Value**: Measures party-level political effectiveness and legislative productivity, indicating whether a party has meaningful influence or is marginalized.

---

## Analytical Framework

### Risk Assessment Dimensions

1. **Behavioral Analysis**
   - Attendance and participation patterns
   - Voting discipline and party loyalty
   - Collaboration and cross-party engagement

2. **Productivity Metrics**
   - Document production (motions, proposals, questions)
   - Legislative activity levels
   - Average productivity trends

3. **Effectiveness Measures**
   - Win rates (voting alignment with outcomes)
   - Approved proposal percentages
   - Political influence indicators

4. **Temporal Analysis**
   - Daily trend detection (immediate issues)
   - Monthly pattern recognition (emerging trends)
   - Annual assessment (sustained patterns)

### Intelligence Products

These rules enable the following intelligence products:

1. **Political Scorecards**: Individual politician performance metrics
2. **Party Effectiveness Reports**: Party-level performance analysis
3. **Risk Dashboards**: Real-time compliance and risk monitoring
4. **Trend Analysis**: Historical pattern recognition
5. **Coalition Health**: Cross-party collaboration indicators
6. **Accountability Metrics**: Democratic accountability measures

### Ethical Considerations

All rules adhere to strict ethical standards:

- **Transparency**: All rules and thresholds are publicly documented
- **Neutrality**: Rules apply equally to all parties and politicians
- **Privacy**: Only public parliamentary data is analyzed
- **Objectivity**: Thresholds based on statistical analysis, not political bias
- **Accountability**: Results are verifiable against public records

### Data Sources

Rules leverage the following data models:

- `ViewRiksdagenPolitician`: Politician profile and career data
- `ViewRiksdagenPartySummary`: Party-level aggregated data
- `ViewRiksdagenVoteDataBallotPoliticianSummaryDaily`: Daily voting summaries
- `ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly`: Monthly voting summaries
- `ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual`: Annual voting summaries
- `ViewRiksdagenVoteDataBallotPartySummaryDaily`: Party daily voting summaries
- `ViewRiksdagenVoteDataBallotPartySummaryMonthly`: Party monthly voting summaries
- `ViewRiksdagenVoteDataBallotPartySummaryAnnual`: Party annual voting summaries

---

## Usage

These rules are automatically evaluated by the CIA compliance checking framework. Results are stored as `RuleViolation` entities and can be accessed through:

1. **Web UI**: Politician and party profile pages display rule violations
2. **API**: Compliance check endpoints return violations
3. **Reports**: Automated reports summarize violations
4. **Dashboards**: Visual representations of risk indicators

## Future Enhancements

Potential areas for expansion:

1. **Predictive Analytics**: Use historical violation patterns to forecast trends
2. **Sentiment Analysis**: Incorporate media coverage and public opinion
3. **Network Analysis**: Map political alliances and influence networks
4. **Economic Impact**: Correlate political behavior with economic outcomes
5. **Regional Analysis**: Track regional representation effectiveness
6. **Committee Specialization**: Assess expertise development in committees

---

## Ministry Risk Rules (Added 2025-11-07)

### 10. MinistryLowProductivity.drl
**Purpose**: Tracks ministry-level legislative and document productivity.

**Rules**:
- **Low document productivity last year - below 10** (MINOR, salience 10)
  - Condition: `documentsLastYear < 10 && > 0`
  - Category: Behavior
  - Resource Tag: LowDocumentOutput

- **No documents last year** (MAJOR, salience 50)
  - Condition: `documentsLastYear == 0`
  - Category: Behavior
  - Resource Tag: NoDocumentOutput

- **Very low average documents per member - below 3** (CRITICAL, salience 100)
  - Condition: `currentMemberSize > 0 && avgDocumentsPerMember < 3`
  - Category: Behavior
  - Resource Tag: ChronicallyLowProductivity

**Intelligence Value**: Identifies ministries that are not actively producing legislative documents, indicating potential government ineffectiveness or lack of policy initiative.

---

### 11. MinistryInactiveLegislation.drl
**Purpose**: Monitors ministry legislative output - government bills and propositions.

**Rules**:
- **Few government bills - below 2** (MINOR, salience 10)
  - Condition: `totalGovernmentBills < 2 && > 0`
  - Category: Behavior
  - Resource Tag: FewGovernmentBills

- **No government bills** (MAJOR, salience 50)
  - Condition: `totalGovernmentBills == 0`
  - Category: Behavior
  - Resource Tag: NoGovernmentBills

- **No propositions** (CRITICAL, salience 100)
  - Condition: `totalPropositions == 0`
  - Category: Behavior
  - Resource Tag: NoPropositions

**Intelligence Value**: Tracks government legislative initiative, identifying ministries that are not fulfilling their legislative mandate to propose new laws and policies.

---

### 12. MinistryUnderstaffed.drl
**Purpose**: Detects ministries with insufficient staffing levels.

**Rules**:
- **Small member size - fewer than 3** (MINOR, salience 10)
  - Condition: `currentMemberSize > 0 && < 3`
  - Category: Structure
  - Resource Tag: SmallTeam

- **Single member - critically understaffed** (MAJOR, salience 50)
  - Condition: `currentMemberSize == 1`
  - Category: Structure
  - Resource Tag: SingleMember

- **No members - vacant ministry** (CRITICAL, salience 100)
  - Condition: `currentMemberSize == 0`
  - Category: Structure
  - Resource Tag: VacantMinistry

**Intelligence Value**: Identifies organizational capacity issues that may prevent ministries from effectively executing their mandate, indicating potential government dysfunction.

---

## Committee Risk Rules (Added 2025-11-07)

### 13. CommitteeLowProductivity.drl
**Purpose**: Tracks committee-level legislative and document productivity.

**Rules**:
- **Low document productivity last year - below 20** (MINOR, salience 10)
  - Condition: `documentsLastYear < 20 && > 0`
  - Category: Behavior
  - Resource Tag: LowDocumentOutput

- **No documents last year** (MAJOR, salience 50)
  - Condition: `documentsLastYear == 0`
  - Category: Behavior
  - Resource Tag: NoDocumentOutput

- **Very low average documents per member - below 2** (CRITICAL, salience 100)
  - Condition: `currentMemberSize > 0 && avgDocumentsPerMember < 2`
  - Category: Behavior
  - Resource Tag: ChronicallyLowProductivity

**Intelligence Value**: Identifies underperforming committees that are not actively contributing to the legislative process through detailed policy work and proposals.

---

### 14. CommitteeLeadershipVacancy.drl
**Purpose**: Detects committees with leadership and staffing deficiencies.

**Rules**:
- **No leadership positions** (MINOR, salience 10)
  - Condition: `currentMemberSize > 0 && currentLeadershipPositions == 0`
  - Category: Structure
  - Resource Tag: NoLeadership

- **Leadership but few members - below 5** (MAJOR, salience 50)
  - Condition: `currentLeadershipPositions > 0 && currentMemberSize < 5`
  - Category: Structure
  - Resource Tag: Understaffed

- **No regular members** (CRITICAL, salience 100)
  - Condition: `currentRegularMembers == 0`
  - Category: Structure
  - Resource Tag: NoRegularMembers

**Intelligence Value**: Tracks committee organizational health, identifying structural problems that may prevent committees from functioning effectively.

---

### 15. CommitteeInactivity.drl
**Purpose**: Monitors committee engagement through motions and follow-up activity.

**Rules**:
- **Few committee motions - below 5** (MINOR, salience 10)
  - Condition: `totalCommitteeMotions < 5 && > 0`
  - Category: Behavior
  - Resource Tag: FewCommitteeMotions

- **No committee motions** (MAJOR, salience 50)
  - Condition: `totalCommitteeMotions == 0`
  - Category: Behavior
  - Resource Tag: NoCommitteeMotions

- **No follow-up motions despite activity** (CRITICAL, salience 100)
  - Condition: `totalFollowUpMotions == 0 && totalCommitteeMotions > 10`
  - Category: Behavior
  - Resource Tag: NoFollowUpEngagement

**Intelligence Value**: Identifies committees that lack sustained policy engagement, indicating lack of accountability or follow-through on legislative initiatives.

---

## Updated Data Sources

### Ministry Data
- `ViewRiksdagenMinistry`: Ministry profile and productivity data
  - totalDocuments, documentsLastYear, avgDocumentsPerMember
  - totalPropositions, totalGovernmentBills
  - currentMemberSize, totalDaysServed, activityLevel

### Committee Data
- `ViewRiksdagenCommittee`: Committee profile and activity data
  - totalDocuments, documentsLastYear, avgDocumentsPerMember
  - totalCommitteeMotions, totalFollowUpMotions
  - currentMemberSize, currentRegularMembers
  - totalLeadershipPositions, currentLeadershipPositions
  - totalSubstitutePositions, currentSubstitutePositions, activityLevel

---

## Updated Statistics

**Total Rules**: 35 (up from 29)
- **Politician Rules**: 20 files
- **Party Rules**: 6 files
- **Ministry Rules**: 3 files (NEW)
- **Committee Rules**: 3 files (NEW)
- **Other Rules**: 3 files (application/user)

**Coverage**: ~85% of available database views utilized for risk assessment
- Politician analysis: ✓ Complete
- Party analysis: ✓ Complete
- Ministry analysis: ✓ Complete (NEW)
- Committee analysis: ✓ Complete (NEW)
- Coalition analysis: Partial (future enhancement)
- Weekly trends: Not implemented (future enhancement)

**New Compliance Check Implementations**: 
- MinistryComplianceCheckImpl (NEW)
- CommitteeComplianceCheckImpl (NEW)

---

## Risk Score Threshold Analysis

### Overview

Politician risk scores range from 0-100 based on multiple weighted components. The risk classification thresholds determine how politicians are categorized into LOW, MEDIUM, HIGH, and CRITICAL risk categories.

### Risk Score Calculation (0-100 scale)

Risk scores are calculated by summing weighted components:

| Component | Weight | Max Points | Description |
|-----------|--------|------------|-------------|
| **Violations** | 2 points each | 40 | Rule violations detected (max 20 violations) |
| **Absence Rate** | Percentage-based | 20 | Annual parliamentary absence percentage |
| **Ineffectiveness** | Inverse percentage | 20 | Based on voting win rate (100 - win_percentage) |
| **Rebel Rate** | Percentage-based | 10 | Party discipline violations percentage |
| **Low Productivity** | Binary | 10 | Triggered if <5 documents last year |
| **Total Maximum** | | **100** | Theoretical maximum score |

**Formula:**
```
risk_score = (violations × 2, max 40) + 
             (absence_rate × 0.20) + 
             ((100 - win_rate) × 0.20) + 
             (rebel_rate × 0.10) + 
             (documents < 5 ? 10 : 0)
```

### Threshold Evolution

#### Previous Thresholds (v1.32 - v1.47)

| Risk Level | Threshold | Observed Distribution | Issue |
|------------|-----------|----------------------|-------|
| CRITICAL | ≥70 | 0 politicians (0%) | Threshold too high |
| HIGH | ≥50 | 24 politicians (6%) | Underpopulated |
| MEDIUM | ≥30 | 330 politicians (82.5%) | **Overclustered** |
| LOW | <30 | 46 politicians (11.5%) | Underpopulated |

**Problem Identified:**
- 82.5% overclustering in MEDIUM category reduced analytical differentiation
- Only 6% HIGH risk despite multiple politicians showing elevated risk indicators
- Observed score range in practice: 10-56 points (not reaching theoretical maximum)

#### Current Thresholds (v1.48+)

| Risk Level | Threshold | Target Distribution | Rationale |
|------------|-----------|---------------------|-----------|
| CRITICAL | ≥65 | <1% (rare extremes) | 12+ violations + high absence |
| HIGH | ≥45 | 15-25% (60-100) | 8-12 violations or high absence+low effectiveness |
| MEDIUM | ≥25 | 50-60% (200-240) | 4-7 violations or moderate concerns |
| LOW | <25 | 25-35% (100-140) | 0-3 violations, standard performance |

### Threshold Adjustment Rationale

**Statistical Analysis:**

1. **Observed Score Distribution** (400 politician sample):
   - Range: 10-56 points
   - Median: 39 points
   - 75th percentile: 44 points
   - 90th percentile: 50 points

2. **Score Component Contributions** (typical distributions):
   - Violations: 0-16 points (0-8 violations most common)
   - Absence: 0-3 points (0-15% absence typical)
   - Ineffectiveness: 6-14 points (30-70% win rates typical)
   - Rebel: 0-0.5 points (0-5% rebel rates typical)
   - Productivity: 0 or 10 points (binary threshold at 5 docs)

3. **Evidence-Based Adjustments**:
   - **HIGH threshold lowered to 45**: Captures politicians at 75th percentile and above
   - **MEDIUM threshold lowered to 25**: Redistributes mid-range (25-44) more evenly
   - **LOW category expanded**: Politicians with 10-24 points show minimal risk indicators
   - **CRITICAL threshold lowered to 65**: Aligns with extreme cases (rare but possible)

### Validation Metrics

To validate threshold effectiveness, monitor these metrics:

1. **Distribution Balance**: Target 20/60/20 distribution (±5%)
2. **Gini Coefficient**: <0.4 for improved evenness
3. **False Positive Rate**: Should not increase >5%
4. **Analytical Utility**: HIGH category should be actionable (60-100 politicians)

### Intelligence Products Impact

The rebalanced thresholds improve:

1. **Political Scorecards**: Better differentiation of performance levels
2. **Risk Dashboards**: More granular risk monitoring
3. **Resource Allocation**: Clearer prioritization for accountability reviews
4. **Trend Analysis**: More sensitive detection of changing risk profiles
5. **Accountability Metrics**: Better alignment with actual behavior patterns

### Monitoring and Adjustment

Risk thresholds should be reviewed periodically (quarterly) to ensure:

- Distribution remains balanced as data evolves
- Score components reflect actual behavior patterns
- Thresholds align with political context changes
- No drift toward over/under-classification

**Changelog:**
- **v1.48 (2026-01-09)**: Rebalanced thresholds to address 82.5% MEDIUM overclustering
  - CRITICAL: 70→65
  - HIGH: 50→45
  - MEDIUM: 30→25
  - LOW: <30→<25
