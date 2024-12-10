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

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CommitteeRolesPageModContentFactoryImpl.
 */
@Component
public final class PartyCommitteeRolesPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	private static final String[] COLUMN_ORDER = { "roleId", "personId", "firstName", "lastName", "detail",
			"active", "roleCode", "fromDate", "toDate", "totalDaysServed" };
	/** The Constant COMMITTEE_ROLES. */
	private static final String COMMITTEE_ROLES = "CommitteeRoles";
	private static final String[] HIDE_COLUMNS = { "roleId", "personId", "party" };
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.POLITICIAN_VIEW_NAME, "personId");

	/**
	 * Instantiates a new party committee roles page mod content factory impl.
	 */
	public PartyCommitteeRolesPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);
		final ViewRiksdagenParty viewRiksdagenParty = getItem(parameters);

		getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent, "Committee Roles " +viewRiksdagenParty.getPartyName(), "Committee Members", "Explore the roles and members of various committees within the party.");

		final DataContainer<ViewRiksdagenCommitteeRoleMember, String> committeeRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommitteeRoleMember.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenCommitteeRoleMember.class,
				committeeRoleMemberDataContainer.findListByProperty(
						new Object[] { viewRiksdagenParty.getPartyId(), Boolean.TRUE },
						ViewRiksdagenCommitteeRoleMember_.party, ViewRiksdagenCommitteeRoleMember_.active),
				COMMITTEE_ROLES, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		pageCompleted(parameters, panel, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PartyPageMode.COMMITTEEROLES.toString());
	}

}
