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
package com.hack23.cia.service.component.agent.impl.worldbank.workgenerator;

import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankIndicatorApi;

/**
 * The Class WorldBankIndicatorWorkGeneratorImpl.
 */
@Service("WorldBankIndicatorWorkGeneratorImpl")
final class WorldBankIndicatorWorkGeneratorImpl extends AbstractWorldBankDataSourcesWorkGenerator {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WorldBankIndicatorWorkGeneratorImpl.class);

	/** The indicator element workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement")
	private Destination indicatorElementWorkdestination;

	/** The worldbank indicator api. */
	@Autowired
	private WorldBankIndicatorApi worldbankIndicatorApi;


	/**
	 * Instantiates a new world bank indicator work generator impl.
	 */
	public WorldBankIndicatorWorkGeneratorImpl() {
		super(WorldBankDataSources.INDICATORS);
	}

	@Override
	public void generateWorkOrders() {
		try {
			final List<IndicatorElement> list =worldbankIndicatorApi.getIndicators();

			final Map<String, String> currentSaved = getImportService()
					.getWorldBankIndicatorElementMap();

			for (final IndicatorElement element : list) {
				if (!currentSaved.containsKey(element.getId())) {
					getJmsSender().send(indicatorElementWorkdestination,
							element);
				}
			}
		} catch (final DataFailureException exception) {
			LOGGER.warn("Problem generating indicator work orders", exception);
		}
	}

}
