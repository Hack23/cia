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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api;

import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;

/**
 * The Interface ChartOptions.
 */
public interface ChartOptions {

	/**
	 * Creates the options country line chart.
	 *
	 * @param series
	 *            the series
	 * @return the options
	 */
	Options createOptionsCountryLineChart(Series series);

	/**
	 * Creates the options donout chart.
	 *
	 * @return the options
	 */
	Options createOptionsDonoutChart();

	/**
	 * Creates the options donout chart with series.
	 *
	 * @param series
	 *            the series
	 * @return the options
	 */
	Options createOptionsDonoutChartWithSeries(Series series);

	/**
	 * Creates the options party line chart.
	 *
	 * @param series
	 *            the series
	 * @return the options
	 */
	Options createOptionsPartyLineChart(Series series);

	/**
	 * Creates the options person line chart.
	 *
	 * @param series
	 *            the series
	 * @return the options
	 */
	Options createOptionsPersonLineChart(Series series);

	/**
	 * Creates the options pie chart.
	 *
	 * @return the options
	 */
	Options createOptionsPieChart();

	/**
	 * Creates the options XY date float legend inside one column.
	 *
	 * @param series
	 *            the series
	 * @return the options
	 */
	Options createOptionsXYDateFloatLegendInsideOneColumn(Series series);

	/**
	 * Creates the options XY date float log Y axis legend outside.
	 *
	 * @param series
	 *            the series
	 * @return the options
	 */
	Options createOptionsXYDateFloatLogYAxisLegendOutside(Series series);

}
