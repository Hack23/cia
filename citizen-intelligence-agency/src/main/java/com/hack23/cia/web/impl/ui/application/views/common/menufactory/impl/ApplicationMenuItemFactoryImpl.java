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
 *	$Id$
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
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
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

	/** The Constant COMMAND_COMMITTEE_RANKING_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_COMMITTEE_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	/** The Constant COMMAND_COMMITTEE_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_COMMITTEE_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_COUNTRY_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_COUNTRY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_DOCUMENTS. */
	private static final PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME,PageMode.OVERVIEW);

	/** The Constant COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	private static final PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_MINISTRY_RANKING_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_MINISTRY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	/** The Constant COMMAND_MINISTRY_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_MINISTRY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_PARLIAMENT_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_PARLIAMENT_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_PARTY_RANKING_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_PARTY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	/** The Constant COMMAND_PARTY_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_PARTY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_POLITICIAN_RANKING_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_POLITICIAN_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME,
			PageMode.DATAGRID);


	/** The Constant COMMAND_POLITICIAN_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_SEARCH_DOCUMENT. */
	private static final PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME,"");

	/** The Constant COMMITTEE_RANKING_TEXT. */
	private static final String COMMITTEE_RANKING_TEXT = "Committee Ranking";

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
	private static final String COUNTRY_RANKING_DESCRIPTION = "Indicators for Sweden overview, find data by topic or source.";

	/** The Constant MINISTRY_RANKING_DESCRIPTION. */
	private static final String MINISTRY_RANKING_DESCRIPTION = "Ministry ranking overview, contains data and charts.";

	/** The Constant MINISTRIES_DESCRIPTION. */
	private static final String MINISTRIES_DESCRIPTION = "All ministries, scoreboard assignments and days served in committees";

	/** The Constant GOVERNMENT_BODY_RANKING_DESCRIPTION. */
	private static final String GOVERNMENT_BODY_RANKING_DESCRIPTION = "All government bodies overview";

	/** The Constant GOVERNMENT_BODIES_DESCRIPTION. */
	private static final String GOVERNMENT_BODIES_DESCRIPTION = "All government bodies, current headcount";

	/** The Constant PARLIAMENT_RANKING_DESCRIPTION. */
	private static final String PARLIAMENT_RANKING_DESCRIPTION = "Charts over parliamentary ballots and document activity";

	/** The Constant COMMITTEE_RANKING_DESCRIPTION. */
	private static final String COMMITTEE_RANKING_DESCRIPTION = "Committee ranking overview, contains data and charts.";

	/** The Constant COMMITTEES_DESCRIPTION. */
	private static final String COMMITTEES_DESCRIPTION = "All committees, scoreboard assignments and days served in committees";

	/** The Constant PARTY_RANKING_DESCRIPTION. */
	private static final String PARTY_RANKING_DESCRIPTION = "Party ranking overview, contains data and charts.";

	/** The Constant PARTIES_DESCRIPTION. */
	private static final String PARTIES_DESCRIPTION = "All parties, scoreboard assignments and days served in government, committees, speaker and party positions.";

	/** The Constant POLITICIAN_RANKING_DESCRIPTION. */
	private static final String POLITICIAN_RANKING_DESCRIPTION = "Politician ranking overview, contains data and charts.";

	/** The Constant POLITICIANS_DESCRIPTION. */
	private static final String POLITICIANS_DESCRIPTION = "All politicians, scoreboard assignments and days served in government, committees, speaker and party positions.";

	/** The Constant SEARCH_DOCUMENTS_DESCRIPTION. */
	private static final String SEARCH_DOCUMENTS_DESCRIPTION = "Search parliament documents";

	/** The Constant DOCUMENTS_DESCRIPTION. */
	private static final String DOCUMENTS_DESCRIPTION = "List all parliament documents";

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

	@Override
	public void addRankingMenu(final MenuBar menuBar) {

		final MenuItem rankingsMenuItem = menuBar.addItem(RANKING_TEXT, VaadinIcons.LINE_CHART, null);

		final MenuItem countryMenuItem = rankingsMenuItem.addItem(COUNTRY_RANKING_LINK_TEXT,VaadinIcons.FLAG, COMMAND_COUNTRY_RANKING_OVERVIEW);

		countryMenuItemFactory.createCountryTopicMenu(countryMenuItem);


		final MenuItem ministryMenuItem = rankingsMenuItem.addItem(MINISTRY_RANKING_LINK_TEXT,VaadinIcons.GROUP, COMMAND_MINISTRY_RANKING_OVERVIEW);

		ministryRankingMenuItemFactory.createMinistryRankingTopics(ministryMenuItem);

		final MenuItem govbodyMenuItem = rankingsMenuItem.addItem(GOVERNMENT_BODY_RANKING,VaadinIcons.GROUP, COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW);

		governmentBodyRankingMenuItemFactory.createGovernmentBodyRankingTopics(govbodyMenuItem);


		final MenuItem parliamentMenuItem = rankingsMenuItem.addItem(PARLIAMENT_RANKING_LINK_TEXT,VaadinIcons.INSTITUTION, COMMAND_PARLIAMENT_RANKING_OVERVIEW);

		parliamentMenuItemFactory.createParliamentTopicMenu(parliamentMenuItem);

		final MenuItem committeeMenuItem = rankingsMenuItem.addItem(COMMITTEE_RANKING_LINK_TEXT,VaadinIcons.GROUP, COMMAND_COMMITTEE_RANKING_OVERVIEW);

		committeeRankingMenuItemFactory.createCommitteeRankingTopics(committeeMenuItem);

		final MenuItem partynMenuItem = rankingsMenuItem.addItem(PARTY_RANKING_LINK_TEXT, VaadinIcons.GROUP,COMMAND_PARTY_RANKING_OVERVIEW);

		partyRankingMenuItemFactory.createPartyRankingTopics(partynMenuItem);

		final MenuItem politicianMenuItem = rankingsMenuItem.addItem(POLITICIAN_RANKING_LINK_TEXT,VaadinIcons.USER, COMMAND_POLITICIAN_RANKING_OVERVIEW);

		politicianRankingMenuItemFactory.createPoliticianRankingTopics(politicianMenuItem);

		final MenuItem documentsMenuItem = rankingsMenuItem.addItem(DOCUMENTS,VaadinIcons.GROUP, COMMAND_DOCUMENTS);
		documentsMenuItem.addItem("List all",VaadinIcons.GROUP, COMMAND_DOCUMENTS);
		documentsMenuItem.addItem(SEARCH_DOCUMENTS,VaadinIcons.GROUP, COMMAND_SEARCH_DOCUMENT);
	}

	@Override
	public MenuBar createMainPageMenuBar(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		addRankingMenu(menuBar);

		return menuBar;
	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid,COUNTRY_RANKING_LINK_TEXT,VaadinIcons.FLAG, COMMAND_COUNTRY_RANKING_OVERVIEW,COUNTRY_RANKING_DESCRIPTION);

		createButtonLink(grid,MINISTRY_RANKING_LINK_TEXT,VaadinIcons.GROUP, COMMAND_MINISTRY_RANKING_OVERVIEW,MINISTRY_RANKING_DESCRIPTION);

		createButtonLink(grid,MINISTRIES_LINK_TEXT,VaadinIcons.GROUP, COMMAND_MINISTRY_RANKING_DATAGRID,MINISTRIES_DESCRIPTION);

		createButtonLink(grid,GOVERNMENT_BODY_RANKING,VaadinIcons.GROUP, COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW,GOVERNMENT_BODY_RANKING_DESCRIPTION);

		createButtonLink(grid,GOVERNMENT_BODIES,VaadinIcons.GROUP, COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID,GOVERNMENT_BODIES_DESCRIPTION);

		createButtonLink(grid,PARLIAMENT_RANKING_LINK_TEXT,VaadinIcons.INSTITUTION, COMMAND_PARLIAMENT_RANKING_OVERVIEW,PARLIAMENT_RANKING_DESCRIPTION);

		createButtonLink(grid,COMMITTEE_RANKING_LINK_TEXT,VaadinIcons.GROUP, COMMAND_COMMITTEE_RANKING_OVERVIEW,COMMITTEE_RANKING_DESCRIPTION);

		createButtonLink(grid,COMMITTEES_LINK_TEXT,VaadinIcons.GROUP, COMMAND_COMMITTEE_RANKING_DATAGRID,COMMITTEES_DESCRIPTION);

		createButtonLink(grid,PARTY_RANKING_LINK_TEXT, VaadinIcons.GROUP,COMMAND_PARTY_RANKING_OVERVIEW,PARTY_RANKING_DESCRIPTION);

		createButtonLink(grid,PARTIES_LINK_TEXT, VaadinIcons.GROUP,COMMAND_PARTY_RANKING_DATAGRID,PARTIES_DESCRIPTION);


		createButtonLink(grid,POLITICIAN_RANKING_LINK_TEXT,VaadinIcons.USER, COMMAND_POLITICIAN_RANKING_OVERVIEW,POLITICIAN_RANKING_DESCRIPTION);

		createButtonLink(grid,POLITICIANS_LINK_TEXT,VaadinIcons.USER, COMMAND_POLITICIAN_RANKING_DATAGRID,POLITICIANS_DESCRIPTION);

		createButtonLink(grid,SEARCH_DOCUMENTS,VaadinIcons.GROUP, COMMAND_SEARCH_DOCUMENT,SEARCH_DOCUMENTS_DESCRIPTION);

		createButtonLink(grid,DOCUMENTS,VaadinIcons.GROUP, COMMAND_DOCUMENTS,DOCUMENTS_DESCRIPTION);

	}

}
