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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCommitteeConstants;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

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
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_OVERVIEW.createItemPageCommand(pageId));

		final MenuItem rolesItem = committeeItem.addItem(ROLES_TEXT, VaadinIcons.GROUP, null);

		rolesItem.addItem(CURRENT_MEMBERS_TEXT, VaadinIcons.USER,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_CURRENT_MEMBERS.createItemPageCommand(pageId));

		rolesItem.addItem(MEMBER_HISTORY_TEXT, VaadinIcons.CALENDAR_USER,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_MEMBER_HISTORY.createItemPageCommand(pageId));

		rolesItem.addItem(ROLE_GHANT_TEXT, VaadinIcons.LINE_CHART,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_ROLE_GHANT.createItemPageCommand(pageId));

		final MenuItem documentItem = committeeItem.addItem(DOCUMENTS_TEXT, VaadinIcons.FILE_TEXT, null);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE_PROCESS,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DOCUMENT_ACTIVITY.createItemPageCommand(pageId));

		documentItem.addItem(DOCUMENT_HISTORY_TEXT, VaadinIcons.FILE_TREE,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DOCUMENT_HISTORY.createItemPageCommand(pageId));

		final MenuItem ballotItem = committeeItem.addItem(BALLOTS_TEXT, VaadinIcons.CLIPBOARD_TEXT, null);

		ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_CHECK,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_BALLOT_DECISION_SUMMARY.createItemPageCommand(pageId));

		ballotItem.addItem(DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_PULSE,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DECISION_SUMMARY.createItemPageCommand(pageId));

		ballotItem.addItem(DECISION_TYPE_DAILY_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_TEXT,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DECISION_TYPE_DAILY_SUMMARY.createItemPageCommand(pageId));

		ballotItem.addItem("Decision flow", VaadinIcons.LINE_CHART,
				PageCommandCommitteeConstants.COMMAND_CHARTS_DECISION_FLOW.createItemPageCommand(pageId));

		committeeItem.addItem(RANKING_PAGE_VISIT_TEXT, VaadinIcons.CLOCK,
				COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid, CURRENT_MEMBERS_TEXT, VaadinIcons.USER,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_CURRENT_MEMBERS.createItemPageCommand(pageId),
				CURRENT_MEMBERS_DESCRIPTION);

		createButtonLink(grid, MEMBER_HISTORY_TEXT, VaadinIcons.CALENDAR_USER,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_MEMBER_HISTORY.createItemPageCommand(pageId),
				MEMBER_HISTORY_DESCRIPTION);

		createButtonLink(grid, ROLE_GHANT_TEXT, VaadinIcons.LINE_CHART,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_ROLE_GHANT.createItemPageCommand(pageId),
				ROLE_GHANT_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE_PROCESS,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DOCUMENT_ACTIVITY.createItemPageCommand(pageId),
				DOCUMENT_ACTIVITY_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_HISTORY_TEXT, VaadinIcons.FILE_TREE,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DOCUMENT_HISTORY.createItemPageCommand(pageId),
				DOCUMENT_HISTORY_DESCRIPTION);

		createButtonLink(grid, BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_CHECK,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_BALLOT_DECISION_SUMMARY.createItemPageCommand(pageId),
				BALLOT_DECISION_SUMMARY_DESCRIPTION);

		createButtonLink(grid, DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_PULSE,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DECISION_SUMMARY.createItemPageCommand(pageId),
				DECISION_SUMMARY_DESCRIPTION);

		createButtonLink(grid, DECISION_TYPE_DAILY_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_TEXT,
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DECISION_TYPE_DAILY_SUMMARY.createItemPageCommand(pageId),
				DECISION_TYPE_DAILY_SUMMARY_DESCRIPTION);

		createButtonLink(grid, "Decision flow", VaadinIcons.LINE_CHART,
				PageCommandCommitteeConstants.COMMAND_CHARTS_DECISION_FLOW.createItemPageCommand(pageId),
				DECISION_FLOW_DESCRIPTION);

		createButtonLink(grid, RANKING_PAGE_VISIT_TEXT, VaadinIcons.CHART,
				COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY, RANKING_PAGE_VISIT_DESC);

	}

}
