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
package com.hack23.cia.web.impl.ui.application.views.user.partyranking.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class PartyRankingDataGridPageModContentFactoryImpl.
 */
@Service
public final class PartyRankingDataGridPageModContentFactoryImpl extends AbstractPartyRankingPageModContentFactoryImpl {

	/** The Constant COLUMN_ORDER. */
	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = {
	    "party",                          // Party name
	    "currentlyActiveMembers",         // Current active members
	    "totalDocumentsLastYear",         // Recent activity
	    "avgDocumentsLastYear",           // Recent productivity per member
	    "totalDocuments",                 // Historical volume
	    "avgDocumentsPerMember",         // Overall productivity
	    "veryHighActivityMembers",       // Member activity distribution
	    "highActivityMembers",
	    "mediumActivityMembers",
	    "lowActivityMembers",
	    "totalPartyMotions",             // Motion types distribution
	    "totalCommitteeMotions",
	    "totalIndividualMotions",
	    "totalCollaborativeMotions",
	    "partyFocusedMembers",          // Member focus distribution
	    "committeeFocusedMembers",
	    "individualFocusedMembers",
	    "currentAssignments",            // Current positions
	    "currentMinistryAssignments",
	    "currentCommitteeAssignments",
	    "currentCommitteeLeadershipAssignments"
	};

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = {
	    "active",
	    "activeParliament",
	    "activeGovernment",
	    "activeCommittee",
	    "activeEu",
	    "activeParty",
	    "activeSpeaker",
	    "totalDaysServedParliament",
	    "totalDaysServedCommittee",
	    "totalDaysServedGovernment",
	    "totalDaysServedEu",
	    "totalDaysServedParty",
	    "totalDaysServedSpeaker",
	    "totalDaysServedCommitteeLeadership",
	    "totalDaysServedCommitteeSubstitute",
	    "totalActiveEu",
	    "currentCommitteeSubstituteAssignments",
	    "totalCommitteeSubstituteAssignments",
	    "currentSpeakerAssignments",
	    "totalSpeakerAssignments",
	    "totalFollowUpMotions",          // Can be accessed in detailed view
	    "firstAssignmentDate",           // Less relevant for activity focus
	    "lastAssignmentDate",
	    "totalPartyAssignments",
	    "totalMinistryAssignments",
	    "totalCommitteeAssignments",
	    "totalCommitteeLeadershipAssignments"
	};
	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(UserViews.PARTY_VIEW_NAME, "party");

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_RANKING_VIEW_NAME;

	/** The Constant PARTIES. */
	private static final String PARTIES = "Parties";

	/**
	 * Instantiates a new party ranking data grid page mod content factory impl.
	 */
	public PartyRankingDataGridPageModContentFactoryImpl() {
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
	@SuppressWarnings("unchecked")
	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getPartyRankingMenuItemFactory().createPartyRankingMenuBar(menuBar);

		CardInfoRowUtil.createPageHeader(panel, panelContent,
		    PartyRankingViewConstants.DATAGRID_TITLE,
		    PartyRankingViewConstants.TITLE_PARTY_RANKINGS,
		    PartyRankingViewConstants.DATAGRID_DESC);

		final String pageId = getPageId(parameters);


		final DataContainer<ViewRiksdagenPartySummary, String> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartySummary.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenPartySummary.class, dataContainer.findListByProperty(
				new Object[] { Boolean.TRUE }, ViewRiksdagenPartySummary_.active),
				PARTIES,
				COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);



		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

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
		return PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_DATAGRID.matches(page, parameters);
	}

}
