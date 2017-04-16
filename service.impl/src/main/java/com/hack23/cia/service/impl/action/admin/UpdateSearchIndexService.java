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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.admin.UpdateSearchIndexRequest;
import com.hack23.cia.service.api.action.admin.UpdateSearchIndexResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.SearchIndexer;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;

/**
 * The Class UpdateSearchIndexService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout=1200)
public final class UpdateSearchIndexService extends
		AbstractBusinessServiceImpl<UpdateSearchIndexRequest, UpdateSearchIndexResponse>
		implements BusinessService<UpdateSearchIndexRequest, UpdateSearchIndexResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UpdateSearchIndexService.class);

	/** The search indexer. */
	@Autowired
	private SearchIndexer searchIndexer;

	/** The create application event service. */
	@Autowired
	private BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	/**
	 * Instantiates a new update search index service.
	 */
	public UpdateSearchIndexService() {
		super(UpdateSearchIndexRequest.class);
	}

	@Override
	@Secured({ "ROLE_ADMIN" })
	public UpdateSearchIndexResponse processService(
			final UpdateSearchIndexRequest serviceRequest) {


		final CreateApplicationEventRequest eventRequest = new CreateApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.ADMIN);
		eventRequest.setApplicationOperation(ApplicationOperationType.UPDATE);
		eventRequest.setActionName(UpdateSearchIndexRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());

		final UserAccount userAccount = getUserAccountFromSecurityContext();


		if (userAccount != null) {
			LOGGER.info("{} started:{}", serviceRequest.getClass().getSimpleName(),userAccount.getEmail());

			eventRequest.setUserId(userAccount.getUserId());
		}

		UpdateSearchIndexResponse response;
		try {
			searchIndexer.updateSearchIndex();
			response = new UpdateSearchIndexResponse(ServiceResult.SUCCESS);
		} catch (final InterruptedException e) {
			LOGGER.warn("Update Index failed",e);
		    Thread.currentThread().interrupt();
		    response = new UpdateSearchIndexResponse(ServiceResult.FAILURE);
		}

		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}



}
