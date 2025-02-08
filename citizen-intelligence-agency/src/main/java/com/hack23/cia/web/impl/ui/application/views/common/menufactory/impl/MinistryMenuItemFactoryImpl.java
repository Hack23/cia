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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMinistryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
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
				PageCommandMinistryConstants.COMMAND_MINISTRY_OVERVIEW.createItemPageCommand(pageId));
		final MenuItem rolesItem = ministryItem.addItem(ROLES_TEXT, VaadinIcons.GROUP, null);

		rolesItem.addItem(CURRENT_MEMBERS_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_CURRENT_MEMBERS.createItemPageCommand(pageId));

		rolesItem.addItem(MEMBER_HISTORY_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_MEMBER_HISTORY.createItemPageCommand(pageId));

		rolesItem.addItem(ROLE_GHANT_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_ROLE_GHANT.createItemPageCommand(pageId));

		rolesItem.addItem(GOVERNMENT_BODIES_HEADCOUNT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_HEADCOUNT.createItemPageCommand(pageId));

		rolesItem.addItem(GOVERNMENT_BODIES_INCOME, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_INCOME.createItemPageCommand(pageId));

		rolesItem.addItem(GOVERNMENT_BODIES_EXPENDITURE, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_EXPENDITURE.createItemPageCommand(pageId));

		final MenuItem documentItem = ministryItem.addItem(DOCUMENTS_TEXT, VaadinIcons.GROUP, null);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_DOCUMENT_ACTIVITY.createItemPageCommand(pageId));

		documentItem.addItem(DOCUMENT_HISTORY_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_DOCUMENT_HISTORY.createItemPageCommand(pageId));

		ministryItem.addItem(RANKING_PAGE_VISIT_TEXT, VaadinIcons.GROUP,
				MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid, CURRENT_MEMBERS_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_CURRENT_MEMBERS.createItemPageCommand(pageId),
				CURRENT_MEMBERS_DESCRIPTION);

		createButtonLink(grid, MEMBER_HISTORY_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_MEMBER_HISTORY.createItemPageCommand(pageId),
				MEMBER_HISTORY_DESCRIPTION);

		createButtonLink(grid, ROLE_GHANT_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_ROLE_GHANT.createItemPageCommand(pageId),
				ROLE_GHANT_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODIES_HEADCOUNT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_HEADCOUNT.createItemPageCommand(pageId),
				GOVERNMENT_BODIES_HEADCOUNT_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODIES_INCOME, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_INCOME.createItemPageCommand(pageId),
				GOVERNMENT_BODIES_INCOME_DESCRIPTION);

		createButtonLink(grid, GOVERNMENT_BODIES_EXPENDITURE, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_GOVERNMENT_BODIES_EXPENDITURE.createItemPageCommand(pageId),
				GOVERNMENT_BODIES_EXPENDITURE_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_ACTIVITY_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_DOCUMENT_ACTIVITY.createItemPageCommand(pageId),
				DOCUMENT_ACTIVITY_DESCRIPTION);

		createButtonLink(grid, DOCUMENT_HISTORY_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_DOCUMENT_HISTORY.createItemPageCommand(pageId),
				DOCUMENT_HISTORY_DESCRIPTION);

		createButtonLink(grid, RANKING_PAGE_VISIT_TEXT, VaadinIcons.GROUP,
				PageCommandMinistryConstants.COMMAND_MINISTRY_PAGEVISIT_HISTORY.createItemPageCommand(pageId),
				RANKING_PAGE_VISIT_DESC);

	}
}
