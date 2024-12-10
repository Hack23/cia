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
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class LeaderHistoryPageModContentFactoryImpl.
 */
@Component
public final class PartyLeaderHistoryPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	private static final String[] COLUMN_ORDER = { "roleId", "roleCode", "personId", "firstName",
			"lastName", "party", "totalDaysServed", "active", "detail", "fromDate", "toDate" };
	private static final String[] HIDE_COLUMNS = { "roleId", "personId", "party", "detail" };
	private static final String LEADER_HISTORY2 = "Leader History";
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.POLITICIAN_VIEW_NAME, "personId");

	/**
	 * Instantiates a new party leader history page mod content factory impl.
	 */
	public PartyLeaderHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);
		final ViewRiksdagenParty viewRiksdagenParty = getItem(parameters);
		getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent, "Leader History " + viewRiksdagenParty.getPartyName(), "Party Leaders", "Explore the history of party leaders and their roles.");

		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenPartyRoleMember.class,
				partyRoleMemberDataContainer.getAllBy(ViewRiksdagenPartyRoleMember_.party,
						viewRiksdagenParty.getPartyId()),
				LEADER_HISTORY2, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
		pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PartyPageMode.LEADERHISTORY.toString());
	}

}
