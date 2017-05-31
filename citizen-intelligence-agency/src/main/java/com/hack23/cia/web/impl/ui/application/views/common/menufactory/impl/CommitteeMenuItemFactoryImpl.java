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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class CommitteeMenuItemFactoryImpl.
 */
@Service
public final class CommitteeMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements CommitteeMenuItemFactory {

	/** The Constant COMMITTEE_RANKING_TEXT. */
	private static final String COMMITTEE_RANKING_TEXT = "Committee Ranking";

	/** The Constant DOCUMENT_HISTORY_TEXT. */
	private static final String DOCUMENT_HISTORY_TEXT = "Document history";

	/** The Constant INDICATORS_TEXT. */
	private static final String INDICATORS_TEXT = "Indicators";

	/** The Constant CHARTS_TEXT. */
	private static final String CHARTS_TEXT = "Charts";

	/** The Constant ROLE_GHANT_TEXT. */
	private static final String ROLE_GHANT_TEXT = "RoleGhant";

	/** The Constant MEMBER_HISTORY_TEXT. */
	private static final String MEMBER_HISTORY_TEXT = "Member History";

	/** The Constant CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_MEMBERS_TEXT = "Current Members";

	/** The Constant ROLES_TEXT. */
	private static final String ROLES_TEXT = "Roles";

	/** The Constant DOCUMENTS_TEXT. */
	private static final String DOCUMENTS_TEXT = "Documents";

	/** The Constant DECISION_TYPE_DAILY_SUMMARY_TEXT. */
	private static final String DECISION_TYPE_DAILY_SUMMARY_TEXT = "Decision Type Daily Summary";

	/** The Constant DECISION_SUMMARY_TEXT. */
	private static final String DECISION_SUMMARY_TEXT = "Decision Summary";

	/** The Constant BALLOT_DECISION_SUMMARY_TEXT. */
	private static final String BALLOT_DECISION_SUMMARY_TEXT = "Ballot Decision Summary";

	/** The Constant BALLOTS_TEXT. */
	private static final String BALLOTS_TEXT = "Ballots";

	/** The Constant DOCUMENT_ACTIVITY_TEXT. */
	private static final String DOCUMENT_ACTIVITY_TEXT = "Document Activity";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The committee ranking menu item factory. */
	@Autowired
	private CommitteeRankingMenuItemFactory committeeRankingMenuItemFactory;

	/** The application menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;


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

		committeeRankingMenuItemFactory.createCommitteeRankingTopics(menuBar.addItem(COMMITTEE_RANKING_TEXT, FontAwesome.GROUP, null));

		final MenuItem committeeItem = menuBar.addItem("Committee "+ pageId, FontAwesome.GROUP,null);


		committeeItem.addItem(OVERVIEW_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.OVERVIEW, pageId));
		committeeItem.addItem(CHARTS_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS, pageId));
		committeeItem.addItem(INDICATORS_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.INDICATORS, pageId));

		final MenuItem rolesItem = committeeItem.addItem(ROLES_TEXT, FontAwesome.GROUP, null);

		rolesItem.addItem(CURRENT_MEMBERS_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.CURRENT_MEMBERS.toString(), pageId));

		rolesItem.addItem(MEMBER_HISTORY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.MEMBERHISTORY.toString(), pageId));

		rolesItem.addItem(ROLE_GHANT_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.ROLEGHANT.toString(), pageId));

		final MenuItem documentItem = committeeItem.addItem(DOCUMENTS_TEXT, FontAwesome.GROUP, null);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENTACTIVITY.toString(), pageId));

		documentItem.addItem(DOCUMENT_HISTORY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENT_HISTORY.toString(), pageId));

		final MenuItem ballotItem = committeeItem.addItem(BALLOTS_TEXT, FontAwesome.GROUP, null);

		ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.BALLOTDECISIONSUMMARY.toString(), pageId));

		ballotItem.addItem(DECISION_SUMMARY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DECISIONSUMMARY.toString(), pageId));

		ballotItem.addItem(DECISION_TYPE_DAILY_SUMMARY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(
				UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString(), pageId));

		committeeItem.addItem(PAGE_VISIT_HISTORY_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId));

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = createGridLayout(panelContent);

		createButtonLink(grid,OVERVIEW_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.OVERVIEW, pageId), "Default description");
		createButtonLink(grid,CHARTS_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS, pageId), "Default description");
		createButtonLink(grid,INDICATORS_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.INDICATORS, pageId), "Default description");


		createButtonLink(grid,CURRENT_MEMBERS_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.CURRENT_MEMBERS.toString(), pageId), "Default description");

		createButtonLink(grid,MEMBER_HISTORY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.MEMBERHISTORY.toString(), pageId), "Default description");

		createButtonLink(grid,ROLE_GHANT_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.ROLEGHANT.toString(), pageId), "Default description");


		createButtonLink(grid,DOCUMENT_ACTIVITY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENTACTIVITY.toString(), pageId), "Default description");

		createButtonLink(grid,DOCUMENT_HISTORY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DOCUMENT_HISTORY.toString(), pageId), "Default description");


		createButtonLink(grid,BALLOT_DECISION_SUMMARY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.BALLOTDECISIONSUMMARY.toString(), pageId), "Default description");

		createButtonLink(grid,DECISION_SUMMARY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
				CommitteePageMode.DECISIONSUMMARY.toString(), pageId), "Default description");

		createButtonLink(grid,DECISION_TYPE_DAILY_SUMMARY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(
				UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString(), pageId), "Default description");

		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId), "Default description");


	}


}
