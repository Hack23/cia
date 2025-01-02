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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSessionType;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationSessionDAO;
import com.hack23.cia.service.impl.action.common.AbstractCommonBusinessServiceImpl;

/**
 * The Class CreateApplicationEventService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
public final class CreateApplicationEventService
		extends AbstractCommonBusinessServiceImpl<CreateApplicationEventRequest, CreateApplicationEventResponse> {

	/** The application session dao. */
	@Autowired
	private ApplicationSessionDAO applicationSessionDAO;

	/**
	 * Instantiates a new creates the application event service.
	 */
	public CreateApplicationEventService() {
		super(CreateApplicationEventRequest.class);
	}

	@Override
	public CreateApplicationEventResponse processService(final CreateApplicationEventRequest serviceRequest) {
		final ApplicationSession applicationSession = applicationSessionDAO
				.findFirstByProperty(ApplicationSession_.sessionId, serviceRequest.getSessionId());

		if (applicationSession != null) {
			final ApplicationActionEvent applicationActionEvent = new ApplicationActionEvent();

			applicationActionEvent.setEventGroup(serviceRequest.getEventGroup());
			applicationActionEvent.setCreatedDate(new Date());
			applicationActionEvent.setSessionId(serviceRequest.getSessionId());

			applicationActionEvent.setPage(serviceRequest.getPage());
			applicationActionEvent.setPageMode(serviceRequest.getPageMode());
			applicationActionEvent.setElementId(serviceRequest.getElementId());
			applicationActionEvent.setApplicationOperation(serviceRequest.getApplicationOperation());
			applicationActionEvent.setActionName(serviceRequest.getActionName());

			applicationActionEvent.setUserId(serviceRequest.getUserId());
			if (serviceRequest.getUserId() != null && !"anonymousUser".equalsIgnoreCase(serviceRequest.getUserId())
					&& ApplicationSessionType.ANONYMOUS == applicationSession.getSessionType()) {
				applicationSession.setSessionType(ApplicationSessionType.REGISTERED_USER);
				applicationSession.setUserId(serviceRequest.getUserId());
			}


			applicationActionEvent.setApplicationMessage(serviceRequest.getApplicationMessage());
			applicationActionEvent.setErrorMessage(serviceRequest.getErrorMessage());

			applicationSession.getEvents().add(applicationActionEvent);

			applicationSessionDAO.persist(applicationSession);
			return new CreateApplicationEventResponse(ServiceResult.SUCCESS);
		} else {
			return new CreateApplicationEventResponse(ServiceResult.FAILURE);
		}
	}


}
