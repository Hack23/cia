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
package com.hack23.cia.service.external.riksdagen.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData;
import com.hack23.cia.model.external.riksdagen.voteringlista.impl.BallotDocumentElement;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenBallotApi;

/**
 * The Class RiksdagenBallotApiImplITest.
 */
public final class RiksdagenBallotApiImplITest extends
AbstractRiksdagenFunctionalIntegrationTest {

	/** The riksdagen api. */
	@Autowired
	private RiksdagenBallotApi riksdagenApi;

	/**
	 * Gets the ballot test.
	 *
	 * @return the ballot test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getBallotTest() throws Exception {
		final List<VoteData> votering = riksdagenApi
				.getBallot("E0B34EE1-3FD3-474A-81D7-781B04BB241E");
		assertNotNull(votering);
		final VoteData voteData = votering.iterator().next();
		assertNotNull(voteData.getEmbeddedId());
		assertNotNull(voteData.getEmbeddedId().getBallotId());
		assertNotNull(voteData.getEmbeddedId().getIntressentId());
		assertNotNull(voteData.getEmbeddedId().getIssue());
	}

	/**
	 * Gets the ballot failure test.
	 *
	 * @return the ballot failure test
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected =  DataFailureException.class)
	public void getBallotFailureTest() throws Exception {
		new RiksdagenBallotApiImpl(createMockXmlAgentThrowsException()).getBallot("E0B34EE1-3FD3-474A-81D7-781B04BB241E");
	}


	/**
	 * Gets the ballot list test.
	 *
	 * @return the ballot list test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getBallotListTest() throws Exception {
		final List<BallotDocumentElement> ballotList = riksdagenApi
				.getBallotList();
		assertNotNull(ballotList);
		assertTrue(ballotList.size() >= 7888);
	}

	/**
	 * Gets the ballot list failure test.
	 *
	 * @return the ballot list failure test
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected =  DataFailureException.class)
	public void getBallotListFailureTest() throws Exception {
		new RiksdagenBallotApiImpl(createMockXmlAgentThrowsException()).getBallotList();
	}

}
