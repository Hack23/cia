/*
 * Copyright 2010 James Pether Sörling
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
package com.hack23.cia.service.external.vdem.impl;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.external.vdem.impl.VdemServiceImpl;

/**
 * The Class VdemServiceITest.
 */
@PerfTest(threads = 1, duration = 3000, warmUp = 1500)
@Required(max = 4000,average = 2500,percentile95=3000,throughput=2)
public class VdemServiceITest extends AbstractVdemFunctionalIntegrationTest {

	/** The i. */
	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	/** The vdem service. */
	@Autowired
	private VdemServiceImpl vdemService;

	/**
	 * Test get questions.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@PerfTest(threads = 1, duration = 15000, warmUp = 4500)
	@Required(max = 25000,average = 25000,percentile95=25000)
	public void testGetQuestions() throws Exception {

		assertEquals(510,vdemService.getQuestions().size());
	}

	/**
	 * Test get country question data.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@PerfTest(threads = 1, duration = 90000, warmUp = 4500)
	@Required(max = 90000,average = 90000,percentile95=90000)

	public void testGetCountryQuestionData() throws Exception {

		assertNotNull(vdemService.getCountryQuestionData());
	}

}
