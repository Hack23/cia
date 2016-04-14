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
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MemberHistoryPageModContentFactoryImpl.
 */
@Component
public final class PartyMemberHistoryPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/** The Constant MEMBER_HISTORY. */
	private static final String MEMBER_HISTORY = "MemberHistory";

	/**
	 * Instantiates a new member history page mod content factory impl.
	 */
	public PartyMemberHistoryPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PartyPageMode.MEMBERHISTORY.toString());
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DataContainer<ViewRiksdagenParty, String> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenParty.class);

		final ViewRiksdagenParty viewRiksdagenParty = dataContainer.load(pageId);

		if (viewRiksdagenParty != null) {

			getMenuItemFactory().createPartyMenuBar(menuBar, pageId);

			panelContent.addComponent(LabelFactory.createHeader2Label(MEMBER_HISTORY));

			final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenPolitician.class);

			final BeanItemContainer<ViewRiksdagenPolitician> politicianDataSource = new BeanItemContainer<>(
					ViewRiksdagenPolitician.class,
					politicianDataContainer.getAllBy(ViewRiksdagenPolitician_.party, viewRiksdagenParty.getPartyId()));

			final Grid partyMemberBeanItemGrid = getGridFactory().createBasicBeanItemGrid(politicianDataSource,
					"Politicians",
					new String[] { "personId", "firstName", "lastName", "party", "gender", "bornYear",
							"totalAssignments", "currentAssignments", "firstAssignmentDate", "lastAssignmentDate",
							"totalDaysServed", "totalDaysServedParliament", "totalDaysServedCommittee",
							"totalDaysServedGovernment", "totalDaysServedEu",

							"active", "activeEu", "activeGovernment", "activeCommittee", "activeParliament",

							"activeParty", "activeSpeaker", "totalDaysServedSpeaker", "totalDaysServedParty",

							"totalPartyAssignments", "totalMinistryAssignments", "totalCommitteeAssignments",
							"totalSpeakerAssignments",

							"currentPartyAssignments", "currentMinistryAssignments", "currentCommitteeAssignments",
							"currentSpeakerAssignments" },
					null, "personId", new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME, "personId"),
					null);

			panelContent.addComponent(partyMemberBeanItemGrid);

			pageCompleted(parameters, panel, pageId, viewRiksdagenParty);
		}
		return panelContent;

	}

}
