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

import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * The Class RegisterUserClickListener.
 *
 * @see RegisterUserClickEvent
 */
public final class RegisterUserClickListener extends AbstractClickListener implements ClickListener {

	/** The Constant LOG_MSG_REGISTER_USER. */
	private static final String LOG_MSG_REGISTER_USER = "RegisterUser {}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUserClickListener.class);

	/** The Constant REGISTER_FAILED. */
	private static final String REGISTER_FAILED = "Register failed:";

	/** The Constant REGISTER_USER_FAILURE. */
	private static final String REGISTER_USER_FAILURE = "RegisterUser {} failure";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The reqister request. */
	private final RegisterUserRequest reqisterRequest;

	/**
	 * Instantiates a new register user click listener.
	 *
	 * @param reqisterRequest
	 *            the reqister request
	 */
	public RegisterUserClickListener(final RegisterUserRequest reqisterRequest) {
		this.reqisterRequest = reqisterRequest;
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		final ServiceResponse response = getApplicationManager().service(reqisterRequest);
		if (ServiceResult.SUCCESS == response.getResult()) {
			LOGGER.info(LOG_MSG_REGISTER_USER,reqisterRequest.getUsername());
			UI.getCurrent().getNavigator().navigateTo(UserViews.USERHOME_VIEW_NAME +"/" + PageMode.OVERVIEW);
		} else {
			showNotification(REGISTER_FAILED,
					response.getErrorMessage(),
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info(REGISTER_USER_FAILURE,reqisterRequest.getUsername());
		}
	}
}