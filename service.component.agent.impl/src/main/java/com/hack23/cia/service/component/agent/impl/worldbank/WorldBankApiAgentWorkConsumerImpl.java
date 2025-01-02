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
package com.hack23.cia.service.component.agent.impl.worldbank;

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

import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.service.component.agent.impl.common.jms.AbstractMessageListener;
import com.hack23.cia.service.component.agent.impl.worldbank.workgenerator.WorldBankDataSourcesWorkGenerator;

/**
 * The Class WorldBankApiAgentWorkConsumerImpl.
 */
@Service("WorldBankApiAgentWorkConsumer")
final class WorldBankApiAgentWorkConsumerImpl extends AbstractMessageListener implements MessageListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WorldBankApiAgentWorkConsumerImpl.class);

	/** The beans of type. */
	private final Map<String, WorldBankDataSourcesWorkGenerator> beansOfType;

	/**
	 * Instantiates a new world bank api agent work consumer impl.
	 *
	 * @param context
	 *            the context
	 */
	public WorldBankApiAgentWorkConsumerImpl(final ApplicationContext context) {
		super();
		beansOfType = context.getBeansOfType(WorldBankDataSourcesWorkGenerator.class);
	}

	@Override
	public void onMessage(final Message message) {
		try {
			configureAuthentication();
			final ObjectMessage msg = (ObjectMessage) message;
			LOGGER.info("Consumed message:{}", msg.getObject());
			final WorldBankDataSources dataSource = (WorldBankDataSources) msg.getObject();
			final Collection<WorldBankDataSourcesWorkGenerator> values = beansOfType.values();

			for (final WorldBankDataSourcesWorkGenerator worldBankDataSourcesWorkGenerator : values) {
				if (worldBankDataSourcesWorkGenerator.matches(dataSource)) {
					worldBankDataSourcesWorkGenerator.generateWorkOrders();
				}
			}
		} catch (final JMSException exception) {
			LOGGER.warn("jms", exception);
		} finally {
			clearAuthentication();
		}
	}

}