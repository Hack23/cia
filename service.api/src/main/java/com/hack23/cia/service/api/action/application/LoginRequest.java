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
package com.hack23.cia.service.api.action.application;

import com.hack23.cia.service.api.action.common.ServiceRequest;


/**
 * The Class LoginRequest.
 */
public final class LoginRequest implements ServiceRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /** The session id. */
    private String sessionId;

    /** The email. */
    private String email;

    /** The userpassword. */
    private String userpassword;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Gets the userpassword.
	 *
	 * @return the userpassword
	 */
	public String getUserpassword() {
		return userpassword;
	}

	/**
	 * Sets the userpassword.
	 *
	 * @param userpassword
	 *            the new userpassword
	 */
	public void setUserpassword(final String userpassword) {
		this.userpassword = userpassword;
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

}
