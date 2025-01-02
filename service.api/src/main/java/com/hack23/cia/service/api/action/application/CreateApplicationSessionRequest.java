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
package com.hack23.cia.service.api.action.application;

import javax.validation.constraints.NotNull;

import com.hack23.cia.model.internal.application.system.impl.ApplicationSessionType;
import com.hack23.cia.service.api.action.common.AbstractRequest;

/**
 * The Class CreateApplicationSessionRequest.
 */
public final class CreateApplicationSessionRequest extends AbstractRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session id. */
	@NotNull
	private String sessionId;

	/** The locale. */
	private String locale;

	/** The operating system. */
	private String operatingSystem;

	/** The ip information. */
	private String ipInformation;

	/** The user agent information. */
	private String userAgentInformation;

	/** The screen size. */
	private String screenSize;

	/** The time zone. */
	private String timeZone;

	/** The session type. */
	private ApplicationSessionType sessionType;

	/**
	 * Instantiates a new creates the application session request.
	 */
	public CreateApplicationSessionRequest() {
		super();
	}

	/**
	 * Gets the ip information.
	 *
	 * @return the ip information
	 */
	public String getIpInformation() {
		return ipInformation;
	}

	/**
	 * Gets the session type.
	 *
	 * @return the session type
	 */
	public ApplicationSessionType getSessionType() {
		return sessionType;
	}

	/**
	 * Gets the user agent information.
	 *
	 * @return the user agent information
	 */
	public String getUserAgentInformation() {
		return userAgentInformation;
	}

	/**
	 * Sets the ip information.
	 *
	 * @param ipInformation
	 *            the new ip information
	 */
	public void setIpInformation(final String ipInformation) {
		this.ipInformation = ipInformation;
	}

	/**
	 * Sets the session type.
	 *
	 * @param sessionType
	 *            the new session type
	 */
	public void setSessionType(final ApplicationSessionType sessionType) {
		this.sessionType = sessionType;
	}

	/**
	 * Sets the user agent information.
	 *
	 * @param userAgentInformation
	 *            the new user agent information
	 */
	public void setUserAgentInformation(final String userAgentInformation) {
		this.userAgentInformation = userAgentInformation;
	}

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionId
	 *            the new session id
	 */
	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets the locale.
	 *
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * Sets the locale.
	 *
	 * @param locale
	 *            the new locale
	 */
	public void setLocale(final String locale) {
		this.locale = locale;
	}

	/**
	 * Gets the operating system.
	 *
	 * @return the operating system
	 */
	public String getOperatingSystem() {
		return operatingSystem;
	}

	/**
	 * Sets the operating system.
	 *
	 * @param operatingSystem
	 *            the new operating system
	 */
	public void setOperatingSystem(final String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	/**
	 * Gets the screen size.
	 *
	 * @return the screen size
	 */
	public String getScreenSize() {
		return screenSize;
	}

	/**
	 * Sets the screen size.
	 *
	 * @param screenSize
	 *            the new screen size
	 */
	public void setScreenSize(final String screenSize) {
		this.screenSize = screenSize;
	}

	/**
	 * Gets the time zone.
	 *
	 * @return the time zone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * Sets the time zone.
	 *
	 * @param timeZone
	 *            the new time zone
	 */
	public void setTimeZone(final String timeZone) {
		this.timeZone = timeZone;
	}

}
