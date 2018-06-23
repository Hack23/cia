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
package com.hack23.cia.service.impl.action.kpi;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.kpi.ComplianceCheckRequest;
import com.hack23.cia.service.api.action.kpi.ComplianceCheckResponse;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class ComplianceCheckServiceITest.
 */
public final class ComplianceCheckServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;


	/**
	 * Test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void Test() throws Exception {
		setAuthenticatedAnonymousUser();

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		final ComplianceCheckRequest serviceRequest = new ComplianceCheckRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());

		final ComplianceCheckResponse  response = (ComplianceCheckResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertNotNull(EXPECT_A_RESULT,response.getList());
		assertFalse(response.getList().isEmpty());

		assertNotNull(EXPECT_A_RESULT,response.getStatusMap());
		assertFalse(response.getStatusMap().isEmpty());

		assertNotNull(EXPECT_A_RESULT,response.getResourceTypeMap());
		assertFalse(response.getResourceTypeMap().isEmpty());
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

		final ComplianceCheckRequest serviceRequest = new ComplianceCheckRequest();

		final ComplianceCheckResponse  response = (ComplianceCheckResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, response);
		assertEquals(ServiceResult.FAILURE, response.getResult());
		assertEquals("sessionId must not be null", response.getErrorMessage());
	}

}
