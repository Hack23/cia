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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory;

import org.dussan.vaadin.dcharts.DCharts;

/**
 * The Interface ChartDataManager.
 */
public interface AdminChartDataManager {


	/**
	 * Creates the application action event page daily summary chart.
	 *
	 * @return the d charts
	 */
	DCharts createApplicationActionEventPageDailySummaryChart();


	/**
	 * Creates the application action event page mode daily summary chart.
	 *
	 * @param page
	 *            the page
	 * @return the d charts
	 */
	DCharts createApplicationActionEventPageModeDailySummaryChart(String page);

	/**
	 * Creates the application action event page element daily summary chart.
	 *
	 * @param page
	 *            the page
	 * @param elementId
	 *            the element id
	 * @return the d charts
	 */
	DCharts createApplicationActionEventPageElementDailySummaryChart(String page, String elementId);

}
