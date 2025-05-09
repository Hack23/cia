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

import com.vaadin.data.Binder;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * The Class CommitFormWrapperClickListener.
 *
 * @see CommitFormWrapperClickEvent
 */
public final class CommitFormWrapperClickListener implements ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The field group. */
	private final Binder<?> binder;

	/** The button listener. */
	private final ClickListener buttonListener;

	/**
	 * Instantiates a new commit form wrapper click listener.
	 *
	 * @param fieldGroup
	 *            the field group
	 * @param buttonListener
	 *            the button listener
	 */
	public CommitFormWrapperClickListener(final Binder<?> fieldGroup, final ClickListener buttonListener) {
		this.binder = fieldGroup;
		this.buttonListener = buttonListener;
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		binder.isValid();
		buttonListener.buttonClick(event);
	}
}