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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PartyMenuItemFactoryImpl.
 */
@Service
public final class PartyMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements PartyMenuItemFactory {

	/** The Constant BALLOT_DECISION_SUMMARY_TEXT. */
	private static final String BALLOT_DECISION_SUMMARY_TEXT = "Ballot Decision Summary";

	/** The Constant BALLOTS_TEXT. */
	private static final String BALLOTS_TEXT = "Ballots";

	/** The Constant COMMITTEE_ROLES. */
	private static final String COMMITTEE_ROLES = "Committee Roles";

	/** The Constant CURRENT_LEADERS. */
	private static final String CURRENT_LEADERS = "Current Leaders";

	/** The Constant CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_MEMBERS_TEXT = "Current Members";

	/** The Constant DOCUMENT_ACTIVITY_TEXT. */
	private static final String DOCUMENT_ACTIVITY_TEXT = "Document Activity";

	/** The Constant DOCUMENT_HISTORY_TEXT. */
	private static final String DOCUMENT_HISTORY_TEXT = "Document history";

	/** The Constant DOCUMENTS_TEXT. */
	private static final String DOCUMENTS_TEXT = "Documents";

	/** The Constant GOVERMENT_ROLES. */
	private static final String GOVERMENT_ROLES = "Goverment Roles";

	/** The Constant LEADER_HISTORY. */
	private static final String LEADER_HISTORY = "Leader History";

	/** The Constant MEMBER_HISTORY_TEXT. */
	private static final String MEMBER_HISTORY_TEXT = "Member History";


	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The Constant PARTY_RANKING. */
	private static final String PARTY_RANKING = "Party Ranking";

	/** The Constant PARTY_WON_DAILY_SUMMARY_CHART. */
	private static final String PARTY_WON_DAILY_SUMMARY_CHART = "Party Won Daily Summary Chart";

	/** The Constant ROLE_CHART_PARTY_LEADERS. */
	private static final String ROLE_CHART_PARTY_LEADERS = "Role chart, party leaders";

	/** The Constant ROLES_TEXT. */
	private static final String ROLES_TEXT = "Roles";

	/** The Constant VOTE_HISTORY. */
	private static final String VOTE_HISTORY = "Vote history";

	/** The Constant CURRENT_LEADERS_DESCRIPTION. */
	private static final String CURRENT_LEADERS_DESCRIPTION = "Current leaders";

	/** The Constant LEADER_HISTORY_DESCRIPTION. */
	private static final String LEADER_HISTORY_DESCRIPTION = "Leader history";

	/** The Constant CURRENT_MEMBERS_DESCRIPTION. */
	private static final String CURRENT_MEMBERS_DESCRIPTION = "Current members";

	/** The Constant MEMBER_HISTORY_DESCRIPTION. */
	private static final String MEMBER_HISTORY_DESCRIPTION = "Current and past members";

	/** The Constant GOVERMENT_ROLES_DESCRIPTION. */
	private static final String GOVERMENT_ROLES_DESCRIPTION = "Government roles hold";

	/** The Constant COMMITTEE_ROLES_DESCRIPTION. */
	private static final String COMMITTEE_ROLES_DESCRIPTION = "Committe roles hold";

	/** The Constant ROLE_CHART_PARTY_LEADERS_DESCRIPTION. */
	private static final String ROLE_CHART_PARTY_LEADERS_DESCRIPTION = "Gantt chart all party leaders";

	/** The Constant DOCUMENT_ACTIVITY_DESCRIPTION. */
	private static final String DOCUMENT_ACTIVITY_DESCRIPTION = "Chart over document activity by type";

	/** The Constant DOCUMENT_HISTORY_DESCRIPTION. */
	private static final String DOCUMENT_HISTORY_DESCRIPTION = "List all document history";

	/** The Constant BALLOT_DECISION_SUMMARY_DESCRIPTION. */
	private static final String BALLOT_DECISION_SUMMARY_DESCRIPTION = "Ballot decision summary";

	/** The Constant PARTY_WON_DAILY_SUMMARY_CHART_DESCRIPTION. */
	private static final String PARTY_WON_DAILY_SUMMARY_CHART_DESCRIPTION = "Chart for Party over won,absent and party rebel votes";

	/** The Constant PARTY_AGAINST_COALATIONS_SUMMARY_DESCRIPTION. */
	private static final String PARTY_AGAINST_COALATIONS_SUMMARY_DESCRIPTION = "Coalations with groups of diffrent parties aginst committe proposals ballots";

	/** The Constant PARTY_SUPPORT_SUMMARY_DESCRIPTION. */
	private static final String PARTY_SUPPORT_SUMMARY_DESCRIPTION = "Trend of agreements with other parties during ballots";

	/** The Constant PAGE_VISIT_HISTORY_DESCRIPTION. */
	private static final String PAGE_VISIT_HISTORY_DESCRIPTION = "View history of page visit for this page.";

	/** The application menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;

	/** The party ranking menu item factory. */
	@Autowired
	private PartyRankingMenuItemFactory partyRankingMenuItemFactory;

	/**
	 * Instantiates a new party menu item factory impl.
	 */
	public PartyMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid,CURRENT_LEADERS, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTLEADERS.toString(), pageId), CURRENT_LEADERS_DESCRIPTION);

		createButtonLink(grid,LEADER_HISTORY, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LEADERHISTORY.toString(), pageId), LEADER_HISTORY_DESCRIPTION);

		createButtonLink(grid,CURRENT_MEMBERS_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTMEMBERS.toString(), pageId), CURRENT_MEMBERS_DESCRIPTION);

		createButtonLink(grid,MEMBER_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MEMBERHISTORY.toString(), pageId), MEMBER_HISTORY_DESCRIPTION);

		createButtonLink(grid,GOVERMENT_ROLES, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.GOVERNMENTROLES.toString(), pageId), GOVERMENT_ROLES_DESCRIPTION);

		createButtonLink(grid,COMMITTEE_ROLES, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEROLES.toString(), pageId), COMMITTEE_ROLES_DESCRIPTION);

		createButtonLink(grid,ROLE_CHART_PARTY_LEADERS, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.ROLEGHANT.toString(), pageId), ROLE_CHART_PARTY_LEADERS_DESCRIPTION);


		createButtonLink(grid,DOCUMENT_ACTIVITY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTACTIVITY.toString(), pageId), DOCUMENT_ACTIVITY_DESCRIPTION);

		createButtonLink(grid,DOCUMENT_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTHISTORY.toString(), pageId), DOCUMENT_HISTORY_DESCRIPTION);


		createButtonLink(grid,VOTE_HISTORY, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VOTEHISTORY.toString(), pageId), VOTE_HISTORY);

		createButtonLink(grid,BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString(), pageId), BALLOT_DECISION_SUMMARY_DESCRIPTION);

		createButtonLink(grid,PARTY_WON_DAILY_SUMMARY_CHART, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString(), pageId), PARTY_WON_DAILY_SUMMARY_CHART_DESCRIPTION);


		createButtonLink(grid,PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString(), VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString(), pageId), PARTY_AGAINST_COALATIONS_SUMMARY_DESCRIPTION);

		createButtonLink(grid,PartyPageMode.PARTYSUPPORTSUMMARY.toString(), VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYSUPPORTSUMMARY.toString(), pageId), PARTY_SUPPORT_SUMMARY_DESCRIPTION);


		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId), PAGE_VISIT_HISTORY_DESCRIPTION);


	}

	@Override
	public void createPartyMenuBar(final MenuBar menuBar, final String pageId) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		partyRankingMenuItemFactory.createPartyRankingTopics(menuBar.addItem(PARTY_RANKING, VaadinIcons.GROUP,null));

		final MenuItem partyItem = menuBar.addItem("Party "+ pageId, VaadinIcons.GROUP,null);

		partyItem.addItem(OVERVIEW_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.OVERVIEW, pageId));

		final MenuItem rolesItem = partyItem.addItem(ROLES_TEXT, VaadinIcons.GROUP, null);

		rolesItem.addItem(CURRENT_LEADERS, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTLEADERS.toString(), pageId));

		rolesItem.addItem(LEADER_HISTORY, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LEADERHISTORY.toString(), pageId));

		rolesItem.addItem(CURRENT_MEMBERS_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTMEMBERS.toString(), pageId));

		rolesItem.addItem(MEMBER_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MEMBERHISTORY.toString(), pageId));

		rolesItem.addItem(GOVERMENT_ROLES, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.GOVERNMENTROLES.toString(), pageId));

		rolesItem.addItem(COMMITTEE_ROLES, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEROLES.toString(), pageId));

		rolesItem.addItem(ROLE_CHART_PARTY_LEADERS, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.ROLEGHANT.toString(), pageId));


		final MenuItem documentItem = partyItem.addItem(DOCUMENTS_TEXT, VaadinIcons.GROUP, null);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTACTIVITY.toString(), pageId));

		documentItem.addItem(DOCUMENT_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTHISTORY.toString(), pageId));

		final MenuItem ballotItem = partyItem.addItem(BALLOTS_TEXT, VaadinIcons.GROUP, null);

		ballotItem.addItem(VOTE_HISTORY, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VOTEHISTORY.toString(), pageId));

		ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString(), pageId));

		ballotItem.addItem(PARTY_WON_DAILY_SUMMARY_CHART, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString(), pageId));


		ballotItem.addItem(PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString(), VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString(), pageId));

		ballotItem.addItem(PartyPageMode.PARTYSUPPORTSUMMARY.toString(), VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
				PartyPageMode.PARTYSUPPORTSUMMARY.toString(), pageId));


		partyItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId));

	}


}
