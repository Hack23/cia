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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory;

import org.springframework.stereotype.Service;

import com.vaadin.ui.MenuBar;

/**
 * A factory for creating MenuItem objects.
 */
@Service
public interface MenuItemFactory {

	/**
	 * Creates a new MenuItem object.
	 * @param menuBar
	 *
	 * @return the menu bar
	 */
	MenuBar createMainPageMenuBar(MenuBar menuBar);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param barmenu
	 *            the barmenu
	 */
	void createTestTopicMenu(MenuBar barmenu);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param menuBar
	 *            the menu bar
	 * @param pageId
	 *            the page id
	 */
	void createPoliticianMenuBar(MenuBar menuBar, String pageId);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param menuBar
	 *            the menu bar
	 */
	void createPoliticianRankingMenuBar(MenuBar menuBar);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param menuBar
	 *            the menu bar
	 * @param pageId
	 *            the page id
	 */
	void createPartyMenuBar(MenuBar menuBar, String pageId);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param menuBar
	 *            the menu bar
	 */
	void createPartyRankingMenuBar(MenuBar menuBar);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param menuBar
	 *            the menu bar
	 * @param pageId
	 *            the page id
	 */
	void createDocumentMenuBar(MenuBar menuBar, String pageId);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param menuBar
	 *            the menu bar
	 * @param pageId
	 *            the page id
	 */
	void createCommitteeeMenuBar(MenuBar menuBar, String pageId);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param menuBar
	 *            the menu bar
	 */
	void createCommitteeeRankingMenuBar(MenuBar menuBar);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param menuBar
	 *            the menu bar
	 * @param pageId
	 *            the page id
	 */
	void createMinistryMenuBar(MenuBar menuBar, String pageId);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param menuBar
	 *            the menu bar
	 */
	void createMinistryRankingMenuBar(MenuBar menuBar);

	/**
	 * Creates a new MenuItem object.
	 *
	 * @param barmenu
	 *            the barmenu
	 * @param pageId
	 *            the page id
	 */
	void createUserHomeMenuBar(MenuBar barmenu, String pageId);


}
