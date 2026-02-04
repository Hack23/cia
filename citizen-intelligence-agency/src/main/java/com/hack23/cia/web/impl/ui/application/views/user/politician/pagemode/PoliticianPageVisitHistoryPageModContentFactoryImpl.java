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
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPoliticianConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class OverviewPageModContentFactoryImpl.
 */
@Component
public final class PoliticianPageVisitHistoryPageModContentFactoryImpl
		extends AbstractPoliticianPageModContentFactoryImpl {

	/**
	 * Instantiates a new politician page visit history page mod content factory
	 * impl.
	 */
	public PoliticianPageVisitHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenPolitician viewRiksdagenPolitician = getItem(parameters);
		getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent,
				PoliticianPageTitleConstants.PAGEVISIT_TITLE + viewRiksdagenPolitician.getFirstName() + ' '
						+ viewRiksdagenPolitician.getLastName() + '(' + viewRiksdagenPolitician.getParty() + ')',
				PoliticianDescriptionConstants.VISIT_TRENDS_TITLE, PoliticianDescriptionConstants.VISIT_HISTORY_DESC);

		createPageVisitHistory(NAME, pageId, panelContent);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
				UserViews.POLITICIAN_VIEW_NAME, parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandPoliticianConstants.COMMAND_POLITICIAN_PAGEVISIT.matches(page, parameters);
	}
}
