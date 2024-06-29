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
package com.hack23.cia.service.component.agent.impl.worldbank.workgenerator.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.service.data.api.CountryElementDAO;
import com.hack23.cia.service.data.api.DataDAO;
import com.hack23.cia.service.data.api.IndicatorElementDAO;

/**
 * The Class WorldbankImportServiceImpl.
 */
@Component("WorldbankImportService")
@Transactional(propagation = Propagation.MANDATORY)
final class WorldbankImportServiceImpl implements WorldbankImportService {

	/** The country element DAO. */
	@Autowired
	private CountryElementDAO countryElementDAO;

	/** The data DAO. */
	@Autowired
	private DataDAO dataDAO;

	/** The indicator element DAO. */
	@Autowired
	private IndicatorElementDAO indicatorElementDAO;

	/**
	 * Instantiates a new worldbank import service impl.
	 */
	public WorldbankImportServiceImpl() {
		super();
	}

	/**
	 * Creates the map from list.
	 *
	 * @param all
	 *            the all
	 * @return the map
	 */
	private static Map<String, String> createMapFromList(final List<String> all) {
		final Map<String, String> map = new ConcurrentHashMap<>();

		for (final String documentElement : all) {
			if (documentElement != null) {
				map.put(documentElement, documentElement);
			}
		}
		return map;
	}

	@Override
	public Map<String, String> getWorldBankCountryMap() {
		final List<String> list = new ArrayList<>();
		for (final CountryElement countryElement : countryElementDAO.getAll()) {
			list.add(countryElement.getIso2Code());
		}
		return createMapFromList(list);
	}

	@Override
	public Map<String, String> getWorldBankDataMap() {
		return createMapFromList(dataDAO.getIdList());
	}

	@Override
	public Map<String, String> getWorldBankIndicatorElementMap() {
		final List<String> list = new ArrayList<>();
		for (final IndicatorElement element : indicatorElementDAO.getAll()) {
			list.add(element.getId());
		}
		return createMapFromList(list);
	}

}
