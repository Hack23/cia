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

import java.util.ArrayList;
import java.util.Collection;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class CreateApplicationEventServiceITest.
 */
@PerfTest(threads = 10, duration = 3000, warmUp = 1500)
@Required(max = 200,average = 10,percentile95=15,throughput=50000)
public class CreateApplicationEventServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The i. */
	@Rule
	public ContiPerfRule i = new ContiPerfRule();


	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;


	/**
	 * Service create application event request success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@PerfTest(threads = 4, duration = 3000, warmUp = 1500)
	@Required(max = 1000,average = 300,percentile95=350,throughput=20)
	public void serviceCreateApplicationEventRequestSuccessTest() throws Exception {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("key", "principal", authorities));

		CreateApplicationEventRequest serviceRequest = new CreateApplicationEventRequest();

		CreateApplicationEventResponse  response = (CreateApplicationEventResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result",response);
		assertEquals(ServiceResult.SUCCESS,response.getResult());
	}

}
