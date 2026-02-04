---
name: political-science-analysis
description: Apply comparative politics, political behavior, public policy analysis, and democratic theory frameworks to Swedish political data
license: Apache-2.0
---

# Political Science Analysis Skill

## Purpose

This skill provides rigorous political science methodologies and analytical frameworks for interpreting political data collected by the CIA platform. It bridges quantitative data analysis with political theory, enabling evidence-based assessments of democratic accountability, institutional performance, and political behavior.

## When to Use This Skill

Apply this skill when:
- ✅ Analyzing voting behavior patterns and legislative outcomes
- ✅ Assessing government coalition stability and effectiveness
- ✅ Evaluating policy implementation and impact
- ✅ Conducting comparative analysis of political parties
- ✅ Measuring democratic accountability indicators
- ✅ Analyzing political representation and constituency alignment
- ✅ Studying institutional performance and committee effectiveness

Do NOT use for:
- ❌ Partisan advocacy or political campaigning
- ❌ Personal opinions about political ideologies
- ❌ Predictions without methodological basis
- ❌ Analysis that favors specific parties or politicians

## Core Political Science Frameworks

### 1. Comparative Politics Framework

**Purpose**: Systematically compare political actors, institutions, and outcomes across time and space.

**Comparative Dimensions**:
```
Actor Level Comparisons:
├─ Individual Politicians
│  ├─ Voting records (discipline, independence)
│  ├─ Legislative productivity (bills, amendments, questions)
│  ├─ Committee participation (attendance, contributions)
│  └─ Constituency representation (district alignment)
├─ Political Parties
│  ├─ Electoral performance (vote share, seats)
│  ├─ Coalition behavior (agreement rates, stability)
│  ├─ Policy positions (left-right, GAL-TAN)
│  └─ Organizational strength (membership, funding)
└─ Institutions
   ├─ Parliamentary committees (productivity, influence)
   ├─ Government ministries (budget, effectiveness)
   └─ Electoral districts (turnout, competitiveness)
```

**CIA Platform Implementation**:
```sql
-- Example: Comparative party discipline analysis
SELECT 
    p.party,
    COUNT(DISTINCT vr.ballot_id) as total_votes,
    COUNT(DISTINCT CASE WHEN vr.vote = party_line.vote THEN vr.ballot_id END) as party_line_votes,
    ROUND(100.0 * COUNT(DISTINCT CASE WHEN vr.vote = party_line.vote THEN vr.ballot_id END) / 
          NULLIF(COUNT(DISTINCT vr.ballot_id), 0), 2) as discipline_rate,
    -- Comparative metrics
    AVG(discipline_rate) OVER () as avg_discipline,
    discipline_rate - AVG(discipline_rate) OVER () as deviation_from_mean
FROM view_politician_voting_record vr
JOIN politician p ON vr.politician_id = p.id
JOIN (
    -- Determine party line (majority vote within party)
    SELECT ballot_id, party, vote, COUNT(*) as vote_count
    FROM view_politician_voting_record vr2
    JOIN politician p2 ON vr2.politician_id = p2.id
    GROUP BY ballot_id, party, vote
    QUALIFY ROW_NUMBER() OVER (PARTITION BY ballot_id, party ORDER BY vote_count DESC) = 1
) party_line ON vr.ballot_id = party_line.ballot_id AND p.party = party_line.party
WHERE vr.vote_date >= '2022-01-01'
GROUP BY p.party
ORDER BY discipline_rate DESC;
```

### 2. Political Behavior Framework

**Purpose**: Understand individual and collective political actions using behavioral science.

**Key Behavioral Indicators**:

| Behavior Type | Indicators | Data Sources | Interpretation |
|---------------|------------|--------------|----------------|
| **Legislative Behavior** | Vote patterns, bill sponsorship, amendments | `view_politician_voting_record`, `view_riksdagen_document` | Activity level, policy priorities |
| **Coalition Behavior** | Coalition voting agreement, cross-party cooperation | `view_coalition_alignment_matrix` | Party discipline, coalition stability |
| **Constituency Behavior** | District representation, constituent engagement | `view_electoral_district_data` | Responsiveness to voters |
| **Committee Behavior** | Attendance, contributions, influence | `view_committee_participation` | Policy expertise, influence |
| **Strategic Behavior** | Timing of actions, position-taking | `view_temporal_voting_patterns` | Electoral strategy, political calculation |

**Behavioral Analysis Pattern**:
```java
@Service
public class PoliticalBehaviorAnalysisService {
    
    /**
     * Analyze voting independence vs. party loyalty
     */
    public BehaviorProfile analyzeLegislativeBehavior(String politicianId, LocalDate startDate, LocalDate endDate) {
        // Retrieve voting record
        List<VotingRecord> votes = votingRepository.findByPoliticianAndDateRange(politicianId, startDate, endDate);
        
        // Calculate behavioral metrics
        double partyDiscipline = calculatePartyDiscipline(votes);
        double independenceIndex = 1.0 - partyDiscipline;
        double legislativeActivity = calculateActivityLevel(votes);
        double crossPartyCooperation = calculateCrossPartyVoting(votes);
        
        // Contextual interpretation
        String interpretation = interpretBehaviorProfile(
            partyDiscipline, 
            independenceIndex, 
            crossPartyCooperation
        );
        
        return BehaviorProfile.builder()
            .politicianId(politicianId)
            .period(new Period(startDate, endDate))
            .partyDiscipline(partyDiscipline)
            .independenceIndex(independenceIndex)
            .legislativeActivity(legislativeActivity)
            .crossPartyCooperation(crossPartyCooperation)
            .interpretation(interpretation)
            .build();
    }
    
    private String interpretBehaviorProfile(double discipline, double independence, double crossParty) {
        if (discipline > 0.95 && crossParty < 0.05) {
            return "Highly disciplined party loyalist with minimal cross-party cooperation";
        } else if (independence > 0.20 && crossParty > 0.15) {
            return "Independent-minded politician with significant cross-party engagement";
        } else if (discipline > 0.85 && crossParty > 0.10) {
            return "Generally loyal to party but willing to cooperate across party lines";
        } else {
            return "Moderate party loyalty with selective independence";
        }
    }
}
```

### 3. Public Policy Analysis Framework

**Purpose**: Assess policy development, implementation, and outcomes.

**Policy Cycle Analysis**:
```
1. Problem Identification
   ├─ Issue salience (media mentions, questions)
   ├─ Stakeholder mobilization (pressure groups)
   └─ Political attention (parliamentary debates)

2. Policy Formulation
   ├─ Committee deliberations
   ├─ Expert consultations
   └─ Draft legislation

3. Decision Making
   ├─ Parliamentary debate quality
   ├─ Voting outcomes
   └─ Coalition agreement

4. Implementation
   ├─ Budget allocation
   ├─ Agency assignment
   └─ Regulatory framework

5. Evaluation
   ├─ Outcome metrics
   ├─ Cost-benefit analysis
   └─ Public satisfaction
```

**CIA Platform Policy Tracking**:
```sql
-- Example: Track policy lifecycle from proposal to implementation
CREATE MATERIALIZED VIEW mv_policy_lifecycle AS
SELECT 
    doc.id as proposal_id,
    doc.title as policy_title,
    doc.submitted_date as proposal_date,
    doc.status as current_status,
    
    -- Committee phase
    committee.name as assigned_committee,
    committee.review_duration_days,
    
    -- Voting phase
    ballot.vote_date,
    ballot.yes_votes,
    ballot.no_votes,
    ballot.abstain_votes,
    CASE WHEN ballot.yes_votes > ballot.no_votes THEN 'PASSED' ELSE 'REJECTED' END as outcome,
    
    -- Implementation phase
    budget.allocated_amount,
    ministry.responsible_ministry,
    ministry.implementation_start_date,
    
    -- Policy cycle duration
    (ballot.vote_date - doc.submitted_date) as deliberation_duration,
    (ministry.implementation_start_date - ballot.vote_date) as implementation_lag
FROM riksdagen_document doc
LEFT JOIN committee_review committee ON doc.id = committee.document_id
LEFT JOIN ballot ballot ON doc.ballot_id = ballot.id
LEFT JOIN budget_allocation budget ON doc.id = budget.policy_id
LEFT JOIN ministry_assignment ministry ON doc.id = ministry.policy_id
WHERE doc.type = 'PROPOSITION'
ORDER BY doc.submitted_date DESC;
```

### 4. Democratic Theory Application

**Purpose**: Evaluate democratic quality and accountability mechanisms.

**Democratic Quality Indicators**:

| Dimension | Indicators | Measurement | Target |
|-----------|------------|-------------|--------|
| **Electoral Accountability** | Turnout, competitiveness, representation | `view_electoral_participation` | High turnout, competitive elections |
| **Legislative Responsiveness** | Constituency alignment, question activity | `view_politician_district_alignment` | Strong constituent representation |
| **Government Transparency** | Data availability, reporting frequency | Platform completeness metrics | 100% data availability |
| **Institutional Effectiveness** | Policy output, implementation success | `view_committee_productivity` | High legislative productivity |
| **Checks and Balances** | Opposition activity, oversight effectiveness | `view_parliamentary_oversight` | Active opposition, robust oversight |
| **Political Equality** | Representation diversity, access equity | `view_representation_demographics` | Proportional representation |

**Democratic Accountability Assessment**:
```java
@Service
public class DemocraticAccountabilityService {
    
    public DemocracyScorecard assessDemocraticQuality(String period) {
        DemocracyScorecard scorecard = new DemocracyScorecard();
        
        // 1. Electoral Accountability
        double turnoutRate = electoralService.calculateTurnoutRate(period);
        double competitivenessIndex = electoralService.calculateCompetitiveness(period);
        scorecard.setElectoralAccountability(
            (turnoutRate * 0.5) + (competitivenessIndex * 0.5)
        );
        
        // 2. Legislative Responsiveness
        double questionActivity = parliamentaryService.calculateQuestionRate(period);
        double constituencyAlignment = parliamentaryService.calculateAlignmentScore(period);
        scorecard.setLegislativeResponsiveness(
            (questionActivity * 0.4) + (constituencyAlignment * 0.6)
        );
        
        // 3. Government Transparency
        double dataCompleteness = platformService.calculateDataCompleteness(period);
        double reportingFrequency = platformService.calculateReportingRate(period);
        scorecard.setGovernmentTransparency(
            (dataCompleteness * 0.6) + (reportingFrequency * 0.4)
        );
        
        // 4. Institutional Effectiveness
        double legislativeProductivity = parliamentaryService.calculateProductivity(period);
        double policyImplementationRate = governmentService.calculateImplementationRate(period);
        scorecard.setInstitutionalEffectiveness(
            (legislativeProductivity * 0.5) + (policyImplementationRate * 0.5)
        );
        
        // 5. Overall Democracy Score (0-100)
        scorecard.setOverallScore(
            (scorecard.getElectoralAccountability() * 0.30) +
            (scorecard.getLegislativeResponsiveness() * 0.25) +
            (scorecard.getGovernmentTransparency() * 0.20) +
            (scorecard.getInstitutionalEffectiveness() * 0.25)
        );
        
        return scorecard;
    }
}
```

## Swedish Political System Specifics

### Parliamentary System Characteristics

**Riksdag (Swedish Parliament)**:
- **Unicameral**: 349 members (odd number prevents ties)
- **Electoral System**: Proportional representation with 4% threshold
- **Term**: Fixed 4-year terms
- **Voting**: Electronic voting system, recorded votes
- **Committees**: 15 standing committees with specialized policy areas

**Government Formation**:
```
Election Results
    ↓
Speaker Nomination (Talman)
    ↓
Formateur Appointed (Prime Minister Candidate)
    ↓
Coalition Negotiations
    ↓
Government Formation
    ↓
Investiture Vote (Negative Parliamentarism)
    ↓
Government Sworn In
```

**Negative Parliamentarism**: Prime Minister confirmed unless absolute majority votes against.

### Party System Analysis

**Swedish Party Spectrum** (Left → Right):
1. **Vänsterpartiet (V)** - Left Party
2. **Socialdemokraterna (S)** - Social Democrats
3. **Miljöpartiet (MP)** - Green Party
4. **Centerpartiet (C)** - Centre Party
5. **Liberalerna (L)** - Liberals
6. **Kristdemokraterna (KD)** - Christian Democrats
7. **Moderaterna (M)** - Moderate Party
8. **Sverigedemokraterna (SD)** - Sweden Democrats

**Coalition Patterns**:
```sql
-- Historical coalition analysis
CREATE MATERIALIZED VIEW mv_coalition_history AS
SELECT 
    gov.start_date,
    gov.end_date,
    ARRAY_AGG(party.name ORDER BY party.seat_count DESC) as coalition_parties,
    SUM(party.seat_count) as total_seats,
    ROUND(100.0 * SUM(party.seat_count) / 349, 2) as seat_percentage,
    gov.stability_index,
    gov.duration_months
FROM government gov
JOIN government_party gp ON gov.id = gp.government_id
JOIN party party ON gp.party_id = party.id
GROUP BY gov.id, gov.start_date, gov.end_date, gov.stability_index, gov.duration_months
ORDER BY gov.start_date DESC;
```

## Analytical Methods

### Quantitative Methods

**Statistical Techniques**:
- **Regression Analysis**: Identify factors influencing voting behavior
- **Time Series Analysis**: Track trends in political indicators over time
- **Cluster Analysis**: Group politicians by voting similarity
- **Principal Component Analysis (PCA)**: Reduce dimensionality of voting data
- **Network Analysis**: Map coalition relationships and influence networks

**Example: Regression Analysis of Voting Behavior**:
```python
import pandas as pd
import statsmodels.api as sm

# Load voting data
voting_data = pd.read_sql("""
    SELECT 
        politician_id,
        party,
        district_urbanization_rate,
        district_unemployment_rate,
        vote_yes_rate,
        vote_no_rate,
        vote_abstain_rate
    FROM view_politician_voting_summary
""", connection)

# Prepare independent variables
X = voting_data[['district_urbanization_rate', 'district_unemployment_rate']]
X = sm.add_constant(X)

# Dependent variable
y = voting_data['vote_yes_rate']

# Run regression
model = sm.OLS(y, X).fit()
print(model.summary())

# Interpretation: How do district characteristics affect voting patterns?
```

### Qualitative Methods

**Case Study Analysis**:
- Deep dive into specific political events or decisions
- Contextual understanding of voting behavior
- Identify causal mechanisms behind patterns

**Content Analysis**:
- Analyze parliamentary debate transcripts
- Examine political manifestos and policy documents
- Study media coverage and framing

**Elite Interviews**: (Future capability)
- Structured interviews with politicians
- Expert consultations on policy interpretation

## Decision Framework

### When Analyzing Political Data

```
START: Political Analysis Task
    │
    ├─→ What is the research question?
    │   ├─→ Descriptive: Use descriptive statistics, visualizations
    │   ├─→ Explanatory: Use regression, causal inference methods
    │   └─→ Predictive: Use time series, machine learning models
    │
    ├─→ What is the unit of analysis?
    │   ├─→ Individual politician: Focus on voting records, activity
    │   ├─→ Political party: Focus on electoral performance, coalition behavior
    │   ├─→ Institution: Focus on committee productivity, ministry effectiveness
    │   └─→ Policy: Focus on legislative lifecycle, implementation outcomes
    │
    ├─→ What is the time frame?
    │   ├─→ Single event: Use case study, qualitative methods
    │   ├─→ Short term (weeks/months): Use descriptive statistics
    │   ├─→ Medium term (years): Use trend analysis, comparative methods
    │   └─→ Long term (decades): Use time series, historical analysis
    │
    ├─→ What is the goal?
    │   ├─→ Academic research: Emphasize rigor, theory testing
    │   ├─→ Journalism: Emphasize timeliness, public interest
    │   ├─→ Public transparency: Emphasize accessibility, accountability
    │   └─→ Political consulting: Emphasize actionability, strategic insight
    │
    └─→ Apply appropriate framework
        ├─→ Comparative Politics Framework
        ├─→ Political Behavior Framework
        ├─→ Public Policy Analysis Framework
        └─→ Democratic Theory Framework
```

## ISMS Compliance Mapping

### ISO 27001:2022 Controls
- **A.5.10 - Acceptable Use of Information**: Ensure political analysis is objective, non-partisan
- **A.5.13 - Labelling of Information**: Classify political data by sensitivity (public figures vs. private citizens)
- **A.8.3 - Information Access Restriction**: Restrict access to PII in political datasets

### NIST Cybersecurity Framework
- **ID.GV-4**: Governance and risk management processes address privacy implications of political data
- **PR.DS-1**: Data-at-rest protection for sensitive political information
- **PR.IP-2**: Privacy requirements integrated into political analysis workflows

### CIS Controls v8
- **Control 3.12**: Segment sensitive political data (PII) from public data
- **Control 14.1**: Establish security awareness training for OSINT ethics

## Hack23 ISMS Policy References

Review these policies before political science analysis:
- **Data Protection Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/data-protection-policy.md
- **Research Ethics Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/research-ethics-policy.md
- **OSINT Collection Policy**: https://github.com/Hack23/ISMS-PUBLIC/blob/main/policies/osint-collection-policy.md

## References

### Political Science Literature
- **Comparative Politics**: Lijphart, A. (2012). *Patterns of Democracy*
- **Political Behavior**: Dalton, R. J. (2020). *Citizen Politics*
- **Public Policy**: Sabatier, P. A. (2007). *Theories of the Policy Process*
- **Democratic Theory**: Dahl, R. A. (1989). *Democracy and Its Critics*

### Swedish Political System
- **Riksdagen**: https://www.riksdagen.se/
- **Swedish Constitution**: https://www.riksdagen.se/en/how-the-riksdag-works/democracy/the-constitution/
- **Election Authority**: https://www.val.se/

### CIA Project Documentation
- **DATA_ANALYSIS_INTOP_OSINT.md**: Intelligence analysis frameworks
- **SWOT.md**: Strategic assessment methodology
- **INTELLIGENCE_DATA_FLOW.md**: Data pipeline and analytical views

### Academic Journals
- *Scandinavian Political Studies*
- *West European Politics*
- *Electoral Studies*
- *Legislative Studies Quarterly*

## Success Metrics

Track these KPIs to measure analytical quality:
- **Accuracy**: Predictive models achieve 80%+ accuracy
- **Objectivity**: Balanced coverage of all political parties
- **Timeliness**: Analysis published within 48 hours of new data
- **Impact**: Citations in academic research, media references
- **Transparency**: All methodologies documented and reproducible
