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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;

/**
 * The Class CommitFormWrapperClickListener.
 */
public final class CommitFormWrapperClickListener implements ClickListener {

	/** The Constant FORM_ERROR. */
	private static final String FORM_ERROR = "Form Error";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommitFormWrapperClickListener.class);

	/** The field group. */
	private final BeanFieldGroup<?> fieldGroup;

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
	public CommitFormWrapperClickListener(final BeanFieldGroup<?> fieldGroup, final ClickListener buttonListener) {
		this.fieldGroup = fieldGroup;
		this.buttonListener = buttonListener;
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		try {
			fieldGroup.commit();
		} catch (final CommitException e) {
			Notification.show(FORM_ERROR,
	                  e.getMessage(),
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.warn(FORM_ERROR,e);
		}
		buttonListener.buttonClick(event);
	}
}