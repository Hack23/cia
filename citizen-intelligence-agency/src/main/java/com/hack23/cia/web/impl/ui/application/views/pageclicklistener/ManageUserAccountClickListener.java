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

import com.hack23.cia.service.api.action.admin.ManageUserAccountRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class ManageUserAccountClickListener.
 *
 * @see com.vaadin.ui.Button.ClickEvent
 */
public class ManageUserAccountClickListener extends AbstractClickListener implements ClickListener {

	public static final String MANAGE_USER_ACCOUNT_DESC = "manage user account desc";

	/** The Constant LOG_MSG_SEND_EMAIL. */
	public static final String LOG_MSG = "Operation {}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ManageUserAccountClickListener.class);

	/** The Constant EMAIL_SENT. */
	public static final String OPERATION_COMPLETED = "Operation completed";

	/** The Constant SEND_EMAIL_FAILEDFAILED. */
	public static final String OPERATION_FAILED = "Operation failed";

	/** The Constant SEND_EMAIL_FAILURE. */
	public static final String OPERATION_FAILURE = "Operation {} failure";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The reqister request. */
	private final ManageUserAccountRequest manageUserAccountRequest;

	/**
	 * Instantiates a new manage user account click listener.
	 *
	 * @param manageUserAccountRequest
	 *            the manage user account request
	 */
	public ManageUserAccountClickListener(final ManageUserAccountRequest manageUserAccountRequest) {
		this.manageUserAccountRequest = manageUserAccountRequest;
	}

	@Override
	public final void buttonClick(final ClickEvent event) {
		final ServiceResponse response = getApplicationManager().service(manageUserAccountRequest);
		if (ServiceResult.SUCCESS == response.getResult()) {
			LOGGER.info(LOG_MSG,manageUserAccountRequest.getUserAcountId());
			showNotification(OPERATION_COMPLETED, MANAGE_USER_ACCOUNT_DESC, Notification.Type.HUMANIZED_MESSAGE);
		} else {
			showNotification(OPERATION_FAILED,
	                  response.getErrorMessage(),
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info(OPERATION_FAILURE,manageUserAccountRequest.getUserAcountId());
		}
	}
}