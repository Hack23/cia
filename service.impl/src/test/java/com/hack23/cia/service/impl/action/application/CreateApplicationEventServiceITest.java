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
package com.hack23.cia.service.impl.action.application;

import java.util.List;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationSessionDAO;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class CreateApplicationEventServiceITest.
 */
public final class CreateApplicationEventServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The i. */
	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	/** The application session dao. */
	@Autowired
	private ApplicationSessionDAO applicationSessionDAO;


	/**
	 * Service create application event request success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@PerfTest(threads = 4, duration = 3000, warmUp = 1500)
	@Required(max = 1000,average = 400,percentile95=450,throughput=10)
	public void serviceCreateApplicationEventRequestSuccessTest() throws Exception {
		setAuthenticatedAnonymousUser();

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		final CreateApplicationEventRequest serviceRequest = new CreateApplicationEventRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());
		serviceRequest.setApplicationMessage("applicationMessage");

		serviceRequest.setEventGroup(ApplicationEventGroup.USER);
		serviceRequest.setApplicationOperation(ApplicationOperationType.CREATE);

		serviceRequest.setPage("Test");
		serviceRequest.setPageMode("PageMode");
		serviceRequest.setElementId("ElementId");

		serviceRequest.setActionName("Content");

		serviceRequest.setApplicationMessage("applicationMessage");
		serviceRequest.setErrorMessage("errorMessage");


		final CreateApplicationEventResponse  response = (CreateApplicationEventResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT,response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS,response.getResult());

		final List<ApplicationSession> findListByProperty = applicationSessionDAO.findListByProperty(ApplicationSession_.sessionId, serviceRequest.getSessionId());
		assertEquals(1, findListByProperty.size());
		final ApplicationSession applicationSession = findListByProperty.get(0);
		assertNotNull(applicationSession);

		assertEquals(1, applicationSession.getEvents().size());


	}


}
