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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CountryMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ParliamentMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class ApplicationMenuItemFactoryImpl.
 */
@Service
public final class ApplicationMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements ApplicationMenuItemFactory {

	/** The Constant COMMAND_PARLIAMENT_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_PARLIAMENT_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_COUNTRY_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_COUNTRY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_MINISTRY_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_MINISTRY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_MINISTRY_RANKING_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_MINISTRY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.DATAGRID);


	/** The Constant COMMAND_COMMITTEE_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_COMMITTEE_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_COMMITTEE_RANKING_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_COMMITTEE_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	/** The Constant COMMAND_PARTY_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_PARTY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_PARTY_RANKING_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_PARTY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME,
			PageMode.DATAGRID);


	/** The Constant COMMAND_POLITICIAN_RANKING_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_POLITICIAN_RANKING_DATAGRID. */
	private static final PageModeMenuCommand COMMAND_POLITICIAN_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME,
			PageMode.DATAGRID);

	/** The Constant COMMAND_SEARCH_DOCUMENT. */
	private static final PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME,"");

	/** The Constant COMMAND_DOCUMENTS. */
	private static final PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME,PageMode.OVERVIEW);


	/** The Constant POLITICIAN_RANKING. */
	private static final String POLITICIAN_RANKING = "Politician Ranking";

	/** The Constant PARTY_RANKING. */
	private static final String PARTY_RANKING = "Party Ranking";

	/** The Constant MINISTRY_RANKING. */
	private static final String MINISTRY_RANKING = "Ministry Ranking";

	/** The Constant COMMITTEE_RANKING_TEXT. */
	private static final String COMMITTEE_RANKING_TEXT = "Committee Ranking";

	/** The Constant RANKING_TEXT. */
	private static final String RANKING_TEXT = "Ranking";

	/** The Constant POLITICIAN_RANKING_LINK_TEXT. */
	private static final String POLITICIAN_RANKING_LINK_TEXT = POLITICIAN_RANKING;

	/** The Constant PARTY_RANKING_LINK_TEXT. */
	private static final String PARTY_RANKING_LINK_TEXT = PARTY_RANKING;

	/** The Constant COMMITTEE_RANKING_LINK_TEXT. */
	private static final String COMMITTEE_RANKING_LINK_TEXT = COMMITTEE_RANKING_TEXT;

	/** The Constant MINISTRY_RANKING_LINK_TEXT. */
	private static final String MINISTRY_RANKING_LINK_TEXT = MINISTRY_RANKING;

	/** The Constant COUNTRY_RANKING_LINK_TEXT. */
	private static final String COUNTRY_RANKING_LINK_TEXT = "Country Ranking";

	/** The Constant PARLIAMENT_RANKING_LINK_TEXT. */
	private static final String PARLIAMENT_RANKING_LINK_TEXT = "Parliament Ranking";

	/** The Constant MINISTRIES_LINK_TEXT. */
	private static final String MINISTRIES_LINK_TEXT = "Ministries";

	/** The Constant COMMITTEES_LINK_TEXT. */
	private static final String COMMITTEES_LINK_TEXT = "Committees";

	/** The Constant PARTIES_LINK_TEXT. */
	private static final String PARTIES_LINK_TEXT = "Parties";

	/** The Constant POLITICIANS_LINK_TEXT. */
	private static final String POLITICIANS_LINK_TEXT = "Politicians";

	/** The Constant DOCUMENTS. */
	private static final String DOCUMENTS = "Documents";

	/** The Constant SEARCH_DOCUMENTS. */
	private static final String SEARCH_DOCUMENTS = "Search documents";


	/** The politician ranking menu item factory. */
	@Autowired
	private PoliticianRankingMenuItemFactory politicianRankingMenuItemFactory;

	/** The party ranking menu item factory. */
	@Autowired
	private PartyRankingMenuItemFactory partyRankingMenuItemFactory;

	/** The committee ranking menu item factory. */
	@Autowired
	private CommitteeRankingMenuItemFactory committeeRankingMenuItemFactory;

	/** The ministry ranking menu item factory. */
	@Autowired
	private MinistryRankingMenuItemFactory ministryRankingMenuItemFactory;

	/** The country menu item factory. */
	@Autowired
	private CountryMenuItemFactory countryMenuItemFactory;

	/** The parliament menu item factory. */
	@Autowired
	private ParliamentMenuItemFactory parliamentMenuItemFactory;

	/**
	 * Instantiates a new application menu item factory impl.
	 */
	public ApplicationMenuItemFactoryImpl() {
		super();
	}

	@Override
	public MenuBar createMainPageMenuBar(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		addRankingMenu(menuBar);

		return menuBar;
	}

	@Override
	public void addRankingMenu(final MenuBar menuBar) {

		final MenuItem rankingsMenuItem = menuBar.addItem(RANKING_TEXT, FontAwesome.AREA_CHART, null);

		final MenuItem countryMenuItem = rankingsMenuItem.addItem(COUNTRY_RANKING_LINK_TEXT,FontAwesome.FLAG, COMMAND_COUNTRY_RANKING_OVERVIEW);

		countryMenuItemFactory.createCountryTopicMenu(countryMenuItem);

		final MenuItem parliamentMenuItem = rankingsMenuItem.addItem(PARLIAMENT_RANKING_LINK_TEXT,FontAwesome.INSTITUTION, COMMAND_PARLIAMENT_RANKING_OVERVIEW);

		parliamentMenuItemFactory.createParliamentTopicMenu(parliamentMenuItem);

		final MenuItem politicianMenuItem = rankingsMenuItem.addItem(POLITICIAN_RANKING_LINK_TEXT,FontAwesome.USER, COMMAND_POLITICIAN_RANKING_OVERVIEW);

		politicianRankingMenuItemFactory.createPoliticianRankingTopics(politicianMenuItem);

		final MenuItem partynMenuItem = rankingsMenuItem.addItem(PARTY_RANKING_LINK_TEXT, FontAwesome.GROUP,COMMAND_PARTY_RANKING_OVERVIEW);

		partyRankingMenuItemFactory.createPartyRankingTopics(partynMenuItem);

		final MenuItem committeeMenuItem = rankingsMenuItem.addItem(COMMITTEE_RANKING_LINK_TEXT,FontAwesome.GROUP, COMMAND_COMMITTEE_RANKING_OVERVIEW);

		committeeRankingMenuItemFactory.createCommitteeRankingTopics(committeeMenuItem);

		final MenuItem ministryMenuItem = rankingsMenuItem.addItem(MINISTRY_RANKING_LINK_TEXT,FontAwesome.GROUP, COMMAND_MINISTRY_RANKING_OVERVIEW);

		ministryRankingMenuItemFactory.createMinistryRankingTopics(ministryMenuItem);


		final MenuItem documentsMenuItem = rankingsMenuItem.addItem(DOCUMENTS,FontAwesome.GROUP, COMMAND_DOCUMENTS);
		documentsMenuItem.addItem("List all",FontAwesome.GROUP, COMMAND_DOCUMENTS);
		documentsMenuItem.addItem(SEARCH_DOCUMENTS,FontAwesome.GROUP, COMMAND_SEARCH_DOCUMENT);
	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final ResponsiveRow grid = createGridLayout(panelContent);

		createButtonLink(grid,COUNTRY_RANKING_LINK_TEXT,FontAwesome.FLAG, COMMAND_COUNTRY_RANKING_OVERVIEW,"Indicators for Sweden overview, find data by topic or source.");

		createButtonLink(grid,PARLIAMENT_RANKING_LINK_TEXT,FontAwesome.INSTITUTION, COMMAND_PARLIAMENT_RANKING_OVERVIEW,"Charts over parlimentary ballots and document activity");

		createButtonLink(grid,POLITICIAN_RANKING_LINK_TEXT,FontAwesome.USER, COMMAND_POLITICIAN_RANKING_OVERVIEW,"Politician ranking overiew, contains data and charts.");

		createButtonLink(grid,POLITICIANS_LINK_TEXT,FontAwesome.USER, COMMAND_POLITICIAN_RANKING_DATAGRID,"All politicans, scoreboard assignments and days served in government, committees, speaker and party positions.");


		createButtonLink(grid,PARTY_RANKING_LINK_TEXT, FontAwesome.GROUP,COMMAND_PARTY_RANKING_OVERVIEW,"Party ranking overiew, contains data and charts.");

		createButtonLink(grid,PARTIES_LINK_TEXT, FontAwesome.GROUP,COMMAND_PARTY_RANKING_DATAGRID,"All parties, scoreboard assignments and days served in government, committees, speaker and party positions.");


		createButtonLink(grid,COMMITTEE_RANKING_LINK_TEXT,FontAwesome.GROUP, COMMAND_COMMITTEE_RANKING_OVERVIEW,"Committee ranking overiew, contains data and charts.");

		createButtonLink(grid,COMMITTEES_LINK_TEXT,FontAwesome.GROUP, COMMAND_COMMITTEE_RANKING_DATAGRID,"All committees, scoreboard assignments and days served in committees");


		createButtonLink(grid,MINISTRY_RANKING_LINK_TEXT,FontAwesome.GROUP, COMMAND_MINISTRY_RANKING_OVERVIEW,"Ministry ranking overiew, contains data and charts.");

		createButtonLink(grid,MINISTRIES_LINK_TEXT,FontAwesome.GROUP, COMMAND_MINISTRY_RANKING_DATAGRID,"All ministries, scoreboard assignments and days served in committees");

		createButtonLink(grid,SEARCH_DOCUMENTS,FontAwesome.GROUP, COMMAND_SEARCH_DOCUMENT,"Search parliament documents");

		createButtonLink(grid,DOCUMENTS,FontAwesome.GROUP, COMMAND_DOCUMENTS,"List all parliament documents");

	}

}
