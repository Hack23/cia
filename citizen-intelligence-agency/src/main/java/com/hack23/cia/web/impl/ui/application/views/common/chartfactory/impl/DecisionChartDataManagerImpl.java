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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenCommitteeDecisionTypeOrgSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenCommitteeDecisionTypeSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisionTypeDailySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisionTypeOrgDailySummary;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.DecisionChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class DecisionChartDataManagerImpl.
 */
@Service
public final class DecisionChartDataManagerImpl extends AbstractChartDataManagerImpl
		implements DecisionChartDataManager {

	/** The Constant DD_MMM_YYYY. */
	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** The Constant UNDER_SCORE. */
	private static final String UNDER_SCORE = "_";

	/**
	 * Instantiates a new decision chart data manager impl.
	 */
	public DecisionChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the decision type by org data.
	 *
	 * @param simpleDateFormat the simple date format
	 * @param dataSeries the data series
	 * @param series the series
	 * @param map the map
	 */
	private static void addDecisionTypeByOrgData(final SimpleDateFormat simpleDateFormat, final DataSeries dataSeries,
			final Series series, final Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> map) {
		for (final Entry<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> entry : map.entrySet()) {
			if (!EMPTY_STRING.equals(entry.getKey())) {

				final XYseries label = new XYseries();
				label.setLabel(entry.getKey());

				series.addSeries(label);

				dataSeries.newSeries();
				for (final ViewRiksdagenCommitteeDecisionTypeOrgDailySummary item : entry.getValue()) {
						dataSeries.add(simpleDateFormat.format(item.getEmbeddedId().getDecisionDate()),
								item.getTotal());
				}
			}
		}
	}

	@Override
	public void createDecisionTypeChart(final AbstractOrderedLayout content) {

		final Map<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> map = getCommitteeDecisionTypeMap();

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		for (final Entry<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> entry : map.entrySet()) {

			if (entry.getKey() != null) {
				series.addSeries(new XYseries().setLabel(entry.getKey()));

				dataSeries.newSeries();
				final List<ViewRiksdagenCommitteeDecisionTypeDailySummary> list = entry.getValue();
				for (final ViewRiksdagenCommitteeDecisionTypeDailySummary item : list) {
					dataSeries.add(simpleDateFormat.format(item.getEmbeddedId().getDecisionDate()), item.getTotal());
				}
			}

		}

		addChart(content, "Decision type daily summary",
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLegendInsideOneColumn(series)).show(),
				true);
	}

	@Override
	public void createDecisionTypeChart(final AbstractOrderedLayout content, final String org) {

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MMM_YYYY, Locale.ENGLISH);

		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		final Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> allMap = getCommitteeDecisionTypeOrgMap();

		final List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary> itemList = allMap
				.get(org.toUpperCase(Locale.ENGLISH).replace(UNDER_SCORE, EMPTY_STRING).trim());

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> map = itemList.parallelStream()
					.filter(t -> t != null && t.getEmbeddedId().getDecisionDate() != null)
					.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getDecisionType()));

			addDecisionTypeByOrgData(simpleDateFormat, dataSeries, series, map);
		}

		addChart(content, "Org Decision type daily summary",
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLegendInsideOneColumn(series)).show(),
				true);
	}

	/**
	 * Gets the committee decision type map.
	 *
	 * @return the committee decision type map
	 */
	private Map<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> getCommitteeDecisionTypeMap() {
		final DataContainer<ViewRiksdagenCommitteeDecisionTypeDailySummary, RiksdagenCommitteeDecisionTypeSummaryEmbeddedId> committeeBallotDecisionPartyDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommitteeDecisionTypeDailySummary.class);

		final Date now = new Date();
		final Date notBefore = new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime();
		return committeeBallotDecisionPartyDataContainer.getAll().parallelStream()
				.filter(t -> t != null && !t.getEmbeddedId().getDecisionDate().after(now)
						&& !notBefore.after(t.getEmbeddedId().getDecisionDate()))
				.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getDecisionType()));
	}

	/**
	 * Gets the committee decision type org map.
	 *
	 * @return the committee decision type org map
	 */
	private Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> getCommitteeDecisionTypeOrgMap() {
		final DataContainer<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary, RiksdagenCommitteeDecisionTypeOrgSummaryEmbeddedId> committeeBallotDecisionPartyDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommitteeDecisionTypeOrgDailySummary.class);

		return committeeBallotDecisionPartyDataContainer.getAll().parallelStream().filter(Objects::nonNull)
				.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getOrg()));
	}

}
