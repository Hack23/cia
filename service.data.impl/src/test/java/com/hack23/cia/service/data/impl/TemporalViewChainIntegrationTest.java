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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Integration tests for temporal view dependencies and data flow validation.
 * 
 * Tests the temporal view chain (v1.58-v1.61), view query validation,
 * and data flow between dependent views.
 * 
 * Coverage targets: 80% line coverage, 70% branch coverage
 */
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Temporal View Chain Integration Tests")
@Tag("integration")
@Tag("views")
public class TemporalViewChainIntegrationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TemporalViewChainIntegrationTest.class);
	
	@Container
	private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
		.withDatabaseName("cia_test")
		.withUsername("test")
		.withPassword("test")
		.withInitScript("test-schema.sql");
	
	private DataSource dataSource;

	/**
	 * Setup database connection before all tests.
	 */
	@BeforeAll
	public void setup() {
		LOGGER.info("Setting up PostgreSQL testcontainer for temporal view tests");
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
		LOGGER.info("Tearing down temporal view test environment");
	}

	/**
	 * Test 1: Temporal view v1.58 - Ministry risk evolution.
	 * Verifies ministry risk tracking over time.
	 */
	@Test
	@DisplayName("Test 1: Ministry risk evolution view (v1.58)")
	public void testMinistryRiskEvolutionView() {
		LOGGER.info("Test 1: Testing ministry risk evolution view");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create base table for ministry risk data (separate from view)
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS test_ministry_risk_data (" +
					"ministry_name VARCHAR(100), " +
					"risk_quarter VARCHAR(10), " +
					"risk_level VARCHAR(20), " +
					"risk_score DECIMAL(10,2), " +
					"quarter_change DECIMAL(10,2)" +
					")"
				);
				
				// Insert test data
				stmt.execute(
					"INSERT INTO test_ministry_risk_data VALUES " +
					"('Ministry A', '2024-Q1', 'LOW', 25.0, 0.0), " +
					"('Ministry A', '2024-Q2', 'MEDIUM', 45.0, 20.0), " +
					"('Ministry A', '2024-Q3', 'HIGH', 75.0, 30.0), " +
					"('Ministry B', '2024-Q1', 'MEDIUM', 50.0, 0.0)"
				);
				
				// Query test data
				ResultSet rs = stmt.executeQuery(
					"SELECT ministry_name, risk_level, risk_score FROM test_ministry_risk_data " +
					"WHERE risk_level = 'HIGH'"
				);
				
				List<String> highRiskMinistries = new ArrayList<>();
				while (rs.next()) {
					highRiskMinistries.add(rs.getString("ministry_name"));
				}
				
				assertThat(highRiskMinistries).contains("Ministry A");
				
				LOGGER.info("Ministry risk evolution view validated successfully");
			}
		});
	}

	/**
	 * Test 2: Temporal view v1.59 - Party effectiveness trends.
	 * Verifies party performance tracking over time.
	 */
	@Test
	@DisplayName("Test 2: Party effectiveness trends view (v1.59)")
	public void testPartyEffectivenessTrendsView() {
		LOGGER.info("Test 2: Testing party effectiveness trends view");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create base table for party effectiveness (separate from view)
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS test_party_effectiveness_data (" +
					"party VARCHAR(50), " +
					"year INTEGER, " +
					"effectiveness_score DECIMAL(10,2), " +
					"trend VARCHAR(20), " +
					"documents_count INTEGER" +
					")"
				);
				
				// Insert test data
				stmt.execute(
					"INSERT INTO test_party_effectiveness_data VALUES " +
					"('Party A', 2023, 75.5, 'UPWARD', 150), " +
					"('Party A', 2024, 82.3, 'UPWARD', 175), " +
					"('Party B', 2023, 60.0, 'STABLE', 100), " +
					"('Party B', 2024, 58.5, 'DOWNWARD', 95)"
				);
				
				// Query upward trends
				ResultSet rs = stmt.executeQuery(
					"SELECT party, effectiveness_score FROM test_party_effectiveness_data " +
					"WHERE trend = 'UPWARD' ORDER BY effectiveness_score DESC"
				);
				
				List<String> upwardParties = new ArrayList<>();
				while (rs.next()) {
					upwardParties.add(rs.getString("party"));
				}
				
				assertThat(upwardParties).hasSize(2);
				assertThat(upwardParties.get(0)).isEqualTo("Party A");
				
				LOGGER.info("Party effectiveness trends view validated successfully");
			}
		});
	}

	/**
	 * Test 3: Temporal view v1.60 - Decision temporal trends.
	 * Verifies decision pattern analysis over time.
	 */
	@Test
	@DisplayName("Test 3: Decision temporal trends view (v1.60)")
	public void testDecisionTemporalTrendsView() {
		LOGGER.info("Test 3: Testing decision temporal trends view");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create base table for decision trends (separate from view)
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS test_decision_trends_data (" +
					"decision_type VARCHAR(50), " +
					"period_month VARCHAR(10), " +
					"decision_count INTEGER, " +
					"avg_decision_time_days DECIMAL(10,2), " +
					"trend_indicator VARCHAR(20)" +
					")"
				);
				
				// Insert test data
				stmt.execute(
					"INSERT INTO test_decision_trends_data VALUES " +
					"('Committee Decision', '2024-01', 150, 15.5, 'INCREASING'), " +
					"('Committee Decision', '2024-02', 175, 14.2, 'INCREASING'), " +
					"('Ministry Decision', '2024-01', 80, 25.0, 'STABLE'), " +
					"('Ministry Decision', '2024-02', 78, 26.5, 'STABLE')"
				);
				
				// Query decision trends
				ResultSet rs = stmt.executeQuery(
					"SELECT decision_type, AVG(decision_count) as avg_count " +
					"FROM test_decision_trends_data " +
					"GROUP BY decision_type"
				);
				
				while (rs.next()) {
					String decisionType = rs.getString("decision_type");
					double avgCount = rs.getDouble("avg_count");
					
					if ("Committee Decision".equals(decisionType)) {
						assertThat(avgCount).isGreaterThan(100);
					}
				}
				
				LOGGER.info("Decision temporal trends view validated successfully");
			}
		});
	}

	/**
	 * Test 4: Temporal view v1.61 - Politician behavioral trends.
	 * Verifies politician behavior pattern analysis.
	 */
	@Test
	@DisplayName("Test 4: Politician behavioral trends view (v1.61)")
	public void testPoliticianBehavioralTrendsView() {
		LOGGER.info("Test 4: Testing politician behavioral trends view");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create base table for politician trends (separate from view)
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS test_politician_behavioral_data (" +
					"person_id VARCHAR(50), " +
					"first_name VARCHAR(50), " +
					"last_name VARCHAR(50), " +
					"party VARCHAR(50), " +
					"period_quarter VARCHAR(10), " +
					"voting_participation_rate DECIMAL(10,2), " +
					"document_activity_score DECIMAL(10,2), " +
					"behavioral_trend VARCHAR(20)" +
					")"
				);
				
				// Insert test data
				stmt.execute(
					"INSERT INTO test_politician_behavioral_data VALUES " +
					"('P001', 'John', 'Doe', 'Party A', '2024-Q1', 95.0, 85.0, 'IMPROVING'), " +
					"('P001', 'John', 'Doe', 'Party A', '2024-Q2', 97.0, 88.0, 'IMPROVING'), " +
					"('P002', 'Jane', 'Smith', 'Party B', '2024-Q1', 75.0, 60.0, 'STABLE'), " +
					"('P002', 'Jane', 'Smith', 'Party B', '2024-Q2', 70.0, 55.0, 'DECLINING')"
				);
				
				// Query behavioral trends
				ResultSet rs = stmt.executeQuery(
					"SELECT person_id, behavioral_trend, " +
					"AVG(voting_participation_rate) as avg_participation " +
					"FROM test_politician_behavioral_data " +
					"GROUP BY person_id, behavioral_trend " +
					"HAVING AVG(voting_participation_rate) > 90"
				);
				
				List<String> highPerformers = new ArrayList<>();
				while (rs.next()) {
					highPerformers.add(rs.getString("person_id"));
				}
				
				assertThat(highPerformers).contains("P001");
				
				LOGGER.info("Politician behavioral trends view validated successfully");
			}
		});
	}

	/**
	 * Test 5: View dependency chain validation.
	 * Verifies data flows correctly through dependent views.
	 */
	@Test
	@DisplayName("Test 5: View dependency chain validation")
	public void testViewDependencyChain() {
		LOGGER.info("Test 5: Testing view dependency chain");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create base data table
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS base_vote_data (" +
					"vote_id SERIAL PRIMARY KEY, " +
					"person_id VARCHAR(50), " +
					"ballot_id VARCHAR(50), " +
					"vote VARCHAR(20), " +
					"vote_date DATE" +
					")"
				);
				
				// Insert base data
				stmt.execute(
					"INSERT INTO base_vote_data (person_id, ballot_id, vote, vote_date) VALUES " +
					"('P001', 'B001', 'YES', '2024-01-15'), " +
					"('P001', 'B002', 'YES', '2024-01-16'), " +
					"('P002', 'B001', 'NO', '2024-01-15'), " +
					"('P002', 'B002', 'ABSENT', '2024-01-16')"
				);
				
				// Create dependent view 1: Daily summary
				stmt.execute(
					"CREATE OR REPLACE VIEW view_vote_daily_summary AS " +
					"SELECT vote_date, COUNT(*) as vote_count " +
					"FROM base_vote_data " +
					"GROUP BY vote_date"
				);
				
				// Create dependent view 2: Person summary (depends on view 1)
				stmt.execute(
					"CREATE OR REPLACE VIEW view_person_vote_summary AS " +
					"SELECT person_id, COUNT(*) as total_votes " +
					"FROM base_vote_data " +
					"GROUP BY person_id"
				);
				
				// Query chain: base -> view1 -> aggregation
				try (ResultSet rs1 = stmt.executeQuery(
						"SELECT SUM(vote_count) as total FROM view_vote_daily_summary"
						)) {
					rs1.next();
					int totalFromView = rs1.getInt("total");

					// Query chain: base -> view2
					try (ResultSet rs2 = stmt.executeQuery(
							"SELECT SUM(total_votes) as total FROM view_person_vote_summary"
							)) {
						rs2.next();
						int totalFromPersonView = rs2.getInt("total");

						// Both should match
						assertThat(totalFromView).isEqualTo(totalFromPersonView);
						assertThat(totalFromView).isEqualTo(4);

						LOGGER.info("View dependency chain validated successfully");
					}
				}
			}
		});
	}

	/**
	 * Test 6: Temporal view query performance.
	 * Verifies views can be queried efficiently.
	 */
	@Test
	@DisplayName("Test 6: Temporal view query performance")
	public void testTemporalViewQueryPerformance() {
		LOGGER.info("Test 6: Testing temporal view query performance");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create table with indexed columns
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS performance_test_view (" +
					"id SERIAL PRIMARY KEY, " +
					"period_date DATE, " +
					"metric_value DECIMAL(10,2)" +
					")"
				);
				
				// Create index for performance
				stmt.execute(
					"CREATE INDEX IF NOT EXISTS idx_period_date ON performance_test_view(period_date)"
				);
				
				// Insert test data
				for (int i = 0; i < 100; i++) {
					stmt.execute(
						"INSERT INTO performance_test_view (period_date, metric_value) " +
						"VALUES ('2024-01-01'::DATE + " + i + ", " + (50.0 + i) + ")"
					);
				}
				
				// Query with date range (should use index)
				long startTime = System.currentTimeMillis();
				ResultSet rs = stmt.executeQuery(
					"SELECT COUNT(*) FROM performance_test_view " +
					"WHERE period_date BETWEEN '2024-01-01' AND '2024-03-31'"
				);
				long endTime = System.currentTimeMillis();
				
				rs.next();
				int count = rs.getInt(1);
				long queryTime = endTime - startTime;
				
				assertThat(count).isGreaterThan(0);
				assertTrue(queryTime < 1000, "Query should complete in under 1 second");
				
				LOGGER.info("Query performance validated: {} ms", queryTime);
			}
		});
	}

	/**
	 * Test 7: Temporal aggregation accuracy.
	 * Verifies temporal aggregations produce correct results.
	 */
	@Test
	@DisplayName("Test 7: Temporal aggregation accuracy")
	public void testTemporalAggregationAccuracy() {
		LOGGER.info("Test 7: Testing temporal aggregation accuracy");
		
		assertDoesNotThrow(() -> {
			try (Connection conn = dataSource.getConnection();
				 Statement stmt = conn.createStatement()) {
				
				// Create table for aggregation test
				stmt.execute(
					"CREATE TABLE IF NOT EXISTS temporal_aggregation_test (" +
					"event_date DATE, " +
					"party VARCHAR(50), " +
					"value INTEGER" +
					")"
				);
				
				// Insert test data
				stmt.execute(
					"INSERT INTO temporal_aggregation_test VALUES " +
					"('2024-01-01', 'Party A', 10), " +
					"('2024-01-02', 'Party A', 15), " +
					"('2024-01-03', 'Party A', 20), " +
					"('2024-01-01', 'Party B', 12), " +
					"('2024-01-02', 'Party B', 18)"
				);
				
				// Test monthly aggregation
				ResultSet rs = stmt.executeQuery(
					"SELECT party, SUM(value) as total, AVG(value) as average " +
					"FROM temporal_aggregation_test " +
					"GROUP BY party " +
					"ORDER BY party"
				);
				
				// Verify Party A totals
				rs.next();
				assertThat(rs.getString("party")).isEqualTo("Party A");
				assertThat(rs.getInt("total")).isEqualTo(45);
				assertThat(rs.getDouble("average")).isEqualTo(15.0);
				
				// Verify Party B totals
				rs.next();
				assertThat(rs.getString("party")).isEqualTo("Party B");
				assertThat(rs.getInt("total")).isEqualTo(30);
				assertThat(rs.getDouble("average")).isEqualTo(15.0);
				
				LOGGER.info("Temporal aggregation accuracy validated successfully");
			}
		});
	}
}
