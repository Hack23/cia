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
package com.hack23.cia.service.impl;

import java.sql.Connection;
import java.util.UUID;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hack23.cia.model.internal.application.system.impl.ApplicationSessionType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionResponse;
import com.hack23.cia.testfoundation.AbstractFunctionalIntegrationTest;

/**
 * The Class AbstractServiceFunctionalIntegrationTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/application-context-service.xml",
		"classpath:/META-INF/cia-service-component-agent.xml",
		"classpath:META-INF/cia-jms-broker.xml",
		"classpath:/META-INF/application-context-service-data.xml",
		"classpath:META-INF/cia-service-external-common.xml",
		"classpath:META-INF/cia-service-external-riksdagen.xml",
		"classpath:META-INF/cia-service-external-worldbank.xml",
		"classpath:META-INF/cia-service-external-vdem.xml",
		"classpath:META-INF/cia-service-external-val.xml",
"classpath:/META-INF/cia-test-context.xml" })
public abstract class AbstractServiceFunctionalIntegrationTest extends AbstractFunctionalIntegrationTest {


	/** The application manager. */
	@Autowired
	protected ApplicationManager applicationManager;


	/**
	 * Instantiates a new abstract service functional integration test.
	 */
	public AbstractServiceFunctionalIntegrationTest() {
		super();
	}

	@Override
	protected Connection getDatabaseConnection() throws Exception {
		return null;
	}

	/**
	 * Creates the test application session.
	 *
	 * @return the creates the application session request
	 */
	protected final CreateApplicationSessionRequest createTestApplicationSession() {
		final CreateApplicationSessionRequest serviceRequest = new CreateApplicationSessionRequest();
		serviceRequest.setIpInformation("8.8.8.8");
		serviceRequest.setLocale("en_US.UTF-8");
		serviceRequest.setOperatingSystem("LINUX");
		serviceRequest.setSessionId(UUID.randomUUID().toString());
		serviceRequest.setSessionType(ApplicationSessionType.ANONYMOUS);
		serviceRequest.setUserAgentInformation("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:42.0) Gecko/20100101 Firefox/42.0");

		final CreateApplicationSessionResponse sessionResponse = (CreateApplicationSessionResponse) applicationManager
				.service(serviceRequest);
		return serviceRequest;
	}

}
