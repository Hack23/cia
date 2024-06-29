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
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.LogoutRequest;
import com.hack23.cia.service.api.action.application.LogoutResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;

/**
 * The Class LogoutService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public final class LogoutService extends AbstractBusinessServiceImpl<LogoutRequest, LogoutResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LogoutService.class);


	/**
	 * Instantiates a new logout service.
	 */
	public LogoutService() {
		super(LogoutRequest.class);
	}

	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public LogoutResponse processService(final LogoutRequest serviceRequest) {
		final LogoutResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}

		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);
		final UserAccount userAccount = getUserAccountFromSecurityContext();

		LogoutResponse response;
		if (userAccount != null) {
			eventRequest.setElementId(userAccount.getEmail());

			final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
			final AnonymousAuthenticationToken anonymousAuthenticationToken = new AnonymousAuthenticationToken(
					serviceRequest.getSessionId(), "ROLE_ANONYMOUS", authorities);
			SecurityContextHolder.getContext().setAuthentication(anonymousAuthenticationToken);

			response=new LogoutResponse(ServiceResult.SUCCESS);
		} else {
			response= new LogoutResponse(ServiceResult.FAILURE);
		}

		eventRequest.setApplicationMessage(response.getResult().toString());

		createApplicationEventService.processService(eventRequest);
		LOGGER.info("Event: {}",eventRequest);
		return response;
	}

	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(final LogoutRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.AUTHENTICATION);
		eventRequest.setActionName(LogoutRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}

	@Override
	protected LogoutResponse createErrorResponse() {
		return new LogoutResponse(ServiceResult.FAILURE);
	}

}
