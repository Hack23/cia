/*
 * Copyright 2010 -2025 James Pether Sörling
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

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
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
import com.hack23.cia.model.internal.application.secure.impl.EncryptedValue_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.ChangePasswordRequest;
import com.hack23.cia.service.api.action.user.ChangePasswordResponse;
import com.hack23.cia.service.data.api.EncryptedValueDAO;
import com.hack23.cia.service.impl.action.application.encryption.VaultManager;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;

/**
 * The Class ChangePasswordService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 600)
public final class ChangePasswordService extends
		AbstractBusinessServiceImpl<ChangePasswordRequest, ChangePasswordResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChangePasswordService.class);

	/** The encrypted value DAO. */
	@Autowired
	private EncryptedValueDAO encryptedValueDAO;

	@Autowired
	private VaultManager vaultManager;

	/** The password encoder. */
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/** The password validator. */
	private final PasswordValidator passwordValidator = new PasswordValidator(new LengthRule(8, 64),
			new CharacterRule(EnglishCharacterData.UpperCase, 1), new CharacterRule(EnglishCharacterData.LowerCase, 1),
			new CharacterRule(EnglishCharacterData.Digit, 1), new CharacterRule(EnglishCharacterData.Special, 1),
			new WhitespaceRule());


	/**
	 * Instantiates a new change password service.
	 */
	public ChangePasswordService() {
		super(ChangePasswordRequest.class);
	}

	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public ChangePasswordResponse processService(
			final ChangePasswordRequest serviceRequest) {
		final ChangePasswordResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}


		LOGGER.info("{}:{}", serviceRequest.getClass().getSimpleName(), serviceRequest.getSessionId());
		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);

		final UserAccount userAccount = getUserAccountFromSecurityContext();

		ChangePasswordResponse response = new ChangePasswordResponse(
				ServiceResult.SUCCESS);
		if (userAccount != null) {

			if (passwordEncoder.matches(
					userAccount.getUserId() + ".uuid" + serviceRequest.getCurrentPassword(), userAccount.getUserpassword()) && serviceRequest.getNewPassword().equals(serviceRequest.getRepeatNewPassword())) {


				final RuleResult passwordRuleResults = passwordValidator
						.validate(new PasswordData(serviceRequest.getNewPassword()));

				if (passwordRuleResults.isValid()) {

					userAccount.setUserpassword(passwordEncoder.encode(userAccount.getUserId() + ".uuid" + serviceRequest.getNewPassword()));
					getUserDAO().merge(userAccount);

					reencryptVaultValues(serviceRequest, userAccount);

				} else {
					response = new ChangePasswordResponse(ServiceResult.FAILURE);
					final String errorMessage = passwordValidator.getMessages(passwordRuleResults).toString();
					response.setErrorMessage(errorMessage);
					eventRequest.setErrorMessage(errorMessage);
				}


			} else {
				response = new ChangePasswordResponse(
						ServiceResult.FAILURE);
			}

		}

		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}

	/**
	 * Reencrypt vault values.
	 *
	 * @param serviceRequest the service request
	 * @param userAccount    the user account
	 */
	private void reencryptVaultValues(final ChangePasswordRequest serviceRequest, final UserAccount userAccount) {
		final String authKey= vaultManager.getEncryptedValue(serviceRequest.getCurrentPassword(), userAccount);
		if (authKey != null) {
			final EncryptedValue encryptedValue = encryptedValueDAO.findFirstByProperty(EncryptedValue_.userId, userAccount.getUserId());
			encryptedValue.setStorage(vaultManager.encryptValue(serviceRequest.getNewPassword(), userAccount.getUserId(), authKey));
			encryptedValueDAO.merge(encryptedValue);
		}
	}

	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(
			final ChangePasswordRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.CREATE);
		eventRequest.setActionName(ChangePasswordRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}

	@Override
	protected ChangePasswordResponse createErrorResponse() {
		return new ChangePasswordResponse(ServiceResult.FAILURE);
	}

}
