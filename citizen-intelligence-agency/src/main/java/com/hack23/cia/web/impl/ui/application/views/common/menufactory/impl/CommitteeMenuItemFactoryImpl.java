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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandRankingHistoryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandCommitteeConstants;

/**
 * The Class CommitteeMenuItemFactoryImpl.
 *
 * This class is responsible for creating and managing the committee menu items
 * and overview pages in the Citizen Intelligence Agency web application.
 * It provides methods to initialize the committee menu bar and generate
 * overview pages with relevant descriptions and icons.
 */
@Service
public final class CommitteeMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements CommitteeMenuItemFactory {

	/** The application menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;

	/** The committee ranking menu item factory. */
	@Autowired
	private CommitteeRankingMenuItemFactory committeeRankingMenuItemFactory;

	/**
	 * Instantiates a new committee menu item factory impl.
	 */
	public CommitteeMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createCommitteeeMenuBar(final MenuBar menuBar, final String pageId) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		committeeRankingMenuItemFactory
				.createCommitteeRankingTopics(menuBar.addItem(COMMITTEE_RANKING_TEXT, VaadinIcons.GROUP, null));

		final MenuItem committeeItem = menuBar.addItem("Committee " + pageId, VaadinIcons.GROUP, null);

		committeeItem.addItem(OVERVIEW_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.OVERVIEW, pageId));

		final MenuItem rolesItem = committeeItem.addItem(ROLES_TEXT, VaadinIcons.GROUP, null);

		rolesItem.addItem(CURRENT_MEMBERS_TEXT, VaadinIcons.USER, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.CURRENT_MEMBERS.toString(), pageId));

		rolesItem.addItem(MEMBER_HISTORY_TEXT, VaadinIcons.CALENDAR_USER,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.MEMBERHISTORY.toString(), pageId));

		rolesItem.addItem(ROLE_GHANT_TEXT, VaadinIcons.LINE_CHART,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.ROLEGHANT.toString(), pageId));

		final MenuItem documentItem = committeeItem.addItem(DOCUMENTS_TEXT, VaadinIcons.FILE_TEXT, null);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE_PROCESS,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.DOCUMENTACTIVITY.toString(), pageId));

		documentItem.addItem(DOCUMENT_HISTORY_TEXT, VaadinIcons.FILE_TREE,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.DOCUMENT_HISTORY.toString(), pageId));

		final MenuItem ballotItem = committeeItem.addItem(BALLOTS_TEXT, VaadinIcons.CLIPBOARD_TEXT, null);

		ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_CHECK,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.BALLOTDECISIONSUMMARY.toString(), pageId));

		ballotItem.addItem(DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_PULSE,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.DECISIONSUMMARY.toString(), pageId));

		ballotItem.addItem(DECISION_TYPE_DAILY_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_TEXT, new PageModeMenuCommand(
				UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString(), pageId));

		ballotItem.addItem("Decision flow", VaadinIcons.LINE_CHART, new PageModeMenuCommand(
				UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS + "/" + ChartIndicators.DECISION_FLOW_CHART, pageId));

		committeeItem.addItem(RANKING_PAGE_VISIT_TEXT, VaadinIcons.CLOCK,
				PageCommandRankingHistoryConstants.COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid, CURRENT_MEMBERS_TEXT, VaadinIcons.USER,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.CURRENT_MEMBERS.toString(), pageId),
				CURRENT_MEMBERS_DESCRIPTION);

		createButtonLink(grid, MEMBER_HISTORY_TEXT, VaadinIcons.CALENDAR_USER,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.MEMBERHISTORY.toString(), pageId),
				MEMBER_HISTORY_DESCRIPTION);

		createButtonLink(grid, ROLE_GHANT_TEXT, VaadinIcons.LINE_CHART,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.ROLEGHANT.toString(), pageId),
				ROLE_GHANT_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE_PROCESS,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.DOCUMENTACTIVITY.toString(), pageId),
				DOCUMENT_ACTIVITY_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_HISTORY_TEXT, VaadinIcons.FILE_TREE,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.DOCUMENT_HISTORY.toString(), pageId),
				DOCUMENT_HISTORY_DESCRIPTION);

		createButtonLink(grid, BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_CHECK,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.BALLOTDECISIONSUMMARY.toString(), pageId),
				BALLOT_DECISION_SUMMARY_DESCRIPTION);

		createButtonLink(grid, DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_PULSE,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
						CommitteePageMode.DECISIONSUMMARY.toString(), pageId),
				DECISION_SUMMARY_DESCRIPTION);

		createButtonLink(grid, DECISION_TYPE_DAILY_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_TEXT, new PageModeMenuCommand(
				UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString(), pageId),
				DECISION_TYPE_DAILY_SUMMARY_DESCRIPTION);

		createButtonLink(grid, "Decision flow", VaadinIcons.LINE_CHART, new PageModeMenuCommand(
				UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS + "/" + ChartIndicators.DECISION_FLOW_CHART, pageId),
				DECISION_FLOW_DESCRIPTION);

		createButtonLink(grid, RANKING_PAGE_VISIT_TEXT, VaadinIcons.CHART,
				PageCommandRankingHistoryConstants.COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY, RANKING_PAGE_VISIT_DESC);

	}

}
