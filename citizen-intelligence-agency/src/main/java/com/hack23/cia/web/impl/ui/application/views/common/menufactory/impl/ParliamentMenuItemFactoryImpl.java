/*
 * Copyright 2010-2025 James Pether Sörling
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

	/** The Constant PARTY_WINNER_DESCRIPTION. */
	private static final String PARTY_WINNER_DESCRIPTION = "Daily average % won ballots";

	/** The Constant PARTY_GENDER_DESCRIPTION. */
	private static final String PARTY_GENDER_DESCRIPTION = "Average percentage male";

	/** The Constant PARTY_AGE_DESCRIPTION. */
	private static final String PARTY_AGE_DESCRIPTION = "Average age";

	/** The Constant RISK_SUMMARY_DESCRIPTION. */
	private static final String RISK_SUMMARY_DESCRIPTION = "Risk summary";

	/** The Constant RULE_VIOLATIONS_DESCRIPTION. */
	private static final String RULE_VIOLATIONS_DESCRIPTION = "Rule violations";

	/** The Constant DOCUMENT_ACTIVITY_DESCRIPTION. */
	private static final String DOCUMENT_ACTIVITY_DESCRIPTION = "Daily total of number published documents";

	/** The Constant DECISION_ACTIVITY_DESCRIPTION. */
	private static final String DECISION_ACTIVITY_DESCRIPTION = "Daily total of number of decisions made";

	/** The Constant PAGE_VISIT_HISTORY_DESCRIPTION. */
	private static final String PAGE_VISIT_HISTORY_DESCRIPTION = "View history of page visit for this page.";

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

		createButtonLink(grid,PARTY_WINNER,VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_WINNER, PARTY_WINNER_DESCRIPTION);

		createButtonLink(grid,PARTY_GENDER,VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_GENDER, PARTY_GENDER_DESCRIPTION);

		createButtonLink(grid,PARTY_AGE, VaadinIcons.INSTITUTION,COMMAND_CHARTS_PARTY_AGE, PARTY_AGE_DESCRIPTION);

		createButtonLink(grid,RISK_SUMMARY, VaadinIcons.INSTITUTION,COMMAND_RISK_SUMMARY,RISK_SUMMARY_DESCRIPTION);

		createButtonLink(grid,RULE_VIOLATIONS, VaadinIcons.INSTITUTION,COMMAND_RULE_VIOLATION,RULE_VIOLATIONS_DESCRIPTION);

		createButtonLink(grid,DOCUMENT_ACTIVITY_BY_TYPE,VaadinIcons.INSTITUTION, COMMAND_DOCUMENT_ACTIVITY, DOCUMENT_ACTIVITY_DESCRIPTION);
		createButtonLink(grid,DECISION_ACTIVITY_BY_TYPE, VaadinIcons.INSTITUTION,COMMAND_DECISION_ACTIVITY, DECISION_ACTIVITY_DESCRIPTION);

		createButtonLink(grid,DECISION_FLOW, VaadinIcons.INSTITUTION,COMMAND_CHARTS_DECISION_FLOW,DECISION_FLOW_DESCRIPTION);


		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, VaadinIcons.INSTITUTION,
				COMMAND_PAGEVISITHISTORY, PAGE_VISIT_HISTORY_DESCRIPTION);

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
		addItem.setDescription(PARTY_WINNER_DESCRIPTION);

		final MenuItem addItem2 = chartIndicators.addItem(PARTY_GENDER,VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_GENDER);
		addItem2.setDescription(PARTY_GENDER_DESCRIPTION);

		final MenuItem addItem3 = chartIndicators.addItem(PARTY_AGE, VaadinIcons.INSTITUTION,COMMAND_CHARTS_PARTY_AGE);
		addItem3.setDescription(PARTY_AGE_DESCRIPTION);

		final MenuItem addItem7 = chartIndicators.addItem(RISK_SUMMARY, VaadinIcons.INSTITUTION,COMMAND_RISK_SUMMARY);
		addItem7.setDescription(RISK_SUMMARY_DESCRIPTION);

		final MenuItem addItem8 = chartIndicators.addItem(RULE_VIOLATIONS, VaadinIcons.INSTITUTION,COMMAND_RULE_VIOLATION);
		addItem8.setDescription(RULE_VIOLATIONS_DESCRIPTION);


		final MenuItem addItem4 = chartIndicators.addItem(DOCUMENT_ACTIVITY_BY_TYPE,VaadinIcons.INSTITUTION, COMMAND_DOCUMENT_ACTIVITY);
		addItem4.setDescription(DOCUMENT_ACTIVITY_DESCRIPTION);

		final MenuItem addItem5 = chartIndicators.addItem(DECISION_ACTIVITY_BY_TYPE, VaadinIcons.INSTITUTION,COMMAND_DECISION_ACTIVITY);
		addItem5.setDescription(DECISION_ACTIVITY_DESCRIPTION);

		final MenuItem addItem6 = chartIndicators.addItem(DECISION_FLOW, VaadinIcons.INSTITUTION,COMMAND_CHARTS_DECISION_FLOW);
		addItem6.setDescription(DECISION_FLOW_DESCRIPTION);

		charts.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.INSTITUTION,
				COMMAND_PAGEVISITHISTORY);
	}


}
