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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ViewSeasonalQuarterlyActivityITest.
 * Integration tests for seasonal trend analysis views created in db-changelog-1.55.xml.
 */
@Transactional
public class ViewSeasonalQuarterlyActivityITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Test view riksdagen seasonal quarterly activity exists.
	 * Verifies that the view can be queried successfully.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testViewRiksdagenSeasonalQuarterlyActivityExists() throws Exception {
		final Query query = entityManager.createNativeQuery(
			"SELECT year, quarter, is_election_year, total_ballots, activity_classification " +
			"FROM view_riksdagen_seasonal_quarterly_activity " +
			"WHERE year >= 2020 " +
			"ORDER BY year, quarter " +
			"LIMIT 10"
		);
		
		final List<?> resultList = query.getResultList();
		assertNotNull("View should return results", resultList);
		// Note: May be empty if no data has been loaded, but view should exist
	}

	/**
	 * Test view riksdagen Q4 election year comparison exists.
	 * Verifies that the Q4-specific view can be queried successfully.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testViewRiksdagenQ4ElectionYearComparisonExists() throws Exception {
		final Query query = entityManager.createNativeQuery(
			"SELECT year, is_election_year, q4_pattern, total_ballots, baseline_ballots " +
			"FROM view_riksdagen_q4_election_year_comparison " +
			"WHERE year >= 2020 " +
			"ORDER BY year DESC " +
			"LIMIT 10"
		);
		
		final List<?> resultList = query.getResultList();
		assertNotNull("View should return results", resultList);
		// Note: May be empty if no data has been loaded, but view should exist
	}

	/**
	 * Test view riksdagen seasonal anomaly detection exists.
	 * Verifies that the anomaly detection view can be queried successfully.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testViewRiksdagenSeasonalAnomalyDetectionExists() throws Exception {
		final Query query = entityManager.createNativeQuery(
			"SELECT year, quarter, anomaly_type, anomaly_direction, anomaly_severity, max_z_score " +
			"FROM view_riksdagen_seasonal_anomaly_detection " +
			"WHERE anomaly_severity IN ('CRITICAL', 'HIGH') " +
			"ORDER BY max_z_score DESC " +
			"LIMIT 10"
		);
		
		final List<?> resultList = query.getResultList();
		assertNotNull("View should return results", resultList);
		// Note: May be empty if no data has been loaded, but view should exist
	}

	/**
	 * Test seasonal view structure validates expected columns.
	 * Verifies that key columns exist with correct data types.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSeasonalViewStructureValidatesExpectedColumns() throws Exception {
		// Query information_schema to verify view columns exist
		final Query query = entityManager.createNativeQuery(
			"SELECT column_name, data_type " +
			"FROM information_schema.columns " +
			"WHERE table_name = 'view_riksdagen_seasonal_quarterly_activity' " +
			"AND column_name IN ('year', 'quarter', 'is_election_year', 'total_ballots', " +
			"'activity_classification', 'ballot_z_score', 'q_baseline_ballots') " +
			"ORDER BY column_name"
		);
		
		final List<?> resultList = query.getResultList();
		assertNotNull("View columns should be queryable", resultList);
		assertTrue("View should have expected columns", resultList.size() >= 5);
	}

	/**
	 * Test anomaly classification values are valid.
	 * Verifies that activity_classification contains only expected values.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testAnomalyClassificationValuesAreValid() throws Exception {
		final Query query = entityManager.createNativeQuery(
			"SELECT DISTINCT activity_classification " +
			"FROM view_riksdagen_seasonal_quarterly_activity " +
			"WHERE activity_classification IS NOT NULL"
		);
		
		final List<?> resultList = query.getResultList();
		assertNotNull("Classification values should be queryable", resultList);
		
		// If there is data, verify classification values are from expected set
		if (!resultList.isEmpty()) {
			for (Object classification : resultList) {
				final String value = classification.toString();
				assertTrue("Classification should be valid: " + value,
					value.equals("ANOMALY_DETECTED") || 
					value.equals("ELEVATED_ACTIVITY") || 
					value.equals("REDUCED_ACTIVITY") || 
					value.equals("NORMAL_ACTIVITY"));
			}
		}
	}

	/**
	 * Test Q4 pattern values are valid.
	 * Verifies that q4_pattern contains only expected values.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testQ4PatternValuesAreValid() throws Exception {
		final Query query = entityManager.createNativeQuery(
			"SELECT DISTINCT q4_pattern " +
			"FROM view_riksdagen_q4_election_year_comparison " +
			"WHERE q4_pattern IS NOT NULL"
		);
		
		final List<?> resultList = query.getResultList();
		assertNotNull("Q4 pattern values should be queryable", resultList);
		
		// If there is data, verify pattern values are from expected set
		if (!resultList.isEmpty()) {
			for (Object pattern : resultList) {
				final String value = pattern.toString();
				assertTrue("Q4 pattern should be valid: " + value,
					value.equals("PRE_ELECTION_SURGE") || 
					value.equals("ELEVATED_ELECTION_ACTIVITY") || 
					value.equals("NORMAL_ELECTION_Q4") || 
					value.equals("NORMAL_Q4"));
			}
		}
	}
}
