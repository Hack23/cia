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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * The Interface GovernmentBodyChartDataManager.
 */
public interface GovernmentBodyChartDataManager {

	/**
	 * Creates the government body expenditure summary chart.
	 *
	 * @param panelContent
	 *            the panel content
	 */
	void createGovernmentBodyExpenditureSummaryChart(VerticalLayout panelContent);

	/**
	 * Creates the government body expenditure summary chart.
	 *
	 * @param panelContent
	 *            the panel content
	 * @param name
	 *            the name
	 */
	void createGovernmentBodyExpenditureSummaryChart(VerticalLayout panelContent, String name);

	/**
	 * Creates the government body headcount summary chart.
	 *
	 * @param panelContent
	 *            the panel content
	 */
	void createGovernmentBodyHeadcountSummaryChart(VerticalLayout panelContent);

	/**
	 * Creates the government body headcount summary chart.
	 *
	 * @param panelContent
	 *            the panel content
	 * @param name
	 *            the name
	 */
	void createGovernmentBodyHeadcountSummaryChart(VerticalLayout panelContent, String name);


	/**
	 * Creates the government body income summary chart.
	 *
	 * @param panelContent
	 *            the panel content
	 */
	void createGovernmentBodyIncomeSummaryChart(VerticalLayout panelContent);

	/**
	 * Creates the government body income summary chart.
	 *
	 * @param panelContent
	 *            the panel content
	 * @param name
	 *            the name
	 */
	void createGovernmentBodyIncomeSummaryChart(VerticalLayout panelContent, String name);

	/**
	 * Creates the ministry government body expenditure summary chart.
	 *
	 * @param content the content
	 */
	void createMinistryGovernmentBodyExpenditureSummaryChart(AbstractOrderedLayout content);

	/**
	 * Creates the ministry government body expenditure summary chart.
	 *
	 * @param panelContent the panel content
	 * @param name         the name
	 */
	void createMinistryGovernmentBodyExpenditureSummaryChart(VerticalLayout panelContent, String name);

	/**
	 * Creates the ministry government body headcount summary chart.
	 *
	 * @param content
	 *            the content
	 */
	void createMinistryGovernmentBodyHeadcountSummaryChart(AbstractOrderedLayout content);


	/**
	 * Creates the ministry government body headcount summary chart.
	 *
	 * @param content
	 *            the content
	 * @param name
	 *            the name
	 */
	void createMinistryGovernmentBodyHeadcountSummaryChart(AbstractOrderedLayout content,String name);

	/**
	 * Creates the ministry government body income summary chart.
	 *
	 * @param content the content
	 */
	void createMinistryGovernmentBodyIncomeSummaryChart(AbstractOrderedLayout content);

	/**
	 * Creates the ministry government body income summary chart.
	 *
	 * @param panelContent the panel content
	 * @param name         the name
	 */
	void createMinistryGovernmentBodyIncomeSummaryChart(VerticalLayout panelContent, String name);

}
