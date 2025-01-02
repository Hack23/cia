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

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vaadin.server.WebBrowser;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

/**
 * The Class WebBrowserUtil.
 */
public final class WebBrowserUtil {

	/** The Constant USER_AGENT_ANALYZER. */
	private static final UserAgentAnalyzer USER_AGENT_ANALYZER = UserAgentAnalyzer
            .newBuilder()
            .hideMatcherLoadStats()
            .withCache(10000)
            .build();

	/** The Constant X_FORWARDED_FOR. */
	public static final String X_FORWARDED_FOR = "X-Forwarded-For";

	/**
	 * Default constructor for WebBrowserUtil.
	 */
	public WebBrowserUtil() {
		// Default constructor
	}

	/**
	 * Gets the ip information.
	 *
	 * @param webBrowser
	 *            the web browser
	 * @return the ip information
	 */
	public static String getIpInformation(final WebBrowser webBrowser) {
		String ipInformation=webBrowser.getAddress();

		final HttpServletRequest httpRequest=((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		final String xForwardedForHeader = httpRequest.getHeader(X_FORWARDED_FOR);
		if (xForwardedForHeader != null) {
			final String[] split = xForwardedForHeader.split(",");
			if (split.length != 0) {
				ipInformation = split[0];
			}
		}
		return ipInformation;
	}


	/**
	 * Gets the operating system.
	 *
	 * @param webBrowser
	 *            the web browser
	 * @return the operating system
	 */
	public static String getOperatingSystem(final WebBrowser webBrowser) {
		synchronized (USER_AGENT_ANALYZER) {
			final UserAgent userAgent = USER_AGENT_ANALYZER.parse(webBrowser.getBrowserApplication());
			return userAgent.getValue(UserAgent.DEVICE_CLASS) + "." +userAgent.getValue(UserAgent.OPERATING_SYSTEM_NAME) +"." + userAgent.getValue(UserAgent.OPERATING_SYSTEM_VERSION);
		}
	}

}
