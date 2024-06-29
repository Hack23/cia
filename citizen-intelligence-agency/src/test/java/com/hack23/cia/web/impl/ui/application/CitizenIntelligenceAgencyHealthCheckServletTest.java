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
package com.hack23.cia.web.impl.ui.application;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class CitizenIntelligenceAgencyHealthCheckServletTest.
 */
public class CitizenIntelligenceAgencyHealthCheckServletTest extends AbstractUnitTest {

	/**
	 * Check health check test.
	 *
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void checkHealthCheckTest() throws ServletException, IOException {
		final CitizenIntelligenceAgencyHealthCheckServlet healthCheckServlet = new CitizenIntelligenceAgencyHealthCheckServlet();

		final MockHttpServletRequest request = new MockHttpServletRequest();
		final MockHttpServletResponse response = new MockHttpServletResponse();

		healthCheckServlet.doGet(request, response);
		assertEquals("OK\n", response.getContentAsString());
	}

}
