# Party Leadership Roles View - Example Queries

This document provides example queries for the `view_party_leadership_roles` created in v1.48.

## View Purpose

Track parliamentary group leaders (gruppledare), party leaders (partiledare), and party secretaries (partisekreterare) with comprehensive party organization analysis.

## Example Queries

### 1. Current Party Leadership Across All Parties

```sql
SELECT 
    party_name,
    leadership_type,
    full_name,
    tenure_years,
    primary_party
FROM view_party_leadership_roles
WHERE is_current = true
ORDER BY party_name, authority_level DESC;
```

**Purpose**: Shows all current party leaders, group leaders, and secretaries.

---

### 2. Parties with Dual Leadership (Party + Group Leader Same Person)

```sql
SELECT 
    full_name,
    party_name,
    concurrent_leadership_roles,
    role_combination
FROM view_party_leadership_roles
WHERE concurrent_leadership_roles > 1
    AND is_current = true
ORDER BY concurrent_leadership_roles DESC;
```

**Purpose**: Identifies power concentration where one person holds multiple leadership roles.

---

### 3. Party Organizational Stability Comparison

```sql
SELECT 
    party_name,
    organizational_stability,
    party_avg_tenure AS avg_years,
    party_total_leaders
FROM view_party_leadership_roles
WHERE is_current = true
GROUP BY party_name, organizational_stability, party_avg_tenure, party_total_leaders
ORDER BY party_avg_tenure DESC;
```

**Purpose**: Compare organizational stability across parties based on leadership tenure patterns.

**Stability Ratings:**
- **STABLE**: Average tenure > 4 years
- **MODERATE**: Average tenure > 2 years
- **VOLATILE**: Average tenure < 2 years

---

### 4. Veteran Leaders (Long Tenure)

```sql
SELECT 
    full_name,
    party_name,
    leadership_type,
    tenure_years,
    leadership_tenure_class,
    age_at_appointment,
    appointment_year
FROM view_party_leadership_roles
WHERE leadership_tenure_class = 'VETERAN'
    OR tenure_years >= 5
ORDER BY tenure_years DESC
LIMIT 20;
```

**Purpose**: Identify experienced leaders with significant tenure.

**Tenure Classifications:**
- **TRANSITIONAL**: < 1 year
- **ESTABLISHED**: 1-4 years
- **VETERAN**: > 4 years

---

### 5. Leadership Gender Distribution

```sql
SELECT 
    party_name,
    leadership_type,
    gender,
    COUNT(*) AS leadership_terms,
    ROUND(AVG(tenure_years), 2) AS avg_tenure,
    COUNT(*) FILTER (WHERE is_current = true) AS current_count
FROM view_party_leadership_roles
GROUP BY party_name, leadership_type, gender
ORDER BY party_name, leadership_type, gender;
```

**Purpose**: Analyze gender representation in party leadership roles.

---

### 6. Leadership Succession Analysis

```sql
SELECT 
    party_name,
    leadership_type,
    full_name,
    from_date,
    to_date,
    tenure_years,
    LEAD(full_name) OVER (
        PARTITION BY party_code, leadership_type 
        ORDER BY from_date
    ) AS successor,
    LEAD(from_date) OVER (
        PARTITION BY party_code, leadership_type 
        ORDER BY from_date
    ) - to_date AS transition_gap_days
FROM view_party_leadership_roles
WHERE to_date IS NOT NULL
ORDER BY party_name, leadership_type, from_date DESC;
```

**Purpose**: Track leadership transitions and identify gaps between leaders.

**Transition Types:**
- **IMMEDIATE**: Same day succession (0 days)
- **RAPID**: Within 30 days
- **DELAYED**: More than 30 days

---

### 7. Young Appointees

```sql
SELECT 
    full_name,
    party_name,
    leadership_type,
    age_at_appointment,
    appointment_year,
    tenure_years,
    is_current
FROM view_party_leadership_roles
WHERE age_at_appointment < 45
ORDER BY age_at_appointment ASC, appointment_year DESC
LIMIT 20;
```

**Purpose**: Identify younger leaders appointed to senior positions.

---

### 8. Party Leadership Timeline (Temporal Query)

```sql
-- Leaders active on a specific date
SELECT 
    party_name,
    leadership_type,
    full_name,
    from_date,
    to_date,
    tenure_years
FROM view_party_leadership_roles
WHERE from_date <= '2020-01-01'::date
    AND (to_date IS NULL OR to_date >= '2020-01-01'::date)
ORDER BY party_name, authority_level DESC;
```

**Purpose**: Show party leadership composition at any point in time.

---

## Key Metrics

| Metric | Description |
|--------|-------------|
| **tenure_days** | Days in leadership position |
| **tenure_years** | Years in leadership position (rounded to 2 decimals) |
| **authority_level** | Hierarchical ranking (10=Partiledare, 9=Gruppledare, 7=Partisekreterare) |
| **is_current** | Boolean flag for current leaders |
| **organizational_stability** | Party stability rating (STABLE, MODERATE, VOLATILE) |
| **concurrent_leadership_roles** | Count of simultaneous leadership roles |
| **leadership_tenure_class** | Career stage (TRANSITIONAL, ESTABLISHED, VETERAN) |

## Data Coverage

- **Partiledare**: 23 party leader positions
- **Gruppledare**: 71 parliamentary group leader positions
- **Partisekreterare**: 28 party secretary positions
- **Total**: 122 leadership positions tracked

## Swedish Context

**Party Leadership Roles:**
- **Partiledare** = Party Leader (overall party control)
- **Gruppledare** = Parliamentary Group Leader (riksdag tactics)
- **Partisekreterare** = Party Secretary (administrative/organizational)

Many parties separate the party leader (who leads the entire party) from the group leader (who leads the parliamentary group in the Riksdag). Concurrent roles indicate power concentration.
