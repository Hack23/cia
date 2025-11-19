-- extract-sample-data.sql
-- Sample Data Extraction for View Debugging and Testing
-- Citizen Intelligence Agency - Open Source Intelligence Platform
--
-- Purpose: Extracts sample data from all tables and views to CSV files
--          focusing on columns used in views for debugging empty views
--
-- Usage:
--   # Extract all sample data to CSV files
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-sample-data.sql
--
--   # Or extract to specific directory
--   cd /output/directory
--   psql -U postgres -d cia_dev -f service.data.impl/src/main/resources/extract-sample-data.sql
--
-- Output:
--   - Creates CSV files for each table: table_<tablename>_sample.csv
--   - Creates CSV files for each view: view_<viewname>_sample.csv
--   - Creates manifest file: sample_data_manifest.csv
--
-- Configuration:
--   SAMPLE_SIZE: Number of rows to extract per table/view (default: 10)

\set ON_ERROR_STOP off
\timing on

\echo '=================================================='
\echo 'CIA Sample Data Extraction'
\echo 'Started:' `date`
\echo '=================================================='

-- Configuration
\set SAMPLE_SIZE 10

\echo ''
\echo 'Configuration:'
\echo '  Sample size: 10 rows per table/view'
\echo '  Output format: CSV with headers'
\echo ''

-- ===========================================================================
-- SECTION 1: Extract Sample Data from Critical Tables
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== EXTRACTING TABLE SAMPLE DATA      ==='
\echo '=========================================='

-- Table: person_data
\echo ''
\echo 'Extracting: person_data (fields used in views)'
\copy (SELECT id, first_name, last_name, gender, born_year, party, status FROM person_data ORDER BY id LIMIT 10) TO 'table_person_data_sample.csv' WITH CSV HEADER;

-- Table: assignment_data
\echo 'Extracting: assignment_data (fields used in views)'
\copy (SELECT hjid, intressent_id, role_code, org_code, status, from_date, to_date, detail FROM assignment_data ORDER BY intressent_id, from_date LIMIT 10) TO 'table_assignment_data_sample.csv' WITH CSV HEADER;

-- Table: document_data
\echo 'Extracting: document_data (fields used in views)'
\copy (SELECT id, document_type, rm, status, label, temp_label, sub_type, made_public_date, org FROM document_data ORDER BY made_public_date DESC LIMIT 10) TO 'table_document_data_sample.csv' WITH CSV HEADER;

-- Table: document_element
\echo 'Extracting: document_element (fields used in views)'
\copy (SELECT id, label, document_type, org, status, made_public_date FROM document_element ORDER BY id LIMIT 10) TO 'table_document_element_sample.csv' WITH CSV HEADER;

-- Table: ballot_data (not a real table, skipping)
-- Note: ballot_data does not exist in schema
\echo 'Skipping: ballot_data (table does not exist in schema)'

-- Table: vote_data
\echo 'Extracting: vote_data (fields used in views)'
\copy (SELECT embedded_id_ballot_id, embedded_id_intressent_id, embedded_id_concern, embedded_id_issue, vote, party, vote_date FROM vote_data ORDER BY vote_date DESC LIMIT 10) TO 'table_vote_data_sample.csv' WITH CSV HEADER;

-- Table: sweden_political_party
\echo 'Extracting: sweden_political_party (fields used in views)'
\copy (SELECT hjid, party_id, party_name, short_code FROM sweden_political_party ORDER BY party_name LIMIT 10) TO 'table_sweden_political_party_sample.csv' WITH CSV HEADER;

-- Table: committee_proposal_data
\echo 'Extracting: committee_proposal_data (fields used in views)'
\copy (SELECT hjid, ballot_id, decision_type, committee_report FROM committee_proposal_data ORDER BY hjid LIMIT 10) TO 'table_committee_proposal_data_sample.csv' WITH CSV HEADER;

-- Table: party_member_data (not a real table, skipping)
-- Note: party_member_data does not exist in schema
\echo 'Skipping: party_member_data (table does not exist in schema)'

-- Table: world_bank_data
\echo 'Extracting: world_bank_data (fields used in views)'
\copy (SELECT hjid, country_id, indicator_id, year_date, data_value FROM world_bank_data ORDER BY year_date DESC LIMIT 10) TO 'table_world_bank_data_sample.csv' WITH CSV HEADER;

-- ===========================================================================
-- SECTION 2: Extract Sample Data from All Views
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== EXTRACTING VIEW SAMPLE DATA       ==='
\echo '=========================================='

-- Note: This section only analyzes views to identify which have data.
-- To extract samples from all views, use the shell script wrapper which
-- can dynamically generate and execute \copy commands for each view.
-- Below we extract only key intelligence views manually.

DO $$
DECLARE
    view_record RECORD;
    view_count INTEGER := 0;
    error_count INTEGER := 0;
    empty_count INTEGER := 0;
    row_count BIGINT;
BEGIN
    RAISE NOTICE 'Analyzing views for sample data extraction...';
    RAISE NOTICE '';
    
    FOR view_record IN 
        SELECT schemaname, viewname 
        FROM pg_views 
        WHERE schemaname = 'public'
        ORDER BY viewname
    LOOP
        BEGIN
            -- Check if view has data
            EXECUTE format('SELECT COUNT(*) FROM %I.%I', 
                view_record.schemaname, view_record.viewname) 
            INTO row_count;
            
            IF row_count > 0 THEN
                RAISE NOTICE 'View %: % rows available for extraction', 
                    view_record.viewname, row_count;
                view_count := view_count + 1;
            ELSE
                empty_count := empty_count + 1;
            END IF;
            
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'ERROR checking %: %', view_record.viewname, SQLERRM;
            error_count := error_count + 1;
        END;
    END LOOP;
    
    RAISE NOTICE '';
    RAISE NOTICE 'View analysis summary:';
    RAISE NOTICE '  Views with data: %', view_count;
    RAISE NOTICE '  Empty views: %', empty_count;
    RAISE NOTICE '  Errors: %', error_count;
    RAISE NOTICE '';
    RAISE NOTICE 'Manual extraction of additional views can be done using \copy commands.';
END $$;

-- Extract specific key views with detailed data
\echo ''
\echo 'Extracting key intelligence views...'

-- View: view_riksdagen_politician
\echo 'Extracting: view_riksdagen_politician'
\copy (SELECT * FROM view_riksdagen_politician LIMIT 10) TO 'view_riksdagen_politician_sample.csv' WITH CSV HEADER;

-- View: view_riksdagen_party
\echo 'Extracting: view_riksdagen_party'
\copy (SELECT * FROM view_riksdagen_party LIMIT 10) TO 'view_riksdagen_party_sample.csv' WITH CSV HEADER;

-- View: view_riksdagen_vote_data_ballot_summary
\echo 'Extracting: view_riksdagen_vote_data_ballot_summary'
\copy (SELECT * FROM view_riksdagen_vote_data_ballot_summary LIMIT 10) TO 'view_riksdagen_vote_data_ballot_summary_sample.csv' WITH CSV HEADER;

-- View: view_riksdagen_committee_decisions
\echo 'Extracting: view_riksdagen_committee_decisions'
\copy (SELECT * FROM view_riksdagen_committee_decisions LIMIT 10) TO 'view_riksdagen_committee_decisions_sample.csv' WITH CSV HEADER;

-- ===========================================================================
-- SECTION 3: Generate Manifest File
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== GENERATING MANIFEST FILE          ==='
\echo '=========================================='

-- Create manifest with metadata about all extracted files
\copy (SELECT 'TABLE' AS source_type, relname AS object_name, n_live_tup AS approximate_rows, pg_size_pretty(pg_total_relation_size(schemaname||'.'||relname)) AS size, 'table_' || relname || '_sample.csv' AS filename FROM pg_stat_user_tables WHERE schemaname = 'public' AND n_live_tup > 0 ORDER BY relname) TO 'sample_data_manifest.csv' WITH CSV HEADER;

-- ===========================================================================
-- SECTION 4: Generate Column Mapping for Views
-- ===========================================================================
\echo ''
\echo '=========================================='
\echo '=== GENERATING VIEW COLUMN MAPPING    ==='
\echo '=========================================='

-- Generate CSV showing which table columns are used in which views
\copy (WITH view_columns AS (SELECT table_schema, table_name, column_name FROM information_schema.columns WHERE table_schema = 'public' AND table_name IN (SELECT viewname FROM pg_views WHERE schemaname = 'public') ORDER BY table_name, column_name) SELECT * FROM view_columns) TO 'view_column_mapping.csv' WITH CSV HEADER;

\echo ''
\echo '=================================================='
\echo 'Sample data extraction completed'
\echo 'Finished:' `date`
\echo '=================================================='
\echo ''
\echo 'Generated files:'
\echo '  - table_*_sample.csv: Sample data from tables'
\echo '  - view_*_sample.csv: Sample data from views'
\echo '  - sample_data_manifest.csv: Metadata about extracted files'
\echo '  - view_column_mapping.csv: Column mappings for views'
\echo ''
\echo 'To reload sample data:'
\echo '  1. Review the CSV files'
\echo '  2. Use COPY FROM to import: '
\echo '     \copy table_name FROM ''table_name_sample.csv'' WITH CSV HEADER;'
\echo ''
\echo 'For troubleshooting empty views, see:'
\echo '  - TROUBLESHOOTING_EMPTY_VIEWS.md'
\echo '  - view_column_mapping.csv for required columns'
\echo ''

\timing off
