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
package com.hack23.cia.service.component.agent.impl.worldbank.workers;

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
 * The Class WorldbankImportServiceImpl.
 */
@Component("WorldbankImportService")
@Transactional(propagation = Propagation.MANDATORY)
public final class WorldbankUpdateServiceImpl implements WorldbankUpdateService {

	/** The country element dao. */
	@Autowired
	private CountryElementDAO countryElementDAO;

	/** The data dao. */
	@Autowired
	private DataDAO dataDAO;

	/** The indicator element dao. */
	@Autowired
	private IndicatorElementDAO indicatorElementDAO;

	/**
	 * Instantiates a new worldbank import service impl.
	 */
	public WorldbankUpdateServiceImpl() {
		super();
	}



	@Override
	public void updateCountryElement(final CountryElement country) {
		countryElementDAO.persist(country);
	}

	@Override
	public void updateData(final List<WorldBankData> data) {
		dataDAO.persist(data);
	}

	@Override
	public void updateIndicatorElement(final IndicatorElement object) {
		indicatorElementDAO.persist(object);
	}

}
