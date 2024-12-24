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
package com.hack23.cia.service.impl.action.common;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.AbstractResponse;
import com.hack23.cia.service.api.action.common.ServiceRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;

/**
 * The Class AbstractBusinessServiceImpl.
 *
 * @param <T>
 *            the generic type
 * @param <V>
 *            the value type
 */
public abstract class AbstractBusinessServiceImpl<T extends ServiceRequest, V extends ServiceResponse>
		extends AbstractCommonBusinessServiceImpl<T, V> {

	/** The create application event service. */
	@Autowired
	protected BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	/** The validator. */
	@Autowired
	protected Validator validator;

	/**
	 * Instantiates a new abstract business service impl.
	 *
	 * @param clazz
	 *            the clazz
	 */
	public AbstractBusinessServiceImpl(final Class<T> clazz) {
		super(clazz);
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
	 * Creates the error response.
	 *
	 * @return the v
	 */
	protected abstract V createErrorResponse();


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
			final V response = createErrorResponse();
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
	 * Validate request.
	 *
	 * @param request
	 *            the request
	 * @return the sets the
	 */
	private Set<ConstraintViolation<T>> validateRequest(final T request) {
        return validator.validate( request );
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


}
