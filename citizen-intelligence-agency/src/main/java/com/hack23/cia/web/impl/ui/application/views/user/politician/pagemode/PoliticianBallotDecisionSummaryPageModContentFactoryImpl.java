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
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class BallotDecisionSummaryPageModContentFactoryImpl.
 */
@Component
public final class PoliticianBallotDecisionSummaryPageModContentFactoryImpl
		extends AbstractPoliticianPageModContentFactoryImpl {

	private static final String BALLOT_ID = "ballotId";
	private static final String[] COLUMN_ORDER = { "voteDate", "rm", "org", "committeeReport", "title",
			"subTitle", "winner", "embeddedId.concern", "embeddedId.issue", "vote", "won", "rebel", "noWinner",
			"approved", "partyApproved", "againstProposalNumber", "againstProposalParties", "totalVotes",
			"partyTotalVotes", "yesVotes", "partyYesVotes", "noVotes", "partyNoVotes", "partyAbstainVotes",
			"abstainVotes", "partyAbsentVotes", "absentVotes", "bornYear", "partyAvgBornYear", "avgBornYear",
			"ballotType", "decisionType", BALLOT_ID };
	private static final String COMMITTEE_BALLOT_DECISION_POLITICIAN_SUMMARY = "Committee Ballot Decision Politician Summary";
	private static final String[] HIDE_COLUMNS = { "label", "endNumber", "publicDate", "createdDate",
			"embeddedId", "partyNoWinner", "partyPercentageYes", "partyPercentageNo", "partyPercentageAbsent",
			"partyPercentageAbstain", "percentageYes", "percentageNo", "percentageAbsent", "percentageAbstain",
			"firstName", "lastName", "party", BALLOT_ID, "decisionType", "ballotType", "againstProposalNumber" };
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.BALLOT_VIEW_NAME, BALLOT_ID);
	private static final String[] NESTED_PROPERTIES = { "embeddedId.concern", "embeddedId.issue" };

	/**
	 * Instantiates a new politician ballot decision summary page mod content
	 * factory impl.
	 */
	public PoliticianBallotDecisionSummaryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenPolitician viewRiksdagenPolitician = getItem(parameters);

		getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent, viewRiksdagenPolitician.getFirstName() + ' ' + viewRiksdagenPolitician.getLastName() + '(' + viewRiksdagenPolitician.getParty() + ')' + " Ballot Decision Summary", "Decision Summary Overview", "Summarize and analyze the politician's voting decisions on various ballots.");

		final DataContainer<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary, ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId> committeeBallotDecisionPartyDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class);

		final List<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary> decisionPartySummaryList = committeeBallotDecisionPartyDataContainer
				.findOrderedByPropertyListByEmbeddedProperty(
						ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class,
						ViewRiksdagenCommitteeBallotDecisionPoliticianSummary_.embeddedId,
						ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId.class,
						ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId_.intressentId, pageId,
						ViewRiksdagenCommitteeBallotDecisionPoliticianSummary_.voteDate);

		getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent,
				ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class, decisionPartySummaryList,
				COMMITTEE_BALLOT_DECISION_POLITICIAN_SUMMARY, NESTED_PROPERTIES, COLUMN_ORDER, HIDE_COLUMNS, LISTENER,
				BALLOT_ID, null);


		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
		UserViews.POLITICIAN_VIEW_NAME, parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PoliticianPageMode.BALLOTDECISIONSUMMARY.toString());
	}
}
