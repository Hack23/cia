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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryRankingMenuItemFactory;
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

	/** The Constant CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_MEMBERS_TEXT = "Current Members";

	/** The Constant DOCUMENT_ACTIVITY_TEXT. */
	private static final String DOCUMENT_ACTIVITY_TEXT = "Document Activity";

	/** The Constant DOCUMENT_HISTORY_TEXT. */
	private static final String DOCUMENT_HISTORY_TEXT = "Document history";

	/** The Constant DOCUMENTS_TEXT. */
	private static final String DOCUMENTS_TEXT = "Documents";

	/** The Constant GOVERNMENT_BODIES_EXPENDITURE. */
	private static final String GOVERNMENT_BODIES_EXPENDITURE = "Government bodies expenditure";

	/** The Constant GOVERNMENT_BODIES. */
	private static final String GOVERNMENT_BODIES_HEADCOUNT = "Government bodies headcount";

	/** The Constant GOVERNMENT_BODIES_INCOME. */
	private static final String GOVERNMENT_BODIES_INCOME = "Government bodies income";

	/** The Constant MEMBER_HISTORY_TEXT. */
	private static final String MEMBER_HISTORY_TEXT = "Member History";

	/** The Constant MINISTRY_RANKING. */
	private static final String MINISTRY_RANKING = "Ministry Ranking";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The Constant ROLE_GHANT_TEXT. */
	private static final String ROLE_GHANT_TEXT = "RoleGhant";

	/** The Constant ROLES_TEXT. */
	private static final String ROLES_TEXT = "Roles";

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

		ministryRankingMenuItemFactory.createMinistryRankingTopics(menuBar.addItem(MINISTRY_RANKING, VaadinIcons.GROUP,null));

		final MenuItem ministryItem = menuBar.addItem("Ministry "+ pageId, VaadinIcons.GROUP,null);


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
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString(), pageId));

		rolesItem.addItem(GOVERNMENT_BODIES_INCOME, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString(), pageId));

		rolesItem.addItem(GOVERNMENT_BODIES_EXPENDITURE, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString(), pageId));

		final MenuItem documentItem = ministryItem.addItem(DOCUMENTS_TEXT, VaadinIcons.GROUP, null);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTACTIVITY.toString(), pageId));

		documentItem.addItem(DOCUMENT_HISTORY_TEXT, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTHISTORY.toString(), pageId));

		ministryItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId));

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid,CURRENT_MEMBERS_TEXT, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.CURRENTMEMBERS.toString(), pageId), "Members currently holding positions");

		createButtonLink(grid,MEMBER_HISTORY_TEXT, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.MEMBERHISTORY.toString(), pageId), "Current and past members");

		createButtonLink(grid,ROLE_GHANT_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.ROLEGHANT.toString(), pageId), "Gantt chart for all the roles");

		createButtonLink(grid,GOVERNMENT_BODIES_HEADCOUNT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString(), pageId), "All government bodies that are governed by ministry");

		createButtonLink(grid,GOVERNMENT_BODIES_INCOME, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString(), pageId), "All government bodies income");

		createButtonLink(grid,GOVERNMENT_BODIES_EXPENDITURE, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString(), pageId), "All government bodies expenditure");

		createButtonLink(grid,DOCUMENT_ACTIVITY_TEXT, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTACTIVITY.toString(), pageId), "Chart over document activity");

		createButtonLink(grid,DOCUMENT_HISTORY_TEXT, VaadinIcons.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTHISTORY.toString(), pageId), "List of all document sorted by most recent");

		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, VaadinIcons.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId), "View history of page visit for this page.");


	}

}
