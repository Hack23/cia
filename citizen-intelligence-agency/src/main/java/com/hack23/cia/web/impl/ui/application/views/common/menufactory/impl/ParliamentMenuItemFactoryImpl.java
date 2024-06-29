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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ParliamentMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.RiskIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ParliamentMenuItemFactoryImpl.
 */
@Service
public final class ParliamentMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements ParliamentMenuItemFactory {

	/** The Constant AVERAGE_AGE. */
	private static final String AVERAGE_AGE = "Average age";

	/** The Constant AVERAGE_PERCENTAGE_MALE. */
	private static final String AVERAGE_PERCENTAGE_MALE = "Average percentage male";

	/**
	 * The Constant
	 * CHART_DAILY_BALLOT_SUMMARY_PERCENTAGE_BALLOTS_THAT_DAY_THE_VOTED_IN_WINNING_SIDE.
	 */
	private static final String CHART_DAILY_BALLOT_SUMMARY_PERCENTAGE_BALLOTS_THAT_DAY_THE_VOTED_IN_WINNING_SIDE = "Chart daily ballot summary, percentage ballots that day the voted in winning side";

	/** The Constant CHART_DECISIONS_BY_DECISION_TYPE. */
	private static final String CHART_DECISIONS_BY_DECISION_TYPE = "Chart decisions by decision type";

	/** The Constant CHART_DOCUMENT_ACTIVITY_BY_TYPE. */
	private static final String CHART_DOCUMENT_ACTIVITY_BY_TYPE = "Chart document activity by type";

	/** The Constant CHART_PARTY_AGE_ALL_BALLOTS. */
	private static final String CHART_PARTY_AGE_ALL_BALLOTS = "Chart party age  all ballots";

	/** The Constant CHART_PARTY_AVERAGE_GENDER_ALL_BALLOTS. */
	private static final String CHART_PARTY_AVERAGE_GENDER_ALL_BALLOTS = "Chart Party average gender all ballots";

	/** The Constant COMMAND_CHARTS_DECISION_FLOW. */
	private static final PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DECISION_FLOW_CHART.toString());

	/** The Constant COMMAND_CHARTS_PARTY_AGE. */
	private static final PageModeMenuCommand COMMAND_CHARTS_PARTY_AGE = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYAGE.toString());

	/** The Constant COMMAND_CHARTS_PARTY_GENDER. */
	private static final PageModeMenuCommand COMMAND_CHARTS_PARTY_GENDER = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYGENDER.toString());

	/** The Constant COMMAND_CHARTS_PARTY_WINNER. */
	private static final PageModeMenuCommand COMMAND_CHARTS_PARTY_WINNER = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYWINNER.toString());

	/** The Constant COMMAND_DECISION_ACTIVITY. */
	private static final PageModeMenuCommand COMMAND_DECISION_ACTIVITY = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DECISIONACTIVITYBYTYPE.toString());

	/** The Constant COMMAND_DOCUMENT_ACTIVITY. */
	private static final PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DOCUMENTACTIVITYBYTYPE.toString());

	/** The Constant COMMAND_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The Constant COMMAND_PAGEVISITHISTORY. */
	private static final PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAND_RISK_SUMMARY. */
	private static final PageModeMenuCommand COMMAND_RISK_SUMMARY = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.RULES, RiskIndicators.RISK_SUMMARY.toString());

	/** The Constant COMMAND_RULE_VIOLATION. */
	private static final PageModeMenuCommand COMMAND_RULE_VIOLATION = new PageModeMenuCommand(
			UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.RULES, RiskIndicators.RULE_VIOLATIONS.toString());

	/** The Constant DAILY_AVERAGE_WON_BALLOTS. */
	private static final String DAILY_AVERAGE_WON_BALLOTS = "daily average % won ballots";

	/** The Constant DAILY_TOTAL_OF_NUMBER_OF_DECSIONS_MADE. */
	private static final String DAILY_TOTAL_OF_NUMBER_OF_DECSIONS_MADE = "daily total of number of decsions made";

	/** The Constant DAILY_TOTAL_OF_NUMBER_PUBLISHED_DOCUMENTS. */
	private static final String DAILY_TOTAL_OF_NUMBER_PUBLISHED_DOCUMENTS = "daily total of number published documents";

	/** The Constant DECISION_ACTIVITY_BY_TYPE. */
	private static final String DECISION_ACTIVITY_BY_TYPE = "Decision activity by type";

	/** The Constant DECISION_FLOW. */
	private static final String DECISION_FLOW = "Decision flow";

	/** The Constant DECISION_FLOW_DESCRIPTION. */
	private static final String DECISION_FLOW_DESCRIPTION = "Decision flow description";

	/** The Constant DOCUMENT_ACTIVITY_BY_TYPE. */
	private static final String DOCUMENT_ACTIVITY_BY_TYPE = "Document activity by type";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The Constant PARLIAMENT_RANKING_TEXT. */
	private static final String PARLIAMENT_RANKING_TEXT = "Parliament Ranking";

	/** The Constant PARTY_AGE. */
	private static final String PARTY_AGE = "Party Age";

	/** The Constant PARTY_GENDER. */
	private static final String PARTY_GENDER = "Party Gender";

	/** The Constant PARTY_WINNER. */
	private static final String PARTY_WINNER = "Party Winner";

	/** The Constant RISK_SUMMARY. */
	private static final String RISK_SUMMARY = "Risk Summary";

	/** The Constant RULE_VIOLATIONS. */
	private static final String RULE_VIOLATIONS = "Rule Violations";

	/** The Constant SWEDISH_PARLIAMENT_INDICATORS. */
	private static final String SWEDISH_PARLIAMENT_INDICATORS = "Swedish parliament Indicators";

	/** The Constant VIEW_HISTORY_OF_PAGE_VISIT_FOR_THIS_PAGE. */
	private static final String VIEW_HISTORY_OF_PAGE_VISIT_FOR_THIS_PAGE = "View history of page visit for this page.";

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
	public void createOverviewPage(final VerticalLayout panelContent) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid,PARTY_WINNER,VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_WINNER, CHART_DAILY_BALLOT_SUMMARY_PERCENTAGE_BALLOTS_THAT_DAY_THE_VOTED_IN_WINNING_SIDE);

		createButtonLink(grid,PARTY_GENDER,VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_GENDER, CHART_PARTY_AVERAGE_GENDER_ALL_BALLOTS);

		createButtonLink(grid,PARTY_AGE, VaadinIcons.INSTITUTION,COMMAND_CHARTS_PARTY_AGE, CHART_PARTY_AGE_ALL_BALLOTS);

		createButtonLink(grid,RISK_SUMMARY, VaadinIcons.INSTITUTION,COMMAND_RISK_SUMMARY,RISK_SUMMARY);

		createButtonLink(grid,RULE_VIOLATIONS, VaadinIcons.INSTITUTION,COMMAND_RULE_VIOLATION,RULE_VIOLATIONS);

		createButtonLink(grid,DOCUMENT_ACTIVITY_BY_TYPE,VaadinIcons.INSTITUTION, COMMAND_DOCUMENT_ACTIVITY, CHART_DOCUMENT_ACTIVITY_BY_TYPE);
		createButtonLink(grid,DECISION_ACTIVITY_BY_TYPE, VaadinIcons.INSTITUTION,COMMAND_DECISION_ACTIVITY, CHART_DECISIONS_BY_DECISION_TYPE);

		createButtonLink(grid,DECISION_FLOW, VaadinIcons.INSTITUTION,COMMAND_CHARTS_DECISION_FLOW,DECISION_FLOW_DESCRIPTION);


		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, VaadinIcons.INSTITUTION,
				COMMAND_PAGEVISITHISTORY, VIEW_HISTORY_OF_PAGE_VISIT_FOR_THIS_PAGE);

	}

	@Override
	public void createParliamentTopicMenu(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		createParliamentTopicMenu(menuBar.addItem(PARLIAMENT_RANKING_TEXT, VaadinIcons.INSTITUTION, null));

	}

	@Override
	public void createParliamentTopicMenu(final MenuItem charts) {
		charts.addItem(OVERVIEW_TEXT, VaadinIcons.INSTITUTION,
				COMMAND_OVERVIEW);

		final MenuItem chartIndicators = charts.addItem(SWEDISH_PARLIAMENT_INDICATORS, VaadinIcons.INSTITUTION, null);

		final MenuItem addItem = chartIndicators.addItem(PARTY_WINNER,VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_WINNER);
		addItem.setDescription(DAILY_AVERAGE_WON_BALLOTS);

		final MenuItem addItem2 = chartIndicators.addItem(PARTY_GENDER,VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_GENDER);
		addItem2.setDescription(AVERAGE_PERCENTAGE_MALE);

		final MenuItem addItem3 = chartIndicators.addItem(PARTY_AGE, VaadinIcons.INSTITUTION,COMMAND_CHARTS_PARTY_AGE);
		addItem3.setDescription(AVERAGE_AGE);

		final MenuItem addItem7 = chartIndicators.addItem(RISK_SUMMARY, VaadinIcons.INSTITUTION,COMMAND_RISK_SUMMARY);
		addItem7.setDescription(RISK_SUMMARY);

		final MenuItem addItem8 = chartIndicators.addItem(RULE_VIOLATIONS, VaadinIcons.INSTITUTION,COMMAND_RULE_VIOLATION);
		addItem8.setDescription(RULE_VIOLATIONS);


		final MenuItem addItem4 = chartIndicators.addItem(DOCUMENT_ACTIVITY_BY_TYPE,VaadinIcons.INSTITUTION, COMMAND_DOCUMENT_ACTIVITY);
		addItem4.setDescription(DAILY_TOTAL_OF_NUMBER_PUBLISHED_DOCUMENTS);

		final MenuItem addItem5 = chartIndicators.addItem(DECISION_ACTIVITY_BY_TYPE, VaadinIcons.INSTITUTION,COMMAND_DECISION_ACTIVITY);
		addItem5.setDescription(DAILY_TOTAL_OF_NUMBER_OF_DECSIONS_MADE);

		final MenuItem addItem6 = chartIndicators.addItem(DECISION_FLOW, VaadinIcons.INSTITUTION,COMMAND_CHARTS_DECISION_FLOW);
		addItem6.setDescription(DECISION_FLOW_DESCRIPTION);

		charts.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.INSTITUTION,
				COMMAND_PAGEVISITHISTORY);
	}


}
