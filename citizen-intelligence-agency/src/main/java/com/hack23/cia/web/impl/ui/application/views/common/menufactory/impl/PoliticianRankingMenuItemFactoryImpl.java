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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class PoliticianRankingMenuItemFactoryImpl.
 */
@Service
public final class PoliticianRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements PoliticianRankingMenuItemFactory {

	/** The Constant CURRENT_PARTIES. */
	private static final String CURRENT_PARTIES = "Current parties";

	/** The Constant ALL_PARTIES. */
	private static final String ALL_PARTIES = "All parties";

	/** The Constant COMMAND_PAGEVISITHISTORY. */
	private static final PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME,
			PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAND_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	/** The Constant COMMAND_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_ALL_PARTIES. */
	private static final PageModeMenuCommand COMMAND_ALL_PARTIES = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.ALLPARTIES.toString());

	/** The Constant COMMAND_CURRENT_PARTIES. */
	private static final PageModeMenuCommand COMMAND_CURRENT_PARTIES = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.CURRENTPARTIES.toString());

	/** The Constant POLITICIAN_RANKING. */
	private static final String POLITICIAN_RANKING = "Politician Ranking";

	/**
	 * The Constant CURRENT_AND_PAST_ASSIGNMENTS_AND_SUMMARY_EXPERIENCE_IN_DAYS.
	 */
	private static final String CURRENT_AND_PAST_ASSIGNMENTS_AND_SUMMARY_EXPERIENCE_IN_DAYS = "Current and past assignments and summary experience in days";

	/** The Constant POLITICAL_EXPERIENCE_SUMMARY. */
	private static final String POLITICAL_EXPERIENCE_SUMMARY = "Political Experience Summary";

	/** The Constant RANKING_LIST_BY_TOPIC_TEXT. */
	private static final String RANKING_LIST_BY_TOPIC_TEXT = "Ranking list by topic";

	/** The Constant CHART_BY_TOPIC_TEXT. */
	private static final String CHART_BY_TOPIC_TEXT = "Chart by topic";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The application menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;

	/**
	 * Instantiates a new politician ranking menu item factory impl.
	 */
	public PoliticianRankingMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createPoliticianRankingMenuBar(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		createPoliticianRankingTopics(menuBar.addItem(POLITICIAN_RANKING, FontAwesome.BUG, null));
	}

	@Override
	public void createPoliticianRankingTopics(final MenuItem politicianMenuItem) {
		politicianMenuItem.addItem(OVERVIEW_TEXT, FontAwesome.BUG, COMMAND_OVERVIEW);

		final MenuItem listByTopic = politicianMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, FontAwesome.BUG, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_EXPERIENCE_SUMMARY, FontAwesome.BUG, COMMAND_DATAGRID);
		listItem.setDescription(CURRENT_AND_PAST_ASSIGNMENTS_AND_SUMMARY_EXPERIENCE_IN_DAYS);

		final MenuItem chartByTopic = politicianMenuItem.addItem(CHART_BY_TOPIC_TEXT, FontAwesome.BUG, null);

		chartByTopic.addItem(ALL_PARTIES, FontAwesome.GROUP, COMMAND_ALL_PARTIES);

		chartByTopic.addItem(CURRENT_PARTIES, FontAwesome.GROUP, COMMAND_CURRENT_PARTIES);

		politicianMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, FontAwesome.BUG, COMMAND_PAGEVISITHISTORY);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final ResponsiveRow grid = createGridLayout(panelContent);

		createButtonLink(grid, POLITICAL_EXPERIENCE_SUMMARY, FontAwesome.BUG, COMMAND_DATAGRID, "Default description");

		createButtonLink(grid, PAGE_VISIT_HISTORY_TEXT, FontAwesome.BUG, COMMAND_PAGEVISITHISTORY, "Default description");

	}

}
