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
package com.hack23.cia.web.impl.ui.application.util;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class UserContextUtilTest.
 */
public class UserContextUtilTest extends AbstractUnitTest {


	/**
	 * Allow role in security context null context test.
	 */
	@Test
	public void allowRoleInSecurityContextNullContextTest() {
		assertFalse(UserContextUtil.allowRoleInSecurityContext("anyRole"));
	}

	/**
	 * Gets the request url null page test.
	 *
	 * @return the request url null page test
	 */
	@Test
	public void getRequestUrlNullPageTest() {
	    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest("GET", "/path")));
		assertEquals("http://localhost/path",UserContextUtil.getRequestUrl(null));
	}

	/**
	 * Gets the user id from security context null test.
	 *
	 * @return the user id from security context null test
	 */
	@Test
	public void getUserIdFromSecurityContextNullTest() {
		assertNull(UserContextUtil.getUserIdFromSecurityContext());
	}


}
