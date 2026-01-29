# 📋 JSON Export Examples - Comprehensive Guide

**Version:** 1.0.0  
**Last Updated**: 2026-01-29  
**Purpose**: Reference examples for all CIA JSON export schemas

---

## 📚 Overview

This directory contains complete, realistic JSON examples for all CIA export schemas. These examples demonstrate the structure, field relationships, and data patterns for each entity type and analytical product.

### Example Files

| Example File | Schema | Size | Entities | Description |
|-------------|--------|------|----------|-------------|
| [politician-example.json](./politician-example.json) | [politician-schema.md](../schemas/politician-schema.md) | 9KB | 2 | Active MPs with complete profiles |
| [party-example.json](./party-example.json) | [party-schema.md](../schemas/party-schema.md) | 7KB | 2 | Parliamentary parties (S, M) |
| [committee-example.json](./committee-example.json) | [committee-schema.md](../schemas/committee-schema.md) | 17KB | 2 | Finance & Foreign Affairs committees |
| [ministry-example.json](./ministry-example.json) | [ministry-schema.md](../schemas/ministry-schema.md) | 19KB | 2 | Finance & Foreign Affairs ministries |
| [risk-assessment-example.json](./risk-assessment-example.json) | [intelligence-schema.md](../schemas/intelligence-schema.md) | 14KB | N/A | Risk violations and assessments |
| [temporal-trends-example.json](./temporal-trends-example.json) | [intelligence-schema.md](../schemas/intelligence-schema.md) | 13KB | N/A | Decision trends with moving averages |
| [coalition-alignment-example.json](./coalition-alignment-example.json) | [intelligence-schema.md](../schemas/intelligence-schema.md) | 9KB | N/A | Party voting alignment matrix |
| **Advanced Analysis (v1.58-v1.61)** |
| [politician-career-path-example.json](./politician-career-path-example.json) | [politician-schema.md](../schemas/politician-schema.md) | 5KB | 1 | Career progression with risk assessment |
| [election-proximity-trends-example.json](./election-proximity-trends-example.json) | [intelligence-schema.md](../schemas/intelligence-schema.md) | 11KB | 1 | Quarterly behavior vs election proximity |
| [party-longitudinal-performance-example.json](./party-longitudinal-performance-example.json) | [party-schema.md](../schemas/party-schema.md) | 10KB | 1 | Multi-semester performance trends |

**Total**: 10 example files, 114KB

---

## 🎯 Core Entity Examples

### Politician Profile Example

**File**: `politician-example.json`  
**Visualization**: [politician-profile.md](../visualizations/politician-profile.md)

**Contents**:
- 2 active MPs (Social Democrat committee chair, Moderate minister)
- Complete biographical data
- Voting records and behavioral metrics
- Committee assignments and leadership roles
- Risk assessments and intelligence tags
- Document productivity and legislative impact

**Key Fields**:
```json
{
  "id": "0123456789",
  "labels": {
    "category": "active-member",
    "intelligenceTags": ["coalition-broker", "policy-expert-economics"]
  },
  "attributes": { "firstName": "Anna", "party": "S" },
  "voting": { "attendanceRate": 94.2, "winRate": 78.5 },
  "intelligence": { "influenceScore": 82.5, "riskLevel": "low" }
}
```

---

### Party Profile Example

**File**: `party-example.json`  
**Visualization**: [party-performance.md](../visualizations/party-performance.md)

**Contents**:
- 2 parliamentary parties (Social Democrats, Moderates)
- Electoral performance and seat counts
- Voting patterns and party discipline
- Coalition relationships
- Leadership structure
- Performance metrics and trends

**Key Fields**:
```json
{
  "id": "S",
  "attributes": { "name": "Socialdemokraterna", "seats": 107 },
  "performance": { "winRate": 52.3, "participationRate": 94.5 },
  "discipline": { "avgRebelRate": 2.3 },
  "intelligence": { "coalitionStability": 0.87, "electoralTrend": "stable" }
}
```

---

### Committee Profile Example

**File**: `committee-example.json`  
**Visualization**: [committee-network.md](../visualizations/committee-network.md)

**Contents**:
- 2 standing committees (Finance, Foreign Affairs)
- Membership composition by party
- Leadership structure (chair, vice chairs)
- Productivity metrics (reports, hearings, meetings)
- Decision patterns and approval rates
- Workload classification and effectiveness scores
- Policy focus areas
- Intelligence analysis with influence ranking

**Key Fields**:
```json
{
  "id": "FiU",
  "attributes": { "name": "Finansutskottet", "type": "standing" },
  "membership": { "totalMembers": 17, "partyDistribution": [...] },
  "productivity": {
    "reports": { "total": 47, "successRate": 91.5 },
    "workloadClassification": "very-high"
  },
  "intelligence": {
    "influenceScore": 95.8,
    "influenceRank": 1,
    "effectivenessScore": 88.7
  }
}
```

---

### Ministry Profile Example

**File**: `ministry-example.json`  
**Visualization**: [government-cabinet.md](../visualizations/government-cabinet.md)

**Contents**:
- 2 government ministries (Finance, Foreign Affairs)
- Organizational structure and personnel
- Budget allocation and execution rates
- Minister and state secretary information
- Performance metrics and KPIs
- Decision success rates
- Policy initiatives and priorities
- Intelligence analysis with predictions

**Key Fields**:
```json
{
  "id": "FI",
  "attributes": { "name": "Finansdepartementet", "portfolio": "economic-policy-budget" },
  "personnel": {
    "ministers": { "financeMinister": {...} },
    "civilServants": { "total": 487 }
  },
  "budget": {
    "allocation": { "total": 1234000000000, "currency": "SEK" },
    "execution": { "executionRate": 69.4 }
  },
  "intelligence": {
    "performanceScore": 89.7,
    "influenceScore": 96.3,
    "predictions": { "budgetDeficit2026": -0.8 }
  }
}
```

---

## 🔍 Intelligence Product Examples

### Risk Assessment Example

**File**: `risk-assessment-example.json`  
**Visualization**: [intelligence-dashboard.md](../visualizations/intelligence-dashboard.md)

**Contents**:
- Rule violation records across 50 behavioral rules
- Severity classification (LOW, MODERATE, HIGH, CRITICAL)
- Detection timestamps and persistence tracking
- Affected politicians and parties
- Risk categories (attendance, effectiveness, discipline, isolation)

**Key Fields**:
```json
{
  "ruleViolations": [
    {
      "id": "RV-2024-001234",
      "ruleId": "POL-ATTENDANCE-01",
      "severity": "MODERATE",
      "detectedDate": "2024-11-20",
      "affectedEntities": { "politicianId": "0123456789" }
    }
  ]
}
```

---

### Temporal Trends Example

**File**: `temporal-trends-example.json`  
**Visualization**: [intelligence-dashboard.md](../visualizations/intelligence-dashboard.md)

**Contents**:
- Decision flow metrics over time
- Daily, weekly, monthly aggregations
- Moving averages (7-day, 30-day, 90-day)
- Approval rates and rejection rates
- Trend classifications

**Key Fields**:
```json
{
  "temporalTrends": [
    {
      "date": "2024-11-20",
      "decisionsTotal": 47,
      "decisionsApproved": 43,
      "movingAverage7Day": 45.2,
      "trendClassification": "stable-high"
    }
  ]
}
```

---

### Coalition Alignment Example

**File**: `coalition-alignment-example.json`  
**Visualization**: [intelligence-dashboard.md](../visualizations/intelligence-dashboard.md)

**Contents**:
- Pairwise party voting alignment matrix
- Alignment rates and vote divergence
- Coalition strength classification
- Temporal trends in cooperation
- Bridge party identification

**Key Fields**:
```json
{
  "coalitionAlignment": [
    {
      "party1": "S",
      "party2": "C",
      "alignmentRate": 68.5,
      "voteDivergence": 31.5,
      "coalitionStrength": "moderate",
      "trend": "improving"
    }
  ]
}
```

---

## 📈 Advanced Analysis Examples (v1.58-v1.61)

### Politician Career Path Example

**File**: `politician-career-path-example.json`  
**Visualization**: [politician-career-analysis.md](../visualizations/politician-career-analysis.md)

**Contents**:
- 4-step career progression (11.3 years)
- 10-level career hierarchy classification
- Progression metrics (promotions, demotions, velocity)
- Career pattern identification (STEADY_CLIMBER)
- Risk profile with behavioral assessment
- Network influence and leadership potential
- Career percentile ranking (87th percentile)

**Key Fields**:
```json
{
  "careerPath": [
    {
      "roleCode": "Ordförande",
      "careerLevel": 6,
      "careerScore": 600,
      "yearsInRole": 5.6,
      "isPeak": true
    }
  ],
  "aggregateMetrics": {
    "careerPattern": "STEADY_CLIMBER",
    "advancementVelocity": 17.7,
    "careerPercentile": 87.1
  }
}
```

**Use Cases**:
- Career trajectory visualization (Sankey diagrams)
- Leadership potential identification
- Succession planning analysis
- Risk assessment for career stages

---

### Election Proximity Trends Example

**File**: `election-proximity-trends-example.json`  
**Visualization**: [election-cycle-analysis.md](../visualizations/election-cycle-analysis.md)

**Contents**:
- 6 quarters of activity (20 months to 5 months before election)
- Election phase classification (5 phases)
- Activity metrics (ballots, documents, decisions)
- Behavioral metrics per quarter
- Deviation from baseline (up to 107% increase)
- Risk scoring evolution
- Pre-election activity boost detection (45.6% increase)

**Key Fields**:
```json
{
  "quarters": [
    {
      "monthsUntilElection": 5,
      "electionPhase": "INTENSE_CAMPAIGN",
      "activity": { "compositeActivityScore": 102.8 },
      "deviations": { "documentActivityRatio": 2.07 }
    }
  ],
  "trends": {
    "documentProductivityTrend": "SHARP_INCREASE",
    "preElectionBoost": 45.6
  }
}
```

**Use Cases**:
- Pre-election behavior pattern detection
- Strategic timing analysis
- Activity surge identification
- Electoral vulnerability assessment

---

### Party Longitudinal Performance Example

**File**: `party-longitudinal-performance-example.json`  
**Visualization**: [party-longitudinal-analysis.md](../visualizations/party-longitudinal-analysis.md)

**Contents**:
- 3 semesters of performance tracking (2023-2024)
- Win rate, participation, approval metrics
- Semester-over-semester change analysis
- Moving averages and trend indicators
- Performance tier classification
- Volatility assessment
- Forecasting indicators and confidence scores

**Key Fields**:
```json
{
  "semesters": [
    {
      "semester": "2024-H1",
      "performance": { "winRate": 52.3, "participationRate": 94.5 },
      "changes": {
        "winRateChangeAbsolute": 0.5,
        "ma3SemesterWinRate": 52.1
      },
      "classifications": {
        "performanceTier": "HIGH_PERFORMER",
        "trajectoryConfidenceScore": 0.89
      }
    }
  ]
}
```

**Use Cases**:
- Long-term trend analysis
- Coalition stability forecasting
- Electoral readiness assessment
- Performance benchmarking

---

## 🎨 Visualization Integration

Each example file is designed to work with corresponding visualization guides:

### Data Flow

```
Database Views
    ↓
JSON Export Service
    ↓
Example JSON Files  ← [You are here]
    ↓
Visualization Guides
    ↓
D3.js/Chart.js Implementation
    ↓
Interactive Dashboards
```

### Example-to-Visualization Mapping

| Example | Primary Visualization | Additional Visualizations |
|---------|----------------------|---------------------------|
| politician-example.json | [politician-profile.md](../visualizations/politician-profile.md) | top10-*.md |
| party-example.json | [party-performance.md](../visualizations/party-performance.md) | party-longitudinal-analysis.md |
| committee-example.json | [committee-network.md](../visualizations/committee-network.md) | intelligence-dashboard.md |
| ministry-example.json | [government-cabinet.md](../visualizations/government-cabinet.md) | intelligence-dashboard.md |
| politician-career-path-example.json | [politician-career-analysis.md](../visualizations/politician-career-analysis.md) | - |
| election-proximity-trends-example.json | [election-cycle-analysis.md](../visualizations/election-cycle-analysis.md) | - |
| party-longitudinal-performance-example.json | [party-longitudinal-analysis.md](../visualizations/party-longitudinal-analysis.md) | - |

---

## 🔧 Usage Guide

### Quick Start

```javascript
// Fetch example data
const response = await fetch('/examples/politician-example.json');
const data = await response.json();

// Access specific politician
const politician = data.data[0];
console.log(`${politician.attributes.firstName} ${politician.attributes.lastName}`);
console.log(`Party: ${politician.attributes.party}`);
console.log(`Influence Score: ${politician.intelligence.influenceScore}`);
```

### Validation

All examples follow the schema structure defined in [schemas/](../schemas/). Validate using:

```bash
# Run validation script
python validate_schemas.py

# Check specific example
python validate_schemas.py examples/politician-example.json
```

### CDN Deployment

Examples can be deployed to CDN for production use:

```bash
# Deploy all examples
./deploy-cdn.sh examples

# Deploy specific example
./deploy-cdn.sh examples/politician-example.json
```

---

## 📊 Data Characteristics

### Realistic Ranges

All examples use realistic data ranges based on actual Swedish political statistics:

| Metric | Typical Range | Example Value |
|--------|---------------|---------------|
| Attendance Rate | 85-97% | 94.2% |
| Win Rate | 45-85% | 78.5% |
| Rebel Rate | 0.5-15% | 2.3% |
| Committee Reports | 20-60/year | 47 |
| Budget Execution | 95-100% | 99.4% |

### Swedish Context

- **Party Names**: Official Swedish names (Socialdemokraterna, not "Social Democrats")
- **Role Codes**: Riksdag terminology (Ordförande, Ledamot, etc.)
- **Dates**: Swedish election cycles (2014, 2018, 2022, 2026)
- **Organizations**: Actual committees (FiU, UU) and ministries (FI, UD)

---

## 🔗 Related Documentation

- [Main README](../README.md) - System overview
- [Schema Specifications](../schemas/) - Detailed field definitions
- [Visualization Guides](../visualizations/) - Implementation examples
- [Validation Reports](../SCHEMA_VALIDATION_REPORT.md) - Quality assurance
- [Business Product Document](../../BUSINESS_PRODUCT_DOCUMENT.md) - Product context

---

## 📝 Example Metadata

All examples include standard metadata:

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2026-01-29T02:40:00Z",
    "source": "Citizen Intelligence Agency",
    "schema": "politician-profile",
    "recordCount": 2,
    "dataDate": "2026-01-28",
    "attribution": "Data derived from Riksdagen Open Data API",
    "license": "Apache-2.0"
  }
}
```

### Metadata Fields

- **version**: Schema version (semantic versioning)
- **generated**: ISO 8601 timestamp of generation
- **source**: Data source attribution
- **schema**: Schema identifier
- **recordCount**: Number of entities in data array
- **dataDate**: Date of underlying data
- **attribution**: Legal attribution
- **license**: Data license (Apache-2.0)

---

## ✅ Quality Standards

All examples meet these standards:

- ✅ **Schema Compliance**: Valid against published schemas
- ✅ **Realistic Data**: Based on actual Swedish political patterns
- ✅ **Complete Coverage**: All major fields populated
- ✅ **Proper Attribution**: Source and license information
- ✅ **Documentation Links**: References to visualization guides
- ✅ **Consistent Formatting**: 2-space indentation, sorted keys
- ✅ **Size Optimization**: Reasonable file sizes (5-20KB per entity)

---

## 🆕 Version History

### v1.0.0 (2026-01-29)
- ✅ Added committee-example.json
- ✅ Added ministry-example.json
- ✅ Added politician-career-path-example.json
- ✅ Added election-proximity-trends-example.json
- ✅ Added party-longitudinal-performance-example.json
- ✅ Created EXAMPLES_README.md

### v0.9.0 (2024-11-24)
- Initial examples: politician, party, risk-assessment, temporal-trends, coalition-alignment

---

**Maintained By**: Citizen Intelligence Agency Development Team  
**License**: Apache-2.0  
**Contact**: https://github.com/Hack23/cia
