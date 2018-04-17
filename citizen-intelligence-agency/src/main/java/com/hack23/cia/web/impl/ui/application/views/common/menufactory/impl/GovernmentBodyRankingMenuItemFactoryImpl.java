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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.GovernmentBodyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentBodyRankingMenuItemFactoryImpl.
 */
@Service
public final class GovernmentBodyRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
		implements GovernmentBodyRankingMenuItemFactory {

	/** The Constant COMMAND_PAGEVISITHISTORY. */
	private static final PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
			PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAN_OVERVIEW. */
	private static final PageModeMenuCommand COMMAN_OVERVIEW = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant GOVERNMENT_BODY_RANKING. */
	private static final String GOVERNMENT_BODY_RANKING = "GovernmentBody Ranking";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The application menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;

	/**
	 * Instantiates a new government body ranking menu item factory impl.
	 */
	public GovernmentBodyRankingMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createGovernmentBodyRankingMenuBar(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);

		createGovernmentBodyRankingTopics(menuBar.addItem(GOVERNMENT_BODY_RANKING, null, null));

	}

	@Override
	public void createGovernmentBodyRankingTopics(final MenuItem ministryMenuItem) {

		ministryMenuItem.addItem(OVERVIEW_TEXT, VaadinIcons.GROUP, COMMAN_OVERVIEW);

		ministryMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.GROUP, COMMAND_PAGEVISITHISTORY);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent) {
		final ResponsiveRow grid = createGridLayout(panelContent);

		createButtonLink(grid, PAGE_VISIT_HISTORY_TEXT, VaadinIcons.GROUP, COMMAND_PAGEVISITHISTORY, "View history of page visit for this page.");

	}

}
