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
package com.hack23.cia.service.component.agent.impl.worldbank.workgenerator;

import java.io.Serializable;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.service.component.agent.impl.common.ProducerMessageFactory;

/**
 * The Class AbstractWorldBankDataSourcesWorkGenerator.
 */
public abstract class AbstractWorldBankDataSourcesWorkGenerator implements WorldBankDataSourcesWorkGenerator {

	/** The jms template. */
	@Autowired
	private JmsTemplate jmsTemplate;

	/** The import service. */
	@Autowired
	private WorldbankImportService importService;

	/** The datasource. */
	private final WorldBankDataSources datasource;

	/**
	 * Instantiates a new abstract world bank data sources work generator.
	 *
	 * @param datasource
	 *            the datasource
	 */
	public AbstractWorldBankDataSourcesWorkGenerator(final WorldBankDataSources datasource) {
		super();
		this.datasource = datasource;
	}

	@Override
	public boolean matches(final WorldBankDataSources datasource) {
		return this.datasource == datasource;
	}

	/**
	 * Send message.
	 *
	 * @param destination
	 *            the destination
	 * @param msg
	 *            the msg
	 * @throws Exception
	 *             the exception
	 */
	protected final void sendMessage(final Destination destination, final Serializable msg) throws Exception {
		jmsTemplate.send(destination, new ProducerMessageFactory(msg));
	}

	/**
	 * Gets the import service.
	 *
	 * @return the import service
	 */
	protected final WorldbankImportService getImportService() {
		return importService;
	}

}
