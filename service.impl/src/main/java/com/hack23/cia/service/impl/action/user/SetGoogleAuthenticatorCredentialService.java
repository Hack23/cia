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
package com.hack23.cia.service.impl.action.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.secure.impl.EncryptedValue;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.SetGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.service.api.action.user.SetGoogleAuthenticatorCredentialResponse;
import com.hack23.cia.service.data.api.AgencyDAO;
import com.hack23.cia.service.data.api.EncryptedValueDAO;
import com.hack23.cia.service.impl.action.application.encryption.VaultManager;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

/**
 * The Class SetGoogleAuthenticatorCredentialService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout=600)
public final class SetGoogleAuthenticatorCredentialService extends
		AbstractBusinessServiceImpl<SetGoogleAuthenticatorCredentialRequest, SetGoogleAuthenticatorCredentialResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SetGoogleAuthenticatorCredentialService.class);

	@Autowired
	private AgencyDAO agencyDAO;

	/** The encrypted value DAO. */
	@Autowired
	private EncryptedValueDAO encryptedValueDAO;

	@Autowired
	private VaultManager vaultManager;

	/** The password encoder. */
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Instantiates a new sets the google authenticator credential service.
	 */
	public SetGoogleAuthenticatorCredentialService() {
		super(SetGoogleAuthenticatorCredentialRequest.class);
	}


	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN"})
	public SetGoogleAuthenticatorCredentialResponse processService(
			final SetGoogleAuthenticatorCredentialRequest serviceRequest) {

		final SetGoogleAuthenticatorCredentialResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}

		LOGGER.info("{}:{}",serviceRequest.getClass().getSimpleName(),serviceRequest.getSessionId());
		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);

		final UserAccount userAccount = getUserAccountFromSecurityContext();

		SetGoogleAuthenticatorCredentialResponse response = new SetGoogleAuthenticatorCredentialResponse(ServiceResult.SUCCESS);
		if (userAccount != null) {

			final GoogleAuthenticator gAuth = new GoogleAuthenticator();
			final GoogleAuthenticatorKey gKey = gAuth.createCredentials();

			if (passwordEncoder.matches(
					userAccount.getUserId() + ".uuid" + serviceRequest.getUserpassword(), userAccount.getUserpassword())) {

				final EncryptedValue encryptedValue = new EncryptedValue();
				encryptedValue.setId(userAccount.getHjid());
				encryptedValue.setUserId(userAccount.getUserId());
				encryptedValue.setVaultName(GoogleAuthenticatorKey.class.getSimpleName());
				encryptedValue.setStorage(vaultManager.encryptValue(serviceRequest.getUserpassword(), userAccount.getUserId(), gKey.getKey()));
				encryptedValueDAO.persist(encryptedValue);

				final String otpAuthTotpURL = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(agencyDAO.getAll().get(0).getAgencyName(), userAccount.getEmail(), gKey);

				response.setOtpAuthTotpURL(otpAuthTotpURL);
				response.setGoogleAuthKey(gKey.getKey());
				response.setGoogleAuthVerificationCode(gKey.getVerificationCode());
				response.setGoogleAuthScratchCodes(gKey.getScratchCodes());
			} else {
				response = new SetGoogleAuthenticatorCredentialResponse(ServiceResult.FAILURE);
			}
		}

		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}


	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(
			final SetGoogleAuthenticatorCredentialRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.CREATE);
		eventRequest.setActionName(SetGoogleAuthenticatorCredentialRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}


	@Override
	protected SetGoogleAuthenticatorCredentialResponse createErrorResponse() {
		return new SetGoogleAuthenticatorCredentialResponse(ServiceResult.FAILURE);
	}



}
