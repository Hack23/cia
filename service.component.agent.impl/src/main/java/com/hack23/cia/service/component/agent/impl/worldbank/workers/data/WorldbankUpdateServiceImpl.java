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
package com.hack23.cia.service.component.agent.impl.worldbank.workers.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.service.data.api.CountryElementDAO;
import com.hack23.cia.service.data.api.DataDAO;
import com.hack23.cia.service.data.api.IndicatorElementDAO;

/**
 * The Class WorldbankUpdateServiceImpl.
 */
@Component("WorldbankUpdateService")
@Transactional(propagation = Propagation.MANDATORY)
final class WorldbankUpdateServiceImpl implements WorldbankUpdateService {

	/** The country element DAO. */
	@Autowired
	private final CountryElementDAO countryElementDAO;

	/** The data DAO. */
	@Autowired
	private final DataDAO dataDAO;

	/** The indicator element DAO. */
	@Autowired
	private final IndicatorElementDAO indicatorElementDAO;

	/**
	 * Instantiates a new worldbank update service impl.
	 *
	 * @param countryElementDAO   the country element DAO
	 * @param dataDAO             the data DAO
	 * @param indicatorElementDAO the indicator element DAO
	 */
	@Autowired
	public WorldbankUpdateServiceImpl(final CountryElementDAO countryElementDAO, final DataDAO dataDAO, final IndicatorElementDAO indicatorElementDAO) {
		super();
		this.countryElementDAO = countryElementDAO;
		this.dataDAO = dataDAO;
		this.indicatorElementDAO = indicatorElementDAO;
	}

	@Override
	public void updateCountryElement(final CountryElement country) {
		if (country != null) {
			countryElementDAO.persist(country);
		}
	}

	@Override
	public void updateData(final List<WorldBankData> data) {
		if (data != null && !data.isEmpty()) {
			dataDAO.persist(data);
		}
	}

	@Override
	public void updateIndicatorElement(final IndicatorElement object) {
		if (object != null) {
			indicatorElementDAO.persist(object);
		}
	}

}
