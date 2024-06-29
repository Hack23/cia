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

import java.util.Map;

/**
 * The Interface WorldbankImportService.
 */
public interface WorldbankImportService {

	/**
	 * Gets the world bank country map.
	 *
	 * @return the world bank country map
	 */
	Map<String, String> getWorldBankCountryMap();

	/**
	 * Gets the world bank data map.
	 *
	 * @return the world bank data map
	 */
	Map<String, String> getWorldBankDataMap();

	/**
	 * Gets the world bank indicator element map.
	 *
	 * @return the world bank indicator element map
	 */
	Map<String, String> getWorldBankIndicatorElementMap();

}
