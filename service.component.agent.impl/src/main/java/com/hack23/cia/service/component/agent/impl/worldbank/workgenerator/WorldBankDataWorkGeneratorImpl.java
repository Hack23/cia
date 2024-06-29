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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankIndicatorApi;

/**
 * The Class WorldBankDataWorkGeneratorImpl.
 */
@Service("WorldBankDataWorkGeneratorImpl")
final class WorldBankDataWorkGeneratorImpl extends AbstractWorldBankDataSourcesWorkGenerator {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WorldBankDataWorkGeneratorImpl.class);


	/** The data workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.worldbank.data.impl.Data")
	private Destination dataWorkdestination;

	/** The application configuration service. */
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;

	@Autowired
	private WorldBankIndicatorApi worldbankIndicatorApi;

	/**
	 * Instantiates a new world bank data work generator impl.
	 */
	public WorldBankDataWorkGeneratorImpl() {
		super(WorldBankDataSources.DATA);
	}

	@Override
	public void generateWorkOrders() {
		final ApplicationConfiguration importDataForCountries = applicationConfigurationService.checkValueOrLoadDefault(
				"Countries to import data from worldbank (isocode) alt comma separated list",
				"Load worldbank data for countries", ConfigurationGroup.AGENT,
				WorldBankCountryWorkGeneratorImpl.class.getSimpleName(), "Worldbank country data loading",
				"Responsible import worldlbank country data", "agent.worldbank.country.data.loadCountries",
				"SE,NO,DK,FI,GB,US");

		try {
			final List<String> indicatorlist = worldbankIndicatorApi.getIndicatorsWithSwedishData();
			final Map<String, String> currentSaved = getImportService().getWorldBankDataMap();

			for (final String country : getImportService().getWorldBankCountryMap().keySet()) {
				if (StringUtils.containsIgnoreCase(importDataForCountries.getPropertyValue(), country)) {
					sendCountryIndicatorWorkOrder(currentSaved, indicatorlist, country);
				}
			}
		} catch (final DataFailureException e) {
			LOGGER.error("Problem generate data workorders", e);
		}
	}

	/**
	 * Send country indicator work order.
	 *
	 * @param currentSaved    the current saved
	 * @param indicators      the indicators
	 * @param countryIso2Code the country iso 2 code
	 */
	private void sendCountryIndicatorWorkOrder(final Map<String, String> currentSaved, final List<String> indicators,
			final String countryIso2Code) {
		for (final String indicator : indicators) {
			if (countryIso2Code != null && countryIso2Code.length() > 0
					&& !currentSaved.containsKey(countryIso2Code + '.' + indicator)) {
				final List<String> load = new ArrayList<>();
				load.add(countryIso2Code);
				load.add(indicator);
				getJmsSender().send(dataWorkdestination, (Serializable) load);
			}
		}

	}

}
