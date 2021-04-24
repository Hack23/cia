/*
 * Copyright 2010-2021 James Pether Sörling
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

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.DocumentWordCountRequest;
import com.hack23.cia.service.api.action.user.DocumentWordCountResponse;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class DocumentWordCountServiceITest.
 */
public final class DocumentWordCountServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;


	/**
	 * Service request success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void serviceRequestSuccessTest() throws Exception {
		setAuthenticatedAnonymousUser();

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		final DocumentWordCountRequest serviceRequest = new DocumentWordCountRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());
		serviceRequest.setMaxResults(100);
		serviceRequest.setDocumentId("GNB47");

		final DocumentWordCountResponse  response = (DocumentWordCountResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result",response);
		assertTrue("Expect a result",response.getWordCountMap().size() > 0);
	}

	
	/**
	 * Service request failure no matching document test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void serviceRequestFailureNoMatchingDocumentTest() throws Exception {
		setAuthenticatedAnonymousUser();

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		final DocumentWordCountRequest serviceRequest = new DocumentWordCountRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());
		serviceRequest.setMaxResults(100);
		serviceRequest.setDocumentId("BAD_DOC_REF_GNB47");

		final DocumentWordCountResponse  response = (DocumentWordCountResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result",response);
		assertTrue("Expect no result",response.getWordCountMap().isEmpty());
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

		final DocumentWordCountRequest serviceRequest = new DocumentWordCountRequest();
		serviceRequest.setMaxResults(100);
		serviceRequest.setDocumentId("GNB47");

		final DocumentWordCountResponse  response = (DocumentWordCountResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result",response);
		assertEquals(ServiceResult.FAILURE, response.getResult());
		assertEquals("sessionId must not be null", response.getErrorMessage());
	}


}
