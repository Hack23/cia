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
package com.hack23.cia.service.impl.action.application;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationSessionDAO;
import com.hack23.cia.service.impl.action.common.AbstractCommonBusinessServiceImpl;

/**
 * The Class CreateApplicationSessionService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public final class CreateApplicationSessionService
		extends AbstractCommonBusinessServiceImpl<CreateApplicationSessionRequest, CreateApplicationSessionResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CreateApplicationSessionService.class);


	/** The application session dao. */
	@Autowired
	private ApplicationSessionDAO applicationSessionDAO;

	/**
	 * Instantiates a new creates the application session service.
	 */
	public CreateApplicationSessionService() {
		super(CreateApplicationSessionRequest.class);
	}

	@Secured({ "ROLE_ANONYMOUS" })
	@Override
	public CreateApplicationSessionResponse processService(final CreateApplicationSessionRequest serviceRequest) {
		final ApplicationSession applicationSession = new ApplicationSession();
		applicationSession.setCreatedDate(new Date());

		applicationSession.setSessionId(serviceRequest.getSessionId());
		applicationSession.setIpInformation(serviceRequest.getIpInformation());
		applicationSession.setLocale(serviceRequest.getLocale());
		applicationSession.setTimeZone(serviceRequest.getTimeZone());
		applicationSession.setScreenSize(serviceRequest.getScreenSize());
		applicationSession.setOperatingSystem(serviceRequest.getOperatingSystem());
		applicationSession.setUserAgentInformation(serviceRequest.getUserAgentInformation());
		applicationSession.setSessionType(serviceRequest.getSessionType());
		applicationSession.setEvents(new ArrayList<>());
		applicationSessionDAO.persist(applicationSession);

		LOGGER.info("Create application session:{}",applicationSession);

		return new CreateApplicationSessionResponse(ServiceResult.SUCCESS);
	}

}
