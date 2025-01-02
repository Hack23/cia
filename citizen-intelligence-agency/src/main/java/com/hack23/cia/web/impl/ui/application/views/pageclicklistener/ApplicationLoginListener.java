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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * The Class ApplicationLoginListener.
 */
public final class ApplicationLoginListener extends AbstractClickListener implements ClickListener {

	/** The Constant LOG_MSG_LOGIN_REQUEST. */
	private static final String LOG_MSG_LOGIN_REQUEST = "LoginRequest {}";

	/** The Constant LOG_MSG_LOGIN_REQUEST_FAILURE. */
	private static final String LOG_MSG_LOGIN_REQUEST_FAILURE = "LoginRequest {} failure";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationLoginListener.class);

	/** The Constant LOGIN_FAILED. */
	private static final String LOGIN_FAILED = "Login failed:";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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
	public void buttonClick(final ClickEvent event) {
		final LoginResponse response = (LoginResponse) getApplicationManager().service(loginRequest);
		if (ServiceResult.SUCCESS == response.getResult()) {
			LOGGER.info(LOG_MSG_LOGIN_REQUEST,loginRequest.getEmail());

			UI.getCurrent().getNavigator().navigateTo(UserViews.USERHOME_VIEW_NAME);
		} else {
			showNotification(LOGIN_FAILED,
					response.getErrorMessage(),
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info(LOG_MSG_LOGIN_REQUEST_FAILURE,loginRequest.getEmail());
		}
	}
}