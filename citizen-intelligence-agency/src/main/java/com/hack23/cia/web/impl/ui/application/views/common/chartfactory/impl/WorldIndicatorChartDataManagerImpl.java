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
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.WorldIndicatorChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
public final class WorldIndicatorChartDataManagerImpl extends AbstractChartDataManagerImpl implements WorldIndicatorChartDataManager {

	/** The Constant YEAR_MONTH_DAY_FORMAT. */
	private static final String YEAR_MONTH_DAY_FORMAT = "%Y-%#m-%#d";

	/** The Constant NUMBER_TICKS_DATE. */
	private static final int NUMBER_TICKS_DATE = 8;


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

		final Axes axes = new Axes()
				.addAxis(new XYaxis().setRenderer(AxisRenderers.DATE)
						.setTickOptions(new AxisTickRenderer().setFormatString(YEAR_MONTH_DAY_FORMAT))
						.setNumberTicks(NUMBER_TICKS_DATE))
				.addAxis(new XYaxis(XYaxes.Y).setLabel(summary.getIndicatorName()));

		final Options options = new Options().addOption(new SeriesDefaults()).addOption(axes)
				.addOption(ChartOptionsImpl.INSTANCE.createHighLighterNorth()).addOption(series).addOption(ChartOptionsImpl.INSTANCE.createLegendOutside());

		addChart(content,"Country indicator" +summary.getIndicatorName(), new DCharts().setDataSeries(dataSeries).setOptions(options).show());
	}

}
