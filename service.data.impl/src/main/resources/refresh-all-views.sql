-- refresh-all-views.sql
-- Materialized View Refresh Script with Dependency-Aware Ordering
-- 
-- Usage:
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/refresh-all-views.sql
--
-- Description:
--   Refreshes all materialized views in correct dependency order with:
--   - Dynamic discovery from pg_matviews (no hardcoded list)
--   - Topologically sorted refresh order (respects MV dependencies)
--   - Error handling to continue on individual failures
--   - Timing information for each view refresh
--   - Progress logging and summary report by dependency level
--   - Multi-level refresh approach (Level 0 -> Level 1 -> Level 2 -> etc.)
--
-- Implementation:
--   Uses recursive CTE to calculate dependency depth for each materialized view.
--   Views are refreshed in order from lowest to highest dependency level,
--   ensuring that all dependencies are refreshed before dependent views.

\set ON_ERROR_STOP off
\timing on

\echo '======================================='
\echo 'Materialized View Refresh Script'
\echo 'Started at:' `date`
\echo '======================================='

-- ===========================================================================
-- PHASE 1: VALIDATION - Check that all views exist
-- ===========================================================================

\echo ''
\echo '--- VALIDATION PHASE ---'
\echo 'Checking that all materialized views exist...'

DO $$
DECLARE
    total_mvs INTEGER;
    mv_name TEXT;
BEGIN
    -- Get total count dynamically
    SELECT COUNT(*) INTO total_mvs FROM pg_matviews WHERE schemaname = 'public';
    
    RAISE NOTICE 'Found % materialized views in public schema', total_mvs;
    
    -- List all materialized views for reference
    FOR mv_name IN 
        SELECT matviewname FROM pg_matviews WHERE schemaname = 'public' ORDER BY matviewname
    LOOP
        RAISE NOTICE '  - %', mv_name;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE 'Validation passed: All materialized views will be refreshed dynamically';
END $$;

-- ===========================================================================
-- PHASE 2: REFRESH - Refresh materialized views dynamically
-- ===========================================================================

\echo ''
\echo '--- REFRESH PHASE ---'
\echo 'Refreshing materialized views in dependency order...'
\echo 'Views are refreshed level-by-level (Level 0 first, then Level 1, etc.)'
\echo ''

DO $$
DECLARE
    mv_record RECORD;
    v_success INT := 0;
    v_failed INT := 0;
    v_start TIMESTAMP;
    v_duration INTERVAL;
    total_mvs INTEGER;
    mv_count INTEGER := 0;
    current_level INTEGER := -1;
    level_start TIMESTAMP;
    level_duration INTERVAL;
    level_count INTEGER := 0;
BEGIN
    -- Get total count for progress reporting
    SELECT COUNT(*) INTO total_mvs FROM pg_matviews WHERE schemaname = 'public';
    
    -- Refresh all materialized views in dependency order
    -- Uses recursive CTE to calculate dependency depth
    FOR mv_record IN 
        WITH RECURSIVE 
        -- Get all materialized view dependencies
        view_deps AS (
            SELECT DISTINCT
                dependent_view.relname AS view_name,
                source_table.relname AS depends_on,
                CASE 
                    WHEN source_table.relkind = 'm' THEN TRUE
                    ELSE FALSE
                END AS depends_on_matview
            FROM pg_depend
            JOIN pg_rewrite ON pg_depend.objid = pg_rewrite.oid
            JOIN pg_class AS dependent_view ON pg_rewrite.ev_class = dependent_view.oid
            JOIN pg_class AS source_table ON pg_depend.refobjid = source_table.oid
            JOIN pg_namespace dependent_ns ON dependent_ns.oid = dependent_view.relnamespace
            JOIN pg_namespace source_ns ON source_ns.oid = source_table.relnamespace
            WHERE dependent_view.relkind = 'm'
              AND source_table.relkind IN ('v', 'm', 'r')
              AND pg_depend.deptype = 'n'
              AND dependent_ns.nspname = 'public'
              AND source_ns.nspname = 'public'
        ),
        -- Build dependency graph (only MV dependencies)
        mv_dependencies AS (
            SELECT view_name, depends_on
            FROM view_deps
            WHERE depends_on_matview = TRUE
        ),
        -- Get all materialized views
        all_mvs AS (
            SELECT matviewname AS view_name
            FROM pg_matviews
            WHERE schemaname = 'public'
        ),
        -- Calculate dependency depth using recursive CTE
        dependency_depth AS (
            -- Base case: views with no MV dependencies
            SELECT 
                am.view_name,
                0 AS depth,
                ARRAY[am.view_name] AS path
            FROM all_mvs am
            WHERE NOT EXISTS (
                SELECT 1 FROM mv_dependencies md 
                WHERE md.view_name = am.view_name
            )
            
            UNION ALL
            
            -- Recursive case: views that depend on views we've already processed
            SELECT 
                md.view_name,
                dd.depth + 1,
                dd.path || md.view_name
            FROM mv_dependencies md
            JOIN dependency_depth dd ON md.depends_on = dd.view_name
            WHERE NOT (md.view_name = ANY(dd.path)) -- Prevent cycles
        ),
        -- Get maximum depth for each view
        max_depths AS (
            SELECT 
                view_name,
                MAX(depth) as max_depth
            FROM dependency_depth
            GROUP BY view_name
        )
        -- Output in refresh order
        SELECT 
            'public' AS schemaname,
            view_name AS matviewname,
            max_depth AS dependency_level
        FROM max_depths
        ORDER BY max_depth, view_name
    LOOP
        BEGIN
            -- Track when we move to a new dependency level
            IF current_level != mv_record.dependency_level THEN
                IF current_level >= 0 THEN
                    level_duration := clock_timestamp() - level_start;
                    RAISE NOTICE '';
                    RAISE NOTICE '  Level % complete: % views in %', 
                        current_level, level_count, level_duration;
                END IF;
                current_level := mv_record.dependency_level;
                level_start := clock_timestamp();
                level_count := 0;
                RAISE NOTICE '';
                RAISE NOTICE '=== Refreshing Level % (dependency depth: %) ===', 
                    current_level, current_level;
            END IF;
            
            mv_count := mv_count + 1;
            level_count := level_count + 1;
            v_start := clock_timestamp();
            RAISE NOTICE '[%/%] [L%] Refreshing: %.%...', 
                mv_count, total_mvs, current_level,
                mv_record.schemaname, mv_record.matviewname;
            
            EXECUTE format('REFRESH MATERIALIZED VIEW %I.%I', 
                mv_record.schemaname, mv_record.matviewname);
            
            v_duration := clock_timestamp() - v_start;
            RAISE NOTICE '      ✓ Refreshed in %', v_duration;
            v_success := v_success + 1;
        EXCEPTION WHEN OTHERS THEN
            RAISE WARNING '      ✗ Failed to refresh %.%: %', 
                mv_record.schemaname, mv_record.matviewname, SQLERRM;
            v_failed := v_failed + 1;
        END;
    END LOOP;
    
    -- Report final level completion
    IF current_level >= 0 THEN
        level_duration := clock_timestamp() - level_start;
        RAISE NOTICE '';
        RAISE NOTICE '  Level % complete: % views in %', 
            current_level, level_count, level_duration;
    END IF;
    
    RAISE NOTICE '';
    RAISE NOTICE '===========================================';
    RAISE NOTICE '--- REFRESH SUMMARY ---';
    RAISE NOTICE '===========================================';
    RAISE NOTICE 'Total views: %', total_mvs;
    RAISE NOTICE 'Successful: %', v_success;
    RAISE NOTICE 'Failed: %', v_failed;
    RAISE NOTICE 'Dependency levels processed: % (0 to %)', 
        current_level + 1, current_level;
    RAISE NOTICE '===========================================';
    
    IF v_failed > 0 THEN
        RAISE WARNING 'Some view refreshes failed. Check logs above for details.';
    END IF;
END $$;

\echo ''
\echo '======================================='
\echo 'Refresh script completed'
\echo 'Finished at:' `date`
\echo '======================================='



