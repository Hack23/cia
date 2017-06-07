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
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentOrgSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenOrgDocumentDailySummary;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartOptions;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.OrgDocumentChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class OrgDocumentChartDataManagerImpl.
 */
@Service
public final class OrgDocumentChartDataManagerImpl extends AbstractChartDataManagerImpl implements OrgDocumentChartDataManager {

	private static final String DOCUMENT_HISTORY_BY_ORG = "Document History by Org";

	/** The Constant NO_INFO. */
	private static final String NO_INFO = "NoInfo";

	/** The Constant LOG_MSG_MISSING_DATA_FOR_KEY. */
	private static final String LOG_MSG_MISSING_DATA_FOR_KEY = "missing data for key:{}";

	/** The Constant YEAR_PREFIX. */
	private static final String YEAR_PREFIX = "19";

	/** The Constant MINUS_SIGN. */
	private static final String MINUS_SIGN = "-";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** The Constant UNDER_SCORE. */
	private static final String UNDER_SCORE = "_";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrgDocumentChartDataManagerImpl.class);

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/** The chart options. */
	@Autowired
	private ChartOptions chartOptions;


	/**
	 * Instantiates a new org document chart data manager impl.
	 */
	public OrgDocumentChartDataManagerImpl() {
		super();
	}


	/**
	 * Gets the view riksdagen org document daily summary map.
	 *
	 * @return the view riksdagen org document daily summary map
	 */
	private Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> getViewRiksdagenOrgDocumentDailySummaryMap() {
		final DataContainer<ViewRiksdagenOrgDocumentDailySummary, RiksdagenDocumentOrgSummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
				.getDataContainer(ViewRiksdagenOrgDocumentDailySummary.class);

		return politicianBallotSummaryDailyDataContainer.getAll().parallelStream()
				.filter(t -> t != null && !t.getEmbeddedId().getPublicDate().startsWith(YEAR_PREFIX))
				.collect(Collectors.groupingBy(t -> StringEscapeUtils.unescapeHtml4(t.getEmbeddedId().getOrg()).toUpperCase(Locale.ENGLISH).replace(UNDER_SCORE, EMPTY_STRING).replace(MINUS_SIGN, EMPTY_STRING).trim()));
	}


	@Override
	public void createDocumentHistoryChartByOrg(final AbstractOrderedLayout content,final String org) {
		final String searchOrg = org.toUpperCase(Locale.ENGLISH).replace(UNDER_SCORE, EMPTY_STRING).replace(MINUS_SIGN, EMPTY_STRING).trim();

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> allMap = getViewRiksdagenOrgDocumentDailySummaryMap();

		final List<ViewRiksdagenOrgDocumentDailySummary> itemList = allMap
				.get(searchOrg);

		if (itemList != null) {

			addDocumentHistoryByOrgData(dataSeries, series, itemList);
		}

		addChart(content,DOCUMENT_HISTORY_BY_ORG, new DCharts().setDataSeries(dataSeries).setOptions(chartOptions.createOptionsXYDateFloatLegendOutside(series)).show(), true);
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

		for (final Entry<String, List<ViewRiksdagenOrgDocumentDailySummary>> entry : map.entrySet()) {

			series.addSeries(new XYseries().setLabel(entry.getKey()));

			dataSeries.newSeries();
			if (entry.getValue() != null) {
				for (final ViewRiksdagenOrgDocumentDailySummary item : entry.getValue()) {
					if (item != null) {
						dataSeries.add(item.getEmbeddedId().getPublicDate(), item.getTotal());
					}
				}
			} else {
				LOGGER.info(LOG_MSG_MISSING_DATA_FOR_KEY, entry);
			}

		}
	}

}
