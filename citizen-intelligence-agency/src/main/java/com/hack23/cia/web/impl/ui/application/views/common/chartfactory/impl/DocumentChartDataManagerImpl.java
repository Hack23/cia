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
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentTypeSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenDocumentTypeDailySummary;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.DocumentChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class DocumentChartDataManagerImpl.
 */
@Service
public final class DocumentChartDataManagerImpl extends AbstractChartDataManagerImpl
		implements DocumentChartDataManager {

	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentChartDataManagerImpl.class);

	/** The Constant MOT_PROP_BET. */
	private static final String MOT_PROP_BET = "mot:prop:bet";

	/** The Constant YEAR_PREFIX. */
	private static final String YEAR_PREFIX = "19";

	/**
	 * Instantiates a new document chart data manager impl.
	 */
	public DocumentChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the data series.
	 *
	 * @param simpleDateFormat the simple date format
	 * @param parseInputDateFormat the parse input date format
	 * @param dataSeries the data series
	 * @param series the series
	 * @param entry the entry
	 */
	private static void addDataSeries(final SimpleDateFormat simpleDateFormat,
			final SimpleDateFormat parseInputDateFormat, final DataSeries dataSeries, final Series series,
			final Entry<String, List<ViewRiksdagenDocumentTypeDailySummary>> entry) {

		series.addSeries(new XYseries().setLabel(entry.getKey()));
		dataSeries.newSeries();

		for (final ViewRiksdagenDocumentTypeDailySummary item : entry.getValue()) {
			if (item != null && item.getEmbeddedId().getPublicDate().length() > 0) {
				try {
					dataSeries.add(simpleDateFormat.format(parseInputDateFormat.parse(item.getEmbeddedId().getPublicDate())), item.getTotal());
				} catch (final ParseException e) {
					LOGGER.warn("Problem parsing date:{}", item.getEmbeddedId().getPublicDate());
				}

			}
		}
	}

	@Override
	public void createDocumentTypeChart(final AbstractOrderedLayout content) {
		final Map<String, List<ViewRiksdagenDocumentTypeDailySummary>> map = getDocumentTypeMap();
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);
		final SimpleDateFormat parseInputDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);

		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		for (final Entry<String, List<ViewRiksdagenDocumentTypeDailySummary>> entry : map.entrySet()) {
			if (entry.getKey() != null && !EMPTY_STRING.equals(entry.getKey())) {
				addDataSeries(simpleDateFormat, parseInputDateFormat, dataSeries, series, entry);
			}
		}

		addChart(content, "Document type",
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLegendInsideOneColumn(series)).show(),
				true);
	}

	/**
	 * Gets the document type map.
	 *
	 * @return the document type map
	 */
	private Map<String, List<ViewRiksdagenDocumentTypeDailySummary>> getDocumentTypeMap() {
		final DataContainer<ViewRiksdagenDocumentTypeDailySummary, RiksdagenDocumentTypeSummaryEmbeddedId> documentTypeSummaryDailyDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenDocumentTypeDailySummary.class);

		return documentTypeSummaryDailyDataContainer.getAll().parallelStream()
				.filter(t -> t != null && !t.getEmbeddedId().getPublicDate().startsWith(YEAR_PREFIX)
						&& StringUtils.containsIgnoreCase(MOT_PROP_BET, t.getEmbeddedId().getDocumentType()))
				.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getDocumentType()));
	}

}
