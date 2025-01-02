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

import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.BallotMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class BallotMenuItemFactoryImpl.
 *
 * This class is responsible for creating and managing the ballot menu items
 * and overview pages in the Citizen Intelligence Agency web application.
 * It provides methods to initialize the ballot menu bar and generate
 * overview pages with relevant descriptions and icons.
 */
@Service
public final class BallotMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements BallotMenuItemFactory {

	/** The Constant CHARTS_TEXT. */
	private static final String CHARTS_TEXT = "Charts";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant BALLOT_RESULTS_DESCRIPTION. */
	private static final String BALLOT_RESULTS_DESCRIPTION = "Ballot results: vote breakdown by party.";

	/**
	 * Instantiates a new ballot menu item factory impl.
	 */
	public BallotMenuItemFactoryImpl() {
		super();
	}

	/**
	 * Creates the ballot menu bar.
	 *
	 * @param menuBar the menu bar
	 * @param pageId the page id
	 */
	@Override
	public void createBallotMenuBar(final MenuBar menuBar, final String pageId) {
		initApplicationMenuBar(menuBar);

		menuBar.addItem(OVERVIEW_TEXT, VaadinIcons.PIE_CHART,
				new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, PageMode.OVERVIEW, pageId));
		menuBar.addItem(CHARTS_TEXT, VaadinIcons.PIE_CHART,
				new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, PageMode.CHARTS, pageId));
	}

	/**
	 * Creates the overview page.
	 *
	 * @param panelContent the panel content
	 * @param pageId the page id
	 */
	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid,CHARTS_TEXT, VaadinIcons.PIE_CHART,
				new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, PageMode.CHARTS, pageId), BALLOT_RESULTS_DESCRIPTION);
	}

}
