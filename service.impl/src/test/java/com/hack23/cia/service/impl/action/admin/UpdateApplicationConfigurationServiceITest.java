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

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.service.api.action.admin.UpdateApplicationConfigurationRequest;
import com.hack23.cia.service.api.action.admin.UpdateApplicationConfigurationResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class UpdateApplicationConfigurationServiceITest.
 */
public final class UpdateApplicationConfigurationServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application configuration service. */
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;


	/**
	 * Success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void successTest() throws Exception {

		setAuthenticatedAdminuser();
		final CreateApplicationSessionRequest createTestApplicationSession = createTestApplicationSession();

		final String randomUUID = UUID.randomUUID().toString();
		final ApplicationConfiguration applicationConfigurationToUpdate = applicationConfigurationService.checkValueOrLoadDefault(
				"UpdateApplicationRequestTest property",
				"UpdateApplicationRequestTest should be set to true/false",
				ConfigurationGroup.AUTHORIZATION, UpdateApplicationConfigurationServiceITest.class.getSimpleName(),
				"UpdateApplicationConfigurationService ITest", "FunctionalIntegrationTest",
				"UpdateApplicationRequestTest." + randomUUID, "true");


		final UpdateApplicationConfigurationRequest serviceRequest = new UpdateApplicationConfigurationRequest();
		serviceRequest.setApplicationConfigurationId(applicationConfigurationToUpdate.getHjid());
		serviceRequest.setSessionId(createTestApplicationSession.getSessionId());

		serviceRequest.setComponentDescription("componentDescription");
		serviceRequest.setConfigDescription("configDescription");
		serviceRequest.setConfigTitle("configTitle");
		serviceRequest.setComponentTitle("componentTitle");
		serviceRequest.setPropertyValue("false");

		final UpdateApplicationConfigurationResponse  response = (UpdateApplicationConfigurationResponse) applicationManager.service(serviceRequest);


		assertNotNull(EXPECT_A_RESULT,response);
		assertEquals(EXPECT_SUCCESS, ServiceResult.SUCCESS,response.getResult());


		final ApplicationConfiguration applicationConfigurationUpdated = applicationConfigurationService.checkValueOrLoadDefault(
				"UpdateApplicationRequestTest property",
				"UpdateApplicationRequestTest should be set to true/false",
				ConfigurationGroup.AUTHORIZATION, UpdateApplicationConfigurationServiceITest.class.getSimpleName(),
				"UpdateApplicationConfigurationService ITest", "FunctionalIntegrationTest",
				"UpdateApplicationRequestTest." + randomUUID, "true");


		assertEquals("false",applicationConfigurationUpdated.getPropertyValue());
		assertEquals("configTitle",applicationConfigurationUpdated.getConfigTitle());
		assertEquals("configDescription",applicationConfigurationUpdated.getConfigDescription());
		assertEquals("componentTitle",applicationConfigurationUpdated.getComponentTitle());
		assertEquals("componentDescription",applicationConfigurationUpdated.getComponentDescription());

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
		final UpdateApplicationConfigurationRequest serviceRequest = new UpdateApplicationConfigurationRequest();
		serviceRequest.setComponentDescription("componentDescription");
		serviceRequest.setConfigDescription("configDescription");
		serviceRequest.setConfigTitle("configTitle");
		serviceRequest.setComponentTitle("componentTitle");
		serviceRequest.setPropertyValue("false");

		final UpdateApplicationConfigurationResponse  response = (UpdateApplicationConfigurationResponse) applicationManager.service(serviceRequest);

		assertNotNull(EXPECT_A_RESULT,response);
		assertEquals(EXPECT_SUCCESS, ServiceResult.FAILURE,response.getResult());
		assertEquals("sessionId must not be null", response.getErrorMessage());
	}

	/**
	 * Failure config do not exist test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void failureConfigDoNotExistTest() throws Exception {

		setAuthenticatedAdminuser();
		final CreateApplicationSessionRequest createTestApplicationSession = createTestApplicationSession();


		final UpdateApplicationConfigurationRequest serviceRequest = new UpdateApplicationConfigurationRequest();
		serviceRequest.setApplicationConfigurationId(-1L);
		serviceRequest.setSessionId(createTestApplicationSession.getSessionId());

		serviceRequest.setComponentDescription("componentDescription");
		serviceRequest.setConfigDescription("configDescription");
		serviceRequest.setConfigTitle("configTitle");
		serviceRequest.setComponentTitle("componentTitle");
		serviceRequest.setPropertyValue("false");

		final UpdateApplicationConfigurationResponse  response = (UpdateApplicationConfigurationResponse) applicationManager.service(serviceRequest);


		assertNotNull(EXPECT_A_RESULT,response);
		assertEquals(EXPECT_SUCCESS, ServiceResult.FAILURE,response.getResult());
	}

}
