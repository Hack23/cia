/*
 * Copyright 2010-2025 James Pether Sörling
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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMainViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPoliticianConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserConstants;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ApplicationMenuItemFactoryImpl.
 */
@Service
public final class ApplicationMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements ApplicationMenuItemFactory {

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

		final MenuItem swedenMenuItem = rankingsMenuItem.addItem(SWEDEN_DASHBOARD, VaadinIcons.FLAG,
				COMMAND_DASHBOARDVIEW_OVERVIEW);
		countryMenuItemFactory.createCountryTopicMenu(swedenMenuItem);

		rankingsMenuItem.addItem(MINISTRIES_LEADER_SCOREBOARD, VaadinIcons.TROPHY,
				PageCommandUserConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD);
		rankingsMenuItem.addItem(PART_LEADERS_SCOREBOARD, VaadinIcons.TROPHY, COMMAND_PARTY_LEADER_SCOREBOARD);

		final MenuItem countryMenuItem = rankingsMenuItem.addItem(COUNTRY_RANKING_LINK_TEXT, VaadinIcons.FLAG,
				COMMAND_COUNTRY_RANKING_OVERVIEW);
		countryMenuItemFactory.createCountryTopicMenu(countryMenuItem);

		final MenuItem ministryMenuItem = rankingsMenuItem.addItem(MINISTRY_RANKING_LINK_TEXT, VaadinIcons.OFFICE,
				COMMAND_MINISTRY_RANKING_OVERVIEW);
		ministryRankingMenuItemFactory.createMinistryRankingTopics(ministryMenuItem);

		final MenuItem govbodyMenuItem = rankingsMenuItem.addItem(GOVERNMENT_BODY_RANKING, VaadinIcons.BUILDING_O,
				COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW);
		governmentBodyRankingMenuItemFactory.createGovernmentBodyRankingTopics(govbodyMenuItem);

		final MenuItem parliamentMenuItem = rankingsMenuItem.addItem(PARLIAMENT_RANKING_LINK_TEXT,
				VaadinIcons.INSTITUTION, COMMAND_PARLIAMENT_RANKING_OVERVIEW);
		parliamentMenuItemFactory.createParliamentTopicMenu(parliamentMenuItem);

		final MenuItem committeeMenuItem = rankingsMenuItem.addItem(COMMITTEE_RANKING_LINK_TEXT, VaadinIcons.GROUP,
				COMMAND_COMMITTEE_RANKING_OVERVIEW);
		committeeRankingMenuItemFactory.createCommitteeRankingTopics(committeeMenuItem);

		final MenuItem partynMenuItem = rankingsMenuItem.addItem(PARTY_RANKING_LINK_TEXT, VaadinIcons.USERS,
				COMMAND_PARTY_RANKING_OVERVIEW);
		partyRankingMenuItemFactory.createPartyRankingTopics(partynMenuItem);

		final MenuItem politicianMenuItem = rankingsMenuItem.addItem(POLITICIAN_RANKING_LINK_TEXT, VaadinIcons.USER,
				POLITICIAN_RANKING_COMMAND_OVERVIEW);
		politicianRankingMenuItemFactory.createPoliticianRankingTopics(politicianMenuItem);

		final MenuItem documentsMenuItem = rankingsMenuItem.addItem(DOCUMENTS, VaadinIcons.FILE_TABLE, null);
		documentsMenuItem.addItem("List all", VaadinIcons.FILE_TABLE, COMMAND_DOCUMENTS_OVERVIEW);
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
		createButtonLink(grid, SWEDEN_DASHBOARD, VaadinIcons.FLAG, COMMAND_DASHBOARDVIEW_OVERVIEW,
				"Visualize political activity in Sweden, present key performance indicators and metadata");
		createButtonLink(grid, MINISTRIES_LEADER_SCOREBOARD, VaadinIcons.TROPHY,
				COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD, MINISTRIES_LEADER_SCOREBOARD_DESCRIPTION);
		createButtonLink(grid, PART_LEADERS_SCOREBOARD, VaadinIcons.TROPHY, COMMAND_PARTY_LEADER_SCOREBOARD,
				DESC_LEADERS_SCOREBOARD);
		createButtonLink(grid, COUNTRY_RANKING_LINK_TEXT, VaadinIcons.FLAG, COMMAND_COUNTRY_RANKING_OVERVIEW,
				COUNTRY_RANKING_DESCRIPTION);
		createButtonLink(grid, MINISTRY_RANKING_LINK_TEXT, VaadinIcons.OFFICE, COMMAND_MINISTRY_RANKING_OVERVIEW,
				MINISTRY_RANKING_DESCRIPTION);
		createButtonLink(grid, MINISTRIES_LINK_TEXT, VaadinIcons.OFFICE, COMMAND_MINISTRY_RANKING_OVERVIEW,
				MINISTRIES_DESCRIPTION);
		createButtonLink(grid, GOVERNMENT_BODY_RANKING, VaadinIcons.BUILDING_O,
				COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW, GOVERNMENT_BODY_RANKING_DESCRIPTION);
		createButtonLink(grid, GOVERNMENT_BODIES, VaadinIcons.BUILDING_O, COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID,
				GOVERNMENT_BODIES_DESCRIPTION);
		createButtonLink(grid, PARLIAMENT_RANKING_LINK_TEXT, VaadinIcons.INSTITUTION,
				COMMAND_PARLIAMENT_RANKING_OVERVIEW, PARLIAMENT_RANKING_DESCRIPTION);
		createButtonLink(grid, COMMITTEE_RANKING_LINK_TEXT, VaadinIcons.GROUP, COMMAND_COMMITTEE_RANKING_OVERVIEW,
				COMMITTEE_RANKING_DESCRIPTION);
		createButtonLink(grid, COMMITTEES_LINK_TEXT, VaadinIcons.GROUP, COMMAND_COMMITTEE_RANKING_DATAGRID,
				COMMITTEES_DESCRIPTION);
		createButtonLink(grid, PARTY_RANKING_LINK_TEXT, VaadinIcons.USERS, COMMAND_PARTY_RANKING_OVERVIEW,
				PARTY_RANKING_DESCRIPTION);
		createButtonLink(grid, PARTIES_LINK_TEXT, VaadinIcons.USERS,
				PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_DATAGRID, PARTIES_DESCRIPTION);
		createButtonLink(grid, POLITICIAN_RANKING_LINK_TEXT, VaadinIcons.USER, POLITICIAN_RANKING_COMMAND_DATAGRID,
				POLITICIAN_RANKING_DESCRIPTION);
		createButtonLink(grid, POLITICIANS_LINK_TEXT, VaadinIcons.USER,
				PageCommandPoliticianConstants.COMMAND_POLITICIANS_LINK, POLITICIANS_DESCRIPTION);
		createButtonLink(grid, SEARCH_DOCUMENTS, VaadinIcons.SEARCH,
				PageCommandMainViewConstants.COMMAND_SEARCH_DOCUMENT, SEARCH_DOCUMENTS_DESCRIPTION);
		createButtonLink(grid, DOCUMENTS, VaadinIcons.FILE_TABLE, COMMAND_DOCUMENTS_OVERVIEW, DOCUMENTS_DESCRIPTION);
	}
}
