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
package com.hack23.cia.service.impl.action.application;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.application.DestroyApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationSessionDAO;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class DestroyApplicationSessionServiceITest.
 */
public final class DestroyApplicationSessionServiceITest extends AbstractServiceFunctionalIntegrationTest {


	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/** The application session dao. */
	@Autowired
	private ApplicationSessionDAO applicationSessionDAO;

	/**
	 * Service destroy application session request success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceDestroyApplicationSessionRequestSuccessTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final CreateApplicationSessionRequest createTestApplicationSession = createTestApplicationSession();

		final DestroyApplicationSessionRequest destroyApplicationSessionRequest = new DestroyApplicationSessionRequest();

		destroyApplicationSessionRequest.setSessionId(createTestApplicationSession.getSessionId());
		final ServiceResponse response = applicationManager.service(destroyApplicationSessionRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS,response.getResult());

		final ApplicationSession applicationSession = applicationSessionDAO.findFirstByProperty(ApplicationSession_.sessionId, createTestApplicationSession.getSessionId());
		assertNotNull(EXPECT_A_RESULT,applicationSession);
		assertNotNull(EXPECT_A_RESULT,applicationSession.getDestroyedDate());
	}

	/**
	 * Service destroy application session request no session id exist failure test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void serviceDestroyApplicationSessionRequestNoSessionIdExistFailureTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final CreateApplicationSessionRequest createTestApplicationSession = createTestApplicationSession();

		final DestroyApplicationSessionRequest destroyApplicationSessionRequest = new DestroyApplicationSessionRequest();

		destroyApplicationSessionRequest.setSessionId(createTestApplicationSession.getSessionId() + "not-valid");
		final ServiceResponse response = applicationManager.service(destroyApplicationSessionRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.FAILURE,response.getResult());

	}

	/**
	 * Service request validation failure test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceRequestValidationFailureTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final DestroyApplicationSessionRequest destroyApplicationSessionRequest = new DestroyApplicationSessionRequest();
		final ServiceResponse response = applicationManager.service(destroyApplicationSessionRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertEquals("sessionId must not be null", response.getErrorMessage());
	}

}
