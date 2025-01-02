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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator;

import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.voteringlista.impl.BallotDocumentElement;
import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenBallotApi;

/**
 * The Class RiksdagenBallotListWorkGeneratorImpl.
 */
@Service("RiksdagenBallotListWorkGeneratorImpl")
final class RiksdagenBallotListWorkGeneratorImpl extends AbstractRiksdagenDataSourcesWorkGenerator {

	/** The Constant LOADING_BALLOTS. */
	private static final String LOADING_BALLOTS = "Loading ballots";


	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenBallotListWorkGeneratorImpl.class);


	/** The vote data workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.votering.impl.VoteData")
	private Destination voteDataWorkdestination;

	/** The riksdagen api. */
	@Autowired
	private RiksdagenBallotApi riksdagenApi;

	/**
	 * Instantiates a new riksdagen ballot list work generator impl.
	 */
	public RiksdagenBallotListWorkGeneratorImpl() {
		super(RiksdagenDataSources.BALLOT_LIST);
	}

	@Override
	public void generateWorkOrders() {
		try {
			final List<BallotDocumentElement> ballotList = riksdagenApi.getBallotList();
			final Map<String, String> alreadySavedIdMap = getImportService()
					.getLoadedBallotIdMap();

			for (final BallotDocumentElement ballotDocument : ballotList) {
				if (!alreadySavedIdMap.containsKey(ballotDocument.getBallotId())) {
					getJmsSender().send(voteDataWorkdestination,
							ballotDocument.getBallotId());
					LOGGER.info("Load : https://data.riksdagen.se/votering/{}", ballotDocument.getBallotId());
				}
			}
		} catch (final DataFailureException e) {
			LOGGER.warn(LOADING_BALLOTS, e);
		}
	}

}
