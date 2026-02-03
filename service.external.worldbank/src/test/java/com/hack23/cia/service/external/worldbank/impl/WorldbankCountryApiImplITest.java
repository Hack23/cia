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

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankCountryApi;

/**
 * The Class WorldbankCountryApiImplITest.
 */
public final class WorldbankCountryApiImplITest extends AbstractWorldbankFunctionalIntegrationTest {

	/** The worlbank api. */
	@Autowired
	private WorldBankCountryApi worlbankApi;

	/**
	 * Gets the countries test.
	 *
	 * @return the countries test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getCountriesTest() throws Exception {
		final List<CountryElement> countries = worlbankApi.getCountries();
		assertNotNull("Expect countries", countries);
		assertTrue("Approx > 230 countries", countries.size() > 230);
	}

	/**
	 * Gets the countries failure test.
	 *
	 * @return the countries failure test
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = DataFailureException.class)
	public void getCountriesFailureTest() throws Exception {
		new WorldbankCountryApiImpl(createMockXmlAgentThrowsException()).getCountries();
	}

}
