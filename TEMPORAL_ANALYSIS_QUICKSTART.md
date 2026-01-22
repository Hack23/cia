# Temporal Analysis Enhanced Statistics - Quick Start Guide

This guide helps you leverage the enhanced PostgreSQL statistics capabilities (PR #8271) for temporal analysis.

## What's New

### Enhanced Statistics Tracking (PR #8271)
- **Planning vs Execution Time**: Separate visibility into query planning overhead
- **Buffer Statistics**: Cache hit ratios and I/O patterns
- **Auto-explain**: Automatic logging of slow queries (>1000ms)
- **I/O Timing**: Detailed I/O timing in EXPLAIN output

### Schema Improvements (PR #8274)
- Fixed Liquibase issues
- Added 2 new temporal views (29 total, up from 27)
- All materialized views properly configured

## Quick Start

### 1. Run the Optimization Script
```bash
psql -d cia_dev -f TEMPORAL_VIEWS_OPTIMIZATION.sql
```

This will:
- Refresh all 20 materialized views
- Verify enhanced statistics configuration
- Check statistics freshness
- Display buffer hit ratios

### 2. Collect Enhanced Statistics
```bash
python3 collect_enhanced_temporal_stats.py --db cia_dev --user postgres
```

This will:
- Reset pg_stat_statements
- Query all 29 temporal views
- Collect planning vs execution time data
- Gather buffer statistics
- Verify statistics collection status

### 3. Run Validation
```bash
./validate_temporal_views.sh
```

This will:
- Test all temporal views against performance targets
- Display enhanced statistics summary
- Show buffer hit ratios
- Verify statistics freshness

## Key Files

| File | Purpose | Size |
|------|---------|------|
| `TEMPORAL_ANALYSIS_PERFORMANCE_REPORT.md` | Comprehensive performance analysis with enhanced statistics | 61K |
| `TEMPORAL_VIEWS_OPTIMIZATION.sql` | Optimization script with statistics validation | 11K |
| `collect_enhanced_temporal_stats.py` | Python script to collect enhanced statistics | 11K |
| `validate_temporal_views.sh` | Validation script with enhanced checks | 7.9K |
| `TEMPORAL_ANALYSIS_UPDATE_SUMMARY.md` | Summary of all enhancements | 9.5K |

## Enhanced Monitoring Queries

### Planning vs Execution Time
```sql
SELECT 
    LEFT(query, 60) as view_query,
    calls,
    ROUND(mean_plan_time::numeric, 2) as plan_ms,
    ROUND(mean_exec_time::numeric, 2) as exec_ms,
    ROUND((100.0 * mean_plan_time / NULLIF(mean_plan_time + mean_exec_time, 0))::numeric, 1) as plan_pct
FROM pg_stat_statements 
WHERE query LIKE '%_summary_%' 
ORDER BY (mean_plan_time + mean_exec_time) DESC 
LIMIT 10;
```

### Buffer Hit Ratio
```sql
SELECT 
    relname,
    heap_blks_hit,
    heap_blks_read,
    ROUND(100.0 * heap_blks_hit / NULLIF(heap_blks_hit + heap_blks_read, 0), 2) as hit_ratio_pct
FROM pg_statio_user_tables
WHERE relname LIKE '%vote_data%' 
   OR relname LIKE '%application_action_event%'
ORDER BY (heap_blks_hit + heap_blks_read) DESC;
```

### Statistics Freshness
```sql
SELECT 
    relname,
    last_analyze,
    n_live_tup,
    n_mod_since_analyze,
    ROUND(100.0 * n_mod_since_analyze / NULLIF(n_live_tup, 0), 2) as staleness_pct
FROM pg_stat_user_tables
WHERE relname IN ('vote_data', 'application_action_event', 'document_data')
ORDER BY staleness_pct DESC;
```

## Key Findings

### Planning Time Dominates
- **91-100%** of query time is planning for materialized views
- **Recommendation**: Use prepared statements to amortize planning cost

### Execution is Fast
- **<0.2ms** execution time for all materialized views
- **Recommendation**: Materialize frequently accessed views

### Buffer Hit Ratio
- **100%** hit ratio where data is accessed
- **Recommendation**: Monitor as data volume grows

### Statistics Are Current
- **0%** staleness on all base tables
- **Recommendation**: Run ANALYZE after bulk data loads

## Production Deployment Checklist

- [ ] Load production data into base tables
- [ ] Run `ANALYZE;` on all tables
- [ ] Refresh all materialized views
- [ ] Run `TEMPORAL_VIEWS_OPTIMIZATION.sql`
- [ ] Validate performance targets with real data
- [ ] Set up pg_stat_statements monitoring
- [ ] Configure auto_explain log monitoring
- [ ] Schedule materialized view refresh (pg_cron)

## Performance Targets

| Granularity | Target | Current (Empty) | Status |
|-------------|--------|-----------------|--------|
| Daily | <250ms | <1ms | ✅ Pass |
| Weekly | <400ms | <1ms | ✅ Pass |
| Monthly | <800ms | <1ms | ✅ Pass |
| Annual | <1500ms | <12ms | ✅ Pass |

## Troubleshooting

### Materialized View Not Refreshed
```sql
-- Check if view needs refresh
SELECT matviewname, pg_size_pretty(pg_total_relation_size(matviewname::regclass))
FROM pg_matviews
WHERE schemaname = 'public' AND matviewname LIKE '%_summary_%';

-- Refresh specific view
REFRESH MATERIALIZED VIEW view_name;
```

### Statistics Out of Date
```sql
-- Check staleness
SELECT relname, n_mod_since_analyze, n_live_tup
FROM pg_stat_user_tables
WHERE n_mod_since_analyze > 0
ORDER BY n_mod_since_analyze DESC;

-- Update statistics
ANALYZE table_name;
```

### Low Buffer Hit Ratio
```sql
-- Check hit ratio
SELECT relname, 
       heap_blks_read,
       heap_blks_hit,
       ROUND(100.0 * heap_blks_hit / NULLIF(heap_blks_hit + heap_blks_read, 0), 2) as ratio
FROM pg_statio_user_tables
WHERE heap_blks_read > 0
ORDER BY ratio ASC;

-- Consider: Increase shared_buffers if ratio <95%
```

## Additional Resources

- [Enhanced Statistics Analysis](TEMPORAL_ANALYSIS_PERFORMANCE_REPORT.md#enhanced-statistics-analysis-pr-8271)
- [Update Summary](TEMPORAL_ANALYSIS_UPDATE_SUMMARY.md)
- [PostgreSQL pg_stat_statements Documentation](https://www.postgresql.org/docs/16/pgstatstatements.html)
- [PostgreSQL auto_explain Documentation](https://www.postgresql.org/docs/16/auto-explain.html)

## Support

For issues or questions:
1. Check the [TEMPORAL_ANALYSIS_PERFORMANCE_REPORT.md](TEMPORAL_ANALYSIS_PERFORMANCE_REPORT.md) for detailed analysis
2. Review [TEMPORAL_ANALYSIS_UPDATE_SUMMARY.md](TEMPORAL_ANALYSIS_UPDATE_SUMMARY.md) for enhancement details
3. Consult the PostgreSQL logs: `/var/log/postgresql/postgresql-16-main.log`

---

**Last Updated**: 2026-01-22  
**Version**: Enhanced Statistics (PR #8271 + PR #8274)
