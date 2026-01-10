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

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryAnnual;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryMonthly;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.model.internal.application.data.rules.impl.Status;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class DroolsRulesValidationTest.
 * 
 * Validates all Drools risk rules for:
 * - Syntax correctness (compilation)
 * - Rule firing on expected inputs  
 * - Salience ordering (10=MINOR, 50=MAJOR, 100+=CRITICAL)
 * - Resource tagging accuracy
 * - ComplianceCheck integration
 * 
 * Note: Integration test requiring database and full Spring context.
 */
public final class DroolsRulesValidationTest extends AbstractServiceFunctionalIntegrationTest {

	@Autowired
	private KieContainer kieContainer;

	/**
	 * Test that all DRL files compile without errors.
	 * Validates syntax correctness of all 45+ rules.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testAllRulesCompileSuccessfully() throws Exception {
		setAuthenticatedAnonymousUser();
		
		assertNotNull("KieContainer should be available", kieContainer);
		
		final KieBase kieBase = kieContainer.getKieBase();
		assertNotNull("KieBase should be available", kieBase);
		
		final Collection<KiePackage> kiePackages = kieBase.getKiePackages();
		assertNotNull("KiePackages should be available", kiePackages);
		assertFalse("Should have at least one package", kiePackages.isEmpty());
		
		int totalRuleCount = 0;
		for (final KiePackage kp : kiePackages) {
			final Collection<Rule> rules = kp.getRules();
			totalRuleCount += rules.size();
			for (final Rule rule : rules) {
				assertNotNull("Rule should have a name", rule.getName());
				assertNotNull("Rule should have a package", rule.getPackageName());
			}
		}
		
		// Verify we have loaded a substantial number of rules (45+ expected)
		assertTrue("Should have loaded at least 40 rules, found: " + totalRuleCount, totalRuleCount >= 40);
	}

	/**
	 * Test Politician rules fire correctly with test data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPoliticianRulesFire() throws Exception {
		setAuthenticatedAnonymousUser();
		
		final KieSession kieSession = kieContainer.newKieSession();
		
		try {
			// Test PoliticianLazy - CRITICAL level (30%+ absence)
			final ViewRiksdagenPolitician politician = createTestPolitician();
			final ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual annualSummary = 
				new ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual();
			annualSummary.setPoliticianPercentageAbsent(new BigDecimal("35.0"));
			annualSummary.setRebelPercentage(new BigDecimal("5.0"));
			
			final PoliticianComplianceCheckImpl check = new PoliticianComplianceCheckImpl(
				politician, null, null, annualSummary);
			
			kieSession.insert(check);
			final int firedRules = kieSession.fireAllRules();
			
			assertTrue("At least one rule should fire", firedRules > 0);
			
			final List<RuleViolation> violations = check.getRuleViolations();
			assertFalse("Should have violations", violations.isEmpty());
			
			boolean foundLazy = false;
			boolean foundRebelRate = false;
			
			for (final RuleViolation violation : violations) {
				if ("PoliticianLazy".equals(violation.getRuleName())) {
					assertEquals("Should be CRITICAL status", Status.CRITICAL, violation.getStatus());
					assertEquals("Should be POLITICIAN resource", ResourceType.POLITICIAN, violation.getResourceType());
					foundLazy = true;
				}
				if ("PoliticianHighRebelRate".equals(violation.getRuleName())) {
					assertEquals("Should be MINOR status for 5% rebel rate", Status.MINOR, violation.getStatus());
					foundRebelRate = true;
				}
			}
			
			assertTrue("Should find PoliticianLazy violation", foundLazy);
			assertTrue("Should find PoliticianHighRebelRate violation", foundRebelRate);
		} finally {
			kieSession.dispose();
		}
	}

	/**
	 * Test Party rules fire correctly with test data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPartyRulesFire() throws Exception {
		setAuthenticatedAnonymousUser();
		
		final KieSession kieSession = kieContainer.newKieSession();
		
		try {
			final ViewRiksdagenPartySummary party = createTestParty();
			final ViewRiksdagenVoteDataBallotPartySummaryAnnual annualSummary = 
				new ViewRiksdagenVoteDataBallotPartySummaryAnnual();
			annualSummary.setPartyPercentageAbsent(new BigDecimal("16.5"));
			
			final PartyComplianceCheckImpl check = new PartyComplianceCheckImpl(
				party, annualSummary, null, null);
			
			kieSession.insert(check);
			final int firedRules = kieSession.fireAllRules();
			
			assertTrue("At least one rule should fire", firedRules > 0);
			
			final List<RuleViolation> violations = check.getRuleViolations();
			assertFalse("Should have violations", violations.isEmpty());
			
			boolean foundPartyLazy = false;
			for (final RuleViolation violation : violations) {
				if ("PartyLazy".equals(violation.getRuleName())) {
					assertEquals("Should be CRITICAL status", Status.CRITICAL, violation.getStatus());
					assertEquals("Should be PARTY resource", ResourceType.PARTY, violation.getResourceType());
					foundPartyLazy = true;
				}
			}
			
			assertTrue("Should find PartyLazy violation", foundPartyLazy);
		} finally {
			kieSession.dispose();
		}
	}

	/**
	 * Test salience ordering - higher salience rules override lower.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSalienceOrdering() throws Exception {
		setAuthenticatedAnonymousUser();
		
		final KieSession kieSession = kieContainer.newKieSession();
		
		try {
			final ViewRiksdagenPolitician politician = createTestPolitician();
			
			// Create data that matches multiple severity levels
			final ViewRiksdagenVoteDataBallotPoliticianSummaryDaily dailySummary = 
				new ViewRiksdagenVoteDataBallotPoliticianSummaryDaily();
			dailySummary.setPoliticianPercentageAbsent(new BigDecimal("100"));
			
			final ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly monthlySummary = 
				new ViewRiksdagenVoteDataBallotPoliticianSummaryMonthly();
			monthlySummary.setPoliticianPercentageAbsent(new BigDecimal("25.0"));
			
			final ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual annualSummary = 
				new ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual();
			annualSummary.setPoliticianPercentageAbsent(new BigDecimal("35.0"));
			annualSummary.setRebelPercentage(new BigDecimal("0"));
			
			final PoliticianComplianceCheckImpl check = new PoliticianComplianceCheckImpl(
				politician, dailySummary, monthlySummary, annualSummary);
			
			kieSession.insert(check);
			kieSession.fireAllRules();
			
			final List<RuleViolation> violations = check.getRuleViolations();
			
			// The highest severity rule should win due to salience ordering
			for (final RuleViolation violation : violations) {
				if ("PoliticianLazy".equals(violation.getRuleName())) {
					assertEquals("Should be CRITICAL status from highest salience rule", 
						Status.CRITICAL, violation.getStatus());
					assertEquals("Should have ExtremeAbsenteeism tag", 
						"ExtremeAbsenteeism", violation.getPositive());
				}
			}
		} finally {
			kieSession.dispose();
		}
	}

	/**
	 * Test no violations for inactive politician.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testNoViolationsForInactivePolitician() throws Exception {
		setAuthenticatedAnonymousUser();
		
		final KieSession kieSession = kieContainer.newKieSession();
		
		try {
			final ViewRiksdagenPolitician politician = createTestPolitician();
			politician.setActiveParliament(false);
			
			final ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual annualSummary = 
				new ViewRiksdagenVoteDataBallotPoliticianSummaryAnnual();
			annualSummary.setPoliticianPercentageAbsent(new BigDecimal("50.0"));
			annualSummary.setRebelPercentage(new BigDecimal("30.0"));
			
			final PoliticianComplianceCheckImpl check = new PoliticianComplianceCheckImpl(
				politician, null, null, annualSummary);
			
			kieSession.insert(check);
			kieSession.fireAllRules();
			
			final List<RuleViolation> violations = check.getRuleViolations();
			
			assertTrue("Inactive politician should have no violations", violations.isEmpty());
		} finally {
			kieSession.dispose();
		}
	}

	/**
	 * Test new collaboration thresholds - MINOR level (below 1%).
	 * Validates recalibrated threshold based on P90 = 1.3%.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPoliticianExtremelyLowCollaboration() throws Exception {
		setAuthenticatedAnonymousUser();
		
		final KieSession kieSession = kieContainer.newKieSession();
		
		try {
			final ViewRiksdagenPolitician politician = createTestPolitician();
			politician.setActiveParliament(true);
			politician.setTotalDocuments(15L);
			politician.setCollaborationPercentage(new BigDecimal("0.5"));
			
			final PoliticianComplianceCheckImpl check = new PoliticianComplianceCheckImpl(
				politician, null, null, null);
			
			kieSession.insert(check);
			final int firedRules = kieSession.fireAllRules();
			
			assertTrue("At least one rule should fire", firedRules > 0);
			
			final List<RuleViolation> violations = check.getRuleViolations();
			assertFalse("Should have violations", violations.isEmpty());
			
			boolean foundExtremelyLowCollab = false;
			for (final RuleViolation violation : violations) {
				if ("PoliticianIsolatedBehavior".equals(violation.getRuleName()) 
					&& "ExtremelyLowCollaboration".equals(violation.getPositive())) {
					assertEquals("Should be MINOR status for <1% collaboration", 
						Status.MINOR, violation.getStatus());
					foundExtremelyLowCollab = true;
				}
			}
			
			assertTrue("Should find ExtremelyLowCollaboration violation", foundExtremelyLowCollab);
		} finally {
			kieSession.dispose();
		}
	}

	/**
	 * Test new collaboration thresholds - MAJOR level (zero collaboration).
	 * Validates threshold based on empirical data showing 84.4% have 0%.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPoliticianZeroCollaboration() throws Exception {
		setAuthenticatedAnonymousUser();
		
		final KieSession kieSession = kieContainer.newKieSession();
		
		try {
			final ViewRiksdagenPolitician politician = createTestPolitician();
			politician.setActiveParliament(true);
			politician.setTotalDocuments(25L);
			politician.setCollaborationPercentage(new BigDecimal("0.0"));
			
			final PoliticianComplianceCheckImpl check = new PoliticianComplianceCheckImpl(
				politician, null, null, null);
			
			kieSession.insert(check);
			final int firedRules = kieSession.fireAllRules();
			
			assertTrue("At least one rule should fire", firedRules > 0);
			
			final List<RuleViolation> violations = check.getRuleViolations();
			assertFalse("Should have violations", violations.isEmpty());
			
			boolean foundZeroCollab = false;
			for (final RuleViolation violation : violations) {
				if ("PoliticianIsolatedBehavior".equals(violation.getRuleName()) 
					&& "ZeroCollaboration".equals(violation.getPositive())) {
					assertEquals("Should be MAJOR status for 0% collaboration", 
						Status.MAJOR, violation.getStatus());
					foundZeroCollab = true;
				}
			}
			
			assertTrue("Should find ZeroCollaboration violation", foundZeroCollab);
		} finally {
			kieSession.dispose();
		}
	}

	/**
	 * Test collaboration threshold edge case - should NOT trigger MAJOR with less than 20 documents.
	 * Documents threshold prevents false positives.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPoliticianZeroCollaborationBelowDocumentThreshold() throws Exception {
		setAuthenticatedAnonymousUser();
		
		final KieSession kieSession = kieContainer.newKieSession();
		
		try {
			final ViewRiksdagenPolitician politician = createTestPolitician();
			politician.setActiveParliament(true);
			politician.setTotalDocuments(15L);  // Below 20 threshold
			politician.setCollaborationPercentage(new BigDecimal("0.0"));
			
			final PoliticianComplianceCheckImpl check = new PoliticianComplianceCheckImpl(
				politician, null, null, null);
			
			kieSession.insert(check);
			kieSession.fireAllRules();
			
			final List<RuleViolation> violations = check.getRuleViolations();
			
			boolean foundZeroCollab = false;
			for (final RuleViolation violation : violations) {
				if ("PoliticianIsolatedBehavior".equals(violation.getRuleName()) 
					&& "ZeroCollaboration".equals(violation.getPositive())) {
					foundZeroCollab = true;
				}
			}
			
			assertFalse("Should NOT find ZeroCollaboration violation with <20 documents", foundZeroCollab);
		} finally {
			kieSession.dispose();
		}
	}

	/**
	 * Test collaboration threshold boundary - exactly 1.0% should NOT trigger MINOR.
	 * Validates threshold boundary condition.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPoliticianCollaborationAtThresholdBoundary() throws Exception {
		setAuthenticatedAnonymousUser();
		
		final KieSession kieSession = kieContainer.newKieSession();
		
		try {
			final ViewRiksdagenPolitician politician = createTestPolitician();
			politician.setActiveParliament(true);
			politician.setTotalDocuments(15L);
			politician.setCollaborationPercentage(new BigDecimal("1.0"));  // Exactly at boundary
			
			final PoliticianComplianceCheckImpl check = new PoliticianComplianceCheckImpl(
				politician, null, null, null);
			
			kieSession.insert(check);
			kieSession.fireAllRules();
			
			final List<RuleViolation> violations = check.getRuleViolations();
			
			boolean foundExtremelyLowCollab = false;
			for (final RuleViolation violation : violations) {
				if ("PoliticianIsolatedBehavior".equals(violation.getRuleName()) 
					&& "ExtremelyLowCollaboration".equals(violation.getPositive())) {
					foundExtremelyLowCollab = true;
				}
			}
			
			assertFalse("Should NOT trigger ExtremelyLowCollaboration at exactly 1.0%", foundExtremelyLowCollab);
		} finally {
			kieSession.dispose();
		}
	}


	// Helper methods

	private ViewRiksdagenPolitician createTestPolitician() {
		final ViewRiksdagenPolitician politician = new ViewRiksdagenPolitician();
		politician.setPersonId("test-person-id");
		politician.setFirstName("Test");
		politician.setLastName("Politician");
		politician.setParty("Test Party");
		politician.setActiveParliament(true);
		politician.setBornYear(1970);
		politician.setTotalDaysServedCommittee(1000L);
		politician.setTotalDaysServedCommitteeLeadership(100L);
		politician.setTotalDaysServedCommitteeSubstitute(50L);
		return politician;
	}

	private ViewRiksdagenPartySummary createTestParty() {
		final ViewRiksdagenPartySummary party = new ViewRiksdagenPartySummary();
		party.setParty("Test Party");
		party.setActiveParliament(true);
		party.setFirstAssignmentDate(new Date());
		party.setTotalAssignments(100L);
		return party;
	}
}
