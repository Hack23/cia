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

import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.LoginForm.LoginListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * The Class ApplicationLoginListener.
 */
public final class ApplicationLoginListener implements LoginListener {

	/** The Constant LOG_MSG_LOGIN_REQUEST_FAILURE. */
	private static final String LOG_MSG_LOGIN_REQUEST_FAILURE = "LoginRequest {} failure";

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "Error message";

	/** The Constant LOGIN_FAILED. */
	private static final String LOGIN_FAILED = "Login failed";

	/** The Constant LOG_MSG_LOGIN_REQUEST. */
	private static final String LOG_MSG_LOGIN_REQUEST = "LoginRequest {}";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationLoginListener.class);

	/** The login request. */
	private final LoginRequest loginRequest;

	/**
	 * Instantiates a new application login listener.
	 *
	 * @param loginRequest
	 *            the login request
	 */
	public ApplicationLoginListener(final LoginRequest loginRequest) {
		this.loginRequest = loginRequest;
	}


	@Override
	public void onLogin(final LoginEvent event) {
		loginRequest.setEmail(event.getLoginParameter("username"));
		loginRequest.setUserpassword(event.getLoginParameter("password"));
		loginRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

		final LoginResponse response = (LoginResponse) ApplicationMangerAccess.getApplicationManager().service(loginRequest);
		if (ServiceResult.SUCCESS == response.getResult()) {
			LOGGER.info(LOG_MSG_LOGIN_REQUEST,event.getLoginParameter("username"));

			UI.getCurrent().getNavigator().navigateTo(UserViews.USERHOME_VIEW_NAME);
		} else {
			Notification.show(LOGIN_FAILED,
	                  ERROR_MESSAGE,
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info(LOG_MSG_LOGIN_REQUEST_FAILURE,event.getLoginParameter("username"));
		}

	}
}