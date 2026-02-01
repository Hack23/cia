---
name: postgresql-operations
description: Manage PostgreSQL: schema migrations, performance tuning, backups, monitoring per README-SCHEMA-MAINTENANCE.md
license: Apache-2.0
---

# PostgreSQL Operations Skill

## Purpose
Manage PostgreSQL database operations including migrations, performance tuning, and backups.

## When to Use
- ✅ Schema migrations with Liquibase
- ✅ Performance optimization
- ✅ Backup and recovery
- ✅ Monitoring and alerting

## Liquibase Migrations
```xml
<changeSet id="1" author="developer">
    <createTable tableName="politician">
        <column name="id" type="VARCHAR(12)">
            <constraints primaryKey="true"/>
        </column>
        <column name="first_name" type="VARCHAR(50)">
            <constraints nullable="false"/>
        </column>
        <column name="last_name" type="VARCHAR(50)">
            <constraints nullable="false"/>
        </column>
    </createTable>
</changeSet>
```

## Performance Tuning
```sql
-- Add indexes for frequently queried columns
CREATE INDEX idx_politician_party ON politician(party);
CREATE INDEX idx_voting_record_date ON voting_record(vote_date);

-- Analyze query performance
EXPLAIN ANALYZE 
SELECT * FROM politician WHERE party = 'S' AND district = 'Stockholm';

-- Update statistics
ANALYZE politician;
```

## Backup Strategy
```bash
# Daily backups
pg_dump -h localhost -U cia_user -d cia_production > backup_$(date +%Y%m%d).sql

# Point-in-time recovery setup
wal_level = replica
archive_mode = on
archive_command = 'cp %p /backup/archive/%f'
```

## Monitoring
```sql
-- Check connection count
SELECT count(*) FROM pg_stat_activity;

-- Check slow queries
SELECT query, calls, total_time, mean_time 
FROM pg_stat_statements 
ORDER BY mean_time DESC LIMIT 10;
```

## References
- PostgreSQL: https://www.postgresql.org/docs/
- service.data.impl/README-SCHEMA-MAINTENANCE.md