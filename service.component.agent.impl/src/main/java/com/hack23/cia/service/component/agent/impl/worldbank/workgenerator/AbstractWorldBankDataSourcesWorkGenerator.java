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
package com.hack23.cia.service.component.agent.impl.worldbank.workgenerator;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.service.component.agent.impl.common.jms.JmsSender;
import com.hack23.cia.service.component.agent.impl.worldbank.workgenerator.data.WorldbankImportService;

/**
 * The Class AbstractWorldBankDataSourcesWorkGenerator.
 */
public abstract class AbstractWorldBankDataSourcesWorkGenerator implements WorldBankDataSourcesWorkGenerator {

	/** The jms sender. */
	@Autowired
	private JmsSender jmsSender;

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
	public final boolean matches(final WorldBankDataSources datasource) {
		return this.datasource == datasource;
	}

	/**
	 * Gets the import service.
	 *
	 * @return the import service
	 */
	protected final WorldbankImportService getImportService() {
		return importService;
	}

	/**
	 * Gets the jms sender.
	 *
	 * @return the jms sender
	 */
	protected final JmsSender getJmsSender() {
		return jmsSender;
	}

}
