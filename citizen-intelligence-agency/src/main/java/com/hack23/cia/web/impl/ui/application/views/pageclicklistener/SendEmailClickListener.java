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

import com.hack23.cia.service.api.action.admin.SendEmailRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class SendEmailClickListener.
 *
 * @see com.vaadin.ui.Button.ClickEvent
 */
public class SendEmailClickListener extends AbstractClickListener implements ClickListener {

	public static final String EMAIL_DESC = "email desc";

	/** The Constant EMAIL_SENT. */
	public static final String EMAIL_SENT = "Email Sent";

	/** The Constant LOG_MSG_SEND_EMAIL. */
	public static final String LOG_MSG_SEND_EMAIL = "SendEmail {}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailClickListener.class);

	/** The Constant SEND_EMAIL_FAILEDFAILED. */
	public static final String SEND_EMAIL_FAILEDFAILED = "Send email failed";

	/** The Constant SEND_EMAIL_FAILURE. */
	public static final String SEND_EMAIL_FAILURE = "SendEmail {} failure";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The reqister request. */
	private final SendEmailRequest sendEmailRequest;

	/**
	 * Instantiates a new register user click listener.
	 *
	 * @param sendEmailRequest
	 *            the reqister request
	 */
	public SendEmailClickListener(final SendEmailRequest sendEmailRequest) {
		this.sendEmailRequest = sendEmailRequest;
	}

	@Override
	public final void buttonClick(final ClickEvent event) {
		final ServiceResponse response = getApplicationManager().service(sendEmailRequest);
		if (ServiceResult.SUCCESS == response.getResult()) {
			LOGGER.info(LOG_MSG_SEND_EMAIL,sendEmailRequest.getEmail());
			showNotification(EMAIL_SENT, EMAIL_DESC, Notification.Type.HUMANIZED_MESSAGE);
		} else {
			showNotification(SEND_EMAIL_FAILEDFAILED,
	                  response.getErrorMessage(),
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info(SEND_EMAIL_FAILURE,sendEmailRequest.getEmail());
		}
	}
}