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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeRankingMenuItemFactory;
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

/**
 * The Class CommitteeMenuItemFactoryImpl.
 *
 * This class is responsible for creating and managing the committee menu items
 * and overview pages in the Citizen Intelligence Agency web application.
 * It provides methods to initialize the committee menu bar and generate
 * overview pages with relevant descriptions and icons.
 */
@Service
public final class CommitteeMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements CommitteeMenuItemFactory {

	/** The Constant BALLOT_DECISION_SUMMARY_TEXT. */
	private static final String BALLOT_DECISION_SUMMARY_TEXT = "Ballot Decision Summary";

	/** The Constant BALLOTS_TEXT. */
	private static final String BALLOTS_TEXT = "Ballots";

	/** The Constant COMMITTEE_RANKING_TEXT. */
	private static final String COMMITTEE_RANKING_TEXT = "Committee Ranking";

	/** The Constant CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_MEMBERS_TEXT = "Current Members";

	/** The Constant DECISION_SUMMARY_TEXT. */
	private static final String DECISION_SUMMARY_TEXT = "Decision Summary";

	/** The Constant DECISION_TYPE_DAILY_SUMMARY_TEXT. */
	private static final String DECISION_TYPE_DAILY_SUMMARY_TEXT = "Decision Type Daily Summary";

	/** The Constant DOCUMENT_ACTIVITY_TEXT. */
	private static final String DOCUMENT_ACTIVITY_TEXT = "Document Activity";

	/** The Constant DOCUMENT_HISTORY_TEXT. */
	private static final String DOCUMENT_HISTORY_TEXT = "Document history";

	/** The Constant DOCUMENTS_TEXT. */
	private static final String DOCUMENTS_TEXT = "Documents";

	/** The Constant MEMBER_HISTORY_TEXT. */
	private static final String MEMBER_HISTORY_TEXT = "Member History";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The Constant ROLE_GHANT_TEXT. */
	private static final String ROLE_GHANT_TEXT = "RoleGhant";

	/** The Constant ROLES_TEXT. */
	private static final String ROLES_TEXT = "Roles";

	/** The Constant CURRENT_MEMBERS_DESCRIPTION. */
	private static final String CURRENT_MEMBERS_DESCRIPTION = "Current roles and days served";

	/** The Constant MEMBER_HISTORY_DESCRIPTION. */
	private static final String MEMBER_HISTORY_DESCRIPTION = "History of all roles and days served";

	/** The Constant ROLE_GHANT_DESCRIPTION. */
	private static final String ROLE_GHANT_DESCRIPTION = "Gantt chart of all roles over time";

	/** The Constant DOCUMENT_ACTIVITY_DESCRIPTION. */
	private static final String DOCUMENT_ACTIVITY_DESCRIPTION = "Chart of document activity by document type.";

	/** The Constant DOCUMENT_HISTORY_DESCRIPTION. */
	private static final String DOCUMENT_HISTORY_DESCRIPTION = "Document history list";

	/** The Constant BALLOT_DECISION_SUMMARY_DESCRIPTION. */
	private static final String BALLOT_DECISION_SUMMARY_DESCRIPTION = "Summary of all ballot decisions";

	/** The Constant DECISION_SUMMARY_DESCRIPTION. */
	private static final String DECISION_SUMMARY_DESCRIPTION = "Summary of all ballots";

	/** The Constant DECISION_TYPE_DAILY_SUMMARY_DESCRIPTION. */
	private static final String DECISION_TYPE_DAILY_SUMMARY_DESCRIPTION = "Chart over decisions by decisions type, daily summary";

	/** The Constant DECISION_FLOW_DESCRIPTION. */
	private static final String DECISION_FLOW_DESCRIPTION = "Decision flow chart";

	/** The Constant PAGE_VISIT_HISTORY_DESCRIPTION. */
	private static final String PAGE_VISIT_HISTORY_DESCRIPTION = "View history of page visit for this page.";

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

		committeeRankingMenuItemFactory.createCommitteeRankingTopics(menuBar.addItem(COMMITTEE_RANKING_TEXT, VaadinIcons.GROUP, null));

		final MenuItem committeeItem = menuBar.addItem("Committee "+ pageId, VaadinIcons.GROUP,null);


		committeeItem.addItem(OVERVIEW_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.OVERVIEW, pageId));

		final MenuItem rolesItem = committeeItem.addItem(ROLES_TEXT, VaadinIcons.GROUP, null);

		rolesItem.addItem(CURRENT_MEMBERS_TEXT, VaadinIcons.USER, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.CURRENT_MEMBERS.toString(), pageId));

		rolesItem.addItem(MEMBER_HISTORY_TEXT, VaadinIcons.CALENDAR_USER, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.MEMBERHISTORY.toString(), pageId));

		rolesItem.addItem(ROLE_GHANT_TEXT, VaadinIcons.GANTT_CHART,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.ROLEGHANT.toString(), pageId));

		final MenuItem documentItem = committeeItem.addItem(DOCUMENTS_TEXT, VaadinIcons.FILE_TEXT, null);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE_PROCESS, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENTACTIVITY.toString(), pageId));

		documentItem.addItem(DOCUMENT_HISTORY_TEXT, VaadinIcons.FILE_TREE, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENT_HISTORY.toString(), pageId));

		final MenuItem ballotItem = committeeItem.addItem(BALLOTS_TEXT, VaadinIcons.CLIPBOARD_TEXT, null);

		ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_CHECK, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.BALLOTDECISIONSUMMARY.toString(), pageId));

		ballotItem.addItem(DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_PULSE, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DECISIONSUMMARY.toString(), pageId));

		ballotItem.addItem(DECISION_TYPE_DAILY_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_TEXT, new PageModeMenuCommand(
				UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString(), pageId));

		ballotItem.addItem("Decision flow", VaadinIcons.FLOW_CHART, new PageModeMenuCommand(
				UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS+"/"+ ChartIndicators.DECISION_FLOW_CHART, pageId));


		committeeItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CLOCK,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId));

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid,CURRENT_MEMBERS_TEXT, VaadinIcons.USER, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.CURRENT_MEMBERS.toString(), pageId), CURRENT_MEMBERS_DESCRIPTION);

		createButtonLink(grid,MEMBER_HISTORY_TEXT, VaadinIcons.CALENDAR_USER, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.MEMBERHISTORY.toString(), pageId), MEMBER_HISTORY_DESCRIPTION);

		createButtonLink(grid,ROLE_GHANT_TEXT, VaadinIcons.GANTT_CHART,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.ROLEGHANT.toString(), pageId), ROLE_GHANT_DESCRIPTION);


		createButtonLink(grid,DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE_PROCESS, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENTACTIVITY.toString(), pageId), DOCUMENT_ACTIVITY_DESCRIPTION);

		createButtonLink(grid,DOCUMENT_HISTORY_TEXT, VaadinIcons.FILE_TREE, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENT_HISTORY.toString(), pageId), DOCUMENT_HISTORY_DESCRIPTION);


		createButtonLink(grid,BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_CHECK, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.BALLOTDECISIONSUMMARY.toString(), pageId), BALLOT_DECISION_SUMMARY_DESCRIPTION);

		createButtonLink(grid,DECISION_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_PULSE, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DECISIONSUMMARY.toString(), pageId), DECISION_SUMMARY_DESCRIPTION);

		createButtonLink(grid,DECISION_TYPE_DAILY_SUMMARY_TEXT, VaadinIcons.CLIPBOARD_TEXT, new PageModeMenuCommand(
				UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString(), pageId), DECISION_TYPE_DAILY_SUMMARY_DESCRIPTION);

		createButtonLink(grid,"Decision flow", VaadinIcons.FLOW_CHART, new PageModeMenuCommand(
				UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS+"/"+ ChartIndicators.DECISION_FLOW_CHART, pageId), DECISION_FLOW_DESCRIPTION);


		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CLOCK,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId), PAGE_VISIT_HISTORY_DESCRIPTION);


	}


}
