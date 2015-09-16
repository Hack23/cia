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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory;

import java.util.List;
import java.util.Map;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.data.DataSeries;

import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisionTypeDailySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisionTypeOrgDailySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenDocumentTypeDailySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenOrgDocumentDailySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPartyDocumentDailySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocumentDailySummary;
import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;

/**
 * The Interface ChartDataManager.
 */
public interface ChartDataManager {


	/**
	 * Gets the view riksdagen vote data ballot party summary.
	 *
	 * @param party
	 *            the party
	 * @return the view riksdagen vote data ballot party summary
	 */
	List<ViewRiksdagenVoteDataBallotPartySummary> getViewRiksdagenVoteDataBallotPartySummary(
			String party);

	/**
	 * Gets the view riksdagen vote data ballot politician summary.
	 *
	 * @param id
	 *            the id
	 * @return the view riksdagen vote data ballot politician summary
	 */
	List<ViewRiksdagenVoteDataBallotPoliticianSummary> getViewRiksdagenVoteDataBallotPoliticianSummary(
			String id);

	/**
	 * Gets the party map.
	 *
	 * @return the party map
	 */
	Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> getPartyMap();

	/**
	 * Gets the document type map.
	 *
	 * @return the document type map
	 */
	Map<String, List<ViewRiksdagenDocumentTypeDailySummary>> getDocumentTypeMap();

	/**
	 * Gets the committee decision type map.
	 *
	 * @return the committee decision type map
	 */
	Map<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> getCommitteeDecisionTypeMap();

	/**
	 * Gets the committee decision type org map.
	 *
	 * @return the committee decision type org map
	 */
	Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> getCommitteeDecisionTypeOrgMap();

	/**
	 * Gets the committee ballot decision party map.
	 *
	 * @return the committee ballot decision party map
	 */
	Map<String, List<ViewRiksdagenCommitteeBallotDecisionPartySummary>> getCommitteeBallotDecisionPartyMap();

	/**
	 * Gets the committee ballot decision politician map.
	 *
	 * @return the committee ballot decision politician map
	 */
	Map<String, List<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary>> getCommitteeBallotDecisionPoliticianMap();

	/**
	 * Gets the max size view riksdagen vote data ballot party summary daily.
	 *
	 * @return the max size view riksdagen vote data ballot party summary daily
	 */
	List<ViewRiksdagenVoteDataBallotPartySummaryDaily> getMaxSizeViewRiksdagenVoteDataBallotPartySummaryDaily();

	/**
	 * Gets the view riksdagen politician document daily summary map.
	 *
	 * @return the view riksdagen politician document daily summary map
	 */
	Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> getViewRiksdagenPoliticianDocumentDailySummaryMap();

	/**
	 * Gets the view riksdagen party document daily summary map.
	 *
	 * @return the view riksdagen party document daily summary map
	 */
	Map<String, List<ViewRiksdagenPartyDocumentDailySummary>> getViewRiksdagenPartyDocumentDailySummaryMap();

	/**
	 * Gets the view riksdagen org document daily summary map.
	 *
	 * @return the view riksdagen org document daily summary map
	 */
	Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> getViewRiksdagenOrgDocumentDailySummaryMap();

	/**
	 * Gets the view riksdagen committee decision type daily summary map.
	 *
	 * @return the view riksdagen committee decision type daily summary map
	 */
	Map<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> getViewRiksdagenCommitteeDecisionTypeDailySummaryMap();

	/**
	 * Gets the view riksdagen committee decision type org daily summary map.
	 *
	 * @return the view riksdagen committee decision type org daily summary map
	 */
	Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> getViewRiksdagenCommitteeDecisionTypeOrgDailySummaryMap();

	/**
	 * Creates the party winner chart.
	 *
	 * @return the d charts
	 */
	DCharts createPartyWinnerChart();

	/**
	 * Creates the document type chart.
	 *
	 * @return the d charts
	 */
	DCharts createDocumentTypeChart();

	/**
	 * Creates the decision type chart.
	 *
	 * @return the d charts
	 */
	DCharts createDecisionTypeChart();

	/**
	 * Creates the decision type chart.
	 *
	 * @param org
	 *            the org
	 * @return the d charts
	 */
	DCharts createDecisionTypeChart(String org);

	/**
	 * Creates the document history chart by org.
	 *
	 * @param org
	 *            the org
	 * @return the d charts
	 */
	DCharts createDocumentHistoryChartByOrg(String org);

	/**
	 * Creates the party line chart.
	 *
	 * @param partyId
	 *            the party id
	 * @return the d charts
	 */
	DCharts createPartyLineChart(String partyId);

	/**
	 * Creates the document history party chart.
	 *
	 * @param org
	 *            the org
	 * @return the d charts
	 */
	DCharts createDocumentHistoryPartyChart(String org);

	/**
	 * Creates the person document history chart.
	 *
	 * @param personId
	 *            the person id
	 * @return the d charts
	 */
	DCharts createPersonDocumentHistoryChart(String personId);

	/**
	 * Creates the person line chart.
	 *
	 * @param personId
	 *            the person id
	 * @return the d charts
	 */
	DCharts createPersonLineChart(String personId);

	/**
	 * Creates the indicator chart.
	 *
	 * @param list
	 *            the list
	 * @param summary
	 *            the summary
	 * @return the d charts
	 */
	DCharts createIndicatorChart(List<WorldBankData> list, ViewWorldbankIndicatorDataCountrySummary summary);

	/**
	 * Creates the chart panel.
	 *
	 * @param dataSeries
	 *            the data series
	 * @param caption
	 *            the caption
	 * @return the d charts
	 */
	DCharts createChartPanel(DataSeries dataSeries, String caption);




}
