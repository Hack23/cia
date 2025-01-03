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

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPartyPeriodSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryDaily;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PartyChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
public final class PartyChartDataManagerImpl extends AbstractChartDataManagerImpl implements PartyChartDataManager {


	/** The Constant NUMBER_BALLOTS. */
	private static final String NUMBER_BALLOTS = "Number ballots";

	/** The Constant PARTY_ABSENT. */
	private static final String PARTY_ABSENT = "Party Absent";

	/** The Constant PARTY_WON. */
	private static final String PARTY_WON = "Party Won";

	/** The party map. */
	private Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> partyMap;

	/**
	 * Instantiates a new party chart data manager impl.
	 */
	public PartyChartDataManagerImpl() {
		super();
	}


	/**
	 * Adds the party data.
	 *
	 * @param dataSeries       the data series
	 * @param list             the list
	 * @param dataValueFunction the data value function
	 */
	private static void addPartyData(final DataSeries dataSeries,
			final List<ViewRiksdagenVoteDataBallotPartySummaryDaily> list, final Function<ViewRiksdagenVoteDataBallotPartySummaryDaily, Object> dataValueFunction) {
		dataSeries.newSeries();
		for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
			if (viewRiksdagenVoteDataBallotPartySummaryDaily != null) {
				dataSeries.add(
						DateUtils.formatDate(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getVoteDate()),
						dataValueFunction.apply(viewRiksdagenVoteDataBallotPartySummaryDaily));
			}
		}
	}


	/**
	 * Adds the ballot data.
	 *
	 * @param dataValueCalculator the data value calculator
	 * @param dataSeries          the data series
	 * @param series              the series
	 * @param entry               the entry
	 */
	private void addBallotData(final DataValueCalculator dataValueCalculator, final DataSeries dataSeries,
			final Series series,
			final Entry<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> entry) {
		series.addSeries(new XYseries().setLabel(getPartyName(entry.getKey())));

		dataSeries.newSeries();
		final List<ViewRiksdagenVoteDataBallotPartySummaryDaily> list = entry.getValue();
		for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
			if (viewRiksdagenVoteDataBallotPartySummaryDaily != null) {
				dataSeries.add(
						DateUtils.formatDate(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getVoteDate()),
								dataValueCalculator.getDataValue(viewRiksdagenVoteDataBallotPartySummaryDaily));
			}
		}
	}





	/**
	 * Creates the party age chart.
	 *
	 * @param content the content
	 */
	@Override
	public void createPartyAgeChart(final AbstractOrderedLayout content) {
	        createPartyBallotChart(content,viewRiksdagenVoteDataBallotPartySummaryDaily -> ZonedDateTime.now(ZoneId.of("Europe/Stockholm")).getYear() - viewRiksdagenVoteDataBallotPartySummaryDaily.getPartyAvgBornYear().intValue());
	}

	/**
	 * Creates the party ballot chart.
	 *
	 * @param content the content
	 * @param dataValueCalculator            the data value calculator
	 * @return the d charts
	 */
	private void createPartyBallotChart(final AbstractOrderedLayout content,final DataValueCalculator dataValueCalculator) {
		final Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> map = getPartyMap();

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		for (final Entry<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> entry : map.entrySet()) {

			if (!"-".equals(entry.getKey())) {
				addBallotData(dataValueCalculator, dataSeries, series, entry);
			}
		}

		ChartUtils.addChart(content,"Party ballot chart", new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsXYDateFloatLegendInsideOneColumn(series)).show(), true);
	}


	/**
	 * Creates the party gender chart.
	 *
	 * @param content the content
	 */
	@Override
	public void createPartyGenderChart(final AbstractOrderedLayout content) {

		createPartyBallotChart(content,viewRiksdagenVoteDataBallotPartySummaryDaily -> 100 - viewRiksdagenVoteDataBallotPartySummaryDaily.getPartyAvgPercentageMale().intValue());

	}

	/**
	 * Creates the party line chart.
	 *
	 * @param content the content
	 * @param partyId the party id
	 */
	@Override
	public void createPartyLineChart(final AbstractOrderedLayout content,final String partyId) {

		final List<ViewRiksdagenVoteDataBallotPartySummaryDaily> list = getViewRiksdagenVoteDataBallotPartySummaryDaily(
				partyId);

		if (list != null) {
			final Series series = new Series().addSeries(new XYseries().setLabel(PARTY_WON))
					.addSeries(new XYseries().setLabel(PARTY_ABSENT));

			final DataSeries dataSeries = new DataSeries();

			addPartyData(dataSeries, list, ViewRiksdagenVoteDataBallotPartySummaryDaily::getPartyWonPercentage);
			addPartyData(dataSeries, list, ViewRiksdagenVoteDataBallotPartySummaryDaily::getPartyPercentageAbsent);

			ChartUtils.addChart(content,"Party result by", new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsPartyLineChart(series)).show(), true);
		}
	}


	/**
	 * Creates the party winner chart.
	 *
	 * @param content the content
	 */
	@Override
	public void createPartyWinnerChart(final AbstractOrderedLayout content) {

		final Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> map = getPartyMap();

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		for (final Entry<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> entry : map.entrySet()) {
			series.addSeries(new XYseries().setLabel(getPartyName(entry.getKey())));
			addPartyData(dataSeries, entry.getValue(), ViewRiksdagenVoteDataBallotPartySummaryDaily::getPartyWonPercentage);
		}

		series.addSeries(new XYseries().setLabel(NUMBER_BALLOTS));
		addPartyData(dataSeries, getMaxSizeViewRiksdagenVoteDataBallotPartySummaryDaily(), ViewRiksdagenVoteDataBallotPartySummaryDaily::getNumberBallots);

		ChartUtils.addChart(content,"Party winner by daily ballot average", new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsXYDateFloatLegendInsideOneColumn(series)).show(), true);
	}


	/**
	 * Gets the max size view riksdagen vote data ballot party summary daily.
	 *
	 * @return the max size view riksdagen vote data ballot party summary daily
	 */
	private List<ViewRiksdagenVoteDataBallotPartySummaryDaily> getMaxSizeViewRiksdagenVoteDataBallotPartySummaryDaily() {
		initPartyMap();

		final Optional<Entry<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>>> first = partyMap.entrySet()
				.stream().sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size())

		).findFirst();

		if (first.isPresent()) {
			return first.get().getValue();
		} else {
			return new ArrayList<>();
		}
	}


	/**
	 * Gets the party map.
	 *
	 * @return the party map
	 */
	private Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> getPartyMap() {
		initPartyMap();

		return partyMap;
	}



	/**
	 * Gets the view riksdagen vote data ballot party summary daily.
	 *
	 * @param party
	 *            the party
	 * @return the view riksdagen vote data ballot party summary daily
	 */
	private List<ViewRiksdagenVoteDataBallotPartySummaryDaily> getViewRiksdagenVoteDataBallotPartySummaryDaily(
			final String party) {
		initPartyMap();

		return partyMap.get(party);
	}


	/**
	 * Inits the party map.
	 */
	private synchronized void initPartyMap() {
		if (partyMap == null) {
			final DataContainer<ViewRiksdagenVoteDataBallotPartySummaryDaily, RiksdagenVoteDataBallotPartyPeriodSummaryEmbeddedId> partyBallotSummaryDailyDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenVoteDataBallotPartySummaryDaily.class);

			partyMap = partyBallotSummaryDailyDataContainer.getAll().parallelStream().filter(Objects::nonNull)
					.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getParty()));
		}
	}

	/**
	 * The Interface DataValueCalculator.
	 */
	@FunctionalInterface
	interface DataValueCalculator {

		/**
		 * Gets the data value.
		 *
		 * @param item
		 *            the item
		 * @return the data value
		 */
		Object getDataValue(ViewRiksdagenVoteDataBallotPartySummaryDaily item);
	}


}
