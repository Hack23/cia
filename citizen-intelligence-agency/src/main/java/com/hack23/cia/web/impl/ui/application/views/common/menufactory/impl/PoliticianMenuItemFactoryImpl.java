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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PoliticianMenuItemFactoryImpl.
 *
 * <p>
 * This class builds menus and overview pages for politician-related views,
 * enabling users to navigate through indicators, roles, document activities,
 * vote histories, and more. It integrates with
 * {@link ApplicationMenuItemFactory}
 * and {@link PoliticianRankingMenuItemFactory} to ensure consistent menu
 * structures within the application.
 * </p>
 */
@Service
public final class PoliticianMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
                implements PoliticianMenuItemFactory {

        /** Menu labels. */
        private static final String BALLOT_DECISION_SUMMARY_TEXT = "Ballot Decision Summary";
        private static final String BALLOTS_TEXT = "Ballots";
        private static final String DOCUMENT_ACTIVITY_TEXT = "Document Activity";
        private static final String DOCUMENT_HISTORY_TEXT = "Document History";
        private static final String DOCUMENTS_TEXT = "Documents";
        private static final String INDICATORS_TEXT = "Indicators";
        private static final String OVERVIEW_TEXT = "Overview";
        private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";
        private static final String POLITICIAN_RANKING = "Politician Ranking";
        private static final String ROLE_GHANT_TEXT = "Role Gantt";
        private static final String ROLE_LIST = "Role List";
        private static final String ROLES_TEXT = "Roles";
        private static final String TOTAL_EXPERIENCE = "Total Experience";
        private static final String VOTE_HISTORY = "Vote History";

        /** Descriptions (~50 characters). */
        private static final String INDICATORS_DESCRIPTION = "Key performance indicators.";
        private static final String TOTAL_EXPERIENCE_DESCRIPTION = "Summary of political experience.";
        private static final String ROLE_LIST_DESCRIPTION = "Detailed list of roles.";
        private static final String ROLE_GHANT_DESCRIPTION = "Gantt chart of roles.";
        private static final String DOCUMENT_ACTIVITY_DESCRIPTION = "Activity by document type.";
        private static final String DOCUMENT_HISTORY_DESCRIPTION = "History of document access.";
        private static final String VOTE_HISTORY_DESCRIPTION = "Summary of voting records.";
        private static final String BALLOT_DECISION_SUMMARY_DESCRIPTION = "Overview of ballot decisions.";
        private static final String PAGE_VISIT_HISTORY_DESCRIPTION = "History of page visits.";

        /** The application menu item factory. */
        @Autowired
        private ApplicationMenuItemFactory applicationMenuItemFactory;

        /** The politician ranking menu item factory. */
        @Autowired
        private PoliticianRankingMenuItemFactory politicianRankingMenuItemFactory;

        /**
         * Instantiates a new politician menu item factory impl.
         */
        public PoliticianMenuItemFactoryImpl() {
                super();
        }

        /**
         * Creates the overview page with quick-access links to various
         * politician-related
         * views such as indicators, roles, document activities, and vote histories.
         *
         * @param panelContent the {@link VerticalLayout} container that holds the
         *                     overview
         *                     content. New UI components (buttons/links) will be added
         *                     here.
         * @param pageId       the identifier for the current page context.
         */
        @Override
        public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
                final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

                createButtonLink(grid, INDICATORS_TEXT, VaadinIcons.CHART,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.INDICATORS, pageId),
                                INDICATORS_DESCRIPTION);

                createButtonLink(grid, TOTAL_EXPERIENCE, VaadinIcons.USER_CLOCK,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.ROLESUMMARY.toString(), pageId),
                                TOTAL_EXPERIENCE_DESCRIPTION);

                createButtonLink(grid, ROLE_LIST, VaadinIcons.LIST,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.ROLELIST.toString(), pageId),
                                ROLE_LIST_DESCRIPTION);

                createButtonLink(grid, ROLE_GHANT_TEXT, VaadinIcons.USER_CLOCK,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.ROLEGHANT.toString(), pageId),
                                ROLE_GHANT_DESCRIPTION);

                createButtonLink(grid, DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE_TEXT,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.DOCUMENTACTIVITY.toString(), pageId),
                                DOCUMENT_ACTIVITY_DESCRIPTION);

                createButtonLink(grid, DOCUMENT_HISTORY_TEXT, VaadinIcons.CHART,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.DOCUMENTHISTORY.toString(), pageId),
                                DOCUMENT_HISTORY_DESCRIPTION);

                createButtonLink(grid, VOTE_HISTORY, VaadinIcons.CHECK_CIRCLE,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.VOTEHISTORY.toString(), pageId),
                                VOTE_HISTORY_DESCRIPTION);

                createButtonLink(grid, BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.CHECK,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.BALLOTDECISIONSUMMARY.toString(), pageId),
                                BALLOT_DECISION_SUMMARY_DESCRIPTION);

                createButtonLink(grid, PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.PAGEVISITHISTORY,
                                                pageId),
                                PAGE_VISIT_HISTORY_DESCRIPTION);
        }

        /**
         * Creates the politician menu bar and integrates it into the main application
         * menu.
         * Adds politician ranking topics and specific politician-related menu items.
         *
         * @param menuBar the main application {@link MenuBar} to which the politician
         *                menu will be added.
         * @param pageId  the identifier for the current page context.
         */
        @Override
        public void createPoliticianMenuBar(final MenuBar menuBar, final String pageId) {
                initApplicationMenuBar(menuBar);
                applicationMenuItemFactory.addRankingMenu(menuBar);
                politicianRankingMenuItemFactory.createPoliticianRankingTopics(
                                menuBar.addItem(POLITICIAN_RANKING, VaadinIcons.USER, null));

                final MenuItem politicianItem = menuBar.addItem("Politician " + pageId, VaadinIcons.USER, null);

                politicianItem.addItem(OVERVIEW_TEXT, VaadinIcons.DASHBOARD,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.OVERVIEW, pageId));

                politicianItem.addItem(INDICATORS_TEXT, VaadinIcons.CHART,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.INDICATORS, pageId));

                final MenuItem rolesItem = politicianItem.addItem(ROLES_TEXT, VaadinIcons.TAGS, null);

                rolesItem.addItem(TOTAL_EXPERIENCE, VaadinIcons.USER_CLOCK,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.ROLESUMMARY.toString(), pageId));

                rolesItem.addItem(ROLE_LIST, VaadinIcons.LIST,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.ROLELIST.toString(), pageId));

                rolesItem.addItem(ROLE_GHANT_TEXT, VaadinIcons.USER_CLOCK,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.ROLEGHANT.toString(), pageId));

                final MenuItem documentItem = politicianItem.addItem(DOCUMENTS_TEXT, VaadinIcons.FILE_TEXT, null);

                documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE_TEXT,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.DOCUMENTACTIVITY.toString(), pageId));

                documentItem.addItem(DOCUMENT_HISTORY_TEXT, VaadinIcons.CHART,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.DOCUMENTHISTORY.toString(), pageId));

                final MenuItem ballotItem = politicianItem.addItem(BALLOTS_TEXT, VaadinIcons.CHECK, null);

                ballotItem.addItem(VOTE_HISTORY, VaadinIcons.CHECK_CIRCLE,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.VOTEHISTORY.toString(), pageId));

                ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.CHECK,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
                                                PoliticianPageMode.BALLOTDECISIONSUMMARY.toString(), pageId));

                politicianItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART,
                                new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.PAGEVISITHISTORY,
                                                pageId));
        }
}
