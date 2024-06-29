/*
 * Copyright 2010-2024 James Pether Sörling
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

import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class SecurityAuthenticationProviderTest.
 */
public class SecurityAuthenticationProviderTest extends AbstractUnitTest {

	/**
	 * Authenticate verify dummy impl.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void authenticateVerifyDummyImpl() throws Exception {
		final SecurityAuthenticationProvider provider = new SecurityAuthenticationProvider();
		assertTrue(provider.supports(UsernamePasswordAuthenticationToken.class));
		assertNull(provider.authenticate(null));
	}
}
