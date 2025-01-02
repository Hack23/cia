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
package com.hack23.cia.service.external.worldbank.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankIndicatorApi;

/**
 * The Class WorldbankIndicatorApiImplTest.
 */
public final class WorldbankIndicatorApiImplTest extends AbstractWorldbankFunctionalIntegrationTest {

	/** The worlbank api. */
	@Autowired
	private WorldBankIndicatorApi worlbankApi;

	/**
	 * Gets the indicators test.
	 *
	 * @return the indicators test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getIndicatorsTest() throws Exception {
		final List<IndicatorElement> indicators = worlbankApi.getIndicators();
		assertNotNull("Expect indicators", indicators);
		assertTrue("Expect above 15.000 indicators", indicators.size() >= 15000);
	}


	@Test
	public void getIndicatorsWithSwedishDataTest() throws Exception {
		final List<IndicatorElement> indicators = worlbankApi.getIndicators();
		assertFalse(indicators.isEmpty());
		final List<String> indicatorsWithSwedishData = worlbankApi.getIndicatorsWithSwedishData();
		assertNotNull("Expect indicators", indicatorsWithSwedishData);
		assertFalse(indicatorsWithSwedishData.isEmpty());
		assertTrue("Expect above 1400 indicators", indicatorsWithSwedishData.size() >= 1400);
		assertNotEquals(indicators.size(), indicatorsWithSwedishData.size());
	}


	/**
	 * Gets the indicators failure test.
	 *
	 * @return the indicators failure test
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = DataFailureException.class)
	public void getIndicatorsFailureTest() throws Exception {
		new WorldbankIndicatorApiImpl(createMockXmlAgentThrowsException()).getIndicators();
	}

}
