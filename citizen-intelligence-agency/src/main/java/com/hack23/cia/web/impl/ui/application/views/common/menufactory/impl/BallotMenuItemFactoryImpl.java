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

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.BallotMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class BallotMenuItemFactoryImpl.
 */
@Service
public final class BallotMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements BallotMenuItemFactory {


	/** The Constant INDICATORS_TEXT. */
	private static final String INDICATORS_TEXT = "Indicators";

	/** The Constant CHARTS_TEXT. */
	private static final String CHARTS_TEXT = "Charts";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";


	/**
	 * Instantiates a new ballot menu item factory impl.
	 */
	public BallotMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createBallotMenuBar(final MenuBar menuBar, final String pageId) {
		initApplicationMenuBar(menuBar);

		menuBar.addItem(OVERVIEW_TEXT, FontAwesome.PIE_CHART,
				new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, PageMode.OVERVIEW, pageId));
		menuBar.addItem(CHARTS_TEXT, FontAwesome.PIE_CHART,
				new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, PageMode.CHARTS, pageId));
		menuBar.addItem(INDICATORS_TEXT, FontAwesome.PIE_CHART,
				new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, PageMode.INDICATORS, pageId));

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = createGridLayout(panelContent);

		createButtonLink(grid,CHARTS_TEXT, FontAwesome.PIE_CHART,
				new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, PageMode.CHARTS, pageId), "Default description");

		createButtonLink(grid,INDICATORS_TEXT, FontAwesome.PIE_CHART,
				new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, PageMode.INDICATORS, pageId), "Default description");

	}

}
