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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class MinistryRankingMenuItemFactoryImpl.
 */
@Service
public final class MinistryRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements MinistryRankingMenuItemFactory {

	/** The Constant GOVERNMENT_ROLES_CHART. */
	private static final String GOVERNMENT_ROLES_CHART = "Government roles chart";

	/** The Constant COMMAND_PAGEVISITHISTORY. */
	private static final PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT. */
	private static final PageModeMenuCommand COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYHEADCOUNT.toString());

	/** The Constant COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS. */
	private static final PageModeMenuCommand COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.ALLMINISTRIESPARTYBYTOTALDAYS.toString());

	/** The Constant COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT. */
	private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESBYHEADCOUNT.toString());

	/** The Constant COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT. */
	private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.CURRENTPARTIESBYHEADCOUNT.toString());

	/** The Constant COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT. */
	private static final PageModeMenuCommand COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.ALL_GOVERNMENT_ROLE_CHART.toString());

	/** The Constant COMMAND_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	/** The Constant COMMAN_OVERVIEW. */
	private static final PageModeMenuCommand COMMAN_OVERVIEW = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_GOVERNMENT_BODIES. */
	private static final PageModeMenuCommand COMMAND_GOVERNMENT_BODIES = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			MinistryPageMode.GOVERNMENT_BODIES.toString());

	/** The Constant GOVERNMENT_BODIES. */
	private static final String GOVERNMENT_BODIES = "Government bodies";

	/** The Constant MINISTRY_RANKING. */
	private static final String MINISTRY_RANKING = "Ministry Ranking";

	/** The Constant ALL_MINISTRIES_TOTAL_MEMBERS_TEXT. */
	private static final String ALL_MINISTRIES_TOTAL_MEMBERS_TEXT = "All ministries, total members";

	/** The Constant ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT. */
	private static final String ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT = "All parties, total days served in ministries";

	/** The Constant CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT. */
	private static final String CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT = "Current parties active in ministries, head count";

	/** The Constant CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT = "Current ministries, current members";

	/**
	 * The Constant
	 * CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_TOTAL_POLTICIAL_DAYS_MEMBERSHIP_DESCRIPTION.
	 */
	private static final String CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_TOTAL_POLTICIAL_DAYS_MEMBERSHIP_DESCRIPTION = "Current and past member and summary of total polticial days membership";

	/** The Constant POLITICAL_WORK_SUMMARY_TEXT. */
	private static final String POLITICAL_WORK_SUMMARY_TEXT = "Political Work Summary";

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
	 * Instantiates a new ministry ranking menu item factory impl.
	 */
	public MinistryRankingMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createMinistryRankingMenuBar(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		createMinistryRankingTopics(menuBar.addItem(MINISTRY_RANKING, null, null));

	}

	@Override
	public void createMinistryRankingTopics(final MenuItem ministryMenuItem) {

		ministryMenuItem.addItem(OVERVIEW_TEXT, FontAwesome.GROUP, COMMAN_OVERVIEW);

		final MenuItem listByTopic = ministryMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, FontAwesome.GROUP, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_WORK_SUMMARY_TEXT, FontAwesome.GROUP, COMMAND_DATAGRID);
		listItem.setDescription(CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_TOTAL_POLTICIAL_DAYS_MEMBERSHIP_DESCRIPTION);

		final MenuItem chartByTopic = ministryMenuItem.addItem(CHART_BY_TOPIC_TEXT, FontAwesome.GROUP, null);

		chartByTopic.addItem(CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT, FontAwesome.GROUP, COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT, FontAwesome.GROUP, COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT);

		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT, FontAwesome.GROUP, COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS);
		chartByTopic.addItem(ALL_MINISTRIES_TOTAL_MEMBERS_TEXT, FontAwesome.GROUP, COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT);

		chartByTopic.addItem(GOVERNMENT_BODIES, FontAwesome.GROUP, COMMAND_GOVERNMENT_BODIES);

		chartByTopic.addItem(GOVERNMENT_ROLES_CHART, FontAwesome.GROUP, COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT);

		ministryMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, FontAwesome.GROUP, COMMAND_PAGEVISITHISTORY);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final GridLayout grid = createGridLayout(panelContent);

		createButtonLink(grid, POLITICAL_WORK_SUMMARY_TEXT, FontAwesome.GROUP, COMMAND_DATAGRID, "Default description");

		createButtonLink(grid, CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT, FontAwesome.GROUP, COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT,
				"Default description");
		createButtonLink(grid, CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT, FontAwesome.GROUP, COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT,
				"Default description");

		createButtonLink(grid, ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT, FontAwesome.GROUP, COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS,
				"Default description");
		createButtonLink(grid, ALL_MINISTRIES_TOTAL_MEMBERS_TEXT, FontAwesome.GROUP, COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT, "Default description");
		createButtonLink(grid, GOVERNMENT_BODIES, FontAwesome.GROUP, COMMAND_GOVERNMENT_BODIES, "Default description");

		createButtonLink(grid,GOVERNMENT_ROLES_CHART, FontAwesome.GROUP, COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT, "Default description");

		createButtonLink(grid, PAGE_VISIT_HISTORY_TEXT, FontAwesome.GROUP, COMMAND_PAGEVISITHISTORY, "Default description");

	}

}
