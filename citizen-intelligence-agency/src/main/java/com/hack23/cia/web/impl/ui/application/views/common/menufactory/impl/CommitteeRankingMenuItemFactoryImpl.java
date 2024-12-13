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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CommitteeRankingMenuItemFactoryImpl.
 */
@Service
public final class CommitteeRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements CommitteeRankingMenuItemFactory {

	/** The Constant ALL_COMMITTEES_TOTAL_MEMBERS. */
	private static final String ALL_COMMITTEES_TOTAL_MEMBERS = "All committees, total members";

	/** The Constant CHART_BY_TOPIC_TEXT. */
	private static final String CHART_BY_TOPIC_TEXT = "Chart by topic";

	/** The Constant COMMAND_ALL_COMMITTEES_BY_HEADCOUNT. */
	private static final PageModeMenuCommand COMMAND_ALL_COMMITTEES_BY_HEADCOUNT = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.CHARTS,ChartIndicators.ALLCOMMITTEESBYHEADCOUNT.toString());

	/** The Constant COMMAND_COMMITTEES_BY_PARTY. */
	private static final PageModeMenuCommand COMMAND_COMMITTEES_BY_PARTY = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.CHARTS,ChartIndicators.COMMITTEESBYPARTY.toString());

	/** The Constant COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT. */
	private static final PageModeMenuCommand COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.CHARTS,ChartIndicators.CURRENTCOMMITTEESBYHEADCOUNT.toString());


	/** The Constant COMMAND_CURRENT_COMMITTEES_BY_PARTY_HEADCOUNT. */
	private static final PageModeMenuCommand COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.CHARTS,ChartIndicators.CURRENTCOMMITTEESBYPARTYDAYSSERVED.toString());

	/** The Constant COMMAND_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	/** The Constant COMMAND_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_PAGEVISIT_HISTORY. */
	private static final PageModeMenuCommand COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.PAGEVISITHISTORY);

	/** The Constant COMMITTEE_RANKING_TEXT. */
	private static final String COMMITTEE_RANKING_TEXT = "Committee Ranking";

	/** The Constant CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS. */
	private static final String CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS = "Current and past member, polticial days";

	/** The Constant CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT = "Current committees, current members";

	/**
	 * The Constant CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS.
	 */
	private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS = "Current parties in committees";

	/**
	 * The Constant
	 * CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES.
	 */
	private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES = "Current parties,days served";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The Constant POLITICAL_WORK_SUMMARY_TEXT. */
	private static final String POLITICAL_WORK_SUMMARY_TEXT = "Political Work Summary";

	/** The Constant RANKING_LIST_BY_TOPIC_TEXT. */
	private static final String RANKING_LIST_BY_TOPIC_TEXT = "Ranking list by topic";

	/** The Constant POLITICAL_WORK_SUMMARY_DESCRIPTION. */
	private static final String POLITICAL_WORK_SUMMARY_DESCRIPTION = "Scoreboard over current member size, political days served and total assignments";

	/** The Constant CURRENT_COMMITTEES_CURRENT_MEMBERS_DESCRIPTION. */
	private static final String CURRENT_COMMITTEES_CURRENT_MEMBERS_DESCRIPTION = "Chart over current committees and member size";

	/** The Constant CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS_DESCRIPTION. */
	private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS_DESCRIPTION = "Chart over current parties active in committees and member size";

	/** The Constant CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES_DESCRIPTION. */
	private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES_DESCRIPTION = "Chart over current parties active in committees days served";

	/** The Constant ALL_COMMITTEES_TOTAL_MEMBERS_DESCRIPTION. */
	private static final String ALL_COMMITTEES_TOTAL_MEMBERS_DESCRIPTION = "Chart over all committees and member size";

	/** The Constant PAGE_VISIT_HISTORY_DESCRIPTION. */
	private static final String PAGE_VISIT_HISTORY_DESCRIPTION = "View history of page visit for this page.";

	/** The application menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;

	/**
	 * Instantiates a new committee ranking menu item factory impl.
	 */
	public CommitteeRankingMenuItemFactoryImpl() {
		super();
	}

	/**
	 * Creates the committeee ranking menu bar.
	 *
	 * @param menuBar
	 *            the menu bar
	 */
	@Override
	public void createCommitteeeRankingMenuBar(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		createCommitteeRankingTopics(menuBar.addItem(COMMITTEE_RANKING_TEXT, VaadinIcons.GROUP, null));
	}

	/**
	 * Creates the committee ranking topics.
	 *
	 * @param committeeMenuItem
	 *            the committee menu item
	 */
	@Override
	public void createCommitteeRankingTopics(final MenuItem committeeMenuItem) {
		committeeMenuItem.addItem(OVERVIEW_TEXT, VaadinIcons.GROUP, COMMAND_OVERVIEW);

		final MenuItem listByTopic = committeeMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, VaadinIcons.GROUP, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_WORK_SUMMARY_TEXT,VaadinIcons.GROUP, COMMAND_DATAGRID);
		listItem.setDescription(CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS);

		final MenuItem chartByTopic = committeeMenuItem.addItem(CHART_BY_TOPIC_TEXT, VaadinIcons.GROUP, null);


		chartByTopic.addItem(CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT,VaadinIcons.GROUP, COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT);
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS,VaadinIcons.GROUP, COMMAND_COMMITTEES_BY_PARTY);
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES,VaadinIcons.GROUP, COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED);

		chartByTopic.addItem(ALL_COMMITTEES_TOTAL_MEMBERS,VaadinIcons.GROUP, COMMAND_ALL_COMMITTEES_BY_HEADCOUNT);

		committeeMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.GROUP, COMMAND_PAGEVISIT_HISTORY);

	}

	/**
	 * Creates the overview page.
	 *
	 * @param panelContent
	 *            the panel content
	 */
	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);


		createButtonLink(grid,POLITICAL_WORK_SUMMARY_TEXT,VaadinIcons.GROUP, COMMAND_DATAGRID, POLITICAL_WORK_SUMMARY_DESCRIPTION);
		createButtonLink(grid,CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT,VaadinIcons.GROUP, COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT, CURRENT_COMMITTEES_CURRENT_MEMBERS_DESCRIPTION);

		createButtonLink(grid,CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS,VaadinIcons.GROUP, COMMAND_COMMITTEES_BY_PARTY, CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS_DESCRIPTION);
		createButtonLink(grid,CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES,VaadinIcons.GROUP, COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED, CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES_DESCRIPTION);
		createButtonLink(grid,ALL_COMMITTEES_TOTAL_MEMBERS,VaadinIcons.GROUP, COMMAND_ALL_COMMITTEES_BY_HEADCOUNT, ALL_COMMITTEES_TOTAL_MEMBERS_DESCRIPTION);

		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, VaadinIcons.GROUP, COMMAND_PAGEVISIT_HISTORY, PAGE_VISIT_HISTORY_DESCRIPTION);

	}


}
