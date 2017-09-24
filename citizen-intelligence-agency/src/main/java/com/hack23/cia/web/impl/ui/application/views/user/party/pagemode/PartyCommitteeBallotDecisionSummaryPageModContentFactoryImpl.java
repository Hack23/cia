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

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
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
 * The Class CommitteeBallotDecisionSummaryPageModContentFactoryImpl.
 */
@Component
public final class PartyCommitteeBallotDecisionSummaryPageModContentFactoryImpl
		extends AbstractPartyPageModContentFactoryImpl {

	/** The Constant COMMITTEE_BALLOT_DECISION_PARTY_SUMMARY. */
	private static final String COMMITTEE_BALLOT_DECISION_PARTY_SUMMARY = "Committee Ballot Decision Party Summary";

	/** The Constant COMMITTEE_BALLOT_DECISION_SUMMARY. */
	private static final String COMMITTEE_BALLOT_DECISION_SUMMARY = "CommitteeBallotDecisionSummary";

	/**
	 * Instantiates a new party committee ballot decision summary page mod
	 * content factory impl.
	 */
	public PartyCommitteeBallotDecisionSummaryPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString());
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

			LabelFactory.createHeader2Label(panelContent, COMMITTEE_BALLOT_DECISION_SUMMARY);

			final DataContainer<ViewRiksdagenCommitteeBallotDecisionPartySummary, ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId> committeeBallotDecisionPartyDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenCommitteeBallotDecisionPartySummary.class);

			final List<ViewRiksdagenCommitteeBallotDecisionPartySummary> decisionPartySummaryList = committeeBallotDecisionPartyDataContainer
					.findOrderedListByEmbeddedProperty(ViewRiksdagenCommitteeBallotDecisionPartySummary.class,
							ViewRiksdagenCommitteeBallotDecisionPartySummary_.embeddedId,
							ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId.class,
							ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId_.party, pageId,
							ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId_.issue);

			getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent, ViewRiksdagenCommitteeBallotDecisionPartySummary.class,
					decisionPartySummaryList, COMMITTEE_BALLOT_DECISION_PARTY_SUMMARY,
					new String[] { "embeddedId.id", "embeddedId.concern", "embeddedId.issue", "embeddedId.party" },
					new String[] { "voteDate", "rm", "org", "embeddedId.id", "embeddedId.party", "committeeReport",
							"title", "subTitle", "winner", "partyApproved", "againstProposalParties", "embeddedId.concern",
							"embeddedId.issue", "endNumber", "createdDate", "publicDate", "ballotId", "decisionType",
							"againstProposalNumber", "ballotType", "label", "avgBornYear", "totalVotes", "approved",
							"noWinner", "percentageYes", "percentageNo", "percentageAbsent", "percentageAbstain",
							"percentageMale", "partyAvgBornYear", "partyTotalVotes", "partyYesVotes", "partyNoVotes",
							"partyAbstainVotes", "partyAbsentVotes", "yesVotes", "noVotes",
							"abstainVotes", "absentVotes", "partyNoWinner", "partyPercentageYes", "partyPercentageNo",
							"partyPercentageAbsent", "partyPercentageAbstain", "partyPercentageMale" },
					new String[] { "embeddedId", "ballotId", "decisionType", "ballotType", "againstProposalNumber",
							"embeddedId.id", "embeddedId.party", "createdDate", "publicDate", "label", "endNumber",
							"org", "partyPercentageYes", "partyPercentageNo", "partyPercentageAbsent",
							"partyPercentageAbstain", "partyPercentageMale", "partyAvgBornYear", "avgBornYear",
							"percentageYes", "percentageNo", "percentageAbsent", "percentageAbstain", "percentageMale",
							"approved", "noWinner" },
					new PageItemPropertyClickListener(UserViews.BALLOT_VIEW_NAME, "ballotId"), "ballotId", null);

			pageCompleted(parameters, panel, pageId, viewRiksdagenParty);
		}
		return panelContent;

	}

}
