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

import java.util.Optional;

import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartOptions;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

/**
 * The Class AbstractChartDataManagerImpl.
 */
public abstract class AbstractChartDataManagerImpl {

	/** The Constant CHART_MARGIN_SIZE. */
	private static final int CHART_BOTTOM_MARGIN_SIZE = 2;

	/** The Constant CHART_LEFT_MARGIN. */
	private static final int CHART_LEFT_MARGIN= 2;

	/** The Constant CHART_RIGHT_MARGIN. */
	private static final int CHART_RIGHT_MARGIN = 2;

	/** The Constant CHART_TOP_MARGIN_SIZE. */
	private static final int CHART_TOP_MARGIN_SIZE = 2;

	/** The Constant CHART_WIDTH_REDUCTION. */
	private static final int CHART_WIDTH_REDUCTION = 50;

	/** The Constant HEIGHT_PERCENTAGE_FULL_PAGE. */
	private static final double HEIGHT_PERCENTAGE_FULL_PAGE = 0.8;
	/** The Constant HEIGHT_PERCETAGE_HALF_PAGE. */
	private static final double HEIGHT_PERCETAGE_HALF_PAGE = 0.5;

	/** The Constant MINIMUM_CHART_HEIGHT_FULL_PAGE. */
	private static final int MINIMUM_CHART_HEIGHT_FULL_PAGE = 400;

	/** The Constant MINIMUM_CHART_WIDTH. */
	private static final int MINIMUM_CHART_WIDTH = 600;

	/** The Constant NINIMUM_CHART_HEIGHT_HALF_PAGE. */
	private static final int NINIMUM_CHART_HEIGHT_HALF_PAGE = 200;

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
	 * Gets the chart window height.
	 *
	 * @param fullPage the full page
	 * @return the chart window height
	 */
	private static int getChartWindowHeight(final boolean fullPage) {
		if (fullPage) {
			return Math.max((int) (Page.getCurrent().getBrowserWindowHeight() * HEIGHT_PERCENTAGE_FULL_PAGE) ,MINIMUM_CHART_HEIGHT_FULL_PAGE);
		} else {
			return Math.max((int) (Page.getCurrent().getBrowserWindowHeight() * HEIGHT_PERCETAGE_HALF_PAGE),NINIMUM_CHART_HEIGHT_HALF_PAGE);
		}
	}

	/**
	 * Gets the chart window width.
	 *
	 * @return the chart window width
	 */
	private static int getChartWindowWidth() {
		return Math.max(Page.getCurrent().getBrowserWindowWidth() - CHART_WIDTH_REDUCTION,MINIMUM_CHART_WIDTH);
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
	 * @param fullPage
	 *            the full page
	 */
	protected static final void addChart(final AbstractOrderedLayout content,final String caption, final DCharts chart, final boolean fullPage) {
		final HorizontalLayout horizontalLayout = new HorizontalLayout();

		final int browserWindowWidth = getChartWindowWidth();

		final int browserWindowHeight = getChartWindowHeight(fullPage);

		horizontalLayout.setWidth(browserWindowWidth, Unit.PIXELS);
		horizontalLayout.setHeight(browserWindowHeight, Unit.PIXELS);
		horizontalLayout.setMargin(true);
		horizontalLayout.setSpacing(false);
		horizontalLayout.addStyleName("v-layout-content-overview-panel-level1");

		final Panel formPanel = new Panel();
		formPanel.setSizeFull();
		formPanel.setContent(horizontalLayout);
		formPanel.setCaption(caption);

		content.addComponent(formPanel);
		content.setExpandRatio(formPanel, ContentRatio.LARGE);


		chart.setWidth(100, Unit.PERCENTAGE);
		chart.setHeight(100, Unit.PERCENTAGE);
		chart.setMarginRight(CHART_RIGHT_MARGIN);
		chart.setMarginLeft(CHART_LEFT_MARGIN);
		chart.setMarginBottom(CHART_BOTTOM_MARGIN_SIZE);
		chart.setMarginTop(CHART_TOP_MARGIN_SIZE);

		horizontalLayout.addComponent(chart);
		chart.setCaption(caption);
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
	 * @param party
	 *            the party
	 * @return the party name
	 */
	protected final String getPartyName(final String party) {
		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		final Optional<ViewRiksdagenParty> matchingObjects =dataContainer.getAll().stream().
			    filter((final ViewRiksdagenParty p) -> p.getPartyId().equalsIgnoreCase(party)).
			    findFirst();

		if (matchingObjects.isPresent()) {
			return matchingObjects.get().getPartyName();

		} else {
			return party;
		}
	}


}
