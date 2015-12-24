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
package com.hack23.cia.web.impl.ui.application.views.user.test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.worldbank.data.impl.Indicator;
import com.hack23.cia.model.external.worldbank.data.impl.Indicator_;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData_;
import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;
import com.hack23.cia.model.internal.application.data.impl.WorldbankIndicatorDataCountrySummaryEmbeddedId;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.AbstractView;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;



/**
 * The Class TestChartView.
 */
@Service
@Scope("prototype")
@VaadinView(value = TestChartView.NAME, cached = true)
public final class TestChartView extends AbstractView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.TEST_CHART_VIEW_NAME;


	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	transient ApplicationManager applicationManager;

	/** The chart data manager. */
	@Autowired
	private transient ChartDataManager chartDataManager;

	/** The menu item factory. */
	@Autowired
	private transient MenuItemFactory menuItemFactory;

	/** The form factory. */
	@Autowired
	private transient FormFactory formFactory;


	/** The page mode content. */
	private VerticalLayout pageModeContent;

	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();

		setCaption("Citizen Intelligence Agency::Test Chart View	");

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		final MenuBar barmenu = new MenuBar();
		layout.addComponent(barmenu);

		menuItemFactory.createTestTopicMenu(barmenu);


		pageModeContent = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		layout.addComponent(pageModeContent);

		pageModeContent.addComponent(new Label("overview"));


		layout.addComponent(pageLinkFactory.createMainViewPageLink());

		setContent(layout);

	}



	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
	 * .ViewChangeEvent)
	 */
	@Override
	public void enter(final ViewChangeEvent event) {

		if (pageModeContent.getComponentCount() != 0) {
			pageModeContent.removeAllComponents();
		}

		final String parameters = event.getParameters();

		if (StringUtils.isEmpty(parameters) ||parameters.contains(PageMode.Overview.toString())) {
				pageModeContent.addComponent(new Label("overview"));
		} else if (parameters.contains(PageMode.Charts.toString())) {

			if (parameters.contains(ChartIndicators.PartyWinner.toString())) {
				pageModeContent.addComponent(chartDataManager.createPartyWinnerChart());
			} else if (parameters.contains(ChartIndicators.DocumentActivityByType.toString())) {
				pageModeContent.addComponent(chartDataManager.createDocumentTypeChart());
			} else if (parameters.contains(ChartIndicators.DecsionActivityByType.toString())) {
				pageModeContent.addComponent(chartDataManager.createDecisionTypeChart());
			}
		} else if (parameters.contains(PageMode.Indicators.toString())) {

				final String indicator = parameters.substring(PageMode.Indicators.toString().length()+"/".length(), parameters.length());

				pageModeContent.addComponent(createDataIndicatorSummaryChartPanel(indicator));
		}

		pageActionEventHelper.createPageEvent(ViewAction.VISIT_TEST_CHART_VIEW, ApplicationEventGroup.USER, NAME, parameters, null);


	}

	/**
	 * Creates the data indicator summary chart panel.
	 *
	 * @param indicator
	 *            the indicator
	 * @return the component
	 */
	private Component createDataIndicatorSummaryChartPanel(final String indicator) {
		final VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSizeFull();

		final DataContainer<ViewWorldbankIndicatorDataCountrySummary, WorldbankIndicatorDataCountrySummaryEmbeddedId> indicatorDataCountrSummaryDailyDataContainer = applicationManager
				.getDataContainer(ViewWorldbankIndicatorDataCountrySummary.class);


		final Optional<ViewWorldbankIndicatorDataCountrySummary> indicatorSummary = indicatorDataCountrSummaryDailyDataContainer
				.getAll()
				.parallelStream()
				.filter(t -> t != null && t.getEmbeddedId().getIndicatorId().equals(indicator)).findFirst();


		if (indicatorSummary.isPresent()) {
			formFactory.addTextFields(verticalLayout,
					new BeanItem<ViewWorldbankIndicatorDataCountrySummary>(
							indicatorSummary.get()),
							ViewWorldbankIndicatorDataCountrySummary.class,
							Arrays.asList(new String[] {
									   "indicatorName",
									   "sourceValue",
									   "sourceNote",
									   "sourceOrganization",
									   "startYear",
									   "endYear",
									   "dataPoint","topics"}));

		}

		final DataContainer<WorldBankData, Serializable> dataContainer = applicationManager
		.getDataContainer(WorldBankData.class);


		final List<WorldBankData> dataList = dataContainer.findListByEmbeddedProperty(WorldBankData.class, WorldBankData_.indicator, Indicator.class, Indicator_.id, indicator);


		verticalLayout.addComponent(chartDataManager.createIndicatorChart(dataList,indicatorSummary.get()));

		return verticalLayout;
	}







}