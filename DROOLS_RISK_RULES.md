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
