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
package com.hack23.cia.service.api.action.application;

import com.hack23.cia.service.api.action.common.AbstractResponse;

/**
 * The Class LoginResponse.
 */
public final class LoginResponse extends AbstractResponse {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The Enum ErrorMessage.
	 */
	public enum ErrorMessage {
		USERNAME_OR_PASSWORD_DO_NOT_MATCH;
	}

	/**
	 * Instantiates a new login response.
	 *
	 * @param result
	 *            the result
	 */
	public LoginResponse(final ServiceResult result) {
		super(result);
	}

}
