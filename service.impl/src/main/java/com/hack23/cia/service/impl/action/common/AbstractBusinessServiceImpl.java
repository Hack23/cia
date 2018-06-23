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
package com.hack23.cia.service.impl.action.common;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.AbstractResponse;
import com.hack23.cia.service.api.action.common.ServiceRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.data.api.UserDAO;

/**
 * The Class AbstractBusinessServiceImpl.
 *
 * @param <T>
 *            the generic type
 * @param <V>
 *            the value type
 */
public abstract class AbstractBusinessServiceImpl<T extends ServiceRequest, V extends ServiceResponse>
		implements BusinessService<T, V> {

	/** The user dao. */
	@Autowired
	private UserDAO userDAO;


	/** The create application event service. */
	@Autowired
	protected BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	
	/** The clazz. */
	private final Class<T> clazz;


	/**
	 * Instantiates a new abstract business service impl.
	 *
	 * @param clazz
	 *            the clazz
	 */
	public AbstractBusinessServiceImpl(final Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public final Class<? extends ServiceRequest> getSupportedService() {
		return clazz;
	}

	/**
	 * Gets the create application event service.
	 *
	 * @return the create application event service
	 */
	protected final BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> getCreateApplicationEventService() {
		return createApplicationEventService;
	}
	
	/**
	 * Gets the user account from security context.
	 *
	 * @return the user account from security context
	 */
	protected final UserAccount getUserAccountFromSecurityContext() {
		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication authentication = context.getAuthentication();
			if (authentication != null) {
				return userDAO.findFirstByProperty(UserAccount_.userId, authentication.getPrincipal().toString());
			}
		}

		return null;
	}
		
	/**
	 * Input validation.
	 *
	 * @param serviceRequest
	 *            the service request
	 * @return the v
	 */
	protected final V inputValidation(final T serviceRequest) {
		final Set<ConstraintViolation<T>> validateRequest = validateRequest(serviceRequest);
		if (!validateRequest.isEmpty()) {
			final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);
			V response = createErrorResponse();
			handleInputViolations(eventRequest, validateRequest, response);
			createApplicationEventService.processService(eventRequest);
			return response;
		} else {
			return null;
		}
	}
	
	/**
	 * Creates the base application event request.
	 *
	 * @return the creates the application event request
	 */
	protected final CreateApplicationEventRequest createBaseApplicationEventRequest() {
		final CreateApplicationEventRequest eventRequest = new CreateApplicationEventRequest();
		final UserAccount userAccount = getUserAccountFromSecurityContext();
		
		if (userAccount != null) {			
			eventRequest.setUserId(userAccount.getUserId());
		}
		return eventRequest;
	}

	
	/**
	 * Creates the application event for service.
	 *
	 * @param serviceRequest
	 *            the service request
	 * @return the creates the application event request
	 */
	protected abstract CreateApplicationEventRequest createApplicationEventForService(final T serviceRequest);
	
	/**
	 * Creates the error response.
	 *
	 * @return the v
	 */
	protected abstract V createErrorResponse();
	
	/**
	 * Validate request.
	 *
	 * @param request
	 *            the request
	 * @return the sets the
	 */
	private Set<ConstraintViolation<T>> validateRequest(final T request) {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator().validate( request );
	}

	/**
	 * Gets the human message.
	 *
	 * @param requestConstraintViolations
	 *            the request constraint violations
	 * @return the human message
	 */
	private String getHumanMessage(final Set<ConstraintViolation<T>> requestConstraintViolations) {
		return requestConstraintViolations.stream()
				.sorted((p1, p2) -> p1.getPropertyPath().toString().compareTo(p2.getPropertyPath().toString()))
				.map(p -> p.getPropertyPath().toString() + " " + p.getMessage()).collect(Collectors.joining(", "));
	}

	/**
	 * Handle input violations.
	 *
	 * @param eventRequest
	 *            the event request
	 * @param requestConstraintViolations
	 *            the request constraint violations
	 * @param response
	 *            the response
	 * @return the v
	 */
	private void handleInputViolations(final CreateApplicationEventRequest eventRequest,
			final Set<ConstraintViolation<T>> requestConstraintViolations,final V response) {
		final String errorMessage = getHumanMessage(requestConstraintViolations);
		((AbstractResponse) response).setErrorMessage(errorMessage);
		eventRequest.setErrorMessage(errorMessage);
	}

	/**
	 * Gets the user dao.
	 *
	 * @return the user dao
	 */
	protected final UserDAO getUserDAO() {
		return userDAO;
	}

	
}
