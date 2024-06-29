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
package com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api;

import org.dussan.vaadin.dcharts.data.DataSeries;

/**
 * A factory for creating DataSeries objects.
 */
public interface MinistryDataSeriesFactory {

	/**
	 * Creates a new MinistryDataSeries object.
	 *
	 * @return the data series
	 */
	DataSeries createChartTimeSeriesTotalDaysServedGovernmentByParty();

	/**
	 * Creates a new MinistryDataSeries object.
	 *
	 * @return the data series
	 */
	DataSeries createMinistryChartTimeSeriesAll();

	/**
	 * Creates a new MinistryDataSeries object.
	 *
	 * @return the data series
	 */
	DataSeries createMinistryChartTimeSeriesCurrent();

}
