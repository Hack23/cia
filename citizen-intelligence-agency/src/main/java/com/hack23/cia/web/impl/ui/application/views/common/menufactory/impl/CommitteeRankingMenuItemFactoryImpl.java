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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeRankingMenuItemFactory;
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
 * The Class CommitteeRankingMenuItemFactoryImpl.
 */
@Service
public final class CommitteeRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements CommitteeRankingMenuItemFactory {

	/** The Constant COMMAND_PAGEVISIT_HISTORY. */
	private static final PageModeMenuCommand COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.PAGEVISITHISTORY);

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
	private static final PageModeMenuCommand COMMAND_CURRENT_COMMITTEES_BY_PARTY_HEADCOUNT = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.CHARTS,ChartIndicators.CURRENTCOMMITTEESBYPARTYHEADCOUNT.toString());


	/** The Constant COMMAND_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	/** The Constant ALL_COMMITTEES_TOTAL_MEMBERS. */
	private static final String ALL_COMMITTEES_TOTAL_MEMBERS = "All committees, total members";

	/**
	 * The Constant
	 * CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES.
	 */
	private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES = "Current parties active in committees, total days served in committees";

	/**
	 * The Constant CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS.
	 */
	private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS = "Current parties active in committees, current assignments";

	/** The Constant CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS. */
	private static final String CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS = "Current and past member and summary of polticial days ";

	/** The Constant CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT = "Current committees, current members";

	/** The Constant POLITICAL_WORK_SUMMARY_TEXT. */
	private static final String POLITICAL_WORK_SUMMARY_TEXT = "Political Work Summary";

	/** The Constant COMMITTEE_RANKING_TEXT. */
	private static final String COMMITTEE_RANKING_TEXT = "Committee Ranking";

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

		createCommitteeRankingTopics(menuBar.addItem(COMMITTEE_RANKING_TEXT, null, null));
	}

	/**
	 * Creates the committee ranking topics.
	 *
	 * @param committeeMenuItem
	 *            the committee menu item
	 */
	@Override
	public void createCommitteeRankingTopics(final MenuItem committeeMenuItem) {
		committeeMenuItem.addItem(OVERVIEW_TEXT, FontAwesome.GROUP, COMMAND_OVERVIEW);

		final MenuItem listByTopic = committeeMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, FontAwesome.GROUP, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_WORK_SUMMARY_TEXT,FontAwesome.GROUP, COMMAND_DATAGRID);
		listItem.setDescription(CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS);

		final MenuItem chartByTopic = committeeMenuItem.addItem(CHART_BY_TOPIC_TEXT, FontAwesome.GROUP, null);


		chartByTopic.addItem(CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT,FontAwesome.GROUP, COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT);
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS,FontAwesome.GROUP, COMMAND_COMMITTEES_BY_PARTY);
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES,FontAwesome.GROUP, COMMAND_CURRENT_COMMITTEES_BY_PARTY_HEADCOUNT);

		chartByTopic.addItem(ALL_COMMITTEES_TOTAL_MEMBERS,FontAwesome.GROUP, COMMAND_ALL_COMMITTEES_BY_HEADCOUNT);

		committeeMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, FontAwesome.GROUP, COMMAND_PAGEVISIT_HISTORY);

	}

	/**
	 * Creates the overview page.
	 *
	 * @param panelContent
	 *            the panel content
	 */
	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final ResponsiveRow grid = createGridLayout(panelContent);


		createButtonLink(grid,POLITICAL_WORK_SUMMARY_TEXT,FontAwesome.GROUP, COMMAND_DATAGRID, "Default description");
		createButtonLink(grid,CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT,FontAwesome.GROUP, COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT, "Default description");

		createButtonLink(grid,CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS,FontAwesome.GROUP, COMMAND_COMMITTEES_BY_PARTY, "Default description");
		createButtonLink(grid,CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES,FontAwesome.GROUP, COMMAND_CURRENT_COMMITTEES_BY_PARTY_HEADCOUNT, "Default description");
		createButtonLink(grid,ALL_COMMITTEES_TOTAL_MEMBERS,FontAwesome.GROUP, COMMAND_ALL_COMMITTEES_BY_HEADCOUNT, "Default description");

		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, FontAwesome.GROUP, COMMAND_PAGEVISIT_HISTORY, "View history of page visit for this page.");

	}


}
