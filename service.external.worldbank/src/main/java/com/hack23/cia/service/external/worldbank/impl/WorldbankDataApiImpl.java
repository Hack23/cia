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
package com.hack23.cia.service.external.worldbank.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.worldbank.data.impl.DataElement;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankDataApi;

/**
 * The Class WorldbankDataApiImpl.
 */
@Component
final class WorldbankDataApiImpl extends BaseWorldBankApiImpl implements WorldBankDataApi {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WorldbankDataApiImpl.class);

	/** The Constant COUNTRY_KEY. */
	private static final String COUNTRY_KEY = "${COUNTRY_KEY}";

	/** The Constant INDICATOR_COUNTRY_DATA. */
	private static final String INDICATOR_COUNTRY_DATA ="https://api.worldbank.org/v2/countries/${COUNTRY_KEY}/indicators/${INDICATOR_KEY}?per_page=1000";

	/** The Constant INDICATOR_KEY. */
	private static final String INDICATOR_KEY = "${INDICATOR_KEY}";

	/**
	 * The Constant
	 * PROBLEM_GETTING_WORLDBANK_DATA_FOR_COUNTRY_CODE_S_INDICATOR_ID_S.
	 */
	private static final String PROBLEM_GETTING_WORLDBANK_DATA_FOR_COUNTRY_CODE_S_INDICATOR_ID_S = "Problem getting worldbank data for countryCode:{} ,indicatorId:{} ";
	/**
	 * The Constant
	 * XMLNS_WB_HTTP_DATA_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String XMLNS_WB_HTTP_DATA_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "xmlns:wb=\"http://data.worldbank.external.model.cia.hack23.com/impl\"";

	/** The data unmarshaller. */
	@Autowired
	@Qualifier("worldbankOrgDataMarshaller")
	private Unmarshaller dataUnmarshaller;


	/**
	 * Instantiates a new worldbank data api impl.
	 */
	@Autowired
	public WorldbankDataApiImpl(final XmlAgent xmlAgent) {
		super(xmlAgent);
	}

	@Override
	public List<WorldBankData> getData(final String countryCode,final String indicatorId) throws DataFailureException {
		try {
			final String url = INDICATOR_COUNTRY_DATA.replace(COUNTRY_KEY,countryCode);
			return ((DataElement) getXmlAgent().unmarshallXml(dataUnmarshaller, url.replace(INDICATOR_KEY, indicatorId),null,XMLNS_WB_HTTP_WWW_WORLDBANK_ORG, XMLNS_WB_HTTP_DATA_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL)).getData();
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_WORLDBANK_DATA_FOR_COUNTRY_CODE_S_INDICATOR_ID_S,countryCode,indicatorId);
			throw new DataFailureException(e);
		}
	}

}
