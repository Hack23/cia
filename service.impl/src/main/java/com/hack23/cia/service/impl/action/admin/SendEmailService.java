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
package com.hack23.cia.service.impl.action.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.service.api.action.admin.SendEmailRequest;
import com.hack23.cia.service.api.action.admin.SendEmailResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.email.EmailService;

/**
 * The Class SendEmailService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 600)
public final class SendEmailService extends AbstractBusinessServiceImpl<SendEmailRequest, SendEmailResponse> {

	/** The email service. */
	@Autowired
	private EmailService emailService;

	/**
	 * Instantiates a new send email service.
	 */
	public SendEmailService() {
		super(SendEmailRequest.class);
	}

	@Override
	@Secured({ "ROLE_ADMIN" })
	public SendEmailResponse processService(final SendEmailRequest serviceRequest) {
		final SendEmailResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}

		emailService.sendEmail(serviceRequest.getEmail(), serviceRequest.getSubject(), serviceRequest.getContent());
		final SendEmailResponse response = new SendEmailResponse(ServiceResult.SUCCESS);

		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);
		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}

	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(final SendEmailRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.ADMIN);
		eventRequest.setApplicationOperation(ApplicationOperationType.CREATE);
		eventRequest.setActionName(SendEmailRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}

	@Override
	protected SendEmailResponse createErrorResponse() {
		return new SendEmailResponse(ServiceResult.FAILURE);
	}

}
