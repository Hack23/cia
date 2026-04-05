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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.model.internal.application.data.rules.impl.Status;

/**
 * Unit test for BruteForceAttack Drools rules.
 *
 * Validates brute force detection rules fire correctly at
 * MINOR (5+), MAJOR (10+), and CRITICAL (20+) thresholds.
 */
public class BruteForceAttackRulesTest {

	/** The kie session. */
	private KieSession kieSession;

	/** The kie container. */
	private KieContainer kieContainer;

	/**
	 * Sets up.
	 */
	@Before
	public void setUp() {
		final KieServices kieServices = KieServices.Factory.get();
		final KieFileSystem kfs = kieServices.newKieFileSystem();
		kfs.write(ResourceFactory.newClassPathResource(
				"com/hack23/cia/service/impl/rules/application/BruteForceAttack.drl"));
		final KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
		final Results results = kieBuilder.getResults();
		assertFalse("DRL compilation should have no errors: " + results.getMessages(),
				results.hasMessages(Message.Level.ERROR));
		kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		kieSession = kieContainer.newKieSession();
		assertNotNull("KieSession should be available", kieSession);
	}

	/**
	 * Tears down.
	 */
	@After
	public void tearDown() {
		if (kieSession != null) {
			kieSession.dispose();
		}
	}

	/**
	 * Test no violation below threshold.
	 * Sessions with fewer than 5 failed attempts should not trigger any rule.
	 */
	@Test
	public void testNoViolationBelowThreshold() {
		final ApplicationComplianceCheckImpl check = new ApplicationComplianceCheckImpl(
				"session-1", "192.168.1.1", 4);

		kieSession.insert(check);
		kieSession.fireAllRules();

		final List<RuleViolation> violations = check.getRuleViolations();
		assertTrue("Should have no violations for 4 failed attempts", violations.isEmpty());
	}

	/**
	 * Test minor threshold fires at 5 failed attempts.
	 */
	@Test
	public void testMinorThresholdFires() {
		final ApplicationComplianceCheckImpl check = new ApplicationComplianceCheckImpl(
				"session-2", "192.168.1.2", 5);

		kieSession.insert(check);
		kieSession.fireAllRules();

		final List<RuleViolation> violations = check.getRuleViolations();
		assertFalse("Should have violations for 5 failed attempts", violations.isEmpty());

		final RuleViolation violation = violations.get(0);
		assertEquals("Status should be MINOR", Status.MINOR, violation.getStatus());
		assertEquals("Rule name should be BruteForceAttack", "BruteForceAttack", violation.getRuleName());
		assertEquals("Resource type should be APPLICATION", ResourceType.APPLICATION, violation.getResourceType());
	}

	/**
	 * Test major threshold fires at 10 failed attempts.
	 */
	@Test
	public void testMajorThresholdFires() {
		final ApplicationComplianceCheckImpl check = new ApplicationComplianceCheckImpl(
				"session-3", "10.0.0.1", 10);

		kieSession.insert(check);
		kieSession.fireAllRules();

		final List<RuleViolation> violations = check.getRuleViolations();
		assertFalse("Should have violations for 10 failed attempts", violations.isEmpty());

		final RuleViolation violation = violations.get(0);
		assertEquals("Status should be MAJOR", Status.MAJOR, violation.getStatus());
		assertEquals("Rule name should be BruteForceAttack", "BruteForceAttack", violation.getRuleName());
	}

	/**
	 * Test critical threshold fires at 20 failed attempts.
	 */
	@Test
	public void testCriticalThresholdFires() {
		final ApplicationComplianceCheckImpl check = new ApplicationComplianceCheckImpl(
				"session-4", "172.16.0.1", 20);

		kieSession.insert(check);
		kieSession.fireAllRules();

		final List<RuleViolation> violations = check.getRuleViolations();
		assertFalse("Should have violations for 20 failed attempts", violations.isEmpty());

		final RuleViolation violation = violations.get(0);
		assertEquals("Status should be CRITICAL", Status.CRITICAL, violation.getStatus());
		assertEquals("Rule name should be BruteForceAttack", "BruteForceAttack", violation.getRuleName());
	}

	/**
	 * Test critical threshold fires at extreme count.
	 */
	@Test
	public void testCriticalThresholdAtExtremeCount() {
		final ApplicationComplianceCheckImpl check = new ApplicationComplianceCheckImpl(
				"session-5", "10.10.10.10", 100);

		kieSession.insert(check);
		kieSession.fireAllRules();

		final List<RuleViolation> violations = check.getRuleViolations();
		assertFalse("Should have violations for 100 failed attempts", violations.isEmpty());

		final RuleViolation violation = violations.get(0);
		assertEquals("Status should be CRITICAL for extreme count", Status.CRITICAL, violation.getStatus());
	}

	/**
	 * Test only critical rule fires for 25 attempts.
	 * With non-overlapping conditions (5-9, 10-19, 20+), exactly one
	 * rule matches for 25 attempts, producing a single CRITICAL violation.
	 */
	@Test
	public void testOnlyCriticalRuleFires() {
		final ApplicationComplianceCheckImpl check = new ApplicationComplianceCheckImpl(
				"session-6", "192.168.0.100", 25);

		kieSession.insert(check);
		kieSession.fireAllRules();

		final List<RuleViolation> violations = check.getRuleViolations();
		assertFalse("Should have violations", violations.isEmpty());
		assertEquals("Should have exactly 1 violation (only CRITICAL matches)", 1, violations.size());

		final RuleViolation violation = violations.get(0);
		assertEquals("Should be CRITICAL for 25 attempts", Status.CRITICAL, violation.getStatus());
	}

	/**
	 * Test boundary at minor threshold.
	 * Exactly 5 should trigger MINOR, 4 should not.
	 */
	@Test
	public void testBoundaryAtMinorThreshold() {
		final ApplicationComplianceCheckImpl checkBelow = new ApplicationComplianceCheckImpl(
				"session-boundary-below", "10.0.0.1", 4);

		kieSession.insert(checkBelow);
		kieSession.fireAllRules();

		assertTrue("4 attempts should have no violations", checkBelow.getRuleViolations().isEmpty());
	}

	/**
	 * Test boundary at minor threshold - at threshold.
	 */
	@Test
	public void testBoundaryAtMinorThresholdAtValue() {
		final ApplicationComplianceCheckImpl checkAt = new ApplicationComplianceCheckImpl(
				"session-boundary-at", "10.0.0.2", 5);

		kieSession.insert(checkAt);
		kieSession.fireAllRules();

		assertFalse("5 attempts should trigger violation", checkAt.getRuleViolations().isEmpty());
		assertEquals("5 attempts should be MINOR", Status.MINOR, checkAt.getRuleViolations().get(0).getStatus());
	}

	/**
	 * Test compliance check impl properties.
	 */
	@Test
	public void testApplicationComplianceCheckImplProperties() {
		final ApplicationComplianceCheckImpl check = new ApplicationComplianceCheckImpl(
				"test-session", "127.0.0.1", 15);

		assertEquals("Session ID should match", "test-session", check.getSessionId());
		assertEquals("IP should match", "127.0.0.1", check.getIpInformation());
		assertEquals("Failed attempts should match", 15, check.getFailedAuthenticationAttempts());
		assertEquals("ID should be session ID", "test-session", check.getId());
		assertNotNull("Name should not be null", check.getName());
		assertEquals("Resource type should be APPLICATION", ResourceType.APPLICATION, check.getResourceType());
	}

}
