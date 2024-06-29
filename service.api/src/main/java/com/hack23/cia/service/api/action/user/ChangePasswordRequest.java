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
package com.hack23.cia.service.api.action.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hack23.cia.service.api.action.common.AbstractRequest;

/**
 * The Class ChangePasswordRequest.
 */
public final class ChangePasswordRequest extends AbstractRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session id. */
	@NotNull
	private String sessionId;

	/** The current password. */
	@NotNull
	@Size(min = 4, max = 64)
	private String currentPassword;

	/** The new password. */
	@NotNull
	@Size(min = 4, max = 64)
	private String newPassword;

	/** The repeat new password. */
	@NotNull
	@Size(min = 4, max = 64)
	private String repeatNewPassword;

	/**
	 * Instantiates a new change password request.
	 */
	public ChangePasswordRequest() {
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
	 * Gets the current password.
	 *
	 * @return the current password
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}

	/**
	 * Sets the current password.
	 *
	 * @param currentPassword the new current password
	 */
	public void setCurrentPassword(final String currentPassword) {
		this.currentPassword = currentPassword;
	}

	/**
	 * Gets the new password.
	 *
	 * @return the new password
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the new password.
	 *
	 * @param newPassword the new new password
	 */
	public void setNewPassword(final String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Gets the repeat new password.
	 *
	 * @return the repeat new password
	 */
	public String getRepeatNewPassword() {
		return repeatNewPassword;
	}

	/**
	 * Sets the repeat new password.
	 *
	 * @param repeatNewPassword the new repeat new password
	 */
	public void setRepeatNewPassword(final String repeatNewPassword) {
		this.repeatNewPassword = repeatNewPassword;
	}

}
