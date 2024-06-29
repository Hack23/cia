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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.SetGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.service.api.action.user.SetGoogleAuthenticatorCredentialResponse;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import fi.jasoft.qrcode.QRCode;

/**
 * The Class SetGoogleAuthenticatorCredentialClickListener.
 */
public final class SetGoogleAuthenticatorCredentialClickListener extends AbstractClickListener implements ClickListener {


	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "Error message";

	/** The Constant GOOGLE_AUTHENTICATOR_QR_CODE. */
	private static final String GOOGLE_AUTHENTICATOR_QR_CODE = "Google Authenticator QR code";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SetGoogleAuthenticatorCredentialClickListener.class);

	/** The Constant MODAL_WINDOW_SIZE. */
	private static final String MODAL_WINDOW_SIZE = "400px";

	/** The Constant PROBLEM_DISPLAYING_QR_CODE. */
	private static final String PROBLEM_DISPLAYING_QR_CODE = "Problem displaying qr code";

	/** The Constant PROBLEM_ENABLE_GOOGLE_AUTHENTICATOR. */
	private static final String PROBLEM_ENABLE_GOOGLE_AUTHENTICATOR = "Problem enable google authenticator";

	/** The Constant PROBLEM_ENABLE_GOOGLE_AUTHENTICATOR_SESSIONID. */
	private static final String PROBLEM_ENABLE_GOOGLE_AUTHENTICATOR_SESSIONID = "Problem enable google authenticator, sessionid{}";

	/** The Constant QR_CODE. */
	private static final String QR_CODE = "QR code";

	/** The Constant QR_CODE_IMAGE_SIZE. */
	private static final String QR_CODE_IMAGE_SIZE = "175px";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant WINDOW_POSITION. */
	private static final int WINDOW_POSITION = 100;

	/** The google auth request. */
	private final SetGoogleAuthenticatorCredentialRequest googleAuthRequest;

	/**
	 * Instantiates a new sets the google authenticator credential click
	 * listener.
	 *
	 * @param googleAuthRequest
	 *            the google auth request
	 */
	public SetGoogleAuthenticatorCredentialClickListener(final SetGoogleAuthenticatorCredentialRequest googleAuthRequest) {
		this.googleAuthRequest = googleAuthRequest;
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		final SetGoogleAuthenticatorCredentialResponse response = (SetGoogleAuthenticatorCredentialResponse) getApplicationManager().service(googleAuthRequest);

		if (ServiceResult.SUCCESS == response.getResult()) {

			try {
				final URI keyUri = new URI(response.getOtpAuthTotpURL());
				final QRCode qrCode = new QRCode(QR_CODE, keyUri.toASCIIString());
				qrCode.setHeight(QR_CODE_IMAGE_SIZE);
				qrCode.setWidth(QR_CODE_IMAGE_SIZE);

				final Window mywindow = new Window(GOOGLE_AUTHENTICATOR_QR_CODE);
				mywindow.setHeight(MODAL_WINDOW_SIZE);
				mywindow.setWidth(MODAL_WINDOW_SIZE);

				mywindow.setPositionX(WINDOW_POSITION);
				mywindow.setPositionY(WINDOW_POSITION);

				final VerticalLayout panelContent = new VerticalLayout();

				mywindow.setContent(panelContent);
				panelContent.addComponent(qrCode);

				mywindow.setModal(true);
				UI.getCurrent().addWindow(mywindow);

			} catch (final URISyntaxException e) {
				LOGGER.warn(PROBLEM_DISPLAYING_QR_CODE,e);
				showNotification(PROBLEM_DISPLAYING_QR_CODE,
		                  ERROR_MESSAGE,
		                  Notification.Type.WARNING_MESSAGE);
			}

		} else {
			showNotification(PROBLEM_ENABLE_GOOGLE_AUTHENTICATOR,
	                  ERROR_MESSAGE,
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info(PROBLEM_ENABLE_GOOGLE_AUTHENTICATOR_SESSIONID,googleAuthRequest.getSessionId());
		}
	}
}