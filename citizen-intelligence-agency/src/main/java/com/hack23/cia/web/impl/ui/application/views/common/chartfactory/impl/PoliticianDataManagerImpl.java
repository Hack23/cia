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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryDaily;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GenericChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PoliticianChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
public final class PoliticianDataManagerImpl extends AbstractChartDataManagerImpl implements PoliticianChartDataManager {

	/** The Constant ABSENT. */
	private static final String ABSENT = "Absent";

	/** The Constant DD_MMM_YYYY. */
	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";

	/** The Constant NUMBER_BALLOTS. */
	private static final String NUMBER_BALLOTS = "Number ballots";

	/** The Constant PARTY_REBEL. */
	private static final String PARTY_REBEL = "Party Rebel";

	/** The Constant WON. */
	private static final String WON = "Won";

	/** The data chart manager. */
	@Autowired
	private GenericChartDataManager<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily> dataChartManager;

	/**
	 * Instantiates a new politician data manager impl.
	 */
	public PoliticianDataManagerImpl() {
		super();
	}



	/**
	 * Adds the politican data.
	 *
	 * @param list             the list
	 * @param dataSeries       the data series
	 * @param simpleDateFormat the simple date format
	 * @param t                the t
	 */
	private static void addPoliticanData(final List<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily> list,
			final DataSeries dataSeries, final SimpleDateFormat simpleDateFormat, final Function<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily, Object> t) {
		dataSeries.newSeries();
		for (final ViewRiksdagenVoteDataBallotPoliticianSummaryDaily viewRiksdagenVoteDataBallotPoliticianSummaryDaily : list) {
			if (viewRiksdagenVoteDataBallotPoliticianSummaryDaily != null) {
				dataSeries.add(
						simpleDateFormat.format(
								viewRiksdagenVoteDataBallotPoliticianSummaryDaily.getEmbeddedId().getVoteDate()),
						t.apply(viewRiksdagenVoteDataBallotPoliticianSummaryDaily));
			}
		}
	}



	/**
	 * Adds the politician indicator data.
	 *
	 * @param list
	 *            the list
	 * @param dataSeries
	 *            the data series
	 * @param simpleDateFormat
	 *            the simple date format
	 */
	private static void addPoliticianIndicatorData(final List<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily> list,
			final DataSeries dataSeries, final SimpleDateFormat simpleDateFormat) {
		addPoliticanData(list, dataSeries, simpleDateFormat, ViewRiksdagenVoteDataBallotPoliticianSummaryDaily::getWonPercentage);
		addPoliticanData(list, dataSeries, simpleDateFormat, ViewRiksdagenVoteDataBallotPoliticianSummaryDaily::getRebelPercentage);
		addPoliticanData(list, dataSeries, simpleDateFormat, ViewRiksdagenVoteDataBallotPoliticianSummaryDaily::getPoliticianPercentageAbsent);
		addPoliticanData(list, dataSeries, simpleDateFormat, ViewRiksdagenVoteDataBallotPoliticianSummaryDaily::getNumberBallots);
	}




	@Override
	public void createPersonLineChart(final AbstractOrderedLayout content,final String personId) {

		final List<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily> list = dataChartManager.findByValue(personId);

		final Series series = new Series().addSeries(new XYseries().setLabel(WON))
				.addSeries(new XYseries().setLabel(PARTY_REBEL)).addSeries(new XYseries().setLabel(ABSENT))
				.addSeries(new XYseries().setLabel(NUMBER_BALLOTS));

		final DataSeries dataSeries = new DataSeries();

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);

		if (list != null) {
			addPoliticianIndicatorData(list, dataSeries, simpleDateFormat);
		}

		addChart(content,"Ballot indicators", new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsPersonLineChart(series)).show(), true);
	}


}
