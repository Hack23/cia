/*
 * Copyright 2010-2024 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;

import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * The Class UiInstanceErrorHandler.
 */
public final class UiInstanceErrorHandler implements ErrorHandler {

	/** The Constant LOG_WARN_VAADIN_ERROR. */
	private static final String LOG_WARN_VAADIN_ERROR = "Vaadin error";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UiInstanceErrorHandler.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ui. */
	private final UI ui;

	/**
	 * Instantiates a new ui instance error handler.
	 *
	 * @param ui
	 *            the ui
	 */
	public UiInstanceErrorHandler(final UI ui) {
		super();
		this.ui = ui;
	}

	@Override
	public void error(final ErrorEvent event) {
		if (ExceptionUtils.getRootCause(event.getThrowable()) instanceof final AccessDeniedException accessDeniedException) {
			Notification.show(accessDeniedException.getMessage(), Notification.Type.ERROR_MESSAGE);
			ui.getNavigator().navigateTo(CommonsViews.MAIN_VIEW_NAME);
		} else {
			LOGGER.warn(LOG_WARN_VAADIN_ERROR, event.getThrowable());
		}
	}

}
