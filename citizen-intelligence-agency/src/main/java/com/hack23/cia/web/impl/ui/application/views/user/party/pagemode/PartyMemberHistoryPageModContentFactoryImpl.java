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
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyMember;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyMember_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MemberHistoryPageModContentFactoryImpl.
 */
@Component
public final class PartyMemberHistoryPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { "id", "firstName", "lastName", "activityLevel", "activityProfile", "collaborationPercentage", "individualMotions", "partyMotions",
			"committeeMotions", "multiPartyMotions", "documentsLastYear", "totalDocuments", "status"
			 };

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "id","partyId", "phoneNumber", "postCode", "registeredDate", "shortCode", "website",
			"gender", "hangarGuid", "imageUrl192", "imageUrl80", "imageUrlMax","coAddress","city","hjid",
			"personUrlXml", "place", "personAssignmentData", "personDetailData","party", "partyName","email","faxNumber","address","bornYear" };

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.POLITICIAN_VIEW_NAME, "id");

	/** The Constant POLITICIANS. */
	private static final String POLITICIANS = "Politicians";

	/**
	 * Instantiates a new party member history page mod content factory impl.
	 */
	public PartyMemberHistoryPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the content.
	 *
	 * @param parameters the parameters
	 * @param menuBar the menu bar
	 * @param panel the panel
	 * @return the layout
	 */
	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenParty viewRiksdagenParty = getItem(parameters);
		getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent,
			PartyViewConstants.MEMBER_HISTORY_HEADER + " " + viewRiksdagenParty.getPartyName(),
			PartyViewConstants.MEMBER_HISTORY_TITLE,
			PartyViewConstants.MEMBER_HISTORY_DESC);

		final DataContainer<ViewRiksdagenPartyMember, String> partyMembernDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyMember.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenPartyMember.class,
				partyMembernDataContainer.getAllBy(ViewRiksdagenPartyMember_.party, viewRiksdagenParty.getPartyId()),
				POLITICIANS, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
		pageId);
		return panelContent;

	}

	/**
	 * Matches.
	 *
	 * @param page the page
	 * @param parameters the parameters
	 * @return true, if successful
	 */
	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandPartyConstants.COMMAND_PARTY_MEMBER_HISTORY.matches(page, parameters);
	}

}
