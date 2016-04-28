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

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.UserHomeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.MenuBar;

/**
 * The Class UserHomeMenuItemFactoryImpl.
 */
@Service
public final class UserHomeMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements UserHomeMenuItemFactory {

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";


	/**
	 * Instantiates a new user home menu item factory impl.
	 */
	public UserHomeMenuItemFactoryImpl() {
		super();
	}


	@Override
	public void createUserHomeMenuBar(final MenuBar menuBar, final String pageId) {
		initApplicationMenuBar(menuBar);

		menuBar.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, PageMode.OVERVIEW, pageId));

		menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, PageMode.PAGEVISITHISTORY,pageId));

	}

}
