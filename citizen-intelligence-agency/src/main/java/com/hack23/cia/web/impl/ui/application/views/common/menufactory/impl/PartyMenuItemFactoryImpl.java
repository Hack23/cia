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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.text.MenuItemRankingPageVisitHistoryConstants;
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
public final class PartyMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements PartyMenuItemFactory {

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

		createButtonLink(grid, CURRENT_LEADERS, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTLEADERS.toString(), pageId),
				CURRENT_LEADERS_DESCRIPTION);

		createButtonLink(grid, LEADER_HISTORY, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LEADERHISTORY.toString(), pageId),
				LEADER_HISTORY_DESCRIPTION);

		createButtonLink(grid, CURRENT_MEMBERS_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.CURRENTMEMBERS.toString(), pageId),
				CURRENT_MEMBERS_DESCRIPTION);

		createButtonLink(grid, MEMBER_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MEMBERHISTORY.toString(), pageId),
				MEMBER_HISTORY_DESCRIPTION);

		createButtonLink(grid, GOVERMENT_ROLES, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.GOVERNMENTROLES.toString(), pageId),
				GOVERMENT_ROLES_DESCRIPTION);

		createButtonLink(grid, COMMITTEE_ROLES, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEROLES.toString(), pageId),
				COMMITTEE_ROLES_DESCRIPTION);

		createButtonLink(grid, ROLE_CHART_PARTY_LEADERS, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.ROLEGHANT.toString(), pageId),
				ROLE_CHART_PARTY_LEADERS_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_ACTIVITY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTACTIVITY.toString(), pageId),
				DOCUMENT_ACTIVITY_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTHISTORY.toString(), pageId),
				DOCUMENT_HISTORY_DESCRIPTION);

		createButtonLink(grid, VOTE_HISTORY, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VOTEHISTORY.toString(), pageId),
				VOTE_HISTORY);

		createButtonLink(grid, BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
						PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString(), pageId),
				BALLOT_DECISION_SUMMARY_DESCRIPTION);

		createButtonLink(grid, PARTY_WON_DAILY_SUMMARY_CHART, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
						PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString(), pageId),
				PARTY_WON_DAILY_SUMMARY_CHART_DESCRIPTION);

		createButtonLink(grid, PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString(), VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
						PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString(), pageId),
				PARTY_AGAINST_COALATIONS_SUMMARY_DESCRIPTION);

		createButtonLink(grid, PartyPageMode.PARTYSUPPORTSUMMARY.toString(), VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
						PartyPageMode.PARTYSUPPORTSUMMARY.toString(), pageId),
				PARTY_SUPPORT_SUMMARY_DESCRIPTION);

		createButtonLink(grid, RANKING_PAGE_VISIT_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId),
				MenuItemRankingPageVisitHistoryConstants.PAGE_VISIT_HISTORY_DESCRIPTION);

	}

	@Override
	public void createPartyMenuBar(final MenuBar menuBar, final String pageId) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		partyRankingMenuItemFactory.createPartyRankingTopics(menuBar.addItem(PARTY_RANKING, VaadinIcons.GROUP, null));

		final MenuItem partyItem = menuBar.addItem("Party " + pageId, VaadinIcons.GROUP, null);

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

		ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
						PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString(), pageId));

		ballotItem.addItem(PARTY_WON_DAILY_SUMMARY_CHART, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
						PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString(), pageId));

		ballotItem.addItem(PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString(), VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
						PartyPageMode.PARTYAGAINSTCOALATIONSSUMMARY.toString(), pageId));

		ballotItem.addItem(PartyPageMode.PARTYSUPPORTSUMMARY.toString(), VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
						PartyPageMode.PARTYSUPPORTSUMMARY.toString(), pageId));

		partyItem.addItem(RANKING_PAGE_VISIT_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId));

	}

}
