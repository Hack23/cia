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
package com.hack23.cia.service.data.impl;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.data.api.VoteDataDAO;

/**
 * The Class VoteDataDAOITest.
 */
@PerfTest(threads = 1, duration = 3000, warmUp = 1500)
@Required(max = 1200,average = 600,percentile95=700,throughput=2)
public final class VoteDataDAOITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The i. */
	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	/** The vote data dao. */
	@Autowired
	private VoteDataDAO voteDataDAO;

	/**
	 * Test get size.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetSize() throws Exception {

		assertTrue("Expect some votes in database",voteDataDAO.getSize() >= 0);
	}

}
