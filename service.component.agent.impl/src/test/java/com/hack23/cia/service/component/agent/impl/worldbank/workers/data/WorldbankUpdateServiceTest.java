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
package com.hack23.cia.service.component.agent.impl.worldbank.workers.data;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.service.data.api.CountryElementDAO;
import com.hack23.cia.service.data.api.DataDAO;
import com.hack23.cia.service.data.api.IndicatorElementDAO;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class WorldbankUpdateServiceITest.
 */
public class WorldbankUpdateServiceTest extends AbstractUnitTest {

	/**
	 * Update indicator element null test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void updateIndicatorElementNullTest() throws Exception {
		final IndicatorElementDAO indicatorElementDAO = mock(IndicatorElementDAO.class);
		final WorldbankUpdateService worldbankUpdateService = new WorldbankUpdateServiceImpl(null, null, indicatorElementDAO);
		worldbankUpdateService.updateIndicatorElement(null);
		verify(indicatorElementDAO, never()).persist(any(IndicatorElement.class));
	}

	/**
	 * Update indicator element success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateIndicatorElementSuccessTest() throws Exception {
		final IndicatorElementDAO indicatorElementDAO = mock(IndicatorElementDAO.class);
		final WorldbankUpdateService worldbankUpdateService = new WorldbankUpdateServiceImpl(null, null, indicatorElementDAO);
		final IndicatorElement indicatorElement = new IndicatorElement();
		worldbankUpdateService.updateIndicatorElement(indicatorElement);
		verify(indicatorElementDAO, times(1)).persist(indicatorElement);
	}

	/**
	 * Update country element null test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void updateCountryElementNullTest() throws Exception {
		final CountryElementDAO countryElementDAO = mock(CountryElementDAO.class);
		final WorldbankUpdateService worldbankUpdateService = new WorldbankUpdateServiceImpl(countryElementDAO, null, null);
		worldbankUpdateService.updateCountryElement(null);
		verify(countryElementDAO, never()).persist(any(CountryElement.class));
	}

	/**
	 * Update country element success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateCountryElementSuccessTest() throws Exception {
		final CountryElementDAO countryElementDAO = mock(CountryElementDAO.class);
		final WorldbankUpdateService worldbankUpdateService = new WorldbankUpdateServiceImpl(countryElementDAO, null, null);
		final CountryElement countryElement = new CountryElement();
		worldbankUpdateService.updateCountryElement(countryElement);
		verify(countryElementDAO, times(1)).persist(countryElement);
	}

	/**
	 * Update data null or empty test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void updateDataNullOrEmptyTest() throws Exception {
		final DataDAO dataDAO = mock(DataDAO.class);
		final WorldbankUpdateService worldbankUpdateService = new WorldbankUpdateServiceImpl(null, dataDAO, null);
		worldbankUpdateService.updateData(null);
		worldbankUpdateService.updateData(new ArrayList<>());
		verify(dataDAO, never()).persist(any(List.class));
	}

	/**
	 * Update data success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateDataSuccessTest() throws Exception {
		final DataDAO dataDAO = mock(DataDAO.class);
		final WorldbankUpdateService worldbankUpdateService = new WorldbankUpdateServiceImpl(null, dataDAO, null);
		final ArrayList<WorldBankData> data = new ArrayList<>();
		data.add(new WorldBankData());
		worldbankUpdateService.updateData(data);
		verify(dataDAO, times(1)).persist(data);
	}

}
