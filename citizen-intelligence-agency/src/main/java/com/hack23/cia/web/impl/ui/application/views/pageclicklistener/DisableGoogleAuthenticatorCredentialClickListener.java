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
import com.hack23.cia.service.api.action.user.DisableGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.service.api.action.user.DisableGoogleAuthenticatorCredentialResponse;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class DisableGoogleAuthenticatorCredentialClickListener.
 *
 * @see DisableGoogleAuthenticatorCredentialClickEvent
 */
public class DisableGoogleAuthenticatorCredentialClickListener extends AbstractClickListener implements ClickListener {

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "Error message";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DisableGoogleAuthenticatorCredentialClickListener.class);

	/** The Constant PROBLEM_ENABLE_GOOGLE_AUTHENTICATOR. */
	private static final String PROBLEM_DISABLE_GOOGLE_AUTHENTICATOR = "Problem disable google authenticator";

	/** The Constant PROBLEM_ENABLE_GOOGLE_AUTHENTICATOR_SESSIONID. */
	private static final String PROBLEM_DISABLE_GOOGLE_AUTHENTICATOR_SESSIONID = "Problem disable google authenticator, sessionid{}";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The google auth request. */
	private final DisableGoogleAuthenticatorCredentialRequest googleAuthRequest;

	/**
	 * Instantiates a new disable google authenticator credential click listener.
	 *
	 * @param googleAuthRequest
	 *            the google auth request
	 */
	public DisableGoogleAuthenticatorCredentialClickListener(
			final DisableGoogleAuthenticatorCredentialRequest googleAuthRequest) {
		this.googleAuthRequest = googleAuthRequest;
	}

	@Override
	public final void buttonClick(final ClickEvent event) {
		final DisableGoogleAuthenticatorCredentialResponse response = (DisableGoogleAuthenticatorCredentialResponse) getApplicationManager().service(googleAuthRequest);

		if (ServiceResult.FAILURE == response.getResult()) {
			showNotification(PROBLEM_DISABLE_GOOGLE_AUTHENTICATOR, ERROR_MESSAGE, Notification.Type.WARNING_MESSAGE);
			LOGGER.info(PROBLEM_DISABLE_GOOGLE_AUTHENTICATOR_SESSIONID, googleAuthRequest.getSessionId());
		}
	}
}