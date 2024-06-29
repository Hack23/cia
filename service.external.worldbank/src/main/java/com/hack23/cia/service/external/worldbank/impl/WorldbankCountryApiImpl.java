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

import com.hack23.cia.model.external.worldbank.countries.impl.CountriesElement;
import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankCountryApi;

/**
 * The Class WorldbankCountryApiImpl.
 */
@Component
final class WorldbankCountryApiImpl extends BaseWorldBankApiImpl implements WorldBankCountryApi {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WorldbankCountryApiImpl.class);

	/** The Constant COUNTRIES. */
	private static final String COUNTRIES = "https://api.worldbank.org/v2/country?per_page=300";

	/** The Constant PROBLEM_GETTING_WORLDBANK_COUNTRY_LIST. */
	private static final String PROBLEM_GETTING_WORLDBANK_COUNTRY_LIST = "Problem getting worldbank country list";

	/**
	 * The Constant
	 * XMLNS_WB_HTTP_COUNTRIES_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String XMLNS_WB_HTTP_COUNTRIES_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "xmlns:wb=\"http://countries.worldbank.external.model.cia.hack23.com/impl\"";

	/** The countries unmarshaller. */
	@Autowired
	@Qualifier("worldbankOrgCountriesMarshaller")
	private Unmarshaller countriesUnmarshaller;

	/**
	 * Instantiates a new worldbank country api impl.
	 */
	@Autowired
	public WorldbankCountryApiImpl(final XmlAgent xmlAgent) {
		super(xmlAgent);
	}

	@Override
	public List<CountryElement> getCountries() throws DataFailureException {
		try {
			return ((CountriesElement) getXmlAgent().unmarshallXml(countriesUnmarshaller, COUNTRIES, null,
					XMLNS_WB_HTTP_WWW_WORLDBANK_ORG,
					XMLNS_WB_HTTP_COUNTRIES_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL)).getCountry();
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_WORLDBANK_COUNTRY_LIST);
			throw new DataFailureException(e);
		}
	}

}
