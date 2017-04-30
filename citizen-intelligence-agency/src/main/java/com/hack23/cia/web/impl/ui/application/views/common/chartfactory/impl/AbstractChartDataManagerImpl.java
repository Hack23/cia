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

import java.util.Optional;

import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

/**
 * The Class AbstractChartDataManagerImpl.
 */
public abstract class AbstractChartDataManagerImpl {


	/** The Constant WINDOW_WIDTH_REDUCTION. */
	private static final int WINDOW_WIDTH_REDUCTION = 50;

	/** The Constant WINDOW_HEIGHT_REDUCTION. */
	private static final int WINDOW_HEIGHT_REDUCTION = 200;

	/** The Constant CHART_HEIGHT_REDUCTION. */
	private static final int CHART_HEIGHT_REDUCTION = 100;

	/** The Constant CHART_WIDTH_REDUCTION. */
	private static final int CHART_WIDTH_REDUCTION = 50;

	/** The Constant CHART_MARGIN_SIZE. */
	private static final int CHART_MARGIN_SIZE = 5;

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new abstract chart data manager impl.
	 */
	public AbstractChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the chart.
	 *
	 * @param content
	 *            the content
	 * @param caption
	 *            the caption
	 * @param chart
	 *            the chart
	 */
	protected final void addChart(final AbstractOrderedLayout content,final String caption, final DCharts chart) {
		final HorizontalLayout horizontalLayout = new HorizontalLayout();

		final int browserWindowWidth = Page.getCurrent().getBrowserWindowWidth() - WINDOW_WIDTH_REDUCTION;
		final int browserWindowHeight = Page.getCurrent().getBrowserWindowHeight() - WINDOW_HEIGHT_REDUCTION;

		horizontalLayout.setWidth(browserWindowWidth, Unit.PIXELS);
		horizontalLayout.setHeight(browserWindowHeight, Unit.PIXELS);


		final Panel formPanel = new Panel();
		formPanel.setSizeFull();
		formPanel.setContent(horizontalLayout);

		content.addComponent(formPanel);
		content.setExpandRatio(formPanel, ContentRatio.LARGE);


		chart.setWidth(browserWindowWidth - CHART_WIDTH_REDUCTION, Unit.PIXELS);
		chart.setHeight(browserWindowHeight - CHART_HEIGHT_REDUCTION, Unit.PIXELS);
		chart.setMarginRight(CHART_MARGIN_SIZE);
		chart.setMarginLeft(CHART_MARGIN_SIZE);
		chart.setMarginBottom(CHART_MARGIN_SIZE);
		chart.setMarginTop(CHART_MARGIN_SIZE);

		horizontalLayout.addComponent(chart);
		chart.setCaption(caption);
	}

	/**
	 * Gets the party name.
	 *
	 * @param party
	 *            the party
	 * @return the party name
	 */
	protected final String getPartyName(final String party) {
		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		final Optional<ViewRiksdagenParty> matchingObjects =dataContainer.getAll().stream().
			    filter(p -> p.getPartyId().equalsIgnoreCase(party)).
			    findFirst();

		if (matchingObjects.isPresent()) {
			return matchingObjects.get().getPartyName();

		} else {
			return party;
		}
	}


}
