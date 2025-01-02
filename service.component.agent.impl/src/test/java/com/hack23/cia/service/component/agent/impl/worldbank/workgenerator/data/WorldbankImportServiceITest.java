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
package com.hack23.cia.service.component.agent.impl.worldbank.workgenerator.data;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;

/**
 * The Class WorldbankImportServiceITest.
 */
@Transactional
public class WorldbankImportServiceITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The worldbank import service. */
	@Autowired
	private WorldbankImportService worldbankImportService;

	/**
	 * Gets the world bank country map test.
	 *
	 * @return the world bank country map test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getWorldBankCountryMapTest() throws Exception {
		final Map<String, String> worldBankCountryMap = worldbankImportService.getWorldBankCountryMap();
		assertNotNull(worldBankCountryMap);
		assertFalse(worldBankCountryMap.isEmpty());
	}

	/**
	 * Gets the world bank data map test.
	 *
	 * @return the world bank data map test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getWorldBankDataMapTest() throws Exception {
		final Map<String, String> worldBankDataMap = worldbankImportService.getWorldBankDataMap();
		assertNotNull(worldBankDataMap);
		assertFalse(worldBankDataMap.isEmpty());
	}

	/**
	 * Gets the world bank indicator element map test.
	 *
	 * @return the world bank indicator element map test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getWorldBankIndicatorElementMapTest() throws Exception {
		final Map<String, String> worldBankIndicatorElementMap = worldbankImportService.getWorldBankIndicatorElementMap();
		assertNotNull(worldBankIndicatorElementMap);
		assertFalse(worldBankIndicatorElementMap.isEmpty());
	}

}
