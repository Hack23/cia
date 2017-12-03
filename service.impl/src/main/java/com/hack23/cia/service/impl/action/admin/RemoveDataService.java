/*
 * Copyright 2010 James Pether Sörling
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
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.admin.RemoveDataRequest;
import com.hack23.cia.service.api.action.admin.RemoveDataResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.RemoveDataManager;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;

/**
 * The Class RemoveDataService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout=1200)
@Secured({ "ROLE_ADMIN" })
public final class RemoveDataService extends
		AbstractBusinessServiceImpl<RemoveDataRequest, RemoveDataResponse>
		implements BusinessService<RemoveDataRequest, RemoveDataResponse> {

	/** The remove data manager. */
	@Autowired
	private RemoveDataManager removeDataManager;

	/** The create application event service. */
	@Autowired
	private BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	/**
	 * Instantiates a new removes the data service.
	 */
	public RemoveDataService() {
		super(RemoveDataRequest.class);
	}


	@Override
	public RemoveDataResponse processService(
			final RemoveDataRequest serviceRequest) {

		final CreateApplicationEventRequest eventRequest = new CreateApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.ADMIN);
		eventRequest.setApplicationOperation(ApplicationOperationType.DELETE);
		eventRequest.setActionName(RemoveDataRequest.class.getSimpleName() + ":" + serviceRequest.getDataType());
		eventRequest.setSessionId(serviceRequest.getSessionId());

		final UserAccount userAccount = getUserAccountFromSecurityContext();

		if (userAccount != null) {

			eventRequest.setUserId(userAccount.getUserId());
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
		
		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}

}
