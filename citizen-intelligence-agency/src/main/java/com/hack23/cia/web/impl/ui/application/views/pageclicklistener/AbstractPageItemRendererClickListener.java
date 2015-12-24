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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import java.util.Set;

import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickListener;

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
public abstract class AbstractPageItemRendererClickListener<T> implements RendererClickListener ,SelectionListener{

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

	/* (non-Javadoc)
	 * @see com.vaadin.ui.renderers.ClickableRenderer.RendererClickListener#click(com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final void click(final RendererClickEvent event) {
		UI.getCurrent().getNavigator().navigateTo(page + "/" + getPageId((T)event.getItemId()));
	}


	/* (non-Javadoc)
	 * @see com.vaadin.event.SelectionEvent.SelectionListener#select(com.vaadin.event.SelectionEvent)
	 */
	@Override
	public final void select(final SelectionEvent event) {
		final Set<T> added =(Set<T>) event.getAdded();

		if (!added.isEmpty()) {
			UI.getCurrent().getNavigator().navigateTo(page + "/" + getPageId(added.iterator().next()));

		}

	}

	/**
	 * Gets the page id.
	 *
	 * @param t
	 *            the t
	 * @return the page id
	 */
	protected abstract String getPageId(T t);

}