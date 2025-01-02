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
import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.service.api.action.application.RegisterUserResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class RegisterUserServiceITest.
 */
public final class RegisterUserServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Service register user request success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceRegisterUserRequestSuccessTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();


		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("Userpassword1!");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());
	}

	/**
	 * Service register user request user already exist failure test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceRegisterUserRequestUserAlreadyExistFailureTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();

		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("Userpassword1!");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

		createApplicationSesstionWithRoleAnonymous();

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());

		final RegisterUserResponse errorResponse = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, errorResponse);
		assertEquals(EXPECT_SUCCESS,ServiceResult.FAILURE, errorResponse.getResult());
		assertEquals(RegisterUserResponse.ErrorMessage.USER_ALREADY_EXIST.toString(), errorResponse.getErrorMessage());

	}

	/**
	 * Service register user request weak password failure test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceRegisterUserRequestWeakPasswordFailureTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();

		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("weak");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse errorResponse = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, errorResponse);
		assertEquals(EXPECT_SUCCESS,ServiceResult.FAILURE, errorResponse.getResult());
		assertEquals("[Password must be 8 or more characters in length., Password must contain 1 or more uppercase characters., Password must contain 1 or more digit characters., Password must contain 1 or more special characters.]", errorResponse.getErrorMessage());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(0, allBy.size());
	}

	/**
	 * Service register user request invalid request failure test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceRegisterUserRequestInvalidRequestFailureTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();

		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "NoValidEmail");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse errorResponse = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, errorResponse);
		assertEquals(EXPECT_SUCCESS,ServiceResult.FAILURE, errorResponse.getResult());
		assertEquals("email must be a well-formed email address, userpassword must not be null", errorResponse.getErrorMessage());
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
		assertNotNull(createApplicationSesstion);
		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername()+"@hack23.com");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setUserpassword("Userpassword1!");

		final RegisterUserResponse errorResponse = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, errorResponse);
		assertEquals(EXPECT_SUCCESS,ServiceResult.FAILURE, errorResponse.getResult());
		assertEquals(ServiceResult.FAILURE, errorResponse.getResult());
		assertEquals("sessionId must not be null", errorResponse.getErrorMessage());
	}

}
