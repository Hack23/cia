-- extract-minister-data.sql
-- Complete Government Minister Data Extraction
-- Citizen Intelligence Agency - Open Source Intelligence Platform
--
-- Purpose: Extracts complete government minister data, ministries, assignments, and performance
--          for government composition analysis and riksdagsmonitor integration
--
-- Usage:
--   # Extract all minister data to CSV files in current directory
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-minister-data.sql
--
--   # Or specify output directory
--   cd /output/directory
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-minister-data.sql
--
-- Output Files:
--   - minister_current.csv               : Current government ministers
--   - minister_historical.csv            : All historical government ministers
--   - ministry_assignments_current.csv   : Current ministry assignments by person
--   - ministry_assignments_historical.csv: Complete ministry assignment history
--   - government_composition.csv         : Current government composition by ministry
--   - government_transitions.csv         : Government changes and transitions
--   - minister_performance.csv           : Minister performance metrics
--
-- ===========================================================================

\set ON_ERROR_STOP off
\timing on
\set VERBOSITY verbose

\echo '=================================================='
\echo 'CIA Complete Minister Data Extraction'
\echo 'Started:' `date`
\echo '=================================================='
\echo ''

-- ===========================================================================
-- 0. REFRESH MATERIALIZED VIEWS
-- ===========================================================================
-- Ensure all materialized views are populated before extraction
-- Note: This may take time on empty database but ensures queries work
--
\echo 'Refreshing required materialized views...'
REFRESH MATERIALIZED VIEW view_riksdagen_politician_document;
\echo '   ✓ Materialized views refreshed'
\echo ''

-- ===========================================================================
-- 1. CURRENT GOVERNMENT MINISTERS
-- ===========================================================================
-- All currently serving government ministers with their roles
--
\echo '1. Extracting Current Government Ministers...'
\copy (SELECT role_id, detail AS ministry, role_code, role_type, first_name, last_name, person_id, party, from_date, to_date, total_days_served, total_documents, documents_last_year, total_propositions, total_government_bills, activity_level FROM view_riksdagen_goverment_role_member WHERE active = true ORDER BY detail, from_date DESC) TO 'minister_current.csv' WITH CSV HEADER
\echo '   ✓ minister_current.csv - Currently serving ministers'
\echo ''

-- ===========================================================================
-- 2. HISTORICAL GOVERNMENT MINISTERS (ALL TIME)
-- ===========================================================================
-- Complete history of all government ministers since records began
--
\echo '2. Extracting Historical Government Ministers...'
\copy (SELECT role_id, detail AS ministry, role_code, role_type, first_name, last_name, person_id, party, from_date, to_date, total_days_served, active, total_documents, documents_last_year, total_propositions, total_government_bills, activity_level FROM view_riksdagen_goverment_role_member ORDER BY from_date DESC, detail, last_name, first_name) TO 'minister_historical.csv' WITH CSV HEADER
\echo '   ✓ minister_historical.csv - Complete minister history'
\echo ''

-- ===========================================================================
-- 3. CURRENT MINISTRY ASSIGNMENTS BY PERSON
-- ===========================================================================
-- Currently active ministry assignments with person details
--
\echo '3. Extracting Current Ministry Assignments...'
\copy (SELECT a.hjid AS assignment_id, a.detail AS ministry, a.role_code, a.org_code, a.from_date, a.to_date, a.status, p.id AS person_id, p.first_name, p.last_name, p.party, p.born_year, p.gender, CURRENT_DATE - a.from_date AS days_in_position FROM assignment_data a JOIN person_data p ON a.intressent_id = p.id WHERE a.status = 'Tjänstgörande' AND (a.to_date IS NULL OR a.to_date >= CURRENT_DATE) AND (a.role_code ILIKE '%MINISTER%' OR a.role_code = 'STATSRÅD' OR a.detail ILIKE '%departementet' OR a.detail = 'Statsrådsberedningen') ORDER BY a.detail, a.from_date DESC) TO 'ministry_assignments_current.csv' WITH CSV HEADER
\echo '   ✓ ministry_assignments_current.csv - Current ministry assignments'
\echo ''

-- ===========================================================================
-- 4. HISTORICAL MINISTRY ASSIGNMENTS (ALL TIME)
-- ===========================================================================
-- Complete history of all ministry assignments
--
\echo '4. Extracting Historical Ministry Assignments...'
\copy (SELECT a.hjid AS assignment_id, a.detail AS ministry, a.role_code, a.org_code, a.from_date, a.to_date, a.status, p.id AS person_id, p.first_name, p.last_name, p.party, p.born_year, p.gender, COALESCE(a.to_date, CURRENT_DATE) - a.from_date AS days_in_position, CASE WHEN a.to_date IS NULL OR a.to_date >= CURRENT_DATE THEN 'active' ELSE 'completed' END AS assignment_status FROM assignment_data a JOIN person_data p ON a.intressent_id = p.id WHERE a.role_code ILIKE '%MINISTER%' OR a.role_code = 'STATSRÅD' OR a.detail ILIKE '%departementet' OR a.detail = 'Statsrådsberedningen' ORDER BY a.from_date DESC, a.detail, p.last_name, p.first_name) TO 'ministry_assignments_historical.csv' WITH CSV HEADER
\echo '   ✓ ministry_assignments_historical.csv - Complete assignment history'
\echo ''

-- ===========================================================================
-- 5. CURRENT GOVERNMENT COMPOSITION
-- ===========================================================================
-- Current government structure by ministry
--
\echo '5. Extracting Current Government Composition...'
\copy (SELECT detail AS ministry, COUNT(*) AS minister_count, STRING_AGG(DISTINCT party, ', ' ORDER BY party) AS parties_represented, STRING_AGG(first_name || ' ' || last_name, ', ' ORDER BY from_date DESC) AS ministers, MIN(from_date) AS oldest_appointment, MAX(from_date) AS newest_appointment FROM view_riksdagen_goverment_role_member WHERE active = true GROUP BY detail ORDER BY minister_count DESC, detail) TO 'government_composition.csv' WITH CSV HEADER
\echo '   ✓ government_composition.csv - Current government structure'
\echo ''

-- ===========================================================================
-- 6. GOVERNMENT TRANSITIONS
-- ===========================================================================
-- Government changes and transitions over time (by year)
--
\echo '6. Extracting Government Transitions...'
\copy (SELECT EXTRACT(YEAR FROM a.from_date)::INTEGER AS year, COUNT(DISTINCT a.hjid) AS new_appointments, COUNT(DISTINCT CASE WHEN a.to_date IS NOT NULL AND EXTRACT(YEAR FROM a.to_date) = EXTRACT(YEAR FROM a.from_date) THEN a.hjid END) AS departures_same_year, COUNT(DISTINCT p.id) AS unique_ministers, COUNT(DISTINCT p.party) AS parties_represented, STRING_AGG(DISTINCT p.party, ', ' ORDER BY p.party) AS parties FROM assignment_data a JOIN person_data p ON a.intressent_id = p.id WHERE (a.role_code ILIKE '%MINISTER%' OR a.role_code = 'STATSRÅD' OR a.detail ILIKE '%departementet' OR a.detail = 'Statsrådsberedningen') AND a.from_date IS NOT NULL AND EXTRACT(YEAR FROM a.from_date) >= 2000 GROUP BY EXTRACT(YEAR FROM a.from_date) ORDER BY year DESC) TO 'government_transitions.csv' WITH CSV HEADER
\echo '   ✓ government_transitions.csv - Annual government changes'
\echo ''

-- ===========================================================================
-- 7. MINISTER PERFORMANCE METRICS
-- ===========================================================================
-- Performance metrics for ministers with document production
--
\echo '7. Extracting Minister Performance Metrics...'
\copy (SELECT person_id, first_name, last_name, party, detail AS ministry, role_code, from_date, to_date, total_days_served, active, total_documents, documents_last_year, total_propositions, total_government_bills, activity_level, ROUND(COALESCE(total_documents::NUMERIC / NULLIF(total_days_served, 0) * 365, 0), 2) AS docs_per_year, ROUND(COALESCE(total_government_bills::NUMERIC / NULLIF(total_documents, 0) * 100, 0), 2) AS govt_bill_percentage FROM view_riksdagen_goverment_role_member WHERE total_days_served > 0 ORDER BY total_documents DESC, total_days_served DESC) TO 'minister_performance.csv' WITH CSV HEADER
\echo '   ✓ minister_performance.csv - Minister performance metrics'
\echo ''

-- ===========================================================================
-- SUMMARY
-- ===========================================================================
\echo '=================================================='
\echo 'Minister Data Extraction Complete'
\echo ''
\echo 'Generated Files:'
\echo '  1. minister_current.csv               - Current ministers'
\echo '  2. minister_historical.csv            - Historical ministers'
\echo '  3. ministry_assignments_current.csv   - Current assignments'
\echo '  4. ministry_assignments_historical.csv - Assignment history'
\echo '  5. government_composition.csv         - Current government structure'
\echo '  6. government_transitions.csv         - Government changes over time'
\echo '  7. minister_performance.csv           - Minister performance metrics'
\echo ''
\echo 'Usage: Import these CSV files for government analysis'
\echo 'Integration: Used by riksdagsmonitor for ministry dashboards'
\echo '=================================================='
