-- schema-health-check.sql
-- Comprehensive Database Schema Health Check
-- Citizen Intelligence Agency - Open Source Intelligence Platform
-- Generated for PostgreSQL 16
--
-- Purpose: Validates database schema integrity, checks view dependencies,
--          verifies data consistency, analyzes performance characteristics,
--          and provides actionable recommendations for improvements.
--
-- Dependencies: Builds on schema-validation.sql from Issue #7865
--
-- Usage:
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/schema-health-check.sql > health_check_report.txt 2>&1
--
-- Output formats:
--   1. Text report with health score and recommendations
--   2. JSON export for automation and monitoring

\set ON_ERROR_STOP off
\timing on

\echo '=================================================='
\echo 'CIA Database Schema Health Check'
\echo 'Started:' `date`
\echo 'Database:' :DBNAME
\echo '=================================================='

-- Create temporary table to store health check results
DROP TABLE IF EXISTS health_check_results;
CREATE TEMP TABLE health_check_results (
    category VARCHAR(50),
    check_name VARCHAR(100),
    status VARCHAR(20),  -- PASS, WARN, FAIL, INFO
    severity INTEGER,     -- 1=INFO, 2=WARNING, 3=ERROR, 4=CRITICAL
    details TEXT,
    recommendation TEXT
);

-- ============================================
-- SECTION 1: Schema Integrity Checks
-- ============================================
\echo ''
\echo '=========================================='
\echo '=== SCHEMA INTEGRITY CHECKS          ==='
\echo '=========================================='

-- Check 1.1: Validate all foreign key constraints
\echo ''
\echo 'Checking foreign key integrity...'
DO $$
DECLARE
    fk_record RECORD;
    orphan_count BIGINT;
    check_query TEXT;
BEGIN
    FOR fk_record IN
        SELECT 
            tc.constraint_name,
            tc.table_schema,
            tc.table_name,
            kcu.column_name,
            ccu.table_schema AS foreign_table_schema,
            ccu.table_name AS foreign_table_name,
            ccu.column_name AS foreign_column_name
        FROM information_schema.table_constraints AS tc
        JOIN information_schema.key_column_usage AS kcu
            ON tc.constraint_name = kcu.constraint_name
            AND tc.table_schema = kcu.table_schema
        JOIN information_schema.constraint_column_usage AS ccu
            ON ccu.constraint_name = tc.constraint_name
            AND ccu.table_schema = tc.table_schema
        WHERE tc.constraint_type = 'FOREIGN KEY'
            AND tc.table_schema = 'public'
        ORDER BY tc.table_name, kcu.column_name
    LOOP
        BEGIN
            -- Build query to check for orphaned records
            check_query := format(
                'SELECT COUNT(*) FROM %I.%I child ' ||
                'LEFT JOIN %I.%I parent ON child.%I = parent.%I ' ||
                'WHERE child.%I IS NOT NULL AND parent.%I IS NULL',
                fk_record.table_schema, fk_record.table_name,
                fk_record.foreign_table_schema, fk_record.foreign_table_name,
                fk_record.column_name, fk_record.foreign_column_name,
                fk_record.column_name, fk_record.foreign_column_name
            );
            
            EXECUTE check_query INTO orphan_count;
            
            INSERT INTO health_check_results VALUES (
                'Schema Integrity',
                'Foreign Key: ' || fk_record.table_name || '.' || fk_record.column_name,
                CASE WHEN orphan_count = 0 THEN 'PASS' ELSE 'FAIL' END,
                CASE WHEN orphan_count = 0 THEN 1 ELSE 4 END,
                'Found ' || orphan_count || ' orphaned records',
                CASE WHEN orphan_count > 0 
                    THEN 'Clean up orphaned records or add missing parent records in ' || fk_record.foreign_table_name
                    ELSE NULL 
                END
            );
        EXCEPTION WHEN OTHERS THEN
            INSERT INTO health_check_results VALUES (
                'Schema Integrity',
                'Foreign Key: ' || fk_record.table_name || '.' || fk_record.column_name,
                'WARN',
                2,
                'Could not check: ' || SQLERRM,
                'Review foreign key constraint definition'
            );
        END;
    END LOOP;
END $$;

-- Check 1.2: Validate view definitions (no broken views)
\echo 'Checking view integrity...'
DO $$
DECLARE
    r RECORD;
    v_error TEXT;
BEGIN
    FOR r IN 
        SELECT schemaname, viewname 
        FROM pg_views 
        WHERE schemaname = 'public'
        ORDER BY viewname
    LOOP
        BEGIN
            EXECUTE format('SELECT COUNT(*) FROM %I.%I LIMIT 1', r.schemaname, r.viewname);
            INSERT INTO health_check_results VALUES (
                'Schema Integrity',
                'View: ' || r.viewname,
                'PASS',
                1,
                'View definition is valid',
                NULL
            );
        EXCEPTION WHEN OTHERS THEN
            v_error := SQLERRM;
            INSERT INTO health_check_results VALUES (
                'Schema Integrity',
                'View: ' || r.viewname,
                'FAIL',
                4,
                'View has broken definition: ' || v_error,
                'Recreate view or fix dependencies. See TROUBLESHOOTING_EMPTY_VIEWS.md'
            );
        END;
    END LOOP;
END $$;

-- Check 1.3: Materialized views freshness
\echo 'Checking materialized view freshness...'
INSERT INTO health_check_results
SELECT
    'Schema Integrity' AS category,
    'Materialized View Freshness: ' || m.matviewname AS check_name,
    CASE
        WHEN s.last_vacuum IS NULL AND s.last_autovacuum IS NULL THEN 'WARN'
        WHEN GREATEST(s.last_vacuum, s.last_autovacuum) < NOW() - INTERVAL '7 days' THEN 'WARN'
        WHEN m.ispopulated = false THEN 'FAIL'
        ELSE 'PASS'
    END AS status,
    CASE
        WHEN m.ispopulated = false THEN 4
        WHEN s.last_vacuum IS NULL AND s.last_autovacuum IS NULL THEN 2
        WHEN GREATEST(s.last_vacuum, s.last_autovacuum) < NOW() - INTERVAL '7 days' THEN 2
        ELSE 1
    END AS severity,
    CASE
        WHEN m.ispopulated = false THEN 'Materialized view is not populated'
        WHEN s.last_vacuum IS NULL AND s.last_autovacuum IS NULL THEN 'Never refreshed'
        ELSE 'Last activity: ' || COALESCE(GREATEST(s.last_vacuum, s.last_autovacuum)::TEXT, 'UNKNOWN')
    END AS details,
    CASE
        WHEN m.ispopulated = false OR (s.last_vacuum IS NULL AND s.last_autovacuum IS NULL)
        THEN 'Refresh materialized view: psql -d cia_dev -f refresh-all-views.sql'
        WHEN GREATEST(s.last_vacuum, s.last_autovacuum) < NOW() - INTERVAL '7 days'
        THEN 'Consider refreshing: REFRESH MATERIALIZED VIEW ' || m.matviewname || ';'
        ELSE NULL
    END AS recommendation
FROM pg_matviews m
LEFT JOIN pg_stat_user_tables s ON s.schemaname = m.schemaname AND s.relname = m.matviewname
WHERE m.schemaname = 'public';

-- ============================================
-- SECTION 2: Data Quality Checks
-- ============================================
\echo ''
\echo '=========================================='
\echo '=== DATA QUALITY CHECKS              ==='
\echo '=========================================='

-- Check 2.1: Empty critical tables
\echo ''
\echo 'Checking for empty critical tables...'
DO $$
DECLARE
    v_table TEXT;
    v_count BIGINT;
    v_critical_tables TEXT[] := ARRAY[
        'person_data',
        'assignment_data',
        'ballot_data',
        'vote_data',
        'document_data',
        'sweden_political_party',
        'committee_proposal_data',
        'document_element',
        'document_person_reference_data'
    ];
BEGIN
    FOREACH v_table IN ARRAY v_critical_tables
    LOOP
        BEGIN
            EXECUTE format('SELECT COUNT(*) FROM %I', v_table) INTO v_count;
            
            IF v_count = 0 THEN
                INSERT INTO health_check_results VALUES (
                    'Data Quality',
                    'Critical Table: ' || v_table,
                    'FAIL',
                    4,
                    'Table is empty (0 rows)',
                    'Load data into table or check data import processes. See TROUBLESHOOTING_EMPTY_VIEWS.md'
                );
            ELSIF v_count < 100 THEN
                INSERT INTO health_check_results VALUES (
                    'Data Quality',
                    'Critical Table: ' || v_table,
                    'WARN',
                    2,
                    'Table has only ' || v_count || ' rows',
                    'Verify data completeness and import status'
                );
            ELSE
                INSERT INTO health_check_results VALUES (
                    'Data Quality',
                    'Critical Table: ' || v_table,
                    'PASS',
                    1,
                    'Table has ' || v_count || ' rows',
                    NULL
                );
            END IF;
        EXCEPTION WHEN OTHERS THEN
            INSERT INTO health_check_results VALUES (
                'Data Quality',
                'Critical Table: ' || v_table,
                'INFO',
                1,
                'Table does not exist or cannot be accessed: ' || SQLERRM,
                'Verify table name and permissions'
            );
        END;
    END LOOP;
END $$;

-- Check 2.2: NULL values in critical columns
\echo 'Checking for NULLs in critical columns...'
DO $$
DECLARE
    null_count BIGINT;
BEGIN
    -- Check person_data.person_id
    BEGIN
        SELECT COUNT(*) INTO null_count FROM person_data WHERE person_id IS NULL;
        INSERT INTO health_check_results VALUES (
            'Data Quality',
            'NULL Check: person_data.person_id',
            CASE WHEN null_count > 0 THEN 'FAIL' ELSE 'PASS' END,
            CASE WHEN null_count > 0 THEN 3 ELSE 1 END,
            'Found ' || null_count || ' NULL person_id values',
            CASE WHEN null_count > 0 THEN 'Fix NULL values in person_data.person_id' ELSE NULL END
        );
    EXCEPTION WHEN OTHERS THEN
        NULL; -- Table may not exist
    END;

    -- Check ballot_data.ballot_id
    BEGIN
        SELECT COUNT(*) INTO null_count FROM ballot_data WHERE ballot_id IS NULL;
        INSERT INTO health_check_results VALUES (
            'Data Quality',
            'NULL Check: ballot_data.ballot_id',
            CASE WHEN null_count > 0 THEN 'FAIL' ELSE 'PASS' END,
            CASE WHEN null_count > 0 THEN 3 ELSE 1 END,
            'Found ' || null_count || ' NULL ballot_id values',
            CASE WHEN null_count > 0 THEN 'Fix NULL values in ballot_data.ballot_id' ELSE NULL END
        );
    EXCEPTION WHEN OTHERS THEN
        NULL; -- Table may not exist
    END;

    -- Check document_data.id
    BEGIN
        SELECT COUNT(*) INTO null_count FROM document_data WHERE id IS NULL;
        INSERT INTO health_check_results VALUES (
            'Data Quality',
            'NULL Check: document_data.id',
            CASE WHEN null_count > 0 THEN 'FAIL' ELSE 'PASS' END,
            CASE WHEN null_count > 0 THEN 3 ELSE 1 END,
            'Found ' || null_count || ' NULL id values',
            CASE WHEN null_count > 0 THEN 'Fix NULL values in document_data.id' ELSE NULL END
        );
    EXCEPTION WHEN OTHERS THEN
        NULL; -- Table may not exist
    END;
END $$;

-- Check 2.3: Duplicate checks in key views
\echo 'Checking for duplicates...'
DO $$
DECLARE
    dup_count BIGINT;
BEGIN
    -- Check for duplicate person_ids in view_riksdagen_politician
    BEGIN
        WITH duplicate_politicians AS (
            SELECT person_id, COUNT(*) as count
            FROM view_riksdagen_politician
            GROUP BY person_id
            HAVING COUNT(*) > 1
        )
        SELECT COUNT(*) INTO dup_count FROM duplicate_politicians;
        
        INSERT INTO health_check_results VALUES (
            'Data Quality',
            'Duplicate Check: view_riksdagen_politician.person_id',
            CASE WHEN dup_count > 0 THEN 'FAIL' ELSE 'PASS' END,
            CASE WHEN dup_count > 0 THEN 3 ELSE 1 END,
            'Found ' || dup_count || ' duplicate person_ids',
            CASE WHEN dup_count > 0 THEN 'Investigate and remove duplicates in view definition' ELSE NULL END
        );
    EXCEPTION WHEN OTHERS THEN
        INSERT INTO health_check_results VALUES (
            'Data Quality',
            'Duplicate Check: view_riksdagen_politician',
            'INFO',
            1,
            'View does not exist or cannot be checked: ' || SQLERRM,
            NULL
        );
    END;
END $$;

-- Check 2.4: NULL percentage in critical columns
\echo 'Checking NULL percentage in critical columns...'
DO $$
DECLARE
    v_null_pct NUMERIC;
    v_critical_columns TEXT[][] := ARRAY[
        ['person_data', 'first_name'],
        ['person_data', 'last_name'],
        ['assignment_data', 'org_code'],
        ['vote_data', 'vote']
    ];
    v_col TEXT[];
BEGIN
    FOREACH v_col SLICE 1 IN ARRAY v_critical_columns
    LOOP
        BEGIN
            EXECUTE format(
                'SELECT ROUND(COUNT(*) FILTER (WHERE %I IS NULL) * 100.0 / NULLIF(COUNT(*), 0), 2) FROM %I',
                v_col[2], v_col[1]
            ) INTO v_null_pct;
            
            INSERT INTO health_check_results VALUES (
                'Data Quality',
                'NULL Percentage: ' || v_col[1] || '.' || v_col[2],
                CASE 
                    WHEN v_null_pct > 10 THEN 'FAIL'
                    WHEN v_null_pct > 5 THEN 'WARN'
                    ELSE 'PASS'
                END,
                CASE 
                    WHEN v_null_pct > 10 THEN 3
                    WHEN v_null_pct > 5 THEN 2
                    ELSE 1
                END,
                COALESCE(v_null_pct::TEXT, '0') || '% NULL values',
                CASE 
                    WHEN v_null_pct > 5 THEN 'Investigate and fix NULL values in ' || v_col[1] || '.' || v_col[2]
                    ELSE NULL
                END
            );
        EXCEPTION WHEN OTHERS THEN
            -- Table or column doesn't exist, skip
            NULL;
        END;
    END LOOP;
END $$;

-- Check 2.5: Data distribution analysis (party balance)
\echo 'Checking data distribution...'
DO $$
DECLARE
    v_stddev NUMERIC;
    v_avg NUMERIC;
    v_coefficient NUMERIC;
BEGIN
    BEGIN
        WITH person_party_distribution AS (
            SELECT party, COUNT(*) as count
            FROM person_data
            WHERE party IS NOT NULL
            GROUP BY party
        )
        SELECT 
            STDDEV(count),
            AVG(count),
            CASE WHEN AVG(count) > 0 THEN STDDEV(count) / AVG(count) ELSE 0 END
        INTO v_stddev, v_avg, v_coefficient
        FROM person_party_distribution;
        
        INSERT INTO health_check_results VALUES (
            'Data Quality',
            'Party Distribution Balance',
            CASE 
                WHEN v_coefficient > 1.5 THEN 'WARN'
                ELSE 'PASS'
            END,
            CASE 
                WHEN v_coefficient > 1.5 THEN 2
                ELSE 1
            END,
            'Distribution coefficient: ' || COALESCE(ROUND(v_coefficient, 2)::TEXT, 'N/A') || 
            ' | Avg per party: ' || COALESCE(ROUND(v_avg, 0)::TEXT, 'N/A'),
            CASE 
                WHEN v_coefficient > 1.5 THEN 'Party distribution imbalance detected - investigate if intentional'
                ELSE NULL
            END
        );
    EXCEPTION WHEN OTHERS THEN
        INSERT INTO health_check_results VALUES (
            'Data Quality',
            'Party Distribution Balance',
            'INFO',
            1,
            'Could not analyze: ' || SQLERRM,
            NULL
        );
    END;
END $$;

-- ============================================
-- SECTION 3: Performance Analysis
-- ============================================
\echo ''
\echo '=========================================='
\echo '=== PERFORMANCE ANALYSIS             ==='
\echo '=========================================='

-- Check 3.1: Missing indexes on foreign keys
\echo ''
\echo 'Checking for missing indexes on foreign keys...'
INSERT INTO health_check_results
SELECT
    'Performance' AS category,
    'Missing Index: ' || fk.table_name || '.' || fk.column_name AS check_name,
    CASE WHEN idx.indexname IS NULL THEN 'WARN' ELSE 'PASS' END AS status,
    CASE WHEN idx.indexname IS NULL THEN 2 ELSE 1 END AS severity,
    CASE 
        WHEN idx.indexname IS NULL THEN 'No index found on foreign key column'
        ELSE 'Index exists: ' || idx.indexname
    END AS details,
    CASE 
        WHEN idx.indexname IS NULL THEN 'CREATE INDEX idx_' || fk.table_name || '_' || fk.column_name || ' ON ' || fk.table_name || '(' || fk.column_name || ');'
        ELSE NULL
    END AS recommendation
FROM (
    SELECT DISTINCT
        tc.table_schema,
        tc.table_name,
        kcu.column_name
    FROM information_schema.table_constraints AS tc
    JOIN information_schema.key_column_usage AS kcu
        ON tc.constraint_name = kcu.constraint_name
        AND tc.table_schema = kcu.table_schema
    WHERE tc.constraint_type = 'FOREIGN KEY'
        AND tc.table_schema = 'public'
) fk
LEFT JOIN LATERAL (
    SELECT
        ci.relname AS indexname
    FROM pg_class ct
    JOIN pg_namespace ns ON ns.oid = ct.relnamespace
    JOIN pg_index i ON i.indrelid = ct.oid
    JOIN pg_class ci ON ci.oid = i.indexrelid
    JOIN pg_attribute a ON a.attrelid = ct.oid AND a.attname = fk.column_name
    WHERE ns.nspname = fk.table_schema
      AND ct.relname = fk.table_name
      AND i.indisvalid
      AND i.indisready
      AND i.indisprimary = false
      AND i.indkey[0] = a.attnum
    LIMIT 1
) idx ON TRUE;

-- Check 3.2: Large tables without recent vacuum
\echo 'Checking table maintenance...'
INSERT INTO health_check_results
SELECT
    'Performance' AS category,
    'Table Maintenance: ' || schemaname || '.' || relname AS check_name,
    CASE 
        WHEN last_vacuum IS NULL THEN 'WARN'
        WHEN last_vacuum < NOW() - INTERVAL '30 days' THEN 'WARN'
        ELSE 'PASS'
    END AS status,
    CASE 
        WHEN last_vacuum IS NULL THEN 2
        WHEN last_vacuum < NOW() - INTERVAL '30 days' THEN 2
        ELSE 1
    END AS severity,
    'Last vacuum: ' || COALESCE(last_vacuum::TEXT, 'NEVER') || 
    ' | Size: ' || pg_size_pretty(pg_total_relation_size(relid)) AS details,
    CASE 
        WHEN last_vacuum IS NULL OR last_vacuum < NOW() - INTERVAL '30 days'
        THEN 'VACUUM ANALYZE ' || quote_ident(schemaname) || '.' || quote_ident(relname) || ';'
        ELSE NULL
    END AS recommendation
FROM pg_stat_user_tables
WHERE schemaname = 'public'
    AND n_live_tup > 1000
ORDER BY last_vacuum ASC NULLS FIRST
LIMIT 10;

-- Check 3.3: Slow query patterns (from pg_stat_statements if available)
\echo 'Checking for slow query patterns...'
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM pg_extension WHERE extname = 'pg_stat_statements') THEN
        INSERT INTO health_check_results
        SELECT
            'Performance' AS category,
            'Slow Query: ' || LEFT(query, 50) || '...' AS check_name,
            'WARN' AS status,
            2 AS severity,
            'Avg time: ' || ROUND(mean_exec_time::NUMERIC, 2) || 'ms | Calls: ' || calls AS details,
            'Optimize query or add indexes' AS recommendation
        FROM pg_stat_statements
        WHERE mean_exec_time > 1000  -- > 1 second
        ORDER BY mean_exec_time DESC
        LIMIT 10;
    ELSE
        INSERT INTO health_check_results VALUES (
            'Performance',
            'pg_stat_statements Extension',
            'INFO',
            1,
            'Extension not enabled',
            'Enable pg_stat_statements for query performance tracking: CREATE EXTENSION pg_stat_statements;'
        );
    END IF;
END $$;

-- Check 3.4: Bloated tables and indexes
\echo 'Checking for table bloat...'
INSERT INTO health_check_results
SELECT
    'Performance' AS category,
    'Table Bloat: ' || schemaname || '.' || relname AS check_name,
    CASE 
        WHEN n_dead_tup > n_live_tup * 0.2 THEN 'WARN'
        ELSE 'PASS'
    END AS status,
    CASE 
        WHEN n_dead_tup > n_live_tup * 0.2 THEN 2
        ELSE 1
    END AS severity,
    'Live tuples: ' || n_live_tup || ' | Dead tuples: ' || n_dead_tup || 
    ' | Dead ratio: ' || ROUND(n_dead_tup::NUMERIC / NULLIF(n_live_tup, 0) * 100, 2) || '%' AS details,
    CASE 
        WHEN n_dead_tup > n_live_tup * 0.2
        THEN 'VACUUM FULL ' || schemaname || '.' || relname || '; -- WARNING: Locks table'
        ELSE NULL
    END AS recommendation
FROM pg_stat_user_tables
WHERE schemaname = 'public'
    AND n_live_tup > 0
    AND n_dead_tup > n_live_tup * 0.2
ORDER BY n_dead_tup DESC
LIMIT 10;

-- Check 3.5: Connection pool usage
\echo 'Checking connection pool usage...'
INSERT INTO health_check_results
SELECT
    'Performance' AS category,
    'Connection Pool Usage' AS check_name,
    CASE 
        WHEN active_connections * 100.0 / max_connections > 90 THEN 'FAIL'
        WHEN active_connections * 100.0 / max_connections > 80 THEN 'WARN'
        ELSE 'PASS'
    END AS status,
    CASE 
        WHEN active_connections * 100.0 / max_connections > 90 THEN 3
        WHEN active_connections * 100.0 / max_connections > 80 THEN 2
        ELSE 1
    END AS severity,
    'Active: ' || active_connections || ' / Max: ' || max_connections || 
    ' (' || ROUND(active_connections * 100.0 / max_connections, 1) || '%)' AS details,
    CASE 
        WHEN active_connections * 100.0 / max_connections > 80 
        THEN 'Increase max_connections or review connection pooling configuration'
        ELSE NULL
    END AS recommendation
FROM (
    SELECT COUNT(*) as active_connections
    FROM pg_stat_activity
    WHERE pid != pg_backend_pid()
      AND backend_type = 'client backend'
) a,
(SELECT setting::INT as max_connections FROM pg_settings WHERE name = 'max_connections') m;

-- Check 3.6: Query cache hit ratio
\echo 'Checking query cache hit ratio...'
INSERT INTO health_check_results
SELECT
    'Performance' AS category,
    'Query Cache Hit Ratio' AS check_name,
    CASE 
        WHEN hit_ratio < 80 THEN 'FAIL'
        WHEN hit_ratio < 90 THEN 'WARN'
        ELSE 'PASS'
    END AS status,
    CASE 
        WHEN hit_ratio < 80 THEN 3
        WHEN hit_ratio < 90 THEN 2
        ELSE 1
    END AS severity,
    'Hit Ratio: ' || ROUND(hit_ratio, 2) || '%' AS details,
    CASE 
        WHEN hit_ratio < 90 THEN 'Increase shared_buffers or optimize queries to reduce disk I/O'
        ELSE NULL
    END AS recommendation
FROM (
    SELECT 
        COALESCE(SUM(heap_blks_hit) * 100.0 / NULLIF(SUM(heap_blks_hit + heap_blks_read), 0), 100) AS hit_ratio
    FROM pg_statio_user_tables
) cache;

-- Check 3.7: Database lock waits
\echo 'Checking for database lock waits...'
INSERT INTO health_check_results
SELECT
    'Performance' AS category,
    'Database Lock Waits' AS check_name,
    CASE 
        WHEN lock_count > 10 THEN 'FAIL'
        WHEN lock_count > 5 THEN 'WARN'
        ELSE 'PASS'
    END AS status,
    CASE 
        WHEN lock_count > 10 THEN 3
        WHEN lock_count > 5 THEN 2
        ELSE 1
    END AS severity,
    'Current lock waits: ' || lock_count AS details,
    CASE 
        WHEN lock_count > 5 THEN 'Investigate long-running transactions: SELECT pid, usename, query, state, wait_event, wait_event_type, query_start FROM pg_stat_activity WHERE wait_event_type = ''Lock'''
        ELSE NULL
    END AS recommendation
FROM (
    SELECT COUNT(*) as lock_count
    FROM pg_stat_activity
    WHERE wait_event_type = 'Lock'
) locks;

-- ============================================
-- SECTION 4: Security Validation
-- ============================================
\echo ''
\echo '=========================================='
\echo '=== SECURITY VALIDATION              ==='
\echo '=========================================='

-- Check 4.1: User permission audit
\echo ''
\echo 'Checking user permissions...'
INSERT INTO health_check_results
SELECT
    'Security' AS category,
    'User Permissions: ' || usename AS check_name,
    CASE 
        WHEN usename = 'postgres' THEN 'INFO'  -- expected postgres superuser
        WHEN usesuper THEN 'WARN'  -- other superusers
        ELSE 'PASS'
    END AS status,
    CASE 
        WHEN usename = 'postgres' THEN 1
        WHEN usesuper THEN 2
        ELSE 1
    END AS severity,
    'User: ' || usename || ' | Superuser: ' || usesuper || ' | Create DB: ' || usecreatedb AS details,
    CASE 
        WHEN usesuper AND usename != 'postgres' THEN 'Review superuser privileges for ' || usename
        ELSE NULL
    END AS recommendation
FROM pg_user;

-- Check 4.2: SSL configuration
\echo 'Checking SSL configuration...'
DO $$
DECLARE
    ssl_setting TEXT;
BEGIN
    SELECT setting INTO ssl_setting FROM pg_settings WHERE name = 'ssl';
    
    IF ssl_setting IS NOT NULL THEN
        INSERT INTO health_check_results VALUES (
            'Security',
            'SSL Configuration',
            CASE 
                WHEN ssl_setting = 'on' THEN 'PASS'
                ELSE 'WARN'
            END,
            CASE 
                WHEN ssl_setting = 'on' THEN 1
                ELSE 2
            END,
            'SSL: ' || ssl_setting,
            CASE 
                WHEN ssl_setting != 'on' THEN 'Consider enabling SSL in postgresql.conf for encrypted connections'
                ELSE NULL
            END
        );
    ELSE
        INSERT INTO health_check_results VALUES (
            'Security',
            'SSL Configuration',
            'INFO',
            1,
            'SSL setting not found',
            'SSL may not be configured'
        );
    END IF;
END $$;

-- Check 4.3: pgaudit extension
\echo 'Checking pgaudit extension...'
INSERT INTO health_check_results
SELECT
    'Security' AS category,
    'pgaudit Extension' AS check_name,
    CASE 
        WHEN extname IS NOT NULL THEN 'PASS'
        ELSE 'INFO'
    END AS status,
    1 AS severity,
    CASE 
        WHEN extname IS NOT NULL THEN 'pgaudit is installed and active'
        ELSE 'pgaudit extension not found'
    END AS details,
    CASE 
        WHEN extname IS NULL THEN 'Consider installing pgaudit extension for comprehensive audit logging: CREATE EXTENSION pgaudit;'
        ELSE NULL
    END AS recommendation
FROM (
    SELECT extname FROM pg_extension WHERE extname = 'pgaudit'
    UNION ALL
    SELECT NULL WHERE NOT EXISTS (SELECT 1 FROM pg_extension WHERE extname = 'pgaudit')
    LIMIT 1
) ext;

-- Check 4.4: Password encryption method
\echo 'Checking password encryption...'
DO $$
DECLARE
    encryption_method TEXT;
BEGIN
    SELECT setting INTO encryption_method FROM pg_settings WHERE name = 'password_encryption';
    
    IF encryption_method IS NOT NULL THEN
        INSERT INTO health_check_results VALUES (
            'Security',
            'Password Encryption Method',
            CASE 
                WHEN encryption_method = 'scram-sha-256' THEN 'PASS'
                WHEN encryption_method = 'md5' THEN 'WARN'
                ELSE 'FAIL'
            END,
            CASE 
                WHEN encryption_method = 'scram-sha-256' THEN 1
                WHEN encryption_method = 'md5' THEN 2
                ELSE 3
            END,
            'Encryption method: ' || encryption_method,
            CASE 
                WHEN encryption_method = 'md5' THEN 'MD5 is deprecated - migrate to scram-sha-256 for better security'
                WHEN encryption_method NOT IN ('scram-sha-256', 'md5') THEN 'Use scram-sha-256 for password security'
                ELSE NULL
            END
        );
    END IF;
END $$;

-- ============================================
-- SECTION 5: Referential Integrity
-- ============================================
\echo ''
\echo '=========================================='
\echo '=== REFERENTIAL INTEGRITY CHECKS     ==='
\echo '=========================================='

-- Check 5.1: Cascade delete configuration
\echo ''
\echo 'Checking cascade delete rules...'
INSERT INTO health_check_results
SELECT
    'Referential Integrity' AS category,
    'Cascade Rule: ' || tc.table_name || ' -> ' || ccu.table_name AS check_name,
    CASE 
        WHEN rc.delete_rule = 'CASCADE' THEN 'INFO'
        WHEN rc.delete_rule = 'NO ACTION' THEN 'PASS'
        ELSE 'INFO'
    END AS status,
    1 AS severity,
    'Delete rule: ' || rc.delete_rule || ' | Update rule: ' || rc.update_rule AS details,
    CASE 
        WHEN rc.delete_rule = 'CASCADE' 
        THEN 'CASCADE delete configured - ensure this is intentional for ' || tc.table_name
        ELSE NULL
    END AS recommendation
FROM information_schema.table_constraints AS tc
JOIN information_schema.referential_constraints AS rc
    ON tc.constraint_name = rc.constraint_name
JOIN information_schema.constraint_column_usage AS ccu
    ON ccu.constraint_name = tc.constraint_name
WHERE tc.constraint_type = 'FOREIGN KEY'
    AND tc.table_schema = 'public'
    AND rc.delete_rule = 'CASCADE'
ORDER BY tc.table_name
LIMIT 20;

-- Check 5.2: Constraint validation
\echo 'Checking constraint validation status...'
INSERT INTO health_check_results
SELECT
    'Referential Integrity' AS category,
    'Constraint Validation: ' || conname AS check_name,
    CASE 
        WHEN convalidated THEN 'PASS'
        ELSE 'WARN'
    END AS status,
    CASE 
        WHEN convalidated THEN 1
        ELSE 2
    END AS severity,
    'Constraint ' || conname || ' on ' || conrelid::regclass::text || 
    ' | Validated: ' || convalidated AS details,
    CASE 
        WHEN NOT convalidated THEN 'Validate constraint: ALTER TABLE ' || conrelid::regclass::text || 
            ' VALIDATE CONSTRAINT ' || conname || ';'
        ELSE NULL
    END AS recommendation
FROM pg_constraint
WHERE connamespace = 'public'::regnamespace
    AND contype IN ('f', 'c')  -- Foreign keys and check constraints
    AND NOT convalidated
LIMIT 10;

-- ============================================
-- SECTION 6: View Dependency Analysis
-- ============================================
\echo ''
\echo '=========================================='
\echo '=== VIEW DEPENDENCY ANALYSIS         ==='
\echo '=========================================='

-- Check 6.1: View dependency depth
\echo ''
\echo 'Checking view dependency depth...'
DO $$
DECLARE
    max_depth INTEGER;
    deep_views INTEGER;
    view_count INTEGER;
BEGIN
    -- Simplified dependency check - just count direct dependencies
    WITH view_deps AS (
        SELECT 
            dependent_view.relname AS view_name,
            COUNT(DISTINCT source_table.relname) AS dep_count
        FROM pg_depend
        JOIN pg_rewrite ON pg_depend.objid = pg_rewrite.oid
        JOIN pg_class AS dependent_view ON pg_rewrite.ev_class = dependent_view.oid
        JOIN pg_class AS source_table ON pg_depend.refobjid = source_table.oid
        WHERE dependent_view.relkind IN ('v', 'm')
            AND source_table.relkind IN ('v', 'm', 'r')
            AND dependent_view.relnamespace = (SELECT oid FROM pg_namespace WHERE nspname = 'public')
        GROUP BY dependent_view.relname
    )
    SELECT 
        COALESCE(MAX(dep_count), 0),
        COUNT(*) FILTER (WHERE dep_count > 5),
        COUNT(*)
    INTO max_depth, deep_views, view_count
    FROM view_deps;
    
    INSERT INTO health_check_results VALUES (
        'View Dependencies',
        'View Dependency Analysis',
        CASE WHEN max_depth > 10 THEN 'WARN' ELSE 'PASS' END,
        CASE WHEN max_depth > 10 THEN 2 ELSE 1 END,
        'Maximum direct dependencies: ' || COALESCE(max_depth::TEXT, '0') || 
        ' | Views with >5 dependencies: ' || COALESCE(deep_views::TEXT, '0') ||
        ' | Total views analyzed: ' || COALESCE(view_count::TEXT, '0'),
        CASE WHEN max_depth > 10 
            THEN 'Consider simplifying views with many dependencies to improve maintainability'
            ELSE NULL
        END
    );
END $$;

-- Check 6.2: Empty views that may indicate data issues
\echo 'Checking for empty views...'
DO $$
DECLARE
    view_rec RECORD;
    row_count BIGINT;
    empty_view_count INTEGER := 0;
BEGIN
    FOR view_rec IN 
        SELECT schemaname, viewname 
        FROM pg_views 
        WHERE schemaname = 'public'
        ORDER BY viewname
    LOOP
        BEGIN
            EXECUTE format('SELECT COUNT(*) FROM %I.%I', view_rec.schemaname, view_rec.viewname)
            INTO row_count;
            
            IF row_count = 0 THEN
                empty_view_count := empty_view_count + 1;
                INSERT INTO health_check_results VALUES (
                    'View Dependencies',
                    'Empty View: ' || view_rec.viewname,
                    'WARN',
                    2,
                    'View returns 0 rows',
                    'Check if this is expected. See TROUBLESHOOTING_EMPTY_VIEWS.md for diagnostic steps'
                );
            END IF;
        EXCEPTION WHEN OTHERS THEN
            -- Already caught in integrity check
            NULL;
        END;
    END LOOP;
    
    IF empty_view_count = 0 THEN
        INSERT INTO health_check_results VALUES (
            'View Dependencies',
            'Empty View Check',
            'PASS',
            1,
            'All views contain data',
            NULL
        );
    END IF;
END $$;

-- ============================================
-- SECTION 7: Calculate Health Score & Report
-- ============================================
\echo ''
\echo '=========================================='
\echo '=== HEALTH SCORE CALCULATION         ==='
\echo '=========================================='

WITH health_summary AS (
    SELECT
        category,
        COUNT(*) AS total_checks,
        COUNT(*) FILTER (WHERE status = 'PASS') AS passed,
        COUNT(*) FILTER (WHERE status = 'WARN') AS warnings,
        COUNT(*) FILTER (WHERE status = 'FAIL') AS failures,
        COUNT(*) FILTER (WHERE severity >= 3) AS critical_issues,
        -- Category-level score
        ROUND(
            (COUNT(*) FILTER (WHERE status = 'PASS') * 100.0 + 
             COUNT(*) FILTER (WHERE status = 'WARN') * 50.0) / 
            NULLIF(COUNT(*), 0), 
            2
        ) AS category_score
    FROM health_check_results
    WHERE status != 'INFO'  -- Exclude informational checks from scoring
    GROUP BY category
),
overall_score AS (
    SELECT
        COUNT(*) AS total_checks,
        COUNT(*) FILTER (WHERE status = 'PASS') AS passed,
        COUNT(*) FILTER (WHERE status = 'WARN') AS warnings,
        COUNT(*) FILTER (WHERE status = 'FAIL') AS failures,
        COUNT(*) FILTER (WHERE severity >= 3) AS critical_issues,
        -- Score formula: (passed * 100 + warnings * 50 + failures * 0) / total
        ROUND(
            (COUNT(*) FILTER (WHERE status = 'PASS') * 100.0 + 
             COUNT(*) FILTER (WHERE status = 'WARN') * 50.0) / 
            NULLIF(COUNT(*), 0), 
            2
        ) AS health_score
    FROM health_check_results
    WHERE status != 'INFO'  -- Exclude informational checks from scoring
)
SELECT
    ''::TEXT AS blank1,
    '=================================================='::TEXT AS separator,
    '           HEALTH CHECK SUMMARY'::TEXT AS title,
    '=================================================='::TEXT AS separator2,
    ''::TEXT AS blank2,
    'Total Checks: ' || total_checks AS total,
    'Passed: ' || passed AS pass_count,
    'Warnings: ' || warnings AS warn_count,
    'Failures: ' || failures AS fail_count,
    'Critical Issues: ' || critical_issues AS critical_count,
    ''::TEXT AS blank3,
    '**OVERALL HEALTH SCORE: ' || health_score || '/100**' AS score,
    ''::TEXT AS blank4,
    CASE
        WHEN health_score >= 90 THEN '✓ Status: EXCELLENT - No action needed'
        WHEN health_score >= 75 THEN '⚠ Status: GOOD - Monitor warnings, plan improvements'
        WHEN health_score >= 60 THEN '⚠⚠ Status: NEEDS ATTENTION - Address failures soon'
        ELSE '✗✗✗ Status: CRITICAL - Immediate action required'
    END AS status_assessment,
    '=================================================='::TEXT AS separator3,
    ''::TEXT AS blank5
FROM overall_score;

-- Display category-level health scores
\echo ''
\echo '--- CATEGORY HEALTH SCORES ---'
SELECT
    category,
    category_score || '/100' AS health_score,
    CASE
        WHEN category_score >= 90 THEN 'EXCELLENT ✓'
        WHEN category_score >= 75 THEN 'GOOD ⚠'
        WHEN category_score >= 60 THEN 'NEEDS ATTENTION ⚠⚠'
        ELSE 'CRITICAL ✗✗✗'
    END AS status,
    total_checks AS checks,
    passed || ' pass' AS passed,
    warnings || ' warn' AS warnings,
    failures || ' fail' AS failures
FROM (
    SELECT
        category,
        COUNT(*) AS total_checks,
        COUNT(*) FILTER (WHERE status = 'PASS') AS passed,
        COUNT(*) FILTER (WHERE status = 'WARN') AS warnings,
        COUNT(*) FILTER (WHERE status = 'FAIL') AS failures,
        ROUND(
            (COUNT(*) FILTER (WHERE status = 'PASS') * 100.0 + 
             COUNT(*) FILTER (WHERE status = 'WARN') * 50.0) / 
            NULLIF(COUNT(*), 0), 
            2
        ) AS category_score
    FROM health_check_results
    WHERE status != 'INFO'
    GROUP BY category
) cat_scores
ORDER BY category_score ASC;

-- Display category breakdown (traditional format)
\echo ''
\echo '--- CATEGORY BREAKDOWN ---'
SELECT
    category,
    total_checks,
    passed || ' passed' AS passed_count,
    warnings || ' warnings' AS warning_count,
    failures || ' failures' AS failure_count,
    ROUND(passed * 100.0 / NULLIF(total_checks, 0), 1) || '%' AS pass_rate
FROM (
    SELECT
        category,
        COUNT(*) AS total_checks,
        COUNT(*) FILTER (WHERE status = 'PASS') AS passed,
        COUNT(*) FILTER (WHERE status = 'WARN') AS warnings,
        COUNT(*) FILTER (WHERE status = 'FAIL') AS failures
    FROM health_check_results
    WHERE status != 'INFO'
    GROUP BY category
) cat_summary
ORDER BY pass_rate ASC;

-- Display all issues (warnings and failures)
\echo ''
\echo '--- ISSUES REQUIRING ATTENTION ---'
SELECT
    category,
    check_name,
    status,
    CASE severity
        WHEN 1 THEN 'INFO'
        WHEN 2 THEN 'WARNING'
        WHEN 3 THEN 'ERROR'
        WHEN 4 THEN 'CRITICAL'
    END AS severity_level,
    details,
    recommendation
FROM health_check_results
WHERE status IN ('WARN', 'FAIL')
ORDER BY severity DESC, category, check_name;

-- Display informational messages
\echo ''
\echo '--- INFORMATIONAL ---'
SELECT
    category,
    check_name,
    details,
    recommendation
FROM health_check_results
WHERE status = 'INFO'
ORDER BY category, check_name;

-- Export to JSON
\echo ''
\echo '--- JSON EXPORT ---'
\echo 'Generating JSON health check report...'

SELECT json_build_object(
    'timestamp', NOW(),
    'database', current_database(),
    'health_score', (
        SELECT ROUND(
            (COUNT(*) FILTER (WHERE status = 'PASS') * 100.0 + 
             COUNT(*) FILTER (WHERE status = 'WARN') * 50.0) / 
            NULLIF(COUNT(*), 0), 
            2
        )
        FROM health_check_results
        WHERE status != 'INFO'
    ),
    'summary', json_build_object(
        'total_checks', (SELECT COUNT(*) FROM health_check_results WHERE status != 'INFO'),
        'passed', (SELECT COUNT(*) FROM health_check_results WHERE status = 'PASS'),
        'warnings', (SELECT COUNT(*) FROM health_check_results WHERE status = 'WARN'),
        'failures', (SELECT COUNT(*) FROM health_check_results WHERE status = 'FAIL'),
        'critical_issues', (SELECT COUNT(*) FROM health_check_results WHERE severity >= 3 AND status != 'INFO')
    ),
    'categories', (
        SELECT json_agg(
            json_build_object(
                'category', category,
                'total_checks', total_checks,
                'passed', passed,
                'warnings', warnings,
                'failures', failures,
                'pass_rate', ROUND(passed * 100.0 / NULLIF(total_checks, 0), 1),
                'category_score', ROUND(
                    (passed * 100.0 + warnings * 50.0) / NULLIF(total_checks, 0), 
                    2
                )
            )
        )
        FROM (
            SELECT
                category,
                COUNT(*) AS total_checks,
                COUNT(*) FILTER (WHERE status = 'PASS') AS passed,
                COUNT(*) FILTER (WHERE status = 'WARN') AS warnings,
                COUNT(*) FILTER (WHERE status = 'FAIL') AS failures
            FROM health_check_results
            WHERE status != 'INFO'
            GROUP BY category
        ) cat
    ),
    'issues', (
        SELECT json_agg(issue_obj)
        FROM (
            SELECT json_build_object(
                'category', category,
                'check_name', check_name,
                'status', status,
                'severity', severity,
                'details', details,
                'recommendation', recommendation
            ) AS issue_obj
            FROM health_check_results
            WHERE status IN ('WARN', 'FAIL')
            ORDER BY severity DESC
        ) issues_subq
    )
)::text AS json_report;

-- ============================================
-- SECTION 8: Prometheus Metrics Export
-- ============================================
\echo ''
\echo '--- PROMETHEUS METRICS EXPORT ---'
\echo 'Generating Prometheus-compatible metrics...'

WITH category_metrics AS (
    SELECT
        category,
        COUNT(*) AS total_checks,
        COUNT(*) FILTER (WHERE status = 'PASS') AS passed,
        COUNT(*) FILTER (WHERE status = 'WARN') AS warnings,
        COUNT(*) FILTER (WHERE status = 'FAIL') AS failures,
        ROUND(
            (COUNT(*) FILTER (WHERE status = 'PASS') * 100.0 + 
             COUNT(*) FILTER (WHERE status = 'WARN') * 50.0) / 
            NULLIF(COUNT(*), 0), 
            2
        ) AS category_score
    FROM health_check_results
    WHERE status != 'INFO'
    GROUP BY category
)
SELECT
    '# HELP cia_db_health_score Database health score by category' || E'\n' ||
    '# TYPE cia_db_health_score gauge' || E'\n' ||
    string_agg(
        'cia_db_health_score{category="' || category || '"} ' || category_score::TEXT,
        E'\n'
    ) || E'\n' ||
    E'\n' ||
    '# HELP cia_db_health_checks_total Total health checks by category and status' || E'\n' ||
    '# TYPE cia_db_health_checks_total gauge' || E'\n' ||
    string_agg(
        'cia_db_health_checks_total{category="' || category || '",status="pass"} ' || passed::TEXT || E'\n' ||
        'cia_db_health_checks_total{category="' || category || '",status="warn"} ' || warnings::TEXT || E'\n' ||
        'cia_db_health_checks_total{category="' || category || '",status="fail"} ' || failures::TEXT,
        E'\n'
    ) AS prometheus_metrics
FROM category_metrics;

\echo ''
\echo 'To export Prometheus metrics to file, run:'
\echo '  psql -U postgres -d cia_dev -t -A -f schema-health-check.sql | grep -A 999 "cia_db_health" > metrics.prom'
\echo ''

\echo ''
\echo '=================================================='
\echo 'Health check completed'
\echo 'Finished:' `date`
\echo '=================================================='
\echo ''
\echo 'To save JSON output to file, run:'
\echo '  psql -U postgres -d cia_dev -t -A -c "SELECT json_build_object(...)" > health_check.json'
\echo ''
\echo 'For automation, see README-SCHEMA-MAINTENANCE.md'
\echo ''

\timing off
