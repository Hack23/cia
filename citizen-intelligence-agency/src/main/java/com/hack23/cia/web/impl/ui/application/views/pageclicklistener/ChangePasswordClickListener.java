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

import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.ChangePasswordRequest;
import com.hack23.cia.service.api.action.user.ChangePasswordResponse;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class ChangePasswordClickListener.
 *
 * @see ChangePasswordClickEvent
 */
public class ChangePasswordClickListener extends AbstractClickListener implements ClickListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChangePasswordClickListener.class);

	/** The Constant PROBLEM_CHANGING_PASSWORD. */
	private static final String PROBLEM_CHANGING_PASSWORD = "Problem changing password";

	/** The Constant PROBLEM_CHANGING_PASSWORD_SESSIONID. */
	private static final String PROBLEM_CHANGING_PASSWORD_SESSIONID = "Problem changing password, sessionid{}";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The change password request. */
	private final ChangePasswordRequest changePasswordRequest;

	/**
	 * Instantiates a new change password click listener.
	 *
	 * @param changePasswordRequest the change password request
	 */
	public ChangePasswordClickListener(
			final ChangePasswordRequest changePasswordRequest) {
		this.changePasswordRequest = changePasswordRequest;
	}

	@Override
	public final void buttonClick(final ClickEvent event) {
		final ChangePasswordResponse response = (ChangePasswordResponse) getApplicationManager().service(changePasswordRequest);

		if (ServiceResult.FAILURE == response.getResult()) {
			showNotification(PROBLEM_CHANGING_PASSWORD, response.getErrorMessage(), Notification.Type.WARNING_MESSAGE);
			LOGGER.info(PROBLEM_CHANGING_PASSWORD_SESSIONID, changePasswordRequest.getSessionId());
		}
	}
}