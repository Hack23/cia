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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GenericChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class VoteHistoryPageModContentFactoryImpl.
 */
@Component
public final class PartyVoteHistoryPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/** The Constant BALLOTS. */
	private static final String BALLOTS = "Ballots";

	/** The Constant EMBEDDED_ID_BALLOT_ID. */
	private static final String EMBEDDED_ID_BALLOT_ID = "embeddedId.ballotId";

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { "embeddedId.party", "voteDate", "rm", "label",
			"embeddedId.concern", "embeddedId.issue", "approved", "partyApproved", "totalVotes", "partyTotalVotes",
			"yesVotes", "partyYesVotes", "noVotes", "partyNoVotes", "partyAbstainVotes", "abstainVotes",
			"partyAbsentVotes", "absentVotes", "partyAvgBornYear", "avgBornYear", "partyPercentageMale",
			"percentageMale", "ballotType", EMBEDDED_ID_BALLOT_ID };

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "embeddedId", "partyNoWinner", "partyPercentageYes",
			"partyPercentageNo", "partyPercentageAbsent", "partyPercentageAbstain", "percentageYes", "percentageNo",
			"percentageAbsent", "percentageAbstain", "ballotType", "embeddedId.party", EMBEDDED_ID_BALLOT_ID,
			"partyAvgBornYear", "avgBornYear", "partyPercentageMale", "percentageMale", "noWinner" };

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.BALLOT_VIEW_NAME, EMBEDDED_ID_BALLOT_ID);

	/** The Constant NESTED_PROPERTIES. */
	private static final String[] NESTED_PROPERTIES = { EMBEDDED_ID_BALLOT_ID, "embeddedId.concern",
			"embeddedId.issue", "embeddedId.party" };

	/** The view riksdagen vote data ballot party summary chart data manager. */
	@Autowired
	private GenericChartDataManager<ViewRiksdagenVoteDataBallotPartySummary> viewRiksdagenVoteDataBallotPartySummaryChartDataManager;

	/**
	 * Instantiates a new party vote history page mod content factory impl.
	 */
	public PartyVoteHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenParty viewRiksdagenParty = getItem(parameters);
		getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent,
            PartyViewConstants.VOTE_HISTORY_HEADER + " " + viewRiksdagenParty.getPartyName(),
            PartyViewConstants.VOTE_HISTORY_TITLE,
            PartyViewConstants.VOTE_HISTORY_DESC);

		getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent,
				ViewRiksdagenVoteDataBallotPartySummary.class,
				viewRiksdagenVoteDataBallotPartySummaryChartDataManager.findByValue(pageId), BALLOTS, NESTED_PROPERTIES,
				COLUMN_ORDER, HIDE_COLUMNS, LISTENER, EMBEDDED_ID_BALLOT_ID, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
		pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandPartyConstants.COMMAND_PARTY_VOTE_HISTORY.matches(page, parameters);
	}

}
