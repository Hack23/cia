/*
 * Copyright 2014 James Pether Sörling
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

/**
 * The Class WebBrowserUtil.
 */
public final class WebBrowserUtil {

	/** The Constant UNKNOWN. */
	private static final String UNKNOWN = "unknown";

	/** The Constant I_PHONE. */
	private static final String I_PHONE = "IPhone";

	/** The Constant IPAD. */
	private static final String IPAD = "IPad";

	/** The Constant IOS. */
	private static final String IOS = "IOS";

	/** The Constant ANDROID. */
	private static final String ANDROID = "Android";

	/** The Constant MAC_OSX. */
	private static final String MAC_OSX = "MacOSX";

	/** The Constant WINDOWS_PHONE. */
	private static final String WINDOWS_PHONE = "WindowsPhone";

	/** The Constant WINDOWS2. */
	private static final String WINDOWS2 = "Windows";

	/** The Constant LINUX. */
	private static final String LINUX = "Linux";

	/** The Constant X_FORWARDED_FOR. */
	private static final String X_FORWARDED_FOR = "X-Forwarded-For";

	/**
	 * Instantiates a new web browser util.
	 */
	private WebBrowserUtil() {
		super();
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
		String osName = UNKNOWN;
	       if (webBrowser.isLinux()) {
			osName = LINUX;
		} else if (webBrowser.isWindows()) {
			osName = WINDOWS2;
		} else if (webBrowser.isWindowsPhone()) {
			osName = WINDOWS_PHONE;
		} else if (webBrowser.isMacOSX()) {
			osName = MAC_OSX;
		} else if (webBrowser.isAndroid()) {
			osName = ANDROID;
		} else if (webBrowser.isIOS()) {
			osName = IOS;
		} else if (webBrowser.isIPad()) {
			osName = IPAD;
		} else if (webBrowser.isIPhone()) {
			osName = I_PHONE;
		}
		return osName;
	}


}
