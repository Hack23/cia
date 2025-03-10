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

import com.hack23.cia.model.external.riksdagen.personlista.impl.PersonElement;
import com.hack23.cia.service.component.agent.impl.common.jms.AbstractMessageListener;
import com.hack23.cia.service.component.agent.impl.riksdagen.workers.data.RiksdagenUpdateService;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenPersonApi;

/**
 * The Class RiksdagenPersonElementWorkConsumerImpl.
 */
@Service("riksdagenPersonElementWorkConsumerImpl")
final class RiksdagenPersonElementWorkConsumerImpl extends AbstractMessageListener implements MessageListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RiksdagenPersonElementWorkConsumerImpl.class);

	/** The update service. */
	private final RiksdagenUpdateService updateService;

	/** The riksdagen api. */
	private final RiksdagenPersonApi riksdagenApi;

	/**
	 * Instantiates a new riksdagen person element work consumer impl.
	 *
	 * @param updateService
	 *            the update service
	 * @param riksdagenApi
	 *            the riksdagen api
	 */
	@Autowired
	public RiksdagenPersonElementWorkConsumerImpl(final RiksdagenUpdateService updateService,
			final RiksdagenPersonApi riksdagenApi) {
		super();
		this.updateService = updateService;
		this.riksdagenApi = riksdagenApi;
	}

	@Override
	public void onMessage(final Message message) {
		try {
			configureAuthentication();
			updateService.update(riksdagenApi
					.getPerson(((PersonElement) ((ObjectMessage) message)
							.getObject()).getId()));
		} catch (final DataFailureException | JMSException e) {
			LOGGER.warn("Error loading PersonElement",e);
		} finally {
			clearAuthentication();
		}
	}
}
