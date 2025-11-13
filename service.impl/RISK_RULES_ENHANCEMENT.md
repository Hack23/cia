# Risk Rules Enhancement Documentation

## Overview
This enhancement adds 10 new risk rule files with 42 new detection rules to improve the intelligence analysis capabilities of the Citizen Intelligence Agency platform.

## New Risk Categories

### Politician Risk Rules

#### 1. PoliticianDecliningEngagement.drl
**Purpose**: Detect deteriorating performance trends by comparing recent activity to historical patterns.

**Rules** (4):
- Declining engagement - monthly absence worse than annual (10%+ gap, MAJOR)
- Declining effectiveness - monthly win rate much lower than annual (15%+ drop, MAJOR)
- Disengagement pattern - high recent absence and abstention (15%+ absent, 8%+ abstain, CRITICAL)
- Increasing rebel behavior - monthly rebel rate exceeds annual (5%+ increase, MAJOR)

**Intelligence Value**: Identifies politicians whose performance is deteriorating, potentially indicating burnout, party conflicts, or disengagement.

#### 2. PoliticianCombinedRisk.drl
**Purpose**: Multi-factor risk assessment combining multiple negative indicators.

**Rules** (5):
- High risk: low effectiveness + high absence (CRITICAL)
- Rebel behavior + low effectiveness (MAJOR)
- Triple risk: high absence + low effectiveness + high rebel rate (CRITICAL)
- Inconsistent behavior: high rebel rate but high presence (MAJOR)
- Combined absence and abstention risk (MAJOR)

**Intelligence Value**: Provides comprehensive risk profiles by analyzing multiple behavioral factors simultaneously.

#### 3. PoliticianAbstentionPattern.drl
**Purpose**: Analyze voting abstention behavior as an indicator of indecision or strategic positioning.

**Rules** (4):
- Concerning abstention: 6-10% abstention rate (MAJOR)
- Critical abstention: 10%+ abstention rate (CRITICAL)
- Strategic abstention: high abstention + high presence (MAJOR)
- Indecision: high abstention + moderate effectiveness (MAJOR)

**Intelligence Value**: Abstention can indicate strategic positioning, indecision, or avoiding controversial votes.

#### 4. PoliticianLowEngagement.drl
**Purpose**: Identify minimal parliamentary engagement and avoidance patterns.

**Rules** (5):
- Minimal engagement: <100 votes annually + 15%+ absent (MAJOR)
- Critically low engagement: <50 votes annually (CRITICAL)
- Avoidance pattern: 25%+ combined absence + abstention (CRITICAL)
- Low impact: present but ineffective (<22% win rate despite presence, MAJOR)
- Marginal participation: <10 votes monthly + 30%+ absent (MAJOR)

**Intelligence Value**: Identifies politicians who are not effectively participating in parliamentary work.

### Party Risk Rules

#### 5. PartyDecliningPerformance.drl
**Purpose**: Detect declining party effectiveness and organizational issues.

**Rules** (4):
- Declining performance: monthly win rate 12%+ lower than annual (MAJOR)
- Worsening absenteeism: monthly absence 8%+ higher than annual (MAJOR)
- Disorganization: high daily and monthly absence variation (MAJOR)
- Critical combined decline: effectiveness and participation both dropping (CRITICAL)

**Intelligence Value**: Early warning of party organizational problems or coalition instability.

#### 6. PartyCombinedRisk.drl
**Purpose**: Comprehensive party risk assessment across multiple dimensions.

**Rules** (4):
- Low effectiveness + high absence (CRITICAL)
- Low win rate + low document productivity (MAJOR)
- Triple risk: low effectiveness + high absence + low productivity (CRITICAL)
- High abstention indicating indecision (MAJOR)

**Intelligence Value**: Holistic assessment of party performance and organizational health.

#### 7. PartyInconsistentBehavior.drl
**Purpose**: Identify erratic party behavior suggesting internal conflicts or coalition instability.

**Rules** (4):
- Inconsistent voting: high daily abstention variance (MAJOR)
- Erratic performance: significant effectiveness gaps (MAJOR)
- Discipline problems: high absence variation (MAJOR)
- Unstable coalition behavior: recent performance drop (CRITICAL)

**Intelligence Value**: Detects parties experiencing internal conflicts or coalition stress.

#### 8. PartyLowProductivity.drl
**Purpose**: Monitor legislative output and organizational effectiveness.

**Rules** (4):
- Low member productivity: <4 docs per member (MAJOR)
- Minimal legislative activity: <50 total docs for large parties (MAJOR)
- Organizational weakness: large party with <3 docs per member (CRITICAL)
- Zero recent output: no documents last year despite history (CRITICAL)

**Intelligence Value**: Assesses party capacity to produce legislation and influence policy.

### Committee Risk Rules

#### 9. CommitteeStagnation.drl
**Purpose**: Detect committee productivity decline and stagnation.

**Rules** (4):
- Chronic stagnation: <1.5 docs per member (CRITICAL)
- Declining output: current year significantly below historical (MAJOR)
- Minimal activity: <15 docs despite large membership (MAJOR)
- Combined risk: low total + low per-member productivity (CRITICAL)

**Intelligence Value**: Identifies non-functional or ineffective committees.

### Ministry Risk Rules

#### 10. MinistryStagnation.drl
**Purpose**: Monitor ministry legislative output and effectiveness.

**Rules** (4):
- Chronic stagnation: <2 docs per member (CRITICAL)
- Declining output: current year significantly below historical (MAJOR)
- Minimal activity: <8 docs for active ministry with 3+ members (MAJOR)
- Combined risk: low total + low per-member productivity (CRITICAL)

**Intelligence Value**: Assesses ministry capacity and identifies potential leadership issues.

## Technical Implementation

### Rule Engine
- **Technology**: Drools 7.x rule engine
- **Language**: Drools Rule Language (DRL)
- **Integration**: Spring Framework integration via RulesEngine service

### Severity Levels
- **MINOR**: Early warning, not immediately critical (salience: 10-50)
- **MAJOR**: Significant concern requiring attention (salience: 50-100)
- **CRITICAL**: Serious issue requiring immediate review (salience: 100-150)

### Salience Priority
Higher salience values execute first, ensuring critical rules are evaluated with priority.

## Intelligence Analysis Benefits

### 1. Trend Detection
Rules compare recent performance (daily/monthly) against historical performance (annual) to identify:
- Deteriorating engagement
- Declining effectiveness
- Increasing problematic behavior

### 2. Multi-Factor Assessment
Combined risk rules analyze multiple indicators simultaneously:
- Absence + Effectiveness + Rebel rate
- Productivity + Effectiveness + Attendance
- Abstention + Presence + Win rate

### 3. Behavioral Pattern Recognition
Rules identify specific behavioral patterns:
- Disengagement (withdrawal from participation)
- Strategic positioning (present but abstaining)
- Rebel behavior (voting against party)
- Avoidance (absence + abstention)

### 4. Organizational Health Monitoring
Party, committee, and ministry rules assess:
- Internal cohesion
- Leadership effectiveness
- Legislative productivity
- Coalition stability

## Data Sources

Rules leverage the following data models:
- `ViewRiksdagenPolitician`: Politician profile data
- `ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual/Monthly/Daily`: Voting behavior
- `ViewRiksdagenPartySummary`: Party information
- `ViewRiksdagenVoteDataBallotPartySummaryAnnual/Monthly/Daily`: Party voting patterns
- `ViewRiksdagenCommittee`: Committee data
- `ViewRiksdagenMinistry`: Ministry information

## Usage in Platform

### Risk Assessment Views
These rules power the Risk Assessment analytics displayed in:
- Politician risk profiles
- Party risk scorecards
- Committee performance dashboards
- Ministry effectiveness reports

### Integration Points
1. **Rules Engine**: `RulesEngineImpl.checkRulesCompliance()`
2. **Compliance Checks**: `ComplianceCheck` implementations
3. **UI Display**: Risk indicators in web interface
4. **API**: Risk data available via service API

## Ethical Considerations

All rules follow the platform's ethical guidelines:
- **Objectivity**: Rules use quantitative thresholds, not subjective judgments
- **Transparency**: All thresholds and logic are documented and open source
- **Privacy**: Only public parliamentary data is used
- **Non-partisan**: Rules apply equally to all parties and politicians
- **Democratic values**: Support informed citizenship, not manipulation

## Performance Considerations

- Rules execute efficiently using Drools pattern matching
- Indexed by salience for priority execution
- Stateless execution model for scalability
- Results cached for dashboard display

## Future Enhancements

Potential future improvements:
1. Machine learning-based threshold optimization
2. Historical trend analysis (comparing multiple years)
3. Cross-party comparative analysis rules
4. Coalition stability prediction models
5. Network analysis rules (collaboration patterns)
6. Sentiment analysis integration (media coverage)

## Testing

Rules are tested through:
- Integration tests: `RuleEngineITest`
- Build verification: Maven compilation
- Runtime validation: Spring context loading
- Manual verification: Dashboard review

## Maintenance

Rule thresholds may need adjustment based on:
- Changes in parliamentary procedures
- Electoral system changes
- Evolving political norms
- User feedback and accuracy assessment

## References

- [Drools Documentation](https://www.drools.org/)
- [Project Architecture](../../../../../../../ARCHITECTURE.md)
- [SWOT Analysis](../../../../../../../SWOT.md)
- [Data Model](../../../../../../../DATA_MODEL.md)
