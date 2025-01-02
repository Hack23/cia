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

import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSessionType;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationSessionDAO;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class CreateApplicationSessionServiceITest.
 */
public final class CreateApplicationSessionServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/** The application session dao. */
	@Autowired
	private ApplicationSessionDAO applicationSessionDAO;

	/**
	 * Service create application session request success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void serviceCreateApplicationSessionRequestSuccessTest() throws Exception {
		setAuthenticatedAnonymousUser();

		final CreateApplicationSessionRequest serviceRequest = new CreateApplicationSessionRequest();
		serviceRequest.setIpInformation("8.8.8.8");
		serviceRequest.setLocale("en_US.UTF-8");
		serviceRequest.setOperatingSystem("LINUX");
		serviceRequest.setSessionId(UUID.randomUUID().toString());
		serviceRequest.setSessionType(ApplicationSessionType.ANONYMOUS);
		serviceRequest.setUserAgentInformation("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:42.0) Gecko/20100101 Firefox/42.0");

		final CreateApplicationSessionResponse response = (CreateApplicationSessionResponse) applicationManager
				.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

		final List<ApplicationSession> findListByProperty = applicationSessionDAO.findListByProperty(ApplicationSession_.sessionId, serviceRequest.getSessionId());
		assertEquals(1, findListByProperty.size());
		final ApplicationSession applicationSession = findListByProperty.get(0);

		assertNotNull(applicationSession);
		assertNotNull(applicationSession.getCreatedDate());

		assertEquals(serviceRequest.getIpInformation(), applicationSession.getIpInformation());
		assertEquals(serviceRequest.getOperatingSystem(), applicationSession.getOperatingSystem());
		assertEquals(serviceRequest.getLocale(), applicationSession.getLocale());
		assertEquals(serviceRequest.getUserAgentInformation(), applicationSession.getUserAgentInformation());
		assertEquals(serviceRequest.getSessionType(), applicationSession.getSessionType());
	}

}
