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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.GovernmentBodyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.GovernmentBodyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentBodyRankingMenuItemFactoryImpl.
 * 
 * <p>This class builds menus and overview pages for government body rankings, 
 * enabling users to navigate through institutional staffing, revenue streams, 
 * expenditure focus, and historical engagement patterns. Through integration 
 * with {@link ApplicationMenuItemFactory}, it ensures a coherent experience 
 * within the main menu structure.</p>
 */
@Service
public final class GovernmentBodyRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
        implements GovernmentBodyRankingMenuItemFactory {

    /** Commands for different page modes within the Government Body Ranking view. */
    private static final PageModeMenuCommand COMMAN_OVERVIEW = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
            PageMode.OVERVIEW);
    private static final PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
            PageMode.DATAGRID);
    private static final PageModeMenuCommand COMMAND_EXPENDITURE = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
            GovernmentBodyPageMode.EXPENDITURE.toString());
    private static final PageModeMenuCommand COMMAND_HEADCOUNT = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
            GovernmentBodyPageMode.HEADCOUNT.toString());
    private static final PageModeMenuCommand COMMAND_INCOME = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
            GovernmentBodyPageMode.INCOME.toString());
    private static final PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
            PageMode.PAGEVISITHISTORY);

    /** Menu labels. */
    private static final String EXPENDITURE = "Expenditure";
    private static final String GOVERNMENT_BODIES = "Government bodies";
    private static final String GOVERNMENT_BODY_RANKING = "GovernmentBody Ranking";
    private static final String HEADCOUNT = "Headcount";
    private static final String INCOME = "Income";
    private static final String OVERVIEW_TEXT = "Overview";
    private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

    /** Descriptions ~50 chars. */
    private static final String HEADCOUNT_DESCRIPTION = "Staffing levels reveal institutional influence.";
    private static final String INCOME_DESCRIPTION = "Compare institutions by their revenue streams.";
    private static final String EXPENDITURE_DESCRIPTION = "Assess how bodies allocate and prioritize spending.";
    private static final String PAGE_VISIT_HISTORY_DESCRIPTION = "Explore historical interest, engagement patterns.";
    private static final String CURRENT_GOVERNMENT_BODIES_DESCRIPTION = "All bodies: study structural roles and authority.";

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
        createGovernmentBodyRankingTopics(menuBar.addItem(GOVERNMENT_BODY_RANKING, VaadinIcons.BUILDING_O, null));
    }

    /**
     * Adds menu items for various government body ranking topics:
     * <ul>
     *   <li>Overview: General dashboard icon</li>
     *   <li>Government Bodies: Building icon for institutions</li>
     *   <li>Headcount: Multiple users for staffing</li>
     *   <li>Income: Money deposit icon for revenues</li>
     *   <li>Expenditure: Money withdraw icon for spending</li>
     *   <li>Page Visit History: History icon for past engagement</li>
     * </ul>
     *
     * @param menuItem the {@link MenuBar.MenuItem} representing "GovernmentBody Ranking"
     *                 under which these topics will be added.
     */
    @Override
    public void createGovernmentBodyRankingTopics(final MenuBar.MenuItem menuItem) {
        menuItem.addItem(OVERVIEW_TEXT, VaadinIcons.DASHBOARD, COMMAN_OVERVIEW);
        menuItem.addItem(GOVERNMENT_BODIES, VaadinIcons.BUILDING_O, COMMAND_DATAGRID);
        menuItem.addItem(HEADCOUNT, VaadinIcons.USERS, COMMAND_HEADCOUNT);
        menuItem.addItem(INCOME, VaadinIcons.MONEY_DEPOSIT, COMMAND_INCOME);
        menuItem.addItem(EXPENDITURE, VaadinIcons.MONEY_WITHDRAW, COMMAND_EXPENDITURE);
        menuItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART, COMMAND_PAGEVISITHISTORY);
    }

    /**
     * Creates the overview page with quick-access links to government body ranking topics.
     * Each button uses an icon and a brief description to guide the user:
     * <ul>
     *   <li>Government Bodies: Building icon, structural roles</li>
     *   <li>Headcount: Users icon, staffing influence</li>
     *   <li>Income: Money deposit icon, revenue streams</li>
     *   <li>Expenditure: Money withdraw icon, spending focus</li>
     *   <li>Page Visit History: History icon, past engagement</li>
     * </ul>
     *
     * @param panelContent the {@link VerticalLayout} container that holds the overview
     *                     content. New UI components will be added here.
     */
    @Override
    public void createOverviewPage(final VerticalLayout panelContent) {
        final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

        createButtonLink(grid, GOVERNMENT_BODIES, VaadinIcons.BUILDING_O, COMMAND_DATAGRID, CURRENT_GOVERNMENT_BODIES_DESCRIPTION);
        createButtonLink(grid, HEADCOUNT, VaadinIcons.USERS, COMMAND_HEADCOUNT, HEADCOUNT_DESCRIPTION);
        createButtonLink(grid, INCOME, VaadinIcons.MONEY_DEPOSIT, COMMAND_INCOME, INCOME_DESCRIPTION);
        createButtonLink(grid, EXPENDITURE, VaadinIcons.MONEY_WITHDRAW, COMMAND_EXPENDITURE, EXPENDITURE_DESCRIPTION);
        createButtonLink(grid, PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART, COMMAND_PAGEVISITHISTORY, PAGE_VISIT_HISTORY_DESCRIPTION);
    }
}
