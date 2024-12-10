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
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 *$Id$
 *$HeadURL$
*/
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionSummary_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
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
public final class CommitteeBallotDecisionSummaryPageModContentFactoryImpl
extends AbstractCommitteePageModContentFactoryImpl {

	private static final String BALLOT_ID = "ballotId";

	private static final String[] COLUMN_ORDER = { "voteDate", "embeddedId.concern", "embeddedId.id",
			"committeeReport", "embeddedId.issue", "rm", "title", "subTitle", "endNumber", "org", "createdDate",
			"publicDate", BALLOT_ID, "decisionType", "againstProposalParties", "againstProposalNumber", "winner",
			"ballotType", "label", "avgBornYear", "totalVotes", "yesVotes", "noVotes", "abstainVotes", "absentVotes",
			"approved", "noWinner", "percentageYes", "percentageNo", "percentageAbsent", "percentageAbstain",
			"percentageMale" };

	private static final String COMMITTEE_BALLOT_DECISION_SUMMARY = "Committee Ballot Decision Summary";

	private static final String[] HIDE_COLUMNS = { "embeddedId", "embeddedId.id", "endNumber", "org",
			"createdDate", "publicDate", BALLOT_ID, "decisionType", "label", "againstProposalNumber", "avgBornYear",
			"percentageMale", "approved", "noWinner", "ballotType", "percentageYes", "percentageNo", "percentageAbsent",
			"percentageAbstain" };

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.BALLOT_VIEW_NAME, BALLOT_ID);

	private static final String[] NESTED_PROPERTIES = { "embeddedId.concern", "embeddedId.issue",
			"embeddedId.id" };

	/**
	 * Instantiates a new committee ballot decision summary page mod content
	 * factory impl.
	 */
	public CommitteeBallotDecisionSummaryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenCommittee viewRiksdagenCommittee = getItem(parameters);
		getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent, "Committee Ballot Decision Summary  " + viewRiksdagenCommittee.getEmbeddedId().getDetail(), "Ballot Decision Summary", "Summary of ballot decisions made by the specified committee.");

		final DataContainer<ViewRiksdagenCommitteeBallotDecisionSummary, ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId> committeeBallotDecisionPartyDataContainer = getApplicationManager()
						.getDataContainer(ViewRiksdagenCommitteeBallotDecisionSummary.class);

		final List<ViewRiksdagenCommitteeBallotDecisionSummary> decisionPartySummaryList = committeeBallotDecisionPartyDataContainer
				.findOrderedListByProperty(ViewRiksdagenCommitteeBallotDecisionSummary_.org,
						pageId.toUpperCase(Locale.ENGLISH), ViewRiksdagenCommitteeBallotDecisionSummary_.createdDate);

		getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent,
				ViewRiksdagenCommitteeBallotDecisionSummary.class, decisionPartySummaryList,
				COMMITTEE_BALLOT_DECISION_SUMMARY, NESTED_PROPERTIES, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, BALLOT_ID,
				null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, CommitteePageMode.BALLOTDECISIONSUMMARY.toString());
	}

}
