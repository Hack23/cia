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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MinistryRankingMenuItemFactoryImpl.
 */
@Service
public final class MinistryRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements MinistryRankingMenuItemFactory {

	/** The Constant MINISTRIES_LEADER_SCOREBOARD. */
	private static final String MINISTRIES_LEADER_SCOREBOARD = "Ministries leader scoreboard";

	/** The Constant ALL_MINISTRIES_TOTAL_MEMBERS_TEXT. */
	private static final String ALL_MINISTRIES_TOTAL_MEMBERS_TEXT = "All ministries, total members";

	/** The Constant ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT. */
	private static final String ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT = "All parties, total days served in ministries";

	/** The Constant CHART_BY_TOPIC_TEXT. */
	private static final String CHART_BY_TOPIC_TEXT = "Chart by topic";

	/** The Constant COMMAN_OVERVIEW. */
	private static final PageModeMenuCommand COMMAN_OVERVIEW = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT. */
	private static final PageModeMenuCommand COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.ALL_GOVERNMENT_ROLE_CHART.toString());

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

	/** The Constant COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD. */
	private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());

	/** The Constant COMMAND_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	/** The Constant COMMAND_GOVERNMENT_BODIES_EXPENDITURE. */
	private static final PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_EXPENDITURE = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString());

	/** The Constant COMMAND_GOVERNMENT_BODIES_HEADCOUNT. */
	private static final PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_HEADCOUNT = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString());

	/** The Constant COMMAND_GOVERNMENT_BODIES_INCOME. */
	private static final PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_INCOME = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString());

	/** The Constant COMMAND_GOVERNMENT_OUTCOME. */
	private static final PageModeMenuCommand COMMAND_GOVERNMENT_OUTCOME = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			MinistryPageMode.GOVERNMENT_OUTCOME.toString());

	/** The Constant COMMAND_PAGEVISITHISTORY. */
	private static final PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.PAGEVISITHISTORY);

	/**
	 * The Constant
	 * CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_TOTAL_POLTICIAL_DAYS_MEMBERSHIP_DESCRIPTION.
	 */
	private static final String CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_TOTAL_POLTICIAL_DAYS_MEMBERSHIP_DESCRIPTION = "Current and past member and summary of total polticial days membership";

	/** The Constant CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT = "Current ministries, current members";

	/** The Constant CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT. */
	private static final String CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT = "Current parties active in ministries, head count";

	/** The Constant GOVERNMENT_BODY_EXPENDITURE_PER_MINISTRY. */
	private static final String GOVERNMENT_BODY_EXPENDITURE_PER_MINISTRY = "Government body expenditure by ministry";

	/** The Constant GOVERNMENT_BODIES. */
	private static final String GOVERNMENT_BODY_HEADCOUNT_PER_MINISTRY = "Government body headcount by ministry";

	/** The Constant GOVERNMENT_BODY_INCOME_PER_MINISTRY. */
	private static final String GOVERNMENT_BODY_INCOME_PER_MINISTRY = "Government body income by ministry";

	/** The Constant GOVERNMENT_OUTCOME. */
	private static final String GOVERNMENT_OUTCOME = "Government outcome";

	/** The Constant GOVERNMENT_ROLES_CHART. */
	private static final String GOVERNMENT_ROLES_CHART = "Government roles chart";

	/** The Constant MINISTRY_RANKING. */
	private static final String MINISTRY_RANKING = "Ministry Ranking";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The Constant POLITICAL_WORK_SUMMARY_TEXT. */
	private static final String POLITICAL_WORK_SUMMARY_TEXT = "Political Work Summary";

	/** The Constant RANKING_LIST_BY_TOPIC_TEXT. */
	private static final String RANKING_LIST_BY_TOPIC_TEXT = "Ranking list by topic";

	/** The Constant POLITICAL_WORK_SUMMARY_DESCRIPTION. */
	private static final String POLITICAL_WORK_SUMMARY_DESCRIPTION = "Scoreboard all ministries with current/total members and politican days served";

	/** The Constant CURRENT_MINISTRIES_CURRENT_MEMBERS_DESCRIPTION. */
	private static final String CURRENT_MINISTRIES_CURRENT_MEMBERS_DESCRIPTION = "Chart over current ministries by headcount";

	/** The Constant CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_DESCRIPTION. */
	private static final String CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_DESCRIPTION = "Chart over current parties active in ministries by headcount";

	/** The Constant MINISTRIES_LEADER_SCOREBOARD_DESCRIPTION. */
	private static final String MINISTRIES_LEADER_SCOREBOARD_DESCRIPTION = "Ministry leaders scoreboard";

	/** The Constant ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_DESCRIPTION. */
	private static final String ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_DESCRIPTION = "Chart over all parties hold positons in ministries by headcount";

	/** The Constant ALL_MINISTRIES_TOTAL_MEMBERS_DESCRIPTION. */
	private static final String ALL_MINISTRIES_TOTAL_MEMBERS_DESCRIPTION = "Chart over all ministries by total headcount";

	/** The Constant GOVERNMENT_BODY_HEADCOUNT_PER_MINISTRY_DESCRIPTION. */
	private static final String GOVERNMENT_BODY_HEADCOUNT_PER_MINISTRY_DESCRIPTION = "Chart over total headcount for all goverment bodies governed by ministries";

	/** The Constant GOVERNMENT_BODY_INCOME_PER_MINISTRY_DESCRIPTION. */
	private static final String GOVERNMENT_BODY_INCOME_PER_MINISTRY_DESCRIPTION = "Chart over total income for all goverment bodies governed by ministries";

	/** The Constant GOVERNMENT_BODY_EXPENDITURE_PER_MINISTRY_DESCRIPTION. */
	private static final String GOVERNMENT_BODY_EXPENDITURE_PER_MINISTRY_DESCRIPTION = "Chart over total spending for all goverment bodies governed by ministries";

	/** The Constant GOVERNMENT_OUTCOME_DESCRIPTION. */
	private static final String GOVERNMENT_OUTCOME_DESCRIPTION = "Economic and financial data for Sweden, SDDS Plus";

	/** The Constant GOVERNMENT_ROLES_CHART_DESCRIPTION. */
	private static final String GOVERNMENT_ROLES_CHART_DESCRIPTION = "Gantt chart all goverment roles";

	/** The Constant PAGE_VISIT_HISTORY_DESCRIPTION. */
	private static final String PAGE_VISIT_HISTORY_DESCRIPTION = "View history of page visit for this page.";

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

		ministryMenuItem.addItem(OVERVIEW_TEXT, VaadinIcons.GROUP, COMMAN_OVERVIEW);

		final MenuItem listByTopic = ministryMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, VaadinIcons.GROUP, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_WORK_SUMMARY_TEXT, VaadinIcons.GROUP, COMMAND_DATAGRID);
		listItem.setDescription(CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_TOTAL_POLTICIAL_DAYS_MEMBERSHIP_DESCRIPTION);

		final MenuItem chartByTopic = ministryMenuItem.addItem(CHART_BY_TOPIC_TEXT, VaadinIcons.GROUP, null);


		chartByTopic.addItem(MINISTRIES_LEADER_SCOREBOARD, VaadinIcons.GROUP,COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD);

		chartByTopic.addItem(CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT, VaadinIcons.GROUP, COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT, VaadinIcons.GROUP, COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT);

		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT, VaadinIcons.GROUP, COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS);

		chartByTopic.addItem(ALL_MINISTRIES_TOTAL_MEMBERS_TEXT, VaadinIcons.GROUP, COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT);

		chartByTopic.addItem(GOVERNMENT_BODY_HEADCOUNT_PER_MINISTRY, VaadinIcons.GROUP, COMMAND_GOVERNMENT_BODIES_HEADCOUNT);

		chartByTopic.addItem(GOVERNMENT_BODY_INCOME_PER_MINISTRY, VaadinIcons.GROUP, COMMAND_GOVERNMENT_BODIES_INCOME);

		chartByTopic.addItem(GOVERNMENT_BODY_EXPENDITURE_PER_MINISTRY, VaadinIcons.GROUP, COMMAND_GOVERNMENT_BODIES_EXPENDITURE);

		chartByTopic.addItem(GOVERNMENT_OUTCOME, VaadinIcons.GROUP, COMMAND_GOVERNMENT_OUTCOME);

		chartByTopic.addItem(GOVERNMENT_ROLES_CHART, VaadinIcons.GROUP, COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT);

		ministryMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.GROUP, COMMAND_PAGEVISITHISTORY);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid, POLITICAL_WORK_SUMMARY_TEXT, VaadinIcons.GROUP, COMMAND_DATAGRID, POLITICAL_WORK_SUMMARY_DESCRIPTION);

		createButtonLink(grid, CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT, VaadinIcons.GROUP, COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT,
				CURRENT_MINISTRIES_CURRENT_MEMBERS_DESCRIPTION);
		createButtonLink(grid, CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT, VaadinIcons.GROUP, COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT,
				CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_DESCRIPTION);


		createButtonLink(grid, MINISTRIES_LEADER_SCOREBOARD, VaadinIcons.GROUP, COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD,
				MINISTRIES_LEADER_SCOREBOARD_DESCRIPTION);

		createButtonLink(grid, ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT, VaadinIcons.GROUP, COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS,
				ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_DESCRIPTION);
		createButtonLink(grid, ALL_MINISTRIES_TOTAL_MEMBERS_TEXT, VaadinIcons.GROUP, COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT, ALL_MINISTRIES_TOTAL_MEMBERS_DESCRIPTION);
		createButtonLink(grid, GOVERNMENT_BODY_HEADCOUNT_PER_MINISTRY, VaadinIcons.GROUP, COMMAND_GOVERNMENT_BODIES_HEADCOUNT, GOVERNMENT_BODY_HEADCOUNT_PER_MINISTRY_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODY_INCOME_PER_MINISTRY, VaadinIcons.GROUP, COMMAND_GOVERNMENT_BODIES_INCOME, GOVERNMENT_BODY_INCOME_PER_MINISTRY_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODY_EXPENDITURE_PER_MINISTRY, VaadinIcons.GROUP, COMMAND_GOVERNMENT_BODIES_EXPENDITURE, GOVERNMENT_BODY_EXPENDITURE_PER_MINISTRY_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_OUTCOME, VaadinIcons.GROUP, COMMAND_GOVERNMENT_OUTCOME, GOVERNMENT_OUTCOME_DESCRIPTION);


		createButtonLink(grid,GOVERNMENT_ROLES_CHART, VaadinIcons.GROUP, COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT, GOVERNMENT_ROLES_CHART_DESCRIPTION);

		createButtonLink(grid, PAGE_VISIT_HISTORY_TEXT, VaadinIcons.GROUP, COMMAND_PAGEVISITHISTORY, PAGE_VISIT_HISTORY_DESCRIPTION);

	}

}
