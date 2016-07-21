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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * The Class MenuItemFactoryImpl.
 */
@Service
public final class ApplicationMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements ApplicationMenuItemFactory {

	/** The Constant COMMAND6. */
	private static final PageModeMenuCommand COMMAND6 = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAND5. */
	private static final PageModeMenuCommand COMMAND5 = new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.OVERVIEW);

	/** The Constant COMMAND4. */
	private static final PageModeMenuCommand COMMAND4 = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The Constant COMMAND3. */
	private static final PageModeMenuCommand COMMAND3 = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The Constant COMMAND2. */
	private static final PageModeMenuCommand COMMAND2 = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The Constant COMMAND. */
	private static final PageModeMenuCommand COMMAND = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The Constant POLITICIAN_RANKING. */
	private static final String POLITICIAN_RANKING = "Politician Ranking";

	/** The Constant PARTY_RANKING. */
	private static final String PARTY_RANKING = "Party Ranking";

	/** The Constant MINISTRY_RANKING. */
	private static final String MINISTRY_RANKING = "Ministry Ranking";

	/** The Constant COMMITTEE_RANKING_TEXT. */
	private static final String COMMITTEE_RANKING_TEXT = "Committee Ranking";

	/** The Constant TEST_TEXT. */
	private static final String TEST_TEXT = "Test";

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

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

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
		
		final MenuItem mainItem = menuBar.addItem("Main", null, null);

		mainItem.addItem(TEST_TEXT, COMMAND5);

		mainItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,COMMAND6);

		
		final MenuItem rankingsMenuItem = menuBar.addItem(RANKING_TEXT, null, null);

		final MenuItem politicianMenuItem = rankingsMenuItem.addItem(POLITICIAN_RANKING_LINK_TEXT,
				COMMAND);

		politicianRankingMenuItemFactory.createPoliticianRankingTopics(politicianMenuItem);

		final MenuItem partynMenuItem = rankingsMenuItem.addItem(PARTY_RANKING_LINK_TEXT,
				COMMAND2);

		partyRankingMenuItemFactory.createPartyRankingTopics(partynMenuItem);

		final MenuItem committeeMenuItem = rankingsMenuItem.addItem(COMMITTEE_RANKING_LINK_TEXT,
				COMMAND3);

		committeeRankingMenuItemFactory.createCommitteeRankingTopics(committeeMenuItem);

		final MenuItem ministryMenuItem = rankingsMenuItem.addItem(MINISTRY_RANKING_LINK_TEXT,
				COMMAND4);

		ministryRankingMenuItemFactory.createMinistryRankingTopics(ministryMenuItem);

	}

}
