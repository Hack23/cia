-- extract-party-data.sql
-- Complete Party Data Extraction
-- Citizen Intelligence Agency - Open Source Intelligence Platform
--
-- Purpose: Extracts complete party master data, members, roles, and voting patterns
--          for political party analysis and riksdagsmonitor integration
--
-- Usage:
--   # Extract all party data to CSV files in current directory
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-party-data.sql
--
--   # Or specify output directory
--   cd /output/directory
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-party-data.sql
--
-- Output Files:
--   - party_master_data.csv              : All registered political parties
--   - party_members_current.csv          : Current party members (active)
--   - party_members_historical.csv       : All party members with history
--   - party_leaders_current.csv          : Current party leaders
--   - party_leaders_historical.csv       : Historical party leadership transitions
--   - party_voting_summary.csv           : Party-level voting statistics
--   - party_composition_timeline.csv     : Party membership changes over time
--
-- ===========================================================================

\set ON_ERROR_STOP off
\timing on
\set VERBOSITY verbose

\echo '=================================================='
\echo 'CIA Complete Party Data Extraction'
\echo 'Started:' `date`
\echo '=================================================='
\echo ''

-- ===========================================================================
-- 1. PARTY MASTER DATA
-- ===========================================================================
-- Complete extraction of all registered political parties from val.se (Swedish Election Authority)
--
\echo '1. Extracting Party Master Data...'
\copy (SELECT hjid AS party_hjid, party_id, party_name, short_code, registered_date, address, co_address, city, post_code, phone_number, fax_number, email, website FROM sweden_political_party ORDER BY party_name) TO 'party_master_data.csv' WITH CSV HEADER
\echo '   ✓ party_master_data.csv - All registered political parties'
\echo ''

-- ===========================================================================
-- 2. CURRENT PARTY MEMBERS (ACTIVE)
-- ===========================================================================
-- All currently active members of parliament by party
--
\echo '2. Extracting Current Party Members...'
\copy (SELECT p.id AS person_id, p.first_name, p.last_name, p.party, p.gender, p.born_year, p.status, COUNT(DISTINCT a.hjid) AS active_assignments, MAX(a.from_date) AS latest_assignment_date FROM person_data p LEFT JOIN assignment_data a ON a.intressent_id = p.id AND a.status = 'Tjänstgörande' AND (a.to_date IS NULL OR a.to_date >= CURRENT_DATE) WHERE p.status = 'Tjänstgörande ledamot' GROUP BY p.id, p.first_name, p.last_name, p.party, p.gender, p.born_year, p.status ORDER BY p.party, p.last_name, p.first_name) TO 'party_members_current.csv' WITH CSV HEADER
\echo '   ✓ party_members_current.csv - Currently active members by party'
\echo ''

-- ===========================================================================
-- 3. HISTORICAL PARTY MEMBERS (ALL TIME)
-- ===========================================================================
-- Complete history of all parliament members with party affiliation
--
\echo '3. Extracting Historical Party Members...'
\copy (SELECT p.id AS person_id, p.first_name, p.last_name, p.party, p.gender, p.born_year, p.status, COUNT(DISTINCT a.hjid) AS total_assignments, MIN(a.from_date) AS first_assignment, MAX(COALESCE(a.to_date, CURRENT_DATE)) AS last_assignment, SUM(CASE WHEN a.to_date IS NULL OR a.to_date >= CURRENT_DATE THEN 1 ELSE 0 END) AS active_assignments FROM person_data p LEFT JOIN assignment_data a ON a.intressent_id = p.id WHERE p.party IS NOT NULL GROUP BY p.id, p.first_name, p.last_name, p.party, p.gender, p.born_year, p.status ORDER BY p.party, first_assignment DESC, p.last_name, p.first_name) TO 'party_members_historical.csv' WITH CSV HEADER
\echo '   ✓ party_members_historical.csv - Complete party membership history'
\echo ''

-- ===========================================================================
-- 4. CURRENT PARTY LEADERS
-- ===========================================================================
-- Currently active party leadership positions
--
\echo '4. Extracting Current Party Leaders...'
\copy (SELECT role_id, party, role_code, role_type, first_name, last_name, person_id, from_date, to_date, total_days_served, total_documents, documents_last_year, activity_level FROM view_riksdagen_party_role_member WHERE active = true ORDER BY party, CASE role_type WHEN 'Party Leader' THEN 1 WHEN 'Party Spokesperson' THEN 2 WHEN 'Group Leader' THEN 3 WHEN 'First Deputy Group Leader' THEN 4 WHEN 'Second Deputy Group Leader' THEN 5 WHEN 'Party Secretary' THEN 6 ELSE 99 END, from_date DESC) TO 'party_leaders_current.csv' WITH CSV HEADER
\echo '   ✓ party_leaders_current.csv - Current party leadership positions'
\echo ''

-- ===========================================================================
-- 5. HISTORICAL PARTY LEADERS (ALL TIME)
-- ===========================================================================
-- Complete history of party leadership transitions
--
\echo '5. Extracting Historical Party Leaders...'
\copy (SELECT role_id, party, role_code, role_type, first_name, last_name, person_id, from_date, to_date, total_days_served, active, total_documents, documents_last_year, activity_level FROM view_riksdagen_party_role_member ORDER BY party, from_date DESC, CASE role_type WHEN 'Party Leader' THEN 1 WHEN 'Party Spokesperson' THEN 2 WHEN 'Group Leader' THEN 3 WHEN 'First Deputy Group Leader' THEN 4 WHEN 'Second Deputy Group Leader' THEN 5 WHEN 'Party Secretary' THEN 6 ELSE 99 END) TO 'party_leaders_historical.csv' WITH CSV HEADER
\echo '   ✓ party_leaders_historical.csv - Complete party leadership history'
\echo ''

-- ===========================================================================
-- 6. PARTY VOTING SUMMARY
-- ===========================================================================
-- Aggregated voting statistics by party
--
\echo '6. Extracting Party Voting Summary...'
\copy (SELECT party, COUNT(DISTINCT ballot_id) AS total_ballots, SUM(party_total) AS total_votes, SUM(party_yes) AS yes_votes, SUM(party_no) AS no_votes, SUM(party_abstain) AS abstain_votes, SUM(party_absent) AS absent_votes, ROUND(100.0 * SUM(party_yes) / NULLIF(SUM(party_total), 0), 2) AS yes_percentage, ROUND(100.0 * SUM(party_no) / NULLIF(SUM(party_total), 0), 2) AS no_percentage, ROUND(100.0 * SUM(party_abstain) / NULLIF(SUM(party_total), 0), 2) AS abstain_percentage, ROUND(100.0 * SUM(party_absent) / NULLIF(SUM(party_total), 0), 2) AS absent_percentage, ROUND(100.0 * SUM(CASE WHEN party_approved THEN 1 ELSE 0 END) / NULLIF(COUNT(*), 0), 2) AS win_rate FROM view_riksdagen_vote_data_ballot_party_summary WHERE party IS NOT NULL GROUP BY party ORDER BY party) TO 'party_voting_summary.csv' WITH CSV HEADER
\echo '   ✓ party_voting_summary.csv - Party-level voting statistics'
\echo ''

-- ===========================================================================
-- 7. PARTY COMPOSITION TIMELINE
-- ===========================================================================
-- Party membership changes over time (annual snapshot)
--
\echo '7. Extracting Party Composition Timeline...'
\copy (SELECT EXTRACT(YEAR FROM a.from_date)::INTEGER AS year, p.party, COUNT(DISTINCT p.id) AS member_count, COUNT(DISTINCT CASE WHEN p.gender = 'man' THEN p.id END) AS male_count, COUNT(DISTINCT CASE WHEN p.gender = 'kvinna' THEN p.id END) AS female_count, AVG(EXTRACT(YEAR FROM a.from_date) - p.born_year) AS avg_age FROM assignment_data a JOIN person_data p ON a.intressent_id = p.id WHERE p.party IS NOT NULL AND a.status = 'Tjänstgörande' AND a.from_date IS NOT NULL AND EXTRACT(YEAR FROM a.from_date) >= 2000 GROUP BY EXTRACT(YEAR FROM a.from_date), p.party ORDER BY year DESC, p.party) TO 'party_composition_timeline.csv' WITH CSV HEADER
\echo '   ✓ party_composition_timeline.csv - Annual party composition changes'
\echo ''

-- ===========================================================================
-- SUMMARY
-- ===========================================================================
\echo '=================================================='
\echo 'Party Data Extraction Complete'
\echo ''
\echo 'Generated Files:'
\echo '  1. party_master_data.csv              - All registered parties'
\echo '  2. party_members_current.csv          - Current active members'
\echo '  3. party_members_historical.csv       - Complete membership history'
\echo '  4. party_leaders_current.csv          - Current leadership positions'
\echo '  5. party_leaders_historical.csv       - Historical leadership transitions'
\echo '  6. party_voting_summary.csv           - Party voting statistics'
\echo '  7. party_composition_timeline.csv     - Annual membership changes'
\echo ''
\echo 'Usage: Import these CSV files for party analysis'
\echo 'Integration: Used by riksdagsmonitor for party dashboards'
\echo '=================================================='
