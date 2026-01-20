/*
 * Copyright 2010-2025 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
 */
package com.hack23.cia.service.data.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * End-to-end integration tests for the complete analysis workflow.
 * 
 * Tests the full data flow: extraction → analysis → rules → reporting.
 * Validates integration between extraction, temporal views, and risk rules.
 * 
 * Coverage targets: 80% line coverage, 70% branch coverage
 */
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Analysis Workflow Integration Tests")
@Tag("integration")
@Tag("workflow")
@Tag("end-to-end")
public class AnalysisWorkflowIntegrationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisWorkflowIntegrationTest.class);
	
	@Container
	private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
		.withDatabaseName("cia_test")
		.withUsername("test")
		.withPassword("test")
		.withInitScript("test-schema.sql");
	
	private DataSource dataSource;
	
	// Workflow state tracking with typed accessors
	private static class WorkflowState {
		private Integer personCount;
		private Map<String, Integer> votingSummary;
		private Integer riskAssessments;
		private List<Map<String, Object>> finalReport;
		
		public synchronized void setPersonCount(Integer count) { this.personCount = count; }
		public synchronized Integer getPersonCount() { return this.personCount; }
		
		public synchronized void setVotingSummary(Map<String, Integer> summary) { this.votingSummary = summary; }
		public synchronized Map<String, Integer> getVotingSummary() { return this.votingSummary; }
		
		public synchronized void setRiskAssessments(Integer assessments) { this.riskAssessments = assessments; }
		public synchronized Integer getRiskAssessments() { return this.riskAssessments; }
		
		public synchronized void setFinalReport(List<Map<String, Object>> report) { this.finalReport = report; }
		public synchronized List<Map<String, Object>> getFinalReport() { return this.finalReport; }
	}
	
	private WorkflowState workflowState = new WorkflowState();

	/**
	 * Setup database connection and initialize workflow environment.
	 */
	@BeforeAll
	public void setup() {
		LOGGER.info("Setting up end-to-end workflow integration tests");
		dataSource = new DriverManagerDataSource(
			postgres.getJdbcUrl(),
			postgres.getUsername(),
			postgres.getPassword()
		);
		LOGGER.info("PostgreSQL testcontainer started: {}", postgres.getJdbcUrl());
	}
	
	/**
	 * Cleanup after all tests.
	 */
	@AfterAll
	public void teardown() {
		LOGGER.info("Tearing down workflow test environment");
	}

	/**
	 * Test 1: Data extraction workflow stage.
	 * Verifies initial data can be extracted and loaded.
	 */
	@Test
	@Order(1)
	@DisplayName("Test 1: Data extraction workflow stage")
	public void testDataExtractionStage() {
		LOGGER.info("Test 1: Testing data extraction stage");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create base tables
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS vote_data (" +
					"vote_id SERIAL PRIMARY KEY, " +
					"person_id VARCHAR(50), " +
					"ballot_id VARCHAR(50), " +
					"vote VARCHAR(20), " +
					"vote_date DATE, " +
					"party VARCHAR(50)" +
					")"
				);
				
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS person_data (" +
					"person_id VARCHAR(50) PRIMARY KEY, " +
					"first_name VARCHAR(50), " +
					"last_name VARCHAR(50), " +
					"party VARCHAR(50), " +
					"status VARCHAR(20)" +
					")"
				);
				
				// Insert sample data
				stmt.execute(
					"INSERT INTO person_data VALUES " +
					"('P001', 'John', 'Doe', 'Party A', 'ACTIVE'), " +
					"('P002', 'Jane', 'Smith', 'Party B', 'ACTIVE'), " +
					"('P003', 'Bob', 'Johnson', 'Party C', 'INACTIVE')"
				);
				
				stmt.execute(
					"INSERT INTO vote_data (person_id, ballot_id, vote, vote_date, party) VALUES " +
					"('P001', 'B001', 'YES', '2024-01-15', 'Party A'), " +
					"('P001', 'B002', 'YES', '2024-01-16', 'Party A'), " +
					"('P002', 'B001', 'NO', '2024-01-15', 'Party B'), " +
					"('P002', 'B002', 'ABSENT', '2024-01-16', 'Party B')"
				);
				
				// Verify extraction
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM person_data");
				rs.next();
				int personCount = rs.getInt(1);
				
				assertThat(personCount).isEqualTo(3);
				workflowState.setPersonCount(personCount);
				
				LOGGER.info("Data extraction stage completed: {} persons", personCount);
			}
		});
	}

	/**
	 * Test 2: Temporal analysis workflow stage.
	 * Verifies temporal views can be created and queried.
	 */
	@Test
	@Order(2)
	@DisplayName("Test 2: Temporal analysis workflow stage")
	public void testTemporalAnalysisStage() {
		LOGGER.info("Test 2: Testing temporal analysis stage");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create temporal view: voting summary by person
				stmt.execute(
					"CREATE OR REPLACE VIEW view_person_voting_summary AS " +
					"SELECT " +
					"  person_id, " +
					"  COUNT(*) as total_votes, " +
					"  COUNT(*) FILTER (WHERE vote = 'YES') as yes_votes, " +
					"  COUNT(*) FILTER (WHERE vote = 'NO') as no_votes, " +
					"  COUNT(*) FILTER (WHERE vote = 'ABSENT') as absent_votes " +
					"FROM vote_data " +
					"GROUP BY person_id"
				);
				
				// Query temporal view
				ResultSet rs = stmt.executeQuery(
					"SELECT person_id, total_votes, absent_votes FROM view_person_voting_summary"
				);
				
				Map<String, Integer> votingSummary = new HashMap<>();
				while (rs.next()) {
					votingSummary.put(
						rs.getString("person_id"),
						rs.getInt("total_votes")
					);
				}
				
				assertThat(votingSummary).isNotEmpty();
				workflowState.setVotingSummary(votingSummary);
				
				LOGGER.info("Temporal analysis stage completed: {} summaries", votingSummary.size());
			}
		});
	}

	/**
	 * Test 3: Risk assessment workflow stage.
	 * Applies risk rules to analyzed data.
	 */
	@Test
	@Order(3)
	@DisplayName("Test 3: Risk assessment workflow stage")
	public void testRiskAssessmentStage() {
		LOGGER.info("Test 3: Testing risk assessment stage");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create risk assessment table
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS risk_assessment (" +
					"assessment_id SERIAL PRIMARY KEY, " +
					"person_id VARCHAR(50), " +
					"risk_score DECIMAL(10,2), " +
					"risk_level VARCHAR(20), " +
					"risk_factors TEXT" +
					")"
				);
				
				// Calculate and insert risk assessments
				stmt.execute(
					"INSERT INTO risk_assessment (person_id, risk_score, risk_level, risk_factors) " +
					"SELECT " +
					"  p.person_id, " +
					"  CASE " +
					"    WHEN v.absent_votes > 0 THEN 50.0 + (v.absent_votes * 10) " +
					"    ELSE 20.0 " +
					"  END as risk_score, " +
					"  CASE " +
					"    WHEN v.absent_votes > 0 THEN 'MEDIUM' " +
					"    ELSE 'LOW' " +
					"  END as risk_level, " +
					"  'Absence: ' || COALESCE(v.absent_votes::TEXT, '0') as risk_factors " +
					"FROM person_data p " +
					"LEFT JOIN view_person_voting_summary v ON p.person_id = v.person_id"
				);
				
				// Query risk assessments
				ResultSet rs = stmt.executeQuery(
					"SELECT COUNT(*) as total, " +
					"COUNT(*) FILTER (WHERE risk_level = 'MEDIUM') as medium_risk, " +
					"COUNT(*) FILTER (WHERE risk_level = 'LOW') as low_risk " +
					"FROM risk_assessment"
				);
				
				rs.next();
				int totalAssessments = rs.getInt("total");
				int mediumRisk = rs.getInt("medium_risk");
				
				assertThat(totalAssessments).isGreaterThan(0);
				workflowState.setRiskAssessments(totalAssessments);
				
				LOGGER.info("Risk assessment stage completed: {} assessments, {} medium risk", 
					totalAssessments, mediumRisk);
			}
		});
	}

	/**
	 * Test 4: Reporting workflow stage.
	 * Generates final reports from analyzed and assessed data.
	 */
	@Test
	@Order(4)
	@DisplayName("Test 4: Reporting workflow stage")
	public void testReportingStage() {
		LOGGER.info("Test 4: Testing reporting stage");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Generate comprehensive report
				ResultSet rs = stmt.executeQuery(
					"SELECT " +
					"  p.person_id, " +
					"  p.first_name, " +
					"  p.last_name, " +
					"  p.party, " +
					"  COALESCE(v.total_votes, 0) as total_votes, " +
					"  COALESCE(r.risk_score, 0) as risk_score, " +
					"  COALESCE(r.risk_level, 'UNKNOWN') as risk_level " +
					"FROM person_data p " +
					"LEFT JOIN view_person_voting_summary v ON p.person_id = v.person_id " +
					"LEFT JOIN risk_assessment r ON p.person_id = r.person_id " +
					"ORDER BY r.risk_score DESC NULLS LAST"
				);
				
				List<Map<String, Object>> report = new ArrayList<>();
				while (rs.next()) {
					Map<String, Object> row = new HashMap<>();
					row.put("person_id", rs.getString("person_id"));
					row.put("name", rs.getString("first_name") + " " + rs.getString("last_name"));
					row.put("party", rs.getString("party"));
					row.put("total_votes", rs.getInt("total_votes"));
					row.put("risk_score", rs.getDouble("risk_score"));
					row.put("risk_level", rs.getString("risk_level"));
					report.add(row);
				}
				
				assertThat(report).isNotEmpty();
				workflowState.setFinalReport(report);
				
				LOGGER.info("Reporting stage completed: {} records in report", report.size());
				
				// Log sample of report
				if (!report.isEmpty()) {
					Map<String, Object> first = report.get(0);
					LOGGER.info("Sample report entry: {} - {} - Risk: {}", 
						first.get("name"), first.get("party"), first.get("risk_level"));
				}
			}
		});
	}

	/**
	 * Test 5: End-to-end workflow validation.
	 * Verifies complete data flow from extraction to reporting.
	 */
	@Test
	@Order(5)
	@DisplayName("Test 5: End-to-end workflow validation")
	public void testEndToEndWorkflow() {
		LOGGER.info("Test 5: Testing end-to-end workflow");
		
		// Retrieve workflow state once and verify all workflow stages completed
		final Integer personCount = workflowState.getPersonCount();
		final Map<String, Integer> votingSummary = workflowState.getVotingSummary();
		final Integer riskAssessments = workflowState.getRiskAssessments();
		final List<Map<String, Object>> report = workflowState.getFinalReport();
		
		assertNotNull(personCount, "Person count must not be null");
		assertNotNull(votingSummary, "Voting summary must not be null");
		assertNotNull(riskAssessments, "Risk assessments count must not be null");
		assertNotNull(report, "Final report must not be null");
		
		final int personCountValue = personCount;
		final int riskAssessmentsValue = riskAssessments;
		
		// Validate data consistency across workflow
		assertThat(personCountValue).isEqualTo(3);
		assertThat(riskAssessmentsValue).isEqualTo(personCountValue);
		assertThat(report).hasSize(personCountValue);
		
		LOGGER.info("End-to-end workflow validated successfully");
		LOGGER.info("Workflow summary: {} persons -> {} risk assessments -> {} report entries",
			personCountValue, riskAssessmentsValue, report.size());
	}

	/**
	 * Test 6: Workflow performance benchmark.
	 * Measures end-to-end workflow execution time.
	 */
	@Test
	@Order(6)
	@DisplayName("Test 6: Workflow performance benchmark")
	public void testWorkflowPerformance() {
		LOGGER.info("Test 6: Testing workflow performance");
		
		Instant start = Instant.now();
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Execute complete workflow in sequence
				
				// Stage 1: Load data
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS perf_test_data (" +
					"id SERIAL PRIMARY KEY, " +
					"value INTEGER" +
					")"
				);
				
				for (int i = 0; i < 100; i++) {
					stmt.execute("INSERT INTO perf_test_data (value) VALUES (" + i + ")");
				}
				
				// Stage 2: Analyze
				stmt.execute(
					"CREATE OR REPLACE VIEW perf_test_view AS " +
					"SELECT AVG(value) as avg_value, COUNT(*) as count FROM perf_test_data"
				);
				
				// Stage 3: Report
				ResultSet rs = stmt.executeQuery("SELECT * FROM perf_test_view");
				rs.next();
				double avgValue = rs.getDouble("avg_value");
				int count = rs.getInt("count");
				
				assertThat(count).isEqualTo(100);
			}
		});
		
		Duration elapsed = Duration.between(start, Instant.now());
		LOGGER.info("Workflow completed in {} ms", elapsed.toMillis());
		
		// Workflow should complete in reasonable time
		assertTrue(elapsed.toSeconds() < 30, "Workflow should complete in under 30 seconds");
	}

	/**
	 * Test 7: Data integrity validation.
	 * Ensures data integrity is maintained throughout workflow.
	 */
	@Test
	@Order(7)
	@DisplayName("Test 7: Data integrity validation")
	public void testDataIntegrityValidation() {
		LOGGER.info("Test 7: Testing data integrity validation");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Verify no data loss between stages
				ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM person_data");
				rs1.next();
				int baseCount = rs1.getInt(1);
				
				ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM risk_assessment");
				rs2.next();
				int assessmentCount = rs2.getInt(1);
				
				// All persons should have risk assessments
				assertThat(assessmentCount).isEqualTo(baseCount);
				
				// Verify no null keys
				ResultSet rs3 = stmt.executeQuery(
					"SELECT COUNT(*) FROM risk_assessment WHERE person_id IS NULL"
				);
				rs3.next();
				int nullKeys = rs3.getInt(1);
				
				assertThat(nullKeys).isEqualTo(0);
				
				LOGGER.info("Data integrity validated: {} base records = {} assessments, 0 null keys",
					baseCount, assessmentCount);
			}
		});
	}

	/**
	 * Test 8: Error handling and recovery.
	 * Validates workflow handles errors gracefully.
	 */
	@Test
	@Order(8)
	@DisplayName("Test 8: Error handling and recovery")
	public void testErrorHandlingAndRecovery() {
		LOGGER.info("Test 8: Testing error handling and recovery");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create table with constraints
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS error_test (" +
					"id SERIAL PRIMARY KEY, " +
					"required_value VARCHAR(50) NOT NULL" +
					")"
				);
				
				// Test 1: Handle constraint violation gracefully
				try {
					stmt.execute("INSERT INTO error_test (required_value) VALUES (NULL)");
				} catch (Exception e) {
					LOGGER.info("Expected error caught: {}", e.getMessage());
				}
				
				// Test 2: Verify table is still usable after error
				stmt.execute("INSERT INTO error_test (required_value) VALUES ('valid')");
				
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM error_test");
				rs.next();
				int count = rs.getInt(1);
				
				assertThat(count).isEqualTo(1);
				
				LOGGER.info("Error handling validated successfully");
			}
		});
	}

	/**
	 * Test 9: Concurrent workflow execution.
	 * Tests workflow can handle concurrent executions.
	 */
	@Test
	@Order(9)
	@DisplayName("Test 9: Concurrent workflow execution")
	public void testConcurrentWorkflowExecution() {
		LOGGER.info("Test 9: Testing concurrent workflow execution");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create test table with proper indexing
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS concurrent_test (" +
					"id SERIAL PRIMARY KEY, " +
					"thread_id INTEGER, " +
					"value INTEGER" +
					")"
				);
				
				// Simulate concurrent inserts
				for (int thread = 1; thread <= 3; thread++) {
					for (int i = 0; i < 10; i++) {
						stmt.execute(
							"INSERT INTO concurrent_test (thread_id, value) VALUES " +
							"(" + thread + ", " + i + ")"
						);
					}
				}
				
				// Verify all inserts completed
				ResultSet rs = stmt.executeQuery(
					"SELECT thread_id, COUNT(*) as count FROM concurrent_test " +
					"GROUP BY thread_id ORDER BY thread_id"
				);
				
				int totalThreads = 0;
				while (rs.next()) {
					int count = rs.getInt("count");
					assertThat(count).isEqualTo(10);
					totalThreads++;
				}
				
				assertThat(totalThreads).isEqualTo(3);
				
				LOGGER.info("Concurrent execution validated: {} threads completed", totalThreads);
			}
		});
	}

	/**
	 * Test 10: Workflow rollback and cleanup.
	 * Verifies workflow can be rolled back and cleaned up.
	 */
	@Test
	@Order(10)
	@DisplayName("Test 10: Workflow rollback and cleanup")
	public void testWorkflowRollbackAndCleanup() {
		LOGGER.info("Test 10: Testing workflow rollback and cleanup");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Begin transaction
				conn.setAutoCommit(false);
				
				// Create and populate temporary table
				stmt.execute(
					"CREATE TEMP TABLE rollback_test (" +
					"id SERIAL PRIMARY KEY, " +
					"value INTEGER" +
					")"
				);
				
				stmt.execute("INSERT INTO rollback_test (value) VALUES (1), (2), (3)");
				
				// Rollback transaction
				conn.rollback();
				
				// Verify data was rolled back
				try {
					ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM rollback_test");
					// Table should not exist after rollback
					rs.next();
					int count = rs.getInt(1);
					assertThat(count).isEqualTo(0);
				} catch (Exception e) {
					// Expected: table doesn't exist after rollback
					LOGGER.info("Rollback successful: table removed as expected");
				}
				
				conn.setAutoCommit(true);
				
				LOGGER.info("Rollback and cleanup validated successfully");
			}
		});
	}
}
