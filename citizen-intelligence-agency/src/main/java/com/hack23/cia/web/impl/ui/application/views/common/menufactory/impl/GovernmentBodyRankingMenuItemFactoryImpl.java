/*
 * Copyright 2010-2025 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.GovernmentBodyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandGovernmentBodyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandGovernmentBodyRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentBodyRankingMenuItemFactoryImpl.
 *
 * <p>
 * This class builds menus and overview pages for government body rankings,
 * enabling users to navigate through institutional staffing, revenue streams,
 * expenditure focus, and historical engagement patterns. Through integration
 * with {@link ApplicationMenuItemFactory}, it ensures a coherent experience
 * within the main menu structure.
 * </p>
 */
@Service
public final class GovernmentBodyRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
                implements GovernmentBodyRankingMenuItemFactory {

        /** The application menu item factory, used to integrate into the main menu. */
        @Autowired
        private ApplicationMenuItemFactory applicationMenuItemFactory;

        /**
         * Instantiates a new government body ranking menu item factory implementation.
         */
        public GovernmentBodyRankingMenuItemFactoryImpl() {
                super();
        }

        /**
         * Creates the government body ranking menu bar and integrates it into the main
         * application menu. Adds government body ranking topics as sub-items.
         *
         * @param menuBar the main application {@link MenuBar} to which the government
         *                body ranking menu will be added.
         */
        @Override
        public void createGovernmentBodyRankingMenuBar(final MenuBar menuBar) {
                initApplicationMenuBar(menuBar);
                applicationMenuItemFactory.addRankingMenu(menuBar);
                createGovernmentBodyRankingTopics(
                                menuBar.addItem(GOVERNMENT_BODY_RANKING, VaadinIcons.BUILDING_O, null));
        }

        /**
         * Adds menu items for various government body ranking topics:
         * <ul>
         * <li>Overview: General dashboard icon</li>
         * <li>Government Bodies: Building icon for institutions</li>
         * <li>Headcount: Multiple users for staffing</li>
         * <li>Income: Money deposit icon for revenues</li>
         * <li>Expenditure: Money withdraw icon for spending</li>
         * <li>Page Visit History: History icon for past engagement</li>
         * </ul>
         *
         * @param menuItem the {@link MenuBar.MenuItem} representing "GovernmentBody
         *                 Ranking"
         *                 under which these topics will be added.
         */
        @Override
        public void createGovernmentBodyRankingTopics(final MenuBar.MenuItem menuItem) {
                menuItem.addItem(OVERVIEW_TEXT, VaadinIcons.DASHBOARD, GOVERNMENT_BODY_COMMAN_OVERVIEW);
                menuItem.addItem(GOVERNMENT_BODIES, VaadinIcons.BUILDING_O, PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID);
                menuItem.addItem(GOVERNMENT_BODIES_HEADCOUNT, VaadinIcons.USER_CHECK,
                		PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODIES_HEADCOUNT);
                menuItem.addItem(GOVERNMENT_BODIES_INCOME, VaadinIcons.MONEY, PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_INCOME);
                menuItem.addItem(GOVERNMENT_BODIES_EXPENDITURE, VaadinIcons.MONEY_WITHDRAW,
                		PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_EXPENDITURE);

                menuItem.addItem(HEADCOUNT, VaadinIcons.USERS, PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_HEADCOUNT);
                menuItem.addItem(INCOME, VaadinIcons.MONEY_DEPOSIT, PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_INCOME);
                menuItem.addItem(EXPENDITURE, VaadinIcons.MONEY_WITHDRAW, PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_EXPENDITURE);
                menuItem.addItem(RANKING_PAGE_VISIT_TEXT, VaadinIcons.CHART,
                                GOVERNMENT_BODY_COMMAND_PAGEVISIT_HISTORY);
        }

        /**
         * Creates the overview page with quick-access links to government body ranking
         * topics.
         * Each button uses an icon and a brief description to guide the user:
         * <ul>
         * <li>Government Bodies: Building icon, structural roles</li>
         * <li>Headcount: Users icon, staffing influence</li>
         * <li>Income: Money deposit icon, revenue streams</li>
         * <li>Expenditure: Money withdraw icon, spending focus</li>
         * <li>Page Visit History: History icon, past engagement</li>
         * </ul>
         *
         * @param panelContent the {@link VerticalLayout} container that holds the
         *                     overview
         *                     content. New UI components will be added here.
         */
        @Override
        public void createOverviewPage(final VerticalLayout panelContent) {
                final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

                createButtonLink(grid, GOVERNMENT_BODIES, VaadinIcons.BUILDING_O, PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID,
                                GOVERNMENT_BODIES_DESCRIPTION);
                createButtonLink(grid, GOVERNMENT_BODIES_HEADCOUNT, VaadinIcons.USER_CHECK,
                		PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_HEADCOUNT, GOVERNMENT_BODIES_HEADCOUNT_DESCRIPTION);
                createButtonLink(grid, GOVERNMENT_BODIES_INCOME, VaadinIcons.MONEY, PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_INCOME,
                                GOVERNMENT_BODIES_INCOME_DESCRIPTION);
                createButtonLink(grid, GOVERNMENT_BODIES_EXPENDITURE, VaadinIcons.MONEY_WITHDRAW,
                		PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_EXPENDITURE, GOVERNMENT_BODIES_EXPENDITURE_DESCRIPTION);

                createButtonLink(grid, HEADCOUNT, VaadinIcons.USERS, PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_HEADCOUNT,
                                HEADCOUNT_DESCRIPTION);
                createButtonLink(grid, INCOME, VaadinIcons.MONEY_DEPOSIT, PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_INCOME,
                                INCOME_DESCRIPTION);
                createButtonLink(grid, EXPENDITURE, VaadinIcons.MONEY_WITHDRAW, PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_EXPENDITURE,
                                EXPENDITURE_DESCRIPTION);
                createButtonLink(grid, RANKING_PAGE_VISIT_TEXT, VaadinIcons.CHART,
                                GOVERNMENT_BODY_COMMAND_PAGEVISIT_HISTORY, RANKING_PAGE_VISIT_DESC);
        }
}
