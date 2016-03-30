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

import java.util.List;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.data.DataSeries;

import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;

/**
 * The Interface ChartDataManager.
 */
public interface ChartDataManager {


	/**
	 * Creates the party winner chart.
	 *
	 * @return the d charts
	 */
	DCharts createPartyWinnerChart();

	/**
	 * Creates the document type chart.
	 *
	 * @return the d charts
	 */
	DCharts createDocumentTypeChart();

	/**
	 * Creates the decision type chart.
	 *
	 * @return the d charts
	 */
	DCharts createDecisionTypeChart();

	/**
	 * Creates the application action event page daily summary chart.
	 *
	 * @return the d charts
	 */
	DCharts createApplicationActionEventPageDailySummaryChart();


	/**
	 * Creates the decision type chart.
	 *
	 * @param org
	 *            the org
	 * @return the d charts
	 */
	DCharts createDecisionTypeChart(String org);

	/**
	 * Creates the document history chart by org.
	 *
	 * @param org
	 *            the org
	 * @return the d charts
	 */
	DCharts createDocumentHistoryChartByOrg(String org);

	/**
	 * Creates the party line chart.
	 *
	 * @param partyId
	 *            the party id
	 * @return the d charts
	 */
	DCharts createPartyLineChart(String partyId);

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

	/**
	 * Creates the person line chart.
	 *
	 * @param personId
	 *            the person id
	 * @return the d charts
	 */
	DCharts createPersonLineChart(String personId);

	/**
	 * Creates the indicator chart.
	 *
	 * @param list
	 *            the list
	 * @param summary
	 *            the summary
	 * @return the d charts
	 */
	DCharts createIndicatorChart(List<WorldBankData> list, ViewWorldbankIndicatorDataCountrySummary summary);

	/**
	 * Creates the chart panel.
	 *
	 * @param dataSeries
	 *            the data series
	 * @param caption
	 *            the caption
	 * @return the d charts
	 */
	DCharts createChartPanel(DataSeries dataSeries, String caption);

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
