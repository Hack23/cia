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
package com.hack23.cia.service.component.agent.impl.worldbank.workers;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.service.component.agent.impl.common.jms.AbstractMessageListener;
import com.hack23.cia.service.component.agent.impl.worldbank.workers.data.WorldbankUpdateService;

/**
 * The Class WorldbankIndicatorElementWorkConsumerImpl.
 */
@Service("worldbankIndicatorElementWorkConsumerImpl")
final class WorldbankIndicatorElementWorkConsumerImpl extends AbstractMessageListener implements
MessageListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WorldbankIndicatorElementWorkConsumerImpl.class);

	/** The update service. */
	private final WorldbankUpdateService updateService;

	/**
	 * Instantiates a new worldbank indicator element work consumer impl.
	 *
	 * @param updateService
	 *            the update service
	 */
	@Autowired
	public WorldbankIndicatorElementWorkConsumerImpl(final WorldbankUpdateService updateService) {
		super();
		this.updateService = updateService;
	}


	@Override
	public void onMessage(final Message message) {
		try {
			configureAuthentication();
			updateService.updateIndicatorElement((IndicatorElement)((ObjectMessage) message).getObject());
		} catch (final JMSException e) {
			LOGGER.warn("Error loading worldbank indicator :" , e);
		} finally {
			clearAuthentication();
		}
	}
}
