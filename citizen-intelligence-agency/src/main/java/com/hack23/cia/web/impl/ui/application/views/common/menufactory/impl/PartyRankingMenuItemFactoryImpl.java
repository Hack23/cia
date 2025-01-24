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
 *  $Id$
 *  $HeadURL$
 */
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
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
public final class PartyRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
                implements PartyRankingMenuItemFactory {

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

                createButtonLink(grid, PART_LEADERS_SCOREBOARD, VaadinIcons.TROPHY,
                                COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD, DESC_LEADERS_SCOREBOARD);

                createButtonLink(grid, TOTAL_MEMBERS, VaadinIcons.USERS,
                                COMMAND_DATAGRID, DESC_ALL_PARTIES_ROLES);

                createButtonLink(grid, CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT, VaadinIcons.INSTITUTION,
                                COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES, DESC_GOVERNMENT_HEADCOUNT);

                createButtonLink(grid, CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT, VaadinIcons.GROUP,
                                COMMAND_CHARTS_CURRENT_COMMITTEES, DESC_COMMITTEES_HEADCOUNT);

                createButtonLink(grid, CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT, VaadinIcons.INSTITUTION,
                                COMMAND_CHARTS_CURRENT_PARTIES, DESC_PARLIAMENT_HEADCOUNT);

                createButtonLink(grid, ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT, VaadinIcons.CLOCK,
                                COMMAND_CHARTS_ALL_PARTIES, DESC_DAYS_SERVED_PARLIAMENT);

                createButtonLink(grid, PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART,
                                PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY);
        }

        /**
         * Creates the party ranking menu bar and integrates it into the main
         * application
         * menu. Ensures consistent icon usage and logical grouping of party ranking
         * topics.
         *
         * @param menuBar the main menu bar to which the party ranking items will be
         *                added
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
         * @param partynMenuItem the parent menu item to which party ranking topics are
         *                       added
         */
        @Override
        public void createPartyRankingTopics(final MenuItem partynMenuItem) {
                partynMenuItem.addItem(OVERVIEW_TEXT, VaadinIcons.DASHBOARD, COMMAND_OVERVIEW);

                partynMenuItem.addItem(PART_LEADERS_SCOREBOARD, VaadinIcons.TROPHY,
                                COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD);

                // Total members using USERS icon for multiple people
                final MenuItem listItem = partynMenuItem.addItem(TOTAL_MEMBERS, VaadinIcons.USERS, COMMAND_DATAGRID);
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

                partynMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART, 
                                PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY);
        }
}
