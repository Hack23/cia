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
package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.MinistryDataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.PartyDataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MinistryRankingChartsPageModContentFactoryImpl.
 */
@Service
public final class MinistryRankingChartsPageModContentFactoryImpl extends AbstractMinistryRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.MINISTRY_RANKING_VIEW_NAME;

	/** The Constant CHARTS. */
	private static final String CHARTS = "Charts:";

	/** The chart data manager. */
	@Autowired
	private ChartDataManager chartDataManager;

	/** The data series factory. */
	@Autowired
	private MinistryDataSeriesFactory dataSeriesFactory;

	/** The data series factory2. */
	@Autowired
	private PartyDataSeriesFactory dataSeriesFactory2;

	/**
	 * Instantiates a new ministry ranking charts page mod content factory impl.
	 */
	public MinistryRankingChartsPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (!StringUtils.isEmpty(parameters) && parameters.contains(PageMode.CHARTS.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getMinistryRankingMenuItemFactory().createMinistryRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);


		final Layout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();

		final Component chartPanelAll = chartDataManager.createChartPanel(dataSeriesFactory.createMinistryChartTimeSeriesAll(), "All");
		if (chartPanelAll != null) {
			chartLayout.addComponent(chartPanelAll);
		}

		final Component chartPanelCurrent = chartDataManager.createChartPanel(dataSeriesFactory.createMinistryChartTimeSeriesCurrent(),
				"Current");
		if (chartPanelCurrent != null) {
			chartLayout.addComponent(chartPanelCurrent);
		}
		panelContent.addComponent(chartLayout);

		final Layout extraChartLayout = createExtraChartLayout();
		if (extraChartLayout != null) {
			panelContent.addComponent(extraChartLayout);
		}


		panel.setCaption(CHARTS + parameters);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	/**
	 * Creates the extra chart layout.
	 *
	 * @return the layout
	 */
	private Layout createExtraChartLayout() {
		final Layout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();

		final Component chartPanelAll = chartDataManager.createChartPanel(
				dataSeriesFactory.createChartTimeSeriesTotalDaysServedGovernmentByParty(),
				"All Parties, total days served");
		if (chartPanelAll != null) {
			chartLayout.addComponent(chartPanelAll);
		}

		final Component chartPanelCurrent = chartDataManager.createChartPanel(
				dataSeriesFactory2.createChartTimeSeriesCurrentGovernmentByParty(), "Current Parties, headcount");
		if (chartPanelCurrent != null) {
			chartLayout.addComponent(chartPanelCurrent);
		}

		return chartLayout;
	}





}
