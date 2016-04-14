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
package com.hack23.cia.web.impl.ui.application.views.user.common;

import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.web.impl.ui.application.action.PageActionEventHelper;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.AdminChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AbstractUserView.
 */
public abstract class AbstractUserView extends Panel implements View {

	/** The Constant GENERAL_PAGE_MODE_PAGE_VISIT. */
	public static final String GENERAL_PAGE_MODE_PAGE_VISIT = "General Page Mode Page Visit";

	/** The Constant CURRENT_PAGE_VISIT_HISTORY. */
	public static final String CURRENT_PAGE_VISIT_HISTORY = "Current Page Visit History";

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The barmenu. */
	private final MenuBar barmenu = new MenuBar();

	/** The panel. */
	private Panel panel;

	/** The page link factory. */
	@Autowired
	protected transient PageLinkFactory pageLinkFactory;

	/** The page action event helper. */
	@Autowired
	protected transient PageActionEventHelper pageActionEventHelper;

	/** The admin chart data manager. */
	@Autowired
	private transient AdminChartDataManager adminChartDataManager;



	/**
	 * Instantiates a new abstract user view.
	 */
	protected AbstractUserView() {
		super();
	}

	/**
	 * Creates the basic layout with panel and footer.
	 *
	 * @param panelName
	 *            the panel name
	 */
	protected final void createBasicLayoutWithPanelAndFooter(final String panelName) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		final VerticalLayout pageModeContent = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		layout.addComponent(pageModeContent);

		pageModeContent.addComponent(new Label(OVERVIEW));
		pageModeContent.addComponent(barmenu);

		panel = new Panel(panelName);

		panel.setSizeFull();
		pageModeContent.addComponent(panel);
		pageModeContent.setExpandRatio(panel, ContentRatio.FULL_SIZE);

		pageModeContent.addComponent(pageLinkFactory.createMainViewPageLink());
		setContent(layout);

		pageModeContent.setWidth(100, Unit.PERCENTAGE);
		pageModeContent.setHeight(100, Unit.PERCENTAGE);

		layout.setWidth(100, Unit.PERCENTAGE);
		layout.setHeight(100, Unit.PERCENTAGE);
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);


	}

	/**
	 * Gets the barmenu.
	 *
	 * @return the barmenu
	 */
	public final MenuBar getBarmenu() {
		return barmenu;
	}


	/**
	 * Gets the panel.
	 *
	 * @return the panel
	 */
	protected final Panel getPanel() {
		return panel;
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
	protected final void createPageVisitHistory(final String pageName, final String pageId, final VerticalLayout panelContent) {
		final Label createHeader2Label = LabelFactory.createHeader2Label(CURRENT_PAGE_VISIT_HISTORY);
		panelContent.addComponent(createHeader2Label);
		final DCharts createApplicationActionEventPageElementDailySummaryChart = adminChartDataManager.createApplicationActionEventPageElementDailySummaryChart(pageName,pageId);
		panelContent.addComponent(createApplicationActionEventPageElementDailySummaryChart);

		final Label createHeader2Label2 = LabelFactory.createHeader2Label(GENERAL_PAGE_MODE_PAGE_VISIT);
		panelContent.addComponent(createHeader2Label2);
		final DCharts createApplicationActionEventPageModeDailySummaryChart = adminChartDataManager.createApplicationActionEventPageModeDailySummaryChart(pageName);
		panelContent.addComponent(createApplicationActionEventPageModeDailySummaryChart);

		panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
		panelContent.setExpandRatio(createApplicationActionEventPageElementDailySummaryChart, ContentRatio.GRID);
		panelContent.setExpandRatio(createHeader2Label2,ContentRatio.SMALL);
		panelContent.setExpandRatio(createApplicationActionEventPageModeDailySummaryChart, ContentRatio.GRID);
	}

}
