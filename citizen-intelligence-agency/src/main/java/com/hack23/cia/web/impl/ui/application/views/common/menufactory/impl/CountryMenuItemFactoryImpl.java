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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;
import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary_;
import com.hack23.cia.model.internal.application.data.impl.WorldbankIndicatorDataCountrySummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.impl.WorldbankIndicatorDataCountrySummaryEmbeddedId_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CountryMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CountryMenuItemFactoryImpl.
 */
@Service
public final class CountryMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements CountryMenuItemFactory {

	/** The Constant COMMAND_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The Constant COMMAND_PAGEVISITHISTORY. */
	private static final PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

	/** The Constant COUNTRY_INDICATORS_SWEDEN. */
	private static final String COUNTRY_INDICATORS_SWEDEN = "Country Indicators, Sweden";


	/** The Constant COUNTRY_RANKING_TEXT. */
	private static final String COUNTRY_RANKING_TEXT = "Counry Ranking";

	/** The Constant DATA_POINTS_FOR_YEAR_ABOVE. */
	private static final int DATA_POINTS_FOR_YEAR_ABOVE = 2010;

	/** The Constant MINIMUM_NUMBER_DATA_POINTS. */
	private static final int MINIMUM_NUMBER_DATA_POINTS = 10;

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/** The application menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;

	/**
	 * Instantiates a new country menu item factory impl.
	 */
	public CountryMenuItemFactoryImpl() {
		super();
	}

	/**
	 * Adds the sources and indicators to menu.
	 *
	 * @param countryIndicators
	 *            the country indicators
	 * @param sourceIndicatorMap
	 *            the source indicator map
	 */
	private static void addSourcesAndIndicatorsToMenu(final MenuItem countryIndicators,
			final Map<String, List<ViewWorldbankIndicatorDataCountrySummary>> sourceIndicatorMap) {

		final Map<String, List<ViewWorldbankIndicatorDataCountrySummary>> sortedIndicatorMap = sourceIndicatorMap
				.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		for (final Entry<String, List<ViewWorldbankIndicatorDataCountrySummary>> entry : sortedIndicatorMap
				.entrySet()) {

			final MenuItem sourceItems = countryIndicators.addItem(entry.getKey(), null, null);

			final List<ViewWorldbankIndicatorDataCountrySummary> sortedEntries = entry.getValue().stream()
					.sorted((e1, e2) -> e1.getIndicatorName().compareTo(e2.getIndicatorName()))
					.toList();

			for (final ViewWorldbankIndicatorDataCountrySummary indciatorSummary : sortedEntries) {
				final MenuItem addItem = sourceItems.addItem(indciatorSummary.getIndicatorName(),
						new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.INDICATORS,
								indciatorSummary.getEmbeddedId().getIndicatorId()));
				addItem.setStyleName("RestrictedHeader");
			}
		}

	}

	@Override
	public void createCountryTopicMenu(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);


		createCountryTopicMenu( menuBar.addItem(COUNTRY_RANKING_TEXT, VaadinIcons.SERVER, null));

	}

	@Override
	public void createCountryTopicMenu(final MenuItem charts) {
		charts.addItem(OVERVIEW_TEXT, VaadinIcons.LINE_CHART,
				COMMAND_OVERVIEW);

		final MenuItem countryIndicators = charts.addItem(COUNTRY_INDICATORS_SWEDEN, VaadinIcons.LINE_CHART, null);

		addSourcesAndIndicatorsToMenu(countryIndicators, getTopicIndicatorMap());

		charts.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.LINE_CHART, COMMAND_PAGEVISITHISTORY);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final MenuBar menuBar = new MenuBar();
		panelContent.addComponent(menuBar);
		panelContent.setComponentAlignment(menuBar, Alignment.TOP_LEFT);
		panelContent.setExpandRatio(menuBar, ContentRatio.LARGE);

		addSourcesAndIndicatorsToMenu(menuBar.addItem("By Topic",VaadinIcons.LINE_CHART, null), getTopicIndicatorMap());
		menuBar.setAutoOpen(true);
	}

	/**
	 * Gets the topic indicator map.
	 *
	 * @return the topic indicator map
	 */
	private Map<String, List<ViewWorldbankIndicatorDataCountrySummary>> getTopicIndicatorMap() {
		final DataContainer<ViewWorldbankIndicatorDataCountrySummary, WorldbankIndicatorDataCountrySummaryEmbeddedId> indicatorDataCountrSummaryDailyDataContainer = applicationManager
				.getDataContainer(ViewWorldbankIndicatorDataCountrySummary.class);

		return indicatorDataCountrSummaryDailyDataContainer
				.findListByEmbeddedProperty(ViewWorldbankIndicatorDataCountrySummary.class,ViewWorldbankIndicatorDataCountrySummary_.embeddedId,WorldbankIndicatorDataCountrySummaryEmbeddedId.class,WorldbankIndicatorDataCountrySummaryEmbeddedId_.countryId,"SE").parallelStream()
				.filter(t -> t != null && t.getSourceValue() != null && t.getEndYear() > DATA_POINTS_FOR_YEAR_ABOVE && t.getDataPoint() > MINIMUM_NUMBER_DATA_POINTS)
				.flatMap(t -> Arrays.asList(t.getTopics().split(";")).stream()
						.map(topic -> new AbstractMap.SimpleEntry<>(topic, t)))

				.collect(Collectors.groupingBy(SimpleEntry::getKey,
						Collectors.mapping(SimpleEntry::getValue, Collectors.toList())));
	}
}
