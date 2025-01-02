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
package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentBodyRankingPageVisitHistoryPageModContentFactoryImpl.
 */
@Component
public final class GovernmentBodyRankingPageVisitHistoryPageModContentFactoryImpl
		extends AbstractGovernmentBodyRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME;

	/**
	 * Instantiates a new government body ranking page visit history page mod
	 * content factory impl.
	 */
	public GovernmentBodyRankingPageVisitHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getGovernmentBodyRankingMenuItemFactory().createGovernmentBodyRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);

		createPageHeader(panel, panelContent, "Government Body Ranking Page Visit History", "Page Visit History", "Track the visit history of government body ranking pages.");

		getAdminChartDataManager().createApplicationActionEventPageModeDailySummaryChart(panelContent,NAME);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_GOVERNMENT_BODY_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters,PageMode.PAGEVISITHISTORY.toString());
	}

}
