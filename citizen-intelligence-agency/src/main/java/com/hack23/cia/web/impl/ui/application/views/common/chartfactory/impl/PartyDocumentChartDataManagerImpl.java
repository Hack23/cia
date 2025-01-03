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

import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentPartySummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPartyDocumentDailySummary;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PartyDocumentChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class PartyDocumentChartDataManagerImpl.
 */
@Service
public final class PartyDocumentChartDataManagerImpl extends AbstractChartDataManagerImpl
		implements PartyDocumentChartDataManager {

	/** The Constant DOCUMENT_HISTORY_PARTY. */
	private static final String DOCUMENT_HISTORY_PARTY = "Document history party";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** The Constant LOG_MSG_MISSING_DATA_FOR_KEY. */
	private static final String LOG_MSG_MISSING_DATA_FOR_KEY = "missing data for key:{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PartyDocumentChartDataManagerImpl.class);

	/** The Constant NO_INFO. */
	private static final String NO_INFO = "NoInfo";

	/** The Constant UNDER_SCORE. */
	private static final String UNDER_SCORE = "_";

	/**
	 * Instantiates a new party document chart data manager impl.
	 */
	public PartyDocumentChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the document history by party data.
	 *
	 * @param dataSeries the data series
	 * @param series the series
	 * @param map the map
	 */
	private static void addDocumentHistoryByPartyData(final DataSeries dataSeries, final Series series,
			final Map<String, List<ViewRiksdagenPartyDocumentDailySummary>> map) {

		for (final Entry<String, List<ViewRiksdagenPartyDocumentDailySummary>> documentTypeEntry : map.entrySet()) {

			series.addSeries(new XYseries().setLabel(documentTypeEntry.getKey()));

			dataSeries.newSeries();
			if (documentTypeEntry.getValue() != null) {
				for (final ViewRiksdagenPartyDocumentDailySummary item : documentTypeEntry.getValue()) {
					dataSeries.add(DateUtils.formatDate(item.getEmbeddedId().getPublicDate()), item.getTotal());
				}
			} else {
				LOGGER.info(LOG_MSG_MISSING_DATA_FOR_KEY, documentTypeEntry);
			}

		}
	}

	/**
	 * Creates the document history party chart.
	 *
	 * @param layout the layout
	 * @param partyShortCode the party short code
	 */
	@Override
	public void createDocumentHistoryPartyChart(final AbstractOrderedLayout layout, final String partyShortCode) {
		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		final Map<String, List<ViewRiksdagenPartyDocumentDailySummary>> allMap = getViewRiksdagenPartyDocumentDailySummaryMap();

		final List<ViewRiksdagenPartyDocumentDailySummary> itemList = allMap
				.get(partyShortCode.toUpperCase(Locale.ENGLISH).replace(UNDER_SCORE, EMPTY_STRING).trim());

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenPartyDocumentDailySummary>> documentTypeMap = itemList.parallelStream()
					.filter(Objects::nonNull).collect(Collectors
							.groupingBy(t -> StringUtils.defaultIfBlank(t.getEmbeddedId().getDocumentType(), NO_INFO)));

			addDocumentHistoryByPartyData(dataSeries, series, documentTypeMap);
		}

		addChart(layout, DOCUMENT_HISTORY_PARTY,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLegendInsideOneColumn(series)).show(),
				true);
	}

	/**
	 * Gets the view riksdagen party document daily summary map.
	 *
	 * @return the view riksdagen party document daily summary map
	 */
	private Map<String, List<ViewRiksdagenPartyDocumentDailySummary>> getViewRiksdagenPartyDocumentDailySummaryMap() {
		final DataContainer<ViewRiksdagenPartyDocumentDailySummary, RiksdagenDocumentPartySummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyDocumentDailySummary.class);

		return politicianBallotSummaryDailyDataContainer.getAll().parallelStream().filter(Objects::nonNull)
				.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getPartyShortCode().toUpperCase(Locale.ENGLISH)
						.replace(UNDER_SCORE, EMPTY_STRING).trim()));
	}

}
