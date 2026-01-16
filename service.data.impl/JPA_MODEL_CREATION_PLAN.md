# JPA Model Creation Plan for Changelog 1.53 Views

## Overview
This document outlines the plan for creating JPA entity mappings for the 3 new database views added in changelog 1.53.

## Status
**Phase**: 1 of 3 - Planning Complete
**Date Started**: 2026-01-16

## Views Requiring JPA Models

### 1. ViewRiksdagenPartyLongitudinalPerformance ⏳ In Progress
- **Database View**: `view_riksdagen_party_longitudinal_performance`
- **Package**: `com.hack23.cia.model.internal.application.data.party.impl`
- **Total Columns**: 73
- **Primary Key**: Composite (party, election_cycle_id, semester)
- **Requires Embedded ID**: Yes
- **Files to Create**:
  1. `ViewRiksdagenPartyLongitudinalPerformanceEmbeddedId.java` - Composite key class
  2. `ViewRiksdagenPartyLongitudinalPerformance.java` - Main entity class

**Key Columns** (for EmbeddedId):
- party (String)
- election_cycle_id (String)
- semester (String)

**Sample Data Columns**:
- cycle_year (Integer)
- calendar_year (Integer)
- is_election_year (Boolean)
- total_ballots (BigDecimal)
- participation_rate (BigDecimal)
- win_rate (BigDecimal)
- active_members (Long)
- documents_last_year (Long)
- rank_by_win_rate (Long)
- percentile_win_rate (Double)
- trajectory_win_rate (String)
- performance_tier (String)
- ... (60+ more columns)

### 2. ViewRiksdagenPartyCoalitionEvolution ⏸️ Pending
- **Database View**: `view_riksdagen_party_coalition_evolution`
- **Package**: `com.hack23.cia.model.internal.application.data.party.impl`
- **Total Columns**: 47
- **Primary Key**: Composite (party_1, party_2, election_cycle_id, semester)
- **Requires Embedded ID**: Yes
- **Files to Create**:
  1. `ViewRiksdagenPartyCoalitionEvolutionEmbeddedId.java` - Composite key class
  2. `ViewRiksdagenPartyCoalitionEvolution.java` - Main entity class

**Key Columns** (for EmbeddedId):
- party_1 (String)
- party_2 (String)
- election_cycle_id (String)
- semester (String)

**Sample Data Columns**:
- cycle_year (Integer)
- calendar_year (Integer)
- joint_voting_days (Long)
- joint_ballots (BigDecimal)
- aligned_ballots (BigDecimal)
- alignment_rate (BigDecimal)
- coalition_strength (String)
- coalition_trend (String)
- strategic_shift (String)
- ... (35+ more columns)

### 3. ViewRiksdagenPartyElectoralTrends ⏸️ Pending
- **Database View**: `view_riksdagen_party_electoral_trends`
- **Package**: `com.hack23.cia.model.internal.application.data.party.impl`
- **Total Columns**: 59
- **Primary Key**: Composite (party, election_cycle_id, semester)
- **Requires Embedded ID**: Yes
- **Files to Create**:
  1. `ViewRiksdagenPartyElectoralTrendsEmbeddedId.java` - Composite key class
  2. `ViewRiksdagenPartyElectoralTrends.java` - Main entity class

**Key Columns** (for EmbeddedId):
- party (String)
- election_cycle_id (String)
- semester (String)

**Sample Data Columns**:
- cycle_year (Integer)
- calendar_year (Integer)
- seat_count_proxy (Long)
- ballots_participated (BigDecimal)
- win_rate (BigDecimal)
- documents_produced (Long)
- electoral_trend (String)
- party_size_category (String)
- volatility_classification (String)
- ... (48+ more columns)

## Implementation Pattern

### Embedded ID Class Template
```java
@Embeddable
public class View[Name]EmbeddedId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "column_name", nullable = false)
    private Type fieldName;
    
    // Constructor, getters, setters, equals, hashCode, toString
}
```

### Entity Class Template
```java
@Entity
@Immutable
@Table(name = "view_table_name")
public class View[Name] implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private View[Name]EmbeddedId embeddedId;
    
    @Column(name = "column_name")
    private Type fieldName;
    
    // Constructor, getters, setters, equals, hashCode, toString
}
```

## persistence.xml Updates Required

Add these entries to `service.data.impl/src/main/resources/META-INF/persistence.xml`:

```xml
<!-- V1.53 Party Longitudinal Analysis Views -->
<class>com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyLongitudinalPerformance</class>
<class>com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyLongitudinalPerformanceEmbeddedId</class>
<class>com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyCoalitionEvolution</class>
<class>com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyCoalitionEvolutionEmbeddedId</class>
<class>com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyElectoralTrends</class>
<class>com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyElectoralTrendsEmbeddedId</class>
```

## Data Type Mappings

| PostgreSQL Type | Java Type | Notes |
|----------------|-----------|-------|
| text | String | |
| character varying | String | |
| integer | Integer | |
| bigint | Long | |
| numeric | BigDecimal | Import java.math.BigDecimal |
| double precision | Double | |
| boolean | Boolean | |
| date | Date | Import java.util.Date |
| timestamp | Date | Import java.util.Date |

## Required Imports

```java
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
```

## Testing Checklist

After creating each entity:
- [ ] Compile without errors
- [ ] Add to persistence.xml
- [ ] Build project: `mvn clean compile`
- [ ] Verify Hibernate metamodel generation
- [ ] Test basic query (if data available)

## Column Definition Files

Full column definitions extracted to:
- `/tmp/view_riksdagen_party_longitudinal_performance_columns.txt` (73 columns)
- `/tmp/view_riksdagen_party_coalition_evolution_columns.txt` (47 columns)
- `/tmp/view_riksdagen_party_electoral_trends_columns.txt` (59 columns)

## Total Work Estimate

- **Total Entities**: 6 classes (3 EmbeddedId + 3 Entity)
- **Total Columns**: 179 columns across all entities
- **Estimated Time**: 3-4 sessions (as requested by user)
- **Session 1**: Planning + First view (ViewRiksdagenPartyLongitudinalPerformance)
- **Session 2**: Second view (ViewRiksdagenPartyCoalitionEvolution)
- **Session 3**: Third view (ViewRiksdagenPartyElectoralTrends) + Testing

## References

- Existing similar entities: `ViewRiksdagenCoalitionAlignmentMatrix` and `ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId`
- Location: `model.internal.application.data.impl/src/main/java/com/hack23/cia/model/internal/application/data/party/impl/`
- persistence.xml: `service.data.impl/src/main/resources/META-INF/persistence.xml`

## Notes

- All views are read-only (marked with @Immutable)
- Views use composite keys requiring @EmbeddedId
- Follow existing code style and naming conventions
- Use Apache Commons Lang3 builders for equals/hashCode/toString
- Generate comprehensive JavaDoc comments
