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
import org.springframework.web.context.request.RequestContextHolder;

import com.ejt.vaadin.loginform.LoginForm.LoginEvent;
import com.ejt.vaadin.loginform.LoginForm.LoginListener;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public final class ApplicationLoginListener implements LoginListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationLoginListener.class);

	/** The application manager. */
	private final ApplicationManager applicationManager;

	/**
	 * Instantiates a new application login listener.
	 *
	 * @param applicationManager
	 *            the application manager
	 */
	public ApplicationLoginListener(final ApplicationManager applicationManager) {
		this.applicationManager = applicationManager;
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* {@inheritDoc}
	 * @see com.ejt.vaadin.loginform.LoginForm.LoginListener#onLogin(com.ejt.vaadin.loginform.LoginForm.LoginEvent)
	 */
	@Override
	public void onLogin(final LoginEvent event) {

		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(event.getUserName());
		loginRequest.setUserpassword(event.getPassword());
		loginRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

		final LoginResponse response = (LoginResponse) applicationManager.service(loginRequest);
		if (ServiceResult.SUCCESS.equals(response.getResult())) {
			LOGGER.info("LoginRequest {}",event.getUserName() );

			UI.getCurrent().getNavigator().navigateTo(UserViews.USERHOME_VIEW_NAME);
		} else {
			Notification.show("Login failed",
	                  "Error message",
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info("LoginRequest {} failure",event.getUserName() );
		}

	}
}