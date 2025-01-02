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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.util.ReflectionTestUtils;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.admin.RemoveDataRequest;
import com.hack23.cia.service.api.action.admin.RemoveDataRequest.DataType;
import com.hack23.cia.service.api.action.admin.RemoveDataResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.RemoveDataManager;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;
import com.hack23.cia.service.impl.action.common.BusinessService;

/**
 * The Class RemoveDataServiceITest.
 */
public final class RemoveDataServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	@Autowired
	@Qualifier("RemoveDataService")
	private BusinessService<? extends ServiceRequest, ? extends ServiceResponse> removeDataService;

	/**
	 * Test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void Test() throws Exception {
		final RemoveDataManager removeDataManager = Mockito.mock(RemoveDataManager.class);
		ReflectionTestUtils.setField(removeDataService, "removeDataManager", removeDataManager);

		setAuthenticatedAdminuser();

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		for (final DataType dataType : RemoveDataRequest.DataType.values()) {
			final RemoveDataRequest serviceRequest = new RemoveDataRequest();
			serviceRequest.setSessionId(createSessionRequest.getSessionId());
			serviceRequest.setDataType(dataType);
			final RemoveDataResponse response = (RemoveDataResponse) applicationManager.service(serviceRequest);
			assertNotNull(EXPECT_A_RESULT, response);
			assertEquals(EXPECT_SUCCESS, ServiceResult.SUCCESS, response.getResult());
		}

		Mockito.verify(removeDataManager).removeApplicationHistory();
		Mockito.verify(removeDataManager).removeCommitteeProposals();
		Mockito.verify(removeDataManager).removeDocuments();
		Mockito.verify(removeDataManager).removeDocumentStatus();
		Mockito.verify(removeDataManager).removePersonData();
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
		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		final RemoveDataRequest serviceRequest = new RemoveDataRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());
		final RemoveDataResponse response = (RemoveDataResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, response);
		assertEquals(EXPECT_SUCCESS, ServiceResult.FAILURE, response.getResult());
		assertEquals("dataType must not be null", response.getErrorMessage());
	}

}
