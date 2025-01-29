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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PoliticianRankingMenuItemFactoryImpl.
 *
 * <p>
 * Builds menus and overviews for politician rankings, enabling analysis of
 * individual politicians' influence, roles, and longevity in various political
 * arenas such as parliament, committees, and parties. Emphasizes political
 * context, strategic influence, and institutional footprints rather than
 * technical details.
 * </p>
 */
@Service
public final class PoliticianRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
        implements PoliticianRankingMenuItemFactory {

    /** The Constant ALL_PARTIES. */
    // Label constants
    private static final String ALL_PARTIES = "All parties";

    /** The Constant CHART_BY_TOPIC_TEXT. */
    private static final String CHART_BY_TOPIC_TEXT = "Chart by topic";

    /** The Constant CURRENT_PARTIES. */
    private static final String CURRENT_PARTIES = "Current parties";

    /** The Constant OVERVIEW_TEXT. */
    private static final String OVERVIEW_TEXT = "Overview";

    /** The Constant PAGE_VISIT_HISTORY_TEXT. */
    private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

    /** The Constant POLITICAL_EXPERIENCE_SUMMARY. */
    private static final String POLITICAL_EXPERIENCE_SUMMARY = "Political Experience Summary";

    /** The Constant POLITICIAN_RANKING. */
    private static final String POLITICIAN_RANKING = "Politician Ranking";

    /** The Constant CURRENT_AND_PAST_ASSIGNMENTS_DESCRIPTION. */
    // Description for total experience detail (tooltip)
    private static final String CURRENT_AND_PAST_ASSIGNMENTS_DESCRIPTION =
            "Summarized roles, responsibilities, and tenure influence";

    /** The Constant DESC_POLITICAL_EXPERIENCE. */
    // Politically focused descriptions (~50 chars)
    private static final String DESC_POLITICAL_EXPERIENCE = "Experience metrics: roles shaping political influence.";

    /** The Constant DESC_ALL_PARTIES. */
    private static final String DESC_ALL_PARTIES = "All parties: mapping legislative engagements.";

    /** The Constant DESC_CURRENT_PARTIES. */
    private static final String DESC_CURRENT_PARTIES = "Current parties: active parliamentary presence.";

    /** The Constant DESC_PAGE_HISTORY. */
    private static final String DESC_PAGE_HISTORY = "Visit history: tracing engagement over time.";



    /** The application menu item factory. */
    @Autowired
    private ApplicationMenuItemFactory applicationMenuItemFactory;

    /**
     * Instantiates a new politician ranking menu item factory implementation.
     */
    public PoliticianRankingMenuItemFactoryImpl() {
        super();
    }

    /**
     * Creates the overview page with concise, politically focused descriptions.
     * Each button link offers a ~50 char description emphasizing political dynamics.
     *
     * @param panelContent the vertical layout to hold the overview content
     */
    @Override
    public void createOverviewPage(final VerticalLayout panelContent) {
        final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

        // Politician experience summary: USER_CLOCK to indicate roles over time
        createButtonLink(grid, POLITICAL_EXPERIENCE_SUMMARY, VaadinIcons.USER_CLOCK,
        		COMMAND_POLITICIAN_RANKING_DATAGRID, DESC_POLITICAL_EXPERIENCE);

        // All parties: analyzing roles, use GROUP icon for multiple parties
        createButtonLink(grid, ALL_PARTIES, VaadinIcons.GROUP,
        		POL_RANK_COMMAND_CHARTS_ALL_PARTIES_BY_HEADCOUNT, DESC_ALL_PARTIES);

        // Current parties: similarly GROUP icon for active parties
        createButtonLink(grid, CURRENT_PARTIES, VaadinIcons.GROUP,
        		POLITICIAN_RANKING_COMMAND_CURRENT_PARTIES, DESC_CURRENT_PARTIES);

        // Page visit history: use HISTORY icon for historical data
        createButtonLink(grid, PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART,
        		POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY, DESC_PAGE_HISTORY);
    }

    /**
     * Creates the politician ranking menu bar and integrates it into the main
     * application menu. Ensures logical grouping and context-appropriate icons.
     *
     * @param menuBar the main menu bar to which the politician ranking items are added
     */
    @Override
    public void createPoliticianRankingMenuBar(final MenuBar menuBar) {
        initApplicationMenuBar(menuBar);
        applicationMenuItemFactory.addRankingMenu(menuBar);

        // Politician Ranking: USER icon for individual-centric analysis
        createPoliticianRankingTopics(menuBar.addItem(POLITICIAN_RANKING, VaadinIcons.USER, null));
    }

    /**
     * Adds politician ranking topics to the specified menu item. Uses icons to convey
     * context—e.g., LIST for lists, CHART for data visualization, GROUP for parties,
     * HISTORY for historical data.
     *
     * @param politicianMenuItem the parent menu item for politician ranking topics
     */
    @Override
    public void createPoliticianRankingTopics(final MenuItem politicianMenuItem) {
        // Overview: DASHBOARD for a general overview panel
        politicianMenuItem.addItem(OVERVIEW_TEXT, VaadinIcons.DASHBOARD, COMMAND_POLITICIAN_RANKING_OVERVIEW);

        // Political experience summary: USER_CLOCK indicating experience over time
        final MenuItem listItem = politicianMenuItem.addItem(POLITICAL_EXPERIENCE_SUMMARY, VaadinIcons.USER_CLOCK, COMMAND_POLITICIAN_RANKING_DATAGRID);
        listItem.setDescription(CURRENT_AND_PAST_ASSIGNMENTS_DESCRIPTION);

        // Chart by topic: CHART icon for visual data representation
        final MenuItem chartByTopic = politicianMenuItem.addItem(CHART_BY_TOPIC_TEXT, VaadinIcons.CHART, null);

        chartByTopic.addItem(ALL_PARTIES, VaadinIcons.GROUP, POL_RANK_COMMAND_CHARTS_ALL_PARTIES_BY_HEADCOUNT);
        chartByTopic.addItem(CURRENT_PARTIES, VaadinIcons.GROUP, POLITICIAN_RANKING_COMMAND_CURRENT_PARTIES);

        politicianMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART, POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }
}
