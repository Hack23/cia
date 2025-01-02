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

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
public final class ChartDataManagerImpl extends AbstractChartDataManagerImpl implements ChartDataManager {

	/**
	 * Instantiates a new chart data manager impl.
	 */
	public ChartDataManagerImpl() {
		super();
	}


	@Override
	public void createChartPanel(final AbstractOrderedLayout layout,final DataSeries chartDataSeries, final String chartCaption) {

		final DCharts chart = new DCharts().setDataSeries(chartDataSeries).setOptions(getChartOptions().createOptionsPieChart());

		chart.show();

		addChart(layout,chartCaption, chart, true);

	}


}
