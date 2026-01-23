# Materialized View Refresh Schedule

This document outlines the refresh schedules for the 5 high-impact materialized views converted for performance optimization.

## Overview

Converting these views to materialized views provides 60-80% performance improvement on complex queries. Each view has a specific refresh schedule based on data volatility and query patterns.

## Refresh Schedules

### 1. view_decision_temporal_trends
- **Frequency**: Daily
- **Schedule**: Every day at 02:00 UTC
- **Cron**: `0 2 * * * /path/to/refresh_materialized_views.sh decision_temporal_trends`
- **Performance**: 40% faster (3s → 1.8s)
- **Rationale**: Decision data is updated daily with parliamentary sessions

### 2. view_riksdagen_politician_influence_metrics
- **Frequency**: Weekly
- **Schedule**: Every Sunday at 03:00 UTC
- **Cron**: `0 3 * * 0 /path/to/refresh_materialized_views.sh politician_influence_metrics`
- **Performance**: 80% faster (5-15s → 1-3s)
- **Rationale**: Network influence metrics change slowly, weekly refresh sufficient

### 3. view_politician_behavioral_trends
- **Frequency**: Monthly
- **Schedule**: 1st day of every month at 04:00 UTC
- **Cron**: `0 4 1 * * /path/to/refresh_materialized_views.sh politician_behavioral_trends`
- **Performance**: 35% faster (4s → 2.6s)
- **Rationale**: Behavioral patterns evolve over weeks/months

### 4. view_party_effectiveness_trends
- **Frequency**: Quarterly
- **Schedule**: 1st day of Jan/Apr/Jul/Oct at 05:00 UTC
- **Cron**: `0 5 1 1,4,7,10 * /path/to/refresh_materialized_views.sh party_effectiveness_trends`
- **Performance**: 50% faster (1.5s → <800ms)
- **Rationale**: Effectiveness metrics are stable over quarters

### 5. view_election_cycle_temporal_trends
- **Frequency**: Post-election (manual trigger)
- **Schedule**: Manual execution after election data is finalized
- **Command**: `/path/to/refresh_materialized_views.sh election_cycle_temporal_trends`
- **Performance**: 70% faster (5-10s → <3s)
- **Dependencies**: ⚠️ **Depends on view_decision_temporal_trends and view_politician_behavioral_trends** - these must be refreshed first
- **Rationale**: Election cycle data is static between elections

## ⚠️ Important: Refresh Order for Dependencies

The `view_election_cycle_temporal_trends` materialized view has dependencies on two other materialized views:
- `view_decision_temporal_trends` (lines 1069, 1095 in db-changelog-1.69.xml)
- `view_politician_behavioral_trends` (lines 1068, 1094 in db-changelog-1.69.xml)

**Required refresh order when refreshing all views:**
1. Refresh `view_decision_temporal_trends` first
2. Refresh `view_politician_behavioral_trends` second
3. Then refresh `view_election_cycle_temporal_trends`

The `refresh_materialized_views.sh all` command already follows this order to ensure correct data population.

## Installation

### 1. Install refresh script

```bash
# Copy script to system location
sudo cp refresh_materialized_views.sh /usr/local/bin/
sudo chmod +x /usr/local/bin/refresh_materialized_views.sh

# Create log directory
sudo mkdir -p /var/log/cia
sudo chown postgres:postgres /var/log/cia
```

### 2. Configure cron jobs

```bash
# Edit postgres user crontab
sudo crontab -e -u postgres

# Add the following lines:

# Daily: Decision temporal trends (02:00 UTC)
0 2 * * * /usr/local/bin/refresh_materialized_views.sh decision_temporal_trends

# Weekly: Politician influence metrics (Sunday 03:00 UTC)
0 3 * * 0 /usr/local/bin/refresh_materialized_views.sh politician_influence_metrics

# Monthly: Politician behavioral trends (1st of month, 04:00 UTC)
0 4 1 * * /usr/local/bin/refresh_materialized_views.sh politician_behavioral_trends

# Quarterly: Party effectiveness trends (1st of Jan/Apr/Jul/Oct, 05:00 UTC)
0 5 1 1,4,7,10 * /usr/local/bin/refresh_materialized_views.sh party_effectiveness_trends

# Manual: Election cycle temporal trends (run after elections)
# /usr/local/bin/refresh_materialized_views.sh election_cycle_temporal_trends
```

## Manual Refresh

To manually refresh all views:

```bash
sudo -u postgres /usr/local/bin/refresh_materialized_views.sh all
```

To manually refresh a specific view:

```bash
sudo -u postgres /usr/local/bin/refresh_materialized_views.sh decision_temporal_trends
```

## Monitoring

Check refresh logs:

```bash
tail -f /var/log/cia_mv_refresh.log
```

Verify view status:

```sql
SELECT 
    matviewname,
    ispopulated,
    pg_size_pretty(pg_total_relation_size('public.'||matviewname)) AS size,
    last_refresh
FROM pg_matviews
WHERE schemaname = 'public' 
  AND matviewname IN (
    'view_decision_temporal_trends',
    'view_riksdagen_politician_influence_metrics',
    'view_party_effectiveness_trends',
    'view_politician_behavioral_trends',
    'view_election_cycle_temporal_trends'
  )
ORDER BY matviewname;
```

## Performance Validation

To validate performance improvements:

```sql
-- Measure query time before and after materialization
\timing on

-- Test query on materialized view (should be <1s)
SELECT COUNT(*), MAX(decision_day) 
FROM view_decision_temporal_trends;

-- Test query on materialized view (should be <3s)
SELECT COUNT(*), MAX(network_connections) 
FROM view_riksdagen_politician_influence_metrics;
```

## Rollback Procedure

If issues arise, revert to regular views using Liquibase rollback:

```bash
cd /path/to/cia/repository
mvn liquibase:rollback -Dliquibase.rollbackCount=16 -pl service.data.impl
```

This will rollback all 16 changesets from v1.69 and restore the original regular views.

## Related Documentation

- **PATTERN_RECOGNITION_PERFORMANCE_REPORT.md**: Performance analysis identifying views for materialization
- **TEMPORAL_ANALYSIS_PERFORMANCE_REPORT.md**: Temporal framework performance analysis
- **service.data.impl/src/main/resources/db-changelog-1.69.xml**: Liquibase changelog for view conversions
- **service.data.impl/README-SCHEMA-MAINTENANCE.md**: Schema maintenance procedures
