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

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentRolesPageModContentFactoryImpl.
 */
@Component
public final class PartyGovernmentRolesPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/** The Constant PERSON_ID. */
	private static final String PERSON_ID = "personId";

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { "roleId", PERSON_ID, "firstName", "lastName", "active",
			"detail", "roleCode", "fromDate", "toDate", "totalDaysServed" };
	/** The Constant GOVERNMENT_ROLES. */
	private static final String GOVERNMENT_ROLES = "Government Roles";

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "roleId", PERSON_ID, "party" };

	/**
	 * Instantiates a new party government roles page mod content factory impl.
	 */
	public PartyGovernmentRolesPageModContentFactoryImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenParty viewRiksdagenParty = getItem(parameters);
		getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent, PartyViewConstants.GOVERNMENT_ROLES_HEADER + viewRiksdagenParty.getPartyName(), PartyViewConstants.GOVERNMENT_ROLES_TITLE, PartyViewConstants.GOVERNMENT_ROLES_DESC);

		final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenGovermentRoleMember.class,
				govermentRoleMemberDataContainer.findListByProperty(
						new Object[] { viewRiksdagenParty.getPartyId(), Boolean.TRUE },
						ViewRiksdagenGovermentRoleMember_.party, ViewRiksdagenGovermentRoleMember_.active),
				GOVERNMENT_ROLES, COLUMN_ORDER, HIDE_COLUMNS,
				new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME, PERSON_ID), null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
		pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandPartyConstants.COMMAND_PARTY_GOVERNMENT_ROLES.matches(page, parameters);
	}

}
