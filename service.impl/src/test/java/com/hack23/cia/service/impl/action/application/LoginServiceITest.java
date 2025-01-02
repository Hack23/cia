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

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.model.internal.application.user.impl.UserType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.service.api.action.application.RegisterUserResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.SetGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;
import com.hack23.cia.service.impl.action.application.encryption.VaultManager;
import com.warrenstrange.googleauth.GoogleAuthenticator;

/**
 * The Class LoginServiceITest.
 */
public final class LoginServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	@Autowired
	private VaultManager vaultManager;

	/**
	 * Service login request success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceLoginRequestSuccessTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();


		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("Userpassword1!");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result", response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());



		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(serviceRequest.getEmail());
		loginRequest.setSessionId(serviceRequest.getSessionId());
		loginRequest.setUserpassword(serviceRequest.getUserpassword());

		final LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

		assertNotNull("Expect a result", loginResponse);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, loginResponse.getResult());
	}

	@Test
	public void serviceLoginRequestFailureMfaMissingTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();


		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("Userpassword1!");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result", response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());


		final SetGoogleAuthenticatorCredentialRequest setGoogleAuthenticatorCredentialRequest = new SetGoogleAuthenticatorCredentialRequest();
		setGoogleAuthenticatorCredentialRequest.setSessionId(serviceRequest.getSessionId());
		setGoogleAuthenticatorCredentialRequest.setUserpassword("Userpassword1!");

		final ServiceResponse setGoogleAuthenticatorCredentialResponse = applicationManager.service(setGoogleAuthenticatorCredentialRequest);

		assertNotNull(EXPECT_A_RESULT, setGoogleAuthenticatorCredentialResponse);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, setGoogleAuthenticatorCredentialResponse.getResult());

		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(serviceRequest.getEmail());
		loginRequest.setSessionId(serviceRequest.getSessionId());
		loginRequest.setUserpassword(serviceRequest.getUserpassword());

		final LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

		assertNotNull("Expect a result", loginResponse);
		assertEquals(ServiceResult.FAILURE, loginResponse.getResult());
		assertEquals(LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH.toString(), loginResponse.getErrorMessage());
	}

	@Test
	public void serviceLoginRequestMfaSuccessTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();

		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("Userpassword1!");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result", response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());

		final SetGoogleAuthenticatorCredentialRequest setGoogleAuthenticatorCredentialRequest = new SetGoogleAuthenticatorCredentialRequest();
		setGoogleAuthenticatorCredentialRequest.setSessionId(serviceRequest.getSessionId());
		setGoogleAuthenticatorCredentialRequest.setUserpassword("Userpassword1!");

		final ServiceResponse setGoogleAuthenticatorCredentialResponse = applicationManager.service(setGoogleAuthenticatorCredentialRequest);

		assertNotNull(EXPECT_A_RESULT, setGoogleAuthenticatorCredentialResponse);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, setGoogleAuthenticatorCredentialResponse.getResult());

		final GoogleAuthenticator gAuth = new GoogleAuthenticator();

		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(serviceRequest.getEmail());
		loginRequest.setSessionId(serviceRequest.getSessionId());
		loginRequest.setUserpassword(serviceRequest.getUserpassword());
		loginRequest.setOtpCode(""+ gAuth.getTotpPassword(vaultManager.getEncryptedValue(serviceRequest.getUserpassword(), allBy.get(0))));

		final LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

		assertNotNull("Expect a result", loginResponse);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, loginResponse.getResult());
	}


	/**
	 * Service login request user password do not match failure test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceLoginRequestUserPasswordDoNotMatchFailureTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();


		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("Userpassword1!");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result", response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());



		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(serviceRequest.getEmail());
		loginRequest.setSessionId(serviceRequest.getSessionId());
		loginRequest.setUserpassword(serviceRequest.getUserpassword() + "wrongpassword");

		final LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

		assertNotNull("Expect a result", loginResponse);
		assertEquals(ServiceResult.FAILURE, loginResponse.getResult());
		assertEquals(LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH.toString(), loginResponse.getErrorMessage());

	}


	/**
	 * Service login request user blocked by max user attemp test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceLoginRequestUserBlockedByMaxUserAttempTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();


		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("Userpassword1!");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result", response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());


		for(int i=0; i< 6; i++) {
			final CreateApplicationSessionRequest newApplicationSesstion = createApplicationSesstionWithRoleAnonymous();

			final LoginRequest loginRequest = new LoginRequest();
			loginRequest.setEmail(serviceRequest.getEmail());
			loginRequest.setSessionId(newApplicationSesstion.getSessionId());
			loginRequest.setUserpassword(serviceRequest.getUserpassword() + "wrongpassword");

			final LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

			assertNotNull("Expect a result", loginResponse);
			assertEquals(ServiceResult.FAILURE, loginResponse.getResult());
			assertEquals(LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH.toString(), loginResponse.getErrorMessage());
		}

		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(serviceRequest.getEmail());
		loginRequest.setSessionId(serviceRequest.getSessionId());
		loginRequest.setUserpassword(serviceRequest.getUserpassword());

		final LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

		assertNotNull("Expect a result", loginResponse);
		assertEquals(ServiceResult.FAILURE, loginResponse.getResult());
		assertEquals(LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH.toString(), loginResponse.getErrorMessage());
	}


	/**
	 * Service login request user blocked by max session attemp test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceLoginRequestUserBlockedByMaxSessionAttempTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();


		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("Userpassword1!");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result", response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());


		final CreateApplicationSessionRequest newApplicationSesstion = createApplicationSesstionWithRoleAnonymous();
		for(int i=0; i< 6; i++) {

			final LoginRequest loginRequest = new LoginRequest();
			loginRequest.setEmail(serviceRequest.getEmail() + "someotheruser");
			loginRequest.setSessionId(newApplicationSesstion.getSessionId());
			loginRequest.setUserpassword(serviceRequest.getUserpassword() + "wrongpassword");

			final LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

			assertNotNull("Expect a result", loginResponse);
			assertEquals(ServiceResult.FAILURE, loginResponse.getResult());
			assertEquals(LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH.toString(), loginResponse.getErrorMessage());
		}

		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(serviceRequest.getEmail());
		loginRequest.setSessionId(newApplicationSesstion.getSessionId());
		loginRequest.setUserpassword(serviceRequest.getUserpassword());

		final LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

		assertNotNull("Expect a result", loginResponse);
		assertEquals(ServiceResult.FAILURE, loginResponse.getResult());
		assertEquals(LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH.toString(), loginResponse.getErrorMessage());
	}

	/**
	 * Service request validation failure test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceRequestValidationFailureTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();
		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail("some@email.com");
		loginRequest.setUserpassword("someValidPassword123!");

		final LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

		assertNotNull("Expect a result", loginResponse);
		assertEquals(ServiceResult.FAILURE, loginResponse.getResult());
		assertEquals("sessionId must not be null", loginResponse.getErrorMessage());
	}


}
