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
package com.hack23.cia.web.impl.ui.application.views.user.test.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartConfiguration;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartType;
import at.downdrown.vaadinaddons.highchartsapi.model.data.PieChartData;
import at.downdrown.vaadinaddons.highchartsapi.model.series.PieChartSeries;

/**
 * The Class TestOverviewPageModContentFactoryImpl.
 */
@Component
public final class TestOverviewPageModContentFactoryImpl extends AbstractTestPageModContentFactoryImpl {

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";

	/** The Constant CHART_WIDTH_FULL. */
	private static final int CHART_WIDTH_FULL = 100;

	/** The Constant CHART_HEIGHT. */
	private static final int CHART_HEIGHT = 40;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestOverviewPageModContentFactoryImpl.class);



	/**
	 * Instantiates a new test overview page mod content factory impl.
	 */
	public TestOverviewPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		panelContent.addComponent(new Label(OVERVIEW));

		createHighChartTest(panelContent);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_TEST_CHART_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);
		panel.setCaption(OVERVIEW);

		return panelContent;

	}

	/**
	 * Creates the high chart test.
	 */
	private void createHighChartTest(final VerticalLayout panelContent) {
		final ChartConfiguration pieConfiguration = new ChartConfiguration();
		pieConfiguration.setTitle("Fruits");
		pieConfiguration.setChartType(ChartType.PIE);

		final PieChartSeries pieFruits = new PieChartSeries("Fruits");
		final PieChartData bananas = new PieChartData("Bananas", 33.2);
		final PieChartData melons = new PieChartData("Melons", 6.21);
		final PieChartData apples = new PieChartData("Apples", 3.44);

		pieFruits.getData().add(bananas);
		pieFruits.getData().add(melons);
		pieFruits.getData().add(apples);

		pieConfiguration.getSeriesList().add(pieFruits);

		try {
		   final HighChart pieChart = HighChartFactory.renderChart(pieConfiguration);
		   pieChart.setHeight(CHART_HEIGHT, Unit.PERCENTAGE);
		   pieChart.setWidth(CHART_WIDTH_FULL, Unit.PERCENTAGE);
		   panelContent.addComponent(pieChart);
		   panelContent.setComponentAlignment(pieChart, Alignment.TOP_CENTER);
		} catch (final HighChartsException e) {
			LOGGER.warn("Problem displaying testchart",e);
		}
	}



}
