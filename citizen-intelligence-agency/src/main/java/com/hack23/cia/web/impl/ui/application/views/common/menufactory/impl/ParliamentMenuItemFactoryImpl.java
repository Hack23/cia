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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ParliamentMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * The Class ParliamentMenuItemFactoryImpl.
 */
@Service
public final class ParliamentMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements ParliamentMenuItemFactory {

	/** The Constant AVERAGE_AGE. */
	private static final String AVERAGE_AGE = "Average age";

	/** The Constant PARTY_AGE. */
	private static final String PARTY_AGE = "Party Age";

	/** The Constant AVERAGE_PERCENTAGE_MALE. */
	private static final String AVERAGE_PERCENTAGE_MALE = "Average percentage male";

	/** The Constant PARTY_GENDER. */
	private static final String PARTY_GENDER = "Party Gender";

	/** The Constant COMMAND22. */
	private static final PageModeMenuCommand COMMAND22 = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAND21. */
	private static final PageModeMenuCommand COMMAND21 = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DECISIONACTIVITYBYTYPE.toString());

	/** The Constant COMMAND20. */
	private static final PageModeMenuCommand COMMAND20 = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DOCUMENTACTIVITYBYTYPE.toString());

	/** The Constant COMMAND19. */
	private static final PageModeMenuCommand COMMAND19 = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYWINNER.toString());

	/** The Constant COMMAND23. */
	private static final PageModeMenuCommand COMMAND23 = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYGENDER.toString());

	/** The Constant COMMAND24. */
	private static final PageModeMenuCommand COMMAND24 = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYAGE.toString());


	/** The Constant COMMAND18. */
	private static final PageModeMenuCommand COMMAND18 = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The Constant DAILY_TOTAL_OF_NUMBER_OF_DECSIONS_MADE. */
	private static final String DAILY_TOTAL_OF_NUMBER_OF_DECSIONS_MADE = "daily total of number of decsions made";

	/** The Constant DECISION_ACTIVITY_BY_TYPE. */
	private static final String DECISION_ACTIVITY_BY_TYPE = "Decision activity by type";

	/** The Constant DAILY_TOTAL_OF_NUMBER_PUBLISHED_DOCUMENTS. */
	private static final String DAILY_TOTAL_OF_NUMBER_PUBLISHED_DOCUMENTS = "daily total of number published documents";

	/** The Constant DOCUMENT_ACTIVITY_BY_TYPE. */
	private static final String DOCUMENT_ACTIVITY_BY_TYPE = "Document activity by type";

	/** The Constant DAILY_AVERAGE_WON_BALLOTS. */
	private static final String DAILY_AVERAGE_WON_BALLOTS = "daily average % won ballots";

	/** The Constant PARTY_WINNER. */
	private static final String PARTY_WINNER = "Party Winner";

	/** The Constant SWEDISH_PARLIAMENT_INDICATORS. */
	private static final String SWEDISH_PARLIAMENT_INDICATORS = "Swedish parliament Indicators";

	/** The Constant PARLIAMENT_RANKING_TEXT. */
	private static final String PARLIAMENT_RANKING_TEXT = "Parliament Ranking";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The application menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;

	/**
	 * Instantiates a new parliament menu item factory impl.
	 */
	public ParliamentMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createParliamentTopicMenu(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		createParliamentTopicMenu(menuBar.addItem(PARLIAMENT_RANKING_TEXT, FontAwesome.INSTITUTION, null));

	}

	@Override
	public void createParliamentTopicMenu(final MenuItem charts) {
		charts.addItem(OVERVIEW_TEXT, FontAwesome.INSTITUTION,
				COMMAND18);

		// Submenu item with a sub-submenu
		final MenuItem chartIndicators = charts.addItem(SWEDISH_PARLIAMENT_INDICATORS, FontAwesome.INSTITUTION, null);

		final MenuItem addItem = chartIndicators.addItem(PARTY_WINNER,FontAwesome.INSTITUTION, COMMAND19);
		addItem.setDescription(DAILY_AVERAGE_WON_BALLOTS);

		final MenuItem addItem2 = chartIndicators.addItem(PARTY_GENDER,FontAwesome.INSTITUTION, COMMAND23);
		addItem2.setDescription(AVERAGE_PERCENTAGE_MALE);

		final MenuItem addItem3 = chartIndicators.addItem(PARTY_AGE, FontAwesome.INSTITUTION,COMMAND24);
		addItem3.setDescription(AVERAGE_AGE);


		final MenuItem addItem4 = chartIndicators.addItem(DOCUMENT_ACTIVITY_BY_TYPE,FontAwesome.INSTITUTION, COMMAND20);
		addItem4.setDescription(DAILY_TOTAL_OF_NUMBER_PUBLISHED_DOCUMENTS);
		final MenuItem addItem5 = chartIndicators.addItem(DECISION_ACTIVITY_BY_TYPE, FontAwesome.INSTITUTION,COMMAND21);
		addItem5.setDescription(DAILY_TOTAL_OF_NUMBER_OF_DECSIONS_MADE);


		charts.addItem(PAGE_VISIT_HISTORY_TEXT, FontAwesome.INSTITUTION,
				COMMAND22);

	}

}
