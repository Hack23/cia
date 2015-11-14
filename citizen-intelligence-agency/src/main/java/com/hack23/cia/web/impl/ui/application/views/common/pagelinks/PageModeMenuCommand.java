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
package com.hack23.cia.web.impl.ui.application.views.common.pagelinks;

import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;

public final class PageModeMenuCommand implements Command {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The page reference. */
	private final String pageReference;

	/** The page. */
	private final String page;


	/**
	 * Instantiates a new page mode menu command.
	 *
	 * @param page
	 *            the page
	 * @param pageMode
	 *            the page mode
	 */
	public PageModeMenuCommand(final String page, final PageMode pageMode) {
		super();
		this.page = page;
		pageReference = pageMode.toString();
	}

	/**
	 * Instantiates a new page mode menu command.
	 *
	 * @param page
	 *            the page
	 * @param part
	 *            the part
	 */
	public PageModeMenuCommand(final String page, final String part) {
		super();
		this.page = page;
		pageReference = part;
	}


	/**
	 * Instantiates a new page mode menu command.
	 *
	 * @param page
	 *            the page
	 * @param pageMode
	 *            the page mode
	 * @param part
	 *            the part
	 */
	public PageModeMenuCommand(final String page, final PageMode pageMode,final String part) {
		super();
		this.page = page;
		pageReference = pageMode.toString() + "/" + part;
	}



	/**
	 * Instantiates a new page mode menu command.
	 *
	 * @param page
	 *            the page
	 * @param pageMode
	 *            the page mode
	 * @param part
	 *            the part
	 */
	public PageModeMenuCommand(final String page, final String pageMode, final String part) {
		this.page = page;
		pageReference = pageMode + "/" + part;
	}

	/**
	 * Gets the page path.
	 *
	 * @return the page path
	 */
	public String getPagePath() {
		return page + "/" + pageReference;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.MenuBar.Command#menuSelected(com.vaadin.ui.MenuBar.MenuItem)
	 */
	@Override
	public void menuSelected(final MenuItem selectedItem) {
		UI.getCurrent().getNavigator().navigateTo(page + "/" + pageReference);
	}

}