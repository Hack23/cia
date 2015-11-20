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
package com.hack23.cia.service.impl.action.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.UserDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;

/**
 * The Class CreateApplicationEventService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public final class CreateApplicationEventService
		extends AbstractBusinessServiceImpl<CreateApplicationEventRequest, CreateApplicationEventResponse>
		implements BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> {

	/** The user dao. */
	@Autowired
	private UserDAO userDAO;

	/**
	 * Instantiates a new creates the application event service.
	 */
	public CreateApplicationEventService() {
		super(CreateApplicationEventRequest.class);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.impl.action.common.BusinessService#processService(com.hack23.cia.service.api.action.common.ServiceRequest)
	 */
	@Override
	@Secured({ "ROLE_ANONYMOUS" })
	public CreateApplicationEventResponse processService(CreateApplicationEventRequest serviceRequest) {
		return new CreateApplicationEventResponse(ServiceResult.SUCCESS);
	}

}
