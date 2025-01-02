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
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyBallotSupportAnnualSummary;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyBallotSupportAnnualSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyBallotSupportAnnualSummaryEmbeddedId_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyBallotSupportAnnualSummary_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PartySupportsChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class PartySupportsChartDataManagerImpl.
 */
@Service
public final class PartySupportsChartDataManagerImpl extends AbstractChartDataManagerImpl
		implements PartySupportsChartDataManager {

	/**
	 * Instantiates a new party supports chart data manager impl.
	 */
	public PartySupportsChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the data.
	 *
	 * @param map        the map
	 * @param series     the series
	 * @param dataSeries the data series
	 */
	private static void addData(final Map<String, List<ViewRiksdagenPartyBallotSupportAnnualSummary>> map, final Series series,
			final DataSeries dataSeries) {
		final Set<Entry<String, List<ViewRiksdagenPartyBallotSupportAnnualSummary>>> entryMap = map.entrySet();

		for (final Entry<String, List<ViewRiksdagenPartyBallotSupportAnnualSummary>> entry : entryMap) {
			series.addSeries(new XYseries().setLabel(entry.getKey()));

			dataSeries.newSeries();
			for (final ViewRiksdagenPartyBallotSupportAnnualSummary data : entry.getValue()) {
					dataSeries.add(data.getEmbeddedId().getDate(),
							100 - data.getDisagreePercentage());
			}

		}
	}

	@Override
	public void createPartyChart(final AbstractOrderedLayout content, final String partyId) {

		final DataContainer<ViewRiksdagenPartyBallotSupportAnnualSummary, ViewRiksdagenPartyBallotSupportAnnualSummaryEmbeddedId> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyBallotSupportAnnualSummary.class);

		final List<ViewRiksdagenPartyBallotSupportAnnualSummary> list = dataContainer.findListByEmbeddedProperty(ViewRiksdagenPartyBallotSupportAnnualSummary.class,
				ViewRiksdagenPartyBallotSupportAnnualSummary_.embeddedId,
				ViewRiksdagenPartyBallotSupportAnnualSummaryEmbeddedId.class,
				ViewRiksdagenPartyBallotSupportAnnualSummaryEmbeddedId_.party, partyId);

		final Map<String, List<ViewRiksdagenPartyBallotSupportAnnualSummary>> map = list.parallelStream().filter(Objects::nonNull)
		.collect(Collectors.groupingBy(t -> t.getEmbeddedId().getOtherParty()));

		final Series series = new Series();
		final DataSeries dataSeries = new DataSeries();

		addData(map, series, dataSeries);

		addChart(content,"Party support ballot", new DCharts().setDataSeries(dataSeries).setOptions(getChartOptions().createOptionsPartyLineChart(series)).show(), true);
	}

}
