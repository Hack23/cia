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
package com.hack23.cia.service.component.agent.impl.riksdagen.workers;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.component.agent.impl.common.jms.AbstractMessageListener;
import com.hack23.cia.service.component.agent.impl.riksdagen.workers.data.RiksdagenUpdateService;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenBallotApi;

/**
 * The Class RiksdagenVoteDataWorkConsumerImpl.
 */
@Service("riksdagenVoteDataWorkConsumerImpl")
final class RiksdagenVoteDataWorkConsumerImpl extends AbstractMessageListener implements MessageListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenVoteDataWorkConsumerImpl.class);

	/** The update service. */
	private final RiksdagenUpdateService updateService;

	/** The riksdagen api. */
	private final RiksdagenBallotApi riksdagenApi;

	/**
	 * Instantiates a new riksdagen vote data work consumer impl.
	 *
	 * @param updateService
	 *            the update service
	 * @param riksdagenApi
	 *            the riksdagen api
	 */
	@Autowired
	public RiksdagenVoteDataWorkConsumerImpl(final RiksdagenUpdateService updateService, final RiksdagenBallotApi riksdagenApi) {
		super();
		this.updateService = updateService;
		this.riksdagenApi = riksdagenApi;
	}

	@Override
	public void onMessage(final Message message) {
		try {
			updateBallot((String) ((ObjectMessage) message).getObject());
		} catch (final JMSException e) {
			LOGGER.warn("No Valid input", e);
		}
	}

	/**
	 * Update ballot.
	 *
	 * @param ballotId
	 *            the ballot id
	 */
	private void updateBallot(final String ballotId) {
		try {
			configureAuthentication();
			updateService.updateVoteDataData(riksdagenApi.getBallot(ballotId));
		} catch (final DataFailureException e) {
			LOGGER.warn("Eror loading riksdagen voteData:{} errorMessage:{}",ballotId, e);
		} finally {
			clearAuthentication();
		}
	}
}
