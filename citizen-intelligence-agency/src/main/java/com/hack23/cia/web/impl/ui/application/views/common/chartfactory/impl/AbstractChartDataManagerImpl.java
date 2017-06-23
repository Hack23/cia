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
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.HorizontalLayout;

/**
 * The Class AbstractChartDataManagerImpl.
 */
public abstract class AbstractChartDataManagerImpl {


	/** The Constant CHART_MARGIN_SIZE. */
	private static final int CHART_BOTTOM_MARGIN_SIZE = 2;
	private static final int CHART_RIGHT_MARGIN = 2;
	private static final int CHART_LEFT_MARGIN= 2;
	private static final int CHART_TOP_MARGIN_SIZE = 2;

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
	 * @param fullPage TODO
	 */
	protected final void addChart(final AbstractOrderedLayout content,final String caption, final DCharts chart, boolean fullPage) {
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

	private int getChartWindowWidth() {
		return Math.max((Page.getCurrent().getBrowserWindowWidth() -50),600);
	}

	private int getChartWindowHeight(boolean fullPage) {
		if (fullPage) {
			return Math.max((Page.getCurrent().getBrowserWindowHeight() / 20) * 16,400);
		} else {
			return Math.max(Page.getCurrent().getBrowserWindowHeight() / 2,200);
		}
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
