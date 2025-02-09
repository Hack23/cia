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

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary_;
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
 * The Class CommitteeBallotDecisionSummaryPageModContentFactoryImpl.
 */
@Component
public final class PartyCommitteeBallotDecisionSummaryPageModContentFactoryImpl
		extends AbstractPartyPageModContentFactoryImpl {

	/** The Constant BALLOT_ID. */
	private static final String BALLOT_ID = "ballotId";

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { "voteDate", "rm", "org", "embeddedId.id",
			"embeddedId.party", "committeeReport", "title", "subTitle", "winner", "partyApproved",
			"againstProposalParties", "embeddedId.concern", "embeddedId.issue", "endNumber", "createdDate",
			"publicDate", BALLOT_ID, "decisionType", "againstProposalNumber", "ballotType", "label", "avgBornYear",
			"totalVotes", "approved", "noWinner", "percentageYes", "percentageNo", "percentageAbsent",
			"percentageAbstain", "percentageMale", "partyAvgBornYear", "partyTotalVotes", "partyYesVotes",
			"partyNoVotes", "partyAbstainVotes", "partyAbsentVotes", "yesVotes", "noVotes", "abstainVotes",
			"absentVotes", "partyNoWinner", "partyPercentageYes", "partyPercentageNo", "partyPercentageAbsent",
			"partyPercentageAbstain", "partyPercentageMale" };

	/** The Constant COMMITTEE_BALLOT_DECISION_PARTY_SUMMARY. */
	private static final String COMMITTEE_BALLOT_DECISION_PARTY_SUMMARY = PartyViewConstants.GRID_LABEL_COMMITTEE_BALLOT_SUMMARY;

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "embeddedId", BALLOT_ID, "decisionType", "ballotType",
			"againstProposalNumber", "embeddedId.id", "embeddedId.party", "createdDate", "publicDate", "label",
			"endNumber", "org", "partyPercentageYes", "partyPercentageNo", "partyPercentageAbsent",
			"partyPercentageAbstain", "partyPercentageMale", "partyAvgBornYear", "avgBornYear", "percentageYes",
			"percentageNo", "percentageAbsent", "percentageAbstain", "percentageMale", "approved", "noWinner" };

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.BALLOT_VIEW_NAME, BALLOT_ID);

	/** The Constant NESTED_PROPERTIES. */
	private static final String[] NESTED_PROPERTIES = { "embeddedId.id", "embeddedId.concern",
			"embeddedId.issue", "embeddedId.party" };

	/**
	 * Instantiates a new party committee ballot decision summary page mod
	 * content factory impl.
	 */
	public PartyCommitteeBallotDecisionSummaryPageModContentFactoryImpl() {
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
			PartyViewConstants.BALLOT_DECISIONS_HEADER + " " + viewRiksdagenParty.getPartyName(),
			PartyViewConstants.BALLOT_DECISIONS_TITLE,
			PartyViewConstants.BALLOT_DECISIONS_DESC);

		final DataContainer<ViewRiksdagenCommitteeBallotDecisionPartySummary, ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId> committeeBallotDecisionPartyDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommitteeBallotDecisionPartySummary.class);

		final List<ViewRiksdagenCommitteeBallotDecisionPartySummary> decisionPartySummaryList = committeeBallotDecisionPartyDataContainer
				.findOrderedByPropertyListByEmbeddedProperty(ViewRiksdagenCommitteeBallotDecisionPartySummary.class,
						ViewRiksdagenCommitteeBallotDecisionPartySummary_.embeddedId,
						ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId.class,
						ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId_.party, pageId,
						ViewRiksdagenCommitteeBallotDecisionPartySummary_.voteDate);

		getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent,
				ViewRiksdagenCommitteeBallotDecisionPartySummary.class, decisionPartySummaryList,
				COMMITTEE_BALLOT_DECISION_PARTY_SUMMARY, NESTED_PROPERTIES, COLUMN_ORDER, HIDE_COLUMNS, LISTENER,
				BALLOT_ID, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
		pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandPartyConstants.COMMAND_PARTY_BALLOT_DECISION_SUMMARY.matches(page, parameters);
	}

}
