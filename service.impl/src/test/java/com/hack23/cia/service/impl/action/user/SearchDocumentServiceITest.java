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
package com.hack23.cia.service.impl.action.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.user.SearchDocumentRequest;
import com.hack23.cia.service.api.action.user.SearchDocumentResponse;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class SearchDocumentServiceITest.
 */
public final class SearchDocumentServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;


	/**
	 * Search success find in title.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void searchSuccessFindInTitle() throws Exception {
		setAuthenticatedAnonymousUser();

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		final SearchDocumentRequest serviceRequest = new SearchDocumentRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());
		serviceRequest.setMaxResults(10);
		serviceRequest.setSearchExpression("programmering");

		final SearchDocumentResponse  response = (SearchDocumentResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertTrue(EXPECT_A_RESULT,response.getResultElement().size() > 0);
	}

	/**
	 * Search success find in content.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void searchSuccessFindInContent() throws Exception {
		setAuthenticatedAnonymousUser();

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		final SearchDocumentRequest serviceRequest = new SearchDocumentRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());
		serviceRequest.setMaxResults(10);
		serviceRequest.setSearchExpression("fritids");

		final SearchDocumentResponse  response = (SearchDocumentResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertTrue(EXPECT_A_RESULT,response.getResultElement().size() > 0);
	}

	/**
	 * Search success no results.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void searchSuccessNoResults() throws Exception {
		setAuthenticatedAnonymousUser();

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		final SearchDocumentRequest serviceRequest = new SearchDocumentRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());
		serviceRequest.setMaxResults(10);
		serviceRequest.setSearchExpression("somethingthatdonotexist");

		final SearchDocumentResponse  response = (SearchDocumentResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertTrue(EXPECT_A_RESULT,response.getResultElement().isEmpty());
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

		final SearchDocumentRequest serviceRequest = new SearchDocumentRequest();
		serviceRequest.setMaxResults(10);
		serviceRequest.setSearchExpression("kan");

		final SearchDocumentResponse  response = (SearchDocumentResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertEquals("sessionId must not be null", response.getErrorMessage());
	}


}
