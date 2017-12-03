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
package com.hack23.cia.service.impl.action.admin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.admin.RemoveDataRequest;
import com.hack23.cia.service.api.action.admin.RemoveDataRequest.DataType;
import com.hack23.cia.service.api.action.admin.RemoveDataResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class RemoveDataServiceITest.
 */
public final class RemoveDataServiceITest extends AbstractServiceFunctionalIntegrationTest {

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

		setAuthenticatedAdminuser();

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();
		
		for (DataType dataType : RemoveDataRequest.DataType.values()) {
			final RemoveDataRequest serviceRequest = new RemoveDataRequest();
			serviceRequest.setSessionId(createSessionRequest.getSessionId());
			serviceRequest.setDataType(dataType);
			final RemoveDataResponse  response = (RemoveDataResponse) applicationManager.service(new RemoveDataRequest());
			assertNotNull(EXPECT_A_RESULT,response);
			assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());			
		}
	}


}
