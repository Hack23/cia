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
 *	$Id: ApplicationManagerITest.java 6119 2015-07-31 17:44:12Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.impl/src/test/java/com/hack23/cia/service/impl/ApplicationManagerITest.java $
 */
package com.hack23.cia.service.impl;

import java.io.Serializable;
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

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.service.api.action.application.RegisterUserResponse;

/**
 * The Class ApplicationManagerITest.
 */
@PerfTest(threads = 10, duration = 3000, warmUp = 1500)
@Required(max = 200,average = 10,percentile95=15,throughput=50000)
public class ApplicationManagerITest extends AbstractServiceFunctionalIntegrationTest {

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
		SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("key", "principal", authorities));

		final DataContainer<ViewRiksdagenCommittee, Serializable> dataContainer = applicationManager.getDataContainer(ViewRiksdagenCommittee.class);
		assertNotNull("Expect a result",dataContainer);

	}

	@Test
	@PerfTest(threads = 1, duration = 3000, warmUp = 1500)
	@Required(max = 400,average = 10,percentile95=15,throughput=5000)
	public void serviceRegisterUserRequestSuccessTest() throws Exception {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("key", "principal", authorities));

		RegisterUserResponse  response = (RegisterUserResponse) applicationManager.service(new RegisterUserRequest());
		assertNotNull("Expect a result",response);
	}



}
