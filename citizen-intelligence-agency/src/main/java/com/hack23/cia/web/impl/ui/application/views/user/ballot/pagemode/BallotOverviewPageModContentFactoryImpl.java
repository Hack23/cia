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
package com.hack23.cia.web.impl.ui.application.views.user.ballot.pagemode;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPartyEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPartyEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionSummary_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class BallotOverviewPageModContentFactoryImpl.
 */
@Component
public final class BallotOverviewPageModContentFactoryImpl extends AbstractBallotPageModContentFactoryImpl {

	private static final int FIRST_OBJECT = 0;

	/** The Constant COMMITTEE. */
	private static final String BALLOT = "Ballot:";

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";

	/**
	 * Instantiates a new ballot overview page mod content factory impl.
	 */
	public BallotOverviewPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DataContainer<ViewRiksdagenVoteDataBallotSummary, RiksdagenVoteDataBallotEmbeddedId> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenVoteDataBallotSummary.class);

		final DataContainer<ViewRiksdagenVoteDataBallotPartySummary, RiksdagenVoteDataBallotPartyEmbeddedId> dataPartyContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenVoteDataBallotPartySummary.class);

		final DataContainer<ViewRiksdagenCommitteeBallotDecisionSummary, ViewRiksdagenCommitteeBallotDecisionEmbeddedId> dataDecisionContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommitteeBallotDecisionSummary.class);

		final List<ViewRiksdagenVoteDataBallotSummary> ballots = dataContainer.findListByEmbeddedProperty(
				ViewRiksdagenVoteDataBallotSummary.class, ViewRiksdagenVoteDataBallotSummary_.embeddedId,
				RiksdagenVoteDataBallotEmbeddedId.class, RiksdagenVoteDataBallotEmbeddedId_.ballotId, pageId);

		final List<ViewRiksdagenVoteDataBallotPartySummary> partyBallotList = dataPartyContainer.findListByEmbeddedProperty(
				ViewRiksdagenVoteDataBallotPartySummary.class, ViewRiksdagenVoteDataBallotPartySummary_.embeddedId,
				RiksdagenVoteDataBallotPartyEmbeddedId.class, RiksdagenVoteDataBallotPartyEmbeddedId_.ballotId, pageId);

		final List<ViewRiksdagenCommitteeBallotDecisionSummary> decisionSummaries = dataDecisionContainer
				.getAllBy(ViewRiksdagenCommitteeBallotDecisionSummary_.ballotId, pageId);

		if (!ballots.isEmpty()) {
			getBallotMenuItemFactory().createBallotMenuBar(menuBar, pageId);

			LabelFactory.createHeader2Label(panelContent,OVERVIEW);


			for (final ViewRiksdagenVoteDataBallotSummary viewRiksdagenVoteDataBallotSummary : ballots) {

				if (!decisionSummaries.isEmpty()) {

					getFormFactory().addFormPanelTextFields(panelContent, new BeanItem<>(decisionSummaries.get(FIRST_OBJECT)),
							ViewRiksdagenCommitteeBallotDecisionSummary.class,
							Arrays.asList(new String[] { "embeddedId.id", "ballotId", "rm", "voteDate", "org",
									"committeeReport", "embeddedId.issue", "title", "subTitle", "decisionType",
									"embeddedId.concern", "ballotType", "winner", "totalVotes", "yesVotes", "noVotes",
									"abstainVotes", "absentVotes", "approved", "endNumber", "againstProposalParties",
									"againstProposalNumber" }));

				} else {

					getFormFactory().addFormPanelTextFields(panelContent, new BeanItem<>(viewRiksdagenVoteDataBallotSummary),
							ViewRiksdagenVoteDataBallotSummary.class,
							Arrays.asList(new String[] { "embeddedId.ballotId", "rm", "voteDate", "embeddedId.issue",
									"embeddedId.concern", "ballotType", "label", "totalVotes", "yesVotes", "noVotes",
									"abstainVotes", "absentVotes", "approved" }));
				}

			}


			final BeanItemContainer<ViewRiksdagenVoteDataBallotPartySummary> partyBallotDataSource = new BeanItemContainer<>(
					ViewRiksdagenVoteDataBallotPartySummary.class, partyBallotList);

			getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent,
					partyBallotDataSource, "Party Ballot Summary",
					new String[] { "embeddedId.ballotId", "embeddedId.concern", "embeddedId.issue",
							"embeddedId.party" },
					new String[] { "embeddedId.party", "voteDate", "rm", "label", "embeddedId.concern",
							"embeddedId.issue", "approved", "partyApproved", "totalVotes", "partyTotalVotes",
							"yesVotes", "partyYesVotes", "noVotes", "partyNoVotes", "partyAbstainVotes", "abstainVotes",
							"partyAbsentVotes", "absentVotes", "partyAvgBornYear", "avgBornYear", "partyPercentageMale",
							"percentageMale", "ballotType", "embeddedId.ballotId" },
					new String[] { "embeddedId", "partyNoWinner", "partyPercentageYes", "partyPercentageNo",
							"partyPercentageAbsent", "partyPercentageAbstain", "percentageYes", "percentageNo",
							"percentageAbsent", "percentageAbstain", "voteDate", "rm", "label", "embeddedId.concern",
							"totalVotes", "approved", "yesVotes", "noVotes", "ballotType",
							"abstainVotes", "absentVotes", "embeddedId.ballotId", "noWinner" },
					new PageItemPropertyClickListener(UserViews.PARTY_VIEW_NAME, "embeddedId.party"), "embeddedId.party", null);

			final VerticalLayout overviewLayout = new VerticalLayout();
			overviewLayout.setSizeFull();

			panelContent.addComponent(overviewLayout);
			panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);
			getBallotMenuItemFactory().createOverviewPage(overviewLayout, pageId);


			panel.setCaption(NAME + "::" + BALLOT + pageId);
			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_BALLOT_VIEW, ApplicationEventGroup.USER, NAME,
					parameters, pageId);
		}
		return panelContent;

	}

}
