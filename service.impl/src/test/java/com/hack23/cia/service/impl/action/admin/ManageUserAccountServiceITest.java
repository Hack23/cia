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

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.model.internal.application.user.impl.UserLockStatus;
import com.hack23.cia.model.internal.application.user.impl.UserType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.admin.ManageUserAccountRequest;
import com.hack23.cia.service.api.action.admin.ManageUserAccountRequest.AccountOperation;
import com.hack23.cia.service.api.action.admin.ManageUserAccountResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.service.api.action.application.RegisterUserResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class SendEmailServiceITest.
 */
public final class ManageUserAccountServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Manage user account success delete test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageUserAccountSuccessDeleteTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();
		final RegisterUserRequest createAccountRequest = new RegisterUserRequest();
		createAccountRequest.setCountry("Sweden");
		createAccountRequest.setUsername(UUID.randomUUID().toString());
		createAccountRequest.setEmail(createAccountRequest.getUsername() + "@email.com");
		createAccountRequest.setUserpassword("Userpassword1!");
		createAccountRequest.setUserType(UserType.PRIVATE);
		createAccountRequest.setSessionId(createSessionRequest.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(createAccountRequest);
		assertNotNull("Expect a result", response);
		assertEquals(EXPECT_SUCCESS, ServiceResult.SUCCESS, response.getResult());
		setAuthenticatedAdminuser();

		final DataContainer<UserAccount, Long> userContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> firstCreatedUsed = userContainer.getAllBy(UserAccount_.username,
				createAccountRequest.getUsername());
		assertEquals(1, firstCreatedUsed.size());

		final ManageUserAccountRequest deleteAccountRequest = new ManageUserAccountRequest();
		deleteAccountRequest.setSessionId(createSessionRequest.getSessionId());
		deleteAccountRequest.setAccountOperation(AccountOperation.DELETE);
		deleteAccountRequest.setUserAcountId(firstCreatedUsed.get(0).getUserId());

		final ManageUserAccountResponse deleteAccountResponse = (ManageUserAccountResponse) applicationManager
				.service(deleteAccountRequest);
		assertNotNull(EXPECT_A_RESULT, deleteAccountResponse);
		assertEquals(EXPECT_SUCCESS, ServiceResult.SUCCESS, deleteAccountResponse.getResult());

		final List<UserAccount> allByAfterDelete = userContainer.getAllBy(UserAccount_.username,
				createAccountRequest.getUsername());
		assertEquals(0, allByAfterDelete.size());

		final DataContainer<ApplicationActionEvent, Long> eventContainer = applicationManager
				.getDataContainer(ApplicationActionEvent.class);
		final List<ApplicationActionEvent> events = eventContainer.getAllBy(ApplicationActionEvent_.userId,
				firstCreatedUsed.get(0).getUserId());
		assertEquals(0, events.size());
		final DataContainer<ApplicationSession, Serializable> sessionContainer = applicationManager
				.getDataContainer(ApplicationSession.class);
		final List<ApplicationSession> sessions = sessionContainer.getAllBy(ApplicationSession_.userId,
				firstCreatedUsed.get(0).getUserId());
		assertEquals(0, sessions.size());
	}

	/**
	 * Manage user account success lock test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageUserAccountSuccessLockTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();
		final RegisterUserRequest createAccountRequest = new RegisterUserRequest();
		createAccountRequest.setCountry("Sweden");
		createAccountRequest.setUsername(UUID.randomUUID().toString());
		createAccountRequest.setEmail(createAccountRequest.getUsername() + "@email.com");
		createAccountRequest.setUserpassword("Userpassword1!");
		createAccountRequest.setUserType(UserType.PRIVATE);
		createAccountRequest.setSessionId(createSessionRequest.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(createAccountRequest);
		assertNotNull("Expect a result", response);
		assertEquals(EXPECT_SUCCESS, ServiceResult.SUCCESS, response.getResult());
		setAuthenticatedAdminuser();

		final DataContainer<UserAccount, Long> userContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> firstCreatedUsed = userContainer.getAllBy(UserAccount_.username,
				createAccountRequest.getUsername());
		assertEquals(1, firstCreatedUsed.size());

		final ManageUserAccountRequest deleteAccountRequest = new ManageUserAccountRequest();
		deleteAccountRequest.setSessionId(createSessionRequest.getSessionId());
		deleteAccountRequest.setAccountOperation(AccountOperation.LOCK);
		deleteAccountRequest.setUserAcountId(firstCreatedUsed.get(0).getUserId());

		final ManageUserAccountResponse deleteAccountResponse = (ManageUserAccountResponse) applicationManager
				.service(deleteAccountRequest);
		assertNotNull(EXPECT_A_RESULT, deleteAccountResponse);
		assertEquals(EXPECT_SUCCESS, ServiceResult.SUCCESS, deleteAccountResponse.getResult());

		final List<UserAccount> allByAfterDelete = userContainer.getAllBy(UserAccount_.username,
				createAccountRequest.getUsername());
		assertEquals(1, allByAfterDelete.size());
		assertEquals(UserLockStatus.LOCKED,allByAfterDelete.get(0).getUserLockStatus());

	}

	/**
	 * Manage user account success un lock test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageUserAccountSuccessUnLockTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();
		final RegisterUserRequest createAccountRequest = new RegisterUserRequest();
		createAccountRequest.setCountry("Sweden");
		createAccountRequest.setUsername(UUID.randomUUID().toString());
		createAccountRequest.setEmail(createAccountRequest.getUsername() + "@email.com");
		createAccountRequest.setUserpassword("Userpassword1!");
		createAccountRequest.setUserType(UserType.PRIVATE);
		createAccountRequest.setSessionId(createSessionRequest.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(createAccountRequest);
		assertNotNull("Expect a result", response);
		assertEquals(EXPECT_SUCCESS, ServiceResult.SUCCESS, response.getResult());
		setAuthenticatedAdminuser();

		final DataContainer<UserAccount, Long> userContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> firstCreatedUsed = userContainer.getAllBy(UserAccount_.username,
				createAccountRequest.getUsername());
		assertEquals(1, firstCreatedUsed.size());

		final ManageUserAccountRequest deleteAccountRequest = new ManageUserAccountRequest();
		deleteAccountRequest.setSessionId(createSessionRequest.getSessionId());
		deleteAccountRequest.setAccountOperation(AccountOperation.UNLOCK);
		deleteAccountRequest.setUserAcountId(firstCreatedUsed.get(0).getUserId());

		final ManageUserAccountResponse deleteAccountResponse = (ManageUserAccountResponse) applicationManager
				.service(deleteAccountRequest);
		assertNotNull(EXPECT_A_RESULT, deleteAccountResponse);
		assertEquals(EXPECT_SUCCESS, ServiceResult.SUCCESS, deleteAccountResponse.getResult());

		final List<UserAccount> allByAfterDelete = userContainer.getAllBy(UserAccount_.username,
				createAccountRequest.getUsername());
		assertEquals(1, allByAfterDelete.size());
		assertEquals(UserLockStatus.UNLOCKED,allByAfterDelete.get(0).getUserLockStatus());

	}

	/**
	 * Manage user account success un loc no valid account id failure test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageUserAccountSuccessUnLocNoValidAccountIdFailureTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();
		final RegisterUserRequest createAccountRequest = new RegisterUserRequest();
		createAccountRequest.setCountry("Sweden");
		createAccountRequest.setUsername(UUID.randomUUID().toString());
		createAccountRequest.setEmail(createAccountRequest.getUsername() + "@email.com");
		createAccountRequest.setUserpassword("Userpassword1!");
		createAccountRequest.setUserType(UserType.PRIVATE);
		createAccountRequest.setSessionId(createSessionRequest.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(createAccountRequest);
		assertNotNull("Expect a result", response);
		assertEquals(EXPECT_SUCCESS, ServiceResult.SUCCESS, response.getResult());
		setAuthenticatedAdminuser();

		final DataContainer<UserAccount, Long> userContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> firstCreatedUsed = userContainer.getAllBy(UserAccount_.username,
				createAccountRequest.getUsername());
		assertEquals(1, firstCreatedUsed.size());

		final ManageUserAccountRequest deleteAccountRequest = new ManageUserAccountRequest();
		deleteAccountRequest.setSessionId(createSessionRequest.getSessionId());
		deleteAccountRequest.setAccountOperation(AccountOperation.UNLOCK);
		deleteAccountRequest.setUserAcountId(firstCreatedUsed.get(0).getUserId() +"novaliduserid");

		final ManageUserAccountResponse deleteAccountResponse = (ManageUserAccountResponse) applicationManager
				.service(deleteAccountRequest);
		assertNotNull(EXPECT_A_RESULT, deleteAccountResponse);
		assertEquals(EXPECT_SUCCESS, ServiceResult.FAILURE, deleteAccountResponse.getResult());
	}


	/**
	 * Manage user account failure test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void manageUserAccountFailureTest() throws Exception {
		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		final ManageUserAccountRequest serviceRequest = new ManageUserAccountRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());

		final ManageUserAccountResponse response = (ManageUserAccountResponse) applicationManager
				.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, response);
		assertEquals(ServiceResult.FAILURE, response.getResult());
		assertEquals("accountOperation must not be null, userAcountId must not be null", response.getErrorMessage());
	}

}
