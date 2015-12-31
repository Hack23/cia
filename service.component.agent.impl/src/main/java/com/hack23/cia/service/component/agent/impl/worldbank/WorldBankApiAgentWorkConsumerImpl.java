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
 *	$Id: WorldBankApiAgentWorkConsumerImpl.java 6102 2015-06-17 21:48:57Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.component.agent.impl/src/main/java/com/hack23/cia/service/component/agent/impl/worldbank/WorldBankApiAgentWorkConsumerImpl.java $
 */
package com.hack23.cia.service.component.agent.impl.worldbank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
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
import com.hack23.cia.service.external.worldbank.api.WorldBankApi;

/**
 * The Class WorldBankApiAgentWorkConsumerImpl.
 */
@Service(value = "WorldBankApiAgentWorkConsumer")
public final class WorldBankApiAgentWorkConsumerImpl extends AbstractAgentWorkConsumerImpl implements MessageListener {


	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WorldBankApiAgentWorkConsumerImpl.class);

	/** The agent work service. */
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

	/** The worldbank api. */
	@Autowired
	private WorldBankApi worldbankApi;

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(final Message message) {
		final ObjectMessage msg = (ObjectMessage) message;

		try {
			WorldBankDataSources source =  (WorldBankDataSources) msg.getObject();
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
			List<CountryElement> countryList;
			countryList = worldbankApi.getCountries();
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
//			final List<CountryElement> countryList = worldbankApi.getCountries();

			final Map<String, String> currentSaved = importService
					.getWorldBankDataMap();


			for (final IndicatorElement indicator : indicatorlist) {
		//		for (final CountryElement country : countryList) {
					final List<String> load = new ArrayList<String>();
//					if (country.getIso2Code() != null
//							&& country.getIso2Code().length() > 0) {
						String swedenIsoCode = "SE";
						load.add(swedenIsoCode);
						load.add(indicator.getId());

						if (!currentSaved.containsKey(swedenIsoCode
								+ "." + indicator.getId())) {
							sendMessage(dataWorkdestination,

									(Serializable) load);
						}
					}
//				}
//			}
		} catch (final Exception e1) {
			LOGGER.warn("jms", e1);
		}
	}

	/**
	 * Start world bank indicator loading.
	 */
	private void startWorldBankIndicatorLoading() {
		try {
			List<IndicatorElement> list;
			list = worldbankApi.getIndicators();

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