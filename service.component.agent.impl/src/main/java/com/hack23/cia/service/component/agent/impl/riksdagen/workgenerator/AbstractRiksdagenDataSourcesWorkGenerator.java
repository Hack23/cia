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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator;

import java.io.Serializable;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;
import com.hack23.cia.service.component.agent.impl.common.ProducerMessageFactory;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenApi;

/**
 * The Class AbstractRiksdagenDataSourcesWorkGenerator.
 */
public abstract class AbstractRiksdagenDataSourcesWorkGenerator implements RiksdagenDataSourcesWorkGenerator {

	/** The import service. */
	@Autowired
	private RiksdagenImportService importService;

	/** The riksdagen api. */
	@Autowired
	private RiksdagenApi riksdagenApi;

	/** The jms template. */
	@Autowired
	private JmsTemplate jmsTemplate;

	/** The datasource. */
	private final RiksdagenDataSources datasource;

	/**
	 * Instantiates a new abstract riksdagen data sources work generator.
	 *
	 * @param datasource
	 *            the datasource
	 */
	public AbstractRiksdagenDataSourcesWorkGenerator(final RiksdagenDataSources datasource) {
		super();
		this.datasource = datasource;
	}

	@Override
	public final boolean matches(final RiksdagenDataSources datasource) {
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
	protected final RiksdagenImportService getImportService() {
		return importService;
	}

	/**
	 * Gets the riksdagen api.
	 *
	 * @return the riksdagen api
	 */
	protected final RiksdagenApi getRiksdagenApi() {
		return riksdagenApi;
	}

}
