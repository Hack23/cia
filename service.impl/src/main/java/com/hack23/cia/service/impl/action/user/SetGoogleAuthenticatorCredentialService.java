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
import com.hack23.cia.service.api.action.user.SetGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.service.api.action.user.SetGoogleAuthenticatorCredentialResponse;
import com.hack23.cia.service.data.api.AgencyDAO;
import com.hack23.cia.service.data.api.UserDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

/**
 * The Class SetGoogleAuthenticatorCredentialService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout=600)
public final class SetGoogleAuthenticatorCredentialService extends
		AbstractBusinessServiceImpl<SetGoogleAuthenticatorCredentialRequest, SetGoogleAuthenticatorCredentialResponse>
		implements BusinessService<SetGoogleAuthenticatorCredentialRequest, SetGoogleAuthenticatorCredentialResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SetGoogleAuthenticatorCredentialService.class);

	/** The create application event service. */
	@Autowired
	private BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	/** The user dao. */
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AgencyDAO agencyDAO;

	/**
	 * Instantiates a new sets the google authenticator credential service.
	 */
	public SetGoogleAuthenticatorCredentialService() {
		super(SetGoogleAuthenticatorCredentialRequest.class);
	}


	/* (non-Javadoc)
	 * @see com.hack23.cia.service.impl.action.common.BusinessService#processService(com.hack23.cia.service.api.action.common.ServiceRequest)
	 */
	@Secured({ "ROLE_USER", "ROLE_ADMIN"})
	@Override
	public SetGoogleAuthenticatorCredentialResponse processService(
			final SetGoogleAuthenticatorCredentialRequest serviceRequest) {

		LOGGER.info("{}:{}",serviceRequest.getClass().getSimpleName(),serviceRequest.getSessionId());


		final CreateApplicationEventRequest eventRequest = new CreateApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.CREATE);
		eventRequest.setActionName(SetGoogleAuthenticatorCredentialRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());

		final UserAccount userAccount = getUserAccountFromSecurityContext();

		final SetGoogleAuthenticatorCredentialResponse response = new SetGoogleAuthenticatorCredentialResponse(ServiceResult.SUCCESS);
		if (userAccount != null) {

			eventRequest.setUserId(userAccount.getUserId());

			final GoogleAuthenticator gAuth = new GoogleAuthenticator();
			final GoogleAuthenticatorKey gKey = gAuth.createCredentials();

			final UserAccount updateUserAccount = userDAO.load(userAccount.getHjid());

			updateUserAccount.setGoogleAuthKey(gKey.getKey());
			updateUserAccount.setGoogleAuthVerificationCode(gKey.getVerificationCode());
			updateUserAccount.setGoogleAuthScratchCodes(gKey.getScratchCodes());
			userDAO.merge(updateUserAccount);

			final String otpAuthTotpURL = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(agencyDAO.getAll().get(0).getAgencyName(), updateUserAccount.getEmail(), gKey);

			response.setOtpAuthTotpURL(otpAuthTotpURL);
			response.setGoogleAuthKey(gKey.getKey());
			response.setGoogleAuthVerificationCode(gKey.getVerificationCode());
			response.setGoogleAuthScratchCodes(gKey.getScratchCodes());
		}

		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}



}
