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
package com.hack23.cia.web.impl.ui.application.views.user.country.pagemode;

import java.io.Serializable;
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
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class WorldIndicatorsPageModContentFactoryImpl.
 */
@Component
public final class WorldIndicatorsPageModContentFactoryImpl extends AbstractCountryPageModContentFactoryImpl {

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
		CardInfoRowUtil.createPageHeader(panel, panelContent,
			CountryViewConstants.INDICATOR_TITLE,
			CountryViewConstants.INDICATOR_SUBTITLE,
			CountryViewConstants.INDICATOR_DESC);
		final String pageId = getPageId(parameters);

		final String indicator = parameters.substring(PageMode.INDICATORS.toString().length() + "/".length());

		createDataIndicatorSummaryChartPanel(panelContent, indicator, panel);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COUNTRY_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;
	}

	/**
	 * Creates the data indicator summary chart panel.
	 *
	 * @param verticalLayout the vertical layout
	 * @param indicator      the indicator
	 * @param panel          the panel
	 */
	private void createDataIndicatorSummaryChartPanel(final VerticalLayout verticalLayout, final String indicator,
			final Panel panel) {
		final DataContainer<ViewWorldbankIndicatorDataCountrySummary, WorldbankIndicatorDataCountrySummaryEmbeddedId> indicatorDataSummaryContainer = getApplicationManager()
				.getDataContainer(ViewWorldbankIndicatorDataCountrySummary.class);

		final Optional<ViewWorldbankIndicatorDataCountrySummary> indicatorSummary = indicatorDataSummaryContainer
				.getAll().parallelStream()
				.filter(t -> t != null && t.getEmbeddedId().getIndicatorId().equals(indicator)).findFirst();

		ViewWorldbankIndicatorDataCountrySummary indicatorSummaryValue = null;
		if (indicatorSummary.isPresent()) {
			indicatorSummaryValue = indicatorSummary.get();

			// Instead of formFactory, we create a card for the indicator summary
			final Label sectionHeader = new Label(CountryViewConstants.INDICATOR_SUMMARY_HEADER);
			sectionHeader.addStyleName(CountryViewConstants.SECTION_HEADER_STYLE);
			verticalLayout.addComponent(sectionHeader);

			final VerticalLayout card = new VerticalLayout();
			card.setMargin(true);
			card.setSpacing(true);
			card.addStyleName(CountryViewConstants.INDICATOR_SUMMARY_CARD_STYLE);
			Responsive.makeResponsive(card);

			// For each field in AS_LIST, we create a row
			if (indicatorSummaryValue.getIndicatorName() != null) {
				card.addComponent(CardInfoRowUtil.createInfoRow(
					CountryViewConstants.INDICATOR_NAME_LABEL,
					indicatorSummaryValue.getIndicatorName(),
					VaadinIcons.INFO_CIRCLE,
					CountryViewConstants.INDICATOR_NAME_TOOLTIP));
			}
			if (indicatorSummaryValue.getSourceValue() != null) {
				card.addComponent(CardInfoRowUtil.createInfoRow("Source Value:", indicatorSummaryValue.getSourceValue(),
						VaadinIcons.GLOBE, "Source of this indicator data"));
			}
			if (indicatorSummaryValue.getSourceOrganization() != null) {
				card.addComponent(CardInfoRowUtil.createInfoRow("Source Organization:", indicatorSummaryValue.getSourceOrganization(),
						VaadinIcons.INSTITUTION, "Organization providing this data"));
			}

			verticalLayout.addComponent(card);
		}

		final DataContainer<WorldBankData, Serializable> dataContainer = getApplicationManager()
				.getDataContainer(WorldBankData.class);

		final List<WorldBankData> dataList = dataContainer.findListByEmbeddedProperty(WorldBankData.class,
				WorldBankData_.indicator, Indicator.class, Indicator_.id, indicator);

		chartDataManager.createIndicatorChart(verticalLayout, dataList, indicatorSummaryValue);
	}


	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PageMode.INDICATORS.toString());
	}

}
