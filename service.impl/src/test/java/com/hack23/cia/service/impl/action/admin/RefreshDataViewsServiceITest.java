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

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.admin.RefreshDataViewsRequest;
import com.hack23.cia.service.api.action.admin.RefreshDataViewsResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class RefreshDataViewsServiceITest.
 */
public class RefreshDataViewsServiceITest extends AbstractServiceFunctionalIntegrationTest {

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

		final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));

		SecurityContextHolder.getContext()
				.setAuthentication(new AnonymousAuthenticationToken("key", "principal", authorities));

		final CreateApplicationSessionRequest createSessionRequest = createTestApplicationSession();

		RefreshDataViewsRequest serviceRequest = new RefreshDataViewsRequest();
		serviceRequest.setSessionId(createSessionRequest.getSessionId());

		final RefreshDataViewsResponse  response = (RefreshDataViewsResponse) applicationManager.service(new RefreshDataViewsRequest());
		assertNotNull("Expect a result",response);
	}

}
