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
package com.hack23.cia.service.impl.action.application;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.DestroyApplicationSessionRequest;
import com.hack23.cia.service.api.action.application.DestroyApplicationSessionResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationSessionDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;

/**
 * The Class DestroyApplicationSessionService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public final class DestroyApplicationSessionService
		extends AbstractBusinessServiceImpl<DestroyApplicationSessionRequest, DestroyApplicationSessionResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DestroyApplicationSessionService.class);

	/** The application session dao. */
	@Autowired
	private ApplicationSessionDAO applicationSessionDAO;

	/**
	 * Instantiates a new destroy application session service.
	 */
	public DestroyApplicationSessionService() {
		super(DestroyApplicationSessionRequest.class);
	}

	@Override
	@Secured({ "ROLE_ANONYMOUS" })
	public DestroyApplicationSessionResponse processService(final DestroyApplicationSessionRequest serviceRequest) {
		final DestroyApplicationSessionResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}


		final ApplicationSession applicationSession = applicationSessionDAO
				.findFirstByProperty(ApplicationSession_.sessionId, serviceRequest.getSessionId());

		if (applicationSession != null) {
			LOGGER.info("Destroy Application session: {}", applicationSession.getSessionId());
			applicationSession.setDestroyedDate(new Date());
			applicationSessionDAO.persist(applicationSession);
			return new DestroyApplicationSessionResponse(ServiceResult.SUCCESS);
		} else {
			LOGGER.warn("Failed to destroy Application session, no session found for id: {}",
					serviceRequest.getSessionId());
			return new DestroyApplicationSessionResponse(ServiceResult.FAILURE);
		}
	}

	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(
			final DestroyApplicationSessionRequest serviceRequest) {
		return new CreateApplicationEventRequest();
	}

	@Override
	protected DestroyApplicationSessionResponse createErrorResponse() {
		return new DestroyApplicationSessionResponse(ServiceResult.FAILURE);
	}

}
