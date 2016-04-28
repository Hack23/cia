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

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * The Class PartyMenuItemFactoryImpl.
 */
@Service
public final class PartyMenuItemFactoryImpl implements PartyMenuItemFactory {

	/** The Constant PARTY_WON_DAILY_SUMMARY_CHART. */
	private static final String PARTY_WON_DAILY_SUMMARY_CHART = "Party Won Daily Summary Chart";

	/** The Constant COMMITTEE_ROLES. */
	private static final String COMMITTEE_ROLES = "Committee Roles";

	/** The Constant GOVERMENT_ROLES. */
	private static final String GOVERMENT_ROLES = "Goverment Roles";

	/** The Constant LEADER_HISTORY. */
	private static final String LEADER_HISTORY = "Leader History";

	/** The Constant CURRENT_LEADERS. */
	private static final String CURRENT_LEADERS = "Current Leaders";

	/** The Constant DOCUMENT_HISTORY_TEXT. */
	private static final String DOCUMENT_HISTORY_TEXT = "Document history";

	/** The Constant INDICATORS_TEXT. */
	private static final String INDICATORS_TEXT = "Indicators";

	/** The Constant CHARTS_TEXT. */
	private static final String CHARTS_TEXT = "Charts";

	/** The Constant MEMBER_HISTORY_TEXT. */
	private static final String MEMBER_HISTORY_TEXT = "Member History";

	/** The Constant CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_MEMBERS_TEXT = "Current Members";

	/** The Constant VOTE_HISTORY. */
	private static final String VOTE_HISTORY = "Vote history";


	/** The Constant ROLES_TEXT. */
	private static final String ROLES_TEXT = "Roles";

	/** The Constant DOCUMENTS_TEXT. */
	private static final String DOCUMENTS_TEXT = "Documents";

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

	/**
	 * Instantiates a new party menu item factory impl.
	 */
	public PartyMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createPartyMenuBar(final MenuBar menuBar, final String pageId) {
		menuBar.removeItems();

		menuBar.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.OVERVIEW, pageId));
		menuBar.addItem(CHARTS_TEXT, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.CHARTS, pageId));

		menuBar.addItem(INDICATORS_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.INDICATORS, pageId));

		final MenuItem rolesItem = menuBar.addItem(ROLES_TEXT, null, null);

		rolesItem.addItem(CURRENT_LEADERS, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTLEADERS.toString(), pageId));

		rolesItem.addItem(LEADER_HISTORY, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LEADERHISTORY.toString(), pageId));

		rolesItem.addItem(CURRENT_MEMBERS_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTMEMBERS.toString(), pageId));

		rolesItem.addItem(MEMBER_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MEMBERHISTORY.toString(), pageId));

		rolesItem.addItem(GOVERMENT_ROLES, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.GOVERNMENTROLES.toString(), pageId));

		rolesItem.addItem(COMMITTEE_ROLES, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEROLES.toString(), pageId));

		final MenuItem documentItem = menuBar.addItem(DOCUMENTS_TEXT, null, null);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTACTIVITY.toString(), pageId));

		documentItem.addItem(DOCUMENT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTHISTORY.toString(), pageId));

		final MenuItem ballotItem = menuBar.addItem(BALLOTS_TEXT, null, null);

		ballotItem.addItem(VOTE_HISTORY, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VOTEHISTORY.toString(), pageId));

		ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString(), pageId));

		ballotItem.addItem(PARTY_WON_DAILY_SUMMARY_CHART, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString(), pageId));

		menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId));

	}

}
