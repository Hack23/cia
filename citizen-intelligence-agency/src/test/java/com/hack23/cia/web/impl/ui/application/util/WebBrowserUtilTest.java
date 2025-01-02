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
package com.hack23.cia.web.impl.ui.application.util;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.vaadin.server.WebBrowser;

/**
 * The Class WebBrowserUtilTest.
 */
public class WebBrowserUtilTest extends AbstractUnitTest {


	/**
	 * Gets the ip information null test.
	 *
	 * @return the ip information null test
	 */
	@Test
	public void getIpInformationNullTest() {
	    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest("GET", "/path")));
	    final WebBrowser webBrowser = new WebBrowser();
		assertNull(WebBrowserUtil.getIpInformation(webBrowser));
	}

	/**
	 * Gets the ip information X forward proxy test.
	 *
	 * @return the ip information X forward proxy test
	 */
	@Test
	public void getIpInformationXForwardProxyTest() {
	    final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/path");
	    request.addHeader(WebBrowserUtil.X_FORWARDED_FOR, "203.0.113.195, 70.41.3.18, 150.172.238.178");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	    final WebBrowser webBrowser = new WebBrowser();
		assertEquals("203.0.113.195",WebBrowserUtil.getIpInformation(webBrowser));
	}

	/**
	 * Gets the ip information X forward test.
	 *
	 * @return the ip information X forward test
	 */
	@Test
	public void getIpInformationXForwardTest() {
	    final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/path");
	    request.addHeader(WebBrowserUtil.X_FORWARDED_FOR, "203.0.113.195");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	    final WebBrowser webBrowser = new WebBrowser();
		assertEquals("203.0.113.195",WebBrowserUtil.getIpInformation(webBrowser));
	}

}
