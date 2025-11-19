-- analyze-view-dependencies.sql
-- Generate view dependency analysis report
--
-- Purpose:
--   Analyzes the dependency graph for all views in the database,
--   showing which views depend on which tables and other views.
--
-- Usage:
--   Output to terminal:
--     psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/analyze-view-dependencies.sql
--
--   Save to CSV file:
--     psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/analyze-view-dependencies.sql -o view_dependencies.csv
--
--   Or use COPY redirection:
--     psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/analyze-view-dependencies.sql > view_dependencies.csv
--
-- Output Format:
--   CSV with columns: dependent_schema, dependent_view, source_schema, source_object, source_type

\echo 'Generating view dependency analysis...'
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
\echo 'To save to file, use: psql -U postgres -d cia_dev -f analyze-view-dependencies.sql -o view_dependencies.csv'
