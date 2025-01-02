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

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.service.api.action.admin.RemoveDataRequest;
import com.hack23.cia.service.api.action.admin.RemoveDataResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.RemoveDataManager;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;

/**
 * The Class RemoveDataService.
 */
@Service("RemoveDataService")
@Transactional(propagation = Propagation.REQUIRED,timeout=1200)
public final class RemoveDataService extends
		AbstractBusinessServiceImpl<RemoveDataRequest, RemoveDataResponse> {

	/** The remove data manager. */
	@Autowired
	private RemoveDataManager removeDataManager;

	/**
	 * Instantiates a new removes the data service.
	 */
	public RemoveDataService() {
		super(RemoveDataRequest.class);
	}


	@Override
	@Secured({ "ROLE_ADMIN" })
	public RemoveDataResponse processService(
			final RemoveDataRequest serviceRequest) {

		final RemoveDataResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}

		final RemoveDataResponse response = new RemoveDataResponse(ServiceResult.SUCCESS);

		switch (serviceRequest.getDataType()) {
			case POLITICIAN:
				removeDataManager.removePersonData();
				break;
			case DOCUMENTS:
				removeDataManager.removeDocuments();
				removeDataManager.removeCommitteeProposals();
				removeDataManager.removeDocumentStatus();
				break;
			case APPLICATION_HISTORY:
				removeDataManager.removeApplicationHistory();
				break;
		}

		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);
		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}


	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(final RemoveDataRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.ADMIN);
		eventRequest.setApplicationOperation(ApplicationOperationType.DELETE);
		eventRequest.setActionName(RemoveDataRequest.class.getSimpleName() + ":" + serviceRequest.getDataType());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}


	@Override
	protected RemoveDataResponse createErrorResponse() {
		return new RemoveDataResponse(ServiceResult.FAILURE);
	}

}
