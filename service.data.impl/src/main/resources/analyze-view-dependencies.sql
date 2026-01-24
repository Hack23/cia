-- analyze-view-dependencies.sql
-- Generate view dependency analysis report with dependency levels
--
-- Purpose:
--   Analyzes the dependency graph for views and materialized views in the database,
--   showing which views depend on which tables, views, and other materialized views.
--   Part 2 focuses specifically on materialized view refresh order.
--
-- Usage:
--   Output to terminal (both parts):
--     psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/analyze-view-dependencies.sql
--
--   Save Part 1 (basic dependencies) to CSV file only:
--     psql -U postgres -d cia_dev -t -A -F',' -c "COPY (SELECT dependent_ns.nspname AS dependent_schema, dependent_view.relname AS dependent_view, source_ns.nspname AS source_schema, source_table.relname AS source_object, CASE source_table.relkind WHEN 'r' THEN 'TABLE' WHEN 'v' THEN 'VIEW' WHEN 'm' THEN 'MATERIALIZED VIEW' ELSE source_table.relkind END AS source_type FROM pg_depend JOIN pg_rewrite ON pg_depend.objid = pg_rewrite.oid JOIN pg_class AS dependent_view ON pg_rewrite.ev_class = dependent_view.oid JOIN pg_class AS source_table ON pg_depend.refobjid = source_table.oid JOIN pg_namespace dependent_ns ON dependent_ns.oid = dependent_view.relnamespace JOIN pg_namespace source_ns ON source_ns.oid = source_table.relnamespace WHERE dependent_view.relkind IN ('v', 'm') AND source_table.relkind IN ('v', 'm', 'r') AND pg_depend.deptype = 'n' AND dependent_ns.nspname = 'public' ORDER BY dependent_schema, dependent_view, source_object) TO STDOUT WITH (FORMAT CSV, HEADER);" > view_dependencies.csv
--
-- Output:
--   1. Basic dependency report (CSV format) - includes both regular and materialized views
--   2. Materialized view refresh order analysis with dependency levels

\echo 'Generating view dependency analysis...'
\echo ''
\echo '=== Part 1: Basic View Dependencies (Regular and Materialized Views) ==='
\echo ''

COPY (
  SELECT 
      dependent_ns.nspname    AS dependent_schema,
      dependent_view.relname  AS dependent_view,
      source_ns.nspname       AS source_schema,
      source_table.relname    AS source_object,
      CASE source_table.relkind
        WHEN 'r' THEN 'TABLE'
        WHEN 'v' THEN 'VIEW'
        WHEN 'm' THEN 'MATERIALIZED VIEW'
        ELSE source_table.relkind
      END AS source_type
  FROM pg_depend
  JOIN pg_rewrite 
         ON pg_depend.objid = pg_rewrite.oid
  JOIN pg_class AS dependent_view
         ON pg_rewrite.ev_class = dependent_view.oid
  JOIN pg_class AS source_table
         ON pg_depend.refobjid = source_table.oid
  JOIN pg_namespace dependent_ns
         ON dependent_ns.oid = dependent_view.relnamespace
  JOIN pg_namespace source_ns
         ON source_ns.oid = source_table.relnamespace
  WHERE dependent_view.relkind IN ('v', 'm')  
    AND source_table.relkind   IN ('v', 'm', 'r') 
    AND pg_depend.deptype = 'n'
    AND dependent_ns.nspname = 'public'
  ORDER BY dependent_schema, dependent_view, source_object
) TO STDOUT WITH (FORMAT CSV, HEADER);

\echo ''
\echo 'View dependency analysis complete.'
\echo ''
\echo '=== Part 2: Materialized View Refresh Order ==='
\echo ''

-- Generate dependency-ordered list for materialized views
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
    max_depth AS dependency_level,
    view_name,
    COALESCE(
        (SELECT string_agg(depends_on, ', ' ORDER BY depends_on)
         FROM mv_dependencies md
         WHERE md.view_name = md2.view_name),
        'None'
    ) AS depends_on_matviews,
    'REFRESH MATERIALIZED VIEW ' || view_name || ';' AS refresh_command
FROM max_depths md2
ORDER BY max_depth, view_name;

\echo ''
\echo 'Dependency analysis complete.'
\echo 'Materialized views should be refreshed in order from Level 0 to highest level.'
