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
package com.hack23.cia.service.external.worldbank.api;

import java.io.IOException;
import java.util.List;

import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;

/**
 * The Interface WorldBankIndicatorApi.
 */
public interface WorldBankIndicatorApi {

	/**
	 * Gets the indicators.
	 *
	 * @return the indicators
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	List<IndicatorElement> getIndicators() throws DataFailureException;

	/**
	 * Gets the indicators with swedish data.
	 *
	 * @return the indicators with swedish data
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	List<String> getIndicatorsWithSwedishData() throws DataFailureException;

}
