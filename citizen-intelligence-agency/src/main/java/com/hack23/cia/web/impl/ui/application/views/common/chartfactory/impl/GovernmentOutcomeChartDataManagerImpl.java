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

	private static void createPeriodData(final Map<String, List<GovernmentOperationPeriodOutcome>> map, final GovernmentOperationPeriodOutcome.Variables variables,final DataSeries dataSeries,
			final Series series) {
		series.addSeries(new XYseries().setLabel(variables.toString()));
		dataSeries.newSeries();

		final List<GovernmentOperationPeriodOutcome> list = map.get(variables.toString());
		Collections.sort(list);
		for (final GovernmentOperationPeriodOutcome entry : list) {

			dataSeries.add(entry.getPeriod() + "-01", entry.getValue());
		}
	}

	@Override
	public void createGovernmentOutcomeChart(final AbstractOrderedLayout content) {
		final Map<String, List<GovernmentOperationPeriodOutcome>> map = esvApi.getReport();
		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		createPeriodData(map,GovernmentOperationPeriodOutcome.Variables.TOTAL_REVENUE, dataSeries, series);
		createPeriodData(map,GovernmentOperationPeriodOutcome.Variables.TOTAL_EXPENDITURES, dataSeries, series);

		addChart(content, "GovernmentOperationPeriodOutcome",
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);

	}

}
