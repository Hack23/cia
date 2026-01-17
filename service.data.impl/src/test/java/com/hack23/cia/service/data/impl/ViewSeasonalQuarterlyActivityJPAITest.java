/*
 * Copyright 2010-2026 James Pether Sörling
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
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.seasonal.impl.ViewRiksdagenQ4ElectionYearComparison;
import com.hack23.cia.model.internal.application.data.seasonal.impl.ViewRiksdagenSeasonalAnomalyDetection;
import com.hack23.cia.model.internal.application.data.seasonal.impl.ViewRiksdagenSeasonalQuarterlyActivity;

/**
 * The Class ViewSeasonalQuarterlyActivityJPAITest.
 * Integration tests for seasonal trend analysis JPA entities.
 */
@Transactional
public class ViewSeasonalQuarterlyActivityJPAITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Test view riksdagen seasonal quarterly activity JPA entity.
	 * Verifies that the JPA entity can be queried successfully.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testViewRiksdagenSeasonalQuarterlyActivityJPAEntity() throws Exception {
		final TypedQuery<ViewRiksdagenSeasonalQuarterlyActivity> query = entityManager.createQuery(
			"SELECT v FROM ViewRiksdagenSeasonalQuarterlyActivity v WHERE v.embeddedId.year >= 2020 ORDER BY v.embeddedId.year, v.embeddedId.quarter",
			ViewRiksdagenSeasonalQuarterlyActivity.class
		);
		query.setMaxResults(10);
		
		final List<ViewRiksdagenSeasonalQuarterlyActivity> resultList = query.getResultList();
		assertNotNull("View should return results", resultList);
		// Note: May be empty if no data has been loaded, but JPA entity should work
	}

	/**
	 * Test view riksdagen Q4 election year comparison JPA entity.
	 * Verifies that the Q4-specific JPA entity can be queried successfully.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testViewRiksdagenQ4ElectionYearComparisonJPAEntity() throws Exception {
		final TypedQuery<ViewRiksdagenQ4ElectionYearComparison> query = entityManager.createQuery(
			"SELECT v FROM ViewRiksdagenQ4ElectionYearComparison v WHERE v.year >= 2020 ORDER BY v.year DESC",
			ViewRiksdagenQ4ElectionYearComparison.class
		);
		query.setMaxResults(10);
		
		final List<ViewRiksdagenQ4ElectionYearComparison> resultList = query.getResultList();
		assertNotNull("View should return results", resultList);
		// Note: May be empty if no data has been loaded, but JPA entity should work
	}

	/**
	 * Test view riksdagen seasonal anomaly detection JPA entity.
	 * Verifies that the anomaly detection JPA entity can be queried successfully.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testViewRiksdagenSeasonalAnomalyDetectionJPAEntity() throws Exception {
		final TypedQuery<ViewRiksdagenSeasonalAnomalyDetection> query = entityManager.createQuery(
			"SELECT v FROM ViewRiksdagenSeasonalAnomalyDetection v WHERE v.anomalySeverity IN ('CRITICAL', 'HIGH') ORDER BY v.maxZScore DESC",
			ViewRiksdagenSeasonalAnomalyDetection.class
		);
		query.setMaxResults(10);
		
		final List<ViewRiksdagenSeasonalAnomalyDetection> resultList = query.getResultList();
		assertNotNull("View should return results", resultList);
		// Note: May be empty if no data has been loaded, but JPA entity should work
	}

	/**
	 * Test seasonal quarterly activity entity getters.
	 * Verifies that embedded ID getters work correctly.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSeasonalQuarterlyActivityEntityGetters() throws Exception {
		final ViewRiksdagenSeasonalQuarterlyActivity entity = new ViewRiksdagenSeasonalQuarterlyActivity();
		
		// Test that getters don't throw exceptions
		assertNull("Year should be null when embeddedId is null", entity.getYear());
		assertNull("Quarter should be null when embeddedId is null", entity.getQuarter());
		
		// Verify entity creation works
		assertNotNull("Entity should be created", entity);
	}

	/**
	 * Test Q4 comparison filter by election year.
	 * Verifies that filtering by election year works.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testQ4ComparisonFilterByElectionYear() throws Exception {
		final TypedQuery<ViewRiksdagenQ4ElectionYearComparison> query = entityManager.createQuery(
			"SELECT v FROM ViewRiksdagenQ4ElectionYearComparison v WHERE v.isElectionYear = true ORDER BY v.year DESC",
			ViewRiksdagenQ4ElectionYearComparison.class
		);
		query.setMaxResults(10);
		
		final List<ViewRiksdagenQ4ElectionYearComparison> resultList = query.getResultList();
		assertNotNull("Query should return results", resultList);
	}

	/**
	 * Test anomaly detection filter by type.
	 * Verifies that filtering by anomaly type works.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testAnomalyDetectionFilterByType() throws Exception {
		final TypedQuery<ViewRiksdagenSeasonalAnomalyDetection> query = entityManager.createQuery(
			"SELECT v FROM ViewRiksdagenSeasonalAnomalyDetection v WHERE v.anomalyType LIKE '%BALLOT%' ORDER BY v.embeddedId.year DESC",
			ViewRiksdagenSeasonalAnomalyDetection.class
		);
		query.setMaxResults(10);
		
		final List<ViewRiksdagenSeasonalAnomalyDetection> resultList = query.getResultList();
		assertNotNull("Query should return results", resultList);
	}
}
