## 🎯 Objective

Optimize database query performance through strategic indexing and elimination of N+1 query patterns to improve dashboard and analytics response times.

## 📋 Background

The CIA platform processes large datasets of political information, including parliamentary votes, politician data, party information, and committee activities. As the database grows, query performance becomes critical for providing responsive dashboards and analytics. This task focuses on identifying and resolving performance bottlenecks through database optimization.

**Current Challenges:**
- Large tables with millions of records (votes, documents, ballot data)
- Complex joins across multiple tables
- Potential N+1 query patterns in data access layer
- Dashboard load times may increase with data growth

**Performance Goals:**
- Dashboard page load < 2 seconds
- Analytics queries < 5 seconds
- Efficient data aggregation for summaries
- Optimized view rendering

**References:**
- [DATA_MODEL.md](https://github.com/Hack23/cia/blob/master/DATA_MODEL.md) - Database structure
- [ARCHITECTURE.md](https://github.com/Hack23/cia/blob/master/ARCHITECTURE.md) - System architecture
- [SWOT.md](https://github.com/Hack23/cia/blob/master/SWOT.md) - Identifies manual data processing as weakness

## ✅ Acceptance Criteria

- [ ] Analyze slow queries using PostgreSQL slow query log and pg_stat_statements
- [ ] Identify missing indexes on frequently queried columns
- [ ] Create database migration scripts for new indexes
- [ ] Identify and eliminate N+1 query patterns in JPA repositories
- [ ] Add database query metrics to monitoring dashboard
- [ ] Document performance improvements with before/after metrics
- [ ] Update entity relationships to use appropriate fetch strategies
- [ ] Add query performance tests to prevent regressions

## 🛠️ Implementation Guidance

### Files to Analyze/Modify

1. **JPA Entity Classes** - Review fetch strategies
   - `model.internal.application.data.impl/*Entity.java`
   - Look for `@OneToMany`, `@ManyToOne`, `@ManyToMany` relationships
   - Check `FetchType.LAZY` vs `FetchType.EAGER`

2. **Repository Classes** - Optimize queries
   - `service.data.impl/src/main/java/*/repository/*.java`
   - Add `@Query` annotations with JOIN FETCH where needed
   - Use `@EntityGraph` for complex fetches

3. **Database Migration Scripts** - Add indexes
   - Create: `service.data.impl/src/main/resources/db/migration/V{X}__add_performance_indexes.sql`

4. **Configuration** - Enable query logging
   - `citizen-intelligence-agency/src/main/resources/application.properties`
   - Add Hibernate statistics logging

### Approach

1. **Enable PostgreSQL Query Analysis**
   ```sql
   -- Enable pg_stat_statements extension (already configured)
   CREATE EXTENSION IF NOT EXISTS pg_stat_statements;
   
   -- Enable slow query logging in postgresql.conf
   -- log_min_duration_statement = 1000  # Log queries > 1 second
   
   -- View slowest queries
   SELECT 
     query,
     calls,
     total_exec_time,
     mean_exec_time,
     max_exec_time
   FROM pg_stat_statements
   ORDER BY mean_exec_time DESC
   LIMIT 20;
   ```

2. **Identify Missing Indexes**
   ```sql
   -- Find tables with sequential scans
   SELECT 
     schemaname,
     tablename,
     seq_scan,
     seq_tup_read,
     idx_scan,
     seq_tup_read / seq_scan AS avg_seq_read
   FROM pg_stat_user_tables
   WHERE seq_scan > 0
   ORDER BY seq_tup_read DESC
   LIMIT 20;
   
   -- Check for missing indexes on foreign keys
   SELECT 
     c.conrelid::regclass AS table_name,
     a.attname AS column_name
   FROM pg_constraint c
   JOIN pg_attribute a ON a.attrelid = c.conrelid 
     AND a.attnum = ANY(c.conkey)
   WHERE c.contype = 'f'
   AND NOT EXISTS (
     SELECT 1 FROM pg_index i
     WHERE i.indrelid = c.conrelid
     AND a.attnum = ANY(i.indkey)
   );
   ```

3. **Enable Hibernate Statistics**
   ```properties
   # application.properties
   spring.jpa.properties.hibernate.generate_statistics=true
   spring.jpa.properties.hibernate.session.events.log=true
   
   # Log slow queries
   spring.jpa.properties.hibernate.session.events.log.LOG_QUERIES_SLOWER_THAN_MS=1000
   ```

4. **Fix N+1 Query Patterns**
   ```java
   // Before: N+1 queries
   @OneToMany(mappedBy = "politician", fetch = FetchType.LAZY)
   private List<Vote> votes;
   
   // After: Single query with JOIN FETCH
   @Query("SELECT p FROM Politician p LEFT JOIN FETCH p.votes WHERE p.id = :id")
   Politician findByIdWithVotes(@Param("id") Long id);
   
   // Or use Entity Graph
   @EntityGraph(attributePaths = {"votes", "party"})
   @Query("SELECT p FROM Politician p WHERE p.id = :id")
   Politician findByIdWithRelations(@Param("id") Long id);
   ```

5. **Create Index Migration Script**
   ```sql
   -- V100__add_performance_indexes.sql
   
   -- Add indexes on frequently queried foreign keys
   CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_vote_politician_id 
     ON vote(politician_id);
   
   CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_vote_ballot_id 
     ON vote(ballot_id);
   
   CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_document_politician_id 
     ON document(politician_id);
   
   -- Add composite indexes for common queries
   CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_vote_politician_date 
     ON vote(politician_id, vote_date);
   
   -- Add indexes on frequently filtered columns
   CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_politician_party 
     ON politician(party_id) WHERE active = true;
   
   -- Analyze tables after indexing
   ANALYZE vote;
   ANALYZE document;
   ANALYZE politician;
   ```

6. **Performance Testing**
   ```java
   @Test
   public void testDashboardQueryPerformance() {
       long startTime = System.currentTimeMillis();
       
       // Execute dashboard query
       List<PoliticianSummary> summaries = 
           politicianService.getDashboardSummaries();
       
       long duration = System.currentTimeMillis() - startTime;
       
       // Assert performance target
       assertThat(duration, lessThan(2000L)); // < 2 seconds
       
       // Verify no N+1 queries (Hibernate statistics)
       Statistics stats = sessionFactory.getStatistics();
       assertThat(stats.getPrepareStatementCount(), lessThan(10L));
   }
   ```

### Common Performance Patterns to Fix

**Pattern 1: N+1 on Collections**
```java
// Problem: Loads each politician's votes in separate query
for (Politician p : politicians) {
    p.getVotes().size(); // N+1 query
}

// Solution: Use JOIN FETCH
@Query("SELECT DISTINCT p FROM Politician p LEFT JOIN FETCH p.votes")
List<Politician> findAllWithVotes();
```

**Pattern 2: Missing Indexes on Date Ranges**
```sql
-- Add index for date range queries
CREATE INDEX idx_vote_date ON vote(vote_date);

-- Add partial index for recent data
CREATE INDEX idx_vote_recent 
  ON vote(vote_date) 
  WHERE vote_date > CURRENT_DATE - INTERVAL '1 year';
```

**Pattern 3: Inefficient Aggregations**
```sql
-- Use materialized view for complex aggregations
CREATE MATERIALIZED VIEW mv_politician_summary AS
SELECT 
  p.id,
  p.name,
  COUNT(v.id) as vote_count,
  COUNT(d.id) as document_count
FROM politician p
LEFT JOIN vote v ON v.politician_id = p.id
LEFT JOIN document d ON d.politician_id = p.id
GROUP BY p.id, p.name;

CREATE UNIQUE INDEX ON mv_politician_summary(id);

-- Refresh periodically
REFRESH MATERIALIZED VIEW CONCURRENTLY mv_politician_summary;
```

### Monitoring and Validation

**Before Optimization:**
```bash
# Capture baseline metrics
pg_dump --schema-only cia_dev > schema_before.sql
EXPLAIN ANALYZE <slow_query> > query_plan_before.txt
```

**After Optimization:**
```bash
# Compare improvements
EXPLAIN ANALYZE <slow_query> > query_plan_after.txt
diff query_plan_before.txt query_plan_after.txt

# Verify index usage
SELECT 
  schemaname,
  tablename,
  indexname,
  idx_scan,
  idx_tup_read,
  idx_tup_fetch
FROM pg_stat_user_indexes
ORDER BY idx_scan DESC;
```

## 🔗 Related Resources

- [PostgreSQL Performance Tuning](https://www.postgresql.org/docs/16/performance-tips.html)
- [Hibernate Performance Guide](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#best-practices)
- [JPA Query Optimization](https://vladmihalcea.com/hibernate-query-plan-cache/)
- [pg_stat_statements Documentation](https://www.postgresql.org/docs/16/pgstatstatements.html)
- [Database Indexing Best Practices](https://use-the-index-luke.com/)

## 📊 Metadata

**Priority:** Medium  
**Effort:** Medium (1-2 days)  
**Labels:** `performance`, `database`, `enhancement`, `optimization`  
**Milestone:** Next Release  
**Impact:** Improved user experience with faster dashboard and analytics response times
