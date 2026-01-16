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
package com.hack23.cia.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import com.hack23.cia.model.internal.application.data.ministry.impl.GovernmentBodyData;
import com.hack23.cia.service.data.api.GovernmentBodyDataDAO;
import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;

/**
 * The Class GovernmentBodyDataLoaderServiceITest.
 * Integration test for GovernmentBodyDataLoaderService.
 */
public final class GovernmentBodyDataLoaderServiceITest extends AbstractServiceFunctionalIntegrationTest {

	@Autowired
	private GovernmentBodyDataDAO governmentBodyDataDAO;

	@Autowired
	private GovernmentBodyDataLoaderService loaderService;

	@Mock
	private EsvApi mockEsvApi;

	/**
	 * Test load government body data if empty successfully loads data.
	 */
	@Test
	public void testLoadGovernmentBodyDataIfEmptySuccessfullyLoadsData() {
		assertNotNull("Expect loader service to be autowired", loaderService);
		assertNotNull("Expect DAO to be autowired", governmentBodyDataDAO);

		// Clear any existing data
		final List<GovernmentBodyData> existingData = governmentBodyDataDAO.getAll();
		if (existingData != null && !existingData.isEmpty()) {
			for (final GovernmentBodyData data : existingData) {
				governmentBodyDataDAO.delete(data);
			}
		}

		// Initialize mocks
		MockitoAnnotations.openMocks(this);

		// Create mock data
		final Map<Integer, List<GovernmentBodyAnnualSummary>> mockData = new HashMap<>();
		final List<GovernmentBodyAnnualSummary> summaries = new ArrayList<>();
		
		final GovernmentBodyAnnualSummary summary = new GovernmentBodyAnnualSummary();
		summary.setName("Test Government Body");
		summary.setConsecutiveNumber(1);
		summary.setGovermentBodyId("TEST001");
		summary.setmCode("TEST");
		summary.setMinistry("Test Ministry");
		summary.setOrgNumber("123456-7890");
		summary.setHeadCount(100);
		summary.setAnnualWorkHeadCount(95);
		summary.setVat("Ja");
		summary.setComment("Test comment");
		
		summaries.add(summary);
		mockData.put(2023, summaries);

		// Configure mock
		when(mockEsvApi.getData()).thenReturn(mockData);

		// Replace real EsvApi with mock
		ReflectionTestUtils.setField(loaderService, "esvApi", mockEsvApi);

		// Execute load
		loaderService.loadGovernmentBodyDataIfEmpty();

		// Verify data was loaded
		final List<GovernmentBodyData> loadedData = governmentBodyDataDAO.getAll();
		assertNotNull("Expect loaded data to be not null", loadedData);
		assertTrue("Expect at least one record to be loaded", loadedData.size() > 0);
	}

	/**
	 * Test load government body data if empty skips when data exists.
	 */
	@Test
	public void testLoadGovernmentBodyDataIfEmptySkipsWhenDataExists() {
		assertNotNull("Expect loader service to be autowired", loaderService);
		assertNotNull("Expect DAO to be autowired", governmentBodyDataDAO);

		// Check if data already exists
		final List<GovernmentBodyData> existingData = governmentBodyDataDAO.getAll();
		
		if (existingData == null || existingData.isEmpty()) {
			// Create test data
			final GovernmentBodyData testData = new GovernmentBodyData(
				2023,
				"Existing Government Body",
				1,
				"EXIST001",
				"EXIST",
				"Existing Ministry",
				"999999-9999",
				50,
				48,
				"Nej",
				"Existing record"
			);
			governmentBodyDataDAO.persist(testData);
		}

		// Get count before load attempt
		final int countBefore = governmentBodyDataDAO.getAll().size();

		// Initialize mocks
		MockitoAnnotations.openMocks(this);

		// Create mock data that should NOT be loaded
		final Map<Integer, List<GovernmentBodyAnnualSummary>> mockData = new HashMap<>();
		final List<GovernmentBodyAnnualSummary> summaries = new ArrayList<>();
		
		final GovernmentBodyAnnualSummary summary = new GovernmentBodyAnnualSummary();
		summary.setName("Should Not Be Loaded");
		summaries.add(summary);
		mockData.put(2024, summaries);

		when(mockEsvApi.getData()).thenReturn(mockData);
		ReflectionTestUtils.setField(loaderService, "esvApi", mockEsvApi);

		// Execute load - should skip
		loaderService.loadGovernmentBodyDataIfEmpty();

		// Verify no new data was added
		final int countAfter = governmentBodyDataDAO.getAll().size();
		assertTrue("Expect data count to remain the same when table is not empty", 
			countAfter == countBefore);
	}
}
