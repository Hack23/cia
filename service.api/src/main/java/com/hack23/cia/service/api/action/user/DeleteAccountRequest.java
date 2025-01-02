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
package com.hack23.cia.service.api.action.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hack23.cia.service.api.action.common.AbstractRequest;

/**
 * The Class DeleteAccountRequest.
 */
public final class DeleteAccountRequest extends AbstractRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session id. */
	@NotNull
	private String sessionId;

	/** The userpassword. */
	@NotNull
	@Size(min = 4, max = 64)
	private String userpassword;

	/**
	 * Instantiates a new delete account request.
	 */
	public DeleteAccountRequest() {
		super();
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
	 * @param sessionId the new session id
	 */
	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
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
	 * @param userpassword the new userpassword
	 */
	public void setUserpassword(final String userpassword) {
		this.userpassword = userpassword;
	}


}
