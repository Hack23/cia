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

import java.util.Optional;

import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartOptions;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class AbstractChartDataManagerImpl.
 */
public abstract class AbstractChartDataManagerImpl {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/** The chart options. */
	@Autowired
	private ChartOptions chartOptions;

	/**
	 * Instantiates a new abstract chart data manager impl.
	 */
	public AbstractChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the chart.
	 *
	 * @param layout
	 *            the layout
	 * @param caption
	 *            the caption
	 * @param chart
	 *            the chart
	 * @param isFullPage
	 *            the isFullPage
	 */
	protected static final void addChart(final AbstractOrderedLayout layout, final String caption, final DCharts chart, final boolean isFullPage) {
		ChartUtils.addChart(layout, caption, chart, isFullPage);
	}

	/**
	 * Gets the application manager.
	 *
	 * @return the application manager
	 */
	protected final ApplicationManager getApplicationManager() {
		return applicationManager;
	}

	/**
	 * Gets the chart options.
	 *
	 * @return the chart options
	 */
	protected final ChartOptions getChartOptions() {
		return chartOptions;
	}

	/**
	 * Gets the party name.
	 *
	 * @param partySummary
	 *            the partySummary
	 * @return the party name
	 */
	protected final String getPartyName(final String partySummary) {
		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		final Optional<ViewRiksdagenParty> matchingObjects =dataContainer.getAll().stream().
			    filter((final ViewRiksdagenParty p) -> p.getPartyId().equalsIgnoreCase(partySummary)).
			    findFirst();

		if (matchingObjects.isPresent()) {
			return matchingObjects.get().getPartyName();

		} else {
			return partySummary;
		}
	}
}
