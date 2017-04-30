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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GenericChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class VoteHistoryPageModContentFactoryImpl.
 */
@Component
public final class PartyVoteHistoryPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/** The Constant VOTE_HISTORY. */
	private static final String VOTE_HISTORY = "VoteHistory";

	/** The view riksdagen vote data ballot party summary chart data manager. */
	@Autowired
	private GenericChartDataManager<ViewRiksdagenVoteDataBallotPartySummary> viewRiksdagenVoteDataBallotPartySummaryChartDataManager;

	/**
	 * Instantiates a new party vote history page mod content factory impl.
	 */
	public PartyVoteHistoryPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PartyPageMode.VOTEHISTORY.toString());
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

			LabelFactory.createHeader2Label(panelContent, VOTE_HISTORY);

			final BeanItemContainer<ViewRiksdagenVoteDataBallotPartySummary> partyBallotDataSource = new BeanItemContainer<>(
					ViewRiksdagenVoteDataBallotPartySummary.class,
					viewRiksdagenVoteDataBallotPartySummaryChartDataManager.findByValue(pageId));

			getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent, partyBallotDataSource, "Ballots",
					new String[] { "embeddedId.ballotId", "embeddedId.concern", "embeddedId.issue",
							"embeddedId.party" },
					new String[] { "embeddedId.party", "voteDate", "rm", "label", "embeddedId.concern",
							"embeddedId.issue", "approved", "partyApproved", "totalVotes", "partyTotalVotes",
							"yesVotes", "partyYesVotes", "noVotes", "partyNoVotes", "partyAbstainVotes", "abstainVotes",
							"partyAbsentVotes", "absentVotes", "partyAvgBornYear", "avgBornYear", "partyPercentageMale",
							"percentageMale", "ballotType", "embeddedId.ballotId" },
					new String[] { "embeddedId", "partyNoWinner", "partyPercentageYes", "partyPercentageNo",
							"partyPercentageAbsent", "partyPercentageAbstain", "percentageYes", "percentageNo",
							"percentageAbsent", "percentageAbstain", "ballotType", "embeddedId.party",
							"embeddedId.ballotId", "partyAvgBornYear", "avgBornYear", "partyPercentageMale",
							"percentageMale", "noWinner" },
					new PageItemPropertyClickListener(UserViews.BALLOT_VIEW_NAME, "embeddedId.ballotId"),
					"embeddedId.ballotId", null);

			pageCompleted(parameters, panel, pageId, viewRiksdagenParty);
		}
		return panelContent;

	}

}
