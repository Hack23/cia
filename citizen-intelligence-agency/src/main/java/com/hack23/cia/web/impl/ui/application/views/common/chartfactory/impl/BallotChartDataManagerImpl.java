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


	@Override
	public void createChart(final Tab tab,final AbstractOrderedLayout content,final List<ViewRiksdagenVoteDataBallotPartySummary> partyList) {
		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		series.addSeries(new XYseries().setLabel("Yes"));
		series.addSeries(new XYseries().setLabel("No"));
		series.addSeries(new XYseries().setLabel("Abstain"));
		series.addSeries(new XYseries().setLabel("Absent"));

		String caption=null;
		for (final ViewRiksdagenVoteDataBallotPartySummary viewRiksdagenVoteDataBallotPartySummary : partyList) {
			if (caption == null) {
				caption = "Party Summary : " + viewRiksdagenVoteDataBallotPartySummary.getEmbeddedId().getIssue() + " " + viewRiksdagenVoteDataBallotPartySummary.getEmbeddedId().getConcern();
				content.setCaption(caption);
				tab.setCaption(caption);
			}

			dataSeries.newSeries()
			.add(getPartyName(viewRiksdagenVoteDataBallotPartySummary.getEmbeddedId().getParty()), viewRiksdagenVoteDataBallotPartySummary.getPartyYesVotes())
			.add(getPartyName(viewRiksdagenVoteDataBallotPartySummary.getEmbeddedId().getParty()),viewRiksdagenVoteDataBallotPartySummary.getPartyNoVotes())
			.add(getPartyName(viewRiksdagenVoteDataBallotPartySummary.getEmbeddedId().getParty()),viewRiksdagenVoteDataBallotPartySummary.getPartyAbstainVotes())
			.add(getPartyName(viewRiksdagenVoteDataBallotPartySummary.getEmbeddedId().getParty()),viewRiksdagenVoteDataBallotPartySummary.getPartyAbsentVotes());
		}


		addChart(content,caption + " ( 4 circles Yes/No/Abstain/Absent votes by party )", new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsDonoutChartWithSeries(series)).show(), true);
	}



	@Override
	public void createChart(final Tab tab,final AbstractOrderedLayout content,final ViewRiksdagenVoteDataBallotSummary viewRiksdagenVoteDataBallotSummary) {
		final DataSeries dataSeries = new DataSeries();

		dataSeries.newSeries().add("Yes", viewRiksdagenVoteDataBallotSummary.getYesVotes());
		dataSeries.newSeries().add("No", viewRiksdagenVoteDataBallotSummary.getNoVotes());
		dataSeries.newSeries().add("Abstain", viewRiksdagenVoteDataBallotSummary.getAbstainVotes());
		dataSeries.newSeries().add("Absent", viewRiksdagenVoteDataBallotSummary.getAbsentVotes());

		final String caption = "Summary : " +viewRiksdagenVoteDataBallotSummary.getEmbeddedId().getIssue() + " " + viewRiksdagenVoteDataBallotSummary.getEmbeddedId().getConcern();
		tab.setCaption(caption);

		addChart(content,caption, new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsDonoutChart()).show(), true);
	}


}
