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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.List;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.DataLabels;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.locations.LegendLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.series.DonutRenderer;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.BallotChartDataManager;

/**
 * The Class BallotChartDataManagerImpl.
 */
@Service
public final class BallotChartDataManagerImpl implements BallotChartDataManager {

	/**
	 * Instantiates a new ballot chart data manager impl.
	 */
	public BallotChartDataManagerImpl() {
		super();
	}

	@Override
	public DCharts createChart(final ViewRiksdagenVoteDataBallotSummary viewRiksdagenVoteDataBallotSummary) {
		final DataSeries dataSeries = new DataSeries();

		dataSeries.newSeries().add("Yes", viewRiksdagenVoteDataBallotSummary.getYesVotes());
		dataSeries.newSeries().add("No", viewRiksdagenVoteDataBallotSummary.getNoVotes());
		dataSeries.newSeries().add("Abstain", viewRiksdagenVoteDataBallotSummary.getAbstainVotes());
		dataSeries.newSeries().add("Absent", viewRiksdagenVoteDataBallotSummary.getAbsentVotes());

		final SeriesDefaults seriesDefaults = new SeriesDefaults().setRenderer(SeriesRenderers.DONUT)
				.setRendererOptions(new DonutRenderer().setSliceMargin(3).setStartAngle(-90).setShowDataLabels(true)
						.setDataLabels(DataLabels.VALUE));

		final Legend legend = new Legend().setShow(true).setPlacement(LegendPlacements.OUTSIDE_GRID)
				.setLocation(LegendLocations.WEST);

		final Highlighter highlighter = new Highlighter()
				.setShow(true)
				.setShowTooltip(true)
				.setTooltipAlwaysVisible(true)
				.setKeepTooltipInsideChart(true);
		
		final Options options = new Options().setSeriesDefaults(seriesDefaults).setLegend(legend).setHighlighter(highlighter);

		return new DCharts().setDataSeries(dataSeries).setOptions(options).show();
	}

	@Override
	public DCharts createChart(final List<ViewRiksdagenVoteDataBallotPartySummary> partyList) {
		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		series.addSeries(new XYseries().setLabel("Yes"));
		series.addSeries(new XYseries().setLabel("No"));
		series.addSeries(new XYseries().setLabel("Abstain"));
		series.addSeries(new XYseries().setLabel("Absent"));
		
		for (final ViewRiksdagenVoteDataBallotPartySummary viewRiksdagenVoteDataBallotPartySummary : partyList) {
			
			
			dataSeries.newSeries()
			.add(viewRiksdagenVoteDataBallotPartySummary.getEmbeddedId().getParty(), viewRiksdagenVoteDataBallotPartySummary.getPartyYesVotes())
			.add(viewRiksdagenVoteDataBallotPartySummary.getEmbeddedId().getParty(),viewRiksdagenVoteDataBallotPartySummary.getPartyNoVotes())
			.add(viewRiksdagenVoteDataBallotPartySummary.getEmbeddedId().getParty(),viewRiksdagenVoteDataBallotPartySummary.getPartyAbstainVotes())
			.add(viewRiksdagenVoteDataBallotPartySummary.getEmbeddedId().getParty(),viewRiksdagenVoteDataBallotPartySummary.getPartyAbsentVotes());			
		}	

		final SeriesDefaults seriesDefaults = new SeriesDefaults().setRenderer(SeriesRenderers.DONUT)
				.setRendererOptions(new DonutRenderer().setSliceMargin(3).setStartAngle(-90).setShowDataLabels(true)
						.setDataLabels(DataLabels.VALUE));

		final Legend legend = new Legend().setShow(true).setPlacement(LegendPlacements.OUTSIDE_GRID)
				.setLocation(LegendLocations.WEST);

		final Highlighter highlighter = new Highlighter()
				.setShow(true)
				.setShowTooltip(true)
				.setTooltipAlwaysVisible(true)
				.setKeepTooltipInsideChart(true);
		
		final Options options = new Options().setSeriesDefaults(seriesDefaults).setLegend(legend).setHighlighter(highlighter).addOption(series);

		return new DCharts().setDataSeries(dataSeries).setOptions(options).show();
	}

}
