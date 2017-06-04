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
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.v7.ui.VerticalLayout;

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

	/** The Constant COMMAND_PAGEVISITHISTORY. */
	private static final PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAND_DECISION_ACTIVITY. */
	private static final PageModeMenuCommand COMMAND_DECISION_ACTIVITY = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DECISIONACTIVITYBYTYPE.toString());

	/** The Constant COMMAND_DOCUMENT_ACTIVITY. */
	private static final PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DOCUMENTACTIVITYBYTYPE.toString());

	/** The Constant COMMAND_CHARTS_PARTY_WINNER. */
	private static final PageModeMenuCommand COMMAND_CHARTS_PARTY_WINNER = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYWINNER.toString());

	/** The Constant COMMAND_CHARTS_PARTY_GENDER. */
	private static final PageModeMenuCommand COMMAND_CHARTS_PARTY_GENDER = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYGENDER.toString());

	/** The Constant COMMAND_CHARTS_PARTY_AGE. */
	private static final PageModeMenuCommand COMMAND_CHARTS_PARTY_AGE = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYAGE.toString());


	/** The Constant COMMAND_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW);

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
				COMMAND_OVERVIEW);

		// Submenu item with a sub-submenu
		final MenuItem chartIndicators = charts.addItem(SWEDISH_PARLIAMENT_INDICATORS, FontAwesome.INSTITUTION, null);

		final MenuItem addItem = chartIndicators.addItem(PARTY_WINNER,FontAwesome.INSTITUTION, COMMAND_CHARTS_PARTY_WINNER);
		addItem.setDescription(DAILY_AVERAGE_WON_BALLOTS);

		final MenuItem addItem2 = chartIndicators.addItem(PARTY_GENDER,FontAwesome.INSTITUTION, COMMAND_CHARTS_PARTY_GENDER);
		addItem2.setDescription(AVERAGE_PERCENTAGE_MALE);

		final MenuItem addItem3 = chartIndicators.addItem(PARTY_AGE, FontAwesome.INSTITUTION,COMMAND_CHARTS_PARTY_AGE);
		addItem3.setDescription(AVERAGE_AGE);


		final MenuItem addItem4 = chartIndicators.addItem(DOCUMENT_ACTIVITY_BY_TYPE,FontAwesome.INSTITUTION, COMMAND_DOCUMENT_ACTIVITY);
		addItem4.setDescription(DAILY_TOTAL_OF_NUMBER_PUBLISHED_DOCUMENTS);
		final MenuItem addItem5 = chartIndicators.addItem(DECISION_ACTIVITY_BY_TYPE, FontAwesome.INSTITUTION,COMMAND_DECISION_ACTIVITY);
		addItem5.setDescription(DAILY_TOTAL_OF_NUMBER_OF_DECSIONS_MADE);


		charts.addItem(PAGE_VISIT_HISTORY_TEXT, FontAwesome.INSTITUTION,
				COMMAND_PAGEVISITHISTORY);
	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final ResponsiveRow grid = createGridLayout(panelContent);

		createButtonLink(grid,PARTY_WINNER,FontAwesome.INSTITUTION, COMMAND_CHARTS_PARTY_WINNER, "Default description");

		createButtonLink(grid,PARTY_GENDER,FontAwesome.INSTITUTION, COMMAND_CHARTS_PARTY_GENDER, "Default description");

		createButtonLink(grid,PARTY_AGE, FontAwesome.INSTITUTION,COMMAND_CHARTS_PARTY_AGE, "Default description");


		createButtonLink(grid,DOCUMENT_ACTIVITY_BY_TYPE,FontAwesome.INSTITUTION, COMMAND_DOCUMENT_ACTIVITY, "Default description");
		createButtonLink(grid,DECISION_ACTIVITY_BY_TYPE, FontAwesome.INSTITUTION,COMMAND_DECISION_ACTIVITY, "Default description");


		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, FontAwesome.INSTITUTION,
				COMMAND_PAGEVISITHISTORY, "View history of page visit for this page.");

	}


}
