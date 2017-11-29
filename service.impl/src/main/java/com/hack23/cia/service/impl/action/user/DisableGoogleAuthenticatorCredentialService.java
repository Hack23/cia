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
package com.hack23.cia.service.impl.action.user;

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
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.DisableGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.service.api.action.user.DisableGoogleAuthenticatorCredentialResponse;
import com.hack23.cia.service.data.api.UserDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;

/**
 * The Class DisableGoogleAuthenticatorCredentialService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout=600)
public final class DisableGoogleAuthenticatorCredentialService extends
		AbstractBusinessServiceImpl<DisableGoogleAuthenticatorCredentialRequest, DisableGoogleAuthenticatorCredentialResponse>
		implements BusinessService<DisableGoogleAuthenticatorCredentialRequest, DisableGoogleAuthenticatorCredentialResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DisableGoogleAuthenticatorCredentialService.class);

	/** The create application event service. */
	@Autowired
	private BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	/** The user dao. */
	@Autowired
	private UserDAO userDAO;

	/**
	 * Instantiates a new disable google authenticator credential service.
	 */
	public DisableGoogleAuthenticatorCredentialService() {
		super(DisableGoogleAuthenticatorCredentialRequest.class);
	}


	@Secured({ "ROLE_USER", "ROLE_ADMIN"})
	@Override
	public DisableGoogleAuthenticatorCredentialResponse processService(
			final DisableGoogleAuthenticatorCredentialRequest serviceRequest) {

		LOGGER.info("{}:{}",serviceRequest.getClass().getSimpleName(),serviceRequest.getSessionId());

		final CreateApplicationEventRequest eventRequest = new CreateApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.CREATE);
		eventRequest.setActionName(DisableGoogleAuthenticatorCredentialRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());

		final UserAccount userAccount = getUserAccountFromSecurityContext();

		final DisableGoogleAuthenticatorCredentialResponse response = new DisableGoogleAuthenticatorCredentialResponse(ServiceResult.SUCCESS);
		if (userAccount != null) {

			eventRequest.setUserId(userAccount.getUserId());

			final UserAccount updateUserAccount = userDAO.load(userAccount.getHjid());

			updateUserAccount.setGoogleAuthKey(null);
			updateUserAccount.setGoogleAuthVerificationCode(null);
			updateUserAccount.setGoogleAuthScratchCodes(null);
			userDAO.merge(updateUserAccount);

		}

		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}



}
