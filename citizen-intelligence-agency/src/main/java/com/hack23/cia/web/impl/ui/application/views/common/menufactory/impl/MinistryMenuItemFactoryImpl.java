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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class MenuItemFactoryImpl.
 */
@Service
public final class MinistryMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements MinistryMenuItemFactory {

	/** The Constant GOVERNMENT_BODIES. */
	private static final String GOVERNMENT_BODIES = "Government bodies";

	/** The Constant MINISTRY_RANKING. */
	private static final String MINISTRY_RANKING = "Ministry Ranking";

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

	/** The Constant DOCUMENT_ACTIVITY_TEXT. */
	private static final String DOCUMENT_ACTIVITY_TEXT = "Document Activity";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

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

		ministryRankingMenuItemFactory.createMinistryRankingTopics(menuBar.addItem(MINISTRY_RANKING, FontAwesome.GROUP,null));

		final MenuItem ministryItem = menuBar.addItem("Ministry "+ pageId, FontAwesome.GROUP,null);


		ministryItem.addItem(OVERVIEW_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW, pageId));
		ministryItem.addItem(CHARTS_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.CHARTS, pageId));
		ministryItem.addItem(INDICATORS_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.INDICATORS, pageId));

		final MenuItem rolesItem = ministryItem.addItem(ROLES_TEXT, FontAwesome.GROUP, null);

		rolesItem.addItem(CURRENT_MEMBERS_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.CURRENTMEMBERS.toString(), pageId));

		rolesItem.addItem(MEMBER_HISTORY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.MEMBERHISTORY.toString(), pageId));

		rolesItem.addItem(ROLE_GHANT_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.ROLEGHANT.toString(), pageId));

		rolesItem.addItem(GOVERNMENT_BODIES, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES.toString(), pageId));



		final MenuItem documentItem = ministryItem.addItem(DOCUMENTS_TEXT, FontAwesome.GROUP, null);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTACTIVITY.toString(), pageId));

		documentItem.addItem(DOCUMENT_HISTORY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTHISTORY.toString(), pageId));

		ministryItem.addItem(PAGE_VISIT_HISTORY_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId));

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final GridLayout grid = createGridLayout(panelContent);

		createButtonLink(grid,OVERVIEW_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW, pageId), "Default description");
		createButtonLink(grid,CHARTS_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.CHARTS, pageId), "Default description");
		createButtonLink(grid,INDICATORS_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.INDICATORS, pageId), "Default description");

		createButtonLink(grid,CURRENT_MEMBERS_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.CURRENTMEMBERS.toString(), pageId), "Default description");

		createButtonLink(grid,MEMBER_HISTORY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.MEMBERHISTORY.toString(), pageId), "Default description");

		createButtonLink(grid,ROLE_GHANT_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.ROLEGHANT.toString(), pageId), "Default description");

		createButtonLink(grid,GOVERNMENT_BODIES, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES.toString(), pageId), "Default description");


		createButtonLink(grid,DOCUMENT_ACTIVITY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTACTIVITY.toString(), pageId), "Default description");

		createButtonLink(grid,DOCUMENT_HISTORY_TEXT, FontAwesome.GROUP, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
				MinistryPageMode.DOCUMENTHISTORY.toString(), pageId), "Default description");

		createButtonLink(grid,PAGE_VISIT_HISTORY_TEXT, FontAwesome.GROUP,
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY, pageId), "Default description");


	}

}
