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
package com.hack23.cia.web.impl.ui.application.views.user.ballot.pagemode;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPartyEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPartyEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionSummary_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;



/**
 * The Class BallotOverviewPageModContentFactoryImpl.
 */
@Component
public final class BallotOverviewPageModContentFactoryImpl extends AbstractBallotPageModContentFactoryImpl {

	private static final List<String> AS_LIST = Arrays.asList( "embeddedId.id", "ballotId", "rm", "voteDate", "org",
			"committeeReport", "embeddedId.issue", "title", "subTitle", "decisionType",
			"embeddedId.concern", "ballotType", "winner", "totalVotes", "yesVotes", "noVotes",
			"abstainVotes", "absentVotes", "approved", "endNumber", "againstProposalParties",
			"againstProposalNumber" );

	private static final List<String> AS_LIST2 = Arrays.asList( "embeddedId.ballotId", "rm", "voteDate", "embeddedId.issue",
			"embeddedId.concern", "ballotType", "label", "totalVotes", "yesVotes", "noVotes",
			"abstainVotes", "absentVotes", "approved" );

	private static final String EMBEDDED_ID_PARTY = "embeddedId.party";

	private static final String[] COLUMN_ORDER = { EMBEDDED_ID_PARTY, "voteDate", "rm", "label", "embeddedId.concern",
			"embeddedId.issue", "approved", "partyApproved", "totalVotes", "partyTotalVotes",
			"yesVotes", "partyYesVotes", "noVotes", "partyNoVotes", "partyAbstainVotes", "abstainVotes",
			"partyAbsentVotes", "absentVotes", "partyAvgBornYear", "avgBornYear", "partyPercentageMale",
			"percentageMale", "ballotType", "embeddedId.ballotId" };



	private static final int FIRST_OBJECT = 0;

	private static final String[] HIDE_COLUMNS = { "embeddedId", "partyNoWinner", "partyPercentageYes", "partyPercentageNo",
			"partyPercentageAbsent", "partyPercentageAbstain", "percentageYes", "percentageNo",
			"percentageAbsent", "percentageAbstain", "voteDate", "rm", "label", "embeddedId.concern",
			"totalVotes", "approved", "yesVotes", "noVotes", "ballotType",
			"abstainVotes", "absentVotes", "embeddedId.ballotId", "noWinner" };

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(UserViews.PARTY_VIEW_NAME, EMBEDDED_ID_PARTY);

	private static final String[] NESTED_PROPERTIES = { "embeddedId.ballotId", "embeddedId.concern", "embeddedId.issue",
			EMBEDDED_ID_PARTY };

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";

	private static final String PARTY_BALLOT_SUMMARY = "Party Ballot Summary";

	/**
	 * Instantiates a new ballot overview page mod content factory impl.
	 */
	public BallotOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final List<ViewRiksdagenVoteDataBallotSummary> ballots = getItem(parameters);

		if (!ballots.isEmpty()) {
			getBallotMenuItemFactory().createBallotMenuBar(menuBar, pageId);

			final DataContainer<ViewRiksdagenVoteDataBallotPartySummary, RiksdagenVoteDataBallotPartyEmbeddedId> dataPartyContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenVoteDataBallotPartySummary.class);

			final DataContainer<ViewRiksdagenCommitteeBallotDecisionSummary, ViewRiksdagenCommitteeBallotDecisionEmbeddedId> dataDecisionContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenCommitteeBallotDecisionSummary.class);

			final List<ViewRiksdagenVoteDataBallotPartySummary> partyBallotList = dataPartyContainer.findListByEmbeddedProperty(
					ViewRiksdagenVoteDataBallotPartySummary.class, ViewRiksdagenVoteDataBallotPartySummary_.embeddedId,
					RiksdagenVoteDataBallotPartyEmbeddedId.class, RiksdagenVoteDataBallotPartyEmbeddedId_.ballotId, pageId);

			final List<ViewRiksdagenCommitteeBallotDecisionSummary> decisionSummaries = dataDecisionContainer
					.getAllBy(ViewRiksdagenCommitteeBallotDecisionSummary_.ballotId, pageId);

			createPageHeader(panel, panelContent, "Ballot Overview", "Overview", "Summarize and analyze key details of ballot processes.");

			for (final ViewRiksdagenVoteDataBallotSummary viewRiksdagenVoteDataBallotSummary : ballots) {

				if (!decisionSummaries.isEmpty()) {

					getFormFactory().addFormPanelTextFields(panelContent, decisionSummaries.get(FIRST_OBJECT),
							ViewRiksdagenCommitteeBallotDecisionSummary.class,
							AS_LIST);

					panel.setCaption(new StringBuilder().append("Ballot Overview : ").append(decisionSummaries.get(FIRST_OBJECT).getTitle()).append(" - ").append(decisionSummaries.get(FIRST_OBJECT).getSubTitle()).toString());
				} else {

					getFormFactory().addFormPanelTextFields(panelContent, viewRiksdagenVoteDataBallotSummary,
							ViewRiksdagenVoteDataBallotSummary.class,
							AS_LIST2);
					panel.setCaption(new StringBuilder().append("Ballot Overview : ").append(viewRiksdagenVoteDataBallotSummary.getEmbeddedId().getConcern()).toString());
				}

			}


			getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent, ViewRiksdagenVoteDataBallotPartySummary.class,
					partyBallotList, PARTY_BALLOT_SUMMARY,
					NESTED_PROPERTIES,
					COLUMN_ORDER,
					HIDE_COLUMNS,
					LISTENER, EMBEDDED_ID_PARTY, null);

			final VerticalLayout overviewLayout = new VerticalLayout();
			overviewLayout.setSizeFull();

			panelContent.addComponent(overviewLayout);
			panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);
			getBallotMenuItemFactory().createOverviewPage(overviewLayout, pageId);


			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_BALLOT_VIEW, ApplicationEventGroup.USER, NAME,
					parameters, pageId);

		}
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

}
