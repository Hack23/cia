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

import com.hack23.cia.service.api.action.admin.UpdateApplicationConfigurationRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * The Class UpdateApplicationConfigurationClickListener.
 *
 * @see com.vaadin.ui.Button.ClickEvent
 */
public class UpdateApplicationConfigurationClickListener extends AbstractClickListener implements ClickListener {

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "Error message";

	/** The Constant LOG_MSG_UPDATE_APPLICATION_CONFIGURATION_FAILURE. */
	private static final String LOG_MSG_UPDATE_APPLICATION_CONFIGURATION_FAILURE = "UpdateApplicationConfiguration {} failure";

	/** The Constant LOG_UPDATE_APPLICATION_CONFIGURATION. */
	private static final String LOG_UPDATE_APPLICATION_CONFIGURATION = "UpdateApplicationConfiguration {}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateApplicationConfigurationClickListener.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant UPDATE_APPLICATION_CONFIGURATION_FAILED. */
	private static final String UPDATE_APPLICATION_CONFIGURATION_FAILED = "Update Application Configuration failed";


	/** The request. */
	private final UpdateApplicationConfigurationRequest request;

	/**
	 * Instantiates a new update application configuration click listener.
	 *
	 * @param request
	 *            the request
	 */
	public UpdateApplicationConfigurationClickListener(final UpdateApplicationConfigurationRequest request) {
		this.request = request;
	}

	@Override
	public final void buttonClick(final ClickEvent event) {
		final ServiceResponse response = getApplicationManager().service(request);
		if (ServiceResult.SUCCESS == response.getResult()) {
			LOGGER.info(LOG_UPDATE_APPLICATION_CONFIGURATION,request.getApplicationConfigurationId());

			UI.getCurrent().getNavigator().navigateTo(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME + "/" + request.getApplicationConfigurationId());

		} else {
			showNotification(UPDATE_APPLICATION_CONFIGURATION_FAILED,
	                  ERROR_MESSAGE,
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info(LOG_MSG_UPDATE_APPLICATION_CONFIGURATION_FAILURE,request.getApplicationConfigurationId());
		}
	}
}