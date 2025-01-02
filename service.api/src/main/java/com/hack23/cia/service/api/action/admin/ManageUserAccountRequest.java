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
package com.hack23.cia.service.api.action.admin;

import javax.validation.constraints.NotNull;

import com.hack23.cia.service.api.action.common.AbstractRequest;

/**
 * The Class ManageUserAccountRequest.
 */
public final class ManageUserAccountRequest extends AbstractRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The Enum AccountOperation.
	 */
	public enum AccountOperation {

		/** The delete. */
		DELETE,

		/** The lock. */
		LOCK,

		/** The unlock. */
		UNLOCK,

		/** The verify email. */
		VERIFY_EMAIL,

		/** The password recovery. */
		PASSWORD_RECOVERY,

		/** The reset mfa. */
		RESET_MFA;
	}

	/** The session id. */
	@NotNull
	private String sessionId;

	/** The user acount id. */
	@NotNull
	private String userAcountId;

	/** The account operation. */
	@NotNull
	private AccountOperation accountOperation;

	/**
	 * Instantiates a new manage user account request.
	 */
	public ManageUserAccountRequest() {
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
	 * @param sessionId
	 *            the new session id
	 */
	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets the user acount id.
	 *
	 * @return the user acount id
	 */
	public String getUserAcountId() {
		return userAcountId;
	}

	/**
	 * Sets the user acount id.
	 *
	 * @param userAcountId
	 *            the new user acount id
	 */
	public void setUserAcountId(final String userAcountId) {
		this.userAcountId = userAcountId;
	}

	/**
	 * Gets the account operation.
	 *
	 * @return the account operation
	 */
	public AccountOperation getAccountOperation() {
		return accountOperation;
	}

	/**
	 * Sets the account operation.
	 *
	 * @param accountOperation
	 *            the new account operation
	 */
	public void setAccountOperation(final AccountOperation accountOperation) {
		this.accountOperation = accountOperation;
	}

}
