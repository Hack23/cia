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
package com.hack23.cia.service.impl.action.admin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.admin.RefreshDataViewsRequest;
import com.hack23.cia.service.api.action.admin.RefreshDataViewsResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class RefreshDataViewsServiceITest.
 */
public final class RefreshDataViewsServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;


	/**
	 * Service sucess test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceSucessTest() throws Exception {

		setAuthenticatedAdminuser();

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		final RefreshDataViewsRequest serviceRequest = new RefreshDataViewsRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());

		final RefreshDataViewsResponse  response = (RefreshDataViewsResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

	}

	/**
	 * Service request validation failure test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceRequestValidationFailureTest() throws Exception {

		setAuthenticatedAdminuser();

		final RefreshDataViewsRequest serviceRequest = new RefreshDataViewsRequest();

		final RefreshDataViewsResponse  response = (RefreshDataViewsResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.FAILURE, response.getResult());
		assertEquals("sessionId must not be null", response.getErrorMessage());

	}


}
