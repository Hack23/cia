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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.List;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.BallotChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.TabSheet.Tab;

/**
 * The Class BallotChartDataManagerImpl.
 */
@Service
public final class BallotChartDataManagerImpl extends AbstractChartDataManagerImpl implements BallotChartDataManager {

	/**
	 * Instantiates a new ballot chart data manager impl.
	 */
	public BallotChartDataManagerImpl() {
		super();
	}


	/**
	 * Creates the chart.
	 *
	 * @param tab the tab
	 * @param content the content
	 * @param partySummaries the party summaries
	 */
	@Override
	public void createChart(final Tab tab,final AbstractOrderedLayout content,final List<ViewRiksdagenVoteDataBallotPartySummary> partySummaries) {
		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		series.addSeries(new XYseries().setLabel("Yes"));
		series.addSeries(new XYseries().setLabel("No"));
		series.addSeries(new XYseries().setLabel("Abstain"));
		series.addSeries(new XYseries().setLabel("Absent"));

		String caption=null;
		for (final ViewRiksdagenVoteDataBallotPartySummary partySummary : partySummaries) {
			if (caption == null) {
				caption = "Party Summary : " + partySummary.getEmbeddedId().getIssue() + " " + partySummary.getEmbeddedId().getConcern();
				content.setCaption(caption);
				tab.setCaption(caption);
			}

			dataSeries.newSeries()
			.add(getPartyName(partySummary.getEmbeddedId().getParty()), partySummary.getPartyYesVotes())
			.add(getPartyName(partySummary.getEmbeddedId().getParty()),partySummary.getPartyNoVotes())
			.add(getPartyName(partySummary.getEmbeddedId().getParty()),partySummary.getPartyAbstainVotes())
			.add(getPartyName(partySummary.getEmbeddedId().getParty()),partySummary.getPartyAbsentVotes());
		}


		addChart(content,caption + " ( 4 circles Yes/No/Abstain/Absent votes by party )", new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsDonoutChartWithSeries(series)).show(), true);
	}



	/**
	 * Creates the chart.
	 *
	 * @param tab the tab
	 * @param content the content
	 * @param ballotSummary the ballot summary
	 */
	@Override
	public void createChart(final Tab tab,final AbstractOrderedLayout content,final ViewRiksdagenVoteDataBallotSummary ballotSummary) {
		final DataSeries dataSeries = new DataSeries();

		dataSeries.newSeries().add("Yes", ballotSummary.getYesVotes());
		dataSeries.newSeries().add("No", ballotSummary.getNoVotes());
		dataSeries.newSeries().add("Abstain", ballotSummary.getAbstainVotes());
		dataSeries.newSeries().add("Absent", ballotSummary.getAbsentVotes());

		final String caption = "Summary : " +ballotSummary.getEmbeddedId().getIssue() + " " + ballotSummary.getEmbeddedId().getConcern();
		tab.setCaption(caption);

		addChart(content,caption, new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsDonoutChart()).show(), true);
	}


}
