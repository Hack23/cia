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
package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.AdminChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AbstractPageModContentFactoryImpl.
 */
public abstract class AbstractPageModContentFactoryImpl implements PageModeContentFactory {

	/** The Constant GENERAL_PAGE_MODE_PAGE_VISIT. */
	public static final String GENERAL_PAGE_MODE_PAGE_VISIT = "General Page Mode Page Visit";

	/** The Constant CURRENT_PAGE_VISIT_HISTORY. */
	public static final String CURRENT_PAGE_VISIT_HISTORY = "Current Page Visit History";

	/** The admin chart data manager. */
	@Autowired
	private AdminChartDataManager adminChartDataManager;

	/**
	 * Instantiates a new abstract page mod content factory impl.
	 */
	protected AbstractPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the panel content.
	 *
	 * @return the vertical layout
	 */
	protected final VerticalLayout createPanelContent() {
		final VerticalLayout panelContent = new VerticalLayout();
		panelContent.setSizeFull();
		panelContent.setMargin(true);
		return panelContent;
	}


	/**
	 * Creates the page visit history.
	 *
	 * @param pageName
	 *            the page name
	 * @param pageId
	 *            the page id
	 * @param panelContent
	 *            the panel content
	 */
	protected final void createPageVisitHistory(final String pageName, final String pageId,
			final VerticalLayout panelContent) {
		final Label createHeader2Label = LabelFactory.createHeader2Label(CURRENT_PAGE_VISIT_HISTORY);
		panelContent.addComponent(createHeader2Label);
		final DCharts createApplicationActionEventPageElementDailySummaryChart = adminChartDataManager
				.createApplicationActionEventPageElementDailySummaryChart(pageName, pageId);
		panelContent.addComponent(createApplicationActionEventPageElementDailySummaryChart);

		final Label createHeader2Label2 = LabelFactory.createHeader2Label(GENERAL_PAGE_MODE_PAGE_VISIT);
		panelContent.addComponent(createHeader2Label2);
		final DCharts createApplicationActionEventPageModeDailySummaryChart = adminChartDataManager
				.createApplicationActionEventPageModeDailySummaryChart(pageName);
		panelContent.addComponent(createApplicationActionEventPageModeDailySummaryChart);

		panelContent.setExpandRatio(createHeader2Label, ContentRatio.SMALL);
		panelContent.setExpandRatio(createApplicationActionEventPageElementDailySummaryChart, ContentRatio.GRID);
		panelContent.setExpandRatio(createHeader2Label2, ContentRatio.SMALL);
		panelContent.setExpandRatio(createApplicationActionEventPageModeDailySummaryChart, ContentRatio.GRID);
	}


}
