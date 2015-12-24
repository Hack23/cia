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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSessionType;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.model.internal.application.user.impl.UserType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionResponse;
import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.service.api.action.application.RegisterUserResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;

/**
 * The Class ApplicationManagerITest.
 */
@PerfTest(threads = 10, duration = 3000, warmUp = 1500)
@Required(max = 200, average = 10, percentile95 = 15, throughput = 50000)
public class ApplicationManagerITest extends AbstractServiceFunctionalIntegrationTest {

	/** The i. */
	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Gets the data container success test.
	 *
	 * @return the data container success test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getDataContainerSuccessTest() throws Exception {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		SecurityContextHolder.getContext()
				.setAuthentication(new AnonymousAuthenticationToken("key", "principal", authorities));

		final DataContainer<ViewRiksdagenCommittee, Serializable> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenCommittee.class);
		assertNotNull("Expect a result", dataContainer);

	}

	/**
	 * Service register user request success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@PerfTest(threads = 4, duration = 3000, warmUp = 1500)
	@Required(max = 1000, average = 300, percentile95 = 350, throughput = 20)
	public void serviceRegisterUserRequestSuccessTest() throws Exception {
		CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstion();


		RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("userpassword");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result", response);
		assertEquals(ServiceResult.SUCCESS, response.getResult());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());
	}


	/**
	 * Service login request success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@PerfTest(threads = 4, duration = 3000, warmUp = 1500)
	@Required(max = 1500, average = 500, percentile95 = 750, throughput = 5)
	public void serviceLoginRequestSuccessTest() throws Exception {
		CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstion();


		RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("userpassword");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull("Expect a result", response);
		assertEquals(ServiceResult.SUCCESS, response.getResult());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());



		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(serviceRequest.getEmail());
		loginRequest.setSessionId(serviceRequest.getSessionId());
		loginRequest.setUserpassword(serviceRequest.getUserpassword());

		LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

		assertNotNull("Expect a result", loginResponse);
		assertEquals(ServiceResult.SUCCESS, loginResponse.getResult());

	}



	/**
	 * Creates the application sesstion.
	 *
	 * @return the creates the application session request
	 */
	private CreateApplicationSessionRequest createApplicationSesstion() {

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		SecurityContextHolder.getContext()
				.setAuthentication(new AnonymousAuthenticationToken("key", "principal", authorities));

		CreateApplicationSessionRequest serviceRequest = new CreateApplicationSessionRequest();
		serviceRequest.setIpInformation("8.8.8.8");
		serviceRequest.setLocale("en_US.UTF-8");
		serviceRequest.setOperatingSystem("LINUX");
		serviceRequest.setSessionId(UUID.randomUUID().toString());
		serviceRequest.setSessionType(ApplicationSessionType.ANONYMOUS);
		serviceRequest.setUserAgentInformation(
				"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:42.0) Gecko/20100101 Firefox/42.0");

		CreateApplicationSessionResponse response = (CreateApplicationSessionResponse) applicationManager
				.service(serviceRequest);
		assertNotNull("Expect a result", response);
		assertEquals(ServiceResult.SUCCESS, response.getResult());
		return serviceRequest;
	}
}
