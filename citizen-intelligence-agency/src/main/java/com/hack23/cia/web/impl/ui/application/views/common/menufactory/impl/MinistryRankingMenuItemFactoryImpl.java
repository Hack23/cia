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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MenuItemRankingPageVisitHistoryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandPageModeConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandRankingHistoryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
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

		createMinistryRankingTopics(menuBar.addItem(MINISTRY_RANKING, VaadinIcons.OFFICE, null));

	}

	@Override
	public void createMinistryRankingTopics(final MenuItem ministryMenuItem) {
		ministryMenuItem.addItem(RANKING_OVERVIEW_TEXT, VaadinIcons.DASHBOARD,
                PageCommandPageModeConstants.COMMAND_OVERVIEW);

		ministryMenuItem.addItem(OVERVIEW_TEXT, VaadinIcons.DASHBOARD,
                PageCommandPageModeConstants.COMMAND_OVERVIEW);

		final MenuItem listItem = ministryMenuItem.addItem(POLITICAL_WORK_SUMMARY_TEXT, VaadinIcons.BAR_CHART,
				COMMAND_MINISTRY_RANKING_DATAGRID);
		listItem.setDescription(CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS);

		final MenuItem chartByTopic = ministryMenuItem.addItem(CHART_BY_TOPIC_TEXT, VaadinIcons.PIE_CHART, null);

		chartByTopic.addItem(MINISTRIES_LEADER_SCOREBOARD, VaadinIcons.TROPHY,
				COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD);

		chartByTopic.addItem(GOVERNMENT_BODIES_HEADCOUNT, VaadinIcons.USER_CHECK, COMMAND_GOVERNMENT_BODIES_HEADCOUNT);

		chartByTopic.addItem(GOVERNMENT_BODIES_INCOME, VaadinIcons.MONEY, COMMAND_GOVERNMENT_BODIES_INCOME);

		chartByTopic.addItem(GOVERNMENT_BODIES_EXPENDITURE, VaadinIcons.MONEY_WITHDRAW,
				COMMAND_GOVERNMENT_BODIES_EXPENDITURE);

		chartByTopic.addItem(GOVERNMENT_OUTCOME, VaadinIcons.BAR_CHART, COMMAND_GOVERNMENT_OUTCOME);

		chartByTopic.addItem(CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT, VaadinIcons.USERS,
				COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_CURRENT_ASSIGNMENTS, VaadinIcons.GROUP,
				COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT);

		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES, VaadinIcons.CALENDAR,
				COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS);

		chartByTopic.addItem(ALL_MINISTRIES_TOTAL_MEMBERS, VaadinIcons.USER,
				COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT);

		chartByTopic.addItem(GOVERNMENT_ROLES_CHART, VaadinIcons.BAR_CHART, COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT);

		ministryMenuItem.addItem(RANKING_PAGE_VISIT_TEXT, VaadinIcons.CHART,
                PageCommandRankingHistoryConstants.MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid, MINISTRIES_LEADER_SCOREBOARD, VaadinIcons.TROPHY,
				COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD,
				MINISTRIES_LEADER_SCOREBOARD_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODIES_HEADCOUNT, VaadinIcons.USER_CHECK, COMMAND_GOVERNMENT_BODIES_HEADCOUNT,
				GOVERNMENT_BODIES_HEADCOUNT_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODIES_INCOME, VaadinIcons.MONEY, COMMAND_GOVERNMENT_BODIES_INCOME,
				GOVERNMENT_BODIES_INCOME_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODIES_EXPENDITURE, VaadinIcons.MONEY_WITHDRAW,
				COMMAND_GOVERNMENT_BODIES_EXPENDITURE, GOVERNMENT_BODIES_EXPENDITURE_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_OUTCOME, VaadinIcons.BAR_CHART, COMMAND_GOVERNMENT_OUTCOME,
				GOVERNMENT_OUTCOME_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_ROLES_CHART, VaadinIcons.LINE_CHART, COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT,
				GOVERNMENT_ROLES_CHART_DESCRIPTION);

		createButtonLink(grid, CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT, VaadinIcons.USERS,
				COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT,
				CURRENT_COMMITTEES_CURRENT_MEMBERS_DESCRIPTION);

		createButtonLink(grid, CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_CURRENT_ASSIGNMENTS, VaadinIcons.GROUP,
				COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT,
				CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS_DESCRIPTION);

		createButtonLink(grid, ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES, VaadinIcons.CALENDAR,
				COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS,
				CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES_DESCRIPTION);
		createButtonLink(grid, ALL_MINISTRIES_TOTAL_MEMBERS, VaadinIcons.USER,
				COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT, ALL_COMMITTEES_TOTAL_MEMBERS_DESCRIPTION);

		createButtonLink(grid, RANKING_PAGE_VISIT_TEXT, VaadinIcons.CHART,
				PageCommandRankingHistoryConstants.MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY,
				MenuItemRankingPageVisitHistoryConstants.PAGE_VISIT_HISTORY_DESCRIPTION);

	}

}
