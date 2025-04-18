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
import com.vaadin.ui.VerticalLayout;

/**
 * A factory for creating UserHomeMenuItem objects.
 */
public interface UserHomeMenuItemFactory {

	/**
	 * Creates a new UserHomeMenuItem object.
	 *
	 * @param overviewLayout
	 *            the overview layout
	 */
	void createOverviewPage(VerticalLayout overviewLayout);

	/**
	 * Creates a new UserHomeMenuItem object.
	 *
	 * @param barmenu
	 *            the barmenu
	 * @param pageId
	 *            the page id
	 */
	void createUserHomeMenuBar(MenuBar barmenu, String pageId);

}
