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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PartyRankingMenuItemFactoryImpl.
 */
@Service
public final class PartyRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements PartyRankingMenuItemFactory {

	/** The Constant COMMAND_PAGEVISITHISTORY. */
	private static final PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAND_CHARTS_ALL_PARTIES. */
	private static final PageModeMenuCommand COMMAND_CHARTS_ALL_PARTIES = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.ALLPARTIES.toString());

	/** The Constant COMMAND_CHARTS_CURRENT_COMMITTEES. */
	private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_COMMITTEES = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTCOMMITTEES.toString());

	/** The Constant COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES. */
	private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTGOVERMENTPARTIES.toString());

	/** The Constant COMMAND_CHARTS_CURRENT_PARTIES. */
	private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTPARTIES.toString());

	/** The Constant COMMAND_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);

	/** The Constant COMMAND_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The Constant PARTY_RANKING. */
	private static final String PARTY_RANKING = "Party Ranking";

	/** The Constant ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT. */
	private static final String ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT = "All parties, total days served in parliament";

	/** The Constant CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT. */
	private static final String CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT = "Current parties active in parliament, head count";

	/**
	 * The Constant
	 * PARTY_BY_TOTAL_MEMBERS_BASED_ON_ROLES_IN_DEPARTMENTS_COMMITTEES_AND_PARLIAMENT.
	 */
	private static final String PARTY_BY_TOTAL_MEMBERS_BASED_ON_ROLES_IN_DEPARTMENTS_COMMITTEES_AND_PARLIAMENT = "Party by total members, based on roles in departments, committees and parliament";

	/** The Constant TOTAL_MEMBERS. */
	private static final String TOTAL_MEMBERS = "Total members";

	/** The Constant CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT. */
	private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT = "Current parties active in committees, head count";

	/** The Constant CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT. */
	private static final String CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT = "Current parties active in ministries, head count";

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
	 * Instantiates a new party ranking menu item factory impl.
	 */
	public PartyRankingMenuItemFactoryImpl() {
		super();
	}


	@Override
	public void createPartyRankingMenuBar(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		createPartyRankingTopics(menuBar.addItem(PARTY_RANKING, FontAwesome.GROUP,null));

	}


	@Override
	public void createPartyRankingTopics(final MenuItem partynMenuItem) {

		partynMenuItem.addItem(OVERVIEW_TEXT, FontAwesome.GROUP,
				COMMAND_OVERVIEW);

		final MenuItem listByTopic = partynMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, FontAwesome.GROUP, null);

		final MenuItem listItem = listByTopic.addItem(TOTAL_MEMBERS,FontAwesome.GROUP,
				COMMAND_DATAGRID);
		listItem.setDescription(PARTY_BY_TOTAL_MEMBERS_BASED_ON_ROLES_IN_DEPARTMENTS_COMMITTEES_AND_PARLIAMENT);

		final MenuItem chartByTopic = partynMenuItem.addItem(CHART_BY_TOPIC_TEXT, FontAwesome.GROUP, null);


		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT,FontAwesome.GROUP,
				COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES);

		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT,FontAwesome.GROUP,
				COMMAND_CHARTS_CURRENT_COMMITTEES);

		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT,FontAwesome.GROUP,
				COMMAND_CHARTS_CURRENT_PARTIES);
		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT,FontAwesome.GROUP,
				COMMAND_CHARTS_ALL_PARTIES);

		partynMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, FontAwesome.GROUP,
				COMMAND_PAGEVISITHISTORY);
	}


	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final GridLayout grid = createGridLayout(panelContent);

		createButtonLink(grid,TOTAL_MEMBERS,FontAwesome.GROUP,
				COMMAND_DATAGRID, "Default description");
		createButtonLink(grid,CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT,FontAwesome.GROUP,
				COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES, "Default description");

		createButtonLink(grid,CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT,FontAwesome.GROUP,
				COMMAND_CHARTS_CURRENT_COMMITTEES, "Default description");
		createButtonLink(grid,CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT,FontAwesome.GROUP,
				COMMAND_CHARTS_CURRENT_PARTIES, "Default description");
		createButtonLink(grid,ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT,FontAwesome.GROUP,
				COMMAND_CHARTS_ALL_PARTIES, "Default description");

		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, FontAwesome.GROUP,
				COMMAND_PAGEVISITHISTORY, "Default description");

	}


}
