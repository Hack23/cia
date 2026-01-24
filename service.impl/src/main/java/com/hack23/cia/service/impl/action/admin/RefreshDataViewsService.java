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
import com.hack23.cia.service.api.action.admin.RefreshDataViewsRequest;
import com.hack23.cia.service.api.action.admin.RefreshDataViewsResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ViewDataManager;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;

/**
 * The Class RefreshDataViewsService.
 */
@Service
public final class RefreshDataViewsService extends
		AbstractBusinessServiceImpl<RefreshDataViewsRequest, RefreshDataViewsResponse> {

	/** The View data manager. */
	@Autowired
	private ViewDataManager viewDataManager;

	/**
	 * Instantiates a new refresh data views service.
	 */
	public RefreshDataViewsService() {
		super(RefreshDataViewsRequest.class);
	}


	@Override
	@Secured({ "ROLE_ADMIN" })
	public RefreshDataViewsResponse processService(
			final RefreshDataViewsRequest serviceRequest) {
		final RefreshDataViewsResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}

		final RefreshDataViewsResponse response = new RefreshDataViewsResponse(ServiceResult.SUCCESS);
		
		// Refresh views in a separate transaction with appropriate timeout
		refreshViewsInTransaction();
		
		// Create application event in a new transaction after refresh completes
		createApplicationEventInNewTransaction(serviceRequest, response);

		return response;
	}
	
	/**
	 * Refresh views in a transaction with 60-minute timeout.
	 */
	@Transactional(propagation = Propagation.REQUIRED, timeout = 3600)
	private void refreshViewsInTransaction() {
		viewDataManager.refreshViews();
	}
	
	/**
	 * Create application event in a new transaction after refresh completes.
	 * This prevents extending lock retention by keeping the event creation
	 * separate from the long-running view refresh transaction.
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void createApplicationEventInNewTransaction(
			final RefreshDataViewsRequest serviceRequest,
			final RefreshDataViewsResponse response) {
		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);
		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);
	}


	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(final RefreshDataViewsRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.ADMIN);
		eventRequest.setApplicationOperation(ApplicationOperationType.UPDATE);
		eventRequest.setActionName(RefreshDataViewsRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}


	@Override
	protected RefreshDataViewsResponse createErrorResponse() {
		return new RefreshDataViewsResponse(ServiceResult.FAILURE);
	}

}
