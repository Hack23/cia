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

import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * The Class PoliticianRankingMenuItemFactoryImpl.
 */
@Service
public final class PoliticianRankingMenuItemFactoryImpl implements PoliticianRankingMenuItemFactory {

	/** The Constant POLITICIAN_RANKING. */
	private static final String POLITICIAN_RANKING = "Politician Ranking";

	/**
	 * The Constant CURRENT_AND_PAST_ASSIGNMENTS_AND_SUMMARY_EXPERIENCE_IN_DAYS.
	 */
	private static final String CURRENT_AND_PAST_ASSIGNMENTS_AND_SUMMARY_EXPERIENCE_IN_DAYS = "Current and past assignments and summary experience in days";

	/** The Constant POLITICAL_EXPERIENCE_SUMMARY. */
	private static final String POLITICAL_EXPERIENCE_SUMMARY = "Political Experience Summary";

	/** The Constant RANKING_LIST_BY_TOPIC_TEXT. */
	private static final String RANKING_LIST_BY_TOPIC_TEXT = "Ranking list by topic";

	/** The Constant CHART_BY_TOPIC_TEXT. */
	private static final String CHART_BY_TOPIC_TEXT = "Chart by topic";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/**
	 * Instantiates a new politician ranking menu item factory impl.
	 */
	public PoliticianRankingMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createPoliticianRankingMenuBar(final MenuBar menuBar) {
		menuBar.removeItems();

		createPoliticianRankingTopics(menuBar.addItem(POLITICIAN_RANKING, null, null));
	}

	@Override
	public void createPoliticianRankingTopics(final MenuItem politicianMenuItem) {
		politicianMenuItem.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		final MenuItem listByTopic = politicianMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, null, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_EXPERIENCE_SUMMARY,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID));
		listItem.setDescription(CURRENT_AND_PAST_ASSIGNMENTS_AND_SUMMARY_EXPERIENCE_IN_DAYS);

		politicianMenuItem.addItem(CHART_BY_TOPIC_TEXT, null, null);

		politicianMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));

	}

}
