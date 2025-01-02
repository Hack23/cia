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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hack23.cia.service.api.action.common.AbstractRequest;

/**
 * The Class LoginRequest.
 */
public final class LoginRequest extends AbstractRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session id. */
	@NotNull
	private String sessionId;

	/** The email. */
	@Email
	private String email;

	/** The userpassword. */
	@NotNull
	@Size(min = 4, max = 64)
	private String userpassword;

	/** The otp code. */
	private String otpCode;

	/**
	 * Instantiates a new login request.
	 */
	public LoginRequest() {
		super();
	}

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

	/**
	 * Gets the otp code.
	 *
	 * @return the otp code
	 */
	public String getOtpCode() {
		return otpCode;
	}

	/**
	 * Sets the otp code.
	 *
	 * @param otpCode
	 *            the new otp code
	 */
	public void setOtpCode(final String otpCode) {
		this.otpCode = otpCode;
	}

}
