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
package com.hack23.cia.web.impl.ui.application.views.user.partyranking.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.PartyDataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PartyRankingChartsPageModContentFactoryImpl.
 */
@Service
public final class PartyRankingAllPartiesChartsPageModContentFactoryImpl extends AbstractPartyRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_RANKING_VIEW_NAME;

	/** The chart data manager. */
	@Autowired
	private ChartDataManager chartDataManager;

	/** The data series factory. */
	@Autowired
	private PartyDataSeriesFactory dataSeriesFactory;

	/**
	 * Instantiates a new party ranking all parties charts page mod content
	 * factory impl.
	 */
	public PartyRankingAllPartiesChartsPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		getPartyRankingMenuItemFactory().createPartyRankingMenuBar(menuBar);

		CardInfoRowUtil.createPageHeader(panel, panelContent, "All Parties Charts", "Party Performance", "Analyze the performance of all political parties using various charts.");

		final HorizontalLayout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();

		chartDataManager.createChartPanel(chartLayout,dataSeriesFactory.createPartyChartTimeSeriesAll(),"Number of party members having roles in Parliament or Government Since 1983");

		panelContent.addComponent(chartLayout);
		panelContent.setExpandRatio(chartLayout,ContentRatio.LARGE_FORM);



		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.ALLPARTIES.toString());
	}


}
