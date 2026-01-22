-- =================================================================
-- CIA Platform - Priority 1 Foreign Key Indexes
-- CRITICAL: Create these indexes on Day 1 before any production use
-- Impact: 10-100x performance improvement on JOINs
-- Estimated Total Time: 1-2 hours
-- Database: cia_dev
-- PostgreSQL: 16.11+
-- =================================================================
-- 
-- Usage:
--   sudo -u postgres psql -d cia_dev -f priority1_indexes.sql
--
-- Monitor progress:
--   SELECT * FROM pg_stat_progress_create_index;
--
-- =================================================================

\timing on
\set ON_ERROR_STOP on

-- Start transaction for atomic creation
BEGIN;

\echo '==================================================================='
\echo 'Creating 28 Foreign Key Indexes (Priority 1 - CRITICAL)'
\echo 'Started: ' `date`
\echo '==================================================================='

-- Person Data Foreign Keys
\echo 'Creating person_data indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_person_data_assignment_fk 
ON person_data(person_assignment_data_perso_0) 
WHERE person_assignment_data_perso_0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_person_data_detail_fk 
ON person_data(person_detail_data_person_da_0) 
WHERE person_detail_data_person_da_0 IS NOT NULL;

-- Document Status Container Foreign Keys
\echo 'Creating document_status_container indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_status_document_fk 
ON document_status_container(document_document_status_con_0) 
WHERE document_document_status_con_0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_status_activity_fk 
ON document_status_container(document_activity_container__0) 
WHERE document_activity_container__0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_status_attachment_fk 
ON document_status_container(document_attachment_containe_0) 
WHERE document_attachment_containe_0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_status_detail_fk 
ON document_status_container(document_detail_container_do_0) 
WHERE document_detail_container_do_0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_status_person_ref_fk 
ON document_status_container(document_person_reference_co_1) 
WHERE document_person_reference_co_1 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_status_proposal_fk 
ON document_status_container(document_proposal_document_s_0) 
WHERE document_proposal_document_s_0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_status_reference_fk 
ON document_status_container(document_reference_container_0) 
WHERE document_reference_container_0 IS NOT NULL;

-- Person Container Foreign Keys
\echo 'Creating person_container_data indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_person_container_person_fk 
ON person_container_data(person_person_container_data_0) 
WHERE person_person_container_data_0 IS NOT NULL;

-- Committee Proposal Foreign Keys
\echo 'Creating committee_proposal indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_committee_proposal_against_fk 
ON committee_proposal_component_0(against_proposal_container_c_0) 
WHERE against_proposal_container_c_0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_committee_proposal_container_fk 
ON committee_proposal_component_0(committee_proposal_container_0) 
WHERE committee_proposal_container_0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_committee_proposal_document_fk 
ON committee_proposal_component_0(document_committee_proposal__0) 
WHERE document_committee_proposal__0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_committee_proposal_list_fk 
ON committee_proposal_data(committee_proposal_list_comm_0) 
WHERE committee_proposal_list_comm_0 IS NOT NULL;

-- Assignment Data Foreign Keys
\echo 'Creating assignment_data indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_assignment_data_list_fk 
ON assignment_data(assignment_list_person_assig_0) 
WHERE assignment_list_person_assig_0 IS NOT NULL;

-- Assignment Element Foreign Keys
\echo 'Creating assignment_element indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_assignment_element_uppdrag_fk 
ON assignment_element(uppdrag_person_assignment_el_0) 
WHERE uppdrag_person_assignment_el_0 IS NOT NULL;

-- Document Activity Foreign Keys
\echo 'Creating document_activity_data indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_activity_list_fk 
ON document_activity_data(document_activities_document_0) 
WHERE document_activities_document_0 IS NOT NULL;

-- Document Attachment Foreign Keys
\echo 'Creating document_attachment indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_attachment_list_fk 
ON document_attachment(document_attachment_list_doc_0) 
WHERE document_attachment_list_doc_0 IS NOT NULL;

-- Document Detail Foreign Keys
\echo 'Creating document_detail_data indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_detail_list_fk 
ON document_detail_data(document_detail_list_documen_0) 
WHERE document_detail_list_documen_0 IS NOT NULL;

-- Document Element Foreign Keys
\echo 'Creating document_element indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_element_container_fk 
ON document_element(dokument_document_container__0) 
WHERE dokument_document_container__0 IS NOT NULL;

-- Document Person Reference Foreign Keys
\echo 'Creating document_person_reference indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_person_ref_list_fk 
ON document_person_reference_da_0(document_person_reference_li_1) 
WHERE document_person_reference_li_1 IS NOT NULL;

-- Document Proposal Container Foreign Keys
\echo 'Creating document_proposal_container indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_proposal_container_fk 
ON document_proposal_container(proposal_document_proposal_c_0) 
WHERE proposal_document_proposal_c_0 IS NOT NULL;

-- Document Reference Foreign Keys
\echo 'Creating document_reference_data indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_doc_reference_list_fk 
ON document_reference_data(document_reference_list_docu_0) 
WHERE document_reference_list_docu_0 IS NOT NULL;

-- Person Detail Element Foreign Keys
\echo 'Creating person_detail_element indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_person_detail_element_fk 
ON person_detail_element(detail_list_person_detail_el_0) 
WHERE detail_list_person_detail_el_0 IS NOT NULL;

-- Person Element Foreign Keys
\echo 'Creating person_element indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_person_element_assignment_fk 
ON person_element(person_assignment_element_pe_0) 
WHERE person_assignment_element_pe_0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_person_element_detail_fk 
ON person_element(person_detail_element_person_0) 
WHERE person_detail_element_person_0 IS NOT NULL;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_person_element_container_fk 
ON person_element(person_person_container_elem_0) 
WHERE person_person_container_elem_0 IS NOT NULL;

-- Sweden Political Party Foreign Keys
\echo 'Creating sweden_political_party indexes...'
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_sweden_party_region_fk 
ON sweden_political_party(parties_sweden_election_regi_0) 
WHERE parties_sweden_election_regi_0 IS NOT NULL;

COMMIT;

\echo '==================================================================='
\echo 'Verification: Check created indexes'
\echo '==================================================================='

SELECT 
    'P1 Foreign Key Indexes Created' as status,
    COUNT(*) as total_indexes,
    pg_size_pretty(SUM(pg_relation_size(indexrelid))) as total_size
FROM pg_indexes i
JOIN pg_stat_user_indexes si ON i.indexname = si.indexrelname
WHERE schemaname = 'public' 
  AND indexname LIKE 'idx_%_fk';

\echo '==================================================================='
\echo 'Completed: ' `date`
\echo 'Expected count: 28 indexes'
\echo 'Next step: Run ANALYZE on all tables'
\echo '==================================================================='

\echo 'Running ANALYZE to update statistics...'
ANALYZE;

\echo '==================================================================='
\echo 'SUCCESS: Priority 1 indexes created and statistics updated'
\echo '==================================================================='

\timing off

