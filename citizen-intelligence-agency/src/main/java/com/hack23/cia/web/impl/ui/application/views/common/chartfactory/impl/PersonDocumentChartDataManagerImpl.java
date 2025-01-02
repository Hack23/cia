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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentPersonSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocumentDailySummary;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PersonDocumentChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class PersonDocumentChartDataManagerImpl.
 */
@Service
public final class PersonDocumentChartDataManagerImpl extends AbstractChartDataManagerImpl
		implements PersonDocumentChartDataManager {

	/** The Constant DD_MMM_YYYY. */
	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";

	private static final String DOCUMENT_HISTORY = "Document history";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** The Constant LOG_MSG_MISSING_DATA_FOR_KEY. */
	private static final String LOG_MSG_MISSING_DATA_FOR_KEY = "missing data for key:{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonDocumentChartDataManagerImpl.class);

	/** The Constant NO_INFO. */
	private static final String NO_INFO = "NoInfo";

	/** The Constant UNDER_SCORE. */
	private static final String UNDER_SCORE = "_";

	/**
	 * Instantiates a new person document chart data manager impl.
	 */
	public PersonDocumentChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the document history by person data.
	 *
	 * @param simpleDateFormat the simple date format
	 * @param dataSeries the data series
	 * @param series the series
	 * @param map the map
	 */
	private static void addDocumentHistoryByPersonData(final SimpleDateFormat simpleDateFormat,
			final DataSeries dataSeries, final Series series,
			final Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> map) {
		for (final Entry<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> entry : map.entrySet()) {

			series.addSeries(new XYseries().setLabel(entry.getKey()));

			dataSeries.newSeries();
			if (entry.getValue() != null) {
				for (final ViewRiksdagenPoliticianDocumentDailySummary item : entry.getValue()) {
					dataSeries.add(simpleDateFormat.format(item.getEmbeddedId().getPublicDate()), item.getTotal());
				}
			} else {
				LOGGER.info(LOG_MSG_MISSING_DATA_FOR_KEY, entry);
			}

		}
	}

	@Override
	public void createPersonDocumentHistoryChart(final AbstractOrderedLayout content, final String personId) {

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		final Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> allMap = getViewRiksdagenPoliticianDocumentDailySummaryMap();

		final List<ViewRiksdagenPoliticianDocumentDailySummary> itemList = allMap
				.get(personId.toUpperCase(Locale.ENGLISH).replace(UNDER_SCORE, EMPTY_STRING).trim());

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> map = itemList.parallelStream()
					.filter(Objects::nonNull).collect(Collectors
							.groupingBy(t -> StringUtils.defaultIfBlank(t.getEmbeddedId().getDocumentType(), NO_INFO)));

			addDocumentHistoryByPersonData(simpleDateFormat, dataSeries, series, map);
		}

		addChart(content, DOCUMENT_HISTORY,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLegendInsideOneColumn(series)).show(),
				true);
	}

	/**
	 * Gets the view riksdagen politician document daily summary map.
	 *
	 * @return the view riksdagen politician document daily summary map
	 */
	private Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> getViewRiksdagenPoliticianDocumentDailySummaryMap() {
		final DataContainer<ViewRiksdagenPoliticianDocumentDailySummary, RiksdagenDocumentPersonSummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPoliticianDocumentDailySummary.class);

		return politicianBallotSummaryDailyDataContainer.getAll().parallelStream().filter(Objects::nonNull)
				.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getPersonId()));
	}

}
