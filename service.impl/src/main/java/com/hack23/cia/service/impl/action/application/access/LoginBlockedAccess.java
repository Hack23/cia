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
package com.hack23.cia.service.impl.action.application.access;

import java.util.List;

/**
 * The Interface LoginBlockedAccess.
 */
public interface LoginBlockedAccess {

	/**
	 * Checks if is blocked.
	 *
	 * @param sessionId
	 *            the session id
	 * @param email
	 *            the email
	 * @return the login block result
	 */
	LoginBlockResult isBlocked(String sessionId,String email);

	/**
	 * The Interface LoginBlockResult.
	 */
	interface LoginBlockResult {

		/**
		 * Checks if is blocked.
		 *
		 * @return true, if is blocked
		 */
		boolean isBlocked();

		/**
		 * Gets the message.
		 *
		 * @return the message
		 */
		List<String> getMessages();
	}
}
