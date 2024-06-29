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
import com.hack23.cia.service.external.riksdagen.api.RiksdagenCommitteeProposalApi;

/**
 * The Class RiksdagenCommitteeProposalComponentDataWorkConsumerImpl.
 */
@Service("riksdagenCommitteeProposalComponentDataWorkConsumerImpl")
final class RiksdagenCommitteeProposalComponentDataWorkConsumerImpl extends AbstractMessageListener implements MessageListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RiksdagenCommitteeProposalComponentDataWorkConsumerImpl.class);

	/** The update service. */
	private final RiksdagenUpdateService updateService;

	/** The riksdagen api. */
	private final RiksdagenCommitteeProposalApi riksdagenApi;


	/**
	 * Instantiates a new riksdagen committee proposal component data work consumer
	 * impl.
	 *
	 * @param updateService
	 *            the update service
	 * @param riksdagenApi
	 *            the riksdagen api
	 */
	@Autowired
	public RiksdagenCommitteeProposalComponentDataWorkConsumerImpl(final RiksdagenUpdateService updateService,
			final RiksdagenCommitteeProposalApi riksdagenApi) {
		super();
		this.updateService = updateService;
		this.riksdagenApi = riksdagenApi;
	}



	@Override
	public void onMessage(final Message message) {
		try {
			configureAuthentication();
			updateService.updateCommitteeProposalComponentData(riksdagenApi
					.getCommitteeProposal((String) ((ObjectMessage) message)
							.getObject()));
		} catch (final DataFailureException | JMSException e) {
			LOGGER.warn("Error loading CommitteeProposalComponentData" , e);
		} finally {
			clearAuthentication();
		}
	}
}
