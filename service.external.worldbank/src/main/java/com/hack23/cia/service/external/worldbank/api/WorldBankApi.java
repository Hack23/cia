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
package com.hack23.cia.service.external.worldbank.api;

import java.util.List;

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.model.external.worldbank.topic.impl.TopicElement;



/**
 * The Interface WorldBankApi.
 */
public interface WorldBankApi {

	/**
	 * Gets the countries.
	 *
	 * @return the countries
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	List<CountryElement> getCountries() throws DataFailureException;

	/**
	 * Gets the data.
	 *
	 * @param countryCode
	 *            the country code
	 * @param indicatorId
	 *            the indicator id
	 * @return the data
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	List<WorldBankData> getData(String countryCode, String indicatorId) throws DataFailureException;

	/**
	 * Gets the indicators.
	 *
	 * @return the indicators
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	List<IndicatorElement> getIndicators() throws DataFailureException;


	/**
	 * Gets the topics.
	 *
	 * @return the topics
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	List<TopicElement> getTopics() throws DataFailureException;



}
