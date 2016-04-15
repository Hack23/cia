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
package com.hack23.cia.web.impl.ui.application.views.user.common;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.AdminChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AbstractRankingView.
 */
public abstract class AbstractRankingView extends AbstractUserView {

	/** The Constant PAGE_VISIT_HISTORY. */
	private static final String PAGE_VISIT_HISTORY = "Page Visit History:";

	/** The Constant CHARTS. */
	private static final String CHARTS = "Charts:";

	/** The Constant DATAGRID. */
	private static final String DATAGRID = "Datagrid:";

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "Overview:";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The admin chart data manager. */
	@Autowired
	private transient AdminChartDataManager adminChartDataManager;

	/** The chart data manager. */
	@Autowired
	private transient ChartDataManager chartDataManager;



	/**
	 * Instantiates a new abstract ranking view.
	 */
	protected AbstractRankingView() {
		super();
	}



	/**
	 * Creates the menu bar.
	 */
	protected abstract void createMenuBar();

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	protected abstract String getName();

	/**
	 * Gets the view action.
	 *
	 * @return the view action
	 */
	protected abstract ViewAction getViewAction();


	@Override
	public final void enter(final ViewChangeEvent event) {

		createMenuBar();

		final String parameters = event.getParameters();

		final VerticalLayout panelContent = new VerticalLayout();
		panelContent.setSizeFull();
		panelContent.setMargin(true);

		if (StringUtils.isEmpty(parameters) || parameters.contains(PageMode.OVERVIEW.toString())) {


			panelContent.addComponent(createDescription());

			getPanel().setCaption(OVERVIEW + event.getParameters());

		} else 	if (parameters.contains(PageMode.DATAGRID.toString())) {

			panelContent.addComponent(createTable());

			getPanel().setCaption(DATAGRID + event.getParameters());

		} else 	if (parameters.contains(PageMode.CHARTS.toString())) {

			final Layout chartLayout = new HorizontalLayout();
			chartLayout.setSizeFull();


			final Component chartPanelAll = chartDataManager.createChartPanel(createChartTimeSeriesAll(),"All");
			if (chartPanelAll!=null) {
				chartLayout.addComponent(chartPanelAll);
			}

			final Component chartPanelCurrent = chartDataManager.createChartPanel(createChartTimeSeriesCurrent(),"Current");
			if (chartPanelCurrent!=null) {
				chartLayout.addComponent(chartPanelCurrent);
			}
			panelContent.addComponent(chartLayout);

			final Layout extraChartLayout = createExtraChartLayout();
			if (extraChartLayout != null) {
				panelContent.addComponent(extraChartLayout);
			}

			getPanel().setCaption(CHARTS + event.getParameters());

		} else if (parameters.contains(PageMode.PAGEVISITHISTORY.toString())) {

			panelContent.addComponent(adminChartDataManager.createApplicationActionEventPageModeDailySummaryChart(getName()));

			getPanel().setCaption(PAGE_VISIT_HISTORY + event.getParameters());

		}

		getPanel().setContent(panelContent);

		pageActionEventHelper.createPageEvent(getViewAction(), ApplicationEventGroup.USER, getName(), parameters, null);



	}

	/**
	 * Creates the extra chart layout.
	 *
	 * @return the layout
	 */
	protected Layout createExtraChartLayout() {
		return null;
	}

	/**
	 * Creates the table.
	 *
	 * @return the component
	 */
	protected abstract Component createTable();

	/**
	 * Creates the description.
	 *
	 * @return the text area
	 */
	protected abstract TextArea createDescription();

	/**
	 * Creates the chart time series all.
	 *
	 * @return the data series
	 */
	protected abstract DataSeries createChartTimeSeriesAll();

	/**
	 * Creates the chart time series current.
	 *
	 * @return the data series
	 */
	protected abstract DataSeries createChartTimeSeriesCurrent();

}
