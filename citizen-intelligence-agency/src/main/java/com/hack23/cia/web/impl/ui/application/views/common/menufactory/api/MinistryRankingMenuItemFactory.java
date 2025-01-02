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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * A factory for creating MinistryRankingMenuItem objects.
 */
public interface MinistryRankingMenuItemFactory {

	/**
	 * Creates a new MinistryRankingMenuItem object.
	 *
	 * @param menuBar
	 *            the menu bar
	 */
	void createMinistryRankingMenuBar(MenuBar menuBar);

	/**
	 * Creates a new MinistryRankingMenuItem object.
	 *
	 * @param ministryMenuItem
	 *            the ministry menu item
	 */
	void createMinistryRankingTopics(MenuItem ministryMenuItem);

	/**
	 * Creates a new MinistryRankingMenuItem object.
	 *
	 * @param panelContent
	 *            the panel content
	 */
	void createOverviewPage(VerticalLayout panelContent);


}
