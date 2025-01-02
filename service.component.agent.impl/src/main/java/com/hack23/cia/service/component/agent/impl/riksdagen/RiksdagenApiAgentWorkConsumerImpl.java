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
package com.hack23.cia.service.component.agent.impl.riksdagen;

import java.util.Collection;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;
import com.hack23.cia.service.component.agent.impl.common.jms.AbstractMessageListener;
import com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator.RiksdagenDataSourcesWorkGenerator;

/**
 * The Class RiksdagenApiAgentWorkConsumerImpl.
 */
@Service("RiksdagenApiAgentWorkConsumer")
final class RiksdagenApiAgentWorkConsumerImpl extends AbstractMessageListener implements MessageListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenApiAgentWorkConsumerImpl.class);

	/** The beans of type. */
	private final Map<String, RiksdagenDataSourcesWorkGenerator> beansOfType;

	/**
	 * Instantiates a new riksdagen api agent work consumer impl.
	 *
	 * @param context
	 *            the context
	 */
	public RiksdagenApiAgentWorkConsumerImpl(final ApplicationContext context) {
		super();
		beansOfType = context.getBeansOfType(RiksdagenDataSourcesWorkGenerator.class);
	}

	@Override
	public void onMessage(final Message message) {
		try {
			configureAuthentication();
			final ObjectMessage msg = (ObjectMessage) message;
			LOGGER.info("Consumed message:{}", msg.getObject());
			final RiksdagenDataSources dataSource = (RiksdagenDataSources) msg.getObject();

			final Collection<RiksdagenDataSourcesWorkGenerator> values = beansOfType.values();

			for (final RiksdagenDataSourcesWorkGenerator riksdagenDataSourcesWorkGenerator : values) {
				if (riksdagenDataSourcesWorkGenerator.matches(dataSource)) {
					riksdagenDataSourcesWorkGenerator.generateWorkOrders();
					return;
				}
			}
		} catch (final JMSException exception) {
			LOGGER.warn("jms", exception);
		} finally {
			clearAuthentication();
		}
	}

}
