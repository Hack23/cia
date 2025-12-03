# Analysis of Distinct Values vs Full Schema SQL Conditional Checks

**Date**: 2025-12-03  
**Author**: Intelligence Operative  
**Purpose**: Identify discrepancies between actual data values in distinct_values CSVs and conditional checks in full_schema.sql views

---

## Executive Summary

After comprehensive analysis of 91 distinct value CSV files against the conditional checks in full_schema.sql, the following issues were identified:

### Critical Issues Found

1. **Missing 'Förste vice talman' in talmansuppdrag role scoring**
   - **Location**: Line 9693 in `view_riksdagen_politician_experience_summary`
   - **Current**: Only 'Andre vice talman' and 'Tredje vice talman' are scored
   - **Missing**: 'Förste vice talman' (8 occurrences in database)
   - **Impact**: Förste vice talman roles are not properly weighted in experience calculations

2. **Decision Chamber Values - Partial Pattern Matching**
   - **Location**: Lines 6291-6294, 6355-6360 in decision views
   - **Issue**: `=utskottet` (6501 rows), `= utskottet` (517), `utskottet` (12), `=utskott` (19) are not matched by any pattern
   - **Impact**: 7,049 decision records fall into "other_decisions" category instead of proper classification
   - **Suggested Fix**: Add '%UTSKOTT%' pattern for committee referrals

### Data Quality Observations

3. **Assignment Type Values - Properly Handled**
   - All 7 assignment types from database are properly handled in views:
     - uppdrag (19,034), kammaruppdrag (7,171), Riksdagsorgan (4,229)
     - Departement (500), partiuppdrag (299), Europaparlamentet (85), talmansuppdrag (33)

4. **Role Code Values - Mostly Handled via Patterns**
   - '%minister%' pattern matches all minister roles (20+ different minister titles)
   - '%ordförande%' and '%vice ordförande%' patterns properly match variations
   - '%suppleant%' and '%ersättare%' patterns match substitute roles

5. **Vote Values - Properly Handled**
   - All 4 vote values properly matched: JA, NEJ, AVSTÅR, FRÅNVARANDE

6. **Document Type Values - Properly Handled**
   - 'mot', 'bet', 'prop' patterns are properly matched with case handling

7. **Party Short Codes - Properly Handled**
   - All major party codes are supported in the party-related views

---

## Detailed Analysis

### 1. Role Code Analysis

#### Values in Views vs Database:

| Role Code in Views | Database Count | Status |
|-------------------|----------------|--------|
| Ordförande | 435 | ✓ Matched |
| Vice ordförande | 408 | ✓ Matched |
| Ledamot | 6,056 | ✓ Matched |
| Suppleant | 14,757 | ✓ Matched |
| Ersättare | 933 | ✓ Matched |
| Riksdagsledamot | 6,052 | ✓ Matched |
| Deputerad | 920 | ✓ Matched |
| Statsråd | 259 | ✓ Matched |
| Statsrådsersättare | 295 | ✓ Matched |
| Gruppledare | 71 | ✓ Matched |
| Partiledare | 23 | ✓ Matched |
| Partisekreterare | 28 | ✓ Matched |
| Talman | 12 | ✓ Matched |
| Förste vice talman | 8 | ⚠️ NOT in talmansuppdrag scoring |
| Andre vice talman | 6 | ✓ Matched |
| Tredje vice talman | 7 | ✓ Matched |
| Statsminister | 10 | ✓ Matched |
| Vice statsminister | 1 | ✓ Matched |

**Issue Identified**: `view_riksdagen_politician_experience_summary` line 9693 checks for:
```sql
WHEN ((a.assignment_type)::text = 'talmansuppdrag'::text) 
  AND ((a.role_code)::text = ANY (ARRAY[
    'Andre vice talman',
    'Tredje vice talman'
  ])) THEN 750.0
```

Should be:
```sql
WHEN ((a.assignment_type)::text = 'talmansuppdrag'::text) 
  AND ((a.role_code)::text = ANY (ARRAY[
    'Förste vice talman',
    'Andre vice talman',
    'Tredje vice talman'
  ])) THEN 750.0
```

### 2. Decision Chamber Pattern Analysis

#### Current Pattern Matching:

| Pattern | Type | Matched Values |
|---------|------|---------------|
| %BIFALL% | Approval | Bifall, BifallDelvis, Bifall delvis, =delvis bifall |
| %GODKÄNT% | Approval | (none found in data) |
| %BIFALLA% | Approval | (none found in data) |
| %AVSLAG% | Rejection | Avslag, avslag |
| %AVSLÅ% | Rejection | (none found in data) |
| %ÅTERFÖRVISNING% | Referral | =Återförvisning till utskottet |
| %ÅTERFÖRVISA% | Referral | återförvisning |

#### Unmatched Values (falling into 'other_decisions'):

| Value | Count | Suggested Classification |
|-------|-------|-------------------------|
| =utskottet | 6,501 | Committee referral |
| = utskottet | 517 | Committee referral |
| utskottet | 12 | Committee referral |
| =utskott | 19 | Committee referral |
| ubtskottet | 1 | Committee referral (typo) |
| =utkottet | 1 | Committee referral (typo) |
| Lagd till handlingarna | 2 | Filed/archived |
| - | 1 | Unknown |
| (empty) | 5,444 | Unclassified |

**Recommendation**: Add '%UTSKOTT%' pattern to properly classify committee referrals.

### 3. Ministry Org Codes

All ministry org codes in views are valid:
- SB (Statsrådsberedningen)
- Fi (Finansdepartementet)
- UD (Utrikesdepartementet)
- Ju (Justitiedepartementet)
- N (Näringsdepartementet)
- M (Miljödepartementet)
- A (Arbetsmarknadsdepartementet)
- I (Infrastrukturdepartementet)
- LI (Landsbygdsdepartementet)
- Jo (Jordbruksdepartementet)
- S (Socialdepartementet)
- U (Utbildningsdepartementet)
- FÖ (Försvarsdepartementet)
- IJ (Integrations- och jämställdhetsdepartementet)
- KN (Klimat- och näringslivsdepartementet)
- Ku (Kulturdepartementet)

---

## Recommendations

### Priority 1 - Fix Now (Liquibase Changelog 1.44)

1. **Fix talmansuppdrag role scoring** - Add 'Förste vice talman' to the ARRAY

### Priority 2 - Consider for Future

1. **Add committee referral pattern** - Add '%UTSKOTT%' to decision classification
2. **Add "Lagd till handlingarna" pattern** - For filed/archived decisions

---

## Files Reviewed

### Distinct Values Files:
- assignment_data_role_code.csv (76 distinct values)
- assignment_data_assignment_type.csv (7 distinct values)
- assignment_data_org_code.csv (39+ distinct values)
- assignment_data_status.csv (5 distinct values)
- document_proposal_data_chamber.csv (25 distinct values)
- person_data_party.csv (17 distinct values)
- person_data_status.csv (200+ distinct values)
- vote_data_vote.csv (4 distinct values)
- document_data_document_type.csv (4 distinct values)
- document_data_sub_type.csv (16 distinct values)
- committee_proposal_data_decision_type.csv (7 distinct values)
- sweden_political_party_short_code.csv (30 distinct values)

### SQL Views Analyzed:
- view_riksdagen_politician_experience_summary
- view_ministry_decision_impact
- view_decision_temporal_trends
- view_riksdagen_committee_productivity
- view_riksdagen_goverment_proposals
- view_ministry_productivity_matrix
- view_ministry_effectiveness_trends
