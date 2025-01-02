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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.UserHomeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserHomePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class UserHomeMenuItemFactoryImpl.
 */
@Service
public final class UserHomeMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements UserHomeMenuItemFactory {

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant SECURITY_SETTING_TEXT. */
	private static final String SECURITY_SETTING_TEXT = "Security settings";

	/** The Constant USER_EVENTS. */
	private static final String USER_EVENTS = "User Events";

	/** The Constant USER_VISITS. */
	private static final String USER_VISITS = "User Visits";

	/** The Constant SECURITY_SETTINGS_DESCRIPTION. */
	private static final String SECURITY_SETTINGS_DESCRIPTION = "Security settings, enable MFA";

	/** The Constant USER_VISITS_DESCRIPTION. */
	private static final String USER_VISITS_DESCRIPTION = "All past visits";

	/** The Constant USER_EVENTS_DESCRIPTION. */
	private static final String USER_EVENTS_DESCRIPTION = "All past events";

	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;

	/**
	 * Instantiates a new user home menu item factory impl.
	 */
	public UserHomeMenuItemFactoryImpl() {
		super();
	}


	@Override
	public void createOverviewPage(final VerticalLayout overviewLayout) {
		final ResponsiveRow grid = RowUtil.createGridLayout(overviewLayout);

		createButtonLink(grid,SECURITY_SETTING_TEXT, VaadinIcons.SHIELD,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.SECURITY_SETTINGS.toString(),""),SECURITY_SETTINGS_DESCRIPTION);

		createButtonLink(grid,USER_VISITS, VaadinIcons.EYE,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.USER_VISITS.toString()),USER_VISITS_DESCRIPTION);

		createButtonLink(grid,USER_EVENTS, VaadinIcons.CALENDAR,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.USER_EVENTS.toString()),USER_EVENTS_DESCRIPTION);


	}


	@Override
	public void createUserHomeMenuBar(final MenuBar menuBar, final String pageId) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);
		final MenuItem accountItem = menuBar.addItem("Useraccount", VaadinIcons.USER,null);


		accountItem.addItem(OVERVIEW_TEXT, VaadinIcons.USER,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, PageMode.OVERVIEW, pageId));

		accountItem.addItem(SECURITY_SETTING_TEXT, VaadinIcons.SHIELD,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.SECURITY_SETTINGS.toString(), pageId));

		accountItem.addItem(USER_VISITS, VaadinIcons.EYE,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.USER_VISITS.toString(), pageId));

		accountItem.addItem(USER_EVENTS, VaadinIcons.CALENDAR,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.USER_EVENTS.toString(), pageId));


	}

}
