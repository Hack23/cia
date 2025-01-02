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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyCoalationAgainstAnnualSummary;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyCoalationAgainstAnnualSummaryEmbeddedId;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PartyCoalationChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class PartyCoalationChartDataManagerImpl.
 */
@Service
public final class PartyCoalationChartDataManagerImpl extends AbstractChartDataManagerImpl
		implements PartyCoalationChartDataManager {

	/** The Constant FILTER_LOW_VALUES. */
	private static final int FILTER_LOW_VALUES = 2;

	/**
	 * Instantiates a new party coalation chart data manager impl.
	 */
	public PartyCoalationChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the data.
	 *
	 * @param map the map
	 * @param series the series
	 * @param dataSeries the data series
	 */
	private static void addData(final Map<String, List<ViewRiksdagenPartyCoalationAgainstAnnualSummary>> map,
			final Series series, final DataSeries dataSeries) {
		final Set<Entry<String, List<ViewRiksdagenPartyCoalationAgainstAnnualSummary>>> entryMap = map.entrySet();

		for (final Entry<String, List<ViewRiksdagenPartyCoalationAgainstAnnualSummary>> entry : entryMap) {
			if (!entry.getValue().isEmpty()) {
				series.addSeries(new XYseries().setLabel(entry.getKey()));

				dataSeries.newSeries();
				for (final ViewRiksdagenPartyCoalationAgainstAnnualSummary data : entry.getValue()) {
					dataSeries.add(data.getEmbeddedId().getYear() + "-01-01", data.getTotal());
				}
			}
		}
	}

	@Override
	public void createPartyChart(final AbstractOrderedLayout layout, final String partyId) {

		final DataContainer<ViewRiksdagenPartyCoalationAgainstAnnualSummary, ViewRiksdagenPartyCoalationAgainstAnnualSummaryEmbeddedId> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyCoalationAgainstAnnualSummary.class);

		final Map<String, List<ViewRiksdagenPartyCoalationAgainstAnnualSummary>> partySummaryMap = dataContainer.getAll().parallelStream()
				.filter(summary -> summary.getEmbeddedId().getGroupAgainst().contains(partyId) && summary.getTotal() > FILTER_LOW_VALUES)
				.collect(Collectors.groupingBy(summary -> summary.getEmbeddedId().getGroupAgainst()));

		final Series chartSeries = new Series();
		final DataSeries chartDataSeries = new DataSeries();

		addData(partySummaryMap, chartSeries, chartDataSeries);

		ChartUtils.addChart(layout, "Part of coalations against committee proposal in ballot more than twice", new DCharts()
				.setDataSeries(chartDataSeries).setOptions(getChartOptions().createOptionsCountryLineChart(chartSeries)).show(),
				true);
	}

}
