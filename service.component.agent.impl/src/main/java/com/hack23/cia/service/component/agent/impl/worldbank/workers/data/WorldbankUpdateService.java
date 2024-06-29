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

import java.util.List;

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;

/**
 * The Interface WorldbankUpdateService.
 */
public interface WorldbankUpdateService {

	/**
	 * Update country element.
	 *
	 * @param country
	 *            the country
	 */
	void updateCountryElement(CountryElement country);



	/**
	 * Update data.
	 *
	 * @param data
	 *            the data
	 */
	void updateData(List<WorldBankData> data);


	/**
	 * Update indicator element.
	 *
	 * @param object
	 *            the object
	 */
	void updateIndicatorElement(IndicatorElement object);

}
