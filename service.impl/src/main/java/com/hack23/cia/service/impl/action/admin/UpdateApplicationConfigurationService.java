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
package com.hack23.cia.service.impl.action.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.service.api.action.admin.UpdateApplicationConfigurationRequest;
import com.hack23.cia.service.api.action.admin.UpdateApplicationConfigurationResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationConfigurationDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;

/**
 * The Class UpdateApplicationConfigurationService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public final class UpdateApplicationConfigurationService extends
		AbstractBusinessServiceImpl<UpdateApplicationConfigurationRequest, UpdateApplicationConfigurationResponse> {

	/** The application configuration dao. */
	@Autowired
	private ApplicationConfigurationDAO applicationConfigurationDAO;

	/**
	 * Instantiates a new update application configuration service.
	 */
	public UpdateApplicationConfigurationService() {
		super(UpdateApplicationConfigurationRequest.class);
	}

	@Override
	@Secured({ "ROLE_ADMIN" })
	public UpdateApplicationConfigurationResponse processService(final UpdateApplicationConfigurationRequest serviceRequest) {

		final UpdateApplicationConfigurationResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}

		final ApplicationConfiguration applicationConfiguration = applicationConfigurationDAO
				.load(serviceRequest.getApplicationConfigurationId());

		UpdateApplicationConfigurationResponse response;

		if (applicationConfiguration != null) {

			applicationConfiguration.setConfigTitle(serviceRequest.getConfigTitle());
			applicationConfiguration.setConfigDescription(serviceRequest.getConfigDescription());
			applicationConfiguration.setComponentTitle(serviceRequest.getComponentTitle());
			applicationConfiguration.setComponentDescription(serviceRequest.getComponentDescription());
			applicationConfiguration.setPropertyValue(serviceRequest.getPropertyValue());

			applicationConfigurationDAO.persist(applicationConfiguration);

			response = new UpdateApplicationConfigurationResponse(ServiceResult.SUCCESS);
		} else {
			response = new UpdateApplicationConfigurationResponse(ServiceResult.FAILURE);
		}

		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);
		eventRequest.setElementId(serviceRequest.getApplicationConfigurationId().toString());
		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;

	}

	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(
			final UpdateApplicationConfigurationRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.ADMIN);
		eventRequest.setApplicationOperation(ApplicationOperationType.UPDATE);
		eventRequest.setActionName(UpdateApplicationConfigurationRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}

	@Override
	protected UpdateApplicationConfigurationResponse createErrorResponse() {
		return new UpdateApplicationConfigurationResponse(ServiceResult.FAILURE);
	}

}
