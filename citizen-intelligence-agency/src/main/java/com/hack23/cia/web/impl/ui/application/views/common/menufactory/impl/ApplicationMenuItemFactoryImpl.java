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
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
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

	@Autowired
	private PoliticianRankingMenuItemFactory politicianRankingMenuItemFactory;

	@Autowired
	private PartyRankingMenuItemFactory partyRankingMenuItemFactory;

	@Autowired
	private CommitteeRankingMenuItemFactory committeeRankingMenuItemFactory;

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

		final MenuItem rankingsMenuItem = menuBar.addItem(RANKING_TEXT, null, null);

		final MenuItem politicianMenuItem = rankingsMenuItem.addItem(POLITICIAN_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		politicianRankingMenuItemFactory.createPoliticianRankingTopics(politicianMenuItem);

		final MenuItem partynMenuItem = rankingsMenuItem.addItem(PARTY_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		partyRankingMenuItemFactory.createPartyRankingTopics(partynMenuItem);

		final MenuItem committeeMenuItem = rankingsMenuItem.addItem(COMMITTEE_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		committeeRankingMenuItemFactory.createCommitteeRankingTopics(committeeMenuItem);

		final MenuItem ministryMenuItem = rankingsMenuItem.addItem(MINISTRY_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		ministryRankingMenuItemFactory.createMinistryRankingTopics(ministryMenuItem);

		menuBar.addItem(TEST_TEXT, new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.OVERVIEW));

		menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.PAGEVISITHISTORY));

		return menuBar;
	}

}
