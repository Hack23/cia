/*
 * Copyright 2010-2024 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  $Id$
 *  $HeadURL$
 */
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CountryMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.GovernmentBodyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ParliamentMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ApplicationMenuItemFactoryImpl.
 *
 * This class is responsible for creating and managing the application menu items,
 * including ranking menus and overview pages. It integrates various menu item factories
 * to provide a comprehensive menu structure for the Citizen Intelligence Agency web application.
 */
@Service
public final class ApplicationMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
        implements ApplicationMenuItemFactory {

    /** The Constant SWEDEN_DASHBOARD. */
    private static final String SWEDEN_DASHBOARD = "Sweden Dashboard";

	/** The Constant COMMAND_COMMITTEE_RANKING_DATAGRID. */
    private static final PageModeMenuCommand COMMAND_COMMITTEE_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The Constant COMMAND_COMMITTEE_RANKING_OVERVIEW. */
    private static final PageModeMenuCommand COMMAND_COMMITTEE_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_COUNTRY_RANKING_OVERVIEW. */
    private static final PageModeMenuCommand COMMAND_COUNTRY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The Constant COMMAND_DASHBOARDVIEW_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_DASHBOARDVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.DASHBOARD_VIEW_NAME,
			PageMode.OVERVIEW);


    /** The Constant COMMAND_DOCUMENTS. */
    private static final PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID. */
    private static final PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The Constant COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW. */
    private static final PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_MINISTRY_RANKING_DATAGRID. */
    private static final PageModeMenuCommand COMMAND_MINISTRY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The Constant COMMAND_MINISTRY_RANKING_OVERVIEW. */
    private static final PageModeMenuCommand COMMAND_MINISTRY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_PARLIAMENT_RANKING_OVERVIEW. */
    private static final PageModeMenuCommand COMMAND_PARLIAMENT_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_PARTY_RANKING_DATAGRID. */
    private static final PageModeMenuCommand COMMAND_PARTY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The Constant COMMAND_PARTY_RANKING_OVERVIEW. */
    private static final PageModeMenuCommand COMMAND_PARTY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_POLITICIAN_RANKING_DATAGRID. */
    private static final PageModeMenuCommand COMMAND_POLITICIAN_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The Constant COMMAND_POLITICIAN_RANKING_OVERVIEW. */
    private static final PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_SEARCH_DOCUMENT. */
    private static final PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME, "");

	/** The Constant COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD. */
	private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());

    /** The Constant COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD. */
    private static final PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTYLEADERSCORECARD.toString());


    /** The Constant COMMITTEE_RANKING_TEXT. */
    private static final String COMMITTEE_RANKING_TEXT = "Committee Ranking";

    /** The Constant DESC_LEADERS_SCOREBOARD. */
    private static final String DESC_LEADERS_SCOREBOARD = "Leaders: comparing party leadership impact.";

    /** The Constant PART_LEADERS_SCOREBOARD. */
    private static final String PART_LEADERS_SCOREBOARD = "Party leaders scoreboard";

    /** The Constant COMMITTEE_RANKING_LINK_TEXT. */
    private static final String COMMITTEE_RANKING_LINK_TEXT = COMMITTEE_RANKING_TEXT;

    /** The Constant COMMITTEES_LINK_TEXT. */
    private static final String COMMITTEES_LINK_TEXT = "Committees";

    /** The Constant COUNTRY_RANKING_LINK_TEXT. */
    private static final String COUNTRY_RANKING_LINK_TEXT = "Country Ranking";

    /** The Constant DOCUMENTS. */
    private static final String DOCUMENTS = "Documents";

    /** The Constant GOVERNMENT_BODIES. */
    private static final String GOVERNMENT_BODIES = "Government bodies";

    /** The Constant GOVERNMENT_BODY_RANKING. */
    private static final String GOVERNMENT_BODY_RANKING = "Government body Ranking";

    /** The Constant MINISTRIES_LINK_TEXT. */
    private static final String MINISTRIES_LINK_TEXT = "Ministries";

    /** The Constant MINISTRY_RANKING. */
    private static final String MINISTRY_RANKING = "Ministry Ranking";

    /** The Constant MINISTRY_RANKING_LINK_TEXT. */
    private static final String MINISTRY_RANKING_LINK_TEXT = MINISTRY_RANKING;

    /** The Constant PARLIAMENT_RANKING_LINK_TEXT. */
    private static final String PARLIAMENT_RANKING_LINK_TEXT = "Parliament Ranking";

    /** The Constant PARTIES_LINK_TEXT. */
    private static final String PARTIES_LINK_TEXT = "Parties";

    /** The Constant PARTY_RANKING. */
    private static final String PARTY_RANKING = "Party Ranking";

    /** The Constant PARTY_RANKING_LINK_TEXT. */
    private static final String PARTY_RANKING_LINK_TEXT = PARTY_RANKING;

    /** The Constant POLITICIAN_RANKING. */
    private static final String POLITICIAN_RANKING = "Politician Ranking";

    /** The Constant POLITICIAN_RANKING_LINK_TEXT. */
    private static final String POLITICIAN_RANKING_LINK_TEXT = POLITICIAN_RANKING;

    /** The Constant POLITICIANS_LINK_TEXT. */
    private static final String POLITICIANS_LINK_TEXT = "Politicians";

    /** The Constant RANKING_TEXT. */
    private static final String RANKING_TEXT = "Ranking";

    /** The Constant SEARCH_DOCUMENTS. */
    private static final String SEARCH_DOCUMENTS = "Search documents";

    /** The Constant COUNTRY_RANKING_DESCRIPTION. */
    private static final String COUNTRY_RANKING_DESCRIPTION = "Sweden’s governance: key indicators.";

	/** The Constant MINISTRIES_LEADER_SCOREBOARD. */
	private static final String MINISTRIES_LEADER_SCOREBOARD = "Government Ministers Scoreboard";

	/** The Constant MINISTRIES_LEADER_SCOREBOARD_DESCRIPTION. */
	private static final String MINISTRIES_LEADER_SCOREBOARD_DESCRIPTION = "Cabinet leaders: power and institutional impact.";

    /** The Constant MINISTRY_RANKING_DESCRIPTION. */
    private static final String MINISTRY_RANKING_DESCRIPTION = "Ministry influence on policy.";

    /** The Constant MINISTRIES_DESCRIPTION. */
    private static final String MINISTRIES_DESCRIPTION = "All ministries: roles and commitments.";

    /** The Constant GOVERNMENT_BODY_RANKING_DESCRIPTION. */
    private static final String GOVERNMENT_BODY_RANKING_DESCRIPTION = "Government bodies: performance snapshots.";

    /** The Constant GOVERNMENT_BODIES_DESCRIPTION. */
    private static final String GOVERNMENT_BODIES_DESCRIPTION = "All bodies: structure and influence.";

    /** The Constant PARLIAMENT_RANKING_DESCRIPTION. */
    private static final String PARLIAMENT_RANKING_DESCRIPTION = "Parliament: legislative effectiveness.";

    /** The Constant COMMITTEE_RANKING_DESCRIPTION. */
    private static final String COMMITTEE_RANKING_DESCRIPTION = "Committees: shaping policy debates.";

    /** The Constant COMMITTEES_DESCRIPTION. */
    private static final String COMMITTEES_DESCRIPTION = "All committees: roles and responsibilities.";

    /** The Constant PARTY_RANKING_DESCRIPTION. */
    private static final String PARTY_RANKING_DESCRIPTION = "Parties: influence on agenda.";

    /** The Constant PARTIES_DESCRIPTION. */
    private static final String PARTIES_DESCRIPTION = "All parties: leadership and policy roles.";

    /** The Constant POLITICIAN_RANKING_DESCRIPTION. */
    private static final String POLITICIAN_RANKING_DESCRIPTION = "Politicians: individual impact.";

    /** The Constant POLITICIANS_DESCRIPTION. */
    private static final String POLITICIANS_DESCRIPTION = "All politicians: service and contributions.";

    /** The Constant SEARCH_DOCUMENTS_DESCRIPTION. */
    private static final String SEARCH_DOCUMENTS_DESCRIPTION = "Search documents: legislative records.";

    /** The Constant DOCUMENTS_DESCRIPTION. */
    private static final String DOCUMENTS_DESCRIPTION = "All documents: official records.";

    /** The committee ranking menu item factory. */
    @Autowired
    private CommitteeRankingMenuItemFactory committeeRankingMenuItemFactory;

    /** The country menu item factory. */
    @Autowired
    private CountryMenuItemFactory countryMenuItemFactory;

    /** The government body ranking menu item factory. */
    @Autowired
    private GovernmentBodyRankingMenuItemFactory governmentBodyRankingMenuItemFactory;

    /** The ministry ranking menu item factory. */
    @Autowired
    private MinistryRankingMenuItemFactory ministryRankingMenuItemFactory;

    /** The parliament menu item factory. */
    @Autowired
    private ParliamentMenuItemFactory parliamentMenuItemFactory;

    /** The party ranking menu item factory. */
    @Autowired
    private PartyRankingMenuItemFactory partyRankingMenuItemFactory;

    /** The politician ranking menu item factory. */
    @Autowired
    private PoliticianRankingMenuItemFactory politicianRankingMenuItemFactory;

    /**
     * Instantiates a new application menu item factory impl.
     */
    public ApplicationMenuItemFactoryImpl() {
        super();
    }

    /**
     * Adds the ranking menu.
     *
     * @param menuBar the menu bar
     */
    @Override
    public void addRankingMenu(final MenuBar menuBar) {
        final MenuItem rankingsMenuItem = menuBar.addItem(RANKING_TEXT, VaadinIcons.LINE_CHART, null);

        final MenuItem swedenMenuItem = rankingsMenuItem.addItem(SWEDEN_DASHBOARD, VaadinIcons.FLAG, COMMAND_DASHBOARDVIEW_OVERVIEW);
        countryMenuItemFactory.createCountryTopicMenu(swedenMenuItem);

        rankingsMenuItem.addItem(MINISTRIES_LEADER_SCOREBOARD, VaadinIcons.TROPHY,COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD);
        rankingsMenuItem.addItem(PART_LEADERS_SCOREBOARD, VaadinIcons.TROPHY,COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD);

        final MenuItem countryMenuItem = rankingsMenuItem.addItem(COUNTRY_RANKING_LINK_TEXT, VaadinIcons.FLAG, COMMAND_COUNTRY_RANKING_OVERVIEW);
        countryMenuItemFactory.createCountryTopicMenu(countryMenuItem);

        final MenuItem ministryMenuItem = rankingsMenuItem.addItem(MINISTRY_RANKING_LINK_TEXT, VaadinIcons.OFFICE, COMMAND_MINISTRY_RANKING_OVERVIEW);
        ministryRankingMenuItemFactory.createMinistryRankingTopics(ministryMenuItem);

        final MenuItem govbodyMenuItem = rankingsMenuItem.addItem(GOVERNMENT_BODY_RANKING, VaadinIcons.BUILDING_O, COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW);
        governmentBodyRankingMenuItemFactory.createGovernmentBodyRankingTopics(govbodyMenuItem);

        final MenuItem parliamentMenuItem = rankingsMenuItem.addItem(PARLIAMENT_RANKING_LINK_TEXT, VaadinIcons.INSTITUTION, COMMAND_PARLIAMENT_RANKING_OVERVIEW);
        parliamentMenuItemFactory.createParliamentTopicMenu(parliamentMenuItem);

        final MenuItem committeeMenuItem = rankingsMenuItem.addItem(COMMITTEE_RANKING_LINK_TEXT, VaadinIcons.GROUP, COMMAND_COMMITTEE_RANKING_OVERVIEW);
        committeeRankingMenuItemFactory.createCommitteeRankingTopics(committeeMenuItem);

        final MenuItem partynMenuItem = rankingsMenuItem.addItem(PARTY_RANKING_LINK_TEXT, VaadinIcons.USERS, COMMAND_PARTY_RANKING_OVERVIEW);
        partyRankingMenuItemFactory.createPartyRankingTopics(partynMenuItem);

        final MenuItem politicianMenuItem = rankingsMenuItem.addItem(POLITICIAN_RANKING_LINK_TEXT, VaadinIcons.USER, COMMAND_POLITICIAN_RANKING_OVERVIEW);
        politicianRankingMenuItemFactory.createPoliticianRankingTopics(politicianMenuItem);

        final MenuItem documentsMenuItem = rankingsMenuItem.addItem(DOCUMENTS, VaadinIcons.FILE_TABLE, COMMAND_DOCUMENTS);
        documentsMenuItem.addItem("List all", VaadinIcons.FILE_TABLE, COMMAND_DOCUMENTS);
        documentsMenuItem.addItem(SEARCH_DOCUMENTS, VaadinIcons.SEARCH, COMMAND_SEARCH_DOCUMENT);
    }

    /**
     * Creates the main page menu bar.
     *
     * @param menuBar the menu bar
     * @return the menu bar
     */
    @Override
    public MenuBar createMainPageMenuBar(final MenuBar menuBar) {
        initApplicationMenuBar(menuBar);
        addRankingMenu(menuBar);
        return menuBar;
    }

    /**
     * Creates the overview page.
     *
     * @param panelContent the panel content
     */
    @Override
    public void createOverviewPage(final VerticalLayout panelContent) {
        final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);


        createButtonLink(grid, "Sweden Dashboard", VaadinIcons.FLAG, COMMAND_DASHBOARDVIEW_OVERVIEW, "Visualize political activity in Sweden, present key performance indicators and metadata");
		createButtonLink(grid, MINISTRIES_LEADER_SCOREBOARD, VaadinIcons.TROPHY, COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD,
				MINISTRIES_LEADER_SCOREBOARD_DESCRIPTION);

		createButtonLink(grid, PART_LEADERS_SCOREBOARD, VaadinIcons.TROPHY,
	                COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD, DESC_LEADERS_SCOREBOARD);

        createButtonLink(grid, COUNTRY_RANKING_LINK_TEXT, VaadinIcons.FLAG, COMMAND_COUNTRY_RANKING_OVERVIEW, COUNTRY_RANKING_DESCRIPTION);
        createButtonLink(grid, MINISTRY_RANKING_LINK_TEXT, VaadinIcons.OFFICE, COMMAND_MINISTRY_RANKING_OVERVIEW, MINISTRY_RANKING_DESCRIPTION);
        createButtonLink(grid, MINISTRIES_LINK_TEXT, VaadinIcons.OFFICE, COMMAND_MINISTRY_RANKING_DATAGRID, MINISTRIES_DESCRIPTION);
        createButtonLink(grid, GOVERNMENT_BODY_RANKING, VaadinIcons.BUILDING_O, COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW, GOVERNMENT_BODY_RANKING_DESCRIPTION);
        createButtonLink(grid, GOVERNMENT_BODIES, VaadinIcons.BUILDING_O, COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID, GOVERNMENT_BODIES_DESCRIPTION);
        createButtonLink(grid, PARLIAMENT_RANKING_LINK_TEXT, VaadinIcons.INSTITUTION, COMMAND_PARLIAMENT_RANKING_OVERVIEW, PARLIAMENT_RANKING_DESCRIPTION);
        createButtonLink(grid, COMMITTEE_RANKING_LINK_TEXT, VaadinIcons.GROUP, COMMAND_COMMITTEE_RANKING_OVERVIEW, COMMITTEE_RANKING_DESCRIPTION);
        createButtonLink(grid, COMMITTEES_LINK_TEXT, VaadinIcons.GROUP, COMMAND_COMMITTEE_RANKING_DATAGRID, COMMITTEES_DESCRIPTION);
        createButtonLink(grid, PARTY_RANKING_LINK_TEXT, VaadinIcons.USERS, COMMAND_PARTY_RANKING_OVERVIEW, PARTY_RANKING_DESCRIPTION);
        createButtonLink(grid, PARTIES_LINK_TEXT, VaadinIcons.USERS, COMMAND_PARTY_RANKING_DATAGRID, PARTIES_DESCRIPTION);
        createButtonLink(grid, POLITICIAN_RANKING_LINK_TEXT, VaadinIcons.USER, COMMAND_POLITICIAN_RANKING_OVERVIEW, POLITICIAN_RANKING_DESCRIPTION);
        createButtonLink(grid, POLITICIANS_LINK_TEXT, VaadinIcons.USER, COMMAND_POLITICIAN_RANKING_DATAGRID, POLITICIANS_DESCRIPTION);
        createButtonLink(grid, SEARCH_DOCUMENTS, VaadinIcons.SEARCH, COMMAND_SEARCH_DOCUMENT, SEARCH_DOCUMENTS_DESCRIPTION);
        createButtonLink(grid, DOCUMENTS, VaadinIcons.FILE_TABLE, COMMAND_DOCUMENTS, DOCUMENTS_DESCRIPTION);
    }
}
