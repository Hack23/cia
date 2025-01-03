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

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.impl.ApplicationActionEventPageElementPeriodSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.impl.ApplicationActionEventPageElementPeriodSummaryEmbeddedId_;
import com.hack23.cia.model.internal.application.data.impl.ApplicationActionEventPageModePeriodSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.impl.ApplicationActionEventPageModePeriodSummaryEmbeddedId_;
import com.hack23.cia.model.internal.application.data.impl.ApplicationActionEventPagePeriodSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.impl.ViewApplicationActionEventPageDailySummary;
import com.hack23.cia.model.internal.application.data.impl.ViewApplicationActionEventPageElementDailySummary;
import com.hack23.cia.model.internal.application.data.impl.ViewApplicationActionEventPageElementDailySummary_;
import com.hack23.cia.model.internal.application.data.impl.ViewApplicationActionEventPageModeDailySummary;
import com.hack23.cia.model.internal.application.data.impl.ViewApplicationActionEventPageModeDailySummary_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.AdminChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
public final class AdminChartDataManagerImpl extends AbstractChartDataManagerImpl implements AdminChartDataManager {

	/** The Constant PAGE_HITS. */
	private static final String PAGE_HITS = "Page Hits";

	/** The Constant PAGE_RANK. */
	private static final String PAGE_RANK = "Page Rank";

	/**
	 * Instantiates a new admin chart data manager impl.
	 */
	public AdminChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the view application action event page element daily summary values.
	 *
	 * @param label the label
	 * @param series the series
	 * @param dailySummaries the dailySummaries
	 * @param dataSeries the data series
	 * @param t the t
	 */
	private static void addViewApplicationActionEventPageElementDailySummaryValues(final String label,
			final Series series, final List<ViewApplicationActionEventPageElementDailySummary> dailySummaries,
			final DataSeries dataSeries,
			final ToLongFunction<ViewApplicationActionEventPageElementDailySummary> t) {
		series.addSeries(new XYseries().setLabel(label));
		dataSeries.newSeries();

		for (final ViewApplicationActionEventPageElementDailySummary summary : dailySummaries) {
			dataSeries.add(DateUtils.formatDate(summary.getEmbeddedId().getCreatedDate()), t.applyAsLong(summary));
		}
	}

	/**
	 * Creates the application action event page daily summary chart.
	 *
	 * @param layout the layout
	 */
	@Override
	public void createApplicationActionEventPageDailySummaryChart(final AbstractOrderedLayout layout) {
		final Map<String, List<ViewApplicationActionEventPageDailySummary>> map = getApplicationActionEventPageDailySummaryMap();
		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();
		addDataToSeries(map, dataSeries, series);
		addChart(layout, "Application Action Events daily Summary",
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);
	}

	/**
	 * Adds data to series.
	 *
	 * @param map the map
	 * @param dataSeries the data series
	 * @param series the series
	 */
	private void addDataToSeries(final Map<String, List<ViewApplicationActionEventPageDailySummary>> map,
			final DataSeries dataSeries, final Series series) {
		for (final Entry<String, List<ViewApplicationActionEventPageDailySummary>> entry : map.entrySet()) {
			if (entry.getKey() != null) {
				series.addSeries(new XYseries().setLabel(entry.getKey()));
				dataSeries.newSeries();
				final List<ViewApplicationActionEventPageDailySummary> list = entry.getValue();
				for (final ViewApplicationActionEventPageDailySummary dataSummaryDaily : list) {
					dataSeries.add(DateUtils.formatDate(dataSummaryDaily.getEmbeddedId().getCreatedDate()),
							dataSummaryDaily.getHits());
				}
			}
		}
	}

	/**
	 * Creates the application action event page element daily summary chart.
	 *
	 * @param layout the layout
	 * @param page the page
	 * @param elementId the element id
	 */
	@Override
	public void createApplicationActionEventPageElementDailySummaryChart(final AbstractOrderedLayout layout,
			final String page, final String elementId) {
		final List<ViewApplicationActionEventPageElementDailySummary> dailySummaries = getApplicationActionEventPageElementDailySummaryList(
				page, elementId);

		final Series series = new Series();
		final DataSeries dataSeries = new DataSeries();

		addViewApplicationActionEventPageElementDailySummaryValues(PAGE_HITS, series, dailySummaries, dataSeries,
				ViewApplicationActionEventPageElementDailySummary::getHits);
		addViewApplicationActionEventPageElementDailySummaryValues(PAGE_RANK, series, dailySummaries, dataSeries,
				ViewApplicationActionEventPageElementDailySummary::getRank);

		addChart(layout, "Page element Action Events daily Summary",
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);
	}

	/**
	 * Creates the application action event page mode daily summary chart.
	 *
	 * @param layout the layout
	 * @param page the page
	 */
	@Override
	public void createApplicationActionEventPageModeDailySummaryChart(final AbstractOrderedLayout layout,
			final String page) {
		final Map<String, List<ViewApplicationActionEventPageModeDailySummary>> map = getApplicationActionEventPageModeDailySummaryMap(
				page);
		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();
		addDataToSeries(map, dataSeries, series);
		addChart(layout, "Page Action Events daily Summary",
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);
	}

	/**
	 * Gets the application action event page daily summary map.
	 *
	 * @return the application action event page daily summary map
	 */
	private Map<String, List<ViewApplicationActionEventPageDailySummary>> getApplicationActionEventPageDailySummaryMap() {
		final DataContainer<ViewApplicationActionEventPageDailySummary, ApplicationActionEventPagePeriodSummaryEmbeddedId> documentTypeSummaryDailyDataContainer = getApplicationManager()
				.getDataContainer(ViewApplicationActionEventPageDailySummary.class);

		return documentTypeSummaryDailyDataContainer.getAll().parallelStream().filter(Objects::nonNull)
				.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getPage()));
	}

	/**
	 * Gets the application action event page element daily summary list.
	 *
	 * @param page the page
	 * @param elementId the element id
	 * @return the application action event page element daily summary list
	 */
	private List<ViewApplicationActionEventPageElementDailySummary> getApplicationActionEventPageElementDailySummaryList(
			final String page, final String elementId) {
		final DataContainer<ViewApplicationActionEventPageElementDailySummary, ApplicationActionEventPageElementPeriodSummaryEmbeddedId> documentTypeSummaryDailyDataContainer = getApplicationManager()
				.getDataContainer(ViewApplicationActionEventPageElementDailySummary.class);

		final List<ViewApplicationActionEventPageElementDailySummary> findOrderedListByEmbeddedProperty = documentTypeSummaryDailyDataContainer
				.findOrderedListByEmbeddedProperty(ViewApplicationActionEventPageElementDailySummary.class,
						ViewApplicationActionEventPageElementDailySummary_.embeddedId,
						ApplicationActionEventPageElementPeriodSummaryEmbeddedId.class,
						ApplicationActionEventPageElementPeriodSummaryEmbeddedId_.elementId, elementId,
						ApplicationActionEventPageElementPeriodSummaryEmbeddedId_.createdDate);

		return findOrderedListByEmbeddedProperty.parallelStream()
				.filter(t -> t != null && t.getEmbeddedId().getPage().equals(page)).toList();
	}

	/**
	 * Gets the application action event page mode daily summary map.
	 *
	 * @param page the page
	 * @return the application action event page mode daily summary map
	 */
	private Map<String, List<ViewApplicationActionEventPageModeDailySummary>> getApplicationActionEventPageModeDailySummaryMap(
			final String page) {
		final DataContainer<ViewApplicationActionEventPageModeDailySummary, ApplicationActionEventPageModePeriodSummaryEmbeddedId> documentTypeSummaryDailyDataContainer = getApplicationManager()
				.getDataContainer(ViewApplicationActionEventPageModeDailySummary.class);

		final List<ViewApplicationActionEventPageModeDailySummary> findOrderedListByEmbeddedProperty = documentTypeSummaryDailyDataContainer
				.findOrderedListByEmbeddedProperty(ViewApplicationActionEventPageModeDailySummary.class,
						ViewApplicationActionEventPageModeDailySummary_.embeddedId,
						ApplicationActionEventPageModePeriodSummaryEmbeddedId.class,
						ApplicationActionEventPageModePeriodSummaryEmbeddedId_.page, page,
						ApplicationActionEventPageModePeriodSummaryEmbeddedId_.createdDate);

		return findOrderedListByEmbeddedProperty.parallelStream().filter(Objects::nonNull)
				.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getPageMode()));
	}

	/**
	 * Creates the application session page daily summary chart.
	 *
	 * @param layout the layout
	 */
	@Override
	public void createApplicationSessionPageDailySummaryChart(final VerticalLayout layout) {
		final DataContainer<ApplicationSession, Serializable> dataContainer = getApplicationManager()
				.getDataContainer(ApplicationSession.class);
		final Map<String, Long> sessionByDayMap = dataContainer.getAll().stream()
				.collect(Collectors.groupingBy(p -> DateUtils.formatDate(p.getCreatedDate()), Collectors.counting()));
		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();
		series.addSeries(new XYseries().setLabel("Daily Active Users"));
		dataSeries.newSeries();
		for (final Entry<String, Long> entry : sessionByDayMap.entrySet()) {
			dataSeries.add(entry.getKey(), entry.getValue());
		}
		addChart(layout, "Application Active Daily Users",
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);
	}

}
