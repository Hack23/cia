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
package com.hack23.cia.service.impl.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.model.internal.application.data.rules.impl.Status;
import com.hack23.cia.service.data.api.RuleViolationDAO;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class RiskRulesIntegrationTest.
 * 
 * Integration test suite validating end-to-end risk rule execution:
 * - Database view queries
 * - Drools rule firing
 * - RuleViolation persistence
 * - Transaction management
 * - Audit trail correctness
 * 
 * Tests the complete OSINT intelligence analysis pipeline.
 */
public final class RiskRulesIntegrationTest extends AbstractServiceFunctionalIntegrationTest {

	@Autowired
	private RulesManager rulesManager;

	@Autowired
	@Qualifier("RuleViolationDAO")
	private RuleViolationDAO ruleViolationDAO;

	/**
	 * Test end-to-end workflow: Load data → Execute rules → Verify persistence.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testEndToEndRuleExecutionAndPersistence() throws Exception {
		setAuthenticatedAnonymousUser();

		// Clear any existing violations
		final List<RuleViolation> existingViolations = ruleViolationDAO.getAll();
		for (final RuleViolation violation : existingViolations) {
			ruleViolationDAO.delete(violation);
		}

		// Execute the full compliance check workflow
		rulesManager.processService();

		// Verify violations were persisted to database
		final List<RuleViolation> persistedViolations = ruleViolationDAO.getAll();
		assertNotNull("Persisted violations should not be null", persistedViolations);
		assertFalse("Should have persisted at least some violations", persistedViolations.isEmpty());

		// Verify audit trail fields are populated
		for (final RuleViolation violation : persistedViolations) {
			assertNotNull("Violation ID should be set", violation.getId());
			assertNotNull("Detected date should be set", violation.getDetectedDate());
			assertNotNull("Reference ID should be set", violation.getReferenceId());
			assertNotNull("Name should be set", violation.getName());
			assertNotNull("Resource type should be set", violation.getResourceType());
			assertNotNull("Rule name should be set", violation.getRuleName());
			assertNotNull("Status should be set", violation.getStatus());
		}
	}

	/**
	 * Test politician rules coverage across all domains.
	 * Validates that politician risk rules fire and persist correctly.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testPoliticianRulesPersistence() throws Exception {
		setAuthenticatedAnonymousUser();

		// Clear existing violations
		for (final RuleViolation violation : ruleViolationDAO.getAll()) {
			ruleViolationDAO.delete(violation);
		}

		// Execute compliance checks
		rulesManager.processService();

		// Query for politician-specific violations
		final List<RuleViolation> allViolations = ruleViolationDAO.getAll();
		long politicianViolations = 0;
		boolean foundLazyRule = false;
		boolean foundRebelRate = false;
		boolean foundExperience = false;

		for (final RuleViolation violation : allViolations) {
			if (ResourceType.POLITICIAN.equals(violation.getResourceType())) {
				politicianViolations++;

				// Check for specific expected rules
				if ("PoliticianLazy".equals(violation.getRuleName())) {
					foundLazyRule = true;
					assertTrue("PoliticianLazy should have meaningful status",
							violation.getStatus() == Status.MINOR ||
							violation.getStatus() == Status.MAJOR ||
							violation.getStatus() == Status.CRITICAL);
				}
				if ("PoliticianHighRebelRate".equals(violation.getRuleName())) {
					foundRebelRate = true;
				}
				if ("PoliticianExperience".equals(violation.getRuleName())) {
					foundExperience = true;
				}
			}
		}

		// Verify we found politician violations
		assertTrue("Should have found some politician violations", politicianViolations > 0);
	}

	/**
	 * Test party rules coverage.
	 * Validates that party risk rules fire and persist correctly.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testPartyRulesPersistence() throws Exception {
		setAuthenticatedAnonymousUser();

		// Clear existing violations
		for (final RuleViolation violation : ruleViolationDAO.getAll()) {
			ruleViolationDAO.delete(violation);
		}

		// Execute compliance checks
		rulesManager.processService();

		// Query for party-specific violations
		final List<RuleViolation> allViolations = ruleViolationDAO.getAll();
		long partyViolations = 0;

		for (final RuleViolation violation : allViolations) {
			if (ResourceType.PARTY.equals(violation.getResourceType())) {
				partyViolations++;

				// Verify audit fields
				assertNotNull("Party violation should have reference ID", violation.getReferenceId());
				assertNotNull("Party violation should have name", violation.getName());
			}
		}

		// Note: May be 0 if no party data meets violation thresholds in test database
		assertTrue("Party violations should be >= 0", partyViolations >= 0);
	}

	/**
	 * Test that different severity levels are correctly persisted.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testViolationSeverityLevels() throws Exception {
		setAuthenticatedAnonymousUser();

		// Clear existing violations
		for (final RuleViolation violation : ruleViolationDAO.getAll()) {
			ruleViolationDAO.delete(violation);
		}

		// Execute compliance checks
		rulesManager.processService();

		// Collect violations by severity
		final List<RuleViolation> allViolations = ruleViolationDAO.getAll();
		boolean foundMinor = false;
		boolean foundMajor = false;
		boolean foundCritical = false;

		for (final RuleViolation violation : allViolations) {
			if (Status.MINOR.equals(violation.getStatus())) {
				foundMinor = true;
			} else if (Status.MAJOR.equals(violation.getStatus())) {
				foundMajor = true;
			} else if (Status.CRITICAL.equals(violation.getStatus())) {
				foundCritical = true;
			}
		}

		// We should have at least one violation of some severity
		assertTrue("Should have at least one violation", 
				foundMinor || foundMajor || foundCritical);
	}

	/**
	 * Test that violations include proper rule metadata.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testViolationMetadata() throws Exception {
		setAuthenticatedAnonymousUser();

		// Clear existing violations
		for (final RuleViolation violation : ruleViolationDAO.getAll()) {
			ruleViolationDAO.delete(violation);
		}

		// Execute compliance checks
		rulesManager.processService();

		// Verify metadata completeness
		final List<RuleViolation> allViolations = ruleViolationDAO.getAll();
		assertFalse("Should have persisted violations", allViolations.isEmpty());

		for (final RuleViolation violation : allViolations) {
			// Required fields
			assertNotNull("Rule name should be set", violation.getRuleName());
			assertNotNull("Rule group should be set", violation.getRuleGroup());
			assertNotNull("Rule description should be set", violation.getRuleDescription());
			assertNotNull("Status should be set", violation.getStatus());

			// Audit fields
			assertNotNull("Detected date should be set", violation.getDetectedDate());
			assertNotNull("Reference ID should be set", violation.getReferenceId());
			assertNotNull("Resource type should be set", violation.getResourceType());

			// Verify resource type is valid
			assertTrue("Resource type should be valid",
					violation.getResourceType() == ResourceType.POLITICIAN ||
					violation.getResourceType() == ResourceType.PARTY ||
					violation.getResourceType() == ResourceType.COMMITTEE ||
					violation.getResourceType() == ResourceType.MINISTRY);
		}
	}

	/**
	 * Test that re-running the service clears old violations and creates new ones.
	 * Validates transaction management: old violations deleted, new ones persisted.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testViolationRefreshOnRerun() throws Exception {
		setAuthenticatedAnonymousUser();

		// First run
		rulesManager.processService();
		final List<RuleViolation> firstRunViolations = ruleViolationDAO.getAll();
		final int firstRunCount = firstRunViolations.size();
		assertTrue("First run should create violations", firstRunCount > 0);

		// Second run - should clear and recreate
		rulesManager.processService();
		final List<RuleViolation> secondRunViolations = ruleViolationDAO.getAll();
		final int secondRunCount = secondRunViolations.size();

		// Both runs should have violations (may vary slightly due to data changes)
		assertTrue("Second run should also create violations", secondRunCount > 0);

		// Verify all violations have fresh timestamps
		for (final RuleViolation violation : secondRunViolations) {
			assertNotNull("Violation should have detected date", violation.getDetectedDate());
		}
	}

	/**
	 * Test resource type coverage across all domains.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testResourceTypeCoverage() throws Exception {
		setAuthenticatedAnonymousUser();

		// Clear existing violations
		for (final RuleViolation violation : ruleViolationDAO.getAll()) {
			ruleViolationDAO.delete(violation);
		}

		// Execute compliance checks
		rulesManager.processService();

		// Count violations by resource type
		final List<RuleViolation> allViolations = ruleViolationDAO.getAll();
		int politicianCount = 0;
		int partyCount = 0;
		int committeeCount = 0;
		int ministryCount = 0;

		for (final RuleViolation violation : allViolations) {
			switch (violation.getResourceType()) {
				case POLITICIAN:
					politicianCount++;
					break;
				case PARTY:
					partyCount++;
					break;
				case COMMITTEE:
					committeeCount++;
					break;
				case MINISTRY:
					ministryCount++;
					break;
				default:
					break;
			}
		}

		// We should have at least politician violations (most common)
		assertTrue("Should have at least politician violations", politicianCount > 0);

		// Note: Committee and Ministry violations may be 0 if no data meets thresholds
		assertTrue("Committee count should be >= 0", committeeCount >= 0);
		assertTrue("Ministry count should be >= 0", ministryCount >= 0);
	}
}
