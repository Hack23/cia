-- remove-documents.sql
-- Document Data Removal Script
--
-- Purpose: Removes all document-related data from the database
--          Used for database reset, testing, or data cleanup
--
-- Usage:
--   psql -U postgres -d cia_dev -f remove-documents.sql
--
-- ⚠️  WARNING: DESTRUCTIVE OPERATION
--   This script permanently deletes ALL document data including:
--   - Document elements (metadata)
--   - Document content data (actual content)
--   Always backup database before running.
--
-- Description:
--   Deletes all records from document-related tables in correct dependency order
--   to avoid foreign key constraint violations.
--
-- Output: Number of rows deleted from each table
--
-- Safety: Run within a transaction for testing:
--   BEGIN;
--   \i remove-documents.sql
--   SELECT COUNT(*) FROM document_element; -- Should be 0
--   ROLLBACK; -- or COMMIT if authorized

delete from document_element;
delete from document_content_data;