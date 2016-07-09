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

import java.util.List;

import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPartyEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPartyEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.BallotChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class BallotChartsPageModContentFactoryImpl.
 */
@Component
public final class BallotChartsPageModContentFactoryImpl extends AbstractBallotPageModContentFactoryImpl {

	private static final int FIRST_OBJECT = 0;

	/** The Constant COMMITTEE. */
	private static final String BALLOT = "Ballot:";

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

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PageMode.CHARTS.toString());
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

		
		final List<ViewRiksdagenVoteDataBallotSummary> ballots = dataContainer.findListByEmbeddedProperty(ViewRiksdagenVoteDataBallotSummary.class, ViewRiksdagenVoteDataBallotSummary_.embeddedId, RiksdagenVoteDataBallotEmbeddedId.class, RiksdagenVoteDataBallotEmbeddedId_.ballotId, pageId);

		List<ViewRiksdagenVoteDataBallotPartySummary> partyBallotList = dataPartyContainer.findListByEmbeddedProperty(ViewRiksdagenVoteDataBallotPartySummary.class, ViewRiksdagenVoteDataBallotPartySummary_.embeddedId, RiksdagenVoteDataBallotPartyEmbeddedId.class, RiksdagenVoteDataBallotPartyEmbeddedId_.ballotId, pageId);
		
		if (!ballots.isEmpty()) {
			getBallotMenuItemFactory().createBallotMenuBar(menuBar, pageId);

		
				
			
				final Label createHeader2Label = LabelFactory.createHeader2Label(CHARTS);
				panelContent.addComponent(createHeader2Label);
				
				HorizontalLayout horizontalLayout = new HorizontalLayout();
				horizontalLayout.setMargin(true);
				horizontalLayout.setWidth(100, Unit.PERCENTAGE);
				horizontalLayout.setHeight(100, Unit.PERCENTAGE);
				
				panelContent.addComponent(horizontalLayout);
				panelContent.setExpandRatio(horizontalLayout, ContentRatio.LARGE);
				
				for (ViewRiksdagenVoteDataBallotSummary viewRiksdagenVoteDataBallotSummary : ballots) {
					DCharts createChart = ballotChartDataManager.createChart(viewRiksdagenVoteDataBallotSummary);
					horizontalLayout.addComponent(createChart);
					horizontalLayout.setExpandRatio(createChart, ContentRatio.GRID);
				}

				DCharts createPartyChart = ballotChartDataManager.createChart(partyBallotList);
				horizontalLayout.addComponent(createPartyChart);
				horizontalLayout.setExpandRatio(createPartyChart, ContentRatio.GRID);

				panel.setCaption(BALLOT + pageId);
				getPageActionEventHelper().createPageEvent(ViewAction.VISIT_BALLOT_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);
		}
		return panelContent;

	}

}
