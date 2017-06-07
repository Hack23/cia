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
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentTypeSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenDocumentTypeDailySummary;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartOptions;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.DocumentChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class DocumentChartDataManagerImpl.
 */
@Service
public final class DocumentChartDataManagerImpl extends AbstractChartDataManagerImpl implements DocumentChartDataManager {

	/** The Constant MOT_PROP_BET. */
	private static final String MOT_PROP_BET = "mot:prop:bet";

	/** The Constant YEAR_PREFIX. */
	private static final String YEAR_PREFIX = "19";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/** The chart options. */
	@Autowired
	private ChartOptions chartOptions;


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



	@Override
	public void createDocumentTypeChart(final AbstractOrderedLayout content) {

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

		addChart(content,"Document type", new DCharts().setDataSeries(dataSeries).setOptions(chartOptions.createOptionsXYDateFloatLegendInsideOneColumn(series)).show(), true);
	}

}
