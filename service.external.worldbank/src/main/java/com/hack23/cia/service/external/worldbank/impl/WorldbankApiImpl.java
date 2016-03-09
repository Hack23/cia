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
package com.hack23.cia.service.external.worldbank.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.worldbank.countries.impl.CountriesElement;
import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.external.worldbank.data.impl.DataElement;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorsElement;
import com.hack23.cia.model.external.worldbank.topic.impl.TopicElement;
import com.hack23.cia.model.external.worldbank.topic.impl.TopicsElement;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankApi;

/**
 * The Class WorldbankApiImpl.
 */
@Component
public final class WorldbankApiImpl implements WorldBankApi {

	/** The xml agent. */
	@Autowired
	private XmlAgent xmlAgent;

	/** The Constant COUNTRIES. */
	private static final String COUNTRIES= "http://api.worldbank.org/country?per_page=300";

	/** The Constant COUNTRY_KEY. */
	private static final String COUNTRY_KEY = "${COUNTRY_KEY}";

	/** The Constant INDICATOR_COUNTRY_DATA. */
	private static final String INDICATOR_COUNTRY_DATA ="http://api.worldbank.org/countries/${COUNTRY_KEY}/indicators/${INDICATOR_KEY}?per_page=10000";

	/** The Constant INDICATOR_KEY. */
	private static final String INDICATOR_KEY = "${INDICATOR_KEY}";

	/** The Constant INDICATORS. */
	private static final String INDICATORS= "http://api.worldbank.org/indicators?per_page=24000";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WorldbankApiImpl.class);

	/** The Constant PROBLEM_GETTING_WORLDBANK_COUNTRY_LIST. */
	private static final String PROBLEM_GETTING_WORLDBANK_COUNTRY_LIST = "Problem getting worldbank country list";

	/**
	 * The Constant
	 * PROBLEM_GETTING_WORLDBANK_DATA_FOR_COUNTRY_CODE_S_INDICATOR_ID_S.
	 */
	private static final String PROBLEM_GETTING_WORLDBANK_DATA_FOR_COUNTRY_CODE_S_INDICATOR_ID_S = "Problem getting worldbank data for countryCode:{} ,indicatorId:{} ";

	/** The Constant PROBLEM_GETTING_WORLDBANK_INDICATOR_LIST. */
	private static final String PROBLEM_GETTING_WORLDBANK_INDICATOR_LIST = "Problem getting worldbank indicator list";

	/** The Constant PROBLEM_GETTING_WORLDBANK_TOPIC_LIST. */
	private static final String PROBLEM_GETTING_WORLDBANK_TOPIC_LIST = "Problem getting worldbank topic list";

	/** The Constant TOPICS. */
	private static final String TOPICS= "http://api.worldbank.org/topics?per_page=3000";

	/**
	 * The Constant
	 * XMLNS_WB_HTTP_COUNTRIES_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String XMLNS_WB_HTTP_COUNTRIES_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "xmlns:wb=\"http://countries.worldbank.external.model.cia.hack23.com/impl\"";

	/**
	 * The Constant
	 * XMLNS_WB_HTTP_DATA_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String XMLNS_WB_HTTP_DATA_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "xmlns:wb=\"http://data.worldbank.external.model.cia.hack23.com/impl\"";

	/**
	 * The Constant
	 * XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "xmlns:wb=\"http://indicators.worldbank.external.model.cia.hack23.com/impl\"";

	/**
	 * The Constant
	 * XMLNS_WB_HTTP_TOPIC_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String XMLNS_WB_HTTP_TOPIC_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "xmlns:wb=\"http://topic.worldbank.external.model.cia.hack23.com/impl\"";

	/** The Constant XMLNS_WB_HTTP_WWW_WORLDBANK_ORG. */
	private static final String XMLNS_WB_HTTP_WWW_WORLDBANK_ORG = "xmlns:wb=\"http://www.worldbank.org\"";

	/** The countries unmarshaller. */
	@Autowired
	@Qualifier("worldbankOrgCountriesMarshaller")
	private Unmarshaller countriesUnmarshaller;

	/** The data unmarshaller. */
	@Autowired
	@Qualifier("worldbankOrgDataMarshaller")
	private Unmarshaller dataUnmarshaller;

	/** The indicators unmarshaller. */
	@Autowired
	@Qualifier("worldbankOrgIndicatorsMarshaller")
	private Unmarshaller indicatorsUnmarshaller;

	/** The topics unmarshaller. */
	@Autowired
	@Qualifier("worldbankOrgTopicsMarshaller")
	private Unmarshaller topicsUnmarshaller;


	/** {@inheritDoc}
	 * @see com.hack23.cia.service.component.agent.impl.worldbank.api.WorlbankApi#getCountries()
	 */
	@Override
	public List<CountryElement> getCountries() throws DataFailureException {
		try {
			return ((CountriesElement) xmlAgent.unmarshallXml(countriesUnmarshaller, COUNTRIES,null,XMLNS_WB_HTTP_WWW_WORLDBANK_ORG, XMLNS_WB_HTTP_COUNTRIES_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL)).getCountry();
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_WORLDBANK_COUNTRY_LIST);
			throw new DataFailureException(e);
		}
	}

	/** {@inheritDoc}
	 * @see com.hack23.cia.service.component.agent.impl.worldbank.api.WorlbankApi#getData(java.lang.String, java.lang.String)
	 */
	@Override
	public List<WorldBankData> getData(final String countryCode,final String indicatorId) throws DataFailureException {
		try {
			final String url = INDICATOR_COUNTRY_DATA.replace(COUNTRY_KEY,countryCode);
			return ((DataElement) xmlAgent.unmarshallXml(dataUnmarshaller, url.replace(INDICATOR_KEY, indicatorId),null,XMLNS_WB_HTTP_WWW_WORLDBANK_ORG, XMLNS_WB_HTTP_DATA_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL)).getData();
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_WORLDBANK_DATA_FOR_COUNTRY_CODE_S_INDICATOR_ID_S,countryCode,indicatorId);
			throw new DataFailureException(e);
		}
	}

	/** {@inheritDoc}
	 * @see com.hack23.cia.service.component.agent.impl.worldbank.api.WorlbankApi#getIndicators()
	 */
	@Override
	public List<IndicatorElement> getIndicators() throws DataFailureException {
		try {
			return ((IndicatorsElement) xmlAgent.unmarshallXml(indicatorsUnmarshaller, INDICATORS,null,XMLNS_WB_HTTP_WWW_WORLDBANK_ORG, XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL)).getIndicator();
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_WORLDBANK_INDICATOR_LIST);
			throw new DataFailureException(e);
		}
	}

	/** {@inheritDoc}
	 * @see com.hack23.cia.service.external.worldbank.api.WorldBankApi#getTopics()
	 */
	@Override
	public List<TopicElement> getTopics() throws DataFailureException {
		try {
			return ((TopicsElement) xmlAgent.unmarshallXml(topicsUnmarshaller, TOPICS,null,XMLNS_WB_HTTP_WWW_WORLDBANK_ORG, XMLNS_WB_HTTP_TOPIC_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL)).getTopic();
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_WORLDBANK_TOPIC_LIST);
			throw new DataFailureException(e);
		}
	}

}
