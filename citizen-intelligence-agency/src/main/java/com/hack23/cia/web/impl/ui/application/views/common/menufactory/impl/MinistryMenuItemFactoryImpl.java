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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandRankingHistoryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MenuItemFactoryImpl.
 */
@Service
public final class MinistryMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements MinistryMenuItemFactory {
	/** The application menu item factory. */

	/** The application menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;

	/** The ministry ranking menu item factory. */
	@Autowired
	private MinistryRankingMenuItemFactory ministryRankingMenuItemFactory;

	/**
	 * Instantiates a new ministry menu item factory impl.
	 */
	public MinistryMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createMinistryMenuBar(final MenuBar menuBar, final String pageId) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		ministryRankingMenuItemFactory
				.createMinistryRankingTopics(menuBar.addItem(MINISTRY_RANKING, VaadinIcons.GROUP, null));

		final MenuItem ministryItem = menuBar.addItem("Ministry " + pageId, VaadinIcons.GROUP, null);

		ministryItem.addItem(OVERVIEW_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW, pageId));
		final MenuItem rolesItem = ministryItem.addItem(ROLES_TEXT, VaadinIcons.GROUP, null);

		rolesItem.addItem(CURRENT_MEMBERS_TEXT, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.CURRENTMEMBERS.toString(), pageId));

		rolesItem.addItem(MEMBER_HISTORY_TEXT, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.MEMBERHISTORY.toString(), pageId));

		rolesItem.addItem(ROLE_GHANT_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.ROLEGHANT.toString(), pageId));

		rolesItem.addItem(GOVERNMENT_BODIES_HEADCOUNT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString(), pageId));

		rolesItem.addItem(GOVERNMENT_BODIES_INCOME, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString(), pageId));

		rolesItem.addItem(GOVERNMENT_BODIES_EXPENDITURE, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString(), pageId));

		final MenuItem documentItem = ministryItem.addItem(DOCUMENTS_TEXT, VaadinIcons.GROUP, null);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.DOCUMENTACTIVITY.toString(), pageId));

		documentItem.addItem(DOCUMENT_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.DOCUMENTHISTORY.toString(), pageId));

		ministryItem.addItem(RANKING_PAGE_VISIT_TEXT, VaadinIcons.GROUP,
				PageCommandRankingHistoryConstants.MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid, CURRENT_MEMBERS_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.CURRENTMEMBERS.toString(), pageId),
				CURRENT_MEMBERS_DESCRIPTION);

		createButtonLink(grid, MEMBER_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.MEMBERHISTORY.toString(), pageId),
				MEMBER_HISTORY_DESCRIPTION);

		createButtonLink(grid, ROLE_GHANT_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.ROLEGHANT.toString(), pageId),
				ROLE_GHANT_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODIES_HEADCOUNT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString(), pageId),
				GOVERNMENT_BODIES_HEADCOUNT_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODIES_INCOME, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString(), pageId),
				GOVERNMENT_BODIES_INCOME_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODIES_EXPENDITURE, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString(), pageId),
				GOVERNMENT_BODIES_EXPENDITURE_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_ACTIVITY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.DOCUMENTACTIVITY.toString(), pageId),
				DOCUMENT_ACTIVITY_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
						MinistryPageMode.DOCUMENTHISTORY.toString(), pageId),
				DOCUMENT_HISTORY_DESCRIPTION);

		createButtonLink(grid, RANKING_PAGE_VISIT_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId),
				RANKING_PAGE_VISIT_DESC);

	}
}
