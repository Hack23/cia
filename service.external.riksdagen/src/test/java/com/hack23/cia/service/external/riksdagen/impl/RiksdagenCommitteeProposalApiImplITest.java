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
package com.hack23.cia.service.external.riksdagen.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenCommitteeProposalApi;

/**
 * The Class RiksdagenCommitteeProposalApiImplITest.
 */
public final class RiksdagenCommitteeProposalApiImplITest extends
AbstractRiksdagenFunctionalIntegrationTest {

	/** The riksdagen api. */
	@Autowired
	private RiksdagenCommitteeProposalApi riksdagenApi;


	/**
	 * Gets the committee proposal test.
	 *
	 * @return the committee proposal test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getCommitteeProposalTest() throws Exception {
		final CommitteeProposalComponentData proposal = riksdagenApi
				.getCommitteeProposal("GX01FiU35");
		assertNotNull(proposal);
		final CommitteeProposalComponentData proposal2 = riksdagenApi
				.getCommitteeProposal("GVA3FöU43");
		assertNotNull(proposal2);

	}

	/**
	 * Gets the committee proposal failure test.
	 *
	 * @return the committee proposal failure test
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected =  DataFailureException.class)
	public void getCommitteeProposalFailureTest() throws Exception {
		new RiksdagenCommitteeProposalApiImpl(createMockXmlAgentThrowsException()).getCommitteeProposal("GX01FiU35");
	}

}
