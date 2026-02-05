-- remove-documentstatus.sql
-- Complete Document Hierarchy Removal Script
--
-- Purpose: Removes all document-related data including status tracking
--          Used for complete database reset, testing, or data cleanup
--
-- Usage:
--   psql -U postgres -d cia_dev -f remove-documentstatus.sql
--
-- ⚠️  WARNING: DESTRUCTIVE OPERATION
--   This script permanently deletes ALL document-related data including:
--   - Document activities, attachments, references, proposals
--   - Document details, container elements
--   - Document status containers (final cleanup)
--   Always backup database before running.
--
-- Description:
--   Deletes all records from document-related tables in correct dependency order
--   to avoid foreign key constraint violations. This is a comprehensive cleanup
--   that removes the entire document hierarchy.
--
-- Output: Number of rows deleted from each table (14 tables total)
--
-- Safety: Run within a transaction for testing:
--   BEGIN;
--   \i remove-documentstatus.sql
--   SELECT COUNT(*) FROM document_status_container; -- Should be 0
--   ROLLBACK; -- or COMMIT if authorized

delete from document_activity_data;
delete from document_activity_container;
delete from document_person_reference_da_0;
delete from document_person_reference_co_0;
delete from document_attachment;
delete from document_attachment_container;
delete from document_reference_data;
delete from document_reference_container;
delete from document_proposal_container;
delete from document_proposal_data;
delete from document_detail_data;
delete from document_detail_container;
delete from document_data;
delete from document_container_element;
delete from document_status_container;
