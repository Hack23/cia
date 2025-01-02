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

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;
import com.hack23.cia.service.component.agent.impl.common.jms.JmsSender;
import com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator.data.RiksdagenImportService;

/**
 * The Class AbstractRiksdagenDataSourcesWorkGenerator.
 */
public abstract class AbstractRiksdagenDataSourcesWorkGenerator implements RiksdagenDataSourcesWorkGenerator {

	/** The import service. */
	@Autowired
	private RiksdagenImportService importService;

	/** The datasource. */
	private final RiksdagenDataSources datasource;

	/** The jms sender. */
	@Autowired
	private JmsSender jmsSender;

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
	 * Gets the jms sender.
	 *
	 * @return the jms sender
	 */
	public final JmsSender getJmsSender() {
		return jmsSender;
	}

	/**
	 * Gets the import service.
	 *
	 * @return the import service
	 */
	protected final RiksdagenImportService getImportService() {
		return importService;
	}

}
