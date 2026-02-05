-- remove-committee-proposal.sql  
-- Committee Proposal Data Removal Script
--
-- Purpose: Removes all committee proposal data from the database
--          Used for database reset, testing, or data cleanup
--
-- Usage:
--   psql -U postgres -d cia_dev -f remove-committee-proposal.sql
--
-- ⚠️  WARNING: DESTRUCTIVE OPERATION
--   This script permanently deletes ALL committee proposal data including:
--   - Committee proposal components, containers, and data
--   - Committee document data
--   - Against proposal containers and data
--   Always backup database before running.
--
-- Description:
--   Deletes all records from committee_proposal-related tables in correct
--   dependency order to avoid foreign key constraint violations.
--
-- Output: Number of rows deleted from each table
--
-- Safety: Run within a transaction for testing:
--   BEGIN;
--   \i remove-committee-proposal.sql
--   SELECT COUNT(*) FROM committee_proposal_data; -- Should be 0
--   ROLLBACK; -- or COMMIT if authorized

delete from committee_proposal_component_0;
delete from committee_proposal_container;
delete from committee_document_data;
delete from against_proposal_container;
delete from committee_proposal_data;
delete from against_proposal_data;

