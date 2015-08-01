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
package com.hack23.cia.web.impl.ui.application.views.common;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;

/**
 * The listener interface for receiving pageItemButtonClick events. The class
 * that is interested in processing a pageItemButtonClick event implements this
 * interface, and the object created with that class is registered with a
 * component using the component's
 * <code>addPageItemButtonClickListener</code> method. When
 * the pageItemButtonClick event occurs, that object's appropriate
 * method is invoked.
 *
 */
public final class PageItemButtonClickListener implements Button.ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The item. */
	private final String item;

	/** The page. */
	private final String page;

	/**
	 * Instantiates a new page item button click listener.
	 *
	 * @param page
	 *            the page
	 * @param item
	 *            the item
	 */
	public PageItemButtonClickListener(final String page, final String item) {
		this.page = page;
		this.item = item;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.
	 * ClickEvent)
	 */
	@Override
	public void buttonClick(final ClickEvent event) {
		UI.getCurrent().getNavigator().navigateTo(page + "/" + item);
	}

}