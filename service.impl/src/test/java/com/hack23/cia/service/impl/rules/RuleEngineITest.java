/*
 * Copyright 2010-2024 James Pether Sörling
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

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.api.action.kpi.ComplianceCheck;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class RuleEngineITest.
 */
public final class RuleEngineITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private RulesEngine rulesEngine;

	/**
	 * Rule engine test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void checkRulesComplianceTest() throws Exception {
		final List<ComplianceCheck> checkRulesCompliance = rulesEngine.checkRulesCompliance();
		assertNotNull(checkRulesCompliance);
		assertFalse(checkRulesCompliance.isEmpty());
		for (final ComplianceCheck complianceCheck : checkRulesCompliance) {
			assertNotNull(complianceCheck.toString());
		}
	}

}
