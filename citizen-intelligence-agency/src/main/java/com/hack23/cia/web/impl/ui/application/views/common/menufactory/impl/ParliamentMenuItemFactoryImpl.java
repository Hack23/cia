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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandParliamentRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandDocumentConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandRankingHistoryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ParliamentMenuItemFactoryImpl.
 */
@Service
public final class ParliamentMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements ParliamentMenuItemFactory {

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

		createButtonLink(grid, PARTY_WINNER, VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_WINNER,
				PARTY_WINNER_DESCRIPTION);

		createButtonLink(grid, PARTY_GENDER, VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_GENDER,
				PARTY_GENDER_DESCRIPTION);

		createButtonLink(grid, PARTY_AGE, VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_AGE, PARTY_AGE_DESCRIPTION);

		createButtonLink(grid, RISK_SUMMARY, VaadinIcons.INSTITUTION, 
			PageCommandParliamentRankingConstants.COMMAND_RISK_SUMMARY, RISK_SUMMARY_DESCRIPTION);

		createButtonLink(grid, RULE_VIOLATIONS, VaadinIcons.INSTITUTION, COMMAND_RULE_VIOLATION,
				RULE_VIOLATIONS_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_ACTIVITY_BY_TYPE, VaadinIcons.INSTITUTION, 
			PageCommandDocumentConstants.COMMAND_DOCUMENT_ACTIVITY, DOCUMENT_ACTIVITY_DESCRIPTION);
		createButtonLink(grid, DECISION_ACTIVITY_BY_TYPE, VaadinIcons.INSTITUTION, COMMAND_DECISION_ACTIVITY,
				DECISION_ACTIVITY_DESCRIPTION);

		createButtonLink(grid, DECISION_FLOW, VaadinIcons.INSTITUTION, 
			PageCommandParliamentRankingConstants.COMMAND_CHARTS_DECISION_FLOW, DECISION_FLOW_DESCRIPTION);

		createButtonLink(grid, RANKING_PAGE_VISIT_TEXT, VaadinIcons.INSTITUTION,
			PageCommandRankingHistoryConstants.PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY, RANKING_PAGE_VISIT_DESC);

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
				 COMMAND_PARLIAMENT_OVERVIEW);

		final MenuItem chartIndicators = charts.addItem(SWEDISH_PARLIAMENT_INDICATORS, VaadinIcons.INSTITUTION, null);

		final MenuItem addItem = chartIndicators.addItem(PARTY_WINNER, VaadinIcons.INSTITUTION,
				COMMAND_CHARTS_PARTY_WINNER);
		addItem.setDescription(PARTY_WINNER_DESCRIPTION);

		final MenuItem addItem2 = chartIndicators.addItem(PARTY_GENDER, VaadinIcons.INSTITUTION,
				COMMAND_CHARTS_PARTY_GENDER);
		addItem2.setDescription(PARTY_GENDER_DESCRIPTION);

		final MenuItem addItem3 = chartIndicators.addItem(PARTY_AGE, VaadinIcons.INSTITUTION, COMMAND_CHARTS_PARTY_AGE);
		addItem3.setDescription(PARTY_AGE_DESCRIPTION);

		final MenuItem addItem7 = chartIndicators.addItem(RISK_SUMMARY, VaadinIcons.INSTITUTION, 
			PageCommandParliamentRankingConstants.COMMAND_RISK_SUMMARY);
		addItem7.setDescription(RISK_SUMMARY_DESCRIPTION);

		final MenuItem addItem8 = chartIndicators.addItem(RULE_VIOLATIONS, VaadinIcons.INSTITUTION,
				COMMAND_RULE_VIOLATION);
		addItem8.setDescription(RULE_VIOLATIONS_DESCRIPTION);

		final MenuItem addItem4 = chartIndicators.addItem(DOCUMENT_ACTIVITY_BY_TYPE, VaadinIcons.INSTITUTION,
			PageCommandDocumentConstants.COMMAND_DOCUMENT_ACTIVITY);
		addItem4.setDescription(DOCUMENT_ACTIVITY_DESCRIPTION);

		final MenuItem addItem5 = chartIndicators.addItem(DECISION_ACTIVITY_BY_TYPE, VaadinIcons.INSTITUTION,
				COMMAND_DECISION_ACTIVITY);
		addItem5.setDescription(DECISION_ACTIVITY_DESCRIPTION);

		final MenuItem addItem6 = chartIndicators.addItem(DECISION_FLOW, VaadinIcons.INSTITUTION,
			PageCommandParliamentRankingConstants.COMMAND_CHARTS_DECISION_FLOW);
		addItem6.setDescription(DECISION_FLOW_DESCRIPTION);

		charts.addItem(RANKING_PAGE_VISIT_TEXT, VaadinIcons.INSTITUTION,
			PageCommandRankingHistoryConstants.PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY);
	}

}
