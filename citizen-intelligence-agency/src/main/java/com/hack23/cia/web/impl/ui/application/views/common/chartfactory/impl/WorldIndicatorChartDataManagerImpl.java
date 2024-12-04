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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.worldbank.data.impl.Country;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.WorldIndicatorChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
public final class WorldIndicatorChartDataManagerImpl extends AbstractChartDataManagerImpl implements WorldIndicatorChartDataManager {

	/**
	 * Instantiates a new world indicator chart data manager impl.
	 */
	public WorldIndicatorChartDataManagerImpl() {
		super();
	}


	@Override
	public void createIndicatorChart(final AbstractOrderedLayout content,final List<WorldBankData> list,
			final ViewWorldbankIndicatorDataCountrySummary summary) {

		final Map<Country, List<WorldBankData>> countryIndicatorsMap = list.stream()
				.collect(Collectors.groupingBy(WorldBankData::getCountry, Collectors.toList()));


		final Series series = new Series();
		final DataSeries dataSeries = new DataSeries();


		for (final Entry<Country, List<WorldBankData>> entry : countryIndicatorsMap.entrySet() ) {
			series.addSeries(new XYseries().setLabel(entry.getKey().getValue()));

			dataSeries.newSeries();

			for (final WorldBankData item : entry.getValue()) {
				if (item != null && item.getYearDate() != null && item.getDataValue() != null
						&& !item.getDataValue().isEmpty()) {
					dataSeries.add(item.getYearDate() + "-01-01", Float.valueOf(item.getDataValue()));
				}
			}
		}

		addChart(content,summary.getSourceNote(), new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsCountryLineChart(series)).show(), false);
	}


}
