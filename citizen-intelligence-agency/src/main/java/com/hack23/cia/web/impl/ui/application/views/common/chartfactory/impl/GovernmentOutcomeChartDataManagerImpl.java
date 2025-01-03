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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentOperationPeriodOutcome;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GovernmentOutcomeChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class GovernmentOutcomeChartDataManagerImpl.
 */
@Service
public final class GovernmentOutcomeChartDataManagerImpl extends AbstractChartDataManagerImpl
		implements GovernmentOutcomeChartDataManager {

	/** The esv api. */
	@Autowired
	private EsvApi esvApi;

	/**
	 * Instantiates a new government outcome chart data manager impl.
	 */
	public GovernmentOutcomeChartDataManagerImpl() {
		super();
	}

	/**
	 * Creates the period data.
	 *
	 * @param outcomeMap the outcome map
	 * @param variables the variables
	 * @param chartDataSeries the chart data series
	 * @param chartSeries the chart series
	 */
	private static void createPeriodData(final Map<String, List<GovernmentOperationPeriodOutcome>> outcomeMap, final GovernmentOperationPeriodOutcome.Variables variables,final DataSeries chartDataSeries,
			final Series chartSeries) {
		chartSeries.addSeries(new XYseries().setLabel(variables.toString()));
		chartDataSeries.newSeries();

		final List<GovernmentOperationPeriodOutcome> list = outcomeMap.get(variables.toString());
		Collections.sort(list);
		for (final GovernmentOperationPeriodOutcome entry : list) {

			chartDataSeries.add(entry.getPeriod() + "-01", entry.getValue());
		}
	}

	/**
	 * Creates the government outcome chart.
	 *
	 * @param layout the layout
	 */
	@Override
	public void createGovernmentOutcomeChart(final AbstractOrderedLayout layout) {
		final Map<String, List<GovernmentOperationPeriodOutcome>> outcomeMap = esvApi.getReport();
		final DataSeries chartDataSeries = new DataSeries();
		final Series chartSeries = new Series();

		createPeriodData(outcomeMap,GovernmentOperationPeriodOutcome.Variables.TOTAL_REVENUE, chartDataSeries, chartSeries);
		createPeriodData(outcomeMap,GovernmentOperationPeriodOutcome.Variables.TOTAL_EXPENDITURES, chartDataSeries, chartSeries);

		addChart(layout, "GovernmentOperationPeriodOutcome",
				new DCharts().setDataSeries(chartDataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(chartSeries)).show(),
				true);

	}

}
