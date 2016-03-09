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

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.LogoutRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public final class LogoutClickListener implements ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutClickListener.class);

	/** The application manager. */
	private final transient ApplicationManager applicationManager;

	/** The logout request. */
	private final LogoutRequest logoutRequest;

	/**
	 * Instantiates a new logout click listener.
	 *
	 * @param logoutRequest
	 *            the logout request
	 * @param applicationManager
	 *            the application manager
	 */
	public LogoutClickListener(final LogoutRequest logoutRequest, final ApplicationManager applicationManager) {
		this.logoutRequest = logoutRequest;
		this.applicationManager = applicationManager;
	}

	/** {@inheritDoc}
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(final ClickEvent event) {
		final ServiceResponse response = applicationManager.service(logoutRequest);


		if (ServiceResult.SUCCESS.equals(response.getResult())) {
			UI.getCurrent().getNavigator().navigateTo(CommonsViews.MAIN_VIEW_NAME);
			UI.getCurrent().getSession().close();
			VaadinService.getCurrentRequest().getWrappedSession().invalidate();
		} else {
			Notification.show("Logout failed",
	                  "Error message",
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info("Logout {} failure",logoutRequest.getSessionId());
		}
	}
}