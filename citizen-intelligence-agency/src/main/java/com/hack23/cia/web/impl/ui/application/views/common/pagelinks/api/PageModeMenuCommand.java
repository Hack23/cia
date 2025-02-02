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
package com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api;

import org.apache.commons.lang3.StringUtils;

import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;

/**
 * The Class PageModeMenuCommand.
 */
public final class PageModeMenuCommand implements Command, ClickListener {

	/** The Constant PAGE_SEPARATOR. */
	private static final Character PAGE_SEPARATOR = '/';

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The page. */
	private final String page;

	/** The page reference. */
	private final String pageReference;


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
	 * @param pageMode
	 *            the page mode
	 * @param part
	 *            the part
	 */
	public PageModeMenuCommand(final String page, final PageMode pageMode,final String part) {
		super();
		this.page = page;
		pageReference = new StringBuilder().append(pageMode).append(PAGE_SEPARATOR).append(part).toString();
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
	public PageModeMenuCommand(final String page, final String pageMode, final String part) {
		this.page = page;
		pageReference = pageMode + PAGE_SEPARATOR + part;
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		UI.getCurrent().getNavigator().navigateTo(page + PAGE_SEPARATOR + pageReference);
	}

	/**
	 * Gets the page path.
	 *
	 * @return the page path
	 */
	public String getPagePath() {
		if (pageReference != null && !pageReference.isEmpty()) {
			return page + PAGE_SEPARATOR + pageReference;
		} else {
			return page;
		}
	}

	@Override
	public void menuSelected(final MenuItem selectedItem) {
		UI.getCurrent().getNavigator().navigateTo(page + PAGE_SEPARATOR + pageReference);
	}


	public boolean matches(final String page, final String parameters) {
		return this.page.equals(page) && StringUtils.contains(parameters, pageReference);
	}

	public boolean matchesPage(final String page,final String parameters) {
		return this.page.equals(page) && (getPageId(parameters).isEmpty() || parameters.contains(PageMode.OVERVIEW.toString()));
	}

	private String getPageId(final String parameters) {
		if (parameters != null) {
			String cleanedString = parameters;
			if (parameters.contains("[")) {
				cleanedString = cleanedString.replace(cleanedString.substring(cleanedString.indexOf('[') , cleanedString.lastIndexOf(']')+1), "");
			}

			return cleanedString.substring(cleanedString.lastIndexOf('/') + "/".length());
		} else {
			return "";
		}
	}
}