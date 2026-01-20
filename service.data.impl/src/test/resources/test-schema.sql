-- Test schema initialization for integration tests
-- Minimal schema required for testcontainers

-- Enable required extensions
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Core tables for testing

CREATE TABLE IF NOT EXISTS vote_data (
    vote_id SERIAL PRIMARY KEY,
    embedded_id_intressent_id VARCHAR(50),
    ballot_id VARCHAR(50),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    party VARCHAR(100),
    vote VARCHAR(50),
    vote_date DATE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS person_data (
    person_id VARCHAR(50) PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    party VARCHAR(100),
    status VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS assignment_data (
    assignment_id SERIAL PRIMARY KEY,
    person_id VARCHAR(50),
    org_code VARCHAR(50),
    role_code VARCHAR(50),
    assignment_type VARCHAR(100),
    detail VARCHAR(200),
    status VARCHAR(50),
    from_date DATE,
    to_date DATE,
    FOREIGN KEY (person_id) REFERENCES person_data(person_id)
);

CREATE TABLE IF NOT EXISTS document_data (
    document_id SERIAL PRIMARY KEY,
    doc_id VARCHAR(50) UNIQUE,
    document_type VARCHAR(100),
    title TEXT,
    sub_title TEXT,
    status VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS rule_violation (
    id SERIAL PRIMARY KEY,
    person_id VARCHAR(50),
    rule_name VARCHAR(200),
    status VARCHAR(50),
    risk_score DECIMAL(10, 2),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for performance
CREATE INDEX IF NOT EXISTS idx_vote_data_person_id ON vote_data(embedded_id_intressent_id);
CREATE INDEX IF NOT EXISTS idx_vote_data_date ON vote_data(vote_date);
CREATE INDEX IF NOT EXISTS idx_person_data_party ON person_data(party);
CREATE INDEX IF NOT EXISTS idx_assignment_data_person_id ON assignment_data(person_id);
CREATE INDEX IF NOT EXISTS idx_rule_violation_person_id ON rule_violation(person_id);

-- Sample views for testing (empty shells - tests will use separate tables)
-- These views are here for reference but tests use test_* tables

CREATE OR REPLACE VIEW view_ministry_risk_evolution AS
SELECT 
    ministry_name,
    risk_quarter,
    risk_level,
    risk_score,
    quarter_change
FROM (SELECT NULL::VARCHAR as ministry_name, NULL::VARCHAR as risk_quarter, 
      NULL::VARCHAR as risk_level, NULL::DECIMAL as risk_score, 
      NULL::DECIMAL as quarter_change) t
WHERE 1=0; -- Placeholder view returning no rows for schema compatibility in tests

CREATE OR REPLACE VIEW view_party_effectiveness_trends AS
SELECT 
    party,
    year,
    effectiveness_score,
    trend,
    documents_count
FROM (SELECT NULL::VARCHAR as party, NULL::INTEGER as year,
      NULL::DECIMAL as effectiveness_score, NULL::VARCHAR as trend,
      NULL::INTEGER as documents_count) t
WHERE 1=0; -- Empty reference view

CREATE OR REPLACE VIEW view_decision_temporal_trends AS
SELECT 
    decision_type,
    period_month,
    decision_count,
    avg_decision_time_days,
    trend_indicator
FROM (SELECT NULL::VARCHAR as decision_type, NULL::VARCHAR as period_month,
      NULL::INTEGER as decision_count, NULL::DECIMAL as avg_decision_time_days,
      NULL::VARCHAR as trend_indicator) t
WHERE 1=0; -- Empty reference view

CREATE OR REPLACE VIEW view_politician_behavioral_trends AS
SELECT 
    person_id,
    first_name,
    last_name,
    party,
    period_quarter,
    voting_participation_rate,
    document_activity_score,
    behavioral_trend
FROM (SELECT NULL::VARCHAR as person_id, NULL::VARCHAR as first_name,
      NULL::VARCHAR as last_name, NULL::VARCHAR as party,
      NULL::VARCHAR as period_quarter, NULL::DECIMAL as voting_participation_rate,
      NULL::DECIMAL as document_activity_score, NULL::VARCHAR as behavioral_trend) t
WHERE 1=0; -- Empty reference view

-- Note: Tests use test_* prefixed tables to avoid confusion with views

-- Grant permissions
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO test;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO test;

-- Insert test data
INSERT INTO person_data (person_id, first_name, last_name, party, status) VALUES
('TEST001', 'Test', 'Person1', 'Test Party A', 'ACTIVE'),
('TEST002', 'Test', 'Person2', 'Test Party B', 'ACTIVE'),
('TEST003', 'Test', 'Person3', 'Test Party C', 'INACTIVE')
ON CONFLICT (person_id) DO NOTHING;

INSERT INTO vote_data (embedded_id_intressent_id, ballot_id, first_name, last_name, party, vote, vote_date) VALUES
('TEST001', 'BALLOT001', 'Test', 'Person1', 'Test Party A', 'YES', '2024-01-15'),
('TEST002', 'BALLOT001', 'Test', 'Person2', 'Test Party B', 'NO', '2024-01-15'),
('TEST003', 'BALLOT001', 'Test', 'Person3', 'Test Party C', 'ABSTAIN', '2024-01-15');
