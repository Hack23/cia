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

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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
import com.hack23.cia.service.api.action.admin.SendEmailRequest;
import com.hack23.cia.service.api.action.admin.SendEmailResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;
import com.hack23.cia.service.impl.email.EmailService;

/**
 * The Class SendEmailService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 600)
public final class SendEmailService extends AbstractBusinessServiceImpl<SendEmailRequest, SendEmailResponse>
		implements BusinessService<SendEmailRequest, SendEmailResponse> {

	/** The Constant EMAIL_IS_NOT_A_VALID_EMAIL_ADDRESS. */
	private static final String EMAIL_IS_NOT_A_VALID_EMAIL_ADDRESS = "Email is not a valid email address";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailService.class);

	/** The email service. */
	@Autowired
	private EmailService emailService;

	/** The create application event service. */
	@Autowired
	private BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	/**
	 * Instantiates a new send email service.
	 */
	public SendEmailService() {
		super(SendEmailRequest.class);
	}

	@Override
	@Secured({ "ROLE_ADMIN" })
	public SendEmailResponse processService(final SendEmailRequest serviceRequest) {

		final CreateApplicationEventRequest eventRequest = new CreateApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.ADMIN);
		eventRequest.setApplicationOperation(ApplicationOperationType.CREATE);
		eventRequest.setActionName(SendEmailRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());

		final UserAccount userAccount = getUserAccountFromSecurityContext();

		if (userAccount != null) {
			LOGGER.info("{} started:{}", serviceRequest.getClass().getSimpleName(), userAccount.getEmail());
			eventRequest.setUserId(userAccount.getUserId());
		}

		SendEmailResponse response;
		if (isValidEmailAddress(serviceRequest.getEmail())) {
			emailService.sendEmail(serviceRequest.getEmail(), serviceRequest.getSubject(), serviceRequest.getContent());
			response = new SendEmailResponse(ServiceResult.SUCCESS);
		} else {
			response = new SendEmailResponse(ServiceResult.FAILURE);
			response.setErrorMessage(EMAIL_IS_NOT_A_VALID_EMAIL_ADDRESS);
		}

		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}

	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}

}
