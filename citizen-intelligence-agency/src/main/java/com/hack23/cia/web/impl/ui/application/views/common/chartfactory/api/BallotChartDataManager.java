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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api;

import java.util.List;

import org.dussan.vaadin.dcharts.DCharts;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary;

/**
 * The Interface BallotChartDataManager.
 */
public interface BallotChartDataManager {


	/**
	 * Creates the chart.
	 *
	 * @param viewRiksdagenVoteDataBallotSummary
	 *            the view riksdagen vote data ballot summary
	 * @return the d charts
	 */
	DCharts createChart(ViewRiksdagenVoteDataBallotSummary viewRiksdagenVoteDataBallotSummary);
	
	/**
	 * Creates the chart.
	 *
	 * @param partyList
	 *            the party list
	 * @return the d charts
	 */
	DCharts createChart(List<ViewRiksdagenVoteDataBallotPartySummary> partyList);
}
