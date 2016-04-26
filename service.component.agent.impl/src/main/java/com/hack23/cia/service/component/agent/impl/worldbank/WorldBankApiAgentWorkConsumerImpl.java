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
package com.hack23.cia.service.component.agent.impl.worldbank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.service.component.agent.impl.common.AbstractAgentWorkConsumerImpl;
import com.hack23.cia.service.external.worldbank.api.WorldBankCountryApi;
import com.hack23.cia.service.external.worldbank.api.WorldBankIndicatorApi;

/**
 * The Class WorldBankApiAgentWorkConsumerImpl.
 */
@Service("WorldBankApiAgentWorkConsumer")
public final class WorldBankApiAgentWorkConsumerImpl extends AbstractAgentWorkConsumerImpl {


	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WorldBankApiAgentWorkConsumerImpl.class);

	/** The import service. */
	@Autowired
	private WorldbankImportService importService;

	/** The country element workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.worldbank.countries.impl.CountryElement")
	private Destination countryElementWorkdestination;

	/** The data workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.worldbank.data.impl.Data")
	private Destination dataWorkdestination;

	/** The indicator element workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement")
	private Destination indicatorElementWorkdestination;

	@Autowired
	private WorldBankCountryApi worldbankCountryApi;

	@Autowired
	private WorldBankIndicatorApi worldbankIndicatorApi;


	/**
	 * Instantiates a new world bank api agent work consumer impl.
	 */
	public WorldBankApiAgentWorkConsumerImpl() {
		super();
	}


	@Override
	public void onMessage(final Message message) {
		final ObjectMessage msg = (ObjectMessage) message;

		try {
			final WorldBankDataSources source =  (WorldBankDataSources) msg.getObject();
			LOGGER.info("Consumed message:{}", source);

			switch (source) {
			case COUNTRIES:
				startWorldbankCountryLoading();
				break;
			case INDICATORS:
				startWorldBankIndicatorLoading();
				break;
			case DATA:
				startWorldBankDataLoading();
				break;
			default:
				break;
			}

		} catch (final JMSException e1) {
			LOGGER.warn("jms", e1);
		}

	}


	/**
	 * Start worldbank country loading.
	 */
	private void startWorldbankCountryLoading() {
		try {
			final List<CountryElement> countryList = worldbankCountryApi.getCountries();
			final Map<String, String> currentSaved = importService
					.getWorldBankCountryMap();

			for (final CountryElement countryElement : countryList) {
				if (!currentSaved.containsKey(countryElement.getIso2Code())) {
					sendMessage(countryElementWorkdestination,
							countryElement);
				}
			}
		} catch (final Exception e1) {
			LOGGER.warn("jms", e1);
		}
	}

	/**
	 * Start world bank data loading.
	 */
	private void startWorldBankDataLoading() {
		try {
			final List<IndicatorElement> indicatorlist = importService.getAllIndicators();
			final List<CountryElement> countryList = worldbankCountryApi.getCountries();

			final Map<String, String> currentSaved = importService
					.getWorldBankDataMap();


			for (final IndicatorElement indicator : indicatorlist) {
				for (final CountryElement country : countryList) {
					final List<String> load = new ArrayList<>();
					if (country.getIso2Code() != null
							&& country.getIso2Code().length() > 0) {
						load.add(country.getIso2Code());
						load.add(indicator.getId());

						if (!currentSaved.containsKey(country.getIso2Code()	+ '.' + indicator.getId())) {
							sendMessage(dataWorkdestination,(Serializable) load);
						}
					}
				}
			}
		} catch (final Exception e1) {
			LOGGER.warn("jms", e1);
		}
	}

	/**
	 * Start world bank indicator loading.
	 */
	private void startWorldBankIndicatorLoading() {
		try {
			final List<IndicatorElement> list =worldbankIndicatorApi.getIndicators();

			final Map<String, String> currentSaved = importService
					.getWorldBankIndicatorElementMap();

			for (final IndicatorElement element : list) {
				if (!currentSaved.containsKey(element.getId())) {
					sendMessage(indicatorElementWorkdestination,
							element);
				}
			}
		} catch (final Exception e1) {
			LOGGER.warn("jms", e1);
		}
	}

}