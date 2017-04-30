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
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class BallotDecisionSummaryPageModContentFactoryImpl.
 */
@Component
public final class PoliticianBallotDecisionSummaryPageModContentFactoryImpl extends AbstractPoliticianPageModContentFactoryImpl {

	/**
	 * Instantiates a new politician ballot decision summary page mod content
	 * factory impl.
	 */
	public PoliticianBallotDecisionSummaryPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PoliticianPageMode.BALLOTDECISIONSUMMARY.toString());
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DataContainer<PersonData, String> dataContainer = getApplicationManager()
				.getDataContainer(PersonData.class);

		final PersonData personData = dataContainer.load(pageId);
		if (personData != null) {

			final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenPolitician.class);

			final ViewRiksdagenPolitician viewRiksdagenPolitician = politicianDataContainer.load(personData.getId());

			getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);

			LabelFactory.createHeader2Label(panelContent,PoliticianPageMode.BALLOTDECISIONSUMMARY.toString());


			final DataContainer<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary, ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId> committeeBallotDecisionPartyDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class);

			final List<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary> decisionPartySummaryList = committeeBallotDecisionPartyDataContainer
					.findOrderedByPropertyListByEmbeddedProperty(
							ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class,
							ViewRiksdagenCommitteeBallotDecisionPoliticianSummary_.embeddedId,
							ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId.class,
							ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId_.intressentId, pageId,
							ViewRiksdagenCommitteeBallotDecisionPoliticianSummary_.voteDate);

			final BeanItemContainer<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary> committeeBallotDecisionPartyDataSource = new BeanItemContainer<>(
					ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class, decisionPartySummaryList);

			getGridFactory()
					.createBasicBeanItemNestedPropertiesGrid(panelContent,committeeBallotDecisionPartyDataSource,
							"Committee Ballot Decision Politician Summary",
							new String[] { "embeddedId.concern", "embeddedId.issue" },
							new String[] { "voteDate", "rm", "org", "committeeReport", "title", "subTitle",
									"winner", "embeddedId.concern", "embeddedId.issue", "vote", "won", "rebel",
									"noWinner", "approved", "partyApproved", "againstProposalNumber",
									"againstProposalParties", "totalVotes", "partyTotalVotes", "yesVotes",
									"partyYesVotes", "noVotes", "partyNoVotes", "partyAbstainVotes",
									"abstainVotes", "partyAbsentVotes", "absentVotes", "bornYear",
									"partyAvgBornYear", "avgBornYear", "ballotType", "decisionType",
									"ballotId" },
							new String[] { "label", "endNumber", "publicDate", "createdDate", "embeddedId",
									"partyNoWinner", "partyPercentageYes", "partyPercentageNo",
									"partyPercentageAbsent", "partyPercentageAbstain", "percentageYes",
									"percentageNo", "percentageAbsent", "percentageAbstain", "firstName",
									"lastName", "party", "ballotId", "decisionType", "ballotType", "againstProposalNumber" },
							new PageItemPropertyClickListener(UserViews.BALLOT_VIEW_NAME, "ballotId"), "ballotId", null);

			pageCompleted(parameters, panel, pageId, viewRiksdagenPolitician);

		}
		return panelContent;

	}
}
