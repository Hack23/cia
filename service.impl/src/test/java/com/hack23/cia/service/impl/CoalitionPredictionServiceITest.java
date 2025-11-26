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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.impl.CoalitionPredictionService.CoalitionScenario;

/**
 * Integration test for coalition prediction service.
 */
public final class CoalitionPredictionServiceITest extends AbstractServiceFunctionalIntegrationTest {

	@Autowired
	private CoalitionPredictionService coalitionPredictionService;

	/**
	 * Test predict coalitions success.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPredictCoalitionsSuccess() throws Exception {
		setAuthenticatedAdminuser();
		
		final List<CoalitionScenario> scenarios = coalitionPredictionService.predictCoalitions("2023/24");
		
		assertNotNull("Coalition scenarios should not be null", scenarios);
		assertFalse("Should return at least one coalition scenario", scenarios.isEmpty());
		assertTrue("Should return at most 10 scenarios", scenarios.size() <= 10);
		
		// Verify scenarios are sorted by probability
		for (int i = 0; i < scenarios.size() - 1; i++) {
			assertTrue("Scenarios should be sorted by probability descending",
				scenarios.get(i).getProbability() >= scenarios.get(i + 1).getProbability());
		}
		
		// Verify first scenario has required fields
		final CoalitionScenario firstScenario = scenarios.get(0);
		assertNotNull("Coalition parties should not be null", firstScenario.getParties());
		assertTrue("Coalition should have at least 2 parties", firstScenario.getParties().size() >= 2);
		assertTrue("Coalition should have at most 4 parties", firstScenario.getParties().size() <= 4);
		assertTrue("Coalition should have majority seats (175+)", firstScenario.getTotalSeats() >= 175);
		assertTrue("Probability should be between 0 and 1", 
			firstScenario.getProbability() >= 0.0 && firstScenario.getProbability() <= 1.0);
		assertTrue("Stability index should be between 0 and 100",
			firstScenario.getStabilityIndex() >= 0 && firstScenario.getStabilityIndex() <= 100);
		assertNotNull("Coalition type should not be null", firstScenario.getCoalitionType());
		assertNotNull("Bloc relationship should not be null", firstScenario.getBlocRelationship());
	}

	/**
	 * Test get alignment matrix success.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetAlignmentMatrixSuccess() throws Exception {
		setAuthenticatedAdminuser();
		
		final Map<String, Map<String, Double>> alignmentMatrix = 
			coalitionPredictionService.getAlignmentMatrix("2023/24");
		
		assertNotNull("Alignment matrix should not be null", alignmentMatrix);
		assertFalse("Alignment matrix should not be empty", alignmentMatrix.isEmpty());
		
		// Verify matrix structure
		for (final Map.Entry<String, Map<String, Double>> entry : alignmentMatrix.entrySet()) {
			final String party = entry.getKey();
			final Map<String, Double> alignments = entry.getValue();
			
			assertNotNull("Party alignments should not be null", alignments);
			
			// Check self-alignment is 1.0
			if (alignments.containsKey(party)) {
				assertEquals("Self-alignment should be 1.0", 1.0, alignments.get(party), 0.001);
			}
			
			// Check all alignment values are between 0 and 1
			for (final Double alignment : alignments.values()) {
				assertTrue("Alignment should be between 0 and 1",
					alignment >= 0.0 && alignment <= 1.0);
			}
		}
	}

	/**
	 * Test calculate stability index success.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCalculateStabilityIndexSuccess() throws Exception {
		setAuthenticatedAdminuser();
		
		// Test stability for a common left-bloc coalition
		final List<String> leftBloc = Arrays.asList("S", "V", "MP");
		final int leftStability = coalitionPredictionService.calculateStabilityIndex(leftBloc, "2023/24");
		
		assertTrue("Stability index should be between 0 and 100",
			leftStability >= 0 && leftStability <= 100);
		
		// Test stability for a common right-bloc coalition
		final List<String> rightBloc = Arrays.asList("M", "KD", "L", "C");
		final int rightStability = coalitionPredictionService.calculateStabilityIndex(rightBloc, "2023/24");
		
		assertTrue("Stability index should be between 0 and 100",
			rightStability >= 0 && rightStability <= 100);
	}

	/**
	 * Test calculate stability index empty list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCalculateStabilityIndexEmptyList() throws Exception {
		setAuthenticatedAdminuser();
		
		final int stability = coalitionPredictionService.calculateStabilityIndex(Arrays.asList(), "2023/24");
		
		assertEquals("Stability should be 0 for empty list", 0, stability);
	}

	/**
	 * Test calculate stability index single party.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCalculateStabilityIndexSingleParty() throws Exception {
		setAuthenticatedAdminuser();
		
		final int stability = coalitionPredictionService.calculateStabilityIndex(
			Arrays.asList("S"), "2023/24");
		
		assertEquals("Stability should be 0 for single party", 0, stability);
	}

	/**
	 * Test calculate stability index null list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCalculateStabilityIndexNullList() throws Exception {
		setAuthenticatedAdminuser();
		
		final int stability = coalitionPredictionService.calculateStabilityIndex(null, "2023/24");
		
		assertEquals("Stability should be 0 for null list", 0, stability);
	}
}
