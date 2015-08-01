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
 *	$Id: WorldbankImportServiceImpl.java 6119 2015-07-31 17:44:12Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.component.agent.impl/src/main/java/com/hack23/cia/service/component/agent/impl/worldbank/WorldbankImportServiceImpl.java $
*/
package com.hack23.cia.service.component.agent.impl.worldbank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
@Component(value = "WorldbankImportService")
@Transactional(propagation = Propagation.MANDATORY)
public final class WorldbankImportServiceImpl implements WorldbankImportService {

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
	 * Creates the map from list.
	 *
	 * @param all
	 *            the all
	 * @return the map
	 */
	private static Map<String, String> createMapFromList(final List<String> all) {
		final Map<String, String> map = new ConcurrentHashMap<String, String>();

		for (final String documentElement : all) {
			map.put(documentElement, documentElement);
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.component.agent.impl.worldbank.WorldbankImportService#getAllIndicators()
	 */
	@Override
	public List<IndicatorElement> getAllIndicators() {
		return indicatorElementDAO.getAll();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getWorldBankCountryMap()
	 */
	@Override
	public Map<String, String> getWorldBankCountryMap() {
		final List<String> list = new ArrayList<String>();
		for (final CountryElement countryElement : countryElementDAO.getAll()) {
			list.add(countryElement.getIso2Code());
		}
		return createMapFromList(list);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getWorldBankDataMap()
	 */
	@Override
	public Map<String, String> getWorldBankDataMap() {
		return createMapFromList(dataDAO.getIdList());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getWorldBankIndicatorElementMap()
	 */
	@Override
	public Map<String, String> getWorldBankIndicatorElementMap() {
		final List<String> list = new ArrayList<String>();
		for (final IndicatorElement element : indicatorElementDAO.getAll()) {
			list.add(element.getId());
		}
		return createMapFromList(list);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * updateCountryElement
	 * (com.hack23.cia.model.external.worldbank.countries.impl.CountryElement)
	 */
	@Override
	public void updateCountryElement(final CountryElement country) {
		countryElementDAO.persist(country);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.component.agent.impl.AgentWorkService#updateData
	 * (java.util.List)
	 */
	@Override
	public void updateData(final List<WorldBankData> data) {
		dataDAO.persist(data);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * updateIndicatorElement
	 * (com.hack23.cia.model.external.worldbank.indicators.
	 * impl.IndicatorElement)
	 */
	@Override
	public void updateIndicatorElement(final IndicatorElement object) {
		indicatorElementDAO.persist(object);
	}

}
