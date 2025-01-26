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
package com.hack23.cia.web.impl.ui.application.views.user.politicianranking.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.PartyDataSeriesFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PoliticianRankingChartsCurrentPartiesPageModContentFactoryImpl.
 */
@Service
public final class PoliticianRankingChartsCurrentPartiesPageModContentFactoryImpl
		extends AbstractPoliticianRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.POLITICIAN_RANKING_VIEW_NAME;

	/** The chart data manager. */
	@Autowired
	private ChartDataManager chartDataManager;

	/** The data series factory. */
	@Autowired
	private PartyDataSeriesFactory dataSeriesFactory;

	/**
	 * Instantiates a new politician ranking charts current parties page mod
	 * content factory impl.
	 */
	public PoliticianRankingChartsCurrentPartiesPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		getPoliticianRankingMenuItemFactory().createPoliticianRankingMenuBar(menuBar);
		CardInfoRowUtil.createPageHeader(panel, panelContent,
			    PoliticianRankingDescriptionConstants.CURRENT_PARTIES_HEADER,
			    PoliticianRankingDescriptionConstants.CURRENT_PARTIES_SUBTITLE,
			    PoliticianRankingDescriptionConstants.CURRENT_PARTIES_DESC);

		chartDataManager.createChartPanel(panelContent,
		    dataSeriesFactory.createPartyChartTimeSeriesCurrent(),
		    PoliticianRankingConstants.CHART_TYPE_CURRENT);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page)
				&& StringUtils.contains(parameters, PageMode.CHARTS.toString()) && parameters.contains(ChartIndicators.CURRENTPARTIES.toString());
	}

}
