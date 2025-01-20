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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import java.util.Set;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;

/**
 * The class AbstractPageItemRendererClickListener.
 *
 * @param <T>            the generic type
 * @see AbstractPageItemRendererClickEvent
 */
public abstract class AbstractPageItemRendererClickListener<T> implements PageItemRendererClickListener<T> {

	/** The Constant PAGE_SEPARATOR. */
	private static final Character PAGE_SEPARATOR = '/';

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The page. */
	private final String page;

	/**
	 * Instantiates a new abstract page item renderer click listener.
	 *
	 * @param page
	 *            the page
	 */
	public AbstractPageItemRendererClickListener(final String page) {
		this.page = page;
	}

	@Override
	public final void click(final RendererClickEvent<T> event) {
		UI.getCurrent().getNavigator().navigateTo(page + PAGE_SEPARATOR + getPageId(event.getItem()));
	}


	/**
	 * Gets the page id.
	 *
	 * @param t
	 *            the t
	 * @return the page id
	 */
	protected abstract String getPageId(T t);


	@Override
	public final void selectionChange(final SelectionEvent<T> event) {
		final Set<T> added =event.getAllSelectedItems();

		if (!added.isEmpty()) {
			UI.getCurrent().getNavigator().navigateTo(page + PAGE_SEPARATOR + getPageId(added.iterator().next()));

		}
	}

}