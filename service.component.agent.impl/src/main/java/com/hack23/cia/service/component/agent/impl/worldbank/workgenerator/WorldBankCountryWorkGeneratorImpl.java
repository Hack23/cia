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

import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;
import com.hack23.cia.service.external.worldbank.api.WorldBankCountryApi;

/**
 * The Class WorldBankCountryWorkGeneratorImpl.
 */
@Service
final class WorldBankCountryWorkGeneratorImpl extends AbstractWorldBankDataSourcesWorkGenerator {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WorldBankCountryWorkGeneratorImpl.class);

	/** The country element workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.worldbank.countries.impl.CountryElement")
	private Destination countryElementWorkdestination;

	/** The worldbank country api. */
	@Autowired
	private WorldBankCountryApi worldbankCountryApi;

	/** The application configuration service. */
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;


	/**
	 * Instantiates a new world bank country work generator impl.
	 */
	public WorldBankCountryWorkGeneratorImpl() {
		super(WorldBankDataSources.COUNTRIES);
	}

	@Override
	public void generateWorkOrders() {

		final ApplicationConfiguration importDataForCountries = applicationConfigurationService.checkValueOrLoadDefault("Countries to import data from worldbank (isocode) alt comma separated list", "Load worldbank data for countries", ConfigurationGroup.AGENT, WorldBankCountryWorkGeneratorImpl.class.getSimpleName(), "Worldbank country data loading", "Responsible import worldlbank country data", "agent.worldbank.country.data.loadCountries", "SE");

		try {
			final List<CountryElement> countryList = worldbankCountryApi.getCountries();
			final Map<String, String> currentSaved = getImportService().getWorldBankCountryMap();

			for (final CountryElement countryElement : countryList) {
				if (!currentSaved.containsKey(countryElement.getIso2Code()) && importDataForCountries.getPropertyValue().equalsIgnoreCase(countryElement.getIso2Code())) {
					sendMessage(countryElementWorkdestination,
							countryElement);
				}
			}
		} catch (final Exception exception) {
			LOGGER.warn("jms", exception);
		}
	}

}
