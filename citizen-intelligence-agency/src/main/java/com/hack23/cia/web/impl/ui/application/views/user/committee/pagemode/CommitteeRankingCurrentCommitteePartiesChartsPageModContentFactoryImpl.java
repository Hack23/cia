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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.PartyDataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class
 * CommitteeRankingCurrentCommitteePartiesChartsPageModContentFactoryImpl.
 */
@Service
public final class CommitteeRankingCurrentCommitteePartiesChartsPageModContentFactoryImpl
		extends AbstractCommitteeRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.COMMITTEE_RANKING_VIEW_NAME;

	/** The Constant CHARTS. */
	private static final String CHARTS = "Charts:";

	/** The Constant CURRENT_PARTIES_HEADCOUNT. */
	private static final String CURRENT_PARTIES_HEADCOUNT = "Current Parties, headcount";

	/** The chart data manager. */
	@Autowired
	private ChartDataManager chartDataManager;

	/** The data series factory 2. */
	@Autowired
	private PartyDataSeriesFactory dataSeriesFactory2;

	/**
	 * Instantiates a new committee ranking current committee parties charts
	 * page mod content factory impl.
	 */
	public CommitteeRankingCurrentCommitteePartiesChartsPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && !StringUtils.isEmpty(parameters) && parameters.contains(PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.CURRENTCOMMITTEESBYPARTYHEADCOUNT.toString());
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getCommitteeRankingMenuItemFactory().createCommitteeeRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);

		final HorizontalLayout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();

		chartDataManager.createChartPanel(chartLayout,
				dataSeriesFactory2.createChartTimeSeriesCurrentCommitteeByParty(), CURRENT_PARTIES_HEADCOUNT);

		panelContent.addComponent(chartLayout);

		panel.setCaption(NAME + "::" + CHARTS + parameters);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

}
