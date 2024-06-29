/*
 * Copyright 2010-2024 James Pether Sörling
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentOrgSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenOrgDocumentDailySummary;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.OrgDocumentChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class OrgDocumentChartDataManagerImpl.
 */
@Service
public final class OrgDocumentChartDataManagerImpl extends AbstractChartDataManagerImpl
		implements OrgDocumentChartDataManager {

	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";

	private static final String DOCUMENT_HISTORY_BY_ORG = "Document History by Org";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrgDocumentChartDataManagerImpl.class);

	/** The Constant MINUS_SIGN. */
	private static final String MINUS_SIGN = "-";

	/** The Constant NO_INFO. */
	private static final String NO_INFO = "NoInfo";

	/** The Constant UNDER_SCORE. */
	private static final String UNDER_SCORE = "_";

	/** The Constant YEAR_PREFIX. */
	private static final String YEAR_PREFIX = "19";

	/**
	 * Instantiates a new org document chart data manager impl.
	 */
	public OrgDocumentChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the data item.
	 *
	 * @param dataSeries              the data series
	 * @param simpleDateFormat        the simple date format
	 * @param parseIncomingDateFormat the parse incoming date format
	 * @param item                    the item
	 */
	private static void addDataItem(final DataSeries dataSeries, final SimpleDateFormat simpleDateFormat,
			final SimpleDateFormat parseIncomingDateFormat, final ViewRiksdagenOrgDocumentDailySummary item) {
		if (item != null && item.getEmbeddedId().getPublicDate().length() > 0) {

			try {
				dataSeries.add(
						simpleDateFormat
								.format(parseIncomingDateFormat.parse(item.getEmbeddedId().getPublicDate())),
						item.getTotal());
			} catch (final ParseException e) {
				LOGGER.warn("Problem parsing date:{}", item.getEmbeddedId().getPublicDate());
			}
		}
	}

	/**
	 * Adds the document history by org data.
	 *
	 * @param dataSeries
	 *            the data series
	 * @param series
	 *            the series
	 * @param itemList
	 *            the item list
	 */
	private static void addDocumentHistoryByOrgData(final DataSeries dataSeries, final Series series,
			final List<ViewRiksdagenOrgDocumentDailySummary> itemList) {
		final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> map = itemList.parallelStream()
				.filter(Objects::nonNull)
				.collect(Collectors.groupingBy(t -> StringUtils.defaultIfBlank(t.getDocumentType(), NO_INFO)));
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);
		final SimpleDateFormat parseIncomingDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);

		for (final Entry<String, List<ViewRiksdagenOrgDocumentDailySummary>> entry : map.entrySet()) {
			addNewDataSerie(dataSeries, series, simpleDateFormat, parseIncomingDateFormat, entry);
		}
	}

	/**
	 * Adds the new data serie.
	 *
	 * @param dataSeries              the data series
	 * @param series                  the series
	 * @param simpleDateFormat        the simple date format
	 * @param parseIncomingDateFormat the parse incoming date format
	 * @param entry                   the entry
	 */
	private static void addNewDataSerie(final DataSeries dataSeries, final Series series,
			final SimpleDateFormat simpleDateFormat, final SimpleDateFormat parseIncomingDateFormat,
			final Entry<String, List<ViewRiksdagenOrgDocumentDailySummary>> entry) {

		series.addSeries(new XYseries().setLabel(entry.getKey()));
		dataSeries.newSeries();

		if (entry.getValue() != null) {
			for (final ViewRiksdagenOrgDocumentDailySummary item : entry.getValue()) {
				addDataItem(dataSeries, simpleDateFormat, parseIncomingDateFormat, item);
			}
		}
	}

	@Override
	public void createDocumentHistoryChartByOrg(final AbstractOrderedLayout content, final String org) {
		final String searchOrg = org.toUpperCase(Locale.ENGLISH).replace(UNDER_SCORE, EMPTY_STRING)
				.replace(MINUS_SIGN, EMPTY_STRING).trim();

		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> allMap = getViewRiksdagenOrgDocumentDailySummaryMap();

		final List<ViewRiksdagenOrgDocumentDailySummary> itemList = allMap.get(searchOrg);

		if (itemList != null) {
			addDocumentHistoryByOrgData(dataSeries, series, itemList);
		}

		addChart(content, DOCUMENT_HISTORY_BY_ORG,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLegendInsideOneColumn(series)).show(),
				true);
	}

	/**
	 * Gets the view riksdagen org document daily summary map.
	 *
	 * @return the view riksdagen org document daily summary map
	 */
	private Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> getViewRiksdagenOrgDocumentDailySummaryMap() {
		final DataContainer<ViewRiksdagenOrgDocumentDailySummary, RiksdagenDocumentOrgSummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenOrgDocumentDailySummary.class);

		return politicianBallotSummaryDailyDataContainer.getAll().parallelStream()
				.filter(t -> t != null && !t.getEmbeddedId().getPublicDate().startsWith(YEAR_PREFIX))
				.collect(Collectors.groupingBy(
						t -> StringEscapeUtils.unescapeHtml4(t.getEmbeddedId().getOrg()).toUpperCase(Locale.ENGLISH)
								.replace(UNDER_SCORE, EMPTY_STRING).replace(MINUS_SIGN, EMPTY_STRING).trim()));
	}

}
