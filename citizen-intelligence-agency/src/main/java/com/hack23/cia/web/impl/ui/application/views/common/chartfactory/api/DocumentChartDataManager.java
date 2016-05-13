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

import org.dussan.vaadin.dcharts.DCharts;

/**
 * The Interface ChartDataManager.
 */
public interface DocumentChartDataManager {


	/**
	 * Creates the document type chart.
	 *
	 * @return the d charts
	 */
	DCharts createDocumentTypeChart();

	/**
	 * Creates the document history chart by org.
	 *
	 * @param org
	 *            the org
	 * @return the d charts
	 */
	DCharts createDocumentHistoryChartByOrg(String org);

	/**
	 * Creates the document history party chart.
	 *
	 * @param org
	 *            the org
	 * @return the d charts
	 */
	DCharts createDocumentHistoryPartyChart(String org);

	/**
	 * Creates the person document history chart.
	 *
	 * @param personId
	 *            the person id
	 * @return the d charts
	 */
	DCharts createPersonDocumentHistoryChart(String personId);

}
