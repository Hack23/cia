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
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class CurrentMembersPageModContentFactoryImpl.
 */
@Component
public final class PartyCurrentMembersPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/** The Constant CURRENT_MEMBERS. */
	private static final String CURRENT_MEMBERS = "Current Members";

	/**
	 * Instantiates a new party current members page mod content factory impl.
	 */
	public PartyCurrentMembersPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PartyPageMode.CURRENTMEMBERS.toString());
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

			getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);

			LabelFactory.createHeader2Label(panelContent,CURRENT_MEMBERS);


			final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenPolitician.class);

			getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenPolitician.class,
					politicianDataContainer.findListByProperty(
							new Object[] { viewRiksdagenParty.getPartyId(), Boolean.TRUE },
							ViewRiksdagenPolitician_.party, ViewRiksdagenPolitician_.active),
					"Politicians",
					new String[] { "personId", "firstName", "lastName", "party", "bornYear", "totalDaysServed",
							"currentAssignments", "totalAssignments", "firstAssignmentDate", "lastAssignmentDate",
							"totalDaysServedParliament", "totalDaysServedCommittee", "totalDaysServedGovernment",
							"totalDaysServedEu",

							"active", "activeEu", "activeGovernment", "activeCommittee", "activeParliament",

							"activeParty", "activeSpeaker", "totalDaysServedSpeaker", "totalDaysServedParty",

							"totalPartyAssignments", "totalMinistryAssignments", "totalCommitteeAssignments",
							"totalSpeakerAssignments",

							"currentPartyAssignments", "currentMinistryAssignments", "currentCommitteeAssignments",
							"currentSpeakerAssignments", "gender" }, new String[] { "personId", "active", "activeEu", "party", "activeGovernment", "activeCommittee", "activeParliament", "activeParty", "activeSpeaker","bornYear" }, new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME, "personId"), null, null);


			pageCompleted(parameters, panel, pageId, viewRiksdagenParty);
		}
		return panelContent;

	}

}
