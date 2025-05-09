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
package com.hack23.cia.web.impl.ui.application.views.user.committeeranking.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCommitteeRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode.CommitteeViewConstants;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CommitteeRankingOverviewPageModContentFactoryImpl.
 */
@Component
public final class CommitteeRankingOverviewPageModContentFactoryImpl extends AbstractCommitteeRankingPageModContentFactoryImpl {

	/** The Constant COMMITTEE_RANKING_BY_TOPIC. */

	/** The Constant NAME. */
	public static final String NAME = UserViews.COMMITTEE_RANKING_VIEW_NAME;

	/**
	 * Instantiates a new committee ranking overview page mod content factory
	 * impl.
	 */
	public CommitteeRankingOverviewPageModContentFactoryImpl() {
		super();
	}


	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getCommitteeRankingMenuItemFactory().createCommitteeeRankingMenuBar(menuBar);
		CardInfoRowUtil.createPageHeader(panel, panelContent,
			CommitteeViewConstants.CR_OVERVIEW_TITLE_HEADER,
			CommitteeViewConstants.CR_OVERVIEW_TITLE,
			CommitteeViewConstants.CR_OVERVIEW_DESCRIPTION);

		final String pageId = getPageId(parameters);

		getCommitteeRankingMenuItemFactory().createOverviewPage(panelContent);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}


	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_OVERVIEW.matches(page, parameters);
	}


}
