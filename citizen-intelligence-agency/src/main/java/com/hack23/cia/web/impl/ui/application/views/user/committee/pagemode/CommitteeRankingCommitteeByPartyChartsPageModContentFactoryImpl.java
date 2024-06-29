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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.CommitteeDataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CommitteeRankingCommitteeByPartyChartsPageModContentFactoryImpl.
 */
@Service
public final class CommitteeRankingCommitteeByPartyChartsPageModContentFactoryImpl
		extends AbstractCommitteeRankingPageModContentFactoryImpl {

	/** The Constant ALL_PARTIES_TOTAL_DAYS_SERVED. */
	private static final String ALL_PARTIES_TOTAL_DAYS_SERVED = "All Parties, total days served";

	/** The Constant CHARTS. */
	private static final String CHARTS = "Charts:";

	/** The Constant NAME. */
	public static final String NAME = UserViews.COMMITTEE_RANKING_VIEW_NAME;

	/** The chart data manager. */
	@Autowired
	private ChartDataManager chartDataManager;

	/** The data series factory. */
	@Autowired
	private CommitteeDataSeriesFactory dataSeriesFactory;

	/**
	 * Instantiates a new committee ranking committee by party charts page mod
	 * content factory impl.
	 */
	public CommitteeRankingCommitteeByPartyChartsPageModContentFactoryImpl() {
		super();
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
				dataSeriesFactory.createChartTimeSeriesTotalDaysServedCommitteeByParty(),
				ALL_PARTIES_TOTAL_DAYS_SERVED);

		panelContent.addComponent(chartLayout);

		panel.setCaption(NAME + "::" + CHARTS + parameters);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.COMMITTEESBYPARTY.toString()) && !parameters.contains(ChartIndicators.CURRENTCOMMITTEESBYPARTYDAYSSERVED.toString());
	}

}
