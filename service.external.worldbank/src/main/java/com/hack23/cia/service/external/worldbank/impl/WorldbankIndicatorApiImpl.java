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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorsElement;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankIndicatorApi;

/**
 * The Class WorldbankIndicatorApiImpl.
 */
@Component
final class WorldbankIndicatorApiImpl extends AbstractWorldBankApiImpl implements WorldBankIndicatorApi {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WorldbankIndicatorApiImpl.class);

	/** The Constant INDICATORS. */
	private static final String INDICATORS = "https://api.worldbank.org/indicators?per_page=5000";

	private static final String PAGE_NUMBER = "&page=";

	/** The Constant PROBLEM_GETTING_WORLDBANK_INDICATOR_LIST. */
	private static final String PROBLEM_GETTING_WORLDBANK_INDICATOR_LIST = "Problem getting worldbank indicator list";

	private static final int SECOND_PAGE = 2;

	/**
	 * The Constant
	 * XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "xmlns:wb=\"http://indicators.worldbank.external.model.cia.hack23.com/impl\"";


	/** The indicators unmarshaller. */
	@Autowired
	@Qualifier("worldbankOrgIndicatorsMarshaller")
	private Unmarshaller indicatorsUnmarshaller;

	/**
	 * Instantiates a new worldbank indicator api impl.
	 */
	public WorldbankIndicatorApiImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.worldbank.api.WorldBankIndicatorApi#getIndicators()
	 */
	@Override
	public List<IndicatorElement> getIndicators() throws DataFailureException {
		final List<IndicatorElement> result = new ArrayList<>();

		try {
			final IndicatorsElement firstPage = (IndicatorsElement) getXmlAgent().unmarshallXml(indicatorsUnmarshaller,
					INDICATORS, null, XMLNS_WB_HTTP_WWW_WORLDBANK_ORG,
					XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL);
			result.addAll(firstPage.getIndicator());

			for (int pageNumber = SECOND_PAGE; pageNumber < firstPage.getPages().intValue(); pageNumber++) {
				final IndicatorsElement otherPageResult = (IndicatorsElement) getXmlAgent().unmarshallXml(indicatorsUnmarshaller,
						INDICATORS + PAGE_NUMBER + pageNumber, null, XMLNS_WB_HTTP_WWW_WORLDBANK_ORG,
						XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL);
				result.addAll(otherPageResult.getIndicator());
			}

		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_WORLDBANK_INDICATOR_LIST);
			throw new DataFailureException(e);
		}

		return result;
	}

}
