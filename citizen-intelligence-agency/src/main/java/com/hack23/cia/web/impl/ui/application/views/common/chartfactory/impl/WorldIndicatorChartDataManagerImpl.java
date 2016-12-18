/*
 * Copyright 2014 James Pether Sörling
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

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartOptions;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.WorldIndicatorChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
public final class WorldIndicatorChartDataManagerImpl extends AbstractChartDataManagerImpl implements WorldIndicatorChartDataManager {

	/** The chart options. */
	@Autowired
	private static ChartOptions chartOptions;


	/**
	 * Instantiates a new world indicator chart data manager impl.
	 */
	public WorldIndicatorChartDataManagerImpl() {
		super();
	}


	@Override
	public void createIndicatorChart(final AbstractOrderedLayout content,final List<WorldBankData> list,
			final ViewWorldbankIndicatorDataCountrySummary summary) {
		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		series.addSeries(new XYseries().setLabel(summary.getIndicatorName()));

		dataSeries.newSeries();

		for (final WorldBankData item : list) {
			if (item != null && item.getYearDate() != null && item.getDataValue() != null
					&& !item.getDataValue().isEmpty()) {
				dataSeries.add(item.getYearDate() + "-01-01", Float.valueOf(item.getDataValue()));
			}
		}

		addChart(content,"Country indicator" +summary.getIndicatorName(), new DCharts().setDataSeries(dataSeries).setOptions(chartOptions.createOptions6(summary.getIndicatorName(), series)).show());
	}


}
