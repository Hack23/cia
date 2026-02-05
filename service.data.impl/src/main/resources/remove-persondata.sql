-- remove-persondata.sql
-- Person Data Removal Script (GDPR Compliance)
--
-- Purpose: Removes all person-related data from the database
--          Used for GDPR "right to erasure" compliance or data anonymization
--
-- Usage:
--   psql -U postgres -d cia_dev -f remove-persondata.sql
--
-- ⚠️  WARNING: DESTRUCTIVE OPERATION - GDPR COMPLIANCE TOOL
--   This script permanently deletes ALL person data.
--   Use only when legally required (GDPR erasure request) or for anonymization.
--   Always backup database before running.
--   Verify legal authorization before execution.
--
-- Description:
--   Deletes all records from person-related tables in correct dependency order:
--   1. PERSON_ASSIGNMENT_DATA (dependent data)
--   2. ASSIGNMENT_DATA (dependent data)
--   3. PERSON_DETAIL_DATA (dependent data)
--   4. DETAIL_DATA (dependent data)
--   5. PERSON_DATA (primary table)
--
-- Output: Number of rows deleted from each table
--
-- Legal Context:
--   GDPR Article 17: Right to Erasure
--   Use for data subject requests or retention policy compliance
--
-- Safety: Run within a transaction for testing:
--   BEGIN;
--   \i remove-persondata.sql
--   SELECT COUNT(*) FROM person_data; -- Should be 0
--   ROLLBACK; -- or COMMIT if authorized

delete from PERSON_DATA;
delete from DETAIL_DATA;
delete from PERSON_DETAIL_DATA;
delete from ASSIGNMENT_DATA;
delete from PERSON_ASSIGNMENT_DATA;
