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
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class BallotOverviewPageModContentFactoryImpl.
 */
@Component
public final class BallotOverviewPageModContentFactoryImpl extends AbstractBallotPageModContentFactoryImpl {

	/** The Constant EMBEDDED_ID_PARTY. */
	private static final String EMBEDDED_ID_PARTY = "embeddedId.party";

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { EMBEDDED_ID_PARTY, "voteDate", "rm", "label", "embeddedId.concern",
			"embeddedId.issue", "approved", "partyApproved", "totalVotes", "partyTotalVotes", "yesVotes",
			"partyYesVotes", "noVotes", "partyNoVotes", "partyAbstainVotes", "abstainVotes", "partyAbsentVotes",
			"absentVotes", "partyAvgBornYear", "avgBornYear", "partyPercentageMale", "percentageMale", "ballotType",
			"embeddedId.ballotId" };

	/** The Constant FIRST_OBJECT. */
	private static final int FIRST_OBJECT = 0;

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "embeddedId", "partyNoWinner", "partyPercentageYes",
			"partyPercentageNo", "partyPercentageAbsent", "partyPercentageAbstain", "percentageYes", "percentageNo",
			"percentageAbsent", "percentageAbstain", "voteDate", "rm", "label", "embeddedId.concern", "totalVotes",
			"approved", "yesVotes", "noVotes", "ballotType", "abstainVotes", "absentVotes", "embeddedId.ballotId",
			"noWinner" };

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.PARTY_VIEW_NAME, EMBEDDED_ID_PARTY);

	/** The Constant NESTED_PROPERTIES. */
	private static final String[] NESTED_PROPERTIES = { "embeddedId.ballotId", "embeddedId.concern", "embeddedId.issue",
			EMBEDDED_ID_PARTY };

	/** The Constant PARTY_BALLOT_SUMMARY. */
	private static final String PARTY_BALLOT_SUMMARY = "Party Ballot Summary";

	/**
	 * Instantiates a new ballot overview page mod content factory impl.
	 */
	public BallotOverviewPageModContentFactoryImpl() {
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
	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		panel.setContent(panelContent);

		final String pageId = getPageId(parameters);
		final List<ViewRiksdagenVoteDataBallotSummary> ballots = getItem(parameters);

		if (!ballots.isEmpty()) {
			getBallotMenuItemFactory().createBallotMenuBar(menuBar, pageId);

			final DataContainer<ViewRiksdagenVoteDataBallotPartySummary, RiksdagenVoteDataBallotPartyEmbeddedId> dataPartyContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenVoteDataBallotPartySummary.class);

			final DataContainer<ViewRiksdagenCommitteeBallotDecisionSummary, ViewRiksdagenCommitteeBallotDecisionEmbeddedId> dataDecisionContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenCommitteeBallotDecisionSummary.class);

			final List<ViewRiksdagenVoteDataBallotPartySummary> partyBallotList = dataPartyContainer
					.findListByEmbeddedProperty(ViewRiksdagenVoteDataBallotPartySummary.class,
							ViewRiksdagenVoteDataBallotPartySummary_.embeddedId,
							RiksdagenVoteDataBallotPartyEmbeddedId.class,
							RiksdagenVoteDataBallotPartyEmbeddedId_.ballotId, pageId);

			final List<ViewRiksdagenCommitteeBallotDecisionSummary> decisionSummaries = dataDecisionContainer
					.getAllBy(ViewRiksdagenCommitteeBallotDecisionSummary_.ballotId, pageId);

			final boolean useDecisionSummaries = !decisionSummaries.isEmpty();

			String mainTitle;
			String subTitle;
			if (useDecisionSummaries) {
				mainTitle = "Ballot Overview " + decisionSummaries.get(0).getTitle() + " - "
						+ decisionSummaries.get(0).getSubTitle();
			} else {
				final ViewRiksdagenVoteDataBallotSummary firstBallot = ballots.get(FIRST_OBJECT);
				mainTitle = "Ballot Overview " + firstBallot.getEmbeddedId().getConcern();
			}
			subTitle = "Ballot Details";

			createPageHeader(panel, panelContent, mainTitle, subTitle,
					"Explore and analyze ballot results and voting statistics.");

			// Card panel
			final Panel cardPanel = createCardPanel("Ballot Information");
			final VerticalLayout cardContent = (VerticalLayout) cardPanel.getContent();
			panelContent.addComponent(cardPanel);
			panelContent.setExpandRatio(cardPanel, ContentRatio.GRID);

			// Two-column layout
			final HorizontalLayout attributesLayout = new HorizontalLayout();
			attributesLayout.setSpacing(true);
			attributesLayout.setWidth("100%");
			cardContent.addComponent(attributesLayout);

			// Left column: Ballot Profile (Textual and identifying attributes)
			final VerticalLayout profileDetailsLayout = new VerticalLayout();
			profileDetailsLayout.setSpacing(true);
			profileDetailsLayout.addStyleName("card-details-column");
			profileDetailsLayout.setWidthUndefined();

			final Label profileDetailsHeader = new Label("Ballot Profile");
			profileDetailsHeader.addStyleName("card-section-title");
			profileDetailsLayout.addComponent(profileDetailsHeader);

			// Right column: Voting Statistics (Numeric and outcome attributes)
			final VerticalLayout serviceStatsLayout = new VerticalLayout();
			serviceStatsLayout.setSpacing(true);
			serviceStatsLayout.addStyleName("card-details-column");
			serviceStatsLayout.setWidthUndefined();

			final Label serviceStatsHeader = new Label("Voting Statistics");
			serviceStatsHeader.addStyleName("card-section-title");
			serviceStatsLayout.addComponent(serviceStatsHeader);

			attributesLayout.addComponents(profileDetailsLayout, serviceStatsLayout);

			// Populate fields depending on data
			if (useDecisionSummaries) {
				final ViewRiksdagenCommitteeBallotDecisionSummary ds = decisionSummaries.get(FIRST_OBJECT);
				// Show essential profile fields
				profileDetailsLayout.addComponent(createInfoRow("Vote Date:", String.valueOf(ds.getVoteDate()), VaadinIcons.CALENDAR));
				profileDetailsLayout.addComponent(createInfoRow("Title:", ds.getTitle(), VaadinIcons.FILE_TEXT_O));
				profileDetailsLayout.addComponent(createInfoRow("SubTitle:", ds.getSubTitle(), VaadinIcons.FILE_TEXT));
				profileDetailsLayout.addComponent(createInfoRow("Decision Type:", ds.getDecisionType(), VaadinIcons.QUESTION_CIRCLE));

				// Stats fields
				serviceStatsLayout.addComponent(createInfoRow("Concern:", ds.getEmbeddedId().getConcern(), VaadinIcons.CLIPBOARD));
				serviceStatsLayout.addComponent(createInfoRow("Ballot Type:", ds.getBallotType(), VaadinIcons.BULLETS));
				serviceStatsLayout.addComponent(createInfoRow("Winner:", ds.getWinner(), VaadinIcons.TROPHY));
				serviceStatsLayout.addComponent(createInfoRow("Approved:", String.valueOf(ds.isApproved()), VaadinIcons.CHECK));
			} else {
				// Non-decision scenario
				final ViewRiksdagenVoteDataBallotSummary bs = ballots.get(FIRST_OBJECT);
				// Profile fields
				profileDetailsLayout.addComponent(createInfoRow("Ballot ID:", bs.getEmbeddedId().getBallotId(), VaadinIcons.CLIPBOARD_USER));
				profileDetailsLayout.addComponent(createInfoRow("RM:", bs.getRm(), VaadinIcons.CALENDAR));
				profileDetailsLayout.addComponent(createInfoRow("Vote Date:", String.valueOf(bs.getVoteDate()), VaadinIcons.CALENDAR));
				profileDetailsLayout.addComponent(createInfoRow("Issue:", bs.getEmbeddedId().getIssue(), VaadinIcons.CLIPBOARD_TEXT));
				profileDetailsLayout.addComponent(createInfoRow("Concern:", bs.getEmbeddedId().getConcern(), VaadinIcons.CLIPBOARD));
				profileDetailsLayout.addComponent(createInfoRow("Ballot Type:", bs.getBallotType(), VaadinIcons.BULLETS));
				profileDetailsLayout.addComponent(createInfoRow("Label:", bs.getLabel(), VaadinIcons.FILE_TEXT));

				// Stats fields
				serviceStatsLayout.addComponent(createInfoRow("Total Votes:", String.valueOf(bs.getTotalVotes()), VaadinIcons.GROUP));
				serviceStatsLayout.addComponent(createInfoRow("Yes Votes:", String.valueOf(bs.getYesVotes()), VaadinIcons.THUMBS_UP));
				serviceStatsLayout.addComponent(createInfoRow("No Votes:", String.valueOf(bs.getNoVotes()), VaadinIcons.THUMBS_DOWN));
				serviceStatsLayout.addComponent(createInfoRow("Abstain Votes:", String.valueOf(bs.getAbstainVotes()), VaadinIcons.SPLIT));
				serviceStatsLayout.addComponent(createInfoRow("Absent Votes:", String.valueOf(bs.getAbsentVotes()), VaadinIcons.EXIT_O));
				serviceStatsLayout.addComponent(createInfoRow("Approved:", String.valueOf(bs.isApproved()), VaadinIcons.CHECK));
			}

			// Spacer before Party Ballot Summary
			final Label tableDivider = new Label("<hr/>", ContentMode.HTML);
			tableDivider.addStyleName("card-divider");
			tableDivider.setWidth("100%");
			panelContent.addComponent(tableDivider);

			// Party Ballot Summary table
			getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent,
					ViewRiksdagenVoteDataBallotPartySummary.class, partyBallotList, PARTY_BALLOT_SUMMARY,
					NESTED_PROPERTIES, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, EMBEDDED_ID_PARTY, null);

			// Overview layout after table
			final VerticalLayout overviewLayout = createOverviewLayout();
			panelContent.addComponent(overviewLayout);
			panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);
			getBallotMenuItemFactory().createOverviewPage(overviewLayout, pageId);

			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_BALLOT_VIEW, ApplicationEventGroup.USER, NAME,
					parameters, pageId);
		}

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
		final String pageId = getPageId(parameters);
		return NAME.equals(page)
				&& (StringUtils.isEmpty(parameters) || parameters.equals(pageId) || parameters.contains(PageMode.OVERVIEW.toString()));
	}

}
