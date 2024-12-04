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
package com.hack23.cia.web.impl.ui.application.views.user.country.pagemode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.worldbank.data.impl.Indicator;
import com.hack23.cia.model.external.worldbank.data.impl.Indicator_;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData_;
import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;
import com.hack23.cia.model.internal.application.data.impl.WorldbankIndicatorDataCountrySummaryEmbeddedId;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.WorldIndicatorChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class WorldIndicatorsPageModContentFactoryImpl.
 */
@Component
public final class WorldIndicatorsPageModContentFactoryImpl extends AbstractCountryPageModContentFactoryImpl {

	private static final List<String> AS_LIST = Arrays.asList("indicatorName",
			   "sourceValue",
			   "sourceOrganization");

	/** The Constant WORLD_INDICATORS. */
	private static final String WORLD_INDICATORS = "World Indicators";

	/** The chart data manager. */
	@Autowired
	private WorldIndicatorChartDataManager chartDataManager;

	/**
	 * Instantiates a new world indicators page mod content factory impl.
	 */
	public WorldIndicatorsPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		getCountryMenuItemFactory().createCountryTopicMenu(menuBar);

		final String pageId = getPageId(parameters);

		final String indicator = parameters.substring(PageMode.INDICATORS.toString().length()+"/".length());

		createDataIndicatorSummaryChartPanel(panelContent,indicator,panel);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COUNTRY_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);

		return panelContent;

	}

	/**
	 * Creates the data indicator summary chart panel.
	 *
	 * @param verticalLayout
	 *            the vertical layout
	 * @param indicator
	 *            the indicator
	 * @param panel
	 *            the panel
	 */
	private void createDataIndicatorSummaryChartPanel(final VerticalLayout verticalLayout,final String indicator,final Panel panel) {

		final DataContainer<ViewWorldbankIndicatorDataCountrySummary, WorldbankIndicatorDataCountrySummaryEmbeddedId> indicatorDataCountrSummaryDailyDataContainer = getApplicationManager()
				.getDataContainer(ViewWorldbankIndicatorDataCountrySummary.class);


		final Optional<ViewWorldbankIndicatorDataCountrySummary> indicatorSummary = indicatorDataCountrSummaryDailyDataContainer
				.getAll()
				.parallelStream()
				.filter(t -> t != null && t.getEmbeddedId().getIndicatorId().equals(indicator)).findFirst();


		ViewWorldbankIndicatorDataCountrySummary indicatorSummaryValue = null;
		if (indicatorSummary.isPresent()) {
			indicatorSummaryValue = indicatorSummary.get();
			panel.setCaption(new StringBuilder().append("World Indicator").append(indicatorSummaryValue.getTopics()).append(" - ").append(indicatorSummaryValue.getIndicatorName()).toString());

			getFormFactory().addFormPanelTextFields(verticalLayout,
					indicatorSummaryValue,
							ViewWorldbankIndicatorDataCountrySummary.class,
							AS_LIST);
		} else {
			panel.setCaption(new StringBuilder().append("World Indicator").toString());
		}
		

		final DataContainer<WorldBankData, Serializable> dataContainer = getApplicationManager()
		.getDataContainer(WorldBankData.class);


		final List<WorldBankData> dataList = dataContainer.findListByEmbeddedProperty(WorldBankData.class, WorldBankData_.indicator, Indicator.class, Indicator_.id, indicator);

		chartDataManager.createIndicatorChart(verticalLayout,dataList,indicatorSummaryValue);

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PageMode.INDICATORS.toString());
	}

}
