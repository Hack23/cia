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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
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


	/** The Constant PARTY_ABSENT. */
	private static final String PARTY_ABSENT = "Party Absent";

	/** The Constant PARTY_WON. */
	private static final String PARTY_WON = "Party Won";

	/** The Constant NUMBER_BALLOTS. */
	private static final String NUMBER_BALLOTS = "Number ballots";

	/** The Constant DD_MMM_YYYY. */
	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";

	/** The party map. */
	private Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> partyMap;

	/**
	 * Instantiates a new party chart data manager impl.
	 */
	public PartyChartDataManagerImpl() {
		super();
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
	 * Inits the party map.
	 */
	private void initPartyMap() {
		if (partyMap == null) {
			final DataContainer<ViewRiksdagenVoteDataBallotPartySummaryDaily, RiksdagenVoteDataBallotPartyPeriodSummaryEmbeddedId> partyBallotSummaryDailyDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenVoteDataBallotPartySummaryDaily.class);

			partyMap = partyBallotSummaryDailyDataContainer.getAll().parallelStream().filter(Objects::nonNull)
					.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getParty()));
		}
	}

	@Override
	public void createPartyWinnerChart(final AbstractOrderedLayout content) {

		final Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> map = getPartyMap();

		final DataSeries dataSeries = new DataSeries();

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);

		final Series series = new Series();

		for (final Entry<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> entry : map.entrySet()) {

			series.addSeries(new XYseries().setLabel(getPartyName(entry.getKey())));

			dataSeries.newSeries();
			final List<ViewRiksdagenVoteDataBallotPartySummaryDaily> list = entry.getValue();
			for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
				if (viewRiksdagenVoteDataBallotPartySummaryDaily != null) {
					dataSeries.add(
							simpleDateFormat
									.format(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getVoteDate()),
							viewRiksdagenVoteDataBallotPartySummaryDaily.getPartyWonPercentage());
				}
			}

		}

		series.addSeries(new XYseries().setLabel(NUMBER_BALLOTS));

		dataSeries.newSeries();
		final List<ViewRiksdagenVoteDataBallotPartySummaryDaily> list = getMaxSizeViewRiksdagenVoteDataBallotPartySummaryDaily();
		for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
			if (viewRiksdagenVoteDataBallotPartySummaryDaily != null) {
				dataSeries.add(
						simpleDateFormat
								.format(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getVoteDate()),
						viewRiksdagenVoteDataBallotPartySummaryDaily.getNumberBallots());
			}
		}

		addChart(content,"Party winner by daily ballot average", new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsXYDateFloatLegendInsideOneColumn(series)).show(), true);
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


	@Override
	public void createPartyLineChart(final AbstractOrderedLayout content,final String partyId) {

		final List<ViewRiksdagenVoteDataBallotPartySummaryDaily> list = getViewRiksdagenVoteDataBallotPartySummaryDaily(
				partyId);

		final Series series = new Series().addSeries(new XYseries().setLabel(PARTY_WON))
				.addSeries(new XYseries().setLabel(PARTY_ABSENT));

		final DataSeries dataSeries = new DataSeries().newSeries();

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);

		if (list != null) {

			for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
				if (viewRiksdagenVoteDataBallotPartySummaryDaily != null) {
					dataSeries.add(
							simpleDateFormat
									.format(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getVoteDate()),
							viewRiksdagenVoteDataBallotPartySummaryDaily.getPartyWonPercentage());
				}
			}

			dataSeries.newSeries();

			for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
				if (viewRiksdagenVoteDataBallotPartySummaryDaily != null) {
					dataSeries.add(
							simpleDateFormat
									.format(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getVoteDate()),
							viewRiksdagenVoteDataBallotPartySummaryDaily.getPartyPercentageAbsent());
				}
			}
		}

		addChart(content,"Party result by", new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsPartyLineChart(series)).show(), true);
	}


	@Override
	public void createPartyGenderChart(final AbstractOrderedLayout content) {

		createPartyBallotChart(content,viewRiksdagenVoteDataBallotPartySummaryDaily -> 100 - viewRiksdagenVoteDataBallotPartySummaryDaily.getPartyAvgPercentageMale().intValue());

	}


	@Override
	public void createPartyAgeChart(final AbstractOrderedLayout content) {
		createPartyBallotChart(content,viewRiksdagenVoteDataBallotPartySummaryDaily -> DateUtils.toCalendar(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getVoteDate()).get(Calendar.YEAR) - viewRiksdagenVoteDataBallotPartySummaryDaily.getPartyAvgBornYear().intValue());
	}



	/**
	 * Creates the party ballot chart.
	 *
	 * @param dataValueCalculator
	 *            the data value calculator
	 * @return the d charts
	 */
	private void createPartyBallotChart(final AbstractOrderedLayout content,final DataValueCalculator dataValueCalculator) {
		final Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> map = getPartyMap();

		final DataSeries dataSeries = new DataSeries();

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);

		final Series series = new Series();

		for (final Entry<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> entry : map.entrySet()) {

			if (!"-".equals(entry.getKey())) {
				series.addSeries(new XYseries().setLabel(getPartyName(entry.getKey())));

				dataSeries.newSeries();
				final List<ViewRiksdagenVoteDataBallotPartySummaryDaily> list = entry.getValue();
				for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
					if (viewRiksdagenVoteDataBallotPartySummaryDaily != null) {
						dataSeries.add(
								simpleDateFormat
										.format(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getVoteDate()),
										dataValueCalculator.getDataValue(viewRiksdagenVoteDataBallotPartySummaryDaily));
					}
				}
			}
		}

		addChart(content,"Party ballot chart", new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsXYDateFloatLegendInsideOneColumn(series)).show(), true);
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
