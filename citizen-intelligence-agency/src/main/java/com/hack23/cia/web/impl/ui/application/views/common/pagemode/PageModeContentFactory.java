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
package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;

/**
 * A factory for creating PageModeContent objects.
 */
public interface PageModeContentFactory {

	/**
	 * Creates a new PageModeContent object.
	 *
	 * @param parameters
	 *            the parameters
	 * @param menuBar
	 *            the menu bar
	 * @param panel
	 *            the panel
	 * @return the layout
	 */
	Layout createContent(String parameters,MenuBar menuBar,Panel panel);

	/**
	 * Matches.
	 *
	 * @param page
	 *            the page
	 * @param parameters
	 *            the parameters
	 * @return true, if successful
	 */
	boolean matches(String page, String parameters);

	/**
	 * Valid reference.
	 *
	 * @param parameters the parameters
	 * @return true, if successful
	 */
	boolean validReference(String parameters);

}