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

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * The Interface ChartDataManager.
 */
public interface AdminChartDataManager {


	/**
	 * Creates the application action event page daily summary chart.
	 *
	 * @param content
	 *            the content
	 */
	void createApplicationActionEventPageDailySummaryChart(AbstractOrderedLayout content);


	/**
	 * Creates the application action event page element daily summary chart.
	 *
	 * @param content
	 *            the content
	 * @param page
	 *            the page
	 * @param elementId
	 *            the element id
	 */
	void createApplicationActionEventPageElementDailySummaryChart(AbstractOrderedLayout content,String page, String elementId);

	/**
	 * Creates the application action event page mode daily summary chart.
	 *
	 * @param content
	 *            the content
	 * @param page
	 *            the page
	 */
	void createApplicationActionEventPageModeDailySummaryChart(AbstractOrderedLayout content,String page);


	/**
	 * Creates the application session page daily summary chart.
	 *
	 * @param content the content
	 */
	void createApplicationSessionPageDailySummaryChart(VerticalLayout content);

}
