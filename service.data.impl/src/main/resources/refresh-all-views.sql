-- refresh-all-views.sql
-- Refresh all materialized views in correct dependency order
-- Views must be refreshed in order: base views first, then dependent views

-- ===========================================================================
-- TIER 1: Base materialized views (no dependencies on other materialized views)
-- ===========================================================================

-- World Bank data
REFRESH MATERIALIZED VIEW view_worldbank_indicator_data_country_summary;

-- Riksdagen base document and vote views
REFRESH MATERIALIZED VIEW view_riksdagen_politician_document;
REFRESH MATERIALIZED VIEW view_riksdagen_org_document_daily_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_document_type_daily_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_committee_decisions;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_committee_ballot_decision_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary;

-- ===========================================================================
-- TIER 2: Daily/summary views that depend on base materialized views
-- ===========================================================================

-- Vote data ballot summaries (depend on base ballot summary)
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_daily;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_weekly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_monthly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_annual;

-- Party summary views (depend on base party summary)
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_daily;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_weekly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_monthly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_annual;

-- Politician summary views (depend on base politician summary)
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_daily;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_weekly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_monthly;
REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_annual;

-- Committee decision summaries (depend on committee decisions)
REFRESH MATERIALIZED VIEW view_riksdagen_committee_ballot_decision_party_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_committee_ballot_decision_politician_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_committee_decision_type_org_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_committee_decision_type_summary;

-- Document summaries (depend on politician_document)
REFRESH MATERIALIZED VIEW view_riksdagen_party_document_daily_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_politician_document_daily_summary;
REFRESH MATERIALIZED VIEW view_riksdagen_politician_document_summary;

-- ===========================================================================
-- ANALYSIS QUERY: View Dependencies
-- ===========================================================================
-- This query shows the dependency graph for all views in the database
-- Uncomment to analyze view dependencies

/*
COPY (
  SELECT 
      dependent_ns.nspname    AS dependent_schema,
      dependent_view.relname  AS dependent_view,
      source_ns.nspname       AS source_schema,
      source_table.relname    AS source_object
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
  ORDER BY dependent_schema, dependent_view
) 
TO '/path/to/view_dependencies.csv' 
WITH (FORMAT csv, HEADER);
*/



