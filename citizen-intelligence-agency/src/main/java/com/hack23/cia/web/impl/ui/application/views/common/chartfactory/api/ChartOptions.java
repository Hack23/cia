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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api;

import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;

/**
 * The Interface ChartOptions.
 */
public interface ChartOptions {

	/**
	 * Creates the options xy date float legend outside.
	 *
	 * @param series
	 *            the series
	 * @return the options
	 */
	Options createOptionsXYDateFloatLegendOutside(Series series);

	Options createOptions2(SeriesDefaults seriesDefaults);

	Options createOptions(Series series, SeriesDefaults seriesDefaults);

	Options createOptions3();

	Options createOptions4(Series series);

	Options createOptions5(Series series);

	Options createOptions6(String label, Series series);

}
