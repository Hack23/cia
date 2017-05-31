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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GenericChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class VotesHistoryPageModContentFactoryImpl.
 */
@Component
public final class PoliticianVotesHistoryPageModContentFactoryImpl extends AbstractPoliticianPageModContentFactoryImpl {

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

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PoliticianPageMode.VOTEHISTORY.toString());
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

			LabelFactory.createHeader2Label(panelContent,PoliticianPageMode.VOTEHISTORY.toString());


			final BeanItemContainer<ViewRiksdagenVoteDataBallotPoliticianSummary> politicianBallotDataSource = new BeanItemContainer<>(
					ViewRiksdagenVoteDataBallotPoliticianSummary.class,
					viewRiksdagenVoteDataBallotPoliticianSummaryChartDataManager.findByValue(personData.getId()));

			getGridFactory().createBasicBeanItemNestedPropertiesGrid(panelContent,
					politicianBallotDataSource, "Ballots",
					new String[] { "embeddedId.ballotId", "embeddedId.concern", "embeddedId.issue" },
					new String[] { "voteDate", "rm", "label", "embeddedId.concern", "embeddedId.issue", "vote",
							"won", "partyWon", "rebel", "noWinner", "approved", "partyApproved", "totalVotes",
							"partyTotalVotes", "yesVotes", "partyYesVotes", "noVotes", "partyNoVotes",
							"partyAbstainVotes", "abstainVotes", "partyAbsentVotes", "absentVotes", "bornYear",
							"partyAvgBornYear", "avgBornYear", "gender", "partyPercentageMale",
							"percentageMale", "ballotType", "embeddedId.ballotId" },
					new String[] { "embeddedId", "partyNoWinner", "partyPercentageYes", "partyPercentageNo",
							"partyPercentageAbsent", "partyPercentageAbstain", "percentageYes", "percentageNo",
							"percentageAbsent", "percentageAbstain", "firstName", "lastName", "party", "embeddedId.ballotId", "ballotType" },
					new PageItemPropertyClickListener(UserViews.BALLOT_VIEW_NAME, "embeddedId.ballotId"), "embeddedId.ballotId", null);

			pageCompleted(parameters, panel, pageId, viewRiksdagenPolitician);

		}
		return panelContent;

	}
}
