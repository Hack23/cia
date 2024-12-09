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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.BallotChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class BallotChartsPageModContentFactoryImpl.
 */
@Component
public final class BallotChartsPageModContentFactoryImpl extends AbstractBallotPageModContentFactoryImpl {

	/** The Constant OVERVIEW. */
	private static final String CHARTS = "Charts";

	@Autowired
	private BallotChartDataManager ballotChartDataManager;

	/**
	 * Instantiates a new ballot charts page mod content factory impl.
	 */
	public BallotChartsPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the issue concern map.
	 *
	 * @param partyBallotList
	 *            the party ballot list
	 * @return the map
	 */
	private static Map<String,List<ViewRiksdagenVoteDataBallotPartySummary>> createIssueConcernMap(final List<ViewRiksdagenVoteDataBallotPartySummary> partyBallotList) {
		final Map<String,List<ViewRiksdagenVoteDataBallotPartySummary>> concernIssuePartyBallotSummaryMap = new HashMap<>();
		for (final ViewRiksdagenVoteDataBallotPartySummary partySummary: partyBallotList) {

			if (partySummary.getEmbeddedId().getIssue() !=null || partySummary.getEmbeddedId().getConcern() != null ) {
				final String key = partySummary.getEmbeddedId().getIssue() + partySummary.getEmbeddedId().getConcern();
				final List<ViewRiksdagenVoteDataBallotPartySummary> partySummarList = concernIssuePartyBallotSummaryMap.computeIfAbsent(key, k -> new ArrayList<>());
				partySummarList.add(partySummary);
			}
		}

		return concernIssuePartyBallotSummaryMap;
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

			final List<ViewRiksdagenVoteDataBallotPartySummary> partyBallotList = dataPartyContainer.findListByEmbeddedProperty(ViewRiksdagenVoteDataBallotPartySummary.class, ViewRiksdagenVoteDataBallotPartySummary_.embeddedId, RiksdagenVoteDataBallotPartyEmbeddedId.class, RiksdagenVoteDataBallotPartyEmbeddedId_.ballotId, pageId);

			final DataContainer<ViewRiksdagenCommitteeBallotDecisionSummary, ViewRiksdagenCommitteeBallotDecisionEmbeddedId> dataDecisionContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenCommitteeBallotDecisionSummary.class);
			final List<ViewRiksdagenCommitteeBallotDecisionSummary> decisionSummaries = dataDecisionContainer
					.getAllBy(ViewRiksdagenCommitteeBallotDecisionSummary_.ballotId, pageId);

			createPageHeader(panel, panelContent, "Ballot Charts", "Ballot Trends and Visualizations", "Provides insights into election trends by visualizing ballot data, assisting in strategic decision-making and voter engagement analysis.");

				final TabSheet tabsheet = new TabSheet();
				tabsheet.setWidth(100, Unit.PERCENTAGE);
				tabsheet.setHeight(100, Unit.PERCENTAGE);

				panelContent.addComponent(tabsheet);
				panelContent.setExpandRatio(tabsheet, ContentRatio.LARGE);

				Collections.sort(ballots, (Comparator<ViewRiksdagenVoteDataBallotSummary>) (o1, o2) -> (o1.getEmbeddedId().getIssue() + o2.getEmbeddedId().getConcern()).compareTo(o1.getEmbeddedId().getIssue() + o2.getEmbeddedId().getConcern()));

				for (final ViewRiksdagenVoteDataBallotSummary viewRiksdagenVoteDataBallotSummary : ballots) {
					final HorizontalLayout tabContent = new HorizontalLayout();
					tabContent.setWidth(100, Unit.PERCENTAGE);
					tabContent.setHeight(100, Unit.PERCENTAGE);
					final Tab tab = tabsheet.addTab(tabContent);

					ballotChartDataManager.createChart(tab,tabContent,viewRiksdagenVoteDataBallotSummary);
				}

				final Map<String, List<ViewRiksdagenVoteDataBallotPartySummary>> concernIssuePartyBallotSummaryMap = createIssueConcernMap(partyBallotList);

				for (final List<ViewRiksdagenVoteDataBallotPartySummary> partyBallotSummaryList : concernIssuePartyBallotSummaryMap.values()) {
					final HorizontalLayout tabContent = new HorizontalLayout();
					tabContent.setWidth(100, Unit.PERCENTAGE);
					tabContent.setHeight(100, Unit.PERCENTAGE);
					final Tab tab = tabsheet.addTab(tabContent);

					ballotChartDataManager.createChart(tab,tabContent,partyBallotSummaryList);
				}

				if (!decisionSummaries.isEmpty()) {
					panel.setCaption(new StringBuilder().append("Ballot Charts : ").append(decisionSummaries.get(0).getTitle()).append(" - ").append(decisionSummaries.get(0).getSubTitle()).toString());
				} else {
					panel.setCaption(new StringBuilder().append("Ballot Charts : ").append(ballots.get(0).getEmbeddedId().getConcern()).toString());
				}

				getPageActionEventHelper().createPageEvent(ViewAction.VISIT_BALLOT_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);
			}
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PageMode.CHARTS.toString());
	}

}
