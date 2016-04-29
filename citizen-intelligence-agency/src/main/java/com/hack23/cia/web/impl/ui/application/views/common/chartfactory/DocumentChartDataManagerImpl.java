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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
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
import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentPartySummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentPersonSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentTypeSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenDocumentTypeDailySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenOrgDocumentDailySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPartyDocumentDailySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocumentDailySummary;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
public final class DocumentChartDataManagerImpl implements DocumentChartDataManager {

	/** The Constant NO_INFO. */
	private static final String NO_INFO = "NoInfo";

	/** The Constant MOT_PROP_BET. */
	private static final String MOT_PROP_BET = "mot:prop:bet";

	/** The Constant LOG_MSG_MISSING_DATA_FOR_KEY. */
	private static final String LOG_MSG_MISSING_DATA_FOR_KEY = "missing data for key:{}";

	/** The Constant LOG_MSG_TRYING_TO_FIND_DOCUMENT_SUMMARY_FOR_ORG_IN_MAP. */
	private static final String LOG_MSG_TRYING_TO_FIND_DOCUMENT_SUMMARY_FOR_ORG_IN_MAP = "Trying to find document summary for org:{} in map:{}";

	/** The Constant YEAR_PREFIX. */
	private static final String YEAR_PREFIX = "19";

	/** The Constant MINUS_SIGN. */
	private static final String MINUS_SIGN = "-";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** The Constant UNDER_SCORE. */
	private static final String UNDER_SCORE = "_";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentChartDataManagerImpl.class);

	/** The Constant DD_MMM_YYYY. */
	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new document chart data manager impl.
	 */
	public DocumentChartDataManagerImpl() {
		super();
	}


	/**
	 * Gets the document type map.
	 *
	 * @return the document type map
	 */
	private Map<String, List<ViewRiksdagenDocumentTypeDailySummary>> getDocumentTypeMap() {
		final DataContainer<ViewRiksdagenDocumentTypeDailySummary, RiksdagenDocumentTypeSummaryEmbeddedId> documentTypeSummaryDailyDataContainer = applicationManager
				.getDataContainer(ViewRiksdagenDocumentTypeDailySummary.class);

		return documentTypeSummaryDailyDataContainer.getAll().parallelStream()
				.filter(t -> t != null && !t.getEmbeddedId().getPublicDate().startsWith(YEAR_PREFIX)
						&& MOT_PROP_BET.contains(t.getEmbeddedId().getDocumentType()))
				.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getDocumentType()));
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


	/**
	 * Gets the view riksdagen party document daily summary map.
	 *
	 * @return the view riksdagen party document daily summary map
	 */
	private Map<String, List<ViewRiksdagenPartyDocumentDailySummary>> getViewRiksdagenPartyDocumentDailySummaryMap() {
		final DataContainer<ViewRiksdagenPartyDocumentDailySummary, RiksdagenDocumentPartySummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPartyDocumentDailySummary.class);

		return politicianBallotSummaryDailyDataContainer.getAll().parallelStream().filter(t -> t != null)
				.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getPartyShortCode().toUpperCase(Locale.ENGLISH).replace(UNDER_SCORE, EMPTY_STRING).trim()));
	}


	/**
	 * Gets the view riksdagen politician document daily summary map.
	 *
	 * @return the view riksdagen politician document daily summary map
	 */
	private Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> getViewRiksdagenPoliticianDocumentDailySummaryMap() {
		final DataContainer<ViewRiksdagenPoliticianDocumentDailySummary, RiksdagenDocumentPersonSummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPoliticianDocumentDailySummary.class);

		return politicianBallotSummaryDailyDataContainer.getAll().parallelStream().filter(t -> t != null)
				.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getPersonId()));
	}



	@Override
	public DCharts createDocumentHistoryChartByOrg(final String org) {
		final String searchOrg = org.toUpperCase(Locale.ENGLISH).replace(UNDER_SCORE, EMPTY_STRING).replace(MINUS_SIGN, EMPTY_STRING).trim();

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> allMap = getViewRiksdagenOrgDocumentDailySummaryMap();

		LOGGER.info(LOG_MSG_TRYING_TO_FIND_DOCUMENT_SUMMARY_FOR_ORG_IN_MAP,searchOrg,allMap.keySet().toString());


		final List<ViewRiksdagenOrgDocumentDailySummary> itemList = allMap
				.get(searchOrg);

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> map = itemList.parallelStream()
					.filter(t -> t != null)
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

		return new DCharts().setDataSeries(dataSeries).setOptions(ChartOptionsImpl.INSTANCE.createOptionsXYDateFloatLegendOutside(series)).show();
	}

	@Override
	public DCharts createDocumentHistoryPartyChart(final String org) {
		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		final Map<String, List<ViewRiksdagenPartyDocumentDailySummary>> allMap = getViewRiksdagenPartyDocumentDailySummaryMap();

		final List<ViewRiksdagenPartyDocumentDailySummary> itemList = allMap
				.get(org.toUpperCase(Locale.ENGLISH).replace(UNDER_SCORE, EMPTY_STRING).trim());

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenPartyDocumentDailySummary>> map = itemList.parallelStream()
					.filter(t -> t != null).collect(Collectors.groupingBy(
							t -> StringUtils.defaultIfBlank(t.getEmbeddedId().getDocumentType(), NO_INFO)));

			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);

			for (final Entry<String, List<ViewRiksdagenPartyDocumentDailySummary>> entry : map.entrySet()) {

				series.addSeries(new XYseries().setLabel(entry.getKey()));

				dataSeries.newSeries();
				if (entry.getValue() != null) {
					for (final ViewRiksdagenPartyDocumentDailySummary item : entry.getValue()) {
						if (item != null) {
							dataSeries.add(simpleDateFormat.format(item.getEmbeddedId().getPublicDate()), item.getTotal());
						}
					}
				} else {
					LOGGER.info(LOG_MSG_MISSING_DATA_FOR_KEY, entry);
				}

			}
		}

		return new DCharts().setDataSeries(dataSeries).setOptions(ChartOptionsImpl.INSTANCE.createOptionsXYDateFloatLegendOutside(series)).show();
	}



	@Override
	public DCharts createPersonDocumentHistoryChart(final String personId) {

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		final Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> allMap = getViewRiksdagenPoliticianDocumentDailySummaryMap();

		final List<ViewRiksdagenPoliticianDocumentDailySummary> itemList = allMap
				.get(personId.toUpperCase(Locale.ENGLISH).replace(UNDER_SCORE, EMPTY_STRING).trim());

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> map = itemList.parallelStream()
					.filter(t -> t != null).collect(Collectors.groupingBy(
							t -> StringUtils.defaultIfBlank(t.getEmbeddedId().getDocumentType(), NO_INFO)));

			for (final Entry<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> entry : map.entrySet()) {

				series.addSeries(new XYseries().setLabel(entry.getKey()));

				dataSeries.newSeries();
				if (entry.getValue() != null) {
					for (final ViewRiksdagenPoliticianDocumentDailySummary item : entry.getValue()) {
						if (item != null) {
							dataSeries.add(simpleDateFormat.format(item.getEmbeddedId().getPublicDate()),
									item.getTotal());
						}
					}
				} else {
					LOGGER.info(LOG_MSG_MISSING_DATA_FOR_KEY, entry);
				}

			}
		}

		return new DCharts().setDataSeries(dataSeries).setOptions(ChartOptionsImpl.INSTANCE.createOptionsXYDateFloatLegendOutside(series)).show();
	}





	@Override
	public DCharts createDocumentTypeChart() {

		final Map<String, List<ViewRiksdagenDocumentTypeDailySummary>> map = getDocumentTypeMap();

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		for (final Entry<String, List<ViewRiksdagenDocumentTypeDailySummary>> entry : map.entrySet()) {

			if (entry.getKey() != null && !EMPTY_STRING.equals(entry.getKey())) {

				series.addSeries(new XYseries().setLabel(entry.getKey()));

				dataSeries.newSeries();
				final List<ViewRiksdagenDocumentTypeDailySummary> list = entry.getValue();
				for (final ViewRiksdagenDocumentTypeDailySummary viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
					if (viewRiksdagenVoteDataBallotPartySummaryDaily != null) {
						dataSeries.add(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getPublicDate(),
								viewRiksdagenVoteDataBallotPartySummaryDaily.getTotal());
					}
				}
			}

		}

		return new DCharts().setDataSeries(dataSeries).setOptions(ChartOptionsImpl.INSTANCE.createOptionsXYDateFloatLegendOutside(series)).show();
	}


}
