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
 *  $Id$
 *  $HeadURL$
 */
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyRankingMenuItemFactory;
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
 * The Class PartyRankingMenuItemFactoryImpl.
 *
 * <p>
 * Responsible for creating and organizing party ranking menu items, charts, and
 * overview pages. Enhances user navigation to analyze party influence, roles,
 * and performance within EU, government, committees, and parliament. Leverages
 * VaadinIcons to visually communicate context and concise descriptions focusing
 * on political significance.
 * </p>
 */
@Service
public final class PartyRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements PartyRankingMenuItemFactory {

    // Label constants
    private static final String PART_LEADERS_SCOREBOARD = "Party leaders scoreboard";
    private static final String ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT = "All parties total days served in parliament";
    private static final String CHART_BY_TOPIC_TEXT = "Chart by topic";
    private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT = "Current parties in committees";
    private static final String CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT = "Current parties in government";
    private static final String CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT = "Current parties in parliament";
    private static final String OVERVIEW_TEXT = "Overview";
    private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";
    private static final String PARTY_BY_TOTAL_MEMBERS_BASED_ON_ROLES_IN_DEPARTMENTS_COMMITTEES_AND_PARLIAMENT =
            "Party by total members across EU/gov/committees/parliament";
    private static final String PARTY_RANKING = "Party Ranking";
    private static final String RANKING_LIST_BY_TOPIC_TEXT = "Ranking list by topic";
    private static final String TOTAL_MEMBERS = "Total members";

    // Political analyst perspective descriptions (~50 chars)
    private static final String DESC_ALL_PARTIES_ROLES = "All parties: influence across key institutions.";
    private static final String DESC_GOVERNMENT_HEADCOUNT = "Governing parties: evaluating institutional strength.";
    private static final String DESC_COMMITTEES_HEADCOUNT = "Committees: parties' agenda-setting influence.";
    private static final String DESC_PARLIAMENT_HEADCOUNT = "Parliament: mapping party legislative leverage.";
    private static final String DESC_LEADERS_SCOREBOARD = "Leaders: comparing party leadership impact.";
    private static final String DESC_DAYS_SERVED_PARLIAMENT = "All parties: experience shaping policy timelines.";
    private static final String DESC_PAGE_VISIT_HISTORY = "Visit history: tracking public engagement patterns.";

    // Page mode commands
    private static final PageModeMenuCommand COMMAND_CHARTS_ALL_PARTIES = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.ALLPARTIES.toString());
    private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_COMMITTEES = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTCOMMITTEES.toString());
    private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTGOVERMENTPARTIES.toString());
    private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTIES.toString());
    private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTYLEADERSCORECARD.toString());
    private static final PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);
    private static final PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW);
    private static final PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    /** Application menu item factory for integrating ranking menus. */
    @Autowired
    private ApplicationMenuItemFactory applicationMenuItemFactory;

    /**
     * Instantiates a new party ranking menu item factory implementation.
     */
    public PartyRankingMenuItemFactoryImpl() {
        super();
    }

    /**
     * Creates the overview page with concise, politically focused descriptions.
     * Each button link is accompanied by ~50 character descriptions highlighting
     * political context rather than technical details.
     *
     * @param panelContent the vertical layout to hold the overview content
     */
    @Override
    public void createOverviewPage(final VerticalLayout panelContent) {
        final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

        createButtonLink(grid, TOTAL_MEMBERS, VaadinIcons.USERS,
                COMMAND_DATAGRID, DESC_ALL_PARTIES_ROLES);

        createButtonLink(grid, CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT, VaadinIcons.INSTITUTION,
                COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES, DESC_GOVERNMENT_HEADCOUNT);

        createButtonLink(grid, CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT, VaadinIcons.GROUP,
                COMMAND_CHARTS_CURRENT_COMMITTEES, DESC_COMMITTEES_HEADCOUNT);

        createButtonLink(grid, CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT, VaadinIcons.INSTITUTION,
                COMMAND_CHARTS_CURRENT_PARTIES, DESC_PARLIAMENT_HEADCOUNT);

        createButtonLink(grid, PART_LEADERS_SCOREBOARD, VaadinIcons.TROPHY,
                COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD, DESC_LEADERS_SCOREBOARD);

        createButtonLink(grid, ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT, VaadinIcons.CLOCK,
                COMMAND_CHARTS_ALL_PARTIES, DESC_DAYS_SERVED_PARLIAMENT);

        createButtonLink(grid, PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART,
                COMMAND_PAGEVISITHISTORY, DESC_PAGE_VISIT_HISTORY);
    }

    /**
     * Creates the party ranking menu bar and integrates it into the main application
     * menu. Ensures consistent icon usage and logical grouping of party ranking
     * topics.
     *
     * @param menuBar the main menu bar to which the party ranking items will be added
     */
    @Override
    public void createPartyRankingMenuBar(final MenuBar menuBar) {
        initApplicationMenuBar(menuBar);
        applicationMenuItemFactory.addRankingMenu(menuBar);

        // Suitable icon (e.g., LINE_CHART) for ranking visualization
        createPartyRankingTopics(menuBar.addItem(PARTY_RANKING, VaadinIcons.LINE_CHART, null));
    }

    /**
     * Adds party ranking topics to the specified menu item. Uses icons that reflect
     * the content context, such as charts for data visualization, groups or
     * institutions for organizational structures, and history for past activity.
     *
     * @param partynMenuItem the parent menu item to which party ranking topics are added
     */
    @Override
    public void createPartyRankingTopics(final MenuItem partynMenuItem) {
        partynMenuItem.addItem(OVERVIEW_TEXT, VaadinIcons.DASHBOARD, COMMAND_OVERVIEW);

        final MenuItem listByTopic = partynMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, VaadinIcons.LIST, null);

        // Total members using USERS icon for multiple people
        final MenuItem listItem = listByTopic.addItem(TOTAL_MEMBERS, VaadinIcons.USERS, COMMAND_DATAGRID);
        listItem.setDescription(PARTY_BY_TOTAL_MEMBERS_BASED_ON_ROLES_IN_DEPARTMENTS_COMMITTEES_AND_PARLIAMENT);

        final MenuItem chartByTopic = partynMenuItem.addItem(CHART_BY_TOPIC_TEXT, VaadinIcons.CHART, null);

        chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT, VaadinIcons.INSTITUTION,
                COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES);

        chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT, VaadinIcons.GROUP,
                COMMAND_CHARTS_CURRENT_COMMITTEES);

        chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT, VaadinIcons.INSTITUTION,
                COMMAND_CHARTS_CURRENT_PARTIES);

        chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT, VaadinIcons.CLOCK,
                COMMAND_CHARTS_ALL_PARTIES);

        partynMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART, COMMAND_PAGEVISITHISTORY);
    }
}
