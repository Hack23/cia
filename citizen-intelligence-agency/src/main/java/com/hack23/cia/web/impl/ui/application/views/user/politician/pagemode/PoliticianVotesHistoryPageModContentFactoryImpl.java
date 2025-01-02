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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GenericChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class VotesHistoryPageModContentFactoryImpl.
 */
@Component
public final class PoliticianVotesHistoryPageModContentFactoryImpl extends AbstractPoliticianPageModContentFactoryImpl {

	private static final String BALLOTS = "Ballots";
	private static final String EMBEDDED_ID_BALLOT_ID = "embeddedId.ballotId";
	private static final String[] COLUMN_ORDER = { "voteDate", "rm", "label", "embeddedId.concern",
			"embeddedId.issue", "vote", "won", "partyWon", "rebel", "noWinner", "approved", "partyApproved",
			"totalVotes", "partyTotalVotes", "yesVotes", "partyYesVotes", "noVotes", "partyNoVotes",
			"partyAbstainVotes", "abstainVotes", "partyAbsentVotes", "absentVotes", "bornYear", "partyAvgBornYear",
			"avgBornYear", "gender", "partyPercentageMale", "percentageMale", "ballotType", EMBEDDED_ID_BALLOT_ID };
	private static final String[] HIDE_COLUMNS = { "embeddedId", "partyNoWinner", "partyPercentageYes",
			"partyPercentageNo", "partyPercentageAbsent", "partyPercentageAbstain", "percentageYes", "percentageNo",
			"percentageAbsent", "percentageAbstain", "firstName", "lastName", "party", EMBEDDED_ID_BALLOT_ID,
			"ballotType" };
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.BALLOT_VIEW_NAME, EMBEDDED_ID_BALLOT_ID);
	private static final String[] NESTED_PROPERTIES = { EMBEDDED_ID_BALLOT_ID, "embeddedId.concern",
			"embeddedId.issue" };
	/**
	 * The view riksdagen vote data ballot politician summary chart data
	 * manager.
	 */
	@Autowired
	private GenericChartDataManager<ViewRiksdagenVoteDataBallotPoliticianSummary> viewRiksdagenVoteDataBallotPoliticianSummaryChartDataManager;

	/**
	 * Instantiates a new politician votes history page mod content factory
	 * impl.
	 */
	public PoliticianVotesHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);
		final ViewRiksdagenPolitician viewRiksdagenPolitician = getItem(parameters);
		getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent, viewRiksdagenPolitician.getFirstName() + ' ' + viewRiksdagenPolitician.getLastName() + '(' + viewRiksdagenPolitician.getParty() + ')' + " Votes History", "Voting Record", "Review the politician's voting history, including key decisions and patterns.");

		getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent,
				ViewRiksdagenVoteDataBallotPoliticianSummary.class,
				viewRiksdagenVoteDataBallotPoliticianSummaryChartDataManager
						.findByValue(viewRiksdagenPolitician.getPersonId()),
				BALLOTS, NESTED_PROPERTIES, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, EMBEDDED_ID_BALLOT_ID, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
		UserViews.POLITICIAN_VIEW_NAME, parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PoliticianPageMode.VOTEHISTORY.toString());
	}
}
