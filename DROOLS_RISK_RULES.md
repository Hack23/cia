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
- **Moderate combined risk** (MINOR, salience 20)
  - Condition: `politicianPercentageAbsent >= 12 && < 17 && wonPercentage < 35`
  - Category: Behavior
  - Resource Tag: ModerateParticipationRisk
  - Statistical Basis: ~P50 absence (actual P50=12.5%, rounded to 12%) + below-P25 effectiveness (35%)

- **Combined low participation** (MAJOR, salience 50)
  - Condition: `politicianPercentageAbsent >= 17 && < 25 && wonPercentage < 30`
  - Category: Behavior
  - Resource Tag: LowParticipationAndEffectiveness
  - Statistical Basis: Above-average absence (17%) + low effectiveness

- **High combined risk** (MAJOR, salience 75)
  - Condition: `politicianPercentageAbsent >= 25 && < 55 && wonPercentage < 25`
  - Category: Behavior
  - Resource Tag: HighCombinedRisk
  - Statistical Basis: ~P75 absence (actual P75=24.6%, rounded to 25%) + below-P10 effectiveness

- **Extreme combined risk** (CRITICAL, salience 105)
  - Condition: `politicianPercentageAbsent >= 55 && wonPercentage < 20`
  - Category: Behavior
  - Resource Tag: ExtremeLowParticipationAndEffectiveness
  - Statistical Basis: ~P90 absence (actual P90=54.5%, rounded to 55%) + very low effectiveness

- **High abstention rate** (MINOR, salience 10)
  - Condition: `politicianPercentageAbstain > 9`
  - Category: Behavior
  - Resource Tag: HighAbstentionRate
  - Statistical Basis: ~P75 abstention (actual P75=9.1%, operationalized as 9% threshold)

**Intelligence Value**: Identifies compounded risks where politicians are both absent frequently and ineffective when present, indicating serious accountability concerns.

**Statistical Justification** (Based on 500-politician sample analysis):
- Absence Rate Distribution: Mean=19.8%, Median=12.5%, P75=24.6%, P90=54.5%
- Won Percentage Distribution: Mean=55.9%, Median=52.4%, P25=35.5%, P10=21.5%
- Thresholds align with behavioral clustering: STANDARD_BEHAVIOR (~14% absence), MODERATE_RISK (~16% absence)

---

### 6. PoliticianLazy.drl (Enhanced with Statistical Thresholds)
**Purpose**: Detects excessive absenteeism from parliamentary votes with finer-grained risk levels.

**Enhanced Rules**:
- **Absent 100% last day** (MINOR, salience 10)
  - Daily binary absence detection
  
- **Absent ≥18% last month** (MAJOR, salience 45)
  - Monthly threshold adjusted from 20% to 18%
  
- **Moderate annual absence: 12-17%** (MINOR, salience 15)
  - Condition: `politicianPercentageAbsent >= 12 && < 17`
  - Resource Tag: ModerateAbsenteeism
  - Statistical Basis: ~P50-P60 range (actual P50=12.5%, rounded to 12%), captures 14-16% behavioral clustering

- **Concerning annual absence: 17-25%** (MAJOR, salience 55)
  - Condition: `politicianPercentageAbsent >= 17 && < 25`
  - Resource Tag: ConcerningAbsenteeism
  - Statistical Basis: P60-P75 range
  
- **High annual absence: 25-55%** (CRITICAL, salience 100)
  - Condition: `politicianPercentageAbsent >= 25 && < 55`
  - Resource Tag: HighAbsenteeism
  - Statistical Basis: ~P75-P90 range (actual P75=24.6%, P90=54.5%, rounded to 25% and 55%)
  
- **Extreme annual absence: ≥55%** (CRITICAL, salience 150)
  - Condition: `politicianPercentageAbsent >= 55`
  - Resource Tag: ExtremeAbsenteeism
  - Statistical Basis: P90+ (top 10% most absent)

**Enhancement Rationale**: 
- Previous thresholds (20%, 30%) were too coarse to capture the 14-16% absence clustering observed across risk categories
- New thresholds align with statistical percentiles from 500-politician sample analysis, with intentional rounding for operational clarity (P50=12.5%→12%, P75=24.6%→25%, P90=54.5%→55%)
- Captures nuanced differences between STANDARD_BEHAVIOR (14% absence) and MODERATE_RISK (16% absence) categories
- Provides progressive risk escalation: 12% → 17% → 25% → 55%
- Mutually exclusive threshold bands prevent duplicate violations at boundaries

**Statistical Basis**:
- Sample Size: 500 active politicians (>50 votes)
- Absence Distribution: P25=5.6%, P50=12.5%, P75=24.6%, P90=54.5%
- Behavioral Clustering: STANDARD=14.1%, MODERATE=16.0%
- Monthly threshold (18%) derived from proportional scaling of annual P50

---

### 7. PoliticianAbstentionPattern.drl (Enhanced with Statistical Thresholds)
**Purpose**: Detects strategic and excessive abstention patterns in parliamentary voting.

**Enhanced Rules**:
- **Concerning abstention pattern: 6-9%** (MAJOR, salience 50)
  - Condition: `politicianPercentageAbstain >= 6 && < 9`
  - Resource Tag: FrequentAbstention
  - Statistical Basis: Above typical range, approaching P75 (actual P75=9.1%, operationalized as 9%)

- **High abstention pattern: 9-13%** (MAJOR, salience 75)
  - Condition: `politicianPercentageAbstain >= 9 && < 13`
  - Resource Tag: HighAbstention
  - Statistical Basis: ~P75-P90 range (actual P75=9.1%, P90=13.2%, operationalized as 9%-13%)

- **Critical abstention pattern: ≥13%** (CRITICAL, salience 100)
  - Condition: `politicianPercentageAbstain >= 13`
  - Resource Tag: ExcessiveAbstention
  - Statistical Basis: ~P90+ (actual P90=13.2%, operationalized as 13% threshold for top 10% most abstaining)

- **Strategic abstention with high presence** (MAJOR, salience 60)
  - Condition: `politicianPercentageAbstain >= 8 && politicianPercentageAbsent < 10 && totalVotes > 50`
  - Resource Tag: StrategicAbstentionPresent
  - Detects politicians who attend but deliberately abstain

- **Indecisive behavior** (MAJOR, salience 55)
  - Condition: `politicianPercentageAbstain >= 7 && wonPercentage >= 25 && < 40`
  - Resource Tag: IndecisiveBehavior
  - Identifies politicians with moderate effectiveness who frequently abstain

**Intelligence Value**: Abstention can indicate strategic positioning, internal party conflicts, or uncertainty about policy positions. High abstention despite attendance suggests deliberate non-commitment rather than mere absence.

**Statistical Basis**:
- Sample Size: 500 active politicians (>50 votes)
- Abstention Distribution: Mean=4.9%, Median=2.3%, P75=9.1%, P90=13.2%, P95=14.5%
- Previous threshold (10%) was at P83, missing 75-83rd percentile range
- New thresholds: 6% (above typical), 9% (P75), 13% (P90)

---

## Party Risk Rules

### 8. PartyHighAbsenteeism.drl
**Purpose**: Enhanced party-level absence tracking across multiple timeframes.

**Rules** (Calibrated based on actual party performance data):
- **Concerning daily absenteeism - 17%+ daily** (MINOR, salience 10)
  - Condition: `dailySummary.partyPercentageAbsent >= 17`
  - Category: Behavior
  - Resource Tag: HighDailyAbsenteeism
  - **Threshold Justification**: Above maximum observed (16.01%). Set at 17% to capture outlier daily spikes above typical 14-16% range.

- **High monthly absenteeism - 16.5%+ monthly** (MAJOR, salience 50)
  - Condition: `monthlySummary.partyPercentageAbsent >= 16.5`
  - Category: Behavior
  - Resource Tag: HighMonthlyAbsenteeism
  - **Threshold Justification**: Above P75 (15.99%) and near maximum observed (16.01%). Captures sustained high absence over a month.

- **Chronic absenteeism - 16%+ annually** (CRITICAL, salience 100)
  - Condition: `annualSummary.partyPercentageAbsent >= 16`
  - Category: Behavior
  - Resource Tag: ChronicAbsenteeism
  - **Threshold Justification**: Near maximum observed (16.01%). Marks consistently worse attendance than typical 14-15% across full year.

**Intelligence Value**: Tracks party-wide attendance patterns that may indicate organizational problems, coalition stress, or strategic abstention campaigns. Thresholds calibrated to Swedish parliamentary behavior where all parties show 12-16% absence rates.

**Data Distribution**: Min: 12.32%, P25: 14.07%, Median: 14.46%, P75: 15.99%, Max: 16.01%

---

### 8. PartyLowCollaboration.drl
**Purpose**: Tracks parties that avoid cross-party collaboration.

**Rules** (Calibrated based on actual collaboration patterns):
- **Low average collaboration - below 1.6%** (MINOR, salience 10)
  - Condition: `avgCollaborationPercentage < 1.6 && >= 1.0`
  - Category: Behavior
  - Resource Tag: LowCrossPartyEngagement
  - **Threshold Justification**: P25 = 1.60%. Marks bottom quartile of cross-party engagement.

- **Very low average collaboration - below 1.0%** (MAJOR, salience 50)
  - Condition: `avgCollaborationPercentage < 1.0 && >= 0.5`
  - Category: Behavior
  - Resource Tag: VeryLowCrossPartyEngagement
  - **Threshold Justification**: Near minimum observed (S at 0.80%). Indicates notable isolation.

- **Minimal collaboration - below 0.5%** (CRITICAL, salience 100)
  - Condition: `avgCollaborationPercentage < 0.5 && > 0`
  - Category: Behavior
  - Resource Tag: MinimalCrossPartyEngagement
  - **Threshold Justification**: Extreme isolation, below all observed parties.

- **No highly collaborative members** (CRITICAL, salience 100)
  - Condition: `currentlyActiveMembers > 5 && highlyCollaborativeMembers == 0`
  - Category: Behavior
  - Resource Tag: NoCollaborativeMembers

- **Low collaborative motions ratio** (MINOR, salience 10)
  - Condition: `totalDocuments > 50 && totalCollaborativeMotions < (totalDocuments * 0.05)`
  - Category: Behavior
  - Resource Tag: FewCollaborativeMotions

**Intelligence Value**: Identifies parties that operate in isolation. Thresholds reflect Swedish political reality where collaboration rates range 0.8-2.9%, far lower than international assumptions of 10-15%.

**Data Distribution**: Min: 0.80%, P25: 1.60%, Median: 1.85%, P75: 2.40%, Max: 2.90%

---

### 9. PartyLowEffectiveness.drl
**Purpose**: Party-level effectiveness tracking combining win rates and productivity.

**Rules** (Calibrated based on actual party performance data):
- **Low annual win rate - below 45%** (MINOR, salience 10)
  - Condition: `partyWonPercentage < 45 && >= 35`
  - Category: Behavior
  - Resource Tag: LowWinRate
  - **Threshold Justification**: Anchored at P25 = 38.68%, but threshold set at 45% as strategic boundary to distinguish normal opposition parties (45-55%) from low-performing ones (<45%).

- **Very low annual win rate - below 35%** (MAJOR, salience 50)
  - Condition: `partyWonPercentage < 35 && >= 25`
  - Category: Behavior
  - Resource Tag: VeryLowWinRate
  - **Threshold Justification**: Captures consistently low performers (V: 31.33%, MP: 31.29%). Below typical opposition range.

- **Critically low annual win rate - below 25%** (CRITICAL, salience 100)
  - Condition: `partyWonPercentage < 25`
  - Category: Behavior
  - Resource Tag: CriticallyLowWinRate
  - **Threshold Justification**: Observed P10 = 28.88%. The CRITICAL threshold is intentionally set below this (at 25%) to capture only extreme, sub-P10 marginalization cases performing far worse than even weak opposition parties.

- **Low document productivity - below 18 docs per member** (MINOR, salience 10)
  - Condition: `currentlyActiveMembers > 0 && avgDocumentsLastYear < 18 && > 0`
  - Category: Behavior
  - Resource Tag: LowDocumentProductivity
  - **Threshold Justification**: P25 = 17.58 docs/member. Captures L (13.62), SD (15.12), KD (17.58) who are notably less productive.

**Intelligence Value**: Measures party-level political effectiveness and legislative productivity. Thresholds distinguish government coalition parties (70-86% win rates) from opposition (45-55%) and marginalized parties (<35%).

**Data Distribution**:
- Win Rates: P25: 38.68%, Median: 59.42%, P75: 82.48%
- Productivity: Min: 13.62, P25: 17.58, Median: 25.71, P75: 37.24, Max: 57.19 docs/member

---

### 10. PartyCombinedRisk.drl
**Purpose**: Identifies parties with multiple concurrent risk factors (low effectiveness + high absence + low productivity).

**Rules** (Calibrated to detect compound risk scenarios):
- **Combined low effectiveness and high absence** (CRITICAL, salience 100)
  - Condition: `partyWonPercentage >= 25 && < 30 && partyPercentageAbsent >= 16`
  - Category: Behavior
  - Resource Tag: LowEffectivenessHighAbsence
  - **Threshold Justification**: Win rate 25-30% (just above CRITICAL standalone threshold) combined with annual absence at maximum observed (16%). Avoids overlap with standalone CRITICAL win rate rule (<25%).

- **Low win rate and low document productivity** (MAJOR, salience 75)
  - Condition: `partyWonPercentage < 35 && avgDocumentsLastYear < 18`
  - Category: Behavior
  - Resource Tag: LowWinRateLowProductivity
  - **Threshold Justification**: MAJOR win rate threshold (35%) combined with P25 productivity (18 docs/member).

- **Triple risk - low effectiveness, high absence, low productivity** (CRITICAL, salience 150)
  - Condition: `partyWonPercentage < 30 && partyPercentageAbsent >= 16 && avgDocumentsLastYear < 18`
  - Category: Behavior
  - Resource Tag: TripleRiskProfile
  - **Threshold Justification**: Combines MAJOR/CRITICAL thresholds across three dimensions. Uses P25 productivity (18) for consistency.

- **High abstention indicating indecision** (MAJOR, salience 50)
  - Condition: `partyPercentageAbstain >= 6 && partyWonPercentage < 45`
  - Category: Behavior
  - Resource Tag: HighAbstentionLowEffectiveness

**Intelligence Value**: Detects systemic party dysfunction where multiple risk factors compound. Triple risk pattern indicates parties facing serious organizational or strategic challenges.

---

### 11. PartyDecliningPerformance.drl
**Purpose**: Identifies parties with deteriorating performance trends over time.

**Rules** (Calibrated to detect temporal decline patterns):
- **Declining performance - monthly win rate significantly lower than annual** (MAJOR, salience 50)
  - Condition: `monthlySummary.partyWonPercentage < annualSummary.partyWonPercentage - 12 && monthlySummary.partyWonPercentage < 45`
  - Category: Behavior
  - Resource Tag: DecreasingWinRate
  - **Threshold Justification**: 12 percentage point drop indicates significant recent decline.

- **Worsening absenteeism - monthly absence exceeds annual by 8%+** (MAJOR, salience 50)
  - Condition: `monthlySummary.partyPercentageAbsent > annualSummary.partyPercentageAbsent + 8`
  - Category: Behavior
  - Resource Tag: WorseningAbsenteeism

- **Disorganization - high daily absence with significant variation** (MAJOR, salience 75)
  - Condition: `dailySummary.partyPercentageAbsent >= 17 && dailySummary.partyPercentageAbsent >= monthlySummary.partyPercentageAbsent + 3`
  - Category: Behavior
  - Resource Tag: InconsistentAttendance
  - **Threshold Justification**: Daily absence spike (17%+) at least 3 points above monthly average indicates erratic attendance patterns.

- **Critical combined decline - effectiveness and participation dropping** (CRITICAL, salience 100)
  - Condition: `monthlySummary.partyWonPercentage < 30 && monthlySummary.partyPercentageAbsent >= 16 && annualSummary.partyWonPercentage >= 45`
  - Category: Behavior
  - Resource Tag: CriticalCombinedDecline
  - **Threshold Justification**: Detects parties with historically normal performance (annual >= 45%) experiencing sudden decline (monthly < 30% win rate + 16% absence).

**Intelligence Value**: Early warning system for parties experiencing performance deterioration. Distinguishes temporary fluctuations from systematic decline.

---

### 12. PartyInconsistentBehavior.drl
**Purpose**: Identifies parties with erratic or unpredictable voting and attendance patterns.

**Rules** (Calibrated to detect variance and inconsistency):
- **Inconsistent voting - high daily abstention variance** (MAJOR, salience 50)
  - Condition: `dailySummary.partyPercentageAbstain >= 8 && annualSummary.partyPercentageAbstain >= 4`
  - Category: Behavior
  - Resource Tag: HighAbstentionVariance

- **Erratic performance - significant daily/monthly effectiveness gaps** (MAJOR, salience 75)
  - Condition: `dailySummary.partyWonPercentage < monthlySummary.partyWonPercentage - 20 && monthlySummary.partyWonPercentage < annualSummary.partyWonPercentage - 12`
  - Category: Behavior
  - Resource Tag: ErraticEffectiveness

- **Discipline problems - high absence variation** (MAJOR, salience 60)
  - Condition: `dailySummary.partyPercentageAbsent >= 17 && monthlySummary.partyPercentageAbsent < 16 && dailySummary.partyPercentageAbsent > monthlySummary.partyPercentageAbsent + 2`
  - Category: Behavior
  - Resource Tag: DisciplineProblems
  - **Threshold Justification**: High daily absence (17%) contrasted with lower monthly average (<16%) indicates inconsistent party discipline.

- **Unstable coalition behavior - recent performance drop** (CRITICAL, salience 100)
  - Condition: `monthlySummary.partyWonPercentage < 25 && annualSummary.partyWonPercentage >= 35 && monthlySummary.partyPercentageAbsent >= 16`
  - Category: Behavior
  - Resource Tag: UnstableCoalitionBehavior

**Intelligence Value**: Detects parties experiencing internal turmoil, coalition stress, or strategic confusion. Inconsistent patterns may indicate leadership challenges or factional divisions.

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

## Ministry Risk Rules (Added 2025-11-07, Updated 2026-01-09)

### Statistical Validation Summary (2026-01-09)
Ministry risk thresholds have been validated against actual ministry performance distributions from sample data analysis (2023-2025):

**Documents Per Ministry Per Year:**
- Mean: 10.4 docs/year
- Median (P50): 5.0 docs/year
- P25 (25th percentile): 3.0 docs/year
- P75 (75th percentile): 15.0 docs/year
- P90 (90th percentile): 22.0 docs/year (using linear interpolation method)
- Range: 1-55 docs/year

**Propositions Per Ministry Per Year:**
- Mean: 10.4 propositions/year
- Median: 5.0 propositions/year
- P25: 3.0 propositions/year
- Note: Propositions track closely with documents in sample data

**Ministry Type Segmentation (2025 Data):**
Productivity varies significantly by portfolio type and policy mandate:

- **Major Policy Ministries** (Finance, Justice):
  - Mean: 46.0 docs/year, Median: 46.0, Range: 37-55
  - Highest legislative activity due to extensive policy portfolios
  - Note: Foreign Affairs (Utrikesdepartementet) traditionally a major ministry, but shows lower activity in sample period (9 docs in 2025, 5 in 2024), aligning more with smaller portfolios for this analysis period

- **Service Ministries** (Infrastructure, Environment, Social):
  - Mean: 21.0 docs/year, Median: 21.0, Range: 16-26
  - Moderate activity aligned with operational mandates

- **Smaller Portfolios** (Culture, Defense, Education, Labor, Cabinet):
  - Mean: 5.8 docs/year, Median: 4.0, Range: 3-10
  - Lower document volume normal for focused portfolios

**Threshold Validation:**
- **< 10 docs/year threshold**: Set slightly below mean (10.4), flags lower-performing ministries. Due to right-skewed distribution, ministries with 5-9 docs/year are above median (50th percentile) but below mean.
- **< 3 docs/member threshold**: Per-capita normalization accounts for staffing variations across ministry types
- **0 propositions threshold**: All active ministries in sample data (2025) produced propositions (Mean=10.4); zero propositions indicates complete legislative inactivity for measured period

**Key Insight:** Unlike committees with more uniform mandates, ministry productivity appropriately varies by portfolio size and policy area. Thresholds target below-average performance while recognizing that smaller portfolios (Culture, Education) naturally operate at lower document volumes (3-10 range) than major policy ministries (37-55 range).

---

### 10. MinistryLowProductivity.drl
**Purpose**: Tracks ministry-level legislative and document productivity.

**Rules** (Thresholds validated 2026-01-09):
- **Low document productivity last year - below 10** (MINOR, salience 10)
  - Condition: `documentsLastYear < 10 && > 0`
  - Category: Behavior
  - Resource Tag: LowDocumentOutput
  - **Statistical Basis**: Threshold slightly below mean (10.4 docs/year) flags lower-performing ministries. Due to right-skewed distribution, captures roughly bottom 60-65% of ministries. Smaller portfolios typically 3-10 range (normal variation), major policy ministries 37-55 range.

- **No documents last year** (MAJOR, salience 50)
  - Condition: `documentsLastYear == 0`
  - Category: Behavior
  - Resource Tag: NoDocumentOutput
  - **Statistical Basis**: Complete inactivity regardless of portfolio type

- **Very low average documents per member - below 3** (CRITICAL, salience 100)
  - Condition: `currentMemberSize > 0 && avgDocumentsPerMember < 3`
  - Category: Behavior
  - Resource Tag: ChronicallyLowProductivity
  - **Statistical Basis**: Per-capita productivity threshold. Normalizes across ministry sizes. Note: Detailed docs/member distribution analysis pending; threshold based on organizational capacity assessment.

**Intelligence Value**: Identifies ministries that are not actively producing legislative documents, indicating potential government ineffectiveness or lack of policy initiative.

---

### 11. MinistryInactiveLegislation.drl
**Purpose**: Monitors ministry legislative output - government bills and propositions.

**Rules** (Thresholds validated 2026-01-09):
- **Few government bills - below 2** (MINOR, salience 10)
  - Condition: `totalGovernmentBills < 2 && > 0`
  - Category: Behavior
  - Resource Tag: FewGovernmentBills
  - **Statistical Basis**: Bill frequency varies by legislative cycle and policy area; major policy ministries drive most legislative activity

- **No government bills** (MAJOR, salience 50)
  - Condition: `totalGovernmentBills == 0`
  - Category: Behavior
  - Resource Tag: NoGovernmentBills
  - **Statistical Basis**: Absence of bills indicates lack of legislative initiative

- **No propositions** (CRITICAL, salience 100)
  - Condition: `totalPropositions == 0`
  - Category: Behavior
  - Resource Tag: NoPropositions
  - **Statistical Basis**: All active ministries in 2025 sample data submitted propositions (Mean=10.4); zero propositions for that period indicates complete legislative inactivity. Note: Only validated for 2025; historical years (2023-2024) require separate validation.

**Intelligence Value**: Tracks government legislative initiative, identifying ministries that are not fulfilling their legislative mandate to propose new laws and policies.

---

### 12. MinistryUnderstaffed.drl
**Purpose**: Detects ministries with insufficient staffing levels.

**Rules** (Thresholds validated 2026-01-09):
- **Small member size - fewer than 3** (MINOR, salience 10)
  - Condition: `currentMemberSize > 0 && < 3`
  - Category: Structure
  - Resource Tag: SmallTeam
  - **Statistical Basis**: Some smaller portfolios naturally have compact teams (Minister + 1-2 state secretaries). Note: currentMemberSize in data model represents active political appointees tracked in the system, not full ministry staff.

- **Single member - critically understaffed** (MAJOR, salience 50)
  - Condition: `currentMemberSize == 1`
  - Category: Structure
  - Resource Tag: SingleMember
  - **Statistical Basis**: Single-member ministries lack adequate organizational capacity; normal structure includes Minister + state secretaries. Note: currentMemberSize reflects tracked political appointees, not full administrative staff.

- **No members - vacant ministry** (CRITICAL, salience 100)
  - Condition: `currentMemberSize == 0`
  - Category: Structure
  - Resource Tag: VacantMinistry
  - **Statistical Basis**: Vacant positions indicate organizational dysfunction or restructuring requiring immediate attention

**Intelligence Value**: Identifies organizational capacity issues that may prevent ministries from effectively executing their mandate, indicating potential government dysfunction.

---

## Committee Risk Rules (Added 2025-11-07, Updated 2026-01-09)

### Statistical Validation Summary (2026-01-09)
Committee risk thresholds have been validated against actual committee performance distributions from sample data analysis:

**Documents Per Year Distribution (Annualized from Quarterly Data):**
- P25 (25th percentile): 108 docs/year
- P50 (Median): 260 docs/year  
- P75 (75th percentile): 1,188 docs/year
- Mean: 714.9 docs/year

**Operational Threshold Note:** The committee low-document-activity rule currently uses an 80 docs/year threshold on `documentsLastYear`. The empirical P25 is 108 docs/year (as shown above), and the code comment `P25 ~108 annualized` refers to this percentile. The operational 80-docs/year threshold is intentionally set below P25 to avoid over-flagging borderline committees while still capturing clearly low-activity cases.

**Documents Per Member (Quarterly):**
- P25: 1.34 docs/member/quarter
- Median: 1.79 docs/member/quarter
- Mean: 1.91 docs/member/quarter

**Total Committee Motions (Cumulative Historical):**
- P25: 81 total motions
- Median: 1,815 total motions
- 25% of committees: < 100 total motions

**Key Insight:** Database view `view_riksdagen_committee` uses:
- `documentsLastYear`: Annual metric (last 12 months)
- `totalCommitteeMotions`: Cumulative total across all time
- `avgDocumentsPerMember`: Cumulative average (total docs / total members ever)

---

### 13. CommitteeLowProductivity.drl
**Purpose**: Tracks committee-level legislative and document productivity using annual document output.

**Rules** (Thresholds validated 2026-01-09):
- **Low document productivity last year - below 80** (MINOR, salience 10)
  - Condition: `documentsLastYear >= 40 && documentsLastYear < 80`
  - Category: Behavior
  - Resource Tag: LowDocumentOutput
  - **Statistical Basis**: Captures approximately bottom 20% of committees (P25 ~108, adjusted to 80)

- **Very low document productivity last year - below 40** (MAJOR, salience 50)
  - Condition: `documentsLastYear > 0 && documentsLastYear < 40 && currentMemberSize < 10`
  - Category: Behavior
  - Resource Tag: VeryLowDocumentOutput
  - **Statistical Basis**: Critically low; operational threshold chosen well below P25 (~108 docs/year) to flag extreme underperformance. Excludes large committees (≥ 10 members) which are handled by the size-specific rule in CommitteeStagnation.drl with higher salience.

- **No documents last year** (CRITICAL, salience 100)
  - Condition: `documentsLastYear == 0`
  - Category: Behavior
  - Resource Tag: NoDocumentOutput
  - **Statistical Basis**: Complete inactivity indicates structural dysfunction

**Changes from Original:**
- MINOR threshold increased from < 20 to < 80 (original captured only 2.4% of committees)
- Added intermediate MAJOR rule for < 40 docs/year  
- Removed avgDocumentsPerMember CRITICAL rule (moved to CommitteeStagnation)
- Thresholds now aligned with P20-P25 percentiles of actual committee activity

**Intelligence Value**: Identifies underperforming committees that are not actively contributing to the legislative process through detailed policy work and proposals. Updated thresholds properly differentiate bottom-quartile performers from average committees.

---

### 14. CommitteeLeadershipVacancy.drl
**Purpose**: Detects committees with leadership and staffing deficiencies.

**Rules** (No changes - structurally validated 2026-01-09):
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

**Validation Notes**: No statistical distribution available in sample data for leadership position counts. Current thresholds appear structurally sound based on standard committee organization patterns. No changes recommended.

**Intelligence Value**: Tracks committee organizational health, identifying structural problems that may prevent committees from functioning effectively.

---

### 15. CommitteeInactivity.drl
**Purpose**: Monitors committee engagement through cumulative motion counts.

**Rules** (Thresholds validated 2026-01-09):
- **Few committee motions - below 100** (MINOR, salience 10)
  - Condition: `totalCommitteeMotions >= 10 && totalCommitteeMotions < 100`
  - Category: Behavior
  - Resource Tag: FewCommitteeMotions
  - **Statistical Basis**: P25 = 81 total motions, threshold set at 100 for clarity

- **Very few committee motions - below 10** (MAJOR, salience 50)
  - Condition: `totalCommitteeMotions > 0 && totalCommitteeMotions < 10`
  - Category: Behavior
  - Resource Tag: VeryFewCommitteeMotions
  - **Statistical Basis**: Severe lack of legislative initiative

- **No committee motions** (CRITICAL, salience 100)
  - Condition: `totalCommitteeMotions == 0`
  - Category: Behavior
  - Resource Tag: NoCommitteeMotions
  - **Statistical Basis**: Complete absence indicates fundamental inactivity

**Changes from Original:**
- MINOR threshold increased from < 5 to < 100 (cumulative totals require higher threshold)
- Added intermediate MAJOR rule for < 10 total motions
- Removed follow-up motion CRITICAL rule (data quality concerns)
- **Note**: totalCommitteeMotions is cumulative across committee lifetime, not annual

**Intelligence Value**: Identifies committees that lack sustained policy engagement over their operational history, indicating structural inactivity or minimal legislative contribution.

---

### 16. CommitteeStagnation.drl  
**Purpose**: Detects chronic underperformance through combined productivity metrics.

**Rules** (Thresholds validated 2026-01-09):
- **Chronic stagnation - very low average documents per member** (CRITICAL, salience 100)
  - Condition: `active && currentMemberSize > 0 && avgDocumentsPerMember < 1.0 && documentsLastYear > 0`
  - Category: Behavior
  - Resource Tag: ChronicLowProductivityPerMember
  - **Statistical Basis**: Below minimum observed in active committees

- **Declining output - significantly below historical** (MAJOR, salience 75)
  - Condition: `active && totalDocuments > 0 && documentsLastYear > 0 && avgDocumentsPerMember < 2.0 && documentsLastYear < (totalDocuments / 5)`
  - Category: Behavior
  - Resource Tag: SignificantOutputDecline
  - **Statistical Basis**: Current year < 20% of historical average

- **Minimal recent activity despite size** (MAJOR, salience 60)
  - Condition: `active && currentMemberSize >= 10 && documentsLastYear > 0 && documentsLastYear < 40`
  - Category: Behavior
  - Resource Tag: MinimalActivityDespiteSize
  - **Statistical Basis**: Large committees producing below 5th percentile output. Higher salience (60 vs 50) ensures this size-specific rule takes precedence over the general low productivity MAJOR rule for committees with 10+ members, avoiding duplicate violations while properly flagging underperformance in context of committee size.

- **Combined risk - low output and low per-member productivity** (CRITICAL, salience 125)
  - Condition: `active && currentMemberSize > 0 && documentsLastYear < 60 && documentsLastYear > 0 && avgDocumentsPerMember < 1.5`
  - Category: Behavior
  - Resource Tag: CombinedLowProductivity
  - **Statistical Basis**: Multiple indicators of chronic underperformance

**Changes from Original:**
- avgDocumentsPerMember thresholds tightened across rules:
  - Chronic stagnation (CRITICAL): `< 1.5` → `< 1.0`
  - Declining output (MAJOR): `< 3.0` → `< 2.0`
  - Combined risk (CRITICAL): `< 1.8` → `< 1.5`
- documentsLastYear threshold adjusted from < 15 to < 40 for large committees (matches general MAJOR threshold but with higher salience 60 vs 50 for precedence)
- Combined risk threshold increased from < 18 to < 60 docs/year
- Thresholds now account for difference between quarterly and cumulative metrics

**Intelligence Value**: Provides early warning system for committees experiencing chronic decline or systemic dysfunction, combining multiple indicators to reduce false positives.

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

Risk scores are calculated by summing weighted components (formula unchanged from v1.32-v1.47):

| Component | Weight | Max Points | Description |
|-----------|--------|------------|-------------|
| **Violations** | 2 points each | 40 | Rule violations detected (max 20 violations) |
| **Absence Rate** | 0.30 multiplier | 30 | Annual parliamentary absence percentage × 0.30 |
| **Rebel Rate** | 0.20 multiplier | 20 | Party discipline violations percentage × 0.20 |
| **Low Productivity** | Binary | 10 | Triggered if <5 documents last year |
| **Total Maximum** | | **100** | Theoretical maximum score |

**Formula (unchanged from v1.32-v1.47):**
```
risk_score = (violations × 2, max 40) + 
             (absence_rate × 0.30) + 
             (rebel_rate × 0.20) + 
             (documents < 5 ? 10 : 0)
```

**Note:** Only the classification thresholds changed in v1.48, not the calculation formula.

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

2. **Score Component Contributions** (typical distributions using v1.47 formula):
   - Violations: 0-16 points (0-8 violations most common)
   - Absence: 0-4.5 points (0-15% absence × 0.30 weight)
   - Rebel: 0-1.0 points (0-5% rebel × 0.20 weight)
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
- **v1.48 (2026-01-09)**: Rebalanced thresholds ONLY to address 82.5% MEDIUM overclustering
  - Risk calculation formula unchanged from v1.47
  - CRITICAL: 70→65, HIGH: 50→45, MEDIUM: 30→25, LOW: <30→<25
  - Data sources and filters unchanged from v1.47
